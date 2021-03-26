package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Provides access to a single position in a TP program.
 */
@IID("{3A49BE60-F5B9-11CF-8901-0020AF68F0A3}")
public interface ITPPosition extends com.github.wshackle.fanuc.robotserver.IRobotObject {
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
   * Returns/sets the text comment associated with the TP position.
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
   * Returns/sets the text comment associated with the TP position.
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
   * Returns the position identification number for the TP position.
   * </p>
   * <p>
   * Getter method for the COM property "Id"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(11)
  int id();


  /**
   * <p>
   * Returns whether the particular motion group is defined for the TP position.
   * </p>
   * <p>
   * Getter method for the COM property "GroupMask"
   * </p>
   * @param groupNum Mandatory short parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(12)
  boolean groupMask(
    short groupNum);


  /**
   * <p>
   * Returns a FRCGroupPosition object representing the TP position in the specified motion group.
   * </p>
   * <p>
   * Getter method for the COM property "Group"
   * </p>
   * @param groupNum Mandatory short parameter.
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IGroupPosition
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(13)
  com.github.wshackle.fanuc.robotserver.IGroupPosition group(
    short groupNum);


  /**
   * <p>
   * Returns whether the robot is currently at the TP position.
   * </p>
   * <p>
   * Getter method for the COM property "IsAtCurPosition"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(205) //= 0xcd. The runtime will prefer the VTID if present
  @VTID(14)
  boolean isAtCurPosition();


  /**
   * <p>
   * Returns the reference count of the TP position.
   * </p>
   * <p>
   * Getter method for the COM property "ReferenceCount"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(206) //= 0xce. The runtime will prefer the VTID if present
  @VTID(15)
  int referenceCount();


  /**
   * <p>
   * Returns/sets the TP position in a binary variant.
   * </p>
   * <p>
   * Getter method for the COM property "Binary"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(207) //= 0xcf. The runtime will prefer the VTID if present
  @VTID(16)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object binary();


  /**
   * <p>
   * Returns/sets the TP position in a binary variant.
   * </p>
   * <p>
   * Setter method for the COM property "Binary"
   * </p>
   * @param binaryPosData Mandatory java.lang.Object parameter.
   */

  @DISPID(207) //= 0xcf. The runtime will prefer the VTID if present
  @VTID(17)
  void binary(
    @MarshalAs(NativeType.VARIANT) java.lang.Object binaryPosData);


  /**
   * <p>
   * Moves the robot to this position for all groups defined for the TP position.
   * </p>
   */

  @DISPID(251) //= 0xfb. The runtime will prefer the VTID if present
  @VTID(18)
  void moveto();


  /**
   * <p>
   * Records the current position of the robot to this position for all groups defined for the TP position.
   * </p>
   */

  @DISPID(252) //= 0xfc. The runtime will prefer the VTID if present
  @VTID(19)
  void record();


  /**
   * <p>
   * Uninitializes the position for all groups defined for the TP position. 
   * </p>
   */

  @DISPID(253) //= 0xfd. The runtime will prefer the VTID if present
  @VTID(20)
  void uninitialize();


  /**
   * <p>
   * Returns True if the TP position has been taught (or has had its positional values manually set), False if it has not.
   * </p>
   * <p>
   * Getter method for the COM property "IsInitialized"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(208) //= 0xd0. The runtime will prefer the VTID if present
  @VTID(21)
  boolean isInitialized();


  /**
   * <p>
   * Reads fresh values for a TP position from the robot.
   * </p>
   */

  @DISPID(254) //= 0xfe. The runtime will prefer the VTID if present
  @VTID(22)
  void refresh();


  /**
   * <p>
   * Sends the local copy of a TP position to the robot.
   * </p>
   */

  @DISPID(255) //= 0xff. The runtime will prefer the VTID if present
  @VTID(23)
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
  @VTID(24)
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
  @VTID(25)
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
  @VTID(26)
  com.github.wshackle.fanuc.robotserver.FREGroupBitMaskConstants groupBitMask();


  /**
   * <p>
   * Postion data for the First exist group in this TP position
   * </p>
   * <p>
   * Getter method for the COM property "FirstExistGroup"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IXyzWpr
   */

  @DISPID(259) //= 0x103. The runtime will prefer the VTID if present
  @VTID(27)
  com.github.wshackle.fanuc.robotserver.IXyzWpr firstExistGroup();


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

  @DISPID(260) //= 0x104. The runtime will prefer the VTID if present
  @VTID(28)
  boolean checkReach(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object from,
    @Optional @DefaultValue("6") com.github.wshackle.fanuc.robotserver.FREMotionTypeConstants moType,
    @Optional @DefaultValue("2") com.github.wshackle.fanuc.robotserver.FREOrientTypeConstants orientType,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object destination,
    @Optional @DefaultValue("0") Holder<com.github.wshackle.fanuc.robotserver.IMotionErrorInfo> motionErrorInfo);


  // Properties:
}
