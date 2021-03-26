package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants identifying the device currently in control of motion. 
 * </p>
 */
public enum FRERemoteMotionMasterConstants {
  /**
   * <p>
   * The motion master code used to specify the User Operating Panel as device in control of motion.
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frUOPMaster, // 0
  /**
   * <p>
   * The motion master code used to specify that KCL is in control of motion.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frKCLMaster, // 1
  /**
   * <p>
   * The motion master code used to specify that the Host device is in control of motion.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frHostMaster, // 2
  /**
   * <p>
   * The motion master code used to specify no device is in control of motion.
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frNoMaster, // 3
}
