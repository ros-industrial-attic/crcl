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
package com.github.wshackle.crcl4java.motoman.kinematics;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public enum RemoteKinematicsConversionFunctionType {
    KINEMATICS_CONVERSION_INVALID(0),
    KINEMATICS_CONVERSION_CONVERT_AXES_TO_CART_POS(1),
    KINEMATICS_CONVERSION_CONVERT_CART_POS_TO_AXES(2),
    KINEMATICS_CONVERSION_CONVERT_PULSE_TO_ANGLE(3),
    KINEMATICS_CONVERSION_CONVERT_ANGLE_TO_PULSE(4),
    KINEMATICS_CONVERSION_CONVERT_FB_PULSE_TO_PULSE(5),
    KINEMATICS_CONVERSION_MAKE_FRAME(6), // reserved but not implemented.
    KINEMATICS_CONVERSION_INV_FRAME(7), // reserved but not implemented.
    KINEMATICS_CONVERSION_ROT_FRAME(8), // reserved but not implemented.
    KINEMATICS_CONVERSION_MUL_FRAME(9), // reserved but not implemented.
    KINEMATICS_CONVERSION_ZYX_EULER_TO_FRAME(10), // reserved but not implemented.
    KINEMATICS_CONVERSION_FRAME_TO_ZYX_EULER(11), // reserved but not implemented.
    KINEMATICS_CONVERSION_CROSS_PRODUCT(12), // reserved but not implemented.
    KINEMATICS_CONVERSION_INNER_PRODUCT(13); // reserved but not implemented.

    private RemoteKinematicsConversionFunctionType(int id) {
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
