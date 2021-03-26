package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants used to identify and set the program protection.
 * </p>
 */
public enum FREProtectionConstants implements ComEnum {
  /**
   * <p>
   * Read/Write program protection.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frReadWriteProtection(1),
  /**
   * <p>
   * Read-only program protection.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frReadOnlyProtection(2),
  ;

  private final int value;
  FREProtectionConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
