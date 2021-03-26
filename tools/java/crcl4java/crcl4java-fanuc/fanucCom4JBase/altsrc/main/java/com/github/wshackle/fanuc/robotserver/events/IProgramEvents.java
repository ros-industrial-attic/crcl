package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{18254511-DC4C-11D0-A083-00A02436CF7E}")
public abstract class IProgramEvents {
  // Methods:
  /**
   * <p>
   * Occurs after a program is deleted.
   * </p>
   */

  @DISPID(1)
  public void delete() {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after an attribute is changed.
   * </p>
   * @param attr Mandatory com.github.wshackle.fanuc.robotserver.FREAttributeConstants parameter.
   */

  @DISPID(2)
  public void attrChange(
    com.github.wshackle.fanuc.robotserver.FREAttributeConstants attr) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after a TP program is reloaded.
   * </p>
   */

  @DISPID(3)
  public void refresh() {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after a program’s variables are reloaded.
   * </p>
   */

  @DISPID(4)
  public void refreshVars() {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
