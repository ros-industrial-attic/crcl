package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants associated with PacketEvent handling.
 * </p>
 */
public enum FREPacketEventConstants implements ComEnum {
  /**
   * <p>
   * The SubSystemCode code used by the KAREL Built-in’s and TPP Macros.
   * </p>
   * <p>
   * The value of this constant is 115
   * </p>
   */
  frSSC_KAREL(115),
  /**
   * <p>
   * Wild card for SubSystemCode trigger
   * </p>
   * <p>
   * The value of this constant is 255
   * </p>
   */
  frSSC_Wild(255),
  /**
   * <p>
   * Wild card for RequestCode Trigger
   * </p>
   * <p>
   * The value of this constant is 255
   * </p>
   */
  frReqC_Wild(255),
  /**
   * <p>
   * Wild card for PacketID trigger
   * </p>
   * <p>
   * The value of this constant is -1
   * </p>
   */
  frPID_Wild(-1),
  ;

  private final int value;
  FREPacketEventConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
