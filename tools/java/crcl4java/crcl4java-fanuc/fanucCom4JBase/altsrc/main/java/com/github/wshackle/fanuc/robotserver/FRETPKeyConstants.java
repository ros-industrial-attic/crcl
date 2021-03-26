package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * Constants for Teach Pendant keys.
 * </p>
 */
public enum FRETPKeyConstants implements ComEnum {
  /**
   * <p>
   * Up arrow.
   * </p>
   * <p>
   * The value of this constant is 212
   * </p>
   */
  frTPKeyUpArw(212),
  /**
   * <p>
   * Down arrow.
   * </p>
   * <p>
   * The value of this constant is 213
   * </p>
   */
  frTPKeyDnArw(213),
  /**
   * <p>
   * Right arrow.
   * </p>
   * <p>
   * The value of this constant is 208
   * </p>
   */
  frTPKeyRtArw(208),
  /**
   * <p>
   * Left arrow.
   * </p>
   * <p>
   * The value of this constant is 209
   * </p>
   */
  frTPKeyLfArw(209),
  /**
   * <p>
   * Shift up arrow.
   * </p>
   * <p>
   * The value of this constant is 204
   * </p>
   */
  frTPKeyShfUpArw(204),
  /**
   * <p>
   * Shift down arrow.
   * </p>
   * <p>
   * The value of this constant is 205
   * </p>
   */
  frTPKeyShfDnArw(205),
  /**
   * <p>
   * Shift right arrow.
   * </p>
   * <p>
   * The value of this constant is 206
   * </p>
   */
  frTPKeyShfRtArw(206),
  /**
   * <p>
   * Shift left arrow.
   * </p>
   * <p>
   * The value of this constant is 207
   * </p>
   */
  frTPKeyShfLfArw(207),
  /**
   * <p>
   * PREV key.
   * </p>
   * <p>
   * The value of this constant is 128
   * </p>
   */
  frTPKeyPrev(128),
  /**
   * <p>
   * F1 key.
   * </p>
   * <p>
   * The value of this constant is 129
   * </p>
   */
  frTPKeyF1(129),
  /**
   * <p>
   * F2 key.
   * </p>
   * <p>
   * The value of this constant is 131
   * </p>
   */
  frTPKeyF2(131),
  /**
   * <p>
   * F3 key.
   * </p>
   * <p>
   * The value of this constant is 132
   * </p>
   */
  frTPKeyF3(132),
  /**
   * <p>
   * F4 key.
   * </p>
   * <p>
   * The value of this constant is 133
   * </p>
   */
  frTPKeyF4(133),
  /**
   * <p>
   * F5 key.
   * </p>
   * <p>
   * The value of this constant is 134
   * </p>
   */
  frTPKeyF5(134),
  /**
   * <p>
   * NEXT key.
   * </p>
   * <p>
   * The value of this constant is 135
   * </p>
   */
  frTPKeyNext(135),
  /**
   * <p>
   * Shift PREV key.
   * </p>
   * <p>
   * The value of this constant is 136
   * </p>
   */
  frTPKeyShfPrev(136),
  /**
   * <p>
   * Shift F1 key.
   * </p>
   * <p>
   * The value of this constant is 137
   * </p>
   */
  frTPKeyShfF1(137),
  /**
   * <p>
   * Shift F2 key.
   * </p>
   * <p>
   * The value of this constant is 138
   * </p>
   */
  frTPKeyShfF2(138),
  /**
   * <p>
   * Shift F3 key.
   * </p>
   * <p>
   * The value of this constant is 139
   * </p>
   */
  frTPKeyShfF3(139),
  /**
   * <p>
   * Shift F4 key.
   * </p>
   * <p>
   * The value of this constant is 140
   * </p>
   */
  frTPKeyShfF4(140),
  /**
   * <p>
   * Shift F5 key.
   * </p>
   * <p>
   * The value of this constant is 141
   * </p>
   */
  frTPKeyShfF5(141),
  /**
   * <p>
   * Shift NEXT key.
   * </p>
   * <p>
   * The value of this constant is 142
   * </p>
   */
  frTPKeyShfNext(142),
  /**
   * <p>
   * User 1 key.
   * </p>
   * <p>
   * The value of this constant is 173
   * </p>
   */
  frTPKeyUsr1(173),
  /**
   * <p>
   * User 2 key.
   * </p>
   * <p>
   * The value of this constant is 174
   * </p>
   */
  frTPKeyUsr2(174),
  /**
   * <p>
   * User 3 key.
   * </p>
   * <p>
   * The value of this constant is 175
   * </p>
   */
  frTPKeyUsr3(175),
  /**
   * <p>
   * User 4 key.
   * </p>
   * <p>
   * The value of this constant is 176
   * </p>
   */
  frTPKeyUsr4(176),
  /**
   * <p>
   * User 5 key.
   * </p>
   * <p>
   * The value of this constant is 177
   * </p>
   */
  frTPKeyUsr5(177),
  /**
   * <p>
   * User 6 key.
   * </p>
   * <p>
   * The value of this constant is 178
   * </p>
   */
  frTPKeyUsr6(178),
  /**
   * <p>
   * User 7 key.
   * </p>
   * <p>
   * The value of this constant is 210
   * </p>
   */
  frTPKeyUsr7(210),
  /**
   * <p>
   * Shift User 1 key.
   * </p>
   * <p>
   * The value of this constant is 179
   * </p>
   */
  frTPKeyShfUsr1(179),
  /**
   * <p>
   * Shift User 2 key.
   * </p>
   * <p>
   * The value of this constant is 180
   * </p>
   */
  frTPKeyShfUsr2(180),
  /**
   * <p>
   * Shift User 3 key.
   * </p>
   * <p>
   * The value of this constant is 181
   * </p>
   */
  frTPKeyShfUsr3(181),
  /**
   * <p>
   * Shift User 4 key.
   * </p>
   * <p>
   * The value of this constant is 182
   * </p>
   */
  frTPKeyShfUsr4(182),
  /**
   * <p>
   * Shift User 5 key.
   * </p>
   * <p>
   * The value of this constant is 183
   * </p>
   */
  frTPKeyShfUsr5(183),
  /**
   * <p>
   * Shift User 6 key.
   * </p>
   * <p>
   * The value of this constant is 184
   * </p>
   */
  frTPKeyShfUsr6(184),
  /**
   * <p>
   * Shift User 7 key.
   * </p>
   * <p>
   * The value of this constant is 211
   * </p>
   */
  frTPKeyShfUsr7(211),
  /**
   * <p>
   * ITEM key.
   * </p>
   * <p>
   * The value of this constant is 148
   * </p>
   */
  frTPKeyItem(148),
  /**
   * <p>
   * STEP key.
   * </p>
   * <p>
   * The value of this constant is 152
   * </p>
   */
  frTPKeyStep(152),
  /**
   * <p>
   * Shift ITEM key.
   * </p>
   * <p>
   * The value of this constant is 154
   * </p>
   */
  frTPKeyShfItem(154),
  /**
   * <p>
   * Shift STEP key.
   * </p>
   * <p>
   * The value of this constant is 157
   * </p>
   */
  frTPKeyShfStep(157),
  /**
   * <p>
   * Enter.
   * </p>
   * <p>
   * The value of this constant is 13
   * </p>
   */
  frTPKeyEnter(13),
  /**
   * <p>
   * Backspace.
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frTPKeyBackspace(8),
  ;

  private final int value;
  FRETPKeyConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
