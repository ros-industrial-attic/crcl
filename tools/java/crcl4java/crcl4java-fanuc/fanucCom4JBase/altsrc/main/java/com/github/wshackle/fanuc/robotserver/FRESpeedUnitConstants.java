package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants used to specify the units for motion speed.
 * </p>
 */
public enum FRESpeedUnitConstants {
  /**
   * <p>
   * Indicates that the unit of robot speed is percent of maximum joint speed.
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frPercentSpeedUnit, // 0
  /**
   * <p>
   * Indicates that the unit of robot speed is millimeters per second.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frMillimetersPerSecondSpeedUnit, // 1
  /**
   * <p>
   * Indicates that the unit of robot speed is centimeters per minute.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frCentimetersPerMinuteSpeedUnit, // 2
  /**
   * <p>
   * Indicates that the unit of robot speed is inches per minute.
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frInchesPerMinuteSpeedUnit, // 3
  /**
   * <p>
   * Indicates that the unit of robot speed is degrees per second.
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frDegreesPerSecondSpeedUnit, // 4
  /**
   * <p>
   * Indicates that robot speed is defined by seconds per move.
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  frSecondsSpeedUnit, // 5
  /**
   * <p>
   * Indicates that robot speed is defined by milliseconds per move.
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  frMillisecondsSpeedUnit, // 6
}
