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



jjscmd="jjs"
javacmd="java"

printenv JAVA_HOME
if test "x${JAVA_HOME}" != "x" ; then
    jjscmd="${JAVA_HOME}/bin/jjs";
    echo jjscmd="${jjscmd}";
    javacmd="${JAVA_HOME}/bin/java";
    echo javacmd="${javacmd}";
fi

export CRCL4JAVA_UI_JAR=`ls -1t crcl4java-ui*.jar ../../../crcl4java-ui/target/crcl4java-ui*with-dependencies.jar | head -n 1`;
if test "x${CRCL4JAVA_UI_JAR}" = "x" ; then 
    remotejarurl="https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-ui/1.4/crcl4java-ui-1.4-jar-with-dependencies.jar";
    echo "Downloading ${remotejarurl}";
    wget "${remotejarurl}"
    export CRCL4JAVA_UI_JAR=`ls -1t crcl4java-ui*.jar | head -n 1`
fi

if ! which "${jjscmd}" >/dev/null 2>/dev/null ; then
    echo "jjs command not found. Would you like to try to automatically install openjdk-8-jdk including jjs(y/N)?";
    read confirm;
    if test "${confirm}x" = "yx" ; then
        sudo apt-get install openjdk-8-jdk
    fi
fi

if ! which "${jjscmd}" >/dev/null 2>/dev/null ; then
    echo "jjs command not found. Would you like to try to automatically install Oracle java 8 including jjs(y/N)?";
    read confirm;
    if test "${confirm}x" = "yx" ; then
        sudo add-apt-repository ppa:webupd8team/java -y
        sudo apt-get update
        sudo apt-get install oracle-java8-installer
        sudo apt-get install oracle-java8-set-default
    fi
fi

if ! which "${jjscmd}" >/dev/null 2>/dev/null ; then
    echo "jjs command not found. Please install Java 1.8 or higher and add to PATH";
    exit 1;
fi

"${javacmd}" -jar "${CRCL4JAVA_UI_JAR}" --mode GraphicalServer
