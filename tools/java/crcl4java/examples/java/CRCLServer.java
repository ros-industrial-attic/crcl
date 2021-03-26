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
import crcl.base.*;
import crcl.utils.CRCLSocket;
import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Will Shackleford{@literal <william.shackleford@nist.gov> }
 */
public class CRCLServer {

    public static void main(String[] args) throws IOException {

        final CRCLStatusType status = new CRCLStatusType();
        final CommandStatusType cmdStatus = new CommandStatusType();
        cmdStatus.setCommandID(1);
        cmdStatus.setStatusID(1);
        status.setCommandStatus(cmdStatus);
        final PoseStatusType poseStatus = new PoseStatusType();
        
        final ServerSocket serverSocket = new ServerSocket(CRCLSocket.DEFAULT_PORT);
        final ExecutorService execService = Executors.newCachedThreadPool();
        execService.submit(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        final Socket socket = serverSocket.accept();
                        execService.submit(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    CRCLSocket crclSocket = new CRCLSocket(socket);
                                    CRCLCommandInstanceType instance = null;
                                    int requestCount = 1;
                                    while (null != (instance = crclSocket.readCommand(true))) {
                                        CRCLCommandType cmd = instance.getCRCLCommand();
                                        if(cmd instanceof GetStatusType) {
                                            requestCount++;
                                            if(requestCount > 3) {
                                                cmdStatus.setCommandState(CommandStateEnumType.CRCL_DONE);
                                            }
                                            cmdStatus.setStatusID(requestCount);
                                            crclSocket.writeStatus(status, true);
                                        } else {
                                            cmdStatus.setCommandID(cmd.getCommandID());
                                            cmdStatus.setCommandState(CommandStateEnumType.CRCL_WORKING);
                                            if(cmd instanceof MoveToType) {
                                                poseStatus.setPose(((MoveToType)cmd).getEndPosition());
                                                status.setPoseStatus(poseStatus);
                                            }
                                        }
                                    }
                                } catch (Exception ex) {
                                    Logger.getLogger(CRCLServer.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                    } catch (Exception ex) {
                        Logger.getLogger(CRCLServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });
    }
}
