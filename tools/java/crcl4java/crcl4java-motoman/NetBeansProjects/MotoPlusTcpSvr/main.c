/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.c
 * Author: Will Shackleford {@literal <william.shackleford@nist.gov>}
 *
 * Created on September 27, 2016, 10:23 AM
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/select.h>

extern void mpUsrRoot(int arg1, int arg2, int arg3, int arg4, int arg5,
	       int arg6, int arg7, int arg8, int arg9, int arg10);

static int quit = 0;
void sigint_handle(int sig) {
    quit = 1;
}

/*
 *  There cannot be a main in an MotoPlus application but to fake it on 
 * linux this file calls the motoPlusMain. 
 * 
 * DO NOT include this file in the real motoplus application.
 */
int main(int argc, char** argv) {
    fd_set fds;
    mpUsrRoot(0,0,0,0,0,0,0,0,0,0);
    printf("MotoPluseTcpSvr main() waiting for SIGINT.\n");
    signal(SIGINT,sigint_handle);
    while(quit == 0) {
        usleep(1000000);
    }    
    
    return 0;
}

