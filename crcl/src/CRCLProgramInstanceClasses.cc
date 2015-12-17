/*********************************************************************/

#include <stdio.h>             // for printf, etc.
#include <string.h>            // for strdup
#include <stdlib.h>            // for exit
#include <list>
#include <boost/regex.hpp>
#include "xml_common/xmlSchemaInstance.hh"
#include "crcl/CRCLProgramInstanceClasses.hh"

#define INDENT 2

/*********************************************************************/
/*********************************************************************/

/* class CRCLProgramFile

*/

CRCLProgramFile::CRCLProgramFile()
{
  version = 0;
  header = 0;
  CRCLProgram = 0;
}

CRCLProgramFile::CRCLProgramFile(
  XmlVersion * versionIn,
  XmlHeaderForCRCLProgram * headerIn,
  CRCLProgramType * CRCLProgramIn)
{
  version = versionIn;
  header = headerIn;
  CRCLProgram = CRCLProgramIn;
}

CRCLProgramFile::~CRCLProgramFile()
{
  delete version;
  delete header;
  delete CRCLProgram;
}

void CRCLProgramFile::PRINTSELFDECL
{
  version->PRINTSELF;
  XFPRINTF "<CRCLProgram\n");
  header->PRINTSELF;
  CRCLProgram->PRINTSELF;
  XFPRINTF "</CRCLProgram>\n");
}

/*********************************************************************/

/* class CRCLProgramType

*/

CRCLProgramType::CRCLProgramType() :
  DataThingType()
{
  InitCanon = 0;
  MiddleCommand = 0;
  EndCanon = 0;
}

CRCLProgramType::CRCLProgramType(
 XmlID * NameIn,
 InitCanonType * InitCanonIn,
 std::list<MiddleCommandType *> * MiddleCommandIn,
 EndCanonType * EndCanonIn) :
  DataThingType(
    NameIn)
{
  InitCanon = InitCanonIn;
  MiddleCommand = MiddleCommandIn;
  EndCanon = EndCanonIn;
  printTypp = false;
}

CRCLProgramType::~CRCLProgramType()
{
  delete InitCanon;
  {
    std::list<MiddleCommandType *>::iterator iter;
    for (iter = MiddleCommand->begin();
         iter != MiddleCommand->end(); iter++)
      delete *iter;
  }
  delete MiddleCommand;
  delete EndCanon;
}

void CRCLProgramType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"CRCLProgramType\"");
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
  XFPRINTF "<InitCanon");
  InitCanon->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</InitCanon>\n");
  {
    std::list<MiddleCommandType *>::iterator iter;
    for (iter = MiddleCommand->begin(); iter != MiddleCommand->end(); iter++)
      {
        SPACESZERO;
        XFPRINTF "<MiddleCommand");
        (*iter)->PRINTSELF;
        SPACESZERO;
        XFPRINTF "</MiddleCommand>\n");
      }
  }
  SPACESZERO;
  XFPRINTF "<EndCanon");
  EndCanon->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</EndCanon>\n");
  SPACESMINUS;
}

/*********************************************************************/

XmlHeaderForCRCLProgram::XmlHeaderForCRCLProgram()
{
  location = 0;
}

XmlHeaderForCRCLProgram::XmlHeaderForCRCLProgram(
  SchemaLocation * locationIn)
{
  location = locationIn;
}

XmlHeaderForCRCLProgram::~XmlHeaderForCRCLProgram()
{
  delete location;
}

void XmlHeaderForCRCLProgram::PRINTSELFDECL
{
  XFPRINTF
          "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
  location->PRINTSELF;
}

/*********************************************************************/

