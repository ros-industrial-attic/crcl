#!/bin/bash

set -x;

# Change to the directory this script is stored in. 
DIR=$(dirname $0 )
if test "x${DIR}" != "x" ; then
    if test -d "${DIR}" ; then
        cd "$DIR";
    fi
fi
echo "Testing Matlab Example ..."

sh ./killJavaServer.sh
sh ./clean.sh || (echo "Clean Failed" ; exit 1) || exit 1;
sh ./runCmdLineJavaServer.sh &
sleep 1
sh ./runMatlabClient.sh || (sh ./killJavaServer.sh;  echo "Run Client Failed"; exit 1) || exit 1;
sh ./killJavaServer.sh

