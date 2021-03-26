package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants used to monitor status indicated by the TP Out signals.
 * </p>
 */
public enum FRETPOutSignalConstants implements ComEnum {
  /**
   * <p>
   * Signal indicating system is in the fault state
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frTPOutFault(1),
  /**
   * <p>
   * Signal indicating motion has been held
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frTPOutHold(2),
  /**
   * <p>
   * Signal indicating single step is enabled
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frTPOutStep(3),
  /**
   * <p>
   * Signal indicating system is busy
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frTPOutBusy(4),
  /**
   * <p>
   * Signal indicating system is running
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  frTPOutRunning(5),
  /**
   * <p>
   * Application specific state signal
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  frTPOutApp1(6),
  /**
   * <p>
   * Application specific state signal
   * </p>
   * <p>
   * The value of this constant is 7
   * </p>
   */
  frTPOutApp2(7),
  /**
   * <p>
   * Application specific state signal
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frTPOutApp3(8),
  ;

  private final int value;
  FRETPOutSignalConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
