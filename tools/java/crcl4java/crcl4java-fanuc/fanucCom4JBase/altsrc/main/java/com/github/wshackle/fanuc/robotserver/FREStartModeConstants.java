package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * Enumerated constants for checking which start mode the controller is in.
 * </p>
 */
public enum FREStartModeConstants implements ComEnum {
  /**
   * <p>
   * Indicates that the robot is running in the Init Start mode.
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frInitStart(0),
  /**
   * <p>
   * Indicates that the robot is running in the Controlled Start mode.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frControlStart(1),
  /**
   * <p>
   * Indicates that the robot is running in the Cold Start mode.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frColdStart(2),
  /**
   * <p>
   * Indicates that the robot is running in the Hot Start mode.
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frHotStart(3),
  /**
   * <p>
   * The value of this constant is 16
   * </p>
   */
  frConfigStart(16),
  /**
   * <p>
   * The value of this constant is 255
   * </p>
   */
  frNotStarted(255),
  /**
   * <p>
   * The value of this constant is 128
   * </p>
   */
  frClockStopped(128),
  ;

  private final int value;
  FREStartModeConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
