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
package com.github.wshackle.crcl4java.motoman.sys1;

import com.github.wshackle.crcl4java.motoman.kinematics.MP_COORD;
import com.github.wshackle.crcl4java.motoman.motctrl.COORD_POS;
import java.util.Arrays;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class MP_CART_POS_RSP_DATA {

    public static final int MAX_CART_AXES = 6;
    public final int lPos[] = new int[MAX_CART_AXES];
    public short sConfig;

    
    public MP_CART_POS_RSP_DATA diff(MP_CART_POS_RSP_DATA other) {
        MP_CART_POS_RSP_DATA ret =new MP_CART_POS_RSP_DATA();
        for (int i = 0; i < lPos.length; i++) {
            ret.lPos[i] = this.lPos[i]-other.lPos[i];
        }
        ret.sConfig = (short) (this.sConfig ^ other.sConfig);
        return ret;
    }
    
    
    public MP_COORD toMpCoord() {
        MP_COORD coord = new MP_COORD();
        coord.x = lPos[0];
        coord.y = lPos[1];
        coord.z = lPos[2];
        coord.rx = lPos[3];
        coord.ry = lPos[4];
        coord.rz = lPos[5];
        return coord;
    }
    
    public COORD_POS toCoordPos() {
        COORD_POS coord = new COORD_POS();
        coord.x = lPos[0];
        coord.y = lPos[1];
        coord.z = lPos[2];
        coord.rx = lPos[3];
        coord.ry = lPos[4];
        coord.rz = lPos[5];
        return coord;
    }
    
    public boolean front() {
        return (sConfig & 1) == 0;
    }

    public boolean back() {
        return !front();
    }

    public boolean upper() {
        return (sConfig & (1 << 1)) == 0;
    }

    public boolean lower() {
        return !upper();
    }

    public boolean flip() {
        return (sConfig & (1 << 2)) == 0;
    }

    public boolean noFlip() {
        return !flip();
    }

    public boolean rLt180() {
        return (sConfig & (1 << 3)) == 0;
    }

    public boolean rGe180() {
        return !rLt180();
    }

    public boolean tLt180() {
        return (sConfig & (1 << 4)) == 0;
    }

    public boolean tGe180() {
        return !tLt180();
    }

    public boolean sLt180() {
        return (sConfig & (1 << 5)) == 0;
    }

    public boolean sGe180() {
        return !sLt180();
    }

    public double x() {
        return lx() / 1000.0;
    }

    public int lx() {
        return lPos[0];
    }

    public double y() {
        return ly() / 1000.0;
    }

    public int ly() {
        return lPos[1];
    }

    public double z() {
        return lz() / 1000.0;
    }

    public int lz() {
        return lPos[2];
    }

    public double rx() {
        return lrx() / 10000.0;
    }

    public int lrx() {
        return lPos[3];
    }

    public double ry() {
        return lry() / 10000.0;
    }

    public int lry() {
        return lPos[4];
    }

    public double rz() {
        return lrz() / 10000.0;
    }

    public int lrz() {
        return lPos[5];
    }

    @Override
    public String toString() {
        return "MP_CART_POS_RSP_DATA{"
                + "lPos=" + Arrays.toString(lPos)
                + ", x=" + x()
                + ", y=" + y()
                + ", z=" + z()
                + ", rx=" + rx()
                + ", ry=" + ry()
                + ", rz=" + rz()
                + ", sConfig=" + String.format("%d (0x%x)", sConfig, sConfig)
                + (front() ? " FRONT" : "")
                + (back() ? " BACK" : "")
                + (upper() ? " UPPER_ARM" : "")
                + (lower() ? " LOWER_ARM" : "")
                + (flip() ? " FLIP" : "")
                + (noFlip() ? " NO_FLIP" : "")
                + (rLt180() ? " (R<180)" : "")
                + (rGe180() ? " (R>=180)" : "")
                + (tLt180() ? " (T<180)" : "")
                + (tGe180() ? " (T>=180)" : "")
                + (sLt180() ? " (S<180)" : "")
                + (sGe180() ? " (S>=180)" : "")
                + '}';
    }

}
