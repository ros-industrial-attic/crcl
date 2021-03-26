/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wshackle.fanuccrclservermain;

import com.github.wshackle.fanuc.robotserver.FREExecuteConstants;
import com.github.wshackle.fanuc.robotserver.FREStepTypeConstants;
import com.github.wshackle.fanuc.robotserver.ITPProgram;
import com.github.wshackle.fanuc.robotserver.IVar;
import crcl.ui.misc.ServerSensorJFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class FanucCRCLServerJInternalFrame extends javax.swing.JInternalFrame implements FanucCRCLServerDisplayInterface {

    /**
     * Creates new form FanucCRCLServerJInternalFrame
     */
    @SuppressWarnings("initialization")
    public FanucCRCLServerJInternalFrame() {
        initComponents();
        jCheckBoxMenuItemDebug.setSelected(FanucCRCLMain.isDebug());
    }

    @Override
    public IVar getOverrideVar() {
        return fanucCRCLServerJPanel1.getOverrideVar();
    }

    @Override
    public void setMorSafetyStatVar(IVar morSafetyStatVar) {
        fanucCRCLServerJPanel1.setMorSafetyStatVar(morSafetyStatVar);
    }

    @Override
    public @Nullable
    IVar getMorSafetyStatVar() {
        return fanucCRCLServerJPanel1.getMorSafetyStatVar();
    }

    @Override
    public void setMoveGroup1ServoReadyVar(IVar var) {
        fanucCRCLServerJPanel1.setMoveGroup1ServoReadyVar(var);
    }

    @Override
    public @Nullable
    IVar getMoveGroup1ServoReadyVar() {
        return fanucCRCLServerJPanel1.getMoveGroup1ServoReadyVar();
    }

    /**
     * Set the value of overrideVar
     *
     * @param overrideVar new value of overrideVar
     */
    @Override
    public void setOverrideVar(IVar overrideVar) {
        fanucCRCLServerJPanel1.setOverrideVar(overrideVar);
    }

    private void launchClient() {
        fanucCRCLServerJPanel1.launchClient();
    }

    private void launchPressureSensorServer() {
        fanucCRCLServerJPanel1.launchPressureSensorServer();
    }

    @SuppressWarnings("nullness")
    private static Image createImage(Dimension d, Color bgColor, Color textColor, Image baseImage) {
        BufferedImage bi = new BufferedImage(d.width, d.height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = bi.createGraphics();
        g2d.setBackground(bgColor);
        g2d.setColor(textColor);
        g2d.clearRect(0, 0, d.width, d.height);
        g2d.setFont(new Font(g2d.getFont().getName(), g2d.getFont().getStyle(), 24));
        g2d.drawImage(baseImage, 0, 0, null);
        bi.flush();
        return bi;
    }

    public static Image getRobotImage() {
        final Image img;
        try {
            final URL robotImageSystemResourceUrl
                    = Objects.requireNonNull(ClassLoader.getSystemResource("robot.png"), "ClassLoader.getSystemResource(\"robot.png\")");
            img = ImageIO.read(robotImageSystemResourceUrl);
        } catch (IOException ex) {
            Logger.getLogger(FanucCRCLServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
        return img;
    }

    private static final Dimension ICON_SIZE = new Dimension(32, 32);
    private static final Image BASE_IMAGE = Objects.requireNonNull(getRobotImage(), "BASE_IMAGE");
    public static final Image SERVER_IMAGE = Objects.requireNonNull(createImage(ICON_SIZE, Color.MAGENTA, Color.BLACK, BASE_IMAGE), "SERVER_IMAGE");

//    private void shutDown() {
//        fanucCRCLServerJPanel1.shutDown();
//    }
    private void launchWebServer() {
        fanucCRCLServerJPanel1.launchWebServer();
    }

    @Override
    public JTextField getjTextFieldHostName() {
        return fanucCRCLServerJPanel1.getjTextFieldHostName();
    }

    public void saveProperties() {
        fanucCRCLServerJPanel1.saveProperties();
    }

    public void setPrograms(List<ITPProgram> _programs) {
        fanucCRCLServerJPanel1.setPrograms(_programs);
        List<ITPProgram> programs = fanucCRCLServerJPanel1.getPrograms();
        this.jMenuRunProgram.removeAll();
        this.jMenuConvertProgram.removeAll();
        if (null != programs) {
            synchronized (programs) {
                Collections.sort(programs, Comparator.comparing(ITPProgram::name));
                for (ITPProgram p : programs) {
                    JMenuItem jmi = new JMenuItem(p.name());
                    jmi.addActionListener(e -> {
                        runReconnectAction(e);
                        if (JOptionPane.showConfirmDialog(FanucCRCLServerJInternalFrame.this.getParent(),
                                "Run " + p.name()) == JOptionPane.YES_OPTION) {
                            p.run(FREStepTypeConstants.frStepNone, 1, FREExecuteConstants.frExecuteFwd);
                            runReconnectAction(e);
                        }
                    });
                    this.jMenuRunProgram.add(jmi);
                    jmi = new JMenuItem(p.name());
                    jmi.addActionListener(e -> {
                        ConvertProgram(p);
                    });
                    this.jMenuConvertProgram.add(jmi);
                }
            }
        }
    }

    public void ConvertProgram(ITPProgram program) {
        fanucCRCLServerJPanel1.ConvertProgram(program);
    }

    public void runReconnectAction(ActionEvent e) {
        for (ActionListener al : jMenuItemReconnectRobot.getActionListeners()) {
            al.actionPerformed(e);
        }
    }

    public @Nullable
    File getPropertiesFile() {
        return fanucCRCLServerJPanel1.getPropertiesFile();
    }

    public void setPropertiesFile(File propertiesFile) {
        fanucCRCLServerJPanel1.setPropertiesFile(propertiesFile);
    }

    public void loadProperties() {
        fanucCRCLServerJPanel1.loadProperties();
    }

    public void setMain(FanucCRCLMain _main) {
        fanucCRCLServerJPanel1.setMain(_main);
    }

    public JSlider getjSliderOverride() {
        return fanucCRCLServerJPanel1.getjSliderOverride();
    }

    public JSlider getjSliderMaxOverride() {
        return fanucCRCLServerJPanel1.getjSliderMaxOverride();
    }

    public JRadioButton getjRadioButtonUseDirectIP() {
        return fanucCRCLServerJPanel1.getjRadioButtonUseDirectIP();
    }

    public JRadioButton getjRadioButtonUseRobotNeighborhood() {
        return fanucCRCLServerJPanel1.getjRadioButtonUseRobotNeighborhood();
    }

    public JTextField getjTextFieldRobotNeighborhoodPath() {
        return fanucCRCLServerJPanel1.getjTextFieldRobotNeighborhoodPath();
    }

    public JTextField getjTextFieldLimitSafetyBumper() {
        return fanucCRCLServerJPanel1.getjTextFieldLimitSafetyBumper();
    }

    public void setConnected(boolean _connected) {
        fanucCRCLServerJPanel1.setConnected(_connected);
    }

    public JTextArea getjTextAreaErrors() {
        return fanucCRCLServerJPanel1.getjTextAreaErrors();
    }

    public JCheckBox getjCheckBoxLogAllCommands() {
        return fanucCRCLServerJPanel1.getjCheckBoxLogAllCommands();
    }

    public void updatePerformanceString(String s) {
        fanucCRCLServerJPanel1.updatePerformanceString(s);
    }

    public void setPreferRobotNeighborhood(boolean b) {
        fanucCRCLServerJPanel1.setPreferRobotNeighborhood(b);
    }

    public void updateCartesianLimits(float xMax,
            float xMin,
            float yMax,
            float yMin,
            float zMax,
            float zMin) {
        fanucCRCLServerJPanel1.updateCartesianLimits(xMax, xMin, yMax, yMin, zMax, zMin);
    }

    public void updateJointLimits(float lowerJointLimits[], float upperJointLimits[]) {
        fanucCRCLServerJPanel1.updateJointLimits(lowerJointLimits, upperJointLimits);
    }

    private void readPropertiesFile() {
//        try {
//            if (PROPERTIES_FILE.exists()) {
//                props.load(new FileReader(PROPERTIES_FILE));
//            }
//            fingerSensorServerCmd = getProperty("fingerSensorServerCmd", DEFAULT_FINGER_SENSOR_SERVER_COMMAND);
//            fingerSensorServerDirectory = getProperty("fingerSensorServerDirectory", DEFAULT_FINGER_SENSOR_SERVER_DIRECTORY);
//            jCheckBoxMenuItemStartClient.setSelected(getBooleanProperty("autoStartClient", false));
//            jCheckBoxMenuItemStartPressureServer.setSelected(getBooleanProperty("autoStartPressureSensorServer", false));
//            jCheckBoxMenuItemShowPressureOutput.setSelected(getBooleanProperty("showPressureOutput", false));
//        } catch (IOException ex) {
//            Logger.getLogger(FanucCRCLServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public JMenuItem getjMenuItemReconnectRobot() {
        return jMenuItemReconnectRobot;
    }

    public JMenuItem getjMenuItemResetAlarms() {
        return jMenuItemResetAlarms;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fanucCRCLServerJPanel1 = new com.github.wshackle.fanuccrclservermain.FanucCRCLServerJPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemResetAlarms = new javax.swing.JMenuItem();
        jMenuItemReconnectRobot = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuRunProgram = new javax.swing.JMenu();
        jMenuConvertProgram = new javax.swing.JMenu();
        jMenuTools = new javax.swing.JMenu();
        jMenuItemLaunchClient = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jCheckBoxMenuItemShowPressureOutput = new javax.swing.JCheckBoxMenuItem();
        jMenuItemLaunchWeb = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jCheckBoxMenuItemStartClient = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemStartPressureServer = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDebug = new javax.swing.JCheckBoxMenuItem();

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Fanuc CRCL Server");

        jMenu1.setText("File");

        jMenuItemResetAlarms.setText("Reset Alarms");
        jMenu1.add(jMenuItemResetAlarms);

        jMenuItemReconnectRobot.setText("Reconnect Robot");
        jMenu1.add(jMenuItemReconnectRobot);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenuRunProgram.setText("Run Program");
        jMenuBar1.add(jMenuRunProgram);

        jMenuConvertProgram.setText("Convert Program");
        jMenuBar1.add(jMenuConvertProgram);

        jMenuTools.setText("Tools");

        jMenuItemLaunchClient.setText("Launch Client");
        jMenuItemLaunchClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLaunchClientActionPerformed(evt);
            }
        });
        jMenuTools.add(jMenuItemLaunchClient);

        jMenuItem1.setText("Launch Finger Pressure Sensor Server");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuTools.add(jMenuItem1);

        jCheckBoxMenuItemShowPressureOutput.setSelected(true);
        jCheckBoxMenuItemShowPressureOutput.setText("Show Finger Pressure Sensor Output");
        jCheckBoxMenuItemShowPressureOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemShowPressureOutputActionPerformed(evt);
            }
        });
        jMenuTools.add(jCheckBoxMenuItemShowPressureOutput);

        jMenuItemLaunchWeb.setText("Launch Web Server/Application");
        jMenuItemLaunchWeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLaunchWebActionPerformed(evt);
            }
        });
        jMenuTools.add(jMenuItemLaunchWeb);

        jMenuBar1.add(jMenuTools);

        jMenu3.setText("Options");

        jCheckBoxMenuItemStartClient.setText("Start Client on Startup");
        jCheckBoxMenuItemStartClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemStartClientActionPerformed(evt);
            }
        });
        jMenu3.add(jCheckBoxMenuItemStartClient);

        jCheckBoxMenuItemStartPressureServer.setText("Start Pressure Sensor Server on Startup");
        jMenu3.add(jCheckBoxMenuItemStartPressureServer);

        jCheckBoxMenuItemDebug.setText("Debug");
        jCheckBoxMenuItemDebug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDebugActionPerformed(evt);
            }
        });
        jMenu3.add(jCheckBoxMenuItemDebug);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fanucCRCLServerJPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fanucCRCLServerJPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemLaunchClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLaunchClientActionPerformed
        launchClient();
    }//GEN-LAST:event_jMenuItemLaunchClientActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        launchPressureSensorServer();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jCheckBoxMenuItemShowPressureOutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemShowPressureOutputActionPerformed
        throw new UnsupportedOperationException("pressure sensor server not implemented");
//        ServerSensorJFrame serverSensorJFrame = fanucCRCLServerJPanel1.getSensorJFrame();
//        if (null != serverSensorJFrame) {
//            serverSensorJFrame.setVisible(jCheckBoxMenuItemShowPressureOutput.isSelected());
//        }
//        saveProperties();
    }//GEN-LAST:event_jCheckBoxMenuItemShowPressureOutputActionPerformed

    private void jMenuItemLaunchWebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLaunchWebActionPerformed
        launchWebServer();
    }//GEN-LAST:event_jMenuItemLaunchWebActionPerformed

    private void jCheckBoxMenuItemStartClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemStartClientActionPerformed
        saveProperties();
    }//GEN-LAST:event_jCheckBoxMenuItemStartClientActionPerformed

    private void jCheckBoxMenuItemDebugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDebugActionPerformed
        FanucCRCLMain.setDebug(jCheckBoxMenuItemDebug.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemDebugActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.github.wshackle.fanuccrclservermain.FanucCRCLServerJPanel fanucCRCLServerJPanel1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebug;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemShowPressureOutput;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemStartClient;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemStartPressureServer;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuConvertProgram;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemLaunchClient;
    private javax.swing.JMenuItem jMenuItemLaunchWeb;
    private javax.swing.JMenuItem jMenuItemReconnectRobot;
    private javax.swing.JMenuItem jMenuItemResetAlarms;
    private javax.swing.JMenu jMenuRunProgram;
    private javax.swing.JMenu jMenuTools;
    // End of variables declaration//GEN-END:variables
}
