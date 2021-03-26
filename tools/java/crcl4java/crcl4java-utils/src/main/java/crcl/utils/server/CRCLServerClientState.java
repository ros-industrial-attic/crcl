/*
 * This software is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain.
 * 
 * This software is experimental. NIST assumes no responsibility whatsoever 
 * for its use by other parties, and makes no guarantees, expressed or 
 * implied, about its quality, reliability, or any other characteristic. 
 * We would appreciate acknowledgement if the software is used. 
 * This software can be redistributed and/or modified freely provided 
 * that any derivative works bear some notice that they are derived from it, 
 * and any modified versions bear some notice that they have been modified.
 * 
 *  See http://www.copyright.gov/title17/92chap1.html#105
 * 
 */
package crcl.utils.server;

import crcl.utils.CRCLSocket;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class CRCLServerClientState implements AutoCloseable {

    private final CRCLSocket cs;
    public int getStatusRequests = 0;
    public int cmdsRecieved = 0;
    public long lastCmdTime = 0;
    public long lastStatRequestTime = 0;
    public long getStatusCmdId = -999;
    public long cmdId = -999;
    public final CRCLStatusFilterSettings filterSettings;
    
    public CRCLServerClientState(CRCLSocket cs) {
        this.cs = cs;
        this.filterSettings = new CRCLStatusFilterSettings();
    }

    @Override
    public void close() {
        try {
            cs.close();
        } catch (IOException ex) {
            Logger.getLogger(CRCLServerClientState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CRCLSocket getCs() {
        return cs;
    }

    @Override
    public String toString() {
        return "CrclServerClientState{" + "cs=" + cs + ", getStatusRequests=" + getStatusRequests + ", cmdsRecieved=" + cmdsRecieved + ", lastCmdTime=" + lastCmdTime + ", lastStatRequestTime=" + lastStatRequestTime + ", getStatusCmdId=" + getStatusCmdId + ", cmdId=" + cmdId + '}';
    }
}
