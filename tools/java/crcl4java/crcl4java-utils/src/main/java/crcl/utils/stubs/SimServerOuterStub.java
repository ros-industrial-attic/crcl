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

import crcl.utils.outer.interfaces.SimServerOuter;
import crcl.base.LengthUnitEnumType;
import crcl.utils.outer.interfaces.SimServerMenuOuter;
import java.util.logging.Level;
import java.util.logging.Logger;



public class SimServerOuterStub implements SimServerOuter, SimServerMenuOuter {

    
    @Override
    public void showMessage(String s) {
        final String sWithThread = "Thread:" + Thread.currentThread().getName()+" \n"+s;
        Logger.getLogger(this.getClass().getName()).log(Level.FINE, sWithThread);
    }

    @Override
    public void showDebugMessage(String s) {
        showMessage(s);
    }
    
    @Override
    public void updateConnectedClients(int numClients) {
    }


    private final boolean sendStatusWithoutRequest;
    
    @Override
    public boolean isSendStatusWithoutRequestSelected() {
        return this.sendStatusWithoutRequest;
    }

    private final boolean appendZero;
    
    @Override
    public boolean isAppendZeroSelected() {
       return this.appendZero;
    }

    private final boolean randomPacket;
    
    @Override
    public boolean isRandomPacketSelected() {
        return this.randomPacket;
    }

//    private final boolean replaceState;
//    
//    @Override
//    public boolean isReplaceStateSelected() {
//        return this.replaceState;
//    }

    
    @Override
    public boolean isEditingStatus() {
        return false;
    }

    @Override
    public void updateCycleCount(int _newCycleCount) {
    }

    @Override
    public void updatePanels(boolean jointschanged) {
    }

    @Override
    public void updateIsInitialized(boolean _initialized) {
        this.initialized = _initialized;
    }

    @Override
    public void updateCurrentCommandType(String cmdName) {
    }

    @Override
    public void updateEndEffector(String text) {
    }

    @Override
    public void updateToolChangerIsOpen(boolean isOpen) {
    }

    private boolean initialized=false;
    
    @Override
    public boolean isInitializedSelected() {
        return this.initialized;
    }

    

    @Override
    public void finishSetCurrentWaypoint(int currentWaypoint) {
    }

//    @Override
//    public void updateLengthUnit(LengthUnitEnumType lu) {
//    }


    @Override
    public void updateNumWaypoints(int numWaypoints) {
    }

    private final boolean debugMoveDone;
    
    @Override
    public boolean isDebugMoveDoneSelected() {
        return this.debugMoveDone;
    }

    private static boolean prop(String propName, boolean defaultVal) {
        return Boolean.valueOf(System.getProperty(propName,Boolean.toString(defaultVal)));
    }
    
    
                
    public SimServerOuterStub() {
        this(
                prop("crcjava.SimServer.validateXML",false),// validateXML
                prop("crcjava.SimServer.sendStatusWithoutRequest",false),// sendStatusWithoutRequest
                prop("crcjava.SimServer.appendZero",false),// appendZero
                prop("crcjava.SimServer.randomPacket",false),// randomPacket
//                prop("crcjava.SimServer.replaceState",false),// replaceState
                prop("crcjava.SimServer.debugMoveDone",false),// debugMoveDone
                prop("crcjava.SimServer.debugReadCommand",false),// debugReadCommand
                prop("crcjava.SimServer.replaceXmlHeader",true),// replaceXmlHeader
                prop("crcjava.SimServer.debugSendStatus",false),// debugSendStatus
                prop("crcjava.SimServer.exiSelected",false)// exiSelected
        );
    }

    public SimServerOuterStub(
            boolean validateXML, 
            boolean sendStatusWithoutRequest, 
            boolean appendZero, 
            boolean randomPacket, 
//            boolean replaceState, 
            boolean debugMoveDone, 
            boolean debugReadCommand,
            boolean replaceXmlHeader,
            boolean debugSendStatus,
            boolean exiSelected) {
//        this.validateXML = validateXML;
        this.sendStatusWithoutRequest = sendStatusWithoutRequest;
        this.appendZero = appendZero;
        this.randomPacket = randomPacket;
//        this.replaceState = replaceState;
        this.debugMoveDone = debugMoveDone;
        this.debugReadCommand = debugReadCommand;
        this.replaceXmlHeader = replaceXmlHeader;
        this.degugSendStatus = debugSendStatus;
        this.exiSelected = exiSelected;
    }
    
    
    private final boolean degugSendStatus;

    @Override
    public boolean isDebugSendStatusSelected() {
        return this.degugSendStatus;
    }

    private final boolean debugReadCommand;
    
    @Override
    public boolean isDebugReadCommandSelected() {
        return this.debugReadCommand;
    }

    private final boolean replaceXmlHeader;
    
    @Override
    public boolean isReplaceXmlHeaderSelected() {
        return this.replaceXmlHeader;
    }
    
    private final boolean exiSelected;

    @Override
    public boolean isEXISelected() {
        return this.exiSelected;
    }
    private static final Logger LOG = Logger.getLogger(SimServerOuterStub.class.getName());

    @Override
    public SimServerMenuOuter getMenuOuter() {
        return this;
    }
    
}
