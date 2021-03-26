package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Provides access to data blocks from a pipe as a set of named fields.
 */
@IID("{B475BC96-3AF1-11D4-9F66-00105AE428C3}")
public interface IPipeFields extends com.github.wshackle.fanuc.robotserver.IRobotObject,Iterable<Com4jObject> {
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
   * Returns a field object from the collection.
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param name Optional parameter. Default value is com4j.Variant.getMissing()
   * @param index Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject item(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object name,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   * <p>
   * Returns the number of fields within this collection object.
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
   * Returns the name of this particular field object.
   * </p>
   * <p>
   * Getter method for the COM property "FieldName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(11)
  java.lang.String fieldName();


  /**
   * <p>
   * Returns the full name of the field object.
   * </p>
   * <p>
   * Getter method for the COM property "FullName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(12)
  java.lang.String fullName();


  /**
   * <p>
   * Returns the parent object.
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(13)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject parent();


  /**
   * <p>
   * Returns the owning pipe object.
   * </p>
   * <p>
   * Getter method for the COM property "Pipe"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IPipe
   */

  @DISPID(205) //= 0xcd. The runtime will prefer the VTID if present
  @VTID(14)
  com.github.wshackle.fanuc.robotserver.IPipe pipe();


  // Properties:
}
