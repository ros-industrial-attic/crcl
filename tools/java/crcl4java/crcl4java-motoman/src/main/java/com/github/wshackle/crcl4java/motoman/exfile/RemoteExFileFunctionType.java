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
public enum RemoteExFileFunctionType {
    EX_FILE_CTRL_INVALID(0),
    EX_FILE_CTRL_GET_FILE_COUNT(1),
    EX_FILE_CTRL_GET_FILE_NAME(2),
    EX_FILE_CTRL_LOAD_FILE(3),
    EX_FILE_CTRL_SAVE_FILE(4),
    EX_FILE_CTRL_FD_READ_FILE(5),
    EX_FILE_CTRL_FD_WRITE_FILE(6),
    EX_FILE_CTRL_FD_GET_JOB_LIST(7);

    private RemoteExFileFunctionType(int id) {
        this.id = id;
    }

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

}
