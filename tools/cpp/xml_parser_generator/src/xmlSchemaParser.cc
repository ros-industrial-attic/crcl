/********************************************************************/

/*

This is an xmlSchemaParser. It reads an XML schema file, checks the
syntax of the file, builds an abstract syntax tree, and reprints the
file from the tree.

This does not process "include" directives, check references, or
check xpaths.

There is an option to save and print comments. The default is to
delete comments.

There are four options for printing documentation nodes:
1. reprint them as is
2. reprint them collapsing white space
3. reprint them formatted
4. remove them (and the annotation nodes in which they are embedded).
The default is to reprint documentation as is.

*/

/********************************************************************/
#include <stdio.h>   // fprintf
#include <string.h>  // strlen
#include <stdlib.h>  // exit
#include "xml_parser_generator/xmlSchemaClasses.hh"

extern XmlSchemaFile * xmlSchemaFile;
extern FILE * yyin;
extern int yyparse();
extern int yyreparse();
extern FILE * yyrein;

/********************************************************************/

int XmlSchemaFile::printDoc = 0;
bool XmlSchemaFile::printComments = false;

/********************************************************************/

void usageMessage( /* ARGUMENTS                                  */
 char * command)   /* the name of the xmlSchemaParser executable */
{
  fprintf(stderr, "usage: %s [-d <docPrint>] [-c] <schema>\n",
	  command);
  fprintf(stderr, "docPrint controls documentation printing and \n");
  fprintf(stderr, "must be asIs, indent, left, or none\n");
  fprintf(stderr, "-c means to print comments\n");
  fprintf(stderr, "schema is the XML schema file to read\n");
  fprintf(stderr, "Example 1: %s plan.xsd\n", command);
  fprintf(stderr, "Example 2: %s -d indent plan.xsd\n", command);
  fprintf(stderr, "Example 3: %s -d asIs -c plan.xsd\n", command);
  fprintf(stderr, "Example 4: %s -c plan.xsd\n", command);
  fprintf(stderr, "defaults are asIs documentation and no comments\n");
  exit(1);
}

/********************************************************************/

int main(       /* ARGUMENTS                                      */
 int argc,      /* one more than the number of command arguments  */
 char * argv[]) /* array of executable name and command arguments */
{
  char * inFileName;
  char * outFileName;
  FILE * outFile;
  
  if (argc == 2)
    {
      inFileName = argv[1];
    }
  else if (argc == 3)
    {
      if (strcmp(argv[1], "-c"))
	usageMessage(argv[0]);
      XmlSchemaFile::printComments = true;
      inFileName = argv[2];
    }
  else if (argc == 4)
    {
      if (strcmp(argv[1], "-d"))
	usageMessage(argv[0]);
      else if (strcmp(argv[2], "asIs") == 0)
	XmlSchemaFile::printDoc = 0;
      else if (strcmp(argv[2], "indent") == 0)
	XmlSchemaFile::printDoc = 1;
      else if (strcmp(argv[2], "left") == 0)
	XmlSchemaFile::printDoc = 2;
      else if (strcmp(argv[2], "none") == 0)
	XmlSchemaFile::printDoc = 3;
      else
	usageMessage(argv[0]);
      inFileName = argv[3];
    }
  else if (argc == 5)
    {
      if (strcmp(argv[1], "-d"))
	usageMessage(argv[0]);
      else if (strcmp(argv[2], "asIs") == 0)
	XmlSchemaFile::printDoc = 0;
      else if (strcmp(argv[2], "indent") == 0)
	XmlSchemaFile::printDoc = 1;
      else if (strcmp(argv[2], "left") == 0)
	XmlSchemaFile::printDoc = 2;
      else if (strcmp(argv[2], "none") == 0)
	XmlSchemaFile::printDoc = 3;
      else
	usageMessage(argv[0]);
      if (strcmp(argv[3], "-c"))
	usageMessage(argv[0]);
      XmlSchemaFile::printComments = true;
      inFileName = argv[4];
    }
  else // ((argc < 2) || (argc > 5))
    {
      usageMessage(argv[0]);
    }
  yyin = fopen(inFileName, "r");
  if (yyin == 0)
    {
      fprintf(stderr, "unable to open file %s for reading\n", inFileName);
      exit(1);
    }
  XmlCppBase::wg3Prefix = 0;

  yyparse();
  fclose(yyin);
  outFileName = new char[strlen(inFileName) + 5];
  sprintf(outFileName, "%secho", inFileName);
  outFile = fopen(outFileName, "w");
  xmlSchemaFile->printSelf(outFile);
  fclose(outFile);
  return 0;
}

