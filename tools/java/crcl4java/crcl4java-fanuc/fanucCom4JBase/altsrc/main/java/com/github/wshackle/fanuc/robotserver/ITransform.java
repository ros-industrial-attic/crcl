package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Represents the position of a single group of axes as a transformation matrix consisting of normal, orient, approach, and location vectors and a component specifying a configuration.
 */
@IID("{A47A5882-056D-11D0-8901-0020AF68F0A3}")
public interface ITransform extends com.github.wshackle.fanuc.robotserver.ICartesianFormat {
  // Methods:
  /**
   * <p>
   * Returns a reference to the normal vector of the position.
   * </p>
   * <p>
   * Getter method for the COM property "Normal"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IVector
   */

  @DISPID(301) //= 0x12d. The runtime will prefer the VTID if present
  @VTID(12)
  com.github.wshackle.fanuc.robotserver.IVector normal();


  /**
   * <p>
   * Returns a reference to the orientation vector of the position.
   * </p>
   * <p>
   * Getter method for the COM property "Orient"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IVector
   */

  @DISPID(302) //= 0x12e. The runtime will prefer the VTID if present
  @VTID(13)
  com.github.wshackle.fanuc.robotserver.IVector orient();


  /**
   * <p>
   * Returns a reference to the approach vector of the position.
   * </p>
   * <p>
   * Getter method for the COM property "Approach"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IVector
   */

  @DISPID(303) //= 0x12f. The runtime will prefer the VTID if present
  @VTID(14)
  com.github.wshackle.fanuc.robotserver.IVector approach();


  /**
   * <p>
   * Returns a reference to the location vector of the position.
   * </p>
   * <p>
   * Getter method for the COM property "Location"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IVector
   */

  @DISPID(304) //= 0x130. The runtime will prefer the VTID if present
  @VTID(15)
  com.github.wshackle.fanuc.robotserver.IVector location();


  // Properties:
}
