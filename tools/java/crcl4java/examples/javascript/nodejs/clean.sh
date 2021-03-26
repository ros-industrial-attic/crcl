#!/bin/sh

set -x;

# Change to the directory this script is stored in. 
DIR=$(dirname $0 )
if test "x${DIR}" != "x" ; then
    if test -d "${DIR}" ; then
        cd "$DIR";
    fi
fi

if test ! -d node_modules  ; then
    \rm -f setup.ok
fi

#rm -rf node_modules
\rm -rf build
\rm -f crcl4java*.jar*
\rm -f crcl4java*.jar
