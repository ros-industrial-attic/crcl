package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * Enumeration of class constants used when saving to a file.
 * </p>
 */
public enum FRESaveClassConstants {
  /**
   * <p>
   * Save all system variables
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frAllSaveClass, // 0
  /**
   * <p>
   * Save majority of system variables
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frMajoritySaveClass, // 1
  /**
   * <p>
   * Save mastering system variables
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frMasterSaveClass, // 2
  /**
   * <p>
   * Save servo system variables
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frServroSaveClass, // 3
  /**
   * <p>
   * Save nosave system variables
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frNoSaveClass, // 4
  /**
   * <p>
   * Save macro system variables
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  frMacroSaveClass, // 5
  /**
   * <p>
   * Save Spot system variables
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  frSpotSaveClass, // 6
  /**
   * <p>
   * Save PROFIBUS system variables
   * </p>
   * <p>
   * The value of this constant is 7
   * </p>
   */
  frProfibusSaveClass, // 7
  /**
   * <p>
   * Save PROFIBUS-FMS system variables
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frProfibusFMSSaveClass, // 8
  /**
   * <p>
   * Save password system variables
   * </p>
   * <p>
   * The value of this constant is 9
   * </p>
   */
  frPasswordSaveClass, // 9
  /**
   * <p>
   * Save network host system variables
   * </p>
   * <p>
   * The value of this constant is 10
   * </p>
   */
  frHostSaveClass, // 10
  /**
   * <p>
   * Save FTP server access control system variables
   * </p>
   * <p>
   * The value of this constant is 11
   * </p>
   */
  frFTPAccessSaveClass, // 11
  /**
   * <p>
   * 	Save intergrated PMC system variables
   * </p>
   * <p>
   * The value of this constant is 12
   * </p>
   */
  frPMCSaveClass, // 12
}
