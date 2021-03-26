To Build:

Download the latest JDK (at-least 1.8+)  from https://adoptopenjdk.net/

( getting the bundled Netbeans is recommended but not required)

To build with netbeans simply open this directory as a project (File-> Open Project ...)  and choose 
(Run -> Build Project (CRCLJavaBase) ).

To build on the command line:

export JAVA_HOME=[path_to_jdk_1.8+]
sudo apt-get install maven
mvn clean install

The schemas are now stored in this projects resources directory.

To change the shemas use and force regeneration of sources:

use -PnewSchema and -Dcrcl.schemaBaseUrl= to set the url of the new schema.

eg.

mvn clean install -PnewSchema -Dcrcl.schemaBaseUrl=https://raw.githubusercontent.com/ros-industrial/crcl/master/schemas/

OR  

mvn clean install -PnewSchema -Dcrcl.schemaBaseUrl=file:${HOME}/crac/xml/crcl/xmlSchemas



This directory only produces a library jar file,
target/CRCLJavaBase-1.0-SNAPSHOT.jar, from
xjc automatically generated sources using the XML Schema files in 
../xml/crcl/xmlSchemas.

It is used by the main project CRCLJava.
