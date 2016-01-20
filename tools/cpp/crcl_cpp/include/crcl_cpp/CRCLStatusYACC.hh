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

#ifndef YY_YY_USR_LOCAL_PROCTOR_INDIGO_WS_SRC_CRCL_TOOLS_CPP_CRCL_CPP_SRC_CRCLSTATUSYACC_HH_INCLUDED
# define YY_YY_USR_LOCAL_PROCTOR_INDIGO_WS_SRC_CRCL_TOOLS_CPP_CRCL_CPP_SRC_CRCLSTATUSYACC_HH_INCLUDED
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
    FINGER1FORCEEND = 279,
    FINGER1FORCESTART = 280,
    FINGER1POSITIONEND = 281,
    FINGER1POSITIONSTART = 282,
    FINGER2FORCEEND = 283,
    FINGER2FORCESTART = 284,
    FINGER2POSITIONEND = 285,
    FINGER2POSITIONSTART = 286,
    FINGER3FORCEEND = 287,
    FINGER3FORCESTART = 288,
    FINGER3POSITIONEND = 289,
    FINGER3POSITIONSTART = 290,
    FORCEEND = 291,
    FORCESTART = 292,
    GRIPPERNAMEEND = 293,
    GRIPPERNAMESTART = 294,
    GRIPPERSTATUSEND = 295,
    GRIPPERSTATUSSTART = 296,
    IEND = 297,
    ISTART = 298,
    ISPOWEREDEND = 299,
    ISPOWEREDSTART = 300,
    JEND = 301,
    JSTART = 302,
    JOINTNUMBEREND = 303,
    JOINTNUMBERSTART = 304,
    JOINTPOSITIONEND = 305,
    JOINTPOSITIONSTART = 306,
    JOINTSTATUSEND = 307,
    JOINTSTATUSSTART = 308,
    JOINTSTATUSESEND = 309,
    JOINTSTATUSESSTART = 310,
    JOINTTORQUEORFORCEEND = 311,
    JOINTTORQUEORFORCESTART = 312,
    JOINTVELOCITYEND = 313,
    JOINTVELOCITYSTART = 314,
    KEND = 315,
    KSTART = 316,
    LINEARVELOCITYEND = 317,
    LINEARVELOCITYSTART = 318,
    MOMENTEND = 319,
    MOMENTSTART = 320,
    NAMEEND = 321,
    NAMESTART = 322,
    POINTEND = 323,
    POINTSTART = 324,
    POSESTATUSEND = 325,
    POSESTATUSSTART = 326,
    POSEEND = 327,
    POSESTART = 328,
    SEPARATIONEND = 329,
    SEPARATIONSTART = 330,
    STATUSIDEND = 331,
    STATUSIDSTART = 332,
    TWISTEND = 333,
    TWISTSTART = 334,
    WRENCHEND = 335,
    WRENCHSTART = 336,
    XAXISEND = 337,
    XAXISSTART = 338,
    XEND = 339,
    XSTART = 340,
    YEND = 341,
    YSTART = 342,
    ZAXISEND = 343,
    ZAXISSTART = 344,
    ZEND = 345,
    ZSTART = 346,
    CRCLSTATUSTYPEDECL = 347,
    COMMANDSTATUSTYPEDECL = 348,
    GRIPPERSTATUSTYPEDECL = 349,
    JOINTSTATUSTYPEDECL = 350,
    JOINTSTATUSESTYPEDECL = 351,
    PARALLELGRIPPERSTATUSTYPEDECL = 352,
    POINTTYPEDECL = 353,
    POSESTATUSTYPEDECL = 354,
    POSETYPEDECL = 355,
    THREEFINGERGRIPPERSTATUSTYPEDECL = 356,
    TWISTTYPEDECL = 357,
    VACUUMGRIPPERSTATUSTYPEDECL = 358,
    VECTORTYPEDECL = 359,
    WRENCHTYPEDECL = 360
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
  XmlDecimal *                        XmlDecimalVal;
  XmlID *                             XmlIDVal;
  XmlNMTOKEN *                        XmlNMTOKENVal;
  XmlNonNegativeInteger *             XmlNonNegativeIntegerVal;
  XmlPositiveInteger *                XmlPositiveIntegerVal;

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
  PoseStatusType *                    PoseStatusTypeVal;
  PoseType *                          PoseTypeVal;
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

#endif /* !YY_YY_USR_LOCAL_PROCTOR_INDIGO_WS_SRC_CRCL_TOOLS_CPP_CRCL_CPP_SRC_CRCLSTATUSYACC_HH_INCLUDED  */
