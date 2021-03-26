package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Defines methods to create COM objects
 */
public abstract class ClassFactory {
  private ClassFactory() {} // instanciation is not allowed


  /**
   * Object used to access extended error information after an error has been thrown (raised) and caught.
   */
  public static com.github.wshackle.fanuc.robotserver.IRobotErrorInfo createFRCRobotErrorInfo() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IRobotErrorInfo.class, "{5BBFA760-09C6-11D2-871C-00C04F98D092}" );
  }

  /**
   * Provides access to the current position of the robot for all motion groups.
   */
  public static com.github.wshackle.fanuc.robotserver.ICurPosition createFRCCurPosition() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ICurPosition.class, "{E2686FA9-1E42-11D1-B6FF-00C04FB9C401}" );
  }

  /**
   * Top level Robot Object interface used to establish connections and get references objects representing other system areas.
   */
  public static com.github.wshackle.fanuc.robotserver.IRobot2 createFRCRobot() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IRobot2.class, "{6D7E3A01-9ECC-11D0-94D5-0020AF68F0A3}" );
  }

  /**
   * Represents the collection of all programs currently loaded on the robot controller, i.e. Teach Pendant, KAREL, and VR programs.
   */
  public static com.github.wshackle.fanuc.robotserver.IPrograms createFRCPrograms() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IPrograms.class, "{1FBD567D-8F13-11D0-94CB-0020AF68F0A3}" );
  }

  /**
   * Represents a program on the robot controller. 
   */
  public static com.github.wshackle.fanuc.robotserver.IProgram createFRCProgram() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IProgram.class, "{D42AB5D5-8FFB-11D0-94CC-0020AF68F0A3}" );
  }

  /**
   * Provides access to robot controller variables, including system and program variables.
   */
  public static com.github.wshackle.fanuc.robotserver.IVars createFRCVars() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IVars.class, "{88B57BCB-D0CA-11CF-959F-00A024329125}" );
  }

  /**
   * Represents a TP program on the robot controller.
   */
  public static com.github.wshackle.fanuc.robotserver.ITPProgram createFRCTPProgram() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ITPProgram.class, "{F5C31101-46AE-11D0-8901-0020AF68F0A3}" );
  }

  /**
   * Provides access to the collection of positions in a TP program. 
   */
  public static com.github.wshackle.fanuc.robotserver.ITPPositions createFRCTPPositions() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ITPPositions.class, "{A16AD1C7-F45A-11CF-8901-0020AF68F0A3}" );
  }

  /**
   * Provides access to a single position in a TP program.
   */
  public static com.github.wshackle.fanuc.robotserver.ITPPosition createFRCTPPosition() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ITPPosition.class, "{3A49BE61-F5B9-11CF-8901-0020AF68F0A3}" );
  }

  /**
   * Provides access to the positional data of a TP position for a specific motion group.
   */
  public static com.github.wshackle.fanuc.robotserver.IGroupPosition createFRCGroupPosition() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IGroupPosition.class, "{A47A5881-056D-11D0-8901-0020AF68F0A3}" );
  }

  /**
   * Provides access to the status of a motion operation.
   */
  public static com.github.wshackle.fanuc.robotserver.IMotionErrorInfo createFRCMotionErrorInfo() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IMotionErrorInfo.class, "{EE912848-BB81-427A-951F-5D9DC0FE74A7}" );
  }

  /**
   * Represents the position of a single group of axes consisting of three real components specifying a Cartesian location (x,y,z), three real components specifying an orientation (w,p,r), and a component specifying a configuration.
   */
  public static com.github.wshackle.fanuc.robotserver.IXyzWpr createFRCXyzWpr() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IXyzWpr.class, "{A47A5885-056D-11D0-8901-0020AF68F0A3}" );
  }

  /**
   * Represents a Cartesian position’s configuration.
   */
  public static com.github.wshackle.fanuc.robotserver.IConfig createFRCConfig() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IConfig.class, "{C58B0E61-ECD4-11D0-9FA5-00A024329125}" );
  }

  /**
   * Represents the extended axes for a position. 
   */
  public static com.github.wshackle.fanuc.robotserver.IAxesCollection createFRCAxesCollection() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IAxesCollection.class, "{035505A1-1C41-11D0-8901-0020AF68F0A3}" );
  }

  /**
   * Provides access to the positional data.
   */
  public static com.github.wshackle.fanuc.robotserver.IPosition2 createFRCPosition() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IPosition2.class, "{D42AB5DB-8FFB-11D0-94CC-0020AF68F0A3}" );
  }

  public static com.github.wshackle.fanuc.robotserver.ITPLines createFRCTPLines() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ITPLines.class, "{F5C31107-46AE-11D0-8901-0020AF68F0A3}" );
  }

  public static com.github.wshackle.fanuc.robotserver.ISelectedLines createFRCSelectedLines() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ISelectedLines.class, "{58ADC520-9395-11D2-877C-00C04FB9C401}" );
  }

  public static com.github.wshackle.fanuc.robotserver.ITPApplDataCollection createFRCTPApplDataCollection() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ITPApplDataCollection.class, "{70F06EE1-DCE0-11D0-A083-00A02436CF7E}" );
  }

  public static com.github.wshackle.fanuc.robotserver.ITPLabels createFRCTPLabels() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ITPLabels.class, "{C3FB0D01-58D6-11D0-8901-0020AF68F0A3}" );
  }

  public static com.github.wshackle.fanuc.robotserver.ITPLabel createFRCTPLabel() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ITPLabel.class, "{C3FB0D03-58D6-11D0-8901-0020AF68F0A3}" );
  }

  /**
   * Provides access to a collection of controller system positions.
   */
  public static com.github.wshackle.fanuc.robotserver.ISysPositions createFRCSysPositions() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ISysPositions.class, "{6055D69B-FFAE-11D0-B6F4-00C04FB9C401}" );
  }

  /**
   * Provides access to a single controller system position.
   */
  public static com.github.wshackle.fanuc.robotserver.ISysPosition createFRCSysPosition() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ISysPosition.class, "{6055D699-FFAE-11D0-B6F4-00C04FB9C401}" );
  }

  /**
   * Provides access to the positional data of a controller system position for a specific motion group.
   */
  public static com.github.wshackle.fanuc.robotserver.ISysGroupPosition createFRCSysGroupPosition() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ISysGroupPosition.class, "{DC2FA0C8-FFAB-11D0-B6F4-00C04FB9C401}" );
  }

  /**
   * The FRCAlarms object is a collection of FRCAlarm objects.  This collection contains all the alarms that have occurred since the last time that the log was cleared, up to a predefined number.
   */
  public static com.github.wshackle.fanuc.robotserver.IAlarms createFRCAlarms() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IAlarms.class, "{7C37F235-A494-11D0-A37F-0020AF39BE5A}" );
  }

  /**
   * This object contains error and cause data for the item selected from the FRCAlarms collection.
   */
  public static com.github.wshackle.fanuc.robotserver.IAlarm createFRCAlarm() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IAlarm.class, "{7C37F237-A494-11D0-A37F-0020AF39BE5A}" );
  }

  /**
   * Represents the collection of all the I/O types currently set up on the controller.
   */
  public static com.github.wshackle.fanuc.robotserver.IIOTypes createFRCIOTypes() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IIOTypes.class, "{59DC16ED-AF91-11D0-A072-00A02436CF7E}" );
  }

  /**
   * This object is used to access both I/O signal and I/O configuration collections.
   */
  public static com.github.wshackle.fanuc.robotserver.IIOType createFRCIOType() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IIOType.class, "{59DC16F1-AF91-11D0-A072-00A02436CF7E}" );
  }

  public static com.github.wshackle.fanuc.robotserver.IUserIOType createFRCUserIOType() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IUserIOType.class, "{59DC16F6-AF91-11D0-A072-00A02436CF7E}" );
  }

  public static com.github.wshackle.fanuc.robotserver.IUserIOSignals createFRCUserIOSignals() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IUserIOSignals.class, "{59DC16FA-AF91-11D0-A072-00A02436CF7E}" );
  }

  public static com.github.wshackle.fanuc.robotserver.IUserIOSignal createFRCUserIOSignal() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IUserIOSignal.class, "{59DC16FF-AF91-11D0-A072-00A02436CF7E}" );
  }

  public static com.github.wshackle.fanuc.robotserver.ITPScreen createFRCTPScreen() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ITPScreen.class, "{660E6870-E286-11D0-8BB6-0020AF39BE5A}" );
  }

  /**
   * The tasks collection represents all interpreter (PXNN) tasks that are currently active on the controller.  This collection will contain one object for each active task.
   */
  public static com.github.wshackle.fanuc.robotserver.ITasks createFRCTasks() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ITasks.class, "{6B01CFFC-4626-11D1-B745-00C04FBBE42A}" );
  }

  /**
   * The task object represents a running thread of execution on the controller.  A running task may be running different programs or program routines at various points in time.
   */
  public static com.github.wshackle.fanuc.robotserver.ITask createFRCTask() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ITask.class, "{847A8F82-4626-11D1-B745-00C04FBBE42A}" );
  }

  /**
   *  This object is used to access all other controller feature/option synchronization objects.
   */
  public static com.github.wshackle.fanuc.robotserver.ISynchData createFRCSynchData() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ISynchData.class, "{2AF44182-9273-11D1-B6F9-00C04FA3BD85}" );
  }

  /**
   * The features collection is a representation of all currently loaded controller features.  This collection will contain one object for each feature.  
   */
  public static com.github.wshackle.fanuc.robotserver.IFeatures createFRCFeatures() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IFeatures.class, "{2AF44184-9273-11D1-B6F9-00C04FA3BD85}" );
  }

  /**
   * This object represents a single feature loaded on the controller.  
   */
  public static com.github.wshackle.fanuc.robotserver.IFeature createFRCFeature() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IFeature.class, "{2AF44186-9273-11D1-B6F9-00C04FA3BD85}" );
  }

  public static com.github.wshackle.fanuc.robotserver.ITPInstructions createFRCTPInstructions() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ITPInstructions.class, "{3C05D26E-9BE8-11D1-B6FC-00C04FA3BD85}" );
  }

  public static com.github.wshackle.fanuc.robotserver.ITPInstruction createFRCTPInstruction() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ITPInstruction.class, "{3C05D270-9BE8-11D1-B6FC-00C04FA3BD85}" );
  }

  public static com.github.wshackle.fanuc.robotserver.ISynchApplData createFRCSynchApplData() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ISynchApplData.class, "{8FAFC8E8-B2B8-11D1-B705-00C04FA3BD85}" );
  }

  public static com.github.wshackle.fanuc.robotserver.ISynchApplDataItem createFRCSynchApplDataItem() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ISynchApplDataItem.class, "{8FAFC8EA-B2B8-11D1-B705-00C04FA3BD85}" );
  }

  /**
   * This object is a general-purpose collection of application specific objects.
   */
  public static com.github.wshackle.fanuc.robotserver.IApplications createFRCApplications() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IApplications.class, "{679622C3-E50A-11D1-B778-00C04FB99C75}" );
  }

  /**
   * Raises an event when the desired ROS Packet is received from the controller. 
   */
  public static com.github.wshackle.fanuc.robotserver.IPacketEvent createFRCPacketEvent() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IPacketEvent.class, "{FCCE9E5F-9420-11D1-B751-00C04FB99C75}" );
  }

  /**
   * Provides access to the information received in a ROS packet.
   */
  public static com.github.wshackle.fanuc.robotserver.IPacket createFRCPacket() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IPacket.class, "{64AF4546-9331-11D1-B751-00C04FB99C75}" );
  }

  /**
   * Enables you to access a group of independent variables and I/O signals with minimum network overhead.
   */
  public static com.github.wshackle.fanuc.robotserver.IScatteredAccess createFRCScatteredAccess() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IScatteredAccess.class, "{6F33A4D1-91F3-11D3-877C-00C04F81118D}" );
  }

  /**
   * Provides access to robot controller system information.
   */
  public static com.github.wshackle.fanuc.robotserver.ISysInfo createFRCSysInfo() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ISysInfo.class, "{4553DA63-ACA1-11D3-8783-00C04F81118D}" );
  }

  /**
   * Provides access to pipes defined on the controller.
   */
  public static com.github.wshackle.fanuc.robotserver.IPipes createFRCPipes() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IPipes.class, "{B475BC91-3AF1-11D4-9F66-00105AE428C3}" );
  }

  /**
   * Provides access to a pipe defined on the controller.
   */
  public static com.github.wshackle.fanuc.robotserver.IPipe createFRCPipe() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IPipe.class, "{B475BC95-3AF1-11D4-9F66-00105AE428C3}" );
  }

  /**
   * Enables you to work with position data without requiring you to create the position on the controller or find an existing one to use.
   */
  public static com.github.wshackle.fanuc.robotserver.IIndPosition createFRCIndPosition() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IIndPosition.class, "{B4819F73-FC65-4475-97D3-974ACF6EE03E}" );
  }

  /**
   * Provides access to the positional data of an Independant position for a specific motion group.
   */
  public static com.github.wshackle.fanuc.robotserver.IIndGroupPosition createFRCIndGroupPosition() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IIndGroupPosition.class, "{DBE7F3B9-01E5-4935-A211-B5CC9D3A1048}" );
  }

  /**
   * Provides access to the current position of the robot for a specific motion group.
   */
  public static com.github.wshackle.fanuc.robotserver.ICurGroupPosition createFRCCurGroupPosition() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ICurGroupPosition.class, "{75CEF1D7-1E43-11D1-B6FF-00C04FB9C401}" );
  }

  public static com.github.wshackle.fanuc.robotserver.IRobManProxy createFRCRobManProxy() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IRobManProxy.class, "{53D6E5D2-F5E2-11D3-9F35-00500409FF06}" );
  }

  /**
   * Represents a system or variable program on the robot controller. 
   */
  public static com.github.wshackle.fanuc.robotserver.IProgram createFRCVRProgram() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IProgram.class, "{8C8ACC9A-4F57-11D0-BC32-444553540000}" );
  }

  /**
   * Represents a KAREL program on the robot controller.  
   */
  public static com.github.wshackle.fanuc.robotserver.IKARELProgram createFRCKARELProgram() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IKARELProgram.class, "{DA462B71-DD0D-11D0-A083-00A02436CF7E}" );
  }

  /**
   * Provides access to individual components of a vector.
   */
  public static com.github.wshackle.fanuc.robotserver.IVector createFRCVector() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IVector.class, "{C1578510-0F7A-11D2-86F4-00C04F9184DB}" );
  }

  /**
   * Represents the position of a single group of axes as a transformation matrix consisting of normal, orient, approach, and location vectors and a component specifying a configuration.
   */
  public static com.github.wshackle.fanuc.robotserver.ITransform createFRCTransform() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ITransform.class, "{A47A5883-056D-11D0-8901-0020AF68F0A3}" );
  }

  /**
   * Represents the position data for a single group as simple joint axes. 
   */
  public static com.github.wshackle.fanuc.robotserver.IJoint createFRCJoint() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IJoint.class, "{A47A5887-056D-11D0-8901-0020AF68F0A3}" );
  }

  /**
   * Provides access to the attributes and value of program and system variables.
   */
  public static com.github.wshackle.fanuc.robotserver.IVar createFRCVar() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IVar.class, "{8C8ACC81-4F57-11D0-BC32-444553540000}" );
  }

  /**
   * This object allows access to numeric registers on the robot controller.
   */
  public static com.github.wshackle.fanuc.robotserver.IRegNumeric createFRCRegNumeric() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IRegNumeric.class, "{6B51A441-212A-11D0-959F-00A024329125}" );
  }

  /**
   * This object allows access to string registers on the robot controller.
   */
  public static com.github.wshackle.fanuc.robotserver.IRegString createFRCRegString() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IRegString.class, "{B5BD1EBA-FEC8-49CC-965B-7DD03974CDB8}" );
  }

  /**
   * Provides access to the positional data for a position stored as a variable on the controller.
   */
  public static com.github.wshackle.fanuc.robotserver.IVarPosition createFRCVarPosition() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IVarPosition.class, "{E3FFB439-2613-11D1-B702-00C04FB9C401}" );
  }

  /**
   * This object is used to access standard associated data common for all KAREL path motion groups.
   */
  public static com.github.wshackle.fanuc.robotserver.ICommonAssoc createFRCCommonAssoc() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ICommonAssoc.class, "{15AAA600-1108-11D2-86F4-00C04F9184DB}" );
  }

  /**
   * This object is used to access standard associated data for all KAREL path motion groups.
   */
  public static com.github.wshackle.fanuc.robotserver.IGroupAssoc createFRCGroupAssoc() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IGroupAssoc.class, "{4DE6A770-1108-11D2-86F4-00C04F9184DB}" );
  }

  public static com.github.wshackle.fanuc.robotserver.ITPLineHelper createFRCTPLineHelper() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ITPLineHelper.class, "{FC761641-4CEA-11D0-8901-0020AF68F0A3}" );
  }

  public static com.github.wshackle.fanuc.robotserver.ITPApplDataHelper createFRCTPApplDataHelper() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ITPApplDataHelper.class, "{51FF0460-DCE1-11D0-A083-00A02436CF7E}" );
  }

  public static com.github.wshackle.fanuc.robotserver.ITPSimpleLine createFRCTPSimpleLine() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ITPSimpleLine.class, "{6C473F20-B5F0-11D2-8781-00C04F98D092}" );
  }

  /**
   * This object is used to access all I/O signals for a particular I/O type.
   */
  public static com.github.wshackle.fanuc.robotserver.IIOSignals createFRCIOSignals() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IIOSignals.class, "{59DC16F8-AF91-11D0-A072-00A02436CF7E}" );
  }

  /**
   * Represents an I/O signal.  
   */
  public static com.github.wshackle.fanuc.robotserver.IIOSignal2 createFRCIOSignal() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IIOSignal2.class, "{59DC170B-AF91-11D0-A072-00A02436CF7E}" );
  }

  /**
   * This object is used to access all I/O configurations for a particular I/O type.
   */
  public static com.github.wshackle.fanuc.robotserver.IIOConfigs createFRCIOConfigs() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IIOConfigs.class, "{59DC1701-AF91-11D0-A072-00A02436CF7E}" );
  }

  /**
   * This object represents an input or output configuration.
   */
  public static com.github.wshackle.fanuc.robotserver.IIOConfig createFRCIOConfig() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IIOConfig.class, "{59DC1703-AF91-11D0-A072-00A02436CF7E}" );
  }

  /**
   * This object is used to access both I/O signal and I/O configuration collections.
   */
  public static com.github.wshackle.fanuc.robotserver.IConfigurableIOType createFRCDigitalIOType() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IConfigurableIOType.class, "{59DC16F4-AF91-11D0-A072-00A02436CF7E}" );
  }

  /**
   * This object is used to access both I/O signal and I/O configuration collections.
   */
  public static com.github.wshackle.fanuc.robotserver.IConfigurableIOType createFRCAnalogIOType() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IConfigurableIOType.class, "{714CC916-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * This object is used to access both I/O signal and I/O configuration collections.
   */
  public static com.github.wshackle.fanuc.robotserver.IConfigurableIOType createFRCGroupIOType() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IConfigurableIOType.class, "{714CC917-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * This object is used to access both I/O signal and I/O configuration collections.
   */
  public static com.github.wshackle.fanuc.robotserver.IConfigurableIOType createFRCUOPIOType() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IConfigurableIOType.class, "{714CC918-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * This object is used to access both I/O signal and I/O configuration collections.
   */
  public static com.github.wshackle.fanuc.robotserver.ISystemIOType createFRCRobotIOType() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ISystemIOType.class, "{714CC919-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * This object is used to access both I/O signal and I/O configuration collections.
   */
  public static com.github.wshackle.fanuc.robotserver.ISystemIOType createFRCSOPIOType() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ISystemIOType.class, "{714CC91A-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * This object is used to access both I/O signal and I/O configuration collections.
   */
  public static com.github.wshackle.fanuc.robotserver.ISystemIOType createFRCTPIOType() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ISystemIOType.class, "{714CC91B-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * This object is used to access both I/O signal and I/O configuration collections.
   */
  public static com.github.wshackle.fanuc.robotserver.IConfigurableIOType createFRCPLCIOType() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IConfigurableIOType.class, "{714CC91C-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * This object is used to access both I/O signal and I/O configuration collections.
   */
  public static com.github.wshackle.fanuc.robotserver.IConfigurableIOType createFRCWeldDigitalIOType() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IConfigurableIOType.class, "{714CC91D-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * This object is used to access both I/O signal and I/O configuration collections.
   */
  public static com.github.wshackle.fanuc.robotserver.IConfigurableIOType createFRCLaserDigitalIOType() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IConfigurableIOType.class, "{714CC91E-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * This object is used to access both I/O signal and I/O configuration collections.
   */
  public static com.github.wshackle.fanuc.robotserver.IConfigurableIOType createFRCLaserAnalogIOType() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IConfigurableIOType.class, "{714CC91F-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * This object is used to access both I/O signal and I/O configuration collections.
   */
  public static com.github.wshackle.fanuc.robotserver.IConfigurableIOType createFRCWeldStickIOType() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IConfigurableIOType.class, "{714CC920-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * This object is used to access Flag signals.
   */
  public static com.github.wshackle.fanuc.robotserver.ISystemIOType createFRCFlagType() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ISystemIOType.class, "{A16B2E95-219A-4FA8-9DE8-021D429B8805}" );
  }

  /**
   * This object is used to access Marker signals.
   */
  public static com.github.wshackle.fanuc.robotserver.ISystemIOType createFRCMarkerType() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.ISystemIOType.class, "{1C9FC454-C455-4A41-80EF-0894FEB07BF8}" );
  }

  /**
   * Represents a digital I/O signal object.  
   */
  public static com.github.wshackle.fanuc.robotserver.IDigitalIOSignal createFRCDigitalIOSignal() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IDigitalIOSignal.class, "{59DC16FD-AF91-11D0-A072-00A02436CF7E}" );
  }

  /**
   * Represents an analog I/O signal.  
   */
  public static com.github.wshackle.fanuc.robotserver.IAnalogIOSignal createFRCAnalogIOSignal() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IAnalogIOSignal.class, "{714CC922-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * Represents a group I/O signal.  
   */
  public static com.github.wshackle.fanuc.robotserver.IAnalogIOSignal createFRCGroupIOSignal() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IAnalogIOSignal.class, "{714CC923-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * Represents a User Operator Panel I/O signal.  
   */
  public static com.github.wshackle.fanuc.robotserver.IUOPIOSignal createFRCUOPIOSignal() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IUOPIOSignal.class, "{714CC925-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * Represents a robot I/O signal.  
   */
  public static com.github.wshackle.fanuc.robotserver.IRobotIOSignal createFRCRobotIOSignal() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IRobotIOSignal.class, "{714CC927-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * Represents a Flag signal.  
   */
  public static com.github.wshackle.fanuc.robotserver.IRobotIOSignal createFRCFlagSignal() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IRobotIOSignal.class, "{1A191D0B-ECA1-42E1-A200-1FC17400A54E}" );
  }

  /**
   * Represents a Marker signal.  
   */
  public static com.github.wshackle.fanuc.robotserver.IRobotIOSignal createFRCMarkerSignal() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IRobotIOSignal.class, "{A5F1E1FE-F2B7-40AB-9D33-6932112978BD}" );
  }

  /**
   * Represents a Standard Operator Panel I/O signal.  
   */
  public static com.github.wshackle.fanuc.robotserver.IUOPIOSignal createFRCSOPIOSignal() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IUOPIOSignal.class, "{714CC928-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * Represents a Teach Pendant I/O signal.  
   */
  public static com.github.wshackle.fanuc.robotserver.IUOPIOSignal createFRCTPIOSignal() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IUOPIOSignal.class, "{714CC929-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * Represents a PLC I/O signal.  
   */
  public static com.github.wshackle.fanuc.robotserver.IUOPIOSignal createFRCPLCIOSignal() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IUOPIOSignal.class, "{714CC92B-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * Represents a weld digital I/O signal.  
   */
  public static com.github.wshackle.fanuc.robotserver.IDigitalIOSignal createFRCWeldDigitalIOSignal() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IDigitalIOSignal.class, "{714CC92C-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * Represents a laser digital I/O signal.  
   */
  public static com.github.wshackle.fanuc.robotserver.IDigitalIOSignal createFRCLaserDigitalIOSignal() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IDigitalIOSignal.class, "{714CC92D-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * Represents a laser analog I/O signal.  
   */
  public static com.github.wshackle.fanuc.robotserver.IAnalogIOSignal createFRCLaserAnalogIOSignal() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IAnalogIOSignal.class, "{714CC92E-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * Represents a weld stick I/O signal.  
   */
  public static com.github.wshackle.fanuc.robotserver.IDigitalIOSignal createFRCWeldStickIOSignal() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IDigitalIOSignal.class, "{714CC92F-B4E5-11D0-A073-00A02436CF7E}" );
  }

  /**
   * Provides access to data blocks from a pipe as a set of named fields.
   */
  public static com.github.wshackle.fanuc.robotserver.IPipeFields createFRCPipeFields() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IPipeFields.class, "{B475BC97-3AF1-11D4-9F66-00105AE428C3}" );
  }

  /**
   * Provides access to data values received from a pipe.
   */
  public static com.github.wshackle.fanuc.robotserver.IPipeField createFRCPipeField() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IPipeField.class, "{B475BC99-3AF1-11D4-9F66-00105AE428C3}" );
  }

  public static com.github.wshackle.fanuc.robotserver.IPipePosition createFRCPipePosition() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotserver.IPipePosition.class, "{B475BC9B-3AF1-11D4-9F66-00105AE428C3}" );
  }
}
