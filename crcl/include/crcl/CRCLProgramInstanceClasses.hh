/*********************************************************************/

#ifndef CRCLPROGRAMINSTANCE_HH
#define CRCLPROGRAMINSTANCE_HH
#include <stdio.h>
#include <list>
#include <xml_common/xmlSchemaInstance.hh>
#include "crcl/CRCLCommandsClasses.hh"

/*********************************************************************/

class CRCLProgramFile;
class CRCLProgramType;
class XmlHeaderForCRCLProgram;

/*********************************************************************/
/*********************************************************************/

class CRCLProgramFile :
  public XmlSchemaInstanceBase
{
public:
  CRCLProgramFile();
  CRCLProgramFile(
    XmlVersion * versionIn,
    XmlHeaderForCRCLProgram * headerIn,
    CRCLProgramType * CRCLProgramIn);
  ~CRCLProgramFile();
  void PRINTSELFDECL;

  XmlVersion * version;
  XmlHeaderForCRCLProgram * header;
  CRCLProgramType * CRCLProgram;
};

/*********************************************************************/

class CRCLProgramType :
  public DataThingType
{
public:
  CRCLProgramType();
  CRCLProgramType(
    XmlID * NameIn,
    InitCanonType * InitCanonIn,
    std::list<MiddleCommandType *> * MiddleCommandIn,
    EndCanonType * EndCanonIn);
  ~CRCLProgramType();
  void PRINTSELFDECL;

  InitCanonType * InitCanon;
  std::list<MiddleCommandType *> * MiddleCommand;
  EndCanonType * EndCanon;

  bool printTypp;
};

/*********************************************************************/

class XmlHeaderForCRCLProgram
{
public:
  XmlHeaderForCRCLProgram();
  XmlHeaderForCRCLProgram(
    SchemaLocation * locationIn);
  ~XmlHeaderForCRCLProgram();
  void PRINTSELFDECL;

  SchemaLocation * location;
};

/*********************************************************************/

#endif // CRCLPROGRAMINSTANCE_HH
