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

#ifndef YY_YYRE_SRC_PATTERNYACC_HH_INCLUDED
# define YY_YYRE_SRC_PATTERNYACC_HH_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int yyredebug;
#endif

/* Token type.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
    ANYCLASS = 258,
    ASTERISKCHAR = 259,
    BACKSLASHCHAR = 260,
    BAD = 261,
    CARRIAGERETURN = 262,
    COLONCHAR = 263,
    COMMA = 264,
    DASHCHAR = 265,
    DIGIT = 266,
    DIGITCLASS = 267,
    HATCHAR = 268,
    INITIALCLASS = 269,
    LBRACKET = 270,
    LBRACKETCHAR = 271,
    LCURLY = 272,
    LCURLYCHAR = 273,
    LETTER = 274,
    LPAREN = 275,
    LPARENCHAR = 276,
    NAMECLASS = 277,
    NEGATE = 278,
    NEWLINE = 279,
    NOTDIGITCLASS = 280,
    NOTINITIALCLASS = 281,
    NOTNAMECLASS = 282,
    NOTWEIRDCLASS = 283,
    NOTWHITESPACECLASS = 284,
    ONETOMANY = 285,
    ORBAR = 286,
    PERIODCHAR = 287,
    PLUSCHAR = 288,
    QUESTIONCHAR = 289,
    QUOTE = 290,
    RANGER = 291,
    RBRACKET = 292,
    RBRACKETCHAR = 293,
    RCURLY = 294,
    RCURLYCHAR = 295,
    RPAREN = 296,
    RPARENCHAR = 297,
    SUBTRACTER = 298,
    TAB = 299,
    UNDERSCORECHAR = 300,
    VERTICALBARCHAR = 301,
    WEIRDCLASS = 302,
    WHITESPACECLASS = 303,
    ZEROORONE = 304,
    ZEROTOMANY = 305
  };
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef int YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yyrelval;

int yyreparse (void);

#endif /* !YY_YYRE_SRC_PATTERNYACC_HH_INCLUDED  */
