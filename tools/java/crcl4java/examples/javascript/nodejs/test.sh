#!/bin/bash

set -x;

# Change to the directory this script is stored in. 
DIR=$(dirname $0 )
if test "x${DIR}" != "x" ; then
    if test -d "${DIR}" ; then
        cd "$DIR";
    fi
fi

echo "Testing NodeJs Example ..."

sh ./killNodejsServer.sh

sh ./clean.sh || (echo "Clean Failed" ; exit 1) || exit 1;
if test !  -f setup.ok  ; then
    sudo -n sh ./setup.sh || (echo "Setup Failed. Please run sudo setup.sh." ; exit 1) || exit 1;
fi

export CRCL4JAVA_UTILS_JAR=`ls -1t crcl4java-utils*.jar ../../../crcl4java-utils/target/crcl4java-utils*with-dependencies.jar | head -n 1`;
if test "x${CRCL4JAVA_UTILS_JAR}" = "x" ; then 
    remotejarurl="https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.4/crcl4java-utils-1.4-jar-with-dependencies.jar";
    echo "Downloading ${remotejarurl}";
    wget "${remotejarurl}"
    export CRCL4JAVA_UTILS_JAR=`ls -1t crcl4java-utils*.jar | head -n 1`
fi


sh ./runNodejsServer.sh &
sleep 3
sh ./runNodejsClient.sh || (sh ./killNodejsServer.sh;  echo "Run Client Failed"; exit 1) || exit 1;
sh ./killNodejsServer.sh

