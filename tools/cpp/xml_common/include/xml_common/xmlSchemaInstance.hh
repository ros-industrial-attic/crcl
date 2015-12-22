/*********************************************************************/

#ifndef XMLSCHEMAINSTANCE_HH
#define XMLSCHEMAINSTANCE_HH

#include <stdio.h>
#include <stdarg.h>
#include <set>
#include <string>

#ifdef STRINGOUT
#define PRINTSELFDECL printSelf(char * outStr, int * N)
#define PRINTSELF printSelf(outStr, N)
#define SPACESZERO  
#define SPACESPLUS 
#define SPACESMINUS 
#define XFPRINTF xprintf(outStr, N,

#else
#define PRINTSELFDECL printSelf(FILE * outFile)
#define PRINTSELF printSelf(outFile)
#define SPACESZERO doSpaces(0, outFile)
#define SPACESPLUS doSpaces(+INDENT, outFile)
#define SPACESMINUS doSpaces(-INDENT, outFile)
#define XFPRINTF fprintf(outFile,
#endif

#define NAMESIZE 200

void xprintf(char * outString, int * N, const char * format, ...);

/*********************************************************************/

class AttributePair;
class SchemaLocation;
class XmlBoolean;
class XmlDate;
class XmlDateTime;
class XmlDecimal;
class XmlDouble;
class XmlFloat;
class XmlID;
class XmlIDREF;
class XmlInt;
class XmlInteger;
class XmlLong;
class XmlNMTOKEN;
class XmlNonNegativeInteger;
class XmlPositiveInteger;
class XmlSchemaInstanceBase;
class XmlString;
class XmlToken;
class XmlUnsignedInt;
class XmlUnsignedLong;
class XmlVersion;

/*********************************************************************/

class AttributePair
{
public:
  AttributePair();
  AttributePair(
    const char * nameIn,
    const char * valIn);
  ~AttributePair();

  std::string name;
  std::string val;
};

/*********************************************************************/

class XmlSchemaInstanceBase
{
public:
  XmlSchemaInstanceBase();
  virtual ~XmlSchemaInstanceBase();
  virtual void PRINTSELFDECL = 0;
  static void doSpaces(int change, FILE * outFile);
};

/*********************************************************************/

class SchemaLocation :
  public XmlSchemaInstanceBase
{
public:
  SchemaLocation();
  SchemaLocation(
    const char * prefixIn,
    const char * locationIn,
    bool hasNamespaceIn);
  ~SchemaLocation();
  void PRINTSELFDECL;

  std::string prefix;
  std::string location;
  bool hasNamespace;
};

/*********************************************************************/

/* class XmlAnyURI

This is a class for handling XML basic type anyURI. It is being
treated the same as the XML basic type string.

*/

class XmlAnyURI :
  public XmlSchemaInstanceBase
{
public:
  XmlAnyURI();
  XmlAnyURI(
    const char * valIn);
  ~XmlAnyURI();
  void PRINTSELFDECL;
  bool XmlAnyURIIsBad();

  std::string val;
  bool bad;
};

/*********************************************************************/

/* class XmlBoolean

This is a class for handling XML basic type boolean.

*/

class XmlBoolean :
  public XmlSchemaInstanceBase
{
public:
  XmlBoolean();
  XmlBoolean(
    const char * valIn);
  ~XmlBoolean();
  void PRINTSELFDECL;
  bool XmlBooleanIsBad();

  bool val;
  bool bad;
};

/*********************************************************************/

/* class XmlDate

This is a class for handling XML basic type date.

*/

class XmlDate :
  public XmlSchemaInstanceBase
{
public:
  XmlDate();
  XmlDate(
    const char * valIn);
  ~XmlDate();
  void PRINTSELFDECL;
  void printBad(FILE * badFile);
  bool XmlDateIsBad();

  std::string val;
  bool bad;
};

/*********************************************************************/

/* class XmlDateTime

This is a class for handling XML basic type dateTime.

*/

class XmlDateTime :
  public XmlSchemaInstanceBase
{
public:
  XmlDateTime();
  XmlDateTime(
    const char * valIn);
  ~XmlDateTime();
  void PRINTSELFDECL;
  void printBad(FILE * badFile);
  bool XmlDateTimeIsBad();

  std::string val;
  bool bad;
};

/*********************************************************************/

/* class XmlDecimal

This is a class for handling XML basic type decimal.

*/

class XmlDecimal :
  public XmlSchemaInstanceBase
{
public:
  XmlDecimal();
  XmlDecimal(
    const char * valStringIn);
  XmlDecimal(
    double valIn);
  ~XmlDecimal();
  void PRINTSELFDECL;
  void printBad(FILE * badFile);
  bool XmlDecimalIsBad();

  double val;
  bool bad;
};

/*********************************************************************/

/* class XmlDouble

This is a class for handling XML basic type double.

*/

class XmlDouble :
  public XmlSchemaInstanceBase
{
public:
  XmlDouble();
  XmlDouble(
    const char * valStringIn);
  XmlDouble(
    double valIn);
  ~XmlDouble();
  void PRINTSELFDECL;
  void printBad(FILE * badFile);
  bool XmlDoubleIsBad();

  double val;
  bool bad;
};

/*********************************************************************/

/* class XmlFloat

This is a class for handling XML basic type float.

*/

class XmlFloat :
  public XmlSchemaInstanceBase
{
public:
  XmlFloat();
  XmlFloat(
    const char * valIn);
  ~XmlFloat();
  void PRINTSELFDECL;
  void printBad(FILE * badFile);
  bool XmlFloatIsBad();

  float val;
  bool bad;
};

/*********************************************************************/

/* class XmlID

This is a class for handling XML IDs. See documentation in
xmlSchemaInstance.cc. This uses std::string to take advantage of the
built-in find and insert functions for a set of std::string. There are
no analogous built-in functions for a set of char*.

*/

class XmlID :
  public XmlSchemaInstanceBase
{
public:
  XmlID();
  XmlID(
    const char * valIn);
  ~XmlID();
  void PRINTSELFDECL;
  void printBad(FILE * badFile);
  bool XmlIDIsBad();
 
  static std::set<std::string> allIDs;
  static int lastAuto;
  static const int idSize;
  static char buffer[];
  std::string val;
  bool bad;
};

/*********************************************************************/

/* class XmlIDREF

This is a class for handling XML IDREFs. See documentation in
xmlSchemaInstance.cc. This uses std::string to take advantage of the
built-in find and insert functions for a set of std::string. There are
no analogous built-in functions for a set of char*.

*/

class XmlIDREF :
  public XmlSchemaInstanceBase
{
public:
  XmlIDREF();
  XmlIDREF(
    const char * valIn);
  ~XmlIDREF();
  void PRINTSELFDECL;
  void printBad(FILE * badFile);
  bool XmlIDREFIsBad();
  static bool idMissing();

  static std::set<std::string> allIDREFs;
  static const int idrefSize;
  std::string val;
  bool bad;
};

/*********************************************************************/

/* class XmlInt

This is a class for handling XML basic type int.

*/

class XmlInt :
  public XmlSchemaInstanceBase
{
public:
  XmlInt();
  XmlInt(
    const char * valIn);
  ~XmlInt();
  void PRINTSELFDECL;
  void printBad(FILE * badFile);
  bool XmlIntIsBad();

  int val;
  bool bad;
};

/*********************************************************************/

/* class XmlInteger

This is a class for handling XML basic type integer.
Currently, this cannot handle an arbitrarily large number (a
capability an XML integer is supposed to have).
Might change the type of val to long.

*/

class XmlInteger :
  public XmlSchemaInstanceBase
{
public:
  XmlInteger();
  XmlInteger(
    const char * valIn);
  ~XmlInteger();
  void PRINTSELFDECL;
  void printBad(FILE * badFile);
  bool XmlIntegerIsBad();

  int val;
  bool bad;
};

/*********************************************************************/

/* class XmlLong

This is a class for handling XML basic type long.

*/

class XmlLong :
  public XmlSchemaInstanceBase
{
public:
  XmlLong();
  XmlLong(
    const char * valIn);
  ~XmlLong();
  void PRINTSELFDECL;
  void printBad(FILE * badFile);
  bool XmlLongIsBad();

  long val;
  bool bad;
};

/*********************************************************************/

/* class XmlNMTOKEN

This is a class for handling XML basic type NMTOKEN.

*/

class XmlNMTOKEN :
  public XmlSchemaInstanceBase
{
public:
  XmlNMTOKEN();
  XmlNMTOKEN(
    const char * valIn);
  ~XmlNMTOKEN();
  void PRINTSELFDECL;
  void printBad(FILE * badFile);
  bool XmlNMTOKENIsBad();

  std::string val;
  bool bad;
};

/*********************************************************************/

/* class XmlNonNegativeInteger

This is a class for handling XML basic type nonNegativeInteger.
Currently, this cannot handle an arbitrarily large number (a
capability an XML integer is supposed to have).
Might change the type of val to long.

*/

class XmlNonNegativeInteger :
  public XmlSchemaInstanceBase
{
public:
  XmlNonNegativeInteger();
  XmlNonNegativeInteger(
    const char * valIn);
  ~XmlNonNegativeInteger();
  void PRINTSELFDECL;
  void printBad(FILE * badFile);
  bool XmlNonNegativeIntegerIsBad();

  int val;
  bool bad;
};

/*********************************************************************/

/* class XmlPositiveInteger

This is a class for handling XML basic type positiveInteger.
Currently, this cannot handle an arbitrarily large number (a
capability an XML integer is supposed to have).
Might change the type of val to long.

*/

class XmlPositiveInteger :
  public XmlSchemaInstanceBase
{
public:
  XmlPositiveInteger();
  XmlPositiveInteger(
    const char * valIn);
  ~XmlPositiveInteger();
  void PRINTSELFDECL;
  void printBad(FILE * badFile);
  bool XmlPositiveIntegerIsBad();

  int val;
  bool bad;
};

/*********************************************************************/

/* class XmlString

This is a class for handling XML basic type string.

*/

class XmlString :
  public XmlSchemaInstanceBase
{
public:
  XmlString();
  XmlString(
    const char * valIn);
  ~XmlString();
  void PRINTSELFDECL;
  void printBad(FILE * badFile);
  bool XmlStringIsBad();

  std::string val;
  bool bad;
};

/*********************************************************************/

/* class XmlToken

This is a class for handling XML basic type token.
A token is a string with no white space at the front or back, and
all white space substrings inside reduced to a single space.
White space is 9, 10, 13 and 32 (tab, linefeed, carriage return, space).

*/

class XmlToken :
  public XmlSchemaInstanceBase
{
public:
  XmlToken();
  XmlToken(
    const char * valIn);
  ~XmlToken();
  void PRINTSELFDECL;
  void printBad(FILE * badFile);
  bool XmlTokenIsBad();

  std::string val;
  bool bad;
};

/*********************************************************************/

/* class XmlUnsignedInt

This is a class for handling XML basic type unsignedInt.

*/

class XmlUnsignedInt :
  public XmlSchemaInstanceBase
{
public:
  XmlUnsignedInt();
  XmlUnsignedInt(
    const char * valIn);
  ~XmlUnsignedInt();
  void PRINTSELFDECL;
  void printBad(FILE * badFile);
  bool XmlUnsignedIntIsBad();

  unsigned int val;
  bool bad;
};

/*********************************************************************/

/* class XmlUnsignedLong

This is a class for handling XML basic type unsignedLong.

*/

class XmlUnsignedLong :
  public XmlSchemaInstanceBase
{
public:
  XmlUnsignedLong();
  XmlUnsignedLong(
    const char * valIn);
  ~XmlUnsignedLong();
  void PRINTSELFDECL;
  void printBad(FILE * badFile);
  bool XmlUnsignedLongIsBad();

  unsigned long val;
  bool bad;
};

/*********************************************************************/

class XmlVersion :
  public XmlSchemaInstanceBase
{
public:
  XmlVersion();
  XmlVersion(bool hasEncodingIn);
  ~XmlVersion();
  void PRINTSELFDECL;

  bool hasEncoding;
};

/*********************************************************************/

#endif // XMLSCHEMAINSTANCE_HH
