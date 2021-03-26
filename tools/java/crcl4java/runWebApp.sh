#!/bin/sh

echo "Running $0 $* from " `pwd`
cat $0

set -x;

#CRCL4JAVARUNWEB.sh
if test ! -f ./runWebApp.sh || grep -v '#CRCL4JAVARUNWEB.sh'  ./runWebApp.sh 2>/dev/null >/dev/null ; then 
    cd "${0%%runWebApp.sh}";
fi

if test "x" != "x${JAVA_HOME}" ; then
    export PATH="${JAVA_HOME}/bin/:${PATH}";
fi

find . -name crcl4java-vaadin-webapp\*war-exec.jar
JARFILE=`find . -name crcl4java-vaadin-webapp\*war-exec.jar | head -n 1`;

if test "x${JARFILE}" = "x" ; then
    export JARFILE=crcl4java-vaadin-webapp/target/crcl4java-vaadin-webapp-1.8-war-exec.jar
fi

if test ! -f "${JARFILE}" ; then
#    which mvn
#    which mvn || ( echo "No mvn command found with PATH=${PATH}" && echo "Please install maven.  https://maven.apache.org/" && false)
##    /usr/bin/mvn -version
##    /etc/alternatives/mvn -version
##    file /usr/bin/mvn
##    file /etc/alternatives/mvn
##    file /usr/share/maven/bin/mvn
##    /usr/share/maven/bin/mvn -version
#    mvn -version
##    mvn -version || ( echo "Please install maven." && false)
#    mvn -Pskip_tests -Pwithweb install
#    ( cd crcl4java-vaadin-webapp; mvn -Pskip_tests -Ptomcat_embed install ; cd ..)
    echo "Please run maven from https://maven.apache.org/ with the commands"
    echo "mvn -Pskip_tests -Pwithweb install"
    echo "( cd crcl4java-vaadin-webapp; mvn -Pskip_tests -Ptomcat_embed install ; cd ..)"
    exit 1;
else 
    echo "Found executable jar file already compiled. ${JARFILE}";

    \rm -f run[0-9]*.log run[0-9]*.err >/dev/null 2>/dev/null || true

    pids=`jps | grep crcl4java-vaadin-webapp | awk '{print $1}'`
    if test "x${pids}x" != "xx" ; then 
        echo "Killing previously started copies of this application. ${pids}"
        kill -KILL ${pids};
    fi

    echo "Running java -jar ${JARFILE}"

    ls -ld .extract
    rm -rf .extract
    pwd
    java  -jar "${JARFILE}" > run$$.log 2> run$$.err &
    jpid=$!

    sleep 10
    tail -f run$$.log &
    taillogpid=$!
    tail -f run$$.err &
    tailerrpid=$!

    firefox http://localhost:8080/crcl4java-vaadin-webapp/ &

    sleep 1
    echo 
    echo "Press enter to kill the server"
    read var

    kill ${jpid} ${taillogpid} ${tailerrpid}

    pids=`jps | grep crcl4java-vaadin-webapp | awk '{print $1}'`
    if test "x${pids}x" != "xx" ; then 
        echo "Killing previously started copies of this application. ${pids}"
        kill -KILL ${pids};
    fi

fi;

