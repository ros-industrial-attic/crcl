package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * This object allows access to numeric registers on the robot controller.
 */
@IID("{6B51A440-212A-11D0-959F-00A024329125}")
public interface IRegNumeric extends com.github.wshackle.fanuc.robotserver.IRobotObject {
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
   * Returns/sets the comment associated with the numeric register.
   * </p>
   * <p>
   * Getter method for the COM property "Comment"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(9)
  java.lang.String comment();


  /**
   * <p>
   * Returns/sets the comment associated with the numeric register.
   * </p>
   * <p>
   * Setter method for the COM property "Comment"
   * </p>
   * @param comment Mandatory java.lang.String parameter.
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(10)
  void comment(
    java.lang.String comment);


  /**
   * <p>
   * Returns/sets the long value for the numeric register.
   * </p>
   * <p>
   * Getter method for the COM property "RegLong"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(11)
  int regLong();


  /**
   * <p>
   * Returns/sets the long value for the numeric register.
   * </p>
   * <p>
   * Setter method for the COM property "RegLong"
   * </p>
   * @param regLong Mandatory int parameter.
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(12)
  void regLong(
    int regLong);


  /**
   * <p>
   * Returns/sets the float value for the numeric register.
   * </p>
   * <p>
   * Getter method for the COM property "RegFloat"
   * </p>
   * @return  Returns a value of type float
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(13)
  float regFloat();


  /**
   * <p>
   * Returns/sets the float value for the numeric register.
   * </p>
   * <p>
   * Setter method for the COM property "RegFloat"
   * </p>
   * @param regFloat Mandatory float parameter.
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(14)
  void regFloat(
    float regFloat);


  /**
   * <p>
   * Returns the type of numeric value currently contained in the register.
   * </p>
   * <p>
   * Getter method for the COM property "Type"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FRETypeCodeConstants
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(15)
  com.github.wshackle.fanuc.robotserver.FRETypeCodeConstants type();


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
  @VTID(16)
  com.github.wshackle.fanuc.robotserver.IVar parent();


  // Properties:
}
