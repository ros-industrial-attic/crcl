#!/bin/sh

# This script was tested on Ubuntu 14.04. 
# It will download if necessary and run the java jar file.
# Java 8 is required for run the server. If Java 8 is not detected it will
# try to install it.

# On platforms other than Ubuntu 14.04 or if the automatic installation fails
# it may be easier just to manually install Java 8 from:
# https://adoptopenjdk.net/
#

if test "x${DEBUG_KILL_JAVA}" != "x" ; then
    set -x;
    jps -l
fi


PID=`jps -l | grep -i crcl | awk '{print $1}'`

kill -KILL ${PID}
