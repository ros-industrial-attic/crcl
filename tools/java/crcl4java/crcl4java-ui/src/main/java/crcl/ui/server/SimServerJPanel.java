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
package crcl.ui.server;

import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStateEnumType;
import crcl.base.GetStatusType;
import crcl.base.GripperStatusType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.PoseStatusType;
import crcl.base.PoseType;
import crcl.ui.DefaultSchemaFiles;
import crcl.utils.XFuture;

import crcl.ui.misc.MultiLineStringJPanel;
import crcl.ui.misc.ObjTableJPanel;
import crcl.utils.CRCLException;
import crcl.utils.CRCLSchemaUtils;
import crcl.utils.CRCLSocket;
import crcl.utils.CRCLUtils;
import static crcl.utils.CRCLUtils.getNonNullJointStatusIterable;
import crcl.utils.PropertiesUtils;
import crcl.utils.kinematics.SimRobotEnum;
import crcl.utils.outer.interfaces.SimServerMenuOuter;
import crcl.utils.outer.interfaces.SimServerOuter;
import crcl.utils.server.CRCLServerSocket;
import crcl.utils.server.GuardHistoryElement;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import static java.util.Objects.requireNonNull;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class SimServerJPanel extends javax.swing.JPanel implements SimServerOuter, AutoCloseable {

    /**
     * Creates new form SimServerJPanel
     *
     */
    @SuppressWarnings("initialization")
    public SimServerJPanel() {
        initComponents();
        try {
            this.inner = new SimServerInner(this, DefaultSchemaFiles.instance());
        } catch (Exception ex) {
            Logger.getLogger(SimServerJPanel.class.getName()).log(Level.SEVERE, "", ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
        SimRobotEnum defaultRobotType
                = DEFAULT_ROBOTTYPE;
        java.awt.EventQueue.invokeLater(() -> this.updatePanelsPrivate());
//        this.lengthUnitComboBox.setSelectedItem(LengthUnitEnumType.MILLIMETER);
//        for (SimRobotEnum srType : SimRobotEnum.values()) {
//            this.setRobotType(srType);
//            inner.setLengthUnit(LengthUnitEnumType.MILLIMETER);
//        }
        this.jComboBoxRobotType.setModel(new DefaultComboBoxModel<>(SimRobotEnum.values()));
        this.jComboBoxRobotType.setSelectedItem(defaultRobotType);
        this.setRobotType(defaultRobotType);

        String portPropertyString = System.getProperty("crcl4java.port");
        if (null != portPropertyString) {
            inner.setPort(Integer.parseInt(portPropertyString));
            this.jTextFieldPort.setText(portPropertyString);
        } else {
            this.jTextFieldPort.setText(Integer.toString(inner.getPort()));
        }
        try {
            String imageLogDirProp = System.getProperty("crcl4java.simserver.imagelogdir");
            File imageLogDir = null;
            if (imageLogDirProp != null && imageLogDirProp.length() > 0) {
                imageLogDir = new File(imageLogDirProp);
                if (!imageLogDir.exists()
                        || !imageLogDir.isDirectory()
                        || !imageLogDir.canWrite()) {
                    Logger.getLogger(SimServerJPanel.class.getName()).log(Level.SEVERE, "Can''t use tempDir from property temp.dir {0}", imageLogDir);
                    imageLogDir = null;
                }
            }
            if (null == imageLogDir) {
                File testTempFile = File.createTempFile("test", "suffix");
                imageLogDir = testTempFile.getParentFile();
                Files.delete(testTempFile.toPath());
            }
            String imageLogDirPath = imageLogDir.getCanonicalPath();
            this.overHeadJPanel1.setImageLogDir(imageLogDirPath);
            this.sideViewJPanel1.setImageLogDir(imageLogDirPath);
        } catch (IOException ex) {
            Logger.getLogger(SimServerJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean logImages = LOG_IMAGES_DEFAULT;
        this.overHeadJPanel1.setLogImages(logImages);
        this.sideViewJPanel1.setLogImages(logImages);
        this.jCheckBoxTeleportToGoals.setSelected(inner.isTeleportToGoals());
        jCheckBoxMenuItemValidateXML.setSelected(inner.isValidateXMLSelected());
        this.jCheckBoxMenuItemLogImages.setSelected(logImages);
    }

    private final ConcurrentLinkedDeque<File> propsFiles = new ConcurrentLinkedDeque<>();

    public boolean isRunning() {
        return inner.isRunning();
    }

    public void restartServer() {
        try {
            inner.restartServer(inner.getServerIsDaemon());
            if (null != propertiesFile) {
                propsFiles.add(propertiesFile);
            }
        } catch (Exception ex) {
            System.err.println("propertiesFile=" + propertiesFile);
            System.err.println("propsFiles=" + propsFiles);
            throw ex;
        }
    }

    public void closeServer() {
        inner.closeServer();
    }

    private File propertiesFile;

    /**
     * Get the value of propertiesFile
     *
     * @return the value of propertiesFile
     */
    public File getPropertiesFile() {
        return propertiesFile;
    }

    /**
     * Set the value of propertiesFile
     *
     * @param propertiesFile new value of propertiesFile
     */
    public void setPropertiesFile(File propertiesFile) {
        this.propertiesFile = propertiesFile;
    }

    public void saveProperties() throws IOException {
        if (null != propertiesFile) {
            Properties props = new Properties();
            int port = Integer.parseInt(jTextFieldPort.getText());
            props.put(CRCLPORT_PROPERTY_NAME, Integer.toString(port));
            props.put(VALIDATEXML_PROPERTY_NAME, Boolean.toString(inner.isValidateXMLSelected()));
            props.put(SIM_ROBOT_TYPE_PROPERTY_NAME, inner.getRobotType().toString());
            String initStatusFilename = inner.getInitStatusFilename();
            if (null != initStatusFilename) {
                props.put(SIM_INIT_STATUS_PROPERTY_NAME, initStatusFilename);
            }
            System.out.println("SimServerJPanel saving properties to " + propertiesFile.getCanonicalPath());
            PropertiesUtils.saveProperties(propertiesFile, props);
        }
    }

    /**
     * Get the value of initStatusFilename
     *
     * @return the value of initStatusFilename
     */
    public String getInitStatusFilename() {
        return inner.getInitStatusFilename();
    }

    /**
     * Set the value of initStatusFilename
     *
     * @param initStatusFilename new value of initStatusFilename
     */
    public void setInitStatusFilename(String initStatusFilename) {
        inner.setInitStatusFilename(initStatusFilename);
    }

    public void loadProperties() throws IOException {
        if (null != propertiesFile) {
            Properties props = new Properties();
            if (propertiesFile.exists()) {
                try (FileReader fr = new FileReader(propertiesFile)) {
                    props.load(fr);
                }
            }
            String validateXmlString = props.getProperty(VALIDATEXML_PROPERTY_NAME);
            if (null != validateXmlString) {
                inner.setValidateXMLSelected(Boolean.parseBoolean(validateXmlString));
            }
            String robotTypeString = props.getProperty(SIM_ROBOT_TYPE_PROPERTY_NAME);
            if (null != robotTypeString) {
                inner.setRobotType(SimRobotEnum.valueOf(robotTypeString));
            }
            String initStatusFilename = props.getProperty(SIM_INIT_STATUS_PROPERTY_NAME);
            if (null != initStatusFilename) {
                try {
                    inner.setInitStatusFilename(initStatusFilename);
                    CRCLStatusType initStatus = CRCLUtils.readStatusFile(new File(propertiesFile.getParentFile(), initStatusFilename));
                    if (null != initStatus) {
                        JointStatusesType jsst = initStatus.getJointStatuses();
                        if (null != jsst) {
                            setCommandStatDONE();
                            Iterable<JointStatusType> jsIterable
                                    = getNonNullJointStatusIterable(jsst);
                            for (JointStatusType jst : jsIterable) {
                                final Double jointPosition
                                        = Objects.requireNonNull(jst.getJointPosition(), "jst.getJointPosition()");
                                final int jointNumber = jst.getJointNumber();
                                inner.setJointPosition(jointPosition, jointNumber - 1);
                            }
                        }
                        PoseStatusType poseStatus = initStatus.getPoseStatus();
                        if (null != poseStatus) {
                            double jpos[] = inner.getJointPositions();
                            if (null != jpos) {
                                PoseType initPose = poseStatus.getPose();
                                if (null != initPose) {
                                    double newjpos[] = inner.poseToJoints(jpos, initPose);
                                    setCommandStatDONE();
                                    inner.teleportJoints(newjpos);
                                    inner.setPose(initPose);
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(SimServerJPanel.class.getName()).log(Level.SEVERE, "loadProperties: initStatusFilename=" + initStatusFilename, ex);
                    if (ex instanceof RuntimeException) {
                        throw (RuntimeException) ex;
                    } else {
                        throw new RuntimeException(ex);
                    }
                }
            }
            String portString = props.getProperty(CRCLPORT_PROPERTY_NAME);
            if (portString != null) {
                int port = Integer.parseInt(portString);
                jTextFieldPort.setText(Integer.toString(port));
                inner.setPort(port);
                restartServer();
            }
        }
        jCheckBoxMenuItemValidateXML.setSelected(this.isValidateXMLSelected());
    }

    private void setCommandStatDONE() {
        setCommandState(CRCL_DONE);
    }

    @SuppressWarnings("nullness")
    private static final @NonNull
    CommandStateEnumType CRCL_DONE = CommandStateEnumType.CRCL_DONE;

    private static final String SIM_INIT_STATUS_PROPERTY_NAME = "crcl.sim.initStatus";
    private static final String SIM_ROBOT_TYPE_PROPERTY_NAME = "crcl.sim.robotType";
    private static final String VALIDATEXML_PROPERTY_NAME = "crcl.validateXML";
    private static final String CRCLPORT_PROPERTY_NAME = "crcl.port";

    static public boolean LOG_IMAGES_DEFAULT = false;

    String tempDir = "/tmp";

    private final static SimRobotEnum DEFAULT_ROBOTTYPE = SimRobotEnum.valueOf(System.getProperty("crcl4java.simserver.robottype", SimRobotEnum.SIMPLE.toString()));

    private @Nullable
    CRCLSocket gripperSocket = null;

    private @Nullable
    Thread gripperReadThread = null;
    private int gripperPort = 4005;
    private String gripperHost = "localhost";
    private boolean sendGripperStatusRequests = true;

    private void setRobotType(SimRobotEnum robotType) {
        this.inner.setRobotType(robotType);
        double[] jointPositions = requireNonNull(inner.getJointPositions(), "inner.getJointPositions()");
        this.overHeadJPanel1.setJointvals(jointPositions);
        this.sideViewJPanel1.setJointvals(jointPositions);
        double[] segLengths = requireNonNull(inner.getSeglengths(), "inner.getSeglengths()");
        this.overHeadJPanel1.setSeglengths(segLengths);
        this.sideViewJPanel1.setSeglengths(segLengths);
        this.overHeadJPanel1.setRobotType(robotType);
        this.sideViewJPanel1.setRobotType(robotType);
        this.updatePanels(true);
    }

    public static final Logger LOGGER = Logger.getLogger(SimServerJPanel.class.getName());

    private void closeGripperSocket() {
        if (null != gripperSocket) {
            try {
                gripperSocket.close();
                gripperSocket = null;
                this.inner.setGripperSocket(null);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        Thread gripperReadThread1 = this.gripperReadThread;
        if (null != gripperReadThread1) {
            try {
                if (gripperReadThread1.isAlive()) {
                    Thread.dumpStack();
                    System.err.println("Interrupting gripperReadThread = " + gripperReadThread1);
                    System.out.println("Interrupting gripperReadThread = " + gripperReadThread1);
                    System.out.println("gripperReadThread.getStackTrace() = " + Arrays.toString(gripperReadThread1.getStackTrace()));
                    gripperReadThread1.interrupt();
                    gripperReadThread1.join(100);
                }
                gripperReadThread1 = null;
                this.gripperReadThread = null;
            } catch (InterruptedException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
    }

    public CRCLStatusType getStatus() {
        return inner.getStatus();
    }

    private volatile SimServerMenuOuter menuOuter;

    /**
     * Get the value of menuOuter
     *
     * @return the value of menuOuter
     */
    public SimServerMenuOuter getMenuOuter() {
        return menuOuter;
    }

    public boolean isValidateXMLSelected() {
        return inner.isValidateXMLSelected();
    }

    public void setValidateXMLSelected(boolean validateXMLSelected) {
        inner.setValidateXMLSelected(validateXMLSelected);
    }

    /**
     * Set the value of menuOuter
     *
     * @param menuOuter new value of menuOuter
     */
    public void setMenuOuter(SimServerMenuOuter menuOuter) {
        this.menuOuter = menuOuter;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuItemSaveProperties = new javax.swing.JMenuItem();
        jMenuItemLoadProperties = new javax.swing.JMenuItem();
        jMenuItemSaveStatusToFile = new javax.swing.JMenuItem();
        jMenuItemSetInitalStatusFile = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemEditStatus = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemSetSchema = new javax.swing.JMenuItem();
        jCheckBoxMenuItemValidateXML = new javax.swing.JCheckBoxMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jCheckBoxMenuItemRandomPacket = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemAppendZero = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemIncludeGripperStatus = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDebugMoveDone = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDebugReadCommand = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDebugSendStatus = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemReplaceXmlHeader = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemEXI = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemLogImages = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDisableTextPopups = new javax.swing.JCheckBoxMenuItem();
        jMenuItemAbout = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItemViewCommandLogBrief = new javax.swing.JMenuItem();
        jMenuItemViewCommandLogFull = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        overHeadJPanel1 = new crcl.ui.server.OverHeadJPanel();
        jPanel2 = new javax.swing.JPanel();
        sideViewJPanel1 = new crcl.ui.server.SideViewJPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaErrors = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldPort = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldCycleTime = new javax.swing.JTextField();
        jButtonReset = new javax.swing.JButton();
        jCheckBoxInitialized = new javax.swing.JCheckBox();
        jCheckBoxSendStatusWithoutRequest = new javax.swing.JCheckBox();
        jButtonRestartServer = new javax.swing.JButton();
        jComboBoxRobotType = new javax.swing.JComboBox<>();
        jCheckBoxTeleportToGoals = new javax.swing.JCheckBox();
        jCheckBoxForceFail = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldConnectedClients = new javax.swing.JTextField();
        jTextFieldCycleCount = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldNumWaypoints = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldCurWaypoint = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldEndEffector = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldCurrentCommandType = new javax.swing.JTextField();
        jButtonPlotGaurdValues = new javax.swing.JButton();

        jMenu1.setText("File");

        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemExit);

        jMenuItemSaveProperties.setText("Save Properties As ...");
        jMenuItemSaveProperties.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSavePropertiesActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSaveProperties);

        jMenuItemLoadProperties.setText("Load Properties ...");
        jMenuItemLoadProperties.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLoadPropertiesActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemLoadProperties);

        jMenuItemSaveStatusToFile.setText("Save Status to File ...");
        jMenuItemSaveStatusToFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaveStatusToFileActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSaveStatusToFile);

        jMenuItemSetInitalStatusFile.setText("Set Inital Status File ...");
        jMenuItemSetInitalStatusFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSetInitalStatusFileActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSetInitalStatusFile);

        jMenuBarSimServer.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItemEditStatus.setText("Status in Table ...");
        jMenuItemEditStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEditStatusActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemEditStatus);

        jMenuBarSimServer.add(jMenu2);

        jMenu3.setText("XML Schemas");

        jMenuItemSetSchema.setText("Set Schema File(s)  ... ");
        jMenuItemSetSchema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSetSchemaActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemSetSchema);

        jCheckBoxMenuItemValidateXML.setSelected(true);
        jCheckBoxMenuItemValidateXML.setText("Validate XML with Schema(s)");
        jCheckBoxMenuItemValidateXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemValidateXMLActionPerformed(evt);
            }
        });
        jMenu3.add(jCheckBoxMenuItemValidateXML);

        jMenuBarSimServer.add(jMenu3);

        jMenu4.setText("Options");

        jCheckBoxMenuItemRandomPacket.setText("Randomize Packeting");
        jMenu4.add(jCheckBoxMenuItemRandomPacket);

        jCheckBoxMenuItemAppendZero.setText("Append 0 for string termination");
        jMenu4.add(jCheckBoxMenuItemAppendZero);

        jCheckBoxMenuItemIncludeGripperStatus.setText("Connect to Gripper Server ...");
        jCheckBoxMenuItemIncludeGripperStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemIncludeGripperStatusActionPerformed(evt);
            }
        });
        jMenu4.add(jCheckBoxMenuItemIncludeGripperStatus);

        jCheckBoxMenuItemDebugMoveDone.setText("Debug MOVE Done");
        jMenu4.add(jCheckBoxMenuItemDebugMoveDone);

        jCheckBoxMenuItemDebugReadCommand.setText("Debug Read Command");
        jMenu4.add(jCheckBoxMenuItemDebugReadCommand);

        jCheckBoxMenuItemDebugSendStatus.setText("Debug Send Status");
        jMenu4.add(jCheckBoxMenuItemDebugSendStatus);

        jCheckBoxMenuItemReplaceXmlHeader.setSelected(true);
        jCheckBoxMenuItemReplaceXmlHeader.setText("Replace XML Headers");
        jMenu4.add(jCheckBoxMenuItemReplaceXmlHeader);

        jCheckBoxMenuItemEXI.setText("Use EXI (Efficient XML Interchange)");
        jCheckBoxMenuItemEXI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemEXIActionPerformed(evt);
            }
        });
        jMenu4.add(jCheckBoxMenuItemEXI);

        jCheckBoxMenuItemLogImages.setText("Log Images");
        jCheckBoxMenuItemLogImages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemLogImagesActionPerformed(evt);
            }
        });
        jMenu4.add(jCheckBoxMenuItemLogImages);

        jCheckBoxMenuItemDisableTextPopups.setText("Disable Text Popups");
        jCheckBoxMenuItemDisableTextPopups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDisableTextPopupsActionPerformed(evt);
            }
        });
        jMenu4.add(jCheckBoxMenuItemDisableTextPopups);

        jMenuItemAbout.setText("About");
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItemAbout);

        jMenuBarSimServer.add(jMenu4);

        jMenu5.setText("Tools");

        jMenuItemViewCommandLogBrief.setText("View Command Log Brief");
        jMenuItemViewCommandLogBrief.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemViewCommandLogBriefActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItemViewCommandLogBrief);

        jMenuItemViewCommandLogFull.setText("View Command Log Full");
        jMenuItemViewCommandLogFull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemViewCommandLogFullActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItemViewCommandLogFull);

        jMenuBarSimServer.add(jMenu5);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Overhead View"));
        jPanel1.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout overHeadJPanel1Layout = new javax.swing.GroupLayout(overHeadJPanel1);
        overHeadJPanel1.setLayout(overHeadJPanel1Layout);
        overHeadJPanel1Layout.setHorizontalGroup(
            overHeadJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        overHeadJPanel1Layout.setVerticalGroup(
            overHeadJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        jPanel1.add(overHeadJPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Side View"));
        jPanel2.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout sideViewJPanel1Layout = new javax.swing.GroupLayout(sideViewJPanel1);
        sideViewJPanel1.setLayout(sideViewJPanel1Layout);
        sideViewJPanel1Layout.setHorizontalGroup(
            sideViewJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        sideViewJPanel1Layout.setVerticalGroup(
            sideViewJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );

        jPanel2.add(sideViewJPanel1, java.awt.BorderLayout.CENTER);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Errors and Messages"));

        jTextAreaErrors.setColumns(20);
        jTextAreaErrors.setLineWrap(true);
        jTextAreaErrors.setRows(5);
        jTextAreaErrors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaErrorsMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextAreaErrorsMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextAreaErrorsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTextAreaErrors);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Setup"));

        jLabel1.setText("Port: ");

        jTextFieldPort.setText("64444");
        jTextFieldPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPortActionPerformed(evt);
            }
        });

        jLabel3.setText("Cycle Time (ms): ");

        jTextFieldCycleTime.setText("20");
        jTextFieldCycleTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCycleTimeActionPerformed(evt);
            }
        });

        jButtonReset.setText("Reset");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        jCheckBoxInitialized.setText("Initialized");
        jCheckBoxInitialized.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxInitializedActionPerformed(evt);
            }
        });

        jCheckBoxSendStatusWithoutRequest.setText("Send Status Without Requests");

        jButtonRestartServer.setText("Restart Server");
        jButtonRestartServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRestartServerActionPerformed(evt);
            }
        });

        jComboBoxRobotType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxRobotTypeActionPerformed(evt);
            }
        });

        jCheckBoxTeleportToGoals.setText("Teleport to Goals");
        jCheckBoxTeleportToGoals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTeleportToGoalsActionPerformed(evt);
            }
        });

        jCheckBoxForceFail.setText("Force Fail");
        jCheckBoxForceFail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxForceFailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 21, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButtonReset)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonRestartServer))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBoxSendStatusWithoutRequest)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jCheckBoxTeleportToGoals)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBoxForceFail))))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jCheckBoxInitialized)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(90, 90, 90))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBoxRobotType, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldCycleTime)
                    .addComponent(jTextFieldPort, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldCycleTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonReset)
                    .addComponent(jButtonRestartServer)
                    .addComponent(jComboBoxRobotType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxInitialized)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxSendStatusWithoutRequest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxTeleportToGoals)
                    .addComponent(jCheckBoxForceFail)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Status"));

        jLabel2.setText("Connected Clients: ");

        jLabel4.setText("Cycle Count:");

        jTextFieldConnectedClients.setEditable(false);
        jTextFieldConnectedClients.setText("0");

        jTextFieldCycleCount.setEditable(false);
        jTextFieldCycleCount.setText("0");

        jLabel5.setText("Number of Waypoints: ");

        jTextFieldNumWaypoints.setEditable(false);
        jTextFieldNumWaypoints.setText("0");

        jLabel6.setText("Current Waypoint:");

        jTextFieldCurWaypoint.setEditable(false);
        jTextFieldCurWaypoint.setText("0");

        jLabel8.setText("End Effector: ");

        jLabel9.setText("Current Commant Type:");

        jButtonPlotGaurdValues.setText("Plot Guard Values");
        jButtonPlotGaurdValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlotGaurdValuesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldEndEffector, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCurrentCommandType, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCurWaypoint, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNumWaypoints, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCycleCount, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldConnectedClients, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jButtonPlotGaurdValues)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextFieldConnectedClients, jTextFieldCurWaypoint, jTextFieldCurrentCommandType, jTextFieldCycleCount, jTextFieldEndEffector, jTextFieldNumWaypoints});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldConnectedClients, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldCycleCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldNumWaypoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCurWaypoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldCurrentCommandType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldEndEffector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jButtonPlotGaurdValues)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private volatile boolean editing_status = false;

//    public String getDocumentation(String name) throws SAXException, IOException, XPathExpressionException, ParserConfigurationException {
//        return xpu.queryXml(statSchemaFiles, "/schema/complexType[@name=\""+name+"\"]/annotation/documentation/text()");
//    }
    private final SimServerInner inner;

    private void showErrorsPopup(MouseEvent evt) {
        JPopupMenu errorsPop = new JPopupMenu();
        JMenuItem clearMi = new JMenuItem("Clear");
        clearMi.addActionListener(e -> {
            jTextAreaErrors.setText("");
            errorsPop.setVisible(false);
        });
        errorsPop.add(clearMi);
        errorsPop.show(evt.getComponent(), evt.getX(), evt.getY());
    }

    @SuppressWarnings("nullness")
    public <T, R> R apply(TryFunction<T, R> f, T b) {
        try {
            return f.apply(b);
        } catch (Exception ex) {
            Logger.getLogger(SimServerJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void close() {
        closeServer();
    }

    private static interface TryFunction<T, R> {

        public R apply(T o) throws Exception;
    };

    public void setSchemaAction() {
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
            setCmdSchema(fa);
            DefaultSchemaFiles defaultsInstance = DefaultSchemaFiles.instance();
            CRCLSchemaUtils.saveCmdSchemaFiles(defaultsInstance.getCmdSchemasFile(), fa);
            setStatSchema(fa);
            CRCLSchemaUtils.saveStatSchemaFiles(defaultsInstance.getStatSchemasFile(), fa);
        }
    }

    public int getPort() {
        return inner.getPort();
    }

    public void viewCommandLogBriefAction() {
        List<CRCLCommandType> l = inner.getCmdLog();
        final CRCLSocket s = this.inner.getCheckerCRCLSocket();
        String string = l.stream()
                .map(c -> apply(CRCLSocket::commandToSimpleString, c))
                .filter(Objects::nonNull)
                .collect(Collectors.joining("\n"));
        this.showMessage(string);
    }

    public void setLogImages(boolean logImages) {
        this.overHeadJPanel1.setLogImages(logImages);
        this.sideViewJPanel1.setLogImages(logImages);
    }

    public void aboutAction() {
        try {
            JOptionPane.showMessageDialog(this, getVersion());
        } catch (IOException ex) {
            Logger.getLogger(SimServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void viewCommandLogFullAction() {
        List<CRCLCommandType> l = inner.getCmdLog();
        final CRCLSocket s = this.inner.getCheckerCRCLSocket();
        String string = l.stream()
                .map((CRCLCommandType c) -> s.commandToPrettyString(c, ""))
                .filter(Objects::nonNull)
                .collect(Collectors.joining("\n"));
        this.showMessage(string);
    }

    private static String createAssertErrorString(CRCLCommandType cmd, long id) {
        return "command id being reduced id=" + id + ", cmd=" + CRCLSocket.cmdToString(cmd);
    }

    private void setCommandId(CRCLCommandType cmd, long id) {
        assert cmd.getCommandID() <= id :
                createAssertErrorString(cmd, id);
        cmd.setCommandID(id);
    }

    private void monitorCrclSocket(CRCLSocket socket) {
        try {
            GetStatusType getStatus = new GetStatusType();
            setCommandId(getStatus, 1);
            CRCLCommandInstanceType cmdInstance = new CRCLCommandInstanceType();
            cmdInstance.setCRCLCommand(getStatus);
            while (!Thread.currentThread().isInterrupted()) {
                CRCLCommandInstanceType gripperCmd = inner.getGripperCmdQueue().poll();
                if (null != gripperCmd) {
                    socket.writeCommand(gripperCmd);
                }
                if (sendGripperStatusRequests) {
                    Thread.sleep(inner.getDelayMillis());
                    setCommandId(getStatus, getStatus.getCommandID() + 1);
                    socket.writeCommand(cmdInstance, false);
                }
                checkMenuOuter();
                CRCLStatusType statusFromGripper
                        = socket.readStatus(inner.isValidateXMLSelected());
                GripperStatusType gripperStatus1 = requireNonNull(statusFromGripper.getGripperStatus(), "statusFromGripper.getGripperStatus()");
                SimServerJPanel.this.getStatus().setGripperStatus(gripperStatus1);
            }
        } catch (CRCLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            showMessage(ex);
        } catch (InterruptedException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void setIncludeGripperAction() {
        try {
            this.closeGripperSocket();
            String gripperPortString = JOptionPane.showInputDialog(this, "Gripper Server Port?", this.gripperPort);
            gripperPort = Integer.parseInt(gripperPortString);
            gripperHost = JOptionPane.showInputDialog(this, "Gripper Server Host?", this.gripperHost);
            gripperPort = Integer.parseInt(gripperPortString);
            sendGripperStatusRequests = (JOptionPane.showConfirmDialog(this, "Send status requests?") == JOptionPane.YES_OPTION);
            CRCLSocket socket = new CRCLSocket(gripperHost, gripperPort);
            this.gripperSocket = socket;
            this.gripperReadThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    monitorCrclSocket(socket);
                }
            }, "simServerReadGripperThread");
            gripperReadThread.start();
            this.inner.setGripperSocket(socket);

        } catch (IOException | CRCLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void editStatusAction() {
        this.editing_status = true;
        try {
            CRCLStatusType newstat
                    = ObjTableJPanel.editObject(this.getStatus(),
                            this.getOuterFrame(),
                            "Edit Status", true,
                            inner.getXpu(),
                            inner.getStatSchemaFiles(),
                            this.checkStatusValidPredicate,
                            CRCLSocket.getUtilSocket());
            if (null != newstat) {
                inner.setStatus(newstat);
                JointStatusesType jsst = this.getStatus().getJointStatuses();
                if (null == jsst) {
                    return;
                }
                Iterable<JointStatusType> jsIterable
                        = CRCLUtils.getNonNullJointStatusIterable(jsst);
                for (JointStatusType jst : jsIterable) {
                    int jvindex = jst.getJointNumber() - 1;
                    final Double jointPosition = jst.getJointPosition();
                    if (null != jointPosition) {
                        double pos = jointPosition;
                        inner.setJointPosition(pos, jvindex);
                        inner.setCommandedJointPosition(pos, jvindex);
                    }
                }
                this.updatePanels(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.editing_status = false;
        }
    }

    @Override
    public boolean isEditingStatus() {
        return editing_status;
    }

    public String getVersion() throws IOException {
        try (
                InputStream is = requireNonNull(ClassLoader.getSystemResourceAsStream("version"), "ClassLoader.getSystemResourceAsStream(\"version\")");
                BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder sb = new StringBuilder();
            String line = null;
            while (null != (line = br.readLine())) {
                sb.append(line);
            }
            sb.append("\nSchema versions = ").append(CRCLSchemaUtils.getSchemaVersions().toString());
            return sb.toString();
        }
    }

    private void checkMenuOuter() {
        if (null == menuOuter) {
            throw new IllegalStateException("SimServerMenuOuter reference is null.");
        }
    }

    private void setCmdSchema(File[] fa) {
        inner.setCmdSchema(fa);
    }

    private void setStatSchema(File[] fa) {
        inner.setStatSchema(fa);
    }

//    public String getStatusXmlString() {
//        try {
//            StringWriter sw = new StringWriter();
//            JAXBContext jc = JAXBContext.newInstance("crcl");
//            Marshaller m = jc.createMarshaller();
//            m.setProperty(Marshaller.JAXB_FRAGMENT, true);
//            m.marshal(jaxb_status, sw);
//            return sw.toString();
//        } catch (JAXBException ex) {
//            Logger.getLogger(SimServerJPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//    JointControlModeEnumType controlmodes[] = new JointControlModeEnumType[]{
//        JointControlModeEnumType.POSITION,
//        JointControlModeEnumType.POSITION,
//        JointControlModeEnumType.POSITION,
//        JointControlModeEnumType.POSITION,
//        JointControlModeEnumType.POSITION,
//        JointControlModeEnumType.POSITION,};
    public void setCommandState(CommandStateEnumType state) {
        inner.setCommandState(state);
    }

    public CommandStateEnumType getCommandState() {
        return inner.getCommandState();
    }

    public boolean checkPose(PoseType goalPose) {
        return inner.checkPose(goalPose);
    }

    @Override
    public void updateCycleCount(int _newCycleCount) {
        this.jTextFieldCycleCount.setText(Integer.toString(_newCycleCount));
    }

    private final Function<CRCLStatusType, XFuture<Boolean>> checkStatusValidPredicate = new Function<CRCLStatusType, XFuture<Boolean>>() {

        @Override
        public XFuture<Boolean> apply(CRCLStatusType t) {
            return checkStatusValid(t);
        }
    };

//            = this::checkStatusValid;
    public XFuture<Boolean> checkStatusValid(CRCLStatusType statusObj) {
        try {
            String s = inner.getCheckerCRCLSocket().statusToPrettyString(statusObj, true);
            return MultiLineStringJPanel.showText(s);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            showMessage(ex);
        }
        return XFuture.completedFuture(false);
    }

    public boolean isSendStatusWithoutRequestSelected() {
        return jCheckBoxSendStatusWithoutRequest.isSelected();
    }

    public void showMessage(Throwable t) {
        showMessage(t.toString());
    }

    private volatile long last_message_show_time = 0;

    @Override
    public void showDebugMessage(final String s) {
        final String sWithThread = "Thread:" + Thread.currentThread().getName() + " \n" + s;
        LOGGER.log(Level.FINE, sWithThread);
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                if (jTextAreaErrors.getText().length() > 50000) {
                    jTextAreaErrors.setText(sWithThread);
                } else {
                    jTextAreaErrors.append("\n" + sWithThread);
                }
            }
        });
    }

    @Override
    public void updateNumWaypoints(int numWaypoints) {
        this.jTextFieldNumWaypoints.setText(Integer.toString(numWaypoints));
    }

    private @Nullable
    JFrame outerFrame;
    private boolean searchedForOuterFrame = false;

    private @Nullable
    JFrame searchForOuterFrame() {
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
        return null;
    }

    /**
     * Get the value of outerFrame
     *
     * @return the value of outerFrame
     */
    public @Nullable
    JFrame getOuterFrame() {
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

    @Override
    public void showMessage(final String s) {
        showDebugMessage(s);
        inner.setStateDescription(s);
        if (showing_message) {
            return;
        }
        showing_message = true;
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                long t = System.currentTimeMillis();
                if (t - last_message_show_time > 500) {
                    last_message_show_time = System.currentTimeMillis();
                    MultiLineStringJPanel.showText(s, getOuterFrame(), "SimServer Message", true);
                }
                last_message_show_time = System.currentTimeMillis();
                showing_message = false;
            }
        });
    }

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

    private void updatePanelsPrivate() {
        double[] jointPositions = inner.getJointPositions();
        if (null != jointPositions) {
            this.overHeadJPanel1.setJointvals(jointPositions);
            this.sideViewJPanel1.setJointvals(jointPositions);
        }
        double[] seglengths = inner.getSeglengths();
        if (null != seglengths) {
            this.overHeadJPanel1.setSeglengths(seglengths);
            this.sideViewJPanel1.setSeglengths(seglengths);
        }

        this.overHeadJPanel1.repaint();
        this.sideViewJPanel1.repaint();
    }

    @Override
    public void updatePanels(boolean jointschanged) {
        if (jointschanged) {
            updatePanelsPrivate();
        }
    }

    @Override
    public void finishSetCurrentWaypoint(int currentWaypoint) {
        this.jTextFieldCurWaypoint.setText(Integer.toString(currentWaypoint));
    }

    @Override
    public void updateIsInitialized(boolean initialized) {
        this.jCheckBoxInitialized.setSelected(initialized);
    }

    @Override
    public void updateCurrentCommandType(String cmdName) {
        this.jTextFieldCurrentCommandType.setText(cmdName);
    }
    private boolean showing_message = false;

    public boolean isInitializedSelected() {
        return jCheckBoxInitialized.isSelected();
    }

    @Override
    public void updateEndEffector(String text) {
        this.jTextFieldEndEffector.setText(text);
    }

    @Override
    public void updateToolChangerIsOpen(boolean isOpen) {
        this.setToolChangerOpen(isOpen);
    }

//    @Override
//    public void updateLengthUnit(LengthUnitEnumType lu) {
//        this.lengthUnitComboBox.setSelectedItem(lu);
//    }
    @Override
    public void updateConnectedClients(int numClients) {
        this.jTextFieldConnectedClients.setText(Integer.toString(numClients));
    }

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

    private void jTextFieldPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPortActionPerformed
        int new_port = Integer.parseInt(this.jTextFieldPort.getText());
        inner.closeServer();
        inner.setPort(new_port);
//        inner.restartServer(inner.getServerIsDaemon());
    }//GEN-LAST:event_jTextFieldPortActionPerformed

    private void jTextFieldCycleTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCycleTimeActionPerformed
        long newDelayMillis = Long.parseLong(this.jTextFieldCycleTime.getText());
        inner.setDelayMillis(newDelayMillis);
    }//GEN-LAST:event_jTextFieldCycleTimeActionPerformed

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        inner.reset();
        this.updatePanels(true);
    }//GEN-LAST:event_jButtonResetActionPerformed

    private void jCheckBoxInitializedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxInitializedActionPerformed
        if (this.jCheckBoxInitialized.isSelected()) {
            this.inner.initialize();
        }
    }//GEN-LAST:event_jCheckBoxInitializedActionPerformed

    private void jButtonRestartServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRestartServerActionPerformed
        int new_port = Integer.parseInt(this.jTextFieldPort.getText());
        inner.closeServer();
        new Thread(() -> {
            inner.setPort(new_port);
            inner.restartServer(inner.getServerIsDaemon());
        }).start();
    }//GEN-LAST:event_jButtonRestartServerActionPerformed

    private void jComboBoxRobotTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRobotTypeActionPerformed
        SimRobotEnum robotType = SimRobotEnum.valueOf(this.jComboBoxRobotType.getSelectedItem().toString());
        this.setRobotType(robotType);
    }//GEN-LAST:event_jComboBoxRobotTypeActionPerformed

    private void jCheckBoxTeleportToGoalsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTeleportToGoalsActionPerformed
        this.inner.setTeleportToGoals(this.jCheckBoxTeleportToGoals.isSelected());
    }//GEN-LAST:event_jCheckBoxTeleportToGoalsActionPerformed

    private void jCheckBoxForceFailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxForceFailActionPerformed
        this.inner.setForceFail(jCheckBoxForceFail.isSelected());
    }//GEN-LAST:event_jCheckBoxForceFailActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItemSavePropertiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSavePropertiesActionPerformed
        JFileChooser chooser = new JFileChooser();
        if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(this)) {
            setPropertiesFile(chooser.getSelectedFile());
            try {
                saveProperties();
            } catch (IOException ex) {
                Logger.getLogger(SimServerJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jMenuItemSavePropertiesActionPerformed

    private void jMenuItemLoadPropertiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLoadPropertiesActionPerformed
        JFileChooser chooser = new JFileChooser();
        if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(this)) {
            try {
                setPropertiesFile(chooser.getSelectedFile());
                loadProperties();
            } catch (IOException ex) {
                Logger.getLogger(SimServerJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jMenuItemLoadPropertiesActionPerformed

    private void jMenuItemSaveStatusToFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaveStatusToFileActionPerformed
        try {
            JFileChooser chooser;
            File propertiesFileToSave = this.getPropertiesFile();

            if (propertiesFileToSave != null) {
                final File parentFile = propertiesFileToSave.getParentFile();
                if (null != parentFile) {
                    chooser = new JFileChooser(parentFile);
                } else {
                    chooser = new JFileChooser();
                }
            } else {
                chooser = new JFileChooser();
            }
            FileFilter xmlFilter = new FileNameExtensionFilter("XML", "xml");
            chooser.addChoosableFileFilter(xmlFilter);
            chooser.setFileFilter(xmlFilter);
            if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(this)) {
                File f = chooser.getSelectedFile();
                String name = f.getName();
                final File fParentFile = f.getParentFile();
                if (!name.endsWith(".xml")) {
                    name = name + ".xml";
                    f = new File(fParentFile, name);
                }
                try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {
                    pw.println(CRCLSocket.statusToPrettyString(this.getStatus()));
                }
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Set as IntialStatus file?")) {
                    if (null != propertiesFileToSave) {
                        File propertiesParentFile = propertiesFileToSave.getParentFile();
                        if (null != propertiesParentFile
                                && null != fParentFile
                                && !Objects.equals(propertiesParentFile.getCanonicalPath(),
                                        fParentFile.getCanonicalPath()
                                )) {
                            File copy = File.createTempFile(name, ".xml", propertiesParentFile);
                            Files.copy(f.toPath(), copy.toPath());
                            this.setInitStatusFilename(copy.getName());
                        } else {
                            this.setInitStatusFilename(name);
                        }
                    } else {
                        this.setInitStatusFilename(name);
                    }
                    this.saveProperties();
                }
            }
        } catch (Exception exception) {
            Logger.getLogger(SimServerJInternalFrame.class.getName()).log(Level.SEVERE, null, exception);
        }
    }//GEN-LAST:event_jMenuItemSaveStatusToFileActionPerformed

    private void jMenuItemSetInitalStatusFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSetInitalStatusFileActionPerformed
        try {
            JFileChooser chooser;
            File propertiesFile = this.getPropertiesFile();
            if (propertiesFile != null) {
                final File parentFile = propertiesFile.getParentFile();
                if (null != parentFile) {
                    chooser = new JFileChooser(parentFile);
                } else {
                    chooser = new JFileChooser();
                }
            } else {
                chooser = new JFileChooser();
            }
            FileFilter xmlFilter = new FileNameExtensionFilter("XML", "xml");
            chooser.addChoosableFileFilter(xmlFilter);
            chooser.setFileFilter(xmlFilter);
            if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(this)) {
                File f = chooser.getSelectedFile();
                String name = f.getName();
                if (null != propertiesFile) {
                    final File propertiesParentFile = propertiesFile.getParentFile();
                    final File fParentFile = f.getParentFile();
                    if (null != propertiesParentFile
                            && null != fParentFile
                            && !Objects.equals(propertiesParentFile.getCanonicalPath(),
                                    fParentFile.getCanonicalPath()
                            )) {
                        File copy = File.createTempFile(name, ".xml", propertiesParentFile);
                        Files.copy(f.toPath(), copy.toPath());
                        this.setInitStatusFilename(copy.getName());
                    } else {
                        this.setInitStatusFilename(name);
                    }
                } else {
                    this.setInitStatusFilename(name);
                }
                this.saveProperties();
            }
        } catch (Exception exception) {
            Logger.getLogger(SimServerJInternalFrame.class.getName()).log(Level.SEVERE, null, exception);
        }
    }//GEN-LAST:event_jMenuItemSetInitalStatusFileActionPerformed

    private void jMenuItemEditStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditStatusActionPerformed
        this.editStatusAction();
    }//GEN-LAST:event_jMenuItemEditStatusActionPerformed

    private void jMenuItemSetSchemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSetSchemaActionPerformed
        this.setSchemaAction();
    }//GEN-LAST:event_jMenuItemSetSchemaActionPerformed

    private void jCheckBoxMenuItemValidateXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemValidateXMLActionPerformed
        this.setValidateXMLSelected(jCheckBoxMenuItemValidateXML.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemValidateXMLActionPerformed

    private void jCheckBoxMenuItemIncludeGripperStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemIncludeGripperStatusActionPerformed
        if (this.jCheckBoxMenuItemIncludeGripperStatus.isSelected()) {
            this.setIncludeGripperAction();
        }
    }//GEN-LAST:event_jCheckBoxMenuItemIncludeGripperStatusActionPerformed

    private void jCheckBoxMenuItemEXIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemEXIActionPerformed
        this.restartServer();
    }//GEN-LAST:event_jCheckBoxMenuItemEXIActionPerformed

    private void jCheckBoxMenuItemLogImagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemLogImagesActionPerformed
        //        this.overHeadJPanel1.setLogImages(this.jCheckBoxMenuItemLogImages.isSelected());
        //        this.sideViewJPanel1.setLogImages(this.jCheckBoxMenuItemLogImages.isSelected());
        this.setLogImages(jCheckBoxMenuItemLogImages.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemLogImagesActionPerformed

    private void jCheckBoxMenuItemDisableTextPopupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDisableTextPopupsActionPerformed
        crcl.ui.misc.MultiLineStringJPanel.disableShowText = jCheckBoxMenuItemDisableTextPopups.isSelected();
    }//GEN-LAST:event_jCheckBoxMenuItemDisableTextPopupsActionPerformed

    private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
        this.aboutAction();
    }//GEN-LAST:event_jMenuItemAboutActionPerformed

    private void jMenuItemViewCommandLogBriefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemViewCommandLogBriefActionPerformed
        this.viewCommandLogBriefAction();
    }//GEN-LAST:event_jMenuItemViewCommandLogBriefActionPerformed

    private void jMenuItemViewCommandLogFullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemViewCommandLogFullActionPerformed
        this.viewCommandLogFullAction();
    }//GEN-LAST:event_jMenuItemViewCommandLogFullActionPerformed

    private void jButtonPlotGaurdValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlotGaurdValuesActionPerformed
        try {
            diagapplet.plotter.plotterJFrame plotJFrame
                    = new diagapplet.plotter.plotterJFrame();
            final CRCLServerSocket<?> innerCrclServerSocketLocal
                    = inner.getCrclServerSocket();
            if (null != innerCrclServerSocketLocal) {
                final List<GuardHistoryElement> guardHistoryList = innerCrclServerSocketLocal.getGuardHistoryList();
                plotJFrame.LoadObjectsList("", guardHistoryList);
            }
            plotJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            plotJFrame.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(SimServerJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonPlotGaurdValuesActionPerformed

    public boolean isDebugMoveDoneSelected() {
        return this.jCheckBoxMenuItemDebugMoveDone.isSelected();
    }

    public boolean isDebugReadCommandSelected() {
        return this.jCheckBoxMenuItemDebugReadCommand.isSelected();
    }

    public boolean isDebugSendStatusSelected() {
        return this.jCheckBoxMenuItemDebugSendStatus.isSelected();
    }

    public boolean isAppendZeroSelected() {
        return jCheckBoxMenuItemAppendZero.isSelected();
    }

    public boolean isReplaceXmlHeaderSelected() {
        return jCheckBoxMenuItemReplaceXmlHeader.isSelected();
    }

    public boolean isRandomPacketSelected() {
        return jCheckBoxMenuItemRandomPacket.isSelected();
    }

//    public boolean isReplaceStateSelected() {
//        return jCheckBoxMenuItemReplaceState.isSelected();
//    }

    public boolean isEXISelected() {
        return jCheckBoxMenuItemEXI.isSelected();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPlotGaurdValues;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonRestartServer;
    private javax.swing.JCheckBox jCheckBoxForceFail;
    private javax.swing.JCheckBox jCheckBoxInitialized;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemAppendZero;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebugMoveDone;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebugReadCommand;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebugSendStatus;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDisableTextPopups;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemEXI;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemIncludeGripperStatus;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemLogImages;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemRandomPacket;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemReplaceXmlHeader;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemValidateXML;
    private javax.swing.JCheckBox jCheckBoxSendStatusWithoutRequest;
    private javax.swing.JCheckBox jCheckBoxTeleportToGoals;
    private javax.swing.JComboBox<SimRobotEnum> jComboBoxRobotType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    public final javax.swing.JMenuBar jMenuBarSimServer = new javax.swing.JMenuBar();
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemEditStatus;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemLoadProperties;
    private javax.swing.JMenuItem jMenuItemSaveProperties;
    private javax.swing.JMenuItem jMenuItemSaveStatusToFile;
    private javax.swing.JMenuItem jMenuItemSetInitalStatusFile;
    private javax.swing.JMenuItem jMenuItemSetSchema;
    private javax.swing.JMenuItem jMenuItemViewCommandLogBrief;
    private javax.swing.JMenuItem jMenuItemViewCommandLogFull;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaErrors;
    private javax.swing.JTextField jTextFieldConnectedClients;
    private javax.swing.JTextField jTextFieldCurWaypoint;
    private javax.swing.JTextField jTextFieldCurrentCommandType;
    private javax.swing.JTextField jTextFieldCycleCount;
    private javax.swing.JTextField jTextFieldCycleTime;
    private javax.swing.JTextField jTextFieldEndEffector;
    private javax.swing.JTextField jTextFieldNumWaypoints;
    private javax.swing.JTextField jTextFieldPort;
    private crcl.ui.server.OverHeadJPanel overHeadJPanel1;
    private crcl.ui.server.SideViewJPanel sideViewJPanel1;
    // End of variables declaration//GEN-END:variables
}
