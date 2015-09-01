Canonical Robot Command Language
================================

# Language Name

The language is called "Canonical Robot Command Language" (CRCL).


II. Purpose

The primary purpose of CRCL is to assist in executing robot plans by
providing generic commands that implement the functionality of typical
industrial robots without being specific either to the language of a plan
that is being executed or to the language used by a robot controller that
executes CRCL commands.

CRCL includes commands whose effects are independent of robot kinematics,
so that various robots can be made to do the same actions with the same set
of commands. Positions and directions in these commands are given in terms
of a fixed Cartesian coordinate system, which is the coordinate system of a
workstation in which the robot is located.

CRCL also includes commands whose effects are dependent on robot kinematics
so that various robots can be controlled using a single language.

A secondary purpose of CRCL is to transmit end effector commands as strings
in some other language so that no additional communications channel is
required to carry end effector commands.

Another secondary purpose of CRCL is to provide a set of command
status messages so that closed loop control may be implemented.

III. Normative Definition

The normative definition of CRCL is provided by three components:

   A. this document, including its normative references

   B. five XML Schema Language files:
      1. CRCLCommands.xsd
      2. CRCLCommandInstance.xsd
      3. CRCLProgramInstance.xsd
      4. CRCLStatus.xsd
      5. DataPrimitives.xsd

   C. four HTML data dictionaries
      1. CRCLCommands.html
      2. CRCLCommandInstance.html
      3. CRCLProgramInstance.html
      4. CRCLStatus.html

A CRCL command message must be an XML document (file or string) conforming
to the CRCLCommandInstance schema.

A CRCL status message must be an XML document (file or string) conforming
to the CRCLStatus schema.

A CRCL program must be an XML document (file or string) conforming
to the CRCLProgramInstance schema.

CRCL messages may be compressed by the sender and decompressed by the
receiver in a communications system used to transmit CRCL messages.

In addition to providing a detailed specification of the structure of
a conforming XML instance file, The XML schema files contain detailed
normative documentation. The HTML files duplicate the both the structure
specification and the documentation in an easy to navigate form.

IV. System Assumptions

CRCL is intended for use with devices typically described as
industrial robots and for other positionable automated devices such as
automated guided vehicles (AGVs). An AGV with a robotic arm attached
may be regarded as a single robot responding to a single stream of
CRCL commands or as two robots responding to two separate streams of
CRCL commands.

A system that deals with canonical robot commands will have at least
two components: a CRCL command sender and a CRCL command executor.

A. A CRCL command sender may be any of:

   1. a plan interpreter that reads a robot control plan file and
   interprets steps of the plan into one or more canonical robot
   commands,

   2. a user interface that gets input from a user (via keyboard,
   mouse, joystick, or any other user interface device) and selects CRCL
   commands to send based on the user input.

   3. a system that reads and sends commands in a CRCL program. 

B. A CRCL command executor may execute a CRCL command by either:

   1. interpreting the CRCL command into the language of a robot
   controller which then executes the command(s) in that language, or

   2. generating hardware control signals directly from the CRCL command.

V. Canonical Robot Command View of a Robot

A robot has one arm and can position and orient the end of the arm
anywhere in some work volume within some tolerance. At each point in
the work volume, the range of orientations that can be attained may be
limited.

When the controlled point is being controlled, the speed and
acceleration of the controlled point may be controlled.

When joints are being controlled, the speed and acceleration of each
joint may be controlled.

A robot can attach one end effector at a time to the end of the arm
from an end effector changing station and can detach the end effector
at the changing station. The changing station itself is passive.
Attaching an end effector is done by (1) moving the robot arm
(with no end effector attached) to an attachment position with respect
to an end effector and (2) giving a CloseToolChanger command. Detaching
an end effector is done by (1) moving the robot arm (with an end effector
attached) to a detachment position and (2) giving an OpenToolChanger
command. The attachment and detachment positions are normally at an end
effector changer in the end effector changing station.

All end effectors available to the robot are either attached to the
robot arm or stored in the end effector changing station.

In the current definition of CRCL, all end effectors are grippers. It is
expected that future revisions of CRCL may introduce other types of
end effector.

A gripper might have only two states (fully open or fully closed), or it
might be uniformly controllable to any fraction of openness between 0
(fully closed) and 1 (fully open). A gripper can hold an object if it is
fully or partially closed, but not if it is fully open.

Opening or closing any gripper mounted at the end of a robot arm is
exercised by using a CRCL command.

The robot cannot currently be commanded with CRCL to simultaneously move
and open or close the gripper.

There is always a controlled point. When no end effector is on the arm,
the controlled point is at the end of the arm. When an end effector
is mounted on the end of the arm, the controlled point is the tool
center point.

The robot can move the controlled point smoothly through a series of
poses from a start pose at which it is not moving to an end pose at
which it is not moving, provided that all poses are given before
motion starts. The acceleration and steady state speed of the
controlled point may be specified. The robot will do its best to
maintain the requested steady state speed but may reduce (but not
increase) speed or acceleration as necessary to allow for the dynamics
of arm motion.

A tolerance for the intermediate points of a smooth motion may be set.
The controlled point must pass the intermediate points within the
given tolerance (without coming back to a point after missing it by
more than the tolerance).

VI. General Information about CRCL

If the sender of CRCL commands sends a new command while the execution of
a previous command is in progress, the robot controller should stop
executing the previous command and start executing the new command.

The pose at the end of a command is called the current pose.

While a CRCL session is in progress (i.e. an InitCanon command has
been executed but an EndCanon has not) and an error state does not
exist, the robot should not move except as directed by a CRCL command.

Where command arguments are in terms of units, the units are as set by the
most recent SetXXXUnits command. If no SetXXXUnits command for a given unit
type has been executed since the last InitCanonType command was executed,
the following default units apply:
     length -- meter
     angle  -- radian
     force  -- newton
     torque -- newtonMeter


VII. CRCL Commands 

The CRCL commands are given here in alphabetical order. For ease of
reading, each command is informally described by giving the command
name followed by the informal names of command arguments (if any) in
parentheses. The type and meaning of each command argument is
described in the text. Argument types may be simple or complex.

Every CRCL command instance has a CommandID that is a positive integer.
In any CRCL command session each command must have a unique CommandID.
It is recommended that CommandIDs be assigned sequentially (1, 2, 3, ...).
For brevity, the CommandID is omitted from the argument lists below.

More detailed descriptions of the commands plus descriptions of the types
of command parameters are given in the XML schema file CRCLCommands.xsd.

ActuateJoints (joints)
----------------------
Meaning: Actuate the joints of the robot as specified.

The joints argument is a list of instances of ActuateJointType. Each
joint may appear at most once on the list. If a joint is not on the
list, its actuation should be as previously set.

Each joint on the joints list has:
 - a JointNumber that identifies the joint
 - a JointPosition that is the target position for the joint
 - JointDetails that provides either(1) the speed and acceleration to use
   in  getting to the position or (2) the force or torque and rate of
   change of force or torque to use in getting to the position.

CloseToolChanger ()
-------------------
Meaning: Close the tool changer on the robot so that it attaches to an
end effector.

The robot must be in an appropriate position with respect to the end
effector for the changer mechanism on the robot to attach to the end
effector.

ConfigureJointReports (resetAll, joints)
----------------------------------------
Meaning: Specify how the status of the robot joints should be reported.

This may be used only if CRCL status is being reported.

The value of ResetAll is either true or false. Joints is a list of of
instances of ConfigureJointReportType. Each joint may appear at most
once on the list.

Dwell (time)
------------
Meaning: Stay motionless for the given amount of time in seconds.

EndCanon ()
-----------
Meaning: Do whatever is necessary to stop executing canonical robot commands.

No specific action is required. The robot controller should not execute any
canonical robot command except InitCanon after executing EndCanon and
should signal an error if it is given one. This command will normally be
given when execution of a plan is complete but may also be given if an
error is detected, or for any other reason.

GetStatus ()
------------
Meaning: Report status immediately.

This may be used only if CRCL status is being reported.

InitCanon ()
------------
Meaning: Do whatever is necessary to get ready to move.

All units are set to the default units. This command will normally be given
when a plan interpreter opens a plan to be executed.

Message (message)
-----------------
Meaning: Display the given message on the operator console.

If there is no operator console, this command has no effect and should
be reported as executed without error.

MoveScrew (start, axisPoint, axialDistanceFree, axialDistanceScrew, turn)
-------------------------------------------------------------------------
Meaning: Move in a screwing motion, as for fastening screws, nuts, and
bolts, and possibly drilling.

The command is executed as follows.

First, if the optional start position exists, the controlled point and axis
are moved to the start position along any convenient trajectory.

Second, if the optional axialDistanceFree exists and is not zero, the
controlled point is moved along the axis by the given axialDistanceFree.

Third and finally, a screwing motion is made. If there is no axisPoint (or
if an axisPoint is given that is at the controlled point), the gripper
rotates around its axis through the angle given by turn at a constant rate
while simultaneously translating along the axis at a constant rate (the
currently set speed) through the axialDistanceScrew so that the rotation and
translation finish at the same time. If there is an axisPoint and it
differs from the location of the controlled point, the controlled point
simultaneously (1) rotates as above, (2) revolves around an axis through the
axisPoint parallel to the controlled axis, and (3) translates as above.
That makes a helical motion of the controlled point. The motion along the
helix is done at the currently set speed.

A positive value of axialDistanceFree or axialDistanceScrew means to move
away from the end effector. A negative value means to move toward the end
effector.

A positive value of turn means to rotate (and possibly revolve) in a
counterclockwise sense as viewed from the positive Z axis of the gripper
(the region extending away from the gripper).

The robot must reach the final position within the tolerance established
either by the start argument or by the most recently executed instance of
SetEndPoseTolerance. The speed and acceleration to use are also set either
by start or by previously executed CRCL commands.

In an instance file, the type of the start argument may be either
PoseType or PoseAndSetType, which is derived from PoseType.

MoveThroughTo (straight, waypoints, numberWaypoints)
----------------------------------------------------

Meaning: Move the controlled point along a trajectory passing near all but
the last of the given waypoints (poses), and stop at the last of the given
waypoints. The numberWaypoints gives the number of waypoints.

The robot must pass each waypoint within the tolerance established (1) by
the tolerance given for the pose in the waypoint, if there is a tolerance
in the waypoint, or if not (2) by the most recently executed instance of
SetIntermediatePoseToleranceType (or by SetEndPoseToleranceType for the
final waypoint).

The speed and acceleration to use are set either in the waypoint or by
previously executed CRCL commands.

If the value of straight is true, the controlled point must be moved in a
straight line between waypoints. If the value of straight is false, the
controlled point may be moved along any convenient trajectory between
waypoints. In either case, there are no restrictions on the values of XAxis
and ZAxis between waypoints.

MoveTo (straight, pose)
-----------------------
Meaning: Move the controlled point to the given pose, and stop there.

If the value of straight is true, the controlled point must be moved in a
straight line. If the value of straight is false, the controlled point may
be moved along any convenient trajectory.

The pose may include settings for speed, acceleration, and tolerance. Any
such settings apply only while the command is being executed, after which
the settings revert to what they were.

The robot must reach the pose within the tolerance established (1) by the
tolerance given for the pose, if there is a tolerance in the pose, or if
not (2) by the most recently executed instance of SetEndPoseTolerance. The
speed and acceleration to use are set either in the pose or by previously
executed CRCL commands.

OpenToolChanger ()
------------------

Meaning: Open the tool changer on the robot.

If an end effector is attached to the robot when this command is given,
executing the command releases the end effector.

This command is normally given only after an end effector attached to the
robot has been moved into an end effector changer.

RunProgram (program)
--------------------
Meaning: Run a program written in a non-CRCL language that the robot
controller understands.

The program is a string of text.
 
SetAngleUnits (unit)
---------------------
Meaning: Set angle units to the named unit.

The unit name must be either "degree" or "radian". After this command is
executed, all commands that use angle units (for orientation or orientation
tolerance) are in terms of the named angle unit. Existing values for
orientation are converted automatically to the equivalent value in new
angle units. The default angle unit is "radian".

SetEndEffectorParameters (settings)
-----------------------------------
Meaning: Set end effector parameters.

SetEndEffectorParameters is for setting parameters of end effectors that
have parameters. The meaning of the parameter settings is not part of CRCL.
It is expected that this command will be used only to send parameter values
that can be used by the end effector currently in use.

SetEndEffector (setting)
------------------------
Meaning: Set the effectivity of the end effector.

The setting is a number between zero and one.

If an end effector has multiple control modes, the control mode must be set
using a SetEndEffectorParameters command, so that the meaning of
SetEndEffector commands is unambiguous.

For end effectors that have a continuously variable setting, the setting
means a fraction of maximum openness, force, torque, power, etc.

For end effectors that have only two choices (powered or unpowered, open or
closed, on or off), a positive setting value means powered, open, or on,
while a zero setting value means unpowered, closed, or off.

SetEndPoseTolerance (tolerance)
-------------------------------
Meaning: Set the tolerance for the position and orientation of the end of
the arm (whenever there is no gripper there) or of the gripper (whenever a
gripper is on the end of the arm) to the given value.

The tolerance argument specifies both position tolerance in length units
and orientation tolerance in angle units.

SetForceUnits (unit)
--------------------
Meaning: Set force units to the named unit.

The unit name must be one of "newton", "pound", or "ounce". After this
command is executed, all commands that use force units are in terms of the
named force unit. The default force unit is "newton".

SetIntermediatePoseTolerance (tolerance)
----------------------------------------
Meaning: Set the tolerance for smooth motion near intermediate points.

The tolerance argument specifies both position tolerance in length units
and orientation tolerance in angle units.


SetLengthUnits (unit)
---------------------
Meaning: Set length units to the named unit.

The unit name must be one of "inch", "mm" or "meter". After this command is
executed, all commands that use length units (for location, tolerance,
speed, and acceleration) are in terms of the named length unit. Existing
values for speed, position, acceleration, etc. are converted automatically
to the equivalent value in new length units. The default length unit is
"meter".

SetMotionCoordination (coordinated)
-----------------------------------
Meaning: Either require rotational motion and translational motion to be
coordinated or allow them to be uncoordinated.

The coordinated parameter is a boolean value. True means require
coordinated motion. False means allow uncoordinated motion.

Motion is coordinated when rotational motion and translational motion must
finish simultaneously in motion commands (including each segment in a
multiple segment motion command), except as possibly temporarily overridden
in the the motion command.

SetRobotParameters (settings)
-----------------------------
Meaning: Set one or more robot parameters as specified.

Each specified parameter has a name that is a string and a value that
is a string. The meaning of the parameter is not known to CRCL, but
the robot controller knows what it means.

This command is not to be used to set any parameter that can be set
using a CRCL command.

SetRotAccel (setting)
---------------------
Meaning: Set rotational acceleration to the given setting in current angle
units per second per second.

The value may be either an absolute value or a fraction of the maximum
rotational acceleration.

SetRotSpeed (setting)
---------------------
Meaning: Set rotational speed to the given setting in current angle units
per second.

The value may be either an absolute value or a fraction of the maximum
rotational speed.

SetTorqueUnits (unit)
---------------------
Meaning: Set torque units to the named unit.

The unit name must be one of "newtonMeter", or "footPound". This tells the
robot that all further commands giving torque values will implicitly use
the named unit. The default torque unit is "newtonMeter".

SetTransAccel (setting)
-----------------------
Meaning: Set translational acceleration to the given setting in current length
units per second per second.

The value may be either an absolute value or a fraction of the maximum
translational acceleration.

SetTransSpeed (setting)
-----------------------
Meaning: Set translational speed to the given setting in current length
units per second.

The value may be either an absolute value or a fraction of the maximum
translational speed.

StopMotion (condition)
----------------------
Meaning: Stop the robot motion according to the given condition, Immediate,
Fast, or Normal.

Immediate means the robot's drives are deactivated immediately and the
brakes are applied. This may result in the controlled point being off the
commanded path when the robot stops.

Fast means the robot and any external axes are brought to a fast,
controlled stop. The drives are deactivated after one second, and the
brakes are applied. The controlled point must be kept on the on the
commanded path as the robot stops.

Normal means the robot and any external drives are stopped using a normal
braking ramp. The drives are not deactivated, and the brakes are not
applied. The controlled point must be kept on the on the commanded path as
the robot stops.


VIII. CRCL Status Messages

There is only one CRCLStatus message type, but it has four components:
  CommandStatus
  JointStatuses (optional)
  Pose (optional)
  GripperStatus (optional).

GripperStatus should not be reported when there is no gripper and should be
reported when there is a gripper.

The coordinate system in which the Pose is reported is always workstation
coordinates.

If CRCL status is being reported on separate channels for both a robot and
a gripper, the status reported on the robot channel should include a Pose,
while the status reported on the gripper channel should not include a Pose.

The units in status messages are as set by the most recent SetXXXUnits
command. If no SetXXXUnits command for a given unit type has been executed
since the last InitCanonType command was executed, the same default units
apply as for commands, namely:
   length -- meter
   angle  -- radian
   force  -- newton
   torque -- newtonMeter

Command Status
--------------

The CommandStatus relates the execution status of the currently executing
command (or the most recently executed command, if there is no current
command).

The CommandID echoes the command id from the received command to which the
status message applies.

The StatusID is an ID associated with this particular status message.

The combination of StatusID and CommandID must be unique within a session.


JointStatuses
-------------

Status for any number of joints may be reported, as specified by the most
recent ConfigureJointReports command. The report for each joint contains
a joint number, an optional joint position, an optional joint force or
torque, and an optional joint velocity.

It is assumed that the system sending canonical commands and the system
executing them both know the kinematics of the robot and have the same
numbering system for the joints, starting with 1. The two systems also have
the same understanding of where the zero point is and which direction is
positive for each joint.

Pose
----

This reports the pose of the gripper if there is one, or the pose of the
last robot joint, otherwise.

Gripper Status
--------------

The gripper status information varies according to the type of gripper.

ParallelGripperStatusType gives gripper status for a parallel jaw gripper.
The Separation element gives the distance between the jaws in length units.

ThreeFingerGripperStatusType gives gripper status for a three finger
gripper by providing an optional finger position and an optional finger
force for each of the three fingers. The fingers are assumed to be
non-articulated. Finger position is 0.0 at fully closed and 1.0 at fully
open and linear in either angle or distance for rotating fingers or sliding
fingers, respectively. All elements are optional, but typically all three
positions will be used if any one of them is used, and similarly for the
three forces.

VacuumGripperStatusType gives gripper status for a vacuum gripper. The
IsPowered element is true if a vacuum is being applied and is false if not.


IX. Normative References

A. Extensible Markup Language (XML) 1.0 (Fifth Edition)
W3C Recommendation 26 November 2008
http://www.w3.org/TR/REC-xml

B. XML Schema Part 1: Structures Second Edition
W3C Recommendation 28 October 2004
http://www.w3.org/TR/xmlschema-1

C. XML Schema Part 2: Datatypes Second Edition
W3C Recommendation 28 October 2004
http://www.w3.org/TR/xmlschema-2
