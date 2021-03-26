/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crcl4java.zeroc.ice.server;

import Ice.Current;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLStatusType;
import crcl.utils.CRCLException;
import crcl.utils.CRCLSocket;
import crcl.zerocice._CRCLSocketWrapperDisp;
import java.util.logging.Level;
import java.util.logging.Logger;
import java2slice.crcl.base.CRCLCommandInstanceTypeIce;
import java2slice.crcl.base.CRCLStatusTypeIce;
import static converters.fromice.FromIceConverters.fromIce;
import static converters.toice.ToIceConverters.toIce;
import crcl.base.GetStatusType;
import java.io.IOException;
import java.math.BigInteger;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class CRCLSocketWrapper extends _CRCLSocketWrapperDisp {

    private final CRCLSocket crclSocket;

    public CRCLSocketWrapper(CRCLSocket crlclSocket) {
        this.crclSocket = crlclSocket;
    }
    

    /**
     * Get the value of crclSocket
     *
     * @return the value of crclSocket
     */
    public CRCLSocket getCrclSocket() {
        return crclSocket;
    }

    @Override
    public void writeCommand(CRCLCommandInstanceTypeIce cmd, Current __current) {
        try {
            CRCLCommandInstanceType cmdToSend = fromIce(cmd);
            crclSocket.writeCommand(cmdToSend);
        } catch (CRCLException ex) {
            Logger.getLogger(CRCLSocketWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static final CRCLCommandInstanceType getStatusInstance;
    private static final GetStatusType getStatus;
    
    static {
        getStatus = new GetStatusType();
        getStatusInstance = new CRCLCommandInstanceType();
        getStatusInstance.setCRCLCommand(getStatus);
        getStatus.setCommandID(1);
    }
    
    @Override
    public CRCLStatusTypeIce readStatus(Current __current) {
        try {
            getStatus.setCommandID(getStatus.getCommandID() + 1);
            crclSocket.writeCommand(getStatusInstance);
            CRCLStatusType status = crclSocket.readStatus();
            CRCLStatusTypeIce iceStatus = toIce(status);
            return iceStatus;
        } catch (CRCLException ex) {
            Logger.getLogger(CRCLSocketWrapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void close() throws IOException {
         crclSocket.close();
    }
    
    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize(); 
    }

    
}
