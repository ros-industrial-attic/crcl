package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * Constants for Application Codes.
 * </p>
 */
public enum FREAPCodeConstants implements ComEnum {
  /**
   * <p>
   * Reserved - do not change.
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frSampleAppID(0),
  /**
   * <p>
   * No item.
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frNoAppID(0),
  /**
   * <p>
   * Arc welding.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frArcWeldingID(1),
  /**
   * <p>
   * Spot welding.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frSpotWeldingID(2),
  /**
   * <p>
   * Material handling.
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frMaterialHandlingID(3),
  /**
   * <p>
   * Laser welding/cutting.
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frLaserApplicationID(4),
  /**
   * <p>
   * Sealing.
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  frSealingID(5),
  /**
   * <p>
   * Painting.
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  frPaintingID(6),
  /**
   * <p>
   * Sealing - Nordson analog Pro-Flo.
   * </p>
   * <p>
   * The value of this constant is 7
   * </p>
   */
  frSealingNordsonAnalogID(7),
  /**
   * <p>
   * Sealing - Nordson digital Pro-Flo.
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frSealingNordsonDigitalID(8),
  /**
   * <p>
   * Sealing - Graco.
   * </p>
   * <p>
   * The value of this constant is 9
   * </p>
   */
  frSealingGracoID(9),
  /**
   * <p>
   * Sealing - Nordson Select Coat.
   * </p>
   * <p>
   * The value of this constant is 10
   * </p>
   */
  frSealingNordsonSelectCoatID(10),
  /**
   * <p>
   * Sealing - Nordson Urethane.
   * </p>
   * <p>
   * The value of this constant is 11
   * </p>
   */
  frSealingNordsonUrethaneID(11),
  /**
   * <p>
   * Sealing - Reserved.
   * </p>
   * <p>
   * The value of this constant is 12
   * </p>
   */
  frSealingReservedID(12),
  /**
   * <p>
   * Sealing - JESCO.
   * </p>
   * <p>
   * The value of this constant is 13
   * </p>
   */
  frSealingJESCOID(13),
  /**
   * <p>
   * Sealing - Fuid Kinetics.
   * </p>
   * <p>
   * The value of this constant is 14
   * </p>
   */
  frSealingFluidKineticsID(14),
  /**
   * <p>
   * Sealing - Robotics, Inc.
   * </p>
   * <p>
   * The value of this constant is 15
   * </p>
   */
  frSealingRoboticsIncID(15),
  /**
   * <p>
   * Sealing - Schucker A2000 and A2500.
   * </p>
   * <p>
   * The value of this constant is 16
   * </p>
   */
  frSealingSchuckerID(16),
  /**
   * <p>
   * HandlingTool.
   * </p>
   * <p>
   * The value of this constant is 17
   * </p>
   */
  frHandlingToolID(17),
  /**
   * <p>
   * BellTool.
   * </p>
   * <p>
   * The value of this constant is 18
   * </p>
   */
  frBellToolID(18),
  /**
   * <p>
   * KAREL process No.0 by SPI_TPKSS990
   * </p>
   * <p>
   * The value of this constant is 20
   * </p>
   */
  frKARELProcess0ID(20),
  /**
   * <p>
   * KAREL process No.1 by SPI_TPKSS991
   * </p>
   * <p>
   * The value of this constant is 21
   * </p>
   */
  frKARELProcess1ID(21),
  /**
   * <p>
   * KAREL process No.2 by SPI_TPKSS992
   * </p>
   * <p>
   * The value of this constant is 22
   * </p>
   */
  frKARELProcess2ID(22),
  /**
   * <p>
   * KAREL process No.3 by SPI_TPKSS993
   * </p>
   * <p>
   * The value of this constant is 23
   * </p>
   */
  frKARELProcess3ID(23),
  /**
   * <p>
   * KAREL process No.4 by SPI_TPKSS994
   * </p>
   * <p>
   * The value of this constant is 24
   * </p>
   */
  frKARELProcess4ID(24),
  /**
   * <p>
   * KAREL process No.5 by SPI_TPKSS995
   * </p>
   * <p>
   * The value of this constant is 25
   * </p>
   */
  frKARELProcess5ID(25),
  /**
   * <p>
   * KAREL process No.6 by SPI_TPKSS996
   * </p>
   * <p>
   * The value of this constant is 26
   * </p>
   */
  frKARELProcess6ID(26),
  /**
   * <p>
   * KAREL process No.7 by SPI_TPKSS997
   * </p>
   * <p>
   * The value of this constant is 27
   * </p>
   */
  frKARELProcess7ID(27),
  /**
   * <p>
   * KAREL process No.8 by SPI_TPKSS998
   * </p>
   * <p>
   * The value of this constant is 28
   * </p>
   */
  frKARELProcess8ID(28),
  /**
   * <p>
   * KAREL process No.9 by SPI_TPKSS999
   * </p>
   * <p>
   * The value of this constant is 29
   * </p>
   */
  frKARELProcess9ID(29),
  /**
   * <p>
   * LD YAG laser generator control
   * </p>
   * <p>
   * The value of this constant is 30
   * </p>
   */
  frLDYagGeneratorID(30),
  /**
   * <p>
   * LD YAG laser robot control
   * </p>
   * <p>
   * The value of this constant is 31
   * </p>
   */
  frLDYagRobotID(31),
  /**
   * <p>
   * User AMR.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frUserAMRID(1),
  /**
   * <p>
   * None.
   * </p>
   * <p>
   * The value of this constant is 0
   * </p>
   */
  frNoAppMask(0),
  /**
   * <p>
   * Arc welding mask.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frArcWeldingMask(1),
  /**
   * <p>
   * Spot welding mask.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frSpotWeldingMask(2),
  /**
   * <p>
   * Palletizing mask.
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frPalletizingMask(4),
  /**
   * <p>
   * HandlingTool mask.
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frHandlingToolMask(8),
  /**
   * <p>
   * Laser mask.
   * </p>
   * <p>
   * The value of this constant is 16
   * </p>
   */
  frLaserMask(16),
  /**
   * <p>
   * Sealing mask.
   * </p>
   * <p>
   * The value of this constant is 32
   * </p>
   */
  frSealingMask(32),
  /**
   * <p>
   * Paint mask.
   * </p>
   * <p>
   * The value of this constant is 64
   * </p>
   */
  frPaintMask(64),
  /**
   * <p>
   * Dispense mask.
   * </p>
   * <p>
   * The value of this constant is 128
   * </p>
   */
  frDispenseMask(128),
  /**
   * <p>
   * KAREL process No.0 mask
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frKARELProcess0Mask(1),
  /**
   * <p>
   * KAREL process No.1 mask
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frKARELProcess1Mask(2),
  /**
   * <p>
   * KAREL process No.2 mask
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frKARELProcess2Mask(4),
  /**
   * <p>
   * KAREL process No.3 mask
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frKARELProcess3Mask(8),
  /**
   * <p>
   * KAREL process No.4 mask
   * </p>
   * <p>
   * The value of this constant is 16
   * </p>
   */
  frKARELProcess4Mask(16),
  /**
   * <p>
   * KAREL process No.5 mask
   * </p>
   * <p>
   * The value of this constant is 32
   * </p>
   */
  frKARELProcess5Mask(32),
  /**
   * <p>
   * KAREL process No.6 mask
   * </p>
   * <p>
   * The value of this constant is 64
   * </p>
   */
  frKARELProcess6Mask(64),
  /**
   * <p>
   * KAREL process No.7 mask
   * </p>
   * <p>
   * The value of this constant is 128
   * </p>
   */
  frKARELProcess7Mask(128),
  /**
   * <p>
   * KAREL process No.8 mask
   * </p>
   * <p>
   * The value of this constant is 256
   * </p>
   */
  frKARELProcess8Mask(256),
  /**
   * <p>
   * KAREL process No.9 mask
   * </p>
   * <p>
   * The value of this constant is 512
   * </p>
   */
  frKARELProcess9Mask(512),
  ;

  private final int value;
  FREAPCodeConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
