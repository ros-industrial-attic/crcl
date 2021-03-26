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

import com.github.wshackle.crcl4java.motoman.motctrl.MotCtrlReturnEnum;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public enum RemoteMotFunctionType {
    MOT_INVALID(0),
    MOT_START(1),
    MOT_STOP(2),
    MOT_TARGET_CLEAR(3),
    MOT_JOINT_TARGET_SEND(4),
    MOT_COORD_TARGET_SEND(5),
    MOT_TARGET_RECEIVE(6),
    MOT_SET_COORD(7),
    MOT_SET_TOOL(8),
    MOT_SET_SPEED(9),
    MOT_SET_ORIGIN(10),
    MOT_SET_TASK(11),
    MOT_SET_SYNC(12),
    MOT_RESET_SYNC(13);

    private RemoteMotFunctionType(int id) {
        this.id = id;
    }

    private final int id;

//    private static Map<Integer, MotCtrlReturnEnum> map = new HashMap<>();
//
//    static {
//        for (int i = 0; i < MotCtrlReturnEnum.values().length; i++) {
//            MotCtrlReturnEnum m = MotCtrlReturnEnum.values()[i];
//            map.put(m.getId(), m);
//        }
//    }

    public int getId() {
        return id;
    }

}
