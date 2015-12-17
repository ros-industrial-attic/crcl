/*********************************************************************/

#ifndef CRCLCOMMANDINSTANCE_HH
#define CRCLCOMMANDINSTANCE_HH
#include <stdio.h>
#include <list>
#include <xml_common/xmlSchemaInstance.hh>
#include "crcl/CRCLCommandsClasses.hh"

/*********************************************************************/

class CRCLCommandInstanceFile;
class CRCLCommandInstanceType;
class XmlHeaderForCRCLCommandInstance;

/*********************************************************************/
/*********************************************************************/

class CRCLCommandInstanceFile :
  public XmlSchemaInstanceBase
{
public:
  CRCLCommandInstanceFile();
  CRCLCommandInstanceFile(
    XmlVersion * versionIn,
    XmlHeaderForCRCLCommandInstance * headerIn,
    CRCLCommandInstanceType * CRCLCommandInstanceIn);
  ~CRCLCommandInstanceFile();
  void PRINTSELFDECL;

  XmlVersion * version;
  XmlHeaderForCRCLCommandInstance * header;
  CRCLCommandInstanceType * CRCLCommandInstance;
};

/*********************************************************************/

class CRCLCommandInstanceType :
  public DataThingType
{
public:
  CRCLCommandInstanceType();
  CRCLCommandInstanceType(
    XmlID * NameIn,
    CRCLCommandType * CRCLCommandIn);
  ~CRCLCommandInstanceType();
  void PRINTSELFDECL;

  CRCLCommandType * CRCLCommand;

  bool printTypp;
};

/*********************************************************************/

class XmlHeaderForCRCLCommandInstance
{
public:
  XmlHeaderForCRCLCommandInstance();
  XmlHeaderForCRCLCommandInstance(
    SchemaLocation * locationIn);
  ~XmlHeaderForCRCLCommandInstance();
  void PRINTSELFDECL;

  SchemaLocation * location;
};

/*********************************************************************/

#endif // CRCLCOMMANDINSTANCE_HH
