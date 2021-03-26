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
package crcl.utils.kinematics;

import crcl.base.PoseType;
import crcl.utils.CRCLPosemath;
import static crcl.utils.CRCLUtils.getNonNullPoint;
import java.util.Arrays;
import java.util.logging.Logger;
import rcs.posemath.PmCartesian;
import rcs.posemath.PmException;
import rcs.posemath.PmRotationMatrix;
import rcs.posemath.PmRotationVector;
import rcs.posemath.PmRpy;
import rcs.posemath.PmSpherical;
import rcs.posemath.Posemath;

public class SimulatedKinematicsSimple {

    public static final int NUM_JOINTS = 6;
    public static final double DEFAULT_SEGLENGTHS[] = new double[]{50.0};
    public static final double DEFAULT_JOINTVALS[] = new double[]{200.0, 0.0, 0.0, 0.0, 0.0, 0.0};

    public PoseType jointsToPose(double jv[]) throws PmException {
        PoseType pose = new PoseType();
        return jointsToPose(jv, pose);
    }

    private double[] seglengths = DEFAULT_SEGLENGTHS;

    private double[] scaled_seglengths = Arrays.copyOf(seglengths, seglengths.length);

    private double scale = 1.0;

    /**
     * Get the value of scale
     *
     * @return the value of scale
     */
    public double getScale() {
        return scale;
    }

    /**
     * Set the value of scale
     *
     * @param scale new value of scale
     */
    public void setScale(double scale) {
        this.scale = scale;
        updateScaledSeglengths(scale);
    }

    private void updateScaledSeglengths(double scale1) {
        scaled_seglengths = new double[seglengths.length];
        for (int i = 0; i < scaled_seglengths.length; i++) {
            scaled_seglengths[i] = scale1 * seglengths[i];
        }
    }

    /**
     * Get the value of seglengths
     *
     * @return the value of seglengths
     */
    public double[] getSeglengths() {
        return seglengths;
    }

    /**
     * Set the value of seglengths
     *
     * @param seglengths new value of seglengths
     */
    public void setSeglengths(double[] seglengths) {
        this.seglengths = seglengths;
        updateScaledSeglengths(scale);
    }

//    public static void printRot9x9(double[][] r) {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                //System.err.printf("%+.3g, ", r[i][j]);
//            }
//            //System.err.println("");
//        }
//    }
    public static double[][] rot9x9mult(double[][] r1, double[][] r2) {
        double[][] rout
                = {
                    {0.0, 0.0, 0.0},
                    {0.0, 0.0, 0.0},
                    {0.0, 0.0, 0.0},};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    rout[i][j] += r1[i][k] * r2[k][j];
                }
            }
        }
        return rout;
    }

    public double[] poseToJoints(double[] _jv, PoseType pose) throws PmException {
        double[] jv = _jv;
        if (null == jv || _jv.length != NUM_JOINTS) {
            jv = new double[NUM_JOINTS];
        }
        double[] sl = this.scaled_seglengths;
        PmCartesian cart = CRCLPosemath.toPmCartesian(getNonNullPoint(pose));
        double endr = sl[0];
        PmRotationMatrix rmat = CRCLPosemath.toPmRotationMatrix(pose);
        PmCartesian endSeg = cart.add(rmat.multiply(new PmCartesian(-endr, 0.0, 0.0)));
        PmSpherical sphereTran = new PmSpherical();
        Posemath.pmCartSphConvert(endSeg, sphereTran);
        jv[0] = sphereTran.r / this.scale;
        jv[1] = Math.toDegrees(sphereTran.theta);
        jv[2] = Math.toDegrees(sphereTran.phi) - 90.0;
        PmRpy rpy = new PmRpy();
        Posemath.pmMatRpyConvert(rmat, rpy);
        jv[3] = Math.toDegrees(rpy.r);
        jv[4] = Math.toDegrees(rpy.p);
        jv[5] = Math.toDegrees(rpy.y);
        return jv;
    }

    public PoseType jointsToPose(double[] jv, PoseType _pose) throws PmException {
        PoseType pose = null;
        double[] sl = this.scaled_seglengths;
        PmSpherical sphereTran = new PmSpherical(Math.toRadians(jv[1]), Math.toRadians(jv[2] + 90.0), jv[0] * this.scale);
        PmCartesian endSeg = new PmCartesian();
        Posemath.pmSphCartConvert(sphereTran, endSeg);
        PmRpy rpy = new PmRpy(Math.toRadians(jv[3]), Math.toRadians(jv[4]), Math.toRadians(jv[5]));
        PmRotationMatrix rmat = new PmRotationMatrix();
        Posemath.pmRpyMatConvert(rpy, rmat);
        PmCartesian cart = endSeg.add(rmat.multiply(new PmCartesian(sl[0], 0, 0)));
        PmRotationVector rv = new PmRotationVector();
        Posemath.pmMatRotConvert(rmat, rv);
        pose = CRCLPosemath.toPoseType(cart, rv, _pose);
        return pose;
    }
    private static final Logger LOG = Logger.getLogger(SimulatedKinematicsSimple.class.getName());
}
