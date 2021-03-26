package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

@IID("{FC761640-4CEA-11D0-8901-0020AF68F0A3}")
public interface ITPLineHelper extends com.github.wshackle.fanuc.robotserver.IRobotObject {
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
   * Getter method for the COM property "Number"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(9)
  int number();


  /**
   */

  @DISPID(251) //= 0xfb. The runtime will prefer the VTID if present
  @VTID(10)
  void lineChanged();


  // Properties:
}
