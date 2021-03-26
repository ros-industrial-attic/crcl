/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crcl4java.zeroc.ice.server;

import crcl.utils.CRCLSocket;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class ServerMain extends Ice.Application {

    public static void main(String[] args) {
        ServerMain app = new ServerMain();
        int status = app.main("Server", args);
        System.exit(status);
    }

    @Override
    public int run(String[] args) {
        Ice.Communicator ic = communicator(); // Ice.Util.initialize(args);
        Ice.ObjectAdapter adapter
                = ic.createObjectAdapterWithEndpoints("CRCLSocketWrapper", "default -p 10000");
        try (CRCLSocket crclSocket = new CRCLSocket("localhost", CRCLSocket.DEFAULT_PORT)) {
            Ice.Object object = new CRCLSocketWrapper(crclSocket);
            adapter.add(object, ic.stringToIdentity("CRCLSocketWrapper"));
            adapter.activate();
            ic.waitForShutdown();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

}
