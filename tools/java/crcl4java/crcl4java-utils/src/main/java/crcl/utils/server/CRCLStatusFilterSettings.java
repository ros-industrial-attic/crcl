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
package crcl.utils.server;

import crcl.base.AngleUnitEnumType;
import crcl.base.CRCLStatusType;
import crcl.base.ConfigureJointReportType;
import crcl.base.ConfigureStatusReportType;
import crcl.base.ForceTorqueSensorStatusType;
import crcl.base.ForceUnitEnumType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.LengthUnitEnumType;
import crcl.base.PointType;
import crcl.base.PoseType;
import crcl.base.SettingsStatusType;
import crcl.base.TorqueUnitEnumType;
import static crcl.utils.CRCLCopier.copy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class CRCLStatusFilterSettings {

    private @MonotonicNonNull
    ConfigureStatusReportType configureStatusReport = null;
    private final Map<Integer, ConfigureJointReportType> configJointsReportMap = new HashMap<>();
    private final UnitsTypeSet clientUserSet = new UnitsTypeSet();
    private @MonotonicNonNull
    UnitsTypeSet serverUserSet = null;
    private @MonotonicNonNull
    UnitsScaleSet serverToClientScaleSet = null;

    public Map<Integer, ConfigureJointReportType> getConfigJointsReportMap() {
        return configJointsReportMap;
    }

    public UnitsTypeSet getClientUserSet() {
        return clientUserSet;
    }

    public @Nullable
    UnitsTypeSet getServerUserSet() {
        return serverUserSet;
    }

    public void setServerUserSet(UnitsTypeSet serverUserSet) {
        this.serverUserSet = serverUserSet;
    }

    public @Nullable
    UnitsScaleSet getServerToClientScaleSet() {
        return serverToClientScaleSet;
    }

    public void setServerToClientScaleSet(UnitsScaleSet serverToClientScaleSet) {
        this.serverToClientScaleSet = serverToClientScaleSet;
    }

    public @Nullable
    ConfigureStatusReportType getConfigureStatusReport() {
        return configureStatusReport;
    }

    public void setConfigureStatusReport(ConfigureStatusReportType configureStatusReport) {
        this.configureStatusReport = configureStatusReport;
    }

    public void clearConfigJointsReportMap() {
        configJointsReportMap.clear();
    }

    public void configureJointReports(Iterable<? extends ConfigureJointReportType> configureJointReport) {
        for (ConfigureJointReportType cjr : configureJointReport) {
            this.configJointsReportMap.put(cjr.getJointNumber(), cjr);
        }
    }

    private static final Comparator<JointStatusType> jointStatusComparator = new Comparator<JointStatusType>() {
        @Override
        public int compare(JointStatusType arg0, JointStatusType arg1) {
            return Integer.compare(arg0.getJointNumber(), arg1.getJointNumber());
        }
    };

    public static final double NEWTON_METER_TO_FOOT_POUND = 0.737562149277266;
    public static final double FOOT_POUND_TO_NEWTON_METER = 1.3558179483314;

    public double convertTorqueToClient(double svrTorque) {
        double scale = getTorqueServerToClientScale();
        return svrTorque * scale;
    }

    public double convertTorqueToServer(double clientTorque) {
        double scale = getTorqueServerToClientScale();
        return clientTorque / scale;
    }

    public double getTorqueServerToClientScale() {
        double scale = 1.0;
        if (null != serverToClientScaleSet) {
            scale = serverToClientScaleSet.getTorqueScale();
        }
        if (null != serverUserSet && null != clientUserSet) {
            TorqueUnitEnumType serverUnit = serverUserSet.getTorqueUnit();
            TorqueUnitEnumType clientUnit = clientUserSet.getTorqueUnit();
            switch (serverUnit) {
                case NEWTON_METER:
                    switch (clientUnit) {
                        case FOOT_POUND:
                            scale *= NEWTON_METER_TO_FOOT_POUND;
                            break;
                    }
                    break;

                case FOOT_POUND:
                    switch (clientUnit) {
                        case NEWTON_METER:
                            scale *= FOOT_POUND_TO_NEWTON_METER;
                            break;
                    }
                    break;
            }
        }
        return scale;
    }

    public double convertLengthToClient(double svrLength) {
        double scale = getLengthServerToClientScale();
        return svrLength * scale;
    }

    public double convertLengthToServer(double clientLength) {
        double scale = getLengthServerToClientScale();
        return clientLength / scale;
    }

    public static final double INCH_TO_METER = 0.0254;

    public double getLengthServerToClientScale() {
        double scale = 1.0;
        if (null != serverToClientScaleSet) {
            scale = serverToClientScaleSet.getLengthScale();
        }
        if (null != serverUserSet && null != clientUserSet) {
            LengthUnitEnumType serverUnit = serverUserSet.getLengthUnit();
            LengthUnitEnumType clientUnit = clientUserSet.getLengthUnit();
            switch (serverUnit) {
                case INCH:
                    switch (clientUnit) {
                        case METER:
                            scale *= INCH_TO_METER;
                            break;

                        case MILLIMETER:
                            scale *= 1000.0 * INCH_TO_METER;
                    }
                    break;

                case METER:
                    switch (clientUnit) {
                        case INCH:
                            scale *= 1 / INCH_TO_METER;
                            break;
                        case MILLIMETER:
                            scale *= 1000.0;
                            break;
                    }
                    break;

                case MILLIMETER:
                    switch (clientUnit) {
                        case INCH:
                            scale *= 0.001 / INCH_TO_METER;
                            break;
                        case METER:
                            scale *= 0.001;
                            break;
                    }
                    break;
            }
        }
        return scale;
    }

    public double convertForceToClient(double svrForce) {
        double scale = getForceServerToClientScale();
        return svrForce * scale;
    }

    public double convertForceToServer(double clientForce) {
        double scale = getForceServerToClientScale();
        return clientForce / scale;
    }

    public static final double NEWTON_TO_OUNCE = 3.596943089595;
    public static final double OUNCE_TO_NEWTON = 0.278013850954;
    public static final double NEWTON_TO_POUND = 0.2248089431;
    public static final double POUND_TO_NEWTON = 4.448221615261;

    public double getForceServerToClientScale() {
        double scale = 1.0;
        if (null != serverToClientScaleSet) {
            scale = serverToClientScaleSet.getForceScale();
        }
        if (null != serverUserSet && null != clientUserSet) {
            ForceUnitEnumType serverUnit = serverUserSet.getForceUnit();
            ForceUnitEnumType clientUnit = clientUserSet.getForceUnit();
            switch (serverUnit) {
                case NEWTON:
                    switch (clientUnit) {
                        case OUNCE:
                            scale *= NEWTON_TO_OUNCE;
                            break;

                        case POUND:
                            scale *= NEWTON_TO_POUND;
                    }
                    break;

                case OUNCE:
                    switch (clientUnit) {
                        case NEWTON:
                            scale *= OUNCE_TO_NEWTON;
                            break;
                        case POUND:
                            scale *= 1 / 16.0;
                            break;
                    }
                    break;

                case POUND:
                    switch (clientUnit) {
                        case NEWTON:
                            scale *= POUND_TO_NEWTON;
                            break;
                        case OUNCE:
                            scale *= 16.0;
                            break;
                    }
                    break;
            }
        }
        return scale;
    }

    public double convertAngleToClient(double svrAngle) {
        double scale = getAngleServerToClientScale();
        return svrAngle * scale;
    }

    public double convertAngleToServer(double clientAngle) {
        double scale = getAngleServerToClientScale();
        return clientAngle / scale;
    }

    public double getAngleServerToClientScale() {
        double scale = 1.0;
        if (null != serverToClientScaleSet) {
            scale = serverToClientScaleSet.getAngleScale();
        }
        if (null != serverUserSet && null != clientUserSet) {
            AngleUnitEnumType serverUnit = serverUserSet.getAngleUnit();
            AngleUnitEnumType clientUnit = clientUserSet.getAngleUnit();
            switch (serverUnit) {
                case DEGREE:
                    switch (clientUnit) {
                        case RADIAN:
                            scale *= Math.toRadians(1.0);
                            break;

                    }
                    break;

                case RADIAN:
                    switch (clientUnit) {
                        case DEGREE:
                            scale *= Math.toDegrees(1.0);
                            break;
                    }
                    break;
            }
        }
        return scale;
    }

    @SuppressWarnings("nullness")
    public CRCLStatusType filterStatus(CRCLStatusType statusIn) {
        final JointStatusesType jointStatusesIn1 = statusIn.getJointStatuses();
        final List<JointStatusType> jointStatusIn1List = (null != jointStatusesIn1) ? jointStatusesIn1.getJointStatus() : null;
        final int jointStatusesIn1ListSize = (null != jointStatusIn1List) ? jointStatusIn1List.size() : -1;
        CRCLStatusType statusOut = copy(statusIn);
        if (null == statusOut) {
            throw new NullPointerException("statusOut");
        }
        if (null == configureStatusReport) {
            configureStatusReport = createDefaultConfigureStatusReportType();
        }
        if (statusOut.getGripperStatus() != null && !configureStatusReport.isReportGripperStatus()) {
            statusOut.setGripperStatus(null);
        }
        final JointStatusesType jointStatusesIn2 = statusIn.getJointStatuses();
        final List<JointStatusType> jointStatusIn2List = (null != jointStatusesIn2) ? jointStatusesIn2.getJointStatus() : null;
        final int jointStatusesIn2ListSize = (null != jointStatusIn2List) ? jointStatusIn2List.size() : -1;
        if (jointStatusesIn2 != jointStatusesIn1) {
            System.out.println("bad copy");
            CRCLStatusType statusOut2 = copy(statusIn);
            throw new RuntimeException("jointStatusesIn2 != jointStatusesIn1");
        }
        if (jointStatusIn2List != jointStatusIn1List) {
            System.out.println("bad copy");
            CRCLStatusType statusOut2 = copy(statusIn);
            throw new RuntimeException("jointStatusIn2List != jointStatusIn1List");
        }
        if (jointStatusesIn2ListSize != jointStatusesIn1ListSize) {
            System.out.println("bad copy");
            CRCLStatusType statusOut2 = copy(statusIn);
            throw new RuntimeException("jointStatusesIn2ListSize != jointStatusesIn1ListSize");
        }
        final JointStatusesType statusOutJointStatuses = statusOut.getJointStatuses();
        if (statusOutJointStatuses != null) {
            List<JointStatusType> jointStatusOutList = statusOutJointStatuses.getJointStatus();
            if (jointStatusOutList == null && jointStatusIn1List != null) {
                System.out.println("bad copy");
                CRCLStatusType statusOut2 = copy(statusIn);
                throw new RuntimeException("joints == null && jointStatusIn1List != null");
            }
            if (null != jointStatusOutList) {
                if (jointStatusOutList.size() != jointStatusesIn1ListSize) {
                    System.out.println("bad copy");
                    CRCLStatusType statusOut2 = copy(statusIn);
                    throw new RuntimeException("joints.size() != jointStatusesIn1ListSize");
                }
            }
            if (!configureStatusReport.isReportJointStatuses()) {
//                throw new RuntimeException("no joints, isReportJointStatuses");
                statusOut.setJointStatuses(null);
            } else if (jointStatusOutList == null) {
                statusOut.setJointStatuses(null);
//                throw new RuntimeException("statusOutJointStatuses.getJointStatus() == null");
            } else if (jointStatusOutList.isEmpty()) {
                statusOut.setJointStatuses(null);
//                throw new RuntimeException("statusOutJointStatuses.getJointStatus().isEmpty()");
            } else if (configJointsReportMap.isEmpty()) {
                statusOut.setJointStatuses(null);
//                throw new RuntimeException("no joints configJointsReportMap.isEmpty()");
            } else {
                List<JointStatusType> jointsCopy = new ArrayList<>();
                for (int i = 0; i < jointStatusOutList.size(); i++) {
                    JointStatusType jst = jointStatusOutList.get(i);
                    final int jointNumber = jst.getJointNumber();
                    ConfigureJointReportType cjr = configJointsReportMap.get(jointNumber);
                    if (cjr != null) {
                        JointStatusType jstCopy = new JointStatusType();
                        jstCopy.setJointNumber(jointNumber);
                        if (cjr.isReportPosition() && null != jst.getJointPosition()) {
                            jstCopy.setJointPosition(jst.getJointPosition());
                        }
                        if (cjr.isReportTorqueOrForce() && null != jst.getJointTorqueOrForce()) {
                            jstCopy.setJointTorqueOrForce(jst.getJointTorqueOrForce());
                        }
                        if (cjr.isReportVelocity() && null != jst.getJointVelocity()) {
                            jstCopy.setJointVelocity(jst.getJointPosition());
                        }
                        jointsCopy.add(jstCopy);
                    }
                }
                Collections.sort(jointsCopy, jointStatusComparator);
                statusOutJointStatuses.getJointStatus().clear();
                statusOutJointStatuses.getJointStatus().addAll(jointsCopy);
            }
        }
        if (statusOut.getPoseStatus() != null) {
            if (!configureStatusReport.isReportPoseStatus()) {
                statusOut.setPoseStatus(null);
            } else {
                PoseType poseOut = statusOut.getPoseStatus().getPose();
                PoseType poseIn = statusIn.getPoseStatus().getPose();
                if (null != poseIn && null != poseOut) {
                    PointType pointIn = poseIn.getPoint();
                    PointType pointOut = poseOut.getPoint();
                    if (null != pointIn && null != pointOut) {
                        pointOut.setX(convertLengthToClient(pointIn.getX()));
                        pointOut.setY(convertLengthToClient(pointIn.getY()));
                        pointOut.setZ(convertLengthToClient(pointIn.getZ()));
                    }
                }
            }
        }
        final SettingsStatusType settingsStatusOut = statusOut.getSettingsStatus();
        if (settingsStatusOut != null) {
            if (!configureStatusReport.isReportSettingsStatus()) {
                statusOut.setSettingsStatus(null);
            } else {
                if (null != clientUserSet) {
                    settingsStatusOut.setAngleUnitName(clientUserSet.getAngleUnit());
                    settingsStatusOut.setLengthUnitName(clientUserSet.getLengthUnit());
                    settingsStatusOut.setTorqueUnitName(clientUserSet.getTorqueUnit());
                    settingsStatusOut.setForceUnitName(clientUserSet.getForceUnit());
                    if (null != settingsStatusOut.getTransSpeedAbsolute()) {
                        settingsStatusOut.getTransSpeedAbsolute().setSetting(convertLengthToClient(statusIn.getSettingsStatus().getTransSpeedAbsolute().getSetting()));
                    }
                    if (null != settingsStatusOut.getRotSpeedAbsolute()) {
                        settingsStatusOut.getRotSpeedAbsolute().setSetting(convertAngleToClient(statusIn.getSettingsStatus().getRotSpeedAbsolute().getSetting()));
                    }
                    if (null != settingsStatusOut.getTransAccelAbsolute()) {
                        settingsStatusOut.getTransAccelAbsolute().setSetting(convertLengthToClient(statusIn.getSettingsStatus().getTransAccelAbsolute().getSetting()));
                    }
                    if (null != settingsStatusOut.getRotAccelAbsolute()) {
                        settingsStatusOut.getRotAccelAbsolute().setSetting(convertAngleToClient(statusIn.getSettingsStatus().getRotAccelAbsolute().getSetting()));
                    }
                }
            }
        }
        if (statusOut.getSensorStatuses() != null) {
            if (!configureStatusReport.isReportSensorsStatus()) {
                statusOut.setSensorStatuses(null);
            } else {
                final List<ForceTorqueSensorStatusType> forceTorqueSensorStatusOutList = statusOut.getSensorStatuses().getForceTorqueSensorStatus();
                final List<ForceTorqueSensorStatusType> forceTorqueSensorStatusInList = statusIn.getSensorStatuses().getForceTorqueSensorStatus();
                for (int i = 0; i < forceTorqueSensorStatusOutList.size(); i++) {
                    ForceTorqueSensorStatusType sensorIn = forceTorqueSensorStatusInList.get(i);
                    ForceTorqueSensorStatusType sensorOut = forceTorqueSensorStatusOutList.get(i);
                    sensorOut.setReadCount(sensorIn.getReadCount());
                    sensorOut.setLastReadTime(sensorIn.getLastReadTime());
                    sensorOut.setFx(convertForceToClient(sensorIn.getFx()));
                    sensorOut.setFy(convertForceToClient(sensorIn.getFy()));
                    sensorOut.setFz(convertForceToClient(sensorIn.getFz()));
                    sensorOut.setTx(convertTorqueToClient(sensorIn.getTx()));
                    sensorOut.setTy(convertTorqueToClient(sensorIn.getTy()));
                    sensorOut.setTz(convertTorqueToClient(sensorIn.getTz()));
                }
            }
        }
        return statusOut;
    }

    private static ConfigureStatusReportType createDefaultConfigureStatusReportType() {
        ConfigureStatusReportType ret = new ConfigureStatusReportType();
        ret.setReportPoseStatus(true);
        ret.setReportJointStatuses(true);
        ret.setReportSettingsStatus(true);
        return ret;
    }

}
