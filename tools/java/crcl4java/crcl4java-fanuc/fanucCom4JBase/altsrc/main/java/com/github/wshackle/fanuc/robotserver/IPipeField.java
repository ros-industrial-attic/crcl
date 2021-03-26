package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Provides access to data values received from a pipe.
 */
@IID("{B475BC98-3AF1-11D4-9F66-00105AE428C3}")
public interface IPipeField extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * <p>
   * Returns the value of the field referred to by this object.
   * </p>
   * <p>
   * Getter method for the COM property "Value"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(8)
  @DefaultMethod
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object value();


  /**
   * <p>
   * Returns only the name of this particular field object.
   * </p>
   * <p>
   * Getter method for the COM property "FieldName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(9)
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

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(10)
  java.lang.String fullName();


  /**
   * <p>
   * Returns the initialized status of the data represented by this object.
   * </p>
   * <p>
   * Getter method for the COM property "IsInitialized"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(11)
  boolean isInitialized();


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
  @VTID(12)
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
  @VTID(13)
  com.github.wshackle.fanuc.robotserver.IPipe pipe();


  /**
   * <p>
   * Returns a code identifying the data type of the field.
   * </p>
   * <p>
   * Getter method for the COM property "TypeCode"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FRETypeCodeConstants
   */

  @DISPID(206) //= 0xce. The runtime will prefer the VTID if present
  @VTID(14)
  com.github.wshackle.fanuc.robotserver.FRETypeCodeConstants typeCode();


  /**
   * <p>
   * Returns a string type name for the field.
   * </p>
   * <p>
   * Getter method for the COM property "TypeName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(207) //= 0xcf. The runtime will prefer the VTID if present
  @VTID(15)
  java.lang.String typeName();


  // Properties:
}
