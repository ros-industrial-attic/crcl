package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants used to specify the motion type.
 * </p>
 */
public enum FREMotionTypeConstants implements ComEnum {
  /**
   * <p>
   * Joint motion
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  frJointMotionType(6),
  /**
   * <p>
   * Linear motion
   * </p>
   * <p>
   * The value of this constant is 7
   * </p>
   */
  frLinearMotionType(7),
  /**
   * <p>
   * Circular motion
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frCircularMotionType(8),
  /**
   * <p>
   * Indicates that the robot's current position be set to this position.
   * </p>
   * <p>
   * The value of this constant is -1
   * </p>
   */
  frSnapToMotionType(-1),
  ;

  private final int value;
  FREMotionTypeConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
