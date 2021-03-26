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

import com.github.wshackle.crcl4java.motoman.motctrl.COORD_POS;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class MP_COORD {

    public int x, y, z;
    public int rx, ry, rz;
    public int ex1, ex2;

    public MP_COORD diff(MP_COORD other) {
        MP_COORD ret =new MP_COORD();
        ret.x = this.x - other.x;
        ret.y = this.y - other.y;
        ret.z = this.z - other.z;
        ret.rx = this.rx - other.rx;
        ret.ry = this.ry - other.ry;
        ret.rz = this.rz - other.rz;
        ret.ex1 = this.ex1 - other.ex1;
        ret.ex2 = this.ex2 - other.ex2;        
        return ret;
    }
    
     public MP_COORD diff(COORD_POS other) {
        MP_COORD ret =new MP_COORD();
        ret.x = this.x - other.x;
        ret.y = this.y - other.y;
        ret.z = this.z - other.z;
        ret.rx = this.rx - other.rx;
        ret.ry = this.ry - other.ry;
        ret.rz = this.rz - other.rz;
        ret.ex1 = this.ex1 - other.ex1;
        ret.ex2 = this.ex2 - other.ex2;        
        return ret;
    }
     
    @Override
    public String toString() {
        return "MP_COORD{" + "x=" + x + ", y=" + y + ", z=" + z + ", rx=" + rx + ", ry=" + ry + ", rz=" + rz + ", ex1=" + ex1 + ", ex2=" + ex2 + '}';
    }

    
}
