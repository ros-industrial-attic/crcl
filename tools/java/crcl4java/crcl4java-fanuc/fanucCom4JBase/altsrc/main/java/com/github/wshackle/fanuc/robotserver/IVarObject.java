package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

@IID("{A6F54250-DE6F-11D0-9F9F-00A024329125}")
public interface IVarObject extends com.github.wshackle.fanuc.robotserver.IRobotObject {
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
   * Returns only the name of this particular variable object.
   * </p>
   * <p>
   * Getter method for the COM property "FieldName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(9)
  java.lang.String fieldName();


  /**
   * <p>
   * Returns the full name of the variable object.  
   * </p>
   * <p>
   * Getter method for the COM property "VarName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(10)
  java.lang.String varName();


  /**
   * <p>
   * Returns the status of whether or not the controller is monitoring this variable.
   * </p>
   * <p>
   * Getter method for the COM property "IsMonitoring"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(11)
  boolean isMonitoring();


  /**
   * <p>
   * Returns/sets whether or not the automatic .Value refresh mechanism is disabled for this variable.
   * </p>
   * <p>
   * Getter method for the COM property "NoRefresh"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(12)
  boolean noRefresh();


  /**
   * <p>
   * Returns/sets whether or not the automatic .Value refresh mechanism is disabled for this variable.
   * </p>
   * <p>
   * Setter method for the COM property "NoRefresh"
   * </p>
   * @param noRefresh Mandatory boolean parameter.
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(13)
  void noRefresh(
    boolean noRefresh);


  /**
   * <p>
   * Tells the controller to monitor a variable and raise an event when its value changes.
   * </p>
   * @param latency Mandatory int parameter.
   */

  @DISPID(250) //= 0xfa. The runtime will prefer the VTID if present
  @VTID(14)
  void startMonitor(
    int latency);


  /**
   * <p>
   * Tells the controller to stop monitoring the variable for value changes.
   * </p>
   */

  @DISPID(251) //= 0xfb. The runtime will prefer the VTID if present
  @VTID(15)
  void stopMonitor();


  /**
   * <p>
   * Immediately updates the value cached in the Robot Server with a "fresh" one read from the robot controller.
   * </p>
   */

  @DISPID(252) //= 0xfc. The runtime will prefer the VTID if present
  @VTID(16)
  void refresh();


  // Properties:
}
