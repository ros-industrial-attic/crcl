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

import com.github.wshackle.crcl4java.motoman.kinematics.MP_COORD;
import com.github.wshackle.crcl4java.motoman.sys1.MP_CART_POS_RSP_DATA;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class COORD_POS {
    public int x,y,z;
    public int rx,ry,rz;
    public int ex1,ex2;

    @Override
    public String toString() {
        return "COORD_POS{" + "x=" + x + ", y=" + y + ", z=" + z + ", rx=" + rx + ", ry=" + ry + ", rz=" + rz + ", ex1=" + ex1 + ", ex2=" + ex2 + '}';
    }
    
    public COORD_POS diff(COORD_POS other) {
        COORD_POS newCoordPos = new COORD_POS();
        newCoordPos.x = this.x - other.x;
        newCoordPos.y = this.y - other.y;
        newCoordPos.z = this.z - other.z;
        newCoordPos.rx = this.rx - other.rx;
        newCoordPos.ry = this.ry - other.ry;
        newCoordPos.rz = this.rz - other.rz;
        newCoordPos.ex1 = this.ex1 - other.ex1;
        newCoordPos.ex2 = this.ex2 - other.ex2;
        
        return newCoordPos;
    }
    
    public COORD_POS diff(MP_COORD other) {
        COORD_POS newCoordPos = new COORD_POS();
        newCoordPos.x = this.x - other.x;
        newCoordPos.y = this.y - other.y;
        newCoordPos.z = this.z - other.z;
        newCoordPos.rx = this.rx - other.rx;
        newCoordPos.ry = this.ry - other.ry;
        newCoordPos.rz = this.rz - other.rz;
        newCoordPos.ex1 = this.ex1 - other.ex1;
        newCoordPos.ex2 = this.ex2 - other.ex2;
        
        return newCoordPos;
    }
    
    public COORD_POS diff(MP_CART_POS_RSP_DATA other) {
        return diff(other.toCoordPos());
    }
    
    public double cartMag() {
        return Math.sqrt(x*x+y*y+z*z);
    }
    
    // MP_CART_POS_RSP_DATA
    public MP_COORD toMpCoord() {
        MP_COORD newMpCoord = new MP_COORD();
        newMpCoord.x = this.x;
        
        return newMpCoord;
    }
}
