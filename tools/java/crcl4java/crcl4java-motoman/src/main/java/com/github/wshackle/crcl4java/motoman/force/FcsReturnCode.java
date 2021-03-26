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
package com.github.wshackle.crcl4java.motoman.force;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public enum FcsReturnCode {

    E_FCS_SUCCESS(0), // == (OK),.
    E_FCS_SPECIFIED_ROB_ID(-1), // specified robot group ID's error.
    E_FCS_CTRL_GRP_NOT_EXIST(-2), // specified control group doesn't exist.
    E_FCS_ROB_USE_SL_FUNC(-3), // SL function which a robot uses is not specified.
    E_FCS_JOG_BUSY(-4), // execution is impossible during jog operation.
    E_FCS_MOVE_BUSY(-5), // execution is impossible during move instruction.
    E_FCS_RANGE_USER_FILE_NO(-6), // specified user coordinates File-no. is outside the range.
    E_FCS_UNREGIST_USER_FRAME(-7), // user coordinates file is unregistered.
    E_FCS_AX_OFFSET_CALC(-8), // servo part offset calculation failure (or), not performing.
    E_FCS_AX_IMP_MODE_START(-9), // servo part impedance control mode setting failure.
    E_FCS_AX_IMP_MODE_END(-10), // servo part impedance control mode cancellation failure.
    E_FCS_SV_UNITS_STRADDLED(-11), // axis is connected ranging over between servo units.
    E_FCS_AX_COMM_TIME_OVER(-12), // servo command processing time limit over.
    E_FCS_AX_COMM_FAILURE(-13), // servo command processing other errors.
    E_FCS_AX_COMM_OBJ_FULL(-14), // servo command execution instance is full.
    E_FCS_SENS2COORD_CONVERT(-15), // conversion error to specification coordinates from sensor coordinates.
    E_FCS_PR_MAKE_FAILURE(-16), // remake the current position failure.
    E_FCS_ARGUMENT_RANGE_OUTSIDE(-17), // outside of the argument value range.(this value is generic),
    E_FCS_DATA_ACQ_FAILURE(-18), // acquisition failure of sensor data.
    E_FCS_RTP_NOT_SUPPORTED(-19); // real-time process not supported.
    
    
//    OK(0),
//    E_FCS_SPECIFIED_ROB_ID(-1), // Robot group ID designation error
//    E_FCS_CTRL_GRP_NOT_EXIST(-2), //  The specified control groupdoes not exist.
//    E_FCS_JOG_BUSY(-4), // This API cannot be executed during JOG operation.
//    E_FCS_MOVE_BUSY(-5),//  This API cannot be execute while MOV command is executed.
//    E_FCS_ARGUMENT_RANGE_OUTSIDE(-17); //  The value is outside the range.

    private FcsReturnCode(int id) {
        this.id = id;
    }

    private final int id;

    private static Map<Integer, FcsReturnCode> map = new HashMap<>();

    static {
        for (int i = 0; i < FcsReturnCode.values().length; i++) {
            FcsReturnCode m = FcsReturnCode.values()[i];
            map.put(m.getId(), m);
        }
    }

    public static FcsReturnCode fromInt(int i) {
        return map.get(i);
    }

    public int getId() {
        return id;
    }

}
