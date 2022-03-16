Canonical Robot Command Language
================================

The "Canonical Robot Command Language" (CRCL) provides generic command and status definitions that implement the functionality of typical industrial robots without being specific either to the language of a plan that is being executed or to the language used by a robot controller that executes CRCL commands. It can be used with offline planners that create output to be stored in CRCL files or online where CRCL is communicated in both directions via TCP. CRCL commands and status could also be exchanged over TCP between an operator interface and a robot controller or proxy for a robot controller.

This repository contains the "standard" CRCL SCHEMA only. It is a standard version 1. There are tool repositories that help integrate CRCL with yoour application. There are example CRCL instance files in the instances subdirectory.

The primay CRCL Java tools can be found here:  [nist/crcl](https://github.com/usnistgov/crcl). This github repository contains experiemntal CRCL XSD as well as Java parsing tools. Under the tools subdirectory in the repository, are language-specific tools for parsing CRCL in  Java, C++, and Python. It is recommended to use Java, as it is the best integration technique, as it uses JaxB to parse the CRCL XSD and generate Java class definitions to parse and serialize the CRCL.

There is an another CRCL example repository found [<u>here</u>](https://github.com/usnistgov/crcl2ros) that contains a ROS workspace to handle CRCL streaming, CRCL to ROS conversion, and ROS to Gazebo simulation. This repository contains C++ code that implements CRCL XML streaming and parsing component, that maps command and status motion primitives from CRCL to ROS, then uses ROS moveit! to plan motion trajectories that are then simulated in Gazebo. Of note, the CRCL is parsed and serialized with XSD tools from Code Synthesis.




