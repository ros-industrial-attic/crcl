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
package com.github.wshackle.fanuccrclservermain;

import static com.github.wshackle.fanuc.robotneighborhood.ClassFactory.createFRCRobotNeighborhood;
import com.github.wshackle.fanuc.robotneighborhood.IRNRobot;
import com.github.wshackle.fanuc.robotneighborhood.IRNRobots;
import com.github.wshackle.fanuc.robotneighborhood.IRobotNeighborhood;
import com.github.wshackle.fanuc.robotserver.FRECurPositionConstants;
import com.github.wshackle.fanuc.robotserver.FREExecuteConstants;
import com.github.wshackle.fanuc.robotserver.FREGroupBitMaskConstants;
import com.github.wshackle.fanuc.robotserver.FREProgramTypeConstants;
import com.github.wshackle.fanuc.robotserver.FREStepTypeConstants;
import com.github.wshackle.fanuc.robotserver.FRETaskStatusConstants;
import com.github.wshackle.fanuc.robotserver.FRETypeCodeConstants;
import com.github.wshackle.fanuc.robotserver.IConfig;
import com.github.wshackle.fanuc.robotserver.ICurGroupPosition;
import com.github.wshackle.fanuc.robotserver.ICurPosition;
import com.github.wshackle.fanuc.robotserver.IIndGroupPosition;
import com.github.wshackle.fanuc.robotserver.IIndPosition;
import com.github.wshackle.fanuc.robotserver.IJoint;
import com.github.wshackle.fanuc.robotserver.IProgram;
import com.github.wshackle.fanuc.robotserver.IPrograms;
import com.github.wshackle.fanuc.robotserver.IRegNumeric;
import com.github.wshackle.fanuc.robotserver.IRobot2;
import com.github.wshackle.fanuc.robotserver.ISysGroupPosition;
import com.github.wshackle.fanuc.robotserver.ISysPosition;
import com.github.wshackle.fanuc.robotserver.ITPProgram;
import com.github.wshackle.fanuc.robotserver.ITask;
import com.github.wshackle.fanuc.robotserver.ITasks;
import com.github.wshackle.fanuc.robotserver.IVar;
import com.github.wshackle.fanuc.robotserver.IVars;
import com.github.wshackle.fanuc.robotserver.IXyzWpr;
import com4j.Com4jObject;
import com4j.ComException;
import crcl.base.ActuateJointType;
import crcl.base.ActuateJointsType;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLStatusType;
import crcl.base.CloseToolChangerType;
import crcl.base.CommandStateEnumType;
import crcl.base.CommandStatusType;
import crcl.base.ConfigureJointReportType;
import crcl.base.ConfigureJointReportsType;
import crcl.base.DwellType;
import crcl.base.EndCanonType;

import crcl.base.GetStatusType;
import crcl.base.GripperStatusType;
import crcl.base.GuardsStatusesType;
import crcl.base.InitCanonType;
import crcl.base.JointDetailsType;
import crcl.base.JointLimitType;
import crcl.base.JointSpeedAccelType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.MessageType;
import crcl.base.MoveThroughToType;
import crcl.base.MoveToType;
import crcl.base.OpenToolChangerType;
import crcl.base.ParallelGripperStatusType;
import crcl.base.PointType;
import crcl.base.PoseStatusType;
import crcl.base.PoseToleranceType;
import crcl.base.PoseType;
import crcl.base.RotSpeedAbsoluteType;
import crcl.base.RotSpeedRelativeType;
import crcl.base.RotSpeedType;
import crcl.base.SensorStatusesType;
import crcl.base.SetAngleUnitsType;
import crcl.base.SetEndEffectorType;
import crcl.base.SetEndPoseToleranceType;
import crcl.base.SetForceUnitsType;
import crcl.base.SetLengthUnitsType;
import crcl.base.SetRotSpeedType;
import crcl.base.SetTransSpeedType;
import crcl.base.SettingsStatusType;
import crcl.base.StopMotionType;
import crcl.base.TransSpeedAbsoluteType;
import crcl.base.TransSpeedRelativeType;
import crcl.base.TransSpeedType;
import crcl.utils.CRCLCopier;
import static crcl.utils.CRCLCopier.copy;
import crcl.utils.CRCLException;
import crcl.utils.CRCLPosemath;
import crcl.utils.CRCLSocket;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSlider;
import rcs.posemath.PmCartesian;
import rcs.posemath.PmException;
import rcs.posemath.PmRotationVector;
import rcs.posemath.PmRpy;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import static crcl.utils.CRCLPosemath.point;
import crcl.utils.CRCLUtils;
import crcl.utils.ThreadLockedHolder;
import crcl.utils.XFuture;
import crcl.utils.XFutureVoid;
import crcl.utils.server.CRCLServerClientState;
import crcl.utils.server.CRCLServerSocket;
import crcl.utils.server.CRCLServerSocketEvent;
import crcl.utils.server.CRCLServerSocketEventListener;
import crcl.utils.server.CRCLServerSocketStateGenerator;
import crcl.utils.server.UnitsTypeSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.SwingUtilities;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.xml.sax.SAXException;
import rcs.posemath.Posemath;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class FanucCRCLMain {

    private @Nullable
    String remoteRobotHost;

    /**
     * Get the value of remoteRobotHost
     *
     * @return the value of remoteRobotHost
     */
    public @Nullable
    String getRemoteRobotHost() {
        return remoteRobotHost;
    }

    /**
     * Set the value of remoteRobotHost
     *
     * @param remoteRobotHost new value of remoteRobotHost
     */
    public XFutureVoid setRemoteRobotHost(String remoteRobotHost) {
        if (!Objects.equals(this.remoteRobotHost, remoteRobotHost)) {
            this.remoteRobotHost = remoteRobotHost;
            if (null != displayInterface) {
                displayInterface.getjTextFieldHostName().setText(remoteRobotHost);
            }
            this.disconnectRemoteRobot();
            return this.connectRemoteRobot();
        } else {
            return XFutureVoid.completedFutureWithName("setRemoteRobotHost.nochange");
        }
    }

    private int localPort;

    /**
     * Get the value of localPort
     *
     * @return the value of localPort
     */
    public int getLocalPort() {
        return localPort;
    }

    /**
     * Set the value of localPort
     *
     * @param localPort new value of localPort
     */
    public void setLocalPort(int localPort) {
        this.localPort = localPort;
    }

    public @Nullable
    IVar getOverideVar() {
        return this.overrideVar;
    }

    public @Nullable
    IVar getMorSafetyStatVar() {
        return this.morSafetyStatVar;
    }

    public @Nullable
    IVar getMoveGroup1RobMove() {
        return moveGroup1RobMoveVar;
    }

    public @Nullable
    IVar getMoveGroup1ServoReadyVar() {
        return moveGroup1ServoReadyVar;
    }

    public @Nullable
    IRobot2 getRobot() {
        return robot;
    }

    private float xMax;
    private float xMin;
    private float yMax;
    private float yMin;
    private float zMax;
    private float zMin;
    private float border1 = 10;

    private void limitAndUpdatePos(ISysGroupPosition pos) {

        IXyzWpr posXyzWpr = pos.formats(FRETypeCodeConstants.frXyzWpr).queryInterface(IXyzWpr.class);
        if (null == posXyzWpr) {
            throw new IllegalArgumentException("Can't get xyzwpr for pos");
        }
        boolean changed = false;
        PmCartesian cart = new PmCartesian(posXyzWpr.x(), posXyzWpr.y(), posXyzWpr.z());
        float xMinEffective = xMin + border1;
        float xMaxEffective = xMax - border1;
        float yMinEffective = yMin + border1;
        float yMaxEffective = yMax - border1;
        float zMinEffective = zMin + border1;
        float zMaxEffective = zMax - border1;

        if (cart.x > xMaxEffective) {
            posXyzWpr.x(xMaxEffective);
            showWarning("X move of " + cart.x + " limited to max = " + xMaxEffective);
            changed = true;
        } else if (cart.x < xMinEffective) {
            posXyzWpr.x(xMinEffective);
            showWarning("X move of " + cart.x + " limited to min= " + xMinEffective);
            changed = true;
        }
        if (cart.y > yMaxEffective) {
            posXyzWpr.y(yMaxEffective);
            showWarning("Y move of " + cart.y + " limited to max = " + yMaxEffective);
            changed = true;
        } else if (cart.y < yMinEffective) {
            posXyzWpr.y(yMinEffective);
            showWarning("Y move of " + cart.y + " limited to min = " + yMinEffective);
            changed = true;
        }
        if (cart.z > zMaxEffective) {
            posXyzWpr.z(zMaxEffective);
            showWarning("Z move of " + cart.z + " limited to max = " + zMaxEffective);
            changed = true;
        } else if (cart.z < zMinEffective) {
            posXyzWpr.z(zMinEffective);
            showWarning("Z move of " + cart.z + " limited to min = " + zMinEffective);
            changed = true;
        }

//        if (changed) {
//            PmCartesian newcart = new PmCartesian(posXyzWpr.x(), posXyzWpr.y(), posXyzWpr.z());
//        }
        pos.update();
    }

    public XFutureVoid startDisplayInterface() {
        XFutureVoid ret = new XFutureVoid("startDisplayInterface");
        SwingUtilities.invokeLater(() -> {
            final FanucCRCLServerDisplayInterface initialLocalDisplayInterface = displayInterface;
            final FanucCRCLServerDisplayInterface localDisplayInterface;
            if (null == initialLocalDisplayInterface) {
                localDisplayInterface = new FanucCRCLServerJFrame();
                displayInterface = localDisplayInterface;
            } else {
                localDisplayInterface = initialLocalDisplayInterface;
            }
            JSlider sliderOv = localDisplayInterface.getjSliderOverride();
            sliderOv.addChangeListener(e -> {
                IVar var = FanucCRCLMain.this.getOverideVar();
                if (null != var) {
                    var.value(sliderOv.getValue());
                } else {
                    showError("Can NOT change override since robot is not initialized.");
                }
            });
            JSlider sliderMaxOv = localDisplayInterface.getjSliderMaxOverride();
            sliderMaxOv.setValue(100);
            sliderMaxOv.addChangeListener(e -> {
                IVar var = FanucCRCLMain.this.getOverideVar();
                if (null != var) {
                    if (Integer.valueOf(var.value().toString()) > sliderMaxOv.getValue()) {
                        var.value(sliderMaxOv.getValue());
                    }
                }
                if (sliderOv.getValue() > sliderMaxOv.getValue()) {
                    sliderOv.setValue(sliderMaxOv.getValue());
                }
                sliderOv.setMaximum(sliderMaxOv.getValue());
                maxRelativeSpeed = sliderMaxOv.getValue();
            });
            localDisplayInterface.setMain(this);
            localDisplayInterface.setPrograms(tpPrograms);
            localDisplayInterface.getjMenuItemReconnectRobot().addActionListener(e -> {
                disconnectRemoteRobot();
                connectRemoteRobot();
            });
            localDisplayInterface.getjMenuItemResetAlarms().addActionListener(e -> {
                IRobot2 robot = FanucCRCLMain.this.getRobot();
                if (null != robot) {
                    robot.alarms().reset();
                    robot.tasks().abortAll(true);
                    this.lastRobotIsConnected = true;
                    this.lastServoReady = true;
                    this.last_safety_stat = 0;
                } else {
                    showError("Can NOT reset alarms since robot is not initialized.");
                }
            });
            localDisplayInterface.getjRadioButtonUseDirectIP().setSelected(!preferRobotNeighborhood);
            localDisplayInterface.getjRadioButtonUseRobotNeighborhood().setSelected(preferRobotNeighborhood);
            final String localRemoteRobotHost = remoteRobotHost;
            if (null != localRemoteRobotHost) {
                localDisplayInterface.getjTextFieldHostName().setText(localRemoteRobotHost);
            }
            localDisplayInterface.getjTextFieldRobotNeighborhoodPath().setText(neighborhoodname);
            localDisplayInterface.getjRadioButtonUseDirectIP().addActionListener(e -> {
                FanucCRCLMain.this.setPreferRobotNeighborhood(localDisplayInterface.getjRadioButtonUseRobotNeighborhood().isSelected());
            });
            localDisplayInterface.getjRadioButtonUseRobotNeighborhood().addActionListener(e -> {
                FanucCRCLMain.this.setPreferRobotNeighborhood(localDisplayInterface.getjRadioButtonUseRobotNeighborhood().isSelected());
            });
            localDisplayInterface.getjTextFieldHostName().addActionListener(e -> {
                FanucCRCLMain.this.setRemoteRobotHost(localDisplayInterface.getjTextFieldHostName().getText());
            });
            localDisplayInterface.getjTextFieldRobotNeighborhoodPath().addActionListener(e -> {
                FanucCRCLMain.this.setNeighborhoodname(localDisplayInterface.getjTextFieldRobotNeighborhoodPath().getText());
            });
            localDisplayInterface.getjTextFieldLimitSafetyBumper().setText("" + border1);
            localDisplayInterface.getjTextFieldLimitSafetyBumper().addActionListener(e -> {
                FanucCRCLMain.this.border1 = Float.valueOf(localDisplayInterface.getjTextFieldLimitSafetyBumper().getText());
            });
            localDisplayInterface.setVisible(true);
            ret.complete();
        });
        return ret;
    }

    private volatile StackTraceElement fanucStartTrace  @Nullable []  = null;
    private volatile boolean started = false;

    public XFutureVoid start(boolean preferRobotNeighborhood, String neighborhoodname, String remoteRobotHost, int localPort) {
        try {
            StackTraceElement callerStartTrace[] = Thread.currentThread().getStackTrace();
            if (this.started) {
                System.err.println("fanucStartTrace = " + CRCLUtils.traceToString(fanucStartTrace));
                System.err.println("");
                throw new IllegalStateException("started twice");
            }
            this.fanucStartTrace = Thread.currentThread().getStackTrace();
            this.started = true;
            this.preferRobotNeighborhood = preferRobotNeighborhood;
            this.neighborhoodname = neighborhoodname;
            this.remoteRobotHost = remoteRobotHost;
            this.localPort = localPort;
            crclServerSocket.setPort(localPort);
            return connectRemoteRobot()
                    .thenRun(() -> wrappedStartCrclServer(callerStartTrace));
        } catch (Exception exception) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    "start(" + preferRobotNeighborhood + "," + neighborhoodname + "," + remoteRobotHost + "," + localPort + ") : " + exception.getMessage(),
                    exception);
            showError(exception.toString());
            throw new RuntimeException(exception);
        }
    }

    private boolean validate = false;

    /**
     * Get the value of validate
     *
     * @return the value of validate
     */
    public boolean isValidate() {
        return validate;
    }

    /**
     * Set the value of validate
     *
     * @param validate new value of validate
     */
    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    long statusUpdateTime = 0;
    private final ThreadLockedHolder<CRCLStatusType> status
            = new ThreadLockedHolder<>("FanucCRCLMain.status", CRCLPosemath.newFullCRCLStatus(), false);
    private final ThreadLockedHolder<CRCLStatusType> serverSocketStatus
            = new ThreadLockedHolder<>("FanucCRCLMain.serverSocketStatus", CRCLPosemath.newFullCRCLStatus(), false);
    private volatile @Nullable
    CRCLStatusType lastStatusUpdateCopy = null;

    volatile long moveDoneTime = 0;
    volatile boolean lastCheckAtPosition = false;
    volatile int moveChecksDone = 0;
    private final double lastJointPosArray[] = new double[10];
    private final long lastJointPosTimeArray[] = new long[10];

    public CRCLStatusType getStatus() {
        return lastStatusUpdateCopy;
    }

    double lastMaxJointDiff = Double.MAX_VALUE;

//    private final PoseStatusType poseStatus = new PoseStatusType();
//
//    /**
//     * Get the value of poseStatus
//     *
//     * @return the value of poseStatus
//     */
//    public PoseStatusType getPoseStatus() {
//        return poseStatus;
//    }
//
//    private final SettingsStatusType settingsStatus = new SettingsStatusType();
//
//    /**
//     * Get the value of settingsStatus
//     *
//     * @return the value of settingsStatus
//     */
//    public SettingsStatusType getSettingsStatus() {
//        return settingsStatus;
//    }
//
//    private JointStatusesType jointStatuses = new JointStatusesType();
//
//    /**
//     * Get the value of jointStatuses
//     *
//     * @return the value of jointStatuses
//     */
//    public JointStatusesType getJointStatuses() {
//        return jointStatuses;
//    }
//
//    /**
//     * Set the value of jointStatuses
//     *
//     * @param jointStatuses new value of jointStatuses
//     */
//    public void setJointStatuses(JointStatusesType jointStatuses) {
//        this.jointStatuses = jointStatuses;
//    }
//
//    private boolean reportPoseStatus = true;
//
//    /**
//     * Get the value of reportPoseStatus
//     *
//     * @return the value of reportPoseStatus
//     */
//    public boolean isReportPoseStatus() {
//        return reportPoseStatus;
//    }
//
//    /**
//     * Set the value of reportPoseStatus
//     *
//     * @param reportPoseStatus new value of reportPoseStatus
//     */
//    public void setReportPoseStatus(boolean reportPoseStatus) {
//        this.reportPoseStatus = reportPoseStatus;
//        CRCLStatusType stat = this.status.get();
//        if (reportPoseStatus && null != poseStatus) {
//            stat.setPoseStatus(poseStatus);
//        } else {
//            stat.setPoseStatus(null);
//        }
//    }
//
//    private boolean reportSettingsStatus = true;
//
//    /**
//     * Get the value of reportSettingsStatus
//     *
//     * @return the value of reportSettingsStatus
//     */
//    public boolean isReportSettingsStatus() {
//        return reportSettingsStatus;
//    }
//
//    /**
//     * Set the value of reportSettingsStatus
//     *
//     * @param reportSettingsStatus new value of reportSettingsStatus
//     */
//    public void setReportSettingsStatus(boolean reportSettingsStatus) {
//        this.reportSettingsStatus = reportSettingsStatus;
//        CRCLStatusType stat = this.status.get();
//        if (reportSettingsStatus && settingsStatus != null) {
//            stat.setSettingsStatus(settingsStatus);
//        } else {
//            stat.setSensorStatuses(null);
//        }
//    }
//
//    private boolean reportJointStatus = true;
//
//    /**
//     * Get the value of reportJointStatus
//     *
//     * @return the value of reportJointStatus
//     */
//    public boolean isReportJointStatus() {
//        return reportJointStatus;
//    }
//
//    /**
//     * Set the value of reportJointStatus
//     *
//     * @param reportJointStatus new value of reportJointStatus
//     */
//    public void setReportJointStatus(boolean reportJointStatus) {
//        this.reportJointStatus = reportJointStatus;
//        CRCLStatusType stat = this.status.get();
//        if (reportJointStatus && null != jointStatuses) {
//            stat.setJointStatuses(jointStatuses);
//        } else {
//            stat.setJointStatuses(null);
//        }
//    }
    private final AtomicBoolean firstUpdate = new AtomicBoolean(true);

    public XFuture<CRCLStatusType> readCachedStatusFromRobot() {

        CRCLStatusType status;
        final long now = System.currentTimeMillis();
        final long timeSinceLastUpdate = now - lastUpdateStatusTime;
        if (timeSinceLastUpdate > 30) {
            XFuture<CRCLStatusType> statusFuture = readStatusFromRobot();
            return statusFuture
                    .thenApply((CRCLStatusType status1) -> {
                        checkJointStatuses(status1);
                        firstUpdate.set(false);
                        lastUpdateStatusTime = System.currentTimeMillis();
                        return status1;
                    });
        } else {
            status = getStatus();
        }
        checkJointStatuses(status);
        return XFuture.completedFuture(status);
    }

    private volatile boolean failJointCheckStackDumped = false;

    private void checkJointStatuses(CRCLStatusType status1) {
        if (!robotIsConnected || this.robot == null) {
            CRCLStatusType stat = this.status.get();
            if (null != stat) {
                final CommandStatusType commandStatus = stat.getCommandStatus();
                if (null != commandStatus
                        && commandStatus.getCommandState() == CommandStateEnumType.CRCL_ERROR) {
                    return;
                }
            }
            setStatusErrorDescription("!robotIsConnected");
            setCommandState(CommandStateEnumType.CRCL_ERROR);
            showError("!robotIsConnected");
            Thread.dumpStack();
        }
        final JointStatusesType jointStatuses = status1.getJointStatuses();
        if (null == jointStatuses) {
            setStatusErrorDescription("null == jointStatuses");
            setCommandState(CommandStateEnumType.CRCL_ERROR);
            showError("null == jointStatuses");
            Thread.dumpStack();
        }
        if (null == jointStatuses || jointStatuses.getJointStatus().size() < 1) {
            status1.setJointStatuses(null);
            final String errMsg = "jointStatuses.().size()="
                    + jointStatuses.getJointStatus().size()
                    + ", last_joint_pos_count=" + last_joint_pos_count
                    + ", last_joint_pos=" + last_joint_pos;
            setStatusErrorDescription(errMsg);
            setCommandState(CommandStateEnumType.CRCL_ERROR);
            showError(errMsg);
            if (!failJointCheckStackDumped) {
                failJointCheckStackDumped = true;
                System.out.println("");
                System.out.flush();
                System.err.println("");
                System.err.flush();
                System.err.println("errMsg=\"" + errMsg + "\"");
                Thread.dumpStack();
                System.err.println("errMsg=\"" + errMsg + "\"");
                System.out.println("");
                System.out.flush();
                System.err.println("");
                System.err.flush();
            }
        }
    }

    boolean lastRobotIsConnected = true;

    private volatile @Nullable
    XFuture<CRCLStatusType> lastReadStatusFromRobotFuture = null;

    public XFuture<CRCLStatusType> readStatusFromRobot() {
        if (null == robot) {
            setStatusErrorDescription("Robot is NOT connected.");
            setCommandState(CommandStateEnumType.CRCL_ERROR);
            if (lastRobotIsConnected) {
                showError("Robot is NOT connected.");
            }
            lastRobotIsConnected = false;
            return XFuture.completedFuture(lastStatusUpdateCopy);
        }
        if (!robotIsConnected) {
            setStatusErrorDescription("Robot is NOT connected.");
            setCommandState(CommandStateEnumType.CRCL_ERROR);
            if (lastRobotIsConnected) {
                showError("Robot is NOT connected.");
            }
            lastRobotIsConnected = false;
            return XFuture.completedFuture(lastStatusUpdateCopy);
        }

        if (!firstUpdate.getAndSet(false)) {
            XFuture<CRCLStatusType> readStatusFuture
                    = XFuture.supplyAsync(
                            "readStatusFromRobotInternal",
                            this::readStatusFromRobotInternal,
                            getRobotService()
                    );
            this.lastReadStatusFromRobotFuture = readStatusFuture;
            return readStatusFuture;
        } else {
            readStatusFromRobotInternal();
        }
        return XFuture.completedFuture(lastStatusUpdateCopy);
    }

    public boolean isConnected() {
        return (null != robot && robotIsConnected);
    }

    public XFutureVoid setConnected(boolean connected) {
        if (connected != isConnected()) {
            if (connected) {
                return connectRemoteRobot();
            } else {
                disconnectRemoteRobot();
                return XFutureVoid.completedFutureWithName("setConnected.disconnected");
            }
        } else {
            return XFutureVoid.completedFutureWithName("setConnected.nochange");
        }
    }

    private boolean lastMotionProgramRunning() {
        final ITPProgram localLastRunMotionProgram = this.lastRunMotionProgram;
        if (null == localLastRunMotionProgram) {
            return false;
        }
        return getTaskList(false)
                .map(taskList -> taskList.stream().anyMatch((Object[] objects) -> objects.length > 0 && Objects.equals(objects[0].toString(), localLastRunMotionProgram.name())))
                .orElse(false);
    }

    public static @Nullable
    PoseType lastDoneMovePose = null;

    public static long lastDoneMoveCommandID = -582;

    private boolean holdingObject;

    /**
     * Get the value of holdingObject
     *
     * @return the value of holdingObject
     */
    public boolean isHoldingObject() {
        return holdingObject;
    }

    /**
     * Set the value of holdingObject
     *
     * @param holdingObject new value of holdingObject
     */
    public void setHoldingObject(boolean holdingObject) {
        this.holdingObject = holdingObject;
    }

    private boolean holdingObjectKnown;

    /**
     * Get the value of holdingObjectKnown
     *
     * @return the value of holdingObjectKnown
     */
    public boolean isHoldingObjectKnown() {
        return holdingObjectKnown;
    }

    /**
     * Set the value of holdingObjectKnown
     *
     * @param holdingObjectKnown new value of holdingObjectKnown
     */
    public void setHoldingObjectKnown(boolean holdingObjectKnown) {
        this.holdingObjectKnown = holdingObjectKnown;
    }

    public @Nullable
    PoseType getPose() {
        if (null == this.status) {
            return null;
        }
        CRCLStatusType stat = this.status.get();
        return CRCLPosemath.getNullablePose(stat);
    }

    public void setPose(PoseType newPose) {
        if (null == this.status) {
            return;
        }
        CRCLStatusType stat = this.status.get();
        CRCLPosemath.setPose(stat, newPose);
    }

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmss.SSS");

    public static String getDateTimeString() {
        Date date = new Date();
        return dateFormat.format(date);
    }

    private @Nullable
    File moveLogFile = null;

    private @Nullable
    PrintStream moveLogFilePrintStream = null;

    private final AtomicInteger readStatusCount = new AtomicInteger();
    private List<Long> updateTimes = new ArrayList<>();
    private final AtomicInteger readStatusFromRobotInternalStartCount = new AtomicInteger();
    private final AtomicInteger readStatusFromRobotInternalEndCount = new AtomicInteger();

    private volatile @Nullable
    IJoint last_joint_pos = null;

    private volatile int last_joint_pos_count = -1;

    private CRCLStatusType readStatusFromRobotInternal() {
        CRCLStatusType localStatus = this.status.get();
        try {
//            copyFromServerSocketStatus();
            synchronized (this) {
                crclServerSocket.runUpdateServerSideStatusRunnables(null);
                readStatusFromRobotInternalStartCount.incrementAndGet();
                if (null == robot) {
                    setStatusErrorDescription("Robot is NOT connected.");
                    setCommandState(CommandStateEnumType.CRCL_ERROR);
                    if (lastRobotIsConnected) {
                        showError("Robot is NOT connected.");
                    }
                    lastRobotIsConnected = false;
                    return localStatus;
                }
                if (!robotIsConnected) {
                    setStatusErrorDescription("Robot is NOT connected.");
                    setCommandState(CommandStateEnumType.CRCL_ERROR);
                    if (lastRobotIsConnected) {
                        showError("Robot is NOT connected.");
                    }
                    lastRobotIsConnected = false;
                    return localStatus;
                }
                lastRobotIsConnected = true;
                long start = System.currentTimeMillis();

                synchronized (localStatus) {
                    readStatusCount.incrementAndGet();
                    CommandStatusType commandStatus = localStatus.getCommandStatus();
                    if (commandStatus == null) {
                        commandStatus = new CommandStatusType();
                        localStatus.setCommandStatus(commandStatus);
                        setCommandState(CommandStateEnumType.CRCL_WORKING);
                    }
                    if (commandStatus.getCommandID() < 1) {
                        commandStatus.setCommandID(1);
                    }
                    if (null == commandStatus.getCommandState()) {
                        setCommandState(CommandStateEnumType.CRCL_WORKING);
                    }
                    if (holdingObjectKnown) {
                        if (null == localStatus.getGripperStatus()) {
                            ParallelGripperStatusType parallelGripperStatus = new ParallelGripperStatusType();
                            parallelGripperStatus.setGripperName("SCHUNK_MPG40");
                            localStatus.setGripperStatus(parallelGripperStatus);
                        }
                        GripperStatusType gripperStatus = localStatus.getGripperStatus();
                        if (null != gripperStatus) {
                            gripperStatus.setHoldingObject(holdingObject);
                        }
                    }
                    if (null != localStatus.getGripperStatus()) {
                        if (localStatus.getGripperStatus() instanceof ParallelGripperStatusType) {
                            ParallelGripperStatusType parallelGripperStatus = (ParallelGripperStatusType) localStatus.getGripperStatus();
                            parallelGripperStatus.setSeparation(gripperSeperation);
                        }
                    }
                    commandStatus.setStatusID(commandStatus.getStatusID() + 1);
                    commandStatus.setOverridePercent(overrideValue);
                    if (commandStatus.getCommandState() != CommandStateEnumType.CRCL_WORKING) {
                        lastCheckAtPosition = false;
                    }
                    if (null == robot) {
                        setStatusErrorDescription("fanucCRCLServer not connected to robot");
                        setCommandState(CommandStateEnumType.CRCL_ERROR);
                        showError("fanucCRCLServer not connected to robot");
                        return localStatus;
                    }
                    ICurPosition icp = robot.curPosition();
                    if (null == icp) {
                        showError("robot.curPosition() returned null");
                        crclServerSocket.setCommandStateEnum(CommandStateEnumType.CRCL_ERROR);
                        commandStatus.setCommandState(CommandStateEnumType.CRCL_ERROR);
                        return localStatus;
                    }
                    ICurGroupPosition icgp = icp.group((short) 1, FRECurPositionConstants.frWorldDisplayType);
                    Com4jObject com4jobj_pos = icgp.formats(FRETypeCodeConstants.frXyzWpr);
                    IXyzWpr pos = com4jobj_pos.queryInterface(IXyzWpr.class);
                    PmCartesian cart = new PmCartesian(pos.x(), pos.y(), pos.z());
                    PmRpy rpy = new PmRpy(Math.toRadians(pos.w()), Math.toRadians(pos.p()), Math.toRadians(pos.r()));
                    setPose(CRCLPosemath.toPoseType(cart, rcs.posemath.Posemath.toRot(rpy), getPose()));
                    Com4jObject com4jobj_joint_pos = icgp.formats(FRETypeCodeConstants.frJoint);
                    IJoint joint_pos = com4jobj_joint_pos.queryInterface(IJoint.class);
                    last_joint_pos = joint_pos;
                    if (null == localStatus.getJointStatuses()) {
                        localStatus.setJointStatuses(new JointStatusesType());
                    }
                    final JointStatusesType jointStatuses = localStatus.getJointStatuses();
                    assert (jointStatuses != null);
                    jointStatuses.getJointStatus().clear();
                    int joint_pos_count = joint_pos.count();
                    last_joint_pos_count = joint_pos_count;
                    for (short i = 1; i <= joint_pos_count; i++) {
                        JointStatusType js = new JointStatusType();
                        js.setJointNumber(i);
                        double cur_joint_pos = joint_pos.item(i);
                        double last_joint_pos = lastJointPosArray[i];
                        long last_joint_pos_time = lastJointPosTimeArray[i];
                        long cur_time = System.currentTimeMillis();
                        double joint_vel = 1000.0 * (cur_joint_pos - last_joint_pos) / (cur_time - last_joint_pos_time + 1);
                        lastJointPosArray[i] = cur_joint_pos;
                        lastJointPosTimeArray[i] = cur_time;
                        js.setJointPosition(cur_joint_pos);
                        try {
                            final Map<Integer, ConfigureJointReportType> localCjrMap = this.cjrMap;
                            if (null != localCjrMap && localCjrMap.size() > 0) {
                                clearJointStatus(js);
                                ConfigureJointReportType cjrt = localCjrMap.get(js.getJointNumber());
                                if (null != cjrt) {
                                    if (cjrt.getJointNumber() == js.getJointNumber()) {
                                        if (cjrt.isReportPosition()) {
                                            js.setJointPosition(cur_joint_pos);
                                        }
                                        if (cjrt.isReportVelocity()) {
                                            js.setJointVelocity(joint_vel);
                                        }
                                        if (cjrt.isReportTorqueOrForce()) {
                                            js.setJointTorqueOrForce(0.0);
                                        }
                                    }
                                }
                                if (commandStatus.getCommandState() == CommandStateEnumType.CRCL_WORKING
                                        && prevCmd instanceof ConfigureJointReportsType) {
                                    this.setCommandState(CommandStateEnumType.CRCL_DONE);
                                }
//                                if (commandStatus.getCommandState() == CommandStateEnumType.CRCL_WORKING
//                                        && prevCmd instanceof ConfigureStatusReportType) {
//                                    this.setCommandState(CommandStateEnumType.CRCL_DONE);
//                                }
                            }
                        } catch (Throwable ex) {
                            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "i=" + i + ",js=" + js + " : " + ex.getMessage(), ex);
                        }
                        jointStatuses.getJointStatus().add(js);
                        checkDonePrevCmd();
                    }
                    if (null == prevCmd || !(prevCmd instanceof InitCanonType)
                            || commandStatus.getCommandState() != CommandStateEnumType.CRCL_WORKING) {
                        checkServoReady();
                    }
                    updateTimes.add(System.currentTimeMillis() - start);
                }
                crclServerSocket.runUpdateServerSideStatusRunnables(localStatus);
            }
            copyToServerSocketStatus();
        } catch (PmException ex) {
            showError(ex.toString());
            getLocalLogger().log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException(ex);
        } finally {
            readStatusFromRobotInternalEndCount.incrementAndGet();
        }
        return localStatus;
    }

    @SuppressWarnings("nullness")
    private void clearJointStatus(JointStatusType js) {
        js.setJointPosition(null);
        js.setJointVelocity(null);
        js.setJointTorqueOrForce(null);
    }

    private void copyToServerSocketStatus() {
        CRCLStatusType localStatus = this.status.get();
        if (null != crclServerSocket && null != serverSocketStatus) {
            CRCLStatusType localServerSocketStatus = this.serverSocketStatus.get();
            synchronized (localStatus) {
                synchronized (crclServerSocket) {
                    final JointStatusesType jointStatusesLocal = localStatus.getJointStatuses();
                    if (null != jointStatusesLocal) {
                        localServerSocketStatus.setJointStatuses(copy(jointStatusesLocal));
                    }
                    final PoseStatusType poseStatusLocal = localStatus.getPoseStatus();
                    if (null != poseStatusLocal) {
                        localServerSocketStatus.setPoseStatus(copy(poseStatusLocal));
                    }
                    final GripperStatusType gripperStatus = localStatus.getGripperStatus();
                    if (null != gripperStatus) {
                        localServerSocketStatus.setGripperStatus(copy(gripperStatus));
                    }
                    this.lastStatusUpdateCopy = CRCLCopier.copy(localStatus);
                }
            }
        }
    }

    private volatile double distToGoal = 0.0;

    public double getDistToGoal() {
        return distToGoal;
    }

    private final List<ITask> tasksList = new ArrayList<>();

    private final Map<String, ITask> namesToTaskMap = new HashMap<>();
    private final Set<String> prognamesNeeded = new HashSet<>();

    private volatile @Nullable
    Iterator<Com4jObject> updateTasksListIterator = null;

    private static final Logger LOGGER = getLocalLogger();

    private static boolean debug = Boolean.getBoolean("FanucCRCLMain.debug");

    private static void logDebug(String string) {
        if (debug) {
            LOGGER.log(Level.INFO, string);
        }
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean newDebugVal) {
        debug = newDebugVal;
    }

    private void updateTasksList() {
        try {
            logDebug("prognamesNeeded = " + prognamesNeeded);
            logDebug("namesToTaskMap.keySet() = " + namesToTaskMap.keySet());
            if (null == updateTasksListIterator) {
                if (null != robot) {
                    ITasks tasks = robot.tasks();
                    if (null != tasks) {
                        updateTasksListIterator = tasks.iterator();
                    }
                }
            }
            if (updateTasksListIterator != null) {
                if (updateTasksListIterator.hasNext()) {
                    Com4jObject c4jo = updateTasksListIterator.next();
                    ITask tsk = c4jo.queryInterface(ITask.class);
                    try {
                        IProgram tskProg = tsk.curProgram();
                        if (null != tskProg) {
                            String tskProgName = tskProg.name();
                            String upperTskProgName = tskProgName.toUpperCase();
                            if (namesToTaskMap.keySet().contains(upperTskProgName)) {
                                return;
                            }
                            if (!prognamesNeeded.contains(upperTskProgName)) {
                                return;
                            }
                            tasksList.add(tsk);
                            namesToTaskMap.put(upperTskProgName, tsk);
                        }
                    } catch (Exception e) {
                    }
                    logDebug("namesToTaskMap.keySet() = " + namesToTaskMap.keySet());
                } else {
                    updateTasksListIterator = null;
                }
            }
        } catch (ComException e) {
            e.printStackTrace();
            showError(e.toString());
        }
    }

    public boolean checkCurrentTasks() {
        if (tasksList.size() < prognamesNeeded.size()) {
            updateTasksList();
        }
        for (ITask tsk : tasksList) {
            FRETaskStatusConstants tskStatus = tsk.status();
            if (tskStatus == FRETaskStatusConstants.frStatusRun || tskStatus == FRETaskStatusConstants.frStatusRunAccept) {
                return false;
            }
        }
        return true;
    }

    private void checkDonePrevCmd() {
        CRCLStatusType localStatus = this.status.get();
        final JointStatusesType jointStatuses = localStatus.getJointStatuses();
        if (localStatus.getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_WORKING) {
            final CRCLCommandType localPrevCmd = prevCmd;
            if (localPrevCmd != null) {
                if (localPrevCmd instanceof MoveToType) {
                    try {
                        MoveToType mtPrev = (MoveToType) localPrevCmd;
                        double dist = distTransFrom(mtPrev.getEndPosition());
                        distToGoal = dist;
                        double rotDist = distRotFrom(mtPrev.getEndPosition());
                        long curTime = System.currentTimeMillis();
                        if (checkMoveDone(dist, rotDist, curTime)) {
                            if (!lastCheckAtPosition) {
                                moveDoneTime = System.currentTimeMillis();
                            } else if ((System.currentTimeMillis() - moveDoneTime) > 20) {
                                try {
                                    lastDoneMovePose = copy(mtPrev.getEndPosition());
                                    lastDoneMoveCommandID = mtPrev.getCommandID();
                                    final PoseType localMoveToStartPosition = moveToStartPosition;
                                    if (null == localMoveToStartPosition) {
                                        throw new NullPointerException("moveToStartPosition");
                                    }
//                                            logDebug("mtPrev.getCommandID() = " + mtPrev.getCommandID());
//                                            logDebug("mtPrev.getEndPosition().getPoint().getZ() = " + mtPrev.getEndPosition().getPoint().getZ());
//                                            logDebug("rotDist = " + rotDist);
//                                            logDebug("dist = " + dist);
                                    double distTransFromStart = distTransFrom(localMoveToStartPosition);
//                                            logDebug("distFromStart = " + distTransFromStart);
                                    double distRotFromStart = distRotFrom(localMoveToStartPosition);
//                                            logDebug("distRotFromStart = " + distRotFromStart);
//                                            logDebug("Done move = " + CRCLSocket.getUtilSocket().commandToString(prevCmd, false) + " status =" + CRCLSocket.getUtilSocket().statusToString(status, false));
                                    long moveTime = (System.currentTimeMillis() - startMoveTime);
//                                            logDebug("Move took " + moveTime + " ms.");
//                                            logDebug("moveChecksDone = " + moveChecksDone);
//                                                moveLogFilePrintStream.println("current_time_ms,current_time_string,id,start_x,start_y,start_z,end_x,end_y,end_z,distTran,distRot,moveTime,moveCheckCount");
                                    if (keepMoveToLog) {
                                        openMoveToLogFile();
                                    }
                                    final PrintStream localLogFilePrintStream = moveLogFilePrintStream;
                                    if (null != localLogFilePrintStream) {
                                        String stringToLog
                                                = curTime + ","
                                                + getDateTimeString() + ","
                                                + (curTime - expectedEndMoveToTime) + ","
                                                + lastDoneMoveCommandID + ","
                                                + localMoveToStartPosition.getPoint().getX() + ","
                                                + localMoveToStartPosition.getPoint().getY() + ","
                                                + localMoveToStartPosition.getPoint().getZ() + ","
                                                + mtPrev.getEndPosition().getPoint().getX() + ","
                                                + mtPrev.getEndPosition().getPoint().getY() + ","
                                                + mtPrev.getEndPosition().getPoint().getZ() + ","
                                                + distTransFromStart + ","
                                                + distRotFromStart + ","
                                                + moveTime + ","
                                                + moveChecksDone + ","
                                                + transSpeed + ","
                                                + rotSpeed + ","
                                                + (distTransFromStart / (1e-3 * moveTime)) + ","
                                                + (distRotFromStart / (1e-3 * moveTime)) + ","
                                                + timeToWaitForLastMotionProgram + ","
                                                + timeToStartMotionProgram + ","
                                                + lastMotionProgramRunningCount + ","
                                                + "\"" + distances + "\","
                                                + "\"" + moveReasons + "\","
                                                + "\"" + updateTimes + "\",";
                                        localLogFilePrintStream.println(stringToLog);
                                        localLogFilePrintStream.flush();
                                    }
                                    moveChecksDone = 0;
//start_y,start_z,end_x,end_y,end_z,distTran,distRot,moveTime,moveCheckCount");

                                } catch (Exception ex) {
                                    getLocalLogger().log(Level.SEVERE, ex.getMessage(), ex);
                                }
                                setCommandState(CommandStateEnumType.CRCL_DONE);
                                setPrevCmd(null);
                            }
                            lastCheckAtPosition = true;
                        } else {
                            setCommandState(CommandStateEnumType.CRCL_WORKING);
                            lastCheckAtPosition = false;
                            moveChecksDone++;
                        }
                    } catch (PmException ex) {
                        showError(ex.toString());
                        getLocalLogger().log(Level.SEVERE, null, ex);
                    }
                } else if (localPrevCmd instanceof MoveThroughToType) {
                    MoveThroughToType mtt = (MoveThroughToType) localPrevCmd;
                    if (currentWaypointNumber >= mtt.getNumPositions()
                            || currentWaypointNumber >= mtt.getWaypoint().size()) {
                        try {
                            PoseType pose = mtt.getWaypoint().get(mtt.getWaypoint().size() - 1);
                            double dist = distTransFrom(pose);
                            double rotDist = distRotFrom(pose);
                            final IIndGroupPosition localGroupPos = Objects.requireNonNull(groupPos, "groupPos");
                            if (dist < distanceTolerance
                                    && rotDist < distanceRotTolerance
                                    && localGroupPos.isAtCurPosition()
                                    && (System.currentTimeMillis() - moveTime > 10)
                                    && checkCurrentTasks()) {
                                if (!lastCheckAtPosition) {
                                    moveDoneTime = System.currentTimeMillis();
                                } else if ((System.currentTimeMillis() - moveDoneTime) > 10) {
                                    setCommandState(CommandStateEnumType.CRCL_DONE);
                                }
                                lastCheckAtPosition = true;
                            } else {
                                setCommandState(CommandStateEnumType.CRCL_WORKING);
                                lastCheckAtPosition = false;
                            }
                        } catch (PmException ex) {
                            showError(ex.toString());
                            getLocalLogger().log(Level.SEVERE, null, ex);
                        }
                    }
                } else if (localPrevCmd instanceof DwellType) {
                    long diff = System.currentTimeMillis() - dwellEndTime;
                    if (diff >= 0 && localStatus.getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_WORKING) {
//                                if(diff > 5) {
//                                    showError("dwell took:" + diff + " additional milliseconds over the expected "+((long)(((DwellType)prevCmd).getDwellTime().doubleValue()*1000.0)));
//                                }
                        setCommandState(CommandStateEnumType.CRCL_DONE);
                    }
                } else if (localPrevCmd instanceof InitCanonType) {
                    long diff = System.currentTimeMillis() - dwellEndTime;
//                            logDebug("(prevCmd instanceof InitCanonType) diff = " + diff);
//                            logDebug("status.getCommandStatus().getCommandState() = " + status.getCommandStatus().getCommandState());
                    if (diff >= 0 && localStatus.getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_WORKING) {
//                                if(diff > 5) {
//                                    showError("dwell took:" + diff + " additional milliseconds over the expected "+((long)(((DwellType)prevCmd).getDwellTime().doubleValue()*1000.0)));
//                                }
                        lastServoReady = true;
//                                logDebug("robotResetCount = " + robotResetCount);
                        boolean secondInitSafetyStatError = checkSafetyStatError();
//                                logDebug("secondInitSafetyStatError = " + secondInitSafetyStatError);
                        if (secondInitSafetyStatError) {
                            setStatusErrorDescription(morSafetyStatToString(last_safety_stat));
                            setCommandState(CommandStateEnumType.CRCL_ERROR);
                        } else if (robotResetCount < 3) {
                            boolean secondInitCheckServoReady = checkServoReady();
                            logDebug("secondInitCheckServoReady = " + secondInitCheckServoReady);
                            if (!secondInitCheckServoReady) {
                                final IRobot2 localRobot = robot;
                                if (null != localRobot) {
                                    localRobot.alarms().reset();
                                    localRobot.tasks().abortAll(true);
                                }
                                dwellEndTime = System.currentTimeMillis() + 2000;
                                robotResetCount++;
                                setCommandState(CommandStateEnumType.CRCL_WORKING);
                            } else {
                                setCommandState(CommandStateEnumType.CRCL_DONE);
                            }
                        } else {
                            setCommandState(CommandStateEnumType.CRCL_DONE);
                        }
                    }
                } else if (localPrevCmd instanceof ActuateJointsType) {
                    if (null == posReg97) {
                        throw new NullPointerException("posReg97");
                    }
                    posReg97.update();
                    if (posReg97.isAtCurPosition() && checkCurrentTasks()) {
                        ActuateJointsType actJoints = (ActuateJointsType) localPrevCmd;
                        double maxDiff = 0;
                        for (ActuateJointType aj : actJoints.getActuateJoint()) {
                            int num = aj.getJointNumber();
                            assert (jointStatuses != null);
                            for (JointStatusType jst : jointStatuses.getJointStatus()) {
                                if (num == jst.getJointNumber()) {
                                    double diff = Math.abs(jst.getJointPosition() - aj.getJointPosition());
                                    if (diff > maxDiff) {
                                        maxDiff = diff;
                                    }
                                    break;
                                }
                            }
                        }
                        if (maxDiff < 0.1 && lastMaxJointDiff < 0.1) {
                            setCommandState(CommandStateEnumType.CRCL_DONE);
                            logDebug("actuateJointMaxTime = " + actuateJointMaxTime);
                            long time_running = System.currentTimeMillis() - actuateJointStartTime;
                            logDebug("time_running = " + time_running);
                        }
                        lastMaxJointDiff = maxDiff;

                    }

                }
            } else {
                lastCheckAtPosition = false;
            }
        } else {
            lastCheckAtPosition = false;
        }
    }

    public static enum MoveStatus {
        DIST_OVER_TOLERANCE,
        ROTDIST_OVER_TOLERANCE,
        EXPECTED_END_MOVE_TIME,
        POSREG98_AT_CUR_POSITION,
        CURTIME_NEAR_MOVETIME,
        LASTMOTIONPROGGRAMRUNNING,
        MOVE_DONE,
        MOVE_STATUS_NOT_SET,
        CHECKED_TASK_STILL_RUNNING,
        MOVE_ABORTED
    }

    private volatile MoveStatus moveStatus = MoveStatus.MOVE_STATUS_NOT_SET;

    public MoveStatus getMoveStatus() {
        return moveStatus;
    }

    List<MoveStatus> moveReasons = new ArrayList<>();
    List<Double> distances = new ArrayList<>();

    private void addMoveReason(MoveStatus reason) {
        moveStatus = reason;
        if (keepMoveToLog) {
            if (moveReasons.isEmpty() || moveReasons.get(moveReasons.size() - 1) != reason) {
                moveReasons.add(reason);
            }
        }
    }

    private boolean checkForLastMotionProgramRunning = false;

    private boolean checkMoveDone(double dist, double rotDist, long curTime) {
        if (abortCount.get() != moveStartAbortCount) {
            addMoveReason(MoveStatus.MOVE_ABORTED);
            return true;
        }
        if ((curTime - expectedEndMoveToTime) > 2000) {
            warnMoveTime(dist, rotDist, curTime);
        }
        if (dist >= distanceTolerance) {
            addMoveReason(MoveStatus.DIST_OVER_TOLERANCE);
            distances.add(dist);
            return false;
        }
        if (rotDist >= distanceRotTolerance) {
            addMoveReason(MoveStatus.ROTDIST_OVER_TOLERANCE);
            return false;
        }

        if (null == posReg98) {
            throw new NullPointerException("posReg98");
        }
        if (!posReg98.isAtCurPosition()) {
            addMoveReason(MoveStatus.POSREG98_AT_CUR_POSITION);
            return false;
        }
        if (!checkCurrentTasks()) {
            addMoveReason(MoveStatus.CHECKED_TASK_STILL_RUNNING);
            return false;
        }
        if (curTime < expectedEndMoveToTime && (curTime - moveTime) < 2000) {
            addMoveReason(MoveStatus.EXPECTED_END_MOVE_TIME);
            return false;
        }
        if ((curTime - moveTime) < 20) {
            addMoveReason(MoveStatus.CURTIME_NEAR_MOVETIME);
            return false;
        }
        if (checkForLastMotionProgramRunning) {
            if (lastMotionProgramRunning()) {
                addMoveReason(MoveStatus.LASTMOTIONPROGGRAMRUNNING);
                return false;
            }
        }
        moveStatus = MoveStatus.MOVE_DONE;
        return true;
    }

    private long lastWarnMoveTime = 0;

    private void warnMoveTime(double dist, double rotDist, long curTime) {
        if (curTime - lastWarnMoveTime > 2000) {
            System.err.println("FanucCRCL: move taking much longer than expected : (curTime - expectedEndMoveToTime) =" + (curTime - expectedEndMoveToTime) + ", moveStatus=" + moveStatus + ",dist=" + dist + ",rotDist=" + rotDist);
            lastWarnMoveTime = curTime;
        }
    }

    public @Nullable
    CRCLCommandType getPrevCmd() {
        return prevCmd;
    }

    private boolean lastActivAlarms = false;

    private boolean checkServoReady() {
        boolean readyNow = false;
        boolean safetyStatError = checkSafetyStatError();
        if (safetyStatError) {
            lastServoReady = true;
        } else if (null != moveGroup1ServoReadyVar) {
            moveGroup1ServoReadyVar.refresh();
            Object val = moveGroup1ServoReadyVar.value();
            if (val instanceof Boolean) {
                boolean servoReady = (boolean) val;
                if (!servoReady) {
                    CRCLStatusType localStatus = this.status.get();
                    if (lastServoReady) {
                        showError("SERVO_NOT_READY (Need to reset fault?)");
                    } else if (localStatus.getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_WORKING) {
                        setStatusErrorDescription("SERVO_NOT_READY (Need to reset fault?)");
                    }
                }
                lastServoReady = servoReady;
                readyNow = servoReady;
            }
        } else {
            lastServoReady = true;
        }
        return readyNow;
    }

    private volatile boolean lastSafetyStatError = false;
    private volatile long lastCheckSafetyStatTime = 0;

    private boolean checkSafetyStatError() {
        boolean safetyStatError = false;
        if (System.currentTimeMillis() - lastCheckSafetyStatTime < 100) {
            return lastSafetyStatError;
        }
        if (null != morSafetyStatVar) {
            morSafetyStatVar.refresh();
            int safety_stat = (int) morSafetyStatVar.value();
            lastCheckSafetyStatTime = System.currentTimeMillis();
            safetyStatError = isMoreSafetyStatError(safety_stat);
            if (safetyStatError) {
                CRCLStatusType localStatus = this.status.get();
                if (safety_stat != last_safety_stat) {
                    showError(morSafetyStatToString(safety_stat));
                } else if (localStatus.getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_WORKING) {
                    setStatusErrorDescription(morSafetyStatToString(safety_stat));
                }
            }
            last_safety_stat = safety_stat;
        }
        lastSafetyStatError = safetyStatError;
        return safetyStatError;
    }

    public ExecutorService getRobotService() {
        if (null == robotService) {
            robotService = Executors.newSingleThreadExecutor(daemonThreadFactory);
        }
        return robotService;
    }

    int last_safety_stat = 0;
    boolean lastServoReady = true;

    public static class FanucClientState extends CRCLServerClientState {

        public FanucClientState(CRCLSocket cs) {
            super(cs);
        }
        int i;
    }

    public static final CRCLServerSocketStateGenerator<FanucClientState> FANUC_STATE_GENERATOR
            = FanucClientState::new;

    @SuppressWarnings("initialization")
    private final CRCLServerSocketEventListener<FanucClientState> crclSocketEventListener
            = this::handleCrclServerSocketEvent;

    private void handleCrclServerSocketEvent(CRCLServerSocketEvent<FanucClientState> evt) {
        try {
            switch (evt.getEventType()) {
                case CRCL_COMMAND_RECIEVED:
                    final CRCLCommandInstanceType instance = evt.getInstance();
                    final CRCLSocket source = evt.getSource();
                    if (source == null || instance == null) {
                        throw new RuntimeException("evt.getEventType()=CRCL_COMMAND_RECIEVED, but instance=" + instance + ", source=" + source);
                    }
                    handleClientCommand(instance, source);
                    break;

                case EXCEPTION_OCCURRED:
                    internalStopMotion();
                    crclServerSocket.setCommandStateEnum(CommandStateEnumType.CRCL_ERROR);
                    final Exception exception = evt.getException();
                    if (null != exception) {
                        crclServerSocket.setStateDescription(exception.getMessage());
                    }
                    break;

                case NEW_CRCL_CLIENT:
                    break;

                case SERVER_CLOSED:
                    internalStopMotion();
                    break;

                case GUARD_LIMIT_REACHED:
                    internalStopMotion();

//                    overrideVar.value(overrideValue);
                    crclServerSocket.comleteGuardTrigger();

                    break;
            }
        } catch (Exception ex) {
            getLocalLogger().log(Level.SEVERE, "evt=" + evt, ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
    }
    private final CRCLServerSocket<FanucClientState> crclServerSocket;

//    private ExecutorService es;
//    private ServerSocket ss;
//    private Set<CRCLSocket> clients = new HashSet<>();
    public static void stop() {
        if (null != main) {
            main.stopInternal();
        }
        main = null;
        logDebug("Thread.activeCount() = " + Thread.activeCount());
        for (StackTraceElement ste[] : Thread.getAllStackTraces().values()) {
            logDebug("ste = " + Arrays.toString(ste));
        }
        Thread ta[] = new Thread[10 + Thread.activeCount()];
        Thread.enumerate(ta);
        for (Thread t : ta) {
            if (null != t && !t.equals(Thread.currentThread())) {
                logDebug("t = " + t);
                logDebug("t.isAlive() = " + t.isAlive());
                logDebug("t.isDaemon() = " + t.isDaemon());
                logDebug("t.isInteruppted() = " + t.isInterrupted());
                logDebug("t.getStackTrace() = " + Arrays.toString(t.getStackTrace()));
                t.interrupt();
            }
        }
        System.exit(0);
    }

    public synchronized void stopCrclServer() {
//        if (null != crclServerFuture) {
//            crclServerFuture.cancel(true);
////            try {
////                crclServerFuture.get(100, TimeUnit.MILLISECONDS);
////            } catch (InterruptedException | ExecutionException | TimeoutException ex) {
////                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
////            }
//        }
//        if (null != clients) {
//            for (CRCLSocket cs : clients) {
//                try {
//                    cs.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            clients.clear();
//        }
//        if (null != ss) {
//            try {
//                ss.close();
//            } catch (IOException ex) {
//                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            ss = null;
//        }
        if (null != crclServerSocket) {
            crclServerSocket.close();
        }
    }

    private void stopInternal() {
        if (null != moveThread) {
            moveThread.interrupt();
            try {
                moveThread.join(100);
            } catch (InterruptedException ex) {
                getLocalLogger().log(Level.SEVERE, null, ex);
            }
            moveThread = null;
        }
        stopCrclServer();
//        if (null != es) {
//            es.shutdownNow();
//            try {
//                es.awaitTermination(500, TimeUnit.MILLISECONDS);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            es = null;
//        }
        disconnectRemoteRobot();
        if (null != neighborhood) {
            neighborhood.dispose();
            neighborhood = null;
        }
        if (null != displayInterface) {
            displayInterface.setVisible(false);
            displayInterface.dispose();
            displayInterface = null;
        }
    }

    private @Nullable
    ExecutorService robotService = null;

    public void disconnectRemoteRobot() {
        final ExecutorService localRobotService = robotService;
        try {
            tasksIterator = null;
            getTaskListOutput = null;
            updateTasksListIterator = null;
            tasksList.clear();
            namesToTaskMap.clear();
            robotIsConnected = false;
            clearDisplayInterfaceConnected();
            if (null != localRobotService) {
                localRobotService.submit(this::disconnectRemoteRobotInternal).get(500, TimeUnit.MILLISECONDS);
                localRobotService.shutdownNow();
                localRobotService.awaitTermination(500, TimeUnit.MILLISECONDS);
                this.robotService = null;
            } else {
                disconnectRemoteRobotInternal();
            }
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            if (null != localRobotService) {
                localRobotService.shutdownNow();
                try {
                    localRobotService.awaitTermination(500, TimeUnit.MILLISECONDS);
                } catch (InterruptedException ex1) {
                    getLocalLogger().log(Level.SEVERE, null, ex1);
                }
                this.robotService = null;
            }
            getLocalLogger().log(Level.SEVERE, null, ex);
        } finally {
            robotIsConnected = false;
            clearDisplayInterfaceConnected();
            robot = null;
        }
    }

    private synchronized void disconnectRemoteRobotInternal() {

        tasksIterator = null;
        getTaskListOutput = null;
        updateTasksListIterator = null;
        tasksList.clear();
        namesToTaskMap.clear();
        try {
//            if (null != robot_ec) {
//                robot_ec.close();
//                robot_ec = null;
//            }
            if (null != robot) {
                robot.dispose();
                robot = null;
            }
            robotIsConnected = false;
            clearDisplayInterfaceConnected();
        } catch (Exception e) {
            getLocalLogger().log(Level.SEVERE, null, e);
        }
    }

    private void clearDisplayInterfaceConnected() {
        if (null != displayInterface) {
            displayInterface.setConnected(false);
        }
    }

    private volatile int robotResetCount = 0;

    private void handleInitCanon(InitCanonType initCmd) {
        lastServoReady = true;

        recheckOverride();
        boolean initSafetyStatError = checkSafetyStatError();
//        logDebug("initSafetyStatError = " + initSafetyStatError);
        if (initSafetyStatError) {
            setStatusErrorDescription(morSafetyStatToString(last_safety_stat));
            setCommandState(CommandStateEnumType.CRCL_ERROR);
        } else {
            boolean initCheckServoReady = checkServoReady();
//            logDebug("initCheckServoReady = " + initCheckServoReady);
            if (!initCheckServoReady) {
                setCommandState(CommandStateEnumType.CRCL_WORKING);
                dwellEndTime = System.currentTimeMillis() + 1000;
            } else {
                setCommandState(CommandStateEnumType.CRCL_DONE);
            }
        }
        final IRobot2 localRobot = robot;
        if (null != localRobot) {
            localRobot.alarms().reset();
            localRobot.tasks().abortAll(true);
        }
        handleCommandCount = 0;
        updateStatusCount = 0;
        maxHandleCommandTime = 0;
        totalHandleCommandTime = 0;
        maxUpdateStatusTime = 0;
        totalUpdateStatusTime = 0;
        lastServoReady = true;

        robotResetCount = 0;
//        checkAlarms();
    }

    private void recheckOverride() {
        if (null != overrideVar) {
            overrideVar.refresh();
            Object overrideValueObject = overrideVar.value();

            if (overrideValueObject instanceof Integer) {
                int val = (Integer) overrideValueObject;
                if (val != overrideValue) {
                    System.out.println("overrideValueObject = " + overrideValueObject);
                    setOverrideValue(val);
                }
            } else {
                System.out.println("overrideValueObject = " + overrideValueObject);
                if (null != overrideValueObject) {
                    System.out.println("overrideValueObject.getClass() = " + overrideValueObject.getClass());
                }
            }
        }
    }

    private void handleStopMotion(StopMotionType stopCmd) {
        internalStopMotion();
    }

    private void internalStopMotion() {
        try {

            long stopTimeStart = System.currentTimeMillis();
            abortCount.incrementAndGet();
            regNumeric96.regLong(100_000);
            regNumeric97.regLong(100_000);
            regNumeric98.regFloat(0.0f);
            PmCartesian cart1 = getCartDirect();
            System.out.println("FanucCrclMain.internalStopMotion cart1 = " + cart1);
            if (null != moveThread) {
                long t0 = System.currentTimeMillis();
                moveThread.interrupt();
                try {
                    moveThread.join(200);
                } catch (InterruptedException ex) {
                    getLocalLogger().log(Level.SEVERE, null, ex);
                }
                moveThread = null;
                long t1 = System.currentTimeMillis();
                long t01diff = t1 - t0;
                System.out.println("FanucCRCLMain internalStopMotion join moveThread time = " + t01diff);
            }
            if (null != lastMoveToTask) {
                long t2 = System.currentTimeMillis();
                lastMoveToTask.abort(true, true);
                long t3 = System.currentTimeMillis();
                long t23diff = t3 - t2;
                System.out.println("FanucCRCLMain internalStopMotion lastMoveToTask.abort(true, true) time = " + t23diff);
            }
//            if (null != robot) {
//                long t4 = System.currentTimeMillis();
//                ITask motionTask = getRunningTpTask();
//                System.out.println("motionTask = " + motionTask);
//                robot.tasks().abortAll(true);
//                long t5 = System.currentTimeMillis();
//                long t45diff = t5 - t4;
//                System.out.println("FanucCRCLMain internalStopMotion robot.tasks().abortAll(true) time = " + t45diff);
//            }
            PmCartesian cart2 = getCartDirect();
            System.out.println("FanucCrclMain.internalStopMotion cart2 = " + cart2);
            setCommandState(CommandStateEnumType.CRCL_DONE);
            isMovingLastCheckTime = -1;
            int movingChecks = 0;
            while (isMoving()) {
                Thread.sleep(10);
                isMovingLastCheckTime = -1;
                movingChecks++;
            }
            PmCartesian cart3 = getCartDirect();
            System.out.println("FanucCrclMain.internalStopMotion cart3 = " + cart3);
            PmCartesian cart13diff = Posemath.subtract(cart3, cart1);
            System.out.println("FanucCrclMain.internalStopMotion cart13diff = " + cart13diff);
            PmCartesian cart23diff = Posemath.subtract(cart3, cart2);
            System.out.println("FanucCrclMain.internalStopMotion cart23diff = " + cart23diff);
            long timeDiff = System.currentTimeMillis() - stopTimeStart;
            System.out.println("FanucCrclMain.internalStopMotion timeDiff = " + timeDiff);
            System.out.println("FanucCrclMain.internalStopMotion movingChecks = " + movingChecks);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    private @Nullable
    ITask getRunningTpTask() {
        ITasks tasks = robot.tasks();
        ITask motionTask;
        Iterator<Com4jObject> tasksIterator = tasks.iterator();
        while (tasksIterator.hasNext()) {
            Com4jObject c4jo = tasksIterator.next();
            if (null == c4jo) {
                continue;
            }
            ITask task = c4jo.queryInterface(ITask.class);
            if (null == task) {
                continue;
            }
            if (task.status() == FRETaskStatusConstants.frStatusRun && task.programType() == FREProgramTypeConstants.frTPProgramType) {
                motionTask = task;
                return motionTask;
            }
        }
        return null;
    }

    private PmCartesian getCartDirect() {
        ICurPosition icp1 = robot.curPosition();
        if (null == icp1) {
            showError("robot.curPosition() returned null");
        }
        ICurGroupPosition icgp1 = icp1.group((short) 1, FRECurPositionConstants.frWorldDisplayType);
        Com4jObject com4jobj_pos1 = icgp1.formats(FRETypeCodeConstants.frXyzWpr);
        IXyzWpr pos1 = com4jobj_pos1.queryInterface(IXyzWpr.class);
        PmCartesian cart1 = new PmCartesian(pos1.x(), pos1.y(), pos1.z());
        return cart1;
    }

    private void handleEndCanon(EndCanonType initCmd) {
        setCommandState(CommandStateEnumType.CRCL_DONE);
    }

    private double gripperSeperation = 1.0;

    /**
     * Get the value of gripperSeperation
     *
     * @return the value of gripperSeperation
     */
    public double getGripperSeperation() {
        return gripperSeperation;
    }

    /**
     * Set the value of gripperSeperation
     *
     * @param gripperSeperation new value of gripperSeperation
     */
    public void setGripperSeperation(double gripperSeperation) {
        this.gripperSeperation = gripperSeperation;
    }

    private void handleCloseToolChanger(CloseToolChangerType closeToolCmd) {
        this.runTPProgram(Objects.requireNonNull(tool_close_prog, "tool_close_prog"));
        setCommandState(CommandStateEnumType.CRCL_DONE);
    }

    private void handleOpenToolChanger(OpenToolChangerType openToolCmd) {
        this.runTPProgram(Objects.requireNonNull(tool_open_prog, "tool_open_prog"));
        setCommandState(CommandStateEnumType.CRCL_DONE);
    }

    private void handleSetEndEffector(SetEndEffectorType seeCmd) {
        if (seeCmd.getSetting() > 0.5) {
            final ITPProgram localOpenGripperProg = Objects.requireNonNull(open_gripper_prog, "open_gripper_prog");
            localOpenGripperProg.run(FREStepTypeConstants.frStepNone, 1, FREExecuteConstants.frExecuteFwd);
            setGripperSeperation(1.0);
        } else {
            final ITPProgram localCloseGripperProg = Objects.requireNonNull(close_gripper_prog, "close_gripper_prog");
            localCloseGripperProg.run(FREStepTypeConstants.frStepNone, 1, FREExecuteConstants.frExecuteFwd);
            setGripperSeperation(0.0);
        }
        CRCLStatusType stat = this.status.get();
        final SettingsStatusType settingsStatus = stat.getSettingsStatus();
        settingsStatus.setEndEffectorSetting(seeCmd.getSetting());
        setCommandState(CommandStateEnumType.CRCL_DONE);
    }

    private void handleSetAngleUnits(SetAngleUnitsType sauCmd) {
        CRCLStatusType stat = this.status.get();
        final SettingsStatusType settingsStatus = stat.getSettingsStatus();
        settingsStatus.setAngleUnitName(sauCmd.getUnitName());
        setCommandState(CommandStateEnumType.CRCL_DONE);
    }

    private void handleSetForceUnits(SetForceUnitsType sfuCmd) {
        CRCLStatusType stat = this.status.get();
        final SettingsStatusType settingsStatus = stat.getSettingsStatus();
        settingsStatus.setForceUnitName(sfuCmd.getUnitName());
        setCommandState(CommandStateEnumType.CRCL_DONE);
    }

    private @Nullable
    String lastErrorString = null;

    public void showError(@Nullable String error) {
        if (error != null) {
            setStatusErrorDescription(error);
            logInfoString(error);
        }
    }

    public synchronized void setStatusErrorDescription(String error) {
        System.out.println("");
        System.out.flush();
        System.err.println("");
        System.err.flush();
        System.err.println("FanucCRCLMain: error=\"" + error + "\"");
        crclServerSocket.setStateDescription(error);
        crclServerSocket.setCommandStateEnum(CommandStateEnumType.CRCL_ERROR);
        CRCLStatusType localStatus = this.lastStatusUpdateCopy;
        if (null != localStatus) {
            if (null == localStatus.getCommandStatus()) {
                localStatus.setCommandStatus(new CommandStatusType());
                localStatus.getCommandStatus().setCommandID(1);
            }

//            setCommandState(CommandStateEnumType.CRCL_ERROR);
            if (null != connectionError && connectionError.length() > 0 && !error.startsWith(connectionError)) {
                localStatus.getCommandStatus().setStateDescription(connectionError + " " + error);
            } else {
                localStatus.getCommandStatus().setStateDescription(error);
            }
        } else {
            System.out.println("");
            System.out.flush();
            System.err.println("");
            System.err.flush();
            System.err.println("status==null, error=\"" + error + "\"");
            Thread.dumpStack();
            System.out.println("");
            System.out.flush();
            System.err.println("");
            System.err.flush();
        }
    }

    private void logInfoString(String error) {
        if (null != error && !error.equals(lastErrorString)) {
            System.err.println(error);
            if (null != displayInterface) {
                displayInterface.getjTextAreaErrors().append(error + "\n");
            }
            lastErrorString = error;
        }
    }

    public void showWarning(String warningString) {
        CRCLStatusType status = this.status.get();
        if (null != status) {
            if (null == status.getCommandStatus()) {
                status.setCommandStatus(new CommandStatusType());
                status.getCommandStatus().setCommandID(1);
            }

//            status.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_ERROR);
            status.getCommandStatus().setStateDescription(warningString);
        }
        logInfoString(warningString);
    }

    private void showInfo(String info) {
        if (null != displayInterface) {
            displayInterface.getjTextAreaErrors().append(info + "\n");
        }
    }

    double maxRelativeSpeed = 100.0;

    private void handleSetTransSpeed(SetTransSpeedType stsCmd) {
        TransSpeedType ts = stsCmd.getTransSpeed();
        CRCLStatusType stat = this.status.get();
        final SettingsStatusType settingsStatus = stat.getSettingsStatus();
        if (ts instanceof TransSpeedRelativeType) {
            TransSpeedRelativeType tsRel = (TransSpeedRelativeType) ts;
            transSpeed = tsRel.getFraction() * 200.0;
//            int val = ((TransSpeedRelativeType) ts).getFraction().multiply(BigDecimal.valueOf(maxRelativeSpeed)).intValue();
            int val = (int) (tsRel.getFraction() * maxRelativeSpeed);
            final IVar localOverrideVar = Objects.requireNonNull(overrideVar, "overrideVar");
            localOverrideVar.value(val);
            setOverrideValue(val);
            if (null != displayInterface) {
                displayInterface.getjSliderOverride().setValue(val);
            }
            setCommandState(CommandStateEnumType.CRCL_DONE);
            settingsStatus.setTransSpeedRelative(tsRel);
        } else if (ts instanceof TransSpeedAbsoluteType) {
            TransSpeedAbsoluteType tsAbs = (TransSpeedAbsoluteType) ts;
            transSpeed = tsAbs.getSetting();
            if (null == regNumeric98) {
                throw new NullPointerException("regNumeric98");
            }
            regNumeric98.regFloat((float) transSpeed);
            showInfo("R[98] = transSpeed = " + transSpeed);
//            reg98Var.update();
            setCommandState(CommandStateEnumType.CRCL_DONE);
            settingsStatus.setTransSpeedAbsolute(tsAbs);
        }
    }

    private void handleSetRotSpeed(SetRotSpeedType stsCmd) {
        RotSpeedType rs = stsCmd.getRotSpeed();
        CRCLStatusType stat = this.status.get();
        final SettingsStatusType settingsStatus = stat.getSettingsStatus();
        if (rs instanceof RotSpeedRelativeType) {
            RotSpeedRelativeType rsRel = (RotSpeedRelativeType) rs;
            int val = (int) (rsRel.getFraction() * maxRelativeSpeed);
            final IVar localOverrideVar = Objects.requireNonNull(overrideVar, "overrideVar");
            localOverrideVar.value(val);
            setOverrideValue(val);
            if (null != displayInterface) {
                displayInterface.getjSliderOverride().setValue(val);
            }
            setCommandState(CommandStateEnumType.CRCL_DONE);
            settingsStatus.setRotSpeedRelative(rsRel);
        } else if (rs instanceof RotSpeedAbsoluteType) {
            RotSpeedAbsoluteType rsAbs = (RotSpeedAbsoluteType) rs;
            rotSpeed = rsAbs.getSetting();
            setCommandState(CommandStateEnumType.CRCL_DONE);
            settingsStatus.setRotSpeedAbsolute(rsAbs);
        }
    }

    private Set<String> getProgramNames() {
        Set<String> prognames = new TreeSet<String>();
        if (null != robot) {
            IPrograms progs = robot.programs();
            for (Com4jObject c4jo_prog : progs) {
                IProgram prog = c4jo_prog.queryInterface(IProgram.class);
                prognames.add(prog.name());
            }
        }
        return prognames;
    }

    volatile private @Nullable
    Thread moveThread = null;

    volatile private int moveCount = 0;
    volatile long moveTime = 0;
    private double transSpeed = 200; // 200 mm/s
    private double rotSpeed = 90; // 90 deg/s

    private boolean posReg98Updated = false;

    private void updatePosReg98() {
        final ISysGroupPosition localPosReg98 = posReg98;
        final IRobot2 localRobot = robot;
        if (!posReg98Updated && null != localPosReg98 && null != localRobot) {
            updatePosReg(localPosReg98, localRobot);
            posReg98Updated = true;
        }
    }

    private void updatePosReg(final ISysGroupPosition localPosReg, final IRobot2 localRobot) {
        localPosReg.record();
        localPosReg.refresh();
        ICurGroupPosition curPos = localRobot.curPosition().group((short) 1, FRECurPositionConstants.frWorldDisplayType);
        IXyzWpr curXyzWpr = curPos.formats(FRETypeCodeConstants.frXyzWpr).queryInterface(IXyzWpr.class);
        IConfig curConf = curXyzWpr.config();
        IXyzWpr posRegXyzWpr = localPosReg.formats(FRETypeCodeConstants.frXyzWpr).queryInterface(IXyzWpr.class);
        IConfig posReg98XyzWprConf = posRegXyzWpr.config();
        posReg98XyzWprConf.flip(curConf.flip());
        posReg98XyzWprConf.front(curConf.front());
        posReg98XyzWprConf.left(curConf.left());
        posReg98XyzWprConf.up(curConf.up());
        posReg98XyzWprConf.turnNum((short) 1, curConf.turnNum((short) 1));
        localPosReg.update();
    }

    private boolean posReg97Updated = false;

    private void updatePosReg97() {
        final ISysGroupPosition localPosReg97 = posReg97;
        final IRobot2 localRobot = robot;
        if (null != localPosReg97 && null != localRobot) {
            localPosReg97.refresh();
            ICurPosition icp = localRobot.curPosition();
            ICurGroupPosition icgp = icp.group((short) 1, FRECurPositionConstants.frJointDisplayType);
            Com4jObject com4jobj_joint_pos = icgp.formats(FRETypeCodeConstants.frJoint);
            IJoint joint_pos = com4jobj_joint_pos.queryInterface(IJoint.class);
            final IJoint posReg97Joint = localPosReg97.formats(FRETypeCodeConstants.frJoint).queryInterface(IJoint.class);
            for (short i = 1; i <= joint_pos.count(); i++) {
                double cur_joint_pos = joint_pos.item(i);
                posReg97Joint.item(i, cur_joint_pos);
            }
            localPosReg97.update();
            posReg97Updated = true;
        }
    }

    private volatile int moveStartAbortCount = -1;
    private final AtomicInteger abortCount = new AtomicInteger();

    long expectedEndMoveToTime = -1;
    long startMoveTime = -1;

    private volatile @Nullable
    PoseType moveToStartPosition = null;

    private volatile @Nullable
    ITask lastMoveToTask = null;

    private void handleMoveTo(MoveToType moveCmd) throws PmException {

//        if(!moveCmd.getGuard().isEmpty()) {
//            MoveThroughToType moveThroughCmd = new MoveThroughToType();
//            moveThroughCmd.setCommandID(moveCmd.getCommandID());
//            moveThroughCmd.setNumPositions(10);
//            PoseType curPose = getPose();
//            for (int i = 0; i < 10; i++) {
//                PoseType pose = new PoseType();
//                pose.setXAxis(moveCmd.getEndPosition().getXAxis());
//                pose.setZAxis(moveCmd.getEndPosition().getZAxis());
//                PointType point = new PointType();
//                final PointType curPosePoint = curPose.getPoint();
//                final PointType endPoint = moveCmd.getEndPosition().getPoint();
//                point.setX(curPosePoint.getX()*((9-i)/10.0)+endPoint.getX()*((i+1)/10.0));
//                point.setY(curPosePoint.getY()*((9-i)/10.0)+endPoint.getY()*((i+1)/10.0));
//                point.setZ(curPosePoint.getZ()*((9-i)/10.0)+endPoint.getZ()*((i+1)/10.0));
//                pose.setPoint(point);
//                moveThroughCmd.getWaypoint().add(pose);
//            }
//            moveThroughCmd.setMoveStraight(true);
//            handleMoveThroughTo(moveThroughCmd);
//            return;
//        }
        if (overrideValue < 50) {
            recheckOverride();
        }
        float f = regNumeric98.regFloat();
        if (f < transSpeed && f < 0.001f) {
            regNumeric98.regFloat((float) transSpeed);
        }
        moveStartAbortCount = abortCount.get();
        moveReasons = new ArrayList<>();
        distances = new ArrayList<>();
        moveChecksDone = 0;
        CRCLStatusType localStatus = this.status.get();
        moveToStartPosition = copy(localStatus.getPoseStatus().getPose());
        posReg97Updated = false;
        setCommandState(CommandStateEnumType.CRCL_WORKING);
        PointType moveCmdEndPt = moveCmd.getEndPosition().getPoint();
        PmCartesian cart = CRCLPosemath.toPmCartesian(moveCmdEndPt);
        PmCartesian endCart = new PmCartesian(cart.x, cart.y, cart.z);
        PmRpy rpy = CRCLPosemath.toPmRpy(moveCmd.getEndPosition());
        updatePosReg98();
        final ISysGroupPosition localPosReg98 = posReg98;
        if (null == localPosReg98) {
            throw new NullPointerException("posReg98");
        }
        localPosReg98.refresh();
        IXyzWpr posReg98XyzWpr = localPosReg98.formats(FRETypeCodeConstants.frXyzWpr).queryInterface(IXyzWpr.class);
        posReg98XyzWpr.setAll(endCart.x, endCart.y, endCart.z, Math.toDegrees(rpy.r), Math.toDegrees(rpy.p), Math.toDegrees(rpy.y));
        limitAndUpdatePos(localPosReg98);
        posReg98XyzWpr = localPosReg98.formats(FRETypeCodeConstants.frXyzWpr).queryInterface(IXyzWpr.class);
        endCart.x = posReg98XyzWpr.x();
        endCart.y = posReg98XyzWpr.y();
        endCart.z = posReg98XyzWpr.z();
        double cartDiff = distTransFrom(moveCmd.getEndPosition());
        double rotDiff = distRotFrom(moveCmd.getEndPosition());
        moveCmdEndPt.setX(endCart.x);
        moveCmdEndPt.setY(endCart.y);
        moveCmdEndPt.setZ(endCart.z);
        double cartMoveTime = cartDiff / transSpeed;
        double rotMoveTime = rotDiff / rotSpeed;
        if (rotMoveTime > cartMoveTime) {
            double timeNeeded = Math.max(rotMoveTime, cartMoveTime);
            int time_needed_ms = (int) (1000.0 * timeNeeded);
            showInfo("MoveTo : cartDiff = " + cartDiff + ",rotDiff = " + rotDiff + ", transSpeed=" + transSpeed + ", time_needed_ms = " + time_needed_ms);
            if (time_needed_ms < 10) {
                time_needed_ms = 10;
            }

            final IRegNumeric localRegNumeric96 = regNumeric96;
            if (null == localRegNumeric96) {
                throw new NullPointerException("regNumeric96");
            }
            localRegNumeric96.regLong(time_needed_ms);
            final IVar localReg96Var = reg96Var;
            if (null == localReg96Var) {
                throw new NullPointerException("reg96Var");
            }
            localReg96Var.update();
            runMotionTpProgram(Objects.requireNonNull(move_w_time_prog, "move_w_time_prog"));
            expectedEndMoveToTime = System.currentTimeMillis() + time_needed_ms * (100 / overrideValue);
        } else {
            showInfo("MoveTo : cartDiff = " + cartDiff + ",rotDiff = " + rotDiff);
            expectedEndMoveToTime = System.currentTimeMillis() + ((long) (1000.0 * cartMoveTime) * (100 / overrideValue));
            if (moveCmd.getGuard().isEmpty()) {
                runMotionTpProgram(Objects.requireNonNull(move_linear_prog, "move_linear_prog"));
            } else {
                PoseType startPose = getPose();
                PointType startPoint = startPose.getPoint();
                final double s = 1.0 / posReg80Array.length;
                for (int i = 0; i < posReg80Array.length; i++) {
                    IXyzWpr posRegIXyzWpr = posReg80Array[i].formats(FRETypeCodeConstants.frXyzWpr).queryInterface(IXyzWpr.class);
                    final double startScale = (9 - i) * s;
                    final double endScale = (i + 1) * s;
                    posRegIXyzWpr.setAll(
                            startPoint.getX() * startScale + endCart.x * endScale,
                            startPoint.getY() * startScale + endCart.y * endScale,
                            startPoint.getZ() * startScale + endCart.z * endScale,
                            Math.toDegrees(rpy.r), Math.toDegrees(rpy.p), Math.toDegrees(rpy.y));
                    limitAndUpdatePos(posReg80Array[i]);
                }
                runMotionTpProgram(Objects.requireNonNull(move_linear10_prog, "move_linear10_prog"));
            }
        }
        startMoveTime = System.currentTimeMillis();
        ITask motTask = getRunningTpTask();
        System.out.println("motTask = " + motTask);
        lastMoveToTask = motTask;
    }

    private volatile @Nullable
    Iterator<Com4jObject> tasksIterator = null;
    private volatile @Nullable
    List<Object[]> getTaskListOutput = null;

    public Optional<List<Object[]>> getTaskList(boolean showAborted) {
        if (null == tasksIterator || null == getTaskListOutput) {
            final IRobot2 localRobot = robot;
            if (null == localRobot) {
                return Optional.empty();
            }
            ITasks tasks = localRobot.tasks();
            getTaskListOutput = new ArrayList<>();
            if (null != tasks) {
                tasksIterator = tasks.iterator();
                getTaskListOutput = new ArrayList<>();
            }
            return Optional.empty();
        } else {
            if (!tasksIterator.hasNext()) {
                tasksIterator = null;
                Optional<List<Object[]>> ret = Optional.ofNullable(getTaskListOutput);
                getTaskListOutput = null;
                return ret;
            }
            Com4jObject c4jo = tasksIterator.next();
            if (null == c4jo) {
                tasksIterator = null;
                Optional<List<Object[]>> ret = Optional.ofNullable(getTaskListOutput);
                getTaskListOutput = null;
                return ret;
            } else {
                ITask tsk = null;
                String tskProgName = null;
                FREProgramTypeConstants pType = null;
                FRETaskStatusConstants tskStatus = null;
                try {
                    tsk = c4jo.queryInterface(ITask.class);
                    try {
                        tskStatus = tsk.status();
                    } catch (Exception e) {
                    }
                    if (!showAborted && tskStatus == FRETaskStatusConstants.frStatusAborted) {
                        return Optional.empty();
                    }
                    try {
                        pType = tsk.programType();
                    } catch (Exception e) {
                    }
                    try {
                        IProgram tskProg = tsk.curProgram();
                        if (null != tskProg) {
                            tskProgName = tskProg.name();
                        }
                    } catch (Exception e) {
                    }
                } catch (ComException e) {
                    e.printStackTrace();
                    showError(e.toString());
                    return Optional.empty();
                }

                if (null == tskProgName && null == pType && null == tskStatus) {
                    return Optional.empty();
                }
                if (null != getTaskListOutput) {
                    getTaskListOutput.add(new Object[]{
                        tskProgName == null ? "" : tskProgName,
                        pType == null ? "" : pType.toString(),
                        tskStatus == null ? "" : tskStatus.toString()
                    });
                }
                return Optional.empty();
            }
        }
    }

    long lastRunMotionTpTime = 0;

    private @Nullable
    ITPProgram lastRunMotionProgram = null;

    private volatile long timeToWaitForLastMotionProgram = 0;
    private volatile long timeToStartMotionProgram = 0;
    private volatile int lastMotionProgramRunningCount = 0;

    public synchronized void runMotionTpProgram(final ITPProgram program) {
        boolean program_started = false;
        int count = 0;
        long start = System.currentTimeMillis();
        CommandStateEnumType state = origState;
        int motionProgramRunningCount = 0;
        if (start - moveDoneTime < 100) {
            while (lastMotionProgramRunning()) {
//            System.err.println("waiting for lastMotionProgramRunning");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    getLocalLogger().log(Level.SEVERE, null, ex);
                }
                motionProgramRunningCount++;
            }
        }
        lastMotionProgramRunningCount = motionProgramRunningCount;
        timeToWaitForLastMotionProgram = System.currentTimeMillis() - start;
        while (!program_started) {
            try {
                count++;
                program.run(FREStepTypeConstants.frStepNone, 1, FREExecuteConstants.frExecuteFwd);
                program_started = true;
            } catch (Exception e) {
                long curtime = System.currentTimeMillis();
                System.err.println("count=" + count);
                System.err.println("state=" + state);
                System.err.println("time since move =" + (curtime - moveTime));
                System.err.println("time since move done=" + (curtime - moveDoneTime));
                System.err.println("time since last command=" + (curtime - lastRunMotionTpTime));
                System.err.println("time since start=" + (curtime - start));
                getLocalLogger().log(Level.SEVERE, null, e);
                getTaskList(false).ifPresent(taskList -> {
                    taskList.forEach((Object[] objects) -> logDebug(Arrays.toString(objects)));
                });
                if (count > 3) {
                    showError(e.toString());
                    return;
                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    getLocalLogger().log(Level.SEVERE, null, ex);
                }
            }
        }
        lastRunMotionProgram = program;
        lastRunMotionTpTime = System.currentTimeMillis();
        timeToStartMotionProgram = lastRunMotionTpTime - start;
        String progName = program.name();
        if (null != progName && progName.length() > 1) {
            prognamesNeeded.add(progName);
            logDebug("progName = " + progName);
            logDebug("prognamesNeeded = " + prognamesNeeded);
        }
    }
    public static final long MOVE_INTERVAL_MILLIS = 100;
    private int currentWaypointNumber = 0;

    public double distTransFrom(PoseType pose) {
        CRCLStatusType localStatus = this.status.get();
        PmCartesian cart = CRCLPosemath.toPmCartesian(Objects.requireNonNull(CRCLPosemath.getNullablePoint(localStatus)));
        return cart.distFrom(CRCLPosemath.toPmCartesian(pose.getPoint()));
    }

    public double distRotFrom(PoseType pose) throws PmException {
        PmRotationVector rotvCurrent = CRCLPosemath.toPmRotationVector(getPose());
        PmRotationVector rotvArg = CRCLPosemath.toPmRotationVector(pose);
        PmRotationVector rotvDiff = rotvArg.multiply(rotvCurrent.inv());
        return Math.toDegrees(rotvDiff.s);
    }

    private void moveToGroupPos() throws InterruptedException {
        boolean didit = false;
        int tries = 0;
        long t0 = System.currentTimeMillis();
        final IIndGroupPosition localGroupPos = Objects.requireNonNull(groupPos, "groupPos");
        do {
            tries++;
            try {
                localGroupPos.moveto();
                didit = true;
            } catch (ComException comEx) {
                showComException(comEx);
                Thread.sleep(20);
            }
        } while (!didit && !Thread.currentThread().isInterrupted() && (System.currentTimeMillis() - t0) < 500);
        long t1 = System.currentTimeMillis();
        long diff = (t1 - t0);
        if (diff > 500 && !didit) {
            showError("Timed out trying to send moveto command. tried " + tries + " times over " + diff + " ms");
        }
    }

    private void handleMoveThroughTo(MoveThroughToType moveCmd) throws PmException {
        posReg97Updated = false;
        final int handleMoveThroughStartingAbortCount = abortCount.get();
        this.moveStartAbortCount = handleMoveThroughStartingAbortCount;
        setCommandState(CommandStateEnumType.CRCL_WORKING);
        final Thread initialMoveThread = moveThread;
        if (initialMoveThread != null) {
            try {
                initialMoveThread.join(200);
            } catch (InterruptedException ex) {
                getLocalLogger().log(Level.SEVERE, null, ex);
            }
            initialMoveThread.interrupt();
            try {
                initialMoveThread.join(200);
            } catch (InterruptedException ex) {
                getLocalLogger().log(Level.SEVERE, null, ex);
            }
            this.moveThread = null;
        }

        moveCount++;
        final Thread localMoveThread = new Thread(() -> {
            try {
                for (currentWaypointNumber = 0; currentWaypointNumber < moveCmd.getNumPositions() && currentWaypointNumber < moveCmd.getWaypoint().size(); currentWaypointNumber++) {
                    PoseType pose = moveCmd.getWaypoint().get(currentWaypointNumber);
                    PmCartesian cart = CRCLPosemath.toPmCartesian(pose.getPoint());
                    PmRpy rpy = CRCLPosemath.toPmRpy(pose);
                    final IRobot2 localRobot = Objects.requireNonNull(robot);
                    ICurPosition icp = localRobot.curPosition();
                    ICurGroupPosition icgp = icp.group((short) 1, FRECurPositionConstants.frWorldDisplayType);
                    Com4jObject com4jobj_pos = icgp.formats(FRETypeCodeConstants.frXyzWpr);
                    IXyzWpr pos = com4jobj_pos.queryInterface(IXyzWpr.class);
                    final IIndGroupPosition localGroupPos = Objects.requireNonNull(groupPos, "groupPos");
                    Com4jObject com4jobj_sys_pos = localGroupPos.formats(FRETypeCodeConstants.frXyzWpr);
                    IXyzWpr sys_pos = com4jobj_sys_pos.queryInterface(IXyzWpr.class);
                    long t0 = System.currentTimeMillis();
                    long t1 = System.currentTimeMillis();
                    sys_pos.setAll(cart.x, cart.y, cart.z,
                            Math.toDegrees(rpy.r), Math.toDegrees(rpy.p), Math.toDegrees(rpy.y));
                    localGroupPos.update();
                    moveToGroupPos();
                    if (Thread.currentThread().isInterrupted()) {
                        return;
                    }
                    if (handleMoveThroughStartingAbortCount != abortCount.get()) {
                        return;
                    }
                    Thread.sleep(40);
                    double dist = distTransFrom(pose);
                    double distRot = distRotFrom(pose);
                    while (dist > distanceTolerance
                            || distRot > distanceRotTolerance
                            || !localGroupPos.isAtCurPosition()
                            || !checkCurrentTasks()) {
                        if (Thread.currentThread().isInterrupted()) {
                            return;
                        }
                        Thread.sleep(20);
                        dist = distTransFrom(pose);
                    }
                    long t5 = System.currentTimeMillis();
                    moveTime = t5;
                    Thread.sleep(20);
                }
            } catch (InterruptedException ex) {
            } catch (ComException | PmException e) {
                e.printStackTrace();
                System.err.println("Time since moveTime = " + (System.currentTimeMillis() - moveTime));
                System.err.println("Time since moveDoneTime = " + (System.currentTimeMillis() - moveDoneTime));
                showError(e.toString());
            }
        }, "moveThread" + moveCount);
        localMoveThread.start();
        this.moveThread = localMoveThread;
    }
    public double distanceTolerance = 1.0; // millimeter
    public double distanceRotTolerance = 0.25; // degrees

//    private void handleSetLengthUnits(SetLengthUnitsType slu) {
//        this.setLengthUnit(slu.getUnitName());
//        setCommandState(CommandStateEnumType.CRCL_DONE);
//        settingsStatus.setLengthUnitName(slu.getUnitName());
//    }
    private void handleSetEndPoseTolerance(SetEndPoseToleranceType sepCmd) {
        PoseToleranceType poseTol = sepCmd.getTolerance();
        distanceTolerance = Math.min(poseTol.getXPointTolerance(),
                Math.min(poseTol.getXPointTolerance(),
                        poseTol.getZPointTolerance()));
        setCommandState(CommandStateEnumType.CRCL_DONE);
        CRCLStatusType stat = this.status.get();
        final SettingsStatusType settingsStatus = stat.getSettingsStatus();
        settingsStatus.setEndPoseTolerance(sepCmd.getTolerance());
    }

    long dwellEndTime = 0;

    private void handleDwell(DwellType dwellCmd) {
        dwellEndTime = System.currentTimeMillis() + ((long) (dwellCmd.getDwellTime() * 1000.0 + 1.0));
//        logDebug("dwellEndTime = " + dwellEndTime);
        setCommandState(CommandStateEnumType.CRCL_WORKING);
    }

    private void setCommandState(CommandStateEnumType newState) {
        crclServerSocket.setCommandStateEnum(newState);
        CRCLStatusType localStatus = this.status.get();
        if (null == localStatus.getCommandStatus()) {
            localStatus.setCommandStatus(new CommandStatusType());
            if (null != crclServerSocket && null != serverSocketStatus) {
                CRCLStatusType localServerSocketStatus = this.serverSocketStatus.get();
                synchronized (crclServerSocket) {
                    localServerSocketStatus.setCommandStatus(localStatus.getCommandStatus());
                }
            }
        } else if (localStatus.getCommandStatus().getCommandState() != newState) {
            localStatus.getCommandStatus().setStateDescription("");
        }
        if (checkSafetyStatError()) {
            newState = CommandStateEnumType.CRCL_ERROR;
        }
        CommandStatusType cmdStatus = localStatus.getCommandStatus();
        if (null != cmdStatus) {
            if (null != crclServerSocket && null != serverSocketStatus) {
                synchronized (crclServerSocket) {
                    cmdStatus.setCommandState(newState);
                }
            } else {
                cmdStatus.setCommandState(newState);
            }
//            if (newState != CommandStateEnumType.CRCL_ERROR && null != prevCmd) {
//                cmdStatus.setStateDescription(prevCmd.getClass().getName());
//            }
        }
    }

    private @MonotonicNonNull
    ConfigureJointReportsType cjrs = null;

    private @Nullable
    Map<Integer, ConfigureJointReportType> cjrMap = null;

    @SuppressWarnings("initialization")
    private void setDefaultJointReports() {
        final Map<Integer, ConfigureJointReportType> initialCjrMap = this.cjrMap;
        final Map<Integer, ConfigureJointReportType> localCjrMap;
        if (null == initialCjrMap) {
            localCjrMap = new HashMap<>();
            this.cjrMap = localCjrMap;
        } else {
            localCjrMap = initialCjrMap;
        }

        for (int i = 1; i <= 6; i++) {
            ConfigureJointReportType cjr = new ConfigureJointReportType();
            cjr.setReportPosition(true);
            cjr.setReportVelocity(true);
            cjr.setJointNumber(i);
            localCjrMap.put(i,
                    cjr);
        }
//        setReportJointStatus(true);
//        setReportPoseStatus(true);
//        setReportSettingsStatus(true);
    }

//    private void handleConfigureStatusReport(ConfigureStatusReportType cmd) {
//        setReportJointStatus(cmd.isReportJointStatuses());
//        setReportPoseStatus(cmd.isReportPoseStatus());
//        setReportSettingsStatus(cmd.isReportSettingsStatus());
//        setCommandState(CommandStateEnumType.CRCL_WORKING);
//    }
    private void handleMessage(MessageType cmd) {
        logInfoString(cmd.getMessage());
        setCommandState(CommandStateEnumType.CRCL_DONE);
    }

    private void handleConfigureJointReports(ConfigureJointReportsType cmd) {
        cjrs = (ConfigureJointReportsType) cmd;
        final Map<Integer, ConfigureJointReportType> initialCjrMap = this.cjrMap;
        final Map<Integer, ConfigureJointReportType> localCjrMap;
        if (cjrs.isResetAll() || null == initialCjrMap) {
            localCjrMap = new HashMap<>();
            this.cjrMap = localCjrMap;
        } else {
            localCjrMap = initialCjrMap;
        }
        for (ConfigureJointReportType cjr : cjrs.getConfigureJointReport()) {
            localCjrMap.put(cjr.getJointNumber(),
                    cjr);
        }
        setCommandState(CommandStateEnumType.CRCL_WORKING);
    }

    private void runTPProgram(ITPProgram prog) {
        try {
            String progName = prog.name();
            final IRobot2 localRobot = Objects.requireNonNull(robot);
            for (Com4jObject c4jo : localRobot.tasks()) {
                ITask tsk = null;
                String tskProgName = null;
                try {
                    tsk = c4jo.queryInterface(ITask.class);
                    FREProgramTypeConstants pType = tsk.programType();
                    if (pType != FREProgramTypeConstants.frTPProgramType) {
                        continue;
                    }
                    IProgram tskProg = tsk.curProgram();
                    if (null == tskProg) {
                        continue;
                    }
                    tskProgName = tskProg.name();
                    if (null == tskProgName) {
                        continue;
                    }
                } catch (ComException e) {
                    continue;
                }
                try {
                    if (tsk != null && tskProgName != null && tskProgName.equals(progName)) {
                        FRETaskStatusConstants tskStatus = tsk.status();
//                        logDebug("tskStatus = " + tskStatus);
                        if (tskStatus == FRETaskStatusConstants.frStatusRun) {
                            logDebug("aborting task with curProgram().name() = " + tskProgName);
                            tsk.abort(true, true);
                            long t0 = System.currentTimeMillis();
                            int cycles = 0;
                            while (!Thread.currentThread().isInterrupted()
                                    && (tskStatus == FRETaskStatusConstants.frStatusRun
                                    || tskStatus == FRETaskStatusConstants.frStatusAborting)) {
                                Thread.sleep(10);
                                tskStatus = tsk.status();
                                logDebug("tskStatus = " + tskStatus);
                                cycles++;
                                logDebug("cycles = " + cycles);
                            }
                            long t1 = System.currentTimeMillis();
                            boolean interrupted = Thread.currentThread().isInterrupted();
                            logDebug("interrupted = " + interrupted);
                            if (interrupted) {
                                System.exit(1);
                            }
                            logDebug("Abort took " + (t1 - t0) + " ms and " + cycles + " cycles.");
                            logDebug("tskStatus = " + tskStatus);
                        }
                    }
                } catch (InterruptedException ie) {
                    return;
                } catch (ComException comEx) {
                    showComException(comEx);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            boolean didit = false;
            int tries = 0;
            long runStartTime = System.currentTimeMillis();
            while (!didit && !Thread.currentThread().isInterrupted()) {
                try {
                    prog.run(FREStepTypeConstants.frStepNone, 1, FREExecuteConstants.frExecuteFwd);
                    if (null != progName && progName.length() > 1) {
                        prognamesNeeded.add(progName);
                        logDebug("progName = " + progName);
                        logDebug("prognamesNeeded = " + prognamesNeeded);
                    }
                    didit = true;
                } catch (Exception e) {
                    tries++;
                    Thread.sleep(10);
                }
            }
            logDebug("(System.currentTimeMillis()-runStartTime) = " + (System.currentTimeMillis() - runStartTime));
            logDebug("tries = " + tries);
        } catch (Exception e) {
            e.printStackTrace();
            logDebug("e.getMessage() = " + e.getMessage());
            prog.run(FREStepTypeConstants.frStepNone, 1, FREExecuteConstants.frExecuteFwd);
        }
    }

    private volatile long actuateJointMaxTime = -1;
    private volatile long actuateJointStartTime = -1;

    private void handleActuateJoints(ActuateJointsType ajCmd) throws PmException, InterruptedException {
        posReg98Updated = false;
        setCommandState(CommandStateEnumType.CRCL_WORKING);
        updatePosReg97();
        final ISysGroupPosition localPosReg97 = Objects.requireNonNull(posReg97);

        localPosReg97.refresh();
        final IJoint posReg97Joint = localPosReg97.formats(FRETypeCodeConstants.frJoint).queryInterface(IJoint.class);
        long max_time = 0;
        double diffs[] = new double[ajCmd.getActuateJoint().size()];
        int diffindex = 0;
        for (ActuateJointType aj : ajCmd.getActuateJoint()) {
            double val = aj.getJointPosition();
            final double origval = val;
            short number = (short) aj.getJointNumber();
            if (number < 1) {
                System.err.println("bad joint number : " + number);
                return;
            }
            if (number > this.upperJointLimits.length) {
                System.err.println("bad joint number > this.upperJointLimits.length : " + number + " > " + this.upperJointLimits);
                return;
            }
            if (number > this.lowerJointLimits.length) {
                System.err.println("bad joint number > this.lowerJointLimits.length : " + number + " > " + this.lowerJointLimits);
                return;
            }
            final double uplimit = this.upperJointLimits[number - 1];
            if (val > uplimit) {
                val = uplimit;
            }
            final double lowlimit = this.lowerJointLimits[number - 1];
            if (val < lowlimit) {
                val = lowlimit;
            }
            float curVal = (float) posReg97Joint.item(number);
            double absDiff = (double) Math.abs(val - curVal);
            if (diffindex < diffs.length) {
                diffs[diffindex] = absDiff;
                diffindex++;
            }
            double speed = DEFAULT_JOINT_SPEED;

            JointDetailsType jd = aj.getJointDetails();
            if (jd instanceof JointSpeedAccelType) {
                JointSpeedAccelType jsa = (JointSpeedAccelType) jd;
                if (jsa.getJointSpeed() != null) {
                    speed = jsa.getJointSpeed().doubleValue();
                    if (speed < Double.MIN_NORMAL) {
                        speed = Double.MIN_NORMAL;
                    }
                }
            }
            long time = (long) (1000 * absDiff / speed);
            if (time > max_time) {
                max_time = time;
            }
            posReg97Joint.item(number, val);
            localPosReg97.update();
            localPosReg97.refresh();
            double chkval = posReg97Joint.item(number);
            logDebug("Actuate joints: number=" + number + " \tval=\t" + val + " \torigval=" + origval + "\tup_limit=" + uplimit + "\tlow_limit=" + lowlimit);
            if (Math.abs(chkval - val) > 1e-4) {
                System.err.println("chkval = " + chkval);
            }
        }
        if (max_time < 10) {
            max_time = 10;
        }
        final IRegNumeric localRegNumeric97 = Objects.requireNonNull(regNumeric97);
        localRegNumeric97.regLong((int) max_time);
        localPosReg97.update();
        actuateJointMaxTime = max_time;
        logDebug("actuateJointMaxTime = " + actuateJointMaxTime);
        logDebug("diffs = " + Arrays.toString(diffs));
        actuateJointStartTime = System.currentTimeMillis();
        this.runTPProgram(Objects.requireNonNull(move_joint_prog, "move_joint_prog"));
        if (moveThread != null) {
            moveThread.interrupt();
            try {
                moveThread.join(200);
            } catch (InterruptedException ex) {
                getLocalLogger().log(Level.SEVERE, null, ex);
            }
            moveThread = null;
        }
    }

    public static final float DEFAULT_JOINT_SPEED = 10.0f;

//    private Set<String> lastAlarms;
//
//    private Set<String> getAlarms() {
//        final IRobot2 localRobot = Objects.requireNonNull(this.robot);
//        IAlarms alarms = localRobot.alarms();
//        Map<java.util.Date, String> alarmMap = new java.util.TreeMap<>();
//        java.util.Date maxResetDate = null;
//        if (null != alarms) {
//            for (Com4jObject alarm_obj : alarms) {
//                IAlarm alarm = alarm_obj.queryInterface(IAlarm.class);
//                if (null != alarm) {
//                    if (alarm.errorClass() == 1) {
//                        if (maxResetDate == null || alarm.timeStamp().after(maxResetDate)) {
//                            maxResetDate = alarm.timeStamp();
//                        }
//                    }
//                    alarmMap.put(alarm.timeStamp(), alarm.errorMessage());
//                }
//            }
//        }
//        Set<String> alarmSet = new TreeSet<>();
//        for (Com4jObject alc4jo : alarms) {
//            IAlarm alarm = alc4jo.queryInterface(IAlarm.class);
//            if (null != alarm) {
//                if (alarm.errorMotion() != 0) {
//                    if (maxResetDate == null || alarm.timeStamp().after(maxResetDate)) {
//                        alarmSet.add(alarm.errorMessage());
//                    }
//                }
//            }
//        }
//        return alarmSet;
//    }
    public @Nullable
    FanucCRCLServerDisplayInterface getDisplayInterface() {
        return displayInterface;
    }

    public void setDisplayInterface(FanucCRCLServerDisplayInterface displayInterface) {
        this.displayInterface = displayInterface;
    }

    private volatile @Nullable
    CRCLCommandType prevCmd = null;

    private void setPrevCmd(@Nullable CRCLCommandType cmd) {
        this.prevCmd = cmd;
    }

    final CRCLSocket utilCrclSocket;

    @SuppressWarnings("initialization")
    public FanucCRCLMain() {
        try {
            utilCrclSocket = new CRCLSocket();
            CRCLStatusType stat = this.status.get();
            CRCLPosemath.setPose(stat, CRCLPosemath.identityPose());
            crclServerSocket = new CRCLServerSocket<>(FANUC_STATE_GENERATOR);
            crclServerSocket.addListener(crclSocketEventListener);
            setDefaultJointReports();
        } catch (Exception exception) {
            getLocalLogger().log(Level.SEVERE, "", exception);
            if (exception instanceof RuntimeException) {
                throw (RuntimeException) exception;
            } else {
                throw new RuntimeException(exception);
            }
        }
    }

    private boolean keepMoveToLog = false;

    /**
     * Get the value of keepMoveToLog
     *
     * @return the value of keepMoveToLog
     */
    public boolean isKeepMoveToLog() {
        return keepMoveToLog;
    }

    /**
     * Set the value of keepMoveToLog
     *
     * @param keepMoveToLog new value of keepMoveToLog
     */
    public void setKeepMoveToLog(boolean keepMoveToLog) {
        this.keepMoveToLog = keepMoveToLog;
        if (!keepMoveToLog) {
            closeMoveToLogFile();
        }
    }

    private void openMoveToLogFile() {
        try {
            File directory = null;
//            if (null != propertiesFile) {
//                directory = propertiesFile.getParentFile();
//            }
            if (null == moveLogFile || null == moveLogFilePrintStream) {
                if (null == directory) {
                    moveLogFile = File.createTempFile("fanucCrclMoveLog_" + getDateTimeString() + "_", ".csv");
                } else {
                    moveLogFile = File.createTempFile("fanucCrclMoveLog_" + getDateTimeString() + "_", ".csv", directory);
                }
                moveLogFilePrintStream = new PrintStream(new FileOutputStream(moveLogFile));
                moveLogFilePrintStream.println("current_time_ms,current_time_string,expectedEndMoveTimeDiff,id,start_x,start_y,start_z,end_x,end_y,end_z,distTran,distRot,moveTime,moveCheckCount,cmdTransSpeed,cmdRotSpeed,realTransSpeed,realRotSpeed,timeToWaitForLastMotionProgram,timeToStartMotionProgram,lastMotionProgramRunningCount,distances,reasons,updateTimes");
            }
        } catch (IOException ex) {
            getLocalLogger().log(Level.SEVERE, null, ex);
        }
    }

    public void closeMoveToLogFile() {
        try {
            PrintStream ps = moveLogFilePrintStream;
            moveLogFile = null;
            moveLogFilePrintStream = null;
            if (null != ps) {
                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long lastUpdateStatusTime = -1;

    private synchronized void updateStatus(CRCLSocket cs) {
        try {
            XFuture<CRCLStatusType> statusFuture
                    = readCachedStatusFromRobot();
            statusFuture
                    .thenAccept((CRCLStatusType suppliedStatus) -> {
                        try {
                            synchronized (suppliedStatus) {
                                if (suppliedStatus.getCommandStatus() == null) {
                                    suppliedStatus.setCommandStatus(new CommandStatusType());
                                }
                                CommandStatusType commandStatus = suppliedStatus.getCommandStatus();
                                if (null != commandStatus) {
                                    if (commandStatus.getCommandID() < 1) {
                                        commandStatus.setCommandID(1);
                                    }
                                    if (commandStatus.getStatusID() < 1) {
                                        commandStatus.setStatusID(1);
                                    }
                                    if (null == suppliedStatus.getCommandStatus().getCommandState()) {
                                        setCommandState(CommandStateEnumType.CRCL_WORKING);
                                    }
                                }
                                cs.writeStatus(suppliedStatus, validate);
                            }
                            copyToServerSocketStatus();
                        } catch (Exception exception) {
                            showError(exception.toString());
                        }
                    });
        } catch (Throwable t) {
            showError(t.toString());
        }
    }

    private CommandStateEnumType origState = CommandStateEnumType.CRCL_DONE;

    private long totalHandleCommandTime = 0;
    private long maxHandleCommandTime = 0;
    private long totalUpdateStatusTime = 0;
    private long maxUpdateStatusTime = 0;
    private long handleCommandCount = 0;
    private long updateStatusCount = 0;
    private volatile long cmdStartTime = -1;

    private void updatePerformance() {
        if (handleCommandCount > 0 && updateStatusCount > 0) {
            String extra = "";
            if (prevCmd instanceof ActuateJointsType) {
//                logDebug("actuateJointMaxTime = " + actuateJointMaxTime);
                long time_running = System.currentTimeMillis() - actuateJointStartTime;
//                logDebug("time_running = " + time_running);
                extra = "actuateJointMaxTime = " + actuateJointMaxTime + ",time_running = " + time_running;
            }
            if (null != displayInterface) {
                displayInterface.updatePerformanceString(
                        "Performance: Commands: "
                        + handleCommandCount
                        + " maxTime=" + maxHandleCommandTime + " (ms),"
                        + " avgTime=" + (totalHandleCommandTime / handleCommandCount) + "(ms)"
                        + " Status: "
                        + updateStatusCount
                        + " maxTime=" + maxUpdateStatusTime + "(ms),"
                        + " avgTime=" + (totalUpdateStatusTime / updateStatusCount) + "(ms),"
                        + " TimeSinceCommandStart=" + (System.currentTimeMillis() - cmdStartTime) + extra
                );
            }
        }
    }

//    private void handleClient(CRCLSocket cs) {
//        try {
//            while (!Thread.currentThread().isInterrupted()) {
//                try {
//                    CRCLCommandInstanceType cmdInstance = cs.readCommand(validate);
//                    handleClientCommand(cmdInstance, cs);
//                } catch (SocketException | EOFException se) {
//                    // probably just closing the connection.
//                    break;
//                } catch (ComException comEx) {
//                    showComException(comEx);
//                    disconnectRemoteRobot();
//                    connectRemoteRobot();
//                }
//            }
//        } catch (SocketException | EOFException se) {
//            // probably just closing the connection.
//        } catch (Exception ex) {
//            Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
//            showError(ex.getMessage());
//        } finally {
//            try {
//                logDebug("Closing connection with " + cs.getInetAddress() + ":" + cs.getPort());
//                clients.remove(cs);
//                cs.close();
//                logDebug("clients = " + clients);
//            } catch (IOException ex) {
//                Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
    private void handleClientCommand(CRCLCommandInstanceType cmdInstance, CRCLSocket cs) throws SAXException, InterruptedException, ExecutionException, JAXBException, IOException, ParserConfigurationException, CRCLException {
        long readRetTime = System.currentTimeMillis();
        final CRCLCommandType cmd = cmdInstance.getCRCLCommand();
        if (cmd instanceof GetStatusType) {
            updateStatus(cs);
            long updateStatusEndTime = System.currentTimeMillis();
            long updateStatusTimeDiff = updateStatusEndTime - readRetTime;
            totalUpdateStatusTime += updateStatusTimeDiff;
            if (updateStatusTimeDiff > maxUpdateStatusTime) {
                maxUpdateStatusTime = updateStatusTimeDiff;
            }
            updateStatusCount++;
        } else {
            try {
                if (null != this.displayInterface && this.displayInterface.getjCheckBoxLogAllCommands().isSelected()) {
                    logInfoString(utilCrclSocket.cmdToString(cmd, 18, 70) + " recieved.");
                    final String lastCommandString = cs.getLastCommandString();
                    if (null != lastCommandString) {
                        logInfoString(lastCommandString);
                    }
                }
                CompletableFuture.runAsync(() -> handleCommand(cmdInstance), Objects.requireNonNull(robotService)).get();
                long handleCommandEndTime = System.currentTimeMillis();
                long handleCommandTimeDiff = handleCommandEndTime - readRetTime;
                totalHandleCommandTime += handleCommandTimeDiff;
                if (handleCommandTimeDiff > maxHandleCommandTime) {
                    maxHandleCommandTime = handleCommandTimeDiff;
                }
                handleCommandCount++;
            } catch (ComException comEx) {
                showError(comEx.getMessage() + " : cmd=" + utilCrclSocket.cmdToString(cmd, 18, 70));
                getLocalLogger().log(Level.SEVERE, "cmd=" + utilCrclSocket.commandToPrettyString(cmd), comEx);
                disconnectRemoteRobot();
                connectRemoteRobot();
            }
        }
        updatePerformance();
    }

    private final AtomicInteger startCrclServerCount = new AtomicInteger();

    private void wrappedStartCrclServer(StackTraceElement startCallerTrace @Nullable []) {
        int startStartCrclServerCount = startCrclServerCount.incrementAndGet();
        try {
            startCrclServer(startCallerTrace);
        } catch (IOException ex) {
            getLocalLogger().log(Level.SEVERE, null, ex);
            System.err.println("localPort = " + localPort);
            System.err.println("startStartCrclServerCount = " + startStartCrclServerCount);
            System.err.println("startCrclServerCount.get() = " + startCrclServerCount.get());
            throw new RuntimeException(ex);
        }
    }

    private void runUpdateCachedStatus() {
        try {
            readCachedStatusFromRobot();
        } catch (Exception ex) {
            getLocalLogger().log(Level.SEVERE, "", ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
    }

    public void startCrclServer() throws IOException {
        startCrclServer(null);
    }

//    Future<?> crclServerFuture = null;
    public synchronized void startCrclServer(StackTraceElement startCallerTrace @Nullable []) throws IOException {
//        stopCrclServer();
//        es = Executors.newCachedThreadPool(daemonThreadFactory);
//        ss = new ServerSocket(localPort);
//        crclServerFuture = es.submit(() -> {
//            while (!Thread.currentThread().isInterrupted()) {
//                try {
//                    Thread.currentThread().setName("FanucCRCL.acceptClients.ss=" + ss);
//                    CRCLSocket cs = new CRCLSocket(ss.accept());
//                    clients.add(cs);
//                    es.submit(() -> {
//                        Thread.currentThread().setName("FanucCRCL.handleClient.cs=" + cs);
//                        handleClient(cs);
//                    });
//                } catch (IOException ex) {
//                    Logger.getLogger(FanucCRCLMain.class.getName()).log(Level.SEVERE, null, ex);
//                    return;
//                }
//            }
//        });
        crclServerSocket.setPort(localPort);
        crclServerSocket.setThreadNamePrefix("FanucCRCLServer");
        this.status.releaseLockThread();
        CRCLStatusType localStatus = this.status.get();
        if (null == localStatus.getCommandStatus()) {
            localStatus.setCommandStatus(new CommandStatusType());
        }
        if (null == localStatus.getSettingsStatus()) {
            localStatus.setSettingsStatus(new SettingsStatusType());
        }
        if (null == localStatus.getSensorStatuses()) {
            localStatus.setSensorStatuses(new SensorStatusesType());
        }
        if (null == localStatus.getGuardsStatuses()) {
            localStatus.setGuardsStatuses(new GuardsStatusesType());
        }
        this.serverSocketStatus.releaseLockThread();
        this.status.releaseLockThread();
        readCachedStatusFromRobot();
        this.serverSocketStatus.releaseLockThread();
        this.status.releaseLockThread();
        CRCLStatusType localServerSocketStatus = this.serverSocketStatus.get();
        localServerSocketStatus.setCommandStatus(localStatus.getCommandStatus());
        localServerSocketStatus.setSettingsStatus(localStatus.getSettingsStatus());
        localServerSocketStatus.setSensorStatuses(localStatus.getSensorStatuses());
        localServerSocketStatus.setGuardsStatuses(localStatus.getGuardsStatuses());
        crclServerSocket.setServerSideStatus(this.serverSocketStatus);
        crclServerSocket.setUpdateStatusSupplier(this::readCachedStatusFromRobot);
        crclServerSocket.setAutomaticallySendServerSideStatus(true);
        crclServerSocket.setAutomaticallyConvertUnits(true);
        crclServerSocket.setServerUnits(new UnitsTypeSet());
        crclServerSocket.start(startCallerTrace);
    }

    private boolean robotIsConnected = false;

    public synchronized void handleCommand(CRCLCommandInstanceType cmdInstance) {
        CRCLCommandType cmd = cmdInstance.getCRCLCommand();
        cmdStartTime = System.currentTimeMillis();
        try {
//            copyFromServerSocketStatus();
            CRCLStatusType localStatus = this.status.get();
            synchronized (localStatus) {
                if (null == localStatus.getCommandStatus()) {
                    localStatus.setCommandStatus(new CommandStatusType());
                    crclServerSocket.setCommandStateEnum(CommandStateEnumType.CRCL_ERROR);
                    localStatus.getCommandStatus().setCommandState(CommandStateEnumType.CRCL_ERROR);
                }
                CommandStatusType cst = localStatus.getCommandStatus();
                if (null != cst) {
                    origState = cst.getCommandState();
                    if (cst.getCommandState() == CommandStateEnumType.CRCL_DONE) {
                        setCommandState(CommandStateEnumType.CRCL_WORKING);
                    }
                    cst.setCommandID(cmd.getCommandID());
                    cst.setStatusID(1);
                    cst.setProgramFile(cmdInstance.getProgramFile());
                    cst.setProgramIndex(cmdInstance.getProgramIndex());
                    cst.setProgramLength(cmdInstance.getProgramLength());
                }
            }
            lastCheckAtPosition = false;
            if (cmd instanceof StopMotionType) {
                handleStopMotion((StopMotionType) cmd);
                setPrevCmd(cmd);
                return;
            }
            if (null == robot || !robotIsConnected || null == groupPos) {
                showError(utilCrclSocket.cmdToString(cmd, 18, 70) + " recieved when robot not connected or not initialized.");
                return;
            }
            if (localStatus.getCommandStatus().getCommandState() != CommandStateEnumType.CRCL_ERROR) {
                localStatus.getCommandStatus().setStateDescription("");
            }
            updateTimes = new ArrayList<>();
            if (cmd instanceof InitCanonType) {
                handleInitCanon((InitCanonType) cmd);
            } else if (cmd instanceof EndCanonType) {
                handleEndCanon((EndCanonType) cmd);
            } else if (cmd instanceof MoveToType) {
                handleMoveTo((MoveToType) cmd);
            } else if (cmd instanceof MoveThroughToType) {
                handleMoveThroughTo((MoveThroughToType) cmd);
            } else if (cmd instanceof SetEndEffectorType) {
                handleSetEndEffector((SetEndEffectorType) cmd);
            } else if (cmd instanceof OpenToolChangerType) {
                handleOpenToolChanger((OpenToolChangerType) cmd);
            } else if (cmd instanceof CloseToolChangerType) {
                handleCloseToolChanger((CloseToolChangerType) cmd);
            } else if (cmd instanceof SetAngleUnitsType) {
                handleSetAngleUnits((SetAngleUnitsType) cmd);
            } else if (cmd instanceof SetForceUnitsType) {
                handleSetForceUnits((SetForceUnitsType) cmd);
            } else if (cmd instanceof SetTransSpeedType) {
                handleSetTransSpeed((SetTransSpeedType) cmd);
            } else if (cmd instanceof SetRotSpeedType) {
                handleSetRotSpeed((SetRotSpeedType) cmd);
            } else if (cmd instanceof ActuateJointsType) {
                handleActuateJoints((ActuateJointsType) cmd);
            } else if (cmd instanceof SetLengthUnitsType) {
                setCommandState(CommandStateEnumType.CRCL_DONE);
//                handleSetLengthUnits((SetLengthUnitsType) cmd);
            } else if (cmd instanceof SetEndPoseToleranceType) {
                handleSetEndPoseTolerance((SetEndPoseToleranceType) cmd);
            } else if (cmd instanceof DwellType) {
                handleDwell((DwellType) cmd);
            } else if (cmd instanceof ConfigureJointReportsType) {
                handleConfigureJointReports((ConfigureJointReportsType) cmd);
//            } else if (cmd instanceof ConfigureStatusReportType) {
//                handleConfigureStatusReport((ConfigureStatusReportType) cmd);
            } else if (cmd instanceof MessageType) {
                handleMessage((MessageType) cmd);
            } else {
                showError("Unimplemented  command :" + cmd.getClass().getSimpleName());
            }
        } catch (Exception ex) {
            showError(ex.getMessage());
            getLocalLogger().log(Level.SEVERE, "handle command", ex);
        }
        copyToServerSocketStatus();
        setPrevCmd(cmd);
    }

    private static Logger getLocalLogger() {
        return Logger.getLogger(FanucCRCLMain.class.getName());
    }

    private @Nullable
    IRobot2 robot = null;

    private @MonotonicNonNull
    IIndGroupPosition groupPos = null;

    private @MonotonicNonNull
    ITPProgram close_gripper_prog = null;
    private @MonotonicNonNull
    ITPProgram open_gripper_prog = null;
    private @MonotonicNonNull
    ITPProgram move_linear_prog = null;
    private @MonotonicNonNull
    ITPProgram move_linear10_prog = null;
    private @MonotonicNonNull
    ITPProgram move_w_time_prog = null;
    private @MonotonicNonNull
    ITPProgram move_joint_prog = null;
    private @MonotonicNonNull
    ITPProgram tool_open_prog = null;
    private @MonotonicNonNull
    ITPProgram tool_close_prog = null;
    private @MonotonicNonNull
    IVar overrideVar = null;

    private @MonotonicNonNull
    IVar morSafetyStatVar = null;
    private @MonotonicNonNull
    IVar moveGroup1RobMoveVar = null;
    private @MonotonicNonNull
    IVar moveGroup1ServoReadyVar = null;

    private long isMovingLastCheckTime = 0;
    private boolean lastIsMoving = false;

    private @Nullable
    File propertiesFile = null;

    /**
     * Get the value of propertiesFile
     *
     * @return the value of propertiesFile
     */
    public @Nullable
    File getPropertiesFile() {
        return propertiesFile;
    }

    /**
     * Set the value of propertiesFile
     *
     * @param propertiesFile new value of propertiesFile
     */
    public void setPropertiesFile(File propertiesFile) {
        this.propertiesFile = propertiesFile;
        setJointLimitsFile(new File(propertiesFile.getParentFile(), "fanucCRLCJointLimits.txt"));
        setCartLimitsFile(new File(propertiesFile.getParentFile(), "fanucCRLCCartLimits.txt"));
    }

    public void loadProperties() {
        if (null != this.propertiesFile && propertiesFile.exists()) {
            Properties props = new Properties();
            try (FileReader reader = new FileReader(propertiesFile)) {
                props.load(reader);
                String keepMoveToLogString = (String) props.get("keepMoveToLog");
                if (null != keepMoveToLogString) {
                    keepMoveToLog = Boolean.parseBoolean(keepMoveToLogString);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        readAndApplyUserCartLimits();
        readAndApplyUserJointLimits();
    }

    public @Nullable
    File getMoveLogFile() {
        return moveLogFile;
    }

    public void saveProperties() {
        final File locaPropertiesFile = propertiesFile;
        if (null != locaPropertiesFile) {
            Properties props = new Properties();
            props.put("keepMoveToLog", Boolean.valueOf(keepMoveToLog));
            try (FileWriter fw = new FileWriter(locaPropertiesFile)) {
                props.store(fw, "");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        saveCartLimits(new PmCartesian(xMin, yMin, zMin), new PmCartesian(xMax, yMax, zMax));
        saveJointLimits(this.lowerJointLimits, this.upperJointLimits);
    }

    public boolean isMoving() {
        if (System.currentTimeMillis() - isMovingLastCheckTime < 20) {
            return lastIsMoving;
        }
        if (null != moveGroup1RobMoveVar) {
            moveGroup1RobMoveVar.refresh();
            Object val = moveGroup1RobMoveVar.value();
            if (val instanceof Boolean) {
                lastIsMoving = (Boolean) val;
            }
            isMovingLastCheckTime = System.currentTimeMillis();
            return lastIsMoving;
        }
        return false;
    }

    private @Nullable
    IVar reg96Var = null;
    private @Nullable
    IVar reg97Var = null;
    private @Nullable
    IVar reg98Var = null;
    private @Nullable
    IRegNumeric regNumeric96 = null;
    private @Nullable
    IRegNumeric regNumeric97 = null;
    private @Nullable
    IRegNumeric regNumeric98 = null;
    private @Nullable
    ISysGroupPosition posReg98 = null;
    private @Nullable
    ISysGroupPosition posReg97 = null;
    private @Nullable
    FanucCRCLServerDisplayInterface displayInterface = null;

    private ISysGroupPosition posReg80Array[] = new ISysGroupPosition[10];

    private @Nullable
    IRobotNeighborhood neighborhood = null;

    private String neighborhoodname = DEFAULT_AGILITY_FANUC_NEIGHBORHOOD_NAME;

    public String getNeighborhoodname() {
        return neighborhoodname;
    }

    public XFutureVoid setNeighborhoodname(String neighborhoodname) {
        if (!Objects.equals(this.neighborhoodname, neighborhoodname)) {
            this.neighborhoodname = neighborhoodname;
            final FanucCRCLServerDisplayInterface localDisplayInterface = displayInterface;
            final String localRemoteRobotHost = remoteRobotHost;
            if (null != localDisplayInterface && null != localRemoteRobotHost) {
                localDisplayInterface.getjTextFieldRobotNeighborhoodPath().setText(localRemoteRobotHost);
            }
            this.disconnectRemoteRobot();
            return this.connectRemoteRobot();
        } else {
            return XFutureVoid.completedFutureWithName("setNeighborhoodname.nochange");
        }
    }

    List<ITPProgram> tpPrograms = new ArrayList<>();

    private boolean preferRobotNeighborhood = false;

    /**
     * Get the value of preferRobotNeighborhood
     *
     * @return the value of preferRobotNeighborhood
     */
    public boolean isPreferRobotNeighborhood() {
        return preferRobotNeighborhood;
    }

    /**
     * Set the value of preferRobotNeighborhood
     *
     * @param preferRobotNeighborhood new value of preferRobotNeighborhood
     */
    public XFutureVoid setPreferRobotNeighborhood(boolean preferRobotNeighborhood) {
        if (this.preferRobotNeighborhood != preferRobotNeighborhood) {
            this.preferRobotNeighborhood = preferRobotNeighborhood;
            if (null != displayInterface) {
                displayInterface.setPreferRobotNeighborhood(preferRobotNeighborhood);
            }
            this.disconnectRemoteRobot();
            return this.connectRemoteRobot();
        } else {
            return XFutureVoid.completedFutureWithName("setPreferRobotNeighborhood.nochange");
        }
    }

    float lowerJointLimits[] = new float[]{-10000.f, -10000.f, -10000.f, -10000.f, -10000.f, -10000.f};
    float upperJointLimits[] = new float[]{10000.f, 10000.f, 10000.f, 10000.f, 10000.f, 10000.f};

    public void applyAdditionalCartLimits(PmCartesian min, PmCartesian max) {
        xMax = (float) Math.min(xMax, max.x);
        xMin = (float) Math.max(xMin, min.x);
        yMax = (float) Math.min(yMax, max.y);
        yMin = (float) Math.max(yMin, min.y);
        zMax = (float) Math.min(zMax, max.z);
        zMin = (float) Math.max(zMin, min.z);
        crclServerSocket.addToUpdateServerSideRunnables(() -> {
            CRCLStatusType stat = this.status.get();
            final SettingsStatusType settingsStatus = stat.getSettingsStatus();
            settingsStatus.setMaxCartesianLimit(point(xMax, yMax, zMax));
            settingsStatus.setMinCartesianLimit(point(xMin, yMin, zMin));
        });
    }

    public void saveCartLimits(PmCartesian min, PmCartesian max) {
        try (PrintWriter pw = new PrintWriter(cartLimitsFile)) {
            pw.println("min.x=" + min.x);
            pw.println("min.y=" + min.y);
            pw.println("min.z=" + min.z);
            pw.println("max.x=" + max.x);
            pw.println("max.y=" + max.y);
            pw.println("max.z=" + max.z);
        } catch (FileNotFoundException ex) {
            getLocalLogger().log(Level.SEVERE, null, ex);
        }
    }

    public void applyAdditionalJointLimits(float[] min, float[] max) {
        for (int i = 0; i < min.length && i < max.length && i < lowerJointLimits.length; i++) {
            lowerJointLimits[i] = min[i];
            upperJointLimits[i] = max[i];
        }
        crclServerSocket.addToUpdateServerSideRunnables(() -> {
            CRCLStatusType stat = this.status.get();
            final SettingsStatusType settingsStatus = stat.getSettingsStatus();
            settingsStatus.getJointLimits().clear();
            for (int i = 0; i < min.length && i < max.length && i < lowerJointLimits.length; i++) {
                JointLimitType jointLimit = new JointLimitType();
                jointLimit.setJointNumber(i + 1);
                jointLimit.setJointMaxPosition(Double.valueOf(upperJointLimits[i]));
                jointLimit.setJointMinPosition(Double.valueOf(lowerJointLimits[i]));
                settingsStatus.getJointLimits().add(jointLimit);
            }
        });
    }

    public void saveJointLimits(float[] min, float[] max) {
        try (PrintWriter pw = new PrintWriter(jointLimitsFile)) {
            for (int i = 0; i < max.length && i < min.length; i++) {
                pw.println("min[" + i + "]=" + min[i]);
                pw.println("max[" + i + "]=" + max[i]);
            }
        } catch (FileNotFoundException ex) {
            getLocalLogger().log(Level.SEVERE, null, ex);
        }
    }

    private File jointLimitsFile = JOINT_LIMITS_FILE;

    /**
     * Get the value of jointLimitsFile
     *
     * @return the value of jointLimitsFile
     */
    public File getJointLimitsFile() {
        return jointLimitsFile;
    }

    /**
     * Set the value of jointLimitsFile
     *
     * @param jointLimitsFile new value of jointLimitsFile
     */
    public void setJointLimitsFile(File jointLimitsFile) {
        this.jointLimitsFile = jointLimitsFile;
    }

    private File cartLimitsFile = CART_LIMITS_FILE;

    /**
     * Get the value of cartLimitsFile
     *
     * @return the value of cartLimitsFile
     */
    public File getCartLimitsFile() {
        return cartLimitsFile;
    }

    /**
     * Set the value of cartLimitsFile
     *
     * @param cartLimitsFile new value of cartLimitsFile
     */
    public void setCartLimitsFile(File cartLimitsFile) {
        this.cartLimitsFile = cartLimitsFile;
    }

    private static final File CART_LIMITS_FILE = new File(CRCLUtils.getCrclUserHomeDir(),
            ".fanucCRLCCartLimits.txt");
    private static final File JOINT_LIMITS_FILE = new File(CRCLUtils.getCrclUserHomeDir(),
            ".fanucCRLCJointLimits.txt");

    private void findString(String input, String token, Consumer<String> tailConsumer) {
        int index = input.indexOf(token);
        if (index >= 0) {
            String tail = input.substring(index + token.length());
            tailConsumer.accept(tail);
        }
    }

    private void findIndexedString(String input, String token, BiConsumer<Integer, String> tailConsumer) {
        int i0 = input.indexOf('[');
        int i1 = input.indexOf(']');
        if (i0 > 0 && i1 > i0) {
            String indexString = input.substring(i0 + 1, i1);
            int indexVal = Integer.valueOf(indexString);
            String newInput = input.substring(0, i0 + 1) + input.substring(i1);
            int index = newInput.indexOf(token);
            if (index >= 0) {
                String tail = newInput.substring(index + token.length());
                tailConsumer.accept(indexVal, tail);
            }
        }
    }

    public void readAndApplyUserCartLimits() {
        PmCartesian min = new PmCartesian(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        PmCartesian max = new PmCartesian(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        if (null != cartLimitsFile && cartLimitsFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(cartLimitsFile))) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    findString(line, "min.x=", t -> min.x = Double.parseDouble(t));
                    findString(line, "max.x=", t -> max.x = Double.parseDouble(t));
                    findString(line, "min.y=", t -> min.y = Double.parseDouble(t));
                    findString(line, "max.y=", t -> max.y = Double.parseDouble(t));
                    findString(line, "min.z=", t -> min.z = Double.parseDouble(t));
                    findString(line, "max.z=", t -> max.z = Double.parseDouble(t));
                }
            } catch (IOException ex) {
                getLocalLogger().log(Level.SEVERE, null, ex);
            }
        }
        applyAdditionalCartLimits(min, max);
    }

    public void readAndApplyUserJointLimits() {
        float min[] = new float[6];
        float max[] = new float[6];
        for (int i = 0; i < max.length; i++) {
            max[i] = Float.POSITIVE_INFINITY;
            min[i] = Float.NEGATIVE_INFINITY;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(jointLimitsFile))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                findIndexedString(line, "min[]=", (i, t) -> min[i] = Float.valueOf(t));
                findIndexedString(line, "max[]=", (i, t) -> max[i] = Float.valueOf(t));
            }
        } catch (IOException ex) {
            getLocalLogger().log(Level.SEVERE, null, ex);
        }
        applyAdditionalJointLimits(min, max);
    }

    public static ThreadFactory daemonThreadFactory
            = new ThreadFactory() {
        public Thread newThread(Runnable r) {
            Thread t = Executors.defaultThreadFactory().newThread(r);
            t.setDaemon(true);
            String oldname = t.getName();
            if (!oldname.startsWith("Fanuc")) {
                t.setName("FanucCRCL." + oldname);
            }
            return t;
        }
    };

    public XFutureVoid connectRemoteRobot() {
        return XFutureVoid.runAsync(
                "connectRemoteRobot",
                this::connectRemoteRobotInternal,
                getRobotService());
    }

    private static final List<String> programNamesToCheckList = Arrays.asList(
            "GRIPPER_OPEN",
            "MOVE_W_TIME",
            "MOVE_JOINT",
            "TOOL_OPEN",
            "TOOL_CLOSE",
            "GRIPPER_CLOSE");

    private static final HashSet<String> programNamesToCheckSet = new HashSet<>(programNamesToCheckList);

    private volatile int overrideValue = 100;

    private void setOverrideValue(int overrideValue) {
        this.overrideValue = Math.max(1, Math.min(100, overrideValue));
    }

    private volatile @Nullable
    String connectionError = null;

    private synchronized void connectRemoteRobotInternal() {
        try {
            this.lastIsMoving = false;
            this.lastRobotIsConnected = true;
            this.last_safety_stat = 0;
            this.lastServoReady = true;
            if (preferRobotNeighborhood) {
                if (null == neighborhood) {
                    logDebug("Calling createFRCRobotNeighborhood ...");
                    neighborhood = createFRCRobotNeighborhood();
                }
                IRNRobots robots = neighborhood.robots();
                for (Com4jObject c4jo : robots) {
                    IRNRobot irnrobot = c4jo.queryInterface(IRNRobot.class);
                    if (null != irnrobot) {
                        if (irnrobot.name().equals(neighborhoodname)) {
                            Com4jObject robot_object = irnrobot.robotServer();
                            this.robot = robot_object.queryInterface(IRobot2.class);
                        }
                    }
                }
                robots.dispose();
            }
            final IRobot2 initialLocalRobot = this.robot;
            final IRobot2 localRobot;
            if (null == initialLocalRobot) {
                logDebug("Calling createFRCRobot ...");
                localRobot
                        = Objects.requireNonNull(
                                com.github.wshackle.fanuc.robotserver.ClassFactory.createFRCRobot(),
                                "com.github.wshackle.fanuc.robotserver.ClassFactory.createFRCRobot()"
                        );
                this.robot = localRobot;
                logDebug("createFRCRobot returned " + localRobot);
                setPreferRobotNeighborhood(false);
            } else {
                localRobot = initialLocalRobot;
            }

            robotIsConnected = localRobot.isConnected();
            final String checkedRemoteRobotHost = Objects.requireNonNull(this.remoteRobotHost, "remoteRobotHost");

            if (!robotIsConnected) {
                logDebug("Connecting to " + checkedRemoteRobotHost + " ...");
                int tries = 0;
                localRobot.connectEx(checkedRemoteRobotHost, true, 1, 1);
                while (!localRobot.isConnected() && !Thread.currentThread().isInterrupted() && tries < 10) {
                    tries++;
                    logDebug("Connecting to " + checkedRemoteRobotHost + " ... : tries = " + tries + "/10");
                    Thread.sleep(200);
                }
                robotIsConnected = localRobot.isConnected();
                logDebug("robotIsConnected = " + robotIsConnected);
            }

            if (!robotIsConnected) {
                showError("Failed to connect to robot: " + checkedRemoteRobotHost);
                connectionError = "Failed to connect to robot: " + checkedRemoteRobotHost;
                return;
            }
            connectionError = null;
            IIndPosition iip = localRobot.createIndependentPosition(FREGroupBitMaskConstants.frGroup1BitMask);
            iip.record();
            groupPos = iip.group((short) 1);

            logDebug("Getting list of programs ...");
            IPrograms programs = localRobot.programs();
            if (null != programs) {
                synchronized (tpPrograms) {
                    clearDisplayedPrograms();
                    tpPrograms.clear();
                    for (Com4jObject com4jo_program : programs) {
                        ITPProgram program = com4jo_program.queryInterface(ITPProgram.class);
                        if (null != program) {
                            tpPrograms.add(program);
                        }
                        if (null != program && program.name().equalsIgnoreCase("GRIPPER_OPEN")) {
                            logDebug("Found open_gripper program.");
                            open_gripper_prog = program;
                        }
                        if (null != program && program.name().equalsIgnoreCase("MOVE_W_TIME")) {
                            logDebug("Found MOVE_W_TIME program.");
                            move_w_time_prog = program;
                        }
                        if (null != program && program.name().equalsIgnoreCase("MOVE_LINEAR")) {
                            logDebug("Found MOVE_LINEAR program.");
                            move_linear_prog = program;
                        }
                        if (null != program && program.name().equalsIgnoreCase("MOVE_LINEAR10")) {
                            logDebug("Found MOVE_LINEAR10 program.");
                            move_linear10_prog = program;
                        }
                        if (null != program && program.name().equalsIgnoreCase("MOVE_JOINT")) {
                            logDebug("Found MOVE_JOINT program.");
                            move_joint_prog = program;
                        }
                        if (null != program && program.name().equalsIgnoreCase("TOOL_OPEN")) {
                            logDebug("Found MOVE_JOINT program.");
                            tool_open_prog = program;
                        }
                        if (null != program && program.name().equalsIgnoreCase("TOOL_CLOSE")) {
                            logDebug("Found MOVE_JOINT program.");
                            tool_close_prog = program;
                        }
                        if (null != program && program.name().equalsIgnoreCase("GRIPPER_CLOSE")) {
                            logDebug("Found close_gripper program.");
                            close_gripper_prog = program;
                        }
                    }
                }
            }

            reg96Var = getNumericIVar(96);
            reg96Var.noUpdate(true);
            regNumeric96 = ((Com4jObject) reg96Var.value()).queryInterface(IRegNumeric.class);
            regNumeric96.regLong(10_000);
            reg97Var = getNumericIVar(97);
            regNumeric97 = ((Com4jObject) reg97Var.value()).queryInterface(IRegNumeric.class);
            regNumeric97.regLong(50);
            reg98Var = getNumericIVar(98);;
            regNumeric98 = ((Com4jObject) reg98Var.value()).queryInterface(IRegNumeric.class);
            regNumeric98.regFloat(DEFAULT_CART_SPEED);

            posReg98 = getISysGroupPosition(98, 1);
            posReg98.record();
            posReg97 = getISysGroupPosition(97, 1);
            posReg97.record();
            for (int i = 0; i < posReg80Array.length; i++) {
                posReg80Array[i] = getISysGroupPosition(80 + i, 1);
                posReg80Array[i].comment("CRCLml10_" + i);
                posReg80Array[i].record();
            }
            logDebug("Calling robot.sysVariables() ...");
            IVars sysvars = localRobot.sysVariables();
            overrideVar = getNamedItemIVar(sysvars, "$MCR.$GENOVERRIDE");
            if (null != overrideVar) {
                overrideVar.refresh();
                Object overrideValueObject = overrideVar.value();
                System.out.println("overrideValueObject = " + overrideValueObject);
//                if(null != overrideValueObject) {
//                    System.out.println("overrideValueObject.getClass() = " + overrideValueObject.getClass());
//                }
                if (overrideValueObject instanceof Integer) {
                    setOverrideValue((Integer) overrideValueObject);
                }
            }
            morSafetyStatVar = getNamedItemIVar(sysvars, "$MOR.$safety_stat");
            if (null != overrideVar) {
                morSafetyStatVar.refresh();
            }
            moveGroup1RobMoveVar = getNamedItemIVar(sysvars, "$MOR_GRP[1].$ROB_MOVE");
            if (null != moveGroup1RobMoveVar) {
                moveGroup1RobMoveVar.refresh();
            }
            moveGroup1ServoReadyVar = getNamedItemIVar(sysvars, "$MOR_GRP[1].$SERVO_READY");
            if (null != moveGroup1RobMoveVar) {
                moveGroup1RobMoveVar.refresh();
            }
            xMax = yMax = zMax = 10000.0f;
            xMin = yMin = zMin = -10000.0f;

//            readCartLimitsFromRobot();
            readAndApplyUserCartLimits();
            for (int i = 0; i < 6; i++) {
                IVar jointUpperLimVar = getNamedItemIVar(sysvars, "$MRR_GRP[1].$UPPERLIMSDF[" + (i + 1) + "]");
                this.upperJointLimits[i] = (Float) jointUpperLimVar.value();
            }
            readAndApplyUserJointLimits();
            updateJFrame();
            logDebug("Connect to Remote Fanuc Robot complete.");
        } catch (ComException comEx) {
            showComException(comEx);
            connectionError = comEx.toString();
        } catch (Exception e) {
            e.printStackTrace();
            showError(e.toString());
            connectionError = e.toString();
        }
    }

    @SuppressWarnings("nullness")
    private static IVar getNamedItemIVar(IVars sysvars, String name) {
        return sysvars.item(name, null).queryInterface(IVar.class);
    }

    @SuppressWarnings("nullness")
    private ISysGroupPosition getISysGroupPosition(int index, int gindex) {
        IRobot2 localRobot = Objects.requireNonNull(this.robot, "robot");
        return localRobot.regPositions().item(index, null).queryInterface(ISysPosition.class).group((short) gindex);
    }

    @SuppressWarnings("nullness")
    private IVar getNumericIVar(int numericIndex) {
        IRobot2 localRobot = Objects.requireNonNull(this.robot, "robot");
        IVar numericVar = localRobot.regNumerics().item(numericIndex, null).queryInterface(IVar.class);
        return numericVar;
    }

    @SuppressWarnings("nullness")
    private void clearDisplayedPrograms() {
        if (null != displayInterface) {
            displayInterface.setPrograms(null);
        }
    }

    public void readCartLimitsFromRobot() {
        final IRobot2 localRobot = Objects.requireNonNull(robot, "robot");
        IVars sysvars = localRobot.sysVariables();
        IVar xLimitVar1 = getNamedItemIVar(sysvars, "$DCSS_CPC[1].$X[1]");
        if (null != xLimitVar1) {
            xMax = xMin = (Float) xLimitVar1.value();
        }
        IVar xLimitVar2 = getNamedItemIVar(sysvars, "$DCSS_CPC[1].$X[2]");
        if (null != xLimitVar2) {
            float v = (Float) xLimitVar2.value();
            if (xMax < v) {
                xMax = v;
            }
            if (xMin > v) {
                xMin = v;
            }
        }
        IVar xLimitVar3 = getNamedItemIVar(sysvars, "$DCSS_CPC[1].$X[3]");
        if (null != xLimitVar3) {
            float v = (Float) xLimitVar3.value();
            if (xMax < v) {
                xMax = v;
            }
            if (xMin > v) {
                xMin = v;
            }
        }
        IVar xLimitVar4 = getNamedItemIVar(sysvars, "$DCSS_CPC[1].$X[4]");
        if (null != xLimitVar4) {
            float v = (Float) xLimitVar4.value();
            if (xMax < v) {
                xMax = v;
            }
            if (xMin > v) {
                xMin = v;
            }
        }

        IVar yLimitVar1 = getNamedItemIVar(sysvars, "$DCSS_CPC[1].$Y[1]");
        if (null != yLimitVar1) {
            yMax = yMin = (Float) yLimitVar1.value();
        }
        IVar yLimitVar2 = getNamedItemIVar(sysvars, "$DCSS_CPC[1].$Y[2]");
        if (null != yLimitVar2) {
            float v = (Float) yLimitVar2.value();
            if (yMax < v) {
                yMax = v;
            }
            if (yMin > v) {
                yMin = v;
            }
        }
        IVar yLimitVar3 = getNamedItemIVar(sysvars, "$DCSS_CPC[1].$Y[3]");
        if (null != yLimitVar3) {
            float v = (Float) yLimitVar3.value();
            if (yMax < v) {
                yMax = v;
            }
            if (yMin > v) {
                yMin = v;
            }
        }
        IVar yLimitVar4 = getNamedItemIVar(sysvars, "$DCSS_CPC[1].$Y[4]");
        if (null != yLimitVar4) {
            float v = (Float) yLimitVar4.value();
            if (yMax < v) {
                yMax = v;
            }
            if (yMin > v) {
                yMin = v;
            }
        }

        IVar zLimitVar1 = getNamedItemIVar(sysvars, "$DCSS_CPC[1].$Z1");
        if (null != zLimitVar1) {
            zMax = zMin = (Float) zLimitVar1.value();
        }
        IVar zLimitVar2 = getNamedItemIVar(sysvars, "$DCSS_CPC[1].$Z2");
        if (null != zLimitVar2) {
            float v = (Float) zLimitVar2.value();
            if (zMax < v) {
                zMax = v;
            }
            if (zMin > v) {
                zMin = v;
            }
        }

        for (int i = 0; i < 6; i++) {
            IVar jointLowerLimVar = getNamedItemIVar(sysvars, "$MRR_GRP[1].$LOWERLIMSDF[" + (i + 1) + "]");
            this.lowerJointLimits[i] = (Float) jointLowerLimVar.value();
        }
        CRCLStatusType stat = this.status.get();
        final SettingsStatusType settingsStatus = stat.getSettingsStatus();
        settingsStatus.setMaxCartesianLimit(point(xMax, yMax, zMax));
        settingsStatus.setMinCartesianLimit(point(xMin, yMin, zMin));
    }
    public static final float DEFAULT_CART_SPEED = 100.0f;

    private @Nullable
    String lastComExString = null;
    private long last_com_ex_time = 0;

    public void showComException(ComException comEx) {
        String newMsg = comEx.getMessage();
        if (!newMsg.equals(lastComExString) || (System.currentTimeMillis() - last_com_ex_time) > 5000) {
            showError(newMsg);
            getLocalLogger().log(Level.SEVERE, null, comEx);
            lastComExString = newMsg;
            last_com_ex_time = System.currentTimeMillis();
        }
    }

    public void updateJFrame() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final FanucCRCLServerDisplayInterface localDisplayInterface = displayInterface;
                if (null != localDisplayInterface) {
                    localDisplayInterface.setMain(FanucCRCLMain.this);
                    localDisplayInterface.setConnected(robotIsConnected);
                    localDisplayInterface.setPrograms(tpPrograms);
                    localDisplayInterface.updateCartesianLimits(xMax, xMin, yMax, yMin, zMax, zMin);
                    localDisplayInterface.updateJointLimits(lowerJointLimits, upperJointLimits);
                    final IVar localOverideVar = Objects.requireNonNull(getOverideVar(), "overideVar");
                    localDisplayInterface.setOverrideVar(localOverideVar);
                    final IVar localMorSafetyStatVar = Objects.requireNonNull(getMorSafetyStatVar(), "morSafetyStatVar");
                    localDisplayInterface.setMorSafetyStatVar(localMorSafetyStatVar);
                    final IVar localMoveGroup1ServoReadyVar = Objects.requireNonNull(getMoveGroup1ServoReadyVar(), "moveGroup1ServoReadyVar");
                    localDisplayInterface.setMoveGroup1ServoReadyVar(localMoveGroup1ServoReadyVar);
                }
            }
        });
    }

    // Taken from https://github.com/ros-industrial/fanuc/blob/indigo-devel/fanuc_driver/karel/libind_rs.kl
//// CONST
//	                     -- '$MOR.$safety_stat', R-J3iC Software Reference Manual, 
//	                     --   MARACSSRF03061E Rev A
//	MFS_EMGOP    =    1  -- E-Stop SOP
//	MFS_EMGTP    =    2  -- E-Stop TP
//	MFS_DEADMAN  =    4  -- TP Deadman
//	MFS_FENCE    =    8  -- Fence Open
//	MFS_ROT      =   16  -- Over Travel
//	MFS_HBK      =   32  -- Hand Broken
//	MFS_EMGEX    =   64  -- External E-Stop
//	MFS_PPABN    =  128  -- ?
//	MFS_BLTBREAK =  256  -- Belt Broken
//	MFS_ENABLE   =  512  -- TP Enable
//	MFS_FALM     = 1024  -- Alarm?
    public static final int MOR_SAFETY_STAT_ESTOP_SOP_FLAG = 1;
    public static final int MOR_SAFETY_STAT_ESTOP_TP_FLAG = 2;
    public static final int MOR_SAFETY_STAT_TP_DEADMAN_FLAG = 4;
    public static final int MOR_SAFETY_STAT_FENCE_OPEN_FLAG = 8;
    public static final int MOR_SAFETY_STAT_OVER_TRAVEL_FLAG = 16;
//    public static final int MOR_SAFETY_STAT_HAND_BROKEN_FLAG = 32; not sure what this is but seems to be always set
    public static final int MOR_SAFETY_STAT_EXTERNAL_ESTOP_FLAG = 64;
    public static final int MOR_SAFETY_STAT_BELT_BROKEN_FLAG = 256;
    public static final int MOR_SAFETY_STAT_TP_ENABLE_FLAG = 512;
    public static final int MOR_SAFETY_STAT_ALARM_FLAG = 1024;

    public static boolean isMoreSafetyStatError(int val) {
        return (val
                & (MOR_SAFETY_STAT_ESTOP_SOP_FLAG
                | MOR_SAFETY_STAT_ESTOP_TP_FLAG
                | MOR_SAFETY_STAT_TP_DEADMAN_FLAG
                | MOR_SAFETY_STAT_FENCE_OPEN_FLAG
                | MOR_SAFETY_STAT_OVER_TRAVEL_FLAG
                | MOR_SAFETY_STAT_EXTERNAL_ESTOP_FLAG
                | MOR_SAFETY_STAT_TP_ENABLE_FLAG
                | MOR_SAFETY_STAT_ALARM_FLAG)) != 0;
    }

    public static String morSafetyStatToString(int val) {
        String ret = val + " : "
                + ((val & MOR_SAFETY_STAT_ESTOP_SOP_FLAG) == MOR_SAFETY_STAT_ESTOP_SOP_FLAG ? " Main E-Stop | " : "")
                + ((val & MOR_SAFETY_STAT_ESTOP_TP_FLAG) == MOR_SAFETY_STAT_ESTOP_TP_FLAG ? " Teach-Pendant E-Stop | " : "")
                + ((val & MOR_SAFETY_STAT_TP_DEADMAN_FLAG) == MOR_SAFETY_STAT_TP_DEADMAN_FLAG ? " Teach-Pendant Deadman ( Need to set switch to Auto and Turn off Pendant) | " : "")
                + ((val & MOR_SAFETY_STAT_FENCE_OPEN_FLAG) == MOR_SAFETY_STAT_FENCE_OPEN_FLAG ? " Fence Open | " : "")
                + ((val & MOR_SAFETY_STAT_OVER_TRAVEL_FLAG) == MOR_SAFETY_STAT_OVER_TRAVEL_FLAG ? " Over Travel | " : "")
                //                + ((val & MOR_SAFETY_STAT_HAND_BROKEN_FLAG) == MOR_SAFETY_STAT_HAND_BROKEN_FLAG ? " Hand Broken | " : "")
                + ((val & MOR_SAFETY_STAT_EXTERNAL_ESTOP_FLAG) == MOR_SAFETY_STAT_EXTERNAL_ESTOP_FLAG ? " External E-Stop | " : "")
                + ((val & MOR_SAFETY_STAT_BELT_BROKEN_FLAG) == MOR_SAFETY_STAT_BELT_BROKEN_FLAG ? " Belt Broken | " : "")
                + ((val & MOR_SAFETY_STAT_TP_ENABLE_FLAG) == MOR_SAFETY_STAT_TP_ENABLE_FLAG ? " TP Enable | " : "")
                + ((val & MOR_SAFETY_STAT_ALARM_FLAG) == MOR_SAFETY_STAT_ALARM_FLAG ? " Alarm | " : "")
                + "  ";
        ret = ret.trim();
        if (ret.endsWith("|")) {
            return ret.substring(0, ret.length() - 1).trim();
        }
        return ret;
    }

    private static @Nullable
    FanucCRCLMain main = null;

    public static @Nullable
    FanucCRCLMain getMain() {
        return main;
    }

    public static void main(String[] args) throws IOException, CRCLException {
        main = new FanucCRCLMain();
        String neighborhoodname = args.length > 0 ? args[0] : DEFAULT_AGILITY_FANUC_NEIGHBORHOOD_NAME;
        String host = args.length > 1 ? args[1] : DEFAULT_AGILITY_LAB_REMOTE_ROBOT_HOST; //"129.6.78.111";
        int port = args.length > 2 ? Integer.valueOf(args[2]) : CRCLSocket.DEFAULT_PORT;
        boolean prefRNN = (args.length > 3) ? Boolean.valueOf(args[3]) : false;
        main.startDisplayInterface();
        main.start(prefRNN, neighborhoodname, host, port);
//        logDebug("Press enter \"stop\" to quit");
//        Scanner in = new Scanner(System.in);
//        while (!in.nextLine().equals("stop")) {
//            logDebug("Enter \"stop\" to quit");
//        }
//        main.stop();
//        main = null;
    }
    public static final String DEFAULT_AGILITY_FANUC_NEIGHBORHOOD_NAME = "AgilityLabLRMate200iD";
    public static final String DEFAULT_AGILITY_LAB_REMOTE_ROBOT_HOST = "192.168.1.34";
}
