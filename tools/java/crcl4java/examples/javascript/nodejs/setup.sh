#!/bin/sh

# This script was tested on Ubuntu 14.04
set -x;

\rm -f setup.ok

sudo -n apt-get install wget nodejs npm openjdk-7-jdk  || (echo "apt-get install wget nodejs npm openjdk-7-jdk Failed. Please run sudo setup.sh or manually run each command in setup.sh as root." ; exit 1) || exit 1;


# Note in Ubuntu and Debian: "node" refers to a program that is unrelated to nodejs
# but on other platforms "node" is the name of the nodejs executable.
# Unfortunately one of the dependant npm install scripts refers to "node" 
# and has to be tricked into using the correct nodejs executable by moving
# the original program and setting up a symbolic link.
if test -f /usr/bin/node ; then
    sudo mv /usr/bin/node /usr/bin/node.original;
fi

sudo -n ln -s /usr/bin/nodejs /usr/bin/node   || (echo "ln -s /usr/bin/nodejs /usr/bin/node Failed. Please run sudo setup.sh or manually run each command in setup.sh as root." ; exit 1) || exit 1;


sudo -n npm install java || (echo "npm install java Failed. Please run sudo setup.sh or manually run each command in setup.sh as root." ; exit 1) || exit 1;


sudo -n rm /usr/bin/node || (echo "rm /usr/bin/node Failed. Please run sudo setup.sh or manually run each command in setup.sh as root." ; exit 1) || exit 1;

if test -f /usr/bin/node.original ; then
    sudo -n mv /usr/bin/node.original /usr/bin/node || (echo "mv /usr/bin/node.original /usr/bin/node Failed. Please run sudo setup.sh or manually run each command in setup.sh as root." ; exit 1) || exit 1;

fi
sudo -n chown -R `whoami` node_modules || (echo "chown -R `whoami` node_modules Failed. Please run sudo setup.sh or manually run each command in setup.sh as root." ; exit 1) || exit 1;


export CRCL4JAVA_UTILS_JAR=`ls -1t crcl4java-utils*.jar ../../../crcl4java-utils/target/crcl4java-utils*with-dependencies.jar | head -n 1`;
if test "x${CRCL4JAVA_UTILS_JAR}" = "x" ; then 
    remotejarurl="https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.4/crcl4java-utils-1.4-jar-with-dependencies.jar";
    echo "Downloading ${remotejarurl}";
    wget "${remotejarurl}"
    export CRCL4JAVA_UTILS_JAR=`ls -1t crcl4java-utils*.jar | head -n 1`
fi

touch setup.ok
