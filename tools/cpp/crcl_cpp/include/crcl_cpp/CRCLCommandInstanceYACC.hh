/* A Bison parser, made by GNU Bison 3.0.2.  */

/* Bison interface for Yacc-like parsers in C

   Copyright (C) 1984, 1989-1990, 2000-2013 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

#ifndef YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_TOOLS_CPP_CRCL_CPP_SRC_CRCLCOMMANDINSTANCEYACC_HH_INCLUDED
# define YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_TOOLS_CPP_CRCL_CPP_SRC_CRCLCOMMANDINSTANCEYACC_HH_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int yydebug;
#endif

/* Token type.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
    BAD = 258,
    DATASTRING = 259,
    ENCODING = 260,
    ENDITEM = 261,
    ENDVERSION = 262,
    SCHEMALOCATION = 263,
    STARTVERSION = 264,
    TERMINALSTRING = 265,
    XMLNSPREFIX = 266,
    XMLNSTARGET = 267,
    XMLVERSION = 268,
    ACTUATEJOINTEND = 269,
    ACTUATEJOINTSTART = 270,
    ANGULARVELOCITYEND = 271,
    ANGULARVELOCITYSTART = 272,
    AXIALDISTANCEFREEEND = 273,
    AXIALDISTANCEFREESTART = 274,
    AXIALDISTANCESCREWEND = 275,
    AXIALDISTANCESCREWSTART = 276,
    AXISPOINTEND = 277,
    AXISPOINTSTART = 278,
    CRCLCOMMANDINSTANCEEND = 279,
    CRCLCOMMANDINSTANCESTART = 280,
    CRCLCOMMANDEND = 281,
    CRCLCOMMANDSTART = 282,
    CHANGERATEEND = 283,
    CHANGERATESTART = 284,
    COMMANDIDEND = 285,
    COMMANDIDSTART = 286,
    CONFIGUREJOINTREPORTEND = 287,
    CONFIGUREJOINTREPORTSTART = 288,
    COORDINATEDEND = 289,
    COORDINATEDSTART = 290,
    DESCRIPTIONEND = 291,
    DESCRIPTIONSTART = 292,
    DWELLTIMEEND = 293,
    DWELLTIMESTART = 294,
    ENDPOSITIONEND = 295,
    ENDPOSITIONSTART = 296,
    FORCEEND = 297,
    FORCESTART = 298,
    FRACTIONEND = 299,
    FRACTIONSTART = 300,
    IEND = 301,
    ISTART = 302,
    JEND = 303,
    JSTART = 304,
    JOINTACCELEND = 305,
    JOINTACCELSTART = 306,
    JOINTDETAILSEND = 307,
    JOINTDETAILSSTART = 308,
    JOINTNUMBEREND = 309,
    JOINTNUMBERSTART = 310,
    JOINTPOSITIONEND = 311,
    JOINTPOSITIONSTART = 312,
    JOINTSPEEDEND = 313,
    JOINTSPEEDSTART = 314,
    KEND = 315,
    KSTART = 316,
    LINEARVELOCITYEND = 317,
    LINEARVELOCITYSTART = 318,
    LOWERRIGHTEND = 319,
    LOWERRIGHTSTART = 320,
    MESSAGEEND = 321,
    MESSAGESTART = 322,
    MOMENTEND = 323,
    MOMENTSTART = 324,
    MOVESTRAIGHTEND = 325,
    MOVESTRAIGHTSTART = 326,
    NAMEEND = 327,
    NAMESTART = 328,
    NUMPOSITIONSEND = 329,
    NUMPOSITIONSSTART = 330,
    ORIENTATIONSTANDARDDEVIATIONEND = 331,
    ORIENTATIONSTANDARDDEVIATIONSTART = 332,
    PARAMETERNAMEEND = 333,
    PARAMETERNAMESTART = 334,
    PARAMETERSETTINGEND = 335,
    PARAMETERSETTINGSTART = 336,
    PARAMETERVALUEEND = 337,
    PARAMETERVALUESTART = 338,
    POINTEND = 339,
    POINTSTART = 340,
    POSITIONSTANDARDDEVIATIONEND = 341,
    POSITIONSTANDARDDEVIATIONSTART = 342,
    PROGRAMTEXTEND = 343,
    PROGRAMTEXTSTART = 344,
    REFOBJECTNAMEEND = 345,
    REFOBJECTNAMESTART = 346,
    REPORTPOSITIONEND = 347,
    REPORTPOSITIONSTART = 348,
    REPORTTORQUEORFORCEEND = 349,
    REPORTTORQUEORFORCESTART = 350,
    REPORTVELOCITYEND = 351,
    REPORTVELOCITYSTART = 352,
    RESETALLEND = 353,
    RESETALLSTART = 354,
    ROTACCELEND = 355,
    ROTACCELSTART = 356,
    ROTSPEEDEND = 357,
    ROTSPEEDSTART = 358,
    SETTINGEND = 359,
    SETTINGSTART = 360,
    STARTPOSITIONEND = 361,
    STARTPOSITIONSTART = 362,
    STOPCONDITIONEND = 363,
    STOPCONDITIONSTART = 364,
    TIMESTAMPEND = 365,
    TIMESTAMPSTART = 366,
    TOLERANCEEND = 367,
    TOLERANCESTART = 368,
    TRANSACCELEND = 369,
    TRANSACCELSTART = 370,
    TRANSSPEEDEND = 371,
    TRANSSPEEDSTART = 372,
    TURNEND = 373,
    TURNSTART = 374,
    UNITNAMEEND = 375,
    UNITNAMESTART = 376,
    UPPERLEFTEND = 377,
    UPPERLEFTSTART = 378,
    WAYPOINTEND = 379,
    WAYPOINTSTART = 380,
    XAXISTOLERANCEEND = 381,
    XAXISTOLERANCESTART = 382,
    XAXISEND = 383,
    XAXISSTART = 384,
    XPOINTTOLERANCEEND = 385,
    XPOINTTOLERANCESTART = 386,
    XEND = 387,
    XSTART = 388,
    YPOINTTOLERANCEEND = 389,
    YPOINTTOLERANCESTART = 390,
    YEND = 391,
    YSTART = 392,
    ZAXISTOLERANCEEND = 393,
    ZAXISTOLERANCESTART = 394,
    ZAXISEND = 395,
    ZAXISSTART = 396,
    ZPOINTTOLERANCEEND = 397,
    ZPOINTTOLERANCESTART = 398,
    ZEND = 399,
    ZSTART = 400,
    ACTUATEJOINTTYPEDECL = 401,
    ACTUATEJOINTSTYPEDECL = 402,
    CRCLCOMMANDINSTANCETYPEDECL = 403,
    CRCLCOMMANDTYPEDECL = 404,
    CLOSETOOLCHANGERTYPEDECL = 405,
    CONFIGUREJOINTREPORTTYPEDECL = 406,
    CONFIGUREJOINTREPORTSTYPEDECL = 407,
    DWELLTYPEDECL = 408,
    ENDCANONTYPEDECL = 409,
    GETSTATUSTYPEDECL = 410,
    INITCANONTYPEDECL = 411,
    JOINTDETAILSTYPEDECL = 412,
    JOINTFORCETORQUETYPEDECL = 413,
    JOINTSPEEDACCELTYPEDECL = 414,
    MESSAGETYPEDECL = 415,
    MIDDLECOMMANDTYPEDECL = 416,
    MOVESCREWTYPEDECL = 417,
    MOVETHROUGHTOTYPEDECL = 418,
    MOVETOTYPEDECL = 419,
    OPENTOOLCHANGERTYPEDECL = 420,
    PARAMETERSETTINGTYPEDECL = 421,
    PHYSICALLOCATIONTYPEDECL = 422,
    POINTTYPEDECL = 423,
    POSEANDSETTYPEDECL = 424,
    POSELOCATIONINTYPEDECL = 425,
    POSELOCATIONONTYPEDECL = 426,
    POSELOCATIONTYPEDECL = 427,
    POSEONLYLOCATIONTYPEDECL = 428,
    POSETOLERANCETYPEDECL = 429,
    REGIONOFINTERESTTYPEDECL = 430,
    RELATIVELOCATIONINTYPEDECL = 431,
    RELATIVELOCATIONONTYPEDECL = 432,
    RELATIVELOCATIONTYPEDECL = 433,
    ROTACCELABSOLUTETYPEDECL = 434,
    ROTACCELRELATIVETYPEDECL = 435,
    ROTACCELTYPEDECL = 436,
    ROTSPEEDABSOLUTETYPEDECL = 437,
    ROTSPEEDRELATIVETYPEDECL = 438,
    ROTSPEEDTYPEDECL = 439,
    RUNPROGRAMTYPEDECL = 440,
    SETANGLEUNITSTYPEDECL = 441,
    SETENDEFFECTORPARAMETERSTYPEDECL = 442,
    SETENDEFFECTORTYPEDECL = 443,
    SETENDPOSETOLERANCETYPEDECL = 444,
    SETFORCEUNITSTYPEDECL = 445,
    SETINTERMEDIATEPOSETOLERANCETYPEDECL = 446,
    SETLENGTHUNITSTYPEDECL = 447,
    SETMOTIONCOORDINATIONTYPEDECL = 448,
    SETROBOTPARAMETERSTYPEDECL = 449,
    SETROTACCELTYPEDECL = 450,
    SETROTSPEEDTYPEDECL = 451,
    SETTORQUEUNITSTYPEDECL = 452,
    SETTRANSACCELTYPEDECL = 453,
    SETTRANSSPEEDTYPEDECL = 454,
    STOPMOTIONTYPEDECL = 455,
    TRANSACCELABSOLUTETYPEDECL = 456,
    TRANSACCELRELATIVETYPEDECL = 457,
    TRANSACCELTYPEDECL = 458,
    TRANSSPEEDABSOLUTETYPEDECL = 459,
    TRANSSPEEDRELATIVETYPEDECL = 460,
    TRANSSPEEDTYPEDECL = 461,
    TWISTTYPEDECL = 462,
    VECTORTYPEDECL = 463,
    WRENCHTYPEDECL = 464
  };
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE YYSTYPE;
union YYSTYPE
{


  SchemaLocation *                    SchemaLocationVal;
  XmlHeaderForCRCLCommandInstance *   XmlHeaderForCRCLCommandInstanceVal;
  XmlVersion *                        XmlVersionVal;
  int *                               iVal;
  char *                              sVal;
  XmlBoolean *                        XmlBooleanVal;
  XmlDateTime *                       XmlDateTimeVal;
  XmlDecimal *                        XmlDecimalVal;
  XmlID *                             XmlIDVal;
  XmlIDREF *                          XmlIDREFVal;
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
  std::list<PoseOnlyLocationType *> * ListPoseOnlyLocationTypeVal;
  MessageType *                       MessageTypeVal;
  MiddleCommandType *                 MiddleCommandTypeVal;
  MoveScrewType *                     MoveScrewTypeVal;
  MoveThroughToType *                 MoveThroughToTypeVal;
  MoveToType *                        MoveToTypeVal;
  OpenToolChangerType *               OpenToolChangerTypeVal;
  ParameterSettingType *              ParameterSettingTypeVal;
  PointType *                         PointTypeVal;
  PoseAndSetType *                    PoseAndSetTypeVal;
  PoseOnlyLocationType *              PoseOnlyLocationTypeVal;
  PoseToleranceType *                 PoseToleranceTypeVal;
  PositiveDecimalType *               PositiveDecimalTypeVal;
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


};
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;

int yyparse (void);

#endif /* !YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_TOOLS_CPP_CRCL_CPP_SRC_CRCLCOMMANDINSTANCEYACC_HH_INCLUDED  */
