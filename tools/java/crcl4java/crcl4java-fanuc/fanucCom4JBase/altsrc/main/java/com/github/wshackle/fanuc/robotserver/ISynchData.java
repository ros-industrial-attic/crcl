package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 *  This object is used to access all other controller feature/option synchronization objects.
 */
@IID("{3C3988CD-9275-11D1-B6F9-00C04FA3BD85}")
public interface ISynchData extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * <p>
   *  This property returns a collection of objects representing all features that are loaded on the currently connected controller.  
   * </p>
   * <p>
   * Getter method for the COM property "Features"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IFeatures
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(8)
  com.github.wshackle.fanuc.robotserver.IFeatures features();


  /**
   * <p>
   * This property returns a collection of objects representing all TPP instructions that can be used on this controller.  
   * </p>
   * <p>
   * Getter method for the COM property "TPInstructions"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ITPInstructions
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(9)
  com.github.wshackle.fanuc.robotserver.ITPInstructions tpInstructions();


  /**
   * <p>
   * This property returns a collection of objects representing all TPP application data that can be used on this controller.  
   * </p>
   * <p>
   * Getter method for the COM property "TPApplData"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.ISynchApplData
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(10)
  com.github.wshackle.fanuc.robotserver.ISynchApplData tpApplData();


  // Properties:
}
