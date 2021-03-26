#!/bin/sh

set -x;

\rm -rf ~/.m2/repositories/com/zeroc
wget https://repo.zeroc.com/nexus/content/repositories/releases/com/zeroc/ant-ice/4.0.0/ant-ice-4.0.0.jar
wget https://repo.zeroc.com/nexus/content/repositories/releases/com/zeroc/ice/3.6.2/ice-3.6.2.jar

mvn install:install-file -Dfile=ice-3.6.2.jar -DgroupId=com.zeroc -DartifactId=ice -Dversion=3.6.2 -Dpackaging=jar -Dsources=Ice.jar
mvn install:install-file -Dfile=ant-ice-4.0.0.jar -DgroupId=com.zeroc -DartifactId=ant-ice -Dversion=4.0.0 -Dpackaging=jar
