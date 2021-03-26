package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Represents the position of a single group of axes as a transformation matrix consisting of normal, orient, approach, and location vectors and a component specifying a configuration.
 */
@IID("{D42AB5DC-8FFB-11D0-94CC-0020AF68F0A3}")
public interface ICartesianFormat extends com.github.wshackle.fanuc.robotserver.IRobotObject {
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
   * Returns a reference to the configuration object for the position.
   * </p>
   * <p>
   * Getter method for the COM property "Config"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IConfig
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(9)
  com.github.wshackle.fanuc.robotserver.IConfig config();


  /**
   * <p>
   * Returns a reference to the object representing the extended axes for the position.
   * </p>
   * <p>
   * Getter method for the COM property "Ext"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IAxesCollection
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(10)
  com.github.wshackle.fanuc.robotserver.IAxesCollection ext();


  /**
   * <p>
   * Returns the parent object.
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IPosition2
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(11)
  com.github.wshackle.fanuc.robotserver.IPosition2 parent();


  // Properties:
}
