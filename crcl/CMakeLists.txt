cmake_minimum_required(VERSION 2.6)

project(crcl)

set(ROS_BUILD_TYPE Debug)

## Find catkin macros and libraries
## if COMPONENTS list like find_package(catkin REQUIRED COMPONENTS xyz)
## is used, also find other catkin packages
find_package(catkin REQUIRED COMPONENTS
  roscpp
  )

find_package(xml_common)

catkin_package(
  CATKIN_DEPENDS roscpp
)

# 
# This makes the local project's include and lib directories available to
# other local projects, without requiring installation in /opt/ros/
# 
catkin_package(
  INCLUDE_DIRS include
  LIBRARIES crcl
)

include_directories(
  include
  src
  ${xml_common_INCLUDE_DIRS}
)

add_library(crcl
  src/CRCLCommandsClasses.cc
  src/CRCLStatusClasses.cc
  src/DataPrimitivesClasses.cc
  )

add_executable(CRCLCommandInstanceParser
  src/CRCLCommandInstanceParser.cc
  src/CRCLCommandInstanceClasses.cc
  src/CRCLCommandInstanceLex.cc
  src/CRCLCommandInstanceYACC.cc
  include/crcl/CRCLCommandInstanceYACC.hh
)

target_link_libraries(CRCLCommandInstanceParser
  crcl
  xml_instance
)

add_executable(CRCLProgramInstanceParser
  src/CRCLProgramInstanceParser.cc
  src/CRCLProgramInstanceClasses.cc
  src/CRCLProgramInstanceLex.cc
  src/CRCLProgramInstanceYACC.cc
  include/crcl/CRCLProgramInstanceYACC.hh
)

target_link_libraries(CRCLProgramInstanceParser
  crcl
  xml_instance
)

add_executable(CRCLStatusParser
  src/CRCLStatusParser.cc
  src/CRCLStatusLex.cc
  src/CRCLStatusYACC.cc
  include/crcl/CRCLStatusYACC.hh
)

target_link_libraries(CRCLStatusParser
  crcl
  xml_instance
)

find_package(BISON)
find_package(FLEX)

IF(BISON_FOUND)
  BISON_TARGET(Commmand ${PROJECT_SOURCE_DIR}/src/CRCLCommandInstance.y ${PROJECT_SOURCE_DIR}/src/CRCLCommandInstanceYACC.cc COMPILE_FLAGS "-d -l")
  BISON_TARGET(Program ${PROJECT_SOURCE_DIR}/src/CRCLProgramInstance.y ${PROJECT_SOURCE_DIR}/src/CRCLProgramInstanceYACC.cc COMPILE_FLAGS "-d -l")
  BISON_TARGET(Status ${PROJECT_SOURCE_DIR}/src/CRCLStatus.y ${PROJECT_SOURCE_DIR}/src/CRCLStatusYACC.cc COMPILE_FLAGS "-d -l")
ENDIF()

add_custom_command(OUTPUT ${PROJECT_SOURCE_DIR}/include/crcl/CRCLCommandInstanceYACC.hh
  COMMAND mv -f ${PROJECT_SOURCE_DIR}/src/CRCLCommandInstanceYACC.hh ${PROJECT_SOURCE_DIR}/include/crcl || true
  DEPENDS ${PROJECT_SOURCE_DIR}/src/CRCLCommandInstanceYACC.hh
  )

add_custom_command(OUTPUT ${PROJECT_SOURCE_DIR}/include/crcl/CRCLProgramInstanceYACC.hh
  COMMAND mv -f ${PROJECT_SOURCE_DIR}/src/CRCLProgramInstanceYACC.hh ${PROJECT_SOURCE_DIR}/include/crcl || true
  DEPENDS ${PROJECT_SOURCE_DIR}/src/CRCLProgramInstanceYACC.hh
  )

add_custom_command(OUTPUT ${PROJECT_SOURCE_DIR}/include/crcl/CRCLStatusYACC.hh
  COMMAND mv -f ${PROJECT_SOURCE_DIR}/src/CRCLStatusYACC.hh ${PROJECT_SOURCE_DIR}/include/crcl || true
  DEPENDS ${PROJECT_SOURCE_DIR}/src/CRCLStatusYACC.hh
  )

IF(FLEX_FOUND)
  FLEX_TARGET(Command ${PROJECT_SOURCE_DIR}/src/CRCLCommandInstance.lex ${PROJECT_SOURCE_DIR}/src/CRCLCommandInstanceLex.cc COMPILE_FLAGS "-L")
  FLEX_TARGET(Program ${PROJECT_SOURCE_DIR}/src/CRCLProgramInstance.lex ${PROJECT_SOURCE_DIR}/src/CRCLProgramInstanceLex.cc COMPILE_FLAGS "-L")
  FLEX_TARGET(Status ${PROJECT_SOURCE_DIR}/src/CRCLStatus.lex ${PROJECT_SOURCE_DIR}/src/CRCLStatusLex.cc COMPILE_FLAGS "-L")
ENDIF()

install(TARGETS crcl
  LIBRARY DESTINATION ${CATKIN_PACKAGE_LIB_DESTINATION}
  )

install(TARGETS CRCLCommandInstanceParser CRCLProgramInstanceParser CRCLStatusParser
  RUNTIME DESTINATION ${CATKIN_PACKAGE_BIN_DESTINATION}
  )

install(DIRECTORY include/${PROJECT_NAME}/
  DESTINATION ${CATKIN_PACKAGE_INCLUDE_DESTINATION}
  )
