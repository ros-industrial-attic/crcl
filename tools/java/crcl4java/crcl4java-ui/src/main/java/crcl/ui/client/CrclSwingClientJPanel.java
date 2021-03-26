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
package crcl.ui.client;

import crcl.base.ActuateJointType;
import crcl.base.ActuateJointsType;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLProgramType;
import crcl.base.CRCLStatusType;
import crcl.base.CloseToolChangerType;
import crcl.base.CommandStateEnumType;
import static crcl.base.CommandStateEnumType.CRCL_DONE;
import static crcl.base.CommandStateEnumType.CRCL_ERROR;
import static crcl.base.CommandStateEnumType.CRCL_READY;
import static crcl.base.CommandStateEnumType.CRCL_WORKING;
import crcl.base.CommandStatusType;
import crcl.base.EndCanonType;
import crcl.base.GripperStatusType;
import crcl.base.GuardLimitEnumType;
import crcl.base.GuardType;
import crcl.base.InitCanonType;
import crcl.base.JointSpeedAccelType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.LengthUnitEnumType;
import static crcl.base.LengthUnitEnumType.INCH;
import static crcl.base.LengthUnitEnumType.METER;
import static crcl.base.LengthUnitEnumType.MILLIMETER;
import crcl.base.MiddleCommandType;
import crcl.base.MoveToType;
import crcl.base.OpenToolChangerType;
import crcl.base.PointType;
import crcl.base.PoseStatusType;
import crcl.base.PoseType;
import crcl.base.RotSpeedAbsoluteType;
import crcl.base.SetEndEffectorType;
import crcl.base.SetLengthUnitsType;
import crcl.base.SetRotSpeedType;
import crcl.base.SetTransSpeedType;
import crcl.base.StopConditionEnumType;
import crcl.base.StopMotionType;
import crcl.base.TransSpeedAbsoluteType;
import crcl.base.VectorType;
import crcl.ui.AutomaticPropertyFileUtils;
import crcl.ui.ConcurrentBlockProgramsException;
import crcl.ui.DefaultSchemaFiles;
import static crcl.ui.IconImages.BASE_IMAGE;
import static crcl.ui.IconImages.DISCONNECTED_IMAGE;
import static crcl.ui.IconImages.DONE_IMAGE;
import static crcl.ui.IconImages.ERROR_IMAGE;
import static crcl.ui.IconImages.WORKING_IMAGE;
import static crcl.ui.PoseDisplay.updateDisplayMode;
import static crcl.ui.PoseDisplay.updatePointTable;
import static crcl.ui.PoseDisplay.updatePoseTable;
import crcl.ui.PoseDisplayMode;
import static crcl.ui.PoseDisplayMode.XYZ_RPY;
import static crcl.ui.PoseDisplayMode.XYZ_RX_RY_RZ;
import static crcl.ui.PoseDisplayMode.XYZ_XAXIS_ZAXIS;
import crcl.ui.misc.ListChooserJPanel;
import crcl.ui.misc.MultiLineStringJPanel;
import crcl.ui.misc.ObjTableJPanel;
import static crcl.ui.misc.ObjTableJPanel.getAssignableClasses;
import crcl.ui.misc.ProgramPlotter;
import crcl.ui.misc.XpathQueryJFrame;
import crcl.utils.CRCLCopier;
import static crcl.utils.CRCLCopier.copy;
import crcl.utils.CRCLException;
import crcl.utils.CRCLPosemath;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.pose;
import static crcl.utils.CRCLPosemath.vector;
import crcl.utils.CRCLSchemaUtils;
import crcl.utils.CRCLSocket;
import crcl.utils.CRCLUtils;
import static crcl.utils.CRCLUtils.middleCommands;
import crcl.utils.XFuture;
import crcl.utils.XFutureVoid;
import crcl.utils.outer.interfaces.CommandStatusLogElement;
import crcl.utils.outer.interfaces.PendantClientMenuOuter;
import crcl.utils.outer.interfaces.PendantClientOuter;
import crcl.utils.outer.interfaces.ProgramRunData;
import diagapplet.plotter.PlotData;
import diagapplet.plotter.plotterJFrame;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import static java.awt.event.ActionEvent.ACTION_FIRST;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static java.util.Objects.requireNonNull;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.xml.sax.SAXException;
import rcs.posemath.PmCartesian;
import rcs.posemath.PmEulerZyx;
import rcs.posemath.PmException;
import rcs.posemath.PmRotationMatrix;
import rcs.posemath.PmRotationVector;
import rcs.posemath.PmRpy;
import rcs.posemath.Posemath;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class CrclSwingClientJPanel
        extends javax.swing.JPanel
        implements PendantClientOuter {

    private final CrclSwingClientInner internal;
    private static final double RPY_JOG_INCREMENT_DEFAULT = 3.0;
    private double rpyJogIncrement = RPY_JOG_INCREMENT_DEFAULT;
    private long pauseTime = -1;
    private long unpauseTime = -1;

    private boolean jogWorldTransSpeedsSet = false;
    private boolean jogWorldRotSpeedsSet = false;
    private static final Logger LOGGER = Logger.getLogger(CrclSwingClientJPanel.class.getName());
    private @Nullable
    XpathQueryJFrame xqJFrame = null;
    private diagapplet.plotter.@Nullable plotterJFrame xyzPlotter = null;
    private diagapplet.plotter.@Nullable plotterJFrame jointsPlotter = null;

    //    javax.swing.Timer pollTimer = null;
//    private volatile @Nullable
//    Thread pollingThread = null;
    private volatile boolean statusRequested = false;
    private long max_diff_readStatusEndTime_requestStatusStartTime = 0;
    private long maxPollStatusCycleTime = 0;
    private long cycles = 0;

    @SuppressWarnings("nullness")
    private @NonNull
    LengthUnitEnumType lengthUnit = LengthUnitEnumType.MILLIMETER;

    private @Nullable
    CRCLProgramType recordPointsProgram = null;
    private static final String SETTINGSREF = "clientsettingsref";
    private static final String CRCLJAVA_USER_DIR = ".crcljava";
    private static final String recent_files_dir = ".crcl_pendant_client_recent_files";

    private PendantClientMenuOuter menuOuter;

    private boolean debugShowProgram = false;

    /**
     * Get the value of debugShowProgram
     *
     * @return the value of debugShowProgram
     */
    public boolean isDebugShowProgram() {
        return debugShowProgram;
    }

    /**
     * Set the value of debugShowProgram
     *
     * @param debugShowProgram new value of debugShowProgram
     */
    public void setDebugShowProgram(boolean debugShowProgram) {
        this.debugShowProgram = debugShowProgram;
    }

    @Override
    public boolean checkUserText(String text) throws InterruptedException, ExecutionException {
        return MultiLineStringJPanel.showText(text).get();
    }

    public void pauseCrclProgram() {
        pauseTime = System.currentTimeMillis();
        internal.pause();
        this.jButtonProgramResume.setEnabled(internal.isPaused());
        this.jButtonProgramPause.setEnabled(internal.isRunningProgram());
        jogWorldTransSpeedsSet = false;
        jogWorldRotSpeedsSet = false;
    }

    public String pauseInfoString() {
        return internal.pauseInfoString();
    }

    public void unpauseCrclProgram() {
        String startPauseInfo = pauseInfoString();
        pauseTime = System.currentTimeMillis();
        internal.unpause();
        this.jButtonProgramResume.setEnabled(internal.isPaused());
        this.jButtonProgramPause.setEnabled(internal.isRunningProgram());
        jogWorldTransSpeedsSet = false;
        jogWorldRotSpeedsSet = false;
        if (isPaused()) {
            System.err.println("startPauseInfo = " + startPauseInfo);
            String currentPauseInfo = pauseInfoString();
            System.err.println("currentPauseInfo=" + currentPauseInfo);
            throw new IllegalStateException("still paused after unpauseCrclProgram()");
        }
    }

    public void showJointsPlot() {
        jointsPlotter = new plotterJFrame();
        jointsPlotter.setTitle("JOINTS");
        jointsPlotter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jointsPlotter.setVisible(true);
    }

    /**
     * Get the value of minLimit
     *
     * @return the value of minLimit
     */
    public PmCartesian getMinLimit() {
        return internal.getMinLimit();
    }

    /**
     * Set the value of minLimit
     *
     * @param minLimit new value of minLimit
     */
    public void setMinLimit(@Nullable PmCartesian minLimit) {
        internal.setMinLimit(minLimit);
    }

    /**
     * Get the value of maxLimit
     *
     * @return the value of maxLimit
     */
    public PmCartesian getMaxLimit() {
        return internal.getMaxLimit();
    }

    /**
     * Set the value of maxLimit
     *
     * @param maxLimit new value of maxLimit
     */
    public void setMaxLimit(@Nullable PmCartesian maxLimit) {
        internal.setMaxLimit(maxLimit);
    }

    public void showSetSchemaFilesDialog() {
        JFileChooser jFileChooser = new JFileChooser();
        javax.swing.filechooser.FileFilter[] ffa = jFileChooser.getChoosableFileFilters();
        for (javax.swing.filechooser.FileFilter ff : ffa) {
            jFileChooser.removeChoosableFileFilter(ff);
        }
        jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML Schema file", "xsd"));
        jFileChooser.setMultiSelectionEnabled(true);
        int result = jFileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File fa[] = jFileChooser.getSelectedFiles();
            internal.setCmdSchema(fa);
            DefaultSchemaFiles defaultSchemaFiles = DefaultSchemaFiles.instance();
            CRCLSchemaUtils.saveCmdSchemaFiles(defaultSchemaFiles.getCmdSchemasFile(), fa);
            internal.setStatSchema(fa);
            CRCLSchemaUtils.saveStatSchemaFiles(defaultSchemaFiles.getStatSchemasFile(), fa);
            internal.setProgramSchema(fa);
            CRCLSchemaUtils.saveProgramSchemaFiles(defaultSchemaFiles.getProgramSchemasFile(), fa);
        }
    }

    @Override
    public void showLastProgramLineExecTimeMillisDists(int row, ProgramRunData prd) {
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            showLastProgramLineExecTimeMillisDistsPrivate(row, prd);
        } else {
            javax.swing.SwingUtilities.invokeLater(() -> {
                showLastProgramLineExecTimeMillisDistsPrivate(row, prd);
            });
        }
    }

    private void showLastProgramLineExecTimeMillisDistsPrivate(int row, ProgramRunData prd) {
        long millis = prd.getTime();
        double dist = prd.getDist();
        boolean result = prd.isResult();
        if (row >= 0 && row < jTableProgram.getRowCount()) {
            jTableProgram.setValueAt(millis, row, 3);
            jTableProgram.setValueAt(dist, row, 4);
            jTableProgram.setValueAt(result, row, 5);
        }
    }

    /**
     * Get the value of menuOuter
     *
     * @return the value of menuOuter
     */
    public PendantClientMenuOuter getMenuOuter() {
        return menuOuter;
    }

    /**
     * Set the value of menuOuter
     *
     * @param menuOuter new value of menuOuter
     */
    public void setMenuOuter(PendantClientMenuOuter menuOuter) {
        this.menuOuter = menuOuter;
    }

    private volatile int errorsLength = 0;

    private void showErrorsPopup(MouseEvent evt) {
        JPopupMenu errorsPop = new JPopupMenu();
        JMenuItem clearMi = new JMenuItem("Clear");
        clearMi.addActionListener(e -> {
            errorsLength = 0;
            jTextAreaErrors.setText("");
            errorsPop.setVisible(false);
        });
        errorsPop.add(clearMi);
        errorsPop.show(evt.getComponent(), evt.getX(), evt.getY());
    }

    private static void scrollToVisible(JTable table, int rowIndex, int vColIndex) {
        Container container = table.getParent();
        if (container instanceof JViewport) {
            JViewport viewport = (JViewport) container;
            Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);
            Point pt = viewport.getViewPosition();
            rect.setLocation(rect.x - pt.x, rect.y - pt.y);
            viewport.scrollRectToVisible(rect);
        } else {
            throw new IllegalStateException("Tables parent " + container + " needs to be a JViewPort");
        }
    }

    public int getCurrentProgramLine() {
        if (internal.isRunningProgram()) {
            return internal.getCurrentProgramLine();
        }
        return jTableProgram.getSelectionModel().getMinSelectionIndex();
    }

    int programLineShowing = -1;

    private final ConcurrentLinkedDeque<CurrentPoseListener> currentPoseListeners = new ConcurrentLinkedDeque<>();

    public void addCurrentPoseListener(CurrentPoseListener l) {
        synchronized (currentPoseListeners) {
            if (!currentPoseListeners.contains(l)) {
                currentPoseListeners.add(l);
            }
        }
    }

    public void removeCurrentPoseListener(CurrentPoseListener l) {
        synchronized (currentPoseListeners) {
            if (!currentPoseListeners.contains(l)) {
                throw new IllegalStateException("Attempt to remove listener not currently registered. l=" + l + ",  currentPoseListeners=" + currentPoseListeners);
            }
            int sz0 = currentPoseListeners.size();
            currentPoseListeners.remove(l);
            int sz1 = currentPoseListeners.size();
            assert (sz0 == (sz1 + 1));
        }
    }

    private final List<ProgramLineListener> programLineListeners = new ArrayList<>();

    public void addProgramLineListener(ProgramLineListener l) {
        synchronized (programLineListeners) {
            if (!programLineListeners.contains(l)) {
                programLineListeners.add(l);
            }
        }
    }

    public void removeProgramLineListener(ProgramLineListener l) {
        programLineListeners.remove(l);
    }

    public @Nullable
    CRCLStatusType getStatus() {
        return lastStatusCopy;
    }

    private void finishShowCurrentProgramLine(final int line, final CRCLProgramType newProgram, @Nullable CRCLStatusType status, List<ProgramRunData> progRunDataList, StackTraceElement ste[]) {
        final List<MiddleCommandType> middleCommandsList
                = CRCLUtils.getNonNullFilteredList(newProgram.getMiddleCommand());

        if (programShowing == null) {
            showProgram(newProgram, progRunDataList, line);
        } else if (newProgram != programShowing) {
            final List<MiddleCommandType> programShowingMiddleCommandsList
                    = CRCLUtils.getNonNullFilteredList(programShowing.getMiddleCommand());
            final int newProgramSize = Objects.requireNonNull(middleCommandsList, "program.getMiddleCommand()").size();
            final InitCanonType showingInit = programShowing.getInitCanon();
            final InitCanonType newInit = newProgram.getInitCanon();
            final EndCanonType showingEnd = programShowing.getEndCanon();
            final EndCanonType newEnd = newProgram.getEndCanon();
            if (null != newEnd && null != newInit && newProgramSize > 0) {
                if (programShowingMiddleCommandsList == null
                        || programShowingMiddleCommandsList.size() != newProgramSize
                        || showingInit == null
                        || showingInit.getCommandID() != newInit.getCommandID()
                        || showingEnd == null
                        || showingEnd.getCommandID() != newEnd.getCommandID()) {
                    showProgram(newProgram, progRunDataList, line);
                }
            }
        }
        logShowCurrentProgramLineInfo(line, newProgram, status, progRunDataList, ste);
        if (line >= this.jTableProgram.getRowCount() || line < 0) {
            return;
        }
        if (line != programLineShowing) {
            if (null != newProgram) {
                if (getProgramRow() != line) {
                    jTableProgram.getSelectionModel().setSelectionInterval(line, line);
                }
                final int row = getProgramRow();
                if (row > 0 && row < jTableProgram.getRowCount() - 1) {
                    Object idObject = jTableProgram.getValueAt(row, 1);
                    if (idObject instanceof Long) {
                        long id = (Long) idObject;
                        if (middleCommandsList.size() <= line - 1
                                || id != middleCommandsList.get(line - 1).getCommandID()) {
                            showProgram(newProgram, progRunDataList, line);
                        }
                    }
                }
                scrollToVisible(jTableProgram, line, 0);
                jTableProgram.repaint();
                jPanelProgram.revalidate();
                jPanelProgram.repaint();
                long endMillis
                        = (internal.getRunEndMillis() > 0 && internal.getRunEndMillis() > internal.getRunStartMillis())
                        ? internal.getRunEndMillis() : System.currentTimeMillis();
                double runTime = (endMillis - this.internal.getRunStartMillis()) / 1000.0;
                this.jTextFieldRunTime.setText(String.format("%.1f", runTime));
                showSelectedProgramLine(line, newProgram, status);
            } else {
                showSelectedProgramCommand(null, status);
            }
        } else {
            updateDistToSelected(status);
        }
        if (null != status) {
            for (int i = 0; i < programLineListeners.size(); i++) {
                ProgramLineListener l = programLineListeners.get(i);
                l.accept(this, line, newProgram, status);
            }
        }
        programLineShowing = line;
    }

    private void updateDistToSelected(@Nullable CRCLStatusType status) {
        if (null != status && currentCommand instanceof MoveToType) {
            MoveToType currentMoveCmd = (MoveToType) currentCommand;
            final PoseStatusType statusPoseStatusLocal = status.getPoseStatus();
            if (null != statusPoseStatusLocal) {
                final PoseType poseLocal = statusPoseStatusLocal.getPose();
                final PoseType currentMoveCmdEndPosition = currentMoveCmd.getEndPosition();
                if (null != poseLocal && null != currentMoveCmdEndPosition) {
                    double dist = CRCLPosemath.diffPosesTran(currentMoveCmdEndPosition, poseLocal);
                    jTextFieldDistToSelected.setText(String.format("%.3f", dist));
                    jTextFieldDistToSelected.setEditable(true);
                    jTextFieldDistToSelected.setEnabled(true);
                } else {
                    jTextFieldDistToSelected.setText("NA");
                    jTextFieldDistToSelected.setEnabled(false);
                    jTextFieldDistToSelected.setEditable(false);
                }
            }
        } else {
            jTextFieldDistToSelected.setText("NA");
            jTextFieldDistToSelected.setEnabled(false);
            jTextFieldDistToSelected.setEditable(false);
        }
    }

    private volatile int lastShowSelectedProgramLineLine = -99;
    private volatile @Nullable
    CRCLProgramType lastshowSelectedProgramLineProgram = null;

    private void showSelectedProgramLine(final int line, final CRCLProgramType program, @Nullable CRCLStatusType status) {
        if (null != lastshowSelectedProgramLineProgram
                && lastShowSelectedProgramLineLine == line
                && lastshowSelectedProgramLineProgram == program) {
            return;
        }
        lastShowSelectedProgramLineLine = line;
        lastshowSelectedProgramLineProgram = program;
        final List<MiddleCommandType> middleCommandsList
                = CRCLUtils.getNonNullFilteredList(program.getMiddleCommand());
        if (line == 0) {
            try {
                InitCanonType cmd = program.getInitCanon();

                showSelectedProgramCommand(cmd, status);
            } catch (Exception ex) {
                Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (line > 0 && (null == middleCommandsList)) {
            try {
                EndCanonType cmd = program.getEndCanon();
                showSelectedProgramCommand(cmd, status);
            } catch (Exception ex) {
                Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (line > 0 && line <= middleCommandsList.size()) {
            try {
                MiddleCommandType cmd = middleCommandsList.get(line - 1);
                showSelectedProgramCommand(cmd, status);
            } catch (Exception ex) {
                Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (line == middleCommandsList.size() + 1) {
            try {
                EndCanonType cmd = program.getEndCanon();
                showSelectedProgramCommand(cmd, status);
            } catch (Exception ex) {
                Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        programPlotterJPanelOverhead.setProgram(program);
        programPlotterJPanelSide.setProgram(program);
        programPlotterJPanelOverhead.setIndex(line);
        programPlotterJPanelSide.setIndex(line);
        programPlotterJPanelOverhead.repaint();
        programPlotterJPanelSide.repaint();
    }

    private volatile @Nullable
    CRCLCommandType currentCommand = null;

    private void showSelectedProgramCommand(@Nullable CRCLCommandType cmd, @Nullable CRCLStatusType status) {
        try {
            currentCommand = cmd;
            updateDistToSelected(status);
            String cmdString
                    = (cmd != null)
                            ? this.internal.getTempCRCLSocket().commandToPrettyString(cmd)
                            : "No Program loaded.";
            int endlineindex = cmdString.indexOf('\n');
            if (endlineindex > 0 && endlineindex < cmdString.length()) {
                cmdString = cmdString.substring(endlineindex + 1);
            }
            int instancestartindex = cmdString.indexOf("<CRCLCommandInstance>");
            if (instancestartindex >= 0) {
                cmdString = cmdString.substring(instancestartindex + "<CRCLCommandInstance>".length());
                endlineindex = cmdString.indexOf('\n');
                if (endlineindex > 0 && endlineindex < cmdString.length()) {
                    cmdString = cmdString.substring(endlineindex + 1);
                }
            }
            int instanceendindex = cmdString.indexOf("</CRCLCommandInstance>");
            if (instanceendindex > 0) {
                cmdString = cmdString.substring(0, instanceendindex);
            }
            cmdString = cmdString.trim();
            this.jTextAreaSelectedProgramCommand.setText(cmdString);
            this.jTextAreaSelectedProgramCommand.setCaretPosition(0);
        } catch (Exception ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    private void logShowCurrentProgramLineInfo(final int line, CRCLProgramType program, @Nullable CRCLStatusType status, @Nullable List<ProgramRunData> progRunDataList, StackTraceElement trace[]) {
        if (!debugShowProgram) {
            return;
        }
        try {
            PrintStream ps = getShowProgramLogPrintStream();
            ps.println(" \ttrace=" + Arrays.toString(trace));
            int count = logShowProgramCount.incrementAndGet();

            String programString = CRCLSocket.getUtilSocket().programToPrettyString(program, false);
            File programFile = File.createTempFile("logShowCurrentProgramLineInfo_program_" + getPort() + "_" + count, ".xml");
            try (PrintStream psProgramFile = new PrintStream(new FileOutputStream(programFile))) {
                psProgramFile.println(programString);
            }

            File progRunDataListFile = File.createTempFile("showProgramLog_progRunDataList_" + getPort() + "_" + count, ".csv");
            if (null != progRunDataList) {
                saveProgramRunDataListToCsv(progRunDataListFile, progRunDataList);
            }
            long time = System.currentTimeMillis();
            ps.println("count=" + count + ",time=" + time + ",(time-showProgramLogStartTime)=" + (time - showProgramLogStartTime));
            String runDataListInfoString = "";
            if (null != progRunDataList) {
                runDataListInfoString = "\", programRunDataList=\"" + progRunDataListFile + "\" (size=" + progRunDataList.size() + "))";
            }
            if (null != status) {
                String statusString = CRCLSocket.getUtilSocket().statusToPrettyString(status, false);
                File statusFile = File.createTempFile("logShowCurrentProgramLineInfo_status_" + getPort() + "_" + count, ".xml");
                try (PrintStream psStatusFile = new PrintStream(new FileOutputStream(statusFile))) {
                    psStatusFile.println(statusString);
                }
                ps.println("logShowCurrentProgramLineInfo(line=" + line + ",program=\"" + programFile + ",status=\"" + statusFile + runDataListInfoString);
            } else {
                ps.println("logShowCurrentProgramLineInfo(line=" + line + ",program=\"" + programFile + runDataListInfoString);
            }
            ps.flush();
        } catch (Exception exception) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

    @Override
    @SuppressWarnings("nullness")
    public void showCurrentProgramLine(final int line,
            CRCLProgramType program,
            @Nullable CRCLStatusType status,
            List<ProgramRunData> progRunDataList) {
        StackTraceElement ste[] = Thread.currentThread().getStackTrace();
        final CRCLProgramType crclProgramCopy = copy(program);
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            finishShowCurrentProgramLine(line, crclProgramCopy, status, progRunDataList, ste);
        } else {
            final CRCLStatusType curInternalStatus = (null == status) ? null : copy(status);
            List<ProgramRunData> progRunDataListCopy = (null != progRunDataList) ? new ArrayList<>(progRunDataList) : Collections.emptyList();
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    finishShowCurrentProgramLine(line, crclProgramCopy, curInternalStatus, progRunDataListCopy, ste);
                }
            });
        }
    }

    /**
     * Creates new form PendantClientJPanel
     *
     */
    @SuppressWarnings("initialization")
    public CrclSwingClientJPanel(@Nullable Container outerContainer, @Nullable JFrame outerJFrame) {
        try {
            this.outerContainer = outerContainer;
            this.outerJFrame = outerJFrame;
            initComponents();
            this.internal = new CrclSwingClientInner(this, DefaultSchemaFiles.instance());
            init();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    /**
     * Creates new form PendantClientJPanel
     *
     */
    @SuppressWarnings("initialization")
    public CrclSwingClientJPanel() {
        this(null, null);
    }

    private void init() {
        String portPropertyString = System.getProperty("crcl4java.port");
        if (null != portPropertyString && portPropertyString.length() > 0) {
            int port = Integer.parseInt(portPropertyString);
            this.jTextFieldPort.setText("" + port);
        }
        String hostPropertyString = System.getProperty("crcl4java.host");
        if (null != hostPropertyString && hostPropertyString.length() > 0) {
            this.jTextFieldHost.setText(hostPropertyString);
        }
//        DefaultSchemaFiles defaultSchemaFiles = DefaultSchemaFiles.instance();
//        internal.setStatSchema(CRCLSocket.readStatSchemaFiles(defaultSchemaFiles.getStatSchemasFile()));
//        internal.setCmdSchema(CRCLSocket.readCmdSchemaFiles(defaultSchemaFiles.getCmdSchemasFile()));
//        internal.setProgramSchema(CRCLSocket.readProgramSchemaFiles(defaultSchemaFiles.getProgramSchemasFile()));
//        readRecentCommandFiles();
//        readRecentPrograms();
        final String programPropertyString = System.getProperty("crcl4java.program");
        if (null != programPropertyString) {
            final String nonNullProgramPropertyString = programPropertyString;
            java.awt.EventQueue.invokeLater(() -> {
                openXmlProgramFile(new File(nonNullProgramPropertyString));
            });
        }
        if (!(outerContainer instanceof CrclSwingClientJInternalFrame)) {
            checkSettingsRef();
        }
        this.updateUIFromInternal();
//        this.jTableProgram.getSelectionModel().addListSelectionListener(e -> finishShowCurrentProgramLine(getProgramRow(), internal.getProgram(), internal.getStatus(), null));
        this.internal.addPropertyChangeListener(new CrclSwingClientJPanel.MyPropertyChangeListener());
        this.jTableProgram.getSelectionModel().addListSelectionListener(programTableListSelectionListener);
        this.transformJPanel1.setPendantClient(this);
        this.jTextFieldStatus.setBackground(Color.GRAY);
        this.programPlotterJPanelOverhead.setPlotter(new ProgramPlotter(ProgramPlotter.View.OVERHEAD));
        this.programPlotterJPanelSide.setPlotter(new ProgramPlotter(ProgramPlotter.View.SIDE));
    }

    private ListSelectionListener programTableListSelectionListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
//            System.out.println("e = " + e);
            boolean adjusting = e.getValueIsAdjusting();
            int row = jTableProgram.getSelectedRow();
            if (!adjusting && null != programShowing && row >= 0) {
                showSelectedProgramLine(row, programShowing, internal.getStatus());
            }
            //finishShowCurrentProgramLine(getProgramRow(), internal.getProgram(), internal.getStatus(), internal.getProgRunDataList(),Thread.currentThread().getStackTrace());
        }
    };

    private void checkMenuOuter() throws IllegalStateException {
        if (null == menuOuter) {
            throw new IllegalStateException("Outer Menu Functions supplier not set.");
        }
    }

    @Override
    public String getHost() {
        return this.jTextFieldHost.getText();
    }

    @Override
    public int getPort() {
        int chckPort = Integer.parseInt(this.jTextFieldPort.getText());
        if (internal.isConnected()) {
            int port1 = internal.getPort();
            if (port1 != chckPort) {
                System.err.println("PendantClientJPanel.getPort() : " + port1 + " != " + chckPort);
                this.jTextFieldPort.setText("" + port1);
            }
            return port1;
        }
        return chckPort;
    }

    public void setPort(int port) {
        jTextFieldPort.setText(Integer.toString(port));
    }

    public void resetPrefs() {
        File crcljavaDir = new File(CRCLUtils.getCrclUserHomeDir(), CRCLJAVA_USER_DIR);
        if (crcljavaDir.exists()) {
            File settingsRef = new File(crcljavaDir, SETTINGSREF);
            boolean deleted = settingsRef.delete();
            System.out.println(settingsRef + ".delete() returned " + deleted);
        }
        rpyJogIncrement = RPY_JOG_INCREMENT_DEFAULT;
        internal.resetPrefs();
        updateUIFromInternal();
    }

    public ExecutorService getCrclSocketActionExecutorService() {
        return internal.getCrclSocketActionExecutorService();
    }

    private static void saveObjectProperties(File f, Object o) {
        try {
            File crcljavaDir = new File(CRCLUtils.getCrclUserHomeDir(), CRCLJAVA_USER_DIR);
            boolean made_dir = crcljavaDir.mkdirs();
            File settingsRef = new File(crcljavaDir, SETTINGSREF);
            try (PrintStream psRef = new PrintStream(new FileOutputStream(settingsRef))) {
                psRef.println(f.getCanonicalPath());
            }
            AutomaticPropertyFileUtils.saveObjectProperties(f, o);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    private void checkSettingsRef() {

        try {
            File crcljavaDir = new File(CRCLUtils.getCrclUserHomeDir(), CRCLJAVA_USER_DIR);
            boolean made_dir = crcljavaDir.mkdirs();
//            Logger.getLogger(CrclSwingClientJPanel.class.getName()).finest(() -> "mkdir " + crcljavaDir + " returned " + made_dir);
            File settingsRef = new File(crcljavaDir, SETTINGSREF);
            if (!settingsRef.exists() || !settingsRef.canRead()) {
                return;
            }
            String prefsFileName = new String(Files.readAllBytes(settingsRef.toPath())).trim();
            File prefsFile = new File(prefsFileName);
            if (prefsFile.exists() && prefsFile.canRead()) {
                loadPrefsFile(prefsFile);
            }
        } catch (IOException ex) {
            Logger.getLogger(CrclSwingClientJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private File propertiesFile;

    /**
     * Get the value of propertiesFile
     *
     * @return the value of propertiesFile
     */
    @Override
    public File getPropertiesFile() {
        return propertiesFile;
    }

    /**
     * Set the value of propertiesFile
     *
     * @param propertiesFile new value of propertiesFile
     */
    @Override
    public void setPropertiesFile(File propertiesFile) {
        this.propertiesFile = propertiesFile;
    }

    @Override
    public void loadProperties() {
        if (null != propertiesFile && propertiesFile.exists()) {
            loadPrefsFile(propertiesFile);
        }
    }

    @Override
    public void saveProperties() {
        saveObjectProperties(propertiesFile, this);
        AutomaticPropertyFileUtils.appendObjectProperties(propertiesFile, "internal.", internal);
    }

    private volatile @Nullable
    File loadedPrefsFile = null;
    private volatile StackTraceElement loadPrefsTrace @Nullable []  = null;
    private volatile @Nullable
    Thread loadPrefsThread = null;

    private void loadPrefsFile(File f) {
        try {
            loadPrefsTrace = Thread.currentThread().getStackTrace();
            loadedPrefsFile = f;
            loadPrefsThread = Thread.currentThread();
            File crcljavaDir = new File(CRCLUtils.getCrclUserHomeDir(), CRCLJAVA_USER_DIR);
            boolean made_dir = crcljavaDir.mkdirs();
            File settingsRef = new File(crcljavaDir, SETTINGSREF);
            try (PrintStream psRef = new PrintStream(new FileOutputStream(settingsRef))) {
                psRef.println(f.getCanonicalPath());
            }
            Map<String, Object> targetMap = new TreeMap<>();
            targetMap.put("internal.", internal);
            Object defaultTarget = this;
            AutomaticPropertyFileUtils.loadPropertyFile(f, targetMap, defaultTarget);
            updateUIFromInternal();
        } catch (IOException iOException) {
            showMessage(iOException);
        }
    }

    public static PoseType tableToPose(JTable table, PoseDisplayMode displayMode) throws PmException {

        TableModel tm = table.getModel();
        PmCartesian tran = new PmCartesian(
                (Double) tm.getValueAt(0, 1),
                (Double) tm.getValueAt(1, 1),
                (Double) tm.getValueAt(2, 1));

        switch (displayMode) {
            case XYZ_XAXIS_ZAXIS:
                PoseType p = new PoseType();
                PointType pt = CRCLPosemath.toPointType(tran);
                VectorType xv = new VectorType();
                xv.setI((Double) tm.getValueAt(3, 1));
                xv.setJ((Double) tm.getValueAt(4, 1));
                xv.setK((Double) tm.getValueAt(5, 1));
                VectorType zv = new VectorType();
                zv.setI((Double) tm.getValueAt(6, 1));
                zv.setJ((Double) tm.getValueAt(7, 1));
                zv.setK((Double) tm.getValueAt(8, 1));
                p.setPoint(pt);
                p.setXAxis(xv);
                p.setZAxis(zv);
                return p;

            case XYZ_RPY:
                PmRpy rpy = new PmRpy(
                        Math.toRadians((Double) tm.getValueAt(3, 1)),
                        Math.toRadians((Double) tm.getValueAt(4, 1)),
                        Math.toRadians((Double) tm.getValueAt(5, 1)));
                return CRCLPosemath.toPoseType(tran, rpy);

            case XYZ_RX_RY_RZ:
                PmEulerZyx zyx = new PmEulerZyx(
                        Math.toRadians((Double) tm.getValueAt(3, 1)),
                        Math.toRadians((Double) tm.getValueAt(4, 1)),
                        Math.toRadians((Double) tm.getValueAt(5, 1)));
                return CRCLPosemath.toPoseType(tran, Posemath.toMat(zyx));

            default:
                throw new IllegalArgumentException("displayMode =" + displayMode);
        }
    }

    public int getProgramRow() {
        final int selectedRows[] = this.jTableProgram.getSelectedRows();
        return (null == selectedRows || selectedRows.length < 1) ? 0 : selectedRows[0];
    }

    public String getVersion() {
        try (
                InputStream versionIs
                = requireNonNull(ClassLoader.getSystemResourceAsStream("version"),
                        "ClassLoader.getSystemResourceAsStream(\"version\")");
                BufferedReader br = new BufferedReader(new InputStreamReader(versionIs))) {
            StringBuilder sb = new StringBuilder();
            String line = null;
            while (null != (line = br.readLine())) {
                sb.append(line);
            }
            sb.append("\nSchema versions = ").append(CRCLSchemaUtils.getSchemaVersions().toString());
            return sb.toString();
        } catch (Exception ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            String exString = ex.toString();
            if (exString.length() > 30) {
                exString = exString.substring(0, 30);
            }
            return exString;
        }
    }

    private static String createAssertErrorString(CRCLCommandType cmd, long id) {
        return "command id being reduced id=" + id + ", cmd=" + CRCLSocket.cmdToString(cmd);
    }

    private void setCommandId(CRCLCommandType cmd, long id) {
        assert cmd.getCommandID() <= id :
                createAssertErrorString(cmd, id);
        cmd.setCommandID(id);
    }

    public void recordPoint(PoseType pose) {
        try {
            CRCLProgramType program = this.recordPointsProgram;
            if (program == null) {
                program = new CRCLProgramType();
                this.recordPointsProgram = program;
                InitCanonType initCmd = new InitCanonType();
                setCommandId(initCmd, 1);
                program.setInitCanon(initCmd);
                EndCanonType endCmd = new EndCanonType();
                setCommandId(endCmd, 3);
                program.setEndCanon(endCmd);
            }
            if (null == program) {
                throw new IllegalStateException("recordPointsProgram==null");
            }
            MoveToType moveToCmd = new MoveToType();
            PoseType endPose = new PoseType();
            PointType posePoint = pose.getPoint();
            if (null != posePoint) {
                endPose.setPoint(posePoint);
            }
            VectorType poseXAxis = pose.getXAxis();
            if (null != poseXAxis) {
                endPose.setXAxis(poseXAxis);
            }
            VectorType poseZAxis = pose.getZAxis();
            if (null != poseZAxis) {
                endPose.setZAxis(poseZAxis);
            }
            moveToCmd.setEndPosition(endPose);
            moveToCmd.setMoveStraight(this.jCheckBoxStraight.isSelected());
            final List<MiddleCommandType> middleCommandsList
                    = middleCommands(program);
            middleCommandsList.add(moveToCmd);
            setCommandId(moveToCmd, middleCommandsList.size() + 1);
            final EndCanonType endCommand
                    = Objects.requireNonNull(
                            program.getEndCanon(),
                            "program.getEndCanon()");
            if (moveToCmd.getCommandID() + 1 > endCommand.getCommandID()) {
                setCommandId(endCommand, moveToCmd.getCommandID() + 1);
            }
            internal.setProgram(program);
            showProgram(program, Collections.emptyList(), 0);
        } catch (Exception ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recordCurrentPoint() {
        Optional.ofNullable(internal)
                .map(CrclSwingClientInner::getStatus)
                .map(CRCLPosemath::getNullablePose)
                .ifPresent(this::recordPoint);
    }

    public void clearRecordedPoints() {
        CRCLProgramType program = this.recordPointsProgram;
        if (null != program) {
            try {
                final List<MiddleCommandType> middleCommandsList
                        = nullableMiddleCommands(program);
                if (null != middleCommandsList) {
                    middleCommandsList.clear();
                }
                this.internal.setProgram(program);
                internal.getProgRunDataList().clear();
                this.showProgram(program, Collections.emptyList(), -1);
            } catch (Exception ex) {
                Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void updateLengthUnit(@Nullable LengthUnitEnumType newUnit) {
        LengthUnitEnumType oldUnit
                = lengthUnit;
        if (newUnit != oldUnit && newUnit != null) {
            double oldScale = 1.0;
            double newScale = 1.0;
            switch (oldUnit) {
                case METER:
                    oldScale = 1.0;
                    break;

                case MILLIMETER:
                    oldScale = 0.001;
                    break;

                case INCH:
                    oldScale = 0.0254;
                    break;
            }
            switch (newUnit) {
                case METER:
                    newScale = 1.0;
                    break;

                case MILLIMETER:
                    newScale = 0.001;
                    break;

                case INCH:
                    newScale = 0.0254;
                    break;
            }
            lengthUnitComboBoxLengthUnit.setSelectedItem(newUnit);
            double oldInc = internal.getXyzJogIncrement();
            double newInc = oldInc * oldScale / newScale;
            internal.setXyzJogIncrement(newInc);
            this.jTextFieldXYZJogIncrement.setText(Double.toString(newInc));
            lengthUnit = newUnit;
        }
    }

    @Override
    public boolean isMonitoringHoldingObject() {
        return this.jCheckBoxMonitorHoldingOutput.isSelected();
    }

    @Override
    public void setExpectedHoldingObject(boolean x) {
        this.jLabelExpectHoldingObject.setText("Expect Holding Object:" + x);
        if (x) {
            jLabelExpectHoldingObject.setForeground(Color.BLACK);
            jLabelExpectHoldingObject.setBackground(Color.WHITE);
        } else {
            jLabelExpectHoldingObject.setForeground(Color.WHITE);
            jLabelExpectHoldingObject.setBackground(Color.BLACK);
        }
    }

    public boolean isHoldingObjectExpected() {
        return internal.isHoldingObjectExpected();
    }

    private void internalShowPaused(boolean paused) {
        jButtonProgramPause.setEnabled(!paused);
        jButtonProgramResume.setEnabled(paused);
        JInternalFrame internalFrame = null;
        if (outerContainer instanceof JInternalFrame) {
            internalFrame = (JInternalFrame) outerContainer;
        }
        if (null != internalFrame) {
            final String oldTitle = internalFrame.getTitle();
            if (paused) {
                if (!oldTitle.startsWith("paused ")) {
                    internalFrame.setTitle("paused " + oldTitle);
                }
            } else {
                if (oldTitle.startsWith("paused ")) {
                    internalFrame.setTitle(oldTitle.substring("paused ".length()));
                }
            }
        } else if (null != outerJFrame) {
            final String oldTitle = outerJFrame.getTitle();
            if (paused) {
                if (!oldTitle.startsWith("paused ")) {
                    outerJFrame.setTitle("paused " + oldTitle);
                }
            } else {
                if (oldTitle.startsWith("paused ")) {
                    outerJFrame.setTitle(oldTitle.substring("paused ".length()));
                }
            }
        }
    }

    @Override
    public void showPaused(boolean paused) {
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            internalShowPaused(paused);
        } else {
            javax.swing.SwingUtilities.invokeLater(() -> this.internalShowPaused(paused));
        }
    }

    @Override
    public void showLastGetStatusCommandString(String string) {
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            jTextAreaLastGetStatusCommand.setText(string);
        } else {
            javax.swing.SwingUtilities.invokeLater(() -> jTextAreaLastGetStatusCommand.setText(string));
        }
    }

    @Override
    public void showLastStopCommandString(String string) {
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            jTextAreaLastStopCommand.setText(string);
        } else {
            javax.swing.SwingUtilities.invokeLater(() -> jTextAreaLastStopCommand.setText(string));
        }
    }

    @Override
    public void showLastOtherCommandString(String string) {
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            jTextAreaLastOtherCommand.setText(string);
        } else {
            javax.swing.SwingUtilities.invokeLater(() -> jTextAreaLastOtherCommand.setText(string));
        }
    }

//    @Override
//    public void showLastCommandString(String string) {
//        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
//            jTextAreaLastCommand.setText(string);
//        } else {
//            javax.swing.SwingUtilities.invokeLater(() -> jTextAreaLastCommand.setText(string));
//        }
//    }
    private class MyPropertyChangeListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            switch (evt.getPropertyName()) {
                case CrclSwingClientInner.PROP_LENGTHUNIT:
                    updateLengthUnit(internal.getLengthUnit());
                    break;

                default:
                    // Ignore unrecognized properties.
                    break;
            }
        }
    }

    @Override
    public void clearProgramTimesDistances() {
        StackTraceElement ste[] = Thread.currentThread().getStackTrace();
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            clearProgramTimesDistancesInternal(ste);
        } else {
            javax.swing.SwingUtilities.invokeLater(
                    () -> clearProgramTimesDistancesInternal(ste));
        }
        programLineShowing = -1;
        jogWorldTransSpeedsSet = false;
        jogWorldRotSpeedsSet = false;
    }

    private void logClearProgramTimesDistancesInternalInfo(StackTraceElement trace[]) throws IOException, JAXBException {
        if (!debugShowProgram) {
            return;
        }
        PrintStream ps = getShowProgramLogPrintStream();
        int count = logShowProgramCount.incrementAndGet();
        long time = System.currentTimeMillis();
        ps.println(" \ttrace = " + Arrays.toString(trace));
        ps.println("count=" + count + ",time=" + time + ",(time-showProgramLogStartTime)=" + (time - showProgramLogStartTime));
        ps.println("clearProgramTimesDistancesInternal()");
        ps.flush();
    }

    private void clearProgramTimesDistancesInternal(StackTraceElement ste[]) {
        try {
            logClearProgramTimesDistancesInternalInfo(ste);
        } catch (IOException | JAXBException ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < jTableProgram.getRowCount(); i++) {
            jTableProgram.setValueAt(-1L, i, 3);
            jTableProgram.setValueAt(0.0, i, 4);
            jTableProgram.setValueAt(false, i, 5);
        }
    }

    @Override
    public PoseType currentStatusPose() {
        final CRCLStatusType status = requireNonNull(internal.getStatus(), "internal.getStatus()");
        PoseStatusType poseStatus = requireNonNull(status.getPoseStatus(), "status.getPoseStatus()");
        PoseType pose = requireNonNull(poseStatus.getPose(), "poseStatus.getPose()");
        return pose;
    }

    public Optional<CRCLStatusType> currentStatus() {
        return Optional.ofNullable(internal)
                .map(x -> x.getStatus());
    }

    public Optional<CommandStateEnumType> currentState() {
        return currentStatus()
                .map(x -> x.getCommandStatus())
                .map(x -> x.getCommandState());
    }

    @SuppressWarnings("nullness")
    @Override
    public MiddleCommandType currentProgramCommand() {
        CRCLProgramType program = internal.getProgram();
        if (null == program) {
            return null;
        }
        int curRow = getProgramRow();
        if (curRow > program.getMiddleCommand().size() || curRow < 1) {
            return null;
        }
        return program.getMiddleCommand().get(curRow - 1);
    }

    private final AtomicInteger pollStopCount = new AtomicInteger();

    public boolean checkPose(PoseType goalPose, boolean ignoreCartTran, boolean throwExceptions) {
        return internal.checkPose(goalPose, ignoreCartTran, throwExceptions);
    }

    private volatile boolean polling = false;
//    private volatile Thread pollingThread = null;

    private volatile @Nullable
    InterruptedException lastPollStatusInterruptedException = null;
    private volatile @Nullable
    Exception lastPollStatusException = null;
    private volatile int lastPollStatusStartPollStopCount = -1;
    private final AtomicInteger pollStatusCallCount = new AtomicInteger();

    private void pollStatus(int startPollStopCount) {
        XFutureVoid lastPollSocketRequestFuture = null;
        try {
            internal.releaseStatusThreadLock();
            int callCount = pollStatusCallCount.incrementAndGet();
            polling = true;
            lastPollStatusStartPollStopCount = startPollStopCount;
            final int poll_ms = internal.getPoll_ms();
            while (continuePolling(startPollStopCount)) {
                cycles++;
                long requestStatusStartTime = System.currentTimeMillis();
                if (null == lastPollSocketRequestFuture || lastPollSocketRequestFuture.isDone()) {
                    lastPollSocketRequestFuture = internal.pollSocketRequestAndReadStatus(() -> continuePolling(startPollStopCount), poll_ms);
                }
                if (!(continuePolling(startPollStopCount))) {
                    return;
                }
                long endCycleTime = System.currentTimeMillis();
                long pollStatusCycleTime = endCycleTime - requestStatusStartTime;
                if (pollStatusCycleTime > maxPollStatusCycleTime) {
                    maxPollStatusCycleTime = pollStatusCycleTime;
                }
            }
        } catch (InterruptedException interruptedException) {
            lastPollStatusInterruptedException = interruptedException;
            this.jCheckBoxPoll.setSelected(false);
        } catch (Exception ex) {
            lastPollStatusException = ex;
            if (!Thread.currentThread().isInterrupted()
                    && this.jCheckBoxPoll.isSelected()
                    && startPollStopCount == pollStopCount.get()) {
                Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.jCheckBoxPoll.setSelected(false);
            stopPollTimer();
        } finally {
            polling = false;
        }
    }

    public void setCrclSocketActionExecutorServiceAndThread(ExecutorService crclSocketActionExecutorService, @Nullable Thread crclSocketActionThread) {
        internal.setCrclSocketActionExecutorServiceAndThread(crclSocketActionExecutorService, crclSocketActionThread);
    }

    private boolean continuePolling(int startPollStopCount) {
        return !Thread.currentThread().isInterrupted()
                && this.jCheckBoxPoll.isSelected()
                && internal.isConnected()
                && startPollStopCount == pollStopCount.get();
    }

    private volatile @Nullable
    ExecutorService pollStatusService = null;
    private volatile @Nullable
    XFutureVoid lastStartPollTimerFuture = null;
    private volatile @Nullable
    Thread pollStatusServiceThread = null;

    private Future<?> startPollTimer() {
        this.stopPollTimer();

        jTextFieldPollTime.setEditable(false);
        jTextFieldPollTime.setEnabled(false);
        int startPollStopCount = pollStopCount.incrementAndGet();
        ExecutorService service = this.pollStatusService;
        if (null == service) {
            service = Executors.newSingleThreadExecutor(new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r, "CRCL Client pollStatus:" + internal.getCrclSocketString());
                    thread.setDaemon(true);
                    pollStatusServiceThread = thread;
                    System.out.println("pollStatusServiceThread.isAlive() = " + pollStatusServiceThread.isAlive());
                    return thread;
                }
            });
            this.pollStatusService = service;
        }
        System.out.println("pollStatusServiceThread = " + pollStatusServiceThread);
        if (null != pollStatusServiceThread) {
            System.out.println("pollStatusServiceThread.isAlive() = " + pollStatusServiceThread.isAlive());
        }
        XFutureVoid ret;
        internal.resetConfigureStatusReportForPollSocket();
        if (null != lastStartPollTimerFuture && !lastStartPollTimerFuture.isDone()) {
            ret = lastStartPollTimerFuture
                    .thenRunAsync(
                            "pollStatus" + startPollStopCount,
                            () -> pollStatus(startPollStopCount),
                            service);
        } else {
            ret = XFuture.runAsync(
                    "pollStatus" + startPollStopCount,
                    () -> pollStatus(startPollStopCount),
                    service);
        }
        System.out.println("pollStatusServiceThread = " + pollStatusServiceThread);
        if (null != pollStatusServiceThread) {
            System.out.println("pollStatusServiceThread.isAlive() = " + pollStatusServiceThread.isAlive());
        }
        lastStartPollTimerFuture = ret;
        if (internal.isConnected() && internal.isInitSent()) {
            enableJoggingControls();
        }
        return ret;
    }

    @SuppressWarnings({"nullness"})
    private void enableJoggingControls() {
        this.jComboBoxJointAxis.setToolTipText(null);
        this.jComboBoxXYZRPY.setToolTipText(null);
        jLabelJointJogMinus.setToolTipText(null);
        jLabelJogMinus.setToolTipText(null);
        jLabelJointJogPlus.setToolTipText(null);
        jLabelJogPlus.setToolTipText(null);

        this.jComboBoxJointAxis.setEnabled(true);
        this.jComboBoxXYZRPY.setEnabled(true);
        jLabelJointJogMinus.setEnabled(true);
        jLabelJointJogMinus.setBackground(Color.white);
        jLabelJogMinus.setEnabled(true);
        jLabelJogMinus.setBackground(Color.white);
        jLabelJointJogPlus.setEnabled(true);
        jLabelJointJogPlus.setBackground(Color.white);
        jLabelJogPlus.setEnabled(true);
        jLabelJogPlus.setBackground(Color.white);
        if (null != this.lastCommandsMenuParent) {
            for (int i = 0; i < lastCommandsMenuParent.getItemCount(); i++) {
                JMenuItem jmi = lastCommandsMenuParent.getItem(i);
                if (null != jmi) {
                    jmi.setEnabled(true);
                }
            }
        }
    }

    public void setDebugInterrupts(boolean debugInterrupts) {
        internal.setDebugInterrupts(debugInterrupts);
    }

    public boolean isDebugInterrupts() {
        return internal.isDebugInterrupts();
    }

    @Override
    public void stopPollTimer() {
        pollStopCount.incrementAndGet();
        showNotJogReady();
    }

    public void showNotJogReady() {
        this.jComboBoxJointAxis.setEnabled(false);
        this.jComboBoxXYZRPY.setEnabled(false);
        jLabelJointJogMinus.setEnabled(false);
        jLabelJointJogMinus.setBackground(Color.gray);
        jLabelJogMinus.setEnabled(false);
        jLabelJogMinus.setBackground(Color.gray);
        jLabelJointJogPlus.setEnabled(false);
        jLabelJointJogPlus.setBackground(Color.gray);
        jLabelJogPlus.setEnabled(false);
        jLabelJogPlus.setBackground(Color.gray);
        final JMenu lastCommandsMenuParent1 = this.lastCommandsMenuParent;
        if (null != lastCommandsMenuParent1) {
            for (int i = 0; i < lastCommandsMenuParent1.getItemCount(); i++) {
                JMenuItem jmi = lastCommandsMenuParent1.getItem(i);
                if (null != jmi) {
                    jmi.setEnabled(false);
                }
            }
        }
    }

    CrclSwingClientInner getInternal() {
        return internal;
    }

    private void updateUIFromInternal() {
        this.jTextFieldJointJogIncrement.setText(Double.toString(internal.getJointJogIncrement()));
        this.jTextFieldXYZJogIncrement.setText(Double.toString(internal.getXyzJogIncrement()));
        this.jTextFieldJointJogSpeed.setText(Double.toString(internal.getJogJointSpeed()));
        this.jTextFieldTransSpeed.setText(Double.toString(internal.getJogTransSpeed()));
        this.jTextFieldRPYJogIncrement.setText(Double.toString(this.rpyJogIncrement));
        this.jTextFieldJogInterval.setText(Double.toString(internal.getJogInterval()));
        this.jTextFieldPollTime.setText(Integer.toString(internal.getPoll_ms()));
        this.jTextFieldReadTimeout.setText(Integer.toString(internal.getReadStatusSoTimeout()));
        jTextFieldWaitForDoneDelay.setText(Long.toString(internal.getWaitForDoneDelay()));
        this.jCheckBoxReadTimeout.setSelected(internal.isUseReadSoTimeout());
        jCheckBoxIgnoreWaitForDoneTimeouts.setSelected(internal.isIgnoreTimeouts());
        final boolean selected = jCheckBoxReadTimeout.isSelected();
        updateTimeoutEnable(selected);
        this.stopPollTimer();
        if (this.jCheckBoxPoll.isSelected() && isConnected()) {
            this.startPollTimer();
        }
        if (isDisableTextPopups()) {
            crcl.ui.misc.MultiLineStringJPanel.disableShowText = true;
        }
    }

    public void openXmlProgramFile(File f) {
        try {
            this.clearProgramTimesDistances();
            this.clearRecordedPoints();
            internal.openXmlProgramFile(f, true);
        } catch (Exception ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            showMessage(ex);
        }
    }

    public void saveXmlProgramFile(File f) throws JAXBException, CRCLException {
        internal.saveXmlProgramFile(f);
    }

    private File lastOpenedProgramFile;

    /**
     * Get the value of lastOpenedProgramFile
     *
     * @return the value of lastOpenedProgramFile
     */
    @Override
    public File getLastOpenedProgramFile() {
        return lastOpenedProgramFile;
    }

    /**
     * Set the value of lastOpenedProgramFile
     *
     * @param lastOpenedProgramFile new value of lastOpenedProgramFile
     */
    public void setLastOpenedProgramFile(File lastOpenedProgramFile) {
        this.lastOpenedProgramFile = lastOpenedProgramFile;
    }

    /**
     * Get the value of sideProgramPlotter
     *
     * @return the value of sideProgramPlotter
     */
    public @Nullable
    ProgramPlotter getSideProgramPlotter() {
        return programPlotterJPanelSide.getPlotter();
    }

    /**
     * Get the value of overheadProgramPlotter
     *
     * @return the value of overheadProgramPlotter
     */
    public @Nullable
    ProgramPlotter getOverheadProgramPlotter() {
        return programPlotterJPanelOverhead.getPlotter();
    }

    public @Nullable
    CRCLProgramType getProgram() {
        return internal.getProgram();
    }

    private volatile @Nullable
    CRCLProgramType prevSetProgramProgram = null;
    private volatile int prevSetProgramLength = -1;
    private volatile @Nullable
    CRCLProgramType showProgramCopy = null;

    @Override
    public void setProgram(@Nullable CRCLProgramType program) {
        this.internal.setProgram(program);
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            prevSetProgramProgram = null;
            showProgramCopy = null;
            if (null != program) {
                this.showProgram(program, Collections.emptyList(), 0);
            }
            return;
        }

        if (null == program) {
            prevSetProgramProgram = null;
            showProgramCopy = null;
        } else if (showProgramCopy == null
                || prevSetProgramProgram != program
                || Objects.requireNonNull(program.getMiddleCommand(), "program.getMiddleCommand()").size() != prevSetProgramLength) {
            showProgramCopy = copy(program);
            prevSetProgramProgram = program;
            prevSetProgramLength = middleCommands(program).size();
        }
        if (null != showProgramCopy) {
            CRCLProgramType programToShow = showProgramCopy;
            try {
                javax.swing.SwingUtilities.invokeLater(() -> this.showProgram(programToShow, Collections.emptyList(), 0));
            } catch (Exception ex) {
                Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void finishOpenXmlProgramFile(File f,
            CRCLProgramType program, boolean saveRecent) {
        try {
            this.recordPointsProgram = null;
            showProgram(program, Collections.emptyList(), 0);
            internal.setProgram(program);
            this.saveRecentProgram(f);
            this.jTabbedPaneLeft.setSelectedComponent(this.jPanelProgram);
            programPlotterJPanelOverhead.setProgram(program);
            programPlotterJPanelSide.setProgram(program);
            programPlotterJPanelOverhead.repaint();
            programPlotterJPanelSide.repaint();
//            jTabbedPaneRightUpper.setSelectedComponent(jPanelProgramPlot);
        } catch (JAXBException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            showMessage(ex);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    private String getFirstLine(File f) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            return requireNonNull(br.readLine(), "br.readLine() : f=" + f);
        }
    }

    public Set<String> getRecentPrograms() {
        File fMainDir = new File(CRCLUtils.getCrclUserHomeDir(), recent_programs_dir);
        Set<String> pathSet = new TreeSet<>();
        if (!fMainDir.exists()) {
            return pathSet;
        }
        File fa[] = fMainDir.listFiles(new java.io.FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return !pathname.isDirectory() && pathname.canRead();
            }
        });
        if (null == fa || fa.length < 1) {
            return pathSet;
        }
        Arrays.sort(fa, new Comparator<File>() {

            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (File f : fa) {
            try {
                String path = getFirstLine(f).trim();
                File fprog = new File(path);
                if (!fprog.exists() || !fprog.canRead() || fprog.isDirectory()) {
                    boolean was_deleted = f.delete();
                    if (!was_deleted) {
                        Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.WARNING, "File " + f + " delete() returned" + was_deleted);
                    }
                    continue;
                }
                final String fprogCanon = fprog.getCanonicalPath();
                if (pathSet.contains(fprogCanon)) {
                    continue;
                }
                pathSet.add(fprogCanon);
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        return pathSet;
    }

    private boolean showing_message = false;
    private volatile long last_message_show_time = 0;

    private final @MonotonicNonNull
    Container outerContainer;

    private final @MonotonicNonNull
    JFrame outerJFrame;

    private boolean searchedForOuterFrame = false;

    private static final boolean LOG_IMAGES_DEFAULT = Boolean.getBoolean("crcl4java.simserver.logimages");

    private boolean toolChangerOpen;

    /**
     * Get the value of toolChangerOpen
     *
     * @return the value of toolChangerOpen
     */
    public boolean isToolChangerOpen() {
        return toolChangerOpen;
    }

    /**
     * Set the value of toolChangerOpen
     *
     * @param toolChangerOpen new value of toolChangerOpen
     */
    public void setToolChangerOpen(boolean toolChangerOpen) {
        this.toolChangerOpen = toolChangerOpen;
    }

    private @Nullable
    Container searchForOuterContainer() {
        if (searchedForOuterFrame) {
            return outerContainer;
        }
        searchedForOuterFrame = true;
        Container container = this;
        while (null != (container = container.getParent())) {
            if (container instanceof JFrame) {
                return container;
            }
            if (container instanceof JInternalFrame) {
                return container;
            }
        }
        return null;
    }

    /**
     * Get the value of outerFrame
     *
     * @return the value of outerFrame
     */
    public @Nullable
    Container getOuterContainer() {
        return outerContainer;
    }

    /**
     * Get the value of outerFrame
     *
     * @return the value of outerFrame
     */
    public @Nullable
    Window getOuterWindow() {
        if (outerContainer instanceof Window) {
            return (Window) outerContainer;
        }
        if (null != outerJFrame) {
            return outerJFrame;
        }
        if (null != outerContainer && outerContainer instanceof Window) {
            return (Window) outerContainer;
        }
        return searchForOuterWindow();
    }

    private @Nullable
    Window searchForOuterWindow() {
        if (outerContainer instanceof Window) {
            return (Window) outerContainer;
        }
        searchedForOuterFrame = true;
        Container container = this;
        while (null != (container = container.getParent())) {
            if (container instanceof Window) {
                return (Window) container;
            }
        }
        return null;
    }

    @Override
    public void showMessage(final String s) {
        System.out.println(s);
        showDebugMessage(s);
        MultiLineStringJPanel.showText(s);
    }

    @Override
    public boolean showDebugMessage(final String s) {
        final String sWithThread = "Thread:" + Thread.currentThread().getName() + " " + s;
        LOGGER.log(Level.FINE, sWithThread);
        if (!this.isVisible()) {
            return true;
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                int elen = CrclSwingClientJPanel.this.errorsLength;
                if (elen > 5000 && (elen + sWithThread.length()) > 20000) {
                    String s = jTextAreaErrors.getText();
                    if (s.length() > 5000) {
                        int startIndex = s.indexOf('\n', s.length() - 5000);
                        if (startIndex < 1) {
                            startIndex = s.length() - 5000;
                        }
                        s = s.substring(startIndex);
                        elen = s.length();
                        jTextAreaErrors.setText(s);
                    }
                }
                jTextAreaErrors.append("\n" + sWithThread);
                elen += 1 + sWithThread.length();
                CrclSwingClientJPanel.this.errorsLength = elen;
            }
        });
        return false;
    }

    @Override
    public void showMessage(Throwable t) {
        this.showMessage(t.toString());
    }

    private volatile long last_t_pos_logged = 0;

    final Map<Integer, Double> last_joints = new HashMap<>();

    private boolean jointsChanged(List<JointStatusType> jsl) {
        if (jsl.size() != last_joints.values().size()) {
            return true;
        }
        for (JointStatusType jst : jsl) {
            Double D = last_joints.get(jst.getJointNumber());
            if (null == D) {
                return true;
            }
            if (!D.equals(jst.getJointPosition())) {
                return true;
            }
        }
        return false;
    }

    private void copyJointPositions(List<JointStatusType> jsl) {
        this.last_joints.clear();
        for (JointStatusType jst : jsl) {
            final int jointNumber = jst.getJointNumber();
            final Double jointPosition = jst.getJointPosition();
            if (null != jointPosition) {
                this.last_joints.put(jointNumber, jointPosition);
            }
        }
    }

    public void setStatus(CRCLStatusType _status) {
        internal.setStatus(_status);
    }

    @Override
    public void checkXmlQuery(CRCLSocket crclSocket) {
        XpathQueryJFrame xqJFrameLocal = this.xqJFrame;
        if (null != xqJFrameLocal && (justShowedXpathQueryDialog || xqJFrameLocal.isUpdateAutomaticallySelected())) {
            justShowedXpathQueryDialog = false;
            String q = xqJFrameLocal.getQuery();
            if (q != null && q.length() > 0) {
                String lastStatusString = crclSocket.getLastStatusString();
                if (null != lastStatusString) {
                    xqJFrameLocal.runQuery(q, lastStatusString);
                }
            }
        }
    }

    public void saveStatusAs(File f) {
        internal.saveStatusAs(f);
    }

    private volatile StackTraceElement finishConnectTrace @Nullable []  = null;

    @Override
    public void finishConnect() {
        finishConnectTrace = Thread.currentThread().getStackTrace();
        this.jButtonConnect.setEnabled(false);
        this.jButtonDisconnect.setEnabled(true);
        enableConnectDependControls();

        if (this.jCheckBoxPoll.isSelected()) {
            this.startPollTimer();
        } else {
            this.jComboBoxJointAxis.setToolTipText("Not Polling?");
            this.jComboBoxXYZRPY.setToolTipText("Not Polling?");
            jLabelJointJogMinus.setToolTipText("Not Polling?");
            jLabelJogMinus.setToolTipText("Not Polling?");
            jLabelJointJogPlus.setToolTipText("Not Polling?");
            jLabelJogPlus.setToolTipText("Not Polling?");
        }
    }

    @SuppressWarnings({"nullness"})
    private void enableConnectDependControls() {
        this.jButtonEnd.setEnabled(true);
        this.jButtonInit.setEnabled(true);
        this.jButtonMoveTo.setEnabled(true);
        this.jButtonCloseGripper.setEnabled(true);
        this.jButtonOpenGripper.setEnabled(true);
        this.jButtonOpenToolChanger.setEnabled(true);
        this.jButtonCloseToolChanger.setEnabled(true);

        this.jButtonEnd.setToolTipText(null);
        this.jButtonInit.setToolTipText(null);
        this.jButtonMoveTo.setToolTipText(null);
        this.jButtonCloseGripper.setToolTipText(null);
        this.jButtonOpenGripper.setToolTipText(null);
        this.jButtonOpenToolChanger.setToolTipText(null);
        this.jButtonCloseToolChanger.setToolTipText(null);
    }

    private volatile @Nullable
    CRCLProgramType lastFinishSetStatusProgramCopy = null;
    private volatile @Nullable
    CRCLProgramType lastFinishSetStatusInternalProgram = null;
    private volatile int lastFinishSetStatusInternalProgramLength = -1;

    @SuppressWarnings("nullness")
    private @Nullable
    List<@NonNull MiddleCommandType> nullableMiddleCommands(@Nullable CRCLProgramType program) {
        if (null == program) {
            return null;
        }
        return program.getMiddleCommand();
    }

    private @Nullable
    CRCLProgramType getFinishSetStatusProgramCopy(CRCLProgramType internalProgram) {
        if (null == internalProgram) {
            lastFinishSetStatusProgramCopy = null;
            lastFinishSetStatusInternalProgram = null;
            lastFinishSetStatusInternalProgramLength = -1;
            return null;
        }
        if (lastFinishSetStatusInternalProgram == internalProgram && null != lastFinishSetStatusProgramCopy
                && lastFinishSetStatusInternalProgramLength == middleCommands(internalProgram).size()) {
            return lastFinishSetStatusProgramCopy;
        } else {
            lastFinishSetStatusProgramCopy = copy(internalProgram);
            lastFinishSetStatusInternalProgramLength = middleCommands(internalProgram).size();
            return lastFinishSetStatusProgramCopy;
        }
    }

    private volatile @Nullable
    CRCLStatusType lastStatusCopy = null;

    @Override
    public void finishSetStatus() {
        long statRecieveTime = System.currentTimeMillis();
        final CRCLStatusType curInternalStatus
                = requireNonNull(internal.getStatus(), "internal.getStatus()");
        this.lastStatusCopy = CRCLCopier.copy(curInternalStatus);
        final boolean isHoldingObjectExpected = internal.isHoldingObjectExpected();
        final CRCLCommandType lastCmd = internal.getLastCommandSent();
        CRCLProgramType internalProgram = internal.getProgram();
        StackTraceElement ste[] = Thread.currentThread().getStackTrace();
        updateCurrentPoseListeners(curInternalStatus, lastCmd, isHoldingObjectExpected, statRecieveTime);
        CommandStatusType ccst = CRCLUtils.getNonNullCommandStatus(curInternalStatus);
        String ccstStateDescription = ccst.getStateDescription();
        String stateDescription;
        if (null != ccstStateDescription) {
            stateDescription = ccstStateDescription;
        } else {
            stateDescription = "";
        }
        final CommandStateEnumType state
                = Objects.requireNonNull(
                        ccst.getCommandState(),
                        "ccst.getCommandState()");
        String stateString = state.toString();
        updateTitle(ccst, stateString, stateDescription);
        boolean isRunning = internal.isRunningProgram();
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            lastFinishSetStatusProgramCopy = null;
            lastFinishSetStatusInternalProgram = null;
            finishSetStatusPriv(internalProgram, curInternalStatus, lastCmd, isHoldingObjectExpected, ste, statRecieveTime, ccst, stateDescription, isRunning);
        } else {
            final CRCLStatusType curInternalStatusCopy
                    = requireNonNull(copy(curInternalStatus), "copy(curInternalStatus)");
            if (null == internalProgram) {
                lastFinishSetStatusProgramCopy = null;
                lastFinishSetStatusInternalProgram = null;
            }
            CRCLProgramType program = (null != internalProgram && isProgramCopyNeeded(curInternalStatusCopy))
                    ? getFinishSetStatusProgramCopy(internalProgram)
                    : null;
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    CrclSwingClientJPanel.this.finishSetStatusPriv(program, curInternalStatusCopy, lastCmd, isHoldingObjectExpected, ste, statRecieveTime, ccst, stateDescription, isRunning);
                }
            });
        }
    }

    private String lastStateDescription = "";
    private @Nullable
    String lastProgramFile = null;

    private @Nullable
    File findProgram(String filename) {
        File f0 = new File(filename);
        if (f0.exists()) {
            return f0;
        }
        for (String recent : getRecentPrograms()) {
            File f = new File(recent);
            if (f.exists() && f.getName().equals(filename)) {
                return f;
            }
        }
        for (String recent : getRecentPrograms()) {
            File f = new File(recent).getParentFile();
            if (null != f && f.exists() && f.getName().equals(filename)) {
                return f;
            }
        }
        f0 = new File(System.getProperty("user.dir"), filename);
        if (f0.exists()) {
            return f0;
        }
        return null;
    }

    public @Nullable
    List<ProgramRunData> getLastProgRunDataList() {
        return internal.getLastProgRunDataList();
    }

    public void saveProgramRunDataListToCsv(File f, List<ProgramRunData> list) throws IOException {
        internal.saveProgramRunDataListToCsv(f, list);
    }

    public void saveLastProgramRunDataListToCsv(File f) throws IOException {
        internal.saveLastProgramRunDataListToCsv(f);
    }

    private boolean isProgramCopyNeeded(final CRCLStatusType curInternalStatus) {
        try {
            if (null == internal.getProgram()) {
                return false;
            }
            if (null != curInternalStatus && null != curInternalStatus.getCommandStatus()) {
                CommandStatusType ccst = curInternalStatus.getCommandStatus();
                if (null != ccst) {
                    if (!internal.isRunningProgram()) {
                        Integer ccstProgramIndex = ccst.getProgramIndex();
                        if (null != ccstProgramIndex) {
                            int index = ccstProgramIndex.intValue();
                            if (index != internal.getLastProgramIndex()) {
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return false;
    }

    private volatile @Nullable
    CommandStateEnumType lastIconState = null;

    private void finishSetStatusPriv(
            @Nullable CRCLProgramType program,
            CRCLStatusType curInternalStatus,
            @Nullable CRCLCommandType lastCommandSent,
            boolean isHoldingObject,
            StackTraceElement[] ste,
            long statReceiveTime,
            CommandStatusType ccst,
            String stateDescription,
            boolean isRunning) {
        if (needInitPoint && null != curInternalStatus) {
            PointType pt = CRCLPosemath.getNullablePoint(curInternalStatus);
            if (null != pt) {
                pt = copy(pt);
                setPlottersInitPoint(pt);
                needInitPoint = false;
            }
        }
        if (null != ccst) {
            this.jTextFieldStatCmdID.setText("" + ccst.getCommandID());
            String ccstStateDescription = ccst.getStateDescription();

            if (null != ccstStateDescription) {
                stateDescription = ccstStateDescription;
            }
            Container container = this.getOuterContainer();
            final CommandStateEnumType state = ccst.getCommandState();
            if (null != state) {
                String stateString = state.toString();
                if (stateString.startsWith("CRCL_")) {
                    stateString = stateString.substring("CRCL_".length());
                }
                if (!stateString.equals(jTextFieldStatus.getText())) {
                    this.jTextFieldStatus.setText(stateString);

                    switch (state) {

                        case CRCL_ERROR:
                            this.jTextFieldStatus.setBackground(Color.RED);
                            if (null != outerJFrame) {
                                outerJFrame.setIconImage(ERROR_IMAGE);
                            }
                            break;

                        case CRCL_WORKING:
                            this.jTextFieldStatus.setBackground(Color.GREEN);
                            if (null != outerJFrame) {
                                outerJFrame.setIconImage(WORKING_IMAGE);
                            }
                            break;

                        case CRCL_READY:
                        case CRCL_DONE:
                        default:
                            this.jTextFieldStatus.setBackground(Color.WHITE);
                            int line = internal.getCurrentProgramLine();
                            if (null != outerJFrame) {
                                if (lastIconState == CRCL_ERROR
                                        || lastIconState == null
                                        || !isRunning
                                        || program == null
                                        || line < 1
                                        || line >= middleCommands(program).size()) {
                                    outerJFrame.setIconImage(DONE_IMAGE);
                                }
                            }
                            break;

                    }
                    lastIconState = state;
                }

                if (!isRunning) {
                    String ccstProgramFile = ccst.getProgramFile();
                    if (null != ccstProgramFile
                            && !ccstProgramFile.equals(internal.getOutgoingProgramFile())
                            && !ccstProgramFile.equals(lastProgramFile)) {
                        File f = findProgram(ccstProgramFile);
                        if (null != f) {
                            openXmlProgramFile(f);
                        }
                        lastProgramFile = ccstProgramFile;
                    }
                    Integer ccstProgramIndex = ccst.getProgramIndex();
                    if (null != ccstProgramIndex) {
                        int index = ccstProgramIndex.intValue();
                        if (index != internal.getLastProgramIndex() && null != program) {
                            finishShowCurrentProgramLine(index, program, curInternalStatus, internal.getProgRunDataList(), ste);
                            internal.setLastProgramIndex(index);
                        }
                    }
                } else {
                    updateDistToSelected(curInternalStatus);
                }
            }
            this.jTextFieldStatusID.setText("" + ccst.getStatusID());
            if (null != stateDescription && !stateDescription.equals(lastStateDescription)) {
                this.jTextAreaStateDescription.setText(stateDescription);
                lastStateDescription = stateDescription;
            }
            GripperStatusType gripperStatus = curInternalStatus.getGripperStatus();
            if (null == gripperStatus || null == gripperStatus.isHoldingObject()) {

                jLabelHoldingObject.setText("HoldingObject: UNKNOWN");
                jLabelHoldingObject.setOpaque(false);
                jLabelHoldingObject.setForeground(Color.BLACK);
                jLabelHoldingObject2.setText("HoldingObject: UNKNOWN");
                jLabelHoldingObject2.setOpaque(false);
                jLabelHoldingObject2.setForeground(Color.BLACK);
            } else {
                final Boolean holdingObject = gripperStatus.isHoldingObject();
                if (null != holdingObject && holdingObject.booleanValue()) {
                    jLabelHoldingObject.setText("HoldingObject: TRUE");
                    jLabelHoldingObject.setForeground(Color.BLACK);
                    jLabelHoldingObject.setBackground(Color.WHITE);
                    jLabelHoldingObject2.setText("HoldingObject: TRUE");
                    jLabelHoldingObject2.setForeground(Color.BLACK);
                    jLabelHoldingObject2.setBackground(Color.WHITE);
                } else {
                    jLabelHoldingObject.setText("HoldingObject: FALSE");
                    jLabelHoldingObject.setForeground(Color.WHITE);
                    jLabelHoldingObject.setBackground(Color.BLACK);
                    jLabelHoldingObject2.setText("HoldingObject: FALSE");
                    jLabelHoldingObject2.setForeground(Color.WHITE);
                    jLabelHoldingObject2.setBackground(Color.BLACK);
                }
                jLabelHoldingObject.setOpaque(true);
                jLabelHoldingObject2.setOpaque(true);
            }
        }
        JointStatusesType jsst = curInternalStatus.getJointStatuses();
        if (jsst != null) {
            List<JointStatusType> jsl = CRCLUtils.getNonNullFilteredList(jsst.getJointStatus());
            boolean joints_changed = this.jointsChanged(jsl);
            if (joints_changed) {
                this.copyJointPositions(jsl);
                DefaultTableModel tm = (DefaultTableModel) this.jTableJoints.getModel();
                double t = System.currentTimeMillis();
                tm.setRowCount(jsl.size());
                boolean hasForce = false;
                boolean hasVel = false;
                for (JointStatusType js : jsl) {
                    int jn = js.getJointNumber();
                    final Double jointVelocity = js.getJointVelocity();
                    if (null != jointVelocity) {
                        if (tm.getColumnCount() < 3) {
                            tm.setColumnCount(3);
                        }
                        tm.setValueAt(jointVelocity, jn - 1, 2);
                        hasVel = true;
                    }
                    final Double jointTorqueOrForce = js.getJointTorqueOrForce();
                    if (null != jointTorqueOrForce) {
                        if (tm.getColumnCount() < 4) {
                            tm.setColumnCount(4);
                        }
                        tm.setValueAt(jointTorqueOrForce, jn - 1, 3);
                        hasForce = true;
                    }
                    final Double jointPosition = js.getJointPosition();
                    if (null == jointPosition) {
//                            tm.setValueAt(Double.NaN, jn-1,1);
                        continue;
                    }

                    double pos = jointPosition;
                    tm.setValueAt(jn, jn - 1, 0);
                    tm.setValueAt(pos, jn - 1, 1);
                    if (this.getMenuOuter().isPlotJointsSelected()) {
                        plotterJFrame plotter = this.jointsPlotter;
                        if (null == plotter || !plotter.isVisible()) {
                            plotter = new plotterJFrame();
                            plotter.setTitle("JOINTS");
                            plotter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            plotter.setVisible(true);
                            this.jointsPlotter = plotter;
                        }
                        if (null == plotter) {
                            throw new IllegalStateException("null == plotter");
                        }
                        String pname = "joint[" + jn + "]";
                        PlotData pd = plotter.getPlotByName(pname);
                        if (null == pd) {
                            pd = new PlotData();
                            pd.name = pname;
                            plotter.AddPlot(pd, pname);
                        }
                        plotter.AddPointToPlot(pd, t, pos, true);
                        if (pd.get_num_points() < 100) {
                            plotter.FitToGraph();
                        }
                    }
                }
                if (!hasForce && !hasVel) {
                    tm.setColumnIdentifiers(new String[]{"Joint", "Position"});
                    tm.setColumnCount(2);
                } else if (!hasForce) {
                    tm.setColumnIdentifiers(new String[]{"Joint", "Position", "Velocity"});
                    tm.setColumnCount(3);
                } else {
                    tm.setColumnIdentifiers(new String[]{"Joint", "Position", "Velocity", "TorqueOrForce"});
                    tm.setColumnCount(4);
                }
                if (null != this.jointsPlotter) {
                    this.jointsPlotter.ScrollRight();
                }
            }
        }
        PoseType p
                = Optional.ofNullable(curInternalStatus)
                        .map(CRCLPosemath::getNullablePose)
                        .orElse(null);
        if (null != p) {
            updatePoseTable(p, this.jTablePose, getCurrentPoseDisplayMode());
            PointType pt = p.getPoint();
            if (null != pt) {
                ProgramPlotter overheadPlotter = programPlotterJPanelOverhead.getPlotter();
                if (null != overheadPlotter) {
                    overheadPlotter.setCurrentPoint(pt);
                }
                ProgramPlotter sidePlotter = programPlotterJPanelSide.getPlotter();
                if (null != sidePlotter) {
                    sidePlotter.setCurrentPoint(pt);
                }
//                    programPlotterJPanelOverhead.getPlotter().setCurrentPoint(pt);
//                    programPlotterJPanelSide.getPlotter().setCurrentPoint(pt);
            }
            checkMenuOuter();
            if (this.menuOuter.isPlotXyzSelected() && null != pt) {

                long t = System.currentTimeMillis();
//                    XMLGregorianCalendar xgc = p.getTimestamp();
//                    if (null != xgc) {
//                        double old_t = t;
//                        t = (double) xgc.toGregorianCalendar().getTime().getTime();
//                    }
                if (t > (this.last_t_pos_logged + 5)) {
                    plotterJFrame plotter = showXyzPlot();
                    if (null == plotter) {
                        throw new IllegalStateException("null == plotter");
                    }
                    double trel = (double) (t - xyzt0);
                    if (trel > 0.1 && trel < 3_600_000.0) {
                        PlotData xpd = plotter.getPlotByName("x");
                        if (null == xpd) {
                            xpd = new PlotData();
                            xpd.name = "x";
                            plotter.AddPlot(xpd, "x");
                        }
                        double x = pt.getX();
                        plotter.AddPointToPlot(xpd, trel, x, true);
                        PlotData ypd = plotter.getPlotByName("y");
                        if (null == ypd) {
                            ypd = new PlotData();
                            ypd.name = "y";
                            plotter.AddPlot(xpd, "y");
                        }
                        double y = pt.getY();
                        plotter.AddPointToPlot(ypd, trel, y, true);
                        PlotData zpd = plotter.getPlotByName("z");
                        if (null == zpd) {
                            zpd = new PlotData();
                            zpd.name = "z";
                            plotter.AddPlot(zpd, "z");
                        }
                        double z = pt.getZ();
                        plotter.AddPointToPlot(zpd, trel, z, true);
                        if (curInternalStatus.getSensorStatuses() != null
                                && !curInternalStatus.getSensorStatuses().getForceTorqueSensorStatus().isEmpty()) {
                            PlotData forceXPd = plotter.getPlotByName("forcex");
                            if (null == forceXPd) {
                                forceXPd = new PlotData();
                                forceXPd.name = "forcex";
                                plotter.AddPlot(forceXPd, "forcex");
                            }
                            double forceX = curInternalStatus.getSensorStatuses().getForceTorqueSensorStatus().get(0).getFx();
                            plotter.AddPointToPlot(forceXPd, trel, forceX, true);
                            PlotData forceYPd = plotter.getPlotByName("forcey");
                            if (null == forceYPd) {
                                forceYPd = new PlotData();
                                forceYPd.name = "forcey";
                                plotter.AddPlot(forceYPd, "forcey");
                            }
                            double forceY = curInternalStatus.getSensorStatuses().getForceTorqueSensorStatus().get(0).getFy();
                            plotter.AddPointToPlot(forceYPd, trel, forceY, true);
                            PlotData forceZPd = plotter.getPlotByName("forcez");
                            if (null == forceZPd) {
                                forceZPd = new PlotData();
                                forceZPd.name = "forcez";
                                plotter.AddPlot(forceZPd, "forcez");
                            }
                            double forceZ = curInternalStatus.getSensorStatuses().getForceTorqueSensorStatus().get(0).getFz();
                            plotter.AddPointToPlot(forceZPd, trel, forceZ, true);
                        }
                        if (xpd.get_num_points() < 100) {
                            plotter.FitToGraph();
                        }
                        plotter.ScrollRight();
                        plotter.repaint();
                    }
                    this.last_t_pos_logged = t;
                }
            }
        }
    }

    private volatile long xyzt0 = 0;

    public plotterJFrame showXyzPlot() {
        plotterJFrame plotter = this.xyzPlotter;
        if (null == plotter || !plotter.isVisible()) {
            plotter = new plotterJFrame();
            plotter.setTitle("XYZ");
            plotter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            plotter.setVisible(true);
            this.xyzPlotter = plotter;
            xyzt0 = System.currentTimeMillis();
        }
        return plotter;
    }

    private volatile @Nullable
    CurrentPoseListenerUpdateInfo currentPoseListenerUpdateInfo = null;

    public @Nullable
    CurrentPoseListenerUpdateInfo getCurrentPoseListenerUpdateInfo() {
        return currentPoseListenerUpdateInfo;
    }

    private void updateCurrentPoseListeners(CRCLStatusType curInternalStatus, @Nullable CRCLCommandType lastCommandSent, boolean isHoldingObject, long statReceiveTime) {
        final CurrentPoseListenerUpdateInfo info = new CurrentPoseListenerUpdateInfo(this, curInternalStatus, lastCommandSent, isHoldingObject, statReceiveTime);
        this.currentPoseListenerUpdateInfo = info;
        for (CurrentPoseListener l : currentPoseListeners) {
            l.handlePoseUpdate(info);
        }
    }

    private void setPlottersInitPoint(@Nullable PointType pt) {
        ProgramPlotter overheadPlotter = programPlotterJPanelOverhead.getPlotter();
        if (null != overheadPlotter) {
            overheadPlotter.setInitPoint(pt);
        }
        ProgramPlotter sidePlotter = programPlotterJPanelSide.getPlotter();
        if (null != sidePlotter) {
            sidePlotter.setInitPoint(pt);
        }
    }

    final List<UpdateTitleListener> updateTitleListeners = new ArrayList<>();

    public void addUpdateTitleListener(UpdateTitleListener utl) {
        updateTitleListeners.add(utl);
    }

    public void removeUpdateTitleListener(UpdateTitleListener utl) {
        updateTitleListeners.remove(utl);
    }

    public String getLastMessage() {
        return internal.getLastMessage();
    }

    public void updateTitle(CommandStatusType ccst, String stateString, String stateDescription) {

        String ccstProgramFile = ccst.getProgramFile();
        Integer ccstProgramIndex = ccst.getProgramIndex();
        String program = (null != ccstProgramFile && null != ccstProgramIndex)
                ? " " + ccstProgramFile + ":" + ccstProgramIndex.toString() : "";
        final Integer ccstProgramLength = ccst.getProgramLength();

        if (!program.isEmpty() && null != ccstProgramLength) {
            program += "/" + ccstProgramLength.toString();
        }
        if (program.length() > 1) {
            program = " " + program.trim() + " ";
        }
        JInternalFrame internalFrame = null;
        if (outerContainer instanceof JInternalFrame) {
            internalFrame = (JInternalFrame) outerContainer;
        }
        String stateDescriptionString = stateDescription;
        if (stateDescriptionString == null) {
            stateDescriptionString = "";
        } else if (stateDescriptionString.length() > 10) {
            stateDescriptionString = stateDescriptionString.substring(0, 9);
        }
        if (stateDescriptionString.length() > 1) {
            stateDescriptionString = " " + stateDescriptionString.trim() + " ";
        }

        String hostPort = "";
        InetAddress addr = internal.getInetAddress();
        if (null != addr) {
            hostPort = " " + addr.getHostAddress() + ":" + internal.getPort() + " ";
        }
        String newTitle
                = (internal.isPaused() ? "paused " : "")
                + "CRCL Client: "
                + hostPort
                + stateString
                + stateDescriptionString
                + program;
        if (newTitle.length() > 72) {
            newTitle = newTitle.substring(0, 72);
        }
        final String finalNewTitle = newTitle;
        JInternalFrame finalInternalFrame = internalFrame;
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            if (null != finalInternalFrame) {
                finalInternalFrame.setTitle(finalNewTitle);
            } else if (null != outerJFrame) {
                outerJFrame.setTitle(finalNewTitle);
            }
        } else {
            if (null != finalInternalFrame) {
                javax.swing.SwingUtilities.invokeLater(() -> finalInternalFrame.setTitle(finalNewTitle));
            } else if (null != outerJFrame) {
                final JFrame finalOuterJFrame = outerJFrame;
                javax.swing.SwingUtilities.invokeLater(() -> finalOuterJFrame.setTitle(finalNewTitle));
            }
        }
        if (null != updateTitleListeners) {
            for (UpdateTitleListener utl : updateTitleListeners) {
                utl.titleChanged(ccst, outerContainer, stateString, stateDescription);
            }
        }
    }

    private volatile boolean disconnecting = false;

    private volatile StackTraceElement @Nullable [] disconnectTrace = null;
    private volatile @Nullable
    Thread disconnectThread = null;

    public synchronized void disconnect() {
        showNotJogReady();
        closeShowProgramLogPrintStream();
        disconnecting = true;
        disconnectThread = Thread.currentThread();
        disconnectTrace = Thread.currentThread().getStackTrace();
        if (isRunningProgram()) {
            internal.showErrorMessage("diconnect while isRunningProgram");
            throw new IllegalStateException("disconnect while isRunningProgram");
        }
        this.jTextFieldStatus.setBackground(Color.GRAY);
        Window window = this.getOuterWindow();
        if (window instanceof CrclSwingClientJFrame) {
            CrclSwingClientJFrame frm = (CrclSwingClientJFrame) window;
            if (null != frm) {
                frm.setIconImage(DISCONNECTED_IMAGE);
                frm.setTitle("CRCL Client: Disconnected");
            }
        }
        if (outerContainer instanceof JInternalFrame) {
            JInternalFrame jInternalFrame = (JInternalFrame) outerContainer;
            jInternalFrame.setTitle("CRCL Client: Disconnected");
        }

        stopPollTimer();
        final XFutureVoid lastStartPollTimerFutureLocalCopy = this.lastStartPollTimerFuture;
        if (null == lastStartPollTimerFutureLocalCopy || lastStartPollTimerFutureLocalCopy.isDone()) {
            completeDisconnect();
        } else {
            System.out.println("lastStartPollTimerFutureLocalCopy = " + lastStartPollTimerFutureLocalCopy);
            lastStartPollTimerFutureLocalCopy.thenRun(this::completeDisconnect);
            try {
                Thread.sleep(11 + 2 * internal.getPoll_ms());
            } catch (InterruptedException ex) {
                Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
            if (isConnected()) {
                throw new RuntimeException("timed out waiting for lastStartPollTimerFuture to complete");
            }
        }
    }

    private void completeDisconnect() throws RuntimeException {
        internal.disconnect();
        jogWorldTransSpeedsSet = false;
        jogWorldRotSpeedsSet = false;
        if (isConnected()) {
            internal.setDebugConnectDisconnect(true);
            internal.setDebugInterrupts(true);
            internal.disconnect();
            throw new RuntimeException("still connected after disconnect crclSocket=" + internal.getCrclSocketString());
        }
    }

    private void closeShowProgramLogPrintStream() {
        if (null != showProgramLogPrintStream) {
            PrintStream ps = showProgramLogPrintStream;
            showProgramLogPrintStream = null;
            ps.close();
        }
    }

    private volatile StackTraceElement finishDisconnectTrace @Nullable []  = null;

    @Override
    public void finishDisconnect() {
        finishDisconnectTrace = Thread.currentThread().getStackTrace();
        this.jButtonConnect.setEnabled(true);
        this.jButtonDisconnect.setEnabled(false);

        this.jButtonEnd.setEnabled(false);
        this.jButtonInit.setEnabled(false);
        this.jButtonMoveTo.setEnabled(false);
        this.jButtonCloseGripper.setEnabled(false);
        this.jButtonOpenGripper.setEnabled(false);
        this.jButtonOpenToolChanger.setEnabled(false);
        this.jButtonCloseToolChanger.setEnabled(false);

        this.jButtonEnd.setToolTipText("Disconnected?");
        this.jButtonInit.setToolTipText("Disconnected?");
        this.jButtonMoveTo.setToolTipText("Disconnected?");
        this.jButtonCloseGripper.setToolTipText("Disconnected?");
        this.jButtonOpenGripper.setToolTipText("Disconnected?");
        this.jButtonOpenToolChanger.setToolTipText("Disconnected?");
        this.jButtonCloseToolChanger.setToolTipText("Disconnected?");
        this.stopPollTimer();
    }

    public boolean isConnected() {
        return internal.isConnected();
    }

    public void connect(String _host, int _port) {
        stopPollTimer();
        this.jTextFieldHost.setText(_host);
        setPort(_port);
        internal.connect(_host, _port);
        jogWorldTransSpeedsSet = false;
        jogWorldRotSpeedsSet = false;

        Window window = this.getOuterWindow();
        if (window instanceof CrclSwingClientJFrame) {
            CrclSwingClientJFrame frm = (CrclSwingClientJFrame) window;
            if (null != frm) {
                frm.setIconImage(BASE_IMAGE);
                frm.setTitle("CRCL Client: " + _host + ":" + _port);
            }
        }
        if (outerContainer instanceof JInternalFrame) {
            JInternalFrame jInternalFrame = (JInternalFrame) outerContainer;
            jInternalFrame.setTitle("CRCL Client: " + _host + ":" + _port);
        }
        disconnecting = !internal.isConnected();
        if (internal.isConnected() && jCheckBoxPoll.isSelected()) {
            startPollTimer();
        }
    }

    private javax.swing.@Nullable Timer jog_timer = null;

    private double lastJogJointPos = Double.NEGATIVE_INFINITY;

    private void jogJointStart(final double increment) {
        if (!internal.isConnected()
                || null == internal.getStatus()) {
            showMessage("Can not send command when not connected.");
            return;
        }
        internal.setJointJogIncrement(Double.parseDouble(this.jTextFieldJointJogIncrement.getText()));
//        this.setJointControlModes(JointControlModeEnumType.POSITION);
        final int index = this.jComboBoxJointAxis.getSelectedIndex() + 1;
        if (null != jog_timer) {
            jog_timer.stop();
            jog_timer = null;
        }
        lastJogJointPos = Double.NEGATIVE_INFINITY;
        jogStopFlag = false;
        ActionListener jogActionListener = new ActionListener() {

            private int apCount = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    apCount++;
                    if (jogStopFlag) {
                        return;
                    }
                    final CRCLStatusType status
                            = requireNonNull(internal.getStatus(), "internal.getStatus()");
                    final JointStatusType js = CRCLUtils.getJointStatus(status, index);
                    if (null == js) {
                        showMessage("Can't jog without joint position internal.getStatus() for joint " + index);
                        return;
                    }
                    final CommandStatusType commandStatus
                            = requireNonNull(status.getCommandStatus(), "status.getCommandStatus()");
                    if (commandStatus.getCommandState() == CommandStateEnumType.CRCL_ERROR) {
                        showMessage("Can't when status commandState = " + CommandStateEnumType.CRCL_ERROR);
                        jogStop();
                    }
                    final double currentJointPositionn = Objects.requireNonNull(js.getJointPosition(), "js.getJointPosition()").doubleValue();
                    double pos = currentJointPositionn;
                    if (apCount > 1) {
                        if (commandStatus.getCommandState() != CommandStateEnumType.CRCL_DONE) {
                            if (CrclSwingClientJPanel.this.menuOuter.isDebugWaitForDoneSelected()
                                    || CrclSwingClientJPanel.this.menuOuter.isDebugSendCommandSelected()) {
                                System.err.println("Jog Timer ActionListener waiting for DONE");
                            }
                            return;
                        }
                        if (commandStatus.getCommandID() < internal.getCmdId()) {
                            if (CrclSwingClientJPanel.this.menuOuter.isDebugWaitForDoneSelected()
                                    || CrclSwingClientJPanel.this.menuOuter.isDebugSendCommandSelected()) {
                                System.err.println("Jog Timer ActionListener waiting for ID greater than " + internal.getCmdId());
                            }
                            return;
                        }
                        if (Math.abs(pos - lastJogJointPos) <= Math.abs((increment) * 0.001)) {
                            System.err.println("Position unchanged from last jog attempt. pos=" + pos);
                        }
                    }
                    ActuateJointType aj = new ActuateJointType();
                    aj.setJointNumber(js.getJointNumber());

//                    
                    lastJogJointPos = pos;
                    aj.setJointPosition(currentJointPositionn + increment);
                    JointSpeedAccelType jsa = new JointSpeedAccelType();
                    jsa.setJointSpeed(Double.parseDouble(CrclSwingClientJPanel.this.jTextFieldJointJogSpeed.getText()));
                    aj.setJointDetails(jsa);
                    ActuateJointsType ajst = new ActuateJointsType();
                    addToActuateJoint(ajst, aj);
                    incAndSendCommandFromAwt(ajst);
//                    apCount = 0;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    jogStop();
                }
            }

        };
        jogActionListener.actionPerformed(new ActionEvent(this, JOG_JOINT_START_ACTION_ID, "jogJointStart"));
        jog_timer = new javax.swing.Timer(internal.getJogInterval(), jogActionListener);
        jog_timer.start();
    }
    private static final int JOG_JOINT_START_ACTION_ID = ACTION_FIRST + 1;
    private static final int JOG_WORLD_START_ACTION_ID = ACTION_FIRST + 2;

    private boolean jogStopFlag = true;

    @SuppressWarnings("nullness")
    private static void addToActuateJoint(ActuateJointsType ajst, ActuateJointType aj) {
        ajst.getActuateJoint().add(aj);
    }

    /**
     * Get the value of rpyJogIncrement
     *
     * @return the value of rpyJogIncrement
     */
    public double getRpyJogIncrement() {
        return rpyJogIncrement;
    }

    /**
     * Set the value of rpyJogIncrement
     *
     * @param rpyJogIncrement new value of rpyJogIncrement
     */
    public void setRpyJogIncrement(double rpyJogIncrement) {
        this.rpyJogIncrement = rpyJogIncrement;
    }

    private void jogWorldStart(double sign) {
        try {
            if (null == internal.getStatus()
                    || null == internal.getStatus()) {
                showMessage("Can not send command when not connected.");
                return;
            }

//        this.setJointControlModes(JointControlModeEnumType.POSITION);
            final String axis = (String) this.jComboBoxXYZRPY.getSelectedItem();
            double tmpinc = 1.0;
            sign = Math.signum(sign);
            switch (axis) {
                case "X":
                case "Y":
                case "Z":
                    tmpinc = internal.getXyzJogIncrement() * sign;
                    break;

                case "Roll":
                case "Pitch":
                case "Yaw":
                    tmpinc = Math.toRadians(this.rpyJogIncrement) * sign;
                    break;

                default:
                    throw new IllegalStateException("Invalid axis selected: " + axis);
            }
            final double axisIncrement = tmpinc;
            final double inc = tmpinc;
            if (null != jog_timer) {
                jog_timer.stop();
                jog_timer = null;
            }
            jogStopFlag = false;
            final boolean moveSentArray[] = new boolean[1];
            moveSentArray[0] = false;
            final int actionCountArray[] = new int[1];
            actionCountArray[0] = 0;
            StringBuilder returnReasonStringBuilder = new StringBuilder();
            ActionListener jogActionListener = new ActionListener() {

                private int actionCount = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        actionCount++;
                        actionCountArray[0] = actionCount;
                        if (actionCountArray[0] >= 20 && !moveSentArray[0]) {
                            throw new RuntimeException("returnReasonStringBuilder=" + returnReasonStringBuilder.toString() + ", actionCountArray=" + Arrays.toString(actionCountArray) + ", moveSentArray=" + Arrays.toString(moveSentArray));
                        }
                        if (jogStopFlag) {
                            String reasonString = "jogStopFlag\n";
                            returnReasonStringBuilder.append(reasonString);
                            System.out.println("reasonString = " + reasonString);
                            return;
                        }
                        final CRCLStatusType status = requireNonNull(internal.getStatus(), "internal.getStatus()");
                        final CommandStatusType commandStatus = requireNonNull(status.getCommandStatus(), "status.getCommandStatus()");
                        final CommandStateEnumType localCommandState = commandStatus.getCommandState();
                        final CRCLCommandType localLastCommandSent = internal.getLastCommandSent();
                        if (!(localLastCommandSent instanceof StopMotionType)) {
                            if (localCommandState != CommandStateEnumType.CRCL_DONE
                                    && (!jCheckBoxWaitForPrevJogMoveDone.isSelected() || !(localLastCommandSent instanceof MoveToType))) {
                                if (null == localLastCommandSent && null == internal.getLastScheduledCommand()) {
                                    throw new RuntimeException("commandStatus.getCommandState()=" + localCommandState + ", internal.getLastCommandSent()=" + localLastCommandSent + ", internal.getLastScheduledCommand()=" + internal.getLastScheduledCommand());
                                }
                                String reasonString = "Jog Timer ActionListener waiting for DONE commandStatus.getCommandState()=" + localCommandState + ", internal.getLastCommandSent()=" + localLastCommandSent + ", internal.getLastScheduledCommand()=" + internal.getLastScheduledCommand() + "\n";
                                if (CrclSwingClientJPanel.this.menuOuter.isDebugWaitForDoneSelected()
                                        || CrclSwingClientJPanel.this.menuOuter.isDebugSendCommandSelected()) {
                                    System.err.println(reasonString);
                                }
                                returnReasonStringBuilder.append(reasonString);
                                System.out.println("reasonString = " + reasonString);
                                return;
                            }
                        }
                        if (actionCount < 2 && !internal.isInitSent()) {
                            InitCanonType initCmd = new InitCanonType();
                            incAndSendCommandFromAwt(initCmd);
                            String reasonString = "actionCount=" + actionCount + " &&  !internal.isInitSent()\n";
                            returnReasonStringBuilder.append(reasonString);
                            System.out.println("reasonString = " + reasonString);
                            return;
                        }
                        if (!jogWorldTransSpeedsSet) {
                            SetTransSpeedType stst = new SetTransSpeedType();
                            TransSpeedAbsoluteType tas = new TransSpeedAbsoluteType();
                            tas.setSetting(Double.parseDouble(jTextFieldTransSpeed.getText()));
                            stst.setTransSpeed(tas);
                            incAndSendCommandFromAwt(stst);
                            jogWorldTransSpeedsSet = true;
                            String reasonString = "!jogWorldTransSpeedsSet\n";
                            returnReasonStringBuilder.append(reasonString);
                            System.out.println("reasonString = " + reasonString);
                            return;
                        }
                        if (!jogWorldRotSpeedsSet) {
                            SetRotSpeedType srst = new SetRotSpeedType();
                            RotSpeedAbsoluteType ras = new RotSpeedAbsoluteType();
                            ras.setSetting(Double.parseDouble(jTextFieldRotationSpeed.getText()));
                            srst.setRotSpeed(ras);
                            incAndSendCommandFromAwt(srst);
                            String reasonString = "!jogWorldRotSpeedsSet\n";
                            returnReasonStringBuilder.append(reasonString);
                            System.out.println("reasonString = " + reasonString);
                            jogWorldRotSpeedsSet = true;
                        }
                        if (localCommandState == CommandStateEnumType.CRCL_ERROR) {
                            jogStop();
                            final String statusString = commandStatus.getStateDescription();
                            String reasonString = "Can not jog when status is " + CommandStateEnumType.CRCL_ERROR + " : "
                                    + statusString + "\n";
                            showMessage(reasonString);
                            returnReasonStringBuilder.append(reasonString);
                            System.out.println("reasonString = " + reasonString);
                            return;
                        }

                        if (commandStatus.getCommandID() < internal.getCmdId()) {
                            if (jogStopFlag
                                    || actionCount > 10
                                    || !(localLastCommandSent instanceof StopMotionType)) {
                                if (CrclSwingClientJPanel.this.menuOuter.isDebugWaitForDoneSelected()
                                        || CrclSwingClientJPanel.this.menuOuter.isDebugSendCommandSelected()) {
                                    System.err.println("Jog Timer ActionListener internal.getStatus().getCommandStatus().getCommandID() < internal.getCmdId() ");
                                    System.err.println("internal.getCmdId() = " + internal.getCmdId());
                                    System.err.println("commandStatus.getCommandID() = " + commandStatus.getCommandID());
                                    System.err.println("internal.getLastCommandSent() = " + localLastCommandSent);
                                    System.err.println("internal.getLastCommandSentStackTrace() = " + Arrays.toString(internal.getLastCommandSentStackTrace()));
                                    System.err.println("internal.getPrevLastCommandSent() = " + internal.getPrevLastCommandSent());
                                }
                                String reasonString = "commandStatus.getCommandID() < internal.getCmdId() : jogStopFlag=" + jogStopFlag + ", actionCount=" + actionCount + ",  internal.getLastCommandSent()=" + localLastCommandSent + "\n";
                                returnReasonStringBuilder.append(reasonString);
                                System.out.println("reasonString = " + reasonString);
                                return;
                            }
                        }
                        MoveToType moveToCmd = new MoveToType();
                        PoseType endPos = new PoseType();
                        PointType endPosPoint = new PointType();
                        endPos.setPoint(endPosPoint);
                        VectorType endPosXAxis = new VectorType();
                        endPos.setXAxis(endPosXAxis);
                        VectorType endPosZAxis = new VectorType();
                        endPos.setZAxis(endPosZAxis);
                        moveToCmd.setEndPosition(endPos);
                        PoseType pose = Optional.ofNullable(internal)
                                .map(CrclSwingClientInner::getStatus)
                                .map(CRCLPosemath::getNullablePose)
                                .orElse(null);
                        if (null != pose) {
                            final PointType posePoint = requireNonNull(pose.getPoint(), "pose.getPoint()");
                            endPosPoint.setX(posePoint.getX());
                            endPosPoint.setY(posePoint.getY());
                            endPosPoint.setZ(posePoint.getZ());
                            final VectorType poseXAxis = requireNonNull(pose.getXAxis(), "pose.getXAxis()");
                            endPosXAxis.setI(poseXAxis.getI());
                            endPosXAxis.setJ(poseXAxis.getJ());
                            endPosXAxis.setK(poseXAxis.getK());
                            final VectorType poseZAxis = requireNonNull(pose.getZAxis(), "pose.getZAxis()");
                            endPosZAxis.setI(poseZAxis.getI());
                            endPosZAxis.setJ(poseZAxis.getJ());
                            endPosZAxis.setK(poseZAxis.getK());
                            switch (axis) {
                                case "X":
                                    endPosPoint.setX(posePoint.getX() + axisIncrement);
                                    break;

                                case "Y":
                                    endPosPoint.setY(posePoint.getY() + axisIncrement);
                                    break;

                                case "Z":
                                    endPosPoint.setZ(posePoint.getZ() + axisIncrement);
                                    break;

                                case "Roll":
                                    incrementRoll(moveToCmd, inc);
                                    break;

                                case "Pitch":
                                    incrementPitch(moveToCmd, inc);
                                    break;

                                case "Yaw":
                                    incrementYaw(moveToCmd, inc);
                                    break;

                                default:
                                    throw new IllegalStateException("Invalid axis selected: " + axis);
                            }
                            incAndSendCommandFromAwt(moveToCmd);
                            moveSentArray[0] = true;
                        } else {
                            showMessage("Can't jog when pose == null");
                            jogStop();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        jogStop();
                    }
                }

                private void incrementYaw(MoveToType moveToType, final double inc) throws PmException {
                    final PoseType pose = requireNonNull(internal.currentStatusPose(), "internal.getPose()");
                    PmRotationMatrix pm = CRCLPosemath.toPmRotationMatrix(pose);
                    PmRpy rpy = Posemath.toRpy(pm);
                    rpy.y += inc;
                    PmRotationVector pm2 = Posemath.toRot(rpy);
                    PoseType nextPose = CRCLPosemath.toPoseType(
                            CRCLPosemath.toPmCartesian(internal.currentStatusPoint()),
                            pm2);
                    moveToType.setEndPosition(nextPose);
                }

                private void incrementPitch(MoveToType moveToType, final double inc) throws PmException {
                    final PoseType pose = requireNonNull(internal.currentStatusPose(), "internal.getPose()");
                    PmRotationMatrix pm = CRCLPosemath.toPmRotationMatrix(pose);
                    PmRpy rpy = Posemath.toRpy(pm);
                    rpy.p += inc;
                    PmRotationVector pm2 = Posemath.toRot(rpy);
                    PoseType nextPose = CRCLPosemath.toPoseType(
                            CRCLPosemath.toPmCartesian(internal.currentStatusPoint()),
                            pm2);
                    moveToType.setEndPosition(nextPose);
                }

                private void incrementRoll(MoveToType moveToType, final double inc) throws PmException {
                    final PoseType pose = requireNonNull(internal.currentStatusPose(), "internal.getPose()");
                    PmRotationMatrix pm = CRCLPosemath.toPmRotationMatrix(pose);
                    PmRpy rpy = Posemath.toRpy(pm);
                    rpy.r += inc;
                    PmRotationVector pm2 = Posemath.toRot(rpy);
                    PoseType nextPose = CRCLPosemath.toPoseType(
                            CRCLPosemath.toPmCartesian(internal.currentStatusPoint()),
                            pm2);
                    moveToType.setEndPosition(nextPose);
                }
            };
//            while (!moveSentArray[0] && actionCountArray[0] < 5) {
            jogActionListener.actionPerformed(new ActionEvent(this, JOG_WORLD_START_ACTION_ID, "jogWorldStart"));
//            }
//            if (actionCountArray[0] >= 5 && !moveSentArray[0]) {
//                throw new RuntimeException("returnReasonStringBuilder=" + returnReasonStringBuilder.toString() + ", actionCountArray=" + Arrays.toString(actionCountArray) + ", moveSentArray=" + Arrays.toString(moveSentArray));
//            }
            jog_timer = new javax.swing.Timer(internal.getJogInterval(), jogActionListener);
            jog_timer.start();
        } catch (Exception ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, "Can not start world jog.", ex);
        }
    }

    private void jogStop() {
        jogStopFlag = true;
        if (null != jog_timer) {
            try {
                jog_timer.stop();
                jog_timer = null;
                internal.stopMotion(FAST);
            } catch (Exception ex) {
                Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @SuppressWarnings("nullness")
    private static final @NonNull
    StopConditionEnumType FAST = StopConditionEnumType.FAST;

    @Override
    public void checkPollSelected() {
        if (this.jCheckBoxPoll.isSelected()) {
            this.startPollTimer();
        }
    }

    private void commonJogStop() {
        this.jogStop();
        if (!internal.isInitSent() || !internal.isConnected()) {
            showNotJogReady();
            return;
        }
        this.jLabelJointJogMinus.setBackground(Color.WHITE);
        this.jLabelJointJogMinus.setForeground(Color.BLACK);
        this.jPanelJointJogMinus.setBackground(Color.WHITE);
        this.jLabelJointJogMinus.repaint();
        this.jPanelJointJogMinus.repaint();
        this.jLabelJogMinus.setBackground(Color.WHITE);
        this.jLabelJogMinus.setForeground(Color.BLACK);
        this.jPanelJogMinus.setBackground(Color.WHITE);
        this.jLabelJogMinus.repaint();
        this.jPanelJogMinus.repaint();
        this.jLabelJointJogPlus.setBackground(Color.WHITE);
        this.jLabelJointJogPlus.setForeground(Color.BLACK);
        this.jPanelJointJogPlus.setBackground(Color.WHITE);
        this.jLabelJointJogPlus.repaint();
        this.jPanelJointJogPlus.repaint();
        this.jLabelJogPlus.setBackground(Color.WHITE);
        this.jLabelJogPlus.setForeground(Color.BLACK);
        this.jPanelJogPlus.setBackground(Color.WHITE);
        this.jLabelJogPlus.repaint();
        this.jPanelJogPlus.repaint();
    }

    public @Nullable
    String getCrclClientErrorMessage() {
        return internal.getCrclClientErrorMessage();
    }

    public void clearCrclClientErrorMessage() {
        internal.clearCrclClientErrorMessage();
    }

    private void saveRecentCommandInstance(CRCLCommandInstanceType cmdInstance) throws Exception {
        CRCLSocket tmpcs = internal.getTempCRCLSocket();
        String s = tmpcs.commandInstanceToPrettyDocString(cmdInstance, true);
        File fDir = new File(CRCLUtils.getCrclUserHomeDir(), recent_files_dir);
        boolean made_dir = fDir.mkdirs();
//        Logger.getLogger(CrclSwingClientJPanel.class.getName()).finest(() -> "mkdir " + fDir + " returned " + made_dir);
        final CRCLCommandType crclCommand = cmdInstance.getCRCLCommand();
        if (null == crclCommand) {
            return;
        }
        String name = crclCommand.getClass().getSimpleName();
        int pindex = name.lastIndexOf('.');
        if (pindex > 0 && pindex < name.length()) {
            name = name.substring(pindex + 1);
        }
        File fDir2 = new File(fDir, name);
        boolean made_dir2 = fDir2.mkdirs();
//        Logger.getLogger(CrclSwingClientJPanel.class.getName()).finest(() -> "mkdir " + fDir2 + " returned " + made_dir2);
        Date dNow = new Date();
        SimpleDateFormat ft
                = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss_a_zzz_");
        String date_string = ft.format(dNow);
        File f = File.createTempFile(date_string, ".xml", fDir2);
        try (FileWriter fw = new FileWriter(f)) {
            fw.write(s);
        }
        menuOuter.readRecentCommandFiles();
    }

    private static final String recent_programs_dir = ".crcl_pendant_client_recent_programs";

    private void saveRecentProgram(File fprog) throws JAXBException, IOException {
        Set<String> pathSet = this.getRecentPrograms();
        if (pathSet.contains(fprog.getCanonicalPath())) {
            return;
        }
        File fDir = new File(CRCLUtils.getCrclUserHomeDir(), recent_programs_dir);
        boolean made_dir = fDir.mkdirs();
//        Logger.getLogger(CrclSwingClientJPanel.class.getName()).finest(() -> "mkdir " + fDir + " returned " + made_dir);
        String name = fprog.getName();
        assert name != null : "@AssumeAssertion(nullness)";
        Date dNow = new Date();
        SimpleDateFormat ft
                = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss_a_zzz_");
        String date_string = ft.format(dNow);
        File flink = File.createTempFile(name + "_" + date_string, ".txt", fDir);
        try (FileWriter fw = new FileWriter(flink)) {
            fw.write(fprog.getCanonicalPath());
        }
    }

    public void saveRecentCommand(CRCLCommandType cmd) throws Exception {
        CRCLCommandInstanceType instanceForSave = new CRCLCommandInstanceType();
        instanceForSave.setCRCLCommand(cmd);
        this.saveRecentCommandInstance(instanceForSave);
    }

    private boolean justShowedXpathQueryDialog = false;

    public void showXpathQueryDialog() {
        if (internal.isConnected()) {
            if (!this.jCheckBoxPoll.isSelected()) {
                this.jCheckBoxPoll.setSelected(true);
                startPollTimer();
            }
        }
        justShowedXpathQueryDialog = true;
        XpathQueryJFrame frame = this.xqJFrame;
        if (null == frame) {
            try {
                frame = new XpathQueryJFrame();
                this.xqJFrame = frame;
            } catch (ParserConfigurationException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        if (null != frame) {
            frame.setVisible(true);
        }
    }

    public void browseOpenCommandXml() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "XML Instance Files", "xml");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File f = chooser.getSelectedFile();
                openXmlInstanceFile(f);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, null, ex);
                this.showMessage(ex);
            }
        }
    }

    public void startRunTest(Map<String, String> testPropsMap) {
        internal.startRunTestThread(testPropsMap);
    }

    public void savePoseList() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Comma-Separated-Values", "csv");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                this.internal.savePoseListToCsvFile(chooser.getSelectedFile().getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PmException ex) {
                Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void stopStatusReaderThread() {
        internal.stopStatusReaderThread();
    }

    public int getMaxRecordCommandsCount() {
        return internal.getMaxRecordCommandsCount();
    }

    public void setMaxRecordCommandsCount(int maxRecordCommandsCount) {
        internal.setMaxRecordCommandsCount(maxRecordCommandsCount);
    }

    public void setRecordCommands(boolean recordCommands) {
        internal.setRecordCommands(recordCommands);
    }

    public void showCommandLog() {
        MultiLineStringJPanel.showText(internal.getRecordedCommandsList().stream().map(cmd -> internal.getTempCRCLSocket().commandToPrettyString(cmd, "")).collect(Collectors.joining("\n\n")));
    }

    public void loadPrefsAction() {
        JFileChooser chooser = new JFileChooser(new File(CRCLUtils.getCrclUserHomeDir()));
        if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(this)) {
            File f = chooser.getSelectedFile();
            loadPrefsFile(f);
        }
    }

    public void setQuitOnTestCommandFailure(boolean q) {
        internal.setQuitOnTestCommandFailure(q);
    }

    public void openLogFile() {
        internal.openLogFile();
    }

    public void aboutAction() {
        try {
            JOptionPane.showMessageDialog(this, getVersion());
        } catch (Exception ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void savePrefsAction() {
        JFileChooser chooser = new JFileChooser(new File(CRCLUtils.getCrclUserHomeDir()));
        if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(this)) {
            File f = chooser.getSelectedFile();
            saveObjectProperties(f, this);
        }
    }

//    public void show3DPlot() {
//        try {
////            File tmpFile = File.createTempFile("poseList", ".csv");
////            this.internal.savePoseListToCsvFile(tmpFile.getAbsolutePath());
//            com.github.wshackle.poselist3dplot.MainJFrame.showPoseList(this.internal.getPoseList());
//        } catch (Exception ex) {
//            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void useExiAction() {
        if (this.isConnected()) {
            this.disconnect();
            connectCurrent();
        }
    }

    public @Nullable
    File getTempLogDir() {
        return internal.getTempLogDir();
    }

    public void setTempLogDir(File tempLogDir) {
        this.internal.setTempLogDir(tempLogDir);
    }

    public void showStatusLog() {
        try {
            File tmpFile
                    = (internal.getTempLogDir() != null)
                    ? File.createTempFile("poseList", ".csv", internal.getTempLogDir())
                    : File.createTempFile("poseList", ".csv");
            this.internal.savePoseListToCsvFile(tmpFile.getAbsolutePath());
            Desktop.getDesktop().open(tmpFile);
        } catch (IOException ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PmException ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void saveJTable(File f, boolean append, JTable jtable) throws IOException {
        boolean fNotEmpty = f.exists() && f.length() > 0;
        try (CSVPrinter printer = new CSVPrinter(new PrintStream(new FileOutputStream(f, append)), CSVFormat.DEFAULT)) {
            TableModel tm = jtable.getModel();
            if (!fNotEmpty || !append) {
                List<String> colNameList = new ArrayList<>();
                for (int i = 0; i < tm.getColumnCount(); i++) {
                    colNameList.add(tm.getColumnName(i));
                }
                printer.printRecord(colNameList);
            }
            for (int i = 0; i < tm.getRowCount(); i++) {
                List<Object> l = new ArrayList<>();
                for (int j = 0; j < tm.getColumnCount(); j++) {
                    Object o = tm.getValueAt(i, j);
//                    if (o instanceof File) {
//                        Path rel = f.getParentFile().toPath().toRealPath().relativize(Paths.get(((File) o).getCanonicalPath())).normalize();
//                        if (rel.toString().length() < ((File) o).getCanonicalPath().length()) {
//                            l.add(rel);
//                        } else {
//                            l.add(o);
//                        }
//                    } else {
                    l.add(o);
//                    }
                }
                printer.printRecord(l);
            }
        }
    }

    private volatile @Nullable
    File commandStatusLogFile = null;

    private File getCommandStatusLogFile() throws IOException {
        if (null == commandStatusLogFile) {
            commandStatusLogFile
                    = (internal.getTempLogDir() != null)
                    ? File.createTempFile("commandStatus_", ".csv", internal.getTempLogDir())
                    : File.createTempFile("commandStatus_", ".csv");
        }
        return commandStatusLogFile;
    }

    private volatile @Nullable
    File commandProfileMapLogFile = null;

    private File getCommandProfileMapFile() throws IOException {
        if (null == commandProfileMapLogFile) {
            commandProfileMapLogFile
                    = (internal.getTempLogDir() != null)
                    ? File.createTempFile("commandProfileMap_", ".csv", internal.getTempLogDir())
                    : File.createTempFile("commandProfileMap_", ".csv");
        }
        return commandProfileMapLogFile;
    }

    public void showCommandStatusLog() {
        try {
            File f = writeCommandStatusLogFile();
            Desktop.getDesktop().open(f);
        } catch (IOException ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showCommandProfileMap() {
        try {
            File f = writeCommandProfileMap();
            Desktop.getDesktop().open(f);
        } catch (IOException ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public File writeCommandProfileMap() throws IOException {
        File f = getCommandProfileMapFile();
        Map<String, Long> profileMap = internal.getCmdPerfMap();
        List<Map.Entry<String, Long>> entriesList = new ArrayList<>(profileMap.entrySet());
        Collections.sort(entriesList, Comparator.comparing((Map.Entry<String, Long> e) -> e.getValue()));
        try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {
            pw.println("name,time");
            for (Map.Entry<String, Long> entry : entriesList) {
                pw.println(entry.getKey() + "," + entry.getValue());
            }
        }
        return f;
    }

    public File writeCommandStatusLogFile() throws IOException {
        File f = getCommandStatusLogFile();
        saveJTable(f, true, jTableCommandStatusLog);
        this.internal.printCommandStatusLog(f, true, true, false, internal.getCommandStatusLogHeadings(), 20);
        return f;
    }

    public void browseOpenProgramXml() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "XML Program Files", "xml");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        this.clearRecordedPoints();
        final CRCLProgramType program = internal.getProgram();
        if (null != program) {
            programMiddlCommandsClear(program);
            try {
                this.showProgram(program, Collections.emptyList(), 0);
            } catch (Exception ex) {
                Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            openXmlProgramFile(f);
        }
    }

    @SuppressWarnings("nullness")
    private void programMiddlCommandsClear(final CRCLProgramType program) {
        if (null != program && null != program.getMiddleCommand()) {
            program.getMiddleCommand().clear();
        }
    }

    public boolean isValidateXmlSchema() {
        return internal.isValidateXmlSchema();
    }

    public void setValidateXmlSchema(boolean validateXmlSchema) {
        internal.setValidateXmlSchema(validateXmlSchema);
    }

    @Override
    public CRCLProgramType editProgram(CRCLProgramType program) {
        CRCLSocket editCrclSocket = CRCLSocket.getUtilSocket();
        program = ObjTableJPanel
                .editObject(program,
                        internal.getXpu(),
                        internal.getProgSchemaFiles(),
                        internal.getCheckProgramValidPredicate(),
                        editCrclSocket);
        return program;
    }

    @SuppressWarnings("nullness")
    public void openXmlInstanceFile(File f) throws Exception {
        String s = internal.getXpu().queryXml(f, "/");
        CRCLCommandInstanceType cmdInstance
                = internal.getTempCRCLSocket().stringToCommand(s, internal.isValidateXmlSchema());
        CRCLCommandType cmd = cmdInstance.getCRCLCommand();
        if (null == cmd) {
            return;
        }
        String sOrigToCheck = internal.getTempCRCLSocket().commandToString(cmdInstance, false);
        long origCommandId = cmd.getCommandID();
        cmd.setCommandID(internal.getCmdId());
        CRCLSocket editCrclSocket = CRCLSocket.getUtilSocket();;

        cmd = ObjTableJPanel.editObject(
                cmd,
                internal.getXpu(),
                internal.getCmdSchemaFiles(),
                internal.getCheckCommandValidPredicate(),
                editCrclSocket);
        if (null == cmd) {
            return;
        }
        cmd.setCommandID(origCommandId);
        cmdInstance.setCRCLCommand(cmd);
        String sToCheck = internal.getTempCRCLSocket().commandToString(cmdInstance, false);
        cmd.setCommandID(internal.getCmdId());
        if (!sToCheck.equals(sOrigToCheck)) {
            this.saveRecentCommand(cmd);
        }
        if (cmd instanceof MiddleCommandType) {
            MiddleCommandType cmdToAdd = (MiddleCommandType) cmd;
            CRCLProgramType program = internal.getProgram();
            if (program != null && JOptionPane.showConfirmDialog(this.getOuterWindow(), "Add command to program") == JOptionPane.YES_OPTION) {
                program.getMiddleCommand().add(cmdToAdd);
                setCommandId(cmdToAdd, internal.getCmdId() + 1);
                setCommandId(program.getEndCanon(), internal.getCmdId() + 2);
            }
            showProgram(program, Collections.emptyList(), 0);
        }
        incAndSendCommandFromAwt(cmd);
    }

    private String tableCommandString(CRCLCommandType cmd) throws ParserConfigurationException, SAXException, IOException, JAXBException {
        return CRCLSocket.commandToSimpleString(cmd);
    }

    private volatile @Nullable
    PrintStream showProgramLogPrintStream = null;
    private AtomicInteger logShowProgramCount = new AtomicInteger();
    private long showProgramLogStartTime = -1;

    private PrintStream getShowProgramLogPrintStream() throws IOException {
        if (null != showProgramLogPrintStream) {
            return showProgramLogPrintStream;
        }
        File programLog = File.createTempFile("showProgramLog_" + getPort(), ".txt");
        showDebugMessage("Logging showProgram debug info to " + programLog);
        showProgramLogStartTime = System.currentTimeMillis();
        showProgramLogPrintStream = new PrintStream(new FileOutputStream(programLog));
        return showProgramLogPrintStream;
    }

    private void logShowProgramInfo(CRCLProgramType program,
            @Nullable List<ProgramRunData> progRunDataList,
            int line) {
        try {
            if (!debugShowProgram) {
                return;
            }
            PrintStream ps = getShowProgramLogPrintStream();
            int count = logShowProgramCount.incrementAndGet();

            String programString = CRCLSocket.getUtilSocket().programToPrettyString(program, false);
            File programFile = File.createTempFile("showProgramLog_" + getPort() + "_" + count, ".xml");
            try (PrintStream psProgramFile = new PrintStream(new FileOutputStream(programFile))) {
                psProgramFile.println(programString);
            }
            File progRunDataListFile = File.createTempFile("showProgramLog_progRunDataList_" + getPort() + "_" + count, ".csv");
            int programRunDataListSize = -1;
            if (null != progRunDataList) {
                saveProgramRunDataListToCsv(progRunDataListFile, progRunDataList);
                programRunDataListSize = progRunDataList.size();
            } else {
                progRunDataListFile = null;
            }
            long time = System.currentTimeMillis();
            ps.println("count=" + count + ",time=" + time + ",(time-showProgramLogStartTime)=" + (time - showProgramLogStartTime));
            ps.println("logShowProgramInfo(program=\"" + programFile + "\", programRunDataList=\"" + progRunDataListFile + "\" (size=" + programRunDataListSize + "),line=" + line + ")");
            ps.flush();
        } catch (Exception exception) {
            LOGGER.log(Level.SEVERE, null, exception);
            showMessage(exception);
            if (exception instanceof RuntimeException) {
                throw (RuntimeException) exception;
            } else {
                throw new RuntimeException(exception);
            }
        }
    }

    private volatile @MonotonicNonNull
    CRCLProgramType programShowing = null;

    public void showProgram(
            CRCLProgramType program,
            @Nullable List<ProgramRunData> progRunDataList,
            int line) {
        try {
            programShowing = program;
            logShowProgramInfo(program, progRunDataList, line);
            DefaultTableModel dtm = (DefaultTableModel) this.jTableProgram.getModel();
            if (null == program) {
                dtm.setRowCount(0);
                return;
            }
            InitCanonType init
                    = Objects.requireNonNull(program.getInitCanon(), "program.getInitCanon()");
            List<MiddleCommandType> middleCommands
                    = CRCLUtils.getNonNullFilteredList(program.getMiddleCommand());
            EndCanonType endCommand
                    = Objects.requireNonNull(program.getEndCanon(), "program.getEndCanon()");
            dtm.setRowCount(2 + middleCommands.size());
            long initCmdId = init.getCommandID();
            dtm.setValueAt(-1, 0, 0);
            dtm.setValueAt(initCmdId, 0, 1);
            try {
                dtm.setValueAt(tableCommandString(init), 0, 2);
            } catch (JAXBException ex) {
                Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            ProgramRunData prd = null;
            if (null != progRunDataList && !progRunDataList.isEmpty()) {
                prd = progRunDataList.get(0);
            }
            if (null != prd && prd != ProgramRunData.PROGRAM_RUN_DATA_PLACEHOLDER) {
                dtm.setValueAt(prd.getTime(), 0, 3);
                dtm.setValueAt(prd.getDist(), 0, 4);
                dtm.setValueAt(prd.isResult(), 0, 5);
            } else {
                dtm.setValueAt(-1L, 0, 3);
                dtm.setValueAt(0.0, 0, 4);
                dtm.setValueAt(false, 0, 5);
            }
            for (int i = 0; i < middleCommands.size(); i++) {
                MiddleCommandType middleCommand = middleCommands.get(i);
                if (null == middleCommand) {
                    showDebugMessage("middleCommands contains null at " + i);
                    continue;
                }
//                maxCmdId = maxCmdId + 1;
                long midCmdId = middleCommand.getCommandID();
//                if (maxCmdId > midCmdId) {
//                    showDebugMessage("middleCommands contains command with too small command id  at " + i + "\n : maxCmdId=" + maxCmdId + ", midCmdId=" + midCmdId+", initCmdId="+initCmdId+",\n program.getInitCanon().getCommandID()="+program.getInitCanon().getCommandID());
//                    throw new IllegalStateException("middleCommands contains command with too small command id  at " + i + "\n : maxCmdId=" + maxCmdId + ", midCmdId=" + midCmdId+", initCmdId="+initCmdId+",\n program.getInitCanon().getCommandID()="+program.getInitCanon().getCommandID());
//                }
//                maxCmdId = Math.max(maxCmdId, middleCommand.getCommandID());
                dtm.setValueAt(i, i + 1, 0);
                dtm.setValueAt(midCmdId, i + 1, 1);
                dtm.setValueAt(tableCommandString(middleCommand), i + 1, 2);
                if (null != progRunDataList && progRunDataList.size() > i + 1 && i < line) {
                    prd = progRunDataList.get(i + 1);
                } else {
                    prd = null;
                }
                if (i > line + 1 && null != progRunDataList && progRunDataList.size() > i + 1
                        && progRunDataList.get(i + 1) != null && progRunDataList.get(i + 1) != ProgramRunData.PROGRAM_RUN_DATA_PLACEHOLDER) {
                    throw new IllegalStateException(" Program Data after line");
                }
                if (null != prd && ProgramRunData.PROGRAM_RUN_DATA_PLACEHOLDER != prd) {
                    dtm.setValueAt(prd.getTime(), i + 1, 3);
                    dtm.setValueAt(prd.getDist(), i + 1, 4);
                    dtm.setValueAt(prd.isResult(), i + 1, 5);
                } else {
                    dtm.setValueAt(-1L, i + 1, 3);
                    dtm.setValueAt(0.0, i + 1, 4);
                    dtm.setValueAt(false, i + 1, 5);
                }
            }
//            maxCmdId = maxCmdId + 1;
            long endCmdId = endCommand.getCommandID();
            if (null != progRunDataList && progRunDataList.size() > 1 + middleCommands.size()) {
                prd = progRunDataList.get(1 + middleCommands.size());
            }
            dtm.setValueAt(-1, 1 + middleCommands.size(), 0);
            dtm.setValueAt(endCmdId, 1 + middleCommands.size(), 1);
            dtm.setValueAt(tableCommandString(endCommand), 1 + middleCommands.size(), 2);
            if (null != prd && prd != ProgramRunData.PROGRAM_RUN_DATA_PLACEHOLDER) {
                dtm.setValueAt(prd.getTime(), 1 + middleCommands.size(), 3);
                dtm.setValueAt(prd.getDist(), 1 + middleCommands.size(), 4);
                dtm.setValueAt(prd.isResult(), 1 + middleCommands.size(), 5);
            } else {
                dtm.setValueAt(-1L, 1 + middleCommands.size(), 3);
                dtm.setValueAt(0.0, 1 + middleCommands.size(), 4);
                if (null == dtm.getValueAt(1 + middleCommands.size(), 5)) {
                    dtm.setValueAt(false, 1 + middleCommands.size(), 5);
                }
            }
            for (int i = 0; i < dtm.getRowCount(); i++) {
                for (int j = 0; j < dtm.getColumnCount(); j++) {
                    Object o = dtm.getValueAt(i, j);
                    checkClearTableModelValue(dtm, j, o, i);
                }
            }

            if (line >= 0 && line < dtm.getRowCount()) {
                jTableProgram.getSelectionModel().setSelectionInterval(line, line);
            }
        } catch (ParserConfigurationException | SAXException | IOException | JAXBException ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        programLineShowing = -1;
    }

    @SuppressWarnings("nullness")
    private void checkClearTableModelValue(DefaultTableModel dtm, int j, Object o, int i) {
        Class<?> clss = dtm.getColumnClass(j);
        if (!clss.isInstance(o)) {
            System.err.println("Bad object : " + o + " at " + i + "," + j + ": columnClass =" + clss);
            dtm.setValueAt(null, i, j);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "nullness", "deprecation", "rawtypes"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneLeft = new javax.swing.JTabbedPane();
        jPanelProgram = new javax.swing.JPanel();
        jScrollPaneProgram = new javax.swing.JScrollPane();
        jTableProgram = new javax.swing.JTable();
        jButtonProgramPause = new javax.swing.JButton();
        jButtonProgramAbort = new javax.swing.JButton();
        jButtonEditProgramItem = new javax.swing.JButton();
        jButtonDeletProgramItem = new javax.swing.JButton();
        jButtonAddProgramItem = new javax.swing.JButton();
        jButtonProgramRun = new javax.swing.JButton();
        jButtonProgramResume = new javax.swing.JButton();
        jButtonPlotProgramItem = new javax.swing.JButton();
        jButtonRunProgFromCurrentLine = new javax.swing.JButton();
        jButtonStepBack = new javax.swing.JButton();
        jButtonStepFwd = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldRunTime = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaSelectedProgramCommand = new javax.swing.JTextArea();
        jLabelHoldingObject2 = new javax.swing.JLabel();
        jCheckBoxMonitorHoldingOutput = new javax.swing.JCheckBox();
        jLabelExpectHoldingObject = new javax.swing.JLabel();
        jCheckBoxStepping = new javax.swing.JCheckBox();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldDistToSelected = new javax.swing.JTextField();
        jPanelJogging = new javax.swing.JPanel();
        jComboBoxJointAxis = new javax.swing.JComboBox<>();
        jPanelJointJogMinus = new javax.swing.JPanel();
        jLabelJointJogMinus = new javax.swing.JLabel();
        jPanelJointJogPlus = new javax.swing.JPanel();
        jLabelJointJogPlus = new javax.swing.JLabel();
        jComboBoxXYZRPY = new javax.swing.JComboBox<>();
        jPanelJogPlus = new javax.swing.JPanel();
        jLabelJogPlus = new javax.swing.JLabel();
        jPanelJogMinus = new javax.swing.JPanel();
        jLabelJogMinus = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldJointJogIncrement = new javax.swing.JTextField();
        jTextFieldXYZJogIncrement = new javax.swing.JTextField();
        jTextFieldRPYJogIncrement = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldJogInterval = new javax.swing.JTextField();
        lengthUnitComboBoxLengthUnit = new crcl.ui.misc.LengthUnitComboBox();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldJointJogSpeed = new javax.swing.JTextField();
        jTextFieldTransSpeed = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldRotationSpeed = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButtonRecordPoint = new javax.swing.JButton();
        jButtonOpenGripper = new javax.swing.JButton();
        jButtonCloseGripper = new javax.swing.JButton();
        jLabelHoldingObject = new javax.swing.JLabel();
        jButtonOpenToolChanger = new javax.swing.JButton();
        jButtonCloseToolChanger = new javax.swing.JButton();
        jCheckBoxWaitForPrevJogMoveDone = new javax.swing.JCheckBox();
        jPanelMoveTo = new javax.swing.JPanel();
        jButtonMoveTo = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableMoveToPose = new javax.swing.JTable();
        jCheckBoxStraight = new javax.swing.JCheckBox();
        jButtonMoveToCurrentPose = new javax.swing.JButton();
        jComboBoxMoveToPoseDisplayMode = new javax.swing.JComboBox<>();
        jButtonMoveToCurrentPoint = new javax.swing.JButton();
        jButtonMoveToDownPosition = new javax.swing.JButton();
        jScrollPaneMoveToGuardTable = new javax.swing.JScrollPane();
        jTableMoveToGuard = new javax.swing.JTable();
        jButtonAddGuard = new javax.swing.JButton();
        jButtonRemoveGuard = new javax.swing.JButton();
        transformJPanel1 = new crcl.ui.misc.TransformJPanel();
        jPanelCommandStatusLogOuter = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableCommandStatusLog = new javax.swing.JTable();
        jCheckBoxPauseCommandStatusLog = new javax.swing.JCheckBox();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldLogMaxLength = new javax.swing.JTextField();
        jCheckBoxLogCommandStatusToFile = new javax.swing.JCheckBox();
        jButtonShowCommandStatusLogFile = new javax.swing.JButton();
        jButtonPlotStatus = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanelConnectionTab = new javax.swing.JPanel();
        jTextFieldPort = new javax.swing.JTextField();
        jButtonConnect = new javax.swing.JButton();
        jButtonDisconnect = new javax.swing.JButton();
        jButtonEnd = new javax.swing.JButton();
        jButtonInit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldHost = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jCheckBoxPoll = new javax.swing.JCheckBox();
        jTextFieldPollTime = new javax.swing.JTextField();
        jCheckBoxReadTimeout = new javax.swing.JCheckBox();
        jTextFieldReadTimeout = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaPerfInfo = new javax.swing.JTextArea();
        jButtonShowPerfInfo = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldWaitForDoneDelay = new javax.swing.JTextField();
        jCheckBoxIgnoreWaitForDoneTimeouts = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextAreaLastStopCommand = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextAreaLastGetStatusCommand = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextAreaLastOtherCommand = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        jScrollPaneErrors = new javax.swing.JScrollPane();
        jTextAreaErrors = new javax.swing.JTextArea();
        jTabbedPaneRight = new javax.swing.JTabbedPane();
        jPanelStatus = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldStatCmdID = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableJoints = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldStatus = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablePose = new javax.swing.JTable();
        jTextFieldStatusID = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextAreaStateDescription = new javax.swing.JTextArea();
        jComboBoxPoseDisplayMode = new javax.swing.JComboBox<>();
        jPanelProgramPlot = new javax.swing.JPanel();
        jPanelOverheadProgramPlot = new javax.swing.JPanel();
        programPlotterJPanelOverhead = new crcl.ui.misc.ProgramPlotterJPanel();
        jPanelOverheadProgramPlot1 = new javax.swing.JPanel();
        programPlotterJPanelSide = new crcl.ui.misc.ProgramPlotterJPanel();

        jTabbedPaneLeft.setBorder(new javax.swing.border.SoftBevelBorder(0));
        jTabbedPaneLeft.setName(""); // NOI18N
        jTabbedPaneLeft.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneLeftStateChanged(evt);
            }
        });
        jTabbedPaneLeft.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jTabbedPaneLeftComponentResized(evt);
            }
        });

        jTableProgram.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(-1),  new Long(1), "InitCanonType",  new Long(-1),  new Double(0.0), null},
                { new Integer(-1),  new Long(2), "EndCanonType",  new Long(-1),  new Double(0.0), null}
            },
            new String [] {
                "Index", "ID", "Type", "Time To Execute(ms)", "Distance Moved", "Result"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Long.class, java.lang.String.class, java.lang.Long.class, java.lang.Double.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneProgram.setViewportView(jTableProgram);

        jButtonProgramPause.setText("Pause");
        jButtonProgramPause.setEnabled(false);
        jButtonProgramPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProgramPauseActionPerformed(evt);
            }
        });

        jButtonProgramAbort.setText("Abort");
        jButtonProgramAbort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProgramAbortActionPerformed(evt);
            }
        });

        jButtonEditProgramItem.setText("Edit Item");
        jButtonEditProgramItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditProgramItemActionPerformed(evt);
            }
        });

        jButtonDeletProgramItem.setText("Delete");
        jButtonDeletProgramItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeletProgramItemActionPerformed(evt);
            }
        });

        jButtonAddProgramItem.setText("Add");
        jButtonAddProgramItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddProgramItemActionPerformed(evt);
            }
        });

        jButtonProgramRun.setText("Run From Start");
        jButtonProgramRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProgramRunActionPerformed(evt);
            }
        });

        jButtonProgramResume.setText("Resume");
        jButtonProgramResume.setEnabled(false);
        jButtonProgramResume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProgramResumeActionPerformed(evt);
            }
        });

        jButtonPlotProgramItem.setText("Plot");
        jButtonPlotProgramItem.setEnabled(false);
        jButtonPlotProgramItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlotProgramItemActionPerformed(evt);
            }
        });

        jButtonRunProgFromCurrentLine.setText("Run from Current");
        jButtonRunProgFromCurrentLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRunProgFromCurrentLineActionPerformed(evt);
            }
        });

        jButtonStepBack.setText("Step Back");
        jButtonStepBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStepBackActionPerformed(evt);
            }
        });

        jButtonStepFwd.setText("Step Fwd");
        jButtonStepFwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStepFwdActionPerformed(evt);
            }
        });

        jLabel12.setText("Run Time:");

        jTextFieldRunTime.setEditable(false);
        jTextFieldRunTime.setText("-1.0");

        jLabel13.setText("Selected Command:");

        jTextAreaSelectedProgramCommand.setEditable(false);
        jTextAreaSelectedProgramCommand.setColumns(20);
        jTextAreaSelectedProgramCommand.setRows(5);
        jScrollPane5.setViewportView(jTextAreaSelectedProgramCommand);

        jLabelHoldingObject2.setText("HoldingObject: UNKNOWN");
        jLabelHoldingObject2.setOpaque(true);

        jCheckBoxMonitorHoldingOutput.setText("Monitor Holding");

        jLabelExpectHoldingObject.setText("Expect Holding Object: FALSE");
        jLabelExpectHoldingObject.setOpaque(true);

        jCheckBoxStepping.setText("Step");
        jCheckBoxStepping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSteppingActionPerformed(evt);
            }
        });

        jLabel20.setText("Dist To Selected:");

        jTextFieldDistToSelected.setText("0.0");

        javax.swing.GroupLayout jPanelProgramLayout = new javax.swing.GroupLayout(jPanelProgram);
        jPanelProgram.setLayout(jPanelProgramLayout);
        jPanelProgramLayout.setHorizontalGroup(
            jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProgramLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProgramLayout.createSequentialGroup()
                        .addGroup(jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5)
                            .addComponent(jScrollPaneProgram))
                        .addGap(10, 10, 10))
                    .addGroup(jPanelProgramLayout.createSequentialGroup()
                        .addGroup(jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelProgramLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldRunTime, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBoxMonitorHoldingOutput)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelHoldingObject2))
                            .addComponent(jLabel13)
                            .addGroup(jPanelProgramLayout.createSequentialGroup()
                                .addComponent(jButtonEditProgramItem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonDeletProgramItem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAddProgramItem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonPlotProgramItem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonStepBack)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonStepFwd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelExpectHoldingObject))
                            .addGroup(jPanelProgramLayout.createSequentialGroup()
                                .addComponent(jButtonProgramPause)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonProgramResume)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonProgramAbort)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonProgramRun)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonRunProgFromCurrentLine)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBoxStepping)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldDistToSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(69, Short.MAX_VALUE))))
        );
        jPanelProgramLayout.setVerticalGroup(
            jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProgramLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneProgram, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextFieldRunTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxMonitorHoldingOutput)
                    .addComponent(jLabelHoldingObject2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonProgramPause)
                    .addComponent(jButtonProgramAbort)
                    .addComponent(jButtonProgramRun)
                    .addComponent(jButtonProgramResume)
                    .addComponent(jButtonRunProgFromCurrentLine)
                    .addComponent(jCheckBoxStepping)
                    .addComponent(jLabel20)
                    .addComponent(jTextFieldDistToSelected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEditProgramItem)
                    .addComponent(jButtonDeletProgramItem)
                    .addComponent(jButtonAddProgramItem)
                    .addComponent(jButtonPlotProgramItem)
                    .addComponent(jButtonStepBack)
                    .addComponent(jButtonStepFwd)
                    .addComponent(jLabelExpectHoldingObject))
                .addContainerGap())
        );

        jTabbedPaneLeft.addTab("Program", jPanelProgram);

        jPanelJogging.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanelJogging.setName("Jogging"); // NOI18N

        jComboBoxJointAxis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Joint 1 (S)", "Joint 2 (L)", "Joint 3 (U)", "Joint 4 (R)", "Joint 5 (B)", "Joint 6 (T)", "Joint 7 (E)", "Joint 8 " }));
        jComboBoxJointAxis.setEnabled(false);

        jPanelJointJogMinus.setBackground(new java.awt.Color(255, 255, 255));
        jPanelJointJogMinus.setBorder(new javax.swing.border.MatteBorder(null));

        jLabelJointJogMinus.setBackground(new java.awt.Color(255, 255, 255));
        jLabelJointJogMinus.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabelJointJogMinus.setText("Joint Jog -");
        jLabelJointJogMinus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelJointJogMinusMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabelJointJogMinusMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelJointJogMinusMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelJointJogMinusLayout = new javax.swing.GroupLayout(jPanelJointJogMinus);
        jPanelJointJogMinus.setLayout(jPanelJointJogMinusLayout);
        jPanelJointJogMinusLayout.setHorizontalGroup(
            jPanelJointJogMinusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJointJogMinusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelJointJogMinus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelJointJogMinusLayout.setVerticalGroup(
            jPanelJointJogMinusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJointJogMinusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelJointJogMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelJointJogPlus.setBackground(new java.awt.Color(255, 255, 255));
        jPanelJointJogPlus.setBorder(new javax.swing.border.MatteBorder(null));

        jLabelJointJogPlus.setBackground(new java.awt.Color(255, 255, 255));
        jLabelJointJogPlus.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabelJointJogPlus.setText("Joint Jog +");
        jLabelJointJogPlus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelJointJogPlusMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelJointJogPlusMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabelJointJogPlusMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanelJointJogPlusLayout = new javax.swing.GroupLayout(jPanelJointJogPlus);
        jPanelJointJogPlus.setLayout(jPanelJointJogPlusLayout);
        jPanelJointJogPlusLayout.setHorizontalGroup(
            jPanelJointJogPlusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJointJogPlusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelJointJogPlus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelJointJogPlusLayout.setVerticalGroup(
            jPanelJointJogPlusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJointJogPlusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelJointJogPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jComboBoxXYZRPY.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "X", "Y", "Z", "Roll", "Pitch", "Yaw", " " }));
        jComboBoxXYZRPY.setEnabled(false);

        jPanelJogPlus.setBackground(new java.awt.Color(255, 255, 255));
        jPanelJogPlus.setBorder(new javax.swing.border.MatteBorder(null));

        jLabelJogPlus.setBackground(new java.awt.Color(255, 255, 255));
        jLabelJogPlus.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabelJogPlus.setText("Jog +");
        jLabelJogPlus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelJogPlusMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelJogPlusMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabelJogPlusMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanelJogPlusLayout = new javax.swing.GroupLayout(jPanelJogPlus);
        jPanelJogPlus.setLayout(jPanelJogPlusLayout);
        jPanelJogPlusLayout.setHorizontalGroup(
            jPanelJogPlusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJogPlusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelJogPlus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelJogPlusLayout.setVerticalGroup(
            jPanelJogPlusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJogPlusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelJogPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelJogMinus.setBackground(new java.awt.Color(255, 255, 255));
        jPanelJogMinus.setBorder(new javax.swing.border.MatteBorder(null));

        jLabelJogMinus.setBackground(new java.awt.Color(255, 255, 255));
        jLabelJogMinus.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabelJogMinus.setText("Jog -");
        jLabelJogMinus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelJogMinusMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelJogMinusMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabelJogMinusMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanelJogMinusLayout = new javax.swing.GroupLayout(jPanelJogMinus);
        jPanelJogMinus.setLayout(jPanelJogMinusLayout);
        jPanelJogMinusLayout.setHorizontalGroup(
            jPanelJogMinusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJogMinusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelJogMinus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelJogMinusLayout.setVerticalGroup(
            jPanelJogMinusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJogMinusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelJogMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText("Joint Jog Increment: ");

        jLabel7.setText("XYZ Jog Increment:");

        jLabel8.setText("RPY Jog Increment:");

        jTextFieldJointJogIncrement.setText("3.0");
        jTextFieldJointJogIncrement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldJointJogIncrementActionPerformed(evt);
            }
        });

        jTextFieldXYZJogIncrement.setText("3.0");
        jTextFieldXYZJogIncrement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldXYZJogIncrementActionPerformed(evt);
            }
        });

        jTextFieldRPYJogIncrement.setText("3.0");
        jTextFieldRPYJogIncrement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRPYJogIncrementActionPerformed(evt);
            }
        });

        jLabel9.setText("Jog Time Period (ms) :");

        jTextFieldJogInterval.setText("100");
        jTextFieldJogInterval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldJogIntervalActionPerformed(evt);
            }
        });

        lengthUnitComboBoxLengthUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lengthUnitComboBoxLengthUnitActionPerformed(evt);
            }
        });

        jLabel11.setText("Joint Jog Speed:");

        jTextFieldJointJogSpeed.setText("100");
        jTextFieldJointJogSpeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldJointJogSpeedActionPerformed(evt);
            }
        });

        jTextFieldTransSpeed.setText("3.0");
        jTextFieldTransSpeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTransSpeedActionPerformed(evt);
            }
        });

        jLabel14.setText("Trans Speed:");

        jLabel15.setText("Rotation Speed:");

        jTextFieldRotationSpeed.setText("90.0");
        jTextFieldRotationSpeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRotationSpeedActionPerformed(evt);
            }
        });

        jLabel16.setText("degrees");

        jLabel17.setText("degrees/second");

        jButtonRecordPoint.setText("Record Point");
        jButtonRecordPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecordPointActionPerformed(evt);
            }
        });

        jButtonOpenGripper.setText("Open Gripper");
        jButtonOpenGripper.setEnabled(false);
        jButtonOpenGripper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenGripperActionPerformed(evt);
            }
        });

        jButtonCloseGripper.setText("Close Gripper");
        jButtonCloseGripper.setEnabled(false);
        jButtonCloseGripper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseGripperActionPerformed(evt);
            }
        });

        jLabelHoldingObject.setText("HoldingObject: Unknown");

        jButtonOpenToolChanger.setText("Open Tool Changer");
        jButtonOpenToolChanger.setEnabled(false);
        jButtonOpenToolChanger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenToolChangerActionPerformed(evt);
            }
        });

        jButtonCloseToolChanger.setText("Close Tool Changer");
        jButtonCloseToolChanger.setEnabled(false);
        jButtonCloseToolChanger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseToolChangerActionPerformed(evt);
            }
        });

        jCheckBoxWaitForPrevJogMoveDone.setText("Wait For Prev Jog Move Done");

        javax.swing.GroupLayout jPanelJoggingLayout = new javax.swing.GroupLayout(jPanelJogging);
        jPanelJogging.setLayout(jPanelJoggingLayout);
        jPanelJoggingLayout.setHorizontalGroup(
            jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJoggingLayout.createSequentialGroup()
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelJoggingLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelJoggingLayout.createSequentialGroup()
                                .addComponent(jComboBoxJointAxis, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelJointJogMinus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelJointJogPlus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelJoggingLayout.createSequentialGroup()
                                .addComponent(jComboBoxXYZRPY, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelJogMinus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelJogPlus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonOpenGripper)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelJoggingLayout.createSequentialGroup()
                                .addComponent(jButtonRecordPoint)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelHoldingObject))
                            .addComponent(jButtonCloseGripper)))
                    .addGroup(jPanelJoggingLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel14)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldJogInterval, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldJointJogSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldJointJogIncrement, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldRPYJogIncrement, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTransSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldXYZJogIncrement, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldRotationSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelJoggingLayout.createSequentialGroup()
                                .addComponent(lengthUnitComboBoxLengthUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonOpenToolChanger)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCloseToolChanger))
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)))
                    .addGroup(jPanelJoggingLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jCheckBoxWaitForPrevJogMoveDone)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanelJoggingLayout.setVerticalGroup(
            jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJoggingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanelJointJogMinus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBoxJointAxis)
                        .addComponent(jPanelJointJogPlus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonRecordPoint)
                        .addComponent(jLabelHoldingObject)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanelJogMinus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBoxXYZRPY)
                        .addComponent(jPanelJogPlus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonOpenGripper)
                        .addComponent(jButtonCloseGripper)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldXYZJogIncrement, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lengthUnitComboBoxLengthUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonOpenToolChanger)
                    .addComponent(jButtonCloseToolChanger))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTransSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRPYJogIncrement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRotationSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldJointJogIncrement, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldJointJogSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelJoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldJogInterval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxWaitForPrevJogMoveDone)
                .addContainerGap(233, Short.MAX_VALUE))
        );

        jTabbedPaneLeft.addTab("Jog", jPanelJogging);

        jPanelMoveTo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanelMoveTo.setName("MoveTo"); // NOI18N

        jButtonMoveTo.setText("MoveTo");
        jButtonMoveTo.setEnabled(false);
        jButtonMoveTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMoveToActionPerformed(evt);
            }
        });

        jTableMoveToPose.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"X",  new Double(0.0)},
                {"Y",  new Double(0.0)},
                {"Z",  new Double(0.0)},
                {"XI",  new Double(1.0)},
                {"XJ",  new Double(0.0)},
                {"XK",  new Double(0.0)},
                {"ZI",  new Double(0.0)},
                {"ZJ",  new Double(0.0)},
                {"ZK",  new Double(1.0)}
            },
            new String [] {
                "Pose Axis", "Position"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTableMoveToPose);

        jCheckBoxStraight.setSelected(true);
        jCheckBoxStraight.setText("Straight");

        jButtonMoveToCurrentPose.setText("Current Pose");
        jButtonMoveToCurrentPose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMoveToCurrentPoseActionPerformed(evt);
            }
        });

        jComboBoxMoveToPoseDisplayMode.setModel(new javax.swing.DefaultComboBoxModel(PoseDisplayMode.values()));
        jComboBoxMoveToPoseDisplayMode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxMoveToPoseDisplayModeItemStateChanged(evt);
            }
        });
        jComboBoxMoveToPoseDisplayMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMoveToPoseDisplayModeActionPerformed(evt);
            }
        });

        jButtonMoveToCurrentPoint.setText("Current Point");
        jButtonMoveToCurrentPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMoveToCurrentPointActionPerformed(evt);
            }
        });

        jButtonMoveToDownPosition.setText("Down Orientation");
        jButtonMoveToDownPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMoveToDownPositionActionPerformed(evt);
            }
        });

        jTableMoveToGuard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sensor ID", "SubField", "Limit", "Limit Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPaneMoveToGuardTable.setViewportView(jTableMoveToGuard);

        jButtonAddGuard.setText("Add Guard");
        jButtonAddGuard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddGuardActionPerformed(evt);
            }
        });

        jButtonRemoveGuard.setText("Remove Guard");
        jButtonRemoveGuard.setEnabled(false);
        jButtonRemoveGuard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveGuardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMoveToLayout = new javax.swing.GroupLayout(jPanelMoveTo);
        jPanelMoveTo.setLayout(jPanelMoveToLayout);
        jPanelMoveToLayout.setHorizontalGroup(
            jPanelMoveToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMoveToLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMoveToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMoveToLayout.createSequentialGroup()
                        .addComponent(jScrollPaneMoveToGuardTable, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelMoveToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonAddGuard)
                            .addComponent(jButtonRemoveGuard)))
                    .addGroup(jPanelMoveToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jComboBoxMoveToPoseDisplayMode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
                    .addGroup(jPanelMoveToLayout.createSequentialGroup()
                        .addComponent(jButtonMoveTo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxStraight)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonMoveToCurrentPose)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonMoveToCurrentPoint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonMoveToDownPosition)))
                .addContainerGap(250, Short.MAX_VALUE))
        );
        jPanelMoveToLayout.setVerticalGroup(
            jPanelMoveToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMoveToLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMoveToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonMoveTo)
                    .addComponent(jCheckBoxStraight)
                    .addComponent(jButtonMoveToCurrentPose)
                    .addComponent(jButtonMoveToCurrentPoint)
                    .addComponent(jButtonMoveToDownPosition))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxMoveToPoseDisplayMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelMoveToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneMoveToGuardTable, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelMoveToLayout.createSequentialGroup()
                        .addComponent(jButtonAddGuard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemoveGuard)))
                .addContainerGap(200, Short.MAX_VALUE))
        );

        jTabbedPaneLeft.addTab("MoveTo", jPanelMoveTo);
        jTabbedPaneLeft.addTab("Transform Setup", transformJPanel1);

        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTableCommandStatusLog.setModel(getCommandStatusLogModel());
        jScrollPane7.setViewportView(jTableCommandStatusLog);

        jCheckBoxPauseCommandStatusLog.setSelected(true);
        jCheckBoxPauseCommandStatusLog.setText("Pause Log");
        jCheckBoxPauseCommandStatusLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPauseCommandStatusLogActionPerformed(evt);
            }
        });

        jLabel19.setText(" Max: ");

        jTextFieldLogMaxLength.setText("200   ");
        jTextFieldLogMaxLength.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLogMaxLengthActionPerformed(evt);
            }
        });

        jCheckBoxLogCommandStatusToFile.setText("Log to File");
        jCheckBoxLogCommandStatusToFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxLogCommandStatusToFileActionPerformed(evt);
            }
        });

        jButtonShowCommandStatusLogFile.setText("Show");
        jButtonShowCommandStatusLogFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowCommandStatusLogFileActionPerformed(evt);
            }
        });

        jButtonPlotStatus.setText("Plot Status");
        jButtonPlotStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlotStatusActionPerformed(evt);
            }
        });

        jButton1.setText("Profile Map");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCommandStatusLogOuterLayout = new javax.swing.GroupLayout(jPanelCommandStatusLogOuter);
        jPanelCommandStatusLogOuter.setLayout(jPanelCommandStatusLogOuterLayout);
        jPanelCommandStatusLogOuterLayout.setHorizontalGroup(
            jPanelCommandStatusLogOuterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCommandStatusLogOuterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCommandStatusLogOuterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(jPanelCommandStatusLogOuterLayout.createSequentialGroup()
                        .addComponent(jCheckBoxPauseCommandStatusLog)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLogMaxLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxLogCommandStatusToFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonShowCommandStatusLogFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPlotStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(0, 192, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelCommandStatusLogOuterLayout.setVerticalGroup(
            jPanelCommandStatusLogOuterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCommandStatusLogOuterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCommandStatusLogOuterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxPauseCommandStatusLog)
                    .addComponent(jLabel19)
                    .addComponent(jTextFieldLogMaxLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxLogCommandStatusToFile)
                    .addComponent(jButtonShowCommandStatusLogFile)
                    .addComponent(jButtonPlotStatus)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneLeft.addTab("Command Status Log", jPanelCommandStatusLogOuter);

        jPanelConnectionTab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextFieldPort.setText("64444");

        jButtonConnect.setText("Connect");
        jButtonConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConnectActionPerformed(evt);
            }
        });

        jButtonDisconnect.setText("Disconnect ");
        jButtonDisconnect.setEnabled(false);
        jButtonDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDisconnectActionPerformed(evt);
            }
        });

        jButtonEnd.setText("End");
        jButtonEnd.setEnabled(false);
        jButtonEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEndActionPerformed(evt);
            }
        });

        jButtonInit.setText("Init");
        jButtonInit.setEnabled(false);
        jButtonInit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInitActionPerformed(evt);
            }
        });

        jLabel1.setText("Host: ");

        jTextFieldHost.setText("localhost");

        jLabel2.setText("Port:");

        jCheckBoxPoll.setSelected(true);
        jCheckBoxPoll.setText("Poll    Interval(ms):");
        jCheckBoxPoll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPollActionPerformed(evt);
            }
        });

        jTextFieldPollTime.setEditable(false);
        jTextFieldPollTime.setText("50");
        jTextFieldPollTime.setEnabled(false);
        jTextFieldPollTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPollTimeActionPerformed(evt);
            }
        });

        jCheckBoxReadTimeout.setText("Read Timout(ms)");
        jCheckBoxReadTimeout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxReadTimeoutActionPerformed(evt);
            }
        });

        jTextFieldReadTimeout.setEditable(false);
        jTextFieldReadTimeout.setEnabled(false);
        jTextFieldReadTimeout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldReadTimeoutActionPerformed(evt);
            }
        });

        jTextAreaPerfInfo.setColumns(20);
        jTextAreaPerfInfo.setRows(5);
        jScrollPane1.setViewportView(jTextAreaPerfInfo);

        jButtonShowPerfInfo.setText("Show Perf Info");
        jButtonShowPerfInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowPerfInfoActionPerformed(evt);
            }
        });

        jLabel21.setText("Wait For Done Delay(ms):");

        jTextFieldWaitForDoneDelay.setText("100");
        jTextFieldWaitForDoneDelay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldWaitForDoneDelayActionPerformed(evt);
            }
        });

        jCheckBoxIgnoreWaitForDoneTimeouts.setText("Ingnore Wait for Done Timeouts");
        jCheckBoxIgnoreWaitForDoneTimeouts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxIgnoreWaitForDoneTimeoutsActionPerformed(evt);
            }
        });

        jLabel4.setText("Last Get Status Sent Command");

        jTextAreaLastStopCommand.setColumns(20);
        jTextAreaLastStopCommand.setRows(5);
        jScrollPane8.setViewportView(jTextAreaLastStopCommand);

        jLabel22.setText("Last  Sent Stop Command");

        jTextAreaLastGetStatusCommand.setColumns(20);
        jTextAreaLastGetStatusCommand.setRows(5);
        jScrollPane9.setViewportView(jTextAreaLastGetStatusCommand);

        jTextAreaLastOtherCommand.setColumns(20);
        jTextAreaLastOtherCommand.setRows(5);
        jScrollPane10.setViewportView(jTextAreaLastOtherCommand);

        jLabel23.setText("Last  Sent Other Command");

        javax.swing.GroupLayout jPanelConnectionTabLayout = new javax.swing.GroupLayout(jPanelConnectionTab);
        jPanelConnectionTab.setLayout(jPanelConnectionTabLayout);
        jPanelConnectionTabLayout.setHorizontalGroup(
            jPanelConnectionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConnectionTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConnectionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9)
                    .addComponent(jScrollPane10)
                    .addGroup(jPanelConnectionTabLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanelConnectionTabLayout.createSequentialGroup()
                        .addGroup(jPanelConnectionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelConnectionTabLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldHost, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelConnectionTabLayout.createSequentialGroup()
                                .addGroup(jPanelConnectionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelConnectionTabLayout.createSequentialGroup()
                                        .addGroup(jPanelConnectionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelConnectionTabLayout.createSequentialGroup()
                                                .addComponent(jCheckBoxReadTimeout)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldReadTimeout))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelConnectionTabLayout.createSequentialGroup()
                                                .addComponent(jCheckBoxPoll)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldPollTime, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanelConnectionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelConnectionTabLayout.createSequentialGroup()
                                                .addComponent(jLabel21)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldWaitForDoneDelay))
                                            .addGroup(jPanelConnectionTabLayout.createSequentialGroup()
                                                .addComponent(jCheckBoxIgnoreWaitForDoneTimeouts)
                                                .addGap(0, 15, Short.MAX_VALUE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelConnectionTabLayout.createSequentialGroup()
                                        .addComponent(jButtonConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonDisconnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonInit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonEnd)))
                        .addContainerGap(153, Short.MAX_VALUE))
                    .addGroup(jPanelConnectionTabLayout.createSequentialGroup()
                        .addGroup(jPanelConnectionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jButtonShowPerfInfo))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelConnectionTabLayout.createSequentialGroup()
                        .addComponent(jScrollPane8)
                        .addContainerGap())))
        );
        jPanelConnectionTabLayout.setVerticalGroup(
            jPanelConnectionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConnectionTabLayout.createSequentialGroup()
                .addGroup(jPanelConnectionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConnectionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConnect)
                    .addComponent(jButtonDisconnect)
                    .addComponent(jButtonInit)
                    .addComponent(jButtonEnd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConnectionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxPoll)
                    .addComponent(jTextFieldPollTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jTextFieldWaitForDoneDelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConnectionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxReadTimeout)
                    .addComponent(jTextFieldReadTimeout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxIgnoreWaitForDoneTimeouts))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonShowPerfInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPaneLeft.addTab("Connection", jPanelConnectionTab);

        jTextAreaErrors.setColumns(20);
        jTextAreaErrors.setLineWrap(true);
        jTextAreaErrors.setRows(3);
        jTextAreaErrors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextAreaErrorsMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaErrorsMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextAreaErrorsMouseReleased(evt);
            }
        });
        jScrollPaneErrors.setViewportView(jTextAreaErrors);

        jTabbedPaneLeft.addTab("Errors", jScrollPaneErrors);

        jPanelStatus.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanelStatus.setName("Status"); // NOI18N

        jLabel3.setText("Command ID:");

        jTextFieldStatCmdID.setEditable(false);
        jTextFieldStatCmdID.setText("0");

        jTableJoints.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), null, null, null},
                { new Integer(2), null, null, null},
                { new Integer(3), null, null, null},
                { new Integer(4), null, null, null},
                { new Integer(5), null, null, null},
                { new Integer(6), null, null, null}
            },
            new String [] {
                "Joint", "Position", "Velocity", "TorqueOrForce"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableJoints);

        jLabel6.setText("Status : ");

        jTablePose.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"X", null},
                {"Y", null},
                {"Z", null},
                {"XI", null},
                {"XJ", null},
                {"XK", null},
                {"ZI", null},
                {"ZJ", null},
                {"Zk", null}
            },
            new String [] {
                "Pose Axis", "Position"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTablePose);

        jTextFieldStatusID.setText("0");

        jLabel10.setText("Status ID:");

        jLabel18.setText("State Description: .....................");

        jTextAreaStateDescription.setColumns(20);
        jTextAreaStateDescription.setFont(new java.awt.Font("Monospaced", 0, 10)); // NOI18N
        jTextAreaStateDescription.setRows(2);
        jScrollPane6.setViewportView(jTextAreaStateDescription);

        jComboBoxPoseDisplayMode.setModel(new javax.swing.DefaultComboBoxModel(PoseDisplayMode.values()));
        jComboBoxPoseDisplayMode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxPoseDisplayModeItemStateChanged(evt);
            }
        });
        jComboBoxPoseDisplayMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPoseDisplayModeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelStatusLayout = new javax.swing.GroupLayout(jPanelStatus);
        jPanelStatus.setLayout(jPanelStatusLayout);
        jPanelStatusLayout.setHorizontalGroup(
            jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatusLayout.createSequentialGroup()
                .addGroup(jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelStatusLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane6)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(jPanelStatusLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelStatusLayout.createSequentialGroup()
                                .addGroup(jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldStatusID)
                                    .addComponent(jTextFieldStatus)))
                            .addGroup(jPanelStatusLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldStatCmdID))
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10))
                    .addGroup(jPanelStatusLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBoxPoseDisplayMode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelStatusLayout.setVerticalGroup(
            jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldStatCmdID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldStatusID, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxPoseDisplayMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPaneRight.addTab("Status", jPanelStatus);

        jPanelProgramPlot.setName("ProgramPlot"); // NOI18N

        jPanelOverheadProgramPlot.setBorder(javax.swing.BorderFactory.createTitledBorder("Overhead"));

        javax.swing.GroupLayout programPlotterJPanelOverheadLayout = new javax.swing.GroupLayout(programPlotterJPanelOverhead);
        programPlotterJPanelOverhead.setLayout(programPlotterJPanelOverheadLayout);
        programPlotterJPanelOverheadLayout.setHorizontalGroup(
            programPlotterJPanelOverheadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 143, Short.MAX_VALUE)
        );
        programPlotterJPanelOverheadLayout.setVerticalGroup(
            programPlotterJPanelOverheadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 237, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelOverheadProgramPlotLayout = new javax.swing.GroupLayout(jPanelOverheadProgramPlot);
        jPanelOverheadProgramPlot.setLayout(jPanelOverheadProgramPlotLayout);
        jPanelOverheadProgramPlotLayout.setHorizontalGroup(
            jPanelOverheadProgramPlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOverheadProgramPlotLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(programPlotterJPanelOverhead, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelOverheadProgramPlotLayout.setVerticalGroup(
            jPanelOverheadProgramPlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOverheadProgramPlotLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(programPlotterJPanelOverhead, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelOverheadProgramPlot1.setBorder(javax.swing.BorderFactory.createTitledBorder("Side"));

        javax.swing.GroupLayout programPlotterJPanelSideLayout = new javax.swing.GroupLayout(programPlotterJPanelSide);
        programPlotterJPanelSide.setLayout(programPlotterJPanelSideLayout);
        programPlotterJPanelSideLayout.setHorizontalGroup(
            programPlotterJPanelSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        programPlotterJPanelSideLayout.setVerticalGroup(
            programPlotterJPanelSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 237, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelOverheadProgramPlot1Layout = new javax.swing.GroupLayout(jPanelOverheadProgramPlot1);
        jPanelOverheadProgramPlot1.setLayout(jPanelOverheadProgramPlot1Layout);
        jPanelOverheadProgramPlot1Layout.setHorizontalGroup(
            jPanelOverheadProgramPlot1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOverheadProgramPlot1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(programPlotterJPanelSide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelOverheadProgramPlot1Layout.setVerticalGroup(
            jPanelOverheadProgramPlot1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOverheadProgramPlot1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(programPlotterJPanelSide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelProgramPlotLayout = new javax.swing.GroupLayout(jPanelProgramPlot);
        jPanelProgramPlot.setLayout(jPanelProgramPlotLayout);
        jPanelProgramPlotLayout.setHorizontalGroup(
            jPanelProgramPlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProgramPlotLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelProgramPlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelOverheadProgramPlot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelOverheadProgramPlot1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelProgramPlotLayout.setVerticalGroup(
            jPanelProgramPlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProgramPlotLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelOverheadProgramPlot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelOverheadProgramPlot1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneRight.addTab("Program Plot", jPanelProgramPlot);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneLeft)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPaneRight, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPaneRight)
                    .addComponent(jTabbedPaneLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonProgramPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProgramPauseActionPerformed
        pauseCrclProgram();
    }//GEN-LAST:event_jButtonProgramPauseActionPerformed

    public XFutureVoid abortProgram() {
        pauseTime = System.currentTimeMillis();
        stopPollTimer();
        return internal.abort()
                .thenRun(() -> javax.swing.SwingUtilities.invokeLater(this::enableRunButtons));
    }

    private void jButtonProgramAbortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProgramAbortActionPerformed
        this.abortProgram();
    }//GEN-LAST:event_jButtonProgramAbortActionPerformed

    private void jButtonEditProgramItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditProgramItemActionPerformed
        int index = getProgramRow();
        if (index > 0 && index < this.jTableProgram.getRowCount() - 1) {
            try {
                final CRCLProgramType program = internal.getProgram();
                if (null == program) {
                    return;
                }
                final List<MiddleCommandType> middleCommandsList
                        = middleCommands(program);
                MiddleCommandType cmdOrig = middleCommandsList.get(index - 1);
                CRCLSocket editCrclSocket = CRCLSocket.getUtilSocket();
                MiddleCommandType cmdEdited
                        = (MiddleCommandType) ObjTableJPanel.editObject(cmdOrig,
                                internal.getXpu(),
                                internal.getCmdSchemaFiles(),
                                CrclSwingClientJPanel.this.internal.getCheckCommandValidPredicate(),
                                editCrclSocket);
                if (null == cmdEdited) {
                    showDebugMessage("Edit Program Item cancelled. cmdEdited == null");
                    return;
                }
                middleCommandsList.set(index - 1, cmdEdited);
                this.showProgram(program, null, -1);
                this.showCurrentProgramLine(index, program, internal.getStatus(), internal.getProgRunDataList());
            } catch (Exception ex) {
                Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonEditProgramItemActionPerformed

    @SuppressWarnings("rawtypes")
    private static DefaultTableModel getCommandStatusLogModel() {
        return new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                CrclSwingClientInner.COMMAND_STATUS_LOG_HEADINGS
        ) {
            Class[] types = CrclSwingClientInner.COMMAND_STATUS_LOG_TYPES;
//            boolean[] canEdit = CrclSwingClientInner.COMMAND_STATUS_LOG_CAN_EDITS;

            public Class getColumnClass(int columnIndex) {
                if (types.length != CrclSwingClientInner.COMMAND_STATUS_LOG_HEADINGS.length) {
                    System.out.println("types.length=" + types.length);
                    System.out.println("CrclSwingClientInner.COMMAND_STATUS_LOG_HEADINGS.length = " + CrclSwingClientInner.COMMAND_STATUS_LOG_HEADINGS.length);
                }
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
    }


    private void jButtonDeletProgramItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeletProgramItemActionPerformed
        int index = getProgramRow();
        if (index > 0 && index < this.jTableProgram.getRowCount() - 1) {
            try {
                final CRCLProgramType program = internal.getProgram();
                if (null == program) {
                    return;
                }
                final List<MiddleCommandType> middleCommandsList
                        = middleCommands(program);
                middleCommandsList.remove(index - 1);
                internal.getProgRunDataList().clear();
                this.showProgram(program, Collections.emptyList(), -1);
                this.showCurrentProgramLine(index, program, internal.getStatus(), internal.getProgRunDataList());
            } catch (Exception ex) {
                Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonDeletProgramItemActionPerformed

    private void jButtonAddProgramItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddProgramItemActionPerformed
        try {
            addProgramItem();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            showMessage(ex);
        }
    }//GEN-LAST:event_jButtonAddProgramItemActionPerformed

    private final List<String> customExcludedPathStrings = new ArrayList<>();

    @SuppressWarnings("rawtypes")
    private void addProgramItem() throws SecurityException, InvocationTargetException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        int index = getProgramRow();
        if (index > 0 && index < this.jTableProgram.getRowCount() - 1) {
            Class<?> clss = MiddleCommandType.class;
            List<Class<?>> availClasses = getAssignableClasses(clss,
                    ObjTableJPanel.getClasses(customExcludedPathStrings));
            Class ca[] = availClasses.toArray(new Class[availClasses.size()]);
            Arrays.sort(ca, Comparator.comparing((Class clss_in_ca) -> clss_in_ca.getSimpleName()));
            final Window outerWindow = this.getOuterWindow();
            if (null == outerWindow) {
                return;
            }
            Class<?> selectedClss = ListChooserJPanel.choose(outerWindow,
                    "Type of new Item",
                    ca, null);
            if (selectedClss == null) {
                showDebugMessage("Add Program Item cancelled. selectedClss == null");
                return;
            }
            MiddleCommandType cmdOrig = (MiddleCommandType) selectedClss.getDeclaredConstructor().newInstance();

            CRCLSocket editCrclSocket = CRCLSocket.getUtilSocket();;
            MiddleCommandType cmdEdited
                    = (MiddleCommandType) ObjTableJPanel.editObject(cmdOrig,
                            internal.getXpu(),
                            internal.getCmdSchemaFiles(),
                            CrclSwingClientJPanel.this.internal.getCheckCommandValidPredicate(),
                            editCrclSocket);
            if (null == cmdEdited) {
                showDebugMessage("Add Program Item cancelled. cmdEdited == null");
                return;
            }
            CRCLProgramType program = internal.getProgram();
            if (program == null) {
                program = new CRCLProgramType();
                program.setInitCanon(new InitCanonType());
                program.setEndCanon(new EndCanonType());
                internal.setProgram(program);
            }
            final List<MiddleCommandType> middleCommandsList
                    = middleCommands(program);
            middleCommandsList.add(index - 1, cmdEdited);
            internal.getProgRunDataList().clear();
            this.showProgram(program, Collections.emptyList(), -1);
            this.showCurrentProgramLine(index, program, internal.getStatus(), internal.getProgRunDataList());
        }
    }

    private void jButtonProgramRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProgramRunActionPerformed
        disableRunButtons();
        if (internal.isPaused()) {
            resumeButtonAction();
        }
        internal.setLastProgramIndex(-1);
        runCurrentProgramAsync(jCheckBoxStepping.isSelected(), true)
                .thenRun(() -> javax.swing.SwingUtilities.invokeLater(this::enableRunButtons));
    }//GEN-LAST:event_jButtonProgramRunActionPerformed

    public StackTraceElement @Nullable [] getRunProgramReturnFalseTrace() {
        return internal.getRunProgramReturnFalseTrace();
    }

    private @Nullable
    XFuture<Boolean> lastProgramFuture = null;

    public boolean runCurrentProgram(boolean stepMode) {
        if (!internal.isConnected()) {
            printDisconnectInfo();
            throw new RuntimeException("runCurrentProgram !internal.isConnected()");
        }
        if (disconnecting) {
            printDisconnectInfo();
            throw new RuntimeException("runCurrentProgram.disconnecting");
        }
        prepRunCurrentProgram(stepMode);
        if (disconnecting) {
            printDisconnectInfo();
            throw new RuntimeException("runCurrentProgram.disconnecting");
        }
        final CRCLProgramType program = internal.getProgram();
        if (null == program) {
            throw new IllegalStateException("null == program");
        }
        return internal.runProgram(program, 0);
    }

    private void printDisconnectInfo() {
        System.out.println();
        System.out.flush();
        System.err.println("printDisconnectInfo: loadedPrefsFile=" + loadedPrefsFile);
        System.err.println("printDisconnectInfo: loadPrefsThread=" + loadPrefsThread);
        System.err.println("printDisconnectInfo: loadPrefsTrace=" + XFuture.traceToString(loadPrefsTrace));
        System.err.println("printDisconnectInfo: finishConnectTrace=" + XFuture.traceToString(finishConnectTrace));
        System.err.println("printDisconnectInfo: finishDisconnectTrace=" + XFuture.traceToString(finishDisconnectTrace));
        System.err.println("printDisconnectInfo: internal.internalConnectInfo() = " + internal.internalConnectInfo());
        System.err.println("printDisconnectInfo: Thread.currentThread() = " + Thread.currentThread());
        System.err.println("printDisconnectInfo: disconnectThread = " + disconnectThread);
        System.err.println("printDisconnectInfo: disconnectTrace = " + XFuture.traceToString(disconnectTrace));
        System.err.println();
        System.err.flush();
    }

    public XFuture<Boolean> runCurrentProgramAsync(boolean stepMode) {
        return runCurrentProgramAsync(stepMode, false);
    }

    public XFuture<Boolean> runCurrentProgramAsync(boolean stepMode, boolean interactive) {
        try {
            if (disconnecting) {
                System.err.println("disconnectThread = " + disconnectThread);
                System.err.println("disconnectTrace = " + XFuture.traceToString(disconnectTrace));
                throw new RuntimeException("runCurrentProgramAsync.disconnecting");
            }
            prepRunCurrentProgram(stepMode);
            if (disconnecting) {
                System.err.println("disconnectThread = " + disconnectThread);
                System.err.println("disconnectTrace = " + XFuture.traceToString(disconnectTrace));
                throw new RuntimeException("runCurrentProgramAsync.disconnecting");
            }
            XFuture<Boolean> future = internal.startRunProgramThread(0, interactive);
            XFuture<Boolean> ret = checkFutureChange(future);
            lastProgramFuture = future;
            return ret;
        } catch (Exception ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            XFuture<Boolean> future = new XFuture<>("runCurrentProgram immediate exception");
            future.completeExceptionally(ex);
            lastProgramFuture = null;
            return future;
        }
    }

    private volatile boolean needInitPoint = false;

    private void prepRunCurrentProgram(boolean stepMode) {
        try {
            boolean startAsConnected = isConnected();
            int startPollStopCount = pollStopCount.get();

            int startlocalPort = internal.getLocalPort();
            int startRemotePort = internal.getPort();
            if (!startAsConnected) {
                connectCurrent();
            }
            int step2localPort = internal.getLocalPort();
            int step2RemotePort = internal.getPort();

            stopPollTimer();
            this.jCheckBoxPoll.setSelected(false);

            this.clearProgramTimesDistances();
            int new_poll_ms = Integer.parseInt(this.jTextFieldPollTime.getText());
            internal.setQuitOnTestCommandFailure(true);
            internal.setPoll_ms(new_poll_ms);
            internal.setWaitForDoneDelay(Long.parseLong(jTextFieldWaitForDoneDelay.getText().trim()));
            setStepMode(stepMode);
            this.jButtonProgramResume.setEnabled(false);
            this.jButtonProgramPause.setEnabled(true);
            jogWorldTransSpeedsSet = false;
            jogWorldRotSpeedsSet = false;
        } catch (Exception exception) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, exception);
            if (exception instanceof RuntimeException) {
                throw (RuntimeException) exception;
            } else {
                throw new RuntimeException(exception);
            }
        }
    }

    public void requestAndReadStatus() {
        boolean wasPolling = polling;
        try {
            if (wasPolling) {
                stopPollTimer();
            }
            internal.scheduleReadAndRequestStatus().get(1000, TimeUnit.MILLISECONDS);
        } catch (Exception ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, "", ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        } finally {
            if (wasPolling && !disconnecting) {
                startPollTimer();
            }
        }
    }

    public XFuture<CRCLStatusType> newStatus() {
        XFuture<CRCLStatusType> f = internal.newStatus();
        if (!polling) {
            internal.scheduleReadAndRequestStatus();
        }
        return f;
    }

    public void setStepMode(boolean newStepMode) {
        internal.setStepMode(newStepMode);
        jCheckBoxStepping.setSelected(newStepMode);
    }

    public boolean isStepMode() {
        boolean stepMode = internal.isStepMode();
        if (jCheckBoxStepping.isSelected() != stepMode) {
            jCheckBoxStepping.setSelected(stepMode);
        }
        return stepMode;
    }

    private void jButtonProgramResumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProgramResumeActionPerformed
        resumeButtonAction();
    }//GEN-LAST:event_jButtonProgramResumeActionPerformed

    private void resumeButtonAction() {
        if (pauseTime > this.internal.getRunStartMillis()) {
            this.internal.runStartMillis += (System.currentTimeMillis() - pauseTime);
            pauseTime = -1;
        }
        internal.unpause();
        this.jButtonProgramResume.setEnabled(internal.isPaused());
        this.jButtonProgramPause.setEnabled(internal.isRunningProgram());
        jogWorldTransSpeedsSet = false;
        jogWorldRotSpeedsSet = false;
    }

    private void jButtonPlotProgramItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlotProgramItemActionPerformed
//        final int index = getProgramRow();
//        if (index > 0 && index < this.jTableProgram.getRowCount() - 1) {
//            MiddleCommandType cmdOrig = internal.getProgram().getMiddleCommand().get(index - 1);
//            BigInteger id = cmdOrig.getCommandID();
//            final List<AnnotatedPose> l
//                    = this.internal
//                    .getPoseList()
//                    .stream()
//                    .filter(x -> x.getLastCommandIdSent().compareTo(id) == 0)
//                    .collect(Collectors.toList());
//            com.github.wshackle.poselist3dplot.MainJFrame
//                    .showPoseList(l);
//        }
    }//GEN-LAST:event_jButtonPlotProgramItemActionPerformed

    private void enableRunButtons() {
        jButtonProgramRun.setEnabled(true);
        jButtonRunProgFromCurrentLine.setEnabled(true);
        jButtonStepFwd.setEnabled(true);
        jButtonStepBack.setEnabled(true);
        this.jButtonProgramResume.setEnabled(false);
        this.jButtonProgramPause.setEnabled(false);
    }

    private void jButtonRunProgFromCurrentLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRunProgFromCurrentLineActionPerformed
        disableRunButtons();
        if (internal.isPaused()) {
            resumeButtonAction();
        }
        continueCurrentProgram(jCheckBoxStepping.isSelected(), true)
                .thenRun(() -> javax.swing.SwingUtilities.invokeLater(this::enableRunButtons));
    }//GEN-LAST:event_jButtonRunProgFromCurrentLineActionPerformed

    public void disableRunButtons() {
        jButtonProgramRun.setEnabled(false);
        jButtonRunProgFromCurrentLine.setEnabled(false);
        jButtonStepFwd.setEnabled(false);
        jButtonStepBack.setEnabled(false);
        this.jButtonProgramResume.setEnabled(false);
        this.jButtonProgramPause.setEnabled(true);
    }

    public boolean isPaused() {
        return internal.isPaused();
    }

    public @Nullable
    String getLastRunningProgramTrueInfo() {
        return internal.getLastRunningProgramTrueInfo();
    }

    public boolean isRunningProgram() {
        return internal.isRunningProgram();
    }

    public boolean isBlockPrograms() {
        return internal.isBlockPrograms();
    }

    public int startBlockingPrograms() {
        try {
            setProgram(null);
        } catch (Exception ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return internal.startBlockingPrograms();
    }

    public void stopBlockingPrograms(int count) throws ConcurrentBlockProgramsException {
        internal.stopBlockingPrograms(count);
    }

    /**
     * Get the value of preClosing
     *
     * @return the value of preClosing
     */
    public boolean isPreClosing() {
        return internal.isPreClosing();
    }

    /**
     * Set the value of preClosing
     *
     * @param preClosing new value of preClosing
     */
    public void setPreClosing(boolean preClosing) {
        internal.setPreClosing(preClosing);
    }

    public boolean isIgnoreTimeouts() {
        boolean ret = internal.isIgnoreTimeouts();
        jCheckBoxIgnoreWaitForDoneTimeouts.setSelected(ret);
        return ret;
    }

    /**
     * Set the value of ignoreTimeouts
     *
     * @param ignoreTimeouts new value of ignoreTimeouts
     */
    public void setIgnoreTimeouts(boolean ignoreTimeouts) {
        jCheckBoxIgnoreWaitForDoneTimeouts.setSelected(ignoreTimeouts);
        internal.setIgnoreTimeouts(ignoreTimeouts);
    }

    private volatile @MonotonicNonNull
    XFuture<Boolean> programFutureInternal = null;
    private volatile @MonotonicNonNull
    XFuture<Boolean> lastContinueCurrentProgramRet = null;

    public XFuture<Boolean> continueCurrentProgram(boolean stepMode) {
        return continueCurrentProgram(stepMode, false);
    }

    public XFuture<Boolean> continueCurrentProgram(boolean stepMode, boolean interactive) {
        if (internal.isBlockPrograms()) {
            internal.printStartBlockingProgramInfo();
            internal.showErrorMessage("Block Programs");
            throw new IllegalStateException("Block Programs");
        }
        try {
            XFuture<Boolean> origProgramFutureInternal = programFutureInternal;
            if (null != origProgramFutureInternal) {
                System.out.println("origProgramFutureInternal.isDone() = " + origProgramFutureInternal.isDone());
                System.out.println("origProgramFutureInternal.isCancelled() = " + origProgramFutureInternal.isCancelled());
            }
            if (null != lastContinueCurrentProgramRet) {
                System.out.println("lastContinueCurrentProgramRet.isDone() = " + lastContinueCurrentProgramRet.isDone());
                System.out.println("lastContinueCurrentProgramRet.isCancelled() = " + lastContinueCurrentProgramRet.isCancelled());
            }
            if (!isConnected()) {
                connectCurrent();
            }
            final CRCLProgramType program = internal.getProgram();
            if (null == program
                    || getCurrentProgramLine() < 0
                    || getCurrentProgramLine() > (middleCommands(program).size() + 1)) {
                return XFuture.completedFuture(internal.getCommandState() != CommandStateEnumType.CRCL_ERROR);
            }
            if (pauseTime > this.internal.runStartMillis) {
                this.internal.runStartMillis += (System.currentTimeMillis() - pauseTime);
            }
            pauseTime = -1;
            if (this.getCurrentProgramLine() < 1) {
                this.internal.runStartMillis = System.currentTimeMillis();
            }

            this.internal.closeTestProgramThread();
            setStepMode(stepMode);
            if (internal.isPaused()) {
                internal.unpause();
            } else {
                System.out.println("internal.isPaused() = " + internal.isPaused());
            }

            XFuture<Boolean> newProgramFutureInternal
                    = internal.startRunProgramThread(this.getCurrentProgramLine(), interactive);
            XFuture<Boolean> ret = checkFutureChange(newProgramFutureInternal);
            programFutureInternal = newProgramFutureInternal;
//            latch.countDown();
            if (null != origProgramFutureInternal) {
                System.out.println("origProgramFutureInternal.isDone() = " + origProgramFutureInternal.isDone());
                System.out.println("origProgramFutureInternal.isCancelled() = " + origProgramFutureInternal.isCancelled());
            }
            if (null != lastContinueCurrentProgramRet) {
                System.out.println("lastContinueCurrentProgramRet.isDone() = " + lastContinueCurrentProgramRet.isDone());
                System.out.println("lastContinueCurrentProgramRet.isCancelled() = " + lastContinueCurrentProgramRet.isCancelled());
            }
            lastContinueCurrentProgramRet = ret;
            return ret;
        } catch (Exception ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            XFuture<Boolean> ret = (new XFuture<>("continueCurrentProgram.exception"));
            ret.completeExceptionally(ex);
            return ret;
        }
    }

    private XFuture<Boolean> checkFutureChange(XFuture<Boolean> newProgramFutureInternal) {
        XFuture<Boolean> ret;
        ret = newProgramFutureInternal
                .thenCompose("PendantClient.checkFutureChange",
                        x -> (null != programFutureInternal && newProgramFutureInternal != programFutureInternal) ? checkFutureChange(programFutureInternal) : XFuture.completedFuture(x));
        return ret;
    }

    private void jButtonStepBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStepBackActionPerformed
        disableRunButtons();
        setStepMode(true);
        internal.abort();
        int l = this.getCurrentProgramLine();
        if (l > 0) {
            l--;
        }
        disableRunButtons();
        internal.startRunProgramThread(l, true)
                .thenRun(() -> javax.swing.SwingUtilities.invokeLater(this::enableRunButtons));
    }//GEN-LAST:event_jButtonStepBackActionPerformed

    private void jButtonStepFwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStepFwdActionPerformed
        setStepMode(true);
        boolean wasPaused = internal.isPaused();
        boolean wasRunning = internal.isRunningProgram();
        if (wasPaused) {
            internal.unpause();
        }
        if (!wasRunning) {
            final int currentProgramLine = this.getCurrentProgramLine();
            final CRCLProgramType internalProgram = internal.getProgram();
            if (currentProgramLine >= 0
                    && null != internalProgram
                    && currentProgramLine < middleCommands(internalProgram).size()) {
                disableRunButtons();
                internal.setLastProgramIndex(currentProgramLine + 1);
                internal.startRunProgramThread(currentProgramLine + 1, true)
                        .thenRun(() -> javax.swing.SwingUtilities.invokeLater(this::enableRunButtons));
            }
        }
    }//GEN-LAST:event_jButtonStepFwdActionPerformed

    private void jLabelJointJogMinusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJointJogMinusMouseExited
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJointJogMinusMouseExited

    private void jLabelJointJogMinusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJointJogMinusMousePressed
        if (!internal.isInitSent() || !internal.isConnected()) {
            showNotJogReady();
            return;
        }
        this.jogJointStart(-1.0 * internal.getJointJogIncrement());
        this.jLabelJointJogMinus.setBackground(Color.BLACK);
        this.jLabelJointJogMinus.setForeground(Color.WHITE);
        this.jPanelJointJogMinus.setBackground(Color.BLACK);
        this.jLabelJointJogMinus.repaint();
        this.jPanelJointJogMinus.repaint();
    }//GEN-LAST:event_jLabelJointJogMinusMousePressed

    private void jLabelJointJogMinusMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJointJogMinusMouseReleased
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJointJogMinusMouseReleased

    private void jLabelJointJogPlusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJointJogPlusMouseExited
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJointJogPlusMouseExited

    private void jLabelJointJogPlusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJointJogPlusMousePressed

        if (!internal.isInitSent() || !internal.isConnected()) {
            showNotJogReady();
            return;
        }
        this.jogJointStart(+1.0 * internal.getJointJogIncrement());
        this.jLabelJointJogPlus.setBackground(Color.BLACK);
        this.jLabelJointJogPlus.setForeground(Color.WHITE);
        this.jPanelJointJogPlus.setBackground(Color.BLACK);
        this.jLabelJointJogPlus.repaint();
        this.jPanelJointJogPlus.repaint();
    }//GEN-LAST:event_jLabelJointJogPlusMousePressed

    private void jLabelJointJogPlusMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJointJogPlusMouseReleased
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJointJogPlusMouseReleased

    private void jLabelJogPlusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogPlusMouseExited
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJogPlusMouseExited

    private void jLabelJogPlusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogPlusMousePressed
        if (!internal.isInitSent() || !internal.isConnected()) {
            showNotJogReady();
            return;
        }
        this.jogWorldStart(+1.0);
        this.jLabelJogPlus.setBackground(Color.BLACK);
        this.jLabelJogPlus.setForeground(Color.WHITE);
        this.jPanelJogPlus.setBackground(Color.BLACK);
        this.jLabelJogPlus.repaint();
        this.jPanelJogPlus.repaint();
    }//GEN-LAST:event_jLabelJogPlusMousePressed

    private void jLabelJogPlusMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogPlusMouseReleased
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJogPlusMouseReleased

    private void jLabelJogMinusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogMinusMousePressed
        if (!internal.isInitSent() || !internal.isConnected()) {
            showNotJogReady();
            return;
        }
        this.jogWorldStart(-1.0);
        this.jLabelJogMinus.setBackground(Color.BLACK);
        this.jLabelJogMinus.setForeground(Color.WHITE);
        this.jPanelJogMinus.setBackground(Color.BLACK);
        this.jLabelJogMinus.repaint();
        this.jPanelJogMinus.repaint();
    }//GEN-LAST:event_jLabelJogMinusMousePressed

    private void jLabelJogMinusMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogMinusMouseReleased
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJogMinusMouseReleased

    private void jLabelJogMinusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJogMinusMouseExited
        this.commonJogStop();
    }//GEN-LAST:event_jLabelJogMinusMouseExited

    private void jTextFieldJointJogIncrementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldJointJogIncrementActionPerformed
        internal.setJointJogIncrement(Double.parseDouble(this.jTextFieldJointJogIncrement.getText()));
    }//GEN-LAST:event_jTextFieldJointJogIncrementActionPerformed

    private void jTextFieldXYZJogIncrementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldXYZJogIncrementActionPerformed
        internal.setXyzJogIncrement(Double.parseDouble(this.jTextFieldXYZJogIncrement.getText()));
    }//GEN-LAST:event_jTextFieldXYZJogIncrementActionPerformed

    private void jTextFieldRPYJogIncrementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRPYJogIncrementActionPerformed
        this.setRpyJogIncrement(Double.parseDouble(this.jTextFieldRPYJogIncrement.getText()));
    }//GEN-LAST:event_jTextFieldRPYJogIncrementActionPerformed

    private void jTextFieldJogIntervalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldJogIntervalActionPerformed
        internal.setJogInterval(Integer.parseInt(this.jTextFieldJogInterval.getText()));
    }//GEN-LAST:event_jTextFieldJogIntervalActionPerformed

    private void lengthUnitComboBoxLengthUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lengthUnitComboBoxLengthUnitActionPerformed
        try {
            SetLengthUnitsType setLengthUnitsCmd = new SetLengthUnitsType();
            setLengthUnitsCmd.setUnitName(this.lengthUnitComboBoxLengthUnit.getSelectedItem());
            this.updateLengthUnit(setLengthUnitsCmd.getUnitName());
            incAndSendCommandFromAwt(setLengthUnitsCmd);
        } catch (JAXBException ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lengthUnitComboBoxLengthUnitActionPerformed

    private void jTextFieldJointJogSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldJointJogSpeedActionPerformed
        internal.setJogJointSpeed(Double.parseDouble(this.jTextFieldJointJogSpeed.getText()));
    }//GEN-LAST:event_jTextFieldJointJogSpeedActionPerformed

    private void jTextFieldTransSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTransSpeedActionPerformed
        internal.setJogTransSpeed(Double.parseDouble(this.jTextFieldTransSpeed.getText()));
        jogWorldTransSpeedsSet = false;
    }//GEN-LAST:event_jTextFieldTransSpeedActionPerformed

    private void jTextFieldRotationSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRotationSpeedActionPerformed
        internal.setJogRotSpeed(Double.parseDouble(this.jTextFieldRotationSpeed.getText()));
        jogWorldRotSpeedsSet = false;
    }//GEN-LAST:event_jTextFieldRotationSpeedActionPerformed

    private void jButtonRecordPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecordPointActionPerformed
        this.recordCurrentPoint();
    }//GEN-LAST:event_jButtonRecordPointActionPerformed

    private void jButtonOpenGripperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenGripperActionPerformed
        try {
            SetEndEffectorType seeCmd = new SetEndEffectorType();
            seeCmd.setSetting(1.0);
            incAndSendCommandFromAwt(seeCmd);
            CRCLProgramType recProgram = this.recordPointsProgram;
            if (null != recProgram) {
                this.recordCurrentPoint();
                middleCommands(recProgram).add(seeCmd);
                internal.setProgram(recProgram);
                showProgram(recProgram, Collections.emptyList(), -1);
            }
        } catch (JAXBException ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonOpenGripperActionPerformed

    private void jButtonCloseGripperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseGripperActionPerformed
        try {
            SetEndEffectorType seeCmd = new SetEndEffectorType();
            seeCmd.setSetting(0.0);
            incAndSendCommandFromAwt(seeCmd);
            CRCLProgramType recProgram = this.recordPointsProgram;
            if (null != recProgram) {
                this.recordCurrentPoint();
                middleCommands(recProgram).add(seeCmd);
                internal.setProgram(recProgram);
                showProgram(recProgram, Collections.emptyList(), -1);
            }
        } catch (JAXBException ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonCloseGripperActionPerformed

    private PoseDisplayMode lastMoveToPoseDisplayMode = PoseDisplayMode.XYZ_XAXIS_ZAXIS;

    private void jButtonMoveToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMoveToActionPerformed
        try {
            MoveToType moveto = new MoveToType();
            PoseType p = tableToPose(this.jTableMoveToPose, lastMoveToPoseDisplayMode);
            moveto.setEndPosition(p);
            moveto.setMoveStraight(this.jCheckBoxStraight.isSelected());
            for (int i = 0; i < jTableMoveToGuard.getRowCount(); i++) {
                GuardType guard = new GuardType();
                final Object valueAtI0 = jTableMoveToGuard.getValueAt(i, 0);
                if (null == valueAtI0 || valueAtI0.toString().length() < 2) {
                    JOptionPane.showMessageDialog(this, "Need sensor ID in row " + i + " of Guard Table");
                    return;
                }
                guard.setSensorID(valueAtI0.toString());
                final Object subfieldObject = jTableMoveToGuard.getValueAt(i, 1);
                if (null != subfieldObject) {
                    String subfieldString = subfieldObject.toString();
                    if (subfieldString.length() > 0) {
                        guard.setSubField(subfieldString);
                    }
                }
                final Object limitObject = jTableMoveToGuard.getValueAt(i, 2);
                if (null != limitObject) {
                    if (limitObject instanceof Double) {
                        guard.setLimitValue((Double) limitObject);
                    } else {
                        String limitString = limitObject.toString();
                        if (limitString.length() > 0) {
                            guard.setLimitValue(Double.valueOf(limitString));
                        }
                    }
                }
                final Object typeObject = jTableMoveToGuard.getValueAt(i, 3);
                if (null != typeObject) {
                    if (typeObject instanceof GuardLimitEnumType) {
                        guard.setLimitType((GuardLimitEnumType) typeObject);
                    } else {
                        String typeString = typeObject.toString();
                        if (typeString.length() > 0) {
                            guard.setLimitType(GuardLimitEnumType.valueOf(typeString));
                        }
                    }
                }
                addGuard(moveto, guard);
            }
            incAndSendCommandFromAwt(moveto);

        } catch (Exception ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonMoveToActionPerformed

    private void addGuard(CRCLCommandType cmd, GuardType guard) {
        Objects.requireNonNull(
                Objects.requireNonNull(cmd, "cmd").getGuard(),
                "getGuard()")
                .add(guard);
    }

    public PoseDisplayMode getCurrentPoseDisplayMode() {
        return (PoseDisplayMode) jComboBoxPoseDisplayMode.getSelectedItem();
    }

    private void jButtonMoveToCurrentPoseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMoveToCurrentPoseActionPerformed
        lastMoveToPoseDisplayMode = (PoseDisplayMode) jComboBoxMoveToPoseDisplayMode.getSelectedItem();
        final PoseType pose = internal.currentStatusPose();
        if (null != pose) {
            updatePoseTable(pose, this.jTableMoveToPose, lastMoveToPoseDisplayMode);
        }
    }//GEN-LAST:event_jButtonMoveToCurrentPoseActionPerformed

    private void jButtonConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConnectActionPerformed
        connectCurrent();
    }//GEN-LAST:event_jButtonConnectActionPerformed

    public void connectCurrent() throws NumberFormatException {
        if (!isConnected()) {
            int port = Integer.parseInt(this.jTextFieldPort.getText());
            this.connect(this.jTextFieldHost.getText(),
                    port);
        }
    }

    private void jButtonDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDisconnectActionPerformed
        if (isRunningProgram()) {
            this.abortProgram()
                    .thenRun(this::disconnect);
        } else {
            this.disconnect();
        }
    }//GEN-LAST:event_jButtonDisconnectActionPerformed

    private void jButtonEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEndActionPerformed
        try {
            EndCanonType end = new EndCanonType();
            incAndSendCommandFromAwt(end);
        } catch (JAXBException ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonEndActionPerformed

    public XFuture<Boolean> incAndSendCommandFromAwt(CRCLCommandType cmd) throws JAXBException {
//        internal.clearLastIncCommandThread();
        return internal.scheduleIncAndSendCommand(cmd);
//        internal.clearLastIncCommandThread();
//        return ret;
    }


    private void jButtonInitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInitActionPerformed
        try {
            InitCanonType init = new InitCanonType();
            incAndSendCommandFromAwt(init);
            if (internal.isConnected() && jCheckBoxPoll.isSelected()) {
                enableJoggingControls();
            }
        } catch (JAXBException ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonInitActionPerformed

    private void jCheckBoxPollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPollActionPerformed
        this.stopPollTimer();
        if (this.jCheckBoxPoll.isSelected()) {
            jTextFieldPollTime.setEditable(false);
            jTextFieldPollTime.setEnabled(false);
            if (internal.isConnected()) {
                this.startPollTimer();
            }
        } else {
            jTextFieldPollTime.setEditable(true);
            jTextFieldPollTime.setEnabled(true);
        }
    }//GEN-LAST:event_jCheckBoxPollActionPerformed

    private void jTextFieldPollTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPollTimeActionPerformed

        if (!this.jCheckBoxPoll.isSelected()) {
            int new_poll_ms = Integer.parseInt(this.jTextFieldPollTime.getText());
            if (new_poll_ms < 10) {
                new_poll_ms = 10;
                this.jTextFieldPollTime.setText("10");
            }
            internal.setPoll_ms(new_poll_ms);
        }
    }//GEN-LAST:event_jTextFieldPollTimeActionPerformed

    private void jTabbedPaneLeftStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneLeftStateChanged
        jogWorldTransSpeedsSet = false;
        jogWorldRotSpeedsSet = false;
    }//GEN-LAST:event_jTabbedPaneLeftStateChanged

    private void jTextAreaErrorsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaErrorsMousePressed
        if (evt.isPopupTrigger()) {
            showErrorsPopup(evt);
        }
    }//GEN-LAST:event_jTextAreaErrorsMousePressed

    private void jTextAreaErrorsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaErrorsMouseReleased
        if (evt.isPopupTrigger()) {
            showErrorsPopup(evt);
        }
    }//GEN-LAST:event_jTextAreaErrorsMouseReleased

    private void jTextAreaErrorsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaErrorsMouseClicked
        if (evt.isPopupTrigger()) {
            showErrorsPopup(evt);
        }
    }//GEN-LAST:event_jTextAreaErrorsMouseClicked

    private boolean disableTextPopups = true;

    /**
     * Get the value of disableTextPopups
     *
     * @return the value of disableTextPopups
     */
    public boolean isDisableTextPopups() {
        return disableTextPopups;
    }

    /**
     * Set the value of disableTextPopups
     *
     * @param disableTextPopups new value of disableTextPopups
     */
    public void setDisableTextPopups(boolean disableTextPopups) {
        this.disableTextPopups = disableTextPopups;
    }

    /**
     * Set the value of enableDebugConnect
     *
     * @param enableDebugConnect should debugging connections be enabled.
     */
    public void setEnableDebugConnect(boolean enableDebugConnect) {
        internal.setDebugConnectDisconnect(enableDebugConnect);
    }


    private void jComboBoxPoseDisplayModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPoseDisplayModeActionPerformed
        updateDisplayMode(jTablePose, getCurrentPoseDisplayMode(), false);
        final PoseType pose = internal.currentStatusPose();
        if (null != pose) {
            updatePoseTable(pose, jTablePose, getCurrentPoseDisplayMode());
        }
    }//GEN-LAST:event_jComboBoxPoseDisplayModeActionPerformed

    private void jComboBoxPoseDisplayModeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxPoseDisplayModeItemStateChanged
        updateDisplayMode(jTablePose, getCurrentPoseDisplayMode(), false);
        final PoseType pose = internal.currentStatusPose();
        if (null != pose) {
            updatePoseTable(pose, jTablePose, getCurrentPoseDisplayMode());
        }
    }//GEN-LAST:event_jComboBoxPoseDisplayModeItemStateChanged

    private void jComboBoxMoveToPoseDisplayModeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxMoveToPoseDisplayModeItemStateChanged
        try {
            PoseDisplayMode newMoveToPoseDisplayMode = (PoseDisplayMode) jComboBoxMoveToPoseDisplayMode.getSelectedItem();
            if (newMoveToPoseDisplayMode != lastMoveToPoseDisplayMode) {
                PoseType origPose = tableToPose(jTableMoveToPose, lastMoveToPoseDisplayMode);
                updateDisplayMode(jTableMoveToPose, newMoveToPoseDisplayMode, true);
                lastMoveToPoseDisplayMode = newMoveToPoseDisplayMode;
                updatePoseTable(origPose, this.jTableMoveToPose, lastMoveToPoseDisplayMode);
            }
        } catch (Exception ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBoxMoveToPoseDisplayModeItemStateChanged

    private void jComboBoxMoveToPoseDisplayModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMoveToPoseDisplayModeActionPerformed
        try {
            PoseDisplayMode newMoveToPoseDisplayMode = (PoseDisplayMode) jComboBoxMoveToPoseDisplayMode.getSelectedItem();
            if (newMoveToPoseDisplayMode != lastMoveToPoseDisplayMode) {
                PoseType origPose = tableToPose(jTableMoveToPose, lastMoveToPoseDisplayMode);
                updateDisplayMode(jTableMoveToPose, newMoveToPoseDisplayMode, true);
                lastMoveToPoseDisplayMode = newMoveToPoseDisplayMode;
                updatePoseTable(origPose, this.jTableMoveToPose, lastMoveToPoseDisplayMode);
            }
        } catch (Exception ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBoxMoveToPoseDisplayModeActionPerformed

    private boolean pauseCommandStatusLog = true;
    private void jCheckBoxPauseCommandStatusLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPauseCommandStatusLogActionPerformed
        pauseCommandStatusLog = jCheckBoxPauseCommandStatusLog.isSelected();
    }//GEN-LAST:event_jCheckBoxPauseCommandStatusLogActionPerformed

    private void jTextFieldLogMaxLengthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLogMaxLengthActionPerformed
        internal.setMaxLogSize(Integer.parseInt(jTextFieldLogMaxLength.getText().trim()));
    }//GEN-LAST:event_jTextFieldLogMaxLengthActionPerformed

    private void jButtonShowCommandStatusLogFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowCommandStatusLogFileActionPerformed
        showCommandStatusLog();
    }//GEN-LAST:event_jButtonShowCommandStatusLogFileActionPerformed

    private void jCheckBoxLogCommandStatusToFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxLogCommandStatusToFileActionPerformed
        commandStatusLogFile = null;
        logCommandStatusToFile = jCheckBoxLogCommandStatusToFile.isSelected();
        ((DefaultTableModel) jTableCommandStatusLog.getModel()).setRowCount(0);
        jCheckBoxPauseCommandStatusLog.setSelected(true);
        pauseCommandStatusLog = true;
    }//GEN-LAST:event_jCheckBoxLogCommandStatusToFileActionPerformed

    private void jButtonOpenToolChangerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenToolChangerActionPerformed
        try {
            OpenToolChangerType otcCmd = new OpenToolChangerType();
            incAndSendCommandFromAwt(otcCmd);
            CRCLProgramType recProgram = this.recordPointsProgram;
            if (null != recProgram) {
                this.recordCurrentPoint();
                middleCommands(recProgram).add(otcCmd);
                internal.setProgram(recProgram);
                showProgram(recProgram, Collections.emptyList(), -1);
            }
        } catch (JAXBException ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonOpenToolChangerActionPerformed

    private void jButtonCloseToolChangerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseToolChangerActionPerformed
        try {
            CloseToolChangerType ctcCmd = new CloseToolChangerType();
            incAndSendCommandFromAwt(ctcCmd);
            CRCLProgramType recProgram = this.recordPointsProgram;
            if (null != recProgram) {
                this.recordCurrentPoint();
                middleCommands(recProgram).add(ctcCmd);
                internal.setProgram(recProgram);
                showProgram(recProgram, Collections.emptyList(), -1);
            }
        } catch (JAXBException ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonCloseToolChangerActionPerformed

    private void jCheckBoxSteppingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSteppingActionPerformed
        internal.setStepMode(jCheckBoxStepping.isSelected());
    }//GEN-LAST:event_jCheckBoxSteppingActionPerformed

    private void jButtonMoveToCurrentPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMoveToCurrentPointActionPerformed
        lastMoveToPoseDisplayMode = (PoseDisplayMode) jComboBoxMoveToPoseDisplayMode.getSelectedItem();
        final PointType point = internal.currentStatusPoint();
        if (null != point) {
            updatePointTable(point, (DefaultTableModel) this.jTableMoveToPose.getModel(), 0);
        }
    }//GEN-LAST:event_jButtonMoveToCurrentPointActionPerformed

    private void jButtonMoveToDownPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMoveToDownPositionActionPerformed
        try {
            lastMoveToPoseDisplayMode = (PoseDisplayMode) jComboBoxMoveToPoseDisplayMode.getSelectedItem();
            PoseType pose = pose(point(0, 0, 0), vector(1, 0, 0), vector(0, 0, -1));
            updatePoseTable(pose, this.jTableMoveToPose, lastMoveToPoseDisplayMode);
        } catch (Exception exception) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, "evt=" + evt, exception);
        }
    }//GEN-LAST:event_jButtonMoveToDownPositionActionPerformed

    private void jButtonAddGuardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddGuardActionPerformed
        ((DefaultTableModel) jTableMoveToGuard.getModel())
                .addRow(new Object[]{"sensorId", "subfield", 0.0, OVER_MAX});
        jButtonRemoveGuard.setEnabled(true);
    }//GEN-LAST:event_jButtonAddGuardActionPerformed

    @SuppressWarnings("nullness")
    private static final @NonNull
    GuardLimitEnumType OVER_MAX = GuardLimitEnumType.OVER_MAX;

    private void jButtonRemoveGuardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveGuardActionPerformed
        final DefaultTableModel tm = (DefaultTableModel) jTableMoveToGuard.getModel();
        final int count = tm.getRowCount();
        if (count > 0) {
            tm.setRowCount(count - 1);
        }
        if (tm.getRowCount() < 1) {
            jButtonRemoveGuard.setEnabled(false);
        }
    }//GEN-LAST:event_jButtonRemoveGuardActionPerformed

    private void jButtonPlotStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlotStatusActionPerformed
        try {
            plotterJFrame plotterFrame = new plotterJFrame();
            plotterFrame.LoadObjectsList("", internal.getTimeStampedStatusList());
            plotterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            plotterFrame.setVisible(true);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonPlotStatusActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        showCommandProfileMap();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBoxReadTimeoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxReadTimeoutActionPerformed
        final boolean selected = jCheckBoxReadTimeout.isSelected();
        updateTimeoutEnable(selected);
        internal.setUseReadSoTimeout(selected);
    }//GEN-LAST:event_jCheckBoxReadTimeoutActionPerformed

    private void updateTimeoutEnable(final boolean selected) {
        if (selected) {
            jTextFieldReadTimeout.setEnabled(true);
            jTextFieldReadTimeout.setEditable(true);
            jTextFieldReadTimeout.setText(Integer.toString(internal.getReadStatusSoTimeout()));
        } else {
            jTextFieldReadTimeout.setText("");
            jTextFieldReadTimeout.setEnabled(false);
            jTextFieldReadTimeout.setEditable(false);
        }
    }

    private void jTextFieldReadTimeoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldReadTimeoutActionPerformed
        internal.setReadStatusSoTimeout(Integer.parseInt(jTextFieldReadTimeout.getText().trim()));
    }//GEN-LAST:event_jTextFieldReadTimeoutActionPerformed

    private void jButtonShowPerfInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowPerfInfoActionPerformed
        String perfInfo = internal.getPerfInfoString("");
        System.out.println("perfInfo = " + perfInfo);
        jTextAreaPerfInfo.setText(perfInfo);
    }//GEN-LAST:event_jButtonShowPerfInfoActionPerformed

    private void jTextFieldWaitForDoneDelayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldWaitForDoneDelayActionPerformed
        internal.setWaitForDoneDelay(Long.parseLong(jTextFieldWaitForDoneDelay.getText().trim()));
    }//GEN-LAST:event_jTextFieldWaitForDoneDelayActionPerformed

    private void jCheckBoxIgnoreWaitForDoneTimeoutsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxIgnoreWaitForDoneTimeoutsActionPerformed
        internal.setIgnoreTimeouts(jCheckBoxIgnoreWaitForDoneTimeouts.isSelected());
    }//GEN-LAST:event_jCheckBoxIgnoreWaitForDoneTimeoutsActionPerformed

    private void jTabbedPaneLeftComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTabbedPaneLeftComponentResized
        int maxwidth = 0;
        int maxprefwidth = 0;
        int maxminwidth = 0;
        int maxmaxwidth = 0;
        String maxwidthTitle = "";
        String maxprefwidthTitle = "";
        String maxminwidthTitle = "";
        String maxmaxwidthTitle = "";
        System.out.println("evt = " + evt);
        System.out.println("getWidth() = " + getWidth());
        for (int i = 0; i < jTabbedPaneLeft.getComponentCount(); i++) {
            Component comp = jTabbedPaneLeft.getComponentAt(i);
            if (null == comp) {
                System.out.println("comp = " + comp);
                System.out.println("i = " + i);
                continue;
            }
            String titleI = jTabbedPaneLeft.getTitleAt(i);
            if (comp.getWidth() > maxwidth) {
                maxwidth = comp.getWidth();
                maxwidthTitle = titleI;
            }
            if (comp.getPreferredSize().width > maxprefwidth) {
                maxprefwidth = comp.getPreferredSize().width;
                maxprefwidthTitle = titleI;
            }
            if (comp.getMinimumSize().width > maxprefwidth) {
                maxminwidth = comp.getMinimumSize().width;
                maxminwidthTitle = titleI;
            }
            if (comp.getMaximumSize().width > maxprefwidth) {
                maxmaxwidth = comp.getMaximumSize().width;
                maxmaxwidthTitle = titleI;
            }
        }
        System.out.println("maxwidth = " + maxwidth);
        System.out.println("maxprefwidth = " + maxprefwidth);
        System.out.println("maxminwidth = " + maxminwidth);
        System.out.println("maxmaxwidth = " + maxmaxwidth);
        System.out.println("maxwidthTitle = " + maxwidthTitle);
        System.out.println("maxprefwidthTitle = " + maxprefwidthTitle);
        System.out.println("maxminwidthTitle = " + maxminwidthTitle);
        System.out.println("maxmaxwidthTitle = " + maxmaxwidthTitle);

    }//GEN-LAST:event_jTabbedPaneLeftComponentResized

    public void printPerfInfo(PrintStream ps, String prefix) {
        ps.print(internal.getPerfInfoString(prefix));
    }

    public @Nullable
    Thread getRunProgramThread() {
        return internal.getCrclSocketActionThread();
    }

    public @Nullable
    XFuture<Boolean> getRunProgramFuture() {
        return internal.getRunProgramFuture();
    }

    private volatile boolean logCommandStatusToFile = false;

    private static final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss.SSS");

    public static String getTimeString(long ms) {
        Date date = new Date(ms);
        return timeFormat.format(date);
    }

    public static void autoResizeTableColWidths(JTable table) {

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        int fullsize = 0;
        Container parent = table.getParent();
        if (null != parent) {
            fullsize = Math.max(parent.getPreferredSize().width, parent.getSize().width);
        }
        int sumWidths = 0;
        for (int i = 0; i < table.getColumnCount(); i++) {
            DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
            TableColumn col = colModel.getColumn(i);
            int width = 0;

            TableCellRenderer renderer = col.getHeaderRenderer();
            if (renderer == null) {
                renderer = table.getTableHeader().getDefaultRenderer();
            }
            Component headerComp = renderer.getTableCellRendererComponent(table, col.getHeaderValue(),
                    false, false, 0, i);
            width = Math.max(width, headerComp.getPreferredSize().width);
            for (int r = 0; r < table.getRowCount(); r++) {
                renderer = table.getCellRenderer(r, i);
                Component comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, i),
                        false, false, r, i);
                width = Math.max(width, comp.getPreferredSize().width);
            }
            if (i == table.getColumnCount() - 1) {
                if (width < fullsize - sumWidths) {
                    width = fullsize - sumWidths;
                }
            }
            col.setPreferredWidth(width + 2);
            sumWidths += width + 2;
        }
    }

    public void printCommandStatusLog() throws IOException {
        internal.printCommandStatusLog(System.out, false, true, internal.getCommandStatusLogHeadings(), 20);
    }

    public void printCommandStatusLog(Appendable appendable, boolean clearLog) throws IOException {
        internal.printCommandStatusLog(appendable, clearLog, true, internal.getCommandStatusLogHeadings(), 20);
    }

    public String commandStatusLogToString() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            internal.printCommandStatusLog(pw, false, true, internal.getCommandStatusLogHeadings(), 20);
        } catch (IOException ex) {
            Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sw.toString();
    }

    public Object @Nullable [] logElementToArray(CommandStatusLogElement el) {
        return internal.logElementToArray(el);
    }

    @Override
    public void updateCommandStatusLog(Deque<CommandStatusLogElement> log) {
        if (logCommandStatusToFile) {
            try {
                writeCommandStatusLogFile();
                return;
            } catch (IOException ex) {
                Logger.getLogger(CrclSwingClientJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!pauseCommandStatusLog && !log.isEmpty()) {
            javax.swing.SwingUtilities.invokeLater(() -> {
                if (!log.isEmpty() && !jCheckBoxPauseCommandStatusLog.isSelected()) {
                    DefaultTableModel tableModel = (DefaultTableModel) jTableCommandStatusLog.getModel();
                    while (!log.isEmpty()) {
                        while (tableModel.getRowCount() > internal.getMaxLogSize()) {
                            tableModel.removeRow(0);
                        }
                        CommandStatusLogElement el = log.pollFirst();
                        if (null != el) {
                            Object[] data = logElementToArray(el);
//                            if(data.length != tableModel.)
                            if (null != data) {
                                tableModel.addRow(data);
                            }
                        }
                    }
                    autoResizeTableColWidths(jTableCommandStatusLog);
                }
            });
        }
    }

    private static String comparableClassName(Class<?> clss) {
        String canonicalName = clss.getCanonicalName();
        if (null != canonicalName) {
            return canonicalName;
        }
        return clss.getName();
    }

    private volatile @Nullable
    JMenu lastCommandsMenuParent = null;

    public void addToCommandsMenu(JMenu commandsMenuParent) {
        CrclSwingClientJPanel pendantClientJPanel1 = this;
        this.lastCommandsMenuParent = commandsMenuParent;
        List<Class<?>> allClasses = ObjTableJPanel.getClasses(customExcludedPathStrings);
        List<Class<?>> cmdClasses = ObjTableJPanel.getAssignableClasses(CRCLCommandType.class,
                allClasses);
        Collections.sort(cmdClasses, Comparator.comparing(CrclSwingClientJPanel::comparableClassName));
        for (final Class<?> c : cmdClasses) {
            JMenuItem jmi = new JMenuItem(comparableClassName(c));
            jmi.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        CrclSwingClientInner internal = pendantClientJPanel1.getInternal();
                        CRCLCommandType cmd = (CRCLCommandType) c.getDeclaredConstructor().newInstance();
                        CRCLSocket editCrclSocket = CRCLSocket.getUtilSocket();
                        cmd
                                = ObjTableJPanel.editObject(
                                        cmd,
                                        internal.getXpu(),
                                        internal.getCmdSchemaFiles(),
                                        internal.getCheckCommandValidPredicate(),
                                        editCrclSocket);
                        if (null != cmd) {
                            cmd.setCommandID(0);
                            internal.scheduleIncAndSendCommand(cmd);
                            pendantClientJPanel1.saveRecentCommand(cmd);
                        }
                    } catch (Exception ex) {
                        LOGGER.log(Level.SEVERE, null, ex);
                    }
                }
            });
            jmi.setEnabled(isConnected());
            commandsMenuParent.add(jmi);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAddGuard;
    private javax.swing.JButton jButtonAddProgramItem;
    private javax.swing.JButton jButtonCloseGripper;
    private javax.swing.JButton jButtonCloseToolChanger;
    private javax.swing.JButton jButtonConnect;
    private javax.swing.JButton jButtonDeletProgramItem;
    private javax.swing.JButton jButtonDisconnect;
    private javax.swing.JButton jButtonEditProgramItem;
    private javax.swing.JButton jButtonEnd;
    private javax.swing.JButton jButtonInit;
    private javax.swing.JButton jButtonMoveTo;
    private javax.swing.JButton jButtonMoveToCurrentPoint;
    private javax.swing.JButton jButtonMoveToCurrentPose;
    private javax.swing.JButton jButtonMoveToDownPosition;
    private javax.swing.JButton jButtonOpenGripper;
    private javax.swing.JButton jButtonOpenToolChanger;
    private javax.swing.JButton jButtonPlotProgramItem;
    private javax.swing.JButton jButtonPlotStatus;
    private javax.swing.JButton jButtonProgramAbort;
    private javax.swing.JButton jButtonProgramPause;
    private javax.swing.JButton jButtonProgramResume;
    private javax.swing.JButton jButtonProgramRun;
    private javax.swing.JButton jButtonRecordPoint;
    private javax.swing.JButton jButtonRemoveGuard;
    private javax.swing.JButton jButtonRunProgFromCurrentLine;
    private javax.swing.JButton jButtonShowCommandStatusLogFile;
    private javax.swing.JButton jButtonShowPerfInfo;
    private javax.swing.JButton jButtonStepBack;
    private javax.swing.JButton jButtonStepFwd;
    private javax.swing.JCheckBox jCheckBoxIgnoreWaitForDoneTimeouts;
    private javax.swing.JCheckBox jCheckBoxLogCommandStatusToFile;
    private javax.swing.JCheckBox jCheckBoxMonitorHoldingOutput;
    private javax.swing.JCheckBox jCheckBoxPauseCommandStatusLog;
    private javax.swing.JCheckBox jCheckBoxPoll;
    private javax.swing.JCheckBox jCheckBoxReadTimeout;
    private javax.swing.JCheckBox jCheckBoxStepping;
    private javax.swing.JCheckBox jCheckBoxStraight;
    private javax.swing.JCheckBox jCheckBoxWaitForPrevJogMoveDone;
    private javax.swing.JComboBox<String> jComboBoxJointAxis;
    private javax.swing.JComboBox<PoseDisplayMode> jComboBoxMoveToPoseDisplayMode;
    private javax.swing.JComboBox<PoseDisplayMode> jComboBoxPoseDisplayMode;
    private javax.swing.JComboBox<String> jComboBoxXYZRPY;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelExpectHoldingObject;
    private javax.swing.JLabel jLabelHoldingObject;
    private javax.swing.JLabel jLabelHoldingObject2;
    private javax.swing.JLabel jLabelJogMinus;
    private javax.swing.JLabel jLabelJogPlus;
    private javax.swing.JLabel jLabelJointJogMinus;
    private javax.swing.JLabel jLabelJointJogPlus;
    private javax.swing.JPanel jPanelCommandStatusLogOuter;
    private javax.swing.JPanel jPanelConnectionTab;
    private javax.swing.JPanel jPanelJogMinus;
    private javax.swing.JPanel jPanelJogPlus;
    private javax.swing.JPanel jPanelJogging;
    private javax.swing.JPanel jPanelJointJogMinus;
    private javax.swing.JPanel jPanelJointJogPlus;
    private javax.swing.JPanel jPanelMoveTo;
    private javax.swing.JPanel jPanelOverheadProgramPlot;
    private javax.swing.JPanel jPanelOverheadProgramPlot1;
    private javax.swing.JPanel jPanelProgram;
    private javax.swing.JPanel jPanelProgramPlot;
    private javax.swing.JPanel jPanelStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JScrollPane jScrollPaneErrors;
    private javax.swing.JScrollPane jScrollPaneMoveToGuardTable;
    private javax.swing.JScrollPane jScrollPaneProgram;
    private javax.swing.JTabbedPane jTabbedPaneLeft;
    private javax.swing.JTabbedPane jTabbedPaneRight;
    private javax.swing.JTable jTableCommandStatusLog;
    private javax.swing.JTable jTableJoints;
    private javax.swing.JTable jTableMoveToGuard;
    private javax.swing.JTable jTableMoveToPose;
    private javax.swing.JTable jTablePose;
    private javax.swing.JTable jTableProgram;
    private javax.swing.JTextArea jTextAreaErrors;
    private javax.swing.JTextArea jTextAreaLastGetStatusCommand;
    private javax.swing.JTextArea jTextAreaLastOtherCommand;
    private javax.swing.JTextArea jTextAreaLastStopCommand;
    private javax.swing.JTextArea jTextAreaPerfInfo;
    private javax.swing.JTextArea jTextAreaSelectedProgramCommand;
    private javax.swing.JTextArea jTextAreaStateDescription;
    private javax.swing.JTextField jTextFieldDistToSelected;
    private javax.swing.JTextField jTextFieldHost;
    private javax.swing.JTextField jTextFieldJogInterval;
    private javax.swing.JTextField jTextFieldJointJogIncrement;
    private javax.swing.JTextField jTextFieldJointJogSpeed;
    private javax.swing.JTextField jTextFieldLogMaxLength;
    private javax.swing.JTextField jTextFieldPollTime;
    private javax.swing.JTextField jTextFieldPort;
    private javax.swing.JTextField jTextFieldRPYJogIncrement;
    private javax.swing.JTextField jTextFieldReadTimeout;
    private javax.swing.JTextField jTextFieldRotationSpeed;
    private javax.swing.JTextField jTextFieldRunTime;
    private javax.swing.JTextField jTextFieldStatCmdID;
    private javax.swing.JTextField jTextFieldStatus;
    private javax.swing.JTextField jTextFieldStatusID;
    private javax.swing.JTextField jTextFieldTransSpeed;
    private javax.swing.JTextField jTextFieldWaitForDoneDelay;
    private javax.swing.JTextField jTextFieldXYZJogIncrement;
    private crcl.ui.misc.LengthUnitComboBox lengthUnitComboBoxLengthUnit;
    private crcl.ui.misc.ProgramPlotterJPanel programPlotterJPanelOverhead;
    private crcl.ui.misc.ProgramPlotterJPanel programPlotterJPanelSide;
    private crcl.ui.misc.TransformJPanel transformJPanel1;
    // End of variables declaration//GEN-END:variables
}
