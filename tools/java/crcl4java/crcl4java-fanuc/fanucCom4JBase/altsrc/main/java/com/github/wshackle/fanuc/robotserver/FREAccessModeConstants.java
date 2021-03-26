package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants used when opening and reopening programs from the FRCPrograms collection.
 * </p>
 */
public enum FREAccessModeConstants {
  /**
   * <p>
   * No program access.
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frNoAccess, // 0
  /**
   * <p>
   * Read only program access.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frReadAccess, // 1
  /**
   * <p>
   * Write only program access.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frWriteAccess, // 2
  /**
   * <p>
   * Read and Write program access.
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frReadWriteAccess, // 3
  /**
   * <p>
   * Overwrite program access.
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frOverwriteAccess, // 4
  /**
   * <p>
   * Read and Overwrite program access.
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  frReadOverwriteAccess, // 5
  /**
   * <p>
   * Write and Overwrite program access.
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  frWriteOverwriteAccess, // 6
  /**
   * <p>
   * All programs access.
   * </p>
   * <p>
   * The value of this constant is 7
   * </p>
   */
  frAllAccess, // 7
}
