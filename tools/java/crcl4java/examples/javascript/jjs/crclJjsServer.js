
/* global Java, java, Packages */

// Import the types from Java that we will use.
var crcl = Packages.crcl;

var CRCLStatusType = crcl.base.CRCLStatusType;
var PoseStatusType = crcl.base.PoseStatusType;
var PoseType = crcl.base.PoseType;
var CommandStatusType = crcl.base.CommandStatusType;
var CommandStateEnumType = crcl.base.CommandStateEnumType;
var GetStatusType = crcl.base.GetStatusType;
var CRCLPosemath = crcl.utils.CRCLPosemath;
var CRCLSocket = crcl.utils.CRCLSocket;
var MoveToType = crcl.base.MoveToType;
var ServerSocket = java.net.ServerSocket;
var BigInteger = java.math.BigInteger;
var ServerSocket = java.net.ServerSocket;
var Executors = java.util.concurrent.Executors;
var Runnable = Java.type('java.lang.Runnable');


// Create a status message to send back when requested.
var status = new CRCLStatusType();
var cmdStatus = new CommandStatusType();
cmdStatus.setCommandID(BigInteger.ONE);
cmdStatus.setStatusID(BigInteger.ONE);
status.setCommandStatus(cmdStatus);
var poseStatus = new PoseStatusType();

// Bind to the default port and start accepting connections
var serverSocket = new ServerSocket(CRCLSocket.DEFAULT_PORT);
var execService = Executors.newCachedThreadPool();

execService.submit(new (Java.extend(Runnable, {
    run: function() {
        while (true) {
            var socket = serverSocket.accept();
            print("New client connnected.");
            
            execService.submit(new (Java.extend(Runnable, {
                run: function() {
                    // Wrap the vanilla socket wit a CRCLSocket
                    var crclSocket = new CRCLSocket(socket);
                    var instance = null;
                    var requestCount = 1;
                    
                    // Read commands from the socket.
                    while (null != (instance = crclSocket.readCommand(true))) {
                        var cmd = instance.getCRCLCommand();
                        print("command class name="+cmd.getClass().getName());
                        if(cmd instanceof GetStatusType) {
                            
                            //Populate and send back a status request.
                            requestCount++;
                            
                            // We assume all requests take 3 cycles to complete,
                            // More realistically many commands can be completed in a 
                            // single cycle while some will take much longer.
                            if(requestCount > 3) {
                                cmdStatus.setCommandState(CommandStateEnumType.CRCL_DONE);
                            }
                            cmdStatus.setStatusID(requestCount);
                            
                            // Send the request. The second parameter asks the 
                            // system to validate the composed status against 
                            // against the schema. Setting it to false will 
                            // save some processing but errors won't fail fast.
                            crclSocket.writeStatus(status, true);
                        } else {
                            
                            // Always update the status with the command ID so
                            // the client can tell which command we are working 
                            // on.
                            cmdStatus.setCommandID(cmd.getCommandID());
                            cmdStatus.setCommandState(CommandStateEnumType.CRCL_WORKING);
                            if(cmd instanceof MoveToType) {
                                
                                // Moves instantly go to their end.
                                // Of course real robots take a while to move,
                                // but we are trying to make a simple and convenient
                                // example.
                                poseStatus.setPose(cmd.getEndPosition());
                                status.setPoseStatus(poseStatus);
                            }
                        }
                    }
                }
            })));
        }
    }
})));



