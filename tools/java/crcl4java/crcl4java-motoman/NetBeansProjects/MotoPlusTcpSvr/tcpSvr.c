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

#ifdef __gnu_linux__
#include <sys/types.h>          /* See NOTES */
#include <sys/socket.h>

#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <stdlib.h>
#include <sys/select.h>
#endif


// I had issues so I can't trust stdlib.h to declare this properly across platforms.
extern void *malloc(size_t);

// for API & FUNCTIONS
void runAcceptTcpClientsTask(ULONG portNo);
#define PORT        12222

void tcpSvr(void) {

    runAcceptTcpClientsTask(PORT);

    mpSuspendSelf;
}


#define MAX_CLIENT_HANDLES (256)
static int clientHandles[MAX_CLIENT_HANDLES];
static int maxClientHandleIndex = 0;

void printTcpSvrInfo() {
    printf("NIST motoPlustTcpSvr: sizeof(short)=%ld\n", sizeof (short));
    printf("NIST motoPlustTcpSvr: sizeof(int)=%ld\n", sizeof (int));
    printf("NIST motoPlustTcpSvr: sizeof(long)=%ld\n", sizeof (long));
    printf("NIST motoPlustTcpSvr: sizeof(LONG)=%ld\n", sizeof (LONG));
    printf("NIST motoPlustTcpSvr: sizeof(ULONG)=%ld\n", sizeof (ULONG));
    printf("NIST motoPlustTcpSvr: angleLen=%d\n", getAngleLen());
    printf("NIST motoPlustTcpSvr: MP_GRP_AXES_NUM=%d\n", MP_GRP_AXES_NUM);
    printf("NIST motoPlustTcpSvr: MP_FCS_AXES_NUM=%d\n", MP_FCS_AXES_NUM);
    printf("NIST motoPlustTcpSvr: sizeof(MP_CART_POS_RSP_DATA)=%ld\n", sizeof (MP_CART_POS_RSP_DATA));
    printf("NIST motoPlustTcpSvr: sizeof(MP_TARGET)=%ld\n", sizeof (MP_TARGET));
    printf("NIST motoPlustTcpSvr: sizeof(MP_COORD)=%ld\n", sizeof (MP_COORD));
    printf("NIST motoPlustTcpSvr: MP_R1_GID = %d\n", MP_R1_GID);
    printf("NIST motoPlustTcpSvr: mpCtrlGrpId2GrpNo(MP_R1_GID) = %d\n", mpCtrlGrpId2GrpNo(MP_R1_GID));
    printf("NIST motoPlustTcpSvr: MP_R2_GID = %d\n", MP_R2_GID);
    printf("NIST motoPlustTcpSvr: mpCtrlGrpId2GrpNo(MP_R2_GID) = %d\n", mpCtrlGrpId2GrpNo(MP_R2_GID));
    printf("NIST motoPlustTcpSvr: MP_B1_GID = %d\n", MP_B1_GID);
    printf("NIST motoPlustTcpSvr: mpCtrlGrpId2GrpNo(MP_B1_GID) = %d\n", mpCtrlGrpId2GrpNo(MP_B1_GID));
    printf("NIST motoPlustTcpSvr: MP_B2_GID = %d\n", MP_B2_GID);
    printf("NIST motoPlustTcpSvr: mpCtrlGrpId2GrpNo(MP_B2_GID) = %d\n", mpCtrlGrpId2GrpNo(MP_B2_GID));
    printf("NIST motoPlustTcpSvr: MP_S1_GID = %d\n", MP_S1_GID);
    printf("NIST motoPlustTcpSvr: mpCtrlGrpId2GrpNo(MP_S1_GID) = %d\n", mpCtrlGrpId2GrpNo(MP_S1_GID));
    printf("NIST motoPlustTcpSvr: MP_S2_GID = %d\n", MP_S2_GID);
    printf("NIST motoPlustTcpSvr: mpCtrlGrpId2GrpNo(MP_S2_GID) = %d\n", mpCtrlGrpId2GrpNo(MP_S2_GID));
}

void runAcceptTcpClientsTask(ULONG portNo) {
    int sockHandle;
    struct sockaddr_in serverSockAddr;
    int rc;

    int acceptHandle;
    struct sockaddr_in clientSockAddr;
    int sizeofSockAddr;
    int selectRet;
    int maxFd = 0;
    fd_set readFdSet;
    fd_set exceptFdSet;
    int emptyIndexFound = 0;
    int i = 0;
    int requestsHandled = 0;
    int exceptCount = 0;
    int failCount = 0;
    printf("NIST motoPlustTcpSvr: sizeof(clientHandles)=  %ld\n", sizeof (clientHandles));
    memset(clientHandles, 0, sizeof (clientHandles));

    /*
        printf("NIST motoPlustTcpSvr: sizeof(taskIdArray)=  %ld\n", sizeof (taskIdArray));
        memset(taskIdArray, 0, sizeof (taskIdArray));
     */

    printf("NIST motoPlustTcpSvr: TCP server starting for port %ld\n", portNo);

    sockHandle = mpSocket(AF_INET, SOCK_STREAM, 0);
    if (sockHandle < 0) {
        printf("NIST motoPlustTcpSvr: socket returned %d\n", sockHandle);
        printf("NIST motoPlustTcpSvr: Error = %s\n", strerror(errno));
        return;
    }

    memset(&serverSockAddr, 0, sizeof (serverSockAddr));
    serverSockAddr.sin_family = AF_INET;
    serverSockAddr.sin_addr.s_addr = INADDR_ANY;
    serverSockAddr.sin_port = mpHtons(portNo);
    /*
        printf("NIST motoPlustTcpSvr: portNo=%us\n", serverSockAddr.sin_port);
     */
    printf("NIST motoPlustTcpSvr: Binding socket.\n");
    rc = mpBind(sockHandle, (struct sockaddr *) &serverSockAddr, sizeof (serverSockAddr));

    if (rc < 0) {
        printf("NIST motoPlustTcpSvr: Bind returned %d\n", rc);
        printf("NIST motoPlustTcpSvr: Error = %s\n", strerror(errno));
        goto closeSockHandle;
    }

    printf("NIST motoPlustTcpSvr: Listen socket.\n");
    rc = mpListen(sockHandle, SOMAXCONN);
    if (rc < 0) {
        printf("NIST motoPlustTcpSvr: Listen returned %d\n", rc);
        printf("NIST motoPlustTcpSvr: Error = %s\n", strerror(errno));
        goto closeSockHandle;
    }

    while (1) {

        FD_ZERO(&readFdSet);
        FD_ZERO(&exceptFdSet);

        FD_SET(sockHandle, &readFdSet);
        FD_SET(sockHandle, &exceptFdSet);
        maxFd = sockHandle;


        for (i = 0; i < maxClientHandleIndex; i++) {
            if (clientHandles[i] > 0) {
                FD_SET(clientHandles[i], &readFdSet);
                FD_SET(clientHandles[i], &exceptFdSet);
                maxFd = maxFd > clientHandles[i] ? maxFd : clientHandles[i];
            }
        }
        /*
                printf("NIST motoPlustTcpSvr: Calling select(%d,%p,NULL,%p,NULL) ...\n", (maxFd + 1), &readFdSet, &exceptFdSet);
         */
        selectRet = mpSelect(maxFd + 1, &readFdSet, NULL, &exceptFdSet, NULL);

        /*
                printf("NIST motoPlustTcpSvr: select returned %d\n", selectRet);
         */
        if (selectRet <= 0) {
            printf("NIST motoPlustTcpSvr: mpSelect returned %d\n", selectRet);
            printf("NIST motoPlustTcpSvr: Error = %s\n", strerror(errno));
            for (i = 0; i < maxClientHandleIndex; i++) {
                printf("NIST motoPlustTcpSvr: clientHandles[i=%d]=%d\n",
                        i, clientHandles[i]);
                if (clientHandles[i] > 0) {
                    printf("NIST motoPlustTcpSvr: closing clientHandles[i=%d]=%d\n",
                            i, clientHandles[i]);
                    mpClose(clientHandles[i]);
                }
                clientHandles[i] = 0;
                printf("NIST motoPlustTcpSvr: setting clientHandles[i=%d]=%d\n",
                        i, clientHandles[i]);
            }
            printf("NIST motoPlustTcpSvr: maxFd=%d\n", maxFd);
            printf("NIST motoPlustTcpSvr: maxClientHandleIndex=%d\n", maxClientHandleIndex);
            maxClientHandleIndex = 0;
            printf("NIST motoPlustTcpSvr: maxClientHandleIndex=%d\n", maxClientHandleIndex);
            continue;
        }
        if (FD_ISSET(sockHandle, &readFdSet)) {
            memset(&clientSockAddr, 0, sizeof (clientSockAddr));
            sizeofSockAddr = sizeof (clientSockAddr);
            acceptHandle = mpAccept(sockHandle, (struct sockaddr *) &clientSockAddr, &sizeofSockAddr);
            printf("NIST motoPlustTcpSvr: acceptHandle=%d\n", acceptHandle);
            printTcpSvrInfo();
            if (acceptHandle <= 0) {
                continue;
            }
            emptyIndexFound = 0;
            for (i = 0; i < maxClientHandleIndex; i++) {
                printf("NIST motoPlustTcpSvr: clientHandles[i=%d]=%d\n",
                        i, clientHandles[i]);
                if (clientHandles[i] < 2) {
                    emptyIndexFound = 1;
                    clientHandles[i] = acceptHandle;
                    printf("NIST motoPlustTcpSvr: Setting clientHandles[i=%d]=%d\n",
                            i, clientHandles[i]);
                    break;
                }
            }
            if (emptyIndexFound == 0) {
                if (maxClientHandleIndex >= MAX_CLIENT_HANDLES) {
                    printf("NIST motoPlustTcpSvr: Too many open socket handle\n");
                    mpClose(acceptHandle);
                    for (i = 0; i < maxClientHandleIndex; i++) {
                        printf("NIST motoPlustTcpSvr: clientHandles[i=%d]=%d\n",
                                i, clientHandles[i]);
                        if (clientHandles[i] > 0) {
                            mpClose(clientHandles[i]);
                        }
                        clientHandles[i] = 0;
                    }
                    maxClientHandleIndex = 0;
                    printf("NIST motoPlustTcpSvr: sizeof(clientHandles)=  %ld\n", sizeof (clientHandles));
                    memset(clientHandles, 0, sizeof (clientHandles));
                    continue;
                }
                clientHandles[maxClientHandleIndex] = acceptHandle;
                printf("NIST motoPlustTcpSvr: clientHandles[maxClientHandleIndex=%d]=%d\n",
                        maxClientHandleIndex, clientHandles[maxClientHandleIndex]);
                maxClientHandleIndex++;
                printf("NIST motoPlustTcpSvr: maxClientHandleIndex=%d\n", maxClientHandleIndex);
            }
        }
        for (i = 0; i < maxClientHandleIndex; i++) {
            if (clientHandles[i] > 0) {
                if (FD_ISSET(clientHandles[i], &exceptFdSet)) {
                    printf("NIST motoPlustTcpSvr: exceptFdSet set for clientHandle\n");
                    printf("NIST motoPlustTcpSvr: Closing clientHandles[i=%d]=%d\n",
                            i, clientHandles[i]);
                    mpClose(clientHandles[i]);
                    clientHandles[i] = 0;
                }
                if (FD_ISSET(clientHandles[i], &readFdSet)) {
                    requestsHandled++;
                    if (requestsHandled % 200 == 0) {
                        printf("NIST motoPlustTcpSvr: requestsHandled=%d\n",
                                requestsHandled);
                        printf("NIST motoPlustTcpSvr: maxClientHandleIndex=%d\n",
                                maxClientHandleIndex);
                    }
                    if (handleSingleConnection(clientHandles[i]) != 0) {
                        failCount++;
                        printf("NIST motoPlustTcpSvr: handleSingleConnection() returned non-zero\n");
                        printf("NIST motoPlustTcpSvr: Closing clientHandles[i=%d]=%d\n",
                                i, clientHandles[i]);
                        printf("NIST motoPlustTcpSvr: failCount=%d\n",
                                failCount);
                        printf("NIST motoPlustTcpSvr: requestsHandled=%d\n",
                                requestsHandled);
                        mpClose(clientHandles[i]);
                        clientHandles[i] = 0;
                        printf("NIST motoPlustTcpSvr: setting clientHandles[i=%d]=%d\n",
                                i, clientHandles[i]);
                    }
                }
            }
        }

        /*
                taskIdArray[taskSpawnCounter % 100] = mpCreateTask(MP_PRI_TIME_NORMAL, MP_STACK_SIZE, (FUNCPTR) handleSingleConnection,
                        acceptHandle, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                printf("NIST motoPlustTcpSvr: taskSpawnCounter = %d\n", taskSpawnCounter);
                printf("NIST motoPlustTcpSvr: taskIdArray[taskSpawnCounter%%100] = %d\n", taskIdArray[taskSpawnCounter % 100]);
                taskSpawnCounter++;
                printf("NIST motoPlustTcpSvr: taskSpawnCounter = %d\n", taskSpawnCounter);
         */
    }
closeSockHandle:
    mpClose(sockHandle);

    printf("NIST motoPlustTcpSvr: maxClientHandleIndex=%d\n",
            maxClientHandleIndex);
    printf("NIST motoPlustTcpSvr: requestsHandled=%d\n",
            requestsHandled);
    return;
}

