/*********************************************************************/

#ifndef CRCLSTATUS_HH
#define CRCLSTATUS_HH
#include <stdio.h>
#include <list>
#include <xml_parser_generator/xmlSchemaInstance.hh>
#include "crcl_cpp/DataPrimitivesClasses.hh"

/*********************************************************************/

class CRCLStatusFile;
class CRCLStatusType;
class CommandStateEnumType;
class CommandStatusType;
class GripperStatusType;
class JointStatusType;
class JointStatusesType;
class ParallelGripperStatusType;
class PoseStatusType;
class ThreeFingerGripperStatusType;
class VacuumGripperStatusType;
class XmlHeaderForCRCLStatus;

/*********************************************************************/
/*********************************************************************/

class CRCLStatusFile :
  public XmlSchemaInstanceBase
{
public:
  CRCLStatusFile();
  CRCLStatusFile(
    XmlVersion * versionIn,
    XmlHeaderForCRCLStatus * headerIn,
    CRCLStatusType * CRCLStatusIn);
  ~CRCLStatusFile();
  void PRINTSELFDECL;

  XmlVersion * version;
  XmlHeaderForCRCLStatus * header;
  CRCLStatusType * CRCLStatus;
};

/*********************************************************************/

class CRCLStatusType :
  public DataThingType
{
public:
  CRCLStatusType();
  CRCLStatusType(
    XmlID * NameIn,
    CommandStatusType * CommandStatusIn,
    JointStatusesType * JointStatusesIn,
    PoseStatusType * PoseStatusIn,
    GripperStatusType * GripperStatusIn);
  ~CRCLStatusType();
  void PRINTSELFDECL;

  CommandStatusType * CommandStatus;
  JointStatusesType * JointStatuses;
  PoseStatusType * PoseStatus;
  GripperStatusType * GripperStatus;

  bool printTypp;
};

/*********************************************************************/

class CommandStateEnumType :
  public XmlString
{
public:
  CommandStateEnumType();
  CommandStateEnumType(
    const char * valIn);
  ~CommandStateEnumType();
  bool CommandStateEnumTypeIsBad();
  void PRINTSELFDECL;
};

/*********************************************************************/

class CommandStatusType :
  public DataThingType
{
public:
  CommandStatusType();
  CommandStatusType(
    XmlID * NameIn,
    XmlNonNegativeInteger * CommandIDIn,
    XmlPositiveInteger * StatusIDIn,
    CommandStateEnumType * CommandStateIn);
  ~CommandStatusType();
  void PRINTSELFDECL;

  XmlNonNegativeInteger * CommandID;
  XmlPositiveInteger * StatusID;
  CommandStateEnumType * CommandState;

  bool printTypp;
};

/*********************************************************************/

class GripperStatusType :
  public DataThingType
{
public:
  GripperStatusType();
  GripperStatusType(
    XmlID * NameIn,
    XmlNMTOKEN * GripperNameIn);
  ~GripperStatusType();
  void PRINTSELFDECL;

  XmlNMTOKEN * GripperName;

  bool printTypp;
};

/*********************************************************************/

class JointStatusType :
  public DataThingType
{
public:
  JointStatusType();
  JointStatusType(
    XmlID * NameIn,
    XmlPositiveInteger * JointNumberIn,
    XmlDecimal * JointPositionIn,
    XmlDecimal * JointTorqueOrForceIn,
    XmlDecimal * JointVelocityIn);
  ~JointStatusType();
  void PRINTSELFDECL;

  XmlPositiveInteger * JointNumber;
  XmlDecimal * JointPosition;
  XmlDecimal * JointTorqueOrForce;
  XmlDecimal * JointVelocity;

  bool printTypp;
};

/*********************************************************************/

class JointStatusesType :
  public DataThingType
{
public:
  JointStatusesType();
  JointStatusesType(
    XmlID * NameIn,
    std::list<JointStatusType *> * JointStatusIn);
  ~JointStatusesType();
  void PRINTSELFDECL;

  std::list<JointStatusType *> * JointStatus;

  bool printTypp;
};

/*********************************************************************/

class ParallelGripperStatusType :
  public GripperStatusType
{
public:
  ParallelGripperStatusType();
  ParallelGripperStatusType(
    XmlID * NameIn,
    XmlNMTOKEN * GripperNameIn,
    XmlDecimal * SeparationIn);
  ~ParallelGripperStatusType();
  void PRINTSELFDECL;

  XmlDecimal * Separation;

  bool printTypp;
};

/*********************************************************************/

class PoseStatusType :
  public DataThingType
{
public:
  PoseStatusType();
  PoseStatusType(
    XmlID * NameIn,
    PoseType * PoseIn,
    TwistType * TwistIn,
    WrenchType * WrenchIn);
  ~PoseStatusType();
  void PRINTSELFDECL;

  PoseType * Pose;
  TwistType * Twist;
  WrenchType * Wrench;

  bool printTypp;
};

/*********************************************************************/

class ThreeFingerGripperStatusType :
  public GripperStatusType
{
public:
  ThreeFingerGripperStatusType();
  ThreeFingerGripperStatusType(
    XmlID * NameIn,
    XmlNMTOKEN * GripperNameIn,
    FractionType * Finger1PositionIn,
    FractionType * Finger2PositionIn,
    FractionType * Finger3PositionIn,
    XmlDecimal * Finger1ForceIn,
    XmlDecimal * Finger2ForceIn,
    XmlDecimal * Finger3ForceIn);
  ~ThreeFingerGripperStatusType();
  void PRINTSELFDECL;

  FractionType * Finger1Position;
  FractionType * Finger2Position;
  FractionType * Finger3Position;
  XmlDecimal * Finger1Force;
  XmlDecimal * Finger2Force;
  XmlDecimal * Finger3Force;

  bool printTypp;
};

/*********************************************************************/

class VacuumGripperStatusType :
  public GripperStatusType
{
public:
  VacuumGripperStatusType();
  VacuumGripperStatusType(
    XmlID * NameIn,
    XmlNMTOKEN * GripperNameIn,
    XmlBoolean * IsPoweredIn);
  ~VacuumGripperStatusType();
  void PRINTSELFDECL;

  XmlBoolean * IsPowered;

  bool printTypp;
};

/*********************************************************************/

class XmlHeaderForCRCLStatus
{
public:
  XmlHeaderForCRCLStatus();
  XmlHeaderForCRCLStatus(
    SchemaLocation * locationIn);
  ~XmlHeaderForCRCLStatus();
  void PRINTSELFDECL;

  SchemaLocation * location;
};

/*********************************************************************/

#endif // CRCLSTATUS_HH
