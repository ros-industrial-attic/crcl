package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Provides access to the positional data for a position stored as a variable on the controller.
 */
@IID("{E3FFB438-2613-11D1-B702-00C04FB9C401}")
public interface IVarPosition extends com.github.wshackle.fanuc.robotserver.IPosition {
  // Methods:
  /**
   * <p>
   *  Returns the parent object.
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IVar
   */

  @DISPID(301) //= 0x12d. The runtime will prefer the VTID if present
  @VTID(32)
  com.github.wshackle.fanuc.robotserver.IVar parent();


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

  @DISPID(302) //= 0x12e. The runtime will prefer the VTID if present
  @VTID(33)
  boolean isEqualTo(
    @MarshalAs(NativeType.Dispatch) com4j.Com4jObject targetPos);


  /**
   * <p>
   * Returns a Boolean indicating if the robot can reach the position or not.
   * </p>
   * <p>
   * Getter method for the COM property "IsReachable"
   * </p>
   * @param from Optional parameter. Default value is com4j.Variant.getMissing()
   * @param moType Optional parameter. Default value is 6
   * @param orientType Optional parameter. Default value is 2
   * @param destination Optional parameter. Default value is com4j.Variant.getMissing()
   * @param motionErrorInfo Optional parameter. Default value is 0
   * @return  Returns a value of type boolean
   */

  @DISPID(303) //= 0x12f. The runtime will prefer the VTID if present
  @VTID(34)
  boolean isReachable(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object from,
    @Optional @DefaultValue("6") com.github.wshackle.fanuc.robotserver.FREMotionTypeConstants moType,
    @Optional @DefaultValue("2") com.github.wshackle.fanuc.robotserver.FREOrientTypeConstants orientType,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object destination,
    @Optional @DefaultValue("0") Holder<com.github.wshackle.fanuc.robotserver.IMotionErrorInfo> motionErrorInfo);


  /**
   * <p>
   * Copies the positional data from another object into this one.
   * </p>
   * @param position Mandatory com4j.Com4jObject parameter.
   */

  @DISPID(304) //= 0x130. The runtime will prefer the VTID if present
  @VTID(35)
  void copy(
    @MarshalAs(NativeType.Dispatch) com4j.Com4jObject position);


  /**
   * <p>
   * Returns a Boolean indicating if the robot can reach the position or not.
   * </p>
   * @param from Optional parameter. Default value is com4j.Variant.getMissing()
   * @param moType Optional parameter. Default value is 6
   * @param orientType Optional parameter. Default value is 2
   * @param destination Optional parameter. Default value is com4j.Variant.getMissing()
   * @param motionErrorInfo Optional parameter. Default value is 0
   * @return  Returns a value of type boolean
   */

  @DISPID(305) //= 0x131. The runtime will prefer the VTID if present
  @VTID(36)
  boolean checkReach(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object from,
    @Optional @DefaultValue("6") com.github.wshackle.fanuc.robotserver.FREMotionTypeConstants moType,
    @Optional @DefaultValue("2") com.github.wshackle.fanuc.robotserver.FREOrientTypeConstants orientType,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object destination,
    @Optional @DefaultValue("0") Holder<com.github.wshackle.fanuc.robotserver.IMotionErrorInfo> motionErrorInfo);


  // Properties:
}
