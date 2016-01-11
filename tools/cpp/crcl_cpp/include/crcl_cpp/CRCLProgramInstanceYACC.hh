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

#ifndef YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_TOOLS_CPP_CRCL_CPP_SRC_CRCLPROGRAMINSTANCEYACC_HH_INCLUDED
# define YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_TOOLS_CPP_CRCL_CPP_SRC_CRCLPROGRAMINSTANCEYACC_HH_INCLUDED
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
    CRCLPROGRAMEND = 279,
    CRCLPROGRAMSTART = 280,
    CHANGERATEEND = 281,
    CHANGERATESTART = 282,
    COMMANDIDEND = 283,
    COMMANDIDSTART = 284,
    CONFIGUREJOINTREPORTEND = 285,
    CONFIGUREJOINTREPORTSTART = 286,
    COORDINATEDEND = 287,
    COORDINATEDSTART = 288,
    DWELLTIMEEND = 289,
    DWELLTIMESTART = 290,
    ENDCANONEND = 291,
    ENDCANONSTART = 292,
    ENDPOSITIONEND = 293,
    ENDPOSITIONSTART = 294,
    FORCEEND = 295,
    FORCESTART = 296,
    FRACTIONEND = 297,
    FRACTIONSTART = 298,
    IEND = 299,
    ISTART = 300,
    INITCANONEND = 301,
    INITCANONSTART = 302,
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
    MESSAGEEND = 319,
    MESSAGESTART = 320,
    MIDDLECOMMANDEND = 321,
    MIDDLECOMMANDSTART = 322,
    MOMENTEND = 323,
    MOMENTSTART = 324,
    MOVESTRAIGHTEND = 325,
    MOVESTRAIGHTSTART = 326,
    NAMEEND = 327,
    NAMESTART = 328,
    NUMPOSITIONSEND = 329,
    NUMPOSITIONSSTART = 330,
    PARAMETERNAMEEND = 331,
    PARAMETERNAMESTART = 332,
    PARAMETERSETTINGEND = 333,
    PARAMETERSETTINGSTART = 334,
    PARAMETERVALUEEND = 335,
    PARAMETERVALUESTART = 336,
    POINTEND = 337,
    POINTSTART = 338,
    PROGRAMTEXTEND = 339,
    PROGRAMTEXTSTART = 340,
    REPORTPOSITIONEND = 341,
    REPORTPOSITIONSTART = 342,
    REPORTTORQUEORFORCEEND = 343,
    REPORTTORQUEORFORCESTART = 344,
    REPORTVELOCITYEND = 345,
    REPORTVELOCITYSTART = 346,
    RESETALLEND = 347,
    RESETALLSTART = 348,
    ROTACCELEND = 349,
    ROTACCELSTART = 350,
    ROTSPEEDEND = 351,
    ROTSPEEDSTART = 352,
    SETTINGEND = 353,
    SETTINGSTART = 354,
    STARTPOSITIONEND = 355,
    STARTPOSITIONSTART = 356,
    STOPCONDITIONEND = 357,
    STOPCONDITIONSTART = 358,
    TOLERANCEEND = 359,
    TOLERANCESTART = 360,
    TRANSACCELEND = 361,
    TRANSACCELSTART = 362,
    TRANSSPEEDEND = 363,
    TRANSSPEEDSTART = 364,
    TURNEND = 365,
    TURNSTART = 366,
    UNITNAMEEND = 367,
    UNITNAMESTART = 368,
    WAYPOINTEND = 369,
    WAYPOINTSTART = 370,
    XAXISTOLERANCEEND = 371,
    XAXISTOLERANCESTART = 372,
    XAXISEND = 373,
    XAXISSTART = 374,
    XPOINTTOLERANCEEND = 375,
    XPOINTTOLERANCESTART = 376,
    XEND = 377,
    XSTART = 378,
    YPOINTTOLERANCEEND = 379,
    YPOINTTOLERANCESTART = 380,
    YEND = 381,
    YSTART = 382,
    ZAXISTOLERANCEEND = 383,
    ZAXISTOLERANCESTART = 384,
    ZAXISEND = 385,
    ZAXISSTART = 386,
    ZPOINTTOLERANCEEND = 387,
    ZPOINTTOLERANCESTART = 388,
    ZEND = 389,
    ZSTART = 390,
    ACTUATEJOINTTYPEDECL = 391,
    ACTUATEJOINTSTYPEDECL = 392,
    CRCLCOMMANDTYPEDECL = 393,
    CRCLPROGRAMTYPEDECL = 394,
    CLOSETOOLCHANGERTYPEDECL = 395,
    CONFIGUREJOINTREPORTTYPEDECL = 396,
    CONFIGUREJOINTREPORTSTYPEDECL = 397,
    DWELLTYPEDECL = 398,
    ENDCANONTYPEDECL = 399,
    GETSTATUSTYPEDECL = 400,
    INITCANONTYPEDECL = 401,
    JOINTDETAILSTYPEDECL = 402,
    JOINTFORCETORQUETYPEDECL = 403,
    JOINTSPEEDACCELTYPEDECL = 404,
    MESSAGETYPEDECL = 405,
    MIDDLECOMMANDTYPEDECL = 406,
    MOVESCREWTYPEDECL = 407,
    MOVETHROUGHTOTYPEDECL = 408,
    MOVETOTYPEDECL = 409,
    OPENTOOLCHANGERTYPEDECL = 410,
    PARAMETERSETTINGTYPEDECL = 411,
    POINTTYPEDECL = 412,
    POSEANDSETTYPEDECL = 413,
    POSETOLERANCETYPEDECL = 414,
    POSETYPEDECL = 415,
    ROTACCELABSOLUTETYPEDECL = 416,
    ROTACCELRELATIVETYPEDECL = 417,
    ROTACCELTYPEDECL = 418,
    ROTSPEEDABSOLUTETYPEDECL = 419,
    ROTSPEEDRELATIVETYPEDECL = 420,
    ROTSPEEDTYPEDECL = 421,
    RUNPROGRAMTYPEDECL = 422,
    SETANGLEUNITSTYPEDECL = 423,
    SETENDEFFECTORPARAMETERSTYPEDECL = 424,
    SETENDEFFECTORTYPEDECL = 425,
    SETENDPOSETOLERANCETYPEDECL = 426,
    SETFORCEUNITSTYPEDECL = 427,
    SETINTERMEDIATEPOSETOLERANCETYPEDECL = 428,
    SETLENGTHUNITSTYPEDECL = 429,
    SETMOTIONCOORDINATIONTYPEDECL = 430,
    SETROBOTPARAMETERSTYPEDECL = 431,
    SETROTACCELTYPEDECL = 432,
    SETROTSPEEDTYPEDECL = 433,
    SETTORQUEUNITSTYPEDECL = 434,
    SETTRANSACCELTYPEDECL = 435,
    SETTRANSSPEEDTYPEDECL = 436,
    STOPMOTIONTYPEDECL = 437,
    TRANSACCELABSOLUTETYPEDECL = 438,
    TRANSACCELRELATIVETYPEDECL = 439,
    TRANSACCELTYPEDECL = 440,
    TRANSSPEEDABSOLUTETYPEDECL = 441,
    TRANSSPEEDRELATIVETYPEDECL = 442,
    TRANSSPEEDTYPEDECL = 443,
    TWISTTYPEDECL = 444,
    VECTORTYPEDECL = 445,
    WRENCHTYPEDECL = 446
  };
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE YYSTYPE;
union YYSTYPE
{


  SchemaLocation *                    SchemaLocationVal;
  XmlHeaderForCRCLProgram *           XmlHeaderForCRCLProgramVal;
  XmlVersion *                        XmlVersionVal;
  int *                               iVal;
  char *                              sVal;
  XmlBoolean *                        XmlBooleanVal;
  XmlDecimal *                        XmlDecimalVal;
  XmlID *                             XmlIDVal;
  XmlPositiveInteger *                XmlPositiveIntegerVal;
  XmlString *                         XmlStringVal;
  XmlToken *                          XmlTokenVal;

  CRCLProgramFile *                   CRCLProgramFileVal;

  ActuateJointType *                  ActuateJointTypeVal;
  ActuateJointsType *                 ActuateJointsTypeVal;
  AngleUnitEnumType *                 AngleUnitEnumTypeVal;
  CRCLProgramType *                   CRCLProgramTypeVal;
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
  std::list<MiddleCommandType *> *    ListMiddleCommandTypeVal;
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

#endif /* !YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_TOOLS_CPP_CRCL_CPP_SRC_CRCLPROGRAMINSTANCEYACC_HH_INCLUDED  */
