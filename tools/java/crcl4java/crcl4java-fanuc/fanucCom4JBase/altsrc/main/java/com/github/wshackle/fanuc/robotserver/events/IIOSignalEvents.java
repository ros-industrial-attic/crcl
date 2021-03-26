package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{0DBE3EF1-F870-11D0-A093-00A02436CF7E}")
public abstract class IIOSignalEvents {
  // Methods:
  /**
   * <p>
   * Occurs before the I/O signal is deleted.
   * </p>
   */

  @DISPID(1)
  public void delete() {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after the I/O signal has changed.
   * </p>
   */

  @DISPID(2)
  public void change() {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after the I/O signal is simulated.
   * </p>
   */

  @DISPID(3)
  public void simulate() {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after the I/O signal is unsimulated.
   * </p>
   */

  @DISPID(4)
  public void unsimulate() {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
