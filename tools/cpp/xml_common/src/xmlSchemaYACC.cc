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

Attributes of XML schema items (element, sequence, complexType, etc.)
may be given in any order, and many are optional. To list in the rules
of a YACC production all the possibilities for a set of attributes is
prohibitively long when there are 4 or more attributes (3 attributes
has 16 possible combinations). Hence, for each schema item, attributes
are collected in a list by the YACC rule, and then the YACC action
calls a doXml...Attributes function that sorts the attributes out.
The C++ class for each schema item contains slots for all the
attributes.  When a schema item is recognized by its YACC rule, the
action first builds a C++ class instance with all of its slots
containing default values (all of which are 0 or some typed equivalent
of nothing).  Then it runs a doXml...Attributes function.  When the
doXml...Attributes function runs, some or all default values may be
replaced. After that function runs, other (non-attribute) slots are
filled in by the action.

The wg3Prefix saved by xmlSchema.lex does not include the colon.

The schema for schemas is
http://www.w3.org/TR/xmlschema-1/#element-attribute

This saves all allowed comments for possible reprinting. The use of
comments is restricted from what is normally allowed in an XML schema,
as follows.

1. Multiple comments may appear immediately after the first line of the
file (which gives the XML version).

2. Multiple comments may appear immediately before a key or keyref
   (annotations are not allowed there).

3. A single comment may appear instead of or immediately before
an annotation. As a result:
3A. where a single annotation is allowed, there may be any of:
 (1) an annotation
 (2) a single comment (of any length)
 (3) a comment followed by an annotation.
3B. where multiple annotations are allowed, there may be multiple comments,
  possibly mixed with annotations.

*/

#include <stdio.h>             // for stderr
#include <string.h>            // for strcat
#include <stdlib.h>            // for malloc, free
#include "xml_common/xmlSchemaClasses.hh" // for xml classes
#include "xmlSchemaYACC.hh"    // for token values

#define YYERROR_VERBOSE
#ifndef YYDEBUG
#define YYDEBUG 1
#endif
#define ERRSIZE 100

/********************************************************************/

int yyerror(const char * s);

extern int yylex();
#ifndef NOPATTERN
extern int yyreparse();
extern FILE * yyrein;
#endif

void checkXmlns(char * prefix, char * location);
void doXmlAnnotationAttributes(XmlAnnotation * note,
			       std::list<XmlAttribPair *> * atts);
void doXmlAnyAttributes(XmlAny * any,
			std::list<XmlAttribPair *> * atts);
void doXmlAppinfoAttributes(XmlAppinfo * appinfo,
			    std::list<XmlAttribPair *> * atts);
void doXmlAttributeLonerAttributes(XmlAttributeLoner * attDecl,
				  std::list<XmlAttribPair *> * atts);
void doXmlAttributeLonerRefableAttributes(XmlAttributeLonerRefable * attTop,
					 std::list<XmlAttribPair *> * atts);
void doXmlAttributeGroupRefAttributes(XmlAttributeGroupRef * grup,
				      std::list<XmlAttribPair *> * atts);
void doXmlAttributeGroupRefableAttributes(XmlAttributeGroupRefable * grup,
					  std::list<XmlAttribPair *> * atts);
void doXmlChoiceAttributes(XmlChoice * choi,
			   std::list<XmlAttribPair *> * atts);
void doXmlComplexContentAttributes(XmlComplexContent * comp,
				   std::list<XmlAttribPair *> * atts);
void doXmlComplexExtensionAttributes(XmlComplexRestriction * comp,
				     std::list<XmlAttribPair *> * atts);
void doXmlComplexRestrictionAttributes(XmlComplexRestriction * comp,
				       std::list<XmlAttribPair *> * atts);
void doXmlComplexTypeAttributes(XmlComplexType * comp,
				std::list<XmlAttribPair *> * atts);
void doXmlDocumentationAttributes(XmlDocumentation * docu,
				  std::list<XmlAttribPair *> * atts);
void doXmlElementGroupAttributes(XmlElementGroup * group,
				 std::list<XmlAttribPair *> * atts);
void doXmlElementGroupRefAttributes(XmlElementGroupRef * group,
				    std::list<XmlAttribPair *> * atts);
void doXmlElementLocalAttributes(XmlElementLocal * elem,
				 std::list<XmlAttribPair *> * atts);
void doXmlElementRefableAttributes(XmlElementRefable * elem,
			       std::list<XmlAttribPair *> * atts);
void doXmlEnumerationAttributes(XmlEnumeration * nume,
				std::list<XmlAttribPair *> * atts);
void doXmlFieldAttributes(XmlField * field,
			  std::list<XmlAttribPair *> * atts);
void doXmlKeyAttributes(XmlKey * key,
			std::list<XmlAttribPair *> * atts);
void doXmlKeyrefAttributes(XmlKeyref * keyref,
			   std::list<XmlAttribPair *> * atts);
void doXmlLengthAttributes(XmlLength * leng,
			   std::list<XmlAttribPair *> * atts);
void doXmlMaxExclusiveAttributes(XmlMaxExclusive * maxe,
				 std::list<XmlAttribPair *> * atts);
void doXmlMaxInclusiveAttributes(XmlMaxInclusive * maxi,
				 std::list<XmlAttribPair *> * atts);
void doXmlMaxLengthAttributes(XmlMaxLength * maxl,
			      std::list<XmlAttribPair *> * atts);
void doXmlMinExclusiveAttributes(XmlMinExclusive * mine,
				 std::list<XmlAttribPair *> * atts);
void doXmlMinInclusiveAttributes(XmlMinInclusive * mini,
				 std::list<XmlAttribPair *> * atts);
void doXmlMinLengthAttributes(XmlMaxLength * minl,
			      std::list<XmlAttribPair *> * atts);
void doXmlPatternAttributes(XmlPattern * pat,
			    std::list<XmlAttribPair *> * atts);
void doXmlSchemaHeaderAttributes(XmlSchemaHeader * header,
				 std::list<XmlAttribPair *> * atts);
void doXmlSelectorAttributes(XmlSelector * sel,
			     std::list<XmlAttribPair *> * atts);
void doXmlSequenceAttributes(XmlSequence * seq,
			     std::list<XmlAttribPair *> * atts);
void doXmlSimpleContentAttributes(XmlSimpleContent * cont,
				  std::list<XmlAttribPair *> * atts);
void doXmlSimpleContentExtensionAttributes(XmlSimpleContentExtension * extend,
				    std::list<XmlAttribPair *> * atts);
void doXmlSimpleContentRestrictionAttributes(XmlSimpleContentRestriction * rest,
					     std::list<XmlAttribPair *> * atts);
void doXmlSimpleListAttributes(XmlSimpleList * liz,
			       std::list<XmlAttribPair *> * atts);
void doXmlSimpleRestrictionAttributes(XmlSimpleRestriction * rest,
				      std::list<XmlAttribPair *> * atts);
void doXmlSimpleTypeAttributes(XmlSimpleType * simp,
			       std::list<XmlAttribPair *> * atts);
void doXmlUniqueAttributes(XmlUnique * unique,
			std::list<XmlAttribPair *> * atts);

/********************************************************************/

char lexMessage[80]; // for communication with xmlSchema.lex
char commentString[40000]; // for communication with xmlSchema.lex
XmlSchemaFile * xmlSchemaFile; // the parse tree
char * globalOwlPrefix;
static char errMessage[ERRSIZE];

/*

The attNames (attribute names) array exists so that it is easy to 
print the names of attributes. Only the names in camel case are
used. The other names are in the array so that token values can be
used for indexing it. The names in the array are the same as those
of the tokens, except for case.

*/

static const char * attNames[] =
  {
    "abstract",
    "attributeFormDefault",
    "BAD",
    "base",
    "default",
    "elementFormDefault",
    "ENCODING",
    "ENDCOMMENT",
    "ENDITEM",
    "ENDWHOLEITEM",
    "ENDVERSION",
    "final",
    "fixed",
    "form",
    "id",
    "itemType",
    "maxOccurs",
    "minOccurs",
    "mixed",
    "name",
      "namespace",
    "nillable",
      "processContents",
    "ref",
    "refer",
    "schemaLocation",
    "source",
    "STARTANNOTATION",
    "STARTCOMMENT",
    "STARTINITEM",
    "STARTOUTITEM",
    "STARTVERSION",
    "substitutionGroup",
    "targetNamespace",
    "TERMINALSTRING",
    "type",
    "use",
    "value",
    "version",
    "xpath",
    "XMLNSPREFIX",
    "XSANNOTATION",
      "XSANY",
    "XSAPPINFO",
    "XSATTRIBUTE",
    "XSATTRIBUTEGROUP",
    "XSCHOICE",
    "XSCOMPLEXCONTENT",
    "XSCOMPLEXTYPE",
    "XSDOCUMENTATION",
    "XSELEMENT",
    "XSENUMERATION",
    "XSEXTENSION",
    "XSFIELD",
    "XSGROUP",
    "XSIMPORT",
    "XSINCLUDE",
    "XSKEY",
    "XSKEYREF",
    "XSLENGTH",
    "XSLIST",
    "XSMAXEXCLUSIVE",
    "XSMAXINCLUSIVE",
    "XSMAXLENGTH",
    "XSMINEXCLUSIVE",
    "XSMININCLUSIVE",
    "XSMINLENGTH",
    "XSPATTERN",
    "XSRESTRICTION",
    "XSSCHEMA",
    "XSSELECTOR",
    "XSSEQUENCE",
    "XSSIMPLECONTENT",
    "XSSIMPLETYPE",
    "XMLVERSION"
  };

/********************************************************************/

/* checkXmlns

This checks that exactly one of the prefixes on the prefixList, is the
same as the XmlCppBase::wg3Prefix and is the prefix for
http://www.w3.org/2001/XMLSchema. It also checks that no other prefix
is the prefix for http://www.w3.org/2001/XMLSchema. If there is any
error, this prints an error message and exits.

The XmlCppBase::wg3Prefix (usually xs or xsd) gets set when the schema
line (usually the second line of the file) gets read.

*/

void checkXmlns(                      /* ARGUMENTS                   */
 std::list<XmlNsPair *> * prefixList) /* list of XmlNsPairs to check */
{
  
  char * prefix;
  std::list<XmlNsPair *>::iterator iter;
  bool foundWg3;

  foundWg3 = false;
  for (iter = prefixList->begin(); iter != prefixList->end(); iter++)
    {
      prefix = (*iter)->prefix;
      if (prefix && (strcmp(XmlCppBase::wg3Prefix, prefix) == 0))
	{ // this prefix is the wg3Prefix
	  if (foundWg3)
	    { // the wg3Prefix was already found
	      snprintf(errMessage, ERRSIZE, "wg3 prefix \"%s\" used twice",
		       XmlCppBase::wg3Prefix);
	      yyerror(errMessage);
	    }
	  else
	    {
	      foundWg3 = true;
	      if (strcmp("http://www.w3.org/2001/XMLSchema", (*iter)->location))
		{
		  snprintf(errMessage, ERRSIZE,
			   "xmlns location for xml schema must be\n"
			   "http://www.w3.org/2001/XMLSchema but is %s",
			   (*iter)->location);
		  yyerror(errMessage);
		}
	    }
	}
      else if (strcmp("http://www.w3.org/2001/XMLSchema", (*iter)->location)
	       == 0)
	{
	  snprintf(errMessage, ERRSIZE,
		   "prefix for http://www.w3.org/2001/XMLSchema \n"
		   "must be %s but is %s", XmlCppBase::wg3Prefix, prefix);
	  yyerror(errMessage);
	}
    }
  if (!foundWg3)
    {
      snprintf(errMessage, ERRSIZE,
	       "prefix %s for http://www.w3.org/2001/XMLSchema not found",
	       XmlCppBase::wg3Prefix);
      yyerror(errMessage);
    }
}

/********************************************************************/

/*

There follow a number of doXml...Attributes functions. In some of
these, the prefix in the attribute pair (which is really a triple, not a
pair) is transcribed into a class instance. The value of the prefix is
not checked. In particular, a zero value is OK.

*/

/********************************************************************/

void doXmlAnnotationAttributes(
 XmlAnnotation * note,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (note->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in annotation");
	      yyerror(errMessage);
	    }
	  note->id = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE, 
		   "bad attribute name \"%s\" in annotation",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
}

/********************************************************************/

void doXmlAnyAttributes(
 XmlAny * any,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (any->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in any");
	      yyerror(errMessage);
	    }
	  any->id = (*iter)->val;
	}
      else if ((*iter)->name == MAXOCCURS)
	{
	  if (any->maxOccurs != -2)
	    {
	      snprintf(errMessage, ERRSIZE, "maxOccurs used twice in any");
	      yyerror(errMessage);
	    }
	  if (strcmp((*iter)->val, "unbounded") == 0)
	    any->maxOccurs = -1;
	  else
	    any->maxOccurs = atoi((*iter)->val);
	}
      else if ((*iter)->name == MINOCCURS)
	{
	  if (any->minOccurs != -2)
	    {
	      snprintf(errMessage, ERRSIZE, "minOccurs used twice in any");
	      yyerror(errMessage);
	    }
	  any->minOccurs = atoi((*iter)->val);
	}
      else if ((*iter)->name == NAMESPACE)
	{
	  if (any->namespaceVal)
	    {
	      snprintf(errMessage, ERRSIZE, "namespace used twice in any");
	      yyerror(errMessage);
	    }
	  else if (strcmp((*iter)->val, "##any") == 0)
	    any->namespaceVal = XmlAny::hashAny;
	  else if (strcmp((*iter)->val, "##other") == 0)
	    any->namespaceVal = XmlAny::hashOther;
	  else if (strcmp((*iter)->val, "##targetNamespace") == 0)
	    any->namespaceVal = XmlAny::hashTarget;
	  else if (strcmp((*iter)->val, "##local") == 0)
	    any->namespaceVal = XmlAny::hashLocal;
	  else
	    {
	      snprintf(errMessage, ERRSIZE, "bad namespace value in any");
	      yyerror(errMessage);
	    }
	}
      else if ((*iter)->name == PROCESSCONTENTS)
	{
	  if (any->processContentsVal)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "processContents used twice in any");
	      yyerror(errMessage);
	    }
	  else if (strcmp((*iter)->val, "lax") == 0)
	    any->processContentsVal = XmlAny::lax;
	  else if (strcmp((*iter)->val, "skip") == 0)
	    any->processContentsVal = XmlAny::skip;
	  else if (strcmp((*iter)->val, "strict") == 0)
	    any->processContentsVal = XmlAny::strict;
	  else
	    {
	      snprintf(errMessage, ERRSIZE,
		       "bad processContents value in any");
	      yyerror(errMessage);
	    }
	}
      else
	{
	  snprintf(errMessage, ERRSIZE, 
		   "bad attribute name \"%s\" in any",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
}

/********************************************************************/


void doXmlAppinfoAttributes(
 XmlAppinfo * appinfo,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == SOURCE)
	{
	  appinfo->source = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in appinfo",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
}
 
/********************************************************************/

/* doXmlAttributeLonerAttributes

If an XmlAttributeLoner has ref, it must not have name, typ, typPrefix,
or simple.

*/

void doXmlAttributeLonerAttributes(
 XmlAttributeLoner * attDecl,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (attDecl->id)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "id used twice in attribute declaration");
	      yyerror(errMessage);
	    }
	  attDecl->id = (*iter)->val;
	}
      else if ((*iter)->name == DEFALT)
	{
	  if (attDecl->defalt)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "default used twice in attribute declaration");
	      yyerror(errMessage);
	    }
	  attDecl->defalt = (*iter)->val;
	}
      else if ((*iter)->name == FIXED)
	{
	  if (attDecl->fixed)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "fixed used twice in attribute declaration");
	      yyerror(errMessage);
	    }
	  attDecl->fixed = (*iter)->val;
	}
      else if ((*iter)->name == FORM)
	{
	  if (strcmp((*iter)->val, "qualified") == 0)
	    attDecl->form = XmlCppBase::qualified;
	  else if (strcmp((*iter)->val, "unqualified") == 0)
	    attDecl->form = XmlCppBase::unqualified;
	  else
	    {
	      snprintf(errMessage, ERRSIZE,
		       "bad form value \"%s\" in attribute declaration",
		       (*iter)->val);
	      yyerror(errMessage);
	    }
	}
      else if ((*iter)->name == NAME)
	{
	  if (attDecl->name)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "name used twice in attribute declaration");
	      yyerror(errMessage);
	    }
	  attDecl->name = (*iter)->val;
	  attDecl->newNameRef = XmlCppBase::modifyName(attDecl->name);
	}
      else if ((*iter)->name == REF)
	{
	  if (attDecl->ref)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "ref used twice in attribute declaration");
	      yyerror(errMessage);
	    }
	  attDecl->ref = (*iter)->val;
	  attDecl->newNameRef = XmlCppBase::modifyName(attDecl->ref);
	}
      else if ((*iter)->name == TYP)
	{
	  if (attDecl->typ)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "type used twice in attribute declaration");
	      yyerror(errMessage);
	    }
	  attDecl->typPrefix = (*iter)->pref;
	  attDecl->typ = (*iter)->val;
	  attDecl->newTyp = XmlCppBase::modifyName(attDecl->typ);
	}
      else if ((*iter)->name == USE)
	{
	  if (attDecl->use != XmlCppBase::useNone)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "use used twice in attribute declaration");
	      yyerror(errMessage);
	    }
	  if (strcmp((*iter)->val, "required") == 0)
	    attDecl->use = XmlCppBase::required;
	  else if (strcmp((*iter)->val, "optional") == 0)
	    attDecl->use = XmlCppBase::optional;
	  else if (strcmp((*iter)->val, "prohibited") == 0)
	    attDecl->use = XmlCppBase::prohibited;
	  else
	    {
	      snprintf(errMessage, ERRSIZE,
		       "bad use value \"%s\" in attribute declaration",
		       (*iter)->val);
	      yyerror(errMessage);
	    }
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in attribute declaration",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if ((attDecl->name == 0) && (attDecl->ref == 0))
    yyerror("XML attribute must have name or ref");
  else if (attDecl->ref)
    {
      if (attDecl->name)
	yyerror("XML attribute must not have name and ref");
      if (attDecl->typPrefix || attDecl->typ)
	yyerror("XML attribute must not have type and ref");
      if (attDecl->simple)
	yyerror("XML attribute must not have simple type and ref");
    }
  if (attDecl->defalt && attDecl->fixed)
    yyerror("XML attribute must not have both default and fixed");
}

/********************************************************************/

/* doXmlAttributeLonerRefableAttributes

According to the section at 
in http://www.w3.org/TR/xmlschema-1 which is the schema for schemas:
ref, use, and form are not allowed, and name is required.

*/

void doXmlAttributeLonerRefableAttributes(
 XmlAttributeLonerRefable * attTop,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == DEFALT)
	{
	  if (attTop->defalt)
	    {
	      snprintf(errMessage, ERRSIZE, "default used twice in attribute");
	      yyerror(errMessage);
	    }
	  attTop->defalt = (*iter)->val;
	}
      else if ((*iter)->name == FIXED)
	{
	  if (attTop->fixed)
	    {
	      snprintf(errMessage, ERRSIZE, "fixed used twice in attribute");
	      yyerror(errMessage);
	    }
	  attTop->fixed = (*iter)->val;
	}
      else if ((*iter)->name == ID)
	{
	  if (attTop->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in attribute");
	      yyerror(errMessage);
	    }
	  attTop->id = (*iter)->val;
	}
       else if ((*iter)->name == NAME)
	{
	  if (attTop->name)
	    {
	      snprintf(errMessage, ERRSIZE, "name used twice in attribute");
	      yyerror(errMessage);
	    }
	  attTop->name = (*iter)->val;
	}
      else if ((*iter)->name == TYP)
	{
	  if (attTop->typ)
	    {
	      snprintf(errMessage, ERRSIZE, "type used twice in attribute");
	      yyerror(errMessage);
	    }
	  attTop->typPrefix = (*iter)->pref;
	  attTop->typ = (*iter)->val;
          attTop->newTyp = XmlCppBase::modifyName(attTop->typ);
	}
     else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in attribute declaration",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (attTop->defalt && attTop->fixed)
    yyerror("XML global attribute may not have both default and fixed");
  if (attTop->name == 0)
    yyerror("XML global attribute must have name");
}

/********************************************************************/

/* doXmlAttributeGroupRefAttributes

*/

void doXmlAttributeGroupRefAttributes(
 XmlAttributeGroupRef * grup,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (grup->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in attributeGroup");
	      yyerror(errMessage);
	    }
	  grup->id = (*iter)->val;
	}
      else if ((*iter)->name == REF)
	{
	  if (grup->ref)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "ref used twice in attributeGroup");
	      yyerror(errMessage);
	    }
	  grup->ref = (*iter)->val;
	}
     else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in attributeGroup",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (grup->ref == 0)
    yyerror("attribute group reference must have ref");
}

/********************************************************************/

/* doXmlAttributeGroupRefableAttributes

*/

void doXmlAttributeGroupRefableAttributes(
 XmlAttributeGroupRefable * grup,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (grup->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in attributeGroup");
	      yyerror(errMessage);
	    }
	  grup->id = (*iter)->val;
	}
      else if ((*iter)->name == NAME)
	{
	  if (grup->name)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "name used twice in attributeGroup");
	      yyerror(errMessage);
	    }
	  grup->name = (*iter)->val;
	}
     else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in attributeGroup",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (grup->name == 0)
    yyerror("attribute group definition must have name");
}

/********************************************************************/

void doXmlChoiceAttributes(
 XmlChoice * choi,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (choi->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in choice");
	      yyerror(errMessage);
	    }
	  choi->id = (*iter)->val;
	}
      else if ((*iter)->name == MAXOCCURS)
	{
	  if (choi->maxOccurs != -2)
	    {
	      snprintf(errMessage, ERRSIZE, "maxOccurs used twice in choice");
	      yyerror(errMessage);
	    }
	  if (strcmp((*iter)->val, "unbounded") == 0)
	    choi->maxOccurs = -1;
	  else
	    choi->maxOccurs = atoi((*iter)->val);
	}
      else if ((*iter)->name == MINOCCURS)
	{
	  if (choi->minOccurs != -2)
	    {
	      snprintf(errMessage, ERRSIZE, "minOccurs used twice in choice");
	      yyerror(errMessage);
	    }
	  choi->minOccurs = atoi((*iter)->val);
	}
      else
	{
	  snprintf(errMessage, ERRSIZE, "bad attribute name \"%s\" in choice",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
}

/********************************************************************/

void doXmlComplexContentAttributes(
 XmlComplexContent * comp,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (comp->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in complexContent");
	      yyerror(errMessage);
	    }
	  comp->id = (*iter)->val;
	}
      else if ((*iter)->name == MIXED)
	{
	  if (comp->mixed != XmlCppBase::logicalNone)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "mixed used twice in complexContent");
	      yyerror(errMessage);
	    }
	  else if (strcmp((*iter)->val, "true") == 0)
	    comp->mixed = XmlCppBase::yes;
	  else if (strcmp((*iter)->val, "false") == 0)
	    comp->mixed = XmlCppBase::no;
	  else
	    {
	      snprintf(errMessage, ERRSIZE,
		       "bad mixed \"%s\" in complexContent", (*iter)->val);
	      yyerror(errMessage);
	    }
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in complexContent",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
}

/********************************************************************/

void doXmlComplexExtensionAttributes(
 XmlComplexExtension * comp,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (comp->id)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "id used twice in complexExtension");
	      yyerror(errMessage);
	    }
	  comp->id = (*iter)->val;
	}
      else if ((*iter)->name == BASE)
	{
	  if (comp->base)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "base used twice in complexExtension");
	      yyerror(errMessage);
	    }
	  comp->basePrefix = (*iter)->pref;
	  comp->base = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in complexExtension",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (comp->base == 0)
    yyerror("complexExtension must have base");
}

/********************************************************************/

void doXmlComplexRestrictionAttributes(
 XmlComplexRestriction * comp,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (comp->id)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "id used twice in complexRestriction");
	      yyerror(errMessage);
	    }
	  comp->id = (*iter)->val;
	}
      else if ((*iter)->name == BASE)
	{
	  if (comp->base)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "base used twice in complexRestriction");
	      yyerror(errMessage);
	    }
	  comp->base = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in complexRestriction",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (comp->base == 0)
    yyerror("complexRestriction must have base");
}

/********************************************************************/

/*

In addition to checking the attributes of an XmlComplexType, this sets
the value of owlPrefix of the XmlComplexType to the current value of
the globalOwlPrefix, which may be the empty string ("") or a prefix
with some characters.

*/

void doXmlComplexTypeAttributes(
 XmlComplexType * comp,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == NAME)
	{
	  if (comp->name)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "name used twice in complex type");
	      yyerror(errMessage);
	    }
	  comp->name = (*iter)->val;
	  comp->newName = XmlCppBase::modifyName(comp->name);
	}
      else if ((*iter)->name == ABSTRACT)
	{
	  if (comp->abstract != XmlCppBase::logicalNone)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "abstract used twice in complex type");
	      yyerror(errMessage);
	    }
	  if (strcmp((*iter)->val, "true") == 0)
	    comp->abstract = XmlCppBase::yes;
	  else if (strcmp((*iter)->val, "false") == 0)
	    comp->abstract = XmlCppBase::no;
	  else
	    {
	      snprintf(errMessage, ERRSIZE,
		       "bad abstract \"%s\" in complexType", (*iter)->val);
	      yyerror(errMessage);
	    }
	}
      else if ((*iter)->name == MIXED)
	{
	  if (comp->mixed != XmlCppBase::logicalNone)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "mixed used twice in complexType");
	      yyerror(errMessage);
	    }
	  else if (strcmp((*iter)->val, "true") == 0)
	    comp->mixed = XmlCppBase::yes;
	  else if (strcmp((*iter)->val, "false") == 0)
	    comp->mixed = XmlCppBase::no;
	  else
	    {
	      snprintf(errMessage, ERRSIZE,
		       "bad mixed \"%s\" in complexType", (*iter)->val);
	      yyerror(errMessage);
	    }
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in complexType",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  comp->owlPrefix = globalOwlPrefix;
}

/********************************************************************/

/* doXmlDocumentationAttributes

This checks the attributes of an XmlDocumentation and sets the
globalOwlPrefix if it is the empty string and the XmlDocumentation
has a value for it.

The only attribute allowed for an XmlDocumentation is a SOURCE attribute.

In addition to checking the attributes of an XmlDocumentation, this
deals with an owlPrefix if one is given. If one is given, it should
occur in a documentation node in an XmlAnnotation immediately
following the schema header and be of the form
<xs:documentation>owlPrefix=XXX</xs:documentation>, where XXX is
the OWL prefix.

*/

void doXmlDocumentationAttributes(
 XmlDocumentation * docu,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == SOURCE)
	{
	  docu->source = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in documentation",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if ((globalOwlPrefix[0] == 0) && (strncmp(docu->text, "owlPrefix=", 10) == 0))
    {
      globalOwlPrefix = strdup(docu->text + 10);
    }
}
 
/********************************************************************/

/* doXmlElementGroupAttributes

*/

void doXmlElementGroupAttributes(
 XmlElementGroup * group,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == NAME)
	{
	  if (group->name)
	    {
	      snprintf(errMessage, ERRSIZE, "name used twice in element group");
	      yyerror(errMessage);
	    }
	  group->name = (*iter)->val;
	  group->newName = XmlCppBase::modifyName(group->name);
	}
      else if ((*iter)->name == ID)
	{
	  if (group->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in element group");
	      yyerror(errMessage);
	    }
	  group->id = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in element group",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
}

/********************************************************************/

/* doXmlElementGroupRefAttributes

*/

void doXmlElementGroupRefAttributes(
 XmlElementGroupRef * group,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == REF)
	{
	  if (group->ref)
	    {
	      snprintf(errMessage, ERRSIZE, "name used twice in element group");
	      yyerror(errMessage);
	    }
	  group->ref = (*iter)->val;
	}
      else if ((*iter)->name == ID)
	{
	  if (group->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in element group");
	      yyerror(errMessage);
	    }
	  group->id = (*iter)->val;
	}
      else if ((*iter)->name == MAXOCCURS)
	{
	  if (group->maxOccurs != -2)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "maxOccurs used twice in element group ref");
	      yyerror(errMessage);
	    }
	  if (strcmp((*iter)->val, "unbounded") == 0)
	    group->maxOccurs = -1;
	  else
	    group->maxOccurs = atoi((*iter)->val);
	}
      else if ((*iter)->name == MINOCCURS)
	{
	  if (group->minOccurs != -2)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "minOccurs used twice in element group ref");
	      yyerror(errMessage);
	    }
	  group->minOccurs = atoi((*iter)->val);
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in element group ref",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
}

/********************************************************************/

/* doXmlElementLocalAttributes

The checks of illegal combinations of attributes have to wait until all
the attributes have been recorded.

The only attributes allowed with ref are minOccurs, maxOccurs, and id.

*/

void doXmlElementLocalAttributes(
 XmlElementLocal * elem,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == DEFALT)
	{
	  if (elem->defalt)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "default used twice in local element");
	      yyerror(errMessage);
	    }
	  if (elem->fixed)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "cannot have both default and fixed in local element");
	      yyerror(errMessage);
	    }
	  elem->defalt = (*iter)->val;
	}
      else if ((*iter)->name == FIXED)
	{
	  if (elem->fixed)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "fixed used twice in local element");
	      yyerror(errMessage);
	    }
	  if (elem->defalt)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "cannot have both default and fixed in local element");
	      yyerror(errMessage);
	    }
	  elem->fixed = (*iter)->val;
	}
      else if ((*iter)->name == FORM)
	{
	  if (elem->form != XmlCppBase::formNone)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "form used twice in local element");
	      yyerror(errMessage);
	    }
	  else if (strcmp((*iter)->val, "qualified") == 0)
	    elem->form = XmlCppBase::qualified;
	  else if (strcmp((*iter)->val, "unqualified") == 0)
	    elem->form = XmlCppBase::unqualified;
	  else
	    {
	      snprintf(errMessage, ERRSIZE,
		       "bad form value \"%s\" in local element",
		       (*iter)->val);
	      yyerror(errMessage);
	    }
	}
      else if ((*iter)->name == NAME)
	{ // ref checked at end of function
	  if (elem->name)
	    {
	      snprintf(errMessage, ERRSIZE, "name used twice in local element");
	      yyerror(errMessage);
	    }
	  elem->name = (*iter)->val;
	  elem->newName = XmlCppBase::modifyName(elem->name);
	}
      else if ((*iter)->name == TYP)
	{ // ref checked at end of function
	  if (elem->typ)
	    {
	      snprintf(errMessage, ERRSIZE, "type used twice in local element");
	      yyerror(errMessage);
	    }
	  elem->typPrefix = (*iter)->pref;
	  elem->typ = (*iter)->val;
	  elem->newTyp = XmlCppBase::modifyName(elem->typ);
	}
      else if ((*iter)->name == REF)
	{  // name checked at end of function
	  if (elem->ref)
	    {
	      snprintf(errMessage, ERRSIZE, "ref used twice in local element");
	      yyerror(errMessage);
	    }
	  elem->ref = (*iter)->val;
	  elem->newName = XmlCppBase::modifyName(elem->ref);
	}
      else if ((*iter)->name == ID)
	{
	  if (elem->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in local element");
	      yyerror(errMessage);
	    }
	  elem->id = (*iter)->val;
	}
      else if ((*iter)->name == MAXOCCURS)
	{
	  if (elem->maxOccurs != -2)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "maxOccurs used twice in local element");
	      yyerror(errMessage);
	    }
	  if (strcmp((*iter)->val, "unbounded") == 0)
	    elem->maxOccurs = -1;
	  else
	    elem->maxOccurs = atoi((*iter)->val);
	}
      else if ((*iter)->name == MINOCCURS)
	{
	  if (elem->minOccurs != -2)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "minOccurs used twice in local element");
	      yyerror(errMessage);
	    }
	  elem->minOccurs = atoi((*iter)->val);
	}
      else if ((*iter)->name == NILLABLE)
	{
	  if (elem->nillable != XmlCppBase::logicalNone)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "nillable used twice in local element");
	      yyerror(errMessage);
	    }
	  else if (strcmp((*iter)->val, "true") == 0)
	    elem->nillable = XmlCppBase::yes;
	  else if (strcmp((*iter)->val, "false") == 0)
	    elem->nillable = XmlCppBase::no;
	  else
	    {
	      snprintf(errMessage, ERRSIZE,
		       "bad nillable \"%s\" in local element",
		      (*iter)->val);
	      yyerror(errMessage);
	    }
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in local element",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if ((elem->ref == 0) && (elem->name == 0))
    {
      snprintf(errMessage, ERRSIZE,
	       "local element must have either name or ref");
      yyerror(errMessage);
    }
  if (elem->ref && (elem->defalt ||
		    elem->fixed  ||
		    elem->form   ||
		    elem->typ    ||
		    elem->name   ||
		    (elem->nillable && (elem->nillable == XmlCppBase::yes))))
    {
      snprintf(errMessage, ERRSIZE,
	       "illegal attribute with ref %s in local element", (*iter)->val);
      yyerror(errMessage);
    }
  if (elem->defalt && elem->fixed)
    yyerror("XML local element may not have both default and fixed");
  elem->needList = ((elem->minOccurs > 1)  || (elem->maxOccurs == -1) ||
		    (elem->maxOccurs == 0) || (elem->maxOccurs > 1));
}

/********************************************************************/

void doXmlElementRefableAttributes(
 XmlElementRefable * elem,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ABSTRACT)
	{
	  if (strcmp((*iter)->val, "true") == 0)
	    elem->abstract = XmlCppBase::yes;
	  else if (strcmp((*iter)->val, "false") == 0)
	    elem->abstract = XmlCppBase::no;
	  else
	    {
	      snprintf(errMessage, ERRSIZE,
		       "bad abstract \"%s\"", (*iter)->val);
	      yyerror(errMessage);
	    }
	}
      else if ((*iter)->name == DEFALT)
	{
	  if (elem->defalt)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "default used twice in global element");
	      yyerror(errMessage);
	    }
	  elem->defalt = (*iter)->val;
	}
      else if ((*iter)->name == FIXED)
	{
	  if (elem->fixed)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "fixed used twice in global element");
	      yyerror(errMessage);
	    }
	  elem->fixed = (*iter)->val;
	}
      else if ((*iter)->name == NAME)
	{
	  if (elem->name)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "name used twice in global element");
	      yyerror(errMessage);
	    }
	  elem->name = (*iter)->val;
	  elem->newName = XmlCppBase::modifyName(elem->name);
	}
      else if ((*iter)->name == NILLABLE)
	{
	  if (elem->nillable != XmlCppBase::logicalNone)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "nillable used twice in global element");
	      yyerror(errMessage);
	    }
	  else if (strcmp((*iter)->val, "true") == 0)
	    elem->nillable = XmlCppBase::yes;
	  else if (strcmp((*iter)->val, "false") == 0)
	    elem->nillable = XmlCppBase::no;
	  else
	    {
	      snprintf(errMessage, ERRSIZE,
		       "bad nillable \"%s\"", (*iter)->val);
	      yyerror(errMessage);
	    }
	}
      else if ((*iter)->name == TYP)
	{
	  if (elem->typ)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "type used twice in global element");
	      yyerror(errMessage);
	    }
	  elem->typPrefix = (*iter)->pref;
	  elem->typ = (*iter)->val;
	  elem->newTyp = XmlCppBase::modifyName(elem->typ);
	}
      else if ((*iter)->name == ID)
	{
	  if (elem->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in global element");
	      yyerror(errMessage);
	    }
	  elem->id = (*iter)->val;
	}
      else if ((*iter)->name == SUBSTITUTIONGROUP)
	{
	  if (elem->substitutionGroup)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "substitutionGroup used twice in global element");
	      yyerror(errMessage);
	    }
	  elem->substitutionGroup = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in global element",
		  attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (elem->name == 0)
    yyerror("name missing in global element");
  if (elem->defalt && elem->fixed)
    yyerror("XML global element may not have both default and fixed");
}

/********************************************************************/

void doXmlEnumerationAttributes(
 XmlEnumeration * nume,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (nume->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in enumeration");
	      yyerror(errMessage);
	    }
	  nume->id = (*iter)->val;
	}
      else if ((*iter)->name == VALUE)
	{
	  if (nume->value)
	    {
	      snprintf(errMessage, ERRSIZE, "value used twice in enumeration");
	      yyerror(errMessage);
	    }
	  nume->value = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in enumeration",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (nume->value == 0)
    yyerror("value missing in enumeration");
}

/********************************************************************/

void doXmlFieldAttributes(
 XmlField * field,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == XPATH)
	{
	  if (field->xpath)
	    {
	      snprintf(errMessage, ERRSIZE, "xpath used twice in field");
	      yyerror(errMessage);
	    }
	  field->xpath = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE, "bad attribute name \"%s\" in field",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (field->xpath == 0)
    yyerror("xpath missing in field");
}

/********************************************************************/

void doXmlImportAttributes(
 XmlImport * imp,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == NAMESPACE)
	{
	  if (imp->namespase)
	    {
	      snprintf(errMessage, ERRSIZE, "namespace used twice in import");
	      yyerror(errMessage);
	    }
	  imp->namespase = (*iter)->val;
	}
      else if ((*iter)->name == SCHEMALOCATION)
	{
	  if (imp->schemaLocation)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "schemaLocation used twice in import");
	      yyerror(errMessage);
	    }
	  imp->schemaLocation = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE, "bad attribute name \"%s\" in import",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
}

/********************************************************************/

void doXmlKeyAttributes(
 XmlKey * key,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (key->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in key");
	      yyerror(errMessage);
	    }
	  key->id = (*iter)->val;
	}
      else if ((*iter)->name == NAME)
	{
	  if (key->name)
	    {
	      snprintf(errMessage, ERRSIZE, "name used twice in key");
	      yyerror(errMessage);
	    }
	  key->name = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE, "bad attribute name \"%s\" in key",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (key->name == 0)
    yyerror("name missing in key");
}

/********************************************************************/

void doXmlKeyrefAttributes(
 XmlKeyref * keyref,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (keyref->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in keyref");
	      yyerror(errMessage);
	    }
	  keyref->id = (*iter)->val;
	}
      else if ((*iter)->name == NAME)
	{
	  if (keyref->name)
	    {
	      snprintf(errMessage, ERRSIZE, "name used twice in keyref");
	      yyerror(errMessage);
	    }
	  keyref->name = (*iter)->val;
	}
      else if ((*iter)->name == REFER)
	{
	  if (keyref->refer)
	    {
	      snprintf(errMessage, ERRSIZE, "refer used twice in keyref");
	      yyerror(errMessage);
	    }
	  keyref->refer = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE, "bad attribute name \"%s\" in keyref",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (keyref->name == 0)
    yyerror("name missing in keyref");
  if (keyref->refer == 0)
    yyerror("refer missing in keyref");
}

/********************************************************************/

/* doXmlLengthAttributes

This checks and sets the values of the attributes of an XmlLength.
If an attribute is used that is not allowed for XmlLength, this
exits.

This also sets the value of intVal by converting the value, which is a
string that must represent an unsigned integer.

*/

void doXmlLengthAttributes(
 XmlLength * leng,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (leng->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in length");
	      yyerror(errMessage);
	    }
	  leng->id = (*iter)->val;
	}
      else if ((*iter)->name == FIXED)
	{
	  if (leng->fixed != XmlCppBase::logicalNone)
	    {
	      snprintf(errMessage, ERRSIZE, "fixed used twice in length");
	      yyerror(errMessage);
	    }
	  else if (strcmp((*iter)->val, "true") == 0)
	    leng->fixed = XmlCppBase::yes;
	  else if (strcmp((*iter)->val, "false") == 0)
	    leng->fixed = XmlCppBase::no;
	  else
	    {
	      snprintf(errMessage, ERRSIZE,
		       "bad fixed \"%s\" in length", (*iter)->val);
	      yyerror(errMessage);
	    }
	}
      else if ((*iter)->name == VALUE)
	{
	  if (leng->value)
	    {
	      snprintf(errMessage, ERRSIZE, "value used twice in length");
	      yyerror(errMessage);
	    }
	  leng->value = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in length",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (leng->value == 0)
    yyerror("value missing in length");
  if (sscanf(leng->value, "%u", &(leng->intVal)) == 0)
    {
      snprintf(errMessage, ERRSIZE,
	       "bad length value \"%s\"  in length", leng->value);
      yyerror(errMessage);
    }
}

/********************************************************************/

void doXmlMaxExclusiveAttributes(
 XmlMaxExclusive * maxe,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (maxe->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in maxExclusive");
	      yyerror(errMessage);
	    }
	  maxe->id = (*iter)->val;
	}
      else if ((*iter)->name == FIXED)
	{
	  if (maxe->fixed != XmlCppBase::logicalNone)
	    {
	      snprintf(errMessage, ERRSIZE, "fixed used twice in maxExclusive");
	      yyerror(errMessage);
	    }
	  else if (strcmp((*iter)->val, "true") == 0)
	    maxe->fixed = XmlCppBase::yes;
	  else if (strcmp((*iter)->val, "false") == 0)
	    maxe->fixed = XmlCppBase::no;
	  else
	    {
	      snprintf(errMessage, ERRSIZE,
		       "bad fixed \"%s\" in maxExclusive", (*iter)->val);
	      yyerror(errMessage);
	    }
	}
      else if ((*iter)->name == VALUE)
	{
	  if (maxe->value)
	    {
	      snprintf(errMessage, ERRSIZE, "value used twice in maxExclusive");
	      yyerror(errMessage);
	    }
	  maxe->value = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in maxExclusive",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (maxe->value == 0)
    yyerror("value missing in maxExclusive");
}

/********************************************************************/

void doXmlMaxInclusiveAttributes(
 XmlMaxInclusive * maxi,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (maxi->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in maxInclusive");
	      yyerror(errMessage);
	    }
	  maxi->id = (*iter)->val;
	}
      else if ((*iter)->name == FIXED)
	{
	  if (maxi->fixed != XmlCppBase::logicalNone)
	    {
	      snprintf(errMessage, ERRSIZE, "fixed used twice in maxInclusive");
	      yyerror(errMessage);
	    }
	  else if (strcmp((*iter)->val, "true") == 0)
	    maxi->fixed = XmlCppBase::yes;
	  else if (strcmp((*iter)->val, "false") == 0)
	    maxi->fixed = XmlCppBase::no;
	  else
	    {
	      snprintf(errMessage, ERRSIZE,
		       "bad fixed \"%s\" in maxInclusive", (*iter)->val);
	      yyerror(errMessage);
	    }
	}
      else if ((*iter)->name == VALUE)
	{
	  if (maxi->value)
	    {
	      snprintf(errMessage, ERRSIZE, "value used twice in maxInclusive");
	      yyerror(errMessage);
	    }
	  maxi->value = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in maxInclusive",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (maxi->value == 0)
    yyerror("value missing in maxInclusive");
}

/********************************************************************/

/* doXmlMaxLengthAttributes

This checks and sets the values of the attributes of an XmlMaxLength.
If an attribute is used that is not allowed for XmlMaxLength, this
exits.

This also sets the value of intVal by converting the value, which is a
string that must represent an unsigned integer.

*/

void doXmlMaxLengthAttributes(
 XmlMaxLength * maxl,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (maxl->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in maxLength");
	      yyerror(errMessage);
	    }
	  maxl->id = (*iter)->val;
	}
      else if ((*iter)->name == FIXED)
	{
	  if (maxl->fixed != XmlCppBase::logicalNone)
	    {
	      snprintf(errMessage, ERRSIZE, "fixed used twice in maxLength");
	      yyerror(errMessage);
	    }
	  else if (strcmp((*iter)->val, "true") == 0)
	    maxl->fixed = XmlCppBase::yes;
	  else if (strcmp((*iter)->val, "false") == 0)
	    maxl->fixed = XmlCppBase::no;
	  else
	    {
	      snprintf(errMessage, ERRSIZE,
		       "bad fixed \"%s\" in maxLength", (*iter)->val);
	      yyerror(errMessage);
	    }
	}
      else if ((*iter)->name == VALUE)
	{
	  if (maxl->value)
	    {
	      snprintf(errMessage, ERRSIZE, "value used twice in maxLength");
	      yyerror(errMessage);
	    }
	  maxl->value = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in maxLength",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (maxl->value == 0)
    yyerror("value missing in maxLength");
  if (sscanf(maxl->value, "%u", &(maxl->intVal)) == 0)
    {
      snprintf(errMessage, ERRSIZE,
	       "bad length value \"%s\"  in maxLength", maxl->value);
      yyerror(errMessage);
    }
}

/********************************************************************/

void doXmlMinExclusiveAttributes(
 XmlMinExclusive * mine,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (mine->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in minExclusive");
	      yyerror(errMessage);
	    }
	  mine->id = (*iter)->val;
	}
      else if ((*iter)->name == FIXED)
	{
	  if (mine->fixed != XmlCppBase::logicalNone)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "fixed used twice in minExclusive");
	      yyerror(errMessage);
	    }
	  else if (strcmp((*iter)->val, "true") == 0)
	    mine->fixed = XmlCppBase::yes;
	  else if (strcmp((*iter)->val, "false") == 0)
	    mine->fixed = XmlCppBase::no;
	  else
	    {
	      snprintf(errMessage, ERRSIZE,
		       "bad fixed \"%s\" in minExclusive", (*iter)->val);
	      yyerror(errMessage);
	    }
	}
      else if ((*iter)->name == VALUE)
	{
	  if (mine->value)
	    {
	      snprintf(errMessage, ERRSIZE, "value used twice in minExclusive");
	      yyerror(errMessage);
	    }
	  mine->value = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in minExclusive",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (mine->value == 0)
    yyerror("value missing in minExclusive");
}

/********************************************************************/

void doXmlMinInclusiveAttributes(
 XmlMinInclusive * mini,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (mini->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in minInclusive");
	      yyerror(errMessage);
	    }
	  mini->id = (*iter)->val;
	}
      else if ((*iter)->name == FIXED)
	{
	  if (mini->fixed != XmlCppBase::logicalNone)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "fixed used twice in minInclusive");
	      yyerror(errMessage);
	    }
	  else if (strcmp((*iter)->val, "true") == 0)
	    mini->fixed = XmlCppBase::yes;
	  else if (strcmp((*iter)->val, "false") == 0)
	    mini->fixed = XmlCppBase::no;
	  else
	    {
	      snprintf(errMessage, ERRSIZE,
		       "bad fixed \"%s\" in minInclusive", (*iter)->val);
	      yyerror(errMessage);
	    }
	}
      else if ((*iter)->name == VALUE)
	{
	  if (mini->value)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "value used twice in minInclusive");
	      yyerror(errMessage);
	    }
	  mini->value = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in minInclusive",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (mini->value == 0)
    yyerror("value missing in minInclusive");
}

/********************************************************************/

/* doXmlMinLengthAttributes

This checks and sets the values of the attributes of an XmlMinLength.
If the value is missing or an attribute is used that is not allowed
for XmlMinLength, this exits.

This also sets the value of intVal by converting the value, which is a
string that must represent an unsigned integer.

*/

void doXmlMinLengthAttributes(
 XmlMinLength * minl,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (minl->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in minLength");
	      yyerror(errMessage);
	    }
	  minl->id = (*iter)->val;
	}
      else if ((*iter)->name == FIXED)
	{
	  if (minl->fixed != XmlCppBase::logicalNone)
	    {
	      snprintf(errMessage, ERRSIZE, "fixed used twice in minLength");
	      yyerror(errMessage);
	    }
	  if (strcmp((*iter)->val, "true") == 0)
	    minl->fixed = XmlCppBase::yes;
	  else if (strcmp((*iter)->val, "false") == 0)
	    minl->fixed = XmlCppBase::no;
	  else
	    {
	      snprintf(errMessage, ERRSIZE,
		       "bad fixed \"%s\" in minLength", (*iter)->val);
	      yyerror(errMessage);
	    }
	}
      else if ((*iter)->name == VALUE)
	{
	  if (minl->value)
	    {
	      snprintf(errMessage, ERRSIZE, "value used twice in minLength");
	      yyerror(errMessage);
	    }
	  minl->value = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in minLength",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (minl->value == 0)
    yyerror("value missing in minLength");
  if (sscanf(minl->value, "%u", &(minl->intVal)) == 0)
    {
      snprintf(errMessage, ERRSIZE,
	       "bad length value \"%s\"  in minLength", minl->value);
      yyerror(errMessage);
    }
}

/********************************************************************/

void doXmlPatternAttributes(
 XmlPattern * pat,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  FILE * patFile = 0;

  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (pat->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in pattern");
	      yyerror(errMessage);
	    }
	  pat->id = (*iter)->val;
	}
      else if ((*iter)->name == VALUE)
	{
	  if (pat->value)
	    {
	      snprintf(errMessage, ERRSIZE, "value used twice in pattern");
	      yyerror(errMessage);
	    }
	  pat->value = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE, "bad attribute name \"%s\" in pattern",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (pat->value == 0)
    {
      yyerror("value missing in pattern");
    }
#ifndef NOPATTERN
  else
    {
      patFile = fopen("tempPatFile", "w");
      if (patFile == 0)
	{
	  fprintf(stderr, "cannot open tempPatFile for writing\n");
	  exit(1);
	}
      fprintf(patFile, "\"%s\"", pat->value);
      fclose(patFile);
      yyrein = fopen("tempPatFile", "r");
      if (yyreparse())
	{
	  fprintf(stderr, "pattern value %s is bad\n", pat->value);
	  exit(1);
	}
      fclose(yyrein);
    }
#endif
}

/********************************************************************/

void doXmlSchemaHeaderAttributes(
 XmlSchemaHeader * header,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (header->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in header");
	      yyerror(errMessage);
	    }
	  header->id = (*iter)->val;
	}
      else if ((*iter)->name == VERSION)
	{
	  if (header->version)
	    {
	      snprintf(errMessage, ERRSIZE, "version used twice in header");
	      yyerror(errMessage);
	    }
	  header->version = (*iter)->val;
	}
      else if ((*iter)->name == ATTRIBUTEFORMDEFAULT)
	{
	  if (header->attributeFormDefault != XmlCppBase::formNone)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "attributeFormDefault used twice in header");
	      yyerror(errMessage);
	    }
	  else if (strcmp((*iter)->val, "qualified") == 0)
	    header->attributeFormDefault = XmlCppBase::qualified;
	  else if (strcmp((*iter)->val, "unqualified") == 0)
	    header->attributeFormDefault = XmlCppBase::unqualified;
	  else
	    {
	      snprintf(errMessage, ERRSIZE,
		       "bad attributeFormDefault \"%s\"", (*iter)->val);
	      yyerror(errMessage);
	    }
	}
      else if ((*iter)->name == ELEMENTFORMDEFAULT)
	{
	  if (header->elementFormDefault != XmlCppBase::formNone)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "elementFormDefault used twice in header");
	      yyerror(errMessage);
	    }
	  else if (strcmp((*iter)->val, "qualified") == 0)
	    header->elementFormDefault = XmlCppBase::qualified;
	  else if (strcmp((*iter)->val, "unqualified") == 0)
	    header->elementFormDefault = XmlCppBase::unqualified;
	  else
	    {
	      snprintf(errMessage, ERRSIZE,
		       "bad elementFormDefault \"%s\"", (*iter)->val);
	      yyerror(errMessage);
	    }
	}
      else if ((*iter)->name == TARGETNAMESPACE)
	{
	  if (header->targetNamespace)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "targetNamespace used twice in header");
	      yyerror(errMessage);
	    }
	  if ((*iter)->pref)
	    yyerror("bug: targetNamespace should not have prefix");
	  header->targetNamespace = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in schema header",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
}

/********************************************************************/

void doXmlSelectorAttributes(
 XmlSelector * sel,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == XPATH)
	{
	  if (sel->xpath)
	    {
	      snprintf(errMessage, ERRSIZE, "xpath used twice in selector");
	      yyerror(errMessage);
	    }
	  sel->xpath = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in selector",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (sel->xpath == 0)
    yyerror("xpath missing in selector");
}

/********************************************************************/

void doXmlSequenceAttributes(
 XmlSequence * seq,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (seq->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in sequence");
	      yyerror(errMessage);
	    }
	  seq->id = (*iter)->val;
	}
      else if ((*iter)->name == MAXOCCURS)
	{
	  if (seq->maxOccurs != -2)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "maxOccurs used twice in sequence");
	      yyerror(errMessage);
	    }
	  if (strcmp((*iter)->val, "unbounded") == 0)
	    seq->maxOccurs = -1;
	  else
	    seq->maxOccurs = atoi((*iter)->val);
	}
      else if ((*iter)->name == MINOCCURS)
	{
	  if (seq->minOccurs != -2)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "minOccurs used twice in sequence");
	      yyerror(errMessage);
	    }
	  seq->minOccurs = atoi((*iter)->val);
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in sequence",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
}

/********************************************************************/

void doXmlSimpleContentAttributes(
 XmlSimpleContent * cont,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (cont->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in simpleContent");
	      yyerror(errMessage);
	    }
	  cont->id = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in simpleContent",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
}


/********************************************************************/

void doXmlSimpleContentExtensionAttributes(
 XmlSimpleContentExtension * extend,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (extend->id)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "id used twice in simple content extension");
	      yyerror(errMessage);
	    }
	  extend->id = (*iter)->val;
	}
      else if ((*iter)->name == BASE)
	{
	  if (extend->base)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "base used twice in simple content extension");
	      yyerror(errMessage);
	    }
	  extend->basePrefix = (*iter)->pref;
	  extend->base = (*iter)->val;
	  extend->newBase = XmlCppBase::modifyName(extend->base);
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in simple content extension",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (extend->base == 0)
    yyerror("simpleContentExtension must have base");
}

/********************************************************************/

void doXmlSimpleContentRestrictionAttributes(
 XmlSimpleContentRestriction * rest,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (rest->id)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "id used twice in simple content restriction");
	      yyerror(errMessage);
	    }
	  rest->id = (*iter)->val;
	}
      else if ((*iter)->name == BASE)
	{
	  if (rest->base)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "base used twice in simple content restriction");
	      yyerror(errMessage);
	    }
	  rest->basePrefix = (*iter)->pref;
	  rest->base = (*iter)->val;
	  rest->newBase = XmlCppBase::modifyName(rest->base);
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in simple content restriction",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (rest->base == 0)
    yyerror("simpleContentRestriction must have base");
}

/********************************************************************/

/* doXmlSimpleListAttributes

This exits if itemType is missing. The rules for XML schema and the
XmlSimpleList class allow an embedded type definition, but we are handling
only venetian blind format schemas, which (by definition) do not have
embedded types.

*/

void doXmlSimpleListAttributes(
 XmlSimpleList * liz,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (liz->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in simple list");
	      yyerror(errMessage);
	    }
	  liz->id = (*iter)->val;
	}
      else if ((*iter)->name == ITEMTYPE)
	{
	  if (liz->itemType)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "item type used twice in simple list");
	      yyerror(errMessage);
	    }
	  liz->typePrefix = (*iter)->pref;
	  liz->itemType = (*iter)->val;
	  liz->newItemType = XmlCppBase::modifyName(liz->itemType);
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in simple list",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (liz->itemType == 0)
    yyerror("simpleList must have item type");
}

/********************************************************************/

void doXmlSimpleRestrictionAttributes(
 XmlSimpleRestriction * rest,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (rest->id)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "id used twice in simple restriction");
	      yyerror(errMessage);
	    }
	  rest->id = (*iter)->val;
	}
      else if ((*iter)->name == BASE)
	{
	  if (rest->base)
	    {
	      snprintf(errMessage, ERRSIZE,
		       "base used twice in simple restriction");
	      yyerror(errMessage);
	    }
	  rest->basePrefix = (*iter)->pref;
	  rest->base = (*iter)->val;
	  rest->newBase = XmlCppBase::modifyName(rest->base);
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in simple restriction",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (rest->base == 0)
    yyerror("simpleRestriction must have base");
}

/********************************************************************/

/* doXmlSimpleTypeAttributes

The value of the "final" attribute is a string that may have up to
three names separated by spaces. If the "final" attribute is used,
this function parses the string and builds an instance of the XmlFinal
class. The string may also be the empty string; in that case, no
instance of the XmlFinal class is built.

This exits if name is missing. We are assuming all schemas are in venetian
blind format, so no anonymous simple types may occur.

This also sets the simple type's value of owlPrefix for the
XmlSimpleType to the globalOwlPrefix, which may be the empty string
("") or a prefix with some characters.

*/

void doXmlSimpleTypeAttributes(
 XmlSimpleType * simp,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  char item1[80];
  char item2[80];
  char item3[80];
  char item4[80];
  int numItems;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (simp->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in simpleType");
	      yyerror(errMessage);
	    }
	  simp->id = (*iter)->val;
	}
      else if ((*iter)->name == NAME)
	{
	  if (simp->name)
	    {
	      snprintf(errMessage, ERRSIZE, "name used twice in simpleType");
	      yyerror(errMessage);
	    }
	  simp->name = (*iter)->val;
	  simp->newName = XmlCppBase::modifyName(simp->name);
	}
      else if ((*iter)->name == FINAL)
	{
	  numItems = sscanf((*iter)->val, "%s %s %s %s",
			    item1, item2, item3, item4);
	  if (numItems > 3)
	    yyerror("too many items in final of simpleType");
	  if (numItems > 2)
	    {
	      if ((strcmp(item3, item2) == 0) ||
		  (strcmp(item3, item1) == 0))
		yyerror("duplicate items in final of simpleType");
	      if (strcmp(item3, "#all") == 0)
		yyerror("other items with #all in final of simpleType");
	      simp->final = new XmlFinal();
	      if (strcmp(item3, "list") == 0)
		simp->final->list = true;
	      else if (strcmp(item3, "union") == 0)
		simp->final->younion = true;
	      else if (strcmp(item3, "restriction") == 0)
		simp->final->restriction = true;
	      else
		{
		  snprintf(errMessage, ERRSIZE,
			   "bad item %s in final of simpleType",
			   item3);
		  yyerror(errMessage);
		}
	    }
	  if (numItems > 1)
	    {
	      if (strcmp(item2, item1) == 0)
		yyerror("duplicate items in final of simpleType");
	      if (strcmp(item2, "#all") == 0)
		yyerror("other items with #all in final of simpleType");
	      if (simp->final == 0)
		simp->final = new XmlFinal();
	      if (strcmp(item2, "list") == 0)
		simp->final->list = true;
	      else if (strcmp(item2, "union") == 0)
		simp->final->younion = true;
	      else if (strcmp(item2, "restriction") == 0)
		simp->final->restriction = true;
	      else
		{
		  snprintf(errMessage, ERRSIZE,
			   "bad item %s in final of simpleType", item2);
		  yyerror(errMessage);
		}
	    }
	  if (numItems > 0)
	    {
	      if (simp->final == 0)
		simp->final = new XmlFinal();
	      else if (strcmp(item1, "#all") == 0)
		yyerror("other items with #all in final of simpleType");
	      if (strcmp(item1, "#all") == 0)
		simp->final->all = true;
	      else if (strcmp(item1, "list") == 0)
		simp->final->list = true;
	      else if (strcmp(item1, "union") == 0)
		simp->final->younion = true;
	      else if (strcmp(item1, "restriction") == 0)
		simp->final->restriction = true;
	      else
		{
		  snprintf(errMessage, ERRSIZE,
			   "bad item %s in final of simpleType", item1);
		  yyerror(errMessage);
		}
	    }
	}
      else
	{
	  snprintf(errMessage, ERRSIZE,
		   "bad attribute name \"%s\" in simpleType",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (simp->name == 0)
    yyerror("name missing in simpleType");
  simp->owlPrefix = globalOwlPrefix;
}

/********************************************************************/

void doXmlUniqueAttributes(
 XmlUnique * unique,
 std::list<XmlAttribPair *> * atts)
{
  std::list<XmlAttribPair *>::iterator iter;
  for (iter = atts->begin(); iter != atts->end(); iter++)
    {
      if ((*iter)->name == ID)
	{
	  if (unique->id)
	    {
	      snprintf(errMessage, ERRSIZE, "id used twice in unique");
	      yyerror(errMessage);
	    }
	  unique->id = (*iter)->val;
	}
      else if ((*iter)->name == NAME)
	{
	  if (unique->name)
	    {
	      snprintf(errMessage, ERRSIZE, "name used twice in unique");
	      yyerror(errMessage);
	    }
	  unique->name = (*iter)->val;
	}
      else
	{
	  snprintf(errMessage, ERRSIZE, "bad attribute name \"%s\" in unique",
		   attNames[(*iter)->name - ABSTRACT]);
	  yyerror(errMessage);
	}
    }
  if (unique->name == 0)
    yyerror("name missing in unique");
}

/********************************************************************/





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
   by #include "xmlSchemaYACC.hh".  */
#ifndef YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_TOOLS_CPP_XML_COMMON_SRC_XMLSCHEMAYACC_HH_INCLUDED
# define YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_TOOLS_CPP_XML_COMMON_SRC_XMLSCHEMAYACC_HH_INCLUDED
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
    ABSTRACT = 258,
    ATTRIBUTEFORMDEFAULT = 259,
    BAD = 260,
    BASE = 261,
    DEFALT = 262,
    ELEMENTFORMDEFAULT = 263,
    ENCODING = 264,
    ENDCOMMENT = 265,
    ENDITEM = 266,
    ENDWHOLEITEM = 267,
    ENDVERSION = 268,
    FINAL = 269,
    FIXED = 270,
    FORM = 271,
    ID = 272,
    ITEMTYPE = 273,
    MAXOCCURS = 274,
    MINOCCURS = 275,
    MIXED = 276,
    NAME = 277,
    NILLABLE = 278,
    NAMESPACE = 279,
    PROCESSCONTENTS = 280,
    REF = 281,
    REFER = 282,
    SCHEMALOCATION = 283,
    SOURCE = 284,
    STARTANNOTATION = 285,
    STARTCOMMENT = 286,
    STARTINITEM = 287,
    STARTOUTITEM = 288,
    STARTVERSION = 289,
    SUBSTITUTIONGROUP = 290,
    TARGETNAMESPACE = 291,
    TERMINALSTRING = 292,
    TYP = 293,
    USE = 294,
    VALUE = 295,
    VERSION = 296,
    XPATH = 297,
    XMLNSPREFIX = 298,
    XSANNOTATION = 299,
    XSANY = 300,
    XSAPPINFO = 301,
    XSATTRIBUTE = 302,
    XSATTRIBUTEGROUP = 303,
    XSCHOICE = 304,
    XSCOMPLEXCONTENT = 305,
    XSCOMPLEXTYPE = 306,
    XSDOCUMENTATION = 307,
    XSELEMENT = 308,
    XSENUMERATION = 309,
    XSEXTENSION = 310,
    XSFIELD = 311,
    XSGROUP = 312,
    XSIMPORT = 313,
    XSINCLUDE = 314,
    XSKEY = 315,
    XSKEYREF = 316,
    XSLENGTH = 317,
    XSLIST = 318,
    XSMAXEXCLUSIVE = 319,
    XSMAXINCLUSIVE = 320,
    XSMAXLENGTH = 321,
    XSMINEXCLUSIVE = 322,
    XSMININCLUSIVE = 323,
    XSMINLENGTH = 324,
    XSPATTERN = 325,
    XSRESTRICTION = 326,
    XSSCHEMA = 327,
    XSSELECTOR = 328,
    XSSEQUENCE = 329,
    XSSIMPLECONTENT = 330,
    XSSIMPLETYPE = 331,
    XSUNIQUE = 332,
    XMLVERSION = 333
  };
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE YYSTYPE;
union YYSTYPE
{


  XmlAnnotation *                   annotationVal;
  std::list<XmlAnnotation *> *      annotationListVal;
  XmlAnnoType *                     annoTypeVal;
  std::list<XmlAnnoType *> *        annoTypeListVal;
  XmlAny *                          anyVal;
  XmlAppinfo *                      appinfoVal;
  XmlAttributor *                   attributorVal;
  std::list<XmlAttributor *> *      attributorListVal;
  XmlAttribPair *                   attribPairVal;
  std::list<XmlAttribPair *> *      attribPairListVal;
  XmlAttributeLoner *               attributeLonerVal;
  XmlAttributeLonerRefable *        attributeLonerRefableVal;
  XmlAttributeGroupRef *            attributeGroupRefVal;
  XmlAttributeGroupRefable *        attributeGroupRefableVal;
  XmlChoice *                       choiceVal;
  XmlChoSeqItem *                   choSeqItemVal;
  std::list<XmlChoSeqItem *> *      choSeqItemListVal;
  std::string *                     commentVal;
  std::list<std::string *> *        commentListVal;
  XmlComplexContentItem *           complexContentItemVal;
  XmlComplexContent *               complexContentVal;
  XmlComplexExtension *             complexExtensionVal;
  XmlComplexRestriction *           complexRestrictionVal;
  XmlComplexTypeItem *              complexTypeItemVal;
  XmlComplexType *                  complexTypeVal;
  XmlCppBase *                      cppBaseVal;
  XmlCppBase::formE                 formEVal;
  XmlDocumentation *                documentationVal;
  XmlElementGroup *                 elementGroupVal;
  XmlElementGroupRef *              elementGroupRefVal;
  XmlElementLocal *                 elementLocalVal;
  XmlElementRefable *               elementRefableVal;
  XmlEnumeration *                  enumerationVal;
  XmlField *                        fieldVal;
  std::list<XmlField *> *           fieldListVal;
  XmlFinal *                        finalVal;
  XmlIdConstraint *                 idConstraintVal;
  std::list<XmlIdConstraint *> *    idConstraintListVal;
  XmlImport *                       importVal;
  XmlInclude *                      includeVal;
  XmlKey *                          keyVal;
  XmlKeyref *                       keyrefVal;
  XmlLength *                       lengthVal;
  XmlMaxExclusive *                 maxExclusiveVal;
  XmlMaxInclusive *                 maxInclusiveVal;
  XmlMaxLength *                    maxLengthVal;
  XmlMinExclusive *                 minExclusiveVal;
  XmlMinInclusive *                 minInclusiveVal;
  XmlMinLength *                    minLengthVal;
  XmlNsPair *                       nsPairVal;
  std::list<XmlNsPair *> *          nsPairListVal;
  XmlOtherContent *                 otherContentVal;
  XmlOtherContentBase *             otherContentBaseVal;
  XmlPattern *                      patternVal;
  XmlRestrictionType *              restrictionTypeVal;
  std::list<XmlRestrictionType *> * restrictionTypeListVal;
  XmlSchema *                       schemaVal;
  XmlSchemaContent1 *               schemaContent1Val;
  std::list<XmlSchemaContent1 *> *  schemaContent1ListVal;
  XmlSchemaContent2 *               schemaContent2Val;
  std::list<XmlSchemaContent2 *> *  schemaContent2ListVal;
  XmlSchemaFile *                   schemaFileVal;
  XmlSchemaHeader *                 schemaHeaderVal;
  XmlSelector *                     selectorVal;
  XmlSequence *                     sequenceVal;
  XmlSimpleContent *                simpleContentVal;
  XmlSimpleContentItem *            simpleContentItemVal;
  XmlSimpleContentExtension *       simpleContentExtensionVal;
  XmlSimpleContentRestriction *     simpleContentRestrictionVal;
  XmlSimpleItem *                   simpleItemVal;
  XmlSimpleList *                   simpleListVal;
  XmlSimpleRestriction *            simpleRestrictionVal;
  XmlSimpleType *                   simpleTypeVal;
  XmlType *                         typVal;
  XmlUnique *                       uniqueVal;
  XmlVersion *                      versionVal;
  
  double        dVal;
  int           iVal;
  char *        sVal;


};
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;

int yyparse (void);

#endif /* !YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_TOOLS_CPP_XML_COMMON_SRC_XMLSCHEMAYACC_HH_INCLUDED  */

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
#define YYLAST   2340

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  79
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  76
/* YYNRULES -- Number of rules.  */
#define YYNRULES  259
/* YYNSTATES -- Number of states.  */
#define YYNSTATES  772

/* YYTRANSLATE[YYX] -- Symbol number corresponding to YYX as returned
   by yylex, with out-of-bounds checking.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   333

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
      75,    76,    77,    78
};

#if YYDEBUG
  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,  2994,  2994,  2998,  3005,  3007,  3013,  3014,  3031,  3032,
    3033,  3034,  3035,  3036,  3037,  3038,  3039,  3040,  3041,  3042,
    3043,  3044,  3045,  3046,  3047,  3048,  3049,  3050,  3051,  3052,
    3053,  3054,  3055,  3056,  3057,  3058,  3062,  3067,  3068,  3072,
    3080,  3089,  3097,  3101,  3108,  3109,  3114,  3115,  3122,  3125,
    3130,  3139,  3146,  3155,  3159,  3164,  3171,  3178,  3189,  3193,
    3200,  3207,  3218,  3222,  3227,  3237,  3242,  3249,  3256,  3267,
    3268,  3272,  3276,  3283,  3284,  3285,  3286,  3287,  3291,  3295,
    3302,  3309,  3320,  3325,  3329,  3336,  3343,  3354,  3355,  3359,
    3363,  3368,  3375,  3383,  3391,  3400,  3407,  3415,  3425,  3429,
    3436,  3444,  3452,  3461,  3468,  3476,  3486,  3487,  3488,  3492,
    3497,  3503,  3510,  3518,  3529,  3536,  3545,  3553,  3563,  3567,
    3572,  3582,  3589,  3598,  3608,  3615,  3623,  3633,  3644,  3649,
    3656,  3663,  3670,  3678,  3686,  3694,  3703,  3710,  3715,  3722,
    3726,  3731,  3741,  3745,  3753,  3757,  3764,  3766,  3768,  3773,
    3777,  3784,  3788,  3797,  3799,  3805,  3812,  3821,  3829,  3842,
    3849,  3858,  3866,  3879,  3883,  3893,  3897,  3907,  3911,  3921,
    3925,  3935,  3939,  3950,  3954,  3964,  3968,  3978,  3983,  3987,
    3994,  3996,  3998,  4003,  4004,  4005,  4009,  4013,  4023,  4024,
    4025,  4026,  4027,  4028,  4029,  4030,  4031,  4035,  4039,  4046,
    4052,  4053,  4054,  4059,  4062,  4069,  4070,  4074,  4075,  4079,
    4080,  4084,  4085,  4089,  4090,  4094,  4095,  4102,  4106,  4113,
    4125,  4129,  4137,  4144,  4155,  4159,  4164,  4171,  4178,  4189,
    4190,  4194,  4198,  4203,  4210,  4217,  4228,  4233,  4239,  4247,
    4254,  4262,  4270,  4278,  4286,  4298,  4299,  4303,  4307,  4315,
    4320,  4326,  4336,  4343,  4354,  4361,  4370,  4378,  4391,  4396
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || 0
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "ABSTRACT", "ATTRIBUTEFORMDEFAULT",
  "BAD", "BASE", "DEFALT", "ELEMENTFORMDEFAULT", "ENCODING", "ENDCOMMENT",
  "ENDITEM", "ENDWHOLEITEM", "ENDVERSION", "FINAL", "FIXED", "FORM", "ID",
  "ITEMTYPE", "MAXOCCURS", "MINOCCURS", "MIXED", "NAME", "NILLABLE",
  "NAMESPACE", "PROCESSCONTENTS", "REF", "REFER", "SCHEMALOCATION",
  "SOURCE", "STARTANNOTATION", "STARTCOMMENT", "STARTINITEM",
  "STARTOUTITEM", "STARTVERSION", "SUBSTITUTIONGROUP", "TARGETNAMESPACE",
  "TERMINALSTRING", "TYP", "USE", "VALUE", "VERSION", "XPATH",
  "XMLNSPREFIX", "XSANNOTATION", "XSANY", "XSAPPINFO", "XSATTRIBUTE",
  "XSATTRIBUTEGROUP", "XSCHOICE", "XSCOMPLEXCONTENT", "XSCOMPLEXTYPE",
  "XSDOCUMENTATION", "XSELEMENT", "XSENUMERATION", "XSEXTENSION",
  "XSFIELD", "XSGROUP", "XSIMPORT", "XSINCLUDE", "XSKEY", "XSKEYREF",
  "XSLENGTH", "XSLIST", "XSMAXEXCLUSIVE", "XSMAXINCLUSIVE", "XSMAXLENGTH",
  "XSMINEXCLUSIVE", "XSMININCLUSIVE", "XSMINLENGTH", "XSPATTERN",
  "XSRESTRICTION", "XSSCHEMA", "XSSELECTOR", "XSSEQUENCE",
  "XSSIMPLECONTENT", "XSSIMPLETYPE", "XSUNIQUE", "XMLVERSION", "$accept",
  "xmlSchemaFile", "attributePair", "attributePairList", "attributeName",
  "schemaLocation", "typeDef", "xmlAnnotation", "xmlAnnotationList",
  "xmlAnnoType", "xmlAnnoTypeList", "xmlAny", "xmlAppinfo",
  "xmlAttributeLoner", "xmlAttributeLonerRefable", "xmlAttributeGroupRef",
  "xmlAttributeGroupRefable", "xmlAttributor", "xmlAttributorList",
  "xmlChoSeqItem", "xmlChoSeqItemList", "xmlChoice", "xmlComment",
  "xmlCommentList", "xmlComplexContent", "xmlComplexContentItem",
  "xmlComplexExtension", "xmlComplexRestriction", "xmlComplexTypeItem",
  "xmlComplexType", "xmlDocumentation", "xmlElementGroup",
  "xmlElementGroupRef", "xmlElementLocal", "xmlElementRefable",
  "xmlEnumeration", "xmlField", "xmlFieldList", "xmlIdConstraint",
  "xmlIdConstraintList", "xmlImport", "xmlInclude", "xmlKey", "xmlKeyref",
  "xmlLength", "xmlMaxExclusive", "xmlMaxInclusive", "xmlMaxLength",
  "xmlMinExclusive", "xmlMinInclusive", "xmlMinLength", "xmlNs",
  "xmlNsList", "xmlOtherContent", "xmlOtherContentBase", "xmlPattern",
  "xmlRestrictionType", "xmlRestrictionTypeList", "xmlSchema",
  "xmlSchemaContent1", "xmlSchemaContent1List", "xmlSchemaContent2",
  "xmlSchemaContent2List", "xmlSchemaHeader", "xmlSelector", "xmlSequence",
  "xmlSimpleContent", "xmlSimpleContentItem", "xmlSimpleContentExtension",
  "xmlSimpleContentRestriction", "xmlSimpleItem", "xmlSimpleList",
  "xmlSimpleRestriction", "xmlSimpleType", "xmlUnique", "xmlVersion", YY_NULLPTR
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
     325,   326,   327,   328,   329,   330,   331,   332,   333
};
# endif

#define YYPACT_NINF -528

#define yypact_value_is_default(Yystate) \
  (!!((Yystate) == (-528)))

#define YYTABLE_NINF -1

#define yytable_value_is_error(Yytable_value) \
  0

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
     STATE-NUM.  */
static const yytype_int16 yypact[] =
{
     -15,   -33,    47,   121,    26,  -528,    69,    10,  -528,   121,
    -528,  -528,   102,  -528,    46,  -528,  -528,   104,    82,  -528,
      85,  -528,    46,  -528,   139,  -528,   149,   149,    76,   149,
     149,   149,  -528,  -528,  -528,  -528,   184,   149,   116,  -528,
    1658,  -528,  1698,  -528,  -528,  -528,  -528,  -528,  -528,   111,
    -528,  -528,   149,   149,  -528,   149,   149,   149,    80,    60,
    -528,   149,  -528,  -528,  -528,  -528,  -528,  -528,  -528,  -528,
    -528,  -528,  -528,  -528,  -528,  -528,  -528,  -528,  -528,  -528,
    -528,  -528,  -528,  -528,  -528,  -528,  -528,  -528,  -528,  -528,
    -528,  -528,  -528,    55,  -528,   578,   618,   658,   698,  1738,
     738,   133,   210,  1778,  -528,  1818,   137,  -528,   140,   201,
     163,  -528,   206,  -528,   131,  -528,   135,  -528,   253,   149,
    -528,  -528,   149,  -528,   308,  -528,  -528,  -528,    78,   106,
    -528,  -528,  -528,   108,   275,   125,   297,   335,  -528,  -528,
    -528,   376,   178,   148,   378,   176,  -528,  -528,   180,  -528,
    -528,   176,  -528,  -528,   -28,   166,   235,   310,    76,   381,
    -528,  -528,   324,  -528,  -528,  -528,  -528,   157,   213,  -528,
     218,  -528,  -528,  -528,  -528,   231,   240,   -37,   257,   259,
    -528,  -528,   382,  -528,  -528,   285,   252,   277,   304,  -528,
    -528,   284,   387,   288,  -528,  -528,  -528,  -528,  -528,  -528,
     406,   372,   400,   385,   176,  -528,  -528,  -528,   428,    43,
     388,   327,   412,   330,   333,    49,   437,  -528,   462,  -528,
     449,   443,   430,   433,  -528,  -528,   475,   434,   468,  1858,
    1898,  -528,   532,   469,   538,   778,   818,   540,   507,   542,
    1938,  1978,   858,  2018,   898,  -528,   546,   508,   552,  2058,
    2098,  2138,  -528,   556,   530,   580,   557,   341,   559,  -528,
    -528,  -528,   604,  -528,  2178,   938,   566,   620,   639,   644,
     978,  1018,   587,   660,   679,    19,    31,  -528,   684,  -528,
     211,  -528,   113,  -528,  -528,   692,  -528,   253,   345,   158,
    -528,   253,   217,  -528,  -528,   700,  -528,   348,   348,   348,
    -528,   719,  -528,   724,   690,   736,  2218,  2258,  2298,  -528,
     170,   246,  -528,   737,  -528,  -528,  -528,   718,  -528,   389,
    -528,   757,  -528,  -528,   723,   739,   731,   742,  -528,   740,
     396,   755,   722,   758,  -528,   213,  -528,   398,   -46,   776,
     777,  -528,  -528,   714,   779,   213,   411,   -34,   734,   413,
     782,  -528,  -528,  -528,   750,   795,   796,   795,   796,   795,
     796,  -528,  -528,   800,  -528,   348,   348,   348,   786,   815,
     797,   350,   353,   359,  -528,   788,   236,   781,  -528,  -528,
    -528,  -528,  -528,  -528,  -528,  -528,  -528,  -528,   415,  -528,
     838,   809,   852,   816,   856,   824,   855,   842,   879,   843,
     417,   846,  -528,  -528,  -528,   859,   853,   896,   851,   419,
     837,  -528,  -528,   917,   854,   897,   857,  -528,   796,   875,
    -528,   421,   796,   423,   796,   425,  -528,   795,   796,   795,
     796,   795,   796,   924,   902,   932,   895,   362,   915,   365,
     368,   916,   940,  -528,  -528,  -528,  -528,  -528,  -528,  -528,
    -528,  -528,   959,   900,  -528,  -528,   961,  -528,   964,  -528,
     972,   941,   976,  -528,   980,   960,   997,  1058,  1098,   962,
     999,  -528,  1000,   949,  1004,  1138,  1178,  -528,  1016,   953,
    1020,  1218,   427,  -528,   988,  -528,   429,   989,   431,   974,
     796,   435,   796,   438,   796,   440,  -528,  1038,  -528,  1041,
    1002,  1052,  1014,   371,  1015,  1060,  -528,  1258,  1298,  1338,
    1378,  1418,  1458,  1498,  1538,  1578,  -528,  1077,  -528,  -528,
    -528,  1078,  -528,  -528,  1079,  -528,   283,  -528,   375,  -528,
    1080,  -528,  -528,  1081,  -528,   289,  -528,   298,  -528,  -528,
    1084,  -528,  1070,  -528,  1047,  1618,  1097,  1050,  1117,  1053,
    1118,   444,  1071,   452,  1074,   463,  1055,  -528,  -528,  1132,
    -528,  1136,  1095,  1140,  -528,   174,  -528,   149,  -528,   149,
    -528,   149,  -528,   149,  -528,   149,  -528,   149,  -528,   149,
    -528,   149,  -528,  -528,  -528,  -528,    39,  1113,   466,   471,
     474,   482,   489,   492,  -528,  -528,  1114,   496,   503,   192,
    1099,   505,   509,   514,  -528,  1102,  1160,  1139,  -528,  -528,
    1172,  -528,  1176,  -528,  1128,  1180,  1147,  1198,  1133,  1200,
    -528,  -528,  1201,  -528,  1161,  1190,  1194,  1195,  1215,  1216,
    1217,  1219,  1222,  1230,  1220,  1196,   533,   537,  1212,  1213,
     555,  1221,   576,   595,  1224,  1232,   616,  1260,  1233,   619,
    1234,  1279,  1236,   635,   656,  1237,  1240,   659,  1280,  -528,
    1272,  -528,  -528,  1318,  -528,  1319,  -528,  1320,  -528,  -528,
    1321,  1281,  1285,  1284,  1278,  1302,  1303,  1283,  1300,  1301,
    -528,  1361,  1328,  1332,   675,  1364,  1377,  1336,  1397,  1339,
    1340,   696,  1398,  1401,  1344,  -528,  1412,  1372,  1417,  -528,
    1420,  1379,  1380,   699,  1437,  1438,  1381,  -528,  1444,  -528,
    -528,  -528,  -528,  1452,  1456,  1457,  1460,  1477,  1478,  1479,
    1480,  1481,  -528,  1484,  1492,  1453,  -528,  -528,  1496,  -528,
    1500,  1517,  1459,  -528,  -528,  1518,  -528,  1520,  -528,  -528,
    1521,  1524,  1472,  -528,  -528,  1536,  -528,  -528,  -528,  -528,
    -528,  -528,  -528,  -528,  -528,  -528,  -528,  -528,  1537,  -528,
    -528,  -528,  1540,  -528,  -528,  -528,  -528,  1557,  -528,  -528,
    -528,  -528
};

  /* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
     Performed when YYTABLE does not specify something else to do.  Zero
     means the default is an error.  */
static const yytype_uint16 yydefact[] =
{
       0,     0,     0,     0,     0,     1,     0,     0,    83,     0,
       2,   203,     0,    82,     0,    84,     3,     0,     0,   258,
       0,   178,     6,     6,     0,   202,   213,   215,    41,   209,
     205,   207,   201,   200,   204,   217,     0,   211,     0,   177,
       0,   179,     0,     6,     6,     6,     6,     6,     6,     0,
       6,    42,   214,   216,     6,   210,   206,   208,     0,     0,
     218,   212,   259,     8,     9,    10,    11,    12,   219,    13,
      14,    15,    16,    17,    19,    20,    21,    22,    23,    18,
      24,    25,    26,    27,    28,    29,    30,    31,    32,    33,
      34,    35,     7,     0,    46,     0,     0,     0,     0,     0,
       0,     0,     0,     0,    43,     0,     0,     4,     0,     0,
       0,    58,     0,    65,     0,   109,     0,   138,     0,     0,
     151,    36,     0,   153,     0,    46,   199,     5,     0,     0,
      47,    45,    44,     0,     0,     0,     0,     0,    69,    70,
      71,     0,     0,     0,     0,   181,   183,   106,     0,   185,
     107,   180,   184,   108,     0,     0,     0,     0,    41,     0,
      37,   149,     0,   146,   147,    38,   148,     0,     0,    73,
       0,    74,    75,    76,    77,     0,     0,     0,     0,     0,
     245,   246,     0,     6,     6,     0,     0,     0,     0,     6,
       6,     0,     0,     0,    72,     6,     6,     6,     6,     6,
       0,     0,     0,     0,   182,     6,     6,     6,     0,     0,
       0,     0,     0,     0,     0,     0,     0,   150,     6,     6,
       0,     0,     0,     0,     6,     6,     0,     0,     0,     0,
       0,    39,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   110,     0,     0,     0,     0,
       0,     0,   137,     0,     0,     0,     0,     0,     0,     6,
       6,     6,     0,    48,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    60,     0,    59,
       0,    53,     0,    62,    67,     0,    66,     0,     0,     0,
     118,     0,     0,   224,   112,     0,   111,     0,     0,     0,
     131,     0,   130,     0,     0,     0,     0,     0,     0,   136,
       0,     0,   129,     0,   117,   152,   154,     0,   247,     0,
     249,     0,   252,    40,     0,     0,     0,     0,    61,     0,
       0,     0,     0,     0,    68,     0,    78,     0,     0,     0,
       0,    88,    87,     0,     0,     0,     0,     0,     0,     0,
       0,   229,   230,   113,     0,     0,     0,     0,     0,     0,
       0,   134,   132,     0,   133,     0,     0,     0,     0,     0,
       0,     0,     0,     0,   116,     0,     0,     0,   196,   188,
     189,   190,   191,   192,   193,   194,   195,   197,     0,   253,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,    79,     6,     6,     0,     0,     0,     0,     0,
       0,     6,     6,     0,     0,     0,     0,     6,     0,     0,
     144,     0,     0,     0,     0,     0,   135,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     6,     6,     6,     6,     6,     6,     6,
       6,     6,     0,     0,   198,    52,     0,   115,     0,    54,
       0,     0,     0,    63,     0,     0,     0,     0,     0,     0,
       0,   119,     0,     0,     0,     0,     0,   225,     0,     0,
       0,     0,     0,     6,     0,   145,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,    49,     0,   128,     0,
       0,     0,     0,     0,     0,     0,   248,     0,     0,     0,
       0,     0,     0,     0,     0,     0,   250,     0,    51,   114,
      56,     0,    55,    64,     0,    80,     0,    89,     0,    98,
       0,    85,   120,     0,   222,     0,   231,     0,   236,   226,
       0,   227,     0,   220,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    50,   122,     0,
     121,     0,     0,     0,   124,     0,   139,     0,   163,     0,
     165,     0,   167,     0,   169,     0,   171,     0,   173,     0,
     175,     0,   186,   251,    57,    81,     0,     0,     0,     0,
       0,     0,     0,     0,    86,   223,     0,     0,     0,     0,
       0,     0,     0,     0,   228,     0,     0,     0,   142,   155,
       0,   159,     0,   254,     0,     0,     0,     0,     0,     0,
     126,   123,     0,   125,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,   157,
       0,   161,   256,     0,   156,     0,   160,     0,   255,   127,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
      90,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   232,     0,     0,     0,   237,
       0,     0,     0,     0,     0,     0,     0,   221,     0,   158,
     162,   257,   140,     0,     0,     0,     0,     0,     0,     0,
       0,     0,    91,     0,     0,     0,    97,    95,     0,    99,
       0,     0,     0,   105,   103,     0,   233,     0,   234,   238,
       0,     0,     0,   240,   239,     0,   143,   141,   164,   166,
     168,   170,   172,   174,   176,   187,    93,    92,     0,    96,
     101,   100,     0,   104,   235,   242,   241,     0,   243,    94,
     102,   244
};

  /* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -528,  -528,  -528,   128,  -528,  -528,  -152,   -17,    30,  -528,
    1445,  -528,  -528,  -528,  -528,  -528,  -528,  -123,  -110,  -114,
    -245,  -102,    -2,  1566,  -528,  1244,  -528,  -528,  1427,    -6,
    -528,  -528,   -94,  -528,  -528,  -528,  -370,  -352,  -149,  -142,
    -528,  -528,  -528,  -528,  -528,  -528,  -528,  -528,  -528,  -528,
    -528,  1550,  -528,  -528,  -497,  -528,  -385,  -527,  1579,  -528,
    -528,  1539,  -528,  -528,  -282,   -86,  -528,  1238,  -528,  -528,
    1413,  -528,  -528,     7,  -528,  -528
};

  /* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     2,    92,    40,    93,   102,   156,    51,    52,   130,
     109,   169,   131,   138,    26,   139,    27,   140,   145,   336,
     337,   171,    28,   159,   147,   340,   341,   342,   148,   160,
     132,    30,   172,   173,    31,   378,   420,   421,   161,   162,
      32,    33,   163,   164,   379,   380,   381,   382,   383,   384,
     385,    21,    22,   150,   151,   386,   387,   388,    10,    34,
      17,    35,    36,    11,   356,   174,   153,   350,   351,   352,
     179,   180,   181,   165,   166,     3
};

  /* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
     positive, shift that token.  If negative, reduce the rule whose
     number is the opposite.  If YYTABLE_NINF, syntax error.  */
static const yytype_uint16 yytable[] =
{
      25,     8,   141,   454,   170,   213,   423,    15,   425,   403,
     603,    29,   146,   217,   211,   214,   358,   360,   194,     1,
     149,   411,   194,    45,    37,   404,   224,   192,   152,   590,
      29,   593,   205,   206,   225,   104,   104,   412,   104,   104,
     104,   204,   146,    37,   104,     4,   346,     5,    50,   207,
     149,   485,   324,   485,   220,   485,   325,    53,   152,    55,
      56,    57,   217,    12,   326,   217,   482,    61,   327,   194,
     486,   257,   488,   418,   654,   422,   491,   424,   493,    13,
     495,   194,    14,   428,   430,   432,   189,   190,   195,    20,
     400,   637,   107,   134,   643,   137,   197,   144,   108,   157,
     409,   168,   175,   205,   206,   176,    54,   178,   217,   259,
     260,    18,   485,   198,   158,    19,   485,   135,   485,    38,
     207,   485,    39,   485,   183,   485,   261,    43,    44,    62,
     184,    45,   106,    46,    23,     6,    24,    47,   551,   101,
     553,   187,   555,    23,     6,   490,   332,   492,   126,   494,
     185,    42,     6,     7,     8,     8,    50,    15,   188,   371,
       8,    23,     6,   142,   143,    23,     6,   154,   155,   373,
     121,    95,    96,    97,    98,    99,   100,   127,   103,    23,
       6,   485,   105,   485,    50,   485,    43,    44,    23,     6,
      45,   343,    46,    23,     6,   133,    47,    48,    49,   200,
      23,     6,   218,   368,    23,     6,   195,   624,   136,     8,
     219,     8,     8,   203,   197,    50,    58,    59,   454,   208,
     439,   122,   123,   402,   217,   189,   190,   195,   196,   437,
     440,   198,   402,   128,   129,   197,    23,     6,   136,   189,
     190,    23,     6,   133,   329,   167,   443,    23,     6,   347,
     348,   221,   198,   199,   444,     8,   445,   446,   447,   448,
     449,   450,   451,   330,   222,   333,     6,   209,   210,   454,
     335,   339,   344,   223,   345,   349,    23,     6,   154,   370,
     355,   357,   359,    23,     6,   167,   402,   331,   217,   177,
     443,   217,   227,   369,   372,   402,   231,   503,   444,   232,
     445,   446,   447,   448,   449,   450,   451,   133,   186,   158,
     233,   229,   230,    23,     6,   586,   587,   235,   236,    23,
       6,   136,   596,   240,   241,   242,   243,   244,    23,     6,
     599,   600,   237,   249,   250,   251,   239,   396,    23,     6,
     177,     6,   154,   212,   189,   190,   264,   265,   427,   429,
     431,   234,   270,   271,   217,     6,   209,   216,     6,   209,
     254,     6,   209,   256,     6,   209,   258,   136,   191,     8,
       8,     8,     6,   209,   304,    23,     6,   338,    23,     6,
     354,     6,   209,   436,     6,   154,   438,   306,   307,   308,
       6,   209,   441,     6,   209,   500,     6,   209,   502,     6,
     209,   504,     6,   209,   562,    23,     6,   586,   136,   193,
     142,   201,     6,   215,   128,   228,   589,   245,   592,   136,
     238,   376,   377,   246,   146,   598,   146,   602,   133,   395,
     167,   401,   149,   247,   149,     8,   248,     8,     8,   252,
     152,   253,   152,   167,   410,   347,   414,   376,   453,   167,
     465,   167,   473,   419,   484,   419,   487,   419,   489,   419,
     544,   419,   547,   419,   549,   255,   194,   419,   552,   194,
     419,   554,   419,   556,   263,   194,   419,   614,   636,   194,
     640,   642,   266,   646,   419,   616,   146,   649,   268,   146,
     262,   653,   269,   657,   149,   419,   618,   149,   586,   635,
     267,     8,   152,   136,   638,   152,   136,   639,   272,   588,
     273,   591,   274,   194,   586,   641,   278,   194,   597,   194,
     601,   136,   644,   194,   136,   645,   194,   684,   136,   648,
     194,   467,   468,   691,   194,   136,   650,   599,   652,   475,
     476,   136,   655,   277,   703,   481,   599,   656,   625,   279,
     626,   284,   627,   286,   628,   285,   629,   294,   630,   295,
     631,   194,   632,   296,   633,   136,   682,   300,   194,   136,
     683,   507,   508,   509,   510,   511,   512,   513,   514,   515,
     194,    63,    64,   301,    65,    66,    67,   136,   687,   110,
     111,   302,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   136,   689,
     303,   545,   305,    85,    86,   309,    87,    88,    89,    90,
      91,    63,    64,   313,    65,    66,    67,   136,   690,   112,
     113,   314,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   136,   694,
     315,   136,   697,    85,    86,   316,    87,    88,    89,    90,
      91,    63,    64,   321,    65,    66,    67,   136,   701,   114,
     115,   322,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   599,   702,
     323,   136,   706,    85,    86,   328,    87,    88,    89,    90,
      91,    63,    64,   334,    65,    66,    67,   136,   725,   116,
     117,   353,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   136,   732,
     361,   136,   742,    85,    86,   362,    87,    88,    89,    90,
      91,    63,    64,   363,    65,    66,    67,   364,   374,   119,
     120,   375,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   389,   390,
     398,   407,   391,    85,    86,   393,    87,    88,    89,    90,
      91,    63,    64,   392,    65,    66,    67,   394,   397,   280,
     281,   399,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   338,   413,
     406,   426,   408,    85,    86,   416,    87,    88,    89,    90,
      91,    63,    64,   417,    65,    66,    67,   354,   419,   282,
     283,   433,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   434,   455,
     435,   442,   452,    85,    86,   456,    87,    88,    89,    90,
      91,    63,    64,   457,    65,    66,    67,   459,   458,   289,
     290,   460,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   461,   462,
     463,   464,   469,    85,    86,   466,    87,    88,    89,    90,
      91,    63,    64,   470,    65,    66,    67,   471,   472,   292,
     293,   474,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   477,   478,
     479,   483,   480,    85,    86,   496,    87,    88,    89,    90,
      91,    63,    64,   498,    65,    66,    67,   497,   499,   311,
     312,   506,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   501,   505,
     516,   517,   518,    85,    86,   519,    87,    88,    89,    90,
      91,    63,    64,   520,    65,    66,    67,   522,   521,   317,
     318,   523,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   525,   524,
     531,   532,   530,    85,    86,   534,    87,    88,    89,    90,
      91,    63,    64,   533,    65,    66,    67,   539,   540,   319,
     320,   541,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   546,   557,
     548,   550,   558,    85,    86,   559,    87,    88,    89,    90,
      91,    63,    64,   560,    65,    66,    67,   561,   563,   526,
     527,   564,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   583,   584,
     585,   594,   595,    85,    86,   604,    87,    88,    89,    90,
      91,    63,    64,   605,    65,    66,    67,   606,   609,   528,
     529,   610,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   611,   613,
     612,   615,   619,    85,    86,   617,    87,    88,    89,    90,
      91,    63,    64,   620,    65,    66,    67,   621,   622,   535,
     536,   623,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   634,   647,
     651,   659,   660,    85,    86,   658,    87,    88,    89,    90,
      91,    63,    64,   661,    65,    66,    67,   662,   663,   537,
     538,   664,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   665,   666,
     667,   668,   669,    85,    86,   670,    87,    88,    89,    90,
      91,    63,    64,   671,    65,    66,    67,   672,   673,   542,
     543,   680,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   674,   675,
     676,   681,   677,    85,    86,   678,    87,    88,    89,    90,
      91,    63,    64,   679,    65,    66,    67,   685,   686,   565,
     566,   695,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   696,   698,
     699,   707,   688,    85,    86,   692,    87,    88,    89,    90,
      91,    63,    64,   693,    65,    66,    67,   700,   704,   567,
     568,   705,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   708,   709,
     710,   711,   712,    85,    86,   713,    87,    88,    89,    90,
      91,    63,    64,   716,    65,    66,    67,   714,   715,   569,
     570,   719,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   717,   720,
     718,   721,   722,    85,    86,   726,    87,    88,    89,    90,
      91,    63,    64,   723,    65,    66,    67,   724,   727,   571,
     572,   728,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   729,   733,
     730,   731,   734,    85,    86,   735,    87,    88,    89,    90,
      91,    63,    64,   736,    65,    66,    67,   737,   738,   573,
     574,   739,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   743,   744,
     740,   741,   745,    85,    86,   746,    87,    88,    89,    90,
      91,    63,    64,   747,    65,    66,    67,   748,   749,   575,
     576,   750,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   751,   752,
     753,   754,   755,    85,    86,   756,    87,    88,    89,    90,
      91,    63,    64,   757,    65,    66,    67,   759,   758,   577,
     578,   760,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   761,   763,
     762,   764,   765,    85,    86,   766,    87,    88,    89,    90,
      91,    63,    64,   767,    65,    66,    67,   768,   769,   579,
     580,   770,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,   771,     9,
     182,   202,    41,    85,    86,    60,    87,    88,    89,    90,
      91,    63,    64,   405,    65,    66,    67,   415,    16,   581,
     582,   226,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,     0,     0,
       0,     0,     0,    85,    86,     0,    87,    88,    89,    90,
      91,    63,    64,     0,    65,    66,    67,     0,     0,   607,
     608,     0,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,     0,     0,
       0,     0,     0,    85,    86,     0,    87,    88,    89,    90,
      91,    63,    64,     0,    65,    66,    67,     0,     0,    68,
       0,     0,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,     0,     0,
       0,     0,     0,    85,    86,     0,    87,    88,    89,    90,
      91,    63,    64,     0,    65,    66,    67,     0,     0,    94,
       0,     0,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,     0,     0,
       0,     0,     0,    85,    86,     0,    87,    88,    89,    90,
      91,    63,    64,     0,    65,    66,    67,     0,     0,   118,
       0,     0,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,     0,     0,
       0,     0,     0,    85,    86,     0,    87,    88,    89,    90,
      91,    63,    64,     0,    65,    66,    67,     0,     0,   124,
       0,     0,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,     0,     0,
       0,     0,     0,    85,    86,     0,    87,    88,    89,    90,
      91,    63,    64,     0,    65,    66,    67,     0,     0,   125,
       0,     0,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,     0,     0,
       0,     0,     0,    85,    86,     0,    87,    88,    89,    90,
      91,    63,    64,     0,    65,    66,    67,     0,     0,   275,
       0,     0,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,     0,     0,
       0,     0,     0,    85,    86,     0,    87,    88,    89,    90,
      91,    63,    64,     0,    65,    66,    67,     0,     0,   276,
       0,     0,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,     0,     0,
       0,     0,     0,    85,    86,     0,    87,    88,    89,    90,
      91,    63,    64,     0,    65,    66,    67,     0,     0,   287,
       0,     0,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,     0,     0,
       0,     0,     0,    85,    86,     0,    87,    88,    89,    90,
      91,    63,    64,     0,    65,    66,    67,     0,     0,   288,
       0,     0,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,     0,     0,
       0,     0,     0,    85,    86,     0,    87,    88,    89,    90,
      91,    63,    64,     0,    65,    66,    67,     0,     0,   291,
       0,     0,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,     0,     0,
       0,     0,     0,    85,    86,     0,    87,    88,    89,    90,
      91,    63,    64,     0,    65,    66,    67,     0,     0,   297,
       0,     0,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,     0,     0,
       0,     0,     0,    85,    86,     0,    87,    88,    89,    90,
      91,    63,    64,     0,    65,    66,    67,     0,     0,   298,
       0,     0,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,     0,     0,
       0,     0,     0,    85,    86,     0,    87,    88,    89,    90,
      91,    63,    64,     0,    65,    66,    67,     0,     0,   299,
       0,     0,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,     0,     0,
       0,     0,     0,    85,    86,     0,    87,    88,    89,    90,
      91,    63,    64,     0,    65,    66,    67,     0,     0,   310,
       0,     0,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,     0,     0,
       0,     0,     0,    85,    86,     0,    87,    88,    89,    90,
      91,    63,    64,     0,    65,    66,    67,     0,     0,   365,
       0,     0,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,     0,     0,
       0,     0,     0,    85,    86,     0,    87,    88,    89,    90,
      91,    63,    64,     0,    65,    66,    67,     0,     0,   366,
       0,     0,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,     0,     0,
       0,     0,     0,    85,    86,     0,    87,    88,    89,    90,
      91,    63,    64,     0,    65,    66,    67,     0,     0,   367,
       0,     0,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    81,    82,    83,    84,     0,     0,
       0,     0,     0,    85,    86,     0,    87,    88,    89,    90,
      91
};

static const yytype_int16 yycheck[] =
{
      17,     3,   112,   388,   118,   157,   358,     9,   360,    55,
     537,    17,   114,   162,   156,   157,   298,   299,   141,    34,
     114,    55,   145,    51,    17,    71,    63,   137,   114,   526,
      36,   528,    60,    61,    71,    52,    53,    71,    55,    56,
      57,   151,   144,    36,    61,    78,   291,     0,    76,    77,
     144,   421,    33,   423,   168,   425,    37,    27,   144,    29,
      30,    31,   211,    37,    33,   214,   418,    37,    37,   192,
     422,   213,   424,   355,   601,   357,   428,   359,   430,    10,
     432,   204,    72,   365,   366,   367,    47,    48,    49,    43,
     335,   588,    37,   110,   591,   112,    57,   114,    43,   116,
     345,   118,   119,    60,    61,   122,    30,   124,   257,    60,
      61,     9,   482,    74,   116,    13,   486,   110,   488,    37,
      77,   491,    37,   493,    46,   495,    77,    47,    48,    13,
      52,    51,    72,    53,    30,    31,    32,    57,   490,    28,
     492,   134,   494,    30,    31,   427,    33,   429,    11,   431,
      44,    23,    31,    32,   156,   157,    76,   159,    33,   311,
     162,    30,    31,    32,    33,    30,    31,    32,    33,   311,
      37,    43,    44,    45,    46,    47,    48,    37,    50,    30,
      31,   551,    54,   553,    76,   555,    47,    48,    30,    31,
      51,    33,    53,    30,    31,    32,    57,    58,    59,    51,
      30,    31,    45,    33,    30,    31,    49,    33,    32,   211,
      53,   213,   214,    33,    57,    76,    32,    33,   603,    53,
     372,    11,    12,   337,   373,    47,    48,    49,    50,   371,
     372,    74,   346,    32,    33,    57,    30,    31,    32,    47,
      48,    30,    31,    32,    33,    32,    54,    30,    31,    32,
      33,    33,    74,    75,    62,   257,    64,    65,    66,    67,
      68,    69,    70,   280,    33,   282,    31,    32,    33,   654,
     287,   288,   289,    33,   291,   292,    30,    31,    32,    33,
     297,   298,   299,    30,    31,    32,   400,   280,   437,    32,
      54,   440,    33,   310,   311,   409,    11,   439,    62,    47,
      64,    65,    66,    67,    68,    69,    70,    32,    33,   311,
      33,   183,   184,    30,    31,    32,    33,   189,   190,    30,
      31,    32,    33,   195,   196,   197,   198,   199,    30,    31,
      32,    33,    48,   205,   206,   207,    48,   330,    30,    31,
      32,    31,    32,    33,    47,    48,   218,   219,   365,   366,
     367,    47,   224,   225,   503,    31,    32,    33,    31,    32,
      33,    31,    32,    33,    31,    32,    33,    32,    33,   371,
     372,   373,    31,    32,    33,    30,    31,    32,    30,    31,
      32,    31,    32,    33,    31,    32,    33,   259,   260,   261,
      31,    32,    33,    31,    32,    33,    31,    32,    33,    31,
      32,    33,    31,    32,    33,    30,    31,    32,    32,    33,
      32,    33,    31,    32,    32,    33,   526,    11,   528,    32,
      33,    32,    33,    51,   526,   535,   528,   537,    32,    33,
      32,    33,   526,    33,   528,   437,    51,   439,   440,    11,
     526,    53,   528,    32,    33,    32,    33,    32,    33,    32,
      33,    32,    33,    32,    33,    32,    33,    32,    33,    32,
      33,    32,    33,    32,    33,    53,   589,    32,    33,   592,
      32,    33,    32,    33,    12,   598,    32,    33,   588,   602,
     590,   591,    33,   593,    32,    33,   588,   597,    58,   591,
      53,   601,    59,   603,   588,    32,    33,   591,    32,    33,
      57,   503,   588,    32,    33,   591,    32,    33,    33,   526,
      76,   528,    44,   636,    32,    33,    47,   640,   535,   642,
     537,    32,    33,   646,    32,    33,   649,   637,    32,    33,
     653,   403,   404,   643,   657,    32,    33,    32,    33,   411,
     412,    32,    33,    11,   654,   417,    32,    33,   565,    11,
     567,    11,   569,    11,   571,    48,   573,    11,   575,    51,
     577,   684,   579,    11,   581,    32,    33,    11,   691,    32,
      33,   443,   444,   445,   446,   447,   448,   449,   450,   451,
     703,     3,     4,    53,     6,     7,     8,    32,    33,    11,
      12,    11,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    32,    33,
      53,   483,    53,    35,    36,    11,    38,    39,    40,    41,
      42,     3,     4,    57,     6,     7,     8,    32,    33,    11,
      12,    11,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    32,    33,
      11,    32,    33,    35,    36,    11,    38,    39,    40,    41,
      42,     3,     4,    76,     6,     7,     8,    32,    33,    11,
      12,    11,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    32,    33,
      11,    32,    33,    35,    36,    11,    38,    39,    40,    41,
      42,     3,     4,    11,     6,     7,     8,    32,    33,    11,
      12,    11,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    32,    33,
      11,    32,    33,    35,    36,    11,    38,    39,    40,    41,
      42,     3,     4,    53,     6,     7,     8,    11,    11,    11,
      12,    33,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    11,    46,
      48,    57,    33,    35,    36,    33,    38,    39,    40,    41,
      42,     3,     4,    52,     6,     7,     8,    47,    33,    11,
      12,    33,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    32,    75,
      33,    11,    33,    35,    36,    33,    38,    39,    40,    41,
      42,     3,     4,    73,     6,     7,     8,    32,    32,    11,
      12,    45,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    33,    11,
      53,    63,    71,    35,    36,    46,    38,    39,    40,    41,
      42,     3,     4,    11,     6,     7,     8,    11,    52,    11,
      12,    47,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    33,    47,
      11,    48,    33,    35,    36,    49,    38,    39,    40,    41,
      42,     3,     4,    50,     6,     7,     8,    11,    57,    11,
      12,    74,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    11,    75,
      33,    56,    75,    35,    36,    11,    38,    39,    40,    41,
      42,     3,     4,    11,     6,     7,     8,    45,    53,    11,
      12,    11,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    53,    53,
      11,    71,    11,    35,    36,    11,    38,    39,    40,    41,
      42,     3,     4,    11,     6,     7,     8,    11,    47,    11,
      12,    11,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    11,    49,
      11,    11,    50,    35,    36,    11,    38,    39,    40,    41,
      42,     3,     4,    74,     6,     7,     8,    11,    75,    11,
      12,    11,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    60,    11,
      61,    77,    11,    35,    36,    53,    38,    39,    40,    41,
      42,     3,     4,    11,     6,     7,     8,    53,    53,    11,
      12,    11,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    11,    11,
      11,    11,    11,    35,    36,    11,    38,    39,    40,    41,
      42,     3,     4,    33,     6,     7,     8,    60,    11,    11,
      12,    61,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    11,    11,
      77,    60,    77,    35,    36,    61,    38,    39,    40,    41,
      42,     3,     4,    11,     6,     7,     8,    11,    53,    11,
      12,    11,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    55,    55,
      71,    11,    33,    35,    36,    73,    38,    39,    40,    41,
      42,     3,     4,    11,     6,     7,     8,    11,    60,    11,
      12,    11,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    61,    11,
      77,    11,    11,    35,    36,    54,    38,    39,    40,    41,
      42,     3,     4,    33,     6,     7,     8,    33,    33,    11,
      12,    11,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    33,    33,
      33,    55,    33,    35,    36,    33,    38,    39,    40,    41,
      42,     3,     4,    33,     6,     7,     8,    55,    55,    11,
      12,    11,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    55,    55,
      11,    11,    71,    35,    36,    71,    38,    39,    40,    41,
      42,     3,     4,    71,     6,     7,     8,    71,    71,    11,
      12,    71,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    56,    11,
      11,    11,    11,    35,    36,    54,    38,    39,    40,    41,
      42,     3,     4,    65,     6,     7,     8,    62,    64,    11,
      12,    68,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    66,    69,
      67,    70,    11,    35,    36,    11,    38,    39,    40,    41,
      42,     3,     4,    55,     6,     7,     8,    55,    11,    11,
      12,    55,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    11,    11,
      71,    71,    11,    35,    36,    71,    38,    39,    40,    41,
      42,     3,     4,    11,     6,     7,     8,    55,    11,    11,
      12,    11,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    11,    11,
      71,    71,    71,    35,    36,    11,    38,    39,    40,    41,
      42,     3,     4,    11,     6,     7,     8,    11,    11,    11,
      12,    11,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    11,    11,
      11,    11,    11,    35,    36,    11,    38,    39,    40,    41,
      42,     3,     4,    11,     6,     7,     8,    11,    55,    11,
      12,    11,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    11,    11,
      71,    11,    11,    35,    36,    11,    38,    39,    40,    41,
      42,     3,     4,    71,     6,     7,     8,    11,    11,    11,
      12,    11,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    11,     3,
     125,   144,    22,    35,    36,    36,    38,    39,    40,    41,
      42,     3,     4,   339,     6,     7,     8,   349,     9,    11,
      12,   178,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    -1,    -1,
      -1,    -1,    -1,    35,    36,    -1,    38,    39,    40,    41,
      42,     3,     4,    -1,     6,     7,     8,    -1,    -1,    11,
      12,    -1,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    -1,    -1,
      -1,    -1,    -1,    35,    36,    -1,    38,    39,    40,    41,
      42,     3,     4,    -1,     6,     7,     8,    -1,    -1,    11,
      -1,    -1,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    -1,    -1,
      -1,    -1,    -1,    35,    36,    -1,    38,    39,    40,    41,
      42,     3,     4,    -1,     6,     7,     8,    -1,    -1,    11,
      -1,    -1,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    -1,    -1,
      -1,    -1,    -1,    35,    36,    -1,    38,    39,    40,    41,
      42,     3,     4,    -1,     6,     7,     8,    -1,    -1,    11,
      -1,    -1,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    -1,    -1,
      -1,    -1,    -1,    35,    36,    -1,    38,    39,    40,    41,
      42,     3,     4,    -1,     6,     7,     8,    -1,    -1,    11,
      -1,    -1,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    -1,    -1,
      -1,    -1,    -1,    35,    36,    -1,    38,    39,    40,    41,
      42,     3,     4,    -1,     6,     7,     8,    -1,    -1,    11,
      -1,    -1,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    -1,    -1,
      -1,    -1,    -1,    35,    36,    -1,    38,    39,    40,    41,
      42,     3,     4,    -1,     6,     7,     8,    -1,    -1,    11,
      -1,    -1,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    -1,    -1,
      -1,    -1,    -1,    35,    36,    -1,    38,    39,    40,    41,
      42,     3,     4,    -1,     6,     7,     8,    -1,    -1,    11,
      -1,    -1,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    -1,    -1,
      -1,    -1,    -1,    35,    36,    -1,    38,    39,    40,    41,
      42,     3,     4,    -1,     6,     7,     8,    -1,    -1,    11,
      -1,    -1,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    -1,    -1,
      -1,    -1,    -1,    35,    36,    -1,    38,    39,    40,    41,
      42,     3,     4,    -1,     6,     7,     8,    -1,    -1,    11,
      -1,    -1,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    -1,    -1,
      -1,    -1,    -1,    35,    36,    -1,    38,    39,    40,    41,
      42,     3,     4,    -1,     6,     7,     8,    -1,    -1,    11,
      -1,    -1,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    -1,    -1,
      -1,    -1,    -1,    35,    36,    -1,    38,    39,    40,    41,
      42,     3,     4,    -1,     6,     7,     8,    -1,    -1,    11,
      -1,    -1,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    -1,    -1,
      -1,    -1,    -1,    35,    36,    -1,    38,    39,    40,    41,
      42,     3,     4,    -1,     6,     7,     8,    -1,    -1,    11,
      -1,    -1,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    -1,    -1,
      -1,    -1,    -1,    35,    36,    -1,    38,    39,    40,    41,
      42,     3,     4,    -1,     6,     7,     8,    -1,    -1,    11,
      -1,    -1,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    -1,    -1,
      -1,    -1,    -1,    35,    36,    -1,    38,    39,    40,    41,
      42,     3,     4,    -1,     6,     7,     8,    -1,    -1,    11,
      -1,    -1,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    -1,    -1,
      -1,    -1,    -1,    35,    36,    -1,    38,    39,    40,    41,
      42,     3,     4,    -1,     6,     7,     8,    -1,    -1,    11,
      -1,    -1,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    -1,    -1,
      -1,    -1,    -1,    35,    36,    -1,    38,    39,    40,    41,
      42,     3,     4,    -1,     6,     7,     8,    -1,    -1,    11,
      -1,    -1,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    -1,    -1,
      -1,    -1,    -1,    35,    36,    -1,    38,    39,    40,    41,
      42,     3,     4,    -1,     6,     7,     8,    -1,    -1,    11,
      -1,    -1,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    -1,    -1,
      -1,    -1,    -1,    35,    36,    -1,    38,    39,    40,    41,
      42
};

  /* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
     symbol of state STATE-NUM.  */
static const yytype_uint8 yystos[] =
{
       0,    34,    80,   154,    78,     0,    31,    32,   101,   102,
     137,   142,    37,    10,    72,   101,   137,   139,     9,    13,
      43,   130,   131,    30,    32,    86,    93,    95,   101,   108,
     110,   113,   119,   120,   138,   140,   141,   152,    37,    37,
      82,   130,    82,    47,    48,    51,    53,    57,    58,    59,
      76,    86,    87,    87,    30,    87,    87,    87,    32,    33,
     140,    87,    13,     3,     4,     6,     7,     8,    11,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    35,    36,    38,    39,    40,
      41,    42,    81,    83,    11,    82,    82,    82,    82,    82,
      82,    28,    84,    82,    86,    82,    72,    37,    43,    89,
      11,    12,    11,    12,    11,    12,    11,    12,    11,    11,
      12,    37,    11,    12,    11,    11,    11,    37,    32,    33,
      88,    91,   109,    32,    86,   152,    32,    86,    92,    94,
      96,    97,    32,    33,    86,    97,   100,   103,   107,   111,
     132,   133,   144,   145,    32,    33,    85,    86,   101,   102,
     108,   117,   118,   121,   122,   152,   153,    32,    86,    90,
      98,   100,   111,   112,   144,    86,    86,    32,    86,   149,
     150,   151,    89,    46,    52,    44,    33,   152,    33,    47,
      48,    33,    97,    33,    96,    49,    50,    57,    74,    75,
      51,    33,   107,    33,    97,    60,    61,    77,    53,    32,
      33,   118,    33,    85,   118,    32,    33,   117,    45,    53,
      98,    33,    33,    33,    63,    71,   149,    33,    33,    82,
      82,    11,    47,    33,    47,    82,    82,    48,    33,    48,
      82,    82,    82,    82,    82,    11,    51,    33,    51,    82,
      82,    82,    11,    53,    33,    53,    33,   118,    33,    60,
      61,    77,    53,    12,    82,    82,    33,    57,    58,    59,
      82,    82,    33,    76,    44,    11,    11,    11,    47,    11,
      11,    12,    11,    12,    11,    48,    11,    11,    11,    11,
      12,    11,    11,    12,    11,    51,    11,    11,    11,    11,
      11,    53,    11,    53,    33,    53,    82,    82,    82,    11,
      11,    11,    12,    57,    11,    11,    11,    11,    12,    11,
      12,    76,    11,    11,    33,    37,    33,    37,    11,    33,
      86,   152,    33,    86,    11,    86,    98,    99,    32,    86,
     104,   105,   106,    33,    86,    86,    99,    32,    33,    86,
     146,   147,   148,    11,    32,    86,   143,    86,   143,    86,
     143,    11,    11,    53,    11,    11,    11,    11,    33,    86,
      33,    85,    86,   118,    11,    33,    32,    33,   114,   123,
     124,   125,   126,   127,   128,   129,   134,   135,   136,    11,
      46,    33,    52,    33,    47,    33,   152,    33,    48,    33,
      99,    33,    98,    55,    71,   104,    33,    57,    33,    99,
      33,    55,    71,    75,    33,   146,    33,    73,   143,    32,
     115,   116,   143,   116,   143,   116,    11,    86,   143,    86,
     143,    86,   143,    45,    33,    53,    33,   118,    33,    85,
     118,    33,    63,    54,    62,    64,    65,    66,    67,    68,
      69,    70,    71,    33,   135,    11,    46,    11,    52,    11,
      47,    33,    47,    11,    48,    33,    49,    82,    82,    33,
      50,    11,    57,    33,    74,    82,    82,    11,    75,    33,
      75,    82,   116,    56,    33,   115,   116,    33,   116,    33,
     143,   116,   143,   116,   143,   116,    11,    45,    11,    53,
      33,    53,    33,   118,    33,    53,    11,    82,    82,    82,
      82,    82,    82,    82,    82,    82,    11,    71,    11,    11,
      11,    47,    11,    11,    49,    11,    11,    12,    11,    12,
      50,    11,    11,    74,    11,    11,    12,    11,    12,    11,
      75,    11,    11,    12,    33,    82,    60,    33,    61,    33,
      77,   116,    33,   116,    33,   116,    33,    11,    11,    53,
      11,    53,    33,    53,    11,    11,    12,    11,    12,    11,
      12,    11,    12,    11,    12,    11,    12,    11,    12,    11,
      12,    11,    12,    11,    11,    11,    32,    33,    86,    97,
     133,    86,    97,   133,    11,    11,    33,    86,    97,    32,
      33,    86,    97,   136,    11,    33,    60,    11,    12,    11,
      61,    11,    77,    11,    33,    60,    33,    61,    33,    77,
      11,    11,    53,    11,    33,    86,    86,    86,    86,    86,
      86,    86,    86,    86,    55,    33,    97,   133,    33,    33,
      97,    33,    97,   133,    33,    33,    97,    55,    33,    97,
      33,    71,    33,    97,   136,    33,    33,    97,    73,    11,
      33,    11,    11,    60,    11,    61,    11,    77,    11,    11,
      54,    33,    33,    33,    33,    33,    33,    33,    33,    33,
      11,    55,    33,    33,    97,    55,    55,    33,    71,    33,
      33,    97,    71,    71,    33,    11,    55,    33,    55,    11,
      71,    33,    33,    97,    71,    71,    33,    11,    56,    11,
      11,    11,    11,    54,    62,    64,    65,    66,    67,    68,
      69,    70,    11,    55,    55,    33,    11,    11,    55,    11,
      71,    71,    33,    11,    11,    71,    11,    55,    11,    11,
      71,    71,    33,    11,    11,    71,    11,    11,    11,    11,
      11,    11,    11,    11,    11,    11,    11,    11,    55,    11,
      11,    11,    71,    11,    11,    11,    11,    71,    11,    11,
      11,    11
};

  /* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint8 yyr1[] =
{
       0,    79,    80,    80,    81,    81,    82,    82,    83,    83,
      83,    83,    83,    83,    83,    83,    83,    83,    83,    83,
      83,    83,    83,    83,    83,    83,    83,    83,    83,    83,
      83,    83,    83,    83,    83,    83,    84,    85,    85,    86,
      86,    86,    87,    87,    88,    88,    89,    89,    90,    90,
      90,    91,    91,    92,    92,    92,    92,    92,    93,    93,
      93,    93,    94,    94,    94,    95,    95,    95,    95,    96,
      96,    97,    97,    98,    98,    98,    98,    98,    99,    99,
     100,   100,   101,   102,   102,   103,   103,   104,   104,   105,
     105,   105,   105,   105,   105,   105,   105,   105,   106,   106,
     106,   106,   106,   106,   106,   106,   107,   107,   107,   108,
     108,   108,   108,   108,   109,   109,   110,   110,   111,   111,
     111,   112,   112,   112,   112,   112,   112,   112,   112,   112,
     113,   113,   113,   113,   113,   113,   113,   113,   113,   114,
     114,   114,   115,   115,   116,   116,   117,   117,   117,   118,
     118,   119,   119,   120,   120,   121,   121,   121,   121,   122,
     122,   122,   122,   123,   123,   124,   124,   125,   125,   126,
     126,   127,   127,   128,   128,   129,   129,   130,   131,   131,
     132,   132,   132,   133,   133,   133,   134,   134,   135,   135,
     135,   135,   135,   135,   135,   135,   135,   136,   136,   137,
     138,   138,   138,   139,   139,   140,   140,   140,   140,   140,
     140,   140,   140,   140,   140,   140,   140,   141,   141,   142,
     143,   143,   144,   144,   145,   145,   145,   145,   145,   146,
     146,   147,   147,   147,   147,   147,   148,   148,   148,   148,
     148,   148,   148,   148,   148,   149,   149,   150,   150,   151,
     151,   151,   152,   152,   153,   153,   153,   153,   154,   154
};

  /* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     2,     3,     2,     3,     0,     2,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     2,     1,     1,     7,
       8,     1,     1,     2,     1,     1,     0,     2,     3,     7,
       8,     8,     7,     4,     7,     8,     8,     9,     4,     8,
       8,     9,     4,     7,     8,     4,     8,     8,     9,     1,
       1,     1,     2,     1,     1,     1,     1,     1,     1,     2,
       8,     9,     2,     1,     2,     8,     9,     1,     1,     4,
       7,     8,     9,     9,    10,     8,     9,     8,     4,     8,
       9,     9,    10,     8,     9,     8,     1,     1,     1,     4,
       7,     8,     8,     9,     8,     7,     9,     8,     4,     7,
       8,     8,     8,     9,     8,     9,     9,    10,     7,     4,
       8,     8,     9,     9,     9,    10,     8,     7,     4,     4,
       7,     8,     4,     7,     1,     2,     1,     1,     1,     1,
       2,     4,     8,     4,     8,     9,    10,    10,    11,     9,
      10,    10,    11,     4,     8,     4,     8,     4,     8,     4,
       8,     4,     8,     4,     8,     4,     8,     2,     1,     2,
       1,     1,     2,     1,     1,     1,     4,     8,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     2,     6,
       1,     1,     1,     0,     2,     1,     2,     1,     2,     1,
       2,     1,     2,     1,     2,     1,     2,     1,     2,     5,
       4,     7,     8,     9,     4,     7,     8,     8,     9,     1,
       1,     4,     7,     8,     8,     9,     4,     7,     8,     8,
       8,     9,     9,     9,    10,     1,     1,     4,     7,     4,
       7,     8,     8,     9,     9,    10,    10,    11,     4,     6
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

/* User initialization code.  */

{
  globalOwlPrefix = strdup("");
}


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

    {(yyval.schemaFileVal) = new XmlSchemaFile((yyvsp[-1].versionVal), 0, (yyvsp[0].schemaVal));
	   xmlSchemaFile = (yyval.schemaFileVal);
	  }

    break;

  case 3:

    {(yyval.schemaFileVal) = new XmlSchemaFile((yyvsp[-2].versionVal), (yyvsp[-1].commentListVal), (yyvsp[0].schemaVal));
	   xmlSchemaFile = (yyval.schemaFileVal);
	  }

    break;

  case 4:

    {(yyval.attribPairVal) = new XmlAttribPair((yyvsp[-1].iVal), 0, (yyvsp[0].sVal));}

    break;

  case 5:

    {(yyval.attribPairVal) = new XmlAttribPair((yyvsp[-2].iVal), (yyvsp[-1].sVal), (yyvsp[0].sVal));}

    break;

  case 6:

    {(yyval.attribPairListVal) = new std::list<XmlAttribPair *>;}

    break;

  case 7:

    {(yyval.attribPairListVal) = (yyvsp[-1].attribPairListVal);
	   std::list<XmlAttribPair *>::iterator iter;
	   for (iter = (yyvsp[-1].attribPairListVal)->begin(); iter != (yyvsp[-1].attribPairListVal)->end(); iter++)
	     { // check for duplicate names
	       if ((*iter)->name == (yyvsp[0].attribPairVal)->name)
		 {
		   snprintf(errMessage, ERRSIZE,
			    "attribute \"%s\" used twice",
			    attNames[(*iter)->name - ABSTRACT]);
		   yyerror(errMessage);
		 }
	     }
	   (yyvsp[-1].attribPairListVal)->push_back((yyvsp[0].attribPairVal));}

    break;

  case 8:

    {(yyval.iVal) = ABSTRACT;}

    break;

  case 9:

    {(yyval.iVal) = ATTRIBUTEFORMDEFAULT;}

    break;

  case 10:

    {(yyval.iVal) = BASE;}

    break;

  case 11:

    {(yyval.iVal) = DEFALT;}

    break;

  case 12:

    {(yyval.iVal) = ELEMENTFORMDEFAULT;}

    break;

  case 13:

    {(yyval.iVal) = FINAL;}

    break;

  case 14:

    {(yyval.iVal) = FIXED;}

    break;

  case 15:

    {(yyval.iVal) = FORM;}

    break;

  case 16:

    {(yyval.iVal) = ID;}

    break;

  case 17:

    {(yyval.iVal) = ITEMTYPE;}

    break;

  case 18:

    {(yyval.iVal) = NAMESPACE;}

    break;

  case 19:

    {(yyval.iVal) = MAXOCCURS;}

    break;

  case 20:

    {(yyval.iVal) = MINOCCURS;}

    break;

  case 21:

    {(yyval.iVal) = MIXED;}

    break;

  case 22:

    {(yyval.iVal) = NAME;}

    break;

  case 23:

    {(yyval.iVal) = NILLABLE;}

    break;

  case 24:

    {(yyval.iVal) = PROCESSCONTENTS;}

    break;

  case 25:

    {(yyval.iVal) = REF;}

    break;

  case 26:

    {(yyval.iVal) = REFER;}

    break;

  case 27:

    {(yyval.iVal) = SCHEMALOCATION;}

    break;

  case 28:

    {(yyval.iVal) = SOURCE;}

    break;

  case 29:

    {(yyval.iVal) = SUBSTITUTIONGROUP;}

    break;

  case 30:

    {(yyval.iVal) = TARGETNAMESPACE;}

    break;

  case 31:

    {(yyval.iVal) = TYP;}

    break;

  case 32:

    {(yyval.iVal) = USE;}

    break;

  case 33:

    {(yyval.iVal) = VALUE;}

    break;

  case 34:

    {(yyval.iVal) = VERSION;}

    break;

  case 35:

    {(yyval.iVal) = XPATH;}

    break;

  case 36:

    {(yyval.sVal) = (yyvsp[0].sVal);}

    break;

  case 37:

    {(yyval.typVal) = (yyvsp[0].complexTypeVal);}

    break;

  case 38:

    {(yyval.typVal) = (yyvsp[0].simpleTypeVal);}

    break;

  case 39:

    {(yyval.annotationVal) = new XmlAnnotation();
	   doXmlAnnotationAttributes((yyval.annotationVal), (yyvsp[-5].attribPairListVal));
           (yyval.annotationVal)->comment = 0;
	   (yyval.annotationVal)->content = (yyvsp[-3].annoTypeListVal);
	  }

    break;

  case 40:

    {(yyval.annotationVal) = new XmlAnnotation();
	   doXmlAnnotationAttributes((yyval.annotationVal), (yyvsp[-5].attribPairListVal));
           (yyval.annotationVal)->comment = (yyvsp[-7].commentVal);
	   (yyval.annotationVal)->content = (yyvsp[-3].annoTypeListVal);
	  }

    break;

  case 41:

    {(yyval.annotationVal) = new XmlAnnotation(); // makes shift/reduce conflict but is OK
	   (yyval.annotationVal)->comment = (yyvsp[0].commentVal);
	   (yyval.annotationVal)->content = 0;
	  }

    break;

  case 42:

    {(yyval.annotationListVal) = new std::list<XmlAnnotation *>;
	   (yyval.annotationListVal)->push_back((yyvsp[0].annotationVal));
          }

    break;

  case 43:

    {(yyval.annotationListVal) = (yyvsp[-1].annotationListVal);
	   (yyvsp[-1].annotationListVal)->push_back((yyvsp[0].annotationVal));
	  }

    break;

  case 44:

    {(yyval.annoTypeVal) = (yyvsp[0].documentationVal);}

    break;

  case 45:

    {(yyval.annoTypeVal) = (yyvsp[0].appinfoVal);}

    break;

  case 46:

    {(yyval.annoTypeListVal) = new std::list<XmlAnnoType *>;}

    break;

  case 47:

    {(yyval.annoTypeListVal) = (yyvsp[-1].annoTypeListVal);
	   (yyvsp[-1].annoTypeListVal)->push_back((yyvsp[0].annoTypeVal));
	  }

    break;

  case 48:

    {(yyval.anyVal) = new XmlAny();
	  }

    break;

  case 49:

    {(yyval.anyVal) = new XmlAny();
	   doXmlAnyAttributes((yyval.anyVal), (yyvsp[-4].attribPairListVal));
	  }

    break;

  case 50:

    {(yyval.anyVal) = new XmlAny();
	   doXmlAnyAttributes((yyval.anyVal), (yyvsp[-5].attribPairListVal));
	   (yyval.anyVal)->frontNote = (yyvsp[-3].annotationVal);
	  }

    break;

  case 51:

    {(yyval.appinfoVal) = new XmlAppinfo();
	   doXmlAppinfoAttributes((yyval.appinfoVal), (yyvsp[-5].attribPairListVal));
	   (yyval.appinfoVal)->text = (yyvsp[-3].sVal);
	  }

    break;

  case 52:

    {(yyval.appinfoVal) = new XmlAppinfo();
	   doXmlAppinfoAttributes((yyval.appinfoVal), (yyvsp[-4].attribPairListVal));
	   (yyval.appinfoVal)->text = strdup("");
	  }

    break;

  case 53:

    {(yyval.attributeLonerVal) = new XmlAttributeLoner();
	   doXmlAttributeLonerAttributes((yyval.attributeLonerVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 54:

    {(yyval.attributeLonerVal) = new XmlAttributeLoner();
	   doXmlAttributeLonerAttributes((yyval.attributeLonerVal), (yyvsp[-4].attribPairListVal));
	  }

    break;

  case 55:

    {(yyval.attributeLonerVal) = new XmlAttributeLoner();
	   doXmlAttributeLonerAttributes((yyval.attributeLonerVal), (yyvsp[-5].attribPairListVal));
	   (yyval.attributeLonerVal)->simple = (yyvsp[-3].simpleTypeVal);
	  }

    break;

  case 56:

    {(yyval.attributeLonerVal) = new XmlAttributeLoner();
	   doXmlAttributeLonerAttributes((yyval.attributeLonerVal), (yyvsp[-5].attribPairListVal));
	   (yyval.attributeLonerVal)->frontNote = (yyvsp[-3].annotationVal);
	  }

    break;

  case 57:

    {(yyval.attributeLonerVal) = new XmlAttributeLoner();
	   doXmlAttributeLonerAttributes((yyval.attributeLonerVal), (yyvsp[-6].attribPairListVal));
	   (yyval.attributeLonerVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.attributeLonerVal)->simple = (yyvsp[-3].simpleTypeVal);
	  }

    break;

  case 58:

    {(yyval.attributeLonerRefableVal) = new XmlAttributeLonerRefable();
	   doXmlAttributeLonerRefableAttributes((yyval.attributeLonerRefableVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 59:

    {(yyval.attributeLonerRefableVal) = new XmlAttributeLonerRefable();
	   doXmlAttributeLonerRefableAttributes((yyval.attributeLonerRefableVal), (yyvsp[-5].attribPairListVal));
	   (yyval.attributeLonerRefableVal)->simple = (yyvsp[-3].simpleTypeVal);
	  }

    break;

  case 60:

    {(yyval.attributeLonerRefableVal) = new XmlAttributeLonerRefable();
	   doXmlAttributeLonerRefableAttributes((yyval.attributeLonerRefableVal), (yyvsp[-5].attribPairListVal));
	   (yyval.attributeLonerRefableVal)->frontNote = (yyvsp[-3].annotationVal);
	  }

    break;

  case 61:

    {(yyval.attributeLonerRefableVal) = new XmlAttributeLonerRefable();
	   doXmlAttributeLonerRefableAttributes((yyval.attributeLonerRefableVal), (yyvsp[-6].attribPairListVal));
	   (yyval.attributeLonerRefableVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.attributeLonerRefableVal)->simple = (yyvsp[-3].simpleTypeVal);
	  }

    break;

  case 62:

    {(yyval.attributeGroupRefVal) = new XmlAttributeGroupRef();
	   doXmlAttributeGroupRefAttributes((yyval.attributeGroupRefVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 63:

    {(yyval.attributeGroupRefVal) = new XmlAttributeGroupRef();
	   doXmlAttributeGroupRefAttributes((yyval.attributeGroupRefVal), (yyvsp[-4].attribPairListVal));
	  }

    break;

  case 64:

    {(yyval.attributeGroupRefVal) = new XmlAttributeGroupRef();
	   doXmlAttributeGroupRefAttributes((yyval.attributeGroupRefVal), (yyvsp[-5].attribPairListVal));
	   (yyval.attributeGroupRefVal)->frontNote = (yyvsp[-3].annotationVal);
	  }

    break;

  case 65:

    {(yyval.attributeGroupRefableVal) = new XmlAttributeGroupRefable();
	   doXmlAttributeGroupRefableAttributes((yyval.attributeGroupRefableVal), (yyvsp[-1].attribPairListVal));
	   (yyval.attributeGroupRefableVal)->xmlAttributes = new std::list<XmlAttributor *>;
	  }

    break;

  case 66:

    {(yyval.attributeGroupRefableVal) = new XmlAttributeGroupRefable();
	   doXmlAttributeGroupRefableAttributes((yyval.attributeGroupRefableVal), (yyvsp[-5].attribPairListVal));
	   (yyval.attributeGroupRefableVal)->xmlAttributes = (yyvsp[-3].attributorListVal);
	  }

    break;

  case 67:

    {(yyval.attributeGroupRefableVal) = new XmlAttributeGroupRefable();
	   doXmlAttributeGroupRefableAttributes((yyval.attributeGroupRefableVal), (yyvsp[-5].attribPairListVal));
	   (yyval.attributeGroupRefableVal)->frontNote = (yyvsp[-3].annotationVal);
	  }

    break;

  case 68:

    {(yyval.attributeGroupRefableVal) = new XmlAttributeGroupRefable();
	   doXmlAttributeGroupRefableAttributes((yyval.attributeGroupRefableVal), (yyvsp[-6].attribPairListVal));
	   (yyval.attributeGroupRefableVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.attributeGroupRefableVal)->xmlAttributes = (yyvsp[-3].attributorListVal);
	  }

    break;

  case 69:

    {(yyval.attributorVal) = (yyvsp[0].attributeLonerVal);}

    break;

  case 70:

    {(yyval.attributorVal) = (yyvsp[0].attributeGroupRefVal);}

    break;

  case 71:

    {(yyval.attributorListVal) = new std::list<XmlAttributor *>;
	   (yyval.attributorListVal)->push_back((yyvsp[0].attributorVal));
	  }

    break;

  case 72:

    {(yyval.attributorListVal) = (yyvsp[-1].attributorListVal);
	   (yyvsp[-1].attributorListVal)->push_back((yyvsp[0].attributorVal));
	  }

    break;

  case 73:

    {(yyval.choSeqItemVal) = (yyvsp[0].anyVal);}

    break;

  case 74:

    {(yyval.choSeqItemVal) = (yyvsp[0].choiceVal);}

    break;

  case 75:

    {(yyval.choSeqItemVal) = (yyvsp[0].elementGroupRefVal);}

    break;

  case 76:

    {(yyval.choSeqItemVal) = (yyvsp[0].elementLocalVal);}

    break;

  case 77:

    {(yyval.choSeqItemVal) = (yyvsp[0].sequenceVal);}

    break;

  case 78:

    {(yyval.choSeqItemListVal) = new std::list<XmlChoSeqItem *>;
	   (yyval.choSeqItemListVal)->push_back((yyvsp[0].choSeqItemVal));
	  }

    break;

  case 79:

    {(yyval.choSeqItemListVal) = (yyvsp[-1].choSeqItemListVal);
	   (yyvsp[-1].choSeqItemListVal)->push_back((yyvsp[0].choSeqItemVal));
	  }

    break;

  case 80:

    {(yyval.choiceVal) = new XmlChoice(); // might add check that list is not empty
	   doXmlChoiceAttributes((yyval.choiceVal), (yyvsp[-5].attribPairListVal));
	   (yyval.choiceVal)->items = (yyvsp[-3].choSeqItemListVal);
	  }

    break;

  case 81:

    {(yyval.choiceVal) = new XmlChoice(); // might add check that list is not empty
	   doXmlChoiceAttributes((yyval.choiceVal), (yyvsp[-6].attribPairListVal));
	   (yyval.choiceVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.choiceVal)->items = (yyvsp[-3].choSeqItemListVal);
	  }

    break;

  case 82:

    {(yyval.commentVal) = new std::string(commentString);}

    break;

  case 83:

    {(yyval.commentListVal) = new std::list<std::string *>;
	   (yyval.commentListVal)->push_back((yyvsp[0].commentVal));
	  }

    break;

  case 84:

    {(yyval.commentListVal) = (yyvsp[-1].commentListVal);
	   (yyvsp[-1].commentListVal)->push_back((yyvsp[0].commentVal));
	  }

    break;

  case 85:

    {(yyval.complexContentVal) = new XmlComplexContent();
	   doXmlComplexContentAttributes((yyval.complexContentVal), (yyvsp[-5].attribPairListVal));
	   (yyval.complexContentVal)->item = (yyvsp[-3].complexContentItemVal);
	  }

    break;

  case 86:

    {(yyval.complexContentVal) = new XmlComplexContent();
	   doXmlComplexContentAttributes((yyval.complexContentVal), (yyvsp[-6].attribPairListVal));
	   (yyval.complexContentVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.complexContentVal)->item = (yyvsp[-3].complexContentItemVal);
	  }

    break;

  case 87:

    {(yyval.complexContentItemVal) = (yyvsp[0].complexRestrictionVal);}

    break;

  case 88:

    {(yyval.complexContentItemVal) = (yyvsp[0].complexExtensionVal);}

    break;

  case 89:

    {(yyval.complexExtensionVal) = new XmlComplexExtension();
	   doXmlComplexExtensionAttributes((yyval.complexExtensionVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 90:

    {(yyval.complexExtensionVal) = new XmlComplexExtension();
	   doXmlComplexExtensionAttributes((yyval.complexExtensionVal), (yyvsp[-4].attribPairListVal));
	  }

    break;

  case 91:

    {(yyval.complexExtensionVal) = new XmlComplexExtension();
	   doXmlComplexExtensionAttributes((yyval.complexExtensionVal), (yyvsp[-5].attribPairListVal));
	   (yyval.complexExtensionVal)->frontNote = (yyvsp[-3].annotationVal);
	  }

    break;

  case 92:

    {(yyval.complexExtensionVal) = new XmlComplexExtension();
	   doXmlComplexExtensionAttributes((yyval.complexExtensionVal), (yyvsp[-6].attribPairListVal));
	   (yyval.complexExtensionVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.complexExtensionVal)->item = (yyvsp[-3].otherContentBaseVal);
	  }

    break;

  case 93:

    {(yyval.complexExtensionVal) = new XmlComplexExtension();
	   doXmlComplexExtensionAttributes((yyval.complexExtensionVal), (yyvsp[-6].attribPairListVal));
	   (yyval.complexExtensionVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.complexExtensionVal)->attribs = (yyvsp[-3].attributorListVal);
	  }

    break;

  case 94:

    {(yyval.complexExtensionVal) = new XmlComplexExtension();
	   doXmlComplexExtensionAttributes((yyval.complexExtensionVal), (yyvsp[-7].attribPairListVal));
	   (yyval.complexExtensionVal)->frontNote = (yyvsp[-5].annotationVal);
	   (yyval.complexExtensionVal)->item = (yyvsp[-4].otherContentBaseVal);
	   (yyval.complexExtensionVal)->attribs = (yyvsp[-3].attributorListVal);
	  }

    break;

  case 95:

    {(yyval.complexExtensionVal) = new XmlComplexExtension();
	   doXmlComplexExtensionAttributes((yyval.complexExtensionVal), (yyvsp[-5].attribPairListVal));
	   (yyval.complexExtensionVal)->item = (yyvsp[-3].otherContentBaseVal);
	  }

    break;

  case 96:

    {(yyval.complexExtensionVal) = new XmlComplexExtension();
	   doXmlComplexExtensionAttributes((yyval.complexExtensionVal), (yyvsp[-6].attribPairListVal));
	   (yyval.complexExtensionVal)->item = (yyvsp[-4].otherContentBaseVal);
	   (yyval.complexExtensionVal)->attribs = (yyvsp[-3].attributorListVal);
	  }

    break;

  case 97:

    {(yyval.complexExtensionVal) = new XmlComplexExtension();
	   doXmlComplexExtensionAttributes((yyval.complexExtensionVal), (yyvsp[-5].attribPairListVal));
	   (yyval.complexExtensionVal)->attribs = (yyvsp[-3].attributorListVal);
	  }

    break;

  case 98:

    {(yyval.complexRestrictionVal) = new XmlComplexRestriction();
	   doXmlComplexRestrictionAttributes((yyval.complexRestrictionVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 99:

    {(yyval.complexRestrictionVal) = new XmlComplexRestriction();
	   doXmlComplexRestrictionAttributes((yyval.complexRestrictionVal), (yyvsp[-5].attribPairListVal));
	   (yyval.complexRestrictionVal)->frontNote = (yyvsp[-3].annotationVal);
	  }

    break;

  case 100:

    {(yyval.complexRestrictionVal) = new XmlComplexRestriction();
	   doXmlComplexRestrictionAttributes((yyval.complexRestrictionVal), (yyvsp[-6].attribPairListVal));
	   (yyval.complexRestrictionVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.complexRestrictionVal)->item = (yyvsp[-3].otherContentBaseVal);
	  }

    break;

  case 101:

    {(yyval.complexRestrictionVal) = new XmlComplexRestriction();
	   doXmlComplexRestrictionAttributes((yyval.complexRestrictionVal), (yyvsp[-6].attribPairListVal));
	   (yyval.complexRestrictionVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.complexRestrictionVal)->attribs = (yyvsp[-3].attributorListVal);
	  }

    break;

  case 102:

    {(yyval.complexRestrictionVal) = new XmlComplexRestriction();
	   doXmlComplexRestrictionAttributes((yyval.complexRestrictionVal), (yyvsp[-7].attribPairListVal));
	   (yyval.complexRestrictionVal)->frontNote = (yyvsp[-5].annotationVal);
	   (yyval.complexRestrictionVal)->item = (yyvsp[-4].otherContentBaseVal);
	   (yyval.complexRestrictionVal)->attribs = (yyvsp[-3].attributorListVal);
	  }

    break;

  case 103:

    {(yyval.complexRestrictionVal) = new XmlComplexRestriction();
	   doXmlComplexRestrictionAttributes((yyval.complexRestrictionVal), (yyvsp[-5].attribPairListVal));
	   (yyval.complexRestrictionVal)->item = (yyvsp[-3].otherContentBaseVal);
	  }

    break;

  case 104:

    {(yyval.complexRestrictionVal) = new XmlComplexRestriction();
	   doXmlComplexRestrictionAttributes((yyval.complexRestrictionVal), (yyvsp[-6].attribPairListVal));
	   (yyval.complexRestrictionVal)->item = (yyvsp[-4].otherContentBaseVal);
	   (yyval.complexRestrictionVal)->attribs = (yyvsp[-3].attributorListVal);
	  }

    break;

  case 105:

    {(yyval.complexRestrictionVal) = new XmlComplexRestriction();
	   doXmlComplexRestrictionAttributes((yyval.complexRestrictionVal), (yyvsp[-5].attribPairListVal));
	   (yyval.complexRestrictionVal)->attribs = (yyvsp[-3].attributorListVal);
	  }

    break;

  case 106:

    {(yyval.complexTypeItemVal) = (yyvsp[0].complexContentVal);}

    break;

  case 107:

    {(yyval.complexTypeItemVal) = (yyvsp[0].otherContentVal);}

    break;

  case 108:

    {(yyval.complexTypeItemVal) = (yyvsp[0].simpleContentVal);}

    break;

  case 109:

    {(yyval.complexTypeVal) = new XmlComplexType();
	   (yyval.complexTypeVal)->item = new XmlOtherContent(0, 0);
	   doXmlComplexTypeAttributes((yyval.complexTypeVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 110:

    {(yyval.complexTypeVal) = new XmlComplexType();
	   (yyval.complexTypeVal)->item = new XmlOtherContent(0, 0);
	   doXmlComplexTypeAttributes((yyval.complexTypeVal), (yyvsp[-4].attribPairListVal));
	  }

    break;

  case 111:

    {(yyval.complexTypeVal) = new XmlComplexType();
	   doXmlComplexTypeAttributes((yyval.complexTypeVal), (yyvsp[-5].attribPairListVal));
	   (yyval.complexTypeVal)->item = (yyvsp[-3].complexTypeItemVal);
	  }

    break;

  case 112:

    {(yyval.complexTypeVal) = new XmlComplexType();
	   (yyval.complexTypeVal)->item = new XmlOtherContent(0, 0);
	   doXmlComplexTypeAttributes((yyval.complexTypeVal), (yyvsp[-5].attribPairListVal));
	   (yyval.complexTypeVal)->frontNote = (yyvsp[-3].annotationVal);
	  }

    break;

  case 113:

    {(yyval.complexTypeVal) = new XmlComplexType();
	   doXmlComplexTypeAttributes((yyval.complexTypeVal), (yyvsp[-6].attribPairListVal));
	   (yyval.complexTypeVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.complexTypeVal)->item = (yyvsp[-3].complexTypeItemVal);
	  }

    break;

  case 114:

    {(yyval.documentationVal) = new XmlDocumentation();
	   (yyval.documentationVal)->text = (yyvsp[-3].sVal);
	   doXmlDocumentationAttributes((yyval.documentationVal), (yyvsp[-5].attribPairListVal));
	  }

    break;

  case 115:

    {(yyval.documentationVal) = new XmlDocumentation();
	   (yyval.documentationVal)->text = strdup("");
	   doXmlDocumentationAttributes((yyval.documentationVal), (yyvsp[-4].attribPairListVal));
	  }

    break;

  case 116:

    {(yyval.elementGroupVal) = new XmlElementGroup();
	   doXmlElementGroupAttributes((yyval.elementGroupVal), (yyvsp[-6].attribPairListVal));
	   (yyval.elementGroupVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.elementGroupVal)->item = (yyvsp[-3].choSeqItemVal);
	  }

    break;

  case 117:

    {(yyval.elementGroupVal) = new XmlElementGroup();
	   doXmlElementGroupAttributes((yyval.elementGroupVal), (yyvsp[-5].attribPairListVal));
	   (yyval.elementGroupVal)->item = (yyvsp[-3].choSeqItemVal);
	  }

    break;

  case 118:

    {(yyval.elementGroupRefVal) = new XmlElementGroupRef();
	   doXmlElementGroupRefAttributes((yyval.elementGroupRefVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 119:

    {(yyval.elementGroupRefVal) = new XmlElementGroupRef();
	   doXmlElementGroupRefAttributes((yyval.elementGroupRefVal), (yyvsp[-4].attribPairListVal));
	  }

    break;

  case 120:

    {(yyval.elementGroupRefVal) = new XmlElementGroupRef();
	   doXmlElementGroupRefAttributes((yyval.elementGroupRefVal), (yyvsp[-5].attribPairListVal));
	   (yyval.elementGroupRefVal)->frontNote = (yyvsp[-3].annotationVal);
	  }

    break;

  case 121:

    {(yyval.elementLocalVal) = new XmlElementLocal();
	   doXmlElementLocalAttributes((yyval.elementLocalVal), (yyvsp[-5].attribPairListVal));
	   (yyval.elementLocalVal)->frontNote = (yyvsp[-3].annotationVal);
	  }

    break;

  case 122:

    {(yyval.elementLocalVal) = new XmlElementLocal();
	   doXmlElementLocalAttributes((yyval.elementLocalVal), (yyvsp[-5].attribPairListVal));
	   if (((yyval.elementLocalVal)->ref) || ((yyval.elementLocalVal)->typ))
	     yyerror("cannot have type definition with type or ref");
	   (yyval.elementLocalVal)->typeDef = (yyvsp[-3].typVal);
	  }

    break;

  case 123:

    {(yyval.elementLocalVal) = new XmlElementLocal();
	   doXmlElementLocalAttributes((yyval.elementLocalVal), (yyvsp[-6].attribPairListVal));
	   if (((yyval.elementLocalVal)->ref) || ((yyval.elementLocalVal)->typ))
	     yyerror("cannot have type definition with type or ref");
	   (yyval.elementLocalVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.elementLocalVal)->typeDef = (yyvsp[-3].typVal);
	  }

    break;

  case 124:

    {(yyval.elementLocalVal) = new XmlElementLocal();
	   doXmlElementLocalAttributes((yyval.elementLocalVal), (yyvsp[-5].attribPairListVal));
	   (yyval.elementLocalVal)->idConstraints = (yyvsp[-3].idConstraintListVal);
	  }

    break;

  case 125:

    {(yyval.elementLocalVal) = new XmlElementLocal();
	   doXmlElementLocalAttributes((yyval.elementLocalVal), (yyvsp[-6].attribPairListVal));
	   (yyval.elementLocalVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.elementLocalVal)->idConstraints = (yyvsp[-3].idConstraintListVal);
	  }

    break;

  case 126:

    {(yyval.elementLocalVal) = new XmlElementLocal();
	   doXmlElementLocalAttributes((yyval.elementLocalVal), (yyvsp[-6].attribPairListVal));
	   if (((yyval.elementLocalVal)->ref) || ((yyval.elementLocalVal)->typ))
	     yyerror("cannot have type definition with type or ref");
	   (yyval.elementLocalVal)->typeDef = (yyvsp[-4].typVal);
	   (yyval.elementLocalVal)->idConstraints = (yyvsp[-3].idConstraintListVal);
	  }

    break;

  case 127:

    {(yyval.elementLocalVal) = new XmlElementLocal();
	   doXmlElementLocalAttributes((yyval.elementLocalVal), (yyvsp[-7].attribPairListVal));
	   if (((yyval.elementLocalVal)->ref) || ((yyval.elementLocalVal)->typ))
	     yyerror("cannot have type definition with type or ref");
	   (yyval.elementLocalVal)->frontNote = (yyvsp[-5].annotationVal);
	   (yyval.elementLocalVal)->typeDef = (yyvsp[-4].typVal);
	   (yyval.elementLocalVal)->idConstraints = (yyvsp[-3].idConstraintListVal);
	  }

    break;

  case 128:

    {(yyval.elementLocalVal) = new XmlElementLocal();
	   doXmlElementLocalAttributes((yyval.elementLocalVal), (yyvsp[-4].attribPairListVal));
	  }

    break;

  case 129:

    {(yyval.elementLocalVal) = new XmlElementLocal();
	   doXmlElementLocalAttributes((yyval.elementLocalVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 130:

    {(yyval.elementRefableVal) = new XmlElementRefable();
	   doXmlElementRefableAttributes((yyval.elementRefableVal), (yyvsp[-5].attribPairListVal));
	   (yyval.elementRefableVal)->frontNote = (yyvsp[-3].annotationVal);
	  }

    break;

  case 131:

    {(yyval.elementRefableVal) = new XmlElementRefable();
	   doXmlElementRefableAttributes((yyval.elementRefableVal), (yyvsp[-5].attribPairListVal));
	   (yyval.elementRefableVal)->typeDef = (yyvsp[-3].typVal);
	  }

    break;

  case 132:

    {(yyval.elementRefableVal) = new XmlElementRefable();
	   doXmlElementRefableAttributes((yyval.elementRefableVal), (yyvsp[-6].attribPairListVal));
	   (yyval.elementRefableVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.elementRefableVal)->typeDef = (yyvsp[-3].typVal);
	  }

    break;

  case 133:

    {(yyval.elementRefableVal) = new XmlElementRefable();
	   doXmlElementRefableAttributes((yyval.elementRefableVal), (yyvsp[-6].attribPairListVal));
	   (yyval.elementRefableVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.elementRefableVal)->idConstraints = (yyvsp[-3].idConstraintListVal);
	  }

    break;

  case 134:

    {(yyval.elementRefableVal) = new XmlElementRefable();
	   doXmlElementRefableAttributes((yyval.elementRefableVal), (yyvsp[-6].attribPairListVal));
	   (yyval.elementRefableVal)->typeDef = (yyvsp[-4].typVal);
	   (yyval.elementRefableVal)->idConstraints = (yyvsp[-3].idConstraintListVal);
	  }

    break;

  case 135:

    {(yyval.elementRefableVal) = new XmlElementRefable();
	   doXmlElementRefableAttributes((yyval.elementRefableVal), (yyvsp[-7].attribPairListVal));
	   (yyval.elementRefableVal)->frontNote = (yyvsp[-5].annotationVal);
	   (yyval.elementRefableVal)->typeDef = (yyvsp[-4].typVal);
	   (yyval.elementRefableVal)->idConstraints = (yyvsp[-3].idConstraintListVal);
	  }

    break;

  case 136:

    {(yyval.elementRefableVal) = new XmlElementRefable();
	   doXmlElementRefableAttributes((yyval.elementRefableVal), (yyvsp[-5].attribPairListVal));
	   (yyval.elementRefableVal)->idConstraints = (yyvsp[-3].idConstraintListVal);
	  }

    break;

  case 137:

    {(yyval.elementRefableVal) = new XmlElementRefable();
	   doXmlElementRefableAttributes((yyval.elementRefableVal), (yyvsp[-4].attribPairListVal));
	  }

    break;

  case 138:

    {(yyval.elementRefableVal) = new XmlElementRefable();
	   doXmlElementRefableAttributes((yyval.elementRefableVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 139:

    {(yyval.enumerationVal) = new XmlEnumeration();
	   doXmlEnumerationAttributes((yyval.enumerationVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 140:

    {(yyval.enumerationVal) = new XmlEnumeration();
	   doXmlEnumerationAttributes((yyval.enumerationVal), (yyvsp[-4].attribPairListVal));
	  }

    break;

  case 141:

    {(yyval.enumerationVal) = new XmlEnumeration();
	   doXmlEnumerationAttributes((yyval.enumerationVal), (yyvsp[-5].attribPairListVal));
	   (yyval.enumerationVal)->frontNote = (yyvsp[-3].annotationVal);
	  }

    break;

  case 142:

    {(yyval.fieldVal) = new XmlField();
	   doXmlFieldAttributes((yyval.fieldVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 143:

    {(yyval.fieldVal) = new XmlField();
	   doXmlFieldAttributes((yyval.fieldVal), (yyvsp[-4].attribPairListVal));
	  }

    break;

  case 144:

    {(yyval.fieldListVal) = new std::list<XmlField *>;
	   (yyval.fieldListVal)->push_back((yyvsp[0].fieldVal));
	  }

    break;

  case 145:

    {(yyval.fieldListVal) = (yyvsp[-1].fieldListVal);
	   (yyvsp[-1].fieldListVal)->push_back((yyvsp[0].fieldVal));
	  }

    break;

  case 146:

    {(yyval.idConstraintVal) = (yyvsp[0].keyVal);}

    break;

  case 147:

    {(yyval.idConstraintVal) = (yyvsp[0].keyrefVal);}

    break;

  case 148:

    {(yyval.idConstraintVal) = (yyvsp[0].uniqueVal);}

    break;

  case 149:

    {(yyval.idConstraintListVal) = new std::list<XmlIdConstraint *>;
	   (yyval.idConstraintListVal)->push_back((yyvsp[0].idConstraintVal));
	  }

    break;

  case 150:

    {(yyval.idConstraintListVal) = (yyvsp[-1].idConstraintListVal);
	   (yyvsp[-1].idConstraintListVal)->push_back((yyvsp[0].idConstraintVal));
	  }

    break;

  case 151:

    {(yyval.importVal) = new XmlImport();
	   doXmlImportAttributes((yyval.importVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 152:

    {(yyval.importVal) = new XmlImport();
	   doXmlImportAttributes((yyval.importVal), (yyvsp[-5].attribPairListVal));
	   (yyval.importVal)->note = (yyvsp[-3].annotationVal);
	  }

    break;

  case 153:

    {(yyval.includeVal) = new XmlInclude((yyvsp[-1].sVal), 0);}

    break;

  case 154:

    {(yyval.includeVal) = new XmlInclude((yyvsp[-5].sVal), (yyvsp[-3].annotationVal));}

    break;

  case 155:

    {(yyval.keyVal) = new XmlKey();
           doXmlKeyAttributes((yyval.keyVal), (yyvsp[-6].attribPairListVal));
	   (yyval.keyVal)->selector = (yyvsp[-4].selectorVal);
	   (yyval.keyVal)->fields = (yyvsp[-3].fieldListVal);
	  }

    break;

  case 156:

    {(yyval.keyVal) = new XmlKey();
           doXmlKeyAttributes((yyval.keyVal), (yyvsp[-6].attribPairListVal));
	   (yyval.keyVal)->comments = (yyvsp[-9].commentListVal);
	   (yyval.keyVal)->selector = (yyvsp[-4].selectorVal);
	   (yyval.keyVal)->fields = (yyvsp[-3].fieldListVal);
	  }

    break;

  case 157:

    {(yyval.keyVal) = new XmlKey();
           doXmlKeyAttributes((yyval.keyVal), (yyvsp[-7].attribPairListVal));
	   (yyval.keyVal)->frontNote = (yyvsp[-5].annotationVal);
	   (yyval.keyVal)->selector = (yyvsp[-4].selectorVal);
	   (yyval.keyVal)->fields = (yyvsp[-3].fieldListVal);
	  }

    break;

  case 158:

    {(yyval.keyVal) = new XmlKey();
           doXmlKeyAttributes((yyval.keyVal), (yyvsp[-7].attribPairListVal));
           (yyval.keyVal)->comments = (yyvsp[-10].commentListVal);
	   (yyval.keyVal)->frontNote = (yyvsp[-5].annotationVal);
	   (yyval.keyVal)->selector = (yyvsp[-4].selectorVal);
	   (yyval.keyVal)->fields = (yyvsp[-3].fieldListVal);
	  }

    break;

  case 159:

    {(yyval.keyrefVal) = new XmlKeyref();
           doXmlKeyrefAttributes((yyval.keyrefVal), (yyvsp[-6].attribPairListVal));
	   (yyval.keyrefVal)->selector = (yyvsp[-4].selectorVal);
	   (yyval.keyrefVal)->fields = (yyvsp[-3].fieldListVal);
	  }

    break;

  case 160:

    {(yyval.keyrefVal) = new XmlKeyref();
           doXmlKeyrefAttributes((yyval.keyrefVal), (yyvsp[-6].attribPairListVal));
	   (yyval.keyrefVal)->comments = (yyvsp[-9].commentListVal);
	   (yyval.keyrefVal)->selector = (yyvsp[-4].selectorVal);
	   (yyval.keyrefVal)->fields = (yyvsp[-3].fieldListVal);
	  }

    break;

  case 161:

    {(yyval.keyrefVal) = new XmlKeyref();
           doXmlKeyrefAttributes((yyval.keyrefVal), (yyvsp[-7].attribPairListVal));
	   (yyval.keyrefVal)->frontNote = (yyvsp[-5].annotationVal);
	   (yyval.keyrefVal)->selector = (yyvsp[-4].selectorVal);
	   (yyval.keyrefVal)->fields = (yyvsp[-3].fieldListVal);
	  }

    break;

  case 162:

    {(yyval.keyrefVal) = new XmlKeyref();
           doXmlKeyrefAttributes((yyval.keyrefVal), (yyvsp[-7].attribPairListVal));
	   (yyval.keyrefVal)->comments = (yyvsp[-10].commentListVal);
	   (yyval.keyrefVal)->frontNote = (yyvsp[-5].annotationVal);
	   (yyval.keyrefVal)->selector = (yyvsp[-4].selectorVal);
	   (yyval.keyrefVal)->fields = (yyvsp[-3].fieldListVal);
	  }

    break;

  case 163:

    {(yyval.lengthVal) = new XmlLength();
	   doXmlLengthAttributes((yyval.lengthVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 164:

    {(yyval.lengthVal) = new XmlLength();
	   doXmlLengthAttributes((yyval.lengthVal), (yyvsp[-5].attribPairListVal));
	   (yyval.lengthVal)->note = (yyvsp[-3].annotationVal);
	  }

    break;

  case 165:

    {(yyval.maxExclusiveVal) = new XmlMaxExclusive();
	   doXmlMaxExclusiveAttributes((yyval.maxExclusiveVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 166:

    {(yyval.maxExclusiveVal) = new XmlMaxExclusive();
	   doXmlMaxExclusiveAttributes((yyval.maxExclusiveVal), (yyvsp[-5].attribPairListVal));
	   (yyval.maxExclusiveVal)->note = (yyvsp[-3].annotationVal);
	  }

    break;

  case 167:

    {(yyval.maxInclusiveVal) = new XmlMaxInclusive();
	   doXmlMaxInclusiveAttributes((yyval.maxInclusiveVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 168:

    {(yyval.maxInclusiveVal) = new XmlMaxInclusive();
	   doXmlMaxInclusiveAttributes((yyval.maxInclusiveVal), (yyvsp[-5].attribPairListVal));
	   (yyval.maxInclusiveVal)->note = (yyvsp[-3].annotationVal);
	  }

    break;

  case 169:

    {(yyval.maxLengthVal) = new XmlMaxLength();
	   doXmlMaxLengthAttributes((yyval.maxLengthVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 170:

    {(yyval.maxLengthVal) = new XmlMaxLength();
	   doXmlMaxLengthAttributes((yyval.maxLengthVal), (yyvsp[-5].attribPairListVal));
	   (yyval.maxLengthVal)->note = (yyvsp[-3].annotationVal);
	  }

    break;

  case 171:

    {(yyval.minExclusiveVal) = new XmlMinExclusive();
	   doXmlMinExclusiveAttributes((yyval.minExclusiveVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 172:

    {(yyval.minExclusiveVal) = new XmlMinExclusive();
	   doXmlMinExclusiveAttributes((yyval.minExclusiveVal), (yyvsp[-5].attribPairListVal));
	   (yyval.minExclusiveVal)->note = (yyvsp[-3].annotationVal);
	  }

    break;

  case 173:

    {(yyval.minInclusiveVal) = new XmlMinInclusive();
	   doXmlMinInclusiveAttributes((yyval.minInclusiveVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 174:

    {(yyval.minInclusiveVal) = new XmlMinInclusive();
	   doXmlMinInclusiveAttributes((yyval.minInclusiveVal), (yyvsp[-5].attribPairListVal));
	   (yyval.minInclusiveVal)->note = (yyvsp[-3].annotationVal);
	  }

    break;

  case 175:

    {(yyval.minLengthVal) = new XmlMinLength();
	   doXmlMinLengthAttributes((yyval.minLengthVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 176:

    {(yyval.minLengthVal) = new XmlMinLength();
	   doXmlMinLengthAttributes((yyval.minLengthVal), (yyvsp[-5].attribPairListVal));
	   (yyval.minLengthVal)->note = (yyvsp[-3].annotationVal);
	  }

    break;

  case 177:

    {(yyval.nsPairVal) = new XmlNsPair((yyvsp[-1].sVal), (yyvsp[0].sVal));}

    break;

  case 178:

    {(yyval.nsPairListVal) = new std::list<XmlNsPair *>;
	   (yyval.nsPairListVal)->push_back((yyvsp[0].nsPairVal));
	  }

    break;

  case 179:

    {(yyval.nsPairListVal) = (yyvsp[-1].nsPairListVal);
	   (yyvsp[-1].nsPairListVal)->push_back((yyvsp[0].nsPairVal));
	  }

    break;

  case 180:

    {(yyval.otherContentVal) = new XmlOtherContent((yyvsp[0].otherContentBaseVal), 0);}

    break;

  case 181:

    {(yyval.otherContentVal) = new XmlOtherContent(0, (yyvsp[0].attributorListVal));}

    break;

  case 182:

    {(yyval.otherContentVal) = new XmlOtherContent((yyvsp[-1].otherContentBaseVal), (yyvsp[0].attributorListVal));}

    break;

  case 183:

    {(yyval.otherContentBaseVal) = (yyvsp[0].choiceVal);}

    break;

  case 184:

    {(yyval.otherContentBaseVal) = (yyvsp[0].sequenceVal);}

    break;

  case 185:

    {(yyval.otherContentBaseVal) = (yyvsp[0].elementGroupRefVal);}

    break;

  case 186:

    {(yyval.patternVal) = new XmlPattern();
	   doXmlPatternAttributes((yyval.patternVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 187:

    {(yyval.patternVal) = new XmlPattern();
	   doXmlPatternAttributes((yyval.patternVal), (yyvsp[-5].attribPairListVal));
	   (yyval.patternVal)->frontNote = (yyvsp[-3].annotationVal);
	  }

    break;

  case 188:

    {(yyval.restrictionTypeVal) = (yyvsp[0].lengthVal);}

    break;

  case 189:

    {(yyval.restrictionTypeVal) = (yyvsp[0].maxExclusiveVal);}

    break;

  case 190:

    {(yyval.restrictionTypeVal) = (yyvsp[0].maxInclusiveVal);}

    break;

  case 191:

    {(yyval.restrictionTypeVal) = (yyvsp[0].maxLengthVal);}

    break;

  case 192:

    {(yyval.restrictionTypeVal) = (yyvsp[0].minExclusiveVal);}

    break;

  case 193:

    {(yyval.restrictionTypeVal) = (yyvsp[0].minInclusiveVal);}

    break;

  case 194:

    {(yyval.restrictionTypeVal) = (yyvsp[0].minLengthVal);}

    break;

  case 195:

    {(yyval.restrictionTypeVal) = (yyvsp[0].patternVal);}

    break;

  case 196:

    {(yyval.restrictionTypeVal) = (yyvsp[0].enumerationVal);}

    break;

  case 197:

    {(yyval.restrictionTypeListVal) = new std::list<XmlRestrictionType *>;
	   (yyval.restrictionTypeListVal)->push_back((yyvsp[0].restrictionTypeVal));
	  }

    break;

  case 198:

    {(yyval.restrictionTypeListVal) = (yyvsp[-1].restrictionTypeListVal);
	   (yyvsp[-1].restrictionTypeListVal)->push_back((yyvsp[0].restrictionTypeVal));
	  }

    break;

  case 199:

    {(yyval.schemaVal) = new XmlSchema((yyvsp[-5].schemaHeaderVal), (yyvsp[-4].schemaContent1ListVal), (yyvsp[-3].schemaContent2ListVal));}

    break;

  case 200:

    {(yyval.schemaContent1Val) = (yyvsp[0].includeVal);}

    break;

  case 201:

    {(yyval.schemaContent1Val) = (yyvsp[0].importVal);}

    break;

  case 202:

    {(yyval.schemaContent1Val) = (yyvsp[0].annotationVal);}

    break;

  case 203:

    {(yyval.schemaContent1ListVal) = new std::list<XmlSchemaContent1 *>;
	    //$$->push_back($1);
	  }

    break;

  case 204:

    {(yyval.schemaContent1ListVal) = (yyvsp[-1].schemaContent1ListVal);
	   (yyvsp[-1].schemaContent1ListVal)->push_back((yyvsp[0].schemaContent1Val));
	  }

    break;

  case 205:

    {(yyval.schemaContent2Val) = (yyvsp[0].elementGroupVal);}

    break;

  case 206:

    {(yyval.schemaContent2Val) = (yyvsp[-1].elementGroupVal);
	   (yyval.schemaContent2Val)->backNotes = (yyvsp[0].annotationListVal);
	  }

    break;

  case 207:

    {(yyval.schemaContent2Val) = (yyvsp[0].elementRefableVal);}

    break;

  case 208:

    {(yyval.schemaContent2Val) = (yyvsp[-1].elementRefableVal);
	   (yyval.schemaContent2Val)->backNotes = (yyvsp[0].annotationListVal);
	  }

    break;

  case 209:

    {(yyval.schemaContent2Val) = (yyvsp[0].complexTypeVal);}

    break;

  case 210:

    {(yyval.schemaContent2Val) = (yyvsp[-1].complexTypeVal);
	   (yyval.schemaContent2Val)->backNotes = (yyvsp[0].annotationListVal);
	  }

    break;

  case 211:

    {(yyval.schemaContent2Val) = (yyvsp[0].simpleTypeVal);}

    break;

  case 212:

    {(yyval.schemaContent2Val) = (yyvsp[-1].simpleTypeVal);
	   (yyval.schemaContent2Val)->backNotes = (yyvsp[0].annotationListVal);
	  }

    break;

  case 213:

    {(yyval.schemaContent2Val) = (yyvsp[0].attributeLonerRefableVal);}

    break;

  case 214:

    {(yyval.schemaContent2Val) = (yyvsp[-1].attributeLonerRefableVal);
	   (yyval.schemaContent2Val)->backNotes = (yyvsp[0].annotationListVal);
	  }

    break;

  case 215:

    {(yyval.schemaContent2Val) = (yyvsp[0].attributeGroupRefableVal);}

    break;

  case 216:

    {(yyval.schemaContent2Val) = (yyvsp[-1].attributeGroupRefableVal);
	   (yyval.schemaContent2Val)->backNotes = (yyvsp[0].annotationListVal);
	  }

    break;

  case 217:

    {(yyval.schemaContent2ListVal) = new std::list<XmlSchemaContent2 *>;
	   (yyval.schemaContent2ListVal)->push_back((yyvsp[0].schemaContent2Val));
	  }

    break;

  case 218:

    {(yyval.schemaContent2ListVal) = (yyvsp[-1].schemaContent2ListVal);
	   (yyvsp[-1].schemaContent2ListVal)->push_back((yyvsp[0].schemaContent2Val));
	  }

    break;

  case 219:

    { // note that this removes the first element from the xmlNsList
	    checkXmlns((yyvsp[-2].nsPairListVal));
	    (yyval.schemaHeaderVal) = new XmlSchemaHeader(0, 0, (yyvsp[-2].nsPairListVal)->front(), (yyvsp[-2].nsPairListVal),
				     XmlCppBase::formNone,
				     XmlCppBase::formNone, 0);
	    doXmlSchemaHeaderAttributes((yyval.schemaHeaderVal), (yyvsp[-1].attribPairListVal));
	    (yyvsp[-2].nsPairListVal)->pop_front();
	  }

    break;

  case 220:

    {(yyval.selectorVal) = new XmlSelector();
	   doXmlSelectorAttributes((yyval.selectorVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 221:

    {(yyval.selectorVal) = new XmlSelector();
	   doXmlSelectorAttributes((yyval.selectorVal), (yyvsp[-4].attribPairListVal));
	  }

    break;

  case 222:

    {(yyval.sequenceVal) = new XmlSequence();
	   doXmlSequenceAttributes((yyval.sequenceVal), (yyvsp[-5].attribPairListVal));
	   (yyval.sequenceVal)->items = (yyvsp[-3].choSeqItemListVal);
	  }

    break;

  case 223:

    {(yyval.sequenceVal) = new XmlSequence();
	   doXmlSequenceAttributes((yyval.sequenceVal), (yyvsp[-6].attribPairListVal));
	   (yyval.sequenceVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.sequenceVal)->items = (yyvsp[-3].choSeqItemListVal);
	  }

    break;

  case 224:

    {(yyval.simpleContentVal) = new XmlSimpleContent();
	   doXmlSimpleContentAttributes((yyval.simpleContentVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 225:

    {(yyval.simpleContentVal) = new XmlSimpleContent();
	   doXmlSimpleContentAttributes((yyval.simpleContentVal), (yyvsp[-4].attribPairListVal));
	  }

    break;

  case 226:

    {(yyval.simpleContentVal) = new XmlSimpleContent();
	   doXmlSimpleContentAttributes((yyval.simpleContentVal), (yyvsp[-5].attribPairListVal));
	   (yyval.simpleContentVal)->frontNote = (yyvsp[-3].annotationVal);
	  }

    break;

  case 227:

    {(yyval.simpleContentVal) = new XmlSimpleContent();
	   doXmlSimpleContentAttributes((yyval.simpleContentVal), (yyvsp[-5].attribPairListVal));
	   (yyval.simpleContentVal)->item = (yyvsp[-3].simpleContentItemVal);
	  }

    break;

  case 228:

    {(yyval.simpleContentVal) = new XmlSimpleContent();
	   doXmlSimpleContentAttributes((yyval.simpleContentVal), (yyvsp[-6].attribPairListVal));
	   (yyval.simpleContentVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.simpleContentVal)->item = (yyvsp[-3].simpleContentItemVal);
	  }

    break;

  case 229:

    {(yyval.simpleContentItemVal) = (yyvsp[0].simpleContentExtensionVal);}

    break;

  case 230:

    {(yyval.simpleContentItemVal) = (yyvsp[0].simpleContentRestrictionVal);}

    break;

  case 231:

    {(yyval.simpleContentExtensionVal) = new XmlSimpleContentExtension();
	   doXmlSimpleContentExtensionAttributes((yyval.simpleContentExtensionVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 232:

    {(yyval.simpleContentExtensionVal) = new XmlSimpleContentExtension();
	   doXmlSimpleContentExtensionAttributes((yyval.simpleContentExtensionVal), (yyvsp[-4].attribPairListVal));
	  }

    break;

  case 233:

    {(yyval.simpleContentExtensionVal) = new XmlSimpleContentExtension();
	   doXmlSimpleContentExtensionAttributes((yyval.simpleContentExtensionVal), (yyvsp[-5].attribPairListVal));
	   (yyval.simpleContentExtensionVal)->frontNote = (yyvsp[-3].annotationVal);
	  }

    break;

  case 234:

    {(yyval.simpleContentExtensionVal) = new XmlSimpleContentExtension();
	   doXmlSimpleContentExtensionAttributes((yyval.simpleContentExtensionVal), (yyvsp[-5].attribPairListVal));
	   (yyval.simpleContentExtensionVal)->attribs = (yyvsp[-3].attributorListVal);
	  }

    break;

  case 235:

    {(yyval.simpleContentExtensionVal) = new XmlSimpleContentExtension();
	   doXmlSimpleContentExtensionAttributes((yyval.simpleContentExtensionVal), (yyvsp[-6].attribPairListVal));
	   (yyval.simpleContentExtensionVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.simpleContentExtensionVal)->attribs = (yyvsp[-3].attributorListVal);
	  }

    break;

  case 236:

    {(yyval.simpleContentRestrictionVal) = new XmlSimpleContentRestriction();
	   doXmlSimpleContentRestrictionAttributes((yyval.simpleContentRestrictionVal), (yyvsp[-1].attribPairListVal));
	   (yyval.simpleContentRestrictionVal)->restrictions = new std::list<XmlRestrictionType *>;
	  }

    break;

  case 237:

    {(yyval.simpleContentRestrictionVal) = new XmlSimpleContentRestriction();
	   doXmlSimpleContentRestrictionAttributes((yyval.simpleContentRestrictionVal), (yyvsp[-4].attribPairListVal));
	   (yyval.simpleContentRestrictionVal)->restrictions = new std::list<XmlRestrictionType *>;
	  }

    break;

  case 238:

    {(yyval.simpleContentRestrictionVal) = new XmlSimpleContentRestriction();
	   doXmlSimpleContentRestrictionAttributes((yyval.simpleContentRestrictionVal), (yyvsp[-5].attribPairListVal));
	   (yyval.simpleContentRestrictionVal)->frontNote = (yyvsp[-3].annotationVal);
	   (yyval.simpleContentRestrictionVal)->restrictions = new std::list<XmlRestrictionType *>;
	  }

    break;

  case 239:

    {(yyval.simpleContentRestrictionVal) = new XmlSimpleContentRestriction();
	   doXmlSimpleContentRestrictionAttributes((yyval.simpleContentRestrictionVal), (yyvsp[-5].attribPairListVal));
	   (yyval.simpleContentRestrictionVal)->restrictions = (yyvsp[-3].restrictionTypeListVal);
	  }

    break;

  case 240:

    {(yyval.simpleContentRestrictionVal) = new XmlSimpleContentRestriction();
	   doXmlSimpleContentRestrictionAttributes((yyval.simpleContentRestrictionVal), (yyvsp[-5].attribPairListVal));
	   (yyval.simpleContentRestrictionVal)->attribs = (yyvsp[-3].attributorListVal);
	   (yyval.simpleContentRestrictionVal)->restrictions = new std::list<XmlRestrictionType *>;
	  }

    break;

  case 241:

    {(yyval.simpleContentRestrictionVal) = new XmlSimpleContentRestriction();
	   doXmlSimpleContentRestrictionAttributes((yyval.simpleContentRestrictionVal), (yyvsp[-6].attribPairListVal));
	   (yyval.simpleContentRestrictionVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.simpleContentRestrictionVal)->restrictions = (yyvsp[-3].restrictionTypeListVal);
	  }

    break;

  case 242:

    {(yyval.simpleContentRestrictionVal) = new XmlSimpleContentRestriction();
	   doXmlSimpleContentRestrictionAttributes((yyval.simpleContentRestrictionVal), (yyvsp[-6].attribPairListVal));
	   (yyval.simpleContentRestrictionVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.simpleContentRestrictionVal)->attribs = (yyvsp[-3].attributorListVal);
	  }

    break;

  case 243:

    {(yyval.simpleContentRestrictionVal) = new XmlSimpleContentRestriction();
	   doXmlSimpleContentRestrictionAttributes((yyval.simpleContentRestrictionVal), (yyvsp[-6].attribPairListVal));
	   (yyval.simpleContentRestrictionVal)->restrictions = (yyvsp[-4].restrictionTypeListVal);
	   (yyval.simpleContentRestrictionVal)->attribs = (yyvsp[-3].attributorListVal);
	  }

    break;

  case 244:

    {(yyval.simpleContentRestrictionVal) = new XmlSimpleContentRestriction();
	   doXmlSimpleContentRestrictionAttributes((yyval.simpleContentRestrictionVal), (yyvsp[-7].attribPairListVal));
	   (yyval.simpleContentRestrictionVal)->frontNote = (yyvsp[-5].annotationVal);
	   (yyval.simpleContentRestrictionVal)->restrictions = (yyvsp[-4].restrictionTypeListVal);
	   (yyval.simpleContentRestrictionVal)->attribs = (yyvsp[-3].attributorListVal);
	  }

    break;

  case 245:

    {(yyval.simpleItemVal) = (yyvsp[0].simpleListVal);}

    break;

  case 246:

    {(yyval.simpleItemVal) = (yyvsp[0].simpleRestrictionVal);}

    break;

  case 247:

    {(yyval.simpleListVal) = new XmlSimpleList();
	   doXmlSimpleListAttributes((yyval.simpleListVal), (yyvsp[-1].attribPairListVal));
	  }

    break;

  case 248:

    {(yyval.simpleListVal) = new XmlSimpleList();
	   doXmlSimpleListAttributes((yyval.simpleListVal), (yyvsp[-4].attribPairListVal));
	  }

    break;

  case 249:

    {(yyval.simpleRestrictionVal) = new XmlSimpleRestriction();
	   doXmlSimpleRestrictionAttributes((yyval.simpleRestrictionVal), (yyvsp[-1].attribPairListVal));
	   (yyval.simpleRestrictionVal)->restrictions = new std::list<XmlRestrictionType *>;
	  }

    break;

  case 250:

    {(yyval.simpleRestrictionVal) = new XmlSimpleRestriction();
	   doXmlSimpleRestrictionAttributes((yyval.simpleRestrictionVal), (yyvsp[-4].attribPairListVal));
	   (yyval.simpleRestrictionVal)->restrictions = new std::list<XmlRestrictionType *>;
	  }

    break;

  case 251:

    {(yyval.simpleRestrictionVal) = new XmlSimpleRestriction();
	   doXmlSimpleRestrictionAttributes((yyval.simpleRestrictionVal), (yyvsp[-5].attribPairListVal));
	   (yyval.simpleRestrictionVal)->restrictions = (yyvsp[-3].restrictionTypeListVal);
	  }

    break;

  case 252:

    {(yyval.simpleTypeVal) = new XmlSimpleType();
	   doXmlSimpleTypeAttributes((yyval.simpleTypeVal), (yyvsp[-5].attribPairListVal));
	   (yyval.simpleTypeVal)->item = (yyvsp[-3].simpleItemVal);
	  }

    break;

  case 253:

    {(yyval.simpleTypeVal) = new XmlSimpleType();
	   doXmlSimpleTypeAttributes((yyval.simpleTypeVal), (yyvsp[-6].attribPairListVal));
	   (yyval.simpleTypeVal)->frontNote = (yyvsp[-4].annotationVal);
	   (yyval.simpleTypeVal)->item = (yyvsp[-3].simpleItemVal);
	  }

    break;

  case 254:

    {(yyval.uniqueVal) = new XmlUnique();
           doXmlUniqueAttributes((yyval.uniqueVal), (yyvsp[-6].attribPairListVal));
	   (yyval.uniqueVal)->selector = (yyvsp[-4].selectorVal);
	   (yyval.uniqueVal)->fields = (yyvsp[-3].fieldListVal);
	  }

    break;

  case 255:

    {(yyval.uniqueVal) = new XmlUnique();
           doXmlUniqueAttributes((yyval.uniqueVal), (yyvsp[-6].attribPairListVal));
	   (yyval.uniqueVal)->comments = (yyvsp[-9].commentListVal);
	   (yyval.uniqueVal)->selector = (yyvsp[-4].selectorVal);
	   (yyval.uniqueVal)->fields = (yyvsp[-3].fieldListVal);
	  }

    break;

  case 256:

    {(yyval.uniqueVal) = new XmlUnique();
           doXmlUniqueAttributes((yyval.uniqueVal), (yyvsp[-7].attribPairListVal));
	   (yyval.uniqueVal)->frontNote = (yyvsp[-5].annotationVal);
	   (yyval.uniqueVal)->selector = (yyvsp[-4].selectorVal);
	   (yyval.uniqueVal)->fields = (yyvsp[-3].fieldListVal);
	  }

    break;

  case 257:

    {(yyval.uniqueVal) = new XmlUnique();
           doXmlUniqueAttributes((yyval.uniqueVal), (yyvsp[-7].attribPairListVal));
           (yyval.uniqueVal)->comments = (yyvsp[-10].commentListVal);
	   (yyval.uniqueVal)->frontNote = (yyvsp[-5].annotationVal);
	   (yyval.uniqueVal)->selector = (yyvsp[-4].selectorVal);
	   (yyval.uniqueVal)->fields = (yyvsp[-3].fieldListVal);
	  }

    break;

  case 258:

    {(yyval.versionVal) = new XmlVersion(false);
	   if (strcmp((yyvsp[-1].sVal), "1.0"))
	     yyerror("version number must be 1.0");
	  }

    break;

  case 259:

    {(yyval.versionVal) = new XmlVersion(true);
	   if (strcmp((yyvsp[-3].sVal), "1.0"))
	     yyerror("version number must be 1.0");
	   else if ((strcmp((yyvsp[-1].sVal), "UTF-8")) && (strcmp((yyvsp[-1].sVal), "utf-8")))
	     yyerror("encoding must be UTF-8");
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



/********************************************************************/

/* yyerror

Returned Value: int (0)

Called By: yyparse

This prints whatever string the parser provides.

*/

int yyerror(     /* ARGUMENTS       */
 const char * s) /* string to print */
{
  fflush(stdout);
  if (lexMessage[0])
    {
      printf("\n%s\n", lexMessage);
      lexMessage[0] = 0;
      exit(1);
    }
  else
    {
      fprintf(stderr, "\n%s\n", s);
      exit(1);
    }
  return 0;
}

/********************************************************************/

