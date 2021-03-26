#!/bin/sh

set -x;


# Change to the directory this script is stored in. 
DIR=$(dirname $0 )
if test "x${DIR}" != "x" ; then
    if test -d "${DIR}" ; then
        cd "$DIR";
    fi
fi

export CRCL4JAVA_UTILS_JAR=`ls -1t crcl4java-utils*.jar ../../crcl4java-utils/target/crcl4java-utils*with-dependencies.jar | head -n 1`;
if test "x${CRCL4JAVA_UTILS_JAR}" = "x" ; then 
    remotejarurl="https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.4/crcl4java-utils-1.4-jar-with-dependencies.jar";
    echo "Downloading ${remotejarurl}";
    wget "${remotejarurl}"
    export CRCL4JAVA_UTILS_JAR=`ls -1t crcl4java-utils*.jar | head -n 1`
fi



javac -cp "${CRCL4JAVA_UTILS_JAR}" CRCLClient.java CRCLServer.java

