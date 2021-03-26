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
package com.github.wshackle.crcl4java.motoman.jbr;

import crcl.base.PoseType;
import crcl.utils.CRCLPosemath;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class JbrPose {

    public JbrPose(String name, int index, PoseType pose) {
        this.name = name;
        this.index = index;
        this.pose = pose;
    }

    
    private String name;

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    private int index;

    /**
     * Get the value of index
     *
     * @return the value of index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Set the value of index
     *
     * @param index new value of index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    private PoseType pose;

    /**
     * Get the value of pose
     *
     * @return the value of pose
     */
    public PoseType getPose() {
        return pose;
    }

    /**
     * Set the value of pose
     *
     * @param pose new value of pose
     */
    public void setPose(PoseType pose) {
        this.pose = pose;
    }

    @Override
    public String toString() {
        String poseString = null;
        try {
            poseString = CRCLPosemath.poseToString(pose);
        } catch(Exception e) {
            poseString = e.toString();
        }
        return "JbrPose{" + "name=" + name + ", index=" + index + ", pose=" + poseString + '}';
    }
}
