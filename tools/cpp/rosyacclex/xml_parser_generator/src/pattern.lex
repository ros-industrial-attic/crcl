%{

/******************************************************************************
  DISCLAIMER:
  This software was produced by the National Institute of Standards
  and Technology (NIST), an agency of the U.S. government, and by statute is
  not subject to copyright in the United States.  Recipients of this software
  assume all responsibility associated with its operation, modification,
  maintenance, and subsequent redistribution.

  See NIST Administration Manual 4.09.07 b and Appendix I. 
*****************************************************************************/

/*

This is a lexer to use with pattern.y. It reads portions of strings that
are supposed to be regular expressions of the sort used as the values of
patterns in an XML schema.

All allowed single character escapes are implemented here.

The following constructs are not currently implemented:
\p{XX}    category escapes (including block escapes)
&#XXXX;   Unicode character representations

The dash character is a pain in the butt. Outside of a [xxx] construct
it is a character with no special meaning. Inside a [xxx] construct it
can stand for either a range or a substraction, depending on where it
occurs. This is handled here by treating it as character only outside of
[xxx] constructs. Inside of [xxx] constructs dash is treated as a RANGER
or part of a SUBTRACTER by using the INBRACKET start state and a bracketDepth.
If error handling is implemented, the start state and bracketDepth will need
resetting to resume correctly after an error inside a [xxx] construct.

*/

#include "patternYACC.hh"    // for tokens, yylval, etc.


#ifdef ECHO_IT
#define ECHOTEST 1
#else
#define ECHOTEST 0
#endif

#define ECH if (ECHOTEST) ECHO

int bracketDepth = 0;

%}

%s INBRACKET

%%

[a-zA-Z]             { ECH; return LETTER; }

[0-9]                { ECH; return DIGIT; }

\\c                  { ECH; return NAMECLASS; }
\\C                  { ECH; return NOTNAMECLASS; }
\\d                  { ECH; return DIGITCLASS; }
\\D                  { ECH; return NOTDIGITCLASS; }
\\i                  { ECH; return INITIALCLASS; }
\\I                  { ECH; return NOTINITIALCLASS; }
\\n                  { ECH; return NEWLINE; }
\\r                  { ECH; return CARRIAGERETURN; }
\\s                  { ECH; return WHITESPACECLASS; }
\\S                  { ECH; return NOTWHITESPACECLASS; }
\\t                  { ECH; return TAB; }
\\w                  { ECH; return NOTWEIRDCLASS; }
\\W                  { ECH; return WEIRDCLASS; }

\.                   { ECH; return ANYCLASS; }

\\\*                 { ECH; return ASTERISKCHAR; }
\\\\                 { ECH; return BACKSLASHCHAR; }
\\\-                 { ECH; return DASHCHAR; }
\\\^                 { ECH; return HATCHAR; }
\\\[                 { ECH; return LBRACKETCHAR; }
\\\{                 { ECH; return LCURLYCHAR; }
\\\(                 { ECH; return LPARENCHAR; }
\\\.                 { ECH; return PERIODCHAR; }
\\\+                 { ECH; return PLUSCHAR; }
\\\?                 { ECH; return QUESTIONCHAR; }
\\\]                 { ECH; return RBRACKETCHAR; }
\\\}                 { ECH; return RCURLYCHAR; }
\\\)                 { ECH; return RPARENCHAR; }
\\\|                 { ECH; return VERTICALBARCHAR; }

<INBRACKET>"-"/"]"   { ECH; return DASHCHAR; }
<INBRACKET>"-"       { ECH; return RANGER; }
<INBRACKET>"-["      { ECH; bracketDepth++; return SUBTRACTER; }

":"                  { ECH; return COLONCHAR; }
","                  { ECH; return COMMA; }
"-"                  { ECH; return DASHCHAR; }
"^"                  { ECH; return NEGATE; }
"["                  { ECH; BEGIN(INBRACKET); bracketDepth++; return LBRACKET;}
"{"                  { ECH; return LCURLY; }
"("                  { ECH; return LPAREN; }
"+"                  { ECH; return ONETOMANY; }
"|"                  { ECH; return ORBAR; }
\"                   { ECH; return QUOTE; }
"]"                  { ECH; bracketDepth--;
                       if (bracketDepth == 0)
			 BEGIN(INITIAL);
                       return RBRACKET;
                     }
"}"                  { ECH; return RCURLY; }
")"                  { ECH; return RPAREN; }
"_"                  { ECH; return UNDERSCORECHAR; }
"?"                  { ECH; return ZEROORONE; }
"*"                  { ECH; return ZEROTOMANY; }

.                    { ECH; return BAD; }

%%

int yywrap()
{
  return 1;
}
