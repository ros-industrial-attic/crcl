#include "xml_parser_generator/xmlSchemaClasses.hh"
#include <stdio.h>             // for printf, etc.
#include <string.h>            // for strdup
#include <ctype.h>             // isalpha, isalnum
#include <stdlib.h>            // for exit

#define INDENT 2
#define NAMESIZE 200

/*

The following definition of XmlCppBase::wg3Prefix is necessary since
wg3Prefix is a static data member of the XmlCppBase class. Strangely,
this also works if "extern" is inserted at the beginning of the line.

If XmlCppBase::wg3Prefix were defined elsewhere, the definition here
without "extern" would presumably produce a "doubly defined" error.

If XmlCppBase::wg3Prefix were not defined elsewhere, inserting "extern"
would presumably cause an "undefined" error.

But no error occurs either way.

The Gnu compiler seems to ignore any "extern" in the definition of a
static data member of a class. Some experimentation shows that if
a declaration of a static data member of a class is made in a different
file using "extern" a compiler error occurs.

*/

char * XmlCppBase::wg3Prefix;
char * XmlCppBase::otherPrefix;
int XmlCppBase::spaces = 0;

/********************************************************************/

/* XmlCppBase::modifyName

Returned Value: char *
  If the "name" argument contains no dashes, this returns that argument.
  If the argument contains dashes or starts with a lower case letter,
  this returns a new char array that is the name with any dashes changed
  to underscores.

Called By: constructors that take arguments for:
  XmlAttributeGroupRefable    - newName
  XmlAttributeLoner           - newName, newTyp
  XmlAttributeLonerRefable    - newName, newTyp
  XmlComplexType              - newName
  XmlElementLocal             - newName, newTyp
  XmlElementRefable           - newName, newTyp
  XmlSimpleContentExtension   - newBase
  XmlSimpleContentRestriction - newBase
  XmlSimpleList               - newItemType
  XmlSimpleRestriction        - newBase
  XmlSimpleType               - newName

For names:
1. XSDL
- The first character must be a letter.
- Each other characters may be a letter, digit, dash, underscore, or period.

2. C++
- The first character must be a letter or an underscore.
- Each other character must be a letter, digit, or underscore.

This implements the following conversions and checks.

If the first character is not a letter, this prints an error message
and exits.

If any other character is not a letter, digit, dash, underscore, or period,
this prints an error message and exits.

If there is no error, all characters are kept the same, except that dashes
and periods are changed to underscores, so that the newXXX is acceptable
in C++. This may produce name conflicts, but that is not likely.


*/

char * XmlCppBase::modifyName( /* ARGUMENTS                 */
 char * name)                  /* name to (possibly) change */
{
  static char buffer[NAMESIZE];
  bool change = false;
  int n;

  if (!isalpha(name[0]))
    {
      fprintf(stderr, "name %s must begin with a letter\n", name);
      exit(1);
    }
  for(n = 0; ((n < (NAMESIZE - 1)) && name[n]); n++)
    {
      if (name[n] == '-')
	{
	  buffer[n] = '_';
	  change = true;
	}
      else if (name[n] == '.')
	{
	  buffer[n] = '_';
	  change = true;
	}
      else if (name[n] == '_');
      else if (!isalnum(name[n]))
	{
	  fprintf(stderr, "illegal character %c in name %s\n", name[n], name);
	  exit(1);
	}
      else
	buffer[n] = name[n];
    }
  if (n == (NAMESIZE - 1))
    {
      fprintf(stderr, "name %s is too long\n", name);
      exit(1);
    }
  buffer[n] = 0;
  return (change ? strdup(buffer) : name);
}

/********************************************************************/

XmlCppBase::XmlCppBase() {}

XmlCppBase::~XmlCppBase() {}

/********************************************************************/

/* XmlCppBase::printBool

This prints lines such as:
 abstract="true"
 abstract="false"

A logicalE may be logicalNone, which is neither true nor false, so
this function should be called only if boolVal is not logicalNone. It
is easy to test for this since the numeric value of logicalNone is 0.

*/

void XmlCppBase::printBool(
 logicalE boolVal,
 const char * attrib,
 FILE * outFile)
{
  fputc('\n', outFile);
  doSpaces(0, outFile);
  fprintf(outFile, "%s=\"%s\"", attrib,
	  ((boolVal == yes) ? "true" : "false"));
}

/********************************************************************/

void XmlCppBase::printMaxOccurs(
 int maxOccurs,
 FILE * outFile)
{
  if (maxOccurs > -2)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      if (maxOccurs == -1)
	fprintf(outFile, "maxOccurs=\"unbounded\"");
      else
	fprintf(outFile, "maxOccurs=\"%d\"", maxOccurs);
    }
}

/********************************************************************/

void XmlCppBase::printMinOccurs(
 int minOccurs,
 FILE * outFile)
{
  if (minOccurs > -1)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "minOccurs=\"%d\"", minOccurs);
    }
}

/********************************************************************/

void XmlCppBase::doSpaces(int change, FILE * outFile)
{
  static int n;

  if (change)
    spaces += change;
  else
    {
      for (n = 0; n < spaces; n++)
	fputc(' ', outFile);
    }
}

/********************************************************************/
/********************************************************************/

/* XmlAnnotation

*/

XmlAnnotation::XmlAnnotation() {}

XmlAnnotation::XmlAnnotation(
  char * idIn,
  std::string * commentIn,
  std::list<XmlAnnoType *> * contentIn)
{
  id = idIn;
  comment = commentIn;
  content = contentIn;
}

XmlAnnotation::~XmlAnnotation() {}

void XmlAnnotation::printSelf(FILE * outFile)
{
  std::list<XmlAnnoType *>::iterator iter;

  if (comment && XmlSchemaFile::printComments)
    {
      fprintf(outFile, "\n");
      fprintf(outFile, "<!--%s-->\n", comment->c_str());
    }
  if (content && (XmlSchemaFile::printDoc != 3))
    { // do not print annotation if not printing documentation
      if ((!(comment && XmlSchemaFile::printComments)) && (spaces == INDENT))
	fprintf(outFile, "\n");
      doSpaces(0, outFile);
      fprintf(outFile, "<%s:annotation>\n", wg3Prefix);
      doSpaces(+INDENT, outFile);
      for (iter = content->begin(); iter != content->end(); iter++)
	(*iter)->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:annotation>\n", wg3Prefix);
    }
}

/********************************************************************/

XmlAnnoType::XmlAnnoType() {}

XmlAnnoType::~XmlAnnoType() {}

/********************************************************************/

/* XmlAny

For namespace, the spec allows (1) one of ##any or ##other, or (2) a
list of anyURI or ##targetNamespace or ##local. This class allows only
one namespace occurrence. It may be ##any, ##other, ##targetNamespace,
or ##local.

*/

XmlAny::XmlAny()
{
  id = 0;
  maxOccurs = -2;
  minOccurs = -2;
  namespaceVal = namespaceNone;
  processContentsVal = processContentsNone;
  frontNote = 0;
}

XmlAny::XmlAny(
 char * idIn,
 int maxOccursIn,
 int minOccursIn,
 namespaceE namespaceValIn,
 processContentsE processContentsValIn,
 XmlAnnotation * frontNoteIn)
{
  id = 0;
  maxOccurs = -2;
  minOccurs = -2;
  namespaceVal = namespaceNone;
  processContentsVal = processContentsNone;
  frontNote = 0;
}

XmlAny::~XmlAny() {}

void XmlAny::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:any", wg3Prefix);
  if (id)
    fprintf(outFile, " id=\"%s\"", id);
  doSpaces(+INDENT, outFile);
  printMinOccurs(minOccurs, outFile);
  printMaxOccurs(maxOccurs, outFile);
  if (namespaceVal != namespaceNone)
    {
      fprintf(outFile, "\n");
      doSpaces(0, outFile);
      fprintf(outFile, "namespace=\"%s\"",
	      ((namespaceVal == hashAny) ? "##any" :
	       (namespaceVal == hashTarget) ? "##targetNamespace" :
	       (namespaceVal == hashOther) ? "##other" :
	       (namespaceVal == hashLocal) ? "##local" : "error"));
    }
  
  if (processContentsVal != processContentsNone)
    {
      fprintf(outFile, "\n");
      doSpaces(0, outFile);
      fprintf(outFile, "processContents=\"%s\"",
	      ((processContentsVal == lax) ? "lax" :
	       (processContentsVal == skip) ? "skip" :
	       (processContentsVal == strict) ? "strict" : "error"));
    }
  if (frontNote)
    {
      fprintf(outFile, ">\n");
      frontNote->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:any>\n", wg3Prefix);
    }
  else
    {
      fprintf(outFile, "/>\n");
      doSpaces(-INDENT, outFile);
    }
}

/********************************************************************/

/* XmlAppinfo

The text to be printed by the printSelf function includes every character
that was read between <xs:appinfor> and </xs:appinfo>. This
may include white space (ASCII 9, 10, 13, 32).

*/

XmlAppinfo::XmlAppinfo()
{
  source = 0;
  text = 0;
}

XmlAppinfo::XmlAppinfo(
  char * sourceIn,
  char * textIn)
{
  source = sourceIn;
  text = textIn;
}

XmlAppinfo::~XmlAppinfo() {}

void XmlAppinfo::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:appinfo", wg3Prefix);
  if (source)
    fprintf(outFile, " source=\"%s\"", source);
  fprintf(outFile, ">");
  if (text)
    {
      fprintf(outFile, "%s", text);
    }
  fprintf(outFile, "</%s:appinfo>\n", wg3Prefix);
}

/********************************************************************/

XmlAttributor::XmlAttributor() {}

XmlAttributor::~XmlAttributor() {}

/********************************************************************/

XmlAttribPair::XmlAttribPair() {}

XmlAttribPair::XmlAttribPair(
  int nameIn,
  char * prefIn,
  char * valIn)
{
  name = nameIn;
  pref = prefIn;
  val = valIn;
}

XmlAttribPair::~XmlAttribPair() {}

/********************************************************************/

/* XmlAttributeGroupRef

An XmlAttributeGroupRef is always a reference.

*/

XmlAttributeGroupRef::XmlAttributeGroupRef()
{
  id = 0;
  ref = 0;
  frontNote = 0;
}

XmlAttributeGroupRef::XmlAttributeGroupRef(
  char * idIn,
  char * refIn,
  XmlAnnotation * frontNoteIn)
{
  id = idIn;
  ref = refIn;
  frontNote = frontNoteIn;
}

XmlAttributeGroupRef::~XmlAttributeGroupRef() {}

void XmlAttributeGroupRef::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:attributeGroup", wg3Prefix);
  if (ref)
    fprintf(outFile, " ref=\"%s\"", ref);
  else
    {
      fprintf(stderr, "ref missing in attributeGroup\n");
      exit(1);
    }
  doSpaces(+INDENT, outFile);
  if (id)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "id=\"%s\"", id);
    }
  if (frontNote)
    {
      fprintf(outFile, ">\n");
      frontNote->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:attributeGroup>\n", wg3Prefix);
    }
  else
    {
      fprintf(outFile, "/>\n");
      doSpaces(-INDENT, outFile);
    }
}

/********************************************************************/

/* XmlAttributeGroupRefable

*/

XmlAttributeGroupRefable::XmlAttributeGroupRefable()
{
  id = 0;
  name = 0;
  frontNote = 0;
  backNotes = 0;
  xmlAttributes = 0;
  newName = 0;
}

XmlAttributeGroupRefable::XmlAttributeGroupRefable(
  char * idIn,
  char * nameIn,
  XmlAnnotation * frontNoteIn,
  std::list<XmlAnnotation *> * backNotesIn,
  std::list<XmlAttributor *> * xmlAttributesIn)
{
  id = idIn;
  name = nameIn;
  frontNote = frontNoteIn;
  backNotes = backNotesIn;
  xmlAttributes = xmlAttributesIn;
  newName = modifyName(name);
}

XmlAttributeGroupRefable::~XmlAttributeGroupRefable() {}

void XmlAttributeGroupRefable::printSelf(FILE * outFile)
{
  std::list<XmlAttributor *>::iterator iter;
  
  fprintf(outFile, "\n");
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:attributeGroup", wg3Prefix);
  if (name)
    fprintf(outFile, " name=\"%s\"", name);
  else
    {
      fprintf(stderr, "top level attributeGroup name missing\n");
      exit(1);
    }
  doSpaces(+INDENT, outFile);
  if (id)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "id=\"%s\"", id);
    }
  if (frontNote || (xmlAttributes && xmlAttributes->size()))
    {
      fprintf(outFile, ">\n");
      if (frontNote)
	frontNote->printSelf(outFile);
      if (xmlAttributes)
	{
	  for (iter = xmlAttributes->begin();
	       iter != xmlAttributes->end();
	       iter++)
	    (*iter)->printSelf(outFile);
	}
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:attributeGroup>\n", wg3Prefix);
    }
  else
    {
      fprintf(outFile, "/>\n");
      doSpaces(-INDENT, outFile);
    }
  if (backNotes)
    {
      std::list<XmlAnnotation *>::iterator iter;
      for (iter = backNotes->begin(); iter != backNotes->end(); iter++)
	(*iter)->printSelf(outFile);
    }
}

/********************************************************************/

XmlAttributeLoner::XmlAttributeLoner()
{
  defalt = 0;
  fixed = 0;
  form = formNone;
  id = 0;
  name = 0;
  ref = 0;
  typPrefix = 0;
  typ = 0;
  use = useNone;
  frontNote = 0;
  simple = 0;
  newNameRef = 0;
  newTyp = 0;
}

XmlAttributeLoner::XmlAttributeLoner(
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
  XmlSimpleType * simpleIn)
{
  defalt = defaltIn;
  fixed = fixedIn;
  form = formIn;
  id = idIn;
  name = nameIn;
  ref = refIn;
  typPrefix = typPrefixIn;
  typ = typIn;
  use = useIn;
  frontNote = frontNoteIn;
  simple = simpleIn;
  newNameRef = (name ? modifyName(name) : modifyName(ref));
  newTyp = modifyName(typ);
}

XmlAttributeLoner::~XmlAttributeLoner() {}

void XmlAttributeLoner::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:attribute", wg3Prefix);
  if (name)
    {
      if (ref)
	{
	  fprintf(stderr, "element %s has name and ref\n", name);
	  exit(1);
	}
      fprintf(outFile, " name=\"%s\"", name);
    }
  else if (ref)
    fprintf(outFile, " ref=\"%s\"", ref);
  doSpaces(+INDENT, outFile);
  if (typ)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      if (typPrefix)
	fprintf(outFile, "type=\"%s:%s\"", typPrefix, typ);
      else
	fprintf(outFile, "type=\"%s\"", typ);
    }
  if (defalt)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "default=\"%s\"", defalt);
    }
  if (fixed)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "fixed=\"%s\"", fixed);
    }
  if (form)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "form=\"%s\"",
	      ((form == qualified) ? "qualified" : "unqualified"));
    }
  if (id)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "id=\"%s\"", id);
    }
  if (use)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "use=\"%s\"",
	      ((use == required) ? "required" :
	       (use == optional) ? "optional" : "prohibited"));
    }
  if (frontNote || simple)
    {
      fprintf(outFile, ">\n");
      if (frontNote)
	frontNote->printSelf(outFile);
      if (simple)
	simple->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:attribute>\n", wg3Prefix);
    }
  else
    {
      fprintf(outFile, "/>\n");
      doSpaces(-INDENT, outFile);
    }
}

/********************************************************************/

/* XmlAttributeLonerRefable

*/

XmlAttributeLonerRefable::XmlAttributeLonerRefable()
{
  defalt = 0;
  fixed = 0;
  id = 0;
  name = 0;
  typ = 0;
  frontNote = 0;
  backNotes = 0;
  simple = 0;
  newName = 0;
}

XmlAttributeLonerRefable::XmlAttributeLonerRefable(
  char * defaltIn,
  char * fixedIn,
  char * idIn,
  char * nameIn,
  char * typIn,
  XmlAnnotation * frontNoteIn,
  std::list<XmlAnnotation *> * backNotesIn,
  XmlSimpleType * simpleIn)
{
  defalt = defaltIn;
  fixed = fixedIn;
  id = idIn;
  name = nameIn;
  typ = typIn;
  frontNote = frontNoteIn;
  backNotes = backNotesIn;
  simple = simpleIn;
  newName = modifyName(name);
  newTyp = modifyName(typ);
}

XmlAttributeLonerRefable::~XmlAttributeLonerRefable() {}

void XmlAttributeLonerRefable::printSelf(FILE * outFile)
{
  fprintf(outFile, "\n");
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:attribute name=\"%s\"", wg3Prefix, name);
  doSpaces(+INDENT, outFile);
  if (typ)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
       if (typPrefix)
	fprintf(outFile, "type=\"%s:%s\"", typPrefix, typ);
      else
	fprintf(outFile, "type=\"%s\"", typ);
    }
  if (defalt)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "default=\"%s\"", defalt);
    }
  if (fixed)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "fixed=\"%s\"", fixed);
    }
  if (id)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "id=\"%s\"", id);
    }
  if (frontNote || simple)
    {
      fprintf(outFile, ">\n");
      if (frontNote)
	frontNote->printSelf(outFile);
      if (simple)
	simple->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:attribute>\n", wg3Prefix);
    }
  else
    {
      fprintf(outFile, "/>\n");
      doSpaces(-INDENT, outFile);
    }
  if (backNotes)
    {
      std::list<XmlAnnotation *>::iterator iter;
      for (iter = backNotes->begin(); iter != backNotes->end(); iter++)
	(*iter)->printSelf(outFile);
    }
}

/********************************************************************/

XmlChoice::XmlChoice()
{
  id = 0;
  maxOccurs = -2;
  minOccurs = -2;
  frontNote = 0;
  items = 0;
}

XmlChoice::XmlChoice(
  char * idIn,
  int maxOccursIn,
  int minOccursIn,
  XmlAnnotation * frontNoteIn,
  std::list<XmlChoSeqItem *> * itemsIn)
{
  id = idIn;
  maxOccurs = maxOccursIn;
  minOccurs = minOccursIn;
  frontNote = frontNoteIn;
  items = itemsIn;
}

XmlChoice::~XmlChoice() {}

void XmlChoice::printSelf(FILE * outFile)
{
  std::list<XmlChoSeqItem *>::iterator iter;

  doSpaces(0, outFile);
  fprintf(outFile, "<%s:choice", wg3Prefix);
  if (id)
    fprintf(outFile, " id=\"%s\"", id);
  doSpaces(+INDENT, outFile);
  printMinOccurs(minOccurs, outFile);
  printMaxOccurs(maxOccurs, outFile);
  if (frontNote || (items && items->size()))
    {
      fprintf(outFile, ">\n");
      if (frontNote)
	frontNote->printSelf(outFile);
      if (items)
	{
	  for (iter = items->begin(); iter != items->end(); iter++)
	    (*iter)->printSelf(outFile);
	}
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:choice>\n", wg3Prefix);
    }
  else
    {
      fprintf(outFile, "/>\n");
      doSpaces(-INDENT, outFile);
    }
}

/********************************************************************/

XmlChoSeqItem::XmlChoSeqItem() {}

XmlChoSeqItem::~XmlChoSeqItem() {}

/********************************************************************/

XmlComplexContent::XmlComplexContent() {}

XmlComplexContent::XmlComplexContent(
  char * idIn,
  logicalE mixedIn,
  XmlAnnotation * frontNoteIn,
  XmlComplexContentItem * itemIn)
{
  id = idIn;
  mixed = mixedIn;
  frontNote = frontNoteIn;
  item = itemIn;
}

XmlComplexContent::~XmlComplexContent() {}

void XmlComplexContent::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:complexContent", wg3Prefix);
  if (id)
    fprintf(outFile, " id=\"%s\"", id);
  doSpaces(+INDENT, outFile);
  if (mixed)
    printBool(mixed, "mixed", outFile);
  if (frontNote || item)
    {
      fprintf(outFile, ">\n");
      if (frontNote)
	frontNote->printSelf(outFile);
      if(item)
	item->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:complexContent>\n", wg3Prefix);
    }
  else
    {
      fprintf(outFile, "/>\n");
      doSpaces(-INDENT, outFile);
    }
}

/********************************************************************/

XmlComplexContentItem::XmlComplexContentItem() {}

XmlComplexContentItem::~XmlComplexContentItem() {}

/********************************************************************/

XmlComplexExtension::XmlComplexExtension()
{
  id = 0;
  basePrefix = 0;
  base = 0;
  frontNote = 0;
  item = 0;
  attribs = 0;
  newAttribs = 0;
}

XmlComplexExtension::XmlComplexExtension(
  char * idIn,
  char * basePrefixIn,
  char * baseIn,
  XmlAnnotation * frontNoteIn,
  XmlOtherContentBase * itemIn,
  std::list<XmlAttributor *> * attribsIn)
{
  id = idIn;
  basePrefix = basePrefixIn;
  base = baseIn;
  frontNote = frontNoteIn;
  item = itemIn;
  attribs = attribsIn;
  newAttribs = 0;
}

XmlComplexExtension::~XmlComplexExtension() {}

void XmlComplexExtension::printSelf(FILE * outFile)
{
  std::list<XmlAttributor *>::iterator iter;

  doSpaces(0, outFile);
  fprintf(outFile, "<%s:extension", wg3Prefix);
  if (base)
    {
      if (basePrefix)
	fprintf(outFile, " base=\"%s:%s\"", basePrefix, base);
      else
	fprintf(outFile, " base=\"%s\"", base);
    }
  else
    {
      fprintf(stderr, "complex extension must have base\n");
      exit(1);
    }
  if (id)
    fprintf(outFile, " id=\"%s\"", id);
  if (frontNote || item || (attribs && attribs->size()))
    {
      fprintf(outFile, ">\n");
      doSpaces(+INDENT, outFile);
      if (frontNote)
	frontNote->printSelf(outFile);
      if(item)
	item->printSelf(outFile);
      if (attribs)
	{
	  for (iter = attribs->begin(); iter != attribs->end(); iter++)
	    (*iter)->printSelf(outFile);
	}
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:extension>\n", wg3Prefix);
    }
  else
    {
      fprintf(outFile, "/>\n");
    }
}

/********************************************************************/

XmlComplexRestriction::XmlComplexRestriction() {}

XmlComplexRestriction::XmlComplexRestriction(
  char * idIn,
  char * baseIn,
  XmlAnnotation * frontNoteIn,
  XmlOtherContentBase * itemIn,
  std::list<XmlAttributor *> * attribsIn)
{
  id = idIn;
  base = baseIn;
  frontNote = frontNoteIn;
  item = itemIn;
  attribs = attribsIn;
}

XmlComplexRestriction::~XmlComplexRestriction() {}

void XmlComplexRestriction::printSelf(FILE * outFile)
{
  std::list<XmlAttributor *>::iterator iter;

  doSpaces(0, outFile);
  fprintf(outFile, "<%s:restriction", wg3Prefix);
  if (base)
    fprintf(outFile, " base=\"%s\"", base);
  else
    {
      fprintf(stderr, "complexRestriction must have base\n");
      exit(1);
    }
  if (id)
    fprintf(outFile, " id=\"%s\"", id);

  doSpaces(+INDENT, outFile);
  if (frontNote || item || (attribs && attribs->size()))
    {
      fprintf(outFile, ">\n");
      if (frontNote)
	frontNote->printSelf(outFile);
      if(item)
	item->printSelf(outFile);
      if (attribs)
	{
	  for (iter = attribs->begin(); iter != attribs->end(); iter++)
	    (*iter)->printSelf(outFile);
	}
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:restriction>\n", wg3Prefix);
    }
  else
    {
      fprintf(outFile, "/>\n");
      doSpaces(-INDENT, outFile);
    }
}

/********************************************************************/

/* XmlComplexType

*/

XmlComplexType::XmlComplexType()
{
  abstract = logicalNone;
  mixed = logicalNone;
  id = 0;
  name = 0;
  frontNote = 0;
  backNotes = 0;
  item = 0;
  newName = 0;
  ccPrinted = false;
  hhPrinted = false;
  owlPrefix = 0;
  extensions = 0;
}

XmlComplexType::XmlComplexType(
  logicalE abstractIn,
  logicalE mixedIn,
  char * idIn,
  char * nameIn,
  XmlAnnotation * frontNoteIn,
  std::list<XmlAnnotation *> * backNotesIn,
  XmlComplexTypeItem * itemIn)
{
  abstract = abstractIn;
  mixed = mixedIn;
  id = idIn;
  name = nameIn;
  frontNote = frontNoteIn;
  backNotes = backNotesIn;
  item = itemIn;
  newName = modifyName(name);
  ccPrinted = false;
  hhPrinted = false;
  owlPrefix = 0;
  extensions = 0;
}

XmlComplexType::~XmlComplexType() {}

void XmlComplexType::printSelf(FILE * outFile)
{
  if (spaces == INDENT)
    fprintf(outFile, "\n");
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:complexType", wg3Prefix);
  if (name)
    fprintf(outFile, " name=\"%s\"", name);
  doSpaces(+INDENT, outFile);
  if (id)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "id=\"%s\"", id);
    }
  if (abstract)
    printBool(abstract, "abstract", outFile);
  if (mixed)
    printBool(mixed, "mixed", outFile);
  if (frontNote || item)
    {
      fprintf(outFile, ">\n");
      if (frontNote)
	frontNote->printSelf(outFile);
      if(item)
	item->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:complexType>\n", wg3Prefix);
    }
  else
    {
      fprintf(outFile, "/>\n");
      doSpaces(-INDENT, outFile);
    }
  if (backNotes)
    {
      std::list<XmlAnnotation *>::iterator iter;
      for (iter = backNotes->begin(); iter != backNotes->end(); iter++)
	(*iter)->printSelf(outFile);
    }
}

/********************************************************************/

XmlComplexTypeItem::XmlComplexTypeItem() {}

XmlComplexTypeItem::~XmlComplexTypeItem() {}

/********************************************************************/

/* XmlDocumentation

The text to be printed by the printSelf function includes every character
that was read between <xs:documentation> and </xs:documentation>. This
may include white space (ASCII 9, 10, 13, 32).

There are three types of printing:
1. When XmlSchemaFile::printDoc == 0, print the text exactly as it is.
2. When XmlSchemaFile::printDoc == 1, and the indented function
   returns true, that indicates the text has already been formatted nicely,
   so print the text exactly as it is.
3. When XmlSchemaFile::printDoc == 1, and the indented function
   returns false, print the text prettily indented. This requires some
   fiddling to find the best places for line breaks. The rules are:
   (a) Start printing the text on a new line
   (b) Begin each line at the same indentation level (two spaces
       farther in from the current indentation level).
   (c) Print as far as space 75 (inclusive) on each line without
       breaking words apart.
   (d) Separate lines at white space in the text.
   (e) If a "word" is longer than the space available on a line,
       print it on a separate line and let it go past space 75.
4. When XmlSchemaFile::printDoc == 2 and the formatted function
   returns true, that indicates the text is specially formatted,
   so print the text exactly as it is.
5. When XmlSchemaFile::printDoc == 2, and the formatted function
   returns false, print the text all on one line, replacing any contiguous
   set of white space characters with a single space.
6. When XmlSchemaFile::printDoc == 3, print nothing.

In cases 3 and 5 above, <xs:documentation> and </xs:documentation>
each always appear alone on a line.

Might split out cases 3 and 5 into separate functions.

*/

XmlDocumentation::XmlDocumentation()
{
  source = 0;
  text = 0;
}

XmlDocumentation::XmlDocumentation(
  char * sourceIn,
  char * textIn)
{
  source = sourceIn;
  text = textIn;
}

XmlDocumentation::~XmlDocumentation() {}

void XmlDocumentation::printSelf(FILE * outFile)
{
  int n; // text counter
  int m; // place to save n
  int k; // buffer counter
  static char buffer[1000];

  if (XmlSchemaFile::printDoc == 3) // print nothing
    return;
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:documentation", wg3Prefix);
  if (source)
    fprintf(outFile, " source=\"%s\"", source);
  fprintf(outFile, ">");
  if (text)
    {
      if (XmlSchemaFile::printDoc == 0)
	{ // print documentation asIs
	  fprintf(outFile, "%s", text);
	}
      else if ((XmlSchemaFile::printDoc == 1) && indented())
	{ // should be indented and already is; print documentation asIs
	  fprintf(outFile, "%s", text);
	}
      else if (XmlSchemaFile::printDoc == 1) // indent
	{ // put characters from text into buffer; then print buffer to file
	  fprintf(outFile, "\n");
	  doSpaces(+INDENT, outFile);
	  for (k = 0, n = 0; text[n]; )
	    {
	      if ((text[n] == 9)  || (text[n] == 10) ||
		  (text[n] == 13) || (text[n] == 32))
		{ // a white space has been found
		  if ((k + spaces) > 75)
		    { // too many chars in buffer; back up to white space
		      buffer[k] = 0; // in case one word fills line
		      m = n; // save place in case one word fills line
		      for (k--,n--; ((k > -1) && (buffer[k] != 32)); n--,k--);
		      if (k == -1)
			{ // one word fills line, so go back to end of word
			  n = m;
			}
		      else
			{ // one word does not fill line
			  buffer[k] = 0;
			  n++; // go to character after white space in text
			}
		      doSpaces(0, outFile);
		      fprintf(outFile, "%s\n", buffer);
		      k = 0;
		    }
		  else
		    {
		      for (n++; 
			   ((text[n] == 9)  || (text[n] == 10) ||
			    (text[n] == 13) || (text[n] == 32));
			   n++); // detect contiguous white space
		      if (k && text[n]) // no space at beginning or end of line
			buffer[k++] = 32; // print one space in buffer
		    }
		}
	      else
		{
		  buffer[k++] = text[n++];
		  if (k > 998)
		    {
		      buffer[999] = 0;
		      fprintf(stderr,
			      "cannot handle monster word %s\n", buffer);
		      exit(1);
		    }
		}
	    }
	  if (k)
	    { // print last line if anything in buffer.
	      buffer[k] = 0;
	      doSpaces(0, outFile);
	      if ((k + spaces) > 75)
		{ // too many chars in buffer; back up to white space
		  for (; ((k > -1) && (buffer[k] != 32)); k--);
		  if (k == -1)
		    { // buffer holds one giant word; print it
		      fprintf(outFile, "%s\n", buffer);
		    }
		  else
		    { // found a space before end of line
		      buffer[k] = 0; // split buffer in two
		      fprintf(outFile, "%s\n", buffer); // print buffer start
		      doSpaces(0, outFile);
		      fprintf(outFile, "%s\n", (buffer + k + 1)); // print end
		    }
		}
	      else
		fprintf(outFile, "%s\n", buffer);
	    }
	  doSpaces(-INDENT, outFile);
	  doSpaces(0, outFile);	  
	}
      else if ((XmlSchemaFile::printDoc == 2) && formatted())
	{ // is specially formatted; print documentation asIs
	  fprintf(outFile, "%s", text);
	}
      else if (XmlSchemaFile::printDoc == 2) // print on one line
	{ 
	  n = 0;
	  k = 0;
	  while (text[n])
	    {
	      if ((text[n] == 9)  || (text[n] == 10) ||
		  (text[n] == 13) || (text[n] == 32))
		{
		  for (n++; 
		       ((text[n] == 9)  || (text[n] == 10) ||
			(text[n] == 13) || (text[n] == 32));
		       n++); // detect contiguous white space
		  if (k && text[n]) // no space at beginning or end
		    fputc(32, outFile); // print one space
		}
	      else
		{
		  fputc(text[n++], outFile);
		  k = 1;
		}
	    }
	}
      else
	{
	  fprintf(stderr, "bug - bad value of XmlSchemaFile::printDoc\n");
	  exit(1);
	}
      fprintf(outFile, "</%s:documentation>\n", wg3Prefix);
    }
}

/*

The indented function returns true if the text of the XmlDocumentation
is already indented almost the way the printSelf function would do it.
Otherwise, it returns false. To return true, the beginning of each
line must have at least "spaces" space characters at the front, and
the length of the line must not exceed 76 (one more than the printSelf
function allows).

When this is called, spaces is set to the number of spaces before the
<xs:documentation> tag. The text is indented further, so the check for
space at the beginning of each text line uses (spaces + INDENT).

*/

bool XmlDocumentation::indented()
{
  int n; // text counter
  int k; // line counter
  int m; // space counter

  for (m = 0, k = 0, n = 0; text[n]; n++)
    {
      if ((text[n] == 13) || (text[n] == 10))
	{ // reached end of line
	  k = 0;
          m = 0;
	}
      else if (k == 0)
	{ // line all spaces so far
	  if (text[n] == 32)
	    { // found another space at the beginning of the line
	      m++;
	      if (m > 76)
		{ // line is full and is all spaces so far
		  return false;
		}
	    }
	  else if (m < (spaces+INDENT))
	    { // found first non-space on line; not enough spaces at front
	      return false;
	    }
	  else
	    { // found first non-space on line after enough space
	      k = m + 1;
	    }
	}
      else
	{ // found next character on line after enough space
	  k++;
	  if (k > 76)
	    { // line is too long
	      return false;
	    }
	}
    }
  return true;
}

/*

The formatted function returns true if all of the following are true:
 1. Each line of the text of the XmlDocumentation is indented at least
    as much as the printSelf function would do it,
    i.e., at least (spaces+INDENT) spaces.
 2. At least one line of the text of the XmlDocumentation is indented
    exactly two spaces more than the way the printSelf function would do it,
    i.e., spaces+INDENT+2.
 3. No line is too long i.e., more than 76 characters.
Otherwise, it returns false.

When this is called, spaces is set to the number of spaces before the
<xs:documentation> tag. The text is indented further, so the check for
space at the beginning of each text line uses (spaces + INDENT) or
(spaces +INDENT + 2).

*/

bool XmlDocumentation::formatted()
{
  int n; // text counter
  int k; // line counter
  int m; // space counter
  bool isFormatted;

  isFormatted = false;
  for (m = 0, k = 0, n = 0; text[n]; n++)
    {
      if ((text[n] == 13) || (text[n] == 10))
	{ // reached end of line
	  k = 0;
          m = 0;
	}
      else if (k == 0)
	{ // line all spaces so far
	  if (text[n] == 32)
	    { // found another space at the beginning of the line
	      m++;
	      if (m > 76)
		{ // line is full and is all spaces so far
		  return false;
		}
	    }
	  else if (m < (spaces + INDENT))
	    { // found first non-space on line; not enough spaces at front
	      return false;
	    }
	  else
	    { // found first non-space on line after enough space
	      if (m == (spaces +INDENT + 2))
		isFormatted = true;
	      k = m + 1;
	    }
	}
      else
	{ // found next character on line after enough space
	  k++;
	  if (k > 76)
	    { // line is too long
	      return false;
	    }
	}
    }
  return isFormatted;
}

/********************************************************************/

/* XmlElementGroup

*/

XmlElementGroup::XmlElementGroup()
{
  id = 0;
  name = 0;
  frontNote = 0;
  item = 0;
}

XmlElementGroup::XmlElementGroup(
  char * idIn,
  char * nameIn,
  XmlAnnotation * frontNoteIn,
  XmlChoSeqItem * itemIn)
{
  id = idIn;
  name = nameIn;
  frontNote = frontNoteIn;
  item = itemIn;
}

XmlElementGroup::~XmlElementGroup() {}

void XmlElementGroup::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:group", wg3Prefix);
  if (name)
    {
      fprintf(outFile, " name=\"%s\"", name);
    }
  else
    {
      fprintf(stderr, "group must have name\n");
      exit(1);
    }
  if (id)
    fprintf(outFile, " id=\"%s\"", id);
  doSpaces(+INDENT, outFile);
  if (frontNote || item)
    {
      fprintf(outFile, ">\n");
      if (frontNote)
	frontNote->printSelf(outFile);
      if (item)
	{
	  if (dynamic_cast<XmlElementGroup *>(item))
	    {
	      fprintf(stderr, "element group %s contains a group\n", name);
	      exit(1);
	    }
	  item->printSelf(outFile);
	}
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:group>\n", wg3Prefix);
    }
  else
    {
      fprintf(outFile, "/>\n");
      doSpaces(-INDENT, outFile);
    }
}

/********************************************************************/

/* XmlElementGroupRef

*/

XmlElementGroupRef::XmlElementGroupRef()
{
  id = 0;
  ref = 0;
  frontNote = 0;
  minOccurs = -2;
  maxOccurs = -2;
}

XmlElementGroupRef::XmlElementGroupRef(
  char * idIn,
  char * refIn,
  int maxOccursIn,
  int minOccursIn,
  XmlAnnotation * frontNoteIn)
{
  id = idIn;
  ref = refIn;
  maxOccurs = maxOccursIn;
  minOccurs = minOccursIn;
  frontNote = frontNoteIn;
}

XmlElementGroupRef::~XmlElementGroupRef() {}

void XmlElementGroupRef::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:group", wg3Prefix);
  if (ref)
    {
      fprintf(outFile, " ref=\"%s\"", ref);
    }
  else
    {
      fprintf(stderr, "element group reference must have ref\n");
      exit(1);
    }
  if (id)
    fprintf(outFile, " id=\"%s\"", id);
  printMinOccurs(minOccurs, outFile);
  printMaxOccurs(maxOccurs, outFile);
  doSpaces(+INDENT, outFile);
  if (frontNote)
    {
      fprintf(outFile, ">\n");
      frontNote->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:group>\n", wg3Prefix);
    }
  else
    {
      fprintf(outFile, "/>\n");
      doSpaces(-INDENT, outFile);
    }
}

/********************************************************************/

/* XmlElementLocal

PrintSelf is printing both name and ref on the first line. Normally,
only one will actually appear on that line, since normally, only one
of the two will be used.

*/

XmlElementLocal::XmlElementLocal()
{
  defalt = 0;
  fixed = 0;
  form = formNone;
  id = 0;
  maxOccurs = -2;
  minOccurs = -2;
  name = 0;
  nillable = logicalNone;
  ref = 0;
  typPrefix = 0;
  typ = 0;
  frontNote = 0;
  typeDef = 0;
  idConstraints = 0;
  mock = false;
  needList = false;
  newName = 0;
  newTyp = 0;
  prodBase = 0;
}

XmlElementLocal::XmlElementLocal(
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
  std::list<XmlIdConstraint *> * idConstraintsIn)
{
  defalt = defaltIn;
  fixed = fixedIn;
  form = formIn;
  id = idIn;
  maxOccurs = maxOccursIn;
  minOccurs = minOccursIn;
  name = nameIn;
  nillable = nillableIn;
  ref = refIn;
  typPrefix = typPrefixIn;
  typ = typIn;
  frontNote = frontNoteIn;
  typeDef = typeDefIn;
  idConstraints = idConstraintsIn;
  mock = false;
  needList = false;
  newName = modifyName(name);
  newTyp = modifyName(typ);
  prodBase = 0;
}

XmlElementLocal::~XmlElementLocal() {}

void XmlElementLocal::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:element", wg3Prefix);
  if (name)
    {
      if (ref)
	{
	  fprintf(stderr, "element %s has name and ref\n", name);
	  exit(1);
	}
      fprintf(outFile, " name=\"%s\"", name);
    }
  else if (ref)
    {
      if (typ || name ||form || defalt || fixed ||
          (nillable && (nillable == XmlCppBase::yes)))
	{
	  fprintf(stderr, "element %s with ref has bad properties\n", name);
	  exit(1);
	}
      fprintf(outFile, " ref=\"%s\"", ref);
    }
  else
    {
      fprintf(stderr, "element must have name or ref\n");
      exit(1);
    }
  doSpaces(+INDENT, outFile);
  if (typ)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      if (typPrefix)
	fprintf(outFile, "type=\"%s:%s\"", typPrefix, typ);
      else
	fprintf(outFile, "type=\"%s\"", typ);
    }
  if (form)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "form=\"%s\"",
	      ((form == qualified) ? "qualified" : "unqualified"));
    }
  if (nillable)
    printBool(nillable, "nillable", outFile);
  if (id)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "id=\"%s\"", id);
    }
  if (defalt)
    {
      if (fixed)
	{
	  fprintf(stderr, "element %s has default and fixed\n", name);
	  exit(1);
	}
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "default=\"%s\"", defalt);
    }
  if (fixed)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "fixed=\"%s\"", fixed);
    }
  printMinOccurs(minOccurs, outFile);
  printMaxOccurs(maxOccurs, outFile);
  if (frontNote || typeDef || idConstraints)
    {
      fprintf(outFile, ">\n");
      if (frontNote)
	frontNote->printSelf(outFile);
      if (typeDef)
	typeDef->printSelf(outFile);
      if (idConstraints)
	{
	  std::list<XmlIdConstraint *>::iterator iter;
	  XmlKey * key;
	  XmlKeyref * keyref;
	  XmlUnique * unique;
	  for (iter = idConstraints->begin();
	       iter != idConstraints->end(); iter++)
	    {
	      if ((key = dynamic_cast<XmlKey *>(*iter)))
		key->printSelf(outFile);
	      else if ((keyref = dynamic_cast<XmlKeyref *>(*iter)))
		keyref->printSelf(outFile);
	      else if ((unique = dynamic_cast<XmlUnique *>(*iter)))
		unique->printSelf(outFile);
	      else
		{
		  fprintf(stderr, "bad XmlIdConstraint\n");
		  exit(1);
		}
	    }
	}
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:element>\n", wg3Prefix);
    }
  else
    {
      fprintf(outFile, "/>\n");
      doSpaces(-INDENT, outFile);
    }
}

/********************************************************************/

/* XmlElementRefable

PrintSelf is printing both name and ref on the first line. Normally,
only one will actually appear on that line, since normally, only one
of the two will be used.

FIX - Add idConstraints to printSelf.

*/

XmlElementRefable::XmlElementRefable()
{
  abstract = logicalNone;
  defalt = 0;
  fixed = 0;
  id = 0;
  name = 0;
  nillable = logicalNone;
  substitutionGroup = 0;
  typPrefix = 0;
  typ = 0;
  frontNote = 0;
  backNotes = 0;
  typeDef = 0;
  idConstraints = 0;
  mock = false;
  newName = 0;
  newTyp = 0;
  prodBase = 0;
}

XmlElementRefable::XmlElementRefable(
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
  std::list<XmlIdConstraint *> * idConstraintsIn)
{
  abstract = abstractIn;
  defalt = defaltIn;
  fixed = fixedIn;
  id = idIn;
  name = nameIn;
  nillable = nillableIn;
  substitutionGroup = substitutionGroupIn;
  typPrefix = typPrefixIn;
  typ = typIn;
  frontNote = frontNoteIn;
  backNotes = backNotesIn;
  typeDef = typeDefIn;
  idConstraints = idConstraintsIn;
  mock = false;
  newName = modifyName(name);
  newTyp = modifyName(typ);
  prodBase = 0;
}

XmlElementRefable::~XmlElementRefable() {}

void XmlElementRefable::printSelf(FILE * outFile)
{
  fprintf(outFile, "\n");
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:element", wg3Prefix);
  if (name)
    fprintf(outFile, " name=\"%s\"", name);
  else
    {
      fprintf(stderr, "element must have name\n");
      exit(1);
    }
  doSpaces(+INDENT, outFile);
  if (typ)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      if (typPrefix)
	fprintf(outFile, "type=\"%s:%s\"", typPrefix, typ);
      else
	fprintf(outFile, "type=\"%s\"", typ);
    }
  if (abstract)
    printBool(abstract, "abstract", outFile);
  if (nillable)
    printBool(nillable, "nillable", outFile);
  if (id)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "id=\"%s\"", id);
    }
  if (defalt)
    {
      if (fixed)
	{
	  fprintf(stderr, "element %s has default and fixed\n", name);
	  exit(1);
	}
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "default=\"%s\"", defalt);
    }
  if (fixed)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "fixed=\"%s\"", fixed);
    }
  if (substitutionGroup)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "substitutionGroup=\"%s\"", substitutionGroup);
    }
  if (frontNote || typeDef || idConstraints)
    {
      fprintf(outFile, ">\n");
      if (frontNote)
	frontNote->printSelf(outFile);
      if (typeDef)
	typeDef->printSelf(outFile);
      if (idConstraints)
	{
	  std::list<XmlIdConstraint *>::iterator iter;
	  XmlKey * key;
	  XmlKeyref * keyref;
	  XmlUnique * unique;
	  for (iter = idConstraints->begin();
	       iter != idConstraints->end(); iter++)
	    {
	      if ((key = dynamic_cast<XmlKey *>(*iter)))
		key->printSelf(outFile);
	      else if ((keyref = dynamic_cast<XmlKeyref *>(*iter)))
		keyref->printSelf(outFile);
	      else if ((unique = dynamic_cast<XmlUnique *>(*iter)))
		unique->printSelf(outFile);
	      else
		{
		  fprintf(stderr, "bad XmlIdConstraint\n");
		  exit(1);
		}
	    }
	}
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:element>\n", wg3Prefix);
    }
  else
    {
      fprintf(outFile, "/>\n");
      doSpaces(-INDENT, outFile);
    }
  if (backNotes)
    {
      std::list<XmlAnnotation *>::iterator iter;
      for (iter = backNotes->begin(); iter != backNotes->end(); iter++)
	(*iter)->printSelf(outFile);
    }
}

/********************************************************************/

XmlEnumeration::XmlEnumeration()
{
  id = 0;
  value = 0;
  frontNote = 0;
}

XmlEnumeration::XmlEnumeration(
  char * idIn,
  char * valueIn,
  XmlAnnotation * frontNoteIn)
{
  id = idIn;
  value = valueIn;
  frontNote = frontNoteIn;
}

XmlEnumeration::~XmlEnumeration() {}

void XmlEnumeration::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:enumeration value=\"%s\"", wg3Prefix, value);
  if (id)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "id=\"%s\"", id);
    }
  if (frontNote)
    {
      fprintf(outFile, ">\n");
      doSpaces(+INDENT, outFile);
      frontNote->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:enumeration>\n", wg3Prefix);
    }
    else
      fprintf(outFile, "/>\n");
}

/********************************************************************/

/* XmlField

The xpath 

*/

XmlField::XmlField()
{
  xpath = 0;
}

XmlField::XmlField(
 char * xpathIn)
{
  xpath = xpathIn;
}

XmlField::~XmlField() {}

void XmlField::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:field", wg3Prefix);
  if (xpath)
    fprintf(outFile, " xpath=\"%s\"/>\n", xpath);
  else
    {
      fprintf(stderr, "field must have xpath\n");
      exit(1);
    }
}


/********************************************************************/

/* XmlFinal

The printSelf function does not print anything if all values are
false.

*/

XmlFinal::XmlFinal()
{
  all = false;
  list = false;
  younion = false;
  restriction = false;
}

XmlFinal::~XmlFinal() {}

void XmlFinal::printSelf(FILE * outFile)
{
  bool printedOne = false;

  if (all)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "final=\"#all\"");
    }
  else if (list || younion || restriction)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "final=\"");
      if (list)
	{
	  fprintf(outFile, "list");
	  printedOne = true;
	}
      if (younion)
	{
	  if (printedOne)
	    fputc(' ', outFile);
	  fprintf(outFile, "union");
	  printedOne = true;
	}
      if (restriction)
	{
	  if (printedOne)
	    fputc(' ', outFile);
	  fprintf(outFile, "restriction");
	}
    }
}

/********************************************************************/

/* XmlIdConstraint

*/

XmlIdConstraint::XmlIdConstraint() {}

XmlIdConstraint::~XmlIdConstraint() {}

/********************************************************************/

/* XmlImport

*/

XmlImport::XmlImport()
{
  schemaLocation = 0;
  namespase = 0;
  note = 0;
}

XmlImport::XmlImport(
  char * schemaLocationIn,
  char * namespaseIn,
  XmlAnnotation * noteIn)
{
  schemaLocation = schemaLocationIn;
  namespase = namespaseIn;
  note = noteIn;
}

XmlImport::~XmlImport() {}

void XmlImport::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:import", wg3Prefix);
  if (namespase)
    {
      fprintf(outFile, " namespace=\"%s\"", namespase);
      if (schemaLocation)
	{
	  fprintf(outFile, "\n");
	  doSpaces(0, outFile);
	  fprintf(outFile, "  schemaLocation=\"%s\"", schemaLocation);
	}
    }
  if (note)
    {
      fprintf(outFile, ">\n");
      doSpaces(+INDENT, outFile);
      note->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:import>\n", wg3Prefix);
    }
  else
    fprintf(outFile, "/>\n");
}

/********************************************************************/

/* XmlInclude

*/

XmlInclude::XmlInclude()
{
  schemaLocation = 0;
  note = 0;
}

XmlInclude::XmlInclude(
  char * schemaLocationIn,
  XmlAnnotation * noteIn)
{
  schemaLocation = schemaLocationIn;
  note = noteIn;
}

XmlInclude::~XmlInclude() {}

void XmlInclude::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:include schemaLocation=\"%s\"",
	  wg3Prefix, schemaLocation);
  if (note)
    {
      fprintf(outFile, ">\n");
      doSpaces(+INDENT, outFile);
      note->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:include>\n", wg3Prefix);
    }
  else
    fprintf(outFile, "/>\n");
}

/********************************************************************/

/* XmlKey

The printSelf function is printing any comments that precede a key.
A blank line is printed before the first comment, if there is one.
A blank line is also printed before the key.

*/

XmlKey::XmlKey()
{
  id = 0;
  name = 0;
  frontNote = 0;
  selector = 0;
  fields = 0;
}

XmlKey::XmlKey(
  char * idIn,
  char * nameIn,
  std::list<std::string *> * commentsIn,
  XmlAnnotation * frontNoteIn,
  XmlSelector * selectorIn,
  std::list<XmlField *> * fieldsIn)
{
  id = idIn;
  name = nameIn;
  comments = commentsIn;
  frontNote = frontNoteIn;
  selector = selectorIn;
  fields = fieldsIn;
}

XmlKey::~XmlKey() {}

void XmlKey::printSelf(FILE * outFile)
{
  std::list<XmlField *>::iterator iter;
  std::list<std::string *>::iterator ator;

  if (comments && XmlSchemaFile::printComments)
    {
      fprintf(outFile, "\n");
      for (ator = comments->begin(); ator != comments->end(); ator++)
	{
	  fprintf(outFile, "<!--");
	  fprintf(outFile, "%s", (*ator)->c_str());
	  fprintf(outFile, "-->\n");
	}
    }
  fprintf(outFile, "\n");
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:key", wg3Prefix);
  if (name)
    fprintf(outFile, " name=\"%s\">\n", name);
  else
    {
      fprintf(stderr, "key must have name\n");
      exit(1);
    }
  doSpaces(+INDENT, outFile);
  if (frontNote)
    frontNote->printSelf(outFile);
  if (selector)
    selector->printSelf(outFile);
  else
    {
      fprintf(stderr, "key must have selector\n");
      exit(1);
    }
  if (fields && fields->size())
    {
      for (iter = fields->begin(); iter != fields->end(); iter++)
	(*iter)->printSelf(outFile);
    }
  else
    {
      fprintf(stderr, "key must have at least one field\n");
      exit(1);
    }
  doSpaces(-INDENT, outFile);
  doSpaces(0, outFile);
  fprintf(outFile, "</%s:key>\n", wg3Prefix);
}

/********************************************************************/

/* XmlKeyref

*/

XmlKeyref::XmlKeyref()
{
  id = 0;
  name = 0;
  frontNote = 0;
  selector = 0;
  fields = 0;
}

XmlKeyref::XmlKeyref(
  char * idIn,
  char * nameIn,
  char * referIn,
  std::list<std::string *> * commentsIn,
  XmlAnnotation * frontNoteIn,
  XmlSelector * selectorIn,
  std::list<XmlField *> * fieldsIn)
{
  id = idIn;
  name = nameIn;
  refer = referIn;
  frontNote = frontNoteIn;
  selector = selectorIn;
  fields = fieldsIn;
}

XmlKeyref::~XmlKeyref() {}

void XmlKeyref::printSelf(FILE * outFile)
{
  std::list<XmlField *>::iterator iter;
  std::list<std::string *>::iterator ator;

  if (comments && XmlSchemaFile::printComments)
    {
      fprintf(outFile, "\n");
      for (ator = comments->begin(); ator != comments->end(); ator++)
	{
	  fprintf(outFile, "<!--");
	  fprintf(outFile, "%s", (*ator)->c_str());
	  fprintf(outFile, "-->\n");
	}
    }
  fprintf(outFile, "\n");
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:keyref", wg3Prefix);
  if (name)
    fprintf(outFile, " name=\"%s\"\n", name);
  else
    {
      fprintf(stderr, "keyref must have name\n");
      exit(1);
    }
  doSpaces(0, outFile);
  if (refer)
    fprintf(outFile, "  refer=\"%s\">\n", refer);
  else
    {
      fprintf(stderr, "keyref must have refer\n");
      exit(1);
    }
  doSpaces(+INDENT, outFile);
  if (frontNote)
    frontNote->printSelf(outFile);
  if (selector)
    selector->printSelf(outFile);
  else
    {
      fprintf(stderr, "keyref must have selector\n");
      exit(1);
    }
  if (fields && fields->size())
    {
      for (iter = fields->begin(); iter != fields->end(); iter++)
	(*iter)->printSelf(outFile);
    }
  else
    {
      fprintf(stderr, "keyref must have at least one field\n");
      exit(1);
    }
  doSpaces(-INDENT, outFile);
  doSpaces(0, outFile);
  fprintf(outFile, "</%s:keyref>\n", wg3Prefix);
}

/********************************************************************/

XmlLength::XmlLength()
{
  fixed = logicalNone;
  id = 0;
  value = 0;
  note = 0;
  intVal = 0;
}

XmlLength::XmlLength(
  logicalE fixedIn,
  char * idIn,
  char * valueIn,
  XmlAnnotation * noteIn)
{
  fixed = fixedIn;
  id = idIn;
  value = valueIn;
  note = noteIn;
  intVal = 0;
}

XmlLength::~XmlLength() {}

void XmlLength::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:length value=\"%s\"", wg3Prefix, value);
  if (fixed)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "fixed=\"%s\"", ((fixed == yes) ? "true" : "false"));
    }
  if (id)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "id=\"%s\"", id);
    }
  if (note)
    {
      fprintf(outFile, ">\n");
      doSpaces(+INDENT, outFile);
      note->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:length>\n", wg3Prefix);
    }
  else
    fprintf(outFile, "/>\n");
}

/********************************************************************/

XmlMaxExclusive::XmlMaxExclusive()
{
  fixed = logicalNone;
  id = 0;
  value = 0;
  note = 0;
}

XmlMaxExclusive::XmlMaxExclusive(
  logicalE fixedIn,
  char * idIn,
  char * valueIn,
  XmlAnnotation * noteIn)
{
  fixed = fixedIn;
  id = idIn;
  value = valueIn;
  note = noteIn;
}

XmlMaxExclusive::~XmlMaxExclusive() {}

void XmlMaxExclusive::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:maxExclusive value=\"%s\"", wg3Prefix, value);
  if (fixed)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "fixed=\"%s\"", ((fixed == yes) ? "true" : "false"));
    }
  if (id)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "id=\"%s\"", id);
    }
  if (note)
    {
      fprintf(outFile, ">\n");
      doSpaces(+INDENT, outFile);
      note->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:maxExclusive>\n", wg3Prefix);
    }
  else
    fprintf(outFile, "/>\n");
}

/********************************************************************/

XmlMaxInclusive::XmlMaxInclusive()
{
  fixed = logicalNone;
  id = 0;
  value = 0;
  note = 0;
}

XmlMaxInclusive::XmlMaxInclusive(
  logicalE fixedIn,
  char * idIn,
  char * valueIn,
  XmlAnnotation * noteIn)
{
  fixed = fixedIn;
  id = idIn;
  value = valueIn;
  note = noteIn;
}

XmlMaxInclusive::~XmlMaxInclusive() {}

void XmlMaxInclusive::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:maxInclusive value=\"%s\"", wg3Prefix, value);
  if (fixed)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "fixed=\"%s\"", ((fixed == yes) ? "true" : "false"));
    }
  if (id)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "id=\"%s\"", id);
    }
  if (note)
    {
      fprintf(outFile, ">\n");
      doSpaces(+INDENT, outFile);
      note->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:maxInclusive>\n", wg3Prefix);
    }
  else
    fprintf(outFile, "/>\n");
}

/********************************************************************/

XmlMaxLength::XmlMaxLength()
{
  fixed = logicalNone;
  id = 0;
  value = 0;
  note = 0;
  intVal = 0;
}

XmlMaxLength::XmlMaxLength(
  logicalE fixedIn,
  char * idIn,
  char * valueIn,
  XmlAnnotation * noteIn)
{
  fixed = fixedIn;
  id = idIn;
  value = valueIn;
  note = noteIn;
  intVal = 0;
}

XmlMaxLength::~XmlMaxLength() {}

void XmlMaxLength::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:maxLength value=\"%s\"", wg3Prefix, value);
  if (fixed)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "fixed=\"%s\"", ((fixed == yes) ? "true" : "false"));
    }
  if (id)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "id=\"%s\"", id);
    }
  if (note)
    {
      fprintf(outFile, ">\n");
      doSpaces(+INDENT, outFile);
      note->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:maxLength>\n", wg3Prefix);
    }
  else
    fprintf(outFile, "/>\n");
}

/********************************************************************/

XmlMinExclusive::XmlMinExclusive()
{
  fixed = logicalNone;
  id = 0;
  value = 0;
  note = 0;
}

XmlMinExclusive::XmlMinExclusive(
  logicalE fixedIn,
  char * idIn,
  char * valueIn,
  XmlAnnotation * noteIn)
{
  fixed = fixedIn;
  id = idIn;
  value = valueIn;
  note = noteIn;
}

XmlMinExclusive::~XmlMinExclusive() {}

void XmlMinExclusive::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:minExclusive value=\"%s\"", wg3Prefix, value);
  if (fixed)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "fixed=\"%s\"", ((fixed == yes) ? "true" : "false"));
    }
  if (id)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "id=\"%s\"", id);
    }
  if (note)
    {
      fprintf(outFile, ">\n");
      doSpaces(+INDENT, outFile);
      note->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:minExclusive>\n", wg3Prefix);
    }
  else
    fprintf(outFile, "/>\n");
}

/********************************************************************/

XmlMinInclusive::XmlMinInclusive()
{
  fixed = logicalNone;
  id = 0;
  value = 0;
  note = 0;
}

XmlMinInclusive::XmlMinInclusive(
  logicalE fixedIn,
  char * idIn,
  char * valueIn,
  XmlAnnotation * noteIn)
{
  fixed = fixedIn;
  id = idIn;
  value = valueIn;
  note = noteIn;
}

XmlMinInclusive::~XmlMinInclusive() {}

void XmlMinInclusive::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:minInclusive value=\"%s\"", wg3Prefix, value);
  if (fixed)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "fixed=\"%s\"", ((fixed == yes) ? "true" : "false"));
    }
  if (id)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "id=\"%s\"", id);
    }
  if (note)
    {
      fprintf(outFile, ">\n");
      doSpaces(+INDENT, outFile);
      note->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:minInclusive>\n", wg3Prefix);
    }
  else
    fprintf(outFile, "/>\n");
}

/********************************************************************/

XmlMinLength::XmlMinLength()
{
  fixed = logicalNone;
  id = 0;
  value = 0;
  note = 0;
  intVal = 0;
}

XmlMinLength::XmlMinLength(
  logicalE fixedIn,
  char * idIn,
  char * valueIn,
  XmlAnnotation * noteIn)
{
  fixed = fixedIn;
  id = idIn;
  value = valueIn;
  note = noteIn;
  intVal = 0;
}

XmlMinLength::~XmlMinLength() {}

void XmlMinLength::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:minLength value=\"%s\"", wg3Prefix, value);
  if (fixed)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "fixed=\"%s\"", ((fixed == yes) ? "true" : "false"));
    }
  if (id)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "id=\"%s\"", id);
    }
  if (note)
    {
      fprintf(outFile, ">\n");
      doSpaces(+INDENT, outFile);
      note->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:minLength>\n", wg3Prefix);
    }
  else
    fprintf(outFile, "/>\n");
}

/********************************************************************/

XmlNsPair::XmlNsPair() {}

XmlNsPair::XmlNsPair(
  char * prefixIn,
  char * locationIn)
{
  prefix = prefixIn;
  location = locationIn;
}

XmlNsPair::~XmlNsPair() {}

void XmlNsPair::printSelf(FILE * outFile)
{
  fputc('\n', outFile);
  doSpaces(0, outFile);
  if (prefix)
    fprintf(outFile, "xmlns:%s=\"%s\"", prefix, location);
  else
    fprintf(outFile, "xmlns=\"%s\"", location);
}

/********************************************************************/

XmlOtherContent::XmlOtherContent()
{
  base = 0;
  attribs = 0;
  newAttribs = 0;
}

XmlOtherContent::XmlOtherContent(
  XmlOtherContentBase * baseIn,
  std::list<XmlAttributor *> * attribsIn)
{
  base = baseIn;
  attribs = attribsIn;
  newAttribs = 0;
}

XmlOtherContent::~XmlOtherContent() {}

void XmlOtherContent::printSelf(FILE * outFile)
{
  std::list<XmlAttributor *>::iterator iter;

  if (base)
    base->printSelf(outFile);
  if (attribs)
    {
      for (iter = attribs->begin(); iter != attribs->end(); iter++)
	(*iter)->printSelf(outFile);
    }
}

/********************************************************************/

XmlOtherContentBase::XmlOtherContentBase() {}

XmlOtherContentBase::~XmlOtherContentBase() {}

/********************************************************************/

XmlPattern::XmlPattern()
{
  id = 0;
  value = 0;
  frontNote = 0;
}

XmlPattern::XmlPattern(
  char * idIn,
  char * valueIn,
  XmlAnnotation * frontNoteIn)
{
  id = idIn;
  value = valueIn;
  frontNote = frontNoteIn;
}

XmlPattern::~XmlPattern() {}

void XmlPattern::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:pattern value=\"%s\"", wg3Prefix, value);
  if (id)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "id=\"%s\"", id);
    }
  if (frontNote)
    {
      fprintf(outFile, ">\n");
      doSpaces(+INDENT, outFile);
      frontNote->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:pattern>\n", wg3Prefix);
    }
    else
      fprintf(outFile, "/>\n");
}

/********************************************************************/

XmlRestrictionType::XmlRestrictionType() {}

XmlRestrictionType::~XmlRestrictionType() {}

/********************************************************************/

XmlSchema::XmlSchema()
{
  header = 0;
  contents1 = 0;
  contents2 = 0;
}

XmlSchema::XmlSchema(
 XmlSchemaHeader * headerIn,
 std::list<XmlSchemaContent1 *> * contents1In,
 std::list<XmlSchemaContent2 *> * contents2In)
{
  header = headerIn;
  contents1 = contents1In;
  contents2 = contents2In;
}

XmlSchema::~XmlSchema() {}

void XmlSchema::printSelf(FILE * outFile)
{
  std::list<XmlSchemaContent1 *>::iterator iter;
  std::list<XmlSchemaContent2 *>::iterator ator;

  header->printSelf(outFile);
  doSpaces(+INDENT, outFile);
  for (iter = contents1->begin(); iter != contents1->end(); iter++)
    (*iter)->printSelf(outFile);
  for (ator = contents2->begin(); ator != contents2->end(); ator++)
    (*ator)->printSelf(outFile);
  doSpaces(-INDENT, outFile);
  fprintf(outFile, "\n");
  fprintf(outFile, "</%s:schema>\n", wg3Prefix);
}

/********************************************************************/

XmlSchemaContent1::XmlSchemaContent1() {}

XmlSchemaContent1::~XmlSchemaContent1() {}

/********************************************************************/

XmlSchemaContent2::XmlSchemaContent2() {}

XmlSchemaContent2::~XmlSchemaContent2() {}

/********************************************************************/

XmlSchemaFile::XmlSchemaFile()
{
  version = 0;
  schema = 0;
}

XmlSchemaFile::XmlSchemaFile(
  XmlVersion * versionIn,
  std::list<std::string *> * commentsIn,
  XmlSchema * schemaIn)
{
  version = versionIn;
  comments = commentsIn;
  schema = schemaIn;
}

XmlSchemaFile::~XmlSchemaFile() {}

void XmlSchemaFile::printSelf(FILE * outFile)
{
  std::list<std::string *>::iterator iter;

  if (version)
    version->printSelf(outFile);
  if (comments && printComments)
    {
      for (iter = comments->begin(); iter != comments->end(); iter++)
	{
	  fprintf(outFile, "<!--");
	  fprintf(outFile, "%s", (*iter)->c_str());
	  fprintf(outFile, "-->\n");
	}
    }
  schema->printSelf(outFile);
}

/********************************************************************/

XmlSchemaHeader::XmlSchemaHeader()
{
  version = 0;
  id = 0;
  schemaSchema = 0;
  otherSchemas =  0;
  attributeFormDefault = formNone;
  elementFormDefault =  formNone;
  targetNamespace = 0;
}

XmlSchemaHeader::XmlSchemaHeader(
 char * versionIn,
 char * idIn,
 XmlNsPair * schemaSchemaIn,
 std::list<XmlNsPair *> * otherSchemasIn,
 formE attributeFormDefaultIn,
 formE elementFormDefaultIn,
 char * targetNamespaceIn)
{
  version = versionIn;
  id = idIn;
  schemaSchema = schemaSchemaIn;
  otherSchemas = otherSchemasIn;
  attributeFormDefault = attributeFormDefaultIn;
  elementFormDefault = elementFormDefaultIn;
  targetNamespace = targetNamespaceIn;
}

XmlSchemaHeader::~XmlSchemaHeader() {}

void XmlSchemaHeader::printSelf(FILE * outFile)
{
  std::list<XmlNsPair *>::iterator iter;
  
  fprintf(outFile, "\n");
  fprintf(outFile, "<%s:schema", wg3Prefix);
  doSpaces(+INDENT, outFile);
  schemaSchema->printSelf(outFile);
  for (iter = otherSchemas->begin(); iter != otherSchemas->end(); iter++)
    (*iter)->printSelf(outFile);
  if (targetNamespace)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "targetNamespace=");
      fprintf(outFile, "\"%s\"", targetNamespace);
    }
  if (elementFormDefault)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "elementFormDefault=\"%s\"",
	      ((elementFormDefault == XmlSchemaHeader::qualified) ?
	       "qualified" : "unqualified"));
    }
  if (attributeFormDefault)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "attributeFormDefault=\"%s\"",
	      ((attributeFormDefault == XmlSchemaHeader::qualified) ?
	       "qualified" : "unqualified"));
    }
  if (version)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "version=\"%s\"", version);
    }
  if (id)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "id=\"%s\"", id);
    }
  doSpaces(-INDENT, outFile);
  fprintf(outFile, ">\n");
}

/********************************************************************/

/* XmlSelector

*/

XmlSelector::XmlSelector()
{
  xpath = 0;
}


XmlSelector::XmlSelector(
 char * xpathIn)
{
  xpath = xpathIn;
}

XmlSelector::~XmlSelector() {}

void XmlSelector::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:selector", wg3Prefix);
  if (xpath)
    {
      if (((int)strlen(xpath) + spaces) > 55)
	{
	  fprintf(outFile, "\n");
	  doSpaces(0, outFile);
	  fprintf(outFile, " ");
	}
      fprintf(outFile, " xpath=\"%s\"/>\n", xpath);
    }
  else
    {
      fprintf(stderr, "selector must have xpath\n");
      exit(1);
    }
}

/********************************************************************/

/* XmlSequence

*/

XmlSequence::XmlSequence()
{
  id = 0;
  maxOccurs = -2;
  minOccurs = -2;
  frontNote = 0;
  items = 0;
}

XmlSequence::XmlSequence(
  char * idIn,
  int maxOccursIn,
  int minOccursIn,
  XmlAnnotation * frontNoteIn,
  std::list<XmlChoSeqItem *> * itemsIn)
{
  id = idIn;
  maxOccurs = maxOccursIn;
  minOccurs = minOccursIn;
  frontNote = frontNoteIn;
  items = itemsIn;
}

XmlSequence::~XmlSequence() {}

void XmlSequence::printSelf(FILE * outFile)
{
  std::list<XmlChoSeqItem *>::iterator iter;

  doSpaces(0, outFile);
  fprintf(outFile, "<%s:sequence", wg3Prefix);
  if (id)
    fprintf(outFile, " id=\"%s\"", id);
  doSpaces(+INDENT, outFile);
  printMinOccurs(minOccurs, outFile);
  printMaxOccurs(maxOccurs, outFile);
  if (frontNote || (items && items->size()))
    {
      fprintf(outFile, ">\n");
      if (frontNote)
	frontNote->printSelf(outFile);
      if (items)
	{
	  for (iter = items->begin(); iter != items->end(); iter++)
	    (*iter)->printSelf(outFile);
	}
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:sequence>\n", wg3Prefix);
    }
  else
    {
      fprintf(outFile, "/>\n");
      doSpaces(-INDENT, outFile);
    }
}

/********************************************************************/

XmlSimpleContent::XmlSimpleContent() {}

XmlSimpleContent::XmlSimpleContent(
  char * idIn,
  XmlAnnotation * frontNoteIn,
  XmlSimpleContentItem * itemIn)
{
  id = idIn;
  frontNote = frontNoteIn;
  item = itemIn;
}

XmlSimpleContent::~XmlSimpleContent() {}

void XmlSimpleContent::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:simpleContent", wg3Prefix);
  if (id)
    fprintf(outFile, " id=\"%s\"", id);
  if (frontNote || item)
    {
      doSpaces(+INDENT, outFile);
      fprintf(outFile, ">\n");
      if (frontNote)
	frontNote->printSelf(outFile);
      if (item)
	item->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:simpleContent>\n", wg3Prefix);
    }
  else
    {
      fprintf(outFile, "/>\n");
    }
}

/********************************************************************/

XmlSimpleContentItem::XmlSimpleContentItem() {}

XmlSimpleContentItem::~XmlSimpleContentItem() {}

/********************************************************************/

XmlSimpleContentExtension::XmlSimpleContentExtension() {}

XmlSimpleContentExtension::XmlSimpleContentExtension(
  char * basePrefixIn,
  char * baseIn,
  char * idIn,
  XmlAnnotation * frontNoteIn,
  std::list<XmlAttributor *> * attribsIn)
{
  basePrefix = basePrefixIn;
  base = baseIn;
  id = idIn;
  frontNote = frontNoteIn;
  attribs = attribsIn;
  newBase = modifyName(base);
}

XmlSimpleContentExtension::~XmlSimpleContentExtension() {}

void XmlSimpleContentExtension::printSelf(FILE * outFile)
{
  std::list<XmlAttributor *>::iterator iter;

  doSpaces(0, outFile);
  fprintf(outFile, "<%s:extension", wg3Prefix);
  if (base)
    {
      if (basePrefix)
	fprintf(outFile, " base=\"%s:%s\"", basePrefix, base);
      else
	fprintf(outFile, " base=\"%s\"", base);
    }
  else
    {
      fprintf(stderr, "complex extension must have base\n");
      exit(1);
    }
  if (id)
    fprintf(outFile, " id=\"%s\"", id);
  if (frontNote || (attribs && attribs->size()))
    {
      fprintf(outFile, ">\n");
      doSpaces(+INDENT, outFile);
      if (frontNote)
	frontNote->printSelf(outFile);
      if (attribs)
	{
	  for (iter = attribs->begin(); iter != attribs->end(); iter++)
	    (*iter)->printSelf(outFile);
	}
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:extension>\n", wg3Prefix);
    }
  else
    {
      fprintf(outFile, "/>\n");
    }
}

/********************************************************************/

XmlSimpleContentRestriction::XmlSimpleContentRestriction()
{
  basePrefix = 0;
  base = 0;
  id = 0;
  frontNote = 0;
  aSimple = 0;
  restrictions = 0;
  newBase = 0;
}

XmlSimpleContentRestriction::XmlSimpleContentRestriction(
  char * basePrefixIn,
  char * baseIn,
  char * idIn,
  XmlAnnotation * frontNoteIn,
  XmlSimpleType * aSimpleIn,
  std::list<XmlRestrictionType *> * restrictionsIn,
  std::list<XmlAttributor *> * attribsIn)
{
  basePrefix = basePrefixIn;
  base = baseIn;
  id = idIn;
  frontNote = frontNoteIn;
  aSimple = aSimpleIn;
  restrictions = restrictionsIn;
  attribs = attribsIn;
  newBase = modifyName(base);
}

XmlSimpleContentRestriction::~XmlSimpleContentRestriction() {}

void XmlSimpleContentRestriction::printSelf(FILE * outFile)
{
  std::list<XmlRestrictionType *>::iterator iter;
  std::list<XmlAttributor *>::iterator ator;

  doSpaces(0, outFile);
  fprintf(outFile, "<%s:restriction base=", wg3Prefix);
  if (basePrefix)
    fprintf(outFile, "\"%s:%s\"", basePrefix, base);
  else
    fprintf(outFile, "\"%s\"", base);
  doSpaces(+INDENT, outFile);
  if (id)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "id=\"%s\"", id);
    }
  if (frontNote || aSimple || (restrictions && restrictions->size()) ||
      (attribs && attribs->size()))
    {
      fprintf(outFile, ">\n");
      if (frontNote)
	frontNote->printSelf(outFile);
      if (aSimple)
	aSimple->printSelf(outFile);
      if (restrictions && restrictions->size())
	{
	  for (iter = restrictions->begin(); iter != restrictions->end();
	       iter++)
	    (*iter)->printSelf(outFile);
	}
      if (attribs)
	{
	  for (ator = attribs->begin(); ator != attribs->end(); ator++)
	    (*ator)->printSelf(outFile);
	}
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:restriction>\n", wg3Prefix);
    }
  else
    {
      fprintf(outFile, "/>\n");
      doSpaces(-INDENT, outFile);
    }
}

/********************************************************************/

XmlSimpleItem::XmlSimpleItem() {}

XmlSimpleItem::~XmlSimpleItem() {}

/********************************************************************/

XmlSimpleList::XmlSimpleList()
{
  itemType = 0;
  id = 0;
  frontNote = 0;
  aSimple = 0;
  newItemType = 0;
}

XmlSimpleList::XmlSimpleList(
  char * itemTypeIn,
  char * idIn,
  XmlAnnotation * frontNoteIn,
  XmlSimpleType * aSimpleIn)
{
  itemType = itemTypeIn;
  id = idIn;
  frontNote = frontNoteIn;
  aSimple = aSimpleIn;
  newItemType = modifyName(itemType);
}

XmlSimpleList::~XmlSimpleList() {}

void XmlSimpleList::printSelf(FILE * outFile)
{
  doSpaces(0, outFile);
  if (typePrefix)
    fprintf(outFile, "<%s:list itemType=\"%s:%s\"",
	    wg3Prefix, typePrefix, itemType);
  else
    fprintf(outFile, "<%s:list itemType=\"%s\"", wg3Prefix, itemType);
  doSpaces(+INDENT, outFile);
  if (id)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "id=\"%s\"", id);
    }
  if (frontNote || aSimple)
    {
      fprintf(outFile, ">\n");
      if (frontNote)
	frontNote->printSelf(outFile);
      if (aSimple)
	aSimple->printSelf(outFile);
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:list>\n", wg3Prefix);
    }
  else
    {
      fprintf(outFile, "/>\n");
      doSpaces(-INDENT, outFile);
    }
}

/********************************************************************/

XmlSimpleRestriction::XmlSimpleRestriction()
{
  basePrefix = 0;
  base = 0;
  id = 0;
  frontNote = 0;
  aSimple = 0;
  restrictions = 0;
  newBase = 0;
}

XmlSimpleRestriction::XmlSimpleRestriction(
  char * basePrefixIn,
  char * baseIn,
  char * idIn,
  XmlAnnotation * frontNoteIn,
  XmlSimpleType * aSimpleIn,
  std::list<XmlRestrictionType *> * restrictionsIn)
{
  basePrefix = basePrefixIn;
  base = baseIn;
  id = idIn;
  frontNote = frontNoteIn;
  aSimple = aSimpleIn;
  restrictions = restrictionsIn;
  newBase = modifyName(base);
}

XmlSimpleRestriction::~XmlSimpleRestriction() {}

void XmlSimpleRestriction::printSelf(FILE * outFile)
{
  std::list<XmlRestrictionType *>::iterator iter;
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:restriction base=", wg3Prefix);
  if (basePrefix)
    fprintf(outFile, "\"%s:%s\"", basePrefix, base);
  else
    fprintf(outFile, "\"%s\"", base);
  doSpaces(+INDENT, outFile);
  if (id)
    {
      fputc('\n', outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "id=\"%s\"", id);
    }
  if (frontNote || aSimple || (restrictions && restrictions->size()))
    {
      fprintf(outFile, ">\n");
      if (frontNote)
	frontNote->printSelf(outFile);
      if (aSimple)
	aSimple->printSelf(outFile);
      if (restrictions && restrictions->size())
	{
	  for (iter = restrictions->begin(); iter != restrictions->end();
	       iter++)
	    (*iter)->printSelf(outFile);
	}
      doSpaces(-INDENT, outFile);
      doSpaces(0, outFile);
      fprintf(outFile, "</%s:restriction>\n", wg3Prefix);
    }
  else
    {
      fprintf(outFile, "/>\n");
      doSpaces(-INDENT, outFile);
    }
}

/********************************************************************/

/* XmlSimpleType

*/

XmlSimpleType::XmlSimpleType()
{
  final = 0;
  id = 0;
  name = 0;
  frontNote = 0;
  backNotes = 0;
  item = 0;
  newName = 0;
  ccPrinted = false;
  hhPrinted = false;
  owlPrefix = 0;
}

XmlSimpleType::XmlSimpleType(
  XmlFinal * finalIn,
  char * idIn,
  char * nameIn,
  XmlAnnotation * frontNoteIn,
  std::list<XmlAnnotation *> * backNotesIn,
  XmlSimpleItem * itemIn)
{
  final = finalIn;
  id = idIn;
  name = nameIn;
  frontNote = frontNoteIn;
  backNotes = backNotesIn;
  item = itemIn;
  newName = modifyName(name);
  ccPrinted = false;
  hhPrinted = false;
  owlPrefix = 0;
}

XmlSimpleType::~XmlSimpleType() {}

void XmlSimpleType::printSelf(FILE * outFile)
{
  if (spaces == INDENT)
    fprintf(outFile, "\n");
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:simpleType", wg3Prefix);
  if (name)
    fprintf(outFile, " name=\"%s\"", name);
  if (id)
    fprintf(outFile, " id=\"%s\"", id);
  doSpaces(+INDENT, outFile);
  if (final)
    final->printSelf(outFile);
  fprintf(outFile, ">\n");
  if (frontNote)
    frontNote->printSelf(outFile);
  item->printSelf(outFile);
  doSpaces(-INDENT, outFile);
  doSpaces(0, outFile);
  fprintf(outFile, "</%s:simpleType>\n", wg3Prefix);
  if (backNotes)
    {
      std::list<XmlAnnotation *>::iterator iter;
      for (iter = backNotes->begin(); iter != backNotes->end(); iter++)
	(*iter)->printSelf(outFile);
    }
}

/********************************************************************/

XmlType::XmlType() {}

XmlType::~XmlType() {}

/********************************************************************/

/* XmlUnique

*/

XmlUnique::XmlUnique()
{
  id = 0;
  name = 0;
  frontNote = 0;
  selector = 0;
  fields = 0;
}

XmlUnique::XmlUnique(
  char * idIn,
  char * nameIn,
  std::list<std::string *> * commentsIn,
  XmlAnnotation * frontNoteIn,
  XmlSelector * selectorIn,
  std::list<XmlField *> * fieldsIn)
{
  id = idIn;
  name = nameIn;
  comments = commentsIn;
  frontNote = frontNoteIn;
  selector = selectorIn;
  fields = fieldsIn;
}

XmlUnique::~XmlUnique() {}

void XmlUnique::printSelf(FILE * outFile)
{
  std::list<XmlField *>::iterator iter;
  std::list<std::string *>::iterator ator;

  if (comments && XmlSchemaFile::printComments)
    {
      fprintf(outFile, "\n");
      for (ator = comments->begin(); ator != comments->end(); ator++)
	{
	  fprintf(outFile, "<!--");
	  fprintf(outFile, "%s", (*ator)->c_str());
	  fprintf(outFile, "-->\n");
	}
    }
  fprintf(outFile, "\n");
  doSpaces(0, outFile);
  fprintf(outFile, "<%s:unique", wg3Prefix);
  if (name)
    fprintf(outFile, " name=\"%s\">\n", name);
  else
    {
      fprintf(stderr, "unique must have name\n");
      exit(1);
    }
  doSpaces(+INDENT, outFile);
  if (frontNote)
    frontNote->printSelf(outFile);
  if (selector)
    selector->printSelf(outFile);
  else
    {
      fprintf(stderr, "unique must have selector\n");
      exit(1);
    }
  if (fields && fields->size())
    {
      for (iter = fields->begin(); iter != fields->end(); iter++)
	(*iter)->printSelf(outFile);
    }
  else
    {
      fprintf(stderr, "unique must have at least one field\n");
      exit(1);
    }
  doSpaces(-INDENT, outFile);
  doSpaces(0, outFile);
  fprintf(outFile, "</%s:unique>\n", wg3Prefix);
}

/********************************************************************/

XmlVersion::XmlVersion() {}

XmlVersion::XmlVersion(bool hasEncodingIn)
{
  hasEncoding = hasEncodingIn;
}

XmlVersion::~XmlVersion() {}

void XmlVersion::printSelf(FILE * outFile)
{
  if (hasEncoding)
    fprintf(outFile, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
  else
    fprintf(outFile, "<?xml version=\"1.0\"?>\n");
}

/********************************************************************/
