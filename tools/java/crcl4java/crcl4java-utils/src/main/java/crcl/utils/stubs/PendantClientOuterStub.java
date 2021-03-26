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
package crcl.utils.stubs;

import crcl.utils.outer.interfaces.PendantClientOuter;
import crcl.base.CRCLProgramType;
import crcl.base.CRCLStatusType;
import crcl.base.MiddleCommandType;
import crcl.base.PoseType;
import crcl.utils.CRCLException;
import crcl.utils.CRCLSocket;
import crcl.utils.outer.interfaces.CommandStatusLogElement;
import crcl.utils.outer.interfaces.PendantClientMenuOuter;
import crcl.utils.outer.interfaces.ProgramRunData;
import java.io.File;
import java.util.Deque;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

@SuppressWarnings("nullness")
public class PendantClientOuterStub implements PendantClientOuter, PendantClientMenuOuter {

    @Override
    public void showMessage(String s) {
        final String sWithThread = "Thread:" + Thread.currentThread().getName() + " " + s;
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, sWithThread);
    }

    @Override
    public void showMessage(Throwable t) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, t.toString());
    }

    @Override
    public boolean showDebugMessage(String s) {
        showMessage(s);
        return false;
    }

    private int currentProgramLine;

    @Override
    public int getCurrentProgramLine() {
        return currentProgramLine;
    }

//    private final boolean replaceState;
//
//    @Override
//    public boolean replaceStateSelected() {
//        return this.replaceState;
//    }
    private final String host;

    @Override
    public String getHost() {
        return this.host;
    }

    private final int port;

    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public void finishDisconnect() {
    }

    @Override
    public void finishConnect() {
    }

    @Override
    public void finishSetStatus() {
    }

    @Override
    public void checkXmlQuery(CRCLSocket crclSocket) {
    }

    @Override
    public void stopPollTimer() {
    }

    @Override
    public void checkPollSelected() {
    }

    private final boolean debugWaitForDone;

    @Override
    public boolean isDebugWaitForDoneSelected() {
        return this.debugWaitForDone;
    }

    private final boolean debugSendCommand;

    @Override
    public boolean isDebugSendCommandSelected() {
        return this.debugSendCommand;
    }

    private final boolean debugReadStatus;

    @Override
    public boolean isDebugReadStatusSelected() {
        return this.debugReadStatus;
    }

    @Override
    public void showCurrentProgramLine(int line, CRCLProgramType program, CRCLStatusType status, List<ProgramRunData> progRunDataList) {
        currentProgramLine = line;
    }

    @Override
    public void finishOpenXmlProgramFile(File f, CRCLProgramType program, boolean addRecent) {
    }

    @Override
    public CRCLProgramType editProgram(CRCLProgramType program) {
        return program;
    }

    @Override
    public void showLastProgramLineExecTimeMillisDists(int row, ProgramRunData prd) {
    }

    private final boolean recordPose;

    @Override
    public boolean isRecordPoseSelected() {
        return this.recordPose;
    }

    private final boolean recordTrigger;

    @Override
    public boolean isRecordTriggerSelected() {
        return this.recordTrigger;
    }

    private static boolean prop(String propName, boolean defaultVal) {
        return Boolean.valueOf(System.getProperty(propName, Boolean.toString(defaultVal)));
    }

    public PendantClientOuterStub() {
        this(prop("crcjava.PendandClient.validateXML", false),// validateXML
                //                prop("crcjava.PendandClient.replaceState", false),// validateXML
                System.getProperty("crcl4java.host", "localhost"), Integer.parseInt(System.getProperty("crcl4java.port",
                Integer.toString(CRCLSocket.DEFAULT_PORT))), //port
                prop("crcjava.PendandClient.debugWaitForDone", false),// debugWaitForDone
                prop("crcjava.PendandClient.debugSendCommand", false),// debugSendCommand
                prop("crcjava.PendandClient.debugReadStatus", false),// debugReadStatus
                prop("crcjava.PendandClient.recordPose", false),// recordPose
                prop("crcjava.PendandClient.recordTrigger", false),// recordPose
                prop("crcjava.PendandClient.exiSelected", false)// exiSelected
        );
    }

    public PendantClientOuterStub(
            boolean validateXml,
            //            boolean replaceState,
            String host,
            int port,
            boolean debugWaitForDone,
            boolean debugSendCommand,
            boolean debugReadStatus,
            boolean recordPose,
            boolean recordTrigger,
            boolean exiSelected) {
//        this.replaceState = replaceState;
        this.host = host;
        this.port = port;
        this.debugWaitForDone = debugWaitForDone;
        this.debugSendCommand = debugSendCommand;
        this.debugReadStatus = debugReadStatus;
        this.recordPose = recordPose;
         this.recordTrigger = recordTrigger;
        this.exiSelected = exiSelected;
    }

    private final boolean exiSelected;

    @Override
    public boolean isEXISelected() {
        return this.exiSelected;
    }

    @Override
    public boolean checkUserText(String text) {
        return true;
    }
    private static final Logger LOG = Logger.getLogger(PendantClientOuterStub.class.getName());

    @Override
    public boolean isMonitoringHoldingObject() {
        return false;
    }

    @Override
    public void setExpectedHoldingObject(boolean x) {
    }

    @Override
    public MiddleCommandType currentProgramCommand() {
        return null;
    }

    @Override
    public PoseType currentStatusPose() {
        return null;
    }

    @Override
    public File getLastOpenedProgramFile() {
        return null;
    }

    @Override
    public void setProgram(CRCLProgramType program) throws JAXBException {
    }

    @Override
    public void saveXmlProgramFile(File f) throws JAXBException, CRCLException {
    }

    @Override
    public boolean isPlotJointsSelected() {
        return false;
    }

    @Override
    public boolean isPlotXyzSelected() {
        return false;
    }

    @Override
    public PendantClientMenuOuter getMenuOuter() {
        return this;
    }

    @Override
    public File getPropertiesFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPropertiesFile(File propertiesFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadProperties() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveProperties() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private volatile long maxTime = -1;

    @Override
    public void updateCommandStatusLog(Deque<CommandStatusLogElement> log) {
        for (CommandStatusLogElement el : log) {
            if (el.getTime() > maxTime) {
                System.out.println(el.toString());
                maxTime = el.getTime();
            }
        }
    }

    @Override
    public void setEnableSaveProgram(boolean enable) {
    }

    @Override
    public void clearProgramTimesDistances() {
    }

    @Override
    public void readRecentCommandFiles() {
    }

    @Override
    public void showPaused(boolean paused) {
    }

    @Override
    public void showLastGetStatusCommandString(String string) {
    }

    @Override
    public void showLastStopCommandString(String string) {
    }

    @Override
    public void showLastOtherCommandString(String string) {
    }

    
}
