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
package crcl.utils.outer.interfaces;

import crcl.base.CRCLStatusType;
import crcl.base.CommandStatusType;
import crcl.base.ForceTorqueSensorStatusType;
import crcl.base.PointType;
import crcl.base.PoseStatusType;
import crcl.base.PoseType;
import crcl.base.SensorStatusesType;
import static crcl.utils.CRCLUtils.getNonNullCommandStatus;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class TimeStampedStatus {

    public final double x;
    public final double y;
    public final double z;
    public final double cmdId;
    public final double absTime;
    public final double relTime;
    public final double timeDiff;
    public final double fx;
    public final double fy;
    public final double fz;
    public final double state;

    public TimeStampedStatus(CRCLStatusType status, long absTime, long relTime, long timediff) {
        final PoseStatusType poseStatus = status.getPoseStatus();
        final PoseType pose = (poseStatus != null) ? poseStatus.getPose() : null;
        final PointType point = (pose != null) ? pose.getPoint() : null;
        final CommandStatusType commandStatus = getNonNullCommandStatus(status);
        final SensorStatusesType sensorStatuses = status.getSensorStatuses();
        final ForceTorqueSensorStatusType forceTorqueStatus
                = (null != sensorStatuses && requireNonNull(sensorStatuses.getForceTorqueSensorStatus()).size() > 0)
                ? requireNonNull(sensorStatuses.getForceTorqueSensorStatus()).get(0) : null;
        x = (point != null) ? point.getX() : 0.0;
        y = (point != null) ? point.getY() : 0.0;
        z = (point != null) ? point.getZ() : 0.0;
        fx = (forceTorqueStatus != null) ? forceTorqueStatus.getFx() : 0.0;
        fy = (forceTorqueStatus != null) ? forceTorqueStatus.getFy() : 0.0;
        fz = (forceTorqueStatus != null) ? forceTorqueStatus.getFz() : 0.0;
        cmdId = (double) ((commandStatus != null) ? commandStatus.getCommandID() : -1.0);
        state = (double) getCommandStateDouble(commandStatus);
        this.absTime = absTime * 1e-3;
        this.relTime = relTime * 1e-3;
        this.timeDiff = timediff *1e-3;
    }

    @SuppressWarnings("nullness")
    private static double getCommandStateDouble(final CommandStatusType commandStatus) {
        return (commandStatus != null) ? commandStatus.getCommandState().ordinal() : -1.0;
    }

}
