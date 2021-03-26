/*
 * This software is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * Software Copyright/Warranty Disclaimer
 *
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain.
 *
 * This software is experimental. NIST assumes no responsibility whatsoever
 * for its use by other parties, and makes no guarantees, expressed or
 * implied, about its quality, reliability, or any other characteristic.
 * We would appreciate acknowledgement if the software is used.
 * This software can be redistributed and/or modified freely provided
 * that any derivative works bear some notice that they are derived from it,
 * and any modified versions bear some notice that they have been modified.
 *
 *  See http://www.copyright.gov/title17/92chap1.html#105
 *
 */

/*********************************
This software provides the server side to be paired with
the client side library in Java named crcl4java-motoman.


NOTE: To run on motoman requires:
MotoPlus SDK for Visual Studion(FS100) 169272.3 v1.1.0

This requires a USB dongle.

On Windows 7 compiling requires one to  first set
EnableLUA to 0
in
HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Policies\System
with regedit.exe

To INSTALL after compiling:

Copy   motoPlusTcpServer.out from motoPlusTcpServer\FS100\motoPlusTcpServer subdirectory
to a USB stick.

From C:\Users\Public\Documents\YaskawaMotomanDocumentation\MotoPlus_SDK_For_Visual_Studio
read 169286-1CD.pdf
(aka "MOTOPLUS SDK FOR VISUAL STUDIO USER'S MANUAL")

Insert the USB stick in the back of Motoman pendant.
Reboot into maintenance mode by holding main menu during bootup and choosing special mode when offered.
Use System -> Security -> Mode -> Management Mode
Default password is 99999999

MotoPlus APL. -> Load (User Application) -> select motoPlusTcpServer.out
press Enter -> YES -> (Overwrite file?) YES

Reboot.


Running in simulation on a any regular PC
can be done without Motoman software with mpFakeLib.


 **********************************/

#include "motoPlus.h"
#include "remoteFunctions.h"

#include <stdio.h>
#include <errno.h>
#include <string.h>

#ifdef __gnu_linux__
#include <netinet/in.h>
#include <stdlib.h>
#include <sys/select.h>
#endif

extern void *malloc(size_t);
extern void free(void *);

#define BUFF_MAX    1023

static int recvN(int handle, char *buf, int n, int flags) {
    int lastRecv = -1;
    int totalRecv = 0;
    do {
        lastRecv = mpRecv(handle, (buf + totalRecv), n - totalRecv, flags);
        if (lastRecv == 0) {
            fprintf(stderr, "NIST motoPlustTcpSvr: recv returned 0\n");
            return lastRecv;
        }
        if (lastRecv < 1) {
            fprintf(stderr, "NIST motoPlustTcpSvr: recv error : %s\n", strerror(errno));
            return lastRecv;
        }
        totalRecv += lastRecv;
    } while (totalRecv < n);
    return totalRecv;
}

static int sendN(int handle, char *buf, int n, int flags) {
    int lastSend = -1;
    int totalSend = 0;
    do {
        lastSend = mpSend(handle, (buf + totalSend), n - totalSend, flags);
        if (lastSend < 1) {
            fprintf(stderr, "NIST motoPlustTcpSvr: send error : %s\n", strerror(errno));
            return lastSend;
        }
        totalSend += lastSend;
    } while (totalSend < n);
    return totalSend;
}

static int checkedSendN(int handle, char *buf, int n, int flags) {
    int sendRet = sendN(handle, buf, n, flags);
    if (sendRet != n) {
        fprintf(stderr, "NIST motoPlustTcpSvr: sendRet = %d != %d\n", sendRet, n);
        return -1;
    }
    return 0;
}

#ifdef DO_SWAP

static void swap(char *buf, int offset, int sz) {
    int i = 0;
    int ret = -1;
    char tmp;
    /*
        printf("swap(%p,%d,%d)\n",buf,offset,sz);
     */
    for (i = 0; i < sz / 2; i++) {
        tmp = buf[offset + i];
        buf[offset + i] = buf[offset + sz - 1 - i];
        buf[offset + sz - 1 - i] = tmp;
    }
}
#endif

// Stupid C won't include a lib function that won't make 
// me tear my hair out looking to make sure it is defined on every
// platform to do this
// so it gets reimplemented yet again.

#ifndef DO_NOT_NEED_STANDARD_INT_TYPES
typedef short int16_t;
typedef int int32_t;
#endif

static int16_t getInt16(char *buf, int offset) {
#ifdef DO_SWAP
    swap(buf, offset, 2);
#endif
    return *((int16_t *) (buf + offset));
}

static void setInt16(char *buf, int offset, int16_t val) {
    *((int16_t *) (buf + offset)) = val;
#ifdef DO_SWAP
    swap(buf, offset, 2);
#endif
}

static int32_t getInt32(char *buf, int offset) {
#ifdef DO_SWAP
    swap(buf, offset, 4);
#endif
    return *((int32_t *) (buf + offset));
}

static void setInt32(char *buf, int offset, int32_t val) {

    *((int32_t *) (buf + offset)) = val;
#ifdef DO_SWAP
    swap(buf, offset, 4);
#endif
}

int returnSingleIntRet(int acceptHandle, char *outBuffer, int ret) {
    setInt32(outBuffer, 0, 4);
    setInt32(outBuffer, 4, ret);
    int sendRet = sendN(acceptHandle, outBuffer, 8, 0);
    if (sendRet != 8) {
        fprintf(stderr, "NIST motoPlustTcpSvr: sendRet = %d != 8\n", sendRet);
        return -1;
    }
    return 0;
}

// Return 0 for success, anything else will be treated like a fatal error closing
// the connection.

int handleSys1FunctionRequest(int acceptHandle, char *inBuffer, char *outBuffer, int type, int msgSize) {

    int i = 0;
    MP_VAR_INFO varInfo[25];
    MP_VAR_DATA varData[25];
    MP_IO_INFO ioInfo[25];
    MP_IO_DATA ioData[25];
    LONG rData[25];
    USHORT iorData[25];
    MP_MODE_RSP_DATA modeData;
    MP_CYCLE_RSP_DATA cycleData;
    MP_ALARM_STATUS_RSP_DATA alarmStatusData;
    MP_ALARM_CODE_RSP_DATA alarmCodeData;
    LONG num;
    int ret;
    MP_CTRL_GRP_SEND_DATA ctrlGrpSendData;
    MP_CART_POS_RSP_DATA cartPosRspData;
    MP_PULSE_POS_RSP_DATA pulsePosRspData;
    MP_FB_PULSE_POS_RSP_DATA fbPulsePosRspData;
    MP_DEG_POS_RSP_DATA_EX degPosRspDataEx;
    MP_SERVO_POWER_RSP_DATA servoPowerRspData;
    MP_SERVO_POWER_SEND_DATA servoPowerSendData;
    MP_STD_RSP_DATA stdRspData;
    MP_SVAR_SEND_INFO svarSendInfo[25];
    MP_SVAR_RECV_INFO svarRecvInfo[25];

    int32_t controlGroup = 0;
    int sendLen;

    switch (type) {
        case SYS1_GET_VAR_DATA:
            num = getInt32(inBuffer, 12);
            if (num < 1 || num > 24) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid num for mpGetVarData num = %ld\n", num);
                return -1;
            }
            if (msgSize != 12 + (4 * num)) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpGetVarData = %d != %ld for num = %ld\n", msgSize, 12 + (4 * num), num);
                return -1;
            }
            for (i = 0; i < num; i++) {
                varInfo[i].usType = getInt16(inBuffer, 16 + (4 * i));
                varInfo[i].usIndex = getInt16(inBuffer, 18 + (4 * i));
            }
            ret = mpGetVarData(varInfo, rData, num);
            setInt32(outBuffer, 0, 4 + num * 4);
            setInt32(outBuffer, 4, ret);
            for (i = 0; i < num; i++) {
                setInt32(outBuffer, 8 + i * 4, rData[i]);
            }
            sendLen = 8 + num * 4;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        case SYS1_PUT_VAR_DATA:
            num = getInt32(inBuffer, 12);
            if (num < 1 || num > 24) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid num for mpPutVarData num = %ld\n", num);
                return -1;
            }
            if (msgSize != 12 + (num * 8)) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpPutVarData = %d != %ld for num = %ld\n", msgSize, 12 + (num * 8), num);
                return -1;
            }
            for (i = 0; i < num; i++) {
                varData[i].usType = getInt16(inBuffer, 16 + (8 * i));
                varData[i].usIndex = getInt16(inBuffer, 18 + (8 * i));
                varData[i].ulValue = getInt32(inBuffer, 20 + (8 * i));
            }
            ret = mpPutVarData(varData, num);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case SYS1_GET_CURRENT_CART_POS:
            if (msgSize != 12) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpMotTargetClear = %d != 12\n", msgSize);
                return -1;
            }
            memset(&ctrlGrpSendData, 0, sizeof (ctrlGrpSendData));
            memset(&cartPosRspData, 0, sizeof (cartPosRspData));
            controlGroup = getInt32(inBuffer, 12);
            ctrlGrpSendData.sCtrlGrp = controlGroup;
            ret = mpGetCartPos(&ctrlGrpSendData, &cartPosRspData);
            setInt32(outBuffer, 0, 54);
            setInt32(outBuffer, 4, ret);
            for (i = 0; i < 6; i++) {
                setInt32(outBuffer, 8 + 4 * i, cartPosRspData.lPos[i]);
            }
            setInt16(outBuffer, 56, cartPosRspData.sConfig);
            sendLen = 58;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        case SYS1_GET_CURRENT_PULSE_POS:
            if (msgSize != 12) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpMotTargetClear = %d != 12\n", msgSize);
                return -1;
            }
            memset(&ctrlGrpSendData, 0, sizeof (ctrlGrpSendData));
            memset(&pulsePosRspData, 0, sizeof (pulsePosRspData));
            controlGroup = getInt32(inBuffer, 12);
            ctrlGrpSendData.sCtrlGrp = controlGroup;
            ret = mpGetPulsePos(&ctrlGrpSendData, &pulsePosRspData);
            setInt32(outBuffer, 0, 68);
            setInt32(outBuffer, 4, ret);
            for (i = 0; i < 8; i++) {
                setInt32(outBuffer, 8 + 4 * i, pulsePosRspData.lPos[i]);
            }
            sendLen = 72;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        case SYS1_GET_CURRENT_FEEDBACK_PULSE_POS:
            if (msgSize != 12) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpMotTargetClear = %d != 12\n", msgSize);
                return -1;
            }
            memset(&ctrlGrpSendData, 0, sizeof (ctrlGrpSendData));
            memset(&fbPulsePosRspData, 0, sizeof (fbPulsePosRspData));
            controlGroup = getInt32(inBuffer, 12);
            ctrlGrpSendData.sCtrlGrp = controlGroup;
            ret = mpGetFBPulsePos(&ctrlGrpSendData, &fbPulsePosRspData);
            setInt32(outBuffer, 0, 68);
            setInt32(outBuffer, 4, ret);
            for (i = 0; i < 8; i++) {
                setInt32(outBuffer, 8 + 4 * i, fbPulsePosRspData.lPos[i]);
            }
            sendLen = 72;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        case SYS1_GET_DEG_POS_EX:
            if (msgSize != 12) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpMotTargetClear = %d != 12\n", msgSize);
                return -1;
            }
            memset(&ctrlGrpSendData, 0, sizeof (ctrlGrpSendData));
            memset(&degPosRspDataEx, 0, sizeof (degPosRspDataEx));
            controlGroup = getInt32(inBuffer, 12);
            ctrlGrpSendData.sCtrlGrp = controlGroup;
            ret = mpGetDegPosEx(&ctrlGrpSendData, &degPosRspDataEx);
            setInt32(outBuffer, 0, 132);
            setInt32(outBuffer, 4, ret);
            for (i = 0; i < 8; i++) {
                setInt32(outBuffer, 8 + 4 * i, degPosRspDataEx.lDegPos[i]);
            }
            for (i = 0; i < 8; i++) {
                setInt32(outBuffer, 72 + 4 * i, degPosRspDataEx.lDegUnit[i]);
            }
            sendLen = 136;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;


        case SYS1_GET_SERVO_POWER:
            if (msgSize != 8) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpGetServoPower = %d != 8\n", msgSize);
                return -1;
            }
            memset(&servoPowerRspData, 0, sizeof (servoPowerRspData));
            ret = mpGetServoPower(&servoPowerRspData);
            setInt32(outBuffer, 0, 6);
            setInt32(outBuffer, 4, ret);
            setInt16(outBuffer, 8, servoPowerRspData.sServoPower);
            sendLen = 10;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        case SYS1_SET_SERVO_POWER:
            if (msgSize != 10) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpSetServoPower = %d != 12\n", msgSize);
                return -1;
            }
            memset(&servoPowerSendData, 0, sizeof (servoPowerSendData));
            memset(&stdRspData, 0, sizeof (stdRspData));
            servoPowerSendData.sServoPower = getInt16(inBuffer, 12);
            ret = mpSetServoPower(&servoPowerSendData, &stdRspData);
            setInt32(outBuffer, 0, 6);
            setInt32(outBuffer, 4, ret);
            setInt16(outBuffer, 8, stdRspData.err_no);
            sendLen = 10;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;


        case SYS1_READIO:
            num = getInt32(inBuffer, 12);
            if (num < 1 || num > 24) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid num for mpReadIO num = %ld\n", num);
                return -1;
            }
            if (msgSize != 12 + (4 * num)) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpReadIO = %d != %ld for num = %ld\n", msgSize, 12 + (4 * num), num);
                return -1;
            }
            for (i = 0; i < num; i++) {
                ioInfo[i].ulAddr = getInt32(inBuffer, 16 + (4 * i));
            }
            memset(iorData, 0, sizeof (iorData));
            ret = mpReadIO(ioInfo, iorData, num);
            setInt32(outBuffer, 0, 4 + num * 2);
            setInt32(outBuffer, 4, ret);
            for (i = 0; i < num; i++) {
                setInt16(outBuffer, 8 + i * 2, iorData[i]);
            }
            sendLen = 8 + num * 2;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        case SYS1_WRITEIO:
            num = getInt32(inBuffer, 12);
            if (num < 1 || num > 24) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid num for mpPutVarData num = %ld\n", num);
                return -1;
            }
            if (msgSize != 12 + (num * 8)) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpPutVarData = %d != %ld for num = %ld\n", msgSize, 12 + (num * 8), num);
                return -1;
            }
            for (i = 0; i < num; i++) {
                ioData[i].ulAddr = getInt32(inBuffer, 16 + (8 * i));
                ioData[i].ulValue = getInt32(inBuffer, 20 + (8 * i));
            }
            ret = mpWriteIO(ioData, num);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case SYS1_GET_MODE:
            if (msgSize != 8) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpGetMode = %d != 8\n", msgSize);
                return -1;
            }
            memset(&modeData, 0, sizeof (modeData));
            ret = mpGetMode(&modeData);
            setInt32(outBuffer, 0, 8);
            setInt32(outBuffer, 4, ret);
            setInt16(outBuffer, 8, modeData.sMode);
            setInt16(outBuffer, 10, modeData.sRemote);
            sendLen = 12;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        case SYS1_GET_CYCLE:
            if (msgSize != 8) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpGetCycle = %d != 8\n", msgSize);
                return -1;
            }
            memset(&cycleData, 0, sizeof (cycleData));
            ret = mpGetCycle(&cycleData);
            setInt32(outBuffer, 0, 6);
            setInt32(outBuffer, 4, ret);
            setInt16(outBuffer, 8, cycleData.sCycle);
            sendLen = 10;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        case SYS1_GET_ALARM_STATUS:
            if (msgSize != 8) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpGetAlarmStatus = %d != 8\n", msgSize);
                return -1;
            }
            memset(&alarmStatusData, 0, sizeof (alarmStatusData));
            ret = mpGetAlarmStatus(&alarmStatusData);
            setInt32(outBuffer, 0, 6);
            setInt32(outBuffer, 4, ret);
            setInt16(outBuffer, 8, alarmStatusData.sIsAlarm);
            sendLen = 10;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        case SYS1_GET_ALARM_CODE:
            if (msgSize != 8) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpGetAlarmCode = %d != 8\n", msgSize);
                return -1;
            }
            memset(&alarmCodeData, 0, sizeof (alarmCodeData));
            ret = mpGetAlarmCode(&alarmCodeData);
            setInt32(outBuffer, 0, 10 + 4 * ((alarmCodeData.usAlarmNum > 4) ? 4 : alarmCodeData.usAlarmNum));
            setInt32(outBuffer, 4, ret);
            setInt16(outBuffer, 8, alarmCodeData.usErrorNo);
            setInt16(outBuffer, 10, alarmCodeData.usErrorData);
            setInt16(outBuffer, 12, alarmCodeData.usAlarmNum);
            for (i = 0; i < alarmCodeData.usAlarmNum && i < 4; i++) {
                setInt16(outBuffer, 14 + i * 4, alarmCodeData.AlarmData.usAlarmNo[i]);
                setInt16(outBuffer, 16 + i * 4, alarmCodeData.AlarmData.usAlarmData[i]);
            }
            sendLen = 14 + 4 * ((alarmCodeData.usAlarmNum > 4) ? 4 : alarmCodeData.usAlarmNum);
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        case SYS1_GET_RTC:
            if (msgSize != 8) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpGetAlarmCode = %d != 8\n", msgSize);
                return -1;
            }
            ret = mpGetRtc();
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case SYS1_GET_SVAR_INFO:
            num = getInt32(inBuffer, 12);
            if (num < 1 || num > 24) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid num for mpGetVarData num = %ld\n", num);
                return -1;
            }
            if (msgSize != 12 + (4 * num)) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpGetVarData = %d != %ld for num = %ld\n", msgSize, 12 + (4 * num), num);
                return -1;
            }
            for (i = 0; i < num; i++) {
                varInfo[i].usType = getInt16(inBuffer, 16 + (4 * i));
                varInfo[i].usIndex = getInt16(inBuffer, 18 + (4 * i));
            }
            ret = mpGetSVarInfo(varInfo, svarRecvInfo, num);
            setInt32(outBuffer, 0, 4 + num * 4);
            setInt32(outBuffer, 4, ret);
            for (i = 0; i < num; i++) {
                *((MP_SVAR_RECV_INFO*) outBuffer + 8 + i * 20 /*sizeof(MP_SVAR_RECV_INFO */)
                        = svarRecvInfo[i];
            }
            sendLen = 8 + num * 20;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;


        case SYS1_PUT_SVAR_INFO:
            num = getInt32(inBuffer, 12);
            if (num < 1 || num > 24) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid num for mpGetVarData num = %ld\n", num);
                return -1;
            }
            if (msgSize != 12 + (4 * num)) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpGetVarData = %d != %ld for num = %ld\n", msgSize, 12 + (4 * num), num);
                return -1;
            }
            for (i = 0; i < num; i++) {
                svarSendInfo[i].usType = getInt16(inBuffer, 16 + (24 * i));
                svarSendInfo[i].usIndex = getInt16(inBuffer, 18 + (24 * i));
                memcpy(svarRecvInfo[i].ucValue,inBuffer + 20 + 24*i,16);
            }
            ret = mpPutSVarInfo(svarSendInfo, num);
            setInt32(outBuffer, 0, 4 + num * 4);
            setInt32(outBuffer, 4, ret);
            sendLen = 8;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        default:
            fprintf(stderr, "NIST motoPlustTcpSvr: invalid sys1 function type = %d\n", type);
            return -1;
    }
    return 0;
}

// Return 0 for success, anything else will be treated like a fatal error closing
// the connection.

int handleMotFunctionRequest(int acceptHandle, char *inBuffer, char *outBuffer, int type, int msgSize) {
    int32_t ret = -1;
    int32_t options = 0;
    int32_t controlGroup = 0;
    int32_t timeout = 0;
    int sendLen = 8;
    MP_TARGET target;
    MP_SPEED speed;
    int32_t grpNo = 0;
    int32_t aux = 0;
    int32_t coordType = 0;
    int32_t tool = 0;
    int32_t taskNo = 0;
    int recvId = 0;
    int i = 0;
    int millisPerTick = 0;
    int maxTimeout = 0;
    switch (type) {
        case MOT_START:
            if (msgSize != 12) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpMotStart = %d != 12\n", msgSize);
                return -1;
            }
            options = getInt32(inBuffer, 12);
            ret = mpMotStart(options);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case MOT_STOP:
            if (msgSize != 12) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpMotStop = %d != 12\n", msgSize);
                return -1;
            }
            options = getInt32(inBuffer, 12);
            ret = mpMotStop(options);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case MOT_TARGET_CLEAR:
            if (msgSize != 16) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpMotTargetClear = %d != 16\n", msgSize);
                return -1;
            }
            controlGroup = getInt32(inBuffer, 12);
            options = getInt32(inBuffer, 16);
            ret = mpMotTargetClear(controlGroup, options);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case MOT_JOINT_TARGET_SEND:
            if (msgSize != 88) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpMotTargetSend = %d != 88\n", msgSize);
                return -1;
            }
            memset(&target, 0, sizeof (target));
            controlGroup = getInt32(inBuffer, 12);
            target.id = getInt32(inBuffer, 16);
            target.intp = getInt32(inBuffer, 20);
            for (i = 0; i < 8 /* MP_GRP_AXES_NUM */; i++) {
                target.dst.joint[i] = getInt32(inBuffer, 24 + (i * 4));
            }
            for (i = 0; i < 8 /* MP_GRP_AXES_NUM */; i++) {
                target.aux.joint[i] = getInt32(inBuffer, 56 + (i * 4));
            }
            timeout = getInt32(inBuffer, 88);
            if (timeout == WAIT_FOREVER) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid timeout for mpMotTargetSend = %d, WAIT_FOREVER not allowed.\n", timeout);
                return -1;
            }
            if (timeout != NO_WAIT) {
                millisPerTick = mpGetRtc();
                maxTimeout = 5000 / millisPerTick;
                if (timeout < 0 || timeout > maxTimeout) {
                    fprintf(stderr, "NIST motoPlustTcpSvr: invalid timeout for mpMotTargetSend = %d, millisPerTick = mpGetRtc() =%d, maxTimeout=5000/millisPerTick=%d\n",
                            timeout, millisPerTick, maxTimeout);
                    return -1;
                }
            }
            ret = mpMotTargetSend(controlGroup, &target, timeout);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case MOT_COORD_TARGET_SEND:
            if (msgSize != 88) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpMotTargetSend = %d != 88\n", msgSize);
                return -1;
            }
            memset(&target, 0, sizeof (target));
            controlGroup = getInt32(inBuffer, 12);
            target.id = getInt32(inBuffer, 16);
            target.intp = getInt32(inBuffer, 20);
            target.dst.coord.x = getInt32(inBuffer, 24);
            target.dst.coord.y = getInt32(inBuffer, 28);
            target.dst.coord.z = getInt32(inBuffer, 32);
            target.dst.coord.rx = getInt32(inBuffer, 36);
            target.dst.coord.ry = getInt32(inBuffer, 40);
            target.dst.coord.rz = getInt32(inBuffer, 44);
            target.dst.coord.ex1 = getInt32(inBuffer, 48);
            target.dst.coord.ex2 = getInt32(inBuffer, 52);
            target.aux.coord.x = getInt32(inBuffer, 56);
            target.aux.coord.y = getInt32(inBuffer, 60);
            target.aux.coord.z = getInt32(inBuffer, 64);
            target.aux.coord.rx = getInt32(inBuffer, 68);
            target.aux.coord.ry = getInt32(inBuffer, 72);
            target.aux.coord.rz = getInt32(inBuffer, 76);
            target.aux.coord.ex1 = getInt32(inBuffer, 80);
            target.aux.coord.ex2 = getInt32(inBuffer, 84);
            timeout = getInt32(inBuffer, 88);
            if (timeout == WAIT_FOREVER) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid timeout for mpMotTargetSend = %d, WAIT_FOREVER not allowed.\n", timeout);
                return -1;
            }
            if (timeout != NO_WAIT) {
                millisPerTick = mpGetRtc();
                maxTimeout = 5000 / millisPerTick;
                if (timeout < 0 || timeout > maxTimeout) {
                    fprintf(stderr, "NIST motoPlustTcpSvr: invalid timeout for mpMotTargetSend = %d, millisPerTick = mpGetRtc() =%d, maxTimeout=5000/millisPerTick=%d\n",
                            timeout, millisPerTick, maxTimeout);
                    return -1;
                }
            }
            ret = mpMotTargetSend(controlGroup, &target, timeout);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case MOT_TARGET_RECEIVE:
            if (msgSize != 24) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpMotTargetReceive = %d != 24\n", msgSize);
                return -1;
            }
            grpNo = getInt32(inBuffer, 12);
            target.id = getInt32(inBuffer, 16);
            timeout = getInt32(inBuffer, 20);
            if (timeout == WAIT_FOREVER) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid timeout for mpMotTargetReceive = %d, WAIT_FOREVER not allowed.\n", timeout);
                return -1;
            }
            if (timeout != NO_WAIT) {
                millisPerTick = mpGetRtc();
                maxTimeout = 5000 / millisPerTick;
                if (timeout < 0 || timeout > maxTimeout) {
                    fprintf(stderr, "NIST motoPlustTcpSvr: invalid timeout for mpMotTargetReceive = %d, millisPerTick = mpGetRtc() =%d, maxTimeout=5000/millisPerTick=%d\n",
                            timeout, millisPerTick, maxTimeout);
                    return -1;
                }
            }
            options = getInt32(inBuffer, 24);
            ret = mpMotTargetReceive(grpNo, target.id, &recvId, timeout, options);
            setInt32(outBuffer, 0, 8);
            setInt32(outBuffer, 4, ret);
            setInt32(outBuffer, 8, recvId);
            sendLen = 12;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        case MOT_SET_COORD:
            if (msgSize != 20) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpMotSetCoord = %d != 20\n", msgSize);
                return -1;
            }
            grpNo = getInt32(inBuffer, 12);
            coordType = getInt32(inBuffer, 16);
            aux = getInt32(inBuffer, 20);
            ret = mpMotSetCoord(grpNo, coordType, aux);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case MOT_SET_TOOL:
            if (msgSize != 16) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpMotSetTool = %d != 16\n", msgSize);
                return -1;
            }
            grpNo = getInt32(inBuffer, 12);
            tool = getInt32(inBuffer, 16);
            ret = mpMotSetTool(grpNo, tool);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case MOT_SET_SPEED:
            if (msgSize != 32) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpMotSetSpeed = %d != 32\n", msgSize);
                return -1;
            }
            grpNo = getInt32(inBuffer, 12);
            speed.vj = getInt32(inBuffer, 16);
            speed.v = getInt32(inBuffer, 24);
            speed.vr = getInt32(inBuffer, 32);
            ret = mpMotSetSpeed(grpNo, &speed);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case MOT_SET_ORIGIN:
            if (msgSize != 16) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpMotSetOrigin = %d != 16\n", msgSize);
                return -1;
            }
            grpNo = getInt32(inBuffer, 12);
            options = getInt32(inBuffer, 16);
            ret = mpMotSetOrigin(grpNo, options);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case MOT_SET_TASK:
            if (msgSize != 16) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpMotSetTask = %d != 16\n", msgSize);
                return -1;
            }
            grpNo = getInt32(inBuffer, 12);
            taskNo = getInt32(inBuffer, 16);
            ret = mpMotSetTask(grpNo, taskNo);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case MOT_SET_SYNC:
            if (msgSize != 20) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpMotSetSync = %d != 20\n", msgSize);
                return -1;
            }
            grpNo = getInt32(inBuffer, 12);
            aux = getInt32(inBuffer, 16);
            options = getInt32(inBuffer, 20);
            ret = mpMotSetSync(grpNo, aux, options);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case MOT_RESET_SYNC:
            if (msgSize != 12) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpMotResetSync = %d != 12\n", msgSize);
                return -1;
            }
            grpNo = getInt32(inBuffer, 12);
            ret = mpMotResetSync(grpNo);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        default:
            fprintf(stderr, "NIST motoPlustTcpSvr: invalid mot function type = %d\n", type);
            return -1;
    }
    return 0;
}

short lastExtensionId = -1;

int handleExFileFunctionRequest(int acceptHandle, char *inBuffer, char *outBuffer, int type, int msgSize) {
    int32_t ret = -1;
    int32_t index = -1;
    int32_t ramDriveId = -1;
    int32_t fileNameOffset = -1;
    int32_t fd = -1;

    int sendLen = 8;
    int namelen = 0;
    short extensionId = -1;
    MP_FILE_NAME_SEND_DATA fileNameSendData;
    MP_GET_JOBLIST_RSP_DATA jobListData;

    switch (type) {

        case EX_FILE_CTRL_GET_FILE_COUNT:
            if (msgSize != 10) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpGetFileCount = %d != 10\n", msgSize);
                return -1;
            }
            extensionId = getInt16(inBuffer, 12);
            if (extensionId < 1 || extensionId > 2) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid extensionId for mpGetFileCount = %d  (must be 1 or 2)\n", extensionId);
                return -1;
            }
            lastExtensionId = -1;
            ret = mpRefreshFileList(extensionId);
            if (ret != 0) {
                return returnSingleIntRet(acceptHandle, outBuffer, ret);
                return 0;
            }
            lastExtensionId = extensionId;
            ret = mpGetFileCount();
            setInt32(outBuffer, 0, 8);
            setInt32(outBuffer, 4, 0);
            setInt32(outBuffer, 8, ret);
            sendLen = 12;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        case EX_FILE_CTRL_GET_FILE_NAME:
            extensionId = getInt16(inBuffer, 12);
            if (extensionId < 1 || extensionId > 2) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid extensionId for mpGetFileName = %d  (must be 1 or 2)\n", extensionId);
                return -1;
            }
            if (extensionId != lastExtensionId) {
                lastExtensionId = -1;
                ret = mpRefreshFileList(extensionId);
                if (ret != 0) {
                    setInt32(outBuffer, 0, 4);
                    setInt32(outBuffer, 4, ret);
                    lastExtensionId = -1;
                    sendLen = 8;
                    return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
                }
            }
            index = getInt32(inBuffer, 14);
            ret = mpGetFileName(index, outBuffer + 12);
            namelen = strlen(outBuffer + 12);
            setInt32(outBuffer, 0, 8 + namelen + 1);
            setInt32(outBuffer, 4, 0);
            setInt32(outBuffer, 8, ret);
            sendLen = 12 + namelen + 1;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        case EX_FILE_CTRL_LOAD_FILE:
            ramDriveId = getInt32(inBuffer, 12);
            if (ramDriveId < 1 || ramDriveId > 2) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid ramDriveId for mpLoadFile = %d  (must be 1 or 2)\n", ramDriveId);
                return -1;
            }
            fileNameOffset = getInt32(inBuffer, 16);
            if (fileNameOffset < 20 || fileNameOffset > (BUFF_MAX - 21)) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid fileNameOffset for mpLoadFile = %d  \n", fileNameOffset);
                return -1;
            }
            ret = mpLoadFile(ramDriveId, inBuffer + 20, inBuffer + fileNameOffset);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case EX_FILE_CTRL_SAVE_FILE:
            ramDriveId = getInt32(inBuffer, 12);
            if (ramDriveId < 1 || ramDriveId > 2) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid ramDriveId for mpSaveFile = %d  (must be 1 or 2)\n", ramDriveId);
                return -1;
            }
            fileNameOffset = getInt32(inBuffer, 16);
            if (fileNameOffset < 20 || fileNameOffset > (BUFF_MAX - 21)) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid fileNameOffset for mpSaveFile = %d  \n", fileNameOffset);
                return -1;
            }
            ret = mpSaveFile(ramDriveId, inBuffer + 20, inBuffer + fileNameOffset);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case EX_FILE_CTRL_FD_READ_FILE:
            fd = getInt32(inBuffer, 12);
            if (fd == -99) {
                fd = acceptHandle;
            }
            if (fd < 1) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid fd for mpFdReadFile = %d\n", ramDriveId);
                return -1;
            }
            memset(&fileNameSendData, 0, sizeof (fileNameSendData));
            strcpy(fileNameSendData.cFileName, inBuffer + 16);
            ret = mpFdReadFile(fd, &fileNameSendData);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case EX_FILE_CTRL_FD_WRITE_FILE:
            fd = getInt32(inBuffer, 12);
            if (fd == -99) {
                fd = acceptHandle;
            }
            if (fd < 1) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid fd for mpFdWriteFile = %d\n", ramDriveId);
                return -1;
            }
            memset(&fileNameSendData, 0, sizeof (fileNameSendData));
            strcpy(fileNameSendData.cFileName, inBuffer + 16);
            ret = mpFdWriteFile(fd, &fileNameSendData);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case EX_FILE_CTRL_FD_GET_JOB_LIST:
            fd = getInt32(inBuffer, 12);
            if (fd == -99) {
                fd = acceptHandle;
            }
            if (fd < 1) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid fd for mpFdGetJobList = %d  (must be 1 or 2)\n", ramDriveId);
                return -1;
            }
            memset(&jobListData, 0, sizeof (jobListData));
            ret = mpFdGetJobList(fd, &jobListData);
            setInt32(outBuffer, 0, 10);
            setInt32(outBuffer, 4, ret);
            setInt16(outBuffer, 8, jobListData.err_no);
            setInt16(outBuffer, 10, jobListData.uIsEndFlag);
            setInt16(outBuffer, 12, jobListData.uListDataNum);
            sendLen = 14;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        default:
            fprintf(stderr, "NIST motoPlustTcpSvr: invalid file function type = %d\n", type);
            return -1;
    }
    return 0;
}

int handleFileFunctionRequest(int acceptHandle, char *inBuffer, char *outBuffer, int type, int msgSize) {
    int32_t ret = -1;
    int32_t mode = -1;
    int32_t flags = -1;
    int32_t fd = -1;
    int32_t maxBytes = -1;

    int sendLen = 8;

    switch (type) {

        case FILE_CTRL_OPEN:
            flags = getInt32(inBuffer, 12);
            mode = getInt32(inBuffer, 16);
            ret = mpOpen(inBuffer + 20, flags, mode);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case FILE_CTRL_CREATE:
            flags = getInt32(inBuffer, 12);
            ret = mpCreate(inBuffer + 16, flags);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case FILE_CTRL_CLOSE:
            if (msgSize != 12) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid msgSize for mpClose = %d != 12\n", msgSize);
                return -1;
            }
            fd = getInt32(inBuffer, 12);
            if (fd < 1) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid fd for mpRead = %d\n", fd);
                return -1;
            }
            ret = mpClose(fd);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case FILE_CTRL_READ:
            fd = getInt32(inBuffer, 12);
            if (fd < 1) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid fd for mpRead = %d\n", fd);
                return -1;
            }
            maxBytes = getInt32(inBuffer, 16);
            if (maxBytes < 1 || maxBytes >= (BUFF_MAX - 8)) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid maxBytes for mpRead = %d max = %d\n", maxBytes, (BUFF_MAX - 8));
                return -1;
            }
            ret = mpRead(fd, outBuffer + 8, maxBytes);
            setInt32(outBuffer, 0, 4 + (ret > 0 ? ret : 0));
            setInt32(outBuffer, 4, ret);
            sendLen = 8 + (ret > 0 ? ret : 0);
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;


        case FILE_CTRL_WRITE:
            fd = getInt32(inBuffer, 12);
            if (fd < 1) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid fd for mpRead = %d\n", fd);
                return -1;
            }
            maxBytes = getInt32(inBuffer, 16);
            if (maxBytes < 1 || maxBytes >= (BUFF_MAX - 8)) {
                fprintf(stderr, "NIST motoPlustTcpSvr: invalid maxBytes for mpRead = %d max = %d\n", maxBytes, (BUFF_MAX - 8));
                return -1;
            }
            ret = mpWrite(fd, inBuffer + 20, maxBytes);
            setInt32(outBuffer, 0, 4 + (ret > 0 ? ret : 0));
            setInt32(outBuffer, 4, ret);
            sendLen = 8 + (ret > 0 ? ret : 0);
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        default:
            fprintf(stderr, "NIST motoPlustTcpSvr: invalid file function type = %d\n", type);
            return -1;
    }
    return 0;
}

int handleFcsFunctionRequest(int acceptHandle, char *inBuffer, char *outBuffer, int type, int msgSize) {
    MP_FCS_ROB_ID rob_id;
    int reset_time;
    MP_FCS_OFFSET_DATA offset_data;
    int coord_type;
    int uf_no;
    MP_FCS_SENS_DATA sens_data;
    MP_FCS_IMP_COEFF m, d, k;
    BITSTRING cart_axes;
    BITSTRING option_ctrl;
    MP_FCS_FREF_DATA fref_data;
    int scale;
    int ret;
    int sendLen = 8;
    int i;

    switch (type) {

        case FORCE_CONTROL_START_MEASURING:
            if (msgSize != 16) {
                fprintf(stderr,
                        "tcpSvr: invalid msgSize for mpFcsStartMeasuring = %d != %d\n",
                        msgSize, 16);
                return -1;
            }
            rob_id = getInt32(inBuffer, 12);
            reset_time = getInt32(inBuffer, 16);
            ret = mpFcsStartMeasuring(rob_id, reset_time, offset_data);
            setInt32(outBuffer, 0, 4 + 4 * MP_FCS_AXES_NUM);
            setInt32(outBuffer, 4, ret);
            for (i = 0; i < MP_FCS_AXES_NUM; i++) {
                setInt32(outBuffer, 8 + (4 * i), offset_data[i]);
            }
            sendLen = 8 + 4 * MP_FCS_AXES_NUM;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        case FORCE_CONTROL_GET_FORCE_DATA:
            if (msgSize != 20) {
                fprintf(stderr,
                        "tcpSvr: invalid msgSize for mpFcsGetForceData = %d != %d\n",
                        msgSize, 20);
                return -1;
            }
            rob_id = getInt32(inBuffer, 12);
            coord_type = getInt32(inBuffer, 16);
            uf_no = getInt32(inBuffer, 20);
            ret = mpFcsGetForceData(rob_id, coord_type, uf_no, sens_data);
            setInt32(outBuffer, 0, 4 + 4 * MP_FCS_AXES_NUM);
            setInt32(outBuffer, 4, ret);
            for (i = 0; i < MP_FCS_AXES_NUM; i++) {
                setInt32(outBuffer, 8 + (4 * i), sens_data[i]);
            }
            sendLen = 8 + 4 * MP_FCS_AXES_NUM;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        case FORCE_CONTROL_START_IMP:
            if (msgSize != 28 + 12 * MP_FCS_AXES_NUM) {
                fprintf(stderr,
                        "tcpSvr: invalid msgSize for mpFcsStartImp = %d != %d\n",
                        msgSize, 28 + 12 * MP_FCS_AXES_NUM);
                return -1;
            }
            rob_id = getInt32(inBuffer, 12);
            for (i = 0; i < MP_FCS_AXES_NUM; i++) {
                m[i] = getInt32(inBuffer, 16 + (4 * i));
            }
            for (i = 0; i < MP_FCS_AXES_NUM; i++) {
                d[i] = getInt32(inBuffer, 16 + 4 * MP_FCS_AXES_NUM + (4 * i));
            }
            for (i = 0; i < MP_FCS_AXES_NUM; i++) {
                k[i] = getInt32(inBuffer, 16 + 8 * MP_FCS_AXES_NUM + (4 * i));
            }
            coord_type = getInt32(inBuffer, 16 + 12 * MP_FCS_AXES_NUM);
            uf_no = getInt32(inBuffer, 20 + 12 * MP_FCS_AXES_NUM);
            cart_axes = getInt32(inBuffer, 24 + 12 * MP_FCS_AXES_NUM);
            option_ctrl = getInt32(inBuffer, 28 + 12 * MP_FCS_AXES_NUM);
            ret = mpFcsStartImp(rob_id, m, d, k,
                    coord_type, uf_no, cart_axes, option_ctrl);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;


        case FORCE_CONTROL_SET_REFERENCE_FORCE:
            if (msgSize != 12 + 4 * MP_FCS_AXES_NUM) {
                fprintf(stderr,
                        "tcpSvr: invalid msgSize for mpFcsSetReferenceForce = %d != %d\n",
                        msgSize, 12 + 4 * MP_FCS_AXES_NUM);
                return -1;
            }
            rob_id = getInt32(inBuffer, 12);
            for (i = 0; i < MP_FCS_AXES_NUM; i++) {
                fref_data[i] = getInt32(inBuffer, 16 + (4 * i));
            }
            ret = mpFcsSetReferenceForce(rob_id, fref_data);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case FORCE_CONTROL_END_IMP:
            if (msgSize != 12) {
                fprintf(stderr,
                        "tcpSvr: invalid msgSize for mpFcsEndImp = %d != %d\n",
                        msgSize, 12);
                return -1;
            }
            rob_id = getInt32(inBuffer, 12);
            ret = mpFcsEndImp(rob_id);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case FORCE_CONTROL_CONV_FORCE_SCALE:
            if (msgSize != 16) {
                fprintf(stderr,
                        "tcpSvr: invalid msgSize for mpFcsConvForceScale = %d != %d\n",
                        msgSize, 16);
                return -1;
            }
            rob_id = getInt32(inBuffer, 12);
            scale = getInt32(inBuffer, 16);
            ret = mpFcsConvForceScale(rob_id, scale);
            return returnSingleIntRet(acceptHandle, outBuffer, ret);
            break;

        case FORCE_CONTROL_GET_SENSOR_DATA:
            if (msgSize != 12) {
                fprintf(stderr,
                        "tcpSvr: invalid msgSize for mpFcsGetForceData = %d != %d",
                        msgSize, 12);
                return -1;
            }
            rob_id = getInt32(inBuffer, 12);
            ret = mpFcsGetSensorData(rob_id, sens_data);
            setInt32(outBuffer, 0, 4 + 4 * MP_FCS_AXES_NUM);
            setInt32(outBuffer, 4, ret);
            for (i = 0; i < MP_FCS_AXES_NUM; i++) {
                setInt32(outBuffer, 8 + (4 * i), sens_data[i]);
            }
            sendLen = 8 + 4 * MP_FCS_AXES_NUM;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        default:
            fprintf(stderr, "NIST motoPlustTcpSvr: invalid file function type = %d\n", type);
            return -1;
    }
    return 0;
}

const int anglelen = sizeof (long) * MP_GRP_AXES_NUM;

const int getAngleLen() {
    return anglelen;
}

int handleKinematicConvFunctionRequest(int acceptHandle, char *inBuffer, char *outBuffer, int type, int msgSize) {

    unsigned int grp_no;
    long angle[MP_GRP_AXES_NUM];
    unsigned int tool_no;
    BITSTRING fig_ctrl;
    MP_COORD coord;
    long prev_angle[MP_GRP_AXES_NUM];
    MP_KINEMA_TYPE kinema_type;
    long pulse[MP_GRP_AXES_NUM];
    long fbpulse[MP_GRP_AXES_NUM];
    /*
        MP_XYZ org_vector;
        MP_XYZ x_vector;
        MP_XYZ y_vector;
        MP_FRAME frame;
        MP_FRAME org_frame;
        double rot_angle;
        MP_XYZ rot_vector;
        MP_FRAME frame1;
        MP_FRAME frame2;
        MP_FRAME frame_prod;
        MP_XYZ vector1;
        MP_XYZ vector2;
        MP_XYZ xyz_prod;
        double inner_double_prod;
     */
    int ret;
    int sendLen = 8;
    int i;

    memset(prev_angle, 0, anglelen);
    memset(angle, 0, anglelen);
    memset(pulse, 0, anglelen);
    memset(fbpulse, 0, anglelen);
    memset(&coord, 0, sizeof (MP_COORD));
    switch (type) {

        case KINEMATICS_CONVERSION_CONVERT_AXES_TO_CART_POS:
            if (msgSize != 16 + 4 * MP_GRP_AXES_NUM) {
                fprintf(stderr,
                        "tcpSvr: invalid msgSize for mpConvAxesToCartPos = %d != %d",
                        msgSize, 16 + 4 * MP_GRP_AXES_NUM);
                return -1;
            }
            grp_no = getInt32(inBuffer, 12);
            for (i = 0; i < MP_GRP_AXES_NUM; i++) {
                angle[i] = getInt32(inBuffer, 16 + i * 4);
            }
            tool_no = getInt32(inBuffer, 16 + 4 * MP_GRP_AXES_NUM);
            ret = mpConvAxesToCartPos(grp_no, angle, tool_no, &fig_ctrl, &coord);
            setInt32(outBuffer, 0, 40);
            setInt32(outBuffer, 4, ret);
            setInt32(outBuffer, 8, fig_ctrl);
            setInt32(outBuffer, 12, coord.x);
            setInt32(outBuffer, 16, coord.y);
            setInt32(outBuffer, 20, coord.z);
            setInt32(outBuffer, 24, coord.rx);
            setInt32(outBuffer, 28, coord.ry);
            setInt32(outBuffer, 32, coord.rz);
            setInt32(outBuffer, 36, coord.ex1);
            setInt32(outBuffer, 40, coord.ex2);
            sendLen = 44;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        case KINEMATICS_CONVERSION_CONVERT_CART_POS_TO_AXES:
            if (msgSize != 56 + MP_GRP_AXES_NUM * 4) {
                fprintf(stderr,
                        "tcpSvr: invalid msgSize for mpConvAxesToCartPos = %d != %d",
                        msgSize, 56 + MP_GRP_AXES_NUM * 4);
                return -1;
            }
            grp_no = getInt32(inBuffer, 12);
            coord.x = getInt32(inBuffer, 16);
            coord.y = getInt32(inBuffer, 20);
            coord.z = getInt32(inBuffer, 24);
            coord.rx = getInt32(inBuffer, 28);
            coord.ry = getInt32(inBuffer, 32);
            coord.rz = getInt32(inBuffer, 36);
            coord.ex1 = getInt32(inBuffer, 40);
            coord.ex2 = getInt32(inBuffer, 44);
            tool_no = getInt32(inBuffer, 48);
            fig_ctrl = getInt32(inBuffer, 52);
            for (i = 0; i < MP_GRP_AXES_NUM; i++) {
                prev_angle[i] = getInt32(inBuffer, 56 + i * 4);
            }
            kinema_type = getInt32(inBuffer, 56 + MP_GRP_AXES_NUM * 4);
            ret = mpConvCartPosToAxes(grp_no, &coord, tool_no, fig_ctrl, prev_angle, kinema_type, angle);
            setInt32(outBuffer, 0, 4 + MP_GRP_AXES_NUM * 4);
            setInt32(outBuffer, 4, ret);
            for (i = 0; i < MP_GRP_AXES_NUM; i++) {
                setInt32(outBuffer, 8 + i * 4, angle[i]);
            }
            sendLen = 8 + MP_GRP_AXES_NUM * 4;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        case KINEMATICS_CONVERSION_CONVERT_PULSE_TO_ANGLE:
            if (msgSize != 12 + MP_GRP_AXES_NUM * 4) {
                fprintf(stderr,
                        "tcpSvr: invalid msgSize for mpConvAxesToCartPos = %d != %d",
                        msgSize, 12 + MP_GRP_AXES_NUM * 4);
                return -1;
            }
            grp_no = getInt32(inBuffer, 12);
            for (i = 0; i < MP_GRP_AXES_NUM; i++) {
                pulse[i] = getInt32(inBuffer, 16 + i * 4);
            }
            ret = mpConvPulseToAngle(grp_no, pulse, angle);
            setInt32(outBuffer, 0, 4 + MP_GRP_AXES_NUM * 4);
            setInt32(outBuffer, 4, ret);
            for (i = 0; i < MP_GRP_AXES_NUM; i++) {
                setInt32(outBuffer, 8 + i * 4, angle[i]);
            }
            sendLen = 8 + MP_GRP_AXES_NUM * 4;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        case KINEMATICS_CONVERSION_CONVERT_ANGLE_TO_PULSE:
            if (msgSize != 12 + MP_GRP_AXES_NUM * 4) {
                fprintf(stderr,
                        "tcpSvr: invalid msgSize for mpConvAxesToCartPos = %d != %d",
                        msgSize, 12 + MP_GRP_AXES_NUM * 4);
                return -1;
            }
            grp_no = getInt32(inBuffer, 12);
            for (i = 0; i < MP_GRP_AXES_NUM; i++) {
                angle[i] = getInt32(inBuffer, 16 + i * 4);
            }
            ret = mpConvAngleToPulse(grp_no, angle, pulse);
            setInt32(outBuffer, 0, 4 + MP_GRP_AXES_NUM * 4);
            setInt32(outBuffer, 4, ret);
            for (i = 0; i < MP_GRP_AXES_NUM; i++) {
                setInt32(outBuffer, 8 + i * 4, pulse[i]);
            }
            sendLen = 8 + MP_GRP_AXES_NUM * 4;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

        case KINEMATICS_CONVERSION_CONVERT_FB_PULSE_TO_PULSE:
            if (msgSize != 12 + MP_GRP_AXES_NUM * 4) {
                fprintf(stderr,
                        "tcpSvr: invalid msgSize for mpConvAxesToCartPos = %d != %d",
                        msgSize, 12 + MP_GRP_AXES_NUM * 4);
                return -1;
            }
            grp_no = getInt32(inBuffer, 12);
            for (i = 0; i < MP_GRP_AXES_NUM; i++) {
                fbpulse[i] = getInt32(inBuffer, 16 + i * 4);
            }
            ret = mpConvFBPulseToPulse(grp_no, fbpulse, pulse);
            setInt32(outBuffer, 0, 4 + MP_GRP_AXES_NUM * 4);
            setInt32(outBuffer, 4, ret);
            for (i = 0; i < MP_GRP_AXES_NUM; i++) {
                setInt32(outBuffer, 8 + i * 4, pulse[i]);
            }
            sendLen = 8 + MP_GRP_AXES_NUM * 4;
            return checkedSendN(acceptHandle, outBuffer, sendLen, 0);
            break;

            /*
                    case KINEMATICS_CONVERSION_MAKE_FRAME:
                        break;

                    case KINEMATICS_CONVERSION_INV_FRAME:
                        break;

                    case KINEMATICS_CONVERSION_ROT_FRAME:
                        break;

                    case KINEMATICS_CONVERSION_MUL_FRAME:
                        break;

                    case KINEMATICS_CONVERSION_ZYX_EULER_TO_FRAME:
                        break;

                    case KINEMATICS_CONVERSION_FRAME_TO_ZYX_EULER:
                        break;

                    case KINEMATICS_CONVERSION_CROSS_PRODUCT:
                        break;

                    case KINEMATICS_CONVERSION_INNER_PRODUCT:
                        break;
             */

        default:
            fprintf(stderr, "NIST motoPlustTcpSvr: invalid handleKinematicConvFunctionRequest function type = %d\n", type);
            return -1;
    }
    return 0;
}

static char inBuffer[BUFF_MAX + 1];
static char outBuffer[BUFF_MAX + 1];

int handleSingleConnection(int acceptHandle) {

    int32_t count = 0;
    int32_t group = 0;
    int32_t type = 0;
    int failed = 0;
    int bytesRecv;
    int32_t msgSize;

    memset(inBuffer, 0, BUFF_MAX + 1);
    memset(outBuffer, 0, BUFF_MAX + 1);
    bytesRecv = recvN(acceptHandle, inBuffer, 4, 0);
    if (bytesRecv != 4) {
        failed = 1;
        return failed;
    }

    msgSize = getInt32(inBuffer, 0);

    if (msgSize < 8 || msgSize >= (BUFF_MAX - 4)) {
        printf("tcpSvr: Invalid msgSize\n");
        failed = 1;
        return failed;
    }

    bytesRecv = recvN(acceptHandle, inBuffer + 4, (int) msgSize, 0);

    if (bytesRecv != msgSize) {
        failed = 1;
        return failed;
    }
    group = getInt32(inBuffer, 4);
    type = getInt32(inBuffer, 8);
    count++;

    switch (group) {
        case MOT_FUNCTION_GROUP:
            failed = handleMotFunctionRequest(acceptHandle, inBuffer, outBuffer, type, msgSize);
            break;

        case SYS1_FUNCTION_GROUP:
            failed = handleSys1FunctionRequest(acceptHandle, inBuffer, outBuffer, type, msgSize);
            break;

        case FILE_CTRL_FUNCTION_GROUP:
            failed = handleFileFunctionRequest(acceptHandle, inBuffer, outBuffer, type, msgSize);
            break;

        case EX_FILE_CTRL_FUNCTION_GROUP:
            failed = handleExFileFunctionRequest(acceptHandle, inBuffer, outBuffer, type, msgSize);
            break;

        case FORCE_CTRL_FUNCTION_GROUP:
            failed = handleFcsFunctionRequest(acceptHandle, inBuffer, outBuffer, type, msgSize);
            break;

        case KINEMATICS_CONVERSION_FUNCTION_GROUP:
            failed = handleKinematicConvFunctionRequest(acceptHandle, inBuffer, outBuffer, type, msgSize);
            break;

        default:
            fprintf(stderr, "NIST motoPlustTcpSvr: unrecognized group =%d\n", group);
            failed = 1;
            break;
    }
    return failed;
    /*
    printf("tcpSvr: msgSize=%d\n", msgSize);
    printf("tcpSvr: group=%d\n", group);
    printf("tcpSvr: type=%d\n", type);
    printf("tcpSvr: count=%d\n", count);
    printf("tcpSvr: Closing acceptHandle=%d\n", acceptHandle);
    mpClose(acceptHandle);
    free(inBuffer);
    free(outBuffer);
     */
}



