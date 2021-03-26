package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants used when opening or reopening a TP program from the FRCPrograms collection.
 * </p>
 */
public enum FRERejectModeConstants {
  /**
   * <p>
   * No program reject.
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frNoReject, // 0
  /**
   * <p>
   * Read program reject.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frReadReject, // 1
  /**
   * <p>
   * Write program reject.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frWriteReject, // 2
  /**
   * <p>
   * Read and Write program reject.
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frReadWriteReject, // 3
  /**
   * <p>
   * Overwrite program reject.
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frOverwriteReject, // 4
  /**
   * <p>
   * Read and Overwrite program reject.
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  frReadOverwriteReject, // 5
  /**
   * <p>
   * Write program reject.
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  frWriteOverwriteReject, // 6
  /**
   * <p>
   * All program reject.
   * </p>
   * <p>
   * The value of this constant is 7
   * </p>
   */
  frAllReject, // 7
}
