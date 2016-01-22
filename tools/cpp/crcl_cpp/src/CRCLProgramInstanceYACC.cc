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
#include "owlCRCLProgramInstanceClasses.hh"
#else
#include "crcl_cpp/CRCLProgramInstanceClasses.hh"
#endif

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
#define YYLAST   447

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  192
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  179
/* YYNRULES -- Number of rules.  */
#define YYNRULES  235
/* YYNSTATES -- Number of states.  */
#define YYNSTATES  593

/* YYTRANSLATE[YYX] -- Symbol number corresponding to YYX as returned
   by yylex, with out-of-bounds checking.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   446

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
     185,   186,   187,   188,   189,   190,   191
};

#if YYDEBUG
  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,   444,   444,   454,   459,   466,   475,   484,   493,   502,
     511,   520,   526,   539,   545,   556,   557,   557,   563,   563,
     570,   571,   576,   583,   584,   584,   590,   590,   596,   603,
     609,   609,   615,   615,   621,   626,   631,   641,   641,   650,
     650,   655,   660,   665,   665,   671,   672,   672,   678,   680,
     685,   690,   690,   696,   696,   703,   704,   704,   710,   710,
     720,   723,   731,   732,   740,   741,   748,   752,   758,   761,
     767,   775,   775,   780,   782,   784,   786,   788,   790,   792,
     794,   796,   798,   800,   802,   804,   806,   808,   810,   812,
     814,   816,   818,   820,   822,   824,   826,   828,   830,   835,
     845,   845,   852,   853,   853,   858,   858,   864,   864,   870,
     876,   881,   881,   887,   893,   898,   905,   911,   913,   918,
     918,   924,   924,   930,   930,   936,   936,   942,   942,   947,
     949,   954,   960,   961,   966,   968,   973,   979,   980,   985,
     985,   994,   994,  1000,  1001,  1001,  1007,  1008,  1013,  1013,
    1023,  1029,  1030,  1035,  1037,  1042,  1048,  1049,  1054,  1056,
    1061,  1067,  1068,  1073,  1073,  1078,  1078,  1087,  1087,  1096,
    1096,  1105,  1105,  1114,  1120,  1122,  1128,  1129,  1129,  1135,
    1141,  1142,  1142,  1148,  1148,  1154,  1155,  1155,  1161,  1161,
    1167,  1168,  1168,  1174,  1180,  1181,  1181,  1187,  1187,  1192,
    1201,  1209,  1218,  1226,  1234,  1242,  1250,  1258,  1268,  1278,
    1287,  1295,  1306,  1314,  1322,  1330,  1338,  1346,  1354,  1363,
    1371,  1379,  1387,  1395,  1403,  1411,  1420,  1428,  1436,  1444,
    1452,  1460,  1469,  1477,  1485,  1493
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
  "COORDINATEDSTART", "DWELLTIMEEND", "DWELLTIMESTART", "ENDCANONEND",
  "ENDCANONSTART", "ENDPOSITIONEND", "ENDPOSITIONSTART", "FORCEEND",
  "FORCESTART", "FRACTIONEND", "FRACTIONSTART", "IEND", "ISTART",
  "INITCANONEND", "INITCANONSTART", "JEND", "JSTART", "JOINTACCELEND",
  "JOINTACCELSTART", "JOINTDETAILSEND", "JOINTDETAILSSTART",
  "JOINTNUMBEREND", "JOINTNUMBERSTART", "JOINTPOSITIONEND",
  "JOINTPOSITIONSTART", "JOINTSPEEDEND", "JOINTSPEEDSTART", "KEND",
  "KSTART", "LINEARVELOCITYEND", "LINEARVELOCITYSTART", "MESSAGEEND",
  "MESSAGESTART", "MIDDLECOMMANDEND", "MIDDLECOMMANDSTART", "MOMENTEND",
  "MOMENTSTART", "MOVESTRAIGHTEND", "MOVESTRAIGHTSTART", "NAMEEND",
  "NAMESTART", "NUMPOSITIONSEND", "NUMPOSITIONSSTART", "PARAMETERNAMEEND",
  "PARAMETERNAMESTART", "PARAMETERSETTINGEND", "PARAMETERSETTINGSTART",
  "PARAMETERVALUEEND", "PARAMETERVALUESTART", "POINTEND", "POINTSTART",
  "PROGRAMTEXTEND", "PROGRAMTEXTSTART", "REPORTPOSITIONEND",
  "REPORTPOSITIONSTART", "REPORTTORQUEORFORCEEND",
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
  "ACTUATEJOINTTYPEDECL", "ACTUATEJOINTSTYPEDECL", "CRCLCOMMANDTYPEDECL",
  "CRCLPROGRAMTYPEDECL", "CLOSETOOLCHANGERTYPEDECL",
  "CONFIGUREJOINTREPORTTYPEDECL", "CONFIGUREJOINTREPORTSTYPEDECL",
  "DWELLTYPEDECL", "ENDCANONTYPEDECL", "GETSTATUSTYPEDECL",
  "INITCANONTYPEDECL", "JOINTDETAILSTYPEDECL", "JOINTFORCETORQUETYPEDECL",
  "JOINTSPEEDACCELTYPEDECL", "MESSAGETYPEDECL", "MIDDLECOMMANDTYPEDECL",
  "MOVESCREWTYPEDECL", "MOVETHROUGHTOTYPEDECL", "MOVETOTYPEDECL",
  "OPENTOOLCHANGERTYPEDECL", "PARAMETERSETTINGTYPEDECL", "POINTTYPEDECL",
  "POSEANDSETTYPEDECL", "POSETOLERANCETYPEDECL", "POSETYPEDECL",
  "ROTACCELABSOLUTETYPEDECL", "ROTACCELRELATIVETYPEDECL",
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
  "VECTORTYPEDECL", "WRENCHTYPEDECL", "$accept", "y_CRCLProgramFile",
  "y_XmlHeaderForCRCLProgram", "y_SchemaLocation", "y_XmlBoolean",
  "y_XmlDecimal", "y_XmlID", "y_XmlPositiveInteger", "y_XmlString",
  "y_XmlToken", "y_XmlVersion", "y_ActuateJointType",
  "y_ActuateJoint_ActuateJointType_1_u",
  "y_AxialDistanceFree_XmlDecimal_0", "$@1",
  "y_AxialDistanceScrew_XmlDecimal", "$@2", "y_AxisPoint_PointType_0",
  "y_CRCLProgramType", "y_ChangeRate_XmlDecimal_0", "$@3",
  "y_CommandID_XmlPositiveInteger", "$@4", "y_ConfigureJointReportType",
  "y_ConfigureJointReport_Configure1001", "y_Coordinated_XmlBoolean",
  "$@5", "y_DwellTime_XmlDecimal", "$@6", "y_EndCanonType",
  "y_EndCanon_EndCanonType", "y_EndPosition_PoseType",
  "y_Fraction_FractionType", "$@7", "y_I_XmlDecimal", "$@8",
  "y_InitCanonType", "y_InitCanon_InitCanonType", "y_J_XmlDecimal", "$@9",
  "y_JointAccel_XmlDecimal_0", "$@10", "y_JointDetailsTypeAny",
  "y_JointDetails_JointDetailsType", "y_JointNumber_XmlPositiveInteger",
  "$@11", "y_JointPosition_XmlDecimal", "$@12",
  "y_JointSpeed_XmlDecimal_0", "$@13", "y_K_XmlDecimal", "$@14",
  "y_ListActuateJoint_ActuateJointType_1_u",
  "y_ListConfigureJointReport_Configure1001",
  "y_ListMiddleCommand_MiddleCommandType_0_u",
  "y_ListParameterSetting_ParameterSett1002",
  "y_ListWaypoint_PoseType_2_u", "y_ListWaypoint_PoseType_2_u_Check",
  "y_Message_XmlString", "$@15", "y_MiddleCommandTypeAny",
  "y_MiddleCommand_MiddleCommandType_0_u", "y_MoveStraight_XmlBoolean",
  "$@16", "y_Name_XmlID_0", "$@17", "y_NumPositions_XmlPositiveInteger",
  "$@18", "y_ParameterName_XmlToken", "$@19", "y_ParameterSettingType",
  "y_ParameterSetting_ParameterSett1002", "y_ParameterValue_XmlToken",
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
     445,   446
};
# endif

#define YYPACT_NINF -385

#define yypact_value_is_default(Yystate) \
  (!!((Yystate) == (-385)))

#define YYTABLE_NINF -1

#define yytable_value_is_error(Yytable_value) \
  0

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
     STATE-NUM.  */
static const yytype_int16 yypact[] =
{
       6,    11,    26,     7,    43,  -385,    52,     0,    56,    59,
      57,  -385,    58,  -385,    -7,    45,    63,  -385,    65,    51,
    -385,  -385,  -385,    93,  -385,    97,    -7,    60,   -15,  -385,
      30,    74,  -385,    98,  -134,  -385,  -385,  -385,   101,  -385,
      -7,    72,   103,   104,   105,   107,   108,   109,   110,   111,
     112,   113,   114,   115,   116,   117,   119,   120,   121,   122,
     123,   125,   126,   127,   128,   129,   131,   132,    73,  -385,
    -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,
    -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,
    -385,  -385,  -385,  -385,  -385,  -385,    74,  -385,    -7,    -7,
      -7,    -7,    -7,    -7,    -7,    -7,    -7,    -7,    -7,    -7,
      -7,    -7,    -7,    -7,    -7,    -7,    -7,    -7,    -7,    -7,
      -7,    -7,    -7,    -7,  -385,   137,  -385,    74,    74,    74,
      74,    74,    74,    74,    74,    74,    74,    74,    74,    74,
      74,    74,    74,    74,    74,    74,    74,    74,    74,    74,
      74,    74,    74,  -385,   118,   130,  -385,    49,   124,  -385,
      78,    46,    77,    77,  -385,    64,    31,    71,    53,    48,
      38,    48,    44,   133,    71,    61,    67,    47,    54,    62,
      55,  -385,   156,  -385,   130,   159,  -385,   161,  -385,   162,
    -385,    -4,   146,   164,    66,   134,   166,  -385,   168,  -385,
     169,    71,  -385,   170,  -385,   171,  -385,   172,  -385,  -385,
     173,  -385,   174,  -385,    71,  -149,  -385,  -115,  -385,   176,
    -385,  -126,  -385,  -127,  -385,   179,  -385,    -7,   175,  -385,
    -385,   153,  -385,  -385,    -7,   180,  -385,    87,  -385,   184,
     177,  -385,    -4,    66,   136,  -385,    -4,  -385,  -385,  -385,
      -7,   135,  -385,  -385,    -7,    88,  -385,  -385,  -385,   185,
     187,   100,  -385,  -385,   189,   191,   102,  -385,  -385,  -385,
     194,   196,    99,  -385,  -385,   197,   198,   106,  -385,  -385,
    -385,   152,  -385,   205,   206,  -385,   211,   212,   139,    -7,
    -385,    -7,   195,   213,   199,   205,   138,   140,  -385,   217,
    -385,   188,   212,   214,   148,  -385,   223,   141,  -385,   225,
     229,   205,    -7,    -7,  -385,    -7,    -7,  -385,   230,    -7,
      -7,  -385,    -7,    -7,  -385,   231,   233,   183,  -385,   144,
      -7,   207,  -385,   208,  -385,   182,   184,   142,   139,   143,
    -385,  -385,   235,   145,   181,  -385,  -385,  -385,  -385,   160,
     147,   237,   167,   151,   244,   150,   155,   157,   226,   158,
     221,   158,   221,   165,   158,   221,   158,   221,   186,  -385,
     249,   215,  -385,   152,  -385,  -385,  -385,   190,   254,   149,
     142,   264,   154,   211,  -385,   265,  -385,  -385,   137,  -385,
    -385,  -385,   267,  -385,  -385,  -385,   270,   200,  -385,  -385,
    -385,   272,  -385,   273,  -385,  -385,  -385,  -385,  -385,  -385,
    -385,  -385,  -385,   137,  -385,   -87,  -385,   204,  -385,    -7,
     216,   254,  -385,   149,  -385,   276,   163,   266,   211,  -385,
     209,   281,  -385,   211,  -385,   286,   218,  -385,  -385,   241,
     211,   293,   294,   250,  -385,  -385,   295,   242,   258,  -385,
     202,   133,   211,  -385,   330,  -385,  -385,   317,   211,  -385,
    -385,   262,   281,   219,   211,  -385,   334,   220,   211,   337,
    -385,   287,    -7,    -7,  -385,  -385,   336,   253,   339,   297,
    -385,   238,   228,   211,  -385,  -385,   243,  -385,   268,  -385,
     227,   211,  -385,   346,  -385,   256,   313,  -385,   257,   298,
     205,  -385,   352,  -385,  -385,   353,   299,  -127,   269,  -385,
     236,   211,  -385,  -385,  -385,   232,   211,  -385,  -385,  -385,
     355,   338,   357,   316,   282,   205,  -385,   211,  -385,   363,
    -385,   263,  -115,   271,  -385,   239,  -385,   259,   211,  -385,
     364,  -385,  -385,   366,  -385,  -385,   288,   205,   333,   211,
    -385,  -385,   278,  -126,   284,  -385,  -385,   252,   211,  -385,
     211,  -385,  -385,   291,  -385,   335,   211,  -385,   279,  -149,
     277,  -385,   289,   211,   326,   211,  -385,  -385,   328,  -385,
     292,   171,  -385,  -385,   365,  -385,   340,  -385,  -385,   285,
    -385,  -385,  -385
};

  /* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
     Performed when YYTABLE does not specify something else to do.  Zero
     means the default is an error.  */
static const yytype_uint8 yydefact[] =
{
       0,     0,     0,     0,     0,     1,     0,     0,     0,     0,
       0,    11,     0,     3,   102,     0,     0,     4,     0,     0,
       2,    12,   103,     0,    64,     0,   102,     0,     0,     7,
       0,     0,    42,     0,     0,    22,    65,   104,     0,    41,
     102,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,    73,
      74,    75,    76,    77,    78,    79,    80,    81,    82,    83,
      84,    85,    86,    87,    88,    89,    90,    91,    92,    93,
      94,    95,    96,    97,    98,    26,     0,    35,   102,   102,
     102,   102,   102,   102,   102,   102,   102,   102,   102,   102,
     102,   102,   102,   102,   102,   102,   102,   102,   102,   102,
     102,   102,   102,   102,    99,     0,    34,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     8,     0,     0,   200,     0,     0,   203,
       0,   146,     0,     0,   210,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,    27,     0,    60,   199,     0,    62,     0,   202,     0,
     206,     0,    20,     0,     0,     0,     0,   216,     0,   217,
       0,   218,    67,     0,   219,     0,   220,     0,   221,   222,
       0,   223,     0,   224,   225,     0,   226,     0,   227,     0,
     228,     0,   229,     0,   230,     0,   231,   102,     0,    61,
     127,   201,    32,    71,   102,     0,   117,     0,   118,     0,
      15,   100,     0,    70,     0,    69,     0,   209,   119,   165,
     102,     0,    66,   139,   102,     0,   167,   169,    30,     0,
       0,     0,   129,   130,     0,     0,     0,   134,   135,   171,
       0,     0,     0,   153,   154,     0,     0,     0,   158,   159,
     148,     0,    14,     0,     0,    63,     0,     0,     0,   102,
     147,   102,     0,     0,     0,     0,     0,     0,    68,     0,
     208,     0,     0,     0,     0,   110,     0,   180,   150,     0,
       0,     0,   102,   102,   131,   102,   102,   136,     0,   102,
     102,   155,   102,   102,   160,     0,     0,     0,     5,     0,
     102,     0,     6,     0,     9,     0,     0,     0,     0,     0,
      21,    16,     0,     0,     0,   174,   175,   105,    36,     0,
       0,     0,     0,     0,     0,   185,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,    51,
       0,     0,   128,     0,    29,    33,    72,     0,     0,     0,
       0,     0,     0,     0,    18,     0,   207,   101,     0,   120,
     166,   107,     0,   109,   140,   181,     0,   194,   168,   170,
      31,     0,   212,     0,   213,   214,   215,   172,   232,   233,
     234,   235,   149,     0,    53,     0,    13,     0,   114,   102,
       0,     0,   116,     0,   183,     0,     0,     0,     0,   163,
       0,     0,   111,     0,   186,     0,   176,   141,    37,     0,
       0,     0,     0,     0,    48,    49,     0,     0,     0,   179,
       0,     0,     0,   188,     0,   113,    17,     0,     0,   106,
      10,     0,     0,     0,     0,   195,     0,   190,     0,     0,
      52,     0,   102,   102,    50,   121,     0,     0,     0,     0,
     193,   161,     0,     0,   197,    19,     0,   108,     0,   182,
       0,     0,   177,     0,   115,     0,     0,    54,   143,    55,
       0,   123,     0,    28,    39,     0,     0,     0,   137,   184,
       0,     0,   164,   112,   187,     0,     0,   191,   142,    38,
       0,    23,     0,    45,     0,     0,   125,     0,    43,     0,
     173,     0,     0,   156,   189,     0,   196,     0,     0,   144,
       0,   204,    56,     0,   205,   122,     0,     0,     0,     0,
      58,   162,     0,     0,   132,   198,   178,     0,     0,    24,
       0,    46,   124,     0,    40,     0,     0,   138,     0,     0,
     151,   192,     0,     0,     0,     0,   126,    44,     0,   157,
       0,     0,   211,   145,     0,    57,     0,    59,   133,     0,
      25,    47,   152
};

  /* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -385,  -385,  -385,  -385,  -294,  -328,  -385,  -384,    90,   -68,
    -385,  -385,   222,  -385,  -385,  -385,  -385,  -385,  -385,  -385,
    -385,   178,  -385,  -385,  -385,   -56,  -385,  -385,  -385,  -385,
    -385,  -385,  -337,  -385,  -385,  -385,  -385,  -385,  -385,  -385,
    -385,  -385,  -385,  -385,    23,  -385,  -385,  -385,  -385,  -385,
    -385,  -385,  -385,  -385,  -385,   224,  -385,  -385,  -385,  -385,
    -385,  -385,   234,  -385,   -26,  -385,  -385,  -385,  -385,  -385,
    -385,  -191,  -385,  -385,    68,    69,  -182,   192,  -215,  -385,
    -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,  -169,
    -385,  -385,  -131,  -385,  -385,  -385,  -385,  -310,  -385,  -385,
    -385,  -385,  -385,  -385,   240,  -385,  -151,  -385,  -385,  -104,
    -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,
    -385,  -385,   -16,   193,  -385,  -385,    28,  -385,  -385,  -385,
    -385,  -385,  -385,  -385,  -385,  -385,  -385,   -14,  -385,  -385,
    -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,
    -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,
    -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,
    -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385,  -385
};

  /* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     2,     9,    13,   329,   333,    30,   154,   335,   461,
       3,   228,   183,   294,   383,   343,   428,   240,    15,   541,
     573,    39,   125,   331,   285,   213,   311,   188,   286,    41,
      35,   247,   404,   469,   479,   527,    27,    24,   506,   549,
     544,   575,   443,   416,   327,   413,   371,   440,   523,   560,
     530,   566,   184,   231,    28,   201,   243,   244,   190,   287,
      68,    36,   194,   295,    19,    25,   300,   388,   352,   431,
     251,   202,   393,   462,   292,   337,   255,   236,   237,   197,
     302,   447,   500,   477,   525,   503,   547,   186,   283,   261,
     216,   570,   266,   218,   533,   204,   306,   402,   468,   521,
     558,   192,   226,   325,   206,   582,   272,   222,   554,   277,
     224,   508,   386,   458,   199,   303,   208,   309,   211,   310,
     220,   318,   420,   245,   467,   516,   379,   355,   433,   382,
     452,   397,   464,   426,   483,   494,   538,   422,   436,   491,
     455,   511,    69,    70,    71,    72,    73,   444,   445,    74,
      75,    76,    77,    78,   238,   262,   263,   267,   268,    79,
      80,    81,    82,    83,    84,    85,    86,    87,    88,    89,
      90,    91,    92,    93,    94,   273,   274,   278,   279
};

  /* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
     positive, shift that token.  If negative, reduce the rule whose
     number is the opposite.  If YYTABLE_NINF, syntax error.  */
static const yytype_uint16 yytable[] =
{
      31,   344,   234,    42,   430,    10,    43,    11,    44,    45,
     252,    46,   259,   260,    96,     1,    47,   358,    48,    49,
      50,    51,    33,   252,     4,   406,     5,   297,   409,   439,
     411,   301,     6,    52,    53,    54,    55,    56,    57,    58,
      59,    60,    61,    62,    63,    64,    65,    66,    67,   264,
     265,   405,    34,     7,   408,   427,   410,   270,   271,   275,
     276,   441,   442,     8,    12,    14,    18,    16,    17,    20,
      21,    22,   127,   128,   129,   130,   131,   132,   133,   134,
     135,   136,   137,   138,   139,   140,   141,   142,   143,   144,
     145,   146,   147,   148,   149,   150,   151,   152,    23,    26,
     457,    29,    37,    38,    40,   463,    32,    95,    97,    98,
      99,   100,   471,   101,   102,   103,   104,   105,   106,   107,
     108,   109,   110,   111,   482,   112,   113,   114,   115,   116,
     486,   117,   118,   119,   120,   121,   490,   122,   123,   124,
     495,   153,   185,   189,   198,   182,   181,   191,   193,   196,
     200,   207,   203,   205,   235,   510,   215,   210,   225,   187,
     219,   221,   227,   515,   217,   230,   212,   232,   233,   239,
     241,   223,   248,   246,   249,   250,   253,   254,   256,   257,
     258,   242,   269,   535,   284,   280,   289,   290,   537,   282,
     291,   312,   308,   313,   314,   315,   293,   316,   317,   548,
     319,   281,   320,   322,   323,   321,   524,   326,   288,   328,
     557,   299,   330,   305,   324,   332,   334,   340,   350,   341,
     342,   565,   336,   347,   304,   351,   348,   353,   307,   356,
     572,   546,   574,   357,   363,   368,   372,   374,   578,   369,
     370,   384,   375,   391,   389,   584,   376,   586,   392,   394,
     395,   387,   345,   563,   346,   414,   385,   401,   400,   390,
     419,   378,   354,   338,   403,   339,   381,   398,   415,   399,
     424,   429,   418,   432,   126,   396,   434,   407,   437,   438,
     421,   425,   453,   459,   456,   460,   359,   360,   412,   361,
     362,   446,   465,   364,   365,   470,   366,   367,   454,   472,
     473,   475,   474,   478,   373,   155,   156,   157,   158,   159,
     160,   161,   162,   163,   164,   165,   166,   167,   168,   169,
     170,   171,   172,   173,   174,   175,   176,   177,   178,   179,
     180,   476,   480,   435,   449,   466,   484,   485,   487,   489,
     492,   496,   501,   497,   502,   504,   505,   507,   513,   493,
     509,   514,   517,   512,   518,   519,   520,   522,   526,   528,
     529,   539,   534,   542,   536,   540,   532,   543,   545,   550,
     559,   551,   561,   555,   567,   556,   562,   564,   553,   569,
     571,   576,   581,   577,   585,   579,   588,   583,   587,   592,
     591,   590,   349,   448,   488,   481,   417,   195,   214,   589,
     580,   552,   568,   531,   377,   450,   229,   380,   423,   451,
       0,   209,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,   296,     0,   298,     0,     0,     0,
       0,     0,     0,     0,     0,     0,   498,   499
};

static const yytype_int16 yycheck[] =
{
      26,   295,     6,   137,   388,     5,   140,     7,   142,   143,
     201,   145,   161,   162,    40,     9,   150,   311,   152,   153,
     154,   155,    37,   214,    13,   362,     0,   242,   365,   413,
     367,   246,    25,   167,   168,   169,   170,   171,   172,   173,
     174,   175,   176,   177,   178,   179,   180,   181,   182,   164,
     165,   361,    67,    10,   364,   383,   366,   183,   184,   186,
     187,   148,   149,    11,     8,     6,    73,    10,    10,    24,
       7,     6,    98,    99,   100,   101,   102,   103,   104,   105,
     106,   107,   108,   109,   110,   111,   112,   113,   114,   115,
     116,   117,   118,   119,   120,   121,   122,   123,    47,     6,
     428,     4,    72,    29,     6,   433,    46,     6,    36,     6,
       6,     6,   440,     6,     6,     6,     6,     6,     6,     6,
       6,     6,     6,     6,   452,     6,     6,     6,     6,     6,
     458,     6,     6,     6,     6,     6,   464,     6,     6,    66,
     468,     4,    93,    65,   113,    15,    28,   101,    71,    85,
      79,   113,    99,   105,   158,   483,    95,   113,   103,    35,
     113,   107,     6,   491,    97,     6,    33,     6,     6,    23,
       6,   109,     6,    39,     6,     6,     6,     6,     6,     6,
       6,   115,     6,   511,    31,     6,     6,   100,   516,    14,
       6,     6,   104,     6,    94,     6,    19,     6,    96,   527,
       6,   227,     6,     6,     6,   106,   500,    55,   234,     4,
     538,    75,     6,    78,   108,     4,     4,    22,     4,     6,
      21,   549,    83,     6,   250,    77,    38,     4,   254,     4,
     558,   525,   560,     4,     4,     4,    92,    30,   566,     6,
      57,     6,    34,     6,    84,   573,    64,   575,    81,    98,
       6,    70,   114,   547,   114,     6,   111,    99,    32,   112,
       6,   119,   121,   289,    43,   291,   123,   112,    53,   112,
       6,     6,    82,     6,    96,   125,     6,   112,     6,     6,
     131,   127,     6,    74,    18,     4,   312,   313,   102,   315,
     316,    87,     6,   319,   320,    54,   322,   323,   135,     6,
       6,     6,    52,    45,   330,   127,   128,   129,   130,   131,
     132,   133,   134,   135,   136,   137,   138,   139,   140,   141,
     142,   143,   144,   145,   146,   147,   148,   149,   150,   151,
     152,    89,   130,   133,   118,   117,     6,    20,    76,   120,
       6,     4,     6,    56,    91,     6,    49,   109,    80,   129,
     122,   124,     6,   110,    98,    42,    99,    59,     6,     6,
      61,     6,   126,     6,   132,    27,    97,    51,    86,     6,
       6,   108,     6,   134,    96,   116,    88,    44,   107,    95,
     128,    90,   105,    48,    58,   106,    94,    98,    60,   104,
      50,    26,   302,   419,   462,   451,   373,   163,   174,   581,
     569,   532,   553,   507,   336,   421,   184,   338,   380,   423,
      -1,   171,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,   242,    -1,   243,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,   472,   473
};

  /* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
     symbol of state STATE-NUM.  */
static const yytype_uint16 yystos[] =
{
       0,     9,   193,   202,    13,     0,    25,    10,    11,   194,
       5,     7,     8,   195,     6,   210,    10,    10,    73,   256,
      24,     7,     6,    47,   229,   257,     6,   228,   246,     4,
     198,   256,    46,    37,    67,   222,   253,    72,    29,   213,
       6,   221,   137,   140,   142,   143,   145,   150,   152,   153,
     154,   155,   167,   168,   169,   170,   171,   172,   173,   174,
     175,   176,   177,   178,   179,   180,   181,   182,   252,   334,
     335,   336,   337,   338,   341,   342,   343,   344,   345,   351,
     352,   353,   354,   355,   356,   357,   358,   359,   360,   361,
     362,   363,   364,   365,   366,     6,   256,    36,     6,     6,
       6,     6,     6,     6,     6,     6,     6,     6,     6,     6,
       6,     6,     6,     6,     6,     6,     6,     6,     6,     6,
       6,     6,     6,     6,    66,   214,   213,   256,   256,   256,
     256,   256,   256,   256,   256,   256,   256,   256,   256,   256,
     256,   256,   256,   256,   256,   256,   256,   256,   256,   256,
     256,   256,   256,     4,   199,   213,   213,   213,   213,   213,
     213,   213,   213,   213,   213,   213,   213,   213,   213,   213,
     213,   213,   213,   213,   213,   213,   213,   213,   213,   213,
     213,    28,    15,   204,   244,    93,   279,    35,   219,    65,
     250,   101,   293,    71,   254,   254,    85,   271,   113,   306,
      79,   247,   263,    99,   287,   105,   296,   113,   308,   296,
     113,   310,    33,   217,   247,    95,   282,    97,   285,   113,
     312,   107,   299,   109,   302,   103,   294,     6,   203,   204,
       6,   245,     6,     6,     6,   158,   269,   270,   346,    23,
     209,     6,   115,   248,   249,   315,    39,   223,     6,     6,
       6,   262,   263,     6,     6,   268,     6,     6,     6,   161,
     162,   281,   347,   348,   164,   165,   284,   349,   350,     6,
     183,   184,   298,   367,   368,   186,   187,   301,   369,   370,
       6,   256,    14,   280,    31,   216,   220,   251,   256,     6,
     100,     6,   266,    19,   205,   255,   269,   270,   315,    75,
     258,   270,   272,   307,   256,    78,   288,   256,   104,   309,
     311,   218,     6,     6,    94,     6,     6,    96,   313,     6,
       6,   106,     6,     6,   108,   295,    55,   236,     4,   196,
       6,   215,     4,   197,     4,   200,    83,   267,   256,   256,
      22,     6,    21,   207,   196,   114,   114,     6,    38,   200,
       4,    77,   260,     4,   121,   319,     4,     4,   196,   256,
     256,   256,   256,     4,   256,   256,   256,   256,     4,     6,
      57,   238,    92,   256,    30,    34,    64,   266,   119,   318,
     267,   123,   321,   206,     6,   111,   304,    70,   259,    84,
     112,     6,    81,   264,    98,     6,   125,   323,   112,   112,
      32,    99,   289,    43,   224,   289,   224,   112,   289,   224,
     289,   224,   102,   237,     6,    53,   235,   236,    82,     6,
     314,   131,   329,   318,     6,   127,   325,   197,   208,     6,
     199,   261,     6,   320,     6,   133,   330,     6,     6,   199,
     239,   148,   149,   234,   339,   340,    87,   273,   256,   118,
     314,   329,   322,     6,   135,   332,    18,   197,   305,    74,
       4,   201,   265,   197,   324,     6,   117,   316,   290,   225,
      54,   197,     6,     6,    52,     6,    89,   275,    45,   226,
     130,   217,   197,   326,     6,    20,   197,    76,   201,   120,
     197,   331,     6,   129,   327,   197,     4,    56,   256,   256,
     274,     6,    91,   277,     6,    49,   230,   109,   303,   122,
     197,   333,   110,    80,   124,   197,   317,     6,    98,    42,
      99,   291,    59,   240,   196,   276,     6,   227,     6,    61,
     242,   301,    97,   286,   126,   197,   132,   197,   328,     6,
      27,   211,     6,    51,   232,    86,   196,   278,   197,   231,
       6,   108,   284,   107,   300,   134,   116,   197,   292,     6,
     241,     6,    88,   196,    44,   197,   243,    96,   298,    95,
     283,   128,   197,   212,   197,   233,    90,    48,   197,   106,
     281,   105,   297,    98,   197,    58,   197,    60,    94,   268,
      26,    50,   104
};

  /* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint16 yyr1[] =
{
       0,   192,   193,   194,   195,   196,   197,   198,   199,   200,
     201,   202,   202,   203,   204,   205,   206,   205,   208,   207,
     209,   209,   210,   211,   212,   211,   214,   213,   215,   216,
     218,   217,   220,   219,   221,   222,   223,   225,   224,   227,
     226,   228,   229,   231,   230,   232,   233,   232,   234,   234,
     235,   237,   236,   239,   238,   240,   241,   240,   243,   242,
     244,   244,   245,   245,   246,   246,   247,   247,   248,   248,
     249,   251,   250,   252,   252,   252,   252,   252,   252,   252,
     252,   252,   252,   252,   252,   252,   252,   252,   252,   252,
     252,   252,   252,   252,   252,   252,   252,   252,   252,   253,
     255,   254,   256,   257,   256,   259,   258,   261,   260,   262,
     263,   265,   264,   266,   267,   268,   269,   270,   270,   272,
     271,   274,   273,   276,   275,   278,   277,   280,   279,   281,
     281,   282,   283,   283,   284,   284,   285,   286,   286,   288,
     287,   290,   289,   291,   292,   291,   293,   293,   295,   294,
     296,   297,   297,   298,   298,   299,   300,   300,   301,   301,
     302,   303,   303,   305,   304,   307,   306,   309,   308,   311,
     310,   313,   312,   314,   315,   315,   316,   317,   316,   318,
     319,   320,   319,   322,   321,   323,   324,   323,   326,   325,
     327,   328,   327,   329,   330,   331,   330,   333,   332,   334,
     335,   336,   337,   338,   339,   340,   341,   342,   343,   344,
     345,   346,   347,   348,   349,   350,   351,   352,   353,   354,
     355,   356,   357,   358,   359,   360,   361,   362,   363,   364,
     365,   366,   367,   368,   369,   370
};

  /* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     5,     2,     2,     1,     1,     1,     1,     1,
       1,     4,     6,     5,     3,     0,     0,     5,     0,     5,
       0,     3,     5,     0,     0,     5,     0,     5,     6,     3,
       0,     5,     0,     5,     3,     3,     3,     0,     5,     0,
       5,     3,     3,     0,     5,     0,     0,     5,     1,     1,
       3,     0,     5,     0,     5,     0,     0,     5,     0,     5,
       1,     2,     0,     2,     0,     2,     2,     1,     2,     1,
       1,     0,     5,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     3,
       0,     5,     0,     0,     5,     0,     5,     0,     5,     4,
       3,     0,     5,     5,     3,     7,     5,     1,     1,     0,
       5,     0,     5,     0,     5,     0,     5,     0,     5,     1,
       1,     3,     0,     3,     1,     1,     3,     0,     3,     0,
       5,     0,     5,     0,     0,     5,     0,     3,     0,     5,
       3,     0,     3,     1,     1,     3,     0,     3,     1,     1,
       3,     0,     3,     0,     5,     0,     5,     0,     5,     0,
       5,     0,     5,     5,     3,     3,     0,     0,     5,     3,
       0,     0,     5,     0,     5,     0,     0,     5,     0,     5,
       0,     0,     5,     3,     0,     0,     5,     0,     5,     5,
       4,     6,     5,     4,     5,     5,     5,     9,     7,     6,
       4,    12,     4,     4,     4,     4,     5,     5,     5,     5,
       5,     5,     5,     5,     5,     5,     5,     5,     5,     5,
       5,     5,     4,     4,     4,     4
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

    {(yyval.CRCLProgramTypeVal) = new CRCLProgramType((yyvsp[-3].XmlIDVal), (yyvsp[-2].InitCanonTypeVal), (yyvsp[-1].ListMiddleCommandTypeVal), (yyvsp[0].EndCanonTypeVal));}

    break;

  case 23:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 24:

    {yyReadData = 1;}

    break;

  case 25:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 26:

    {yyReadData = 1;}

    break;

  case 27:

    {(yyval.XmlPositiveIntegerVal) = (yyvsp[-1].XmlPositiveIntegerVal);}

    break;

  case 28:

    {(yyval.ConfigureJointReportTypeVal) = new ConfigureJointReportType((yyvsp[-4].XmlIDVal), (yyvsp[-3].XmlPositiveIntegerVal), (yyvsp[-2].XmlBooleanVal), (yyvsp[-1].XmlBooleanVal), (yyvsp[0].XmlBooleanVal));}

    break;

  case 29:

    {(yyval.ConfigureJointReportTypeVal) = (yyvsp[-1].ConfigureJointReportTypeVal);}

    break;

  case 30:

    {yyReadData = 1;}

    break;

  case 31:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

    break;

  case 32:

    {yyReadData = 1;}

    break;

  case 33:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 34:

    {(yyval.EndCanonTypeVal) = new EndCanonType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlPositiveIntegerVal));}

    break;

  case 35:

    {(yyval.EndCanonTypeVal) = (yyvsp[-1].EndCanonTypeVal);}

    break;

  case 36:

    {(yyval.PoseTypeVal) = (yyvsp[-1].PoseTypeVal);}

    break;

  case 37:

    {yyReadData = 1;}

    break;

  case 38:

    {(yyval.FractionTypeVal) = new FractionType((yyvsp[-1].sVal));
	   if ((yyval.FractionTypeVal)->bad)
	     yyerror("bad Fraction value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 39:

    {yyReadData = 1;}

    break;

  case 40:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 41:

    {(yyval.InitCanonTypeVal) = new InitCanonType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlPositiveIntegerVal));}

    break;

  case 42:

    {(yyval.InitCanonTypeVal) = (yyvsp[-1].InitCanonTypeVal);}

    break;

  case 43:

    {yyReadData = 1;}

    break;

  case 44:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 45:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 46:

    {yyReadData = 1;}

    break;

  case 47:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 48:

    {(yyval.JointDetailsTypeVal) = (yyvsp[0].JointForceTorqueTypeVal);}

    break;

  case 49:

    {(yyval.JointDetailsTypeVal) = (yyvsp[0].JointSpeedAccelTypeVal);}

    break;

  case 50:

    {(yyval.JointDetailsTypeVal) = (yyvsp[-1].JointDetailsTypeVal);}

    break;

  case 51:

    {yyReadData = 1;}

    break;

  case 52:

    {(yyval.XmlPositiveIntegerVal) = (yyvsp[-1].XmlPositiveIntegerVal);}

    break;

  case 53:

    {yyReadData = 1;}

    break;

  case 54:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 55:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 56:

    {yyReadData = 1;}

    break;

  case 57:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 58:

    {yyReadData = 1;}

    break;

  case 59:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 60:

    {(yyval.ListActuateJointTypeVal) = new std::list<ActuateJointType *>;
	   (yyval.ListActuateJointTypeVal)->push_back((yyvsp[0].ActuateJointTypeVal));}

    break;

  case 61:

    {(yyval.ListActuateJointTypeVal) = (yyvsp[-1].ListActuateJointTypeVal);
	   (yyval.ListActuateJointTypeVal)->push_back((yyvsp[0].ActuateJointTypeVal));}

    break;

  case 62:

    {(yyval.ListConfigureJointReportTypeVal) = new std::list<ConfigureJointReportType *>;}

    break;

  case 63:

    {(yyval.ListConfigureJointReportTypeVal) = (yyvsp[-1].ListConfigureJointReportTypeVal);
	   (yyval.ListConfigureJointReportTypeVal)->push_back((yyvsp[0].ConfigureJointReportTypeVal));}

    break;

  case 64:

    {(yyval.ListMiddleCommandTypeVal) = new std::list<MiddleCommandType *>;}

    break;

  case 65:

    {(yyval.ListMiddleCommandTypeVal) = (yyvsp[-1].ListMiddleCommandTypeVal);
	   (yyval.ListMiddleCommandTypeVal)->push_back((yyvsp[0].MiddleCommandTypeVal));}

    break;

  case 66:

    {(yyval.ListParameterSettingTypeVal) = (yyvsp[-1].ListParameterSettingTypeVal);
	   (yyval.ListParameterSettingTypeVal)->push_back((yyvsp[0].ParameterSettingTypeVal));}

    break;

  case 67:

    {(yyval.ListParameterSettingTypeVal) = new std::list<ParameterSettingType *>;
	   (yyval.ListParameterSettingTypeVal)->push_back((yyvsp[0].ParameterSettingTypeVal));}

    break;

  case 68:

    {(yyval.ListPoseTypeVal) = (yyvsp[-1].ListPoseTypeVal);
	   (yyval.ListPoseTypeVal)->push_back((yyvsp[0].PoseTypeVal));}

    break;

  case 69:

    {(yyval.ListPoseTypeVal) = new std::list<PoseType *>;
	   (yyval.ListPoseTypeVal)->push_back((yyvsp[0].PoseTypeVal));}

    break;

  case 70:

    {(yyval.ListPoseTypeVal) = (yyvsp[0].ListPoseTypeVal);
	   if ((yyvsp[0].ListPoseTypeVal)->size() < 2)
	     yyerror("must be at least 2 Waypoints");
	  }

    break;

  case 71:

    {yyReadData = 1;}

    break;

  case 72:

    {(yyval.XmlStringVal) = (yyvsp[-1].XmlStringVal);}

    break;

  case 73:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].ActuateJointsTypeVal);}

    break;

  case 74:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].CloseToolChangerTypeVal);}

    break;

  case 75:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].ConfigureJointReportsTypeVal);}

    break;

  case 76:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].DwellTypeVal);}

    break;

  case 77:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].GetStatusTypeVal);}

    break;

  case 78:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].MessageTypeVal);}

    break;

  case 79:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].MoveScrewTypeVal);}

    break;

  case 80:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].MoveThroughToTypeVal);}

    break;

  case 81:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].MoveToTypeVal);}

    break;

  case 82:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].OpenToolChangerTypeVal);}

    break;

  case 83:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].RunProgramTypeVal);}

    break;

  case 84:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetAngleUnitsTypeVal);}

    break;

  case 85:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetEndEffectorParametersTypeVal);}

    break;

  case 86:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetEndEffectorTypeVal);}

    break;

  case 87:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetEndPoseToleranceTypeVal);}

    break;

  case 88:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetForceUnitsTypeVal);}

    break;

  case 89:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetIntermediatePoseToleranceTypeVal);}

    break;

  case 90:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetLengthUnitsTypeVal);}

    break;

  case 91:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetMotionCoordinationTypeVal);}

    break;

  case 92:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetRobotParametersTypeVal);}

    break;

  case 93:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetRotAccelTypeVal);}

    break;

  case 94:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetRotSpeedTypeVal);}

    break;

  case 95:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetTorqueUnitsTypeVal);}

    break;

  case 96:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetTransAccelTypeVal);}

    break;

  case 97:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].SetTransSpeedTypeVal);}

    break;

  case 98:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[0].StopMotionTypeVal);}

    break;

  case 99:

    {(yyval.MiddleCommandTypeVal) = (yyvsp[-1].MiddleCommandTypeVal);}

    break;

  case 100:

    {yyReadData = 1;}

    break;

  case 101:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

    break;

  case 102:

    {(yyval.XmlIDVal) = 0;}

    break;

  case 103:

    {yyReadData = 1;}

    break;

  case 104:

    {(yyval.XmlIDVal) = (yyvsp[-1].XmlIDVal);}

    break;

  case 105:

    {yyReadData = 1;}

    break;

  case 106:

    {(yyval.XmlPositiveIntegerVal) = (yyvsp[-1].XmlPositiveIntegerVal);}

    break;

  case 107:

    {yyReadData = 1;}

    break;

  case 108:

    {(yyval.XmlTokenVal) = (yyvsp[-1].XmlTokenVal);}

    break;

  case 109:

    {(yyval.ParameterSettingTypeVal) = new ParameterSettingType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlTokenVal), (yyvsp[0].XmlTokenVal));}

    break;

  case 110:

    {(yyval.ParameterSettingTypeVal) = (yyvsp[-1].ParameterSettingTypeVal);}

    break;

  case 111:

    {yyReadData = 1;}

    break;

  case 112:

    {(yyval.XmlTokenVal) = (yyvsp[-1].XmlTokenVal);}

    break;

  case 113:

    {(yyval.PointTypeVal) = new PointType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));}

    break;

  case 114:

    {(yyval.PointTypeVal) = (yyvsp[-1].PointTypeVal);}

    break;

  case 115:

    {(yyval.PoseToleranceTypeVal) = new PoseToleranceType((yyvsp[-5].XmlIDVal), (yyvsp[-4].XmlDecimalVal), (yyvsp[-3].XmlDecimalVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));}

    break;

  case 116:

    {(yyval.PoseTypeVal) = new PoseType((yyvsp[-3].XmlIDVal), (yyvsp[-2].PointTypeVal), (yyvsp[-1].VectorTypeVal), (yyvsp[0].VectorTypeVal));}

    break;

  case 117:

    {(yyval.PoseTypeVal) = (yyvsp[0].PoseTypeVal);}

    break;

  case 118:

    {(yyval.PoseTypeVal) = (yyvsp[0].PoseAndSetTypeVal);}

    break;

  case 119:

    {yyReadData = 1;}

    break;

  case 120:

    {(yyval.XmlStringVal) = (yyvsp[-1].XmlStringVal);}

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

    {yyReadData = 1;}

    break;

  case 126:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

    break;

  case 127:

    {yyReadData = 1;}

    break;

  case 128:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

    break;

  case 129:

    {(yyval.RotAccelTypeVal) = (yyvsp[0].RotAccelAbsoluteTypeVal);}

    break;

  case 130:

    {(yyval.RotAccelTypeVal) = (yyvsp[0].RotAccelRelativeTypeVal);}

    break;

  case 131:

    {(yyval.RotAccelTypeVal) = (yyvsp[-1].RotAccelTypeVal);}

    break;

  case 132:

    {(yyval.RotAccelTypeVal) = 0;}

    break;

  case 133:

    {(yyval.RotAccelTypeVal) = (yyvsp[-1].RotAccelTypeVal);}

    break;

  case 134:

    {(yyval.RotSpeedTypeVal) = (yyvsp[0].RotSpeedAbsoluteTypeVal);}

    break;

  case 135:

    {(yyval.RotSpeedTypeVal) = (yyvsp[0].RotSpeedRelativeTypeVal);}

    break;

  case 136:

    {(yyval.RotSpeedTypeVal) = (yyvsp[-1].RotSpeedTypeVal);}

    break;

  case 137:

    {(yyval.RotSpeedTypeVal) = 0;}

    break;

  case 138:

    {(yyval.RotSpeedTypeVal) = (yyvsp[-1].RotSpeedTypeVal);}

    break;

  case 139:

    {yyReadData = 1;}

    break;

  case 140:

    {(yyval.FractionTypeVal) = new FractionType((yyvsp[-1].sVal));
	   if ((yyval.FractionTypeVal)->bad)
	     yyerror("bad Setting value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 141:

    {yyReadData = 1;}

    break;

  case 142:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 143:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 144:

    {yyReadData = 1;}

    break;

  case 145:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 146:

    {(yyval.PoseTypeVal) = 0;}

    break;

  case 147:

    {(yyval.PoseTypeVal) = (yyvsp[-1].PoseTypeVal);}

    break;

  case 148:

    {yyReadData = 1;}

    break;

  case 149:

    {(yyval.StopConditionEnumTypeVal) = new StopConditionEnumType((yyvsp[-1].sVal));
	   if ((yyval.StopConditionEnumTypeVal)->bad)
	     yyerror("bad StopCondition value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 150:

    {(yyval.PoseToleranceTypeVal) = (yyvsp[-1].PoseToleranceTypeVal);}

    break;

  case 151:

    {(yyval.PoseToleranceTypeVal) = 0;}

    break;

  case 152:

    {(yyval.PoseToleranceTypeVal) = (yyvsp[-1].PoseToleranceTypeVal);}

    break;

  case 153:

    {(yyval.TransAccelTypeVal) = (yyvsp[0].TransAccelAbsoluteTypeVal);}

    break;

  case 154:

    {(yyval.TransAccelTypeVal) = (yyvsp[0].TransAccelRelativeTypeVal);}

    break;

  case 155:

    {(yyval.TransAccelTypeVal) = (yyvsp[-1].TransAccelTypeVal);}

    break;

  case 156:

    {(yyval.TransAccelTypeVal) = 0;}

    break;

  case 157:

    {(yyval.TransAccelTypeVal) = (yyvsp[-1].TransAccelTypeVal);}

    break;

  case 158:

    {(yyval.TransSpeedTypeVal) = (yyvsp[0].TransSpeedAbsoluteTypeVal);}

    break;

  case 159:

    {(yyval.TransSpeedTypeVal) = (yyvsp[0].TransSpeedRelativeTypeVal);}

    break;

  case 160:

    {(yyval.TransSpeedTypeVal) = (yyvsp[-1].TransSpeedTypeVal);}

    break;

  case 161:

    {(yyval.TransSpeedTypeVal) = 0;}

    break;

  case 162:

    {(yyval.TransSpeedTypeVal) = (yyvsp[-1].TransSpeedTypeVal);}

    break;

  case 163:

    {yyReadData = 1;}

    break;

  case 164:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 165:

    {yyReadData = 1;}

    break;

  case 166:

    {(yyval.AngleUnitEnumTypeVal) = new AngleUnitEnumType((yyvsp[-1].sVal));
	   if ((yyval.AngleUnitEnumTypeVal)->bad)
	     yyerror("bad UnitName value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 167:

    {yyReadData = 1;}

    break;

  case 168:

    {(yyval.ForceUnitEnumTypeVal) = new ForceUnitEnumType((yyvsp[-1].sVal));
	   if ((yyval.ForceUnitEnumTypeVal)->bad)
	     yyerror("bad UnitName value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 169:

    {yyReadData = 1;}

    break;

  case 170:

    {(yyval.LengthUnitEnumTypeVal) = new LengthUnitEnumType((yyvsp[-1].sVal));
	   if ((yyval.LengthUnitEnumTypeVal)->bad)
	     yyerror("bad UnitName value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 171:

    {yyReadData = 1;}

    break;

  case 172:

    {(yyval.TorqueUnitEnumTypeVal) = new TorqueUnitEnumType((yyvsp[-1].sVal));
	   if ((yyval.TorqueUnitEnumTypeVal)->bad)
	     yyerror("bad UnitName value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 173:

    {(yyval.VectorTypeVal) = new VectorType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));}

    break;

  case 174:

    {(yyval.PoseTypeVal) = (yyvsp[-1].PoseTypeVal);}

    break;

  case 175:

    {(yyval.PoseTypeVal) = (yyvsp[-1].PoseTypeVal);}

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

    {(yyval.VectorTypeVal) = (yyvsp[-1].VectorTypeVal);}

    break;

  case 180:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 181:

    {yyReadData = 1;}

    break;

  case 182:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 183:

    {yyReadData = 1;}

    break;

  case 184:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 185:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 186:

    {yyReadData = 1;}

    break;

  case 187:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 188:

    {yyReadData = 1;}

    break;

  case 189:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

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

    {(yyval.ActuateJointsTypeVal) = new ActuateJointsType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].ListActuateJointTypeVal));
	   (yyval.ActuateJointsTypeVal)->printTypp = true;
	  }

    break;

  case 200:

    {(yyval.CloseToolChangerTypeVal) = new CloseToolChangerType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlPositiveIntegerVal));
	   (yyval.CloseToolChangerTypeVal)->printTypp = true;
	  }

    break;

  case 201:

    {(yyval.ConfigureJointReportsTypeVal) = new ConfigureJointReportsType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlPositiveIntegerVal), (yyvsp[-1].XmlBooleanVal), (yyvsp[0].ListConfigureJointReportTypeVal));
	   (yyval.ConfigureJointReportsTypeVal)->printTypp = true;
	  }

    break;

  case 202:

    {(yyval.DwellTypeVal) = new DwellType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.DwellTypeVal)->printTypp = true;
	  }

    break;

  case 203:

    {(yyval.GetStatusTypeVal) = new GetStatusType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlPositiveIntegerVal));
	   (yyval.GetStatusTypeVal)->printTypp = true;
	  }

    break;

  case 204:

    {(yyval.JointForceTorqueTypeVal) = new JointForceTorqueType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.JointForceTorqueTypeVal)->printTypp = true;
	  }

    break;

  case 205:

    {(yyval.JointSpeedAccelTypeVal) = new JointSpeedAccelType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.JointSpeedAccelTypeVal)->printTypp = true;
	  }

    break;

  case 206:

    {(yyval.MessageTypeVal) = new MessageType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].XmlStringVal));
	   (yyval.MessageTypeVal)->printTypp = true;
	  }

    break;

  case 207:

    {(yyval.MoveScrewTypeVal) = new MoveScrewType((yyvsp[-6].XmlIDVal), (yyvsp[-5].XmlPositiveIntegerVal), (yyvsp[-4].PoseTypeVal), (yyvsp[-3].PointTypeVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.MoveScrewTypeVal)->printTypp = true;
	  }

    break;

  case 208:

    {(yyval.MoveThroughToTypeVal) = new MoveThroughToType((yyvsp[-4].XmlIDVal), (yyvsp[-3].XmlPositiveIntegerVal), (yyvsp[-2].XmlBooleanVal), (yyvsp[-1].ListPoseTypeVal), (yyvsp[0].XmlPositiveIntegerVal));
	   (yyval.MoveThroughToTypeVal)->printTypp = true;
	  }

    break;

  case 209:

    {(yyval.MoveToTypeVal) = new MoveToType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlPositiveIntegerVal), (yyvsp[-1].XmlBooleanVal), (yyvsp[0].PoseTypeVal));
	   (yyval.MoveToTypeVal)->printTypp = true;
	  }

    break;

  case 210:

    {(yyval.OpenToolChangerTypeVal) = new OpenToolChangerType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlPositiveIntegerVal));
	   (yyval.OpenToolChangerTypeVal)->printTypp = true;
	  }

    break;

  case 211:

    {(yyval.PoseAndSetTypeVal) = new PoseAndSetType((yyvsp[-9].XmlIDVal), (yyvsp[-8].PointTypeVal), (yyvsp[-7].VectorTypeVal), (yyvsp[-6].VectorTypeVal), (yyvsp[-5].XmlBooleanVal), (yyvsp[-4].TransSpeedTypeVal), (yyvsp[-3].RotSpeedTypeVal), (yyvsp[-2].TransAccelTypeVal), (yyvsp[-1].RotAccelTypeVal), (yyvsp[0].PoseToleranceTypeVal));
	   (yyval.PoseAndSetTypeVal)->printTypp = true;
	  }

    break;

  case 212:

    {(yyval.RotAccelAbsoluteTypeVal) = new RotAccelAbsoluteType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.RotAccelAbsoluteTypeVal)->printTypp = true;
	  }

    break;

  case 213:

    {(yyval.RotAccelRelativeTypeVal) = new RotAccelRelativeType((yyvsp[-1].XmlIDVal), (yyvsp[0].FractionTypeVal));
	   (yyval.RotAccelRelativeTypeVal)->printTypp = true;
	  }

    break;

  case 214:

    {(yyval.RotSpeedAbsoluteTypeVal) = new RotSpeedAbsoluteType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.RotSpeedAbsoluteTypeVal)->printTypp = true;
	  }

    break;

  case 215:

    {(yyval.RotSpeedRelativeTypeVal) = new RotSpeedRelativeType((yyvsp[-1].XmlIDVal), (yyvsp[0].FractionTypeVal));
	   (yyval.RotSpeedRelativeTypeVal)->printTypp = true;
	  }

    break;

  case 216:

    {(yyval.RunProgramTypeVal) = new RunProgramType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].XmlStringVal));
	   (yyval.RunProgramTypeVal)->printTypp = true;
	  }

    break;

  case 217:

    {(yyval.SetAngleUnitsTypeVal) = new SetAngleUnitsType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].AngleUnitEnumTypeVal));
	   (yyval.SetAngleUnitsTypeVal)->printTypp = true;
	  }

    break;

  case 218:

    {(yyval.SetEndEffectorParametersTypeVal) = new SetEndEffectorParametersType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].ListParameterSettingTypeVal));
	   (yyval.SetEndEffectorParametersTypeVal)->printTypp = true;
	  }

    break;

  case 219:

    {(yyval.SetEndEffectorTypeVal) = new SetEndEffectorType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].FractionTypeVal));
	   (yyval.SetEndEffectorTypeVal)->printTypp = true;
	  }

    break;

  case 220:

    {(yyval.SetEndPoseToleranceTypeVal) = new SetEndPoseToleranceType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].PoseToleranceTypeVal));
	   (yyval.SetEndPoseToleranceTypeVal)->printTypp = true;
	  }

    break;

  case 221:

    {(yyval.SetForceUnitsTypeVal) = new SetForceUnitsType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].ForceUnitEnumTypeVal));
	   (yyval.SetForceUnitsTypeVal)->printTypp = true;
	  }

    break;

  case 222:

    {(yyval.SetIntermediatePoseToleranceTypeVal) = new SetIntermediatePoseToleranceType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].PoseToleranceTypeVal));
	   (yyval.SetIntermediatePoseToleranceTypeVal)->printTypp = true;
	  }

    break;

  case 223:

    {(yyval.SetLengthUnitsTypeVal) = new SetLengthUnitsType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].LengthUnitEnumTypeVal));
	   (yyval.SetLengthUnitsTypeVal)->printTypp = true;
	  }

    break;

  case 224:

    {(yyval.SetMotionCoordinationTypeVal) = new SetMotionCoordinationType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].XmlBooleanVal));
	   (yyval.SetMotionCoordinationTypeVal)->printTypp = true;
	  }

    break;

  case 225:

    {(yyval.SetRobotParametersTypeVal) = new SetRobotParametersType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].ListParameterSettingTypeVal));
	   (yyval.SetRobotParametersTypeVal)->printTypp = true;
	  }

    break;

  case 226:

    {(yyval.SetRotAccelTypeVal) = new SetRotAccelType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].RotAccelTypeVal));
	   (yyval.SetRotAccelTypeVal)->printTypp = true;
	  }

    break;

  case 227:

    {(yyval.SetRotSpeedTypeVal) = new SetRotSpeedType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].RotSpeedTypeVal));
	   (yyval.SetRotSpeedTypeVal)->printTypp = true;
	  }

    break;

  case 228:

    {(yyval.SetTorqueUnitsTypeVal) = new SetTorqueUnitsType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].TorqueUnitEnumTypeVal));
	   (yyval.SetTorqueUnitsTypeVal)->printTypp = true;
	  }

    break;

  case 229:

    {(yyval.SetTransAccelTypeVal) = new SetTransAccelType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].TransAccelTypeVal));
	   (yyval.SetTransAccelTypeVal)->printTypp = true;
	  }

    break;

  case 230:

    {(yyval.SetTransSpeedTypeVal) = new SetTransSpeedType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].TransSpeedTypeVal));
	   (yyval.SetTransSpeedTypeVal)->printTypp = true;
	  }

    break;

  case 231:

    {(yyval.StopMotionTypeVal) = new StopMotionType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].StopConditionEnumTypeVal));
	   (yyval.StopMotionTypeVal)->printTypp = true;
	  }

    break;

  case 232:

    {(yyval.TransAccelAbsoluteTypeVal) = new TransAccelAbsoluteType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.TransAccelAbsoluteTypeVal)->printTypp = true;
	  }

    break;

  case 233:

    {(yyval.TransAccelRelativeTypeVal) = new TransAccelRelativeType((yyvsp[-1].XmlIDVal), (yyvsp[0].FractionTypeVal));
	   (yyval.TransAccelRelativeTypeVal)->printTypp = true;
	  }

    break;

  case 234:

    {(yyval.TransSpeedAbsoluteTypeVal) = new TransSpeedAbsoluteType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.TransSpeedAbsoluteTypeVal)->printTypp = true;
	  }

    break;

  case 235:

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
