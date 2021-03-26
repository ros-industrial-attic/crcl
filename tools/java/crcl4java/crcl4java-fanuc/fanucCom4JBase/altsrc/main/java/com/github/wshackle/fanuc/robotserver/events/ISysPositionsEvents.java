package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{94F57CE7-381B-11D1-B6FE-00C04FB9E76B}")
public abstract class ISysPositionsEvents {
  // Methods:
  /**
   * <p>
   * Occurs when a position is changed, Id and GroupNum specifies which position was changed.
   * </p>
   * @param id Mandatory int parameter.
   * @param groupNum Mandatory short parameter.
   */

  @DISPID(1)
  public void change(
    int id,
    short groupNum) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs when a position’s comment is changed, Id and GroupNum specifies which position was changed.
   * </p>
   * @param id Mandatory int parameter.
   * @param groupNum Mandatory short parameter.
   */

  @DISPID(2)
  public void commentChange(
    int id,
    short groupNum) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
