package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * This object is used to access both I/O signal and I/O configuration collections.
 */
@IID("{59DC16F3-AF91-11D0-A072-00A02436CF7E}")
public interface IConfigurableIOType extends com.github.wshackle.fanuc.robotserver.ISystemIOType {
  // Methods:
  /**
   * <p>
   * Returns the collection of I/O configurations.
   * </p>
   * <p>
   * Getter method for the COM property "Configs"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IIOConfigs
   */

  @DISPID(301) //= 0x12d. The runtime will prefer the VTID if present
  @VTID(18)
  com.github.wshackle.fanuc.robotserver.IIOConfigs configs();


  // Properties:
}
