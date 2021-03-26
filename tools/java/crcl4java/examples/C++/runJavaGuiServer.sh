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


jarfile="crcl4java-ui-1.4-jar-with-dependencies.jar"
if ! test -f "${jarfile}" ; then
    remotejarurl="https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-ui/1.4/crcl4java-ui-1.4-jar-with-dependencies.jar";
    echo "Downloading ${remotejarurl}";
    wget "${remotejarurl}"
fi

jjscmd="jjs"
javacmd="java"

if ! which jjs >/dev/null 2>/dev/null ; then
    printenv JAVA_HOME
    if test "x${JAVA_HOME}" != "x" ; then
        jjscmd="${JAVA_HOME}/bin/jjs";
        echo jjscmd="${jjscmd}";
        javacmd="${JAVA_HOME}/bin/java";
    fi
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

"${javacmd}" -jar "${jarfile}" --mode GraphicalServer
