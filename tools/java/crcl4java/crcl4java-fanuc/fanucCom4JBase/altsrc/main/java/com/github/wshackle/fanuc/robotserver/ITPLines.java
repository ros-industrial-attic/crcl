package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

@IID("{F5C31106-46AE-11D0-8901-0020AF68F0A3}")
public interface ITPLines extends com.github.wshackle.fanuc.robotserver.IRobotObject,Iterable<Com4jObject> {
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
   * @param lineNum Mandatory int parameter.
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(10)
  @DefaultMethod
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject item(
    int lineNum);


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
   * <p>
   * Getter method for the COM property "CurLineNum"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(12)
  int curLineNum();


  /**
   * <p>
   * Setter method for the COM property "CurLineNum"
   * </p>
   * @param curLineNum Mandatory int parameter.
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(13)
  void curLineNum(
    int curLineNum);


  /**
   * <p>
   * Getter method for the COM property "SelectedLines"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ISelectedLines
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(14)
  com.github.wshackle.fanuc.robotserver.ISelectedLines selectedLines();


  /**
   * @param lineNum Mandatory int parameter.
   */

  @DISPID(251) //= 0xfb. The runtime will prefer the VTID if present
  @VTID(15)
  void remove(
    int lineNum);


  /**
   * @param mnCode Mandatory short parameter.
   * @param insertLine Mandatory int parameter.
   * @param optParam1 Optional parameter. Default value is com4j.Variant.getMissing()
   * @param optParam2 Optional parameter. Default value is com4j.Variant.getMissing()
   * @param optParam3 Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(252) //= 0xfc. The runtime will prefer the VTID if present
  @VTID(16)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject add(
    short mnCode,
    int insertLine,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object optParam1,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object optParam2,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object optParam3);


  /**
   * <p>
   * Getter method for the COM property "FullText"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(17)
  java.lang.String fullText();


    // Properties:
  }
