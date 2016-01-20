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

#ifndef YY_YY_USR_LOCAL_PROCTOR_INDIGO_WS_SRC_CRCL_TOOLS_CPP_CRCL_CPP_SRC_CRCLCOMMANDINSTANCEYACC_HH_INCLUDED
# define YY_YY_USR_LOCAL_PROCTOR_INDIGO_WS_SRC_CRCL_TOOLS_CPP_CRCL_CPP_SRC_CRCLCOMMANDINSTANCEYACC_HH_INCLUDED
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
    DWELLTIMEEND = 291,
    DWELLTIMESTART = 292,
    ENDPOSITIONEND = 293,
    ENDPOSITIONSTART = 294,
    FORCEEND = 295,
    FORCESTART = 296,
    FRACTIONEND = 297,
    FRACTIONSTART = 298,
    IEND = 299,
    ISTART = 300,
    JEND = 301,
    JSTART = 302,
    JOINTACCELEND = 303,
    JOINTACCELSTART = 304,
    JOINTDETAILSEND = 305,
    JOINTDETAILSSTART = 306,
    JOINTNUMBEREND = 307,
    JOINTNUMBERSTART = 308,
    JOINTPOSITIONEND = 309,
    JOINTPOSITIONSTART = 310,
    JOINTSPEEDEND = 311,
    JOINTSPEEDSTART = 312,
    KEND = 313,
    KSTART = 314,
    LINEARVELOCITYEND = 315,
    LINEARVELOCITYSTART = 316,
    MESSAGEEND = 317,
    MESSAGESTART = 318,
    MOMENTEND = 319,
    MOMENTSTART = 320,
    MOVESTRAIGHTEND = 321,
    MOVESTRAIGHTSTART = 322,
    NAMEEND = 323,
    NAMESTART = 324,
    NUMPOSITIONSEND = 325,
    NUMPOSITIONSSTART = 326,
    PARAMETERNAMEEND = 327,
    PARAMETERNAMESTART = 328,
    PARAMETERSETTINGEND = 329,
    PARAMETERSETTINGSTART = 330,
    PARAMETERVALUEEND = 331,
    PARAMETERVALUESTART = 332,
    POINTEND = 333,
    POINTSTART = 334,
    PROGRAMTEXTEND = 335,
    PROGRAMTEXTSTART = 336,
    REPORTPOSITIONEND = 337,
    REPORTPOSITIONSTART = 338,
    REPORTTORQUEORFORCEEND = 339,
    REPORTTORQUEORFORCESTART = 340,
    REPORTVELOCITYEND = 341,
    REPORTVELOCITYSTART = 342,
    RESETALLEND = 343,
    RESETALLSTART = 344,
    ROTACCELEND = 345,
    ROTACCELSTART = 346,
    ROTSPEEDEND = 347,
    ROTSPEEDSTART = 348,
    SETTINGEND = 349,
    SETTINGSTART = 350,
    STARTPOSITIONEND = 351,
    STARTPOSITIONSTART = 352,
    STOPCONDITIONEND = 353,
    STOPCONDITIONSTART = 354,
    TOLERANCEEND = 355,
    TOLERANCESTART = 356,
    TRANSACCELEND = 357,
    TRANSACCELSTART = 358,
    TRANSSPEEDEND = 359,
    TRANSSPEEDSTART = 360,
    TURNEND = 361,
    TURNSTART = 362,
    UNITNAMEEND = 363,
    UNITNAMESTART = 364,
    WAYPOINTEND = 365,
    WAYPOINTSTART = 366,
    XAXISTOLERANCEEND = 367,
    XAXISTOLERANCESTART = 368,
    XAXISEND = 369,
    XAXISSTART = 370,
    XPOINTTOLERANCEEND = 371,
    XPOINTTOLERANCESTART = 372,
    XEND = 373,
    XSTART = 374,
    YPOINTTOLERANCEEND = 375,
    YPOINTTOLERANCESTART = 376,
    YEND = 377,
    YSTART = 378,
    ZAXISTOLERANCEEND = 379,
    ZAXISTOLERANCESTART = 380,
    ZAXISEND = 381,
    ZAXISSTART = 382,
    ZPOINTTOLERANCEEND = 383,
    ZPOINTTOLERANCESTART = 384,
    ZEND = 385,
    ZSTART = 386,
    ACTUATEJOINTTYPEDECL = 387,
    ACTUATEJOINTSTYPEDECL = 388,
    CRCLCOMMANDINSTANCETYPEDECL = 389,
    CRCLCOMMANDTYPEDECL = 390,
    CLOSETOOLCHANGERTYPEDECL = 391,
    CONFIGUREJOINTREPORTTYPEDECL = 392,
    CONFIGUREJOINTREPORTSTYPEDECL = 393,
    DWELLTYPEDECL = 394,
    ENDCANONTYPEDECL = 395,
    GETSTATUSTYPEDECL = 396,
    INITCANONTYPEDECL = 397,
    JOINTDETAILSTYPEDECL = 398,
    JOINTFORCETORQUETYPEDECL = 399,
    JOINTSPEEDACCELTYPEDECL = 400,
    MESSAGETYPEDECL = 401,
    MIDDLECOMMANDTYPEDECL = 402,
    MOVESCREWTYPEDECL = 403,
    MOVETHROUGHTOTYPEDECL = 404,
    MOVETOTYPEDECL = 405,
    OPENTOOLCHANGERTYPEDECL = 406,
    PARAMETERSETTINGTYPEDECL = 407,
    POINTTYPEDECL = 408,
    POSEANDSETTYPEDECL = 409,
    POSETOLERANCETYPEDECL = 410,
    POSETYPEDECL = 411,
    ROTACCELABSOLUTETYPEDECL = 412,
    ROTACCELRELATIVETYPEDECL = 413,
    ROTACCELTYPEDECL = 414,
    ROTSPEEDABSOLUTETYPEDECL = 415,
    ROTSPEEDRELATIVETYPEDECL = 416,
    ROTSPEEDTYPEDECL = 417,
    RUNPROGRAMTYPEDECL = 418,
    SETANGLEUNITSTYPEDECL = 419,
    SETENDEFFECTORPARAMETERSTYPEDECL = 420,
    SETENDEFFECTORTYPEDECL = 421,
    SETENDPOSETOLERANCETYPEDECL = 422,
    SETFORCEUNITSTYPEDECL = 423,
    SETINTERMEDIATEPOSETOLERANCETYPEDECL = 424,
    SETLENGTHUNITSTYPEDECL = 425,
    SETMOTIONCOORDINATIONTYPEDECL = 426,
    SETROBOTPARAMETERSTYPEDECL = 427,
    SETROTACCELTYPEDECL = 428,
    SETROTSPEEDTYPEDECL = 429,
    SETTORQUEUNITSTYPEDECL = 430,
    SETTRANSACCELTYPEDECL = 431,
    SETTRANSSPEEDTYPEDECL = 432,
    STOPMOTIONTYPEDECL = 433,
    TRANSACCELABSOLUTETYPEDECL = 434,
    TRANSACCELRELATIVETYPEDECL = 435,
    TRANSACCELTYPEDECL = 436,
    TRANSSPEEDABSOLUTETYPEDECL = 437,
    TRANSSPEEDRELATIVETYPEDECL = 438,
    TRANSSPEEDTYPEDECL = 439,
    TWISTTYPEDECL = 440,
    VECTORTYPEDECL = 441,
    WRENCHTYPEDECL = 442
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


};
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;

int yyparse (void);

#endif /* !YY_YY_USR_LOCAL_PROCTOR_INDIGO_WS_SRC_CRCL_TOOLS_CPP_CRCL_CPP_SRC_CRCLCOMMANDINSTANCEYACC_HH_INCLUDED  */
