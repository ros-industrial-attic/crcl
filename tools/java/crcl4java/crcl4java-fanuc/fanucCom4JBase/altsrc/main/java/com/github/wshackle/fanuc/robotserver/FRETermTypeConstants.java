package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants used to specify the motion termination type.
 * </p>
 */
public enum FRETermTypeConstants implements ComEnum {
  /**
   * <p>
   * Fine tolerance
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frFineTermType(1),
  /**
   * <p>
   * Coarse tolerance
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frCoarseTermType(2),
  /**
   * <p>
   * No settling time
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frNoSettleTermType(3),
  /**
   * <p>
   * No deceleration time
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frNoDecelTermType(4),
  /**
   * <p>
   * Variable deceleration
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  frVarDecelTermType(5),
  ;

  private final int value;
  FRETermTypeConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
