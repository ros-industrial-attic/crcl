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
import crcl.utils.server.CRCLServerSocketEvent;
import crcl.utils.server.CRCLServerSocketEventListener;
import crcl.utils.server.CRCLServerSocket;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStateEnumType;
import crcl.base.CommandStatusType;
import crcl.base.GetStatusType;
import crcl.base.MoveToType;
import crcl.base.PoseStatusType;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Example Server using CRCLServerSocket and a callback.
 *
 * @author Will Shackleford{@literal <william.shackleford@nist.gov> }
 */
@SuppressWarnings("nullness")
public class CRCLServerSocketCallbackExample {

    public static void main(String[] args) throws IOException, CRCLException, InterruptedException, Exception {

        final CRCLStatusType status = new CRCLStatusType();
        final CommandStatusType cmdStatus = new CommandStatusType();
        cmdStatus.setCommandID(1);
        cmdStatus.setStatusID(1);
        status.setCommandStatus(cmdStatus);
        final PoseStatusType poseStatus = new PoseStatusType();

        try (final CRCLServerSocket<CRCLServerClientState> serverSocket = CRCLServerSocket.newDefaultServer()) {
            serverSocket.setQueueEvents(false);
            serverSocket.addListener(new CRCLServerSocketEventListener<CRCLServerClientState>() {
                int requestCount = 1;

                @Override
                public void accept(CRCLServerSocketEvent<CRCLServerClientState> e) {
                    CRCLSocket crclSocket = e.getSource();
                    CRCLCommandInstanceType instance = e.getInstance();
                    if (null != instance) {
                        CRCLCommandType cmd = instance.getCRCLCommand();
                        if (cmd instanceof GetStatusType) {
                            try {
                                requestCount++;
                                if (requestCount > 3) {
                                    cmdStatus.setCommandState(CommandStateEnumType.CRCL_DONE);
                                }
                                cmdStatus.setStatusID(requestCount);
                                crclSocket.writeStatus(status, true);
                            } catch (CRCLException ex) {
                                Logger.getLogger(CRCLServerSocketCallbackExample.class.getName()).log(Level.SEVERE, null, ex);
                            }
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
                        Logger.getLogger(CRCLServerSocketCallbackExample.class.getName()).log(Level.SEVERE, null, exception);
                    }
                }
            });
            serverSocket.runServer();
        }
    }

}
