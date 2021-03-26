package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * Enumerated constants for the task ignore attribute
 * </p>
 */
public enum FRETaskIgnoreConstants implements ComEnum {
  /**
   * <p>
   * Ignore abort/pause req by error
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frIgnoreError(1),
  /**
   * <p>
   * Ignore abort/pause req by command.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frIgnoreCommand(2),
  /**
   * <p>
   * Ignore pause request by TP
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frIgnoreTP(4),
  /**
   * <p>
   * When pause, do abort
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frIgnorePauseAbort(8),
  /**
   * <p>
   * When pause, do abort with ERROR
   * </p>
   * <p>
   * The value of this constant is 16
   * </p>
   */
  frIgnoreErrorAbort(16),
  ;

  private final int value;
  FRETaskIgnoreConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
