package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of save option constants used when programs or variables to a file.
 * </p>
 */
public enum FRESaveOptionConstants {
  /**
   * <p>
   * 	Save the file without automatically overwriting any older version
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frStandardSave, // 0
  /**
   * <p>
   * Save the file and automatically overwrite any older versions
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frOverwriteSave, // 1
}
