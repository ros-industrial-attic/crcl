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
#include "crcl/CRCLStatusClasses.hh"

#define YYERROR_VERBOSE
#define YYDEBUG 1

CRCLStatusFile * CRCLStatusTree; // the parse tree

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
   by #include "CRCLStatusYACC.hh".  */
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
#define YYLAST   213

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  128
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  97
/* YYNRULES -- Number of rules.  */
#define YYNRULES  120
/* YYNSTATES -- Number of states.  */
#define YYNSTATES  284

/* YYTRANSLATE[YYX] -- Symbol number corresponding to YYX as returned
   by yylex, with out-of-bounds checking.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   382

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
     125,   126,   127
};

#if YYDEBUG
  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,   267,   267,   277,   282,   289,   298,   307,   316,   325,
     334,   343,   352,   370,   376,   389,   394,   401,   401,   407,
     407,   417,   423,   435,   436,   436,   443,   444,   444,   455,
     456,   456,   463,   464,   464,   475,   476,   476,   483,   484,
     484,   494,   499,   499,   505,   507,   509,   515,   516,   521,
     521,   526,   526,   532,   532,   537,   537,   544,   545,   545,
     551,   558,   563,   569,   570,   576,   577,   577,   584,   585,
     585,   591,   591,   596,   601,   604,   616,   622,   623,   623,
     629,   630,   630,   640,   646,   651,   659,   666,   667,   672,
     678,   679,   679,   696,   697,   697,   703,   703,   709,   709,
     716,   717,   717,   723,   730,   731,   741,   747,   753,   754,
     759,   764,   764,   769,   769,   774,   779,   779,   784,   792,
     803
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
  "ANGULARVELOCITYEND", "ANGULARVELOCITYSTART", "CRCLSTATUSEND",
  "CRCLSTATUSSTART", "COMMANDIDEND", "COMMANDIDSTART", "COMMANDSTATEEND",
  "COMMANDSTATESTART", "COMMANDSTATUSEND", "COMMANDSTATUSSTART",
  "DESCRIPTIONEND", "DESCRIPTIONSTART", "FINGER1FORCEEND",
  "FINGER1FORCESTART", "FINGER1POSITIONEND", "FINGER1POSITIONSTART",
  "FINGER2FORCEEND", "FINGER2FORCESTART", "FINGER2POSITIONEND",
  "FINGER2POSITIONSTART", "FINGER3FORCEEND", "FINGER3FORCESTART",
  "FINGER3POSITIONEND", "FINGER3POSITIONSTART", "FORCEEND", "FORCESTART",
  "GRIPPERNAMEEND", "GRIPPERNAMESTART", "GRIPPERSTATUSEND",
  "GRIPPERSTATUSSTART", "IEND", "ISTART", "ISPOWEREDEND", "ISPOWEREDSTART",
  "JEND", "JSTART", "JOINTNUMBEREND", "JOINTNUMBERSTART",
  "JOINTPOSITIONEND", "JOINTPOSITIONSTART", "JOINTSTATUSEND",
  "JOINTSTATUSSTART", "JOINTSTATUSESEND", "JOINTSTATUSESSTART",
  "JOINTTORQUEORFORCEEND", "JOINTTORQUEORFORCESTART", "JOINTVELOCITYEND",
  "JOINTVELOCITYSTART", "KEND", "KSTART", "LINEARVELOCITYEND",
  "LINEARVELOCITYSTART", "LOWERRIGHTEND", "LOWERRIGHTSTART", "MOMENTEND",
  "MOMENTSTART", "NAMEEND", "NAMESTART", "ORIENTATIONSTANDARDDEVIATIONEND",
  "ORIENTATIONSTANDARDDEVIATIONSTART", "POINTEND", "POINTSTART",
  "POSESTATUSEND", "POSESTATUSSTART", "POSEEND", "POSESTART",
  "POSITIONSTANDARDDEVIATIONEND", "POSITIONSTANDARDDEVIATIONSTART",
  "REFOBJECTNAMEEND", "REFOBJECTNAMESTART", "SEPARATIONEND",
  "SEPARATIONSTART", "STATUSIDEND", "STATUSIDSTART", "TIMESTAMPEND",
  "TIMESTAMPSTART", "TWISTEND", "TWISTSTART", "UPPERLEFTEND",
  "UPPERLEFTSTART", "WRENCHEND", "WRENCHSTART", "XAXISEND", "XAXISSTART",
  "XEND", "XSTART", "YEND", "YSTART", "ZAXISEND", "ZAXISSTART", "ZEND",
  "ZSTART", "CRCLSTATUSTYPEDECL", "COMMANDSTATUSTYPEDECL",
  "GRIPPERSTATUSTYPEDECL", "JOINTSTATUSTYPEDECL", "JOINTSTATUSESTYPEDECL",
  "PARALLELGRIPPERSTATUSTYPEDECL", "PHYSICALLOCATIONTYPEDECL",
  "POINTTYPEDECL", "POSELOCATIONINTYPEDECL", "POSELOCATIONONTYPEDECL",
  "POSELOCATIONTYPEDECL", "POSEONLYLOCATIONTYPEDECL", "POSESTATUSTYPEDECL",
  "REGIONOFINTERESTTYPEDECL", "RELATIVELOCATIONINTYPEDECL",
  "RELATIVELOCATIONONTYPEDECL", "RELATIVELOCATIONTYPEDECL",
  "THREEFINGERGRIPPERSTATUSTYPEDECL", "TWISTTYPEDECL",
  "VACUUMGRIPPERSTATUSTYPEDECL", "VECTORTYPEDECL", "WRENCHTYPEDECL",
  "$accept", "y_CRCLStatusFile", "y_XmlHeaderForCRCLStatus",
  "y_SchemaLocation", "y_XmlBoolean", "y_XmlDateTime", "y_XmlDecimal",
  "y_XmlID", "y_XmlIDREF", "y_XmlNMTOKEN", "y_XmlNonNegativeInteger",
  "y_XmlPositiveInteger", "y_XmlVersion", "y_AngularVelocity_VectorType",
  "y_CRCLStatusType", "y_CommandID_XmlNonNegativeInteger", "$@1",
  "y_CommandState_CommandStateEnumType", "$@2", "y_CommandStatusType",
  "y_CommandStatus_CommandStatusType", "y_Finger1Force_XmlDecimal_0",
  "$@4", "y_Finger1Position_FractionType_0", "$@5",
  "y_Finger2Force_XmlDecimal_0", "$@6", "y_Finger2Position_FractionType_0",
  "$@7", "y_Finger3Force_XmlDecimal_0", "$@8",
  "y_Finger3Position_FractionType_0", "$@9", "y_Force_VectorType",
  "y_GripperName_XmlNMTOKEN", "$@10", "y_GripperStatusTypeAny",
  "y_GripperStatus_GripperStatusType_0", "y_I_XmlDecimal", "$@11",
  "y_IsPowered_XmlBoolean", "$@12", "y_J_XmlDecimal", "$@13",
  "y_JointNumber_XmlPositiveInteger", "$@14",
  "y_JointPosition_XmlDecimal_0", "$@15", "y_JointStatusType",
  "y_JointStatus_JointStatusType_1_u", "y_JointStatusesType",
  "y_JointStatuses_JointStatusesType_0",
  "y_JointTorqueOrForce_XmlDecimal_0", "$@16",
  "y_JointVelocity_XmlDecimal_0", "$@17", "y_K_XmlDecimal", "$@18",
  "y_LinearVelocity_VectorType", "y_ListJointStatus_JointStatusType_1_u",
  "y_Moment_VectorType", "y_Name_XmlID_0", "$@19",
  "y_OrientationStandardDeviation_P1001", "$@20", "y_PointType",
  "y_Point_PointType", "y_PoseOnlyLocationType", "y_PoseStatusType",
  "y_PoseStatus_PoseStatusType_0", "y_Pose_PoseOnlyLocationType",
  "y_PositionStandardDeviation_Posi1002", "$@21",
  "y_RefObjectName_XmlIDREF_0", "$@23", "y_Separation_XmlDecimal", "$@24",
  "y_StatusID_XmlPositiveInteger", "$@25", "y_Timestamp_XmlDateTime_0",
  "$@26", "y_TwistType", "y_Twist_TwistType_0", "y_VectorType",
  "y_WrenchType", "y_Wrench_WrenchType_0", "y_XAxis_VectorType",
  "y_X_XmlDecimal", "$@27", "y_Y_XmlDecimal", "$@28", "y_ZAxis_VectorType",
  "y_Z_XmlDecimal", "$@29", "y_x_ParallelGripperStatusType",
  "y_x_ThreeFingerGripperStatusType", "y_x_VacuumGripperStatusType", YY_NULLPTR
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
     375,   376,   377,   378,   379,   380,   381,   382
};
# endif

#define YYPACT_NINF -157

#define yypact_value_is_default(Yystate) \
  (!!((Yystate) == (-157)))

#define YYTABLE_NINF -1

#define yytable_value_is_error(Yytable_value) \
  0

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
     STATE-NUM.  */
static const yytype_int16 yypact[] =
{
       0,    -6,    13,    -3,     5,  -157,     8,    -2,    14,    10,
      11,  -157,    15,  -157,   -48,    12,    19,  -157,    21,     6,
    -157,  -157,  -157,    25,   -25,    29,   -48,    16,    28,   -42,
    -157,   -33,    20,  -157,   -48,   -15,    36,     1,  -157,    37,
     -39,    -4,  -157,   -48,   -27,  -105,  -157,  -157,    46,    32,
      48,  -157,    -4,   -23,  -157,    51,    52,    53,    18,  -157,
    -157,  -157,    57,  -157,    56,  -157,   -48,     9,  -157,    58,
     -24,   -48,   -48,   -48,  -157,  -157,    47,    62,  -157,    17,
    -157,   -48,    -9,    65,   -21,    31,    31,    31,  -157,  -157,
     -11,    72,    71,    26,    -5,  -157,   -48,   -10,    75,  -157,
      76,    -1,    54,    39,  -157,    68,  -157,    83,    33,    84,
       7,    30,  -157,   -48,     3,  -157,    88,  -157,    92,    66,
      95,  -157,  -157,    62,  -157,    96,    42,  -157,    99,    34,
     101,    93,    74,  -157,   106,  -157,  -157,   105,    77,  -157,
      69,   111,  -157,   110,  -157,   114,  -157,   115,    23,   -48,
      60,   101,  -157,   101,    59,  -157,    82,   111,   121,  -157,
     120,   100,   125,  -157,  -157,    78,   111,  -157,  -157,    49,
     128,   -48,    61,   101,    35,    89,  -157,   119,    98,   101,
    -157,  -157,    55,   109,   136,  -157,   135,   112,  -157,   102,
    -157,    86,   111,  -157,  -157,    63,    43,  -157,    50,   101,
      73,   141,   103,  -157,  -157,    81,  -157,  -157,   118,   149,
    -157,   150,   122,  -157,  -157,   104,  -157,   152,    64,  -157,
      67,   153,    87,  -157,   155,   107,  -157,  -157,   126,   111,
    -157,   157,  -157,  -157,  -157,   160,    70,  -157,  -157,   161,
    -157,   111,  -157,   162,  -157,  -157,   129,   111,  -157,   111,
    -157,   165,  -157,   168,  -157,   130,   111,  -157,  -157,   143,
     111,    79,   111,  -157,   108,   172,  -157,   131,   111,  -157,
     144,  -157,    80,   111,  -157,   113,  -157,   124,  -157,  -157,
      85,  -157,  -157,  -157
};

  /* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
     Performed when YYTABLE does not specify something else to do.  Zero
     means the default is an error.  */
static const yytype_uint8 yydefact[] =
{
       0,     0,     0,     0,     0,     1,     0,     0,     0,     0,
       0,    13,     0,     3,    77,     0,     0,     4,     0,     0,
       2,    14,    78,     0,    63,     0,    77,     0,     0,    87,
       8,     0,     0,    22,    77,     0,     0,    47,    79,     0,
       0,     0,    64,    77,     0,     0,    16,    17,     0,     0,
       0,    74,    62,     0,    88,     0,     0,     0,     0,    44,
      45,    46,     0,    98,     0,    21,    77,     0,    75,     0,
     104,    77,    77,    77,    48,    11,     0,     0,    19,     0,
      61,    77,     0,     0,   108,     0,     0,     0,    18,    12,
       0,     0,     0,    57,    93,    89,    77,     0,     0,    86,
       0,     0,    26,     0,    99,     0,    55,     0,    65,     0,
     100,     0,   105,    77,     0,    42,     0,   118,     0,    32,
       0,   120,    20,     0,    58,     0,    68,    94,     0,     0,
       0,     0,     0,   109,     0,    96,    27,     0,    38,    51,
       0,     0,    66,     0,    60,     0,   101,     0,     0,    77,
       0,     0,   103,     0,     0,    10,     0,     0,     0,    33,
       0,    23,     0,    56,     7,     0,     0,    69,     9,     0,
       0,    77,     0,     0,     0,     0,    73,     0,     0,     0,
     107,    43,     0,     0,     0,    39,     0,    29,     5,     0,
      59,     0,     0,    95,     6,     0,     0,    84,     0,     0,
      90,     0,     0,    15,    41,     0,    97,    28,     0,     0,
      24,     0,    35,    52,    67,     0,   102,     0,     0,   110,
       0,     0,    80,    49,     0,     0,    76,    34,     0,     0,
      30,     0,   119,    70,   111,     0,     0,   115,    91,     0,
      85,     0,    53,     0,   106,    40,     0,     0,    36,     0,
     113,     0,    83,     0,    81,     0,     0,    71,    25,     0,
       0,     0,     0,   116,     0,     0,    50,     0,     0,    31,
       0,   112,     0,     0,    92,     0,    54,     0,    37,   114,
       0,    82,    72,   117
};

  /* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -157,  -157,  -157,  -157,  -157,  -157,  -156,  -157,  -157,  -157,
    -157,    90,  -157,  -157,  -157,  -157,  -157,  -157,  -157,  -157,
    -157,  -157,  -157,  -157,  -157,  -157,  -157,  -157,  -157,  -157,
    -157,  -157,  -157,  -157,   -75,  -157,  -157,  -157,  -157,  -157,
    -157,  -157,  -157,  -157,  -157,  -157,  -157,  -157,  -157,   132,
    -157,  -157,  -157,  -157,  -157,  -157,  -157,  -157,  -157,  -157,
    -157,   -26,  -157,  -157,  -157,  -157,  -157,  -157,  -157,  -157,
    -157,  -157,  -157,  -157,  -157,  -157,  -157,  -157,  -157,  -157,
    -157,  -157,  -157,  -149,  -157,  -157,  -157,  -157,  -157,  -157,
    -157,  -157,  -157,  -157,  -157,  -157,  -157
};

  /* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     2,     9,    13,   189,   195,   165,    31,   169,   156,
      76,    90,     3,   152,    15,    40,    62,    65,    91,    27,
      24,   187,   229,   119,   158,   212,   247,   138,   184,   232,
     260,   161,   209,   154,   101,   134,    58,    46,   202,   241,
     121,   162,   225,   256,    93,   123,   108,   141,    67,    51,
      35,    29,   126,   166,   144,   192,   244,   268,   131,    52,
     180,    19,    25,   240,   265,   172,   148,    82,    44,    37,
      70,   222,   253,   110,   145,   117,   157,    49,    77,   129,
     170,    97,    84,   150,   114,    99,   174,   218,   249,   236,
     262,   200,   252,   273,    59,    60,    61
};

  /* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
     positive, shift that token.  If negative, reduce the rule whose
     number is the opposite.  If YYTABLE_NINF, syntax error.  */
static const yytype_uint16 yytable[] =
{
      32,   182,   177,    10,   178,    11,    55,     4,    41,     1,
     191,   102,   103,     5,     6,     7,    14,    53,    56,     8,
      57,    16,    12,    18,   198,    17,    21,    22,    20,    23,
     205,    26,    28,    30,    34,    36,   215,    38,    33,    39,
      79,    42,    43,    47,    45,    85,    86,    87,    48,    54,
     220,    50,    63,    64,    66,    94,    69,    71,    72,    73,
      74,    75,    78,    80,    81,    88,    89,    83,    92,    95,
     111,    96,   100,   246,    98,   104,   105,   106,   109,   107,
     112,   113,   115,   118,   116,   255,   120,   132,   122,   124,
     127,   259,   125,   261,   135,   130,   128,   133,   136,   137,
     267,   139,   142,   143,   270,   146,   272,   149,   151,   147,
     155,   159,   277,   153,   160,   164,   167,   280,   168,   163,
     173,   171,   181,   175,   176,   183,   185,   186,   179,   188,
     190,   193,   194,   203,   201,   197,   204,   207,   199,   206,
     208,   210,   217,   211,   214,   196,   219,   223,   213,   226,
     227,   216,   224,   228,   221,   258,   230,   231,   234,   238,
     239,   242,   245,   248,   233,   235,   250,   254,   257,   237,
     243,   263,   264,   269,   266,   251,   275,   271,   278,   276,
     279,     0,     0,     0,    68,   281,   282,     0,   274,   283,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,   140
};

static const yytype_int16 yycheck[] =
{
      26,   157,   151,     5,   153,     7,   111,    13,    34,     9,
     166,    86,    87,     0,    17,    10,     6,    43,   123,    11,
     125,    10,     8,    71,   173,    10,     7,     6,    16,    23,
     179,     6,    57,     4,     6,    77,   192,    70,    22,    19,
      66,    56,     6,     6,    43,    71,    72,    73,    87,    76,
     199,    55,     6,    21,     6,    81,    79,     6,     6,     6,
      42,     4,     6,    54,     6,    18,     4,    91,    51,    78,
      96,     6,    41,   229,    95,    86,     4,     6,    83,    53,
      90,     6,     6,    29,    85,   241,    47,   113,    20,     6,
       6,   247,    59,   249,     6,    65,    89,    94,     6,    33,
     256,     6,     6,    61,   260,     6,   262,     6,    15,    75,
       4,     6,   268,    39,    37,     4,     6,   273,     4,    50,
      97,     6,    40,   149,    64,     4,     6,    27,    69,     4,
      52,    82,     4,    14,    45,    74,    38,    28,   103,    84,
       4,     6,    99,    31,    58,   171,    96,     6,    46,    68,
      32,    88,    49,     4,    81,    26,     6,    35,     6,     6,
      73,     6,    36,     6,    60,   101,     6,     6,     6,   102,
      63,     6,     4,    30,    44,   105,     4,    98,    34,    48,
     100,    -1,    -1,    -1,    52,    72,    62,    -1,    80,   104,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,   123
};

  /* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
     symbol of state STATE-NUM.  */
static const yytype_uint8 yystos[] =
{
       0,     9,   129,   140,    13,     0,    17,    10,    11,   130,
       5,     7,     8,   131,     6,   142,    10,    10,    71,   189,
      16,     7,     6,    23,   148,   190,     6,   147,    57,   179,
       4,   135,   189,    22,     6,   178,    77,   197,    70,    19,
     143,   189,    56,     6,   196,    43,   165,     6,    87,   205,
      55,   177,   187,   189,    76,   111,   123,   125,   164,   222,
     223,   224,   144,     6,    21,   145,     6,   176,   177,    79,
     198,     6,     6,     6,    42,     4,   138,   206,     6,   189,
      54,     6,   195,    91,   210,   189,   189,   189,    18,     4,
     139,   146,    51,   172,   189,    78,     6,   209,    95,   213,
      41,   162,   162,   162,    86,     4,     6,    53,   174,    83,
     201,   189,    90,     6,   212,     6,    85,   203,    29,   151,
      47,   168,    20,   173,     6,    59,   180,     6,    89,   207,
      65,   186,   189,    94,   163,     6,     6,    33,   155,     6,
     139,   175,     6,    61,   182,   202,     6,    75,   194,     6,
     211,    15,   141,    39,   161,     4,   137,   204,   152,     6,
      37,   159,   169,    50,     4,   134,   181,     6,     4,   136,
     208,     6,   193,    97,   214,   189,    64,   211,   211,    69,
     188,    40,   134,     4,   156,     6,    27,   149,     4,   132,
      52,   134,   183,    82,     4,   133,   189,    74,   211,   103,
     219,    45,   166,    14,    38,   211,    84,    28,     4,   160,
       6,    31,   153,    46,    58,   134,    88,    99,   215,    96,
     211,    81,   199,     6,    49,   170,    68,    32,     4,   150,
       6,    35,   157,    60,     6,   101,   217,   102,     6,    73,
     191,   167,     6,    63,   184,    36,   134,   154,     6,   216,
       6,   105,   220,   200,     6,   134,   171,     6,    26,   134,
     158,   134,   218,     6,     4,   192,    44,   134,   185,    30,
     134,    98,   134,   221,    80,     4,    48,   134,    34,   100,
     134,    72,    62,   104
};

  /* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint8 yyr1[] =
{
       0,   128,   129,   130,   131,   132,   133,   134,   135,   136,
     137,   138,   139,   140,   140,   141,   142,   144,   143,   146,
     145,   147,   148,   149,   150,   149,   151,   152,   151,   153,
     154,   153,   155,   156,   155,   157,   158,   157,   159,   160,
     159,   161,   163,   162,   164,   164,   164,   165,   165,   167,
     166,   169,   168,   171,   170,   173,   172,   174,   175,   174,
     176,   177,   178,   179,   179,   180,   181,   180,   182,   183,
     182,   185,   184,   186,   187,   187,   188,   189,   190,   189,
     191,   192,   191,   193,   194,   195,   196,   197,   197,   198,
     199,   200,   199,   201,   202,   201,   204,   203,   206,   205,
     207,   208,   207,   209,   210,   210,   211,   212,   213,   213,
     214,   216,   215,   218,   217,   219,   221,   220,   222,   223,
     224
};

  /* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     5,     2,     2,     1,     1,     1,     1,     1,
       1,     1,     1,     4,     6,     3,     6,     0,     5,     0,
       5,     5,     3,     0,     0,     5,     0,     0,     5,     0,
       0,     5,     0,     0,     5,     0,     0,     5,     0,     0,
       5,     3,     0,     5,     1,     1,     1,     0,     3,     0,
       5,     0,     5,     0,     5,     0,     5,     0,     0,     5,
       6,     3,     3,     0,     3,     0,     0,     5,     0,     0,
       5,     0,     5,     3,     1,     2,     3,     0,     0,     5,
       0,     0,     5,     5,     3,     9,     5,     0,     3,     3,
       0,     0,     5,     0,     0,     5,     0,     5,     0,     5,
       0,     0,     5,     4,     0,     3,     5,     4,     0,     3,
       3,     0,     5,     0,     5,     3,     0,     5,     5,    10,
       5
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

    {(yyval.CRCLStatusFileVal) = new CRCLStatusFile((yyvsp[-4].XmlVersionVal), (yyvsp[-2].XmlHeaderForCRCLStatusVal), (yyvsp[-1].CRCLStatusTypeVal));
	   CRCLStatusTree = (yyval.CRCLStatusFileVal);
	   if (XmlIDREF::idMissing())
	     yyerror("xs:ID missing for xs:IDREF");
	  }

    break;

  case 3:

    {(yyval.XmlHeaderForCRCLStatusVal) = new XmlHeaderForCRCLStatus((yyvsp[0].SchemaLocationVal));}

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

    {(yyval.XmlNMTOKENVal) = new XmlNMTOKEN((yyvsp[0].sVal));
	   if ((yyval.XmlNMTOKENVal)->bad)
	     yyerror("bad XmlNMTOKEN");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 11:

    {(yyval.XmlNonNegativeIntegerVal) = new XmlNonNegativeInteger((yyvsp[0].sVal));
	   if ((yyval.XmlNonNegativeIntegerVal)->bad)
	     yyerror("bad XmlNonNegativeInteger");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 12:

    {(yyval.XmlPositiveIntegerVal) = new XmlPositiveInteger((yyvsp[0].sVal));
	   if ((yyval.XmlPositiveIntegerVal)->bad)
	     yyerror("bad XmlPositiveInteger");
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

    {(yyval.VectorTypeVal) = (yyvsp[-1].VectorTypeVal);}

    break;

  case 16:

    {(yyval.CRCLStatusTypeVal) = new CRCLStatusType((yyvsp[-4].XmlIDVal), (yyvsp[-3].CommandStatusTypeVal), (yyvsp[-2].JointStatusesTypeVal), (yyvsp[-1].PoseStatusTypeVal), (yyvsp[0].GripperStatusTypeVal));}

    break;

  case 17:

    {yyReadData = 1;}

    break;

  case 18:

    {(yyval.XmlNonNegativeIntegerVal) = (yyvsp[-1].XmlNonNegativeIntegerVal);}

    break;

  case 19:

    {yyReadData = 1;}

    break;

  case 20:

    {(yyval.CommandStateEnumTypeVal) = new CommandStateEnumType((yyvsp[-1].sVal));
	   if ((yyval.CommandStateEnumTypeVal)->bad)
	     yyerror("bad CommandState value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 21:

    {(yyval.CommandStatusTypeVal) = new CommandStatusType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlNonNegativeIntegerVal), (yyvsp[-1].XmlPositiveIntegerVal), (yyvsp[0].CommandStateEnumTypeVal));}

    break;

  case 22:

    {(yyval.CommandStatusTypeVal) = (yyvsp[-1].CommandStatusTypeVal);}

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

    {(yyval.FractionTypeVal) = 0;}

    break;

  case 27:

    {yyReadData = 1;}

    break;

  case 28:

    {(yyval.FractionTypeVal) = new FractionType((yyvsp[-1].sVal));
	   if ((yyval.FractionTypeVal)->bad)
	     yyerror("bad Finger1Position value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 29:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 30:

    {yyReadData = 1;}

    break;

  case 31:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 32:

    {(yyval.FractionTypeVal) = 0;}

    break;

  case 33:

    {yyReadData = 1;}

    break;

  case 34:

    {(yyval.FractionTypeVal) = new FractionType((yyvsp[-1].sVal));
	   if ((yyval.FractionTypeVal)->bad)
	     yyerror("bad Finger2Position value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 35:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 36:

    {yyReadData = 1;}

    break;

  case 37:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 38:

    {(yyval.FractionTypeVal) = 0;}

    break;

  case 39:

    {yyReadData = 1;}

    break;

  case 40:

    {(yyval.FractionTypeVal) = new FractionType((yyvsp[-1].sVal));
	   if ((yyval.FractionTypeVal)->bad)
	     yyerror("bad Finger3Position value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 41:

    {(yyval.VectorTypeVal) = (yyvsp[-1].VectorTypeVal);}

    break;

  case 42:

    {yyReadData = 1;}

    break;

  case 43:

    {(yyval.XmlNMTOKENVal) = (yyvsp[-1].XmlNMTOKENVal);}

    break;

  case 44:

    {(yyval.GripperStatusTypeVal) = (yyvsp[0].ParallelGripperStatusTypeVal);}

    break;

  case 45:

    {(yyval.GripperStatusTypeVal) = (yyvsp[0].ThreeFingerGripperStatusTypeVal);}

    break;

  case 46:

    {(yyval.GripperStatusTypeVal) = (yyvsp[0].VacuumGripperStatusTypeVal);}

    break;

  case 47:

    {(yyval.GripperStatusTypeVal) = 0;}

    break;

  case 48:

    {(yyval.GripperStatusTypeVal) = (yyvsp[-1].GripperStatusTypeVal);}

    break;

  case 49:

    {yyReadData = 1;}

    break;

  case 50:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 51:

    {yyReadData = 1;}

    break;

  case 52:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

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

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 58:

    {yyReadData = 1;}

    break;

  case 59:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 60:

    {(yyval.JointStatusTypeVal) = new JointStatusType((yyvsp[-4].XmlIDVal), (yyvsp[-3].XmlPositiveIntegerVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));}

    break;

  case 61:

    {(yyval.JointStatusTypeVal) = (yyvsp[-1].JointStatusTypeVal);}

    break;

  case 62:

    {(yyval.JointStatusesTypeVal) = new JointStatusesType((yyvsp[-1].XmlIDVal), (yyvsp[0].ListJointStatusTypeVal));}

    break;

  case 63:

    {(yyval.JointStatusesTypeVal) = 0;}

    break;

  case 64:

    {(yyval.JointStatusesTypeVal) = (yyvsp[-1].JointStatusesTypeVal);}

    break;

  case 65:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 66:

    {yyReadData = 1;}

    break;

  case 67:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 68:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 69:

    {yyReadData = 1;}

    break;

  case 70:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 71:

    {yyReadData = 1;}

    break;

  case 72:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 73:

    {(yyval.VectorTypeVal) = (yyvsp[-1].VectorTypeVal);}

    break;

  case 74:

    {(yyval.ListJointStatusTypeVal) = new std::list<JointStatusType *>;
	   (yyval.ListJointStatusTypeVal)->push_back((yyvsp[0].JointStatusTypeVal));}

    break;

  case 75:

    {(yyval.ListJointStatusTypeVal) = (yyvsp[-1].ListJointStatusTypeVal);
	   (yyval.ListJointStatusTypeVal)->push_back((yyvsp[0].JointStatusTypeVal));}

    break;

  case 76:

    {(yyval.VectorTypeVal) = (yyvsp[-1].VectorTypeVal);}

    break;

  case 77:

    {(yyval.XmlIDVal) = 0;}

    break;

  case 78:

    {yyReadData = 1;}

    break;

  case 79:

    {(yyval.XmlIDVal) = (yyvsp[-1].XmlIDVal);}

    break;

  case 80:

    {(yyval.PositiveDecimalTypeVal) = 0;}

    break;

  case 81:

    {yyReadData = 1;}

    break;

  case 82:

    {(yyval.PositiveDecimalTypeVal) = new PositiveDecimalType((yyvsp[-1].sVal));
	   if ((yyval.PositiveDecimalTypeVal)->bad)
	     yyerror("bad OrientationStandardDeviation value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 83:

    {(yyval.PointTypeVal) = new PointType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));}

    break;

  case 84:

    {(yyval.PointTypeVal) = (yyvsp[-1].PointTypeVal);}

    break;

  case 85:

    {(yyval.PoseOnlyLocationTypeVal) = new PoseOnlyLocationType((yyvsp[-7].XmlIDVal), (yyvsp[-6].XmlIDREFVal), (yyvsp[-5].XmlDateTimeVal), (yyvsp[-4].PointTypeVal), (yyvsp[-3].VectorTypeVal), (yyvsp[-2].VectorTypeVal), (yyvsp[-1].PositiveDecimalTypeVal), (yyvsp[0].PositiveDecimalTypeVal));}

    break;

  case 86:

    {(yyval.PoseStatusTypeVal) = new PoseStatusType((yyvsp[-3].XmlIDVal), (yyvsp[-2].PoseOnlyLocationTypeVal), (yyvsp[-1].TwistTypeVal), (yyvsp[0].WrenchTypeVal));}

    break;

  case 87:

    {(yyval.PoseStatusTypeVal) = 0;}

    break;

  case 88:

    {(yyval.PoseStatusTypeVal) = (yyvsp[-1].PoseStatusTypeVal);}

    break;

  case 89:

    {(yyval.PoseOnlyLocationTypeVal) = (yyvsp[-1].PoseOnlyLocationTypeVal);}

    break;

  case 90:

    {(yyval.PositiveDecimalTypeVal) = 0;}

    break;

  case 91:

    {yyReadData = 1;}

    break;

  case 92:

    {(yyval.PositiveDecimalTypeVal) = new PositiveDecimalType((yyvsp[-1].sVal));
	   if ((yyval.PositiveDecimalTypeVal)->bad)
	     yyerror("bad PositionStandardDeviation value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 93:

    {(yyval.XmlIDREFVal) = 0;}

    break;

  case 94:

    {yyReadData = 1;}

    break;

  case 95:

    {(yyval.XmlIDREFVal) = (yyvsp[-1].XmlIDREFVal);}

    break;

  case 96:

    {yyReadData = 1;}

    break;

  case 97:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 98:

    {yyReadData = 1;}

    break;

  case 99:

    {(yyval.XmlPositiveIntegerVal) = (yyvsp[-1].XmlPositiveIntegerVal);}

    break;

  case 100:

    {(yyval.XmlDateTimeVal) = 0;}

    break;

  case 101:

    {yyReadData = 1;}

    break;

  case 102:

    {(yyval.XmlDateTimeVal) = (yyvsp[-1].XmlDateTimeVal);}

    break;

  case 103:

    {(yyval.TwistTypeVal) = new TwistType((yyvsp[-2].XmlIDVal), (yyvsp[-1].VectorTypeVal), (yyvsp[0].VectorTypeVal));}

    break;

  case 104:

    {(yyval.TwistTypeVal) = 0;}

    break;

  case 105:

    {(yyval.TwistTypeVal) = (yyvsp[-1].TwistTypeVal);}

    break;

  case 106:

    {(yyval.VectorTypeVal) = new VectorType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));}

    break;

  case 107:

    {(yyval.WrenchTypeVal) = new WrenchType((yyvsp[-2].XmlIDVal), (yyvsp[-1].VectorTypeVal), (yyvsp[0].VectorTypeVal));}

    break;

  case 108:

    {(yyval.WrenchTypeVal) = 0;}

    break;

  case 109:

    {(yyval.WrenchTypeVal) = (yyvsp[-1].WrenchTypeVal);}

    break;

  case 110:

    {(yyval.VectorTypeVal) = (yyvsp[-1].VectorTypeVal);}

    break;

  case 111:

    {yyReadData = 1;}

    break;

  case 112:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 113:

    {yyReadData = 1;}

    break;

  case 114:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 115:

    {(yyval.VectorTypeVal) = (yyvsp[-1].VectorTypeVal);}

    break;

  case 116:

    {yyReadData = 1;}

    break;

  case 117:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 118:

    {(yyval.ParallelGripperStatusTypeVal) = new ParallelGripperStatusType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlNMTOKENVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.ParallelGripperStatusTypeVal)->printTypp = true;
	  }

    break;

  case 119:

    {(yyval.ThreeFingerGripperStatusTypeVal) = new ThreeFingerGripperStatusType((yyvsp[-7].XmlIDVal), (yyvsp[-6].XmlNMTOKENVal), (yyvsp[-5].FractionTypeVal), (yyvsp[-4].FractionTypeVal), (yyvsp[-3].FractionTypeVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.ThreeFingerGripperStatusTypeVal)->printTypp = true;
	  }

    break;

  case 120:

    {(yyval.VacuumGripperStatusTypeVal) = new VacuumGripperStatusType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlNMTOKENVal), (yyvsp[0].XmlBooleanVal));
	   (yyval.VacuumGripperStatusTypeVal)->printTypp = true;
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
