package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{021408E1-F879-11D0-A093-00A02436CF7E}")
public abstract class IIOConfigsEvents {
  // Methods:
  /**
   * <p>
   * Occurs after a configuration is created.
   * </p>
   * @param ioConfig Mandatory com.github.wshackle.fanuc.robotserver.IIOConfig parameter.
   */

  @DISPID(1)
  public void create(
    com.github.wshackle.fanuc.robotserver.IIOConfig ioConfig) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after a configuration is deleted.
   * </p>
   * @param ioConfig Mandatory com.github.wshackle.fanuc.robotserver.IIOConfig parameter.
   */

  @DISPID(2)
  public void delete(
    com.github.wshackle.fanuc.robotserver.IIOConfig ioConfig) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
