package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{7DD25A00-AC49-11D0-8B7F-0020AF39BE5A}")
public abstract class IAlarmNotify {
  // Methods:
  /**
   * <p>
   * Occurs when an alarm is created by the controller, including RESET, CLEAR, and CLEAR ALL.
   * </p>
   * @param alarm Mandatory com4j.Com4jObject parameter.
   */

  @DISPID(1)
  public void alarmNotify(
    com4j.Com4jObject alarm) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
