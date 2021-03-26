/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

#include "motoPlus.h"
#include "tcpSvr.h"

extern void mpUsrRoot(int arg1, int arg2, int arg3, int arg4, int arg5,
	       int arg6, int arg7, int arg8, int arg9, int arg10);
// for LOCAL DEFINITIONS
static int tIDTCP;

void mpUsrRoot(int arg1, int arg2, int arg3, int arg4, int arg5,
	       int arg6, int arg7, int arg8, int arg9, int arg10) {
    tIDTCP = mpCreateTask(MP_PRI_TIME_NORMAL, MP_STACK_SIZE, (FUNCPTR)tcpSvr,
						arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	
}