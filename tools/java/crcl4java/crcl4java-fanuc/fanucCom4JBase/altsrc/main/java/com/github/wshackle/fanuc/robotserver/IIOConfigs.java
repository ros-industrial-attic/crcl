package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * This object is used to access all I/O configurations for a particular I/O type.
 */
@IID("{59DC1700-AF91-11D0-A072-00A02436CF7E}")
public interface IIOConfigs extends com.github.wshackle.fanuc.robotserver.IRobotObject,Iterable<Com4jObject> {
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
   * Returns a configuration from the collection.  
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param firstLogicalNum Optional parameter. Default value is com4j.Variant.getMissing()
   * @param index Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IIOConfig
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  com.github.wshackle.fanuc.robotserver.IIOConfig item(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object firstLogicalNum,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   * <p>
   * Returns the number of configurations contained in the collection.
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
   * Returns the owning I/O type
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
   * Adds a new configuration to the collection.  
   * </p>
   * @param firstLogicalNum Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IIOConfig
   */

  @DISPID(151) //= 0x97. The runtime will prefer the VTID if present
  @VTID(12)
  com.github.wshackle.fanuc.robotserver.IIOConfig add(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object firstLogicalNum);


  /**
   * <p>
   * Removes a configuration from the collection.
   * </p>
   * @param firstLogicalNum Optional parameter. Default value is com4j.Variant.getMissing()
   * @param index Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(152) //= 0x98. The runtime will prefer the VTID if present
  @VTID(13)
  void remove(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object firstLogicalNum,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  // Properties:
}
