package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants used to identify the different types of programs.
 * </p>
 */
public enum FREProgramTypeConstants {
  /**
   * <p>
   * Constant to identify Variable program type.
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frVRProgramType, // 0
  /**
   * <p>
   * Constant to identify the Teach Pendant Program type.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frTPProgramType, // 1
  /**
   * <p>
   * Constant to identify the KAREL program type.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frKarelProgramType, // 2
}
