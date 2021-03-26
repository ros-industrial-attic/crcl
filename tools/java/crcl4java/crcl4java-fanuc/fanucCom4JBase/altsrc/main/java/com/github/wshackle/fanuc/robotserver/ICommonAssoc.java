package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * This object is used to access standard associated data common for all KAREL path motion groups.
 */
@IID("{34E3E250-1107-11D2-86F4-00C04F9184DB}")
public interface ICommonAssoc extends com.github.wshackle.fanuc.robotserver.IRobotObject {
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
   * Returns/sets the termination type.  
   * </p>
   * <p>
   * Getter method for the COM property "SegTermType"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FRETermTypeConstants
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(9)
  com.github.wshackle.fanuc.robotserver.FRETermTypeConstants segTermType();


  /**
   * <p>
   * Returns/sets the termination type.  
   * </p>
   * <p>
   * Setter method for the COM property "SegTermType"
   * </p>
   * @param segTermType Mandatory com.github.wshackle.fanuc.robotserver.FRETermTypeConstants parameter.
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(10)
  void segTermType(
    com.github.wshackle.fanuc.robotserver.FRETermTypeConstants segTermType);


  /**
   * <p>
   * Returns/sets the deceleration tolerance.
   * </p>
   * <p>
   * Getter method for the COM property "SegDecelTol"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(11)
  short segDecelTol();


  /**
   * <p>
   * Returns/sets the deceleration tolerance.
   * </p>
   * <p>
   * Setter method for the COM property "SegDecelTol"
   * </p>
   * @param segDecelTol Mandatory short parameter.
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(12)
  void segDecelTol(
    short segDecelTol);


  /**
   * <p>
   * Returns/sets the acceleration override.
   * </p>
   * <p>
   * Getter method for the COM property "SegRelAccel"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(13)
  short segRelAccel();


  /**
   * <p>
   * Returns/sets the acceleration override.
   * </p>
   * <p>
   * Setter method for the COM property "SegRelAccel"
   * </p>
   * @param segRelAccel Mandatory short parameter.
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(14)
  void segRelAccel(
    short segRelAccel);


  /**
   * <p>
   * Returns/sets the time shift override.
   * </p>
   * <p>
   * Getter method for the COM property "SegTimeShift"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(15)
  short segTimeShift();


  /**
   * <p>
   * Returns/sets the time shift override.
   * </p>
   * <p>
   * Setter method for the COM property "SegTimeShift"
   * </p>
   * @param segTimeShift Mandatory short parameter.
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(16)
  void segTimeShift(
    short segTimeShift);


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
