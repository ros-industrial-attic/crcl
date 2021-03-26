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
package crcl.utils.outer.interfaces;

import crcl.base.CRCLProgramType;
import crcl.base.CRCLStatusType;
import crcl.base.MiddleCommandType;
import crcl.base.PoseType;
import crcl.utils.CRCLException;
import crcl.utils.CRCLSocket;
import java.io.File;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public interface PendantClientOuter extends PropertyOwner {

    public void showMessage(String s);

    public void showMessage(Throwable t);

    public boolean showDebugMessage(String s);

    public String getHost();

    public int getPort();

    public void finishDisconnect();

    public void finishConnect();

    public void finishSetStatus();

    public void checkXmlQuery(CRCLSocket crclSocket);

    public void stopPollTimer();

    public void checkPollSelected();

    public void showCurrentProgramLine(int line, CRCLProgramType program, CRCLStatusType status, List<ProgramRunData> programRunData);

    public void clearProgramTimesDistances();

    public void showLastProgramLineExecTimeMillisDists(int row, ProgramRunData prd);

    public int getCurrentProgramLine();
    
    public void finishOpenXmlProgramFile(File f, CRCLProgramType program, boolean addRecent);

    public CRCLProgramType editProgram(CRCLProgramType program);

    public boolean checkUserText(String text) throws InterruptedException, ExecutionException;

    public boolean isMonitoringHoldingObject();

    public void setExpectedHoldingObject(boolean x);

    public MiddleCommandType currentProgramCommand();

    public PoseType currentStatusPose();

    public File getLastOpenedProgramFile();

    public void setProgram(CRCLProgramType program) throws JAXBException;

    public void saveXmlProgramFile(File f) throws JAXBException, CRCLException;

    public PendantClientMenuOuter getMenuOuter();

    public void updateCommandStatusLog(Deque<CommandStatusLogElement> log);
    
    public void showPaused(boolean paused);
    
    public void showLastGetStatusCommandString(String string);
    public void showLastStopCommandString(String string);
    public void showLastOtherCommandString(String string);

}
