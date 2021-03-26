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

import crcl.base.CRCLStatusType;
import crcl.utils.outer.interfaces.SimServerMenuOuter;
import crcl.utils.outer.interfaces.SimServerOuter;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class SimServerJInternalFrame extends javax.swing.JInternalFrame implements SimServerOuter, SimServerMenuOuter, AutoCloseable {

    /**
     * Creates new form SimServerJInternalFrame
     */
    @SuppressWarnings("initialization")
    public SimServerJInternalFrame() {
        simServerJPanel1 = new crcl.ui.server.SimServerJPanel();

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("CRCL Simulation Server");

        setJMenuBar(simServerJPanel1.jMenuBarSimServer);
        add(simServerJPanel1);
        pack();
        this.simServerJPanel1.setMenuOuter(this);
    }
    
    public boolean isRunning() {
        return this.simServerJPanel1.isRunning();
    }

    public void restartServer() {
        this.simServerJPanel1.restartServer();
    }

    public CRCLStatusType getStatus() {
        return simServerJPanel1.getStatus();
    }

    @Override
    public boolean isDebugMoveDoneSelected() {
        return this.simServerJPanel1.isDebugMoveDoneSelected();
    }

    @Override
    public boolean isDebugReadCommandSelected() {
        return this.simServerJPanel1.isDebugReadCommandSelected();
    }

    @Override
    public boolean isDebugSendStatusSelected() {
        return this.simServerJPanel1.isDebugSendStatusSelected();
    }

    @Override
    public void updateConnectedClients(int numClients) {
        simServerJPanel1.updateConnectedClients(numClients);
    }

    @Override
    public void updateCycleCount(int _newCycleCount) {
        simServerJPanel1.updateCycleCount(_newCycleCount);
    }

    @Override
    public void updatePanels(boolean jointschanged) {
        simServerJPanel1.updatePanels(jointschanged);
    }

    @Override
    public void updateIsInitialized(boolean initialized) {
        simServerJPanel1.updateIsInitialized(initialized);
    }

    @Override
    public void updateCurrentCommandType(String cmdName) {
        simServerJPanel1.updateCurrentCommandType(cmdName);
    }

    @Override
    public void updateEndEffector(String text) {
        simServerJPanel1.updateEndEffector(text);
    }

    @Override
    public void updateToolChangerIsOpen(boolean isOpen) {
        simServerJPanel1.updateToolChangerIsOpen(isOpen);
    }

    @Override
    public void showMessage(String msgString) {
        simServerJPanel1.showMessage(msgString);
    }

    @Override
    public void finishSetCurrentWaypoint(int currentWaypoint) {
        simServerJPanel1.finishSetCurrentWaypoint(currentWaypoint);
    }

//    @Override
//    public void updateLengthUnit(LengthUnitEnumType lu) {
//        simServerJPanel1.updateLengthUnit(lu);
//    }
    @Override
    public void showDebugMessage(String s) {
        simServerJPanel1.showDebugMessage(s);
    }

    @Override
    public void updateNumWaypoints(int numWaypoints) {
        simServerJPanel1.updateNumWaypoints(numWaypoints);
    }

    @Override
    public boolean isInitializedSelected() {
        return simServerJPanel1.isInitializedSelected();
    }

    @Override
    public boolean isSendStatusWithoutRequestSelected() {
        return simServerJPanel1.isSendStatusWithoutRequestSelected();
    }

    @Override
    public boolean isAppendZeroSelected() {
        return simServerJPanel1.isAppendZeroSelected();
    }

    @Override
    public boolean isReplaceXmlHeaderSelected() {
        return simServerJPanel1.isReplaceXmlHeaderSelected();
    }

    @Override
    public boolean isRandomPacketSelected() {
        return simServerJPanel1.isRandomPacketSelected();
    }

//    @Override
//    public boolean isReplaceStateSelected() {
//        return simServerJPanel1.isReplaceStateSelected();
//    }

    @Override
    public boolean isEditingStatus() {
        return simServerJPanel1.isEditingStatus();
    }

    @Override
    public boolean isEXISelected() {
        return simServerJPanel1.isEXISelected();
    }

    public boolean isValidateXMLSelected() {
        return simServerJPanel1.isValidateXMLSelected();
    }

    public int getPort() {
        return simServerJPanel1.getPort();
    }

    @Override
    public SimServerMenuOuter getMenuOuter() {
        return this;
    }

    public File getPropertiesFile() {
        return simServerJPanel1.getPropertiesFile();
    }

    public void setPropertiesFile(File f) {
        simServerJPanel1.setPropertiesFile(f);
    }

    public void loadProperties() throws IOException {
        simServerJPanel1.loadProperties();

    }

    public void saveProperties() throws IOException {
        simServerJPanel1.saveProperties();
    }

    private final crcl.ui.server.SimServerJPanel simServerJPanel1;

    @Override
    public void close() {
        simServerJPanel1.closeServer();
        this.setVisible(false);
    }
}
