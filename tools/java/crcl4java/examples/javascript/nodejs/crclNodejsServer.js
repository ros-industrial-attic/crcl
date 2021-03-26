
var java = require("java");
//java.classpath.push("crcl4java-utils-1.3-jar-with-dependencies.jar");
java.classpath.push(process.env.CRCL4JAVA_UTILS_JAR);


// By default for node-java, you have to put "Sync" after everything for the normal function 
// or pass a callback and use the ASync version. 
// These options reverse this so you can use the Sync version by default and add "ASync" 
// if you want to pass the callback.
java.asyncOptions = {
  asyncSuffix: "ASync",
  syncSuffix: ""
};

// Import the types from Java that we will use.
var CRCLStatusType = java.import("crcl.base.CRCLStatusType");
var PoseStatusType = java.import("crcl.base.PoseStatusType");
var PoseType = java.import("crcl.base.PoseType");
var CommandStatusType = java.import("crcl.base.CommandStatusType");
var CommandStateEnumType = java.import("crcl.base.CommandStateEnumType");
var CRCLPosemath = java.import("crcl.utils.CRCLPosemath");
var CRCLSocket = java.import("crcl.utils.CRCLSocket");
var GetStatusType = java.import('crcl.base.GetStatusType');
var InitCanonType = java.import("crcl.base.InitCanonType");
var MoveToType = java.import("crcl.base.MoveToType");
var CRCLCommandInstanceType = java.import('crcl.base.CRCLCommandInstanceType');
var BigInteger = java.import("java.math.BigInteger");

// ********************************
// ** Server variables
// ********************************
var net     = require('net');
var server  = net.createServer();
var clients = [];
var SERVER_PORT_NUM = CRCLSocket.DEFAULT_PORT;


// Create a status message to send back when requested.
var status = new CRCLStatusType();
var cmdStatus = new CommandStatusType();
var poseStatus = new PoseStatusType();
cmdStatus.setCommandID(1);
status.setCommandStatus(cmdStatus);


server.on('connection', function (socket) {
       console.log ('New client connected.');

       // Create an empty CRCLSocket for XML conversions only.
       var crclsocket = new CRCLSocket();

       clients.push(socket);

       // How many status request for the current command.
       var reqnumber = 1;
       
       // define which things were interested in
       socket.on('data', function(data) {
            
            // Get a list of CRCLCommandInstances from the current data.
            // Note that data may contain multiple XML messages or only 
            // part of one. If a partial message is recieved it will
            // be stored in crclsocket to previx the data next time.
            l = crclsocket.parseMultiCommandString(data.toString());
            var i = 0;
            
            
            for(i = 0; i < l.size(); i++) {
                instance = l.get(i);
                cmd = instance.getCRCLCommand();
                console.log("command class name="+cmd.getClass().getName());
                if(cmd.getClass().getName()=== "crcl.base.GetStatusType") {
                    
                    // We assume all requests take 3 cycles to complete,
                    // More realistically many commands can be completed in a 
                    // single cycle while some will take much longer.
                    if(reqnumber > 3) {
                        cmdStatus.setCommandState(CommandStateEnumType.CRCL_DONE);
                    }
                    cmdStatus.setStatusID(reqnumber);
                    
                    // Send the request. The second parameter asks the 
                    // system to validate the composed status against 
                    // against the schema. Setting it to false will 
                    // save some processing but errors won't fail fast.
                    socket.write(crclsocket.statusToString(status,true));                   
                    reqnumber++;
                } else {
                    reqnumber=1;
                    
                    // Always update the status with the command ID so
                    // the client can tell which command we are working 
                    // on.
                    cmdStatus.setCommandID(cmd.getCommandID());
                    cmdStatus.setCommandState(CommandStateEnumType.CRCL_WORKING);
                    switch(cmd.getClass().getName()) {
                        case "crcl.base.MoveToType":
                            
                            // Moves instantly go to their end.
                            // Of course real robots take a while to move,
                            // but we are trying to make a simple and convenient
                            // example.
                            poseStatus.setPose(cmd.getEndPosition());
                            status.setPoseStatus(poseStatus);
                            break;
                            
                       default:
                           break;
                    }
                }
            }
        });

       socket.on('error', function(error) {
         console.log ('Server error -- ', error.message);
       });

       socket.on('close', function() {
	 console.log ('connection closed.');
	 var client_index = clients.indexOf(socket);
	 clients.splice(client_index, 1);
       });
});

server.listen(SERVER_PORT_NUM);
      


