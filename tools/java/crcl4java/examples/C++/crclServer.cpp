
#include "crclj.h"
#include <iostream>

using namespace crclj;
using namespace crclj::crcl::base;
using namespace crclj::crcl::utils;
using namespace crclj::java::math;
using namespace crclj::java::util;
using namespace crclj::java::lang;

using namespace std;

static bool quit=false;


int main(int argc, const char **argv) {

    try {
        
        
        // Create a status message for sending to clients.
        CRCLStatusType status;
        CommandStatusType cmdStatus;
        cmdStatus.setCommandID(1);
        cmdStatus.setStatusID(1);
        status.setCommandStatus(cmdStatus);
        PoseStatusType poseStatus;

        // Create as Server Socket object bound to the default port.

        CRCLServerSocket serverSocket(CRCLSocket::getDEFAULT_PORT());

        // Enable queuing of events so we don't need a callback.
        serverSocket.setQueueEvents(true);

        
        // Start a background thread to listen and accept connections.
        serverSocket.start();
        cout << " C++ CRCL Server background thread started. " << endl;
        
        int requestCount = 1;

        // Process events until someone interrupts us.
        while (!quit) {

            // Wait for the next event.
            CRCLServerSocketEvent e = serverSocket.waitForEvent();

            // Get an object representing a connection back to the 
            // client responsible for this event.
            CRCLSocket crclSocket = e.getSource();

            // Check if this event has a Command instance attached.
            CRCLCommandInstanceType instance = e.getInstance();
            if (NULL != instance.jthis) {

                //Get the command from the instance.
                CRCLCommandType cmd = instance.getCRCLCommand();
                if (GetStatusType::instanceof(cmd)) {

                    // In this simple simulated example all commands are
                    // DONE after 3 get status requests. 
                    // In a more realistic system, some commands like move a long
                    // distance would take a long time and some commands that
                    // just change a setting will immediately be done.
                    requestCount++;
                    if (requestCount > 3) {
                        cmdStatus.setCommandState(CommandStateEnumType::getCRCL_DONE());
                    }
                    cmdStatus.setStatusID(requestCount);
                    crclSocket.writeStatus(status, true);
                } else {
                    requestCount = 1;
                    
                    // Set the CommandID so client can know which command we are
                    // working on.
                    cmdStatus.setCommandID(cmd.getCommandID());
                    cmdStatus.setCommandState(CommandStateEnumType::getCRCL_WORKING());
                    if (MoveToType::instanceof(cmd)) {

                        // Simulates a system where the goal endPosition is immediately
                        // reached.
                        poseStatus.setPose(MoveToType::cast(cmd).getEndPosition());
                        status.setPoseStatus(poseStatus);
                    } else if(MessageType::instanceof(cmd)) {
                        MessageType messageCmd = MessageType::cast(cmd);
                        jstring msgJString = messageCmd.getMessage();
                        jboolean isCopy = false;
                        const char *msgCString = GetStringUTFChars(msgJString,&isCopy);
                        cout << "message=" << msgCString <<endl;
                        ReleaseStringUTFChars(msgJString,msgCString);
                    }
                }
            }

            // Events could also have an exception attached that could be
            // handled here.
            Exception exception = e.getException();
            if (NULL != exception.jthis) {
                PrintObject("exception",exception);
            }
        }
        cout << " Closing serverSocket ... " << endl;
        // Close all connected sockets and interrupt() & join() any background threads
        serverSocket.close();
        
        cout << " End of C++ CRCL Server main() reached. " << endl;
    } catch (jthrowable t) {
        PrintJThrowable("Exception Thrown : ", t);
    }
}