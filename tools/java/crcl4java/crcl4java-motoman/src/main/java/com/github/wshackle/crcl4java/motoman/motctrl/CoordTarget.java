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

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class CoordTarget extends Target {

    public CoordTarget(MP_INTP_TYPE intp, int id) {
        super(intp, id);
    }

    public CoordTarget(boolean isStraight, int id) {
        super(isStraight?MP_INTP_TYPE.MP_MOVL_TYPE:MP_INTP_TYPE.MP_MOVL_TYPE, id);
    }
    
    public CoordTarget() {
        this(false,0);
    }
    
    private final COORD_POS dst = new COORD_POS();

    /**
     * Get the value of dst
     *
     * @return the value of dst
     */
    public COORD_POS getDst() {
        return dst;
    }

    private final COORD_POS aux = new COORD_POS();

    /**
     * Get the value of aux
     *
     * @return the value of aux
     */
    public COORD_POS getAux() {
        return aux;
    }

    @Override
    public String toString() {
        return "CoordTarget{" +"id="+getId()+", intp="+getIntp() + ", dst=" + dst + ", aux=" + aux + '}';
    }
    
    
}
