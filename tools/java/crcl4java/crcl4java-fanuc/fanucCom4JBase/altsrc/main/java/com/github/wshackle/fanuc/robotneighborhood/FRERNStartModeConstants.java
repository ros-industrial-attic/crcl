package com.github.wshackle.fanuc.robotneighborhood  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants used to identify the start mode in which the virtual controller is running.
 * </p>
 */
public enum FRERNStartModeConstants implements ComEnum {
  /**
   * <p>
   * Indicates that the robot is running in the Init Start mode.
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frRNInitStartMode(0),
  /**
   * <p>
   * Indicates that the robot is running in the Controlled Start mode.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frRNControlledStartMode(1),
  /**
   * <p>
   * Indicates that the robot is running in the Cold Start mode.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frRNColdStartMode(2),
  /**
   * <p>
   * Indicates that the robot is running in the Hot Start mode.
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frRNHotStartMode(3),
  /**
   * <p>
   * Indicates that the robot is running in the Configuration Start mode.
   * </p>
   * <p>
   * The value of this constant is 16
   * </p>
   */
  frRNConfigStartMode(16),
  /**
   * <p>
   * Indicates that the virtual robot is being recovered from a backup.
   * </p>
   * <p>
   * The value of this constant is 252
   * </p>
   */
  frRNRecoveryInProgress(252),
  /**
   * <p>
   * Indicates that any start mode is O.K..
   * </p>
   * <p>
   * The value of this constant is 253
   * </p>
   */
  frRNDontCareStartMode(253),
  /**
   * <p>
   * Indicates that the virtual robot is currently starting.
   * </p>
   * <p>
   * The value of this constant is 254
   * </p>
   */
  frRNStartupInProgress(254),
  /**
   * <p>
   * Indicates that the virtual robot is not running.
   * </p>
   * <p>
   * The value of this constant is 255
   * </p>
   */
  frRNNotStarted(255),
  ;

  private final int value;
  FRERNStartModeConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
