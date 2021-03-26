#!/bin/sh
set -x;

# Change to the directory this script is stored in. 
DIR=$(dirname $0 )
if test "x${DIR}" != "x" ; then
    if test -d "${DIR}" ; then
        cd "$DIR";
    fi
fi


## This will need to be edited on most systems that do NOT already have JAVA_HOME set.
JAVA_HOME_DEFAULT="/usr/lib/jvm/java-8-oracle/";

if test "x${JAVA_HOME}" = "x" -a -d "${JAVA_HOME_DEFAULT}"; then
    export JAVA_HOME="${JAVA_HOME_DEFAULT}";
fi

if ! "${JAVA_HOME}/bin/java" -version 2>&1 | grep java | grep version | grep 1.[789] >/dev/null 2>/dev/null ; then
    echo "Java 8 not found. Would you like to try to automatically install Oracle java 8(y/N)?";
    read confirm;
    if test "${confirm}x" = "yx" ; then
        sudo add-apt-repository ppa:webupd8team/java -y
        sudo apt-get update
        sudo apt-get install oracle-java8-installer
        sudo apt-get install oracle-java8-set-default
    fi
    if test "x${JAVA_HOME}" = "x" -a -d "${JAVA_HOME_DEFAULT}"; then
        export JAVA_HOME="${JAVA_HOME_DEFAULT}";
    fi
fi

if ! "${JAVA_HOME}/bin/java" -version 2>&1 | grep java | grep version | grep 1.[789] >/dev/null 2>/dev/null ; then
    echo "Please install JDK 1.8 or higher and set JAVA_HOME to this directory";
    exit 1;
fi



CRCL4JAVA_UTILS_JAR=`ls -1t crcl4java-utils*.jar ../../crcl4java-utils/target/crcl4java-utils*with-dependencies.jar | head -n 1`;
if test "x${CRCL4JAVA_UTILS_JAR}" = "x" ; then 
    wget "https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.4/crcl4java-utils-1.4-jar-with-dependencies.jar"
    #wget "https://oss.sonatype.org/content/repositories/snapshots/com/github/wshackle/crcl4java-utils/1.5-SNAPSHOT/crcl4java-utils-1.4-20160505.202933-4-jar-with-dependencies.jar"
    CRCL4JAVA_UTILS_JAR=`ls -1t crcl4java-utils*.jar | head -n 1`
fi

mkdir generated;

( 
    set -x;
    cd generated;
    JAVA4CPP_JAR=`ls -1t java4cpp*.jar | head -n 1`;
    if test "x${JAVA4CPP_JAR}" = "x" ; then
        wget "http://repo.maven.apache.org/maven2/com/github/wshackle/java4cpp/1.5/java4cpp-1.5-jar-with-dependencies.jar"
        #wget "http://repo.maven.apache.org/maven2/com/github/wshackle/java4cpp/1.4/java4cpp-1.4-jar-with-dependencies.jar"
        JAVA4CPP_JAR=`ls -1t java4cpp*.jar | head -n 1`;
    fi
    "${JAVA_HOME}/bin/java" -jar "${JAVA4CPP_JAR}" -p crcl -n crclj -j "../${CRCL4JAVA_UTILS_JAR}" || exit 1;
) || exit 1;


if test "x${JVM_LIB_DIR}" = "x" ; then
    export JVM_LIB_DIR=`find "${JAVA_HOME}/" -name libjvm.so | head -n 1 | sed 's#libjvm.so##g'`;
fi

rm jvm_lib_dir
rm java_home

ln -s "${JVM_LIB_DIR}" jvm_lib_dir
ln -s "${JAVA_HOME}" java_home

g++ -O0 -g -fPIC -shared -I "${JAVA_HOME}/include"  -I ./generated -I "${JAVA_HOME}/include/linux" generated/crclj*.cpp -L "${JVM_LIB_DIR}" -Wl,--rpath "${JVM_LIB_DIR}" -ljvm -o libcrclGenerated.so || (echo "Build Failed" ; exit 1) || exit 1;
g++ -O0 -g -I "${JAVA_HOME}/include"  -I ./generated -I "${JAVA_HOME}/include/linux" crclClient.cpp  -L `pwd` -Wl,--rpath `pwd`  -L "${JVM_LIB_DIR}" -Wl,--rpath "${JVM_LIB_DIR}" -ljvm  -lcrclGenerated -o crclClient || (echo "Build Failed" ; exit 1) || exit 1;
g++ -O0 -g -I "${JAVA_HOME}/include"  -I ./generated -I "${JAVA_HOME}/include/linux" crclServer.cpp  -L `pwd` -Wl,--rpath `pwd`  -L "${JVM_LIB_DIR}" -Wl,--rpath "${JVM_LIB_DIR}" -ljvm  -lcrclGenerated -o crclServer || (echo "Build Failed" ; exit 1) || exit 1;
