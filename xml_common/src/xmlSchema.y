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
#define YYDEBUG 1
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


%}

%union {
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
}

%type <attribPairVal>               attributePair
%type <attribPairListVal>           attributePairList
%type <iVal>                        attributeName
%type <sVal>                        schemaLocation
%type <typVal>                      typeDef
%type <annotationVal>               xmlAnnotation
%type <annotationListVal>           xmlAnnotationList
%type <annoTypeVal>                 xmlAnnoType
%type <annoTypeListVal>             xmlAnnoTypeList
%type <anyVal>                      xmlAny
%type <appinfoVal>                  xmlAppinfo
%type <attributorVal>               xmlAttributor
%type <attributorListVal>           xmlAttributorList
%type <attributeLonerVal>           xmlAttributeLoner
%type <attributeLonerRefableVal>    xmlAttributeLonerRefable
%type <attributeGroupRefVal>        xmlAttributeGroupRef
%type <attributeGroupRefableVal>    xmlAttributeGroupRefable
%type <choiceVal>                   xmlChoice
%type <choSeqItemVal>               xmlChoSeqItem
%type <choSeqItemListVal>           xmlChoSeqItemList
%type <commentVal>                  xmlComment
%type <commentListVal>              xmlCommentList
%type <complexContentVal>           xmlComplexContent
%type <complexContentItemVal>       xmlComplexContentItem
%type <complexExtensionVal>         xmlComplexExtension
%type <complexRestrictionVal>       xmlComplexRestriction
%type <complexTypeVal>              xmlComplexType
%type <complexTypeItemVal>          xmlComplexTypeItem
%type <documentationVal>            xmlDocumentation
%type <elementGroupVal>             xmlElementGroup
%type <elementGroupRefVal>          xmlElementGroupRef
%type <elementLocalVal>             xmlElementLocal
%type <elementRefableVal>           xmlElementRefable
%type <enumerationVal>              xmlEnumeration
%type <fieldVal>                    xmlField
%type <fieldListVal>                xmlFieldList
%type <idConstraintVal>             xmlIdConstraint
%type <idConstraintListVal>         xmlIdConstraintList
%type <importVal>                   xmlImport
%type <includeVal>                  xmlInclude
%type <keyVal>                      xmlKey
%type <keyrefVal>                   xmlKeyref
%type <lengthVal>                   xmlLength
%type <maxExclusiveVal>             xmlMaxExclusive
%type <maxInclusiveVal>             xmlMaxInclusive
%type <maxLengthVal>                xmlMaxLength
%type <minExclusiveVal>             xmlMinExclusive
%type <minInclusiveVal>             xmlMinInclusive
%type <minLengthVal>                xmlMinLength
%type <nsPairVal>                   xmlNs
%type <nsPairListVal>               xmlNsList
%type <otherContentVal>             xmlOtherContent
%type <otherContentBaseVal>         xmlOtherContentBase
%type <patternVal>                  xmlPattern
%type <restrictionTypeVal>          xmlRestrictionType
%type <restrictionTypeListVal>      xmlRestrictionTypeList
%type <schemaVal>                   xmlSchema
%type <schemaFileVal>               xmlSchemaFile
%type <schemaHeaderVal>             xmlSchemaHeader
%type <schemaContent1Val>           xmlSchemaContent1
%type <schemaContent1ListVal>       xmlSchemaContent1List
%type <schemaContent2Val>           xmlSchemaContent2
%type <schemaContent2ListVal>       xmlSchemaContent2List
%type <selectorVal>                 xmlSelector
%type <sequenceVal>                 xmlSequence
%type <simpleContentVal>            xmlSimpleContent
%type <simpleContentItemVal>        xmlSimpleContentItem
%type <simpleContentExtensionVal>   xmlSimpleContentExtension
%type <simpleContentRestrictionVal> xmlSimpleContentRestriction
%type <simpleItemVal>               xmlSimpleItem
%type <simpleListVal>               xmlSimpleList
%type <simpleRestrictionVal>        xmlSimpleRestriction
%type <simpleTypeVal>               xmlSimpleType
%type <uniqueVal>                   xmlUnique
%type <versionVal>                  xmlVersion

%token <iVal> ABSTRACT
%token <iVal> ATTRIBUTEFORMDEFAULT
%token <iVal> BAD
%token <iVal> BASE
%token <iVal> DEFALT
%token <iVal> ELEMENTFORMDEFAULT
%token <iVal> ENCODING
%token <iVal> ENDCOMMENT
%token <iVal> ENDITEM
%token <iVal> ENDWHOLEITEM
%token <iVal> ENDVERSION
%token <iVal> FINAL
%token <iVal> FIXED
%token <iVal> FORM
%token <iVal> ID
%token <iVal> ITEMTYPE
%token <iVal> MAXOCCURS
%token <iVal> MINOCCURS
%token <iVal> MIXED
%token <iVal> NAME
%token <iVal> NILLABLE
%token <iVal> NAMESPACE
%token <iVal> PROCESSCONTENTS
%token <iVal> REF
%token <iVal> REFER
%token <iVal> SCHEMALOCATION
%token <iVal> SOURCE
%token <iVal> STARTANNOTATION
%token <iVal> STARTCOMMENT
%token <iVal> STARTINITEM
%token <iVal> STARTOUTITEM
%token <iVal> STARTVERSION
%token <iVal> SUBSTITUTIONGROUP
%token <iVal> TARGETNAMESPACE
%token <sVal> TERMINALSTRING
%token <iVal> TYP
%token <iVal> USE
%token <iVal> VALUE
%token <iVal> VERSION
%token <iVal> XPATH
%token <sVal> XMLNSPREFIX
%token <iVal> XSANNOTATION
%token <iVal> XSANY
%token <iVal> XSAPPINFO
%token <iVal> XSATTRIBUTE
%token <iVal> XSATTRIBUTEGROUP
%token <iVal> XSCHOICE
%token <iVal> XSCOMPLEXCONTENT
%token <iVal> XSCOMPLEXTYPE
%token <iVal> XSDOCUMENTATION
%token <iVal> XSELEMENT
%token <iVal> XSENUMERATION
%token <iVal> XSEXTENSION
%token <iVal> XSFIELD
%token <iVal> XSGROUP
%token <iVal> XSIMPORT
%token <iVal> XSINCLUDE
%token <iVal> XSKEY
%token <iVal> XSKEYREF
%token <iVal> XSLENGTH
%token <iVal> XSLIST
%token <iVal> XSMAXEXCLUSIVE
%token <iVal> XSMAXINCLUSIVE
%token <iVal> XSMAXLENGTH
%token <iVal> XSMINEXCLUSIVE
%token <iVal> XSMININCLUSIVE
%token <iVal> XSMINLENGTH
%token <iVal> XSPATTERN
%token <iVal> XSRESTRICTION
%token <iVal> XSSCHEMA
%token <iVal> XSSELECTOR
%token <iVal> XSSEQUENCE
%token <iVal> XSSIMPLECONTENT
%token <iVal> XSSIMPLETYPE
%token <iVal> XSUNIQUE
%token <iVal> XMLVERSION

%initial-action
{
  globalOwlPrefix = strdup("");
}

%%

xmlSchemaFile :
	  xmlVersion xmlSchema
          {$$ = new XmlSchemaFile($1, 0, $2);
	   xmlSchemaFile = $$;
	  }
	| xmlVersion xmlCommentList xmlSchema
          {$$ = new XmlSchemaFile($1, $2, $3);
	   xmlSchemaFile = $$;
	  }
	;

attributePair :
	  attributeName TERMINALSTRING
          {$$ = new XmlAttribPair($1, 0, $2);}
	| attributeName XMLNSPREFIX TERMINALSTRING
	  {$$ = new XmlAttribPair($1, $2, $3);}
	;

attributePairList :
	  /* empty */
          {$$ = new std::list<XmlAttribPair *>;}
	| attributePairList attributePair
          {$$ = $1;
	   std::list<XmlAttribPair *>::iterator iter;
	   for (iter = $1->begin(); iter != $1->end(); iter++)
	     { // check for duplicate names
	       if ((*iter)->name == $2->name)
		 {
		   snprintf(errMessage, ERRSIZE,
			    "attribute \"%s\" used twice",
			    attNames[(*iter)->name - ABSTRACT]);
		   yyerror(errMessage);
		 }
	     }
	   $1->push_back($2);}
	;

attributeName :
	  ABSTRACT {$$ = ABSTRACT;}
	| ATTRIBUTEFORMDEFAULT {$$ = ATTRIBUTEFORMDEFAULT;}
	| BASE {$$ = BASE;}
	| DEFALT {$$ = DEFALT;}
	| ELEMENTFORMDEFAULT {$$ = ELEMENTFORMDEFAULT;}
	| FINAL {$$ = FINAL;}
	| FIXED {$$ = FIXED;}
	| FORM {$$ = FORM;}
	| ID {$$ = ID;}
	| ITEMTYPE {$$ = ITEMTYPE;}
        | NAMESPACE {$$ = NAMESPACE;}
	| MAXOCCURS {$$ = MAXOCCURS;}
	| MINOCCURS {$$ = MINOCCURS;}
	| MIXED {$$ = MIXED;}
	| NAME {$$ = NAME;}
	| NILLABLE {$$ = NILLABLE;}
	| PROCESSCONTENTS {$$ = PROCESSCONTENTS;}
	| REF {$$ = REF;}
	| REFER {$$ = REFER;}
	| SCHEMALOCATION {$$ = SCHEMALOCATION;}
	| SOURCE {$$ = SOURCE;}
	| SUBSTITUTIONGROUP {$$ = SUBSTITUTIONGROUP;}
	| TARGETNAMESPACE {$$ = TARGETNAMESPACE;}
	| TYP {$$ = TYP;}
	| USE {$$ = USE;}
	| VALUE {$$ = VALUE;}
	| VERSION {$$ = VERSION;}
	| XPATH {$$ = XPATH;}
	;

schemaLocation :
	  SCHEMALOCATION TERMINALSTRING
          {$$ = $2;}
	;

typeDef :
	  xmlComplexType {$$ = $1;}
	| xmlSimpleType {$$ = $1;}
	;

xmlAnnotation :
	  STARTANNOTATION attributePairList ENDITEM
	  xmlAnnoTypeList
	  STARTOUTITEM XSANNOTATION ENDITEM
	  {$$ = new XmlAnnotation();
	   doXmlAnnotationAttributes($$, $2);
           $$->comment = 0;
	   $$->content = $4;
	  }
	| xmlComment
          STARTANNOTATION attributePairList ENDITEM
	  xmlAnnoTypeList
	  STARTOUTITEM XSANNOTATION ENDITEM
	  {$$ = new XmlAnnotation();
	   doXmlAnnotationAttributes($$, $3);
           $$->comment = $1;
	   $$->content = $5;
	  }
	| xmlComment
	  {$$ = new XmlAnnotation(); // makes shift/reduce conflict but is OK
	   $$->comment = $1;
	   $$->content = 0;
	  }
	;

xmlAnnotationList :
	  xmlAnnotation
          {$$ = new std::list<XmlAnnotation *>;
	   $$->push_back($1);
          }
	| xmlAnnotationList xmlAnnotation
	  {$$ = $1;
	   $1->push_back($2);
	  }
	;

xmlAnnoType :
	  xmlDocumentation {$$ = $1;}
	| xmlAppinfo {$$ = $1;}
	;

xmlAnnoTypeList :
	  /* empty */
          {$$ = new std::list<XmlAnnoType *>;}
	| xmlAnnoTypeList xmlAnnoType
          {$$ = $1;
	   $1->push_back($2);
	  }
	;

xmlAny: 
	  STARTINITEM XSANY ENDWHOLEITEM
	  {$$ = new XmlAny();
	  }
	| STARTINITEM XSANY attributePairList ENDITEM
	  STARTOUTITEM XSANY ENDITEM
	  {$$ = new XmlAny();
	   doXmlAnyAttributes($$, $3);
	  }
	| STARTINITEM XSANY attributePairList ENDITEM
	  xmlAnnotation
	  STARTOUTITEM XSANY ENDITEM
	  {$$ = new XmlAny();
	   doXmlAnyAttributes($$, $3);
	   $$->frontNote = $5;
	  }
	;
xmlAppinfo : 
	  STARTINITEM XSAPPINFO attributePairList ENDITEM
	  TERMINALSTRING
	  STARTOUTITEM XSAPPINFO ENDITEM
	  {$$ = new XmlAppinfo();
	   doXmlAppinfoAttributes($$, $3);
	   $$->text = $5;
	  }
	| STARTINITEM XSAPPINFO attributePairList ENDITEM
	  STARTOUTITEM XSAPPINFO ENDITEM
	  {$$ = new XmlAppinfo();
	   doXmlAppinfoAttributes($$, $3);
	   $$->text = strdup("");
	  }
	;

xmlAttributeLoner : 
	  STARTINITEM XSATTRIBUTE attributePairList ENDWHOLEITEM
	  {$$ = new XmlAttributeLoner();
	   doXmlAttributeLonerAttributes($$, $3);
	  }
	| STARTINITEM XSATTRIBUTE attributePairList ENDITEM
	  STARTOUTITEM XSATTRIBUTE ENDITEM
	  {$$ = new XmlAttributeLoner();
	   doXmlAttributeLonerAttributes($$, $3);
	  }
	| STARTINITEM XSATTRIBUTE attributePairList ENDITEM
	  xmlSimpleType
	  STARTOUTITEM XSATTRIBUTE ENDITEM
	  {$$ = new XmlAttributeLoner();
	   doXmlAttributeLonerAttributes($$, $3);
	   $$->simple = $5;
	  }
	| STARTINITEM XSATTRIBUTE attributePairList ENDITEM
	  xmlAnnotation
	  STARTOUTITEM XSATTRIBUTE ENDITEM
	  {$$ = new XmlAttributeLoner();
	   doXmlAttributeLonerAttributes($$, $3);
	   $$->frontNote = $5;
	  }
	| STARTINITEM XSATTRIBUTE attributePairList ENDITEM
	  xmlAnnotation xmlSimpleType
	  STARTOUTITEM XSATTRIBUTE ENDITEM
	  {$$ = new XmlAttributeLoner();
	   doXmlAttributeLonerAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->simple = $6;
	  }
	;

xmlAttributeLonerRefable : 
	  STARTINITEM XSATTRIBUTE attributePairList ENDWHOLEITEM
	  {$$ = new XmlAttributeLonerRefable();
	   doXmlAttributeLonerRefableAttributes($$, $3);
	  }
	| STARTINITEM XSATTRIBUTE attributePairList ENDITEM
	  xmlSimpleType
	  STARTOUTITEM XSATTRIBUTE ENDITEM
	  {$$ = new XmlAttributeLonerRefable();
	   doXmlAttributeLonerRefableAttributes($$, $3);
	   $$->simple = $5;
	  }
	| STARTINITEM XSATTRIBUTE attributePairList ENDITEM
	  xmlAnnotation
	  STARTOUTITEM XSATTRIBUTE ENDITEM
	  {$$ = new XmlAttributeLonerRefable();
	   doXmlAttributeLonerRefableAttributes($$, $3);
	   $$->frontNote = $5;
	  }
	| STARTINITEM XSATTRIBUTE attributePairList ENDITEM
	  xmlAnnotation xmlSimpleType
	  STARTOUTITEM XSATTRIBUTE ENDITEM
	  {$$ = new XmlAttributeLonerRefable();
	   doXmlAttributeLonerRefableAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->simple = $6;
	  }
	;

xmlAttributeGroupRef : 
	  STARTINITEM XSATTRIBUTEGROUP attributePairList ENDWHOLEITEM
	  {$$ = new XmlAttributeGroupRef();
	   doXmlAttributeGroupRefAttributes($$, $3);
	  }
	| STARTINITEM XSATTRIBUTEGROUP attributePairList ENDITEM
	  STARTOUTITEM XSATTRIBUTEGROUP ENDITEM
	  {$$ = new XmlAttributeGroupRef();
	   doXmlAttributeGroupRefAttributes($$, $3);
	  }
	| STARTINITEM XSATTRIBUTEGROUP attributePairList ENDITEM
	  xmlAnnotation
	  STARTOUTITEM XSATTRIBUTEGROUP ENDITEM
	  {$$ = new XmlAttributeGroupRef();
	   doXmlAttributeGroupRefAttributes($$, $3);
	   $$->frontNote = $5;
	  }
	;

xmlAttributeGroupRefable : 
	  STARTINITEM XSATTRIBUTEGROUP attributePairList ENDWHOLEITEM
	  {$$ = new XmlAttributeGroupRefable();
	   doXmlAttributeGroupRefableAttributes($$, $3);
	   $$->xmlAttributes = new std::list<XmlAttributor *>;
	  }
	| STARTINITEM XSATTRIBUTEGROUP attributePairList ENDITEM
	  xmlAttributorList
	  STARTOUTITEM XSATTRIBUTEGROUP ENDITEM
	  {$$ = new XmlAttributeGroupRefable();
	   doXmlAttributeGroupRefableAttributes($$, $3);
	   $$->xmlAttributes = $5;
	  }
	| STARTINITEM XSATTRIBUTEGROUP attributePairList ENDITEM
	  xmlAnnotation
	  STARTOUTITEM XSATTRIBUTEGROUP ENDITEM
	  {$$ = new XmlAttributeGroupRefable();
	   doXmlAttributeGroupRefableAttributes($$, $3);
	   $$->frontNote = $5;
	  }
	| STARTINITEM XSATTRIBUTEGROUP attributePairList ENDITEM
	  xmlAnnotation xmlAttributorList
	  STARTOUTITEM XSATTRIBUTEGROUP ENDITEM
	  {$$ = new XmlAttributeGroupRefable();
	   doXmlAttributeGroupRefableAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->xmlAttributes = $6;
	  }
	;

xmlAttributor :
	  xmlAttributeLoner {$$ = $1;}
	| xmlAttributeGroupRef {$$ = $1;}
	;

xmlAttributorList :
	  xmlAttributor
          {$$ = new std::list<XmlAttributor *>;
	   $$->push_back($1);
	  }
	| xmlAttributorList xmlAttributor
          {$$ = $1;
	   $1->push_back($2);
	  }
	;

xmlChoSeqItem :
	  xmlAny {$$ = $1;}
	| xmlChoice {$$ = $1;}
	| xmlElementGroupRef {$$ = $1;}
	| xmlElementLocal {$$ = $1;}
	| xmlSequence {$$ = $1;}
	;

xmlChoSeqItemList :
	  xmlChoSeqItem
	  {$$ = new std::list<XmlChoSeqItem *>;
	   $$->push_back($1);
	  }
	| xmlChoSeqItemList xmlChoSeqItem
          {$$ = $1;
	   $1->push_back($2);
	  }
	;

xmlChoice: 
	  STARTINITEM XSCHOICE attributePairList ENDITEM
	  xmlChoSeqItemList
	  STARTOUTITEM XSCHOICE ENDITEM
	  {$$ = new XmlChoice(); // might add check that list is not empty
	   doXmlChoiceAttributes($$, $3);
	   $$->items = $5;
	  }
	| STARTINITEM XSCHOICE attributePairList ENDITEM
	  xmlAnnotation xmlChoSeqItemList
	  STARTOUTITEM XSCHOICE ENDITEM
	  {$$ = new XmlChoice(); // might add check that list is not empty
	   doXmlChoiceAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->items = $6;
	  }
	;

xmlComment:
	  STARTCOMMENT ENDCOMMENT
	  {$$ = new std::string(commentString);}
	;

xmlCommentList:
	  xmlComment
          {$$ = new std::list<std::string *>;
	   $$->push_back($1);
	  }
	| xmlCommentList xmlComment
          {$$ = $1;
	   $1->push_back($2);
	  }
	;

xmlComplexContent :
	  STARTINITEM XSCOMPLEXCONTENT attributePairList ENDITEM
	  xmlComplexContentItem
	  STARTOUTITEM XSCOMPLEXCONTENT ENDITEM
	  {$$ = new XmlComplexContent();
	   doXmlComplexContentAttributes($$, $3);
	   $$->item = $5;
	  }
	| STARTINITEM XSCOMPLEXCONTENT attributePairList ENDITEM
	  xmlAnnotation xmlComplexContentItem
	  STARTOUTITEM XSCOMPLEXCONTENT ENDITEM
	  {$$ = new XmlComplexContent();
	   doXmlComplexContentAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->item = $6;
	  }
	;

xmlComplexContentItem :
	  xmlComplexRestriction {$$ = $1;}
	| xmlComplexExtension {$$ = $1;}
	;

xmlComplexExtension :
	  STARTINITEM XSEXTENSION attributePairList ENDWHOLEITEM
	  {$$ = new XmlComplexExtension();
	   doXmlComplexExtensionAttributes($$, $3);
	  }
	| STARTINITEM XSEXTENSION attributePairList ENDITEM
	  STARTOUTITEM XSEXTENSION ENDITEM
	  {$$ = new XmlComplexExtension();
	   doXmlComplexExtensionAttributes($$, $3);
	  }
	| STARTINITEM XSEXTENSION attributePairList ENDITEM
	  xmlAnnotation
	  STARTOUTITEM XSEXTENSION ENDITEM
	  {$$ = new XmlComplexExtension();
	   doXmlComplexExtensionAttributes($$, $3);
	   $$->frontNote = $5;
	  }
	| STARTINITEM XSEXTENSION attributePairList ENDITEM
	  xmlAnnotation xmlOtherContentBase
	  STARTOUTITEM XSEXTENSION ENDITEM
	  {$$ = new XmlComplexExtension();
	   doXmlComplexExtensionAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->item = $6;
	  }
	| STARTINITEM XSEXTENSION attributePairList ENDITEM
	  xmlAnnotation xmlAttributorList
	  STARTOUTITEM XSEXTENSION ENDITEM
	  {$$ = new XmlComplexExtension();
	   doXmlComplexExtensionAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->attribs = $6;
	  }
	| STARTINITEM XSEXTENSION attributePairList ENDITEM
	  xmlAnnotation xmlOtherContentBase xmlAttributorList
	  STARTOUTITEM XSEXTENSION ENDITEM
	  {$$ = new XmlComplexExtension();
	   doXmlComplexExtensionAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->item = $6;
	   $$->attribs = $7;
	  }
	| STARTINITEM XSEXTENSION attributePairList ENDITEM
	  xmlOtherContentBase
	  STARTOUTITEM XSEXTENSION ENDITEM
	  {$$ = new XmlComplexExtension();
	   doXmlComplexExtensionAttributes($$, $3);
	   $$->item = $5;
	  }
	| STARTINITEM XSEXTENSION attributePairList ENDITEM
	  xmlOtherContentBase xmlAttributorList
	  STARTOUTITEM XSEXTENSION ENDITEM
	  {$$ = new XmlComplexExtension();
	   doXmlComplexExtensionAttributes($$, $3);
	   $$->item = $5;
	   $$->attribs = $6;
	  }
	| STARTINITEM XSEXTENSION attributePairList ENDITEM
	  xmlAttributorList
	  STARTOUTITEM XSEXTENSION ENDITEM
	  {$$ = new XmlComplexExtension();
	   doXmlComplexExtensionAttributes($$, $3);
	   $$->attribs = $5;
	  }
	;

xmlComplexRestriction :
	  STARTINITEM XSRESTRICTION attributePairList ENDWHOLEITEM
	  {$$ = new XmlComplexRestriction();
	   doXmlComplexRestrictionAttributes($$, $3);
	  }
	| STARTINITEM XSRESTRICTION attributePairList ENDITEM
	  xmlAnnotation
	  STARTOUTITEM XSRESTRICTION ENDITEM
	  {$$ = new XmlComplexRestriction();
	   doXmlComplexRestrictionAttributes($$, $3);
	   $$->frontNote = $5;
	  }
	| STARTINITEM XSRESTRICTION attributePairList ENDITEM
	  xmlAnnotation xmlOtherContentBase
	  STARTOUTITEM XSRESTRICTION ENDITEM
	  {$$ = new XmlComplexRestriction();
	   doXmlComplexRestrictionAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->item = $6;
	  }
	| STARTINITEM XSRESTRICTION attributePairList ENDITEM
	  xmlAnnotation xmlAttributorList
	  STARTOUTITEM XSRESTRICTION ENDITEM
	  {$$ = new XmlComplexRestriction();
	   doXmlComplexRestrictionAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->attribs = $6;
	  }
	| STARTINITEM XSRESTRICTION attributePairList ENDITEM
	  xmlAnnotation xmlOtherContentBase xmlAttributorList
	  STARTOUTITEM XSRESTRICTION ENDITEM
	  {$$ = new XmlComplexRestriction();
	   doXmlComplexRestrictionAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->item = $6;
	   $$->attribs = $7;
	  }
	| STARTINITEM XSRESTRICTION attributePairList ENDITEM
	  xmlOtherContentBase
	  STARTOUTITEM XSRESTRICTION ENDITEM
	  {$$ = new XmlComplexRestriction();
	   doXmlComplexRestrictionAttributes($$, $3);
	   $$->item = $5;
	  }
	| STARTINITEM XSRESTRICTION attributePairList ENDITEM
	  xmlOtherContentBase xmlAttributorList
	  STARTOUTITEM XSRESTRICTION ENDITEM
	  {$$ = new XmlComplexRestriction();
	   doXmlComplexRestrictionAttributes($$, $3);
	   $$->item = $5;
	   $$->attribs = $6;
	  }
	| STARTINITEM XSRESTRICTION attributePairList ENDITEM
	  xmlAttributorList
	  STARTOUTITEM XSRESTRICTION ENDITEM
	  {$$ = new XmlComplexRestriction();
	   doXmlComplexRestrictionAttributes($$, $3);
	   $$->attribs = $5;
	  }
	;

xmlComplexTypeItem :
	  xmlComplexContent {$$ = $1;}
	| xmlOtherContent {$$ = $1;}
	| xmlSimpleContent {$$ = $1;}
	;

xmlComplexType :
	  STARTINITEM XSCOMPLEXTYPE attributePairList ENDWHOLEITEM
	  {$$ = new XmlComplexType();
	   $$->item = new XmlOtherContent(0, 0);
	   doXmlComplexTypeAttributes($$, $3);
	  }
	| STARTINITEM XSCOMPLEXTYPE attributePairList ENDITEM
	  STARTOUTITEM XSCOMPLEXTYPE ENDITEM
	  {$$ = new XmlComplexType();
	   $$->item = new XmlOtherContent(0, 0);
	   doXmlComplexTypeAttributes($$, $3);
	  }
	| STARTINITEM XSCOMPLEXTYPE attributePairList ENDITEM
	  xmlComplexTypeItem
	  STARTOUTITEM XSCOMPLEXTYPE ENDITEM
	  {$$ = new XmlComplexType();
	   doXmlComplexTypeAttributes($$, $3);
	   $$->item = $5;
	  }
	| STARTINITEM XSCOMPLEXTYPE attributePairList ENDITEM
	  xmlAnnotation
	  STARTOUTITEM XSCOMPLEXTYPE ENDITEM
	  {$$ = new XmlComplexType();
	   $$->item = new XmlOtherContent(0, 0);
	   doXmlComplexTypeAttributes($$, $3);
	   $$->frontNote = $5;
	  }
	| STARTINITEM XSCOMPLEXTYPE attributePairList ENDITEM
	  xmlAnnotation xmlComplexTypeItem
	  STARTOUTITEM XSCOMPLEXTYPE ENDITEM
	  {$$ = new XmlComplexType();
	   doXmlComplexTypeAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->item = $6;
	  }
	;

xmlDocumentation : 
	  STARTINITEM XSDOCUMENTATION attributePairList ENDITEM
	  TERMINALSTRING
	  STARTOUTITEM XSDOCUMENTATION ENDITEM
	  {$$ = new XmlDocumentation();
	   $$->text = $5;
	   doXmlDocumentationAttributes($$, $3);
	  }
	| STARTINITEM XSDOCUMENTATION attributePairList ENDITEM
	  STARTOUTITEM XSDOCUMENTATION ENDITEM
	  {$$ = new XmlDocumentation();
	   $$->text = strdup("");
	   doXmlDocumentationAttributes($$, $3);
	  }
	;

xmlElementGroup :
	  STARTINITEM XSGROUP attributePairList ENDITEM
	  xmlAnnotation xmlChoSeqItem
          STARTOUTITEM XSGROUP ENDITEM
	  {$$ = new XmlElementGroup();
	   doXmlElementGroupAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->item = $6;
	  }
	| STARTINITEM XSGROUP attributePairList ENDITEM
	  xmlChoSeqItem
          STARTOUTITEM XSGROUP ENDITEM
	  {$$ = new XmlElementGroup();
	   doXmlElementGroupAttributes($$, $3);
	   $$->item = $5;
	  }
	;

xmlElementGroupRef :
	  STARTINITEM XSGROUP attributePairList ENDWHOLEITEM
	  {$$ = new XmlElementGroupRef();
	   doXmlElementGroupRefAttributes($$, $3);
	  }
	| STARTINITEM XSGROUP attributePairList ENDITEM
          STARTOUTITEM XSGROUP ENDITEM
	  {$$ = new XmlElementGroupRef();
	   doXmlElementGroupRefAttributes($$, $3);
	  }
	| STARTINITEM XSGROUP attributePairList ENDITEM
	  xmlAnnotation
          STARTOUTITEM XSGROUP ENDITEM
	  {$$ = new XmlElementGroupRef();
	   doXmlElementGroupRefAttributes($$, $3);
	   $$->frontNote = $5;
	  }
	;

xmlElementLocal :
	  STARTINITEM XSELEMENT attributePairList ENDITEM
	  xmlAnnotation
          STARTOUTITEM XSELEMENT ENDITEM
	  {$$ = new XmlElementLocal();
	   doXmlElementLocalAttributes($$, $3);
	   $$->frontNote = $5;
	  }
	| STARTINITEM XSELEMENT attributePairList ENDITEM
	  typeDef
          STARTOUTITEM XSELEMENT ENDITEM
	  {$$ = new XmlElementLocal();
	   doXmlElementLocalAttributes($$, $3);
	   if (($$->ref) || ($$->typ))
	     yyerror("cannot have type definition with type or ref");
	   $$->typeDef = $5;
	  }
	| STARTINITEM XSELEMENT attributePairList ENDITEM
	  xmlAnnotation typeDef
          STARTOUTITEM XSELEMENT ENDITEM
	  {$$ = new XmlElementLocal();
	   doXmlElementLocalAttributes($$, $3);
	   if (($$->ref) || ($$->typ))
	     yyerror("cannot have type definition with type or ref");
	   $$->frontNote = $5;
	   $$->typeDef = $6;
	  }
	| STARTINITEM XSELEMENT attributePairList ENDITEM
          xmlIdConstraintList
          STARTOUTITEM XSELEMENT ENDITEM
	  {$$ = new XmlElementLocal();
	   doXmlElementLocalAttributes($$, $3);
	   $$->idConstraints = $5;
	  }
	| STARTINITEM XSELEMENT attributePairList ENDITEM
	  xmlAnnotation xmlIdConstraintList
          STARTOUTITEM XSELEMENT ENDITEM
	  {$$ = new XmlElementLocal();
	   doXmlElementLocalAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->idConstraints = $6;
	  }
	| STARTINITEM XSELEMENT attributePairList ENDITEM
	  typeDef xmlIdConstraintList
          STARTOUTITEM XSELEMENT ENDITEM
	  {$$ = new XmlElementLocal();
	   doXmlElementLocalAttributes($$, $3);
	   if (($$->ref) || ($$->typ))
	     yyerror("cannot have type definition with type or ref");
	   $$->typeDef = $5;
	   $$->idConstraints = $6;
	  }
	| STARTINITEM XSELEMENT attributePairList ENDITEM
	  xmlAnnotation typeDef xmlIdConstraintList
          STARTOUTITEM XSELEMENT ENDITEM
	  {$$ = new XmlElementLocal();
	   doXmlElementLocalAttributes($$, $3);
	   if (($$->ref) || ($$->typ))
	     yyerror("cannot have type definition with type or ref");
	   $$->frontNote = $5;
	   $$->typeDef = $6;
	   $$->idConstraints = $7;
	  }
	| STARTINITEM XSELEMENT attributePairList ENDITEM
          STARTOUTITEM XSELEMENT ENDITEM
	  {$$ = new XmlElementLocal();
	   doXmlElementLocalAttributes($$, $3);
	  }
	| STARTINITEM XSELEMENT attributePairList ENDWHOLEITEM
	  {$$ = new XmlElementLocal();
	   doXmlElementLocalAttributes($$, $3);
	  }
	;

xmlElementRefable :
	  STARTINITEM XSELEMENT attributePairList ENDITEM
	  xmlAnnotation
          STARTOUTITEM XSELEMENT ENDITEM
	  {$$ = new XmlElementRefable();
	   doXmlElementRefableAttributes($$, $3);
	   $$->frontNote = $5;
	  }
	| STARTINITEM XSELEMENT attributePairList ENDITEM
	  typeDef
          STARTOUTITEM XSELEMENT ENDITEM
	  {$$ = new XmlElementRefable();
	   doXmlElementRefableAttributes($$, $3);
	   $$->typeDef = $5;
	  }
	| STARTINITEM XSELEMENT attributePairList ENDITEM
	  xmlAnnotation typeDef
          STARTOUTITEM XSELEMENT ENDITEM
	  {$$ = new XmlElementRefable();
	   doXmlElementRefableAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->typeDef = $6;
	  }
	| STARTINITEM XSELEMENT attributePairList ENDITEM
	  xmlAnnotation xmlIdConstraintList
          STARTOUTITEM XSELEMENT ENDITEM
	  {$$ = new XmlElementRefable();
	   doXmlElementRefableAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->idConstraints = $6;
	  }
	| STARTINITEM XSELEMENT attributePairList ENDITEM
	  typeDef xmlIdConstraintList
          STARTOUTITEM XSELEMENT ENDITEM
	  {$$ = new XmlElementRefable();
	   doXmlElementRefableAttributes($$, $3);
	   $$->typeDef = $5;
	   $$->idConstraints = $6;
	  }
	| STARTINITEM XSELEMENT attributePairList ENDITEM
	  xmlAnnotation typeDef xmlIdConstraintList
          STARTOUTITEM XSELEMENT ENDITEM
	  {$$ = new XmlElementRefable();
	   doXmlElementRefableAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->typeDef = $6;
	   $$->idConstraints = $7;
	  }
	| STARTINITEM XSELEMENT attributePairList ENDITEM
	  xmlIdConstraintList
          STARTOUTITEM XSELEMENT ENDITEM
	  {$$ = new XmlElementRefable();
	   doXmlElementRefableAttributes($$, $3);
	   $$->idConstraints = $5;
	  }
	| STARTINITEM XSELEMENT attributePairList ENDITEM
          STARTOUTITEM XSELEMENT ENDITEM
	  {$$ = new XmlElementRefable();
	   doXmlElementRefableAttributes($$, $3);
	  }
	| STARTINITEM XSELEMENT attributePairList ENDWHOLEITEM
	  {$$ = new XmlElementRefable();
	   doXmlElementRefableAttributes($$, $3);
	  }
	;

xmlEnumeration :
	  STARTINITEM XSENUMERATION attributePairList ENDWHOLEITEM
	  {$$ = new XmlEnumeration();
	   doXmlEnumerationAttributes($$, $3);
	  }
	| STARTINITEM XSENUMERATION attributePairList ENDITEM
	  STARTOUTITEM XSENUMERATION ENDITEM
	  {$$ = new XmlEnumeration();
	   doXmlEnumerationAttributes($$, $3);
	  }
	| STARTINITEM XSENUMERATION attributePairList ENDITEM
	  xmlAnnotation
	  STARTOUTITEM XSENUMERATION ENDITEM
	  {$$ = new XmlEnumeration();
	   doXmlEnumerationAttributes($$, $3);
	   $$->frontNote = $5;
	  }
	;

xmlField :
	  STARTINITEM XSFIELD attributePairList ENDWHOLEITEM
	  {$$ = new XmlField();
	   doXmlFieldAttributes($$, $3);
	  }
	| STARTINITEM XSFIELD attributePairList ENDITEM
	  STARTOUTITEM XSFIELD ENDITEM
	  {$$ = new XmlField();
	   doXmlFieldAttributes($$, $3);
	  }
	;

xmlFieldList :
	  xmlField
          {$$ = new std::list<XmlField *>;
	   $$->push_back($1);
	  }
	| xmlFieldList xmlField
	  {$$ = $1;
	   $1->push_back($2);
	  }
	;

xmlIdConstraint :
	  xmlKey
          {$$ = $1;}
	| xmlKeyref
          {$$ = $1;}
	| xmlUnique
          {$$ = $1;}
	;

xmlIdConstraintList :
	  xmlIdConstraint
          {$$ = new std::list<XmlIdConstraint *>;
	   $$->push_back($1);
	  }
	| xmlIdConstraintList xmlIdConstraint
	  {$$ = $1;
	   $1->push_back($2);
	  }
	;

xmlImport :
	  STARTINITEM XSIMPORT attributePairList ENDWHOLEITEM
          {$$ = new XmlImport();
	   doXmlImportAttributes($$, $3);
	  }
	| STARTINITEM XSIMPORT attributePairList ENDITEM
          xmlAnnotation STARTOUTITEM XSIMPORT ENDITEM
          {$$ = new XmlImport();
	   doXmlImportAttributes($$, $3);
	   $$->note = $5;
	  }
	;

xmlInclude :
	  STARTINITEM XSINCLUDE schemaLocation ENDWHOLEITEM
          {$$ = new XmlInclude($3, 0);}
	| STARTINITEM XSINCLUDE schemaLocation ENDITEM
          xmlAnnotation STARTOUTITEM XSINCLUDE ENDITEM
	  {$$ = new XmlInclude($3, $5);}
	;

xmlKey :
	  STARTINITEM XSKEY attributePairList ENDITEM
	  xmlSelector xmlFieldList STARTOUTITEM XSKEY ENDITEM
	  {$$ = new XmlKey();
           doXmlKeyAttributes($$, $3);
	   $$->selector = $5;
	   $$->fields = $6;
	  }
	| xmlCommentList
	  STARTINITEM XSKEY attributePairList ENDITEM
	  xmlSelector xmlFieldList STARTOUTITEM XSKEY ENDITEM
	  {$$ = new XmlKey();
           doXmlKeyAttributes($$, $4);
	   $$->comments = $1;
	   $$->selector = $6;
	   $$->fields = $7;
	  }
	| STARTINITEM XSKEY attributePairList ENDITEM
	  xmlAnnotation xmlSelector xmlFieldList STARTOUTITEM XSKEY ENDITEM
	  {$$ = new XmlKey();
           doXmlKeyAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->selector = $6;
	   $$->fields = $7;
	  }
	| xmlCommentList
	  STARTINITEM XSKEY attributePairList ENDITEM
	  xmlAnnotation xmlSelector xmlFieldList STARTOUTITEM XSKEY ENDITEM
	  {$$ = new XmlKey();
           doXmlKeyAttributes($$, $4);
           $$->comments = $1;
	   $$->frontNote = $6;
	   $$->selector = $7;
	   $$->fields = $8;
	  }
	;

xmlKeyref :
	  STARTINITEM XSKEYREF attributePairList ENDITEM
	  xmlSelector xmlFieldList STARTOUTITEM XSKEYREF ENDITEM
	  {$$ = new XmlKeyref();
           doXmlKeyrefAttributes($$, $3);
	   $$->selector = $5;
	   $$->fields = $6;
	  }
	| xmlCommentList
	  STARTINITEM XSKEYREF attributePairList ENDITEM
	  xmlSelector xmlFieldList STARTOUTITEM XSKEYREF ENDITEM
	  {$$ = new XmlKeyref();
           doXmlKeyrefAttributes($$, $4);
	   $$->comments = $1;
	   $$->selector = $6;
	   $$->fields = $7;
	  }
	| STARTINITEM XSKEYREF attributePairList ENDITEM
	  xmlAnnotation xmlSelector xmlFieldList STARTOUTITEM XSKEYREF ENDITEM
	  {$$ = new XmlKeyref();
           doXmlKeyrefAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->selector = $6;
	   $$->fields = $7;
	  }
	| xmlCommentList
	  STARTINITEM XSKEYREF attributePairList ENDITEM
	  xmlAnnotation xmlSelector xmlFieldList STARTOUTITEM XSKEYREF ENDITEM
	  {$$ = new XmlKeyref();
           doXmlKeyrefAttributes($$, $4);
	   $$->comments = $1;
	   $$->frontNote = $6;
	   $$->selector = $7;
	   $$->fields = $8;
	  }
	;

xmlLength :
	  STARTINITEM XSLENGTH attributePairList ENDWHOLEITEM
	  {$$ = new XmlLength();
	   doXmlLengthAttributes($$, $3);
	  }
	| STARTINITEM XSLENGTH attributePairList ENDITEM
	  xmlAnnotation
	  STARTOUTITEM XSLENGTH ENDITEM
	  {$$ = new XmlLength();
	   doXmlLengthAttributes($$, $3);
	   $$->note = $5;
	  }
	;

xmlMaxExclusive :
	  STARTINITEM XSMAXEXCLUSIVE attributePairList ENDWHOLEITEM
	  {$$ = new XmlMaxExclusive();
	   doXmlMaxExclusiveAttributes($$, $3);
	  }
	| STARTINITEM XSMAXEXCLUSIVE attributePairList ENDITEM
	  xmlAnnotation
	  STARTOUTITEM XSMAXEXCLUSIVE ENDITEM
	  {$$ = new XmlMaxExclusive();
	   doXmlMaxExclusiveAttributes($$, $3);
	   $$->note = $5;
	  }
	;

xmlMaxInclusive :
	  STARTINITEM XSMAXINCLUSIVE attributePairList ENDWHOLEITEM
	  {$$ = new XmlMaxInclusive();
	   doXmlMaxInclusiveAttributes($$, $3);
	  }
	| STARTINITEM XSMAXINCLUSIVE attributePairList ENDITEM
	  xmlAnnotation
	  STARTOUTITEM XSMAXINCLUSIVE ENDITEM
	  {$$ = new XmlMaxInclusive();
	   doXmlMaxInclusiveAttributes($$, $3);
	   $$->note = $5;
	  }
	;

xmlMaxLength :
	  STARTINITEM XSMAXLENGTH attributePairList ENDWHOLEITEM
	  {$$ = new XmlMaxLength();
	   doXmlMaxLengthAttributes($$, $3);
	  }
	| STARTINITEM XSMAXLENGTH attributePairList ENDITEM
	  xmlAnnotation
	  STARTOUTITEM XSMAXLENGTH ENDITEM
	  {$$ = new XmlMaxLength();
	   doXmlMaxLengthAttributes($$, $3);
	   $$->note = $5;
	  }
	;

xmlMinExclusive :
	  STARTINITEM XSMINEXCLUSIVE attributePairList ENDWHOLEITEM
	  {$$ = new XmlMinExclusive();
	   doXmlMinExclusiveAttributes($$, $3);
	  }
	| STARTINITEM XSMINEXCLUSIVE attributePairList ENDITEM
	  xmlAnnotation
	  STARTOUTITEM XSMINEXCLUSIVE ENDITEM
	  {$$ = new XmlMinExclusive();
	   doXmlMinExclusiveAttributes($$, $3);
	   $$->note = $5;
	  }

	;

xmlMinInclusive :
	  STARTINITEM XSMININCLUSIVE attributePairList ENDWHOLEITEM
	  {$$ = new XmlMinInclusive();
	   doXmlMinInclusiveAttributes($$, $3);
	  }
	| STARTINITEM XSMININCLUSIVE attributePairList ENDITEM
	  xmlAnnotation
	  STARTOUTITEM XSMININCLUSIVE ENDITEM
	  {$$ = new XmlMinInclusive();
	   doXmlMinInclusiveAttributes($$, $3);
	   $$->note = $5;
	  }
	;

xmlMinLength :
	  STARTINITEM XSMINLENGTH attributePairList ENDWHOLEITEM
	  {$$ = new XmlMinLength();
	   doXmlMinLengthAttributes($$, $3);
	  }
	| STARTINITEM XSMINLENGTH attributePairList ENDITEM
	  xmlAnnotation
	  STARTOUTITEM XSMINLENGTH ENDITEM
	  {$$ = new XmlMinLength();
	   doXmlMinLengthAttributes($$, $3);
	   $$->note = $5;
	  }
	;

xmlNs :
	XMLNSPREFIX TERMINALSTRING
	{$$ = new XmlNsPair($1, $2);}
	;

xmlNsList :
	  xmlNs
          {$$ = new std::list<XmlNsPair *>;
	   $$->push_back($1);
	  }
	| xmlNsList xmlNs
          {$$ = $1;
	   $1->push_back($2);
	  }
	;

xmlOtherContent :
	  xmlOtherContentBase
	  {$$ = new XmlOtherContent($1, 0);}
	| xmlAttributorList
	  {$$ = new XmlOtherContent(0, $1);}
	| xmlOtherContentBase xmlAttributorList
	  {$$ = new XmlOtherContent($1, $2);}
	;

xmlOtherContentBase :
	  xmlChoice {$$ = $1;}
	| xmlSequence {$$ = $1;}
	| xmlElementGroupRef {$$ = $1;}
	;

xmlPattern :
	  STARTINITEM XSPATTERN attributePairList ENDWHOLEITEM
	  {$$ = new XmlPattern();
	   doXmlPatternAttributes($$, $3);
	  }
	| STARTINITEM XSPATTERN attributePairList ENDITEM
	  xmlAnnotation
	  STARTOUTITEM XSPATTERN ENDITEM
	  {$$ = new XmlPattern();
	   doXmlPatternAttributes($$, $3);
	   $$->frontNote = $5;
	  }
	;

xmlRestrictionType :
	  xmlLength {$$ = $1;}
	| xmlMaxExclusive {$$ = $1;}
	| xmlMaxInclusive {$$ = $1;}
	| xmlMaxLength {$$ = $1;}
	| xmlMinExclusive {$$ = $1;}
	| xmlMinInclusive {$$ = $1;}
	| xmlMinLength {$$ = $1;}
	| xmlPattern {$$ = $1;}
	| xmlEnumeration {$$ = $1;}
	;

xmlRestrictionTypeList :
	  xmlRestrictionType
          {$$ = new std::list<XmlRestrictionType *>;
	   $$->push_back($1);
	  }
	| xmlRestrictionTypeList xmlRestrictionType
	  {$$ = $1;
	   $1->push_back($2);
	  }
	;

xmlSchema :
	  xmlSchemaHeader xmlSchemaContent1List xmlSchemaContent2List
          STARTOUTITEM XSSCHEMA ENDITEM
          {$$ = new XmlSchema($1, $2, $3);}
	;

xmlSchemaContent1 :
	  xmlInclude {$$ = $1;}
	| xmlImport {$$ = $1;}
	| xmlAnnotation {$$ = $1;}
	;

xmlSchemaContent1List :
	  /* empty */
          {$$ = new std::list<XmlSchemaContent1 *>;
	    //$$->push_back($1);
	  }
	| xmlSchemaContent1List xmlSchemaContent1
	  {$$ = $1;
	   $1->push_back($2);
	  }
	;

xmlSchemaContent2 :
          xmlElementGroup {$$ = $1;}
	| xmlElementGroup xmlAnnotationList
	  {$$ = $1;
	   $$->backNotes = $2;
	  }
	| xmlElementRefable {$$ = $1;}
	| xmlElementRefable xmlAnnotationList
	  {$$ = $1;
	   $$->backNotes = $2;
	  }
	| xmlComplexType {$$ = $1;}
	| xmlComplexType xmlAnnotationList
	  {$$ = $1;
	   $$->backNotes = $2;
	  }
	| xmlSimpleType {$$ = $1;}
	| xmlSimpleType xmlAnnotationList
	  {$$ = $1;
	   $$->backNotes = $2;
	  }
	| xmlAttributeLonerRefable {$$ = $1;}
	| xmlAttributeLonerRefable xmlAnnotationList
	  {$$ = $1;
	   $$->backNotes = $2;
	  }
	| xmlAttributeGroupRefable {$$ = $1;}
	| xmlAttributeGroupRefable xmlAnnotationList
	  {$$ = $1;
	   $$->backNotes = $2;
	  }
	;

xmlSchemaContent2List :
	  xmlSchemaContent2
          {$$ = new std::list<XmlSchemaContent2 *>;
	   $$->push_back($1);
	  }
	| xmlSchemaContent2List xmlSchemaContent2
	  {$$ = $1;
	   $1->push_back($2);
	  }
	;

xmlSchemaHeader :
	  STARTINITEM XSSCHEMA xmlNsList attributePairList ENDITEM
          { // note that this removes the first element from the xmlNsList
	    checkXmlns($3);
	    $$ = new XmlSchemaHeader(0, 0, $3->front(), $3,
				     XmlCppBase::formNone,
				     XmlCppBase::formNone, 0);
	    doXmlSchemaHeaderAttributes($$, $4);
	    $3->pop_front();
	  }
	;

xmlSelector :
	  STARTINITEM XSSELECTOR attributePairList ENDWHOLEITEM
	  {$$ = new XmlSelector();
	   doXmlSelectorAttributes($$, $3);
	  }
	| STARTINITEM XSSELECTOR attributePairList ENDITEM
	  STARTOUTITEM XSSELECTOR ENDITEM
	  {$$ = new XmlSelector();
	   doXmlSelectorAttributes($$, $3);
	  }
	;

xmlSequence :
	  STARTINITEM XSSEQUENCE attributePairList ENDITEM
	  xmlChoSeqItemList
	  STARTOUTITEM XSSEQUENCE ENDITEM
	  {$$ = new XmlSequence();
	   doXmlSequenceAttributes($$, $3);
	   $$->items = $5;
	  }
	| STARTINITEM XSSEQUENCE attributePairList ENDITEM
	  xmlAnnotation xmlChoSeqItemList
	  STARTOUTITEM XSSEQUENCE ENDITEM
	  {$$ = new XmlSequence();
	   doXmlSequenceAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->items = $6;
	  }
	;

xmlSimpleContent :
	  STARTINITEM XSSIMPLECONTENT attributePairList ENDWHOLEITEM
	  {$$ = new XmlSimpleContent();
	   doXmlSimpleContentAttributes($$, $3);
	  }
	| STARTINITEM XSSIMPLECONTENT attributePairList ENDITEM
	  STARTOUTITEM XSSIMPLECONTENT ENDITEM
	  {$$ = new XmlSimpleContent();
	   doXmlSimpleContentAttributes($$, $3);
	  }
	| STARTINITEM XSSIMPLECONTENT attributePairList ENDITEM
          xmlAnnotation
	  STARTOUTITEM XSSIMPLECONTENT ENDITEM
	  {$$ = new XmlSimpleContent();
	   doXmlSimpleContentAttributes($$, $3);
	   $$->frontNote = $5;
	  }
	| STARTINITEM XSSIMPLECONTENT attributePairList ENDITEM
          xmlSimpleContentItem
	  STARTOUTITEM XSSIMPLECONTENT ENDITEM
	  {$$ = new XmlSimpleContent();
	   doXmlSimpleContentAttributes($$, $3);
	   $$->item = $5;
	  }
	| STARTINITEM XSSIMPLECONTENT attributePairList ENDITEM
          xmlAnnotation xmlSimpleContentItem
	  STARTOUTITEM XSSIMPLECONTENT ENDITEM
	  {$$ = new XmlSimpleContent();
	   doXmlSimpleContentAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->item = $6;
	  }
	;

xmlSimpleContentItem :
	  xmlSimpleContentExtension  {$$ = $1;}
	| xmlSimpleContentRestriction  {$$ = $1;}
	;

xmlSimpleContentExtension :
	  STARTINITEM XSEXTENSION attributePairList ENDWHOLEITEM
	  {$$ = new XmlSimpleContentExtension();
	   doXmlSimpleContentExtensionAttributes($$, $3);
	  }
	| STARTINITEM XSEXTENSION attributePairList ENDITEM
	  STARTOUTITEM XSEXTENSION ENDITEM
	  {$$ = new XmlSimpleContentExtension();
	   doXmlSimpleContentExtensionAttributes($$, $3);
	  }
	| STARTINITEM XSEXTENSION attributePairList ENDITEM
          xmlAnnotation
	  STARTOUTITEM XSEXTENSION ENDITEM
	  {$$ = new XmlSimpleContentExtension();
	   doXmlSimpleContentExtensionAttributes($$, $3);
	   $$->frontNote = $5;
	  }
	| STARTINITEM XSEXTENSION attributePairList ENDITEM
          xmlAttributorList
	  STARTOUTITEM XSEXTENSION ENDITEM
	  {$$ = new XmlSimpleContentExtension();
	   doXmlSimpleContentExtensionAttributes($$, $3);
	   $$->attribs = $5;
	  }
	| STARTINITEM XSEXTENSION attributePairList ENDITEM
          xmlAnnotation xmlAttributorList
	  STARTOUTITEM XSEXTENSION ENDITEM
	  {$$ = new XmlSimpleContentExtension();
	   doXmlSimpleContentExtensionAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->attribs = $6;
	  }
	;

xmlSimpleContentRestriction :
	  STARTINITEM XSRESTRICTION attributePairList ENDWHOLEITEM
	  {$$ = new XmlSimpleContentRestriction();
	   doXmlSimpleContentRestrictionAttributes($$, $3);
	   $$->restrictions = new std::list<XmlRestrictionType *>;
	  }
	| STARTINITEM XSRESTRICTION attributePairList ENDITEM
	  STARTOUTITEM XSRESTRICTION ENDITEM
	  {$$ = new XmlSimpleContentRestriction();
	   doXmlSimpleContentRestrictionAttributes($$, $3);
	   $$->restrictions = new std::list<XmlRestrictionType *>;
	  }
	| STARTINITEM XSRESTRICTION attributePairList ENDITEM
          xmlAnnotation
	  STARTOUTITEM XSRESTRICTION ENDITEM
	  {$$ = new XmlSimpleContentRestriction();
	   doXmlSimpleContentRestrictionAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->restrictions = new std::list<XmlRestrictionType *>;
	  }
	| STARTINITEM XSRESTRICTION attributePairList ENDITEM
	  xmlRestrictionTypeList
	  STARTOUTITEM XSRESTRICTION ENDITEM
	  {$$ = new XmlSimpleContentRestriction();
	   doXmlSimpleContentRestrictionAttributes($$, $3);
	   $$->restrictions = $5;
	  }
	| STARTINITEM XSRESTRICTION attributePairList ENDITEM
	  xmlAttributorList
	  STARTOUTITEM XSRESTRICTION ENDITEM
	  {$$ = new XmlSimpleContentRestriction();
	   doXmlSimpleContentRestrictionAttributes($$, $3);
	   $$->attribs = $5;
	   $$->restrictions = new std::list<XmlRestrictionType *>;
	  }
	| STARTINITEM XSRESTRICTION attributePairList ENDITEM
	  xmlAnnotation xmlRestrictionTypeList
	  STARTOUTITEM XSRESTRICTION ENDITEM
	  {$$ = new XmlSimpleContentRestriction();
	   doXmlSimpleContentRestrictionAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->restrictions = $6;
	  }
	| STARTINITEM XSRESTRICTION attributePairList ENDITEM
	  xmlAnnotation xmlAttributorList
	  STARTOUTITEM XSRESTRICTION ENDITEM
	  {$$ = new XmlSimpleContentRestriction();
	   doXmlSimpleContentRestrictionAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->attribs = $6;
	  }
	| STARTINITEM XSRESTRICTION attributePairList ENDITEM
	  xmlRestrictionTypeList xmlAttributorList
	  STARTOUTITEM XSRESTRICTION ENDITEM
	  {$$ = new XmlSimpleContentRestriction();
	   doXmlSimpleContentRestrictionAttributes($$, $3);
	   $$->restrictions = $5;
	   $$->attribs = $6;
	  }
	| STARTINITEM XSRESTRICTION attributePairList ENDITEM
	  xmlAnnotation xmlRestrictionTypeList xmlAttributorList
	  STARTOUTITEM XSRESTRICTION ENDITEM
	  {$$ = new XmlSimpleContentRestriction();
	   doXmlSimpleContentRestrictionAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->restrictions = $6;
	   $$->attribs = $7;
	  }
	;

xmlSimpleItem :
	  xmlSimpleList {$$ = $1;}
	| xmlSimpleRestriction {$$ = $1;}
	;

xmlSimpleList :
	  STARTINITEM XSLIST attributePairList ENDWHOLEITEM
	  {$$ = new XmlSimpleList();
	   doXmlSimpleListAttributes($$, $3);
	  }
	| STARTINITEM XSLIST attributePairList ENDITEM
	  STARTOUTITEM XSLIST ENDITEM
	  {$$ = new XmlSimpleList();
	   doXmlSimpleListAttributes($$, $3);
	  }
	;

xmlSimpleRestriction :
	  STARTINITEM XSRESTRICTION attributePairList ENDWHOLEITEM
	  {$$ = new XmlSimpleRestriction();
	   doXmlSimpleRestrictionAttributes($$, $3);
	   $$->restrictions = new std::list<XmlRestrictionType *>;
	  }
	| STARTINITEM XSRESTRICTION attributePairList ENDITEM
	  STARTOUTITEM XSRESTRICTION ENDITEM
	  {$$ = new XmlSimpleRestriction();
	   doXmlSimpleRestrictionAttributes($$, $3);
	   $$->restrictions = new std::list<XmlRestrictionType *>;
	  }
	| STARTINITEM XSRESTRICTION attributePairList ENDITEM
	  xmlRestrictionTypeList
	  STARTOUTITEM XSRESTRICTION ENDITEM
	  {$$ = new XmlSimpleRestriction();
	   doXmlSimpleRestrictionAttributes($$, $3);
	   $$->restrictions = $5;
	  }
	;

xmlSimpleType :
	  STARTINITEM XSSIMPLETYPE attributePairList ENDITEM
	  xmlSimpleItem
	  STARTOUTITEM XSSIMPLETYPE ENDITEM
	  {$$ = new XmlSimpleType();
	   doXmlSimpleTypeAttributes($$, $3);
	   $$->item = $5;
	  }
	| STARTINITEM XSSIMPLETYPE attributePairList ENDITEM
	  xmlAnnotation xmlSimpleItem
	  STARTOUTITEM XSSIMPLETYPE ENDITEM
	  {$$ = new XmlSimpleType();
	   doXmlSimpleTypeAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->item = $6;
	  }
	;

xmlUnique :
	  STARTINITEM XSUNIQUE attributePairList ENDITEM
	  xmlSelector xmlFieldList STARTOUTITEM XSUNIQUE ENDITEM
	  {$$ = new XmlUnique();
           doXmlUniqueAttributes($$, $3);
	   $$->selector = $5;
	   $$->fields = $6;
	  }
	| xmlCommentList
	  STARTINITEM XSUNIQUE attributePairList ENDITEM
	  xmlSelector xmlFieldList STARTOUTITEM XSUNIQUE ENDITEM
	  {$$ = new XmlUnique();
           doXmlUniqueAttributes($$, $4);
	   $$->comments = $1;
	   $$->selector = $6;
	   $$->fields = $7;
	  }
	| STARTINITEM XSUNIQUE attributePairList ENDITEM
	  xmlAnnotation xmlSelector xmlFieldList STARTOUTITEM XSUNIQUE ENDITEM
	  {$$ = new XmlUnique();
           doXmlUniqueAttributes($$, $3);
	   $$->frontNote = $5;
	   $$->selector = $6;
	   $$->fields = $7;
	  }
	| xmlCommentList
	  STARTINITEM XSUNIQUE attributePairList ENDITEM
	  xmlAnnotation xmlSelector xmlFieldList STARTOUTITEM XSUNIQUE ENDITEM
	  {$$ = new XmlUnique();
           doXmlUniqueAttributes($$, $4);
           $$->comments = $1;
	   $$->frontNote = $6;
	   $$->selector = $7;
	   $$->fields = $8;
	  }
	;

xmlVersion :
	  STARTVERSION XMLVERSION TERMINALSTRING ENDVERSION
	  {$$ = new XmlVersion(false);
	   if (strcmp($3, "1.0"))
	     yyerror("version number must be 1.0");
	  }
	| STARTVERSION XMLVERSION TERMINALSTRING
	  ENCODING TERMINALSTRING ENDVERSION
	  {$$ = new XmlVersion(true);
	   if (strcmp($3, "1.0"))
	     yyerror("version number must be 1.0");
	   else if ((strcmp($5, "UTF-8")) && (strcmp($5, "utf-8")))
	     yyerror("encoding must be UTF-8");
	  }
	;

%%

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

