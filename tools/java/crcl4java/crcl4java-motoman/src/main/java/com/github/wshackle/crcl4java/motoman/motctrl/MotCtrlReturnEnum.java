/*
 * This software is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * Software Copywrite/Warranty Disclaimer
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
package com.github.wshackle.crcl4java.motoman.motctrl;

import crcl.utils.XFuture;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public enum MotCtrlReturnEnum {
    SUCCESS(0, "Success"),
    E_MP_MOT_FAILURE(-1, "Error"),
    E_MP_MOT_SVOFF(-2, "turning off servo power."),
    E_MP_MOT_HOLD(-3, "stopped by hold."),
    E_MP_MOT_ALARM_OCCUR(-4, "alarm / error occurring."),
    E_MP_MOT_HOME_UNSET(-5, "unset to home position."),
    E_MP_MOT_HOME_UNCHK(-6, "home position is not checked."),
    E_MP_MOT_START_UNREADY(-7, "not PLAY mode."),
    E_MP_MOT_START_COLLISION(-8, "JOB playback was collision."),
    E_MP_MOT_GRP_DUPLICATE(-9, "JOB playback group is duplicate."),
    E_MP_MOT_TIMEOUT(-16, "elapsed timeout."),
    E_MP_MOT_INTERRUPT(-17, "playback interrupted."),
    E_MP_MOT_GRP_NOT_EXIST(-32, "specified group is not exist."),
    E_MP_MOT_BASE_LACK(-33, "base group is lacked."),
    E_MP_MOT_INTP_OUTRNG(-34, "interpolation command is out of range."),
    E_MP_MOT_USER_OUTRNG(-35, "user coordinate system No. is out of range."),
    E_MP_MOT_TASK_OUTRNG(-36, "task number is out of range"),
    E_MP_MOT_BASE_TASK_UNMATCH(-37, "robot task no. and base group task no. are not the same."),
    E_MP_MOT_MASTER_GRP_ILLEGAL(-38, "coordinated motion master group does not exist or sepcifying is illegal."),
    E_MP_MOT_SLAVE_GRP_ILLEGAL(-39, "coordinated motion slave group does not exist or sepcifying is illegal."),
    E_MP_MOT_MASTER_INTP_OUTRNG(-40, "coordinated motion master group's interpolation command is out of range."),
    E_MP_MOT_SLAVE_INTP_OUTRNG(-41, "coordinated motion slave group's interpolation command is out of range."),
    E_MP_MOT_MASTER_LACK(-42, "coordinated motion master group lacked."),
    E_MP_MOT_SYNC_TASK_UNMATCH(-43, "coordinated motion group's task no. are not the same."),
    E_MP_MOT_SYNC_ILLEGAL(-44, "coordinated motion multiple master or multiple slave are specified."),
    E_MP_MOT_TOOL_OUTRNG(-45, "tool number is out of range ");

    private final int id;
    private final String message;

    private static Map<Integer,MotCtrlReturnEnum> map = new HashMap<>();
    
    static {
        for (int i = 0; i < MotCtrlReturnEnum.values().length; i++) {
            MotCtrlReturnEnum m = MotCtrlReturnEnum.values()[i];
            map.put(m.getId(),m);
        }
    }
    
    public static MotCtrlReturnEnum fromId(int id) {
        MotCtrlReturnEnum ret =  map.get(id);
        if(ret != SUCCESS) {
            System.out.println("");
            System.out.flush();
            StackTraceElement trace[] = Thread.currentThread().getStackTrace();
            System.err.println("trace = " +XFuture.traceToString(trace));
            System.err.println("MotCtrlReturnEnum.fromId("+id+") returning "+ret);
            System.err.flush();
            System.out.println("");
            System.out.flush();
        }
        return ret;
    }
    
    private MotCtrlReturnEnum(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "MotCtrlReturnEnum."+super.toString()+"{" + "id=" + id + ", message=" + message + '}';
    }
}
