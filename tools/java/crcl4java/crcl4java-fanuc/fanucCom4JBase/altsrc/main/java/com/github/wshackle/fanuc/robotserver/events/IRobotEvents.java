package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{52A6CF60-4732-11D2-8738-00C04F98D092}")
public abstract class IRobotEvents {
  // Methods:
  /**
   * <p>
   * This event is fired after the connection has been established to the controller.
   * </p>
   */

  @DISPID(1)
  public void connect() {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * This event is fired after a connection to a controller has been terminated abnormally.
   * </p>
   */

  @DISPID(2)
  public void disconnect() {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs when an error is detected that is not handled by normal excpetion handling.
   * </p>
   * @param error Mandatory com.github.wshackle.fanuc.robotserver.IRobotErrorInfo parameter.
   */

  @DISPID(3)
  public void error(
    com.github.wshackle.fanuc.robotserver.IRobotErrorInfo error) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
