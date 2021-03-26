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
package crcl.utils.server;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class UnitsScaleSet {

    final private  double angleScale;
    final private  double lengthScale;
    final private  double forceScale;
    final private  double torqueScale;

    public UnitsScaleSet(double angleScale, double lengthScale, double forceScale, double torqueScale) {
        this.angleScale = angleScale;
        this.lengthScale = lengthScale;
        this.forceScale = forceScale;
        this.torqueScale = torqueScale;
    }

    public UnitsScaleSet() {
        this(1.0,1.0,1.0,1.0);
    }

    public double getAngleScale() {
        return angleScale;
    }

    public double getLengthScale() {
        return lengthScale;
    }

    public double getForceScale() {
        return forceScale;
    }

    public double getTorqueScale() {
        return torqueScale;
    }

    @Override
    public String toString() {
        return "UnitsScaleSet{" + "angleScale=" + angleScale + ", lengthScale=" + lengthScale + ", forceScale=" + forceScale + ", torqueScale=" + torqueScale + '}';
    }
    
}
