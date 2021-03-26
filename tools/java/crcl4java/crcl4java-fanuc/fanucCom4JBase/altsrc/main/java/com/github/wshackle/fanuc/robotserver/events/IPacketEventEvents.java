package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{298DEBD5-9976-11D1-B753-00C04FB99C75}")
public abstract class IPacketEventEvents {
  // Methods:
  /**
   * <p>
   * Occurs when a ROS is received that matches the trigger criteria specified by the RequestCode and SybSystemCode properties..
   * </p>
   * @param packet Mandatory Holder<com.github.wshackle.fanuc.robotserver.IPacket> parameter.
   */

  @DISPID(1)
  public void receive(
    Holder<com.github.wshackle.fanuc.robotserver.IPacket> packet) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
