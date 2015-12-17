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

This expects that the XmlCppBase::wg3Prefix will be 0 before the
first thing of the form "<xs:" is seen, where "xs" could be any alphabetic
characters.  The first time something of that form is seen, this sets
XmlCppBase::wg3Prefix to the string consisting of the characters that
were found. Every other time < is seen followed by some alphabetic
characters and a colon, this checks that the characters are the same
as XmlCppBase::wg3Prefix. If the check fails, this records an error
message and returns BAD.

In general, this looks for preceding white space and not trailing
white space. The one exception to this is for ENDITEM, which allows
both preceding and trailing white space. This is done because DOS
files cause the parser to fail at the very end of the file if trailing
white space is not allowed.

A prefix is some letters preceding or following a colon. Let XXX stand
for a prefix. The prefixes in <XXX: and </XXX: are handled by
doPrefix.  The prefix in xmlns:XXX= is pulled out and returned as the
value of XMLNSPREFIX. The string following targetNamespace= or
xmlns:XXX= will probably have a colon in it, but we do not want to
treat the part before or after the colon as a prefix, so strings following
targetNamespace= and xmlns:XXX= are handled by going into the STRING
mode. Other prefixes, which occur mainly in type names, are handled by
returning the prefix as the value of XMLNSPREFIX and going into the
PREFIXED mode.

The value of a pattern or an enumeration may include a colon, so an
inPatEnum variable and a PATENUMVALUE state are defined. The inPatEnum
is set to 1 when a pattern or an enumeration is found, and the
PATENUMVALUE state is entered if "value" is read and inPatEnum is 1.

*/

#include <string.h>            // for strdup
#include "xml_common/xmlSchemaClasses.hh" // for classes referenced in xmlSchemaYACC.hh
#include "xmlSchemaYACC.hh"    // for tokens, yylval, etc.

#define ECHO_IT 1
#define ECH if (ECHO_IT) ECHO

extern char lexMessage[];
extern char commentString[];

int n;
int first;
char lexbuf[40];
int inPatEnum = 0;

/********************************************************************/

/* doPrefix

Returned Value: int
  This returns the retVal argument.

Called By: The lex lines that read prefixes preceded by < or </

The value of the tex argument is something like <xs: or </xs:
where the xs (or xsd or whatever) is the prefix we are after.
The function removes everything before the x and deletes the
colon at the end.

The first time this is called is when the second line of the
schema file is being read. That line reads: <xs:schema ...
where the xs may be xsd or anything else; in any event, it
is what the XmlCppBase::wg3Prefix should be. XmlCppBase::wg3Prefix
is zero at that time, so this sets it to a copy of the prefix.

Every other time this is called, it checks that the prefix is
the same as XmlCppBase::wg3Prefix.

*/

int doPrefix( /* ARGUMENTS                */
 char * tex,  /* string containing prefix */
 int retVal)  /* value to return          */
{
  int first;

  tex[strlen(tex) - 1] = 0; // delete the colon at the end
  for (first = 0; tex[first] < 'A'; first++); // skip <, /, and white space
  if (XmlCppBase::wg3Prefix == 0)
    XmlCppBase::wg3Prefix = strdup(tex + first);
  else if (strcmp((tex + first), XmlCppBase::wg3Prefix))
    {
      sprintf(lexMessage, "Bad prefix %s. Prefix should be %s\n",
	      (tex + first), XmlCppBase::wg3Prefix);
      return BAD;
    }
  return retVal;
}

/********************************************************************/

%}

V [ \t\n\r]+
W [ \t\n\r]*
%x COMMENT
%x DOC
%x ENDDOC
%x PATENUMVALUE
%x PREFIXED
%x STARTDOC
%x STRING

%%

{W}"<!--"                  { ECH; /* no need to save <!-- */
                             n = 0;
                             BEGIN(COMMENT);
                             return STARTCOMMENT;
                           }
<COMMENT>.                 { ECH;
                             commentString[n++] = yytext[0];
			     if (n >= 40000)
                               { strcpy(lexMessage, "comment too long");
				 return BAD;
			       }
                           }
<COMMENT>\n                { ECH;
                             commentString[n++] = yytext[0];
			     if (n >= 40000)
                               { strcpy(lexMessage, "comment too long");
				 return BAD;
			       }
                           }
<COMMENT>"-->"             { ECH; /* no need to save --> */
                             commentString[n] = 0;
                             BEGIN(INITIAL);
                             return ENDCOMMENT;
                           }
<STARTDOC>{W}">"           { ECH; BEGIN(DOC); return ENDITEM; }
<STARTDOC>{W}"source"{W}"=" { ECH; return SOURCE; }
<STARTDOC>{W}\"[^\"]+\"    { ECH;
                             for (first = 0; yytext[first] != '"'; first++);
                             first++;
 			     for (n = first; yytext[n] != '"'; n++);
 			     yytext[n] = 0;
                             yylval.sVal = strdup(yytext + first);
			     return TERMINALSTRING;
                           }
<STARTDOC>.                { ECH; return BAD; }

<DOC>[^<]*                 { ECH;
                             yylval.sVal = strdup(yytext);
			     return TERMINALSTRING;
                           }
<DOC>"</"[a-zA-Z]*":"      { ECH; BEGIN(ENDDOC);
                             return doPrefix(yytext, STARTOUTITEM);
                           }
<DOC>.                     { ECH; return BAD; }

<ENDDOC>"appinfo"          { ECH; BEGIN(INITIAL); return XSAPPINFO; }
<ENDDOC>"documentation"    { ECH; BEGIN(INITIAL); return XSDOCUMENTATION; }
<ENDDOC>.                  { ECH; return BAD; }

<PATENUMVALUE>\"[^\"]+\"   { ECH; 
                             for (first = 0; yytext[first] != '"'; first++);
                             first++;
 			     for (n = first; yytext[n] != '"'; n++);
 			     yytext[n] = 0;
                             yylval.sVal = strdup(yytext + first);
                             inPatEnum = 0;
                             BEGIN(INITIAL);
			     return TERMINALSTRING;
                           }
<PREFIXED>[^\"]+\"         { ECH;
                             for (n = 0; yytext[n] != '"'; n++);
 			     yytext[n] = 0;
                             yylval.sVal = strdup(yytext);
                             BEGIN(INITIAL);
			     return TERMINALSTRING;
                           }
<STRING>{W}\"[^\"]+\"      { ECH;
                             for (first = 0; yytext[first] != '"'; first++);
                             first++;
 			     for (n = first; yytext[n] != '"'; n++);
 			     yytext[n] = 0;
                             yylval.sVal = strdup(yytext + first);
                             BEGIN(INITIAL);
			     return TERMINALSTRING;
                           }
{W}">"{W}                  { ECH; return ENDITEM; }
{W}"/>"                    { ECH; return ENDWHOLEITEM; }
{W}"<"[a-zA-Z]*":"         { ECH; return doPrefix(yytext, STARTINITEM);}
{W}"</"[a-zA-Z]*":"        { ECH; return doPrefix(yytext, STARTOUTITEM);}
{W}"?>"                    { ECH; return ENDVERSION; }
{W}"<?"                    { ECH; return STARTVERSION; }
"xml"[ \t]+"version"{W}"=" { ECH; return XMLVERSION; }

{W}"<"[a-zA-Z]*":annotation" { ECH;
                               for (n = 0; yytext[n] != ':'; n++);
                               yytext[n+1] = 0;
                               return doPrefix(yytext, STARTANNOTATION);
                             }

{W}\"[^:\"]+":"            { ECH;
                             for (first = 0; yytext[first] != '"'; first++);
                             first++;
 			     for (n = first; yytext[n] != ':'; n++);
 			     yytext[n] = 0;
                             yylval.sVal = strdup(yytext + first);
                             BEGIN(PREFIXED);
                             return XMLNSPREFIX;
                           }
{W}\"[^:\"]+\"             { ECH;
                             for (first = 0; yytext[first] != '"'; first++);
                             first++;
 			     for (n = first; yytext[n] != '"'; n++);
 			     yytext[n] = 0;
                             yylval.sVal = strdup(yytext + first);
			     return TERMINALSTRING;
                           }
{W}"xmlns:"[a-zA-Z]+{W}"=" { ECH;
                             for (first = 0; yytext[first] != ':'; first++);
                             first++;
			     for (n = first;
				  (((yytext[n] >= 'A') && (yytext[n] <= 'Z')) ||
				   ((yytext[n] >= 'a') && (yytext[n] <= 'z')));
				  n++);
			     yytext[n] = 0;
			     yylval.sVal = strdup(yytext + first);
                             BEGIN(STRING);
			     return XMLNSPREFIX;
		           }
{W}"xmlns"{W}"="           { ECH;
			     yylval.sVal = 0;
                             BEGIN(STRING);
			     return XMLNSPREFIX;
		           }
{W}"targetNamespace"{W}"=" { ECH;
                             BEGIN(STRING);
                             return TARGETNAMESPACE;
                           }

{W}"namespace"{W}"="       { ECH;
                             BEGIN(STRING);
                             return NAMESPACE;
                           }

{W}"schemaLocation"{W}"="  { ECH;
                             BEGIN(STRING);
                             return SCHEMALOCATION;
                           }

{W}"abstract"{W}"="             { ECH; return ABSTRACT; }
{W}"attributeFormDefault"{W}"=" { ECH; return ATTRIBUTEFORMDEFAULT; }
{W}"base"{W}"="                 { ECH; return BASE; }
{W}"default"{W}"="              { ECH; return DEFALT; }
{W}"elementFormDefault"{W}"="   { ECH; return ELEMENTFORMDEFAULT; }
{W}"encoding"{W}"="             { ECH; return ENCODING; }
{W}"final"{W}"="                { ECH; return FINAL; }
{W}"fixed"{W}"="                { ECH; return FIXED; }
{W}"form"{W}"="                 { ECH; return FORM; }
{W}"id"{W}"="                   { ECH; return ID; }
{W}"itemType"{W}"="             { ECH; return ITEMTYPE; }
{W}"maxOccurs"{W}"="            { ECH; return MAXOCCURS; }
{W}"minOccurs"{W}"="            { ECH; return MINOCCURS; }
{W}"mixed"{W}"="                { ECH; return MIXED; }
{W}"name"{W}"="                 { ECH; return NAME; }
{W}"nillable"{W}"="             { ECH; return NILLABLE; }
{W}"processContents"{W}"="      { ECH; return PROCESSCONTENTS; }
{W}"refer"{W}"="                { ECH; return REFER; }
{W}"ref"{W}"="                  { ECH; return REF; }
{W}"source"{W}"="               { ECH; return SOURCE; }
{W}"substitutionGroup"{W}"="    { ECH; return SUBSTITUTIONGROUP; }
{W}"type"{W}"="                 { ECH; return TYP; }
{W}"use"{W}"="                  { ECH; return USE; }
{W}"value"{W}"="                { ECH;
                                  if (inPatEnum) BEGIN PATENUMVALUE;
                                  return VALUE;
                                }
{W}"version"{W}"="              { ECH; return VERSION; }
{W}"xpath"{W}"="                { ECH; BEGIN(STRING); return XPATH; }

"annotation"               { ECH; return XSANNOTATION; }
"any"                      { ECH; return XSANY; }
"appinfo"                  { ECH; BEGIN(STARTDOC); return XSAPPINFO; }
"attribute"                { ECH; return XSATTRIBUTE; }
"attributeGroup"           { ECH; return XSATTRIBUTEGROUP; }
"choice"                   { ECH; return XSCHOICE; }
"complexContent"           { ECH; return XSCOMPLEXCONTENT; }
"complexType"              { ECH; return XSCOMPLEXTYPE; }
"documentation"            { ECH; BEGIN(STARTDOC); return XSDOCUMENTATION; }
"element"                  { ECH; return XSELEMENT; }
"enumeration"              { ECH; inPatEnum = 1; return XSENUMERATION; }
"extension"                { ECH; return XSEXTENSION; }
"field"                    { ECH; return XSFIELD; }
"group"                    { ECH; return XSGROUP; }
"import"                   { ECH; return XSIMPORT; }
"include"                  { ECH; return XSINCLUDE; }
"key"                      { ECH; return XSKEY; }
"keyref"                   { ECH; return XSKEYREF; }
"length"                   { ECH; return XSLENGTH; }
"list"                     { ECH; return XSLIST; }
"maxExclusive"             { ECH; return XSMAXEXCLUSIVE; }
"maxInclusive"             { ECH; return XSMAXINCLUSIVE; }
"maxLength"                { ECH; return XSMAXLENGTH; }
"minExclusive"             { ECH; return XSMINEXCLUSIVE; }
"minInclusive"             { ECH; return XSMININCLUSIVE; }
"minLength"                { ECH; return XSMINLENGTH; }
"pattern"                  { ECH; inPatEnum = 1; return XSPATTERN; }
"restriction"              { ECH; return XSRESTRICTION; }
"schema"                   { ECH; return XSSCHEMA; }
"selector"                 { ECH; return XSSELECTOR; }
"sequence"                 { ECH; return XSSEQUENCE; }
"simpleContent"            { ECH; return XSSIMPLECONTENT; }
"simpleType"               { ECH; return XSSIMPLETYPE; }
"unique"                   { ECH; return XSUNIQUE; }

.                          { ECH; return BAD; }

%%

int yywrap()
{
  return 1;
}

