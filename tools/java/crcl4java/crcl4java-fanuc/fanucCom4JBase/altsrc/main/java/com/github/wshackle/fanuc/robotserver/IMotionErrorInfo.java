package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Provides access to the status of a motion operation.
 */
@IID("{50F24196-4CB8-4375-96C3-A05885F4189D}")
public interface IMotionErrorInfo extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * <p>
   * Returns the group number for which the error applies.
   * </p>
   * <p>
   * Getter method for the COM property "GroupNum"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(8)
  int groupNum();


  /**
   * <p>
   * Returns a bit mask from which you can determine which joint(s) caused the error.
   * </p>
   * <p>
   * Getter method for the COM property "JointBitMask"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FREJointBitMaskConstants
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(9)
  com.github.wshackle.fanuc.robotserver.FREJointBitMaskConstants jointBitMask();


  /**
   * <p>
   * Returns the motion task ID as defined in mocons.hc.
   * </p>
   * <p>
   * Getter method for the COM property "MoTaskId"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(10)
  int moTaskId();


  /**
   * <p>
   * Returns an indicator of where the error occurred within the task.
   * </p>
   * <p>
   * Getter method for the COM property "MoTaskParam"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(104) //= 0x68. The runtime will prefer the VTID if present
  @VTID(11)
  int moTaskParam();


  /**
   * <p>
   * Returns rlib ID as defined in rlcons.hc.
   * </p>
   * <p>
   * Getter method for the COM property "RlibId"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(105) //= 0x69. The runtime will prefer the VTID if present
  @VTID(12)
  int rlibId();


  /**
   * <p>
   * Returns an indicator of where the error occurred within the module.
   * </p>
   * <p>
   * Getter method for the COM property "RlibParam"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(106) //= 0x6a. The runtime will prefer the VTID if present
  @VTID(13)
  int rlibParam();


  /**
   * <p>
   * Returns an object from which you can access the robot error information.
   * </p>
   * <p>
   * Getter method for the COM property "RobotErrorInfo"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IRobotErrorInfo
   */

  @DISPID(107) //= 0x6b. The runtime will prefer the VTID if present
  @VTID(14)
  com.github.wshackle.fanuc.robotserver.IRobotErrorInfo robotErrorInfo();


  /**
   * <p>
   * Returns a bit mask from which you can determine which joint(s) exceed the upper limit.
   * </p>
   * <p>
   * Getter method for the COM property "UpperLimMask"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FREJointBitMaskConstants
   */

  @DISPID(108) //= 0x6c. The runtime will prefer the VTID if present
  @VTID(15)
  com.github.wshackle.fanuc.robotserver.FREJointBitMaskConstants upperLimMask();


  // Properties:
}
