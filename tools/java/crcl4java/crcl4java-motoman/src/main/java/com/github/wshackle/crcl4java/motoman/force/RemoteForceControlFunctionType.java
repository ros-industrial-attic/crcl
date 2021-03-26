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

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public enum RemoteForceControlFunctionType {

    FORCE_CONTROL_INVALID(0),
    FORCE_CONTROL_START_MEASURING(1),
    FORCE_CONTROL_GET_FORCE_DATA(2),
    FORCE_CONTROL_START_IMP(3),
    FORCE_CONTROL_SET_REFERENCE_FORCE(4),
    FORCE_CONTROL_END_IMP(5),
    FORCE_CONTROL_CONV_FORCE_SCALE(6),
    FORCE_CONTROL_GET_SENSOR_DATA(7);

    private RemoteForceControlFunctionType(int id) {
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
