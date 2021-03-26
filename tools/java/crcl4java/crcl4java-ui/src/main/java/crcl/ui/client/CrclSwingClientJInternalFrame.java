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

import crcl.base.CRCLProgramType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStateEnumType;
import crcl.base.MiddleCommandType;
import crcl.base.PoseType;
import crcl.ui.ConcurrentBlockProgramsException;
import crcl.utils.XFuture;
import crcl.utils.XFutureVoid;
import crcl.ui.misc.PropertiesJPanel;
import crcl.ui.misc.TransformSetupJFrame;
import crcl.utils.CRCLException;
import crcl.utils.CRCLSocket;
import crcl.utils.CRCLUtils;
import crcl.utils.outer.interfaces.CommandStatusLogElement;
import crcl.utils.outer.interfaces.PendantClientMenuOuter;
import crcl.utils.outer.interfaces.PendantClientOuter;
import crcl.utils.outer.interfaces.ProgramRunData;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBException;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import rcs.posemath.PmCartesian;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class CrclSwingClientJInternalFrame
        extends javax.swing.JInternalFrame implements PendantClientOuter, PendantClientMenuOuter {

    /**
     * Creates new form PendantClientJInternalFrame
     */
    @SuppressWarnings("initialization")
    CrclSwingClientJInternalFrame() {
        this(null);
    }

    public CrclSwingClientJPanel getPendantClientJPanel1() {
        return pendantClientJPanel1;
    }

    public boolean checkPose(PoseType goalPose, boolean ignoreCartTran,boolean throwExceptions) {
        return pendantClientJPanel1.checkPose(goalPose, ignoreCartTran,throwExceptions);
    }

    /**
     * Creates new form PendantClientJInternalFrame
     *
     * @param parentJFrame parentJFrame
     */
    @SuppressWarnings("initialization")
    public CrclSwingClientJInternalFrame(@Nullable JFrame parentJFrame) {
        this.parentJFrame = parentJFrame;
        initComponents();
        init();
    }

    /**
     * Get the value of minLimit
     *
     * @return the value of minLimit
     */
    public PmCartesian getMinLimit() {
        return pendantClientJPanel1.getMinLimit();
    }

    /**
     * Set the value of minLimit
     *
     * @param minLimit new value of minLimit
     */
    public void setMinLimit(@Nullable PmCartesian minLimit) {
        pendantClientJPanel1.setMinLimit(minLimit);
    }

    /**
     * Get the value of maxLimit
     *
     * @return the value of maxLimit
     */
    public PmCartesian getMaxLimit() {
        return pendantClientJPanel1.getMaxLimit();
    }

    /**
     * Set the value of maxLimit
     *
     * @param maxLimit new value of maxLimit
     */
    public void setMaxLimit(@Nullable PmCartesian maxLimit) {
        pendantClientJPanel1.setMaxLimit(maxLimit);
    }

    public void setStepMode(boolean step) {
        pendantClientJPanel1.setStepMode(step);
    }

    public boolean isStepMode() {
        return pendantClientJPanel1.isStepMode();
    }

    public @Nullable
    String getCrclClientErrorMessage() {
        return pendantClientJPanel1.getCrclClientErrorMessage();
    }

    public boolean isPaused() {
        return pendantClientJPanel1.isPaused();
    }

    public @Nullable
    Thread getRunProgramThread() {
        return pendantClientJPanel1.getRunProgramThread();
    }

    public @Nullable
    XFuture<Boolean> getRunProgramFuture() {
        return pendantClientJPanel1.getRunProgramFuture();
    }

    public int getCurrentProgramLine() {
        return pendantClientJPanel1.getCurrentProgramLine();
    }

    public boolean isRunningProgram() {
        return pendantClientJPanel1.isRunningProgram();
    }

    public @Nullable
    String getLastRunningProgramTrueInfo() {
        return pendantClientJPanel1.getLastRunningProgramTrueInfo();
    }

    public boolean isBlockPrograms() {
        return pendantClientJPanel1.isBlockPrograms();
    }

    public int startBlockingPrograms() {
        return pendantClientJPanel1.startBlockingPrograms();
    }

    public void stopBlockingPrograms(int count) throws ConcurrentBlockProgramsException {
        pendantClientJPanel1.stopBlockingPrograms(count);
    }

    public boolean isIgnoreTimeouts() {
        return pendantClientJPanel1.isIgnoreTimeouts();
    }

    public @Nullable
    List<ProgramRunData> getLastProgRunDataList() {
        return pendantClientJPanel1.getLastProgRunDataList();
    }

    public void saveProgramRunDataListToCsv(File f, List<ProgramRunData> list) throws IOException {
        pendantClientJPanel1.saveProgramRunDataListToCsv(f, list);
    }

    public void saveLastProgramRunDataListToCsv(File f) throws IOException {
        pendantClientJPanel1.saveLastProgramRunDataListToCsv(f);
    }

    /**
     * Set the value of ignoreTimeouts
     *
     * @param ignoreTimeouts new value of ignoreTimeouts
     */
    public void setIgnoreTimeouts(boolean ignoreTimeouts) {
        pendantClientJPanel1.setIgnoreTimeouts(ignoreTimeouts);
    }

    public synchronized void disconnect() {
        pendantClientJPanel1.disconnect();
        if (pendantClientJPanel1.isConnected()) {
            throw new RuntimeException("still connected after disconnect");
        }
    }

    public void clearCrclClientErrorMessage() {
        pendantClientJPanel1.clearCrclClientErrorMessage();
    }

    public void pauseCrclProgram() {
        pendantClientJPanel1.pauseCrclProgram();
    }

    public String pauseInfoString() {
        return pendantClientJPanel1.pauseInfoString();
    }

    public void unpauseCrclProgram() {
        pendantClientJPanel1.unpauseCrclProgram();
    }

    public void connectCurrent() {
        pendantClientJPanel1.connectCurrent();
    }

    public void connect(String host, int port) {
        pendantClientJPanel1.connect(host, port);
    }

    private @MonotonicNonNull
    JFrame parentJFrame;

    public @Nullable
    JFrame findParentJFrame() {
        if (parentJFrame != null) {
            return parentJFrame;
        }
        Container parent = this.getParent();
        while (parent != null && !(parent instanceof JFrame)) {
            parentJFrame = (JFrame) parent;
            parent = parent.getParent();
        }
        return parentJFrame;
    }

    public void addUpdateTitleListener(UpdateTitleListener utl) {
        pendantClientJPanel1.addUpdateTitleListener(utl);
    }

    public void removeUpdateTitleListener(UpdateTitleListener utl) {
        pendantClientJPanel1.removeUpdateTitleListener(utl);
    }

    private void init() {
//        pendantClientJPanel1.setOuterContainer(this);
        pendantClientJPanel1.setMenuOuter(this);
        pendantClientJPanel1.addToCommandsMenu(jMenuCmds);

        readRecentCommandFiles();
        readRecentPrograms();
//        this.setIconImage(DISCONNECTED_IMAGE);
        super.setTitle("CRCL Client: Disconnected");
//        jCheckBoxMenuItemIgnoreTimeouts.setSelected(isIgnoreTimeouts());
        jCheckBoxMenuItemValidateXml.setSelected(pendantClientJPanel1.isValidateXmlSchema());

//        try {
//            this.setIconImage(IconImages.BASE_IMAGE);
//        } catch (Exception ex) {
//            Logger.getLogger(SimServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

//    private void updateUIFromInternal() {
//        pendantClientJPanel1.updateUIFromInternal();
//        this.jCheckBoxMenuItemQuitProgramOnTestCommandFail.setSelected(pendantClientJPanel1.getInternal().isQuitOnTestCommandFailure());
//    }

    private static final String recent_files_dir = ".crcl_pendant_client_recent_files";

    public void addProgramLineListener(ProgramLineListener l) {
        pendantClientJPanel1.addProgramLineListener(l);
    }

    public void removeProgramLineListener(ProgramLineListener l) {
        pendantClientJPanel1.removeProgramLineListener(l);
    }

    public void addCurrentPoseListener(CurrentPoseListener l) {
        pendantClientJPanel1.addCurrentPoseListener(l);
    }

    public void removeCurrentPoseListener(CurrentPoseListener l) {
        pendantClientJPanel1.removeCurrentPoseListener(l);
    }

    public String getLastMessage() {
        return pendantClientJPanel1.getLastMessage();
    }

    @Override
    public void setEnableSaveProgram(boolean enable) {
        jMenuItemSaveProgramAs.setEnabled(enable);
    }

    @Override
    public void readRecentCommandFiles() {
        File fMainDir = new File(CRCLUtils.getCrclUserHomeDir(),
                recent_files_dir);
        if (!fMainDir.exists()) {
            return;
        }
        File fa[] = fMainDir.listFiles(new java.io.FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() && pathname.canRead();
            }
        });
        if (null == fa || fa.length < 1) {
            return;
        }
        Arrays.sort(fa, new Comparator<File>() {

            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        this.jMenuCommandRecent.removeAll();
        for (File fSubDir : fa) {
            addClassSubmenu(fSubDir);
        }
    }

    private void addClassSubmenu(File fSubDir) {
        final String subdirName = fSubDir.getName();
        try {
            Class.forName("crcl.base." + subdirName);
        } catch (ClassNotFoundException ex) {
            System.out.println("ignoring subdir that doesn't match class " + subdirName);
            return;
        }
        JMenu jm = new JMenu(subdirName);

        this.jMenuCommandRecent.add(jm);
        File sub_fa[] = fSubDir.listFiles(new java.io.FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".xml");
            }
        });
        if (null != sub_fa) {
            Arrays.sort(sub_fa, Comparator.comparing(File::lastModified).reversed());
            for (int i = 0; i < sub_fa.length && i < 4; i++) {
                File xmlFile = sub_fa[i];
                JMenuItem jmi = new JMenuItem(xmlFile.getName());
                jmi.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            pendantClientJPanel1.openXmlInstanceFile(xmlFile);
                        } catch (Exception ex) {
                            LOGGER.log(Level.SEVERE, null, ex);
                            showMessage(ex);
                        }
                    }
                });
                jm.add(jmi);
            }
        }
    }

    private void readRecentPrograms() {
        Set<String> pathSet = pendantClientJPanel1.getRecentPrograms();
        this.jMenuRecentProgram.removeAll();
        for (final String fprogCanon : pathSet) {
            try {
                JMenuItem jmi = new JMenuItem(fprogCanon);
                jmi.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pendantClientJPanel1.openXmlProgramFile(new File(fprogCanon));
                    }
                });
                this.jMenuRecentProgram.add(jmi);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
    }

    public static final Logger LOGGER = Logger.getLogger(CrclSwingClientJInternalFrame.class.getName());

    @Override
    public void saveXmlProgramFile(File f) throws JAXBException, CRCLException {
        pendantClientJPanel1.saveXmlProgramFile(f);
    }

    private JFrame outerFrame;
    private boolean searchedForOuterFrame = false;

    private JFrame searchForOuterFrame() {
        if (searchedForOuterFrame) {
            return outerFrame;
        }
        searchedForOuterFrame = true;
        Container container = this;
        while (null != (container = container.getParent())) {
            if (container instanceof JFrame) {
                return (JFrame) container;
            }
        }
        throw new IllegalStateException("no containter parent is a JFrame");
    }

    /**
     * Get the value of outerFrame
     *
     * @return the value of outerFrame
     */
    public JFrame getOuterFrame() {
        if (null == outerFrame) {
            outerFrame = searchForOuterFrame();
        }
        return outerFrame;
    }

    /**
     * Set the value of outerFrame
     *
     * @param outerFrame new value of outerFrame
     */
    public void setOuterFrame(JFrame outerFrame) {
        this.outerFrame = outerFrame;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pendantClientJPanel1 = new crcl.ui.client.CrclSwingClientJPanel(this,parentJFrame);
        jMenuBarPendantClient = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemOpenXmlCommandInstance = new javax.swing.JMenuItem();
        jMenuCommandRecent = new javax.swing.JMenu();
        jMenuItemOpenXmlProgram = new javax.swing.JMenuItem();
        jMenuRecentProgram = new javax.swing.JMenu();
        jMenuItemSaveProgramAs = new javax.swing.JMenuItem();
        jMenuItemClearRecordedPoints = new javax.swing.JMenuItem();
        jMenuItemSavePoseList = new javax.swing.JMenuItem();
        jMenuItemSavePrefs = new javax.swing.JMenuItem();
        jMenuItemLoadPrefs = new javax.swing.JMenuItem();
        jMenuItemResetPrefs = new javax.swing.JMenuItem();
        jMenuItemViewLogFile = new javax.swing.JMenuItem();
        jMenuItemSaveStatusAs = new javax.swing.JMenuItem();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuTools = new javax.swing.JMenu();
        jMenuItemXPathQuery = new javax.swing.JMenuItem();
        jCheckBoxMenuItemPlotXYZ = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemPlotJoints = new javax.swing.JCheckBoxMenuItem();
        jMenuItemRunTest = new javax.swing.JMenuItem();
        jCheckBoxMenuItemRecordPoseList = new javax.swing.JCheckBoxMenuItem();
        jMenuItemPoseList3DPlot = new javax.swing.JMenuItem();
        jMenuItemOpenStatusLog = new javax.swing.JMenuItem();
        jMenuItemShowCommandLog = new javax.swing.JMenuItem();
        jMenuItemTransformProgram = new javax.swing.JMenuItem();
        jCheckBoxMenuItemRecordTriggers = new javax.swing.JCheckBoxMenuItem();
        jMenuCmds = new javax.swing.JMenu();
        jMenuXmlSchemas = new javax.swing.JMenu();
        jMenuItemSetSchemaFiles = new javax.swing.JMenuItem();
        jCheckBoxMenuItemValidateXml = new javax.swing.JCheckBoxMenuItem();
        jMenuOptions = new javax.swing.JMenu();
        jCheckBoxMenuItemDebugWaitForDone = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDebugSendCommand = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDebugReadStatus = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDebugInterrupts = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemEnableDebugConnect = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDebugShowProgram = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemUseEXI = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemRecordCommands = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemQuitProgramOnTestCommandFail = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDisableTextPopups = new javax.swing.JCheckBoxMenuItem();
        jMenuItemAbout = new javax.swing.JMenuItem();

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("CRCL Client");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pendantClientJPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pendantClientJPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItemOpenXmlCommandInstance.setText("Open CRCL XML Command Instance File ... ");
        jMenuItemOpenXmlCommandInstance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOpenXmlCommandInstanceActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemOpenXmlCommandInstance);

        jMenuCommandRecent.setText("Recent CRCL XML Command Instance Files");
        jMenu1.add(jMenuCommandRecent);

        jMenuItemOpenXmlProgram.setText("Open CRCL XML Program File ...");
        jMenuItemOpenXmlProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOpenXmlProgramActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemOpenXmlProgram);

        jMenuRecentProgram.setText("Recent CRCL XML Program File ...");
        jMenu1.add(jMenuRecentProgram);

        jMenuItemSaveProgramAs.setText("Save Recorded Points Program As ...");
        jMenuItemSaveProgramAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaveProgramAsActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSaveProgramAs);

        jMenuItemClearRecordedPoints.setText("Clear Recorded Points");
        jMenuItemClearRecordedPoints.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClearRecordedPointsActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemClearRecordedPoints);

        jMenuItemSavePoseList.setText("Save Pose List ...");
        jMenuItemSavePoseList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSavePoseListActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSavePoseList);

        jMenuItemSavePrefs.setText("Save Preferences File ...");
        jMenuItemSavePrefs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSavePrefsActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSavePrefs);

        jMenuItemLoadPrefs.setText("Load Preferences File ...");
        jMenuItemLoadPrefs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLoadPrefsActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemLoadPrefs);

        jMenuItemResetPrefs.setText("Reset Preferences");
        jMenuItemResetPrefs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemResetPrefsActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemResetPrefs);

        jMenuItemViewLogFile.setText("View Log File ");
        jMenuItemViewLogFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemViewLogFileActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemViewLogFile);

        jMenuItemSaveStatusAs.setText("Save Status As ...");
        jMenuItemSaveStatusAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaveStatusAsActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSaveStatusAs);

        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemExit);

        jMenuBarPendantClient.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBarPendantClient.add(jMenu2);

        jMenuTools.setText(" Tools ");

        jMenuItemXPathQuery.setText("XPath Status  Query ... ");
        jMenuItemXPathQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemXPathQueryActionPerformed(evt);
            }
        });
        jMenuTools.add(jMenuItemXPathQuery);

        jCheckBoxMenuItemPlotXYZ.setText("2D Plot XYZ vs Time ...");
        jCheckBoxMenuItemPlotXYZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemPlotXYZActionPerformed(evt);
            }
        });
        jMenuTools.add(jCheckBoxMenuItemPlotXYZ);

        jCheckBoxMenuItemPlotJoints.setText("Plot Joints");
        jCheckBoxMenuItemPlotJoints.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemPlotJointsActionPerformed(evt);
            }
        });
        jMenuTools.add(jCheckBoxMenuItemPlotJoints);

        jMenuItemRunTest.setText("Run Test");
        jMenuItemRunTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRunTestActionPerformed(evt);
            }
        });
        jMenuTools.add(jMenuItemRunTest);

        jCheckBoxMenuItemRecordPoseList.setText("Record Pose List");
        jMenuTools.add(jCheckBoxMenuItemRecordPoseList);

        jMenuItemPoseList3DPlot.setText("3D Pose List Plot ...");
        jMenuItemPoseList3DPlot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPoseList3DPlotActionPerformed(evt);
            }
        });
        jMenuTools.add(jMenuItemPoseList3DPlot);

        jMenuItemOpenStatusLog.setText("Open Status Log ...");
        jMenuItemOpenStatusLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOpenStatusLogActionPerformed(evt);
            }
        });
        jMenuTools.add(jMenuItemOpenStatusLog);

        jMenuItemShowCommandLog.setText("Show Command Log ...");
        jMenuItemShowCommandLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemShowCommandLogActionPerformed(evt);
            }
        });
        jMenuTools.add(jMenuItemShowCommandLog);

        jMenuItemTransformProgram.setText("Transform Program");
        jMenuItemTransformProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTransformProgramActionPerformed(evt);
            }
        });
        jMenuTools.add(jMenuItemTransformProgram);

        jCheckBoxMenuItemRecordTriggers.setSelected(true);
        jCheckBoxMenuItemRecordTriggers.setText("Record Triggers");
        jMenuTools.add(jCheckBoxMenuItemRecordTriggers);

        jMenuBarPendantClient.add(jMenuTools);

        jMenuCmds.setText("Commands");
        jMenuBarPendantClient.add(jMenuCmds);

        jMenuXmlSchemas.setText("XML Schemas");

        jMenuItemSetSchemaFiles.setText("Set XML Schema Files ... ");
        jMenuItemSetSchemaFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSetSchemaFilesActionPerformed(evt);
            }
        });
        jMenuXmlSchemas.add(jMenuItemSetSchemaFiles);

        jCheckBoxMenuItemValidateXml.setSelected(true);
        jCheckBoxMenuItemValidateXml.setText("Validate using Schemas");
        jCheckBoxMenuItemValidateXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemValidateXmlActionPerformed(evt);
            }
        });
        jMenuXmlSchemas.add(jCheckBoxMenuItemValidateXml);

        jMenuBarPendantClient.add(jMenuXmlSchemas);

        jMenuOptions.setText("Options");

        jCheckBoxMenuItemDebugWaitForDone.setText("Debug waitForDone()");
        jMenuOptions.add(jCheckBoxMenuItemDebugWaitForDone);

        jCheckBoxMenuItemDebugSendCommand.setText("Debug sendCommand()");
        jMenuOptions.add(jCheckBoxMenuItemDebugSendCommand);

        jCheckBoxMenuItemDebugReadStatus.setText("Debug  readStatus() ");
        jMenuOptions.add(jCheckBoxMenuItemDebugReadStatus);

        jCheckBoxMenuItemDebugInterrupts.setSelected(true);
        jCheckBoxMenuItemDebugInterrupts.setText("Debug Interrupts");
        jMenuOptions.add(jCheckBoxMenuItemDebugInterrupts);

        jCheckBoxMenuItemEnableDebugConnect.setText("Debug Connect/Disconnect");
        jCheckBoxMenuItemEnableDebugConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemEnableDebugConnectActionPerformed(evt);
            }
        });
        jMenuOptions.add(jCheckBoxMenuItemEnableDebugConnect);

        jCheckBoxMenuItemDebugShowProgram.setText("Debug showProgram");
        jCheckBoxMenuItemDebugShowProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDebugShowProgramActionPerformed(evt);
            }
        });
        jMenuOptions.add(jCheckBoxMenuItemDebugShowProgram);

        jCheckBoxMenuItemUseEXI.setText("USE EXI (Efficient XML Interchange)");
        jCheckBoxMenuItemUseEXI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemUseEXIActionPerformed(evt);
            }
        });
        jMenuOptions.add(jCheckBoxMenuItemUseEXI);

        jCheckBoxMenuItemRecordCommands.setText("Record Commands");
        jCheckBoxMenuItemRecordCommands.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemRecordCommandsActionPerformed(evt);
            }
        });
        jMenuOptions.add(jCheckBoxMenuItemRecordCommands);

        jCheckBoxMenuItemQuitProgramOnTestCommandFail.setSelected(true);
        jCheckBoxMenuItemQuitProgramOnTestCommandFail.setText("Quit Program on Test Command Fail");
        jCheckBoxMenuItemQuitProgramOnTestCommandFail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemQuitProgramOnTestCommandFailActionPerformed(evt);
            }
        });
        jMenuOptions.add(jCheckBoxMenuItemQuitProgramOnTestCommandFail);

        jCheckBoxMenuItemDisableTextPopups.setText("Disable Text Popups");
        jCheckBoxMenuItemDisableTextPopups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDisableTextPopupsActionPerformed(evt);
            }
        });
        jMenuOptions.add(jCheckBoxMenuItemDisableTextPopups);

        jMenuItemAbout.setText("About");
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });
        jMenuOptions.add(jMenuItemAbout);

        jMenuBarPendantClient.add(jMenuOptions);

        setJMenuBar(jMenuBarPendantClient);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemOpenXmlCommandInstanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOpenXmlCommandInstanceActionPerformed
        pendantClientJPanel1.browseOpenCommandXml();
    }//GEN-LAST:event_jMenuItemOpenXmlCommandInstanceActionPerformed

    private void jMenuItemOpenXmlProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOpenXmlProgramActionPerformed
        pendantClientJPanel1.browseOpenProgramXml();
    }//GEN-LAST:event_jMenuItemOpenXmlProgramActionPerformed

    private void jMenuItemSaveProgramAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaveProgramAsActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "XML Program Files", "xml");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File f = chooser.getSelectedFile();
                saveXmlProgramFile(f);
            } catch (JAXBException | CRCLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
                this.showMessage(ex);
            }
        }
    }//GEN-LAST:event_jMenuItemSaveProgramAsActionPerformed

    private void jMenuItemClearRecordedPointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClearRecordedPointsActionPerformed
        pendantClientJPanel1.clearRecordedPoints();
    }//GEN-LAST:event_jMenuItemClearRecordedPointsActionPerformed

    private void jMenuItemSavePoseListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSavePoseListActionPerformed
        pendantClientJPanel1.savePoseList();
    }//GEN-LAST:event_jMenuItemSavePoseListActionPerformed

    private void jMenuItemLoadPrefsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLoadPrefsActionPerformed
        pendantClientJPanel1.loadPrefsAction();
    }//GEN-LAST:event_jMenuItemLoadPrefsActionPerformed

    private void jMenuItemSavePrefsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSavePrefsActionPerformed
        pendantClientJPanel1.savePrefsAction();
    }//GEN-LAST:event_jMenuItemSavePrefsActionPerformed

    private void jMenuItemResetPrefsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemResetPrefsActionPerformed
        pendantClientJPanel1.resetPrefs();
    }//GEN-LAST:event_jMenuItemResetPrefsActionPerformed

    private void jMenuItemViewLogFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemViewLogFileActionPerformed
        pendantClientJPanel1.openLogFile();
    }//GEN-LAST:event_jMenuItemViewLogFileActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItemXPathQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemXPathQueryActionPerformed
        pendantClientJPanel1.showXpathQueryDialog();
    }//GEN-LAST:event_jMenuItemXPathQueryActionPerformed

    private void jCheckBoxMenuItemPlotXYZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemPlotXYZActionPerformed
//        if (this.jCheckBoxMenuItemPlotXYZ.isSelected()) {
//            pendantClientJPanel1.show
//        }
    }//GEN-LAST:event_jCheckBoxMenuItemPlotXYZActionPerformed

    private void jCheckBoxMenuItemPlotJointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemPlotJointsActionPerformed
        if (this.jCheckBoxMenuItemPlotJoints.isSelected()) {
            pendantClientJPanel1.showJointsPlot();
        }
    }//GEN-LAST:event_jCheckBoxMenuItemPlotJointsActionPerformed

    private void jMenuItemRunTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRunTestActionPerformed
        Map<String, String> testPropsMap
                = PropertiesJPanel.confirmPropertiesMap(this.getOuterFrame(), "Test Run Properties", true,
                        pendantClientJPanel1.getInternal().getDefaultTestPropertiesMap());
        if (null != testPropsMap) {
            pendantClientJPanel1.startRunTest(testPropsMap);
        }
    }//GEN-LAST:event_jMenuItemRunTestActionPerformed

    private void jMenuItemPoseList3DPlotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPoseList3DPlotActionPerformed
//        pendantClientJPanel1.show3DPlot();
    }//GEN-LAST:event_jMenuItemPoseList3DPlotActionPerformed

    private void jMenuItemOpenStatusLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOpenStatusLogActionPerformed
        pendantClientJPanel1.showStatusLog();
    }//GEN-LAST:event_jMenuItemOpenStatusLogActionPerformed

    private void jMenuItemShowCommandLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemShowCommandLogActionPerformed

        pendantClientJPanel1.showCommandLog();
    }//GEN-LAST:event_jMenuItemShowCommandLogActionPerformed

    private void jMenuItemTransformProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTransformProgramActionPerformed
        TransformSetupJFrame setupFrame = new TransformSetupJFrame();
        setupFrame.setPendantClient(this.pendantClientJPanel1);
        setupFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItemTransformProgramActionPerformed

    private void jMenuItemSetSchemaFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSetSchemaFilesActionPerformed
        pendantClientJPanel1.showSetSchemaFilesDialog();
    }//GEN-LAST:event_jMenuItemSetSchemaFilesActionPerformed

    private void jCheckBoxMenuItemUseEXIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemUseEXIActionPerformed
        pendantClientJPanel1.useExiAction();
    }//GEN-LAST:event_jCheckBoxMenuItemUseEXIActionPerformed

    private void jCheckBoxMenuItemRecordCommandsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemRecordCommandsActionPerformed
        boolean selected = this.jCheckBoxMenuItemRecordCommands.isSelected();
        try {
            if (selected) {
                int oldMaxCount = pendantClientJPanel1.getMaxRecordCommandsCount();
                String maxCountString = JOptionPane.showInputDialog(this, "Maximum number of commands to keep?", oldMaxCount);
                int newMaxCount = Integer.parseInt(maxCountString);
                pendantClientJPanel1.setMaxRecordCommandsCount(newMaxCount);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        pendantClientJPanel1.setRecordCommands(selected);
    }//GEN-LAST:event_jCheckBoxMenuItemRecordCommandsActionPerformed

    private void jCheckBoxMenuItemQuitProgramOnTestCommandFailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemQuitProgramOnTestCommandFailActionPerformed
        pendantClientJPanel1.setQuitOnTestCommandFailure(this.jCheckBoxMenuItemQuitProgramOnTestCommandFail.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemQuitProgramOnTestCommandFailActionPerformed

    private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
        pendantClientJPanel1.aboutAction();
    }//GEN-LAST:event_jMenuItemAboutActionPerformed


    private void jCheckBoxMenuItemDisableTextPopupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDisableTextPopupsActionPerformed
        crcl.ui.misc.MultiLineStringJPanel.disableShowText = jCheckBoxMenuItemDisableTextPopups.isSelected();
        this.pendantClientJPanel1.setDisableTextPopups(crcl.ui.misc.MultiLineStringJPanel.disableShowText);
    }//GEN-LAST:event_jCheckBoxMenuItemDisableTextPopupsActionPerformed

    private void jCheckBoxMenuItemEnableDebugConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemEnableDebugConnectActionPerformed
        this.pendantClientJPanel1.setEnableDebugConnect(jCheckBoxMenuItemEnableDebugConnect.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemEnableDebugConnectActionPerformed

    private void jCheckBoxMenuItemValidateXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemValidateXmlActionPerformed
        pendantClientJPanel1.setValidateXmlSchema(jCheckBoxMenuItemValidateXml.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemValidateXmlActionPerformed

    private void jCheckBoxMenuItemDebugShowProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDebugShowProgramActionPerformed
        pendantClientJPanel1.setDebugShowProgram(jCheckBoxMenuItemDebugShowProgram.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemDebugShowProgramActionPerformed

    private void jMenuItemSaveStatusAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaveStatusAsActionPerformed
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            pendantClientJPanel1.saveStatusAs(chooser.getSelectedFile());
        }
    }//GEN-LAST:event_jMenuItemSaveStatusAsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebugInterrupts;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebugReadStatus;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebugSendCommand;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebugShowProgram;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebugWaitForDone;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDisableTextPopups;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemEnableDebugConnect;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemPlotJoints;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemPlotXYZ;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemQuitProgramOnTestCommandFail;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemRecordCommands;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemRecordPoseList;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemRecordTriggers;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemUseEXI;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemValidateXml;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBarPendantClient;
    private javax.swing.JMenu jMenuCmds;
    private javax.swing.JMenu jMenuCommandRecent;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemClearRecordedPoints;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemLoadPrefs;
    private javax.swing.JMenuItem jMenuItemOpenStatusLog;
    private javax.swing.JMenuItem jMenuItemOpenXmlCommandInstance;
    private javax.swing.JMenuItem jMenuItemOpenXmlProgram;
    private javax.swing.JMenuItem jMenuItemPoseList3DPlot;
    private javax.swing.JMenuItem jMenuItemResetPrefs;
    private javax.swing.JMenuItem jMenuItemRunTest;
    private javax.swing.JMenuItem jMenuItemSavePoseList;
    private javax.swing.JMenuItem jMenuItemSavePrefs;
    private javax.swing.JMenuItem jMenuItemSaveProgramAs;
    private javax.swing.JMenuItem jMenuItemSaveStatusAs;
    private javax.swing.JMenuItem jMenuItemSetSchemaFiles;
    private javax.swing.JMenuItem jMenuItemShowCommandLog;
    private javax.swing.JMenuItem jMenuItemTransformProgram;
    private javax.swing.JMenuItem jMenuItemViewLogFile;
    private javax.swing.JMenuItem jMenuItemXPathQuery;
    private javax.swing.JMenu jMenuOptions;
    private javax.swing.JMenu jMenuRecentProgram;
    private javax.swing.JMenu jMenuTools;
    private javax.swing.JMenu jMenuXmlSchemas;
    private javax.swing.JPanel jPanel1;
    private crcl.ui.client.CrclSwingClientJPanel pendantClientJPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void showMessage(String s) {
        pendantClientJPanel1.showMessage(s);
    }

    @Override
    public void showMessage(Throwable t) {
        pendantClientJPanel1.showMessage(t);
    }

    @Override
    public boolean showDebugMessage(String s) {
        return pendantClientJPanel1.showDebugMessage(s);
    }

    @Override
    public String getHost() {
        return pendantClientJPanel1.getHost();
    }

    @Override
    public int getPort() {
        return pendantClientJPanel1.getPort();
    }

    @Override
    public void finishDisconnect() {
        pendantClientJPanel1.finishDisconnect();
    }

    @Override
    public void finishConnect() {
        pendantClientJPanel1.finishConnect();
    }

    @Override
    public void finishSetStatus() {
        pendantClientJPanel1.finishSetStatus();
    }

    @Override
    public void clearProgramTimesDistances() {
        pendantClientJPanel1.clearProgramTimesDistances();
    }

    @Override
    public void checkXmlQuery(CRCLSocket crclSocket) {
        pendantClientJPanel1.checkXmlQuery(crclSocket);
    }

    @Override
    public void stopPollTimer() {
        pendantClientJPanel1.stopPollTimer();
    }

    @Override
    public void checkPollSelected() {
        pendantClientJPanel1.checkPollSelected();
    }

    @Override
    public void showCurrentProgramLine(int line, CRCLProgramType program, CRCLStatusType status, List<ProgramRunData> progRunDataList) {
        pendantClientJPanel1.showCurrentProgramLine(line, program, status, progRunDataList);
    }

    @Override
    public void showLastProgramLineExecTimeMillisDists(int row, ProgramRunData prd) {
        pendantClientJPanel1.showLastProgramLineExecTimeMillisDists(row, prd);
    }

    @Override
    public void finishOpenXmlProgramFile(File f, CRCLProgramType program, boolean addRecent) {
        pendantClientJPanel1.finishOpenXmlProgramFile(f, program, addRecent);
    }

    @Override
    public CRCLProgramType editProgram(CRCLProgramType program) {
        return pendantClientJPanel1.editProgram(program);
    }

    @Override
    public boolean checkUserText(String text) throws InterruptedException, ExecutionException {
        return pendantClientJPanel1.checkUserText(text);
    }

    @Override
    public boolean isMonitoringHoldingObject() {
        return pendantClientJPanel1.isMonitoringHoldingObject();
    }

    @Override
    public void setExpectedHoldingObject(boolean x) {
        pendantClientJPanel1.setExpectedHoldingObject(x);
    }

    @Override
    public MiddleCommandType currentProgramCommand() {
        return pendantClientJPanel1.currentProgramCommand();
    }

    public Optional<CRCLStatusType> getCurrentStatus() {
        return pendantClientJPanel1.currentStatus();
    }

    public XFuture<CRCLStatusType> getNewStatus() {
        return pendantClientJPanel1.newStatus();
    }

    public void requestAndReadStatus() {
        pendantClientJPanel1.requestAndReadStatus();
    }

    public void setCrclSocketActionExecutorServiceAndThread(ExecutorService crclSocketActionExecutorService, @Nullable Thread crclSocketActionThread) {
        pendantClientJPanel1.setCrclSocketActionExecutorServiceAndThread(crclSocketActionExecutorService, crclSocketActionThread);
    }

    public Optional<CommandStateEnumType> getCurrentState() {
        return pendantClientJPanel1.currentState();
    }

    @Override
    public PoseType currentStatusPose() {
        return pendantClientJPanel1.currentStatusPose();
    }

    public @Nullable
    CRCLProgramType getProgram() {
        return pendantClientJPanel1.getProgram();
    }

    public boolean isConnected() {
        return pendantClientJPanel1.isConnected();
    }

    @Override
    public File getLastOpenedProgramFile() {
        return pendantClientJPanel1.getLastOpenedProgramFile();
    }

    public boolean runCurrentProgram(boolean stepMode) {
        this.jCheckBoxMenuItemQuitProgramOnTestCommandFail.setSelected(true);
        return pendantClientJPanel1.runCurrentProgram(stepMode);
    }

    public XFuture<Boolean> runCurrentProgramAsync(boolean stepMode) {
        this.jCheckBoxMenuItemQuitProgramOnTestCommandFail.setSelected(true);
        return pendantClientJPanel1.runCurrentProgramAsync(stepMode);
    }

    @Override
    public void setProgram(CRCLProgramType program) throws JAXBException {
        pendantClientJPanel1.setProgram(program);
    }

    @Override
    public boolean isPlotJointsSelected() {
        return jCheckBoxMenuItemPlotJoints.isSelected();
    }

    @Override
    public boolean isPlotXyzSelected() {
        return jCheckBoxMenuItemPlotXYZ.isSelected();
    }

    public boolean validateXmlSelected() {
        return jCheckBoxMenuItemValidateXml.isSelected();
    }

//    @Override
//    public boolean replaceStateSelected() {
//        return jCheckBoxMenuItemReplaceState.isSelected();
//    }
    @Override
    public boolean isDebugWaitForDoneSelected() {
        return jCheckBoxMenuItemDebugWaitForDone.isSelected();
    }

    @Override
    public boolean isDebugSendCommandSelected() {
        return jCheckBoxMenuItemDebugSendCommand.isSelected();
    }

    @Override
    public boolean isDebugReadStatusSelected() {
        return jCheckBoxMenuItemDebugReadStatus.isSelected();
    }

    @Override
    public boolean isRecordPoseSelected() {
        return jCheckBoxMenuItemRecordPoseList.isSelected();
    }

    @Override
    public boolean isRecordTriggerSelected() {
        return jCheckBoxMenuItemRecordTriggers.isSelected();
    }

    @Override
    public boolean isEXISelected() {
        return jCheckBoxMenuItemUseEXI.isSelected();
    }

//    @Override
//    public boolean isUseReadStatusThreadSelected() {
//        return jCheckBoxMenuItemUseReadStatusThread.isSelected();
//    }
    @Override
    public PendantClientMenuOuter getMenuOuter() {
        return this;
    }

    public XFutureVoid abortProgram() {
        return pendantClientJPanel1.abortProgram();
    }

    @Override
    public File getPropertiesFile() {
        return pendantClientJPanel1.getPropertiesFile();
    }

    @Override
    public void setPropertiesFile(File propertiesFile) {
        pendantClientJPanel1.setPropertiesFile(propertiesFile);
    }

    public ExecutorService getCrclSocketActionExecutorService() {
        return pendantClientJPanel1.getCrclSocketActionExecutorService();
    }

    @Override
    public void loadProperties() {
        pendantClientJPanel1.loadProperties();
        jCheckBoxMenuItemDebugInterrupts.setSelected(pendantClientJPanel1.isDebugInterrupts());
        jCheckBoxMenuItemDisableTextPopups.setSelected(pendantClientJPanel1.isDisableTextPopups());
//        jCheckBoxMenuItemIgnoreTimeouts.setSelected(isIgnoreTimeouts());
        jCheckBoxMenuItemValidateXml.setSelected(pendantClientJPanel1.isValidateXmlSchema());
    }

    @Override
    public void saveProperties() {
        pendantClientJPanel1.saveProperties();
    }

    public XFuture<Boolean> continueCurrentProgram(boolean stepMode) {
        return pendantClientJPanel1.continueCurrentProgram(stepMode);
    }

    public @Nullable
    File getTempLogDir() {
        return pendantClientJPanel1.getTempLogDir();
    }

    public void setTempLogDir(File tempLogDir) {
        this.pendantClientJPanel1.setTempLogDir(tempLogDir);
    }

    public StackTraceElement @Nullable [] getRunProgramReturnFalseTrace() {
        return pendantClientJPanel1.getRunProgramReturnFalseTrace();
    }

    @Override
    public void updateCommandStatusLog(Deque<CommandStatusLogElement> log) {
        this.pendantClientJPanel1.updateCommandStatusLog(log);
    }

    /**
     * Get the value of preClosing
     *
     * @return the value of preClosing
     */
    public boolean isPreClosing() {
        return pendantClientJPanel1.isPreClosing();
    }

    /**
     * Set the value of preClosing
     *
     * @param preClosing new value of preClosing
     */
    public void setPreClosing(boolean preClosing) {
        pendantClientJPanel1.setPreClosing(preClosing);
    }

    public File writeCommandProfileMap() throws IOException {
        return pendantClientJPanel1.writeCommandProfileMap();
    }

    public File writeCommandStatusLogFile() throws IOException {
        return pendantClientJPanel1.writeCommandStatusLogFile();
    }

    public void printPerfInfo(PrintStream ps, String prefix) {
        pendantClientJPanel1.printPerfInfo(ps, prefix);
    }

    @Override
    public void showPaused(boolean paused) {
        String oldTitle = getTitle();
        if (paused && !oldTitle.startsWith("paused ")) {
            setTitle("paused " + oldTitle);
        } else if (!paused && oldTitle.startsWith("paused ")) {
            setTitle(oldTitle.substring("paused ".length()));
        }
    }

    @Override
    public void showLastGetStatusCommandString(String string) {
        pendantClientJPanel1.showLastGetStatusCommandString(string);
    }

    @Override
    public void showLastStopCommandString(String string) {
        pendantClientJPanel1.showLastStopCommandString(string);
    }

    @Override
    public void showLastOtherCommandString(String string) {
        pendantClientJPanel1.showLastOtherCommandString(string);
    }
}
