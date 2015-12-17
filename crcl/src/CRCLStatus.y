%{

#include <stdio.h>             // for stderr
#include <string.h>            // for strcat
#include <stdlib.h>            // for malloc, free
#include "crcl/CRCLStatusClasses.hh"

#define YYERROR_VERBOSE
#define YYDEBUG 1

CRCLStatusFile * CRCLStatusTree; // the parse tree

extern int yylex();
int yyReadData = 0;
int yyReadDataList = 0;

int yyerror(const char * s);

%}

%union {
  SchemaLocation *                    SchemaLocationVal;
  XmlHeaderForCRCLStatus *            XmlHeaderForCRCLStatusVal;
  XmlVersion *                        XmlVersionVal;
  int *                               iVal;
  char *                              sVal;
  XmlBoolean *                        XmlBooleanVal;
  XmlDateTime *                       XmlDateTimeVal;
  XmlDecimal *                        XmlDecimalVal;
  XmlID *                             XmlIDVal;
  XmlIDREF *                          XmlIDREFVal;
  XmlNMTOKEN *                        XmlNMTOKENVal;
  XmlNonNegativeInteger *             XmlNonNegativeIntegerVal;
  XmlPositiveInteger *                XmlPositiveIntegerVal;
  XmlString *                         XmlStringVal;

  CRCLStatusFile *                    CRCLStatusFileVal;

  CRCLStatusType *                    CRCLStatusTypeVal;
  CommandStateEnumType *              CommandStateEnumTypeVal;
  CommandStatusType *                 CommandStatusTypeVal;
  FractionType *                      FractionTypeVal;
  GripperStatusType *                 GripperStatusTypeVal;
  JointStatusType *                   JointStatusTypeVal;
  JointStatusesType *                 JointStatusesTypeVal;
  std::list<JointStatusType *> *      ListJointStatusTypeVal;
  ParallelGripperStatusType *         ParallelGripperStatusTypeVal;
  PointType *                         PointTypeVal;
  PoseOnlyLocationType *              PoseOnlyLocationTypeVal;
  PoseStatusType *                    PoseStatusTypeVal;
  PositiveDecimalType *               PositiveDecimalTypeVal;
  ThreeFingerGripperStatusType *      ThreeFingerGripperStatusTypeVal;
  TwistType *                         TwistTypeVal;
  VacuumGripperStatusType *           VacuumGripperStatusTypeVal;
  VectorType *                        VectorTypeVal;
  WrenchType *                        WrenchTypeVal;
}

%type <SchemaLocationVal>             y_SchemaLocation
%type <XmlHeaderForCRCLStatusVal>     y_XmlHeaderForCRCLStatus
%type <XmlVersionVal>                 y_XmlVersion
%type <CRCLStatusFileVal>             y_CRCLStatusFile
%type <XmlBooleanVal>                 y_XmlBoolean
%type <XmlDateTimeVal>                y_XmlDateTime
%type <XmlDecimalVal>                 y_XmlDecimal
%type <XmlIDVal>                      y_XmlID
%type <XmlIDREFVal>                   y_XmlIDREF
%type <XmlNMTOKENVal>                 y_XmlNMTOKEN
%type <XmlNonNegativeIntegerVal>      y_XmlNonNegativeInteger
%type <XmlPositiveIntegerVal>         y_XmlPositiveInteger
%type <XmlStringVal>                  y_XmlString

%type <VectorTypeVal>                 y_AngularVelocity_VectorType
%type <CRCLStatusTypeVal>             y_CRCLStatusType
%type <XmlNonNegativeIntegerVal>      y_CommandID_XmlNonNegativeInteger
%type <CommandStateEnumTypeVal>       y_CommandState_CommandStateEnumType
%type <CommandStatusTypeVal>          y_CommandStatusType
%type <CommandStatusTypeVal>          y_CommandStatus_CommandStatusType
%type <XmlStringVal>                  y_Description_XmlString
%type <XmlDecimalVal>                 y_Finger1Force_XmlDecimal_0
%type <FractionTypeVal>               y_Finger1Position_FractionType_0
%type <XmlDecimalVal>                 y_Finger2Force_XmlDecimal_0
%type <FractionTypeVal>               y_Finger2Position_FractionType_0
%type <XmlDecimalVal>                 y_Finger3Force_XmlDecimal_0
%type <FractionTypeVal>               y_Finger3Position_FractionType_0
%type <VectorTypeVal>                 y_Force_VectorType
%type <XmlNMTOKENVal>                 y_GripperName_XmlNMTOKEN
%type <GripperStatusTypeVal>          y_GripperStatusTypeAny
%type <GripperStatusTypeVal>          y_GripperStatus_GripperStatusType_0
%type <XmlDecimalVal>                 y_I_XmlDecimal
%type <XmlBooleanVal>                 y_IsPowered_XmlBoolean
%type <XmlDecimalVal>                 y_J_XmlDecimal
%type <XmlPositiveIntegerVal>         y_JointNumber_XmlPositiveInteger
%type <XmlDecimalVal>                 y_JointPosition_XmlDecimal_0
%type <JointStatusTypeVal>            y_JointStatusType
%type <JointStatusTypeVal>            y_JointStatus_JointStatusType_1_u
%type <JointStatusesTypeVal>          y_JointStatusesType
%type <JointStatusesTypeVal>          y_JointStatuses_JointStatusesType_0
%type <XmlDecimalVal>                 y_JointTorqueOrForce_XmlDecimal_0
%type <XmlDecimalVal>                 y_JointVelocity_XmlDecimal_0
%type <XmlDecimalVal>                 y_K_XmlDecimal
%type <VectorTypeVal>                 y_LinearVelocity_VectorType
%type <ListJointStatusTypeVal>        y_ListJointStatus_JointStatusType_1_u
%type <PointTypeVal>                  y_LowerRight_PointType
%type <VectorTypeVal>                 y_Moment_VectorType
%type <XmlIDVal>                      y_Name_XmlID_0
%type <PositiveDecimalTypeVal>        y_OrientationStandardDeviation_P1001
%type <PointTypeVal>                  y_PointType
%type <PointTypeVal>                  y_Point_PointType
%type <PoseOnlyLocationTypeVal>       y_PoseOnlyLocationType
%type <PoseStatusTypeVal>             y_PoseStatusType
%type <PoseStatusTypeVal>             y_PoseStatus_PoseStatusType_0
%type <PoseOnlyLocationTypeVal>       y_Pose_PoseOnlyLocationType
%type <PositiveDecimalTypeVal>        y_PositionStandardDeviation_Posi1002
%type <XmlIDREFVal>                   y_RefObjectName_XmlIDREF
%type <XmlIDREFVal>                   y_RefObjectName_XmlIDREF_0
%type <XmlDecimalVal>                 y_Separation_XmlDecimal
%type <XmlPositiveIntegerVal>         y_StatusID_XmlPositiveInteger
%type <XmlDateTimeVal>                y_Timestamp_XmlDateTime_0
%type <TwistTypeVal>                  y_TwistType
%type <TwistTypeVal>                  y_Twist_TwistType_0
%type <PointTypeVal>                  y_UpperLeft_PointType
%type <VectorTypeVal>                 y_VectorType
%type <WrenchTypeVal>                 y_WrenchType
%type <WrenchTypeVal>                 y_Wrench_WrenchType_0
%type <VectorTypeVal>                 y_XAxis_VectorType
%type <XmlDecimalVal>                 y_X_XmlDecimal
%type <XmlDecimalVal>                 y_Y_XmlDecimal
%type <VectorTypeVal>                 y_ZAxis_VectorType
%type <XmlDecimalVal>                 y_Z_XmlDecimal
%type <ParallelGripperStatusTypeVal>  y_x_ParallelGripperStatusType
%type <ThreeFingerGripperStatusTypeVal> y_x_ThreeFingerGripperStatusType
%type <VacuumGripperStatusTypeVal>    y_x_VacuumGripperStatusType

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

%token <iVal> ANGULARVELOCITYEND
%token <iVal> ANGULARVELOCITYSTART
%token <iVal> CRCLSTATUSEND
%token <iVal> CRCLSTATUSSTART
%token <iVal> COMMANDIDEND
%token <iVal> COMMANDIDSTART
%token <iVal> COMMANDSTATEEND
%token <iVal> COMMANDSTATESTART
%token <iVal> COMMANDSTATUSEND
%token <iVal> COMMANDSTATUSSTART
%token <iVal> DESCRIPTIONEND
%token <iVal> DESCRIPTIONSTART
%token <iVal> FINGER1FORCEEND
%token <iVal> FINGER1FORCESTART
%token <iVal> FINGER1POSITIONEND
%token <iVal> FINGER1POSITIONSTART
%token <iVal> FINGER2FORCEEND
%token <iVal> FINGER2FORCESTART
%token <iVal> FINGER2POSITIONEND
%token <iVal> FINGER2POSITIONSTART
%token <iVal> FINGER3FORCEEND
%token <iVal> FINGER3FORCESTART
%token <iVal> FINGER3POSITIONEND
%token <iVal> FINGER3POSITIONSTART
%token <iVal> FORCEEND
%token <iVal> FORCESTART
%token <iVal> GRIPPERNAMEEND
%token <iVal> GRIPPERNAMESTART
%token <iVal> GRIPPERSTATUSEND
%token <iVal> GRIPPERSTATUSSTART
%token <iVal> IEND
%token <iVal> ISTART
%token <iVal> ISPOWEREDEND
%token <iVal> ISPOWEREDSTART
%token <iVal> JEND
%token <iVal> JSTART
%token <iVal> JOINTNUMBEREND
%token <iVal> JOINTNUMBERSTART
%token <iVal> JOINTPOSITIONEND
%token <iVal> JOINTPOSITIONSTART
%token <iVal> JOINTSTATUSEND
%token <iVal> JOINTSTATUSSTART
%token <iVal> JOINTSTATUSESEND
%token <iVal> JOINTSTATUSESSTART
%token <iVal> JOINTTORQUEORFORCEEND
%token <iVal> JOINTTORQUEORFORCESTART
%token <iVal> JOINTVELOCITYEND
%token <iVal> JOINTVELOCITYSTART
%token <iVal> KEND
%token <iVal> KSTART
%token <iVal> LINEARVELOCITYEND
%token <iVal> LINEARVELOCITYSTART
%token <iVal> LOWERRIGHTEND
%token <iVal> LOWERRIGHTSTART
%token <iVal> MOMENTEND
%token <iVal> MOMENTSTART
%token <iVal> NAMEEND
%token <iVal> NAMESTART
%token <iVal> ORIENTATIONSTANDARDDEVIATIONEND
%token <iVal> ORIENTATIONSTANDARDDEVIATIONSTART
%token <iVal> POINTEND
%token <iVal> POINTSTART
%token <iVal> POSESTATUSEND
%token <iVal> POSESTATUSSTART
%token <iVal> POSEEND
%token <iVal> POSESTART
%token <iVal> POSITIONSTANDARDDEVIATIONEND
%token <iVal> POSITIONSTANDARDDEVIATIONSTART
%token <iVal> REFOBJECTNAMEEND
%token <iVal> REFOBJECTNAMESTART
%token <iVal> SEPARATIONEND
%token <iVal> SEPARATIONSTART
%token <iVal> STATUSIDEND
%token <iVal> STATUSIDSTART
%token <iVal> TIMESTAMPEND
%token <iVal> TIMESTAMPSTART
%token <iVal> TWISTEND
%token <iVal> TWISTSTART
%token <iVal> UPPERLEFTEND
%token <iVal> UPPERLEFTSTART
%token <iVal> WRENCHEND
%token <iVal> WRENCHSTART
%token <iVal> XAXISEND
%token <iVal> XAXISSTART
%token <iVal> XEND
%token <iVal> XSTART
%token <iVal> YEND
%token <iVal> YSTART
%token <iVal> ZAXISEND
%token <iVal> ZAXISSTART
%token <iVal> ZEND
%token <iVal> ZSTART


%token <iVal> CRCLSTATUSTYPEDECL
%token <iVal> COMMANDSTATUSTYPEDECL
%token <iVal> GRIPPERSTATUSTYPEDECL
%token <iVal> JOINTSTATUSTYPEDECL
%token <iVal> JOINTSTATUSESTYPEDECL
%token <iVal> PARALLELGRIPPERSTATUSTYPEDECL
%token <iVal> PHYSICALLOCATIONTYPEDECL
%token <iVal> POINTTYPEDECL
%token <iVal> POSELOCATIONINTYPEDECL
%token <iVal> POSELOCATIONONTYPEDECL
%token <iVal> POSELOCATIONTYPEDECL
%token <iVal> POSEONLYLOCATIONTYPEDECL
%token <iVal> POSESTATUSTYPEDECL
%token <iVal> REGIONOFINTERESTTYPEDECL
%token <iVal> RELATIVELOCATIONINTYPEDECL
%token <iVal> RELATIVELOCATIONONTYPEDECL
%token <iVal> RELATIVELOCATIONTYPEDECL
%token <iVal> THREEFINGERGRIPPERSTATUSTYPEDECL
%token <iVal> TWISTTYPEDECL
%token <iVal> VACUUMGRIPPERSTATUSTYPEDECL
%token <iVal> VECTORTYPEDECL
%token <iVal> WRENCHTYPEDECL

%%

y_CRCLStatusFile :
	  y_XmlVersion CRCLSTATUSSTART y_XmlHeaderForCRCLStatus
	  y_CRCLStatusType CRCLSTATUSEND
	  {$$ = new CRCLStatusFile($1, $3, $4);
	   CRCLStatusTree = $$;
	   if (XmlIDREF::idMissing())
	     yyerror("xs:ID missing for xs:IDREF");
	  }
	;

y_XmlHeaderForCRCLStatus:
	  XMLNSPREFIX y_SchemaLocation
	  {$$ = new XmlHeaderForCRCLStatus($2);}
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

y_XmlDateTime :
	  DATASTRING
	  {$$ = new XmlDateTime($1);
	   if ($$->bad)
	     yyerror("bad XmlDateTime");
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

y_XmlIDREF :
	  DATASTRING
	  {$$ = new XmlIDREF($1);
	   if ($$->bad)
	     yyerror("bad XmlIDREF");
	   free($1);
	  }
	;

y_XmlNMTOKEN :
	  DATASTRING
	  {$$ = new XmlNMTOKEN($1);
	   if ($$->bad)
	     yyerror("bad XmlNMTOKEN");
	   free($1);
	  }
	;

y_XmlNonNegativeInteger :
	  DATASTRING
	  {$$ = new XmlNonNegativeInteger($1);
	   if ($$->bad)
	     yyerror("bad XmlNonNegativeInteger");
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

y_AngularVelocity_VectorType :
	  ANGULARVELOCITYSTART y_VectorType ANGULARVELOCITYEND
	  {$$ = $2;}
	;

y_CRCLStatusType :
	   ENDITEM y_Name_XmlID_0 y_CommandStatus_CommandStatusType
	  y_JointStatuses_JointStatusesType_0 y_PoseStatus_PoseStatusType_0
	  y_GripperStatus_GripperStatusType_0
	  {$$ = new CRCLStatusType($2, $3, $4, $5, $6);}
	;

y_CommandID_XmlNonNegativeInteger :
	  COMMANDIDSTART ENDITEM {yyReadData = 1;} y_XmlNonNegativeInteger
	  COMMANDIDEND
	  {$$ = $4;}
	;

y_CommandState_CommandStateEnumType :
	  COMMANDSTATESTART ENDITEM {yyReadData = 1;} DATASTRING
	  COMMANDSTATEEND
	  {$$ = new CommandStateEnumType($4);
	   if ($$->bad)
	     yyerror("bad CommandState value");
	   free($4);
	  }
	;

y_CommandStatusType :
	   ENDITEM y_Name_XmlID_0 y_CommandID_XmlNonNegativeInteger
	  y_StatusID_XmlPositiveInteger y_CommandState_CommandStateEnumType
	  {$$ = new CommandStatusType($2, $3, $4, $5);}
	;

y_CommandStatus_CommandStatusType :
	  COMMANDSTATUSSTART y_CommandStatusType COMMANDSTATUSEND
	  {$$ = $2;}
	;

y_Description_XmlString :
	  DESCRIPTIONSTART ENDITEM {yyReadData = 1;} y_XmlString
	  DESCRIPTIONEND
	  {$$ = $4;}
	;

y_Finger1Force_XmlDecimal_0 :
	  /* empty */
	  {$$ = 0;}
	| FINGER1FORCESTART ENDITEM {yyReadData = 1;} y_XmlDecimal
	  FINGER1FORCEEND
	  {$$ = $4;}
	;

y_Finger1Position_FractionType_0 :
	  /* empty */
	  {$$ = 0;}
	| FINGER1POSITIONSTART ENDITEM {yyReadData = 1;} DATASTRING
	  FINGER1POSITIONEND
	  {$$ = new FractionType($4);
	   if ($$->bad)
	     yyerror("bad Finger1Position value");
	   free($4);
	  }
	;

y_Finger2Force_XmlDecimal_0 :
	  /* empty */
	  {$$ = 0;}
	| FINGER2FORCESTART ENDITEM {yyReadData = 1;} y_XmlDecimal
	  FINGER2FORCEEND
	  {$$ = $4;}
	;

y_Finger2Position_FractionType_0 :
	  /* empty */
	  {$$ = 0;}
	| FINGER2POSITIONSTART ENDITEM {yyReadData = 1;} DATASTRING
	  FINGER2POSITIONEND
	  {$$ = new FractionType($4);
	   if ($$->bad)
	     yyerror("bad Finger2Position value");
	   free($4);
	  }
	;

y_Finger3Force_XmlDecimal_0 :
	  /* empty */
	  {$$ = 0;}
	| FINGER3FORCESTART ENDITEM {yyReadData = 1;} y_XmlDecimal
	  FINGER3FORCEEND
	  {$$ = $4;}
	;

y_Finger3Position_FractionType_0 :
	  /* empty */
	  {$$ = 0;}
	| FINGER3POSITIONSTART ENDITEM {yyReadData = 1;} DATASTRING
	  FINGER3POSITIONEND
	  {$$ = new FractionType($4);
	   if ($$->bad)
	     yyerror("bad Finger3Position value");
	   free($4);
	  }
	;

y_Force_VectorType :
	  FORCESTART y_VectorType FORCEEND
	  {$$ = $2;}
	;

y_GripperName_XmlNMTOKEN :
	  GRIPPERNAMESTART ENDITEM {yyReadData = 1;} y_XmlNMTOKEN
	  GRIPPERNAMEEND
	  {$$ = $4;}
	;

y_GripperStatusTypeAny :
	  y_x_ParallelGripperStatusType
	  {$$ = $1;}
	| y_x_ThreeFingerGripperStatusType
	  {$$ = $1;}
	| y_x_VacuumGripperStatusType
	  {$$ = $1;}
	;

y_GripperStatus_GripperStatusType_0 :
	  /* empty */
	  {$$ = 0;}
	| GRIPPERSTATUSSTART y_GripperStatusTypeAny GRIPPERSTATUSEND
	  {$$ = $2;}
	;

y_I_XmlDecimal :
	  ISTART ENDITEM {yyReadData = 1;} y_XmlDecimal IEND
	  {$$ = $4;}
	;

y_IsPowered_XmlBoolean :
	  ISPOWEREDSTART ENDITEM {yyReadData = 1;} y_XmlBoolean
	  ISPOWEREDEND
	  {$$ = $4;}
	;

y_J_XmlDecimal :
	  JSTART ENDITEM {yyReadData = 1;} y_XmlDecimal JEND
	  {$$ = $4;}
	;

y_JointNumber_XmlPositiveInteger :
	  JOINTNUMBERSTART ENDITEM {yyReadData = 1;} y_XmlPositiveInteger
	  JOINTNUMBEREND
	  {$$ = $4;}
	;

y_JointPosition_XmlDecimal_0 :
	  /* empty */
	  {$$ = 0;}
	| JOINTPOSITIONSTART ENDITEM {yyReadData = 1;} y_XmlDecimal
	  JOINTPOSITIONEND
	  {$$ = $4;}
	;

y_JointStatusType :
	   ENDITEM y_Name_XmlID_0 y_JointNumber_XmlPositiveInteger
	  y_JointPosition_XmlDecimal_0 y_JointTorqueOrForce_XmlDecimal_0
	  y_JointVelocity_XmlDecimal_0
	  {$$ = new JointStatusType($2, $3, $4, $5, $6);}
	;

y_JointStatus_JointStatusType_1_u :
	  JOINTSTATUSSTART y_JointStatusType JOINTSTATUSEND
	  {$$ = $2;}
	;

y_JointStatusesType :
	   ENDITEM y_Name_XmlID_0 y_ListJointStatus_JointStatusType_1_u
	  {$$ = new JointStatusesType($2, $3);}
	;

y_JointStatuses_JointStatusesType_0 :
	  /* empty */
	  {$$ = 0;}
	| JOINTSTATUSESSTART y_JointStatusesType JOINTSTATUSESEND
	  {$$ = $2;}
	;

y_JointTorqueOrForce_XmlDecimal_0 :
	  /* empty */
	  {$$ = 0;}
	| JOINTTORQUEORFORCESTART ENDITEM {yyReadData = 1;} y_XmlDecimal
	  JOINTTORQUEORFORCEEND
	  {$$ = $4;}
	;

y_JointVelocity_XmlDecimal_0 :
	  /* empty */
	  {$$ = 0;}
	| JOINTVELOCITYSTART ENDITEM {yyReadData = 1;} y_XmlDecimal
	  JOINTVELOCITYEND
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

y_ListJointStatus_JointStatusType_1_u :
	  y_JointStatus_JointStatusType_1_u
	  {$$ = new std::list<JointStatusType *>;
	   $$->push_back($1);}
	| y_ListJointStatus_JointStatusType_1_u
	  y_JointStatus_JointStatusType_1_u
	  {$$ = $1;
	   $$->push_back($2);}
	;

y_LowerRight_PointType :
	  LOWERRIGHTSTART y_PointType LOWERRIGHTEND
	  {$$ = $2;}
	;

y_Moment_VectorType :
	  MOMENTSTART y_VectorType MOMENTEND
	  {$$ = $2;}
	;

y_Name_XmlID_0 :
	  /* empty */
	  {$$ = 0;}
	| NAMESTART ENDITEM {yyReadData = 1;} y_XmlID NAMEEND
	  {$$ = $4;}
	;

y_OrientationStandardDeviation_P1001 :
	  /* empty */
	  {$$ = 0;}
	| ORIENTATIONSTANDARDDEVIATIONSTART ENDITEM {yyReadData = 1;}
	  DATASTRING ORIENTATIONSTANDARDDEVIATIONEND
	  {$$ = new PositiveDecimalType($4);
	   if ($$->bad)
	     yyerror("bad OrientationStandardDeviation value");
	   free($4);
	  }
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

y_PoseOnlyLocationType :
	   ENDITEM y_Name_XmlID_0 y_RefObjectName_XmlIDREF_0
	  y_Timestamp_XmlDateTime_0 y_Point_PointType y_XAxis_VectorType
	  y_ZAxis_VectorType y_PositionStandardDeviation_Posi1002
	  y_OrientationStandardDeviation_P1001
	  {$$ = new PoseOnlyLocationType($2, $3, $4, $5, $6, $7, $8, $9);}
	;

y_PoseStatusType :
	   ENDITEM y_Name_XmlID_0 y_Pose_PoseOnlyLocationType
	  y_Twist_TwistType_0 y_Wrench_WrenchType_0
	  {$$ = new PoseStatusType($2, $3, $4, $5);}
	;

y_PoseStatus_PoseStatusType_0 :
	  /* empty */
	  {$$ = 0;}
	| POSESTATUSSTART y_PoseStatusType POSESTATUSEND
	  {$$ = $2;}
	;

y_Pose_PoseOnlyLocationType :
	  POSESTART y_PoseOnlyLocationType POSEEND
	  {$$ = $2;}
	;

y_PositionStandardDeviation_Posi1002 :
	  /* empty */
	  {$$ = 0;}
	| POSITIONSTANDARDDEVIATIONSTART ENDITEM {yyReadData = 1;}
	  DATASTRING POSITIONSTANDARDDEVIATIONEND
	  {$$ = new PositiveDecimalType($4);
	   if ($$->bad)
	     yyerror("bad PositionStandardDeviation value");
	   free($4);
	  }
	;

y_RefObjectName_XmlIDREF :
	  REFOBJECTNAMESTART ENDITEM {yyReadData = 1;} y_XmlIDREF
	  REFOBJECTNAMEEND
	  {$$ = $4;}
	;

y_RefObjectName_XmlIDREF_0 :
	  /* empty */
	  {$$ = 0;}
	| REFOBJECTNAMESTART ENDITEM {yyReadData = 1;} y_XmlIDREF
	  REFOBJECTNAMEEND
	  {$$ = $4;}
	;

y_Separation_XmlDecimal :
	  SEPARATIONSTART ENDITEM {yyReadData = 1;} y_XmlDecimal
	  SEPARATIONEND
	  {$$ = $4;}
	;

y_StatusID_XmlPositiveInteger :
	  STATUSIDSTART ENDITEM {yyReadData = 1;} y_XmlPositiveInteger
	  STATUSIDEND
	  {$$ = $4;}
	;

y_Timestamp_XmlDateTime_0 :
	  /* empty */
	  {$$ = 0;}
	| TIMESTAMPSTART ENDITEM {yyReadData = 1;} y_XmlDateTime
	  TIMESTAMPEND
	  {$$ = $4;}
	;

y_TwistType :
	   ENDITEM y_Name_XmlID_0 y_LinearVelocity_VectorType
	  y_AngularVelocity_VectorType
	  {$$ = new TwistType($2, $3, $4);}
	;

y_Twist_TwistType_0 :
	  /* empty */
	  {$$ = 0;}
	| TWISTSTART y_TwistType TWISTEND
	  {$$ = $2;}
	;

y_UpperLeft_PointType :
	  UPPERLEFTSTART y_PointType UPPERLEFTEND
	  {$$ = $2;}
	;

y_VectorType :
	   ENDITEM y_Name_XmlID_0 y_I_XmlDecimal y_J_XmlDecimal
	  y_K_XmlDecimal
	  {$$ = new VectorType($2, $3, $4, $5);}
	;

y_WrenchType :
	   ENDITEM y_Name_XmlID_0 y_Force_VectorType y_Moment_VectorType
	  {$$ = new WrenchType($2, $3, $4);}
	;

y_Wrench_WrenchType_0 :
	  /* empty */
	  {$$ = 0;}
	| WRENCHSTART y_WrenchType WRENCHEND
	  {$$ = $2;}
	;

y_XAxis_VectorType :
	  XAXISSTART y_VectorType XAXISEND
	  {$$ = $2;}
	;

y_X_XmlDecimal :
	  XSTART ENDITEM {yyReadData = 1;} y_XmlDecimal XEND
	  {$$ = $4;}
	;

y_Y_XmlDecimal :
	  YSTART ENDITEM {yyReadData = 1;} y_XmlDecimal YEND
	  {$$ = $4;}
	;

y_ZAxis_VectorType :
	  ZAXISSTART y_VectorType ZAXISEND
	  {$$ = $2;}
	;

y_Z_XmlDecimal :
	  ZSTART ENDITEM {yyReadData = 1;} y_XmlDecimal ZEND
	  {$$ = $4;}
	;

y_x_ParallelGripperStatusType :
	  PARALLELGRIPPERSTATUSTYPEDECL ENDITEM y_Name_XmlID_0
	  y_GripperName_XmlNMTOKEN y_Separation_XmlDecimal
	  {$$ = new ParallelGripperStatusType($3, $4, $5);
	   $$->printTypp = true;
	  }
	;

y_x_ThreeFingerGripperStatusType :
	  THREEFINGERGRIPPERSTATUSTYPEDECL ENDITEM y_Name_XmlID_0
	  y_GripperName_XmlNMTOKEN y_Finger1Position_FractionType_0
	  y_Finger2Position_FractionType_0 y_Finger3Position_FractionType_0
	  y_Finger1Force_XmlDecimal_0 y_Finger2Force_XmlDecimal_0
	  y_Finger3Force_XmlDecimal_0
	  {$$ = new ThreeFingerGripperStatusType($3, $4, $5, $6, $7, $8, $9, $10);
	   $$->printTypp = true;
	  }
	;

y_x_VacuumGripperStatusType :
	  VACUUMGRIPPERSTATUSTYPEDECL ENDITEM y_Name_XmlID_0
	  y_GripperName_XmlNMTOKEN y_IsPowered_XmlBoolean
	  {$$ = new VacuumGripperStatusType($3, $4, $5);
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
