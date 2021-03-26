#!/usr/bin/python

# CRCL Client Example using Java CRCL Library and JPype (http://jpype.sourceforge.net/)
# Run with:
# python crclclient.py

from jpype import *
import urllib
import os

#jarfilename="crcl4java-utils-1.3-jar-with-dependencies.jar"
#if not os.path.isfile(jarfilename):
#    remotejarurl = "https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.3/crcl4java-utils-1.3-jar-with-dependencies.jar"
#    print ("Downloading "+remotejarurl)
#    urllib.urlretrieve (remotejarurl,jarfilename )

jarfilename=os.getenv('CRCL4JAVA_UTILS_JAR')
javahome=os.getenv('JAVA_HOME', '/usr/lib/jvm/java-7-openjdk-amd64/')
jvmlib=os.getenv('JVM_LIB',javahome+"/jre/lib/amd64/server/libjvm.so")
startJVM(jvmlib,
    "-Djava.class.path="+jarfilename)
crcl = JPackage('crcl')
CRCLSocket = JClass('crcl.utils.CRCLSocket')
CRCLPosemath = JClass('crcl.utils.CRCLPosemath')
CommandStateEnumType = JClass('crcl.base.CommandStateEnumType')

print "Connect"
s = CRCLSocket(JString("localhost"), JInt(64444))
#s.setEXIEnabled(True);

instance = crcl.base.CRCLCommandInstanceType()

## Create and send InitCanon command
print "Send InitCanon"
init = crcl.base.InitCanonType()
init.setCommandID(7)
instance.setCRCLCommand(init)
s.writeCommand(instance)

### Create and send getStatus request.
print "Send GetStatus"
getStat = crcl.base.GetStatusType()
getStat.setCommandID(9)
instance.setCRCLCommand(getStat)
s.writeCommand(instance)

### Read status from server
stat = s.readStatus()
cmdStat = stat.getCommandStatus()
IDback = -1

while IDback != init.getCommandID() or cmdStat.getCommandState() != CommandStateEnumType.CRCL_DONE:

    ## Create and send getStatus request.
    print "Send GetStatus"
    getStat.setCommandID(getStat.getCommandID()+1)
    instance.setCRCLCommand(getStat)
    s.writeCommand(instance)

    ## Read status from server
    ## Print out the status details.
    stat = s.readStatus()
    cmdStat = stat.getCommandStatus()
    IDback = cmdStat.getCommandID()

    print "Status:"
    print("CommandID = " + str(IDback))
    print("State = " + cmdStat.getCommandState().toString())

## Create and send MoveTo command.
print "Send MoveTo"
moveTo = crcl.base.MoveToType()
moveTo.setCommandID(8)
pt = CRCLPosemath.point(248.5,2.5,0.1)
xaxis = CRCLPosemath.vector(1.0,0.0,0.0)
zaxis = CRCLPosemath.vector(0.0,0.0,1.0)
pose = CRCLPosemath.pose(pt,xaxis,zaxis)
moveTo.setEndPosition(pose)
moveTo.setMoveStraight(False)
instance.setCRCLCommand(moveTo)
s.writeCommand(instance)


### Create and send getStatus request.
print "Send GetStatus"
getStat = crcl.base.GetStatusType()
getStat.setCommandID(9)
instance.setCRCLCommand(getStat)
s.writeCommand(instance)

### Read status from server
stat = s.readStatus()
cmdStat = stat.getCommandStatus()
IDback = -1

while IDback != moveTo.getCommandID() or cmdStat.getCommandState() != CommandStateEnumType.CRCL_DONE:

    ## Create and send getStatus request.
    print "Send GetStatus"
    getStat.setCommandID(getStat.getCommandID()+1)
    instance.setCRCLCommand(getStat)
    s.writeCommand(instance)

    ## Read status from server
    ## Print out the status details.
    stat = s.readStatus()
    cmdStat = stat.getCommandStatus()
    IDback = cmdStat.getCommandID()

    print "Status:"
    print("CommandID = " + str(IDback))
    print("State = " + cmdStat.getCommandState().toString())
    pt = stat.getPoseStatus().getPose().getPoint()
    print("pose = " + str(pt.getX()) + "," + str(pt.getY()) + "," + str(pt.getZ()))
    jst = stat.getJointStatuses()
    l = jst.getJointStatus()
    print "Joints:" 
    for i in range(0,l.size()):
        js = l.get(i)
        print("Num="+str(js.getJointNumber())+" Pos="+js.getJointPosition().toString())

s.close();
shutdownJVM()

