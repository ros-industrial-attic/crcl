package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{B475BC93-3AF1-11D4-9F66-00105AE428C3}")
public abstract class IPipeEvents {
  // Methods:
  /**
   * <p>
   * Occurs when new information is received from the pipe.
   * </p>
   * @param state Mandatory com.github.wshackle.fanuc.robotserver.FREPipeStateConstants parameter.
   * @param data Mandatory com4j.Com4jObject parameter.
   */

  @DISPID(1)
  public void receive(
    com.github.wshackle.fanuc.robotserver.FREPipeStateConstants state,
    com4j.Com4jObject data) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs when this pipe is unregistered on the controller.
   * </p>
   */

  @DISPID(2)
  public void unregister() {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
