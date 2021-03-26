package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants used to identify the storage type of a teach pendant program (FRCTPProgram) object.
 * </p>
 */
public enum FRETPStorageTypeConstants implements ComEnum {
  /**
   * <p>
   * The program is loaded in CMOS (TPP PERM) memory.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frCMOSStorageType(1),
  /**
   * <p>
   * The program is considered as being loaded but it resides in the file system as a .TP file at the location defined by $FILE_PATH. 
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frFileStorageType(3),
  /**
   * <p>
   * The program is stored in the file system but is current loaded in DRAM to run.
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  frFileDRAMStorageType(6),
  /**
   * <p>
   * The program is stored in the file system but is current loaded in CMOS to be edited.
   * </p>
   * <p>
   * The value of this constant is 7
   * </p>
   */
  frFileCMOSStorageType(7),
  /**
   * <p>
   * The program is loaded in shadow memory.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frShadowStorageType(2),
  /**
   * <p>
   * The program is stored in shadow memory but is current loaded in CMOS to be edited.
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  frShadowCMOSStorageType(5),
  /**
   * <p>
   * The program is loaded in shadow memory and will be loaded into DRAM when requested to run.
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frShadowOnDemandStorageType(4),
  /**
   * <p>
   * The program is stored in shadow memory but is current loaded in DRAM to be run.
   * </p>
   * <p>
   * The value of this constant is 9
   * </p>
   */
  frShadowOnDemandDRAMStorageType(9),
  /**
   * <p>
   * The program is stored in shadow memory but is current loaded in CMOS to be edited.
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frShadowOnDemandCMOSStorageType(8),
  ;

  private final int value;
  FRETPStorageTypeConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
