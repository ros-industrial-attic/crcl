package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{44E3D090-7178-11D1-B762-00C04FBBE42A}")
public abstract class IKARELProgramEvents {
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
   * Occurs after a KAREL program is reloaded.
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
   * Occurs after a KAREL program’s subtype is changed.
   * </p>
   */

  @DISPID(5)
  public void subTypeChange() {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
