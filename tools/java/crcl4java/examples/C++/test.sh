#!/bin/bash

set -x;

# Change to the directory this script is stored in. 
DIR=$(dirname $0 )
if test "x${DIR}" != "x" ; then
    if test -d "${DIR}" ; then
        cd "$DIR";
    fi
fi

echo "Testing C++ Example ..."

sh ./clean.sh || (echo "Clean Failed" ; exit 1) || exit 1;
sh ./build.sh || (echo "Build Failed" ; exit 1) || exit 1;
sh ./killCppServer.sh
sh ./killJavaServer.sh

echo 
echo "Testing C++ Client -> Java Server . . ."
echo

export CRCL4JAVA_UI_JAR=`ls -1t crcl4java-ui*.jar ../../crcl4java-ui/target/crcl4java-ui*with-dependencies.jar | head -n 1`;
if test "x${CRCL4JAVA_UI_JAR}" = "x" ; then 
    remotejarurl="https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-ui/1.4/crcl4java-ui-1.4-jar-with-dependencies.jar";
    echo "Downloading ${remotejarurl}";
    wget "${remotejarurl}"
    export CRCL4JAVA_UI_JAR=`ls -1t crcl4java-ui*.jar | head -n 1`
fi

sh ./runCmdLineJavaServer.sh &
sleep 3
sh ./runCppClient.sh || (sh ./killCppServer.sh;  echo "Run Client Failed"; exit 1) || exit 1;
sh ./killCppServer.sh

echo 
echo "C++ Client -> Java Server succeeded."
echo

sh ./killCppServer.sh
sh ./killJavaServer.sh

echo 
echo "Testing C++ Client -> C++ Server . . ."
echo

sh ./runCppServer.sh &
sleep 1
sh ./runCppClient.sh || (sh ./killCppServer.sh;  echo "Run Client Failed"; exit 1) || exit 1;
sh ./killCppServer.sh

echo 
echo "C++ Client -> C++ Server succeeded."
echo

