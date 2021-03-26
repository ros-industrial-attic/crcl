package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants that identify predefined pipes.
 * </p>
 */
public enum FREPipeIDConstants implements ComEnum {
  /**
   * <p>
   * ID constant for one of the predefined KAREL pipes.
   * </p>
   * <p>
   * The value of this constant is 7
   * </p>
   */
  frPipeKAREL1ID(7),
  /**
   * <p>
   * ID constant for one of the predefined KAREL pipes.
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frPipeKAREL2ID(8),
  /**
   * <p>
   * ID constant for one of the predefined KAREL pipes.
   * </p>
   * <p>
   * The value of this constant is 9
   * </p>
   */
  frPipeKAREL3ID(9),
  /**
   * <p>
   * ID constant for one of the predefined KAREL pipes.
   * </p>
   * <p>
   * The value of this constant is 10
   * </p>
   */
  frPipeKAREL4ID(10),
  /**
   * <p>
   * ID constant for one of the predefined KAREL pipes.
   * </p>
   * <p>
   * The value of this constant is 11
   * </p>
   */
  frPipeKAREL5ID(11),
  /**
   * <p>
   * ID constant for the pipe to monitor output from the AX task.
   * </p>
   * <p>
   * The value of this constant is 16
   * </p>
   */
  frPipeAxTaskID(16),
  /**
   * <p>
   * ID constant for the pipe used by the Data Monitor option.
   * </p>
   * <p>
   * The value of this constant is 6
   * </p>
   */
  frPipeDataMonitorID(6),
  /**
   * <p>
   * ID constant for the pipe to monitor the error logger.
   * </p>
   * <p>
   * The value of this constant is 13
   * </p>
   */
  frPipeErrorLoggerID(13),
  /**
   * <p>
   * ID constant for the pipe to monitor the motion filter.
   * </p>
   * <p>
   * The value of this constant is 12
   * </p>
   */
  frPipeFilterID(12),
  /**
   * <p>
   * ID constant for the pipe to monitor Joint angles from the Interpolator.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frPipeJointAngleID(1),
  /**
   * <p>
   * ID constant for the pipe to monitor other data from the interpolator.
   * </p>
   * <p>
   * The value of this constant is 15
   * </p>
   */
  frPipeIntrOtherID(15),
  /**
   * <p>
   * ID constant for the pipe to monitor the analog I/O signals being scanned.
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frPipeIOScanAnalogID(3),
  /**
   * <p>
   * ID constant for the pipe to monitor the digital I/O signals being scanned.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frPipeIOScanDigitalID(2),
  /**
   * <p>
   * ID constant for the pipe to monitor motion data for the TP profiler.
   * </p>
   * <p>
   * The value of this constant is 18
   * </p>
   */
  frPipeMoDaqID(18),
  /**
   * <p>
   * ID constant for the pipe to monitor motion data from the PG task.
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  frPipePgMotionID(5),
  /**
   * <p>
   * ID constant for the pipe to monitor motion data from the Planner task.
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frPipePlannerTaskID(4),
  /**
   * <p>
   * ID constant for the pipe to monitor the PX task.
   * </p>
   * <p>
   * The value of this constant is 14
   * </p>
   */
  frPipePxTaskID(14),
  /**
   * <p>
   * ID constant for the pipe to monitor PG data from the interpreter for the TP profiler.
   * </p>
   * <p>
   * The value of this constant is 17
   * </p>
   */
  frPipePgDaqID(17),
  /**
   * <p>
   * ID constant for the pipe to monitor motion simulation data such as offline OVC value.
   * </p>
   * <p>
   * The value of this constant is 28
   * </p>
   */
  frPipeMOTNSimID(28),
  /**
   * <p>
   * ID constant for the pipe to monitor motion ITP data.
   * </p>
   * <p>
   * The value of this constant is 27
   * </p>
   */
  frPipeMFDataID(27),
  /**
   * <p>
   * ID constant for the pipe to monitor motion segment record data.
   * </p>
   * <p>
   * The value of this constant is 26
   * </p>
   */
  frPipeMISegID(26),
  ;

  private final int value;
  FREPipeIDConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
