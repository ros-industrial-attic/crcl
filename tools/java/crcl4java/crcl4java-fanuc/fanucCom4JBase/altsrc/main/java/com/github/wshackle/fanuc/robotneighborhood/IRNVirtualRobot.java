package com.github.wshackle.fanuc.robotneighborhood  ;

import com4j.*;

/**
 * Provides access to a specific virtual robot.
 */
@IID("{67909ED3-ECFC-4AAF-886C-38B74F321E09}")
public interface IRNVirtualRobot extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Returns/Sets the name of this robot.
   * </p>
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  java.lang.String name();


  /**
   * <p>
   * Returns/Sets the name of this robot.
   * </p>
   * <p>
   * Setter method for the COM property "Name"
   * </p>
   * @param name Mandatory java.lang.String parameter.
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(8)
  void name(
    java.lang.String name);


  /**
   * <p>
   * Returns the type of the robot – Real or Virtual.
   * </p>
   * <p>
   * Getter method for the COM property "Type"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotneighborhood.FRERNRobotTypeConstants
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(9)
  com.github.wshackle.fanuc.robotneighborhood.FRERNRobotTypeConstants type();


  /**
   * <p>
   * Returns the FRCRNRobots object that holds this FRCRNRobot object.
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotneighborhood.IRNRobots
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(10)
  com.github.wshackle.fanuc.robotneighborhood.IRNRobots parent();


  /**
   * <p>
   * Returns the fully decorated name of this robot.
   * </p>
   * <p>
   * Getter method for the COM property "PathName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(11)
  java.lang.String pathName();


  /**
   * <p>
   * Returns/sets the enable status of the AutoReconnect feature.
   * </p>
   * <p>
   * Getter method for the COM property "AutoReconnectEnable"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(12)
  boolean autoReconnectEnable();


  /**
   * <p>
   * Returns/sets the enable status of the AutoReconnect feature.
   * </p>
   * <p>
   * Setter method for the COM property "AutoReconnectEnable"
   * </p>
   * @param autoReconnectEnable Mandatory boolean parameter.
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(13)
  void autoReconnectEnable(
    boolean autoReconnectEnable);


  /**
   * <p>
   * Returns/sets the number of reconnection attempts that the AutoReconnect feature will try when activated.
   * </p>
   * <p>
   * Getter method for the COM property "AutoReconnectNumRetries"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(14)
  int autoReconnectNumRetries();


  /**
   * <p>
   * Returns/sets the number of reconnection attempts that the AutoReconnect feature will try when activated.
   * </p>
   * <p>
   * Setter method for the COM property "AutoReconnectNumRetries"
   * </p>
   * @param autoReconnectNumRetries Mandatory int parameter.
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(15)
  void autoReconnectNumRetries(
    int autoReconnectNumRetries);


  /**
   * <p>
   * Returns/sets the frequency at which the AutoReconnect feature will attempt to reconnect.
   * </p>
   * <p>
   * Getter method for the COM property "AutoReconnectPeriod"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(16)
  int autoReconnectPeriod();


  /**
   * <p>
   * Returns/sets the frequency at which the AutoReconnect feature will attempt to reconnect.
   * </p>
   * <p>
   * Setter method for the COM property "AutoReconnectPeriod"
   * </p>
   * @param autoReconnectPeriod Mandatory int parameter.
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(17)
  void autoReconnectPeriod(
    int autoReconnectPeriod);


  /**
   * <p>
   * Returns the last know status of the connection to the robot.
   * </p>
   * <p>
   * Getter method for the COM property "ConnectionStatus"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotneighborhood.FRERNConnectionStatusConstants
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(18)
  com.github.wshackle.fanuc.robotneighborhood.FRERNConnectionStatusConstants connectionStatus();


  /**
   * <p>
   * Returns/sets the enable status of the Heartbeat feature.
   * </p>
   * <p>
   * Getter method for the COM property "HeartbeatEnable"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(19)
  boolean heartbeatEnable();


  /**
   * <p>
   * Returns/sets the enable status of the Heartbeat feature.
   * </p>
   * <p>
   * Setter method for the COM property "HeartbeatEnable"
   * </p>
   * @param heartbeatEnable Mandatory boolean parameter.
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(20)
  void heartbeatEnable(
    boolean heartbeatEnable);


  /**
   * <p>
   * Returns/sets the frequency for which the monitoring of the physical connection to a robot is executed.
   * </p>
   * <p>
   * Getter method for the COM property "HeartbeatPeriod"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(21)
  int heartbeatPeriod();


  /**
   * <p>
   * Returns/sets the frequency for which the monitoring of the physical connection to a robot is executed.
   * </p>
   * <p>
   * Setter method for the COM property "HeartbeatPeriod"
   * </p>
   * @param heartbeatPeriod Mandatory int parameter.
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(22)
  void heartbeatPeriod(
    int heartbeatPeriod);


  /**
   * <p>
   * Returns/sets the enable status of the KeepAlive feature.
   * </p>
   * <p>
   * Getter method for the COM property "KeepAliveEnable"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(23)
  boolean keepAliveEnable();


  /**
   * <p>
   * Returns/sets the enable status of the KeepAlive feature.
   * </p>
   * <p>
   * Setter method for the COM property "KeepAliveEnable"
   * </p>
   * @param keepAliveEnable Mandatory boolean parameter.
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(24)
  void keepAliveEnable(
    boolean keepAliveEnable);


  /**
   * <p>
   * Returns/sets the amount of time that the Robot Neighborhood will keep the Robot Object active after all connections are released.
   * </p>
   * <p>
   * Getter method for the COM property "KeepAliveDuration"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(12) //= 0xc. The runtime will prefer the VTID if present
  @VTID(25)
  int keepAliveDuration();


  /**
   * <p>
   * Returns/sets the amount of time that the Robot Neighborhood will keep the Robot Object active after all connections are released.
   * </p>
   * <p>
   * Setter method for the COM property "KeepAliveDuration"
   * </p>
   * @param keepAliveDuration Mandatory int parameter.
   */

  @DISPID(12) //= 0xc. The runtime will prefer the VTID if present
  @VTID(26)
  void keepAliveDuration(
    int keepAliveDuration);


  /**
   * <p>
   * Returns IP Address of the RJ-3 controller that the connection represents.
   * </p>
   * <p>
   * Getter method for the COM property "IPAddress"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(13) //= 0xd. The runtime will prefer the VTID if present
  @VTID(27)
  java.lang.String ipAddress();


  /**
   * <p>
   * This property will return a collection of communication services that the robot supports.
   * </p>
   * <p>
   * Getter method for the COM property "Services"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotneighborhood.IRNServices
   */

  @DISPID(14) //= 0xe. The runtime will prefer the VTID if present
  @VTID(28)
  com.github.wshackle.fanuc.robotneighborhood.IRNServices services();


  /**
   * <p>
   * Returns a Robot Server object.
   * </p>
   * <p>
   * Getter method for the COM property "RobotServer"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(15) //= 0xf. The runtime will prefer the VTID if present
  @VTID(29)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject robotServer();


  /**
   * <p>
   * Copies the description of this robot to the Windows clipboard.
   * </p>
   */

  @DISPID(17) //= 0x11. The runtime will prefer the VTID if present
  @VTID(30)
  void copy();


  /**
   * <p>
   * Returns the path to the virtual robot subdirectory.
   * </p>
   * <p>
   * Getter method for the COM property "Location"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(18) //= 0x12. The runtime will prefer the VTID if present
  @VTID(31)
  java.lang.String location();


  /**
   * <p>
   * Returns the path to the virtual robot subdirectory.
   * </p>
   * <p>
   * Getter method for the COM property "StartMode"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotneighborhood.FRERNStartModeConstants
   */

  @DISPID(19) //= 0x13. The runtime will prefer the VTID if present
  @VTID(32)
  com.github.wshackle.fanuc.robotneighborhood.FRERNStartModeConstants startMode();


  /**
   * <p>
   * Returns the version string.
   * </p>
   * <p>
   * Getter method for the COM property "Version"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(20) //= 0x14. The runtime will prefer the VTID if present
  @VTID(33)
  java.lang.String version();


  /**
   * <p>
   * Creates a backup that can be used to recreate the robot.
   * </p>
   * @param backupPath Optional parameter. Default value is ""
   */

  @DISPID(21) //= 0x15. The runtime will prefer the VTID if present
  @VTID(34)
  void backup(
    @Optional @DefaultValue("") java.lang.String backupPath);


  /**
   * <p>
   * Starts a virtual robot in the specified mode.
   * </p>
   * @param requestedStartMode Mandatory com.github.wshackle.fanuc.robotneighborhood.FRERNStartModeConstants parameter.
   * @param autoRecover Optional parameter. Default value is false
   * @param backupPath Optional parameter. Default value is ""
   * @return  Returns a value of type com.github.wshackle.fanuc.robotneighborhood.FRERNStartModeConstants
   */

  @DISPID(22) //= 0x16. The runtime will prefer the VTID if present
  @VTID(35)
  com.github.wshackle.fanuc.robotneighborhood.FRERNStartModeConstants start(
    com.github.wshackle.fanuc.robotneighborhood.FRERNStartModeConstants requestedStartMode,
    @Optional @DefaultValue("-1") boolean autoRecover,
    @Optional @DefaultValue("") java.lang.String backupPath);


  /**
   * <p>
   * Stops the virtual robot.
   * </p>
   */

  @DISPID(23) //= 0x17. The runtime will prefer the VTID if present
  @VTID(36)
  void stop();


  /**
   * <p>
   * Returns the path to where the virtual robot was last backed up 
   * </p>
   * <p>
   * Getter method for the COM property "LastBackupPath"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(24) //= 0x18. The runtime will prefer the VTID if present
  @VTID(37)
  java.lang.String lastBackupPath();


  /**
   * <p>
   * Returns the path to where the virtual robot was last backed up 
   * </p>
   * <p>
   * Setter method for the COM property "LastBackupPath"
   * </p>
   * @param lastBackupPath Mandatory java.lang.String parameter.
   */

  @DISPID(24) //= 0x18. The runtime will prefer the VTID if present
  @VTID(38)
  void lastBackupPath(
    java.lang.String lastBackupPath);


  // Properties:
}
