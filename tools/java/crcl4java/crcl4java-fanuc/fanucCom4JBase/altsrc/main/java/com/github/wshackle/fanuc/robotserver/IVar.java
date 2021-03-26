package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Provides access to the attributes and value of program and system variables.
 */
@IID("{8C8ACC80-4F57-11D0-BC32-444553540000}")
public interface IVar extends com.github.wshackle.fanuc.robotserver.IVarObject {
  // Methods:
  /**
   * <p>
   * Returns/sets the value of the variable referred to by this object.  
   * </p>
   * <p>
   * Getter method for the COM property "Value"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(17)
  @DefaultMethod
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object value();


  /**
   * <p>
   * Returns/sets the value of the variable referred to by this object.  
   * </p>
   * <p>
   * Setter method for the COM property "Value"
   * </p>
   * @param value Mandatory java.lang.Object parameter.
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(18)
  @DefaultMethod
  void value(
    @MarshalAs(NativeType.VARIANT) java.lang.Object value);


  /**
   * <p>
   * Returns a code identifying the type (integer, real, register, etc.) of the variable.
   * </p>
   * <p>
   * Getter method for the COM property "TypeCode"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FRETypeCodeConstants
   */

  @DISPID(301) //= 0x12d. The runtime will prefer the VTID if present
  @VTID(19)
  com.github.wshackle.fanuc.robotserver.FRETypeCodeConstants typeCode();


  /**
   * <p>
   * Returns a string type name for the variable.
   * </p>
   * <p>
   * Getter method for the COM property "TypeName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(302) //= 0x12e. The runtime will prefer the VTID if present
  @VTID(20)
  java.lang.String typeName();


  /**
   * <p>
   * Returns the motion group number for this (position-type) variable object.
   * </p>
   * <p>
   * Getter method for the COM property "GroupNum"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(303) //= 0x12f. The runtime will prefer the VTID if present
  @VTID(21)
  short groupNum();


  /**
   * <p>
   * Returns the maximum string length for a string variable.
   * </p>
   * <p>
   * Getter method for the COM property "MaxStringLen"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(304) //= 0x130. The runtime will prefer the VTID if present
  @VTID(22)
  short maxStringLen();


  /**
   * <p>
   * Returns the maximum value of a numeric variable.  
   * </p>
   * <p>
   * Getter method for the COM property "MaxValue"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(305) //= 0x131. The runtime will prefer the VTID if present
  @VTID(23)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object maxValue();


  /**
   * <p>
   * Returns the minimum value of a numeric variable.   
   * </p>
   * <p>
   * Getter method for the COM property "MinValue"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(306) //= 0x132. The runtime will prefer the VTID if present
  @VTID(24)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object minValue();


  /**
   * <p>
   * Returns the available access to the variable (read-only, read-write, etc.).
   * </p>
   * <p>
   * Getter method for the COM property "AccessCode"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FREVarAccessCodeConstants
   */

  @DISPID(307) //= 0x133. The runtime will prefer the VTID if present
  @VTID(25)
  com.github.wshackle.fanuc.robotserver.FREVarAccessCodeConstants accessCode();


  /**
   * <p>
   * Returns the storage class of the variable.
   * </p>
   * <p>
   * Getter method for the COM property "StorageClass"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FREVarStorageClassConstants
   */

  @DISPID(308) //= 0x134. The runtime will prefer the VTID if present
  @VTID(26)
  com.github.wshackle.fanuc.robotserver.FREVarStorageClassConstants storageClass();


  /**
   * <p>
   *  Returns the parent object.
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IVars
   */

  @DISPID(309) //= 0x135. The runtime will prefer the VTID if present
  @VTID(27)
  com.github.wshackle.fanuc.robotserver.IVars parent();


  /**
   * <p>
   * Returns the initialized status for this variable object.
   * </p>
   * <p>
   * Getter method for the COM property "IsInitialized"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(310) //= 0x136. The runtime will prefer the VTID if present
  @VTID(28)
  boolean isInitialized();


  /**
   * <p>
   * Uninitializes a variable.
   * </p>
   */

  @DISPID(351) //= 0x15f. The runtime will prefer the VTID if present
  @VTID(29)
  void uninitialize();


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
  @VTID(30)
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
  @VTID(31)
  void override(
    int override);


  /**
   * <p>
   * Returns/sets whether or not the data is immediately sent to the robot when an assignment is made to the Value property. 
   * </p>
   * <p>
   * Getter method for the COM property "NoUpdate"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(401) //= 0x191. The runtime will prefer the VTID if present
  @VTID(32)
  boolean noUpdate();


  /**
   * <p>
   * Returns/sets whether or not the data is immediately sent to the robot when an assignment is made to the Value property. 
   * </p>
   * <p>
   * Setter method for the COM property "NoUpdate"
   * </p>
   * @param noUpdate Mandatory boolean parameter.
   */

  @DISPID(401) //= 0x191. The runtime will prefer the VTID if present
  @VTID(33)
  void noUpdate(
    boolean noUpdate);


  /**
   * <p>
   * Sends the local copy of this variable's value to the robot.
   * </p>
   */

  @DISPID(402) //= 0x192. The runtime will prefer the VTID if present
  @VTID(34)
  void update();


  /**
   * <p>
   * Copies the data from the supplied FRVar object into this one.
   * </p>
   * @param sourceVar Mandatory com.github.wshackle.fanuc.robotserver.IVar parameter.
   */

  @DISPID(403) //= 0x193. The runtime will prefer the VTID if present
  @VTID(35)
  void copy(
    com.github.wshackle.fanuc.robotserver.IVar sourceVar);


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
  @VTID(36)
  int size();


  // Properties:
}
