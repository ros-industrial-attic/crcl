package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants used to identify the variable type.
 * </p>
 */
public enum FRETypeCodeConstants implements ComEnum {
  /**
   * <p>
   * Cartesian position represented as a transformation matrix.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frTransform(1),
  /**
   * <p>
   * Cartesian position with yaw, pitch, and roll orientation.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frXyzWpr(2),
  /**
   * <p>
   * Cartesian position with AES orientation.
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frXyzAes(3),
  /**
   * <p>
   * Cartesian XYZ position information with yaw, pitch, and roll orientation in joint angle (degrees) format.
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frXyz456(4),
  /**
   * <p>
   * Cartesian position represented as a transformation matrix with extended axes.
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  frExtTransform(5),
  /**
   * <p>
   * Cartesian position with yaw, pitch, and roll orientation and extended axes.
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  frExtXyzWpr(6),
  /**
   * <p>
   * Cartesian position with AES orientation and extended axes.
   * </p>
   * <p>
   * The value of this constant is 7
   * </p>
   */
  frExtXyzAes(7),
  /**
   * <p>
   * Cartesian XYZ position information with yaw, pitch, and roll orientation in joint (degrees) format. Extended axis information is included as well.
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frExtXyz456(8),
  /**
   * <p>
   * Joint position.
   * </p>
   * <p>
   * The value of this constant is 9
   * </p>
   */
  frJoint(9),
  /**
   * <p>
   * Simple integer variable.
   * </p>
   * <p>
   * The value of this constant is 16
   * </p>
   */
  frIntegerType(16),
  /**
   * <p>
   * Simple real variable.
   * </p>
   * <p>
   * The value of this constant is 17
   * </p>
   */
  frRealType(17),
  /**
   * <p>
   * Simple boolean variable.
   * </p>
   * <p>
   * The value of this constant is 18
   * </p>
   */
  frBooleanType(18),
  /**
   * <p>
   * Vector variable.
   * </p>
   * <p>
   * The value of this constant is 19
   * </p>
   */
  frVectorType(19),
  /**
   * <p>
   * Common associated data variable.
   * </p>
   * <p>
   * The value of this constant is 20
   * </p>
   */
  frCommonAssocType(20),
  /**
   * <p>
   * Simple short variable.
   * </p>
   * <p>
   * The value of this constant is 23
   * </p>
   */
  frShortType(23),
  /**
   * <p>
   * Simple byte variable.
   * </p>
   * <p>
   * The value of this constant is 24
   * </p>
   */
  frByteType(24),
  /**
   * <p>
   * Simple string variable.
   * </p>
   * <p>
   * The value of this constant is 209
   * </p>
   */
  frStringType(209),
  /**
   * <p>
   * Configuration variable.
   * </p>
   * <p>
   * The value of this constant is 28
   * </p>
   */
  frConfigType(28),
  /**
   * <p>
   * File variable.
   * </p>
   * <p>
   * The value of this constant is 29
   * </p>
   */
  frFileType(29),
  /**
   * <p>
   * Group associated data variable.
   * </p>
   * <p>
   * The value of this constant is 30
   * </p>
   */
  frGroupAssocType(30),
  /**
   * <p>
   * Path variable.
   * </p>
   * <p>
   * The value of this constant is 31
   * </p>
   */
  frPathType(31),
  /**
   * <p>
   * Array variable.
   * </p>
   * <p>
   * The value of this constant is 215
   * </p>
   */
  frArrayType(215),
  /**
   * <p>
   * Structure variable.
   * </p>
   * <p>
   * The value of this constant is 210
   * </p>
   */
  frStructureType(210),
  /**
   * <p>
   * Register position.
   * </p>
   * <p>
   * The value of this constant is 37
   * </p>
   */
  frRegPositionType(37),
  /**
   * <p>
   * Numeric register.
   * </p>
   * <p>
   * The value of this constant is 38
   * </p>
   */
  frRegNumericType(38),
  /**
   * <p>
   * Numeric register.
   * </p>
   * <p>
   * The value of this constant is 39
   * </p>
   */
  frRegStringType(39),
  ;

  private final int value;
  FRETypeCodeConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
