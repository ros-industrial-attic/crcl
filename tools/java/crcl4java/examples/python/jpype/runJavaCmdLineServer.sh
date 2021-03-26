#!/bin/sh

# This script was tested on Ubuntu 14.04. 
# It will download if necessary and run the java jar file.
# Java 8 is required for run the server. If Java 8 is not detected it will
# try to install it.

# On platforms other than Ubuntu 14.04 or if the automatic installation fails
# it may be easier just to manually install Java 8 from:
# https://adoptopenjdk.net/
#
set -x;

# Change to the directory this script is stored in. 
DIR=$(dirname $0 )
if test "x${DIR}" != "x" ; then
    if test -d "${DIR}" ; then
        cd "$DIR";
    fi
fi

export CRCL4JAVA_UI_JAR=`ls -1t crcl4java-ui*.jar ../../../crcl4java-ui/target/crcl4java-ui*with-dependencies.jar | head -n 1`;
if test "x${CRCL4JAVA_UI_JAR}" = "x" ; then 
    remotejarurl="https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-ui/1.4/crcl4java-ui-1.4-jar-with-dependencies.jar";
    echo "Downloading ${remotejarurl}";
    wget "${remotejarurl}"
    export CRCL4JAVA_UI_JAR=`ls -1t crcl4java-ui*.jar | head -n 1`
fi

jjscmd="jjs"
javacmd="java"


printenv JAVA_HOME
if test "x${JAVA_HOME}" != "x" ; then
    javacmd="${JAVA_HOME}/bin/java";
fi


"${javacmd}" -jar "${CRCL4JAVA_UI_JAR}" --mode CmdLineServer
