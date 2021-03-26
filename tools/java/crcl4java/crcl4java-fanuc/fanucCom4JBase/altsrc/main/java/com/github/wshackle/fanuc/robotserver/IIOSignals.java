package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * This object is used to access all I/O signals for a particular I/O type.
 */
@IID("{59DC16F7-AF91-11D0-A072-00A02436CF7E}")
public interface IIOSignals extends com.github.wshackle.fanuc.robotserver.IRobotObject,Iterable<Com4jObject> {
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
   * Returns a specific signal from the collection. 
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param logicalNum Optional parameter. Default value is com4j.Variant.getMissing()
   * @param index Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IIOSignal2
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  com.github.wshackle.fanuc.robotserver.IIOSignal2 item(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object logicalNum,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   * <p>
   * Returns the number of signals contained in the collection.
   * </p>
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(10)
  int count();


  /**
   * <p>
   * Returns the parent object.  
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IIOType
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(11)
  com.github.wshackle.fanuc.robotserver.IIOType parent();


  /**
   * <p>
   * Returns True if monitoring is enabled, False if not.
   * </p>
   * <p>
   * Getter method for the COM property "IsMonitoring"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(12)
  boolean isMonitoring();


  /**
   * <p>
   * Returns/sets the “block-read” status for the collection.
   * </p>
   * <p>
   * Getter method for the COM property "NoRefresh"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(104) //= 0x68. The runtime will prefer the VTID if present
  @VTID(13)
  boolean noRefresh();


  /**
   * <p>
   * Returns/sets the “block-read” status for the collection.
   * </p>
   * <p>
   * Setter method for the COM property "NoRefresh"
   * </p>
   * @param noRefresh Mandatory boolean parameter.
   */

  @DISPID(104) //= 0x68. The runtime will prefer the VTID if present
  @VTID(14)
  void noRefresh(
    boolean noRefresh);


  /**
   * <p>
   * Starts the monitoring of the I/O signals collection for changes.
   * </p>
   * @param latency Mandatory int parameter.
   */

  @DISPID(150) //= 0x96. The runtime will prefer the VTID if present
  @VTID(15)
  void startMonitor(
    int latency);


  /**
   * <p>
   * Stops the monitoring of the I/O signals collection for changes.
   * </p>
   */

  @DISPID(151) //= 0x97. The runtime will prefer the VTID if present
  @VTID(16)
  void stopMonitor();


  /**
   * <p>
   * Refreshes the I/O Signal data when the NoRefresh property is true.
   * </p>
   */

  @DISPID(152) //= 0x98. The runtime will prefer the VTID if present
  @VTID(17)
  void refresh();


  // Properties:
}
