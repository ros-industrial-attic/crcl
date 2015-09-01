Canonical Robot Command Language
================================

The language called "Canonical Robot Command Language" (CRCL) provides generic command and status definitions that implement the functionality of typical industrial robots without being specific either to the language of a plan that is being executed or to the language used by a robot controller that executes CRCL commands. It can be used with offline planners that create output to be stored in CRCL files or online where CRCL is communicated in both directions via TCP. CRCL commands and status could also be exchanged over TCP between an operator interface and a robot controller or proxy for a robot controller.

This repository contains the reference documentation  in doc/REFERENCE.md. XML Schema Language files for validation and code generation in schemas subdirectory  and example instance files in the instances subdirectory.

Tools, libraries and example code to aid users of dealing directly with  CRCL in specific programing languages are provided in the locations:

    * Java : https://github.com/wshackle/crcl4java
    * C# : https://github.com/wshackle/crcl4csharp
    * Python : tools/python subdirectory
