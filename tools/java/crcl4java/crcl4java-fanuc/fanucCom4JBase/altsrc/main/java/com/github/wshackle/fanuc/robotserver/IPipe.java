package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Provides access to a pipe defined on the controller.
 */
@IID("{B475BC94-3AF1-11D4-9F66-00105AE428C3}")
public interface IPipe extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * <p>
   * Tells the controller to flush the pipe.
   * </p>
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(8)
  void flush();


  /**
   * <p>
   * Returns the ID number of the pipe.
   * </p>
   * <p>
   * Getter method for the COM property "Id"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FREPipeIDConstants
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(9)
  com.github.wshackle.fanuc.robotserver.FREPipeIDConstants id();


  /**
   * <p>
   * Returns True if the Robot Server is monitoring this pipe.
   * </p>
   * <p>
   * Getter method for the COM property "IsMonitoring"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(10)
  boolean isMonitoring();


  /**
   * <p>
   * Returns True if the pipe has overflowed and is no longer sending data.
   * </p>
   * <p>
   * Getter method for the COM property "IsOverflowed"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(11)
  boolean isOverflowed();


  /**
   * <p>
   * Returns the parent object.
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IPipes
   */

  @DISPID(205) //= 0xcd. The runtime will prefer the VTID if present
  @VTID(12)
  com.github.wshackle.fanuc.robotserver.IPipes parent();


  /**
   * <p>
   * Starts monitoring data put into the pipe.
   * </p>
   * @param flushTime Mandatory int parameter.
   */

  @DISPID(206) //= 0xce. The runtime will prefer the VTID if present
  @VTID(13)
  void startMonitor(
    int flushTime);


  /**
   * <p>
   * Stops monitoring data put into the pipe.
   * </p>
   */

  @DISPID(207) //= 0xcf. The runtime will prefer the VTID if present
  @VTID(14)
  void stopMonitor();


  /**
   * <p>
   * Returns a string type name for the pipe.
   * </p>
   * <p>
   * Getter method for the COM property "TypeName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(208) //= 0xd0. The runtime will prefer the VTID if present
  @VTID(15)
  java.lang.String typeName();


  /**
   * <p>
   * Returns a string that identifies the KAREL program which defined the type.
   * </p>
   * <p>
   * Getter method for the COM property "TypeProgName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(209) //= 0xd1. The runtime will prefer the VTID if present
  @VTID(16)
  java.lang.String typeProgName();


  // Properties:
}
