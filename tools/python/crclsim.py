#!/usr/bin/python

import sys, time, getopt, ConfigParser
from crclintf import *

class CRCLSimulator(CRCLDevice):

    def __init__(self, **kwargs):
        super(CRCLSimulator, self).__init__(**kwargs)
        self.fraction = 1

    # use default CRCLDevice defs for these
    # handleInitCanonType
    # handleEndCanonType

    def handleSetTransSpeedRelativeType(self, child):
        t = child.findtext("CommandID")
        if t == None:
            return
        self.setCommandID(t)
        v = child.findtext("Fraction")
        if v == None:
            self.setCommandState(CommandStateType.ERROR)
            return
        self.setFraction(v)
        self.setCommandState(CommandStateType.DONE)

    '''
    def handleSetTransSpeedAbsoluteType(self, child):
        global DEBUG, CommandID, CommandState
        if DEBUG: print "handleSetTransSpeedRelativeType"

        CommandID = child.findtext("CommandID")
        CommandState = CommandStateType.DONE
        print CommandID, 
        print child.findtext("TransSpeed")

    def handleMoveThroughToType(self, child):
        global DEBUG, CommandID, CommandState, Pose
        if DEBUG: print "handleMoveThroughToType"

        CommandID = child.findtext("CommandID")
        print CommandID, 
        print child.findtext("NumPositions"), 
        print child.findtext("MoveStraight")
        for i in child.findall("Waypoint"):
            try:
                ii = i.find("Point")
                point = PointType(float(ii.findtext("X")), float(ii.findtext("Y")), float(ii.findtext("Z")))
                ii = i.find("XAxis")
                xaxis = VectorType(float(ii.findtext("I")), float(ii.findtext("J")), float(ii.findtext("K")))
                ii = i.find("ZAxis")
                zaxis = VectorType(float(ii.findtext("I")), float(ii.findtext("J")), float(ii.findtext("K")))
                Pose.Point = point
                Pose.XAxis = xaxis
                Pose.ZAxis = zaxis
                print Pose
                CommandState = CommandStateType.DONE
            except:
                CommandState = CommandStateType.ERROR
    '''

    def handleMoveToType(self, child):
        t = child.findtext("CommandID")
        if t == None:
            return
        self.setCommandID(t)
        try:
            i = child.find("EndPosition")
            ii = i.find("Point")
            point = PointType(float(ii.findtext("X")), float(ii.findtext("Y")), float(ii.findtext("Z")))
            ii = i.find("XAxis")
            xaxis = VectorType(float(ii.findtext("I")), float(ii.findtext("J")), float(ii.findtext("K")))
            ii = i.find("ZAxis")
            zaxis = VectorType(float(ii.findtext("I")), float(ii.findtext("J")), float(ii.findtext("K")))
            self.setPose(PoseType(point, xaxis, zaxis))
            self.setCommandState(CommandStateType.DONE)
        except:
            self.setCommandState(CommandStateType.ERROR)

    '''
    Add CRCL interface defs to load and unload grippers
    '''

    def handleOpenToolChangerType(self, child):
        t = child.findtext("CommandID")
        if t == None:
            return
        self.setCommandID(t)
        self.setCommandState(CommandStateType.DONE)
        if self.debug: print "removing tool", self.name

    def handleCloseToolChangerType(self, child):
        t = child.findtext("CommandID")
        if t == None:
            return
        self.setCommandID(t)
        name = child.findtext("Name")
        if name == None:
            self.setCommandState(CommandStateType.ERROR)
        else:
            self.setCommandState(CommandStateType.DONE)
            if self.debug: print "adding tool", name

    '''
    def handleSetEndEffectorParametersType(self, child):
        global DEBUG, CommandID, CommandState
        if DEBUG: print "handleSetEndEffectorParametersType"
        CommandID = child.findtext("CommandID")
        CommandState = CommandStateType.DONE
        print CommandID, "params:"
        for i in child.findall("ParameterSetting"):
            print i.findtext("ParameterName"), "=", i.findtext("ParameterValue")

    def handleSetEndEffectorType(self, child):
        global DEBUG, CommandID, CommandState
        global ThreeFingerGripperStatus
        if DEBUG: print "handleSetEndEffectorType"

        CommandID = child.findtext("CommandID")
        print CommandID,
        print child.findtext("SetEndEffector")
        try: 
            setting = child.findtext("Setting")
            ThreeFingerGripperStatus.setFingerPosition(float(setting), float(setting), float(setting))
            print ThreeFingerGripperStatus
            CommandState = CommandStateType.DONE
        except:
            CommandState = CommandStateType.ERROR
    '''

# --- Main ---

if __name__ == "__main__":

    INIFILE = ""
    NAME = "CRCLSimulator"
    PORT = 0
    PERIOD = 0
    IS_ROBOT = False
    IS_GRIPPER = False
    DEBUG = False

    try:
        opts, args = getopt.getopt(sys.argv[1:], "i:n:p:t:rgd", ["inifile=", "name=", "port=", "period=", "robot", "gripper", "debug"])
    except getopt.GetoptError, err:
        print NAME, ":", str(err)
        sys.exit(1)

    for o, a in opts:
        if o in ("-i", "--infile"):
            INIFILE = a
        elif o in ("-n", "--name"):
            NAME = a
        elif o in ("-p", "--port"):
            PORT = a
        elif o in ("-t", "--period"):
            PERIOD = a
        elif o in ("-r", "--robot"):
            IS_ROBOT = True
        elif o in ("-g", "--gripper"):
            IS_GRIPPER = True
        elif o in ("-d", "--debug"):
            DEBUG = True

    defdict = {
        "port" : "",
        "period" : "",
        }

    if INIFILE != "":
        try:
            with open(INIFILE, "rb") as f:
                config = ConfigParser.ConfigParser(defdict)
                config.read(INIFILE)
                if PORT == "":
                    PORT = config.get(NAME, "port")
                if PERIOD == "":
                    PERIOD = config.get(NAME, "period")
        except IOError as err:
            print NAME, ": open inifile :", str(err)
            sys.exit(1)
        except (ConfigParser.NoSectionError, ConfigParser.NoOptionError) as err:
            print NAME, ": read inifile :", str(err)
            sys.exit(1)

    if PORT == 0:
        PORT = 12301
        print NAME, ": using default port", PORT

    if PERIOD == 0:
        PERIOD = 1
        print NAME, ": using default period", PERIOD

    # if our type is not specified, be both a robot and gripper
    if (IS_ROBOT == False) and (IS_GRIPPER == False):
        IS_ROBOT == True
        IS_GRIPPER == True

    myDevice = CRCLSimulator()

    myDevice.setDebug(DEBUG)

    myDevice.server(PORT, PERIOD)

    sys.exit(0)
