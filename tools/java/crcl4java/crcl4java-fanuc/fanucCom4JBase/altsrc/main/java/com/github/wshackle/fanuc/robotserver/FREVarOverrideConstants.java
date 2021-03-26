package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * Enumerated constants used for overriding controller restrictions on variables.
 * </p>
 */
public enum FREVarOverrideConstants implements ComEnum {
  /**
   * <p>
   * Don't override controller restrictions.
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frVarNoOverride(0),
  /**
   * <p>
   * Override all access checking.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frVarAccessOverride(1),
  /**
   * <p>
   * Don't do any limit checking.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frVarLimitOverride(2),
  /**
   * <p>
   * Allow Refresh on the System Variables TopVar.
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frRefreshSysvarsOverride(4),
  /**
   * <p>
   * Override every variable restriction possible.
   * </p>
   * <p>
   * The value of this constant is -1
   * </p>
   */
  frVarAllOverride(-1),
  ;

  private final int value;
  FREVarOverrideConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
