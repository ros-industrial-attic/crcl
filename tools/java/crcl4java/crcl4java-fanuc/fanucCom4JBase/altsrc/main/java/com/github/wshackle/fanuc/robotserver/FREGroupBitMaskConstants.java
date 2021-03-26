package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants for setting group masks
 * </p>
 */
public enum FREGroupBitMaskConstants implements ComEnum {
  /**
   * <p>
   * The bit mask that represents motion group 1.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frGroup1BitMask(1),
  /**
   * <p>
   * The bit mask that represents motion group 2.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frGroup2BitMask(2),
  /**
   * <p>
   * The bit mask that represents motion group 3.
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frGroup3BitMask(4),
  /**
   * <p>
   * The bit mask that represents motion group 4.
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frGroup4BitMask(8),
  /**
   * <p>
   * The bit mask that represents motion group 5.
   * </p>
   * <p>
   * The value of this constant is 16
   * </p>
   */
  frGroup5BitMask(16),
  /**
   * <p>
   * The bit mask that represents motion group 6.
   * </p>
   * <p>
   * The value of this constant is 32
   * </p>
   */
  frGroup6BitMask(32),
  /**
   * <p>
   * The bit mask that represents motion group 7.
   * </p>
   * <p>
   * The value of this constant is 64
   * </p>
   */
  frGroup7BitMask(64),
  /**
   * <p>
   * The bit mask that represents motion group 8.
   * </p>
   * <p>
   * The value of this constant is 128
   * </p>
   */
  frGroup8BitMask(128),
  ;

  private final int value;
  FREGroupBitMaskConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
