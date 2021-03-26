package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{B475BC92-3AF1-11D4-9F66-00105AE428C3}")
public abstract class IPipesEvents {
  // Methods:
  /**
   * <p>
   * Occurs when a new pipe is registered on the controller.
   * </p>
   * @param pipe Mandatory com.github.wshackle.fanuc.robotserver.IPipe parameter.
   */

  @DISPID(1)
  public void register(
    com.github.wshackle.fanuc.robotserver.IPipe pipe) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs when a  pipe is unregistered on the controller.
   * </p>
   * @param pipe Mandatory com.github.wshackle.fanuc.robotserver.IPipe parameter.
   */

  @DISPID(2)
  public void unregister(
    com.github.wshackle.fanuc.robotserver.IPipe pipe) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
