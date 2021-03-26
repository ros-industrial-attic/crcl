package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Represents an I/O signal.  
 */
@IID("{59DC16FB-AF91-11D0-A072-00A02436CF7E}")
public interface IIOSignal extends com.github.wshackle.fanuc.robotserver.IRobotObject {
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


  // Properties:
}
