package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Provides access to the positional data.
 */
@IID("{D42AB5DA-8FFB-11D0-94CC-0020AF68F0A3}")
public interface IPosition extends com.github.wshackle.fanuc.robotserver.IRobotObject {
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
   * Getter method for the COM property "AutomaticUpdate"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(9)
  boolean automaticUpdate();


  /**
   * <p>
   * Setter method for the COM property "AutomaticUpdate"
   * </p>
   * @param automaticUpdate Mandatory boolean parameter.
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(10)
  void automaticUpdate(
    boolean automaticUpdate);


  /**
   * <p>
   * Returns/sets the format the position is stored in.
   * </p>
   * <p>
   * Getter method for the COM property "Type"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FRETypeCodeConstants
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(11)
  com.github.wshackle.fanuc.robotserver.FRETypeCodeConstants type();


  /**
   * <p>
   * Returns/sets the format the position is stored in.
   * </p>
   * <p>
   * Setter method for the COM property "Type"
   * </p>
   * @param type Mandatory com.github.wshackle.fanuc.robotserver.FRETypeCodeConstants parameter.
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(12)
  void type(
    com.github.wshackle.fanuc.robotserver.FRETypeCodeConstants type);


  /**
   * <p>
   * Returns positional data in the specified format.
   * </p>
   * <p>
   * Getter method for the COM property "Formats"
   * </p>
   * @param type Mandatory com.github.wshackle.fanuc.robotserver.FRETypeCodeConstants parameter.
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(13)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject formats(
    com.github.wshackle.fanuc.robotserver.FRETypeCodeConstants type);


  /**
   * <p>
   * Returns the group number for the position.
   * </p>
   * <p>
   * Getter method for the COM property "GroupNum"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(14)
  short groupNum();


  /**
   * <p>
   * Returns the position identification number for the position.
   * </p>
   * <p>
   * Getter method for the COM property "Id"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(205) //= 0xcd. The runtime will prefer the VTID if present
  @VTID(15)
  int id();


  /**
   * <p>
   * Returns whether the robot is currently at the position.
   * </p>
   * <p>
   * Getter method for the COM property "IsAtCurPosition"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(206) //= 0xce. The runtime will prefer the VTID if present
  @VTID(16)
  boolean isAtCurPosition();


  /**
   * <p>
   * Returns whether the position is initialized.
   * </p>
   * <p>
   * Getter method for the COM property "IsInitialized"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(207) //= 0xcf. The runtime will prefer the VTID if present
  @VTID(17)
  boolean isInitialized();


  /**
   * <p>
   * Returns/sets the User Frame to which the position is referenced.
   * </p>
   * <p>
   * Getter method for the COM property "UserFrame"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(208) //= 0xd0. The runtime will prefer the VTID if present
  @VTID(18)
  int userFrame();


  /**
   * <p>
   * Returns/sets the User Frame to which the position is referenced.
   * </p>
   * <p>
   * Setter method for the COM property "UserFrame"
   * </p>
   * @param userFrame Mandatory int parameter.
   */

  @DISPID(208) //= 0xd0. The runtime will prefer the VTID if present
  @VTID(19)
  void userFrame(
    int userFrame);


  /**
   * <p>
   * Returns/sets the User Tool to which the position is referenced.
   * </p>
   * <p>
   * Getter method for the COM property "UserTool"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(209) //= 0xd1. The runtime will prefer the VTID if present
  @VTID(20)
  int userTool();


  /**
   * <p>
   * Returns/sets the User Tool to which the position is referenced.
   * </p>
   * <p>
   * Setter method for the COM property "UserTool"
   * </p>
   * @param userTool Mandatory int parameter.
   */

  @DISPID(209) //= 0xd1. The runtime will prefer the VTID if present
  @VTID(21)
  void userTool(
    int userTool);


  /**
   * <p>
   * Returns whether the position is currently being monitored for changes.
   * </p>
   * <p>
   * Getter method for the COM property "IsMonitoring"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(210) //= 0xd2. The runtime will prefer the VTID if present
  @VTID(22)
  boolean isMonitoring();


  /**
   * <p>
   * Updates any changes to the position back to the controller.
   * </p>
   */

  @DISPID(251) //= 0xfb. The runtime will prefer the VTID if present
  @VTID(23)
  void update();


  /**
   * <p>
   * Clears all changes to the position since the last update of the position.
   * </p>
   */

  @DISPID(252) //= 0xfc. The runtime will prefer the VTID if present
  @VTID(24)
  void refresh();


  /**
   * <p>
   * Moves the robot to this position.
   * </p>
   */

  @DISPID(253) //= 0xfd. The runtime will prefer the VTID if present
  @VTID(25)
  void moveto();


  /**
   * <p>
   * Records the current position of the robot to this position.
   * </p>
   */

  @DISPID(254) //= 0xfe. The runtime will prefer the VTID if present
  @VTID(26)
  void record();


  /**
   * <p>
   * Enables the Change event, with specified latency.
   * </p>
   * @param latency Mandatory int parameter.
   */

  @DISPID(255) //= 0xff. The runtime will prefer the VTID if present
  @VTID(27)
  void startMonitor(
    int latency);


  /**
   * <p>
   * Stops the Change event from occurring.
   * </p>
   */

  @DISPID(256) //= 0x100. The runtime will prefer the VTID if present
  @VTID(28)
  void stopMonitor();


  /**
   * <p>
   * Uninitializes the position. 
   * </p>
   */

  @DISPID(257) //= 0x101. The runtime will prefer the VTID if present
  @VTID(29)
  void uninitialize();


  /**
   * <p>
   * Multiply two input positions’ transformation matrices and set the results to this position.
   * </p>
   * @param leftPos Mandatory com4j.Com4jObject parameter.
   * @param rightPos Mandatory com4j.Com4jObject parameter.
   */

  @DISPID(258) //= 0x102. The runtime will prefer the VTID if present
  @VTID(30)
  void matMul(
    @MarshalAs(NativeType.Dispatch) com4j.Com4jObject leftPos,
    @MarshalAs(NativeType.Dispatch) com4j.Com4jObject rightPos);


  /**
   * <p>
   * Invert the input position transformation matrix and set the results to this position.
   * </p>
   * @param inputPos Mandatory com4j.Com4jObject parameter.
   */

  @DISPID(259) //= 0x103. The runtime will prefer the VTID if present
  @VTID(31)
  void matInv(
    @MarshalAs(NativeType.Dispatch) com4j.Com4jObject inputPos);


  // Properties:
}
