console.log("");
console.log("Staring clrclNodejsClient.js");
console.log("process.env.CRCL4JAVA_UTILS_JAR = " + process.env.CRCL4JAVA_UTILS_JAR);
console.log("");


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

var CRCLStatusType = java.import("crcl.base.CRCLStatusType");
var PoseStatusType = java.import("crcl.base.PoseStatusType");
var PoseType = java.import("crcl.base.PoseType");
var CommandStatusType = java.import("crcl.base.CommandStatusType");
var CommandStateEnumType = java.import("crcl.base.CommandStateEnumType");
var GetStatusType = java.import('crcl.base.GetStatusType');
var InitCanonType = java.import("crcl.base.InitCanonType");
var MoveToType = java.import("crcl.base.MoveToType");
var CRCLCommandInstanceType = java.import('crcl.base.CRCLCommandInstanceType');
var CRCLPosemath = java.import("crcl.utils.CRCLPosemath");
var CRCLSocket = java.import("crcl.utils.CRCLSocket");
var BigInteger = java.import("java.math.BigInteger");


function pose(pt,xAxis,zAxis) {
    return CRCLPosemath.pose(pt,xAxis,zAxis);
}

function point(x,y,z) {
    return CRCLPosemath.point(x,y,z);
}

function vector(i,j,k) {
    return CRCLPosemath.vector(i,j,k);
}

// Create a socket connected to the default port on localhost
var socket = new CRCLSocket("localhost",CRCLSocket.DEFAULT_PORT);
      
// Create an instance to wrap all commands.
console.log("s=" + socket);
var instance = new CRCLCommandInstanceType();
console.log("instance=" + instance);

// Create and send init command
var init = new InitCanonType();
init.setCommandID(7);
instance.setCRCLCommand(init);
socket.writeCommand(instance);

// Create and send MoveTo command.
var moveTo = new MoveToType();
moveTo.setCommandID(8);
var pose = pose(point(0.65,0.05,0.15),vector(1,0,0),vector(0,0,1));
moveTo.setEndPosition(pose);
moveTo.setMoveStraight(false);
instance.setCRCLCommand(moveTo);
socket.writeCommand(instance);
console.log("moveTo="+moveTo);


var IDback = null;
var cmdStat = null;

do {
    
    // Create and send getStatus request.
    var getStat = java.newInstanceSync("crcl.base.GetStatusType");
    getStat.setCommandID(9);
    instance.setCRCLCommand(getStat);
    socket.writeCommand(instance);

    // Read status from server
    var stat = socket.readStatus();

    // Print out the status details.
    cmdStat = stat.getCommandStatus();
    IDback = cmdStat.getCommandID();
    console.log("Status:");
    console.log("CommandID = " + IDback);
    console.log("State = " + cmdStat.getCommandState());
    pt = stat.getPoseStatus().getPose().getPoint();
    console.log("pt = " + pt.getX() + "," + pt.getY() + "," + pt.getZ());
    var jst = stat.getJointStatuses();
    if(jst !== null) {
        var l = jst.getJointStatus();
        console.log("Joints:");
        for (index=0; index<l.size(); index++) {
            var js = l.get(index);
            console.log("Num=" + js.getJointNumber() + " Pos=" + js.getJointPosition());
        }
    }
    // Keep sending requests and getting and printing status until the moveTo is completed.
} while(((IDback - moveTo.getCommandID()) !== 0) || (cmdStat.getCommandState().equals(CommandStateEnumType.CRCL_WORKING)));

console.log("Closing socket");
socket.close();
