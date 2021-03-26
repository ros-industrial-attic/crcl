package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{3AC7EA79-381C-11D1-B6FE-00C04FB9E76B}")
public abstract class ISysPositionEvents {
  // Methods:
  /**
   * <p>
   * Occurs when the position is changed, GroupNum specifies which group was changed.
   * </p>
   * @param groupNum Mandatory short parameter.
   */

  @DISPID(1)
  public void change(
    short groupNum) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs when a position’s comment is changed, GroupNum specifies which group was changed.
   * </p>
   * @param groupNum Mandatory short parameter.
   */

  @DISPID(2)
  public void commentChange(
    short groupNum) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
