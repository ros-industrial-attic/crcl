package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants used to identify program attributes.  
 * </p>
 */
public enum FREAttributeConstants {
  /**
   * <p>
   * Constant to identify the Program Name program attribute.
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frNameAttr, // 0
  /**
   * <p>
   * Constant to identify the Owner program attribute.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frOwnerAttr, // 1
  /**
   * <p>
   * Constant to identify the Comment program attribute.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frCommentAttr, // 2
  /**
   * <p>
   * Constant to identify the Program Size program attribute.
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frSizeAttr, // 3
  /**
   * <p>
   * Constant to identify the Allocated Size program attribute.
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frAllcSizeAttr, // 4
  /**
   * <p>
   * Constant to identify the Number of Lines program attribute.
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  frNumLinesAttr, // 5
  /**
   * <p>
   * Constant to identify the Created, i.e. loaded, Time program attribute.
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  frCreatedAttr, // 6
  /**
   * <p>
   * Constant to identify the Last Modified Time program attribute.
   * </p>
   * <p>
   * The value of this constant is 7
   * </p>
   */
  frLastModifiedAttr, // 7
  /**
   * <p>
   * Constant to identify the Original File Name program attribute.
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frOriginalNameAttr, // 8
  /**
   * <p>
   * Constant to identify the Version program attribute.
   * </p>
   * <p>
   * The value of this constant is 9
   * </p>
   */
  frVersionAttr, // 9
  /**
   * <p>
   * Constant to identify the DefaultGroup program attribute.
   * </p>
   * <p>
   * The value of this constant is 10
   * </p>
   */
  frDefaultGroupAttr, // 10
  /**
   * <p>
   * Constant to identify the Protection program attribute.
   * </p>
   * <p>
   * The value of this constant is 11
   * </p>
   */
  frProtectionAttr, // 11
  /**
   * <p>
   * Constant to identify the Protection program attribute.
   * </p>
   * <p>
   * The value of this constant is 12
   * </p>
   */
  frStackSizeAttr, // 12
  /**
   * <p>
   * Constant to identify the Task Priority program attribute.
   * </p>
   * <p>
   * The value of this constant is 13
   * </p>
   */
  frPriorityAttr, // 13
  /**
   * <p>
   * Constant to identify the Time Slice Duration program attribute.
   * </p>
   * <p>
   * The value of this constant is 14
   * </p>
   */
  frTimeSliceAttr, // 14
  /**
   * <p>
   * Constant to identify the BusyLampOff program attribute.
   * </p>
   * <p>
   * The value of this constant is 15
   * </p>
   */
  frBusyLampOffAttr, // 15
  /**
   * <p>
   * Constant to identify the IgnoreAbort program attribute.
   * </p>
   * <p>
   * The value of this constant is 16
   * </p>
   */
  frIgnoreAbortAttr, // 16
  /**
   * <p>
   * Constant to identify the IgnorePause program attribute.
   * </p>
   * <p>
   * The value of this constant is 17
   * </p>
   */
  frIgnorePauseAttr, // 17
  /**
   * <p>
   * Constant to identify the Invisible program attribute.
   * </p>
   * <p>
   * The value of this constant is 18
   * </p>
   */
  frInvisibleAttr, // 18
  /**
   * <p>
   * Constant to identify the Storage Type program attribute.
   * </p>
   * <p>
   * The value of this constant is 19
   * </p>
   */
  frStorageTypeAttr, // 19
}
