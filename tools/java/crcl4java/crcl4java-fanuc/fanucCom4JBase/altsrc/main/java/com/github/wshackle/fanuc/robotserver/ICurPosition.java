package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Provides access to the current position of the robot for all motion groups.
 */
@IID("{E2686FA8-1E42-11D1-B6FF-00C04FB9C401}")
public interface ICurPosition extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
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

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(8)
  boolean groupMask(
    short groupNum);


  /**
   * <p>
   * Returns FRCCurGroupPosition object representing the current position of the robot for the specified group and DisplayType.
   * </p>
   * <p>
   * Getter method for the COM property "Group"
   * </p>
   * @param groupNum Mandatory short parameter.
   * @param displayType Mandatory com.github.wshackle.fanuc.robotserver.FRECurPositionConstants parameter.
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ICurGroupPosition
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(9)
  com.github.wshackle.fanuc.robotserver.ICurGroupPosition group(
    short groupNum,
    com.github.wshackle.fanuc.robotserver.FRECurPositionConstants displayType);


  /**
   * <p>
   * Returns the number of groups defined on the controller.
   * </p>
   * <p>
   * Getter method for the COM property "NumGroups"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(10)
  short numGroups();


  /**
   * <p>
   * Returns if the position is currently being monitored for changes.
   * </p>
   * <p>
   * Getter method for the COM property "IsMonitoring"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(11)
  boolean isMonitoring();


  /**
   * <p>
   * Enables the Change event, with specified latency.
   * </p>
   * @param latency Mandatory int parameter.
   */

  @DISPID(250) //= 0xfa. The runtime will prefer the VTID if present
  @VTID(12)
  void startMonitor(
    int latency);


  /**
   * <p>
   * Stops the Change event from occurring.
   * </p>
   */

  @DISPID(251) //= 0xfb. The runtime will prefer the VTID if present
  @VTID(13)
  void stopMonitor();


  /**
   * <p>
   * Returns the bit mask representation of the groups supported by this position.
   * </p>
   * <p>
   * Getter method for the COM property "GroupBitMask"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FREGroupBitMaskConstants
   */

  @DISPID(252) //= 0xfc. The runtime will prefer the VTID if present
  @VTID(14)
  com.github.wshackle.fanuc.robotserver.FREGroupBitMaskConstants groupBitMask();


  // Properties:
}
