package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{48F075F8-4626-11D1-B745-00C04FBBE42A}")
public abstract class ITasksEvents {
  // Methods:
  /**
   * <p>
   * This event occurs if the StartMonitor method has been called and any task object in the collection changes.
   * </p>
   * @param changeType Mandatory com.github.wshackle.fanuc.robotserver.FREStatusTypeConstants parameter.
   * @param task Mandatory com.github.wshackle.fanuc.robotserver.ITask parameter.
   */

  @DISPID(1)
  public void change(
    com.github.wshackle.fanuc.robotserver.FREStatusTypeConstants changeType,
    com.github.wshackle.fanuc.robotserver.ITask task) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
