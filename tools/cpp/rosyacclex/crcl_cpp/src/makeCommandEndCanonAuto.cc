#include <string.h>
#include <stdlib.h>            // for malloc, free
#include "crcl_cpp/CRCLCommandInstanceClasses.hh"
#include "crcl_cpp/CRCLCommandsClasses.hh"

int main(int argc, char * argv[])
{
  XmlVersion versionIn;
  XmlHeaderForCRCLCommandInstance headerIn;
  CRCLCommandInstanceType CRCLCommandInstanceIn;
  EndCanonType EndCanonCommand;
  CRCLCommandInstanceFile CRCLCommandInstanceFileIn;
  SchemaLocation SchemaLocationIn;
  XmlPositiveInteger CommandIDIn;
  FILE * outFile;

  // insert non-pointer values
  // SchemaLocationIn.prefix and SchemaLocationIn.location are std::strings
  versionIn.hasEncoding = true;
  SchemaLocationIn.prefix = "xsi";
  SchemaLocationIn.location =  "../xmlSchemas/CRCLCommandInstance.xsd";
  SchemaLocationIn.hasNamespace = false;
  EndCanonCommand.printTypp = true;
  CommandIDIn.val = 3;
  CommandIDIn.bad = false;
  CRCLCommandInstanceIn.printTypp = false;

  // wire up pointers
  EndCanonCommand.Name = 0;
  CRCLCommandInstanceIn.Name = 0;
  headerIn.location = &SchemaLocationIn;
  CRCLCommandInstanceFileIn.version = &versionIn;
  CRCLCommandInstanceFileIn.header =  &headerIn;
  CRCLCommandInstanceFileIn.CRCLCommandInstance = &CRCLCommandInstanceIn;
  EndCanonCommand.CommandID = &CommandIDIn;
  CRCLCommandInstanceIn.CRCLCommand = &EndCanonCommand;

  // print the file
  outFile = fopen("EndCRCLCommandFile", "w");
  CRCLCommandInstanceFileIn.printSelf(outFile);
  fclose(outFile);

  // unwire non-zero pointers so that destructors (which run automatically)
  // do not try to delete automatic variables
  headerIn.location = 0;
  CRCLCommandInstanceFileIn.version = 0;
  CRCLCommandInstanceFileIn.header = 0;
  CRCLCommandInstanceFileIn.CRCLCommandInstance = 0;
  EndCanonCommand.CommandID = 0;
  CRCLCommandInstanceIn.CRCLCommand = 0;
  
  return 0;
}
