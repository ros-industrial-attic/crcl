/*********************************************************************/

#include <stdio.h>             // for printf, etc.
#include <string.h>            // for strdup
#include <stdlib.h>            // for exit
#include <list>
#include <boost/regex.hpp>
#include <xml_parser_generator/xmlSchemaInstance.hh>
#include "crcl_cpp/CRCLStatusClasses.hh"

#define INDENT 2

/*********************************************************************/
/*********************************************************************/

/* class CRCLStatusFile

*/

CRCLStatusFile::CRCLStatusFile()
{
  version = 0;
  header = 0;
  CRCLStatus = 0;
}

CRCLStatusFile::CRCLStatusFile(
  XmlVersion * versionIn,
  XmlHeaderForCRCLStatus * headerIn,
  CRCLStatusType * CRCLStatusIn)
{
  version = versionIn;
  header = headerIn;
  CRCLStatus = CRCLStatusIn;
}

CRCLStatusFile::~CRCLStatusFile()
{
  delete version;
  delete header;
  delete CRCLStatus;
}

void CRCLStatusFile::PRINTSELFDECL
{
  version->PRINTSELF;
  XFPRINTF "<CRCLStatus\n");
  header->PRINTSELF;
  CRCLStatus->PRINTSELF;
  XFPRINTF "</CRCLStatus>\n");
}

/*********************************************************************/

/* class CRCLStatusType

*/

CRCLStatusType::CRCLStatusType() :
  DataThingType()
{
  CommandStatus = 0;
  JointStatuses = 0;
  PoseStatus = 0;
  GripperStatus = 0;
}

CRCLStatusType::CRCLStatusType(
 XmlID * NameIn,
 CommandStatusType * CommandStatusIn,
 JointStatusesType * JointStatusesIn,
 PoseStatusType * PoseStatusIn,
 GripperStatusType * GripperStatusIn) :
  DataThingType(
    NameIn)
{
  CommandStatus = CommandStatusIn;
  JointStatuses = JointStatusesIn;
  PoseStatus = PoseStatusIn;
  GripperStatus = GripperStatusIn;
  printTypp = false;
}

CRCLStatusType::~CRCLStatusType()
{
  delete CommandStatus;
  delete JointStatuses;
  delete PoseStatus;
  delete GripperStatus;
}

void CRCLStatusType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"CRCLStatusType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  SPACESZERO;
  XFPRINTF "<CommandStatus");
  CommandStatus->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</CommandStatus>\n");
  if (JointStatuses)
    {
      SPACESZERO;
      XFPRINTF "<JointStatuses");
      JointStatuses->PRINTSELF;
      SPACESZERO;
      XFPRINTF "</JointStatuses>\n");
    }
  if (PoseStatus)
    {
      SPACESZERO;
      XFPRINTF "<PoseStatus");
      PoseStatus->PRINTSELF;
      SPACESZERO;
      XFPRINTF "</PoseStatus>\n");
    }
  if (GripperStatus)
    {
      SPACESZERO;
      XFPRINTF "<GripperStatus");
      GripperStatus->PRINTSELF;
      SPACESZERO;
      XFPRINTF "</GripperStatus>\n");
    }
  SPACESMINUS;
}

/*********************************************************************/

/* class CommandStateEnumType

*/

CommandStateEnumType::CommandStateEnumType() :
  XmlString() {}

CommandStateEnumType::CommandStateEnumType(
 const char * valIn) :
  XmlString(valIn)
{
  if (!bad)
    bad = (strcmp(val.c_str(), "CRCL_Done") &&
           strcmp(val.c_str(), "CRCL_Error") &&
           strcmp(val.c_str(), "CRCL_Working") &&
           strcmp(val.c_str(), "CRCL_Ready"));
}

CommandStateEnumType::~CommandStateEnumType() {}

bool CommandStateEnumType::CommandStateEnumTypeIsBad()
{
  return (strcmp(val.c_str(), "CRCL_Done") &&
          strcmp(val.c_str(), "CRCL_Error") &&
          strcmp(val.c_str(), "CRCL_Working") &&
          strcmp(val.c_str(), "CRCL_Ready"));
}

void CommandStateEnumType::PRINTSELFDECL
{
  if (CommandStateEnumTypeIsBad())
    {
      fprintf(stderr, "bad CommandStateEnumType value, ");
      XmlString::printBad(stderr);
      fprintf(stderr, " exiting\n");
      exit(1);
    }
  XmlString::PRINTSELF;
}

/*********************************************************************/

/* class CommandStatusType

*/

CommandStatusType::CommandStatusType() :
  DataThingType()
{
  CommandID = 0;
  StatusID = 0;
  CommandState = 0;
}

CommandStatusType::CommandStatusType(
 XmlID * NameIn,
 XmlNonNegativeInteger * CommandIDIn,
 XmlPositiveInteger * StatusIDIn,
 CommandStateEnumType * CommandStateIn) :
  DataThingType(
    NameIn)
{
  CommandID = CommandIDIn;
  StatusID = StatusIDIn;
  CommandState = CommandStateIn;
  printTypp = false;
}

CommandStatusType::~CommandStatusType()
{
  delete CommandID;
  delete StatusID;
  delete CommandState;
}

void CommandStatusType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"CommandStatusType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  SPACESZERO;
  XFPRINTF "<CommandID>");
  CommandID->PRINTSELF;
  XFPRINTF "</CommandID>\n");
  SPACESZERO;
  XFPRINTF "<StatusID>");
  StatusID->PRINTSELF;
  XFPRINTF "</StatusID>\n");
  SPACESZERO;
  XFPRINTF "<CommandState>");
  CommandState->PRINTSELF;
  XFPRINTF "</CommandState>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class GripperStatusType

*/

GripperStatusType::GripperStatusType() :
  DataThingType()
{
  GripperName = 0;
}

GripperStatusType::GripperStatusType(
 XmlID * NameIn,
 XmlNMTOKEN * GripperNameIn) :
  DataThingType(
    NameIn)
{
  GripperName = GripperNameIn;
  printTypp = false;
}

GripperStatusType::~GripperStatusType()
{
  delete GripperName;
}

void GripperStatusType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"GripperStatusType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  SPACESZERO;
  XFPRINTF "<GripperName>");
  GripperName->PRINTSELF;
  XFPRINTF "</GripperName>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class JointStatusType

*/

JointStatusType::JointStatusType() :
  DataThingType()
{
  JointNumber = 0;
  JointPosition = 0;
  JointTorqueOrForce = 0;
  JointVelocity = 0;
}

JointStatusType::JointStatusType(
 XmlID * NameIn,
 XmlPositiveInteger * JointNumberIn,
 XmlDecimal * JointPositionIn,
 XmlDecimal * JointTorqueOrForceIn,
 XmlDecimal * JointVelocityIn) :
  DataThingType(
    NameIn)
{
  JointNumber = JointNumberIn;
  JointPosition = JointPositionIn;
  JointTorqueOrForce = JointTorqueOrForceIn;
  JointVelocity = JointVelocityIn;
  printTypp = false;
}

JointStatusType::~JointStatusType()
{
  delete JointNumber;
  delete JointPosition;
  delete JointTorqueOrForce;
  delete JointVelocity;
}

void JointStatusType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"JointStatusType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  SPACESZERO;
  XFPRINTF "<JointNumber>");
  JointNumber->PRINTSELF;
  XFPRINTF "</JointNumber>\n");
  if (JointPosition)
    {
      SPACESZERO;
      XFPRINTF "<JointPosition>");
      JointPosition->PRINTSELF;
      XFPRINTF "</JointPosition>\n");
    }
  if (JointTorqueOrForce)
    {
      SPACESZERO;
      XFPRINTF "<JointTorqueOrForce>");
      JointTorqueOrForce->PRINTSELF;
      XFPRINTF "</JointTorqueOrForce>\n");
    }
  if (JointVelocity)
    {
      SPACESZERO;
      XFPRINTF "<JointVelocity>");
      JointVelocity->PRINTSELF;
      XFPRINTF "</JointVelocity>\n");
    }
  SPACESMINUS;
}

/*********************************************************************/

/* class JointStatusesType

*/

JointStatusesType::JointStatusesType() :
  DataThingType()
{
  JointStatus = 0;
}

JointStatusesType::JointStatusesType(
 XmlID * NameIn,
 std::list<JointStatusType *> * JointStatusIn) :
  DataThingType(
    NameIn)
{
  JointStatus = JointStatusIn;
  printTypp = false;
}

JointStatusesType::~JointStatusesType()
{
  if (JointStatus)
    {
      std::list<JointStatusType *>::iterator iter;
      for (iter = JointStatus->begin();
           iter != JointStatus->end(); iter++)
        delete *iter;
      delete JointStatus;
    }
}

void JointStatusesType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"JointStatusesType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  {
    std::list<JointStatusType *>::iterator iter;
    for (iter = JointStatus->begin(); iter != JointStatus->end(); iter++)
      {
        SPACESZERO;
        XFPRINTF "<JointStatus");
        (*iter)->PRINTSELF;
        SPACESZERO;
        XFPRINTF "</JointStatus>\n");
      }
  }
  SPACESMINUS;
}

/*********************************************************************/

/* class ParallelGripperStatusType

*/

ParallelGripperStatusType::ParallelGripperStatusType() :
  GripperStatusType()
{
  Separation = 0;
}

ParallelGripperStatusType::ParallelGripperStatusType(
 XmlID * NameIn,
 XmlNMTOKEN * GripperNameIn,
 XmlDecimal * SeparationIn) :
  GripperStatusType(
    NameIn,
    GripperNameIn)
{
  Separation = SeparationIn;
  printTypp = false;
}

ParallelGripperStatusType::~ParallelGripperStatusType()
{
  delete Separation;
}

void ParallelGripperStatusType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"ParallelGripperStatusType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  SPACESZERO;
  XFPRINTF "<GripperName>");
  GripperName->PRINTSELF;
  XFPRINTF "</GripperName>\n");
  SPACESZERO;
  XFPRINTF "<Separation>");
  Separation->PRINTSELF;
  XFPRINTF "</Separation>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class PoseStatusType

*/

PoseStatusType::PoseStatusType() :
  DataThingType()
{
  Pose = 0;
  Twist = 0;
  Wrench = 0;
}

PoseStatusType::PoseStatusType(
 XmlID * NameIn,
 PoseType * PoseIn,
 TwistType * TwistIn,
 WrenchType * WrenchIn) :
  DataThingType(
    NameIn)
{
  Pose = PoseIn;
  Twist = TwistIn;
  Wrench = WrenchIn;
  printTypp = false;
}

PoseStatusType::~PoseStatusType()
{
  delete Pose;
  delete Twist;
  delete Wrench;
}

void PoseStatusType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"PoseStatusType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  SPACESZERO;
  XFPRINTF "<Pose");
  Pose->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</Pose>\n");
  if (Twist)
    {
      SPACESZERO;
      XFPRINTF "<Twist");
      Twist->PRINTSELF;
      SPACESZERO;
      XFPRINTF "</Twist>\n");
    }
  if (Wrench)
    {
      SPACESZERO;
      XFPRINTF "<Wrench");
      Wrench->PRINTSELF;
      SPACESZERO;
      XFPRINTF "</Wrench>\n");
    }
  SPACESMINUS;
}

/*********************************************************************/

/* class ThreeFingerGripperStatusType

*/

ThreeFingerGripperStatusType::ThreeFingerGripperStatusType() :
  GripperStatusType()
{
  Finger1Position = 0;
  Finger2Position = 0;
  Finger3Position = 0;
  Finger1Force = 0;
  Finger2Force = 0;
  Finger3Force = 0;
}

ThreeFingerGripperStatusType::ThreeFingerGripperStatusType(
 XmlID * NameIn,
 XmlNMTOKEN * GripperNameIn,
 FractionType * Finger1PositionIn,
 FractionType * Finger2PositionIn,
 FractionType * Finger3PositionIn,
 XmlDecimal * Finger1ForceIn,
 XmlDecimal * Finger2ForceIn,
 XmlDecimal * Finger3ForceIn) :
  GripperStatusType(
    NameIn,
    GripperNameIn)
{
  Finger1Position = Finger1PositionIn;
  Finger2Position = Finger2PositionIn;
  Finger3Position = Finger3PositionIn;
  Finger1Force = Finger1ForceIn;
  Finger2Force = Finger2ForceIn;
  Finger3Force = Finger3ForceIn;
  printTypp = false;
}

ThreeFingerGripperStatusType::~ThreeFingerGripperStatusType()
{
  delete Finger1Position;
  delete Finger2Position;
  delete Finger3Position;
  delete Finger1Force;
  delete Finger2Force;
  delete Finger3Force;
}

void ThreeFingerGripperStatusType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"ThreeFingerGripperStatusType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  SPACESZERO;
  XFPRINTF "<GripperName>");
  GripperName->PRINTSELF;
  XFPRINTF "</GripperName>\n");
  if (Finger1Position)
    {
      SPACESZERO;
      XFPRINTF "<Finger1Position>");
      Finger1Position->PRINTSELF;
      XFPRINTF "</Finger1Position>\n");
    }
  if (Finger2Position)
    {
      SPACESZERO;
      XFPRINTF "<Finger2Position>");
      Finger2Position->PRINTSELF;
      XFPRINTF "</Finger2Position>\n");
    }
  if (Finger3Position)
    {
      SPACESZERO;
      XFPRINTF "<Finger3Position>");
      Finger3Position->PRINTSELF;
      XFPRINTF "</Finger3Position>\n");
    }
  if (Finger1Force)
    {
      SPACESZERO;
      XFPRINTF "<Finger1Force>");
      Finger1Force->PRINTSELF;
      XFPRINTF "</Finger1Force>\n");
    }
  if (Finger2Force)
    {
      SPACESZERO;
      XFPRINTF "<Finger2Force>");
      Finger2Force->PRINTSELF;
      XFPRINTF "</Finger2Force>\n");
    }
  if (Finger3Force)
    {
      SPACESZERO;
      XFPRINTF "<Finger3Force>");
      Finger3Force->PRINTSELF;
      XFPRINTF "</Finger3Force>\n");
    }
  SPACESMINUS;
}

/*********************************************************************/

/* class VacuumGripperStatusType

*/

VacuumGripperStatusType::VacuumGripperStatusType() :
  GripperStatusType()
{
  IsPowered = 0;
}

VacuumGripperStatusType::VacuumGripperStatusType(
 XmlID * NameIn,
 XmlNMTOKEN * GripperNameIn,
 XmlBoolean * IsPoweredIn) :
  GripperStatusType(
    NameIn,
    GripperNameIn)
{
  IsPowered = IsPoweredIn;
  printTypp = false;
}

VacuumGripperStatusType::~VacuumGripperStatusType()
{
  delete IsPowered;
}

void VacuumGripperStatusType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"VacuumGripperStatusType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  SPACESZERO;
  XFPRINTF "<GripperName>");
  GripperName->PRINTSELF;
  XFPRINTF "</GripperName>\n");
  SPACESZERO;
  XFPRINTF "<IsPowered>");
  IsPowered->PRINTSELF;
  XFPRINTF "</IsPowered>\n");
  SPACESMINUS;
}

/*********************************************************************/

XmlHeaderForCRCLStatus::XmlHeaderForCRCLStatus()
{
  location = 0;
}

XmlHeaderForCRCLStatus::XmlHeaderForCRCLStatus(
  SchemaLocation * locationIn)
{
  location = locationIn;
}

XmlHeaderForCRCLStatus::~XmlHeaderForCRCLStatus()
{
  delete location;
}

void XmlHeaderForCRCLStatus::PRINTSELFDECL
{
  XFPRINTF
          "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
  location->PRINTSELF;
}

/*********************************************************************/

