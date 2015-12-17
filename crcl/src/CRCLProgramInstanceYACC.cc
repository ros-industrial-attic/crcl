/* A Bison parser, made by GNU Bison 3.0.2.  */

/* Bison implementation for Yacc-like parsers in C

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

/* C LALR(1) parser skeleton written by Richard Stallman, by
   simplifying the original so-called "semantic" parser.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Identify Bison output.  */
#define YYBISON 1

/* Bison version.  */
#define YYBISON_VERSION "3.0.2"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Push parsers.  */
#define YYPUSH 0

/* Pull parsers.  */
#define YYPULL 1




/* Copy the first part of user declarations.  */



#include <stdio.h>             // for stderr
#include <string.h>            // for strcat
#include <stdlib.h>            // for malloc, free
#include "crcl/CRCLProgramInstanceClasses.hh"

#define YYERROR_VERBOSE
#define YYDEBUG 1

CRCLProgramFile * CRCLProgramTree; // the parse tree

extern int yylex();
int yyReadData = 0;
int yyReadDataList = 0;

int yyerror(const char * s);




# ifndef YY_NULLPTR
#  if defined __cplusplus && 201103L <= __cplusplus
#   define YY_NULLPTR nullptr
#  else
#   define YY_NULLPTR 0
#  endif
# endif

/* Enabling verbose error messages.  */
#ifdef YYERROR_VERBOSE
# undef YYERROR_VERBOSE
# define YYERROR_VERBOSE 1
#else
# define YYERROR_VERBOSE 0
#endif

/* In a future release of Bison, this section will be replaced
   by #include "CRCLProgramInstanceYACC.hh".  */
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

/* Copy the second part of user declarations.  */



#ifdef short
# undef short
#endif

#ifdef YYTYPE_UINT8
typedef YYTYPE_UINT8 yytype_uint8;
#else
typedef unsigned char yytype_uint8;
#endif

#ifdef YYTYPE_INT8
typedef YYTYPE_INT8 yytype_int8;
#else
typedef signed char yytype_int8;
#endif

#ifdef YYTYPE_UINT16
typedef YYTYPE_UINT16 yytype_uint16;
#else
typedef unsigned short int yytype_uint16;
#endif

#ifdef YYTYPE_INT16
typedef YYTYPE_INT16 yytype_int16;
#else
typedef short int yytype_int16;
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif ! defined YYSIZE_T
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned int
# endif
#endif

#define YYSIZE_MAXIMUM ((YYSIZE_T) -1)

#ifndef YY_
# if defined YYENABLE_NLS && YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(Msgid) dgettext ("bison-runtime", Msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(Msgid) Msgid
# endif
#endif

#ifndef YY_ATTRIBUTE
# if (defined __GNUC__                                               \
      && (2 < __GNUC__ || (__GNUC__ == 2 && 96 <= __GNUC_MINOR__)))  \
     || defined __SUNPRO_C && 0x5110 <= __SUNPRO_C
#  define YY_ATTRIBUTE(Spec) __attribute__(Spec)
# else
#  define YY_ATTRIBUTE(Spec) /* empty */
# endif
#endif

#ifndef YY_ATTRIBUTE_PURE
# define YY_ATTRIBUTE_PURE   YY_ATTRIBUTE ((__pure__))
#endif

#ifndef YY_ATTRIBUTE_UNUSED
# define YY_ATTRIBUTE_UNUSED YY_ATTRIBUTE ((__unused__))
#endif

#if !defined _Noreturn \
     && (!defined __STDC_VERSION__ || __STDC_VERSION__ < 201112)
# if defined _MSC_VER && 1200 <= _MSC_VER
#  define _Noreturn __declspec (noreturn)
# else
#  define _Noreturn YY_ATTRIBUTE ((__noreturn__))
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YYUSE(E) ((void) (E))
#else
# define YYUSE(E) /* empty */
#endif

#if defined __GNUC__ && 407 <= __GNUC__ * 100 + __GNUC_MINOR__
/* Suppress an incorrect diagnostic about yylval being uninitialized.  */
# define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN \
    _Pragma ("GCC diagnostic push") \
    _Pragma ("GCC diagnostic ignored \"-Wuninitialized\"")\
    _Pragma ("GCC diagnostic ignored \"-Wmaybe-uninitialized\"")
# define YY_IGNORE_MAYBE_UNINITIALIZED_END \
    _Pragma ("GCC diagnostic pop")
#else
# define YY_INITIAL_VALUE(Value) Value
#endif
#ifndef YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_END
#endif
#ifndef YY_INITIAL_VALUE
# define YY_INITIAL_VALUE(Value) /* Nothing. */
#endif


#if ! defined yyoverflow || YYERROR_VERBOSE

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# ifdef YYSTACK_USE_ALLOCA
#  if YYSTACK_USE_ALLOCA
#   ifdef __GNUC__
#    define YYSTACK_ALLOC __builtin_alloca
#   elif defined __BUILTIN_VA_ARG_INCR
#    include <alloca.h> /* INFRINGES ON USER NAME SPACE */
#   elif defined _AIX
#    define YYSTACK_ALLOC __alloca
#   elif defined _MSC_VER
#    include <malloc.h> /* INFRINGES ON USER NAME SPACE */
#    define alloca _alloca
#   else
#    define YYSTACK_ALLOC alloca
#    if ! defined _ALLOCA_H && ! defined EXIT_SUCCESS
#     include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
      /* Use EXIT_SUCCESS as a witness for stdlib.h.  */
#     ifndef EXIT_SUCCESS
#      define EXIT_SUCCESS 0
#     endif
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's 'empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (0)
#  ifndef YYSTACK_ALLOC_MAXIMUM
    /* The OS might guarantee only one guard page at the bottom of the stack,
       and a page size can be as small as 4096 bytes.  So we cannot safely
       invoke alloca (N) if N exceeds 4096.  Use a slightly smaller number
       to allow for a few compiler-allocated temporary stack slots.  */
#   define YYSTACK_ALLOC_MAXIMUM 4032 /* reasonable circa 2006 */
#  endif
# else
#  define YYSTACK_ALLOC YYMALLOC
#  define YYSTACK_FREE YYFREE
#  ifndef YYSTACK_ALLOC_MAXIMUM
#   define YYSTACK_ALLOC_MAXIMUM YYSIZE_MAXIMUM
#  endif
#  if (defined __cplusplus && ! defined EXIT_SUCCESS \
       && ! ((defined YYMALLOC || defined malloc) \
             && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef EXIT_SUCCESS
#    define EXIT_SUCCESS 0
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined EXIT_SUCCESS
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined EXIT_SUCCESS
void free (void *); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
# endif
#endif /* ! defined yyoverflow || YYERROR_VERBOSE */


#if (! defined yyoverflow \
     && (! defined __cplusplus \
         || (defined YYSTYPE_IS_TRIVIAL && YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  yytype_int16 yyss_alloc;
  YYSTYPE yyvs_alloc;
};

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (sizeof (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (sizeof (yytype_int16) + sizeof (YYSTYPE)) \
      + YYSTACK_GAP_MAXIMUM)

# define YYCOPY_NEEDED 1

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack_alloc, Stack)                           \
    do                                                                  \
      {                                                                 \
        YYSIZE_T yynewbytes;                                            \
        YYCOPY (&yyptr->Stack_alloc, Stack, yysize);                    \
        Stack = &yyptr->Stack_alloc;                                    \
        yynewbytes = yystacksize * sizeof (*Stack) + YYSTACK_GAP_MAXIMUM; \
        yyptr += yynewbytes / sizeof (*yyptr);                          \
      }                                                                 \
    while (0)

#endif

#if defined YYCOPY_NEEDED && YYCOPY_NEEDED
/* Copy COUNT objects from SRC to DST.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(Dst, Src, Count) \
      __builtin_memcpy (Dst, Src, (Count) * sizeof (*(Src)))
#  else
#   define YYCOPY(Dst, Src, Count)              \
      do                                        \
        {                                       \
          YYSIZE_T yyi;                         \
          for (yyi = 0; yyi < (Count); yyi++)   \
            (Dst)[yyi] = (Src)[yyi];            \
        }                                       \
      while (0)
#  endif
# endif
#endif /* !YYCOPY_NEEDED */

/* YYFINAL -- State number of the termination state.  */
#define YYFINAL  5
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   459

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  214
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  189
/* YYNRULES -- Number of rules.  */
#define YYNRULES  249
/* YYNSTATES -- Number of states.  */
#define YYNSTATES  623

/* YYTRANSLATE[YYX] -- Symbol number corresponding to YYX as returned
   by yylex, with out-of-bounds checking.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   468

#define YYTRANSLATE(YYX)                                                \
  ((unsigned int) (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[TOKEN-NUM] -- Symbol number corresponding to TOKEN-NUM
   as returned by yylex, without out-of-bounds checking.  */
static const yytype_uint8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    41,    42,    43,    44,
      45,    46,    47,    48,    49,    50,    51,    52,    53,    54,
      55,    56,    57,    58,    59,    60,    61,    62,    63,    64,
      65,    66,    67,    68,    69,    70,    71,    72,    73,    74,
      75,    76,    77,    78,    79,    80,    81,    82,    83,    84,
      85,    86,    87,    88,    89,    90,    91,    92,    93,    94,
      95,    96,    97,    98,    99,   100,   101,   102,   103,   104,
     105,   106,   107,   108,   109,   110,   111,   112,   113,   114,
     115,   116,   117,   118,   119,   120,   121,   122,   123,   124,
     125,   126,   127,   128,   129,   130,   131,   132,   133,   134,
     135,   136,   137,   138,   139,   140,   141,   142,   143,   144,
     145,   146,   147,   148,   149,   150,   151,   152,   153,   154,
     155,   156,   157,   158,   159,   160,   161,   162,   163,   164,
     165,   166,   167,   168,   169,   170,   171,   172,   173,   174,
     175,   176,   177,   178,   179,   180,   181,   182,   183,   184,
     185,   186,   187,   188,   189,   190,   191,   192,   193,   194,
     195,   196,   197,   198,   199,   200,   201,   202,   203,   204,
     205,   206,   207,   208,   209,   210,   211,   212,   213
};

#if YYDEBUG
  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,   475,   475,   485,   490,   497,   506,   515,   524,   533,
     542,   551,   560,   569,   575,   588,   594,   605,   606,   606,
     612,   612,   619,   620,   625,   632,   633,   633,   639,   639,
     645,   652,   658,   658,   670,   670,   676,   681,   686,   696,
     696,   705,   705,   710,   715,   720,   720,   726,   727,   727,
     733,   735,   740,   745,   745,   751,   751,   758,   759,   759,
     765,   765,   775,   778,   786,   787,   795,   796,   803,   807,
     813,   817,   823,   836,   836,   841,   843,   845,   847,   849,
     851,   853,   855,   857,   859,   861,   863,   865,   867,   869,
     871,   873,   875,   877,   879,   881,   883,   885,   887,   889,
     891,   896,   906,   906,   913,   914,   914,   919,   919,   926,
     927,   927,   937,   937,   943,   949,   954,   954,   960,   966,
     971,   979,   981,   986,   994,   995,   995,  1005,  1005,  1018,
    1019,  1019,  1025,  1025,  1031,  1031,  1037,  1037,  1043,  1043,
    1048,  1050,  1055,  1061,  1062,  1067,  1069,  1074,  1080,  1081,
    1086,  1086,  1095,  1095,  1101,  1102,  1102,  1108,  1109,  1114,
    1114,  1125,  1126,  1126,  1132,  1138,  1139,  1144,  1146,  1151,
    1157,  1158,  1163,  1165,  1170,  1176,  1177,  1182,  1182,  1187,
    1187,  1196,  1196,  1205,  1205,  1214,  1214,  1228,  1234,  1236,
    1242,  1243,  1243,  1249,  1255,  1256,  1256,  1262,  1262,  1268,
    1269,  1269,  1275,  1275,  1281,  1282,  1282,  1288,  1294,  1295,
    1295,  1301,  1301,  1306,  1315,  1323,  1332,  1340,  1348,  1356,
    1364,  1372,  1383,  1393,  1402,  1410,  1424,  1432,  1440,  1448,
    1456,  1464,  1472,  1481,  1489,  1497,  1505,  1513,  1521,  1529,
    1538,  1546,  1554,  1562,  1570,  1578,  1587,  1595,  1603,  1611
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || 0
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "BAD", "DATASTRING", "ENCODING",
  "ENDITEM", "ENDVERSION", "SCHEMALOCATION", "STARTVERSION",
  "TERMINALSTRING", "XMLNSPREFIX", "XMLNSTARGET", "XMLVERSION",
  "ACTUATEJOINTEND", "ACTUATEJOINTSTART", "ANGULARVELOCITYEND",
  "ANGULARVELOCITYSTART", "AXIALDISTANCEFREEEND", "AXIALDISTANCEFREESTART",
  "AXIALDISTANCESCREWEND", "AXIALDISTANCESCREWSTART", "AXISPOINTEND",
  "AXISPOINTSTART", "CRCLPROGRAMEND", "CRCLPROGRAMSTART", "CHANGERATEEND",
  "CHANGERATESTART", "COMMANDIDEND", "COMMANDIDSTART",
  "CONFIGUREJOINTREPORTEND", "CONFIGUREJOINTREPORTSTART", "COORDINATEDEND",
  "COORDINATEDSTART", "DESCRIPTIONEND", "DESCRIPTIONSTART", "DWELLTIMEEND",
  "DWELLTIMESTART", "ENDCANONEND", "ENDCANONSTART", "ENDPOSITIONEND",
  "ENDPOSITIONSTART", "FORCEEND", "FORCESTART", "FRACTIONEND",
  "FRACTIONSTART", "IEND", "ISTART", "INITCANONEND", "INITCANONSTART",
  "JEND", "JSTART", "JOINTACCELEND", "JOINTACCELSTART", "JOINTDETAILSEND",
  "JOINTDETAILSSTART", "JOINTNUMBEREND", "JOINTNUMBERSTART",
  "JOINTPOSITIONEND", "JOINTPOSITIONSTART", "JOINTSPEEDEND",
  "JOINTSPEEDSTART", "KEND", "KSTART", "LINEARVELOCITYEND",
  "LINEARVELOCITYSTART", "LOWERRIGHTEND", "LOWERRIGHTSTART", "MESSAGEEND",
  "MESSAGESTART", "MIDDLECOMMANDEND", "MIDDLECOMMANDSTART", "MOMENTEND",
  "MOMENTSTART", "MOVESTRAIGHTEND", "MOVESTRAIGHTSTART", "NAMEEND",
  "NAMESTART", "NUMPOSITIONSEND", "NUMPOSITIONSSTART",
  "ORIENTATIONSTANDARDDEVIATIONEND", "ORIENTATIONSTANDARDDEVIATIONSTART",
  "PARAMETERNAMEEND", "PARAMETERNAMESTART", "PARAMETERSETTINGEND",
  "PARAMETERSETTINGSTART", "PARAMETERVALUEEND", "PARAMETERVALUESTART",
  "POINTEND", "POINTSTART", "POSITIONSTANDARDDEVIATIONEND",
  "POSITIONSTANDARDDEVIATIONSTART", "PROGRAMTEXTEND", "PROGRAMTEXTSTART",
  "REFOBJECTNAMEEND", "REFOBJECTNAMESTART", "REPORTPOSITIONEND",
  "REPORTPOSITIONSTART", "REPORTTORQUEORFORCEEND",
  "REPORTTORQUEORFORCESTART", "REPORTVELOCITYEND", "REPORTVELOCITYSTART",
  "RESETALLEND", "RESETALLSTART", "ROTACCELEND", "ROTACCELSTART",
  "ROTSPEEDEND", "ROTSPEEDSTART", "SETTINGEND", "SETTINGSTART",
  "STARTPOSITIONEND", "STARTPOSITIONSTART", "STOPCONDITIONEND",
  "STOPCONDITIONSTART", "TIMESTAMPEND", "TIMESTAMPSTART", "TOLERANCEEND",
  "TOLERANCESTART", "TRANSACCELEND", "TRANSACCELSTART", "TRANSSPEEDEND",
  "TRANSSPEEDSTART", "TURNEND", "TURNSTART", "UNITNAMEEND",
  "UNITNAMESTART", "UPPERLEFTEND", "UPPERLEFTSTART", "WAYPOINTEND",
  "WAYPOINTSTART", "XAXISTOLERANCEEND", "XAXISTOLERANCESTART", "XAXISEND",
  "XAXISSTART", "XPOINTTOLERANCEEND", "XPOINTTOLERANCESTART", "XEND",
  "XSTART", "YPOINTTOLERANCEEND", "YPOINTTOLERANCESTART", "YEND", "YSTART",
  "ZAXISTOLERANCEEND", "ZAXISTOLERANCESTART", "ZAXISEND", "ZAXISSTART",
  "ZPOINTTOLERANCEEND", "ZPOINTTOLERANCESTART", "ZEND", "ZSTART",
  "ACTUATEJOINTTYPEDECL", "ACTUATEJOINTSTYPEDECL", "CRCLCOMMANDTYPEDECL",
  "CRCLPROGRAMTYPEDECL", "CLOSETOOLCHANGERTYPEDECL",
  "CONFIGUREJOINTREPORTTYPEDECL", "CONFIGUREJOINTREPORTSTYPEDECL",
  "DWELLTYPEDECL", "ENDCANONTYPEDECL", "GETSTATUSTYPEDECL",
  "INITCANONTYPEDECL", "JOINTDETAILSTYPEDECL", "JOINTFORCETORQUETYPEDECL",
  "JOINTSPEEDACCELTYPEDECL", "MESSAGETYPEDECL", "MIDDLECOMMANDTYPEDECL",
  "MOVESCREWTYPEDECL", "MOVETHROUGHTOTYPEDECL", "MOVETOTYPEDECL",
  "OPENTOOLCHANGERTYPEDECL", "PARAMETERSETTINGTYPEDECL",
  "PHYSICALLOCATIONTYPEDECL", "POINTTYPEDECL", "POSEANDSETTYPEDECL",
  "POSELOCATIONINTYPEDECL", "POSELOCATIONONTYPEDECL",
  "POSELOCATIONTYPEDECL", "POSEONLYLOCATIONTYPEDECL",
  "POSETOLERANCETYPEDECL", "REGIONOFINTERESTTYPEDECL",
  "RELATIVELOCATIONINTYPEDECL", "RELATIVELOCATIONONTYPEDECL",
  "RELATIVELOCATIONTYPEDECL", "ROTACCELABSOLUTETYPEDECL",
  "ROTACCELRELATIVETYPEDECL", "ROTACCELTYPEDECL",
  "ROTSPEEDABSOLUTETYPEDECL", "ROTSPEEDRELATIVETYPEDECL",
  "ROTSPEEDTYPEDECL", "RUNPROGRAMTYPEDECL", "SETANGLEUNITSTYPEDECL",
  "SETENDEFFECTORPARAMETERSTYPEDECL", "SETENDEFFECTORTYPEDECL",
  "SETENDPOSETOLERANCETYPEDECL", "SETFORCEUNITSTYPEDECL",
  "SETINTERMEDIATEPOSETOLERANCETYPEDECL", "SETLENGTHUNITSTYPEDECL",
  "SETMOTIONCOORDINATIONTYPEDECL", "SETROBOTPARAMETERSTYPEDECL",
  "SETROTACCELTYPEDECL", "SETROTSPEEDTYPEDECL", "SETTORQUEUNITSTYPEDECL",
  "SETTRANSACCELTYPEDECL", "SETTRANSSPEEDTYPEDECL", "STOPMOTIONTYPEDECL",
  "TRANSACCELABSOLUTETYPEDECL", "TRANSACCELRELATIVETYPEDECL",
  "TRANSACCELTYPEDECL", "TRANSSPEEDABSOLUTETYPEDECL",
  "TRANSSPEEDRELATIVETYPEDECL", "TRANSSPEEDTYPEDECL", "TWISTTYPEDECL",
  "VECTORTYPEDECL", "WRENCHTYPEDECL", "$accept", "y_CRCLProgramFile",
  "y_XmlHeaderForCRCLProgram", "y_SchemaLocation", "y_XmlBoolean",
  "y_XmlDateTime", "y_XmlDecimal", "y_XmlID", "y_XmlIDREF",
  "y_XmlPositiveInteger", "y_XmlString", "y_XmlToken", "y_XmlVersion",
  "y_ActuateJointType", "y_ActuateJoint_ActuateJointType_1_u",
  "y_AxialDistanceFree_XmlDecimal_0", "$@1",
  "y_AxialDistanceScrew_XmlDecimal", "$@2", "y_AxisPoint_PointType_0",
  "y_CRCLProgramType", "y_ChangeRate_XmlDecimal_0", "$@3",
  "y_CommandID_XmlPositiveInteger", "$@4", "y_ConfigureJointReportType",
  "y_ConfigureJointReport_Configure1001", "y_Coordinated_XmlBoolean",
  "$@5", "y_DwellTime_XmlDecimal", "$@7", "y_EndCanonType",
  "y_EndCanon_EndCanonType", "y_EndPosition_PoseOnlyLocationType",
  "y_Fraction_FractionType", "$@8", "y_I_XmlDecimal", "$@9",
  "y_InitCanonType", "y_InitCanon_InitCanonType", "y_J_XmlDecimal", "$@10",
  "y_JointAccel_XmlDecimal_0", "$@11", "y_JointDetailsTypeAny",
  "y_JointDetails_JointDetailsType", "y_JointNumber_XmlPositiveInteger",
  "$@12", "y_JointPosition_XmlDecimal", "$@13",
  "y_JointSpeed_XmlDecimal_0", "$@14", "y_K_XmlDecimal", "$@15",
  "y_ListActuateJoint_ActuateJointType_1_u",
  "y_ListConfigureJointReport_Configure1001",
  "y_ListMiddleCommand_MiddleCommandType_0_u",
  "y_ListParameterSetting_ParameterSett1003",
  "y_ListWaypoint_PoseOnlyLocationType_2_u",
  "y_ListWaypoint_PoseOnlyLocationType_2_u_Check", "y_Message_XmlString",
  "$@16", "y_MiddleCommandTypeAny",
  "y_MiddleCommand_MiddleCommandType_0_u", "y_MoveStraight_XmlBoolean",
  "$@17", "y_Name_XmlID_0", "$@18", "y_NumPositions_XmlPositiveInteger",
  "$@19", "y_OrientationStandardDeviation_P1002", "$@20",
  "y_ParameterName_XmlToken", "$@21", "y_ParameterSettingType",
  "y_ParameterSetting_ParameterSett1003", "y_ParameterValue_XmlToken",
  "$@22", "y_PointType", "y_Point_PointType", "y_PoseOnlyLocationType",
  "y_PoseOnlyLocationTypeAny", "y_PoseToleranceType",
  "y_PositionStandardDeviation_Posi1004", "$@23",
  "y_ProgramText_XmlString", "$@24", "y_RefObjectName_XmlIDREF_0", "$@26",
  "y_ReportPosition_XmlBoolean", "$@27",
  "y_ReportTorqueOrForce_XmlBoolean", "$@28",
  "y_ReportVelocity_XmlBoolean", "$@29", "y_ResetAll_XmlBoolean", "$@30",
  "y_RotAccelTypeAny", "y_RotAccel_RotAccelType",
  "y_RotAccel_RotAccelType_0", "y_RotSpeedTypeAny",
  "y_RotSpeed_RotSpeedType", "y_RotSpeed_RotSpeedType_0",
  "y_Setting_FractionType", "$@31", "y_Setting_XmlDecimal", "$@32",
  "y_Setting_XmlDecimal_0", "$@33", "y_StartPosition_PoseOnlyLocation1005",
  "y_StopCondition_StopConditionEnumType", "$@34",
  "y_Timestamp_XmlDateTime_0", "$@35", "y_Tolerance_PoseToleranceType",
  "y_Tolerance_PoseToleranceType_0", "y_TransAccelTypeAny",
  "y_TransAccel_TransAccelType", "y_TransAccel_TransAccelType_0",
  "y_TransSpeedTypeAny", "y_TransSpeed_TransSpeedType",
  "y_TransSpeed_TransSpeedType_0", "y_Turn_XmlDecimal", "$@36",
  "y_UnitName_AngleUnitEnumType", "$@37", "y_UnitName_ForceUnitEnumType",
  "$@38", "y_UnitName_LengthUnitEnumType", "$@39",
  "y_UnitName_TorqueUnitEnumType", "$@40", "y_VectorType",
  "y_Waypoint_PoseOnlyLocationType_2_u", "y_XAxisTolerance_XmlDecimal_0",
  "$@41", "y_XAxis_VectorType", "y_XPointTolerance_XmlDecimal_0", "$@42",
  "y_X_XmlDecimal", "$@43", "y_YPointTolerance_XmlDecimal_0", "$@44",
  "y_Y_XmlDecimal", "$@45", "y_ZAxisTolerance_XmlDecimal_0", "$@46",
  "y_ZAxis_VectorType", "y_ZPointTolerance_XmlDecimal_0", "$@47",
  "y_Z_XmlDecimal", "$@48", "y_x_ActuateJointsType",
  "y_x_CloseToolChangerType", "y_x_ConfigureJointReportsType",
  "y_x_DwellType", "y_x_GetStatusType", "y_x_JointForceTorqueType",
  "y_x_JointSpeedAccelType", "y_x_MessageType", "y_x_MoveScrewType",
  "y_x_MoveThroughToType", "y_x_MoveToType", "y_x_OpenToolChangerType",
  "y_x_PoseAndSetType", "y_x_RotAccelAbsoluteType",
  "y_x_RotAccelRelativeType", "y_x_RotSpeedAbsoluteType",
  "y_x_RotSpeedRelativeType", "y_x_RunProgramType",
  "y_x_SetAngleUnitsType", "y_x_SetEndEffectorParametersType",
  "y_x_SetEndEffectorType", "y_x_SetEndPoseToleranceType",
  "y_x_SetForceUnitsType", "y_x_SetIntermediatePoseToleranceType",
  "y_x_SetLengthUnitsType", "y_x_SetMotionCoordinationType",
  "y_x_SetRobotParametersType", "y_x_SetRotAccelType",
  "y_x_SetRotSpeedType", "y_x_SetTorqueUnitsType", "y_x_SetTransAccelType",
  "y_x_SetTransSpeedType", "y_x_StopMotionType",
  "y_x_TransAccelAbsoluteType", "y_x_TransAccelRelativeType",
  "y_x_TransSpeedAbsoluteType", "y_x_TransSpeedRelativeType", YY_NULLPTR
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[NUM] -- (External) token number corresponding to the
   (internal) symbol number NUM (which must be that of a token).  */
static const yytype_uint16 yytoknum[] =
{
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,   290,   291,   292,   293,   294,
     295,   296,   297,   298,   299,   300,   301,   302,   303,   304,
     305,   306,   307,   308,   309,   310,   311,   312,   313,   314,
     315,   316,   317,   318,   319,   320,   321,   322,   323,   324,
     325,   326,   327,   328,   329,   330,   331,   332,   333,   334,
     335,   336,   337,   338,   339,   340,   341,   342,   343,   344,
     345,   346,   347,   348,   349,   350,   351,   352,   353,   354,
     355,   356,   357,   358,   359,   360,   361,   362,   363,   364,
     365,   366,   367,   368,   369,   370,   371,   372,   373,   374,
     375,   376,   377,   378,   379,   380,   381,   382,   383,   384,
     385,   386,   387,   388,   389,   390,   391,   392,   393,   394,
     395,   396,   397,   398,   399,   400,   401,   402,   403,   404,
     405,   406,   407,   408,   409,   410,   411,   412,   413,   414,
     415,   416,   417,   418,   419,   420,   421,   422,   423,   424,
     425,   426,   427,   428,   429,   430,   431,   432,   433,   434,
     435,   436,   437,   438,   439,   440,   441,   442,   443,   444,
     445,   446,   447,   448,   449,   450,   451,   452,   453,   454,
     455,   456,   457,   458,   459,   460,   461,   462,   463,   464,
     465,   466,   467,   468
};
# endif

#define YYPACT_NINF -387

#define yypact_value_is_default(Yystate) \
  (!!((Yystate) == (-387)))

#define YYTABLE_NINF -1

#define yytable_value_is_error(Yytable_value) \
  0

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
     STATE-NUM.  */
static const yytype_int16 yypact[] =
{
       1,     2,    25,     5,    22,  -387,    35,    15,    39,    42,
      40,  -387,    41,  -387,   -28,    28,    46,  -387,    49,    50,
    -387,  -387,  -387,    94,  -387,    97,   -28,    54,   -34,  -387,
      29,    75,  -387,   100,  -133,  -387,  -387,  -387,   101,  -387,
     -28,    70,   103,   105,   106,   107,   108,   109,   110,   111,
     112,   113,   114,   115,   116,   117,   118,   120,   121,   122,
     123,   124,   126,   127,   128,   129,   130,   132,    69,  -387,
    -387,  -387,  -387,  -387,  -387,  -387,  -387,  -387,  -387,  -387,
    -387,  -387,  -387,  -387,  -387,  -387,  -387,  -387,  -387,  -387,
    -387,  -387,  -387,  -387,  -387,  -387,    75,  -387,   -28,   -28,
     -28,   -28,   -28,   -28,   -28,   -28,   -28,   -28,   -28,   -28,
     -28,   -28,   -28,   -28,   -28,   -28,   -28,   -28,   -28,   -28,
     -28,   -28,   -28,   -28,  -387,   136,  -387,    75,    75,    75,
      75,    75,    75,    75,    75,    75,    75,    75,    75,    75,
      75,    75,    75,    75,    75,    75,    75,    75,    75,    75,
      75,    75,    75,  -387,   119,   131,  -387,    45,   125,  -387,
      73,    32,    74,    74,  -387,    51,    20,    65,    43,    34,
      30,    34,    31,   133,    65,    48,    47,    33,    38,    44,
      55,  -387,   153,  -387,   131,   154,  -387,   157,  -387,   158,
    -387,    -3,   144,   165,    52,   134,   166,  -387,   167,  -387,
     168,    65,  -387,   170,  -387,   171,  -387,   172,  -387,  -387,
     173,  -387,   174,  -387,    65,  -155,  -387,  -148,  -387,   176,
    -387,  -165,  -387,  -166,  -387,   177,  -387,   -28,   175,  -387,
    -387,   155,  -387,  -387,   -28,   179,  -387,    77,  -387,   182,
     180,  -387,    -3,    52,   135,  -387,    -3,  -387,  -387,  -387,
     -28,   137,  -387,  -387,   -28,    68,  -387,  -387,  -387,   184,
     186,    89,  -387,  -387,   188,   189,    91,  -387,  -387,  -387,
     192,   194,    84,  -387,  -387,   197,   198,    85,  -387,  -387,
    -387,   149,  -387,   203,   204,  -387,   205,   207,   138,   -28,
    -387,   -28,   190,   209,   195,   203,    92,    95,  -387,   211,
    -387,   185,   207,   215,   139,  -387,   222,    96,  -387,   223,
     225,   203,   -28,   -28,  -387,   -28,   -28,  -387,   226,   -28,
     -28,  -387,   -28,   -28,  -387,   228,   229,   183,  -387,   142,
     -28,   206,  -387,   202,  -387,   181,   235,   140,   138,   141,
    -387,  -387,   239,   143,   160,  -387,  -387,  -387,  -387,   156,
     146,   240,   163,   145,   241,   152,   147,   148,   219,   150,
     213,   150,   213,   151,   150,   213,   150,   213,   161,  -387,
     250,   212,  -387,   149,  -387,  -387,  -387,  -387,   254,   187,
     140,   255,   159,   205,  -387,   258,  -387,  -387,   136,  -387,
    -387,  -387,   262,  -387,  -387,  -387,   271,   191,  -387,  -387,
    -387,   273,  -387,   274,  -387,  -387,  -387,  -387,  -387,  -387,
    -387,  -387,  -387,   136,  -387,  -118,  -387,   201,   277,  -387,
     182,   162,   187,  -387,   276,   193,   265,   205,  -387,   210,
     280,  -387,   205,  -387,   279,   200,  -387,  -387,   236,   205,
     293,   295,   248,  -387,  -387,   297,   233,  -387,   242,   329,
     246,   331,   196,   162,   205,  -387,   333,  -387,  -387,   315,
     205,  -387,  -387,   261,   280,   214,   205,  -387,   334,   208,
     205,   340,  -387,   287,   -28,   -28,  -387,  -387,   341,   245,
    -387,  -387,   238,  -387,   -28,   217,   331,   259,   196,   218,
     205,  -387,  -387,   231,  -387,   269,  -387,   220,   205,  -387,
     350,  -387,   249,   316,  -387,   252,   298,   203,  -387,   356,
    -387,  -387,   317,  -387,   221,   357,   285,   259,  -387,   227,
     205,  -387,  -387,  -387,   224,   205,  -387,  -387,  -387,   362,
     342,   365,   319,   278,   203,  -387,   367,   324,  -387,  -387,
     370,  -387,   285,  -387,   230,  -387,   247,   205,  -387,   373,
    -387,  -387,   374,  -387,  -387,   283,   203,  -387,   376,   320,
     380,  -387,   133,  -387,  -387,   243,   205,  -387,   205,  -387,
    -387,   286,   205,  -387,   381,  -387,   299,   384,   270,  -387,
     282,   205,   332,   205,  -387,   347,   205,  -387,  -387,   314,
    -166,   288,  -387,   371,  -387,   344,  -387,   348,   205,  -387,
     281,  -148,   284,  -387,  -387,  -387,   337,  -387,   294,  -165,
     300,  -387,  -387,   289,  -155,   291,  -387,   302,   171,  -387,
    -387,   296,  -387
};

  /* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
     Performed when YYTABLE does not specify something else to do.  Zero
     means the default is an error.  */
static const yytype_uint8 yydefact[] =
{
       0,     0,     0,     0,     0,     1,     0,     0,     0,     0,
       0,    13,     0,     3,   104,     0,     0,     4,     0,     0,
       2,    14,   105,     0,    66,     0,   104,     0,     0,     8,
       0,     0,    44,     0,     0,    24,    67,   106,     0,    43,
     104,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,    75,
      76,    77,    78,    79,    80,    81,    82,    83,    84,    85,
      86,    87,    88,    89,    90,    91,    92,    93,    94,    95,
      96,    97,    98,    99,   100,    28,     0,    37,   104,   104,
     104,   104,   104,   104,   104,   104,   104,   104,   104,   104,
     104,   104,   104,   104,   104,   104,   104,   104,   104,   104,
     104,   104,   104,   104,   101,     0,    36,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,    10,     0,     0,   214,     0,     0,   217,
       0,   157,     0,     0,   224,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,    29,     0,    62,   213,     0,    64,     0,   216,     0,
     220,     0,    22,     0,     0,     0,     0,   230,     0,   231,
       0,   232,    69,     0,   233,     0,   234,     0,   235,   236,
       0,   237,     0,   238,   239,     0,   240,     0,   241,     0,
     242,     0,   243,     0,   244,     0,   245,   104,     0,    63,
     138,   215,    34,    73,   104,     0,   121,     0,   122,     0,
      17,   102,     0,    72,     0,    71,     0,   223,   127,   179,
     104,     0,    68,   150,   104,     0,   181,   183,    32,     0,
       0,     0,   140,   141,     0,     0,     0,   145,   146,   185,
       0,     0,     0,   167,   168,     0,     0,     0,   172,   173,
     159,     0,    16,     0,     0,    65,     0,     0,   129,   104,
     158,   104,     0,     0,     0,     0,     0,     0,    70,     0,
     222,     0,     0,     0,     0,   115,     0,   194,   164,     0,
       0,     0,   104,   104,   142,   104,   104,   147,     0,   104,
     104,   169,   104,   104,   174,     0,     0,     0,     5,     0,
     104,     0,     7,     0,    11,     0,     0,   161,   129,     0,
      23,    18,     0,     0,     0,   188,   189,   107,    38,     0,
       0,     0,     0,     0,     0,   199,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,    53,
       0,     0,   139,     0,    31,    35,    74,   130,     0,     0,
     161,     0,     0,     0,    20,     0,   221,   103,     0,   128,
     180,   112,     0,   114,   151,   195,     0,   208,   182,   184,
      33,     0,   226,     0,   227,   228,   229,   186,   246,   247,
     248,   249,   160,     0,    55,     0,    15,     0,     0,   162,
       0,     0,     0,   197,     0,     0,     0,     0,   177,     0,
       0,   116,     0,   200,     0,   190,   152,    39,     0,     0,
       0,     0,     0,    50,    51,     0,     0,     9,     0,     0,
       0,     0,     0,     0,     0,   202,     0,   118,    19,     0,
       0,   108,    12,     0,     0,     0,     0,   209,     0,   204,
       0,     0,    54,     0,   104,   104,    52,   132,     0,     0,
     131,     6,     0,   119,   104,     0,     0,   124,     0,     0,
       0,   211,    21,     0,   113,     0,   196,     0,     0,   191,
       0,   123,     0,     0,    56,   154,    57,     0,   134,     0,
      30,   163,     0,   193,     0,     0,   109,   124,   198,     0,
       0,   178,   117,   201,     0,     0,   205,   153,    40,     0,
      25,     0,    47,     0,     0,   136,     0,     0,   207,   125,
       0,   120,   109,   203,     0,   210,     0,     0,   155,     0,
     218,    58,     0,   219,   133,     0,     0,    41,     0,     0,
       0,   110,     0,   212,   192,     0,     0,    26,     0,    48,
     135,     0,     0,    45,     0,   187,     0,     0,   175,   206,
       0,     0,     0,     0,   137,     0,     0,    60,   126,     0,
       0,   148,   156,     0,    59,     0,    42,     0,     0,   111,
       0,     0,   170,    27,    49,    46,     0,   176,     0,     0,
     143,    61,   149,     0,     0,   165,   171,     0,     0,   225,
     144,     0,   166
};

  /* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -387,  -387,  -387,  -387,  -294,  -387,  -329,  -387,  -387,  -386,
     102,   -62,  -387,  -387,   232,  -387,  -387,  -387,  -387,  -387,
    -387,  -387,  -387,   178,  -387,  -387,  -387,  -153,  -387,  -387,
    -387,  -387,  -387,  -387,  -358,  -387,  -387,  -387,  -387,  -387,
    -387,  -387,  -387,  -387,  -387,  -387,    37,  -387,  -387,  -387,
    -387,  -387,  -387,  -387,  -387,  -387,  -387,   237,  -387,  -387,
    -387,  -387,  -387,  -387,   251,  -387,   -26,  -387,  -387,  -387,
    -129,  -387,  -387,  -387,  -387,  -195,  -387,  -387,    -5,    -4,
     199,  -230,  -201,   -98,  -387,  -387,  -387,    82,  -387,  -387,
    -387,  -387,  -387,  -387,  -387,  -387,  -387,  -193,  -387,  -387,
    -179,  -387,  -387,  -387,  -387,  -353,  -387,  -387,  -387,  -387,
    -387,  -387,    53,  -387,   253,  -387,  -186,  -387,  -387,  -164,
    -387,  -387,  -387,  -387,  -387,  -387,  -387,  -387,  -387,  -387,
    -387,  -387,   -61,   216,  -387,  -387,   -25,  -387,  -387,  -387,
    -387,  -387,  -387,  -387,  -387,  -387,  -387,   -59,  -387,  -387,
    -387,  -387,  -387,  -387,  -387,  -387,  -387,  -387,  -387,  -387,
    -387,  -387,  -387,  -387,  -387,  -387,  -387,  -387,  -387,  -387,
    -387,  -387,  -387,  -387,  -387,  -387,  -387,  -387,  -387,  -387,
    -387,  -387,  -387,  -387,  -387,  -387,  -387,  -387,  -387
};

  /* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     2,     9,    13,   329,   482,   333,    30,   448,   154,
     335,   463,     3,   228,   183,   294,   383,   343,   427,   240,
      15,   550,   581,    39,   125,   331,   285,   213,   311,   188,
     286,    41,    35,   247,   404,   471,   537,   572,    27,    24,
     559,   586,   553,   583,   442,   416,   327,   413,   371,   439,
     532,   568,   575,   598,   184,   231,    28,   201,   243,   244,
     190,   287,    68,    36,   194,   295,    19,    25,   300,   388,
     541,   577,   352,   430,   251,   202,   393,   464,   292,   421,
     236,   237,   255,   516,   560,   197,   302,   337,   418,   446,
     507,   479,   534,   510,   556,   186,   283,   261,   216,   615,
     266,   218,   602,   204,   306,   402,   470,   530,   566,   192,
     226,   325,   379,   449,   206,   619,   272,   222,   610,   277,
     224,   591,   386,   460,   199,   303,   208,   309,   211,   310,
     220,   318,   485,   245,   469,   525,   452,   355,   432,   382,
     454,   397,   466,   425,   490,   501,   547,   487,   435,   498,
     457,   520,    69,    70,    71,    72,    73,   443,   444,    74,
      75,    76,    77,    78,   238,   262,   263,   267,   268,    79,
      80,    81,    82,    83,    84,    85,    86,    87,    88,    89,
      90,    91,    92,    93,    94,   273,   274,   278,   279
};

  /* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
     positive, shift that token.  If negative, reduce the rule whose
     number is the opposite.  If YYTABLE_NINF, syntax error.  */
static const yytype_uint16 yytable[] =
{
      31,   344,   429,   234,   406,    33,   252,   409,   405,   411,
       1,   408,   297,   410,    96,     4,   301,   358,    42,   252,
      10,    43,    11,    44,    45,     5,    46,   438,   259,   260,
       6,    47,     7,    48,    49,    50,    51,    34,   264,   265,
     270,   271,   275,   276,   440,   441,     8,    12,    14,    18,
      16,    17,    20,    21,   426,    22,    52,    53,    54,    55,
      56,    57,    58,    59,    60,    61,    62,    63,    64,    65,
      66,    67,   127,   128,   129,   130,   131,   132,   133,   134,
     135,   136,   137,   138,   139,   140,   141,   142,   143,   144,
     145,   146,   147,   148,   149,   150,   151,   152,   459,    23,
      26,    29,    32,   465,    38,    37,    40,    95,    97,    98,
     473,    99,   100,   101,   102,   103,   104,   105,   106,   107,
     108,   109,   110,   111,   112,   489,   113,   114,   115,   116,
     117,   493,   118,   119,   120,   121,   122,   497,   123,   124,
     153,   502,   189,   191,   196,   198,   182,   181,   185,   193,
     200,   205,   203,   215,   217,   207,   210,   221,   219,   227,
     230,   519,   187,   232,   233,   223,   212,   239,   225,   524,
     235,   241,   248,   249,   250,   246,   253,   254,   256,   257,
     258,   242,   269,   280,   308,   289,   284,   290,   291,   282,
     312,   544,   313,   314,   315,   316,   546,   317,   319,   293,
     320,   281,   321,   322,   323,   324,   326,   328,   288,   332,
     330,   334,   340,   533,   299,   341,   342,   347,   565,   350,
     345,   305,   351,   346,   304,   348,   353,   356,   307,   357,
     363,   354,   368,   336,   387,   369,   374,   580,   375,   582,
     555,   377,   370,   585,   372,   384,   391,   395,   389,   376,
     392,   400,   593,   394,   595,   378,   414,   597,   403,   401,
     419,   423,   571,   338,   428,   339,   385,   415,   431,   606,
     390,   398,   399,   412,   126,   407,   420,   433,   381,   436,
     437,   447,   455,   458,   462,   467,   359,   360,   461,   361,
     362,   396,   472,   364,   365,   451,   366,   367,   445,   474,
     424,   475,   476,   477,   373,   155,   156,   157,   158,   159,
     160,   161,   162,   163,   164,   165,   166,   167,   168,   169,
     170,   171,   172,   173,   174,   175,   176,   177,   178,   179,
     180,   468,   478,   481,   483,   492,   480,   484,   434,   491,
     499,   486,   456,   494,   503,   504,   509,   508,   496,   513,
     515,   500,   511,   521,   518,   522,   526,   527,   523,   531,
     528,   529,   535,   539,   536,   538,   540,   543,   548,   549,
     545,   551,   552,   557,   554,   558,   561,   564,   563,   567,
     569,   570,   573,   574,   576,   579,   584,   587,   589,   588,
     592,   590,   594,   596,   599,   601,   604,   603,   605,   611,
     612,   607,   495,   609,   349,   614,   620,   616,   618,   578,
     417,   214,   622,   562,   195,   450,   229,   621,   453,   542,
     380,   617,   608,   613,   209,   514,   600,     0,   488,   517,
       0,     0,     0,   422,     0,     0,     0,     0,     0,     0,
       0,   296,     0,     0,     0,     0,     0,     0,   505,   506,
       0,     0,     0,     0,     0,     0,     0,     0,   512,   298
};

static const yytype_int16 yycheck[] =
{
      26,   295,   388,     6,   362,    39,   201,   365,   361,   367,
       9,   364,   242,   366,    40,    13,   246,   311,   151,   214,
       5,   154,     7,   156,   157,     0,   159,   413,   183,   184,
      25,   164,    10,   166,   167,   168,   169,    71,   186,   187,
     205,   206,   208,   209,   162,   163,    11,     8,     6,    77,
      10,    10,    24,     7,   383,     6,   189,   190,   191,   192,
     193,   194,   195,   196,   197,   198,   199,   200,   201,   202,
     203,   204,    98,    99,   100,   101,   102,   103,   104,   105,
     106,   107,   108,   109,   110,   111,   112,   113,   114,   115,
     116,   117,   118,   119,   120,   121,   122,   123,   427,    49,
       6,     4,    48,   432,    29,    76,     6,     6,    38,     6,
     439,     6,     6,     6,     6,     6,     6,     6,     6,     6,
       6,     6,     6,     6,     6,   454,     6,     6,     6,     6,
       6,   460,     6,     6,     6,     6,     6,   466,     6,    70,
       4,   470,    69,   111,    93,   125,    15,    28,   103,    75,
      85,   117,   109,   105,   107,   125,   125,   119,   125,     6,
       6,   490,    37,     6,     6,   121,    33,    23,   113,   498,
     173,     6,     6,     6,     6,    41,     6,     6,     6,     6,
       6,   129,     6,     6,   116,     6,    31,   110,     6,    14,
       6,   520,     6,   104,     6,     6,   525,   106,     6,    19,
       6,   227,   118,     6,     6,   120,    57,     4,   234,     4,
       6,     4,    22,   507,    79,     6,    21,     6,   547,     4,
     128,    84,    83,   128,   250,    40,     4,     4,   254,     4,
       4,   135,     4,    95,    74,     6,    30,   566,    36,   568,
     534,     6,    59,   572,   102,     6,     6,     6,    92,    68,
      87,    32,   581,   108,   583,   115,     6,   586,    45,   109,
       6,     6,   556,   289,     6,   291,   123,    55,     6,   598,
     124,   124,   124,   112,    96,   124,    89,     6,   137,     6,
       6,     4,     6,    18,     4,     6,   312,   313,    78,   315,
     316,   139,    56,   319,   320,   133,   322,   323,    97,     6,
     141,     6,    54,     6,   330,   127,   128,   129,   130,   131,
     132,   133,   134,   135,   136,   137,   138,   139,   140,   141,
     142,   143,   144,   145,   146,   147,   148,   149,   150,   151,
     152,   131,    99,     4,    88,    20,    94,     6,   147,     6,
       6,   145,   149,    82,     4,    58,   101,     6,   134,   132,
      91,   143,   114,   122,   136,    86,     6,   108,   138,    61,
      44,   109,     6,     6,    47,   144,    81,   140,     6,    27,
     146,     6,    53,     6,    96,    51,     6,   130,   148,     6,
       6,    98,     6,    63,     4,   142,   100,     6,     4,    90,
     108,   121,    60,    46,    80,   107,    52,    26,    50,    62,
     106,   120,   464,   119,   302,   105,   104,   118,   117,   562,
     373,   174,   116,   542,   163,   420,   184,   618,   422,   517,
     338,   614,   601,   609,   171,   486,   590,    -1,   453,   488,
      -1,    -1,    -1,   380,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,   242,    -1,    -1,    -1,    -1,    -1,    -1,   474,   475,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   484,   243
};

  /* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
     symbol of state STATE-NUM.  */
static const yytype_uint16 yystos[] =
{
       0,     9,   215,   226,    13,     0,    25,    10,    11,   216,
       5,     7,     8,   217,     6,   234,    10,    10,    77,   280,
      24,     7,     6,    49,   253,   281,     6,   252,   270,     4,
     221,   280,    48,    39,    71,   246,   277,    76,    29,   237,
       6,   245,   151,   154,   156,   157,   159,   164,   166,   167,
     168,   169,   189,   190,   191,   192,   193,   194,   195,   196,
     197,   198,   199,   200,   201,   202,   203,   204,   276,   366,
     367,   368,   369,   370,   373,   374,   375,   376,   377,   383,
     384,   385,   386,   387,   388,   389,   390,   391,   392,   393,
     394,   395,   396,   397,   398,     6,   280,    38,     6,     6,
       6,     6,     6,     6,     6,     6,     6,     6,     6,     6,
       6,     6,     6,     6,     6,     6,     6,     6,     6,     6,
       6,     6,     6,     6,    70,   238,   237,   280,   280,   280,
     280,   280,   280,   280,   280,   280,   280,   280,   280,   280,
     280,   280,   280,   280,   280,   280,   280,   280,   280,   280,
     280,   280,   280,     4,   223,   237,   237,   237,   237,   237,
     237,   237,   237,   237,   237,   237,   237,   237,   237,   237,
     237,   237,   237,   237,   237,   237,   237,   237,   237,   237,
     237,    28,    15,   228,   268,   103,   309,    37,   243,    69,
     274,   111,   323,    75,   278,   278,    93,   299,   125,   338,
      85,   271,   289,   109,   317,   117,   328,   125,   340,   328,
     125,   342,    33,   241,   271,   105,   312,   107,   315,   125,
     344,   119,   331,   121,   334,   113,   324,     6,   227,   228,
       6,   269,     6,     6,     6,   173,   294,   295,   378,    23,
     233,     6,   129,   272,   273,   347,    41,   247,     6,     6,
       6,   288,   289,     6,     6,   296,     6,     6,     6,   183,
     184,   311,   379,   380,   186,   187,   314,   381,   382,     6,
     205,   206,   330,   399,   400,   208,   209,   333,   401,   402,
       6,   280,    14,   310,    31,   240,   244,   275,   280,     6,
     110,     6,   292,    19,   229,   279,   294,   295,   347,    79,
     282,   295,   300,   339,   280,    84,   318,   280,   116,   341,
     343,   242,     6,     6,   104,     6,     6,   106,   345,     6,
       6,   118,     6,     6,   120,   325,    57,   260,     4,   218,
       6,   239,     4,   220,     4,   224,    95,   301,   280,   280,
      22,     6,    21,   231,   218,   128,   128,     6,    40,   224,
       4,    83,   286,     4,   135,   351,     4,     4,   218,   280,
     280,   280,   280,     4,   280,   280,   280,   280,     4,     6,
      59,   262,   102,   280,    30,    36,    68,     6,   115,   326,
     301,   137,   353,   230,     6,   123,   336,    74,   283,    92,
     124,     6,    87,   290,   108,     6,   139,   355,   124,   124,
      32,   109,   319,    45,   248,   319,   248,   124,   319,   248,
     319,   248,   112,   261,     6,    55,   259,   260,   302,     6,
      89,   293,   326,     6,   141,   357,   220,   232,     6,   223,
     287,     6,   352,     6,   147,   362,     6,     6,   223,   263,
     162,   163,   258,   371,   372,    97,   303,     4,   222,   327,
     292,   133,   350,   293,   354,     6,   149,   364,    18,   220,
     337,    78,     4,   225,   291,   220,   356,     6,   131,   348,
     320,   249,    56,   220,     6,     6,    54,     6,    99,   305,
      94,     4,   219,    88,     6,   346,   145,   361,   350,   220,
     358,     6,    20,   220,    82,   225,   134,   220,   363,     6,
     143,   359,   220,     4,    58,   280,   280,   304,     6,   101,
     307,   114,   280,   132,   346,    91,   297,   361,   136,   220,
     365,   122,    86,   138,   220,   349,     6,   108,    44,   109,
     321,    61,   264,   218,   306,     6,    47,   250,   144,     6,
      81,   284,   297,   140,   220,   146,   220,   360,     6,    27,
     235,     6,    53,   256,    96,   218,   308,     6,    51,   254,
     298,     6,   284,   148,   130,   220,   322,     6,   265,     6,
      98,   218,   251,     6,    63,   266,     4,   285,   241,   142,
     220,   236,   220,   257,   100,   220,   255,     6,    90,     4,
     121,   335,   108,   220,    60,   220,    46,   220,   267,    80,
     333,   107,   316,    26,    52,    50,   220,   120,   314,   119,
     332,    62,   106,   330,   105,   313,   118,   311,   117,   329,
     104,   296,   116
};

  /* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint16 yyr1[] =
{
       0,   214,   215,   216,   217,   218,   219,   220,   221,   222,
     223,   224,   225,   226,   226,   227,   228,   229,   230,   229,
     232,   231,   233,   233,   234,   235,   236,   235,   238,   237,
     239,   240,   242,   241,   244,   243,   245,   246,   247,   249,
     248,   251,   250,   252,   253,   255,   254,   256,   257,   256,
     258,   258,   259,   261,   260,   263,   262,   264,   265,   264,
     267,   266,   268,   268,   269,   269,   270,   270,   271,   271,
     272,   272,   273,   275,   274,   276,   276,   276,   276,   276,
     276,   276,   276,   276,   276,   276,   276,   276,   276,   276,
     276,   276,   276,   276,   276,   276,   276,   276,   276,   276,
     276,   277,   279,   278,   280,   281,   280,   283,   282,   284,
     285,   284,   287,   286,   288,   289,   291,   290,   292,   293,
     294,   295,   295,   296,   297,   298,   297,   300,   299,   301,
     302,   301,   304,   303,   306,   305,   308,   307,   310,   309,
     311,   311,   312,   313,   313,   314,   314,   315,   316,   316,
     318,   317,   320,   319,   321,   322,   321,   323,   323,   325,
     324,   326,   327,   326,   328,   329,   329,   330,   330,   331,
     332,   332,   333,   333,   334,   335,   335,   337,   336,   339,
     338,   341,   340,   343,   342,   345,   344,   346,   347,   347,
     348,   349,   348,   350,   351,   352,   351,   354,   353,   355,
     356,   355,   358,   357,   359,   360,   359,   361,   362,   363,
     362,   365,   364,   366,   367,   368,   369,   370,   371,   372,
     373,   374,   375,   376,   377,   378,   379,   380,   381,   382,
     383,   384,   385,   386,   387,   388,   389,   390,   391,   392,
     393,   394,   395,   396,   397,   398,   399,   400,   401,   402
};

  /* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     5,     2,     2,     1,     1,     1,     1,     1,
       1,     1,     1,     4,     6,     5,     3,     0,     0,     5,
       0,     5,     0,     3,     5,     0,     0,     5,     0,     5,
       6,     3,     0,     5,     0,     5,     3,     3,     3,     0,
       5,     0,     5,     3,     3,     0,     5,     0,     0,     5,
       1,     1,     3,     0,     5,     0,     5,     0,     0,     5,
       0,     5,     1,     2,     0,     2,     0,     2,     2,     1,
       2,     1,     1,     0,     5,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     3,     0,     5,     0,     0,     5,     0,     5,     0,
       0,     5,     0,     5,     4,     3,     0,     5,     5,     3,
       9,     1,     1,     7,     0,     0,     5,     0,     5,     0,
       0,     5,     0,     5,     0,     5,     0,     5,     0,     5,
       1,     1,     3,     0,     3,     1,     1,     3,     0,     3,
       0,     5,     0,     5,     0,     0,     5,     0,     3,     0,
       5,     0,     0,     5,     3,     0,     3,     1,     1,     3,
       0,     3,     1,     1,     3,     0,     3,     0,     5,     0,
       5,     0,     5,     0,     5,     0,     5,     5,     3,     3,
       0,     0,     5,     3,     0,     0,     5,     0,     5,     0,
       0,     5,     0,     5,     0,     0,     5,     3,     0,     0,
       5,     0,     5,     5,     4,     6,     5,     4,     5,     5,
       5,     9,     7,     6,     4,    16,     4,     4,     4,     4,
       5,     5,     5,     5,     5,     5,     5,     5,     5,     5,
       5,     5,     5,     5,     5,     5,     4,     4,     4,     4
};


#define yyerrok         (yyerrstatus = 0)
#define yyclearin       (yychar = YYEMPTY)
#define YYEMPTY         (-2)
#define YYEOF           0

#define YYACCEPT        goto yyacceptlab
#define YYABORT         goto yyabortlab
#define YYERROR         goto yyerrorlab


#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)                                  \
do                                                              \
  if (yychar == YYEMPTY)                                        \
    {                                                           \
      yychar = (Token);                                         \
      yylval = (Value);                                         \
      YYPOPSTACK (yylen);                                       \
      yystate = *yyssp;                                         \
      goto yybackup;                                            \
    }                                                           \
  else                                                          \
    {                                                           \
      yyerror (YY_("syntax error: cannot back up")); \
      YYERROR;                                                  \
    }                                                           \
while (0)

/* Error token number */
#define YYTERROR        1
#define YYERRCODE       256



/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)                        \
do {                                            \
  if (yydebug)                                  \
    YYFPRINTF Args;                             \
} while (0)

/* This macro is provided for backward compatibility. */
#ifndef YY_LOCATION_PRINT
# define YY_LOCATION_PRINT(File, Loc) ((void) 0)
#endif


# define YY_SYMBOL_PRINT(Title, Type, Value, Location)                    \
do {                                                                      \
  if (yydebug)                                                            \
    {                                                                     \
      YYFPRINTF (stderr, "%s ", Title);                                   \
      yy_symbol_print (stderr,                                            \
                  Type, Value); \
      YYFPRINTF (stderr, "\n");                                           \
    }                                                                     \
} while (0)


/*----------------------------------------.
| Print this symbol's value on YYOUTPUT.  |
`----------------------------------------*/

static void
yy_symbol_value_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
{
  FILE *yyo = yyoutput;
  YYUSE (yyo);
  if (!yyvaluep)
    return;
# ifdef YYPRINT
  if (yytype < YYNTOKENS)
    YYPRINT (yyoutput, yytoknum[yytype], *yyvaluep);
# endif
  YYUSE (yytype);
}


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

static void
yy_symbol_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
{
  YYFPRINTF (yyoutput, "%s %s (",
             yytype < YYNTOKENS ? "token" : "nterm", yytname[yytype]);

  yy_symbol_value_print (yyoutput, yytype, yyvaluep);
  YYFPRINTF (yyoutput, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

static void
yy_stack_print (yytype_int16 *yybottom, yytype_int16 *yytop)
{
  YYFPRINTF (stderr, "Stack now");
  for (; yybottom <= yytop; yybottom++)
    {
      int yybot = *yybottom;
      YYFPRINTF (stderr, " %d", yybot);
    }
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)                            \
do {                                                            \
  if (yydebug)                                                  \
    yy_stack_print ((Bottom), (Top));                           \
} while (0)


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

static void
yy_reduce_print (yytype_int16 *yyssp, YYSTYPE *yyvsp, int yyrule)
{
  unsigned long int yylno = yyrline[yyrule];
  int yynrhs = yyr2[yyrule];
  int yyi;
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %lu):\n",
             yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      YYFPRINTF (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr,
                       yystos[yyssp[yyi + 1 - yynrhs]],
                       &(yyvsp[(yyi + 1) - (yynrhs)])
                                              );
      YYFPRINTF (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)          \
do {                                    \
  if (yydebug)                          \
    yy_reduce_print (yyssp, yyvsp, Rule); \
} while (0)

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args)
# define YY_SYMBOL_PRINT(Title, Type, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   YYSTACK_ALLOC_MAXIMUM < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif


#if YYERROR_VERBOSE

# ifndef yystrlen
#  if defined __GLIBC__ && defined _STRING_H
#   define yystrlen strlen
#  else
/* Return the length of YYSTR.  */
static YYSIZE_T
yystrlen (const char *yystr)
{
  YYSIZE_T yylen;
  for (yylen = 0; yystr[yylen]; yylen++)
    continue;
  return yylen;
}
#  endif
# endif

# ifndef yystpcpy
#  if defined __GLIBC__ && defined _STRING_H && defined _GNU_SOURCE
#   define yystpcpy stpcpy
#  else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
static char *
yystpcpy (char *yydest, const char *yysrc)
{
  char *yyd = yydest;
  const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
#  endif
# endif

# ifndef yytnamerr
/* Copy to YYRES the contents of YYSTR after stripping away unnecessary
   quotes and backslashes, so that it's suitable for yyerror.  The
   heuristic is that double-quoting is unnecessary unless the string
   contains an apostrophe, a comma, or backslash (other than
   backslash-backslash).  YYSTR is taken from yytname.  If YYRES is
   null, do not copy; instead, return the length of what the result
   would have been.  */
static YYSIZE_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYSIZE_T yyn = 0;
      char const *yyp = yystr;

      for (;;)
        switch (*++yyp)
          {
          case '\'':
          case ',':
            goto do_not_strip_quotes;

          case '\\':
            if (*++yyp != '\\')
              goto do_not_strip_quotes;
            /* Fall through.  */
          default:
            if (yyres)
              yyres[yyn] = *yyp;
            yyn++;
            break;

          case '"':
            if (yyres)
              yyres[yyn] = '\0';
            return yyn;
          }
    do_not_strip_quotes: ;
    }

  if (! yyres)
    return yystrlen (yystr);

  return yystpcpy (yyres, yystr) - yyres;
}
# endif

/* Copy into *YYMSG, which is of size *YYMSG_ALLOC, an error message
   about the unexpected token YYTOKEN for the state stack whose top is
   YYSSP.

   Return 0 if *YYMSG was successfully written.  Return 1 if *YYMSG is
   not large enough to hold the message.  In that case, also set
   *YYMSG_ALLOC to the required number of bytes.  Return 2 if the
   required number of bytes is too large to store.  */
static int
yysyntax_error (YYSIZE_T *yymsg_alloc, char **yymsg,
                yytype_int16 *yyssp, int yytoken)
{
  YYSIZE_T yysize0 = yytnamerr (YY_NULLPTR, yytname[yytoken]);
  YYSIZE_T yysize = yysize0;
  enum { YYERROR_VERBOSE_ARGS_MAXIMUM = 5 };
  /* Internationalized format string. */
  const char *yyformat = YY_NULLPTR;
  /* Arguments of yyformat. */
  char const *yyarg[YYERROR_VERBOSE_ARGS_MAXIMUM];
  /* Number of reported tokens (one for the "unexpected", one per
     "expected"). */
  int yycount = 0;

  /* There are many possibilities here to consider:
     - If this state is a consistent state with a default action, then
       the only way this function was invoked is if the default action
       is an error action.  In that case, don't check for expected
       tokens because there are none.
     - The only way there can be no lookahead present (in yychar) is if
       this state is a consistent state with a default action.  Thus,
       detecting the absence of a lookahead is sufficient to determine
       that there is no unexpected or expected token to report.  In that
       case, just report a simple "syntax error".
     - Don't assume there isn't a lookahead just because this state is a
       consistent state with a default action.  There might have been a
       previous inconsistent state, consistent state with a non-default
       action, or user semantic action that manipulated yychar.
     - Of course, the expected token list depends on states to have
       correct lookahead information, and it depends on the parser not
       to perform extra reductions after fetching a lookahead from the
       scanner and before detecting a syntax error.  Thus, state merging
       (from LALR or IELR) and default reductions corrupt the expected
       token list.  However, the list is correct for canonical LR with
       one exception: it will still contain any token that will not be
       accepted due to an error action in a later state.
  */
  if (yytoken != YYEMPTY)
    {
      int yyn = yypact[*yyssp];
      yyarg[yycount++] = yytname[yytoken];
      if (!yypact_value_is_default (yyn))
        {
          /* Start YYX at -YYN if negative to avoid negative indexes in
             YYCHECK.  In other words, skip the first -YYN actions for
             this state because they are default actions.  */
          int yyxbegin = yyn < 0 ? -yyn : 0;
          /* Stay within bounds of both yycheck and yytname.  */
          int yychecklim = YYLAST - yyn + 1;
          int yyxend = yychecklim < YYNTOKENS ? yychecklim : YYNTOKENS;
          int yyx;

          for (yyx = yyxbegin; yyx < yyxend; ++yyx)
            if (yycheck[yyx + yyn] == yyx && yyx != YYTERROR
                && !yytable_value_is_error (yytable[yyx + yyn]))
              {
                if (yycount == YYERROR_VERBOSE_ARGS_MAXIMUM)
                  {
                    yycount = 1;
                    yysize = yysize0;
                    break;
                  }
                yyarg[yycount++] = yytname[yyx];
                {
                  YYSIZE_T yysize1 = yysize + yytnamerr (YY_NULLPTR, yytname[yyx]);
                  if (! (yysize <= yysize1
                         && yysize1 <= YYSTACK_ALLOC_MAXIMUM))
                    return 2;
                  yysize = yysize1;
                }
              }
        }
    }

  switch (yycount)
    {
# define YYCASE_(N, S)                      \
      case N:                               \
        yyformat = S;                       \
      break
      YYCASE_(0, YY_("syntax error"));
      YYCASE_(1, YY_("syntax error, unexpected %s"));
      YYCASE_(2, YY_("syntax error, unexpected %s, expecting %s"));
      YYCASE_(3, YY_("syntax error, unexpected %s, expecting %s or %s"));
      YYCASE_(4, YY_("syntax error, unexpected %s, expecting %s or %s or %s"));
      YYCASE_(5, YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s"));
# undef YYCASE_
    }

  {
    YYSIZE_T yysize1 = yysize + yystrlen (yyformat);
    if (! (yysize <= yysize1 && yysize1 <= YYSTACK_ALLOC_MAXIMUM))
      return 2;
    yysize = yysize1;
  }

  if (*yymsg_alloc < yysize)
    {
      *yymsg_alloc = 2 * yysize;
      if (! (yysize <= *yymsg_alloc
             && *yymsg_alloc <= YYSTACK_ALLOC_MAXIMUM))
        *yymsg_alloc = YYSTACK_ALLOC_MAXIMUM;
      return 1;
    }

  /* Avoid sprintf, as that infringes on the user's name space.
     Don't have undefined behavior even if the translation
     produced a string with the wrong number of "%s"s.  */
  {
    char *yyp = *yymsg;
    int yyi = 0;
    while ((*yyp = *yyformat) != '\0')
      if (*yyp == '%' && yyformat[1] == 's' && yyi < yycount)
        {
          yyp += yytnamerr (yyp, yyarg[yyi++]);
          yyformat += 2;
        }
      else
        {
          yyp++;
          yyformat++;
        }
  }
  return 0;
}
#endif /* YYERROR_VERBOSE */

/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

static void
yydestruct (const char *yymsg, int yytype, YYSTYPE *yyvaluep)
{
  YYUSE (yyvaluep);
  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yytype, yyvaluep, yylocationp);

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  YYUSE (yytype);
  YY_IGNORE_MAYBE_UNINITIALIZED_END
}




/* The lookahead symbol.  */
int yychar;

/* The semantic value of the lookahead symbol.  */
YYSTYPE yylval;
/* Number of syntax errors so far.  */
int yynerrs;


/*----------.
| yyparse.  |
`----------*/

int
yyparse (void)
{
    int yystate;
    /* Number of tokens to shift before error messages enabled.  */
    int yyerrstatus;

    /* The stacks and their tools:
       'yyss': related to states.
       'yyvs': related to semantic values.

       Refer to the stacks through separate pointers, to allow yyoverflow
       to reallocate them elsewhere.  */

    /* The state stack.  */
    yytype_int16 yyssa[YYINITDEPTH];
    yytype_int16 *yyss;
    yytype_int16 *yyssp;

    /* The semantic value stack.  */
    YYSTYPE yyvsa[YYINITDEPTH];
    YYSTYPE *yyvs;
    YYSTYPE *yyvsp;

    YYSIZE_T yystacksize;

  int yyn;
  int yyresult;
  /* Lookahead token as an internal (translated) token number.  */
  int yytoken = 0;
  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;

#if YYERROR_VERBOSE
  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYSIZE_T yymsg_alloc = sizeof yymsgbuf;
#endif

#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N))

  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  yyssp = yyss = yyssa;
  yyvsp = yyvs = yyvsa;
  yystacksize = YYINITDEPTH;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY; /* Cause a token to be read.  */
  goto yysetstate;

/*------------------------------------------------------------.
| yynewstate -- Push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
 yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;

 yysetstate:
  *yyssp = yystate;

  if (yyss + yystacksize - 1 <= yyssp)
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYSIZE_T yysize = yyssp - yyss + 1;

#ifdef yyoverflow
      {
        /* Give user a chance to reallocate the stack.  Use copies of
           these so that the &'s don't force the real ones into
           memory.  */
        YYSTYPE *yyvs1 = yyvs;
        yytype_int16 *yyss1 = yyss;

        /* Each stack pointer address is followed by the size of the
           data in use in that stack, in bytes.  This used to be a
           conditional around just the two extra args, but that might
           be undefined if yyoverflow is a macro.  */
        yyoverflow (YY_("memory exhausted"),
                    &yyss1, yysize * sizeof (*yyssp),
                    &yyvs1, yysize * sizeof (*yyvsp),
                    &yystacksize);

        yyss = yyss1;
        yyvs = yyvs1;
      }
#else /* no yyoverflow */
# ifndef YYSTACK_RELOCATE
      goto yyexhaustedlab;
# else
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
        goto yyexhaustedlab;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
        yystacksize = YYMAXDEPTH;

      {
        yytype_int16 *yyss1 = yyss;
        union yyalloc *yyptr =
          (union yyalloc *) YYSTACK_ALLOC (YYSTACK_BYTES (yystacksize));
        if (! yyptr)
          goto yyexhaustedlab;
        YYSTACK_RELOCATE (yyss_alloc, yyss);
        YYSTACK_RELOCATE (yyvs_alloc, yyvs);
#  undef YYSTACK_RELOCATE
        if (yyss1 != yyssa)
          YYSTACK_FREE (yyss1);
      }
# endif
#endif /* no yyoverflow */

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;

      YYDPRINTF ((stderr, "Stack size increased to %lu\n",
                  (unsigned long int) yystacksize));

      if (yyss + yystacksize - 1 <= yyssp)
        YYABORT;
    }

  YYDPRINTF ((stderr, "Entering state %d\n", yystate));

  if (yystate == YYFINAL)
    YYACCEPT;

  goto yybackup;

/*-----------.
| yybackup.  |
`-----------*/
yybackup:

  /* Do appropriate processing given the current state.  Read a
     lookahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to lookahead token.  */
  yyn = yypact[yystate];
  if (yypact_value_is_default (yyn))
    goto yydefault;

  /* Not known => get a lookahead token if don't already have one.  */

  /* YYCHAR is either YYEMPTY or YYEOF or a valid lookahead symbol.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token: "));
      yychar = yylex ();
    }

  if (yychar <= YYEOF)
    {
      yychar = yytoken = YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YY_SYMBOL_PRINT ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    goto yydefault;
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yytable_value_is_error (yyn))
        goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the lookahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);

  /* Discard the shifted token.  */
  yychar = YYEMPTY;

  yystate = yyn;
  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END

  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- Do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     '$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];


  YY_REDUCE_PRINT (yyn);
  switch (yyn)
    {
        case 2:

    {(yyval.CRCLProgramFileVal) = new CRCLProgramFile((yyvsp[-4].XmlVersionVal), (yyvsp[-2].XmlHeaderForCRCLProgramVal), (yyvsp[-1].CRCLProgramTypeVal));
	   CRCLProgramTree = (yyval.CRCLProgramFileVal);
	   if (XmlIDREF::idMissing())
	     yyerror("xs:ID missing for xs:IDREF");
	  }

    break;

  case 3:

    {(yyval.XmlHeaderForCRCLProgramVal) = new XmlHeaderForCRCLProgram((yyvsp[0].SchemaLocationVal));}

    break;

  case 4:

    {(yyval.SchemaLocationVal) = new SchemaLocation("xsi", (yyvsp[0].sVal), false);
	   free((yyvsp[0].sVal));
	  }

    break;

  case 5:

    {(yyval.XmlBooleanVal) = new XmlBoolean((yyvsp[0].sVal));
	   if ((yyval.XmlBooleanVal)->bad)
	     yyerror("bad XmlBoolean");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 6:

    {(yyval.XmlDateTimeVal) = new XmlDateTime((yyvsp[0].sVal));
	   if ((yyval.XmlDateTimeVal)->bad)
	     yyerror("bad XmlDateTime");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 7:

    {(yyval.XmlDecimalVal) = new XmlDecimal((yyvsp[0].sVal));
	   if ((yyval.XmlDecimalVal)->bad)
	     yyerror("bad XmlDecimal");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 8:

    {(yyval.XmlIDVal) = new XmlID((yyvsp[0].sVal));
	   if ((yyval.XmlIDVal)->bad)
	     yyerror("bad XmlID");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 9:

    {(yyval.XmlIDREFVal) = new XmlIDREF((yyvsp[0].sVal));
	   if ((yyval.XmlIDREFVal)->bad)
	     yyerror("bad XmlIDREF");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 10:

    {(yyval.XmlPositiveIntegerVal) = new XmlPositiveInteger((yyvsp[0].sVal));
	   if ((yyval.XmlPositiveIntegerVal)->bad)
	     yyerror("bad XmlPositiveInteger");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 11:

    {(yyval.XmlStringVal) = new XmlString((yyvsp[0].sVal));
	   if ((yyval.XmlStringVal)->bad)
	     yyerror("bad XmlString");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 12:

    {(yyval.XmlTokenVal) = new XmlToken((yyvsp[0].sVal));
	   if ((yyval.XmlTokenVal)->bad)
	     yyerror("bad XmlToken");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 13:

    {(yyval.XmlVersionVal) = new XmlVersion(false);
	   if (strcmp((yyvsp[-1].sVal), "1.0"))
	     yyerror("version number must be 1.0");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 14:

    {(yyval.XmlVersionVal) = new XmlVersion(true);
	   if (strcmp((yyvsp[-3].sVal), "1.0"))
	     yyerror("version number must be 1.0");
	   else if ((strcmp((yyvsp[-1].sVal), "UTF-8")) && (strcmp((yyvsp[-1].sVal), "utf-8")))
	     yyerror("encoding must be UTF-8");
	   free((yyvsp[-3].sVal));
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 15:

    {(yyval.ActuateJointTypeVal) = new ActuateJointType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlPositiveIntegerVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].JointDetailsTypeVal));}

    break;

  case 16:

    {(yyval.ActuateJointTypeVal) = (yyvsp[-1].ActuateJointTypeVal);}

    break;

  case 17:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 18:

    {yyReadData = 1;}

    break;

  case 19:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 20:

    {yyReadData = 1;}

    break;

  case 21:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 22:

    {(yyval.PointTypeVal) = 0;}

    break;

  case 23:

    {(yyval.PointTypeVal) = (yyvsp[-1].PointTypeVal);}

    break;

  case 24:

    {(yyval.CRCLProgramTypeVal) = new CRCLProgramType((yyvsp[-3].XmlIDVal), (yyvsp[-2].InitCanonTypeVal), (yyvsp[-1].ListMiddleCommandTypeVal), (yyvsp[0].EndCanonTypeVal));}

    break;

  case 25:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 26:

    {yyReadData = 1;}

    break;

  case 27:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 28:

    {yyReadData = 1;}

    break;

  case 29:

    {(yyval.XmlPositiveIntegerVal) = (yyvsp[-1].XmlPositiveIntegerVal);}

    break;

  case 30:

    {(yyval.ConfigureJointReportTypeVal) = new ConfigureJointReportType((yyvsp[-4].XmlIDVal), (yyvsp[-3].XmlPositiveIntegerVal), (yyvsp[-2].XmlBooleanVal), (yyvsp[-1].XmlBooleanVal), (yyvsp[0].XmlBooleanVal));}

    break;

  case 31:

    {(yyval.ConfigureJointReportTypeVal) = (yyvsp[-1].ConfigureJointReportTypeVal);}

    break;

  case 32:

    {yyReadData = 1;}

    break;

  case 33:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

    break;

  case 34:

    {yyReadData = 1;}

    break;

  case 35:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 36:

    {(yyval.EndCanonTypeVal) = new EndCanonType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlPositiveIntegerVal));}

    break;

  case 37:

    {(yyval.EndCanonTypeVal) = (yyvsp[-1].EndCanonTypeVal);}

    break;

  case 38:

    {(yyval.PoseOnlyLocationTypeVal) = (yyvsp[-1].PoseOnlyLocationTypeVal);}

    break;

  case 39:

    {yyReadData = 1;}

    break;

  case 40:

    {(yyval.FractionTypeVal) = new FractionType((yyvsp[-1].sVal));
	   if ((yyval.FractionTypeVal)->bad)
	     yyerror("bad Fraction value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 41:

    {yyReadData = 1;}

    break;

  case 42:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 43:

    {(yyval.InitCanonTypeVal) = new InitCanonType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlPositiveIntegerVal));}

    break;

  case 44:

    {(yyval.InitCanonTypeVal) = (yyvsp[-1].InitCanonTypeVal);}

    break;

  case 45:

    {yyReadData = 1;}

    break;

  case 46:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 47:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 48:

    {yyReadData = 1;}

    break;

  case 49:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 50:

    {(yyval.JointDetailsTypeVal) = (yyvsp[0].JointForceTorqueTypeVal);}

    break;

  case 51:

    {(yyval.JointDetailsTypeVal) = (yyvsp[0].JointSpeedAccelTypeVal);}

    break;

  case 52:

    {(yyval.JointDetailsTypeVal) = (yyvsp[-1].JointDetailsTypeVal);}

    break;

  case 53:

    {yyReadData = 1;}

    break;

  case 54:

    {(yyval.XmlPositiveIntegerVal) = (yyvsp[-1].XmlPositiveIntegerVal);}

    break;

  case 55:

    {yyReadData = 1;}

    break;

  case 56:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 57:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 58:

    {yyReadData = 1;}

    break;

  case 59:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 60:

    {yyReadData = 1;}

    break;

  case 61:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 62:

    {(yyval.ListActuateJointTypeVal) = new std::list<ActuateJointType *>;
	   (yyval.ListActuateJointTypeVal)->push_back((yyvsp[0].ActuateJointTypeVal));}

    break;

  case 63:

    {(yyval.ListActuateJointTypeVal) = (yyvsp[-1].ListActuateJointTypeVal);
	   (yyval.ListActuateJointTypeVal)->push_back((yyvsp[0].ActuateJointTypeVal));}

    break;

  case 64:

    {(yyval.ListConfigureJointReportTypeVal) = new std::list<ConfigureJointReportType *>;}

    break;

  case 65:

    {(yyval.ListConfigureJointReportTypeVal) = (yyvsp[-1].ListConfigureJointReportTypeVal);
	   (yyval.ListConfigureJointReportTypeVal)->push_back((yyvsp[0].ConfigureJointReportTypeVal));}

    break;

  case 66:

    {(yyval.ListMiddleCommandTypeVal) = new std::list<MiddleCommandType *>;}

    break;

  case 67:

    {(yyval.ListMiddleCommandTypeVal) = (yyvsp[-1].ListMiddleCommandTypeVal);
	   (yyval.ListMiddleCommandTypeVal)->push_back((yyvsp[0].MiddleCommandTypeVal));}

    break;

  case 68:

    {(yyval.ListParameterSettingTypeVal) = (yyvsp[-1].ListParameterSettingTypeVal);
	   (yyval.ListParameterSettingTypeVal)->push_back((yyvsp[0].ParameterSettingTypeVal));}

    break;

  case 69:

    {(yyval.ListParameterSettingTypeVal) = new std::list<ParameterSettingType *>;
	   (yyval.ListParameterSettingTypeVal)->push_back((yyvsp[0].ParameterSettingTypeVal));}

    break;

  case 70:

    {(yyval.ListPoseOnlyLocationTypeVal) = (yyvsp[-1].ListPoseOnlyLocationTypeVal);
	   (yyval.ListPoseOnlyLocationTypeVal)->push_back((yyvsp[0].PoseOnlyLocationTypeVal));}

    break;

  case 71:

    {(yyval.ListPoseOnlyLocationTypeVal) = new std::list<PoseOnlyLocationType *>;
	   (yyval.ListPoseOnlyLocationTypeVal)->push_back((yyvsp[0].PoseOnlyLocationTypeVal));}

    break;

  case 72:

    {(yyval.ListPoseOnlyLocationTypeVal) = (yyvsp[0].ListPoseOnlyLocationTypeVal);
	   if ((yyvsp[0].ListPoseOnlyLocationTypeVal)->size() < 2)
	     yyerror("must be at least 2 Waypoints");
	  }

    break;

  case 73:

    {yyReadData = 1;}

    break;

  case 74:

    {(yyval.XmlStringVal) = (yyvsp[-1].XmlStringVal);}

    break;

  case 75:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].ActuateJointsTypeVal);}

    break;

  case 76:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].CloseToolChangerTypeVal);}

    break;

  case 77:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].ConfigureJointReportsTypeVal);}

    break;

  case 78:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].DwellTypeVal);}

    break;

  case 79:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].GetStatusTypeVal);}

    break;

  case 80:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].MessageTypeVal);}

    break;

  case 81:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].MoveScrewTypeVal);}

    break;

  case 82:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].MoveThroughToTypeVal);}

    break;

  case 83:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].MoveToTypeVal);}

    break;

  case 84:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].OpenToolChangerTypeVal);}

    break;

  case 85:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].RunProgramTypeVal);}

    break;

  case 86:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetAngleUnitsTypeVal);}

    break;

  case 87:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetEndEffectorParametersTypeVal);}

    break;

  case 88:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetEndEffectorTypeVal);}

    break;

  case 89:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetEndPoseToleranceTypeVal);}

    break;

  case 90:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetForceUnitsTypeVal);}

    break;

  case 91:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetIntermediatePoseToleranceTypeVal);}

    break;

  case 92:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetLengthUnitsTypeVal);}

    break;

  case 93:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetMotionCoordinationTypeVal);}

    break;

  case 94:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetRobotParametersTypeVal);}

    break;

  case 95:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetRotAccelTypeVal);}

    break;

  case 96:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetRotSpeedTypeVal);}

    break;

  case 97:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetTorqueUnitsTypeVal);}

    break;

  case 98:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetTransAccelTypeVal);}

    break;

  case 99:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetTransSpeedTypeVal);}

    break;

  case 100:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].StopMotionTypeVal);}

    break;

  case 101:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[-1].MiddleCommandTypeVal);}

    break;

  case 102:

    {yyReadData = 1;}

    break;

  case 103:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

    break;

  case 104:

    {(yyval.XmlIDVal) = 0;}

    break;

  case 105:

    {yyReadData = 1;}

    break;

  case 106:

    {(yyval.XmlIDVal) = (yyvsp[-1].XmlIDVal);}

    break;

  case 107:

    {yyReadData = 1;}

    break;

  case 108:

    {(yyval.XmlPositiveIntegerVal) = (yyvsp[-1].XmlPositiveIntegerVal);}

    break;

  case 109:

    {(yyval.PositiveDecimalTypeVal) = 0;}

    break;

  case 110:

    {yyReadData = 1;}

    break;

  case 111:

    {(yyval.PositiveDecimalTypeVal) = new PositiveDecimalType((yyvsp[-1].sVal));
	   if ((yyval.PositiveDecimalTypeVal)->bad)
	     yyerror("bad OrientationStandardDeviation value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 112:

    {yyReadData = 1;}

    break;

  case 113:

    {(yyval.XmlTokenVal) = (yyvsp[-1].XmlTokenVal);}

    break;

  case 114:

    {(yyval.ParameterSettingTypeVal) = new ParameterSettingType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlTokenVal), (yyvsp[0].XmlTokenVal));}

    break;

  case 115:

    {(yyval.ParameterSettingTypeVal) = (yyvsp[-1].ParameterSettingTypeVal);}

    break;

  case 116:

    {yyReadData = 1;}

    break;

  case 117:

    {(yyval.XmlTokenVal) = (yyvsp[-1].XmlTokenVal);}

    break;

  case 118:

    {(yyval.PointTypeVal) = new PointType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));}

    break;

  case 119:

    {(yyval.PointTypeVal) = (yyvsp[-1].PointTypeVal);}

    break;

  case 120:

    {(yyval.PoseOnlyLocationTypeVal) = new PoseOnlyLocationType((yyvsp[-7].XmlIDVal), (yyvsp[-6].XmlIDREFVal), (yyvsp[-5].XmlDateTimeVal), (yyvsp[-4].PointTypeVal), (yyvsp[-3].VectorTypeVal), (yyvsp[-2].VectorTypeVal), (yyvsp[-1].PositiveDecimalTypeVal), (yyvsp[0].PositiveDecimalTypeVal));}

    break;

  case 121:

    {(yyval.PoseOnlyLocationTypeVal) = (yyvsp[0].PoseOnlyLocationTypeVal);}

    break;

  case 122:

    {(yyval.PoseOnlyLocationTypeVal) = (yyvsp[0].PoseAndSetTypeVal);}

    break;

  case 123:

    {(yyval.PoseToleranceTypeVal) = new PoseToleranceType((yyvsp[-5].XmlIDVal), (yyvsp[-4].XmlDecimalVal), (yyvsp[-3].XmlDecimalVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));}

    break;

  case 124:

    {(yyval.PositiveDecimalTypeVal) = 0;}

    break;

  case 125:

    {yyReadData = 1;}

    break;

  case 126:

    {(yyval.PositiveDecimalTypeVal) = new PositiveDecimalType((yyvsp[-1].sVal));
	   if ((yyval.PositiveDecimalTypeVal)->bad)
	     yyerror("bad PositionStandardDeviation value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 127:

    {yyReadData = 1;}

    break;

  case 128:

    {(yyval.XmlStringVal) = (yyvsp[-1].XmlStringVal);}

    break;

  case 129:

    {(yyval.XmlIDREFVal) = 0;}

    break;

  case 130:

    {yyReadData = 1;}

    break;

  case 131:

    {(yyval.XmlIDREFVal) = (yyvsp[-1].XmlIDREFVal);}

    break;

  case 132:

    {yyReadData = 1;}

    break;

  case 133:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

    break;

  case 134:

    {yyReadData = 1;}

    break;

  case 135:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

    break;

  case 136:

    {yyReadData = 1;}

    break;

  case 137:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

    break;

  case 138:

    {yyReadData = 1;}

    break;

  case 139:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

    break;

  case 140:

    {(yyval.RotAccelTypeVal) = (yyvsp[0].RotAccelAbsoluteTypeVal);}

    break;

  case 141:

    {(yyval.RotAccelTypeVal) = (yyvsp[0].RotAccelRelativeTypeVal);}

    break;

  case 142:

    {(yyval.RotAccelTypeVal) = (yyvsp[-1].RotAccelTypeVal);}

    break;

  case 143:

    {(yyval.RotAccelTypeVal) = 0;}

    break;

  case 144:

    {(yyval.RotAccelTypeVal) = (yyvsp[-1].RotAccelTypeVal);}

    break;

  case 145:

    {(yyval.RotSpeedTypeVal) = (yyvsp[0].RotSpeedAbsoluteTypeVal);}

    break;

  case 146:

    {(yyval.RotSpeedTypeVal) = (yyvsp[0].RotSpeedRelativeTypeVal);}

    break;

  case 147:

    {(yyval.RotSpeedTypeVal) = (yyvsp[-1].RotSpeedTypeVal);}

    break;

  case 148:

    {(yyval.RotSpeedTypeVal) = 0;}

    break;

  case 149:

    {(yyval.RotSpeedTypeVal) = (yyvsp[-1].RotSpeedTypeVal);}

    break;

  case 150:

    {yyReadData = 1;}

    break;

  case 151:

    {(yyval.FractionTypeVal) = new FractionType((yyvsp[-1].sVal));
	   if ((yyval.FractionTypeVal)->bad)
	     yyerror("bad Setting value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 152:

    {yyReadData = 1;}

    break;

  case 153:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 154:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 155:

    {yyReadData = 1;}

    break;

  case 156:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 157:

    {(yyval.PoseOnlyLocationTypeVal) = 0;}

    break;

  case 158:

    {(yyval.PoseOnlyLocationTypeVal) = (yyvsp[-1].PoseOnlyLocationTypeVal);}

    break;

  case 159:

    {yyReadData = 1;}

    break;

  case 160:

    {(yyval.StopConditionEnumTypeVal) = new StopConditionEnumType((yyvsp[-1].sVal));
	   if ((yyval.StopConditionEnumTypeVal)->bad)
	     yyerror("bad StopCondition value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 161:

    {(yyval.XmlDateTimeVal) = 0;}

    break;

  case 162:

    {yyReadData = 1;}

    break;

  case 163:

    {(yyval.XmlDateTimeVal) = (yyvsp[-1].XmlDateTimeVal);}

    break;

  case 164:

    {(yyval.PoseToleranceTypeVal) = (yyvsp[-1].PoseToleranceTypeVal);}

    break;

  case 165:

    {(yyval.PoseToleranceTypeVal) = 0;}

    break;

  case 166:

    {(yyval.PoseToleranceTypeVal) = (yyvsp[-1].PoseToleranceTypeVal);}

    break;

  case 167:

    {(yyval.TransAccelTypeVal) = (yyvsp[0].TransAccelAbsoluteTypeVal);}

    break;

  case 168:

    {(yyval.TransAccelTypeVal) = (yyvsp[0].TransAccelRelativeTypeVal);}

    break;

  case 169:

    {(yyval.TransAccelTypeVal) = (yyvsp[-1].TransAccelTypeVal);}

    break;

  case 170:

    {(yyval.TransAccelTypeVal) = 0;}

    break;

  case 171:

    {(yyval.TransAccelTypeVal) = (yyvsp[-1].TransAccelTypeVal);}

    break;

  case 172:

    {(yyval.TransSpeedTypeVal) = (yyvsp[0].TransSpeedAbsoluteTypeVal);}

    break;

  case 173:

    {(yyval.TransSpeedTypeVal) = (yyvsp[0].TransSpeedRelativeTypeVal);}

    break;

  case 174:

    {(yyval.TransSpeedTypeVal) = (yyvsp[-1].TransSpeedTypeVal);}

    break;

  case 175:

    {(yyval.TransSpeedTypeVal) = 0;}

    break;

  case 176:

    {(yyval.TransSpeedTypeVal) = (yyvsp[-1].TransSpeedTypeVal);}

    break;

  case 177:

    {yyReadData = 1;}

    break;

  case 178:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 179:

    {yyReadData = 1;}

    break;

  case 180:

    {(yyval.AngleUnitEnumTypeVal) = new AngleUnitEnumType((yyvsp[-1].sVal));
	   if ((yyval.AngleUnitEnumTypeVal)->bad)
	     yyerror("bad UnitName value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 181:

    {yyReadData = 1;}

    break;

  case 182:

    {(yyval.ForceUnitEnumTypeVal) = new ForceUnitEnumType((yyvsp[-1].sVal));
	   if ((yyval.ForceUnitEnumTypeVal)->bad)
	     yyerror("bad UnitName value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 183:

    {yyReadData = 1;}

    break;

  case 184:

    {(yyval.LengthUnitEnumTypeVal) = new LengthUnitEnumType((yyvsp[-1].sVal));
	   if ((yyval.LengthUnitEnumTypeVal)->bad)
	     yyerror("bad UnitName value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 185:

    {yyReadData = 1;}

    break;

  case 186:

    {(yyval.TorqueUnitEnumTypeVal) = new TorqueUnitEnumType((yyvsp[-1].sVal));
	   if ((yyval.TorqueUnitEnumTypeVal)->bad)
	     yyerror("bad UnitName value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 187:

    {(yyval.VectorTypeVal) = new VectorType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));}

    break;

  case 188:

    {(yyval.PoseOnlyLocationTypeVal) = (yyvsp[-1].PoseOnlyLocationTypeVal);}

    break;

  case 189:

    {(yyval.PoseOnlyLocationTypeVal) = (yyvsp[-1].PoseOnlyLocationTypeVal);}

    break;

  case 190:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 191:

    {yyReadData = 1;}

    break;

  case 192:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 193:

    {(yyval.VectorTypeVal) = (yyvsp[-1].VectorTypeVal);}

    break;

  case 194:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 195:

    {yyReadData = 1;}

    break;

  case 196:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 197:

    {yyReadData = 1;}

    break;

  case 198:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 199:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 200:

    {yyReadData = 1;}

    break;

  case 201:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 202:

    {yyReadData = 1;}

    break;

  case 203:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 204:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 205:

    {yyReadData = 1;}

    break;

  case 206:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 207:

    {(yyval.VectorTypeVal) = (yyvsp[-1].VectorTypeVal);}

    break;

  case 208:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 209:

    {yyReadData = 1;}

    break;

  case 210:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 211:

    {yyReadData = 1;}

    break;

  case 212:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 213:

    {(yyval.ActuateJointsTypeVal) = new ActuateJointsType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].ListActuateJointTypeVal));
	   (yyval.ActuateJointsTypeVal)->printTypp = true;
	  }

    break;

  case 214:

    {(yyval.CloseToolChangerTypeVal) = new CloseToolChangerType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlPositiveIntegerVal));
	   (yyval.CloseToolChangerTypeVal)->printTypp = true;
	  }

    break;

  case 215:

    {(yyval.ConfigureJointReportsTypeVal) = new ConfigureJointReportsType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlPositiveIntegerVal), (yyvsp[-1].XmlBooleanVal), (yyvsp[0].ListConfigureJointReportTypeVal));
	   (yyval.ConfigureJointReportsTypeVal)->printTypp = true;
	  }

    break;

  case 216:

    {(yyval.DwellTypeVal) = new DwellType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.DwellTypeVal)->printTypp = true;
	  }

    break;

  case 217:

    {(yyval.GetStatusTypeVal) = new GetStatusType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlPositiveIntegerVal));
	   (yyval.GetStatusTypeVal)->printTypp = true;
	  }

    break;

  case 218:

    {(yyval.JointForceTorqueTypeVal) = new JointForceTorqueType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.JointForceTorqueTypeVal)->printTypp = true;
	  }

    break;

  case 219:

    {(yyval.JointSpeedAccelTypeVal) = new JointSpeedAccelType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.JointSpeedAccelTypeVal)->printTypp = true;
	  }

    break;

  case 220:

    {(yyval.MessageTypeVal) = new MessageType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].XmlStringVal));
	   (yyval.MessageTypeVal)->printTypp = true;
	  }

    break;

  case 221:

    {(yyval.MoveScrewTypeVal) = new MoveScrewType((yyvsp[-6].XmlIDVal), (yyvsp[-5].XmlPositiveIntegerVal), (yyvsp[-4].PoseOnlyLocationTypeVal), (yyvsp[-3].PointTypeVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.MoveScrewTypeVal)->printTypp = true;
	  }

    break;

  case 222:

    {(yyval.MoveThroughToTypeVal) = new MoveThroughToType((yyvsp[-4].XmlIDVal), (yyvsp[-3].XmlPositiveIntegerVal), (yyvsp[-2].XmlBooleanVal), (yyvsp[-1].ListPoseOnlyLocationTypeVal), (yyvsp[0].XmlPositiveIntegerVal));
	   (yyval.MoveThroughToTypeVal)->printTypp = true;
	  }

    break;

  case 223:

    {(yyval.MoveToTypeVal) = new MoveToType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlPositiveIntegerVal), (yyvsp[-1].XmlBooleanVal), (yyvsp[0].PoseOnlyLocationTypeVal));
	   (yyval.MoveToTypeVal)->printTypp = true;
	  }

    break;

  case 224:

    {(yyval.OpenToolChangerTypeVal) = new OpenToolChangerType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlPositiveIntegerVal));
	   (yyval.OpenToolChangerTypeVal)->printTypp = true;
	  }

    break;

  case 225:

    {(yyval.PoseAndSetTypeVal) = new PoseAndSetType((yyvsp[-13].XmlIDVal), (yyvsp[-12].XmlIDREFVal), (yyvsp[-11].XmlDateTimeVal), (yyvsp[-10].PointTypeVal), (yyvsp[-9].VectorTypeVal), (yyvsp[-8].VectorTypeVal), (yyvsp[-7].PositiveDecimalTypeVal), (yyvsp[-6].PositiveDecimalTypeVal), (yyvsp[-5].XmlBooleanVal), (yyvsp[-4].TransSpeedTypeVal), (yyvsp[-3].RotSpeedTypeVal), (yyvsp[-2].TransAccelTypeVal), (yyvsp[-1].RotAccelTypeVal), (yyvsp[0].PoseToleranceTypeVal));
	   (yyval.PoseAndSetTypeVal)->printTypp = true;
	  }

    break;

  case 226:

    {(yyval.RotAccelAbsoluteTypeVal) = new RotAccelAbsoluteType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.RotAccelAbsoluteTypeVal)->printTypp = true;
	  }

    break;

  case 227:

    {(yyval.RotAccelRelativeTypeVal) = new RotAccelRelativeType((yyvsp[-1].XmlIDVal), (yyvsp[0].FractionTypeVal));
	   (yyval.RotAccelRelativeTypeVal)->printTypp = true;
	  }

    break;

  case 228:

    {(yyval.RotSpeedAbsoluteTypeVal) = new RotSpeedAbsoluteType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.RotSpeedAbsoluteTypeVal)->printTypp = true;
	  }

    break;

  case 229:

    {(yyval.RotSpeedRelativeTypeVal) = new RotSpeedRelativeType((yyvsp[-1].XmlIDVal), (yyvsp[0].FractionTypeVal));
	   (yyval.RotSpeedRelativeTypeVal)->printTypp = true;
	  }

    break;

  case 230:

    {(yyval.RunProgramTypeVal) = new RunProgramType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].XmlStringVal));
	   (yyval.RunProgramTypeVal)->printTypp = true;
	  }

    break;

  case 231:

    {(yyval.SetAngleUnitsTypeVal) = new SetAngleUnitsType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].AngleUnitEnumTypeVal));
	   (yyval.SetAngleUnitsTypeVal)->printTypp = true;
	  }

    break;

  case 232:

    {(yyval.SetEndEffectorParametersTypeVal) = new SetEndEffectorParametersType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].ListParameterSettingTypeVal));
	   (yyval.SetEndEffectorParametersTypeVal)->printTypp = true;
	  }

    break;

  case 233:

    {(yyval.SetEndEffectorTypeVal) = new SetEndEffectorType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].FractionTypeVal));
	   (yyval.SetEndEffectorTypeVal)->printTypp = true;
	  }

    break;

  case 234:

    {(yyval.SetEndPoseToleranceTypeVal) = new SetEndPoseToleranceType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].PoseToleranceTypeVal));
	   (yyval.SetEndPoseToleranceTypeVal)->printTypp = true;
	  }

    break;

  case 235:

    {(yyval.SetForceUnitsTypeVal) = new SetForceUnitsType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].ForceUnitEnumTypeVal));
	   (yyval.SetForceUnitsTypeVal)->printTypp = true;
	  }

    break;

  case 236:

    {(yyval.SetIntermediatePoseToleranceTypeVal) = new SetIntermediatePoseToleranceType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].PoseToleranceTypeVal));
	   (yyval.SetIntermediatePoseToleranceTypeVal)->printTypp = true;
	  }

    break;

  case 237:

    {(yyval.SetLengthUnitsTypeVal) = new SetLengthUnitsType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].LengthUnitEnumTypeVal));
	   (yyval.SetLengthUnitsTypeVal)->printTypp = true;
	  }

    break;

  case 238:

    {(yyval.SetMotionCoordinationTypeVal) = new SetMotionCoordinationType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].XmlBooleanVal));
	   (yyval.SetMotionCoordinationTypeVal)->printTypp = true;
	  }

    break;

  case 239:

    {(yyval.SetRobotParametersTypeVal) = new SetRobotParametersType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].ListParameterSettingTypeVal));
	   (yyval.SetRobotParametersTypeVal)->printTypp = true;
	  }

    break;

  case 240:

    {(yyval.SetRotAccelTypeVal) = new SetRotAccelType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].RotAccelTypeVal));
	   (yyval.SetRotAccelTypeVal)->printTypp = true;
	  }

    break;

  case 241:

    {(yyval.SetRotSpeedTypeVal) = new SetRotSpeedType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].RotSpeedTypeVal));
	   (yyval.SetRotSpeedTypeVal)->printTypp = true;
	  }

    break;

  case 242:

    {(yyval.SetTorqueUnitsTypeVal) = new SetTorqueUnitsType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].TorqueUnitEnumTypeVal));
	   (yyval.SetTorqueUnitsTypeVal)->printTypp = true;
	  }

    break;

  case 243:

    {(yyval.SetTransAccelTypeVal) = new SetTransAccelType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].TransAccelTypeVal));
	   (yyval.SetTransAccelTypeVal)->printTypp = true;
	  }

    break;

  case 244:

    {(yyval.SetTransSpeedTypeVal) = new SetTransSpeedType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].TransSpeedTypeVal));
	   (yyval.SetTransSpeedTypeVal)->printTypp = true;
	  }

    break;

  case 245:

    {(yyval.StopMotionTypeVal) = new StopMotionType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].StopConditionEnumTypeVal));
	   (yyval.StopMotionTypeVal)->printTypp = true;
	  }

    break;

  case 246:

    {(yyval.TransAccelAbsoluteTypeVal) = new TransAccelAbsoluteType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.TransAccelAbsoluteTypeVal)->printTypp = true;
	  }

    break;

  case 247:

    {(yyval.TransAccelRelativeTypeVal) = new TransAccelRelativeType((yyvsp[-1].XmlIDVal), (yyvsp[0].FractionTypeVal));
	   (yyval.TransAccelRelativeTypeVal)->printTypp = true;
	  }

    break;

  case 248:

    {(yyval.TransSpeedAbsoluteTypeVal) = new TransSpeedAbsoluteType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.TransSpeedAbsoluteTypeVal)->printTypp = true;
	  }

    break;

  case 249:

    {(yyval.TransSpeedRelativeTypeVal) = new TransSpeedRelativeType((yyvsp[-1].XmlIDVal), (yyvsp[0].FractionTypeVal));
	   (yyval.TransSpeedRelativeTypeVal)->printTypp = true;
	  }

    break;



      default: break;
    }
  /* User semantic actions sometimes alter yychar, and that requires
     that yytoken be updated with the new translation.  We take the
     approach of translating immediately before every use of yytoken.
     One alternative is translating here after every semantic action,
     but that translation would be missed if the semantic action invokes
     YYABORT, YYACCEPT, or YYERROR immediately after altering yychar or
     if it invokes YYBACKUP.  In the case of YYABORT or YYACCEPT, an
     incorrect destructor might then be invoked immediately.  In the
     case of YYERROR or YYBACKUP, subsequent parser actions might lead
     to an incorrect destructor call or verbose syntax error message
     before the lookahead is translated.  */
  YY_SYMBOL_PRINT ("-> $$ =", yyr1[yyn], &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);

  *++yyvsp = yyval;

  /* Now 'shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */

  yyn = yyr1[yyn];

  yystate = yypgoto[yyn - YYNTOKENS] + *yyssp;
  if (0 <= yystate && yystate <= YYLAST && yycheck[yystate] == *yyssp)
    yystate = yytable[yystate];
  else
    yystate = yydefgoto[yyn - YYNTOKENS];

  goto yynewstate;


/*--------------------------------------.
| yyerrlab -- here on detecting error.  |
`--------------------------------------*/
yyerrlab:
  /* Make sure we have latest lookahead translation.  See comments at
     user semantic actions for why this is necessary.  */
  yytoken = yychar == YYEMPTY ? YYEMPTY : YYTRANSLATE (yychar);

  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
#if ! YYERROR_VERBOSE
      yyerror (YY_("syntax error"));
#else
# define YYSYNTAX_ERROR yysyntax_error (&yymsg_alloc, &yymsg, \
                                        yyssp, yytoken)
      {
        char const *yymsgp = YY_("syntax error");
        int yysyntax_error_status;
        yysyntax_error_status = YYSYNTAX_ERROR;
        if (yysyntax_error_status == 0)
          yymsgp = yymsg;
        else if (yysyntax_error_status == 1)
          {
            if (yymsg != yymsgbuf)
              YYSTACK_FREE (yymsg);
            yymsg = (char *) YYSTACK_ALLOC (yymsg_alloc);
            if (!yymsg)
              {
                yymsg = yymsgbuf;
                yymsg_alloc = sizeof yymsgbuf;
                yysyntax_error_status = 2;
              }
            else
              {
                yysyntax_error_status = YYSYNTAX_ERROR;
                yymsgp = yymsg;
              }
          }
        yyerror (yymsgp);
        if (yysyntax_error_status == 2)
          goto yyexhaustedlab;
      }
# undef YYSYNTAX_ERROR
#endif
    }



  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse lookahead token after an
         error, discard it.  */

      if (yychar <= YYEOF)
        {
          /* Return failure if at end of input.  */
          if (yychar == YYEOF)
            YYABORT;
        }
      else
        {
          yydestruct ("Error: discarding",
                      yytoken, &yylval);
          yychar = YYEMPTY;
        }
    }

  /* Else will try to reuse lookahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:

  /* Pacify compilers like GCC when the user code never invokes
     YYERROR and the label yyerrorlab therefore never appears in user
     code.  */
  if (/*CONSTCOND*/ 0)
     goto yyerrorlab;

  /* Do not reclaim the symbols of the rule whose action triggered
     this YYERROR.  */
  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);
  yystate = *yyssp;
  goto yyerrlab1;


/*-------------------------------------------------------------.
| yyerrlab1 -- common code for both syntax error and YYERROR.  |
`-------------------------------------------------------------*/
yyerrlab1:
  yyerrstatus = 3;      /* Each real token shifted decrements this.  */

  for (;;)
    {
      yyn = yypact[yystate];
      if (!yypact_value_is_default (yyn))
        {
          yyn += YYTERROR;
          if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYTERROR)
            {
              yyn = yytable[yyn];
              if (0 < yyn)
                break;
            }
        }

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
        YYABORT;


      yydestruct ("Error: popping",
                  yystos[yystate], yyvsp);
      YYPOPSTACK (1);
      yystate = *yyssp;
      YY_STACK_PRINT (yyss, yyssp);
    }

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END


  /* Shift the error token.  */
  YY_SYMBOL_PRINT ("Shifting", yystos[yyn], yyvsp, yylsp);

  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturn;

/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturn;

#if !defined yyoverflow || YYERROR_VERBOSE
/*-------------------------------------------------.
| yyexhaustedlab -- memory exhaustion comes here.  |
`-------------------------------------------------*/
yyexhaustedlab:
  yyerror (YY_("memory exhausted"));
  yyresult = 2;
  /* Fall through.  */
#endif

yyreturn:
  if (yychar != YYEMPTY)
    {
      /* Make sure we have latest lookahead translation.  See comments at
         user semantic actions for why this is necessary.  */
      yytoken = YYTRANSLATE (yychar);
      yydestruct ("Cleanup: discarding lookahead",
                  yytoken, &yylval);
    }
  /* Do not reclaim the symbols of the rule whose action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
                  yystos[*yyssp], yyvsp);
      YYPOPSTACK (1);
    }
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
#if YYERROR_VERBOSE
  if (yymsg != yymsgbuf)
    YYSTACK_FREE (yymsg);
#endif
  return yyresult;
}



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
