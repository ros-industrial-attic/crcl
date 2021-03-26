package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * This object is used to access standard associated data for all KAREL path motion groups.
 */
@IID("{424160A0-1108-11D2-86F4-00C04F9184DB}")
public interface IGroupAssoc extends com.github.wshackle.fanuc.robotserver.IRobotObject {
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
   * Returns/sets the speed override.
   * </p>
   * <p>
   * Getter method for the COM property "SegRelSpeed"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(9)
  short segRelSpeed();


  /**
   * <p>
   * Returns/sets the speed override.
   * </p>
   * <p>
   * Setter method for the COM property "SegRelSpeed"
   * </p>
   * @param segRelSpeed Mandatory short parameter.
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(10)
  void segRelSpeed(
    short segRelSpeed);


  /**
   * <p>
   * Returns/sets the motion type.
   * </p>
   * <p>
   * Getter method for the COM property "SegMoType"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FREMotionTypeConstants
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(11)
  com.github.wshackle.fanuc.robotserver.FREMotionTypeConstants segMoType();


  /**
   * <p>
   * Returns/sets the motion type.
   * </p>
   * <p>
   * Setter method for the COM property "SegMoType"
   * </p>
   * @param segMoType Mandatory com.github.wshackle.fanuc.robotserver.FREMotionTypeConstants parameter.
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(12)
  void segMoType(
    com.github.wshackle.fanuc.robotserver.FREMotionTypeConstants segMoType);


  /**
   * <p>
   * Returns/sets the orientation control.
   * </p>
   * <p>
   * Getter method for the COM property "SegOrientType"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FREOrientTypeConstants
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(13)
  com.github.wshackle.fanuc.robotserver.FREOrientTypeConstants segOrientType();


  /**
   * <p>
   * Returns/sets the orientation control.
   * </p>
   * <p>
   * Setter method for the COM property "SegOrientType"
   * </p>
   * @param segOrientType Mandatory com.github.wshackle.fanuc.robotserver.FREOrientTypeConstants parameter.
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(14)
  void segOrientType(
    com.github.wshackle.fanuc.robotserver.FREOrientTypeConstants segOrientType);


  /**
   * <p>
   * Returns/sets the segment break property.
   * </p>
   * <p>
   * Getter method for the COM property "SegBreak"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(15)
  boolean segBreak();


  /**
   * <p>
   * Returns/sets the segment break property.
   * </p>
   * <p>
   * Setter method for the COM property "SegBreak"
   * </p>
   * @param segBreak Mandatory boolean parameter.
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(16)
  void segBreak(
    boolean segBreak);


  /**
   * <p>
   *  Returns the owning variable object.
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IVar
   */

  @DISPID(205) //= 0xcd. The runtime will prefer the VTID if present
  @VTID(17)
  com.github.wshackle.fanuc.robotserver.IVar parent();


  // Properties:
}
