/* global Java, Packages */

// jjs script
// Invoke with jjs -cp crcl4java-utils-1.3-jar-with-dependencies.jar clrcClient.js


// Import all the Java types we will need.
var crcl = Packages.crcl;

var CRCLSocket = crcl.utils.CRCLSocket;
var MoveToType = crcl.base.MoveToType;
var CRCLSocket = crcl.utils.CRCLSocket;
var CRCLPosemath = crcl.utils.CRCLPosemath;
var CRCLCommandInstanceType = crcl.base.CRCLCommandInstanceType;
var CommandStateEnumType = crcl.base.CommandStateEnumType;
var InitCanonType = crcl.base.InitCanonType;
var MoveToType = crcl.base.MoveToType;
var GetStatusType = crcl.base.GetStatusType;
var BigInteger = java.math.BigInteger;

// Convenience functions instead of static imports for creating Points,Vectors an Poses
function pose(pt, xaxis, zaxis) {
    return CRCLPosemath.pose(pt, xaxis, zaxis);
}

function point(x, y, z) {
    return CRCLPosemath.point(x, y, z);
}

function vector(i, j, k) {
    return CRCLPosemath.vector(i, j, k);
}

var socket = new CRCLSocket("localhost", CRCLSocket.DEFAULT_PORT);
// Create an instance to wrap all commands.
print("Opened " + socket);
var instance = new CRCLCommandInstanceType();

//        CRCLCommandInstanceType instance = new CRCLCommandInstanceType();
//        // Create and send init command
var init = new InitCanonType();
init.setCommandID(7);
instance.setCRCLCommand(init);
socket.writeCommand(instance);

// Create and send MoveTo command.
var moveTo = new MoveToType();
moveTo.setCommandID(8);
var pose = pose(point(0.65, 0.05, 0.15), vector(1, 0, 0), vector(0, 0, 1));
moveTo.setEndPosition(pose);
moveTo.setMoveStraight(false);
instance.setCRCLCommand(moveTo);
socket.writeCommand(instance);


var IDback = null;
var cmdStat = null;

do {

    // Create and send getStatus request.
    var getStat = new GetStatusType();
    getStat.setCommandID(9);
    instance.setCRCLCommand(getStat);
    socket.writeCommand(instance);
    
    // Read status from server
    var stat = socket.readStatus();

    // Print out the status details.
    var cmdStat = stat.getCommandStatus();
    var IDback = cmdStat.getCommandID();
    print("Status:");
    print("CommandID = " + IDback);
    print("State = " + cmdStat.getCommandState());
    pt = stat.getPoseStatus().getPose().getPoint();
    print("pt = " + pt.getX() + "," + pt.getY() + "," + pt.getZ());
    var jst = stat.getJointStatuses();
    if (jst != null) {
        var l = jst.getJointStatus();
        print("Joints:");
        for (index = 0; index < l.size(); index++) {
            var js = l.get(index);
            print("Num=" + js.getJointNumber() + " Pos=" + js.getJointPosition());
        }
    }
    // Repeat sending getstatus requets and printing status until the commandID is the moveTo commandID and the state is not WORKING
} while (!(IDback == moveTo.getCommandID()) || cmdStat.getCommandState().equals(CommandStateEnumType.CRCL_WORKING));

print("Closing socket");
socket.close();
