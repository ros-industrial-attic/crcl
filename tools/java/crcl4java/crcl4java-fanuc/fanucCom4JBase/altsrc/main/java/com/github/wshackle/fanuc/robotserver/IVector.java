package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Provides access to individual components of a vector.
 */
@IID("{924CC060-0F7A-11D2-86F4-00C04F9184DB}")
public interface IVector extends com.github.wshackle.fanuc.robotserver.IRobotObject,Iterable<Com4jObject> {
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
   * Returns/sets the component values for the vector.
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param valueNum Mandatory short parameter.
   * @return  Returns a value of type double
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(10)
  @DefaultMethod
  double item(
    short valueNum);


  /**
   * <p>
   * Returns/sets the component values for the vector.
   * </p>
   * <p>
   * Setter method for the COM property "Item"
   * </p>
   * @param valueNum Mandatory short parameter.
   * @param value Mandatory double parameter.
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(11)
  @DefaultMethod
  void item(
    short valueNum,
    double value);


  /**
   * <p>
   * Returns/sets the X component of the vector.
   * </p>
   * <p>
   * Getter method for the COM property "X"
   * </p>
   * @return  Returns a value of type double
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(12)
  double x();


  /**
   * <p>
   * Returns/sets the X component of the vector.
   * </p>
   * <p>
   * Setter method for the COM property "X"
   * </p>
   * @param x Mandatory double parameter.
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(13)
  void x(
    double x);


  /**
   * <p>
   * Returns/sets the Y component of the vector.
   * </p>
   * <p>
   * Getter method for the COM property "Y"
   * </p>
   * @return  Returns a value of type double
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(14)
  double y();


  /**
   * <p>
   * Returns/sets the Y component of the vector.
   * </p>
   * <p>
   * Setter method for the COM property "Y"
   * </p>
   * @param y Mandatory double parameter.
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(15)
  void y(
    double y);


  /**
   * <p>
   * Returns/sets the Z component of the vector.
   * </p>
   * <p>
   * Getter method for the COM property "Z"
   * </p>
   * @return  Returns a value of type double
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(16)
  double z();


  /**
   * <p>
   * Returns/sets the Z component of the vector.
   * </p>
   * <p>
   * Setter method for the COM property "Z"
   * </p>
   * @param z Mandatory double parameter.
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(17)
  void z(
    double z);


  /**
   * <p>
   * Returns the parent of the vector object.
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(18)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject parent();


  // Properties:
}
