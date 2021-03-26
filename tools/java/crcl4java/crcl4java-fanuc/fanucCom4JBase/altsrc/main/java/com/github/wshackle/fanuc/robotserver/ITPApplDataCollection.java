package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

@IID("{A7E095A2-DCDE-11D0-A083-00A02436CF7E}")
public interface ITPApplDataCollection extends com.github.wshackle.fanuc.robotserver.IRobotObject,Iterable<Com4jObject> {
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
   * Getter method for the COM property "Item"
   * </p>
   * @param name Optional parameter. Default value is com4j.Variant.getMissing()
   * @param index1 Optional parameter. Default value is com4j.Variant.getMissing()
   * @param index2 Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(10)
  @DefaultMethod
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject item(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object name,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index1,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index2);


  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(11)
  int count();


  /**
   * @param name Mandatory java.lang.String parameter.
   * @param index1 Optional parameter. Default value is com4j.Variant.getMissing()
   * @param index2 Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(251) //= 0xfb. The runtime will prefer the VTID if present
  @VTID(12)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject add(
    java.lang.String name,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index1,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index2);


  /**
   * @param name Optional parameter. Default value is com4j.Variant.getMissing()
   * @param index1 Optional parameter. Default value is com4j.Variant.getMissing()
   * @param index2 Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(252) //= 0xfc. The runtime will prefer the VTID if present
  @VTID(13)
  void remove(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object name,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index1,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index2);


  // Properties:
}
