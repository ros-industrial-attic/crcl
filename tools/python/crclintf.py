#!/usr/bin/python

import sys, socket, threading, time
import xml.etree.ElementTree as ET
from crcl import *

uri = "http://www.w3.org/2001/XMLSchema-instance"
xsi = "{" + uri + "}"

RobotPort = 12301
GripperPort = 12302

def except_info():
    exc_type, exc_value = sys.exc_info()[:2]
    return str(exc_type.__name__) + ": " + str(exc_value)

class CRCLDevice(object):

    def setName(self, n):
        self.name = n

    def setDebug(self, on):
        self.debug = on

    def setCommandID(self, n):
        self.crclStatus.CommandStatus.setCommandID(n)

    def setStatusID(self, n):
        self.crclStatus.CommandStatus.setStatusID(n)

    def setCommandState(self, s):
        self.crclStatus.CommandStatus.setCommandState(s)

    def setPose(self, p):
        if self.crclStatus.PoseStatus == None:
            self.crclStatus.PoseStatus = PoseStatusType(p)
        else:
            self.crclStatus.PoseStatus.setPose(p)

    def setFraction(self, f):
        self.fraction = f

    def __init__(self, _name = "CRCLDevice"):
        self.setName(_name)
        self.setDebug(False)
        self.setFraction(1)

        self.crclStatus = CRCLStatusType()
        self.socket = -1
        self.serverThread = None

    def printStatus(self):
        print self.crclStatus

    def __str__(self):
        self.printStatus()

    def handleInitCanonType(self, child): pass
    def handleEndCanonType(self, child): pass
    def handleSetTransSpeedRelativeType(self, child): pass
    def handleSetTransSpeedAbsoluteType(self, child): pass
    def handleMoveThroughToType(self, child): pass
    def handleMoveToType(self, child): pass
    def handleOpenToolChangerType(self, child): pass
    def handleCloseToolChangerType(self, child): pass
    def handleSetEndEffectorParametersType(self, child): pass
    def handleSetEndEffectorType(self, child): pass

    def reader(self, conn):
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
                print self.name, ": error parsing data :", str(data)
                continue

            for child in root:
                if child.tag == "CRCLCommand":
                    cmd = child.attrib.get(xsi+"type", None)
                    if cmd == "InitCanonType":
                        self.handleInitCanonType(child)
                    elif cmd == "EndCanonType":
                        self.handleEndCanonType(child)
                    elif cmd == "SetTransSpeedRelativeType":
                        self.handleSetTransSpeedRelativeType(child)
                    elif cmd == "SetTransSpeedAbsoluteType":
                        self.handleSetTransSpeedAbsoluteType(child)
                    elif cmd == "MoveThroughToType":
                        self.handleMoveThroughToType(child)
                    elif cmd == "MoveToType":
                        self.handleMoveToType(child)
                    elif cmd == "OpenToolChangerType":
                        self.handleOpenToolChangerType(child)
                    elif cmd == "CloseToolChangerType":
                        self.handleCloseToolChangerType(child)
                    elif cmd == "SetEndEffectorParametersType":
                        self.handleSetEndEffectorParametersType(child)
                    elif cmd == "SetEndEffectorType":
                        self.handleSetEndEffectorType(child)
                    else:
                        # unknown command
                        print self.name, ": unknown command :", cmd
                else:
                    # unknown tag
                    print self.name, ": unknown tag :", child.tag

        # recv failed, client disconnected
        if self.debug:  print self.name, ": reader done"
        conn.close()

    def writer(self, conn, period):
        count = 0
        # loop with a delay while a client is connected
        while True:
            count += 1
            self.setStatusID(count)
            tree = self.crclStatus.tree()

            # stringify it
            output = StringIO.StringIO()
            output.write(xmldec)
            tree.write(output)
            outstr = output.getvalue()
            output.close()
            # write it to the client
            try:
                conn.send(outstr)
                time.sleep(period)
            except:
                # we detect a client disconnect via a send() error
                if self.debug: print self.name, ":", except_info()
                break

        # send failed, client disconnected
        if self.debug: print self.name, ": writer done"

    def server(self, port, period):
        BACKLOG = 1
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.socket.bind(("", int(port)))
        self.socket.listen(BACKLOG)

        while True:
            if self.debug: print self.name, ": accepting connections on", port
            try: conn, addr = self.socket.accept()
            except: break
            if self.debug: print self.name, ": connected by", addr
            r = threading.Thread(target=self.reader, args=(conn,))
            r.daemon = True
            r.start()
            w = threading.Thread(target=self.writer, args=(conn,float(period)))
            w.daemon = True
            w.start()

    def startServer(self, port, period):
        self.serverThread = threading.Thread(target=self.server, args=(port, period))
        self.serverThread.daemon = True
        self.serverThread.start()
        
    def stopServer(self):
        try: self.serverThread.stop()
        except: pass
        try: self.socket.close()
        except: pass

