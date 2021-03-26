package com.github.wshackle.fanuc.robotneighborhood  ;

import com4j.*;

/**
 * Provides root access to the Robot Neighborhood.
 */
@IID("{27EF444E-5706-4BAD-8BAA-01610A602C37}")
public interface IRobotNeighborhood extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Returns the top level FRCRNRobots object for accessing the neighborhood.
   * </p>
   * <p>
   * Getter method for the COM property "Robots"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotneighborhood.IRNRobots
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  com.github.wshackle.fanuc.robotneighborhood.IRNRobots robots();


  /**
   * <p>
   * Sets/returns the amount of time that the Robot Neighborhood will tolerate for the virtual robot to shut down.
   * </p>
   * <p>
   * Getter method for the COM property "VirtualRestartTolerance"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  int virtualRestartTolerance();


  /**
   * <p>
   * Sets/returns the amount of time that the Robot Neighborhood will tolerate for the virtual robot to shut down.
   * </p>
   * <p>
   * Setter method for the COM property "VirtualRestartTolerance"
   * </p>
   * @param virtualRestartTolerance Mandatory int parameter.
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(9)
  void virtualRestartTolerance(
    int virtualRestartTolerance);


  /**
   * <p>
   * Sets/returns the time to delay after the robot responds to a ping to begin reconnecting.
   * </p>
   * <p>
   * Getter method for the COM property "PingDelay"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(10)
  int pingDelay();


  /**
   * <p>
   * Sets/returns the time to delay after the robot responds to a ping to begin reconnecting.
   * </p>
   * <p>
   * Setter method for the COM property "PingDelay"
   * </p>
   * @param pingDelay Mandatory int parameter.
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(11)
  void pingDelay(
    int pingDelay);


  /**
   * <p>
   * Sets/returns the delay that the Robot Server will tolerate when waiting for the robot controller to respond to a request.
   * </p>
   * <p>
   * Getter method for the COM property "RSResponseTolerance"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(12)
  int rsResponseTolerance();


  /**
   * <p>
   * Sets/returns the delay that the Robot Server will tolerate when waiting for the robot controller to respond to a request.
   * </p>
   * <p>
   * Setter method for the COM property "RSResponseTolerance"
   * </p>
   * @param rsResponseTolerance Mandatory int parameter.
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(13)
  void rsResponseTolerance(
    int rsResponseTolerance);


  /**
   * <p>
   * Sets/returns the default setting for AutoReconnectEnable.
   * </p>
   * <p>
   * Getter method for the COM property "DefaultAutoReconnectEnable"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(14)
  boolean defaultAutoReconnectEnable();


  /**
   * <p>
   * Sets/returns the default setting for AutoReconnectEnable.
   * </p>
   * <p>
   * Setter method for the COM property "DefaultAutoReconnectEnable"
   * </p>
   * @param defaultAutoReconnectEnable Mandatory boolean parameter.
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(15)
  void defaultAutoReconnectEnable(
    boolean defaultAutoReconnectEnable);


  /**
   * <p>
   * Sets/returns the default setting for AutoReconnectPeriod
   * </p>
   * <p>
   * Getter method for the COM property "DefaultAutoReconnectPeriod"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(16)
  int defaultAutoReconnectPeriod();


  /**
   * <p>
   * Sets/returns the default setting for AutoReconnectPeriod
   * </p>
   * <p>
   * Setter method for the COM property "DefaultAutoReconnectPeriod"
   * </p>
   * @param defaultAutoReconnectPeriod Mandatory int parameter.
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(17)
  void defaultAutoReconnectPeriod(
    int defaultAutoReconnectPeriod);


  /**
   * <p>
   * Sets/returns the default setting for AutoReconnectNumRetries
   * </p>
   * <p>
   * Getter method for the COM property "DefaultAutoReconnectNumRetries"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(18)
  int defaultAutoReconnectNumRetries();


  /**
   * <p>
   * Sets/returns the default setting for AutoReconnectNumRetries
   * </p>
   * <p>
   * Setter method for the COM property "DefaultAutoReconnectNumRetries"
   * </p>
   * @param pVal Mandatory int parameter.
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(19)
  void defaultAutoReconnectNumRetries(
    int pVal);


  /**
   * <p>
   * Sets/returns the default setting for HeartbeatEnable
   * </p>
   * <p>
   * Getter method for the COM property "DefaultHeartbeatEnable"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(20)
  boolean defaultHeartbeatEnable();


  /**
   * <p>
   * Sets/returns the default setting for HeartbeatEnable
   * </p>
   * <p>
   * Setter method for the COM property "DefaultHeartbeatEnable"
   * </p>
   * @param defaultHeartbeatEnable Mandatory boolean parameter.
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(21)
  void defaultHeartbeatEnable(
    boolean defaultHeartbeatEnable);


  /**
   * <p>
   * Sets/returns the default setting for HeartbeatPeriod
   * </p>
   * <p>
   * Getter method for the COM property "DefaultHeartbeatPeriod"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(22)
  int defaultHeartbeatPeriod();


  /**
   * <p>
   * Sets/returns the default setting for HeartbeatPeriod
   * </p>
   * <p>
   * Setter method for the COM property "DefaultHeartbeatPeriod"
   * </p>
   * @param defaultHeartbeatPeriod Mandatory int parameter.
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(23)
  void defaultHeartbeatPeriod(
    int defaultHeartbeatPeriod);


  /**
   * <p>
   * Sets/returns the default setting for KeepAliveDuration
   * </p>
   * <p>
   * Getter method for the COM property "DefaultKeepAliveEnable"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(24)
  boolean defaultKeepAliveEnable();


  /**
   * <p>
   * Sets/returns the default setting for KeepAliveDuration
   * </p>
   * <p>
   * Setter method for the COM property "DefaultKeepAliveEnable"
   * </p>
   * @param defaultKeepAliveEnable Mandatory boolean parameter.
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(25)
  void defaultKeepAliveEnable(
    boolean defaultKeepAliveEnable);


  /**
   * <p>
   * Sets/returns the default setting for KeepAliveEnable
   * </p>
   * <p>
   * Getter method for the COM property "DefaultKeepAliveDuration"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(26)
  int defaultKeepAliveDuration();


  /**
   * <p>
   * Sets/returns the default setting for KeepAliveEnable
   * </p>
   * <p>
   * Setter method for the COM property "DefaultKeepAliveDuration"
   * </p>
   * @param defaultKeepAliveDuration Mandatory int parameter.
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(27)
  void defaultKeepAliveDuration(
    int defaultKeepAliveDuration);


  /**
   * <p>
   * Sets/returns the amount of time that the Robot Neighborhood delays when automatically switching between start modes.
   * </p>
   * <p>
   * Getter method for the COM property "StartModeAutoDelay"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(12) //= 0xc. The runtime will prefer the VTID if present
  @VTID(28)
  int startModeAutoDelay();


  /**
   * <p>
   * Sets/returns the amount of time that the Robot Neighborhood delays when automatically switching between start modes.
   * </p>
   * <p>
   * Setter method for the COM property "StartModeAutoDelay"
   * </p>
   * @param startModeAutoDelay Mandatory int parameter.
   */

  @DISPID(12) //= 0xc. The runtime will prefer the VTID if present
  @VTID(29)
  void startModeAutoDelay(
    int startModeAutoDelay);


  /**
   * <p>
   * Returns the FRCRDMResponses object from which you can monitor and control FANUC's Robot Discovery Method.
   * </p>
   * <p>
   * Getter method for the COM property "RDMResponses"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotneighborhood.IRNRDMResponses
   */

  @DISPID(13) //= 0xd. The runtime will prefer the VTID if present
  @VTID(30)
  com.github.wshackle.fanuc.robotneighborhood.IRNRDMResponses rdmResponses();


  // Properties:
}
