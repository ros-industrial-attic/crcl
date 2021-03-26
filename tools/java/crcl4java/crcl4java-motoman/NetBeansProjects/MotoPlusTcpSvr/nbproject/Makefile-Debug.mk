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
	${OBJECTDIR}/main.o \
	${OBJECTDIR}/mpFakeLib.o \
	${OBJECTDIR}/mpMain.o \
	${OBJECTDIR}/remoteFunctions.o \
	${OBJECTDIR}/tcpSvr.o


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
LDLIBSOPTIONS=-lpthread

# Build Targets
.build-conf: ${BUILD_SUBPROJECTS}
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/motoplustcpsvr

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/motoplustcpsvr: ${OBJECTFILES}
	${MKDIR} -p ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}
	${LINK.c} -o ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/motoplustcpsvr ${OBJECTFILES} ${LDLIBSOPTIONS}

${OBJECTDIR}/main.o: main.c
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.c) -g -DDO_SWAP=1 -DMP_FAKELIB_DEBUG=1 -DUSE_FAKE_MOTOPLUS=1 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/main.o main.c

${OBJECTDIR}/mpFakeLib.o: mpFakeLib.c
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.c) -g -DDO_SWAP=1 -DMP_FAKELIB_DEBUG=1 -DUSE_FAKE_MOTOPLUS=1 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/mpFakeLib.o mpFakeLib.c

${OBJECTDIR}/mpMain.o: mpMain.c
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.c) -g -DDO_SWAP=1 -DMP_FAKELIB_DEBUG=1 -DUSE_FAKE_MOTOPLUS=1 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/mpMain.o mpMain.c

${OBJECTDIR}/remoteFunctions.o: remoteFunctions.c
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.c) -g -DDO_SWAP=1 -DMP_FAKELIB_DEBUG=1 -DUSE_FAKE_MOTOPLUS=1 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/remoteFunctions.o remoteFunctions.c

${OBJECTDIR}/tcpSvr.o: tcpSvr.c
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.c) -g -DDO_SWAP=1 -DMP_FAKELIB_DEBUG=1 -DUSE_FAKE_MOTOPLUS=1 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/tcpSvr.o tcpSvr.c

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
