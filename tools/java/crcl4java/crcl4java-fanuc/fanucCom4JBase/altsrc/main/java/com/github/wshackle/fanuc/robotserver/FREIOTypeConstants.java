package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants used to identify the different types of I/O.
 * </p>
 */
public enum FREIOTypeConstants implements ComEnum {
  /**
   * <p>
   * Digital Input
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frDInType(1),
  /**
   * <p>
   * Digital Output
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frDOutType(2),
  /**
   * <p>
   * Analog Input
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frAInType(3),
  /**
   * <p>
   * Analog Output
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frAOutType(4),
  /**
   * <p>
   * PLC Input
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  frPLCInType(6),
  /**
   * <p>
   * PLC Output
   * </p>
   * <p>
   * The value of this constant is 7
   * </p>
   */
  frPLCOutType(7),
  /**
   * <p>
   * Robot Digital Input
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frRDInType(8),
  /**
   * <p>
   * Robot Digital Output
   * </p>
   * <p>
   * The value of this constant is 9
   * </p>
   */
  frRDOutType(9),
  /**
   * <p>
   * System Operator Panel Input
   * </p>
   * <p>
   * The value of this constant is 11
   * </p>
   */
  frSOPInType(11),
  /**
   * <p>
   * System Operator Panel Output
   * </p>
   * <p>
   * The value of this constant is 12
   * </p>
   */
  frSOPOutType(12),
  /**
   * <p>
   * Teach Pendant Digital Input
   * </p>
   * <p>
   * The value of this constant is 14
   * </p>
   */
  frTPInType(14),
  /**
   * <p>
   * Teach Pendant Digital Output
   * </p>
   * <p>
   * The value of this constant is 15
   * </p>
   */
  frTPOutType(15),
  /**
   * <p>
   * Weld Digital Input
   * </p>
   * <p>
   * The value of this constant is 16
   * </p>
   */
  frWDInType(16),
  /**
   * <p>
   * Weld Digital Output
   * </p>
   * <p>
   * The value of this constant is 17
   * </p>
   */
  frWDOutType(17),
  /**
   * <p>
   * Group Input
   * </p>
   * <p>
   * The value of this constant is 18
   * </p>
   */
  frGPInType(18),
  /**
   * <p>
   * Group Output
   * </p>
   * <p>
   * The value of this constant is 19
   * </p>
   */
  frGPOutType(19),
  /**
   * <p>
   * User Operator Panel Input
   * </p>
   * <p>
   * The value of this constant is 20
   * </p>
   */
  frUOPInType(20),
  /**
   * <p>
   * User Operator Panel Output
   * </p>
   * <p>
   * The value of this constant is 21
   * </p>
   */
  frUOPOutType(21),
  /**
   * <p>
   * Laser Digital Input
   * </p>
   * <p>
   * The value of this constant is 22
   * </p>
   */
  frLDInType(22),
  /**
   * <p>
   * Laser Digital Output
   * </p>
   * <p>
   * The value of this constant is 23
   * </p>
   */
  frLDOutType(23),
  /**
   * <p>
   * Laser Analog Input
   * </p>
   * <p>
   * The value of this constant is 24
   * </p>
   */
  frLAInType(24),
  /**
   * <p>
   * Laser Analog Output
   * </p>
   * <p>
   * The value of this constant is 25
   * </p>
   */
  frLAOutType(25),
  /**
   * <p>
   * Weld Stick Input
   * </p>
   * <p>
   * The value of this constant is 26
   * </p>
   */
  frWSTKInType(26),
  /**
   * <p>
   * Weld Stick Output
   * </p>
   * <p>
   * The value of this constant is 27
   * </p>
   */
  frWSTKOutType(27),
  /**
   * <p>
   * Flag type
   * </p>
   * <p>
   * The value of this constant is 35
   * </p>
   */
  frFlagType(35),
  /**
   * <p>
   * Marker type
   * </p>
   * <p>
   * The value of this constant is 36
   * </p>
   */
  frMarkerType(36),
  /**
   * <p>
   * Maximum system defined I/O type
   * </p>
   * <p>
   * The value of this constant is 38
   * </p>
   */
  frMaxIOType(38),
  ;

  private final int value;
  FREIOTypeConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
