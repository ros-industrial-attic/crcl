/*********************************************************************/

#include <stdio.h>             // for printf, etc.
#include <string.h>            // for strdup
#include <stdlib.h>            // for exit
#include <list>
#include <boost/regex.hpp>
#include <xml_common/xmlSchemaInstance.hh>
#include "crcl_cpp/CRCLCommandsClasses.hh"

#define INDENT 2

/*********************************************************************/
/*********************************************************************/

/* class ActuateJointType

*/

ActuateJointType::ActuateJointType() :
  DataThingType()
{
  JointNumber = 0;
  JointPosition = 0;
  JointDetails = 0;
}

ActuateJointType::ActuateJointType(
 XmlID * NameIn,
 XmlPositiveInteger * JointNumberIn,
 XmlDecimal * JointPositionIn,
 JointDetailsType * JointDetailsIn) :
  DataThingType(
    NameIn)
{
  JointNumber = JointNumberIn;
  JointPosition = JointPositionIn;
  JointDetails = JointDetailsIn;
  printTypp = false;
}

ActuateJointType::~ActuateJointType()
{
  delete JointNumber;
  delete JointPosition;
  delete JointDetails;
}

void ActuateJointType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"ActuateJointType\"");
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
  SPACESZERO;
  XFPRINTF "<JointPosition>");
  JointPosition->PRINTSELF;
  XFPRINTF "</JointPosition>\n");
  SPACESZERO;
  XFPRINTF "<JointDetails");
  JointDetails->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</JointDetails>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class ActuateJointsType

*/

ActuateJointsType::ActuateJointsType() :
  MiddleCommandType()
{
  ActuateJoint = 0;
}

ActuateJointsType::ActuateJointsType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 std::list<ActuateJointType *> * ActuateJointIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  ActuateJoint = ActuateJointIn;
  printTypp = false;
}

ActuateJointsType::~ActuateJointsType()
{
  {
    std::list<ActuateJointType *>::iterator iter;
    for (iter = ActuateJoint->begin();
         iter != ActuateJoint->end(); iter++)
      delete *iter;
  }
  delete ActuateJoint;
}

void ActuateJointsType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"ActuateJointsType\"");
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
  {
    std::list<ActuateJointType *>::iterator iter;
    for (iter = ActuateJoint->begin(); iter != ActuateJoint->end(); iter++)
      {
        SPACESZERO;
        XFPRINTF "<ActuateJoint");
        (*iter)->PRINTSELF;
        SPACESZERO;
        XFPRINTF "</ActuateJoint>\n");
      }
  }
  SPACESMINUS;
}

/*********************************************************************/

/* class CRCLCommandType

*/

CRCLCommandType::CRCLCommandType() :
  DataThingType()
{
  CommandID = 0;
}

CRCLCommandType::CRCLCommandType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn) :
  DataThingType(
    NameIn)
{
  CommandID = CommandIDIn;
  printTypp = false;
}

CRCLCommandType::~CRCLCommandType()
{
  delete CommandID;
}

void CRCLCommandType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"CRCLCommandType\"");
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
  SPACESMINUS;
}

/*********************************************************************/

/* class CloseToolChangerType

*/

CloseToolChangerType::CloseToolChangerType() :
  MiddleCommandType() {}

CloseToolChangerType::CloseToolChangerType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  printTypp = false;
}

CloseToolChangerType::~CloseToolChangerType() {}

void CloseToolChangerType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"CloseToolChangerType\"");
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
  SPACESMINUS;
}

/*********************************************************************/

/* class ConfigureJointReportType

*/

ConfigureJointReportType::ConfigureJointReportType() :
  DataThingType()
{
  JointNumber = 0;
  ReportPosition = 0;
  ReportTorqueOrForce = 0;
  ReportVelocity = 0;
}

ConfigureJointReportType::ConfigureJointReportType(
 XmlID * NameIn,
 XmlPositiveInteger * JointNumberIn,
 XmlBoolean * ReportPositionIn,
 XmlBoolean * ReportTorqueOrForceIn,
 XmlBoolean * ReportVelocityIn) :
  DataThingType(
    NameIn)
{
  JointNumber = JointNumberIn;
  ReportPosition = ReportPositionIn;
  ReportTorqueOrForce = ReportTorqueOrForceIn;
  ReportVelocity = ReportVelocityIn;
  printTypp = false;
}

ConfigureJointReportType::~ConfigureJointReportType()
{
  delete JointNumber;
  delete ReportPosition;
  delete ReportTorqueOrForce;
  delete ReportVelocity;
}

void ConfigureJointReportType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"ConfigureJointReportType\"");
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
  SPACESZERO;
  XFPRINTF "<ReportPosition>");
  ReportPosition->PRINTSELF;
  XFPRINTF "</ReportPosition>\n");
  SPACESZERO;
  XFPRINTF "<ReportTorqueOrForce>");
  ReportTorqueOrForce->PRINTSELF;
  XFPRINTF "</ReportTorqueOrForce>\n");
  SPACESZERO;
  XFPRINTF "<ReportVelocity>");
  ReportVelocity->PRINTSELF;
  XFPRINTF "</ReportVelocity>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class ConfigureJointReportsType

*/

ConfigureJointReportsType::ConfigureJointReportsType() :
  MiddleCommandType()
{
  ResetAll = 0;
  ConfigureJointReport = 0;
}

ConfigureJointReportsType::ConfigureJointReportsType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 XmlBoolean * ResetAllIn,
 std::list<ConfigureJointReportType *> * ConfigureJointReportIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  ResetAll = ResetAllIn;
  ConfigureJointReport = ConfigureJointReportIn;
  printTypp = false;
}

ConfigureJointReportsType::~ConfigureJointReportsType()
{
  delete ResetAll;
  {
    std::list<ConfigureJointReportType *>::iterator iter;
    for (iter = ConfigureJointReport->begin();
         iter != ConfigureJointReport->end(); iter++)
      delete *iter;
  }
  delete ConfigureJointReport;
}

void ConfigureJointReportsType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"ConfigureJointReportsType\"");
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
  XFPRINTF "<ResetAll>");
  ResetAll->PRINTSELF;
  XFPRINTF "</ResetAll>\n");
  {
    std::list<ConfigureJointReportType *>::iterator iter;
    for (iter = ConfigureJointReport->begin(); iter != ConfigureJointReport->end(); iter++)
      {
        SPACESZERO;
        XFPRINTF "<ConfigureJointReport");
        (*iter)->PRINTSELF;
        SPACESZERO;
        XFPRINTF "</ConfigureJointReport>\n");
      }
  }
  SPACESMINUS;
}

/*********************************************************************/

/* class DwellType

*/

DwellType::DwellType() :
  MiddleCommandType()
{
  DwellTime = 0;
}

DwellType::DwellType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 XmlDecimal * DwellTimeIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  DwellTime = DwellTimeIn;
  printTypp = false;
}

DwellType::~DwellType()
{
  delete DwellTime;
}

void DwellType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"DwellType\"");
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
  XFPRINTF "<DwellTime>");
  DwellTime->PRINTSELF;
  XFPRINTF "</DwellTime>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class EndCanonType

*/

EndCanonType::EndCanonType() :
  CRCLCommandType() {}

EndCanonType::EndCanonType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn) :
  CRCLCommandType(
    NameIn,
    CommandIDIn)
{
  printTypp = false;
}

EndCanonType::~EndCanonType() {}

void EndCanonType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"EndCanonType\"");
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
  SPACESMINUS;
}

/*********************************************************************/

/* class GetStatusType

*/

GetStatusType::GetStatusType() :
  MiddleCommandType() {}

GetStatusType::GetStatusType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  printTypp = false;
}

GetStatusType::~GetStatusType() {}

void GetStatusType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"GetStatusType\"");
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
  SPACESMINUS;
}

/*********************************************************************/

/* class InitCanonType

*/

InitCanonType::InitCanonType() :
  CRCLCommandType() {}

InitCanonType::InitCanonType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn) :
  CRCLCommandType(
    NameIn,
    CommandIDIn)
{
  printTypp = false;
}

InitCanonType::~InitCanonType() {}

void InitCanonType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"InitCanonType\"");
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
  SPACESMINUS;
}

/*********************************************************************/

/* class JointDetailsType

*/

JointDetailsType::JointDetailsType() :
  DataThingType() {}

JointDetailsType::JointDetailsType(
 XmlID * NameIn) :
  DataThingType(
    NameIn)
{
  printTypp = false;
}

JointDetailsType::~JointDetailsType() {}

void JointDetailsType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"JointDetailsType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  SPACESMINUS;
}

/*********************************************************************/

/* class JointForceTorqueType

*/

JointForceTorqueType::JointForceTorqueType() :
  JointDetailsType()
{
  Setting = 0;
  ChangeRate = 0;
}

JointForceTorqueType::JointForceTorqueType(
 XmlID * NameIn,
 XmlDecimal * SettingIn,
 XmlDecimal * ChangeRateIn) :
  JointDetailsType(
    NameIn)
{
  Setting = SettingIn;
  ChangeRate = ChangeRateIn;
  printTypp = false;
}

JointForceTorqueType::~JointForceTorqueType()
{
  delete Setting;
  delete ChangeRate;
}

void JointForceTorqueType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"JointForceTorqueType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  if (Setting)
    {
      SPACESZERO;
      XFPRINTF "<Setting>");
      Setting->PRINTSELF;
      XFPRINTF "</Setting>\n");
    }
  if (ChangeRate)
    {
      SPACESZERO;
      XFPRINTF "<ChangeRate>");
      ChangeRate->PRINTSELF;
      XFPRINTF "</ChangeRate>\n");
    }
  SPACESMINUS;
}

/*********************************************************************/

/* class JointSpeedAccelType

*/

JointSpeedAccelType::JointSpeedAccelType() :
  JointDetailsType()
{
  JointSpeed = 0;
  JointAccel = 0;
}

JointSpeedAccelType::JointSpeedAccelType(
 XmlID * NameIn,
 XmlDecimal * JointSpeedIn,
 XmlDecimal * JointAccelIn) :
  JointDetailsType(
    NameIn)
{
  JointSpeed = JointSpeedIn;
  JointAccel = JointAccelIn;
  printTypp = false;
}

JointSpeedAccelType::~JointSpeedAccelType()
{
  delete JointSpeed;
  delete JointAccel;
}

void JointSpeedAccelType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"JointSpeedAccelType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  if (JointSpeed)
    {
      SPACESZERO;
      XFPRINTF "<JointSpeed>");
      JointSpeed->PRINTSELF;
      XFPRINTF "</JointSpeed>\n");
    }
  if (JointAccel)
    {
      SPACESZERO;
      XFPRINTF "<JointAccel>");
      JointAccel->PRINTSELF;
      XFPRINTF "</JointAccel>\n");
    }
  SPACESMINUS;
}

/*********************************************************************/

/* class MessageType

*/

MessageType::MessageType() :
  MiddleCommandType()
{
  Message = 0;
}

MessageType::MessageType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 XmlString * MessageIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  Message = MessageIn;
  printTypp = false;
}

MessageType::~MessageType()
{
  delete Message;
}

void MessageType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"MessageType\"");
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
  XFPRINTF "<Message>");
  Message->PRINTSELF;
  XFPRINTF "</Message>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class MiddleCommandType

*/

MiddleCommandType::MiddleCommandType() :
  CRCLCommandType() {}

MiddleCommandType::MiddleCommandType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn) :
  CRCLCommandType(
    NameIn,
    CommandIDIn)
{
  printTypp = false;
}

MiddleCommandType::~MiddleCommandType() {}

void MiddleCommandType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"MiddleCommandType\"");
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
  SPACESMINUS;
}

/*********************************************************************/

/* class MoveScrewType

*/

MoveScrewType::MoveScrewType() :
  MiddleCommandType()
{
  StartPosition = 0;
  AxisPoint = 0;
  AxialDistanceFree = 0;
  AxialDistanceScrew = 0;
  Turn = 0;
}

MoveScrewType::MoveScrewType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 PoseOnlyLocationType * StartPositionIn,
 PointType * AxisPointIn,
 XmlDecimal * AxialDistanceFreeIn,
 XmlDecimal * AxialDistanceScrewIn,
 XmlDecimal * TurnIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  StartPosition = StartPositionIn;
  AxisPoint = AxisPointIn;
  AxialDistanceFree = AxialDistanceFreeIn;
  AxialDistanceScrew = AxialDistanceScrewIn;
  Turn = TurnIn;
  printTypp = false;
}

MoveScrewType::~MoveScrewType()
{
  delete StartPosition;
  delete AxisPoint;
  delete AxialDistanceFree;
  delete AxialDistanceScrew;
  delete Turn;
}

void MoveScrewType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"MoveScrewType\"");
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
  if (StartPosition)
    {
      SPACESZERO;
      XFPRINTF "<StartPosition");
      StartPosition->PRINTSELF;
      SPACESZERO;
      XFPRINTF "</StartPosition>\n");
    }
  if (AxisPoint)
    {
      SPACESZERO;
      XFPRINTF "<AxisPoint");
      AxisPoint->PRINTSELF;
      SPACESZERO;
      XFPRINTF "</AxisPoint>\n");
    }
  if (AxialDistanceFree)
    {
      SPACESZERO;
      XFPRINTF "<AxialDistanceFree>");
      AxialDistanceFree->PRINTSELF;
      XFPRINTF "</AxialDistanceFree>\n");
    }
  SPACESZERO;
  XFPRINTF "<AxialDistanceScrew>");
  AxialDistanceScrew->PRINTSELF;
  XFPRINTF "</AxialDistanceScrew>\n");
  SPACESZERO;
  XFPRINTF "<Turn>");
  Turn->PRINTSELF;
  XFPRINTF "</Turn>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class MoveThroughToType

*/

MoveThroughToType::MoveThroughToType() :
  MiddleCommandType()
{
  MoveStraight = 0;
  Waypoint = 0;
  NumPositions = 0;
}

MoveThroughToType::MoveThroughToType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 XmlBoolean * MoveStraightIn,
 std::list<PoseOnlyLocationType *> * WaypointIn,
 XmlPositiveInteger * NumPositionsIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  MoveStraight = MoveStraightIn;
  Waypoint = WaypointIn;
  NumPositions = NumPositionsIn;
  printTypp = false;
}

MoveThroughToType::~MoveThroughToType()
{
  delete MoveStraight;
  {
    std::list<PoseOnlyLocationType *>::iterator iter;
    for (iter = Waypoint->begin();
         iter != Waypoint->end(); iter++)
      delete *iter;
  }
  delete Waypoint;
  delete NumPositions;
}

void MoveThroughToType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"MoveThroughToType\"");
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
  XFPRINTF "<MoveStraight>");
  MoveStraight->PRINTSELF;
  XFPRINTF "</MoveStraight>\n");
  {
    std::list<PoseOnlyLocationType *>::iterator iter;
    for (iter = Waypoint->begin(); iter != Waypoint->end(); iter++)
      {
        SPACESZERO;
        XFPRINTF "<Waypoint");
        (*iter)->PRINTSELF;
        SPACESZERO;
        XFPRINTF "</Waypoint>\n");
      }
  }
  SPACESZERO;
  XFPRINTF "<NumPositions>");
  NumPositions->PRINTSELF;
  XFPRINTF "</NumPositions>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class MoveToType

*/

MoveToType::MoveToType() :
  MiddleCommandType()
{
  MoveStraight = 0;
  EndPosition = 0;
}

MoveToType::MoveToType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 XmlBoolean * MoveStraightIn,
 PoseOnlyLocationType * EndPositionIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  MoveStraight = MoveStraightIn;
  EndPosition = EndPositionIn;
  printTypp = false;
}

MoveToType::~MoveToType()
{
  delete MoveStraight;
  delete EndPosition;
}

void MoveToType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"MoveToType\"");
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
  XFPRINTF "<MoveStraight>");
  MoveStraight->PRINTSELF;
  XFPRINTF "</MoveStraight>\n");
  SPACESZERO;
  XFPRINTF "<EndPosition");
  EndPosition->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</EndPosition>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class OpenToolChangerType

*/

OpenToolChangerType::OpenToolChangerType() :
  MiddleCommandType() {}

OpenToolChangerType::OpenToolChangerType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  printTypp = false;
}

OpenToolChangerType::~OpenToolChangerType() {}

void OpenToolChangerType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"OpenToolChangerType\"");
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
  SPACESMINUS;
}

/*********************************************************************/

/* class ParameterSettingType

*/

ParameterSettingType::ParameterSettingType() :
  DataThingType()
{
  ParameterName = 0;
  ParameterValue = 0;
}

ParameterSettingType::ParameterSettingType(
 XmlID * NameIn,
 XmlToken * ParameterNameIn,
 XmlToken * ParameterValueIn) :
  DataThingType(
    NameIn)
{
  ParameterName = ParameterNameIn;
  ParameterValue = ParameterValueIn;
  printTypp = false;
}

ParameterSettingType::~ParameterSettingType()
{
  delete ParameterName;
  delete ParameterValue;
}

void ParameterSettingType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"ParameterSettingType\"");
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
  XFPRINTF "<ParameterName>");
  ParameterName->PRINTSELF;
  XFPRINTF "</ParameterName>\n");
  SPACESZERO;
  XFPRINTF "<ParameterValue>");
  ParameterValue->PRINTSELF;
  XFPRINTF "</ParameterValue>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class PoseAndSetType

*/

PoseAndSetType::PoseAndSetType() :
  PoseOnlyLocationType()
{
  Coordinated = 0;
  TransSpeed = 0;
  RotSpeed = 0;
  TransAccel = 0;
  RotAccel = 0;
  Tolerance = 0;
}

PoseAndSetType::PoseAndSetType(
 XmlID * NameIn,
 XmlIDREF * RefObjectNameIn,
 XmlDateTime * TimestampIn,
 PointType * PointIn,
 VectorType * XAxisIn,
 VectorType * ZAxisIn,
 PositiveDecimalType * PositionStandardDeviationIn,
 PositiveDecimalType * OrientationStandardDeviationIn,
 XmlBoolean * CoordinatedIn,
 TransSpeedType * TransSpeedIn,
 RotSpeedType * RotSpeedIn,
 TransAccelType * TransAccelIn,
 RotAccelType * RotAccelIn,
 PoseToleranceType * ToleranceIn) :
  PoseOnlyLocationType(
    NameIn,
    RefObjectNameIn,
    TimestampIn,
    PointIn,
    XAxisIn,
    ZAxisIn,
    PositionStandardDeviationIn,
    OrientationStandardDeviationIn)
{
  Coordinated = CoordinatedIn;
  TransSpeed = TransSpeedIn;
  RotSpeed = RotSpeedIn;
  TransAccel = TransAccelIn;
  RotAccel = RotAccelIn;
  Tolerance = ToleranceIn;
  printTypp = false;
}

PoseAndSetType::~PoseAndSetType()
{
  delete Coordinated;
  delete TransSpeed;
  delete RotSpeed;
  delete TransAccel;
  delete RotAccel;
  delete Tolerance;
}

void PoseAndSetType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"PoseAndSetType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  if (RefObjectName)
    {
      SPACESZERO;
      XFPRINTF "<RefObjectName>");
      RefObjectName->PRINTSELF;
      XFPRINTF "</RefObjectName>\n");
    }
  if (Timestamp)
    {
      SPACESZERO;
      XFPRINTF "<Timestamp>");
      Timestamp->PRINTSELF;
      XFPRINTF "</Timestamp>\n");
    }
  SPACESZERO;
  XFPRINTF "<Point");
  Point->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</Point>\n");
  SPACESZERO;
  XFPRINTF "<XAxis");
  XAxis->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</XAxis>\n");
  SPACESZERO;
  XFPRINTF "<ZAxis");
  ZAxis->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</ZAxis>\n");
  if (PositionStandardDeviation)
    {
      SPACESZERO;
      XFPRINTF "<PositionStandardDeviation>");
      PositionStandardDeviation->PRINTSELF;
      XFPRINTF "</PositionStandardDeviation>\n");
    }
  if (OrientationStandardDeviation)
    {
      SPACESZERO;
      XFPRINTF "<OrientationStandardDeviation>");
      OrientationStandardDeviation->PRINTSELF;
      XFPRINTF "</OrientationStandardDeviation>\n");
    }
  SPACESZERO;
  XFPRINTF "<Coordinated>");
  Coordinated->PRINTSELF;
  XFPRINTF "</Coordinated>\n");
  if (TransSpeed)
    {
      SPACESZERO;
      XFPRINTF "<TransSpeed");
      TransSpeed->PRINTSELF;
      SPACESZERO;
      XFPRINTF "</TransSpeed>\n");
    }
  if (RotSpeed)
    {
      SPACESZERO;
      XFPRINTF "<RotSpeed");
      RotSpeed->PRINTSELF;
      SPACESZERO;
      XFPRINTF "</RotSpeed>\n");
    }
  if (TransAccel)
    {
      SPACESZERO;
      XFPRINTF "<TransAccel");
      TransAccel->PRINTSELF;
      SPACESZERO;
      XFPRINTF "</TransAccel>\n");
    }
  if (RotAccel)
    {
      SPACESZERO;
      XFPRINTF "<RotAccel");
      RotAccel->PRINTSELF;
      SPACESZERO;
      XFPRINTF "</RotAccel>\n");
    }
  if (Tolerance)
    {
      SPACESZERO;
      XFPRINTF "<Tolerance");
      Tolerance->PRINTSELF;
      SPACESZERO;
      XFPRINTF "</Tolerance>\n");
    }
  SPACESMINUS;
}

/*********************************************************************/

/* class PoseToleranceType

*/

PoseToleranceType::PoseToleranceType() :
  DataThingType()
{
  XPointTolerance = 0;
  YPointTolerance = 0;
  ZPointTolerance = 0;
  XAxisTolerance = 0;
  ZAxisTolerance = 0;
}

PoseToleranceType::PoseToleranceType(
 XmlID * NameIn,
 XmlDecimal * XPointToleranceIn,
 XmlDecimal * YPointToleranceIn,
 XmlDecimal * ZPointToleranceIn,
 XmlDecimal * XAxisToleranceIn,
 XmlDecimal * ZAxisToleranceIn) :
  DataThingType(
    NameIn)
{
  XPointTolerance = XPointToleranceIn;
  YPointTolerance = YPointToleranceIn;
  ZPointTolerance = ZPointToleranceIn;
  XAxisTolerance = XAxisToleranceIn;
  ZAxisTolerance = ZAxisToleranceIn;
  printTypp = false;
}

PoseToleranceType::~PoseToleranceType()
{
  delete XPointTolerance;
  delete YPointTolerance;
  delete ZPointTolerance;
  delete XAxisTolerance;
  delete ZAxisTolerance;
}

void PoseToleranceType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"PoseToleranceType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  if (XPointTolerance)
    {
      SPACESZERO;
      XFPRINTF "<XPointTolerance>");
      XPointTolerance->PRINTSELF;
      XFPRINTF "</XPointTolerance>\n");
    }
  if (YPointTolerance)
    {
      SPACESZERO;
      XFPRINTF "<YPointTolerance>");
      YPointTolerance->PRINTSELF;
      XFPRINTF "</YPointTolerance>\n");
    }
  if (ZPointTolerance)
    {
      SPACESZERO;
      XFPRINTF "<ZPointTolerance>");
      ZPointTolerance->PRINTSELF;
      XFPRINTF "</ZPointTolerance>\n");
    }
  if (XAxisTolerance)
    {
      SPACESZERO;
      XFPRINTF "<XAxisTolerance>");
      XAxisTolerance->PRINTSELF;
      XFPRINTF "</XAxisTolerance>\n");
    }
  if (ZAxisTolerance)
    {
      SPACESZERO;
      XFPRINTF "<ZAxisTolerance>");
      ZAxisTolerance->PRINTSELF;
      XFPRINTF "</ZAxisTolerance>\n");
    }
  SPACESMINUS;
}

/*********************************************************************/

/* class RotAccelAbsoluteType

*/

RotAccelAbsoluteType::RotAccelAbsoluteType() :
  RotAccelType()
{
  Setting = 0;
}

RotAccelAbsoluteType::RotAccelAbsoluteType(
 XmlID * NameIn,
 XmlDecimal * SettingIn) :
  RotAccelType(
    NameIn)
{
  Setting = SettingIn;
  printTypp = false;
}

RotAccelAbsoluteType::~RotAccelAbsoluteType()
{
  delete Setting;
}

void RotAccelAbsoluteType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"RotAccelAbsoluteType\"");
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
  XFPRINTF "<Setting>");
  Setting->PRINTSELF;
  XFPRINTF "</Setting>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class RotAccelRelativeType

*/

RotAccelRelativeType::RotAccelRelativeType() :
  RotAccelType()
{
  Fraction = 0;
}

RotAccelRelativeType::RotAccelRelativeType(
 XmlID * NameIn,
 FractionType * FractionIn) :
  RotAccelType(
    NameIn)
{
  Fraction = FractionIn;
  printTypp = false;
}

RotAccelRelativeType::~RotAccelRelativeType()
{
  delete Fraction;
}

void RotAccelRelativeType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"RotAccelRelativeType\"");
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
  XFPRINTF "<Fraction>");
  Fraction->PRINTSELF;
  XFPRINTF "</Fraction>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class RotAccelType

*/

RotAccelType::RotAccelType() :
  DataThingType() {}

RotAccelType::RotAccelType(
 XmlID * NameIn) :
  DataThingType(
    NameIn)
{
  printTypp = false;
}

RotAccelType::~RotAccelType() {}

void RotAccelType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"RotAccelType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  SPACESMINUS;
}

/*********************************************************************/

/* class RotSpeedAbsoluteType

*/

RotSpeedAbsoluteType::RotSpeedAbsoluteType() :
  RotSpeedType()
{
  Setting = 0;
}

RotSpeedAbsoluteType::RotSpeedAbsoluteType(
 XmlID * NameIn,
 XmlDecimal * SettingIn) :
  RotSpeedType(
    NameIn)
{
  Setting = SettingIn;
  printTypp = false;
}

RotSpeedAbsoluteType::~RotSpeedAbsoluteType()
{
  delete Setting;
}

void RotSpeedAbsoluteType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"RotSpeedAbsoluteType\"");
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
  XFPRINTF "<Setting>");
  Setting->PRINTSELF;
  XFPRINTF "</Setting>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class RotSpeedRelativeType

*/

RotSpeedRelativeType::RotSpeedRelativeType() :
  RotSpeedType()
{
  Fraction = 0;
}

RotSpeedRelativeType::RotSpeedRelativeType(
 XmlID * NameIn,
 FractionType * FractionIn) :
  RotSpeedType(
    NameIn)
{
  Fraction = FractionIn;
  printTypp = false;
}

RotSpeedRelativeType::~RotSpeedRelativeType()
{
  delete Fraction;
}

void RotSpeedRelativeType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"RotSpeedRelativeType\"");
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
  XFPRINTF "<Fraction>");
  Fraction->PRINTSELF;
  XFPRINTF "</Fraction>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class RotSpeedType

*/

RotSpeedType::RotSpeedType() :
  DataThingType() {}

RotSpeedType::RotSpeedType(
 XmlID * NameIn) :
  DataThingType(
    NameIn)
{
  printTypp = false;
}

RotSpeedType::~RotSpeedType() {}

void RotSpeedType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"RotSpeedType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  SPACESMINUS;
}

/*********************************************************************/

/* class RunProgramType

*/

RunProgramType::RunProgramType() :
  MiddleCommandType()
{
  ProgramText = 0;
}

RunProgramType::RunProgramType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 XmlString * ProgramTextIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  ProgramText = ProgramTextIn;
  printTypp = false;
}

RunProgramType::~RunProgramType()
{
  delete ProgramText;
}

void RunProgramType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"RunProgramType\"");
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
  XFPRINTF "<ProgramText>");
  ProgramText->PRINTSELF;
  XFPRINTF "</ProgramText>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class SetAngleUnitsType

*/

SetAngleUnitsType::SetAngleUnitsType() :
  MiddleCommandType()
{
  UnitName = 0;
}

SetAngleUnitsType::SetAngleUnitsType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 AngleUnitEnumType * UnitNameIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  UnitName = UnitNameIn;
  printTypp = false;
}

SetAngleUnitsType::~SetAngleUnitsType()
{
  delete UnitName;
}

void SetAngleUnitsType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"SetAngleUnitsType\"");
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
  XFPRINTF "<UnitName>");
  UnitName->PRINTSELF;
  XFPRINTF "</UnitName>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class SetEndEffectorParametersType

*/

SetEndEffectorParametersType::SetEndEffectorParametersType() :
  MiddleCommandType()
{
  ParameterSetting = 0;
}

SetEndEffectorParametersType::SetEndEffectorParametersType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 std::list<ParameterSettingType *> * ParameterSettingIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  ParameterSetting = ParameterSettingIn;
  printTypp = false;
}

SetEndEffectorParametersType::~SetEndEffectorParametersType()
{
  {
    std::list<ParameterSettingType *>::iterator iter;
    for (iter = ParameterSetting->begin();
         iter != ParameterSetting->end(); iter++)
      delete *iter;
  }
  delete ParameterSetting;
}

void SetEndEffectorParametersType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"SetEndEffectorParametersType\"");
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
  {
    std::list<ParameterSettingType *>::iterator iter;
    for (iter = ParameterSetting->begin(); iter != ParameterSetting->end(); iter++)
      {
        SPACESZERO;
        XFPRINTF "<ParameterSetting");
        (*iter)->PRINTSELF;
        SPACESZERO;
        XFPRINTF "</ParameterSetting>\n");
      }
  }
  SPACESMINUS;
}

/*********************************************************************/

/* class SetEndEffectorType

*/

SetEndEffectorType::SetEndEffectorType() :
  MiddleCommandType()
{
  Setting = 0;
}

SetEndEffectorType::SetEndEffectorType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 FractionType * SettingIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  Setting = SettingIn;
  printTypp = false;
}

SetEndEffectorType::~SetEndEffectorType()
{
  delete Setting;
}

void SetEndEffectorType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"SetEndEffectorType\"");
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
  XFPRINTF "<Setting>");
  Setting->PRINTSELF;
  XFPRINTF "</Setting>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class SetEndPoseToleranceType

*/

SetEndPoseToleranceType::SetEndPoseToleranceType() :
  MiddleCommandType()
{
  Tolerance = 0;
}

SetEndPoseToleranceType::SetEndPoseToleranceType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 PoseToleranceType * ToleranceIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  Tolerance = ToleranceIn;
  printTypp = false;
}

SetEndPoseToleranceType::~SetEndPoseToleranceType()
{
  delete Tolerance;
}

void SetEndPoseToleranceType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"SetEndPoseToleranceType\"");
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
  XFPRINTF "<Tolerance");
  Tolerance->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</Tolerance>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class SetForceUnitsType

*/

SetForceUnitsType::SetForceUnitsType() :
  MiddleCommandType()
{
  UnitName = 0;
}

SetForceUnitsType::SetForceUnitsType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 ForceUnitEnumType * UnitNameIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  UnitName = UnitNameIn;
  printTypp = false;
}

SetForceUnitsType::~SetForceUnitsType()
{
  delete UnitName;
}

void SetForceUnitsType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"SetForceUnitsType\"");
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
  XFPRINTF "<UnitName>");
  UnitName->PRINTSELF;
  XFPRINTF "</UnitName>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class SetIntermediatePoseToleranceType

*/

SetIntermediatePoseToleranceType::SetIntermediatePoseToleranceType() :
  MiddleCommandType()
{
  Tolerance = 0;
}

SetIntermediatePoseToleranceType::SetIntermediatePoseToleranceType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 PoseToleranceType * ToleranceIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  Tolerance = ToleranceIn;
  printTypp = false;
}

SetIntermediatePoseToleranceType::~SetIntermediatePoseToleranceType()
{
  delete Tolerance;
}

void SetIntermediatePoseToleranceType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"SetIntermediatePoseToleranceType\"");
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
  XFPRINTF "<Tolerance");
  Tolerance->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</Tolerance>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class SetLengthUnitsType

*/

SetLengthUnitsType::SetLengthUnitsType() :
  MiddleCommandType()
{
  UnitName = 0;
}

SetLengthUnitsType::SetLengthUnitsType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 LengthUnitEnumType * UnitNameIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  UnitName = UnitNameIn;
  printTypp = false;
}

SetLengthUnitsType::~SetLengthUnitsType()
{
  delete UnitName;
}

void SetLengthUnitsType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"SetLengthUnitsType\"");
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
  XFPRINTF "<UnitName>");
  UnitName->PRINTSELF;
  XFPRINTF "</UnitName>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class SetMotionCoordinationType

*/

SetMotionCoordinationType::SetMotionCoordinationType() :
  MiddleCommandType()
{
  Coordinated = 0;
}

SetMotionCoordinationType::SetMotionCoordinationType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 XmlBoolean * CoordinatedIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  Coordinated = CoordinatedIn;
  printTypp = false;
}

SetMotionCoordinationType::~SetMotionCoordinationType()
{
  delete Coordinated;
}

void SetMotionCoordinationType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"SetMotionCoordinationType\"");
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
  XFPRINTF "<Coordinated>");
  Coordinated->PRINTSELF;
  XFPRINTF "</Coordinated>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class SetRobotParametersType

*/

SetRobotParametersType::SetRobotParametersType() :
  MiddleCommandType()
{
  ParameterSetting = 0;
}

SetRobotParametersType::SetRobotParametersType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 std::list<ParameterSettingType *> * ParameterSettingIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  ParameterSetting = ParameterSettingIn;
  printTypp = false;
}

SetRobotParametersType::~SetRobotParametersType()
{
  {
    std::list<ParameterSettingType *>::iterator iter;
    for (iter = ParameterSetting->begin();
         iter != ParameterSetting->end(); iter++)
      delete *iter;
  }
  delete ParameterSetting;
}

void SetRobotParametersType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"SetRobotParametersType\"");
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
  {
    std::list<ParameterSettingType *>::iterator iter;
    for (iter = ParameterSetting->begin(); iter != ParameterSetting->end(); iter++)
      {
        SPACESZERO;
        XFPRINTF "<ParameterSetting");
        (*iter)->PRINTSELF;
        SPACESZERO;
        XFPRINTF "</ParameterSetting>\n");
      }
  }
  SPACESMINUS;
}

/*********************************************************************/

/* class SetRotAccelType

*/

SetRotAccelType::SetRotAccelType() :
  MiddleCommandType()
{
  RotAccel = 0;
}

SetRotAccelType::SetRotAccelType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 RotAccelType * RotAccelIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  RotAccel = RotAccelIn;
  printTypp = false;
}

SetRotAccelType::~SetRotAccelType()
{
  delete RotAccel;
}

void SetRotAccelType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"SetRotAccelType\"");
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
  XFPRINTF "<RotAccel");
  RotAccel->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</RotAccel>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class SetRotSpeedType

*/

SetRotSpeedType::SetRotSpeedType() :
  MiddleCommandType()
{
  RotSpeed = 0;
}

SetRotSpeedType::SetRotSpeedType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 RotSpeedType * RotSpeedIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  RotSpeed = RotSpeedIn;
  printTypp = false;
}

SetRotSpeedType::~SetRotSpeedType()
{
  delete RotSpeed;
}

void SetRotSpeedType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"SetRotSpeedType\"");
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
  XFPRINTF "<RotSpeed");
  RotSpeed->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</RotSpeed>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class SetTorqueUnitsType

*/

SetTorqueUnitsType::SetTorqueUnitsType() :
  MiddleCommandType()
{
  UnitName = 0;
}

SetTorqueUnitsType::SetTorqueUnitsType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 TorqueUnitEnumType * UnitNameIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  UnitName = UnitNameIn;
  printTypp = false;
}

SetTorqueUnitsType::~SetTorqueUnitsType()
{
  delete UnitName;
}

void SetTorqueUnitsType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"SetTorqueUnitsType\"");
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
  XFPRINTF "<UnitName>");
  UnitName->PRINTSELF;
  XFPRINTF "</UnitName>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class SetTransAccelType

*/

SetTransAccelType::SetTransAccelType() :
  MiddleCommandType()
{
  TransAccel = 0;
}

SetTransAccelType::SetTransAccelType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 TransAccelType * TransAccelIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  TransAccel = TransAccelIn;
  printTypp = false;
}

SetTransAccelType::~SetTransAccelType()
{
  delete TransAccel;
}

void SetTransAccelType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"SetTransAccelType\"");
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
  XFPRINTF "<TransAccel");
  TransAccel->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</TransAccel>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class SetTransSpeedType

*/

SetTransSpeedType::SetTransSpeedType() :
  MiddleCommandType()
{
  TransSpeed = 0;
}

SetTransSpeedType::SetTransSpeedType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 TransSpeedType * TransSpeedIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  TransSpeed = TransSpeedIn;
  printTypp = false;
}

SetTransSpeedType::~SetTransSpeedType()
{
  delete TransSpeed;
}

void SetTransSpeedType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"SetTransSpeedType\"");
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
  XFPRINTF "<TransSpeed");
  TransSpeed->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</TransSpeed>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class StopConditionEnumType

*/

StopConditionEnumType::StopConditionEnumType() :
  XmlToken() {}

StopConditionEnumType::StopConditionEnumType(
 const char * valIn) :
  XmlToken(valIn)
{
  if (!bad)
    bad = (strcmp(val.c_str(), "Immediate") &&
           strcmp(val.c_str(), "Fast") &&
           strcmp(val.c_str(), "Normal"));
}

StopConditionEnumType::~StopConditionEnumType() {}

bool StopConditionEnumType::StopConditionEnumTypeIsBad()
{
  return (strcmp(val.c_str(), "Immediate") &&
          strcmp(val.c_str(), "Fast") &&
          strcmp(val.c_str(), "Normal"));
}

void StopConditionEnumType::PRINTSELFDECL
{
  if (StopConditionEnumTypeIsBad())
    {
      fprintf(stderr, "bad StopConditionEnumType value, ");
      XmlToken::printBad(stderr);
      fprintf(stderr, " exiting\n");
      exit(1);
    }
  XmlToken::PRINTSELF;
}

/*********************************************************************/

/* class StopMotionType

*/

StopMotionType::StopMotionType() :
  MiddleCommandType()
{
  StopCondition = 0;
}

StopMotionType::StopMotionType(
 XmlID * NameIn,
 XmlPositiveInteger * CommandIDIn,
 StopConditionEnumType * StopConditionIn) :
  MiddleCommandType(
    NameIn,
    CommandIDIn)
{
  StopCondition = StopConditionIn;
  printTypp = false;
}

StopMotionType::~StopMotionType()
{
  delete StopCondition;
}

void StopMotionType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"StopMotionType\"");
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
  XFPRINTF "<StopCondition>");
  StopCondition->PRINTSELF;
  XFPRINTF "</StopCondition>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class TransAccelAbsoluteType

*/

TransAccelAbsoluteType::TransAccelAbsoluteType() :
  TransAccelType()
{
  Setting = 0;
}

TransAccelAbsoluteType::TransAccelAbsoluteType(
 XmlID * NameIn,
 XmlDecimal * SettingIn) :
  TransAccelType(
    NameIn)
{
  Setting = SettingIn;
  printTypp = false;
}

TransAccelAbsoluteType::~TransAccelAbsoluteType()
{
  delete Setting;
}

void TransAccelAbsoluteType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"TransAccelAbsoluteType\"");
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
  XFPRINTF "<Setting>");
  Setting->PRINTSELF;
  XFPRINTF "</Setting>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class TransAccelRelativeType

*/

TransAccelRelativeType::TransAccelRelativeType() :
  TransAccelType()
{
  Fraction = 0;
}

TransAccelRelativeType::TransAccelRelativeType(
 XmlID * NameIn,
 FractionType * FractionIn) :
  TransAccelType(
    NameIn)
{
  Fraction = FractionIn;
  printTypp = false;
}

TransAccelRelativeType::~TransAccelRelativeType()
{
  delete Fraction;
}

void TransAccelRelativeType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"TransAccelRelativeType\"");
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
  XFPRINTF "<Fraction>");
  Fraction->PRINTSELF;
  XFPRINTF "</Fraction>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class TransAccelType

*/

TransAccelType::TransAccelType() :
  DataThingType() {}

TransAccelType::TransAccelType(
 XmlID * NameIn) :
  DataThingType(
    NameIn)
{
  printTypp = false;
}

TransAccelType::~TransAccelType() {}

void TransAccelType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"TransAccelType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  SPACESMINUS;
}

/*********************************************************************/

/* class TransSpeedAbsoluteType

*/

TransSpeedAbsoluteType::TransSpeedAbsoluteType() :
  TransSpeedType()
{
  Setting = 0;
}

TransSpeedAbsoluteType::TransSpeedAbsoluteType(
 XmlID * NameIn,
 XmlDecimal * SettingIn) :
  TransSpeedType(
    NameIn)
{
  Setting = SettingIn;
  printTypp = false;
}

TransSpeedAbsoluteType::~TransSpeedAbsoluteType()
{
  delete Setting;
}

void TransSpeedAbsoluteType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"TransSpeedAbsoluteType\"");
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
  XFPRINTF "<Setting>");
  Setting->PRINTSELF;
  XFPRINTF "</Setting>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class TransSpeedRelativeType

*/

TransSpeedRelativeType::TransSpeedRelativeType() :
  TransSpeedType()
{
  Fraction = 0;
}

TransSpeedRelativeType::TransSpeedRelativeType(
 XmlID * NameIn,
 FractionType * FractionIn) :
  TransSpeedType(
    NameIn)
{
  Fraction = FractionIn;
  printTypp = false;
}

TransSpeedRelativeType::~TransSpeedRelativeType()
{
  delete Fraction;
}

void TransSpeedRelativeType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"TransSpeedRelativeType\"");
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
  XFPRINTF "<Fraction>");
  Fraction->PRINTSELF;
  XFPRINTF "</Fraction>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class TransSpeedType

*/

TransSpeedType::TransSpeedType() :
  DataThingType() {}

TransSpeedType::TransSpeedType(
 XmlID * NameIn) :
  DataThingType(
    NameIn)
{
  printTypp = false;
}

TransSpeedType::~TransSpeedType() {}

void TransSpeedType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"TransSpeedType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  SPACESMINUS;
}

/*********************************************************************/

