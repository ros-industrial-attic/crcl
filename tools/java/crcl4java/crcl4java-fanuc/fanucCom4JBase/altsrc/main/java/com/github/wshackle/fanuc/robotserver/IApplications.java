package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * This object is a general-purpose collection of application specific objects.
 */
@IID("{78063945-E50A-11D1-B778-00C04FB99C75}")
public interface IApplications extends com.github.wshackle.fanuc.robotserver.IRobotObject,Iterable<Com4jObject> {
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
   * Returns an object from the Applications collection object.
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param key Mandatory java.lang.String parameter.
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject item(
    java.lang.String key);


  /**
   * <p>
   * Returns the number of objects in the Applications collection.
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
   * Adds an application specific object to the Applications collection.  
   * </p>
   * @param appObject Mandatory com4j.Com4jObject parameter.
   * @param key Mandatory java.lang.String parameter.
   */

  @DISPID(151) //= 0x97. The runtime will prefer the VTID if present
  @VTID(11)
  void add(
    @MarshalAs(NativeType.Dispatch) com4j.Com4jObject appObject,
    java.lang.String key);


  /**
   * <p>
   * Removes an application specific object from the Applications collection.  
   * </p>
   * @param key Mandatory java.lang.String parameter.
   */

  @DISPID(152) //= 0x98. The runtime will prefer the VTID if present
  @VTID(12)
  void remove(
    java.lang.String key);


  // Properties:
}
