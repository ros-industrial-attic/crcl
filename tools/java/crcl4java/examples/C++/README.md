


C++ can use Java libraries through JNI or more conveniently through java4cpp.
https://github.com/wshackle/java4cpp.

Install Java SE Development Kit 8  from https://adoptopenjdk.net/

If you need help or a more complete description of the options for installing 
Java 8 on Ubuntu see: 

    https://github.com/usnistgov/crcl/wiki/How-to-install-Java-8-on-Ubuntu-Linux.
    


The example can be built with the build.sh script or by following these instructions:

Download 
	 http://repo.maven.apache.org/maven2/com/github/wshackle/java4cpp/1.4/java4cpp-1.4-jar-with-dependencies.jar
	 
	 and

	 https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.3/crcl4java-utils-1.3-jar-with-dependencies.jar

into the current directory.


Generate wrapper C++ files with:

java -jar java4cpp-1.3-jar-with-dependencies.jar -p crcl -n crclj -j crcl4java-utils-1.3-jar-with-dependencies.jar

Set the environment variable JAVA_HOME to the location of your java installation.( eg /usr/lib/jvm/java-8-oracle/. 

On Linux:

Set the JVM_LIBDIR variable to the sub-directory of JAVA_HOME that contains libjvm.so. (eg /usr/lib/jvm/java-8-oracle/jre/lib/amd64/server/ )

Compile with:

g++ -O0 -g -I "${JAVA_HOME}/include"  -I "${JAVA_HOME}/include/linux" crclj*.cpp crclClient.cpp  -L "${JVM_LIBDIR}" -Wl,--rpath "${JVM_LIBDIR}" -ljvm -o crclj_test

See build.sh for details.

On Windows

Edit setup.bat so that the JAVA_HOME variable is set correctly.
Run the setup.bat to generate header files and C++ source files in the generated 
directory.

One can either use the Visual Studio Solutions in CRCLCLient and CRCLServer sub-directories or
compile with build.bat.

To compile with build.bat :

Edit build.bat so that JVM_LIBDIR,JVM_DLLDIR and MSVISUALC_DIR are set correctly.

Set the JVM_LIBDIR variable to the sub-directory of JAVA_HOME that contains jvm.lib (eg C:\Program Files\Java\jdk1.8.0_92\lib )
Set the JVM_DLLDIR variable to the sub-directory of JAVA_HOME that contains jvm.dll (eg C:\Program Files\Java\jdk1.8.0_92\jre\bin\server )
Set the MSVISUALC_DIR variable to the location of Visual Studio (with C++ included)  (eg C:\Program Files (x86)\Microsoft Visual Studio 14.0) 
The file vcvarsall.bat should be in the MSVISUALC_DIR.

To use the Visual Studio Solutions: 

Edit  Properties -> C/C++ -> General -> Additional Include directories
     To include the  JDK include and its win32 subdirectory. 
     (eg C:\Program Files\Java\jdk1.8.0_92\include\win32;C:\Program Files\Java\jdk1.8.0_92\include;)


Edit Properties -> Linker -> General -> Additional Library Directories
    To include the JDK lib directory.
    (eg C:\Program Files\Java\jdk1.8.0_92\lib )
     
Before running a client you will need to have started a server.  
The scripts runJavaGuiServer.sh (for Linux) or runJavaGuiServer.bat (for Windows)
provide an option for a server to start.

For a reference for all classes and functions available download and unzip:


    https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.3/crcl4java-utils-1.3-javadoc.jar
    https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-base/1.3/crcl4java-base-1.3-javadoc.jar

Or use the scripts showDoc.sh / showDoc.bat to download and open the docs.
The scripts will open two tabs. One for utils packages and one for the base package.
