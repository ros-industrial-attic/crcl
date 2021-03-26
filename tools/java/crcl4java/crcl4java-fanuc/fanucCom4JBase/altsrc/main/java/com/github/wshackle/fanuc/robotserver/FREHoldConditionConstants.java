package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * Enumerated constants used for task hold conditions
 * </p>
 */
public enum FREHoldConditionConstants implements ComEnum {
  /**
   * <p>
   * Hold on any condition
   * </p>
   * <p>
   * The value of this constant is -1
   * </p>
   */
  frHoldAny(-1),
  /**
   * <p>
   * Wait for KAREL calls to Mnemonics or vice versa to finish
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frHoldChgExe(1),
  /**
   * <p>
   * Wait for internal pause error to occur
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frHoldInrPaus(2),
  /**
   * <p>
   * Wait for internal abort error to occur
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frHoldInrAbrt(4),
  /**
   * <p>
   * Wait for a failure to lock the default motion group
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frHoldFailLk(8),
  /**
   * <p>
   * Wait for motion started
   * </p>
   * <p>
   * The value of this constant is 16
   * </p>
   */
  frHoldMoStrt(16),
  /**
   * <p>
   * Wait for motion done
   * </p>
   * <p>
   * The value of this constant is 32
   * </p>
   */
  frHoldMoDone(32),
  /**
   * <p>
   * Wait for UNWAIT action
   * </p>
   * <p>
   * The value of this constant is 64
   * </p>
   */
  frHoldUnwait(64),
  /**
   * <p>
   * Wait for serial I/O request to process
   * </p>
   * <p>
   * The value of this constant is 128
   * </p>
   */
  frHoldIOPkt(128),
  /**
   * <p>
   * Wait for ENDCONCURRENT to finish processing
   * </p>
   * <p>
   * The value of this constant is 256
   * </p>
   */
  frHoldCltDone(256),
  /**
   * <p>
   * Wait for Stop/Cancel request to finish
   * </p>
   * <p>
   * The value of this constant is 512
   * </p>
   */
  frHoldCanDone(512),
  /**
   * <p>
   * Softparts waits for any packet 
   * </p>
   * <p>
   * The value of this constant is 1024
   * </p>
   */
  frHoldPktWt(1024),
  /**
   * <p>
   * Wait for release done message
   * </p>
   * <p>
   * The value of this constant is 2048
   * </p>
   */
  frHoldRlsDon(2048),
  /**
   * <p>
   * Waits for attach done message
   * </p>
   * <p>
   * The value of this constant is 4096
   * </p>
   */
  frHoldActDon(4096),
  /**
   * <p>
   * Wait for AMR started
   * </p>
   * <p>
   * The value of this constant is 8192
   * </p>
   */
  frHoldAMRStrt(8192),
  /**
   * <p>
   * Wait for AMR done
   * </p>
   * <p>
   * The value of this constant is 16384
   * </p>
   */
  frHoldAMRDone(16384),
  /**
   * <p>
   * Wait for AMR packet
   * </p>
   * <p>
   * The value of this constant is 32768
   * </p>
   */
  frHoldAMRPkt(32768),
  /**
   * <p>
   * Wait for User AMR packet
   * </p>
   * <p>
   * The value of this constant is 65536
   * </p>
   */
  frHoldUAMRPkt(65536),
  /**
   * <p>
   * Wait for vision builtin to complete
   * </p>
   * <p>
   * The value of this constant is 131072
   * </p>
   */
  frHoldVBltin(131072),
  /**
   * <p>
   * Wait for nowait pulse complete
   * </p>
   * <p>
   * The value of this constant is 262144
   * </p>
   */
  frHoldPulsDone(262144),
  /**
   * <p>
   * Wait for nowait MMR/AMR complete
   * </p>
   * <p>
   * The value of this constant is 524288
   * </p>
   */
  frHoldNoWtDone(524288),
  /**
   * <p>
   * Wait for unlock group complete 
   * </p>
   * <p>
   * The value of this constant is 1048576
   * </p>
   */
  frHoldUnlock(1048576),
  /**
   * <p>
   * Wait for TIME BEFORE/AFTER call
   * </p>
   * <p>
   * The value of this constant is 2097152
   * </p>
   */
  frHoldTimeDone(2097152),
  ;

  private final int value;
  FREHoldConditionConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
