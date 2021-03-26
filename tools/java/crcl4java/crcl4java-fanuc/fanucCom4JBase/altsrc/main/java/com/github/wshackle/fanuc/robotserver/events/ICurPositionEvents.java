package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{EF22631E-87A8-11D1-B765-00C04FBBE42A}")
public abstract class ICurPositionEvents {
  // Methods:
  /**
   * <p>
   * Occurs when the robot position changes.  Returns an array of which motion group positions have changed. 
   * </p>
   * @param groupList Mandatory java.lang.Object parameter.
   */

  @DISPID(1)
  public void change(
    java.lang.Object groupList) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
