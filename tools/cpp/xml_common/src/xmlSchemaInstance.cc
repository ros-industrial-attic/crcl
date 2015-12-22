/*********************************************************************/

#include <stdio.h>              // for fprintf, etc.
#include <stdlib.h>             // for exit
#include <string.h>             // for strcmp
#include "xml_common/xmlSchemaInstance.hh"

/* 

In this copy of xmlSchemaInstance.cc, the boost regex facility is not used.
Hard-coded string format checkers are used instead.

The handling of the various types of strings should be improved to deal
correctly with the XML white space facets: preserve, replace, and collapse.
Currently these are only partially handled correctly.

*/

/*********************************************************************/

std::set<std::string> XmlID::allIDs;
int XmlID::lastAuto = 0;
const int XmlID::idSize = 100;
char XmlID::buffer[101];
std::set<std::string> XmlIDREF::allIDREFs;
const int XmlIDREF::idrefSize = 100;

/*********************************************************************/

/*

The printSelf functions here (which are used in printing XML data
files) call the xxxIsBad function before printing.  If the returned
value is true (meaning the data is bad), an error message is printed
to stderr, and this exits.

It might be a good idea to have a global "exit" flag that is checked
by the printSelf functions. If the exit flag is set to true, printSelf
will behave as described above. If the exit flag is set to false, the
printSelf will not print anything to the outFile, will print a warning
message to stderr, and will not exit.

The constructors here that take a char * argument read the argument.
If the argument is valid for the data type, val is set to the value
that is read, and bad is set to false. If the argument is not valid,
bad is set to true and a warning message is printed. The constructor
does not exit if there is an error. It is up to the caller of the
constructor to check the value of bad and decide what to do if bad
is true.

The XXXIsBad functions are necessary even though bad is public and
could be accessed directly because if a descendant type of a built in
type is defined, an XXXIsBad function will be defined for each member of
the chain of descendants, and each of those will call the XXXIsBad
function for its parent.

Although "val" cannot avoid being of the correct C++ type, "bad" may
be true if a value could not be read. Hence, if "val" is set in a
program, "bad" should first be set to false and then be set to xxxIsBad().

*/

/*********************************************************************/

void xprintf(char * outString, int * N, const char * format, ...)
{
  va_list args;

  va_start (args, format);
  *N += vsprintf(outString + *N, format, args);
  va_end(args);
}

/*********************************************************************/

/* class AttributePair

*/

AttributePair::AttributePair() {}

AttributePair::AttributePair(
 const char * nameIn,
 const char * valIn)
{
  name = nameIn; // automatic write to std::string
  val = valIn;   // automatic write to std::string
}

AttributePair::~AttributePair() {}

/*********************************************************************/

/* class SchemaLocation

*/

SchemaLocation::SchemaLocation() {}

SchemaLocation::SchemaLocation(
  const char * prefixIn,
  const char * locationIn,
  bool hasNamespaceIn)
{
  prefix = prefixIn;     // automatic write to std::string
  location = locationIn; // automatic write to std::string
  hasNamespace = hasNamespaceIn;
}

SchemaLocation::~SchemaLocation() {}

void SchemaLocation::PRINTSELFDECL
{
  if (hasNamespace)
    {
      XFPRINTF "  %s:schemaLocation=\"%s\"",
	      prefix.c_str(), location.c_str());
    }
  else
    {
      XFPRINTF "  %s:noNamespaceSchemaLocation=\"%s\"",
	      prefix.c_str(), location.c_str());
    }
}

/*********************************************************************/

/* class XmlAnyURI

This is a class for handling XML basic type anyURI. 

*/

XmlAnyURI::XmlAnyURI()
{
  val = "";
  bad = true;
}

XmlAnyURI::XmlAnyURI(
  const char * valIn)
{
  val = valIn; // automatic write to std::string
  bad = false;
}

XmlAnyURI::~XmlAnyURI() {}

bool XmlAnyURI::XmlAnyURIIsBad()
{
  return bad;
}

void XmlAnyURI::PRINTSELFDECL
{
  if (XmlAnyURIIsBad())
    {
      fprintf(stderr, "anyURI value is bad\n");
      exit(1);
    }
  XFPRINTF "%s", val.c_str());
}

/*********************************************************************/

/* class XmlBoolean

This is a class for handling XML basic type boolean.
This is not allowing spaces inside the quotes around "true" and "false".
Might make the value of this be a logical, so that an unset boolean
could have an unknown value.

*/

XmlBoolean::XmlBoolean()
{
  val = true;
  bad = true;
}

XmlBoolean::XmlBoolean(
  const char * valIn)
{
  if (strcmp(valIn, "true") == 0)
    {
      val = true;
      bad = false;
    }
  else if (strcmp(valIn, "false") == 0)
    {
      val = false;
      bad = false;
    }
  else
    {
      val = true;
      fprintf(stderr, "%s is not a valid boolean\n", valIn);
      bad = true;
    }
}

XmlBoolean::~XmlBoolean() {}

bool XmlBoolean::XmlBooleanIsBad()
{
  return bad;
}

void XmlBoolean::PRINTSELFDECL
{
  if (XmlBooleanIsBad())
    {
      fprintf(stderr, "boolean value is bad\n");
      exit(1);
    }
  XFPRINTF "%s", (val ? "true" : "false"));
}

/*********************************************************************/

/* class XmlDate

This is a class for handling XML basic type date. This is currently
not checking the requirements for a date and is allowing any string.

*/

XmlDate::XmlDate()
{
  val = "";
  bad = true;
}

XmlDate::XmlDate(
  const char * valIn)
{
  val = valIn; // automatic write to std::string
  bad = false;
}

XmlDate::~XmlDate() {}

bool XmlDate::XmlDateIsBad()
{
  return bad;
}

void XmlDate::PRINTSELFDECL
{
  if (XmlDateIsBad())
    {
      fprintf(stderr, "date value is bad\n");
      exit(1);
    }
  XFPRINTF "%s", val.c_str());
}

void XmlDate::printBad(FILE * badFile)
{
  fprintf(badFile, "%s", val.c_str());
}

/*********************************************************************/

/* class XmlDateTime

This is a class for handling XML basic type dateTime. This is currently
not checking the requirements for a dateTime and is allowing any string.

*/

XmlDateTime::XmlDateTime()
{
  val = "";
  bad = true;
}

XmlDateTime::XmlDateTime(
  const char * valIn)
{
  val = valIn; // automatic write to std::string
  bad = false;
}

XmlDateTime::~XmlDateTime() {}

bool XmlDateTime::XmlDateTimeIsBad()
{
  return bad;
}

void XmlDateTime::PRINTSELFDECL
{
  if (XmlDateTimeIsBad())
    {
      fprintf(stderr, "dateTime value is bad\n");
      exit(1);
    }
  XFPRINTF "%s", val.c_str());
}

void XmlDateTime::printBad(FILE * badFile)
{
  fprintf(badFile, "%s", val.c_str());
}

/*********************************************************************/

/* class XmlDecimal

This is a class for handling XML basic type decimal.
Might add fussier checks similar to the ones for unsignedInt.

*/

XmlDecimal::XmlDecimal()
{
  val = 0;
  bad = true;
}

XmlDecimal::XmlDecimal(
  const char * valStringIn)
{
  if (sscanf(valStringIn, "%lf", &val) == 1)
    {
      bad = false;
    }
  else
    {
      fprintf(stderr, "%s is not a valid decimal\n", valStringIn);
      bad = true;
    }
}

XmlDecimal::XmlDecimal(
  double valIn)
{
  val = valIn;
  bad = false;
}

XmlDecimal::~XmlDecimal() {}

bool XmlDecimal::XmlDecimalIsBad()
{
  return bad;
}

void XmlDecimal::PRINTSELFDECL
{
  if (XmlDecimalIsBad())
    {
      fprintf(stderr, "decimal value is bad\n");
      exit(1);
    }
  XFPRINTF "%f", val);
}

void XmlDecimal::printBad(FILE * badFile)
{
  fprintf(badFile, "%f", val);
}

/*********************************************************************/

/* class XmlDouble

This is a class for handling XML basic type double.
Might add fussier checks similar to the ones for unsignedInt.

*/

XmlDouble::XmlDouble()
{
  val = 0;
  bad = true;
}

XmlDouble::XmlDouble(
  const char * valStringIn)
{
  if (sscanf(valStringIn, "%lf", &val) == 1)
    {
      bad = false;
    }
  else
    {
      fprintf(stderr, "%s is not a valid double\n", valStringIn);
      bad = true;
    }
}

XmlDouble::XmlDouble(
  double valIn)
{
  val = valIn;
  bad = false;
}

XmlDouble::~XmlDouble() {}

bool XmlDouble::XmlDoubleIsBad()
{
  return bad;
}

void XmlDouble::PRINTSELFDECL
{
  if (XmlDoubleIsBad())
    {
      fprintf(stderr, "double value is bad\n");
      exit(1);
    }
  XFPRINTF "%f", val);
}

void XmlDouble::printBad(FILE * badFile)
{
  fprintf(badFile, "%f", val);
}

/*********************************************************************/

/* class XmlFloat

This is a class for handling XML basic type float.
Might add fussier checks similar to the ones for unsignedInt.


*/

XmlFloat::XmlFloat()
{
  val = 0.0;
  bad = true;
}

XmlFloat::XmlFloat(
  const char * valIn)
{
  if (sscanf(valIn, "%f", &val) == 1)
    {
      bad = false;
    }
  else
    {
      fprintf(stderr, "%s is not a valid float\n", valIn);
      bad = true;
    }
}

XmlFloat::~XmlFloat() {}

bool XmlFloat::XmlFloatIsBad()
{
  return bad;
}

void XmlFloat::PRINTSELFDECL
{
  if (XmlFloatIsBad())
    {
      fprintf(stderr, "float value is bad\n");
      exit(1);
    }
  XFPRINTF "%f", val);
}

void XmlFloat::printBad(FILE * badFile)
{
  fprintf(badFile, "%f", val);
}

/*********************************************************************/

/* class XmlID

This is a class for handling XML IDs, which are required to be unique
in any one instance file.

The allIDs, lastAuto, idSize, and buffer static attributes are 
defined at the beginning of this file.

The constructor that takes no arguments assigns a unique value to the
ID of the form autoIdN, where the N at the end is an integer. The
lastAuto attribute keeps the next value of N to use. Before using
that value, however, it is checked whether autoIdN already exists.
If so, N is increased by 1 and the process repeats. This is currently
not checking that all integers have been used up. The "bad" attribute
is set to false.

The constructor that takes the argument valIn checks the length of valIn.
If is is greater than idSize, val is set to "0", and bad is set to true.
Bad is set to true also if the valIn is already used in another XmlID

The allIDs set keeps track of all strings used in instances of
XmlID. Every time a new XmlID is created, its val is recorded in
allIDs. When the destructor is called, the string in the XmlID is
removed from allIDs.

The buffer is a place for constructing a string.

The bad attribute is set to false if the XmlID is OK. It is set to true
if the constructor with the valIn argument contains a value that is too
long (larger than idSize) or a value that is already used for another
XmlID.

It is expected that any program using XmlIDs will check the bad attribute
after calling the constructor with the valIn argument.

The limitation on the size of IDs is probably not necessary, but it may be
useful to the find and insert operations.

A valid ID may have leading and/or trailing white space, but
otherwise, the first non-space character must be a member of [a-zA-Z_]
and other characters must be a member of [a-zA-Z0-9_.-].

This is handling white space correctly. Leading white space (tab, space,
10, 13) and trailing white space is removed.

*/

XmlID::XmlID()
{
  for (lastAuto++; ; lastAuto++)
    {
      snprintf(buffer, idSize, "autoId%d", lastAuto);
      if (allIDs.find(buffer) == allIDs.end())
	break;
    }
  val = buffer;
  allIDs.insert(val);
  bad = false;
}

XmlID::XmlID(
 const char * valIn)
{
  int n;
  int start;
  int length;
  static char buffer[NAMESIZE];

  length = strlen(valIn);
  if (length > NAMESIZE)
    {
      fprintf(stderr, "ID %s is too long", valIn);
      exit(1);
    }
  strncpy(buffer, valIn, NAMESIZE);
  for (start=0; ((buffer[start] == ' ') || (buffer[start] == '\t') ||
                 (buffer[start] == 10)  || (buffer[start] == 13)); start++);
  for (n = length - 1; ((buffer[n] == ' ') || (buffer[n] == '\t') ||
		        (buffer[n] == 10)  || (buffer[n] == 13)); n--);
  buffer[n+1] = 0;
  if ((int)strlen(buffer + start) > idSize)
    {
      fprintf(stderr,
	      "the ID %s is too long\n", buffer + start);
      val = "";
      bad = true;
      return;
    }
  n = start;
  if ((buffer[n] != '_') &&
      ((buffer[n] < 'a') || (buffer[n] > 'z')) &&
      ((buffer[n] < 'A') || (buffer[n] > 'Z')))
    { // first non-white character is not allowed
      val = "";
      bad = true;
      fprintf(stderr, "%s is not a valid ID\n", buffer + start);
      return;
    }
  for (n++; ((buffer[n] == '_') ||
	     (buffer[n] == '.') ||
	     (buffer[n] == '-') ||
	     ((buffer[n] >= 'a') && (buffer[n] <= 'z')) ||
	     ((buffer[n] >= 'A') && (buffer[n] <= 'Z')) ||
	     ((buffer[n] >= '0') && (buffer[n] <= '9'))); n++);
  if (buffer[n])
    { // buffer[n] should be 0 but isn't
      val = "";
      bad = true;
      fprintf(stderr, "%s is not a valid ID\n", buffer + start);
      return;
    }
   else
    {
      val = buffer + start; // automatic write to std::string
      if (allIDs.find(val) != allIDs.end())
	{
	  fprintf(stderr, "ID value %s is already in use\n", val.c_str());
	  val = "";
	  bad = true;
	}
      else
	{ // everything OK
	  bad = false;
	  allIDs.insert(val);
	}
    }
}

XmlID::~XmlID()
{
  allIDs.erase(val);
}

bool XmlID::XmlIDIsBad()
{
  int n;

  if (bad)
    return true;
  else if ((int)val.size() > idSize)
    return true;
  else if (allIDs.find(val) == allIDs.end())
    return true;
  else if ((val[0] != '_') &&
	   ((val[0] < 'a') || (val[0] > 'z')) &&
	   ((val[0] < 'A') || (val[0] > 'Z')))
    { // first non-space non-tab is not allowed
      val = "";
      return true;
    }
  for (n=1; ((val[n] == '_') ||
	     (val[n] == '.') ||
	     (val[n] == '-') ||
	     ((val[n] >= 'a') && (val[n] <= 'z')) ||
	     ((val[n] >= 'A') && (val[n] <= 'Z')) ||
	     ((val[n] >= '0') && (val[n] <= '9'))); n++);
  for ( ; ((val[n] == ' ') || (val[n] == '\t')); n++);
  if (val[n])
    { // val[n] should be 0 but isn't
      val = "";
      return true;
    }
  return false;
}

void XmlID::PRINTSELFDECL
{
  if (XmlIDIsBad())
    {
      fprintf(stderr, "ID %s is bad\n", val.c_str());
      exit(1);
    }
  XFPRINTF "%s", val.c_str());
}

void XmlID::printBad(FILE * badFile)
{
  fprintf(badFile, "%s", val.c_str());
}

/*********************************************************************/

/* class XmlIDREF

This is a class for handling XML IDREFs, which are required to match
XML IDs with the same val. Checking IDREFs agains IDs must be postponed
until an entire data file has been read, so this saves all the IDREFs
in the allIDREFs. The data file parser must do the checking.

Every time a new XmlIDREF is created, its val is recorded in
allIDREFs. When the destructor is called, the string in the XmlIDREF is
removed from allIDREFs.

The allIDREFs and idrefSize static attributes are defined at the
beginning of this file.

The bad attribute is set to false if the XmlIDREF is OK. It is set to true
if the constructor with the valIn argument contains a value that is too
long (larger than idrefSize).

It is expected that any program using XmlIDREFs will check the bad attribute
after calling the constructor with the valIn argument.

The limitation on the size of IDREFs is probably not necessary, but it may be
useful to the find and insert operations.

*/

XmlIDREF::XmlIDREF()
{
  val = "";
  bad = true;
}

XmlIDREF::XmlIDREF(
  const char * valIn)
{
  int n;
  int start;
  int length;
  static char buffer[NAMESIZE];

  length = strlen(valIn);
  if (length > NAMESIZE)
    {
      fprintf(stderr, "IDREF %s is too long", valIn);
      exit(1);
    }
  strncpy(buffer, valIn, NAMESIZE);
  for (start=0; ((buffer[start] == ' ') || (buffer[start] == '\t') ||
                 (buffer[start] == 10)  || (buffer[start] == 13)); start++);
  for (n = length - 1; ((buffer[n] == ' ') || (buffer[n] == '\t') ||
		        (buffer[n] == 10)  || (buffer[n] == 13)); n--);
  buffer[n+1] = 0;
  if ((int)strlen(buffer + start) > idrefSize)
    {
      fprintf(stderr,
	      "the IDREF %s is too long\n", buffer + start);
      val = "";
      bad = true;
      return;
    }
  n = start;
  if ((buffer[n] != '_') &&
      ((buffer[n] < 'a') || (buffer[n] > 'z')) &&
      ((buffer[n] < 'A') || (buffer[n] > 'Z')))
    { // first non-white character is not allowed
      val = "";
      bad = true;
      fprintf(stderr, "%s is not a valid IDREF\n", buffer + start);
      return;
    }
  for (n++; ((buffer[n] == '_') ||
	     (buffer[n] == '.') ||
	     (buffer[n] == '-') ||
	     ((buffer[n] >= 'a') && (buffer[n] <= 'z')) ||
	     ((buffer[n] >= 'A') && (buffer[n] <= 'Z')) ||
	     ((buffer[n] >= '0') && (buffer[n] <= '9'))); n++);
  if (buffer[n])
    { // buffer[n] should be 0 but isn't
      val = "";
      bad = true;
      fprintf(stderr, "%s is not a valid IDREF\n", buffer + start);
      return;
    }
  val = buffer + start; // automatic write to std::string
  bad = false;
  allIDREFs.insert(val);
}

XmlIDREF::~XmlIDREF()
{
  allIDREFs.erase(val);
}

bool XmlIDREF::XmlIDREFIsBad()
{
  int n;

  if (bad)
    return true;
  else if ((int)val.size() > idrefSize)
    return true;
  else if (allIDREFs.find(val) == allIDREFs.end())
    return true;
  else if ((val[0] != '_') &&
	   ((val[0] < 'a') || (val[0] > 'z')) &&
	   ((val[0] < 'A') || (val[0] > 'Z')))
    { // first non-space non-tab is not allowed
      val = "";
      return true;
    }
  for (n=1; ((val[n] == '_') ||
	     (val[n] == '.') ||
	     (val[n] == '-') ||
	     ((val[n] >= 'a') && (val[n] <= 'z')) ||
	     ((val[n] >= 'A') && (val[n] <= 'Z')) ||
	     ((val[n] >= '0') && (val[n] <= '9'))); n++);
  for ( ; ((val[n] == ' ') || (val[n] == '\t')); n++);
  if (val[n])
    { // val[n] should be 0 but isn't
      val = "";
      return true;
    }
  return false;
}

void XmlIDREF::PRINTSELFDECL
{
  if (XmlIDREFIsBad())
    {
      fprintf(stderr, "IDREF %s is bad\n", val.c_str());
      exit(1);
    }
  XFPRINTF "%s", val.c_str());
}

void XmlIDREF::printBad(FILE * badFile)
{
  fprintf(badFile, "%s", val.c_str());
}

bool XmlIDREF::idMissing()
{
  std::set<std::string>::iterator iter;
  for (iter = allIDREFs.begin(); iter != allIDREFs.end(); iter++)
    {
      if (XmlID::allIDs.find(*iter) == XmlID::allIDs.end())
	{
	  fprintf(stderr, "referenced xs:ID %s is not defined\n",
		  iter->c_str());
	  return true;
	}
    }
  return false;
}

/*********************************************************************/

/* class XmlInt

This is a class for handling XML basic type int.

The constructor that takes an argument checks that valIn consists of
1. a sequence of zero or more spaces and tabs followed by
2. an optional plus or minus sign followed by
3. a string of one or more digits followed by
4. a sequence of zero or more spaces and tabs
boost::regex("^[ \t]*[+-]?[0-9]+[ \t]*$"

If that checks, it is checked that sscanf can read the number.
If all checks pass, bad is set to false and val is set to the number read.
If any check fails, bad is set to true and val is set to 0.

*/

XmlInt::XmlInt()
{
  val = 0;
  bad = true;
}

XmlInt::XmlInt(
  const char * valIn)
{
  int n;

  for (n=0; ((valIn[n] == ' ') || (valIn[n] == '\t')); n++);
  if ((valIn[n] == '+') || (valIn[n] == '-'))
    n++;
  if ((valIn[n] < '0') || (valIn[n] > '9'))
    { // first non-space non-tab non-one-sign is not a digit
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid int\n", valIn);
      return;
    }
  for (n++ ; ((valIn[n] >= '0') && (valIn[n] <= '9')); n++);
  for ( ; ((valIn[n] == ' ') || (valIn[n] == '\t')); n++);
  if (valIn[n])
    { // valIn[n] should be 0 but isn't
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid int\n", valIn);
      return;
    }
  else if (sscanf(valIn, "%d", &val) == 1)
    {
      bad = false;
    }
  else
    {
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid int\n", valIn);
    }
}

XmlInt::~XmlInt() {}

bool XmlInt::XmlIntIsBad()
{
  return bad;
}

void XmlInt::PRINTSELFDECL
{
  if (XmlIntIsBad())
    {
      fprintf(stderr, "int value is bad\n");
      exit(1);
    }
  XFPRINTF "%d", val);
}

void XmlInt::printBad(FILE * badFile)
{
  fprintf(badFile, "%d", val);
}

/*********************************************************************/

/* class XmlInteger

This is a class for handling XML basic type integer.

The constructor that takes an argument checks that valIn consists of
1. a sequence of zero or more spaces and tabs followed by
2. an optional plus or minus sign followed by
3. a string of one or more digits followed by
4. a sequence of zero or more spaces and tabs
boost::regex("^[ \t]*[+-]?[0-9]+[ \t]*$"

If that checks, it is checked that sscanf can read the number.
If all checks pass, bad is set to false and val is set to the number read.
If any check fails, bad is set to true and val is set to 0.

*/

XmlInteger::XmlInteger()
{
  val = 0;
  bad = true;
}

XmlInteger::XmlInteger(
  const char * valIn)
{
  int n;

  for (n=0; ((valIn[n] == ' ') || (valIn[n] == '\t')); n++);
  if ((valIn[n] == '+') || (valIn[n] == '-'))
    n++;
  if ((valIn[n] < '0') || (valIn[n] > '9'))
    { // first non-space non-tab non-one-sign is not a digit
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid integer\n", valIn);
      return;
    }
  for (n++ ; ((valIn[n] >= '0') && (valIn[n] <= '9')); n++);
  for ( ; valIn[n] && ((valIn[n] == ' ') || (valIn[n] == '\t')); n++);
  if (valIn[n])
    { // valIn[n] should be 0 but isn't
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid integer\n", valIn);
      return;
    }
  else if (sscanf(valIn, "%d", &val) == 1)
    {
      bad = false;
    }
  else
    {
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid integer\n", valIn);
    }
}

XmlInteger::~XmlInteger() {}

bool XmlInteger::XmlIntegerIsBad()
{
  return bad;
}

void XmlInteger::PRINTSELFDECL
{
  if (XmlIntegerIsBad())
    {
      fprintf(stderr, "integer value is bad\n");
      exit(1);
    }
  XFPRINTF "%d", val);
}

void XmlInteger::printBad(FILE * badFile)
{
  fprintf(badFile, "%d", val);
}

/*********************************************************************/

/* class XmlLong

This is a class for handling XML basic type long.

The constructor that takes an argument checks that valIn consists of
1. a sequence of zero or more spaces and tabs followed by
2. an optional plus or minus sign followed by
3. a string of one or more digits followed by
4. a sequence of zero or more spaces and tabs
boost::regex("^[ \t]*[+-]?[0-9]+[ \t]*$"

If that checks, it is checked that sscanf can read the number.
If all checks pass, bad is set to false and val is set to the number read.
If any check fails, bad is set to true and val is set to 0.

*/

XmlLong::XmlLong()
{
  val = 0;
  bad = true;
}

XmlLong::XmlLong(
  const char * valIn)
{
  int n;

  for (n=0; ((valIn[n] == ' ') || (valIn[n] == '\t')); n++);
  if ((valIn[n] == '+') || (valIn[n] == '-'))
    n++;
  if ((valIn[n] < '0') || (valIn[n] > '9'))
    { // first non-space non-tab non-one-sign is not a digit
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid long\n", valIn);
      return;
    }
  for (n++ ; ((valIn[n] >= '0') && (valIn[n] <= '9')); n++);
  for ( ; ((valIn[n] == ' ') || (valIn[n] == '\t')); n++);
  if (valIn[n])
    { // valIn[n] should be 0 but isn't
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid long\n", valIn);
      return;
    }
  else if (sscanf(valIn, "%ld", &val) == 1)
    {
      bad = false;
    }
  else
    {
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid long\n", valIn);
    }
}

XmlLong::~XmlLong() {}

bool XmlLong::XmlLongIsBad()
{
  return bad;
}

void XmlLong::PRINTSELFDECL
{
  if (XmlLongIsBad())
    {
      fprintf(stderr, "long value is bad\n");
      exit(1);
    }
  XFPRINTF "%ld", val);
}

void XmlLong::printBad(FILE * badFile)
{
  fprintf(badFile, "%ld", val);
}

/*********************************************************************/

/* class XmlNMTOKEN

This is a class for handling XML basic type NMTOKEN.

The constructor that takes an argument checks that valIn a valid NMTOKEN.
If so, val is set to valIn and bad is set to false.
If not, val is set to the empty string and bad is set to true.

A valid NMTOKEN may have leading and/or trailing spaces and tabs, but
otherwise, it must be a member of [a-zA-Z0-9:_.-]+.
boost::regex("^[a-zA-Z0-9:_.-]+$"

*/

XmlNMTOKEN::XmlNMTOKEN()
{
  val = "";
  bad = true;
}

XmlNMTOKEN::XmlNMTOKEN(
  const char * valIn)
{
  int n;

  for (n=0; ((valIn[n] == ' ') || (valIn[n] == '\t')); n++);
  if ((valIn[n] != ':') &&
      (valIn[n] != '_') &&
      (valIn[n] != '.') &&
      (valIn[n] != '-') &&
      ((valIn[n] < 'a') || (valIn[n] > 'z')) &&
      ((valIn[n] < 'A') || (valIn[n] > 'Z')) &&
      ((valIn[n] < '0') || (valIn[n] > '9')))
    { // first non-space non-tab is not allowed
      val = "";
      bad = true;
      fprintf(stderr, "%s is not a valid NMTOKEN\n", valIn);
      return;
    }
  for (n++; ((valIn[n] == ':') ||
	     (valIn[n] == '_') ||
	     (valIn[n] == '.') ||
	     (valIn[n] == '-') ||
	     ((valIn[n] >= 'a') && (valIn[n] <= 'z')) ||
	     ((valIn[n] >= 'A') && (valIn[n] <= 'Z')) ||
	     ((valIn[n] >= '0') && (valIn[n] <= '9'))); n++);
  for ( ; ((valIn[n] == ' ') || (valIn[n] == '\t')); n++);
  if (valIn[n])
    { // valIn[n] should be 0 but isn't
      val = "";
      bad = true;
      fprintf(stderr, "%s is not a valid NMTOKEN\n", valIn);
      return;
    }
  val = valIn; // automatic write to std::string
  bad = false;
}

XmlNMTOKEN::~XmlNMTOKEN() {}

bool XmlNMTOKEN::XmlNMTOKENIsBad()
{
  int n;
  const char * valIn;
  
  if (bad)
    return true;
  valIn = val.c_str();
  for (n=0; ((valIn[n] == ':') ||
	     (valIn[n] == '_') ||
	     (valIn[n] == '.') ||
	     (valIn[n] == '-') ||
	     ((valIn[n] >= 'a') && (valIn[n] <= 'z')) ||
	     ((valIn[n] >= 'A') && (valIn[n] <= 'Z')) ||
	     ((valIn[n] >= '0') && (valIn[n] <= '9'))); n++);
  if ((n == 0) || valIn[n])
    return true;
  return false;
}

void XmlNMTOKEN::PRINTSELFDECL
{
  if (XmlNMTOKENIsBad())
    {
      fprintf(stderr, "NMTOKEN value %s is bad\n", val.c_str());
      exit(1);
    }
  XFPRINTF "%s", val.c_str());
}

void XmlNMTOKEN::printBad(FILE * badFile)
{
  fprintf(badFile, "%s", val.c_str());
}

/*********************************************************************/

/* class XmlNonNegativeInteger

This is a class for handling XML basic type nonNegativeInteger.

The constructor that takes an argument checks that valIn consists of
1. a sequence of zero or more spaces and tabs followed by
2. an optional plus or minus sign followed by
3. a string of one or more digits followed by
4. a sequence of zero or more  spaces and tabs
boost::regex("^[ \t]*[+-]?[0-9]+[ \t]*$"

If that checks, it is checked that sscanf can read the number.
If that checks, it is checked that the value is not negative.
If all checks pass, bad is set to false and val is set to the number read.
If any check fails, bad is set to true and val is set to 0.

*/

XmlNonNegativeInteger::XmlNonNegativeInteger()
{
  val = 0;
  bad = true;
}

XmlNonNegativeInteger::XmlNonNegativeInteger(
  const char * valIn)
{
  int n;

  for (n=0; ((valIn[n] == ' ') || (valIn[n] == '\t')); n++);
  if ((valIn[n] == '+') || (valIn[n] == '-'))
    n++;
  if ((valIn[n] < '0') || (valIn[n] > '9'))
    { // first non-space non-tab non-one-sign is not a digit
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid nonNegativeInteger\n", valIn);
      return;
    }
  for (n++ ; ((valIn[n] >= '0') && (valIn[n] <= '9')); n++);
  for ( ; ((valIn[n] == ' ') || (valIn[n] == '\t')); n++);
  if (valIn[n])
    { // valIn[n] should be 0 but isn't
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid nonNegativeInteger\n", valIn);
      return;
    }
  else if (sscanf(valIn, "%d", &val) == 1)
    {
      if (val > -1)
	bad = false;
      else
	{
	  val = 0;
	  bad = true;
	  fprintf(stderr, "%s is not a valid nonNegativeInteger\n", valIn);
	}
    }
  else
    {
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid nonNegativeInteger\n", valIn);
    }
}

XmlNonNegativeInteger::~XmlNonNegativeInteger() {}

bool XmlNonNegativeInteger::XmlNonNegativeIntegerIsBad()
{
  if (bad)
    return true;
  else if (val < 0)
    return true;
  else
    return false;
}

void XmlNonNegativeInteger::PRINTSELFDECL
{
  if (XmlNonNegativeIntegerIsBad())
    {
      fprintf(stderr, "nonNegativeInteger value is bad\n");
      exit(1);
    }
  XFPRINTF "%d", val);
}

void XmlNonNegativeInteger::printBad(FILE * badFile)
{
  fprintf(badFile, "%d", val);
}

/*********************************************************************/

/* class XmlPositiveInteger

This is a class for handling XML basic type positiveInteger.

The constructor that takes an argument checks that valIn consists of
1. a sequence of zero or more spaces and tabs followed by
2. an optional plus or minus sign followed by
3. a string of one or more digits followed by
4. a sequence of zero or more spaces and tabs
boost::regex("^[ \t]*[+-]?[0-9]+[ \t]*$"

If that checks, it is checked that sscanf can read the number.
If that checks, it is checked that the value is positive.
If all checks pass, bad is set to false and val is set to the number read.
If any check fails, bad is set to true and val is set to 0.

*/

XmlPositiveInteger::XmlPositiveInteger()
{
  val = 0;
  bad = true;
}

XmlPositiveInteger::XmlPositiveInteger(
  const char * valIn)
{
  int n;

  for (n=0; ((valIn[n] == ' ') || (valIn[n] == '\t')); n++);
  if ((valIn[n] == '+') || (valIn[n] == '-'))
    n++;
  if ((valIn[n] < '0') || (valIn[n] > '9'))
    { // first non-space non-tab non-one-sign is not a digit
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid positiveInteger\n", valIn);
      return;
    }
  for (n++ ; ((valIn[n] >= '0') && (valIn[n] <= '9')); n++);
  for ( ; ((valIn[n] == ' ') || (valIn[n] == '\t')); n++);
  if (valIn[n])
    { // valIn[n] should be 0 but isn't
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid positiveInteger\n", valIn);
      return;
    }
  else if (sscanf(valIn, "%d", &val) == 1)
    {
      if (val > 0)
	bad = false;
      else
	{
	  val = 0;
	  bad = true;
	  fprintf(stderr, "%s is not a valid positiveInteger\n", valIn);
	}
    }
  else
    {
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid positiveInteger\n", valIn);
    }
}

XmlPositiveInteger::~XmlPositiveInteger() {}

bool XmlPositiveInteger::XmlPositiveIntegerIsBad()
{
  if (bad)
    return true;
  else if (val < 1)
    return true;
  else
    return false;
}

void XmlPositiveInteger::PRINTSELFDECL
{
  if (XmlPositiveIntegerIsBad())
    {
      fprintf(stderr, "positiveInteger value is bad\n");
      exit(1);
    }
  XFPRINTF "%d", val);
}

void XmlPositiveInteger::printBad(FILE * badFile)
{
  fprintf(badFile, "%d", val);
}

/*********************************************************************/

/* class XmlSchemaInstanceBase

*/

XmlSchemaInstanceBase::XmlSchemaInstanceBase() {}

XmlSchemaInstanceBase::~XmlSchemaInstanceBase() {}

void XmlSchemaInstanceBase::doSpaces(int change, FILE * outFile)
{
  static int spaces = 0;
  static int n;

  if (change)
    spaces += change;
  else
    {
      for (n = 0; n < spaces; n++)
        fputc(' ', outFile);
    }
}

/*********************************************************************/

/* class XmlString

This is a class for handling XML basic type string. 

*/

XmlString::XmlString()
{
  val = "";
  bad = true;
}

XmlString::XmlString(
  const char * valIn)
{
  val = valIn; // automatic write to std::string
  bad = false;
}

XmlString::~XmlString() {}

bool XmlString::XmlStringIsBad()
{
  return bad;
}

void XmlString::PRINTSELFDECL
{
  if (XmlStringIsBad())
    {
      fprintf(stderr, "string value is bad\n");
      exit(1);
    }
  XFPRINTF "%s", val.c_str());
}

void XmlString::printBad(FILE * badFile)
{
  fprintf(badFile, "%s", val.c_str());
}

/*********************************************************************/

/* class XmlToken

This is a class for handling XML basic type token.

In the constructor that takes a char array, white space at the front
and back of the char array is removed if there is any, and substrings
of white space inside the char array are replaced by a single space.

*/

XmlToken::XmlToken()
{
  val = "";
  bad = true;
}

XmlToken::XmlToken(
  const char * valIn)
{
  char * buffer;
  int n;
  int m;

  buffer = new char[strlen(valIn) + 1];
  strcpy(buffer, valIn);
  for (n = 0; ((buffer[n] == 9)  || (buffer[n] == 10) ||
	       (buffer[n] == 13) || (buffer[n] == 32)); n++);
  for (m = 0; buffer[n]; n++)
    {
      if ((buffer[n] == 9)  || (buffer[n] == 10) ||
	  (buffer[n] == 13) || (buffer[n] == 32))
	{
	  buffer[m++] = 32;
	  for (n++; ((buffer[n] == 9)  || (buffer[n] == 10) ||
		  (buffer[n] == 13) || (buffer[n] == 32)); n++);
	  n--;
	}
      else
	{
	  buffer[m++] = buffer[n];
	}
    }
  buffer[m] = 0;
  if (buffer[m-1] == 32)
    (buffer[m-1] = 0);
  val = buffer; // automatic write to std::string
  bad = false;
  delete [] buffer;
}

XmlToken::~XmlToken() {}

bool XmlToken::XmlTokenIsBad()
{
  return bad;
}

void XmlToken::PRINTSELFDECL
{
  if (XmlTokenIsBad())
    {
      fprintf(stderr, "token value is bad\n");
      exit(1);
    }
  XFPRINTF "%s", val.c_str());
}

void XmlToken::printBad(FILE * badFile)
{
  fprintf(badFile, "%s", val.c_str());
}

/*********************************************************************/

/* class XmlUnsignedInt

This is a class for handling XML basic type unsignedInt.

The constructor that takes an argument checks that valIn consists of
1. a sequence of zero or more spaces and tabs followed by
2. a string of one or more digits followed by
3. a sequence of zero or more spaces and tabs
boost::regex("^[ \t]*[0-9]+[ \t]*$"

If that checks, sscanf is called to read the number.
If all checks pass, bad is set to false and val is set to the number read.
If any check fails, bad is set to true and val is set to 0.

*/

XmlUnsignedInt::XmlUnsignedInt()
{
  val = 0;
  bad = true;
}

XmlUnsignedInt::XmlUnsignedInt(
  const char * valIn)
{
  int n;

  for (n=0; ((valIn[n] == ' ') || (valIn[n] == '\t')); n++);
  if ((valIn[n] < '0') || (valIn[n] > '9'))
    { // first non-space non-tab is not a digit
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid unsignedInt\n", valIn);
      return;
    }
  for (n++ ; ((valIn[n] >= '0') && (valIn[n] <= '9')); n++);
  for ( ; ((valIn[n] == ' ') || (valIn[n] == '\t')); n++);
  if (valIn[n])
    { // valIn[n] should be 0 but isn't
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid unsignedInt\n", valIn);
      return;
    }
  else if (sscanf(valIn, "%d", &val) == 1)
    {
      bad = false;
    }
  else
    {
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid unsignedInt\n", valIn);
    }
}

XmlUnsignedInt::~XmlUnsignedInt() {}

bool XmlUnsignedInt::XmlUnsignedIntIsBad()
{
  return bad;
}

void XmlUnsignedInt::PRINTSELFDECL
{
  if (XmlUnsignedIntIsBad())
    {
      fprintf(stderr, "unsignedInt value is bad\n");
      exit(1);
    }
  XFPRINTF "%u", val);
}

void XmlUnsignedInt::printBad(FILE * badFile)
{
  fprintf(badFile, "%u", val);
}

/*********************************************************************/

/* class XmlUnsignedLong

This is a class for handling XML basic type unsignedLong.

The constructor that takes an argument checks that valIn consists of
1. a sequence of zero or more spaces and tabs followed by
2. a string of one or more digits followed by
3. a sequence of zero or more spaces and tabs
boost::regex("^[ \t]*[0-9]+[ \t]*$"

If that checks, sscanf is called to read the number.
If all checks pass, bad is set to false and val is set to the number read.
If any check fails, bad is set to true and val is set to 0.

*/

XmlUnsignedLong::XmlUnsignedLong()
{
  val = 0;
  bad = true;
}

XmlUnsignedLong::XmlUnsignedLong(
  const char * valIn)
{
  int n;

  for (n=0; ((valIn[n] == ' ') || (valIn[n] == '\t')); n++);
  if ((valIn[n] < '0') || (valIn[n] > '9'))
    { // first non-space non-tab is not a digit
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid unsignedLong\n", valIn);
      return;
    }
  for (n++ ; ((valIn[n] >= '0') && (valIn[n] <= '9')); n++);
  for ( ; ((valIn[n] == ' ') || (valIn[n] == '\t')); n++);
  if (valIn[n])
    { // valIn[n] should be 0 but isn't
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid unsignedLong\n", valIn);
      return;
    }
  else if (sscanf(valIn, "%ld", &val) == 1)
    {
      bad = false;
    }
  else
    {
      val = 0;
      bad = true;
      fprintf(stderr, "%s is not a valid unsignedLong\n", valIn);
    }
}

XmlUnsignedLong::~XmlUnsignedLong() {}

bool XmlUnsignedLong::XmlUnsignedLongIsBad()
{
  return bad;
}

void XmlUnsignedLong::PRINTSELFDECL
{
  if (XmlUnsignedLongIsBad())
    {
      fprintf(stderr, "unsignedLong value is bad\n");
      exit(1);
    }
  XFPRINTF "%lu", val);
}

void XmlUnsignedLong::printBad(FILE * badFile)
{
  fprintf(badFile, "%lu", val);
}

/*********************************************************************/

/* class XmlVersion

*/

XmlVersion::XmlVersion() {}

XmlVersion::XmlVersion(bool hasEncodingIn)
{
  hasEncoding = hasEncodingIn;
}

XmlVersion::~XmlVersion() {}

void XmlVersion::PRINTSELFDECL
{
  if (hasEncoding)
    XFPRINTF "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
  else
    XFPRINTF "<?xml version=\"1.0\"?>\n");
}

/*********************************************************************/

