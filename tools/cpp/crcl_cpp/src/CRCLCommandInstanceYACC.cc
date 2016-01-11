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
   by #include "CRCLCommandInstanceYACC.hh".  */
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

#endif /* !YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_TOOLS_CPP_CRCL_CPP_SRC_CRCLCOMMANDINSTANCEYACC_HH_INCLUDED  */

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
#define YYLAST   456

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  188
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  176
/* YYNRULES -- Number of rules.  */
#define YYNRULES  233
/* YYNSTATES -- Number of states.  */
#define YYNSTATES  588

/* YYTRANSLATE[YYX] -- Symbol number corresponding to YYX as returned
   by yylex, with out-of-bounds checking.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   442

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
     185,   186,   187
};

#if YYDEBUG
  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,   437,   437,   447,   452,   459,   468,   477,   486,   495,
     504,   513,   519,   532,   538,   549,   550,   550,   556,   556,
     563,   564,   569,   574,   576,   578,   580,   582,   584,   586,
     588,   590,   592,   594,   596,   598,   600,   602,   604,   606,
     608,   610,   612,   614,   616,   618,   620,   622,   624,   626,
     628,   633,   639,   640,   640,   646,   646,   652,   659,   665,
     665,   671,   671,   677,   687,   687,   696,   696,   701,   701,
     707,   708,   708,   714,   716,   721,   726,   726,   732,   732,
     739,   740,   740,   746,   746,   756,   759,   767,   768,   775,
     779,   785,   788,   794,   802,   802,   812,   812,   819,   820,
     820,   825,   825,   831,   831,   837,   843,   848,   848,   854,
     860,   865,   872,   878,   880,   885,   885,   891,   891,   897,
     897,   903,   903,   909,   909,   914,   916,   921,   927,   928,
     933,   935,   940,   946,   947,   952,   952,   961,   961,   967,
     968,   968,   974,   975,   980,   980,   990,   996,   997,  1002,
    1004,  1009,  1015,  1016,  1021,  1023,  1028,  1034,  1035,  1040,
    1040,  1045,  1045,  1054,  1054,  1063,  1063,  1072,  1072,  1081,
    1087,  1089,  1095,  1096,  1096,  1102,  1108,  1109,  1109,  1115,
    1115,  1121,  1122,  1122,  1128,  1128,  1134,  1135,  1135,  1141,
    1147,  1148,  1148,  1154,  1154,  1159,  1168,  1176,  1185,  1193,
    1201,  1209,  1217,  1225,  1233,  1241,  1251,  1261,  1270,  1278,
    1289,  1297,  1305,  1313,  1321,  1329,  1337,  1346,  1354,  1362,
    1370,  1378,  1386,  1394,  1403,  1411,  1419,  1427,  1435,  1443,
    1452,  1460,  1468,  1476
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
  "AXISPOINTSTART", "CRCLCOMMANDINSTANCEEND", "CRCLCOMMANDINSTANCESTART",
  "CRCLCOMMANDEND", "CRCLCOMMANDSTART", "CHANGERATEEND", "CHANGERATESTART",
  "COMMANDIDEND", "COMMANDIDSTART", "CONFIGUREJOINTREPORTEND",
  "CONFIGUREJOINTREPORTSTART", "COORDINATEDEND", "COORDINATEDSTART",
  "DWELLTIMEEND", "DWELLTIMESTART", "ENDPOSITIONEND", "ENDPOSITIONSTART",
  "FORCEEND", "FORCESTART", "FRACTIONEND", "FRACTIONSTART", "IEND",
  "ISTART", "JEND", "JSTART", "JOINTACCELEND", "JOINTACCELSTART",
  "JOINTDETAILSEND", "JOINTDETAILSSTART", "JOINTNUMBEREND",
  "JOINTNUMBERSTART", "JOINTPOSITIONEND", "JOINTPOSITIONSTART",
  "JOINTSPEEDEND", "JOINTSPEEDSTART", "KEND", "KSTART",
  "LINEARVELOCITYEND", "LINEARVELOCITYSTART", "MESSAGEEND", "MESSAGESTART",
  "MOMENTEND", "MOMENTSTART", "MOVESTRAIGHTEND", "MOVESTRAIGHTSTART",
  "NAMEEND", "NAMESTART", "NUMPOSITIONSEND", "NUMPOSITIONSSTART",
  "PARAMETERNAMEEND", "PARAMETERNAMESTART", "PARAMETERSETTINGEND",
  "PARAMETERSETTINGSTART", "PARAMETERVALUEEND", "PARAMETERVALUESTART",
  "POINTEND", "POINTSTART", "PROGRAMTEXTEND", "PROGRAMTEXTSTART",
  "REPORTPOSITIONEND", "REPORTPOSITIONSTART", "REPORTTORQUEORFORCEEND",
  "REPORTTORQUEORFORCESTART", "REPORTVELOCITYEND", "REPORTVELOCITYSTART",
  "RESETALLEND", "RESETALLSTART", "ROTACCELEND", "ROTACCELSTART",
  "ROTSPEEDEND", "ROTSPEEDSTART", "SETTINGEND", "SETTINGSTART",
  "STARTPOSITIONEND", "STARTPOSITIONSTART", "STOPCONDITIONEND",
  "STOPCONDITIONSTART", "TOLERANCEEND", "TOLERANCESTART", "TRANSACCELEND",
  "TRANSACCELSTART", "TRANSSPEEDEND", "TRANSSPEEDSTART", "TURNEND",
  "TURNSTART", "UNITNAMEEND", "UNITNAMESTART", "WAYPOINTEND",
  "WAYPOINTSTART", "XAXISTOLERANCEEND", "XAXISTOLERANCESTART", "XAXISEND",
  "XAXISSTART", "XPOINTTOLERANCEEND", "XPOINTTOLERANCESTART", "XEND",
  "XSTART", "YPOINTTOLERANCEEND", "YPOINTTOLERANCESTART", "YEND", "YSTART",
  "ZAXISTOLERANCEEND", "ZAXISTOLERANCESTART", "ZAXISEND", "ZAXISSTART",
  "ZPOINTTOLERANCEEND", "ZPOINTTOLERANCESTART", "ZEND", "ZSTART",
  "ACTUATEJOINTTYPEDECL", "ACTUATEJOINTSTYPEDECL",
  "CRCLCOMMANDINSTANCETYPEDECL", "CRCLCOMMANDTYPEDECL",
  "CLOSETOOLCHANGERTYPEDECL", "CONFIGUREJOINTREPORTTYPEDECL",
  "CONFIGUREJOINTREPORTSTYPEDECL", "DWELLTYPEDECL", "ENDCANONTYPEDECL",
  "GETSTATUSTYPEDECL", "INITCANONTYPEDECL", "JOINTDETAILSTYPEDECL",
  "JOINTFORCETORQUETYPEDECL", "JOINTSPEEDACCELTYPEDECL", "MESSAGETYPEDECL",
  "MIDDLECOMMANDTYPEDECL", "MOVESCREWTYPEDECL", "MOVETHROUGHTOTYPEDECL",
  "MOVETOTYPEDECL", "OPENTOOLCHANGERTYPEDECL", "PARAMETERSETTINGTYPEDECL",
  "POINTTYPEDECL", "POSEANDSETTYPEDECL", "POSETOLERANCETYPEDECL",
  "POSETYPEDECL", "ROTACCELABSOLUTETYPEDECL", "ROTACCELRELATIVETYPEDECL",
  "ROTACCELTYPEDECL", "ROTSPEEDABSOLUTETYPEDECL",
  "ROTSPEEDRELATIVETYPEDECL", "ROTSPEEDTYPEDECL", "RUNPROGRAMTYPEDECL",
  "SETANGLEUNITSTYPEDECL", "SETENDEFFECTORPARAMETERSTYPEDECL",
  "SETENDEFFECTORTYPEDECL", "SETENDPOSETOLERANCETYPEDECL",
  "SETFORCEUNITSTYPEDECL", "SETINTERMEDIATEPOSETOLERANCETYPEDECL",
  "SETLENGTHUNITSTYPEDECL", "SETMOTIONCOORDINATIONTYPEDECL",
  "SETROBOTPARAMETERSTYPEDECL", "SETROTACCELTYPEDECL",
  "SETROTSPEEDTYPEDECL", "SETTORQUEUNITSTYPEDECL", "SETTRANSACCELTYPEDECL",
  "SETTRANSSPEEDTYPEDECL", "STOPMOTIONTYPEDECL",
  "TRANSACCELABSOLUTETYPEDECL", "TRANSACCELRELATIVETYPEDECL",
  "TRANSACCELTYPEDECL", "TRANSSPEEDABSOLUTETYPEDECL",
  "TRANSSPEEDRELATIVETYPEDECL", "TRANSSPEEDTYPEDECL", "TWISTTYPEDECL",
  "VECTORTYPEDECL", "WRENCHTYPEDECL", "$accept",
  "y_CRCLCommandInstanceFile", "y_XmlHeaderForCRCLCommandInstance",
  "y_SchemaLocation", "y_XmlBoolean", "y_XmlDecimal", "y_XmlID",
  "y_XmlPositiveInteger", "y_XmlString", "y_XmlToken", "y_XmlVersion",
  "y_ActuateJointType", "y_ActuateJoint_ActuateJointType_1_u",
  "y_AxialDistanceFree_XmlDecimal_0", "$@1",
  "y_AxialDistanceScrew_XmlDecimal", "$@2", "y_AxisPoint_PointType_0",
  "y_CRCLCommandInstanceType", "y_CRCLCommandTypeAny",
  "y_CRCLCommand_CRCLCommandType", "y_ChangeRate_XmlDecimal_0", "$@3",
  "y_CommandID_XmlPositiveInteger", "$@4", "y_ConfigureJointReportType",
  "y_ConfigureJointReport_Configure1002", "y_Coordinated_XmlBoolean",
  "$@5", "y_DwellTime_XmlDecimal", "$@6", "y_EndPosition_PoseType",
  "y_Fraction_FractionType", "$@7", "y_I_XmlDecimal", "$@8",
  "y_J_XmlDecimal", "$@9", "y_JointAccel_XmlDecimal_0", "$@10",
  "y_JointDetailsTypeAny", "y_JointDetails_JointDetailsType",
  "y_JointNumber_XmlPositiveInteger", "$@11", "y_JointPosition_XmlDecimal",
  "$@12", "y_JointSpeed_XmlDecimal_0", "$@13", "y_K_XmlDecimal", "$@14",
  "y_ListActuateJoint_ActuateJointType_1_u",
  "y_ListConfigureJointReport_Configure1002",
  "y_ListParameterSetting_ParameterSett1003",
  "y_ListWaypoint_PoseType_2_u", "y_ListWaypoint_PoseType_2_u_Check",
  "y_Message_XmlString", "$@15", "y_MoveStraight_XmlBoolean", "$@16",
  "y_Name_XmlID_0", "$@17", "y_NumPositions_XmlPositiveInteger", "$@18",
  "y_ParameterName_XmlToken", "$@19", "y_ParameterSettingType",
  "y_ParameterSetting_ParameterSett1003", "y_ParameterValue_XmlToken",
  "$@20", "y_PointType", "y_Point_PointType", "y_PoseToleranceType",
  "y_PoseType", "y_PoseTypeAny", "y_ProgramText_XmlString", "$@21",
  "y_ReportPosition_XmlBoolean", "$@22",
  "y_ReportTorqueOrForce_XmlBoolean", "$@23",
  "y_ReportVelocity_XmlBoolean", "$@24", "y_ResetAll_XmlBoolean", "$@25",
  "y_RotAccelTypeAny", "y_RotAccel_RotAccelType",
  "y_RotAccel_RotAccelType_0", "y_RotSpeedTypeAny",
  "y_RotSpeed_RotSpeedType", "y_RotSpeed_RotSpeedType_0",
  "y_Setting_FractionType", "$@26", "y_Setting_XmlDecimal", "$@27",
  "y_Setting_XmlDecimal_0", "$@28", "y_StartPosition_PoseType_0",
  "y_StopCondition_StopConditionEnumType", "$@29",
  "y_Tolerance_PoseToleranceType", "y_Tolerance_PoseToleranceType_0",
  "y_TransAccelTypeAny", "y_TransAccel_TransAccelType",
  "y_TransAccel_TransAccelType_0", "y_TransSpeedTypeAny",
  "y_TransSpeed_TransSpeedType", "y_TransSpeed_TransSpeedType_0",
  "y_Turn_XmlDecimal", "$@30", "y_UnitName_AngleUnitEnumType", "$@31",
  "y_UnitName_ForceUnitEnumType", "$@32", "y_UnitName_LengthUnitEnumType",
  "$@33", "y_UnitName_TorqueUnitEnumType", "$@34", "y_VectorType",
  "y_Waypoint_PoseType_2_u", "y_XAxisTolerance_XmlDecimal_0", "$@35",
  "y_XAxis_VectorType", "y_XPointTolerance_XmlDecimal_0", "$@36",
  "y_X_XmlDecimal", "$@37", "y_YPointTolerance_XmlDecimal_0", "$@38",
  "y_Y_XmlDecimal", "$@39", "y_ZAxisTolerance_XmlDecimal_0", "$@40",
  "y_ZAxis_VectorType", "y_ZPointTolerance_XmlDecimal_0", "$@41",
  "y_Z_XmlDecimal", "$@42", "y_x_ActuateJointsType",
  "y_x_CloseToolChangerType", "y_x_ConfigureJointReportsType",
  "y_x_DwellType", "y_x_EndCanonType", "y_x_GetStatusType",
  "y_x_InitCanonType", "y_x_JointForceTorqueType",
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
     435,   436,   437,   438,   439,   440,   441,   442
};
# endif

#define YYPACT_NINF -350

#define yypact_value_is_default(Yystate) \
  (!!((Yystate) == (-350)))

#define YYTABLE_NINF -1

#define yytable_value_is_error(Yytable_value) \
  0

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
     STATE-NUM.  */
static const yytype_int16 yypact[] =
{
      42,    52,    86,    62,    78,  -350,    79,    47,    81,    85,
      82,  -350,    83,  -350,    25,    71,    89,  -350,    91,    72,
    -350,  -350,  -350,   -93,  -350,    94,    95,    96,    97,    98,
      99,   100,   101,   102,   104,   105,   106,   107,   109,   110,
     111,   112,   113,   114,   116,   117,   118,   119,   120,   121,
     122,   123,   124,   125,    74,  -350,  -350,  -350,  -350,  -350,
    -350,  -350,  -350,  -350,  -350,  -350,  -350,  -350,  -350,  -350,
    -350,  -350,  -350,  -350,  -350,  -350,  -350,  -350,  -350,  -350,
    -350,  -350,  -350,  -350,    64,    25,    25,    25,    25,    25,
      25,    25,    25,    25,    25,    25,    25,    25,    25,    25,
      25,    25,    25,    25,    25,    25,    25,    25,    25,    25,
      25,    25,    25,  -350,  -350,   115,   115,   115,   115,   115,
     115,   115,   115,   115,   115,   115,   115,   115,   115,   115,
     115,   115,   115,   115,   115,   115,   115,   115,   115,   115,
     115,   115,   115,   129,   127,  -350,    48,   103,  -350,  -350,
    -350,    73,    41,    76,    76,  -350,    63,    38,    75,    53,
      50,    43,    50,    44,   128,    75,    65,    61,    46,    55,
      54,    66,  -350,   154,  -350,   127,   156,  -350,   160,  -350,
     161,  -350,    23,   145,   163,    59,   132,   167,  -350,   168,
    -350,   169,    75,  -350,   170,  -350,   172,  -350,   173,  -350,
    -350,   174,  -350,   175,  -350,    75,  -126,  -350,  -100,  -350,
     176,  -350,  -117,  -350,  -116,  -350,   177,  -350,   180,    25,
     171,  -350,  -350,   153,  -350,  -350,    25,   181,  -350,    92,
    -350,   183,   182,  -350,    23,    59,   131,  -350,    23,  -350,
    -350,  -350,    25,   130,  -350,  -350,    25,    90,  -350,  -350,
    -350,   185,   187,   126,  -350,  -350,   188,   189,   134,  -350,
    -350,  -350,   190,   193,   108,  -350,  -350,   197,   199,   133,
    -350,  -350,  -350,  -350,   179,   158,  -350,   202,   201,  -350,
     208,   209,   135,    25,  -350,    25,   195,   212,   194,   202,
     136,   138,  -350,   214,  -350,   198,   209,   219,   162,  -350,
     223,   140,  -350,   229,   234,   202,    25,    25,  -350,    25,
      25,  -350,   238,    25,    25,  -350,    25,    25,  -350,   239,
    -350,   243,   196,  -350,   157,    25,   218,  -350,   216,  -350,
     191,   183,   143,   135,   141,  -350,  -350,   249,   137,   200,
    -350,  -350,  -350,  -350,   184,   155,   255,   192,   178,   256,
     144,   159,   165,   236,   215,   225,   215,   225,   203,   215,
     225,   215,   225,   211,  -350,   265,   224,  -350,   158,  -350,
    -350,  -350,   235,   268,   149,   143,   271,   204,   208,  -350,
     272,  -350,  -350,   180,  -350,  -350,  -350,   273,  -350,  -350,
    -350,   274,   186,  -350,  -350,  -350,   306,  -350,   308,  -350,
    -350,  -350,  -350,  -350,  -350,  -350,  -350,  -350,   180,  -350,
     -76,  -350,   233,  -350,    25,   205,   268,  -350,   149,  -350,
     311,   206,   300,   208,  -350,   250,   317,  -350,   208,  -350,
     316,   210,  -350,  -350,   276,   208,   318,   319,   280,  -350,
    -350,   320,   246,   287,  -350,   207,   128,   208,  -350,   328,
    -350,  -350,   315,   208,  -350,  -350,   264,   317,   222,   208,
    -350,   333,   217,   208,   336,  -350,   289,    25,    25,  -350,
    -350,   335,   257,   339,   299,  -350,   242,   230,   208,  -350,
    -350,   244,  -350,   275,  -350,   232,   208,  -350,   343,  -350,
     259,   312,  -350,   260,   301,   202,  -350,   350,  -350,  -350,
     351,   302,  -116,   266,  -350,   240,   208,  -350,  -350,  -350,
     237,   208,  -350,  -350,  -350,   354,   334,   358,   321,   284,
     202,  -350,   208,  -350,   361,  -350,   267,  -100,   269,  -350,
     245,  -350,   261,   208,  -350,   362,  -350,  -350,   363,  -350,
    -350,   290,   202,   332,   208,  -350,  -350,   285,  -117,   288,
    -350,  -350,   254,   208,  -350,   208,  -350,  -350,   294,  -350,
     338,   208,  -350,   279,  -126,   286,  -350,   291,   208,   330,
     208,  -350,  -350,   331,  -350,   298,   172,  -350,  -350,   364,
    -350,   342,  -350,  -350,   293,  -350,  -350,  -350
};

  /* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
     Performed when YYTABLE does not specify something else to do.  Zero
     means the default is an error.  */
static const yytype_uint8 yydefact[] =
{
       0,     0,     0,     0,     0,     1,     0,     0,     0,     0,
       0,    11,     0,     3,    98,     0,     0,     4,     0,     0,
       2,    12,    99,     0,    22,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    23,    24,    25,    26,    27,
      28,    29,    30,    31,    32,    33,    34,    35,    36,    37,
      38,    39,    40,    41,    42,    43,    44,    45,    46,    47,
      48,    49,    50,     7,     0,    98,    98,    98,    98,    98,
      98,    98,    98,    98,    98,    98,    98,    98,    98,    98,
      98,    98,    98,    98,    98,    98,    98,    98,    98,    98,
      98,    98,    98,    51,   100,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   196,     0,     0,   199,   200,
     201,     0,   142,     0,     0,   208,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,    55,     0,    85,   195,     0,    87,     0,   198,
       0,   204,     0,    20,     0,     0,     0,     0,   214,     0,
     215,     0,   216,    90,     0,   217,     0,   218,     0,   219,
     220,     0,   221,     0,   222,   223,     0,   224,     0,   225,
       0,   226,     0,   227,     0,   228,     0,   229,     0,    98,
       0,    86,   123,   197,    61,    94,    98,     0,   113,     0,
     114,     0,    15,    96,     0,    93,     0,    92,     0,   207,
     115,   161,    98,     0,    89,   135,    98,     0,   163,   165,
      59,     0,     0,     0,   125,   126,     0,     0,     0,   130,
     131,   167,     0,     0,     0,   149,   150,     0,     0,     0,
     154,   155,   144,     8,     0,     0,    14,     0,     0,    88,
       0,     0,     0,    98,   143,    98,     0,     0,     0,     0,
       0,     0,    91,     0,   206,     0,     0,     0,     0,   106,
       0,   176,   146,     0,     0,     0,    98,    98,   127,    98,
      98,   132,     0,    98,    98,   151,    98,    98,   156,     0,
      56,     0,     0,     5,     0,    98,     0,     6,     0,     9,
       0,     0,     0,     0,     0,    21,    16,     0,     0,     0,
     170,   171,   101,    63,     0,     0,     0,     0,     0,     0,
     181,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,    76,     0,     0,   124,     0,    58,
      62,    95,     0,     0,     0,     0,     0,     0,     0,    18,
       0,   205,    97,     0,   116,   162,   103,     0,   105,   136,
     177,     0,   190,   164,   166,    60,     0,   210,     0,   211,
     212,   213,   168,   230,   231,   232,   233,   145,     0,    78,
       0,    13,     0,   110,    98,     0,     0,   112,     0,   179,
       0,     0,     0,     0,   159,     0,     0,   107,     0,   182,
       0,   172,   137,    64,     0,     0,     0,     0,     0,    73,
      74,     0,     0,     0,   175,     0,     0,     0,   184,     0,
     109,    17,     0,     0,   102,    10,     0,     0,     0,     0,
     191,     0,   186,     0,     0,    77,     0,    98,    98,    75,
     117,     0,     0,     0,     0,   189,   157,     0,     0,   193,
      19,     0,   104,     0,   178,     0,     0,   173,     0,   111,
       0,     0,    79,   139,    80,     0,   119,     0,    57,    66,
       0,     0,     0,   133,   180,     0,     0,   160,   108,   183,
       0,     0,   187,   138,    65,     0,    52,     0,    70,     0,
       0,   121,     0,    68,     0,   169,     0,     0,   152,   185,
       0,   192,     0,     0,   140,     0,   202,    81,     0,   203,
     118,     0,     0,     0,     0,    83,   158,     0,     0,   128,
     194,   174,     0,     0,    53,     0,    71,   120,     0,    67,
       0,     0,   134,     0,     0,   147,   188,     0,     0,     0,
       0,   122,    69,     0,   153,     0,     0,   209,   141,     0,
      82,     0,    84,   129,     0,    54,    72,   148
};

  /* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -350,  -350,  -350,  -350,  -261,  -314,  -350,  -349,   139,   -66,
    -350,  -350,   220,  -350,  -350,  -350,  -350,  -350,  -350,  -350,
    -350,  -350,  -350,   166,  -350,  -350,  -350,   -52,  -350,  -350,
    -350,  -350,  -327,  -350,  -350,  -350,  -350,  -350,  -350,  -350,
    -350,  -350,    28,  -350,  -350,  -350,  -350,  -350,  -350,  -350,
    -350,  -350,   241,  -350,  -350,  -350,  -350,   247,  -350,   -85,
    -350,  -350,  -350,  -350,  -350,  -350,  -155,  -350,  -350,    67,
      69,  -179,   213,  -196,  -350,  -350,  -350,  -350,  -350,  -350,
    -350,  -350,  -350,  -350,  -165,  -350,  -350,  -127,  -350,  -350,
    -350,  -350,  -320,  -350,  -350,  -350,  -350,  -350,  -350,   248,
    -350,  -145,  -350,  -350,   -98,  -350,  -350,  -350,  -350,  -350,
    -350,  -350,  -350,  -350,  -350,  -350,  -350,   -11,   221,  -350,
    -350,    32,  -350,  -350,  -350,  -350,  -350,  -350,  -350,  -350,
    -350,  -350,   -10,  -350,  -350,  -350,  -350,  -350,  -350,  -350,
    -350,  -350,  -350,  -350,  -350,  -350,  -350,  -350,  -350,  -350,
    -350,  -350,  -350,  -350,  -350,  -350,  -350,  -350,  -350,  -350,
    -350,  -350,  -350,  -350,  -350,  -350,  -350,  -350,  -350,  -350,
    -350,  -350,  -350,  -350,  -350,  -350
};

  /* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     2,     9,    13,   324,   328,    84,   274,   330,   456,
       3,   220,   174,   288,   378,   338,   423,   232,    15,    54,
      24,   536,   568,   144,   218,   326,   279,   204,   305,   179,
     280,   239,   399,   464,   474,   522,   501,   544,   539,   570,
     438,   411,   322,   408,   366,   435,   518,   555,   525,   561,
     175,   223,   192,   235,   236,   181,   281,   185,   289,    19,
      25,   294,   383,   347,   426,   243,   193,   388,   457,   286,
     332,   247,   228,   229,   188,   296,   442,   495,   472,   520,
     498,   542,   177,   277,   253,   207,   565,   258,   209,   528,
     195,   300,   397,   463,   516,   553,   183,   217,   319,   197,
     577,   264,   213,   549,   269,   215,   503,   381,   453,   190,
     297,   199,   303,   202,   304,   211,   312,   415,   237,   462,
     511,   374,   350,   428,   377,   447,   392,   459,   421,   478,
     489,   533,   417,   431,   486,   450,   506,    55,    56,    57,
      58,    59,    60,    61,   439,   440,    62,    63,    64,    65,
      66,   230,   254,   255,   259,   260,    67,    68,    69,    70,
      71,    72,    73,    74,    75,    76,    77,    78,    79,    80,
      81,    82,   265,   266,   270,   271
};

  /* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
     positive, shift that token.  If negative, reduce the rule whose
     number is the opposite.  If YYTABLE_NINF, syntax error.  */
static const yytype_uint16 yytable[] =
{
     115,   116,   117,   118,   119,   120,   121,   122,   123,   124,
     125,   126,   127,   128,   129,   130,   131,   132,   133,   134,
     135,   136,   137,   138,   139,   140,   141,   142,   339,   226,
     401,   251,   252,   404,   425,   406,   400,   244,   291,   403,
      26,   405,   295,    27,   353,    28,    29,    30,    31,    32,
     244,     1,    10,    33,    11,    34,    35,    36,    37,   434,
     256,   257,   262,   263,   422,     4,   267,   268,   436,   437,
      38,    39,    40,    41,    42,    43,    44,    45,    46,    47,
      48,    49,    50,    51,    52,    53,     5,     6,     7,    12,
       8,    14,    16,    17,    18,    20,    21,    22,    83,    23,
     113,    85,    86,    87,    88,    89,    90,    91,    92,   452,
      93,    94,    95,    96,   458,    97,    98,    99,   100,   101,
     102,   466,   103,   104,   105,   106,   107,   108,   109,   110,
     111,   112,   114,   477,   275,   172,   180,   176,   182,   481,
     178,   282,   173,   184,   187,   485,   143,   189,   194,   490,
     191,   196,   198,   201,   208,   210,   206,   298,   212,   214,
     219,   301,   222,   203,   505,   216,   224,   225,   231,   233,
     234,   238,   510,   240,   241,   242,   245,   227,   246,   248,
     249,   250,   261,   272,   273,   276,   278,   283,   284,   285,
     302,   306,   530,   307,   309,   310,   313,   532,   333,   314,
     334,   287,   293,   316,   299,   317,   323,   325,   543,   320,
     315,   321,   327,   329,   331,   337,   308,   335,   336,   552,
     342,   354,   355,   345,   356,   357,   311,   348,   359,   360,
     560,   361,   362,   351,   519,   346,   343,   318,   352,   567,
     368,   569,   358,   363,   380,   367,   340,   573,   341,   364,
     369,   365,   370,   371,   579,   379,   581,   349,   373,   541,
     376,   386,   390,   385,   384,   391,   382,   393,   398,   387,
     395,   409,   389,   394,   414,   410,   416,   419,   424,   427,
     429,   558,   145,   146,   147,   148,   149,   150,   151,   152,
     153,   154,   155,   156,   157,   158,   159,   160,   161,   162,
     163,   164,   165,   166,   167,   168,   169,   170,   171,   407,
     396,   402,   432,   413,   433,   430,   441,   448,   451,   444,
     454,   455,   460,   461,   467,   468,   470,   420,   465,   443,
     469,   471,   473,   475,   479,   480,   482,   449,   484,   487,
     491,   496,   488,   492,   497,   499,   500,   502,   504,   512,
     507,   508,   509,   513,   514,   515,   521,   523,   517,   527,
     534,   524,   529,   535,   537,   531,   540,   545,   554,   556,
     538,   546,   548,   551,   557,   550,   559,   562,   566,   564,
     571,   574,   493,   494,   572,   578,   580,   576,   583,   582,
     586,   483,   585,   587,   476,   221,   412,   584,   372,   575,
     547,   186,   375,   563,   526,   445,   205,   418,   446,     0,
     200,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   344,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,   290,     0,     0,
       0,     0,     0,     0,     0,     0,   292
};

static const yytype_int16 yycheck[] =
{
      85,    86,    87,    88,    89,    90,    91,    92,    93,    94,
      95,    96,    97,    98,    99,   100,   101,   102,   103,   104,
     105,   106,   107,   108,   109,   110,   111,   112,   289,     6,
     357,   157,   158,   360,   383,   362,   356,   192,   234,   359,
     133,   361,   238,   136,   305,   138,   139,   140,   141,   142,
     205,     9,     5,   146,     7,   148,   149,   150,   151,   408,
     160,   161,   179,   180,   378,    13,   182,   183,   144,   145,
     163,   164,   165,   166,   167,   168,   169,   170,   171,   172,
     173,   174,   175,   176,   177,   178,     0,    25,    10,     8,
      11,     6,    10,    10,    69,    24,     7,     6,     4,    27,
      26,     6,     6,     6,     6,     6,     6,     6,     6,   423,
       6,     6,     6,     6,   428,     6,     6,     6,     6,     6,
       6,   435,     6,     6,     6,     6,     6,     6,     6,     6,
       6,     6,    68,   447,   219,     6,    63,    89,    97,   453,
      37,   226,    15,    67,    81,   459,    31,   109,    95,   463,
      75,   101,   109,   109,    93,   109,    91,   242,   103,   105,
       6,   246,     6,    35,   478,    99,     6,     6,    23,     6,
     111,    39,   486,     6,     6,     6,     6,   154,     6,     6,
       6,     6,     6,     6,     4,    14,    33,     6,    96,     6,
     100,     6,   506,     6,     6,     6,     6,   511,   283,     6,
     285,    19,    71,     6,    74,     6,     4,     6,   522,    30,
     102,    53,     4,     4,    79,    21,    90,    22,     6,   533,
       6,   306,   307,     4,   309,   310,    92,     4,   313,   314,
     544,   316,   317,     4,   495,    73,    38,   104,     4,   553,
     325,   555,     4,     4,   107,    88,   110,   561,   110,     6,
      32,    55,    36,    62,   568,     6,   570,   117,   115,   520,
     119,     6,     6,   108,    80,   121,    66,   108,    43,    77,
      34,     6,    94,   108,     6,    51,   127,     6,     6,     6,
       6,   542,   116,   117,   118,   119,   120,   121,   122,   123,
     124,   125,   126,   127,   128,   129,   130,   131,   132,   133,
     134,   135,   136,   137,   138,   139,   140,   141,   142,    98,
      95,   108,     6,    78,     6,   129,    83,     6,    18,   114,
      70,     4,     6,   113,     6,     6,     6,   123,    52,   414,
      50,    85,    45,   126,     6,    20,    72,   131,   116,     6,
       4,     6,   125,    54,    87,     6,    47,   105,   118,     6,
     106,    76,   120,    94,    42,    95,     6,     6,    57,    93,
       6,    59,   122,    29,     6,   128,    82,     6,     6,     6,
      49,   104,   103,   112,    84,   130,    44,    92,   124,    91,
      86,   102,   467,   468,    46,    94,    56,   101,    90,    58,
      48,   457,    28,   100,   446,   175,   368,   576,   331,   564,
     527,   154,   333,   548,   502,   416,   165,   375,   418,    -1,
     162,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,   296,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,   234,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,   235
};

  /* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
     symbol of state STATE-NUM.  */
static const yytype_uint16 yystos[] =
{
       0,     9,   189,   198,    13,     0,    25,    10,    11,   190,
       5,     7,     8,   191,     6,   206,    10,    10,    69,   247,
      24,     7,     6,    27,   208,   248,   133,   136,   138,   139,
     140,   141,   142,   146,   148,   149,   150,   151,   163,   164,
     165,   166,   167,   168,   169,   170,   171,   172,   173,   174,
     175,   176,   177,   178,   207,   325,   326,   327,   328,   329,
     330,   331,   334,   335,   336,   337,   338,   344,   345,   346,
     347,   348,   349,   350,   351,   352,   353,   354,   355,   356,
     357,   358,   359,     4,   194,     6,     6,     6,     6,     6,
       6,     6,     6,     6,     6,     6,     6,     6,     6,     6,
       6,     6,     6,     6,     6,     6,     6,     6,     6,     6,
       6,     6,     6,    26,    68,   247,   247,   247,   247,   247,
     247,   247,   247,   247,   247,   247,   247,   247,   247,   247,
     247,   247,   247,   247,   247,   247,   247,   247,   247,   247,
     247,   247,   247,    31,   211,   211,   211,   211,   211,   211,
     211,   211,   211,   211,   211,   211,   211,   211,   211,   211,
     211,   211,   211,   211,   211,   211,   211,   211,   211,   211,
     211,   211,     6,    15,   200,   238,    89,   270,    37,   217,
      63,   243,    97,   284,    67,   245,   245,    81,   262,   109,
     297,    75,   240,   254,    95,   278,   101,   287,   109,   299,
     287,   109,   301,    35,   215,   240,    91,   273,    93,   276,
     109,   303,   103,   290,   105,   293,    99,   285,   212,     6,
     199,   200,     6,   239,     6,     6,     6,   154,   260,   261,
     339,    23,   205,     6,   111,   241,   242,   306,    39,   219,
       6,     6,     6,   253,   254,     6,     6,   259,     6,     6,
       6,   157,   158,   272,   340,   341,   160,   161,   275,   342,
     343,     6,   179,   180,   289,   360,   361,   182,   183,   292,
     362,   363,     6,     4,   195,   247,    14,   271,    33,   214,
     218,   244,   247,     6,    96,     6,   257,    19,   201,   246,
     260,   261,   306,    71,   249,   261,   263,   298,   247,    74,
     279,   247,   100,   300,   302,   216,     6,     6,    90,     6,
       6,    92,   304,     6,     6,   102,     6,     6,   104,   286,
      30,    53,   230,     4,   192,     6,   213,     4,   193,     4,
     196,    79,   258,   247,   247,    22,     6,    21,   203,   192,
     110,   110,     6,    38,   196,     4,    73,   251,     4,   117,
     310,     4,     4,   192,   247,   247,   247,   247,     4,   247,
     247,   247,   247,     4,     6,    55,   232,    88,   247,    32,
      36,    62,   257,   115,   309,   258,   119,   312,   202,     6,
     107,   295,    66,   250,    80,   108,     6,    77,   255,    94,
       6,   121,   314,   108,   108,    34,    95,   280,    43,   220,
     280,   220,   108,   280,   220,   280,   220,    98,   231,     6,
      51,   229,   230,    78,     6,   305,   127,   320,   309,     6,
     123,   316,   193,   204,     6,   195,   252,     6,   311,     6,
     129,   321,     6,     6,   195,   233,   144,   145,   228,   332,
     333,    83,   264,   247,   114,   305,   320,   313,     6,   131,
     323,    18,   193,   296,    70,     4,   197,   256,   193,   315,
       6,   113,   307,   281,   221,    52,   193,     6,     6,    50,
       6,    85,   266,    45,   222,   126,   215,   193,   317,     6,
      20,   193,    72,   197,   116,   193,   322,     6,   125,   318,
     193,     4,    54,   247,   247,   265,     6,    87,   268,     6,
      47,   224,   105,   294,   118,   193,   324,   106,    76,   120,
     193,   308,     6,    94,    42,    95,   282,    57,   234,   192,
     267,     6,   223,     6,    59,   236,   292,    93,   277,   122,
     193,   128,   193,   319,     6,    29,   209,     6,    49,   226,
      82,   192,   269,   193,   225,     6,   104,   275,   103,   291,
     130,   112,   193,   283,     6,   235,     6,    84,   192,    44,
     193,   237,    92,   289,    91,   274,   124,   193,   210,   193,
     227,    86,    46,   193,   102,   272,   101,   288,    94,   193,
      56,   193,    58,    90,   259,    28,    48,   100
};

  /* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint16 yyr1[] =
{
       0,   188,   189,   190,   191,   192,   193,   194,   195,   196,
     197,   198,   198,   199,   200,   201,   202,   201,   204,   203,
     205,   205,   206,   207,   207,   207,   207,   207,   207,   207,
     207,   207,   207,   207,   207,   207,   207,   207,   207,   207,
     207,   207,   207,   207,   207,   207,   207,   207,   207,   207,
     207,   208,   209,   210,   209,   212,   211,   213,   214,   216,
     215,   218,   217,   219,   221,   220,   223,   222,   225,   224,
     226,   227,   226,   228,   228,   229,   231,   230,   233,   232,
     234,   235,   234,   237,   236,   238,   238,   239,   239,   240,
     240,   241,   241,   242,   244,   243,   246,   245,   247,   248,
     247,   250,   249,   252,   251,   253,   254,   256,   255,   257,
     258,   259,   260,   261,   261,   263,   262,   265,   264,   267,
     266,   269,   268,   271,   270,   272,   272,   273,   274,   274,
     275,   275,   276,   277,   277,   279,   278,   281,   280,   282,
     283,   282,   284,   284,   286,   285,   287,   288,   288,   289,
     289,   290,   291,   291,   292,   292,   293,   294,   294,   296,
     295,   298,   297,   300,   299,   302,   301,   304,   303,   305,
     306,   306,   307,   308,   307,   309,   310,   311,   310,   313,
     312,   314,   315,   314,   317,   316,   318,   319,   318,   320,
     321,   322,   321,   324,   323,   325,   326,   327,   328,   329,
     330,   331,   332,   333,   334,   335,   336,   337,   338,   339,
     340,   341,   342,   343,   344,   345,   346,   347,   348,   349,
     350,   351,   352,   353,   354,   355,   356,   357,   358,   359,
     360,   361,   362,   363
};

  /* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     5,     2,     2,     1,     1,     1,     1,     1,
       1,     4,     6,     5,     3,     0,     0,     5,     0,     5,
       0,     3,     3,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     3,     0,     0,     5,     0,     5,     6,     3,     0,
       5,     0,     5,     3,     0,     5,     0,     5,     0,     5,
       0,     0,     5,     1,     1,     3,     0,     5,     0,     5,
       0,     0,     5,     0,     5,     1,     2,     0,     2,     2,
       1,     2,     1,     1,     0,     5,     0,     5,     0,     0,
       5,     0,     5,     0,     5,     4,     3,     0,     5,     5,
       3,     7,     5,     1,     1,     0,     5,     0,     5,     0,
       5,     0,     5,     0,     5,     1,     1,     3,     0,     3,
       1,     1,     3,     0,     3,     0,     5,     0,     5,     0,
       0,     5,     0,     3,     0,     5,     3,     0,     3,     1,
       1,     3,     0,     3,     1,     1,     3,     0,     3,     0,
       5,     0,     5,     0,     5,     0,     5,     0,     5,     5,
       3,     3,     0,     0,     5,     3,     0,     0,     5,     0,
       5,     0,     0,     5,     0,     5,     0,     0,     5,     3,
       0,     0,     5,     0,     5,     5,     4,     6,     5,     4,
       4,     4,     5,     5,     5,     9,     7,     6,     4,    12,
       4,     4,     4,     4,     5,     5,     5,     5,     5,     5,
       5,     5,     5,     5,     5,     5,     5,     5,     5,     5,
       4,     4,     4,     4
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

    {(yyval.CRCLCommandInstanceFileVal) = new CRCLCommandInstanceFile((yyvsp[-4].XmlVersionVal), (yyvsp[-2].XmlHeaderForCRCLCommandInstanceVal), (yyvsp[-1].CRCLCommandInstanceTypeVal));
	   CRCLCommandInstanceTree = (yyval.CRCLCommandInstanceFileVal);
	   if (XmlIDREF::idMissing())
	     yyerror("xs:ID missing for xs:IDREF");
	  }

    break;

  case 3:

    {(yyval.XmlHeaderForCRCLCommandInstanceVal) = new XmlHeaderForCRCLCommandInstance((yyvsp[0].SchemaLocationVal));}

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

    {(yyval.XmlDecimalVal) = new XmlDecimal((yyvsp[0].sVal));
	   if ((yyval.XmlDecimalVal)->bad)
	     yyerror("bad XmlDecimal");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 7:

    {(yyval.XmlIDVal) = new XmlID((yyvsp[0].sVal));
	   if ((yyval.XmlIDVal)->bad)
	     yyerror("bad XmlID");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 8:

    {(yyval.XmlPositiveIntegerVal) = new XmlPositiveInteger((yyvsp[0].sVal));
	   if ((yyval.XmlPositiveIntegerVal)->bad)
	     yyerror("bad XmlPositiveInteger");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 9:

    {(yyval.XmlStringVal) = new XmlString((yyvsp[0].sVal));
	   if ((yyval.XmlStringVal)->bad)
	     yyerror("bad XmlString");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 10:

    {(yyval.XmlTokenVal) = new XmlToken((yyvsp[0].sVal));
	   if ((yyval.XmlTokenVal)->bad)
	     yyerror("bad XmlToken");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 11:

    {(yyval.XmlVersionVal) = new XmlVersion(false);
	   if (strcmp((yyvsp[-1].sVal), "1.0"))
	     yyerror("version number must be 1.0");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 12:

    {(yyval.XmlVersionVal) = new XmlVersion(true);
	   if (strcmp((yyvsp[-3].sVal), "1.0"))
	     yyerror("version number must be 1.0");
	   else if ((strcmp((yyvsp[-1].sVal), "UTF-8")) && (strcmp((yyvsp[-1].sVal), "utf-8")))
	     yyerror("encoding must be UTF-8");
	   free((yyvsp[-3].sVal));
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 13:

    {(yyval.ActuateJointTypeVal) = new ActuateJointType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlPositiveIntegerVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].JointDetailsTypeVal));}

    break;

  case 14:

    {(yyval.ActuateJointTypeVal) = (yyvsp[-1].ActuateJointTypeVal);}

    break;

  case 15:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 16:

    {yyReadData = 1;}

    break;

  case 17:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 18:

    {yyReadData = 1;}

    break;

  case 19:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 20:

    {(yyval.PointTypeVal) = 0;}

    break;

  case 21:

    {(yyval.PointTypeVal) = (yyvsp[-1].PointTypeVal);}

    break;

  case 22:

    {(yyval.CRCLCommandInstanceTypeVal) = new CRCLCommandInstanceType((yyvsp[-1].XmlIDVal), (yyvsp[0].CRCLCommandTypeVal));}

    break;

  case 23:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].ActuateJointsTypeVal);}

    break;

  case 24:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].CloseToolChangerTypeVal);}

    break;

  case 25:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].ConfigureJointReportsTypeVal);}

    break;

  case 26:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].DwellTypeVal);}

    break;

  case 27:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].EndCanonTypeVal);}

    break;

  case 28:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].GetStatusTypeVal);}

    break;

  case 29:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].InitCanonTypeVal);}

    break;

  case 30:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].MessageTypeVal);}

    break;

  case 31:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].MoveScrewTypeVal);}

    break;

  case 32:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].MoveThroughToTypeVal);}

    break;

  case 33:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].MoveToTypeVal);}

    break;

  case 34:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].OpenToolChangerTypeVal);}

    break;

  case 35:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].RunProgramTypeVal);}

    break;

  case 36:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetAngleUnitsTypeVal);}

    break;

  case 37:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetEndEffectorParametersTypeVal);}

    break;

  case 38:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetEndEffectorTypeVal);}

    break;

  case 39:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetEndPoseToleranceTypeVal);}

    break;

  case 40:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetForceUnitsTypeVal);}

    break;

  case 41:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetIntermediatePoseToleranceTypeVal);}

    break;

  case 42:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetLengthUnitsTypeVal);}

    break;

  case 43:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetMotionCoordinationTypeVal);}

    break;

  case 44:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetRobotParametersTypeVal);}

    break;

  case 45:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetRotAccelTypeVal);}

    break;

  case 46:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetRotSpeedTypeVal);}

    break;

  case 47:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetTorqueUnitsTypeVal);}

    break;

  case 48:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetTransAccelTypeVal);}

    break;

  case 49:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetTransSpeedTypeVal);}

    break;

  case 50:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].StopMotionTypeVal);}

    break;

  case 51:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[-1].CRCLCommandTypeVal);}

    break;

  case 52:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 53:

    {yyReadData = 1;}

    break;

  case 54:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 55:

    {yyReadData = 1;}

    break;

  case 56:

    {(yyval.XmlPositiveIntegerVal) = (yyvsp[-1].XmlPositiveIntegerVal);}

    break;

  case 57:

    {(yyval.ConfigureJointReportTypeVal) = new ConfigureJointReportType((yyvsp[-4].XmlIDVal), (yyvsp[-3].XmlPositiveIntegerVal), (yyvsp[-2].XmlBooleanVal), (yyvsp[-1].XmlBooleanVal), (yyvsp[0].XmlBooleanVal));}

    break;

  case 58:

    {(yyval.ConfigureJointReportTypeVal) = (yyvsp[-1].ConfigureJointReportTypeVal);}

    break;

  case 59:

    {yyReadData = 1;}

    break;

  case 60:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

    break;

  case 61:

    {yyReadData = 1;}

    break;

  case 62:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 63:

    {(yyval.PoseTypeVal) = (yyvsp[-1].PoseTypeVal);}

    break;

  case 64:

    {yyReadData = 1;}

    break;

  case 65:

    {(yyval.FractionTypeVal) = new FractionType((yyvsp[-1].sVal));
	   if ((yyval.FractionTypeVal)->bad)
	     yyerror("bad Fraction value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 66:

    {yyReadData = 1;}

    break;

  case 67:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 68:

    {yyReadData = 1;}

    break;

  case 69:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 70:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 71:

    {yyReadData = 1;}

    break;

  case 72:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 73:

    {(yyval.JointDetailsTypeVal) = (yyvsp[0].JointForceTorqueTypeVal);}

    break;

  case 74:

    {(yyval.JointDetailsTypeVal) = (yyvsp[0].JointSpeedAccelTypeVal);}

    break;

  case 75:

    {(yyval.JointDetailsTypeVal) = (yyvsp[-1].JointDetailsTypeVal);}

    break;

  case 76:

    {yyReadData = 1;}

    break;

  case 77:

    {(yyval.XmlPositiveIntegerVal) = (yyvsp[-1].XmlPositiveIntegerVal);}

    break;

  case 78:

    {yyReadData = 1;}

    break;

  case 79:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 80:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 81:

    {yyReadData = 1;}

    break;

  case 82:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 83:

    {yyReadData = 1;}

    break;

  case 84:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 85:

    {(yyval.ListActuateJointTypeVal) = new std::list<ActuateJointType *>;
	   (yyval.ListActuateJointTypeVal)->push_back((yyvsp[0].ActuateJointTypeVal));}

    break;

  case 86:

    {(yyval.ListActuateJointTypeVal) = (yyvsp[-1].ListActuateJointTypeVal);
	   (yyval.ListActuateJointTypeVal)->push_back((yyvsp[0].ActuateJointTypeVal));}

    break;

  case 87:

    {(yyval.ListConfigureJointReportTypeVal) = new std::list<ConfigureJointReportType *>;}

    break;

  case 88:

    {(yyval.ListConfigureJointReportTypeVal) = (yyvsp[-1].ListConfigureJointReportTypeVal);
	   (yyval.ListConfigureJointReportTypeVal)->push_back((yyvsp[0].ConfigureJointReportTypeVal));}

    break;

  case 89:

    {(yyval.ListParameterSettingTypeVal) = (yyvsp[-1].ListParameterSettingTypeVal);
	   (yyval.ListParameterSettingTypeVal)->push_back((yyvsp[0].ParameterSettingTypeVal));}

    break;

  case 90:

    {(yyval.ListParameterSettingTypeVal) = new std::list<ParameterSettingType *>;
	   (yyval.ListParameterSettingTypeVal)->push_back((yyvsp[0].ParameterSettingTypeVal));}

    break;

  case 91:

    {(yyval.ListPoseTypeVal) = (yyvsp[-1].ListPoseTypeVal);
	   (yyval.ListPoseTypeVal)->push_back((yyvsp[0].PoseTypeVal));}

    break;

  case 92:

    {(yyval.ListPoseTypeVal) = new std::list<PoseType *>;
	   (yyval.ListPoseTypeVal)->push_back((yyvsp[0].PoseTypeVal));}

    break;

  case 93:

    {(yyval.ListPoseTypeVal) = (yyvsp[0].ListPoseTypeVal);
	   if ((yyvsp[0].ListPoseTypeVal)->size() < 2)
	     yyerror("must be at least 2 Waypoints");
	  }

    break;

  case 94:

    {yyReadData = 1;}

    break;

  case 95:

    {(yyval.XmlStringVal) = (yyvsp[-1].XmlStringVal);}

    break;

  case 96:

    {yyReadData = 1;}

    break;

  case 97:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

    break;

  case 98:

    {(yyval.XmlIDVal) = 0;}

    break;

  case 99:

    {yyReadData = 1;}

    break;

  case 100:

    {(yyval.XmlIDVal) = (yyvsp[-1].XmlIDVal);}

    break;

  case 101:

    {yyReadData = 1;}

    break;

  case 102:

    {(yyval.XmlPositiveIntegerVal) = (yyvsp[-1].XmlPositiveIntegerVal);}

    break;

  case 103:

    {yyReadData = 1;}

    break;

  case 104:

    {(yyval.XmlTokenVal) = (yyvsp[-1].XmlTokenVal);}

    break;

  case 105:

    {(yyval.ParameterSettingTypeVal) = new ParameterSettingType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlTokenVal), (yyvsp[0].XmlTokenVal));}

    break;

  case 106:

    {(yyval.ParameterSettingTypeVal) = (yyvsp[-1].ParameterSettingTypeVal);}

    break;

  case 107:

    {yyReadData = 1;}

    break;

  case 108:

    {(yyval.XmlTokenVal) = (yyvsp[-1].XmlTokenVal);}

    break;

  case 109:

    {(yyval.PointTypeVal) = new PointType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));}

    break;

  case 110:

    {(yyval.PointTypeVal) = (yyvsp[-1].PointTypeVal);}

    break;

  case 111:

    {(yyval.PoseToleranceTypeVal) = new PoseToleranceType((yyvsp[-5].XmlIDVal), (yyvsp[-4].XmlDecimalVal), (yyvsp[-3].XmlDecimalVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));}

    break;

  case 112:

    {(yyval.PoseTypeVal) = new PoseType((yyvsp[-3].XmlIDVal), (yyvsp[-2].PointTypeVal), (yyvsp[-1].VectorTypeVal), (yyvsp[0].VectorTypeVal));}

    break;

  case 113:

    {(yyval.PoseTypeVal) = (yyvsp[0].PoseTypeVal);}

    break;

  case 114:

    {(yyval.PoseTypeVal) = (yyvsp[0].PoseAndSetTypeVal);}

    break;

  case 115:

    {yyReadData = 1;}

    break;

  case 116:

    {(yyval.XmlStringVal) = (yyvsp[-1].XmlStringVal);}

    break;

  case 117:

    {yyReadData = 1;}

    break;

  case 118:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

    break;

  case 119:

    {yyReadData = 1;}

    break;

  case 120:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

    break;

  case 121:

    {yyReadData = 1;}

    break;

  case 122:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

    break;

  case 123:

    {yyReadData = 1;}

    break;

  case 124:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

    break;

  case 125:

    {(yyval.RotAccelTypeVal) = (yyvsp[0].RotAccelAbsoluteTypeVal);}

    break;

  case 126:

    {(yyval.RotAccelTypeVal) = (yyvsp[0].RotAccelRelativeTypeVal);}

    break;

  case 127:

    {(yyval.RotAccelTypeVal) = (yyvsp[-1].RotAccelTypeVal);}

    break;

  case 128:

    {(yyval.RotAccelTypeVal) = 0;}

    break;

  case 129:

    {(yyval.RotAccelTypeVal) = (yyvsp[-1].RotAccelTypeVal);}

    break;

  case 130:

    {(yyval.RotSpeedTypeVal) = (yyvsp[0].RotSpeedAbsoluteTypeVal);}

    break;

  case 131:

    {(yyval.RotSpeedTypeVal) = (yyvsp[0].RotSpeedRelativeTypeVal);}

    break;

  case 132:

    {(yyval.RotSpeedTypeVal) = (yyvsp[-1].RotSpeedTypeVal);}

    break;

  case 133:

    {(yyval.RotSpeedTypeVal) = 0;}

    break;

  case 134:

    {(yyval.RotSpeedTypeVal) = (yyvsp[-1].RotSpeedTypeVal);}

    break;

  case 135:

    {yyReadData = 1;}

    break;

  case 136:

    {(yyval.FractionTypeVal) = new FractionType((yyvsp[-1].sVal));
	   if ((yyval.FractionTypeVal)->bad)
	     yyerror("bad Setting value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 137:

    {yyReadData = 1;}

    break;

  case 138:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 139:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 140:

    {yyReadData = 1;}

    break;

  case 141:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 142:

    {(yyval.PoseTypeVal) = 0;}

    break;

  case 143:

    {(yyval.PoseTypeVal) = (yyvsp[-1].PoseTypeVal);}

    break;

  case 144:

    {yyReadData = 1;}

    break;

  case 145:

    {(yyval.StopConditionEnumTypeVal) = new StopConditionEnumType((yyvsp[-1].sVal));
	   if ((yyval.StopConditionEnumTypeVal)->bad)
	     yyerror("bad StopCondition value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 146:

    {(yyval.PoseToleranceTypeVal) = (yyvsp[-1].PoseToleranceTypeVal);}

    break;

  case 147:

    {(yyval.PoseToleranceTypeVal) = 0;}

    break;

  case 148:

    {(yyval.PoseToleranceTypeVal) = (yyvsp[-1].PoseToleranceTypeVal);}

    break;

  case 149:

    {(yyval.TransAccelTypeVal) = (yyvsp[0].TransAccelAbsoluteTypeVal);}

    break;

  case 150:

    {(yyval.TransAccelTypeVal) = (yyvsp[0].TransAccelRelativeTypeVal);}

    break;

  case 151:

    {(yyval.TransAccelTypeVal) = (yyvsp[-1].TransAccelTypeVal);}

    break;

  case 152:

    {(yyval.TransAccelTypeVal) = 0;}

    break;

  case 153:

    {(yyval.TransAccelTypeVal) = (yyvsp[-1].TransAccelTypeVal);}

    break;

  case 154:

    {(yyval.TransSpeedTypeVal) = (yyvsp[0].TransSpeedAbsoluteTypeVal);}

    break;

  case 155:

    {(yyval.TransSpeedTypeVal) = (yyvsp[0].TransSpeedRelativeTypeVal);}

    break;

  case 156:

    {(yyval.TransSpeedTypeVal) = (yyvsp[-1].TransSpeedTypeVal);}

    break;

  case 157:

    {(yyval.TransSpeedTypeVal) = 0;}

    break;

  case 158:

    {(yyval.TransSpeedTypeVal) = (yyvsp[-1].TransSpeedTypeVal);}

    break;

  case 159:

    {yyReadData = 1;}

    break;

  case 160:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 161:

    {yyReadData = 1;}

    break;

  case 162:

    {(yyval.AngleUnitEnumTypeVal) = new AngleUnitEnumType((yyvsp[-1].sVal));
	   if ((yyval.AngleUnitEnumTypeVal)->bad)
	     yyerror("bad UnitName value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 163:

    {yyReadData = 1;}

    break;

  case 164:

    {(yyval.ForceUnitEnumTypeVal) = new ForceUnitEnumType((yyvsp[-1].sVal));
	   if ((yyval.ForceUnitEnumTypeVal)->bad)
	     yyerror("bad UnitName value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 165:

    {yyReadData = 1;}

    break;

  case 166:

    {(yyval.LengthUnitEnumTypeVal) = new LengthUnitEnumType((yyvsp[-1].sVal));
	   if ((yyval.LengthUnitEnumTypeVal)->bad)
	     yyerror("bad UnitName value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 167:

    {yyReadData = 1;}

    break;

  case 168:

    {(yyval.TorqueUnitEnumTypeVal) = new TorqueUnitEnumType((yyvsp[-1].sVal));
	   if ((yyval.TorqueUnitEnumTypeVal)->bad)
	     yyerror("bad UnitName value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 169:

    {(yyval.VectorTypeVal) = new VectorType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));}

    break;

  case 170:

    {(yyval.PoseTypeVal) = (yyvsp[-1].PoseTypeVal);}

    break;

  case 171:

    {(yyval.PoseTypeVal) = (yyvsp[-1].PoseTypeVal);}

    break;

  case 172:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 173:

    {yyReadData = 1;}

    break;

  case 174:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 175:

    {(yyval.VectorTypeVal) = (yyvsp[-1].VectorTypeVal);}

    break;

  case 176:

    {(yyval.XmlDecimalVal) = 0;}

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

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 181:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 182:

    {yyReadData = 1;}

    break;

  case 183:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 184:

    {yyReadData = 1;}

    break;

  case 185:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 186:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 187:

    {yyReadData = 1;}

    break;

  case 188:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 189:

    {(yyval.VectorTypeVal) = (yyvsp[-1].VectorTypeVal);}

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

    {yyReadData = 1;}

    break;

  case 194:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 195:

    {(yyval.ActuateJointsTypeVal) = new ActuateJointsType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].ListActuateJointTypeVal));
	   (yyval.ActuateJointsTypeVal)->printTypp = true;
	  }

    break;

  case 196:

    {(yyval.CloseToolChangerTypeVal) = new CloseToolChangerType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlPositiveIntegerVal));
	   (yyval.CloseToolChangerTypeVal)->printTypp = true;
	  }

    break;

  case 197:

    {(yyval.ConfigureJointReportsTypeVal) = new ConfigureJointReportsType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlPositiveIntegerVal), (yyvsp[-1].XmlBooleanVal), (yyvsp[0].ListConfigureJointReportTypeVal));
	   (yyval.ConfigureJointReportsTypeVal)->printTypp = true;
	  }

    break;

  case 198:

    {(yyval.DwellTypeVal) = new DwellType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.DwellTypeVal)->printTypp = true;
	  }

    break;

  case 199:

    {(yyval.EndCanonTypeVal) = new EndCanonType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlPositiveIntegerVal));
	   (yyval.EndCanonTypeVal)->printTypp = true;
	  }

    break;

  case 200:

    {(yyval.GetStatusTypeVal) = new GetStatusType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlPositiveIntegerVal));
	   (yyval.GetStatusTypeVal)->printTypp = true;
	  }

    break;

  case 201:

    {(yyval.InitCanonTypeVal) = new InitCanonType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlPositiveIntegerVal));
	   (yyval.InitCanonTypeVal)->printTypp = true;
	  }

    break;

  case 202:

    {(yyval.JointForceTorqueTypeVal) = new JointForceTorqueType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.JointForceTorqueTypeVal)->printTypp = true;
	  }

    break;

  case 203:

    {(yyval.JointSpeedAccelTypeVal) = new JointSpeedAccelType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.JointSpeedAccelTypeVal)->printTypp = true;
	  }

    break;

  case 204:

    {(yyval.MessageTypeVal) = new MessageType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].XmlStringVal));
	   (yyval.MessageTypeVal)->printTypp = true;
	  }

    break;

  case 205:

    {(yyval.MoveScrewTypeVal) = new MoveScrewType((yyvsp[-6].XmlIDVal), (yyvsp[-5].XmlPositiveIntegerVal), (yyvsp[-4].PoseTypeVal), (yyvsp[-3].PointTypeVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.MoveScrewTypeVal)->printTypp = true;
	  }

    break;

  case 206:

    {(yyval.MoveThroughToTypeVal) = new MoveThroughToType((yyvsp[-4].XmlIDVal), (yyvsp[-3].XmlPositiveIntegerVal), (yyvsp[-2].XmlBooleanVal), (yyvsp[-1].ListPoseTypeVal), (yyvsp[0].XmlPositiveIntegerVal));
	   (yyval.MoveThroughToTypeVal)->printTypp = true;
	  }

    break;

  case 207:

    {(yyval.MoveToTypeVal) = new MoveToType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlPositiveIntegerVal), (yyvsp[-1].XmlBooleanVal), (yyvsp[0].PoseTypeVal));
	   (yyval.MoveToTypeVal)->printTypp = true;
	  }

    break;

  case 208:

    {(yyval.OpenToolChangerTypeVal) = new OpenToolChangerType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlPositiveIntegerVal));
	   (yyval.OpenToolChangerTypeVal)->printTypp = true;
	  }

    break;

  case 209:

    {(yyval.PoseAndSetTypeVal) = new PoseAndSetType((yyvsp[-9].XmlIDVal), (yyvsp[-8].PointTypeVal), (yyvsp[-7].VectorTypeVal), (yyvsp[-6].VectorTypeVal), (yyvsp[-5].XmlBooleanVal), (yyvsp[-4].TransSpeedTypeVal), (yyvsp[-3].RotSpeedTypeVal), (yyvsp[-2].TransAccelTypeVal), (yyvsp[-1].RotAccelTypeVal), (yyvsp[0].PoseToleranceTypeVal));
	   (yyval.PoseAndSetTypeVal)->printTypp = true;
	  }

    break;

  case 210:

    {(yyval.RotAccelAbsoluteTypeVal) = new RotAccelAbsoluteType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.RotAccelAbsoluteTypeVal)->printTypp = true;
	  }

    break;

  case 211:

    {(yyval.RotAccelRelativeTypeVal) = new RotAccelRelativeType((yyvsp[-1].XmlIDVal), (yyvsp[0].FractionTypeVal));
	   (yyval.RotAccelRelativeTypeVal)->printTypp = true;
	  }

    break;

  case 212:

    {(yyval.RotSpeedAbsoluteTypeVal) = new RotSpeedAbsoluteType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.RotSpeedAbsoluteTypeVal)->printTypp = true;
	  }

    break;

  case 213:

    {(yyval.RotSpeedRelativeTypeVal) = new RotSpeedRelativeType((yyvsp[-1].XmlIDVal), (yyvsp[0].FractionTypeVal));
	   (yyval.RotSpeedRelativeTypeVal)->printTypp = true;
	  }

    break;

  case 214:

    {(yyval.RunProgramTypeVal) = new RunProgramType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].XmlStringVal));
	   (yyval.RunProgramTypeVal)->printTypp = true;
	  }

    break;

  case 215:

    {(yyval.SetAngleUnitsTypeVal) = new SetAngleUnitsType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].AngleUnitEnumTypeVal));
	   (yyval.SetAngleUnitsTypeVal)->printTypp = true;
	  }

    break;

  case 216:

    {(yyval.SetEndEffectorParametersTypeVal) = new SetEndEffectorParametersType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].ListParameterSettingTypeVal));
	   (yyval.SetEndEffectorParametersTypeVal)->printTypp = true;
	  }

    break;

  case 217:

    {(yyval.SetEndEffectorTypeVal) = new SetEndEffectorType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].FractionTypeVal));
	   (yyval.SetEndEffectorTypeVal)->printTypp = true;
	  }

    break;

  case 218:

    {(yyval.SetEndPoseToleranceTypeVal) = new SetEndPoseToleranceType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].PoseToleranceTypeVal));
	   (yyval.SetEndPoseToleranceTypeVal)->printTypp = true;
	  }

    break;

  case 219:

    {(yyval.SetForceUnitsTypeVal) = new SetForceUnitsType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].ForceUnitEnumTypeVal));
	   (yyval.SetForceUnitsTypeVal)->printTypp = true;
	  }

    break;

  case 220:

    {(yyval.SetIntermediatePoseToleranceTypeVal) = new SetIntermediatePoseToleranceType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].PoseToleranceTypeVal));
	   (yyval.SetIntermediatePoseToleranceTypeVal)->printTypp = true;
	  }

    break;

  case 221:

    {(yyval.SetLengthUnitsTypeVal) = new SetLengthUnitsType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].LengthUnitEnumTypeVal));
	   (yyval.SetLengthUnitsTypeVal)->printTypp = true;
	  }

    break;

  case 222:

    {(yyval.SetMotionCoordinationTypeVal) = new SetMotionCoordinationType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].XmlBooleanVal));
	   (yyval.SetMotionCoordinationTypeVal)->printTypp = true;
	  }

    break;

  case 223:

    {(yyval.SetRobotParametersTypeVal) = new SetRobotParametersType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].ListParameterSettingTypeVal));
	   (yyval.SetRobotParametersTypeVal)->printTypp = true;
	  }

    break;

  case 224:

    {(yyval.SetRotAccelTypeVal) = new SetRotAccelType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].RotAccelTypeVal));
	   (yyval.SetRotAccelTypeVal)->printTypp = true;
	  }

    break;

  case 225:

    {(yyval.SetRotSpeedTypeVal) = new SetRotSpeedType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].RotSpeedTypeVal));
	   (yyval.SetRotSpeedTypeVal)->printTypp = true;
	  }

    break;

  case 226:

    {(yyval.SetTorqueUnitsTypeVal) = new SetTorqueUnitsType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].TorqueUnitEnumTypeVal));
	   (yyval.SetTorqueUnitsTypeVal)->printTypp = true;
	  }

    break;

  case 227:

    {(yyval.SetTransAccelTypeVal) = new SetTransAccelType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].TransAccelTypeVal));
	   (yyval.SetTransAccelTypeVal)->printTypp = true;
	  }

    break;

  case 228:

    {(yyval.SetTransSpeedTypeVal) = new SetTransSpeedType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].TransSpeedTypeVal));
	   (yyval.SetTransSpeedTypeVal)->printTypp = true;
	  }

    break;

  case 229:

    {(yyval.StopMotionTypeVal) = new StopMotionType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].StopConditionEnumTypeVal));
	   (yyval.StopMotionTypeVal)->printTypp = true;
	  }

    break;

  case 230:

    {(yyval.TransAccelAbsoluteTypeVal) = new TransAccelAbsoluteType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.TransAccelAbsoluteTypeVal)->printTypp = true;
	  }

    break;

  case 231:

    {(yyval.TransAccelRelativeTypeVal) = new TransAccelRelativeType((yyvsp[-1].XmlIDVal), (yyvsp[0].FractionTypeVal));
	   (yyval.TransAccelRelativeTypeVal)->printTypp = true;
	  }

    break;

  case 232:

    {(yyval.TransSpeedAbsoluteTypeVal) = new TransSpeedAbsoluteType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.TransSpeedAbsoluteTypeVal)->printTypp = true;
	  }

    break;

  case 233:

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
