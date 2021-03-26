package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants used to specify the orientation control of Linear motion.
 * </p>
 */
public enum FREOrientTypeConstants implements ComEnum {
  /**
   * <p>
   * Two-angle orientation interpolation
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frRSWorldOrientType(1),
  /**
   * <p>
   * Three-angle orientation interpolation
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frAESWorldOrientType(2),
  /**
   * <p>
   * Wrist-Joint orientation interpolation
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frWristJointOrientType(3),
  /**
   * <p>
   * Minimum joint rotation orientation interpolation
   * </p>
   * <p>
   * The value of this constant is 256
   * </p>
   */
  frMinRotationOrientType(256),
  ;

  private final int value;
  FREOrientTypeConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
