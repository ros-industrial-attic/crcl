package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * The tasks collection represents all interpreter (PXNN) tasks that are currently active on the controller.  This collection will contain one object for each active task.
 */
@IID("{5F26BE72-4626-11D1-B745-00C04FBBE42A}")
public interface ITasks extends com.github.wshackle.fanuc.robotserver.IRobotObject,Iterable<Com4jObject> {
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
   * Item returns a task object, specified by collection index or by name, from the collection to the caller.
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param index Optional parameter. Default value is com4j.Variant.getMissing()
   * @param name Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ITask
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  com.github.wshackle.fanuc.robotserver.ITask item(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object name);


  /**
   * <p>
   * Count returns the maximum number of tasks available on the controller.   
   * </p>
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(10)
  short count();


  /**
   * <p>
   * IsMonitoring returns TRUE if task monitoring has been enabled via the StartMonitor method, and FALSE if monitoring is not enabled
   * </p>
   * <p>
   * Getter method for the COM property "IsMonitoring"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(11)
  boolean isMonitoring();


  /**
   * <p>
   * Aborts all active abortable interpreter tasks.
   * </p>
   * @param cancel Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(250) //= 0xfa. The runtime will prefer the VTID if present
  @VTID(12)
  void abortAll(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object cancel);


  /**
   * <p>
   * Pauses all active pauseable interpreter tasks..
   * </p>
   * @param cancel Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(251) //= 0xfb. The runtime will prefer the VTID if present
  @VTID(13)
  void pauseAll(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object cancel);


  /**
   * <p>
   * This method begins monitoring of all task objects in the collection for changes.
   * </p>
   * @param interval Mandatory int parameter.
   */

  @DISPID(252) //= 0xfc. The runtime will prefer the VTID if present
  @VTID(14)
  void startMonitor(
    int interval);


  /**
   * <p>
   * This method stops monitoring of all task objects in the collection for changes.
   * </p>
   */

  @DISPID(253) //= 0xfd. The runtime will prefer the VTID if present
  @VTID(15)
  void stopMonitor();


  /**
   * <p>
   * Tests if the specified task exists.  If it does, the FRCTask object to access it is returned.
   * </p>
   * @param name Mandatory java.lang.String parameter.
   * @param task Mandatory Holder<com.github.wshackle.fanuc.robotserver.ITask> parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(254) //= 0xfe. The runtime will prefer the VTID if present
  @VTID(16)
  boolean tryItem(
    java.lang.String name,
    Holder<com.github.wshackle.fanuc.robotserver.ITask> task);


  // Properties:
}
