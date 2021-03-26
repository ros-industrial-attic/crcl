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

import crcl.base.CRCLProgramType;
import crcl.base.PointType;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class CommonInfo {

    final private String[] remotePrograms;

    /**
     * Get the value of remotePrograms
     *
     * @return the value of remotePrograms
     */
    public String[] getRemotePrograms() {
        return remotePrograms;
    }

    /**
     * Get the value of remotePrograms at specified index
     *
     * @param index the index of remotePrograms
     * @return the value of remotePrograms at specified index
     */
    public String getRemotePrograms(int index) {
        return this.remotePrograms[index];
    }

    final private String currentFileName;

    /**
     * Get the value of currentFileName
     *
     * @return the value of currentFileName
     */
    public String getCurrentFileName() {
        return currentFileName;
    }

    final private CRCLProgramType currentProgram;

    /**
     * Get the value of currentProgram
     *
     * @return the value of currentProgram
     */
    public CRCLProgramType getCurrentProgram() {
        return currentProgram;
    }

    final private int programIndex;

    final private TransformInfo transformInfo;

    /**
     * Get the value of transformInfo
     *
     * @return the value of transformInfo
     */
    public TransformInfo getTransformInfo() {
        return transformInfo;
    }


    /**
     * Get the value of programIndex
     *
     * @return the value of programIndex
     */
    public int getProgramIndex() {
        return programIndex;
    }

    private CommonInfo(String[] remotePrograms, String currentFileName, CRCLProgramType currentProgram, int programIndex, TransformInfo transformInfo) {
        this.remotePrograms = remotePrograms;
        this.currentFileName = currentFileName;
        this.currentProgram = currentProgram;
        this.programIndex = programIndex;
        this.transformInfo = transformInfo;
    }

    static public CommonInfo defaultWithRemotePrograms(final String[] remotePrograms) {
        return new CommonInfo(remotePrograms,"",new CRCLProgramType(), 0, TransformInfo.fromTwoPairsOfPoints());
    }
    
    static public CommonInfo withNewProgram(CommonInfo orig,String[] remotePrograms, String currentFileName, CRCLProgramType currentProgram) {
        return new CommonInfo(remotePrograms,currentFileName,currentProgram,0,orig.getTransformInfo());
    }
    
    static public CommonInfo withRemotePrograms(CommonInfo orig, final String[] remotePrograms) {
        return new
            CommonInfo(remotePrograms,
                    orig.getCurrentFileName(),
                    orig.getCurrentProgram(),
                    orig.getProgramIndex(),
                    orig.getTransformInfo());
    }
    static public CommonInfo withProgramIndex(CommonInfo orig, final int programIndex) {
        return new
            CommonInfo(orig.getRemotePrograms(),
                    orig.getCurrentFileName(),
                    orig.getCurrentProgram(),
                    programIndex,
                    orig.getTransformInfo());
    }
    
    static public CommonInfo withTransformInfo(CommonInfo orig, TransformInfo transformInfo) {
        return new
            CommonInfo(orig.getRemotePrograms(),
                    orig.getCurrentFileName(),
                    orig.getCurrentProgram(),
                    orig.getProgramIndex(),
                    transformInfo);
    }
    
    static public CommonInfo withTransformInfoA1(CommonInfo orig, PointType a1) {
        return new
            CommonInfo(orig.getRemotePrograms(),
                    orig.getCurrentFileName(),
                    orig.getCurrentProgram(),
                    orig.getProgramIndex(),
                    TransformInfo.withA1(orig.getTransformInfo(), a1));
    }
    
    static public CommonInfo withTransformInfoA2(CommonInfo orig, PointType a2) {
        return new
            CommonInfo(orig.getRemotePrograms(),
                    orig.getCurrentFileName(),
                    orig.getCurrentProgram(),
                    orig.getProgramIndex(),
                    TransformInfo.withA2(orig.getTransformInfo(), a2));
    }
    
    static public CommonInfo withTransformInfoB1(CommonInfo orig, PointType b1) {
        return new
            CommonInfo(orig.getRemotePrograms(),
                    orig.getCurrentFileName(),
                    orig.getCurrentProgram(),
                    orig.getProgramIndex(),
                    TransformInfo.withB1(orig.getTransformInfo(), b1));
    }
    
    static public CommonInfo withTransformInfoB2(CommonInfo orig, PointType b2) {
        return new
            CommonInfo(orig.getRemotePrograms(),
                    orig.getCurrentFileName(),
                    orig.getCurrentProgram(),
                    orig.getProgramIndex(),
                    TransformInfo.withB2(orig.getTransformInfo(), b2));
    }
}
