/* 
 * This is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * 
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain. This software is experimental.
 * NIST assumes no responsibility whatsoever for its use by other
 * parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic. We would appreciate
 * acknowledgment if the software is used. This software can be
 * redistributed and/or modified freely provided that any derivative works
 * bear some notice that they are derived from it, and any modified
 * versions bear some notice that they have been modified.
 * 
 */
package crcl.utils;

import crcl.utils.server.CRCLServerClientState;
import crcl.utils.server.CRCLServerSocket;
import crcl.utils.server.CRCLServerSocketStateGenerator;
import crcl.utils.server.CRCLServerSocketEvent;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStateEnumType;
import crcl.base.CommandStatusType;
import crcl.base.GetStatusType;
import crcl.base.MoveToType;
import crcl.base.PoseStatusType;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Example Server using CRCLServerSocket and non-blocking with polling on the
 * read.
 *
 * @author Will Shackleford{@literal <william.shackleford@nist.gov> }
 */
@SuppressWarnings("nullness")
public class CRCLServerSocketPollingExample {

    public static class PollingExampleClientState extends CRCLServerClientState {

        public PollingExampleClientState(CRCLSocket cs) {
            super(cs);
        }
        int i;
    }
    
    private static final CRCLServerSocketStateGenerator<PollingExampleClientState> POLLING_EXAMPLE_STATE_GENERATOR
            = new CRCLServerSocketStateGenerator<PollingExampleClientState>() {
        @Override
        public PollingExampleClientState generate(CRCLSocket crclSocket) {
            return new PollingExampleClientState(crclSocket);
        }
    };
    
    public static void main(String[] args) throws IOException, CRCLException, InterruptedException, Exception {

        final CRCLStatusType status = new CRCLStatusType();
        final CommandStatusType cmdStatus = new CommandStatusType();
        cmdStatus.setCommandID(1);
        cmdStatus.setStatusID(1);
        status.setCommandStatus(cmdStatus);
        final PoseStatusType poseStatus = new PoseStatusType();

        try (final CRCLServerSocket<PollingExampleClientState> serverSocket = new CRCLServerSocket<>(POLLING_EXAMPLE_STATE_GENERATOR)) {
            serverSocket.setQueueEvents(true);
            serverSocket.start();
            int requestCount = 1;
            while (!Thread.currentThread().isInterrupted()) {
                List<CRCLServerSocketEvent<PollingExampleClientState>> l = serverSocket.checkForEvents();
                if (l.size() < 1) {
                    Thread.sleep(200);
                }
                for (CRCLServerSocketEvent<PollingExampleClientState> e : l) {
                    CRCLSocket crclSocket = e.getSource();
                    CRCLCommandInstanceType instance = e.getInstance();
                    PollingExampleClientState state = e.getState();
                    if (null != instance) {
                        CRCLCommandType cmd = instance.getCRCLCommand();
                        if (cmd instanceof GetStatusType) {
                            requestCount++;
                            if (requestCount > 3) {
                                cmdStatus.setCommandState(CommandStateEnumType.CRCL_DONE);
                            }
                            cmdStatus.setStatusID(requestCount);
                            crclSocket.writeStatus(status, true);
                        } else {
                            requestCount = 1;
                            cmdStatus.setCommandID(cmd.getCommandID());
                            cmdStatus.setCommandState(CommandStateEnumType.CRCL_WORKING);
                            if (cmd instanceof MoveToType) {
                                poseStatus.setPose(((MoveToType) cmd).getEndPosition());
                                status.setPoseStatus(poseStatus);
                            }
                        }
                    }
                    Exception exception = e.getException();
                    if (null != exception) {
                        Logger.getLogger(CRCLServerSocketPollingExample.class.getName()).log(Level.SEVERE, null, exception);
                    }
                }
            }
        }
    }
}
