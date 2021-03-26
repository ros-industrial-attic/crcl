#!/bin/bash

set -x;

# Change to the directory this script is stored in. 
DIR=$(dirname $0 )
if test "x${DIR}" != "x" ; then
    if test -d "${DIR}" ; then
        cd "$DIR";
    fi
fi

sh ./runCppServer.sh &
sleep 1
sh ./runCppClient.sh || (sh ./killCppServer.sh;  echo "Run Client Failed"; exit 1) || exit 1;
sh ./killCppServer.sh

