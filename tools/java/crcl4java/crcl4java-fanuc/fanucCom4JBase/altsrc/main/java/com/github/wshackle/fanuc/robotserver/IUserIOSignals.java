package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

@IID("{59DC16F9-AF91-11D0-A072-00A02436CF7E}")
public interface IUserIOSignals extends com.github.wshackle.fanuc.robotserver.IRobotObject,Iterable<Com4jObject> {
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
   * Getter method for the COM property "Item"
   * </p>
   * @param logicalType Optional parameter. Default value is com4j.Variant.getMissing()
   * @param logicalNum Optional parameter. Default value is com4j.Variant.getMissing()
   * @param index Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IUserIOSignal
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  com.github.wshackle.fanuc.robotserver.IUserIOSignal item(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object logicalType,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object logicalNum,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
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
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IUserIOType
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(11)
  com.github.wshackle.fanuc.robotserver.IUserIOType parent();


  /**
   * <p>
   * Getter method for the COM property "IsMonitoring"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(12)
  boolean isMonitoring();


  /**
   * @param logicalType Mandatory short parameter.
   * @param logicalNum Mandatory int parameter.
   * @param index Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IUserIOSignal
   */

  @DISPID(151) //= 0x97. The runtime will prefer the VTID if present
  @VTID(13)
  com.github.wshackle.fanuc.robotserver.IUserIOSignal add(
    short logicalType,
    int logicalNum,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   * @param logicalType Optional parameter. Default value is com4j.Variant.getMissing()
   * @param logicalNum Optional parameter. Default value is com4j.Variant.getMissing()
   * @param index Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(152) //= 0x98. The runtime will prefer the VTID if present
  @VTID(14)
  void remove(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object logicalType,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object logicalNum,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   * <p>
   * Starts the monitoring of the I/O signal for changes.
   * </p>
   * @param latency Mandatory int parameter.
   */

  @DISPID(153) //= 0x99. The runtime will prefer the VTID if present
  @VTID(15)
  void startMonitor(
    int latency);


  /**
   * <p>
   * Stops the monitoring of the I/O signal for changes.
   * </p>
   */

  @DISPID(154) //= 0x9a. The runtime will prefer the VTID if present
  @VTID(16)
  void stopMonitor();


  // Properties:
}
