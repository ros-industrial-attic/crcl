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
public class GuardHistoryElement {

    public final double time;
    public final double value;
    public final double x;
    public final double y;
    public final double z;

    public GuardHistoryElement(double time, double value, double x, double y, double z) {
        this.time = time;
        this.value = value;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getTime() {
        return time;
    }

    public double getValue() {
        return value;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "GuardHistoryElement{" + "time=" + time + ", value=" + value + ", x=" + x + ", y=" + y + ", z=" + z + '}';
    }

    

    
}
