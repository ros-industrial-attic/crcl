/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   motoPlus.h
 * Author: shackle
 *
 * Created on October 3, 2016, 4:45 PM
 */

#ifdef USE_FAKE_MOTOPLUS

#ifndef MOTOPLUS_H
#define MOTOPLUS_H



struct sockaddr;

#ifdef __cplusplus
extern "C" {
#endif

#include <stddef.h>
#include <sys/select.h>

#define NO_WAIT (0)
#define WAIT_FOREVER (-1)

    typedef int BOOL;
    typedef int STATUS;
    typedef unsigned long ULONG;
    typedef unsigned short USHORT;
    typedef short SHORT;
    typedef char CHAR;
    typedef long LONG;
    typedef unsigned char UCHAR;
    typedef unsigned long CTRLG_T;

#define MP_GRP_AXES_NUM 8 
#define MP_STACK_SIZE  0 
#define MP_PRI_TIME_NORMAL 3 

#define mpSuspendSelf {}
#define mpHtons(x)  htons(x)

    typedef long MP_JOINT[MP_GRP_AXES_NUM];

#define MAX_CART_AXES (6) 

    typedef struct {
        LONG lPos[MAX_CART_AXES];
        SHORT sConfig;
        CHAR reserved[2];
    } MP_CART_POS_RSP_DATA;

    typedef struct {
        USHORT usType;
        USHORT usIndex;
    } MP_VAR_INFO;

    typedef struct {
        USHORT usType;
        USHORT usIndex;
        LONG ulValue;
    } MP_VAR_DATA;

    typedef enum {
        MP_PULSE_TYPE, /* pulse data type. */
        MP_ANGLE_TYPE, /* angle data type. */
        MP_BASE_TYPE, /* base coordinate data type. */
        MP_ROBOT_TYPE, /* robot coordinate data type. */
        MP_USER_TYPE, /* user coordinate data type. */
    } MP_COORD_TYPE;

    typedef enum {
        MP_R1_GID = 0,
        MP_R2_GID,
        MP_R3_GID,
        MP_R4_GID,
        MP_B1_GID,
        MP_B2_GID,
        MP_B3_GID,
        MP_B4_GID,
        MP_S1_GID,
        MP_S2_GID,
        MP_S3_GID
    } MP_GRP_ID_TYPE;

    typedef struct {
        long vj; /* joint velocity(0.01[%]). */
        long v; /* trajectory velocity(0.1[mm/sec]). */
        long vr; /* orientation velocity(0.1[deg/sec]). */
    } MP_SPEED;

    typedef enum {
        MP_MOV_NOP_TYPE, /* NOP */
        MP_MOVJ_TYPE, /* link interpolation type. */
        MP_MOVL_TYPE, /* linear interpolation type. */
        MP_MOVC_TYPE, /* circular interpolation type. */
    } MP_INTP_TYPE;

    typedef struct {
        long x, y, z;
        long rx, ry, rz;
        long ex1, ex2;
    } MP_COORD;

    typedef union {
        MP_COORD coord;
        MP_JOINT joint;
    } MP_POS;

    typedef struct {
        int id; /* target ID. */
        MP_INTP_TYPE intp; /* interpolation type. */
        MP_POS dst; /* destination position. */
        MP_POS aux; /* passing(auxiliary) position. */
    } MP_TARGET;

    typedef struct /* Control Group send data */ {
        CTRLG_T sCtrlGrp; /* Control Group */
    } MP_CTRL_GRP_SEND_DATA;

    typedef struct {
        SHORT sRobotNo;
        SHORT sFrame;
        SHORT sToolNo;
        CHAR reserved[2];
    } MP_CARTPOS_EX_SEND_DATA;


#define MAX_PULSE_AXES   (8) /* Maximum pulse axes */

    typedef struct /* Pulse position receive data */ {
        LONG lPos[MAX_PULSE_AXES]; /* Pulse position */
    } MP_PULSE_POS_RSP_DATA;

    typedef struct /* Radian position receive data */ {
        LONG lRadPos[MAX_PULSE_AXES]; /* Radian position */
    } MP_RAD_POS_RSP_DATA;

    typedef struct /* Degree position receive data */ {
        LONG lDegPos[MAX_PULSE_AXES]; /* Degree position */
    } MP_DEG_POS_RSP_DATA;

#define MP_POS_UNIT_DEGREE   (1)
#define MP_POS_UNIT_DISTANCE  (2)
#define MP_POS_UNIT_RADIAN   (3)

    typedef struct /* Radian position and unit receive data */ {
        LONG lRadPos[MAX_PULSE_AXES]; /* Radian position */
        LONG lRadUnit[MAX_PULSE_AXES]; /* Unit */
    } MP_RAD_POS_RSP_DATA_EX;

    typedef struct /* Degree position and unit receive data */ {
        LONG lDegPos[MAX_PULSE_AXES]; /* Degree position */
        LONG lDegUnit[MAX_PULSE_AXES]; /* Unit */
    } MP_DEG_POS_RSP_DATA_EX;

    typedef struct /* Feedback pulse position receive data */ {
        LONG lPos[MAX_PULSE_AXES]; /* Pulse position */
    } MP_FB_PULSE_POS_RSP_DATA;

    typedef struct /* Servo speed data */ {
        LONG lSpeed[MAX_PULSE_AXES]; /* Speed */
    } MP_SERVO_SPEED_RSP_DATA;

    typedef struct /* Feedback speed data */ {
        LONG lSpeed[MAX_PULSE_AXES]; /* Speed */
    } MP_FB_SPEED_RSP_DATA;

    typedef struct /* Torque receive data */ {
        LONG lTorquePcnt[MAX_PULSE_AXES]; /* Torque [percent] */
    } MP_TORQUE_RSP_DATA;

    typedef struct /* Torque receive data (absolute) */ {
        LONG lTorqueNm[MAX_PULSE_AXES]; /* Torque [percent] */
    } MP_TORQUE_EX_RSP_DATA;

    typedef struct /* JOG speed receive data */ {
        SHORT sJogSpeed; /* JOG speed */
        CHAR reserved[2];
    } MP_JOGSPEED_RSP_DATA;

    typedef struct /* JOG coordinate receive data */ {
        USHORT sJogCoord; /* JOG coordinate */
        CHAR reserved[2];
    } MP_JOGCOORD_RSP_DATA;

#define MAX_SVAR_SIZE    (16) /* size of S-Variable */

    typedef struct /* S-Variable recieve data */ {
        UCHAR ucValue[MAX_SVAR_SIZE + 1]; /* S-Variable data(with delimiter) */
        UCHAR reserved[3];
    } MP_SVAR_RECV_INFO;

    typedef struct {
        SHORT sServoPower;
        CHAR reserved[2];
    } MP_SERVO_POWER_SEND_DATA;

    typedef struct {
        SHORT sServoPower;
        CHAR reserved[2];
    } MP_SERVO_POWER_RSP_DATA;

    typedef struct /* Error number receive data */ {
        USHORT err_no; /* Error number */
        CHAR reserved[2];
    } MP_STD_RSP_DATA;

    typedef struct {
        ULONG ulAddr;
    } MP_IO_INFO;

    typedef struct {
        ULONG ulAddr;
        ULONG ulValue;
    } MP_IO_DATA;

    typedef struct {
        SHORT sMode; /* Mode (Play/Teach) */
        SHORT sRemote; /* Remote mode */
    } MP_MODE_RSP_DATA;

    typedef struct /* Cycle receive data */ {
        SHORT sCycle; /* Cycle */
        CHAR reserved[2];
    } MP_CYCLE_RSP_DATA;

    typedef struct /* Alarm status receive data */ {
        SHORT sIsAlarm; /* Alarm status */
        CHAR reserved[2];
    } MP_ALARM_STATUS_RSP_DATA;

#define MAX_ALARM_COUNT    (4) /* Maximum Alarm no. */

    typedef struct {
        USHORT usAlarmNo[MAX_ALARM_COUNT]; /* Alarm number */
        USHORT usAlarmData[MAX_ALARM_COUNT]; /* Alarm Data */
    } MP_ALARM_DATA;

    typedef struct /* Alarm code receive data */ {
        USHORT usErrorNo; /* Error number */
        USHORT usErrorData; /* Error data */
        USHORT usAlarmNum; /* Number of Alarm */
        CHAR reserved[2];
        MP_ALARM_DATA AlarmData; /* Alarm data */
    } MP_ALARM_CODE_RSP_DATA;


#ifndef TRANS_FILE_LEN
#define TRANS_FILE_LEN (32 + 1 + 3)
#endif /*TRANS_FILE_LEN*/

    typedef struct {
        char cFileName[TRANS_FILE_LEN + 1];
        char reserved[3];
    } MP_FILE_NAME_SEND_DATA;

#define MP_LIST_DATA_SIZE   (1000)

    typedef struct {
        unsigned short err_no;
        unsigned short uIsEndFlag;
        unsigned short uListDataNum;
        unsigned char cListData[MP_LIST_DATA_SIZE];
        char reserved[2];
    } MP_GET_JOBLIST_RSP_DATA;

    typedef struct {
        USHORT usType;
        USHORT usIndex;
        UCHAR ucValue[MAX_SVAR_SIZE + 1];
        UCHAR reserved[3];
    } MP_SVAR_SEND_INFO;

#ifdef __cplusplus
    typedef int (*FUNCPTR) (...); /* ptr to function returning int */
    typedef void (*VOIDFUNCPTR) (...); /* ptr to function returning void */
#else
    typedef int (*FUNCPTR) (); /* ptr to function returning int */
    typedef void (*VOIDFUNCPTR) (); /* ptr to function returning void */
#endif   /* _cplusplus */



    /* typedefs */
    typedef unsigned int BITSTRING;
#define BITALL_ZERO 0

    /* generic argument */
#define MP_GRP_NUM 4 // number of control groups.
#define MP_GRP_AXES_NUM 8 // number per one control group of control axes.
#define MP_ROB_NUM 4 // number of robots.
#define MP_BASE_NUM MP_ROB_NUM // number of base control groups.
#define MP_STATION_NUM 3 // number of station control groups.
#define MP_GRP_KIND 3 // kind number of control groups.
#define MP_JOB_NAME_LEN 32 // job name length.
#define MP_JOB_TASK_NUM 6 // number of running multijob.

    /* definition master & sub task(=Job execution task) id's */
#define MP_MASTER_TSK_ID 0

    enum {
        MP_MASTER_TSK = MP_MASTER_TSK_ID,
        MP_SUB1_TSK,
        MP_SUB2_TSK,
        MP_SUB3_TSK,
        MP_SUB4_TSK,
        MP_SUB5_TSK
    };

#if !defined(CLEAR) || (CLEAR!=0)
#define CLEAR  0
#endif

#if defined(_WRS_KERNEL) && !defined(__INCmotoProh)
    typedef MSSG_T EXEJT_T;
#define MP_GRP_CONFIG GRP_CONFIG
#else /* specified in RTP or Client Side */
    typedef unsigned long EXEJT_T; // running master & sub-task status.[bit-string(d0~d6)]. BIT-ON:activated task
    typedef unsigned long CTRLG_T; // control group specified.[bit-string(d0~d3)]. BIT-ON:activated group
#if !defined(__INCmotoProh) // for building at executable file(.vxe)
    typedef char CHAR;
    typedef long LONG;
    typedef short SHORT;
    typedef float FLOAT;
    typedef double DOUBLE;
#endif

    /* control group configuration structure. */
    typedef struct {
        CTRLG_T ctrl_grp;
        UCHAR axes_config[MP_GRP_NUM];
    } MP_GRP_CONFIG;
#endif /* _WRS_KERNEL */

#define EXEJT_NONE (EXEJT_T)0
#define CTRLG_NONE (CTRLG_T)0
#define CHK_LR_INCLUSION_ZRNG(l, r) (((l) & (r)) == 0)
#define CHK_LR_INCLUSION_ZROK(l, r) (((l) | (r)) != (r))



    /* MOTO-Plus Kinematic API's return values */
#define E_KINEMA_FAILURE  (ERROR)
#define E_KINEMA_CONV_IMPOSSIBLE (-2) // calculation of the specified inverse kinematics is impossible.
#define E_KINEMA_GRP_OUT_RANGE  (-3) // specified group No. is out of the range.
#define E_KINEMA_TOOL_OUT_RANGE  (-4) // specified tool No. is out of the range.
#define E_KINEMA_KINEMA_TYPE_ERR (-5) // specified kinematics type was illegal.
#define E_KINEMA_INTERPOLATION_INVALID (-6) // operation to the position and posture in which the interpolation is impossible.

    /* mpConvCartPosToAxes kinematics type */
    typedef enum {
        MP_KINEMA_DEFAULT,
        MP_KINEMA_DELTA,
        MP_KINEMA_FIG
    } MP_KINEMA_TYPE;

    /* Distance data unit conversion constant */
#define MP_DIST_UNIT 1000

    /* type of frame */
    typedef struct {
        double nx, ny, nz;
        double ox, oy, oz;
        double ax, ay, az;
        double px, py, pz;
    } MP_FRAME;

    /* type of position vector */
    typedef struct {
        double x;
        double y;
        double z;
    } MP_XYZ;


    /* generic argument & check macros */
#define MP_FCS_AXES_NUM  6 // number of 6-axes force sensor data elements.
    typedef int MP_FCS_AXES_DATA[MP_FCS_AXES_NUM]; // type of 6-axes force sensor data.

    /* MOTO-Plus FCS(Force Control Service) API's return values */
#define E_FCS_SUCCESS   (0) // == (OK).
#define E_FCS_SPECIFIED_ROB_ID  (-1) // specified robot group ID's error.
#define E_FCS_CTRL_GRP_NOT_EXIST (-2) // specified control group doesn't exist.
#define E_FCS_ROB_USE_SL_FUNC  (-3) // SL function which a robot uses is not specified.
#define E_FCS_JOG_BUSY   (-4) // execution is impossible during jog operation.
#define E_FCS_MOVE_BUSY   (-5) // execution is impossible during move instruction.
#define E_FCS_RANGE_USER_FILE_NO (-6) // specified user coordinates File-no. is outside the range.
#define E_FCS_UNREGIST_USER_FRAME (-7) // user coordinates file is unregistered.
#define E_FCS_AX_OFFSET_CALC  (-8) // servo part offset calculation failure (or) not performing.
#define E_FCS_AX_IMP_MODE_START  (-9) // servo part impedance control mode setting failure.
#define E_FCS_AX_IMP_MODE_END  (-10) // servo part impedance control mode cancellation failure.
#define E_FCS_SV_UNITS_STRADDLED (-11) // axis is connected ranging over between servo units.
#define E_FCS_AX_COMM_TIME_OVER  (-12) // servo command processing time limit over.
#define E_FCS_AX_COMM_FAILURE  (-13) // servo command processing other errors.
#define E_FCS_AX_COMM_OBJ_FULL  (-14) // servo command execution instance is full.
#define E_FCS_SENS2COORD_CONVERT (-15) // conversion error to specification coordinates from sensor coordinates.
#define E_FCS_PR_MAKE_FAILURE  (-16) // remake the current position failure.
#define E_FCS_ARGUMENT_RANGE_OUTSIDE (-17) // outside of the argument value range.(this value is generic)
#define E_FCS_DATA_ACQ_FAILURE  (-18) // acquisition failure of sensor data.
#define E_FCS_RTP_NOT_SUPPORTED  (-19) // real-time process not supported.

    /* definition robots control group id's */
    typedef enum {
        MP_FCS_R1ID = 0,
        MP_FCS_R2ID
    } MP_FCS_ROB_ID;

    /* specified coordinate types */
    enum {
        FCS_BASE_TYPE,
        FCS_ROBO_TYPE,
        FCS_TOOL_TYPE,
        FCS_USER_TYPE,
        FCS_SENS_TYPE,
        FCS_FLANGE_TYPE
    };

    /* specified cartesian axes mask */
#define FCS_CART_AXES_MASK 0x3f // specified [X, Y, Z, Rx, Ry, Rz]

    /* data type definition of API's argument */
#define MP_FCS_OFFSET_DATA MP_FCS_AXES_DATA // type of offset data.
#define MP_FCS_SENS_DATA MP_FCS_AXES_DATA // type of force sensor data.
#define MP_FCS_IMP_COEFF MP_FCS_AXES_DATA // type of impedance coefficient.
#define MP_FCS_FREF_DATA MP_FCS_AXES_DATA // type of reference force data.

    /* for definition of the argument <option_ctrl> specified */
#define FCS_CONTACT_STABILITY_OPT 0x00000001 // contact stability processing demand.
#define FCS_LAST_FORCE_ORDER_OPT 0x00000002 // last force order level keeping.(execution time of mpFcsStartImp)

    /* definition of conversion scale */
    enum {
        FCS_FORCE_SCALE_10E0, // scale is changed 1 times.
        FCS_FORCE_SCALE_10EP1, // scale is changed 10 times.
        FCS_FORCE_SCALE_10EN1, // scale is changed 0.1 times.
        FCS_FORCE_SCALE_10EN2, // scale is changed 0.01 times.
        FCS_FORCE_SCALE_10EN3 // scale is changed 0.001 times.
    };

    /* function declarations */


    extern int mpCreateTask(int mpPriSpec, int stackSize, FUNCPTR entryPt,
            int arg1, int arg2, int arg3, int arg4, int arg5,
            int arg6, int arg7, int arg8, int arg9, int arg10);

    extern int mpRecv(int s, char *buf, int bufLen, int flags);
    extern int mpSend(int s, const char *buf, int bufLen, int flags);
    extern int mpSelect(int width, fd_set *pReadFds, fd_set *pWriteFds, fd_set *pExceptFds, struct timeval *pTimeOut);
    extern int mpCtrlGrpId2GrpNo(int in);

    extern STATUS mpTaskSuspend(int tid);

    extern int mpSocket(int domain, int type, int protocol);

    extern int mpListen(int s, int backlog);

    extern int mpAccept(int s, struct sockaddr *addr, int *addrlen);

    extern int mpBind(int s, struct sockaddr *name, int namelen);

    extern int mpConnect(int s, struct sockaddr *name, int namelen);

    extern int mpRecv(int s, char *buf, int bufLen, int flags);

    extern int mpSend(int s, const char *buf, int bufLen, int flags);

    extern STATUS mpClose(int fd);

    extern int mpMotStart(int options);

    extern int mpMotStop(int options);

    extern int mpMotTargetClear(CTRLG_T grp, int options);

    extern int mpMotTargetSend(CTRLG_T grp, MP_TARGET *target, int timeout);

    extern int mpMotTargetReceive(int grpNo, int id, int *recvId, int timeout, int options);

    extern int mpMotSetCoord(int grpNo, MP_COORD_TYPE type, int aux);

    extern int mpMotSetTool(int grpNo, int toolNo);


    extern int mpMotSetSpeed(int grpNo, MP_SPEED *spd);

    extern int mpMotSetOrigin(int grpNo, int options);

    extern int mpMotSetTask(int grpNo, int taskNo);

    extern int mpMotSetSync(int grpNo, int aux, int options);

    extern int mpMotResetSync(int grpNo);

    extern LONG mpGetVarData(MP_VAR_INFO *sData, LONG* rData, LONG num);

    extern LONG mpPutVarData(MP_VAR_DATA *sData, LONG num);

    extern LONG mpGetSVarInfo(MP_VAR_INFO *sData, MP_SVAR_RECV_INFO* rData, LONG num);

    extern LONG mpPutSVarInfo(MP_SVAR_SEND_INFO *sData, LONG num);

    extern LONG mpGetCartPos(MP_CTRL_GRP_SEND_DATA *sData, MP_CART_POS_RSP_DATA *rData);

    extern LONG mpGetPulsePos(MP_CTRL_GRP_SEND_DATA *sData, MP_PULSE_POS_RSP_DATA *rData);

    extern LONG mpGetFBPulsePos(MP_CTRL_GRP_SEND_DATA *sData, MP_FB_PULSE_POS_RSP_DATA *rData);

    extern LONG mpGetDegPosEx(MP_CTRL_GRP_SEND_DATA *sData, MP_DEG_POS_RSP_DATA_EX *rData);

    extern LONG mpSetServoPower(MP_SERVO_POWER_SEND_DATA *sData, MP_STD_RSP_DATA *rData);

    extern LONG mpGetServoPower(MP_SERVO_POWER_RSP_DATA *rData);

    extern LONG mpReadIO(MP_IO_INFO *sData, USHORT* rData, LONG num);

    extern LONG mpWriteIO(MP_IO_DATA *sData, LONG num);

    extern LONG mpGetMode(MP_MODE_RSP_DATA *rData);

    extern LONG mpGetCycle(MP_CYCLE_RSP_DATA *rData);

    extern LONG mpGetAlarmStatus(MP_ALARM_STATUS_RSP_DATA *rData);

    extern LONG mpGetAlarmCode(MP_ALARM_CODE_RSP_DATA *rData);

    extern long mpRefreshFileList(short extensionId);

    extern long mpGetFileCount(void);
    extern long mpGetFileName(int index, char *fileName);

    extern long mpLoadFile(long mpRamDriveId, const char *loadPath, const char *fileName);
    extern long mpSaveFile(long mpRamDriveId, const char *savePath, const char *fileName);


    extern long mpFdWriteFile(int fd, MP_FILE_NAME_SEND_DATA *sData);
    extern long mpFdReadFile(int fd, MP_FILE_NAME_SEND_DATA *sData);
    extern long mpFdGetJobList(int fd, MP_GET_JOBLIST_RSP_DATA *rData);


    extern int mpCreate(const char * name, int flags);
    extern int mpOpen(const char * name, int flags, int mode);
    extern STATUS mpRemove(const char * name);
    extern STATUS mpClose(int fd);
    extern int mpRename(const char * oldName, const char * newName);
    extern int mpRead(int fd, char * buffer, size_t maxBytes);
    extern int mpWrite(int fd, char * buffer, size_t nBytes);
    extern int mpGetRtc(void);

    /*--- 02 ---*/
    extern int mpFcsStartMeasuring(MP_FCS_ROB_ID rob_id, int reset_time, MP_FCS_OFFSET_DATA offset_data);
    extern int mpFcsGetForceData(MP_FCS_ROB_ID rob_id, int coord_type, int uf_no, MP_FCS_SENS_DATA sens_data);
    extern int mpFcsStartImp(MP_FCS_ROB_ID rob_id, MP_FCS_IMP_COEFF m, MP_FCS_IMP_COEFF d, MP_FCS_IMP_COEFF k,
            int coord_type, int uf_no, BITSTRING cart_axes, BITSTRING option_ctrl);
    extern int mpFcsSetReferenceForce(MP_FCS_ROB_ID rob_id, MP_FCS_FREF_DATA fref_data);
    extern int mpFcsEndImp(MP_FCS_ROB_ID rob_id);
    extern int mpFcsConvForceScale(MP_FCS_ROB_ID rob_id, int scale);
    extern int mpFcsGetSensorData(MP_FCS_ROB_ID rob_id, MP_FCS_SENS_DATA sens_data);

    /*--- 00 ---*/
    extern int mpConvAxesToCartPos(unsigned int grp_no, long angle[MP_GRP_AXES_NUM], unsigned int tool_no, BITSTRING *fig_ctrl, MP_COORD *coord);
    extern int mpConvCartPosToAxes(unsigned int grp_no, MP_COORD *coord, unsigned int tool_no, BITSTRING fig_ctrl, long prev_angle[MP_GRP_AXES_NUM], MP_KINEMA_TYPE type, long angle[MP_GRP_AXES_NUM]);
    extern int mpConvPulseToAngle(unsigned int grp_no, long pulse[MP_GRP_AXES_NUM], long angle[MP_GRP_AXES_NUM]);
    extern int mpConvAngleToPulse(unsigned int grp_no, long angle[MP_GRP_AXES_NUM], long pulse[MP_GRP_AXES_NUM]);
    extern int mpConvFBPulseToPulse(unsigned int grp_no, long fbpulse[MP_GRP_AXES_NUM], long pulse[MP_GRP_AXES_NUM]);
    extern int mpMakeFrame(MP_XYZ* org_vector, MP_XYZ* x_vector, MP_XYZ* y_vector, MP_FRAME* frame);
    extern int mpInvFrame(MP_FRAME*org_frame, MP_FRAME* frame);
    extern int mpRotFrame(MP_FRAME* org_frame, double angle, MP_XYZ* vector, MP_FRAME* frame);
    extern int mpMulFrame(MP_FRAME* frame1, MP_FRAME* frame2, MP_FRAME* frame_prod);
    extern int mpZYXeulerToFrame(MP_COORD* coord, MP_FRAME* frame);
    extern int mpFrameToZYXeuler(MP_FRAME*frame, MP_COORD* coord);
    extern int mpCrossProduct(MP_XYZ* vector1, MP_XYZ* vector2, MP_XYZ* xyz_prod);
    extern int mpInnerProduct(MP_XYZ* vector1, MP_XYZ* vector2, double* double_prod);

#ifdef __cplusplus
}
#endif


#endif /* MOTOPLUS_H */

#endif /* USE_FAKE_MOTOPLUS */


