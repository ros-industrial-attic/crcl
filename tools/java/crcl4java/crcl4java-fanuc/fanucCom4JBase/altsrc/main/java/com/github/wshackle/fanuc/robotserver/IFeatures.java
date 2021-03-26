package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * The features collection is a representation of all currently loaded controller features.  This collection will contain one object for each feature.  
 */
@IID("{2AF44183-9273-11D1-B6F9-00C04FA3BD85}")
public interface IFeatures extends com.github.wshackle.fanuc.robotserver.IRobotObject,Iterable<Com4jObject> {
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
   *  Item returns a feature object, specified by collection index or by order number, from the collection to the caller. 
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param orderNumber Optional parameter. Default value is com4j.Variant.getMissing()
   * @param index Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IFeature
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  com.github.wshackle.fanuc.robotserver.IFeature item(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object orderNumber,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   * <p>
   * Count returns the number of valid features loaded on the controller.
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
   * IsValid returns TRUE if a feature is loaded, and FALSE if it is not loaded. 
   * </p>
   * <p>
   * Getter method for the COM property "IsValid"
   * </p>
   * @param orderNumber Mandatory java.lang.String parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(11)
  boolean isValid(
    java.lang.String orderNumber);


  /**
   * <p>
   * Refresh rebuilds the features collection from the $FEATURE system variables.  
   * </p>
   */

  @DISPID(150) //= 0x96. The runtime will prefer the VTID if present
  @VTID(12)
  void refresh();


  // Properties:
}
