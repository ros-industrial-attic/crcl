package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

@IID("{53D6E5D1-F5E2-11D3-9F35-00500409FF06}")
public interface IRobManProxy extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "GetRobot"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IRobot
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(7)
  com.github.wshackle.fanuc.robotserver.IRobot getRobot();


  /**
   * @return  Returns a value of type int
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(8)
  int releaseRobot();


  // Properties:
}
