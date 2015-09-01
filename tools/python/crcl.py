#!/usr/bin/python

import sys, StringIO, xml.etree.ElementTree as ET
import math

xmldec = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
uri = "http://www.w3.org/2001/XMLSchema-instance"
cmdloc = "CRCLCommandInstance.xsd"
progloc = "CRCLProgramInstance.xsd"
statloc = "CRCLStatus.xsd"
cmddict = {"xmlns:xsi" : uri, "xsi:noNamespaceSchemaLocation" : cmdloc}
progdict = {"xmlns:xsi" : uri, "xsi:noNamespaceSchemaLocation" : progloc}
statdict = {"xmlns:xsi" : uri, "xsi:noNamespaceSchemaLocation" : statloc}

# 
# To Do: add the inverse methods, filling in the class given some XML
# 

class DataThingType(object):

    def __init__(self, Name=None, **kwargs):
        self.Name = Name

    def set(self, Name):
        self.Name = Name
        return True

    def get(self):
        return self.Name

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        if self.Name != None: ET.SubElement(root, "Name").text = str(self.Name)
        return ET.ElementTree(root)

    def __str__(self):
        output = StringIO.StringIO()
        self.tree().write(output)
        outstr = xmldec + output.getvalue()
        output.close()
        return outstr

class PointType(DataThingType):

    def __init__(self, X, Y, Z, **kwargs):
        super(PointType, self).__init__(**kwargs)
        self.X = X
        self.Y = Y
        self.Z = Z

    def set(self, X, Y, Z):
        self.X = X
        self.Y = Y
        self.Z = Z
        return True

    def get(self):
        return self.X, self.Y, self.Z

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        super(PointType, self).tree(root)
        ET.SubElement(root, "X").text = str(self.X)
        ET.SubElement(root, "Y").text = str(self.Y)
        ET.SubElement(root, "Z").text = str(self.Z)
        return ET.ElementTree(root)

class VectorType(DataThingType):

    def __init__(self, I, J, K, **kwargs):
        super(VectorType, self).__init__(**kwargs)
        self.I = I
        self.J = J
        self.K = K

    def set(self, I, J, K):
        self.I = I
        self.J = J
        self.K = K
        return True

    def get(self):
        return self.I, self.J, self.K

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        super(VectorType, self).tree(root)
        ET.SubElement(root, "I").text = str(self.I)
        ET.SubElement(root, "J").text = str(self.J)
        ET.SubElement(root, "K").text = str(self.K)
        return ET.ElementTree(root)

class PoseType(DataThingType):

    def __init__(self, Point = PointType(0,0,0), XAxis = VectorType(1,0,0), ZAxis = VectorType(0,0,1), **kwargs):
        super(PoseType, self).__init__(**kwargs)
        self.Point = Point
        self.XAxis = XAxis
        self.ZAxis = ZAxis

    def set(self, Point, XAxis, ZAxis):
        self.Point = Point
        self.XAxis = XAxis
        self.ZAxis = ZAxis
        return True

    def get(self):
        return self.Point.get(), self.XAxis.get(), self.ZAxis,get()
    def tree(self, root=None):
        if root == None: 
            root = ET.Element(None)
        else:
            super(PoseType, self).tree(root)
        el = root
        pel = ET.SubElement(el, "Point")
        self.Point.tree(pel)
        xel = ET.SubElement(el, "XAxis")
        self.XAxis.tree(xel)
        zel = ET.SubElement(el, "ZAxis")
        self.ZAxis.tree(zel)
        return ET.ElementTree(root)

class TransSpeedType(DataThingType):

    def __init__(self, **kwargs):
        super(TransSpeedType, self).__init__(**kwargs)

    def wrap(self, root, cmd):
        return ET.SubElement(root, "TransSpeed", attrib={"xsi:type" : cmd})

class TransSpeedAbsoluteType(TransSpeedType):

    def __init__(self, Setting, **kwargs):
        super(TransSpeedAbsoluteType, self).__init__(**kwargs)
        self.Setting = Setting

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        super(TransSpeedAbsoluteType, self).tree(root)
        ET.SubElement(self.wrap(root, "TransSpeedAbsoluteType"), "Setting").text = str(self.Setting)
        return ET.ElementTree(root)

class TransSpeedRelativeType(TransSpeedType):

    def __init__(self, Fraction, **kwargs):
        super(TransSpeedRelativeType, self).__init__(**kwargs)
        self.Fraction = Fraction

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        super(TransSpeedRelativeType, self).tree(root)
        ET.SubElement(self.wrap(root, "TransSpeedRelativeType"), "Fraction").text = str(self.Fraction)
        return ET.ElementTree(root)

class TransAccelType(DataThingType):

    def __init__(self, **kwargs):
        super(TransAccelType, self).__init__(**kwargs)

    def wrap(self, root, cmd):
        return ET.SubElement(root, "TransAccel", attrib={"xsi:type" : cmd})

class TransAccelAbsoluteType(TransAccelType):

    def __init__(self, Setting, **kwargs):
        super(TransAccelAbsoluteType, self).__init__(**kwargs)
        self.Setting = Setting

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        super(TransAccelAbsoluteType, self).tree(root)
        ET.SubElement(self.wrap(root, "TransAccelAbsoluteType"), "Setting").text = str(self.Setting)
        return ET.ElementTree(root)

class TransAccelRelativeType(TransAccelType):

    def __init__(self, Fraction, **kwargs):
        super(TransAccelRelativeType, self).__init__(**kwargs)
        self.Fraction = Fraction

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        super(TransAccelRelativeType, self).tree(root)
        ET.SubElement(self.wrap(root, "TransAccelRelativeType"), "Fraction").text = str(self.Fraction)
        return ET.ElementTree(root)

class RotSpeedType(DataThingType):

    def __init__(self, **kwargs):
        super(RotSpeedType, self).__init__(**kwargs)

    def wrap(self, root, cmd):
        return ET.SubElement(root, "RotSpeed", attrib={"xsi:type" : cmd})

class RotSpeedAbsoluteType(RotSpeedType):

    def __init__(self, Setting, **kwargs):
        super(RotSpeedAbsoluteType, self).__init__(**kwargs)
        self.Setting = Setting

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        super(RotSpeedAbsoluteType, self).tree(root)
        ET.SubElement(self.wrap(root, "RotSpeedAbsoluteType"), "Setting").text = str(self.Setting)
        return ET.ElementTree(root)

class RotSpeedRelativeType(RotSpeedType):

    def __init__(self, Fraction, **kwargs):
        super(RotSpeedRelativeType, self).__init__(**kwargs)
        self.Fraction = Fraction

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        super(RotSpeedRelativeType, self).tree(root)
        ET.SubElement(self.wrap(root, "RotSpeedRelativeType"), "Fraction").text = str(self.Fraction)
        return ET.ElementTree(root)

class RotAccelType(DataThingType):

    def __init__(self, **kwargs):
        super(RotAccelType, self).__init__(**kwargs)

    def wrap(self, root, cmd):
        return ET.SubElement(root, "RotAccel", attrib={"xsi:type" : cmd})

class RotAccelAbsoluteType(RotAccelType):

    def __init__(self, Setting, **kwargs):
        super(RotAccelAbsoluteType, self).__init__(**kwargs)
        self.Setting = Setting

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        super(RotAccelAbsoluteType, self).tree(root)
        ET.SubElement(self.wrap(root, "RotAccelAbsoluteType"), "Setting").text = str(self.Setting)
        return ET.ElementTree(root)

class RotAccelRelativeType(RotAccelType):

    def __init__(self, Fraction, **kwargs):
        super(RotAccelRelativeType, self).__init__(**kwargs)
        self.Fraction = Fraction

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        super(RotAccelRelativeType, self).tree(root)
        ET.SubElement(self.wrap(root, "RotAccelRelativeType"), "Fraction").text = str(self.Fraction)
        return ET.ElementTree(root)

class PoseToleranceType(DataThingType):

    def __init__(self, XPointTolerance = None, YPointTolerance = None, ZPointTolerance = None, XAxisTolerance = None, ZAxisTolerance = None, **kwargs):
        super(PoseToleranceType, self).__init__(**kwargs)
        self.XPointTolerance = XPointTolerance
        self.YPointTolerance = YPointTolerance
        self.ZPointTolerance = ZPointTolerance
        self.XAxisTolerance = XAxisTolerance
        self.ZAxisTolerance = ZAxisTolerance

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        super(PoseToleranceType, self).tree(root)
        if self.XPointTolerance != None: ET.SubElement(root, "XPointTolerance").text = str(self.XPointTolerance)
        if self.YPointTolerance != None: ET.SubElement(root, "YPointTolerance").text = str(self.YPointTolerance)
        if self.ZPointTolerance != None: ET.SubElement(root, "ZPointTolerance").text = str(self.ZPointTolerance)
        if self.XAxisTolerance != None: ET.SubElement(root, "XAxisTolerance").text = str(self.XAxisTolerance)
        if self.ZAxisTolerance != None: ET.SubElement(root, "ZAxisTolerance").text = str(self.ZAxisTolerance)
        return ET.ElementTree(root)

class PoseAndSetType(PoseType):

    def __init__(self, Coordinated, TransSpeed = None, RotSpeed = None, TransAccel = None, RotAccel = None, Tolerance = None, **kwargs):
        super(PoseAndSetType, self).__init__(**kwargs)
        self.Coordinated = Coordinated
        self.TransSpeed = TransSpeed
        self.RotSpeed = RotSpeed
        self.TransAccel = TransAccel
        self.RotAccel = RotAccel
        self.Tolerance = Tolerance

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        super(PoseAndSetType, self).tree(root)
        if self.Coordinated: ET.SubElement(root, "Coordinated").text = "true"
        else: ET.SubElement(root, "Coordinated").text = "false"
        if self.TransSpeed != None: self.TransSpeed.tree(root)
        if self.RotSpeed != None: self.RotSpeed.tree(root)
        if self.TransAccel != None: self.TransAccel.tree(root)
        if self.RotAccel != None: self.RotAccel.tree(root)
        if self.Tolerance != None: self.Tolerance.tree(root)
        return ET.ElementTree(root)

# --- Commands ---

class CRCLCommandInstanceType(DataThingType):

    def __init__(self, CRCLCommand, **kwargs):
        super(CRCLCommandInstanceType, self).__init__(**kwargs)
        self.CRCLCommand = CRCLCommand

class CRCLCommandType(DataThingType):

    def __init__(self, CommandID, **kwargs):
        super(CRCLCommandType, self).__init__(**kwargs)
        self.CommandID = CommandID

    def tree(self, root = None):
        if root == None: root = ET.Element(None)
        else: super(CRCLCommandType, self).tree(root)
        ET.SubElement(root, "CommandID").text = str(self.CommandID)
        return ET.ElementTree(root)
        
class MiddleCommandType(CRCLCommandType):

    def __init__(self, CommandID, **kwargs): super(MiddleCommandType, self).__init__(CommandID, **kwargs)

def wrapIt(root, cmd):
    '''
    If a command doesn't have a parent tree, then it's a single
    command and it gets the top-level root instance CRCLCommandInstance
    '''
    if root == None:
        root = ET.Element("CRCLCommandInstance", attrib=cmddict)
        wrap = "CRCLCommand"
    else:
        wrap = "MiddleCommand"
    el = ET.SubElement(root, wrap, attrib={"xsi:type" : cmd})
    return root, el

class SetTransSpeedType(MiddleCommandType):

    def __init__(self, CommandID, TransSpeed, **kwargs):
        super(SetTransSpeedType, self).__init__(CommandID, **kwargs)
        self.TransSpeed = TransSpeed

    def tree(self, root=None):
        root, el = wrapIt(root, "SetTransSpeedType")
        super(SetTransSpeedType, self).tree(el)
        self.TransSpeed.tree(el)
        return ET.ElementTree(root)

class SetTransAccelType(MiddleCommandType):

    def __init__(self, CommandID, TransAccel, **kwargs):
        super(SetTransAccelType, self).__init__(CommandID, **kwargs)
        self.TransAccel = TransAccel

    def tree(self, root=None):
        root, el = wrapIt(root, "SetTransAccelType")
        super(SetTransAccelType, self).tree(el)
        self.TransAccel.tree(el)
        return ET.ElementTree(root)

class SetEndPoseToleranceType(MiddleCommandType):

    def __init__(self, CommandID, Tolerance, **kwargs):
        super(SetEndPoseToleranceType, self).__init__(CommandID, **kwargs)
        self.Tolerance = Tolerance

    def tree(self, root=None):
        root, el = wrapIt(root, "SetEndPoseToleranceType")
        super(SetEndPoseToleranceType, self).tree(el)
        tel = ET.SubElement(el, "Tolerance")
        self.Tolerance.tree(tel)
        return ET.ElementTree(root)

class OpenToolChangerType(MiddleCommandType):

    def __init__(self, CommandID, **kwargs):
        super(OpenToolChangerType, self).__init__(CommandID, **kwargs)

    def tree(self, root=None):
        root, el = wrapIt(root, "OpenToolChangerType")
        super(OpenToolChangerType, self).tree(el)
        return ET.ElementTree(root)

class CloseToolChangerType(MiddleCommandType):

    def __init__(self, CommandID, **kwargs):
        super(CloseToolChangerType, self).__init__(CommandID, **kwargs)

    def tree(self, root=None):
        root, el = wrapIt(root, "CloseToolChangerType")
        super(CloseToolChangerType, self).tree(el)
        return ET.ElementTree(root)

class MoveThroughToType(MiddleCommandType):

    def __init__(self, CommandID, MoveStraight, Waypoint, **kwargs):
        super(MoveThroughToType, self).__init__(CommandID, **kwargs)
        self.MoveStraight = MoveStraight
        self.Waypoint = Waypoint
        self.NumPositions = len(self.Waypoint)

    def tree(self, root=None):
        root, el = wrapIt(root, "MoveThroughToType")
        super(MoveThroughToType, self).tree(el)
        if self.MoveStraight: ET.SubElement(el, "MoveStraight").text = "true"
        else: ET.SubElement(el, "MoveStraight").text = "false"
        for wp in self.Waypoint:
            wpel = ET.SubElement(el, "Waypoint")
            # FIXME -- derived type PoseAndSetType speeds don't come through
            wp.tree(wpel)
            '''
            ptel = ET.SubElement(wpel, "Point")
            wp.Point.tree(ptel)
            xel = ET.SubElement(wpel, "XAxis")
            wp.XAxis.tree(xel)
            zel = ET.SubElement(wpel, "ZAxis")
            wp.ZAxis.tree(zel)
            '''
        ET.SubElement(el, "NumPositions").text = str(self.NumPositions)
        return ET.ElementTree(root)

    def add(self, wps):
        try:
            self.Waypoint.append(wps)
        except:
            return False
        self.NumPositions = len(self.Waypoint)
        return True

class MoveToType(MiddleCommandType):

    def __init__(self, CommandID, MoveStraight, EndPosition, **kwargs):
        super(MoveToType, self).__init__(CommandID, **kwargs)
        self.MoveStraight = MoveStraight
        self.EndPosition = EndPosition

    def tree(self, root=None):
        root, el = wrapIt(root, "MoveToType")
        super(MoveToType, self).tree(el)

        if self.MoveStraight: ET.SubElement(el, "MoveStraight").text = "true"
        else: ET.SubElement(el, "MoveStraight").text = "false"
        ep = self.EndPosition
        epel = ET.SubElement(el, "EndPosition")
        ep.tree(epel)
        return ET.ElementTree(root)

# --- speed control

class ParameterSettingType(DataThingType):

    def __init__(self, ParameterName, ParameterValue, **kwargs):
        super(ParameterSettingType, self).__init__(**kwargs)
        self.ParameterName = ParameterName
        self.ParameterValue = ParameterValue

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        super(ParameterSettingType, self).tree(root)
        el = ET.SubElement(root, "ParameterSetting")
        ET.SubElement(el, "ParameterName").text = str(self.ParameterName)
        ET.SubElement(el, "ParameterValue").text = str(self.ParameterValue)
        return ET.ElementTree(root)

class SetEndEffectorParametersType(MiddleCommandType):

    def __init__(self, CommandID, ParameterSetting, **kwargs):
        super(SetEndEffectorParametersType, self).__init__(CommandID, **kwargs)
        self.ParameterSetting = ParameterSetting

    def tree(self, root=None):
        root, el = wrapIt(root, "SetEndEffectorParametersType")
        super(SetEndEffectorParametersType, self).tree(el)
        try:
            for ps in self.ParameterSetting:
                ps.tree(el)
        except: 
            self.ParameterSetting.tree(el)
        return ET.ElementTree(root)

class InitCanonType(MiddleCommandType):

    def __init__(self, CommandID, **kwargs):
        super(InitCanonType, self).__init__(CommandID, **kwargs)

    def tree(self, root=None):
        if root == None:
            root, el = wrapIt(root, "InitCanonType")
        else:
            el = ET.SubElement(root, "InitCanon")
        super(InitCanonType, self).tree(el)
        return ET.ElementTree(root)

class EndCanonType(CRCLCommandType):

    def __init__(self, CommandID, **kwargs):
        super(EndCanonType, self).__init__(CommandID, **kwargs)

    def tree(self, root=None):
        if root == None:
            root, el = wrapIt(root, "EndCanonType")
        else:
            el = ET.SubElement(root, "EndCanon")
        super(EndCanonType, self).tree(el)
        return ET.ElementTree(root)

class SetEndEffectorType(CRCLCommandType):

    def __init__(self, CommandID, Setting, **kwargs):
        super(SetEndEffectorType, self).__init__(CommandID, **kwargs)
        self.Setting = Setting

    def tree(self, root=None):
        root, el = wrapIt(root, "SetEndEffectorType")
        super(SetEndEffectorType, self).tree(el)
        ET.SubElement(el, "Setting").text = str(self.Setting)
        return ET.ElementTree(root)

    def set(self, Setting):
        self.Setting = Setting
        return True

    def get(self):
        return self.Setting

class CRCLProgramType(DataThingType):

    def __init__(self, **kwargs):
        super(CRCLProgramType, self).__init__(**kwargs)
        self.MiddleCommand = [ ]

    def tree(self, root=None):
        if root == None: root = ET.Element("CRCLProgram", attrib=progdict)
        super(CRCLProgramType, self).tree(root)
        InitCanonType(1).tree(root)
        for cmd in self.MiddleCommand:
            cmd.tree(root)
        EndCanonType(0).tree(root)
        return ET.ElementTree(root)

    def add(self, MiddleCommand):
        self.MiddleCommand.append(MiddleCommand)
        return True

class CommandStateType(object):
    DONE = "Done"
    ERROR = "Error"
    WORKING = "Working"
    TIMEOUT = "Timeout"

def toCommandStateType(s):
    if s == CommandStateType.DONE: return s
    if s == CommandStateType.WORKING: return s
    if s == CommandStateType.TIMEOUT: return s
    return CommandStateType.ERROR

class CommandStatusType(DataThingType):

    def __init__(self, CommandID = 1, StatusID = 1, CommandState = CommandStateType.DONE, **kwargs):
        super(CommandStatusType, self).__init__(**kwargs)
        self.CommandID = CommandID
        self.StatusID = StatusID
        self.CommandState = CommandState

    def setCommandID(self, n):
        self.CommandID = n

    def setStatusID(self, n):
        self.StatusID = n

    def setCommandState(self, s):
        self.CommandState = s

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        else: super(CommandStatusType, self).tree(root)
        el = ET.SubElement(root, "CommandStatus")
        ET.SubElement(el, "CommandID").text = str(self.CommandID)
        ET.SubElement(el, "StatusID").text = str(self.StatusID)
        ET.SubElement(el, "CommandState").text = str(self.CommandState)
        return ET.ElementTree(root)

class PoseStatusType(DataThingType):

    def __init__(self, Pose, **kwargs):
        super(PoseStatusType, self).__init__(**kwargs)
        self.Pose = Pose

    def setPose(self, Pose):
        self.Pose = Pose

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        else: super(PoseStatusType, self).tree(root)
        el = ET.SubElement(root, "Pose")
        self.Pose.tree(el)
        return ET.ElementTree(root)

class JointStatusType(DataThingType):

    def __init__(self, JointNumber, JointPosition=None, JointTorqueOrForce=None, JointVelocity=None, **kwargs):
        super(JointStatusType, self).__init__(**kwargs)
        self.JointNumber = JointNumber
        self.JointPosition = JointPosition
        self.JointTorqueOrForce = JointTorqueOrForce
        self.JointVelocity = JointVelocity

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        else: super(JointStatusType, self).tree(root)
        el = ET.SubElement(root, "JointStatus")
        ET.SubElement(el, "JointNumber").text = str(self.JointNumber)
        if self.JointPosition != None: ET.SubElement(el, "JointPosition").text = str(self.JointPosition)
        if self.JointTorqueOrForce != None: ET.SubElement(el, "JointTorqueOrForce").text = str(self.JointTorqueOrForce)
        if self.JointVelocity != None: ET.SubElement(el, "JointVelocity").text = str(self.JointVelocity)

class JointStatusesType(DataThingType):

    def __init__(self, **kwargs):
        super(JointStatusesType, self).__init__(**kwargs)
        self.JointStatus = [ ]

    def add(self, wps):
        try:
            self.JointStatus.append(wps)
        except:
            return False
        return True

    def tree(self, root=None):
        if len(self.JointStatus) == 0: return ET.ElementTree(ET.Element(None))
        if root == None: root = ET.Element(None)
        else: super(JointStatusesType, self).tree(root)
        el = ET.SubElement(root, "JointStatuses")
        for js in self.JointStatus:
            js.tree(el)
        return ET.ElementTree(root)

class GripperStatusType(DataThingType):

    def __init__(self, GripperName, **kwargs):
        super(GripperStatusType, self).__init__(self, **kwargs)
        self.GripperName = GripperName

    def setName(self, name):
        try:
            self.GripperName = name
        except:
            self.GripperName = ""

    def getName(self):
        return self.GripperName

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        ET.SubElement(root, "GripperName").text = str(self.GripperName)

class ParallelGripperStatusType(GripperStatusType):

    def __init__(self, GripperName, Separation, **kwargs):
        super(ParallelGripperStatusType, self).__init__(GripperName, **kwargs)
        self.Separation = Separation

    def setSeparation(self, Separation):
        self.Separation = Separation

    def getSeparation(self):
        return self.Separation

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        el = ET.SubElement(root, "GripperStatus", attrib={"xsi:type" : "ParallelGripperStatusType"})
        super(ParallelGripperStatusType, self).tree(el)
        ET.SubElement(el, "Separation").text = str(self.Separation)
        return ET.ElementTree(root)

class ThreeFingerGripperStatusType(GripperStatusType):

    def setFingerPosition(self, Finger1Position, Finger2Position, Finger3Position):
        self.Finger1Position = Finger1Position
        self.Finger2Position = Finger2Position
        self.Finger3Position = Finger3Position

    def setFingerForce(self, Finger1Force, Finger2Force, Finger3Force):
        self.Finger1Force = Finger1Force
        self.Finger2Force = Finger2Force
        self.Finger3Force = Finger3Force

    def __init__(self, GripperName = "ThreeFingerGripper", **kwargs):
        super(ThreeFingerGripperStatusType, self).__init__(GripperName, **kwargs)
        self.setFingerPosition(0, 0, 0)
        self.setFingerForce(0, 0, 0)

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        el = ET.SubElement(root, "GripperStatus", attrib={"xsi:type" : "ThreeFingerGripperStatusType"})
        super(ThreeFingerGripperStatusType, self).tree(el)
        ET.SubElement(el, "Finger1Position").text = str(self.Finger1Position)
        ET.SubElement(el, "Finger2Position").text = str(self.Finger2Position)
        ET.SubElement(el, "Finger3Position").text = str(self.Finger3Position)
        ET.SubElement(el, "Finger1Force").text = str(self.Finger1Force)
        ET.SubElement(el, "Finger2Force").text = str(self.Finger2Force)
        ET.SubElement(el, "Finger3Force").text = str(self.Finger3Force)
        return ET.ElementTree(root
)
class VacuumGripperStatusType(GripperStatusType):

    def __init__(self, GripperName, IsPowered, **kwargs):
        super(VacuumGripperStatusType, self).__init__(GripperName, **kwargs)
        self.IsPowered = IsPowered

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        el = ET.SubElement(root, "GripperStatus", attrib={"xsi:type" : "VacuumGripperStatusType"})
        super(VacuumGripperStatusType, self).tree(el)
        if self.IsPowered: ET.SubElement(el, "IsPowered").text = "true"
        else: ET.SubElement(el, "IsPowered").text = "false"
        return ET.ElementTree(root)

class CRCLStatusType(DataThingType):

    def __init__(self, CommandStatus=CommandStatusType(), PoseStatus=None, JointStatuses=None, GripperStatus=None, **kwargs):
        super(CRCLStatusType, self).__init__(**kwargs)
        self.CommandStatus = CommandStatus
        self.PoseStatus = PoseStatus
        self.JointStatuses = JointStatuses
        self.GripperStatus = GripperStatus

    def setCommandStatus(self, CommandStatus):
        self.CommandStatus = CommandStatus

    def setPoseStatus(self, PoseStatus):
        self.PoseStatus = PoseStatus

    def setJointStatuses(self, JointStatuses):
        self.JointStatuses = JointStatuses

    def setGripperStatus(self, GripperStatus):
        self.GripperStatus = GripperStatus

    def tree(self, root=None):
        if root == None: root = ET.Element("CRCLStatus", attrib=statdict)
        super(CRCLStatusType, self).tree(root)
        self.CommandStatus.tree(root)
        if self.PoseStatus != None: self.PoseStatus.tree(root)
        if self.JointStatuses != None: self.JointStatuses.tree(root)
        if self.GripperStatus != None: self.GripperStatus.tree(root)
        return ET.ElementTree(root)

    def write(self):
        return str(self)

    def read(self, treeStr):
        tree = ET.parse(StringIO.StringIO(treeStr.rstrip(' \t\n\r\0')))
        root = tree.getroot()
        if root.tag == "CRCLStatus":
            for child in root:
                if child.tag == "CommandStatus":
                    t = child.findtext("CommandID")
                    if (t != None) and (t != ""): self.CommandStatus.CommandID = t
                    t = child.findtext("StatusID")
                    if (t != None) and (t != ""): self.CommandStatus.StatusID = t
                    t = child.findtext("CommandState")
                    if (t != None) and (t != ""): self.CommandStatus.CommandState = toCommandStateType(t)
                elif child.tag == "Pose":
                    for cc in child:
                        if cc.tag == "Point":
                            x = float(cc.findtext("X"))
                            y = float(cc.findtext("Y"))
                            z = float(cc.findtext("Z"))
                        if cc.tag == "XAxis":
                            xi = float(cc.findtext("I"))
                            xj = float(cc.findtext("J"))
                            xk = float(cc.findtext("K"))
                        if cc.tag == "ZAxis":
                            zi = float(cc.findtext("I"))
                            zj = float(cc.findtext("J"))
                            zk = float(cc.findtext("K"))
                    self.Pose = PoseType(PointType(x,y,z), VectorType(xi,xj,xk), VectorType(zi,zj,zk))
        return True

# --- Utility functions ---

def unit(Vector):
    try:
        i = float(Vector.I)
        j = float(Vector.J)
        k = float(Vector.K)
        maginv = 1 / math.sqrt(i*i + j*j + k*k)
        Vunit = VectorType(i*maginv, j*maginv, k*maginv)
        return Vunit
    except:
        raise ValueError
    
class MatrixType(DataThingType):
    def __init__(self, X, Y, Z, **kwargs):
        super(MatrixType, self).__init__(**kwargs)
        self.X = X
        self.Y = Y
        self.Z = Z

    def set(self, X, Y, Z):
        self.X = X
        self.Y = Y
        self.Z = Z
        return True

    def get(self):
        return self.X, self.Y, self.Z

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        super(MatrixType, self).tree(root)
        self.X.tree(ET.SubElement(root, "X"))  
        self.Y.tree(ET.SubElement(root, "Y"))  
        self.Z.tree(ET.SubElement(root, "Z"))  
        return ET.ElementTree(root)

class QuaternionType(DataThingType):
    def __init__(self, X, Y, Z, W, **kwargs):
        super(QuaternionType, self).__init__(**kwargs)
        self.X = X
        self.Y = Y
        self.Z = Z
        self.W = W

    def tree(self, root=None):
        if root == None: root = ET.Element(None)
        super(QuaternionType, self).tree(root)
        ET.SubElement(root, "X").text = str(self.X)
        ET.SubElement(root, "Y").text = str(self.Y)
        ET.SubElement(root, "Z").text = str(self.Z)
        ET.SubElement(root, "W").text = str(self.W)
        return ET.ElementTree(root)

def PointPointAdd(PA, PB):
    return PointType(PA.X + PB.X, PA.Y + PB.Y, PA.Z + PB.Z)

def VectorVectorCross(VA, VB):
    AI, AJ, AK = VA.get()
    BI, BJ, BK = VB.get()
    YI = (AJ*BK) - (AK*BJ)
    YJ = (AK*BI) - (AI*BK)
    YK = (AI*BJ) - (AJ*BI)
    return VectorType(YI, YJ, YK)

def VectorsToMatrix(VX, VZ):
    return MatrixType(VX, VectorVectorCross(VZ, VX), VZ)

def MatrixToVectors(M):
    return M.X, M.Z

'''
Matrix representation:

|  m.x.x   m.y.x   m.z.x  |
|  m.x.y   m.y.y   m.z.y  |
|  m.x.z   m.y.z   m.z.z  |

Quaternion (x, y, z, w) to matrix conversion:

m.x.x = 1 - 2 * (sq(q.y) + sq(q.z));
m.x.y = 2 * (q.x * q.y + q.z * q.w);
m.x.z = 2 * (q.z * q.x - q.y * q.w);

m.y.x = 2 * (q.x * q.y - q.z * q.w);
m.y.y = 1 - 2 * (sq(q.z) + sq(q.x));
m.y.z = 2 * (q.y * q.z + q.x * q.w);

m.z.x = 2 * (q.z * q.x + q.y * q.w);
m.z.y = 2 * (q.y * q.z - q.x * q.w);
m.z.z = 1 - 2 * (sq(q.x) + sq(q.y));
'''

def sq(x): return x*x

def QuaternionToMatrix(Q):
    return MatrixType(VectorType(1 - 2 * (sq(Q.Y) + sq(Q.Z)),
                                 2 * (Q.X * Q.Y + Q.Z * Q.W),
                                 2 * (Q.Z * Q.X - Q.Y * Q.W)),
                      VectorType(2 * (Q.X * Q.Y - Q.Z * Q.W),
                                 1 - 2 * (sq(Q.Z) + sq(Q.X)),
                                 2 * (Q.Y * Q.Z + Q.X * Q.W)),
                      VectorType(2 * (Q.Z * Q.X + Q.Y * Q.W),
                                 2 * (Q.Y * Q.Z - Q.X * Q.W),
                                 1 - 2 * (sq(Q.X) + sq(Q.Y))))

def MatrixToQuaternion(Min):
    try: M = MatrixType(unit(Min.X), unit(Min.Y), unit(Min.Z))
    except: raise ValueError

    discr = 1.0 + M.X.I + M.Y.J + M.Z.K
    if discr < 0: discr = 0
  
    Q = QuaternionType(0,0,0, 0.5 * math.sqrt(discr))

    if Q.W <= 0:
        Q.W = 0
        discr = 1.0 + M.X.I - M.Y.J - M.Z.K
        if discr < 0: discr = 0
        Q.X = math.sqrt(discr) * 0.5
        discr = 1.0 + M.Y.J - M.X.I - M.Z.K
        if discr < 0: discr = 0
        Q.Y = math.sqrt(discr) * 0.5
        discr = 1.0 + M.Z.K - M.Y.J - M.X.I
        if discr < 0: discr = 0
        Q.Z = math.sqrt(discr) * 0.5
        if (Q.X > Q.Y) and (Q.X > Q.Z):
            if M.X.J < 0.0: Q.Y = -Q.Y
            if M.X.K < 0.0: Q.Z = -Q.Z
        elif Q.Y > Q.Z:
            if M.X.J < 0:	Q.X = -Q.X
            if M.Y.K < 0: Q.Z = -Q.Z
        else:
            if M.X.K < 0: Q.X = -Q.X
            if M.Y.K < 0: Q.Y = -Q.Y
    else:
        a = 1 / (4 * Q.W)
        Q.X = (M.Y.K - M.Z.J) * a
        Q.Y = (M.Z.I - M.X.K) * a
        Q.Z = (M.X.J - M.Y.I) * a

    return Q

def MatrixPointMult(M1, P2):
    P = PointType(M1.X.I*P2.X + M1.Y.I*P2.Y + M1.Z.I*P2.Z,
                  M1.X.J*P2.X + M1.Y.J*P2.Y + M1.Z.J*P2.Z,
                  M1.X.K*P2.X + M1.Y.K*P2.Y + M1.Z.K*P2.Z)
    return P

def MatrixVectorMult(M1, V2):
    V = VectorType(M1.X.I*V2.I + M1.Y.I*V2.J + M1.Z.I*V2.K,
                   M1.X.J*V2.I + M1.Y.J*V2.J + M1.Z.J*V2.K,
                   M1.X.K*V2.I + M1.Y.K*V2.J + M1.Z.K*V2.K)
    return V

def MatrixMatrixMult(M1, M2):
    M = MatrixType(MatrixVectorMult(M1, M2.X),
                   MatrixVectorMult(M1, M2.Y),
                   MatrixVectorMult(M1, M2.Z))
    return M

def PosePoseMult(T1, T2):
    R1 = VectorsToMatrix(T1.XAxis, T1.ZAxis)
    R2 = VectorsToMatrix(T2.XAxis, T2.ZAxis)
    ROut = MatrixMatrixMult(R1, R2)
    XAxisOut, ZAxisOut = MatrixToVectors(ROut)

    P2R = MatrixPointMult(R1, T2.Point)
    PointOut = PointPointAdd(T1.Point, P2R)

    return PoseType(PointOut, XAxisOut, ZAxisOut)

