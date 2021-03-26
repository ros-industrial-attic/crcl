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

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public enum VarType {
    MP_RESTYPE_VAR_B((short)1),
    MP_RESTYPE_VAR_I((short)2),
    MP_RESTYPE_VAR_D((short)3),
    MP_RESTYPE_VAR_R((short)4),
    MP_RESTYPE_VAR_ROBOT((short)5),
    MP_RESTYPE_VAR_BASE((short)6),
    MP_RESTYPE_VAR_STATION((short)7),
    MP_RESTYPE_CIO((short)8),
    MP_RESTYPE_VAR_S((short)9),
    MP_RESTYPE_CIO_GENERAL_IN((short)11),
    MP_RESTYPE_CIO_GENERAL_OUT((short)12),
    MP_RESTYPE_CIO_EXTERNAL_IN((short)13),
    MP_RESTYPE_CIO_DL_IN((short)14),
    MP_RESTYPE_CIO_EXTERNAL_OUT((short)15),
    MP_RESTYPE_CIO_DL_OUT((short)16),
    MP_RESTYPE_CIO_SPECIAL_IN((short)17),
    MP_RESTYPE_CIO_SPECIAL_OUT((short)18),
    MP_RESTYPE_CIO_IF_PANEL((short)19),
    MP_RESTYPE_CIO_TEMP_RELAY((short)20),
    MP_RESTYPE_CIO_PBOX_IN((short)21),
    MP_RESTYPE_CIO_CONTROL_IN((short)22),
    MP_RESTYPE_CIO_DUMMY_IN((short)23),
    MP_RESTYPE_CIO_REGISTER((short)24);
    
    final short id;

    private VarType(short id) {
        this.id = id;
    }

    public short getId() {
        return id;
    }
    
    
}
