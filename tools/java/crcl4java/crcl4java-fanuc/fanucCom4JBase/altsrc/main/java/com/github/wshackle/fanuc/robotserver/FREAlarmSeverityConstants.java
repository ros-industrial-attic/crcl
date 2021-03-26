package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants used for Alarms.
 * </p>
 */
public enum FREAlarmSeverityConstants implements ComEnum {
  /**
   * <p>
   * Execution mask	
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frSevExMask(3),
  /**
   * <p>
   * Execution: no effect	
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frSevExNone(0),
  /**
   * <p>
   * Execution: debug
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frSevExDebug(1),
  /**
   * <p>
   * Execution: pause
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frSevExPause(2),
  /**
   * <p>
   * Execution: abort
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frSevExAbort(3),
  /**
   * <p>
   * Motion mask
   * </p>
   * <p>
   * The value of this constant is 12
   * </p>
   */
  frSevMoMask(12),
  /**
   * <p>
   * Motion: no effect
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frSevMoNone(0),
  /**
   * <p>
   * Motion: stop
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frSevMoStop(4),
  /**
   * <p>
   * Motion: cancel
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frSevMoCancel(8),
  /**
   * <p>
   * Severity: none
   * </p>
   * <p>
   * The value of this constant is 128
   * </p>
   */
  frSevNone(128),
  /**
   * <p>
   * Severity: warning
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frSevWarn(0),
  /**
   * <p>
   * Severity: pause
   * </p>
   * <p>
   * The value of this constant is 34
   * </p>
   */
  frSevPause(34),
  /**
   * <p>
   * Severity: local pause
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frSevPauseL(2),
  /**
   * <p>
   * Severity: stop
   * </p>
   * <p>
   * The value of this constant is 38
   * </p>
   */
  frSevStop(38),
  /**
   * <p>
   * Severity: local stop
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  frSevStopL(6),
  /**
   * <p>
   * Severity: servo
   * </p>
   * <p>
   * The value of this constant is 54
   * </p>
   */
  frSevServo(54),
  /**
   * <p>
   * Severity: servo 2
   * </p>
   * <p>
   * The value of this constant is 59
   * </p>
   */
  frSevServo2(59),
  /**
   * <p>
   * Severity: abort
   * </p>
   * <p>
   * The value of this constant is 43
   * </p>
   */
  frSevAbort(43),
  /**
   * <p>
   * Severity: local abort
   * </p>
   * <p>
   * The value of this constant is 11
   * </p>
   */
  frSevAbortL(11),
  /**
   * <p>
   * Severity: system
   * </p>
   * <p>
   * The value of this constant is 123
   * </p>
   */
  frSevSystem(123),
  /**
   * <p>
   * Error class: normal
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frErrorNormal(0),
  /**
   * <p>
   * Error class: reset
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frErrorReset(1),
  /**
   * <p>
   * Error class: clear
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frErrorClear(2),
  /**
   * <p>
   * Error class: clear all
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frErrorClearAll(3),
  ;

  private final int value;
  FREAlarmSeverityConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
