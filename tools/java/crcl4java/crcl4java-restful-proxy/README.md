# crcl4java-restful-proxy
RESTful services for  "Canonical Robot Command Language" (CRCL)

The language called "Canonical Robot Command Language" (CRCL) provides generic command and status definitions that implement the functionality of typical industrial robots without being specific either to the language of a plan that is being executed or to the language used by a robot controller that executes CRCL commands. It is normally sent over a normal simple
TCP socket without the HTTP headers etc normally part of a RESTful interface.
This module provides a proxy which allows clients to connect to a RESTful interface.
Commands are forwarded over simple TCP connection. Status is obtained over the simple
TCP connection and made available via the RESTful interface.

The RESTful proxy will also convert from XML to/from JSON as required if the 
JSON resources are accessed.



Build
-----


To build one needs:
  * JDK 1.8+ (https://adoptopenjdk.net/)  and
  * maven 3.0.5+ (https://maven.apache.org/download.cgi) 
  
Use the command:

    mvn package 

    
OR 

  * An IDE bundled with both maven and a JDK such as Netbeans, IntelliJ, or Eclipse.
      * Netbeans will open a maven project with File -> Open Project just like Netbeans generated Ant projects.
      * IntelliJ and Eclipse both have options to import maven projects.


The module can be build as a WAR (Web application ARchive) file that can be
deployed along other web applications on an Application server such as Apache Tomcat 
or Glassfish.

Optionally enable the tomcat_embed profile  by selecting tomcat_embed in the Netbeans
drop-down configuration list or by adding -Ptomcat_embed. This will create a stand-alone
executable jar file so that no Application server is needed.

eg. 

        mvn -Ptomcat_embed package

        java -jar target/crcl4java-restful-proxy-1.8-war-exec.jar 


Once the program is started one can access the API's and their documentation 
and test tools at:

        http://localhost:8080/crcl4java-restful-proxy/



