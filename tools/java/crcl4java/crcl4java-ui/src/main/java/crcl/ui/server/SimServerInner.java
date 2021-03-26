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
 *  This software is experimental. NIST assumes no responsibility whatsoever for its use by other
 * parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic. We would appreciate
 * acknowledgement if the software is used. This software can be
 * redistributed and/or modified freely provided that any derivative works
 * bear some notice that they are derived from it, and any modified
 * versions bear some notice that they have been modified.
 * 
 */
package crcl.ui.server;

import crcl.base.ActuateJointType;
import crcl.base.ActuateJointsType;
import crcl.base.AngleUnitEnumType;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLStatusType;
import crcl.base.CloseToolChangerType;
import crcl.base.CommandStateEnumType;
import static crcl.base.CommandStateEnumType.CRCL_DONE;
import crcl.base.CommandStatusType;
import crcl.base.ConfigureJointReportType;
import crcl.base.ConfigureJointReportsType;
import crcl.base.DwellType;
import crcl.base.EndCanonType;
import crcl.base.GetStatusType;
import crcl.base.GripperStatusType;
import crcl.base.InitCanonType;
import crcl.base.JointDetailsType;
import crcl.base.JointPositionToleranceSettingType;
import crcl.base.JointPositionsTolerancesType;
import crcl.base.JointSpeedAccelType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.MessageType;
import crcl.base.MoveScrewType;
import crcl.base.MoveThroughToType;
import crcl.base.MoveToType;
import crcl.base.OpenToolChangerType;
import crcl.base.PointType;
import crcl.base.PoseAndSetType;
import crcl.base.PoseStatusType;
import crcl.base.PoseToleranceType;
import crcl.base.PoseType;
import crcl.base.RotAccelAbsoluteType;
import crcl.base.RotAccelRelativeType;
import crcl.base.RotAccelType;
import crcl.base.RotSpeedAbsoluteType;
import crcl.base.RotSpeedRelativeType;
import crcl.base.RotSpeedType;
import crcl.base.SetAngleUnitsType;
import crcl.base.SetEndEffectorParametersType;
import crcl.base.SetEndEffectorType;
import crcl.base.SetEndPoseToleranceType;
import crcl.base.SetIntermediatePoseToleranceType;
import crcl.base.SetLengthUnitsType;
import crcl.base.SetRotAccelType;
import crcl.base.SetRotSpeedType;
import crcl.base.SetTransAccelType;
import crcl.base.SetTransSpeedType;
import crcl.base.SettingsStatusType;
import crcl.base.StopMotionType;
import crcl.base.TransAccelAbsoluteType;
import crcl.base.TransAccelRelativeType;
import crcl.base.TransAccelType;
import crcl.base.TransSpeedAbsoluteType;
import crcl.base.TransSpeedRelativeType;
import crcl.base.TransSpeedType;
import crcl.base.VectorType;
import crcl.ui.DefaultSchemaFiles;
import crcl.utils.CRCLCopier;
import static crcl.utils.CRCLCopier.copy;
import crcl.utils.CRCLException;
import crcl.utils.CRCLPosemath;
import static crcl.utils.CRCLPosemath.getNullablePose;
import static crcl.utils.CRCLPosemath.maxDiffDoubleArray;
import static crcl.utils.CRCLPosemath.multiply;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.pose;
import static crcl.utils.CRCLPosemath.shift;
import static crcl.utils.CRCLPosemath.toPmCartesian;
import static crcl.utils.CRCLPosemath.toPmRotationVector;
import static crcl.utils.CRCLPosemath.toPoseType;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.vectorToPmCartesian;
import static crcl.utils.CRCLSchemaUtils.filesToCmdSchema;
import static crcl.utils.CRCLSchemaUtils.filesToStatSchema;
import static crcl.utils.CRCLSchemaUtils.readCmdSchemaFiles;
import static crcl.utils.CRCLSchemaUtils.readStatSchemaFiles;
import crcl.utils.CRCLSocket;
import static crcl.utils.CRCLUtils.getNonNullFilteredList;
import static crcl.utils.CRCLUtils.getNonNullIterable;
import crcl.utils.PoseToleranceChecker;
import crcl.utils.ThreadLockedHolder;
import crcl.utils.XFuture;
import crcl.utils.XpathUtils;
import crcl.utils.kinematics.SimRobotEnum;
import static crcl.utils.kinematics.SimRobotEnum.PLAUSIBLE;
import static crcl.utils.kinematics.SimRobotEnum.SIMPLE;
import crcl.utils.kinematics.SimulatedKinematicsPlausible;
import crcl.utils.kinematics.SimulatedKinematicsSimple;
import crcl.utils.outer.interfaces.SimServerMenuOuter;
import crcl.utils.outer.interfaces.SimServerOuter;
import crcl.utils.server.CRCLServerClientState;
import crcl.utils.server.CRCLServerSocket;
import crcl.utils.server.CRCLServerSocketEvent;
import crcl.utils.server.CRCLServerSocketEventListener;
import static crcl.utils.server.CRCLServerSocketEventType.CRCL_COMMAND_RECIEVED;
import static crcl.utils.server.CRCLServerSocketEventType.GUARD_LIMIT_REACHED;
import static crcl.utils.server.CRCLServerSocketEventType.NEW_CRCL_CLIENT;
import crcl.utils.server.CRCLServerSocketStateGenerator;
import crcl.utils.server.UnitsTypeSet;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static java.util.Objects.requireNonNull;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.xml.sax.SAXException;
import rcs.posemath.PmCartesian;
import rcs.posemath.PmException;
import rcs.posemath.PmRotationVector;
import rcs.posemath.Posemath;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class SimServerInner {

    private final static Set<SimServerInner> runningServers = new HashSet<>();
    private static final Logger LOGGER = Logger.getLogger(SimServerInner.class.getName());
    private static boolean testing = false;
    private static final double SCALE_FUDGE_FACTOR = 0.5;
    public static long debugCmdSendTime = 0;

    public static void setTesting(boolean _testing) {
        testing = _testing;
    }

    private boolean reportPoseStatus = true;

    private boolean forceFail = false;

    /**
     * Get the value of forceFail
     *
     * @return the value of forceFail
     */
    public boolean isForceFail() {
        return forceFail;
    }

    /**
     * Set the value of forceFail
     *
     * @param forceFail new value of forceFail
     */
    public void setForceFail(boolean forceFail) {
        this.forceFail = forceFail;
    }

    public void setStatus(CRCLStatusType newStatus) {
        if (null == newStatus) {
            throw new IllegalArgumentException("null == newStatus");
        }
        CRCLStatusType newStatusCopy = CRCLCopier.copy(newStatus);
        if (null == newStatusCopy) {
            throw new NullPointerException("newStatusCopy");
        }
        addToUpdateStatusRunnables(() -> this.applySetStatus(newStatusCopy));
    }

    private void addToUpdateStatusRunnables(Runnable r) {
        if (null != this.crclServerSocket) {
            this.crclServerSocket.addToUpdateServerSideRunnables(r);
        } else {
            r.run();
        }
    }

    private void applySetStatus(CRCLStatusType newStatus) {
        final CRCLStatusType stat = this.status.get();
        String statusName = newStatus.getName();
        if (null != statusName) {
            stat.setName(statusName);
        }
        CommandStatusType commandStatus = requireNonNull(newStatus.getCommandStatus(), "newStatus.getCommandStatus()");
        stat.setCommandStatus(commandStatus);
        PoseStatusType poseStatus1 = newStatus.getPoseStatus();
        if (null != poseStatus1) {
            stat.setPoseStatus(poseStatus1);
        }
        JointStatusesType jointStatuses1 = newStatus.getJointStatuses();
        if (null != jointStatuses1) {
            stat.setJointStatuses(jointStatuses1);
        }
        GripperStatusType gripperStatus = newStatus.getGripperStatus();
        if (null != gripperStatus) {
            stat.setGripperStatus(gripperStatus);
        }
        SettingsStatusType settingsStatus1 = newStatus.getSettingsStatus();
        if (null != settingsStatus1) {
            stat.setSettingsStatus(settingsStatus1);
        }
    }


    private double maxDwell = getDoubleProperty("crcl4java.maxdwell", 6000.0);

    private static double getDoubleProperty(String propName, double defaultVal) {
        return Double.parseDouble(System.getProperty(propName, Double.toString(defaultVal)));
    }

    public void setStateDescription(String s) {
        CommandStatusType commandStatus = this.getStatus().getCommandStatus();
        if (commandStatus == null) {
            commandStatus = new CommandStatusType();
            this.getStatus().setCommandStatus(commandStatus);
        }
        if (null != this.crclServerSocket) {
            this.crclServerSocket.setStateDescription(s);
        }
    }

    static public void printAllClientStates(final PrintStream ps) {
        SimServerInner.runningServers.forEach(s -> s.printClientStates(ps));
    }

    private @Nullable
    CRCLSocket gripperSocket = null;

    private final XpathUtils xpu;
    private final SimServerOuter outer;
    Queue<CRCLCommandInstanceType> cmdQueue = new ConcurrentLinkedQueue<>();
    private final Queue<CRCLCommandInstanceType> gripperCmdQueue = new ConcurrentLinkedQueue<>();

    private double @MonotonicNonNull [] jointPositions = null;
    private double @MonotonicNonNull [] commandedJointPositions = null;
    private double @MonotonicNonNull [] jointVelocites = null;
    private double @MonotonicNonNull [] commandedJointVelocities = null;
    private double @MonotonicNonNull [] commandedJointAccellerations = null;
    private double @MonotonicNonNull [] jointmins = null;
    private double @MonotonicNonNull [] jointmaxs = null;
    private double @MonotonicNonNull [] seglengths = null;

    private @Nullable
    PoseType goalPose = null;

    private final double maxTransSpeed = getDoubleProperty("crcl4java.simserver.maxTransSpeed", 2.0);
    private final double maxTransAccel = getDoubleProperty("crcl4java.simserver.maxTransAccell", 20.0);

    private double curTransSpeed = 0;
    private double commandedTransSpeed = maxTransSpeed * 0.5;
    private double curTransAccel = 0;
    private double commandedTransAccel = maxTransAccel * 0.5;

    private final double maxRotSpeed = getDoubleProperty("crcl4java.simserver.maxRotSpeed", 2.0);
    private final double maxRotAccel = getDoubleProperty("crcl4java.simserver.maxRotAccell", 20.0);

    private double curRotSpeed = 0;
    private double commandedRotSpeed = maxRotSpeed * 0.5;
    private double curRotAccel = 0;
    private double commandedRotAccel = maxRotAccel * 0.5;

    private final List<CRCLCommandType> cmdLog = new ArrayList<>();
    private SimRobotEnum robotType = SimRobotEnum.PLAUSIBLE;
    private int port;
    private boolean moveStraight = false;

    private volatile @Nullable
    CRCLServerSocket<SimServerClientState> crclServerSocket = null;

    public @Nullable
    CRCLServerSocket<SimServerClientState> getCrclServerSocket() {
        return crclServerSocket;
    }

    private final SimulatedKinematicsPlausible skPlausible = new SimulatedKinematicsPlausible();
    private final SimulatedKinematicsSimple skSimple = new SimulatedKinematicsSimple();
    final private ThreadLockedHolder<CRCLStatusType> status
            = new ThreadLockedHolder(
                    "SimServerInner.status",
                    CRCLPosemath.newFullCRCLStatus(),
                    false
            );

    private @Nullable
    CRCLCommandType multiStepCommand = null;
    private int moveScrewStep = 0;
    private BigDecimal moveScriptTurnComplete = BigDecimal.ZERO;
    private double jointSpeedMax = getDoubleProperty("crcl4java.simserver.jointSpeedMax", 200.0);
    private @Nullable
    PmRotationVector lastDiffRotv = null;
    private int cycle_count = 0;
    private final ConcurrentLinkedDeque<SimServerClientState> clientStates = new ConcurrentLinkedDeque<>();
    private final Map<CRCLSocket, Thread> clientThreadMap = Collections.synchronizedMap(new IdentityHashMap<>());
    private volatile @Nullable
    Thread simulationThread = null;
    private final AtomicInteger closeCount = new AtomicInteger();
    long maxCmdId = 1;
    private final Map<CRCLSocket, LastStatusInfo> lastStatusMap
            = Collections.synchronizedMap(new IdentityHashMap<>());
    private boolean executingMoveCommand = false;
    long debugUpdateStatusTime = 0;
    private int currentWaypoint;

    private void cleanupClientStatesThreadMap() {
        List<SimServerClientState> clientStatesToRemove = new ArrayList<>();
        for (SimServerClientState cs : clientStates) {
            CRCLSocket crclSocket = cs.getCs();
            if (null == crclSocket) {
                clientStatesToRemove.add(cs);
                continue;
            }
            Socket socket = crclSocket.getSocket();
            if (null == socket) {
                clientStatesToRemove.add(cs);
                continue;
            }
            if (socket.isClosed()) {
                clientStatesToRemove.add(cs);
            }
        }
        clientStates.removeAll(clientStatesToRemove);
        for (int i = 0; i < clientStatesToRemove.size(); i++) {
            SimServerClientState clientState = clientStatesToRemove.get(i);
            CRCLSocket crclSocket = clientState.getCs();
            if (!lastStatusMap.isEmpty() && null != crclSocket) {
                lastStatusMap.remove(crclSocket);
            }
        }
        List<CRCLSocket> crclSocketsToRemove = new ArrayList<>();
        for (Map.Entry<CRCLSocket, Thread> entry : clientThreadMap.entrySet()) {
            CRCLSocket key = entry.getKey();
            Thread value = entry.getValue();
            Socket socket = key.getSocket();
            if (null == socket) {
                crclSocketsToRemove.add(key);
                if (null != value) {
                    value.interrupt();
                }
                continue;
            }
            if (socket.isClosed()) {
                crclSocketsToRemove.add(key);
                if (null != value) {
                    value.interrupt();
                }
                continue;
            }
            if (!value.isAlive()) {
                try {
                    key.close();
                } catch (IOException ex) {
                    Logger.getLogger(SimServerInner.class.getName()).log(Level.SEVERE, null, ex);
                }
                crclSocketsToRemove.add(key);
                continue;
            }
        }
        for (int i = 0; i < crclSocketsToRemove.size(); i++) {
            CRCLSocket crclSocket = crclSocketsToRemove.get(i);
            clientThreadMap.remove(crclSocket);
            if (null != lastStatusMap) {
                lastStatusMap.remove(crclSocket);
            }
        }
    }

    private final Set<Class<? extends CRCLCommandType>> gripperCommands = new HashSet<>(
            Arrays.asList(
                    InitCanonType.class,
                    EndCanonType.class,
                    SetEndEffectorType.class,
                    SetEndEffectorParametersType.class)
    );
    private long cmdQueuePutTime = 0;
    private boolean debug_this_command = false;
    private int cmdQueueCmdsOffered = 0;

//    @Nullable
//    private Thread acceptClientsThread = null;
    private long delayMillis = Long.getLong("crcl4java.simserver.delayMillis", 20);
    private @MonotonicNonNull
    ConfigureJointReportsType cjrs = null;
    private final Map<Integer, ConfigureJointReportType> cjrMap = new HashMap<>();
    private PoseToleranceType expectedEndPoseTolerance = new PoseToleranceType();
    private PoseToleranceType expectedIntermediatePoseTolerance = new PoseToleranceType();
    private long dwellEndTime = 0;
    private long debugCmdStartTime = 0;
    private final long debugCmdQueueTime = 0;
    private long cmdQueueMaxSize = 0;
    private long maxCmdQueuePollTime = 0;
    private @Nullable
    CRCLCommandInstanceType lastReadCommandInstance = null;
    private int cmdQueuePollReturnCount = 0;
    private int cmdQueuePollReturnNonNullCount = 0;
    long maxDiffCmdQueuePutEmpty = 0;
    private double lengthScale = 0.01 * SCALE_FUDGE_FACTOR;
//    private LengthUnitEnumType lengthUnit = LengthUnitEnumType.MILLIMETER;
    private final List<PoseType> waypoints = Collections.synchronizedList(new ArrayList<>());
    private long maxReadCommandTime = 0;
    private long maxUpdateStatusTime = 0;
    private long maxSimCycleTime = 0;
    private long simCycleCount = 0;
    private @Nullable
    CRCLSocket checkerCRCLSocket = null;

    private File[] statSchemaFiles;

    public File[] getStatSchemaFiles() {
        return statSchemaFiles;
    }

    @SuppressWarnings("initialization")
    SimServerInner(SimServerOuter _outer, DefaultSchemaFiles defaultSchemaFiles) throws ParserConfigurationException {
        this.outer = _outer;
        this.xpu = new XpathUtils();
        this.robotType = SimRobotEnum.SIMPLE;
        this.port = CRCLSocket.DEFAULT_PORT;
        this.statSchemaFiles = readStatSchemaFiles(defaultSchemaFiles.getStatSchemasFile());
        this.setStatSchema(this.statSchemaFiles);
        this.setCmdSchema(readCmdSchemaFiles(defaultSchemaFiles.getCmdSchemasFile()));
        this.resetToDefaults();
        String portPropertyString = System.getProperty("crcl4java.port");
        if (null != portPropertyString) {
            this.port = Integer.parseInt(portPropertyString);
        }
//        updateStatusReporting();
        this.status.releaseLockThread();
    }

//    private void updateStatusReporting() {
//        final CRCLStatusType stat = this.status.get();
//        if (this.isReportJointStatus()) {
//            stat.setJointStatuses(jointStatuses);
//        } else {
//            clearJointStatuses();
//        }
//        if (this.isReportPoseStatus()) {
//            stat.setPoseStatus(poseStatus);
//        } else {
//            clearPoseStatus();
//        }
//        if (this.isReportSettingsStatus()) {
//            stat.setSettingsStatus(settingsStatus);
//        } else {
//            clearSettingsStatus();
//        }
//    }
    /**
     * Get the value of gripperSocket
     *
     * @return the value of gripperSocket
     */
    public @Nullable
    CRCLSocket getGripperSocket() {
        return gripperSocket;
    }

    /**
     * Set the value of gripperSocket
     *
     * @param gripperSocket new value of gripperSocket
     */
    public void setGripperSocket(@Nullable CRCLSocket gripperSocket) {
        this.gripperSocket = gripperSocket;
    }

    public Queue<CRCLCommandInstanceType> getGripperCmdQueue() {
        return gripperCmdQueue;
    }

    /**
     * Get the value of cmdLog
     *
     * @return the value of cmdLog
     */
    public List<CRCLCommandType> getCmdLog() {
        return Collections.unmodifiableList(cmdLog);
    }

    private void resetToPlausibleDefaults() {
        try {

            double[] newJointPositions = Arrays.copyOf(SimulatedKinematicsPlausible.DEFAULT_JOINTVALS, SimulatedKinematicsPlausible.DEFAULT_JOINTVALS.length);
            jointVelocites = new double[newJointPositions.length];
            jointPositions = newJointPositions;
            commandedJointPositions = Arrays.copyOf(SimulatedKinematicsPlausible.DEFAULT_JOINTVALS, SimulatedKinematicsPlausible.DEFAULT_JOINTVALS.length);
            jointmins = new double[]{-170.0, 5.0, -170.0, +10.0, -135.0, -135.0};
            jointmaxs = new double[]{+170.0, 85.0, -10.0, 170.0, +135.0, +135.0};
            seglengths = SimulatedKinematicsPlausible.DEFAULT_SEGLENGTHS;
            PoseType pose = getPose();
            if (null == pose) {
                pose = pose(point(0, 0, 0), vector(1, 0, 0), vector(0, 0, 1));
            }
            setPoseFromJoints(pose);
            setPose(pose);
        } catch (Exception ex) {
            Logger.getLogger(SimServerInner.class.getName()).log(Level.SEVERE, null, ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
    }

    private void resetToSimpleDefaults() {
        try {
            double newJointPositions[] = Arrays.copyOf(SimulatedKinematicsSimple.DEFAULT_JOINTVALS, SimulatedKinematicsSimple.DEFAULT_JOINTVALS.length);
            jointVelocites = new double[newJointPositions.length];
            jointPositions = newJointPositions;
            commandedJointPositions = Arrays.copyOf(SimulatedKinematicsSimple.DEFAULT_JOINTVALS, SimulatedKinematicsSimple.DEFAULT_JOINTVALS.length);

            jointmins = new double[]{0, -360.0, -360.0, -360.0, -360.0, -360.0};
            jointmaxs = new double[]{Double.POSITIVE_INFINITY, +360.0, +360.0, +360.0, +360.0, +360.0};
            seglengths = SimulatedKinematicsSimple.DEFAULT_SEGLENGTHS;
            PoseType pose = getPose();
            if (null == pose) {
                pose = pose(point(0, 0, 0), vector(1, 0, 0), vector(0, 0, 1));
            }
            setPoseFromJoints(pose);
            setPose(pose);
        } catch (Exception ex) {
            Logger.getLogger(SimServerInner.class.getName()).log(Level.SEVERE, null, ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * Get the value of robotType
     *
     * @return the value of robotType
     */
    public SimRobotEnum getRobotType() {
        return robotType;
    }

    private void resetToDefaults() {
        switch (robotType) {
            case PLAUSIBLE:
                resetToPlausibleDefaults();
                break;

            case SIMPLE:
                resetToSimpleDefaults();
                break;
        }
    }

    private boolean robotTypeSet = false;

    /**
     * Set the value of robotType
     *
     * @param robotType new value of robotType
     */
    public void setRobotType(SimRobotEnum robotType) {
        try {
            if (robotType != this.robotType || !robotTypeSet) {
                this.robotType = robotType;
                this.resetToDefaults();
            }
        } finally {
            robotTypeSet = true;
        }
    }

    public XpathUtils getXpu() {
        return xpu;
    }

    /**
     * Get the value of port
     *
     * @return the value of port
     */
    public int getPort() {
        return port;
    }

    private boolean teleportToGoals = Boolean.getBoolean("crcl4java.simserver.teleportToGoals");

    ;

    /**
     * Get the value of teleportToGoals
     *
     * @return the value of teleportToGoals
     */
    public boolean isTeleportToGoals() {
        return teleportToGoals;
    }

    /**
     * Set the value of teleportToGoals
     *
     * @param teleportToGoals new value of teleportToGoals
     */
    public void setTeleportToGoals(boolean teleportToGoals) {
        this.teleportToGoals = teleportToGoals;
    }

    public @Nullable
    PoseType getPose() {
        if (null == status) {
            return null;
        }
        CRCLStatusType stat = this.status.get();
        PoseStatusType poseStatus = stat.getPoseStatus();
        if (null == poseStatus) {
            return null;
        } else {
            PoseType pose = poseStatus.getPose();
            if (null != pose) {
                return copy(pose);
            } else {
                return null;
            }
        }
    }

    public void setPose(PoseType pose) {
        final PointType posePoint = pose.getPoint();
        if (null == posePoint) {
            throw new NullPointerException("pose.getPoint() == null : pose =" + pose);
        }
        if (posePoint.getX() > 1200.0) {
            System.err.println("pose.getPoint().getX()=" + posePoint.getX());
        }
        CRCLStatusType stat = this.status.get();
        PoseStatusType poseStatus = stat.getPoseStatus();
        poseStatus.setPose(pose);
    }

    public void simulatedTeleportToPose(PoseType pose) {
        try {
            if (checkForceFail()) {
                return;
            }
            if (null != pose) {
                PointType pt = pose.getPoint();
                if (null != pt) {
                    double origJointPositions[] = this.jointPositions;
                    PoseType origPose = getPose();
                    if (origPose == null) {
                        origPose = new PoseType();
                    }
                    switch (robotType) {
                        case PLAUSIBLE:
                            if (null == origJointPositions) {
                                origJointPositions = new double[SimulatedKinematicsPlausible.NUM_JOINTS];
                            }
                            double newPlausibleJointPositions[] = skPlausible.poseToJoints(origJointPositions, pose);
                            if (null != newPlausibleJointPositions) {
                                this.jointPositions = newPlausibleJointPositions;
                                setPose(skPlausible.jointsToPose(newPlausibleJointPositions, origPose));
                                commandedJointPositions = Arrays.copyOf(newPlausibleJointPositions, newPlausibleJointPositions.length);
                            }
                            break;

                        case SIMPLE:
                            if (null == origJointPositions) {
                                origJointPositions = new double[SimulatedKinematicsSimple.NUM_JOINTS];
                            }
                            double newSimpleJointPositions[] = skSimple.poseToJoints(origJointPositions, pose);
                            if (null != newSimpleJointPositions) {
                                this.jointPositions = newSimpleJointPositions;
                                setPose(skSimple.jointsToPose(newSimpleJointPositions, origPose));
                                commandedJointPositions = Arrays.copyOf(newSimpleJointPositions, newSimpleJointPositions.length);
                            }
                            break;
                    }
                    this.goalPose = null;
                    this.setWaypoints(null);
                    setCommandState(CRCL_DONE);
                }
            }

        } catch (PmException ex) {
            Logger.getLogger(SimServerInner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean checkForceFail() {
        if (this.forceFail) {
            setCommandState(CRCL_ERROR, "Forced Failure State");
            return true;
        }
        return false;
    }

    private boolean serverIsDaemon = true;

    public boolean getServerIsDaemon() {
        return serverIsDaemon;
    }

    /**
     * Set the value of port
     *
     * @param port new value of port
     */
    public void setPort(int port) {
        this.port = port;
        if (null != this.crclServerSocket && !crclServerSocket.isClosed()) {
            throw new IllegalStateException("Can't change port when already bound. close first.");
        }
    }

    /**
     * Get the value of moveStraight
     *
     * @return the value of moveStraight
     */
    public boolean isMoveStraight() {
        return moveStraight;
    }

    @SuppressWarnings("nullness")
    public void setJointPosition(double _position, int index) {
        addToUpdateStatusRunnables(() -> this.applySetJointPosition(_position, index));
    }

    @SuppressWarnings("nullness")
    private void applySetJointPosition(double _position, int index) {
        final CRCLStatusType stat = this.status.get();
        if (stat.getCommandStatus().getCommandState() == CRCL_WORKING) {
            throw new IllegalStateException("changing joint position while executing command.");
        }
        synchronized (jointPositions) {
            this.jointPositions[index] = _position;
        }
    }

    @SuppressWarnings("nullness")
    public void setCommandedJointPosition(double _position, int index) {
        this.commandedJointPositions[index] = _position;
    }

    public void reset() {
        try {
            PoseType origPose = getPose();
            if (null == origPose) {
                origPose = new PoseType();
            }
            switch (robotType) {
                case PLAUSIBLE:
                    jointPositions = Arrays.copyOf(SimulatedKinematicsPlausible.DEFAULT_JOINTVALS, SimulatedKinematicsPlausible.DEFAULT_JOINTVALS.length);
                    setPose(skPlausible.jointsToPose(jointPositions, origPose));
                    break;

                case SIMPLE:
                    jointPositions = Arrays.copyOf(SimulatedKinematicsSimple.DEFAULT_JOINTVALS, SimulatedKinematicsSimple.DEFAULT_JOINTVALS.length);
                    setPose(skSimple.jointsToPose(jointPositions, origPose));
                    break;
            }
            if (null != jointPositions) {
                commandedJointPositions = Arrays.copyOf(jointPositions, jointPositions.length);
            }
            this.goalPose = null;
            this.setWaypoints(null);
            setCommandState(CRCL_DONE);
        } catch (PmException ex) {
            Logger.getLogger(SimServerInner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double @Nullable [] getJointPositions() {
        return jointPositions;
    }

    public double @Nullable [] getSeglengths() {
        return seglengths;
    }

    public void setGoalPose(@Nullable PoseType goalPose) {
        this.goalPose = goalPose;
        if (null != goalPose) {
            if (!checkPose(goalPose)) {
                throw new IllegalArgumentException("goalPose is invalid: " + CRCLPosemath.poseToString(goalPose));
            }
            if (teleportToGoals) {
                this.simulatedTeleportToPose(goalPose);
            }
        }
    }

    public @Nullable
    PoseToleranceType getEndPoseTolerance() {
        CRCLStatusType lastUpdateStatusCopy = this.getLastUpdateServerSideStatusCopy();
        if (null == lastUpdateStatusCopy) {
            return null;
        }
        final CRCLStatusType stat = lastUpdateStatusCopy;
        SettingsStatusType settingsStatus = stat.getSettingsStatus();
        if (null == settingsStatus) {
            return null;
        }
        return settingsStatus.getEndPoseTolerance();
    }

    private @Nullable
    CRCLStatusType getLastUpdateServerSideStatusCopy() {
        if (null == this.crclServerSocket) {
            return null;
        }
        return crclServerSocket.getLastUpdateServerSideStatusCopy();
    }

    public boolean isFinishedMove() {
        final CRCLStatusType stat = this.getLastUpdateServerSideStatusCopy();
        if (null == stat) {
            return false;
        }
        final PoseType currentPose = getNullablePose(stat);
        if (null == currentPose) {
            return false;
        }
        final SettingsStatusType localSettingsStatus = stat.getSettingsStatus();
        if (null == localSettingsStatus) {
            return false;
        }
        final PoseType localGoalPose = goalPose;
        if (null != localGoalPose) {
            PoseToleranceType endPoseTol = getEndPoseTolerance();
            if (null == endPoseTol) {
                return false;
            }
            final AngleUnitEnumType angleUnitName = localSettingsStatus.getAngleUnitName();
            if (null != angleUnitName) {
                boolean goalInTol = PoseToleranceChecker.isInTolerance(localGoalPose, currentPose, endPoseTol, angleUnitName);
                return goalInTol;
            } else {
                final PointType localGoalPosePoint = localGoalPose.getPoint();
                final PointType currentPosePoint = currentPose.getPoint();
                boolean goalInTolTranOnly = PoseToleranceChecker.isInTolerance(localGoalPosePoint, currentPosePoint, endPoseTol);
                return goalInTolTranOnly;
            }
        } else {
            double jpa1[] = this.jointPositions;
            if (null == jpa1) {
                throw new IllegalStateException("null == jointPositions");
            }
            double cjpa1[] = this.commandedJointPositions;
            if (null == cjpa1) {
                throw new IllegalStateException("null == commandedJointPositions");
            }
            // double currentJointPositions[]= 
            double jpa2[] = jpa1;
            double cjpa2[] = cjpa1;
            final JointPositionsTolerancesType jointTolSettings
                    = localSettingsStatus.getJointTolerances();
            if (null == jointTolSettings) {
                return false;
            }
            double jointdiffs[] = new double[jpa2.length];
            double jointtols[] = new double[jpa2.length];
            for (int i = 0; i < jointtols.length; i++) {
                jointtols[i] = getJointDiffMax();
            }
            final List<JointPositionToleranceSettingType> settingsList
                    = getNonNullFilteredList(jointTolSettings.getSetting());
            for (int i = 0; i < settingsList.size(); i++) {
                JointPositionToleranceSettingType setting = settingsList.get(i);
                int n = setting.getJointNumber();
                if (n > 1 && n <= jointtols.length) {
                    jointtols[n - 1] = setting.getJointPositionTolerance();
                }
            }
            for (int i = 0; i < jointdiffs.length; i++) {
                double jointdiff = Math.abs(jpa2[i] - cjpa2[i]);
                jointdiffs[i] = jointdiff;
                if (jointdiff > jointtols[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    public @Nullable
    VectorType getXAxis() {
        PoseType pose = getPose();
        if (pose == null) {
            return null;
        }
        return pose.getXAxis();
    }

    public @Nullable
    VectorType getZAxis() {
        PoseType pose = getPose();
        if (pose == null) {
            return null;
        }
        return pose.getZAxis();
    }

    private boolean handleContinueMoveScrew(MoveScrewType moveScrew, AngleUnitEnumType angleType) {
        switch (Objects.requireNonNull(moveScrewStep, "moveScrewStep")) {
            case 0:
                setCommandState(CRCL_WORKING);
                ;
                if (moveScrew.getStartPosition() != null) {
                    setGoalPose(moveScrew.getStartPosition());
                    moveScrewStep = 1;
                } else {
                    moveScrewStep = 2;
                }
                break;

            case 1:
                if (isFinishedMove()) {
                    PoseType pose = getPose();
                    if (null == pose) {
                        multiStepCommand = null;
                        setCommandState(CRCL_ERROR, "pose is null ");
                        return false;
                    } else if (null == goalPose) {
                        multiStepCommand = null;
                        setCommandState(CRCL_ERROR, "goalPose is null ");
                        return false;
                    } else if (!PoseToleranceChecker.isInTolerance(pose, goalPose,
                            expectedEndPoseTolerance, angleType)) {
                        multiStepCommand = null;
                        String checkToleranceString = PoseToleranceChecker.checkToleranceString(pose, goalPose, expectedEndPoseTolerance, angleType);
                        setCommandState(CRCL_ERROR, "pose not in tolerance : " + checkToleranceString);
                        return false;
                    }
                }
                break;

            case 2:
                final Double axialDistanceFree = moveScrew.getAxialDistanceFree();
                if (axialDistanceFree != null && axialDistanceFree > 0) {
                    PoseType pose = requireNonNull(getPose(), "getPose()");
                    VectorType xAxis = requireNonNull(getXAxis(), "getXAxis()");
                    PoseType newGoalPose = shift(pose,
                            multiply(axialDistanceFree, xAxis));
                    setGoalPose(newGoalPose);
                    setMoveStraight(true);
                    moveScrewStep = 3;
                } else {
                    moveScrewStep = 4;
                }
                break;

            case 3:
                if (isFinishedMove()) {
                    moveScrewStep = 4;
                }
                break;

            case 4:
                moveScriptTurnComplete = BigDecimal.ZERO;
                moveScrewStep = 5;
                break;

            case 5:
                multiStepCommand = null;
                setCommandState(CRCL_DONE);
                return false;
        }
        return true;
//        setCommandState(CommandStateEnumType.CRCL_DONE);
//        multiStepCommand = null;
//        return false;
    }

    private boolean handleMultiStepCommand(SimServerClientState clientState) {
        if (null == multiStepCommand) {
            return false;
        }
        if (multiStepCommand instanceof MoveScrewType) {
            return handleContinueMoveScrew((MoveScrewType) multiStepCommand, clientState.filterSettings.getClientUserSet().getAngleUnit());
        }
        multiStepCommand = null;
        return false;
    }

    public void setCommandState(CommandStateEnumType state) {
        if (null != crclServerSocket) {
            crclServerSocket.setCommandStateEnum(state);
        }
    }

    public void setCommandState(CommandStateEnumType state, String stateDescription) {
        if (null == crclServerSocket) {
            throw new NullPointerException("crclServerSocket");
        }
        crclServerSocket.setCommandStateEnum(state);
        crclServerSocket.setStateDescription(stateDescription);
//        addToUpdateStatusRunnables(() -> this.applySetCommandState(state, stateDescription));
    }

    @SuppressWarnings("nullness")
    public CommandStateEnumType getCommandState() {
        final CRCLStatusType stat = this.getLastUpdateServerSideStatusCopy();
        CommandStatusType cst = stat.getCommandStatus();
        if (null == cst) {
            setCommandState(CRCL_ERROR, "stat.getCommandStatus() == null");
            return CRCL_ERROR;
        }
        return cst.getCommandState();
    }

    private void showMessage(String s) {
        String short_status = s.trim();
        if (short_status.length() > 40) {
            short_status = short_status.substring(0, 36) + " ...";
        }
//        status.getCommandStatus().setStateDescription(short_status);
        outer.showMessage(s);
        setStateDescription(short_status);
    }

    public boolean checkPose(PoseType goalPose) {
        VectorType goalXAxis = requireNonNull(goalPose.getXAxis(), "goalPose.getXAxis()");
        PmCartesian xvec = vectorToPmCartesian(goalXAxis);
        if (Math.abs(xvec.mag() - 1.0) > 1e-3) {
            final String err = "Bad postion : xvec " + xvec + " has magnitude not equal to one.";
            showMessage(err);
            setCommandState(CRCL_ERROR, err);
            return false;
        }
        VectorType goalZAxis = requireNonNull(goalPose.getZAxis(), "goalPose.getZAxis()");
        PmCartesian zvec = vectorToPmCartesian(goalZAxis);
        if (Math.abs(zvec.mag() - 1.0) > 1e-3) {
            final String err = "Bad postion : zvec " + zvec + " has magnitude not equal to one.";
            showMessage(err);
            setCommandState(CRCL_ERROR, err);
            return false;
        }
        if (Math.abs(Posemath.pmCartCartDot(xvec, zvec)) > 1e-3) {
            final String message = "Bad postion : xvec " + xvec + " and zvec " + zvec + " are not orthogonal.";
            showMessage(message);
            setCommandState(CRCL_ERROR, message);
            return false;
        }
        return true;
    }

    public double getJointSpeedMax() {
        return jointSpeedMax;
    }

    public void setJointSpeedMax(double jointSpeedMax) {
        this.jointSpeedMax = jointSpeedMax;
    }

    public PoseType limitSpeedAccel(PoseType goal,
            PoseType current) {
        PoseType newGoal = goal;
        try {
            PointType goalPoint = requireNonNull(goal.getPoint(), "goalPose.getPoint()");
            PmCartesian goalPt = toPmCartesian(goalPoint);
            PointType currentPoint = requireNonNull(current.getPoint(), "current.getPoint()");
            PmCartesian currentPt = toPmCartesian(currentPoint);
            PmCartesian diffPt = goalPt.subtract(currentPt);
            double lastTransSpeed = this.curTransSpeed;
            double diffTransSpeed = diffPt.mag() / (delayMillis * 1e-3);
            this.curTransSpeed = diffTransSpeed;
            this.curTransAccel = this.curTransSpeed - lastTransSpeed;
            if (Math.abs(curTransAccel) > this.commandedTransAccel) {
                this.curTransSpeed = lastTransSpeed
                        + Math.signum(curTransAccel) * this.commandedTransAccel;
            }
            if (this.curTransSpeed > this.commandedTransSpeed) {
                this.curTransSpeed = this.commandedTransSpeed;
            }
            PmRotationVector goalRotv = toPmRotationVector(goal);
            PmRotationVector currentRotv = toPmRotationVector(current);
            PmRotationVector diffRotv = currentRotv.inv().multiply(goalRotv);
            double lastRotSpeed = this.curRotSpeed;
            double diffRotSpeed = diffRotv.s / (delayMillis * 1e-3);
            this.curRotSpeed = diffRotSpeed;
            this.curRotAccel = this.curRotSpeed - lastRotSpeed;
            if (Math.abs(curRotAccel) > this.commandedRotAccel) {
                this.curRotSpeed = lastRotSpeed
                        + Math.signum(curRotAccel) * this.commandedRotAccel;
            }
            if (Math.abs(this.curRotSpeed) > this.commandedRotSpeed) {
                this.curRotSpeed = this.commandedRotSpeed * Math.signum(this.curRotSpeed);
            }
            double transMoveFraction = 1.0;
            if (Math.abs(diffTransSpeed - this.curTransSpeed) > this.commandedTransSpeed * 0.1
                    && Math.abs(diffTransSpeed) > 1e-6) {
                transMoveFraction = curTransSpeed / diffTransSpeed;
            }
            double rotMoveFraction = 1.0;
            if (Math.abs(diffRotSpeed - this.curRotSpeed) > this.commandedRotSpeed * 0.1
                    && Math.abs(diffRotSpeed) > 1e-6) {
                rotMoveFraction = Math.abs(curRotSpeed / diffRotSpeed);
            }
            double moveFraction = Math.min(transMoveFraction, rotMoveFraction);
            assert (moveFraction > 0);
            if (moveFraction < 0.99) {
                PmCartesian newGoalPt = currentPt.add(diffPt.multiply(moveFraction));
                PmRotationVector newGoalRotv = currentRotv.multiply(diffRotv.multiply(moveFraction));
                newGoal = toPoseType(newGoalPt, newGoalRotv);
            }
            lastDiffRotv = diffRotv;
        } catch (PmException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            outer.showMessage(ex.toString());
        }
        return newGoal;
    }

    @Nullable
    PointType getPoint() {
        PoseType pose = getPose();
        if (null == pose) {
            return null;
        }
        return pose.getPoint();
    }

    public double[] getStraightMoveCommandedJointVals(PoseType curGoalPose) {
        double[] commandedJointPositions1 = this.commandedJointPositions;
        if (null == commandedJointPositions1) {
            throw new IllegalStateException("null == this.commandedJointPositions");
        }
        try {
            final double JOINT_DIFF_MAX = getJointDiffMax();
            PointType curGoalPosePoint = requireNonNull(curGoalPose.getPoint(), "curGoalPose.getPoint()");
            PmCartesian goalPt = toPmCartesian(curGoalPosePoint);
            PointType currentPoint = requireNonNull(getPoint(), "getPoint()");
            PmCartesian currentPt = toPmCartesian(currentPoint);
            PmCartesian diffPt = goalPt.subtract(currentPt);
            PmRotationVector goalRotv = toPmRotationVector(curGoalPose);
            PoseType pose = requireNonNull(getPose(), "getPose()");
            PmRotationVector currentRotv = toPmRotationVector(pose);
            PmRotationVector diffRotv = goalRotv.multiply(currentRotv.inv());
            PoseType newGoalPose = curGoalPose;
            goalPoseToCommandedPositions(newGoalPose);

            if (null == this.jointPositions) {
                throw new IllegalStateException("null == this.jointPositions");
            }
            double maxdiff = maxDiffDoubleArray(commandedJointPositions1, this.jointPositions, 360.0);
            double scale = 1.0;
            int cycles = 0;
            while (maxdiff > JOINT_DIFF_MAX) {
                cycles++;
                if (cycles > 10) {
                    throw new IllegalStateException("cycles=" + cycles);
                }
                scale *= JOINT_DIFF_MAX / (maxdiff + 0.01);
                PmCartesian scaledDiffPt = diffPt.multiply(scale);
                PmCartesian newGoalPt = currentPt.add(scaledDiffPt);
                PmRotationVector scaledDiffRot = diffRotv.multiply(scale);
                PmRotationVector newGoalRotv = currentRotv.multiply(scaledDiffRot);
                newGoalPose = toPoseType(newGoalPt, newGoalRotv);
                this.goalPoseToCommandedPositions(newGoalPose);
                maxdiff = maxDiffDoubleArray(commandedJointPositions1, this.jointPositions, 360.0);
            }
        } catch (PmException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
        this.commandedJointPositions = commandedJointPositions1;
        return commandedJointPositions1;
    }

    private volatile @MonotonicNonNull
    Schema cmdSchema = null;

    private volatile File @Nullable [] cmdSchemaFiles = null;

    final void setCmdSchema(File[] fa) {
        try {
            Schema newCmdSchema = filesToCmdSchema(fa);
            this.cmdSchema = newCmdSchema;
            this.cmdSchemaFiles = fa;
            cleanupClientStatesThreadMap();
            for (SimServerClientState state : this.clientStates) {
                state.getCs().setCmdSchema(newCmdSchema);
                state.getCs().setCmdSchemaFiles(fa);
            }
        } catch (CRCLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    private volatile @MonotonicNonNull
    Schema statSchema = null;

    final void setStatSchema(File[] fa) {
        try {
            Schema newStatSchema = filesToStatSchema(fa);
            this.statSchema = newStatSchema;
            this.statSchemaFiles = fa;
            cleanupClientStatesThreadMap();
            for (SimServerClientState state : this.clientStates) {
                state.getCs().setStatSchema(newStatSchema);
            }
        } catch (CRCLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Set the value of moveStraight
     *
     * @param moveStraight new value of moveStraight
     */
    public void setMoveStraight(boolean moveStraight) {
        this.moveStraight = moveStraight;
    }

    private volatile boolean debugInterrupts = Boolean.getBoolean("crcl.ui.server.SimServerInner.debugInterrupts");

    /**
     * Get the value of debugInterrupts
     *
     * @return the value of debugInterrupts
     */
    public boolean isDebugInterrupts() {
        return debugInterrupts;
    }

    /**
     * Set the value of debugInterrupts
     *
     * @param debugInterrupts new value of debugInterrupts
     */
    public void setDebugInterrupts(boolean debugInterrupts) {
        this.debugInterrupts = debugInterrupts;
    }

    private volatile @Nullable
    CRCLServerSocket lastClosedServerSocket = null;

    public synchronized void closeServer() {
        try {
            SimServerInner.runningServers.remove(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeCount.incrementAndGet();
        if (null != clientThreadMap) {
            synchronized (clientThreadMap) {
                for (Thread t : clientThreadMap.values()) {
                    try {
                        if (t.isAlive()) {
                            if (debugInterrupts) {
                                Thread.dumpStack();
                                System.err.println("Interrupting t = " + t);
                                System.out.println("Interrupting t = " + t);
                                System.out.println("t.getStackTrace() = " + Arrays.toString(t.getStackTrace()));
                            }
                            t.interrupt();
                            t.join(2000);
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SimServerInner.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        Thread simThread = this.simulationThread;
        if (null != simThread) {
            try {
                try {
                    simThread.join(100);
                } catch (InterruptedException ex) {
                }
                if (simThread.isAlive()) {
                    if (debugInterrupts) {
                        Thread.dumpStack();
                        System.err.println("Interrupting simThread = " + simThread);
                        System.out.println("Interrupting simThread = " + simThread);
                        System.out.println("simThread.getStackTrace() = " + Arrays.toString(simThread.getStackTrace()));
                    }
                    simThread.interrupt();
                    simThread.join(100);
                }
            } catch (InterruptedException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            this.simulationThread = null;
        }
        if (null != clientStates) {
            for (SimServerClientState s : clientStates) {
                try {
                    s.close();
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }
            clientStates.clear();
            clientStatesSize = clientStates.size();
//            System.out.println("clientStates.size()=" + clientStatesSize);
        }
        closeCount.incrementAndGet();
        CRCLServerSocket closedServerSocket = this.crclServerSocket;
        if (null != crclServerSocket) {
            try {
                this.crclServerSocket.close();
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            this.crclServerSocket = null;
        }
        this.updateConnectedClients();
        this.lastStatusMap.clear();
        if (null != clientThreadMap) {
            synchronized (clientThreadMap) {
                for (Thread t : clientThreadMap.values()) {
                    try {
                        if (t.isAlive()) {
                            if (debugInterrupts) {
                                Thread.dumpStack();
                                System.err.println("Interrupting t = " + t);
                                System.out.println("Interrupting t = " + t);
                                System.out.println("t.getStackTrace() = " + Arrays.toString(t.getStackTrace()));
                            }
                            t.interrupt();
                            t.join(100);
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SimServerInner.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                this.clientThreadMap.clear();
            }
        }
        lastClosedServerSocket = closedServerSocket;
    }

    private boolean validateXMLSelected = true;

    public boolean isValidateXMLSelected() {
        return validateXMLSelected;
    }

    public void setValidateXMLSelected(boolean validateXMLSelected) {
        this.validateXMLSelected = validateXMLSelected;
    }

    private SimServerMenuOuter menuOuter() {
        if (null == outer) {
            throw new IllegalStateException("SimServerOuter not set.");
        }
        if (null == outer.getMenuOuter()) {
            throw new IllegalStateException("SimServerOuter has null SimServerMenuOuter.");
        }
        return outer.getMenuOuter();
    }

    private static final CRCLSocket.UnaryOperator<String> NOP_FILTER = x -> x;

    private void sendStatus(@Nullable CRCLSocket socket, final CRCLStatusType stat) {
        CRCLSocket curSocket = socket;
        try {
            if (null == curSocket) {
                if (!menuOuter().isSendStatusWithoutRequestSelected()
                        || null == clientStates
                        || clientStates.size() < 1) {
                    return;
                }
            }
            synchronized (stat) {
                CommandStatusType commandStatus = stat.getCommandStatus();
                if (null == commandStatus) {
                    commandStatus = new CommandStatusType();
                    stat.setCommandStatus(commandStatus);
                }
                CommandStateEnumType commandState = getCommandState();
                if (null == commandState) {
                    commandState = CRCL_WORKING;
                }
                if (null != socket) {
                    try {
                        socket.setAppendTrailingZero(menuOuter().isAppendZeroSelected());
                        socket.setRandomPacketing(menuOuter().isRandomPacketSelected());
                        socket.setReplaceHeader(menuOuter().isReplaceXmlHeaderSelected());
//                        if (menuOuter().isReplaceStateSelected()) {
//                            socket.setStatusStringOutputFilter(CRCLSocket.removeCRCLFromState);
//                        } else {
//                            socket.setStatusStringOutputFilter(NOP_FILTER);
//                        }
                        socket.setStatusStringOutputFilter(NOP_FILTER);
                        boolean new_state = true;
                        if (null != this.lastStatusMap) {
                            LastStatusInfo lsi = this.lastStatusMap.get(socket);
                            new_state = isStateNew(lsi, commandStatus, commandState);
                        }
                        if (menuOuter().isDebugSendStatusSelected() && new_state) {
                            outer.showDebugMessage("Status sent to " + socket.getInetAddress() + ":" + socket.getPort()
                                    + " CommandId="
                                    + commandStatus.getCommandID()
                                    + " StatusId="
                                    + commandStatus.getStatusID()
                                    + " State="
                                    + commandState);
                        }
                        socket.writeStatus(stat, validateXMLSelected);
                        if (debugUpdateStatusTime > 0) {
                            debugUpdateStatusTime = 0;
                        }
                        if (menuOuter().isDebugSendStatusSelected() && new_state) {
                            outer.showDebugMessage("writeStatus Complete");
                        }
                        if (new_state) {
                            LastStatusInfo lsi = new LastStatusInfo();
                            lsi.lastSentCid = commandStatus.getCommandID();
                            lsi.lastSentState = commandState;
                            this.lastStatusMap.put(socket, lsi);
                        }
                    } catch (CRCLException ex) {
                        LOGGER.log(Level.SEVERE, null, ex);
                        try {
                            socket.close();
                        } catch (IOException ex1) {
                            LOGGER.log(Level.SEVERE, null, ex1);
                        }
                        if (null != lastStatusMap) {
                            lastStatusMap.remove(socket);
                        }
                        cleanupClientStatesThreadMap();
                        for (SimServerClientState cs : clientStates) {
                            if (Objects.equals(cs.getCs(), socket)) {
                                checkClientStateSize(1);
                                clientStates.remove(cs);
                                checkClientStateSize(0);
                            }
                        }
                    }
                    return;
                }
                curSocket = clientStates.element().getCs();
//                if (menuOuter().isReplaceStateSelected()) {
//                    curSocket.setStatusStringOutputFilter(CRCLSocket.removeCRCLFromState);
//                } else {
//                    curSocket.setStatusStringOutputFilter(NOP_FILTER);
//                }
                curSocket.setStatusStringOutputFilter(NOP_FILTER);
                LastStatusInfo lsi = this.lastStatusMap.get(curSocket);
                boolean new_state = isStateNew(lsi, commandStatus, commandState);
                if (menuOuter().isDebugSendStatusSelected() && new_state) {
                    outer.showDebugMessage("Status sent to " + socket
                            + " CommandId="
                            + commandStatus.getCommandID()
                            + " StatusId="
                            + commandStatus.getStatusID()
                            + " State="
                            + commandState);
                }
                String xmls = curSocket.statusToString(stat, validateXMLSelected);
                int write_count = 0;
                cleanupClientStatesThreadMap();
                List<SimServerClientState> clientStatesList = new ArrayList<>(clientStates);
                for (int i = 0; i < clientStatesList.size(); i++) {
                    SimServerClientState clientState = clientStatesList.get(i);
                    curSocket = clientState.getCs();

                    try {
                        curSocket.setAppendTrailingZero(menuOuter().isAppendZeroSelected());
                        curSocket.setRandomPacketing(menuOuter().isRandomPacketSelected());
                        curSocket.setReplaceHeader(menuOuter().isReplaceXmlHeaderSelected());
                        curSocket.writeWithFill(xmls);
                        write_count++;
                    } catch (IOException ex) {
                        try {
                            LOGGER.log(Level.SEVERE, null, ex);
                            checkClientStateSize(1);
                            clientStates.remove(clientState);
                            checkClientStateSize(0);
                            Thread t = null;
                            synchronized (clientThreadMap) {
                                t = clientThreadMap.get(curSocket);
                                clientThreadMap.remove(curSocket);
                            }
                            if (null != t) {
                                if (t.isAlive()) {
                                    Thread.dumpStack();
                                    System.err.println("Interrupting t = " + t);
                                    System.out.println("Interrupting t = " + t);
                                    System.out.println("t.getStackTrace() = " + Arrays.toString(t.getStackTrace()));
                                    t.interrupt();
                                    t.join(100);
                                }
                            }
                            updateConnectedClients();
                        } catch (InterruptedException ex1) {
                            LOGGER.log(Level.SEVERE, null, ex1);
                        }
                    } catch (InterruptedException ex) {
                        LOGGER.log(Level.SEVERE, null, ex);
                    }
                }
                if (menuOuter().isDebugSendStatusSelected() && new_state) {
                    outer.showDebugMessage("writeStatus  to " + write_count + " clients complete.");
                }
                if (new_state && null != socket) {
                    lsi = new LastStatusInfo();
                    lsi.lastSentCid = commandStatus.getCommandID();
                    lsi.lastSentState = commandState;
                    this.lastStatusMap.put(socket, lsi);
                }
            }
        } catch (CRCLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            showMessage(ex + "\n" + ((curSocket != null) ? curSocket.getLastStatusString() : ""));
        }
    }

    @SuppressWarnings("nullness")
    private static final @NonNull
    CommandStateEnumType CRCL_WORKING = CommandStateEnumType.CRCL_WORKING;

    private boolean isStateNew(@Nullable LastStatusInfo lsi, CommandStatusType commandStatus, CommandStateEnumType commandState) {
        if (null == lsi) {
            return true;
        }
        CommandStateEnumType lastSentState = lsi.lastSentState;
        if (null == lastSentState) {
            return true;
        }
        return lsi.lastSentCid != commandStatus.getCommandID()
                || lastSentState.equals(commandState);
    }

    private boolean debugClientStateSize = false;

    private void checkClientStateSize(int maxSize) {
        clientStatesSize = clientStates.size();
        if (debugClientStateSize && clientStatesSize > maxSize) {
            System.out.println("clientStates.size()=" + clientStatesSize);
//            Map<Integer, List<CRCLSocket.CrclSocketCreatorInfo>> map = CRCLSocket.getCreatorMap();
//            CRCLSocket.printCreatorMap();
            List<SimServerClientState> origClientStateList = new ArrayList<>(clientStates);
            System.out.println("origClientStateList = " + origClientStateList);
            System.err.println("More than one simultaneous client : " + clientStates);
            this.cleanupClientStatesThreadMap();
            List<SimServerClientState> cleanedClientStateList = new ArrayList<>(clientStates);
            System.out.println("cleanedClientStateList = " + cleanedClientStateList);
        }
    }

    private boolean isCoordinated(PoseType pose) {
        if (pose instanceof PoseAndSetType) {
            PoseAndSetType pas = (PoseAndSetType) pose;
            return pas.isCoordinated();
        }
        return false;
    }

    public XMLGregorianCalendar getXMLGregorianCalendarNow()
            throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar now
                = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
        return now;
    }

    private void incStatusId() {
        final CRCLStatusType stat = this.status.get();
        synchronized (stat) {
            CommandStatusType cst = stat.getCommandStatus();
            if (null != cst) {
                cst.setStatusID(cst.getStatusID() + 1);
                if (cmdLog.size() > 0) {
                    if (cst.getCommandState() != CRCL_ERROR) {
                        cst.setStateDescription("");
                    }
                }
            }
        }
    }

    @SuppressWarnings("nullness")
    private static final @NonNull
    CommandStateEnumType CRCL_ERROR = CommandStateEnumType.CRCL_ERROR;

    public void teleportJoints(double newJointPositions[]) {
        if (null == newJointPositions) {
            throw new IllegalArgumentException("null == newJointPositions");
        }
        if (null == jointPositions) {
            throw new IllegalArgumentException("null == jointPositions");
        }
        if (null == commandedJointPositions) {
            throw new IllegalArgumentException("null == commandedJointPositions");
        }
        if (null == jointVelocites) {
            throw new IllegalArgumentException("null == jointVelocites");
        }
        if (newJointPositions.length != jointPositions.length) {
            throw new IllegalArgumentException(newJointPositions.length + " newJointPositions.length != jointPositions.length " + jointPositions.length);
        }
        System.arraycopy(newJointPositions, 0, jointPositions, 0, jointPositions.length);
        System.arraycopy(newJointPositions, 0, commandedJointPositions, 0, jointPositions.length);
        for (int i = 0; i < jointVelocites.length; i++) {
            jointVelocites[i] = 0;
        }
        CRCLStatusType stat = this.status.get();

        JointStatusesType jsst = stat.getJointStatuses();

        Iterable<JointStatusType> jsIterable = getNonNullIterable(jsst.getJointStatus());
        for (JointStatusType jst : jsIterable) {
            int jointNumber = jst.getJointNumber();
            if (jointNumber > 0 && jointNumber < newJointPositions.length - 1) {
                jst.setJointPosition(newJointPositions[jointNumber - 1]);
            }
        }
    }

    private final AtomicInteger workingCount = new AtomicInteger();
    private volatile int maxWorkingCount = 0;

    /**
     *
     * @return the boolean
     */
    private boolean updateStatus() {
        final CRCLServerSocket<SimServerClientState> crclServerSocket1 = crclServerSocket;
        if (null == crclServerSocket1) {
            throw new NullPointerException("crclServerSocket");
        }
        crclServerSocket1.runUpdateServerSideStatusRunnables(null);
        boolean jointschanged = false;
        PoseType curGoalPose = null;
        if (checkForceFail()) {
            return true;
        }
        double[] commandedJointPositions1 = this.commandedJointPositions;
        try {
            final CRCLStatusType stat = this.status.get();
            final CRCLStatusType statCopy;
            synchronized (stat) {
                if (!outer.isEditingStatus()) {
                    if (debugCmdStartTime > 0) {
                        debugUpdateStatusTime = System.currentTimeMillis();
                        long diffdebugUpdateStatusTimedebugCmdStartTime = debugUpdateStatusTime - debugCmdStartTime;
                        System.out.println("diffdebugUpdateStatusTimedebugCmdStartTime = " + diffdebugUpdateStatusTimedebugCmdStartTime);
                        debugCmdStartTime = 0;
                    }
                    CommandStatusType commandStatus = stat.getCommandStatus();
                    if (null == commandStatus) {
                        commandStatus = new CommandStatusType();
                        commandStatus.setCommandID(crclServerSocket1.getLastRecievedCommandID());
                        commandStatus.setStatusID(1);
                        commandStatus.setCommandState(crclServerSocket1.getCommandStateEnum());
                        stat.setCommandStatus(commandStatus);
                    }
                    this.incStatusId();
                    PoseType newGoalPose = this.goalPose;
                    if (null == newGoalPose
                            && this.currentWaypoint < waypoints.size() - 1) {
                        int new_waypoint = this.currentWaypoint + 1;
                        this.setCurrentWaypoint(new_waypoint);
                        newGoalPose = this.waypoints.get(this.currentWaypoint);
                        setGoalPose(newGoalPose);
                    }
                    PoseType startingCurrentPose = getPose();
                    if (null == startingCurrentPose) {
                        throw new NullPointerException("getPose() returned null");
                    }
                    PoseType currentPose = copy(startingCurrentPose);
                    if (null != newGoalPose && null != currentPose) {
                        curGoalPose = this.limitSpeedAccel(newGoalPose, currentPose);
                        if (this.moveStraight || isCoordinated(newGoalPose)) {
                            commandedJointPositions1 = getStraightMoveCommandedJointVals(curGoalPose);
                        } else {
                            goalPoseToCommandedPositions(curGoalPose);
                        }
                    }
                    if (null == jointPositions) {
                        throw new IllegalStateException("null == jointPositions");
                    }
                    if (null == jointVelocites) {
                        throw new IllegalStateException("null == jointVelocites");
                    }
                    if (null == commandedJointPositions1) {
                        commandedJointPositions1 = Arrays.copyOf(jointPositions, jointPositions.length);
                    }
                    synchronized (jointPositions) {
                        List<JointStatusType> jsl = new ArrayList<>();
                        for (int i = 0; i < jointPositions.length; i++) {
                            final double JOINT_DIFF_MAX = getAllowedJointDiff(i);
                            double jointPositionI = jointPositions[i];
                            final double lastJointPositionI = jointPositionI;
                            double commandedJointPositionI = commandedJointPositions1[i];
                            final double origCommandedJointPositionI = commandedJointPositionI;
                            double commandToCurrentJointDiff = commandedJointPositionI - jointPositionI;
                            double absCommandToCurrentJointDiff = Math.abs(commandToCurrentJointDiff);
                            double jointIncrement = 0.0;
                            if (absCommandToCurrentJointDiff < JOINT_DIFF_MAX) {
                                if (absCommandToCurrentJointDiff > 1e-4) {
                                    jointschanged = true;
                                }
                                jointPositionI = commandedJointPositionI;
                            } else {
                                jointIncrement = JOINT_DIFF_MAX * Math.signum(commandToCurrentJointDiff);
                                jointPositionI += jointIncrement;
                                if (robotType == SimRobotEnum.SIMPLE && curGoalPose != null) {
                                    jointPositionI = commandedJointPositionI;
                                }
                                jointschanged = true;
                            }
                            boolean atMinLimit = false;
                            if (jointmins != null && jointmins.length > i) {
                                double jointminI = jointmins[i];
                                if (jointPositionI < jointminI) {
                                    newGoalPose = null;
                                    this.goalPose = null;
                                    setCommandState(CRCL_ERROR, "jointPositionI < jointminI : i=" + i + ",jointPositionI=" + jointPositionI + ",jointminI=" + jointminI);
                                    showMessage("Joint " + (i + 1) + " at " + jointPositionI + " less than limit " + jointminI);
                                    jointPositionI = jointminI;
                                    commandedJointPositionI = jointPositionI;
                                    commandedJointPositions1[i] = commandedJointPositionI;
                                    atMinLimit = true;
                                }
                            }
                            boolean atMaxLimit = false;
                            if (jointmaxs != null && jointmaxs.length > i) {
                                double jointmaxI = jointmaxs[i];
                                if (jointPositionI > jointmaxI) {
                                    newGoalPose = null;
                                    this.goalPose = null;
                                    setCommandState(CRCL_ERROR, "jointPositionI > jointmaxI : i=" + i + ",jointPositionI=" + jointPositionI + ",jointmaxI=" + jointmaxI);
                                    showMessage("Joint " + (i + 1) + " at " + jointPositionI + " more than limit " + jointmaxI);
                                    jointPositionI = jointmaxI;
                                    commandedJointPositionI = jointPositionI;
                                    commandedJointPositions1[i] = commandedJointPositionI;
                                    atMaxLimit = true;
                                }
                            }

                            double jointVelocitesI = ((jointPositionI - lastJointPositionI) * 1000.0) / delayMillis;
                            if (Math.abs(jointVelocitesI) < 0.01 * absCommandToCurrentJointDiff) {
                                final String exceptionstring = "Joint velocity too slow to complete in 100 cycles : \n"
                                        + "i=" + i + ", atMinLimit=" + atMinLimit + ", atMaxLimit=" + atMaxLimit + ",\n"
                                        + "JOINT_DIFF_MAX=" + JOINT_DIFF_MAX + ", jointVelocitesI=" + jointVelocitesI + ",\n"
                                        + "absCommandToCurrentJointDiff=" + absCommandToCurrentJointDiff + ",delayMillis=" + delayMillis + ",\n"
                                        + "(jointPositionI - lastJointPositionI)=" + (jointPositionI - lastJointPositionI) + ",\n"
                                        + "jointschanged=" + jointschanged + ",jointIncrement=" + jointIncrement + ",\n"
                                        + "origCommandedJointPositionI=" + origCommandedJointPositionI + ", \n"
                                        + "jointPositionI=" + jointPositionI + ", origJointPositionI=" + lastJointPositionI + ", (origJointPositionI-jointPositionI)=" + (lastJointPositionI - jointPositionI) + "\n";
                                System.out.println("");
                                System.out.flush();
                                System.err.println("exceptionstring = " + exceptionstring);
                                throw new IllegalStateException(exceptionstring);
                            }

                            JointStatusType js = new JointStatusType();
                            js.setJointNumber(i + 1);
                            if (cjrMap.size() > 0) {
                                clearJointStatus(js);
                                ConfigureJointReportType cjrt = this.cjrMap.get(js.getJointNumber());
                                if (null != cjrt) {
                                    if (cjrt.getJointNumber() == js.getJointNumber()) {
                                        if (cjrt.isReportPosition()) {
                                            js.setJointPosition(jointPositionI);
                                        }
                                        if (cjrt.isReportVelocity()) {
                                            js.setJointVelocity(jointVelocitesI);
                                        }
                                        if (cjrt.isReportTorqueOrForce()) {
                                            js.setJointTorqueOrForce(10.0);
                                        }
                                    }
                                }
                                if (commandStatus.getCommandState() == CRCL_WORKING
                                        && cmdLog.get(cmdLog.size() - 1) instanceof ConfigureJointReportsType) {
                                    setCommandState(CRCL_DONE);
                                }
                            } else {
                                js.setJointPosition(jointPositionI);
                            }
                            jointVelocites[i] = jointVelocitesI;
                            jointPositions[i] = jointPositionI;
                            jsl.add(js);
                        }
                        JointStatusesType jsst = stat.getJointStatuses();
                        final List<JointStatusType> jointStatusesListLocal
                                = jsst.getJointStatus();
                        jointStatusesListLocal.clear();
                        jointStatusesListLocal.addAll(jsl);
//                        CRCLUtils.clearAndSetList(jointStatusesListLocal, jsl);
                    }
                    if (jointschanged
                            || null == currentPose) {
                        setPoseFromJoints(currentPose);
                    }
                    outer.updatePanels(jointschanged);
                    if (executingMoveCommand
                            && commandStatus.getCommandState() == CRCL_WORKING) {
                        boolean finished = (null != goalPose && isFinishedMove());
                        if (!jointschanged || finished) {
                            if (null == newGoalPose
                                    || null == this.waypoints
                                    || this.currentWaypoint >= this.waypoints.size()) {
                                setCommandState(CRCL_DONE);
                                if (menuOuter().isDebugMoveDoneSelected()) {
                                    outer.showDebugMessage("SimServerInner DONE move command: " + commandStatus.getCommandID());
                                    outer.showDebugMessage("SimServerInner jointpositions = " + Arrays.toString(jointPositions));
                                }
                                this.setMoveStraight(false);
                                this.setWaypoints(null);
                            } else {
                                newGoalPose = null;
                                this.goalPose = null;
                            }
                        }
                    }
                    if (commandStatus.getCommandState() == CRCL_WORKING) {
                        int wc = workingCount.incrementAndGet();
                        if (wc > maxWorkingCount) {
                            maxWorkingCount = wc;
//                            System.out.println("Working on same command for  " + wc + " cycles.");
                        }
                    } else {
                        workingCount.set(0);
                    }
                    cycle_count++;
                }
                if (commandedJointPositions1 != null) {
                    this.commandedJointPositions = commandedJointPositions1;
                }
                if (null == crclServerSocket1) {
                    throw new NullPointerException("crclServerSocket");
                }
                crclServerSocket1.runUpdateServerSideStatusRunnables(stat);
                statCopy = CRCLCopier.copy(stat);
            }
            if (null == statCopy) {
                throw new NullPointerException("statCopy");
            }
            XFuture<CRCLStatusType> f;
            while (!statusFutures.isEmpty()
                    && ((f = statusFutures.poll()) != null)) {
                f.complete(statCopy);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String message = e.getMessage();
            if (null != message) {
                outer.showMessage(message);
                setCommandState(CRCL_ERROR, message);
            } else {
                outer.showMessage(e.toString());
                setCommandState(CRCL_ERROR, e.toString());
            }

            if (null != commandedJointPositions1 && null != jointPositions) {
                for (int i = 0; i < jointPositions.length && i < commandedJointPositions1.length; i++) {
                    commandedJointPositions1[i] = jointPositions[i];
                }
                this.commandedJointPositions = commandedJointPositions1;
            }
            this.goalPose = null;
            if (testing) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    @SuppressWarnings("nullness")
    private void setPoseFromJoints(@Nullable PoseType currentPose) throws PmException {
        switch (robotType) {
            case PLAUSIBLE:
                setPose(skPlausible.jointsToPose(jointPositions, currentPose));
                break;

            case SIMPLE:
                setPose(skSimple.jointsToPose(jointPositions, currentPose));
                break;
        }
    }

    @SuppressWarnings("nullness")
    private void clearJointStatus(JointStatusType js) {
        js.setJointPosition(null);
        js.setJointVelocity(null);
        js.setJointTorqueOrForce(null);
    }

    private double getJointDiffMax() {
        return jointSpeedMax * delayMillis * 1e-3;
    }

    private double getAllowedJointDiff(int i) {
        if (this.commandedJointVelocities == null
                && this.commandedJointAccellerations == null) {
            return getJointDiffMax();
        }
        double veldiff
                = this.commandedJointVelocities == null
                        ? Double.POSITIVE_INFINITY
                        : this.commandedJointVelocities[i] * delayMillis * 1e-3;
        double accdiff = Double.POSITIVE_INFINITY;
        return Math.min(getJointDiffMax(), Math.min(accdiff, veldiff));
    }

    /**
     *
     * @param _goalPose the value of _goalPose
     */
    public void goalPoseToCommandedPositions(PoseType _goalPose) {
        if (null == commandedJointPositions) {
            if (null != jointPositions) {
                commandedJointPositions = Arrays.copyOf(jointPositions, jointPositions.length);
            } else {
                throw new IllegalStateException("null == commandedJointPositions && null == jointPositions");
            }
        }
        try {
            double jointsIn[] = this.commandedJointPositions;
            PoseType inputPose = _goalPose;
            double jointsOut[] = poseToJoints(jointsIn, inputPose);
            this.commandedJointPositions = jointsOut;
        } catch (Exception exception) {
            Logger.getLogger(SimServerInner.class.getName()).log(Level.SEVERE, "", exception);
            if (exception instanceof RuntimeException) {
                throw (RuntimeException) exception;
            } else {
                throw new RuntimeException(exception);
            }
        }
    }

    private String initStatusFilename;

    /**
     * Get the value of initStatusFilename
     *
     * @return the value of initStatusFilename
     */
    public String getInitStatusFilename() {
        return initStatusFilename;
    }

    /**
     * Set the value of initStatusFilename
     *
     * @param initStatusFilename new value of initStatusFilename
     */
    public void setInitStatusFilename(String initStatusFilename) {
        this.initStatusFilename = initStatusFilename;
    }

    public double[] poseToJoints(double[] jointsIn, PoseType inputPose) throws IllegalArgumentException, PmException {
        double jointsOut[];
        switch (robotType) {
            case PLAUSIBLE:
                jointsOut = skPlausible.poseToJoints(jointsIn, inputPose);
                break;

            case SIMPLE:
                jointsOut = skSimple.poseToJoints(jointsIn, inputPose);
                break;

            default:
                throw new IllegalArgumentException("bad robotType=" + robotType);
        }
        return jointsOut;
    }

    /**
     * Get the value of currentWaypoint
     *
     * @return the value of currentWaypoint
     */
    public int getCurrentWaypoint() {
        return currentWaypoint;
    }

    /**
     * Set the value of currentWaypoint
     *
     * @param currentWaypoint new value of currentWaypoint
     */
    public void setCurrentWaypoint(int currentWaypoint) {
        this.currentWaypoint = currentWaypoint;
        outer.finishSetCurrentWaypoint(currentWaypoint);
    }

    public void printClientStates(final PrintStream ps) {
        try {
            final CRCLStatusType stat = this.status.get();
            synchronized (stat) {
                ps.println("Start printClientStates");
                ps.println("SimServerInner.this = " + SimServerInner.this);
                if (null != clientStates) {
                    clientStates.forEach(ps::println);
                }
                ps.println("cmdLog=" + this.cmdLog);
                if (!cmdLog.isEmpty()) {
                    String cmdString = this.getCheckerCRCLSocket().commandToPrettyString(cmdLog.get(cmdLog.size() - 1));
                    ps.println("SimServerInner : cmdString=" + cmdString);
                }
                String statString = this.getCheckerCRCLSocket().statusToPrettyString(stat, false);
                ps.println("SimServerInner : statString=" + statString);
                ps.println("end SimServerInner statString");
                if (null != this.cmdQueue) {
                    ps.println("cmdQueue.peek() = " + cmdQueue.peek());
                }
                this.updateStatus();
                ps.println("End printClientStates");
            }
        } catch (Exception ex) {
            Logger.getLogger(SimServerInner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("nullness")
    public boolean isGripperCommand(CRCLCommandInstanceType cmdInstance) {
        return Optional.ofNullable(cmdInstance)
                .map(CRCLCommandInstanceType::getCRCLCommand)
                .map(CRCLCommandType::getClass)
                .map(gripperCommands::contains)
                .orElse(false);
    }

//    private void readCommandsRepeatedly(SimServerClientState state, final int start_close_count) {
//        try {
//            while (!Thread.currentThread().isInterrupted()) {
//                if (closeCount.get() > start_close_count) {
//                    return;
//                }
////                final CRCLSocket cs = state.getCs();
//                final CRCLCommandInstanceType cmdInstance
//                        = state.getCs().readCommand(validateXMLSelected);
//                handleCommandReceived(cmdInstance, state);
//            }
//        } catch (SocketException se) {
//            final CRCLSocket cs = state.getCs();
//
//            try {
//                cs.close();
//            } catch (IOException ex1) {
//            }
//            this.clientStates.remove(state);
//            clientStatesSize = clientStates.size();
////            System.out.println("clientStates.size()=" + clientStatesSize);
//        } catch (CRCLException ex) {
//            final CRCLSocket cs = state.getCs();
//            if (ex.getCause() instanceof EOFException) {
//                try {
//                    cs.close();
//                } catch (IOException ex1) {
//                    LOGGER.log(Level.SEVERE, null, ex1);
//                }
//                this.clientStates.remove(state);
//                clientStatesSize = clientStates.size();
////                System.out.println("clientStates.size()=" + clientStatesSize);
//                return;
//            }
//            if (null != ex.getCause() && ex.getCause().getCause() instanceof EOFException) {
//                try {
//                    cs.close();
//                } catch (IOException ex1) {
//                    LOGGER.log(Level.SEVERE, null, ex1);
//                }
//                this.clientStates.remove(state);
//                clientStatesSize = clientStates.size();
////                System.out.println("clientStates.size()=" + clientStatesSize);
//                return;
//            }
//            if (closeCount.get() <= start_close_count) {
//                System.err.println("String to parse was:" + cs.getLastCommandString());
//                LOGGER.log(Level.SEVERE, null, ex);
//                this.showMessage(ex.toString() + "\nString to parse was:" + cs.getLastCommandString());
//            }
//        } catch (IOException ex) {
//            final CRCLSocket cs = state.getCs();
//            if (closeCount.get() <= start_close_count) {
//                String str = cs.getReadInProgressString();
//                if (str.length() == 0) {
//                    return;
//                }
//                LOGGER.log(Level.SEVERE, "ReadInProgressString:{0}", str);
//                LOGGER.log(Level.SEVERE, null, ex);
//            }
//            clientStatesSize = clientStates.size();
////            System.out.println("clientStates.size()=" + clientStatesSize);
//        } finally {
//            final CRCLSocket cs = state.getCs();
//            try {
//                cs.close();
//            } catch (IOException ex1) {
//            }
//            this.clientStates.remove(state);
//            clientStatesSize = clientStates.size();
////            System.out.println("clientStates.size()=" + clientStatesSize);
//        }
//    }
    private void handleCommandReceived(final CRCLCommandInstanceType cmdInstance, SimServerClientState state) {
//        LOGGER.log(Level.FINER, () -> "cmdInstance = " + cmdInstance);
        if (null != cmdInstance) {
            CRCLCommandType cmd = cmdInstance.getCRCLCommand();
            if (null != cmd) {
//                LOGGER.log(Level.FINEST, () -> "SimServerInner.readCommandsRepeatedly() : cmd = " + cmd + ", state=" + state);
                if (cmd instanceof GetStatusType) {
//                    state.getStatusRequests++;
//                    state.lastStatRequestTime = System.currentTimeMillis();
//                    GetStatusType getStatus = (GetStatusType) cmd;
//                    if (debug_this_command || menuOuter().isDebugReadCommandSelected()
//                            && getStatus.getCommandID() != state.getStatusCmdId) {
//                        outer.showDebugMessage("SimServerInner.readCommandsRepeatedly() :  (getStatus=" + getStatus + " ID=" + getStatus.getCommandID() + ") state = " + state);
//                    }
//                    state.getStatusCmdId = getStatus.getCommandID();
//                    //                        if (enableGetStatusIDCheck && null != state.cmdId
////                                && !state.getStatusCmdId.equals(state.cmdId)) {
////                            LOGGER.log(Level.SEVERE, "SimServerInner.readCommandsRepeatedly() GetStatusIDCheck failed: state.getStatusCmdId={0}, state.cmdId = {1},status={2}", new Object[]{state.getStatusCmdId, state.cmdId, CRCLSocket.statToDebugString(status)});
////                            LOGGER.setLevel(Level.OFF);
////                            new Thread(() -> closeServer()).start();
////                            return;
////                        }
//                    final CRCLStatusType stat = this.getLastUpdateServerSideStatusCopy();
//                    synchronized (stat) {
//                        CommandStatusType cst = stat.getCommandStatus();
//                        if (null == cst) {
//                            cst = new CommandStatusType();
//                            cst.setCommandID(cmd.getCommandID());
//                            cst.setStatusID(1);
//                            cst.setCommandState(crclServerSocket.getCommandStateEnum());
//                            stat.setCommandStatus(cst);
//                        } else {
//                            cst.setCommandState(crclServerSocket.getCommandStateEnum());
//                        }
//                        SimServerInner.this.sendStatus(state.getCs(), stat);
//                    }
                    throw new RuntimeException("this command should have been handled by CRCLServerSocket.handleAutomaticEvents");
                } else {
                    debug_this_command = false;
                    if (debugCmdSendTime > 0) {
                        long debugCmdRecvTime = System.currentTimeMillis();
                        long diffDebugCmdSend = debugCmdRecvTime - debugCmdSendTime;
                        System.out.println("diffDebugCmdSend = " + diffDebugCmdSend);
                        System.out.println("cmd = " + cmd);
                        if (null != cmd) {
                            System.out.println("cmd.getCommandID() = " + cmd.getCommandID());
                        }
                        debugCmdSendTime = 0;
                        debug_this_command = true;
                    }
                    state.cmdsRecieved++;
                    state.lastCmdTime = System.currentTimeMillis();
                    state.cmdId = cmd.getCommandID();
                    if (debug_this_command || menuOuter().isDebugReadCommandSelected()) {
                        outer.showDebugMessage("SimServerInner.readCommandsRepeatedly() : cmdInstance.getCRCLCommand() = " + cmd
                                + " cmdInstance.getCRCLCommand().getCommandID() = " + cmd.getCommandID() + ", state=" + state);
                    }
                    cmdQueuePutTime = System.currentTimeMillis();
                    SimServerInner.this.cmdQueue.add(cmdInstance);
                    cmdQueueCmdsOffered++;
                    if (cmdQueueMaxSize < cmdQueue.size()) {
                        cmdQueueMaxSize = cmdQueue.size();
                    }
                    if (isGripperCommand(cmdInstance)) {
                        if (null != gripperSocket && gripperSocket.isConnected()) {
                            gripperCmdQueue.add(cmdInstance);
                        }
                    }
                }
            }
        }
    }
    private volatile int clientStatesSize = 0;

    private final CRCLServerSocketEventListener<SimServerClientState> crclServerSocketEventListener
            = new CRCLServerSocketEventListener<SimServerClientState>() {
        @Override
        public void accept(CRCLServerSocketEvent<SimServerClientState> evt) {
            handleCRCLServerSocketEvent(evt);
        }
    };

    private void handleCRCLServerSocketEvent(CRCLServerSocketEvent<SimServerClientState> evt) {
        final CRCLServerSocket<SimServerClientState> crclServerSocket1 = crclServerSocket;
        if(null == crclServerSocket1) {
            throw new NullPointerException("crclServerSocket1");
        }
        switch (evt.getEventType()) {
            case NEW_CRCL_CLIENT:
                CRCLSocket cs = evt.getSource();
                if (cs == null) {
                    throw new NullPointerException("evt.getSource() == null : evt=" + evt);
                }
                handleNewClient(cs);
                break;

            case CRCL_COMMAND_RECIEVED:
                final CRCLCommandInstanceType cmdInstance = evt.getInstance();
                if (null == cmdInstance) {
                    throw new NullPointerException("evt.getInstance() == null : evt=" + evt);
                }
                final SimServerClientState state = evt.getState();
                if (null == state) {
                    throw new NullPointerException("evt.getState() == null : evt=" + evt);
                }
                handleCommandReceived(cmdInstance, state);
                break;

            case GUARD_LIMIT_REACHED:
                executeStopMotionCmd();
                if (null == crclServerSocket1) {
                    throw new RuntimeException("null==crclServerSocket");
                }
                crclServerSocket1.comleteGuardTrigger();
                break;

            case EXCEPTION_OCCURRED:
                executeStopMotionCmd();
                crclServerSocket1.setCommandStateEnum(CRCL_ERROR);
                final Exception exception = evt.getException();
                if (null != exception && null != exception.getMessage()) {
                    crclServerSocket1.setStateDescription(exception.getMessage());
                } else {
                    crclServerSocket1.setStateDescription("evt=" + evt);
                }
                break;

            case SERVER_CLOSED:
                executeStopMotionCmd();
                break;
        }
    }

    private void handleNewClient(CRCLSocket cs) {
        final SimServerClientState state = new SimServerClientState(cs);
//                cs.setEXIEnabled(menuOuter().isEXISelected());
        cleanupClientStatesThreadMap();
        clientStates.add(state);
        checkClientStateSize(1);
        this.updateConnectedClients();
    }

    @SuppressWarnings("nullness")
    private CRCLSocket createCrclSocket(@Nullable Socket s) {
        if (null == cmdSchema) {
            throw new IllegalStateException("null==cmdSchema");
        }
        if (null == statSchema) {
            throw new IllegalStateException("null==statSchema");
        }
        final CRCLSocket cs
                = CRCLSocket.newCRCLSocketForSocketSchemas(s, cmdSchema, statSchema, null);
        return cs;
    }

    public double getCurTransSpeed() {
        return curTransSpeed;
    }

    public void setCurTransSpeed(double curTransSpeed) {
        this.curTransSpeed = curTransSpeed;
    }

    public double getCommandedTransSpeed() {
        return commandedTransSpeed;
    }

    public void setCommandedTransSpeed(double commandedTransSpeed) {
        this.commandedTransSpeed = commandedTransSpeed;
    }

    public double getCurTransAccel() {
        return curTransAccel;
    }

    public void setCurTransAccel(double curTransAccel) {
        this.curTransAccel = curTransAccel;
    }

    public double getCommandedTransAccel() {
        return commandedTransAccel;
    }

    public void setCommandedTransAccel(double commandedTransAccel) {
        this.commandedTransAccel = commandedTransAccel;
    }

    public double getCurRotSpeed() {
        return curRotSpeed;
    }

    public void setCurRotSpeed(double curRotSpeed) {
        this.curRotSpeed = curRotSpeed;
    }

    public double getCommandedRotSpeed() {
        return commandedRotSpeed;
    }

    public void setCommandedRotSpeed(double commandedRotSpeed) {
        this.commandedRotSpeed = commandedRotSpeed;
    }

    public double getCurRotAccel() {
        return curRotAccel;
    }

    public void setCurRotAccel(double curRotAccel) {
        this.curRotAccel = curRotAccel;
    }

    public double getCommandedRotAccel() {
        return commandedRotAccel;
    }

    public void setCommandedRotAccel(double commandedRotAccel) {
        this.commandedRotAccel = commandedRotAccel;
    }

    /**
     * Get the value of delayMillis
     *
     * @return the value of delayMillis
     */
    public long getDelayMillis() {
        return delayMillis;
    }

    /**
     * Set the value of delayMillis
     *
     * @param delay_millis new value of delayMillis
     */
    public void setDelayMillis(long delay_millis) {
        this.delayMillis = delay_millis;
    }

    /**
     * Get the value of expectedEndPoseTolerance
     *
     * @return the value of expectedEndPoseTolerance
     */
    public PoseToleranceType getExpectedEndPoseTolerance() {
        return expectedEndPoseTolerance;
    }

    /**
     * Set the value of expectedEndPoseTolerance
     *
     * @param expectedEndPoseTolerance new value of expectedEndPoseTolerance
     */
    public void setExpectedEndPoseTolerance(PoseToleranceType expectedEndPoseTolerance) {
        this.expectedEndPoseTolerance = expectedEndPoseTolerance;
    }

    /**
     * Get the value of expectedIntermediatePoseTolerance
     *
     * @return the value of expectedIntermediatePoseTolerance
     */
    public PoseToleranceType getExpectedIntermediatePoseTolerance() {
        return expectedIntermediatePoseTolerance;
    }

    /**
     * Set the value of expectedIntermediatePoseTolerance
     *
     * @param expectedIntermediatePoseToleranceType new value of
     * expectedIntermediatePoseTolerance
     */
    public void setExpectedIntermediatePoseTolerance(PoseToleranceType expectedIntermediatePoseToleranceType) {
        this.expectedIntermediatePoseTolerance = expectedIntermediatePoseToleranceType;
    }

    public void updateConnectedClients() {
        outer.updateConnectedClients(Math.max(clientStates.size(), clientThreadMap.size()));
    }

    private void readCommand() throws ParserConfigurationException, IOException, SAXException {
        if (dwellEndTime > 0 && System.currentTimeMillis() < dwellEndTime) {
            return;
        }
        if (dwellEndTime > 0) {
            setCommandState(CRCL_DONE);
            dwellEndTime = 0;
            return;
        }
        if (cmdQueue.size() > cmdQueueMaxSize) {
            cmdQueueMaxSize = cmdQueue.size();
        }
        long cmdQueuePollStart = System.currentTimeMillis();
        CRCLCommandInstanceType instance = cmdQueue.poll();
        long cmdQueuePollEnd = System.currentTimeMillis();
        cmdQueuePollReturnCount++;
        if (instance != null) {
            cmdQueuePollReturnNonNullCount++;
        }
        long cmdQueuePollTime = cmdQueuePollEnd - cmdQueuePollStart;
        if (debug_this_command) {
            System.out.println("cmdQueuePollTime = " + cmdQueuePollTime);
        }
        if (cmdQueuePollTime > maxCmdQueuePollTime) {
            maxCmdQueuePollTime = cmdQueuePollTime;
        }
        int cmdsInCycle = 0;
        while (null != instance) {
            lastReadCommandInstance = instance;
            cmdsInCycle++;
            if (debug_this_command) {
                System.out.println("cmdsInCycle = " + cmdsInCycle);
            }
            if (cmdQueuePutTime > 0 && cmdQueue.isEmpty()) {
                long cmdQueueEmptyTime = System.currentTimeMillis();
                long diffCmdQueuePutEmpty = cmdQueueEmptyTime - cmdQueuePutTime;
                cmdQueuePutTime = 0;
                if (diffCmdQueuePutEmpty > maxDiffCmdQueuePutEmpty) {
                    maxDiffCmdQueuePutEmpty = diffCmdQueuePutEmpty;
                }
            }
            CRCLCommandType cmd = instance.getCRCLCommand();
            if (null == cmd) {
                System.err.println("cmd is null");
                return;
            }
            if (debug_this_command || menuOuter().isDebugReadCommandSelected()) {
                outer.showDebugMessage("SimServerInner.readCommand() : cmd = " + cmd
                        + " cmd.getCommandID() = " + cmd.getCommandID());
            }
            cmdLog.add(cmd);
            while (cmdLog.size() > 100) {
                cmdLog.remove(0);
            }
            String cmdName = CRCLSocket.commandToSimpleString(cmd);
            outer.updateCurrentCommandType(cmdName);
            final CRCLStatusType stat = this.getLastUpdateServerSideStatusCopy();
            if(null == stat) {
                throw new NullPointerException("stat");
            }
            synchronized (stat) {
                CommandStatusType cst = stat.getCommandStatus();
                if (null == cst) {
                    cst = new CommandStatusType();
                    cst.setCommandState(CRCL_WORKING);
                    stat.setCommandStatus(cst);
                    setCommandState(CRCL_WORKING);;
                } else if (cst.getCommandState() == CommandStateEnumType.CRCL_DONE) {
                    setCommandState(CRCL_WORKING);;
                    cst.setCommandState(CRCL_WORKING);
                }
            }
            executingMoveCommand = false;
            double[] commandedJointPositions1 = commandedJointPositions;
            if (cmd instanceof InitCanonType) {
                InitCanonType init = (InitCanonType) cmd;
                initialize();
            } else if (cmd instanceof StopMotionType) {
                StopMotionType stop = (StopMotionType) cmd;
                executeStopMotionCmd();
            } else {
                if (stat.getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_DONE) {
                    this.setWaypoints(null);
                }
                if (!menuOuter().isInitializedSelected()
                        && !(cmd instanceof EndCanonType)) {
                    String className = cmd.getClass().getCanonicalName();
                    if (null == className) {
                        className = cmd.getClass().getName();
                    }
                    final String message = "Not initialized when " + className.substring("crcl.base.".length()) + " recieved.";
                    showMessage(message);
                    setCommandState(CRCL_ERROR, message);
                    return;
                }
                if (null == jointPositions) {
                    throw new IllegalStateException("null == jointPositions");
                }
                final CRCLServerSocket<SimServerClientState> crclServerSocket1 = crclServerSocket;
                if(null == crclServerSocket1) {
                    throw new NullPointerException("crclServerSocket1");
                }
                if (cmd instanceof SetEndEffectorType) {
                    SetEndEffectorType seet = (SetEndEffectorType) cmd;
                    outer.updateEndEffector(Double.toString(seet.getSetting()));
                    setCommandState(CRCL_DONE);
                    crclServerSocket1.addToUpdateServerSideRunnables(() -> {
                        CRCLStatusType statToChange = this.status.get();
                        final SettingsStatusType settingsStatus = statToChange.getSettingsStatus();
                        settingsStatus.setEndEffectorSetting(seet.getSetting());
                    });
                } else if (cmd instanceof CloseToolChangerType) {
                    CloseToolChangerType ctc = (CloseToolChangerType) cmd;
                    outer.updateToolChangerIsOpen(false);
                    setCommandState(CRCL_DONE);
                } else if (cmd instanceof OpenToolChangerType) {
                    OpenToolChangerType otc = (OpenToolChangerType) cmd;
                    outer.updateToolChangerIsOpen(true);
                    setCommandState(CRCL_DONE);
                } else if (cmd instanceof MessageType) {
                    MessageType mt = (MessageType) cmd;
                    this.showMessage("MESSAGE: " + mt.getMessage() + "\n");
                    setCommandState(CRCL_DONE);
                } else if (cmd instanceof ConfigureJointReportsType) {
                    cjrs = (ConfigureJointReportsType) cmd;
                    if (cjrs.isResetAll()) {
                        this.cjrMap.clear();
                    }
                    final Iterable<ConfigureJointReportType> cjrIterable
                            = getNonNullIterable(cjrs.getConfigureJointReport());
                    for (ConfigureJointReportType cjr : cjrIterable) {
                        this.cjrMap.put(cjr.getJointNumber(), cjr);
                    }
                    setCommandState(CRCL_WORKING);;
//                    setReportJointStatus(true);
                } else if (cmd instanceof SetLengthUnitsType) {
//                    SetLengthUnitsType slu = (SetLengthUnitsType) cmd;
//                    LengthUnitEnumType lu = slu.getUnitName();
//                    setLengthUnit(lu);
//                    setCommandState(CommandStateEnumType.CRCL_DONE);
//                    settingsStatus.setLengthUnitName(lu);
                } else if (cmd instanceof SetTransSpeedType) {
                    SetTransSpeedType sts = (SetTransSpeedType) cmd;
                    TransSpeedType ts = sts.getTransSpeed();
                    if (ts instanceof TransSpeedAbsoluteType) {
                        TransSpeedAbsoluteType tsa = (TransSpeedAbsoluteType) ts;
                        this.setCommandedTransSpeed(tsa.getSetting());
                        crclServerSocket1.addToUpdateServerSideRunnables(() -> {
                            CRCLStatusType statToChange = this.status.get();
                            final SettingsStatusType settingsStatus = statToChange.getSettingsStatus();

                            settingsStatus.setTransSpeedAbsolute(tsa);

                        });

                    } else if (ts instanceof TransSpeedRelativeType) {
                        TransSpeedRelativeType tsr = (TransSpeedRelativeType) ts;
                        this.setCommandedTransSpeed(tsr.getFraction() * maxTransSpeed);
                        crclServerSocket1.addToUpdateServerSideRunnables(() -> {
                            CRCLStatusType statToChange = this.status.get();
                            final SettingsStatusType settingsStatus = statToChange.getSettingsStatus();

                            settingsStatus.setTransSpeedRelative(tsr);
                        });
                    } else {
                        final String message = "Unrecognized type of TransSpeed in SetTransSpeedType";
                        outer.showMessage(message);
                        setCommandState(CRCL_ERROR, message);
                        return;
                    }
                    setCommandState(CRCL_DONE);
                } else if (cmd instanceof SetTransAccelType) {
                    SetTransAccelType sts = (SetTransAccelType) cmd;
                    TransAccelType ts = sts.getTransAccel();
                    if (ts instanceof TransAccelAbsoluteType) {
                        TransAccelAbsoluteType taa = (TransAccelAbsoluteType) ts;
                        this.setCommandedTransAccel(taa.getSetting());
                        crclServerSocket1.addToUpdateServerSideRunnables(() -> {
                            CRCLStatusType statToChange = this.status.get();
                            final SettingsStatusType settingsStatus = statToChange.getSettingsStatus();
                            settingsStatus.setTransAccelAbsolute(taa);
                        });
                    } else if (ts instanceof TransAccelRelativeType) {
                        TransAccelRelativeType tar = (TransAccelRelativeType) ts;
                        this.setCommandedTransAccel(tar.getFraction() * maxTransAccel);
                        crclServerSocket1.addToUpdateServerSideRunnables(() -> {
                            CRCLStatusType statToChange = this.status.get();
                            final SettingsStatusType settingsStatus = statToChange.getSettingsStatus();
                            settingsStatus.setTransAccelRelative(tar);

                        });
                    } else {
                        final String message = "Unrecognized type of TransAccel in SetTransAccelType";
                        outer.showMessage(message);
                        setCommandState(CRCL_ERROR, message);
                        return;
                    }
                    setCommandState(CRCL_DONE);
                } else if (cmd instanceof SetRotSpeedType) {
                    SetRotSpeedType sts = (SetRotSpeedType) cmd;
                    RotSpeedType ts = sts.getRotSpeed();
                    if (ts instanceof RotSpeedAbsoluteType) {
                        RotSpeedAbsoluteType rsa = (RotSpeedAbsoluteType) ts;
                        this.setCommandedRotSpeed(rsa.getSetting());
                        crclServerSocket1.addToUpdateServerSideRunnables(() -> {
                            CRCLStatusType statToChange = this.status.get();
                            final SettingsStatusType settingsStatus = statToChange.getSettingsStatus();
                            settingsStatus.setRotSpeedAbsolute(rsa);
                        });
                    } else if (ts instanceof RotSpeedRelativeType) {
                        RotSpeedRelativeType rsr = (RotSpeedRelativeType) ts;
                        this.setCommandedRotSpeed(rsr.getFraction() * maxRotSpeed);
                        crclServerSocket1.addToUpdateServerSideRunnables(() -> {
                            CRCLStatusType statToChange = this.status.get();
                            final SettingsStatusType settingsStatus = statToChange.getSettingsStatus();
                            settingsStatus.setRotSpeedRelative(rsr);
                        });

                    } else {
                        final String message = "Unrecognized type of RotSpeed in SetRotSpeedType";
                        outer.showMessage(message);
                        setCommandState(CRCL_ERROR, message);
                        return;
                    }
                    setCommandState(CRCL_DONE);
                } else if (cmd instanceof SetRotAccelType) {
                    SetRotAccelType sts = (SetRotAccelType) cmd;
                    RotAccelType ts = sts.getRotAccel();
                    if (ts instanceof RotAccelAbsoluteType) {
                        RotAccelAbsoluteType raa = (RotAccelAbsoluteType) ts;
                        this.setCommandedRotAccel(raa.getSetting());
                        crclServerSocket1.addToUpdateServerSideRunnables(() -> {
                            CRCLStatusType statToChange = this.status.get();
                            final SettingsStatusType settingsStatus = statToChange.getSettingsStatus();
                            settingsStatus.setRotAccelAbsolute(raa);
                        });
                    } else if (ts instanceof RotAccelRelativeType) {
                        RotAccelRelativeType rar = (RotAccelRelativeType) ts;
                        this.setCommandedRotAccel(rar.getFraction() * maxRotAccel);
                        crclServerSocket1.addToUpdateServerSideRunnables(() -> {
                            CRCLStatusType statToChange = this.status.get();
                            final SettingsStatusType settingsStatus = statToChange.getSettingsStatus();
                            settingsStatus.setRotAccelRelative(rar);
                        });

                    } else {
                        final String message = "Unrecognized type of RotAccel in SetRotAccelType";
                        outer.showMessage(message);
                        setCommandState(CRCL_ERROR, message);
                        return;
                    }
                    setCommandState(CRCL_DONE);
                } else if (cmd instanceof EndCanonType) {
                    EndCanonType end = (EndCanonType) cmd;
                    setCommandState(CRCL_DONE);
//                    outer.updateIsInitialized(false);
                    this.setWaypoints(null);
                    this.setGoalPose(null);
                    if (null == jointPositions) {
                        throw new IllegalStateException("null == jointPositions");
                    }
                    commandedJointPositions1 = Arrays.copyOf(jointPositions, jointPositions.length);
                    this.commandedJointPositions = commandedJointPositions1;
                } else if (cmd instanceof MoveThroughToType) {
                    this.executingMoveCommand = true;
                    MoveThroughToType mv = (MoveThroughToType) cmd;
                    List<PoseType> wpts = getNonNullFilteredList(mv.getWaypoint());
                    int numpositions = mv.getNumPositions();
                    if (numpositions < 2) {
                        throw new RuntimeException("MoveThroughToType must set NumPositions to at-least 2 but NumPositions=" + numpositions + ".");
                    }
                    if (null != wpts) {
                        if (wpts.size() < 2) {
                            throw new RuntimeException("MoveThroughToType must have at-least two waypoints but " + wpts.size() + " were given.");
                        }
                        if (wpts.size() != numpositions) {
                            throw new RuntimeException("MoveThroughToType has NumPositions=" + numpositions + " but " + wpts.size() + " waypoints.");
                        }
                        this.setWaypoints(wpts);

                        for (PoseType pose : wpts) {
                            if (!checkPose(pose)) {
                                throw new RuntimeException("pose in waypoints is invalid :" + CRCLPosemath.poseToString(pose));
                            }
                        }
                        setCommandState(CRCL_WORKING);;
                        this.setCurrentWaypoint(0);
                        this.setGoalPose(wpts.get(0));

                        if (teleportToGoals) {
                            setCurrentWaypoint(wpts.size() - 1);
                            setGoalPose(wpts.get(wpts.size() - 1));
                        }
                    }
                    commandedJointPositions1 = initCommandedJointPositionsVelocitiesAccellerations(jointPositions);
                } else if (cmd instanceof ActuateJointsType) {
                    this.executingMoveCommand = true;
                    ActuateJointsType ajst = (ActuateJointsType) cmd;
                    this.goalPose = null;
                    List<ActuateJointType> ajl = getNonNullFilteredList(ajst.getActuateJoint());
                    if (null == jointPositions) {
                        throw new IllegalStateException("null == jointPositions");
                    }
                    if (null == commandedJointPositions1) {
                        commandedJointPositions1 = Arrays.copyOf(jointPositions, jointPositions.length);
                        this.commandedJointPositions = commandedJointPositions1;
                    }
                    for (ActuateJointType aj : ajl) {
                        int index = aj.getJointNumber() - 1;

                        if (index < 0 || index > this.jointPositions.length) {
                            final String message = "Bad joint index:" + index;
                            showMessage(message);
                            setCommandState(CRCL_ERROR, message);
                            break;
                        }
                        if (index >= 0 && index < this.jointPositions.length) {
                            commandedJointPositions1[index] = aj.getJointPosition();
                        }
                        JointDetailsType jd = aj.getJointDetails();
                        if (jd instanceof JointSpeedAccelType) {
                            JointSpeedAccelType jsa = (JointSpeedAccelType) jd;
                            Double vel = jsa.getJointSpeed();
                            if (null != vel) {
                                if (null == this.commandedJointVelocities) {
                                    this.commandedJointVelocities = new double[commandedJointPositions1.length];
                                    Arrays.setAll(this.commandedJointVelocities, i -> Double.POSITIVE_INFINITY);
                                }
                                this.commandedJointVelocities[index] = vel;
                            }
                            Double acc = jsa.getJointAccel();
                            if (null != acc) {
                                if (null == this.commandedJointAccellerations) {
                                    this.commandedJointAccellerations = new double[commandedJointPositions1.length];
                                    Arrays.setAll(this.commandedJointAccellerations, i -> Double.POSITIVE_INFINITY);
                                }
                                this.commandedJointAccellerations[index] = acc;
                            }
                        }
                    }
                    if (teleportToGoals) {
                        goalPose = null;
                        if (jointPositions == null) {
                            jointPositions = Arrays.copyOf(commandedJointPositions1, commandedJointPositions1.length);
                        } else {
                            synchronized (jointPositions) {
                                System.arraycopy(commandedJointPositions1, 0, jointPositions, 0, Math.min(commandedJointPositions1.length, jointPositions.length));
                            }
                        }
                        goalPose = null;
                        waypoints.clear();
                    }
                    if (debug_this_command || menuOuter().isDebugReadCommandSelected()) {
                        outer.showDebugMessage("SimServer commandedJointPositions = " + Arrays.toString(commandedJointPositions1));
                    }
                    setCommandState(CRCL_WORKING);;
                    outer.updatePanels(true);
                } else if (cmd instanceof MoveToType) {
                    this.executingMoveCommand = true;
                    MoveToType moveto = (MoveToType) cmd;
                    this.setGoalPose(moveto.getEndPosition());
                    setCommandState(CRCL_WORKING);;
                    this.setMoveStraight(moveto.isMoveStraight());
                    this.setCurrentWaypoint(0);
                    outer.updatePanels(true);
                    commandedJointPositions1 = initCommandedJointPositionsVelocitiesAccellerations(jointPositions);
                } else if (cmd instanceof SetAngleUnitsType) {
//                    SetAngleUnitsType setAngle = (SetAngleUnitsType) cmd;
//                    this.setAngleType(setAngle.getUnitName());
//                    setCommandState(CommandStateEnumType.CRCL_DONE);
//                    settingsStatus.setAngleUnitName(setAngle.getUnitName());
                } else if (cmd instanceof SetEndPoseToleranceType) {
                    SetEndPoseToleranceType endPoseTol = (SetEndPoseToleranceType) cmd;
                    this.setExpectedEndPoseTolerance(Objects.requireNonNull(endPoseTol.getTolerance(), "endPoseTol.getTolerance()"));
                    setCommandState(CRCL_DONE);
                    crclServerSocket1.addToUpdateServerSideRunnables(() -> {
                        CRCLStatusType statToChange = this.status.get();
                        final SettingsStatusType settingsStatus = statToChange.getSettingsStatus();
                        settingsStatus.setEndPoseTolerance(endPoseTol.getTolerance());
                    });
                } else if (cmd instanceof SetIntermediatePoseToleranceType) {
                    SetIntermediatePoseToleranceType intermediatePoseTol = (SetIntermediatePoseToleranceType) cmd;
                    this.setExpectedIntermediatePoseTolerance(Objects.requireNonNull(intermediatePoseTol.getTolerance(), "intermediatePoseTol.getTolerance()"));
                    setCommandState(CRCL_DONE);
                    crclServerSocket1.addToUpdateServerSideRunnables(() -> {
                        CRCLStatusType statToChange = this.status.get();
                        final SettingsStatusType settingsStatus = statToChange.getSettingsStatus();
                        settingsStatus.setIntermediatePoseTolerance(intermediatePoseTol.getTolerance());
                    });
                } else if (cmd instanceof DwellType) {
                    DwellType dwellCmd = (DwellType) cmd;
                    double dwellTime = dwellCmd.getDwellTime() * 1000.0;
                    if (dwellTime > maxDwell) {
                        LOGGER.warning("dwellTime of " + dwellTime + " exceeded max of " + maxDwell);
                        dwellTime = maxDwell;
                    }
                    dwellEndTime = System.currentTimeMillis() + ((long) dwellTime);
                    setCommandState(CRCL_WORKING);;
                } else if (cmd instanceof MoveScrewType) {
                    MoveScrewType moveScrew = (MoveScrewType) cmd;
                    final String message = "MoveScrewType not implemented.";
//                    setCommandState(CommandStateEnumType.CRCL_WORKING);
//                    this.multiStepCommand = moveScrew;
//                    this.moveScrewStep = 0;
                    setCommandState(CRCL_ERROR, message);
                    outer.showDebugMessage("\n" + message + "\n");

//                } else if (cmd instanceof ConfigureStatusReportType) {
//                    ConfigureStatusReportType csr = (ConfigureStatusReportType) cmd;
//                    setReportGripperStatus(csr.isReportGripperStatus());
//                    setReportJointStatus(csr.isReportJointStatuses());
//                    setReportPoseStatus(csr.isReportPoseStatus());
//                    setReportSettingsStatus(csr.isReportSettingsStatus());
//                    setCommandState(CRCL_DONE);
                } else {
                    final String cmdSimpleName = cmd.getClass().getSimpleName();
                    final String message = cmdSimpleName + " not implemented.";
                    setCommandState(CRCL_ERROR, message);
//                    setCommandState(CommandStateEnumType.CRCL_DONE);
                    outer.showDebugMessage("\n" + message + "\n");
                }
            }
            synchronized (stat) {
                CommandStatusType cst = stat.getCommandStatus();
                if (null != cst) {
                    cst.setCommandID(cmd.getCommandID());
                    String programFile = instance.getProgramFile();
                    if (null != programFile) {
                        cst.setProgramFile(programFile);
                    }
                    Integer programIndex = instance.getProgramIndex();
                    if (null != programIndex) {
                        cst.setProgramIndex(programIndex);
                    }
                    Integer programLength = instance.getProgramLength();
                    if (null != programLength) {
                        cst.setProgramLength(programLength);
                    }
                }
            }
            if (cmdQueue.size() > cmdQueueMaxSize) {
                cmdQueueMaxSize = cmdQueue.size();
            }
            cmdQueuePollStart = System.currentTimeMillis();
            instance = cmdQueue.poll();
            cmdQueuePollEnd = System.currentTimeMillis();
            cmdQueuePollReturnCount++;
            if (instance != null) {
                cmdQueuePollReturnNonNullCount++;
            }
            cmdQueuePollTime = cmdQueuePollEnd - cmdQueuePollStart;
            if (debug_this_command) {
                System.out.println("cmdQueuePollTime = " + cmdQueuePollTime);
            }
            if (cmdQueuePollTime > maxCmdQueuePollTime) {
                maxCmdQueuePollTime = cmdQueuePollTime;
            }
        }
        if (cmdQueuePutTime > 0) {
            long cmdQueueEmptyTime = System.currentTimeMillis();
            long diffCmdQueuePutEmpty = cmdQueueEmptyTime - cmdQueuePutTime;
            cmdQueuePutTime = 0;
            if (diffCmdQueuePutEmpty > maxDiffCmdQueuePutEmpty) {
                maxDiffCmdQueuePutEmpty = diffCmdQueuePutEmpty;
            }
        }
    }

    private void executeStopMotionCmd() {
        this.executingMoveCommand = true;
        this.setGoalPose(null);
        this.setWaypoints(null);
        if (null != this.jointPositions && null != commandedJointPositions) {
            System.arraycopy(this.jointPositions, 0, commandedJointPositions, 0,
                    Math.min(this.jointPositions.length, commandedJointPositions.length));
        }
        if (null != crclServerSocket 
                && crclServerSocket.getCommandStateEnum() == CommandStateEnumType.CRCL_WORKING) {
            setCommandState(CRCL_DONE);
        }
    }

    private double[] initCommandedJointPositionsVelocitiesAccellerations(double[] oldJointPositions) {
        double[] commandedJointPositions1;
        int length = oldJointPositions.length;
        commandedJointPositions1 = Arrays.copyOf(oldJointPositions, length);
        this.commandedJointPositions = commandedJointPositions1;
        this.commandedJointAccellerations = new double[length];
        Arrays.setAll(this.commandedJointAccellerations, i -> Double.POSITIVE_INFINITY);
        this.commandedJointVelocities = new double[length];
        Arrays.setAll(this.commandedJointVelocities, i -> Double.POSITIVE_INFINITY);
        return commandedJointPositions1;
    }

    public void initialize() {
        addToUpdateStatusRunnables(() -> this.applyInitialize());
    }

    private void applyInitialize() {
        setCommandState(CRCL_DONE);
        final CRCLStatusType stat = this.status.get();
        CommandStatusType cst = stat.getCommandStatus();
        if (null == cst) {
            cst = new CommandStatusType();
        }
        cst.setCommandState(CommandStateEnumType.CRCL_DONE);
        outer.updateIsInitialized(true);
        this.setWaypoints(null);
        this.setGoalPose(null);
        if (null == this.jointPositions) {
            throw new IllegalStateException("null == this.jointPositions");
        }
        this.commandedJointPositions = Arrays.copyOf(jointPositions, jointPositions.length);
    }

    /**
     * Get the value of waypoints
     *
     * @return the value of waypoints
     */
    public List<PoseType> getWaypoints() {
        return Collections.unmodifiableList(waypoints);
    }

    /**
     * Set the value of waypoints
     *
     * @param waypoints new value of waypoints
     */
    public void setWaypoints(@Nullable List<PoseType> waypoints) {
        this.waypoints.clear();
        if (null != waypoints) {
            waypoints.addAll(waypoints);
            outer.updateNumWaypoints(waypoints.size());
        } else {
            outer.updateNumWaypoints(0);
            this.setCurrentWaypoint(0);
        }
    }

    static final private Map<Integer, CRCLServerSocket> serverMap = new ConcurrentHashMap<>();
    static final private Map<Integer, StackTraceElement[]> traceMap = new ConcurrentHashMap<>();
    static final private Map<Integer, Thread> threadMap = new ConcurrentHashMap<>();

    public boolean isRunning() {
        if (!SimServerInner.runningServers.contains(this)) {
            return false;
        } else if (null == this.crclServerSocket) {
            return false;
        } else if (!this.crclServerSocket.isStartingSocketChannel() && (this.crclServerSocket.isClosed() || !this.crclServerSocket.isRunning())) {
            return false;
        } else if (this.simulationThread == null) {
            return false;
        } else if (!this.simulationThread.isAlive()) {
            return false;
        } else {
            return true;
        }
    }

    private final ConcurrentLinkedDeque<XFuture<CRCLStatusType>> statusFutures = new ConcurrentLinkedDeque<>();

    private XFuture<CRCLStatusType> getNextFuture() {
        XFuture<CRCLStatusType> ret = new XFuture<>("SimServerInner.getNextFuture");
        statusFutures.add(ret);
        return ret;
    }

    public synchronized void restartServer(boolean asDaemon) {
        if (null == cmdSchema) {
            throw new IllegalStateException("null==cmdSchema");
        }
        if (null == statSchema) {
            throw new IllegalStateException("null==statSchema");
        }
        try {
            double[] commandedJointPositions1 = this.commandedJointPositions;
            if (null == commandedJointPositions1) {
                throw new IllegalStateException("null == this.commandedJointPositions");
            }
            closeServer();
            CRCLServerSocket<SimServerClientState> restartingServerSocket = new CRCLServerSocket<>(port, simStateGenerator);
            serverMap.put(port, restartingServerSocket);
            traceMap.put(port, Thread.currentThread().getStackTrace());
            threadMap.put(port, Thread.currentThread());
            final int start_close_count = this.closeCount.get();
            if (port == 0) {
                // This is a hack so the integration test can be run on a port 
                // found automatically with the client run in the same 
                // process.
                // For this test only force port to zero then it will be bound
                // to a free port which gets passed back to the client with a system property.
                this.port = restartingServerSocket.getPort();
                System.setProperty("crcl4java.port", Integer.toString(port));
            }
            restartingServerSocket.setStatSchema(statSchema);
            restartingServerSocket.setCmdSchema(cmdSchema);
            if (null != cmdSchemaFiles) {
                restartingServerSocket.setCmdSchemaFiles(cmdSchemaFiles);
            }
            restartingServerSocket.setThreadNamePrefix("CRCLSimServer");
            restartingServerSocket.addListener(crclServerSocketEventListener);
            restartingServerSocket.setServerSideStatus(status);
            restartingServerSocket.setAutomaticallySendServerSideStatus(true);
            restartingServerSocket.setAutomaticallyConvertUnits(true);
            restartingServerSocket.setServerUnits(new UnitsTypeSet());
            restartingServerSocket.setUpdateStatusSupplier(this::getNextFuture);
            this.crclServerSocket = restartingServerSocket;
            maxReadCommandTime = 0;
            maxUpdateStatusTime = 0;
            maxSimCycleTime = 0;
            simCycleCount = 0;
            this.status.releaseLockThread();
            restartingServerSocket.start();

            Thread newSimulationThread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        while (!Thread.currentThread().isInterrupted()) {
                            if (closeCount.get() > start_close_count) {
                                return;
                            }
                            simCycleCount++;
                            long cycleStartTime = System.currentTimeMillis();
                            Thread.sleep(delayMillis);
                            long startCommandReadTime = System.currentTimeMillis();
                            readCommand();
                            long endCommandReadTime = System.currentTimeMillis();
                            long commandReadTime = endCommandReadTime - startCommandReadTime;
                            if (debug_this_command) {
                                System.out.println("commandReadTime = " + commandReadTime);
                            }
                            if (commandReadTime > maxReadCommandTime) {
                                maxReadCommandTime = commandReadTime;
                            }
                            final CRCLStatusType stat = SimServerInner.this.status.get();
                            if (!updateStatus()) {
                                sendStatus(null, stat);
                            }

                            long endCycleTime = System.currentTimeMillis();
                            long statusUpdateTime = endCycleTime - endCommandReadTime;
                            if (debug_this_command) {
                                System.out.println("statusUpdateTime = " + statusUpdateTime);
                            }
                            if (statusUpdateTime > maxUpdateStatusTime) {
                                maxUpdateStatusTime = statusUpdateTime;
                            }
                            if (debug_this_command) {
                                System.out.println("simCycleCount = " + simCycleCount);
                            }
                            long cycleTime = endCycleTime - cycleStartTime;
                            if (debug_this_command) {
                                System.out.println("cycleTime = " + cycleTime);
                            }
                            if (cycleTime > maxSimCycleTime) {
                                maxSimCycleTime = cycleTime;
                            }
                        }
                    } catch (InterruptedException ex) {
                        if (SimServerInner.this.closeCount.get() <= start_close_count) {
                            LOGGER.log(Level.SEVERE, null, ex);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(SimServerInner.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, "simThread.ssock=" + restartingServerSocket);
            newSimulationThread.setDaemon(asDaemon);
            this.serverIsDaemon = asDaemon;
            this.simulationThread = newSimulationThread;
            newSimulationThread.start();
            SimServerInner.runningServers.add(this);
            this.crclServerSocket = restartingServerSocket;
        } catch (Exception ex) {
            System.err.println("port=" + port);
            StackTraceElement trace[] = traceMap.get(port);
            System.err.println("trace=" + Arrays.toString(trace));
            System.err.println("serverMap=" + serverMap);
            System.err.println("traceMap=" + traceMap);
            System.err.println("threadMap=" + threadMap);
            showMessage("Can not start server on port " + port + " : " + ex.getMessage());
            LOGGER.log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    public CRCLStatusType getStatus() {
        if(null == crclServerSocket) {
            throw new NullPointerException("crclServerSocket");
        }
        return crclServerSocket.getLastUpdateServerSideStatusCopy();
    }

    public String getStatusXmlString() throws Exception {
        return this.getCheckerCRCLSocket().statusToPrettyString(this.getStatus(), false);
    }

    public CRCLSocket getCheckerCRCLSocket() {
        if (null != checkerCRCLSocket) {
            return checkerCRCLSocket;
        }
        return (checkerCRCLSocket = createCrclSocket(null));
    }

    private static class LastStatusInfo {

        long lastSentCid = -999;
        @Nullable
        CommandStateEnumType lastSentState = null;
    }

    private static class SimServerClientState extends CRCLServerClientState {

        SimServerClientState(CRCLSocket cs) {
            super(cs);
        }

        @Override
        public String toString() {
            return "SimServerClientState{" + "cs=" + getCs() + ", getStatusRequests=" + getStatusRequests + ", cmdsRecieved=" + cmdsRecieved + ", lastCmdTime=" + lastCmdTime + ", lastStatRequestTime=" + lastStatRequestTime + ", getStatusCmdId=" + getStatusCmdId + ", cmdId=" + cmdId + '}';
        }
    }
    private static final CRCLServerSocketStateGenerator<SimServerClientState> simStateGenerator
            = new CRCLServerSocketStateGenerator<SimServerInner.SimServerClientState>() {
        @Override
        public SimServerClientState generate(CRCLSocket crclSocket) {
            return new SimServerClientState(crclSocket);
        }
    };
}
