package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{C240AAA1-F86F-11D0-A093-00A02436CF7E}")
public abstract class IUserIOSignalsEvents {
  // Methods:
  /**
   * @param userIOSignal Mandatory com.github.wshackle.fanuc.robotserver.IUserIOSignal parameter.
   */

  @DISPID(1)
  public void create(
    com.github.wshackle.fanuc.robotserver.IUserIOSignal userIOSignal) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param userIOSignal Mandatory com.github.wshackle.fanuc.robotserver.IUserIOSignal parameter.
   */

  @DISPID(2)
  public void delete(
    com.github.wshackle.fanuc.robotserver.IUserIOSignal userIOSignal) {
        throw new UnsupportedOperationException();
  }


  /**
   */

  @DISPID(3)
  public void refresh() {
        throw new UnsupportedOperationException();
  }


  /**
   * @param userIOSignal Mandatory java.lang.Object parameter.
   */

  @DISPID(4)
  public void change(
    java.lang.Object userIOSignal) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param ioSignal Mandatory java.lang.Object parameter.
   */

  @DISPID(5)
  public void simulate(
    java.lang.Object ioSignal) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param ioSignal Mandatory java.lang.Object parameter.
   */

  @DISPID(6)
  public void unsimulate(
    java.lang.Object ioSignal) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
