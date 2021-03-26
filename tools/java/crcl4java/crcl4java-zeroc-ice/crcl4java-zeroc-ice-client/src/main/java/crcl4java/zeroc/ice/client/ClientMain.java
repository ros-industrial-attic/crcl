/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crcl4java.zeroc.ice.client;

import static converters.fromice.FromIceConverters.fromIce;
import static converters.toice.ToIceConverters.toIce;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLStatusType;
import crcl.base.GetStatusType;
import crcl.utils.CRCLException;
import crcl.utils.CRCLServerSocket;
import crcl.utils.CRCLServerSocketEvent;
import crcl.utils.CRCLSocket;
import crcl.zerocice.CRCLSocketWrapperPrx;
import crcl.zerocice.CRCLSocketWrapperPrxHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import java2slice.crcl.base.CRCLStatusTypeIce;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class ClientMain extends Ice.Application {

    public static void main(String[] args) {
        ClientMain app = new ClientMain();
        int status = app.main("Client", args);
        System.exit(status);
    }

    @Override
    public int run(String[] args) {
        Ice.Communicator ic = communicator(); //Ice.Util.initialize(args);
        Ice.ObjectPrx base = ic.stringToProxy("CRCLSocketWrapper:default -p 10000");
        CRCLSocketWrapperPrx wrapper = CRCLSocketWrapperPrxHelper.checkedCast(base);
        if (wrapper == null) {
            throw new Error("Invalid proxy");
        }
        try (CRCLServerSocket svrSocket = new CRCLServerSocket(CRCLSocket.DEFAULT_PORT + 1)) {
            svrSocket.setQueueEvents(true);
            svrSocket.start();
            while (!Thread.currentThread().isInterrupted()) {
                CRCLServerSocketEvent event = svrSocket.waitForEvent();
                CRCLCommandInstanceType cmdInstance = event.getInstance();
                if (null != cmdInstance) {
                    CRCLCommandType cmd = cmdInstance.getCRCLCommand();
                    if (cmd instanceof GetStatusType) {
                        try {
                            CRCLStatusTypeIce statusIce = wrapper.readStatus();
                            CRCLStatusType status = fromIce(statusIce);
                            event.getSource().writeStatus(status);
                        } catch (CRCLException ex) {
                            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        wrapper.writeCommand(toIce(cmdInstance));
                    }
                } else {
                    throw new Exception(event.getException());
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
