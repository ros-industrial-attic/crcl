package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{F22A6948-A310-11D1-B77A-00C04FBBE42A}")
public abstract class ITaskEvents {
  // Methods:
  /**
   * <p>
   * This event occurs if the StartMonitor method has been called and a task object changes.
   * </p>
   * @param changeType Mandatory com.github.wshackle.fanuc.robotserver.FREStatusTypeConstants parameter.
   */

  @DISPID(2)
  public void change(
    com.github.wshackle.fanuc.robotserver.FREStatusTypeConstants changeType) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
