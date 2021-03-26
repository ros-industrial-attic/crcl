package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Provides access to the collection of positions in a TP program. 
 */
@IID("{A16AD1C6-F45A-11CF-8901-0020AF68F0A3}")
public interface ITPPositions extends com.github.wshackle.fanuc.robotserver.IRobotObject,Iterable<Com4jObject> {
  // Methods:
  /**
   * <p>
   * Returns the owning program object.
   * </p>
   * <p>
   * Getter method for the COM property "Program"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IProgram
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(8)
  com.github.wshackle.fanuc.robotserver.IProgram program();


  /**
   * <p>
   * Getter method for the COM property "_NewEnum"
   * </p>
   */

  @DISPID(-4) //= 0xfffffffc. The runtime will prefer the VTID if present
  @VTID(9)
  java.util.Iterator<Com4jObject> iterator();

  /**
   * <p>
   * Returns a TP position from the collection. 
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param id Optional parameter. Default value is com4j.Variant.getMissing()
   * @param index Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ITPPosition
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(10)
  @DefaultMethod
  com.github.wshackle.fanuc.robotserver.ITPPosition item(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object id,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   * <p>
   * Returns the number of TP positions contained in the collection (and therefore, in the TP program).
   * </p>
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(11)
  int count();


  /**
   * <p>
   * Returns the first empty position ID from the TP position collection
   * </p>
   * <p>
   * Getter method for the COM property "NewPosID"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(12)
  int newPosID();


  /**
   */

  @DISPID(251) //= 0xfb. The runtime will prefer the VTID if present
  @VTID(13)
  void renumberAll();


  /**
   * <p>
   * Returns a TP position from the collection. 
   * </p>
   * @param groupNum Mandatory short parameter.
   * @param index Mandatory int parameter.
   * @param x Mandatory Holder<Double> parameter.
   * @param y Mandatory Holder<Double> parameter.
   * @param z Mandatory Holder<Double> parameter.
   * @param w Mandatory Holder<Double> parameter.
   * @param p Mandatory Holder<Double> parameter.
   * @param r Mandatory Holder<Double> parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(14)
  boolean getGroupXyzWprByIndex(
    short groupNum,
    int index,
    Holder<Double> x,
    Holder<Double> y,
    Holder<Double> z,
    Holder<Double> w,
    Holder<Double> p,
    Holder<Double> r);


  // Properties:
}
