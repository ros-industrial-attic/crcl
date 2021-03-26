package com.github.wshackle.fanuc.robotneighborhood.events;

import com4j.*;

/**
 * _IRNServiceEvents Interface
 */
@IID("{1F9317DE-1A2F-4705-AEBD-7BAE4BCB2FD6}")
public abstract class _IRNServiceEvents {
  // Methods:
  /**
   * <p>
   * This event notifies the client when a service has changed state on the controller.
   * </p>
   * @param state Mandatory com.github.wshackle.fanuc.robotneighborhood.FRERNServiceStateConstants parameter.
   */

  @DISPID(1)
  public void onStateChange(
    com.github.wshackle.fanuc.robotneighborhood.FRERNServiceStateConstants state) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
