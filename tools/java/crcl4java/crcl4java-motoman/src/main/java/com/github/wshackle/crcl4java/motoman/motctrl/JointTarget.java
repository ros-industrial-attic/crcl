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

import java.util.Arrays;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class JointTarget extends Target {

    public JointTarget(MP_INTP_TYPE intp, int id) {
        super(intp, id);
    }

    
    public JointTarget(int id) {
        super(MP_INTP_TYPE.MP_MOVJ_TYPE, id);
    }
    
    public JointTarget() {
        super(MP_INTP_TYPE.MP_MOVJ_TYPE, 0);
    }
    
    public final static int MP_GRP_AXES_NUM = 8;

    private final int[] dst = new int[MP_GRP_AXES_NUM];

    /**
     * Get the value of dst
     *
     * @return the value of dst
     */
    public int[] getDst() {
        return dst;
    }

    private final int[] aux = new int[MP_GRP_AXES_NUM];

    /**
     * Get the value of aux
     *
     * @return the value of aux
     */
    public int[] getAux() {
        return aux;
    }

    @Override
    public String toString() {
        return "JointTarget{" +"id="+getId()+", intp="+getIntp()+ ", dst=" + Arrays.toString(dst) + ", aux=" + Arrays.toString(aux) + '}';
    }

    
}
