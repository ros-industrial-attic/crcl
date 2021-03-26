package com.github.wshackle.fanuc.robotneighborhood.events;

import com4j.*;

/**
 * _IRNRobotEvents Interface
 */
@IID("{10F4DC48-EEA4-48D4-8E2B-A71124817484}")
public abstract class _IRNRobotEvents {
  // Methods:
  /**
   * <p>
   * Occurs when a change in a connections status is detected.
   * </p>
   * @param newStatus Mandatory com.github.wshackle.fanuc.robotneighborhood.FRERNConnectionStatusConstants parameter.
   */

  @DISPID(1)
  public void onConnectionStatusChange(
    com.github.wshackle.fanuc.robotneighborhood.FRERNConnectionStatusConstants newStatus) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
