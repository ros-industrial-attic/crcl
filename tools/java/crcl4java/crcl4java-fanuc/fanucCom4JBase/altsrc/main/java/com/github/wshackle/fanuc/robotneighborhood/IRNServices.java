package com.github.wshackle.fanuc.robotneighborhood  ;

import com4j.*;

/**
 * A collection of FRCRNService objects.
 */
@IID("{6B117BF8-3A13-4FAF-BFC1-40CC2716B92E}")
public interface IRNServices extends Com4jObject,Iterable<Com4jObject> {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "_NewEnum"
   * </p>
   */

  @DISPID(-4) //= 0xfffffffc. The runtime will prefer the VTID if present
  @VTID(7)
  java.util.Iterator<Com4jObject> iterator();

  /**
   * <p>
   * Returns the number of services that are available on this robot.
   * </p>
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(8)
  int count();


  /**
   * <p>
   * Returns an FRCRNService object as specified.
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param id Optional parameter. Default value is 255
   * @param name Optional parameter. Default value is ""
   * @param index Optional parameter. Default value is -1
   * @return  Returns a value of type com.github.wshackle.fanuc.robotneighborhood.IRNService
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(9)
  com.github.wshackle.fanuc.robotneighborhood.IRNService item(
    @Optional @DefaultValue("255") com.github.wshackle.fanuc.robotneighborhood.FRERNServiceIDConstants id,
    @Optional @DefaultValue("") java.lang.String name,
    @Optional @DefaultValue("-1") int index);


  /**
   * <p>
   * Returns the parent of this object.
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotneighborhood.IRNRobot
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(10)
  com.github.wshackle.fanuc.robotneighborhood.IRNRobot parent();


  // Properties:
}
