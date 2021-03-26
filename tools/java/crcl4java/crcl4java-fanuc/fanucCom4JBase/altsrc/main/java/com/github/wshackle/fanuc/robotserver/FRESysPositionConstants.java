package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants used to specify the different system position types.
 * </p>
 */
public enum FRESysPositionConstants {
  /**
   * <p>
   * Position Register.
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frRegSysPosition, // 0
  /**
   * <p>
   * Tool Frame.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frToolFrameSysPosition, // 1
  /**
   * <p>
   * Jog Frame.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frJogFrameSysPosition, // 2
  /**
   * <p>
   * User Frame.
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frUserFrameSysPosition, // 3
  /**
   * <p>
   * Default Frame.
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frBaseFrameSysPosition, // 4
}
