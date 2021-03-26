package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

@IID("{6C473F21-B5F0-11D2-8781-00C04F98D092}")
public interface ITPSimpleLine extends com.github.wshackle.fanuc.robotserver.IRobotObject {
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
   * Getter method for the COM property "Binary"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(9)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object binary();


  /**
   * <p>
   * Setter method for the COM property "Binary"
   * </p>
   * @param binary Mandatory java.lang.Object parameter.
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(10)
  void binary(
    @MarshalAs(NativeType.VARIANT) java.lang.Object binary);


  /**
   * <p>
   * Getter method for the COM property "Number"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(11)
  int number();


  /**
   * <p>
   * Getter method for the COM property "MnCode"
   * </p>
   * @return  Returns a value of type byte
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(12)
  byte mnCode();


  /**
   * <p>
   * Getter method for the COM property "Text"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(13)
  java.lang.String text();


  /**
   * <p>
   * Getter method for the COM property "BinNoUpdate"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(205) //= 0xcd. The runtime will prefer the VTID if present
  @VTID(14)
  boolean binNoUpdate();


  /**
   * <p>
   * Setter method for the COM property "BinNoUpdate"
   * </p>
   * @param noUpdate Mandatory boolean parameter.
   */

  @DISPID(205) //= 0xcd. The runtime will prefer the VTID if present
  @VTID(15)
  void binNoUpdate(
    boolean noUpdate);


  /**
   */

  @DISPID(206) //= 0xce. The runtime will prefer the VTID if present
  @VTID(16)
  void binUpdate();


  /**
   * @param indexOfByte Mandatory int parameter.
   * @return  Returns a value of type float
   */

  @DISPID(207) //= 0xcf. The runtime will prefer the VTID if present
  @VTID(17)
  float binGetFloat(
    int indexOfByte);


  /**
   * @param indexOfByte Mandatory int parameter.
   * @param fNewValue Mandatory float parameter.
   */

  @DISPID(208) //= 0xd0. The runtime will prefer the VTID if present
  @VTID(18)
  void binSetFloat(
    int indexOfByte,
    float fNewValue);


  /**
   * @param text Mandatory java.lang.String parameter.
   */

  @DISPID(209) //= 0xd1. The runtime will prefer the VTID if present
  @VTID(19)
  void changeLine(
    java.lang.String text);


  // Properties:
}
