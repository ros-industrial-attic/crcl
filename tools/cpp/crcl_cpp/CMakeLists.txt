cmake_minimum_required(VERSION 2.6)

project(crcl_cpp)

find_package(catkin REQUIRED COMPONENTS
  roscpp
  )

find_package(xml_common REQUIRED)

catkin_package(
  CATKIN_DEPENDS roscpp
  INCLUDE_DIRS include
  LIBRARIES crcl_cpp
  )

include_directories(
  include
  ${xml_common_INCLUDE_DIRS}
  )

add_library(crcl_cpp
  src/CRCLCommandsClasses.cc
  src/CRCLStatusClasses.cc
  src/DataPrimitivesClasses.cc
  src/CRCLProgramInstanceClasses.cc
  src/CRCLCommandInstanceClasses.cc
  )

add_executable(CRCLCommandInstanceParser
  src/CRCLCommandInstanceParser.cc
  src/CRCLCommandInstanceLex.cc
  src/CRCLCommandInstanceYACC.cc
  include/crcl_cpp/CRCLCommandInstanceYACC.hh
  )

target_link_libraries(CRCLCommandInstanceParser
  crcl_cpp
  xml_instance
  )

add_executable(CRCLProgramInstanceParser
  src/CRCLProgramInstanceParser.cc
  src/CRCLProgramInstanceLex.cc
  src/CRCLProgramInstanceYACC.cc
  include/crcl_cpp/CRCLProgramInstanceYACC.hh
  )

target_link_libraries(CRCLProgramInstanceParser
  crcl_cpp
  xml_instance
  )

add_executable(CRCLStatusParser
  src/CRCLStatusParser.cc
  src/CRCLStatusLex.cc
  src/CRCLStatusYACC.cc
  include/crcl_cpp/CRCLStatusYACC.hh
  )

target_link_libraries(CRCLStatusParser
  crcl_cpp
  xml_instance
  )

add_executable(makeCommandEndCanon
  src/makeCommandEndCanon.cc
  )

target_link_libraries(makeCommandEndCanon
  crcl_cpp
  xml_instance
  )

add_executable(makeStatusExample
  src/makeStatusExample.cc
  )

target_link_libraries(makeStatusExample
  crcl_cpp
  xml_instance
  )

# 
# these commands create the source code from the schema, if the schema is newer
#

add_custom_command(OUTPUT ${PROJECT_SOURCE_DIR}/src/CRCLCommandInstanceClasses.cc ${PROJECT_SOURCE_DIR}/src/CRCLCommandInstanceParser.cc ${PROJECT_SOURCE_DIR}/src/CRCLCommandInstance.lex ${PROJECT_SOURCE_DIR}/src/CRCLCommandInstance.y ${PROJECT_SOURCE_DIR}/include/crcl_cpp/CRCLCommandInstanceClasses.hh
  DEPENDS ${PROJECT_SOURCE_DIR}/../../../schemas/CRCLCommandInstance.xsd
  COMMAND cd ${PROJECT_SOURCE_DIR}/../../../schemas && rosrun xml_common xmlInstanceParserGenerator -i xml_common/ -a crcl_cpp/ -x CRCLCommandInstance.xsd
  COMMAND cd ${PROJECT_SOURCE_DIR}/../../../schemas && mv -f *.y *.lex *.cc ${PROJECT_SOURCE_DIR}/src || true
  COMMAND cd ${PROJECT_SOURCE_DIR}/../../../schemas && mv -f *.hh ${PROJECT_SOURCE_DIR}/include/crcl_cpp || true
  )

add_custom_command(OUTPUT ${PROJECT_SOURCE_DIR}/src/CRCLProgramInstanceClasses.cc ${PROJECT_SOURCE_DIR}/src/CRCLProgramInstanceParser.cc ${PROJECT_SOURCE_DIR}/src/CRCLProgramInstance.lex ${PROJECT_SOURCE_DIR}/src/CRCLProgramInstance.y ${PROJECT_SOURCE_DIR}/include/crcl_cpp/CRCLProgramInstanceClasses.hh
  DEPENDS ${PROJECT_SOURCE_DIR}/../../../schemas/CRCLProgramInstance.xsd
  COMMAND cd ${PROJECT_SOURCE_DIR}/../../../schemas && rosrun xml_common xmlInstanceParserGenerator -i xml_common/ -a crcl_cpp/ -x CRCLProgramInstance.xsd
  COMMAND cd ${PROJECT_SOURCE_DIR}/../../../schemas && mv -f *.y *.lex *.cc ${PROJECT_SOURCE_DIR}/src || true
  COMMAND cd ${PROJECT_SOURCE_DIR}/../../../schemas && mv -f *.hh ${PROJECT_SOURCE_DIR}/include/crcl_cpp || true
  )

add_custom_command(OUTPUT ${PROJECT_SOURCE_DIR}/src/CRCLStatusClasses.cc ${PROJECT_SOURCE_DIR}/src/CRCLStatusParser.cc ${PROJECT_SOURCE_DIR}/src/CRCLStatus.lex ${PROJECT_SOURCE_DIR}/src/CRCLStatus.y ${PROJECT_SOURCE_DIR}/include/crcl_cpp/CRCLStatusClasses.hh
  DEPENDS ${PROJECT_SOURCE_DIR}/../../../schemas/CRCLStatus.xsd
  COMMAND cd ${PROJECT_SOURCE_DIR}/../../../schemas && rosrun xml_common xmlInstanceParserGenerator -i xml_common/ -a crcl_cpp/ -x CRCLStatus.xsd
  COMMAND cd ${PROJECT_SOURCE_DIR}/../../../schemas && mv -f *.y *.lex *.cc ${PROJECT_SOURCE_DIR}/src || true
  COMMAND cd ${PROJECT_SOURCE_DIR}/../../../schemas && mv -f *.hh ${PROJECT_SOURCE_DIR}/include/crcl_cpp || true
  )

add_custom_command(OUTPUT ${PROJECT_SOURCE_DIR}/src/CRCLCommandsClasses.cc ${PROJECT_SOURCE_DIR}/include/crcl_cpp/CRCLCommandsClasses.hh
  DEPENDS ${PROJECT_SOURCE_DIR}/../../../schemas/CRCLCommands.xsd
  COMMAND cd ${PROJECT_SOURCE_DIR}/../../../schemas && rosrun xml_common xmlInstanceParserGenerator -i xml_common/ -a crcl_cpp/ -x CRCLCommands.xsd
  COMMAND cd ${PROJECT_SOURCE_DIR}/../../../schemas && mv -f *.cc ${PROJECT_SOURCE_DIR}/src || true
  COMMAND cd ${PROJECT_SOURCE_DIR}/../../../schemas && mv -f *.hh ${PROJECT_SOURCE_DIR}/include/crcl_cpp || true
  )

add_custom_command(OUTPUT ${PROJECT_SOURCE_DIR}/src/CRCLDataPrimitivesClasses.cc ${PROJECT_SOURCE_DIR}/include/crcl_cpp/CRCLDataPrimitivesClasses.hh
  DEPENDS ${PROJECT_SOURCE_DIR}/../../../schemas/CRCLDataPrimitives.xsd
  COMMAND cd ${PROJECT_SOURCE_DIR}/../../../schemas && rosrun xml_common xmlInstanceParserGenerator -i xml_common/ -a crcl_cpp/ -x CRCLDataPrimitives.xsd
  COMMAND cd ${PROJECT_SOURCE_DIR}/../../../schemas && mv -f *.cc ${PROJECT_SOURCE_DIR}/src || true
  COMMAND cd ${PROJECT_SOURCE_DIR}/../../../schemas && mv -f *.hh ${PROJECT_SOURCE_DIR}/include/crcl_cpp || true
  )

find_package(BISON)
find_package(FLEX)

IF(BISON_FOUND)
  BISON_TARGET(Commmand ${PROJECT_SOURCE_DIR}/src/CRCLCommandInstance.y ${PROJECT_SOURCE_DIR}/src/CRCLCommandInstanceYACC.cc COMPILE_FLAGS "-d -l")
  BISON_TARGET(Program ${PROJECT_SOURCE_DIR}/src/CRCLProgramInstance.y ${PROJECT_SOURCE_DIR}/src/CRCLProgramInstanceYACC.cc COMPILE_FLAGS "-d -l")
  BISON_TARGET(Status ${PROJECT_SOURCE_DIR}/src/CRCLStatus.y ${PROJECT_SOURCE_DIR}/src/CRCLStatusYACC.cc COMPILE_FLAGS "-d -l")
ENDIF()

add_custom_command(OUTPUT ${PROJECT_SOURCE_DIR}/include/crcl_cpp/CRCLCommandInstanceYACC.hh
  COMMAND mv -f ${PROJECT_SOURCE_DIR}/src/CRCLCommandInstanceYACC.hh ${PROJECT_SOURCE_DIR}/include/crcl_cpp || true
  DEPENDS ${PROJECT_SOURCE_DIR}/src/CRCLCommandInstanceYACC.hh
  )

add_custom_command(OUTPUT ${PROJECT_SOURCE_DIR}/include/crcl_cpp/CRCLProgramInstanceYACC.hh
  COMMAND mv -f ${PROJECT_SOURCE_DIR}/src/CRCLProgramInstanceYACC.hh ${PROJECT_SOURCE_DIR}/include/crcl_cpp || true
  DEPENDS ${PROJECT_SOURCE_DIR}/src/CRCLProgramInstanceYACC.hh
  )

add_custom_command(OUTPUT ${PROJECT_SOURCE_DIR}/include/crcl_cpp/CRCLStatusYACC.hh
  COMMAND mv -f ${PROJECT_SOURCE_DIR}/src/CRCLStatusYACC.hh ${PROJECT_SOURCE_DIR}/include/crcl_cpp || true
  DEPENDS ${PROJECT_SOURCE_DIR}/src/CRCLStatusYACC.hh
  )

IF(FLEX_FOUND)
  FLEX_TARGET(Command ${PROJECT_SOURCE_DIR}/src/CRCLCommandInstance.lex ${PROJECT_SOURCE_DIR}/src/CRCLCommandInstanceLex.cc COMPILE_FLAGS "-L")
  FLEX_TARGET(Program ${PROJECT_SOURCE_DIR}/src/CRCLProgramInstance.lex ${PROJECT_SOURCE_DIR}/src/CRCLProgramInstanceLex.cc COMPILE_FLAGS "-L")
  FLEX_TARGET(Status ${PROJECT_SOURCE_DIR}/src/CRCLStatus.lex ${PROJECT_SOURCE_DIR}/src/CRCLStatusLex.cc COMPILE_FLAGS "-L")
ENDIF()

install(TARGETS crcl_cpp
  LIBRARY DESTINATION ${CATKIN_PACKAGE_LIB_DESTINATION}
  )

install(TARGETS CRCLCommandInstanceParser CRCLProgramInstanceParser CRCLStatusParser
  RUNTIME DESTINATION ${CATKIN_PACKAGE_BIN_DESTINATION}
  )

install(DIRECTORY include/${PROJECT_NAME}/
  DESTINATION ${CATKIN_PACKAGE_INCLUDE_DESTINATION}
  )
