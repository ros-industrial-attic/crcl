package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Enables you to access a group of independent variables and I/O signals with minimum network overhead.
 */
@IID("{6F33A4D2-91F3-11D3-877C-00C04F81118D}")
public interface IScatteredAccess extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * <p>
   * Reads the values of all items controlled by this object from the robot into local Robot Server memory on the PC.
   * </p>
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(8)
  void refresh();


  /**
   * <p>
   * Writes the values of all items controlled by this object from local Robot Server memory on the PC to the robot.
   * </p>
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(9)
  void update();


  // Properties:
}
