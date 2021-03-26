package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

@IID("{59DC16FE-AF91-11D0-A072-00A02436CF7E}")
public interface IUserIOSignal extends com.github.wshackle.fanuc.robotserver.IIOSignal {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Index"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(16)
  int index();


  /**
   * <p>
   * Setter method for the COM property "Index"
   * </p>
   * @param index Mandatory int parameter.
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(17)
  void index(
    int index);


  /**
   * <p>
   * Getter method for the COM property "LogicalType"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(18)
  short logicalType();


  /**
   * <p>
   * Getter method for the COM property "Value"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(19)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object value();


  /**
   * <p>
   * Setter method for the COM property "Value"
   * </p>
   * @param value Mandatory java.lang.Object parameter.
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(20)
  void value(
    @MarshalAs(NativeType.VARIANT) java.lang.Object value);


  /**
   * <p>
   * Getter method for the COM property "Simulate"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(21)
  boolean simulate();


  /**
   * <p>
   * Setter method for the COM property "Simulate"
   * </p>
   * @param simulate Mandatory boolean parameter.
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(22)
  void simulate(
    boolean simulate);


  /**
   * <p>
   * Getter method for the COM property "Polarity"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(205) //= 0xcd. The runtime will prefer the VTID if present
  @VTID(23)
  boolean polarity();


  /**
   * <p>
   * Setter method for the COM property "Polarity"
   * </p>
   * @param polarity Mandatory boolean parameter.
   */

  @DISPID(205) //= 0xcd. The runtime will prefer the VTID if present
  @VTID(24)
  void polarity(
    boolean polarity);


  /**
   * <p>
   * Getter method for the COM property "Complementary"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(206) //= 0xce. The runtime will prefer the VTID if present
  @VTID(25)
  boolean complementary();


  /**
   * <p>
   * Setter method for the COM property "Complementary"
   * </p>
   * @param complementary Mandatory boolean parameter.
   */

  @DISPID(206) //= 0xce. The runtime will prefer the VTID if present
  @VTID(26)
  void complementary(
    boolean complementary);


  /**
   * <p>
   * Getter method for the COM property "IsInput"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(207) //= 0xcf. The runtime will prefer the VTID if present
  @VTID(27)
  boolean isInput();


  /**
   * <p>
   * Getter method for the COM property "IsBoolean"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(208) //= 0xd0. The runtime will prefer the VTID if present
  @VTID(28)
  boolean isBoolean();


  /**
   * <p>
   * Getter method for the COM property "CanSimulate"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(209) //= 0xd1. The runtime will prefer the VTID if present
  @VTID(29)
  boolean canSimulate();


  /**
   * <p>
   * Getter method for the COM property "CanInvert"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(210) //= 0xd2. The runtime will prefer the VTID if present
  @VTID(30)
  boolean canInvert();


  /**
   * <p>
   * Getter method for the COM property "CanComplement"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(211) //= 0xd3. The runtime will prefer the VTID if present
  @VTID(31)
  boolean canComplement();


  /**
   * <p>
   * Returns the owning I/O type
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IUserIOSignals
   */

  @DISPID(212) //= 0xd4. The runtime will prefer the VTID if present
  @VTID(32)
  com.github.wshackle.fanuc.robotserver.IUserIOSignals parent();


  // Properties:
}
