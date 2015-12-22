/*

This uses the prefix Xml for all class names in order to avoid complications
with C++ reserved words. This may make it possible to skip using a namespace
for this software, or it may be desirable to have a namespace as well as
the prefix.

These classes are intended to implement a subset of XML schema as
described in the spec http://www.w3.org/TR/xmlschema-1.
Items that are not implemented are included here in comments or in
commented out code.

In some cases where a list is optional in XML, these C++ classes
require having a list, but the list may be empty. It was not feasible to
do this in all cases since empty lists in YACC led to parser conflicts.

This is currently representing as (char *) anything that is printed in
an XML schema with double quotes around it. It may be useful to switch
to either a std:string or multiple subtypes.

All of the productions in the documentation of classes are from 
http://www.w3.org/TR/xmlschema-1. Those productions originally
included {any attributes with non-schema namespace . . .}. That line has
been deleted from all the productions cited here. Attributes with
non-schema namespace (whatever that is) are not allowed in these classes.

Currently, no "block" is implemented.

The XmlAnnotation,  XmlKey, and XmlKeyref classes include comments in order
to allow comments to be saved and reprinted easily.

*/

#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <list>

class XmlAnnotation;
class XmlAnnoType;
class XmlAny;
class XmlAppinfo;
class XmlAttributor;
class XmlAttribPair;
class XmlAttributeLoner;
class XmlAttributeLonerRefable;
class XmlAttributeGroupRef;
class XmlAttributeGroupRefable;
class XmlChoice;
class XmlChoSeqItem;
class XmlComplexContent;
class XmlComplexContentItem;
class XmlComplexExtension;
class XmlComplexRestriction;
class XmlComplexType;
class XmlComplexTypeItem;
class XmlCppBase;
class XmlDocumentation;
class XmlElementGroup;
class XmlElementGroupRef;
class XmlElementLocal;
class XmlElementRefable;
class XmlEnumeration;
class XmlField;
class XmlFinal;
class XmlIdConstraint;
class XmlImport;
class XmlInclude;
class XmlKey;
class XmlKeyref;
class XmlLength;
class XmlMaxExclusive;
class XmlMaxInclusive;
class XmlMaxLength;
class XmlMinExclusive;
class XmlMinInclusive;
class XmlMinLength;
class XmlNsPair;
class XmlOtherContent;
class XmlOtherContentBase;
class XmlPattern;
class XmlRestrictionType;
class XmlSchema;
class XmlSchemaContent1;
class XmlSchemaContent2;
class XmlSchemaFile;
class XmlSchemaHeader;
class XmlSelector;
class XmlSequence;
class XmlSimpleContent;
class XmlSimpleContentItem;
class XmlSimpleContentExtension;
class XmlSimpleItem;
class XmlSimpleList;
class XmlSimpleRestriction;
class XmlSimpleContentRestriction;
class XmlSimpleType;
class XmlType;
class XmlVersion;

/********************************************************************/
/********************************************************************/
/* This section 1 defines XmlCppBase and XmlAttribPair              */

/* class XmlCppBase

This is the base class from which almost everything else is derived.

The enum logicalE is used where XML has booleans. A logicalNone value
indicates no value was given.

For formE, formNone indicates no value was given.

For useE, useNone indicates no value was given. The default is optional.

Making all the printXxx functions be member functions of XmlCppBase is
probably immoral, since (except for printSelf) not all subtypes of
XmlCppBase have a reason to call them. However, it is very convenient,
and, while it constitutes temptation to sin, is not sin itself.

This has space for the wg3Prefix (usually xs or xsd) and one
otherPrefix. In principle, a schema may have several other prefixes,
so this is a bit of a limitation, but dealing with an entire list
of other prefixes would be a big nuisance.

The prefixes are saved without the colon.

*/

class XmlCppBase
{
public:
  enum formE {formNone=0, qualified, unqualified};
  enum logicalE {logicalNone=0, yes, no};
  enum useE {useNone = 0, optional, prohibited, required};

  XmlCppBase();
  virtual ~XmlCppBase();
  virtual void printSelf(FILE * outFile) = 0;
  static char * modifyName(char * name);
  static void printBool(logicalE boolVal,
			const char * attrib, FILE * outFile);
  static void printMaxOccurs(int maxOccurs, FILE * outFile);
  static void printMinOccurs(int minOccurs, FILE * outFile);
  static void doSpaces(int change, FILE * outFile);

  static char * wg3Prefix;
  static char * otherPrefix;
  static int spaces;
};

/********************************************************************/

/* class XmlAttribPair

The XmlAttribPair class is a utility class used in the xmlSchemaParser.
It is not needed for representing an XML schema. During parsing,
XmlAttribPairs are built, but then the values in them are checked
and assigned to class values. The YACC file xmlSchema.y has a large
number of doXml...Attributes functions that do that.

An XmlAttribPair represents an attribute name and an attribute value.
The value is always enclosed in quotes and may or may not have a prefix.
The prefix is saved separately as the value of pref so that it may be
compared easily with known prefixes. It is saved without the colon.
If there is no prefix, the value of pref is 0.

Example 1: Schema contains 'type="xs:string"'.
name = TYP (an int constant defined in xmlSchema.y representing "type")
pref = "xs"
value = "string"

Example 2: Schema contains 'name="PhoneExtType"'
name = NAME (an int constant defined in xmlSchema.y representing "name")
pref = 0
value = "PhoneExtType"

*/

class XmlAttribPair
{
public:
  XmlAttribPair();
  XmlAttribPair(
    int nameIn,
    char * prefIn,
    char * valIn);
  ~XmlAttribPair();

  int name;
  char * pref;
  char * val;
};

/********************************************************************/
/********************************************************************/
/* This section 2 contains children of XmlCppBase, alphabetically.  */

/* class XmlAnnoType

This is the parent class for:

XmlDocumentation - implemented
XmlAppinfo       - implemented

Those are the two types of content in an Annotation.

*/

class XmlAnnoType :
  public XmlCppBase
{
public:
  XmlAnnoType();
  ~XmlAnnoType();
  void printSelf(FILE * outFile) = 0;
};

/********************************************************************/

/* class XmlAttributor

This is the parent class for:

XmlAttributeLoner - implemented
XmlAttributeGroupRef - implemented

This is needed in XmlOtherContent in order to make a mixed list of the
child types.

*/

class XmlAttributor :
  public XmlCppBase
{
public:
  XmlAttributor();
  ~XmlAttributor();
  void printSelf(FILE * outFile) = 0;
};

/********************************************************************/

/* class XmlChoSeqItem

This is the parent class for:

XmlAny             - implemented
XmlChoice          - implemented
XmlElementLocal    - implemented
XmlElementGroupRef - implemented
XmlSequence        - implemented

Those may be used in an XmlChoice or an XmlSequence.

*/

class XmlChoSeqItem :
  public XmlCppBase
{
public:
  XmlChoSeqItem();
  ~XmlChoSeqItem();
  virtual void printSelf(FILE * outFile) = 0;
};

/********************************************************************/

/* XmlComplexContentItem

This is a parent class for:
XmlComplexExtension - implemented
XmlComplexRestriction - implemented

It is used in the XmlComplexContent class.

*/

class XmlComplexContentItem :
  public XmlCppBase
{
public:
  XmlComplexContentItem();
  ~XmlComplexContentItem();
  void printSelf(FILE * outFile) = 0;
};


/********************************************************************/

/* class XmlComplexTypeItem

(simpleContent | complexContent |
             ((group | all | choice | sequence)?,
              ((attribute | attributeGroup)*, anyAttribute?)))

This is the parent class for:

XmlComplexContent - implemented
XmlOtherContent   - implemented
XmlSimpleContent  - implemented

It is used in the XmlComplexType class.

*/

class XmlComplexTypeItem :
  public XmlCppBase
{
public:
  XmlComplexTypeItem();
  ~XmlComplexTypeItem();
  void printSelf(FILE * outFile) = 0;
};

/********************************************************************/

/* class XmlField

The contents of a field are currently stubbed out as a string.

*/

class XmlField :
  public XmlCppBase
{
public:
  XmlField();
  XmlField(char * xpathIn);
  ~XmlField();
  void printSelf(FILE * outFile);

  char * xpath;
};

/********************************************************************/

/* class XmlFinal

If all is true, the other three attributes should be false, and if any
of the other three is true, all should be false.

The spelling younion is used since union is a C++ reserved word. Also all
is used rather than #all since # is a C++ preprocessor directive that is
not allowed in names.

*/

class XmlFinal :
  public XmlCppBase
{
public:
  XmlFinal();
  ~XmlFinal();
  void printSelf(FILE * outFile);

  bool all;
  bool list;
  bool younion;
  bool restriction;
};

/********************************************************************/

/* class XmlIdConstraint

This is the parent class for XmlUnique, XmlKey, and XmlKeyref

*/

class XmlIdConstraint :
  public XmlCppBase
{
public:
  XmlIdConstraint();
  ~XmlIdConstraint();
  virtual void printSelf(FILE * outFile) = 0;
};

/********************************************************************/

/* class XmlNsPair

The prefix is optional. If a schema header has more than one xmlns,
all but one must have a prefix.

*/

class XmlNsPair :
  public XmlCppBase
{
public:
  XmlNsPair();
  XmlNsPair(
    char * prefixIn,
    char * locationIn);
 ~XmlNsPair();
  void printSelf(FILE * outFile);

  char * prefix;
  char * location;
};

/********************************************************************/

/* class XmlOtherContentBase

(group | all | choice | sequence)

This is the parent class for:

XmlAll             - not implemented
XmlChoice          - implemented
XmlElementGroupRef - implemented
XmlSequence        - implemented

*/

class XmlOtherContentBase :
  public XmlCppBase
{
public:
  XmlOtherContentBase();
  ~XmlOtherContentBase();
  void printSelf(FILE * outFile) = 0;
};

/********************************************************************/

/* class XmlRestrictionType

This is the parent class for:

XmlEnumeration     - implemented
XmlFractionDigits  - not implemented
XmlLength          - implemented
XmlMaxExclusive    - implemented
XmlMaxInclusive    - implemented
XmlMaxLength       - implemented
XmlMinExclusive    - implemented
XmlMinInclusive    - implemented
XmlMinLength       - implemented
XmlPattern         - implemented
XmlTotalDigits     - not implemented
XmlWhiteSpace      - not implemented

*/

class XmlRestrictionType :
  public XmlCppBase
{
public:
  XmlRestrictionType();
  ~XmlRestrictionType();
  void printSelf(FILE * outFile) = 0;
};

/********************************************************************/

/* class XmlSchema

<schema
  attributeFormDefault = (qualified | unqualified) : unqualified
  blockDefault = (#all | List of (extension | restriction | substitution))  : ''
  elementFormDefault = (qualified | unqualified) : unqualified
  finalDefault = (#all | List of (extension | restriction | list | union))  : ''
  id = ID
  targetNamespace = anyURI
  version = token
  xml:lang = language
  Content: ((include | import | redefine | annotation)*,
            (((simpleType | complexType | group | attributeGroup)
           | element | attribute | notation), annotation*)*)
</schema>

In the last two lines of Content, it is not clear why there are
parentheses around the first four items, since they have no effect.

An {any attributes with non-schema namespace . . .} that was included
in the production above has been removed. That item is not allowed
here or anywhere else in these classes. It is not clear why they are
allowed in XML schema.

The effect of the definition of Content above is that (1) Content starts
with any number of include, import, and redefine, (2) Content ends
with any number of simpleType, complexType, group, attributeGroup,
element, attribute, and notation, and (3) annotations can be put anywhere
between items. The construction above (which appears to allow annotations
in the second half of the content only after an instance of one of the
other second half items) was probably made to avoid the problem that
if annotations were allowed anywhere in the second half it would not
be possible to tell where the annotations at the end of the first half
end and where the annotations starting the second half begin. That would
be a problem for parsers, which are incapable of a "who cares" attitude.

In the XmlSchema class defined here:
1. header (an XmlSchemaHeader) includes everything before Content in
   the production above.
2. contents1 (a list of XmlContent1) is everything covered by
   (include | import | redefine | annotation)*
3. contents2 (a list of XmlContent2) is everything covered by
   (((simpleType | complexType | group | attributeGroup)
                | element | attribute | notation), annotation*)*

*/

class XmlSchema :
  public XmlCppBase
{
public:
  XmlSchema();
  XmlSchema(
    XmlSchemaHeader * headerIn,
    std::list<XmlSchemaContent1 *> * contents1In,
    std::list<XmlSchemaContent2 *> * contents2In);
  ~XmlSchema();
  void printSelf(FILE * outFile);

  XmlSchemaHeader * header;
  std::list<XmlSchemaContent1 *> * contents1;
  std::list<XmlSchemaContent2 *> * contents2;
};

/********************************************************************/

/* class XmlSchemaContent1

This is the parent class for:

XmlAnnotation  - implemented
XmlImport      - implemented
XmlInclude     - implemented
XmlRedefine    - not implemented

(include | import | redefine | annotation)

*/

class XmlSchemaContent1 :
  public XmlCppBase
{
public:
  XmlSchemaContent1();
  ~XmlSchemaContent1();
  void printSelf(FILE * outFile) = 0;
};

/********************************************************************/

/* class XmlSchemaContent2

This is the parent class for:

XmlAttributeGroupRefable - implemented
XmlAttributeLonerRefable - implemented
XmlComplexType           - implemented
XmlElementRefable        - implemented
XmlSimpleType            - implemented
XmlElementGroup          - implemented
notation                 - not implemented

Each of those may be followed by zero to many annotations (backNotes).

There is no constructor here that takes a list of XmlAnnotations for
the backNotes. The subtype constructors include the backNotes.

((simpleType | complexType | group | attributeGroup |
  element    | attribute   | notation), annotation*)

*/

class XmlSchemaContent2 :
  public XmlCppBase
{
public:
  XmlSchemaContent2();
  ~XmlSchemaContent2();
  void printSelf(FILE * outFile) = 0;

  std::list<XmlAnnotation *> * backNotes;
};

/********************************************************************/

/* class XmlSchemaFile

This is the top level item for an XML schema.

The version is inherited from the XML file spec.

For printDoc, asIs = 0, indent = 1, left = 2

The printDoc and printComments attributes are static so that the printSelf
functions for XmlDocumentation and XmlComment can get their values easily.

The comments attribute is for comments between the version and the rest of
the schema file. The XmlSchema may also contain comments.

*/

class XmlSchemaFile :
  public XmlCppBase
{
public:
  XmlSchemaFile();
  XmlSchemaFile(
    XmlVersion * versionIn,
    std::list<std::string *> * commentsIn,
    XmlSchema * schemaIn);
 ~XmlSchemaFile();
  void printSelf(FILE * outFile);

  XmlVersion * version;
  std::list<std::string *> * comments;
  XmlSchema * schema;
  static int printDoc;
  static bool printComments;
};

/********************************************************************/

/* class XmlSchemaHeader

The schemaSchemaIn and otherSchemasIn attributes are not listed as schema
components in http://www.w3.org/TR/xmlschema-1/#components, but all
the examples in XMLSpy or produced by XMLSpy include them.

The "version" here is the version of the schema, not the version of XML.

*/


class XmlSchemaHeader :
  public XmlCppBase
{
public:
  XmlSchemaHeader();
  XmlSchemaHeader(
    char * versionIn,
    char * idIn,
    XmlNsPair * schemaSchemaIn,
    std::list<XmlNsPair *> * otherSchemasIn,
    formE attributeFormDefaultIn,
    formE elementFormDefaultIn,
    char * targetNamespaceIn);
  ~XmlSchemaHeader();
  void printSelf(FILE * outFile);

  //blockDefault = (#all | List of (extension|restriction|substitution))  : ''
  //finalDefault = (#all | List of (extension|restriction|list | union))  : ''
  char * version;
  //xml:lang = language
  char * id;
  XmlNsPair * schemaSchema;
  std::list<XmlNsPair *> * otherSchemas;
  formE attributeFormDefault;
  formE elementFormDefault;
  char * targetNamespace;
};

/********************************************************************/

/* class XmlSelector

The contents of a selector are currently stubbed out as a string.

*/

class XmlSelector :
  public XmlCppBase
{
public:
  XmlSelector();
  XmlSelector(char * xpathIn);
  ~XmlSelector();
  void printSelf(FILE * outFile);

  char * xpath;
};

/********************************************************************/

/* class XmlSimpleContentItem

This is a parent class for:

XmlSimpleContentRestriction - implemented
XmlSimpleContentExtension - implemented

*/

class XmlSimpleContentItem :
  public XmlCppBase
{
public:
  XmlSimpleContentItem();
  ~XmlSimpleContentItem();
  void printSelf(FILE * outFile) = 0;
};

/********************************************************************/

/* class XmlSimpleItem

This is a parent class for:

XmlList              - implemented
XmlSimpleRestriction - implemented
XmlUnion             - not implemented

*/

class XmlSimpleItem :
  public XmlCppBase
{
public:
  XmlSimpleItem();
  ~XmlSimpleItem();
  void printSelf(FILE * outFile) = 0;
};

/********************************************************************/

/* class XmlType

This is the parent class for:

XmlComplexType - implemented
XmlSimpleType  - implemented

Note that putting the name and newName attributes of the child classes
XmlComplexType and XmlSimpleType into XmlType causes failure of
dynamic_cast XmlSchemaContent2, which is a second parent type of those
two classes. So don't try it again.

*/

class XmlType :
  public XmlCppBase
{
public:
  XmlType();
  ~XmlType();
  void printSelf(FILE * outFile) = 0;
};

/********************************************************************/

/* class XmlVersion

The value of version must be "1.0", and the value of encoding (which
is optional) must be "UTF-8" if it is used. The hasEncoding attribute
only tells whether an encoding is given.

*/

class XmlVersion :
  public XmlCppBase
{
public:
  XmlVersion();
  XmlVersion(bool hasEncodingIn);
  ~XmlVersion();
  void printSelf(FILE * outFile);

  bool hasEncoding;
};

/********************************************************************/
/********************************************************************/
/* This section 3 contains classes that are derived from classes in */
/* section 2. They are arranged alphabetically.                     */

/* class XmlAnnotation

The comment attribute is included to make it possible to save and
reprint comments.

*/

class XmlAnnotation :
  public XmlSchemaContent1
{
public:
  XmlAnnotation();
  XmlAnnotation(
    char * idIn,
    std::string * commentIn,
    std::list<XmlAnnoType *> * contentIn);
  ~XmlAnnotation();
  void printSelf(FILE * outFile);

  char * id;
  std::string * comment;
  std::list<XmlAnnoType *> * content; 
};

/********************************************************************/

/* class XmlAny

<any
  id = ID
  maxOccurs = (nonNegativeInteger | unbounded)  : 1
  minOccurs = nonNegativeInteger : 1
  namespace = ((##any | ##other) |
               List of (anyURI | (##targetNamespace | ##local))
              ) 
  processContents = (lax | skip | strict)
  Content: (annotation?)
</any>

Only one namespace occurrence is implemented. It may be ##any, ##other,
##targetNamespace, or ##local.

*/

class XmlAny :
  public XmlChoSeqItem
{
public:
  enum namespaceE {namespaceNone=0,
                   hashAny, hashOther, hashTarget, hashLocal};
  enum processContentsE {processContentsNone=0, lax, skip, strict};
  XmlAny();
  XmlAny(
    char * idIn,
    int maxOccursIn,
    int minOccursIn,
    namespaceE namespaceValIn,
    processContentsE processContentsValIn,
    XmlAnnotation * frontNoteIn);
  ~XmlAny();
  void printSelf(FILE * outFile);

  char * id;
  int maxOccurs;  // default is 1
  int minOccurs;  // default is 1
  namespaceE namespaceVal;
  processContentsE processContentsVal;

  XmlAnnotation * frontNote;
};


/********************************************************************/

/* class XmlAppinfo

The text is a string that does not contain <.

*/

class XmlAppinfo :
  public XmlAnnoType
{
public:
  XmlAppinfo();
  XmlAppinfo(
    char * sourceIn,
    char * textIn);
  ~XmlAppinfo();
  void printSelf(FILE * outFile);

  char * source;
  char * text;
};

/********************************************************************/

/* class XmlAttributeGroupRef

The frontNote and xmlAttributes are content. The other C++ attributes
are XML attributes.

*/

class XmlAttributeGroupRef :
  public XmlAttributor
{
public:
  XmlAttributeGroupRef();
  XmlAttributeGroupRef(
    char * idIn,
    char * refIn,
    XmlAnnotation * frontNoteIn);
  ~XmlAttributeGroupRef();
  void printSelf(FILE * outFile);

  char * id;
  char * ref;

  XmlAnnotation * frontNote;
};

/********************************************************************/

/* class XmlAttributeGroupRefable

The frontNote and xmlAttributes are content. The other C++ attributes
are XML attributes.

backNotes is inherited from XmlSchemaContent2.

*/

class XmlAttributeGroupRefable :
  public XmlAttributor,
  public XmlSchemaContent2
{
public:
  XmlAttributeGroupRefable();
  XmlAttributeGroupRefable(
    char * idIn,
    char * nameIn,
    XmlAnnotation * frontNoteIn,
    std::list<XmlAnnotation *> * backNotesIn,
    std::list<XmlAttributor *> * xmlAttributesIn);
  ~XmlAttributeGroupRefable();
  void printSelf(FILE * outFile);

  char * id;
  char * name;

  XmlAnnotation * frontNote;
  std::list<XmlAttributor *> * xmlAttributes;

  char * newName;
  std::list<XmlAttributeLoner *> * newAttribs;
};

/********************************************************************/

/* class XmlAttributeLoner

The default and fixed items are mutually exclusive; might combine
them in a new type.

The frontNote and simple are content. The other C++ attributes are XML
attributes.

The newNameRef is for processing. It is not needed for representing a
schema. It is needed for getting rid of dashes in an identifier (by
substituting underscores), so that if the identifier gets used as a C++
identifier, it is legal. Exactly one of name and ref is required,
so the newNameRef serves for whichever one is used.

If ref is used, name, typ, typPrefix, and simple must not be used.

*/

class XmlAttributeLoner :
  public XmlAttributor
{
public:
  XmlAttributeLoner();
  XmlAttributeLoner(
    char * defaltIn,
    char * fixedIn,
    formE formIn,
    char * idIn,
    char * nameIn,
    char * refIn,
    char * typPrefixIn,
    char * typIn,
    useE useIn,
    XmlAnnotation * frontNoteIn,
    XmlSimpleType * simpleIn);
  ~XmlAttributeLoner();
  void printSelf(FILE * outFile);

  char * defalt;
  char * fixed;
  formE form;
  char * id;
  char * name;
  char * ref;
  char * typPrefix;
  char * typ;
  useE use;

  XmlAnnotation * frontNote;
  XmlSimpleType * simple;

  char * newNameRef;
  char * newTyp;
};

/********************************************************************/

/* class XmlAttributeLonerRefable


The default and fixed items are mutually exclusive; might combine
them in a new type.

backNotes is inherited from XmlSchemaContent2.

*/

class XmlAttributeLonerRefable :
  public XmlSchemaContent2
{
public:
  XmlAttributeLonerRefable();
  XmlAttributeLonerRefable(
    char * defaltIn,
    char * fixedIn,
    char * idIn,
    char * nameIn,
    char * typIn,
    XmlAnnotation * frontNoteIn,
    std::list<XmlAnnotation *> * backNotesIn,
    XmlSimpleType * simpleIn);
  ~XmlAttributeLonerRefable();
  void printSelf(FILE * outFile);

  char * defalt;
  char * fixed;
  char * id;
  char * name;
  char * typPrefix;
  char * typ;

  XmlAnnotation * frontNote;
  XmlSimpleType * simple;

  char * newName;
  char * newTyp;
};

/********************************************************************/

/* class XmlChoice

For maxOccurs, a value of -1 means unbounded. A value less than -1 
(use -2) means no maxOccurs was given.

For minOccurs, any negative value (use -2) means no minOccurs was given.

The items may be empty.

<choice
  id = ID
  maxOccurs = (nonNegativeInteger | unbounded)  : 1
  minOccurs = nonNegativeInteger : 1

  Content: (annotation?, (element | group | choice | sequence | any)*)
</choice>

*/

class XmlChoice :
  public XmlOtherContentBase,
  public XmlChoSeqItem
{
public:
  XmlChoice();
  XmlChoice(
    char * idIn,
    int maxOccursIn,
    int minOccursIn,
    XmlAnnotation * frontNoteIn,
    std::list<XmlChoSeqItem *> * itemsIn);
  ~XmlChoice();
  void printSelf(FILE * outFile);

  char * id;
  int maxOccurs;  // default is 1
  int minOccurs;  // default is 1
  
  XmlAnnotation * frontNote;
  std::list<XmlChoSeqItem *> * items;

  bool mock;      // added, true if choice is in a mock complexType
};

/********************************************************************/

/* class XmlComplexContent

<complexContent
  id = ID
  mixed = boolean
  Content: (annotation?, (restriction | extension))
</complexContent>

*/

class XmlComplexContent :
  public XmlComplexTypeItem
{
public:
  XmlComplexContent();
  XmlComplexContent(
    char * idIn,
    logicalE mixedIn,
    XmlAnnotation * frontNoteIn,
    XmlComplexContentItem * itemIn);
  ~XmlComplexContent();
  void printSelf(FILE * outFile);

  char * id;
  logicalE mixed;

  XmlAnnotation * frontNote;
  XmlComplexContentItem * item;
};

/********************************************************************/


/* class XmlComplexExtension

<extension
  base = QName
  id = ID

  Content: (annotation?, ((group | all | choice | sequence)?,
                         ((attribute | attributeGroup)*, anyAttribute?)))
</extension>

The base is not optional. All the other attributes are optional.

In an extension, only the items that are added should be declared.

The newAttribs are not part of XML Schema. They are added for processing.
During processing, the newAttribs should be set to contain all the
XmlAttributeLoners in attribs (which may contain both XmlAttributeLoners
and XmlAttributeGroupRefs). The newAttribs are arranged alphabetically.

*/

class XmlComplexExtension :
  public XmlComplexContentItem
{
public:
  XmlComplexExtension();
  XmlComplexExtension(
    char * idIn,
    char * basePrefixIn,
    char * baseIn,
    XmlAnnotation * frontNoteIn,
    XmlOtherContentBase * itemIn,
    std::list<XmlAttributor *> * attribsIn);
  ~XmlComplexExtension();
  void printSelf(FILE * outFile);

  char * id;
  char * basePrefix;
  char * base;

  XmlAnnotation * frontNote;
  XmlOtherContentBase * item;
  std::list<XmlAttributor *> * attribs;
  //XmlAnyAttribute * any;

  std::list<XmlAttributeLoner *> * newAttribs;
};

/********************************************************************/

/* class XmlComplexRestriction

<restriction
  base = QName
  id = ID

  Content: (annotation?, (group | all | choice | sequence)?,
                         ((attribute | attributeGroup)*, anyAttribute?))

The semantics of a restriction are that it must redeclare the
base type with contents such that anything that is a valid instance of
the restricted type is also a valid instance of the base type. Judging
from the available examples, everything from the contents of the
base type that is to be retained must be included in the restriction.
Under the valid instance rule stated above, however, only optional
contents can be eliminated. Determining that a complex restriction is
a valid one will take some doing, programmatically.

Any attributes do not need to be redeclared (they will be inherited).
An attribute may be redeclared in order to restrict it.

The example at
http://www.w3.org/TR/xmlschema-1/#element-complexContent..extension
says that omitting a part of the content of the base eliminates that
part from the restriction.

The example at http://www.w3schools.com/Schema/el_restriction.asp
shows everything to be retained being reiterated.

The base is required. All the other C++ attributes are optional.

It is not clear why the semantics require everything to be reiterated,
since optionals could be eliminated by making them prohibited, but there
is probably a good reason that I have not heard.

The newAttribs are not part of XML Schema. They are added for processing.
During processing, the newAttribs should be set to contain all the
XmlAttributeLoners in attribs (which may contain both XmlAttributeLoners
and XmlAttributeGroupRefs). The newAttribs are arranged alphabetically.

*/

class XmlComplexRestriction :
  public XmlComplexContentItem
{
public:
  XmlComplexRestriction();
  XmlComplexRestriction(
    char * idIn,
    char * baseIn,
    XmlAnnotation * frontNoteIn,
    XmlOtherContentBase * itemIn,
    std::list<XmlAttributor *> * attribsIn);
  ~XmlComplexRestriction();
  void printSelf(FILE * outFile);

  char * id;
  char * base;

  XmlAnnotation * frontNote;
  XmlOtherContentBase * item;
  std::list<XmlAttributor *> * attribs;
  //XmlAnyAttribute * any;

  std::list<XmlAttributeLoner *> * newAttribs;
};

/********************************************************************/

/* class XmlComplexType

<complexType
  abstract = boolean : false
  block = (#all | List of (extension | restriction))
  final = (#all | List of (extension | restriction))
  id = ID
  mixed = boolean : false
  name = NCName>

  Content: (annotation?,
            (simpleContent | complexContent |
             ((group | all | choice | sequence)?,
              ((attribute | attributeGroup)*, anyAttribute?))))
</complexType>

The abstract, block, final, id, mixed, and name are attributes.
The frontNote and item are content.
All the attributes and content are optional.

However, an XmlComplexType at the top level may need a name to be used.
Might split this into Local and Top subtypes.

backNotes is inherited from XmlSchemaContent2.

The newName, ccPrinted, hhPrinted, owlPrefix, and extensions are for
processing; they are not needed for representing a schema.

When the item is an XmlOtherContent, any attributes are attached to
that content (even if there is no choice or sequence).

The production also allows no content at all; this handles that, since
frontNote and item may be 0.

*/

class XmlComplexType :
  public XmlType,
  public XmlSchemaContent2
{
public:
  XmlComplexType();
  XmlComplexType(
    logicalE abstractIn,
    logicalE mixedIn,
    char * idIn,
    char * nameIn,
    XmlAnnotation * frontNoteIn,
    std::list<XmlAnnotation *> * backNotesIn,
    XmlComplexTypeItem * itemIn);
  ~XmlComplexType();
  void printSelf(FILE * outFile);

  logicalE abstract;
  //block
  //final
  char * id;
  logicalE mixed;
  char * name;

  XmlAnnotation * frontNote;
  XmlComplexTypeItem * item;

  char * newName;
  bool ccPrinted;
  bool hhPrinted;
  char * owlPrefix;
  std::list<XmlComplexType *> * extensions;
};

/********************************************************************/

/* class XmlDocumentation

The text is a string that does not contain <.

*/

class XmlDocumentation :
  public XmlAnnoType
{
public:
  XmlDocumentation();
  XmlDocumentation(
    char * sourceIn,
    char * textIn);
  ~XmlDocumentation();
  void printSelf(FILE * outFile);
  bool indented();
  bool formatted();

  char * source;
  //language

  char * text;
};

/********************************************************************/

/* class XmlElementGroup

<group
  id = ID
  name = NCName
  Content:annotation? (all | choice | sequence)
</group>

From section 3.7.2 of http://www.w3.org/TR/xmlschema-1

all - not implemented
choice - implemented
sequence - implemented

The sequence or choice may not have maxOccurs or minOccurs.
The XmlChoSeqItem may not be an XmlElementGroup.

*/

class XmlElementGroup :
  public XmlSchemaContent2
{
public:
  XmlElementGroup();
  XmlElementGroup(
    char * idIn,
    char * nameIn,
    XmlAnnotation * frontNoteIn,
    XmlChoSeqItem * itemIn);
  ~XmlElementGroup();
  void printSelf(FILE * outFile);

  char * id;
  char * name;

  XmlAnnotation * frontNote;
  XmlChoSeqItem * item;

  char * newName;

};

/********************************************************************/

/* class XmlElementGroupRef

<group
  id = ID
  ref = NCName
  maxOccurs = (nonNegativeInteger | unbounded)  : 1
  minOccurs = nonNegativeInteger : 1
  Content:annotation?
</group>

From section 3.7.2 of http://www.w3.org/TR/xmlschema-1

This should have a newRef attribute (like newName).

*/

class XmlElementGroupRef :
  public XmlOtherContentBase,
  public XmlChoSeqItem
{
public:
  XmlElementGroupRef();
  XmlElementGroupRef(
    char * idIn,
    char * nameIn,
    int maxOccurs,
    int minOccurs,
    XmlAnnotation * frontNoteIn);

  ~XmlElementGroupRef();
  void printSelf(FILE * outFile);

  char * id;
  char * ref;
  int maxOccurs;
  int minOccurs;

  XmlAnnotation * frontNote;

};

/********************************************************************/

/* class XmlElementLocal

<element
  abstract = boolean : false
  block = (#all | List of (extension | restriction | substitution))
  default = string
  final = (#all | List of (extension | restriction))
  fixed = string
  form = (qualified | unqualified)
  id = ID
  maxOccurs = (nonNegativeInteger | unbounded)  : 1
  minOccurs = nonNegativeInteger : 1
  name = NCName
  nillable = boolean : false
  ref = QName
  substitutionGroup = QName
  type = QName
  Content:(annotation?, ((simpleType | complexType)?, (unique | key | keyref)*))
</element>

From section 3.3.3 of http://www.w3.org/TR/xmlschema-1
   2.1 One of ref or name must be present, but not both.
   2.2 If ref is present, then all of <complexType>, <simpleType>, <key>,
      <keyref>, <unique>, nillable, default, fixed, form, block and type
      must be absent, i.e. only minOccurs, maxOccurs, id are allowed in
      addition to ref, along with <annotation>.

For maxOccurs, a value of -1 means unbounded. A value less than -1 
(use -2) means no maxOccurs was given.

For minOccurs, any negative value (use -2) means no minOccurs was given.

XML content includes frontNote, typeDef, and idConstraint. Everything else
is an XML attribute. All XML attributes and XML content are optional.

The mock, needList, newName, newTyp, and prodBase are not needed to
represent an XML schema but are needed for processing.

mock - This boolean is set to false in the constructor. It should be
false unless the element is created during processing (not by reading
a file) and is not intended to be printed as an element, in which case
it is set to true.

needList - This boolean is set to false in the constructor. It should
be set to true during processing if a list of occurrences of the
element should be created. That is whenever any of the following
occur.
 maxOccurs is unbounded
 maxOccurs is 0
 maxOccurs is greater than 1
 minOccurs is greater than 1

newName and newTyp - These are needed for getting rid of dashes and
periods in XSDL names (by substituting underscores), so that if the
names get used in C++ identifiers, they are legal.

prod

The newName is derived from the name if there is a name. Otherwise,
the newName is derived from the ref.

For differences between XmlElementLocal and XmlElementRefable, see
documentation of XmlElementRefable.

ElementRef might be better as a separate class since it has many
fewer C++ attributes.

*/

class XmlElementLocal :
  public XmlChoSeqItem
{
public:
  XmlElementLocal();
  XmlElementLocal(
    char * defaltIn,
    char * fixedIn,
    formE formIn,
    char * idIn,
    int maxOccursIn,
    int minOccursIn,
    char * nameIn,
    logicalE nillableIn,
    char * refIn,
    char * typPrefixIn,
    char * typIn,
    XmlAnnotation * frontNoteIn,
    XmlType * typeDefIn,
    std::list<XmlIdConstraint *> * idConstraintsIn);
  ~XmlElementLocal();
  void printSelf(FILE * outFile);

  // block
  char * defalt;     // "default" is a C++ keyword, so change spelling
  char * fixed;
  formE form;
  char * id;
  int maxOccurs;     // default is 1
  int minOccurs;     // default is 1
  char * name;
  logicalE nillable; // default is no (false)
  char * ref;
  char * typPrefix;
  char * typ;

  XmlAnnotation * frontNote;
  XmlType * typeDef;
  std::list<XmlIdConstraint *> * idConstraints;

  bool mock;        // added - see above
  bool needList;    // added - see above
  char * newName;   // added - see above
  char * newTyp;    // added - see above
  char * prodBase;  // added - see above
};

/********************************************************************/

/* class XmlElementRefable

An XmlElementRefable occurs at the top level of a schema. If it is the
first thing after the includes, etc., it gets special treatment, since
then it may be the first thing in an XML data file.  A ref in another
XML item must name an XmlElementRefable.

Alternatives to making this identical in structure to XmlElementLocal
might be good to have a parent type for both that owns all the attributes
or to have no XmlElementRefable at all and put a boolean refable flag
in XmlElementLocal.

Differences between XmlElementLocal and XmlElementRefable are as follows.
These are from "Definitive Xml Schema", pages 120 and 124. The rules in
"XML Schema Part 1: Structures Second Edition" are difficult to read and
seem to be slightly different. For example, Definitive Xml Schema does not
allow abstract in XmlElementLocal, but XML Schema Part 1 allows it as long
as the value is false.

1. Items XmlElementLocal may have but XmlElementRefable may not:
  subtype of XmlChoSeqItem
  form
  maxOccurs
  minOccurs
  ref
  
2. Items XmlElementRefable may have but XmlElementLocal may not:
   subtype of XmlSchemaContent2
   abstract
   final
   substitutionGroup

The mock, needList, newName, newTyp, and prodBase are not needed to
represent an XML schema but are needed for processing.

mock - This boolean is set to false in the constructor. It should be
false unless the element is changed during processing and is not
intended to be printed as an element, in which case it will be set to true
at some point during processing.

The substitutes are other XmlElementRefables whose substitutionGroup is
this XmlElementRefable. It is empty when first constructed.

*/

class XmlElementRefable :
  public XmlSchemaContent2
{
public:
  XmlElementRefable();
  XmlElementRefable(
    logicalE abstractIn,
    char * defaltIn,
    char * fixedIn,
    char * idIn,
    char * nameIn,
    logicalE nillableIn,
    char * substitutionGroupIn,
    char * typPrefixIn,
    char * typIn,
    XmlAnnotation * frontNoteIn,
    std::list<XmlAnnotation *> * backNotesIn,
    XmlType * typeDefIn,
    std::list<XmlIdConstraint *> * idConstraintsIn);
  ~XmlElementRefable();
  void printSelf(FILE * outFile);

  logicalE abstract; // default is no (false)
  // block
  char * defalt;     // "default" is a C++ keyword, so change spelling
  // final
  char * fixed;
  char * id;
  char * name;
  logicalE nillable; // default is no (false)
  char * substitutionGroup;
  char * typPrefix;
  char * typ;

  XmlAnnotation * frontNote;
  XmlType * typeDef;
  std::list<XmlIdConstraint *> * idConstraints;

  bool mock;                                  // added - see above
  char * newName;                             // added
  char * newTyp;                              // added
  char * prodBase;                            // added
  std::list<XmlElementRefable *> substitutes; // added - see above
};

/********************************************************************/

/* class XmlEnumeration

*/

class XmlEnumeration :
  public XmlRestrictionType
{
public:
  XmlEnumeration();
  XmlEnumeration(
    char * idIn,
    char * valueIn,
    XmlAnnotation * frontNoteIn);
  ~XmlEnumeration();
  void printSelf(FILE * outFile);

  char * id;
  char * value;

  XmlAnnotation * frontNote;
};

/********************************************************************/

/* class XmlImport

The namespase (namespace is a C++ key word) is an optional attribute.
The schemaLocation is an optional attribute.

The note is content and is optional.

In the XML spec, an import also has an optional id.
That is not modeled here.

*/

class XmlImport :
  public XmlSchemaContent1
{
public:
  XmlImport(
    char * schemaLocationIn,
    char * namespaseIn,
    XmlAnnotation * noteIn);
  XmlImport();
  ~XmlImport();
  void printSelf(FILE * outFile);

  char * schemaLocation;
  char * namespase;
  XmlAnnotation * note;
};

/********************************************************************/

/* class XmlInclude

The schemaLocation is an attribute and is not optional.

The note is content and is optional.

In the XML spec, an include also has an optional id.
That is not modeled here.

*/

class XmlInclude :
  public XmlSchemaContent1
{
public:
  XmlInclude(
    char * schemaLocationIn,
    XmlAnnotation * noteIn);
  XmlInclude();
  ~XmlInclude();
  void printSelf(FILE * outFile);

  char * schemaLocation;
  XmlAnnotation * note;
};

/********************************************************************/

/* class XmlKey

<key
  id = ID
  name = NCName
  Content: (annotation?, (selector, field+))
</key>

The comments attribute is included to make it possible to save and
reprint comments immediately preceding a key.

*/

class XmlKey :
  public XmlIdConstraint
{
public:
  XmlKey();
  XmlKey(
    char * idIn,
    char * nameIn,
    std::list<std::string *> * commentsIn,
    XmlAnnotation * frontNoteIn,
    XmlSelector * selectorIn,
    std::list<XmlField *> * fieldsIn);
  ~XmlKey();
  void printSelf(FILE * outFile);

  char * id;
  char * name;

  std::list<std::string *> * comments;
  XmlAnnotation * frontNote;
  XmlSelector * selector;
  std::list<XmlField *> * fields;
};

/********************************************************************/

/* class XmlKeyref

<keyref
  id = ID
  name = NCName
  refer = QName
  Content: (annotation?, (selector, field+))
</keyref>

The comments attribute is included to make it possible to save and
reprint comments immediately preceding a keyref.

*/

class XmlKeyref :
  public XmlIdConstraint
{
public:
  XmlKeyref();
  XmlKeyref(
    char * idIn,
    char * nameIn,
    char * referIn,
    std::list<std::string *> * commentsIn,
    XmlAnnotation * frontNoteIn,
    XmlSelector * selectorIn,
    std::list<XmlField *> * fieldsIn);
  ~XmlKeyref();
  void printSelf(FILE * outFile);

  char * id;
  char * name;
  char * refer;

  std::list<std::string *> * comments;
  XmlAnnotation * frontNote;
  XmlSelector * selector;
  std::list<XmlField *> * fields;
};

/********************************************************************/

/* class XmlLength

Value is not optional and must represent a non-negative integer.

The intVal is not needed for parsing or printing. It is the unsigned
integer represented by the value string.

*/

class XmlLength :
  public XmlRestrictionType
{
public:
  XmlLength();
  XmlLength(
    logicalE fixedIn,
    char * idIn,
    char * valueIn,
    XmlAnnotation * noteIn);
  ~XmlLength();
  void printSelf(FILE * outFile);

  logicalE fixed;
  char * id;
  char * value;

  XmlAnnotation * note;

  unsigned int intVal;
};

/********************************************************************/

/* class XmlMaxExclusive

Value is not optional and must make sense for the base data type.

*/

class XmlMaxExclusive :
  public XmlRestrictionType
{
public:
  XmlMaxExclusive();
  XmlMaxExclusive(
    logicalE fixedIn,
    char * idIn,
    char * valueIn,
    XmlAnnotation * noteIn);
  ~XmlMaxExclusive();
  void printSelf(FILE * outFile);

  logicalE fixed;
  char * id;
  char * value;

  XmlAnnotation * note;
};

/********************************************************************/

/* class XmlMaxInclusive

Value is not optional and must make sense for the base data type.

*/

class XmlMaxInclusive :
  public XmlRestrictionType
{
public:
  XmlMaxInclusive();
  XmlMaxInclusive(
    logicalE fixedIn,
    char * idIn,
    char * valueIn,
    XmlAnnotation * noteIn);
  ~XmlMaxInclusive();
  void printSelf(FILE * outFile);

  logicalE fixed;
  char * id;
  char * value;

  XmlAnnotation * note;
};

/********************************************************************/

/* class XmlMaxLength

Value is not optional and must represent a non-negative integer.

The intVal is not needed for parsing or printing. It is the unsigned
integer represented by the value string.

*/

class XmlMaxLength :
  public XmlRestrictionType
{
public:
  XmlMaxLength();
  XmlMaxLength(
    logicalE fixedIn,
    char * idIn,
    char * valueIn,
    XmlAnnotation * noteIn);
  ~XmlMaxLength();
  void printSelf(FILE * outFile);

  logicalE fixed;
  char * id;
  char * value;

  XmlAnnotation * note;

  unsigned int intVal;
};

/********************************************************************/

/* class XmlMinExclusive

Value is not optional and must make sense for the base data type.

*/

class XmlMinExclusive :
  public XmlRestrictionType
{
public:
  XmlMinExclusive();
  XmlMinExclusive(
    logicalE fixedIn,
    char * idIn,
    char * valueIn,
    XmlAnnotation * noteIn);
  ~XmlMinExclusive();
  void printSelf(FILE * outFile);

  logicalE fixed;
  char * id;
  char * value;

  XmlAnnotation * note;
};

/********************************************************************/

/* class XmlMinInclusive

Value is not optional and must make sense for the base data type.

*/

class XmlMinInclusive :
  public XmlRestrictionType
{
public:
  XmlMinInclusive();
  XmlMinInclusive(
    logicalE fixedIn,
    char * idIn,
    char * valueIn,
    XmlAnnotation * noteIn);
  ~XmlMinInclusive();
  void printSelf(FILE * outFile);

  logicalE fixed;
  char * id;
  char * value;

  XmlAnnotation * note;
};

/********************************************************************/

/* class XmlMinLength

Value is not optional and must represent a non-negative integer.

The intVal is not needed for parsing or printing. It is the unsigned
integer represented by the value string.

*/

class XmlMinLength :
  public XmlRestrictionType
{
public:
  XmlMinLength();
  XmlMinLength(
    logicalE fixedIn,
    char * idIn,
    char * valueIn,
    XmlAnnotation * noteIn);
  ~XmlMinLength();
  void printSelf(FILE * outFile);

  logicalE fixed;
  char * id;
  char * value;

  XmlAnnotation * note;

  unsigned int intVal;
};

/********************************************************************/

/* class XmlOtherContent

((group | all | choice | sequence)?,
              ((attribute | attributeGroup)*, anyAttribute?))

element group ref  - implemented
all                - not implemented
choice             - implemented
sequence           - implemented

Base and any are optional. The attrib list may be empty.

The newAttribs are not part of XML Schema. They are added for processing.
During processing, the newAttribs should be set to contain all the
XmlAttributeLoners in attribs (which may contain both XmlAttributeLoners
and XmlAttributeGroupRefs). The newAttribs are arranged alphabetically.

*/

class XmlOtherContent :
  public XmlComplexTypeItem
{
public:
  XmlOtherContent(
    XmlOtherContentBase * baseIn,
    std::list<XmlAttributor *> * attribsIn);
  XmlOtherContent();
  ~XmlOtherContent();
  void printSelf(FILE * outFile);

  XmlOtherContentBase * base;
  std::list<XmlAttributor *> * attribs;

  std::list<XmlAttributeLoner *> * newAttribs;
};

/********************************************************************/

/* class XmlPattern

*/

class XmlPattern :
  public XmlRestrictionType
{
public:
  XmlPattern();
  XmlPattern(
    char * idIn,
    char * valueIn,
    XmlAnnotation * frontNoteIn);
  ~XmlPattern();
  void printSelf(FILE * outFile);

  char * id;
  char * value;

  XmlAnnotation * frontNote;
};

/********************************************************************/

/* class XmlSequence

*/

class XmlSequence :
  public XmlOtherContentBase,
  public XmlChoSeqItem
{
public:
  XmlSequence();
  XmlSequence(
    char * idIn,
    int maxOccursIn,
    int minOccursIn,
    XmlAnnotation * frontNoteIn,
    std::list<XmlChoSeqItem *> * itemsIn);
  ~XmlSequence();
  void printSelf(FILE * outFile);

  char * id;
  int maxOccurs;
  int minOccurs;

  XmlAnnotation * frontNote;
  std::list<XmlChoSeqItem *> * items;
};


/********************************************************************/

/* class XmlSimpleContent

<simpleContent
  id = ID
  Content: (annotation?, (restriction | extension))
</simpleContent>

*/

class XmlSimpleContent :
  public XmlComplexTypeItem
{
public:
  XmlSimpleContent();
  XmlSimpleContent(
    char * idIn,
    XmlAnnotation * frontNoteIn,
    XmlSimpleContentItem * itemIn);
  ~XmlSimpleContent();
  void printSelf(FILE * outFile);

  char * id;

  XmlAnnotation * frontNote;
  XmlSimpleContentItem * item;
};

/********************************************************************/

/* XmlSimpleContentExtension

An XmlSimpleContentExtension extends the simpleContent in which it is nested.
The type being extended is specified by the basePrefix and the base.

From http://www.w3.org/TR/xmlschema-1/#element-complexType

<extension
  base = QName
  id = ID
  Content: (annotation?, ((attribute | attributeGroup)*, anyAttribute?))
</extension>

All the C++ attributes are optional.  The attrib list may be empty.

The newAttribs are not part of XML Schema. They are added for processing.
During processing, the newAttribs should be set to contain all the
XmlAttributeLoners in attribs (which may contain both XmlAttributeLoners
and XmlAttributeGroupRefs). The newAttribs are arranged alphabetically.

*/

class XmlSimpleContentExtension :
  public XmlSimpleContentItem
{
public:
  XmlSimpleContentExtension();
  XmlSimpleContentExtension(
    char * basePrefixIn,
    char * baseIn,
    char * idIn,
    XmlAnnotation * frontNoteIn,
    std::list<XmlAttributor *> * attribsIn);
  ~XmlSimpleContentExtension();
  void printSelf(FILE * outFile);
  
  char * basePrefix;
  char * base; // QName
  char * id;

  XmlAnnotation * frontNote;
  std::list<XmlAttributor *> * attribs;
  
  char * newBase;
  std::list<XmlAttributeLoner *> * newAttribs;
};

/********************************************************************/

/* XmlSimpleContentRestriction

An XmlSimpleContentRestriction restricts the simpleContent in
which it is nested. The type being restricted is specified by the
basePrefix and the base. If no base is specified, the description
of what is being restricted is provided by the simpleType shown in
the Content below.

From http://www.w3.org/TR/xmlschema-1/#element-simpleType
<restriction
  base = QName
  id = ID
  Content: (annotation?,
            (simpleType?,
             (minExclusive | minInclusive | maxExclusive | maxInclusive |
              totalDigits | fractionDigits | length | minLength | maxLength |
              enumeration | whiteSpace | pattern)*)?,
           ((attribute | attributeGroup)*, anyAttribute?))
</restriction>

This differs from XmlSimpleRestriction only in allowing attributes (which
must restrict existing attributes).

All the C++ attributes are optional.

*/

class XmlSimpleContentRestriction :
  public XmlSimpleContentItem
{
public:
  XmlSimpleContentRestriction();
  XmlSimpleContentRestriction(
    char * basePrefixIn,
    char * baseIn,
    char * idIn,
    XmlAnnotation * frontNoteIn,
    XmlSimpleType * aSimpleIn,
    std::list<XmlRestrictionType *> * restrictionsIn,
    std::list<XmlAttributor *> * attribsIn);
  ~XmlSimpleContentRestriction();
  void printSelf(FILE * outFile);

  char * basePrefix;
  char * base; // QName
  char * id;
  std::list<XmlAttributor *> * attribs;

  XmlAnnotation * frontNote;
  XmlSimpleType * aSimple;
  std::list<XmlRestrictionType *> * restrictions;

  char * newBase;
  std::list<XmlAttributeLoner *> * newAttribs;
};

/********************************************************************/

/* XmlSimpleList

An XmlSimpleList provides a white space separated list enclosed
in parentheses of items of a given type, for example: (one two three)
is a list of xs:string.

An XmlSimpleList must have either the itemType or simpleType, but not both.

From http://www.w3.org/TR/xmlschema-1/#element-simpleType

<list
  id = ID
  itemType = QName
  Content: (annotation?, simpleType?)
</list>

*/

class XmlSimpleList :
  public XmlSimpleItem
{
public:
  XmlSimpleList();
  XmlSimpleList(
    char * itemTypeIn,
    char * idIn,
    XmlAnnotation * frontNoteIn,
    XmlSimpleType * aSimpleIn);

  ~XmlSimpleList();
  void printSelf(FILE * outFile);

  char * typePrefix;
  char * itemType; // QName
  char * id;

  XmlAnnotation * frontNote;
  XmlSimpleType * aSimple;

  char * newItemType;
};

/********************************************************************/

/* XmlSimpleRestriction

An XmlSimpleRestriction restricts the simpleType in
which it is nested. The type being restricted is specified by the
basePrefix and the base. If no base is specified, the description
of what is being restricted is provided by the simpleType shown in
the Content below.

From http://www.w3.org/TR/xmlschema-1/#element-simpleType

<restriction
  base = QName
  id = ID

  Content: (annotation?,
            (simpleType?,
             (minExclusive | minInclusive | maxExclusive | maxInclusive |
              totalDigits | fractionDigits | length | minLength | maxLength |
              enumeration | whiteSpace | pattern)*))
</restriction>

All the C++ attributes are optional.

*/

class XmlSimpleRestriction :
  public XmlSimpleItem
{
public:
  XmlSimpleRestriction();
  XmlSimpleRestriction(
    char * basePrefixIn,
    char * baseIn,
    char * idIn,
    XmlAnnotation * frontNoteIn,
    XmlSimpleType * aSimpleIn,
    std::list<XmlRestrictionType *> * restrictionsIn);
  ~XmlSimpleRestriction();
  void printSelf(FILE * outFile);

  char * basePrefix;
  char * base; // QName
  char * id;

  XmlAnnotation * frontNote;
  XmlSimpleType * aSimple;
  std::list<XmlRestrictionType *> * restrictions;

  char * newBase;
};

/********************************************************************/

/* class XmlSimpleType

The item is not optional.

backNotes is inherited from XmlSchemaContent2.

The newName, ccPrinted, hhPrinted, and owlPrefix are for processing;
they are not needed for representing a schema.

*/

class XmlSimpleType :
  public XmlType,
  public XmlSchemaContent2
{
public:
  XmlSimpleType();
  XmlSimpleType(
    XmlFinal * finalIn,
    char * idIn,
    char * nameIn,
    XmlAnnotation * frontNoteIn,
    std::list<XmlAnnotation *> * backNotesIn,
    XmlSimpleItem * itemIn);
  ~XmlSimpleType();
  void printSelf(FILE * outFile);

  XmlFinal * final;
  char * id;
  char * name;

  XmlAnnotation * frontNote;
  XmlSimpleItem * item;

  char * newName;
  bool ccPrinted;
  bool hhPrinted;
  char * owlPrefix;
};

/********************************************************************/

/* class XmlUnique

<key
  id = ID
  name = NCName
  Content: (annotation?, (selector, field+))
</key>

The comments attribute is included to make it possible to save and
reprint comments immediately preceding a unique.

*/

class XmlUnique :
  public XmlIdConstraint
{
public:
  XmlUnique();
  XmlUnique(
    char * idIn,
    char * nameIn,
    std::list<std::string *> * commentsIn,
    XmlAnnotation * frontNoteIn,
    XmlSelector * selectorIn,
    std::list<XmlField *> * fieldsIn);
  ~XmlUnique();
  void printSelf(FILE * outFile);

  char * id;
  char * name;

  std::list<std::string *> * comments;
  XmlAnnotation * frontNote;
  XmlSelector * selector;
  std::list<XmlField *> * fields;
};

/********************************************************************/


