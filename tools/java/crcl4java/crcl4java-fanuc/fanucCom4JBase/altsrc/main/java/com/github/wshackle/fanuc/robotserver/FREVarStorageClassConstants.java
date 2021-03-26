package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants identifying the storage class of variables.
 * </p>
 */
public enum FREVarStorageClassConstants implements ComEnum {
  /**
   * <p>
   * Designates DRAM storage for this variable.
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frVarDRAM(0),
  /**
   * <p>
   * Designates CMOS storage for this variable.
   * </p>
   * <p>
   * The value of this constant is 253
   * </p>
   */
  frVarCMOS(253),
  /**
   * <p>
   * Designates shared CMOS storage for this variable.
   * </p>
   * <p>
   * The value of this constant is 252
   * </p>
   */
  frVarFastCMOS(252),
  ;

  private final int value;
  FREVarStorageClassConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
