package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Provides access to pipes defined on the controller.
 */
@IID("{B475BC90-3AF1-11D4-9F66-00105AE428C3}")
public interface IPipes extends com.github.wshackle.fanuc.robotserver.IRobotObject,Iterable<Com4jObject> {
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
   * Returns a pipe object from the collection.
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param id Optional parameter. Default value is com4j.Variant.getMissing()
   * @param index Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IPipe
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  com.github.wshackle.fanuc.robotserver.IPipe item(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object id,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   * <p>
   * Returns the number of items within this collection object.
   * </p>
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(10)
  int count();


  // Properties:
}
