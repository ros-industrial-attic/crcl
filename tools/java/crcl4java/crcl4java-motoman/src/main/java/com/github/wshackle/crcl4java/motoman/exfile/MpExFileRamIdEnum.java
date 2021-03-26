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
package com.github.wshackle.crcl4java.motoman.exfile;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public enum MpExFileRamIdEnum {
    MP_DRV_ID_INVALID(0, ""),
    MP_DRV_ID_DRAM(1, "MPRAM1:0"),
    MP_DRV_ID_SRAM(2, "MPSRAM1:0"),
    MP_DRV_ID_USB0(3, "MPUSB0"),
    MP_DRV_ID_USB1(4, "MPUSB1");

    private MpExFileRamIdEnum(final int id, final String dotName) {
        this.id = id;
        this.name = dotName;
    }

    private final String name;
    private final int id;

//    private static Map<Integer, RemoteSys1FunctionType> map = new HashMap<>();
//
//    static {
//        for (int i = 0; i < RemoteSys1FunctionType.values().length; i++) {
//            RemoteSys1FunctionType m = RemoteSys1FunctionType.values()[i];
//            map.put(m.getId(), m);
//        }
//    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
