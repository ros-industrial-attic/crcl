package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{0872A5E4-398C-46A3-989D-1E9B1BCA72DC}")
public abstract class IIndPositionEvents {
  // Methods:
  /**
   * <p>
   * Occurs after the position is changed.
   * </p>
   * @param groupNum Mandatory short parameter.
   */

  @DISPID(1)
  public void change(
    short groupNum) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
