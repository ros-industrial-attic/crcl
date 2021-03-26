package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

@IID("{B475BC9A-3AF1-11D4-9F66-00105AE428C3}")
public interface IPipePosition extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Formats"
   * </p>
   * @param type Mandatory com.github.wshackle.fanuc.robotserver.FRETypeCodeConstants parameter.
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(8)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject formats(
    com.github.wshackle.fanuc.robotserver.FRETypeCodeConstants type);


  /**
   * <p>
   * Returns the group number for the position.
   * </p>
   * <p>
   * Getter method for the COM property "GroupNum"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(9)
  short groupNum();


  /**
   * <p>
   * Getter method for the COM property "IsAtCurPosition"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(10)
  boolean isAtCurPosition();


  /**
   * <p>
   * Returns a boolean value that indicates if the positional data contained in the current object is 'almost equal to' the positional data of another object.
   * </p>
   * <p>
   * Getter method for the COM property "IsEqualTo"
   * </p>
   * @param targetPos Mandatory com4j.Com4jObject parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(11)
  boolean isEqualTo(
    @MarshalAs(NativeType.Dispatch) com4j.Com4jObject targetPos);


  /**
   * <p>
   * Getter method for the COM property "IsInitialized"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(205) //= 0xcd. The runtime will prefer the VTID if present
  @VTID(12)
  boolean isInitialized();


  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IPipeField
   */

  @DISPID(206) //= 0xce. The runtime will prefer the VTID if present
  @VTID(13)
  com.github.wshackle.fanuc.robotserver.IPipeField parent();


  /**
   * <p>
   * Getter method for the COM property "Pipe"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IPipe
   */

  @DISPID(207) //= 0xcf. The runtime will prefer the VTID if present
  @VTID(14)
  com.github.wshackle.fanuc.robotserver.IPipe pipe();


  /**
   * <p>
   * Getter method for the COM property "Type"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FRETypeCodeConstants
   */

  @DISPID(208) //= 0xd0. The runtime will prefer the VTID if present
  @VTID(15)
  com.github.wshackle.fanuc.robotserver.FRETypeCodeConstants type();


  // Properties:
}
