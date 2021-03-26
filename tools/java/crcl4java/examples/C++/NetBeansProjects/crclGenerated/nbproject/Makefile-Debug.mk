#
# Generated Makefile - do not edit!
#
# Edit the Makefile in the project folder instead (../Makefile). Each target
# has a -pre and a -post target defined where you can add customized code.
#
# This makefile implements configuration specific macros and targets.


# Environment
MKDIR=mkdir
CP=cp
GREP=grep
NM=nm
CCADMIN=CCadmin
RANLIB=ranlib
CC=gcc
CCC=g++
CXX=g++
FC=gfortran
AS=as

# Macros
CND_PLATFORM=GNU-Linux
CND_DLIB_EXT=so
CND_CONF=Debug
CND_DISTDIR=dist
CND_BUILDDIR=build

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/_ext/92efb7ef/crclj00.o \
	${OBJECTDIR}/_ext/92efb7ef/crclj01.o \
	${OBJECTDIR}/_ext/92efb7ef/crclj02.o \
	${OBJECTDIR}/_ext/92efb7ef/crclj03.o \
	${OBJECTDIR}/_ext/92efb7ef/crclj04.o \
	${OBJECTDIR}/_ext/92efb7ef/crclj05.o \
	${OBJECTDIR}/_ext/92efb7ef/crclj06.o \
	${OBJECTDIR}/_ext/92efb7ef/crclj07.o \
	${OBJECTDIR}/_ext/92efb7ef/crclj08.o \
	${OBJECTDIR}/_ext/92efb7ef/crclj09.o \
	${OBJECTDIR}/_ext/92efb7ef/crclj10.o \
	${OBJECTDIR}/_ext/92efb7ef/crclj11.o \
	${OBJECTDIR}/_ext/92efb7ef/crclj12.o \
	${OBJECTDIR}/_ext/92efb7ef/crclj13.o \
	${OBJECTDIR}/_ext/92efb7ef/crclj14.o \
	${OBJECTDIR}/_ext/92efb7ef/crclj15.o


# C Compiler Flags
CFLAGS=

# CC Compiler Flags
CCFLAGS=
CXXFLAGS=

# Fortran Compiler Flags
FFLAGS=

# Assembler Flags
ASFLAGS=

# Link Libraries and Options
LDLIBSOPTIONS=-L../../jvm_lib_dir -ljvm

# Build Targets
.build-conf: ${BUILD_SUBPROJECTS}
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libcrclGenerated.${CND_DLIB_EXT}

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libcrclGenerated.${CND_DLIB_EXT}: ${OBJECTFILES}
	${MKDIR} -p ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}
	${LINK.cc} -o ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libcrclGenerated.${CND_DLIB_EXT} ${OBJECTFILES} ${LDLIBSOPTIONS} -shared -fPIC

${OBJECTDIR}/_ext/92efb7ef/crclj00.o: ../../generated/crclj00.cpp
	${MKDIR} -p ${OBJECTDIR}/_ext/92efb7ef
	${RM} "$@.d"
	$(COMPILE.cc) -g -I../../java_home/include -I../../java_home/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/92efb7ef/crclj00.o ../../generated/crclj00.cpp

${OBJECTDIR}/_ext/92efb7ef/crclj01.o: ../../generated/crclj01.cpp
	${MKDIR} -p ${OBJECTDIR}/_ext/92efb7ef
	${RM} "$@.d"
	$(COMPILE.cc) -g -I../../java_home/include -I../../java_home/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/92efb7ef/crclj01.o ../../generated/crclj01.cpp

${OBJECTDIR}/_ext/92efb7ef/crclj02.o: ../../generated/crclj02.cpp
	${MKDIR} -p ${OBJECTDIR}/_ext/92efb7ef
	${RM} "$@.d"
	$(COMPILE.cc) -g -I../../java_home/include -I../../java_home/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/92efb7ef/crclj02.o ../../generated/crclj02.cpp

${OBJECTDIR}/_ext/92efb7ef/crclj03.o: ../../generated/crclj03.cpp
	${MKDIR} -p ${OBJECTDIR}/_ext/92efb7ef
	${RM} "$@.d"
	$(COMPILE.cc) -g -I../../java_home/include -I../../java_home/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/92efb7ef/crclj03.o ../../generated/crclj03.cpp

${OBJECTDIR}/_ext/92efb7ef/crclj04.o: ../../generated/crclj04.cpp
	${MKDIR} -p ${OBJECTDIR}/_ext/92efb7ef
	${RM} "$@.d"
	$(COMPILE.cc) -g -I../../java_home/include -I../../java_home/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/92efb7ef/crclj04.o ../../generated/crclj04.cpp

${OBJECTDIR}/_ext/92efb7ef/crclj05.o: ../../generated/crclj05.cpp
	${MKDIR} -p ${OBJECTDIR}/_ext/92efb7ef
	${RM} "$@.d"
	$(COMPILE.cc) -g -I../../java_home/include -I../../java_home/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/92efb7ef/crclj05.o ../../generated/crclj05.cpp

${OBJECTDIR}/_ext/92efb7ef/crclj06.o: ../../generated/crclj06.cpp
	${MKDIR} -p ${OBJECTDIR}/_ext/92efb7ef
	${RM} "$@.d"
	$(COMPILE.cc) -g -I../../java_home/include -I../../java_home/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/92efb7ef/crclj06.o ../../generated/crclj06.cpp

${OBJECTDIR}/_ext/92efb7ef/crclj07.o: ../../generated/crclj07.cpp
	${MKDIR} -p ${OBJECTDIR}/_ext/92efb7ef
	${RM} "$@.d"
	$(COMPILE.cc) -g -I../../java_home/include -I../../java_home/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/92efb7ef/crclj07.o ../../generated/crclj07.cpp

${OBJECTDIR}/_ext/92efb7ef/crclj08.o: ../../generated/crclj08.cpp
	${MKDIR} -p ${OBJECTDIR}/_ext/92efb7ef
	${RM} "$@.d"
	$(COMPILE.cc) -g -I../../java_home/include -I../../java_home/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/92efb7ef/crclj08.o ../../generated/crclj08.cpp

${OBJECTDIR}/_ext/92efb7ef/crclj09.o: ../../generated/crclj09.cpp
	${MKDIR} -p ${OBJECTDIR}/_ext/92efb7ef
	${RM} "$@.d"
	$(COMPILE.cc) -g -I../../java_home/include -I../../java_home/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/92efb7ef/crclj09.o ../../generated/crclj09.cpp

${OBJECTDIR}/_ext/92efb7ef/crclj10.o: ../../generated/crclj10.cpp
	${MKDIR} -p ${OBJECTDIR}/_ext/92efb7ef
	${RM} "$@.d"
	$(COMPILE.cc) -g -I../../java_home/include -I../../java_home/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/92efb7ef/crclj10.o ../../generated/crclj10.cpp

${OBJECTDIR}/_ext/92efb7ef/crclj11.o: ../../generated/crclj11.cpp
	${MKDIR} -p ${OBJECTDIR}/_ext/92efb7ef
	${RM} "$@.d"
	$(COMPILE.cc) -g -I../../java_home/include -I../../java_home/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/92efb7ef/crclj11.o ../../generated/crclj11.cpp

${OBJECTDIR}/_ext/92efb7ef/crclj12.o: ../../generated/crclj12.cpp
	${MKDIR} -p ${OBJECTDIR}/_ext/92efb7ef
	${RM} "$@.d"
	$(COMPILE.cc) -g -I../../java_home/include -I../../java_home/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/92efb7ef/crclj12.o ../../generated/crclj12.cpp

${OBJECTDIR}/_ext/92efb7ef/crclj13.o: ../../generated/crclj13.cpp
	${MKDIR} -p ${OBJECTDIR}/_ext/92efb7ef
	${RM} "$@.d"
	$(COMPILE.cc) -g -I../../java_home/include -I../../java_home/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/92efb7ef/crclj13.o ../../generated/crclj13.cpp

${OBJECTDIR}/_ext/92efb7ef/crclj14.o: ../../generated/crclj14.cpp
	${MKDIR} -p ${OBJECTDIR}/_ext/92efb7ef
	${RM} "$@.d"
	$(COMPILE.cc) -g -I../../java_home/include -I../../java_home/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/92efb7ef/crclj14.o ../../generated/crclj14.cpp

${OBJECTDIR}/_ext/92efb7ef/crclj15.o: ../../generated/crclj15.cpp
	${MKDIR} -p ${OBJECTDIR}/_ext/92efb7ef
	${RM} "$@.d"
	$(COMPILE.cc) -g -I../../java_home/include -I../../java_home/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/92efb7ef/crclj15.o ../../generated/crclj15.cpp

# Subprojects
.build-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}

# Subprojects
.clean-subprojects:

# Enable dependency checking
.dep.inc: .depcheck-impl

include .dep.inc
