#!/usr/bin/jython

# CRCL Client Example using Java CRCL Library and Jython (http://www.jython.org) 
# Run with:
# env CLASSPATH=crcl4java-utils-1.3-jar-with-dependencies.jar jython crclclient.py

import sys
import os

print "sys.path"
print sys.path
jarfilename=os.getenv('CRCL4JAVA_UTILS_JAR')

print "os.getenv('CRCL4JAVA_UTILS_JAR')"
print jarfilename

sys.path.append(jarfilename)

import java.lang.Boolean

from crcl.base import *
from crcl.utils import CRCLSocket
from crcl.utils import CRCLPosemath

print "Connect"
s = CRCLSocket("localhost", 64444)
#s.setEXIEnabled(True)
instance = CRCLCommandInstanceType()

## Create and send InitCanon command
print "Send InitCanon"
init = InitCanonType()
init.setCommandID(7)
instance.setCRCLCommand(init)
s.writeCommand(instance)



## Create and send getStatus request.
print "Send GetStatus"
getStat = GetStatusType()
getStat.setCommandID(9)
instance.setCRCLCommand(getStat)
s.writeCommand(instance)

## Read status from server
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

#    
## Create and send MoveTo command.
print "Send MoveTo"
moveTo = MoveToType()
moveTo.setCommandID(8)
pt = CRCLPosemath.point(248.5,2.5,0.1)
xaxis = CRCLPosemath.vector(1.0,0.0,0.0)
zaxis = CRCLPosemath.vector(0.0,0.0,1.0)
pose = CRCLPosemath.pose(pt,xaxis,zaxis)
moveTo.setEndPosition(pose)
moveTo.setMoveStraight(java.lang.Boolean.FALSE)
instance.setCRCLCommand(moveTo)
s.writeCommand(instance)


## Create and send getStatus request.
print "Send GetStatus"
getStat = GetStatusType()
getStat.setCommandID(9)
instance.setCRCLCommand(getStat)
s.writeCommand(instance)

## Read status from server
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
        print("Num="+str(js.getJointNumber())+" Pos="+str(js.getJointPosition()))

