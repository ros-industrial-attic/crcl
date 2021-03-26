package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants for setting and testing joint masks.
 * </p>
 */
public enum FREJointBitMaskConstants implements ComEnum {
  /**
   * <p>
   * The bit mask that represents joint n.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frJoint1BitMask(1),
  /**
   * <p>
   * The bit mask that represents joint n.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frJoint2BitMask(2),
  /**
   * <p>
   * The bit mask that represents joint n.
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frJoint3BitMask(4),
  /**
   * <p>
   * The bit mask that represents joint n.
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frJoint4BitMask(8),
  /**
   * <p>
   * The bit mask that represents joint n.
   * </p>
   * <p>
   * The value of this constant is 16
   * </p>
   */
  frJoint5BitMask(16),
  /**
   * <p>
   * The bit mask that represents joint n.
   * </p>
   * <p>
   * The value of this constant is 32
   * </p>
   */
  frJoint6BitMask(32),
  /**
   * <p>
   * The bit mask that represents joint n.
   * </p>
   * <p>
   * The value of this constant is 64
   * </p>
   */
  frJoint7BitMask(64),
  /**
   * <p>
   * The bit mask that represents joint n.
   * </p>
   * <p>
   * The value of this constant is 128
   * </p>
   */
  frJoint8BitMask(128),
  /**
   * <p>
   * The bit mask that represents joint n.
   * </p>
   * <p>
   * The value of this constant is 256
   * </p>
   */
  frJoint9BitMask(256),
  ;

  private final int value;
  FREJointBitMaskConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
