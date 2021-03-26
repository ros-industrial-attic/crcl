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

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public enum KinReturnCode {
    E_KINEMA_SUCCESS(0),
    E_KINEMA_FAILURE(-1),
    E_KINEMA_CONV_IMPOSSIBLE(-2), // calculation of the specified inverse kinematics is impossible.
    E_KINEMA_GRP_OUT_RANGE(-3), // specified group No. is out of the range.
    E_KINEMA_TOOL_OUT_RANGE(-4), // specified tool No. is out of the range.
    E_KINEMA_KINEMA_TYPE_ERR(-5), // specified kinematics type was illegal.
    E_KINEMA_INTERPOLATION_INVALID(-6); // operation to the position and posture in which the interpolation is impossible.

    private KinReturnCode(int id) {
        this.id = id;
    }

    private final int id;

    private static Map<Integer, KinReturnCode> map = new HashMap<>();

    static {
        for (int i = 0; i < KinReturnCode.values().length; i++) {
            KinReturnCode m = KinReturnCode.values()[i];
            map.put(m.getId(), m);
        }
    }

    public static KinReturnCode fromInt(int i) {
        return map.get(i);
    }

    public int getId() {
        return id;
    }
}
