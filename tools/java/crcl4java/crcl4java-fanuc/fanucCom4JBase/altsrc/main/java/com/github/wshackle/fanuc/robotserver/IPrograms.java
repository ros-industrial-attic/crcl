package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Represents the collection of all programs currently loaded on the robot controller, i.e. Teach Pendant, KAREL, and VR programs.
 */
@IID("{18F67740-46A8-11D0-8901-0020AF68F0A3}")
public interface IPrograms extends com.github.wshackle.fanuc.robotserver.IRobotObject,Iterable<Com4jObject> {
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
   * Returns the specified program from the programs collection.
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param name Mandatory java.lang.Object parameter.
   * @param newReference Optional parameter. Default value is com4j.Variant.getMissing()
   * @param accessMode Optional parameter. Default value is com4j.Variant.getMissing()
   * @param rejectMode Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IProgram
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  com.github.wshackle.fanuc.robotserver.IProgram item(
    @MarshalAs(NativeType.VARIANT) java.lang.Object name,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object newReference,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object accessMode,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object rejectMode);


  /**
   * <p>
   * Returns the number of programs currently in the collecti/on that are loaded and visible on the controller.
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
   * Returns/sets the currently selected program.
   * </p>
   * <p>
   * Getter method for the COM property "Selected"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(11)
  java.lang.String selected();


  /**
   * <p>
   * Returns/sets the currently selected program.
   * </p>
   * <p>
   * Setter method for the COM property "Selected"
   * </p>
   * @param selected Mandatory java.lang.String parameter.
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(12)
  void selected(
    java.lang.String selected);


  /**
   * <p>
   * Returns the parent Robot object of the Programs Collection.
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IRobot2
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(13)
  com.github.wshackle.fanuc.robotserver.IRobot2 parent();


  /**
   * <p>
   * Adds a new program to the collection.
   * </p>
   * @param name Mandatory java.lang.String parameter.
   * @param type Mandatory com.github.wshackle.fanuc.robotserver.FREProgramTypeConstants parameter.
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IProgram
   */

  @DISPID(150) //= 0x96. The runtime will prefer the VTID if present
  @VTID(14)
  com.github.wshackle.fanuc.robotserver.IProgram add(
    java.lang.String name,
    com.github.wshackle.fanuc.robotserver.FREProgramTypeConstants type);


  /**
   * <p>
   * Removes a program from the programs collection and the memory of the controller.
   * </p>
   * @param name Mandatory java.lang.String parameter.
   */

  @DISPID(151) //= 0x97. The runtime will prefer the VTID if present
  @VTID(15)
  void remove(
    java.lang.String name);


  /**
   * <p>
   * Tests if the specified program exists and if it does, the FRCProgam object to access it is returned.
   * </p>
   * @param name Mandatory java.lang.String parameter.
   * @param newReference Optional parameter. Default value is com4j.Variant.getMissing()
   * @param accessMode Optional parameter. Default value is com4j.Variant.getMissing()
   * @param rejectMode Optional parameter. Default value is com4j.Variant.getMissing()
   * @param program Mandatory Holder<com.github.wshackle.fanuc.robotserver.IProgram> parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(152) //= 0x98. The runtime will prefer the VTID if present
  @VTID(16)
  boolean tryItem(
    java.lang.String name,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object newReference,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object accessMode,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object rejectMode,
    Holder<com.github.wshackle.fanuc.robotserver.IProgram> program);


  // Properties:
}
