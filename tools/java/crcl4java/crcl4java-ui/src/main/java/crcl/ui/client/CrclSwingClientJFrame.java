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
package crcl.ui.client;

import crcl.base.CRCLProgramType;
import crcl.base.CRCLStatusType;
import crcl.base.MiddleCommandType;
import crcl.base.PoseType;
import crcl.ui.IconImages;
import crcl.ui.misc.PropertiesJPanel;
import crcl.ui.server.SimServerJFrame;
import crcl.ui.misc.TransformSetupJFrame;
import static crcl.ui.IconImages.DISCONNECTED_IMAGE;
import crcl.utils.XFutureVoid;
import crcl.utils.CRCLSocket;
import crcl.utils.CRCLException;
import crcl.utils.CRCLUtils;
import crcl.utils.outer.interfaces.CommandStatusLogElement;
import crcl.utils.outer.interfaces.PendantClientMenuOuter;
import crcl.utils.outer.interfaces.PendantClientOuter;
import crcl.utils.outer.interfaces.ProgramRunData;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Will Shackleford{@literal <william.shackleford@nist.gov> }
 */
public class CrclSwingClientJFrame extends javax.swing.JFrame implements PendantClientOuter, PendantClientMenuOuter {

    @SuppressWarnings("initialization")
    public CrclSwingClientJFrame(GraphicsConfiguration gc) throws ParserConfigurationException {
        super(gc);
        init();
    }

    @Override
    public int getCurrentProgramLine() {
        return this.pendantClientJPanel1.getCurrentProgramLine();
    }

    public CrclSwingClientJPanel getPendantClientJPanel1() {
        return pendantClientJPanel1;
    }

    @SuppressWarnings("initialization")
    public CrclSwingClientJFrame(String title) throws ParserConfigurationException {
        super(title);
        init();
        setTitle(title);
    }

    /**
     * Creates new form PendantClient
     *
     * @throws javax.xml.parsers.ParserConfigurationException when
     * javax.xml.parsers.DocumentBuilderFactory fails in XpathUtils
     */
    @SuppressWarnings("initialization")
    public CrclSwingClientJFrame() throws ParserConfigurationException {
        super();
        init();
    }

    private void init() {
        initComponents();
//        pendantClientJPanel1.setOuterContainer(this);
        pendantClientJPanel1.setMenuOuter(this);
        pendantClientJPanel1.addToCommandsMenu(jMenuCmds);

        readRecentCommandFiles();
        readRecentPrograms();
        this.setIconImage(DISCONNECTED_IMAGE);
        this.setTitle("CRCL Client: Disconnected");
        jCheckBoxMenuItemValidateXml.setSelected(pendantClientJPanel1.isValidateXmlSchema());
        try {
            this.setIconImage(IconImages.BASE_IMAGE);
        } catch (Exception ex) {
            Logger.getLogger(SimServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public XFutureVoid abortProgram() {
        return pendantClientJPanel1.abortProgram();
    }

    public void connectCurrent() {
        pendantClientJPanel1.connectCurrent();
    }

    public void connect(String host, int port) {
        pendantClientJPanel1.connect(host, port);
    }

    public void disconnect() {
        pendantClientJPanel1.disconnect();
    }

//    private void updateUIFromInternal() {
//        pendantClientJPanel1.updateUIFromInternal();
//        this.jCheckBoxMenuItemQuitProgramOnTestCommandFail.setSelected(pendantClientJPanel1.getInternal().isQuitOnTestCommandFailure());
//    }

    private static final String recent_files_dir = ".crcl_pendant_client_recent_files";

    @Override
    public void setEnableSaveProgram(boolean enable) {
        this.jMenuItemSaveProgramAs.setEnabled(enable);
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

//    private static final Comparator<File> LAST_MODIFIED_COMPARATOR
//            = (File o1, File o2) -> Long.compare(o1.lastModified(), o2.lastModified());
    private static final Comparator<File> LAST_MODIFIED_COMPARATOR
            = Comparator.comparing(File::lastModified);

    private void sortFileArrayByLastModified(File[] sub_fa) {
        if (null != sub_fa) {
            Arrays.sort(sub_fa, LAST_MODIFIED_COMPARATOR.reversed());
        }
    }

    public static final Logger LOGGER = Logger.getLogger(CrclSwingClientJFrame.class.getName());

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pendantClientJPanel1 = new crcl.ui.client.CrclSwingClientJPanel(this,this);
        jMenuBarPendantClient = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemOpenXmlCommandInstance = new javax.swing.JMenuItem();
        jMenuCommandRecent = new javax.swing.JMenu();
        jMenuItemOpenXmlProgram = new javax.swing.JMenuItem();
        jMenuRecentProgram = new javax.swing.JMenu();
        jMenuItemSaveProgramAs = new javax.swing.JMenuItem();
        jMenuItemClearRecordedPoints = new javax.swing.JMenuItem();
        jMenuItemSavePoseList = new javax.swing.JMenuItem();
        jMenuItemLoadPrefs = new javax.swing.JMenuItem();
        jMenuItemSavePrefs = new javax.swing.JMenuItem();
        jMenuItemResetPrefs = new javax.swing.JMenuItem();
        jMenuItemViewLogFile = new javax.swing.JMenuItem();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuTools = new javax.swing.JMenu();
        jMenuItemXPathQuery = new javax.swing.JMenuItem();
        jCheckBoxMenuItemPlotXYZ = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemJoints = new javax.swing.JCheckBoxMenuItem();
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
        jCheckBoxMenuItemUseEXI = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemRecordCommands = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemQuitProgramOnTestCommandFail = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDisableTextPopups = new javax.swing.JCheckBoxMenuItem();
        jMenuItemAbout = new javax.swing.JMenuItem();

        FormListener formListener = new FormListener();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CRCL Client");

        jMenu1.setText("File");
        jMenu1.addActionListener(formListener);

        jMenuItemOpenXmlCommandInstance.setText("Open CRCL XML Command Instance File ... ");
        jMenuItemOpenXmlCommandInstance.addActionListener(formListener);
        jMenu1.add(jMenuItemOpenXmlCommandInstance);

        jMenuCommandRecent.setText("Recent CRCL XML Command Instance Files");
        jMenu1.add(jMenuCommandRecent);

        jMenuItemOpenXmlProgram.setText("Open CRCL XML Program File ...");
        jMenuItemOpenXmlProgram.addActionListener(formListener);
        jMenu1.add(jMenuItemOpenXmlProgram);

        jMenuRecentProgram.setText("Recent CRCL XML Program File ...");
        jMenu1.add(jMenuRecentProgram);

        jMenuItemSaveProgramAs.setText("Save Recorded Points Program As ...");
        jMenuItemSaveProgramAs.addActionListener(formListener);
        jMenu1.add(jMenuItemSaveProgramAs);

        jMenuItemClearRecordedPoints.setText("Clear Recorded Points");
        jMenuItemClearRecordedPoints.addActionListener(formListener);
        jMenu1.add(jMenuItemClearRecordedPoints);

        jMenuItemSavePoseList.setText("Save Pose List ...");
        jMenuItemSavePoseList.addActionListener(formListener);
        jMenu1.add(jMenuItemSavePoseList);

        jMenuItemLoadPrefs.setText("Load Preferences File ...");
        jMenuItemLoadPrefs.addActionListener(formListener);
        jMenu1.add(jMenuItemLoadPrefs);

        jMenuItemSavePrefs.setText("Save Preferences File ...");
        jMenuItemSavePrefs.addActionListener(formListener);
        jMenu1.add(jMenuItemSavePrefs);

        jMenuItemResetPrefs.setText("Reset Preferences");
        jMenuItemResetPrefs.addActionListener(formListener);
        jMenu1.add(jMenuItemResetPrefs);

        jMenuItemViewLogFile.setText("View Log File ");
        jMenuItemViewLogFile.addActionListener(formListener);
        jMenu1.add(jMenuItemViewLogFile);

        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(formListener);
        jMenu1.add(jMenuItemExit);

        jMenuBarPendantClient.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBarPendantClient.add(jMenu2);

        jMenuTools.setText(" Tools ");

        jMenuItemXPathQuery.setText("XPath Status  Query ... ");
        jMenuItemXPathQuery.addActionListener(formListener);
        jMenuTools.add(jMenuItemXPathQuery);

        jCheckBoxMenuItemPlotXYZ.setText("2D Plot XYZ vs Time ...");
        jCheckBoxMenuItemPlotXYZ.addActionListener(formListener);
        jMenuTools.add(jCheckBoxMenuItemPlotXYZ);

        jCheckBoxMenuItemJoints.setText("Plot Joints");
        jCheckBoxMenuItemJoints.addActionListener(formListener);
        jMenuTools.add(jCheckBoxMenuItemJoints);

        jMenuItemRunTest.setText("Run Test");
        jMenuItemRunTest.addActionListener(formListener);
        jMenuTools.add(jMenuItemRunTest);

        jCheckBoxMenuItemRecordPoseList.setText("Record Pose List");
        jMenuTools.add(jCheckBoxMenuItemRecordPoseList);

        jMenuItemPoseList3DPlot.setText("3D Pose List Plot ...");
        jMenuItemPoseList3DPlot.addActionListener(formListener);
        jMenuTools.add(jMenuItemPoseList3DPlot);

        jMenuItemOpenStatusLog.setText("Open Status Log ...");
        jMenuItemOpenStatusLog.addActionListener(formListener);
        jMenuTools.add(jMenuItemOpenStatusLog);

        jMenuItemShowCommandLog.setText("Show Command Log ...");
        jMenuItemShowCommandLog.addActionListener(formListener);
        jMenuTools.add(jMenuItemShowCommandLog);

        jMenuItemTransformProgram.setText("Transform Program");
        jMenuItemTransformProgram.addActionListener(formListener);
        jMenuTools.add(jMenuItemTransformProgram);

        jCheckBoxMenuItemRecordTriggers.setSelected(true);
        jCheckBoxMenuItemRecordTriggers.setText("Record Triggers");
        jMenuTools.add(jCheckBoxMenuItemRecordTriggers);

        jMenuBarPendantClient.add(jMenuTools);

        jMenuCmds.setText("Commands");
        jMenuBarPendantClient.add(jMenuCmds);

        jMenuXmlSchemas.setText("XML Schemas");

        jMenuItemSetSchemaFiles.setText("Set XML Schema Files ... ");
        jMenuItemSetSchemaFiles.addActionListener(formListener);
        jMenuXmlSchemas.add(jMenuItemSetSchemaFiles);

        jCheckBoxMenuItemValidateXml.setSelected(true);
        jCheckBoxMenuItemValidateXml.setText("Validate using Schemas");
        jCheckBoxMenuItemValidateXml.addActionListener(formListener);
        jMenuXmlSchemas.add(jCheckBoxMenuItemValidateXml);

        jMenuBarPendantClient.add(jMenuXmlSchemas);

        jMenuOptions.setText("Options");

        jCheckBoxMenuItemDebugWaitForDone.setText("Debug waitForDone()");
        jMenuOptions.add(jCheckBoxMenuItemDebugWaitForDone);

        jCheckBoxMenuItemDebugSendCommand.setText("Debug sendCommand()");
        jMenuOptions.add(jCheckBoxMenuItemDebugSendCommand);

        jCheckBoxMenuItemDebugReadStatus.setText("Debug  readStatus() ");
        jMenuOptions.add(jCheckBoxMenuItemDebugReadStatus);

        jCheckBoxMenuItemUseEXI.setText("USE EXI (Efficient XML Interchange)");
        jCheckBoxMenuItemUseEXI.addActionListener(formListener);
        jMenuOptions.add(jCheckBoxMenuItemUseEXI);

        jCheckBoxMenuItemRecordCommands.setText("Record Commands");
        jCheckBoxMenuItemRecordCommands.addActionListener(formListener);
        jMenuOptions.add(jCheckBoxMenuItemRecordCommands);

        jCheckBoxMenuItemQuitProgramOnTestCommandFail.setSelected(true);
        jCheckBoxMenuItemQuitProgramOnTestCommandFail.setText("Quit Program on Test Command Fail");
        jCheckBoxMenuItemQuitProgramOnTestCommandFail.addActionListener(formListener);
        jMenuOptions.add(jCheckBoxMenuItemQuitProgramOnTestCommandFail);

        jCheckBoxMenuItemDisableTextPopups.setSelected(true);
        jCheckBoxMenuItemDisableTextPopups.setText("Disable Text Popups");
        jCheckBoxMenuItemDisableTextPopups.addActionListener(formListener);
        jMenuOptions.add(jCheckBoxMenuItemDisableTextPopups);

        jMenuItemAbout.setText("About");
        jMenuItemAbout.addActionListener(formListener);
        jMenuOptions.add(jMenuItemAbout);

        jMenuBarPendantClient.add(jMenuOptions);

        setJMenuBar(jMenuBarPendantClient);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pendantClientJPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pendantClientJPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jMenu1) {
                CrclSwingClientJFrame.this.jMenu1ActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemOpenXmlCommandInstance) {
                CrclSwingClientJFrame.this.jMenuItemOpenXmlCommandInstanceActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemOpenXmlProgram) {
                CrclSwingClientJFrame.this.jMenuItemOpenXmlProgramActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemSaveProgramAs) {
                CrclSwingClientJFrame.this.jMenuItemSaveProgramAsActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemClearRecordedPoints) {
                CrclSwingClientJFrame.this.jMenuItemClearRecordedPointsActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemSavePoseList) {
                CrclSwingClientJFrame.this.jMenuItemSavePoseListActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemLoadPrefs) {
                CrclSwingClientJFrame.this.jMenuItemLoadPrefsActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemSavePrefs) {
                CrclSwingClientJFrame.this.jMenuItemSavePrefsActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemResetPrefs) {
                CrclSwingClientJFrame.this.jMenuItemResetPrefsActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemViewLogFile) {
                CrclSwingClientJFrame.this.jMenuItemViewLogFileActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemExit) {
                CrclSwingClientJFrame.this.jMenuItemExitActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemXPathQuery) {
                CrclSwingClientJFrame.this.jMenuItemXPathQueryActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemPlotXYZ) {
                CrclSwingClientJFrame.this.jCheckBoxMenuItemPlotXYZActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemJoints) {
                CrclSwingClientJFrame.this.jCheckBoxMenuItemJointsActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemRunTest) {
                CrclSwingClientJFrame.this.jMenuItemRunTestActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemPoseList3DPlot) {
                CrclSwingClientJFrame.this.jMenuItemPoseList3DPlotActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemOpenStatusLog) {
                CrclSwingClientJFrame.this.jMenuItemOpenStatusLogActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemShowCommandLog) {
                CrclSwingClientJFrame.this.jMenuItemShowCommandLogActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemTransformProgram) {
                CrclSwingClientJFrame.this.jMenuItemTransformProgramActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemSetSchemaFiles) {
                CrclSwingClientJFrame.this.jMenuItemSetSchemaFilesActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemValidateXml) {
                CrclSwingClientJFrame.this.jCheckBoxMenuItemValidateXmlActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemUseEXI) {
                CrclSwingClientJFrame.this.jCheckBoxMenuItemUseEXIActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemRecordCommands) {
                CrclSwingClientJFrame.this.jCheckBoxMenuItemRecordCommandsActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemQuitProgramOnTestCommandFail) {
                CrclSwingClientJFrame.this.jCheckBoxMenuItemQuitProgramOnTestCommandFailActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemDisableTextPopups) {
                CrclSwingClientJFrame.this.jCheckBoxMenuItemDisableTextPopupsActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemAbout) {
                CrclSwingClientJFrame.this.jMenuItemAboutActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents


    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemExitActionPerformed


    private void jMenuItemXPathQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemXPathQueryActionPerformed
        pendantClientJPanel1.showXpathQueryDialog();
    }//GEN-LAST:event_jMenuItemXPathQueryActionPerformed

    private void jMenuItemSetSchemaFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSetSchemaFilesActionPerformed
        pendantClientJPanel1.showSetSchemaFilesDialog();
    }//GEN-LAST:event_jMenuItemSetSchemaFilesActionPerformed

    private void jCheckBoxMenuItemJointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemJointsActionPerformed

    }//GEN-LAST:event_jCheckBoxMenuItemJointsActionPerformed

    private void jCheckBoxMenuItemPlotXYZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemPlotXYZActionPerformed
        if (this.jCheckBoxMenuItemPlotXYZ.isSelected()) {
            pendantClientJPanel1.showJointsPlot();
        }
    }//GEN-LAST:event_jCheckBoxMenuItemPlotXYZActionPerformed

    private void jMenuItemOpenXmlCommandInstanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOpenXmlCommandInstanceActionPerformed
        pendantClientJPanel1.browseOpenCommandXml();
    }//GEN-LAST:event_jMenuItemOpenXmlCommandInstanceActionPerformed

    private void jMenuItemOpenXmlProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOpenXmlProgramActionPerformed
        pendantClientJPanel1.browseOpenProgramXml();
    }//GEN-LAST:event_jMenuItemOpenXmlProgramActionPerformed


    private void jMenuItemRunTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRunTestActionPerformed
        Map<String, String> testPropsMap
                = PropertiesJPanel.confirmPropertiesMap(this, "Test Run Properties", true,
                        pendantClientJPanel1.getInternal().getDefaultTestPropertiesMap());
        if (null != testPropsMap) {
            pendantClientJPanel1.startRunTest(testPropsMap);
        }
    }//GEN-LAST:event_jMenuItemRunTestActionPerformed


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

    private void jMenuItemSavePoseListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSavePoseListActionPerformed
        pendantClientJPanel1.savePoseList();
    }//GEN-LAST:event_jMenuItemSavePoseListActionPerformed

    private void jMenuItemPoseList3DPlotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPoseList3DPlotActionPerformed
//        pendantClientJPanel1.show3DPlot();
    }//GEN-LAST:event_jMenuItemPoseList3DPlotActionPerformed

    private void jMenuItemOpenStatusLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOpenStatusLogActionPerformed
        pendantClientJPanel1.showStatusLog();
    }//GEN-LAST:event_jMenuItemOpenStatusLogActionPerformed

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

    private void jMenuItemShowCommandLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemShowCommandLogActionPerformed

        pendantClientJPanel1.showCommandLog();
    }//GEN-LAST:event_jMenuItemShowCommandLogActionPerformed


    private void jMenuItemClearRecordedPointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClearRecordedPointsActionPerformed
        pendantClientJPanel1.clearRecordedPoints();
    }//GEN-LAST:event_jMenuItemClearRecordedPointsActionPerformed


    private void jMenuItemLoadPrefsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLoadPrefsActionPerformed
        pendantClientJPanel1.loadPrefsAction();
    }//GEN-LAST:event_jMenuItemLoadPrefsActionPerformed

    private void jMenuItemSavePrefsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSavePrefsActionPerformed
        pendantClientJPanel1.savePrefsAction();
    }//GEN-LAST:event_jMenuItemSavePrefsActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jCheckBoxMenuItemQuitProgramOnTestCommandFailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemQuitProgramOnTestCommandFailActionPerformed
        pendantClientJPanel1.setQuitOnTestCommandFailure(this.jCheckBoxMenuItemQuitProgramOnTestCommandFail.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemQuitProgramOnTestCommandFailActionPerformed

    private void jMenuItemViewLogFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemViewLogFileActionPerformed
        pendantClientJPanel1.openLogFile();
    }//GEN-LAST:event_jMenuItemViewLogFileActionPerformed

    private void jMenuItemTransformProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTransformProgramActionPerformed
        TransformSetupJFrame setupFrame = new TransformSetupJFrame();
        setupFrame.setPendantClient(this.pendantClientJPanel1);
        setupFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItemTransformProgramActionPerformed


    private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
        pendantClientJPanel1.aboutAction();
    }//GEN-LAST:event_jMenuItemAboutActionPerformed

    private void jMenuItemResetPrefsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemResetPrefsActionPerformed
        pendantClientJPanel1.resetPrefs();
    }//GEN-LAST:event_jMenuItemResetPrefsActionPerformed

    private void jCheckBoxMenuItemDisableTextPopupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDisableTextPopupsActionPerformed
        crcl.ui.misc.MultiLineStringJPanel.disableShowText = jCheckBoxMenuItemDisableTextPopups.isSelected();
        this.pendantClientJPanel1.setDisableTextPopups(crcl.ui.misc.MultiLineStringJPanel.disableShowText);
    }//GEN-LAST:event_jCheckBoxMenuItemDisableTextPopupsActionPerformed

    private void jCheckBoxMenuItemValidateXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemValidateXmlActionPerformed
        pendantClientJPanel1.setValidateXmlSchema(jCheckBoxMenuItemValidateXml.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemValidateXmlActionPerformed

    @Override
    public boolean isEXISelected() {
        return this.jCheckBoxMenuItemUseEXI.isSelected();
    }

    public void addProgramLineListener(ProgramLineListener l) {
        pendantClientJPanel1.addProgramLineListener(l);
    }

    public void removeProgramLineListener(ProgramLineListener l) {
        pendantClientJPanel1.removeProgramLineListener(l);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        String prefLaf = "Nimbus"; // "GTK+"; // "GTK+"; // Nimbus, Metal ...
        /* Set the preferred look and feel */

        if (prefLaf != null) {
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
             */
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                    System.out.println("info.getName() = " + info.getName());
                    if (prefLaf.equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;

                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(CrclSwingClientJFrame.class
                        .getName()).log(java.util.logging.Level.SEVERE, null, ex);

            }
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new CrclSwingClientJFrame().setVisible(true);
                } catch (ParserConfigurationException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebugReadStatus;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebugSendCommand;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebugWaitForDone;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDisableTextPopups;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemJoints;
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
    private javax.swing.JMenuItem jMenuItemSetSchemaFiles;
    private javax.swing.JMenuItem jMenuItemShowCommandLog;
    private javax.swing.JMenuItem jMenuItemTransformProgram;
    private javax.swing.JMenuItem jMenuItemViewLogFile;
    private javax.swing.JMenuItem jMenuItemXPathQuery;
    private javax.swing.JMenu jMenuOptions;
    private javax.swing.JMenu jMenuRecentProgram;
    private javax.swing.JMenu jMenuTools;
    private javax.swing.JMenu jMenuXmlSchemas;
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

    @Override
    public PoseType currentStatusPose() {
        return pendantClientJPanel1.currentStatusPose();
    }

    public @Nullable
    CRCLProgramType getProgram() {
        return pendantClientJPanel1.getProgram();
    }

    @Override
    public File getLastOpenedProgramFile() {
        return pendantClientJPanel1.getLastOpenedProgramFile();
    }

    @Override
    public void setProgram(CRCLProgramType program) throws JAXBException {
        pendantClientJPanel1.setProgram(program);
    }

    @Override
    public void saveXmlProgramFile(File f) throws JAXBException, CRCLException {
        pendantClientJPanel1.saveXmlProgramFile(f);
    }

    @Override
    public boolean isPlotJointsSelected() {
        return jCheckBoxMenuItemJoints.isSelected();
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
    public PendantClientMenuOuter getMenuOuter() {
        return this;
    }

    @Override
    public File getPropertiesFile() {
        return pendantClientJPanel1.getPropertiesFile();
    }

    @Override
    public void setPropertiesFile(File propertiesFile) {
        pendantClientJPanel1.setPropertiesFile(propertiesFile);
    }

    @Override
    public void loadProperties() {
        pendantClientJPanel1.loadProperties();
        jCheckBoxMenuItemDisableTextPopups.setSelected(pendantClientJPanel1.isDisableTextPopups());
        jCheckBoxMenuItemValidateXml.setSelected(pendantClientJPanel1.isValidateXmlSchema());
    }

    @Override
    public void saveProperties() {
        pendantClientJPanel1.saveProperties();
    }

    @Override
    public void updateCommandStatusLog(Deque<CommandStatusLogElement> log) {
        this.pendantClientJPanel1.updateCommandStatusLog(log);
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
    public boolean isRecordTriggerSelected() {
        return jCheckBoxMenuItemRecordTriggers.isSelected();
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
