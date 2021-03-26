#!/bin/bash

set -x;

mkdir -p /tmp/$$/
mv src/main/java/crcl/utils/CRCLCopier.java /tmp/$$/
java -jar ~/NetBeansProjects/java4cpp/target/java4cpp-1.6-SNAPSHOT-jar-with-dependencies.jar -j ../crcl4java-base/target/crcl4java-base-1.9.1-SNAPSHOT.jar -p crcl.base --excludedclassnames crcl.base.ObjectFactory --nocopyclassnames crcl.utils.CRCLCommandWrapper --javacloner crcl.utils.CRCLCopier.java
mv crcl/utils/CRCLCopier.java src/main/java/crcl/utils/ 
rmdir crcl/utils/ 
rmdir  crcl
