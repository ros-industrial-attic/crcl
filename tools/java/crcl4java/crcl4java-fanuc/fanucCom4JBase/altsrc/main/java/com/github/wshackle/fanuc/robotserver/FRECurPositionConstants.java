package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants used to identify the different types of current position display.
 * </p>
 */
public enum FRECurPositionConstants {
  /**
   * <p>
   * Used to return the current position as Joint display type.
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frJointDisplayType, // 0
  /**
   * <p>
   * Used to return the current position as User display type. 
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frUserDisplayType, // 1
  /**
   * <p>
   * Used to return the current position as World display type.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frWorldDisplayType, // 2
}
