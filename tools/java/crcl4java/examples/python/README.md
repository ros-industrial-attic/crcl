
There are two independant ways of using crcl4java with python.

    * JPype -- uses the regular python interpeter and loads java classes within python
    * Jython -- runs the python interpreter within the jvm

For ubuntu use either:

sudo apt-get install python-jpype

or

sudo apt-get install jython

See comments at the start of each python file for instructions on how to run
and set paths and/or use the run scripts.


Before running a client you will need to have started a server.  
The scripts runJavaGuiServer.sh (for Linux) or runJavaGuiServer.bat (for Windows)
provide an option for a server to start. Java 8 is required for the 
graphical Java server.

To install Java SE Development Kit 8, goto  https://adoptopenjdk.net/

If you need help or a more complete description of the options for installing 
Java 8 on Ubuntu see: 

    https://github.com/usnistgov/crcl/wiki/How-to-install-Java-8-on-Ubuntu-Linux.
    


For a reference for all classes and functions available download and unzip:


    https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.3/crcl4java-utils-1.3-javadoc.jar
    https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-base/1.3/crcl4java-base-1.3-javadoc.jar

Or use the scripts showDoc.sh / showDoc.bat to download and open the docs.
The scripts will open two tabs. One for utils packages and one for the base package.


