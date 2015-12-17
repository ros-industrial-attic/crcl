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

#ifndef YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_CRCL_SRC_CRCLPROGRAMINSTANCEYACC_HH_INCLUDED
# define YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_CRCL_SRC_CRCLPROGRAMINSTANCEYACC_HH_INCLUDED
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
    DESCRIPTIONEND = 289,
    DESCRIPTIONSTART = 290,
    DWELLTIMEEND = 291,
    DWELLTIMESTART = 292,
    ENDCANONEND = 293,
    ENDCANONSTART = 294,
    ENDPOSITIONEND = 295,
    ENDPOSITIONSTART = 296,
    FORCEEND = 297,
    FORCESTART = 298,
    FRACTIONEND = 299,
    FRACTIONSTART = 300,
    IEND = 301,
    ISTART = 302,
    INITCANONEND = 303,
    INITCANONSTART = 304,
    JEND = 305,
    JSTART = 306,
    JOINTACCELEND = 307,
    JOINTACCELSTART = 308,
    JOINTDETAILSEND = 309,
    JOINTDETAILSSTART = 310,
    JOINTNUMBEREND = 311,
    JOINTNUMBERSTART = 312,
    JOINTPOSITIONEND = 313,
    JOINTPOSITIONSTART = 314,
    JOINTSPEEDEND = 315,
    JOINTSPEEDSTART = 316,
    KEND = 317,
    KSTART = 318,
    LINEARVELOCITYEND = 319,
    LINEARVELOCITYSTART = 320,
    LOWERRIGHTEND = 321,
    LOWERRIGHTSTART = 322,
    MESSAGEEND = 323,
    MESSAGESTART = 324,
    MIDDLECOMMANDEND = 325,
    MIDDLECOMMANDSTART = 326,
    MOMENTEND = 327,
    MOMENTSTART = 328,
    MOVESTRAIGHTEND = 329,
    MOVESTRAIGHTSTART = 330,
    NAMEEND = 331,
    NAMESTART = 332,
    NUMPOSITIONSEND = 333,
    NUMPOSITIONSSTART = 334,
    ORIENTATIONSTANDARDDEVIATIONEND = 335,
    ORIENTATIONSTANDARDDEVIATIONSTART = 336,
    PARAMETERNAMEEND = 337,
    PARAMETERNAMESTART = 338,
    PARAMETERSETTINGEND = 339,
    PARAMETERSETTINGSTART = 340,
    PARAMETERVALUEEND = 341,
    PARAMETERVALUESTART = 342,
    POINTEND = 343,
    POINTSTART = 344,
    POSITIONSTANDARDDEVIATIONEND = 345,
    POSITIONSTANDARDDEVIATIONSTART = 346,
    PROGRAMTEXTEND = 347,
    PROGRAMTEXTSTART = 348,
    REFOBJECTNAMEEND = 349,
    REFOBJECTNAMESTART = 350,
    REPORTPOSITIONEND = 351,
    REPORTPOSITIONSTART = 352,
    REPORTTORQUEORFORCEEND = 353,
    REPORTTORQUEORFORCESTART = 354,
    REPORTVELOCITYEND = 355,
    REPORTVELOCITYSTART = 356,
    RESETALLEND = 357,
    RESETALLSTART = 358,
    ROTACCELEND = 359,
    ROTACCELSTART = 360,
    ROTSPEEDEND = 361,
    ROTSPEEDSTART = 362,
    SETTINGEND = 363,
    SETTINGSTART = 364,
    STARTPOSITIONEND = 365,
    STARTPOSITIONSTART = 366,
    STOPCONDITIONEND = 367,
    STOPCONDITIONSTART = 368,
    TIMESTAMPEND = 369,
    TIMESTAMPSTART = 370,
    TOLERANCEEND = 371,
    TOLERANCESTART = 372,
    TRANSACCELEND = 373,
    TRANSACCELSTART = 374,
    TRANSSPEEDEND = 375,
    TRANSSPEEDSTART = 376,
    TURNEND = 377,
    TURNSTART = 378,
    UNITNAMEEND = 379,
    UNITNAMESTART = 380,
    UPPERLEFTEND = 381,
    UPPERLEFTSTART = 382,
    WAYPOINTEND = 383,
    WAYPOINTSTART = 384,
    XAXISTOLERANCEEND = 385,
    XAXISTOLERANCESTART = 386,
    XAXISEND = 387,
    XAXISSTART = 388,
    XPOINTTOLERANCEEND = 389,
    XPOINTTOLERANCESTART = 390,
    XEND = 391,
    XSTART = 392,
    YPOINTTOLERANCEEND = 393,
    YPOINTTOLERANCESTART = 394,
    YEND = 395,
    YSTART = 396,
    ZAXISTOLERANCEEND = 397,
    ZAXISTOLERANCESTART = 398,
    ZAXISEND = 399,
    ZAXISSTART = 400,
    ZPOINTTOLERANCEEND = 401,
    ZPOINTTOLERANCESTART = 402,
    ZEND = 403,
    ZSTART = 404,
    ACTUATEJOINTTYPEDECL = 405,
    ACTUATEJOINTSTYPEDECL = 406,
    CRCLCOMMANDTYPEDECL = 407,
    CRCLPROGRAMTYPEDECL = 408,
    CLOSETOOLCHANGERTYPEDECL = 409,
    CONFIGUREJOINTREPORTTYPEDECL = 410,
    CONFIGUREJOINTREPORTSTYPEDECL = 411,
    DWELLTYPEDECL = 412,
    ENDCANONTYPEDECL = 413,
    GETSTATUSTYPEDECL = 414,
    INITCANONTYPEDECL = 415,
    JOINTDETAILSTYPEDECL = 416,
    JOINTFORCETORQUETYPEDECL = 417,
    JOINTSPEEDACCELTYPEDECL = 418,
    MESSAGETYPEDECL = 419,
    MIDDLECOMMANDTYPEDECL = 420,
    MOVESCREWTYPEDECL = 421,
    MOVETHROUGHTOTYPEDECL = 422,
    MOVETOTYPEDECL = 423,
    OPENTOOLCHANGERTYPEDECL = 424,
    PARAMETERSETTINGTYPEDECL = 425,
    PHYSICALLOCATIONTYPEDECL = 426,
    POINTTYPEDECL = 427,
    POSEANDSETTYPEDECL = 428,
    POSELOCATIONINTYPEDECL = 429,
    POSELOCATIONONTYPEDECL = 430,
    POSELOCATIONTYPEDECL = 431,
    POSEONLYLOCATIONTYPEDECL = 432,
    POSETOLERANCETYPEDECL = 433,
    REGIONOFINTERESTTYPEDECL = 434,
    RELATIVELOCATIONINTYPEDECL = 435,
    RELATIVELOCATIONONTYPEDECL = 436,
    RELATIVELOCATIONTYPEDECL = 437,
    ROTACCELABSOLUTETYPEDECL = 438,
    ROTACCELRELATIVETYPEDECL = 439,
    ROTACCELTYPEDECL = 440,
    ROTSPEEDABSOLUTETYPEDECL = 441,
    ROTSPEEDRELATIVETYPEDECL = 442,
    ROTSPEEDTYPEDECL = 443,
    RUNPROGRAMTYPEDECL = 444,
    SETANGLEUNITSTYPEDECL = 445,
    SETENDEFFECTORPARAMETERSTYPEDECL = 446,
    SETENDEFFECTORTYPEDECL = 447,
    SETENDPOSETOLERANCETYPEDECL = 448,
    SETFORCEUNITSTYPEDECL = 449,
    SETINTERMEDIATEPOSETOLERANCETYPEDECL = 450,
    SETLENGTHUNITSTYPEDECL = 451,
    SETMOTIONCOORDINATIONTYPEDECL = 452,
    SETROBOTPARAMETERSTYPEDECL = 453,
    SETROTACCELTYPEDECL = 454,
    SETROTSPEEDTYPEDECL = 455,
    SETTORQUEUNITSTYPEDECL = 456,
    SETTRANSACCELTYPEDECL = 457,
    SETTRANSSPEEDTYPEDECL = 458,
    STOPMOTIONTYPEDECL = 459,
    TRANSACCELABSOLUTETYPEDECL = 460,
    TRANSACCELRELATIVETYPEDECL = 461,
    TRANSACCELTYPEDECL = 462,
    TRANSSPEEDABSOLUTETYPEDECL = 463,
    TRANSSPEEDRELATIVETYPEDECL = 464,
    TRANSSPEEDTYPEDECL = 465,
    TWISTTYPEDECL = 466,
    VECTORTYPEDECL = 467,
    WRENCHTYPEDECL = 468
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
  XmlDateTime *                       XmlDateTimeVal;
  XmlDecimal *                        XmlDecimalVal;
  XmlID *                             XmlIDVal;
  XmlIDREF *                          XmlIDREFVal;
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

#endif /* !YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_CRCL_SRC_CRCLPROGRAMINSTANCEYACC_HH_INCLUDED  */
