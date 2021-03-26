#!/bin/sh

# Change to the directory this script is stored in. 
DIR=$(dirname $0 )
if test "x${DIR}" != "x" ; then
    if test -d "${DIR}" ; then
        cd "$DIR";
    fi
fi


killall -KILL crclServer
killall -KILL crclClient

\rm -f jvm_lib_dir java_home crclj[0-9]*.cpp crclj.h crclj_fwd.h crclj[0-9]*.h core core.[0-9]* hs_*.log *.o *.so crclj_test crclBlockingClient java4cpp*.jar* crcl4java*.jar* 2> /dev/null || true
\rm -rf generated
\rm -f crcl4java*.jar
\rm -f crclServer crclClient

