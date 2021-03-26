package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * This object allows access to string registers on the robot controller.
 */
@IID("{CADEF7CB-650F-45B7-BC95-9080FA32640B}")
public interface IRegString extends com.github.wshackle.fanuc.robotserver.IRobotObject {
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
   * Returns/sets the comment associated with the string register.
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
   * Returns/sets the comment associated with the string register.
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
   * Returns/sets the string register's value.
   * </p>
   * <p>
   * Getter method for the COM property "Value"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(11)
  java.lang.String value();


  /**
   * <p>
   * Returns/sets the string register's value.
   * </p>
   * <p>
   * Setter method for the COM property "Value"
   * </p>
   * @param value Mandatory java.lang.String parameter.
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(12)
  void value(
    java.lang.String value);


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
  @VTID(13)
  com.github.wshackle.fanuc.robotserver.IVar parent();


  // Properties:
}
