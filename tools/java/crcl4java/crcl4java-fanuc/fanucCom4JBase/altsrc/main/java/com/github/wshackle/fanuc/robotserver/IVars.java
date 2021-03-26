package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Provides access to robot controller variables, including system and program variables.
 */
@IID("{88B57BCA-D0CA-11CF-959F-00A024329125}")
public interface IVars extends com.github.wshackle.fanuc.robotserver.IVarObject,Iterable<Com4jObject> {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "_NewEnum"
   * </p>
   */

  @DISPID(-4) //= 0xfffffffc. The runtime will prefer the VTID if present
  @VTID(17)
  java.util.Iterator<Com4jObject> iterator();

  /**
   * <p>
   * Returns a variable object from the collection. 
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param name Optional parameter. Default value is com4j.Variant.getMissing()
   * @param index Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(18)
  @DefaultMethod
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject item(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object name,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   * <p>
   * Returns the number of items within this object.
   * </p>
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(301) //= 0x12d. The runtime will prefer the VTID if present
  @VTID(19)
  short count();


  /**
   * <p>
   * Returns the parent object.
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(302) //= 0x12e. The runtime will prefer the VTID if present
  @VTID(20)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject parent();


  /**
   * <p>
   * Returns/sets the level of controller restriction override. Use FREVarOverrideConstants to set this.
   * </p>
   * <p>
   * Getter method for the COM property "Override"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(400) //= 0x190. The runtime will prefer the VTID if present
  @VTID(21)
  int override();


  /**
   * <p>
   * Returns/sets the level of controller restriction override. Use FREVarOverrideConstants to set this.
   * </p>
   * <p>
   * Setter method for the COM property "Override"
   * </p>
   * @param override Mandatory int parameter.
   */

  @DISPID(400) //= 0x190. The runtime will prefer the VTID if present
  @VTID(22)
  void override(
    int override);


  /**
   * <p>
   * Controls if the data is immediately sent to the robot or not when an assignment is made to the Value property of any FRCVar object under this FRCVars object.
   * </p>
   * <p>
   * Getter method for the COM property "NoUpdate"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(401) //= 0x191. The runtime will prefer the VTID if present
  @VTID(23)
  boolean noUpdate();


  /**
   * <p>
   * Controls if the data is immediately sent to the robot or not when an assignment is made to the Value property of any FRCVar object under this FRCVars object.
   * </p>
   * <p>
   * Setter method for the COM property "NoUpdate"
   * </p>
   * @param noUpdate Mandatory boolean parameter.
   */

  @DISPID(401) //= 0x191. The runtime will prefer the VTID if present
  @VTID(24)
  void noUpdate(
    boolean noUpdate);


  /**
   * <p>
   * Sends the local copy of the values for this whole structure to the robot.
   * </p>
   */

  @DISPID(402) //= 0x192. The runtime will prefer the VTID if present
  @VTID(25)
  void update();


  /**
   * <p>
   * Copies the data from the supplied FRVars object into this one.
   * </p>
   * @param sourceVars Mandatory com.github.wshackle.fanuc.robotserver.IVars parameter.
   */

  @DISPID(403) //= 0x193. The runtime will prefer the VTID if present
  @VTID(26)
  void copy(
    com.github.wshackle.fanuc.robotserver.IVars sourceVars);


  /**
   * <p>
   * Returns the size of this variable in terms of bytes
   * </p>
   * <p>
   * Getter method for the COM property "Size"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(404) //= 0x194. The runtime will prefer the VTID if present
  @VTID(27)
  int size();


    // Properties:
  }
