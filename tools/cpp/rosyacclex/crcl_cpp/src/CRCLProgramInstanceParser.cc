#include <stdio.h>   // fprintf
#include <string.h>  // strlen
#include <stdlib.h>  // exit
#include "crcl_cpp/CRCLProgramInstanceClasses.hh"

extern CRCLProgramFile * CRCLProgramTree;
extern FILE * yyin;
extern int yyparse();
extern void yylex_destroy();

int main(       /* ARGUMENTS                                      */
 int argc,      /* one more than the number of command arguments  */
 char * argv[]) /* array of executable name and command arguments */
{
  std::string outFileName;
  FILE * inFile;
  FILE * outFile;

  if (argc != 2)
    {
      fprintf(stderr, "Usage: %s <data file name>\n", argv[0]);
      exit(1);
    }
  inFile = fopen(argv[1], "r");
  if (inFile == 0)
    {
      fprintf(stderr, "unable to open file %s for reading\n", argv[1]);
      exit(1);
    }
  yyin = inFile;
  yyparse();
  fclose(inFile);
  outFileName = argv[1];
  outFileName.append("echo");
  outFile = fopen(outFileName.c_str(), "w");
  CRCLProgramTree->PRINTSELF;
  fclose(outFile);
  delete CRCLProgramTree;
  yylex_destroy();
  return 0;
}
