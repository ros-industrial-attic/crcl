package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{53D6E5D3-F5E2-11D3-9F35-00500409FF06}")
public abstract class IRobManProxyEvents {
  // Methods:
  /**
   * @param answer Mandatory Holder<Integer> parameter.
   */

  @DISPID(4)
  public void terminateRobot(
    Holder<Integer> answer) {
        throw new UnsupportedOperationException();
  }


  /**
   */

  @DISPID(1)
  public void connect() {
        throw new UnsupportedOperationException();
  }


  /**
   */

  @DISPID(2)
  public void disconnect() {
        throw new UnsupportedOperationException();
  }


  /**
   * @param error Mandatory com.github.wshackle.fanuc.robotserver.IRobotErrorInfo parameter.
   */

  @DISPID(3)
  public void error(
    com.github.wshackle.fanuc.robotserver.IRobotErrorInfo error) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
