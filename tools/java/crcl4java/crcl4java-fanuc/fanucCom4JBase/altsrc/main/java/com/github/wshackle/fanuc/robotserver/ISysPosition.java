package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Provides access to a single controller system position.
 */
@IID("{6055D698-FFAE-11D0-B6F4-00C04FB9C401}")
public interface ISysPosition extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * <p>
   * Returns the position identification number for the position.
   * </p>
   * <p>
   * Getter method for the COM property "Id"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(8)
  int id();


  /**
   * <p>
   * Returns whether the specified group is supported on the controller.
   * </p>
   * <p>
   * Getter method for the COM property "GroupMask"
   * </p>
   * @param groupNum Mandatory short parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(9)
  boolean groupMask(
    short groupNum);


  /**
   * <p>
   * Returns a FRCSysGroupPosition object representing the system position in the specified motion group.
   * </p>
   * <p>
   * Getter method for the COM property "Group"
   * </p>
   * @param groupNum Mandatory short parameter.
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ISysGroupPosition
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(10)
  com.github.wshackle.fanuc.robotserver.ISysGroupPosition group(
    short groupNum);


  /**
   * <p>
   * Returns whether the robot is currently at the position.
   * </p>
   * <p>
   * Getter method for the COM property "IsAtCurPosition"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(11)
  boolean isAtCurPosition();


  /**
   * <p>
   * Moves the robot to this position for all groups defined on the controller.
   * </p>
   */

  @DISPID(251) //= 0xfb. The runtime will prefer the VTID if present
  @VTID(12)
  void moveto();


  /**
   * <p>
   * Records the current position of the robot to this position for all groups defined on the controller.
   * </p>
   */

  @DISPID(252) //= 0xfc. The runtime will prefer the VTID if present
  @VTID(13)
  void record();


  /**
   * <p>
   * Uninitializes the position for all groups defined on the controller. 
   * </p>
   */

  @DISPID(253) //= 0xfd. The runtime will prefer the VTID if present
  @VTID(14)
  void uninitialize();


  /**
   * <p>
   * Returns whether the position is initialized.
   * </p>
   * <p>
   * Getter method for the COM property "IsInitialized"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(205) //= 0xcd. The runtime will prefer the VTID if present
  @VTID(15)
  boolean isInitialized();


  /**
   * <p>
   * Reads fresh values for a position register, tool frame, user frame, or jog frame from the robot.
   * </p>
   */

  @DISPID(254) //= 0xfe. The runtime will prefer the VTID if present
  @VTID(16)
  void refresh();


  /**
   * <p>
   * Sends the local copy of a position register, tool frame, user frame, or jog frame to the robot.
   * </p>
   */

  @DISPID(255) //= 0xff. The runtime will prefer the VTID if present
  @VTID(17)
  void update();


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

  @DISPID(256) //= 0x100. The runtime will prefer the VTID if present
  @VTID(18)
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

  @DISPID(257) //= 0x101. The runtime will prefer the VTID if present
  @VTID(19)
  void copy(
    @MarshalAs(NativeType.Dispatch) com4j.Com4jObject position);


  /**
   * <p>
   * Returns the bit mask representation of the groups supported by this position.
   * </p>
   * <p>
   * Getter method for the COM property "GroupBitMask"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FREGroupBitMaskConstants
   */

  @DISPID(258) //= 0x102. The runtime will prefer the VTID if present
  @VTID(20)
  com.github.wshackle.fanuc.robotserver.FREGroupBitMaskConstants groupBitMask();


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

  @DISPID(259) //= 0x103. The runtime will prefer the VTID if present
  @VTID(21)
  boolean checkReach(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object from,
    @Optional @DefaultValue("6") com.github.wshackle.fanuc.robotserver.FREMotionTypeConstants moType,
    @Optional @DefaultValue("2") com.github.wshackle.fanuc.robotserver.FREOrientTypeConstants orientType,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object destination,
    @Optional @DefaultValue("0") Holder<com.github.wshackle.fanuc.robotserver.IMotionErrorInfo> motionErrorInfo);


  // Properties:
}
