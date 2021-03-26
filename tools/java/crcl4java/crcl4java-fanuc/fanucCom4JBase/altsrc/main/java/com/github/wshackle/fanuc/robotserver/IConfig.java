package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Represents a Cartesian position’s configuration.
 */
@IID("{C58B0E60-ECD4-11D0-9FA5-00A024329125}")
public interface IConfig extends com.github.wshackle.fanuc.robotserver.IRobotObject {
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
   * Returns/sets the ASCII (text) representation of the configuration.
   * </p>
   * <p>
   * Getter method for the COM property "Text"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  java.lang.String text();


  /**
   * <p>
   * Returns/sets the ASCII (text) representation of the configuration.
   * </p>
   * <p>
   * Setter method for the COM property "Text"
   * </p>
   * @param text Mandatory java.lang.String parameter.
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(10)
  @DefaultMethod
  void text(
    java.lang.String text);


  /**
   * <p>
   * Returns/sets each turn number of the configuration.
   * </p>
   * <p>
   * Getter method for the COM property "TurnNum"
   * </p>
   * @param index Mandatory short parameter.
   * @return  Returns a value of type short
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(11)
  short turnNum(
    short index);


  /**
   * <p>
   * Returns/sets each turn number of the configuration.
   * </p>
   * <p>
   * Setter method for the COM property "TurnNum"
   * </p>
   * @param index Mandatory short parameter.
   * @param turnNum Mandatory short parameter.
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(12)
  void turnNum(
    short index,
    short turnNum);


  /**
   * <p>
   * Returns/sets the “Flip” property for the configuration.
   * </p>
   * <p>
   * Getter method for the COM property "Flip"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(13)
  boolean flip();


  /**
   * <p>
   * Returns/sets the “Flip” property for the configuration.
   * </p>
   * <p>
   * Setter method for the COM property "Flip"
   * </p>
   * @param flip Mandatory boolean parameter.
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(14)
  void flip(
    boolean flip);


  /**
   * <p>
   * Returns/sets the “Left” property for the configuration.
   * </p>
   * <p>
   * Getter method for the COM property "Left"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(15)
  boolean left();


  /**
   * <p>
   * Returns/sets the “Left” property for the configuration.
   * </p>
   * <p>
   * Setter method for the COM property "Left"
   * </p>
   * @param left Mandatory boolean parameter.
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(16)
  void left(
    boolean left);


  /**
   * <p>
   * Returns/sets the “Up” prooperty for the configuration.
   * </p>
   * <p>
   * Getter method for the COM property "Up"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(17)
  boolean up();


  /**
   * <p>
   * Returns/sets the “Up” prooperty for the configuration.
   * </p>
   * <p>
   * Setter method for the COM property "Up"
   * </p>
   * @param up Mandatory boolean parameter.
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(18)
  void up(
    boolean up);


  /**
   * <p>
   * Returns/sets the “Front” property for the configuration.
   * </p>
   * <p>
   * Getter method for the COM property "Front"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(205) //= 0xcd. The runtime will prefer the VTID if present
  @VTID(19)
  boolean front();


  /**
   * <p>
   * Returns/sets the “Front” property for the configuration.
   * </p>
   * <p>
   * Setter method for the COM property "Front"
   * </p>
   * @param front Mandatory boolean parameter.
   */

  @DISPID(205) //= 0xcd. The runtime will prefer the VTID if present
  @VTID(20)
  void front(
    boolean front);


  /**
   * <p>
   * Returns the parent object.
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(206) //= 0xce. The runtime will prefer the VTID if present
  @VTID(21)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject parent();


  // Properties:
}
