package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants used when loading a file.
 * </p>
 */
public enum FRELoadOptionConstants implements ComEnum {
  /**
   * <p>
   * Normal file load
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frStandardLoad(0),
  /**
   * <p>
   * Overwrite TP program load
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frOverwriteTPLoad(1),
  /**
   * <p>
   * Temp pool PC program load
   * </p>
   * <p>
   * The value of this constant is 10
   * </p>
   */
  frTempPCLoad(10),
  /**
   * <p>
   * Normal variable file load
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frStandardVarLoad(0),
  /**
   * <p>
   * Convert variable file load
   * </p>
   * <p>
   * The value of this constant is 16
   * </p>
   */
  frConvertVarLoad(16),
  /**
   * <p>
   * Cold start sysvar file load
   * </p>
   * <p>
   * The value of this constant is 17
   * </p>
   */
  frColdSysLoad(17),
  /**
   * <p>
   * Convert at cold start sysvar file load
   * </p>
   * <p>
   * The value of this constant is 18
   * </p>
   */
  frColdConvertSysLoad(18),
  /**
   * <p>
   * DRAM only variable file load
   * </p>
   * <p>
   * The value of this constant is 19
   * </p>
   */
  frDramOnlyVarLoad(19),
  /**
   * <p>
   * Override mismatch errors variable file load
   * </p>
   * <p>
   * The value of this constant is 256
   * </p>
   */
  frOverrideVarLoad(256),
  ;

  private final int value;
  FRELoadOptionConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
