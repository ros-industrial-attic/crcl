package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * Enumerated constants used for the task status attribute.
 * </p>
 */
public enum FRETaskStatusConstants implements ComEnum {
  /**
   * <p>
   * Idle task not running, paused, or aborted.
   * </p>
   * <p>
   * The value of this constant is -32768
   * </p>
   */
  frStatusIdle(-32768),
  /**
   * <p>
   * RUN request accepted
   * </p>
   * <p>
   * The value of this constant is -2
   * </p>
   */
  frStatusRunAccept(-2),
  /**
   * <p>
   * Task abort request accepted, abort in progress.
   * </p>
   * <p>
   * The value of this constant is -1
   * </p>
   */
  frStatusAborting(-1),
  /**
   * <p>
   * Task is running
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frStatusRun(0),
  /**
   * <p>
   * Task is paused
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frStatusPaused(1),
  /**
   * <p>
   * Task is aborted.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frStatusAborted(2),
  ;

  private final int value;
  FRETaskStatusConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
