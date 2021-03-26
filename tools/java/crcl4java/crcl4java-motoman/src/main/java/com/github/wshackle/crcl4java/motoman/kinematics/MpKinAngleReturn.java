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

import com.github.wshackle.crcl4java.motoman.MotoPlusConnection;
import java.util.Arrays;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class MpKinAngleReturn extends MpKinBaseReturn {
    
    public final int angle[] = new int[MotoPlusConnection.MP_GRP_AXES_NUM];

    @Override
    public String toString() {
        double dangle[] = new double[angle.length];
        for (int i = 0; i < dangle.length; i++) {
            dangle[i] = 0.0001*angle[i];
        }
        System.out.println("dangle = " + Arrays.toString(dangle));
        return "MpAngleReturn{" + "returnInt=" + returnInt + ", returnCode=" + returnCode 
                + ", angle=" + Arrays.toString(angle) 
                + ", dangle = " + Arrays.toString(dangle)
                + '}';
    }
    
    
}
