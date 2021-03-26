package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * Enumerated constants used to specify the different modes of single stepping a program.
 * </p>
 */
public enum FREStepTypeConstants implements ComEnum {
  /**
   * <p>
   * No stepping (run).
   * </p>
   * <p>
   * The value of this constant is -1
   * </p>
   */
  frStepNone(-1),
  /**
   * <p>
   * Pause after statement.
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frStepStatement(0),
  /**
   * <p>
   * Pause after motion complete.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frStepMotion(1),
  /**
   * <p>
   * Pause after statements & routines.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frStepRoutine(2),
  /**
   * <p>
   * Pause after statements & KAREL motion.
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frStepTPMotion(3),
  ;

  private final int value;
  FREStepTypeConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
