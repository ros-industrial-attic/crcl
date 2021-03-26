package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Represents the extended axes for a position. 
 */
@IID("{035505A0-1C41-11D0-8901-0020AF68F0A3}")
public interface IAxesCollection extends com.github.wshackle.fanuc.robotserver.IRobotObject,Iterable<Com4jObject> {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "_NewEnum"
   * </p>
   */

  @DISPID(-4) //= 0xfffffffc. The runtime will prefer the VTID if present
  @VTID(8)
  java.util.Iterator<Com4jObject> iterator();

  /**
   * <p>
   * Returns/sets the values for each extended axis in the position.
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param jointNum Mandatory short parameter.
   * @return  Returns a value of type double
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  double item(
    short jointNum);


  /**
   * <p>
   * Returns/sets the values for each extended axis in the position.
   * </p>
   * <p>
   * Setter method for the COM property "Item"
   * </p>
   * @param jointNum Mandatory short parameter.
   * @param value Mandatory double parameter.
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(10)
  @DefaultMethod
  void item(
    short jointNum,
    double value);


  /**
   * <p>
   * Returns the number of extended axes in the position.
   * </p>
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(11)
  short count();


  // Properties:
}
