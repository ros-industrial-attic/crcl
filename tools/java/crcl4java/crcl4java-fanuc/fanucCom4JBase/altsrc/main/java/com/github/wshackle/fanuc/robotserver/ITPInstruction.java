package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

@IID("{3C05D26F-9BE8-11D1-B6FC-00C04FA3BD85}")
public interface ITPInstruction extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "LMCode"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(8)
  short lmCode();


  /**
   * <p>
   * Getter method for the COM property "OptionID1"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(9)
  short optionID1();


  /**
   * <p>
   * Getter method for the COM property "OptionID2"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(10)
  short optionID2();


  /**
   * <p>
   * Getter method for the COM property "OptionID3"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(104) //= 0x68. The runtime will prefer the VTID if present
  @VTID(11)
  short optionID3();


  /**
   * <p>
   * Getter method for the COM property "Caption"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(105) //= 0x69. The runtime will prefer the VTID if present
  @VTID(12)
  java.lang.String caption();


  /**
   * <p>
   * Setter method for the COM property "Caption"
   * </p>
   * @param caption Mandatory java.lang.String parameter.
   */

  @DISPID(105) //= 0x69. The runtime will prefer the VTID if present
  @VTID(13)
  void caption(
    java.lang.String caption);


  /**
   * <p>
   * Getter method for the COM property "LinesProgID"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(106) //= 0x6a. The runtime will prefer the VTID if present
  @VTID(14)
  java.lang.String linesProgID();


  /**
   * <p>
   * Setter method for the COM property "LinesProgID"
   * </p>
   * @param linesProgID Mandatory java.lang.String parameter.
   */

  @DISPID(106) //= 0x6a. The runtime will prefer the VTID if present
  @VTID(15)
  void linesProgID(
    java.lang.String linesProgID);


  /**
   * <p>
   * Getter method for the COM property "EditProgID"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(107) //= 0x6b. The runtime will prefer the VTID if present
  @VTID(16)
  java.lang.String editProgID();


  /**
   * <p>
   * Setter method for the COM property "EditProgID"
   * </p>
   * @param editProgID Mandatory java.lang.String parameter.
   */

  @DISPID(107) //= 0x6b. The runtime will prefer the VTID if present
  @VTID(17)
  void editProgID(
    java.lang.String editProgID);


  /**
   * <p>
   * Getter method for the COM property "PictureName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(108) //= 0x6c. The runtime will prefer the VTID if present
  @VTID(18)
  java.lang.String pictureName();


  /**
   * <p>
   * Setter method for the COM property "PictureName"
   * </p>
   * @param pictureName Mandatory java.lang.String parameter.
   */

  @DISPID(108) //= 0x6c. The runtime will prefer the VTID if present
  @VTID(19)
  void pictureName(
    java.lang.String pictureName);


  /**
   * <p>
   * Getter method for the COM property "IsMotionAppend"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(109) //= 0x6d. The runtime will prefer the VTID if present
  @VTID(20)
  boolean isMotionAppend();


  /**
   * <p>
   * Setter method for the COM property "IsMotionAppend"
   * </p>
   * @param isMotionAppend Mandatory boolean parameter.
   */

  @DISPID(109) //= 0x6d. The runtime will prefer the VTID if present
  @VTID(21)
  void isMotionAppend(
    boolean isMotionAppend);


  // Properties:
}
