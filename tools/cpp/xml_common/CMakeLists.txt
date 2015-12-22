cmake_minimum_required(VERSION 2.6)

project(xml_common)

## Find catkin macros and libraries
## if COMPONENTS list like find_package(catkin REQUIRED COMPONENTS xyz)
## is used, also find other catkin packages
find_package(catkin REQUIRED COMPONENTS
  roscpp
  )

# 
# This makes the local project's include and lib directories available to
# other local projects, without requiring installation in /opt/ros/
# 
catkin_package(
  CATKIN_DEPENDS roscpp
  INCLUDE_DIRS include
  LIBRARIES xml_common xml_instance
)

include_directories(
  include
)

add_library(xml_common 
  src/patternLex.cc
  src/patternYACC.cc
  src/xmlSchemaClasses.cc
  src/xmlSchemaLex.cc
  src/xmlSchemaYACC.cc
  )

add_library(xml_instance
  src/xmlSchemaInstance.cc
  )

add_executable(xmlSchemaParser 
  src/xmlSchemaParser.cc
  )

target_link_libraries(xmlSchemaParser
  xml_common
)

add_executable(xmlInstanceParserGenerator
  src/xmlInstanceParserGenerator.cc
  )

target_link_libraries(xmlInstanceParserGenerator
  xml_common
)

find_package(BISON)
find_package(FLEX)

IF(BISON_FOUND)
  BISON_TARGET(Pattern ${PROJECT_SOURCE_DIR}/src/pattern.y ${PROJECT_SOURCE_DIR}/src/patternYACC.cc COMPILE_FLAGS "-d -l -p yyre")
  BISON_TARGET(Schema ${PROJECT_SOURCE_DIR}/src/xmlSchema.y ${PROJECT_SOURCE_DIR}/src/xmlSchemaYACC.cc COMPILE_FLAGS "-d -l")
ENDIF()

IF(FLEX_FOUND)
  FLEX_TARGET(Pattern ${PROJECT_SOURCE_DIR}/src/pattern.lex ${PROJECT_SOURCE_DIR}/src/patternLex.cc COMPILE_FLAGS "-L -Pyyre")
  FLEX_TARGET(Schema ${PROJECT_SOURCE_DIR}/src/xmlSchema.lex ${PROJECT_SOURCE_DIR}/src/xmlSchemaLex.cc COMPILE_FLAGS "-L")
ENDIF()

install(TARGETS xml_common xml_instance
  LIBRARY DESTINATION ${CATKIN_PACKAGE_LIB_DESTINATION}
  )

install(TARGETS xmlSchemaParser xmlInstanceParserGenerator
  RUNTIME DESTINATION ${CATKIN_PACKAGE_BIN_DESTINATION}
  )

install(DIRECTORY include/${PROJECT_NAME}/
  DESTINATION ${CATKIN_PACKAGE_INCLUDE_DESTINATION}
  )
