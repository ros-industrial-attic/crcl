package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

@IID("{D42AB5D4-8FFB-11D0-94CC-0020AF68F0A3}")
public interface IRobotObject extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Returns the Robot object.  
   * </p>
   * <p>
   * Getter method for the COM property "Robot"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IRobot2
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  com.github.wshackle.fanuc.robotserver.IRobot2 robot();


  // Properties:
}
