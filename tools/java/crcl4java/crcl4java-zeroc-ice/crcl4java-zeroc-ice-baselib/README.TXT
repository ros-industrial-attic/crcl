
This directory contains a pom.xml to build the base library needed by both 
the CRCL ICE clients and servers. CRCL is normally sent as XML directly over a
TCP socket. While this eliminates any need for a dependancy on a library other
than operating system provided socket. Composing and parsing XML may require more
effort on the programmers part, computational delays or communications bandwidth
than other communications frameworks require. Other communications frameworks may
also provide additional useful services. For this reason CRCL specific bridges are
provided to other communications frameworks. Java clients/servers could use the library
directly while applications in other languages can use the appropriate tool included
with Ice to create corresponding interface classes from 
src/generated/resources/crcl4java.slice.

The Internet Communications Engine (Ice) is a modern open-source object-oriented toolkit
 that enables you to build distributed applications developed by ZeroC Inc.

To build the library requires:

    Java Development Kit (JDK) 1.7 or higher
        https://adoptopenjdk.net/

    Maven 3.0.5 or higher
        https://maven.apache.org/download.cgi

    ICE 3.6 or higher
        https://zeroc.com/distributions/ice

To build and install the library use the command.

    mvn install

Maven must be able to locate the slice2java program included in ICE. If it is
not installed in the expected default location it can be specified it can be
specified on the command line eg. 

    mvn "-Dslice2java.path=C:\\Program Files (x86)\\ZeroC\\Ice-3.6.2\\bin\\slice2java.exe" install

(Note the need to surround paths with spaces in quotes. )

For repeated use it would probably be more convenient to set the path in
the settings.xml file in the .m2 subdirectory of your home directory.

eg.

<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                          http://maven.apache.org/xsd/settings-1.0.0.xsd">
<profiles>
   <profile>
       <id>defaultproperties</id>
       <activation>
           <activeByDefault>true</activeByDefault>
       </activation>
       <properties>
           <slice2java.path>C:\Program Files (x86)\ZeroC\Ice-3.6.2\bin\slice2java.exe</slice2java.path>
       </properties>
   </profile>
</profiles>
</settings>



