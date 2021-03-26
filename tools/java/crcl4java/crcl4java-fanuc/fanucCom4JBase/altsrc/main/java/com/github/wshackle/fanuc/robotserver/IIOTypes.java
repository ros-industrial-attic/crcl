package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Represents the collection of all the I/O types currently set up on the controller.
 */
@IID("{59DC16EB-AF91-11D0-A072-00A02436CF7E}")
public interface IIOTypes extends com.github.wshackle.fanuc.robotserver.IRobotObject,Iterable<Com4jObject> {
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
   * Returns a specific I/O type object from the collection.  
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param type Optional parameter. Default value is com4j.Variant.getMissing()
   * @param index Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IIOType
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  com.github.wshackle.fanuc.robotserver.IIOType item(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object type,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   * <p>
   * Returns the number of I/O types set up on the controller.  
   * </p>
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(10)
  short count();


  /**
   * <p>
   * Returns the parent object.  
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IRobot2
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(11)
  com.github.wshackle.fanuc.robotserver.IRobot2 parent();


  /**
   * <p>
   * Adds a new User I/O type object to the collection.  
   * </p>
   * @param type Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IUserIOType
   */

  @DISPID(151) //= 0x97. The runtime will prefer the VTID if present
  @VTID(12)
  com.github.wshackle.fanuc.robotserver.IUserIOType add(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object type);


  /**
   * <p>
   * Removes a specified User I/O type object from the collection.  
   * </p>
   * @param type Optional parameter. Default value is com4j.Variant.getMissing()
   * @param index Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(152) //= 0x98. The runtime will prefer the VTID if present
  @VTID(13)
  void remove(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object type,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   * <p>
   * Saves all configuration data from memory to the specified file device.
   * </p>
   * @param fileName Mandatory java.lang.String parameter.
   */

  @DISPID(153) //= 0x99. The runtime will prefer the VTID if present
  @VTID(14)
  void save(
    java.lang.String fileName);


  /**
   * <p>
   * Unsimulates all I/O.
   * </p>
   */

  @DISPID(154) //= 0x9a. The runtime will prefer the VTID if present
  @VTID(15)
  void unsimulate();


  /**
   * <p>
   * Returns/sets the dry run status.
   * </p>
   * <p>
   * Getter method for the COM property "DryRun"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(16)
  boolean dryRun();


  /**
   * <p>
   * Returns/sets the dry run status.
   * </p>
   * <p>
   * Setter method for the COM property "DryRun"
   * </p>
   * @param dryRun Mandatory boolean parameter.
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(17)
  void dryRun(
    boolean dryRun);


  // Properties:
}
