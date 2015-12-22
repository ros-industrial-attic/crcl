/*

The classes list is kept alphabetically by newName. It contains only
XmlTypes. An XmlType is either an XmlComplexType or an XmlSimpleType.
A class is defined for each member of the classes list. A class is also
defined for each attribute of each XmlComplexType.

*/

/********************************************************************/

#define NAMESIZE 200
#define TEXTSIZE 2000
#define BIGSIZE  20000

#include <string>

/********************************************************************/

/* class elementInfo

An elementInfo gives:
1. element - a pointer to an element,
2. complexType - a pointer to an XmlComplexType, which is the type of
   the element if that type is an XmlComplexType and null otherwise.
3. simpleType - a pointer to an XmlSimpleType, which is the type of
   the element if that type is an XmlSimpleType and null otherwise.

At most one of complexType and simpleType can be non-null. If both
are null, the type of the element is a built-in XML type.

The elementInfos list (a list of elementInfo) is used for several
different purposes. Different C++ attributes of elementInfo are used
for these different purposes.

*/

class elementInfo
{
public:
  elementInfo();
  elementInfo(XmlElementLocal * elementIn,
	      XmlComplexType * complexTypeIn, XmlSimpleType * simpleTypeIn);
  ~ elementInfo();
  
  XmlElementLocal * element;
  XmlComplexType * complexType;
  XmlSimpleType * simpleType;
};

/********************************************************************/

/* class namePair

A namePair gives a pair of strings.

NamePair is used in printing the YACC file. It is used in
buildYaccUnionElementPairs, buildUnionExtensionPairs, and
printYaccUnionElements for handling the YACC union.

It is used in buildYaccTypeElementPairs, buildYaccTypeExtensionPairs,
and printYaccTypeElements for handling the list of YACC types.

It is used in many functions for building and printing YACC rules.  In
that use, name1 is a name but name2 is a long string that is a YACC
rule.

In all of its uses, namePairs are put into an alphabetically ordered,
duplicate free list using enterNamePair.

*/

class namePair
{
public:
  namePair();
  namePair(char * name1In, char * name2In);
  ~namePair();

  char * name1;
  char * name2;
};

/********************************************************************/

/* class generator

The contents attribute points to the schema portion of the parse
tree of an XML schema file (everything except the version).

The classes attribute is a list of the XmlTypes from which classes
are derived. This is used in printing the C++ header file and the
C++ code file.

The elementInfos is a list of elementInfo in alphabetical order by
element name. This is used in printing the lex file.

The attributeInfo is a list of strings in alphabetical order. The
strings are the names of XML attributes. This is used in printing the
lex file.

The extensionInfo is a list of namePairs in alphabetical order by
elementName, then typeName. This is used in printing the lex file.

*/

class generator
{
public:
  generator();
  ~generator();

  enum intSize {shorty, plain, longy};

  void abbreviateNames();
  void allCaps(char * lowerName, char * upperName);
  void buildChoice(XmlChoice * choice, XmlElementRefable * refable);
  void buildClasses();
  void buildClassesIncluded();
  void buildDescendants();
  void buildDescendantSet(XmlComplexType * parent,
			  std::list<XmlComplexType *> * descends, bool isTop);
  void buildElementInfo();
  void buildExtensions();
  void buildMockChoice(XmlChoice * choice, XmlElementLocal * mockElement,
		       char * baseName);
  void buildMockSequence(XmlSequence * choice, XmlElementLocal * mockElement,
			 char * baseName);
  void buildSomeExtensions(XmlComplexType * typ);

  void buildYaccBasicRule(char * prodName, char * elementName, char * typ,
			  bool emptyOk,
			  std::list<namePair *> * endRules);
  void buildYaccChoiceRule(XmlChoice * choice, char * name,
			   std::list<namePair *> * endRules);
  void buildYaccChoiceItemRule(XmlElementLocal * element, char * name,
			       char * prodName,
			       std::list<namePair *> * endRules);
  void buildYaccComplexAnyRule(char * typ,
			       std::list<XmlComplexType *> * extensions,
			       std::list<namePair *> * endRules);
  void buildYaccComplexElementRule(XmlComplexType * complx,
                                   char * prodName, char * elementName, 
				   bool emptyOk, bool mock, 
				   std::list<namePair *> * endRules);
  void buildYaccComplexExtRule(char * prodName, char * elementName,
			       char * typ, bool emptyOk,
			       std::list<namePair *> * endRules);
  void buildYaccComplexTypeRule(XmlComplexType * complx,
				std::list<namePair *> * endRules);
  void buildYaccElementListRule(char * prodName, XmlElementLocal * element,
				char * typ, std::list<namePair *> * endRules);
  void buildYaccEmptyRule(char * newName,
			  std::list<XmlAttributeLoner *> * attribs,
			  std::list<namePair *> * endRules);
  void buildYaccExtensionBaseArgs(char * baseName, int * n,
				  int * index, int * comma);
  void buildYaccExtensionBaseItems(char * baseName, int * n);
  void buildYaccExtensionRule1(XmlComplexType * complx,
			       XmlComplexExtension * extend,
			       std::list<namePair *> * endRules);
  void buildYaccExtensionRule2(XmlComplexType * complx,
			       std::list<namePair *> * endRules);
  void buildYaccExtensionRules(std::list<XmlComplexType *> * extensions,
			       XmlComplexType * complx,
			       std::list<namePair *> * endRules);
  void buildYaccExtensionSequenceItems(std::list<XmlChoSeqItem *> * items,
				       int * n);
  void buildYaccRestrictSListRule(XmlElementLocal * element,
				  XmlSimpleType * simple,
				  char * itemTypePrefix, char * itemTypeName,
				  std::list<namePair *> * endRules);
  void buildYaccRulesEnd(std::list<namePair *> * endRules);
  void buildYaccSequenceArgs(int * n, XmlSequence * sequence, int first);
  void buildYaccSequenceItems(int * n, XmlSequence * sequence);
  void buildYaccSequenceRule(XmlSequence * sequence, char * newName,
			     std::list<XmlAttributeLoner *> * attribs,
			     std::list<namePair *> * endRules);
  void buildYaccSimpleContentRule(char * name, char * newName,
				  XmlSimpleContent * simple,
				  std::list<namePair *> * endRules);
  void buildYaccSimpleListRule(char * prodName, char * elementName,
			       XmlSimpleType * simple,
			       XmlSimpleList * aList, bool emptyOk, 
			       std::list<namePair *> * endRules);
  void buildYaccSimpleRule(char * prodName, char * elementName,
			   char * typ, XmlSimpleType * simple, bool emptyOk, 
			   std::list<namePair *> * endRules);
  void buildYaccTypeElementPairs(std::list<namePair *> * typePairs);
  void buildYaccTypeElementPairsBasic(std::list<namePair *> * typePairs,
				      XmlElementLocal * element,
				      bool needsList, char * valName,
				      char * prodName);
  void buildYaccTypeElementPairsComplex(XmlComplexType * complex,
				      std::list<namePair *> * typePairs,
				      XmlElementLocal * element, bool needsList,
				      char * valName, char * prodName);
  void buildYaccTypeElementPairsSimple(XmlSimpleType * simple,
				       std::list<namePair *> * typePairs,
				       XmlElementLocal * element,
				       bool needsOList,
				       char * valName, char * prodName);
  void buildYaccTypeExtensionPairs(std::list<XmlComplexType *> * extensions,
				   std::list<namePair *> * typePairs);
  void buildYaccUnionElementPairs(std::list<namePair *> * unionPairs);
  void buildYaccUnionElementPairsSimple(XmlSimpleType * simple,
					std::list<namePair *> * unionPairs);
  void buildYaccUnionExtensionPairs(std::list<XmlComplexType *> * extensions,
				std::list<namePair *> * unionPairs);

  void checkBaseArgs(char * baseName, int * hasSequence, int * hasAttribs);
  void checkBaseArgsSimple(char * baseName,
			   char * basePrefix, int * hasAttribs);
  void checkListRestrictions(std::list<XmlRestrictionType *> * restrictions);
  void checkName(char * fileName, char * bassNameWithPath,
		 char * bassNameNoPath);
  void checkNumberRestrictions(std::list<XmlRestrictionType *> * restrictions);
  void enterAttribute(char * name);
  void enterAttributeGroupRefable(XmlAttributeGroupRefable * refable);
  void enterAttributeInList(XmlAttributeLoner * attrib,
			    std::list<XmlAttributeLoner *> * attributes,
			    bool sameOK);
  void enterAttributeLonerRefable(XmlAttributeLonerRefable * refable);
  void enterClass(XmlType * aClass);
  void enterClass2(XmlType * aClass);
  void enterElementInfo(elementInfo * info);
  void enterElementRefable(XmlElementRefable * refable);
  void enterKid(XmlComplexType * parent, XmlComplexType * kid);
  void enterLoner(XmlAttributeLoner * loner,
		  std::list<XmlAttributeLoner *> * lonerList);
  void enterName(char * name, std::list<char *> * names);
  void enterNamePair(char * name1, char * name2, std::list<namePair *> * pairs);
  void findAllAttributes(XmlComplexType * complx,
			 std::list<XmlAttributeLoner *> * allAttributes);
  XmlComplexType * findComplexClass(char * name);
  void findCppTypeName(char * wg3TypeName, char * cppTypeName);
  void findRootXmlType(XmlSimpleType * simple, char * rootXmlTypeName);
  void findRootXmlTypeComplex(XmlComplexType * complx, char * rootXmlTypeName);
  XmlSimpleType * findSimpleClass(char * name);
  XmlElementRefable * findElementRefable(char * name);
  void fitPrint(FILE * aFile, char * buffer, int length, int tooBig);
  void flattenAttributes(std::list<XmlAttributeLoner *> * knew,
			 std::list<XmlAttributor *> * old);
  bool hasAttribs(char * baseName);
  bool isNumber(char * typeName);
  bool isRestrictedSList(XmlSimpleType * simple, char * typeName,
			 char * sListItemTypeName, char * sListItemPrefix,
			 int * level);
  bool isStringy(char * typeName);

  void printCppCode();
  void printCppCodeAttributeArgs(std::list<XmlAttributeLoner *> * attribs,
				 const char * typeName, int * comma,
				 const char * space);
  void printCppCodeAttributeDeletes(std::list<XmlAttributeLoner *> * attribs);
  void printCppCodeAttributeSettings(std::list<XmlAttributeLoner *> * attribs);
  void printCppCodeAttributeSettings0(std::list<XmlAttributeLoner *> * attribs);
  void printCppCodeBadAttributes(char * className,
				 std::list<XmlAttributeLoner *> *allAttributes);
  void printCppCodeBaseArgs(char * baseName, int * comma, bool printAttribs);
  void printCppCodeChoice(XmlChoice * choice, char * newName,
			  std::list<XmlAttributeLoner *> * attribs);
  void printCppCodeChoiceConstructors(char * newName,
				      std::list<XmlAttributeLoner *> * attribs,
                                      char * className);
  void printCppCodeChoiceItemDeletes(std::list<XmlChoSeqItem *> * items,
				     char * newName);
  void printCppCodeChoiceItems(std::list<XmlChoSeqItem *> * items,
			       char * newName);
  void printCppCodeComplex(XmlComplexType * complx);
  void printCppCodeComplexExtend(XmlComplexExtension * extend,
				 XmlComplexType * complx,
				 char * newName, char * name,
			       std::list<XmlAttributeLoner *> * allAttributes);
  void printCppCodeDeleteElement(XmlElementLocal * element);
  void printCppCodeEnd();
  void printCppCodeOtherMinimal(char * newName,
			        std::list<XmlAttributeLoner *> * attribs);
  void printCppCodePrintAttribs(std::list<XmlAttributeLoner *> * attribs);
  void printCppCodePrintElement(XmlElementLocal * element);
  void printCppCodePrintItems(std::list<XmlChoSeqItem *> * items);
  void printCppCodePrintLines(XmlComplexType * baseClass, char * baseName);
  void printCppCodeRestrictEnum(char * newName,  char * parentName,
				char * rootCppTypeName,
				std::list<XmlRestrictionType *> * restrictions);
  void printCppCodeRestrictNumber(char * newName, char * basicName,
				  char * rootCppTypeName, 
			       std::list<XmlRestrictionType *> * restrictions);
  void printCppCodeRestrictPattern(char * newName, char * parentName,
				   char * rootCppTypeName, 
				   XmlPattern * pattern);
  void printCppCodeRestrictSList(char * newName, char * parentName,
				 std::list<XmlRestrictionType *>* restrictions,
				 char * typePrefix, char * newItemType,
				 int level);
  void printCppCodeRestrictString(char * newName, char * newBase,
				  char * rootCppTypeName, 
			       std::list<XmlRestrictionType *> * restrictions);
  void printCppCodeSchemaClasses();
  void printCppCodeSequence(XmlSequence * sequence, char * newName,
			    std::list<XmlAttributeLoner *> * attribs);
  void printCppCodeSequenceArgs(std::list<XmlChoSeqItem *> * items,
				int * comma);
  void printCppCodeSequenceDeletes(std::list<XmlChoSeqItem *> * items);
  void printCppCodeSequenceSettings(std::list<XmlChoSeqItem *> * items);
  void printCppCodeSequenceSettings0(std::list<XmlChoSeqItem *> * items);
  void printCppCodeSimple(XmlSimpleType * simple);
  void printCppCodeSimpleExtend(XmlSimpleContentExtension * extend,
				char * newName, char * name,
				std::list<XmlAttributeLoner *> * allAttributes);
  void printCppCodeSimpleList(char * newName, char * typePrefix,
			      char * newItemType);
  void printCppCodeStart();

  void printCppHeader();
  void printCppHeaderAttributeArgs(std::list<XmlAttributeLoner *> * attribs,
				   int * comma, const char * space);
  void printCppHeaderAttributeItems(std::list<XmlAttributeLoner *> * attribs,
				    int hasAttribs);
  void printCppHeaderBaseArgs(char * baseName, int * comma,
			      const char * space, bool printAttribs);
  void printCppHeaderBaseArgsSimple(char * baseName, char * basePrefix,
				    int * comma, const char * space);
  void printCppHeaderBaseItemsSimple(char * baseName, char * basePrefix);
  void printCppHeaderChanges(char * clasName);
  void printCppHeaderChoice(XmlChoice * choice, char * newName,
			    std::list<XmlAttributeLoner *> * attribs);
  void printCppHeaderChoiceConstructors(char * newName,
					std::list<XmlAttributeLoner *> *attribs,
					char * className);
  void printCppHeaderComplex(XmlComplexType * complx);
  void printCppHeaderComplexExtend(XmlComplexExtension * extend,
				   char * newName);
  void printCppHeaderEnd();
  void printCppHeaderOtherMinimal(char * newName,
			          std::list<XmlAttributeLoner *> * attribs);
  void printCppHeaderRestrictEnum(char * newName, char * parentName,
			       std::list<XmlRestrictionType *> * restrictions);
  void printCppHeaderRestrictSList(char * newName, char * parentName,
				  std::list<XmlRestrictionType *>* restrictions,
				  char * typePrefix, char * newItemType,
                                  int level);
  void printCppHeaderRestrictNumber(char * newName, char * parentName,
			       std::list<XmlRestrictionType *> * restrictions);
  void printCppHeaderRestrictPattern(char * newName, char * parentName);
  void printCppHeaderRestrictString(char * newName,  char * parentName,
			       std::list<XmlRestrictionType *> * restrictions);
  void printCppHeaderSchemaClasses();
  void printCppHeaderSchemaClassesComplex(XmlComplexType * complx,
					  unsigned int * totalPrinted,
					  int * progress);
  void printCppHeaderSchemaClassesSimple(XmlSimpleType * simple,
					 unsigned int * totalPrinted,
					 int * progress);
  void printCppHeaderSchemaClassNames();
  void printCppHeaderSequence(XmlSequence * sequence, char * newName,
			      std::list<XmlAttributeLoner *> * attribs);
  void printCppHeaderSequenceArgs(std::list<XmlChoSeqItem *> * items,
				  int * comma, const char * space);
  void printCppHeaderSequenceItems(std::list<XmlChoSeqItem *> * items);
  void printCppHeaderSimple(XmlSimpleType * simple);
  void printCppHeaderSimpleExtend(XmlSimpleContentExtension * extend,
				  char * newName,
			      std::list<XmlAttributeLoner *> * allAttributes);
  void printCppHeaderSimpleList(char * newName,
				char * typePrefix, char * newItemType);
  void printCppHeaderStart();
  void printCppHeaderUnionEnum(std::list<XmlChoSeqItem *> * items);

  void printLex();
  void printLexAttributeNames();
  void printLexElementNames();
  void printLexEnd();
  void printLexExtensions();
  void printLexStart();

  void printNotTop();
  void printParser();
  void printSelf();
  void printStarLine(FILE * file, bool before, bool after);
  void printTop();

  void printYacc();
  void printYaccAttributeTokens();
  void printYaccElementTokens();
  void printYaccEnd();
  void printYaccExtensionTokens();
  int printYaccRuleDefinition(char * text);
  void printYaccRules(std::list<namePair *> * endRules);
  void printYaccRulesAttributeName();
  void printYaccRulesBasic(const char * name);
  void printYaccRulesEnd(std::list<namePair *> * endRules);
  void printYaccRulesStart();
  void printYaccRulesXmlVersion();
  void printYaccStart();
  void printYaccTokens();
  void printYaccTypes();
  void printYaccTypeElements();
  void printYaccUnion();
  void printYaccUnionElements(std::list<namePair *> * unionPairs);

  void processIncludes(std::list<char *> * includeds);
  void readOldHeader(char * headerName);
  void readSchema(char * fileName, bool isTop);
  void replaceSubstitutionGroups();
  void reviewChanges();
  void setHas(char * prefix, char * typeName);
  void usageMessage(char * command);

/* attribute value notes

All of the hasXXX attributes are used in printing the YACC file. XXX
is an XML basic type. The values of all the hasXXX attributes are set
to false in the generator constructor. The value of hasXXX is set to
true in one of the buildYACC functions if the XXX type is used in any
XML file being processed. If the value is set to true when the YACC
file is built, the YACC file needs an entry in each of three sections
(union, types, and rules). Otherwise, those entries are not needed and
bison will complain if they are included.

attributeInfo is alphabetical list of all attributes in all schemas
attributeGroupRefables is list of all XmlAttributeGroupRefable in the schema
attributeLonerRefables is list of all XmlAttributeLonerRefable in the schema
baseNameNoPath is the base name for files without the path or suffix
baseNameWithPath is the base name for files with the path but no suffix
ccFile is the file pointer for the code file to write
changeMap is a map from class names to change text
className is handy buffer for class names (only)
classBaseName is top->newName with initial caps
classes is a list of all the XmlTypes in the schema (a class results from each)
classesMaster is a list of all the XmlTypes in all the schemas
contents1 is the list of XmlSchemaContent1 in the XML file
contents2 is the list of XmlSchemaContent2 in the XML file
dummyName is a dummy name needed for comparison
elementInfos
elementRefables points to a system-wide list of all XmlElementRefables
extensionInfos
hasAnyURI  - see above
hasBoolean - see above
hasDate - see above
hasDateTime - see above
hasDecimal - see above
hasDouble - see above
hasFloat - see above
hasID - see above
hasIDREF - see above
hasInt - see above
hasInteger - see above
hasLong - see above
hasNMTOKEN - see above
hasNonNegativeInteger - see above
hasPositiveInteger - see above
hasString - see above
hasToken - see above
hasUnsignedInt - see above
hasUnsignedLong - see above
headerName is the name of the existing header file to read
hhFile is the file pointer for the header file to write
includedSchemas is a list of the names of included schemas
lexFile is the file pointer for the Lex file to write
mockCount is a pointer to a system-wide mock counter
moreIncludes is the list of added includes from the existing header file
stringInput - true means take input from a string, false means take from file
subordinates is a list of subordinate generators (for some or all includes)
top is an XmlElementLocal surrogate for the top element
target is the name of the target namespace
text
wholeFlag is initialized to false and set to true if /> may be in a data file
yaccFile is the file pointer for the YACC file to write
yyprefix is the substitute prefix for yy

If more than one header file is written (which happens when the top-level
XML schema includes other files), then if a headerName is given, changes
in only that header are written into the new header file.

*/

  std::map<std::string, XmlComplexType *> * allComplex;
  std::map<std::string, XmlSimpleType *> * allSimple;
  std::list<char *> *              attributeInfo;
  std::map<std::string, XmlAttributeGroupRefable *> * attributeGroupRefables;
  std::map<std::string, XmlAttributeLonerRefable *> * attributeLonerRefables;
  char                             baseNameNoPath[NAMESIZE];
  char                             baseNameWithPath[NAMESIZE];
  FILE *                           ccFile;
  std::map<std::string, std::list<char *> *> *  changeMap;
  char                             className[NAMESIZE];
  char                             classBaseName[NAMESIZE];
  std::list<XmlType *> *           classes;
  std::list<XmlSchemaContent1 *> * contents1;
  std::list<XmlSchemaContent2 *> * contents2;
  char *                           dummyName;
  std::list<elementInfo *> *       elementInfos;
  std::map<std::string, XmlElementRefable *> * elementRefables;
  std::list<char *> *              extensionInfos;
  bool                             hasAnyURI;
  bool                             hasBoolean;
  bool                             hasDate;
  bool                             hasDateTime;
  bool                             hasDecimal;
  bool                             hasDouble;
  bool                             hasFloat;
  bool                             hasID;
  bool                             hasIDREF;
  bool                             hasInt;
  bool                             hasInteger;
  bool                             hasLong;
  bool                             hasNMTOKEN;
  bool                             hasNonNegativeInteger;
  bool                             hasPositiveInteger;
  bool                             hasString;
  bool                             hasToken;
  bool                             hasUnsignedInt;
  bool                             hasUnsignedLong;
  char *                           headerName;
  FILE *                           hhFile;
  std::list<char *> *              includedSchemas;
  FILE *                           lexFile;
  int *                            mockCount;
  std::list<char *> *              moreIncludes;
  bool                             stringInput;
  std::list<generator *>           subordinates;
  XmlElementLocal *                top;
  char *                           target;
  char                             text[BIGSIZE];
  bool                             wholeFlag;
  FILE *                           yaccFile;
  char *                           yyprefix;
};

/********************************************************************/

