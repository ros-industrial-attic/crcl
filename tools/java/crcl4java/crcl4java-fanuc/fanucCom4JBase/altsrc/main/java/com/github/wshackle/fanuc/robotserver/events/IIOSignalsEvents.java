package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{39A374D1-F86E-11D0-A093-00A02436CF7E}")
public abstract class IIOSignalsEvents {
  // Methods:
  /**
   * <p>
   * Occurs after an I/O signal is created.
   * </p>
   * @param ioSignal Mandatory com.github.wshackle.fanuc.robotserver.IIOSignal2 parameter.
   */

  @DISPID(1)
  public void create(
    com.github.wshackle.fanuc.robotserver.IIOSignal2 ioSignal) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after an I/O signal is deleted.
   * </p>
   * @param ioSignal Mandatory com.github.wshackle.fanuc.robotserver.IIOSignal2 parameter.
   */

  @DISPID(2)
  public void delete(
    com.github.wshackle.fanuc.robotserver.IIOSignal2 ioSignal) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after one or more I/O signals are changed.
   * </p>
   * @param ioSignal Mandatory java.lang.Object parameter.
   */

  @DISPID(3)
  public void change(
    java.lang.Object ioSignal) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after one or more I/O signals are simulated.
   * </p>
   * @param ioSignal Mandatory java.lang.Object parameter.
   */

  @DISPID(4)
  public void simulate(
    java.lang.Object ioSignal) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after one or more I/O signals are unsimulated.
   * </p>
   * @param ioSignal Mandatory java.lang.Object parameter.
   */

  @DISPID(5)
  public void unsimulate(
    java.lang.Object ioSignal) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
