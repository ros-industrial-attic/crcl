/*
 * This is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * 
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain. This software is experimental.
 * NIST assumes no responsibility whatsoever for its use by other
 * parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic. We would appreciate
 * acknowledgment if the software is used. This software can be
 * redistributed and/or modified freely provided that any derivative works
 * bear some notice that they are derived from it, and any modified
 * versions bear some notice that they have been modified.
 * 
 */
package com.github.wshackle.crcl4java.commons.math;

import crcl.base.PointType;
import crcl.base.PoseType;
import crcl.base.VectorType;
import java.math.BigDecimal;
import java.util.logging.Logger;
import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;


/*
 * 
 * NOTE: Comments beginning with {@literal @} or {@literal >>>} are used by Checker Framework Comments
 * beginning with {@literal @} must have no spaces in the comment or Checker will ignore
 * it.
 *
 * See http://types.cs.washington.edu/checker-framework for null pointer
 * checks. This file can be compiled without the Checker Framework, but using
 * the framework allows potential NullPointerExceptions to be found.
 */

 /*>>>
import org.checkerframework.checker.nullness.qual.*;
 */
public class CRCLPosemathCommons {

    private CRCLPosemathCommons() {
        // never to be called.
    }

    /**
     * Convert a CRCL VectorType to commons-math Vector3D
     *
     * @param vIn vector to convert
     * @return commons-math representation of a Vector
     */
    public static Vector3D toCommonsVector3D(VectorType vIn) {
        return new Vector3D(
                vIn.getI(),
                vIn.getJ(),
                vIn.getK());
    }

    /**
     * Convert a CRCL VectorType to commons-math Vector3D
     *
     * @param ptIn point to convert
     * @return commons-math representation of a Vector
     */
    public static Vector3D toCommonsVector3D(PointType ptIn) {
        return new Vector3D(
                ptIn.getX(),
                ptIn.getY(),
                ptIn.getZ());
    }

    /**
     * Take the translational component of a CRCL Pose and convert it to
     * commons-math Vector3D
     *
     * @param poseIn pose to convert
     * @return commons-math representation of a Vector
     */
    public static Vector3D toCommonsVector3D(PoseType poseIn) {
        return toCommonsVector3D(poseIn.getPoint());
    }

    /**
     * Convert commons-math Vector3D to a CRCL Unit Vector without normalizing
     * WARNING: may produce non-normalized vectors
     *
     * @param vIn commons-math vector to convert
     * @param vOut optional location to store converted value in
     * @return CRCL VectorType of the unit of input vector
     */
    public static VectorType toCRCLVector(Vector3D vIn, VectorType vOut) {
        if (vOut == null) {
            vOut = new VectorType();
        }
        try {
            vOut.setI(vIn.getX());
            vOut.setJ(vIn.getY());
            vOut.setK(vIn.getZ());
        } catch (org.apache.commons.math3.exception.MathArithmeticException ex) {
            // If the input vector had zero magnitude return magnitude vector.
        }
        return vOut;
    }

    /**
     * Convert commons-math Vector3D to a without normalizing CRCL Unit Vector
     * WARNING: may produce non-normalized vectors
     *
     * @param vIn commons-math vector to convert
     * @return CRCL VectorType of the unit of input vector
     */
    public static VectorType toCRCLVector(Vector3D vIn) {
        return toCRCLVector(vIn, new VectorType());
    }

    /**
     * Normalize and convert commons-math Vector3D to a CRCL Unit Vector
     *
     * @param vIn commons-math vector to convert
     * @param vOut optional location to store converted value in
     * @return CRCL VectorType of the unit of input vector
     */
    public static VectorType toCRCLUnitVector(Vector3D vIn, VectorType vOut) {
        if (vOut == null) {
            vOut = new VectorType();
        }
        try {
            Vector3D vInUnit = vIn.normalize();
            vOut.setI(vInUnit.getX());
            vOut.setJ(vInUnit.getY());
            vOut.setK(vInUnit.getZ());
        } catch (org.apache.commons.math3.exception.MathArithmeticException ex) {
            // If the input vector had zero magnitude return magnitude vector.
        }
        return vOut;
    }

    /**
     * Normalize and convert commons-math Vector3D to a CRCL Unit Vector
     *
     * @param vIn commons-math vector to convert
     * @return CRCL VectorType of the unit of input vector
     */
    public static VectorType toCRCLUnitVector(Vector3D vIn) {
        return toCRCLUnitVector(vIn, new VectorType());
    }

    /**
     * Take the rotation component of PoseLocationIn and convert to commons-math
     * Rotation
     *
     * @param poseIn CRCL Pose to convert
     * @return converted rotational component
     */
    public static Rotation toCommonsRotation(PoseType poseIn) {
        return new Rotation(
                toCommonsVector3D(poseIn.getXAxis()),
                toCommonsVector3D(poseIn.getZAxis()),
                Vector3D.PLUS_I,
                Vector3D.PLUS_K);
    }

    /**
     * Convert commons-math Vector3D to CRCL Point
     *
     * @param vIn commons-math vector to convert
     * @param ptOut optional location to store new Point
     * @return converted vector as CRCL PointType
     */
    public static PointType toCRCLPoint(Vector3D vIn, PointType ptOut) {
        if (null == ptOut) {
            ptOut = new PointType();
        }
        ptOut.setX(vIn.getX());
        ptOut.setY(vIn.getY());
        ptOut.setZ(vIn.getZ());
        return ptOut;
    }

    /**
     * Convert commons-math Vector3D to CRCL Point
     *
     * @param vIn commons-math vector to convert
     * @return converted vector as CRCL PointType
     */
    public static PointType toCRCLPoint(Vector3D vIn) {
        return toCRCLPoint(vIn, new PointType());
    }

    private static final Logger LOG = Logger.getLogger(CRCLPosemathCommons.class.getName());
}
