#!/usr/bin/python

import sys, time, getopt, socket, time, threading, StringIO, xml.etree.ElementTree as ET, ConfigParser
from crcl import *

xmldec = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
uri = "http://www.w3.org/2001/XMLSchema-instance"
xsi = "{" + uri + "}"
dict = {"xmlns:xsi" : uri}

def except_info():
    exc_type, exc_value = sys.exc_info()[:2]
    return str(exc_type.__name__) + ": " + str(exc_value)

INIFILE = ""
NAME = "robot_prim"
PORT = ""
PERIOD = 0.5
DO_ROBOT = False
DO_GRIPPER = False
DEBUG = False

# the global RCS header variables for the currently executing command
CommandID = 0
StatusID = 0
CommandState = "Ready"
Pose = PoseType()
ThreeFingerGripperStatus = ThreeFingerGripperStatusType("ThreeFingerGripper")
ParallelGripperStatus = ParallelGripperStatusType("ParallelGripper", 0)
VacuumGripperStatus = VacuumGripperStatusType("VacuumGripper", False)
InMotion = False

# --- Status writer ---

def writer(conn, period):
    global DEBUG, DO_ROBOT, DO_GRIPPER
    global CommandID, StatusID, CommandState
    global Pose
    global ThreeFingerGripperStatus

    # loop with a delay while a client is connected
    while True:

        StatusID += 1 
        cs = CRCLStatusType(CommandStatusType(CommandID, StatusID, CommandState), Pose)
        if DO_ROBOT: cs.setPose(Pose)
        if DO_GRIPPER: cs.setGripperStatus(ThreeFingerGripperStatus)

        tree = cs.tree()

        # stringify it
        output = StringIO.StringIO()
        output.write(xmldec)
        tree.write(output)
        outstr = output.getvalue()
        output.close()
        # FIXME -- add a debug level to inhibit this independently
        # if DEBUG: print outstr

        # write it to the client
        try:
            conn.send(outstr)
            time.sleep(period)
        except:
            # we detect a client disconnect via a send() error
            break

    # send failed, client disconnected
    if DEBUG: print NAME, ": writer done"

# --- Message handlers ---

def handleInitCanonType(child):
    global DEBUG, CommandID, CommandState
    if DEBUG: print "handleInitCanonType"
    CommandID = child.findtext("CommandID")
    CommandState = CommandStateType.DONE
    print CommandID, "init"

def handleEndCanonType(child):
    global DEBUG, CommandID, CommandState
    if DEBUG: print "handleEndCanonType"
    CommandID = child.findtext("CommandID")
    CommandState = CommandStateType.DONE
    print CommandID, "end"

def handleSetTransSpeedRelativeType(child):
    global DEBUG, CommandID, CommandState
    if DEBUG: print "handleSetTransSpeedRelativeType"
    
    CommandID = child.findtext("CommandID")
    CommandState = CommandStateType.DONE
    print CommandID, 
    print child.findtext("Fraction")

def handleSetTransSpeedAbsoluteType(child):
    global DEBUG, CommandID, CommandState
    if DEBUG: print "handleSetTransSpeedRelativeType"
    
    CommandID = child.findtext("CommandID")
    CommandState = CommandStateType.DONE
    print CommandID, 
    print child.findtext("TransSpeed")

def handleMoveThroughToType(child):
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

def handleMoveToType(child):
    global DEBUG, CommandID, CommandState, Pose
    if DEBUG: print "handleMoveToType"
    
    CommandID = child.findtext("CommandID")
    print CommandID, 
    print child.findtext("MoveStraight")
    try:
        i = child.find("EndPosition")

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

def handleOpenToolChangerType(child):
    global DEBUG, CommandID, CommandState
    if DEBUG: print "handleOpenToolChangerType"
    CommandID = child.findtext("CommandID")
    CommandState = CommandStateType.DONE
    print CommandID, "open"

def handleCloseToolChangerType(child):
    global DEBUG, CommandID, CommandState
    global ThreeFingerGripperStatus
    if DEBUG: print "handleCloseToolChangerType"
    CommandID = child.findtext("CommandID")
    CommandState = CommandStateType.DONE
    name = child.findtext("Name")
    ThreeFingerGripperStatus.setName(name)
    print CommandID, "close", name

def handleSetEndEffectorParametersType(child):
    global DEBUG, CommandID, CommandState
    if DEBUG: print "handleSetEndEffectorParametersType"
    CommandID = child.findtext("CommandID")
    CommandState = CommandStateType.DONE
    print CommandID, "params:"
    for i in child.findall("ParameterSetting"):
        print i.findtext("ParameterName"), "=", i.findtext("ParameterValue")

def handleSetEndEffectorType(child):
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

# -- Command reader ---

def reader(conn):

    # message size to read; make this > max XML message length
    size = 1024

    # loop forever while a client is connected, blocking on its messages
    while True:
        try:
            data = conn.recv(size)
        except:
            break
        if not data: break

        try:
            tree = ET.parse(StringIO.StringIO(data))
            root = tree.getroot()
        except:
            print NAME, ": error parsing data :", str(data)
        for child in root:
            if child.tag == "CRCLCommand":
                cmd = child.attrib.get(xsi+"type", None)
                if cmd == "InitCanonType":
                    handleInitCanonType(child)
                elif cmd == "EndCanonType":
                    handleEndCanonType(child)
                elif cmd == "SetTransSpeedRelativeType":
                    handleSetTransSpeedRelativeType(child)
                elif cmd == "SetTransSpeedAbsoluteType":
                    handleSetTransSpeedAbsoluteType(child)
                elif cmd == "MoveThroughToType":
                    handleMoveThroughToType(child)
                elif cmd == "MoveToType":
                    handleMoveToType(child)
                elif cmd == "OpenToolChangerType":
                    handleOpenToolChangerType(child)
                elif cmd == "CloseToolChangerType":
                    handleCloseToolChangerType(child)
                elif cmd == "SetEndEffectorParametersType":
                    handleSetEndEffectorParametersType(child)
                elif cmd == "SetEndEffectorType":
                    handleSetEndEffectorType(child)
                else:
                    # unknown command
                    print NAME, ": unknown command :", cmd
            else:
                # unknown tag
                print NAME, ": unknown tag :", child.tag

    # recv failed, client disconnected
    if DEBUG: print NAME, ": reader done"
    conn.close()

# --- Main ---

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
        DO_ROBOT = True
    elif o in ("-g", "--gripper"):
        DO_GRIPPER = True
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

if PORT == "":
    print NAME, ": no port provided"
    sys.exit(1)

if PERIOD == "":
    PERIOD = 1
    print NAME, ": using default period", PERIOD

# if our type is not specified, be both a robot and gripper
if (DO_ROBOT == False) and (DO_GRIPPER == False):
    DO_ROBOT == True
    DO_GRIPPER == True

BACKLOG = 1
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
s.bind(("", int(PORT)))
s.listen(BACKLOG)

while True:
    if DEBUG: print NAME, ": accepting connections on", str(PORT)
    try: conn, addr = s.accept()
    except: break
    if DEBUG: print NAME, ": connected by", addr
    r = threading.Thread(target=reader, args=(conn,))
    r.daemon = True
    r.start()
    t = threading.Thread(target=writer, args=(conn,float(PERIOD)))
    t.daemon = True
    t.start()

print NAME, ": done"

sys.exit(0)
