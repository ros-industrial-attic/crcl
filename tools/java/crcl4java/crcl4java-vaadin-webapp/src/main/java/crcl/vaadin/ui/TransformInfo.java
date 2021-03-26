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
package crcl.vaadin.ui;

import crcl.base.PointType;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class TransformInfo {

    final private PointType a1;

    /**
     * Get the value of a1
     *
     * @return the value of a1
     */
    public PointType getA1() {
        return a1;
    }


    final private PointType a2;

    /**
     * Get the value of a2
     *
     * @return the value of a2
     */
    public PointType getA2() {
        return a2;
    }


    final private PointType b1;

    /**
     * Get the value of b1
     *
     * @return the value of b1
     */
    public PointType getB1() {
        return b1;
    }


    final private PointType b2;

    /**
     * Get the value of b2
     *
     * @return the value of b2
     */
    public PointType getB2() {
        return b2;
    }

    private TransformInfo(PointType a1, PointType a2, PointType b1, PointType b2) {
        this.a1 = a1;
        this.a2 = a2;
        this.b1 = b1;
        this.b2 = b2;
    }
    
    public static TransformInfo fromTwoPairsOfPoints() {
        return new TransformInfo(new PointType(), new PointType(), new PointType(), new PointType());
    }
    

    public static TransformInfo withA1(TransformInfo orig, PointType a1) {
        return new TransformInfo(a1,orig.getA2(),orig.getB1(),orig.getB2());
    }
   
    public static TransformInfo withA2(TransformInfo orig, PointType a2) {
        return new TransformInfo(orig.getA1(),a2,orig.getB1(),orig.getB2());
    }
    
    public static TransformInfo withB1(TransformInfo orig, PointType b1) {
        return new TransformInfo(orig.getA1(),orig.getA2(),b1,orig.getB2());
    }
    
    public static TransformInfo withB2(TransformInfo orig, PointType b2) {
        return new TransformInfo(orig.getA1(),orig.getA2(),orig.getB1(),b2);
    }
    
}
