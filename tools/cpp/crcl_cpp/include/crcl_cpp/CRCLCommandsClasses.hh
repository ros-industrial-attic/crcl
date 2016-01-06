/*********************************************************************/

#ifndef CRCLCOMMANDS_HH
#define CRCLCOMMANDS_HH
#include <stdio.h>
#include <list>
#include <xml_parser_generator/xmlSchemaInstance.hh>
#include "crcl_cpp/DataPrimitivesClasses.hh"

/*********************************************************************/

class ActuateJointType;
class ActuateJointsType;
class CRCLCommandType;
class CloseToolChangerType;
class ConfigureJointReportType;
class ConfigureJointReportsType;
class DwellType;
class EndCanonType;
class GetStatusType;
class InitCanonType;
class JointDetailsType;
class JointForceTorqueType;
class JointSpeedAccelType;
class MessageType;
class MiddleCommandType;
class MoveScrewType;
class MoveThroughToType;
class MoveToType;
class OpenToolChangerType;
class ParameterSettingType;
class PoseAndSetType;
class PoseToleranceType;
class RotAccelAbsoluteType;
class RotAccelRelativeType;
class RotAccelType;
class RotSpeedAbsoluteType;
class RotSpeedRelativeType;
class RotSpeedType;
class RunProgramType;
class SetAngleUnitsType;
class SetEndEffectorParametersType;
class SetEndEffectorType;
class SetEndPoseToleranceType;
class SetForceUnitsType;
class SetIntermediatePoseToleranceType;
class SetLengthUnitsType;
class SetMotionCoordinationType;
class SetRobotParametersType;
class SetRotAccelType;
class SetRotSpeedType;
class SetTorqueUnitsType;
class SetTransAccelType;
class SetTransSpeedType;
class StopConditionEnumType;
class StopMotionType;
class TransAccelAbsoluteType;
class TransAccelRelativeType;
class TransAccelType;
class TransSpeedAbsoluteType;
class TransSpeedRelativeType;
class TransSpeedType;

/*********************************************************************/
/*********************************************************************/

class ActuateJointType :
  public DataThingType
{
public:
  ActuateJointType();
  ActuateJointType(
    XmlID * NameIn,
    XmlPositiveInteger * JointNumberIn,
    XmlDecimal * JointPositionIn,
    JointDetailsType * JointDetailsIn);
  ~ActuateJointType();
  void PRINTSELFDECL;

  XmlPositiveInteger * JointNumber;
  XmlDecimal * JointPosition;
  JointDetailsType * JointDetails;

  bool printTypp;
};

/*********************************************************************/

class CRCLCommandType :
  public DataThingType
{
public:
  CRCLCommandType();
  CRCLCommandType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn);
  ~CRCLCommandType();
  void PRINTSELFDECL;

  XmlPositiveInteger * CommandID;

  bool printTypp;
};

/*********************************************************************/

class ConfigureJointReportType :
  public DataThingType
{
public:
  ConfigureJointReportType();
  ConfigureJointReportType(
    XmlID * NameIn,
    XmlPositiveInteger * JointNumberIn,
    XmlBoolean * ReportPositionIn,
    XmlBoolean * ReportTorqueOrForceIn,
    XmlBoolean * ReportVelocityIn);
  ~ConfigureJointReportType();
  void PRINTSELFDECL;

  XmlPositiveInteger * JointNumber;
  XmlBoolean * ReportPosition;
  XmlBoolean * ReportTorqueOrForce;
  XmlBoolean * ReportVelocity;

  bool printTypp;
};

/*********************************************************************/

class EndCanonType :
  public CRCLCommandType
{
public:
  EndCanonType();
  EndCanonType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn);
  ~EndCanonType();
  void PRINTSELFDECL;


  bool printTypp;
};

/*********************************************************************/

class InitCanonType :
  public CRCLCommandType
{
public:
  InitCanonType();
  InitCanonType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn);
  ~InitCanonType();
  void PRINTSELFDECL;


  bool printTypp;
};

/*********************************************************************/

class JointDetailsType :
  public DataThingType
{
public:
  JointDetailsType();
  JointDetailsType(
    XmlID * NameIn);
  ~JointDetailsType();
  void PRINTSELFDECL;


  bool printTypp;
};

/*********************************************************************/

class JointForceTorqueType :
  public JointDetailsType
{
public:
  JointForceTorqueType();
  JointForceTorqueType(
    XmlID * NameIn,
    XmlDecimal * SettingIn,
    XmlDecimal * ChangeRateIn);
  ~JointForceTorqueType();
  void PRINTSELFDECL;

  XmlDecimal * Setting;
  XmlDecimal * ChangeRate;

  bool printTypp;
};

/*********************************************************************/

class JointSpeedAccelType :
  public JointDetailsType
{
public:
  JointSpeedAccelType();
  JointSpeedAccelType(
    XmlID * NameIn,
    XmlDecimal * JointSpeedIn,
    XmlDecimal * JointAccelIn);
  ~JointSpeedAccelType();
  void PRINTSELFDECL;

  XmlDecimal * JointSpeed;
  XmlDecimal * JointAccel;

  bool printTypp;
};

/*********************************************************************/

class MiddleCommandType :
  public CRCLCommandType
{
public:
  MiddleCommandType();
  MiddleCommandType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn);
  ~MiddleCommandType();
  void PRINTSELFDECL;


  bool printTypp;
};

/*********************************************************************/

class MoveScrewType :
  public MiddleCommandType
{
public:
  MoveScrewType();
  MoveScrewType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    PoseOnlyLocationType * StartPositionIn,
    PointType * AxisPointIn,
    XmlDecimal * AxialDistanceFreeIn,
    XmlDecimal * AxialDistanceScrewIn,
    XmlDecimal * TurnIn);
  ~MoveScrewType();
  void PRINTSELFDECL;

  PoseOnlyLocationType * StartPosition;
  PointType * AxisPoint;
  XmlDecimal * AxialDistanceFree;
  XmlDecimal * AxialDistanceScrew;
  XmlDecimal * Turn;

  bool printTypp;
};

/*********************************************************************/

class MoveThroughToType :
  public MiddleCommandType
{
public:
  MoveThroughToType();
  MoveThroughToType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    XmlBoolean * MoveStraightIn,
    std::list<PoseOnlyLocationType *> * WaypointIn,
    XmlPositiveInteger * NumPositionsIn);
  ~MoveThroughToType();
  void PRINTSELFDECL;

  XmlBoolean * MoveStraight;
  std::list<PoseOnlyLocationType *> * Waypoint;
  XmlPositiveInteger * NumPositions;

  bool printTypp;
};

/*********************************************************************/

class MoveToType :
  public MiddleCommandType
{
public:
  MoveToType();
  MoveToType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    XmlBoolean * MoveStraightIn,
    PoseOnlyLocationType * EndPositionIn);
  ~MoveToType();
  void PRINTSELFDECL;

  XmlBoolean * MoveStraight;
  PoseOnlyLocationType * EndPosition;

  bool printTypp;
};

/*********************************************************************/

class OpenToolChangerType :
  public MiddleCommandType
{
public:
  OpenToolChangerType();
  OpenToolChangerType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn);
  ~OpenToolChangerType();
  void PRINTSELFDECL;


  bool printTypp;
};

/*********************************************************************/

class ParameterSettingType :
  public DataThingType
{
public:
  ParameterSettingType();
  ParameterSettingType(
    XmlID * NameIn,
    XmlToken * ParameterNameIn,
    XmlToken * ParameterValueIn);
  ~ParameterSettingType();
  void PRINTSELFDECL;

  XmlToken * ParameterName;
  XmlToken * ParameterValue;

  bool printTypp;
};

/*********************************************************************/

class PoseAndSetType :
  public PoseOnlyLocationType
{
public:
  PoseAndSetType();
  PoseAndSetType(
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
    PoseToleranceType * ToleranceIn);
  ~PoseAndSetType();
  void PRINTSELFDECL;

  XmlBoolean * Coordinated;
  TransSpeedType * TransSpeed;
  RotSpeedType * RotSpeed;
  TransAccelType * TransAccel;
  RotAccelType * RotAccel;
  PoseToleranceType * Tolerance;

  bool printTypp;
};

/*********************************************************************/

class PoseToleranceType :
  public DataThingType
{
public:
  PoseToleranceType();
  PoseToleranceType(
    XmlID * NameIn,
    XmlDecimal * XPointToleranceIn,
    XmlDecimal * YPointToleranceIn,
    XmlDecimal * ZPointToleranceIn,
    XmlDecimal * XAxisToleranceIn,
    XmlDecimal * ZAxisToleranceIn);
  ~PoseToleranceType();
  void PRINTSELFDECL;

  XmlDecimal * XPointTolerance;
  XmlDecimal * YPointTolerance;
  XmlDecimal * ZPointTolerance;
  XmlDecimal * XAxisTolerance;
  XmlDecimal * ZAxisTolerance;

  bool printTypp;
};

/*********************************************************************/

class RotAccelType :
  public DataThingType
{
public:
  RotAccelType();
  RotAccelType(
    XmlID * NameIn);
  ~RotAccelType();
  void PRINTSELFDECL;


  bool printTypp;
};

/*********************************************************************/

class RotSpeedType :
  public DataThingType
{
public:
  RotSpeedType();
  RotSpeedType(
    XmlID * NameIn);
  ~RotSpeedType();
  void PRINTSELFDECL;


  bool printTypp;
};

/*********************************************************************/

class RunProgramType :
  public MiddleCommandType
{
public:
  RunProgramType();
  RunProgramType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    XmlString * ProgramTextIn);
  ~RunProgramType();
  void PRINTSELFDECL;

  XmlString * ProgramText;

  bool printTypp;
};

/*********************************************************************/

class SetAngleUnitsType :
  public MiddleCommandType
{
public:
  SetAngleUnitsType();
  SetAngleUnitsType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    AngleUnitEnumType * UnitNameIn);
  ~SetAngleUnitsType();
  void PRINTSELFDECL;

  AngleUnitEnumType * UnitName;

  bool printTypp;
};

/*********************************************************************/

class SetEndEffectorParametersType :
  public MiddleCommandType
{
public:
  SetEndEffectorParametersType();
  SetEndEffectorParametersType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    std::list<ParameterSettingType *> * ParameterSettingIn);
  ~SetEndEffectorParametersType();
  void PRINTSELFDECL;

  std::list<ParameterSettingType *> * ParameterSetting;

  bool printTypp;
};

/*********************************************************************/

class SetEndEffectorType :
  public MiddleCommandType
{
public:
  SetEndEffectorType();
  SetEndEffectorType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    FractionType * SettingIn);
  ~SetEndEffectorType();
  void PRINTSELFDECL;

  FractionType * Setting;

  bool printTypp;
};

/*********************************************************************/

class SetEndPoseToleranceType :
  public MiddleCommandType
{
public:
  SetEndPoseToleranceType();
  SetEndPoseToleranceType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    PoseToleranceType * ToleranceIn);
  ~SetEndPoseToleranceType();
  void PRINTSELFDECL;

  PoseToleranceType * Tolerance;

  bool printTypp;
};

/*********************************************************************/

class SetForceUnitsType :
  public MiddleCommandType
{
public:
  SetForceUnitsType();
  SetForceUnitsType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    ForceUnitEnumType * UnitNameIn);
  ~SetForceUnitsType();
  void PRINTSELFDECL;

  ForceUnitEnumType * UnitName;

  bool printTypp;
};

/*********************************************************************/

class SetIntermediatePoseToleranceType :
  public MiddleCommandType
{
public:
  SetIntermediatePoseToleranceType();
  SetIntermediatePoseToleranceType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    PoseToleranceType * ToleranceIn);
  ~SetIntermediatePoseToleranceType();
  void PRINTSELFDECL;

  PoseToleranceType * Tolerance;

  bool printTypp;
};

/*********************************************************************/

class SetLengthUnitsType :
  public MiddleCommandType
{
public:
  SetLengthUnitsType();
  SetLengthUnitsType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    LengthUnitEnumType * UnitNameIn);
  ~SetLengthUnitsType();
  void PRINTSELFDECL;

  LengthUnitEnumType * UnitName;

  bool printTypp;
};

/*********************************************************************/

class SetMotionCoordinationType :
  public MiddleCommandType
{
public:
  SetMotionCoordinationType();
  SetMotionCoordinationType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    XmlBoolean * CoordinatedIn);
  ~SetMotionCoordinationType();
  void PRINTSELFDECL;

  XmlBoolean * Coordinated;

  bool printTypp;
};

/*********************************************************************/

class SetRobotParametersType :
  public MiddleCommandType
{
public:
  SetRobotParametersType();
  SetRobotParametersType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    std::list<ParameterSettingType *> * ParameterSettingIn);
  ~SetRobotParametersType();
  void PRINTSELFDECL;

  std::list<ParameterSettingType *> * ParameterSetting;

  bool printTypp;
};

/*********************************************************************/

class SetRotAccelType :
  public MiddleCommandType
{
public:
  SetRotAccelType();
  SetRotAccelType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    RotAccelType * RotAccelIn);
  ~SetRotAccelType();
  void PRINTSELFDECL;

  RotAccelType * RotAccel;

  bool printTypp;
};

/*********************************************************************/

class SetRotSpeedType :
  public MiddleCommandType
{
public:
  SetRotSpeedType();
  SetRotSpeedType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    RotSpeedType * RotSpeedIn);
  ~SetRotSpeedType();
  void PRINTSELFDECL;

  RotSpeedType * RotSpeed;

  bool printTypp;
};

/*********************************************************************/

class SetTorqueUnitsType :
  public MiddleCommandType
{
public:
  SetTorqueUnitsType();
  SetTorqueUnitsType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    TorqueUnitEnumType * UnitNameIn);
  ~SetTorqueUnitsType();
  void PRINTSELFDECL;

  TorqueUnitEnumType * UnitName;

  bool printTypp;
};

/*********************************************************************/

class SetTransAccelType :
  public MiddleCommandType
{
public:
  SetTransAccelType();
  SetTransAccelType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    TransAccelType * TransAccelIn);
  ~SetTransAccelType();
  void PRINTSELFDECL;

  TransAccelType * TransAccel;

  bool printTypp;
};

/*********************************************************************/

class SetTransSpeedType :
  public MiddleCommandType
{
public:
  SetTransSpeedType();
  SetTransSpeedType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    TransSpeedType * TransSpeedIn);
  ~SetTransSpeedType();
  void PRINTSELFDECL;

  TransSpeedType * TransSpeed;

  bool printTypp;
};

/*********************************************************************/

class StopConditionEnumType :
  public XmlToken
{
public:
  StopConditionEnumType();
  StopConditionEnumType(
    const char * valIn);
  ~StopConditionEnumType();
  bool StopConditionEnumTypeIsBad();
  void PRINTSELFDECL;
};

/*********************************************************************/

class StopMotionType :
  public MiddleCommandType
{
public:
  StopMotionType();
  StopMotionType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    StopConditionEnumType * StopConditionIn);
  ~StopMotionType();
  void PRINTSELFDECL;

  StopConditionEnumType * StopCondition;

  bool printTypp;
};

/*********************************************************************/

class TransAccelType :
  public DataThingType
{
public:
  TransAccelType();
  TransAccelType(
    XmlID * NameIn);
  ~TransAccelType();
  void PRINTSELFDECL;


  bool printTypp;
};

/*********************************************************************/

class TransSpeedType :
  public DataThingType
{
public:
  TransSpeedType();
  TransSpeedType(
    XmlID * NameIn);
  ~TransSpeedType();
  void PRINTSELFDECL;


  bool printTypp;
};

/*********************************************************************/

class ActuateJointsType :
  public MiddleCommandType
{
public:
  ActuateJointsType();
  ActuateJointsType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    std::list<ActuateJointType *> * ActuateJointIn);
  ~ActuateJointsType();
  void PRINTSELFDECL;

  std::list<ActuateJointType *> * ActuateJoint;

  bool printTypp;
};

/*********************************************************************/

class CloseToolChangerType :
  public MiddleCommandType
{
public:
  CloseToolChangerType();
  CloseToolChangerType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn);
  ~CloseToolChangerType();
  void PRINTSELFDECL;


  bool printTypp;
};

/*********************************************************************/

class ConfigureJointReportsType :
  public MiddleCommandType
{
public:
  ConfigureJointReportsType();
  ConfigureJointReportsType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    XmlBoolean * ResetAllIn,
    std::list<ConfigureJointReportType *> * ConfigureJointReportIn);
  ~ConfigureJointReportsType();
  void PRINTSELFDECL;

  XmlBoolean * ResetAll;
  std::list<ConfigureJointReportType *> * ConfigureJointReport;

  bool printTypp;
};

/*********************************************************************/

class DwellType :
  public MiddleCommandType
{
public:
  DwellType();
  DwellType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    XmlDecimal * DwellTimeIn);
  ~DwellType();
  void PRINTSELFDECL;

  XmlDecimal * DwellTime;

  bool printTypp;
};

/*********************************************************************/

class GetStatusType :
  public MiddleCommandType
{
public:
  GetStatusType();
  GetStatusType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn);
  ~GetStatusType();
  void PRINTSELFDECL;


  bool printTypp;
};

/*********************************************************************/

class MessageType :
  public MiddleCommandType
{
public:
  MessageType();
  MessageType(
    XmlID * NameIn,
    XmlPositiveInteger * CommandIDIn,
    XmlString * MessageIn);
  ~MessageType();
  void PRINTSELFDECL;

  XmlString * Message;

  bool printTypp;
};

/*********************************************************************/

class RotAccelAbsoluteType :
  public RotAccelType
{
public:
  RotAccelAbsoluteType();
  RotAccelAbsoluteType(
    XmlID * NameIn,
    XmlDecimal * SettingIn);
  ~RotAccelAbsoluteType();
  void PRINTSELFDECL;

  XmlDecimal * Setting;

  bool printTypp;
};

/*********************************************************************/

class RotAccelRelativeType :
  public RotAccelType
{
public:
  RotAccelRelativeType();
  RotAccelRelativeType(
    XmlID * NameIn,
    FractionType * FractionIn);
  ~RotAccelRelativeType();
  void PRINTSELFDECL;

  FractionType * Fraction;

  bool printTypp;
};

/*********************************************************************/

class RotSpeedAbsoluteType :
  public RotSpeedType
{
public:
  RotSpeedAbsoluteType();
  RotSpeedAbsoluteType(
    XmlID * NameIn,
    XmlDecimal * SettingIn);
  ~RotSpeedAbsoluteType();
  void PRINTSELFDECL;

  XmlDecimal * Setting;

  bool printTypp;
};

/*********************************************************************/

class RotSpeedRelativeType :
  public RotSpeedType
{
public:
  RotSpeedRelativeType();
  RotSpeedRelativeType(
    XmlID * NameIn,
    FractionType * FractionIn);
  ~RotSpeedRelativeType();
  void PRINTSELFDECL;

  FractionType * Fraction;

  bool printTypp;
};

/*********************************************************************/

class TransAccelAbsoluteType :
  public TransAccelType
{
public:
  TransAccelAbsoluteType();
  TransAccelAbsoluteType(
    XmlID * NameIn,
    XmlDecimal * SettingIn);
  ~TransAccelAbsoluteType();
  void PRINTSELFDECL;

  XmlDecimal * Setting;

  bool printTypp;
};

/*********************************************************************/

class TransAccelRelativeType :
  public TransAccelType
{
public:
  TransAccelRelativeType();
  TransAccelRelativeType(
    XmlID * NameIn,
    FractionType * FractionIn);
  ~TransAccelRelativeType();
  void PRINTSELFDECL;

  FractionType * Fraction;

  bool printTypp;
};

/*********************************************************************/

class TransSpeedAbsoluteType :
  public TransSpeedType
{
public:
  TransSpeedAbsoluteType();
  TransSpeedAbsoluteType(
    XmlID * NameIn,
    XmlDecimal * SettingIn);
  ~TransSpeedAbsoluteType();
  void PRINTSELFDECL;

  XmlDecimal * Setting;

  bool printTypp;
};

/*********************************************************************/

class TransSpeedRelativeType :
  public TransSpeedType
{
public:
  TransSpeedRelativeType();
  TransSpeedRelativeType(
    XmlID * NameIn,
    FractionType * FractionIn);
  ~TransSpeedRelativeType();
  void PRINTSELFDECL;

  FractionType * Fraction;

  bool printTypp;
};

/*********************************************************************/

#endif // CRCLCOMMANDS_HH
