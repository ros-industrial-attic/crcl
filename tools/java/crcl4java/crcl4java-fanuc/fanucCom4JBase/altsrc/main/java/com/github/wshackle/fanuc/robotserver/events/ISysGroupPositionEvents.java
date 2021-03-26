package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{6EA7D4AD-381C-11D1-B6FE-00C04FB9E76B}")
public abstract class ISysGroupPositionEvents {
  // Methods:
  /**
   * <p>
   * Occurs after the position is changed.
   * </p>
   */

  @DISPID(1)
  public void change() {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs when the position’s comment is changed.
   * </p>
   */

  @DISPID(2)
  public void commentChange() {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
