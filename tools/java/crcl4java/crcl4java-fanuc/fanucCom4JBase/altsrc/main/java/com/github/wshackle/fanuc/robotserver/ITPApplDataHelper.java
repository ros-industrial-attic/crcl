package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

@IID("{343D8EB1-DCE1-11D0-A083-00A02436CF7E}")
public interface ITPApplDataHelper extends com.github.wshackle.fanuc.robotserver.IRobotObject {
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
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(9)
  java.lang.String name();


  /**
   * <p>
   * Getter method for the COM property "Index1"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(10)
  int index1();


  /**
   * <p>
   * Getter method for the COM property "Index2"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(11)
  int index2();


  /**
   */

  @DISPID(251) //= 0xfb. The runtime will prefer the VTID if present
  @VTID(12)
  void applDataChanged();


  // Properties:
}
