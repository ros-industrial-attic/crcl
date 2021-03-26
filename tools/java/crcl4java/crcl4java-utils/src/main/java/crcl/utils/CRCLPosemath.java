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
package crcl.utils;

import crcl.base.CRCLCommandType;
import crcl.base.CRCLProgramType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStatusType;
import crcl.base.DataThingType;
import crcl.base.EndCanonType;
import crcl.base.GripperStatusType;
import crcl.base.GuardsStatusesType;
import crcl.base.InitCanonType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.MiddleCommandType;
import crcl.base.MoveToType;
import crcl.base.PointType;
import crcl.base.PoseStatusType;
import crcl.base.PoseType;
import crcl.base.PoseToleranceType;
import crcl.base.SensorStatusesType;
import crcl.base.SettingsStatusType;
import crcl.base.VectorType;
import static crcl.utils.CRCLCopier.copy;
import static crcl.utils.CRCLUtils.getNonNullPoint;
import static crcl.utils.CRCLUtils.getNonNullXAxis;
import static crcl.utils.CRCLUtils.getNonNullZAxis;
import static crcl.utils.CRCLUtils.middleCommands;
import java.awt.geom.Point2D;
import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import java.math.BigDecimal;
import java.security.Guard;
import java.util.Objects;
import static java.util.Objects.requireNonNull;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;
import rcs.posemath.PmCartesian;
import rcs.posemath.PmEulerZyx;
import rcs.posemath.PmException;
import rcs.posemath.PmHomogeneous;
import rcs.posemath.PmPose;
import rcs.posemath.PmRotationMatrix;
import rcs.posemath.PmRotationVector;
import rcs.posemath.PmRpy;
import rcs.posemath.Posemath;
import rcs.posemath.PmQuaternion;


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
/**
 * Utility class for converting to/from CRCL classes and computing pose
 * multiplications.
 *
 * @author Will Shackleford
 * {@literal <william.shackleford@nist.gov>,<wshackle@gmail.com>}
 */
public class CRCLPosemath {

    private CRCLPosemath() {
        // never to be called.
    }

    /**
     * Add a joint status to the joint statuses list. (This method is used
     * primarily to avoid a nullness warning.)
     *
     * @param jst statuses object containing list
     * @param js status object to be added to list
     */
    @SuppressWarnings("nullness")
    public static void addJointStatus(JointStatusesType jst, JointStatusType js) {
        jst.getJointStatus().add(js);
    }

    /**
     * Create a new PoseType object set to refer the the given pt with xaxis =
     * 1,0,0, and zaxis = 0,0,1
     *
     * @param pt point to refer t
     * @return new PoseType object.
     */
    static public PoseType pose(PointType pt) {
        PoseType pose = new PoseType();
        pose.setPoint(pt);
        pose.setXAxis(vector(1.0, 0.0, 0.0));
        pose.setZAxis(vector(0.0, 0.0, 1.0));
        return pose;
    }

    /**
     * Create a new PoseType object set to refer the the given pt, xAxis, and
     * zAxis.
     *
     * @param pt point to refer to
     * @param xAxis x axis vector to refer to
     * @param zAxis z axis vector to refer to
     * @return new PoseType object.
     */
    static public PoseType pose(PointType pt, VectorType xAxis, VectorType zAxis) {
        PoseType pose = new PoseType();
        pose.setPoint(pt);
        pose.setXAxis(xAxis);
        pose.setZAxis(zAxis);
        return pose;
    }

    /**
     * Create a new PointType object with given x,y and z BigDecimal values.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return new PointType object.
     */
    static public PointType point(BigDecimal x, BigDecimal y, BigDecimal z) {
        PointType point = new PointType();
        point.setX(x.doubleValue());
        point.setY(y.doubleValue());
        point.setZ(z.doubleValue());
        return point;
    }

    /**
     * Create a new PointType object with given x,y and z double values that
     * will be converted to BigDecimal values.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return new PointType object.
     */
    static public PointType point(double x, double y, double z) {
        if (Double.isInfinite(x) || Double.isNaN(x)
                || Double.isInfinite(y) || Double.isNaN(y)
                || Double.isInfinite(z) || Double.isNaN(z)) {
            throw new IllegalArgumentException("point(" + x + "," + y + "," + z + ") contains NaN or Infinity");
        }
        PointType point = new PointType();
        point.setX(x);
        point.setY(y);
        point.setZ(z);
        return point;
    }

    /**
     * Create a new VectorType object from the given BigDecimal i,j,k vector
     * components.
     *
     * @param i i component of vector
     * @param j j component of vector
     * @param k k component of vector
     * @return new VectorType object.
     */
    static public VectorType vector(BigDecimal i, BigDecimal j, BigDecimal k) {
        VectorType vector = new VectorType();
        vector.setI(i.doubleValue());
        vector.setJ(j.doubleValue());
        vector.setK(k.doubleValue());
        return vector;
    }

    /**
     * Create a new VectorType object from the given double i,j,k vector
     * components which will be converted to BigDecimal values.
     *
     * @param i i component of vector
     * @param j j component of vector
     * @param k k component of vector
     * @return new VectorType object.
     */
    static public VectorType vector(double i, double j, double k) {
        VectorType vector = new VectorType();
        vector.setI(i);
        vector.setJ(j);
        vector.setK(k);
        return vector;
    }

    /**
     * Get the maximum commandID of any command in the program.
     *
     * @param prog program to check for commandID values.
     *
     * @return the maximum commandID of any command in the program or
     * BigInteger.ONE if the program is empty or all commandId values are null.
     */
    static public long getMaxId(CRCLProgramType prog) {
        long max = 1;
        if (null != prog) {
            InitCanonType initCmd = prog.getInitCanon();
            if (null != initCmd) {
                max = Math.max(max, initCmd.getCommandID());
            }
            for (MiddleCommandType cmd : middleCommands(prog)) {
                max = Math.max(max, cmd.getCommandID());
            }
            EndCanonType endCmd = prog.getEndCanon();
            if (null != endCmd) {
                max = Math.max(max, endCmd.getCommandID());
            }
        }
        return max;
    }

    /**
     * Get a pose from a possibly null status object that may have a null
     * poseStatus field.
     *
     * @param stat status to get pose from
     * @return pose or null if stat is null or has a null poseStatus field.
     */
    public static @Nullable
    PoseType getNullablePose(@Nullable CRCLStatusType stat) {
        if (stat != null) {
            PoseStatusType poseStatus = stat.getPoseStatus();
            if (null != poseStatus) {
                return poseStatus.getPose();
            }
        }
        return null;
    }

    /**
     * Extract the X and Y coordinates for a Point2D.Double. The Z coordinate is
     * ignored.
     *
     * @param pt input point
     * @return Point2D.Double with x and y from PointType
     */
    public static Point2D.Double xyPoint2D(PointType pt) {
        return new Point2D.Double(pt.getX(),
                pt.getY());
    }

    /**
     * Extract the X and Y coordinates for a Point2D.Double. The Z coordinate is
     * ignored and the rotation are ignored.
     *
     * @param pose input pose
     * @return Point2D.Double with x and y from PointType
     */
    public static Point2D.Double xyPoint2D(PoseType pose) {
        return xyPoint2D(getNonNullPoint(pose));
    }

    /**
     * Extract the cylindrical r and z coordinates for a Point2D.Double. The X
     * and Y coordinates are combined to compute r as the x of then
     * Point2D.Double and the z is extracted as the y of the new Point2D.Double
     *
     * @param pt input point
     * @return Point2D.Double with x and y from PointType
     */
    public static Point2D.Double rzPoint2D(PointType pt) {
        PmCartesian cart = CRCLPosemath.toPmCartesian(pt);
        return new Point2D.Double(Math.sqrt(cart.x * cart.x + cart.y * cart.y), cart.z);
    }

    /**
     * Extract the X and Y coordinates for a Point2D.Double. The X and Y
     * coordinates are combined to compute r as the x of then Point2D.Double and
     * the z is extracted as the y of the new Point2D.Double
     *
     * @param pose input pose
     * @return Point2D.Double with x and y from PointType
     */
    public static Point2D.Double rzPoint2D(PoseType pose) {
        return rzPoint2D(getNonNullPoint(pose));
    }

//    /**
//     * Copy or clone the vector.
//     *
//     * @param vec vector to be cloned
//     * @return VectorType with same initial values as vec but can be
//     * independently modified.
//     */
//    public static @Nullable
//    VectorType copy( /*@Nullable*/VectorType vec) {
//        if (null == vec) {
//            return null;
//        }
//        VectorType newVec = new VectorType();
//        newVec.setName(vec.getName());
//        newVec.setI(vec.getI());
//        newVec.setJ(vec.getJ());
//        newVec.setK(vec.getK());
//        return newVec;
//    }
//
//    /**
//     * Copy or clone the pose.
//     *
//     * @param pose pose to be cloned
//     * @return PoseType with same initial values as pose but can be
//     * independently modified.
//     */
//    public static @Nullable
//    PoseType copyNullable(@Nullable PoseType pose) {
//        if (null == pose) {
//            return null;
//        }
//        return copy(pose);
//    }
//
//    /**
//     * Copy or clone the pose.
//     *
//     * @param pose pose to be cloned
//     * @return PoseType with same initial values as pose but can be
//     * independently modified.
//     */
//    public static PoseAndSetType copy(PoseAndSetType pose) {
//        if (null == pose) {
//            throw new IllegalArgumentException("copy(PoseType) called with null argument.");
//        }
//        PoseAndSetType newPose = new PoseAndSetType();
//        newPose.setName(pose.getName());
//        PointType pt = copy(getNonNullPoint(pose));
//        if (null != pt) {
//            newPose.setPoint(pt);
//        }
//        VectorType xAxis = copy(pose.getNullableXAxis());
//        if (null != xAxis) {
//            newPose.setXAxis(xAxis);
//        }
//        VectorType zAxis = copy(pose.getNullableZAxis());
//        if (null != zAxis) {
//            newPose.setZAxis(zAxis);
//        }
//        return newPose;
//    }
//
//    /**
//     * Copy or clone the pose.
//     *
//     * @param pose pose to be cloned
//     * @return PoseType with same initial values as pose but can be
//     * independently modified.
//     */
//    public static PoseType copy(PoseType pose) {
//        if (null == pose) {
//            throw new IllegalArgumentException("copy(PoseType) called with null argument.");
//        }
//        if (pose instanceof PoseAndSetType) {
//            return copy((PoseAndSetType) pose);
//        }
//        PoseType newPose = new PoseType();
//        newPose.setName(pose.getName());
//        PointType pt = copy(getNonNullPoint(pose));
//        if (null != pt) {
//            newPose.setPoint(pt);
//        }
//        VectorType xAxis = copy(pose.getNullableXAxis());
//        if (null != xAxis) {
//            newPose.setXAxis(xAxis);
//        }
//        VectorType zAxis = copy(pose.getNullableZAxis());
//        if (null != zAxis) {
//            newPose.setZAxis(zAxis);
//        }
//        return newPose;
//    }
//
//    /**
//     * Copy or clone a status.
//     *
//     * @param status to be cloned
//     * @return CRCLStatusType with same initial values as pose but can be
//     * independently modified.
//     */
//    public static CRCLStatusType copy(CRCLStatusType status) {
//        if (null == status) {
//            throw new IllegalArgumentException("copy(CRCLStatusType) called with null argument.");
//        }
//        CRCLStatusType newStatus = new CRCLStatusType();
//        newStatus.setName(status.getName());
//        CommandStatusType commandStatus = copy(status.getCommandStatus());
//        if (null != commandStatus) {
//            newStatus.setCommandStatus(commandStatus);
//        }
//        JointStatusesType jointStatuses = copy(status.getJointStatuses());
//        if (null != jointStatuses) {
//            newStatus.setJointStatuses(jointStatuses);
//        }
//        PoseStatusType poseStatus = copy(status.getPoseStatus());
//        if (null != poseStatus) {
//            newStatus.setPoseStatus(poseStatus);
//        }
//        GripperStatusType gripperStatus = copy(status.getGripperStatus());
//        if (null != gripperStatus) {
//            newStatus.setGripperStatus(gripperStatus);
//        }
//        SettingsStatusType settingsStatus = copy(status.getSettingsStatus());
//        if (null != settingsStatus) {
//            newStatus.setSettingsStatus(settingsStatus);
//        }
//        SensorStatusesType sensorsStatus = copy(status.getSensorStatuses());
//        if (null != sensorsStatus) {
//            newStatus.setSensorStatuses(sensorsStatus);
//        }
//        GuardsStatusesType guardsStatus = copy(status.getGuardsStatuses());
//        if (null != guardsStatus) {
//            newStatus.setGuardsStatuses(guardsStatus);
//        }
//        return newStatus;
//    }
//
//    /**
//     * Copy or clone a settings status.
//     *
//     * @param settings to be cloned
//     * @return SettingsStatusType with same initial values as pose but can be
//     * independently modified.
//     */
//    public static @Nullable
//    SettingsStatusType copy(SettingsStatusType settings) {
//        if (null != settings) {
//            SettingsStatusType newSettings = new SettingsStatusType();
//            newSettings.setName(settings.getName());
//            newSettings.setAngleUnitName(settings.getAngleUnitName());
//            newSettings.setEndEffectorSetting(settings.getEndEffectorSetting());
//            newSettings.getEndEffectorParameterSetting().clear();
//            newSettings.getEndEffectorParameterSetting().addAll(settings.getEndEffectorParameterSetting());
//            newSettings.setForceUnitName(settings.getForceUnitName());
//            newSettings.setIntermediatePoseTolerance(settings.getIntermediatePoseTolerance());
//            newSettings.getJointLimits().clear();
//            newSettings.getJointLimits().addAll(settings.getJointLimits());
//            newSettings.setLengthUnitName(settings.getLengthUnitName());
//            newSettings.setMaxCartesianLimit(settings.getMaxCartesianLimit());
//            newSettings.setMinCartesianLimit(settings.getMinCartesianLimit());
//            newSettings.setPoseTolerance(settings.getPoseTolerance());
//            newSettings.getRobotParameterSetting().clear();
//            newSettings.getRobotParameterSetting().addAll(settings.getRobotParameterSetting());
//            newSettings.setRotAccelAbsolute(settings.getRotAccelAbsolute());
//            newSettings.setRotAccelRelative(settings.getRotAccelRelative());
//            newSettings.setRotSpeedAbsolute(settings.getRotSpeedAbsolute());
//            newSettings.setRotSpeedRelative(settings.getRotSpeedRelative());
//            newSettings.setTorqueUnitName(settings.getTorqueUnitName());
//            newSettings.setTransAccelAbsolute(settings.getTransAccelAbsolute());
//            newSettings.setTransAccelRelative(settings.getTransAccelRelative());
//            newSettings.setTransSpeedAbsolute(settings.getTransSpeedAbsolute());
//            newSettings.setTransSpeedRelative(settings.getTransSpeedRelative());
//            newSettings.setMotionCoordinated(settings.isMotionCoordinated());
//            return newSettings;
//        }
//        return null;
//    }
//
//    /**
//     * Copy or clone a settings status.
//     *
//     * @param sensorsStatuses to be cloned
//     * @return SettingsStatusType with same initial values as pose but can be
//     * independently modified.
//     */
//    public static @Nullable
//    SensorStatusesType copy(SensorStatusesType sensorsStatuses) {
//        if (null != sensorsStatuses) {
//            SensorStatusesType newSensorStatuses = new SensorStatusesType();
//            newSensorStatuses.setName(sensorsStatuses.getName());
//            if (null != sensorsStatuses.getOnOffSensorStatus()) {
//                newSensorStatuses.getOnOffSensorStatus().clear();
//                for (int i = 0; i < sensorsStatuses.getOnOffSensorStatus().size(); i++) {
//                    OnOffSensorStatusType onOffSensorStatus = sensorsStatuses.getOnOffSensorStatus().get(i);
//                    OnOffSensorStatusType onOffSensorStatusCopy = copy(onOffSensorStatus);
//                    if (null != onOffSensorStatusCopy) {
//                        newSensorStatuses.getOnOffSensorStatus().add(onOffSensorStatusCopy);
//                    }
//                }
//            }
//            if (null != sensorsStatuses.getScalarSensorStatus()) {
//                newSensorStatuses.getScalarSensorStatus().clear();
//                for (int i = 0; i < sensorsStatuses.getScalarSensorStatus().size(); i++) {
//                    ScalarSensorStatusType scalarSensorStatus = sensorsStatuses.getScalarSensorStatus().get(i);
//                    ScalarSensorStatusType scalarSensorStatusCopy = copy(scalarSensorStatus);
//                    if (null != scalarSensorStatusCopy) {
//                        newSensorStatuses.getScalarSensorStatus().add(scalarSensorStatusCopy);
//                    }
//                }
//            }
//
//            if (null != sensorsStatuses.getCountSensorStatus()) {
//                newSensorStatuses.getCountSensorStatus().clear();
//                for (int i = 0; i < sensorsStatuses.getCountSensorStatus().size(); i++) {
//                    CountSensorStatusType countSensorStatus = sensorsStatuses.getCountSensorStatus().get(i);
//                    CountSensorStatusType countSensorStatusCopy = copy(countSensorStatus);
//                    if (null != countSensorStatusCopy) {
//                        newSensorStatuses.getCountSensorStatus().add(countSensorStatusCopy);
//                    }
//                }
//            }
//
//            if (null != sensorsStatuses.getForceTorqueSensorStatus()) {
//                newSensorStatuses.getForceTorqueSensorStatus().clear();
//                for (int i = 0; i < sensorsStatuses.getForceTorqueSensorStatus().size(); i++) {
//                    ForceTorqueSensorStatusType forceTorqueSensorStatus = sensorsStatuses.getForceTorqueSensorStatus().get(i);
//                    ForceTorqueSensorStatusType forceTorqueSensorStatusCopy = copy(forceTorqueSensorStatus);
//                    if (null != forceTorqueSensorStatusCopy) {
//                        newSensorStatuses.getForceTorqueSensorStatus().add(forceTorqueSensorStatusCopy);
//                    }
//                }
//            }
//            return newSensorStatuses;
//        }
//        return null;
//    }
//
//    /**
//     * Copy or clone a settings status.
//     *
//     * @param guardStatuses to be cloned
//     * @return GuardsStatusesType with same initial values as pose but can be
//     * independently modified.
//     */
//    public static @Nullable
//    GuardsStatusesType copy(GuardsStatusesType guardStatuses) {
//        if (null != guardStatuses) {
//            GuardsStatusesType newGuardStatuses = new GuardsStatusesType();
//
//            newGuardStatuses.setName(guardStatuses.getName());
//            newGuardStatuses.setTriggerStopTimeMicros(guardStatuses.getTriggerStopTimeMicros());
//            newGuardStatuses.setTriggerCount(guardStatuses.getTriggerCount());
//            newGuardStatuses.setTriggerValue(guardStatuses.getTriggerValue());
//            final PoseType triggerPose = guardStatuses.getTriggerPose();
//            if (null != triggerPose) {
//                newGuardStatuses.setTriggerPose(copy(triggerPose));
//            }
//            final List<GuardType> oldGuardList = guardStatuses.getGuard();
//            final List<GuardType> newGuardList = newGuardStatuses.getGuard();
//            for (GuardType g : oldGuardList) {
//                if (null == g) {
//                    throw new RuntimeException("oldGuardList contains nulls: " + oldGuardList);
//                }
//                final GuardType copyOfG = copy(g);
//                if (null == copyOfG) {
//                    throw new RuntimeException("null == copyOfG");
//                }
//                newGuardList.add(copyOfG);
//            }
//            return newGuardStatuses;
//        }
//        return null;
//    }
//
//    /**
//     * Copy or clone a settings status.
//     *
//     * @param guard to be cloned
//     * @return GuardType with same initial values as pose but can be
//     * independently modified.
//     */
//    public static @Nullable
//    GuardType copy(@Nullable GuardType guard) {
//        if (null != guard) {
//            GuardType newGuard = new GuardType();
//            newGuard.setCheckCount(guard.getCheckCount());
//            newGuard.setLastCheckTime(guard.getLastCheckTime());
//            newGuard.setLastCheckValue(guard.getLastCheckValue());
//            newGuard.setName(guard.getName());
//            newGuard.setLimitType(guard.getLimitType());
//            newGuard.setLimitValue(guard.getLimitValue());
//            newGuard.setRecheckTimeMicroSeconds(guard.getRecheckTimeMicroSeconds());
//            newGuard.setSensorID(guard.getSensorID());
//            newGuard.setSubField(guard.getSubField());
//            return newGuard;
//        }
//        return null;
//    }
//
//    /**
//     * Copy or clone a settings status.
//     *
//     * @param forceTorqueSensorStatus to be cloned
//     * @return SettingsStatusType with same initial values as pose but can be
//     * independently modified.
//     */
//    public static @Nullable
//    ForceTorqueSensorStatusType copy(ForceTorqueSensorStatusType forceTorqueSensorStatus) {
//        if (null != forceTorqueSensorStatus) {
//            ForceTorqueSensorStatusType newForceTorqueSensorStatus = new ForceTorqueSensorStatusType();
//            newForceTorqueSensorStatus.setSensorID(forceTorqueSensorStatus.getSensorID());
//            newForceTorqueSensorStatus.setName(forceTorqueSensorStatus.getName());
//            newForceTorqueSensorStatus.setLastReadTime(forceTorqueSensorStatus.getLastReadTime());
//            newForceTorqueSensorStatus.setReadCount(forceTorqueSensorStatus.getReadCount());
//            newForceTorqueSensorStatus.setFx(forceTorqueSensorStatus.getFx());
//            newForceTorqueSensorStatus.setFy(forceTorqueSensorStatus.getFy());
//            newForceTorqueSensorStatus.setFz(forceTorqueSensorStatus.getFz());
//            newForceTorqueSensorStatus.setTx(forceTorqueSensorStatus.getTx());
//            newForceTorqueSensorStatus.setTy(forceTorqueSensorStatus.getTy());
//            newForceTorqueSensorStatus.setTz(forceTorqueSensorStatus.getTz());
//            copyOptionList(newForceTorqueSensorStatus.getSensorParameterSetting(), forceTorqueSensorStatus.getSensorParameterSetting());
//            return newForceTorqueSensorStatus;
//        }
//        return null;
//    }
//
//    /**
//     * Copy or clone a settings status.
//     *
//     * @param countSensorStatus to be cloned
//     * @return SettingsStatusType with same initial values as pose but can be
//     * independently modified.
//     */
//    public static @Nullable
//    CountSensorStatusType copy(CountSensorStatusType countSensorStatus) {
//        if (null != countSensorStatus) {
//            CountSensorStatusType newCountSensorStatus = new CountSensorStatusType();
//            newCountSensorStatus.setLastReadTime(countSensorStatus.getLastReadTime());
//            newCountSensorStatus.setReadCount(countSensorStatus.getReadCount());
//            newCountSensorStatus.setSensorID(countSensorStatus.getSensorID());
//            newCountSensorStatus.setName(countSensorStatus.getName());
//            newCountSensorStatus.setCountValue(countSensorStatus.getCountValue());
//            copyOptionList(newCountSensorStatus.getSensorParameterSetting(), countSensorStatus.getSensorParameterSetting());
//            return newCountSensorStatus;
//        }
//        return null;
//    }
//
//    /**
//     * Copy or clone a settings status.
//     *
//     * @param scalarSensorStatus to be cloned
//     * @return SettingsStatusType with same initial values as pose but can be
//     * independently modified.
//     */
//    public static @Nullable
//    ScalarSensorStatusType copy(ScalarSensorStatusType scalarSensorStatus) {
//        if (null != scalarSensorStatus) {
//            ScalarSensorStatusType newScalarSensorStatus = new ScalarSensorStatusType();
//            newScalarSensorStatus.setLastReadTime(scalarSensorStatus.getLastReadTime());
//            newScalarSensorStatus.setReadCount(scalarSensorStatus.getReadCount());
//            newScalarSensorStatus.setSensorID(scalarSensorStatus.getSensorID());
//            newScalarSensorStatus.setName(scalarSensorStatus.getName());
//            newScalarSensorStatus.setScalarValue(scalarSensorStatus.getScalarValue());
//            copyOptionList(newScalarSensorStatus.getSensorParameterSetting(), scalarSensorStatus.getSensorParameterSetting());
//            return newScalarSensorStatus;
//        }
//        return null;
//    }
//
//    /**
//     * Copy or clone a settings status.
//     *
//     * @param ParameterSettingType to be cloned
//     * @return ParameterSettingType with same initial values as arg but can be
//     * independently modified.
//     */
//    public static @Nullable
//    ParameterSettingType copy(ParameterSettingType parmSet) {
//        if (null != parmSet) {
//            ParameterSettingType newParmSet = new ParameterSettingType();
//            newParmSet.setName(parmSet.getName());
//            newParmSet.setParameterName(parmSet.getParameterName());
//            newParmSet.setParameterValue(parmSet.getParameterValue());
//            return newParmSet;
//        }
//        return null;
//    }
//
//    /**
//     * Copy or clone a settings status.
//     *
//     * @param onOffSensorStatus to be cloned
//     * @return SettingsStatusType with same initial values as pose but can be
//     * independently modified.
//     */
//    public static @Nullable
//    OnOffSensorStatusType copy(OnOffSensorStatusType onOffSensorStatus) {
//        if (null != onOffSensorStatus) {
//            OnOffSensorStatusType newOnOffSensorStatus = new OnOffSensorStatusType();
//            newOnOffSensorStatus.setLastReadTime(onOffSensorStatus.getLastReadTime());
//            newOnOffSensorStatus.setReadCount(onOffSensorStatus.getReadCount());
//            newOnOffSensorStatus.setSensorID(onOffSensorStatus.getSensorID());
//            newOnOffSensorStatus.setName(onOffSensorStatus.getName());
//            newOnOffSensorStatus.setOn(onOffSensorStatus.isOn());
//            copyOptionList(newOnOffSensorStatus.getSensorParameterSetting(), onOffSensorStatus.getSensorParameterSetting());
//            return newOnOffSensorStatus;
//        }
//        return null;
//    }
//
//    /**
//     * Copy or clone a gripper status.
//     *
//     * @param status status to be cloned
//     * @return GripperStatusType with same initial values as pose but can be
//     * independently modified.
//     */
//    public static @Nullable
//    GripperStatusType copy(@Nullable GripperStatusType status) {
//        if (null == status) {
//            return null;
//        }
//        GripperStatusType newStatus = null;
//        if (status instanceof VacuumGripperStatusType) {
//            VacuumGripperStatusType vacuumGripperStatus = (VacuumGripperStatusType) status;
//            VacuumGripperStatusType newVacuumGripperStatus = new VacuumGripperStatusType();
//            newVacuumGripperStatus.setIsPowered(vacuumGripperStatus.isIsPowered());
//            newStatus = newVacuumGripperStatus;
//        } else if (status instanceof ParallelGripperStatusType) {
//            ParallelGripperStatusType parallelGripperStatus = (ParallelGripperStatusType) status;
//            ParallelGripperStatusType newParallelGripperStatus = new ParallelGripperStatusType();
//            newParallelGripperStatus.setSeparation(parallelGripperStatus.getSeparation());
//            newStatus = newParallelGripperStatus;
//        } else if (status instanceof ThreeFingerGripperStatusType) {
//            ThreeFingerGripperStatusType threeFingerGripperStatus = (ThreeFingerGripperStatusType) status;
//            ThreeFingerGripperStatusType newThreeFingerGripperStatusType = new ThreeFingerGripperStatusType();
//            newThreeFingerGripperStatusType.setFinger1Force(threeFingerGripperStatus.getFinger1Force());
//            newThreeFingerGripperStatusType.setFinger2Force(threeFingerGripperStatus.getFinger2Force());
//            newThreeFingerGripperStatusType.setFinger3Force(threeFingerGripperStatus.getFinger2Force());
//            newThreeFingerGripperStatusType.setFinger1Position(threeFingerGripperStatus.getFinger1Position());
//            newThreeFingerGripperStatusType.setFinger2Position(threeFingerGripperStatus.getFinger2Position());
//            newThreeFingerGripperStatusType.setFinger3Position(threeFingerGripperStatus.getFinger2Position());
//            newStatus = newThreeFingerGripperStatusType;
//        }
//        if (null == newStatus) {
//            throw new IllegalArgumentException("status has unrecognized subtype" + status.getClass());
//        }
//
//        newStatus.setName(status.getName());
//        newStatus.setGripperName(status.getGripperName());
//        final List<ParameterSettingType> optionListOut = newStatus.getGripperOption();
//        final List<ParameterSettingType> optionListIn = status.getGripperOption();
//        copyOptionList(optionListOut, optionListIn);
//        return newStatus;
//    }
//
//    private static void copyOptionList(final List<ParameterSettingType> optionListOut, final List<ParameterSettingType> optionListIn) {
//        optionListOut.clear();
//        List<ParameterSettingType> l = new ArrayList<>();
//        for (ParameterSettingType parmSet : optionListIn) {
//            l.add(copy(parmSet));
//        }
//        optionListOut.addAll(l);
//    }
//
//    /**
//     * Copy or clone a pose status.
//     *
//     * @param status status to be cloned
//     * @return PoseStatusType with same initial values as pose but can be
//     * independently modified.
//     */
//    public static @Nullable
//    PoseStatusType copy(@Nullable PoseStatusType status) {
//        if (null == status) {
//            return null;
//        }
//        PoseStatusType newStatus = new PoseStatusType();
//        newStatus.setName(status.getName());
//        newStatus.setPose(copy(status.getNullablePose()));
//        newStatus.setConfiguration(status.getConfiguration());
//        TwistType twist = copy(status.getTwist());
//        if (twist != null) {
//            newStatus.setTwist(twist);
//        }
//        WrenchType wrench = copy(status.getWrench());
//        if (wrench != null) {
//            newStatus.setWrench(wrench);
//        }
////        newStatus.setConfiguration(status.getConfiguration());
//        return newStatus;
//    }
//
//    /**
//     * Copy or clone a command status.
//     *
//     * @param twist status to be cloned
//     * @return JointStatusesType with same initial values as pose but can be
//     * independently modified.
//     */
//    public static @Nullable
//    TwistType copy(@Nullable TwistType twist) {
//        if (null == twist) {
//            return null;
//        }
//        TwistType newTwist = new TwistType();
//        newTwist.setName(twist.getName());
//        VectorType angularVelocity = copy(twist.getAngularVelocity());
//        if (null != angularVelocity) {
//            newTwist.setAngularVelocity(angularVelocity);
//        }
//        VectorType linearVelocity = copy(twist.getLinearVelocity());
//        if (null != linearVelocity) {
//            newTwist.setLinearVelocity(linearVelocity);
//        }
//        return newTwist;
//    }
//    /**
//     * Copy or clone a command status.
//     *
//     * @param wrench status to be cloned
//     * @return JointStatusesType with same initial values as pose but can be
//     * independently modified.
//     */
//    public static @Nullable
//    WrenchType copy(@Nullable WrenchType wrench) {
//        if (null == wrench) {
//            return null;
//        }
//        WrenchType newWrench = new WrenchType();
//        newWrench.setName(wrench.getName());
//        VectorType force = copy(wrench.getForce());
//        if (null != force) {
//            newWrench.setForce(force);
//        }
//        VectorType moment = copy(wrench.getMoment());
//        if (null != moment) {
//            newWrench.setMoment(moment);
//        }
//        return newWrench;
//    }
//
//    /**
//     * Copy or clone a command status.
//     *
//     * @param status status to be cloned
//     * @return JointStatusesType with same initial values as pose but can be
//     * independently modified.
//     */
//    public static @Nullable
//    JointStatusesType copy(@Nullable JointStatusesType status) {
//        if (null == status) {
//            return null;
//        }
//        JointStatusesType newStatus = new JointStatusesType();
//        newStatus.setName(status.getName());
//        final List<JointStatusType> jl = new ArrayList<>(status.getJointStatus());
//        for (int i = 0; i < jl.size(); i++) {
//            final JointStatusType jointI = jl.get(i);
//            newStatus.getJointStatus().add(copy(jointI));
//        }
//        return newStatus;
//    }
//
//    /**
//     * Copy or clone a command status.
//     *
//     * @param status status to be cloned
//     * @return JointStatusesType with same initial values as pose but can be
//     * independently modified.
//     */
//    public static JointStatusType copy(JointStatusType status) {
//        if (null == status) {
//            throw new IllegalArgumentException("copy(JointStatusType) should not be passed null.");
//        }
//        JointStatusType newStatus = new JointStatusType();
//        newStatus.setName(status.getName());
//        newStatus.setJointNumber(status.getJointNumber());
//        newStatus.setJointPosition(status.getJointPosition());
//        newStatus.setJointVelocity(status.getJointVelocity());
//        newStatus.setJointTorqueOrForce(status.getJointTorqueOrForce());
//        return newStatus;
//    }
//
//    /**
//     * Copy or clone a command status.
//     *
//     * @param status status to be cloned
//     * @return CommandStatusType with same initial values as pose but can be
//     * independently modified.
//     */
//    public static @Nullable
//    CommandStatusType copy(@Nullable CommandStatusType status) {
//        if (null == status) {
//            return null;
//        }
//        CommandStatusType newStatus = new CommandStatusType();
//        newStatus.setName(status.getName());
//        newStatus.setCommandID(status.getCommandID());
//        newStatus.setCommandState(status.getCommandState());
//        newStatus.setStateDescription(status.getStateDescription());
//        newStatus.setStatusID(status.getStatusID());
//        newStatus.setProgramFile(status.getProgramFile());
//        newStatus.setProgramIndex(status.getProgramIndex());
//        newStatus.setProgramLength(status.getProgramLength());
//        newStatus.setOverridePercent(status.getOverridePercent());
//        return newStatus;
//    }
    /**
     * Copy or clone the pose.
     *
     * @param pose pose to be have x axis flipped.
     * @return PoseType with same initial values as pose except X points in the
     * opposite direction.
     */
    public static PoseType flipXAxis(PoseType pose) {
        PoseType newPose = new PoseType();
        newPose.setName(pose.getName());
        PointType pt = copy(getNonNullPoint(pose));
        if (null != pt) {
            newPose.setPoint(pt);
        }
        VectorType newXAxis = new VectorType();
        final BigDecimal MINUS_ONE = BigDecimal.valueOf(-1);
        final VectorType localPoselXAxis = getNonNullXAxis(pose);
        newXAxis.setI(localPoselXAxis.getI() * -1.0);
        newXAxis.setJ(localPoselXAxis.getJ() * -1.0);
        newXAxis.setK(localPoselXAxis.getK() * -1.0);
        newPose.setXAxis(newXAxis);
        VectorType zAxis = CRCLCopier.copy(pose.getZAxis());
        if (null != zAxis) {
            newPose.setZAxis(zAxis);
        }
        return newPose;
    }

    /**
     * Create a Point an initialize X,Y, and Z to zero.
     *
     * @return new zeroed point.
     */
    public static PointType newZeroedPoint() {
        PointType pt = new PointType();
        pt.setX(0.0);
        pt.setY(0.0);
        pt.setZ(0.0);
        return pt;
    }

    /**
     * Create a copy of a program and multiply poses in the program with the
     * given pose.
     *
     * @param pose pose to multiply
     * @param programIn program to be copied and transformed
     *
     * @return a new copy of the program with the poses multiplied.
     */
    public static @Nullable
    CRCLProgramType transformProgram(PoseType pose, CRCLProgramType programIn) {
        return transformProgramWithFilter(pose, programIn, null);
    }

    private static void setCommandId(CRCLCommandType cmd, long id) {
//        assert cmd.getCommandID() <= id :
//                createAssertErrorString(cmd,id);
        cmd.setCommandID(id);
    }

    /**
     * Interface by classes returning whether transformProgramWithFilter will
     * multiply an endPose of a given command. (Previously required to avoid
     * Java 8 Predicate dependency.)
     */
    public static interface PoseFilter extends Predicate<PoseType> {

    }

    /**
     * Create a copy of a program and multiply poses in the program with the
     * given pose.
     *
     * @param pose pose to multiply
     * @param programIn program to be copied and transformed
     * @param filter optional filter to determine which poses are multiplied and
     * which are copied as is.
     *
     * @return a new copy of the program with the poses multiplied.
     */
    public static CRCLProgramType
            transformProgramWithFilter(
                    PoseType pose,
                    CRCLProgramType programIn,
                    @Nullable PoseFilter filter) {
        CRCLProgramType programOut = new CRCLProgramType();
        InitCanonType initCmdOut = new InitCanonType();
        InitCanonType initCmdIn = programIn.getInitCanon();
        long id = 1;
        if (null != initCmdIn) {
            setCommandId(initCmdOut, initCmdIn.getCommandID());
            id = initCmdIn.getCommandID();
            programOut.setInitCanon(initCmdOut);
        }
        for (MiddleCommandType cmd : middleCommands(programIn)) {
            if (cmd instanceof MoveToType) {
                MoveToType moveToCmdIn = (MoveToType) cmd;
                MoveToType moveToCmdOut = new MoveToType();
                setCommandId(moveToCmdOut, moveToCmdIn.getCommandID());
                final PoseType nonNullEndPosition = getNonNullEndPosition(moveToCmdIn);
                if (null != filter && !filter.test(nonNullEndPosition)) {
                    final PoseType endPositionCopy = copy(nonNullEndPosition);
                    if (null != endPositionCopy) {
                        moveToCmdOut.setEndPosition(endPositionCopy);
                    }
                } else {
                    moveToCmdOut.setEndPosition(CRCLPosemath.multiply(pose, nonNullEndPosition));
                }
                moveToCmdOut.setMoveStraight(moveToCmdIn.isMoveStraight());
                middleCommands(programOut).add(moveToCmdOut);
            } else {
                middleCommands(programOut).add(cmd);
            }
            id = Math.max(id, cmd.getCommandID()) + 1;
        }
        EndCanonType endCmdOut = new EndCanonType();
        EndCanonType endCmdIn = programIn.getEndCanon();
        if (null != endCmdIn) {
            setCommandId(endCmdOut, endCmdIn.getCommandID());
        }
        programOut.setEndCanon(endCmdOut);
        return programOut;
    }

    /**
     * Gets the end position from a MoveToType command. (It's primary value is
     * in providing a more informative NullPointerException if the command
     * itself or the end position is null).
     *
     * @param moveToCmdIn command to get end position from
     * @return the commands end position
     */
    public static PoseType getNonNullEndPosition(MoveToType moveToCmdIn) {
        return requireNonNull(
                requireNonNull(moveToCmdIn, "moveToCmdIn")
                        .getEndPosition(),
                "getEndPosition()"
        );
    }

//    public static CRCLProgramType flipXAxis(CRCLProgramType programIn) {
//        CRCLProgramType programOut = new CRCLProgramType();
//        InitCanonType initCmdOut = new InitCanonType();
//        InitCanonType initCmdIn = programIn.getInitCanon();
//        long id = 1;
//        if (null != initCmdIn) {
//            setCommandId(initCmdOut, initCmdIn.getCommandID());
//            id = initCmdIn.getCommandID();
//            programOut.setInitCanon(initCmdOut);
//        }
//
//        for (MiddleCommandType cmd : middleCommands(programIn)) {
//            if (cmd instanceof MoveToType) {
//                MoveToType moveToCmdIn = (MoveToType) cmd;
//                MoveToType moveToCmdOut = new MoveToType();
//                setCommandId(moveToCmdOut, moveToCmdIn.getCommandID());
//                moveToCmdOut.setEndPosition(CRCLPosemath.flipXAxis(getNonNullEndPosition(moveToCmdIn)));
//                moveToCmdOut.setMoveStraight(moveToCmdIn.isMoveStraight());
//                middleCommands(programOut).add(moveToCmdOut);
//            } else {
//                middleCommands(programOut).add(cmd);
//            }
//            id = Math.max(id, cmd.getCommandID()) + 1;
//        }
//        EndCanonType endCmdOut = new EndCanonType();
//        EndCanonType endCmdIn = programIn.getEndCanon();
//        if (null != endCmdIn) {
//            setCommandId(endCmdOut, endCmdIn.getCommandID());
//        }
//        programOut.setEndCanon(endCmdOut);
//        return programOut;
//    }
    /**
     * Compute a transform such that two points on a rigid body taken in one
     * coordinated system can be tranformed into corresponding two points of the
     * same rigid body on another coordinate system. In order to require only 2
     * points it is assumed that the Z axis is the same in both coordinate
     * systems.
     *
     * In order to produce reasonable outputs the two points need to be the same
     * distance apart in both coordinate systems both along the Z axis and
     * within the XY plane.
     *
     * @param a1 Point 1 on the rigid body in the A coordinate system.
     * @param a2 Point 2 on the rigid body in the A coordinate system.
     * @param b1 Point 1 on the rigid body in the B coordinate system.
     * @param b2 Point 2 on the rigid body in the B coordinate system.
     * @return Pose such that b1 = multiply(pose,a1) and b2 = multiply(pose,a2)
     * @throws crcl.utils.CRCLException if the two points are identical.
     */
    public static PoseType compute2DTransform(PointType a1,
            PointType a2,
            PointType b1,
            PointType b2) throws CRCLException {
        try {
            return toPose(
                    compute2DPmTransform(
                            toPmCartesian(a1),
                            toPmCartesian(a2),
                            toPmCartesian(b1),
                            toPmCartesian(b2))
            );
        } catch (PmException pmException) {
            throw new CRCLException(pmException);
        }
    }

    /**
     * Compute a transform such that two points on a rigid body taken in one
     * coordinated system can be tranformed into corresponding two points of the
     * same rigid body on another coordinate system. In order to require only 2
     * points it is assumed that the Z axis is the same in both coordinate
     * systems.
     *
     * In order to produce reasonable outputs the two points need to be the same
     * distance apart in both coordinate systems both along the Z axis and
     * within the XY plane.
     *
     *
     * Code was tranformed from C code sent from Fred Proctor to Will
     * Shackleford in an email on 2/26/2016 titled "Code to compute pose from
     * pairs of points"
     *
     * @param a1 Point 1 on the rigid body in the A coordinate system.
     * @param a2 Point 2 on the rigid body in the A coordinate system.
     * @param b1 Point 1 on the rigid body in the B coordinate system.
     * @param b2 Point 2 on the rigid body in the B coordinate system.
     * @return Pose such that b1 = multiply(pose,a1) and b2 = multiply(pose,a2)
     * @throws crcl.utils.CRCLException if any PoseMath exception occurs.
     */
    public static PmPose compute2DPmTransform(
            PmCartesian a1,
            PmCartesian a2,
            PmCartesian b1,
            PmCartesian b2) throws CRCLException {
        try {
            /* first do the rotation part */
 /* 'tha' is the angle of the line a2-a1 in {A} */
            double tha = atan2(a2.y - a1.y, a2.x - a1.x);
            /* 'thb' is the angle of the line b2-b1 in {B} */
            double thb = atan2(b2.y - b1.y, b2.x - b1.x);
            /* the difference of these is the angle between {A} and {B} */
            double theta = thb - tha;
            /* normalize it */
            while (theta > PI) {
                theta -= 2 * PI;
            }
            while (theta < -PI) {
                theta += 2 * PI;
            }
            /* 'theta' is now the angle from {A} to {B} */
 /* make it the rotation part of 'pout' */
            PmEulerZyx zyxout = new PmEulerZyx();
            PmPose pout = new PmPose();
            zyxout.z = theta;
            zyxout.y = 0;
            zyxout.x = 0;
            Posemath.pmZyxQuatConvert(zyxout, pout.rot);


            /* now do the translation part */
 /* the translation part is the displacement between the centroids */
 /* with 'ac' being the centroid of the 'a' points */
            PmCartesian ac = new PmCartesian();
            ac.x = (a1.x + a2.x) * 0.5;
            ac.y = (a1.y + a2.y) * 0.5;
            ac.z = 0;

            /* and 'bc' being the centroid of the 'b' points */
            PmCartesian bc = new PmCartesian();
            bc.x = (b1.x + b2.x) * 0.5;
            bc.y = (b1.y + b2.y) * 0.5;
            bc.z = 0;

            PmCartesian ac_out = new PmCartesian();

            /* you have to rotate 'ac' into the {B} frame to difference them */
            Posemath.pmQuatCartMult(pout.rot, ac, ac_out);
            Posemath.pmCartCartSub(bc, ac_out, pout.tran);
            pout.tran.z = (b2.z + b1.z - a1.z - a2.z) / 2.0;
            return pout;
        } catch (PmException e) {
            throw new CRCLException(e);
        }
    }

    /**
     * Get the point from a status which may have a null poseStatus or pose
     * field.
     *
     * @param stat status to get point from
     * @return a point or null if the status is null or has a null poseStatus or
     * pose field.
     *
     */
    public static @Nullable
    PointType getNullablePoint(@Nullable CRCLStatusType stat) {
        if (stat != null) {
            PoseType pose = getNullablePose(stat);
            if (pose != null) {
                return pose.getPoint();
            }
        }
        return null;
    }

    /**
     * Get the xaxis from a status which may have a null poseStatus or pose
     * field.
     *
     * @param stat status to get xaxis from
     * @return a xaxis vector or null if the status is null or has a null
     * poseStatus or pose field.
     *
     */
    public static @Nullable
    VectorType getNullableXAxis(@Nullable CRCLStatusType stat) {
        if (stat != null) {
            PoseType pose = getNullablePose(stat);
            if (pose != null) {
                return pose.getXAxis();
            }
        }
        return null;
    }

    /**
     * Get the zaxis from a status which may have a null poseStatus or pose
     * field.
     *
     * @param stat status to get zaxis from
     * @return a zaxis vector or null if the status is null or has a null
     * poseStatus or pose field.
     *
     */
    public static @Nullable
    VectorType getNullableZAxis(@Nullable CRCLStatusType stat) {
        if (stat != null) {
            PoseType pose = getNullablePose(stat);
            if (pose != null) {
                return pose.getZAxis();
            }
        }
        return null;
    }

    public static CRCLStatusType newFullCRCLStatus() {
        CRCLStatusType status = new CRCLStatusType();
        status.setCommandStatus(new CommandStatusType());
        status.setJointStatuses(new JointStatusesType());
        status.setSensorStatuses(new SensorStatusesType());
        status.setSettingsStatus(new SettingsStatusType());
        status.setGuardsStatuses(new GuardsStatusesType());
        CRCLPosemath.setPose(status, CRCLPosemath.identityPose());
        return status;
    }

    /**
     * Set the pose in a status that may have a null poseStatus field. (create a
     * new poseStatus field if necessary.)
     *
     * @param stat status to set pose field
     * @param pose new value of the pose
     */
    public static void setPose(@Nullable CRCLStatusType stat, PoseType pose) {
        if (null != stat) {
            PoseStatusType poseStatus = stat.getPoseStatus();
            if (null != poseStatus) {
                poseStatus.setPose(pose);
            } else {
                PoseStatusType newPoseStatus = new PoseStatusType();
                newPoseStatus.setPose(pose);
                stat.setPoseStatus(newPoseStatus);
            }
        }
    }

    /**
     * Set the point in a status that may have a null poseStatus field. (create
     * a new poseStatus field if necessary.)
     *
     * @param stat status to set pose field
     * @param pt new value of the point
     */
    public static void setPoint(@Nullable CRCLStatusType stat, PointType pt) {
        if (stat != null) {
            PoseType pose = getNullablePose(stat);
            if (null != pose) {
                pose.setPoint(pt);
            } else {
                PoseType newPose = new PoseType();
                newPose.setPoint(pt);
                setPose(stat, newPose);
            }
        }
    }

    /**
     * Set the xaxis in a status that may have a null poseStatus field. (create
     * a new poseStatus field if necessary.)
     *
     * @param stat status to set pose field
     * @param xAxis new value of the xaxis
     */
    public static void setXAxis(@Nullable CRCLStatusType stat, VectorType xAxis) {
        if (stat != null) {
            PoseType pose = getNullablePose(stat);
            if (null != pose) {
                pose.setXAxis(xAxis);
            } else {
                PoseType newPose = new PoseType();
                newPose.setXAxis(xAxis);
                setPose(stat, newPose);
            }
        }
    }

    /**
     * Set the zaxis in a status that may have a null poseStatus field. (create
     * a new poseStatus field if necessary.)
     *
     * @param stat status to set pose field
     * @param zAxis new value of the zaxis
     */
    public static void setZAxis(@Nullable CRCLStatusType stat, VectorType zAxis) {
        if (stat != null) {
            PoseType pose = getNullablePose(stat);
            if (null != pose) {
                pose.setZAxis(zAxis);
            } else {
                PoseType newPose = new PoseType();
                newPose.setZAxis(zAxis);
                setPose(stat, newPose);
            }
        }
    }

    /**
     * Initialize the pose if it is null or has null fields in a given status
     *
     * @param status status object with possibly null pose to be initialized
     */
    public static void initPose(CRCLStatusType status) {
        if (null == CRCLPosemath.getNullablePose(status)) {
            CRCLPosemath.setPose(status, identityPose());
        }
        if (null == CRCLPosemath.getNullablePoint(status)) {
            CRCLPosemath.setPoint(status, new PointType());
        }
        if (null == CRCLPosemath.getNullableXAxis(status)) {
            VectorType xAxis = new VectorType();
            xAxis.setI(1.0);
            xAxis.setJ(0.0);
            xAxis.setK(0.0);
            CRCLPosemath.setXAxis(status, xAxis);
        }
        if (null == CRCLPosemath.getNullableZAxis(status)) {
            VectorType zAxis = new VectorType();
            zAxis.setI(0.0);
            zAxis.setJ(0.0);
            zAxis.setK(1.0);
            CRCLPosemath.setZAxis(status, zAxis);
        }
    }

    private static String dataTypeThingToStartString(DataThingType dtt) {
        return "{"
                + ((dtt.getName() != null) ? "name=" + dtt.getName() + "," : "");
    }

    /**
     * Create a string for logging/debug from a point.
     *
     * @param pt to be converted so a string representation.
     * @return string representation of point
     */
    public static String toString(@Nullable PointType pt) {
        if (null == pt) {
            return "null";
        }
        return dataTypeThingToStartString(pt)
                + "x=" + Objects.toString(pt.getX()) + ","
                + "y=" + Objects.toString(pt.getY()) + ","
                + "z=" + Objects.toString(pt.getZ())
                + "}";
    }

    /**
     * Create a string for logging/debug from a vector.
     *
     * @param v vector to be converted so a string representation.
     * @return string representation of vector
     */
    public static String toString(@Nullable VectorType v) {
        if (null == v) {
            return "null";
        }
        return dataTypeThingToStartString(v)
                + "i=" + Objects.toString(v.getI()) + ","
                + "j=" + Objects.toString(v.getJ()) + ","
                + "k=" + Objects.toString(v.getK())
                + "}";
    }

    /**
     * Create a string for logging/debug from a pose.
     *
     * @param pose to be converted so a string representation.
     * @return string representation of pose
     */
    public static String toString(PoseType pose) {
        if (null == pose) {
            return "null";
        }
        return dataTypeThingToStartString(pose)
                + "pt=" + toString(pose.getPoint()) + ","
                + "xAxis=" + toString(pose.getXAxis()) + ","
                + "zAxis=" + toString(pose.getZAxis())
                + "}";
    }

    /**
     * Create a string for logging/debug from a pose tolerance.
     *
     * @param posetol to be converted so a string representation.
     * @return string representation of pose tolerance
     */
    public static String toString(PoseToleranceType posetol) {
        if (null == posetol) {
            return "null";
        }
        return dataTypeThingToStartString(posetol)
                + "XPointTolerance=" + Objects.toString(posetol.getXPointTolerance()) + ","
                + "YPointTolerance=" + Objects.toString(posetol.getYPointTolerance()) + ","
                + "ZPointTolerance=" + Objects.toString(posetol.getZPointTolerance()) + ","
                + "XAxisTolerance=" + Objects.toString(posetol.getXAxisTolerance()) + ","
                + "ZAxisTolerance=" + Objects.toString(posetol.getZAxisTolerance()) + ","
                + "}";
    }

    /**
     * Convert crcl.PointType to rcs.posemath.PmCartesian
     *
     * @param pt Point to be converted
     * @return PmCartesian equivalent
     */
    public static PmCartesian toPmCartesian(final PointType pt) {
        return new PmCartesian(
                pt.getX(),
                pt.getY(),
                pt.getZ());
    }

    /**
     * Create a new pose with zero translation or rotation.
     *
     * @return new identity pose
     */
    public static PoseType identityPose() {
        PoseType newPose = new PoseType();
        PointType pt = new PointType();
        pt.setX(0.0);
        pt.setY(0.0);
        pt.setZ(0.0);
        newPose.setPoint(pt);
        VectorType xAxis = new VectorType();
        xAxis.setI(1.0);
        xAxis.setJ(0.0);
        xAxis.setK(0.0);
        newPose.setXAxis(xAxis);
        VectorType zAxis = new VectorType();
        zAxis.setI(0.0);
        zAxis.setJ(0.0);
        zAxis.setK(1.0);
        newPose.setZAxis(zAxis);
        return newPose;
    }

    /**
     * Create a string representation of the point for logging/debug.
     *
     * @param point to convert
     * @return new string representation
     */
    public static String pointToString(PointType point) {
        try {
            PmCartesian cart = toPmCartesian(point);
            return cart.toString();
        } catch (Exception exception) {
            LOG.log(Level.SEVERE, "", exception);
            return exception.toString();
        }
    }

    /**
     * Create a string representation of the pose for logging/debug.
     *
     * @param pose to convert
     * @return new string representation
     */
    public static String poseToString(PoseType pose) {
        try {
            if (null == pose) {
                return "null";
            }
            PmRotationMatrix rmat = toPmRotationMatrix(pose);
            PmCartesian cart = toPmCartesian(getNonNullPoint(pose));
            return String.format("{%n{%.3g,%.3g,%.3g,%.3g},%n{%.3g,%.3g,%.3g,%.3g},%n{%.3g,%.3g,%.3g,%.3g},%n{%.3g,%.3g,%.3g,%.3g}%n}",
                    rmat.x.x, rmat.x.y, rmat.x.z, cart.x,
                    rmat.y.x, rmat.y.y, rmat.y.z, cart.y,
                    rmat.z.x, rmat.z.y, rmat.z.z, cart.z,
                    0.0, 0.0, 0.0, 1.0);
        } catch (Exception exception) {
            LOG.log(Level.SEVERE, "", exception);
            return exception.toString();
        }
    }

    /**
     * Create a string representation with roll-pitch-yaw rotation of the pose
     * for logging/debug.
     *
     * @param pose to convert
     * @return new string representation
     */
    public static String poseToXyzRpyString(PoseType pose) {
        try {
            PmRpy rpy = toPmRpy(pose);
            PmCartesian cart = toPmCartesian(getNonNullPoint(pose));
            return String.format("X=%.3f,Y=%.3f,Z=%.3f,Roll=%.3f,Pitch=%.3f,Yaw=%.3f",
                    cart.x, cart.y, cart.z,
                    Math.toDegrees(rpy.r), Math.toDegrees(rpy.p), Math.toDegrees(rpy.y));
        } catch (Exception exception) {
            LOG.log(Level.SEVERE, "", exception);
            return exception.toString();
        }
    }

    /**
     * Add two points.
     *
     * @param p1
     * @param p2
     * @return sum of points
     */
    public static PointType add(PointType p1, PointType p2) {
        PointType sum = new PointType();
        sum.setX(p1.getX() + p2.getX());
        sum.setY(p1.getY() + p2.getY());
        sum.setZ(p1.getZ() + p2.getZ());
        return sum;
    }

    /**
     * Subtract one point from another.
     *
     * @param p1
     * @param p2
     * @return p1 - p2
     */
    public static PointType subtract(PointType p1, PointType p2) {
        PointType sum = new PointType();
        sum.setX(p1.getX() - p2.getX());
        sum.setY(p1.getY() - p2.getY());
        sum.setZ(p1.getZ() - p2.getZ());
        return sum;
    }

    /**
     * Get a pose from a possibly null status object that may have a null
     * poseStatus field and if it is not null convert it to a PmPose (from
     * rcslib) object.
     *
     * @param stat status object to get pose from
     * @return new pose as a PmPose
     *
     * @throws CRCLException status contains a null or invalid pose
     */
    public static PmPose toPmPose(CRCLStatusType stat) throws CRCLException {
        if (stat == null) {
            throw new IllegalArgumentException("Can not convert null status to PmPose");
        }
        try {
            PoseType pose = getNullablePose(stat);
            if (pose != null) {
                PointType pt = getNullablePoint(stat);
                if (null != pt) {
                    PmCartesian cart = toPmCartesian(pt);
                    PmRotationMatrix mat = toPmRotationMatrix(pose);
                    if (null != mat) {
                        return new PmPose(cart, new PmQuaternion(mat));
                    }
                }
            }
        } catch (PmException pmException) {
            throw new CRCLException(pmException);
        }
        throw new IllegalArgumentException("stat has null pose components");
    }

    /**
     * Convert a CRCL PoseType to a PmPose from rcslib.
     *
     * @param p CRCL pose to convert
     * @return converted pose
     * @throws CRCLException pose was invalid
     */
    public static PmPose toPmPose(PoseType p) throws CRCLException {
        try {
            final PointType nonNullPoint = getNonNullPoint(p);
            final PmCartesian pointCartesian = toPmCartesian(nonNullPoint);
            final PmRotationMatrix rotationMatrix = toPmRotationMatrix(p);
            final PmQuaternion quat = Posemath.toQuat(rotationMatrix);
            return new PmPose(pointCartesian, quat);
        } catch (PmException pmException) {
            throw new CRCLException(pmException);
        }
    }

    /**
     * Multiply a pose by a point. That is rotate the point based on the pose
     * rotation and add to the point in the pose.
     *
     * @param pose
     * @param pt
     * @return new result point
     * @throws CRCLException pose was invalid
     */
    public static PointType multiply(PoseType pose, PointType pt) throws CRCLException {
        try {
            PmCartesian cartOut = new PmCartesian();
            Posemath.pmPoseCartMult(toPmPose(pose), toPmCartesian(pt), cartOut);
            return toPointType(cartOut);
        } catch (PmException pmException) {
            throw new CRCLException(pmException);
        }
    }

    /**
     * Multiply all fields of a vector by a BigDecimal distance.
     *
     * @param dist
     * @param v
     * @return new scaled vector
     */
    public static PointType multiply(final BigDecimal dist, final VectorType v) {
        return multiply(dist.doubleValue(), v);
    }

    /**
     * Multiply all fields of a vector by a distance.
     *
     * @param dist
     * @param v
     * @return new scaled vector
     */
    public static PointType multiply(double dist, VectorType v) {
        PointType out = new PointType();

        out.setX(v.getI() * dist);
        out.setY(v.getJ() * dist);
        out.setZ(v.getK() * dist);
        return out;
    }

    /**
     * Multiply all fields of a point by a distance.
     *
     * @param dist
     * @param p
     * @return new scaled point
     */
    public static PointType multiply(BigDecimal dist, PointType p) {
        return multiply(dist.doubleValue(), p);
    }

    /**
     * Multiply all fields of a point by a distance.
     *
     * @param dist
     * @param p
     * @return new scaled point
     */
    public static PointType multiply(double dist, PointType p) {
        PointType out = new PointType();
        out.setX(p.getX() * dist);
        out.setY(p.getY() * dist);
        out.setZ(p.getZ() * dist);
        return out;
    }

    /**
     * Compute the dot product of two vectors.
     *
     * @param v1
     * @param v2
     * @return dot product
     */
    public static double dot(VectorType v1, VectorType v2) {
        return v1.getI() * v2.getI()
                + v1.getJ() * v2.getJ()
                + v1.getK() * v2.getK();
    }

    /**
     * Compute the dot product of a vector and a point.
     *
     * @param v1
     * @param p2
     * @return dot product
     */
    public static double dot(VectorType v1, PointType p2) {
        return v1.getI() * p2.getX()
                + v1.getJ() * p2.getY()
                + v1.getK() * p2.getZ();
    }

    /**
     * Generate L2 norm of Vector WARNING: This function loses the BigDecimal
     * precision and range in VectorType
     *
     * @param v1 vector to compute norm of
     * @return norm of input vector
     */
    public static double norm(VectorType v1) {
        double i = v1.getI();
        double j = v1.getJ();
        double k = v1.getK();
        return Math.sqrt(i * i + j * j + k * k);
    }

    /**
     * Normalize the vector so its L2 Norm is 1 WARNING: This function uses
     * norm() which loses the BigDecimal precision and range in VectorType
     *
     * @param v vector to compute norm of
     * @return normalized input vector
     * @throws CRCLException if norm(v) less than Double.MIN_VALUE
     */
    public static VectorType normalize(VectorType v) throws CRCLException {
        VectorType vout = new VectorType();
        double normv = norm(v);
        if (normv < Double.MIN_VALUE) {
            throw new CRCLException(new IllegalArgumentException("Can't normalize vector with zero magnitude."));
        }
        double normInv = 1.0 / norm(v);
        vout.setI(v.getI() * normInv);
        vout.setJ(v.getJ() * normInv);
        vout.setK(v.getK() * normInv);
        return vout;
    }

    /**
     * Compute cross product of two Vectors WARNING: The output may not be
     * normalized even if the input vectors are.
     *
     * @param v1 first vector
     * @param v2 second vector
     * @return cross product vector
     */
    public static VectorType cross(VectorType v1, VectorType v2) {
        VectorType vout = new VectorType();
        double i = v1.getJ() * v2.getK() - v1.getK() * v2.getJ();
        double j = v1.getK() * v2.getI() - v1.getI() * v2.getK();
        double k = v1.getI() * v2.getJ() - v1.getJ() * v2.getI();
        vout.setI(i);
        vout.setJ(j);
        vout.setK(k);
        return vout;
    }

    /**
     * Convert a PmPose from rcslib to a CRCL PoseType.
     *
     * @param pose input
     * @return new pose as a CRCL PoseType.
     * @throws PmException pose was invalid
     */
    public static PoseType toPose(PmPose pose) throws PmException {
        PmHomogeneous hom = new PmHomogeneous();
        Posemath.pmPoseHomConvert(pose, hom);
        return toPose(hom.toMatdd());
    }

    /**
     * Convert a Homogeneous Transformation in a 4x4 double array to a CRCL
     * PoseType.
     *
     * rxx rxy rxz tx ryx ryy ryz ty rzx rzy rzz tz 0 0 0 1
     *
     *
     * @param mat input
     * @return new pose
     */
    public static PoseType toPose(double mat[][]) {
        if (null == mat || mat.length != 4
                || mat[0].length != 4
                || mat[1].length != 4
                || mat[2].length != 4
                || mat[3].length != 4) {
            throw new IllegalArgumentException("toPose() matrix should be 4x4");
        }
        PoseType newPose = new PoseType();
        PointType pt = new PointType();
        pt.setX(mat[0][3]);
        pt.setY(mat[1][3]);
        pt.setZ(mat[2][3]);
        newPose.setPoint(pt);
        VectorType xAxis = new VectorType();
        xAxis.setI(mat[0][0]);
        xAxis.setJ(mat[1][0]);
        xAxis.setK(mat[2][0]);
        newPose.setXAxis(xAxis);
        VectorType zAxis = new VectorType();
        zAxis.setI(mat[0][2]);
        zAxis.setJ(mat[1][2]);
        zAxis.setK(mat[2][2]);
        newPose.setZAxis(zAxis);
        return newPose;
    }

    /**
     * Convert a CRCL PoseType to a 4x4 Homogeneous Transformation matrix in a
     * double array
     *
     * @param poseIn
     * @return transformation matrix
     */
    public static double[][] toHomMat(PoseType poseIn) {
        double mat[][] = new double[][]{
            {1.0, 0.0, 0.0, 0.0},
            {0.0, 1.0, 0.0, 0.0},
            {0.0, 0.0, 1.0, 0.0},
            {0.0, 0.0, 0.0, 1.0}
        };
        PointType pt = getNonNullPoint(poseIn);
        mat[0][3] = pt.getX();
        mat[1][3] = pt.getY();
        mat[2][3] = pt.getZ();
        VectorType xAxis = getNonNullXAxis(poseIn);
        mat[0][0] = xAxis.getI();
        mat[0][1] = xAxis.getJ();
        mat[0][2] = xAxis.getK();
        final VectorType zAxis = getNonNullZAxis(poseIn);
        VectorType yAxis = cross(zAxis, xAxis);
        mat[1][0] = yAxis.getI();
        mat[1][1] = yAxis.getJ();
        mat[1][2] = yAxis.getK();
        mat[2][0] = zAxis.getI();
        mat[2][1] = zAxis.getJ();
        mat[2][2] = zAxis.getK();
        return mat;
    }

    /**
     * Compute a pose that reverses both the translation and rotation of the
     * input.
     *
     * @param p input pose
     * @return new inverted pose
     */
    public static PoseType invert(PoseType p) {
        PoseType pOut = new PoseType();
        VectorType xAxisIn = getNonNullXAxis(p);
        VectorType zAxisIn = getNonNullZAxis(p);
        VectorType yAxisIn = cross(zAxisIn, xAxisIn);
        VectorType xAxisOut = new VectorType();
        xAxisOut.setI(xAxisIn.getI());
        xAxisOut.setJ(yAxisIn.getI());
        xAxisOut.setK(zAxisIn.getI());
        pOut.setXAxis(xAxisOut);
        VectorType zAxisOut = new VectorType();
        zAxisOut.setI(xAxisIn.getK());
        zAxisOut.setJ(yAxisIn.getK());
        zAxisOut.setK(zAxisIn.getK());
        pOut.setZAxis(zAxisOut);

        PointType pt = new PointType();
        final PointType pointIn = getNonNullPoint(p);
        pt.setX(dot(xAxisIn, pointIn) * -1.0);
        pt.setY(dot(yAxisIn, pointIn) * -1.0);
        pt.setZ(dot(zAxisIn, pointIn) * -1.0);
        pOut.setPoint(pt);

        return pOut;
    }

    /**
     * Multiply to poses.
     *
     * @param p1
     * @param p2
     * @return new product.
     */
    public static PoseType multiply(PoseType p1, PoseType p2) {
        PoseType poseOut = new PoseType();
        final VectorType xAxis1 = getNonNullXAxis(p1);
        final VectorType zAxis1 = getNonNullZAxis(p1);
        VectorType yAxis1 = cross(zAxis1, xAxis1);
        VectorType xAxisOut = new VectorType();
        VectorType zAxisOut = new VectorType();
        PointType pt2 = getNonNullPoint(p2);
        PointType pt2rot = new PointType();
        pt2rot.setX(xAxis1.getI() * pt2.getX()
                + yAxis1.getI() * pt2.getY()
                + zAxis1.getI() * pt2.getZ()
        );
        pt2rot.setY(xAxis1.getJ() * pt2.getX()
                + yAxis1.getJ() * pt2.getY()
                + zAxis1.getJ() * pt2.getZ()
        );
        pt2rot.setZ(xAxis1.getK() * pt2.getX()
                + yAxis1.getK() * pt2.getY()
                + zAxis1.getK() * pt2.getZ()
        );
        final PointType pt1 = getNonNullPoint(p1);
        PointType pt = add(pt1, pt2rot);
        poseOut.setPoint(pt);
        final VectorType xAxis2 = getNonNullXAxis(p2);
        xAxisOut.setI(xAxis1.getI() * xAxis2.getI()
                + yAxis1.getI() * xAxis2.getJ()
                + zAxis1.getI() * xAxis2.getK()
        );
        xAxisOut.setJ(xAxis1.getJ() * xAxis2.getI()
                + yAxis1.getJ() * xAxis2.getJ()
                + zAxis1.getJ() * xAxis2.getK()
        );
        xAxisOut.setK(xAxis1.getK() * xAxis2.getI()
                + yAxis1.getK() * xAxis2.getJ()
                + zAxis1.getK() * xAxis2.getK()
        );

        poseOut.setXAxis(xAxisOut);
        final VectorType zAxis2 = getNonNullZAxis(p2);
        zAxisOut.setI(xAxis1.getI() * zAxis2.getI()
                + yAxis1.getI() * zAxis2.getJ()
                + zAxis1.getI() * zAxis2.getK()
        );
        zAxisOut.setJ(xAxis1.getJ() * zAxis2.getI()
                + yAxis1.getJ() * zAxis2.getJ()
                + zAxis1.getJ() * zAxis2.getK()
        );
        zAxisOut.setK(xAxis1.getK() * zAxis2.getI()
                + yAxis1.getK() * zAxis2.getJ()
                + zAxis1.getK() * zAxis2.getK()
        );
        poseOut.setZAxis(zAxisOut);
        return poseOut;
    }

    /**
     * Add the pt to the poseIn point or translational component without
     * rotating first by the pose rotation. Copy the rotation to the output
     * pose.
     *
     * @param poseIn
     * @param pt
     * @return new result pose
     */
    @SuppressWarnings("nullness")
    public static PoseType shift(final PoseType poseIn, final PointType pt) {
        PoseType poseOut = new PoseType();
        final PointType ptIn = getNonNullPoint(poseIn);
        PointType sum = add(ptIn, pt);
        poseOut.setPoint(sum);
        poseOut.setXAxis(CRCLCopier.copy(poseIn.getXAxis()));
        poseOut.setZAxis(CRCLCopier.copy(poseIn.getZAxis()));
        return poseOut;
    }

//    public static PoseType pointXAxisZAxisToPose(PointType pt, VectorType x, VectorType z) {
//        PoseType pose = new PoseType();
//        pose.setPoint(pt);
//        pose.setXAxis(x);
//        pose.setZAxis(z);
//        return pose;
//    }
    /**
     * Compute the cartesian distance between two points.
     *
     * @param pt1 first Point
     * @param pt2 second Point
     * @return distance between pt1 and pt2
     */
    public static double diffPoints(PointType pt1, PointType pt2) {
        return toPmCartesian(pt1).distFrom(toPmCartesian(pt2));
    }

    /**
     * Compute the cartesian distane between the translational components of two
     * poses. Rotations are ignored.
     *
     * @param p1 first Pose
     * @param p2 second Pose
     * @return distance between p1 and p2
     */
    public static double diffPosesTran(PoseType p1, PoseType p2) {
        final PointType pt1 = getNonNullPoint(p1);
        final PointType pt2 = getNonNullPoint(p2);
        return diffPoints(pt1, pt2);
    }

    /**
     * Convert the crcl.VectorType to a PmCartesian. crcl.VectorType is
     * generally used as a unit vector for rotation.
     *
     * @param v Vector to convert
     * @return PmCartesian equivalent of v
     */
    public static PmCartesian vectorToPmCartesian(VectorType v) {
        return new PmCartesian(v.getI(),
                v.getJ(),
                v.getK());
    }

    /**
     * Combine a translation and rotation in a PoseType
     *
     * @param tran translational component of pose
     * @param mat rotational component of pose
     * @return new Pose creating from combining inputs or pose_in if not null
     * @throws PmException if rotation vector can not be converted to matrix
     */
    static public PoseType toPoseType(PmCartesian tran, PmRotationMatrix mat) throws PmException {
        return toPoseType(tran, mat, ((PoseType) null));
    }

    /**
     * Combine a translation and rotation in a PoseType
     *
     * @param tran translational component of pose
     * @param mat rotational component of pose
     * @param pose_in optional pose to be set instead of creating new Pose
     * @return new Pose creating from combining inputs or pose_in if not null
     * @throws PmException if rotation vector can not be converted to matrix
     */
    static public PoseType toPoseType(PmCartesian tran, PmRotationMatrix mat, @Nullable PoseType pose_in) throws PmException {
        PoseType pose = pose_in;
        if (pose == null) {
            pose = new PoseType();
        }
        pose.setPoint(toPointType(tran));
        VectorType xVec = new VectorType();
        xVec.setI(mat.x.x);
        xVec.setJ(mat.x.y);
        xVec.setK(mat.x.z);
        pose.setXAxis(xVec);
        VectorType zVec = new VectorType();
        zVec.setI(mat.z.x);
        zVec.setJ(mat.z.y);
        zVec.setK(mat.z.z);
        pose.setZAxis(zVec);
        return pose;
    }

    /**
     * Combine a translation and rotation in a PoseType
     *
     * @param tran translational component of pose
     * @param v rotational component of pose
     * @param pose_in optional pose to be set instead of creating new Pose
     * @return new Pose creating from combining inputs or pose_in if not null
     * @throws PmException if rotation vector can not be converted to matrix
     */
    static public PoseType toPoseType(PmCartesian tran, PmRotationVector v, @Nullable PoseType pose_in) throws PmException {
        PoseType pose = pose_in;
        if (pose == null) {
            pose = new PoseType();
        }
        pose.setPoint(toPointType(tran));
        PmRotationMatrix mat = Posemath.toMat(v);
        VectorType xVec = new VectorType();
        xVec.setI(mat.x.x);
        xVec.setJ(mat.x.y);
        xVec.setK(mat.x.z);
        pose.setXAxis(xVec);
        VectorType zVec = new VectorType();
        zVec.setI(mat.z.x);
        zVec.setJ(mat.z.y);
        zVec.setK(mat.z.z);
        pose.setZAxis(zVec);
        return pose;
    }

    /**
     * Combine an rcslib Posemath translation and rotation(roll-pitch-yaw) in a
     * PoseType
     *
     * @param tran translational component of pose
     * @param v rotational component of pose in roll-pith-yaw format.
     * @param pose_in optional pose to be set instead of creating new Pose
     * @return new Pose creating from combining inputs or pose_in if not null
     * @throws PmException if rotation vector can not be converted to matrix
     */
    static public PoseType toPoseType(PmCartesian tran, PmRpy v, @Nullable PoseType pose_in) throws PmException {
        PoseType pose = pose_in;
        if (pose == null) {
            pose = new PoseType();
        }
        pose.setPoint(toPointType(tran));
        PmRotationMatrix mat = Posemath.toMat(v);
        VectorType xVec = new VectorType();
        xVec.setI(mat.x.x);
        xVec.setJ(mat.x.y);
        xVec.setK(mat.x.z);
        pose.setXAxis(xVec);
        VectorType zVec = new VectorType();
        zVec.setI(mat.z.x);
        zVec.setJ(mat.z.y);
        zVec.setK(mat.z.z);
        pose.setZAxis(zVec);
        return pose;
    }

    /**
     * Combine a translation and rotation in a PoseType
     *
     * @param tran translational component of Pose
     * @param v rotational component of Pose
     * @return new Pose
     * @throws PmException if rotation vector can not be converted to matrix
     */
    static public PoseType toPoseType(PmCartesian tran, PmRotationVector v) throws PmException {
        return toPoseType(tran, v, ((PoseType) null));
    }

    /**
     * Combine a translation and rotation(roll-pitch-yaw) in a PoseType
     *
     * @param tran translational component of Pose
     * @param v rotational component in roll-pitch-yaw format of Pose
     * @return new Pose
     * @throws PmException if rotation vector can not be converted to matrix
     */
    static public PoseType toPoseType(PmCartesian tran, PmRpy v) throws PmException {
        return toPoseType(tran, v, ((PoseType) null));
    }

    /**
     * Extracts only the rotation part of a PoseType and converts it to a
     * PmRotationMatrix
     *
     * @param p Pose to be converted
     * @return Rotation matrix from rotational component of pose
     * @throws PmException if rotation vectors are invalid
     */
    public static PmRotationMatrix toPmRotationMatrix(PoseType p) throws PmException {
        final VectorType localPoseXAxis = getNonNullXAxis(p);
        PmCartesian cx = vectorToPmCartesian(localPoseXAxis);
        final VectorType localPoseZAxis = getNonNullZAxis(p);
        PmCartesian cz = vectorToPmCartesian(localPoseZAxis);
        final VectorType computedYAxis = cross(localPoseZAxis, localPoseXAxis);
        //PmCartesian cy = Posemath.cross(cz, cx);
        PmCartesian cy = vectorToPmCartesian(computedYAxis);
        return new PmRotationMatrix(cx, cy, cz);
    }

    /**
     * Extracts only the rotation part of a PoseType and converts it to a
     * PmRotationMatrix
     *
     * @param p Pose to convert
     * @return Rotation Vector from rotational component of pose.
     * @throws PmException if rotation vectors are invalid
     */
    public static PmRotationVector toPmRotationVector(final PoseType p) throws PmException {
        return Posemath.toRot(toPmRotationMatrix(p));
    }

    /**
     * Extracts only the rotation part of a PoseType and converts it to a
     * roll-pitch-yaw
     *
     * @param p Pose to convert
     * @return Roll-pitch-yaw taken from rotational component of Pose
     * @throws PmException if rotation vectors are invalid
     */
    public static PmRpy toPmRpy(PoseType p) throws PmException {
        return Posemath.toRpy(toPmRotationMatrix(p));
    }

    /**
     * Convenience function that computes the maximum of the absolute value of
     * two arrays. The two arrays must be the same length.
     *
     * @param da first array of doubles
     * @param da2 second array of doubles
     * @param mod optional modulus set to zero or negative to ignore
     * @return maximum difference between corresponding elements of two arrays
     */
    public static double maxDiffDoubleArray(double da[], double da2[], double mod) {
        if (null == da || null == da2 || da.length != da2.length) {
            throw new IllegalArgumentException("maxDiffDoubleArray expencs two double arrays of same size");
        }
        double diff = 0.0;
        for (int i = 0; i < da.length; i++) {
            double diffi = Math.abs(da[i] - da2[i]);
            if (mod > Double.MIN_NORMAL) {
                int modi = (int) Math.ceil(diff / mod);
                diffi = Math.min(diffi, Math.abs(mod * modi - diffi));
                int modj = (int) Math.floor(diff / mod);
                diffi = Math.min(diffi, Math.abs(mod * modj - diffi));
            }
            diff = Math.max(diff, diffi);
        }
        return diff;
    }

    /**
     * Compute the magnitude of a rotation vector between the two poses in
     * radians.
     *
     * @param pose1 first pose to compare
     * @param pose2 second pose to compare
     * @return magnitude of rotation between poses.
     * @throws PmException if either pose has an invalid rotation.
     */
    public static double diffPosesRot(PoseType pose1, PoseType pose2) throws PmException {
        PmRotationMatrix m1 = toPmRotationMatrix(pose1);
        PmRotationMatrix m2 = toPmRotationMatrix(pose2);
        PmRotationVector r = Posemath.toRot(m1.multiply(m2.inv()));
        return r.s;
    }

    private static boolean isFinite(double d) {
        return !Double.isInfinite(d) && !Double.isNaN(d);
    }

    /**
     * Convert a PmCartesian to a crcl.PointType
     *
     * @param c Cartesian point to convert
     * @return Point equivalent of input cartesian
     */
    public static PointType toPointType(PmCartesian c) {
        if (!isFinite(c.x)) {
            throw new IllegalArgumentException("c.x=" + c.x + ", c=" + c);
        }
        if (!isFinite(c.y)) {
            throw new IllegalArgumentException("c.y=" + c.y + ", c=" + c);
        }
        if (!isFinite(c.z)) {
            throw new IllegalArgumentException("c.z=" + c.z + ", c=" + c);
        }
        PointType pt = new PointType();
        pt.setX(c.x);
        pt.setY(c.y);
        pt.setZ(c.z);
        return pt;
    }
    private static final Logger LOG = Logger.getLogger(CRCLPosemath.class.getName());
}
