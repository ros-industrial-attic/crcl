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
package crcl.restful.proxy;

import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLStatusType;
import crcl.base.GetStatusType;
import crcl.utils.CRCLException;
import crcl.utils.CRCLSocket;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Objects;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class ProxyCommon {

    private ProxyCommon() {

    }

    private static final ProxyCommon theCommonProxy = new ProxyCommon();

    public static ProxyCommon getTheCommonProxy() {
        return theCommonProxy;
    }

    private CRCLSocket crclSocket;
    private CRCLStatusType status;
    private long last_status_recv_time = -1;
    private long last_status_request_time = -1;
    private long cmdId = 1;
    private static final int PORT = Integer.parseInt(System.getProperty("crcl.restful.proxy.port", "" + CRCLSocket.DEFAULT_PORT));
    private static final String HOST = System.getProperty("crcl.restful.proxy.host", "localhost");
    private CRCLCommandInstanceType cmdInstance = new CRCLCommandInstanceType();

    public synchronized CRCLCommandInstanceType getCmdInstance() {
        return cmdInstance;
    }

    public synchronized void setCmdInstance(CRCLCommandInstanceType newCmdInstance) throws CRCLException, IOException {
        try {
            if (Objects.equals(cmdId, newCmdInstance.getCRCLCommand().getCommandID())) {
                cmdId = cmdId + 1;
                newCmdInstance.getCRCLCommand().setCommandID(cmdId);
            }
            cmdId = newCmdInstance.getCRCLCommand().getCommandID();
            cmdInstance.setCRCLCommand(newCmdInstance.getCRCLCommand());
            cmdInstance.setProgramFile(newCmdInstance.getProgramFile());
            cmdInstance.setProgramIndex(newCmdInstance.getProgramIndex());
            cmdInstance.setProgramLength(newCmdInstance.getProgramLength());
            cmdInstance.setName(newCmdInstance.getName());
            if (null == crclSocket) {
                crclSocket = new CRCLSocket(HOST, PORT);
            }
            crclSocket.writeCommand(cmdInstance);
        } catch (Throwable throwable) {
            if (null != crclSocket) {
                crclSocket.close();
                crclSocket = null;
            }
            throw throwable;
        }
    }

    public synchronized CRCLStatusType getStatus() throws CRCLException, IOException {
        try {
            long cur_time = System.currentTimeMillis();
            if (null != status && cur_time - last_status_recv_time < 100) {
                return status;
            }
            if (null == crclSocket) {
                crclSocket = new CRCLSocket(HOST, PORT);
            }
            CRCLCommandInstanceType instance = new CRCLCommandInstanceType();
            GetStatusType getStatus = new GetStatusType();
            getStatus.setCommandID(cmdId);
            instance.setCRCLCommand(getStatus);
            crclSocket.writeCommand(instance);
            last_status_request_time = System.currentTimeMillis();
            status = crclSocket.readStatus();
            last_status_recv_time = System.currentTimeMillis();
            return status;
        } catch (Throwable throwable) {
            if (null != crclSocket) {
                crclSocket.close();
                crclSocket = null;
            }
            throw throwable;
        }
    }

    private static void info(String string) {
        java.util.logging.Logger.getLogger(ProxyCommon.class.getName()).info(string);
    }

    private static void warning(String string) {
        java.util.logging.Logger.getLogger(ProxyCommon.class.getName()).warning(string);
    }

    private static void logException(java.util.logging.Level level, String msg, Throwable throwable) {
        java.util.logging.Logger.getLogger(ProxyCommon.class.getName()).log(level, msg, throwable);
    }
}
