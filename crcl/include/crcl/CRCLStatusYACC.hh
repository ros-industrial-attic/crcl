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

#ifndef YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_CRCL_SRC_CRCLSTATUSYACC_HH_INCLUDED
# define YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_CRCL_SRC_CRCLSTATUSYACC_HH_INCLUDED
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
    ANGULARVELOCITYEND = 269,
    ANGULARVELOCITYSTART = 270,
    CRCLSTATUSEND = 271,
    CRCLSTATUSSTART = 272,
    COMMANDIDEND = 273,
    COMMANDIDSTART = 274,
    COMMANDSTATEEND = 275,
    COMMANDSTATESTART = 276,
    COMMANDSTATUSEND = 277,
    COMMANDSTATUSSTART = 278,
    DESCRIPTIONEND = 279,
    DESCRIPTIONSTART = 280,
    FINGER1FORCEEND = 281,
    FINGER1FORCESTART = 282,
    FINGER1POSITIONEND = 283,
    FINGER1POSITIONSTART = 284,
    FINGER2FORCEEND = 285,
    FINGER2FORCESTART = 286,
    FINGER2POSITIONEND = 287,
    FINGER2POSITIONSTART = 288,
    FINGER3FORCEEND = 289,
    FINGER3FORCESTART = 290,
    FINGER3POSITIONEND = 291,
    FINGER3POSITIONSTART = 292,
    FORCEEND = 293,
    FORCESTART = 294,
    GRIPPERNAMEEND = 295,
    GRIPPERNAMESTART = 296,
    GRIPPERSTATUSEND = 297,
    GRIPPERSTATUSSTART = 298,
    IEND = 299,
    ISTART = 300,
    ISPOWEREDEND = 301,
    ISPOWEREDSTART = 302,
    JEND = 303,
    JSTART = 304,
    JOINTNUMBEREND = 305,
    JOINTNUMBERSTART = 306,
    JOINTPOSITIONEND = 307,
    JOINTPOSITIONSTART = 308,
    JOINTSTATUSEND = 309,
    JOINTSTATUSSTART = 310,
    JOINTSTATUSESEND = 311,
    JOINTSTATUSESSTART = 312,
    JOINTTORQUEORFORCEEND = 313,
    JOINTTORQUEORFORCESTART = 314,
    JOINTVELOCITYEND = 315,
    JOINTVELOCITYSTART = 316,
    KEND = 317,
    KSTART = 318,
    LINEARVELOCITYEND = 319,
    LINEARVELOCITYSTART = 320,
    LOWERRIGHTEND = 321,
    LOWERRIGHTSTART = 322,
    MOMENTEND = 323,
    MOMENTSTART = 324,
    NAMEEND = 325,
    NAMESTART = 326,
    ORIENTATIONSTANDARDDEVIATIONEND = 327,
    ORIENTATIONSTANDARDDEVIATIONSTART = 328,
    POINTEND = 329,
    POINTSTART = 330,
    POSESTATUSEND = 331,
    POSESTATUSSTART = 332,
    POSEEND = 333,
    POSESTART = 334,
    POSITIONSTANDARDDEVIATIONEND = 335,
    POSITIONSTANDARDDEVIATIONSTART = 336,
    REFOBJECTNAMEEND = 337,
    REFOBJECTNAMESTART = 338,
    SEPARATIONEND = 339,
    SEPARATIONSTART = 340,
    STATUSIDEND = 341,
    STATUSIDSTART = 342,
    TIMESTAMPEND = 343,
    TIMESTAMPSTART = 344,
    TWISTEND = 345,
    TWISTSTART = 346,
    UPPERLEFTEND = 347,
    UPPERLEFTSTART = 348,
    WRENCHEND = 349,
    WRENCHSTART = 350,
    XAXISEND = 351,
    XAXISSTART = 352,
    XEND = 353,
    XSTART = 354,
    YEND = 355,
    YSTART = 356,
    ZAXISEND = 357,
    ZAXISSTART = 358,
    ZEND = 359,
    ZSTART = 360,
    CRCLSTATUSTYPEDECL = 361,
    COMMANDSTATUSTYPEDECL = 362,
    GRIPPERSTATUSTYPEDECL = 363,
    JOINTSTATUSTYPEDECL = 364,
    JOINTSTATUSESTYPEDECL = 365,
    PARALLELGRIPPERSTATUSTYPEDECL = 366,
    PHYSICALLOCATIONTYPEDECL = 367,
    POINTTYPEDECL = 368,
    POSELOCATIONINTYPEDECL = 369,
    POSELOCATIONONTYPEDECL = 370,
    POSELOCATIONTYPEDECL = 371,
    POSEONLYLOCATIONTYPEDECL = 372,
    POSESTATUSTYPEDECL = 373,
    REGIONOFINTERESTTYPEDECL = 374,
    RELATIVELOCATIONINTYPEDECL = 375,
    RELATIVELOCATIONONTYPEDECL = 376,
    RELATIVELOCATIONTYPEDECL = 377,
    THREEFINGERGRIPPERSTATUSTYPEDECL = 378,
    TWISTTYPEDECL = 379,
    VACUUMGRIPPERSTATUSTYPEDECL = 380,
    VECTORTYPEDECL = 381,
    WRENCHTYPEDECL = 382
  };
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE YYSTYPE;
union YYSTYPE
{


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


};
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;

int yyparse (void);

#endif /* !YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_CRCL_SRC_CRCLSTATUSYACC_HH_INCLUDED  */
