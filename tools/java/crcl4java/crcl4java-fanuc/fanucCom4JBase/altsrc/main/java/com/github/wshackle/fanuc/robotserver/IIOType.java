package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * This object is used to access both I/O signal and I/O configuration collections.
 */
@IID("{59DC16EF-AF91-11D0-A072-00A02436CF7E}")
public interface IIOType extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * <p>
   * Returns the type constant of the I/O type.
   * </p>
   * <p>
   * Getter method for the COM property "Type"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(8)
  short type();


  /**
   * <p>
   * Returns the owning I/O Types collection.
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IIOTypes
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(9)
  com.github.wshackle.fanuc.robotserver.IIOTypes parent();


  // Properties:
}
