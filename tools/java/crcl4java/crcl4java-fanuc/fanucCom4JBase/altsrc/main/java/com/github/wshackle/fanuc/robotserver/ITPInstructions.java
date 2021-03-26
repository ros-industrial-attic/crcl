package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

@IID("{3C05D26D-9BE8-11D1-B6FC-00C04FA3BD85}")
public interface ITPInstructions extends com.github.wshackle.fanuc.robotserver.IRobotObject,Iterable<Com4jObject> {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "_NewEnum"
   * </p>
   */

  @DISPID(-4) //= 0xfffffffc. The runtime will prefer the VTID if present
  @VTID(8)
  java.util.Iterator<Com4jObject> iterator();

  /**
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param lmCode Optional parameter. Default value is com4j.Variant.getMissing()
   * @param index Optional parameter. Default value is com4j.Variant.getMissing()
   * @param optParam1 Optional parameter. Default value is com4j.Variant.getMissing()
   * @param optParam2 Optional parameter. Default value is com4j.Variant.getMissing()
   * @param optParam3 Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ITPInstruction
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  com.github.wshackle.fanuc.robotserver.ITPInstruction item(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object lmCode,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object optParam1,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object optParam2,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object optParam3);


  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(10)
  int count();


  /**
   * <p>
   * Getter method for the COM property "IsValid"
   * </p>
   * @param lmCode Mandatory short parameter.
   * @param optParam1 Optional parameter. Default value is com4j.Variant.getMissing()
   * @param optParam2 Optional parameter. Default value is com4j.Variant.getMissing()
   * @param optParam3 Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type boolean
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(11)
  boolean isValid(
    short lmCode,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object optParam1,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object optParam2,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object optParam3);


  /**
   * @param lmCode Mandatory short parameter.
   * @param optParam1 Optional parameter. Default value is com4j.Variant.getMissing()
   * @param optParam2 Optional parameter. Default value is com4j.Variant.getMissing()
   * @param optParam3 Optional parameter. Default value is com4j.Variant.getMissing()
   * @param caption Mandatory java.lang.String parameter.
   * @param linesProgID Mandatory java.lang.String parameter.
   * @param editProgID Mandatory java.lang.String parameter.
   * @param pictureName Mandatory java.lang.String parameter.
   * @param isMotionAppend Mandatory boolean parameter.
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ITPInstruction
   */

  @DISPID(150) //= 0x96. The runtime will prefer the VTID if present
  @VTID(12)
  com.github.wshackle.fanuc.robotserver.ITPInstruction add(
    short lmCode,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object optParam1,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object optParam2,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object optParam3,
    java.lang.String caption,
    java.lang.String linesProgID,
    java.lang.String editProgID,
    java.lang.String pictureName,
    boolean isMotionAppend);


  /**
   * @param lmCode Mandatory short parameter.
   * @param optParam1 Optional parameter. Default value is com4j.Variant.getMissing()
   * @param optParam2 Optional parameter. Default value is com4j.Variant.getMissing()
   * @param optParam3 Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(151) //= 0x97. The runtime will prefer the VTID if present
  @VTID(13)
  void remove(
    short lmCode,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object optParam1,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object optParam2,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object optParam3);


  // Properties:
}
