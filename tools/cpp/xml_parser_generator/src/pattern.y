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

This parses a file containing a single line containing a single string
(with double quotes at the beginning and end) containing an XML
regular expression of the sort used in XML schema patterns. The parser
only checks correctness, it does not build a parse tree as it parses.

If a line causes a syntax error, an error message is printed (usually
just saying "syntax error").

To use the parser this builds, it is intended that when a regular
expression is to be parsed, it should be put into a file, and then the
file should be parsed.  There does not seem to be a more direct way to
use a YACC parser to parse a string.

If HASMAIN is #defined when the compiler runs, a main program is
written that will read a file consisting of lines each of which is a
regular expression in quotes. The lines get read one at a time. Each
time a line is read, it is put into the file tempFile, and then the
parser parses tempFile. In this stand-alone parser, each line is
reprinted as the lexer reads it. If a line gets parsed correctly, "is
OK" is printed after the line. If there is a syntax error on the line,
the part of the line up to the point at which the error occurred is
printed, then an error message is printed, then the rest of the line
is printed (as a result of error recovery) until a newline is read.
For example, if the string is "b{2}a", then that is what is printed.
If the string is "b{}a", which is syntactially incorrect since the
curly braces have nothing in them, then the following is printed:

   "b{}
   syntax error
   a"

*/

#include <stdio.h>             // for fopen

int yyreerror(const char * s);

extern int yyrelex();

extern FILE * yyrein;

%}

%token   ANYCLASS
%token   ASTERISKCHAR
%token   BACKSLASHCHAR
%token   BAD
%token   CARRIAGERETURN
%token   COLONCHAR
%token   COMMA
%token   DASHCHAR
%token   DIGIT
%token   DIGITCLASS
%token   HATCHAR
%token   INITIALCLASS
%token   LBRACKET
%token   LBRACKETCHAR
%token   LCURLY
%token   LCURLYCHAR
%token   LETTER
%token   LPAREN
%token   LPARENCHAR
%token   NAMECLASS
%token   NEGATE
%token   NEWLINE
%token   NOTDIGITCLASS
%token   NOTINITIALCLASS
%token   NOTNAMECLASS
%token   NOTWEIRDCLASS
%token   NOTWHITESPACECLASS
%token   ONETOMANY
%token   ORBAR
%token   PERIODCHAR
%token   PLUSCHAR
%token   QUESTIONCHAR
%token   QUOTE
%token   RANGER
%token   RBRACKET
%token   RBRACKETCHAR
%token   RCURLY
%token   RCURLYCHAR
%token   RPAREN
%token   RPARENCHAR
%token   SUBTRACTER
%token   TAB
%token   UNDERSCORECHAR
%token   VERTICALBARCHAR
%token   WEIRDCLASS
%token   WHITESPACECLASS
%token   ZEROORONE
%token   ZEROTOMANY

%%

regexpString :
	  QUOTE branchList QUOTE
	| error '\n'
	;

atom :
	  ANYCLASS
	| bracketed
	| character
	| DIGITCLASS
	| LPAREN branchList RPAREN
	| NAMECLASS
	| NOTDIGITCLASS
	| NOTWHITESPACECLASS
	| WHITESPACECLASS
	;

bracketed :
	  LBRACKET itemList RBRACKET
	| LBRACKET NEGATE itemList RBRACKET
	| LBRACKET itemList SUBTRACTER itemList RBRACKET RBRACKET
	| LBRACKET NEGATE itemList SUBTRACTER itemList RBRACKET RBRACKET
	;

branchList :
	  branch
	| branchList ORBAR branch
	;

branch :
	  pieceList
	;

character :
	  DIGIT
	| escapeChar
	| LETTER
	| puncChar
	;

charClass :
	  ANYCLASS
	| DIGITCLASS
	| INITIALCLASS
	| NAMECLASS
	| NOTDIGITCLASS
	| NOTINITIALCLASS
	| NOTNAMECLASS
	| NOTWEIRDCLASS
	| NOTWHITESPACECLASS
	| WEIRDCLASS
	| WHITESPACECLASS
	;

escapeChar :
	  BACKSLASHCHAR
	| CARRIAGERETURN
	| NEWLINE
	| TAB
	;

item :
	  character
	| charClass
	| range
	;

itemList :
	  item
	| itemList item
	;

number :
	  DIGIT
	| number DIGIT
	;

pieceList :
	  piece
	| pieceList piece
	;

piece :
	  atom
	| atom quantifier
	;

puncChar :
	  ASTERISKCHAR
	| COLONCHAR
	| DASHCHAR
	| HATCHAR
	| LBRACKETCHAR
	| LCURLYCHAR
	| LPARENCHAR
	| PERIODCHAR
	| PLUSCHAR
	| QUESTIONCHAR
	| RBRACKETCHAR
	| RCURLYCHAR
	| RPARENCHAR
	| UNDERSCORECHAR
	| VERTICALBARCHAR
	;

quantifier :
	  LCURLY number RCURLY
	| LCURLY number COMMA RCURLY
	| LCURLY number COMMA number RCURLY
	| ONETOMANY
	| ZEROORONE
	| ZEROTOMANY
	;

range :
	  character RANGER character
	;

%%


/********************************************************************/

/* yyreerror

Returned Value: int (0)

Called By: yyreparse

This prints whatever string the parser provides.

*/

int yyreerror(     /* ARGUMENTS       */
 const char * s) /* string to print */
{
  fflush(stdout);
  fprintf(stderr, "\n%s\n", s);
  return 0;
}

/********************************************************************/

#ifdef HASMAIN

int main(int argc, char * argv[])
{
  FILE * inFile;
  FILE * stringFile;
  char buffer[500];

  inFile = fopen(argv[1], "r");
  while (fgets(buffer, 500, inFile))
    {
      stringFile = fopen("tempFile", "w");
      fprintf(stringFile, "%s", buffer);
      fclose(stringFile);
      yyrein = fopen("tempFile", "r");
      yyreparse();
      printf("\n");
      fclose(yyrein);
    }
  fclose(inFile);
  return 0;
}

#endif

/********************************************************************/

