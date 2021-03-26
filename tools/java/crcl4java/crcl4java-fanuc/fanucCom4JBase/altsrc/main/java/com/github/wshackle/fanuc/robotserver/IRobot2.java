package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Top level Robot Object interface used to establish connections and get references objects representing other system areas.
 */
@IID("{A6861F10-5F34-4053-ABE4-55C55F595814}")
public interface IRobot2 extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Gets the host name of the robot to which this object is connected.
   * </p>
   * <p>
   * Getter method for the COM property "HostName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(7)
  java.lang.String hostName();


  /**
   * <p>
   * Gets the current position of the robot arm.
   * </p>
   * <p>
   * Getter method for the COM property "CurPosition"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ICurPosition
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(8)
  com.github.wshackle.fanuc.robotserver.ICurPosition curPosition();


  /**
   * <p>
   * Gets the devices of the robot.
   * </p>
   * <p>
   * Getter method for the COM property "Devices"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(9)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject devices();


  /**
   * <p>
   * Gets the list of programs currently loaded on the controller.
   * </p>
   * <p>
   * Getter method for the COM property "Programs"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IPrograms
   */

  @DISPID(104) //= 0x68. The runtime will prefer the VTID if present
  @VTID(10)
  com.github.wshackle.fanuc.robotserver.IPrograms programs();


  /**
   * <p>
   * Gets the position registers of the robot.
   * </p>
   * <p>
   * Getter method for the COM property "RegPositions"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ISysPositions
   */

  @DISPID(105) //= 0x69. The runtime will prefer the VTID if present
  @VTID(11)
  com.github.wshackle.fanuc.robotserver.ISysPositions regPositions();


  /**
   * <p>
   * Gets the numeric registers of the robot.
   * </p>
   * <p>
   * Getter method for the COM property "RegNumerics"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IVars
   */

  @DISPID(106) //= 0x6a. The runtime will prefer the VTID if present
  @VTID(12)
  com.github.wshackle.fanuc.robotserver.IVars regNumerics();


  /**
   * <p>
   * Gets the system variables of the robot.
   * </p>
   * <p>
   * Getter method for the COM property "SysVariables"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IVars
   */

  @DISPID(107) //= 0x6b. The runtime will prefer the VTID if present
  @VTID(13)
  com.github.wshackle.fanuc.robotserver.IVars sysVariables();


  /**
   * <p>
   * Gets or sets which device is currently the remote master of the motion system.
   * </p>
   * <p>
   * Getter method for the COM property "RemoteMotionMaster"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FRERemoteMotionMasterConstants
   */

  @DISPID(108) //= 0x6c. The runtime will prefer the VTID if present
  @VTID(14)
  com.github.wshackle.fanuc.robotserver.FRERemoteMotionMasterConstants remoteMotionMaster();


  /**
   * <p>
   * Gets or sets which device is currently the remote master of the motion system.
   * </p>
   * <p>
   * Setter method for the COM property "RemoteMotionMaster"
   * </p>
   * @param remoteMotionMaster Mandatory com.github.wshackle.fanuc.robotserver.FRERemoteMotionMasterConstants parameter.
   */

  @DISPID(108) //= 0x6c. The runtime will prefer the VTID if present
  @VTID(15)
  void remoteMotionMaster(
    com.github.wshackle.fanuc.robotserver.FRERemoteMotionMasterConstants remoteMotionMaster);


  /**
   * <p>
   * Gets the list of active and logged alarms for the robot.
   * </p>
   * <p>
   * Getter method for the COM property "Alarms"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IAlarms
   */

  @DISPID(109) //= 0x6d. The runtime will prefer the VTID if present
  @VTID(16)
  com.github.wshackle.fanuc.robotserver.IAlarms alarms();


  /**
   * <p>
   * Gets the IO objects of the robot.
   * </p>
   * <p>
   * Getter method for the COM property "IOTypes"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IIOTypes
   */

  @DISPID(110) //= 0x6e. The runtime will prefer the VTID if present
  @VTID(17)
  com.github.wshackle.fanuc.robotserver.IIOTypes ioTypes();


  /**
   * <p>
   * Gets the Teach Pendant screen interface object.
   * </p>
   * <p>
   * Getter method for the COM property "TPScreen"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ITPScreen
   */

  @DISPID(111) //= 0x6f. The runtime will prefer the VTID if present
  @VTID(18)
  com.github.wshackle.fanuc.robotserver.ITPScreen tpScreen();


  /**
   * <p>
   * Gets or sets the language of the robot controller.
   * </p>
   * <p>
   * Getter method for the COM property "Language"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FRELanguageConstants
   */

  @DISPID(112) //= 0x70. The runtime will prefer the VTID if present
  @VTID(19)
  com.github.wshackle.fanuc.robotserver.FRELanguageConstants language();


  /**
   * <p>
   * Gets or sets the language of the robot controller.
   * </p>
   * <p>
   * Setter method for the COM property "Language"
   * </p>
   * @param language Mandatory com.github.wshackle.fanuc.robotserver.FRELanguageConstants parameter.
   */

  @DISPID(112) //= 0x70. The runtime will prefer the VTID if present
  @VTID(20)
  void language(
    com.github.wshackle.fanuc.robotserver.FRELanguageConstants language);


  /**
   * <p>
   * Gets the collection of user defined tool frames for  teach pendant programs.
   * </p>
   * <p>
   * Getter method for the COM property "ToolFrames"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ISysPositions
   */

  @DISPID(113) //= 0x71. The runtime will prefer the VTID if present
  @VTID(21)
  com.github.wshackle.fanuc.robotserver.ISysPositions toolFrames();


  /**
   * <p>
   * Gets the collection of user defined frames for  teach pendant programs.
   * </p>
   * <p>
   * Getter method for the COM property "UserFrames"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ISysPositions
   */

  @DISPID(114) //= 0x72. The runtime will prefer the VTID if present
  @VTID(22)
  com.github.wshackle.fanuc.robotserver.ISysPositions userFrames();


  /**
   * <p>
   * Gets the jog frames of the robot.
   * </p>
   * <p>
   * Getter method for the COM property "JogFrames"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ISysPositions
   */

  @DISPID(115) //= 0x73. The runtime will prefer the VTID if present
  @VTID(23)
  com.github.wshackle.fanuc.robotserver.ISysPositions jogFrames();


  /**
   * <p>
   * Gets the list of KAREL or TP programs tasks active on the controller.
   * </p>
   * <p>
   * Getter method for the COM property "Tasks"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ITasks
   */

  @DISPID(116) //= 0x74. The runtime will prefer the VTID if present
  @VTID(24)
  com.github.wshackle.fanuc.robotserver.ITasks tasks();


  /**
   * <p>
   * Gets the list of loaded features for the robot.
   * </p>
   * <p>
   * Getter method for the COM property "SynchData"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ISynchData
   */

  @DISPID(117) //= 0x75. The runtime will prefer the VTID if present
  @VTID(25)
  com.github.wshackle.fanuc.robotserver.ISynchData synchData();


  /**
   * <p>
   * Gets the Applications collection of the robot.
   * </p>
   * <p>
   * Getter method for the COM property "Applications"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IApplications
   */

  @DISPID(118) //= 0x76. The runtime will prefer the VTID if present
  @VTID(26)
  com.github.wshackle.fanuc.robotserver.IApplications applications();


  /**
   * <p>
   * Enables/disables the teach pendant as the motion control source.
   * </p>
   * <p>
   * Getter method for the COM property "TPMotionSource"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(119) //= 0x77. The runtime will prefer the VTID if present
  @VTID(27)
  boolean tpMotionSource();


  /**
   * <p>
   * Enables/disables the teach pendant as the motion control source.
   * </p>
   * <p>
   * Setter method for the COM property "TPMotionSource"
   * </p>
   * @param tpMotionSource Mandatory boolean parameter.
   */

  @DISPID(119) //= 0x77. The runtime will prefer the VTID if present
  @VTID(28)
  void tpMotionSource(
    boolean tpMotionSource);


  /**
   * <p>
   * Returns True if the Robot Server is currently connected offline.
   * </p>
   * <p>
   * Getter method for the COM property "IsOffline"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(120) //= 0x78. The runtime will prefer the VTID if present
  @VTID(29)
  boolean isOffline();


  /**
   * <p>
   * This property returns a Boolean flag indicating if the object is connected as an event logger.
   * </p>
   * <p>
   * Getter method for the COM property "IsEventLogger"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(121) //= 0x79. The runtime will prefer the VTID if present
  @VTID(30)
  boolean isEventLogger();


  /**
   * <p>
   * Obsolete. Use ConnectEx method
   * </p>
   * @param hostName Mandatory java.lang.String parameter.
   */

  @DISPID(151) //= 0x97. The runtime will prefer the VTID if present
  @VTID(31)
  void connect(
    java.lang.String hostName);


    /**
     * <p>
     * Loads a file from a controller device into memory.
     * </p>
     * @param fileName Mandatory java.lang.String parameter.
     * @param option Mandatory com.github.wshackle.fanuc.robotserver.FRELoadOptionConstants parameter.
     */

    @DISPID(153) //= 0x99. The runtime will prefer the VTID if present
    @VTID(33)
    void load(
      java.lang.String fileName,
      com.github.wshackle.fanuc.robotserver.FRELoadOptionConstants option);


    /**
     * <p>
     * Creates a ROS packet event object for receiving events from the robot.
     * </p>
     * @param subSystemCode Optional parameter. Default value is com4j.Variant.getMissing()
     * @param requestCode Optional parameter. Default value is com4j.Variant.getMissing()
     * @param packetID Optional parameter. Default value is com4j.Variant.getMissing()
     * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IPacketEvent
     */

    @DISPID(154) //= 0x9a. The runtime will prefer the VTID if present
    @VTID(34)
    com.github.wshackle.fanuc.robotserver.IPacketEvent createPacketEvent(
      @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object subSystemCode,
      @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object requestCode,
      @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object packetID);


    /**
     * <p>
     * Connects to an off-line simulated robot controller, specified by a virtual hostname.
     * </p>
     * @param hostName Optional parameter. Default value is com4j.Variant.getMissing()
     * @param mdPath Optional parameter. Default value is com4j.Variant.getMissing()
     * @param frsPath Optional parameter. Default value is com4j.Variant.getMissing()
     * @param ignoreVersion Optional parameter. Default value is com4j.Variant.getMissing()
     */

    @DISPID(155) //= 0x9b. The runtime will prefer the VTID if present
    @VTID(35)
    void offlineConnect(
      @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object hostName,
      @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object mdPath,
      @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object frsPath,
      @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object ignoreVersion);


    /**
     * <p>
     * Returns extended error information for the last error thrown.
     * </p>
     * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IRobotErrorInfo
     */

    @DISPID(156) //= 0x9c. The runtime will prefer the VTID if present
    @VTID(36)
    com.github.wshackle.fanuc.robotserver.IRobotErrorInfo getErrorInfo();


    /**
     * <p>
     * Connects the Robot object as an event logger service.
     * </p>
     */

    @DISPID(157) //= 0x9d. The runtime will prefer the VTID if present
    @VTID(37)
    void eventLoggerConnect();


    /**
     * @param command Mandatory java.lang.String parameter.
     * @param wait Mandatory boolean parameter.
     */

    @DISPID(158) //= 0x9e. The runtime will prefer the VTID if present
    @VTID(38)
    void kcl(
      java.lang.String command,
      boolean wait);


    /**
     * @param fileName Mandatory java.lang.String parameter.
     */

    @DISPID(159) //= 0x9f. The runtime will prefer the VTID if present
    @VTID(39)
    void spruncmd(
      java.lang.String fileName);


    /**
     * @param message Mandatory java.lang.String parameter.
     */

    @DISPID(160) //= 0xa0. The runtime will prefer the VTID if present
    @VTID(40)
    void outputDebugMessage(
      java.lang.String message);


    /**
     * <p>
     * Attempts connection to the robot controller specified by HostName the number of times specified by NumRetries at a the Period specified.  NoWait = TRUE will return immediately.
     * </p>
     * @param hostName Mandatory java.lang.String parameter.
     * @param noWait Mandatory boolean parameter.
     * @param numRetries Mandatory int parameter.
     * @param period Mandatory int parameter.
     */

    @DISPID(161) //= 0xa1. The runtime will prefer the VTID if present
    @VTID(41)
    void connectEx(
      java.lang.String hostName,
      boolean noWait,
      int numRetries,
      int period);


    /**
     * <p>
     * Returns whether the Robot Object is connected to a robot controller.
     * </p>
     * <p>
     * Getter method for the COM property "IsConnected"
     * </p>
     * @return  Returns a value of type boolean
     */

    @DISPID(162) //= 0xa2. The runtime will prefer the VTID if present
    @VTID(42)
    boolean isConnected();


      /**
       * <p>
       * Returns an FRCSysInfo object from which system information can be obtained.
       * </p>
       * <p>
       * Getter method for the COM property "SysInfo"
       * </p>
       * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ISysInfo
       */

      @DISPID(164) //= 0xa4. The runtime will prefer the VTID if present
      @VTID(44)
      com.github.wshackle.fanuc.robotserver.ISysInfo sysInfo();


      /**
       * <p>
       * Returns the list of pipes currently registered on the controller.
       * </p>
       * <p>
       * Getter method for the COM property "Pipes"
       * </p>
       * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IPipes
       */

      @DISPID(165) //= 0xa5. The runtime will prefer the VTID if present
      @VTID(45)
      com.github.wshackle.fanuc.robotserver.IPipes pipes();


      /**
       * <p>
       * Creates a new FRCIndPosition class object for your use.
       * </p>
       * @param groupBitMask Optional parameter. Default value is 0
       * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IIndPosition
       */

      @DISPID(166) //= 0xa6. The runtime will prefer the VTID if present
      @VTID(46)
      com.github.wshackle.fanuc.robotserver.IIndPosition createIndependentPosition(
        @Optional @DefaultValue("0") com.github.wshackle.fanuc.robotserver.FREGroupBitMaskConstants groupBitMask);


      /**
       * <p>
       * Process ID
       * </p>
       * <p>
       * Getter method for the COM property "ProcessID"
       * </p>
       * @return  Returns a value of type int
       */

      @DISPID(167) //= 0xa7. The runtime will prefer the VTID if present
      @VTID(47)
      int processID();


      /**
       * <p>
       * Gets the string registers of the robot.
       * </p>
       * <p>
       * Getter method for the COM property "RegStrings"
       * </p>
       * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IVars
       */

      @DISPID(168) //= 0xa8. The runtime will prefer the VTID if present
      @VTID(48)
      com.github.wshackle.fanuc.robotserver.IVars regStrings();


      // Properties:
    }
