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

import crcl.utils.server.CRCLServerSocketEvent;
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
 * Example Server using CRCLServerSocket and blocking on the read.
 *
 * @author Will Shackleford{@literal <william.shackleford@nist.gov> }
 */
@SuppressWarnings("nullness")
public class CRCLServerSocketBlockingExample {

    public static void main(String[] args) throws IOException, CRCLException, InterruptedException, Exception {

        // Create a status message for sending to clients.
        final CRCLStatusType status = new CRCLStatusType();
        final CommandStatusType cmdStatus = new CommandStatusType();
        cmdStatus.setCommandID(1);
        cmdStatus.setStatusID(1);
        status.setCommandStatus(cmdStatus);
        final PoseStatusType poseStatus = new PoseStatusType();

        // Create as Server Socket object bound to the default port.
        try (final CRCLServerSocket serverSocket = CRCLServerSocket.newDefaultServer()) {

            // Enable queuing of events so we don't need a callback.
            serverSocket.setQueueEvents(true);

            // Start a background thread to listen and accept connections.
            serverSocket.start();

            int requestCount = 1;

            // Process events until someone interrupts us.
            while (!Thread.currentThread().isInterrupted()) {

                // Wait for the next event.
                CRCLServerSocketEvent e = serverSocket.waitForEvent();

                // Get an object representing a connection back to the 
                // client responsible for this event.
                CRCLSocket crclSocket = e.getSource();

                // Check if this event has a Command instance attached.
                CRCLCommandInstanceType instance = e.getInstance();
                if (null != instance) {

                    //Get the command from the instance.
                    CRCLCommandType cmd = instance.getCRCLCommand();
                    if (cmd instanceof GetStatusType) {

                        // In this simple simulated example all commands are
                        // DONE after 3 get status requests. 
                        // In a more realistic system, some commands like move a long
                        // distance would take a long time and some commands that
                        // just change a setting will immediately be done.
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

                            // Simulates a system where the goal endPosition is immediately
                            // reached.
                            poseStatus.setPose(((MoveToType) cmd).getEndPosition());
                            status.setPoseStatus(poseStatus);
                        }
                    }
                }

                // Events could also have an exception attached that could be
                // handled here.
                Exception exception = e.getException();
                if (null != exception) {
                    Logger.getLogger(CRCLServerSocketBlockingExample.class.getName()).log(Level.SEVERE, null, exception);
                }
            }
        }

    }
}
