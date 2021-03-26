package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants identifying the access of variables.
 * </p>
 */
public enum FREVarAccessCodeConstants implements ComEnum {
  /**
   * <p>
   * No variable access.
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frVarNoAccess(0),
  /**
   * <p>
   * Read only variable access.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frVarReadAccess(1),
  /**
   * <p>
   * Read and Write variable access.
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frVarReadWriteAccess(3),
  /**
   * <p>
   * Use field protection variable access.
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  frVarFieldProtectionAccess(5),
  /**
   * <p>
   * Requires motion control for variable access.
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frVarMotionControlAccess(8),
  /**
   * <p>
   * Requires application control for variable access.
   * </p>
   * <p>
   * The value of this constant is 16
   * </p>
   */
  frVarApplicationControlAccess(16),
  ;

  private final int value;
  FREVarAccessCodeConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
