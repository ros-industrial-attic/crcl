%{

#include <stdio.h>             // for stderr
#include <string.h>            // for strcat
#include <stdlib.h>            // for malloc, free
#ifdef OWL
#include "owlCRCLCommandInstanceClasses.hh"
#else
#include "crcl_cpp/CRCLCommandInstanceClasses.hh"
#endif

#define YYERROR_VERBOSE
#define YYDEBUG 1

CRCLCommandInstanceFile * CRCLCommandInstanceTree; // the parse tree

extern int yylex();
int yyReadData = 0;
int yyReadDataList = 0;

int yyerror(const char * s);

%}

%union {
  SchemaLocation *                    SchemaLocationVal;
  XmlHeaderForCRCLCommandInstance *   XmlHeaderForCRCLCommandInstanceVal;
  XmlVersion *                        XmlVersionVal;
  int *                               iVal;
  char *                              sVal;
  XmlBoolean *                        XmlBooleanVal;
  XmlDecimal *                        XmlDecimalVal;
  XmlID *                             XmlIDVal;
  XmlPositiveInteger *                XmlPositiveIntegerVal;
  XmlString *                         XmlStringVal;
  XmlToken *                          XmlTokenVal;

  CRCLCommandInstanceFile *           CRCLCommandInstanceFileVal;

  ActuateJointType *                  ActuateJointTypeVal;
  ActuateJointsType *                 ActuateJointsTypeVal;
  AngleUnitEnumType *                 AngleUnitEnumTypeVal;
  CRCLCommandInstanceType *           CRCLCommandInstanceTypeVal;
  CRCLCommandType *                   CRCLCommandTypeVal;
  CloseToolChangerType *              CloseToolChangerTypeVal;
  ConfigureJointReportType *          ConfigureJointReportTypeVal;
  ConfigureJointReportsType *         ConfigureJointReportsTypeVal;
  DwellType *                         DwellTypeVal;
  EndCanonType *                      EndCanonTypeVal;
  ForceUnitEnumType *                 ForceUnitEnumTypeVal;
  FractionType *                      FractionTypeVal;
  GetStatusType *                     GetStatusTypeVal;
  InitCanonType *                     InitCanonTypeVal;
  JointDetailsType *                  JointDetailsTypeVal;
  JointForceTorqueType *              JointForceTorqueTypeVal;
  JointSpeedAccelType *               JointSpeedAccelTypeVal;
  LengthUnitEnumType *                LengthUnitEnumTypeVal;
  std::list<ActuateJointType *> *     ListActuateJointTypeVal;
  std::list<ConfigureJointReportType *> * ListConfigureJointReportTypeVal;
  std::list<ParameterSettingType *> * ListParameterSettingTypeVal;
  std::list<PoseType *> *             ListPoseTypeVal;
  MessageType *                       MessageTypeVal;
  MiddleCommandType *                 MiddleCommandTypeVal;
  MoveScrewType *                     MoveScrewTypeVal;
  MoveThroughToType *                 MoveThroughToTypeVal;
  MoveToType *                        MoveToTypeVal;
  OpenToolChangerType *               OpenToolChangerTypeVal;
  ParameterSettingType *              ParameterSettingTypeVal;
  PointType *                         PointTypeVal;
  PoseAndSetType *                    PoseAndSetTypeVal;
  PoseToleranceType *                 PoseToleranceTypeVal;
  PoseType *                          PoseTypeVal;
  RotAccelAbsoluteType *              RotAccelAbsoluteTypeVal;
  RotAccelRelativeType *              RotAccelRelativeTypeVal;
  RotAccelType *                      RotAccelTypeVal;
  RotSpeedAbsoluteType *              RotSpeedAbsoluteTypeVal;
  RotSpeedRelativeType *              RotSpeedRelativeTypeVal;
  RotSpeedType *                      RotSpeedTypeVal;
  RunProgramType *                    RunProgramTypeVal;
  SetAngleUnitsType *                 SetAngleUnitsTypeVal;
  SetEndEffectorParametersType *      SetEndEffectorParametersTypeVal;
  SetEndEffectorType *                SetEndEffectorTypeVal;
  SetEndPoseToleranceType *           SetEndPoseToleranceTypeVal;
  SetForceUnitsType *                 SetForceUnitsTypeVal;
  SetIntermediatePoseToleranceType *  SetIntermediatePoseToleranceTypeVal;
  SetLengthUnitsType *                SetLengthUnitsTypeVal;
  SetMotionCoordinationType *         SetMotionCoordinationTypeVal;
  SetRobotParametersType *            SetRobotParametersTypeVal;
  SetRotAccelType *                   SetRotAccelTypeVal;
  SetRotSpeedType *                   SetRotSpeedTypeVal;
  SetTorqueUnitsType *                SetTorqueUnitsTypeVal;
  SetTransAccelType *                 SetTransAccelTypeVal;
  SetTransSpeedType *                 SetTransSpeedTypeVal;
  StopConditionEnumType *             StopConditionEnumTypeVal;
  StopMotionType *                    StopMotionTypeVal;
  TorqueUnitEnumType *                TorqueUnitEnumTypeVal;
  TransAccelAbsoluteType *            TransAccelAbsoluteTypeVal;
  TransAccelRelativeType *            TransAccelRelativeTypeVal;
  TransAccelType *                    TransAccelTypeVal;
  TransSpeedAbsoluteType *            TransSpeedAbsoluteTypeVal;
  TransSpeedRelativeType *            TransSpeedRelativeTypeVal;
  TransSpeedType *                    TransSpeedTypeVal;
  VectorType *                        VectorTypeVal;
}

%type <SchemaLocationVal>             y_SchemaLocation
%type <XmlHeaderForCRCLCommandInstanceVal> y_XmlHeaderForCRCLCommandInstance
%type <XmlVersionVal>                 y_XmlVersion
%type <CRCLCommandInstanceFileVal>    y_CRCLCommandInstanceFile
%type <XmlBooleanVal>                 y_XmlBoolean
%type <XmlDecimalVal>                 y_XmlDecimal
%type <XmlIDVal>                      y_XmlID
%type <XmlPositiveIntegerVal>         y_XmlPositiveInteger
%type <XmlStringVal>                  y_XmlString
%type <XmlTokenVal>                   y_XmlToken

%type <ActuateJointTypeVal>           y_ActuateJointType
%type <ActuateJointTypeVal>           y_ActuateJoint_ActuateJointType_1_u
%type <VectorTypeVal>                 y_AngularVelocity_VectorType
%type <XmlDecimalVal>                 y_AxialDistanceFree_XmlDecimal_0
%type <XmlDecimalVal>                 y_AxialDistanceScrew_XmlDecimal
%type <PointTypeVal>                  y_AxisPoint_PointType_0
%type <CRCLCommandInstanceTypeVal>    y_CRCLCommandInstanceType
%type <CRCLCommandTypeVal>            y_CRCLCommandTypeAny
%type <CRCLCommandTypeVal>            y_CRCLCommand_CRCLCommandType
%type <XmlDecimalVal>                 y_ChangeRate_XmlDecimal_0
%type <XmlPositiveIntegerVal>         y_CommandID_XmlPositiveInteger
%type <ConfigureJointReportTypeVal>   y_ConfigureJointReportType
%type <ConfigureJointReportTypeVal>   y_ConfigureJointReport_Configure1002
%type <XmlBooleanVal>                 y_Coordinated_XmlBoolean
%type <XmlDecimalVal>                 y_DwellTime_XmlDecimal
%type <PoseTypeVal>                   y_EndPosition_PoseType
%type <VectorTypeVal>                 y_Force_VectorType
%type <FractionTypeVal>               y_Fraction_FractionType
%type <XmlDecimalVal>                 y_I_XmlDecimal
%type <XmlDecimalVal>                 y_J_XmlDecimal
%type <XmlDecimalVal>                 y_JointAccel_XmlDecimal_0
%type <JointDetailsTypeVal>           y_JointDetailsTypeAny
%type <JointDetailsTypeVal>           y_JointDetails_JointDetailsType
%type <XmlPositiveIntegerVal>         y_JointNumber_XmlPositiveInteger
%type <XmlDecimalVal>                 y_JointPosition_XmlDecimal
%type <XmlDecimalVal>                 y_JointSpeed_XmlDecimal_0
%type <XmlDecimalVal>                 y_K_XmlDecimal
%type <VectorTypeVal>                 y_LinearVelocity_VectorType
%type <ListActuateJointTypeVal>       y_ListActuateJoint_ActuateJointType_1_u
%type <ListConfigureJointReportTypeVal> y_ListConfigureJointReport_Configure1002
%type <ListParameterSettingTypeVal>   y_ListParameterSetting_ParameterSett1003
%type <ListPoseTypeVal>               y_ListWaypoint_PoseType_2_u
%type <ListPoseTypeVal>               y_ListWaypoint_PoseType_2_u_Check
%type <XmlStringVal>                  y_Message_XmlString
%type <VectorTypeVal>                 y_Moment_VectorType
%type <XmlBooleanVal>                 y_MoveStraight_XmlBoolean
%type <XmlIDVal>                      y_Name_XmlID_0
%type <XmlPositiveIntegerVal>         y_NumPositions_XmlPositiveInteger
%type <XmlTokenVal>                   y_ParameterName_XmlToken
%type <ParameterSettingTypeVal>       y_ParameterSettingType
%type <ParameterSettingTypeVal>       y_ParameterSetting_ParameterSett1003
%type <XmlTokenVal>                   y_ParameterValue_XmlToken
%type <PointTypeVal>                  y_PointType
%type <PointTypeVal>                  y_Point_PointType
%type <PoseToleranceTypeVal>          y_PoseToleranceType
%type <PoseTypeVal>                   y_PoseType
%type <PoseTypeVal>                   y_PoseTypeAny
%type <XmlStringVal>                  y_ProgramText_XmlString
%type <XmlBooleanVal>                 y_ReportPosition_XmlBoolean
%type <XmlBooleanVal>                 y_ReportTorqueOrForce_XmlBoolean
%type <XmlBooleanVal>                 y_ReportVelocity_XmlBoolean
%type <XmlBooleanVal>                 y_ResetAll_XmlBoolean
%type <RotAccelTypeVal>               y_RotAccelTypeAny
%type <RotAccelTypeVal>               y_RotAccel_RotAccelType
%type <RotAccelTypeVal>               y_RotAccel_RotAccelType_0
%type <RotSpeedTypeVal>               y_RotSpeedTypeAny
%type <RotSpeedTypeVal>               y_RotSpeed_RotSpeedType
%type <RotSpeedTypeVal>               y_RotSpeed_RotSpeedType_0
%type <FractionTypeVal>               y_Setting_FractionType
%type <XmlDecimalVal>                 y_Setting_XmlDecimal
%type <XmlDecimalVal>                 y_Setting_XmlDecimal_0
%type <PoseTypeVal>                   y_StartPosition_PoseType_0
%type <StopConditionEnumTypeVal>      y_StopCondition_StopConditionEnumType
%type <PoseToleranceTypeVal>          y_Tolerance_PoseToleranceType
%type <PoseToleranceTypeVal>          y_Tolerance_PoseToleranceType_0
%type <TransAccelTypeVal>             y_TransAccelTypeAny
%type <TransAccelTypeVal>             y_TransAccel_TransAccelType
%type <TransAccelTypeVal>             y_TransAccel_TransAccelType_0
%type <TransSpeedTypeVal>             y_TransSpeedTypeAny
%type <TransSpeedTypeVal>             y_TransSpeed_TransSpeedType
%type <TransSpeedTypeVal>             y_TransSpeed_TransSpeedType_0
%type <XmlDecimalVal>                 y_Turn_XmlDecimal
%type <AngleUnitEnumTypeVal>          y_UnitName_AngleUnitEnumType
%type <ForceUnitEnumTypeVal>          y_UnitName_ForceUnitEnumType
%type <LengthUnitEnumTypeVal>         y_UnitName_LengthUnitEnumType
%type <TorqueUnitEnumTypeVal>         y_UnitName_TorqueUnitEnumType
%type <VectorTypeVal>                 y_VectorType
%type <PoseTypeVal>                   y_Waypoint_PoseType_2_u
%type <XmlDecimalVal>                 y_XAxisTolerance_XmlDecimal_0
%type <VectorTypeVal>                 y_XAxis_VectorType
%type <XmlDecimalVal>                 y_XPointTolerance_XmlDecimal_0
%type <XmlDecimalVal>                 y_X_XmlDecimal
%type <XmlDecimalVal>                 y_YPointTolerance_XmlDecimal_0
%type <XmlDecimalVal>                 y_Y_XmlDecimal
%type <XmlDecimalVal>                 y_ZAxisTolerance_XmlDecimal_0
%type <VectorTypeVal>                 y_ZAxis_VectorType
%type <XmlDecimalVal>                 y_ZPointTolerance_XmlDecimal_0
%type <XmlDecimalVal>                 y_Z_XmlDecimal
%type <ActuateJointsTypeVal>          y_x_ActuateJointsType
%type <CloseToolChangerTypeVal>       y_x_CloseToolChangerType
%type <ConfigureJointReportsTypeVal>  y_x_ConfigureJointReportsType
%type <DwellTypeVal>                  y_x_DwellType
%type <EndCanonTypeVal>               y_x_EndCanonType
%type <GetStatusTypeVal>              y_x_GetStatusType
%type <InitCanonTypeVal>              y_x_InitCanonType
%type <JointForceTorqueTypeVal>       y_x_JointForceTorqueType
%type <JointSpeedAccelTypeVal>        y_x_JointSpeedAccelType
%type <MessageTypeVal>                y_x_MessageType
%type <MoveScrewTypeVal>              y_x_MoveScrewType
%type <MoveThroughToTypeVal>          y_x_MoveThroughToType
%type <MoveToTypeVal>                 y_x_MoveToType
%type <OpenToolChangerTypeVal>        y_x_OpenToolChangerType
%type <PoseAndSetTypeVal>             y_x_PoseAndSetType
%type <RotAccelAbsoluteTypeVal>       y_x_RotAccelAbsoluteType
%type <RotAccelRelativeTypeVal>       y_x_RotAccelRelativeType
%type <RotSpeedAbsoluteTypeVal>       y_x_RotSpeedAbsoluteType
%type <RotSpeedRelativeTypeVal>       y_x_RotSpeedRelativeType
%type <RunProgramTypeVal>             y_x_RunProgramType
%type <SetAngleUnitsTypeVal>          y_x_SetAngleUnitsType
%type <SetEndEffectorParametersTypeVal> y_x_SetEndEffectorParametersType
%type <SetEndEffectorTypeVal>         y_x_SetEndEffectorType
%type <SetEndPoseToleranceTypeVal>    y_x_SetEndPoseToleranceType
%type <SetForceUnitsTypeVal>          y_x_SetForceUnitsType
%type <SetIntermediatePoseToleranceTypeVal> y_x_SetIntermediatePoseToleranceType
%type <SetLengthUnitsTypeVal>         y_x_SetLengthUnitsType
%type <SetMotionCoordinationTypeVal>  y_x_SetMotionCoordinationType
%type <SetRobotParametersTypeVal>     y_x_SetRobotParametersType
%type <SetRotAccelTypeVal>            y_x_SetRotAccelType
%type <SetRotSpeedTypeVal>            y_x_SetRotSpeedType
%type <SetTorqueUnitsTypeVal>         y_x_SetTorqueUnitsType
%type <SetTransAccelTypeVal>          y_x_SetTransAccelType
%type <SetTransSpeedTypeVal>          y_x_SetTransSpeedType
%type <StopMotionTypeVal>             y_x_StopMotionType
%type <TransAccelAbsoluteTypeVal>     y_x_TransAccelAbsoluteType
%type <TransAccelRelativeTypeVal>     y_x_TransAccelRelativeType
%type <TransSpeedAbsoluteTypeVal>     y_x_TransSpeedAbsoluteType
%type <TransSpeedRelativeTypeVal>     y_x_TransSpeedRelativeType

%token <iVal> BAD
%token <sVal> DATASTRING
%token <iVal> ENCODING
%token <iVal> ENDITEM
%token <iVal> ENDVERSION
%token <sVal> SCHEMALOCATION
%token <iVal> STARTVERSION
%token <sVal> TERMINALSTRING
%token <iVal> XMLNSPREFIX
%token <iVal> XMLNSTARGET
%token <iVal> XMLVERSION

%token <iVal> ACTUATEJOINTEND
%token <iVal> ACTUATEJOINTSTART
%token <iVal> ANGULARVELOCITYEND
%token <iVal> ANGULARVELOCITYSTART
%token <iVal> AXIALDISTANCEFREEEND
%token <iVal> AXIALDISTANCEFREESTART
%token <iVal> AXIALDISTANCESCREWEND
%token <iVal> AXIALDISTANCESCREWSTART
%token <iVal> AXISPOINTEND
%token <iVal> AXISPOINTSTART
%token <iVal> CRCLCOMMANDINSTANCEEND
%token <iVal> CRCLCOMMANDINSTANCESTART
%token <iVal> CRCLCOMMANDEND
%token <iVal> CRCLCOMMANDSTART
%token <iVal> CHANGERATEEND
%token <iVal> CHANGERATESTART
%token <iVal> COMMANDIDEND
%token <iVal> COMMANDIDSTART
%token <iVal> CONFIGUREJOINTREPORTEND
%token <iVal> CONFIGUREJOINTREPORTSTART
%token <iVal> COORDINATEDEND
%token <iVal> COORDINATEDSTART
%token <iVal> DWELLTIMEEND
%token <iVal> DWELLTIMESTART
%token <iVal> ENDPOSITIONEND
%token <iVal> ENDPOSITIONSTART
%token <iVal> FORCEEND
%token <iVal> FORCESTART
%token <iVal> FRACTIONEND
%token <iVal> FRACTIONSTART
%token <iVal> IEND
%token <iVal> ISTART
%token <iVal> JEND
%token <iVal> JSTART
%token <iVal> JOINTACCELEND
%token <iVal> JOINTACCELSTART
%token <iVal> JOINTDETAILSEND
%token <iVal> JOINTDETAILSSTART
%token <iVal> JOINTNUMBEREND
%token <iVal> JOINTNUMBERSTART
%token <iVal> JOINTPOSITIONEND
%token <iVal> JOINTPOSITIONSTART
%token <iVal> JOINTSPEEDEND
%token <iVal> JOINTSPEEDSTART
%token <iVal> KEND
%token <iVal> KSTART
%token <iVal> LINEARVELOCITYEND
%token <iVal> LINEARVELOCITYSTART
%token <iVal> MESSAGEEND
%token <iVal> MESSAGESTART
%token <iVal> MOMENTEND
%token <iVal> MOMENTSTART
%token <iVal> MOVESTRAIGHTEND
%token <iVal> MOVESTRAIGHTSTART
%token <iVal> NAMEEND
%token <iVal> NAMESTART
%token <iVal> NUMPOSITIONSEND
%token <iVal> NUMPOSITIONSSTART
%token <iVal> PARAMETERNAMEEND
%token <iVal> PARAMETERNAMESTART
%token <iVal> PARAMETERSETTINGEND
%token <iVal> PARAMETERSETTINGSTART
%token <iVal> PARAMETERVALUEEND
%token <iVal> PARAMETERVALUESTART
%token <iVal> POINTEND
%token <iVal> POINTSTART
%token <iVal> PROGRAMTEXTEND
%token <iVal> PROGRAMTEXTSTART
%token <iVal> REPORTPOSITIONEND
%token <iVal> REPORTPOSITIONSTART
%token <iVal> REPORTTORQUEORFORCEEND
%token <iVal> REPORTTORQUEORFORCESTART
%token <iVal> REPORTVELOCITYEND
%token <iVal> REPORTVELOCITYSTART
%token <iVal> RESETALLEND
%token <iVal> RESETALLSTART
%token <iVal> ROTACCELEND
%token <iVal> ROTACCELSTART
%token <iVal> ROTSPEEDEND
%token <iVal> ROTSPEEDSTART
%token <iVal> SETTINGEND
%token <iVal> SETTINGSTART
%token <iVal> STARTPOSITIONEND
%token <iVal> STARTPOSITIONSTART
%token <iVal> STOPCONDITIONEND
%token <iVal> STOPCONDITIONSTART
%token <iVal> TOLERANCEEND
%token <iVal> TOLERANCESTART
%token <iVal> TRANSACCELEND
%token <iVal> TRANSACCELSTART
%token <iVal> TRANSSPEEDEND
%token <iVal> TRANSSPEEDSTART
%token <iVal> TURNEND
%token <iVal> TURNSTART
%token <iVal> UNITNAMEEND
%token <iVal> UNITNAMESTART
%token <iVal> WAYPOINTEND
%token <iVal> WAYPOINTSTART
%token <iVal> XAXISTOLERANCEEND
%token <iVal> XAXISTOLERANCESTART
%token <iVal> XAXISEND
%token <iVal> XAXISSTART
%token <iVal> XPOINTTOLERANCEEND
%token <iVal> XPOINTTOLERANCESTART
%token <iVal> XEND
%token <iVal> XSTART
%token <iVal> YPOINTTOLERANCEEND
%token <iVal> YPOINTTOLERANCESTART
%token <iVal> YEND
%token <iVal> YSTART
%token <iVal> ZAXISTOLERANCEEND
%token <iVal> ZAXISTOLERANCESTART
%token <iVal> ZAXISEND
%token <iVal> ZAXISSTART
%token <iVal> ZPOINTTOLERANCEEND
%token <iVal> ZPOINTTOLERANCESTART
%token <iVal> ZEND
%token <iVal> ZSTART


%token <iVal> ACTUATEJOINTTYPEDECL
%token <iVal> ACTUATEJOINTSTYPEDECL
%token <iVal> CRCLCOMMANDINSTANCETYPEDECL
%token <iVal> CRCLCOMMANDTYPEDECL
%token <iVal> CLOSETOOLCHANGERTYPEDECL
%token <iVal> CONFIGUREJOINTREPORTTYPEDECL
%token <iVal> CONFIGUREJOINTREPORTSTYPEDECL
%token <iVal> DWELLTYPEDECL
%token <iVal> ENDCANONTYPEDECL
%token <iVal> GETSTATUSTYPEDECL
%token <iVal> INITCANONTYPEDECL
%token <iVal> JOINTDETAILSTYPEDECL
%token <iVal> JOINTFORCETORQUETYPEDECL
%token <iVal> JOINTSPEEDACCELTYPEDECL
%token <iVal> MESSAGETYPEDECL
%token <iVal> MIDDLECOMMANDTYPEDECL
%token <iVal> MOVESCREWTYPEDECL
%token <iVal> MOVETHROUGHTOTYPEDECL
%token <iVal> MOVETOTYPEDECL
%token <iVal> OPENTOOLCHANGERTYPEDECL
%token <iVal> PARAMETERSETTINGTYPEDECL
%token <iVal> POINTTYPEDECL
%token <iVal> POSEANDSETTYPEDECL
%token <iVal> POSETOLERANCETYPEDECL
%token <iVal> POSETYPEDECL
%token <iVal> ROTACCELABSOLUTETYPEDECL
%token <iVal> ROTACCELRELATIVETYPEDECL
%token <iVal> ROTACCELTYPEDECL
%token <iVal> ROTSPEEDABSOLUTETYPEDECL
%token <iVal> ROTSPEEDRELATIVETYPEDECL
%token <iVal> ROTSPEEDTYPEDECL
%token <iVal> RUNPROGRAMTYPEDECL
%token <iVal> SETANGLEUNITSTYPEDECL
%token <iVal> SETENDEFFECTORPARAMETERSTYPEDECL
%token <iVal> SETENDEFFECTORTYPEDECL
%token <iVal> SETENDPOSETOLERANCETYPEDECL
%token <iVal> SETFORCEUNITSTYPEDECL
%token <iVal> SETINTERMEDIATEPOSETOLERANCETYPEDECL
%token <iVal> SETLENGTHUNITSTYPEDECL
%token <iVal> SETMOTIONCOORDINATIONTYPEDECL
%token <iVal> SETROBOTPARAMETERSTYPEDECL
%token <iVal> SETROTACCELTYPEDECL
%token <iVal> SETROTSPEEDTYPEDECL
%token <iVal> SETTORQUEUNITSTYPEDECL
%token <iVal> SETTRANSACCELTYPEDECL
%token <iVal> SETTRANSSPEEDTYPEDECL
%token <iVal> STOPMOTIONTYPEDECL
%token <iVal> TRANSACCELABSOLUTETYPEDECL
%token <iVal> TRANSACCELRELATIVETYPEDECL
%token <iVal> TRANSACCELTYPEDECL
%token <iVal> TRANSSPEEDABSOLUTETYPEDECL
%token <iVal> TRANSSPEEDRELATIVETYPEDECL
%token <iVal> TRANSSPEEDTYPEDECL
%token <iVal> TWISTTYPEDECL
%token <iVal> VECTORTYPEDECL
%token <iVal> WRENCHTYPEDECL

%%

y_CRCLCommandInstanceFile :
	  y_XmlVersion CRCLCOMMANDINSTANCESTART y_XmlHeaderForCRCLCommandInstance
	  y_CRCLCommandInstanceType CRCLCOMMANDINSTANCEEND
	  {$$ = new CRCLCommandInstanceFile($1, $3, $4);
	   CRCLCommandInstanceTree = $$;
	   if (XmlIDREF::idMissing())
	     yyerror("xs:ID missing for xs:IDREF");
	  }
	;

y_XmlHeaderForCRCLCommandInstance:
	  XMLNSPREFIX y_SchemaLocation
	  {$$ = new XmlHeaderForCRCLCommandInstance($2);}
	;

y_SchemaLocation :
	  SCHEMALOCATION TERMINALSTRING
	  {$$ = new SchemaLocation("xsi", $2, false);
	   free($2);
	  }
	;

y_XmlBoolean :
	  DATASTRING
	  {$$ = new XmlBoolean($1);
	   if ($$->bad)
	     yyerror("bad XmlBoolean");
	   free($1);
	  }
	;

y_XmlDecimal :
	  DATASTRING
	  {$$ = new XmlDecimal($1);
	   if ($$->bad)
	     yyerror("bad XmlDecimal");
	   free($1);
	  }
	;

y_XmlID :
	  DATASTRING
	  {$$ = new XmlID($1);
	   if ($$->bad)
	     yyerror("bad XmlID");
	   free($1);
	  }
	;

y_XmlPositiveInteger :
	  DATASTRING
	  {$$ = new XmlPositiveInteger($1);
	   if ($$->bad)
	     yyerror("bad XmlPositiveInteger");
	   free($1);
	  }
	;

y_XmlString :
	  DATASTRING
	  {$$ = new XmlString($1);
	   if ($$->bad)
	     yyerror("bad XmlString");
	   free($1);
	  }
	;

y_XmlToken :
	  DATASTRING
	  {$$ = new XmlToken($1);
	   if ($$->bad)
	     yyerror("bad XmlToken");
	   free($1);
	  }
	;

y_XmlVersion :
	  STARTVERSION XMLVERSION TERMINALSTRING ENDVERSION
	  {$$ = new XmlVersion(false);
	   if (strcmp($3, "1.0"))
	     yyerror("version number must be 1.0");
	   free($3);
	  }
	| STARTVERSION XMLVERSION TERMINALSTRING
	  ENCODING TERMINALSTRING ENDVERSION
	  {$$ = new XmlVersion(true);
	   if (strcmp($3, "1.0"))
	     yyerror("version number must be 1.0");
	   else if ((strcmp($5, "UTF-8")) && (strcmp($5, "utf-8")))
	     yyerror("encoding must be UTF-8");
	   free($3);
	   free($5);
	  }
	;

y_ActuateJointType :
	   ENDITEM y_Name_XmlID_0 y_JointNumber_XmlPositiveInteger
	  y_JointPosition_XmlDecimal y_JointDetails_JointDetailsType
	  {$$ = new ActuateJointType($2, $3, $4, $5);}
	;

y_ActuateJoint_ActuateJointType_1_u :
	  ACTUATEJOINTSTART y_ActuateJointType ACTUATEJOINTEND
	  {$$ = $2;}
	;

y_AngularVelocity_VectorType :
	  ANGULARVELOCITYSTART y_VectorType ANGULARVELOCITYEND
	  {$$ = $2;}
	;

y_AxialDistanceFree_XmlDecimal_0 :
	  /* empty */
	  {$$ = 0;}
	| AXIALDISTANCEFREESTART ENDITEM {yyReadData = 1;} y_XmlDecimal
	  AXIALDISTANCEFREEEND
	  {$$ = $4;}
	;

y_AxialDistanceScrew_XmlDecimal :
	  AXIALDISTANCESCREWSTART ENDITEM {yyReadData = 1;} y_XmlDecimal
	  AXIALDISTANCESCREWEND
	  {$$ = $4;}
	;

y_AxisPoint_PointType_0 :
	  /* empty */
	  {$$ = 0;}
	| AXISPOINTSTART y_PointType AXISPOINTEND
	  {$$ = $2;}
	;

y_CRCLCommandInstanceType :
	   ENDITEM y_Name_XmlID_0 y_CRCLCommand_CRCLCommandType
	  {$$ = new CRCLCommandInstanceType($2, $3);}
	;

y_CRCLCommandTypeAny :
	  y_x_ActuateJointsType
	  {$$ = $1;}
	| y_x_CloseToolChangerType
	  {$$ = $1;}
	| y_x_ConfigureJointReportsType
	  {$$ = $1;}
	| y_x_DwellType
	  {$$ = $1;}
	| y_x_EndCanonType
	  {$$ = $1;}
	| y_x_GetStatusType
	  {$$ = $1;}
	| y_x_InitCanonType
	  {$$ = $1;}
	| y_x_MessageType
	  {$$ = $1;}
	| y_x_MoveScrewType
	  {$$ = $1;}
	| y_x_MoveThroughToType
	  {$$ = $1;}
	| y_x_MoveToType
	  {$$ = $1;}
	| y_x_OpenToolChangerType
	  {$$ = $1;}
	| y_x_RunProgramType
	  {$$ = $1;}
	| y_x_SetAngleUnitsType
	  {$$ = $1;}
	| y_x_SetEndEffectorParametersType
	  {$$ = $1;}
	| y_x_SetEndEffectorType
	  {$$ = $1;}
	| y_x_SetEndPoseToleranceType
	  {$$ = $1;}
	| y_x_SetForceUnitsType
	  {$$ = $1;}
	| y_x_SetIntermediatePoseToleranceType
	  {$$ = $1;}
	| y_x_SetLengthUnitsType
	  {$$ = $1;}
	| y_x_SetMotionCoordinationType
	  {$$ = $1;}
	| y_x_SetRobotParametersType
	  {$$ = $1;}
	| y_x_SetRotAccelType
	  {$$ = $1;}
	| y_x_SetRotSpeedType
	  {$$ = $1;}
	| y_x_SetTorqueUnitsType
	  {$$ = $1;}
	| y_x_SetTransAccelType
	  {$$ = $1;}
	| y_x_SetTransSpeedType
	  {$$ = $1;}
	| y_x_StopMotionType
	  {$$ = $1;}
	;

y_CRCLCommand_CRCLCommandType :
	  CRCLCOMMANDSTART y_CRCLCommandTypeAny CRCLCOMMANDEND
	  {$$ = $2;}
	;

y_ChangeRate_XmlDecimal_0 :
	  /* empty */
	  {$$ = 0;}
	| CHANGERATESTART ENDITEM {yyReadData = 1;} y_XmlDecimal
	  CHANGERATEEND
	  {$$ = $4;}
	;

y_CommandID_XmlPositiveInteger :
	  COMMANDIDSTART ENDITEM {yyReadData = 1;} y_XmlPositiveInteger
	  COMMANDIDEND
	  {$$ = $4;}
	;

y_ConfigureJointReportType :
	   ENDITEM y_Name_XmlID_0 y_JointNumber_XmlPositiveInteger
	  y_ReportPosition_XmlBoolean y_ReportTorqueOrForce_XmlBoolean
	  y_ReportVelocity_XmlBoolean
	  {$$ = new ConfigureJointReportType($2, $3, $4, $5, $6);}
	;

y_ConfigureJointReport_Configure1002 :
	  CONFIGUREJOINTREPORTSTART y_ConfigureJointReportType
	  CONFIGUREJOINTREPORTEND
	  {$$ = $2;}
	;

y_Coordinated_XmlBoolean :
	  COORDINATEDSTART ENDITEM {yyReadData = 1;} y_XmlBoolean
	  COORDINATEDEND
	  {$$ = $4;}
	;

y_DwellTime_XmlDecimal :
	  DWELLTIMESTART ENDITEM {yyReadData = 1;} y_XmlDecimal
	  DWELLTIMEEND
	  {$$ = $4;}
	;

y_EndPosition_PoseType :
	  ENDPOSITIONSTART y_PoseTypeAny ENDPOSITIONEND
	  {$$ = $2;}
	;

y_Force_VectorType :
	  FORCESTART y_VectorType FORCEEND
	  {$$ = $2;}
	;

y_Fraction_FractionType :
	  FRACTIONSTART ENDITEM {yyReadData = 1;} DATASTRING FRACTIONEND
	  {$$ = new FractionType($4);
	   if ($$->bad)
	     yyerror("bad Fraction value");
	   free($4);
	  }
	;

y_I_XmlDecimal :
	  ISTART ENDITEM {yyReadData = 1;} y_XmlDecimal IEND
	  {$$ = $4;}
	;

y_J_XmlDecimal :
	  JSTART ENDITEM {yyReadData = 1;} y_XmlDecimal JEND
	  {$$ = $4;}
	;

y_JointAccel_XmlDecimal_0 :
	  /* empty */
	  {$$ = 0;}
	| JOINTACCELSTART ENDITEM {yyReadData = 1;} y_XmlDecimal
	  JOINTACCELEND
	  {$$ = $4;}
	;

y_JointDetailsTypeAny :
	  y_x_JointForceTorqueType
	  {$$ = $1;}
	| y_x_JointSpeedAccelType
	  {$$ = $1;}
	;

y_JointDetails_JointDetailsType :
	  JOINTDETAILSSTART y_JointDetailsTypeAny JOINTDETAILSEND
	  {$$ = $2;}
	;

y_JointNumber_XmlPositiveInteger :
	  JOINTNUMBERSTART ENDITEM {yyReadData = 1;} y_XmlPositiveInteger
	  JOINTNUMBEREND
	  {$$ = $4;}
	;

y_JointPosition_XmlDecimal :
	  JOINTPOSITIONSTART ENDITEM {yyReadData = 1;} y_XmlDecimal
	  JOINTPOSITIONEND
	  {$$ = $4;}
	;

y_JointSpeed_XmlDecimal_0 :
	  /* empty */
	  {$$ = 0;}
	| JOINTSPEEDSTART ENDITEM {yyReadData = 1;} y_XmlDecimal
	  JOINTSPEEDEND
	  {$$ = $4;}
	;

y_K_XmlDecimal :
	  KSTART ENDITEM {yyReadData = 1;} y_XmlDecimal KEND
	  {$$ = $4;}
	;

y_LinearVelocity_VectorType :
	  LINEARVELOCITYSTART y_VectorType LINEARVELOCITYEND
	  {$$ = $2;}
	;

y_ListActuateJoint_ActuateJointType_1_u :
	  y_ActuateJoint_ActuateJointType_1_u
	  {$$ = new std::list<ActuateJointType *>;
	   $$->push_back($1);}
	| y_ListActuateJoint_ActuateJointType_1_u
	  y_ActuateJoint_ActuateJointType_1_u
	  {$$ = $1;
	   $$->push_back($2);}
	;

y_ListConfigureJointReport_Configure1002 :
	  /* empty */
	  {$$ = new std::list<ConfigureJointReportType *>;}
	| y_ListConfigureJointReport_Configure1002
	  y_ConfigureJointReport_Configure1002
	  {$$ = $1;
	   $$->push_back($2);}
	;

y_ListParameterSetting_ParameterSett1003 :
	  y_ListParameterSetting_ParameterSett1003
	  y_ParameterSetting_ParameterSett1003
	  {$$ = $1;
	   $$->push_back($2);}
	| y_ParameterSetting_ParameterSett1003
	  {$$ = new std::list<ParameterSettingType *>;
	   $$->push_back($1);}
	;

y_ListWaypoint_PoseType_2_u :
	  y_ListWaypoint_PoseType_2_u y_Waypoint_PoseType_2_u
	  {$$ = $1;
	   $$->push_back($2);}
	| y_Waypoint_PoseType_2_u
	  {$$ = new std::list<PoseType *>;
	   $$->push_back($1);}
	;

y_ListWaypoint_PoseType_2_u_Check :
	  y_ListWaypoint_PoseType_2_u
	  {$$ = $1;
	   if ($1->size() < 2)
	     yyerror("must be at least 2 Waypoints");
	  }
	;

y_Message_XmlString :
	  MESSAGESTART ENDITEM {yyReadData = 1;} y_XmlString MESSAGEEND
	  {$$ = $4;}
	;

y_Moment_VectorType :
	  MOMENTSTART y_VectorType MOMENTEND
	  {$$ = $2;}
	;

y_MoveStraight_XmlBoolean :
	  MOVESTRAIGHTSTART ENDITEM {yyReadData = 1;} y_XmlBoolean
	  MOVESTRAIGHTEND
	  {$$ = $4;}
	;

y_Name_XmlID_0 :
	  /* empty */
	  {$$ = 0;}
	| NAMESTART ENDITEM {yyReadData = 1;} y_XmlID NAMEEND
	  {$$ = $4;}
	;

y_NumPositions_XmlPositiveInteger :
	  NUMPOSITIONSSTART ENDITEM {yyReadData = 1;} y_XmlPositiveInteger
	  NUMPOSITIONSEND
	  {$$ = $4;}
	;

y_ParameterName_XmlToken :
	  PARAMETERNAMESTART ENDITEM {yyReadData = 1;} y_XmlToken
	  PARAMETERNAMEEND
	  {$$ = $4;}
	;

y_ParameterSettingType :
	   ENDITEM y_Name_XmlID_0 y_ParameterName_XmlToken
	  y_ParameterValue_XmlToken
	  {$$ = new ParameterSettingType($2, $3, $4);}
	;

y_ParameterSetting_ParameterSett1003 :
	  PARAMETERSETTINGSTART y_ParameterSettingType PARAMETERSETTINGEND
	  {$$ = $2;}
	;

y_ParameterValue_XmlToken :
	  PARAMETERVALUESTART ENDITEM {yyReadData = 1;} y_XmlToken
	  PARAMETERVALUEEND
	  {$$ = $4;}
	;

y_PointType :
	   ENDITEM y_Name_XmlID_0 y_X_XmlDecimal y_Y_XmlDecimal
	  y_Z_XmlDecimal
	  {$$ = new PointType($2, $3, $4, $5);}
	;

y_Point_PointType :
	  POINTSTART y_PointType POINTEND
	  {$$ = $2;}
	;

y_PoseToleranceType :
	   ENDITEM y_Name_XmlID_0 y_XPointTolerance_XmlDecimal_0
	  y_YPointTolerance_XmlDecimal_0 y_ZPointTolerance_XmlDecimal_0
	  y_XAxisTolerance_XmlDecimal_0 y_ZAxisTolerance_XmlDecimal_0
	  {$$ = new PoseToleranceType($2, $3, $4, $5, $6, $7);}
	;

y_PoseType :
	   ENDITEM y_Name_XmlID_0 y_Point_PointType y_XAxis_VectorType
	  y_ZAxis_VectorType
	  {$$ = new PoseType($2, $3, $4, $5);}
	;

y_PoseTypeAny :
	  y_PoseType
	  {$$ = $1;}
	| y_x_PoseAndSetType
	  {$$ = $1;}
	;

y_ProgramText_XmlString :
	  PROGRAMTEXTSTART ENDITEM {yyReadData = 1;} y_XmlString
	  PROGRAMTEXTEND
	  {$$ = $4;}
	;

y_ReportPosition_XmlBoolean :
	  REPORTPOSITIONSTART ENDITEM {yyReadData = 1;} y_XmlBoolean
	  REPORTPOSITIONEND
	  {$$ = $4;}
	;

y_ReportTorqueOrForce_XmlBoolean :
	  REPORTTORQUEORFORCESTART ENDITEM {yyReadData = 1;} y_XmlBoolean
	  REPORTTORQUEORFORCEEND
	  {$$ = $4;}
	;

y_ReportVelocity_XmlBoolean :
	  REPORTVELOCITYSTART ENDITEM {yyReadData = 1;} y_XmlBoolean
	  REPORTVELOCITYEND
	  {$$ = $4;}
	;

y_ResetAll_XmlBoolean :
	  RESETALLSTART ENDITEM {yyReadData = 1;} y_XmlBoolean RESETALLEND
	  {$$ = $4;}
	;

y_RotAccelTypeAny :
	  y_x_RotAccelAbsoluteType
	  {$$ = $1;}
	| y_x_RotAccelRelativeType
	  {$$ = $1;}
	;

y_RotAccel_RotAccelType :
	  ROTACCELSTART y_RotAccelTypeAny ROTACCELEND
	  {$$ = $2;}
	;

y_RotAccel_RotAccelType_0 :
	  /* empty */
	  {$$ = 0;}
	| ROTACCELSTART y_RotAccelTypeAny ROTACCELEND
	  {$$ = $2;}
	;

y_RotSpeedTypeAny :
	  y_x_RotSpeedAbsoluteType
	  {$$ = $1;}
	| y_x_RotSpeedRelativeType
	  {$$ = $1;}
	;

y_RotSpeed_RotSpeedType :
	  ROTSPEEDSTART y_RotSpeedTypeAny ROTSPEEDEND
	  {$$ = $2;}
	;

y_RotSpeed_RotSpeedType_0 :
	  /* empty */
	  {$$ = 0;}
	| ROTSPEEDSTART y_RotSpeedTypeAny ROTSPEEDEND
	  {$$ = $2;}
	;

y_Setting_FractionType :
	  SETTINGSTART ENDITEM {yyReadData = 1;} DATASTRING SETTINGEND
	  {$$ = new FractionType($4);
	   if ($$->bad)
	     yyerror("bad Setting value");
	   free($4);
	  }
	;

y_Setting_XmlDecimal :
	  SETTINGSTART ENDITEM {yyReadData = 1;} y_XmlDecimal SETTINGEND
	  {$$ = $4;}
	;

y_Setting_XmlDecimal_0 :
	  /* empty */
	  {$$ = 0;}
	| SETTINGSTART ENDITEM {yyReadData = 1;} y_XmlDecimal SETTINGEND
	  {$$ = $4;}
	;

y_StartPosition_PoseType_0 :
	  /* empty */
	  {$$ = 0;}
	| STARTPOSITIONSTART y_PoseTypeAny STARTPOSITIONEND
	  {$$ = $2;}
	;

y_StopCondition_StopConditionEnumType :
	  STOPCONDITIONSTART ENDITEM {yyReadData = 1;} DATASTRING
	  STOPCONDITIONEND
	  {$$ = new StopConditionEnumType($4);
	   if ($$->bad)
	     yyerror("bad StopCondition value");
	   free($4);
	  }
	;

y_Tolerance_PoseToleranceType :
	  TOLERANCESTART y_PoseToleranceType TOLERANCEEND
	  {$$ = $2;}
	;

y_Tolerance_PoseToleranceType_0 :
	  /* empty */
	  {$$ = 0;}
	| TOLERANCESTART y_PoseToleranceType TOLERANCEEND
	  {$$ = $2;}
	;

y_TransAccelTypeAny :
	  y_x_TransAccelAbsoluteType
	  {$$ = $1;}
	| y_x_TransAccelRelativeType
	  {$$ = $1;}
	;

y_TransAccel_TransAccelType :
	  TRANSACCELSTART y_TransAccelTypeAny TRANSACCELEND
	  {$$ = $2;}
	;

y_TransAccel_TransAccelType_0 :
	  /* empty */
	  {$$ = 0;}
	| TRANSACCELSTART y_TransAccelTypeAny TRANSACCELEND
	  {$$ = $2;}
	;

y_TransSpeedTypeAny :
	  y_x_TransSpeedAbsoluteType
	  {$$ = $1;}
	| y_x_TransSpeedRelativeType
	  {$$ = $1;}
	;

y_TransSpeed_TransSpeedType :
	  TRANSSPEEDSTART y_TransSpeedTypeAny TRANSSPEEDEND
	  {$$ = $2;}
	;

y_TransSpeed_TransSpeedType_0 :
	  /* empty */
	  {$$ = 0;}
	| TRANSSPEEDSTART y_TransSpeedTypeAny TRANSSPEEDEND
	  {$$ = $2;}
	;

y_Turn_XmlDecimal :
	  TURNSTART ENDITEM {yyReadData = 1;} y_XmlDecimal TURNEND
	  {$$ = $4;}
	;

y_UnitName_AngleUnitEnumType :
	  UNITNAMESTART ENDITEM {yyReadData = 1;} DATASTRING UNITNAMEEND
	  {$$ = new AngleUnitEnumType($4);
	   if ($$->bad)
	     yyerror("bad UnitName value");
	   free($4);
	  }
	;

y_UnitName_ForceUnitEnumType :
	  UNITNAMESTART ENDITEM {yyReadData = 1;} DATASTRING UNITNAMEEND
	  {$$ = new ForceUnitEnumType($4);
	   if ($$->bad)
	     yyerror("bad UnitName value");
	   free($4);
	  }
	;

y_UnitName_LengthUnitEnumType :
	  UNITNAMESTART ENDITEM {yyReadData = 1;} DATASTRING UNITNAMEEND
	  {$$ = new LengthUnitEnumType($4);
	   if ($$->bad)
	     yyerror("bad UnitName value");
	   free($4);
	  }
	;

y_UnitName_TorqueUnitEnumType :
	  UNITNAMESTART ENDITEM {yyReadData = 1;} DATASTRING UNITNAMEEND
	  {$$ = new TorqueUnitEnumType($4);
	   if ($$->bad)
	     yyerror("bad UnitName value");
	   free($4);
	  }
	;

y_VectorType :
	   ENDITEM y_Name_XmlID_0 y_I_XmlDecimal y_J_XmlDecimal
	  y_K_XmlDecimal
	  {$$ = new VectorType($2, $3, $4, $5);}
	;

y_Waypoint_PoseType_2_u :
	  WAYPOINTSTART y_PoseType WAYPOINTEND
	  {$$ = $2;}
	| WAYPOINTSTART y_PoseTypeAny WAYPOINTEND
	  {$$ = $2;}
	;

y_XAxisTolerance_XmlDecimal_0 :
	  /* empty */
	  {$$ = 0;}
	| XAXISTOLERANCESTART ENDITEM {yyReadData = 1;} y_XmlDecimal
	  XAXISTOLERANCEEND
	  {$$ = $4;}
	;

y_XAxis_VectorType :
	  XAXISSTART y_VectorType XAXISEND
	  {$$ = $2;}
	;

y_XPointTolerance_XmlDecimal_0 :
	  /* empty */
	  {$$ = 0;}
	| XPOINTTOLERANCESTART ENDITEM {yyReadData = 1;} y_XmlDecimal
	  XPOINTTOLERANCEEND
	  {$$ = $4;}
	;

y_X_XmlDecimal :
	  XSTART ENDITEM {yyReadData = 1;} y_XmlDecimal XEND
	  {$$ = $4;}
	;

y_YPointTolerance_XmlDecimal_0 :
	  /* empty */
	  {$$ = 0;}
	| YPOINTTOLERANCESTART ENDITEM {yyReadData = 1;} y_XmlDecimal
	  YPOINTTOLERANCEEND
	  {$$ = $4;}
	;

y_Y_XmlDecimal :
	  YSTART ENDITEM {yyReadData = 1;} y_XmlDecimal YEND
	  {$$ = $4;}
	;

y_ZAxisTolerance_XmlDecimal_0 :
	  /* empty */
	  {$$ = 0;}
	| ZAXISTOLERANCESTART ENDITEM {yyReadData = 1;} y_XmlDecimal
	  ZAXISTOLERANCEEND
	  {$$ = $4;}
	;

y_ZAxis_VectorType :
	  ZAXISSTART y_VectorType ZAXISEND
	  {$$ = $2;}
	;

y_ZPointTolerance_XmlDecimal_0 :
	  /* empty */
	  {$$ = 0;}
	| ZPOINTTOLERANCESTART ENDITEM {yyReadData = 1;} y_XmlDecimal
	  ZPOINTTOLERANCEEND
	  {$$ = $4;}
	;

y_Z_XmlDecimal :
	  ZSTART ENDITEM {yyReadData = 1;} y_XmlDecimal ZEND
	  {$$ = $4;}
	;

y_x_ActuateJointsType :
	  ACTUATEJOINTSTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger
	  y_ListActuateJoint_ActuateJointType_1_u
	  {$$ = new ActuateJointsType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_CloseToolChangerType :
	  CLOSETOOLCHANGERTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger
	  {$$ = new CloseToolChangerType($3, $4);
	   $$->printTypp = true;
	  }
	;

y_x_ConfigureJointReportsType :
	  CONFIGUREJOINTREPORTSTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger y_ResetAll_XmlBoolean
	  y_ListConfigureJointReport_Configure1002
	  {$$ = new ConfigureJointReportsType($3, $4, $5, $6);
	   $$->printTypp = true;
	  }
	;

y_x_DwellType :
	  DWELLTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger y_DwellTime_XmlDecimal
	  {$$ = new DwellType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_EndCanonType :
	  ENDCANONTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger
	  {$$ = new EndCanonType($3, $4);
	   $$->printTypp = true;
	  }
	;

y_x_GetStatusType :
	  GETSTATUSTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger
	  {$$ = new GetStatusType($3, $4);
	   $$->printTypp = true;
	  }
	;

y_x_InitCanonType :
	  INITCANONTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger
	  {$$ = new InitCanonType($3, $4);
	   $$->printTypp = true;
	  }
	;

y_x_JointForceTorqueType :
	  JOINTFORCETORQUETYPEDECL ENDITEM y_Name_XmlID_0
	  y_Setting_XmlDecimal_0 y_ChangeRate_XmlDecimal_0
	  {$$ = new JointForceTorqueType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_JointSpeedAccelType :
	  JOINTSPEEDACCELTYPEDECL ENDITEM y_Name_XmlID_0
	  y_JointSpeed_XmlDecimal_0 y_JointAccel_XmlDecimal_0
	  {$$ = new JointSpeedAccelType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_MessageType :
	  MESSAGETYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger y_Message_XmlString
	  {$$ = new MessageType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_MoveScrewType :
	  MOVESCREWTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger y_StartPosition_PoseType_0
	  y_AxisPoint_PointType_0 y_AxialDistanceFree_XmlDecimal_0
	  y_AxialDistanceScrew_XmlDecimal y_Turn_XmlDecimal
	  {$$ = new MoveScrewType($3, $4, $5, $6, $7, $8, $9);
	   $$->printTypp = true;
	  }
	;

y_x_MoveThroughToType :
	  MOVETHROUGHTOTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger y_MoveStraight_XmlBoolean
	  y_ListWaypoint_PoseType_2_u_Check
	  y_NumPositions_XmlPositiveInteger
	  {$$ = new MoveThroughToType($3, $4, $5, $6, $7);
	   $$->printTypp = true;
	  }
	;

y_x_MoveToType :
	  MOVETOTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger y_MoveStraight_XmlBoolean
	  y_EndPosition_PoseType
	  {$$ = new MoveToType($3, $4, $5, $6);
	   $$->printTypp = true;
	  }
	;

y_x_OpenToolChangerType :
	  OPENTOOLCHANGERTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger
	  {$$ = new OpenToolChangerType($3, $4);
	   $$->printTypp = true;
	  }
	;

y_x_PoseAndSetType :
	  POSEANDSETTYPEDECL ENDITEM y_Name_XmlID_0 y_Point_PointType
	  y_XAxis_VectorType y_ZAxis_VectorType y_Coordinated_XmlBoolean
	  y_TransSpeed_TransSpeedType_0 y_RotSpeed_RotSpeedType_0
	  y_TransAccel_TransAccelType_0 y_RotAccel_RotAccelType_0
	  y_Tolerance_PoseToleranceType_0
	  {$$ = new PoseAndSetType($3, $4, $5, $6, $7, $8, $9, $10, $11, $12);
	   $$->printTypp = true;
	  }
	;

y_x_RotAccelAbsoluteType :
	  ROTACCELABSOLUTETYPEDECL ENDITEM y_Name_XmlID_0
	  y_Setting_XmlDecimal
	  {$$ = new RotAccelAbsoluteType($3, $4);
	   $$->printTypp = true;
	  }
	;

y_x_RotAccelRelativeType :
	  ROTACCELRELATIVETYPEDECL ENDITEM y_Name_XmlID_0
	  y_Fraction_FractionType
	  {$$ = new RotAccelRelativeType($3, $4);
	   $$->printTypp = true;
	  }
	;

y_x_RotSpeedAbsoluteType :
	  ROTSPEEDABSOLUTETYPEDECL ENDITEM y_Name_XmlID_0
	  y_Setting_XmlDecimal
	  {$$ = new RotSpeedAbsoluteType($3, $4);
	   $$->printTypp = true;
	  }
	;

y_x_RotSpeedRelativeType :
	  ROTSPEEDRELATIVETYPEDECL ENDITEM y_Name_XmlID_0
	  y_Fraction_FractionType
	  {$$ = new RotSpeedRelativeType($3, $4);
	   $$->printTypp = true;
	  }
	;

y_x_RunProgramType :
	  RUNPROGRAMTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger y_ProgramText_XmlString
	  {$$ = new RunProgramType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_SetAngleUnitsType :
	  SETANGLEUNITSTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger y_UnitName_AngleUnitEnumType
	  {$$ = new SetAngleUnitsType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_SetEndEffectorParametersType :
	  SETENDEFFECTORPARAMETERSTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger
	  y_ListParameterSetting_ParameterSett1003
	  {$$ = new SetEndEffectorParametersType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_SetEndEffectorType :
	  SETENDEFFECTORTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger y_Setting_FractionType
	  {$$ = new SetEndEffectorType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_SetEndPoseToleranceType :
	  SETENDPOSETOLERANCETYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger y_Tolerance_PoseToleranceType
	  {$$ = new SetEndPoseToleranceType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_SetForceUnitsType :
	  SETFORCEUNITSTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger y_UnitName_ForceUnitEnumType
	  {$$ = new SetForceUnitsType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_SetIntermediatePoseToleranceType :
	  SETINTERMEDIATEPOSETOLERANCETYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger y_Tolerance_PoseToleranceType
	  {$$ = new SetIntermediatePoseToleranceType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_SetLengthUnitsType :
	  SETLENGTHUNITSTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger y_UnitName_LengthUnitEnumType
	  {$$ = new SetLengthUnitsType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_SetMotionCoordinationType :
	  SETMOTIONCOORDINATIONTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger y_Coordinated_XmlBoolean
	  {$$ = new SetMotionCoordinationType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_SetRobotParametersType :
	  SETROBOTPARAMETERSTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger
	  y_ListParameterSetting_ParameterSett1003
	  {$$ = new SetRobotParametersType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_SetRotAccelType :
	  SETROTACCELTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger y_RotAccel_RotAccelType
	  {$$ = new SetRotAccelType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_SetRotSpeedType :
	  SETROTSPEEDTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger y_RotSpeed_RotSpeedType
	  {$$ = new SetRotSpeedType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_SetTorqueUnitsType :
	  SETTORQUEUNITSTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger y_UnitName_TorqueUnitEnumType
	  {$$ = new SetTorqueUnitsType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_SetTransAccelType :
	  SETTRANSACCELTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger y_TransAccel_TransAccelType
	  {$$ = new SetTransAccelType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_SetTransSpeedType :
	  SETTRANSSPEEDTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger y_TransSpeed_TransSpeedType
	  {$$ = new SetTransSpeedType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_StopMotionType :
	  STOPMOTIONTYPEDECL ENDITEM y_Name_XmlID_0
	  y_CommandID_XmlPositiveInteger
	  y_StopCondition_StopConditionEnumType
	  {$$ = new StopMotionType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_TransAccelAbsoluteType :
	  TRANSACCELABSOLUTETYPEDECL ENDITEM y_Name_XmlID_0
	  y_Setting_XmlDecimal
	  {$$ = new TransAccelAbsoluteType($3, $4);
	   $$->printTypp = true;
	  }
	;

y_x_TransAccelRelativeType :
	  TRANSACCELRELATIVETYPEDECL ENDITEM y_Name_XmlID_0
	  y_Fraction_FractionType
	  {$$ = new TransAccelRelativeType($3, $4);
	   $$->printTypp = true;
	  }
	;

y_x_TransSpeedAbsoluteType :
	  TRANSSPEEDABSOLUTETYPEDECL ENDITEM y_Name_XmlID_0
	  y_Setting_XmlDecimal
	  {$$ = new TransSpeedAbsoluteType($3, $4);
	   $$->printTypp = true;
	  }
	;

y_x_TransSpeedRelativeType :
	  TRANSSPEEDRELATIVETYPEDECL ENDITEM y_Name_XmlID_0
	  y_Fraction_FractionType
	  {$$ = new TransSpeedRelativeType($3, $4);
	   $$->printTypp = true;
	  }
	;

%%

/*********************************************************************/

/* yyerror

Returned Value: int (0)

Called By: yyparse

This prints whatever string the parser provides.

*/

int yyerror(      /* ARGUMENTS       */
 const char * s)  /* string to print */
{
  fflush(stdout);
  fprintf(stderr, "\n%s\n", s);
  exit(1);
  return 0;
}

/*********************************************************************/
