package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

@IID("{8FAFC8E7-B2B8-11D1-B705-00C04FA3BD85}")
public interface ISynchApplData extends com.github.wshackle.fanuc.robotserver.IRobotObject,Iterable<Com4jObject> {
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
   * Item returns an application data item object from the collection to the caller.  
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param name Optional parameter. Default value is com4j.Variant.getMissing()
   * @param collectionIndex Optional parameter. Default value is com4j.Variant.getMissing()
   * @param applicationID Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ISynchApplDataItem
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  com.github.wshackle.fanuc.robotserver.ISynchApplDataItem item(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object name,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object collectionIndex,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object applicationID);


  /**
   * <p>
   * Count returns the number of application data items that are valid for the controller.
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
   * Add places a new TPP application data object in the TPP application data collection.
   * </p>
   * @param name Mandatory java.lang.String parameter.
   * @param applicationID Optional parameter. Default value is com4j.Variant.getMissing()
   * @param applProgID Mandatory java.lang.String parameter.
   * @param editProgID Mandatory java.lang.String parameter.
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ISynchApplDataItem
   */

  @DISPID(150) //= 0x96. The runtime will prefer the VTID if present
  @VTID(11)
  com.github.wshackle.fanuc.robotserver.ISynchApplDataItem add(
    java.lang.String name,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object applicationID,
    java.lang.String applProgID,
    java.lang.String editProgID);


  /**
   * <p>
   *  Remove deletes a TPP application data object from the application data collection.  
   * </p>
   * @param name Mandatory java.lang.String parameter.
   * @param applicationID Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(151) //= 0x97. The runtime will prefer the VTID if present
  @VTID(12)
  void remove(
    java.lang.String name,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object applicationID);


  // Properties:
}
