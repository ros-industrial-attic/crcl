#!/bin/sh
set -x;

# Change to the directory this script is stored in. 
DIR=$(dirname $0 )
if test "x${DIR}" != "x" ; then
    if test -d "${DIR}" ; then
        cd "$DIR";
    fi
fi

\rm -f crcl4java*.jar
( cd matlab; sh clean.sh )
( cd javascript/nodejs; sh clean.sh )
( cd javascript/rhino; sh clean.sh )
( cd javascript/jjs; sh clean.sh )
( cd C++; sh clean.sh )
( cd java; sh clean.sh )
( cd python/jpype; sh clean.sh )
( cd python/jython; sh clean.sh )
