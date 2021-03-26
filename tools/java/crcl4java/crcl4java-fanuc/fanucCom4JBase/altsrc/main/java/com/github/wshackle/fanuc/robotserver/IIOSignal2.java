package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Represents an I/O signal.  
 */
@IID("{D3EE1B91-8BB6-11D3-877C-00C04F81118D}")
public interface IIOSignal2 extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * <p>
   * Returns the logical signal number.
   * </p>
   * <p>
   * Getter method for the COM property "LogicalNum"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(8)
  int logicalNum();


  /**
   * <p>
   * Returns/sets the comment string of a signal.
   * </p>
   * <p>
   * Getter method for the COM property "Comment"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(9)
  java.lang.String comment();


  /**
   * <p>
   * Returns/sets the comment string of a signal.
   * </p>
   * <p>
   * Setter method for the COM property "Comment"
   * </p>
   * @param comment Mandatory java.lang.String parameter.
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(10)
  void comment(
    java.lang.String comment);


  /**
   * <p>
   * Returns True if the signal is assigned, returns False otherwise.
   * </p>
   * <p>
   * Getter method for the COM property "IsAssigned"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(11)
  boolean isAssigned();


  /**
   * <p>
   * Returns True if signal is offline, returns False otherwise.
   * </p>
   * <p>
   * Getter method for the COM property "IsOffline"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(104) //= 0x68. The runtime will prefer the VTID if present
  @VTID(12)
  boolean isOffline();


  /**
   * <p>
   * Returns True if monitoring is enabled, False if not.
   * </p>
   * <p>
   * Getter method for the COM property "IsMonitoring"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(105) //= 0x69. The runtime will prefer the VTID if present
  @VTID(13)
  boolean isMonitoring();


  /**
   * <p>
   * Starts the monitoring of the I/O signal for changes.
   * </p>
   * @param latency Mandatory int parameter.
   */

  @DISPID(150) //= 0x96. The runtime will prefer the VTID if present
  @VTID(14)
  void startMonitor(
    int latency);


  /**
   * <p>
   * Stops the monitoring of the I/O signal for changes.
   * </p>
   */

  @DISPID(151) //= 0x97. The runtime will prefer the VTID if present
  @VTID(15)
  void stopMonitor();


  /**
   * <p>
   * Controls if the data is read directly from the robot or not when an read access is made to the Value property.
   * </p>
   * <p>
   * Getter method for the COM property "NoRefresh"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(250) //= 0xfa. The runtime will prefer the VTID if present
  @VTID(16)
  boolean noRefresh();


  /**
   * <p>
   * Controls if the data is read directly from the robot or not when an read access is made to the Value property.
   * </p>
   * <p>
   * Setter method for the COM property "NoRefresh"
   * </p>
   * @param noRefresh Mandatory boolean parameter.
   */

  @DISPID(250) //= 0xfa. The runtime will prefer the VTID if present
  @VTID(17)
  void noRefresh(
    boolean noRefresh);


  /**
   * <p>
   * Controls if the data is immediately sent to the robot or not when an assignment is made to the Value property.
   * </p>
   * <p>
   * Getter method for the COM property "NoUpdate"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(251) //= 0xfb. The runtime will prefer the VTID if present
  @VTID(18)
  boolean noUpdate();


  /**
   * <p>
   * Controls if the data is immediately sent to the robot or not when an assignment is made to the Value property.
   * </p>
   * <p>
   * Setter method for the COM property "NoUpdate"
   * </p>
   * @param noUpdate Mandatory boolean parameter.
   */

  @DISPID(251) //= 0xfb. The runtime will prefer the VTID if present
  @VTID(19)
  void noUpdate(
    boolean noUpdate);


  /**
   * <p>
   * Reads new information of the signal from the robot.
   * </p>
   */

  @DISPID(252) //= 0xfc. The runtime will prefer the VTID if present
  @VTID(20)
  void refresh();


  /**
   * <p>
   * Sends the local copy of this signal's information to the robot.
   * </p>
   */

  @DISPID(253) //= 0xfd. The runtime will prefer the VTID if present
  @VTID(21)
  void update();


  // Properties:
}
