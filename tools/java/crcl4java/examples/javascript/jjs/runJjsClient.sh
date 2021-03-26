#!/bin/sh

# This script was tested on Ubuntu 14.04. 
# To run the script you need jjs which is included in Java 8 and the crcl4java-utils jar file.
# The script will try to download them if not already downloaded and then run the 
# clclclient.js  with jjs with the jar file on the classpath.

set -x;


export CRCL4JAVA_UTILS_JAR=`ls -1t crcl4java-utils*.jar ../../../crcl4java-utils/target/crcl4java-utils*with-dependencies.jar | head -n 1`;
if test "x${CRCL4JAVA_UTILS_JAR}" = "x" ; then 
    remotejarurl="https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.4/crcl4java-utils-1.4-jar-with-dependencies.jar";
    echo "Downloading ${remotejarurl}";
    wget "${remotejarurl}"
    export CRCL4JAVA_UTILS_JAR=`ls -1t crcl4java-utils*.jar | head -n 1`
fi


jjscmd="jjs"

#if ! which jjs >/dev/null 2>/dev/null ; then
#    printenv JAVA_HOME
#    if test "x${JAVA_HOME}" != "x" ; then
#        jjscmd="${JAVA_HOME}/bin/jjs";
#        echo jjscmd="${jjscmd}";
#    fi
#fi

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

"${jjscmd}" -cp "${CRCL4JAVA_UTILS_JAR}":.  crclJjsClient.js
