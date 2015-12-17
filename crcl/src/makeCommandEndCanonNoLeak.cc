#include "CRCLCommandInstanceClasses.hh"
#include "CRCLCommandsClasses.hh"

int main(int argc, char * argv[])
{
  XmlVersion * versionIn;
  XmlHeaderForCRCLCommandInstance * headerIn;
  CRCLCommandInstanceType * CRCLCommandInstanceIn;
  EndCanonType * EndCanonCommand;
  CRCLCommandInstanceFile * CRCLCommandInstanceFileIn;
  FILE * outFile;

  versionIn = new XmlVersion(true);
  headerIn = new XmlHeaderForCRCLCommandInstance;
  CRCLCommandInstanceIn = new CRCLCommandInstanceType;
  CRCLCommandInstanceFileIn =
    new CRCLCommandInstanceFile(versionIn, headerIn, CRCLCommandInstanceIn);
  headerIn->location =
    new SchemaLocation("xsi", "../xmlSchemas/CRCLCommandInstance.xsd", false);
  EndCanonCommand = new EndCanonType;
  EndCanonCommand->Name = 0;
  EndCanonCommand->printTypp = true;
  EndCanonCommand->CommandID = new XmlPositiveInteger("3");
  CRCLCommandInstanceIn->Name = 0;
  CRCLCommandInstanceIn->CRCLCommand = EndCanonCommand;
  outFile = fopen("EndCRCLCommandFile", "w");
  CRCLCommandInstanceFileIn->printSelf(outFile);
  fclose(outFile);
  delete CRCLCommandInstanceFileIn;
  return 0;
}
