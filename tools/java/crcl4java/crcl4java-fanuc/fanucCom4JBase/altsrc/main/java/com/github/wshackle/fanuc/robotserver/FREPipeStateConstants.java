package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants that identify states of a pipe.
 * </p>
 */
public enum FREPipeStateConstants implements ComEnum {
  /**
   * <p>
   * Indicates that the pipe has overflowed.
   * </p>
   * <p>
   * The value of this constant is 32
   * </p>
   */
  frPipeOverflowedState(32),
  /**
   * <p>
   * Indicates that the pipe is flushed.
   * </p>
   * <p>
   * The value of this constant is 64
   * </p>
   */
  frPipeFlushedState(64),
  /**
   * <p>
   * Indicates that the pipe is currently active.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frPipeActiveState(2),
  /**
   * <p>
   * Indicates that the pipe overflowed temporarily, then continued.
   * </p>
   * <p>
   * The value of this constant is 256
   * </p>
   */
  frPipeTempOverflowedState(256),
  ;

  private final int value;
  FREPipeStateConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
