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

2015Jan28

The partially revised version of this file that is in
common/newGen/source has some improvements that have not yet been
transferred into this working version. 

XSI:TYPE
--------

One major improvement is in the handling of xsi:type. Every instance
of a complexType may include an xsi:type attribute, whether it needs
it or not. That is implemented in the revision but not in this working
version. The notes in the revision say that hundreds of lines of code
were changed in order to handle xsi:type correctly. In particular:

1. In the revision, every C++ class representing an XSDL complexType
must have a printTypp field. This is implemented (A) by having all C++
classes representing complexTypes that are not extensions of basic
types be derived directly or indirectly from XmlTypeBase [which is
defined in the revised version of xmlSchemaInstance.hh,cc], and (B) by
putting printTypp explicitly into C++ classes representing extensions
of basic types.

2. In the revision, printTypp is put explicitly into every C++ class
representing a simpleType derived from a basic type by restriction.

3. In the revision, the xsiTypeNames field of the generator is used to
collect the names of all classes having printTypp. This is done by the
buildXsiTypeNamesAll and buildXsiTypeNamesSome functions. Those
functions actually collect only the names of all classes representing
derived types, but all classes except XmlTypeBase are derived.

4. In the revision, printLexXsiTypes prints a line in the lex file for
each type in xsiTypeNames.

5. In the revision, printYaccXsiTypeTokens prints a token line in the
YACC file for each type in xsiTypeNames.

ACCESS FUNCTIONS
----------------

Another major improvement is that an option for generating access function
and keeping fields private has been added.

2014Mar28

Several std::lists (or std::maps) of things are required by this
program as follows.

Lists/Maps that are C++ fields of generator
------------------------------------------------
allComplex (used only in findComplexType, includes types in all schemas)
allSimple (used only in findSimpleType, includes types in all schemas)
attributeInfo (used only in printing Lex)
attributeLonerRefables (of all schema files, populated only in top generator)
attributeGroupRefables (of all schema files, populated only in top generator)
classes (of schema file of This generator)
contents1 (of schema file of This generator)
contents2 (of schema file of This generator and subordinates)
elementInfos (of all schema files, populated only in top generator)
elementRefables (of all schema files, shared by all generators)
elementGroupRefables (does not currently exist)
extensionInfos (used only in printing Lex)

Lists that are C++ fields of types in XML schema
----------------------------------------------------
substitutes (of XmlElementRefable)
extensions (of XmlComplexType (also called descendants))

Lists that are transient in generator functions
-----------------------------------------------
allAttributes (of an XmlComplexType)

The allComplex, allSimple, attributeLonerRefables, attributeGroupRefables,
and elementRefables maps are used only to resolve references. Since these
maps cover all schema files in the hierarchy, references from schema A to
items in a schema B that is not included by A are allowed, which is not
legal by the rules of XML schema. It would be better to have these lists
include only items from the schema file for This generator and the
generators for the schema files it includes. That could be implemented by
reading all schemas first and recording the hierarchy structure. Then the
generator for each schema would construct its local master lists by adding
items from the local self lists all of the schemas it includes (directly or
indirectly).

The current method is OK except for not detecting illegal references.

It might be better to have both a local contents2 list and a master
contents2 list. The local contents2 list is created during parsing and
is used for generating the local classes list. The master contents2 list
is used for generating YACC and Lex.

2014Mar4

Try the ALSO item below first. It should be simple to test.

Working on debugging. When processing QMPlans.xsd from QIF V1.0, the
generator has bugs that cause errors when compiling the C++ files
and errors when using bison to process the YACC files.

Successfully compiled:

CharacteristicTypesClasses.o
FeatureTypesClasses.o
MeasurementResourcesTypesClasses.o
QIFTypesClasses.o
PrimitiveTypesClasses.o
TraceabilityClasses.o
UnitsClasses.o

Was working on compiling ProductTypesClasses.o.
The code has the following problems.

ProductTypesClasses.cc:540

AssemblyGeometryDefinitionsType::AssemblyGeometryDefinitionsType(
 QIFIdType * idIn,
 QIFReferenceType * AssemblyDefinitionIdIn,
 AssemblyGeometr_1032_Type * AssemblyGeometr_1032In) :
  ProductGeometryDefinitionsBaseType(
    idIn)

error: no matching function for call to 
ProductGeometryDefinitionsBaseType(QIFIdType * idIn)

A correct call would be
ProductGeometryDefinitionsBaseType(
 QIFIdType * idIn,
 std::list<ProductGeometryDefinitionsBaseTypeChoicePair *> * pairsIn);

The parent, ProductGeometryDefinitionsBaseType has a choice (P) of
various types of Part model. The child AssemblyGeometryDefinitionsType
has an additional choice (A) of various types of assembly model.

The ProductGeometryDefinitionsBaseType constructor is called from the
AssemblyGeometryDefinitionsType constructor shown above.
In the AssemblyGeometryDefinitionsType, the list for choice P is missing
entirely. In the constructor for the parent type,
ProductGeometryDefinitionsBaseType, there is a list for choice P, but
there is no mock element. It looks like the choice processing for
ProductGeometryDefinitionsBaseType should be putting in the mock
element for choice P. 

ALSO

It might not be necessary to have the booleans hhPrinted and ccPrinted
(which indicate whether code has been printed for a class). The YACC and
Lex could be generated from allComplex and allSimple. processIncludes
can be simplified if this is done.

What this does
--------------

This reads an XML schema file Xxx.xsd in canonical format and stores the
meaning of the contents of the file in a parse tree in terms of the
xmlSchemaClasses. Then it uses the parse tree for writing the following
files (in the same directory as Xxx.xsd):
1. Xxx.hh - a C++ header file defining classes to represent instance files
   corresponding to the schema.
2. Xxx.cc - a C++ code file implementing the classes
3. Xxx.y - a YACC file for parsing an instance file corresponding to the
   schema and storing the data in terms of the C++ classes.
4. Xxx.lex - a Lex file containing the lexical scanner used by Xxx.y
5. XxxParser.cc - a C++ file containing a main function that reads, stores,
   and reprints an XML instance file corresponding to the schema.

If the XML schema includes other XML schemas files with the same
namespace, this also generates C++ header and code files for those
schema files, but not YACC, Lex, or parser files. The parser built for
Xxx.xsd will be able to parse everything defined in the other schema
files.

If the XML schema does not start with an element, this prints only the
C++ header and code files.

If the generator arguments include "-h <old header file>", any allowed
changes in the old header file will be transcribed into the corresponding
positions in the header file that is generated. Two types of changes to
header files are allowed. First, immediately after the #includes
near the top of the file, a // style comment line may be inserted followed
by more #includes, followed by a blank line. Second, immediately before the
right curly brace that closes each class definition, a // style comment
line may be inserted followed by any lines that are allowed in that
position (for example, a comment, a function declaration, a field
declaration, a public or private declaration, or a constructor declaration).
The generator uses the C++ structures for representing XML schemas defined
in xmlSchemaClasses.hh and xmlSchemaClasses.cc. It uses the schema parser
defined in xmlSchema.lex and xmlSchema.y.

The generator has been tested on a variety of schemas being used in
actual projects and is able to handle them all, so it is expected to
be widely useful in its current form.

All code produced by the generator is human-readable, and human-editable
(if the human knows C++, YACC, and Lex).

Limitations
-----------

The generator handles only schemas in canonical form. A schema in
canonical form has all of its type definitions at the top level. This
is not a major limitation because almost any XML schema can be easily
reduced to canonical form, and the canonical schema will handle
exactly the same instance files as the original schema.

The generator does not distinguish adequately between names that are
the same except for the use of upper and lower case (Ohno and OhNo,
for example), so don't use names in XML schemas that are identical except
for that. The specific problem is that two identical tokens will
be defined in the YACC file (using all upper case letters).

Since names containing dashes are changed by replacing dashes with underscores,
don't use names that are identical except in places where one contains
a dash and the other contains an underscore (oh-no and oh_no, for example).

This does not implement all of XML. The XML schema classes and schema
parser can handle most of XML. Items not implemented in the classes and
parser include the following:

1. xs:all

2. any

3. anyAttribute

4. xs:import

5. xs:notation

6. xs:redefine

7. restriction types: fractionDigits, totalDigits, whiteSpace

8. xs:union

9. wildcards

The generator implements much but not all of what can be handled by the
XML schema classes and the XML schema parser. Of course the generator does
not handle the items listed above; if any of them is included in a schema
the parser prints a parse error message and exits. Parsable items not
implemented in the generator include the following (there may be a few more,
but not many). In general, if the generator encounters any of these, it
either prints a "cannot handle" message and exits or silently ignores them.

1. Restriction of a simple type with attributes is not handled. A "cannot
   handle" message is printed, and the generator exits.

2. final, etc. (limitations on extensions and other derivations of types)

3. Some XML basic data types are not recognized, and some, such as date,
   and dateTime are not checked. Date and dateTime are represented as
   unstructured strings in C++.

4. XmlComplexRestriction is not handled. A "cannot handle" message is
   printed, and the generator exits.

5. Attributes with XmlChoice are not handled. A "cannot handle" message
   is printed, and the generator exits.

6. Any restrictions on strings other than enumeration and pattern are
   not handled. A "cannot handle" message is printed, and the generator
   exits.

7. xs:key, xs:unique, and xs:keyref and their contents (xs:field,
   xs:selector) are read and ignored. 

All names are constructed in an intuitive way so that humans can deal
with them, and the generator works hard to avoid naming conflicts, but
the names produced by the generator are not guaranteed to be free of
conflicts.

Choice or Sequence in Choice or Sequence
----------------------------------------

Sequences and choices may contain element, sequences, and choices.

Here is an example of a choice in a sequence.

  <xs:complexType name="ShirtType">
    <xs:sequence>
      <xs:choice>
        <xs:element name="number"
          type="xs:integer"
          minOccurs="0"/>
        <xs:element name="name"
          type="xs:string"
          minOccurs="0"/>
      </xs:choice>
      <xs:element name="size"
        type="SizeType"/>
    </xs:sequence>
  </xs:complexType>

This is handled in the generator's internal model of the complexType
(that is built when the schema is read) by creating a mock type and a
mock element for the choice and replacing the choice with the mock
element. After the changes, the internal model of the example is what
it would be if the following had been in the schema. 

  <xs:complexType name="ShirtType">
    <xs:sequence>
      <xs:element name="ShirtType_1001"
        type="ShirtType_1001_Type"/>
      <xs:element name="size"
        type="SizeType"/>
    </xs:sequence>
  </xs:complexType>

  <xs:complexType name="ShirtType_1001_Type"
    <xs:choice>
      <xs:element name="number"
        type="xs:integer"
        minOccurs="0"/>
      <xs:element name="name"
        type="xs:string"
        minOccurs="0"/>
    </xs:choice>
  </xs:complexType>

However, both the ShirtType_1001 element and the ShirtType_1001_Type
complexType have been marked with a "mock" boolean flag set to true.
When the C++ code, Lex code, and YACC code are generated for the
instance file parser, the mock is checked and the code is written to
do the right thing for reading and writing instance files conforming
to the original schema. The number 1001 in the names above is the
value of a master counter for uses of mock. The counter starts at 1001
and is incremented each time a new set of mock items is built. The
mock type and the mock element that uses it both use the same number.

A sequence inside a choice or another sequence is handled similarly by
defining a type containing the inner sequence and replacing the
inner sequence with a mock element in the outer sequence or choice.
The type of the mock element is the new type. The new type does not
need to be marked as being mock.

The creation of mock items occurs in buildElementInfo before anything
is printed. buildElementInfo works by going through the classes list
from front to back.  The class for each mock type that is created is
added at the end of the end of the classes list during the process of
going through the list. Hence, each of the mock classes is also processed.
The effect is to recursively dig into any nests of choices and sequences.
When buildElementInfo has finished executing, no nesting remains, and
mock elements appear in the places where there were nested sequences
and choices.

The following generator functions were written or modified to implement this:
   buildElementInfo
   buildMockChoice
   buildMockSequence
   buildYaccChoiceRule
   buildYaccComplexElementRule
   buildYaccRulesEnd
   main
   printCppCodeChoice
   printCppCodePrintElement
   printLexElementNames
   processIncludes


Schema Includes
---------------

Any XML schema file may include other XML schema files. It is necessary to
generate code for all directly or indirectly included files as well as
for the top level file.

The top level schema and each included schema has a C++ header file
and a C++ code file generated for it. One YACC file and one Lex file
are written if the top level schema has a root element. The YACC and
Lex files must handle all items in all schemas. The YACC and Lex files
are generated from the contents2 of the top level schema. At the time
the YACC and Lex are generated, the top level contents2 contains all
contents2 of all included included schemas. The way that the contents2
lists are combined is described in the documentation of processIncludes.

When the C++ code for an including schema is generated, the classes
for its included schemas will be marked as having been processed
(hhPrinted and ccPrinted will be true), so they will not be printed
again in the header and code files for the including schema. This is
implemented by making sure C++ code for included schemas is printed before
code for an including schema. 

Each schema is read only once, regardless of which other schemas include
it. The implementation handles circular includes and having the same
file included multiple times. See the documentation of processIncludes
for more details.


Lists
-----

Specific terminology is used in the documentation to
distinguish among four types of list:
1. occurrence list - oList (for multiple occurrences of an element)
2. XML simpleList - sList
3. lists in C++ code - std::list
4. lists in YACC - YACC list

To avoid confusion, sequences of lines in a file (such as the class
declarations that appear at the beginning of a header file) are not
called lists.

For oLists, a std::list of the type of the element is being
constructed, rather than creating a type for the element and building
a list of that.  This is consistent through the C++ files and the YACC
file that are generated. There does not appear to be any down side to
handling oLists that way. For sLists also, a std::list of the type of
the element is being constructed.

Names related to sLists and oLists are of the form
ListXXX.  For example, a line from the YACC union might be:
  std::list<XmlInteger *> *           ListXmlIntegerVal;
and a line from the list of %type might be
  %type <ListXmlIntegerVal>           y_ListIntElement_XmlInteger_u


Patterns
--------

Patterns (regular expressions describing allowed strings) are handled for
xs:string, xs:token, xs:ID, xs:IDREF, and xs:NMTOKEN. The generator
generates code to check that instance files use correct patterns. This is
done using the boost regular expression tester. Patterns for other types
of strings are not yet implemented. Patterns for numbers are allowed but
code is not written to implement them. It would probably be possible
to use the same sort of code for checking patterns for numbers as is
used for checking patterns for strings.


Function names in this file
---------------------------

Functions whose names start with "find" either (1) return a value
which is the object found (or null) or (2) copy a name into a char
array.

Functions whose names start with "enter" return nothing but take an
argument that is a value that gets entered into a std::list.

Functions whose names start with "build" return nothing but build data
structures. Some of these put text into char arrays which subsequently
get reformatted and printed to files.

Functions whose names start with "print" print text into files.

Naming in generated files
-------------------------

If an XML name is used in constructing a C++ or YACC name, any dashes or
periods are changed to underscores.

XML element names are not necessarily unique in a schema. Any given element
name may be used by more than one XmlElementLocal.  This will happen
whenever, for example, two sequences use the same element name.

XML defined type names are unique in a schema and may not duplicate
built-in XML type names.

Type name prefixes in XML schemas
---------------------------------

If a schema uses a prefix for names in the targetNamespace, then the same
prefix must be used for all names that are not basic. In instance files
corresponding to the schema, that same prefix must be used on the
line identifying the XMLSchema-instance and on the line giving the
schemaLocation.

If a schema does not use a prefix for names in the targetNamespace, then no
prefix must be used for all names that are not basic. In instance files
corresponding to the schema, the prefix "xsi" must be used on the
line identifying the XMLSchema-instance and on the line giving the
schemaLocation.

The rules just described work with XMLSpy. In XMLSpy, if there is no
prefix in the schema, then no prefix must be used for all names that are
not basic, and in instance files corresponding to the schema, the same prefix
must be used on the line identifying the XMLSchema-instance and on the line
giving the schemaLocation, but it does not matter what the prefix is.

  C++ class and field names
  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

XML type names with dashes changed to underscores are used for C++ class
names corresponding to defined type names.

XML built-in types correspond moderately well to C++ built-in types.
Several findXXX functions in the generator handle the correspondence.

XML element names with dashes changed to underscores are used as C++
field names. Within a single C++ class, no XML element name is used
for more than one field name, so duplicate XML element names are
not a problem.

  YACC production names
  ~~~~~~~~~~~~~~~~~~~~~  

All YACC production names produced by the generator are of the following
form. In the notation [] means optional and <prodBase> is as defined 
in enterElementInfo.  Other characters are literal:

  y_[x_][List]<prodBase>[_Check]

When [x_] is used - If XML type X is an extension of type T, and T is
the type of an element E, then the generator will build a YACC production
for X that starts with an XML type declaration (since one is needed in
order to use X with E in an XML instance file). If X is also used directly
as the type of some other element F, then the generator will also build a
YACC production for X that does not include the type declaration. If
the two built productions have the same left-hand side, they will be
combined into one production with two definitions when the YACC file
printer prints them. This would cause the parser to allow an XML instance
file to use a type X without a type declaration as the value of E,
which should not be allowed. To avoid this, different names are used
for the two productions for X. The production for X used in an
extension whose parent type is the type of an element has the prefix
y_x_ .

When [List] is used - If needList for an element is true, the List
prefix is used.

When [_Check] is used - If needList for an element is true. A production
providing a place to check the size of the std::list against maxOccurs and
minOccurs of the element may be needed. If that production is needed, its
name ends in _Check.

  YACC type names
  ~~~~~~~~~~~~~~~

YACC type names are defined in the union definition near the beginning
of the YACC file and used in the type declarations that
follow the union definition. All YACC type names end with "Val".
Where a YACC type name is for a std::list, the YACC type name is of
the form "ListXXXVal".

Handling ref
------------

In C++ terms, the ref mechanism in XML schema lets you define one or
more named class attributes outside of any class definition. Defining
and using refs in XML is a lot like #define in C or C++. In XML,
instead of an explicit indication that something is being #defined,
the definition is simply put at the top level of the schema. Everything
defined at the top level (except for types and the first item if it is
an element) is really like a #define. To use the attribute(s), you use
ref="<attributeName>".

Ref may be used with elements, groups, attributeLoners, and
attributeGroups. It is the only way to use groups and attributeGroups.
If any of those four types of thing is not named in a ref, it does not
get used.

For elements, ref is handled as follows. The handling of ref for XML
attributes is described farther down in the "Handling XML attributes"
section.

1. XmlElementRefable is defined. Every top-level element in the XML schema
file is an XmlElementRefable. If the first entry is an element, it is
parsed as an XmlElementRefable but gets special treatment.

2. A std::map named elementRefables of XmlElementRefables for the
entire set of XML schema files being processed is built. The map is
accessed by the element name.

3. The elementRefables std::map for a generator is populated in
buildClasses or buildClassesIncluded after all schema files have been
parsed. buildClasses and buildClassesIncluded call enterElementRefable
to put XmlElementRefables into the map.

4. When buildElementInfo runs, if an elementLocal has a ref,
(i) findElementRefable is called to find the ref in the elementRefables,
(ii) the ref attribute of the elementLocal is replaced by a "name"
     attribute whose value is the name of the ref, and 
(iii) a "type" attribute is inserted whose value is the type of the ref.

After all the refs have been replaced, everything runs as if the refs
were never there. This is analogous to what the preprocessor does in C++.

Example:

Suppose the following XmlElementRefable is defined in the schema:

  <xs:element name="SignificantDigits"
    type="xs:integer"/>

and elsewhere the following XmlElementLocal appears:

      <xs:element ref="SignificantDigits"/>

Then in the generator, the C++ model of the XmlElementLocal is revised to
be the same as if the schema had the following instead:

      <xs:element name="SignificantDigits"
        type="xs:integer"/>

This approach will not work when key/keyref pairs are embedded in ref'd
elements. Generating code to check Key and keyref is not currently
implemented.

Handling optional elements
----------------------

Optional elements (those with minOccurs="0") that may occur at most
once are handled in YACC by allowing the optional to be empty and
returning a null pointer in that case. In the C++ model of a class,
since all C++ fields corresponding to elements are pointers, nothing
special needs to be done to handle an element that occurs 0 or 1
times. If it does not occur, a null pointer is used.

Optional elements that may occur more than once (maxOccurs="unbounded"
or maxOccurs is greater than 1) are handled by making a std::list. The
std::list is never null, even if there is no instance of the element;
in that case the std::list is empty.

Handling XML choice
-------------------

Classes are created for handling an XmlComplexType containing an XmlChoice
of XML elements as follows. Suppose the XML name of the XmlComplexType is XXX.

1. A C++ class named XXX is created to represent the XmlComplexType.
It has a C++ field named pairs, which is a std::list of pointers
to XXXChoicePairs.  It may have other C++ fields which are pointers
to the C++ equivalents of XML attributes.

2. A union named XXXVal is defined. Each line of the union represents
one of the elements of the XmlChoice. The names in the union are the
names of the elements. The types in the union are the C++ equivalents
of the types of the elements.

3. A class named XXXChoicePair is defined. It has two fields. One is named
XXXType and its value is a whichOne. The other is named XXXValue and its
value is an XXXVal.

4. An enum named whichOne is defined in the XXXChoicePair class. The values of
the enum are made by appending E to the names of the elements.

If the XML type of an XML element is XXX, then the C++ type of the
C++ field representing that element is XXX.

The XXX class has the pairs field defined as a std::list because
if maxOccurs for the XmlChoice is unbounded or greater than 1, a
std::list is required. If maxOccurs is 1 or is not given (which means
maxOccurs is 1), there is no problem; The std::list length is just
1. If maxOccurs is 0, the std::list is empty.

If an XML choice and the elements in it both have maxOccurs greater than
one, parsing an instance in YACC may be ambiguous. This has not been
handled. If it occurs, bison will announce that a conflict exists.

The following functions participate in the handling of XML choice:
  buildYaccChoiceRule
  buildYaccComplexElementRule
  buildYaccTypeElementPairs
  buildYaccUnionElementPairs
  printCppCodeChoice
  printCppCodeStart
  printCppHeaderChoice
  printCppHeaderSchemaClassNames
  printCppHeaderSequenceArgs
  printCppHeaderSequenceItems
  printCppHeaderStart

Handling XML attributes
-----------------------

See the "Handling ref" section above first. XML attributes may be included
with an XmlComplexExtension or an XmlOtherContent. To make the text
clearer, in this section "attribute owner" means an XmlComplexExtension or
an XmlOtherContent.

There are several XML features that make it difficult to deal with XML
attributes, namely:

1. There are several ways attributes can be associated with
   an attribute owner:
1a. by putting in one or more attribute definitions.
   An attribute definition that may be put in is defined in an
   XmlAttributeLoner.
1b. by putting in one or more refs to attribute definitions.
   The reference is put into an XmlAttributeLoner instead of having a
   definition in the XmlAttributeLoner. An attribute definition that
   may be referenced is called an XmlAttributeLonerRefable and
   may occur only at the top level of the schema. Every top level
   attribute is an XmlAttributeLonerRefable.
1c. by putting in one or more references to a group of attribute definitions.
   A reference to a group of attribute definitions is called an
   XmlAttributeGroupRef. A group of definitions that may be referenced is
   called an XmlAttributeGroupRefable and may occur only at the top level of
   the schema. Every top level attribute group is an XmlAttributeGroupRefable.

2. Refs may be nested in both single attributes and attribute groups
   (i.e., an attribute ref may name an attribute that has an attribute ref).

3. In an XML instance file, attributes may be given in any order.

XML attributes are modeled in the C++ classes by having each XML attribute
be a separate C++ field.

The semantics of attributes are the same regardless of how they get
into an attribute owner. That is, attributes mean the same thing as if
they were all put in as individual attribute definitions. The
generator uses this fact to make std::lists called newAttribs
consisting of XmlAttributeLoners that have definitions. Each attribute
owner has a newAttribs std::list. In order to make the process of
building those std::lists easier, XmlAttributeGroupRefable also has a
newAttribs std::list. The newAttribs std::lists are all kept in
alphabetical order.

To deal with the first two items above, after the XML schema has been
parsed, first the newAttribs of all XmlAttributeGroupRefables are
built and any XmlAttributeLonerRefable that has a ref has the ref
replaced by a definition. Then the newAttribs std::lists of all
attribute owners are built.  In the newAttribs for an attribute owner,
any XmlAttributeLoner with a ref is represented by an
XmlAttributeLoner with a definition, and any XmlAttributeGroupRef is
represented by copying in its newAttribs. C++ generation for an
attribute owner does not take place until its newAttribs std::list has
been built. During C++ generation, the newAttribs std::list is used.

Item 3 above makes it difficult to generate an instance file parser.
Fortunately, all attribute values are strings. This makes it feasible
to define the AttributePair class, which is simply (1) a std::string that
represents the attribute name and (2) a std::string that represents the
value. All attributes are given one after the other in the instance file,
so the instance file parser handles them by first making a std::list of
AttributePairs, second making an instance of the class that has null
pointers for all the attributes, and third calling a checking function
belonging to the instance.  The checking function checks that the
attribute names and values are all legitimate and inserts the values
in the instance. Printing attributes into instance files is not a problem.

Handling built in XML data types
--------------------------------

For each built in XML data type (such as positiveInteger) a C++ class is
declared and implemented (in xmlSchemaInstance.hh and xmlSchemaInstance.cc,
respectively). The value of the data is stored in a "val" field.
A "bad" field indicates whether the data violates the restrictions
required by the XML data type. A checking function checks the restrictions.
On parsing, the YACC parser calls the constructor which calls the checking
function and sets the value of "bad". The YACC parser checks the value
of "bad". On printing, the printer runs the checking function. Inside an
executing program the value of "val" may changed to violate the constraints,
but it will not be possible to print a file containing the bad value.


Checking restrictions on XML built-in data types
------------------------------------------------

The rules for the chain of C++ classes that represent a chain of XML
restrictions of a built-in XML data type are as follows. An important
consideration that has motivated these rules is that it should be easy
to prevent unnoticed errors, and it should be difficult to pass errors
on. The XML chain in this situation will consist of simpleTypes.

1. Any chain of C++ class derivations representing a chain of XML
   restrictions of an XML simple type ends at a C++ root type (defined in
   xmlSchemaInstance.cc) representing an XML built-in data type.
   Each class in the chain inherits two C++ fields from the C++ root
   class: (1) "val", which has a built-in C++ type, and (2) a boolean "bad"
   which will be set to true if "val" violates the constraints it is
   supposed to follow. The class has no other C++ fields.
   Each class in the chain has two constructors. One takes no arguments.
   The other takes a char * argument.

2. The constructor that takes no arguments does nothing except call the
   constructor for its parent type that takes no arguments. When the
   call chain ends at the root type, the root type constructor sets val
   to some arbitrary value of the correct type and sets bad to true.

3. The constructor that takes a char * argument calls the constructor for
   its parent, passing the argument to the parent constructor.
   When the call chain reaches the constructor for the root class, "bad"
   and "val" will be set. Then the chain of constructors that has been
   formed dynamically unwinds. After the call to a parent constructor, if
   "bad" is false (meaning everything is OK so far), the child constructor
   sets "bad" to the value returned by the child class's IsBad function.

4. A checking function for each C++ class in the chain is defined.
   The checking function for a class named xxx is named xxxIsBad.
   For example, if the class is named Kid and its parent class is
   named Dad, then the checking function for the class will be named
   KidIsBad, and the checking function for the parent class will be
   named DadIsBad. Each checking function takes no arguments.
   The KidIsBad function calls DadIsBad. If DadIsBad returns true,
   KidIsBad returns true since "val" is known to violate constraints.
   If DadIsBad returns false, KidIsBad applies its own checks and returns
   what they return.

5. In order to prevent printing bad data, the printSelf function for
   the class calls the checking function for the class. If the checking
   function returns true, an error message is printed and the function
   calls exit. If the checking function returns false, printSelf calls
   the printSelf function for the root class.

Checking restrictions on XML simple list types
------------------------------------------------

The rules for the chain of C++ classes that represent a chain of XML
restrictions of a simple list (sList) of built-in XML data type are as
follows. These are similar the rules for the built-in data types.

1. The bottom of the chain is an sList of some simple data type (basic
or restricted, but not an sList) with no restrictions. This sList does
not need or have a "bad" data element or a checking function. The
printSelf function that does the actual printing is at this level.

2. The next level up has one or more restrictions, so it has a "bad"
field and a checking function that checks the restrictions of this
level. The constructor at this level takes an sList of the simple type
as an argument and passes it to the constructor at the bottom level.
Then it sets "bad" to the value returned by its checking function.
The printSelf function calls the checking function and exits if it
returns true (indicating a restriction failed); then it calls the
printSelf function at the bottom level. During file parsing, the YACC
parser calls yyerror (which exits) if "bad" is true after the constructor
is called.

3. The third level up (and higher levels) has one or more restrictions, so
it has a checking function that checks the restrictions of this level. It
inherits the "bad" field from the next level down. The constructor at
this level takes an sList of the simple type as an argument and passes it
to the constructor at the next level down. Then, if "bad" is false, it
sets bad to the value returned by its checking function. That way, as the
chain of constructors comes back up, all the restrictions at all the levels
get checked. The printSelf function calls the checking function at this
level and exits if it returns true (indicating a restriction failed); then
it calls the printSelf function at the next level down. As the chain of
calls to  printSelf moves down, all the restrictions at all the levels
get checked before anything is printed. During file parsing, the YACC
parser calls yyerror (which exits) if "bad" is true after the constructor
is called.

Dealing with instances ending in />
-----------------------------------

If a type has no elements but does have attributes, an instance of an
element of that type may end in />. This is being handled. If the type
has elements that are all optional and an instance uses none of them,
the instance may also end in />. This is not being handled. 

The .cc file that is generated always prints the long form. For example:
                 <Gizmo id="ID1">
                 </Gizmo>

It might be good to have the .cc file that is generated print the short form.
For example:
                 <Gizmo id="ID2"/>
This would be a nuisance since the parent type prints the start and end for
the element using the type in the long form.

Unsigned int
---------------------

In C++, an unsigned int is not what it sounds like. In C++, scanf will read
a negative number into an unsigned int, rather than refusing to accept it.
The result is whatever unsigned int happens to match the bit pattern of the
negative number. The maximum value is system dependent, but must be at
least 65535.

In XML (sections 3.3.21.1 and 3.3.22 of "XML Schema Part 2: Data"), an
unsignedInt is what it sounds like -- a sequence of digits (without a
preceding sign). The maximum value is 4294967295.

In the generator, XML unsignedInts are represented by C++ ints, and it
is checked that the value is not less than zero. The C++ ints are printed
out in the printSelf functions produced by the generator using printf,
which omits the plus sign by default.

C++ code
--------

The C++ header and code files written for each class include:
1. A constructor taking no arguments that does nothing.
2. A constructor taking an argument for each field. The constructor
   sets each field to the corresponding value given in the arguments.
3. A constructor taking an argument for each field not corresponding
   to an attribute. These constructors are used in YACC code.
4. A destructor taking no arguments that has the effect of deleting all
   fields.
5. A printSelf function that prints the item in an XML file. This takes
   one argument that is a FILE pointer.
6. A field for each attribute and element. In the case of XmlChoice,
   more items are included as described above.

For classes representing XML restrictions, there is also a boolean "bad"
field and a checkVal or xxxIsBad function called by the constructor
that checks whether the data is bad. The YACC parser checks the value of
"bad" after the constructor runs.

YACC terminology
----------------

In the in-line documentation of functions with names that include
"Yacc", YACC terminology is frequently used. The next-to-last section
of a YACC file, which is usually the largest section of a YACC file,
is a set of rules. Here is an example of a YACC rule.

y_ListPerson_PersonType_u :
	  y_Person_PersonType_u
	  {$$ = new std::list<PersonType *>;
	   $$->push_back($1);}
	| y_ListPerson_PersonType_u y_Person_PersonType_u
	  {$$ = $1;
	   $$->push_back($2);}
	;

The "rule" is all eight lines above. A rule consists of a left-hand
side, a colon, and a right-hand side (everything after the colon). The
left-hand side has only one symbol, called the "production name" of
the rule. The right-hand side has a semicolon at the end and consists
of pairs (which we will call "subrules") of sets of lines. In the
example above, there are two subrules. If there is more than one
subrule, the subrules are separated by vertical bars. The first set of
lines in a subrule are called the "definition". In the example above,
each definition has only one line, but in general, a definition may
have several lines. The second set of lines is YACCified C++ code
starting with a left curly brace and ending with a right curly
brace. The second set is called the "action". The actions in the
example above each take up two lines.

The first and sixth sections of a YACC file are C++ code.

The second section of a YACC file is a C++ union definition.

The third section of a YACC file is a set of type declarations.

The fourth section of a YACC file is set of token declarations.

The fifth section of a YACC file is the rules, as described above.

Hidden Coordination
-------------------

The generator code has the unfortunate property that symbols that must
be the same or closely related are spread across the code that the
generator writes, and there is nothing in the generator code that
indicates the relationship. For example, the XML schema
AsPlannedPallets.xsd defines a PlannedPallet element whose type is
PlannedPalletType. When the generator produces files for this schema:

1. The C++ header file is named AsPlannedPalletsClasses.hh and
   in that file:
   a. PlannedPalletType is listed in the class declarations at the
      beginning of the file.
   b. The PlannedPalletType class is declared in the middle of the file.
2. The C++ code file is named AsPlannedPalletsClasses.cc and in that file:
   a. The PlannedPalletType class is defined in the middle of the file.
3. The YACC file is named AsPlannedPallets.y, and in that file:
   a. The union definition in the second section includes a
      PlannedPalletTypeVal variable which is a pointer to an instance
      of the PlannedPalletType class.
   b. The YACC type declarations in the third section includes
      a declaration that PlannedPalletTypeVal is the type of the
      y_PlannedPalletType production and a declaration that
      PlannedPalletTypeVal is also the type of
      y_PlannedPallet_PlannedPalletType_u.
   c. The YACC tokens that is the fourth section includes
      PLANNEDPALLETEND and PLANNEDPALLETSTART.
   d. The YACC rule for the y_PlannedPalletType production and the YACC
      rule for y_PlannedPallet_PlannedPalletType_u are given in the
      fifth section.
4. The Lex file is named AsPlannedPallets.lex, and in that file:
   a. The Lex rules for recognizing PLANNEDPALLETEND and PLANNEDPALLETSTART
      are given.

The generator includes code-writing code that ensures that all these items
are coordinated, but there is no hint that the generator code is doing that
in the code itself. The in-line documentation of the generator code
describes some of the required coordination. When any code-writing section
of the generator is changed, it may be necessary to change some or all of
the other sections that deal with the same sort of item.

Code appearance
----------------------------------------

All the code written by the generator is as human-readable as it could
be if it were written by a good human programmer. In all files, items
are listed alphabetically wherever possible and line length is usually
kept under the standard 80 characters. One assumption throughout is
that a tab (ASCII 9) at the beginning of a line is the size of 8
spaces (ASCII 32).

The C++ header files, the C++ code files, and the (short) C++ parser files
produced by the generator are clear and nicely formatted.

The YACC and Lex files produced by the generator are ugly. That's just
the way YACC and Lex files always are. A human could not improve the
formatting significantly.

The portions of the generator code that write files (which is most of the
code) are horribly ugly. That is inescapable for code that writes nicely
formatted code. The printing of code is done using C-style print commands
since they enable fine control more easily than C++ print commands.  The
formatting of the YACC file is done in two stages. The rules in the file
are constructed as a std::list of pairs of strings by many buildXXX
functions. The string pairs are alphabetized and unduplicated.  Then the
file is printed by printYaccXXX functions.

FIX
----------------------------------------

FIX - Simple extensions (which add XML attributes) are not handled by
inheritance, so a mix of restriction and extension of a list type will
probably not work.

FIX - The data type entries in the YACC file that is generated are not used
if the data types are used only for attribute values since the attribute
values are not parsed (they just come in as strings). When bison runs, it
flags them as useless. Change so that being an attribute value does not
trigger putting a type into the YACC file.

FIX - If two elements have names that differ only in the case of one
or more letters in the name, duplicate %tokens will be entered in the
%tokens section of the YACC file, since the tokens are made by putting
the names of elements in elementInfos into upper case letters. This
might be dealt with by using case insensitive comparison for ordering
in enterElementInfo and for comparing element names of adjacent
elementInfos in printYaccElementTokens.

Maybe make changes
----------------------------------------

Idea for rebuilding the way the generator builds the YACC and Lex files

Currently, the generator builds each part of the YACC file separately. In
most cases, this is done by going through the elementInfos list. The
elementInfos list is processed again for printing Lex.
The elementInfos are processed in:
 buildExtensions
 buildYaccRulesEnd
 buildYaccTypeElementPairs
 buildYaccUnionElementPairs
 enterElementInfo (puts elementInfo into elementInfos)
 printLexElementNames
 printYaccElementTokens

This results in making the same sorts of decisions multiple times. It
may be better to go through the elementInfos once and handle all the
items built from each elementInfo. The various types of item would be
saved in lists of the specific item types. Nothing would be printed until
the all the lists were complete.

*/

/********************************************************************/

#include <stdio.h>   // fprintf
#include <string.h>  // strlen
#include <ctype.h>   // toupper
#include <stdlib.h>  // exit
#include <list>      // list
#include <map>       // map
#include <string>    // string
#include "xml_parser_generator/xmlSchemaClasses.hh"
#include "xml_parser_generator/xmlInstanceParserGenerator.hh"
#define XMLSCHEMAINSTANCEBASE "XmlSchemaInstanceBase"

static char *includePrefix = NULL;
static char *appIncludePrefix = NULL;

/********************************************************************/

extern XmlSchemaFile * xmlSchemaFile;
extern FILE * yyin;
extern int yyparse();

/********************************************************************/

int XmlSchemaFile::printDoc = 0;
bool XmlSchemaFile::printComments = false;

/********************************************************************/

/* generator::abbreviateNames

Returned Value: none

Called By: printTop

If the length of a prodBase in an elementInfo is more than 35, this
abbreviates the name in place by printing the mockCount over the
prodBase characters starting in the 31st place and then ending the string.

This could be a problem if the mockCount ever exceeds 5 characters,
but that is very unlikely with the current mockCount starting value of
1001; 998999 mockCount values would have to have been used.

Before being abbreviated, the prodBase names are unique. Since each
mockCount value is used only once, the abbreviated names are also unique.

*/

void generator::abbreviateNames()
{
  std::list<elementInfo *>::iterator iter;
  char * prodBase;

  for (iter = elementInfos->begin(); iter != elementInfos->end(); iter++)
    {
      prodBase = (*iter)->element->prodBase;
      if (prodBase)
	{
	  if (strlen(prodBase) > 35)
	    {
	      snprintf(prodBase+30, 5, "%d", *mockCount);
	      (*mockCount)++;
	    }
	}
      else
	{
	  fprintf(stderr, "prodBase missing from elementInfo\n");
	  exit(1);
	}
    }
}

/********************************************************************/

/* generator::allCaps

Returned Value: none

Called By:
  generator::buildYaccBasicRule
  generator::buildYaccComplexElementRule
  generator::buildYaccComplexExtRule
  generator::buildYaccExtensionRule1
  generator::buildYaccExtensionRule2
  generator::buildYaccRestrictSListRule
  generator::buildYaccSimpleListRule
  generator::buildYaccSimpleRule
  generator::printCppHeader
  generator::printLexAttributeNames
  generator::printLexElementNames
  generator::printLexExtensions
  generator::printYaccAttributeTokens
  generator::printYaccElementTokens
  generator::printYaccExtensionTokens
  generator::printYaccRulesAttributeName
  generator::printYaccRulesStart

This copies lowerName into upperName, changing any lower case letters
to upper case letters.

*/

void generator::allCaps( /* ARGUMENTS                         */
 char * lowerName,       /* name to convert to all upper case */
 char * upperName)       /* name converted to all upper case  */
{
  int length;
  int n;

  length = strlen(lowerName);
  for (n = 0; n < length; n++)
    upperName[n] = toupper(lowerName[n]);
  upperName[n] = 0;
}

/********************************************************************/

/* generator::buildChoice

Returned Value: none

Called By: generator::replaceSubstitutionGroups

This builds an XmlChoice whose elements are XmlElementLocals corresponding
to the XmlElementRefables in the substitutes of the refable. The mock of
the XmlChoice is set to false since there is an element whose type is the
choice. The XmlChoice is put at the end of the classes.

The type of the refable might be a basic type, in which case the typPrefix
of the refable will be xs, but the type is being changed to a choice, so
the typPrefix is set to 0 (null).

FIX - Need to include an XmlElementLocal corresponding to the refable
if the element is not abstract and its type is not abstract.

*/

void generator::buildChoice(  /* ARGUMENTS                                  */
 XmlChoice * choice,          /* the choice to build                        */
 XmlElementRefable * refable) /* refable to use substitutes from for choice */
{
  std::list<XmlElementRefable *>::iterator iter;
  XmlElementLocal * element;

  refable->typPrefix = 0;
  choice->items = new std::list<XmlChoSeqItem *>;
  choice->mock = true;
  for (iter = refable->substitutes.begin();
       iter != refable->substitutes.end(); iter++)
    {
      element = new XmlElementLocal();
      choice->items->push_back(element);
      element->name = (*iter)->name;
      element->newName = (*iter)->newName;
      element->typ = (*iter)->typ;
      element->newTyp = (*iter)->typ;
    }
}

/********************************************************************/
/* generator::buildClasses

Returned Value: none

Called By: main

This goes through the contents2 std::list of the schema. For each item
on the std::list:
1. If the item is a complex type or simple type, it is placed on the
   classes std::list.
2. If the item is the first item and is an XmlElementRefable, it is
   handled as described below.
3. If the item is not the first item and is an XmlElementRefable, it is
   entered into the elementRefables map.
4. If the item is an XmlAttributeLoner, it is entered into the
   attributeLonerRefables map.
5. If the item is an XmlAttributeGroupRefable, it is entered into the
   attributeGroupRefables map.

The classes are the top-level complexTypes and the top-level simpleTypes.

All top level elements (which must be XmlElementRefable) of an XML
schema in canonical form must have a non-zero typ and must have a zero
typeDef. This checks for that.

A representation of the top-level element needs to be included in the
elementInfos so that the name of the top-level element will be included in
the lex file and a production (and other items) for the top-level element
will be included in the YACC file. However, an elementInfo requires an
XmlElementLocal.  To deal with this, the generator has an field named
top that is a pointer to an XmlElementLocal, which is built here. Top has
the same name, newName, type, and typPrefix as the top-level element.  An
elementInfo is built here using top and entered into the elementInfos. Top
is used at several places in the code.

FIX - This is not handling nested substitutionGroups. If refable R2 is
in the substitutes of refable R1 and R2 has substitutes, then R2's
substitutes should be added to R1's substitutes.

*/

void generator::buildClasses() /* NO ARGUMENTS */
{
  std::list<XmlSchemaContent2 *>::iterator iter;
  XmlElementRefable * realTop;
  XmlElementRefable * elementRefable;
  XmlAttributeGroupRefable * attributeGroupRefable;
  XmlAttributeLonerRefable * attributeLonerRefable;
  XmlComplexType * complx;
  XmlSimpleType * simple;
  
  iter = contents2->begin();
  if ((realTop = dynamic_cast<XmlElementRefable *>(*iter)))
    {
      if (realTop->typeDef || (realTop->typ == 0))
	{
	  fprintf(stderr, "top element must have a typ and no typeDef\n");
	  exit(1);
	}
      else
	iter++;
    }
  for ( ; iter != contents2->end(); iter++)
    {
      if ((complx = dynamic_cast<XmlComplexType *>(*iter)))
	{
	  enterClass(complx);
	  enterClass2(complx);
	}
      else if ((simple = dynamic_cast<XmlSimpleType *>(*iter)))
	{
	  enterClass(simple);
	  enterClass2(simple);
	}
      else if ((elementRefable = dynamic_cast<XmlElementRefable *>(*iter)))
	{
	  enterElementRefable(elementRefable);
	  if (elementRefable->typeDef || (elementRefable->typ == 0))
	    {
	      fprintf(stderr,
		      "top level element must have a typ and no typeDef\n");
	      exit(1);
	    }
	}
      else if ((attributeLonerRefable =
		dynamic_cast<XmlAttributeLonerRefable *>(*iter)))
	{
	  enterAttributeLonerRefable(attributeLonerRefable);
	}
      else if ((attributeGroupRefable =
		dynamic_cast<XmlAttributeGroupRefable *>(*iter)))
	{
	  enterAttributeGroupRefable(attributeGroupRefable);
	}
      else if (dynamic_cast<XmlElementGroup *>(*iter))
	{
	  fprintf(stderr, "buildClasses cannot handle XmlElementGroup\n");
	  //exit(1);
	}
      else
	{
	  fprintf(stderr, "unknown XmlSchemaContent2 type in buildClasses\n");
	  exit(1);
	}
    }
  if (realTop)
    {
      top = new XmlElementLocal;
      top->name = realTop->name;
      top->typ = realTop->typ;
      top->typPrefix = 0;
      top->newName = XmlCppBase::modifyName(top->name);
      top->newTyp = XmlCppBase::modifyName(top->typ);
      enterElementInfo(new elementInfo(top, findComplexClass(top->newTyp), 0));
    }
  else
    top = 0;
}

/********************************************************************/

/* generator::buildClassesIncluded

Returned Value: none

Called By: generator::processIncludes

This builds the classes std::list for a generator instance created to
handle an included XML schema file.

The classes that get added are the top-level complexTypes and the
top-level simpleTypes from the included schema (which is all of them
since all type definitions are required to be at the top level).

*/

void generator::buildClassesIncluded() /* NO ARGUMENTS */
{
  std::list<XmlSchemaContent2 *>::iterator iter;
  XmlComplexType * complx;
  XmlSimpleType * simple;
  
  top = 0;
  for (iter = contents2->begin(); iter != contents2->end(); iter++)
    {
      if ((complx = dynamic_cast<XmlComplexType *>(*iter)))
	{
	  enterClass(complx);
	}
      else if ((simple = dynamic_cast<XmlSimpleType *>(*iter)))
	{
	  enterClass(simple);
	}
    }
}

/********************************************************************/

/* generator::buildDescendants

Returned Value: none

Called By: generator::printLex

This goes through the classes std::list, finds all the descendants of each
XmlComplexType, complx, and puts them in the extensions std::list of
complx. The descendants of complx are those XmlComplexExtensions that
have complx as the base type or that have an ancestor (via extension)
that has complx as the base type. When this is called, the immediate
children of complx have already been recorded (if there are any).

The extensions of an XmlComplexType are used in buildSomeExtensions
to build the generator's extensionInfos std::list, which is needed to print
the lex file.

*/

void generator::buildDescendants() /* NO ARGUMENTS */
{
  std::list<XmlType *>::iterator iter;
  XmlComplexType * complx;
  
  for (iter = classes->begin(); iter != classes->end(); iter++)
    {
      if ((complx = dynamic_cast<XmlComplexType *>(*iter)))
	{
	  buildDescendantSet(complx, complx->extensions, true);
	}
    }
}

/********************************************************************/

/* generator::buildDescendantSet

Returned Value: none

Called By:
  generator::buildDescendants
  generator::buildDescendantSet (recursively)

This finds all the descendants (via extension) of the patriarch and
records them in the patriarch's extensions std::list. It calls itself
recursively to dig down through all levels of descendants. It checks
for loops of descendants while it works. If a loop of descendants is
found, it prints an error message and exits.

Since XML does not have multiple inheritance via extensions of complex
types, and since this is called only once for any given patriarch,
there is no need to check for duplicates in the descendants.

The descends std::list may be null; in this case the function does nothing
and returns.

The immediate children of the patriarch have already been entered in
the patriarch's extensions std::list, so when this is called at the
top level, nothing should be added.

*/

void generator::buildDescendantSet(      /* ARGUMENTS                    */
 XmlComplexType * patriarch,             /* class to add descendants to  */
 std::list<XmlComplexType *> * descends, /* a list of descendants to add */
 bool isTop)                             /* true=top-level call          */
{
  std::list<XmlComplexType *>::iterator iter;

  if (descends == 0)
    return;
  for (iter = descends->begin(); iter != descends->end(); iter++)
    {
      if (*iter == patriarch)
	{
	  fprintf(stderr, "loop of complex extensions found, exiting\n");
	  exit(1);
	}
      if (isTop == false)
	patriarch->extensions->push_back(*iter);
      buildDescendantSet(patriarch, (*iter)->extensions, false);
    }
}

/********************************************************************/

/* generator::buildElementInfo

Returned Value: none

Called By:
  generator::printNotTop
  generator::printTop

This calls enterElementInfo for every XmlElementLocal in the XML
schema.  These are all descended from the classes std::list. The
XmlElementLocals are found in the items std::lists of sequences and
choices in complex types of the classes std::list.

If a top-level XML schema includes another schema but does not
use all the types defined in the other schema, then the YACC file
generated from the classes (which will include all elements in both
schemas) will have useless rules and useless nonterminals in it. Bison
will detect the useless items and issue warnings. The header and code
files produced by bison will be good. Bison keeps useless tokens since
the lexical scanner might use them. Bison gets rid of the useless
rules in the code it writes in order to produce a simpler parser. Thus,
bison does exactly what is desired, except that the warnings are annoying.

To avoid the warnings issued by bison, before building the Lex and
YACC files, it might be feasible to start at the top element, trace
down the tree of elements that actually get used, and build the
elementInfos that way.

In the case of an XmlComplexType with an XmlOtherContent item,
currently, only XmlSequence and XmlChoice are handled by the parser.
The other two types of XmlOtherContent are XmlElementGroupRef and
XmlAll. An XmlElementGroupRef cannot be handled. If it is used, this
prints an error message and exits. XmlAll has not been defined in the
xmlSchemaClasses. The parser will return an error if a schema contains
them, so it is not necessary to check for XmlAll here.

In the case of am XmlComplexExtension whose item is a choice, the item
is replaced by a sequence whose items is a std::list<XmlChoSeqItem *>
containing only the choice. When that list is processed further down in
the function, a mock choice element and type will be built.

In the case of an element E that uses ref R:
1. The name of E is changed to the name of R.
2. The newName of E is already set to the newName of R (in the parser).
3. The newTyp of E is changed to the newTyp of R.
4. The typPrefix of E is changed to the typPrefix of R.
5. The mock of E is changed to the mock of R.
   
If mock of R is true, that indicates that R used to be an element at
the head of a substitution group, but has been changed to be an
element with a choice among the substitutes. Changing the mock of E to
true will cause the parser to look for the elements of the choice and
will cause printSelf to print one of the choice elements.

*/

void generator::buildElementInfo()/* NO ARGUMENTS */
{
  XmlComplexType * complx;
  XmlComplexType * complx2;
  XmlSimpleType * simple;
  XmlOtherContent * other;
  XmlComplexContent * comp;
  XmlComplexExtension * extend;
  XmlSequence * sequence;
  XmlChoice * choice;
  XmlElementLocal * elementLocal;
  XmlElementRefable * elementRef;
  std::list<XmlType *>::iterator iter;
  std::list<XmlChoSeqItem *> * items;
  std::list<XmlChoSeqItem *>::iterator ator;

  for (iter = classes->begin(); iter != classes->end(); iter++)
    {
      if ((complx = dynamic_cast<XmlComplexType *>(*iter)))
	{
	  items = 0;
	  if ((other = dynamic_cast<XmlOtherContent *>(complx->item)))
	    {
	      if ((sequence = dynamic_cast<XmlSequence *>(other->base)))
		items = sequence->items;
	      else if ((choice = dynamic_cast<XmlChoice *>(other->base)))
		items = choice->items;
	    }
	  else if ((comp = dynamic_cast<XmlComplexContent *>(complx->item)))
	    {
	      if ((extend = dynamic_cast<XmlComplexExtension *>(comp->item)))
		{
		  if ((sequence = dynamic_cast<XmlSequence *>(extend->item)))
		    items = sequence->items;
		  else if ((choice = dynamic_cast<XmlChoice *>(extend->item)))
		    {
		      items = new std::list<XmlChoSeqItem *>;
		      items->push_back(choice);
		      sequence = new XmlSequence();
		      extend->item = sequence;
		      sequence->items = items;
		    }
		}
	      else if ((dynamic_cast<XmlComplexRestriction *>(comp->item)))
		{
		  fprintf(stderr, "cannot handle complex restriction\n");
		  exit(1);
		}
	      else
		{
		  fprintf(stderr, "unknown type of XML complex content item\n");
		  exit(1);
		}
	    }
	  if (!items)
	    continue;
	  for (ator = items->begin(); ator != items->end(); ator++)
	    {
	      if ((elementLocal = dynamic_cast<XmlElementLocal *>(*ator)))
		{
		  if (elementLocal->ref)
		    {
		      elementRef = findElementRefable(elementLocal->ref);
		      if (elementRef)
			{
			  if (elementRef->newTyp == 0)
			    {
			      fprintf(stderr, "ref %s has no type\n",
				      elementLocal->ref);
			      exit(1);
			    }
			  elementLocal->name = elementLocal->ref;
			  // elementLocal->newName is already set to
                          // modifyName(elementLocal->ref)
			  elementLocal->newTyp = elementRef->newTyp;
			  elementLocal->typPrefix = elementRef->typPrefix;
			  elementLocal->mock = elementRef->mock;
			}
		      else
			{
			  fprintf(stderr,
				  "no reference %s found for local element\n",
				  elementLocal->ref);
			  exit(1);
			}
		    }
		  else if (elementLocal->newTyp == 0)
		    {
		      fprintf(stderr, "element %s has no type\n",
			      elementLocal->name);
		      exit(1);
		    }
		  complx2 = findComplexClass(elementLocal->newTyp);
		  simple = findSimpleClass(elementLocal->newTyp);
		  enterElementInfo
		    (new elementInfo (elementLocal, complx2, simple));
		}
	      else if ((choice = dynamic_cast<XmlChoice *>(*ator)))
		{
		  elementLocal = new XmlElementLocal();
		  buildMockChoice(choice, elementLocal, complx->newName);
		  *ator = elementLocal;
		}
	      else if ((sequence = dynamic_cast<XmlSequence *>(*ator)))
		{
		  elementLocal = new XmlElementLocal();
		  buildMockSequence(sequence, elementLocal, complx->newName);
		  *ator = elementLocal;
		}
	      else if (dynamic_cast<XmlElementGroupRef *>(*ator))
		{
		  fprintf(stderr, "buildElementInfo cannot handle"
			  " element group reference\n");
		  exit(1);
		}

	    }
	}
    }
}

/********************************************************************/

/* generator::buildExtensions

Returned Value: none

Called By: generator::printLex

This builds the extensionInfos std::list by traversing the
elementInfos std::list and calling buildSomeExtensions for each
elementInfo on the std::list.

*/

void generator::buildExtensions() /* NO ARGUMENTS */
{
  std::list<elementInfo *>::iterator iter;

  for (iter = elementInfos->begin(); iter != elementInfos->end(); iter++)
    {
      if ((*iter)->complexType)
	buildSomeExtensions((*iter)->complexType);
    }
}

/********************************************************************/

/* generator::buildMockChoice

Returned Value: none

Called By: generator::buildElementInfo

This makes a complexType called choiceType:
1. The name and newName are set to xxx_n_Type where xxx is the
   given baseName, and n is the current value of the global mockCount.
2. The item is set to a new XmlOtherContent whose base is the given choice.

This also adds information to the given mockElement:
1. The name and newName of are set to xxx_n where xxx is the
   given baseName, and n is the current value of the global mockCount.
2. type is set to xxx_n_Type
3. mock is set to true.

An elementInfo is entered into elementInfos for the mockElement.

The choiceType is put at the end of the classes list so that its
elements will be processed. This function is called while classes are
being processed, so it is necessary to put new classes at the end of
the classes list, not in alphabetical order. When the new class is
processed, if it has choices or sequences, they will be processed in
recursive fashion.

The baseName should be the newName of the parent type of the choice.

To prevent names from becoming too long, this uses only the first 15
characters of the baseName. 

*/

void generator::buildMockChoice( /* ARGUMENTS                                */
 XmlChoice * choice,             /* choice from which to make complexType    */
 XmlElementLocal * mockElement,  /* mock element - type will be choiceType   */
 char * baseName)                /* base of names of choice type and element */
{
  char typeName[NAMESIZE];
  char elementName[NAMESIZE];
  XmlComplexType * choiceType;
  XmlOtherContent * other;

  if (strlen(baseName) > 15)
    {
      snprintf(typeName, 16, "%s", baseName);
      snprintf(typeName+15, (NAMESIZE - 15), "_%d_Type", *mockCount);
      snprintf(elementName, 16, "%s", baseName);
      snprintf(elementName+15, (NAMESIZE - 15), "_%d", *mockCount);
    }
  else
    {
      snprintf(typeName, NAMESIZE, "%s_%d_Type", baseName, *mockCount);
      snprintf(elementName, NAMESIZE, "%s_%d", baseName, *mockCount);
    }
  choice->mock = true;
  choiceType = new XmlComplexType();
  choiceType->name = strdup(typeName);
  choiceType->newName = choiceType->name;
  other = new XmlOtherContent();
  choiceType->item = other;
  other->base = choice;
  classes->push_back(choiceType);
  (*allComplex)[choiceType->newName] = choiceType;
  
  mockElement->typ = choiceType->name;
  mockElement->newTyp = mockElement->typ;
  mockElement->name = strdup(elementName);
  mockElement->newName = mockElement->name;
  mockElement->mock = true;
  enterElementInfo(new elementInfo (mockElement, choiceType, 0));

  (*mockCount)++;
}

/********************************************************************/

/* generator::buildMockSequence

Returned Value: none

Called By: generator::buildElementInfo

This makes a complexType called sequenceType:
1. The name and newName are set to xxx_n_Type where xxx is the
   given baseName, and n is the current value of the global mockCount.
2. The item is set to a new XmlOtherContent whose base is the given sequence.

This also adds information to the given mockElement:
1. The name and newName of are set to xxx_n where xxx is the
   given baseName, and n is the current value of the global mockCount.
2. type is set to xxx_n_Type
3. mock is set to true.

An elementInfo is entered into elementInfos for the mockElement.

The sequenceType is put at the end of the classes list so that its
elements will be processed. This function is called while classes are
being processed, so it is necessary to put new classes at the end of
the classes list, not in alphabetical order. When the new class is
processed, if it has choices or sequences, they will be processed in
recursive fashion.

The baseName should be the newName of the parent type of the sequence.

To prevent names from becoming too long, this uses only the first 15
characters of the baseName. 

Unlike choice, the sequence does not need a mock C++ field.

*/

void generator::buildMockSequence( /* ARGUMENTS                              */
 XmlSequence * sequence,         /* sequence from which to make complexType  */
 XmlElementLocal * mockElement,  /* mock element - type will be sequenceType */
 char * baseName)                /* base of names of sequence type & element */
{
  char typeName[NAMESIZE];
  char elementName[NAMESIZE];
  XmlComplexType * sequenceType;
  XmlOtherContent * other;

  if (strlen(baseName) > 15)
    {
      snprintf(typeName, 16, "%s", baseName);
      snprintf(typeName+15, (NAMESIZE - 15), "_%d_Type", *mockCount);
      snprintf(elementName, 16, "%s", baseName);
      snprintf(elementName+15, (NAMESIZE - 15), "_%d", *mockCount);
    }
  else
    {
      snprintf(typeName, NAMESIZE, "%s_%d_Type", baseName, *mockCount);
      snprintf(elementName, NAMESIZE, "%s_%d", baseName, *mockCount);
    }
  sequenceType = new XmlComplexType();
  sequenceType->name = strdup(typeName);
  sequenceType->newName = sequenceType->name;
  other = new XmlOtherContent();
  sequenceType->item = other;
  other->base = sequence;
  classes->push_back(sequenceType);
  (*allComplex)[sequenceType->newName] = sequenceType;
  
  mockElement->typ = sequenceType->name;
  mockElement->newTyp = mockElement->typ;
  mockElement->name = strdup(elementName);
  mockElement->newName = mockElement->name;
  mockElement->mock = true;
  enterElementInfo(new elementInfo (mockElement, sequenceType, 0));

  (*mockCount)++;
}


/********************************************************************/

/* generator::buildSomeExtensions

Returned Value: none

Called By: generator::buildExtensions

This builds the extensionInfos std::list by calling enterName for each
XmlComplexType on the extensions std::list of typ.

*/

void generator::buildSomeExtensions( /* ARGUMENTS                        */
 XmlComplexType * typ)               /* a type that extends another type */
{
  std::list<XmlComplexType *>::iterator iter;

  if (typ->extensions == 0)
    return;
  for (iter = typ->extensions->begin(); iter != typ->extensions->end(); iter++)
    {
      enterName((*iter)->name, extensionInfos);
    }
}

/********************************************************************/

/* generator::buildYaccBasicRule

Returned Value: none

Called By: generator::buildYaccRulesEnd

This builds the production-name / subrule pairs for a rule for
an element whose value is an XML basic type. The rules for the
basic types themselves are each printed by a separate printYaccRules<type>
function, which is called only if the type is used in the schema.

Example - This might build the namePair from which is printed:

y_Street :
	  STREETSTART ENDITEM {yyReadData = 1;} y_XmlString STREETEND
	  {$$ = $4;}
	;

If the element can be omitted (i.e. minOccurs = 0), this prints a
second pair with the same production name, which sets the $$ return value
to a null pointer if the element is omitted.

*/

void generator::buildYaccBasicRule( /* ARGUMENTS                      */
 char * prodName,                   /* name of production without y_  */
 char * elementName,                /* newName of element             */
 char * typ,                        /* name of type of element        */
 bool emptyOk,                      /* true=optional, false=not       */
 std::list<namePair *> * endRules)  /* rules to build                 */
{
  static char buffer[NAMESIZE];
  static char typName[NAMESIZE];
  int n;
  
  if (emptyOk)
    {
      n = sprintf(text, "/* empty */%c", 13);
      sprintf(text+n, "\t  {$$ = 0;}\n");
      enterNamePair(strdup(prodName), strdup(text), endRules);
    }
  allCaps(elementName, buffer);
  findCppTypeName(typ, typName);
  setHas(XmlCppBase::wg3Prefix, typ);
  n = sprintf(text, "%sSTART ENDITEM {%sReadData = 1;} y_%s %sEND%c",
	      buffer, yyprefix, typName, buffer, 13);
  sprintf(text+n, "\t  {$$ = $4;}\n");
  enterNamePair(strdup(prodName), strdup(text), endRules);
}

/********************************************************************/

/* generator::buildYaccChoiceRule

Returned Value: none

Called By: generator::buildYaccComplexTypeRule

If a complex type is a choice and maxOccurs is unbounded, a std::list is
required. There is no printSelf function for a std::list. To deal with
that, in addition to writing a production for the choice type, this
writes a production for a YACC list of the choice type and a list holder
production. In the C++ classes, a YACC list holder class is defined that
has a printSelf function.

Example: This might build namePairs for y_CustomItemsType, from which
the following is printed. The list holder here is y_CustomItemsType.
The size of the list may be checked in the list holder.

y_CustomItemsTypeChoicePair :
	  y_DoubleItem_DoubleItemType
	  {$$ = new CustomItemsTypeChoicePair();
	   $$->CustomItemsTypeType = CustomItemsTypeChoicePair::DoubleItemE;
	   $$->CustomItemsTypeValue.DoubleItem = $1;
	  }
	| y_IntItem_IntItemType
	  {$$ = new CustomItemsTypeChoicePair();
	   $$->CustomItemsTypeType = CustomItemsTypeChoicePair::IntItemE;
	   $$->CustomItemsTypeValue.IntItem = $1;
	  }
	| y_StringItem_StringItemType
	  {$$ = new CustomItemsTypeChoicePair();
	   $$->CustomItemsTypeType = CustomItemsTypeChoicePair::StringItemE;
	   $$->CustomItemsTypeValue.StringItem = $1;
	  }
	;

y_ListCustomItemsTypeChoicePair :
	  y_CustomItemsTypeChoicePair
	  {$$ = new std::list<CustomItemsTypeChoicePair *>;
	   $$->push_back($1);
	  }
	| y_ListCustomItemsTypeChoicePair y_CustomItemsTypeChoicePair
	  {$$ = $1;
	   $$->push_back($2);
	  }
	;

y_CustomItemsType :
	  ENDITEM y_ListCustomItemsTypeChoicePair
	  {$$ = new CustomItemsType($2);
           if ($2->size() > 3)
             yyerror("only 3 choices are allowed");
           if ($2->size() < 1)
             yyerror("at least 1 choice is required");
          }
	;

The rule for recognizing a mock choice differs from the rule for
recognizing a real choice because in an instance file, the real choice
will be preceded by an element name followed by possibly some
attribute values followed by ENDITEM (which is ">"). The ENDITEM part
of that is first thing in the rule for a real choice. The mock choice
will not have the ENDITEM.

When this is called the process of un-nesting choices and sequences
will have resulted in all items in the choice being elements, so the
check for non-elements should never find one.

FIX - This is not currently handling attributes of the parent of the choice.

*/

void generator::buildYaccChoiceRule( /* ARGUMENTS                      */
 XmlChoice * choice,                 /* choice to print rule for       */
 char * name,                        /* newName of complex with choice */
 std::list<namePair *> * endRules)   /* rules to build                 */
{
  static char prodName[NAMESIZE];
  std::list<XmlChoSeqItem *> * items;
  std::list<XmlChoSeqItem *>::iterator iter;
  XmlElementLocal * element;
  int maxi;
  int mini;
  int n;
  
  maxi = ((choice->maxOccurs < -1) ? 1 : choice->maxOccurs);
  mini = ((choice->minOccurs <  0) ? 1 : choice->minOccurs);
  if ((maxi != -1) && (maxi < mini))
    {
      fprintf(stderr,
	      "maxOccurs, %d, must not be less than minOccurs, %d\n",
	      maxi, mini);
      exit(1);
    }
  items = choice->items;
  sprintf(prodName, "%sChoicePair", name);
  for (iter = items->begin(); iter != items->end(); iter++)
    {
      element = dynamic_cast<XmlElementLocal *>(*iter);
      if (element == 0)
	{
	  fprintf(stderr, "choice item must be an element\n");
	  exit(1);
	}
      buildYaccChoiceItemRule(element, name, prodName, endRules);
    }
  sprintf(prodName, "List%sChoicePair", name);
  n = sprintf(text, "y_%sChoicePair%c", name, 13);
  n += sprintf(text+n, "\t  {$$ = new std::list<%sChoicePair *>;\n", name);
  n += sprintf(text+n, "\t   $$->push_back($1);\n");
  sprintf(text+n, "\t  }\n");
  enterNamePair(strdup(prodName), strdup(text), endRules);
  n = sprintf(text, "y_List%sChoicePair y_%sChoicePair%c", name, name, 13);
  n += sprintf(text+n, "\t  {$$ = $1;\n");
  n += sprintf(text+n, "\t   $$->push_back($2);\n");
  sprintf(text+n, "\t  }\n");
  enterNamePair(strdup(prodName), strdup(text), endRules);

  if (choice->mock)
    {
      n = sprintf(text, "y_List%sChoicePair%c", name, 13);
      n += sprintf(text+n, "\t  {$$ = new %s($1);\n", name);
      if (maxi != -1)
	{
	  n += sprintf(text+n, "\t   if ($1->size() > %d)\n", maxi);
	  n += sprintf(text+n,
		       "\t     %serror(\"only %d choice%s allowed\");\n",
		       yyprefix, maxi, ((maxi > 1) ? "s are" : " is"));
	}
      n += sprintf(text+n, "\t   if ($1->size() < %d)\n", mini);
    }
  else
    {
      n = sprintf(text, "ENDITEM y_List%sChoicePair%c", name, 13);
      n += sprintf(text+n, "\t  {$$ = new %s($2);\n", name);
      if (maxi != -1)
	{
	  n += sprintf(text+n, "\t   if ($2->size() > %d)\n", maxi);
	  n += sprintf(text+n,
		       "\t     %serror(\"only %d choice%s allowed\");\n",
		       yyprefix, maxi, ((maxi > 1) ? "s are" : " is"));
	}
      n += sprintf(text+n, "\t   if ($2->size() < %d)\n", mini);
    }
  n += sprintf(text+n, "\t     %serror(\"at least %d choice%s required\");\n",
	       yyprefix, mini, ((mini > 1) ? "s are" : " is"));
  sprintf(text+n, "\t  }\n");
  sprintf(prodName, "%s", name);
  enterNamePair(strdup(prodName), strdup(text), endRules);
}

/********************************************************************/

/* generator::buildYaccChoiceItemRule

Returned Value: none

Called By: generator::buildYaccChoiceRule

This builds a YACC production that recognizes an element or a list of
elements with the same name in a choice and makes a new ChoicePair
with what it recognizes. If a maximum number of occurrences of the
element has been specified that is not 1 (in which case a list will
have been used), this also writes code that checks the maximum and
minimum number of elements in the list.

This does not allow choice items to be optional (i.e., have minOccurs
be zero) since that will cause ambiguous parsing, which is not allowed.

*/

void generator::buildYaccChoiceItemRule( /* ARGUMENTS                      */
 XmlElementLocal * element,              /* element to write rule for      */
 char * name,                            /* newName of complex with choice */
 char * prodName,                        /* production name of rule        */
 std::list<namePair *> * endRules)       /*  rules to build                */
{
  int maxi;
  int mini;
  int n;

  maxi = ((element->maxOccurs < -1) ? 1 : element->maxOccurs);
  mini = ((element->minOccurs <  0) ? 1 : element->minOccurs);
  if ((maxi != -1) && (maxi < mini))
    {
      fprintf(stderr,
	      "maxOccurs, %d, must not be less than minOccurs, %d\n",
	      maxi, mini);
      exit(1);
    }
  if (mini == 0)
    {
      fprintf(stderr, "choice item may not be optional\n");
      exit(1);
    }
  if (element->needList)
    {
      n = sprintf(text, "y_List%s%s%c", element->prodBase,
		  (((maxi != 1) || (mini > 1)) ? "_Check" : ""), 13);
    }
  else
    n = sprintf(text, "y_%s%c", element->prodBase, 13);
  n += sprintf(text+n, "\t  {$$ = new %sChoicePair();\n", name);
  n += sprintf(text+n, "\t   $$->%sType = %sChoicePair::%sE;\n",
	       name, name, element->newName);
  n += sprintf(text+n, "\t   $$->%sValue.%s = $1;\n",
	       name, element->newName);
  if (maxi != 1)
    {
      n += sprintf(text+n, "\t   if ($1->size() > %d)\n", maxi);
      n += sprintf(text+n, "\t     yyerror(\"only %d %ss are allowed\");\n",
		   maxi, element->newName);
      n += sprintf(text+n, "\t   if ($1->size() < %d)\n", mini);
      n += sprintf(text+n,
		   "\t     yyerror(\"at least %d %ss are required\");\n",
		   mini, element->newName);
    }
  sprintf(text+n, "\t  }\n");
  enterNamePair(strdup(prodName), strdup(text), endRules);
}

/********************************************************************/

/* generator::buildYaccComplexAnyRule

Returned Value: none

Called By: generator::buildYaccRulesEnd

If an XML type T has subtypes, any of the subtypes can be used where
the value of an element is of type T. Thus, the definition of the rule
for the element must include the production name of a rule P that
represents a choice of T or any of its subtypes, and P must be
generated.  This function generates P. The production name of the rule
that is generated always ends in "Any". The production names of the
rules for the subtypes always start with y_x_ .

Example - This might build the namePairs from which the following rule
is printed. UK_Address and US_Address are both subtypes of
AddressType, and AddressType is not abstract. If AddressType were
abstract, y_AddressType would not be included in the alternatives, since
an AddressType that is not a UK_Address or a US_Address could not appear
in a file.

y_AddressTypeAny :
	  y_AddressType
	  {$$ = $1;}
	| y_x_UK_Address
	  {$$ = $1;}
	| y_x_US_Address
	  {$$ = $1;}
	;

Note that although the base of the production name (AddressTypeAny in
the example) is entered in each pair, pairs with the same production
name get combined into a single rule when the pairs are printed.

The y_x_ rules for subtypes used in place of a parent type look for
'xsi:type="xxx"', which is required in that situation.  When the subtype
is used directly, the y_ rule does not look for 'xsi:type="xxx"'. It is
quite possible that both forms of the rule for a subtype (y_x_US_Address
and y_US_Address) will be used in a YACC file.

*/

void generator::buildYaccComplexAnyRule(   /* ARGUMENTS                 */
 char * newTyp,                            /* new name of parent type   */
 std::list<XmlComplexType *> * extensions, /* extensions of parent type */
 std::list<namePair *> * endRules)         /* rules to build            */
{
  std::list<XmlComplexType *>::iterator iter;
  static char buffer[NAMESIZE];
  XmlComplexType * complx;
  int n;

  sprintf(buffer, "%sAny", newTyp);
  complx = findComplexClass(newTyp);
  if (complx == 0)
    {
      fprintf(stderr, "type %s not found in buildYaccComplexAnyRule\n",
	      newTyp);
      exit(1);
    }
  if (complx->abstract != XmlCppBase::yes)
    { // put parent class at the head of the list unless it is abstract
      n = sprintf(text, "y_%s%c", newTyp, 13);
      sprintf(text+n, "\t  {$$ = $1;}\n");
      enterNamePair(strdup(buffer), strdup(text), endRules);
    }
  for (iter = extensions->begin(); iter != extensions->end(); iter++)
    { // put derived class in the list if not abstract
      if ((*iter)->abstract != XmlCppBase::yes)
	{
	  n = sprintf(text, "y_x_%s%c", (*iter)->newName, 13);
	  sprintf(text+n, "\t  {$$ = $1;}\n");
	  enterNamePair(strdup(buffer), strdup(text), endRules);
	}
    }
}

/********************************************************************/

/* generator::buildYaccComplexElementRule

Returned Value: none

Called By: generator::buildYaccRulesEnd

This builds a namePair for a rule for an element whose type is an
XmlComplexType. If the element may be omitted (i.e., minOccurs = 0),
a second name pair is built that sets the return value to a null
pointer.

If the type of the element has XmlOtherContent but neither a sequence
nor a choice, so that an instance may not have an end tag, for example
<Gizmo id="ID1"/>, then this also builds a namePair for a rule that
recognizes XXX_Whole.

Example 1 - This might build the namePair for a non-top element from
which is printed: 

y_Company_CompanyType :
	  COMPANYSTART y_CompanyType COMPANYEND
	  {$$ = $2;}
	;

Example 2 - This might build two namePairs for a non-top element from
which is printed:

y_AltAddress_UK_Address_0 :
	  / * empty * /
	  {$$ = 0;}
	| ALTADDRESSSTART y_UK_Address ALTADDRESSEND
	  {$$ = $2;}
	;

Example 3 - This might build two namePairs for a non-top element from
which is printed:

y_Gizmo_GizmoType_u :
	  GIZMOSTART y_GizmoType GIZMOEND
	  {$$ = $2;}
	| GIZMOSTART y_GizmoType_Whole
	  {$$ = $2;}
	;

*/

void generator::buildYaccComplexElementRule( /* ARGUMENTS                  */
 XmlComplexType * complx,             /* complex type of element           */
 char * prodName,                     /* name of production without y_     */
 char * elementName,                  /* newName of element                */
 bool emptyOk,                        /* true=list may be empty, false=not */
 bool mock,                           /* true=mock element, false=not      */
 std::list<namePair *> * endRules)    /* rules to build                    */
{
  static char buffer[NAMESIZE];
  XmlOtherContent * other;           // other content item in complx
  int n;

  if (emptyOk)
    {
      n = sprintf(text, "/* empty */%c", 13);
      sprintf(text+n, "\t  {$$ = 0;}\n");
      enterNamePair(strdup(prodName), strdup(text), endRules);
    }
  allCaps(elementName, buffer);
  if (mock)
    {
      n = sprintf(text, "y_%s%c", complx->newName, 13);
      sprintf(text+n, "\t  {$$ = $1;}\n");
    }
  else
    {
      n = sprintf(text, "%sSTART y_%s %sEND%c",
		  buffer, complx->newName, buffer, 13);
      sprintf(text+n, "\t  {$$ = $2;}\n");
    }
  enterNamePair(strdup(prodName), strdup(text), endRules);
  if ((other = dynamic_cast<XmlOtherContent *>(complx->item)) &&
      (dynamic_cast<XmlSequence *>(other->base) == 0) &&
      (dynamic_cast<XmlChoice *>(other->base) == 0))
    {
      n = sprintf(text, "%sSTART y_%s_Whole%c",
		  buffer, complx->newName, 13);
      sprintf(text+n, "\t  {$$ = $2;}\n");
      enterNamePair(strdup(prodName), strdup(text), endRules);
      wholeFlag = true;
    }

}

/********************************************************************/

/* generator::buildYaccComplexExtRule

Returned Value: none

Called By: generator::buildYaccRulesEnd

This builds a namePair for a rule for an element whose type has
subtypes.  The rule that gets built is simple. All the rule does is
say to look for the production name (y_AddressTypeAny in the example
below) that represents a rule that gives the type and its subtypes
as alternatives.

Example 1 - This might build the namePair from which is printed:

y_Address_AddressType :
	  ADDRESSSTART y_AddressTypeAny ADDRESSEND
	  {$$ = $2;}
	;

Example 2 - This might build two namePairs from which is printed
            (without the extra space in the comment markers):

y_InternalShape_InternalShapeType_0 :
	  / * empty * /
	  {$$ = 0;}
	| INTERNALSHAPESTART y_InternalShapeTypeAny INTERNALSHAPEEND
	  {$$ = $2;}
	;

*/

void generator::buildYaccComplexExtRule( /* ARGUMENTS                         */
 char * prodName,                        /* name of production without y_     */
 char * elementName,                     /* newName of element                */
 char * typ,                             /* new name of type of element       */
 bool emptyOk,                           /* true=list may be empty, false=not */
 std::list<namePair *> * endRules)       /* rules to build                    */
{
  static char buffer[NAMESIZE];
  int n;
  
  if (emptyOk)
    {
      n = sprintf(text, "/* empty */%c", 13);
      sprintf(text+n, "\t  {$$ = 0;}\n");
      enterNamePair(strdup(prodName), strdup(text), endRules);
    }
  allCaps(elementName, buffer);
  n = sprintf(text, "%sSTART y_%sAny %sEND%c", buffer, typ, buffer, 13);
  sprintf(text+n, "\t  {$$ = $2;}\n");
  enterNamePair(strdup(prodName), strdup(text), endRules);
}

/********************************************************************/

/* generator::buildYaccComplexTypeRule

Returned Value: none

Called By: generator::buildYaccRulesEnd

This builds a namePair representing a rule for an XmlComplexType.
This is called only if the XmlComplexType is the type of an element.
The item in the XmlComplexType may be
 - an XmlOtherContent
 - an XmlComplexContent (which is usually an extension)
 - an XmlSimpleContent

An XmlOtherContent is allowed to have no sequence or choice. This will
be the case if (1) it is something like Stop, which requires nothing
further or (2) it is an abstract parent type with no elements.

FIX - Implement attributes with choice. This will not be simple.
See buildYaccSequenceRule

*/

void generator::buildYaccComplexTypeRule(/* ARGUMENTS                      */
 XmlComplexType * complx,                /* complex type to build rule for */
 std::list<namePair *> * endRules)       /* rules to build                 */
{
  XmlOtherContent * other;           // other content item in complx
  XmlComplexContent * comp;          // complex content item in complx
  XmlSimpleContent * simp;           // simple content item in complx
  XmlSequence * sequence;            // sequence in other
  XmlChoice * choice;                // choice in other
  XmlComplexExtension * extend;      // extension item in comp
  XmlComplexRestriction * restrict;  // restriction item in comp

  if ((other = dynamic_cast<XmlOtherContent *>(complx->item)))
    {
      if ((sequence = dynamic_cast<XmlSequence *>(other->base)))
	{
	  buildYaccSequenceRule(sequence, complx->newName,
				other->newAttribs, endRules);
	}
      else if ((choice = dynamic_cast<XmlChoice *>(other->base)))
	{
	  if (other->newAttribs)
	    {
	      fprintf(stderr, "cannot handle attributes with choice\n");
	      exit(1);
	    }
	  buildYaccChoiceRule(choice, complx->newName, endRules);
	}
      else
	buildYaccEmptyRule(complx->newName, other->newAttribs, endRules);
    }
  else if ((comp = dynamic_cast<XmlComplexContent *>(complx->item)))
    {
      if ((extend = dynamic_cast<XmlComplexExtension *>(comp->item)))
	{
	  buildYaccExtensionRule1(complx, extend, endRules);
	}
      else if ((restrict = dynamic_cast<XmlComplexRestriction *>(comp->item)))
	{
	  fprintf(stderr, "cannot handle complex restriction\n");
	  exit(1);
	}
      else
	{
	  fprintf(stderr, "unknown type of XML complex content item\n");
	  exit(1);
	}
    }
  else if ((simp = dynamic_cast<XmlSimpleContent *>(complx->item)))
    {
      buildYaccSimpleContentRule(complx->name, complx->newName, simp, endRules);
    }
  else
    {
      fprintf(stderr, "bad subtype of XML complex type %s\n", complx->name);
      exit(1);
    }
}

/********************************************************************/

/* generator::buildYaccElementListRule

Returned Value: none

Called By: generator::buildYaccRulesEnd

This builds:

1. two namePairs for representing a rule for an XmlElementLocal
(element) that can occur more than once. If the element is optional,
the std::list will not be null but it may be empty.

2. If needed, one namePair for representing a rule that checks the
size of the list against its minOccurs and maxOccurs. The minOccurs
check is not generated if minOccurs is 0 or 1. In the case of minOccurs
being 1, a parse error will be given if the element does not occur.
The maxOccurs check is not included if maxOccurs is unbounded.

Example 1 - For an element "Nicknames" that may occur zero times and may not
occur more than 3 times, this might build pairs from which the following
rules are printed. (The empty rule does not have the extra spaces in
/ * and * / but they are needed here since this is a C++ comment.):

y_ListNickname_XmlString_2_3 :
	  / * empty * /
	  {$$ = new std::list<char *>;}
	| y_ListNickname_XmlString_2_3 y_Nickname_XmlString_2_3
	  {$$ = $1;
	   $$->push_back($2);}
	;

y_ListNickname_XmlString_3_Check :
          y_ListNickname_XmlString_2_3
          {$$ = $1;
           if ($1->size() > 3)
             yyerror("must not be more than 3 Nicknames");
           if ($1->size() < 2)
	      yyerror("must be at least 2 Nicknames");
          }

Example 2 - For an element "Person" that must occur at least once and
may occur any number of times, this might build pairs from which the
following rule is printed:

y_ListPerson_PersonType_u :
	  y_Person_PersonType_u
	  {$$ = new std::list<PersonType *>;
	   $$->push_back($1);}
	| y_ListPerson_PersonType_u y_Person_PersonType_u
	  {$$ = $1;
	   $$->push_back($2);}
	;

The _Check suffix used here is also used in buildYaccSequenceItems.

*/

void generator::buildYaccElementListRule( /* ARGUMENTS                     */
 char * prodName,                         /* name of production without y_ */
 XmlElementLocal * element,               /* element to make list rule for */
 char * typ,                              /* name of type of element       */
 std::list<namePair *> * endRules)        /* rules to build                */
{
  static char buffer[NAMESIZE];
  int n;
  int mini;
  int maxi;

  mini = ((element->minOccurs < 0) ? 1 : element->minOccurs);
  maxi = ((element->maxOccurs < -1) ? 1 : element->maxOccurs);
  if ((mini == 0) || (maxi == 0))
    {
      sprintf(buffer, "List%s", prodName);
      n = sprintf(text, "/* empty */%c", 13);
      sprintf(text+n, "\t  {$$ = new std::list<%s *>;}\n", typ);
      enterNamePair(strdup(buffer), strdup(text), endRules);
    }
  else
    {
      sprintf(buffer, "List%s", prodName);
      n = sprintf(text, "y_%s%c", prodName, 13);
      n += sprintf(text+n, "\t  {$$ = new std::list<%s *>;\n", typ);
      sprintf(text+n, "\t   $$->push_back($1);}\n");
      enterNamePair(strdup(buffer), strdup(text), endRules);
    }
  n = sprintf(text, "y_%s y_%s%c", buffer, prodName, 13);
  n += sprintf(text+n, "\t  {$$ = $1;\n");
  sprintf(text+n, "\t   $$->push_back($2);}\n");
  enterNamePair(strdup(buffer), strdup(text), endRules);
  if ((mini > 1) || (maxi != -1))
    {
      sprintf(buffer, "List%s_Check", prodName);
      n = sprintf(text, "y_List%s%c", prodName, 13);
      n += sprintf(text+n, "\t  {$$ = $1;\n");
      if (maxi != -1)
	{
	  n += sprintf(text+n,
		       "\t   if ($1->size() > %d)\n", maxi);
	  n += sprintf(text+n,
		       "\t     yyerror(\"must not be more than %d", maxi);
	  n += sprintf(text+n," %s%s\");\n", element->name,
		       ((maxi > 1) ? "s" : ""));
	}
      if (mini > 1)
	{
	  n += sprintf(text+n,
		       "\t   if ($1->size() < %d)\n", mini);
	  n += sprintf(text+n,
		       "\t     yyerror(\"must be at least %d %ss\");\n",
		       mini, element->name);
	}
      sprintf(text+n, "\t  }\n");
      enterNamePair(strdup(buffer), strdup(text), endRules);
    }
}

/********************************************************************/

/* generator::buildYaccEmptyRule

Returned Value: none

Called By: buildYaccComplexTypeRule

This builds two namePairs representing a rule for an XmlComplexType containing
nothing except possibly XML attributes. Two namePairs are needed because
either of the following two forms might occur in an instance file.

      <Gizmo id="ID1"/>

      <Gizmo id="ID1">
      </Gizmo>

Example 1 - For the type of a local element with no attributes, this
might build the two namePairs from which is printed:

y_SomeType :
	  ENDITEM
          {$$ = new SomeType();}
	;

y_SomeType_Whole :
	  ENDWHOLEITEM
          {$$ = new SomeType();}
	;

Example 2 - For the type of a local element with attributes, this might build
the two namePairs from which is printed:

y_GizmoType :
	  y_ListAttributePair ENDITEM
          {$$ = new GizmoType();
	   if ($$->badAttributes($1))
	     yyerror("Bad attributes for GizmoType");
	   delete $1;
	  }
	;

y_GizmoType_Whole :
	  y_ListAttributePair ENDWHOLEITEM
	  {$$ = new GizmoType();
	   if ($$->badAttributes($1))
	     yyerror("Bad attributes for GizmoType");
	   delete $1;
	  }
	;

*/

void generator::buildYaccEmptyRule(        /* ARGUMENTS               */
 char * newName,                           /* newName of complex type */
 std::list<XmlAttributeLoner *> * attribs, /* attributes of other     */
 std::list<namePair *> * endRules)         /* rules to build          */
{
  int n;
  static char newerName[TEXTSIZE];

  sprintf(newerName, "%s_Whole", newName);
  if (attribs && attribs->size())
    {
      n = sprintf(text, "y_ListAttributePair ENDITEM%c", 13);
      n += sprintf(text+n, "\t  {$$ = new %s();\n", newName);
      n += sprintf(text+n, "\t   if ($$->badAttributes($1))\n");
      n += sprintf(text+n, "\t     %serror(\"Bad attributes for %s\");\n",
		   yyprefix, newName);
      n += sprintf(text+n, "\t   delete $1;\n");
      sprintf(text+n, "\t  }\n");
      enterNamePair(strdup(newName), strdup(text), endRules);
      n = sprintf(text, "y_ListAttributePair ENDWHOLEITEM%c", 13);
      n += sprintf(text+n, "\t  {$$ = new %s();\n", newName);
      n += sprintf(text+n, "\t   if ($$->badAttributes($1))\n");
      n += sprintf(text+n, "\t     %serror(\"Bad attributes for %s\");\n",
		   yyprefix, newName);
      n += sprintf(text+n, "\t   delete $1;\n");
      sprintf(text+n, "\t  }\n");
      enterNamePair(strdup(newerName), strdup(text), endRules);
    }
  else
    {
      n = sprintf(text, "ENDITEM%c", 13);
      sprintf(text+n, "\t  {$$ = new %s();}\n", newName);
      enterNamePair(strdup(newName), strdup(text), endRules);
      n = sprintf(text, "ENDWHOLEITEM%c", 13);
      sprintf(text+n, "\t  {$$ = new %s();}\n", newName);
      enterNamePair(strdup(newerName), strdup(text), endRules);
    }
}

/********************************************************************/

/* generator::buildYaccExtensionBaseArgs

Returned Value: none

Called By:
  generator::buildYaccExtensionBaseArgs (recursively)
  generator::buildYaccExtensionRule1
  generator::buildYaccExtensionRule2

This prints in the text array the constructor arguments
for an extension that exist because the base type has them.

This calls itself recursively to handle extension depths greater than one.

Example - If US_Address is an extension of Address, and Address has
Name, Street, and City elements, this might print the following in the
text array used in printing the rule for US_Address:

 $3, $4, $5

If there are attributes of the base, they are not included as
arguments to the constructor since the generator uses the constructors
that do not take arguments corresponding to attributes.

*/

void generator::buildYaccExtensionBaseArgs( /* ARGUMENTS                     */
 char * baseName,                           /* name of base class            */
 int * n,                                   /* index for text                */
 int * index,                               /* index of argument in arg list */
 int * comma)                               /* 1=print comma & space 0=don't */
{
  XmlComplexType * complx;
  XmlOtherContent * other;
  XmlComplexContent * comp;
  XmlComplexExtension * extend;
  XmlSequence * sequence;
  std::list<XmlChoSeqItem *> * items;
  std::list<XmlChoSeqItem *>::iterator iter;

  complx = findComplexClass(baseName);
  if (complx == 0)
    {
      fprintf(stderr,
	      "base class %s not found in buildYaccExtensionBaseArgs\n",
	      baseName);
      exit(1);
    }
  if ((other = dynamic_cast<XmlOtherContent *>(complx->item)) &&
      (sequence = dynamic_cast<XmlSequence *>(other->base)))
    {
      items = sequence->items;
      for (iter = items->begin(); iter != items->end(); iter++)
	{
	  *n += sprintf((text + *n), "%s$%d", (*comma ? ", " : ""), (*index)++);
	  *comma = 1;
	}
    }
  else if ((comp = dynamic_cast<XmlComplexContent *>(complx->item)))
    {
      if ((extend = dynamic_cast<XmlComplexExtension *>(comp->item)))
	{
	  buildYaccExtensionBaseArgs(extend->base, n, index, comma);
	  if ((sequence = dynamic_cast<XmlSequence *>(extend->item)))
	    {
	      items = sequence->items;
	      for (iter = items->begin(); iter != items->end(); iter++)
		{
		  *n += sprintf((text + *n), "%s$%d", (*comma ? ", " : ""),
				(*index)++);
		  *comma = 1;
		}
	    }
	}
      else if ((dynamic_cast<XmlComplexRestriction *>(comp->item)))
	{
	  fprintf(stderr, "cannot handle complex restriction\n");
	}
    }
}

/********************************************************************/

/* generator::buildYaccExtensionBaseItems

Returned Value: none

Called By:
  generator::buildYaccExtensionBaseItems (recursively)
  generator::buildYaccExtensionRule1
  generator::buildYaccExtensionRule2

This prints in the text array the items in the definition of a
rule for an extension that exist because the base type has them.

This calls itself recursively to handle extension depths greater than one.

Example - If US_Address is an extension of Address, and Address has
Name, Street, and City elements, this might print the following in the
text array used in printing the rule for US_Address:

 y_Name y_Street y_City

*/

void generator::buildYaccExtensionBaseItems( /* ARGUMENTS          */
 char * baseName,                            /* name of base class */
 int * n)                                    /* index for text     */
{
  XmlComplexType * complx;
  XmlOtherContent * other;
  XmlComplexContent * comp;
  XmlComplexExtension * extend;
  XmlSequence * sequence;

  complx = findComplexClass(baseName);
  if (complx == 0)
    {
      fprintf(stderr,
	      "base class %s not found in buildYaccExtensionBaseItems\n",
	      baseName);
      exit(1);
    }
  if ((other = dynamic_cast<XmlOtherContent *>(complx->item)))
    {
      if ((sequence = dynamic_cast<XmlSequence *>(other->base)))
	{
	  buildYaccExtensionSequenceItems(sequence->items, n);
	}
    }
  else if ((comp = dynamic_cast<XmlComplexContent *>(complx->item)))
    {
      if ((extend = dynamic_cast<XmlComplexExtension *>(comp->item)))
	{
	  buildYaccExtensionBaseItems(extend->base, n);
	  if ((sequence = dynamic_cast<XmlSequence *>(extend->item)))
	    {
	      buildYaccExtensionSequenceItems(sequence->items, n);
	    }
	}
      else if ((dynamic_cast<XmlComplexRestriction *>(comp->item)))
	{
	  fprintf(stderr, "cannot handle complex restriction\n");
	}
    }
}

/********************************************************************/

/* generator::buildYaccExtensionRule1

Returned Value: none

Called By: generator::buildYaccComplexTypeRule

This is called if a type that is an extension of another type is used
as the type of an element. It builds a rule definition and an action.

*/

void generator::buildYaccExtensionRule1( /* ARGUMENTS            */
 XmlComplexType * complx,                /* extension of parent  */
 XmlComplexExtension * extend,           /* extension in complx  */
 std::list<namePair *> * endRules)       /* rules to build       */
{
  int n;                         // index for text
  int k;                         // counter for definition items
  static char buffer[NAMESIZE];  // buffer for building names
  XmlSequence * sequence;        // sequence in extend
  int index;                     // index for definition items
  int numItems;                  // number of items in sequence
  int comma = 0;                 // 1=print comma, 0=don't
  bool hasAttributes;            // true=complx or parent has attributes

  sequence = dynamic_cast<XmlSequence *>(extend->item);
  allCaps(complx->newName, buffer);
  hasAttributes = ((extend->attribs) || hasAttribs(extend->base));
  if (hasAttributes)
    {
      n = sprintf(text, " y_ListAttributePair");
      index = 3;
    }
  else
    {
      n = 0;
      index = 2;
    }
  n += sprintf(text+n, " ENDITEM");
  buildYaccExtensionBaseItems(extend->base, &n);
  if (sequence)
    {
      buildYaccExtensionSequenceItems(sequence->items, &n);
    }
  n += sprintf(text+n, "%c", 13);
  n += sprintf(text+n, "\t  {$$ = new %s(", complx->newName);
  buildYaccExtensionBaseArgs(extend->base, &n, &index, &comma);
  numItems = (sequence ? sequence->items->size() : 0);
  for (k = 0; k < numItems; k++)
    {
      n += sprintf(text+n, "%s$%d", (comma ? ", " : ""), index++);
      comma = 1;
    }
  n += sprintf(text+n, ");");
  if (hasAttributes)
    {
      n += sprintf(text+n, "\n");
      n += sprintf(text+n, "\t   if ($$->badAttributes($1))\n");
      n += sprintf(text+n, "\t     %serror(\"Bad attributes for %s\");\n",
		   yyprefix, complx->newName);
      n += sprintf(text+n, "\t   delete $1;\n");
      sprintf(text+n, "\t  }\n");
    }
  else
    sprintf(text+n, "}\n");
  enterNamePair(strdup(complx->newName), strdup(text), endRules);
}

/********************************************************************/

/* generator::buildYaccExtensionRule2

Returned Value: none

Called By: generator::buildYaccExtensionRules

This builds a rule definition and an action for an XmlComplexType
that extends another XmlComplexType.

If there are attributes of the extension or its parent, all of them
are included in the definition by using using the y_ListAttributePair
item. They are not included as arguments to the constructor since the
generator uses the constructors that do not take arguments corresponding
to attributes. The C++ fields representing XML attributes are set
when the badAttributes function runs (which happens when the badAttributes
function for the class is called in the YACC file immediately after the
constructor runs).

The elements of the extension and its parent are included in the definition
and in the arguments to the constructor, where they are represented by
numbers indicating their position in the definition.

The production definition this builds requires that the type declaration
precede any other attributes. That may or may not be too limiting.

Example - If US_Address is an extension of AddressType and AddressType
is the type of an element, this might build the namePair used to print
the following rule:

y_x_UK_Address :
	  UK_ADDRESSDECL ENDITEM y_Name y_Street y_City y_Postcode
	  {$$ = new UK_Address($3, $4, $5, $6);}
	;

*/

void generator::buildYaccExtensionRule2( /* ARGUMENTS                       */
 XmlComplexType * complx,                /* extension of parent             */
 std::list<namePair *> * endRules)       /* rules to build                  */
{
  int n;                         // index for text
  int k;                         // counter for definition items
  static char buffer[NAMESIZE];  // buffer for building names
  XmlComplexContent * comp;      // complex content item in complx
  XmlComplexExtension * extend;  // extension item in comp
  XmlSequence * sequence;        // sequence in extend
  int index;                     // index for definition items
  int numItems;                  // number of items in sequence
  int comma = 0;                 // 1=print comma, 0=don't
  bool hasAttributes;            // true=complx or parent has attributes

  comp = dynamic_cast<XmlComplexContent *>(complx->item);
  if (comp == 0)
    {
      fprintf(stderr, "bug 1 in buildYaccExtensionRule2\n");
      exit(1);
    }
  extend = dynamic_cast<XmlComplexExtension *>(comp->item);
  if (extend == 0)
    {
      fprintf(stderr, "bug 2 in buildYaccExtensionRule2\n");
      exit(1);
    }
  if (complx->abstract == XmlCppBase::yes)
    return; // do not print anything for abstract types
  sequence = dynamic_cast<XmlSequence *>(extend->item);
  allCaps(complx->newName, buffer);
  n = sprintf(text, "%sDECL", buffer);
  hasAttributes = ((extend->attribs) || hasAttribs(extend->base));
  if (hasAttributes)
    {
      n += sprintf(text+n, " y_ListAttributePair");
      index = 4;
    }
  else
    index = 3;
  n += sprintf(text+n, " ENDITEM");
  buildYaccExtensionBaseItems(extend->base, &n);
  if (sequence)
    {
      buildYaccExtensionSequenceItems(sequence->items, &n);
    }
  n += sprintf(text+n, "%c", 13);
  n += sprintf(text+n, "\t  {$$ = new %s(", complx->newName);
  buildYaccExtensionBaseArgs(extend->base, &n, &index, &comma);
  numItems = (sequence ? sequence->items->size() : 0);
  for (k = 0; k < numItems; k++)
    {
      n += sprintf(text+n, "%s$%d", (comma ? ", " : ""), index++);
      comma = 1;
    }
  n += sprintf(text+n, ");\n");
  n += sprintf(text+n, "\t   $$->printTypp = true;\n");
  if (hasAttributes)
    {
      n += sprintf(text+n, "\n");
      n += sprintf(text+n, "\t   if ($$->badAttributes($1))\n");
      n += sprintf(text+n, "\t     %serror(\"Bad attributes for %s\");\n",
		   yyprefix, complx->newName);
      n += sprintf(text+n, "\t   delete $1;\n");
    }
  sprintf(text+n, "\t  }\n");
  sprintf(buffer, "x_%s", complx->newName);
  enterNamePair(strdup(buffer), strdup(text), endRules);
}

/********************************************************************/

/* generator::buildYaccExtensionRules

Returned Value: none

Called By: generator::buildYaccRulesEnd

This builds namePairs that represent the C++ subclasses (XML extensions)
of a base class.

This is called only if complx is used as the type of an element.

Example - If UK_Address and US_Address are extensions of Address, this
might build rules with production names y_x_UK_Address and y_x_US_Address.

*/

void generator::buildYaccExtensionRules(   /* ARGUMENTS                    */
 std::list<XmlComplexType *> * extensions, /* extensions of complx         */
 XmlComplexType * complx,                  /* complex type with extensions */
 std::list<namePair *> * endRules)         /* rules to build               */
{
  std::list<XmlComplexType *>::iterator iter;

  for (iter = extensions->begin(); iter != extensions->end(); iter++)
    {
      buildYaccExtensionRule2(*iter, endRules);
    }
}

/********************************************************************/

/* generator::buildYaccExtensionSequenceItems

Returned Value: none

Called By:
  generator::buildYaccExtensionBaseItems
  generator::buildYaccExtensionRule1
  generator::buildYaccExtensionRule2

cd This prints in the text for the YACC file the items in the sequence of
an extension.

Example - If (1) US_Address is an extension of Address, (2) Address has
Name, Street, and City elements, and (3) US_Address has Zip and State
elements, this function will be called twice.

When it is called by buildYaccExtensionBaseItems it will print the
following in the text array used in printing the rule for US_Address:

y_Name y_Street y_City

When it is called by buildYaccExtensionRule2 it will print the
following in the text array used in printing the rule for US_Address:

y_Zip y_State

If the sequence contains anything other than an element, this prints an
error message and exits. However, that should never happen since when
this is called, any choice or sequence will have been replaced by a
mock element.

*/

void generator::buildYaccExtensionSequenceItems( /* ARGUMENTS              */
 std::list<XmlChoSeqItem *> * items,             /* list of items to print */
 int * n)                                        /* index for text         */
{
  std::list<XmlChoSeqItem *>::iterator iter;
  XmlElementLocal * element;

  for (iter = items->begin(); iter != items->end(); iter++)
    {
      element = dynamic_cast<XmlElementLocal *>(*iter);
      if (element == 0)
	{
	  fprintf(stderr,
	     "buildYaccExtensionSequenceItems cannot handle non-element\n");
	  exit(1);
	}
      if (element->needList)
	{
	  *n += sprintf((text + *n), " y_List%s%s", element->prodBase,
			(((element->minOccurs > 1) ||
			  (element->maxOccurs != -1)) ? "_Check" : ""));
	}
      else
	*n += sprintf((text + *n), " y_%s", element->prodBase);
    }
}

/********************************************************************/

/* generator::buildYaccRestrictSListRule

Returned Value: none

Called By: generator::buildYaccRulesEnd

This handles building namePairs for YACC rules when there is an
element whose type is a simple restriction that is at the top of a chain
of restrictions that has an sList at the bottom.

If the item type of the sList is a simple type, it is assumed to have
a "bad" field that will need checking after the constructor is called.
If the item type of the sList is a basic type, enterHas is called rather
than building a namePair.

Example - The situation is:
1. An element named AllowedOrientations is of type AllowedOrientationsList.
2. minOccurs for AllowedOrientations is zero.
3. An AllowedOrientationsList cannot be empty.
4. AllowedOrientationsList is a restriction of AllowedOrientationsBase.
5. The item type of the list is BoxOrientType.
6. BoxOrientType is a restriction of xs:integer (a basic type).

This would build six namePairs from which the following four rules are
printed. In two cases, two name pairs for the same left side are
combined in one rule. Note that AllowedOrientationsBase does not appear.

y_ListAllowedOrientations :
	  y_ListBoxOrientType
	  {$$ = new AllowedOrientationsList($1);
	   if ($$->bad)
	     yyerror("bad AllowedOrientationsList value");
	  }
	;

y_ListAllowedOrientations_AllowedOrientations_0 :
	  / * empty * /
	  {$$ = 0;}
	| ALLOWEDORIENTATIONSSTART  ENDITEM {yyReadDataList = 1;}
          y_ListAllowedOrientations ALLOWEDORIENTATIONSEND
	  {$$ = $4;}
	;

y_BoxOrientType :
	  DATASTRING
	  {$$ = new BoxOrientType($1);
	   if ($$->bad)
	     yyerror("bad BoxOrientType");
	   free($1);
	  }
	;

y_ListBoxOrientType :
	  y_BoxOrientType
	  {$$ = new std::list<BoxOrientType *>;
	   $$->push_back($1);
	  }
	| y_ListBoxOrientType y_BoxOrientType
	  {$$ = $1;
	   $$->push_back($2);
	  }
	;

FIX - Need to check that if the item type is not basic it is a restriction,
not an sList. An sList of an sList is not allowed.

FIX - This needs to write another name pair for an empty sList. SLists are
allowed to be empty. A restriction is needed to prevent that.

*/

void generator::buildYaccRestrictSListRule( /* ARGUMENTS                     */
 XmlElementLocal * element,                 /* element is simple restriction */
 XmlSimpleType * simple,                    /* simple type of element        */
 char * itemTypePrefix,                     /* prefix of item type           */
 char * itemTypeName,                       /* name of item type             */
 std::list<namePair *> * endRules)          /* rules to build                */
{
  static char buffer[NAMESIZE];
  static char typeName[NAMESIZE];
  bool isBasic;            // true if item type in base sList is basic
  int n;

  // call setHas if item type is basic. Build namePair for item type if not.
  isBasic = (itemTypePrefix &&
	     (strcmp(itemTypePrefix, XmlCppBase::wg3Prefix) == 0));
  if (isBasic)
    {
      setHas(XmlCppBase::wg3Prefix, itemTypeName);
      findCppTypeName(itemTypeName, typeName);
    }
  else
    {
      strcpy(typeName, itemTypeName);
      n = sprintf(text, "DATASTRING%c", 13);
      n += sprintf(text+n, "\t  {$$ = new %s($1);\n", itemTypeName);
      n += sprintf(text+n, "\t   if ($$->bad)\n");
      n += sprintf(text+n, "\t     %serror(\"bad %s\");\n", 
		   yyprefix, itemTypeName);
      n += sprintf(text+n, "\t   free($1);\n");
      sprintf(text+n, "\t  }\n");
      enterNamePair(strdup(typeName),  strdup(text), endRules);
    }

  // if element is optional, build namePair for empty rule for element
  if (element->minOccurs == 0)
    {
      n = sprintf(text, "/* empty */%c", 13);
      sprintf(text+n, "\t  {$$ = 0;}\n");
      enterNamePair(strdup(element->prodBase), strdup(text), endRules);
    }

  // build namePair for non-empty rule for element
  allCaps(element->newName, buffer);
  n = sprintf(text, "%sSTART ENDITEM {yyReadDataList = 1;} y_%s %sEND%c",
	      buffer, simple->newName, buffer, 13);
  sprintf(text+n, "\t  {$$ = $4;}\n");
  enterNamePair(strdup(element->prodBase), strdup(text), endRules);
  
  sprintf(buffer, "List%s", typeName);

  // build namePair for type at top of restriction chain
  n = sprintf(text, "y_%s%c", buffer, 13);
  n += sprintf(text+n, "\t  {$$ = new %s($1);\n", simple->newName);
  n += sprintf(text+n, "\t   if ($$->bad)\n");
  n += sprintf(text+n, "\t     %serror(\"bad %s value\");\n",
	       yyprefix, simple->newName);
  sprintf(text+n, "\t  }\n");
  enterNamePair(strdup(simple->newName), strdup(text), endRules);
  
  // build namePair for first element of sList of item type
  n = sprintf(text, "y_%s%c", typeName, 13);
  n += sprintf(text+n, "\t  {$$ = new std::list<%s *>;\n", typeName);
  n += sprintf(text+n, "\t   $$->push_back($1);\n");
  n += sprintf(text+n, "\t  }\n");
  enterNamePair(strdup(buffer), strdup(text), endRules);

  // build namePair for other elements of sList of item type
  n = sprintf(text, "y_%s y_%s%c", buffer, typeName, 13);
  n += sprintf(text+n, "\t  {$$ = $1;\n");
  n += sprintf(text+n, "\t   $$->push_back($2);\n");
  sprintf(text+n, "\t  }\n");
  enterNamePair(strdup(buffer), strdup(text), endRules);
}

/********************************************************************/

/* generator::buildYaccRulesEnd

Returned Value: none

Called By: generator::printYacc

This goes through the elementInfos. For each elementInfo, it
generates one or more namePairs representing rules and stores them in
the endRules in alphabetical order by name1. The left hand sides of
the rules are the same as the right-hand sides of the type
declarations generated in buildYaccTypeElementPairs, and this function
is very similar to that one.

Each rule is represented by a one or more namePairs (a pair of strings).
Each pair represents a production name and the right-hand side of the
production. Where more than one namePair is used to construct a single
rule, the name1s of all those namePairs are the same.

A. oList
A1. For a basic type, namePairs are put into the endRules resulting in:
A1a. a rule for the oList
A1b. a rule for the element

A2. For a complex type, namePairs are put into the endRules resulting in:
A2a. a rule for the element
A2b. a rule for the type of the element, if the type is not abstract
A2c. a rule for the oList

A3. For a simple type, namePairs are put into the endRules resulting in:
A3a. a pair for the element
A3b. a pair for the oList
Note that no pair is needed for the type

B. Not an oList
B1. For a basic type, namePairs are put into the endRules resulting in:
B1a. a pair for the element

B2. For a complex type, namePairs are put into the endRules resulting in:
B2a. rules for the type of the element (see buildYaccComplexTypeRule),
     if the type is not abstract
B2b. if there are extensions:
       i. a rule for the element
       ii. a rule for the "any" type
       iii. a rule for each extension
     if there are no extensions:
       i. a rule for the element

B3. For a simple type, namePairs are put into the endRules resulting in:
B3a. if the simple type is an sList:
       i. a rule for the element
B3b. if the simple type is a restriction of an sList:
       i. a rule for the element
       ii. a rule for the restricted sList
       iii. a rule for the unrestricted sList
       iv. a rule for the sList item
B3c. otherwise:
       i. a rule for the element
Note that for a simple type, no rule is needed for the type (since the
parser sees a string).

The elementInfos entries are unique, so it is not necessary to check
for duplicates here.

If the same complex type is used in more than one elementInfo, enterNamePair
will be called with the same names more than once, but enterNamePair
checks for duplicates, so no duplicates will get into the typePairs.

No rule should be built for the top element, since the rule for XXXFile
is used instead. FIX - Currently, this is checking for the top element
only when it has a complex type or a basic type; a check should be made
if it has simple type as well. 

*/

void generator::buildYaccRulesEnd( /* ARGUMENTS       */
 std::list<namePair *> * endRules) /* rules to build  */
{
  static char * prodBase;                  // production name for element
  static char typeName[NAMESIZE];          // name for a C++ type
  std::list<elementInfo *>::iterator iter; // iterator for element infos
  XmlComplexType * complx;                 // complex type of element if complex
  XmlSimpleType * simple;                  // simple type of element if simple
  XmlSimpleList * sList;                   // list in base type
  XmlElementLocal * element;               // an element from element infos
  bool isBasic;                            // true if element type is basic
  int level;                               // level of sList derivation
  static char ignore[NAMESIZE];            // buffer req'd in isRestrictedList
  static char itemTypePrefix[NAMESIZE];    // buffer for list item type prefix
  static char itemTypeName[NAMESIZE];      // buffer for list item type name

  for (iter = elementInfos->begin(); iter != elementInfos->end(); iter++)
    {
      element = (*iter)->element;
      complx = (*iter)->complexType;
      simple = (*iter)->simpleType;
      isBasic = (element->typPrefix &&
		 (strcmp(element->typPrefix, XmlCppBase::wg3Prefix) == 0));
      prodBase = element->prodBase;
      if (element->needList)
	{ // zero or many elements are allowed
	  if (isBasic)
	    {
	      findCppTypeName(element->newTyp, typeName);
	      buildYaccBasicRule(prodBase, element->newName, element->newTyp,
				 false, endRules);
	      buildYaccElementListRule(prodBase, element, typeName, endRules);
	    }
	  else if (complx)
	    {
	      if (complx->abstract != XmlCppBase::yes)
		{
		  buildYaccComplexElementRule(complx, prodBase,
					      element->newName, false,
					      element->mock, endRules);
		  buildYaccComplexTypeRule(complx, endRules);
		}
	      buildYaccElementListRule(prodBase, element,
				       element->newTyp, endRules);
	      if (complx->extensions)
		{
		  buildYaccComplexExtRule(prodBase, element->newName,
					  element->newTyp, false, endRules);
		  buildYaccComplexAnyRule(element->newTyp,
					  complx->extensions, endRules);
		  buildYaccExtensionRules(complx->extensions, complx, endRules);
		}
	    }
	  else if (simple)
	    {
	      buildYaccSimpleRule(prodBase, element->newName,
				  element->newTyp, simple, false, endRules);
	      buildYaccElementListRule(prodBase, element,
				       element->newTyp, endRules);
	    }
	  else
	    {
	      fprintf(stderr, "bad elementInfo in buildYaccRulesEnd\n");
	      exit(1);
	    }
	}
      else
	{ // multiple elements not allowed
	  if (isBasic && (element != top)) // rule for top is y_XXXFile
	    {
	      buildYaccBasicRule(prodBase, element->newName, element->newTyp,
				 (element->minOccurs == 0), endRules);
	    }
	  else if (complx)
	    {
	      if (complx->abstract != XmlCppBase::yes)
		buildYaccComplexTypeRule(complx, endRules);
	      if (complx->extensions)
		{
		  buildYaccComplexExtRule(prodBase, element->newName,
					  element->newTyp,
					  (element->minOccurs == 0), endRules);
		  buildYaccComplexAnyRule(element->newTyp,
					  complx->extensions, endRules);
		  buildYaccExtensionRules(complx->extensions, complx, endRules);
		}
	      else if (element != top) // rule for top is y_XXXFile
		{
		  buildYaccComplexElementRule(complx, prodBase,
					      element->newName,
					      (element->minOccurs == 0),
					      element->mock, endRules);
		}
	    }
	  else if (simple)
	    {
	      level = 0;
	      if ((sList = dynamic_cast<XmlSimpleList *>(simple->item)))
		{
		  buildYaccSimpleListRule(prodBase, element->newName, simple,
					  sList, (element->minOccurs == 0),
					  endRules);
		}
	      else if (dynamic_cast<XmlSimpleRestriction *>(simple->item) &&
		       isRestrictedSList(simple, ignore, itemTypePrefix,
					 itemTypeName, &level))
		{
		  buildYaccRestrictSListRule(element, simple, itemTypePrefix,
					     itemTypeName, endRules);
		}
	      else
		buildYaccSimpleRule(prodBase, element->newName,
				    element->newTyp, simple,
				    (element->minOccurs == 0), endRules);
	    }
	  else
	    {
	      fprintf(stderr, "bad elementInfo in buildYaccRulesEnd\n");
	      exit(1);
	    }
	}
    }
}

/********************************************************************/

/* generator::buildYaccSequenceArgs

Returned Value: none

Called By: generator::buildYaccSequenceRule

This prints YACC style constructor arguments in the text for a
sequence. The numeric value of the first one is given by the "first"
argument.

A comma and space are printed before the numeric arguments if there
were previous constructor arguments representing XML attributes. The
index argument "n" will be reset.

Example - This might add the following to the text array:
  $3, $4, $5, $6, $7, $8

*/

void generator::buildYaccSequenceArgs( /* ARGUMENTS                       */
 int * n,                              /* index for text                  */
 XmlSequence * sequence,               /* sequence to print args for      */
 int first)                            /* index of first argument         */
{
  std::list<XmlChoSeqItem *> * items;
  int k;
  int stop;
  int comma = 0;  // 1=print comma and space 0=don't

  items = sequence->items;
  stop = items->size();
  for (k = 0; k < stop; k++)
    {
      *n += sprintf((text + *n), "%s$%d", (comma ? ", " : ""), first++);
      comma = 1;
    }
}

/********************************************************************/

/* generator::buildYaccSequenceItems

Returned Value: none

Called By: generator::buildYaccSequenceRule

This prints in the text array the items in the definition part of a
rule that represent elements of an XML sequence.

Example - This might add the following to the text array:

 y_First y_Last y_PhoneExt y_Title_Opt y_Email y_Address

If the item is not a oList, the name that is printed is "y_" followed
by the prodBase of the element.

If the maxOccurs of an element is -1 (which means unbounded), or is
zero, or is greater than 1, an oList is needed. In this case the name
that is printed is "y_" followed by "List", followed by the prodBase of
the element.

This prints an error message and exits if an XmlChoice or XmlSequence
appears in a sequence. However, this should never happen since when
this is called, any choice or sequence will have been replaced by a
mock element.

*/

void generator::buildYaccSequenceItems(  /* ARGUMENTS             */
 int * n,                                /* index for text        */
 XmlSequence * sequence)                 /* sequence of elements  */
{
  XmlElementLocal * element;
  XmlChoice * choice;
  XmlSequence * seq;
  std::list<XmlChoSeqItem *> * items;
  std::list<XmlChoSeqItem *>::iterator iter;
  int k = 0;                       // index in sequence

  items = sequence->items;
  for (iter = items->begin(); iter != items->end(); k++, iter++)
    {
      if ((element = dynamic_cast<XmlElementLocal *>(*iter)))
	{
	  if (element->needList)
	    {
	      *n += sprintf((text + *n), " y_List%s%s", element->prodBase,
			    (((element->minOccurs > 1) ||
			      (element->maxOccurs != -1)) ? "_Check" : ""));
	    }
	  else
	    {
	      *n += sprintf((text + *n), " y_%s", element->prodBase);
	    }
	}
      else if ((choice = dynamic_cast<XmlChoice *>(*iter)))
	{
	  fprintf(stderr, "cannot handle choice in a sequence\n");
	  exit(1);
	}
      else if ((seq = dynamic_cast<XmlSequence *>(*iter)))
	{
	  fprintf(stderr, "cannot handle a sequence in a sequence\n");
	  exit(1);
	}
    }
}

/********************************************************************/

/* generator::buildYaccSequenceRule

Returned Value: none

Called By: generator::buildYaccComplexTypeRule

This builds a namePair representing a rule for an XmlComplexType containing
a sequence.

This calls the constructor that does not take attributes. Since there
may or may not be elements, some fiddling is necessary.

For the type of the top-level element, three extra items are added to the
definition, and "first" variable is increased by three to account for
the extra items.

Example 1 - For the type of the top-level element, this might build
the namePair from which is printed:

y_CompanyType :
	  ENDITEM y_ListNickname_string_0_3_Check y_Address_AddressType
	  y_ListPerson_PersonType_u
	  {$$ = new CompanyType($2, $3, $4);}
	;

Example 2 - For the type of a local element, this might build
the namePair from which is printed:

y_PersonType :
	  y_ListAttributePair ENDITEM y_First_string y_Last_string
	  y_PhoneExt_PhoneExtType y_PhoneExt2_PhoneExtType_0
	  y_Title_string_0 y_Email_string y_Address_string y_IsTall_boolean
	  y_Age_AgeType_0 y_AltAddress_UK_Address_0
	  {$$ = new PersonType($3, $4, $5, $6, $7, $8, $9, $10, $11, $12);
	   if ($$->badAttributes($1))
	     yyerror("Bad attributes for PersonType");
           delete $1;
	  }
	;

*/

void generator::buildYaccSequenceRule(    /* ARGUMENTS                       */
 XmlSequence * sequence,                  /* sequence to build from          */
 char * newName,                          /* newName of complex with sequence*/
 std::list<XmlAttributeLoner *> * attribs,/* attributes of other with seqnce */
 std::list<namePair *> * endRules)        /* rules to build                  */
{
  std::list<XmlAttributeLoner *>::iterator iter;
  std::list<int>::iterator ator;
  int n;
  int first;

  if (attribs)
    {
      n = sprintf(text, "y_ListAttributePair ENDITEM");
      first = 3;
    }
  else
    {
      n = sprintf(text, "ENDITEM");
      first = 2;
    }
  buildYaccSequenceItems(&n, sequence);
  n += sprintf(text+n, "%c", 13);
  n += sprintf(text+n, "\t  {$$ = new %s(", newName);
  buildYaccSequenceArgs(&n, sequence, first);
  n += sprintf(text+n, ");");
  if (attribs && attribs->size())
    {
      n += sprintf(text+n, "\n");
      n += sprintf(text+n, "\t   if ($$->badAttributes($1))\n");
      n += sprintf(text+n, "\t     %serror(\"Bad attributes for %s\");\n",
		   yyprefix, newName);
      n += sprintf(text+n, "\t   delete $1;\n");
      sprintf(text+n, "\t  }\n");
    }
  else
    sprintf(text+n, "}\n");
  enterNamePair(strdup(newName), strdup(text), endRules);
}

/********************************************************************/

/* generator::buildYaccSimpleContentRule

Returned Value: none

Called By: generator::buildYaccComplexTypeRule

This builds a namePair representing a rule for an XmlComplexType with
simpleContent.

FIX - See notes on simple extension at beginning. The YACC rule should
be looking for a DATASTRING and passing it to the constructor.

*/

void generator::buildYaccSimpleContentRule(/* ARGUMENTS                      */
 char * name,                         /* name of type to build rule for      */
 char * newName,                      /* new name of type to build rule for  */
 XmlSimpleContent * content,          /* XmlSimpleContent to build rule from */
 std::list<namePair *> * endRules)    /* rules to build                      */
{
  XmlSimpleContentExtension * extend;
  char typeName[NAMESIZE];
  int n;

  if ((extend = dynamic_cast<XmlSimpleContentExtension *>(content->item)))
    {
      if (extend->basePrefix &&
	  (strcmp(extend->basePrefix, XmlCppBase::wg3Prefix) == 0))
	findCppTypeName(extend->base, typeName);
      else
	strcpy(typeName, extend->newBase);
      n = sprintf(text, "y_ListAttributePair ENDITEM {yyReadData = 1;} y_%s%c",
		  typeName, 13);
      n += sprintf(text+n, "\t  {$$ = new %s($4);\n", newName);
      n += sprintf(text+n, "\t   if ($$->badAttributes($1))\n");
      n += sprintf(text+n, "\t     %serror(\"Bad %s attributes\");\n",
		   yyprefix, name);
      n += sprintf(text+n, "\t   delete $1;\n");
      sprintf(text+n, "\t  }\n");
      enterNamePair(strdup(newName), strdup(text), endRules);
    }
  else if ((dynamic_cast<XmlSimpleContentRestriction *>(content->item)))
    {
      fprintf(stderr, "cannot handle simple content restriction\n");
      exit(1);
    }
  else
    {
      fprintf(stderr, "bad simple content type in printCppCodeComplex\n");
      exit(1);
    }
}

/********************************************************************/

/* generator::buildYaccSimpleListRule

Returned Value: none

Called By: generator::buildYaccRulesEnd

This builds 
 - a namePair representing a rule for an element whose type is a XmlSimpleList
 - a namePair representing a rule for the type of the element

Example - If the element name is FavoriteNumbers, the name of the
sList type is FavoriteNumbersType, and the XML type of the data in the
sList is unsignedInt, this might build four namePairs from which is
printed:

y_ListXmlUnsignedInt :
	  y_ListXmlUnsignedInt y_XmlUnsignedInt
	  {$$ = $1;
	   $$->push_back($2);
	  }
	| y_XmlUnsignedInt
	  {$$ = new std::list<XmlUnsignedInt *>;
	   $$->push_back($1);
	  }
	;

y_FavoriteNumbersType :
	 y_ListXmlUnsignedInt
	 {$$ = new FavoriteNumbersType($1);}
	;

y_FavoriteNumbers_FavoriteNumbersType :
	  FAVORITENUMBERSSTART ENDITEM {yyReadDataList = 1;}
	  y_FavoriteNumbersType FAVORITENUMBERSEND
	  {$$ = $4;}
	;

The return type of the productions for the element and the type is
a pointer to an instance of the type.

The emptyOk argument refers to the element and is true if the element
is optional (minOccurs = 0).

FIX -The sList (by XML rules) may be empty. This is currently not
allowed.

*/

void generator::buildYaccSimpleListRule( /* ARGUMENTS                      */
 char * prodName,                        /* name of production without y_  */
 char * elementName,                     /* newName of element             */
 XmlSimpleType * simple,                 /* simple type of element         */
 XmlSimpleList * sList,                  /* list that is simple->item      */
 bool emptyOk,                           /* true=optional, false=not       */
 std::list<namePair *> * endRules)       /* rules to build                 */
{
  static char buffer[NAMESIZE];
  static char cppTypeName[NAMESIZE]; // sList item type name
  bool isBasic;                      // true if element type is basic
  int n;                             // character counter

  isBasic = (sList->typePrefix &&
	     (strcmp(sList->typePrefix, XmlCppBase::wg3Prefix) == 0));
  if (isBasic)
    {
      setHas(XmlCppBase::wg3Prefix, sList->newItemType);
      findCppTypeName(sList->newItemType, cppTypeName);
    }
  else
      strcpy(cppTypeName, sList->newItemType);

  sprintf(buffer, "List%s", cppTypeName);
  n = sprintf(text, "y_%s%c", cppTypeName, 13);
  n += sprintf(text+n, "\t  {$$ = new std::list<%s *>;\n", cppTypeName);
  n += sprintf(text+n, "\t   $$->push_back($1);\n");
  sprintf(text+n, "\t  }\n");
  enterNamePair(strdup(buffer), strdup(text), endRules);

  n = sprintf(text, "y_%s y_%s%c", buffer, cppTypeName, 13);
  n += sprintf(text+n, "\t  {$$ = $1;\n");
  n += sprintf(text+n, "\t   $$->push_back($2);\n");
  sprintf(text+n, "\t  }\n");
  enterNamePair(strdup(buffer), strdup(text), endRules);
  
  n = sprintf(text, "y_%s%c", buffer, 13);
  n += sprintf(text+n, "\t  {$$ = new %s($1);}\n", simple->newName);
  enterNamePair(strdup(simple->newName), strdup(text), endRules);

  if (emptyOk)
    {
      n = sprintf(text, "/* empty */%c", 13);
      sprintf(text+n, "\t  {$$ = 0;}\n");
      enterNamePair(strdup(prodName), strdup(text), endRules);
    }
  allCaps(elementName, buffer);
  n = sprintf(text, "%sSTART ENDITEM {%sReadDataList = 1;} y_%s %sEND%c",
	      buffer, yyprefix, simple->newName, buffer, 13);
  sprintf(text+n, "\t  {$$ = $4;}\n");
  enterNamePair(strdup(prodName), strdup(text), endRules);
}

/********************************************************************/

/* generator::buildYaccSimpleRule

Returned Value: none

Called By: generator::buildYaccRulesEnd

This builds one or two namePairs that represent the rule for an
element whose type is an XmlSimpleType.

If the simple type contains restrictions on a basic type or on another
simple type derived by restriction, the checks for the restrictions
are handled in the constructor. If the checks fail, the constructor
sets the "bad" field of the class instance to true. After the
constructor runs, the rule checks whether the "bad" field is true
and reports an error if so. Before the constructor can be called,
however, the rule checks whether the data (which is always a string)
can represent the basic type (an integer, for example) of the simple
type. This function calls findRootXmlType in order to find the basic
type.

If the element is optional, this builds two namePairs, the first of
which allows the element to be missing.

Example 1 - If an element named State is not optional and is of simple
type US_State, this might build a namePair from which the following
rule is printed:

y_State_US_State :
	  STATESTART ENDITEM {yyReadData = 1;} DATASTRING STATEEND
	  {$$ = new US_State($4);
	   if ($$->bad)
	     yyerror("bad State value");
           free($4);
	  }
	;

Example 2 - If an element named PhoneExt2 is optional and is of simple
type PhoneExtType which has base type integer, this might build two
namePairs from which the following rule is printed (the rule does not
have the extra spaces in / * and * / but they are needed here since
this is a C++ comment):

y_PhoneExt2_PhoneExtType_0 :
	  / * empty * /
	  {$$ = 0;}
	| PHONEEXT2START ENDITEM {yyReadData = 1;} DATASTRING PHONEEXT2END
	   $$ = new PhoneExtType($4);
	   if ($$->bad)
	     yyerror("bad PhoneExt2 value");
           free($4)
	  }
	;

*/

void generator::buildYaccSimpleRule( /* ARGUMENTS                      */
 char * prodName,                    /* name of production without y_  */
 char * elementName,                 /* newName of element             */
 char * typ,                         /* name of type of element        */
 XmlSimpleType * simple,             /* simple type whose name is typ  */
 bool emptyOk,                       /* true=optional, false=not       */
 std::list<namePair *> * endRules)   /* rules to build                 */
{
  static char buffer[NAMESIZE];
  int n;

  if (emptyOk)
    {
      n = sprintf(text, "/* empty */%c", 13);
      sprintf(text+n, "\t  {$$ = 0;}\n");
      enterNamePair(strdup(prodName), strdup(text), endRules);
    }
  allCaps(elementName, buffer);
  n = sprintf(text, "%sSTART ENDITEM {%sReadData = 1;} DATASTRING %sEND%c",
	      buffer, yyprefix, buffer, 13);
  n += sprintf(text+n, "\t  {$$ = new %s($4);\n", typ);
  n += sprintf(text+n, "\t   if ($$->bad)\n");
  n += sprintf(text+n, "\t     %serror(\"bad %s value\");\n",
	       yyprefix, elementName);
  n += sprintf(text+n, "\t   free($4);\n");
  sprintf(text+n, "\t  }\n");
  enterNamePair(strdup(prodName), strdup(text), endRules);
}

/********************************************************************/

/* generator::buildYaccTypeElementPairs

Returned Value: none

Called By: generator::printYaccTypeElements

This makes pairs of names representing a production name and a YACC
value name. Each pair will be used in printing a line declaring
a YACC type.

Example - this might make the pair:
(Address_AddressType, AddressType).
For that pair, the following line will eventually be printed in the YACC file:
%type <AddressTypeVal>  y_Address_AddressType

For an oList (maxOccurs is -1 or greater than 1), a pair for the oList
and a pair for the type of item in the oList are entered into the
typePairs. Also for an oList, unless minOccurs is 1 and maxOccurs is
unbounded, a pair for checking the size of the oList is entered.

*/

void generator::buildYaccTypeElementPairs( /* ARGUMENTS                  */
 std::list<namePair *> * typePairs)        /* list of namePairs to build */
{
  char valName[NAMESIZE];
  char prodName[NAMESIZE];
  std::list<elementInfo *>::iterator iter;
  XmlComplexType * complx;
  XmlSimpleType * simple;
  XmlElementLocal * element;
  bool isBasic;

  for (iter = elementInfos->begin(); iter != elementInfos->end(); iter++)
    {
      element = (*iter)->element;
      complx = (*iter)->complexType;
      simple = (*iter)->simpleType;
      isBasic = (element->typPrefix &&
		 (strcmp(element->typPrefix, XmlCppBase::wg3Prefix) == 0));
      if (isBasic)
	{
	  buildYaccTypeElementPairsBasic(typePairs, element, element->needList,
					 valName, prodName);
	}
      else if (complx)
	{
	  buildYaccTypeElementPairsComplex(complx, typePairs, element,
					   element->needList, valName,
					   prodName);
	}
      else if (simple)
	{
	  buildYaccTypeElementPairsSimple(simple, typePairs, element,
					  element->needList, valName,
					  prodName);
	}
      else
	{
	  fprintf(stderr, "bad elementInfo in buildYaccTypeElementPairs\n");
	  exit(1);
	}
    }
}

/********************************************************************/

/* generator::buildYaccTypeElementPairsBasic

Returned Value: none

Called By: generator::printYaccTypeElementPairs

*/

void generator::buildYaccTypeElementPairsBasic(/* ARGUMENTS                  */
 std::list<namePair *> * typePairs,            /* list of namePairs to build */
 XmlElementLocal * element,                    /* element using simple type  */
 bool needsList,                               /* true=list needed           */
 char * valName,                               /* buffer for val name        */
 char * prodName)                              /* buffer for production name */
{
  static char typeName[NAMESIZE];

  findCppTypeName(element->newTyp, typeName);
  enterNamePair(strdup(element->prodBase),
		strdup(typeName), typePairs);
  if (needsList)
    {
      findCppTypeName(element->newTyp, typeName);
      sprintf(prodName, "List%s", element->prodBase);
      sprintf(valName, "List%s", typeName);
      enterNamePair(strdup(prodName), strdup(valName), typePairs);
      if ((element->minOccurs > 1) || (element->maxOccurs != -1))
	{
	  sprintf(prodName, "List%s_Check", element->prodBase);
	  enterNamePair(strdup(prodName), strdup(valName), typePairs);
	}
    }
}

/********************************************************************/

/* generator::buildYaccTypeElementPairsComplex

Returned Value: none

Called By: generator::printYaccTypeElementPairs

For a complex type, a pair for the element is entered into the
typePairs. Also, if the type of element is not abstract, a pair for
the type of the element is entered into the typePairs.

For a complex type that has extensions, this is creating a namePair based
on a name ending with "Any" for a rule that allows the type or any of
the extensions to be used where the type is used. Also, in this case,
a namePair for each of the extensions is built.

If an instance of a complex type might end in />, this prints a pair for
a type whose production name ends in _Whole.

If the same complex type is used in more than one elementInfo, enterNamePair
will be called with the same names more than once, but enterNamePair
checks for duplicates, so no duplicates will get into the typePairs.

FIX - To cover all possible cases, there are probably more things to do when
needsList is true than are currently being done. What is here handles
everything in the existing test suite. In particular, sLists and the
element type of sLists seem to need fixing.

*/

void generator::buildYaccTypeElementPairsComplex(/* ARGUMENTS                 */
 XmlComplexType * complx,                       /* complex type to build for  */
 std::list<namePair *> * typePairs,             /* list of namePairs to build */
 XmlElementLocal * element,                     /* element using simple type  */
 bool needsList,                                /* true=list needed           */
 char * valName,                                /* buffer for val name        */
 char * prodName)                               /* buffer for production name */
{
  XmlOtherContent * other;
  XmlChoice * choice;
  
  if ((other = dynamic_cast<XmlOtherContent *>(complx->item)) &&
      (choice =  dynamic_cast<XmlChoice *>(other->base)))
    {
      strcpy(valName, complx->newName);
      enterNamePair(strdup(complx->newName), strdup(valName), typePairs);
      if (element != top) // YACC production for top is XXXFile
	enterNamePair(strdup(element->prodBase), strdup(valName), typePairs);
      sprintf(valName, "List%sChoicePair", complx->newName);
      sprintf(prodName, "List%sChoicePair", complx->newName);
      enterNamePair(strdup(prodName), strdup(valName), typePairs);
      sprintf(valName, "%sChoicePair", complx->newName);
      sprintf(prodName, "%sChoicePair", complx->newName);
      enterNamePair(strdup(prodName), strdup(valName), typePairs);
    }
  else
    {
      strcpy(valName, complx->newName);
      if (complx->abstract != XmlCppBase::yes)
	enterNamePair(strdup(complx->newName), strdup(valName), typePairs);
      if ((other = dynamic_cast<XmlOtherContent *>(complx->item)) &&
	  (dynamic_cast<XmlSequence *>(other->base) == 0) &&
	  (dynamic_cast<XmlChoice *>(other->base) == 0))
	{
	  sprintf(prodName, "%s_Whole", complx->newName);
	  enterNamePair(strdup(prodName), strdup(valName), typePairs);
	}
      if (element != top) // YACC production for top is XXXFile
	enterNamePair(strdup(element->prodBase), strdup(valName), typePairs);
    }
  if (needsList)
    {
      sprintf(prodName, "List%s", element->prodBase);
      sprintf(valName, "List%s", complx->newName);
      enterNamePair(strdup(prodName), strdup(valName), typePairs);
      if ((element->minOccurs > 1) || (element->maxOccurs != -1))
	{
	  sprintf(prodName, "List%s_Check", element->prodBase);
	  enterNamePair(strdup(prodName), strdup(valName), typePairs);
	}
    }
  if (complx->extensions)
    {
      sprintf(prodName, "%sAny", complx->newName);
      strcpy(valName, complx->newName);
      enterNamePair(strdup(prodName),
		    strdup(valName), typePairs);
      buildYaccTypeExtensionPairs(complx->extensions, typePairs);
    }
}

/********************************************************************/

/* generator::buildYaccTypeElementPairsSimple

Returned Value: none

Called By: generator::printYaccTypeElementPairs

If an element E is of a simple type S1 that is a restriction at the
top of a chain of restrictions with an sList S2 at the bottom, then
namePairs are needed for E, S1, and S2. In addition, a name pair is
needed for the item type of the sList.  If the item type is a basic
type, this is handled by calling setHas; otherwise, a name pair is
inserted for the item type.

In the case of a restriction of a restriction of a simple type that is
not an sList, if the type being restricted is not used elsewhere as the
type of an element or attribute, it does not need to be used to build
an element pair. If it is used elsewhere, it will be entered where that
element or attribute is being processed.

*/

void generator::buildYaccTypeElementPairsSimple(/* ARGUMENTS                 */
 XmlSimpleType * simple,                       /* simple type to build for   */
 std::list<namePair *> * typePairs,            /* list of namePairs to build */
 XmlElementLocal * element,                    /* element using simple type  */
 bool needsOList,                              /* true=oList needed          */
 char * valName,                               /* buffer for val name        */
 char * prodName)                              /* buffer for production name */
{
  XmlSimpleList * sList;
  static char buffer[NAMESIZE];
  static char cppTypeName[NAMESIZE];
  static char sListItemPrefix[NAMESIZE];
  static char sListItemTypeName[NAMESIZE];
  int level;

  strcpy(valName, simple->newName);
  enterNamePair(strdup(element->prodBase), strdup(valName), typePairs);
  level = 0;
  if (needsOList)
    {
      sprintf(prodName, "List%s", element->prodBase);
      sprintf(valName, "List%s", simple->newName);
      enterNamePair(strdup(prodName), strdup(valName), typePairs);
      if ((element->minOccurs > 1) || (element->maxOccurs != -1))
	{
	  sprintf(prodName, "List%s_Check", element->prodBase);
	  enterNamePair(strdup(prodName), strdup(valName), typePairs);
	}
    }
  if (dynamic_cast<XmlSimpleRestriction *>(simple->item) &&
      isRestrictedSList(simple, buffer, sListItemPrefix,
			sListItemTypeName, &level))
    {
      enterNamePair(strdup(valName), strdup(valName), typePairs);
      if (strcmp(sListItemPrefix, XmlCppBase::wg3Prefix) == 0)
	{
	  setHas(XmlCppBase::wg3Prefix, sListItemTypeName);
	  findCppTypeName(sListItemTypeName, cppTypeName);
	}
      else
	{
	  strcpy(cppTypeName, sListItemTypeName);
	  enterNamePair(strdup(sListItemTypeName),
			strdup(sListItemTypeName), typePairs);
	}
      sprintf(buffer, "List%s", cppTypeName);
      enterNamePair(strdup(buffer), strdup(buffer), typePairs);
    }
  else if ((sList = dynamic_cast<XmlSimpleList *>(simple->item)))
    {
      if (sList->typePrefix &&
	  (strcmp(sList->typePrefix, XmlCppBase::wg3Prefix) == 0))
      {
	  setHas(XmlCppBase::wg3Prefix, sList->newItemType);
	  findCppTypeName(sList->newItemType, cppTypeName);
	}
      else
	strcpy(cppTypeName, sList->newItemType);
      enterNamePair(strdup(valName), strdup(valName), typePairs);
      sprintf(buffer, "List%s", cppTypeName);
      enterNamePair(strdup(buffer), strdup(buffer), typePairs);
    }
}

/********************************************************************/

/* generator::buildYaccTypeExtensionPairs

Returned Value: none

Called By: generator::buildYaccTypeElementPairs

For each extension on the extensions std::list that is not abstract, this
enters a pair in the typePairs std::list. The name1 of the namePair is a
production name without the "y_" prefix, and the name2 is the value
type name. This puts a "x_" prefix before the production name.

Example - this might make the pair:
(x_UK_Address, UK_Address).
For that pair, the following line will eventually be printed in the YACC file:
%type <UK_AddressVal>                 y_x_UK_Address


*/

void generator::buildYaccTypeExtensionPairs( /* ARGUMENTS                  */
 std::list<XmlComplexType *> * extensions,   /* list of extensions         */
 std::list<namePair *> * typePairs)          /* list of namePairs to build */
{
  static char prodName[NAMESIZE];
  static char valName[NAMESIZE];
  std::list<XmlComplexType *>::iterator iter;
  
  for (iter = extensions->begin(); iter != extensions->end(); iter++)
    {
      if ((*iter)->abstract != XmlCppBase::yes)
	{
	  sprintf(prodName, "x_%s", (*iter)->newName);
	  strcpy(valName, (*iter)->newName);
	  enterNamePair(strdup(prodName), strdup(valName), typePairs);
	}
    }
}

/********************************************************************/

/* generator::buildYaccUnionElementPairs

Returned Value: none

Called By: generator::printYaccUnion

This builds unionPairs, a std::list of pairs of strings representing a
C++ type (stored in the name2 of the namePair) and the base of the
YACC type name for that type (stored in the name1 of the
namePair). The unionPairs are used in printYaccUnionElements for
printing the union section of the YACC file.  Except where there is an
oList or an sList, the two names in each namePair are the same.

This works by going through the elementInfos std::list. Elements that can
occur more than once or not at all are handled in the first half of the
function. Such elements are handled in the cppCode by a std::list of the
element. Here, a namePair for the std::list and a namePair for the type of
item in the std::list are entered into unionPair. For those entries that
are complex types, a namePair for each extension of the type is also entered.

Example - A call to this function might build two namePairs
from which is printed:
  std::list<PersonType *> *           ListPersonTypeVal;
  PersonType *                        PersonTypeVal;

Elements that occur exactly once are handled in the second half of the
function. The element is handled differently depending on whether it
is a complex type, a simpleType, or a basic type. For those entries
that are complex types, the extensions of the type are also entered.


Example - a call to this function might build the namePair
from which is printed:
  AddressType *                       AddressTypeVal;

Example - a call to this function might build two name pairs
from which is printed:
  FavoriteNumbersType *               FavoriteNumbersTypeVal;
  std::list<XmlUnsignedInt *> *       ListXmlUnsignedIntVal;
 
Printing lines of the union for XML basic types is handled
separately. If a basic type is the type of an element or a std::list
item, setHas is called (here and/or elsewhere) so that a namePair will
be printed for that basic type.

FIX - This is not handling an element that may occur multiple times if the
type is sList.

*/

void generator::buildYaccUnionElementPairs( /* ARGUMENTS                    */
 std::list<namePair *> * unionPairs)        /* list of namePairs built here */
{
  char cppName[NAMESIZE];
  char valName[NAMESIZE];
  char typeName[NAMESIZE];
  std::list<elementInfo *>::iterator iter;
  XmlComplexType * complx;
  XmlSimpleType * simple;
  XmlOtherContent * other;
  XmlChoice * choice;
  XmlElementLocal * element;
  bool isBasic;

  for (iter = elementInfos->begin(); iter != elementInfos->end(); iter++)
    {
      element = (*iter)->element;
      isBasic = (element->typPrefix &&
		 (strcmp(element->typPrefix, XmlCppBase::wg3Prefix) == 0));
      if (element->needList)
	{ // zero or many elements are allowed
	  if (isBasic)
	    {
	      setHas(XmlCppBase::wg3Prefix, element->newTyp);
	      findCppTypeName(element->newTyp, typeName);
	      sprintf(valName, "List%sVal", typeName);
	      sprintf(cppName, "std::list<%s *>", typeName);
	      enterNamePair(strdup(valName), strdup(cppName), unionPairs);
	    }
	  else if ((complx = (*iter)->complexType))
	    {
	      sprintf(valName, "%sVal", complx->newName);
	      enterNamePair(strdup(valName),
			    strdup(complx->newName), unionPairs);
	      sprintf(valName, "List%sVal", complx->newName);
	      sprintf(cppName, "std::list<%s *>", complx->newName);
	      enterNamePair(strdup(valName), strdup(cppName), unionPairs);
	      if (complx->extensions)
		buildYaccUnionExtensionPairs(complx->extensions, unionPairs);
	    }
	  else if ((simple = (*iter)->simpleType))
	    {
	      sprintf(valName, "%sVal", simple->newName);
	      strcpy(cppName, simple->newName);
	      enterNamePair(strdup(valName), strdup(cppName), unionPairs);
	      sprintf(valName, "List%sVal", simple->newName);
	      sprintf(cppName, "std::list<%s *>", simple->newName);
	      enterNamePair(strdup(valName), strdup(cppName), unionPairs);
	    }
	}
      else
	{ // multiple elements are not allowed 
	  if ((complx = (*iter)->complexType))
	    {
	      if (complx->extensions)
		buildYaccUnionExtensionPairs(complx->extensions, unionPairs);
	      if ((other = dynamic_cast<XmlOtherContent *>(complx->item)) &&
		  (choice =  dynamic_cast<XmlChoice *>(other->base)))
		{
		  sprintf(valName, "List%sChoicePairVal", complx->newName);
		  sprintf(cppName, "std::list<%sChoicePair *>",
			  complx->newName);
		  enterNamePair(strdup(valName), strdup(cppName), unionPairs);
		  sprintf(valName, "%sChoicePairVal", complx->newName);
		  sprintf(cppName, "%sChoicePair", complx->newName);
		  enterNamePair(strdup(valName), strdup(cppName), unionPairs);
		}
	      sprintf(valName, "%sVal", complx->newName);
	      strcpy(cppName, complx->newName);
	      enterNamePair(strdup(valName), strdup(cppName), unionPairs);
	    }
	  else if ((simple = (*iter)->simpleType))
	    {
	      buildYaccUnionElementPairsSimple(simple, unionPairs);
	    }
	  else if (isBasic)
	    {
	      setHas(XmlCppBase::wg3Prefix, element->newTyp);
	    }
	}
    }
}

/********************************************************************/

/* generator::buildYaccUnionElementPairsSimple

Returned Value: none

Called By: generator::buildYaccUnionElementPairs

*/

void generator::buildYaccUnionElementPairsSimple( /* ARGUMENTS              */
 XmlSimpleType * simple,                    /* simple type for making pairs */
 std::list<namePair *> * unionPairs)        /* list of namePairs built here */
{
  char cppName[NAMESIZE];
  char valName[NAMESIZE];
  char typeName[NAMESIZE];
  char itemTypeName[NAMESIZE];
  char itemPrefix[NAMESIZE];
  XmlSimpleList * sList;
  int level;

  if (dynamic_cast<XmlSimpleRestriction *>(simple->item) &&
      isRestrictedSList(simple, typeName, itemPrefix,
			itemTypeName, &level))
    {
      if (strcmp(itemPrefix, XmlCppBase::wg3Prefix) == 0)
	{
	  setHas(XmlCppBase::wg3Prefix, itemTypeName);
	  findCppTypeName(itemTypeName, cppName);
	}
      else
	{
	  strcpy(cppName, itemTypeName);
	  sprintf(valName, "%sVal", cppName);
	  enterNamePair(strdup(valName), strdup(cppName),
			unionPairs);
	}
      sprintf(valName, "List%sVal", cppName);
      sprintf(itemTypeName, "std::list<%s *>", cppName);
      enterNamePair(strdup(valName),
		    strdup(itemTypeName), unionPairs);
    }
  else if ((sList = dynamic_cast<XmlSimpleList *>(simple->item)))
    {
      if (sList->typePrefix &&
	  (strcmp(sList->typePrefix, XmlCppBase::wg3Prefix) == 0))
	{
	  setHas(XmlCppBase::wg3Prefix, sList->newItemType);
	  findCppTypeName(sList->newItemType, cppName);
	}
      else
	strcpy(cppName, sList->newItemType);
      sprintf(valName, "List%sVal", cppName);
      sprintf(typeName, "std::list<%s *>", cppName);
      enterNamePair(strdup(valName), strdup(typeName), unionPairs);
    }
  sprintf(valName, "%sVal", simple->newName);
  strcpy(cppName, simple->newName);
  enterNamePair(strdup(valName), strdup(cppName), unionPairs);
}

/********************************************************************/

/* generator::buildYaccUnionExtensionPairs

Returned Value: none

Called By: generator::buildYaccUnionElementPairs

If an element has a type that has extensions, the extensions must also
be entered in the YACC union. This function puts a pair into the
unionPairs std::list for each extension.

Example - This might build the namePair from which is printed:

  UK_Address *                        UK_AddressVal; 

*/

void generator::buildYaccUnionExtensionPairs( /* ARGUMENTS                   */
 std::list<XmlComplexType *> * extensions, /* list of extensions to add      */
 std::list<namePair *> * unionPairs)       /* list of name pairs, built here */
{
  std::list<XmlComplexType *>::iterator iter;
  static char valName[NAMESIZE];

  for (iter = extensions->begin(); iter != extensions->end(); iter++)
    {
      sprintf(valName, "%sVal", (*iter)->newName);
      enterNamePair(strdup(valName), (*iter)->newName, unionPairs);
    }
}

/********************************************************************/

/* generator::checkBaseArgs

Returned Value: none

Called By:
  checkBaseArgs (recursive)
  printCppCodeComplexExtend
  printCppHeaderComplexExtend

This looks through the base class to see if it has a non-empty sequence
or any attributes. If a non-empty sequence is found, hasSequence is set
to 1. If an attribute is found, hasAttribs is set to 1. If the base
class is derived, this calls itself recursively to look through the base
class of the base class.

*/ 

void generator::checkBaseArgs( /* ARGUMENTS                */
 char * baseName,              /* name of base class       */
 int * hasSequence,            /* 1=has a sequence, 0=not  */ 
 int * hasAttribs)             /* 1=has attributes, 0=not  */ 
{
  XmlComplexType * complx;
  XmlOtherContent * other;
  XmlComplexContent * comp;
  XmlSimpleContent * simp;
  XmlComplexExtension * extend;
  XmlSequence * sequence;

  complx = findComplexClass(baseName);
  if (complx == 0)
    {
      fprintf(stderr, "base class %s not found in checkBaseArgs\n",
	      baseName);
      exit(1);
    }
  if ((other = dynamic_cast<XmlOtherContent *>(complx->item)))
    {
      if ((sequence = dynamic_cast<XmlSequence *>(other->base)) &&
	  sequence->items && sequence->items->size())
	*hasSequence = 1;
      if (other->attribs && other->attribs->size())
	*hasAttribs = 1;
    }
  else if ((comp = dynamic_cast<XmlComplexContent *>(complx->item)))
    {
      if ((extend = dynamic_cast<XmlComplexExtension *>(comp->item)))
	{
	  if ((sequence = dynamic_cast<XmlSequence *>(extend->item))
	       && sequence->items && sequence->items->size())
	    *hasSequence = 1;
	  if (extend->attribs && extend->attribs->size())
	    *hasAttribs = 1;
	  if ((*hasSequence == 0) || (*hasAttribs == 0))
	    checkBaseArgs(extend->base, hasSequence, hasAttribs);
	}
      else if ((dynamic_cast<XmlComplexRestriction *>(comp->item)))
	{
	  fprintf(stderr, "cannot handle complex restriction\n");
	  exit(1);
	}
    }
  else if ((simp = dynamic_cast<XmlSimpleContent *>(complx->item)))
    {
      *hasSequence = 0;
      *hasAttribs = 1;
    }
}

/********************************************************************/

/* generator::checkBaseArgsSimple

Returned Value: none

Called By:
  checkBaseArgsSimple (recursive)
  printCppHeaderSimpleExtend

This looks through the base class to see if it has any attributes. If
an attribute is found, hasAttribs is set to 1. If the base class is
derived, this calls itself recursively to look through the base class
of the base class.

*/ 

void generator::checkBaseArgsSimple( /* ARGUMENTS                          */
 char * baseName,                    /* name of base class                 */
 char * basePrefix,                  /* prefix of base class (may be null) */
 int * hasAttribs)                   /* 1=has attributes, 0=not            */ 
{
  XmlComplexType * complx;
  XmlSimpleContent * simp;
  XmlSimpleContentExtension * extend;

  if ((complx = findComplexClass(baseName)))
    {
      if ((simp = dynamic_cast<XmlSimpleContent *>(complx->item)))
	{
	  if ((extend = dynamic_cast<XmlSimpleContentExtension *>(simp->item)))
	    {
	      if (extend->attribs && extend->attribs->size())
		*hasAttribs = 1;
	      else
		checkBaseArgsSimple(extend->base,
				    extend->basePrefix, hasAttribs);
	    }
	  else if (dynamic_cast<XmlSimpleContentRestriction *>(simp->item))
	    {
	      fprintf(stderr, "cannot handle simple content restriction\n");
	      exit(1);
	    }
	  else
	    {
	      fprintf(stderr,
		      "bad simple content type in checkBaseArgsSimple\n");
	      exit(1);
	    }
	}
      else if (dynamic_cast<XmlOtherContent *>(complx->item))
	{
	  fprintf(stderr,
		  "simple extension may not have other content base\n");
	  exit(1);
	}
      else if (dynamic_cast<XmlComplexContent *>(complx->item))
	{
	  fprintf(stderr,
		  "simple extension may not have complex content base\n");
	  exit(1);
	}
    }
  else if (findSimpleClass(baseName));
  else if (basePrefix && (strcmp(basePrefix, XmlCppBase::wg3Prefix) == 0));
  else
    {
      fprintf(stderr, "base class %s not found in checkBaseArgsSimple\n",
	      baseName);
      exit(1);
    }
}

/********************************************************************/

/* generator::checkListRestrictions

Returned Value: none

Called By: generator::printCppHeaderRestrictSList

This goes through the restrictions on an sList and checks that
there are not two Xmllength restrictions, two XmlMaxLength
restrictions, or two XmlMinLength restrictions.

If an error is found, this prints an error message and exits.

*/

void generator::checkListRestrictions(           /* ARGUMENTS    */
 std::list<XmlRestrictionType *> * restrictions) /* restrictions */
{
  XmlLength * length = 0;
  XmlMaxLength * maxLength = 0;
  XmlMinLength * minLength = 0;
  std::list<XmlRestrictionType *>::iterator iter;
  
  for (iter = restrictions->begin(); iter != restrictions->end(); iter++)
    {
      if ((length == 0) &&
	  (length = dynamic_cast<XmlLength *>(*iter)));
      else if ((length = dynamic_cast<XmlLength *>(*iter)))
	{
	  fprintf(stderr, "list must not have two length restrictions\n");
	  exit(1);
	}
      if ((maxLength == 0) &&
	  (maxLength = dynamic_cast<XmlMaxLength *>(*iter)));
      else if ((maxLength = dynamic_cast<XmlMaxLength *>(*iter)))
	{
	  fprintf(stderr, "list must not have two maxLength restrictions\n");
	  exit(1);
	}
      if ((minLength == 0) &&
	  (minLength = dynamic_cast<XmlMinLength *>(*iter)));
      else if ((minLength = dynamic_cast<XmlMinLength *>(*iter)))
	{
	  fprintf(stderr, "list must not have two minLength restrictions\n");
	  exit(1);
	}
    }
}

/********************************************************************/

/* generator::checkName

Returned Value: none

Called By:
  generator::processIncludes
  generator::readSchema

This checks that the given fileName is not too long and ends with
.xsd. If the name fails either check, this prints an error message and
exits. If the name passes, this puts the base name (.xsd removed) with
path into the bassNameWithPath buffer and puts the base name without
path into the bassNameNoPath buffer.

*/

void generator::checkName( /* ARGUMENTS                                */
 char * fileName,          /* name of schema to read, including path   */
 char * bassNameWithPath,  /* buffer to put base name with path in     */
 char * bassNameNoPath)    /* buffer to put base name without path in  */
{
  int n; 
  n = strlen(fileName);
  if (n > (NAMESIZE - 2))
    {
      fprintf(stderr, "schema file name must have fewer than %d characters\n",
	      (NAMESIZE - 1));
      exit(1);
    }
  n -= 4;
  if (strcmp(fileName+n, ".xsd"))
    {
      fprintf(stderr,
	      "schema file name %s must end with .xsd\n", fileName);
      exit(1);
    }
  strcpy(bassNameWithPath, fileName);
  bassNameWithPath[n] = 0;  // remove .xsd suffix from bassNameWithPath
  for ( ; n >= 0; n--)
    if ((bassNameWithPath[n] == ' ') ||
	(bassNameWithPath[n] == '\\') ||
	(bassNameWithPath[n] == '/'))
      break;
  strcpy(bassNameNoPath, (bassNameWithPath + n + 1));
}

/********************************************************************/

/* generator::checkNumberRestrictions

Returned Value: none

Called By:
  generator::printCppHeaderRestrictNumber

This goes through the restrictions on an sList of numbers and checks
that there are not two XmlMaxExclusive restrictions, two
XmlMinExclusive restrictions, two XmlMaxInclusive restrictions, two
XmlMinInclusive restrictions, or two pattern restrictions. It also
checks that there are no other types of restrictions.

If an error is found, this prints an error message and exits.

*/

void generator::checkNumberRestrictions(         /* ARGUMENTS    */
 std::list<XmlRestrictionType *> * restrictions) /* restrictions */
{
  XmlMaxExclusive * maxEx = 0;
  XmlMaxInclusive * maxIn = 0;
  XmlMinExclusive * minEx = 0;
  XmlMinInclusive * minIn = 0;
  XmlPattern * pat = 0;
  std::list<XmlRestrictionType *>::iterator iter;
  
  for (iter = restrictions->begin(); iter != restrictions->end(); iter++)
    {
      if ((maxEx == 0) &&
	  (maxEx = dynamic_cast<XmlMaxExclusive *>(*iter)));
      else if ((maxEx = dynamic_cast<XmlMaxExclusive *>(*iter)))
	{
	  fprintf(stderr,
		  "number must not have two maxExclusive restrictions\n");
	  exit(1);
	}
      else if ((maxIn == 0) &&
	       (maxIn = dynamic_cast<XmlMaxInclusive *>(*iter)));
      else if ((maxIn = dynamic_cast<XmlMaxInclusive *>(*iter)))
	{
	  fprintf(stderr,
		  "number must not have two maxInclusive restrictions\n");
	  exit(1);
	}
      else if ((minEx == 0) &&
	  (minEx = dynamic_cast<XmlMinExclusive *>(*iter)));
      else if ((minEx = dynamic_cast<XmlMinExclusive *>(*iter)))
	{
	  fprintf(stderr,
		  "number must not have two minExclusive restrictions\n");
	  exit(1);
	}
      else if ((minIn == 0) &&
	       (minIn = dynamic_cast<XmlMinInclusive *>(*iter)));
      else if ((minIn = dynamic_cast<XmlMinInclusive *>(*iter)))
	{
	  fprintf(stderr,
		  "number must not have two minInclusive restrictions\n");
	  exit(1);
	}
      else if ((pat == 0) &&
	       (pat = dynamic_cast<XmlPattern *>(*iter)));
      else if ((pat = dynamic_cast<XmlPattern *>(*iter)))
	{
	  fprintf(stderr,
		  "number must not have two pattern restrictions\n");
	  exit(1);
	}
      else if (dynamic_cast<XmlEnumeration *>(*iter))
	{
	  fprintf(stderr, "warning - cannot handle enumeration of number\n");
	}
      else
	{
	  fprintf(stderr, "bad number restriction type\n");
	  exit(1);
	}
    }
}

/********************************************************************/

/* generator::enterAttribute

Returned Value: none

Called By: generator::flattenAttributes

This inserts an attribute newName in the attributeInfo std::list in
alphabetical order. Attribute newNames may be reused in various
elements, so a check is made that an attribute newName is not already
entered before inserting the newName. The attributeInfo std::list is
needed to print the lex file.

*/

void generator::enterAttribute( /* ARGUMENTS                      */
 char * newName)                /* name to put into attributeInfo */
{
  std::list<char *>::iterator iter;
  int val;

  for (iter = attributeInfo->begin(); iter != attributeInfo->end(); iter++)
    {
      val = strcmp(newName, *iter);
      if (val == 0)
	return;
      if (val < 0)
	{
	  attributeInfo->insert(iter, newName);
	  break;
	}
    }
  if (iter == attributeInfo->end())
    attributeInfo->push_back(newName);
}

/********************************************************************/

/* generator::enterAttributeInList

Returned Value: none

Called By: generator::findAllAttributes

This inserts an XmlAttributeLoner in the attributes std::list in
alphabetical order by newName.

When attrib is from the attributes std::list of a restriction (indicated by
the sameOK argument being true), attrib must have the same newName as an
attribute in an ancestor (which should already be on the attributes
std::list). Otherwise, this signals an error and exits.

When attrib is from the attributes std::list of an extension or a
non-derived complex type (indicated by the sameOK argument being
false), attrib must not have the same newName as an attribute in an
ancestor. Otherwise, this signals an error and exits.

FIX - This is currently not checking that an attribute in a restriction
(sameOK is true) restricts the attribute it replaces (which has the same
newName).

*/

void generator::enterAttributeInList(      /* ARGUMENTS                       */
 XmlAttributeLoner * attrib,               /* attribute to put in list        */
 std::list<XmlAttributeLoner *> * attributes, /* list to put attribute in     */
 bool sameOK)                              /* true=same newName OK, false=not */
{
  std::list<XmlAttributeLoner *>::iterator iter;
  int val;

  for (iter = attributes->begin(); iter != attributes->end(); iter++)
    {
      val = strcmp(attrib->newNameRef, (*iter)->newNameRef);
      if (val == 0)
	{
	  if (sameOK)
	    { // replace the attribute that was on the list with attrib
	      iter = attributes->erase(iter);
	      attributes->insert(iter, attrib);
	    }
	  else
	    {
	      fprintf(stderr, "attribute name %s used twice\n",
		      attrib->newNameRef);
	      exit(1);
	    }
	}
      else if (val < 0)
	{
	  attributes->insert(iter, attrib);
	  break;
	}
    }
  if (iter == attributes->end())
    {
      if (sameOK)
	{
	  fprintf(stderr, "new attribute %s used in a restriction\n",
		      attrib->newNameRef);
	  exit(1);
	}
      else
	attributes->push_back(attrib);
    }
}

/********************************************************************/

/* generator::enterAttributeGroupRefable

Returned Value: none

Called By:  generator::buildClasses

This makes the attributeGroupRefables std::list, derived from the contents2
of the schema.


*/

void generator::enterAttributeGroupRefable( /* ARGUMENTS                 */
 XmlAttributeGroupRefable * refable)        /* attribute group to record */
{
  static std::map<std::string, XmlAttributeGroupRefable *>::iterator iter;

  iter = attributeGroupRefables->find(refable->name);
  if (iter == attributeGroupRefables->end())
    (*attributeGroupRefables)[refable->name] = refable;
  else
    {
      fprintf(stderr, "duplicate reference attribute group name %s. Exiting\n",
	      refable->newName);
      exit(1);
    }
}

/********************************************************************/

/* generator::enterAttributeLonerRefable

Returned Value: none

Called By:  generator::buildClasses

This makes the attributeLonerRefables std::map, derived from the contents2
of the schema.


*/

void generator::enterAttributeLonerRefable( /* ARGUMENTS                 */
 XmlAttributeLonerRefable * refable)        /* attribute loner to record */
{
  static std::map<std::string, XmlAttributeLonerRefable *>::iterator iter;

  iter = attributeLonerRefables->find(refable->name);
  if (iter == attributeLonerRefables->end())
    (*attributeLonerRefables)[refable->name] = refable;
  else
    {
      fprintf(stderr, "duplicate reference attribute loner name %s. Exiting\n",
	      refable->newName);
      exit(1);
    }
}

/********************************************************************/

/* generator::enterClass

Returned Value: none

Called By:
  generator::buildClasses
  generator::buildClassesIncluded

This inserts aClass in the classes std::list in alphabetical order (by
newName). If a duplicate class name is found, this prints an error
message and exits.

*/

void generator::enterClass( /* ARGUMENTS                 */
 XmlType * aClass)          /* class to put into classes */
{
  std::list<XmlType *>::iterator iter;
  XmlSimpleType * simple;
  XmlComplexType * complx;
  char * newName;
  int result;

  if ((complx = dynamic_cast<XmlComplexType *>(aClass)))
    newName = complx->newName;
  else if ((simple = dynamic_cast<XmlSimpleType *>(aClass)))
    newName = simple->newName;
  else
    {
      fprintf(stderr, "bug: class not XmlSimpleType or XmlComplexType\n");
      exit(1);
    }
  for (iter = classes->begin(); iter != classes->end(); iter++)
    {
      if ((complx = dynamic_cast<XmlComplexType *>(*iter)))
	{
	  result = strcmp(newName, complx->newName);
	  if (result < 0)
	    {
	      classes->insert(iter, aClass);
	      break;
	    }
	  else if (result == 0)
	    {
	      fprintf(stderr, "duplicate class name %s. Exiting\n", newName);
	      exit(1);
	    }
	}
      else if ((simple = dynamic_cast<XmlSimpleType *>(*iter)))
	{
	  result = strcmp(newName, simple->newName);
	  if (result < 0)
	    {
	      classes->insert(iter, aClass);
	      break;
	    }
	  else if (result == 0)
	    {
	      fprintf(stderr, "duplicate class name %s. Exiting\n", newName);
	      exit(1);
	    }
	}
    }
  if (iter == classes->end())
    classes->push_back(aClass);
}

/********************************************************************/

void generator::enterClass2( /* ARGUMENTS                 */
 XmlType * aClass)           /* class to put into classes */
{
  static std::map<std::string, XmlComplexType *>::iterator iter;
  static std::map<std::string, XmlSimpleType *>::iterator ator;
  XmlSimpleType * simple;
  XmlComplexType * complx;

  if ((complx = dynamic_cast<XmlComplexType *>(aClass)))
     {
       iter = allComplex->find(complx->newName);
       if (iter == allComplex->end())
	 (*allComplex)[complx->newName] = complx;
       else
	 {
	   fprintf(stderr,
		   "duplicate complex class name %s. Exiting\n",
		   complx->newName);
	   exit(1);
	 }
     }
   else if ((simple = dynamic_cast<XmlSimpleType *>(aClass)))
     {
       ator = allSimple->find(simple->newName);
       if (ator == allSimple->end())
	 (*allSimple)[simple->newName] = simple;
       else
	 {
	   fprintf(stderr,
		   "duplicate simple class name %s. Exiting\n",
		   simple->newName);
	   exit(1);
	 }
     }
  else
    {
      fprintf(stderr, "bug: class not XmlSimpleType or XmlComplexType\n");
      exit(1);
    }
}

/********************************************************************/

/* generator::enterElementInfo

Returned Value: none

Called By:
  generator::buildClasses
  generator::buildElementInfo

This assigns a prodBase name to each element that arrives in an
elementInfo, which is normally all elements in the schema. This may
produce annoyingly long prodBase names. Names more than 65 characters
long will cause an error when YACC is generated, so if the methods
described here make a prodBase more than 35 characters long, the
prodBase will later be abbreviated in abbreviateNames.

The prodBase name has two to four parts. In general, it has the form A_B_C_D

A is the newName of the element.
B is the newName of the type of the element.
_C is used iff
   (1) _D is used, or
   (2) the element may occur zero times, or
   (3) the element may occur more than once.
   The value of C is the number of times the element may occur.
_D is used iff the element may occur zero times or more
   than once.
   The value of D is u if the number of occurrences is unbounded.
   Otherwise, the value of D is that number.

For example, if the newName of the element is foo, the newName of the
type of the element is bar, the element is optional (may occur 0
times), and the element may occur at most 3 times, the prodBase
name will be foo_bar_0_3.

With the same name and type as above, if the number of occurrences of the
element is constrained to be 1, the prodBase name would be foo_bar.

The prodBase name is used in:
  buildYaccChoiceItemRule
  buildYaccExtensionSequenceItems
  buildYaccRestrictSListRule
  buildYaccRulesEnd
  buildYaccSequenceItems
  buildYaccTypeElementPairs
  buildYaccTypeElementPairsComplex
  buildYaccTypeElementPairsSimple

If an info with the same prodBase name is not already on the elementInfos
std::list, this inserts the info in the elementInfos std::list in
alphabetical order (by element name and then type name). If there is
an info on the list with the same prodBase, the element's prodBase is
set to the prodBase of the one on the list so that if the prodBase of
the one on the list is abbreviated (by abbreviateNames), the element's
prodBase will still be identical.

The info argument may be a duplicate of an elementInfo already on the
std::list for two reasons.
1. Two or more different XML types may use elements with the same
name and type.
2. When an XmlComplexExtension is being processed, this function will be
called for each element of the parent type and the child type.  Since
the parent type elements are also processed here when the parent type
is being handled, elements of the parent type will be processed more
than once.

Any particular element name may be used in more than one place in an
XML schema, so the names of the elements in the elementInfos may not be
unique. Thus, there may be several entries in elementInfos with the
same element name.

Recall that (1) for minOccurs, a negative value means no value was given,
so the actual value is the default value, one, and (2) for maxOccurs,
a value of -1 means "unbounded" was given, and a value of -2 or less means
no value was given, so the actual value is the default value, one.

The prodBase name is used in the YACC file. The name needs to be as
complex as it is since different YACC rules must be written for each
different name.  The items determining what YACC rule must be written
are the same as the ones used in building the prodName.

This does not guarantee that the prodName is as unique as it needs to be
since strange combinations of names could produce the same prodName.
For example, the combination of foo_bar and baz will produce the same
name as the combination of foo and bar_baz (foo_bar_baz). However, if
that happens, compiler errors will occur, so it won't sneak through.

This prints an error message and exits if the maximum of the number of
times the element can occur is less than the minimum.

*/

void generator::enterElementInfo( /* ARGUMENTS                            */
 elementInfo * info)              /* elementInfo to put into elementInfos */
{
  std::list<elementInfo *>::iterator iter;
  XmlElementLocal * element;
  int maxi;
  int mini;
  int val;
  int n;

  static char prodBase[NAMESIZE];
  static char typName[NAMESIZE];
  element = info->element;
  maxi = ((element->maxOccurs < -1) ? 1 : element->maxOccurs);
  mini = ((element->minOccurs <  0) ? 1 : element->minOccurs);
  if ((maxi != -1) && (maxi < mini))
    {
      fprintf(stderr,
	      "maxOccurs, %d, must not be less than minOccurs, %d, in %s\n",
	      maxi, mini, element->name);
      exit(1);
    }
  if (info->complexType)
    n = sprintf(prodBase, "%s_%s",
		element->newName, info->complexType->newName);
  else if (info->simpleType)
    n = sprintf(prodBase, "%s_%s",
		element->newName, info->simpleType->newName);
  else // if basic XML type
    {
      findCppTypeName(element->newTyp, typName);
      n = sprintf(prodBase, "%s_%s", element->newName, typName);
    }

  if (maxi != 1)
    { // need suffixes indicating minimum and maximum
      n += sprintf(prodBase+n, "_%d", mini); // use _N suffix for minimum
      if (maxi == -1)
	{ // unbounded occurrences, so use _u suffix
	  sprintf(prodBase+n, "_u");
	}
      else
	{ // bounded list, so use _N suffix for maximum
	  sprintf(prodBase+n, "_%d", maxi);
	}
    }
  else if (mini != 1 )
    { // no suffix for maximum, but need suffix for minimum; use _N suffix
      n += sprintf(prodBase+n, "_0");
    }
  element->prodBase = strdup(prodBase);
  for (iter = elementInfos->begin(); iter != elementInfos->end(); iter++)
    {
      val = strcmp(element->prodBase, (*iter)->element->prodBase);
      if (val == 0)
	{
	  element->prodBase = (*iter)->element->prodBase;
	  return; // already processed this element or one just like it
	}
      if (val <= 0)
	{
	  elementInfos->insert(iter, info);
	  break;
	}
    }
  if (iter == elementInfos->end())
    {
      elementInfos->push_back(info);
    }
}

/********************************************************************/

/* generator::enterElementRefable

Returned Value: none

Called By:
  generator::buildClasses

This:
1. makes the elementRefables std::map containing all XmlElementRefable's.
2. builds the substitutes list of each of the XmlElementRefable's.

This is called for each of the XmlElementRefable's. It checks the
refable against each XmlElementRefable already in the elementRefables
map. If an XmlElementRefable is found with the same name as one already
on the elementRefables list, this prints and error message and exits.
If the name of XmlElementRefable B is in the substitutionGroup of
XmlElementRefable D, B is added to the substitutes of D.

The refable being processed is added to the elementRefables.

*/

void generator::enterElementRefable(  /* ARGUMENTS            */
 XmlElementRefable * refable)         /* the refable to enter */
{
  static std::map<std::string, XmlElementRefable *>::iterator iter;

  for (iter = elementRefables->begin();
       iter != elementRefables->end(); iter++)
    {
      if (iter->first == refable->newName)
	{
	  fprintf(stderr, "duplicate reference element name %s. Exiting\n",
	  	  refable->newName);
	  exit(1);
	}
      if (iter->second->substitutionGroup &&
	  (strcmp(iter->second->substitutionGroup, refable->newName) == 0))
	{
	  refable->substitutes.push_back(iter->second);
	}
      if (refable->substitutionGroup &&
	  (strcmp(refable->substitutionGroup, iter->second->newName) == 0))
	{
	  iter->second->substitutes.push_back(refable);
	}
    }
  (*elementRefables)[refable->newName] = refable;
}

/********************************************************************/

/* generator::enterKid

Returned Value: none

Called By: generator::printCppCodeComplexExtend

This enters the kid type in the extensions of the parent type if it is
not already there. The extensions std::list of an XmlComplexType is not
kept in any particular order.

*/

void generator::enterKid( /* ARGUMENTS                          */
 XmlComplexType * parent, /* parent type for which to enter kid */
 XmlComplexType * kid)    /* kid type                           */
{
  std::list<XmlComplexType *> * extensions;
  std::list<XmlComplexType *>::iterator iter;
  
  extensions = parent->extensions;
  if (extensions)
    {
      for (iter = extensions->begin(); iter != extensions->end(); iter++)
	{
	  if (*iter == kid)
	    return; // kid is already on the list, so do nothing
	}
      extensions->push_back(kid); // kid not already on list, so add kid
    }
  else
    {
      parent->extensions = new std::list<XmlComplexType *>;
      parent->extensions->push_back(kid);
    }
}

/********************************************************************/

/* generator::enterLoner

Returned Value: none

Called By: flattenAttributes

This enters the loner in the lonerList in alphabetical order by name.

*/

void generator::enterLoner(                  /* ARGUMENTS                    */
 XmlAttributeLoner * loner,                  /* item to put into list        */
 std::list<XmlAttributeLoner *> * lonerList) /* list in which to enter loner */
{
  std::list<XmlAttributeLoner *>::iterator iter;
  int result;

  for (iter = lonerList->begin(); iter != lonerList->end(); iter++)
    {
      result = strcmp(loner->newNameRef, (*iter)->newNameRef);
      if (result < 0)
	{
	  lonerList->insert(iter, loner);
	  break;
	}
      else if (result == 0)
	{
	  fprintf(stderr, "attribute name %s reused in same type\n",
		  loner->newNameRef);
	  exit(1);
	}
    }
  if (iter == lonerList->end())
    lonerList->push_back(loner);
}

/********************************************************************/

/* generator::enterName

Returned Value: none

Called By: generator::buildSomeExtensions

This adds a name to the names std::list in alphabetical order.
If the name is already on the std::list, it is not entered again.
What gets entered is the name, not a copy of the name.

*/

void generator::enterName(  /* ARGUMENTS              */
 char * name,               /* name to enter          */
 std::list<char *> * names) /* list of names to build */
{
  std::list<char *>::iterator iter;
  int nameVal;

  for (iter = names->begin(); iter != names->end(); iter++)
    {
      nameVal = strcmp(name, *iter);
      if (nameVal == 0) // duplicate found, do not enter it
	return;
      else if (nameVal < 0)
	{
	  names->insert(iter, name);
	  break;
	}
    }
  if (iter == names->end())
    names->push_back(name);
}

/********************************************************************/

/* generator::enterNamePair

Returned Value: none

Called By:
  generator::buildYaccBasicRule
  generator::buildYaccChoiceRule
  generator::buildYaccComplexAnyRule
  generator::buildYaccComplexElementRule
  generator::buildYaccComplexExtRule
  generator::buildYaccElementListRule
  generator::buildYaccExtensionRule1
  generator::buildYaccExtensionRule2
  generator::buildYaccRestrictSListRule
  generator::buildYaccSequenceRule
  generator::buildYaccSimpleListRule
  generator::buildYaccSimpleRule
  generator::buildYaccTypeElementPairs
  generator::buildYaccTypeExtensionPairs
  generator::buildYaccUnionElementPairs
  generator::buildYaccUnionExtensionPairs

This adds a namePair to the pairs std::list in alphabetical order,
first by name1, and then by name2 (if name1s are the same). Any
duplicate pairs are entered only once.

*/

void generator::enterNamePair(  /* ARGUMENTS                  */
 char * name1,                  /* first name in pair         */
 char * name2,                  /* second name in pair        */
 std::list<namePair *> * pairs) /* list of namePairs to build */
{
  std::list<namePair *>::iterator iter;
  int name1val;
  int name2val;

  for (iter = pairs->begin(); iter != pairs->end(); iter++)
    {
      name1val = strcmp(name1, (*iter)->name1);
      if (name1val == 0)
	{
	  name2val = strcmp(name2, (*iter)->name2);
	  if (name2val == 0) // duplicate found, do not enter it
	    return;
	  else if (name2val < 0)
	    {
	      pairs->insert(iter, new namePair(name1, name2));
	      break;
	    }
	}
      else if (name1val < 0)
	{
	  pairs->insert(iter, new namePair(name1, name2));
	  break;
	}
    }
  if (iter == pairs->end())
    pairs->push_back(new namePair(name1, name2));
}


/********************************************************************/

/* generator::findAllAttributes

Returned Value: none

Called By:
  generator::findAllAttributes (recursively)
  generator::printCppCodeComplex

This finds the attributes of an XmlComplexType and all of its
ancestors and puts them in the allAttributes std::list in alphabetical
order. If an attribute occurs in a restriction, it must restrict an
existing attribute and have the same name as an attribute in an
ancestor. In this case the attribute in the restriction will replace
the attribute in the ancestor in the allAttributes std::list.

This function should be called for all complex types to prevent the
occurrence of duplicate attribute names, which are illegal.
enterAttributeInList, which is called by this function, signals an
error if sameOK is false and the same name is used more than once.

FIX - Duplicate element names are not illegal in XSDL (if they have
the same type). However, duplicate element names will result in bad
C++ code, since duplicate field names will not work in C++. Duplicate
element names are not currently being detected.

*/

void generator::findAllAttributes(
 XmlComplexType * complx,
 std::list<XmlAttributeLoner *> * allAttributes)
{
  XmlOtherContent * other;
  XmlComplexContent * comp;
  XmlSimpleContent * simp;
  XmlComplexExtension * compExtend;
  XmlComplexRestriction * compRestrict;
  XmlComplexType * baseType;
  XmlSimpleContentExtension * simpExtend;
  XmlSimpleContentRestriction * simpRestrict;
  std::list<XmlAttributeLoner *> * addThese;
  std::list<XmlAttributeLoner *>::iterator iter;
  bool sameOK;

  if ((other = dynamic_cast<XmlOtherContent *>(complx->item)))
    {
      addThese = other->newAttribs;
      sameOK = false;
    }
  else if ((comp = dynamic_cast<XmlComplexContent *>(complx->item)))
    {
      if ((compExtend = dynamic_cast<XmlComplexExtension *>(comp->item)))
	{
	  baseType = findComplexClass(compExtend->base);
	  if (baseType == 0)
	    {
	      fprintf(stderr, "base type %s not found\n", compExtend->base);
	      exit(1);
	    }
	  findAllAttributes(baseType, allAttributes);
	  addThese = compExtend->newAttribs;
	  sameOK = false;
	}
      else if ((compRestrict =
		dynamic_cast<XmlComplexRestriction *>(comp->item)))
	{
	  baseType = findComplexClass(compRestrict->base);
	  if (baseType == 0)
	    {
	      fprintf(stderr, "base type %s not found\n", compRestrict->base);
	      exit(1);
	    }
	  findAllAttributes(baseType, allAttributes);
	  addThese = compRestrict->newAttribs;
	  sameOK = true;
	}
      else
	{
	  fprintf(stderr, "bad complex content type in findAllAttributes\n");
	  exit(1);
	}
    }
  else if ((simp = dynamic_cast<XmlSimpleContent *>(complx->item)))
    {
      if ((simpExtend =
	   dynamic_cast<XmlSimpleContentExtension *>(simp->item)))
	{ // base type could be complex type with simple content 
	  baseType = findComplexClass(simpExtend->base);
	  if (baseType)
	    findAllAttributes(baseType, allAttributes);
	  addThese = simpExtend->newAttribs;
	  sameOK = false;
	}
      else if ((simpRestrict =
	   dynamic_cast<XmlSimpleContentRestriction *>(simp->item)))
	{ // base type could be complex type with simple content 
	  baseType = findComplexClass(simpRestrict->base);
	  if (baseType)
	    findAllAttributes(baseType, allAttributes);
	  addThese = simpRestrict->newAttribs;
	  sameOK = true;
	}
      else
	{
	  fprintf(stderr, "bad simple content type in findAllAttributes\n");
	  exit(1);
	}
    }
  else
    {
      fprintf(stderr, "bad complex type in findAllAttributes\n");
      exit(1);
    }
  if (addThese)
    {
      for (iter = addThese->begin(); iter != addThese->end(); iter++)
	enterAttributeInList(*iter, allAttributes, sameOK);
    }
}

/********************************************************************/

/* generator::findComplexClass

Returned Value: XmlComplexType *
  This looks through the allComplex (all complex classes from all included
  schemas) and returns the class with the given name, or null if it is
  not found.

Called By:
  generator::buildClasses
  generator::buildElementInfo
  generator::buildYaccExtensionBaseArgs
  generator::buildYaccExtensionBaseItems
  generator::checkBaseArgs
  generator::checkBaseArgsSimple
  generator::hasAttribs
  generator::printCppCodeBaseArgs
  generator::printCppCodeChoiceItems
  generator::printCppCodeComplexExtend
  generator::printCppCodePrintLines
  generator::printCppCodeSimpleExtend
  generator::printCppHeaderBaseArgs
  generator::printCppHeaderBaseArgsSimple
  generator::printCppHeaderBaseItemsSimple
  generator::printCppHeaderSchemaClassesComplex
  generator::printYaccRulesStart

*/

XmlComplexType * generator::findComplexClass( /* ARGUMENTS             */
 char * name)                                 /* name of class to find */
{
  static std::map<std::string, XmlComplexType *>::iterator iter;

  iter = allComplex->find(name);
  return ((iter == allComplex->end()) ? 0 : iter->second);
}

/********************************************************************/

/* generator::findCppTypeName

Returned Value: none

Called By:
  generator::buildYaccRestrictSListRule
  generator::buildYaccRulesEnd
  generator::buildYaccSimpleListRule
  generator::buildYaccUnionElementPairs
  generator::printCppCodeBadAttributes
  generator::printCppCodeChoiceItems
  generator::printCppCodePrintElement
  generator::printCppCodeSimpleExtend
  generator::printCppCodeSimpleList
  generator::printCppHeaderBaseArgsSimple
  generator::printCppHeaderBaseItemsSimple
  generator::printCppHeaderSequenceArgs
  generator::printCppHeaderSequenceItems
  generator::printCppHeaderSimple
  generator::printCppHeaderSimpleExtend
  generator::printCppHeaderSimpleList

This finds the C++ type name corresponding to the wg3 type name and
copies it into the cppTypeName. All such names are formed by writing
the prefix "Xml" followed by the wg3TypeName with the first letter in
upper case.

The names copied into cppTypeName are all the names of C++
classes. Where the cppTypeName is used in the caller, a "*" will be
added to change the type to a pointer. Using pointers makes it easy to
handle optional items (as null pointers).

*/

void generator::findCppTypeName( /* ARGUMENTS                     */
 char * wg3TypeName,             /* name of XML type              */
 char * cppTypeName)             /* array to put C++ type name in */
{
  snprintf(cppTypeName, NAMESIZE, "Xml%c%s", toupper(wg3TypeName[0]),
	   wg3TypeName+1);
}

/********************************************************************/

/* generator::findElementRefable

Returned Value: XmlElementRefable *
  This looks through the elementRefables and returns the one with
  the given name, or null if it is not found.

Called By: generator::buildElementInfo

*/

XmlElementRefable * generator::findElementRefable( /* ARGUMENTS               */
 char * name)                                      /* name of refable to find */
{
  static std::map<std::string, XmlElementRefable *>::iterator iter;

  iter = elementRefables->find(name);
  return ((iter == elementRefables->end()) ? 0 : iter->second);
}

/********************************************************************/

/* generator::findRootXmlTypeComplex

Returned Value: none

Called By: printCppCodeSimpleExtend

This finds the XML root type of an XmlComplexType complx whose root
type is a basic type. This is possible iff the item in complx is an
XmlSimpleContent. Thus, this function should not be called unless it
is known that the item in complx is an XmlSimpleContent whose item is
an XmlSimpleContentExtension.

*/

void generator::findRootXmlTypeComplex( /* ARGUMENTS                        */
 XmlComplexType * complx,       /* complex type for which to find root type */
 char * rootXmlTypeName)        /* array in which to put the answer         */
{
  XmlSimpleContentExtension * extend;
  XmlSimpleContent * content;
  XmlSimpleType * simple;
  XmlComplexType * comp;

  if ((content = dynamic_cast<XmlSimpleContent *>(complx->item)))
    {
      if ((extend = dynamic_cast<XmlSimpleContentExtension *>(content->item)))
	{
	  if (extend->basePrefix &&
	      (strcmp(extend->basePrefix, XmlCppBase::wg3Prefix) == 0))
	    {
	      strcpy(rootXmlTypeName, extend->base);
	    }
	  else if ((simple = findSimpleClass(extend->newBase)))
	    {
	      findRootXmlType(simple, rootXmlTypeName);
	    }
	  else if ((comp = findComplexClass(extend->newBase)))
	    {
	      findRootXmlTypeComplex(comp, rootXmlTypeName);
	    }
	  else
	    {
	      fprintf(stderr, "bad extension type in findRootXmlTypeComplex\n");
	      exit(1);
	    }
	}
      else if ((dynamic_cast<XmlSimpleContentRestriction *>(content->item)))
	{
	  fprintf(stderr, "cannot handle restriction of complex type\n");
	  exit(1);
	}
    }
  else
    {
      fprintf(stderr, "findRootXmlTypeComplex should not have been called\n");
      exit(1);
    }
}

/********************************************************************/

/* generator::findRootXmlType

Returned Value: none

Called By:
  generator::buildYaccRestrictSListRule
  generator::buildYaccSimpleRule
  generator::findRootXmlType (recursively)
  generator::printCppCodeSimple
  generator::printCppHeaderSimple

This finds the XML root type of an XmlSimpleType, "simple".

If the item in "simple" is an sList, this finds the XML root type of
the type that is listed.

If the item in "simple" is an XmlSimpleRestriction, this finds the XML
root type of simple.

In either case, it puts the name of the XML root type into the
rootXmlTypeName array. It calls itself recursively if necessary to
handle a restriction of a restriction.

*/

void generator::findRootXmlType( /* ARGUMENTS                               */
 XmlSimpleType * simple,         /* simple type for which to find root type */
 char * rootXmlTypeName)         /* array in which to put the answer        */
{
  XmlSimpleRestriction * restrict;
  XmlSimpleList * sList;  

  if (simple == 0)
    {
      fprintf(stderr, "null simple type in findRootXmlType\n");
      exit(1);
    }
  if ((sList = dynamic_cast<XmlSimpleList *>(simple->item)))
    {
      if (sList->typePrefix &&
	  (strcmp(sList->typePrefix, XmlCppBase::wg3Prefix) == 0))
	{
	  strcpy(rootXmlTypeName, sList->newItemType);
	}
      else
	findRootXmlType(findSimpleClass(sList->newItemType), rootXmlTypeName);
    }
  else if ((restrict = dynamic_cast<XmlSimpleRestriction *>(simple->item)))
    {
      if (restrict->basePrefix &&
	  (strcmp(restrict->basePrefix, XmlCppBase::wg3Prefix) == 0))
	{
	  strcpy(rootXmlTypeName, restrict->base);
	}
       else
	findRootXmlType(findSimpleClass(restrict->newBase), rootXmlTypeName);
    }
  else
    {
      fprintf(stderr, "cannot find basic type for %s\n", simple->name);
      exit(1);
    }
}

/********************************************************************/

/* generator::findSimpleClass

Returned Value: XmlSimpleType *
  This looks through the allSimple (all simple classes from all included
  schemas) and returns the simple class with the given name, or null
  if no such class is found.

Called By:
  generator::buildElementInfo
  generator::buildYaccRestrictSListRule
  generator::buildYaccRulesEnd
  generator::buildYaccTypeElementPairs
  generator::buildYaccUnionElementPairs
  generator::checkBaseArgsSimple
  generator::findRootXmlType
  generator::printCppCodeChoiceItems
  generator::printCppCodePrintElement
  generator::printCppCodeSimple
  generator::printCppCodeSimpleExtend
  generator::printCppHeaderSchemaClassesComplex
  generator::printCppHeaderSchemaClassesSimple
  generator::printCppHeaderSimple

*/

XmlSimpleType * generator::findSimpleClass( /* ARGUMENTS             */
 char * name)                               /* name of class to find */
{
  static std::map<std::string, XmlSimpleType *>::iterator iter;

  iter = allSimple->find(name);
  return ((iter == allSimple->end()) ? 0 : iter->second);
}

/********************************************************************/

/* generator::fitPrint

Returned Value: none

Called By: generator::printCppCodePrintElement

This expects the buffer to contain a string such as

  XFPRINTF "<UnitLength>%f</UnitLength>\n", *UnitLength);

which is a function call.

If the string has filled the buffer, it probably has been truncated, so
an error message is printed and the function exits.

If the string fits on an 80-character line, it is printed as is.

Otherwise, if there is a comma somewhere in the string, the string is
printed on two lines. The first line has everything up to and including
the last comma in the string. The second line has the rest of the
string indented so that the first non-space character is under the
character after the opening left parenthesis. For example, if the string
above were too long to fit, it would be printed as follows.

  XFPRINTF "<UnitLength>%f</UnitLength>\n",
          *UnitLength);

If there is no comma in a string that does not fit, it is printed as is.

It might be worth the trouble to break the string into more than two
pieces if there is more than one comma.

*/

void generator::fitPrint( /*  ARGUMENTS                                      */
 FILE * aFile,            /* the file in which to print                      */
 char * buffer,           /* the buffer to print                             */
 int length,              /* the number of characters in the buffer before 0 */
 int tooBig)              /* the size of the buffer                          */
{
  int k;
  int n;

  if (length >= tooBig)
    {
      fprintf(stderr, "string too big for buffer\n");
      exit(1);
    }
  if (length < 80)
    {
      fprintf(aFile, "%s", buffer);
      return;
    }
  for (k = length; ((k > 0) && (buffer[k] != ',')); k--);
  if (k == 0)
    {
      fprintf(aFile, "%s", buffer);
      return;
    }
  buffer[k] = 0;
  fprintf(aFile, "%s,\n", buffer);
  buffer[k] = ',';
  for (n = 0; ((n < k) && (buffer[n] != '(')); n++)
    {
      fputc(' ', aFile);
    }
  if (n == k)
    {
      fprintf(stderr, "bad string in fitPrint\n");
      exit(1);
    }
  fprintf(aFile, "%s", (buffer + k + 1));
}

/********************************************************************/

/* generator::flattenAttributes

Returned Value: none

Called By:
  generator::printCppHeaderSchemaClassNames
  generator::flattenAttributes (recursively)

This builds the "knew" std::list of XmlAttributeLoners alphabetically (by
newName) from all the attributes in the "old" std::list of XmlAttributors.
The function goes through the old std::list and processes each XmlAttributor.

An XmlAttributor may be an XmlAttributeLoner or an XmlAttributeGroupRef.

If the XmlAttributor is an XmlAttributeLoner, it is called decl.  Decl
may have a name or a ref.  If decl has a name, decl is not changed. If
decl has a ref, the referenced XmlAttributeLonerRefable, lonerRefable,
is found, and decl is modified by setting ref to 0 and inserting the
contents of lonerRefable. In either case, a pointer to decl is added
to the knew std::list.

If the XmlAttributor is an XmlAttributeGroupRef, it may only have a ref.
In this case, the referenced XmlAttributeGroupRefable, groupRefable,
is found in the attributeGroupRefables. If the groupRefable already has
newAttribs, each one is put into the knew std::list unless it is
already there. Otherwise, the function calls itself recursively using
the a new std::list "newer" of XmlAttributeLoner for knew and the
attributes std::list of the groupRefable. Then newAttribs of the
groupRefable is set to the newer and the elements of the newer are
added to knew.

In addition, enterAttribute is called for every XmlAttributeLoner put on
the knew std::list. This is simply recording the newNameRef of the
XmlAttributeLoner, which is needed for building the lex file.

*/

void generator::flattenAttributes(      /* ARGUMENTS                   */
 std::list<XmlAttributeLoner *> * knew, /* attribute list to build     */
 std::list<XmlAttributor *> * old)      /* attribute list to take from */
{
  XmlAttributeLoner * decl;
  XmlAttributeGroupRef * groupRef;
  std::list<XmlAttributeLoner *> * newer;
  std::list<XmlAttributor *>::iterator iter;
  std::list<XmlAttributeLoner *>::iterator ator;
  std::map<std::string, XmlAttributeLonerRefable *>::iterator etor;
  std::map<std::string, XmlAttributeGroupRefable *>::iterator otor;
  XmlAttributeLonerRefable * lonerRefable;
  XmlAttributeGroupRefable * groupRefable;
  
  for (iter = old->begin(); iter != old->end(); iter++)
    {
      if ((decl = dynamic_cast<XmlAttributeLoner *>(*iter)))
	{
	  if (decl->ref)
	    {
	      etor = attributeLonerRefables->find(decl->ref);
	      if (etor == attributeLonerRefables->end())
		{
		  fprintf(stderr, "referenced item %s not found\n", decl->ref);
		  exit(1);
		}
	      else
		{ // newNameRef does not need changing
		  lonerRefable = etor->second;
		  decl->ref = 0;
		  decl->defalt = lonerRefable->defalt;
		  decl->fixed = lonerRefable->fixed;
		  decl->name = lonerRefable->name;
		  decl->typ = lonerRefable->typ;
		  decl->simple = lonerRefable->simple;
		}
	    }
	  enterAttribute(decl->newNameRef);
	  enterLoner(decl, knew);
	}
      else if ((groupRef = dynamic_cast<XmlAttributeGroupRef *>(*iter)))
	{
	  otor = attributeGroupRefables->find(groupRef->ref);
	  if (otor == attributeGroupRefables->end())
	    {
	      fprintf(stderr, "referenced item %s not found\n", groupRef->ref);
	      exit(1);
	    }
	  else
	    {
	      groupRefable = otor->second;
	      if (groupRefable->newAttribs == 0)
		{
		  newer = new std::list<XmlAttributeLoner *>;
		  groupRefable->newAttribs = newer;
		  flattenAttributes(newer, groupRefable->xmlAttributes);
		}
	      else
		newer = groupRefable->newAttribs;
	      for (ator = newer->begin(); ator != newer->end(); ator++)
		{
		  enterAttribute((*ator)->newNameRef);
		  enterLoner((*ator), knew);
		}
	    }
	}
    }
}

/********************************************************************/

generator::generator() /* NO ARGUMENTS */
{
  allComplex = 0;
  allSimple = 0;
  attributeInfo = new std::list<char *>;
  attributeLonerRefables = 0;
  attributeGroupRefables = 0;
  // baseNameNoPath is a buffer
  // baseNameWithPath is a buffer
  ccFile = 0;
  changeMap = new std::map<std::string, std::list<char *> *>;
  // className is a buffer
  // classBaseName is a buffer
  classes = new std::list<XmlType *>;
  contents1 = 0;
  contents2 = 0;
  dummyName = strdup("_");
  elementInfos = new std::list<elementInfo *>;
  elementRefables = 0;
  extensionInfos = new std::list<char *>;
  hasBoolean = false;
  hasDate = false;
  hasDateTime = false;
  hasDecimal = false;
  hasDouble = false;
  hasFloat = false;
  hasID = false;
  hasIDREF = false;
  hasInt = false;
  hasInteger = false;
  hasLong = false;
  hasNMTOKEN = false;
  hasNonNegativeInteger = false;
  hasPositiveInteger = false;
  hasString = false;
  hasToken = false;
  hasUnsignedInt = false;
  hasUnsignedLong = false;
  headerName = 0;
  hhFile = 0;
  includedSchemas = new std::list<char *>;
  lexFile = 0;
  mockCount = 0;
  moreIncludes = new std::list<char *>;
  stringInput = false;
  // subordinates is a local list
  top = 0;
  target = 0;
  // text is a buffer
  wholeFlag = false;
  yaccFile = 0;
  yyprefix = 0;
}

/********************************************************************/

generator::~generator() {}

/********************************************************************/

/* generator::hasAttribs

Returned Value: bool
  This returns true if the XML type with the given baseName has XML attributes.
  If not, this returns false.

Called By:
  generator::buildYaccExtensionRule1
  generator::buildYaccExtensionRule2
  generator::hasAttribs (recursively)

Since the type may be derived from a type that has attributes, this must
call itself recursively.

*/

bool generator::hasAttribs( /* ARGUMENTS             */
 char * baseName)           /* name of XML base type */
{
  XmlComplexType * complx;
  XmlOtherContent * other;
  XmlComplexContent * comp;
  XmlComplexExtension * extend;

  complx = findComplexClass(baseName);
  if (complx == 0)
    {
      fprintf(stderr, "base class %s not found in hasAttribs\n",
	      baseName);
      exit(1);
    }
  if ((other = dynamic_cast<XmlOtherContent *>(complx->item)))
    {
      if (other->newAttribs)
	return true;
      else
	return false;
    }
  else if ((comp = dynamic_cast<XmlComplexContent *>(complx->item)))
    {
      if ((extend = dynamic_cast<XmlComplexExtension *>(comp->item)))
	{
	  if (extend->attribs)
	    return true;
	  else
	    return hasAttribs(extend->base);
	}
      else if ((dynamic_cast<XmlComplexRestriction *>(comp->item)))
	{
	  fprintf(stderr, "cannot handle complex restriction\n");
	  return false;
	}
    }
  return false;
}

/********************************************************************/

/* generator::isNumber

Returned Value: bool
  This returns true if the typeName is one of those listed in the function.
  Otherwise, this returns false.

Called By:
  generator::printCppCodeSimple
  generator::printCppHeaderSimple

*/

bool generator::isNumber(
 char * typeName)
{
  return ((strcmp(typeName, "XmlDecimal")            == 0) ||
	  (strcmp(typeName, "XmlDouble")             == 0) ||
	  (strcmp(typeName, "XmlFloat")              == 0) ||
	  (strcmp(typeName, "XmlInt")                == 0) ||
	  (strcmp(typeName, "XmlInteger")            == 0) ||
	  (strcmp(typeName, "XmlLong")               == 0) ||
	  (strcmp(typeName, "XmlNonNegativeInteger") == 0) ||
	  (strcmp(typeName, "XmlPositiveInteger")    == 0) ||
	  (strcmp(typeName, "XmlShort")              == 0) ||
	  (strcmp(typeName, "XmlUnsignedInt")        == 0) ||
	  (strcmp(typeName, "XmlUnsignedLong")       == 0) ||
	  (strcmp(typeName, "XmlUnsignedShort")      == 0));
}

/********************************************************************/

/* generator::isRestrictedSList

Returned Value: none

Called By:
  generator::buildYaccRestrictSListRule
  generator::buildYaccSimpleRule
  generator::isRestrictedSList (recursively)
  generator::printCppCodeSimple
  generator::printCppHeaderSimple

"simple" is known to be a restriction when the first time this is
called. If the base thing being restricted is an sList, this fills in
the three buffers as indicated by their names and eventually returns
true. Otherwise, it eventually returns false. The value of level is
reset, but the value is relevant only if this returns true.  This
calls itself recursively if necessary to handle a restriction of a
restriction. When it is called recursively, "simple" may be
a restriction or an sList.

*/

bool generator::isRestrictedSList( /* ARGUMENTS                            */
 XmlSimpleType * simple,           /* simple type to check                 */
 char * typeName,                  /* buffer for type name - see above     */
 char * sListItemPrefix,           /* buffer for prefix of sList item type */ 
 char * sListItemTypeName,         /* buffer for name of sList item type   */ 
 int * level)                      /* nesting level, increased here        */
{
  XmlSimpleRestriction * restrict;
  XmlSimpleList * sList;  

  if (simple == 0)
    {
      return false;
    }
  if ((sList = dynamic_cast<XmlSimpleList *>(simple->item)))
    {
      if (*level == 1)
	strncpy(typeName, simple->newName, NAMESIZE);
      if (sList->typePrefix)
	strncpy(sListItemPrefix, sList->typePrefix, NAMESIZE);
      else
	sListItemPrefix[0] = 0;
      strncpy(sListItemTypeName, sList->newItemType, NAMESIZE);
    }
  else if ((restrict = dynamic_cast<XmlSimpleRestriction *>(simple->item)))
    {
      if (*level == 1)
	strncpy(typeName, simple->newName, NAMESIZE);
      *level += 1; 
      return isRestrictedSList(findSimpleClass(restrict->newBase), typeName,
			       sListItemPrefix, sListItemTypeName, level);
    }
  else
    {
      return false;
    }
  return true;
}

/********************************************************************/

/* generator::isStringy

Returned Value: bool
  This returns true if the typeName is one of those listed in the function.
  Otherwise, this returns false.

Called By:
  generator::printCppCodeSimple
  generator::printCppHeaderSimple

*/

bool generator::isStringy(char * typeName)
{
  return ((strcmp(typeName, "XmlString") == 0)  ||
	  (strcmp(typeName, "XmlAnyURI") == 0)  ||
	  (strcmp(typeName, "XmlNMTOKEN") == 0) ||
	  (strcmp(typeName, "XmlToken") == 0)   ||
	  (strcmp(typeName, "XmlID") == 0)      ||
	  (strcmp(typeName, "XmlIDREF") == 0));
}

/********************************************************************/

/* main

Returned Value: int 0

Called By: user

This:
1. checks the arguments. Three argument pairs and one singleton
   argument are allowed, as follows.
     -p <prefix>
     -h <old header file name>
     -x <XML schema name>
     -s
   <prefix> is the prefix to use in place of yy in the Lex and YACC files
       that are generated. If this is used, gen.yyprefix is set to it.
       Otherwise, gen.yyprefix is set to "yy".
   <XML schema name> is the name of a schema file in canonical
       form. It may be a complete path name and must end with ".xsd".
       This must be used. The schema file is read.
   <old header file name> is the name of an existing header file for
       an earlier version of the schema that may include hand-written
       changes; if this is used, the file is read.
   -s means that input should be taken from a string. If this is used,
      gen.stringInput (which has a default value of false) is set to true.
   If there is any error in the arguments, this calls usageMessage,
   (which explains how to call the generator) then exits.
2. calls readOldHeader to read the old header if there is one.
3. calls readSchema to read the schema file.
4. calls processIncludes to process any included XML schema files
5. calls buildClasses to find the items in the parse tree from which classes
   will be printed, which are the top-level complexTypes and the top-level
   simpleTypes. This also sets the "top" generator variable.
6. calls printSelf to print everything.
7. calls reviewChanges to check that all header changes have been transcribed.

FIX - maybe - This is reading at most one old header, whereas there may
be several old headers when there are includes. This might be fixed by
putting old headers in the same directory where new code is being
written. The generator could check for an existing header of the given
name before printing a new one.

*/

int main(       /* ARGUMENTS                                      */
 int argc,      /* one more than the number of command arguments  */
 char * argv[]) /* array of executable name and command arguments */
{
  generator gen;
  std::map<std::string, XmlComplexType *> allComplex;
  std::map<std::string, XmlSimpleType *> allSimple;
  std::map<std::string, XmlElementRefable *> elementRefables;
  std::map<std::string, XmlAttributeLonerRefable *> attributeLonerRefables;
  std::map<std::string, XmlAttributeGroupRefable *> attributeGroupRefables;
  std::list<char *> includeds; // list of included schema names
  int schemaIndex = 0;
  int headerIndex = 0;
  int prefixIndex = 0;
  int includeIndex = 0;
  int appIncludeIndex = 0;
  int mockCount = 1001;
  int n;

  for (n = 1; n < argc; )
    {
      if (strcmp(argv[n], "-p") == 0)
	{
	  if (prefixIndex)
	    gen.usageMessage(argv[0]);
	  else
	    {
	      prefixIndex = n+1;
	      n += 2;
	    }
	}
      else if (strcmp(argv[n], "-h") == 0)
	{
	  if (headerIndex)
	    gen.usageMessage(argv[0]);
	  else
	    {
	      headerIndex = n+1;
	      n += 2;
	    }
	}
      else if (strcmp(argv[n], "-x") == 0)
	{
	  if (schemaIndex)
	    gen.usageMessage(argv[0]);
	  else
	    {
	      schemaIndex = n+1;
	      n += 2;
	    }
	}
      else if (strcmp(argv[n], "-s") == 0)
	{
	  if (gen.stringInput == true)
	    gen.usageMessage(argv[0]);
	  else
	    {
	      gen.stringInput = true;
	      n++;
	    }
	}
      else if (strcmp(argv[n], "-i") == 0)
	{
	  if (includeIndex)
	    gen.usageMessage(argv[0]);
	  else
	    {
	      includeIndex = n+1;
	      n += 2;
	    }
	}
      else if (strcmp(argv[n], "-a") == 0)
	{
	  if (appIncludeIndex)
	    gen.usageMessage(argv[0]);
	  else
	    {
	      appIncludeIndex = n+1;
	      n += 2;
	    }
	}
      else
	gen.usageMessage(argv[0]);
    }
  if (prefixIndex)
    gen.yyprefix = argv[prefixIndex];
  else
    gen.yyprefix = strdup("yy"); // the default prefix in Lex and YACC
  if (includeIndex)
    includePrefix = argv[includeIndex];
  else
    includePrefix = strdup("");
  if (appIncludeIndex)
    appIncludePrefix = argv[appIncludeIndex];
  else
    appIncludePrefix = strdup("");
  if (headerIndex)
    gen.readOldHeader(argv[headerIndex]);
  if (schemaIndex == 0)
    gen.usageMessage(argv[0]);
  gen.mockCount = &mockCount;
  gen.readSchema(argv[schemaIndex], true);
  includeds.push_back(gen.baseNameNoPath);
  gen.allComplex = &allComplex;
  gen.allSimple = &allSimple;
  gen.elementRefables = &elementRefables;
  gen.attributeLonerRefables = &attributeLonerRefables;
  gen.attributeGroupRefables = &attributeGroupRefables;
  gen.processIncludes(&includeds);
  gen.buildClasses();
  gen.replaceSubstitutionGroups();
  gen.printSelf();
  gen.reviewChanges();
  return 0;
}

/********************************************************************/

/* generator::printCppCode

Returned Value: none

Called By:
  printNotTop
  printTop

This prints the C++ code file.

The code file is generated from a set of classes that represent an
XML schema in canonical form.  The code file is printed in the same
directory as the schema file.

The name for the code file is the baseNameWithPath with "Classes.cc" appended.

*/

void generator::printCppCode() /* NO ARGUMENTS */
{
  static char nameBuf[NAMESIZE];

  sprintf(nameBuf, "%sClasses.cc", baseNameWithPath);
  ccFile = fopen(nameBuf, "w");
  if (ccFile == 0)
    {
      fprintf(stderr, "could not open file %s for writing\n", nameBuf);
      exit(1);
    }
  hhFile = ccFile; // need this since header printers called by code printers
  printCppCodeStart();
  printCppCodeSchemaClasses();
  if (top)
    printCppCodeEnd();
  fclose(ccFile);
  ccFile = 0;
  hhFile = 0;
}

/********************************************************************/

/* generator::printCppCodeAttributeArgs

Returned Value: none

Called By: printCppCodeBaseArgs

This prints in the C++ code some arguments of the base type
constructor called in a child type constructor. The child is an
extension of the base type. The arguments are those corresponding to
attributes in the base type. This prints only the argument
names, not the types.  Each argument is on a separate line.

The value of comma will be 0 if no other argument has been printed
yet, and in this case a newline and the argument are printed.  The
value of comma will be 1 if an argument has been printed previously,
and in this case a comma, a newline, and the argument are printed. If
this prints one or more arguments, the value of comma is set to 1.

FIX - Check that comma is set correctly if this is the top level.

*/

void generator::printCppCodeAttributeArgs( /* ARGUMENTS                     */
 std::list<XmlAttributeLoner *> * attribs, /* list of attributes to print   */
 const char * typeName,                    /* name of type                  */
 int * comma,                              /* non-zero = start with a comma */
 const char * space)                       /* extra space at start of line  */
{
  std::list<XmlAttributeLoner *>::iterator iter;
  char * name;
  
  if (!attribs) //may be null
    return;
  for (iter = attribs->begin(); iter != attribs->end(); iter++)
    { // OK if list empty
      name = (*iter)->newNameRef;
      fprintf(hhFile, "%s\n", (*comma ? "," : ""));
      fprintf(hhFile, "%s %sIn", space,  name);
      *comma = 1;
    }
}

/********************************************************************/

/* generator::printCppCodeAttributeDeletes

Returned Value: none

Called By:
  generator::printCppCodeComplexExtend
  generator::printCppCodeOtherMinimal
  generator::printCppCodeSequence
  generator::printCppCodeSimpleExtend

This prints in the C++ code file the deletes representing attributes
when the class represents an XML schema complexType and the
complexType has attributes.

This may called if attribs is 0 or is not 0 but is empty.

*/

void generator::printCppCodeAttributeDeletes( /* ARGUMENTS                   */
 std::list<XmlAttributeLoner *> * attribs)    /* list of attributes to print */
{
  std::list<XmlAttributeLoner *>::iterator iter;

  if (attribs && attribs->size())
    {
      for (iter = attribs->begin(); iter != attribs->end(); iter++)
	{
	  fprintf(ccFile, "  delete %s;\n", (*iter)->newNameRef);
	}
    }
}

/********************************************************************/

/* generator::printCppCodeAttributeSettings

Returned Value: none

Called By:
  generator::printCppCodeChoiceConstructors
  generator::printCppCodeComplexExtend
  generator::printCppCodeOtherMinimal
  generator::printCppCodeSequence
  generator::printCppCodeSimpleExtend

This prints in the C++ code file the settings representing attributes
when the class represents an XML schema complexType and the
complexType has attributes.

*/

void generator::printCppCodeAttributeSettings( /* ARGUMENTS                   */
 std::list<XmlAttributeLoner *> * attribs)     /* list of attributes to print */
{
  std::list<XmlAttributeLoner *>::iterator iter;

  if (!attribs) //may be null
    return;
  for (iter = attribs->begin(); iter != attribs->end(); iter++)
    { // OK if list empty
      fprintf(ccFile, "  %s = %sIn;\n",
	      (*iter)->newNameRef, (*iter)->newNameRef);
    }
}

/********************************************************************/

/* generator::printCppCodeAttributeSettings0

Returned Value: none

Called By:
  generator::printCppCodeChoiceConstructors
  generator::printCppCodeComplexExtend
  generator::printCppCodeOtherMinimal
  generator::printCppCodeSequence
  generator::printCppCodeSimpleExtend

This prints in the C++ code file the settings representing attributes
when the class represents an XML schema complexType and the
complexType has attributes. All the pointers to attributes are set to 0.
Otherwise, they may be non-zero garbage.

*/

void generator::printCppCodeAttributeSettings0( /* ARGUMENTS                 */
 std::list<XmlAttributeLoner *> * attribs)      /* list of attributes to set */
{
  std::list<XmlAttributeLoner *>::iterator iter;

  if (!attribs) //may be null
    return;
  for (iter = attribs->begin(); iter != attribs->end(); iter++)
    { // OK if list empty
      fprintf(ccFile, "  %s = 0;\n", (*iter)->newNameRef);
    }
}

/********************************************************************/

/* generator::printCppCodeBadAttributes

Returned Value: none

Called By:  generator::printCppCodeComplex

The badAttributes function this writes takes a std::list of AttributePair
as an argument. For each AttributePair in that std::list, the function
makes some checks and then sets the value of the C++ field representing the
XML attribute with the name given in the AttributePair. The checks are:

1. The same attribute is not set twice.
2. The value given in the AttributePair is not bad.
3. The named attribute is an attribute of the class.
4. The attribute is present if it is required.

The badAttributes function returns a bool that is true if a check fails
(i.e., if the attributes are bad) and false otherwise.

*/

void generator::printCppCodeBadAttributes(       /* ARGUMENTS               */
 char * className,                               /* name of complex class   */
 std::list<XmlAttributeLoner *> * allAttributes) /* all attributes of class */
{
  std::list<XmlAttributeLoner *>::iterator iter;
  XmlAttributeLoner * decl;
  bool isBasic;
  int needsElse = 0;
  static char attClassName[NAMESIZE];
  char * attName;

  fprintf(ccFile, "\n");
  fprintf(ccFile, "bool %s::badAttributes(\n", className);
  fprintf(ccFile,
" std::list<AttributePair *> * attributes)\n"
"{\n"
"  std::list<AttributePair *>::iterator iter;\n"
"  AttributePair * decl;\n"
"\n"
"  for (iter = attributes->begin(); iter != attributes->end(); iter++)\n"
"    {\n"
"      decl = *iter;\n");
  for (iter = allAttributes->begin(); iter != allAttributes->end(); iter++)
    {
      decl = *iter;
      attName = decl->newNameRef;
      isBasic = (decl->typPrefix &&
		 (strcmp(decl->typPrefix, XmlCppBase::wg3Prefix) == 0));
      if (isBasic)
	  findCppTypeName(decl->newTyp, attClassName);
      else
	strcpy(attClassName, decl->newTyp);
      fprintf(ccFile, (needsElse ? "      else " : "      "));
      fprintf(ccFile, "if (decl->name == \"%s\")\n", decl->name);
      needsElse = 1;
      fprintf(ccFile, "        {\n");
      fprintf(ccFile, "          %s * %sVal;\n", attClassName, attName);
      fprintf(ccFile, 
"          if (%s)\n"
"            {\n"
"              fprintf(stderr, \"two values for %s in %s\\n\");\n"
"              return true;\n"
"            }\n", attName, attName, className);

      fprintf(ccFile, "          %sVal = new %s(decl->val.c_str());\n",
	      attName, attClassName);
      fprintf(ccFile, "          if (%sVal->bad)\n", attName);
      fprintf(ccFile, "            {\n");
      fprintf(ccFile, "              fprintf(stderr, \"bad value %%s for "
	      "%s in %s\\n\",\n", attName, className);
      fprintf(ccFile, "                      decl->val.c_str());\n");
      fprintf(ccFile, "              return true;\n");
      fprintf(ccFile, "            }\n");
      fprintf(ccFile, "          else\n");
      fprintf(ccFile, "            %s = %sVal;\n", attName, attName);
      fprintf(ccFile,"        }\n");
    }
  fprintf(ccFile, 
"      else\n"
"        {\n"
"          fprintf(stderr, \"bad attribute in %s\\n\");\n"
"          return true;\n"
"        }\n"
"    }\n", className);
  for (iter = allAttributes->begin(); iter != allAttributes->end(); iter++)
    {
      decl = *iter;
      if (decl->use == XmlCppBase::required)
        {
          fprintf(ccFile, "  if (%s == 0)\n", decl->newNameRef);
          fprintf(ccFile, "    {\n");
          fprintf(ccFile, "      fprintf(stderr, "
                  "\"required attribute \\\"%s\\\" missing in %s\\n\");\n",
                  decl->newNameRef, className);
          fprintf(ccFile, "      return true;\n");
          fprintf(ccFile, "    }\n");
        }
    }
  fprintf(ccFile,
"  for (iter = attributes->begin(); iter != attributes->end(); iter++)\n"
"    {\n"
"      delete *iter;\n"
"    }\n");
  fprintf(ccFile, "  return false;\n");
  fprintf(ccFile, "}\n");
}

/********************************************************************/

/* generator::printCppCodeBaseArgs

Returned Value: none

Called By:
  generator::printCppCodeBaseArgs (recursively)
  generator::printCppCodeComplexExtend

This prints in the C++ code file the arguments to the constructor of
an extension that exist because the base type has them.

This calls itself recursively to handle extension depths greater than one.

*/

void generator::printCppCodeBaseArgs( /* ARGUMENTS                      */
 char * baseName,                     /* name of base class             */
 int * comma,                         /* non-zero = start with a comma  */
 bool printAttribs)                   /* true=print args for attributes */
{
  XmlComplexType * complx;
  XmlOtherContent * other;
  XmlComplexContent * comp;
  XmlComplexExtension * extend;
  XmlSequence * sequence;

  complx = findComplexClass(baseName);
  if (complx == 0)
    {
      fprintf(stderr, "base class %s not found in printCppCodeBaseArgs\n",
	      baseName);
      exit(1);
    }
  if ((other = dynamic_cast<XmlOtherContent *>(complx->item)))
    {
      if (printAttribs && other->newAttribs)
	{
	  printCppCodeAttributeArgs(other->newAttribs,
				    complx->newName, comma, "   ");
	}
      if ((sequence = dynamic_cast<XmlSequence *>(other->base)))
	{
	  printCppCodeSequenceArgs(sequence->items, comma);
	}
    }
  else if ((comp = dynamic_cast<XmlComplexContent *>(complx->item)))
    {
      if ((extend = dynamic_cast<XmlComplexExtension *>(comp->item)))
	{
	  printCppCodeBaseArgs(extend->base, comma, printAttribs);
	  if (printAttribs && extend->attribs)
	    {
	      printCppCodeAttributeArgs(extend->newAttribs,
					complx->newName, comma, "   ");
	    }
	  if ((sequence = dynamic_cast<XmlSequence *>(extend->item)))
	    {
	      printCppCodeSequenceArgs(sequence->items, comma);
	    }
	}
      else if ((dynamic_cast<XmlComplexRestriction *>(comp->item)))
	{
	  fprintf(stderr, "cannot handle complex restriction\n");
	}
    }
}

/********************************************************************/

/* generator::printCppCodeChoice

Returned Value: none

Called By:  generator::printCppCodeComplex

This prints in the C++ code file the implementation of a class derived
from an XmlChoice.

See the discussion of choice at the beginning of this file.

FIX - This is not dealing with minOccurs or maxOccurs of the choice.
     Add tests for list size in constructor and printSelf.

*/

void generator::printCppCodeChoice(        /* ARGUMENTS                      */
 XmlChoice * choice,                       /* XmlChoice to print code for    */
 char * newName,                           /* name for class                 */
 std::list<XmlAttributeLoner *> * attribs) /* flattened attributes of choice */
{
  static char className[NAMESIZE];

  sprintf(className, "%sChoicePair", newName);
  fprintf(ccFile, "%s::%s() {}\n", className, className);
  fprintf(ccFile, "\n");
  fprintf(ccFile, "%s::%s(\n", className, className);
  fprintf(ccFile, " whichOne %sTypeIn,\n", newName);
  fprintf(ccFile, " %sVal %sValueIn)\n", newName, newName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  %sType = %sTypeIn;\n", newName, newName);
  fprintf(ccFile, "  %sValue = %sValueIn;\n", newName, newName);
  fprintf(ccFile, "}\n");
  fprintf(ccFile, "\n");
  fprintf(ccFile, "%s::~%s()\n", className, className);
  fprintf(ccFile, "{\n");
  printCppCodeChoiceItemDeletes(choice->items, newName);
  fprintf(ccFile, "}\n");
  fprintf(ccFile, "\n");
  fprintf(ccFile, "void %s::PRINTSELFDECL\n", className);
  fprintf(ccFile, "{\n");
  printCppCodeChoiceItems(choice->items, newName);
  fprintf(ccFile, "}\n");
  fprintf(ccFile, "\n");
  printCppCodeChoiceConstructors(newName, attribs, className);
  fprintf(ccFile, "\n");
  fprintf(ccFile, "%s::~%s()\n", newName, newName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  std::list<%sChoicePair *>::iterator iter;\n", newName);
  fprintf(ccFile, "\n");
  fprintf(ccFile,
	  "  for (iter = pairs->begin(); iter != pairs->end(); iter++)\n");
  fprintf(ccFile, "    {\n");
  fprintf(ccFile, "      delete *iter;\n");
  fprintf(ccFile, "    }\n");
  fprintf(ccFile, "  delete pairs;\n");
  fprintf(ccFile, "}\n");
  fprintf(ccFile, "\n");
  fprintf(ccFile, "void %s::PRINTSELFDECL\n", newName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  std::list<%s *>::iterator iter;\n", className);
  fprintf(ccFile, "\n");
  printCppCodePrintAttribs(attribs);
  if (choice->mock == false)
    {
      fprintf(ccFile, "  XFPRINTF \">\\n\");\n");
      fprintf(ccFile, "  SPACESPLUS;\n");
    }
  fprintf(ccFile,
	  "  for (iter = pairs->begin(); iter != pairs->end(); iter++)\n");
  fprintf(ccFile, "    {\n");
  fprintf(ccFile, "      (*iter)->PRINTSELF;\n");
  fprintf(ccFile, "    }\n");
  if (choice->mock == false)
    {
      fprintf(ccFile, "  SPACESMINUS;\n");
    }
  fprintf(ccFile, "}\n");
  //if (attribs && attribs->size())
  //  printCppCodeBadAttributes(attribs, newName, 0);
}

/********************************************************************/

/* generator::printCppCodeChoiceConstructors

Returned Value: none

Called By:  generator::printCppCodeChoice

This prints 2 or 3 constructors for a class derived from an XmlChoice.
1. takes no arguments, does nothing
2. takes pairsIn argument, sets pairs to pairsIn
3. takes attributes and pairsIn arguments, sets attributes,
   sets pairs to pairsIn

*/

void generator::printCppCodeChoiceConstructors( /* ARGUMENTS                 */
 char * newName,                           /* name for class                 */
 std::list<XmlAttributeLoner *> * attribs, /* flattened attributes of choice */
 char * className)                         /* name for ChoicePair class      */
{
  int comma = 0;
  
  // start printing constructor with no arguments
  if (attribs && attribs->size())
    {
      fprintf(ccFile, "%s::%s()\n", newName, newName);
      fprintf(ccFile, "{\n");
      printCppCodeAttributeSettings0(attribs);
      fprintf(ccFile, "  pairs = 0;\n");
      fprintf(ccFile, "}\n");
    }
  else
    fprintf(ccFile, "%s::%s() {}", newName, newName);
  // end printing constructor with no arguments
  // start printing constructor with pairsIn argument
  fprintf(ccFile, "\n");
  fprintf(ccFile, "%s::%s(\n", newName, newName);
  fprintf(ccFile, "  std::list<%s *> * pairsIn)\n", className);
  fprintf(ccFile, "{\n");
  if (attribs && attribs->size())
      printCppCodeAttributeSettings0(attribs);
  fprintf(ccFile, "  pairs = pairsIn;\n");
  fprintf(ccFile, "}\n");
  // end printing constructor with pairsIn argument
  if (attribs && attribs->size())
    {  // start printing constructor with attributes and pairsIn arguments

      comma = 0;
      fprintf(ccFile, "\n");
      fprintf(ccFile, "%s::%s(", newName, newName);
      printCppHeaderAttributeArgs(attribs, &comma, "");
      fprintf(ccFile, ",\n");
      fprintf(ccFile, "  std::list<%s *> * pairsIn)\n", className);
      fprintf(ccFile, "{\n");
      printCppCodeAttributeSettings(attribs);
      fprintf(ccFile, "  pairs = pairsIn;\n");
      fprintf(ccFile, "}\n");
      // end printing constructor with attributes and pairsIn arguments
    }
}

/********************************************************************/

/* generator::printCppCodeChoiceItemDeletes

Returned Value: none

Called By:  generator::printCppCodeChoice

This prints in the C++ code file the part of the destructor
that handles the choices (items) from an XmlChoice.

Example: This might print

  if (AngleToleranceTypeType == AngleToleranceT_1001E)
    delete AngleToleranceTypeValue.AngleToleranceT_1001;
  else if (AngleToleranceTypeType == MinValueE)
    delete AngleToleranceTypeValue.MinValue;

*/

void generator::printCppCodeChoiceItemDeletes(/* ARGUMENTS                   */
 std::list<XmlChoSeqItem *> * items,    /* items to generate enum items from */
 char * newName)                        /* newName of choice                 */
{
  static char cppType[NAMESIZE];  // for C++ equivalent of XML type listed
  std::list<XmlChoSeqItem *>::iterator iter;
  XmlElementLocal * element;

  for (iter = items->begin(); iter != items->end(); iter++)
    {
      if ((element = dynamic_cast<XmlElementLocal *>(*iter)))
	{
	  if (element->minOccurs == 0)
	    {
	      fprintf(stderr,
		      "element in choice must not have minOccurs of 0\n");
	      exit(1);
	    }

	  fprintf(ccFile, "  %sif (%sType == %sE)\n", 
		  ((*iter != items->front()) ? "else " : ""),
		  newName, element->newName);
	  if (element->needList)
	    { // zero or many elements are allowed
	      if (element->typPrefix &&
		  (strcmp(element->typPrefix, XmlCppBase::wg3Prefix) == 0))
		findCppTypeName(element->newTyp, cppType);
	      else
		strcpy(cppType, element->newTyp);
	      fprintf(ccFile, "    {\n");
	      fprintf(ccFile, "      std::list<%s *>::iterator iter;\n",
		      cppType);
	      fprintf(ccFile, "      for (iter = %sValue.%s->begin();\n",
		      newName, element->newName);
	      fprintf(ccFile, "           iter != %sValue.%s->end();\n",
		      newName, element->newName);
	      fprintf(ccFile, "           iter++)\n");
	      fprintf(ccFile, "        delete *iter;\n");
	      fprintf(ccFile, "      delete %sValue.%s;\n",
		      newName, element->newName);
	      fprintf(ccFile, "    }\n");
	    }
	  else
	    { // not oList
	      fprintf(ccFile, "    delete %sValue.%s;\n",
		      newName, element->newName);
	    }
	}
      else
	{
	  fprintf(stderr,
		  "printCppCodeChoiceItemDeletes cannot handle non-element\n");
	  exit(1);
	}
    }
}

/********************************************************************/
/* generator::printCppCodeChoiceItems

Returned Value: none

Called By:  generator::printCppCodeChoice

This prints in the C++ code file the part of the printSelf function
that handles the choices (items) from an XmlChoice.

A complex type does not get a > printed after the element name, but
basic and simple types do. This is because for a complex type, it may
be necessary to print xxx:type="yyy" after the element name and before
the > . The printCppCodeComplexExtend function handles that.

A complex type is printed on multiple lines, whereas basic and simple
types are printed on one line. Hence, a complex type gets a second
doSpaces.

Example: This might print

void LocalUnitType::PRINTSELFDECL
{
  if (LocalUnitTypeType == UserDefinedUnitsE)
    {
      SPACESZERO;
      XFPRINTF "<UserDefinedUnits>");
      XFPRINTF "%f", *(LocalUnitTypeValue.UserDefinedUnits));
      XFPRINTF "</UserDefinedUnits>\n");
    }
  else if (LocalUnitTypeType == TimeE)
    {
      SPACESZERO;
      XFPRINTF "<Time>");
      XFPRINTF "%f", *(LocalUnitTypeValue.Time));
      XFPRINTF "</Time>\n");
    }
...
}

*/

void generator::printCppCodeChoiceItems(/* ARGUMENTS                         */
 std::list<XmlChoSeqItem *> * items,    /* items to generate enum items from */
 char * newName)                        /* newName of choice                 */
{
  static char cppType[NAMESIZE];  // for C++ equivalent of XML type listed
  std::list<XmlChoSeqItem *>::iterator iter;
  XmlElementLocal * element;
  XmlComplexType * complx = 0;
  XmlSimpleType * simple = 0;
  bool isBasic;

  for (iter = items->begin(); iter != items->end(); iter++)
    {
      if ((element = dynamic_cast<XmlElementLocal *>(*iter)))
	{
	  if (element->minOccurs == 0)
	    {
	      fprintf(stderr,
		      "element in choice must not have minOccurs of 0\n");
	      exit(1);
	    }
	  isBasic = (element->typPrefix &&
		     (strcmp(element->typPrefix, XmlCppBase::wg3Prefix) == 0));
	  if (!isBasic)
	    {
	      complx = findComplexClass(element->newTyp);
	      if (!complx)
		simple = findSimpleClass(element->newTyp);
	    }
	  fprintf(ccFile, "  %sif (%sType == %sE)\n", 
		  ((*iter != items->front()) ? "else " : ""),
		  newName, element->newName);
	  fprintf(ccFile, "    {\n");
	  if (element->needList)
	    { // zero or many elements are allowed
	      if (isBasic)
		findCppTypeName(element->newTyp, cppType);
	      else
		strcpy(cppType, element->newTyp);
	      fprintf(ccFile,
		      "      std::list<%s *>::iterator iter;\n", cppType);
	      fprintf(ccFile,
		      "      for (iter = %sValue.%s->begin();\n",
		      newName, element->newName);
	      fprintf(ccFile,
		      "           iter != %sValue.%s->end();\n",
		      newName, element->newName);
	      fprintf(ccFile,
		      "           iter++)\n");
	      fprintf(ccFile, "        {\n");
	      fprintf(ccFile, "          SPACESZERO;\n");
	      if (simple || isBasic)
		{
		  fprintf(ccFile, "          XFPRINTF \"<%s>\");\n",
			  element->newName);
		  fprintf(ccFile, "          (*iter)->PRINTSELF;\n");
		}
	      else if (complx)
		{
		  fprintf(ccFile, "          XFPRINTF \"<%s\");\n",
			  element->newName);
		  fprintf(ccFile, "          (*iter)->PRINTSELF;\n");
		  fprintf(ccFile, "          SPACESZERO;\n");
		}
	      fprintf(ccFile, "          XFPRINTF \"</%s>\\n\");\n",
		      element->newName);
	      fprintf(ccFile, "        }\n");
	    }
	  else
	    { // not oList
	      fprintf(ccFile, "      SPACESZERO;\n");
	      if (simple || isBasic)
		{
		  fprintf(ccFile, "      XFPRINTF \"<%s>\");\n",
			  element->newName);
		  fprintf(ccFile, "      %sValue.%s->PRINTSELF;\n",
			  newName, element->newName);
		}
	      else if (complx)
		{
		  fprintf(ccFile, "      XFPRINTF \"<%s\");\n",
			  element->newName);
		  fprintf(ccFile, "      %sValue.%s->PRINTSELF;\n",
			  newName, element->newName);
		  fprintf(ccFile, "      SPACESZERO;\n");
		}
	      fprintf(ccFile, "      XFPRINTF \"</%s>\\n\");\n",
		      element->newName);
	    }
	  fprintf(ccFile, "    }\n");
	}
      else
	{
	  fprintf(stderr,
		  "printCppCodeChoiceItems cannot handle non-element\n");
	  exit(1);
	}
    }
}

/********************************************************************/

/* generator::printCppCodeComplex

Returned Value: none

Called By:  generator::printCppCodeSchemaClasses

This prints in the C++ code file the code for a class derived from
an XmlComplexType.

When a complex type has no content except possibly attributes
(which may happen if, for example, the type is an abstract parent
type), complex->item is set in xmlSchema.y to an XmlOtherContent that
has no base and may or may not have attributes. Here that is handled
by printCppCodeOtherMinimal.


*/

void generator::printCppCodeComplex( /* ARGUMENTS                        */
 XmlComplexType * complx)            /* XmlComplexType to print code for */
{
  XmlOtherContent * other;
  XmlComplexContent * comp;
  XmlSimpleContent * simp;
  XmlComplexExtension * compExtend;
  XmlSimpleContentExtension * simpExtend;
  XmlSequence * sequence;
  XmlChoice * choice;
  std::list<XmlAttributeLoner *> allAttributes;

  findAllAttributes(complx, &allAttributes);
  fprintf(ccFile, "/* class %s\n", complx->newName);
  fprintf(ccFile, "\n");
  fprintf(ccFile, "*/\n");
  fprintf(ccFile, "\n");
  if ((other = dynamic_cast<XmlOtherContent *>(complx->item)))
    {
      if ((sequence = dynamic_cast<XmlSequence *>(other->base)))
	printCppCodeSequence(sequence, complx->newName, other->newAttribs);
      else if ((choice = dynamic_cast<XmlChoice *>(other->base)))
	printCppCodeChoice(choice, complx->newName, other->newAttribs);
      else
        printCppCodeOtherMinimal(complx->newName, other->newAttribs);
    }
  else if ((comp = dynamic_cast<XmlComplexContent *>(complx->item)))
    {
      if ((compExtend = dynamic_cast<XmlComplexExtension *>(comp->item)))
	printCppCodeComplexExtend(compExtend, complx, complx->newName,
				  complx->name, &allAttributes);
      else if ((dynamic_cast<XmlComplexRestriction *>(comp->item)))
	{
	  fprintf(stderr, "cannot handle complex restriction\n");
	  exit(1);
	}
      else
	{
	  fprintf(stderr, "bad complex content type in printCppCodeComplex\n");
	  exit(1);
	}
    }
  else if ((simp = dynamic_cast<XmlSimpleContent *>(complx->item)))
    {
      if ((simpExtend = dynamic_cast<XmlSimpleContentExtension *>(simp->item)))
	printCppCodeSimpleExtend(simpExtend, complx->newName, complx->name,
				 &allAttributes);
      else if ((dynamic_cast<XmlSimpleContentRestriction *>(simp->item)))
	{
	  fprintf(stderr, "cannot handle simple content restriction\n");
	  exit(1);
	}
      else
	{
	  fprintf(stderr, "bad simple content type in printCppCodeComplex\n");
	  exit(1);
	}
    }
  else
    {
      fprintf(stderr, "bad complex type in printCppCodeComplex\n");
      exit(1); 
    }
  if (allAttributes.size())
    printCppCodeBadAttributes(complx->newName, &allAttributes);
}

/********************************************************************/

/* generator::printCppCodeComplexExtend

Returned Value: none

Called By: generator::printCppCodeComplex

This prints in the C++ code file the implementation of a class derived from
an XmlComplexExtension.

It prints the definitions of 1, 2, or 3 constructors.
1. A constructor taking no arguments.
2. If the extension has a sequence or any part of its base has a sequence,
   a constructor taking arguments corresponding to the sequence items.
3. If the extension has attributes or any part of its base has attributes,
   a constructor taking arguments corresponding to the attributes and any
   sequence items.

The sequence variable is the elements added to the type by the extension.

The arguments to the constructor taking all C++ fields must include
the names of all the C++ fields of the base type from which the
extension is derived. This is accomplished by calling printCppHeaderBaseArgs.

There is some trickiness in having the printSelf function this prints
determine whether or not to print a declaration of the type. A type
declaration is needed in the XML instance file if the type of the element
being printed was declared as a parent type of the extension, but no
type declaration is needed in the XML instance file if the type of the
element being printed is the extension type. To deal with this, the
C++ class for every extension type has a boolean printTypp
field (that class is printed by printCppHeaderComplexExtend). A type
declaration is printed by the printSelf function this writes if
printTypp is true. PrintTypp It is set to false in the constructor
with arguments, but printTypp is set to true by the action for the
YACC rule that recognizes an extension type being used for a parent
type. That action is written by buildYaccExtensionRule2.

*/

void generator::printCppCodeComplexExtend( /* ARGUMENTS                     */
 XmlComplexExtension * extend,    /* XmlComplexExtension to print class for */
 XmlComplexType * complx,         /* complex (the type being extended)      */
 char * newName,                  /* newName of complx                      */
 char * name,                     /* name of complx                         */
 std::list<XmlAttributeLoner *> * allAttributes) /* all attributes of class */
{
  XmlSequence * sequence;
  std::list<XmlChoSeqItem *> * items = 0;
  XmlComplexType * baseType;
  int comma;
  std::list<XmlAttributeLoner *> attribs;
  int hasSequence;
  int hasAttribs;   // set to 1 if extension has attributes, else set to 0

  baseType = findComplexClass(extend->base);
  if (baseType == 0)
    {
      fprintf(stderr, "base type %s not found\n", extend->base);
      exit(1);
    }
  enterKid(baseType, complx);
  sequence = dynamic_cast<XmlSequence *>(extend->item);
  hasSequence = ((sequence && sequence->items->size()) ? 1 : 0);
  hasAttribs = ((extend->attribs && extend->attribs->size()) ? 1 : 0);
  if ((hasSequence == 0) || (hasAttribs == 0)) 
    checkBaseArgs(extend->base, &hasSequence, &hasAttribs);
  // start printing constructor with no arguments
  fprintf(ccFile, "%s::%s() :\n", newName, newName);
  if ((extend->attribs && extend->attribs->size()) ||
      (sequence && sequence->items->size()))
    {
      fprintf(ccFile, "  %s()\n", extend->base);
      fprintf(ccFile, "{\n");
      if (extend->attribs && extend->attribs->size())
	{ // set attributes of extend to 0
	  printCppCodeAttributeSettings0(extend->newAttribs);
	}
      if (sequence && sequence->items->size())
	{ // set sequence items of extend to 0
	  items = sequence->items;
	  printCppCodeSequenceSettings0(items);
	}
      fprintf(ccFile, "}\n");
    }
  else
    fprintf(ccFile, "  %s() {}\n", extend->base);
  // end printing constructor with no arguments
  if (hasSequence)
    {
      // start printing constructor with arguments for sequence items only
      fprintf(ccFile, "\n");
      fprintf(ccFile, "%s::%s(", newName, newName);
      comma = 0;
      printCppHeaderBaseArgs(extend->base, &comma, "", false);
      if (sequence)
	{
	  items = sequence->items;
	  printCppHeaderSequenceArgs(items, &comma, "");
	}
      fprintf(ccFile, ") :\n");
      fprintf(ccFile, "  %s(", extend->base);
      comma = 0;
      printCppCodeBaseArgs(extend->base, &comma, false);
      fprintf(ccFile, ")\n");
      fprintf(ccFile, "{\n");
      if (hasAttribs)// base constructor called so set only new attributes to 0
	printCppCodeAttributeSettings0(extend->newAttribs);
      if (items)
	printCppCodeSequenceSettings(items);
      fprintf(ccFile, "  printTypp = false;\n");
      fprintf(ccFile, "}\n");
      // end printing constructor with arguments for sequence items only
    }
  if (hasAttribs)
    {
      // start printing constructor with arguments for all items
      fprintf(ccFile, "\n");
      fprintf(ccFile, "%s::%s(", newName, newName);
      comma = 0;
      printCppHeaderBaseArgs(extend->base, &comma, "", true);
      if (extend->attribs && extend->attribs->size())
	{
	  printCppHeaderAttributeArgs(extend->newAttribs, &comma, "");
	  comma = 1;
	}
      if (sequence && sequence->items && sequence->items->size())
	{
	  items = sequence->items;
	  printCppHeaderSequenceArgs(items, &comma, "");
	}
      fprintf(ccFile, ") :\n");
      fprintf(ccFile, "  %s(", extend->base);
      comma = 0;
      printCppCodeBaseArgs(extend->base, &comma, true);
      fprintf(ccFile, ")\n");
      fprintf(ccFile, "{\n");
      printCppCodeAttributeSettings(extend->newAttribs);
      if (items)
	printCppCodeSequenceSettings(items);
      fprintf(ccFile, "  printTypp = false;\n");
      fprintf(ccFile, "}\n");
      // end printing constructor with arguments for all items
    }
  // start printing destructor
  fprintf(ccFile, "\n");
  if (items || hasAttribs)
    {
      fprintf(ccFile, "%s::~%s()\n", newName, newName);
      fprintf(ccFile, "{\n");
      if (hasAttribs)
	printCppCodeAttributeDeletes(extend->newAttribs);
      if (items)
	printCppCodeSequenceDeletes(items);
      fprintf(ccFile, "}\n");
     }
  else
    fprintf(ccFile, "%s::~%s() {}\n", newName, newName);
  // end printing destructor
  // start printing printSelf
  fprintf(ccFile, "\n");
  fprintf(ccFile, "void %s::PRINTSELFDECL\n", newName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  if (printTypp)\n");
  fprintf(ccFile, "    XFPRINTF \" xsi:type=\\\"%s\\\"\");\n", name);
  printCppCodePrintAttribs(allAttributes);
  fprintf(ccFile, "  XFPRINTF \">\\n\");\n");
  fprintf(ccFile, "  SPACESPLUS;\n");
  printCppCodePrintLines(baseType, extend->base);
  if (items)
    printCppCodePrintItems(items);
  fprintf(ccFile, "  SPACESMINUS;\n");
  fprintf(ccFile, "}\n");
  // end printing printSelf
}

/********************************************************************/

#define TOMFIX 1

#ifdef TOMFIX

/* generator::printCppCodeDeleteElement

Returned Value: none

Called By: printCppCodeSequenceDeletes

This prints the code that deletes an element. If the element is not 0
and is a list, the elements of the list are deleted, and then the list
is deleted. Otherwise, only the element is deleted. For example:

A. for a list need to test for 0
  if (Nickname)
    {
      std::list<XmlString *>::iterator iter;
      for (iter = Nickname->begin(); iter != Nickname->end(); iter++)
        delete *iter;
      delete Nickname;
    }

B. for not a list do not need to test for 0
  delete Nickname;

*/

void generator::printCppCodeDeleteElement( /* ARGUMENTS          */
 XmlElementLocal * element)                /* element to delete  */
{
  static char typeName[NAMESIZE];
  bool isBasic;

  if (element->newTyp == 0)
    {
      fprintf(stderr, "element %s must have a type\n", element->name);
      exit(1);
    }
  isBasic = (element->typPrefix &&
         (strcmp(element->typPrefix, XmlCppBase::wg3Prefix) == 0));
  if (element->needList)
    {  // zero or many elements are allowed
      if (isBasic)
    findCppTypeName(element->newTyp, typeName);
      else
    strncpy(typeName, element->newTyp, NAMESIZE);
      fprintf(ccFile, "  if (%s)\n", element->newName);
      fprintf(ccFile, "    {\n");
      fprintf(ccFile, "      std::list<%s *>::iterator iter;\n", typeName);
      fprintf(ccFile, "      for (iter = %s->begin();\n", element->newName);
      fprintf(ccFile, "           iter != %s->end(); iter++)\n",
          element->newName);
      fprintf(ccFile, "        delete *iter;\n");
      fprintf(ccFile, "      delete %s;\n", element->newName);
      fprintf(ccFile, "    }\n");
    }
  else
    fprintf(ccFile, "  delete %s;\n", element->newName);
}

#else

/* generator::printCppCodeDeleteElement

Returned Value: none

Called By: printCppCodeSequenceDeletes

This prints the code that deletes an element. If the element is a list, the
elements of the list are deleted, and then the list is deleted. Otherwise,
only the element is deleted. For example:

  {
    std::list<XmlString *>::iterator iter;
    for (iter = Nickname->begin(); iter != Nickname->end(); iter++)
      delete *iter;
  }
  delete Nickname;

*/

void generator::printCppCodeDeleteElement( /* ARGUMENTS          */
 XmlElementLocal * element)                /* element to delete  */
{
  static char typeName[NAMESIZE];
  bool isBasic;

  if (element->newTyp == 0)
    {
      fprintf(stderr, "element %s must have a type\n", element->name);
      exit(1);
    }
  isBasic = (element->typPrefix &&
	     (strcmp(element->typPrefix, XmlCppBase::wg3Prefix) == 0));
  if (element->needList)
    {  // zero or many elements are allowed
      if (isBasic)
	findCppTypeName(element->newTyp, typeName);
      else
	strncpy(typeName, element->newTyp, NAMESIZE);
      fprintf(ccFile, "  {\n");
      fprintf(ccFile, "    std::list<%s *>::iterator iter;\n", typeName);
      fprintf(ccFile, "    for (iter = %s->begin();\n", element->newName);
      fprintf(ccFile, "         iter != %s->end(); iter++)\n",
	      element->newName);
      fprintf(ccFile, "      delete *iter;\n");
      fprintf(ccFile, "  }\n");
    }
  fprintf(ccFile, "  delete %s;\n", element->newName);
}

#endif

/********************************************************************/

/* generator::printCppCodeEnd

Returned Value: none

Called By: generator::printCppCode

This prints the XmlHeaderForXXX class. It prints two constructors,
a destructor, and a printSelf function.

*/

void generator::printCppCodeEnd()
{
  // start printing constructor with no arguments
  fprintf(ccFile, "XmlHeaderFor%s::XmlHeaderFor%s()\n",
	  classBaseName, classBaseName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  location = 0;\n");
  fprintf(ccFile, "}\n");
  // end printing constructor with no arguments
  // start printing constructor with location argument
  fprintf(ccFile, "\n");
  fprintf(ccFile, "XmlHeaderFor%s::XmlHeaderFor%s(\n",
	classBaseName, classBaseName);
  fprintf(ccFile, "  SchemaLocation * locationIn)\n");
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  location = locationIn;\n");
  fprintf(ccFile, "}\n");
  // end printing constructor with location argument
  // start printing destructor
  fprintf(ccFile, "\n");
  fprintf(ccFile, "XmlHeaderFor%s::~XmlHeaderFor%s()\n",
	  classBaseName, classBaseName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  delete location;\n");
  fprintf(ccFile, "}\n");
  // end printing destructor
  // start printing printSelf
  fprintf(ccFile, "\n");
  fprintf(ccFile, "void XmlHeaderFor%s::PRINTSELFDECL\n", classBaseName);
  fprintf(ccFile, "{\n");
  if (target)
    {
      fprintf(ccFile, "  XFPRINTF \"  xmlns=\\\"%s\\\"\\n\");\n", target);
    }
  fprintf(ccFile, "  XFPRINTF\n");
  fprintf(ccFile, "          \"  xmlns:xsi=\\\"http://www.w3.org/");
  fprintf(ccFile, "2001/XMLSchema-instance\\\"\\n\");\n");
  fprintf(ccFile, "  location->PRINTSELF;\n");
  fprintf(ccFile, "}\n");
  // end printing printSelf
  printStarLine(ccFile, true, true);
}

/********************************************************************/

/* generator::printCppCodeOtherMinimal

Returned Value: none

Called By: generator::printCppCodeComplex

This is called when a complex type has no content except possibly
attributes. It prints one or two constructors. It always prints a
constructor that takes no arguments. If there are attributes, it also
prints a constructor taking attribute arguments. In addition, it
prints a destructor and a printSelf function.

*/

void generator::printCppCodeOtherMinimal(  /* ARGUMENTS                    */
 char * newName,                           /* newName of an XmlComplexType */
 std::list<XmlAttributeLoner *> * attribs) /* list of attributes to print  */
{
  int comma = 0;
  
  //start printing constructor with no arguments
  fprintf(ccFile, "%s::%s() {", newName, newName);
  if (attribs && attribs->size())
    {
      fprintf(ccFile, "\n");
      printCppCodeAttributeSettings0(attribs);
    }
  fprintf(ccFile, "}\n");
  //end printing constructor with no arguments
  if (attribs && attribs->size())
    {
      //start printing constructor with attribute arguments
      fprintf(ccFile, "\n");
      fprintf(ccFile, "%s::%s(", newName, newName);
      comma = 0;
      printCppHeaderAttributeArgs(attribs, &comma, "");
      fprintf(ccFile, ")\n");
      fprintf(ccFile, "{\n");
      printCppCodeAttributeSettings(attribs);
      fprintf(ccFile, "}\n");
      //end printing constructor with attribute arguments
    }
  //start printing destructor
  fprintf(ccFile, "\n");
  if (attribs && attribs->size())
    {
      fprintf(ccFile, "%s::~%s()\n", newName, newName);
      fprintf(ccFile, "{\n");
      printCppCodeAttributeDeletes(attribs);
      fprintf(ccFile, "}\n");
    }
  else
    fprintf(ccFile, "%s::~%s() {}\n", newName, newName);
  //end printing destructor
  //start printing printSelf
  fprintf(ccFile, "\n");
  fprintf(ccFile, "void %s::PRINTSELFDECL\n", newName);
  fprintf(ccFile, "{\n");
  printCppCodePrintAttribs(attribs);
  fprintf(ccFile, "  XFPRINTF \">\\n\");\n");
  fprintf(ccFile, "}\n");
  //end printing printSelf
}

/********************************************************************/

/* generator::printCppCodePrintAttribs

Returned Value: none

Called By:
  generator::printCppCodeChoice
  generator::printCppCodeComplexExtend
  generator::printCppCodePrintLines
  generator::printCppCodeSequence
  generator::printCppCodeSimpleExtend

This prints in the C++ code file the settings representing attributes
when the class represents an XML schema complexType containing an XML
schema sequence and the complexType has attributes.

The code this prints checks that required attributes are present and
exits after printing an error message if not.

*/

void generator::printCppCodePrintAttribs(  /* ARGUMENTS                   */
 std::list<XmlAttributeLoner *> * attribs) /* list of attributes to print */
{
  std::list<XmlAttributeLoner *>::iterator iter;
  XmlAttributeLoner * decl;

  if (!attribs) //may be null
    return;
  for (iter = attribs->begin(); iter != attribs->end(); iter++)
    { // OK if list empty
      decl = *iter;
      fprintf(ccFile, "  if (%s)\n", decl->newNameRef);
      fprintf(ccFile, "    {\n");
      fprintf(ccFile, "      XFPRINTF \" %s=\\\"\");\n",
	      decl->newNameRef);
      fprintf(ccFile, "      %s->PRINTSELF;\n", decl->newNameRef);
      fprintf(ccFile, "      XFPRINTF \"\\\"\");\n");
      fprintf(ccFile, "    }\n");
      if (decl->use == XmlCppBase::required)
	{
	  fprintf(ccFile, "  else\n");
	  fprintf(ccFile, "    {\n");
	  fprintf(ccFile, "      fprintf(stderr, \"required "
		  "attribute \\\"%s\\\" missing\\n\");\n", decl->newNameRef);
	  fprintf(ccFile, "      exit(1);\n");
	  fprintf(ccFile, "    }\n");
	}
    }
}

/********************************************************************/

/* generator::printCppCodePrintElement

Returned Value: none

Called By: generator::printCppCodePrintItems

This prints in the code file the part of a printSelf function
that prints an XML element.

For maxOccurs, a value of -1 means unbounded. A value less than -1 
means no maxOccurs was given.

If minOccurs is 0 and maxOccurs is 1 either by default or explicit
assignment, printing the value occurs only if the element occurs.
If minOccurs is 0 and maxOccurs allows for a oList, no special action
is taken since nothing will be printed if the oList is empty.

Note that nothing is printed if maxOccurs is zero. If an element has
attributes, they are printed by the printSelf function of the class for
the type of the element.

Example 1. For a non-optional simpleType, this function might print
the following segment of code. The segment of code prints one line.

  SPACESZERO;
  XFPRINTF "<TemperatureUnits>");
  TemperatureUnits->PRINTSELF;
  XFPRINTF "</TemperatureUnits>\n");

The segment of code above might print:
   <TemperatureUnits>FAHRENHEIT</TemperatureUnits>

Example 2. For an optional ComplexType element with ComplexContent,
this function might print the following segment of code.  The if{}
structure is used because the element is optional.  The
AngularUnits->printSelf function here finishes the line starting with
"<AngularUnits", then prints 0 to many lines (as determined by the
structure of AngularUnitsType) and starts a new line. The call to
doSpaces inserts space at the beginning of the line that ends with
"</AngularUnits>\n". When AngularUnits exists, this segment of code
prints at least three lines.


  if (AngularUnits)
    {
      SPACESZERO;
      XFPRINTF "<AngularUnits");
      AngularUnits->PRINTSELF;
      SPACESZERO;
      XFPRINTF "</AngularUnits>\n");
    }

The segment of code above might print:
    <AngularUnits>
      <unitName>radian</unitName>
      <factor>57.295780</factor>
    </AngularUnits>

Example 3. For an optional complexType element with simpleContent
(i.e.  a simpleType with attributes), this function might print the
following segment of code.  The segment of code prints one line. The
if{} structure is used because the element is optional.  The
LinearUnits->printSelf function here continues the line starting with
"<LinearUnits" by printing the attributes, a > and a value.  When
LinearUnits exists, this segment of code prints only one line. Note
that doSpaces is not called a second time.

  if (LinearUnits)
    {
      SPACESZERO;
      XFPRINTF "<LinearUnits");
      LinearUnits->PRINTSELF;
      XFPRINTF "</LinearUnits>\n");
    }

The segment of code above might print:
    <LinearUnits unitName="inch">25.400000</LinearUnits>

FIX - add code to handle mock for list.

*/

void generator::printCppCodePrintElement( /* ARGUMENTS        */
 XmlElementLocal * element)               /* element to print */
{
  static char typeName[NAMESIZE];
  XmlSimpleType * simple;
  XmlComplexType * complx;
  const char * space2 = "  ";
  const char * space6 = "      ";
  const char * space;
  bool isBasic;

  if (element->newTyp == 0)
    {
      fprintf(stderr, "element %s must have a type\n", element->name);
      exit(1);
    }
  simple = findSimpleClass(element->newTyp);
  isBasic = (element->typPrefix &&
	     (strcmp(element->typPrefix, XmlCppBase::wg3Prefix) == 0));
  if (element->needList)
    {  // zero or many elements are allowed
      fprintf(ccFile, "  {\n");
      if (simple || isBasic)
	{
	  if (isBasic)
	    findCppTypeName(element->newTyp, typeName);
	  else
	    strncpy(typeName, element->newTyp, NAMESIZE);
	  fprintf(ccFile, "    std::list<%s *>::iterator iter;\n", typeName);
	  fprintf(ccFile,
		  "    for (iter = %s->begin(); iter != %s->end(); iter++)\n",
		  element->newName, element->newName);
	  fprintf(ccFile, "      {\n");
	  fprintf(ccFile, "        SPACESZERO;\n");
	  fprintf(ccFile, "        XFPRINTF \"<%s>\");\n",
		  element->name);
	  fprintf(ccFile, "        (*iter)->PRINTSELF;\n");
          fprintf(ccFile, "        XFPRINTF \"</%s>\\n\");\n",
		  element->name);
	  fprintf(ccFile, "      }\n");
	}
      else
	{
	  fprintf(ccFile, "    std::list<%s *>::iterator iter;\n",
		  element->newTyp);
	  fprintf(ccFile,
		  "    for (iter = %s->begin(); iter != %s->end(); iter++)\n",
		  element->newName, element->newName);
	  fprintf(ccFile, "      {\n");
	  fprintf(ccFile, "        SPACESZERO;\n");
	  fprintf(ccFile, "        XFPRINTF \"<%s\");\n",
		  element->name);
	  fprintf(ccFile, "        (*iter)->PRINTSELF;\n");
	  fprintf(ccFile, "        SPACESZERO;\n");
	  fprintf(ccFile, "        XFPRINTF \"</%s>\\n\");\n",
		  element->name);
	  fprintf(ccFile, "      }\n");
	}
      fprintf(ccFile, "  }\n");
    }
  else  // only one element allowed
    {
      if (element->minOccurs == 0)
	{ // element is optional and may be 0
	  fprintf(ccFile, "  if (%s)\n", element->newName);
	  fprintf(ccFile, "    {\n");
	  space = space6;
	}
      else
	space = space2;
      if (element->mock)
	{
	  if (simple || isBasic)
	    {
	      fprintf(ccFile, "%s%s->PRINTSELF;\n",
		      space, element->newName);
	    }
	  else
	    {
	      complx = findComplexClass(element->newTyp);
	      if (!complx)
		{
		  fprintf(stderr,
			  "complex class %s not found in"
			  " printCppCodePrintElement\n", element->newTyp);
		  exit(1);
		}
	      fprintf(ccFile, "%s%s->PRINTSELF;\n",
		      space, element->newName);
	    }
	}
      else
	{
	  fprintf(ccFile, "%sSPACESZERO;\n", space);
	  if (simple || isBasic)
	    {
	      fprintf(ccFile, "%sXFPRINTF \"<%s>\");\n",
		      space, element->name);
	      fprintf(ccFile, "%s%s->PRINTSELF;\n",
		      space, element->newName);
	    }
	  else
	    {
	      complx = findComplexClass(element->newTyp);
	      if (!complx)
		{
		  fprintf(stderr,
			  "complex class %s not found in "
			  "printCppCodePrintElement\n", element->newTyp);
		  exit(1);
		}
	      fprintf(ccFile, "%sXFPRINTF \"<%s\");\n",
		      space, element->name);
	      fprintf(ccFile, "%s%s->PRINTSELF;\n",
		      space, element->newName);
	      if (!dynamic_cast<XmlSimpleContent *>(complx->item))
		fprintf(ccFile, "%sSPACESZERO;\n", space);
	    }
	  fprintf(ccFile, "%sXFPRINTF \"</%s>\\n\");\n",
		  space, element->name);
	}
      if (element->minOccurs == 0)
	fprintf(ccFile, "    }\n");
    }
}

/********************************************************************/

/* generator::printCppCodePrintItems

Returned Value: none

Called By:
  generator::printCppCodeComplexExtend
  generator::printCppCodePrintLines
  generator::printCppCodeSequence

This prints in the C++ code file the body of the printSelf function.

*/

void generator::printCppCodePrintItems( /* ARGUMENTS           */
 std::list<XmlChoSeqItem *> * items)    /* items to print from */
{
  std::list<XmlChoSeqItem *>::iterator iter;
  XmlElementLocal * element;
  
  for (iter = items->begin(); iter != items->end(); iter++)
    {
      if ((element = dynamic_cast<XmlElementLocal *>(*iter)))
	printCppCodePrintElement(element);
    }
}

/********************************************************************/

/* generator::printCppCodePrintLines

Returned Value: none

Called By:
  generator::printCppCodeComplexExtend
  generator::printCppCodePrintLines (recursively)

This prints the lines of the printSelf function for an XmlComplexExtension.

This calls itself recursively to handle extension depths greater than one.
The recursive calls print before the function prints since the XML elements
of a parent occur before those of the extension.

*/

void generator::printCppCodePrintLines( /* ARGUMENTS          */
 XmlComplexType * baseClass,            /* base class         */
 char * baseName)                       /* name of base class */
{
  XmlOtherContent * other;
  XmlComplexContent * comp;
  XmlComplexExtension * extend;
  XmlComplexType * parent;
  XmlSequence * sequence;

  if (baseClass == 0)
    {
      fprintf(stderr, "base class %s not found in printCppCodePrintLines\n",
	      baseName);
      exit(1);
    }
  if ((other = dynamic_cast<XmlOtherContent *>(baseClass->item)))
    {
      if ((sequence = dynamic_cast<XmlSequence *>(other->base)))
	{
	  printCppCodePrintItems(sequence->items);
	}
    }
  else if ((comp = dynamic_cast<XmlComplexContent *>(baseClass->item)))
    {
      if ((extend = dynamic_cast<XmlComplexExtension *>(comp->item)))
	{
	  parent = findComplexClass(extend->base);
	  printCppCodePrintLines(parent, extend->base);
	  if ((sequence = dynamic_cast<XmlSequence *>(extend->item)))
	    {
	      printCppCodePrintItems(sequence->items);
	    }
	}
      else if ((dynamic_cast<XmlComplexRestriction *>(comp->item)))
	{
	  fprintf(stderr, "cannot handle complex restriction\n");
	}
    }
}

/********************************************************************/

/* generator::printCppCodeRestrictEnum

Returned Value: none

Called By:  generator::printCppCodeRestrictString

This prints in the C++ code file the implementation of a class derived
from an XmlSimpleRestriction whose base in an XML string. The
restriction must all be XmlEnumerations.

The restrictions std::list should not be null, but it may be empty.

This writes:
 - a constructor taking no arguments
 - a constructor taking a valIn argument
 - a destructor
 - a printSelf function
 - an XXXIsBad function

The printSelf function calls the printSelf function of the parent so that
any restrictions imposed in the parent will be checked by the parent's
XXXIsBad function before any actual printing is done.

*/

void generator::printCppCodeRestrictEnum(        /*  ARGUMENTS               */
 char * newName,                                 /* name for class           */
 char * parentName,                              /* name of parent class     */
 char * rootCppTypeName,                         /* name of root C++ class   */
 std::list<XmlRestrictionType *> * restrictions) /* restrictions giving enum */
{
  XmlEnumeration * enumer;
  std::list<XmlRestrictionType *>::iterator iter;
  
  // start printing constructor with no arguments
  fprintf(ccFile, "%s::%s() :\n", newName, newName);
  fprintf(ccFile, "  %s() {}\n", parentName);
  // end printing constructor with no arguments
  // start printing constructor with valIn argument
  fprintf(ccFile, "\n");
  fprintf(ccFile, "%s::%s(\n", newName, newName);
  fprintf(ccFile, " const char * valIn) :\n");
  fprintf(ccFile, "  %s(valIn)\n", parentName);
  fprintf(ccFile, "{");
  if (restrictions->size())
    {
      fprintf(ccFile, "\n");
      fprintf(ccFile, "  if (!bad)\n");
      fprintf(ccFile, "    bad = (");
      for (iter = restrictions->begin(); iter != restrictions->end(); iter++)
	{
	  enumer = dynamic_cast<XmlEnumeration *>(*iter);
	  if (!enumer)
	    {
	      fprintf(stderr, "all restrictions must be enumerations\n");
	      exit(1);
	    }
	  fprintf(ccFile, "strcmp(val.c_str(), \"%s\"%s", enumer->value,
		  ((*iter == restrictions->back()) ? "));\n" :
		   ") &&\n           "));
	}
    }
  fprintf(ccFile, "}\n");
  // end printing constructor with valIn argument
  // start printing destructor
  fprintf(ccFile, "\n");
  fprintf(ccFile, "%s::~%s() {}\n", newName, newName);
  // end printing destructor
  // start printing XXXIsBad
  fprintf(ccFile, "\n");
  fprintf(ccFile, "bool %s::%sIsBad()\n", newName, newName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  return (");
  if (restrictions->size() == 0)
    {
      fprintf(ccFile, "false);\n");
    }
  else
    {
      for (iter = restrictions->begin(); iter != restrictions->end(); iter++)
	{
	  enumer = dynamic_cast<XmlEnumeration *>(*iter);
	  if (!enumer)
	    {
	      fprintf(stderr, "all restrictions must be enumerations\n");
	      exit(1);
	    }
	  fprintf(ccFile, "strcmp(val.c_str(), \"%s\"%s", enumer->value,
		  ((*iter == restrictions->back()) ? "));\n" :
		   ") &&\n          "));
	}
    }
  fprintf(ccFile, "}\n");
  // end printing XXXIsBad
  // start printing printSelf
  fprintf(ccFile, "\n");
  fprintf(ccFile, "void %s::PRINTSELFDECL\n", newName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  if (%sIsBad())\n", newName);
  fprintf(ccFile, "    {\n");
  fprintf(ccFile, "      fprintf(stderr, \"bad %s value, \");\n", newName);
  fprintf(ccFile, "      %s::printBad(stderr);\n", rootCppTypeName);
  fprintf(ccFile, "      fprintf(stderr, \" exiting\\n\");\n");
  fprintf(ccFile, "      exit(1);\n");
  fprintf(ccFile, "    }\n");
  fprintf(ccFile, "  %s::PRINTSELF;\n", rootCppTypeName);
  fprintf(ccFile, "}\n");
  // end printing printSelf
}

/********************************************************************/

/* generator::printCppCodeRestrictNumber

Returned Value: none

Called By: generator::printCppCodeSimple

This prints in the C++ code file the implementation of a class derived from
an XmlSimpleRestriction whose base in a number, specifically:
decimal
double
float
int
integer
nonNegativeInteger
positiveInteger
long
short
unsignedInt
unsignedLong
unsignedShort

The restrictions std::list should not be null, but it may be empty.

This writes:
 - a constructor taking no arguments
 - a constructor taking a valIn argument
 - a destructor
 - a printSelf function
 - an XXXIsBad function

This implements checks for maxExclusive, minExclusive, maxInclusive,
and minInclusive. It also allows but does not print useful code for
pattern and enumeration (it just prints bad = (false); for those).

If any other type of restriction occurs, this prints an error message
and exits.

The printSelf function calls the printSelf function of the parent so that
any restrictions imposed in the parent will be checked by the parent's
XXXIsBad function before any actual printing is done.

*/

void generator::printCppCodeRestrictNumber(      /* ARGUMENTS                */
 char * newName,                                 /* class name               */
 char * parentName,                    /* name of C++ class being restricted */
 char * rootCppTypeName,                         /* name of root C++ class   */
 std::list<XmlRestrictionType *> * restrictions) /* restrictions             */
{
  XmlMaxExclusive * maxEx;
  XmlMaxInclusive * maxIn;
  XmlMinExclusive * minEx;
  XmlMinInclusive * minIn;
  std::list<XmlRestrictionType *>::iterator iter;
  
  // start printing constructor with no arguments
  fprintf(ccFile, "%s::%s() :\n", newName, newName);
  fprintf(ccFile, "  %s() {}\n", parentName);
  // end printing constructor with no arguments
  // start printing constructor with valIn argument
  fprintf(ccFile, "\n");
  fprintf(ccFile, "%s::%s(\n", newName, newName);
  fprintf(ccFile, " const char * valIn) : %s(valIn)\n", parentName);
  fprintf(ccFile, "{");
  if (restrictions->size())
    {
      fprintf(ccFile, "\n");
      fprintf(ccFile, "  if (!bad)\n");
      fprintf(ccFile, "    bad = (");
      for (iter = restrictions->begin(); iter != restrictions->end(); iter++)
	{
	  if ((maxEx = dynamic_cast<XmlMaxExclusive *>(*iter)))
	    {
	      fprintf(ccFile, "(val >= %s)", maxEx->value);
	    }
	  else if ((maxIn = dynamic_cast<XmlMaxInclusive *>(*iter)))
	    {
	      fprintf(ccFile, "(val > %s)", maxIn->value);
	    }
	  else if ((minEx = dynamic_cast<XmlMinExclusive *>(*iter)))
	    {
	      fprintf(ccFile, "(val <= %s)", minEx->value);
	    }
	  else if ((minIn = dynamic_cast<XmlMinInclusive *>(*iter)))
	    {
	      fprintf(ccFile, "(val < %s)", minIn->value);
	    }
	  else if (dynamic_cast<XmlPattern *>(*iter))
	    {
	      fprintf(ccFile, "false");
	    }
	  else if (dynamic_cast<XmlEnumeration *>(*iter))
	    {
	      fprintf(ccFile, "false);\n");
	      break;
	    }
	  else
	    {
	      fprintf(stderr, "bad number restriction type\n");
	      exit(1);
	    }
	  fprintf(ccFile, "%s", ((*iter == restrictions->back()) ? ");\n" :
				 " ||\n          "));
	}
    }
  fprintf(ccFile, "}\n");
  // end printing constructor with valIn argument
  // start printing destructor
  fprintf(ccFile, "\n");
  fprintf(ccFile, "%s::~%s() {}\n", newName, newName);
  // end printing destructor
  // start printing XXXIsBad
  fprintf(ccFile, "\n");
  fprintf(ccFile, "bool %s::%sIsBad()\n", newName, newName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  if (%sIsBad())\n", parentName);
  fprintf(ccFile, "    return true;\n");
  if (restrictions->size() == 0)
    fprintf(ccFile, "  return false;\n");
  else
    {
      fprintf(ccFile, "  return (");
      for (iter = restrictions->begin(); iter != restrictions->end(); iter++)
	{
	  if ((maxEx = dynamic_cast<XmlMaxExclusive *>(*iter)))
	    {
	      fprintf(ccFile, "(val >= %s)", maxEx->value);
	    }
	  else if ((maxIn = dynamic_cast<XmlMaxInclusive *>(*iter)))
	    {
	      fprintf(ccFile, "(val > %s)", maxIn->value);
	    }
	  else if ((minEx = dynamic_cast<XmlMinExclusive *>(*iter)))
	    {
	      fprintf(ccFile, "(val <= %s)", minEx->value);
	    }
	  else if ((minIn = dynamic_cast<XmlMinInclusive *>(*iter)))
	    {
	      fprintf(ccFile, "(val < %s)", minIn->value);
	    }
	  else if (dynamic_cast<XmlPattern *>(*iter))
	    {
	      fprintf(ccFile, "false");
	    }
	  else if (dynamic_cast<XmlEnumeration *>(*iter))
	    {
	      fprintf(ccFile, "false);\n");
	      break;
	    }
	  else
	    {
	      fprintf(stderr, "bad number restriction type\n");
	      exit(1);
	    }
	  fprintf(ccFile, "%s",
	      ((*iter == restrictions->back()) ? ");\n" : " ||\n          "));
	}
    }
  fprintf(ccFile, "}\n");
  // end printing XXXIsBad
  // start printing printSelf
  fprintf(ccFile, "\n");
  fprintf(ccFile, "void %s::PRINTSELFDECL\n", newName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  if (%sIsBad())\n", newName);
  fprintf(ccFile, "    {\n");
  fprintf(ccFile, "      fprintf(stderr, \"bad %s value, \");\n", newName);
  fprintf(ccFile, "      %s::printBad(stderr);\n", rootCppTypeName);
  fprintf(ccFile, "      fprintf(stderr, \" exiting\\n\");\n");
  fprintf(ccFile, "      exit(1);\n");
  fprintf(ccFile, "    }\n");
  fprintf(ccFile, "  %s::PRINTSELF;\n", rootCppTypeName);
  fprintf(ccFile, "}\n");
  // end printing printSelf
}

/********************************************************************/

/* generator::printCppCodeRestrictPattern

Returned Value: none

Called By:  generator::printCppCodeRestrictString

This prints in the C++ code file the implementation of a class derived
from an XmlSimpleRestriction that (1) has a rootType that is one of
the XML built in stringy types (string, token, NMTOKEN, ID, or IDREF) and
(2) has a single restriction that is a pattern. The XML schema pattern is
put into a C++ string representing a pattern that the boost regular
expression routines can handle. Boost uses Perl regular expressions,
and XML patterns are almost identical.

Since the pattern from the XML schema will be put into a C++ string,
and the pattern may contain backslashes (which either are ignored or
create escape sequences inside C++ strings), it is necessary to check
each character of the pattern and print two backslashes whenever one
is found in the XML pattern (two backslashes is the C++ escape
character for one backslash). In addition, a ^ (to represent the start
of a line) is put at the beginning of the pattern and a $ (to
represent the end of the line) is put at the end. The boost regular
expression parser needs those to be added.

This writes:
 - a constructor taking no arguments
 - a constructor taking a valIn argument
 - a destructor
 - a printSelf function
 - an XXXIsBad function

*/

void generator::printCppCodeRestrictPattern( /* ARGUMENTS              */
 char * newName,                             /* name for class         */
 char * parentName,                          /* name of parent class   */
 char * rootCppTypeName,                     /* name of root C++ class */
 XmlPattern * pattern)                       /* a pattern restriction  */
{
  char c;
  int n;

  // start printing constructor with no arguments
  fprintf(ccFile, "%s::%s() :\n", newName, newName);
  fprintf(ccFile, "  %s() {}\n", parentName);
  // end printing constructor with no arguments
  // start printing constructor with valIn argument
  fprintf(ccFile, "\n");
  fprintf(ccFile, "%s::%s(\n", newName, newName);
  fprintf(ccFile, " const char * valIn) :\n");
  fprintf(ccFile, "  %s(valIn)\n", parentName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  if (!bad)\n");
  fprintf(ccFile, "    {\n");
  fprintf(ccFile, "      boost::regex pattern;\n");
  fprintf(ccFile, "      const char * regexp = \"^");
  for (n = 0;  (c = pattern->value[n]); n++)
    { // print the 
      if (c == '\\') // if c is one backslash
	fprintf(ccFile, "\\\\"); // print two backslashes
      else
	fputc(c, ccFile);
    }
  fprintf(ccFile, "$\";\n");
  fprintf(ccFile,
"      pattern = boost::regex(regexp,\n"
"			 boost::regex::extended|boost::regex::no_except);\n"
"      if (pattern.empty())\n"
"        fprintf(stderr,\n"
"                \"cannot handle \\\"%%s\\\", so not checking %%s\\n\",\n"
"                regexp, val.c_str());\n"
"      else\n"
"        bad = !boost::regex_search(val, pattern);\n"
"    }\n"
"}\n");
  // end printing constructor with valIn argument
  // start printing destructor
  fprintf(ccFile, "\n");
  fprintf(ccFile, "%s::~%s() {}\n", newName, newName);
  // end printing destructor
  // start printing XXXIsBad
  fprintf(ccFile, "\n");
  fprintf(ccFile, "bool %s::%sIsBad()\n", newName, newName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  boost::regex pattern;\n");
  fprintf(ccFile, "  const char * regexp = \"^");
  for (n = 0;  (c = pattern->value[n]); n++)
    {
      if (c == '\\') // if c is one backslash
	fprintf(ccFile, "\\\\"); // print two backslashes
      else
	fputc(c, ccFile);
    }
  fprintf(ccFile, "$\";\n");
  fprintf(ccFile, "\n");
  fprintf(ccFile, "  if (%sIsBad() == true)\n", parentName);
  fprintf(ccFile,
"    return true;\n"
"  pattern = boost::regex(regexp,\n"
"			 boost::regex::extended|boost::regex::no_except);\n"
"  if (pattern.empty())\n"
"    {\n"
"      fprintf(stderr, \"cannot handle \\\"%%s\\\", so not checking %%s\\n\",\n"
"	      regexp, val.c_str());\n"
"      return false;\n"
"    }\n"
"  return !boost::regex_search(val, pattern);\n"
"}\n");
  // end printing XXXIsBad
  // start printing printSelf
  fprintf(ccFile, "\n");
  fprintf(ccFile, "void %s::PRINTSELFDECL\n", newName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  if (%sIsBad())\n", newName);
  fprintf(ccFile, "    {\n");
  fprintf(ccFile, "      fprintf(stderr, \"bad %s value, \");\n", newName);
  fprintf(ccFile, "      %s::printBad(stderr);\n", rootCppTypeName);
  fprintf(ccFile, "      fprintf(stderr, \" exiting\\n\");\n");
  fprintf(ccFile, "      exit(1);\n");
  fprintf(ccFile, "    }\n");
  fprintf(ccFile, "  %s::PRINTSELF;\n", rootCppTypeName);
  fprintf(ccFile, "}\n");
  // end printing printSelf
}

/********************************************************************/

/* generator::printCppCodeRestrictSList

Returned Value: none

Called By: generator::printCppCodeSimple

This writes:
 - a constructor that takes no arguments
 - a constructor that takes a list argument
 - a destructor
 - a printSelf function
 - a Check function that implements the restrictions on the list

The restrictions on the sList are checked by the Check function in the
constructor and in printSelf when the sList is printed. The way the
YACC works, the constructor that takes a list argument gets a
completed std::list that has been made as if it were unrestricted.

The printSelf function calls the printSelf function of the parent so that
any restrictions imposed in the parent will be checked by the parent's
Check function before any actual printing is done.

The destructor does not need to do anything because it will automatically
call the destructor for the parent type and so on through other
restrictions until the destructor for the unrestricted list will run
and get rid of the list and its elements.

*/

void generator::printCppCodeRestrictSList(       /* ARGUMENTS                */
 char * newName,                                 /* class name               */
 char * parentName,                              /* parent class name        */
 std::list<XmlRestrictionType *> * restrictions, /* restrictions             */
 char * typePrefix,                              /* typePrefix of item       */
 char * newItemType,                             /* XML name of item type    */
 int level)                                      /* 1 means list restriction */
{
  XmlLength * length;
  XmlMaxLength * maxLength;
  XmlMinLength * minLength;
  std::list<XmlRestrictionType *>::iterator iter;
  static char baseCppTypeName[NAMESIZE];

  if (typePrefix && (strcmp(typePrefix, XmlCppBase::wg3Prefix) == 0))
    findCppTypeName(newItemType, baseCppTypeName);
  else
    strncpy(baseCppTypeName, newItemType, NAMESIZE);
  // start printing constructor that takes no arguments
  fprintf(ccFile, "%s::%s() :\n", newName, newName);
  fprintf(ccFile, "  %s() {}\n", parentName);
  // end printing constructor that takes no arguments
  // start printing constructor that takes list argument
  fprintf(ccFile, "\n");
  fprintf(ccFile, "%s::%s(\n", newName, newName);
  fprintf(ccFile, "  std::list<%s *> * theListIn) :\n", baseCppTypeName);
  fprintf(ccFile, "  %s(theListIn)\n", parentName);
  fprintf(ccFile, "{\n");
  if (level == 1)
    {
      fprintf(ccFile, "  bad = %sCheck();\n", newName);
      fprintf(ccFile, "  if (bad)\n");
      fprintf(ccFile, "    fprintf(stderr, \"%sCheck failed\\n\");\n", newName);
    }
  else
    {
      fprintf(ccFile, "  if (!bad)\n");
      fprintf(ccFile, "    {\n");
      fprintf(ccFile, "      bad = %sCheck();\n", newName);
      fprintf(ccFile, "      if (bad)\n");
      fprintf(ccFile, "        fprintf(stderr, \"%sCheck failed\\n\");\n",
	      newName);
      fprintf(ccFile, "    }\n"); 
    }
  fprintf(ccFile, "}\n");
  // end printing constructor that takes list argument
  // start printing destructor
  fprintf(ccFile, "\n");
  fprintf(ccFile, "%s::~%s() {}\n", newName, newName);
  // end printing destructor
  // start printing printSelf
  fprintf(ccFile, "\n");
  fprintf(ccFile, "void %s::PRINTSELFDECL\n", newName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  if (%sCheck())\n", newName);
  fprintf(ccFile, "    {\n");
  fprintf(ccFile, "      fprintf(stderr, \"%sCheck failed\\n\");\n", newName);
  fprintf(ccFile, "      exit(1);\n");
  fprintf(ccFile, "    }\n");
  fprintf(ccFile, "  %s::PRINTSELF;\n", parentName);
  fprintf(ccFile, "}\n");
  // end printing printSelf
  // start printing Check
  fprintf(ccFile, "\n");
  fprintf(ccFile, "bool %s::%sCheck()\n", newName, newName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  return (");
  if (restrictions->size() == 0)
    {
      fprintf(ccFile, "false);\n");
      fprintf(ccFile, "}\n");
      return;
    }
  for (iter = restrictions->begin(); iter != restrictions->end(); iter++)
    {
      if ((length = dynamic_cast<XmlLength *>(*iter)))
	{
	  fprintf(ccFile, "(theList->size() != %s)", length->value);
	}
      else if ((maxLength = dynamic_cast<XmlMaxLength *>(*iter)))
	{
	  fprintf(ccFile, "(theList->size() > %s)", maxLength->value);
	}
      else if ((minLength = dynamic_cast<XmlMinLength *>(*iter)))
	{
	  fprintf(ccFile, "(theList->size() < %s)", minLength->value);
	}
      else
	{
	  fprintf(stderr, "bad list restriction type\n");
	  exit(1);
	}
      fprintf(ccFile, "%s",
	      ((*iter == restrictions->back()) ? ");\n" : " ||\n          "));
    }
   fprintf(ccFile, "}\n");
  // end printing Check
}

/********************************************************************/

/* generator::printCppCodeRestrictString

Returned Value: none

Called By: generator::printCppCodeSimple

The restrictions std::list should not be null, but it may be empty.

This is called if the rootType of the newName class is a string
type (xs:ID, xs:IDREF, xs:string, xs:NMTOKEN, xs:token).

Currently, the only restrictions handled are either (1) enumerations
or (2) single pattern restrictions.

*/

void generator::printCppCodeRestrictString(      /*  ARGUMENTS              */
 char * newName,                                 /* name for class          */
 char * newBase,                                 /* name for parent         */
 char * rootCppTypeName,                         /* name of root basic type */
 std::list<XmlRestrictionType *> * restrictions) /* restrictions            */
{
  XmlPattern * pattern;

  if ((restrictions->size() == 0) ||
      (dynamic_cast<XmlEnumeration *>(restrictions->front())))
    printCppCodeRestrictEnum(newName,  newBase, rootCppTypeName, restrictions);
  else if ((restrictions->size() == 1) &&
	   (pattern = dynamic_cast<XmlPattern *>(restrictions->front())))
    printCppCodeRestrictPattern(newName, newBase, rootCppTypeName, pattern);
  else
    {
      fprintf(stderr, "cannot handle string restrictions for %s\n", newName);
      exit(1);
    }
}

/********************************************************************/

/* generator::printCppCodeSchemaClasses

Returned Value: none

Called By:  generator::printCppCode

This prints in the C++ code file the implementations of all the classes
that have not yet had their implementations printed.

*/

void generator::printCppCodeSchemaClasses() /* NO ARGUMENTS */
{
  XmlComplexType * complx;
  XmlSimpleType * simple;
  std::list<XmlType *>::iterator iter;
  
  for (iter = classes->begin(); iter != classes->end(); iter++)
    {
      if ((complx = dynamic_cast<XmlComplexType *>(*iter)))
	{
	  if (complx->ccPrinted)
	    continue;
	  printCppCodeComplex(complx);
	  printStarLine(ccFile, true, true);
	  complx->ccPrinted = true;
	}
      else if ((simple = dynamic_cast<XmlSimpleType *>(*iter)))
	{
	  if (simple->ccPrinted)
	    continue;
	  printCppCodeSimple(simple);
	  printStarLine(ccFile, true, true);
	  simple->ccPrinted = true;
	}
      else
	{
	  fprintf(stderr, "bad class type in printCppCodeSchemaClasses\n");
	  exit(1);
	}
    }
}

/********************************************************************/

/* generator::printCppCodeSequence

Returned Value: none

Called By:  generator::printCppCodeComplex

This prints in the C++ code file the implementation of a class representing
an XmlComplexType containing a sequence. Such a class is not derived, so
there is no parent constructor to call.

*/

void generator::printCppCodeSequence(     /* ARGUMENTS                       */
 XmlSequence * sequence,                  /* XmlSequence to print class for  */
 char * newName,                          /* newName of complex type         */
 std::list<XmlAttributeLoner *> * attribs)/* flattened attributes of complex */
{
  int comma = 0;
  std::list<XmlChoSeqItem *> * items;
  
  items = sequence->items;
  // start printing constructor with no arguments
  if ((attribs && attribs->size()) || (items && items->size()))
    {
      fprintf(ccFile, "%s::%s()\n", newName, newName);
      fprintf(ccFile, "{\n");
      if (attribs && attribs->size())
	{
	  printCppCodeAttributeSettings0(attribs);
	}
      if (items && items->size())
	{
	  printCppCodeSequenceSettings0(items);
	}
      fprintf(ccFile, "}\n");
    }
  else
    fprintf(ccFile, "%s::%s() {}\n", newName, newName);
  // end printing constructor with no arguments
  if (items && items->size())
    {
      // start printing constructor with sequence arguments
      fprintf(ccFile, "\n");
      fprintf(ccFile, "%s::%s(", newName, newName);
      printCppHeaderSequenceArgs(items, &comma, "");
      fprintf(ccFile, ")\n");
      fprintf(ccFile, "{\n");
      if (attribs && attribs->size())
	printCppCodeAttributeSettings0(attribs);
      printCppCodeSequenceSettings(items);
      fprintf(ccFile, "}\n");
      // end printing constructor with sequence arguments
    }
  if (attribs && attribs->size())
    {
      // start printing constructor with attribute and sequence arguments
      comma = 0;
      fprintf(ccFile, "\n");
      fprintf(ccFile, "%s::%s(", newName, newName);
      printCppHeaderAttributeArgs(attribs, &comma, "");
      if (items && items->size())
	printCppHeaderSequenceArgs(items, &comma, "");
      fprintf(ccFile, ")\n");
      fprintf(ccFile, "{\n");
      printCppCodeAttributeSettings(attribs);
      if (items && items->size())
	printCppCodeSequenceSettings(items);
      fprintf(ccFile, "}\n");
      // end printing constructor with attribute and sequence arguments
    }
  // start printing destructor
  fprintf(ccFile, "\n");
  if ((attribs && attribs->size()) || (items && items->size()))
    {
      fprintf(ccFile, "%s::~%s()\n", newName, newName);
      fprintf(ccFile, "{\n");
      if (attribs && attribs->size())
	printCppCodeAttributeDeletes(attribs);
      if (items && items->size())
	printCppCodeSequenceDeletes(items);
      fprintf(ccFile, "}\n");
    }
  else
    fprintf(ccFile, "%s::~%s() {}\n", newName, newName);
  // end printing destructor
  // start printing printSelf
  fprintf(ccFile, "\n");
  fprintf(ccFile, "void %s::PRINTSELFDECL\n", newName);
  fprintf(ccFile, "{\n");
  printCppCodePrintAttribs(attribs);
  fprintf(ccFile, "  XFPRINTF \">\\n\");\n");
  fprintf(ccFile, "  SPACESPLUS;\n");
  printCppCodePrintItems(items);
  fprintf(ccFile, "  SPACESMINUS;\n");
  fprintf(ccFile, "}\n");
  // end printing printSelf
}

/********************************************************************/

/* generator::printCppCodeSequenceArgs

Returned Value: none

Called By:  generator::printCppCodeBaseArgs

This prints in the C++ code some arguments of the base type
constructor called in a child type constructor. The child is an
extension of the base type. The arguments are those corresponding to
elements in a sequence in the base type. This prints only the argument
names, not the types.  Each argument is on a separate line.

The value of comma will be 0 if no other argument has been printed
yet, and in this case a newline and the argument are printed.  The
value of comma will be 1 if an argument has been printed previously,
and in this case a comma, a newline, and the argument are printed. If
this prints one or more arguments, the value of comma is set to 1.

If any of the sequence items is not an element, this prints an error
message and exits. However, at the time this is called, all sequence
items that are choice or sequence will have been replace by mock
elements, so the error should never occur.

*/

void generator::printCppCodeSequenceArgs( /* ARGUMENTS                     */
 std::list<XmlChoSeqItem *> * items,      /* list of items to print        */
 int * comma)                             /* non-zero = start with a comma */
{
  std::list<XmlChoSeqItem *>::iterator iter;
  XmlElementLocal * element;

  for (iter = items->begin(); iter != items->end(); iter++)
    {
      if ((element = dynamic_cast<XmlElementLocal *>(*iter)))
	{
	  fprintf(ccFile, "%s\n", (*comma ? "," : ""));
	  fprintf(ccFile, "    %sIn", element->newName);
	  *comma = 1;
	}
      else
	{
	  fprintf(stderr,
		  "printCppCodeSequenceArgs cannot handle non-element\n");
	}
    }
}

/********************************************************************/

/* generator::printCppCodeSequenceDeletes

Returned Value: none

Called By:
?  generator::printCppCodeComplexExtend
  generator::printCppCodeSequence

This prints in the C++ code file the statements of the body of the
destructor that delete elements.
*/

void generator::printCppCodeSequenceDeletes( /* ARGUMENTS               */
 std::list<XmlChoSeqItem *> * items)         /* list of items to delete */
{
  std::list<XmlChoSeqItem *>::iterator iter;
  XmlElementLocal * element;
  
  for (iter = items->begin(); iter != items->end(); iter++)
    {
      if ((element = dynamic_cast<XmlElementLocal *>(*iter)))
	printCppCodeDeleteElement(element);
    }
}

/********************************************************************/

/* generator::printCppCodeSequenceSettings

Returned Value: none

Called By:
  generator::printCppCodeComplexExtend
  generator::printCppCodeSequence

This prints in the C++ code file statements of the body of the
constructor taking arguments. The statements are those derived
from the items argument. All of the statements are of the form:
 foo = fooIn;

*/

void generator::printCppCodeSequenceSettings( /* ARGUMENTS              */
 std::list<XmlChoSeqItem *> * items)          /* list of items to print */
{
  std::list<XmlChoSeqItem *>::iterator iter;
  XmlElementLocal * element;
  
  for (iter = items->begin(); iter != items->end(); iter++)
    {
      if ((element = dynamic_cast<XmlElementLocal *>(*iter)))
	fprintf(ccFile, "  %s = %sIn;\n", element->newName, element->newName);
    }
}

/********************************************************************/

/* generator::printCppCodeSequenceSettings0

Returned Value: none

Called By:
  generator::
  generator::

This prints in the C++ code file statements of the body of the
constructor taking no arguments. The statements are those derived
from the items argument. All of the statements are of the form:
 foo = 0;

*/

void generator::printCppCodeSequenceSettings0( /* ARGUMENTS              */
 std::list<XmlChoSeqItem *> * items)           /* list of items to print */
{
  std::list<XmlChoSeqItem *>::iterator iter;
  XmlElementLocal * element;
  
  for (iter = items->begin(); iter != items->end(); iter++)
    {
      if ((element = dynamic_cast<XmlElementLocal *>(*iter)))
	fprintf(ccFile, "  %s = 0;\n", element->newName);
    }
}

/********************************************************************/

/* generator::printCppCodeSimple

Returned Value: none

Called By: generator::printCppCodeSchemaClasses

This prints in the C++ code file the implementation of a C++ class
equivalent to an XML type derived by restriction or listing from an
XmlSimpleType or an XML built-in type.


*/

void generator::printCppCodeSimple( /* ARGUMENTS                     */
 XmlSimpleType * simple)            /* simple type to print class for */
{
  XmlSimpleRestriction * restrict;
  XmlSimpleList * sList;
  std::list<XmlRestrictionType *> * rests;
  static char baseCppTypeName[NAMESIZE];
  static char rootCppTypeName[NAMESIZE];
  static char rootXmlTypeName[NAMESIZE];
  char * base;
  int level;

  fprintf(ccFile, "/* class %s\n", simple->newName);
  fprintf(ccFile, "\n");
  fprintf(ccFile, "*/\n");
  fprintf(ccFile, "\n");
  if ((restrict = dynamic_cast<XmlSimpleRestriction *>(simple->item)))
    {
      rests = restrict->restrictions;
      level = 0;
      if (isRestrictedSList(simple, baseCppTypeName, rootCppTypeName,
			    rootXmlTypeName, &level))
	{ // restriction of an sList, buffer names irrelevant
	  printCppCodeRestrictSList(simple->newName, baseCppTypeName, rests,
				    rootCppTypeName, rootXmlTypeName, level);
	}
      else
	{ // restriction of a string or number
	  findRootXmlType(simple, rootXmlTypeName);
	  findCppTypeName(rootXmlTypeName, rootCppTypeName);
	  base = restrict->base;
	  if (restrict->basePrefix &&
	      (strcmp(restrict->basePrefix, XmlCppBase::wg3Prefix) == 0))
	    findCppTypeName(restrict->newBase, baseCppTypeName);
	  else
	    strncpy(baseCppTypeName, restrict->newBase, NAMESIZE);
	  if (isStringy(rootCppTypeName))
	    printCppCodeRestrictString(simple->newName, baseCppTypeName,
				       rootCppTypeName, rests);
	  else if (isNumber(rootCppTypeName))
	    printCppCodeRestrictNumber(simple->newName, baseCppTypeName,
				       rootCppTypeName, rests);
	  else
	    {
	      fprintf(stderr, "cannot handle %s, which restricts %s\n",
		      simple->name, base);
	      exit(1);
	    }
	}
    }
  else if ((sList = dynamic_cast<XmlSimpleList *>(simple->item)))
    {
      printCppCodeSimpleList(simple->newName,
			     sList->typePrefix, sList->newItemType);
    }
  else
    {
      fprintf(stderr, "cannot handle simple type %s\n", simple->name);
      exit(1);
    }
}

/********************************************************************/

/* generator::printCppCodeSimpleExtend

Returned Value: none

Called By: generator::printCppCodeComplex

This prints in the C++ code file the C++ implementation of a complex
class whose item is an XmlSimpleContentExtension. That type of
extension adds or restricts XSDL attributes. Every
XmlSimpleContentExtension that does not extend a list has a value that
is a basic type and is put into a C++ field called val. An
XmlSimpleContentExtension and its ancestors cannot have any elements.

It prints the definitions of 2 or 3 constructors.
1. A constructor taking no arguments that sets val to 0 and all other
   C++ fields (if any) to 0.
2. A constructor taking a val argument that sets val to the incoming
   value and sets all other C++ fields (if any) to 0.
3. If there are any XSDL attributes (from the extension or its base),
   a constructor taking a valIn argument and arguments corresponding
   to the XSDL attributes and sets val and all C++ fields to their
   incoming values.

The constructors are not calling the parent type constructor. Instead,
they are setting all values directly. It might be useful to change to
calling the parent type constructor and set only values added by the
extension, but there is no obvious benefit to doing that.

The arguments to the constructor taking all C++ fields must include
the names of all the C++ fields of the base type from which the
extension is derived. This is accomplished by calling printCppHeaderBaseArgs.

There is some trickiness in having the printSelf function this prints
determine whether or not to print a declaration of the type. A type
declaration is needed in the XML instance file if the type of the element
being printed was declared as a parent type of the extension, but no
type declaration is needed in the XML instance file if the type of the
element being printed is the extension type. To deal with this, the
C++ class for every extension type has a boolean printTypp
field (that class is printed by printCppHeaderComplexExtend). A type
declaration is printed by the printSelf function this writes if
printTypp is true. PrintTypp It is set to false in the constructor
with arguments, but printTypp is set to true by the action for the
YACC rule that recognizes an extension type being used for a parent
type. That action is written by buildYaccExtensionRule2.

An XmlSimpleContentExtension can extend a basic type, an XmlSimpleType,
or an XmlComplexType with an XmlSimpleContent. This is explicit in
"The Definitive XML Schema", sections 14.3.1 and 14.4.1; see Example
14-1. In section 14.5.1, restrictions of XmlComplexTypes with
XmlSimpleContents are also allowed; see Example 14-10.
XMLSpy allows all of those types of extension and restriction.

A complex content complex type may not extend a simple content complex
type in any way.

For an extension of an XML basic type, findCppTypeName is called to
find the C++ type of the value.

For an extension of a simple type, findRootXmlType is called to find
the XML type of the value, and then findCppTypeName is called to find
the C++ type of the value.

For an extension of a complex type, findRootXmlTypeComplex is called
to find the XML type of the value, and then findCppTypeName is called
to find the C++ type of the value.

FIX - If the base type is a simple restriction, this is not writing code
to check the restrictions.

FIX - This is not handling restrictions of XSDL attributes.

FIX - This is not handling an extension of a simple list.

*/

void generator::printCppCodeSimpleExtend( /* ARGUMENTS                        */
 XmlSimpleContentExtension * extend,  /* extension from which to print class  */
 char * newName,                      /* newName of type containing extension */
 char * name,                         /* name of type containing extension    */
 std::list<XmlAttributeLoner *> * allAttributes) /* all attributes            */
{
  XmlComplexType * complx;
  XmlSimpleType * simple;
  int comma = 0;
  bool isBasic;
  static char rootXmlTypeName[TEXTSIZE];
  static char cppTypeName[TEXTSIZE];

  isBasic = (extend->basePrefix &&
	     (strcmp(extend->basePrefix, XmlCppBase::wg3Prefix) == 0));
  if (isBasic)
    findCppTypeName(extend->base, cppTypeName);
  else if ((simple = findSimpleClass(extend->base)))
    {
      if (dynamic_cast<XmlSimpleList *>(simple->item))
	{
	  fprintf(stderr, "cannot handle extension of simple list\n");
	  exit(1);
	}
      findRootXmlType(simple, rootXmlTypeName);
      findCppTypeName(rootXmlTypeName, cppTypeName);
    }
  else if ((complx = findComplexClass(extend->base)))
    {
      findRootXmlTypeComplex(complx, rootXmlTypeName);
      findCppTypeName(rootXmlTypeName, cppTypeName);
    }
  else
    {
      fprintf(stderr, "base type %s not found in printCppCodeSimpleExtend\n",
	      extend->base);
      exit(1);
    }
  // start printing constructor with no arguments
  fprintf(ccFile, "%s::%s() {\n", newName, newName);
  printCppCodeAttributeSettings0(allAttributes);
  fprintf(ccFile, "  val = 0;\n");
  fprintf(ccFile, "  printTypp = false;\n");
  fprintf(ccFile, "}\n");
  // end printing constructor with no arguments
  // start printing constructor with val argument
  fprintf(ccFile, "\n");
  fprintf(ccFile, "%s::%s(\n", newName, newName);
  fprintf(hhFile, " %s * valIn)\n", cppTypeName);
  fprintf(ccFile, "{\n");
  printCppCodeAttributeSettings0(allAttributes);
  fprintf(ccFile, "  val = valIn;\n");
  fprintf(ccFile, "  printTypp = false;\n");
  fprintf(ccFile, "}\n");
  // end printing constructor with val argument
  if (allAttributes->size())
    {
      // start printing constructor with attribute arguments
      fprintf(ccFile, "\n");
      fprintf(ccFile, "%s::%s(", newName, newName);
      comma = 0;
      printCppHeaderAttributeArgs(allAttributes, &comma, "");
      fprintf(hhFile, ",\n");
      fprintf(hhFile, " %s * valIn)\n", cppTypeName);
      fprintf(ccFile, "{\n");
      printCppCodeAttributeSettings(allAttributes);
      fprintf(ccFile, "  val = valIn;\n");
      fprintf(ccFile, "  printTypp = false;\n");
      fprintf(ccFile, "}\n");
      // end printing constructor with attribute arguments
    }
  // start printing destructor
  fprintf(ccFile, "\n");
  if (extend->attribs && extend->attribs->size())
    {
      fprintf(ccFile, "%s::~%s()\n", newName, newName);
      fprintf(ccFile, "{\n");
      printCppCodeAttributeDeletes(extend->newAttribs);
      fprintf(ccFile, "  delete val;\n");
      fprintf(ccFile, "}\n");
    }
  else
    fprintf(ccFile, "%s::~%s() {}\n", newName, newName);
  // end printing destructor
  // start printing printSelf
  fprintf(ccFile, "\n");
  fprintf(ccFile, "void %s::PRINTSELFDECL\n", newName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  if (printTypp)\n");
  fprintf(ccFile, "    XFPRINTF \" xsi:type=\\\"%s\\\"\");\n", name);
  printCppCodePrintAttribs(allAttributes);
  fprintf(ccFile, "  XFPRINTF \">\");\n");
  fprintf(ccFile, "  val->PRINTSELF;\n");
  fprintf(ccFile, "}\n");
  // end printing printSelf
}

/********************************************************************/

/* generator::printCppCodeSimpleList

Returned Value: none

Called By: generator::printCppCodeSimple

This prints the C++ code for an sList. It prints two constructors, a
destructor, and a printSelf Function. The destructor deletes the items
on the list.

*/

void generator::printCppCodeSimpleList( /* ARGUMENTS                 */
 char * newName,                        /* newName of simple type    */
 char * typePrefix,                     /* typePrefix of item listed */
 char * newItemType)                    /* type of item being listed */
{
  static char typeName[NAMESIZE];
  bool isBasic;

  isBasic = (typePrefix && (strcmp(typePrefix, XmlCppBase::wg3Prefix) == 0));
  if (isBasic)
    findCppTypeName(newItemType, typeName);
  else
    strcpy(typeName, newItemType);
  // start printing constructor that takes no arguments
  fprintf(ccFile, "%s::%s()\n", newName, newName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  theList = 0;\n");
  fprintf(ccFile, "}\n");
  // end printing constructor that takes no arguments
  // start printing constructor that takes a list argument
  fprintf(ccFile, "\n");
  fprintf(ccFile, "%s::%s(\n", newName, newName);
  fprintf(ccFile, "  std::list<%s *> * theListIn)\n", typeName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  theList = theListIn;\n");
  fprintf(ccFile, "}\n");
  // end printing constructor that takes a list argument
  // start printing destructor
  fprintf(ccFile, "\n");
  fprintf(ccFile, "%s::~%s()\n", newName, newName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  std::list<%s *>::iterator iter;\n", typeName);
  fprintf(ccFile, "\n");
  fprintf(ccFile, "  if (theList)\n");
  fprintf(ccFile, "    {\n");
  fprintf(ccFile, "      for (iter = theList->begin();\n");
  fprintf(ccFile, "           iter != theList->end(); iter++)\n");
  fprintf(ccFile, "        delete *iter;\n");
  fprintf(ccFile, "      delete theList;\n");
  fprintf(ccFile, "    }\n");
  fprintf(ccFile, "}\n");
  // end printing destructor
  // start printing printSelf
  fprintf(ccFile, "\n");
  fprintf(ccFile, "void %s::PRINTSELFDECL\n", newName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  std::list<%s *>::iterator iter;\n", typeName);
  fprintf(ccFile,
	  "  for (iter = theList->begin(); iter != theList->end(); iter++)\n");
  fprintf(ccFile, "    {\n");
  fprintf(ccFile, "      (*iter)->PRINTSELF;\n");
  fprintf(ccFile, "      if (*iter != theList->back())\n");
  fprintf(ccFile, "	XFPRINTF \" \");\n");
  fprintf(ccFile, "    }\n");
  fprintf(ccFile, "}\n");
  // end printing printSelf
}

/********************************************************************/

/* generator::printCppCodeStart

Returned Value: none

Called By: printCppCode

This prints in the C++ code file:
  - seven includes
  - a #define for INDENT
  - the implementation of xxxFile (if this is not an XML included file)

The name of the root element is in the top-level element; that element has
already been checked, so this does not recheck it.

*/

void generator::printCppCodeStart() /*  NO ARGUMENTS  */
{
  printStarLine(ccFile, false, true);
  fprintf(ccFile, "#include <stdio.h>             // for printf, etc.\n");
  fprintf(ccFile, "#include <string.h>            // for strdup\n");
  fprintf(ccFile, "#include <stdlib.h>            // for exit\n");
  fprintf(ccFile, "#include <list>\n");
  fprintf(ccFile, "#include <boost/regex.hpp>\n");
  fprintf(ccFile, "#include <%sxmlSchemaInstance.hh>\n", includePrefix);
  fprintf(ccFile, "#include \"%s%sClasses.hh\"\n", appIncludePrefix, baseNameNoPath);

  fprintf(ccFile, "\n");
  fprintf(ccFile, "#define INDENT 2\n");
  printStarLine(ccFile, true, false);
  printStarLine(ccFile, false, true);

  if (!top)
    return;
  fprintf(ccFile, "/* class %sFile\n", classBaseName);
  fprintf(ccFile, "\n");
  fprintf(ccFile, "*/\n");
  fprintf(ccFile, "\n");
  // start printing constructor with no arguments
  fprintf(ccFile, "%sFile::%sFile()\n", classBaseName, classBaseName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  version = 0;\n");
  fprintf(ccFile, "  header = 0;\n");
  fprintf(ccFile, "  %s = 0;\n", top->newName);
  fprintf(ccFile, "}\n");
  // end printing constructor with no arguments
  // start printing constructor with three arguments
  fprintf(ccFile, "\n");
  fprintf(ccFile, "%sFile::%sFile(\n", classBaseName, classBaseName);
  fprintf(ccFile, "  XmlVersion * versionIn,\n");
  fprintf(ccFile, "  XmlHeaderFor%s * headerIn,\n", classBaseName);
  fprintf(ccFile, "  %s * %sIn)\n", top->newTyp, top->newName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  version = versionIn;\n");
  fprintf(ccFile, "  header = headerIn;\n");
  fprintf(ccFile, "  %s = %sIn;\n", top->newName, top->newName);
  fprintf(ccFile, "}\n");
  // end printing constructor with three arguments
  // start printing destructor
  fprintf(ccFile, "\n");
  fprintf(ccFile, "%sFile::~%sFile()\n", classBaseName, classBaseName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  delete version;\n");
  fprintf(ccFile, "  delete header;\n");
  fprintf(ccFile, "  delete %s;\n", top->newName);
  fprintf(ccFile, "}\n");
  // end printing destructor
  // start printing printSelf
  fprintf(ccFile, "\n");
  fprintf(ccFile,"void %sFile::PRINTSELFDECL\n", classBaseName);
  fprintf(ccFile, "{\n");
  fprintf(ccFile, "  version->PRINTSELF;\n");
  fprintf(ccFile, "  XFPRINTF \"<%s\\n\");\n", top->name);
  fprintf(ccFile, "  header->PRINTSELF;\n");
  fprintf(ccFile, "  %s->PRINTSELF;\n",  top->newName);
  fprintf(ccFile, "  XFPRINTF \"</%s>\\n\");\n",  top->name);
  fprintf(ccFile, "}\n");
  // end printing printSelf
  printStarLine(ccFile, true, true);
}

/********************************************************************/

/* generator::printCppHeader

Returned Value: none

Called By:
  generator::printNotTop
  generator::printTop

This prints the C++ header file.

The header file name is the baseNameWithPath with "Classes.hh" appended.

*/

void generator::printCppHeader() /* NO ARGUMENTS */
{
  static char buffer[NAMESIZE];
  int n;

  sprintf(buffer, "%sClasses.hh", baseNameWithPath);
  hhFile = fopen(buffer, "w");
  if (hhFile == 0)
    {
      fprintf(stderr, "could not open file %s for writing\n", buffer);
      exit(1);
    }
  allCaps(baseNameNoPath, buffer);
  for(n = 0; buffer[n]; n++)
    {
      if (buffer[n] == '-')
	buffer[n] = '_';
    }
  printStarLine(hhFile, false, true);
  fprintf(hhFile, "#ifndef %s_HH\n", buffer);
  fprintf(hhFile, "#define %s_HH\n", buffer);
  printCppHeaderStart();
  printCppHeaderSchemaClasses();
  if (top)
    printCppHeaderEnd();
  fprintf(hhFile, "#endif // %s_HH\n", buffer);
  fclose(hhFile);
  hhFile = 0;
}

/********************************************************************/

/* generator::printCppHeaderAttributeArgs

Returned Value: none

Called By:
  generator::printCppCodeChoiceConstructors
  generator::printCppCodeComplexExtend
  generator::printCppCodeSequence
  generator::printCppCodeSimpleExtend
  generator::printCppHeaderBaseArgs
  generator::printCppHeaderBaseArgsSimple
  generator::printCppHeaderChoiceConstructors
  generator::printCppHeaderComplexExtend
  generator::printCppHeaderSequence
  generator::printCppHeaderSimpleExtend

This prints in the C++ header or code file the arguments representing
attributes of the class constructor taking arguments when the class
represents an XML schema complexType containing an XML schema sequence
and the complexType has attributes.

The value of comma will be 0 if no other argument has been printed
yet, and in this case a newline is printed followed by the argument.
The value of comma will be 1 if an argument has been printed previously,
and in this case a comma and a newline are printed followed by
the argument. If this prints one or more arguments, the value of comma
is set to 1.

The space argument is needed since the code printers use a different
amount of space at the beginning of the line (one space) than the
header printers (four spaces).

FIX - Check that comma is set correctly if this is the top level.

*/

void generator::printCppHeaderAttributeArgs( /* ARGUMENTS                     */
 std::list<XmlAttributeLoner *> * attribs,   /* list of attributes to print   */
 int * comma,                                /* non-zero = start with a comma */
 const char * space)                         /* extra space at start of line  */
{
  std::list<XmlAttributeLoner *>::iterator iter;
  static char typeName[NAMESIZE];
  
  if (!attribs) //may be null
    return;
  for (iter = attribs->begin(); iter != attribs->end(); iter++)
    { // OK if list empty
      fprintf(hhFile, "%s\n", (*comma ? "," : ""));
      *comma = 1;
      if ((*iter)->typPrefix &&
	  (strcmp((*iter)->typPrefix, XmlCppBase::wg3Prefix) == 0))
	{
	  findCppTypeName((*iter)->newTyp, typeName);
	  fprintf(hhFile, "%s %s * %sIn", space, typeName, (*iter)->newNameRef);
	}
      else
	fprintf(hhFile, "%s %s * %sIn",
		space, (*iter)->newTyp, (*iter)->newNameRef);
    }
}

/********************************************************************/

/* generator::printCppHeaderAttributeItems

Returned Value: none

Called By:
  generator::printCppHeaderChoice
  generator::printCppHeaderComplexExtend
  generator::printCppHeaderSequence
  generator::printCppHeaderSimpleExtend

This is called when the class being printed represents an XML schema
complexType containing a sequence, choice, or extension and the
complexType has attributes.  This prints in the C++ header file:
(1) the declaration of the badAttributes function for the class being
printed, and
(2) the C++ class fields representing the XML attributes.

The badAttributes function needs to be called if either the class or
any of its base classes has attributes.

*/

void generator::printCppHeaderAttributeItems(/* ARGUMENTS                    */
 std::list<XmlAttributeLoner *> * attribs,   /* list of attributes to print  */
 int hasAttribs)                             /* non-zero=print badAttributes */
{
  std::list<XmlAttributeLoner *>::iterator iter;
  static char typeName[NAMESIZE];
  
  if ((!attribs) || (attribs->size() == 0))//may be null or empty
    {
      if (hasAttribs)
	fprintf(hhFile,
	   "  bool badAttributes(std::list<AttributePair *> * attributes);\n");
      fprintf(hhFile, "\n");
      return;
    }
  fprintf(hhFile,
	  "  bool badAttributes(std::list<AttributePair *> * attributes);\n");
  fprintf(hhFile, "\n");
  for (iter = attribs->begin(); iter != attribs->end(); iter++)
    { // OK if list empty
      if ((*iter)->typPrefix &&
	  (strcmp((*iter)->typPrefix, XmlCppBase::wg3Prefix) == 0))
	{
	  findCppTypeName((*iter)->newTyp, typeName);
	  fprintf(hhFile, "  %s * %s;\n", typeName, (*iter)->newNameRef);
	}
      else
	fprintf(hhFile, "  %s * %s;\n", (*iter)->newTyp, (*iter)->newNameRef);
    }
}

/********************************************************************/

/* generator::printCppHeaderBaseArgs

Returned Value: none

Called By:
  generator::printCppCodeComplexExtend
  generator::printCppHeaderBaseArgs (recursively)
  generator::printCppHeaderComplexExtend

This prints in the C++ header or code file the arguments to the
constructor of an extension that exist because the base type has them.

This calls itself recursively to handle extension depths greater than one.
The arguments for the parent type get printed before those for the child type.

Note that this starts out by printing either a comma and a newline or
just a newline. Thus, the caller should not put a newline on the
preceding line.

*/

void generator::printCppHeaderBaseArgs( /* ARGUMENTS                         */
 char * baseName,                       /* name of base class                */
 int * comma,                           /* non-zero = start with a comma     */
 const char * space,                    /* extra space at start of line      */
 bool printAttribs)                     /* true=print attrib args, false=not */
{
  XmlComplexType * complx;
  XmlOtherContent * other;
  XmlComplexContent * comp;
  XmlSimpleContent * simp;
  XmlComplexExtension * compExtend;
  XmlSimpleContentExtension * simpExtend;
  XmlSequence * sequence;

  if (findSimpleClass(baseName))
    return; // FIX if necessary; might omit the value
  complx = findComplexClass(baseName);
  if (complx == 0)
    {
      fprintf(stderr, "base class %s not found in printCppHeaderBaseArgs\n",
	      baseName);
      exit(1);
    }
  if ((other = dynamic_cast<XmlOtherContent *>(complx->item)))
    {
      if (printAttribs && other->newAttribs && other->newAttribs->size())
	{
	  printCppHeaderAttributeArgs(other->newAttribs, comma, space);
	}
      if ((sequence = dynamic_cast<XmlSequence *>(other->base)))
	{
	  printCppHeaderSequenceArgs(sequence->items, comma, space);
	}
    }
  else if ((comp = dynamic_cast<XmlComplexContent *>(complx->item)))
    {
      if ((compExtend = dynamic_cast<XmlComplexExtension *>(comp->item)))
	{
	  printCppHeaderBaseArgs(compExtend->base, comma, space, printAttribs);
	  if (printAttribs && compExtend->attribs &&
	      compExtend->attribs->size())
	    {
	      printCppHeaderAttributeArgs(compExtend->newAttribs,
					  comma, space);
	    }
	  if ((sequence = dynamic_cast<XmlSequence *>(compExtend->item)))
	    {
	      printCppHeaderSequenceArgs(sequence->items, comma, space);
	    }
	}
      else if ((dynamic_cast<XmlComplexRestriction *>(comp->item)))
	{
	  fprintf(stderr, "cannot handle complex restriction\n");
	  exit(1);
	}
      else
	{
	  fprintf(stderr, "bad complex content type\n");
	  exit(1);
	}
    }
  else if ((simp = dynamic_cast<XmlSimpleContent *>(complx->item)))
    {
      if ((simpExtend = dynamic_cast<XmlSimpleContentExtension *>(simp->item)))
	{
	  printCppHeaderBaseArgs(simpExtend->base, comma, space, printAttribs);
	  if (printAttribs && simpExtend->attribs &&
	      simpExtend->attribs->size())
	    {
	      printCppHeaderAttributeArgs(simpExtend->newAttribs,
					  comma, space);
	    }
	}
      else if (dynamic_cast<XmlSimpleContentRestriction *>(simp->item))
	{
	  fprintf(stderr, "cannot handle simple content restriction\n");
	  exit(1);
	}
      else
	{
	  fprintf(stderr, "bad simple content type\n");
	  exit(1);
	}
    }
}

/********************************************************************/

/* generator::printCppHeaderBaseArgsSimple

Returned Value: none

Called By:
  printCppHeaderBaseArgsSimple (recursively)
  printCppHeaderSimpleExtend

This prints constructor arguments resulting from the parent type of
an XmlSimpleContentExtension (and its parent type, etc.). The first item
printed is the simple type at the end of the type chain. This calls itself
recursively to get all the way through the chain.

A parent type may be a basic type, an XmlSimpleTypeRestriction,
or an XmlComplexType whose item is an XmlSimpleContent whose item is
an XmlSimpleContentExtension.

*/

void generator::printCppHeaderBaseArgsSimple(/* ARGUMENTS                     */
 char * baseName,                            /* name of base (basic or class) */
 char * basePrefix,                          /* prefix of base (may be null)  */
 int * comma,                                /* non-zero = start with a comma */
 const char * space)                         /* extra space at start of line  */
{
  XmlComplexType * complx;
  XmlSimpleContent * simp;
  XmlSimpleContentExtension * extend;
  XmlSimpleRestriction * restrict;
  
  if ((complx = findComplexClass(baseName)) &&
      (simp = dynamic_cast<XmlSimpleContent *>(complx->item)))
    {
      if ((extend = dynamic_cast<XmlSimpleContentExtension *>(simp->item)))
	{
	  printCppHeaderBaseArgsSimple(extend->base, extend->basePrefix,
				       comma, space);
	  if (extend->attribs && extend->attribs->size())
	    {
	      printCppHeaderAttributeArgs(extend->newAttribs,
					  comma, space);
	    }
	}
      else if ((restrict = dynamic_cast<XmlSimpleRestriction *>(simp->item)))
	printCppHeaderBaseArgsSimple(restrict->base, restrict->basePrefix,
				     comma, space);
    }
}

/********************************************************************/

/* generator::printCppHeaderBaseItemsSimple

Returned Value: none

Called By:
  printCppHeaderBaseItemsSimple (recursively)
  printCppHeaderSimpleExtend (not yet)

This prints the C++ fields of a class resulting from the parent type of
an XmlSimpleContentExtension (and its parent type, etc.). The first item
printed is the simple type at the end of the type chain. This calls itself
recursively to get all the way through the chain.

A parent type may be a basic type, an XmlSimpleTypeRestriction,
or an XmlComplexType whose item is an XmlSimpleContent whose item is
an XmlSimpleContentExtension.

*/

void generator::printCppHeaderBaseItemsSimple(/* ARGUMENTS                    */
 char * baseName,                             /* name of base (basic or class */
 char * basePrefix)                           /* prefix of base (may be null) */
{
/* 
  XmlComplexType * complx;
  XmlSimpleContent * simp;
  XmlSimpleContentExtension * extend;
  XmlSimpleRestriction * restrict;
  char typeName[NAMESIZE];
  
  if (basePrefix && (strcmp(basePrefix, XmlCppBase::wg3Prefix) == 0))
    {
      findCppTypeName(baseName, typeName);
      fprintf(hhFile, "  %s * val;\n", typeName);
    }
  else if ((complx = findComplexClass(baseName)) &&
      (simp = dynamic_cast<XmlSimpleContent *>(complx->item)))
    {
      if ((extend = dynamic_cast<XmlSimpleContentExtension *>(simp->item)))
	{
	  printCppHeaderBaseItemsSimple(extend->base, extend->basePrefix);
	  if (extend->attribs && extend->attribs->size())
	    {
	      printCppHeaderAttributeItems(extend->newAttribs,
					   comma, space);
	    }
	}
      else if ((restrict = dynamic_cast<XmlSimpleRestriction *>(simp->item)))
	printCppHeaderBaseArgsSimple(restrict->base, restrict->basePrefix,
				     comma, space);
    }
 */
}

/********************************************************************/

/* generator::printCppHeaderChanges

Returned Value: none

Called By: 
  generator::printCppHeaderChoice
  generator::printCppHeaderComplexExtend
  generator::printCppHeaderSequence
  generator::printCppHeaderSimple
  generator::printCppHeaderSimpleExtend
  generator::printCppHeaderStart

This looks to see if the class whose name is clasName has any changes
recorded in the changeMap. If so, the text of the changes is printed.

This function is always called just before the right brace that ends a class
definition is printed.

*/

void generator::printCppHeaderChanges( /* ARGUMENTS                  */
 char * clasName)                      /* name of class with changes */
{
  std::list<char *> * changes;
  std::map<std::string, std::list<char *> *>::iterator iter;
  std::list<char *>::iterator ator;
  
  iter = changeMap->find(clasName);
  if (iter != changeMap->end())
    {
      changes = iter->second;
      for (ator = changes->begin(); ator != changes->end(); ator++)
	{
	  fprintf(hhFile, "%s", *ator);
	}
      changes->push_front(strdup("done"));
    }
}

/********************************************************************/

/* generator::printCppHeaderChoice

Returned Value: none

Called By: generator::printCppHeaderComplex

This prints in the C++ header file the definition of a class derived from
an XmlOtherContent that contains an XmlChoice.

*/

void generator::printCppHeaderChoice(      /* ARGUMENTS                      */
 XmlChoice * choice,                       /* XmlChoice to print class for   */
 char * newName,                           /* newName of complex type        */
 std::list<XmlAttributeLoner *> * attribs) /* flattened attributes of choice */
{
  fprintf(hhFile, "union %sVal\n", newName);
  fprintf(hhFile, "{\n");
  printCppHeaderSequenceItems(choice->items);
  fprintf(hhFile, "};\n");
  fprintf(hhFile, "\n");

  sprintf(className, "%sChoicePair", newName);
  fprintf(hhFile, "class %s :\n", className);
  fprintf(hhFile, "  public %s\n", XMLSCHEMAINSTANCEBASE);
  fprintf(hhFile, "{\n");
  fprintf(hhFile, "public:\n");
  printCppHeaderUnionEnum(choice->items);
  fprintf(hhFile, "  %s();\n", className);
  fprintf(hhFile, "  %s(", className);
  fprintf(hhFile, "\n");
  fprintf(hhFile, "    whichOne %sTypeIn,\n", newName);
  fprintf(hhFile, "    %sVal %sValueIn);\n", newName, newName);
  fprintf(hhFile, "  ~%s();\n", className);
  fprintf(hhFile, "  void PRINTSELFDECL;\n");
  fprintf(hhFile, "\n");
  fprintf(hhFile, "  whichOne %sType;\n", newName);
  fprintf(hhFile, "  %sVal %sValue;\n", newName, newName);
  printCppHeaderChanges(newName);
  fprintf(hhFile, "};\n");
  fprintf(hhFile, "\n");

  fprintf(hhFile, "class %s :\n", newName);
  fprintf(hhFile, "  public %s\n", XMLSCHEMAINSTANCEBASE);
  fprintf(hhFile, "{\n");
  fprintf(hhFile, "public:\n");
  printCppHeaderChoiceConstructors(newName, attribs, className);
  fprintf(hhFile, "  ~%s();\n", newName);
  fprintf(hhFile, "  void PRINTSELFDECL;\n");
  printCppHeaderAttributeItems(attribs, 0);
  fprintf(hhFile, "  std::list<%s *> * pairs;\n", className);
  printCppHeaderChanges(className);
  fprintf(hhFile, "};\n");
}

/********************************************************************/

/* generator::printCppHeaderChoiceConstructors

Returned Value: none

Called By: printCppHeaderChoice

This prints the declaration of two or three class constructors:
1. a constructor without arguments (always printed)
2. a constructor that takes a pairsIn argument (always printed)
3. a constructor that takes a pairsIn argument and arguments for
   any XML attributes (printed only if there are XML attributes).

*/

void generator::printCppHeaderChoiceConstructors( /* ARGUMENTS               */
 char * newName,                           /* newName of complex type        */
 std::list<XmlAttributeLoner *> * attribs, /* flattened attributes of choice */
 char * className)                         /* class for pairs list           */
{
  int comma = 0;

  fprintf(hhFile, "  %s();\n", newName);
  fprintf(hhFile, "  %s(\n", newName);
  fprintf(hhFile, "    std::list<%s *> * pairsIn);\n", className);
  if (attribs && attribs->size())
    {
      comma = 0;
      fprintf(hhFile, "  %s(", newName);
      printCppHeaderAttributeArgs(attribs, &comma, "   ");
      fprintf(hhFile, "%s\n", (comma ? "," : ""));
      fprintf(hhFile, "    std::list<%s *> * pairsIn);\n", className);
    }
}

/********************************************************************/

/* generator::printCppHeaderComplex

Returned Value: none

Called By: generator::printCppHeaderSchemaClassesComplex

This prints in the C++ header file the definition of a class derived from
an XmlComplexType.

When a complex type has no content except possibly attributes
(which may happen if, for example, the type is an abstract parent
type), complex->item is set in xmlSchema.y to an XmlOtherContent that
has no base and may or may not have attributes. Here that is handled
by printCppHeaderOtherMinimal.

*/

void generator::printCppHeaderComplex( /* ARGUMENTS                         */
  XmlComplexType * complx)             /* XmlComplexType to print class for */
{
  XmlOtherContent * other;
  XmlComplexContent * comp;
  XmlSimpleContent * simp;
  XmlComplexExtension * compExtend;
  XmlSimpleContentExtension * simpExtend;
  XmlSequence * sequence;
  XmlChoice * choice;
  std::list<XmlAttributeLoner *> allAttributes;

  findAllAttributes(complx, &allAttributes);
  if ((other = dynamic_cast<XmlOtherContent *>(complx->item)))
    {
      if ((sequence = dynamic_cast<XmlSequence *>(other->base)))
	printCppHeaderSequence(sequence, complx->newName, other->newAttribs);
      else if ((choice = dynamic_cast<XmlChoice *>(other->base)))
	printCppHeaderChoice(choice, complx->newName, other->newAttribs);
      else
        printCppHeaderOtherMinimal(complx->newName, other->newAttribs);
    }
  else if ((comp = dynamic_cast<XmlComplexContent *>(complx->item)))
    {
      if ((compExtend = dynamic_cast<XmlComplexExtension *>(comp->item)))
	printCppHeaderComplexExtend(compExtend, complx->newName);
      else if ((dynamic_cast<XmlComplexRestriction *>(comp->item)))
	{
	  fprintf(stderr, "cannot handle complex content restriction\n");
	  exit(1);
	}
      else
	{
	  fprintf(stderr, "bad complex content type\n");
	  exit(1);
	}
    }
  else if ((simp = dynamic_cast<XmlSimpleContent *>(complx->item)))
    {
      if ((simpExtend = dynamic_cast<XmlSimpleContentExtension *>(simp->item)))
	printCppHeaderSimpleExtend(simpExtend, complx->newName, &allAttributes);
      else if ((dynamic_cast<XmlSimpleContentRestriction *>(comp->item)))
	{
	  fprintf(stderr, "cannot handle simple content restriction\n");
	  exit(1);
	}
      else
	{
	  fprintf(stderr, "bad simple content type\n");
	  exit(1);
	}
    }
  else
    {
      fprintf(stderr, "bad content of complex type\n");
      exit(1);
    }
}

/********************************************************************/

/* generator::printCppHeaderComplexExtend

Returned Value: none

Called By: generator::printCppHeaderComplex

This prints in the C++ header file the definition of a class derived from
an XmlComplexExtension.

It prints the declarations of 1, 2, or 3 constructors.
1. A constructor taking no arguments.
2. If the extension has a sequence or any part of its base has a sequence,
   a constructor taking arguments corresponding to the sequence items.
3. If the extension has attributes or any part of its base has attributes,
   a constructor taking arguments corresponding to the attributes and any
   sequence items.

The sequence variable is the elements added to the type by the extension.

The arguments to the constructor taking all C++ fields must include
the names of all the C++ fields of the base type from which the
extension is derived. This is accomplished by calling printCppHeaderBaseArgs.

If the item in the extension is a choice, this prints an error message and
exits. However, at the time this is called, a choice in an extension
will first have been put inside a sequence, so the error should never occur.
(The choice in the sequence will also have been replaced by a mock element
using a mock type that is a choice, but that is not relevant in this function.)

*/

void generator::printCppHeaderComplexExtend( /* ARGUMENTS                  */
 XmlComplexExtension * extend,   /* XmlComplexExtension to print class for */
 char * newName)                 /*  newName of complex type               */
{
  XmlSequence * sequence;
  XmlChoice * choice;
  int comma;
  int hasSequence = 0;
  int hasAttribs = 0;

  choice = dynamic_cast<XmlChoice *>(extend->item);
  if (choice)
    {
      fprintf(stderr, "cannot handle choice in the extension in %s\n", newName);
      exit(1);
    }
  sequence = dynamic_cast<XmlSequence *>(extend->item);
  if (sequence && sequence->items->size())
    hasSequence = 1;
  if (extend->attribs && extend->attribs->size())
    hasAttribs = 1;
  if ((hasSequence == 0) || (hasAttribs == 0)) 
    checkBaseArgs(extend->base, &hasSequence, &hasAttribs);
  fprintf(hhFile, "class %s :\n", newName);
  fprintf(hhFile, "  public %s\n", extend->base);
  fprintf(hhFile, "{\n");
  fprintf(hhFile, "public:\n");
  fprintf(hhFile, "  %s();\n", newName);
  if (hasSequence)
    {
      comma = 0;
      fprintf(hhFile, "  %s(", newName);
      printCppHeaderBaseArgs(extend->base, &comma, "   ", false);
      if (sequence && sequence->items->size())
	{
	  printCppHeaderSequenceArgs(sequence->items, &comma, "   ");
	}
      fprintf(hhFile, ");\n");
    }
  if (hasAttribs)
    {
      comma = 0;
      fprintf(hhFile, "  %s(", newName);
      printCppHeaderBaseArgs(extend->base, &comma, "   ", true);
      if (extend->attribs && extend->attribs->size())
	{
	  printCppHeaderAttributeArgs(extend->newAttribs, &comma, "   ");
	  comma = 1;
	}
      if (sequence && sequence->items->size())
	{
	  printCppHeaderSequenceArgs(sequence->items, &comma, "   ");
	}
      fprintf(hhFile, ");\n");
    }
  fprintf(hhFile, "  ~%s();\n", newName);
  fprintf(hhFile, "  void PRINTSELFDECL;\n");
  printCppHeaderAttributeItems(extend->newAttribs, hasAttribs);
  if (sequence)
    printCppHeaderSequenceItems(sequence->items);
  fprintf(hhFile, "\n");
  fprintf(hhFile, "  bool printTypp;\n");
  printCppHeaderChanges(newName);
  fprintf(hhFile, "};\n");
}

/********************************************************************/

/* generator::printCppHeaderEnd

Returned Value: none

Called By: generator::printCppHeader

This prints the XmlHeaderForXXX class declaration.

*/

void generator::printCppHeaderEnd() /* NO ARGUMENTS    */
{
  fprintf(hhFile, "class XmlHeaderFor%s\n", classBaseName);
  fprintf(hhFile, "{\n");
  fprintf(hhFile, "public:\n");
  fprintf(hhFile, "  XmlHeaderFor%s();\n", classBaseName);
  fprintf(hhFile, "  XmlHeaderFor%s(\n", classBaseName);
  fprintf(hhFile, "    SchemaLocation * locationIn);\n");
  fprintf(hhFile, "  ~XmlHeaderFor%s();\n", classBaseName);
  fprintf(hhFile, "  void PRINTSELFDECL;\n");
  fprintf(hhFile, "\n");
  fprintf(hhFile, "  SchemaLocation * location;\n");
  fprintf(hhFile, "};\n");
  printStarLine(hhFile, true, true);
}

/********************************************************************/

/* generator::printCppHeaderOtherMinimal

Returned Value: none

Called By: generator::printCppHeaderComplex

*/

void generator::printCppHeaderOtherMinimal(
 char * newName,
 std::list<XmlAttributeLoner *> * attribs)
{
  int comma;

  fprintf(hhFile, "class %s :\n", newName);
  fprintf(hhFile, "  public %s\n", XMLSCHEMAINSTANCEBASE);
  fprintf(hhFile, "{\n");
  fprintf(hhFile, "public:\n");
  fprintf(hhFile, "  %s();\n", newName);
  if (attribs && attribs->size())
    {
      comma = 0;
      fprintf(hhFile, "  %s(", newName);
      printCppHeaderAttributeArgs(attribs, &comma, "   ");
      fprintf(hhFile, ");\n");
    }
  fprintf(hhFile, "  ~%s();\n", newName);
  fprintf(hhFile, "  void PRINTSELFDECL;\n");
  printCppHeaderAttributeItems(attribs, 0);
  printCppHeaderChanges(newName);
  fprintf(hhFile, "};\n");
}

/********************************************************************/

/* generator::printCppHeaderRestrictEnum

Returned Value: none

Called By: generator::printCppHeaderRestrictString

This prints in the C++ header file the definition of a class derived from
an XmlSimpleRestriction of a stringy type.

When this is called, it is known that either the restrictions std::list is
empty or the first item on the restrictions std::list is an XmlEnumeration.
In the second case, all the items should be XmlEnumerations.  This
checks that all the items are XmlEnumerations and then prints the body
of the class declaration.

The restrictions std::list may not be null, but it may be empty.

*/

void generator::printCppHeaderRestrictEnum(      /* ARGUMENTS                */
 char * newName,                                 /* class name               */
 char * parentName,                    /* name of C++ class being restricted */
 std::list<XmlRestrictionType *> * restrictions) /* restrictions             */
{
  XmlEnumeration * enumer;
  std::list<XmlRestrictionType *>::iterator iter;
  
  for (iter = restrictions->begin(); iter != restrictions->end(); iter++)
    {
      enumer = dynamic_cast<XmlEnumeration *>(*iter);
      if (!enumer)
	{
	  fprintf(stderr, "all restrictions must be enumerations\n");
	  exit(1);
	}
    }
  fprintf(hhFile, "  public %s\n", parentName);
  fprintf(hhFile, "{\n");
  fprintf(hhFile, "public:\n");
  fprintf(hhFile, "  %s();\n", newName);
  fprintf(hhFile, "  %s(\n", newName);
  fprintf(hhFile, "    const char * valIn);\n");
  fprintf(hhFile, "  ~%s();\n", newName);
  fprintf(hhFile, "  bool %sIsBad();\n", newName);
  fprintf(hhFile, "  void PRINTSELFDECL;\n");
}

/********************************************************************/

/* generator::printCppHeaderRestrictSList

Returned Value: none

Called By: generator::printCppHeaderSimple

*/

void generator::printCppHeaderRestrictSList(     /* ARGUMENTS                */
 char * newName,                                 /* class name               */
 char * parentName,                              /* parent class name        */
 std::list<XmlRestrictionType *> * restrictions, /* restrictions on list     */
 char * typePrefix,                              /* typePrefix of item       */
 char * newItemType,                             /* XML name of item type    */
 int level)                                      /* 1 means list restriction */
{
  static char baseCppTypeName[NAMESIZE];

  checkListRestrictions(restrictions);
  if (typePrefix && (strcmp(typePrefix, XmlCppBase::wg3Prefix) == 0))
    findCppTypeName(newItemType, baseCppTypeName);
  else
    strncpy(baseCppTypeName, newItemType, NAMESIZE);
  fprintf(hhFile, "  public %s\n", parentName);
  fprintf(hhFile, "{\n");
  fprintf(hhFile, "public:\n");
  fprintf(hhFile, "  %s();\n", newName);
  fprintf(hhFile, "  %s(\n", newName);
  fprintf(hhFile, "    std::list<%s *> * theListIn);\n", baseCppTypeName);
  fprintf(hhFile, "  ~%s();\n", newName);
  fprintf(hhFile, "  void PRINTSELFDECL;\n");
  fprintf(hhFile, "  bool %sCheck();\n", newName);
  if (level == 1)
    {
      fprintf(hhFile, "\n");
      fprintf(hhFile, "  bool bad;\n");
    }
}

/********************************************************************/

/* generator::printCppHeaderRestrictNumber

Returned Value: none

Called By: generator::printCppHeaderSimple

This prints in the C++ header file the definition of a class derived from
an XmlSimpleRestriction when the base type is a number type.

Each restriction must be one of the following, and there may be at
most one of each:
  XmlMaxExclusive
  XmlMaxInclusive
  XmlMinExclusive
  XmlMinInclusive
  
This checks that all the conditions above are satisfied and then prints the
body of the class declaration.

The restrictions std::list may not be null, but it may be empty.

*/

void generator::printCppHeaderRestrictNumber(    /* ARGUMENTS                */
 char * newName,                                 /* name of class            */
 char * parentName,                    /* name of C++ class being restricted */
 std::list<XmlRestrictionType *> * restrictions) /* restrictions             */
{
  checkNumberRestrictions(restrictions);
  fprintf(hhFile, "  public %s\n", parentName);
  fprintf(hhFile, "{\n");
  fprintf(hhFile, "public:\n");
  fprintf(hhFile, "  %s();\n", newName);
  fprintf(hhFile, "  %s(\n", newName);
  fprintf(hhFile, "    const char * valIn);\n");
  fprintf(hhFile, "  ~%s();\n", newName);
  fprintf(hhFile, "  bool %sIsBad();\n", newName);
  fprintf(hhFile, "  void PRINTSELFDECL;\n");
}

/********************************************************************/

/* generator::printCppHeaderRestrictPattern

Returned Value: none

Called By: generator::printCppHeaderRestrictString

The validity of the regular expression for the pattern in the schema
is checked by the XML schema parser.

Code to check that an instance of a pattern actually matches the
pattern is too hard to develop from scratch.

Checking instances of patterns is implemented by using boost regex-match.
See printCppCodeRestrictPattern.

This function prints an XXXIsBad function declaration. and a "bad"
field, however, since the badAttributes function of any type that
has a pattern type as an attribute will check the value of bad.

ALTERNATIVES NOT USED

A. A set of code in Haskell is available at:
http://www.haskell.org/haskellwiki/Regular_expressions_for_XML_Schema
It may be feasible to translate that into C++.

B. It may be feasible to implement a pattern match check by transcribing
the schema patterns into lex patterns in the lexer written by the
xmlInstanceParserGenerator. For many XML schema patterns, the Lex pattern
will be identical to the schema pattern.
1. In the YACC generated by the generator, define a token to represent
an instance of the pattern.
2. In the YACC generated by the generator, use the token in a production
representing an element whose type is the pattern. (If the pattern is
the type of an attribute, some other approach will be needed.)
3. In the Lex generated by the generator, have the line that looks
for the transcribed pattern return the token.

For example: suppose in bleep.xsd, the XML pattern is [ABC]*, the name of
the pattern type is pType, and pType is the type of element foo.

1. In bleep.y, put something like
%token ABCTOKEN

2. In bleep.y put something like
fooStart ABCTOKEN fooEnd

3. In bleep.lex put something like
[ABC]*    { return ABCTOKEN;}

*/

void generator::printCppHeaderRestrictPattern( /* ARGUMENTS                  */
 char * newName,                       /* class name                         */
 char * parentName)                    /* name of C++ class being restricted */
{
  fprintf(hhFile, "  public %s\n", parentName);
  fprintf(hhFile, "{\n");
  fprintf(hhFile, "public:\n");
  fprintf(hhFile, "  %s();\n", newName);
  fprintf(hhFile, "  %s(\n", newName);
  fprintf(hhFile, "    const char * valIn);\n");
  fprintf(hhFile, "  ~%s();\n", newName);
  fprintf(hhFile, "  bool %sIsBad();\n", newName);
  fprintf(hhFile, "  void PRINTSELFDECL;\n");
}

/********************************************************************/

/* generator::printCppHeaderRestrictString

Returned Value: none

Called By: generator::printCppHeaderSimple

The restrictions std::list may not be null, but it may be empty.

This is called if the root type of the newName class is a string
type (xs:ID, xs:IDREF, xs:string, xs:NMTOKEN, xs:token).

Currently, the only restrictions handled are either (1) enumerations
or (2) single pattern restrictions.

*/

void generator::printCppHeaderRestrictString(    /* ARGUMENTS               */
 char * newName,                                 /* class name              */
 char * parentName,                   /* name of C++ class being restricted */
 std::list<XmlRestrictionType *> * restrictions) /* restrictions            */
{
  XmlPattern * pattern;

  if ((restrictions->size() == 0) ||
      (dynamic_cast<XmlEnumeration *>(restrictions->front())))
    printCppHeaderRestrictEnum(newName, parentName, restrictions);
  else if ((restrictions->size() == 1) &&
	   (pattern = dynamic_cast<XmlPattern *>(restrictions->front())))
    printCppHeaderRestrictPattern(newName, parentName);
  else
    {
      fprintf(stderr, "cannot handle string restrictions for %s\n", newName);
      exit(1);
    }
}

/********************************************************************/

/* generator::printCppHeaderSchemaClasses

Returned Value: none

Called By: generator::printCppHeader

This prints in the C++ header file the definitions of classes derived
from the XmlComplexTypes and the XmlSimpleTypes in an XML Schema.
It does this by going through the "classes" std::list repeatedly.
Each time it prints the ones that have not yet been printed and
are ready to print.

The C++ class for an XmlComplexExtension has the C++ class for the
base of the extension as a parent type. In a C++ header file, a parent
type must appear before any of its child types. Therefore, this makes
sure that parent classes get printed before child classes. Whenever an
XmlComplexExtension is found, this prints the class for it only if the
base class has been printed.

If two or more XmlComplexExtensions form a loop (e.g., B extends A, C
extends B, and A extends C), which is illegal, the classes for them
will never get printed. To check for that, this checks that when nothing
more can be printed (indicated by "progress" being false), the total
number of classes printed (excluding the attribute classes) is the same
as the number of entries in the "classes" std::list.

The attribute classes are not included in the "classes" std::list, so
totalPrinted is not increased when they are printed. The child
attribute classes are printed immediately after the parent attribute
class.

*/

void generator::printCppHeaderSchemaClasses() /* NO ARGUMENTS */
{
  XmlComplexType * complx;
  XmlSimpleType * simple;
  std::list<XmlType *>::iterator iter;
  int progress = 1;
  unsigned int totalPrinted = 0;
  
  while (progress)
    {
      progress = 0;
      for (iter = classes->begin(); iter != classes->end(); iter++)
	{
	  if ((complx = dynamic_cast<XmlComplexType *>(*iter)))
	    {
	      if (complx->hhPrinted == true)
		continue;
	      printCppHeaderSchemaClassesComplex(complx,
						 &totalPrinted, &progress);
	    }
	  else if ((simple = dynamic_cast<XmlSimpleType *>(*iter)))
	    {
	      if (simple->hhPrinted == true)
		continue;
	      printCppHeaderSchemaClassesSimple(simple,
						 &totalPrinted, &progress);
	    }
	  else
	    {
	      fprintf(stderr,
		      "bad class type in printCppHeaderSchemaClasses\n");
	      exit(1);
	    }
	}
      if (totalPrinted > classes->size())
	{
	  fprintf(stderr, "schema contains circular definitions; exiting\n");
	  exit(1);
	}
    }
}

/********************************************************************/

/* generator::printCppHeaderSchemaClassesComplex

Returned Value: none

Called By: generator::printCppHeaderSchemaClasses

If a complex type has a base class, this checks whether the base class
has been printed already. If not, this does not print anything. If so,
this prints the class and any attribute classes. If a class is
printed, totalPrinted is increased by 1 and progress is set to 1.

*/

void generator::printCppHeaderSchemaClassesComplex( /* ARGUMENTS             */
 XmlComplexType * complx,               /* XmlComplexType to print class for */
 unsigned int * totalPrinted,           /* number of classes printed         */
 int * progress)                        /* set to 1 if something printed     */
{
  XmlComplexContent * cont = 0;
  XmlSimpleContent * simp = 0;
  XmlComplexType * comp = 0;
  XmlSimpleType * simple = 0;
  XmlComplexExtension * compExtend = 0;
  XmlSimpleContentExtension * simpExtend = 0;
  bool printIt;

  if ((cont = dynamic_cast<XmlComplexContent *>(complx->item)) &&
      (compExtend = dynamic_cast<XmlComplexExtension *>(cont->item)))
    {
      comp = findComplexClass(compExtend->base);
      if (comp == 0)
	{
	  fprintf(stderr, "base class %s not found in "
		  "printCppHeaderSchemaClasses\n", compExtend->base);
	  exit(1);
	}
      printIt = comp->hhPrinted;
    }
  else if ((simp = dynamic_cast<XmlSimpleContent *>(complx->item)) &&
	   (simpExtend = dynamic_cast<XmlSimpleContentExtension *>(simp->item)))
    {
      if (simpExtend->basePrefix &&
	  (strcmp(simpExtend->basePrefix, XmlCppBase::wg3Prefix) == 0))
	{
	  printIt = true;
	}
      else if ((comp = findComplexClass(simpExtend->base)))
	{
	  printIt = comp->hhPrinted;
	}
      else if ((simple = findSimpleClass(simpExtend->base)))
	{
	  printIt = simple->hhPrinted;
	}
      else
	{
	  fprintf(stderr, "base class %s not found in "
		  "printCppHeaderSchemaClassesComplex\n", simpExtend->base);
	  exit(1);
	}
    }
  else
    printIt = true;
  if (printIt)
    {
      printCppHeaderComplex(complx);
      printStarLine(hhFile, true, true);
      (*totalPrinted)++;
      *progress = 1;
      complx->hhPrinted = true;
    }
}

/********************************************************************/

/* generator::printCppHeaderSchemaClassesSimple

Returned Value: none

Called By: generator::printCppHeaderSchemaClasses

If a simple type has a base class, this checks whether the base class
has been printed already. If not, this does not print anything. If so,
this prints the class.  If a class is printed, totalPrinted is
increased by 1 and progress is set to 1.

*/

void generator::printCppHeaderSchemaClassesSimple( /* ARGUMENTS             */
 XmlSimpleType * simple,                /* XmlSimpleType to print class for */
 unsigned int * totalPrinted,           /* number of classes printed        */
 int * progress)                        /* set to 1 if something printed    */
{
  XmlSimpleRestriction * restrict;
  XmlSimpleType * simp = 0;
  XmlSimpleList * sList;
  bool printIt;

  if ((restrict = dynamic_cast<XmlSimpleRestriction *>(simple->item)))
    {
      if (restrict->basePrefix &&
	  (strcmp(restrict->basePrefix, XmlCppBase::wg3Prefix) == 0))
	{
	  printIt = true;
	}
      else if ((simp = findSimpleClass(restrict->newBase)))
	{
	  printIt = simp->hhPrinted;
	}
    }
  else if ((sList = dynamic_cast<XmlSimpleList *>(simple->item)))
    {
      printIt = true;
    }
  else
    {
      fprintf(stderr, "cannot handle simple type %s\n", simple->name);
      exit(1);
    }
  if (printIt)
    {
      printCppHeaderSimple(simple);
      printStarLine(hhFile, true, true);
      (*totalPrinted)++;
      *progress = 1;
      simple->hhPrinted = true;
    }
}

/********************************************************************/

/* generator::printCppHeaderSchemaClassNames

Returned Value: none

Called By: generator::printCppHeaderStart

This prints in the C++ header file the declarations of classes derived
from types in the XML schema.

A class name is printed for each class in the classes std::list.

In addition, if an XmlComplexType has an item that is any of
  - an XmlOtherContent with XML attributes
  - an XmlComplexContent with an item that is an XmlComplexExtension
    with attributes
  - an XmlSimpleContent with an item that is an XmlSimpleContentExtension
    with attributes
Then a flattened version of the attributes std::list is made and stored as the
   value of the newAttribs of the class.

If any bad type is found, an error message is printed and this exits.

If a class is an XmlComplexType with XmlOtherContent that is a choice,
this prints an XXXChoicePair class name in the classes std::list.

If an XmlComplexRestriction or XmlSimpleContentRestriction is found, this
prints a "cannot handle" message and exits.

*/

void generator::printCppHeaderSchemaClassNames() /* NO ARGUMENTS */
{
  XmlComplexType * complx;
  XmlComplexContent * cont;
  XmlSimpleContent * simp;
  XmlSimpleType * simple;
  XmlOtherContent * other;
  XmlComplexExtension * compExtend;
  XmlSimpleContentExtension * simpExtend;
  std::list<XmlType *>::iterator iter;
  
  for (iter = classes->begin(); iter != classes->end(); iter++)
    {
      if ((complx = dynamic_cast<XmlComplexType *>(*iter)))
	{
	  if (!complx->hhPrinted)
	    fprintf(hhFile, "class %s;\n", complx->newName);
	  if ((other = dynamic_cast<XmlOtherContent *>(complx->item)))
	    {
	      if (other->attribs && other->attribs->size())
		{
		  other->newAttribs = new std::list<XmlAttributeLoner *>;
		  flattenAttributes(other->newAttribs, other->attribs);
		}
	      if (dynamic_cast<XmlChoice *>(other->base))
		{
		  if (!complx->hhPrinted)
		    fprintf(hhFile, "class %sChoicePair;\n", complx->newName);
		}
	    }
	  else if ((cont = dynamic_cast<XmlComplexContent *>(complx->item)))
	    {
	      if ((compExtend=dynamic_cast<XmlComplexExtension *>(cont->item)))
		{
		  if (compExtend->attribs && compExtend->attribs->size())
		    {
		      compExtend->newAttribs =
			new std::list<XmlAttributeLoner *>;
		      flattenAttributes(compExtend->newAttribs,
					compExtend->attribs);
		    }
		}
	      else if (dynamic_cast<XmlComplexRestriction *>(cont->item))
		{
		  fprintf(stderr,
			  "cannot handle complex content restriction\n");
		  exit(1);
		}
	      else
		{
		  fprintf(stderr, "bad complex content type\n");
		  exit(1);
		}
	    }
	  else if ((simp = dynamic_cast<XmlSimpleContent *>(complx->item)))
	    {
	      if ((simpExtend =
		   dynamic_cast<XmlSimpleContentExtension *>(simp->item)))
		{
		  if (simpExtend->attribs && simpExtend->attribs->size())
		    {
		      simpExtend->newAttribs =
			new std::list<XmlAttributeLoner *>;
		      flattenAttributes(simpExtend->newAttribs,
					simpExtend->attribs);
		    }
		}
	      else if (dynamic_cast<XmlSimpleContentRestriction *>(simp->item))
		{
		  fprintf(stderr, "cannot handle simple content restriction\n");
		  exit(1);
		}
	      else
		{
		  fprintf(stderr, "bad simple content type\n");
		  exit(1);
		}
	    }
	  else
	    {
	      fprintf(stderr, "bad complex type %s\n", complx->name);
	      exit(1);
	    }
	}
      else if ((simple = dynamic_cast<XmlSimpleType *>(*iter)))
	{
	  if (!simple->hhPrinted)
	    fprintf(hhFile, "class %s;\n", simple->newName);
	}
      else
	{
	  fprintf(stderr, "bad class type\n");
	  exit(1);
	}
    }
}

/********************************************************************/

/* generator::printCppHeaderSequence

Returned Value: none

Called By: generator::printCppHeaderComplex

This prints in the C++ header file the definition of a class derived from
an XmlOtherContent that contains an XmlSequence.

This prints declarations of 1, 2, or 3 constructors.
1. A constructor is always declared that takes no arguments.
2. If there are attributes, a constructor is declared that
   takes arguments from both attributes and the sequence.
3. If the sequence is not empty, a constructor is declared that takes
   arguments from only the sequence.

*/

void generator::printCppHeaderSequence(   /* ARGUMENTS                       */
 XmlSequence * sequence,                  /* XmlSequence to print class for  */
 char * newName,                          /* newName of complex type         */
 std::list<XmlAttributeLoner *> * attribs)/* flattened attributes of complex */
{
  int comma;

  fprintf(hhFile, "class %s :\n", newName);
  fprintf(hhFile, "  public %s\n", XMLSCHEMAINSTANCEBASE);
  fprintf(hhFile, "{\n");
  fprintf(hhFile, "public:\n");
  fprintf(hhFile, "  %s();\n", newName);
  if (sequence->items->size())
    {
      comma = 0;
      fprintf(hhFile, "  %s(", newName);
      printCppHeaderSequenceArgs(sequence->items, &comma, "   ");
      fprintf(hhFile, ");\n");
    }
  if (attribs && attribs->size())
    {
      comma = 0;
      fprintf(hhFile, "  %s(", newName);
      printCppHeaderAttributeArgs(attribs, &comma, "   ");
      printCppHeaderSequenceArgs(sequence->items, &comma, "   "); // OK if none
      fprintf(hhFile, ");\n");
    }
  fprintf(hhFile, "  ~%s();\n", newName);
  fprintf(hhFile, "  void PRINTSELFDECL;\n");
  printCppHeaderAttributeItems(attribs, 0);
  printCppHeaderSequenceItems(sequence->items);
  printCppHeaderChanges(newName);
  fprintf(hhFile, "};\n");
}

/********************************************************************/

/* generator::printCppHeaderSequenceArgs

Returned Value: none

Called By:
  generator::printCppCodeComplexExtend
  generator::printCppCodeSequence
  generator::printCppHeaderBaseArgs
  generator::printCppHeaderComplexExtend
  generator::printCppHeaderSequence

This prints in the C++ header or code file the arguments to the class
constructor taking arguments when the class represents an XML schema
complexType containing an XML schema sequence.

The value of comma will be 0 if no other argument has been printed
yet, and in this case a newline is printed followed by the argument.
The value of comma will be 1 if an argument has been printed previously,
and in this case a comma and a newline are printed followed by
the argument. If this prints one or more arguments, the value of comma
is set to 1.

The space argument is needed since the code printers use a different
amount of space at the beginning of the line (one space) than the
header printers (four spaces).

If the type of an element in the sequence is an XmlComplexType or
XmlSimpleType, then the type of the argument is the name of the type.

This function works only if each element has a typ (i.e., only if the
schema is in canonical form).

If any of the items is not an element, this prints an error message and
exits. However, at the time this is called all items that are choice or
sequence will have been replaced by mock elements, so the error should
never occur.

*/

void generator::printCppHeaderSequenceArgs( /* ARGUMENTS                     */
 std::list<XmlChoSeqItem *> * items,        /* list of items to print        */
 int * comma,                               /* non-zero = start with a comma */
 const char * space)                        /* extra space at start of line  */
{
  std::list<XmlChoSeqItem *>::iterator iter;
  XmlElementLocal * element;
  char typeName[NAMESIZE];
  bool isBasic;

  for (iter = items->begin(); iter != items->end(); iter++)
    {
      if ((element = dynamic_cast<XmlElementLocal *>(*iter)))
	{
	  isBasic = (element->typPrefix &&
		     (strcmp(element->typPrefix, XmlCppBase::wg3Prefix) == 0));
	  if (element->needList)
	    { // zero or many elements are allowed
	      if (element->newTyp)
		{
		  fprintf(hhFile, "%s\n", (*comma ? "," : ""));
		  if (isBasic)
		    {
		      findCppTypeName(element->newTyp, typeName);
		      fprintf(hhFile, "%s std::list<%s *> * %sIn",
			      space, typeName, element->newName);
		    }
		  else
		    {
		      fprintf(hhFile, "%s std::list<%s *> * %sIn",
			      space, element->newTyp, element->newName);
		    }
		  *comma = 1;
		}
	    }
	  else // only one element allowed
	    {
	      if (element->newTyp)
		{
		  fprintf(hhFile, "%s\n", (*comma ? "," : ""));
		  if (isBasic)
		    {
		      findCppTypeName(element->newTyp, typeName);
		      fprintf(hhFile, "%s %s * %sIn",
			      space, typeName, element->newName);
		    }
		  else
		    {
		      fprintf(hhFile, "%s %s * %sIn",
			      space, element->newTyp, element->newName);
		    }
		  *comma = 1;
		}
	    }
	}
      else
	{
	  fprintf(stderr,
		  "printCppHeaderSequenceArgs cannot handle non-element\n");
	  exit(1);
	}
    }
}

/********************************************************************/

/* generator::printCppHeaderSequenceItems

Returned Value: none

Called By:
  generator::printCppHeaderChoice
  generator::printCppHeaderComplexExtend
  generator::printCppHeaderSequence

This prints in the C++ header file the C++ fields of a class when the
class contains an XML schema sequence.

If the type of an element in the sequence is an XmlComplexType or an
an XmlSimpleType, then the type of the field is the name of the type.

If any of the items is not an element, this prints an error message and
exits. However, at the time this is called all items that are choice or
sequence will have been replaced by mock elements, so the error should
never occur.

*/

void generator::printCppHeaderSequenceItems( /* ARGUMENTS              */
 std::list<XmlChoSeqItem *> * items)         /* list of items to print */
{
  std::list<XmlChoSeqItem *>::iterator iter;
  XmlElementLocal * element;
  char typeName[NAMESIZE];
  bool isBasic;

  for (iter = items->begin(); iter != items->end(); iter++)
    {
      if ((element = dynamic_cast<XmlElementLocal *>(*iter)))
	{
	  isBasic = (element->typPrefix &&
		     (strcmp(element->typPrefix, XmlCppBase::wg3Prefix) == 0));
	  if (element->needList)
	    { // zero or many elements are allowed
	      if (element->newTyp)
		{
		  if (isBasic)
		    {
		      findCppTypeName(element->newTyp, typeName);
		      fprintf(hhFile, "  std::list<%s *> * %s;\n",
			      typeName, element->newName);
		    }
		  else
		    {
		      fprintf(hhFile, "  std::list<%s *> * %s;\n",
			      element->newTyp, element->newName);
		    }
		}
	    }
	  else // only one element allowed
	    {
	      if (element->newTyp)
		{
		  if (isBasic)
		    {
		      findCppTypeName(element->newTyp, typeName);
		      fprintf(hhFile, "  %s * %s;\n",
			      typeName, element->newName);
		    }
		  else
		    {
		      fprintf(hhFile, "  %s * %s;\n",
			      element->newTyp, element->newName);
		    }
		}
	    }
	}
      else
	{
	  fprintf(stderr,
		  "printCppHeaderSequenceItems cannot handle non-element\n");
	  exit(1);
	}
    }
}

/********************************************************************/

/* generator::printCppHeaderSimple

Returned Value: none

Called By: generator::printCppHeaderSchemaClassesSimple

This prints in the C++ header file the definition of a C++ class
equivalent to an XML type derived by restriction or listing from an
XmlSimpleType or an XML built-in type.

This handles restrictions of the XML built-in types listed below and
restrictions of those types.

decimal
double
float
ID
IDREF
int
integer
long
NMTOKEN
nonNegativeInteger
positiveInteger
short
string
unsignedInt
unsignedLong
unsignedShort

If any other type of restriction is encountered (such as a restriction
of a boolean), this prints an error message and exits.

*/

void generator::printCppHeaderSimple( /* ARGUMENTS                        */
 XmlSimpleType * simple)              /* XmlSimpleType to print class for */
{
  XmlSimpleRestriction * restrict; // the simple type 
  XmlSimpleList * sList;
  std::list<XmlRestrictionType *> * rests;
  static char baseCppTypeName[NAMESIZE];
  static char rootCppTypeName[NAMESIZE];
  static char rootXmlTypeName[NAMESIZE];
  char * base;
  int level;

  fprintf(hhFile, "class %s :\n", simple->newName);
  if ((restrict = dynamic_cast<XmlSimpleRestriction *>(simple->item)))
    {
      rests = restrict->restrictions;
      level = 0;
      if (isRestrictedSList(simple, baseCppTypeName, rootCppTypeName,
			    rootXmlTypeName, &level))
	{ // restriction of an sList
	  printCppHeaderRestrictSList(simple->newName, baseCppTypeName, rests,
				      rootCppTypeName, rootXmlTypeName, level);
	}
      else
	{ // restriction of a string or number
	  findRootXmlType(simple, rootXmlTypeName);
	  findCppTypeName(rootXmlTypeName, rootCppTypeName);
	  base = restrict->base;
	  if (restrict->basePrefix &&
	      (strcmp(restrict->basePrefix, XmlCppBase::wg3Prefix) == 0))
	    findCppTypeName(restrict->newBase, baseCppTypeName);
	  else
	    strncpy(baseCppTypeName, restrict->newBase, NAMESIZE);
	  if (isStringy(rootCppTypeName))
	    printCppHeaderRestrictString(simple->newName, baseCppTypeName,
					 rests);
	  else if (isNumber(rootCppTypeName))
	    printCppHeaderRestrictNumber(simple->newName, baseCppTypeName,
					 rests);
	  else
	    {
	      fprintf(stderr, "cannot handle %s, which restricts %s\n",
		      simple->name, base);
	      exit(1);
	    }
	}
    }
  else if ((sList = dynamic_cast<XmlSimpleList *>(simple->item)))
    {
      printCppHeaderSimpleList(simple->newName,
			       sList->typePrefix, sList->newItemType);
    }
  else
    {
      fprintf(stderr, "cannot handle simple type %s\n", simple->name);
      exit(1);
    }
  printCppHeaderChanges(className);
  fprintf(hhFile, "};\n");
}

/********************************************************************/

/* generator::printCppHeaderSimpleExtend

Returned Value: none

Called By: generator::printCppHeaderComplex

This prints in the C++ header file the declaration of a C++ class
representing a complex class whose item is an
XmlSimpleContentExtension. That type of extension adds or restricts
XSDL attributes. Every XmlSimpleContentExtension has a value that is a
basic type and is put into a C++ field called val. An
XmlSimpleContentExtension and its ancestors cannot have any elements.

See the documentation of printCppCodeSimpleExtend. This declares the
constructors that are defined there. 

The declarations for the C++ fields representing any XML attributes
are also printed.

*/

void generator::printCppHeaderSimpleExtend( /* ARGUMENTS                      */
 XmlSimpleContentExtension * extend,  /* extension from which to print class  */
 char * newName,                      /* newName of type containing extension */
 std::list<XmlAttributeLoner *> * allAttributes) /* all attributes            */
{
  XmlComplexType * complx;
  XmlSimpleType * simple;
  int comma;
  int hasAttributes;
  bool isBasic;
  static char rootXmlTypeName[TEXTSIZE];
  static char cppTypeName[NAMESIZE];

  isBasic = (extend->basePrefix &&
	     (strcmp(extend->basePrefix, XmlCppBase::wg3Prefix) == 0));
  if (isBasic)
    findCppTypeName(extend->base, cppTypeName);
  else if ((simple = findSimpleClass(extend->base)))
    {
      findRootXmlType(simple, rootXmlTypeName);
      findCppTypeName(rootXmlTypeName, cppTypeName);
    }
  else if ((complx = findComplexClass(extend->base)))
    {
      findRootXmlTypeComplex(complx, rootXmlTypeName);
      findCppTypeName(rootXmlTypeName, cppTypeName);
    }
  else
    {
      fprintf(stderr, "base type %s not found in printCppHeaderSimpleExtend\n",
	      extend->base);
      exit(1);
    }
  fprintf(hhFile, "class %s :\n", newName);
  fprintf(hhFile, "  public %s\n",
	  (isBasic ? XMLSCHEMAINSTANCEBASE : extend->base));
  fprintf(hhFile, "{\n");
  fprintf(hhFile, "public:\n");
  fprintf(hhFile, "  %s();\n", newName);
  fprintf(hhFile, "  %s(\n", newName);
  fprintf(hhFile, "    %s * valIn);\n", cppTypeName);
  hasAttributes = ((extend->attribs && extend->attribs->size()) ? 1 : 0);
  if (hasAttributes == 0)
    checkBaseArgsSimple(extend->base, extend->basePrefix, &hasAttributes);
  if (allAttributes->size())
    {
      comma = 0;
      fprintf(hhFile, "  %s(", newName);
      printCppHeaderAttributeArgs(allAttributes, &comma, "   ");
      fprintf(hhFile, ",\n");
      fprintf(hhFile, "    %s * valIn);\n", cppTypeName);
    }
  fprintf(hhFile, "  ~%s();\n", newName);
  fprintf(hhFile, "  void PRINTSELFDECL;\n");
  printCppHeaderAttributeItems(extend->newAttribs, hasAttributes);
  if (isBasic)
    {
      fprintf(hhFile, "  %s * val;\n", cppTypeName);
      fprintf(hhFile, "\n");
      fprintf(hhFile, "  bool printTypp;\n");
    }
  printCppHeaderChanges(newName);
  fprintf(hhFile, "};\n");
}

/********************************************************************/

/* generator::printCppHeaderSimpleList

Returned Value: none

Called By: generator::printCppHeaderSimple

This prints in the C++ header file the declaration of a C++ class
representing an XmlSimpleList of an XML basic item. 

*/

void generator::printCppHeaderSimpleList( /* ARGUMENTS                  */
 char * newName,                          /* newName of simple type     */
 char * typePrefix,                       /* type prefix of item listed */
 char * newItemType)                      /* XML type of item listed    */
{
  static char typeName[NAMESIZE];

  if (typePrefix && (strcmp(typePrefix, XmlCppBase::wg3Prefix) == 0))
    findCppTypeName(newItemType, typeName);
  else
    strcpy(typeName, newItemType);
  fprintf(hhFile, "  public %s\n", XMLSCHEMAINSTANCEBASE);
  fprintf(hhFile, "{\n");
  fprintf(hhFile, "public:\n");
  fprintf(hhFile, "  %s();\n", newName);
  fprintf(hhFile, "  %s(\n", newName);
  fprintf(hhFile, "    std::list<%s *> * theListIn);\n", typeName);
  fprintf(hhFile, "  ~%s();\n", newName);
  fprintf(hhFile, "  void PRINTSELFDECL;\n");
  fprintf(hhFile, "\n");
  fprintf(hhFile, "  std::list<%s *> * theList;\n", typeName);
}

/********************************************************************/

/* generator::printCppHeaderStart

Returned Value: none

Called By: generator::printCppHeader

This prints in the C++ header file:
  - three includes
  - the declaration of the XXXFile class needed in every header file
    derived from an XML schema (if the input file is not an included file)
  - the declarations of C++ classes derived from types in the XML schema
  - the definition of the XXXFile class (if the input file is not an
    included file)

*/

void generator::printCppHeaderStart() /* NO ARGUMENTS */
{
  std::list<char *>::iterator iter;

  fprintf(hhFile, "#include <stdio.h>\n");
  fprintf(hhFile, "#include <list>\n");
  fprintf(hhFile, "#include <%sxmlSchemaInstance.hh>\n", includePrefix);
  for (iter = includedSchemas->begin(); iter != includedSchemas->end(); iter++)
    {
      fprintf(hhFile, "#include \"%s%sClasses.hh\"\n", appIncludePrefix, *iter);
    }
  if (moreIncludes)
    {
      for (iter = moreIncludes->begin(); iter != moreIncludes->end(); iter++)
	fprintf(hhFile, "%s", *iter);
    }
  printStarLine(hhFile, true, true);
  snprintf(className, NAMESIZE, "%sFile", classBaseName);
  if (top)
    {
      fprintf(hhFile, "class %s;\n", className);
    }
  printCppHeaderSchemaClassNames();
  if (top)
    fprintf(hhFile, "class XmlHeaderFor%s;\n", classBaseName);
  printStarLine(hhFile, true, false);
  printStarLine(hhFile, false, true);
  if (!top)
    return;
  fprintf(hhFile, "class %s :\n", className);
  fprintf(hhFile, "  public %s\n", XMLSCHEMAINSTANCEBASE);
  fprintf(hhFile, "{\n");
  fprintf(hhFile, "public:\n");
  fprintf(hhFile, "  %s();\n", className);
  fprintf(hhFile, "  %s(\n", className);
  fprintf(hhFile, "    XmlVersion * versionIn,\n");
  fprintf(hhFile, "    XmlHeaderFor%s * headerIn,\n", classBaseName);
  fprintf(hhFile, "    %s * %sIn);\n", top->newTyp, top->newName);
  fprintf(hhFile, "  ~%s();\n", className);
  fprintf(hhFile, "  void PRINTSELFDECL;\n");
  fprintf(hhFile, "\n");
  fprintf(hhFile, "  XmlVersion * version;\n");
  fprintf(hhFile, "  XmlHeaderFor%s * header;\n", classBaseName);
  fprintf(hhFile, "  %s * %s;\n", top->newTyp, top->newName);
  printCppHeaderChanges(className);
  fprintf(hhFile, "};\n");
  printStarLine(hhFile, true, true);
}

/********************************************************************/

/* generator::printCppHeaderUnionEnum

Returned Value: none

Called By:  generator::printCppHeaderChoice

This prints in the C++ header file an enumeration that will identify
which element of a union has been used.

*/

void generator::printCppHeaderUnionEnum( /* ARGUMENTS                         */
 std::list<XmlChoSeqItem *> * items)     /* items to generate enum items from */
{
  std::list<XmlChoSeqItem *>::iterator iter;
  XmlElementLocal * element;

  fprintf(hhFile, "  enum whichOne {\n");
  for (iter = items->begin(); iter != items->end(); iter++)
    {
      if ((element = dynamic_cast<XmlElementLocal *>(*iter)))
	{
	  fprintf(hhFile, "    %sE%s\n", element->newName,
		  ((*iter == items->back()) ? " };" : ","));
	}
      else
	{
	  fprintf(stderr,
		  "printCppHeaderUnionEnum cannot handle non-element\n");
	  exit(1);
	}
    }
}

/********************************************************************/

/* generator::printLex

Returned Value: none

Called By:  printTop

This prints the lex file.

The lex file name is baseNameWithPath with .lex appended

*/

void generator::printLex() /* NO ARGUMENTS */
{
  char nameBuf[NAMESIZE];

  sprintf(nameBuf, "%s.lex", baseNameWithPath);
  lexFile = fopen(nameBuf, "w");
  if (lexFile == 0)
    {
      fprintf(stderr, "could not open file %s for writing\n", nameBuf);
      exit(1);
    }
  printLexStart();
  printLexElementNames();
  printLexAttributeNames();
  printLexExtensions();
  printLexEnd();
  fclose(lexFile);
  lexFile = 0;
}

/********************************************************************/

/* generator::printLexAttributeNames

Returned Value: none

Called By: printLex

This prints the lines of the lex file that deal with attribute names.

*/

void generator::printLexAttributeNames() /* NO ARGUMENTS  */
{
  std::list<char *>::iterator iter;
  int numChars;
  char buffer[NAMESIZE];

  if (attributeInfo->size() == 0)
    return;
  fprintf(lexFile, "\n");
  for (iter = attributeInfo->begin(); iter != attributeInfo->end(); iter++)
    {
      allCaps(*iter, buffer);
      numChars = fprintf(lexFile, "{W}\"%s\"{W}\"=\"", *iter);
      for ( ; numChars < 30; numChars++)
	fprintf(lexFile, " ");
      fprintf(lexFile, "{ ECH; return %s; }\n", buffer);
    }
}

/********************************************************************/

/* generator::printLexElementNames

Returned Value: none

Called By: printLex

This prints the lines of the lex file that deal with element names.

An elementInfo representing the top-level element is included in the
elementInfos in buildClasses since the name of the top-level element
needs to appear in the lex section that recognizes element names.

The same element name may be used several times.

*/

void generator::printLexElementNames() /* NO ARGUMENTS  */
{
  std::list<elementInfo *>::iterator iter;
  int numChars;
  char buffer[NAMESIZE];
  char * currentName;

  currentName = dummyName;
  for (iter = elementInfos->begin(); iter != elementInfos->end(); iter++)
    {
      if ((*iter)->element->mock)
	continue;
      if (strcmp((*iter)->element->newName, currentName) == 0)
	continue;
      else
	currentName = (*iter)->element->newName;
      allCaps(currentName, buffer);
      numChars = fprintf(lexFile, "\"</\"{W}\"%s\"{W}\">\" ", currentName);
      for ( ; numChars < 30; numChars++)
	fprintf(lexFile, " ");
      fprintf(lexFile, "{ECH; return %sEND;}\n", buffer);
      numChars = fprintf(lexFile, "\"<\"{W}\"%s\" ", currentName);
      for ( ; numChars < 30; numChars++)
	fprintf(lexFile, " ");
      fprintf(lexFile, "{ECH; return %sSTART;}\n", buffer);
    }
}

/********************************************************************/

/* generator::printLexEnd

Returned Value: none

Called By: printLex

This prints the end of the lex file in the order listed. The items at the
end of the lex file that read from the instance file:
1. read a target namespace declaration if there is a target namespace.
   This is often the third line of an instance file and has the form:
   xmlns="<targetNamespace>"
   where <targetNamespace> is the targetNamespace specified in the schema.
   If that form is read, the lexer returns XMLNSTARGET, which has an
   integer token value (not a string value).
2. read the schema instance declaration.
   This is often the fourth line of an instance file and has the form:
   xmlns:<prefix>="http://www.w3.org/2001/XMLSchema-instance"
   where <prefix> is the prefix for the targetNamespace specified in the
   schema if a prefix is specified and is xsi otherwise.
   If that form is found, the lexer returns XMLNSPREFIX. XMLNSPREFIX
   has an integer token value (not a string value).
3. read the > that ends a tag
4. read and save a TERMINALSTRING
5. read and ignore white space
6. read any other character and return BAD

In addition, this prints the yywrap function in the lex file.

*/

void generator::printLexEnd() /* NO ARGUMENTS  */
{
  fprintf(lexFile, "\n");
  if (target)
    {
      fprintf(lexFile, "\"xmlns\"{W}\"=\"{W}\"\\\"%s\\\"\"", target);
      fprintf(lexFile, " {ECH; return XMLNSTARGET;}\n");
      fprintf(lexFile, "\n");
    }
  fprintf(lexFile, "\"xmlns:xsi\"{W}\"=\"{W}\"\\\"");
  fprintf(lexFile, "http://www.w3.org/2001/XMLSchema-instance\\\"\" {ECH;\n");
  fprintf(lexFile, 
	  "                                           return XMLNSPREFIX;}\n");
  fprintf(lexFile, "\n");
  fprintf(lexFile, "\">\"                           {ECH; return ENDITEM;}\n");
  if (wholeFlag)
    fprintf(lexFile,
	    "\"/>\"                          {ECH; return ENDWHOLEITEM;}\n");
  fprintf(lexFile, "\n");
  fprintf(lexFile, "\\\"[^\\\"]+\\\"                 {ECH;\n");
  fprintf(lexFile, "                            int n;\n");
  fprintf(lexFile, "                            for (n = 1; ");
  fprintf(lexFile, "%stext[n] != '\"'; n++);\n", yyprefix);
  fprintf(lexFile, "                            %stext[n] = 0;\n", yyprefix);
  fprintf(lexFile, 
	  "                            %slval.sVal = strdup(%stext + 1);\n",
	  yyprefix, yyprefix);
  fprintf(lexFile, "                            return TERMINALSTRING;\n");
  fprintf(lexFile, "                           }\n");
  fprintf(lexFile, "\n");
  fprintf(lexFile, "{W}                        {ECH;}\n");
  fprintf(lexFile, "\n");
  fprintf(lexFile, ".                          {ECH; return BAD;}\n");
  fprintf(lexFile, "\n");
  fprintf(lexFile, "%%%%\n");
  fprintf(lexFile, "\n");
  fprintf(lexFile, "int %swrap()\n", yyprefix);
  fprintf(lexFile, "{\n");
  fprintf(lexFile, "  return 1;\n");
  fprintf(lexFile, "}\n");
}

/********************************************************************/

/* generator::printLexExtensions

Returned Value: none

Called By: generator::printLex

This goes through the extensionInfos std::list, and for each name on
the std::list (such as "UK-Address") prints in the lex file
an item such as:

{W}"xsi:type"{W}"="{W}"\"UK-Address\""  { ECH; return UK_ADDRESSDECL; }

Items like that are needed when an XmlElementLocal has a type that is
the parent type of one or more extensions. In the example above,
Address is the parent type of UK-Address. If a instance file uses a
UK-Address, where the schema specifies Address as the type, the instance
file must include <... xsi:type="UK-Address">.  The lex file must
be able to recognize that as the start of a UK-Address.

Getting the required information in place for printing these items is a
major nuisance. The following things have been put in place solely because
they are needed for that purpose:

1. The extensionInfos field of the xmlInstanceParserGenerator.
2. The extensions field of the XmlComplexType class. This std::list
   includes all the children, grandchildren, etc., that the type has.
3. The enterKid function.
4. The call to enterKid in printCppCodeComplexExtend.
5. The buildExtensions function.
6. The buildSomeExtensions function.
7. The enterName function.
8. The buildDescendants function.
9. The buildDescendantSet function.
10. The calls to buildDescendants and buildExtensions in printLex.

*/

void generator::printLexExtensions() /* NO ARGUMENTS  */
{
  std::list<char *>::iterator iter;
  char buffer[NAMESIZE];
  int n;

  if (extensionInfos->size() == 0)
    return;
  fprintf(lexFile, "\n");
  for (iter = extensionInfos->begin(); iter != extensionInfos->end(); iter++)
    {
      allCaps(*iter, buffer);
      for (n=0; buffer[n]; n++)
	{
	  if (buffer[n] == '-')
	    buffer[n] = '_';
	}
      fprintf(lexFile, 
	      "{W}\"xsi:type\"{W}\"=\"{W}\"\\\"%s\\\"\"", *iter);
      fprintf(lexFile, "  { ECH; return %sDECL; }\n", buffer);
    }
}

/********************************************************************/

/* generator::printLexStart

Returned Value: none

Called By: printLex

This prints the beginning of the lex file.

The .cc file produced by flex from the .lex file this prints includes
several items that Windows does not like. In order to make the .cc file
compilable on Windows, this prints a 7-line #ifdef WIN32 section.

If the schema has a target namespace:
The schema location specification of an XML instance file must be:
    <prefix>:schemaLocation="<targetNamespace> <location>"
where <prefix> is the prefix specified in the schema for the
targetNamespace, <targetNamespace> is the name of the targetNamespace,
and <location> is the location of the schema. This function writes
lines in the lex file that look for the <prefix>:schemaLocation= part
of that and return SCHEMALOCATION when it is found. The rest of that
specification will be recognized as a TERMINALSTRING, and the string
will be checked in the YACC file to be sure the first part of it is
the name of the targetNamespace. SCHEMALOCATION does not need to have
a string value; it just has an integer token value.

Otherwise (i.e. the schema does not have a target namespace):
The schema location specification of an XML instance file must be:
    xsi:noNamespaceSchemaLocation="<location>"
where <location> is the location of the schema. This function writes
lines in the lex file that look for the xsi:noNamespaceSchemaLocation=
part of that and return SCHEMALOCATION when it is found. The rest of that
specification will be recognized as a TERMINALSTRING. SCHEMALOCATION does
not need to have a string value; it just has an integer token value.

*/

void generator::printLexStart() /* NO ARGUMENTS  */
{
  fprintf(lexFile,
"%%{\n"
"\n"
"/*\n"
"\n"
"This ignores white space outside of meaningful strings of characters.\n"
"\n"
"*/\n"
"\n"
"#ifdef WIN32\n"
"#include <io.h>\n"
"#define strdup _strdup\n"
"#define fileno _fileno\n"
"#define isatty _isatty\n"
"#define YY_NO_UNISTD_H\n"
"#endif\n"
"#include <string.h>          // for strdup\n");
  // fprintf(lexFile, "#ifdef OWL\n");
  // fprintf(lexFile, "#include \"owl%c%sClasses.hh\"\n",
  // toupper(baseNameNoPath[0]), baseNameNoPath+1);
  // fprintf(lexFile, "#else\n");
  fprintf(lexFile, "#include \"%s%sClasses.hh\"\n", appIncludePrefix, baseNameNoPath);
  // fprintf(lexFile, "#endif\n");
  fprintf(lexFile, "#include \"%s%sYACC.hh\"    // for tokens, yylval, etc.\n",
	  appIncludePrefix, baseNameNoPath);
  fprintf(lexFile, "\n");
  fprintf(lexFile, "#ifndef NO_ECHO\n");
  fprintf(lexFile, "#define ECHO_IT 1\n");
  fprintf(lexFile, "#else\n");
  fprintf(lexFile, "#define ECHO_IT 0\n");
  fprintf(lexFile, "#endif\n");
  fprintf(lexFile, "#define ECH if (ECHO_IT) ECHO\n");
  if (stringInput)
    {
      fprintf(lexFile, "#undef YY_INPUT\n");
      fprintf(lexFile, "#define YY_INPUT(b, r, ms) (r = set_%sinput(b, ms))\n",
	      yyprefix);
      fprintf(lexFile, "\n");
      fprintf(lexFile, "extern int %sReadData;\n", yyprefix);
      fprintf(lexFile, "extern int %sReadDataList;\n", yyprefix);
      fprintf(lexFile, "char * %sStringInputPointer;\n", yyprefix);
      fprintf(lexFile, "char * %sStringInputEnd;\n", yyprefix);
      fprintf(lexFile, "\n");
      fprintf(lexFile, "int set_%sinput(char * buffer, int maxSize)\n",
	      yyprefix);
      fprintf(lexFile, "{\n");
      fprintf(lexFile, "  int n;\n");
      fprintf(lexFile, "\n");
      fprintf(lexFile,
	      "  n = (maxSize < (%sStringInputEnd - %sStringInputPointer) ?\n",
	      yyprefix, yyprefix);
      fprintf(lexFile,
	      "       maxSize : (%sStringInputEnd - %sStringInputPointer));\n",
	      yyprefix, yyprefix);
      fprintf(lexFile, "  if (n > 0)\n");
      fprintf(lexFile, "    {\n");
      fprintf(lexFile, "      memcpy(buffer, %sStringInputPointer, n);\n",
	      yyprefix);
      fprintf(lexFile, "      %sStringInputPointer += n;\n", yyprefix);
      fprintf(lexFile, "    }\n");
      fprintf(lexFile, "  return n;\n");
      fprintf(lexFile, "}\n");
    }
  else
    {
      fprintf(lexFile, "\n");
      fprintf(lexFile, "extern int %sReadData;\n", yyprefix);
      fprintf(lexFile, "extern int %sReadDataList;\n", yyprefix);
    }
  fprintf(lexFile, "\n");
  fprintf(lexFile, "%%}\n");
  fprintf(lexFile, "\n");
  fprintf(lexFile, "W [ \\t\\n\\r]*\n");
  fprintf(lexFile, "%%x COMMENT\n");
  fprintf(lexFile, "%%x DATA\n");
  fprintf(lexFile, "%%x DATALIST\n");
  fprintf(lexFile, "\n");
  fprintf(lexFile, "%%%%\n");
  fprintf(lexFile, "\n");
  fprintf(lexFile, "  if (%sReadData)\n", yyprefix);
  fprintf(lexFile, "    {\n");
  fprintf(lexFile, "      BEGIN(DATA);\n");
  fprintf(lexFile, "      %sReadData = 0;\n", yyprefix);
  fprintf(lexFile, "    }\n");
  fprintf(lexFile, "  else if (%sReadDataList)\n", yyprefix);
  fprintf(lexFile,
"    {\n"
"      BEGIN(DATALIST);\n"
"    }\n"
"\n"
"{W}\"<!--\"               { ECH; BEGIN(COMMENT); /* delete comment start */}\n"
"<COMMENT>.              { ECH;  /* delete comment middle */ }\n"
"<COMMENT>\\n             { ECH;  /* delete comment middle */ }\n"
"<COMMENT>\"-->\"          { ECH; BEGIN(INITIAL); /* delete comment end */ }\n"
"\n"
"<DATA>[^<]*             { ECH; BEGIN(INITIAL);\n"
"                          %slval.sVal = strdup(%stext);\n",
	  yyprefix, yyprefix);
  fprintf(lexFile,
"                          return DATASTRING;\n"
"                        }\n"
"\n"
"<DATALIST>[^< \\t]*      { ECH;\n"
"                          %slval.sVal = strdup(%stext);\n",
	  yyprefix, yyprefix);
  fprintf(lexFile,
"                          return DATASTRING;\n"
"                        }\n"
"<DATALIST>{W}           { ECH;}\n"
"<DATALIST>\"<\"           { %sReadDataList = 0;\n", yyprefix);
  fprintf(lexFile,
"                          unput('<');\n"
"			  BEGIN(INITIAL);}\n"
"\n"
"\"encoding\"{W}\"=\"              {ECH; return ENCODING;}\n"
"\"?>\"                          {ECH; return ENDVERSION;}\n"
"\"<?\"                          {ECH; return STARTVERSION;}\n");
  if (target)
    {
      fprintf(lexFile,
"\"xsi:schemaLocation\"{W}\"=\"    {ECH; return SCHEMALOCATION;}\n");
    }
  else
    {
      fprintf(lexFile,
"\"xsi:noNamespaceSchemaLocation\"{W}\"=\" {ECH; return SCHEMALOCATION;}\n");
    }
  fprintf(lexFile,
"\"xml\"[ \\t]+\"version\"{W}\"=\"    {ECH; return XMLVERSION;}\n"
"\n");

}

/********************************************************************/

/* generator::printNotTop

Returned Value: none

Called By:
  generator::printSelf

This prints the C++ header and code files. It is called when there is
no top-level element in the XML schema (which is normally if the schema
is intended to be included in other schemas).

*/

void generator::printNotTop()
{
  buildElementInfo();
  printCppHeader();
  printCppCode();
}

/********************************************************************/

/* generator::printParser

Returned Value: none

Called By:  printTop

This prints the parser file.

The parser file name is baseNameWithPath with Parser.cc appended.

*/

void generator::printParser() /* NO ARGUMENTS */
{
  char nameBuf[NAMESIZE];
  FILE * parserFile;

  sprintf(nameBuf, "%sParser.cc", baseNameWithPath);
  parserFile = fopen(nameBuf, "w");
  fprintf(parserFile, "#include <stdio.h>   // fprintf\n");
  fprintf(parserFile, "#include <string.h>  // strlen\n");
  fprintf(parserFile, "#include <stdlib.h>  // exit\n");
  fprintf(parserFile, "#include \"%s%sClasses.hh\"\n", appIncludePrefix, baseNameNoPath);
  if (stringInput)
    fprintf(parserFile, "#define MAX_SIZE 10000000\n");
  fprintf(parserFile, "\n");
  fprintf(parserFile, "extern %sFile * %sTree;\n",
	  classBaseName, top->newName);
  fprintf(parserFile, "extern FILE * %sin;\n", yyprefix);
  fprintf(parserFile, "extern int %sparse();\n", yyprefix);
  fprintf(parserFile, "extern void %slex_destroy();\n", yyprefix);
  if (stringInput)
    {
      fprintf(parserFile, "extern char * %sStringInputPointer;\n", yyprefix);
      fprintf(parserFile, "extern char * %sStringInputEnd;\n", yyprefix);
    }
  fprintf(parserFile,
"\n"
"int main(       /* ARGUMENTS                                      */\n"
" int argc,      /* one more than the number of command arguments  */\n"
" char * argv[]) /* array of executable name and command arguments */\n"
"{\n"
"  std::string outFileName;\n"
"  FILE * inFile;\n"
"  FILE * outFile;\n");
  if (stringInput)
    {
      fprintf(parserFile, "  char * inputString;\n");
      fprintf(parserFile, "  int n;\n");
      fprintf(parserFile, "  int stringSize;\n");
    }
  fprintf(parserFile,
"\n"
"  if (argc != 2)\n"
"    {\n"
"      fprintf(stderr, \"Usage: %%s <data file name>\\n\", argv[0]);\n"
"      exit(1);\n"
"    }\n");
  if (stringInput)
    {
      fprintf(parserFile,
"  for (stringSize = 10000; stringSize <= MAX_SIZE; stringSize *= 10)\n"
"    {\n"
"      inputString = new char[stringSize + 1];\n"
"      inFile = fopen(argv[1], \"r\");\n"
"      if (inFile == 0)\n"
"	{\n"
"	  fprintf(stderr, \"unable to open file %%s for reading\\n\", argv[1]);\n"
"	  exit(1);\n"
"	}\n"
"      for (n = 0; (((inputString[n] = getc(inFile)) != EOF) &&\n"
"		   (n < stringSize)); n++);\n"
"      fclose(inFile);\n"
"      if (n < stringSize)\n"
"	break;\n"
"      else\n"
"	delete [] inputString;\n"
"    }\n"
"  if (stringSize > MAX_SIZE)\n"
"    {\n"
"      fprintf(stderr,\n"
"	      \"input file is too large (more than %%d bytes), exiting\\n\",\n"
"	      MAX_SIZE);\n"
"      return 1;\n"
"    }\n"
"  inputString[n] = 0;\n"
"  %sStringInputPointer = inputString;\n"
"  %sStringInputEnd = (inputString + n);\n", yyprefix, yyprefix);
    }
  else
    {
      fprintf(parserFile,
"  inFile = fopen(argv[1], \"r\");\n"
"  if (inFile == 0)\n"
"    {\n"
"      fprintf(stderr, \"unable to open file %%s for reading\\n\", argv[1]);\n"
"      exit(1);\n"
"    }\n");
      fprintf(parserFile, "  %sin = inFile;\n", yyprefix);
    }
  fprintf(parserFile, "  %sparse();\n", yyprefix);
  if (!stringInput)
    fprintf(parserFile, "  fclose(inFile);\n");
  fprintf(parserFile, "  outFileName = argv[1];\n");
  fprintf(parserFile, "  outFileName.append(\"echo\");\n");
  fprintf(parserFile, "  outFile = fopen(outFileName.c_str(), \"w\");\n");
  fprintf(parserFile, "  %sTree->PRINTSELF;\n", top->newName);
  fprintf(parserFile, "  fclose(outFile);\n");
  fprintf(parserFile, "  delete %sTree;\n", top->newName);
  if (stringInput)
    fprintf(parserFile, "  delete [] inputString;\n");
  fprintf(parserFile, "  %slex_destroy();\n", yyprefix);
  fprintf(parserFile, "  return 0;\n");
  fprintf(parserFile, "}\n");
  fclose(parserFile);
  parserFile = 0;
}

/********************************************************************/

/* generator::printSelf

Returned Value: none

Called By:
  main
  generator::printSelf (recursively)

When called from main, this prints everything the generator is going
to print. First it calls itself for all of its subordinate generators.
Then it prints files with the same base name as itself. The
subordinate generators need to print first in order that the classes
they contain will be marked as having been printed and will,
therefore, not be printed twice.

*/

void generator::printSelf()
{
  std::list<generator *>::iterator iter;
  
  for (iter = subordinates.begin(); iter != subordinates.end(); iter++)
    { // OK if list empty
      (*iter)->printSelf();
    }
  if (top)
    {
      strcpy(classBaseName, top->newName);
      classBaseName[0] = toupper(classBaseName[0]);
      printTop();
    }
  else
    printNotTop();
}

/********************************************************************/

/* generator::printStarLine

Returned Value: none

Called By:
  generator::printCppCodeEnd
  generator::printCppCodeSchemaClasses
  generator::printCppCodeStart
  generator::printCppHeader
  generator::printCppHeaderEnd
  generator::printCppHeaderSchemaClassesComplex
  generator::printCppHeaderSchemaClassesSimple
  generator::printCppHeaderStart
  generator::printYaccEnd

This prints a (comment) line of stars, possibly preceded by a blank line
and possibly followed by a blank line.

*/

void generator::printStarLine( /* ARGUMENTS                                  */
 FILE * file,                  /* file to print in                           */
 bool before,                  /* if true, print blank line before star line */
 bool after)                   /* if true, print blank line after star line  */
{
  if (before)
    fprintf(file, "\n");
  fprintf(file,
"/*********************************************************************/\n");
  if (after)
    fprintf(file, "\n");
}

/********************************************************************/

/* generator::printTop

Returned Value: none

Called By: printSelf

This prints all five files. It is called when there is a top-level
element in the XML schema.

The buildDescendants and buildExtensions functions are called where they are:
 - after printCppCode
 - with buildDescendants before buildExtensions
 - with buildExtensions before printYacc and printLex
because
(1) buildDescendants must be called before buildExtensions
(2) buildDescendants cannot be called until printCppCodePrintLines has
    entered all the kids of each XmlComplexType
(3) buildExtensions cannot be called until elementInfos has been
    built, which happens when buildElementInfo executes
(4) buildDescendants must be called before printYacc

Also, printYacc must be called before printLex because the wholeFlag may
be set to true when printYacc runs, and printLex uses the wholeFlag.

*/

void generator::printTop()
{
  buildElementInfo();
  printCppHeader();
  printCppCode();
  buildDescendants();
  buildExtensions();
  abbreviateNames();
  printYacc();
  printLex();
  printParser();
}

/********************************************************************/

/* generator::printYacc

Returned Value: none

Called By:  printTop

This prints the YACC file.

The YACC file name is baseNameWithPath with .y appended.

*/

void generator::printYacc() /* NO ARGUMENTS */
{
  char nameBuf[NAMESIZE];
  std::list<namePair *> endRules;

  sprintf(nameBuf, "%s.y", baseNameWithPath);
  yaccFile = fopen(nameBuf, "w");
  if (yaccFile == 0)
    {
      fprintf(stderr, "could not open file %s for writing\n", nameBuf);
      exit(1);
    }
  buildYaccRulesEnd(&endRules);
  printYaccStart();
  printYaccUnion();
  printYaccTypes();
  printYaccTokens();
  printYaccRules(&endRules);
  printYaccEnd();
  fclose(yaccFile);
  yaccFile = 0;
}

/********************************************************************/

/* generator::printYaccAttributeTokens

Returned Value: none

Called By: printYaccTokens

This prints in the YACC file token declarations for XML attribute names.

*/

void generator::printYaccAttributeTokens() /* NO ARGUMENTS  */
{
  std::list<char *>::iterator iter;
  char buffer[NAMESIZE];

  fprintf(yaccFile, "\n");
  for (iter = attributeInfo->begin(); iter != attributeInfo->end(); iter++)
    {
      allCaps(*iter, buffer);
      fprintf(yaccFile, "%%token <iVal> %s\n", buffer);
    }
}

/********************************************************************/

/* generator::printYaccElementTokens

Returned Value: none

Called By: generator::printYaccTokens

This prints in the YACC file START and END token declarations for elements.
It is possible for the same element name to be used for more than
one elementInfo; only one elementInfo with any given element name
is used for printing in the YACC tokens.

This skips mock elements since START and END tokens are not used for them.

*/

void generator::printYaccElementTokens() /* NO ARGUMENTS  */
{
  std::list<elementInfo *>::iterator iter;
  char buffer[NAMESIZE];
  char * currentName;

  currentName = dummyName;
  for (iter = elementInfos->begin(); iter != elementInfos->end(); iter++)
    {
      if (strcmp((*iter)->element->newName, currentName) == 0)
	continue;
      if ((*iter)->element->mock)
	continue;
      currentName = (*iter)->element->newName;
      allCaps(currentName, buffer);
      fprintf(yaccFile, "%%token <iVal> %sEND\n", buffer);
      fprintf(yaccFile, "%%token <iVal> %sSTART\n", buffer);
    }
}

/********************************************************************/

/* generator::printYaccEnd

Returned Value: none

Called By: generator::printYacc

This prints the end of the YACC file.

*/

void generator::printYaccEnd() /* NO ARGUMENTS  */
{
  printStarLine(yaccFile, true, true);
  fprintf(yaccFile, "/* %serror\n", yyprefix);
  fprintf(yaccFile, "\n");
  fprintf(yaccFile, "Returned Value: int (0)\n");
  fprintf(yaccFile, "\n");
  fprintf(yaccFile, "Called By: %sparse\n", yyprefix);
  fprintf(yaccFile, "\n");
  fprintf(yaccFile, "This prints whatever string the parser provides.\n");
  fprintf(yaccFile, "\n");
  fprintf(yaccFile, "*/\n");
  fprintf(yaccFile, "\n");
  fprintf(yaccFile, "int %serror(      /* ARGUMENTS       */\n", yyprefix);
  fprintf(yaccFile, " const char * s)  /* string to print */\n");
  fprintf(yaccFile, "{\n");
  fprintf(yaccFile, "  fflush(stdout);\n");
  fprintf(yaccFile, "  fprintf(stderr, \"\\n%%s\\n\", s);\n");
  fprintf(yaccFile, "  exit(1);\n");
  fprintf(yaccFile, "  return 0;\n");
  fprintf(yaccFile, "}\n");
  printStarLine(yaccFile, true, false);
}

/********************************************************************/

/* generator::printYaccExtensionTokens

Returned Value: none

Called By: generator::printYaccTokens

This prints in the YACC file the declarations of tokens for types that
are extensions of other types.

*/

void generator::printYaccExtensionTokens() /* NO ARGUMENTS  */
{
  XmlComplexType * complx;
  XmlComplexContent * cont;
  XmlComplexExtension * extend;
  std::list<XmlType *>::iterator iter;
  char buffer[NAMESIZE];
  
  fprintf(yaccFile, "\n");
  for (iter = classes->begin(); iter != classes->end(); iter++)
    {
      if ((complx = dynamic_cast<XmlComplexType *>(*iter)) &&
	  (cont = dynamic_cast<XmlComplexContent *>(complx->item)) &&
	  (extend = dynamic_cast<XmlComplexExtension *>(cont->item)))
	{
	  allCaps(complx->newName, buffer);
	  fprintf(yaccFile, "%%token <iVal> %sDECL\n", buffer);
	}
    }      
}

/********************************************************************/

/* generator::printYaccRuleDefinition

Returned Value: int
  This returns the index in text of the character after the definition
  part of the text.

Called By: generator::printYaccRulesEnd

This prints the definition part of a YACC rule. If the definition is
more than 65 characters long, it is printed on multiple lines.

The character in name2 that ends the definition is ASCII 13.

If any series of non-space characters in the definition part of the
text has more than 65 characters, that part of the definition will not
fit on a standard line, so this prints an error message and exits.

*/

int generator::printYaccRuleDefinition( /* ARGUMENTS                */
 char * text)                           /* rule from which to print */
{
  int length;
  int m;
  int start;

  for (length = 0; text[length] != 13; length++);
  text[length] = 0;
  for (start = 0; (start+65) < length; start = (m+1))
    {
      for (m = (start+65); ((m > start) && (text[m] != ' ')); m--);
      if (m == start)
	{
	  fprintf(stderr, "name too long to print YACC rule\n");
	  exit(1);
	}
      text[m] = 0;
      fprintf(yaccFile, "%s%s\n", ((start != 0) ? "\t  " : ""), (text+start));
    }
  fprintf(yaccFile, "%s%s\n", ((start != 0) ? "\t  " : ""), (text+start));
  return (length+1);
}

/********************************************************************/

/* generator::printYaccRules

Returned Value: none

Called By: printYacc

This prints the rules in the YACC file.

*/

void generator::printYaccRules(    /* ARGUMENTS                          */
 std::list<namePair *> * endRules) /* name pairs used for printing rules */
{
  printYaccRulesStart();
  printYaccRulesAttributeName();
  printYaccRulesEnd(endRules);
  fprintf(yaccFile, "\n");
  fprintf(yaccFile, "%%%%\n");  
}

/********************************************************************/

/* generator::printYaccRulesAttributeName

Returned Value: none

Called By: printYaccRules

This prints the rule in the YACC file that deals with attribute names.

*/

void generator::printYaccRulesAttributeName() /* NO ARGUMENTS  */
{
  std::list<char *>::iterator iter;
  char buffer[NAMESIZE];
  bool needBar;

  if (attributeInfo->size() == 0)
    return; // no attributes, so no need to define y_attributeName
  needBar = false;
  fprintf(yaccFile, "\n");
  fprintf(yaccFile, "y_attributeName :\n");
  for (iter = attributeInfo->begin(); iter != attributeInfo->end(); iter++)
    {
      allCaps(*iter, buffer);
      fprintf(yaccFile, "\t%s %s {$$ = strdup(\"%s\");}\n",
	      (needBar ? "|" : " "), buffer, *iter);
      needBar = true;
    }
  fprintf(yaccFile, "\t;\n");
}

/********************************************************************/

/* generator::printYaccRulesBasic

Returned Value: none

Called By: generator::printYaccRulesStart

This prints a YACC rule that reads an XML string.

*/

void generator::printYaccRulesBasic( /*  ARGUMENTS  */
 const char * name)                  /* name of class for basic type */
{
  fprintf(yaccFile,
"\n"
"y_%s :\n"
"	  DATASTRING\n"
"	  {$$ = new %s($1);\n"
"	   if ($$->bad)\n"
"	     %serror(\"bad %s\");\n"
"	   free($1);\n"
"	  }\n"
	  "	;\n", name, name, yyprefix, name);
}

/********************************************************************/

/* generator::printYaccRulesEnd

Returned Value: none

Called By: printYaccRules

This prints the end of the rules section of the YACC file. The rules
to be printed are represented by namePairs which have been stored in
the endRules std::list. A namePair consists of two strings called name1
and name2.

The endRules are arranged alphabetically by name1. The name1 of each
namePair in the endRules is a production name. The name2 of each
namePair consists of one line of definition followed by an action (a
section of C++ code), which may have several lines. The definition and
action are separated by an ASCII 13 character.

The definition line in name2 may be too long to fit on a normal line
(75 characters). If that happens, the definition will be split here
into two or more lines of the YACC file. The lines of the action are
expected to fit (have 75 or fewer characters); that is not checked.

Two or more namePairs in the endRules may have the same production
name (name1). All namePairs with the same production name will be
combined here into a single rule. The production name will be printed
only once, and the name2s will be printed separated by "or" bars (|).

The definition part of the name2 is expected to have no space at the
beginning of the line.  The action part of the name2 is expected to
have a tab and two or more spaces at the beginning of each line.

Since this prints one production from all the namePairs that have the
same name1, it needs to look ahead to determine whether to print
a semicolon that ends the production. It uses currentName to keep
the last name for comparison with the next name.

*/

void generator::printYaccRulesEnd( /* ARGUMENTS           */
 std::list<namePair *> * endRules) /* YACC rules to print */
{
  std::list<namePair *>::iterator iter;
  char * currentName;
  int n;

  if (endRules->size() == 0)
    return;
  iter = endRules->begin();
  fprintf(yaccFile, "\n");
  fprintf(yaccFile, "y_%s :\n", (*iter)->name1);
  fprintf(yaccFile, "\t  ");
  while (1)
    {
      n = printYaccRuleDefinition((*iter)->name2);
      fprintf(yaccFile, "%s", ((*iter)->name2 + n));
      currentName = (*iter)->name1;
      iter++;
      if (iter == endRules->end())
	{
	  fprintf(yaccFile, "\t;\n");
	  break;
	}
      else if (strcmp(currentName, (*iter)->name1))
	{
	  fprintf(yaccFile, "\t;\n");
	  fprintf(yaccFile, "\n");
	  fprintf(yaccFile, "y_%s :\n", (*iter)->name1);
	  fprintf(yaccFile, "\t  ");
	}
      else
	fprintf(yaccFile, "\t| ");
    }
}

/********************************************************************/

/* generator::printYaccRulesStart

Returned Value: none

Called By: printYaccRules

This prints the rules in the YACC file for:
y_xxxFile
y_XmlHeaderForxxx
y_AttributePair (if there are attributes)
y_ListAttributePair (if there are attributes)
y_SchemaLocation
y_XmlVersion

In the SchemaLocation, the TERMINALSTRING has two parts. The first
part should be the name of the targetNamespace. The second part is
the location of the schema for that targetNamespace. For example:
"urn:Palletizing Order.xsd". This writes code that checks that the
first part is correct.

FIX - The rule for y_xxxFile may need alternatives (like the ones generated
in buildYaccRulesEnd). For example if complx has subtypes.

*/

void generator::printYaccRulesStart() /* NO ARGUMENTS  */
{
  XmlComplexType * complx;
  char buffer[NAMESIZE];

  complx = findComplexClass(top->newTyp);
  if (complx == 0)
    {
      fprintf(stderr, "top level class of %s must be complex type\n",
	      top->typ);
      exit(1);
    }
  fprintf(yaccFile, "\n");
  fprintf(yaccFile, "y_%sFile :\n", classBaseName);
  allCaps(classBaseName, buffer);
  fprintf(yaccFile, "\t  y_XmlVersion %sSTART y_XmlHeaderFor%s\n"
                    "\t  y_%s %sEND\n",
	  buffer, classBaseName, complx->newName, buffer);
  fprintf(yaccFile, "\t  {$$ = new %sFile($1, $3, $4);\n", classBaseName);
  fprintf(yaccFile, "\t   %sTree = $$;\n", top->newName);
  fprintf(yaccFile, "\t   if (XmlIDREF::idMissing())\n");
  fprintf(yaccFile, "\t     yyerror(\"xs:ID missing for xs:IDREF\");\n");
  fprintf(yaccFile, "\t  }\n");
  fprintf(yaccFile, "\t;\n");
  fprintf(yaccFile, "\n");
  fprintf(yaccFile, "y_XmlHeaderFor%s:\n", classBaseName);
  if (target)
    {
      fprintf(yaccFile, "\t  XMLNSTARGET XMLNSPREFIX y_SchemaLocation\n");
      fprintf(yaccFile, "\t  {$$ = new XmlHeaderFor%s($3);}\n", classBaseName);
    }
  else
    {
      fprintf(yaccFile, "\t  XMLNSPREFIX y_SchemaLocation\n");
      fprintf(yaccFile, "\t  {$$ = new XmlHeaderFor%s($2);}\n", classBaseName);
    }
  fprintf(yaccFile, "\t;\n");
  if (attributeInfo->size())
    {
      fprintf(yaccFile,
"\n"
"y_AttributePair :\n"
"	  y_attributeName TERMINALSTRING\n"
"	  {$$ = new AttributePair($1, $2);\n"
"	   free($1);\n"
"	   free($2);\n"
"	  }\n"
"	;\n"
"\n"
"y_ListAttributePair :\n"
"	  y_AttributePair\n"
"	  {$$ = new std::list<AttributePair *>;\n"
"	   $$->push_back($1);\n"
"	  }\n"
"	| y_ListAttributePair y_AttributePair\n"
"	  {$$ = $1;\n"
"	   $$->push_back($2);\n"
"	  }\n"
"	;\n");
    }
  if (target)
    {
      fprintf(yaccFile,
"\n"
"y_SchemaLocation :\n"
"	  SCHEMALOCATION TERMINALSTRING\n"
	  "\t  {$$ = new SchemaLocation(\"xsi\", $2, true);\n");
      fprintf(yaccFile,
	      "\t   if (strncmp(\"%s \", $2, %d))\n",
	      target, (int)(1+strlen(target)));
      fprintf(yaccFile,
"	     {\n"
"	       fprintf(stderr,\n"
"	          \"wrong targetNamespace in schema location %%s\\n\", $2);\n"
"	       exit(1);\n"
"	     }\n");
    }
  else
    {
      fprintf(yaccFile,
"\n"
"y_SchemaLocation :\n"
"	  SCHEMALOCATION TERMINALSTRING\n"
	  "	  {$$ = new SchemaLocation(\"xsi\", $2, false);\n");
    }
  fprintf(yaccFile,
"	   free($2);\n"
"	  }\n"
"	;\n");

  if (hasAnyURI)
    printYaccRulesBasic("XmlAnyURI");
  if (hasBoolean)
    printYaccRulesBasic("XmlBoolean");
  if (hasDate)
    printYaccRulesBasic("XmlDate");
  if (hasDateTime)
    printYaccRulesBasic("XmlDateTime");
  if (hasDecimal)
    printYaccRulesBasic("XmlDecimal");
  if (hasDouble)
    printYaccRulesBasic("XmlDouble");
  if (hasFloat)
    printYaccRulesBasic("XmlFloat");
  if (hasID)
    printYaccRulesBasic("XmlID");
  if (hasIDREF)
    printYaccRulesBasic("XmlIDREF");
  if (hasInt)
    printYaccRulesBasic("XmlInt");
  if (hasInteger)
    printYaccRulesBasic("XmlInteger");
  if (hasLong)
    printYaccRulesBasic("XmlLong");
  if (hasNMTOKEN)
    printYaccRulesBasic("XmlNMTOKEN");
  if (hasNonNegativeInteger)
    printYaccRulesBasic("XmlNonNegativeInteger");
  if (hasPositiveInteger)
    printYaccRulesBasic("XmlPositiveInteger");
  if (hasString)
    printYaccRulesBasic("XmlString");
  if (hasToken)
    printYaccRulesBasic("XmlToken");
  if (hasUnsignedInt)
    printYaccRulesBasic("XmlUnsignedInt");
  if (hasUnsignedLong)
    printYaccRulesBasic("XmlUnsignedLong");
  printYaccRulesXmlVersion();
}

/********************************************************************/

/* generator::printYaccRulesXmlVersion

Returned Value: none

Called By: generator::printYaccRulesStart

This prints a YACC rule that reads the XML version and associated stuff.

*/

void generator::printYaccRulesXmlVersion() /* NO ARGUMENTS  */
{
  fprintf(yaccFile,
"\n"
"y_XmlVersion :\n"
"	  STARTVERSION XMLVERSION TERMINALSTRING ENDVERSION\n"
"	  {$$ = new XmlVersion(false);\n"
"	   if (strcmp($3, \"1.0\"))\n"
"	     %serror(\"version number must be 1.0\");\n"
"	   free($3);\n"
"	  }\n"
"	| STARTVERSION XMLVERSION TERMINALSTRING\n"
"	  ENCODING TERMINALSTRING ENDVERSION\n"
"	  {$$ = new XmlVersion(true);\n"
"	   if (strcmp($3, \"1.0\"))\n"
"	     %serror(\"version number must be 1.0\");\n"
"	   else if ((strcmp($5, \"UTF-8\")) && (strcmp($5, \"utf-8\")))\n"
"	     %serror(\"encoding must be UTF-8\");\n"
"	   free($3);\n"
"	   free($5);\n"
"	  }\n"
"	;\n", yyprefix, yyprefix, yyprefix);
}

/********************************************************************/

/* generator::printYaccStart

Returned Value: none

Called By: generator::printYacc

This prints the beginning of the YACC file.

*/

void generator::printYaccStart() /* NO ARGUMENTS  */
{
  fprintf(yaccFile, "%%{\n");
  fprintf(yaccFile, "\n");
  fprintf(yaccFile, "#include <stdio.h>             // for stderr\n");
  fprintf(yaccFile, "#include <string.h>            // for strcat\n");
  fprintf(yaccFile, "#include <stdlib.h>            // for malloc, free\n");
  // fprintf(yaccFile, "#ifdef OWL\n");
  // fprintf(yaccFile, "#include \"owl%c%sClasses.hh\"\n",
  // toupper(baseNameNoPath[0]), baseNameNoPath+1);
  // fprintf(yaccFile, "#else\n");
  fprintf(yaccFile, "#include \"%s%sClasses.hh\"\n", appIncludePrefix, baseNameNoPath);
  // fprintf(yaccFile, "#endif\n");
  fprintf(yaccFile, "\n");
  fprintf(yaccFile, "#define YYERROR_VERBOSE\n");
  fprintf(yaccFile, "#define YYDEBUG 1\n");
  fprintf(yaccFile, "\n");
  fprintf(yaccFile, "%sFile * %sTree; // the parse tree\n",
	  classBaseName, top->newName);
  fprintf(yaccFile, "\n");
  fprintf(yaccFile, "extern int %slex();\n", yyprefix);
  fprintf(yaccFile, "int %sReadData = 0;\n", yyprefix);
  fprintf(yaccFile, "int %sReadDataList = 0;\n", yyprefix);
  fprintf(yaccFile, "\n");
  fprintf(yaccFile, "int %serror(const char * s);\n", yyprefix);
  fprintf(yaccFile, "\n");
  fprintf(yaccFile, "%%}\n");
}

/********************************************************************/

/* generator::printYaccTokens

Returned Value: none

Called By: generator::printYacc

This prints the token declarations in the YACC file.

*/

void generator::printYaccTokens() /* NO ARGUMENTS  */
{
  fprintf(yaccFile,
"\n"
"%%token <iVal> BAD\n"
"%%token <sVal> DATASTRING\n"
"%%token <iVal> ENCODING\n"
"%%token <iVal> ENDITEM\n"
"%%token <iVal> ENDVERSION\n");
  if (wholeFlag)
    fprintf(yaccFile,
"%%token <iVal> ENDWHOLEITEM\n");
  fprintf(yaccFile,
"%%token <sVal> SCHEMALOCATION\n"
"%%token <iVal> STARTVERSION\n"
"%%token <sVal> TERMINALSTRING\n"
"%%token <iVal> XMLNSPREFIX\n"
"%%token <iVal> XMLNSTARGET\n"
"%%token <iVal> XMLVERSION\n"
"\n");
  printYaccElementTokens();
  printYaccAttributeTokens();
  printYaccExtensionTokens();
  fprintf(yaccFile, "\n");
  fprintf(yaccFile, "%%%%\n");
}

/********************************************************************/

/* generator::printYaccTypes

Returned Value: none

Called By: generator::printYacc

This prints the type declarations in the YACC file.
The first five are hard-coded, and the sixth is semi-hard coded.

*/

void generator::printYaccTypes() /* NO ARGUMENTS  */
{
  int numChars;

  fprintf(yaccFile, "\n");
  if (attributeInfo->size())
    {
      fprintf(yaccFile,
"%%type <sVal>                          y_attributeName\n"
"%%type <AttributePairVal>              y_AttributePair\n"
"%%type <ListAttributePairVal>          y_ListAttributePair\n");
    }
  fprintf(yaccFile,
	  "%%type <SchemaLocationVal>             y_SchemaLocation\n");
  numChars = fprintf(yaccFile, "%%type <XmlHeaderFor%sVal> ", classBaseName);
  for (; numChars < 38; numChars++)
    fprintf(yaccFile, " ");
  fprintf(yaccFile, "y_XmlHeaderFor%s\n", classBaseName);
  fprintf(yaccFile, "%%type <XmlVersionVal>                 y_XmlVersion\n");
  numChars = fprintf(yaccFile, "%%type <%sFileVal> ", classBaseName);
  for (; numChars < 38; numChars++)
    fprintf(yaccFile, " ");
  fprintf(yaccFile, "y_%sFile\n", classBaseName);
  if (hasAnyURI)
    fprintf(yaccFile,
	    "%%type <XmlAnyURIVal>                  y_XmlAnyURI\n");
  if (hasBoolean)
    fprintf(yaccFile,
	    "%%type <XmlBooleanVal>                 y_XmlBoolean\n");
  if (hasDate)
    fprintf(yaccFile,
	    "%%type <XmlDateVal>                    y_XmlDate\n");
  if (hasDateTime)
    fprintf(yaccFile,
	    "%%type <XmlDateTimeVal>                y_XmlDateTime\n");
  if (hasDecimal)
    fprintf(yaccFile,
	    "%%type <XmlDecimalVal>                 y_XmlDecimal\n");
  if (hasDouble)
    fprintf(yaccFile,
	    "%%type <XmlDoubleVal>                  y_XmlDouble\n");
  if (hasFloat)
    fprintf(yaccFile,
	    "%%type <XmlFloatVal>                   y_XmlFloat\n");
  if (hasID)
    fprintf(yaccFile,
	    "%%type <XmlIDVal>                      y_XmlID\n");
  if (hasIDREF)
    fprintf(yaccFile,
	    "%%type <XmlIDREFVal>                   y_XmlIDREF\n");
  if (hasInt)
    fprintf(yaccFile,
	    "%%type <XmlIntVal>                     y_XmlInt\n");
  if (hasInteger)
    fprintf(yaccFile,
	    "%%type <XmlIntegerVal>                 y_XmlInteger\n");
  if (hasLong)
    fprintf(yaccFile,
	    "%%type <XmlLongVal>                    y_XmlLong\n");
  if (hasNMTOKEN)
    fprintf(yaccFile,
	    "%%type <XmlNMTOKENVal>                 y_XmlNMTOKEN\n");
  if (hasNonNegativeInteger)
    fprintf(yaccFile,
	    "%%type <XmlNonNegativeIntegerVal>      y_XmlNonNegativeInteger\n");
  if (hasPositiveInteger)
    fprintf(yaccFile,
	    "%%type <XmlPositiveIntegerVal>         y_XmlPositiveInteger\n");
  if (hasString)
    fprintf(yaccFile,
	    "%%type <XmlStringVal>                  y_XmlString\n");
  if (hasToken)
    fprintf(yaccFile,
	    "%%type <XmlTokenVal>                   y_XmlToken\n");
  if (hasUnsignedInt)
    fprintf(yaccFile,
	    "%%type <XmlUnsignedIntVal>             y_XmlUnsignedInt\n");
  if (hasUnsignedLong)
    fprintf(yaccFile,
	    "%%type <XmlUnsignedLongVal>            y_XmlUnsignedLong\n");
  printYaccTypeElements();
}

/********************************************************************/

/* generator::printYaccTypeElements

Returned Value: none

Called By: generator::printYaccTypes

This prints the rest of the type declarations in the YACC file.
The first few are printed by printYaccTypes.

*/

void generator::printYaccTypeElements() /* NO ARGUMENTS  */
{
  std::list<namePair *>::iterator iter;
  std::list<namePair *> typePairs;
  int numChars;

  buildYaccTypeElementPairs(&typePairs);
  fprintf(yaccFile, "\n");
  for (iter = typePairs.begin(); iter != typePairs.end(); iter++)
    {
      numChars = fprintf(yaccFile, "%%type <%sVal> ", (*iter)->name2);
      for ( ; numChars < 38; numChars++)
	fprintf(yaccFile, " ");
      fprintf(yaccFile, "y_%s\n", (*iter)->name1);
    }
}

/********************************************************************/

/* generator::printYaccUnion

Returned Value: none

Called By: generator::printYacc

This prints the union declaration in the YACC file.
The first few are hard-coded.

*/

void generator::printYaccUnion() /* NO ARGUMENTS  */
{
  int numChars;
  std::list<namePair *> unionPairs;

  buildYaccUnionElementPairs(&unionPairs);
  fprintf(yaccFile, "\n");
  fprintf(yaccFile, "%%union {\n");
  if (attributeInfo->size())
    {
      fprintf(yaccFile,
	      "  AttributePair *                     AttributePairVal;\n"
	      "  std::list<AttributePair *> *        ListAttributePairVal;\n");
    }
  fprintf(yaccFile,
	  "  SchemaLocation *                    SchemaLocationVal;\n");
  numChars = fprintf(yaccFile, "  XmlHeaderFor%s * ", classBaseName);
  for ( ; numChars < 38; numChars++)
    fprintf(yaccFile, " ");
  fprintf(yaccFile, "XmlHeaderFor%sVal;\n", classBaseName);
  fprintf(yaccFile,
	  "  XmlVersion *                        XmlVersionVal;\n");
  fprintf(yaccFile,
	  "  int *                               iVal;\n");
  fprintf(yaccFile,
	  "  char *                              sVal;\n");
  if (hasAnyURI)
    fprintf(yaccFile,
	    "  XmlAnyURI *                         XmlAnyURIVal;\n");
  if (hasBoolean)
    fprintf(yaccFile,
	    "  XmlBoolean *                        XmlBooleanVal;\n");
  if (hasDate)
    fprintf(yaccFile,
	    "  XmlDate *                           XmlDateVal;\n");
  if (hasDateTime)
    fprintf(yaccFile,
	    "  XmlDateTime *                       XmlDateTimeVal;\n");
  if (hasDecimal)
    fprintf(yaccFile,
	    "  XmlDecimal *                        XmlDecimalVal;\n");
  if (hasDouble)
    fprintf(yaccFile,
	    "  XmlDouble *                         XmlDoubleVal;\n");
  if (hasFloat)
    fprintf(yaccFile,
	    "  XmlFloat *                          XmlFloatVal;\n");
  if (hasID)
    fprintf(yaccFile,
	    "  XmlID *                             XmlIDVal;\n");
  if (hasIDREF)
    fprintf(yaccFile,
	    "  XmlIDREF *                          XmlIDREFVal;\n");
  if (hasInt)
    fprintf(yaccFile,
	    "  XmlInt *                            XmlIntVal;\n");
  if (hasInteger)
    fprintf(yaccFile,
	    "  XmlInteger *                        XmlIntegerVal;\n");
  if (hasLong)
    fprintf(yaccFile,
	    "  XmlLong *                           XmlLongVal;\n");
  if (hasNMTOKEN)
    fprintf(yaccFile,
	    "  XmlNMTOKEN *                        XmlNMTOKENVal;\n");
  if (hasNonNegativeInteger)
    fprintf(yaccFile,
	    "  XmlNonNegativeInteger *             XmlNonNegativeIntegerVal;\n");
  if (hasPositiveInteger)
    fprintf(yaccFile,
	    "  XmlPositiveInteger *                XmlPositiveIntegerVal;\n");
  if (hasString)
    fprintf(yaccFile,
	    "  XmlString *                         XmlStringVal;\n");
  if (hasToken)
    fprintf(yaccFile,
	    "  XmlToken *                          XmlTokenVal;\n");
  if (hasUnsignedInt)
    fprintf(yaccFile,
	    "  XmlUnsignedInt *                    XmlUnsignedIntVal;\n");
  if (hasUnsignedLong)
    fprintf(yaccFile,
	    "  XmlUnsignedLong *                   XmlUnsignedLongVal;\n");
  fprintf(yaccFile, "\n");
  numChars = fprintf(yaccFile, "  %sFile * ", classBaseName);
  for ( ; numChars < 38; numChars++)
    fprintf(yaccFile, " ");
  fprintf(yaccFile, "%sFileVal;\n", classBaseName);
  printYaccUnionElements(&unionPairs);
  fprintf(yaccFile, "}\n");
}

/********************************************************************/

/* generator::printYaccUnionElements

Returned Value: none

Called By: generator::printYaccUnion

This prints in the YACC file the rest of the items in the union declaration.
The first few items and the closing brace are printed in printYaccUnion.

*/

void generator::printYaccUnionElements( /* ARGUMENTS                   */
 std::list<namePair *> * unionPairs)    /* list of pairs to print from */
{
  std::list<namePair *>::iterator iter;
  int numChars;

  fprintf(yaccFile, "\n");
  for (iter = unionPairs->begin(); iter != unionPairs->end(); iter++)
    {
      numChars = fprintf(yaccFile, "  %s *", (*iter)->name2);
      for ( ; numChars < 37; numChars++)
	fprintf(yaccFile, " ");
      fprintf(yaccFile, " %s;\n", (*iter)->name1);
    }
}

/********************************************************************/

/* generator::processIncludes

Returned Value: none

Called By:
  main
  generator::processIncludes (recursively)

This reads and processes (but does not print from) all directly or
indirectly included XML schemas of an XML schema. This function calls
itself recursively if necessary so it builds a tree of function calls
each of which copies the contents2 for any included schemas that were
not previously read into the contents2 for the including schema. The
end effect is that all the contents2 from all schemas included at any
level end up being included in the contents2 for the top level schema.

Since each schema file is read only once, if a schema file F is xs:included
by more than one other schema file (say schema files A, C, and D), the
contents2 for F will be part of the contents2 of only one of those other
schema files (whichever one is processed first - say A). The generators for
those other schemas (C and D) will not have access to the classes of F
through their own contents2s. However, when it is time to print code, those
other classes will need to get information about the classes of F. To deal
with this, the findComplexClass and findSimpleClass functions (which access
information about a named class) look in the allComplex and allSimple maps.
Every generator in the hierarchy of generators has pointers to these maps.
Using these maps is not an ideal solution because it may give a generator
access to information not included by the schema for the generator.

The two hierarchies below are an example. The one on the left
represents a hierarchy of xs:includes. The one on the right represents
a generator hierarchy that might result from those xs:includes.
Capital letters represent schema files on the left and generators on
the right. On the left, any given schema file may be named in several
xs:includes. On the right, there is only one generator per schema file.


         T                  T
        / \                / \
       /   \              /   \
      A     B            A     B
     / \   / \          / \     \
    F   C C   D        F   C     D
        | |   |
        F F   F

The includedSchemas std::list that belongs to the local generator has
the base names (without directory paths or the .xsd suffix) of all the
schema files included by the generator for the specific schema file to
which the current call to processIncludes belongs. This is needed to
construct the C++ includes that go in the header file for the schema.
For example, in the hierarchy above on the left, the includedSchema
std::list for B would consist of the names of C and D.

The global includeds std::list is a list of the base names of all the
schema files that have already been xs:included from anywhere in the
hierarchy of xs:includes. If a file already on the list is encountered
again, it is not processed. For example, if A is processed before B,
then C will also be processed before B, and C will be on the includeds
list when B is processed, so B will not call for C to be processed.

This method of dealing with xs:includes handles circular includes
(e.g. schema file A includes schema file B and schema file B includes
schema file A) as well as multiple includes of the same file.

The method of using includedSchemas and includeds has the weakness
that if two schema files are included that differ and are on different
directory paths but have the same file name, only one of them will be
processed, resulting in a failure of some sort later.

*/

void generator::processIncludes( /* ARGUMENTS                              */
 std::list<char *> * includeds)  /* list of names of included schema files */
{
  std::list<XmlSchemaContent1 *>::iterator iter1A;
  std::list<XmlSchemaContent1 *>::iterator iter1B;
  std::list<XmlSchemaContent2 *>::iterator iter2;
  std::list<char *>::iterator iter3;
  generator * gen;
  XmlInclude * incl;
  char bassNameWithPath[NAMESIZE];
  char bassNameNoPath[NAMESIZE];

  for (iter1A = contents1->begin(); iter1A != contents1->end(); iter1A++)
    {
      if ((incl = dynamic_cast<XmlInclude *>(*iter1A)))
	{
	  for (iter3 = includeds->begin(); iter3 != includeds->end(); iter3++)
	    {
	      checkName(incl->schemaLocation, bassNameWithPath, bassNameNoPath);
	      if (strcmp(*iter3, bassNameNoPath) == 0)
		break;
	    }
	  if (iter3 != includeds->end()) // already included
	    { // need to put name in includedSchemas
	      includedSchemas->push_back(strdup(bassNameNoPath));
	      continue;
	    }
	  gen = new generator;
	  gen->allComplex = allComplex;
	  gen->allSimple = allSimple;
	  gen->elementRefables = elementRefables;
          gen->attributeLonerRefables = attributeLonerRefables;
          gen->attributeGroupRefables = attributeGroupRefables;
	  gen->mockCount = mockCount;
	  gen->readSchema(incl->schemaLocation, false);
	  includeds->push_back(gen->baseNameNoPath);
	  includedSchemas->push_back(gen->baseNameNoPath);
	  gen->processIncludes(includeds);
	  gen->buildClassesIncluded();
	  gen->replaceSubstitutionGroups();
	  subordinates.push_back(gen); // add gen to subordinates
	  for (iter2 = gen->contents2->begin();
	       iter2 != gen->contents2->end(); iter2++)
	    { // add contents of included file to current contents
	      contents2->push_back(*iter2);
	    }
	}
    }
}

/********************************************************************/

/* generator::readOldHeader

Returned Value: none

Called By: main

This starts in the expectInclude readState and loops until an error or
the end of the file. Each time around the loop, it reads a line of the
old header file. It expects to go into the following readStates. If
there is an error, it exits.

1. expectInclude
   a. End of file is error.
   b. If the line starts with #include, go into the
      expectIncludeCommentBlank readState.

2. expectIncludeCommentBlank
   a. End of file is error.
   b. If the line starts with // (possibly preceded by white space), save
      it in moreIncludes and go into expectIncludeBlank readState.
   c. If the line starts with #include, ignore it and stay in the
      expectIncludeCommentBlank readState.
   d. If the line is blank, go into the expectClassEnd readState.
   e. Otherwise error.

3. expectIncludeBlank
   a. End of file is error.
   b. If the line is blank, go into the expectClassEnd readState.
   c. If the line starts with #include, save it in moreIncludes and stay
      in the expectIncludeBlank readState.
   d. Otherwise error.

4. expectClassEnd
   a. End of file is OK; return.
   b. If line starts with "class " and the name that follows is not
      followed by a semicolon (i.e. skip class declarations), copy that
      name into className and go into the expectCommentBrace readState.
   c. Otherwise, ignore the line and stay in the expectClassEnd readState.

5. expectCommentBrace
   a. End of file is error.
   b. If the line starts with // (possibly preceded by white space), set
      saveLines to a new std::list<char *> and put a copy of the line in it.
      Put the pair <className, saveLines> into the changeMap. Go into the
      expectChangeBrace readState;
   c. If the line starts with "};", go into the expectClassEnd readState.
   d. Otherwise, ignore the line and stay in the expectCommentBrace readState.

6. expectChangeBrace
   a. End of file is error.
   b. If the line starts with "};", go into the expectClassEnd readState.
   c. Otherwise, add a copy of the line to the end of the saveLines std::list
      and stay in the expectChangeBrace readState.

*/

void generator::readOldHeader( /* ARGUMENTS                  */
 char * headerName)            /* name of old header to read */
{
  FILE * oldHeader;
  static char buffer[NAMESIZE];
  static char className[NAMESIZE];

  static const int expectInclude = 1;
  static const int expectIncludeCommentBlank = 2;
  static const int expectIncludeBlank = 3;
  static const int expectClassEnd = 4;
  static const int expectCommentBrace = 5;
  static const int expectChangeBrace = 6;
  int readState;
  char * result;
  std::list<char *> * savedLines;
  int n;

  oldHeader = fopen(headerName, "r");
  if (oldHeader == 0)
    {
      fprintf(stderr, "could not open %s for reading -- exiting\n", headerName);
      exit(1);
    }
  readState = expectInclude;
  while (1)
    {
      result = fgets(buffer, NAMESIZE, oldHeader);
      if (readState == expectInclude)
	{
	  if (result == 0)
	    {
	      fprintf(stderr, "premature end of %s\n", headerName);
	      fprintf(stderr, "expecting #include -- exiting\n");
	      exit(1);
	    }
	  else if (strncmp(buffer, "#include", 8) == 0)
	    readState = expectIncludeCommentBlank;
	}
      else if (readState == expectIncludeCommentBlank)
	{
	  if (result == 0)
	    {
	      fprintf(stderr, "premature end of %s\n", headerName);
 	      fprintf(stderr,
		      "expecting #include or comment or blank -- exiting\n");
	      exit(1);
	    }
	  for (n = 0; ((buffer[n] == ' ') || (buffer[n] == '\t')); n++);
	  if ((buffer[n] == '/') && (buffer[n+1] == '/'))
	    {
	      moreIncludes->push_back(strdup(buffer));
	      readState = expectIncludeBlank;
	    }
	  else if (strncmp(buffer+n, "#include", 8) == 0);
	  else if ((buffer[n] == 10) || (buffer[n] == 13))
	    readState = expectClassEnd;
	  else
	    {
	      fprintf(stderr, "bad file line as follows\n");
	      fprintf(stderr, "%s", buffer);
 	      fprintf(stderr,
		      "expecting #include or comment or blank -- exiting\n");
	      exit(1);
	    }
	}
      else if (readState == expectIncludeBlank)
	{
	  if (result == 0)
	    {
	      fprintf(stderr, "premature end of %s\n", headerName);
	      fprintf(stderr,
		      "expecting #include or a blank line -- exiting\n");
	      exit(1);
	    }
	  for (n = 0; ((buffer[n] == ' ') || (buffer[n] == '\t')); n++);
	  if (strncmp(buffer+n, "#include", 8) == 0)
	    moreIncludes->push_back(strdup(buffer));
	  else if ((buffer[n] == 10) || (buffer[n] == 13))
	    readState = expectClassEnd;
	  else
	    {
	      fprintf(stderr, "bad file line as follows\n");
	      fprintf(stderr, "%s", buffer);
 	      fprintf(stderr,
		      "expecting #include or a blank line -- exiting\n");
	      exit(1);
	    } 
	}
      else if (readState == expectClassEnd)
	{
	  if (result == 0)
	    return;
	  else if (strncmp(buffer, "class ", 6) == 0)
	    {
	      sscanf(buffer+6, "%s", className);
	      if (className[strlen(className) - 1] != ';')
		readState = expectCommentBrace;
	    }
	}
      else if (readState == expectCommentBrace)
	{
	  if (result == 0)
	    {
	      fprintf(stderr, "premature end of %s\n", headerName);
	      fprintf(stderr,
		      "expecting a comment or close brace -- exiting\n");
	      exit(1);
	    }
	  for (n = 0; ((buffer[n] == ' ') || (buffer[n] == '\t')); n++);
	  if ((buffer[n] == '/') && (buffer[n+1] == '/'))
	    {
	      savedLines = new std::list<char *>;
	      savedLines->push_back(strdup(buffer));
	      changeMap->insert(make_pair(className, savedLines));
	      readState = expectChangeBrace;
	    }
	  else if ((buffer[n] == '}') && (buffer[n+1] == ';'))
	    readState = expectClassEnd;
	}
      else if (readState == expectChangeBrace)
	{
	  if (result == 0)
	    {
	      fprintf(stderr, "premature end of %s\n", headerName);
	      fprintf(stderr, "expecting a close brace -- exiting\n");
	      exit(1);
	    }
	  else if ((buffer[0] == '}') && (buffer[1] == ';'))
	    readState = expectClassEnd;
	  else
	    savedLines->push_back(strdup(buffer));
	}
    }
}

/********************************************************************/

/* generator::readSchema

Returned Value: none

Called By:
  main
  processIncludes

This reads the schema with the given fileName and sets the following
fields of the generator that is calling:
  baseNameWithPath
  baseNameNoPath
  contents1
  contents2
  target

When the caller is the generator in main, this also sets the static
class field XmlCppBase::wg3Prefix.

*/

void generator::readSchema( /* ARGUMENTS                                      */
 char * fileName,           /* name of schema to read, including path         */
 bool isTop)                /* true=caller main, false=caller processIncludes */
{
  char * savedPrefix;

  checkName(fileName, baseNameWithPath, baseNameNoPath);
  yyin = fopen(fileName, "r");
  if (yyin == 0)
    {
      fprintf(stderr, "unable to open file %s for reading\n", fileName);
      exit(1);
    }
  if (isTop) // XmlCppBase::wg3Prefix is reset by yyparse
    XmlCppBase::wg3Prefix = 0;
  else
    savedPrefix = XmlCppBase::wg3Prefix;
  yyparse();
  fclose(yyin);
  if (!isTop && strncmp(savedPrefix, XmlCppBase::wg3Prefix, NAMESIZE))
    {
      fprintf(stderr, 
	      "included files must use same wg3Prefix as including file\n");
      exit(1);
    }
  contents1 = xmlSchemaFile->schema->contents1;
  contents2 = xmlSchemaFile->schema->contents2;
  target = xmlSchemaFile->schema->header->targetNamespace;
  /*
  if (target == 0)
    {
      fprintf(stderr, "schema must have a targetNamespace\n");
      exit(1);
    }
  */
}

/********************************************************************/

/* generator::replaceSubstitutionGroups

Returned Value: none

Called By: main

This looks through the elementRefables. If an XmlElementRefableMaster
is the head of a substitution group (indicated by a non-zero size of
the substitutes list) and has not yet been processed (indicated by
mock being false), this changes the element so that its type is a
choice containing copies of the members of the substitution group and
sets its mock to true.

*/

void generator::replaceSubstitutionGroups()
{
  static std::map<std::string, XmlElementRefable *>::iterator iter;
  XmlChoice * choice;
  XmlComplexType * choiceType;
  XmlOtherContent * other;
  char name[NAMESIZE];
  XmlElementRefable * refable;

  for (iter = elementRefables->begin();
       iter != elementRefables->end(); iter++)
    {
      refable = iter->second;
      if (refable->substitutes.size() && (refable->mock == false))
	{
	  choice = new XmlChoice();
	  choiceType = new XmlComplexType();
	  other = new XmlOtherContent();
	  snprintf(name, NAMESIZE, "%s_%d_Type", refable->newName, *mockCount);
	  choiceType->name = strdup(name);
	  choiceType->newName = choiceType->name;
	  choiceType->item = other;
	  other->base = choice;
	  buildChoice(choice, iter->second);
	  refable->typ = choiceType->newName;
	  refable->newTyp = choiceType->newName;
	  refable->mock = true;
	  classes->push_back(choiceType);
	  (*mockCount)++;
	  (*allComplex)[choiceType->newName] = choiceType;
	}
    }
}

/********************************************************************/

/* generator::reviewChanges

Returned Value: none

Called By: main

This reviews the texts in the changeMap to see if all of them have
been marked done. If any is not so marked, this prints a warning
message.  A warning message should be printed only if a class that was
printed for an earlier version of an XML schema (the one from which
the old header file was generated) is not printed for the current
version of the schema.

*/

void generator::reviewChanges() /* NO ARGUMENTS */
{
  std::list<char *> * changes;
  std::map<std::string, std::list<char *> *>::iterator iter;
  
  for (iter = changeMap->begin(); iter != changeMap->end(); iter++)
    {
      changes = iter->second;
      if (strncmp(changes->front(), "done", 4))
	{
	  fprintf(stderr, 
		  "warning: class %s no longer exists -- changes were lost\n",
		  iter->first.c_str());
	}
    }
}

/********************************************************************/

/* generator::setHas

Returned Value: none

Called By:
  generator::buildYaccBasicRule
  generator::buildYaccRestrictSListRule
  generator::buildYaccSimpleListRule
  generator::buildYaccSimpleRule
  generator::buildYaccUnionElementPairs

If the prefix and typeName (XXX) are those of a basic type, this sets
hasXXX to true, which indicates that the YACC file needs to include
the production y_XXX, since that production is used in other productions.

Need to add more basic types, e.g. short.

*/

void generator::setHas( /* ARGUMENTS            */
 char * prefix,         /* prefix for type name */
 char * typeName)       /* type name            */
{
  if (prefix && (prefix == XmlCppBase::wg3Prefix))
    {
      if (strcmp(typeName, "anyURI") == 0)
	hasAnyURI = true;
      else if (strcmp(typeName, "boolean") == 0)
	hasBoolean = true;
      else if (strcmp(typeName, "date") == 0)
	hasDate = true;
      else if (strcmp(typeName, "dateTime") == 0)
	hasDateTime = true;
      else if (strcmp(typeName, "decimal") == 0)
	hasDecimal = true;
      else if (strcmp(typeName, "double") == 0)
	hasDouble = true;
      else if (strcmp(typeName, "float") == 0)
	hasFloat = true;
      else if (strcmp(typeName, "ID") == 0)
	hasID = true;
      else if (strcmp(typeName, "IDREF") == 0)
	hasIDREF = true;
      else if (strcmp(typeName, "int") == 0)
	hasInt = true;
      else if (strcmp(typeName, "integer") == 0)
	hasInteger = true;
      else if (strcmp(typeName, "long") == 0)
	hasLong = true;
      else if (strcmp(typeName, "NMTOKEN") == 0)
	hasNMTOKEN = true;
      else if (strcmp(typeName, "nonNegativeInteger") == 0)
	hasNonNegativeInteger = true;
      else if (strcmp(typeName, "positiveInteger") == 0)
	hasPositiveInteger = true;
      else if (strcmp(typeName, "string") == 0)
	hasString = true;
      else if (strcmp(typeName, "token") == 0)
	hasToken = true;
      else if (strcmp(typeName, "unsignedInt") == 0)
	hasUnsignedInt = true;
      else if (strcmp(typeName, "unsignedLong") ==0)
	hasUnsignedLong = true;
      else
	{
	  fprintf(stderr, "setHas cannot handle %s\n", typeName);
	  exit(1);
	}
    }
}

/********************************************************************/

/* generator::usageMessage

Returned Value: none

Called By: main

This prints a message about how to use the GenXMiller.

*/

void generator::usageMessage( /* ARGUMENTS                            */
 char * command)              /* the name of the generator executable */
{
  fprintf(stderr, "usage: %s [-s] [-h <header>] [-p <prefix>] [-i <include prefix>] [-a <app include prefix>] -x <schema>\n",
	  command);
  fprintf(stderr, "-s means write code that takes input from a string\n");
  fprintf(stderr, "header is the existing header file\n");
  fprintf(stderr, "prefix is the prefix to use in YACC and Lex files\n");
  fprintf(stderr, "include prefix is the prefix for these header files\n");
  fprintf(stderr, "app include prefix is the prefix for application header files\n");
  fprintf(stderr, "schema is the XML schema file to read\n");
  fprintf(stderr, "Example 1: %s -x plan.xsd\n", command);
  fprintf(stderr, "Example 2: %s -s -x plan.xsd\n", command);
  fprintf(stderr, "Example 3: %s -p yypl -x plan.xsd\n", command);
  fprintf(stderr, "Example 4: %s -x plan.xsd -h planClasses.hh\n", command);
  fprintf(stderr, "Example 5: %s -i xml_parser_generator/ -a crcl/ -x plan.xsd\n", command);
  exit(1);
}

/********************************************************************/

/* elementInfo functions

two constructors and a destructor for the elementInfo class.

*/

elementInfo:: elementInfo() {}

elementInfo:: elementInfo(
 XmlElementLocal * elementIn,
 XmlComplexType * complexTypeIn,
 XmlSimpleType * simpleTypeIn)
{
  element = elementIn;
  complexType = complexTypeIn;
  simpleType = simpleTypeIn;
}

 elementInfo::~ elementInfo() {}

/********************************************************************/

/* namePair functions

two constructors and a destructor for the namePair class.

*/

namePair::namePair() {}

namePair::namePair(
 char * name1In,
 char * name2In)
{
  name1 = name1In;
  name2 = name2In;
}

namePair::~namePair() {}

/********************************************************************/

