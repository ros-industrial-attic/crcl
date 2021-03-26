package com.github.wshackle.fanuc.robotneighborhood  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants used to determine the state of a robot’s TCP/IP service.
 * </p>
 */
public enum FRERNServiceStateConstants {
  /**
   * <p>
   * Indicates that the service state is not known at this time..
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frRNServiceStateUnknown, // 0
  /**
   * <p>
   * Indicates that the service is not available on the Robot.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frRNServiceStateStopped, // 1
  /**
   * <p>
   * Indicates that the service has been started on the Robot.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frRNServiceStateStarted, // 2
}
