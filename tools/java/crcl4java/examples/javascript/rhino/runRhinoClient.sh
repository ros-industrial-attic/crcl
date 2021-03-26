#!/bin/sh

# This script was tested on Ubuntu 14.04. 
# To run the script you need jjs which is included in Java 8 and the crcl4java-utils jar file.
# The script will try to download them if not already downloaded and then run the 
# clclclient.js  with jjs with the jar file on the classpath.

set -x;

\rm -f crcl4java-utils-copy.jar;

export CRCL4JAVA_UTILS_JAR=`ls -1t crcl4java-utils*.jar ../../../crcl4java-utils/target/crcl4java-utils*with-dependencies.jar | head -n 1`;
if test "x${CRCL4JAVA_UTILS_JAR}" = "x" ; then 
    remotejarurl="https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.4/crcl4java-utils-1.4-jar-with-dependencies.jar";
    echo "Downloading ${remotejarurl}";
    wget "${remotejarurl}"
    export CRCL4JAVA_UTILS_JAR=`ls -1t crcl4java-utils*.jar | head -n 1`
fi

ln -s "${CRCL4JAVA_UTILS_JAR}" crcl4java-utils-copy.jar
if ! which rhino >/dev/null 2>/dev/null ; then
    sudo apt-get install rhino;
fi

rhino crclRhinoClient.js
