package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * Constants for TPScreen control characters.
 * </p>
 */
public enum FRETPScreenConstants implements ComEnum {
  /**
   * <p>
   * ASCII Backspace.
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frTPScrnBackspace(8),
  /**
   * <p>
   * ASCII Tab.
   * </p>
   * <p>
   * The value of this constant is 9
   * </p>
   */
  frTPScrnTab(9),
  /**
   * <p>
   * ASCII Linefeed.
   * </p>
   * <p>
   * The value of this constant is 10
   * </p>
   */
  frTPScrnLinefeed(10),
  /**
   * <p>
   * ASCII Vertical Tab.
   * </p>
   * <p>
   * The value of this constant is 11
   * </p>
   */
  frTPScrnVertTab(11),
  /**
   * <p>
   * ASCII Formfeed.
   * </p>
   * <p>
   * The value of this constant is 12
   * </p>
   */
  frTPScrnFormfeed(12),
  /**
   * <p>
   * ASCII Carriage Return.
   * </p>
   * <p>
   * The value of this constant is 13
   * </p>
   */
  frTPScrnCR(13),
  /**
   * <p>
   * Clear Window.
   * </p>
   * <p>
   * The value of this constant is 128
   * </p>
   */
  frTPScrnClear(128),
  /**
   * <p>
   * Clear to End of Line.
   * </p>
   * <p>
   * The value of this constant is 129
   * </p>
   */
  frTPScrnClearEOL(129),
  /**
   * <p>
   * Clear to End of Window.
   * </p>
   * <p>
   * The value of this constant is 130
   * </p>
   */
  frTPScrnClearEOW(130),
  /**
   * <p>
   * Cursor Position.
   * </p>
   * <p>
   * The value of this constant is 131
   * </p>
   */
  frTPScrnCUP(131),
  /**
   * <p>
   * Cursor Return.
   * </p>
   * <p>
   * The value of this constant is 132
   * </p>
   */
  frTPScrnReturn(132),
  /**
   * <p>
   * Cursor Down.
   * </p>
   * <p>
   * The value of this constant is 133
   * </p>
   */
  frTPScrnCursorDown(133),
  /**
   * <p>
   * Cursor Up.
   * </p>
   * <p>
   * The value of this constant is 134
   * </p>
   */
  frTPScrnCursorUp(134),
  /**
   * <p>
   * New Line.
   * </p>
   * <p>
   * The value of this constant is 135
   * </p>
   */
  frTPScrnNewLine(135),
  /**
   * <p>
   * Cursor Back.
   * </p>
   * <p>
   * The value of this constant is 136
   * </p>
   */
  frTPScrnCursorBack(136),
  /**
   * <p>
   * Cursor Home.
   * </p>
   * <p>
   * The value of this constant is 137
   * </p>
   */
  frTPScrnCursorHome(137),
  /**
   * <p>
   * Blink.
   * </p>
   * <p>
   * The value of this constant is 138
   * </p>
   */
  frTPScrnBlink(138),
  /**
   * <p>
   * Reverse Video.
   * </p>
   * <p>
   * The value of this constant is 139
   * </p>
   */
  frTPScrnReverseVid(139),
  /**
   * <p>
   * Bold.
   * </p>
   * <p>
   * The value of this constant is 140
   * </p>
   */
  frTPScrnBold(140),
  /**
   * <p>
   * Underscore.
   * </p>
   * <p>
   * The value of this constant is 141
   * </p>
   */
  frTPScrnUnderscore(141),
  /**
   * <p>
   * Double Wide.
   * </p>
   * <p>
   * The value of this constant is 142
   * </p>
   */
  frTPScrnWideSize(142),
  /**
   * <p>
   * Normal Attributes.
   * </p>
   * <p>
   * The value of this constant is 143
   * </p>
   */
  frTPScrnNormal(143),
  /**
   * <p>
   * Blink Off.
   * </p>
   * <p>
   * The value of this constant is 144
   * </p>
   */
  frTPScrnNoBlink(144),
  /**
   * <p>
   * Reverse Video Off.
   * </p>
   * <p>
   * The value of this constant is 145
   * </p>
   */
  frTPScrnNoReverseVid(145),
  /**
   * <p>
   * Graphics Character Set.
   * </p>
   * <p>
   * The value of this constant is 146
   * </p>
   */
  frTPScrnGraphics(146),
  /**
   * <p>
   * ASCII Character Set.
   * </p>
   * <p>
   * The value of this constant is 147
   * </p>
   */
  frTPScrnASCII(147),
  /**
   * <p>
   * Double High.
   * </p>
   * <p>
   * The value of this constant is 148
   * </p>
   */
  frTPScrnHighSize(148),
  /**
   * <p>
   * Normal Size.
   * </p>
   * <p>
   * The value of this constant is 153
   * </p>
   */
  frTPScrnNormalSize(153),
  /**
   * <p>
   * Cursor Forward.
   * </p>
   * <p>
   * The value of this constant is 156
   * </p>
   */
  frTPScrnCursorForward(156),
  /**
   * <p>
   * Scroll Region.
   * </p>
   * <p>
   * The value of this constant is 157
   * </p>
   */
  frTPScrnScroll(157),
  /**
   * <p>
   * European Character Set.
   * </p>
   * <p>
   * The value of this constant is 158
   * </p>
   */
  frTPScrnEuropean(158),
  /**
   * <p>
   * Katakana Character Set.
   * </p>
   * <p>
   * The value of this constant is 159
   * </p>
   */
  frTPScrnKatakana(159),
  ;

  private final int value;
  FRETPScreenConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
