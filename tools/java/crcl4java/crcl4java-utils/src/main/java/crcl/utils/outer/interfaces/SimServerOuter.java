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

import crcl.base.LengthUnitEnumType;


/**
 * This class defines the interface between the SimServerInner and a GUI frame.
 * The class could also be stubbed for command line implementations.
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public interface SimServerOuter  {
    public void updateConnectedClients(int numClients);
    public void updateCycleCount(int _newCycleCount);
    public void updatePanels(boolean jointschanged);
    public void updateIsInitialized(boolean initialized);
    public void updateCurrentCommandType(String cmdName);
    public void updateEndEffector(String text);
    public void updateToolChangerIsOpen(boolean isOpen);
    
    public void showMessage(String msgString);
    public void finishSetCurrentWaypoint(int currentWaypoint);
//    public void updateLengthUnit(LengthUnitEnumType lu);
    public void showDebugMessage(final String s);
    public void updateNumWaypoints(int numWaypoints);
    
    public boolean isEditingStatus();

    public SimServerMenuOuter getMenuOuter();
}
