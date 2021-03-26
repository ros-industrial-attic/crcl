
Node.js is JavaScript runtime built on Chrome's V8 JavaScript engine.

See :

    https://nodejs.org/en/

On Debian and Ubuntu the name of the Node.js executable is nodejs since the 
command used on other platforms "node" was already used by an unrelated
 application.


To install nodejs and the required modules on Ubuntu run:

    ./setup.sh

sudo privileges are required to run setup.sh.

On Windows, Install Python 2.7 (which is used during the install of one of the
    npm java dependencies ) eg:

    
    https://www.python.org/downloads/release/python-2711/

    or

    https://www.python.org/ftp/python/2.7.11/python-2.7.11.amd64.msi


With NodeJs and Python 2.7 installed run:

    setup.bat


On other platforms you will need to manually download the jar files below and 
install nodejs, and use "npm java" to install the java bindings as described
in https://www.npmjs.com/package/java :

    https://oss.sonatype.org/content/repositories/snapshots/com/github/wshackle/crcl4java-utils/1.5-SNAPSHOT/crcl4java-utils-1.4-20160422.130648-1-jar-with-dependencies.jar
    https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-ui/1.3/crcl4java-ui-1.3-jar-with-dependencies.jar
    https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.3/crcl4java-utils-1.3-jar-with-dependencies.jar


To run the server written in javascript in Linux:

    ./runServer.sh

To run the server written in javascript in Windows:

    runServer.bat

The server will bind to the default port and wait for clients to send commands
and it will send back status messages to clients that request it with
the GetStatusType command.


Before running the client, a CRCL server should be started. One alternative
is the server started with runJavaGuiServer.sh script. Java 8 is required for the 
graphical Java server.

To install Java SE Development Kit 8, goto  https://adoptopenjdk.net/

If you need help or a more complete description of the options for installing 
Java 8 on Ubuntu see: 

    https://github.com/usnistgov/crcl/wiki/How-to-install-Java-8-on-Ubuntu-Linux.
    


To run the client on Linux:

    ./runClient.sh

To run the client written in javascript in Windows:

    runClient.bat


The client connects to a CRCL server on localhost on the default port, sends a 
couple of commands, requests status and prints the status.


For a reference for all classes and functions available download and unzip:


    https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.3/crcl4java-utils-1.3-javadoc.jar
    https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-base/1.3/crcl4java-base-1.3-javadoc.jar

Or use the scripts showDoc.sh / showDoc.bat to download and open the docs.
The scripts will open two tabs. One for utils packages and one for the base package.

