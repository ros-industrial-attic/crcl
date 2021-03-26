package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * The FRCAlarms object is a collection of FRCAlarm objects.  This collection contains all the alarms that have occurred since the last time that the log was cleared, up to a predefined number.
 */
@IID("{7C37F232-A494-11D0-A37F-0020AF39BE5A}")
public interface IAlarms extends com.github.wshackle.fanuc.robotserver.IRobotObject,Iterable<Com4jObject> {
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
   * Returns a particular FRCAlarm object from the alarm log. 
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param index Mandatory int parameter.
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IAlarm
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  com.github.wshackle.fanuc.robotserver.IAlarm item(
    int index);


  /**
   * <p>
   * Returns the total number of alarm objects in the collection.  
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
   * Returns/sets the maximum number of alarms the log will hold. 
   * </p>
   * <p>
   * Getter method for the COM property "Max"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(11)
  int max();


  /**
   * <p>
   * Returns/sets the maximum number of alarms the log will hold. 
   * </p>
   * <p>
   * Setter method for the COM property "Max"
   * </p>
   * @param number Mandatory int parameter.
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(12)
  void max(
    int number);


  /**
   * <p>
   * Returns TRUE if there are any active alarms in the log.  
   * </p>
   * <p>
   * Getter method for the COM property "ActiveAlarms"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(13)
  boolean activeAlarms();


  /**
   * <p>
   * Issues a RESET command to the controller.
   * </p>
   */

  @DISPID(205) //= 0xcd. The runtime will prefer the VTID if present
  @VTID(14)
  void reset();


  // Properties:
}
