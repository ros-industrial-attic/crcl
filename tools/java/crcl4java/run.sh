#!/bin/sh

#CRCL4JAVARUN.sh
if test ! -f ./run.sh || grep -v '#CRCL4JAVARUN.sh'  ./run.sh  ; then 
    cd "${0%%run.sh}";
fi

if test "x" != "x${JAVA_HOME}" ; then
    export PATH="${JAVA_HOME}/bin/:${PATH}";
fi

export JARFILE=`find crcl4java-ui -name crcl4java-ui\*jar-with-dependencies.jar | head -n 1`;
echo "JARFILE=${JARFILE}"

if test ! -f "${JARFILE}" ; then
    mvn -version || ( echo "Please install maven." && false)
    mvn -Pskip_tests package
    export JARFILE=`find crcl4java-ui -name crcl4java-ui\*jar-with-dependencies.jar | head -n 1`;
fi;

\rm -f run[0-9]*.log run[0-9]*.err >/dev/null 2>/dev/null || true

echo "JARFILE=${JARFILE}"

java -jar "${JARFILE}" $* > run$$.log 2> run$$.err

