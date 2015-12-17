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

#ifndef YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_XML_COMMON_SRC_XMLSCHEMAYACC_HH_INCLUDED
# define YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_XML_COMMON_SRC_XMLSCHEMAYACC_HH_INCLUDED
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

#endif /* !YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_XML_COMMON_SRC_XMLSCHEMAYACC_HH_INCLUDED  */
