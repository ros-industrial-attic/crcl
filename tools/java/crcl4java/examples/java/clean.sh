#!/bin/sh


# Change to the directory this script is stored in. 
DIR=$(dirname $0 )
if test "x${DIR}" != "x" ; then
    if test -d "${DIR}" ; then
        cd "$DIR";
    fi
fi


\rm -f *.class crcl4java*.jar*
\rm -rf crcl4java*javadoc

\rm -f crcl4java*.jar
