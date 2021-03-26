package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{F96C81C1-DCD9-11D0-A083-00A02436CF7E}")
public abstract class ITPProgramEvents {
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


  /**
   * <p>
   * Occurs after a program is renamed.
   * </p>
   */

  @DISPID(5)
  public void rename() {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after a TP program’s subtype is changed.
   * </p>
   */

  @DISPID(6)
  public void subTypeChange() {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
