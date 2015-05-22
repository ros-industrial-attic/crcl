#!/usr/bin/python

import sys
from time import *
from crcl import *

m = DataThingType()
print m

m = DataThingType("Name 1")
print m

m = DataThingType(Name="Name 2")
print m

# ----

try: m = PointType()
except: print "ok on error"

m = PointType(1, 2, 3)
print m

m = PointType(1, 2, 3, Name="Point 1")
print m

if m.set(3, 4, 5): print m
else: print "set failed"

x, y, z = m.get()
print m.Name, "=", x, y, z

# ---

try: m = VectorType()
except: print "ok on error"

m = VectorType(1, 2, 3)
print m

m = VectorType(1, 2, 3, Name="Vector 1")
print m

if m.set(3, 4, 5): print m
else: print "set failed"

i, j, k = m.get()
print m.Name, "=", i, j, k

# ---

m = PhysicalLocationType()
print m

m = PhysicalLocationType(time())
print m

m = PhysicalLocationType(Name="Name 2")
print m

m = PhysicalLocationType(time(), Name="Name 3")
print m

# ---

m = PoseLocationType()
print m

m = PoseLocationType(PointType(11,12,13), VectorType(0,1,0,Name="Ux"), ZAxis=VectorType(1,0,0))
print m

m = PoseLocationType(PointType(11,12,13), VectorType(1,0,0), VectorType(0,0,1), 0.2, 0.8, Name="Name 4", TimeStamp=time())
print m

# ---

point_a = PointType(0.1, 0.2, 0.3, Name="point_a")
ux = VectorType(1,0,0)
uz = VectorType(0,0,1)
m = PoseOnlyLocationType(point_a, ux, uz, Name="POL 1", TimeStamp=time())
print m

# ---

m = CRCLCommandType(101)
print m

try: m = MiddleCommandType()
except: print "ok on error"

m = MiddleCommandType(102)
print m

m = OpenToolChangerType(103)
print m

m = CloseToolChangerType(104, Name="gripper_gear")
print m

ppos = ParameterSettingType("Pos", 10)
pvel = ParameterSettingType("Vel", -3)
print ppos, pvel

m = SetEndEffectorParametersType(117, ppos)
print m

m = SetEndEffectorParametersType(117, [ppos, pvel])
print m

point1 = PointType(1, 2, 3, Name="Point1")
point2 = PointType(2, 4, 6, Name="Point2")
point3 = PointType(0, 0, 0)
xaxis = VectorType(1, 0, 0, Name="ux")
zaxis = VectorType(0, 0, 1)

m = MoveToType(15, False, PoseOnlyLocationType(point1, xaxis, zaxis), Name="EP")
print m

m = MoveThroughToType(17, True, [PoseOnlyLocationType(point1, xaxis, zaxis), PoseOnlyLocationType(point1, xaxis, zaxis)], Name="WP1")
print m

point2.set(4, 5, 6)
point3.set(7, 8, 9)
m.add(PoseOnlyLocationType(point3, xaxis, zaxis))
print m

cs = CommandStatusType(201, 202, CommandStateType.READY)
print cs

ps = PoseOnlyLocationType(Name="PoseStatus")
print ps

m = CRCLStatusType(cs, ps)
print m

jses = JointStatusesType()
print jses
jses.add(JointStatusType(1, 1.1, 1.2))
jses.add(JointStatusType(2, JointVelocity=7.8))
print jses

m = CRCLStatusType(cs, ps, jses)
print m

pg = ParallelGripperStatusType("PGrip", 0.44)
print pg

m = CRCLStatusType(cs, ps, pg)
print m

vg = VacuumGripperStatusType("VGrip", True)
print vg

m = CRCLStatusType(cs, ps, vg)
print m

m = CRCLStatusType(cs, ps, jses, pg)
print m

# ---

pg = CRCLProgramType(Name="Test Program")
pg.add(MoveThroughToType(2, True, [PoseOnlyLocationType(point1, xaxis, zaxis)]))
print pg

print VectorVectorCross(VectorType(1, 0, 0), VectorType(0, 1, 0))

m = MatrixType(VectorType(0.866, 0.5, 0), VectorType(-0.5, 0.866, 0), VectorType(0, 0, 1))
print m

print VectorsToMatrix(VectorType(0.866, 0.5, 0), VectorType(0, 0, 1))

n = MatrixType(VectorType(0.866, -0.5, 0), VectorType(0.5, 0.866, 0), VectorType(0, 0, 1))

print MatrixMatrixMult(m, n)

P1 = PoseLocationType(PointType(100, 200, 0), VectorType(1,0,0), VectorType(0,0,1))
P21 = PoseLocationType(PointType(-5, 20, 12.7), VectorType(0,1,0), VectorType(0,0,1))
P2 = PosePoseMult(P1, P21)
print P2

toks = [1, 2, 3, 4, 5, 6, 7]
params = []

for key, val in zip(toks[0::2], toks[1::2]):
    params.append(ParameterSettingType(key, val))

m = SetEndEffectorParametersType(1, params)
print m

q = QuaternionType(0.7071, 0, 0, 0.7071)
m = QuaternionToMatrix(q)
print m
qq = MatrixToQuaternion(m)
print q
print qq
