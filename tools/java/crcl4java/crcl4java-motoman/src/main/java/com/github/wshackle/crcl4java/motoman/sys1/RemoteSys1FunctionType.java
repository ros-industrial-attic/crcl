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
package com.github.wshackle.crcl4java.motoman.sys1;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public enum RemoteSys1FunctionType {
    SYS1_INVALID(0),
    SYS1_GET_VAR_DATA(1),
    SYS1_PUT_VAR_DATA(2),
    SYS1_GET_CURRENT_CART_POS(3),
    SYS1_GET_CURRENT_PULSE_POS(4),
    SYS1_GET_CURRENT_FEEDBACK_PULSE_POS(5),
    SYS1_GET_DEG_POS_EX(6),
    SYS1_INVALID_RESERVED2(7), // Place holder for GET_RAD_EX  not implemented.
    SYS1_GET_SERVO_POWER(8),
    SYS1_SET_SERVO_POWER(9),
    SYS1_READIO(10),
    SYS1_WRITEIO(11),
    SYS1_GET_MODE(12),
    SYS1_GET_CYCLE(13),
    SYS1_GET_ALARM_STATUS(14),
    SYS1_GET_ALARM_CODE(15),
    SYS1_GET_RTC(16),
    SYS1_GET_CURRENT_CART_POS_EX(17),
    SYS1_GET_SVAR_INFO(18),
    SYS1_PUT_SVAR_INFO(19);

    private RemoteSys1FunctionType(int id) {
        this.id = id;
    }

    private final int id;

//    private static Map<Integer, RemoteSys1FunctionType> map = new HashMap<>();
//
//    static {
//        for (int i = 0; i < RemoteSys1FunctionType.values().length; i++) {
//            RemoteSys1FunctionType m = RemoteSys1FunctionType.values()[i];
//            map.put(m.getId(), m);
//        }
//    }
    public int getId() {
        return id;
    }

}
