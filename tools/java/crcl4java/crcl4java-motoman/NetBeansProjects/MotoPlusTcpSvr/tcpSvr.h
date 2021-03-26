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
(aka "MOTOPLUS SDK FOR VISUAL STUDIO USER’S MANUAL")

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


/* 
 * File:   tcpSvr.h
 * Author: Will Shackleford {@literal <william.shackleford@nist.gov>}
 *
 * Created on September 27, 2016, 2:12 PM
 */

#ifndef TCPSVR_H
#define TCPSVR_H

#ifdef __cplusplus
extern "C" {
#endif


    extern void tcpSvr(void);

#ifdef __cplusplus
}
#endif

#endif /* TCPSVR_H */

