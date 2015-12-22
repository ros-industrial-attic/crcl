These files in this `xml_common/src` directory are hand edited: 

'''
pattern.lex
pattern.y
xmlInstanceParserGenerator.cc
xmlSchema.lex
xmlSchema.y
xmlSchemaClasses.cc
xmlSchemaInstance.cc
xmlSchemaParser.cc
'''
These files are generated from the `.lex` and `.y` files above using the `bison` and `flex` utilities:
'''
patternLex.cc
patternYACC.cc
patternYACC.hh
xmlSchemaLex.cc
xmlSchemaYACC.cc
xmlSchemaYACC.hh
'''
None of the headers is needed by external programs who use these utilities, so they are not installed. 

