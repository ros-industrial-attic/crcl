package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Represents the position data for a single group as simple joint axes. 
 */
@IID("{A47A5886-056D-11D0-8901-0020AF68F0A3}")
public interface IJoint extends com.github.wshackle.fanuc.robotserver.IRobotObject,Iterable<Com4jObject> {
  // Methods:
  /**
   * <p>
   * Returns the owning program object.
   * </p>
   * <p>
   * Getter method for the COM property "Program"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IProgram
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(8)
  com.github.wshackle.fanuc.robotserver.IProgram program();


  /**
   * <p>
   * Getter method for the COM property "_NewEnum"
   * </p>
   */

  @DISPID(-4) //= 0xfffffffc. The runtime will prefer the VTID if present
  @VTID(9)
  java.util.Iterator<Com4jObject> iterator();

  /**
   * <p>
   * Returns/sets the value for each axis in the position.
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param jointNum Mandatory short parameter.
   * @return  Returns a value of type double
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(10)
  @DefaultMethod
  double item(
    short jointNum);


  /**
   * <p>
   * Returns/sets the value for each axis in the position.
   * </p>
   * <p>
   * Setter method for the COM property "Item"
   * </p>
   * @param jointNum Mandatory short parameter.
   * @param value Mandatory double parameter.
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(11)
  @DefaultMethod
  void item(
    short jointNum,
    double value);


  /**
   * <p>
   * Returns the parent object.
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IPosition2
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(12)
  com.github.wshackle.fanuc.robotserver.IPosition2 parent();


  /**
   * <p>
   * Returns the number of axes in the position.  
   * </p>
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(13)
  short count();


  // Properties:
}
