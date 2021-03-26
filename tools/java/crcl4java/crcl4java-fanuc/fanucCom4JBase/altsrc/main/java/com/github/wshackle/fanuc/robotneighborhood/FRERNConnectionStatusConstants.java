package com.github.wshackle.fanuc.robotneighborhood  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants that represent the different connection status values.
 * </p>
 */
public enum FRERNConnectionStatusConstants implements ComEnum {
  /**
   * <p>
   * Indicates that the robot responds to pings over the network.
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frRNAvailable(0),
  /**
   * <p>
   * Indicates that the robot does not respond to pings over the network.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frRNUnavailable(1),
  /**
   * <p>
   * Indicates that the Robot Neighborhood is in the process of connecting to the robot controller.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frRNConnecting(2),
  /**
   * <p>
   * Indicates that the robot has at least a single connection.
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frRNConnected(3),
  /**
   * <p>
   * Indicates that the robot controller stopped responding to the HeartBeat signal.
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frRNHeartbeatLost(4),
  /**
   * <p>
   * Indicates that the robot was connected, but has been disconnected.
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  frRNDisconnecting(5),
  /**
   * <p>
   * The robot status has not been determined yet.
   * </p>
   * <p>
   * The value of this constant is 255
   * </p>
   */
  frRNUnknown(255),
  ;

  private final int value;
  FRERNConnectionStatusConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
