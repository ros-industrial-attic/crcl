/*********************************************************************/

#include <stdio.h>             // for printf, etc.
#include <string.h>            // for strdup
#include <stdlib.h>            // for exit
#include <list>
#include <boost/regex.hpp>
#include <xml_common/xmlSchemaInstance.hh>
#include "crcl_cpp/CRCLCommandInstanceClasses.hh"

#define INDENT 2

/*********************************************************************/
/*********************************************************************/

/* class CRCLCommandInstanceFile

*/

CRCLCommandInstanceFile::CRCLCommandInstanceFile()
{
  version = 0;
  header = 0;
  CRCLCommandInstance = 0;
}

CRCLCommandInstanceFile::CRCLCommandInstanceFile(
  XmlVersion * versionIn,
  XmlHeaderForCRCLCommandInstance * headerIn,
  CRCLCommandInstanceType * CRCLCommandInstanceIn)
{
  version = versionIn;
  header = headerIn;
  CRCLCommandInstance = CRCLCommandInstanceIn;
}

CRCLCommandInstanceFile::~CRCLCommandInstanceFile()
{
  delete version;
  delete header;
  delete CRCLCommandInstance;
}

void CRCLCommandInstanceFile::PRINTSELFDECL
{
  version->PRINTSELF;
  XFPRINTF "<CRCLCommandInstance\n");
  header->PRINTSELF;
  CRCLCommandInstance->PRINTSELF;
  XFPRINTF "</CRCLCommandInstance>\n");
}

/*********************************************************************/

/* class CRCLCommandInstanceType

*/

CRCLCommandInstanceType::CRCLCommandInstanceType() :
  DataThingType()
{
  CRCLCommand = 0;
}

CRCLCommandInstanceType::CRCLCommandInstanceType(
 XmlID * NameIn,
 CRCLCommandType * CRCLCommandIn) :
  DataThingType(
    NameIn)
{
  CRCLCommand = CRCLCommandIn;
  printTypp = false;
}

CRCLCommandInstanceType::~CRCLCommandInstanceType()
{
  delete CRCLCommand;
}

void CRCLCommandInstanceType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"CRCLCommandInstanceType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  SPACESZERO;
  XFPRINTF "<CRCLCommand");
  CRCLCommand->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</CRCLCommand>\n");
  SPACESMINUS;
}

/*********************************************************************/

XmlHeaderForCRCLCommandInstance::XmlHeaderForCRCLCommandInstance()
{
  location = 0;
}

XmlHeaderForCRCLCommandInstance::XmlHeaderForCRCLCommandInstance(
  SchemaLocation * locationIn)
{
  location = locationIn;
}

XmlHeaderForCRCLCommandInstance::~XmlHeaderForCRCLCommandInstance()
{
  delete location;
}

void XmlHeaderForCRCLCommandInstance::PRINTSELFDECL
{
  XFPRINTF
          "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
  location->PRINTSELF;
}

/*********************************************************************/

