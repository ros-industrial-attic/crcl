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
#include "crcl/CRCLCommandInstanceClasses.hh"

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
#ifndef YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_CRCL_SRC_CRCLCOMMANDINSTANCEYACC_HH_INCLUDED
# define YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_CRCL_SRC_CRCLCOMMANDINSTANCEYACC_HH_INCLUDED
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

#endif /* !YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_CRCL_SRC_CRCLCOMMANDINSTANCEYACC_HH_INCLUDED  */

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
#define YYLAST   451

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  210
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  186
/* YYNRULES -- Number of rules.  */
#define YYNRULES  247
/* YYNSTATES -- Number of states.  */
#define YYNSTATES  618

/* YYTRANSLATE[YYX] -- Symbol number corresponding to YYX as returned
   by yylex, with out-of-bounds checking.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   464

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
     205,   206,   207,   208,   209
};

#if YYDEBUG
  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,   468,   468,   478,   483,   490,   499,   508,   517,   526,
     535,   544,   553,   562,   568,   581,   587,   598,   599,   599,
     605,   605,   612,   613,   618,   623,   625,   627,   629,   631,
     633,   635,   637,   639,   641,   643,   645,   647,   649,   651,
     653,   655,   657,   659,   661,   663,   665,   667,   669,   671,
     673,   675,   677,   682,   688,   689,   689,   695,   695,   701,
     708,   714,   714,   726,   726,   732,   742,   742,   751,   751,
     756,   756,   762,   763,   763,   769,   771,   776,   781,   781,
     787,   787,   794,   795,   795,   801,   801,   811,   814,   822,
     823,   830,   834,   840,   844,   850,   863,   863,   873,   873,
     880,   881,   881,   886,   886,   893,   894,   894,   904,   904,
     910,   916,   921,   921,   927,   933,   938,   946,   948,   953,
     961,   962,   962,   972,   972,   985,   986,   986,   992,   992,
     998,   998,  1004,  1004,  1010,  1010,  1015,  1017,  1022,  1028,
    1029,  1034,  1036,  1041,  1047,  1048,  1053,  1053,  1062,  1062,
    1068,  1069,  1069,  1075,  1076,  1081,  1081,  1092,  1093,  1093,
    1099,  1105,  1106,  1111,  1113,  1118,  1124,  1125,  1130,  1132,
    1137,  1143,  1144,  1149,  1149,  1154,  1154,  1163,  1163,  1172,
    1172,  1181,  1181,  1195,  1201,  1203,  1209,  1210,  1210,  1216,
    1222,  1223,  1223,  1229,  1229,  1235,  1236,  1236,  1242,  1242,
    1248,  1249,  1249,  1255,  1261,  1262,  1262,  1268,  1268,  1273,
    1282,  1290,  1299,  1307,  1315,  1323,  1331,  1339,  1347,  1355,
    1366,  1376,  1385,  1393,  1407,  1415,  1423,  1431,  1439,  1447,
    1455,  1464,  1472,  1480,  1488,  1496,  1504,  1512,  1521,  1529,
    1537,  1545,  1553,  1561,  1570,  1578,  1586,  1594
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
  "DESCRIPTIONEND", "DESCRIPTIONSTART", "DWELLTIMEEND", "DWELLTIMESTART",
  "ENDPOSITIONEND", "ENDPOSITIONSTART", "FORCEEND", "FORCESTART",
  "FRACTIONEND", "FRACTIONSTART", "IEND", "ISTART", "JEND", "JSTART",
  "JOINTACCELEND", "JOINTACCELSTART", "JOINTDETAILSEND",
  "JOINTDETAILSSTART", "JOINTNUMBEREND", "JOINTNUMBERSTART",
  "JOINTPOSITIONEND", "JOINTPOSITIONSTART", "JOINTSPEEDEND",
  "JOINTSPEEDSTART", "KEND", "KSTART", "LINEARVELOCITYEND",
  "LINEARVELOCITYSTART", "LOWERRIGHTEND", "LOWERRIGHTSTART", "MESSAGEEND",
  "MESSAGESTART", "MOMENTEND", "MOMENTSTART", "MOVESTRAIGHTEND",
  "MOVESTRAIGHTSTART", "NAMEEND", "NAMESTART", "NUMPOSITIONSEND",
  "NUMPOSITIONSSTART", "ORIENTATIONSTANDARDDEVIATIONEND",
  "ORIENTATIONSTANDARDDEVIATIONSTART", "PARAMETERNAMEEND",
  "PARAMETERNAMESTART", "PARAMETERSETTINGEND", "PARAMETERSETTINGSTART",
  "PARAMETERVALUEEND", "PARAMETERVALUESTART", "POINTEND", "POINTSTART",
  "POSITIONSTANDARDDEVIATIONEND", "POSITIONSTANDARDDEVIATIONSTART",
  "PROGRAMTEXTEND", "PROGRAMTEXTSTART", "REFOBJECTNAMEEND",
  "REFOBJECTNAMESTART", "REPORTPOSITIONEND", "REPORTPOSITIONSTART",
  "REPORTTORQUEORFORCEEND", "REPORTTORQUEORFORCESTART",
  "REPORTVELOCITYEND", "REPORTVELOCITYSTART", "RESETALLEND",
  "RESETALLSTART", "ROTACCELEND", "ROTACCELSTART", "ROTSPEEDEND",
  "ROTSPEEDSTART", "SETTINGEND", "SETTINGSTART", "STARTPOSITIONEND",
  "STARTPOSITIONSTART", "STOPCONDITIONEND", "STOPCONDITIONSTART",
  "TIMESTAMPEND", "TIMESTAMPSTART", "TOLERANCEEND", "TOLERANCESTART",
  "TRANSACCELEND", "TRANSACCELSTART", "TRANSSPEEDEND", "TRANSSPEEDSTART",
  "TURNEND", "TURNSTART", "UNITNAMEEND", "UNITNAMESTART", "UPPERLEFTEND",
  "UPPERLEFTSTART", "WAYPOINTEND", "WAYPOINTSTART", "XAXISTOLERANCEEND",
  "XAXISTOLERANCESTART", "XAXISEND", "XAXISSTART", "XPOINTTOLERANCEEND",
  "XPOINTTOLERANCESTART", "XEND", "XSTART", "YPOINTTOLERANCEEND",
  "YPOINTTOLERANCESTART", "YEND", "YSTART", "ZAXISTOLERANCEEND",
  "ZAXISTOLERANCESTART", "ZAXISEND", "ZAXISSTART", "ZPOINTTOLERANCEEND",
  "ZPOINTTOLERANCESTART", "ZEND", "ZSTART", "ACTUATEJOINTTYPEDECL",
  "ACTUATEJOINTSTYPEDECL", "CRCLCOMMANDINSTANCETYPEDECL",
  "CRCLCOMMANDTYPEDECL", "CLOSETOOLCHANGERTYPEDECL",
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
  "VECTORTYPEDECL", "WRENCHTYPEDECL", "$accept",
  "y_CRCLCommandInstanceFile", "y_XmlHeaderForCRCLCommandInstance",
  "y_SchemaLocation", "y_XmlBoolean", "y_XmlDateTime", "y_XmlDecimal",
  "y_XmlID", "y_XmlIDREF", "y_XmlPositiveInteger", "y_XmlString",
  "y_XmlToken", "y_XmlVersion", "y_ActuateJointType",
  "y_ActuateJoint_ActuateJointType_1_u",
  "y_AxialDistanceFree_XmlDecimal_0", "$@1",
  "y_AxialDistanceScrew_XmlDecimal", "$@2", "y_AxisPoint_PointType_0",
  "y_CRCLCommandInstanceType", "y_CRCLCommandTypeAny",
  "y_CRCLCommand_CRCLCommandType", "y_ChangeRate_XmlDecimal_0", "$@3",
  "y_CommandID_XmlPositiveInteger", "$@4", "y_ConfigureJointReportType",
  "y_ConfigureJointReport_Configure1002", "y_Coordinated_XmlBoolean",
  "$@5", "y_DwellTime_XmlDecimal", "$@7",
  "y_EndPosition_PoseOnlyLocationType", "y_Fraction_FractionType", "$@8",
  "y_I_XmlDecimal", "$@9", "y_J_XmlDecimal", "$@10",
  "y_JointAccel_XmlDecimal_0", "$@11", "y_JointDetailsTypeAny",
  "y_JointDetails_JointDetailsType", "y_JointNumber_XmlPositiveInteger",
  "$@12", "y_JointPosition_XmlDecimal", "$@13",
  "y_JointSpeed_XmlDecimal_0", "$@14", "y_K_XmlDecimal", "$@15",
  "y_ListActuateJoint_ActuateJointType_1_u",
  "y_ListConfigureJointReport_Configure1002",
  "y_ListParameterSetting_ParameterSett1004",
  "y_ListWaypoint_PoseOnlyLocationType_2_u",
  "y_ListWaypoint_PoseOnlyLocationType_2_u_Check", "y_Message_XmlString",
  "$@16", "y_MoveStraight_XmlBoolean", "$@17", "y_Name_XmlID_0", "$@18",
  "y_NumPositions_XmlPositiveInteger", "$@19",
  "y_OrientationStandardDeviation_P1003", "$@20",
  "y_ParameterName_XmlToken", "$@21", "y_ParameterSettingType",
  "y_ParameterSetting_ParameterSett1004", "y_ParameterValue_XmlToken",
  "$@22", "y_PointType", "y_Point_PointType", "y_PoseOnlyLocationType",
  "y_PoseOnlyLocationTypeAny", "y_PoseToleranceType",
  "y_PositionStandardDeviation_Posi1005", "$@23",
  "y_ProgramText_XmlString", "$@24", "y_RefObjectName_XmlIDREF_0", "$@26",
  "y_ReportPosition_XmlBoolean", "$@27",
  "y_ReportTorqueOrForce_XmlBoolean", "$@28",
  "y_ReportVelocity_XmlBoolean", "$@29", "y_ResetAll_XmlBoolean", "$@30",
  "y_RotAccelTypeAny", "y_RotAccel_RotAccelType",
  "y_RotAccel_RotAccelType_0", "y_RotSpeedTypeAny",
  "y_RotSpeed_RotSpeedType", "y_RotSpeed_RotSpeedType_0",
  "y_Setting_FractionType", "$@31", "y_Setting_XmlDecimal", "$@32",
  "y_Setting_XmlDecimal_0", "$@33", "y_StartPosition_PoseOnlyLocation1006",
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
     435,   436,   437,   438,   439,   440,   441,   442,   443,   444,
     445,   446,   447,   448,   449,   450,   451,   452,   453,   454,
     455,   456,   457,   458,   459,   460,   461,   462,   463,   464
};
# endif

#define YYPACT_NINF -353

#define yypact_value_is_default(Yystate) \
  (!!((Yystate) == (-353)))

#define YYTABLE_NINF -1

#define yytable_value_is_error(Yytable_value) \
  0

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
     STATE-NUM.  */
static const yytype_int16 yypact[] =
{
      75,    72,    86,    62,    78,  -353,    79,    27,    81,    85,
      82,  -353,    83,  -353,    21,    71,    89,  -353,    91,    73,
    -353,  -353,  -353,  -117,  -353,    94,    93,    95,    96,    98,
      99,   100,   101,   103,   104,   105,   106,   107,   108,   110,
     111,   112,   113,   114,   115,   116,   117,   118,   119,   120,
     121,   122,   123,   125,   109,  -353,  -353,  -353,  -353,  -353,
    -353,  -353,  -353,  -353,  -353,  -353,  -353,  -353,  -353,  -353,
    -353,  -353,  -353,  -353,  -353,  -353,  -353,  -353,  -353,  -353,
    -353,  -353,  -353,  -353,    60,    21,    21,    21,    21,    21,
      21,    21,    21,    21,    21,    21,    21,    21,    21,    21,
      21,    21,    21,    21,    21,    21,    21,    21,    21,    21,
      21,    21,    21,  -353,  -353,   102,   102,   102,   102,   102,
     102,   102,   102,   102,   102,   102,   102,   102,   102,   102,
     102,   102,   102,   102,   102,   102,   102,   102,   102,   102,
     102,   102,   102,   131,   124,  -353,    39,   126,  -353,  -353,
    -353,    76,    33,    74,    74,  -353,    55,    26,    67,    44,
      37,    30,    37,    31,   127,    67,    52,    51,    34,    41,
      42,    49,  -353,   154,  -353,   124,   157,  -353,   158,  -353,
     161,  -353,    23,   145,   163,    45,   130,   166,  -353,   167,
    -353,   169,    67,  -353,   170,  -353,   171,  -353,   172,  -353,
    -353,   173,  -353,   174,  -353,    67,  -138,  -353,  -132,  -353,
     176,  -353,  -139,  -353,  -140,  -353,   177,  -353,   180,    21,
     179,  -353,  -353,   148,  -353,  -353,    21,   181,  -353,    80,
    -353,   182,   178,  -353,    23,    45,   128,  -353,    23,  -353,
    -353,  -353,    21,   129,  -353,  -353,    21,    77,  -353,  -353,
    -353,   184,   185,   133,  -353,  -353,   188,   189,    97,  -353,
    -353,  -353,   196,   198,    92,  -353,  -353,   199,   201,    69,
    -353,  -353,  -353,  -353,   183,   153,  -353,   206,   205,  -353,
     208,   210,   135,    21,  -353,    21,   193,   211,   195,   206,
     132,   134,  -353,   212,  -353,   187,   210,   215,   141,  -353,
     226,   136,  -353,   230,   231,   206,    21,    21,  -353,    21,
      21,  -353,   232,    21,    21,  -353,    21,    21,  -353,   233,
    -353,   237,   190,  -353,   140,    21,   207,  -353,   213,  -353,
     186,   239,   138,   135,   137,  -353,  -353,   240,   142,   194,
    -353,  -353,  -353,  -353,   162,   143,   247,   192,   150,   249,
     144,   146,   149,   238,   155,   220,   155,   220,   151,   155,
     220,   155,   220,   165,  -353,   270,   224,  -353,   153,  -353,
    -353,  -353,  -353,   272,   197,   138,   274,   147,   208,  -353,
     275,  -353,  -353,   180,  -353,  -353,  -353,   277,  -353,  -353,
    -353,   279,   191,  -353,  -353,  -353,   280,  -353,   281,  -353,
    -353,  -353,  -353,  -353,  -353,  -353,  -353,  -353,   180,  -353,
     -92,  -353,   225,   284,  -353,   182,   160,   197,  -353,   313,
     200,   302,   208,  -353,   248,   317,  -353,   208,  -353,   318,
     202,  -353,  -353,   269,   208,   319,   320,   276,  -353,  -353,
     321,   235,  -353,   241,   328,   251,   327,   203,   160,   208,
    -353,   330,  -353,  -353,   322,   208,  -353,  -353,   259,   317,
     209,   208,  -353,   332,   204,   208,   336,  -353,   285,    21,
      21,  -353,  -353,   340,   250,  -353,  -353,   242,  -353,    21,
     221,   327,   261,   203,   218,   208,  -353,  -353,   236,  -353,
     271,  -353,   217,   208,  -353,   349,  -353,   252,   314,  -353,
     254,   298,   206,  -353,   354,  -353,  -353,   315,  -353,   223,
     355,   287,   261,  -353,   229,   208,  -353,  -353,  -353,   227,
     208,  -353,  -353,  -353,   360,   338,   362,   323,   278,   206,
    -353,   365,   324,  -353,  -353,   366,  -353,   287,  -353,   234,
    -353,   253,   208,  -353,   369,  -353,  -353,   370,  -353,  -353,
     283,   206,  -353,   374,   325,   377,  -353,   127,  -353,  -353,
     244,   208,  -353,   208,  -353,  -353,   291,   208,  -353,   382,
    -353,   297,   385,   273,  -353,   288,   208,   333,   208,  -353,
     347,   208,  -353,  -353,   326,  -140,   292,  -353,   368,  -353,
     348,  -353,   351,   208,  -353,   289,  -132,   282,  -353,  -353,
    -353,   341,  -353,   301,  -139,   299,  -353,  -353,   290,  -138,
     293,  -353,   307,   171,  -353,  -353,   296,  -353
};

  /* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
     Performed when YYTABLE does not specify something else to do.  Zero
     means the default is an error.  */
static const yytype_uint8 yydefact[] =
{
       0,     0,     0,     0,     0,     1,     0,     0,     0,     0,
       0,    13,     0,     3,   100,     0,     0,     4,     0,     0,
       2,    14,   101,     0,    24,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    25,    26,    27,    28,    29,
      30,    31,    32,    33,    34,    35,    36,    37,    38,    39,
      40,    41,    42,    43,    44,    45,    46,    47,    48,    49,
      50,    51,    52,     8,     0,   100,   100,   100,   100,   100,
     100,   100,   100,   100,   100,   100,   100,   100,   100,   100,
     100,   100,   100,   100,   100,   100,   100,   100,   100,   100,
     100,   100,   100,    53,   102,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   210,     0,     0,   213,   214,
     215,     0,   153,     0,     0,   222,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,    57,     0,    87,   209,     0,    89,     0,   212,
       0,   218,     0,    22,     0,     0,     0,     0,   228,     0,
     229,     0,   230,    92,     0,   231,     0,   232,     0,   233,
     234,     0,   235,     0,   236,   237,     0,   238,     0,   239,
       0,   240,     0,   241,     0,   242,     0,   243,     0,   100,
       0,    88,   134,   211,    63,    96,   100,     0,   117,     0,
     118,     0,    17,    98,     0,    95,     0,    94,     0,   221,
     123,   175,   100,     0,    91,   146,   100,     0,   177,   179,
      61,     0,     0,     0,   136,   137,     0,     0,     0,   141,
     142,   181,     0,     0,     0,   163,   164,     0,     0,     0,
     168,   169,   155,    10,     0,     0,    16,     0,     0,    90,
       0,     0,   125,   100,   154,   100,     0,     0,     0,     0,
       0,     0,    93,     0,   220,     0,     0,     0,     0,   111,
       0,   190,   160,     0,     0,     0,   100,   100,   138,   100,
     100,   143,     0,   100,   100,   165,   100,   100,   170,     0,
      58,     0,     0,     5,     0,   100,     0,     7,     0,    11,
       0,     0,   157,   125,     0,    23,    18,     0,     0,     0,
     184,   185,   103,    65,     0,     0,     0,     0,     0,     0,
     195,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,    78,     0,     0,   135,     0,    60,
      64,    97,   126,     0,     0,   157,     0,     0,     0,    20,
       0,   219,    99,     0,   124,   176,   108,     0,   110,   147,
     191,     0,   204,   178,   180,    62,     0,   224,     0,   225,
     226,   227,   182,   244,   245,   246,   247,   156,     0,    80,
       0,    15,     0,     0,   158,     0,     0,     0,   193,     0,
       0,     0,     0,   173,     0,     0,   112,     0,   196,     0,
     186,   148,    66,     0,     0,     0,     0,     0,    75,    76,
       0,     0,     9,     0,     0,     0,     0,     0,     0,     0,
     198,     0,   114,    19,     0,     0,   104,    12,     0,     0,
       0,     0,   205,     0,   200,     0,     0,    79,     0,   100,
     100,    77,   128,     0,     0,   127,     6,     0,   115,   100,
       0,     0,   120,     0,     0,     0,   207,    21,     0,   109,
       0,   192,     0,     0,   187,     0,   119,     0,     0,    81,
     150,    82,     0,   130,     0,    59,   159,     0,   189,     0,
       0,   105,   120,   194,     0,     0,   174,   113,   197,     0,
       0,   201,   149,    67,     0,    54,     0,    72,     0,     0,
     132,     0,     0,   203,   121,     0,   116,   105,   199,     0,
     206,     0,     0,   151,     0,   216,    83,     0,   217,   129,
       0,     0,    68,     0,     0,     0,   106,     0,   208,   188,
       0,     0,    55,     0,    73,   131,     0,     0,    70,     0,
     183,     0,     0,   171,   202,     0,     0,     0,     0,   133,
       0,     0,    85,   122,     0,     0,   144,   152,     0,    84,
       0,    69,     0,     0,   107,     0,     0,   166,    56,    74,
      71,     0,   172,     0,     0,   139,    86,   145,     0,     0,
     161,   167,     0,     0,   223,   140,     0,   162
};

  /* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -353,  -353,  -353,  -353,  -261,  -353,  -319,  -353,  -353,  -352,
     139,   -50,  -353,  -353,   243,  -353,  -353,  -353,  -353,  -353,
    -353,  -353,  -353,  -353,  -353,   175,  -353,  -353,  -353,  -147,
    -353,  -353,  -353,  -353,  -308,  -353,  -353,  -353,  -353,  -353,
    -353,  -353,  -353,  -353,    43,  -353,  -353,  -353,  -353,  -353,
    -353,  -353,  -353,  -353,   255,  -353,  -353,  -353,  -353,   258,
    -353,   -85,  -353,  -353,  -353,  -124,  -353,  -353,  -353,  -353,
    -152,  -353,  -353,    -1,    -2,   214,  -177,  -197,   -95,  -353,
    -353,  -353,    88,  -353,  -353,  -353,  -353,  -353,  -353,  -353,
    -353,  -353,  -190,  -353,  -353,  -174,  -353,  -353,  -353,  -353,
    -301,  -353,  -353,  -353,  -353,  -353,  -353,    48,  -353,   262,
    -353,  -179,  -353,  -353,  -159,  -353,  -353,  -353,  -353,  -353,
    -353,  -353,  -353,  -353,  -353,  -353,  -353,   -54,   216,  -353,
    -353,   -20,  -353,  -353,  -353,  -353,  -353,  -353,  -353,  -353,
    -353,  -353,   -53,  -353,  -353,  -353,  -353,  -353,  -353,  -353,
    -353,  -353,  -353,  -353,  -353,  -353,  -353,  -353,  -353,  -353,
    -353,  -353,  -353,  -353,  -353,  -353,  -353,  -353,  -353,  -353,
    -353,  -353,  -353,  -353,  -353,  -353,  -353,  -353,  -353,  -353,
    -353,  -353,  -353,  -353,  -353,  -353
};

  /* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     2,     9,    13,   324,   477,   328,    84,   443,   274,
     330,   458,     3,   220,   174,   288,   378,   338,   422,   232,
      15,    54,    24,   545,   576,   144,   218,   326,   279,   204,
     305,   179,   280,   239,   399,   466,   532,   567,   554,   581,
     548,   578,   437,   411,   322,   408,   366,   434,   527,   563,
     570,   593,   175,   223,   192,   235,   236,   181,   281,   185,
     289,    19,    25,   294,   383,   536,   572,   347,   425,   243,
     193,   388,   459,   286,   416,   228,   229,   247,   511,   555,
     188,   296,   332,   413,   441,   502,   474,   529,   505,   551,
     177,   277,   253,   207,   610,   258,   209,   597,   195,   300,
     397,   465,   525,   561,   183,   217,   319,   374,   444,   197,
     614,   264,   213,   605,   269,   215,   586,   381,   455,   190,
     297,   199,   303,   202,   304,   211,   312,   480,   237,   464,
     520,   447,   350,   427,   377,   449,   392,   461,   420,   485,
     496,   542,   482,   430,   493,   452,   515,    55,    56,    57,
      58,    59,    60,    61,   438,   439,    62,    63,    64,    65,
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
      26,   424,    10,    27,    11,    28,    29,    30,    31,    32,
     244,   251,   252,    33,   353,    34,    35,    36,    37,   401,
     256,   257,   404,   244,   406,   400,   433,   291,   403,   421,
     405,   295,   262,   263,   267,   268,   435,   436,    38,    39,
      40,    41,    42,    43,    44,    45,    46,    47,    48,    49,
      50,    51,    52,    53,     1,     4,     5,     6,     7,    12,
       8,    14,    16,    17,    18,    20,    21,    22,    83,    85,
      23,    86,    87,   454,    88,    89,    90,    91,   460,    92,
      93,    94,    95,    96,    97,   468,    98,    99,   100,   101,
     102,   103,   104,   105,   106,   107,   108,   109,   110,   111,
     484,   112,   114,   143,   275,   113,   488,   172,   176,   173,
     182,   282,   492,   180,   187,   184,   497,   189,   191,   194,
     196,   198,   201,   206,   208,   210,   212,   298,   216,   214,
     219,   301,   203,   222,   224,   178,   514,   225,   231,   233,
     234,   238,   240,   241,   519,   242,   245,   246,   248,   249,
     250,   278,   261,   272,   273,   318,   284,   283,   285,   302,
     306,   307,   227,   276,   309,   310,   539,   287,   333,   311,
     334,   541,   313,   293,   314,   316,   315,   317,   321,   299,
     323,   325,   327,   320,   329,   335,   337,   336,   342,   345,
     346,   354,   355,   560,   356,   357,   331,   343,   359,   360,
     348,   361,   362,   308,   351,   352,   358,   363,   367,   369,
     368,   528,   575,   364,   577,   372,   379,   365,   580,   373,
     384,   370,   371,   386,   389,   390,   340,   588,   341,   590,
     396,   380,   592,   385,   382,   398,   393,   349,   550,   394,
     376,   402,   395,   407,   601,   387,   409,   410,   414,   391,
     418,   423,   415,   426,   419,   428,   431,   432,   442,   446,
     566,   145,   146,   147,   148,   149,   150,   151,   152,   153,
     154,   155,   156,   157,   158,   159,   160,   161,   162,   163,
     164,   165,   166,   167,   168,   169,   170,   171,   440,   450,
     453,   457,   456,   467,   462,   469,   470,   472,   471,   463,
     473,   475,   476,   479,   429,   478,   486,   489,   494,   491,
     498,   499,   487,   495,   481,   451,   503,   504,   510,   508,
     513,   518,   506,   517,   516,   521,   522,   526,   523,   524,
     530,   534,   531,   533,   535,   538,   543,   544,   546,   540,
     549,   552,   556,   553,   547,   562,   564,   565,   558,   559,
     568,   571,   574,   583,   500,   501,   569,   579,   582,   584,
     585,   589,   587,   591,   507,   596,   598,   604,   599,   600,
     609,   606,   594,   607,   611,   602,   613,   615,   617,   490,
     573,   412,   186,   557,   445,   448,   616,   537,   221,   612,
     205,   375,   603,   417,   200,   608,   595,   509,   483,     0,
     512,     0,     0,     0,     0,   344,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,   290,     0,
       0,   292
};

static const yytype_int16 yycheck[] =
{
      85,    86,    87,    88,    89,    90,    91,    92,    93,    94,
      95,    96,    97,    98,    99,   100,   101,   102,   103,   104,
     105,   106,   107,   108,   109,   110,   111,   112,   289,     6,
     147,   383,     5,   150,     7,   152,   153,   154,   155,   156,
     192,   179,   180,   160,   305,   162,   163,   164,   165,   357,
     182,   183,   360,   205,   362,   356,   408,   234,   359,   378,
     361,   238,   201,   202,   204,   205,   158,   159,   185,   186,
     187,   188,   189,   190,   191,   192,   193,   194,   195,   196,
     197,   198,   199,   200,     9,    13,     0,    25,    10,     8,
      11,     6,    10,    10,    73,    24,     7,     6,     4,     6,
      27,     6,     6,   422,     6,     6,     6,     6,   427,     6,
       6,     6,     6,     6,     6,   434,     6,     6,     6,     6,
       6,     6,     6,     6,     6,     6,     6,     6,     6,     6,
     449,     6,    72,    31,   219,    26,   455,     6,    99,    15,
     107,   226,   461,    67,    89,    71,   465,   121,    81,   105,
     113,   121,   121,   101,   103,   121,   115,   242,   109,   117,
       6,   246,    35,     6,     6,    39,   485,     6,    23,     6,
     125,    41,     6,     6,   493,     6,     6,     6,     6,     6,
       6,    33,     6,     6,     4,   116,   106,     6,     6,   112,
       6,     6,   169,    14,     6,     6,   515,    19,   283,   102,
     285,   520,     6,    75,     6,     6,   114,     6,    55,    80,
       4,     6,     4,    30,     4,    22,    21,     6,     6,     4,
      79,   306,   307,   542,   309,   310,    91,    40,   313,   314,
       4,   316,   317,   100,     4,     4,     4,     4,    98,    32,
     325,   502,   561,     6,   563,     6,     6,    57,   567,   111,
      88,    38,    66,     6,   104,     6,   124,   576,   124,   578,
     105,   119,   581,   120,    70,    45,   120,   131,   529,   120,
     133,   120,    34,   108,   593,    83,     6,    53,     6,   135,
       6,     6,    85,     6,   137,     6,     6,     6,     4,   129,
     551,   116,   117,   118,   119,   120,   121,   122,   123,   124,
     125,   126,   127,   128,   129,   130,   131,   132,   133,   134,
     135,   136,   137,   138,   139,   140,   141,   142,    93,     6,
      18,     4,    74,    54,     6,     6,     6,     6,    52,   127,
      95,    90,     4,     6,   143,    84,     6,    78,     6,   130,
       4,    56,    20,   139,   141,   145,     6,    97,    87,   128,
     132,   134,   110,    82,   118,     6,   104,    59,    44,   105,
       6,     6,    47,   140,    77,   136,     6,    29,     6,   142,
      92,     6,     6,    49,    51,     6,     6,    94,   144,   126,
       6,     4,   138,    86,   469,   470,    61,    96,     6,     4,
     117,    58,   104,    46,   479,   103,    28,   115,    50,    48,
     101,    60,    76,   102,   114,   116,   113,   100,   112,   459,
     557,   368,   154,   537,   415,   417,   613,   512,   175,   609,
     165,   333,   596,   375,   162,   604,   585,   481,   448,    -1,
     483,    -1,    -1,    -1,    -1,   296,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   234,    -1,
      -1,   235
};

  /* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
     symbol of state STATE-NUM.  */
static const yytype_uint16 yystos[] =
{
       0,     9,   211,   222,    13,     0,    25,    10,    11,   212,
       5,     7,     8,   213,     6,   230,    10,    10,    73,   271,
      24,     7,     6,    27,   232,   272,   147,   150,   152,   153,
     154,   155,   156,   160,   162,   163,   164,   165,   185,   186,
     187,   188,   189,   190,   191,   192,   193,   194,   195,   196,
     197,   198,   199,   200,   231,   357,   358,   359,   360,   361,
     362,   363,   366,   367,   368,   369,   370,   376,   377,   378,
     379,   380,   381,   382,   383,   384,   385,   386,   387,   388,
     389,   390,   391,     4,   217,     6,     6,     6,     6,     6,
       6,     6,     6,     6,     6,     6,     6,     6,     6,     6,
       6,     6,     6,     6,     6,     6,     6,     6,     6,     6,
       6,     6,     6,    26,    72,   271,   271,   271,   271,   271,
     271,   271,   271,   271,   271,   271,   271,   271,   271,   271,
     271,   271,   271,   271,   271,   271,   271,   271,   271,   271,
     271,   271,   271,    31,   235,   235,   235,   235,   235,   235,
     235,   235,   235,   235,   235,   235,   235,   235,   235,   235,
     235,   235,   235,   235,   235,   235,   235,   235,   235,   235,
     235,   235,     6,    15,   224,   262,    99,   300,    39,   241,
      67,   267,   107,   314,    71,   269,   269,    89,   290,   121,
     329,    81,   264,   280,   105,   308,   113,   319,   121,   331,
     319,   121,   333,    35,   239,   264,   101,   303,   103,   306,
     121,   335,   115,   322,   117,   325,   109,   315,   236,     6,
     223,   224,     6,   263,     6,     6,     6,   169,   285,   286,
     371,    23,   229,     6,   125,   265,   266,   338,    41,   243,
       6,     6,     6,   279,   280,     6,     6,   287,     6,     6,
       6,   179,   180,   302,   372,   373,   182,   183,   305,   374,
     375,     6,   201,   202,   321,   392,   393,   204,   205,   324,
     394,   395,     6,     4,   219,   271,    14,   301,    33,   238,
     242,   268,   271,     6,   106,     6,   283,    19,   225,   270,
     285,   286,   338,    75,   273,   286,   291,   330,   271,    80,
     309,   271,   112,   332,   334,   240,     6,     6,   100,     6,
       6,   102,   336,     6,     6,   114,     6,     6,   116,   316,
      30,    55,   254,     4,   214,     6,   237,     4,   216,     4,
     220,    91,   292,   271,   271,    22,     6,    21,   227,   214,
     124,   124,     6,    40,   220,     4,    79,   277,     4,   131,
     342,     4,     4,   214,   271,   271,   271,   271,     4,   271,
     271,   271,   271,     4,     6,    57,   256,    98,   271,    32,
      38,    66,     6,   111,   317,   292,   133,   344,   226,     6,
     119,   327,    70,   274,    88,   120,     6,    83,   281,   104,
       6,   135,   346,   120,   120,    34,   105,   310,    45,   244,
     310,   244,   120,   310,   244,   310,   244,   108,   255,     6,
      53,   253,   254,   293,     6,    85,   284,   317,     6,   137,
     348,   216,   228,     6,   219,   278,     6,   343,     6,   143,
     353,     6,     6,   219,   257,   158,   159,   252,   364,   365,
      93,   294,     4,   218,   318,   283,   129,   341,   284,   345,
       6,   145,   355,    18,   216,   328,    74,     4,   221,   282,
     216,   347,     6,   127,   339,   311,   245,    54,   216,     6,
       6,    52,     6,    95,   296,    90,     4,   215,    84,     6,
     337,   141,   352,   341,   216,   349,     6,    20,   216,    78,
     221,   130,   216,   354,     6,   139,   350,   216,     4,    56,
     271,   271,   295,     6,    97,   298,   110,   271,   128,   337,
      87,   288,   352,   132,   216,   356,   118,    82,   134,   216,
     340,     6,   104,    44,   105,   312,    59,   258,   214,   297,
       6,    47,   246,   140,     6,    77,   275,   288,   136,   216,
     142,   216,   351,     6,    29,   233,     6,    51,   250,    92,
     214,   299,     6,    49,   248,   289,     6,   275,   144,   126,
     216,   313,     6,   259,     6,    94,   214,   247,     6,    61,
     260,     4,   276,   239,   138,   216,   234,   216,   251,    96,
     216,   249,     6,    86,     4,   117,   326,   104,   216,    58,
     216,    46,   216,   261,    76,   324,   103,   307,    28,    50,
      48,   216,   116,   305,   115,   323,    60,   102,   321,   101,
     304,   114,   302,   113,   320,   100,   287,   112
};

  /* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint16 yyr1[] =
{
       0,   210,   211,   212,   213,   214,   215,   216,   217,   218,
     219,   220,   221,   222,   222,   223,   224,   225,   226,   225,
     228,   227,   229,   229,   230,   231,   231,   231,   231,   231,
     231,   231,   231,   231,   231,   231,   231,   231,   231,   231,
     231,   231,   231,   231,   231,   231,   231,   231,   231,   231,
     231,   231,   231,   232,   233,   234,   233,   236,   235,   237,
     238,   240,   239,   242,   241,   243,   245,   244,   247,   246,
     249,   248,   250,   251,   250,   252,   252,   253,   255,   254,
     257,   256,   258,   259,   258,   261,   260,   262,   262,   263,
     263,   264,   264,   265,   265,   266,   268,   267,   270,   269,
     271,   272,   271,   274,   273,   275,   276,   275,   278,   277,
     279,   280,   282,   281,   283,   284,   285,   286,   286,   287,
     288,   289,   288,   291,   290,   292,   293,   292,   295,   294,
     297,   296,   299,   298,   301,   300,   302,   302,   303,   304,
     304,   305,   305,   306,   307,   307,   309,   308,   311,   310,
     312,   313,   312,   314,   314,   316,   315,   317,   318,   317,
     319,   320,   320,   321,   321,   322,   323,   323,   324,   324,
     325,   326,   326,   328,   327,   330,   329,   332,   331,   334,
     333,   336,   335,   337,   338,   338,   339,   340,   339,   341,
     342,   343,   342,   345,   344,   346,   347,   346,   349,   348,
     350,   351,   350,   352,   353,   354,   353,   356,   355,   357,
     358,   359,   360,   361,   362,   363,   364,   365,   366,   367,
     368,   369,   370,   371,   372,   373,   374,   375,   376,   377,
     378,   379,   380,   381,   382,   383,   384,   385,   386,   387,
     388,   389,   390,   391,   392,   393,   394,   395
};

  /* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     5,     2,     2,     1,     1,     1,     1,     1,
       1,     1,     1,     4,     6,     5,     3,     0,     0,     5,
       0,     5,     0,     3,     3,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     3,     0,     0,     5,     0,     5,     6,
       3,     0,     5,     0,     5,     3,     0,     5,     0,     5,
       0,     5,     0,     0,     5,     1,     1,     3,     0,     5,
       0,     5,     0,     0,     5,     0,     5,     1,     2,     0,
       2,     2,     1,     2,     1,     1,     0,     5,     0,     5,
       0,     0,     5,     0,     5,     0,     0,     5,     0,     5,
       4,     3,     0,     5,     5,     3,     9,     1,     1,     7,
       0,     0,     5,     0,     5,     0,     0,     5,     0,     5,
       0,     5,     0,     5,     0,     5,     1,     1,     3,     0,
       3,     1,     1,     3,     0,     3,     0,     5,     0,     5,
       0,     0,     5,     0,     3,     0,     5,     0,     0,     5,
       3,     0,     3,     1,     1,     3,     0,     3,     1,     1,
       3,     0,     3,     0,     5,     0,     5,     0,     5,     0,
       5,     0,     5,     5,     3,     3,     0,     0,     5,     3,
       0,     0,     5,     0,     5,     0,     0,     5,     0,     5,
       0,     0,     5,     3,     0,     0,     5,     0,     5,     5,
       4,     6,     5,     4,     4,     4,     5,     5,     5,     9,
       7,     6,     4,    16,     4,     4,     4,     4,     5,     5,
       5,     5,     5,     5,     5,     5,     5,     5,     5,     5,
       5,     5,     5,     5,     4,     4,     4,     4
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

    {(yyval.CRCLCommandInstanceTypeVal) = new CRCLCommandInstanceType((yyvsp[-1].XmlIDVal), (yyvsp[0].CRCLCommandTypeVal));}

    break;

  case 25:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].ActuateJointsTypeVal);}

    break;

  case 26:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].CloseToolChangerTypeVal);}

    break;

  case 27:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].ConfigureJointReportsTypeVal);}

    break;

  case 28:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].DwellTypeVal);}

    break;

  case 29:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].EndCanonTypeVal);}

    break;

  case 30:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].GetStatusTypeVal);}

    break;

  case 31:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].InitCanonTypeVal);}

    break;

  case 32:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].MessageTypeVal);}

    break;

  case 33:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].MoveScrewTypeVal);}

    break;

  case 34:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].MoveThroughToTypeVal);}

    break;

  case 35:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].MoveToTypeVal);}

    break;

  case 36:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].OpenToolChangerTypeVal);}

    break;

  case 37:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].RunProgramTypeVal);}

    break;

  case 38:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetAngleUnitsTypeVal);}

    break;

  case 39:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetEndEffectorParametersTypeVal);}

    break;

  case 40:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetEndEffectorTypeVal);}

    break;

  case 41:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetEndPoseToleranceTypeVal);}

    break;

  case 42:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetForceUnitsTypeVal);}

    break;

  case 43:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetIntermediatePoseToleranceTypeVal);}

    break;

  case 44:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetLengthUnitsTypeVal);}

    break;

  case 45:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetMotionCoordinationTypeVal);}

    break;

  case 46:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetRobotParametersTypeVal);}

    break;

  case 47:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetRotAccelTypeVal);}

    break;

  case 48:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetRotSpeedTypeVal);}

    break;

  case 49:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetTorqueUnitsTypeVal);}

    break;

  case 50:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetTransAccelTypeVal);}

    break;

  case 51:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].SetTransSpeedTypeVal);}

    break;

  case 52:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[0].StopMotionTypeVal);}

    break;

  case 53:

    {(yyval.CRCLCommandTypeVal) = (yyvsp[-1].CRCLCommandTypeVal);}

    break;

  case 54:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 55:

    {yyReadData = 1;}

    break;

  case 56:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 57:

    {yyReadData = 1;}

    break;

  case 58:

    {(yyval.XmlPositiveIntegerVal) = (yyvsp[-1].XmlPositiveIntegerVal);}

    break;

  case 59:

    {(yyval.ConfigureJointReportTypeVal) = new ConfigureJointReportType((yyvsp[-4].XmlIDVal), (yyvsp[-3].XmlPositiveIntegerVal), (yyvsp[-2].XmlBooleanVal), (yyvsp[-1].XmlBooleanVal), (yyvsp[0].XmlBooleanVal));}

    break;

  case 60:

    {(yyval.ConfigureJointReportTypeVal) = (yyvsp[-1].ConfigureJointReportTypeVal);}

    break;

  case 61:

    {yyReadData = 1;}

    break;

  case 62:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

    break;

  case 63:

    {yyReadData = 1;}

    break;

  case 64:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 65:

    {(yyval.PoseOnlyLocationTypeVal) = (yyvsp[-1].PoseOnlyLocationTypeVal);}

    break;

  case 66:

    {yyReadData = 1;}

    break;

  case 67:

    {(yyval.FractionTypeVal) = new FractionType((yyvsp[-1].sVal));
	   if ((yyval.FractionTypeVal)->bad)
	     yyerror("bad Fraction value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 68:

    {yyReadData = 1;}

    break;

  case 69:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 70:

    {yyReadData = 1;}

    break;

  case 71:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 72:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 73:

    {yyReadData = 1;}

    break;

  case 74:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 75:

    {(yyval.JointDetailsTypeVal) = (yyvsp[0].JointForceTorqueTypeVal);}

    break;

  case 76:

    {(yyval.JointDetailsTypeVal) = (yyvsp[0].JointSpeedAccelTypeVal);}

    break;

  case 77:

    {(yyval.JointDetailsTypeVal) = (yyvsp[-1].JointDetailsTypeVal);}

    break;

  case 78:

    {yyReadData = 1;}

    break;

  case 79:

    {(yyval.XmlPositiveIntegerVal) = (yyvsp[-1].XmlPositiveIntegerVal);}

    break;

  case 80:

    {yyReadData = 1;}

    break;

  case 81:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 82:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 83:

    {yyReadData = 1;}

    break;

  case 84:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 85:

    {yyReadData = 1;}

    break;

  case 86:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 87:

    {(yyval.ListActuateJointTypeVal) = new std::list<ActuateJointType *>;
	   (yyval.ListActuateJointTypeVal)->push_back((yyvsp[0].ActuateJointTypeVal));}

    break;

  case 88:

    {(yyval.ListActuateJointTypeVal) = (yyvsp[-1].ListActuateJointTypeVal);
	   (yyval.ListActuateJointTypeVal)->push_back((yyvsp[0].ActuateJointTypeVal));}

    break;

  case 89:

    {(yyval.ListConfigureJointReportTypeVal) = new std::list<ConfigureJointReportType *>;}

    break;

  case 90:

    {(yyval.ListConfigureJointReportTypeVal) = (yyvsp[-1].ListConfigureJointReportTypeVal);
	   (yyval.ListConfigureJointReportTypeVal)->push_back((yyvsp[0].ConfigureJointReportTypeVal));}

    break;

  case 91:

    {(yyval.ListParameterSettingTypeVal) = (yyvsp[-1].ListParameterSettingTypeVal);
	   (yyval.ListParameterSettingTypeVal)->push_back((yyvsp[0].ParameterSettingTypeVal));}

    break;

  case 92:

    {(yyval.ListParameterSettingTypeVal) = new std::list<ParameterSettingType *>;
	   (yyval.ListParameterSettingTypeVal)->push_back((yyvsp[0].ParameterSettingTypeVal));}

    break;

  case 93:

    {(yyval.ListPoseOnlyLocationTypeVal) = (yyvsp[-1].ListPoseOnlyLocationTypeVal);
	   (yyval.ListPoseOnlyLocationTypeVal)->push_back((yyvsp[0].PoseOnlyLocationTypeVal));}

    break;

  case 94:

    {(yyval.ListPoseOnlyLocationTypeVal) = new std::list<PoseOnlyLocationType *>;
	   (yyval.ListPoseOnlyLocationTypeVal)->push_back((yyvsp[0].PoseOnlyLocationTypeVal));}

    break;

  case 95:

    {(yyval.ListPoseOnlyLocationTypeVal) = (yyvsp[0].ListPoseOnlyLocationTypeVal);
	   if ((yyvsp[0].ListPoseOnlyLocationTypeVal)->size() < 2)
	     yyerror("must be at least 2 Waypoints");
	  }

    break;

  case 96:

    {yyReadData = 1;}

    break;

  case 97:

    {(yyval.XmlStringVal) = (yyvsp[-1].XmlStringVal);}

    break;

  case 98:

    {yyReadData = 1;}

    break;

  case 99:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

    break;

  case 100:

    {(yyval.XmlIDVal) = 0;}

    break;

  case 101:

    {yyReadData = 1;}

    break;

  case 102:

    {(yyval.XmlIDVal) = (yyvsp[-1].XmlIDVal);}

    break;

  case 103:

    {yyReadData = 1;}

    break;

  case 104:

    {(yyval.XmlPositiveIntegerVal) = (yyvsp[-1].XmlPositiveIntegerVal);}

    break;

  case 105:

    {(yyval.PositiveDecimalTypeVal) = 0;}

    break;

  case 106:

    {yyReadData = 1;}

    break;

  case 107:

    {(yyval.PositiveDecimalTypeVal) = new PositiveDecimalType((yyvsp[-1].sVal));
	   if ((yyval.PositiveDecimalTypeVal)->bad)
	     yyerror("bad OrientationStandardDeviation value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 108:

    {yyReadData = 1;}

    break;

  case 109:

    {(yyval.XmlTokenVal) = (yyvsp[-1].XmlTokenVal);}

    break;

  case 110:

    {(yyval.ParameterSettingTypeVal) = new ParameterSettingType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlTokenVal), (yyvsp[0].XmlTokenVal));}

    break;

  case 111:

    {(yyval.ParameterSettingTypeVal) = (yyvsp[-1].ParameterSettingTypeVal);}

    break;

  case 112:

    {yyReadData = 1;}

    break;

  case 113:

    {(yyval.XmlTokenVal) = (yyvsp[-1].XmlTokenVal);}

    break;

  case 114:

    {(yyval.PointTypeVal) = new PointType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));}

    break;

  case 115:

    {(yyval.PointTypeVal) = (yyvsp[-1].PointTypeVal);}

    break;

  case 116:

    {(yyval.PoseOnlyLocationTypeVal) = new PoseOnlyLocationType((yyvsp[-7].XmlIDVal), (yyvsp[-6].XmlIDREFVal), (yyvsp[-5].XmlDateTimeVal), (yyvsp[-4].PointTypeVal), (yyvsp[-3].VectorTypeVal), (yyvsp[-2].VectorTypeVal), (yyvsp[-1].PositiveDecimalTypeVal), (yyvsp[0].PositiveDecimalTypeVal));}

    break;

  case 117:

    {(yyval.PoseOnlyLocationTypeVal) = (yyvsp[0].PoseOnlyLocationTypeVal);}

    break;

  case 118:

    {(yyval.PoseOnlyLocationTypeVal) = (yyvsp[0].PoseAndSetTypeVal);}

    break;

  case 119:

    {(yyval.PoseToleranceTypeVal) = new PoseToleranceType((yyvsp[-5].XmlIDVal), (yyvsp[-4].XmlDecimalVal), (yyvsp[-3].XmlDecimalVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));}

    break;

  case 120:

    {(yyval.PositiveDecimalTypeVal) = 0;}

    break;

  case 121:

    {yyReadData = 1;}

    break;

  case 122:

    {(yyval.PositiveDecimalTypeVal) = new PositiveDecimalType((yyvsp[-1].sVal));
	   if ((yyval.PositiveDecimalTypeVal)->bad)
	     yyerror("bad PositionStandardDeviation value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 123:

    {yyReadData = 1;}

    break;

  case 124:

    {(yyval.XmlStringVal) = (yyvsp[-1].XmlStringVal);}

    break;

  case 125:

    {(yyval.XmlIDREFVal) = 0;}

    break;

  case 126:

    {yyReadData = 1;}

    break;

  case 127:

    {(yyval.XmlIDREFVal) = (yyvsp[-1].XmlIDREFVal);}

    break;

  case 128:

    {yyReadData = 1;}

    break;

  case 129:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

    break;

  case 130:

    {yyReadData = 1;}

    break;

  case 131:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

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

    {(yyval.RotAccelTypeVal) = (yyvsp[0].RotAccelAbsoluteTypeVal);}

    break;

  case 137:

    {(yyval.RotAccelTypeVal) = (yyvsp[0].RotAccelRelativeTypeVal);}

    break;

  case 138:

    {(yyval.RotAccelTypeVal) = (yyvsp[-1].RotAccelTypeVal);}

    break;

  case 139:

    {(yyval.RotAccelTypeVal) = 0;}

    break;

  case 140:

    {(yyval.RotAccelTypeVal) = (yyvsp[-1].RotAccelTypeVal);}

    break;

  case 141:

    {(yyval.RotSpeedTypeVal) = (yyvsp[0].RotSpeedAbsoluteTypeVal);}

    break;

  case 142:

    {(yyval.RotSpeedTypeVal) = (yyvsp[0].RotSpeedRelativeTypeVal);}

    break;

  case 143:

    {(yyval.RotSpeedTypeVal) = (yyvsp[-1].RotSpeedTypeVal);}

    break;

  case 144:

    {(yyval.RotSpeedTypeVal) = 0;}

    break;

  case 145:

    {(yyval.RotSpeedTypeVal) = (yyvsp[-1].RotSpeedTypeVal);}

    break;

  case 146:

    {yyReadData = 1;}

    break;

  case 147:

    {(yyval.FractionTypeVal) = new FractionType((yyvsp[-1].sVal));
	   if ((yyval.FractionTypeVal)->bad)
	     yyerror("bad Setting value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 148:

    {yyReadData = 1;}

    break;

  case 149:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 150:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 151:

    {yyReadData = 1;}

    break;

  case 152:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 153:

    {(yyval.PoseOnlyLocationTypeVal) = 0;}

    break;

  case 154:

    {(yyval.PoseOnlyLocationTypeVal) = (yyvsp[-1].PoseOnlyLocationTypeVal);}

    break;

  case 155:

    {yyReadData = 1;}

    break;

  case 156:

    {(yyval.StopConditionEnumTypeVal) = new StopConditionEnumType((yyvsp[-1].sVal));
	   if ((yyval.StopConditionEnumTypeVal)->bad)
	     yyerror("bad StopCondition value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 157:

    {(yyval.XmlDateTimeVal) = 0;}

    break;

  case 158:

    {yyReadData = 1;}

    break;

  case 159:

    {(yyval.XmlDateTimeVal) = (yyvsp[-1].XmlDateTimeVal);}

    break;

  case 160:

    {(yyval.PoseToleranceTypeVal) = (yyvsp[-1].PoseToleranceTypeVal);}

    break;

  case 161:

    {(yyval.PoseToleranceTypeVal) = 0;}

    break;

  case 162:

    {(yyval.PoseToleranceTypeVal) = (yyvsp[-1].PoseToleranceTypeVal);}

    break;

  case 163:

    {(yyval.TransAccelTypeVal) = (yyvsp[0].TransAccelAbsoluteTypeVal);}

    break;

  case 164:

    {(yyval.TransAccelTypeVal) = (yyvsp[0].TransAccelRelativeTypeVal);}

    break;

  case 165:

    {(yyval.TransAccelTypeVal) = (yyvsp[-1].TransAccelTypeVal);}

    break;

  case 166:

    {(yyval.TransAccelTypeVal) = 0;}

    break;

  case 167:

    {(yyval.TransAccelTypeVal) = (yyvsp[-1].TransAccelTypeVal);}

    break;

  case 168:

    {(yyval.TransSpeedTypeVal) = (yyvsp[0].TransSpeedAbsoluteTypeVal);}

    break;

  case 169:

    {(yyval.TransSpeedTypeVal) = (yyvsp[0].TransSpeedRelativeTypeVal);}

    break;

  case 170:

    {(yyval.TransSpeedTypeVal) = (yyvsp[-1].TransSpeedTypeVal);}

    break;

  case 171:

    {(yyval.TransSpeedTypeVal) = 0;}

    break;

  case 172:

    {(yyval.TransSpeedTypeVal) = (yyvsp[-1].TransSpeedTypeVal);}

    break;

  case 173:

    {yyReadData = 1;}

    break;

  case 174:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 175:

    {yyReadData = 1;}

    break;

  case 176:

    {(yyval.AngleUnitEnumTypeVal) = new AngleUnitEnumType((yyvsp[-1].sVal));
	   if ((yyval.AngleUnitEnumTypeVal)->bad)
	     yyerror("bad UnitName value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 177:

    {yyReadData = 1;}

    break;

  case 178:

    {(yyval.ForceUnitEnumTypeVal) = new ForceUnitEnumType((yyvsp[-1].sVal));
	   if ((yyval.ForceUnitEnumTypeVal)->bad)
	     yyerror("bad UnitName value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 179:

    {yyReadData = 1;}

    break;

  case 180:

    {(yyval.LengthUnitEnumTypeVal) = new LengthUnitEnumType((yyvsp[-1].sVal));
	   if ((yyval.LengthUnitEnumTypeVal)->bad)
	     yyerror("bad UnitName value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 181:

    {yyReadData = 1;}

    break;

  case 182:

    {(yyval.TorqueUnitEnumTypeVal) = new TorqueUnitEnumType((yyvsp[-1].sVal));
	   if ((yyval.TorqueUnitEnumTypeVal)->bad)
	     yyerror("bad UnitName value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 183:

    {(yyval.VectorTypeVal) = new VectorType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));}

    break;

  case 184:

    {(yyval.PoseOnlyLocationTypeVal) = (yyvsp[-1].PoseOnlyLocationTypeVal);}

    break;

  case 185:

    {(yyval.PoseOnlyLocationTypeVal) = (yyvsp[-1].PoseOnlyLocationTypeVal);}

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

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 196:

    {yyReadData = 1;}

    break;

  case 197:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 198:

    {yyReadData = 1;}

    break;

  case 199:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 200:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 201:

    {yyReadData = 1;}

    break;

  case 202:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 203:

    {(yyval.VectorTypeVal) = (yyvsp[-1].VectorTypeVal);}

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

    {yyReadData = 1;}

    break;

  case 208:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 209:

    {(yyval.ActuateJointsTypeVal) = new ActuateJointsType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].ListActuateJointTypeVal));
	   (yyval.ActuateJointsTypeVal)->printTypp = true;
	  }

    break;

  case 210:

    {(yyval.CloseToolChangerTypeVal) = new CloseToolChangerType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlPositiveIntegerVal));
	   (yyval.CloseToolChangerTypeVal)->printTypp = true;
	  }

    break;

  case 211:

    {(yyval.ConfigureJointReportsTypeVal) = new ConfigureJointReportsType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlPositiveIntegerVal), (yyvsp[-1].XmlBooleanVal), (yyvsp[0].ListConfigureJointReportTypeVal));
	   (yyval.ConfigureJointReportsTypeVal)->printTypp = true;
	  }

    break;

  case 212:

    {(yyval.DwellTypeVal) = new DwellType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.DwellTypeVal)->printTypp = true;
	  }

    break;

  case 213:

    {(yyval.EndCanonTypeVal) = new EndCanonType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlPositiveIntegerVal));
	   (yyval.EndCanonTypeVal)->printTypp = true;
	  }

    break;

  case 214:

    {(yyval.GetStatusTypeVal) = new GetStatusType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlPositiveIntegerVal));
	   (yyval.GetStatusTypeVal)->printTypp = true;
	  }

    break;

  case 215:

    {(yyval.InitCanonTypeVal) = new InitCanonType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlPositiveIntegerVal));
	   (yyval.InitCanonTypeVal)->printTypp = true;
	  }

    break;

  case 216:

    {(yyval.JointForceTorqueTypeVal) = new JointForceTorqueType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.JointForceTorqueTypeVal)->printTypp = true;
	  }

    break;

  case 217:

    {(yyval.JointSpeedAccelTypeVal) = new JointSpeedAccelType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.JointSpeedAccelTypeVal)->printTypp = true;
	  }

    break;

  case 218:

    {(yyval.MessageTypeVal) = new MessageType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].XmlStringVal));
	   (yyval.MessageTypeVal)->printTypp = true;
	  }

    break;

  case 219:

    {(yyval.MoveScrewTypeVal) = new MoveScrewType((yyvsp[-6].XmlIDVal), (yyvsp[-5].XmlPositiveIntegerVal), (yyvsp[-4].PoseOnlyLocationTypeVal), (yyvsp[-3].PointTypeVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.MoveScrewTypeVal)->printTypp = true;
	  }

    break;

  case 220:

    {(yyval.MoveThroughToTypeVal) = new MoveThroughToType((yyvsp[-4].XmlIDVal), (yyvsp[-3].XmlPositiveIntegerVal), (yyvsp[-2].XmlBooleanVal), (yyvsp[-1].ListPoseOnlyLocationTypeVal), (yyvsp[0].XmlPositiveIntegerVal));
	   (yyval.MoveThroughToTypeVal)->printTypp = true;
	  }

    break;

  case 221:

    {(yyval.MoveToTypeVal) = new MoveToType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlPositiveIntegerVal), (yyvsp[-1].XmlBooleanVal), (yyvsp[0].PoseOnlyLocationTypeVal));
	   (yyval.MoveToTypeVal)->printTypp = true;
	  }

    break;

  case 222:

    {(yyval.OpenToolChangerTypeVal) = new OpenToolChangerType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlPositiveIntegerVal));
	   (yyval.OpenToolChangerTypeVal)->printTypp = true;
	  }

    break;

  case 223:

    {(yyval.PoseAndSetTypeVal) = new PoseAndSetType((yyvsp[-13].XmlIDVal), (yyvsp[-12].XmlIDREFVal), (yyvsp[-11].XmlDateTimeVal), (yyvsp[-10].PointTypeVal), (yyvsp[-9].VectorTypeVal), (yyvsp[-8].VectorTypeVal), (yyvsp[-7].PositiveDecimalTypeVal), (yyvsp[-6].PositiveDecimalTypeVal), (yyvsp[-5].XmlBooleanVal), (yyvsp[-4].TransSpeedTypeVal), (yyvsp[-3].RotSpeedTypeVal), (yyvsp[-2].TransAccelTypeVal), (yyvsp[-1].RotAccelTypeVal), (yyvsp[0].PoseToleranceTypeVal));
	   (yyval.PoseAndSetTypeVal)->printTypp = true;
	  }

    break;

  case 224:

    {(yyval.RotAccelAbsoluteTypeVal) = new RotAccelAbsoluteType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.RotAccelAbsoluteTypeVal)->printTypp = true;
	  }

    break;

  case 225:

    {(yyval.RotAccelRelativeTypeVal) = new RotAccelRelativeType((yyvsp[-1].XmlIDVal), (yyvsp[0].FractionTypeVal));
	   (yyval.RotAccelRelativeTypeVal)->printTypp = true;
	  }

    break;

  case 226:

    {(yyval.RotSpeedAbsoluteTypeVal) = new RotSpeedAbsoluteType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.RotSpeedAbsoluteTypeVal)->printTypp = true;
	  }

    break;

  case 227:

    {(yyval.RotSpeedRelativeTypeVal) = new RotSpeedRelativeType((yyvsp[-1].XmlIDVal), (yyvsp[0].FractionTypeVal));
	   (yyval.RotSpeedRelativeTypeVal)->printTypp = true;
	  }

    break;

  case 228:

    {(yyval.RunProgramTypeVal) = new RunProgramType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].XmlStringVal));
	   (yyval.RunProgramTypeVal)->printTypp = true;
	  }

    break;

  case 229:

    {(yyval.SetAngleUnitsTypeVal) = new SetAngleUnitsType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].AngleUnitEnumTypeVal));
	   (yyval.SetAngleUnitsTypeVal)->printTypp = true;
	  }

    break;

  case 230:

    {(yyval.SetEndEffectorParametersTypeVal) = new SetEndEffectorParametersType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].ListParameterSettingTypeVal));
	   (yyval.SetEndEffectorParametersTypeVal)->printTypp = true;
	  }

    break;

  case 231:

    {(yyval.SetEndEffectorTypeVal) = new SetEndEffectorType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].FractionTypeVal));
	   (yyval.SetEndEffectorTypeVal)->printTypp = true;
	  }

    break;

  case 232:

    {(yyval.SetEndPoseToleranceTypeVal) = new SetEndPoseToleranceType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].PoseToleranceTypeVal));
	   (yyval.SetEndPoseToleranceTypeVal)->printTypp = true;
	  }

    break;

  case 233:

    {(yyval.SetForceUnitsTypeVal) = new SetForceUnitsType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].ForceUnitEnumTypeVal));
	   (yyval.SetForceUnitsTypeVal)->printTypp = true;
	  }

    break;

  case 234:

    {(yyval.SetIntermediatePoseToleranceTypeVal) = new SetIntermediatePoseToleranceType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].PoseToleranceTypeVal));
	   (yyval.SetIntermediatePoseToleranceTypeVal)->printTypp = true;
	  }

    break;

  case 235:

    {(yyval.SetLengthUnitsTypeVal) = new SetLengthUnitsType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].LengthUnitEnumTypeVal));
	   (yyval.SetLengthUnitsTypeVal)->printTypp = true;
	  }

    break;

  case 236:

    {(yyval.SetMotionCoordinationTypeVal) = new SetMotionCoordinationType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].XmlBooleanVal));
	   (yyval.SetMotionCoordinationTypeVal)->printTypp = true;
	  }

    break;

  case 237:

    {(yyval.SetRobotParametersTypeVal) = new SetRobotParametersType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].ListParameterSettingTypeVal));
	   (yyval.SetRobotParametersTypeVal)->printTypp = true;
	  }

    break;

  case 238:

    {(yyval.SetRotAccelTypeVal) = new SetRotAccelType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].RotAccelTypeVal));
	   (yyval.SetRotAccelTypeVal)->printTypp = true;
	  }

    break;

  case 239:

    {(yyval.SetRotSpeedTypeVal) = new SetRotSpeedType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].RotSpeedTypeVal));
	   (yyval.SetRotSpeedTypeVal)->printTypp = true;
	  }

    break;

  case 240:

    {(yyval.SetTorqueUnitsTypeVal) = new SetTorqueUnitsType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].TorqueUnitEnumTypeVal));
	   (yyval.SetTorqueUnitsTypeVal)->printTypp = true;
	  }

    break;

  case 241:

    {(yyval.SetTransAccelTypeVal) = new SetTransAccelType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].TransAccelTypeVal));
	   (yyval.SetTransAccelTypeVal)->printTypp = true;
	  }

    break;

  case 242:

    {(yyval.SetTransSpeedTypeVal) = new SetTransSpeedType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].TransSpeedTypeVal));
	   (yyval.SetTransSpeedTypeVal)->printTypp = true;
	  }

    break;

  case 243:

    {(yyval.StopMotionTypeVal) = new StopMotionType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].StopConditionEnumTypeVal));
	   (yyval.StopMotionTypeVal)->printTypp = true;
	  }

    break;

  case 244:

    {(yyval.TransAccelAbsoluteTypeVal) = new TransAccelAbsoluteType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.TransAccelAbsoluteTypeVal)->printTypp = true;
	  }

    break;

  case 245:

    {(yyval.TransAccelRelativeTypeVal) = new TransAccelRelativeType((yyvsp[-1].XmlIDVal), (yyvsp[0].FractionTypeVal));
	   (yyval.TransAccelRelativeTypeVal)->printTypp = true;
	  }

    break;

  case 246:

    {(yyval.TransSpeedAbsoluteTypeVal) = new TransSpeedAbsoluteType((yyvsp[-1].XmlIDVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.TransSpeedAbsoluteTypeVal)->printTypp = true;
	  }

    break;

  case 247:

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
