package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{C19FE67C-A462-11D0-B304-00A02479C928}")
public abstract class IVarEvents {
  // Methods:
  /**
   * <p>
   * Occurs after a variable is deleted.
   * </p>
   */

  @DISPID(2)
  public void delete() {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after a variable is changed.
   * </p>
   */

  @DISPID(3)
  public void change() {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after a variable is renamed.
   * </p>
   */

  @DISPID(4)
  public void rename() {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after a numeric register comment is changed.
   * </p>
   */

  @DISPID(6)
  public void commentChange() {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
