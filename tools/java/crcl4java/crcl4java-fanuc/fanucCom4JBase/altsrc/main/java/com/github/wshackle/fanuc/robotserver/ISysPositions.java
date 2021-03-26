package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Provides access to a collection of controller system positions.
 */
@IID("{6055D69A-FFAE-11D0-B6F4-00C04FB9C401}")
public interface ISysPositions extends com.github.wshackle.fanuc.robotserver.IRobotObject,Iterable<Com4jObject> {
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
   * Returns a position from the collection. 
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param id Optional parameter. Default value is com4j.Variant.getMissing()
   * @param index Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ISysPosition
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  com.github.wshackle.fanuc.robotserver.ISysPosition item(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object id,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   * <p>
   * Returns the number of positions contained in the collection.
   * </p>
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(10)
  int count();


  /**
   * <p>
   * Returns/sets the selected “active” frame.
   * </p>
   * <p>
   * Getter method for the COM property "Selected"
   * </p>
   * @param groupNum Mandatory short parameter.
   * @return  Returns a value of type int
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(11)
  int selected(
    short groupNum);


  /**
   * <p>
   * Returns/sets the selected “active” frame.
   * </p>
   * <p>
   * Setter method for the COM property "Selected"
   * </p>
   * @param groupNum Mandatory short parameter.
   * @param selected Mandatory int parameter.
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(12)
  void selected(
    short groupNum,
    int selected);


  /**
   * <p>
   * Reads fresh values for all position registers, tool frames, user frames, or jog frames from the robot.
   * </p>
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(13)
  void refresh();


  /**
   * <p>
   * Sends the local copy of the position registers, tool frames, user frames, or jog frames to the robot.
   * </p>
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(14)
  void update();


  // Properties:
}
