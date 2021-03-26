package com.github.wshackle.fanuc.robotneighborhood.events;

import com4j.*;

/**
 * _IRNRDMResponsesEvents Interface
 */
@IID("{A62511CC-B61C-4F50-BC6D-996622C48078}")
public abstract class _IRNRDMResponsesEvents {
  // Methods:
  /**
   * <p>
   * Occurs when the content of the most recent response from a robot is different than the last.
   * </p>
   * @param rdmResponse Mandatory com.github.wshackle.fanuc.robotneighborhood.IRNRDMResponse parameter.
   */

  @DISPID(1)
  public void onChange(
    com.github.wshackle.fanuc.robotneighborhood.IRNRDMResponse rdmResponse) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs when a robot that had previously responded, fails to respond now.
   * </p>
   * @param rdmResponse Mandatory com.github.wshackle.fanuc.robotneighborhood.IRNRDMResponse parameter.
   */

  @DISPID(2)
  public void onExpire(
    com.github.wshackle.fanuc.robotneighborhood.IRNRDMResponse rdmResponse) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs when a robot not currently in the responses collection responds to an RDM scan.
   * </p>
   * @param rdmResponse Mandatory com.github.wshackle.fanuc.robotneighborhood.IRNRDMResponse parameter.
   */

  @DISPID(3)
  public void onNew(
    com.github.wshackle.fanuc.robotneighborhood.IRNRDMResponse rdmResponse) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
