package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{21CB68D1-DCDE-11D0-A083-00A02436CF7E}")
public abstract class ITPPositionEvents {
  // Methods:
  /**
   * <p>
   * Occurs after a TP position is changed.
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
   * Occurs before a TP position is deleted.
   * </p>
   */

  @DISPID(2)
  public void delete() {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after a TP position is renumbered.
   * </p>
   */

  @DISPID(3)
  public void renumber() {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
