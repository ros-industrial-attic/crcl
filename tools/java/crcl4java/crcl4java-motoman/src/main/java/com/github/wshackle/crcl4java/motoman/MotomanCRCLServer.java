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
package com.github.wshackle.crcl4java.motoman;

import com.github.wshackle.crcl4java.motoman.MotoPlusConnection.MotoPlusConnectionException;
import com.github.wshackle.crcl4java.motoman.MotoPlusConnection.Returner;
import com.github.wshackle.crcl4java.motoman.MotoPlusConnection.Starter;
import com.github.wshackle.crcl4java.motoman.motctrl.COORD_POS;
import com.github.wshackle.crcl4java.motoman.motctrl.CoordTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.JointTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_COORD_TYPE;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_INTP_TYPE;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_SPEED;
import com.github.wshackle.crcl4java.motoman.motctrl.MotCtrlReturnEnum;
import com.github.wshackle.crcl4java.motoman.motofitproxy.MotoFitTorqueSensorFinder;
import com.github.wshackle.crcl4java.motoman.sys1.MP_ALARM_CODE_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_ALARM_STATUS_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_CART_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_MODE_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_PULSE_POS_RSP_DATA;
import crcl.base.ActuateJointType;
import crcl.base.ActuateJointsType;
import crcl.base.AngleUnitEnumType;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLStatusType;
import crcl.base.CloseToolChangerType;
import crcl.base.CommandStateEnumType;
import static crcl.base.CommandStateEnumType.CRCL_DONE;
import static crcl.base.CommandStateEnumType.CRCL_ERROR;
import static crcl.base.CommandStateEnumType.CRCL_READY;
import static crcl.base.CommandStateEnumType.CRCL_WORKING;
import crcl.base.CommandStatusType;
import crcl.base.ConfigureJointReportsType;
import crcl.base.DwellType;
import crcl.base.EndCanonType;
import crcl.base.GetStatusType;
import crcl.base.InitCanonType;
import crcl.base.JointDetailsType;
import crcl.base.JointSpeedAccelType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.MessageType;
import crcl.base.MiddleCommandType;
import crcl.base.MoveToType;
import crcl.base.OpenToolChangerType;
import crcl.base.PoseStatusType;
import crcl.base.RotSpeedAbsoluteType;
import crcl.base.RotSpeedRelativeType;
import crcl.base.RotSpeedType;
import crcl.base.SetEndEffectorType;
import crcl.base.SetRotSpeedType;
import crcl.base.SetTransSpeedType;
import crcl.base.StopMotionType;
import crcl.base.TransSpeedAbsoluteType;
import crcl.base.TransSpeedRelativeType;
import crcl.base.TransSpeedType;
import crcl.utils.CRCLCopier;
import crcl.utils.CRCLPosemath;
import static crcl.utils.CRCLPosemath.pose;

import crcl.utils.CRCLSocket;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rcs.posemath.PmCartesian;
import rcs.posemath.PmException;
import rcs.posemath.PmRpy;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import crcl.utils.ThreadLockedHolder;
import crcl.utils.XFuture;
import crcl.utils.server.CRCLServerClientState;
import crcl.utils.server.CRCLServerSocket;
import crcl.utils.server.CRCLServerSocketEvent;
import crcl.utils.server.CRCLServerSocketEventListener;
import crcl.utils.server.CRCLServerSocketStateGenerator;
import crcl.utils.server.UnitsTypeSet;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import rcs.posemath.PmEulerZyx;
import rcs.posemath.PmRotationMatrix;
import rcs.posemath.PmRotationVector;
import rcs.posemath.Posemath;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class MotomanCRCLServer implements AutoCloseable {

    public static class MotomanClientState extends CRCLServerClientState {

        public MotomanClientState(CRCLSocket cs) {
            super(cs);
        }
        int i;
    }

    public static final CRCLServerSocketStateGenerator<MotomanClientState> MOTOMAN_STATE_GENERATOR
            = MotomanClientState::new;

    private final CRCLServerSocketEventListener<MotomanClientState> crclSocketEventListener
            = this::handleCrclServerSocketEvent;

    private final CRCLServerSocket<MotomanClientState> crclServerSocket;
    private final ThreadLocal<MotoPlusConnection> mpc = new ThreadLocal<>();
    private final ConcurrentLinkedDeque<MotoPlusConnection> allMpcs = new ConcurrentLinkedDeque<>();
    private final MotoPlusConnection triggerStopMpc;
    private final MotoPlusConnection updatePositionOnlyMpc;

    public CRCLServerSocket<MotomanClientState> getCrclServerSocket() {
        return crclServerSocket;
    }

    private final AtomicInteger updatePositionOnlyCount = new AtomicInteger();
    private final AtomicLong updatePositionOnlyTotalTime = new AtomicLong();
    private volatile long maxUpdatePositionOnlyTime = 0;

    public synchronized void updatePositionOnly() {
        try {
            long t0 = System.currentTimeMillis();
            int upoCount = updatePositionOnlyCount.incrementAndGet();
            MP_CART_POS_RSP_DATA cartData = updatePositionOnlyMpc.getCartPos(0);
            crclServerSocket.setX(cartData.x());
            crclServerSocket.setY(cartData.y());
            crclServerSocket.setZ(cartData.z());
            long t1 = System.currentTimeMillis();
            long diff = t1 - t0;
            long totalUpoTime = updatePositionOnlyTotalTime.addAndGet(diff);
            if (diff > maxUpdatePositionOnlyTime) {
                maxUpdatePositionOnlyTime = diff;
            }
        } catch (Exception ex) {
            Logger.getLogger(MotomanCRCLServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static interface IOSupplier<T> {

        public T get() throws IOException;
    }
    private final IOSupplier<MotoPlusConnection> mpcSupplier;

    public MotomanCRCLServer(CRCLServerSocket<MotomanClientState> svrSocket, IOSupplier<MotoPlusConnection> mpConnectioSupplier) throws IOException {
        this.crclServerSocket = svrSocket;
        this.mpcSupplier = mpConnectioSupplier;
        MotoPlusConnection mpcInit = mpcSupplier.get();
        this.mpc.set(mpcInit);
        this.allMpcs.add(mpcInit);
        triggerStopMpc = mpcSupplier.get();
        updatePositionOnlyMpc = mpcSupplier.get();
        this.crclServerSocket.addListener(crclSocketEventListener);
        this.crclServerSocket.setThreadNamePrefix("MotomanCrclServer");
        this.crclServerSocket.setGuardCheckUpdatePositionOnlyRunnable(this::updatePositionOnly);
        this.crclServerSocket.addSensorFinder(new MotoFitTorqueSensorFinder());
        this.startTime = System.currentTimeMillis();
        statusCount.set(0);
        statSkipCount.set(0);
        totalCommandTime.set(0);
        totalStatTime.set(0);
        cmdCount.set(0);
        lastCommandTime = -1;
        lastStatTime = -1;
        maxCommandTime = -1;
        maxStatTime = -1;
        CRCLStatusType localCrclStatus = this.crclStatus.get();
        final CommandStatusType localCommandStatus = new CommandStatusType();
        localCommandStatus.setCommandID(1);
        localCommandStatus.setStatusID(1);
        localCommandStatus.setCommandState(CRCL_READY);
        localCrclStatus.setCommandStatus(localCommandStatus);
        final PoseStatusType localPoseStatus = new PoseStatusType();
        localPoseStatus.setPose(pose(point(0, 0, 0), vector(1, 0, 0), vector(0, 0, 1)));
        localCrclStatus.setPoseStatus(localPoseStatus);
        if (null != crclServerSocket) {
            crclServerSocket.setCommandStateEnum(CRCL_READY);
        }
    }

//    private final AtomicInteger  connectionsCount = new AtomicInteger();
    private final AtomicInteger statusCount = new AtomicInteger();
    private final AtomicInteger statSkipCount = new AtomicInteger();
    private final AtomicInteger cmdCount = new AtomicInteger();
    private final AtomicLong totalCommandTime = new AtomicLong();
    private final AtomicLong totalStatTime = new AtomicLong();

    private volatile long lastCommandTime = -1;
    private volatile long lastStatTime = -1;
    private volatile long maxCommandTime = -1;
    private volatile long maxStatTime = -1;
    private volatile long startTime = -1;

    public MotoPlusConnection getLocalMotoPlusConnection() throws IOException {
        MotoPlusConnection mpcCurrent = mpc.get();
        if (null == mpcCurrent || !mpcCurrent.isConnected()) {
            mpcCurrent = mpcSupplier.get();
            mpc.set(mpcCurrent);
            this.allMpcs.add(mpcCurrent);
        }
        return mpcCurrent;
    }

    public String getPerformanceInfo() {
        long timeSinceStart = (startTime > 0)
                ? (System.currentTimeMillis() - startTime)
                : -1;
//        MotoPlusConnection mpcLocal = getLocalMotoPlusConnection();

        String perfString
                = "timeSinceStart = " + timeSinceStart
                + "\n totalCommandTime=" + totalCommandTime.get()
                + "\n totalStatTime=" + totalStatTime.get()
                + "\n statusCount=" + statusCount.get()
                + "\n statSkipCount=" + statSkipCount.get()
                + "\n cmdCount=" + cmdCount.get()
                + "\n lastCommandTime=" + lastCommandTime
                + "\n lastStatTime=" + lastStatTime
                + "\n maxCommandTime=" + maxCommandTime
                + "\n maxStatTime=" + maxStatTime
                + "\n lastCommand=" + CRCLSocket.commandToSimpleString(lastCommand)
                + "\n lastIncrementTargetCommand=" + CRCLSocket.commandToSimpleString(lastIncrementTargetCommand)
                + "\n maxCommand=" + CRCLSocket.commandToSimpleString(maxCommand)
                + "\n withAlarmsCountInitCmd=" + withAlarmsCountInitCmd.get()
                + "\n withAlarmsCountNotWorking=" + withAlarmsCountNotWorking.get()
                + "\n withAlarmsCountTooLong=" + withAlarmsCountTooLong.get()
                + "\n recheckCoordTargetFailConsecutive=" + recheckCoordTargetFailConsecutive.get()
                + "\n recheckCoordTargetResendNeededCount=" + recheckCoordTargetResendNeededCount.get()
                + "\n recheckCoordTargetResendNotNeededCount=" + recheckCoordTargetResendNotNeededCount.get()
                + "\n maxRecheckCoordTargetFailCount=" + maxRecheckCoordTargetFailCount
                + "\n moveToCount=" + moveToCount.get()
                + "\n moveToSetPowerCount=" + moveToSetPowerCount.get()
                + "\n moveToSetCoordCount=" + moveToSetCoordCount.get()
                + "\n moveToSetGetPosCount=" + moveToSetGetPosCount.get()
                + "\n idDiffCount=" + idDiffCount.get()
                + "\n workingStatCount=" + workingStatCount.get()
                + "\n targetRecieveSuccessCount=" + targetRecieveSuccessCount.get()
                //                + "\n getMaxReadMpcStatusTimeDiffArray=" + Arrays.toString(mpcLocal.getMaxReadMpcStatusTimeDiffArray())
                //                + "\n getMaxReadMpcStatusTimeDiffArrayAfterMove=" + Arrays.toString(mpcLocal.getMaxReadMpcStatusTimeDiffArrayAfterMove())
                + "\n updatePositionOnlyTotalTime=" + updatePositionOnlyTotalTime.get()
                + "\n updatePositionOnlyCount=" + updatePositionOnlyCount.get()
                + "\n maxUpdatePositionOnlyTime=" + maxUpdatePositionOnlyTime
                + "\n stopCount=" + stopCount.get()
                + "\n maxStopTimeNanos=" + maxStopTimeNanos
                + "\n maxStopTimeNanos0=" + maxStopTimeNanos0
                + "\n maxStopTimeNanos1=" + maxStopTimeNanos1
                + "\n maxStopTimeNanos2=" + maxStopTimeNanos2
                + "\n maxStopTimeNanos3=" + maxStopTimeNanos3
                + "\n totalStopTimeNanos=" + totalStopTimeNanos.get()
                + "\n totalStopTimeNanos0=" + totalStopTimeNanos0.get()
                + "\n totalStopTimeNanos1=" + totalStopTimeNanos1.get()
                + "\n totalStopTimeNanos2=" + totalStopTimeNanos2.get()
                + "\n totalStopTimeNanos3=" + totalStopTimeNanos3.get()
                + "\n triggeredStopCount=" + triggeredStopCount.get()
                + "\n maxTriggeredStopTimeNanos=" + maxTriggeredStopTimeNanos
                + "\n maxTriggeredStopTimeNanos0=" + maxTriggeredStopTimeNanos0
                + "\n maxTriggeredStopTimeNanos1=" + maxTriggeredStopTimeNanos1
                + "\n maxTriggeredStopTimeNanos2=" + maxTriggeredStopTimeNanos2
                + "\n maxTriggeredStopTimeNanos3=" + maxTriggeredStopTimeNanos3
                + "\n totalTriggeredStopTimeNanos=" + totalTriggeredStopTimeNanos.get()
                + "\n totalTriggeredStopTimeNanos0=" + totalTriggeredStopTimeNanos0.get()
                + "\n totalTriggeredStopTimeNanos1=" + totalTriggeredStopTimeNanos1.get()
                + "\n totalTriggeredStopTimeNanos2=" + totalTriggeredStopTimeNanos2.get()
                + "\n totalTriggeredStopTimeNanos3=" + totalTriggeredStopTimeNanos3.get();
        return perfString;
    }

    public String getShortPerformanceInfo() {
        long timeSinceStart = (startTime > 0)
                ? (System.currentTimeMillis() - startTime)
                : -1;
        String perfString
                = "" + timeSinceStart
                + " " + totalCommandTime.get()
                + " " + totalStatTime.get()
                + " " + statusCount.get()
                + " " + statSkipCount.get()
                + " " + cmdCount.get()
                + " " + lastCommandTime
                + " " + lastStatTime
                + " " + maxCommandTime
                + " " + maxStatTime;
        return perfString;
    }

//    public MotoPlusConnection getMpc() {
//        if(allMpcs.isEmpty()) {
//            return null;
//        }
//        return getLocalMotoPlusConnection();
//    }
    public boolean mpcConnected() {
        if (allMpcs.isEmpty()) {
            return false;
        }
        MotoPlusConnection mpcLocal;
        try {
            mpcLocal = getLocalMotoPlusConnection();
            return mpcLocal.isConnected();
        } catch (IOException ex) {
            Logger.getLogger(MotomanCRCLServer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Future<?> start() {
        crclServerSocket.setServerSideStatus(crclStatus);
        final CRCLStatusType localCrclStatus = this.crclStatus.get();
        final CommandStateEnumType currentCommandState = localCrclStatus.getCommandStatus().getCommandState();
        crclServerSocket.setUpdateStatusSupplier(() -> {
            boolean withAlarm = checkAlarms();
            return getCrclStatusFuture(true, withAlarm, statCacheTime);
        });
        crclServerSocket.setAutomaticallySendServerSideStatus(true);
        crclServerSocket.setAutomaticallyConvertUnits(true);
        crclServerSocket.setServerUnits(new UnitsTypeSet());
        this.startTime = System.currentTimeMillis();
        statusCount.set(0);
        statSkipCount.set(0);
        cmdCount.set(0);
        totalCommandTime.set(0);
        totalStatTime.set(0);
        lastCommandTime = -1;
        lastStatTime = -1;
        maxCommandTime = -1;
        maxStatTime = -1;
        return crclServerSocket.start();
    }

    @Override
    public void close() throws Exception {
        crclServerSocket.close();
        allMpcClose();
    }

    private void allMpcClose() throws Exception {
        MotoPlusConnection mpcI;
        while (null != (mpcI = allMpcs.poll())) {
            mpcI.close();
        }
        mpc.remove();
    }

    private final ThreadLockedHolder<CRCLStatusType> crclStatus
            = new ThreadLockedHolder<>("MotomanCRCLServer.crclSTatus", CRCLPosemath.newFullCRCLStatus());

    private long last_status_update_time = -1;

    private final int lastJointPos[] = new int[MP_PULSE_POS_RSP_DATA.MAX_PULSE_AXES];

    private final ConcurrentLinkedDeque<Consumer<String>> logListeners = new ConcurrentLinkedDeque<>();

    public void addLogListener(Consumer<String> consumer) {
        logListeners.add(consumer);
    }

    public void removeLogListener(Consumer<String> consumer) {
        logListeners.remove(consumer);
    }

    private void log(String s) {
        for (Consumer<String> consumer : logListeners) {
            consumer.accept(s);
        }
    }

    private void logErr(String s) {
        System.err.println(s);
        log(s);
    }

    private final AtomicInteger recheckCoordTargetFailConsecutive = new AtomicInteger();

    private final AtomicInteger recheckCoordTargetResendNeededCount = new AtomicInteger();
    private final AtomicInteger recheckCoordTargetResendNotNeededCount = new AtomicInteger();
    private volatile int maxRecheckCoordTargetFailCount = 0;

    private double transDiffCartData(MP_CART_POS_RSP_DATA pos1, COORD_POS dst) {
        int diffx = pos1.lx() - dst.x;
        int diffy = pos1.ly() - dst.y;
        int diffz = pos1.lz() - dst.z;
        return Math.sqrt(diffx * diffx + diffy * diffy + diffz * diffz);
    }

//    private void recheckCoordTarget(MP_CART_POS_RSP_DATA pos) throws IOException, MotoPlusConnectionException, PmException {
//        boolean resendNeeded = false;
//        final COORD_POS dst = lastMoveToCoordTarget.getDst();
//        double diff = transDiffCartData(pos, dst);
//        if (diff > 100.0) {
//            int diffx = pos.lx() - dst.x;
//            if (Math.abs(diffx) > 100) {
//                System.err.println("MotomanCRCLServer.recheckCoordTarget: diffx = " + diffx + ",pos=" + pos + ",lastMoveToCoordTarget=" + lastMoveToCoordTarget);
//                resendNeeded = true;
//            }
//            int diffy = pos.ly() - dst.y;
//            if (Math.abs(diffy) > 100) {
//                System.out.println("MotomanCRCLServer.recheckCoordTarget: diffy = " + diffy + ",pos=" + pos + ",lastMoveToCoordTarget=" + lastMoveToCoordTarget);
//                resendNeeded = true;
//            }
//            int diffz = pos.lz() - dst.z;
//            if (Math.abs(diffz) > 100) {
//                System.out.println("MotomanCRCLServer.recheckCoordTarget: diffz = " + diffz + ",pos=" + pos + ",lastMoveToCoordTarget=" + lastMoveToCoordTarget);
//                resendNeeded = true;
//            }
//        }
//        if (resendNeeded) {
//            recheckCoordTargetResendNeededCount.incrementAndGet();
//            int failCount = recheckCoordTargetFailConsecutive.incrementAndGet();
//            if (failCount > maxRecheckCoordTargetFailCount) {
//                maxRecheckCoordTargetFailCount = failCount;
//            }
//            if (failCount > 10) {
//                getCommandStatus().setCommandState(CRCL_ERROR);
//                getCommandStatus().setStateDescription("MotomanCRCLServer.recheckCoordTarget: diff= " + diff + ",failCount=" + failCount + ",pos=" + pos + ",lastMoveToCoordTarget=" + lastMoveToCoordTarget);
//            } else {
//                getCommandStatus().setCommandState(CRCL_WORKING);
//                moveTo((MoveToType) lastCommand);
//            }
//        } else {
//            recheckCoordTargetResendNotNeededCount.incrementAndGet();
//            getCommandStatus().setCommandState(CRCL_DONE);
//            recheckCoordTargetFailConsecutive.set(0);
//        }
//    }
    private int computeJointDiffMax(int lPos1[], int lPos2[]) {
        int max = 0;
        for (int i = 0; i < lPos2.length && i < lPos1.length; i++) {
            int lPos1I = lPos1[i];
            int lPos2I = lPos2[i];
            int diff = lPos1I - lPos2I;
            int absDiff = Math.abs(diff);
            if (absDiff > max) {
                max = absDiff;
            }
        }
        return max;
    }

//    private void recheckJoints(MP_PULSE_POS_RSP_DATA curPulseData, int recvId) throws IOException, MotoPlusConnectionException {
//        System.out.println("recvId = " + recvId);
//        System.out.println("actuateJointsLastJointTarget.getId() = " + actuateJointsLastJointTarget.getId());
//        System.out.printf("%10s\t%10s\t%10s\t%10s\t%10s\t%10s\n",
//                "Axis", "Current", "Destination", "Start", "DiffDest", "DiffStart");
//        boolean resendNeeded = false;
//        for (int i = 0; i < MP_PULSE_POS_RSP_DATA.MAX_PULSE_AXES; i++) {
//            int current = curPulseData.lPos[i];
//            int dst = actuateJointsLastJointTarget.getDst()[i];
//            int start = actuateJointsStartPulseData.lPos[i];
//            int diffdest = current - dst;
//            int diffstart = current - start;
//            System.out.printf("%10d\t%10d\t%10d\t%10d\t%10d\t%10d\n",
//                    i, current, dst, start, diffdest, diffstart);
//            if (Math.abs(diffdest) > 100) {
//                resendNeeded = true;
//            }
//        }
//        if (resendNeeded) {
//            getCommandStatus().setCommandState(CRCL_WORKING);
//            actuateJoints((ActuateJointsType) lastCommand);
//        } else {
//            getCommandStatus().setCommandState(CRCL_DONE);
//        }
//    }
    private final AtomicInteger lastSentTargetId = new AtomicInteger(1);
    private volatile int lastRecvdTargetId = 1;

    public int getLastSentTargetId() {
        return lastSentTargetId.get();
    }

    public int incrementAndGetLastSentTargetId() {
        return lastSentTargetId.incrementAndGet();
    }

    public int getLastRecvdTargetId() {
        return lastRecvdTargetId;
    }

    private final AtomicInteger updatesSinceCommand = new AtomicInteger();

    private volatile MpcStatus lastMpcStatus = null;

    private final AtomicInteger idDiffCount = new AtomicInteger();
    private final AtomicInteger consecutiveIdDiffCount = new AtomicInteger();
    private volatile int maxConsecutiveIdDiffCount = -1;
    private final AtomicInteger workingStatCount = new AtomicInteger();
    private final AtomicInteger consecutiveWorkingStatCount = new AtomicInteger();
    private volatile int maxConsecutiveWorkingStatCount = -1;
    private final AtomicInteger targetRecieveSuccessCount = new AtomicInteger();

    private volatile @Nullable
    CRCLStatusType lastGetCrclStatusCopy = null;

    private volatile long last_status_time_diff = -1;
    private volatile long last_cmd_time_diff = -1;

    public String getStatusCheckInfo() {
        long time = System.currentTimeMillis();
        return "(time-last_status_update_time)=" + (time - last_status_update_time) + "\n"
                + "last_status_time_diff=" + last_status_time_diff + "\n"
                + "last_cmd_time_diff=" + last_cmd_time_diff + "\n"
                + "statusCount=" + statusCount.get() + "\n"
                + "updatesSinceCommand" + updatesSinceCommand.get() + "\n"
                + "workingStatCount" + workingStatCount.get() + "\n"
                + "consecutiveWorkingStatCount" + consecutiveWorkingStatCount.get() + "\n"
                + "maxConsecutiveWorkingStatCount" + maxConsecutiveWorkingStatCount + "\n"
                + "lastCheckMoveCommand" + CRCLSocket.cmdToPrettyString(lastCheckMoveCommand) + "\n"
                + "actuateJointsLastJointTarget=" + actuateJointsLastJointTarget + "\n"
                + "lastMoveToCoordTarget" + lastMoveToCoordTarget + "\n"
                + "lastSentTargetId=" + lastSentTargetId.get() + "\n"
                + "lastRecvdTargetId=" + lastRecvdTargetId + "\n"
                + "lastStatusCheckPrevLastRecvdTargetId=" + lastStatusCheckPrevLastRecvdTargetId + "\n"
                + "lastStatusCheckLastSentId=" + lastStatusCheckLastSentId + "\n"
                + "idDiffCount=" + idDiffCount.get() + "\n"
                + "consecutiveIdDiffCount=" + consecutiveIdDiffCount.get() + "\n"
                + "maxConsecutiveIdDiffCount=" + maxConsecutiveIdDiffCount + "\n"
                + "(time-lastCheckMoveTime)=" + (time - lastCheckMoveTime) + "\n"
                + "lastCheckMoveJointDiffMax=" + lastCheckMoveJointDiffMax + "\n"
                + "lastCheckMoveCartDiff=" + lastCheckMoveCartDiff + "\n"
                + "lastMpcStatus=" + this.lastMpcStatus + "\n\n//end lastMpcStatus\n\n"
                + "lastGetCrclStatusCopy=" + CRCLSocket.statusToPrettyString(lastGetCrclStatusCopy) + "\n\n//end lastGetCrclStatusCopy\n\n";

    }

    private volatile int lastStatusCheckPrevLastRecvdTargetId = -1;
    private volatile int lastStatusCheckLastSentId = -1;

    public XFuture<CRCLStatusType> getCrclStatusFuture(boolean withJoints, boolean withAlarmModeStatus, int minStatusDiffTime) {
        long time = System.currentTimeMillis();
        long status_time_diff = time - last_status_update_time;
        this.last_status_time_diff = status_time_diff;
        long cmd_time_diff = time - last_command_time;
        this.last_cmd_time_diff = cmd_time_diff;
        final CRCLStatusType crclLocalStatus = this.crclStatus.get();
        try {
            crclServerSocket.runUpdateServerSideStatusRunnables(null);
            if (status_time_diff > minStatusDiffTime) {
                statusCount.incrementAndGet();
                long t0 = System.currentTimeMillis();

                updatesSinceCommand.incrementAndGet();

                try {
                    final CommandStatusType commandStatusLocal = crclLocalStatus.getCommandStatus();
                    if (!mpcConnected() || allMpcs.isEmpty()) {
                        setStateDescription(commandStatusLocal, CRCL_ERROR, "Not connected to Robot.");
                        commandStatusLocal.setStatusID(commandStatusLocal.getStatusID() + 1);
                        return XFuture.completedFuture(crclLocalStatus);
                    }
                    final CommandStateEnumType localOrigCommandState = crclServerSocket.getCommandStateEnum();
                    MotoPlusConnection mpcLocal = this.getLocalMotoPlusConnection();
                    final int prevLastRecvdTargetId = getLastRecvdTargetId();
                    final boolean lastErrorWasWrongMode = mpcLocal.isLastErrorWasWrongMode();
                    int lastSentId = getLastSentTargetId();
                    MpcStatus mpcStatus = mpcLocal.readMpcStatus(
                            localOrigCommandState,
                            lastSentId,
                            (int) crclLocalStatus.getCommandStatus().getStatusID(),
                            withJoints,
                            withAlarmModeStatus,
                            prevLastRecvdTargetId);
                    this.lastMpcStatus = mpcStatus;
                    MP_ALARM_STATUS_DATA alarmStatusData = mpcStatus.getAlarmStatusData();
                    if (null != alarmStatusData) {
                        if (localOrigCommandState != CRCL_ERROR) {
                            if (alarmStatusData.sIsAlarm != 0) {
                                MP_ALARM_CODE_DATA alarmCodeData1 = mpcStatus.getAlarmCodeData();
                                setStateDescription(commandStatusLocal, CRCL_ERROR, "alarmCodeData = " + alarmCodeData1);
                            }
                        }
                        alarmCheckTime = System.currentTimeMillis();
                    }
                    final MP_PULSE_POS_RSP_DATA pulseData = mpcStatus.getPulseData();
                    final MP_CART_POS_RSP_DATA pos = mpcStatus.getPos();
                    if (localOrigCommandState == CRCL_WORKING) {
                        workingStatCount.incrementAndGet();
                        int cwsc = consecutiveWorkingStatCount.incrementAndGet();
                        if (maxConsecutiveWorkingStatCount < cwsc) {
                            maxConsecutiveWorkingStatCount = cwsc;
                        }
                        this.lastStatusCheckPrevLastRecvdTargetId = prevLastRecvdTargetId;
                        this.lastStatusCheckLastSentId = lastSentId;
                        if (lastSentId != prevLastRecvdTargetId && lastCommand == lastIncrementTargetCommand) {
                            idDiffCount.incrementAndGet();
                            int cidc = consecutiveIdDiffCount.incrementAndGet();
                            if (maxConsecutiveIdDiffCount < cidc) {
                                maxConsecutiveIdDiffCount = cidc;
                            }
//                        System.out.println("lastSentTargetId = " + lastSentTargetId);
//                        System.out.println("mpcLocal.getLastRecvdTargetId () = " + mpcLocal.getLastRecvdTargetId ());
                            MotCtrlReturnEnum motTargetReceiveRet = mpcStatus.getMotTargetReceiveRet();
                            int recvId = mpcStatus.getRecvId();
                            if (null == motTargetReceiveRet) {
                                recheckMoveCommandDone(pulseData, recvId, lastSentId, pos);
                            } else {
                                switch (motTargetReceiveRet) {
                                    case SUCCESS:
                                        targetRecieveSuccessCount.incrementAndGet();
                                        if (recvId != 0) {
                                            if (debug) {
                                                System.out.println("recvId = " + recvId);
                                            }
                                        }
                                        if (lastSentId == recvId) {
                                            recheckMoveCommandDone(pulseData, recvId, lastSentId, pos);
                                        }
                                        break;
                                    case E_MP_MOT_FAILURE:
                                        recheckMoveCommandDone(pulseData, recvId, lastSentId, pos);
                                        break;
                                    default:
                                        // MotCtrlReturnEnum.E_MP_MOT_FAILURE occurs to frequently for unknown reasons so it is ignored.
                                        System.out.println("recvId = " + recvId);
                                        System.err.println("motTargetReceiveRet = " + motTargetReceiveRet);
                                        System.err.println("cmd_time_diff=" + cmd_time_diff);
                                        System.err.println("updatesSinceCommand = " + updatesSinceCommand.get());
                                        System.err.println("lastCommand=" + lastCommand);
                                        System.err.println("lastIncrementTargetCommand=" + lastIncrementTargetCommand);
                                        setStateDescription(commandStatusLocal, CRCL_ERROR, "motTargetReceiveRet=" + motTargetReceiveRet);
                                        break;
                                }
                            }
                        } else {
                            consecutiveIdDiffCount.set(0);
                        }
                        if (dwelling && System.currentTimeMillis() > dwellEnd) {
                            if (commandStatusLocal.getCommandState() != CRCL_DONE) {
                                commandStatusLocal.setStateDescription("");
                                commandStatusLocal.setCommandState(CRCL_DONE);
                                crclServerSocket.setCommandStateEnum(CRCL_DONE);
                            }
                            dwelling = false;
                        }
                    } else {
                        consecutiveWorkingStatCount.set(0);
                        consecutiveIdDiffCount.set(0);
                    }
                    if (null != pulseData) {
                        System.arraycopy(pulseData.lPos, 0, lastJointPos, 0, lastJointPos.length);
                        if (null == crclLocalStatus.getJointStatuses()) {
                            crclLocalStatus.setJointStatuses(new JointStatusesType());
                        }
                        List<JointStatusType> jsl = crclLocalStatus.getJointStatuses().getJointStatus();
                        for (int i = 0; i < MP_PULSE_POS_RSP_DATA.MAX_PULSE_AXES; i++) {
                            int diff = pulseData.lPos[i] - lastJointPos[i];
                            if (i >= jsl.size()) {
                                JointStatusType js = new JointStatusType();
                                js.setJointNumber(i + 1);
                                js.setJointPosition((double) pulseData.lPos[i]);
                                if (last_status_update_time > 0) {
                                    double vj = ((double) diff) / status_time_diff;
                                    js.setJointVelocity(vj);
//                            System.out.println("i = " + i + "vj = " + vj);
                                }
                                jsl.add(js);
                            } else {
                                JointStatusType js = jsl.get(i);
                                js.setJointNumber(i + 1);
                                js.setJointPosition((double) pulseData.lPos[i]);

                                if (last_status_update_time > 0) {
                                    double vj = ((double) diff) / status_time_diff;
                                    js.setJointVelocity(vj);
//                            System.out.println("i = " + i + ", vj = " + vj);
                                }
                                jsl.set(i, js);
                            }
                            lastJointPos[i] = pulseData.lPos[i];
                        }
                    }
                    final MP_MODE_DATA modeData = mpcStatus.getModeData();
                    if (null != modeData) {
                        boolean wrongMode = (modeData.sRemote == 0);
                        if (wrongMode) {
                            setStateDescription(commandStatusLocal, CRCL_ERROR, "Pendant switch must be set to REMOTE. : current mode = " + modeData.toString());
                        } else if (lastErrorWasWrongMode) {
                            commandStatusLocal.setStateDescription("");
                        }
                    }
                    if (null != pos) {
                        PmCartesian cart = new PmCartesian(pos.x(), pos.y(), pos.z());
                        PmEulerZyx zyx = new PmEulerZyx(Math.toRadians(pos.rz()), Math.toRadians(pos.ry()), Math.toRadians(pos.rx()));
                        PmRotationMatrix mat = Posemath.toMat(zyx);
                        crclLocalStatus.getPoseStatus().setPose(CRCLPosemath.toPoseType(cart, mat, crclLocalStatus.getPoseStatus().getPose()));
                    }
                    last_status_update_time = System.currentTimeMillis();
                    long t1 = System.currentTimeMillis();
                    long statDiffTime = t1 - t0;
                    this.lastStatTime = statDiffTime;
                    this.totalStatTime.addAndGet(statDiffTime);
                    if (this.maxStatTime < statDiffTime) {
                        this.maxStatTime = statDiffTime;
                    }
                } catch (IOException | PmException | MotoPlusConnection.MotoPlusConnectionException ex) {
                    logException(ex);
                    try {
                        allMpcClose();
                    } catch (Exception ex1) {
                        logException(ex1);
                    }
                    setStateDescription(CRCL_ERROR, ex.getMessage());
                }
                incCommandStatusId();
                CRCLStatusType statusCopy = CRCLCopier.copy(crclLocalStatus);
                this.lastGetCrclStatusCopy = statusCopy;
                return XFuture.completedFuture(statusCopy);
            } else {
                statSkipCount.incrementAndGet();
                long statusId = incCommandStatusId();
                if (null == this.lastGetCrclStatusCopy) {
                    this.lastGetCrclStatusCopy = CRCLCopier.copy(crclLocalStatus);
                }
                this.lastGetCrclStatusCopy.getCommandStatus().setStatusID(statusId);
                return XFuture.completedFuture(lastGetCrclStatusCopy);
            }
        } finally {
            crclServerSocket.runUpdateServerSideStatusRunnables(crclLocalStatus);
        }
    }

    private volatile int lastCheckMoveJointDiffMax = -1;
    private volatile double lastCheckMoveCartDiff = -1;
    private volatile long lastCheckMoveTime = -1;

    public long getLastCheckMoveTime() {
        return lastCheckMoveTime;
    }

    public int getLastCheckMoveJointDiffMax() {
        return lastCheckMoveJointDiffMax;
    }

    public double getLastCheckMoveCartDiff() {
        return lastCheckMoveCartDiff;
    }
    private volatile CRCLCommandType lastCheckMoveCommand = null;

    private void recheckMoveCommandDone(final MP_PULSE_POS_RSP_DATA pulseData, int recvId, int lastSentId, final MP_CART_POS_RSP_DATA pos) throws RuntimeException, MotoPlusConnectionException, PmException, IOException {
        lastCheckMoveCommand = CRCLCopier.copy(lastCommand);
        lastCheckMoveTime = System.currentTimeMillis();
        if (lastCommand instanceof ActuateJointsType) {
            if (null != pulseData && null != actuateJointsLastJointTarget) {
                System.arraycopy(pulseData.lPos, 0, lastJointPos, 0, lastJointPos.length);
                int diffMax = computeJointDiffMax(pulseData.lPos, actuateJointsLastJointTarget.getDst());
                lastCheckMoveJointDiffMax = diffMax;
                if (diffMax < 10) {
                    if (crclServerSocket.getCommandStateEnum() != CRCL_DONE) {
                        crclServerSocket.setStateDescription("");
                        crclServerSocket.setCommandStateEnum(CRCL_DONE);
                    }
                }
            } else {
                throw new RuntimeException("lastSendId=" + lastSentId + ",recvId=" + recvId + ",lastCommand=" + lastCommand + ", pulseData=" + pulseData);
            }
        } else if (lastCommand instanceof MoveToType) {
            if (null == lastMoveToCoordTarget) {
                throw new NullPointerException("lastMoveToCoordTarget: " + "lastSendId=" + lastSentId + ",recvId=" + recvId + ",lastCommand=" + lastCommand);
            }
            final COORD_POS dst = lastMoveToCoordTarget.getDst();
            double diff = transDiffCartData(pos, dst);
            lastCheckMoveCartDiff = diff;
            if (diff < 100) {
                if (crclServerSocket.getCommandStateEnum() != CRCL_DONE) {
                    crclServerSocket.setStateDescription("");
                    crclServerSocket.setCommandStateEnum(CRCL_DONE);
                }
            }
        } else {
            throw new RuntimeException("lastSendId=" + lastSentId + ",recvId=" + recvId + ",lastCommand=" + lastCommand);
        }

    }

    private final AtomicLong commandStatusId = new AtomicLong();
    private long incCommandStatusId() {
//        final CommandStatusType localCommandStatus = this.lastGetCrclStatusCopy.getCommandStatus();
//        final long nextID = localCommandStatus.getStatusID() + 1;
//        localCommandStatus.setStatusID(nextID);
        return commandStatusId.incrementAndGet();
    }

    private CommandStateEnumType lastState = CRCL_WORKING;
    private String lastDescription = "";

    private void setStateDescription(CommandStatusType localCommandStatus, CommandStateEnumType state, String description) {
        localCommandStatus.setCommandState(state);
        localCommandStatus.setStateDescription(description);
        if (lastState != state || !Objects.equals(lastDescription, description)) {
            log(state + ":" + description);
            lastState = state;
            lastDescription = description;
        }
        crclServerSocket.setCommandStateEnum(state);
        crclServerSocket.setStateDescription(description);
    }

    private void setStateDescription(CommandStateEnumType state, String description) {
        crclServerSocket.setCommandStateEnum(state);
        crclServerSocket.setStateDescription(description);
    }

    private void logException(final java.lang.Exception ex) {
        Logger.getLogger(MotomanCRCLServer.class.getName()).log(Level.SEVERE, "MotomanCRCLServer.logException", ex);
        log(ex.toString());
    }

    private volatile boolean initialized = false;

    private volatile long maxStopTimeNanos = 0;
    private volatile long maxStopTimeNanos0 = 0;
    private volatile long maxStopTimeNanos1 = 0;
    private volatile long maxStopTimeNanos2 = 0;
    private volatile long maxStopTimeNanos3 = 0;
    private final AtomicLong totalStopTimeNanos = new AtomicLong();
    private final AtomicLong totalStopTimeNanos0 = new AtomicLong();
    private final AtomicLong totalStopTimeNanos1 = new AtomicLong();
    private final AtomicLong totalStopTimeNanos2 = new AtomicLong();
    private final AtomicLong totalStopTimeNanos3 = new AtomicLong();
    private final AtomicInteger stopCount = new AtomicInteger();

    private void stopMotion() throws IOException {
        MotoPlusConnection mpcLocal = getLocalMotoPlusConnection();
        Starter starter = mpcLocal.getStarter();
        long t0 = System.nanoTime();
        starter.startMpMotStop(0);
        long t1 = System.nanoTime();
        starter.startMpMotTargetClear(0xf, 0);
        long t2 = System.nanoTime();
        Returner returner = mpcLocal.getReturner();

        MotCtrlReturnEnum stopRet = returner.getMpMotStandardReturn();// mpc.mpMotStop(0);
        long t3 = System.nanoTime();
        if (debug) {
            System.out.println("stopRet = " + stopRet);
        }
        MotCtrlReturnEnum clearRet = returner.getMpMotStandardReturn();//mpc.mpMotTargetClear(0xf, 0);
        long t4 = System.nanoTime();
        if (debug) {
            System.out.println("clearRet = " + clearRet);
        }
        long diff0 = t1 - t0;
        long diff1 = t2 - t1;
        long diff2 = t3 - t2;
        long diff3 = t4 - t3;
        long diff = t4 - t0;
        if (diff <= 0) {
            return;
        }
        int sc = stopCount.incrementAndGet();
        if (diff0 > maxStopTimeNanos0) {
            maxStopTimeNanos0 = diff0;
        }
        if (diff1 > maxStopTimeNanos1) {
            maxStopTimeNanos1 = diff1;
        }
        if (diff2 > maxStopTimeNanos2) {
            maxStopTimeNanos2 = diff2;
        }
        if (diff3 > maxStopTimeNanos3) {
            maxStopTimeNanos3 = diff3;
        }
        if (diff > maxStopTimeNanos) {
            maxStopTimeNanos = diff;
        }
        long totalT = totalStopTimeNanos.addAndGet(diff);
        long totalT0 = totalStopTimeNanos0.addAndGet(diff0);
        long totalT1 = totalStopTimeNanos1.addAndGet(diff1);
        long totalT2 = totalStopTimeNanos2.addAndGet(diff2);
        long totalT3 = totalStopTimeNanos3.addAndGet(diff3);

    }

    private volatile long maxTriggeredStopTimeNanos = 0;
    private volatile long maxTriggeredStopTimeNanos0 = 0;
    private volatile long maxTriggeredStopTimeNanos1 = 0;
    private volatile long maxTriggeredStopTimeNanos2 = 0;
    private volatile long maxTriggeredStopTimeNanos3 = 0;
    private volatile long maxTriggeredStopTimeNanos4 = 0;
    private final AtomicLong totalTriggeredStopTimeNanos = new AtomicLong();
    private final AtomicLong totalTriggeredStopTimeNanos0 = new AtomicLong();
    private final AtomicLong totalTriggeredStopTimeNanos1 = new AtomicLong();
    private final AtomicLong totalTriggeredStopTimeNanos2 = new AtomicLong();
    private final AtomicLong totalTriggeredStopTimeNanos3 = new AtomicLong();
    private final AtomicLong totalTriggeredStopTimeNanos4 = new AtomicLong();
    private final AtomicInteger triggeredStopCount = new AtomicInteger();

    private void triggeredStopMotion() throws IOException {

        Starter starter = triggerStopMpc.getStarter();
        MP_CART_POS_RSP_DATA cartData0[] = new MP_CART_POS_RSP_DATA[1];
        boolean getCart0 = triggerStopMpc.mpGetCartPos(0, cartData0);
        long t0 = System.nanoTime();
        starter.startMpMotStop(0);
        long t1 = System.nanoTime();
        starter.startMpMotTargetClear(0xf, 0);
        long t2 = System.nanoTime();

        Returner returner = triggerStopMpc.getReturner();

        MotCtrlReturnEnum stopRet = returner.getMpMotStandardReturn();// mpc.mpMotStop(0);
        long t3 = System.nanoTime();

        if (debug) {
            System.out.println("MotomanCRCLServer.triggeredStopMotion stopRet = " + stopRet);
        }
        MotCtrlReturnEnum clearRet = returner.getMpMotStandardReturn();//mpc.mpMotTargetClear(0xf, 0);
        long t4 = System.nanoTime();
        if (debug) {
            System.out.println("MotomanCRCLServer.triggeredStopMotion clearRet = " + clearRet);
        }

        MP_CART_POS_RSP_DATA cartData1[] = new MP_CART_POS_RSP_DATA[1];
        MP_CART_POS_RSP_DATA cartData2[] = new MP_CART_POS_RSP_DATA[1];
        MP_CART_POS_RSP_DATA lastDiff;
        boolean getCart1 = triggerStopMpc.mpGetCartPos(0, cartData1);
        int moveChecksNeeded = 0;
        try {
            boolean moving = true;
            while (moving) {

                Thread.sleep(10);
                cartData2 = new MP_CART_POS_RSP_DATA[1];
                boolean getCart2 = triggerStopMpc.mpGetCartPos(0, cartData2);
                MP_CART_POS_RSP_DATA diff = cartData2[0].diff(cartData1[0]);
                System.out.println("MotomanCRCLServer.triggeredStopMotion diff = " + diff);
                if (diff.lx() < 50 && diff.ly() < 50 && diff.lz() < 50) {
                    lastDiff = diff;
                    moving = false;
                    break;
                }
                cartData1 = cartData2;
                moveChecksNeeded++;
                System.out.println("MotomanCRCLServer.triggeredStopMotion moveChecksNeeded = " + moveChecksNeeded);
            }
        } catch (InterruptedException | IOException ex2) {
            ex2.printStackTrace();
        }
        long t5 = System.nanoTime();

//        MotCtrlReturnEnum stopRet = triggerStopMpc.mpMotStop(0);
//        if (debug) {
//            System.out.println("stopRet = " + stopRet);
//        }
//        MotCtrlReturnEnum clearRet = triggerStopMpc.mpMotTargetClear(0xf, 0);
//        if (debug) {
//            System.out.println("clearRet = " + clearRet);
//        }
        long diff0 = t1 - t0;
        long diff1 = t2 - t1;
        long diff2 = t3 - t2;
        long diff3 = t4 - t3;
        long diff4 = t5 - t4;
        long diff = t5 - t0;
        if (diff <= 0) {
            return;
        }
        int tsc = triggeredStopCount.incrementAndGet();
        if (diff0 > maxTriggeredStopTimeNanos0) {
            maxTriggeredStopTimeNanos0 = diff0;
        }
        if (diff1 > maxTriggeredStopTimeNanos1) {
            maxTriggeredStopTimeNanos1 = diff1;
        }
        if (diff2 > maxTriggeredStopTimeNanos2) {
            maxTriggeredStopTimeNanos2 = diff2;
        }
        if (diff3 > maxTriggeredStopTimeNanos3) {
            maxTriggeredStopTimeNanos3 = diff3;
        }
        if (diff4 > maxTriggeredStopTimeNanos4) {
            maxTriggeredStopTimeNanos4 = diff4;
        }
        if (diff > maxTriggeredStopTimeNanos) {
            maxTriggeredStopTimeNanos = diff;
        }
        long totalT = totalTriggeredStopTimeNanos.addAndGet(diff);
        long totalT0 = totalTriggeredStopTimeNanos0.addAndGet(diff0);
        long totalT1 = totalTriggeredStopTimeNanos1.addAndGet(diff1);
        long totalT2 = totalTriggeredStopTimeNanos2.addAndGet(diff2);
        long totalT3 = totalTriggeredStopTimeNanos3.addAndGet(diff3);
        long totalT4 = totalTriggeredStopTimeNanos3.addAndGet(diff4);
        System.out.println("MotomanCRCLServer.triggeredStopMotion cartData0[0] = " + cartData0[0]);
        System.out.println("MotomanCRCLServer.triggeredStopMotion cartData2[0] = " + cartData2[0]);
        MP_CART_POS_RSP_DATA fullDiff = cartData0[0].diff(cartData2[0]);
        System.out.println("MotomanCRCLServer.triggeredStopMotion diff = " + diff);
        System.out.println("MotomanCRCLServer.triggeredStopMotion diff0 = " + diff0);
        System.out.println("MotomanCRCLServer.triggeredStopMotion diff1 = " + diff1);
        System.out.println("MotomanCRCLServer.triggeredStopMotion diff2 = " + diff2);
        System.out.println("MotomanCRCLServer.triggeredStopMotion diff3 = " + diff3);
        System.out.println("MotomanCRCLServer.triggeredStopMotion diff4 = " + diff4);
        System.out.println("MotomanCRCLServer.triggeredStopMotion fullDiff = " + fullDiff);
        System.out.println("MotomanCRCLServer.triggeredStopMotion moveChecksNeeded = " + moveChecksNeeded);

    }

    private double maxVelocity = 100.0;

//    private LengthUnitEnumType currentLengthUnits = LengthUnitEnumType.MILLIMETER;
//    private AngleUnitEnumType currentAngleUnits = AngleUnitEnumType.DEGREE;
    private final double MM_TO_INCH = 0.0393701;

    private void setTransSpeed(SetTransSpeedType sts, MotomanClientState clientState) throws IOException {
        MP_SPEED spd = new MP_SPEED();
        TransSpeedType ts = sts.getTransSpeed();
        if (ts instanceof TransSpeedAbsoluteType) {
            TransSpeedAbsoluteType tsa = (TransSpeedAbsoluteType) ts;
//            spd.v = (int) clientState.filterSettings.convertLengthToServer(tsa.getSetting() * 10.0);
            spd.v = (int) (tsa.getSetting() * 10.0);
            if (debug) {
                System.out.println("spd = " + spd);
            }
            final MotoPlusConnection mpcLocal = getLocalMotoPlusConnection();
            mpcLocal.mpMotSetSpeed(0, spd);
        } else if (ts instanceof TransSpeedRelativeType) {
//            TransSpeedRelativeType tsr = (TransSpeedRelativeType) ts;
//            spd.v = (int) (tsr.getFraction() * maxVelocity);
//            System.out.println("spd = " + spd);
//            mpc.mpMotSetSpeed(0, spd);
            System.err.println("Setting relative speed not supported.");
        }
    }

    private void setRotSpeed(SetRotSpeedType srs, MotomanClientState clientState) throws IOException {
        MP_SPEED spd = new MP_SPEED();
        RotSpeedType rs = srs.getRotSpeed();
        if (rs instanceof RotSpeedAbsoluteType) {
            RotSpeedAbsoluteType rsa = (RotSpeedAbsoluteType) rs;
            final AngleUnitEnumType angleUnit = clientState.filterSettings.getClientUserSet().getAngleUnit();
            switch (angleUnit) {
                case DEGREE:
                    spd.vr = (int) (rsa.getSetting() * 10.0);
                    break;

                case RADIAN:
                    spd.vr = (int) (Math.toDegrees(rsa.getSetting()) * 10.0);
                    break;
            }
            if (debug) {
                System.out.println("spd = " + spd);
            }
            final MotoPlusConnection mpcLocal = getLocalMotoPlusConnection();
            mpcLocal.mpMotSetSpeed(0, spd);
        } else if (rs instanceof RotSpeedRelativeType) {
//            RotSpeedRelativeType rsr = (RotSpeedRelativeType) rs;
//            spd.vr = (int) (rsr.getFraction() * maxVelocity);
//            mpc.mpMotSetSpeed(1, spd);
            System.err.println("Setting relative speed not supported.");
        }
    }

    private void setEndEffector(SetEndEffectorType see) throws Exception {
        final MotoPlusConnection mpcLocal = getLocalMotoPlusConnection();
        if (see.getSetting() > 0.5) {
            mpcLocal.openGripper();
        } else {
            mpcLocal.closeGripper();
        }
    }

    private void openToolChanger(OpenToolChangerType see) throws Exception {
        final MotoPlusConnection mpcLocal = getLocalMotoPlusConnection();
        mpcLocal.openToolChanger();
    }

    private void closeToolChanger(CloseToolChangerType see) throws Exception {
        final MotoPlusConnection mpcLocal = getLocalMotoPlusConnection();
        mpcLocal.closeToolChanger();
    }

    private boolean debug = false;

    /**
     * Get the value of debug
     *
     * @return the value of debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * Set the value of debug
     *
     * @param debug new value of debug
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    private volatile CoordTarget lastMoveToCoordTarget = null;

    private final AtomicInteger moveToCount = new AtomicInteger();
    private final AtomicInteger moveToSetPowerCount = new AtomicInteger();
    private final AtomicInteger moveToSetCoordCount = new AtomicInteger();
    private final AtomicInteger moveToSetGetPosCount = new AtomicInteger();

    private volatile MiddleCommandType lastIncrementTargetCommand = null;

    private void moveTo(MoveToType cmd) throws IOException, MotoPlusConnection.MotoPlusConnectionException, PmException {
        boolean isStraight = cmd.isMoveStraight();
        final MotoPlusConnection mpcLocal = getLocalMotoPlusConnection();
        final int newTargetId = incrementAndGetLastSentTargetId();
        lastIncrementTargetCommand = cmd;
        CoordTarget tgt = new CoordTarget(isStraight, newTargetId);
        moveToCount.incrementAndGet();
        if (!mpcLocal.checkNeedSetPower(true, 5000)) {
            moveToSetPowerCount.incrementAndGet();
            mpcLocal.mpSetServoPower(true);
            boolean power = mpcLocal.mpGetServoPower();
            if (debug) {
                try {
                    System.out.println("moveTo(" + CRCLSocket.getUtilSocket().commandToSimpleString(cmd) + ")");
                } catch (Exception ex) {
                    logException(ex);
                }
                System.out.println("power = " + power);
            }
            if (power == false) {
                throw new MotoPlusConnection.MotoPlusConnectionException("Failed to set servo power on");
            }
        }
        if (debug) {
            System.out.println("Calling mpMotSetCoord(1, MP_COORD_TYPE.MP_ROBOT_TYPE, 0)");
        }
        if (!mpcLocal.checkNeedMotSetCoord(0, MP_COORD_TYPE.MP_ROBOT_TYPE, 0, 5000)) {
            moveToSetCoordCount.incrementAndGet();
            MotCtrlReturnEnum motSetCoordRet = mpcLocal.mpMotSetCoord(0, MP_COORD_TYPE.MP_ROBOT_TYPE, 0);
            if (debug) {
                System.out.println("motSetCoordRet = " + motSetCoordRet);
            }
        }
        tgt.setId(newTargetId);
        if (isStraight) {
            tgt.setIntp(MP_INTP_TYPE.MP_MOVL_TYPE);
        } else {
            tgt.setIntp(MP_INTP_TYPE.MP_MOVL_TYPE);
        }
        tgt.getDst().x = (int) (cmd.getEndPosition().getPoint().getX() * 1000.0);
        tgt.getDst().y = (int) (cmd.getEndPosition().getPoint().getY() * 1000.0);
        tgt.getDst().z = (int) (cmd.getEndPosition().getPoint().getZ() * 1000.0);
        PmRotationMatrix rotMat = CRCLPosemath.toPmRotationMatrix(cmd.getEndPosition());
        if (debug) {
            System.out.println("rotMat = " + rotMat);
        }
//        PmRpy rpy = new PmRpy();
//        int e = Posemath.pmMatRpyConvert(rotMat, rpy);
//        System.out.println("rpy = " + Math.toDegrees(rpy.r) + ", " + Math.toDegrees(rpy.p) + "," + Math.toDegrees(rpy.y));
        PmRotationVector rv = Posemath.toRot(rotMat);
//        double rotMatDeg = Math.toDegrees(rv.s);
        PmRpy rpy = Posemath.toRpy(rotMat);

//        PmEulerZyx zyx = new PmEulerZyx();
//        e = Posemath.pmRpyZyxConvert(rpy, zyx);
//        System.out.println("e = " + e);
//        System.out.println("zyx = " + Math.toDegrees(zyx.x) + ", " + Math.toDegrees(zyx.y) + "," + Math.toDegrees(zyx.z));
//        PmEulerZyz zyz = new PmEulerZyz();
//        e = Posemath.pmRpyZyzConvert(rpy, zyz);
//        System.out.println("e = " + e);
//        System.out.println("zyzp = " + Math.toDegrees(zyz.z) + ", " + Math.toDegrees(zyz.y) + "," + Math.toDegrees(zyz.zp));
        tgt.getAux().x = (int) (cmd.getEndPosition().getPoint().getX() * 1000.0);
        tgt.getAux().y = (int) (cmd.getEndPosition().getPoint().getY() * 1000.0);
        tgt.getAux().z = (int) (cmd.getEndPosition().getPoint().getZ() * 1000.0);
//        PmRpy rpy = CRCLPosemath.toPmRpy(cmd.getEndPosition());
//        MP_CART_POS_RSP_DATA pos = mpc.getCartPos(0);

        double rx = Math.atan2(cmd.getEndPosition().getZAxis().getK(),
                Math.hypot(cmd.getEndPosition().getZAxis().getI(), cmd.getEndPosition().getZAxis().getJ()));
        PmEulerZyx zyx = new PmEulerZyx();
        Posemath.pmMatZyxConvert(rotMat, zyx);
        double degreesRx = Math.toDegrees(zyx.x);
        double degreesRy = Math.toDegrees(zyx.y);
        double degreesRz = Math.toDegrees(zyx.z);

        if (debug) {
            System.out.println("zyx = " + zyx);
        }
        tgt.getDst().rx = (int) (degreesRx * 10000.0);
        tgt.getDst().ry = (int) (degreesRy * 10000.0);
        tgt.getDst().rz = (int) (degreesRz * 10000.0);
        tgt.getAux().rx = (int) tgt.getDst().rx;
        tgt.getAux().ry = (int) tgt.getDst().ry;
        tgt.getAux().rz = (int) tgt.getDst().rz;

//        System.out.println("tgt = " + tgt);
//        System.out.println("Convert to zyx");
//        PmEulerZyx zyx = new PmEulerZyx();
//        Posemath.pmRpyZyxConvert(rpy, zyx);
//        tgt.getDst().rx = (int) (Math.toDegrees(zyx.x) * 10000.0);
//        tgt.getDst().ry = (int) (Math.toDegrees(zyx.y) * 10000.0);
//        tgt.getDst().rz = (int) (Math.toDegrees(zyx.z) * 10000.0);
//        tgt.getAux().rx = (int) (Math.toDegrees(zyx.x) * 10000.0);
//        tgt.getAux().ry = (int) (Math.toDegrees(zyx.y) * 10000.0);
//        tgt.getAux().rz = (int) (Math.toDegrees(zyx.z) * 10000.0);
//        System.out.println("tgt = " + tgt);
//        tgt.getDst().rx = pos.lPos[3];
//        tgt.getDst().ry = pos.lPos[4];
//        tgt.getDst().rz = pos.lPos[5];
//        tgt.getAux().rx = pos.lPos[3];
//        tgt.getAux().ry = pos.lPos[4];
//        tgt.getAux().rz = pos.lPos[5];
        if (Math.abs(tgt.getDst().rx) > 1799990
                || Math.abs(tgt.getDst().ry) > 1799990
                || Math.abs(tgt.getDst().rz) > 1799990
                || debug) {

            moveToSetGetPosCount.incrementAndGet();
            MP_CART_POS_RSP_DATA pos = mpcLocal.cachedGetCartPos(0, 300);
            if (Math.abs(tgt.getDst().rx) > 1799990
                    && Math.abs(pos.lrx()) > 1799990
                    && pos.lrx() * tgt.getDst().rx < 0) {
                tgt.getDst().rx = (int) pos.lrx();
                tgt.getAux().rx = (int) pos.lrx();
            }
            if (Math.abs(tgt.getDst().ry) > 1799990
                    && Math.abs(pos.lry()) > 1799990
                    && pos.lry() * tgt.getDst().ry < 0) {
                tgt.getDst().ry = (int) pos.lry();
                tgt.getAux().ry = (int) pos.lry();
            }
            if (Math.abs(tgt.getDst().rz) > 1799990
                    && Math.abs(pos.lrz()) > 1799990
                    && pos.lrz() * tgt.getDst().rz < 0) {
                tgt.getDst().rz = (int) pos.lrz();
                tgt.getAux().rz = (int) pos.lrz();
            }
            if (debug) {
                System.out.println("tgt = " + tgt);
                System.out.println("pos = " + pos);
            }
        }
        Starter starter = mpcLocal.getStarter();
        starter.startMpMotTargetCoordSend(1, tgt, MotoPlusConnection.NO_WAIT);
        starter.startMpMotStart(0);
        Returner returner = mpcLocal.getReturner();
        MotCtrlReturnEnum targetCoordSendRet
                = returner.getMpMotStandardReturn();
//                = mpc.mpMotTargetCoordSend(1, tgt, MotoPlusConnection.NO_WAIT);
        if (debug) {
            System.out.println("targetCoordSendRet = " + targetCoordSendRet);
        }
        MotCtrlReturnEnum motStartRet
                = returner.getMpMotStandardReturn();
        if (debug) {
            System.out.println("motStartRet = " + motStartRet);
            System.out.println("lastSentTargetId = " + getLastSentTargetId());
        }
        lastMoveToCoordTarget = tgt;
    }

    private volatile MP_PULSE_POS_RSP_DATA actuateJointsStartPulseData = null;
    private volatile JointTarget actuateJointsLastJointTarget = null;

    private void actuateJoints(ActuateJointsType ajs) throws IOException, MotoPlusConnection.MotoPlusConnectionException {
        boolean debug = true;
        final MotoPlusConnection mpcLocal = getLocalMotoPlusConnection();
        final int newTargetId = incrementAndGetLastSentTargetId();
        lastIncrementTargetCommand = ajs;
        JointTarget tgt = new JointTarget(newTargetId);
        mpcLocal.mpSetServoPower(true);
        boolean power = mpcLocal.mpGetServoPower();
        if (debug) {
            try {
                System.out.println("actuateJoints(" + CRCLSocket.getUtilSocket().commandToSimpleString(ajs) + ")");
            } catch (Exception ex) {
                logException(ex);
            }
            System.out.println("power = " + power);
        }
        tgt.setId(newTargetId);
        tgt.setIntp(MP_INTP_TYPE.MP_MOVJ_TYPE);

        if (debug) {
            System.out.println("Calling mpMotSetCoord(1, MP_COORD_TYPE.MP_ROBOT_TYPE, 0)");
        }
        MotCtrlReturnEnum motSetCoordRet = mpcLocal.mpMotSetCoord(0, MP_COORD_TYPE.MP_PULSE_TYPE, 0);
        if (debug) {
            System.out.println("motSetCoordRet = " + motSetCoordRet);
        }
        MP_PULSE_POS_RSP_DATA pulseData = mpcLocal.getPulsePos(0);
        actuateJointsStartPulseData = pulseData;
        if (debug) {
            System.out.println("pulseData = " + pulseData);
        }
        for (int i = 0; i < MP_PULSE_POS_RSP_DATA.MAX_PULSE_AXES; i++) {
            tgt.getDst()[i] = pulseData.lPos[i];
            tgt.getAux()[i] = pulseData.lPos[i];
        }
        for (ActuateJointType aj : ajs.getActuateJoint()) {
            tgt.getDst()[aj.getJointNumber() - 1] = (int) aj.getJointPosition();
            tgt.getAux()[aj.getJointNumber() - 1] = (int) aj.getJointPosition();
            JointDetailsType jd = aj.getJointDetails();
            if (jd instanceof JointSpeedAccelType) {
                JointSpeedAccelType jas = (JointSpeedAccelType) jd;
                MP_SPEED spd = new MP_SPEED();
                spd.vj = (int) (jas.getJointSpeed() * 100.0);
                mpcLocal.mpMotSetSpeed(0, spd);
            }
        }
        actuateJointsLastJointTarget = tgt;
        if (debug) {
            System.out.println("tgt = " + tgt);
        }
        MotCtrlReturnEnum targetJointSendRet
                = mpcLocal.mpMotTargetJointSend(1, tgt, MotoPlusConnection.NO_WAIT);
        if (targetJointSendRet != MotCtrlReturnEnum.SUCCESS) {
            setStateDescription(CommandStateEnumType.CRCL_ERROR, "targetJointSendRet=" + targetJointSendRet);
            return;
        }
        if (debug) {
            System.out.println("targetJointSendRet = " + targetJointSendRet);
        }

        MotCtrlReturnEnum motStartRet
                = mpcLocal.mpMotStart(0);
        if (motStartRet != MotCtrlReturnEnum.SUCCESS) {
            setStateDescription(CommandStateEnumType.CRCL_ERROR, "motStartRet=" + motStartRet);
            return;
        }
        if (debug) {
            System.out.println("motStartRet = " + motStartRet);
            System.out.println("lastSentTargetId = " + getLastSentTargetId());
        }
    }

    private long dwellEnd = -1;
    private boolean dwelling = false;
    private long initCanonTime = 0;

    private void initCanon() {
        try {
            initialized = false;
            final MotoPlusConnection mpcLocal = getLocalMotoPlusConnection();
            if (!mpcLocal.isConnected()) {
                initCanonTime = 0;
                mpcLocal.reconnect();
                if (!mpcLocal.isConnected()) {
                    setStateDescription(CRCL_ERROR, "Can not connect to robot.");
                }
            }
            final long currentTimeMillis = System.currentTimeMillis();
            final long timeDiff = currentTimeMillis - initCanonTime;

            if (timeDiff > 10000) {
                stopMotion();
                if (!mpcLocal.mpGetServoPower()) {
                    mpcLocal.mpSetServoPower(true);
                    if (!mpcLocal.mpGetServoPower()) {
                        MP_MODE_DATA modeData = mpcLocal.mpGetMode();
                        if (modeData.sRemote == 0) {
                            setStateDescription(CRCL_ERROR, "Pendant switch must be set to REMOTE. : current mode = " + modeData.toString());
                        } else {
                            setStateDescription(CRCL_ERROR, "Can not enable servo power.");
                        }
                        mpcLocal.mpSetServoPower(false);
                        return;
                    }
                }
                initCanonTime = System.currentTimeMillis();
            }
            mpcLocal.mpSetServoPower(false);
            initialized = true;
            setStateDescription(CRCL_DONE, "");
        } catch (IOException | MotoPlusConnectionException ex) {
            if (!mpcConnected()) {
                setStateDescription(CRCL_ERROR, ex.toString());
            }
            logException(ex);
        }
    }

    private volatile CRCLCommandType lastCommand = null;
    private volatile CRCLCommandType maxCommand = null;
    private volatile long last_command_time = -1;

    public String getLastCommandText() {
        if (null == lastCommand) {
            return "null";
        } else {
            return CRCLSocket.getUtilSocket().commandToPrettyString(lastCommand);
        }
    }

    public String getLastCheckMoveCommandText() {
        if (null == lastCheckMoveCommand) {
            return "null";
        } else {
            return CRCLSocket.getUtilSocket().commandToPrettyString(lastCheckMoveCommand);
        }
    }

    public void handleCrclServerSocketEvent(CRCLServerSocketEvent<MotomanClientState> event) {
        try {
            switch (event.getEventType()) {
                case CRCL_COMMAND_RECIEVED:
                    CRCLCommandInstanceType cmdInstance = event.getInstance();
                    if (null != cmdInstance) {
                        CRCLCommandType cmd = cmdInstance.getCRCLCommand();
                        handleNewCommandFromServerSocket(cmd, event);
                    }
                    break;

                case GUARD_LIMIT_REACHED:
                    triggeredStopMotion();
                    if (crclServerSocket.getCommandStateEnum() == CRCL_WORKING) {
                        setStateDescription(CRCL_DONE, "");
                    }
                    crclServerSocket.comleteGuardTrigger();
                    break;

                case EXCEPTION_OCCURRED:
                case SERVER_CLOSED:
                    stopMotion();
                    break;
            }

        } catch (Exception ex) {
            setStateDescription(CRCL_ERROR, ex.getMessage());
            logException(ex);
        }
    }

    private volatile long alarmCheckTime = -1;
    private volatile long alarmDiffMax = 3000;

    private CRCLStatusType errorOnlyStatus(long cmdid, String errorDescription) {
        CommandStatusType commandStatus = new CommandStatusType();
        commandStatus.setCommandID(cmdid);
        commandStatus.setStateDescription(errorDescription);
        commandStatus.setCommandState(CRCL_ERROR);
        crclServerSocket.setCommandStateEnum(CRCL_ERROR);
        CRCLStatusType crclStatus = new CRCLStatusType();
        crclStatus.setCommandStatus(commandStatus);
        return crclStatus;
    }
    private int statCacheTime = 50;

    public int getStatCacheTime() {
        return statCacheTime;
    }

    public void setStatCacheTime(int statCacheTime) {
        this.statCacheTime = statCacheTime;
    }

    private void handleNewCommandFromServerSocket(CRCLCommandType cmd, CRCLServerSocketEvent<MotomanClientState> event) throws Exception {
        final CRCLStatusType localCrclStatus = this.crclStatus.get();
        final CommandStatusType localCommandStatus = localCrclStatus.getCommandStatus();
        if (cmd instanceof GetStatusType) {
            final boolean withJoints = event.getState().filterSettings.getConfigureStatusReport().isReportJointStatuses();
            final CommandStateEnumType currentCommandState = localCommandStatus.getCommandState();
            boolean withAlarm = checkAlarms();
            getCrclStatusFuture(withJoints, withAlarm, statCacheTime)
                    .thenAccept((CRCLStatusType suppliedStatus) -> {
                        try {
                            event.getSource().writeStatus(suppliedStatus);
                        } catch (Exception ex) {
                            logException(ex);
                            if (ex instanceof RuntimeException) {
                                throw (RuntimeException) ex;
                            } else {
                                throw new RuntimeException(ex);
                            }
                        }
                    })
                    .exceptionally((Throwable throawable) -> {
                        try {
                            event.getSource().writeStatus(errorOnlyStatus(cmd.getCommandID(), throawable.getMessage()));
                        } catch (Exception ex) {
                            logException(ex);
                            if (ex instanceof RuntimeException) {
                                throw (RuntimeException) ex;
                            } else {
                                throw new RuntimeException(ex);
                            }
                        }
                        return null;
                    });

//            event.getSource().writeStatus(getCrclStatusFuture());
        } else {
            cmdCount.incrementAndGet();
            long t0 = System.currentTimeMillis();
            lastCommand = cmd;
            updatesSinceCommand.set(0);
            last_command_time = System.currentTimeMillis();
            
            localCommandStatus.setCommandID(cmd.getCommandID());
            if (crclServerSocket.getCommandStateEnum() != CRCL_ERROR) {
                localCommandStatus.setCommandState(CRCL_WORKING);
                crclServerSocket.setCommandStateEnum(CRCL_WORKING);
            }
            if (cmd instanceof InitCanonType) {
                initCanon();
            } else if (!initialized) {
                if (crclServerSocket.getCommandStateEnum() != CRCL_ERROR
                        || localCommandStatus.getStateDescription().length() < 1) {
                    setStateDescription(CRCL_ERROR, "Command received when not initialized. cmd=" + cmd);
                } else {
                    logErr("Command received when not initialized. cmd=" + cmd);
                }
            } else if (cmd instanceof StopMotionType) {
                stopMotion();
                setStateDescription(CRCL_DONE, "");
            } else if (cmd instanceof ActuateJointsType) {
                actuateJoints((ActuateJointsType) cmd);
                setStateDescription(CRCL_WORKING, "");
            } else if (cmd instanceof SetTransSpeedType) {
                setTransSpeed((SetTransSpeedType) cmd, event.getState());
                setStateDescription(CRCL_DONE, "");
            } else if (cmd instanceof SetRotSpeedType) {
                setRotSpeed((SetRotSpeedType) cmd, event.getState());
                setStateDescription(CRCL_DONE, "");
            } else if (cmd instanceof MoveToType) {
                setStateDescription(CRCL_WORKING, "");
                moveTo((MoveToType) cmd);
                setStateDescription(CRCL_WORKING, "");
            } else if (cmd instanceof DwellType) {
                dwellEnd = System.currentTimeMillis() + (long) (((DwellType) cmd).getDwellTime() * 1000.0);
                dwelling = true;
                setStateDescription(CRCL_WORKING, "");
            } else if (cmd instanceof ConfigureJointReportsType) {
                setStateDescription(CRCL_DONE, "");
//            } else if (cmd instanceof SetLengthUnitsType) {
//                setLengthUnits((SetLengthUnitsType) cmd);
//                setStateDescription(CRCL_DONE, "");
//            } else if (cmd instanceof SetAngleUnitsType) {
////                        setAngleUnits((SetAngleUnitsType) cmd);
//                setStateDescription(CRCL_DONE, "");
            } else if (cmd instanceof SetEndEffectorType) {
                setEndEffector((SetEndEffectorType) cmd);
                setStateDescription(CRCL_DONE, "");
            } else if (cmd instanceof OpenToolChangerType) {
                openToolChanger((OpenToolChangerType) cmd);
                setStateDescription(CRCL_DONE, "");
            } else if (cmd instanceof CloseToolChangerType) {
                closeToolChanger((CloseToolChangerType) cmd);
                setStateDescription(CRCL_DONE, "");
            } else if (cmd instanceof EndCanonType) {
                setStateDescription(CRCL_DONE, "");
            } else if (cmd instanceof MessageType) {
                setStateDescription(CRCL_DONE, ((MessageType) cmd).getMessage());
            } else {
                setStateDescription(CRCL_ERROR, cmd.getClass().getName() + " not implemented");
                try {
                    System.err.println("Unrecognized cmd = " + CRCLSocket.getUtilSocket().commandToSimpleString(cmd));
                } catch (Exception ex) {
                    logException(ex);
                }
            }
            long t1 = System.currentTimeMillis();
            long commandTime = t1 - t0;
            this.lastCommandTime = commandTime;
            this.totalCommandTime.addAndGet(commandTime);
            if (this.maxCommandTime < commandTime) {
                this.maxCommandTime = commandTime;
                this.maxCommand = cmd;
            }
            last_command_time = System.currentTimeMillis();
        }
    }

    private AtomicInteger withAlarmsCountInitCmd = new AtomicInteger();
    private AtomicInteger withAlarmsCountNotWorking = new AtomicInteger();
    private AtomicInteger withAlarmsCountTooLong = new AtomicInteger();

    private boolean checkAlarms() {
        final long now = System.currentTimeMillis();
        final long alarmTimeDiff = now - alarmCheckTime;
        final long alarmCmdTimeDiff = now - last_command_time;
        if (crclServerSocket.isCheckingGuards()) {
            return false;
        }
        if (lastCommand instanceof InitCanonType) {
            withAlarmsCountInitCmd.incrementAndGet();
            return true;
        }
        final CRCLStatusType localCrclStatus = this.lastGetCrclStatusCopy;
        if (null == localCrclStatus || null == localCrclStatus.getCommandStatus()) {
            return true;
        }
        final CommandStateEnumType currentCommandState = localCrclStatus.getCommandStatus().getCommandState();
        if (currentCommandState != CRCL_WORKING
                && alarmCmdTimeDiff > alarmDiffMax) {
            withAlarmsCountNotWorking.incrementAndGet();
            return true;
        }
        if (alarmTimeDiff > alarmDiffMax) {
            withAlarmsCountTooLong.incrementAndGet();
            return true;
        }
        return false;
    }

//    public CommandStateEnumType getCommandState() {
//        return getCommandStatus().getCommandState();
//    }
//
//    private CommandStatusType getCommandStatus() {
//        CRCLStatusType loclaCrclStatus = this.crclStatus.get();
//        return loclaCrclStatus.getCommandStatus();
//    }
    public static final String DEFAULT_MOTOMAN_HOST = "192.168.1.33"; //10.0.0.2";
//    public static final String DEFAULT_MOTOMAN_HOST = "localhost";
    public static final int DEFAULT_MOTOMAN_PORT = 12222;

    public static void main(String[] args) throws Exception {
        String motomanHost = DEFAULT_MOTOMAN_HOST;
        int motomanPort = DEFAULT_MOTOMAN_PORT;
        int crclPort = CRCLSocket.DEFAULT_PORT;
        for (int i = 0; i < args.length - 1; i++) {
            switch (args[i]) {
                case "--motomanhost":
                    motomanHost = args[i + 1];
                    break;

                case "--motomanport":
                    motomanPort = Integer.parseInt(args[i + 1]);
                    break;
                case "--crclport":
                    crclPort = Integer.parseInt(args[i + 1]);
                    break;
            }
        }
        final String motomanHostF = motomanHost;
        final int motomanPortF = motomanPort;
        final int crclPortF = crclPort;
        System.out.println("Starting MotomanCrclServer on port " + crclPort + " after connecting to Motoman robot " + motomanHost + " on port " + motomanPort);
        try (MotomanCRCLServer motomanCrclServer = new MotomanCRCLServer(
                new CRCLServerSocket<>(crclPortF, MOTOMAN_STATE_GENERATOR),
                () -> MotoPlusConnection.connectionFromSocket(new Socket(motomanHostF, motomanPortF)))) {
            motomanCrclServer.crclServerSocket.runServer();
        }
    }

}
