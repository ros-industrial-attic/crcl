package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * Enumerated constants used for task attributes.
 * </p>
 */
public enum FRETaskAttributeConstants implements ComEnum {
  /**
   * <p>
   * Task all attribute, read attribute only
   * </p>
   * <p>
   * The value of this constant is 100
   * </p>
   */
  frTaskAttrAllAtr(100),
  /**
   * <p>
   * Task name attribute
   * </p>
   * <p>
   * The value of this constant is 101
   * </p>
   */
  frTaskAttrTaskName(101),
  /**
   * <p>
   * Task priority attribute
   * </p>
   * <p>
   * The value of this constant is 102
   * </p>
   */
  frTaskAttrTaskPri(102),
  /**
   * <p>
   * Task duration attribute
   * </p>
   * <p>
   * The value of this constant is 103
   * </p>
   */
  frTaskAttrDuration(103),
  /**
   * <p>
   * Task EPT index attribute
   * </p>
   * <p>
   * The value of this constant is 104
   * </p>
   */
  frTaskAttrEPTIndex(104),
  /**
   * <p>
   * Task program type attribute
   * </p>
   * <p>
   * The value of this constant is 105
   * </p>
   */
  frTaskAttrProgType(105),
  /**
   * <p>
   * Task current line attribute
   * </p>
   * <p>
   * The value of this constant is 106
   * </p>
   */
  frTaskAttrCurLine(106),
  /**
   * <p>
   * Task status attribute
   * </p>
   * <p>
   * The value of this constant is 107
   * </p>
   */
  frTaskAttrTaskSt(107),
  /**
   * <p>
   * Task held attribute
   * </p>
   * <p>
   * The value of this constant is 108
   * </p>
   */
  frTaskAttrTskHld(108),
  /**
   * <p>
   * Task stack size attribute
   * </p>
   * <p>
   * The value of this constant is 109
   * </p>
   */
  frTaskAttrStkSize(109),
  /**
   * <p>
   * Task ignore abort attribute
   * </p>
   * <p>
   * The value of this constant is 110
   * </p>
   */
  frTaskAttrIgnoreAbort(110),
  /**
   * <p>
   * Task ignore pause attribute
   * </p>
   * <p>
   * The value of this constant is 111
   * </p>
   */
  frTaskAttrIgnorePause(111),
  /**
   * <p>
   * Task busy lamp off attribute
   * </p>
   * <p>
   * The value of this constant is 112
   * </p>
   */
  frTaskAttrBusyLampOff(112),
  /**
   * <p>
   * Task step task attribute
   * </p>
   * <p>
   * The value of this constant is 113
   * </p>
   */
  frTaskAttrStepTask(113),
  /**
   * <p>
   * Task trace enabled attribute
   * </p>
   * <p>
   * The value of this constant is 114
   * </p>
   */
  frTaskAttrTraceEnb(114),
  /**
   * <p>
   * Task TP motion attribute
   * </p>
   * <p>
   * The value of this constant is 115
   * </p>
   */
  frTaskAttrTPMotion(115),
  /**
   * <p>
   * Task super motion attribute
   * </p>
   * <p>
   * The value of this constant is 116
   * </p>
   */
  frTaskAttrSuperMotion(116),
  /**
   * <p>
   * Task number of child tasks attribute
   * </p>
   * <p>
   * The value of this constant is 117
   * </p>
   */
  frTaskAttrNumChild(117),
  /**
   * <p>
   * Task trace length attribute
   * </p>
   * <p>
   * The value of this constant is 118
   * </p>
   */
  frTaskAttrTraceLength(118),
  /**
   * <p>
   * Task number of parents attribute
   * </p>
   * <p>
   * The value of this constant is 119
   * </p>
   */
  frTaskAttrParenNum(119),
  /**
   * <p>
   * Task invisible attribute
   * </p>
   * <p>
   * The value of this constant is 120
   * </p>
   */
  frTaskAttrInvisibleTask(120),
  /**
   * <p>
   * Task system task attribute
   * </p>
   * <p>
   * The value of this constant is 121
   * </p>
   */
  frTaskAttrSystemTask(121),
  /**
   * <p>
   * Task variable write enable attribute.
   * </p>
   * <p>
   * The value of this constant is 122
   * </p>
   */
  frTaskAttrVarWriteEnable(122),
  /**
   * <p>
   * Task pause on shift attribute
   * </p>
   * <p>
   * The value of this constant is 123
   * </p>
   */
  frTaskAttrPauseOnShift(123),
  /**
   * <p>
   * Task TCD status attribute
   * </p>
   * <p>
   * The value of this constant is 124
   * </p>
   */
  frTaskAttrTCDStatus(124),
  /**
   * <p>
   * Task not circular motion attribute
   * </p>
   * <p>
   * The value of this constant is 125
   * </p>
   */
  frTaskAttrCircMotion(125),
  /**
   * <p>
   * Task locked arm attribute
   * </p>
   * <p>
   * The value of this constant is 126
   * </p>
   */
  frTaskAttrLockedArm(126),
  /**
   * <p>
   * Task motion control attribute
   * </p>
   * <p>
   * The value of this constant is 127
   * </p>
   */
  frTaskAttrMotionCtl(127),
  /**
   * <p>
   * Task number of outstanding MMRs attribute
   * </p>
   * <p>
   * The value of this constant is 128
   * </p>
   */
  frTaskAttrNumOutMMR(128),
  /**
   * <p>
   * Task error program name attribute
   * </p>
   * <p>
   * The value of this constant is 129
   * </p>
   */
  frTaskAttrERPrgName(129),
  /**
   * <p>
   * User task ID attribute
   * </p>
   * <p>
   * The value of this constant is 130
   * </p>
   */
  frTaskAttrUserTaskID(130),
  /**
   * <p>
   * Task program name attribute
   * </p>
   * <p>
   * The value of this constant is 131
   * </p>
   */
  frTaskAttrProgName(131),
  /**
   * <p>
   * Task routine name attribute
   * </p>
   * <p>
   * The value of this constant is 132
   * </p>
   */
  frTaskAttrRoutName(132),
  ;

  private final int value;
  FRETaskAttributeConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
