#!/bin/sh

export crcl_mvn_utils_url="https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/${project.name}/${project.version}";
export crcl_utils_finalname="${project.build.finalName}";
export crcl_utils_fullpath_jar="${project.build.directory}/${project.build.finalName}-jar-with-dependencies.jar";
export crcl_utils_remote_jar="${crcl_mvn_utils_url}/${project.build.finalName}-jar-with-dependencies.jar";
export crcl_utils_jar="${project.build.finalName}-jar-with-dependencies.jar";
export crcl_utils_fullpath_javadoc_jar="${project.build.directory}/${project.build.finalName}-javadoc.jar";
export crcl_utils_remote_javadoc_jar="${crcl_mvn_utils_url}/${project.build.finalName}-javadoc.jar";
export crcl_utils_javadoc_jar="${project.build.finalName}-javadoc.jar";
export fullpath_javadoc_jar="${crcl_utils_fullpath_javadoc_jar}";
export remote_javadoc_jar="${crcl_utils_remote_javadoc_jar}";
export javadoc_jar="${crcl_utils_javadoc_jar}";
export fullpath_jar="${crcl_utils_fullpath_jar}";
export jar="${crcl_utils_jar}";


