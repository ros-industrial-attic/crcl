package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * The task object represents a running thread of execution on the controller.  A running task may be running different programs or program routines at various points in time.
 */
@IID("{7745C8FE-4626-11D1-B745-00C04FBBE42A}")
public interface ITask extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * <p>
   * Returns/sets the state of the busy lamp behavior.
   * </p>
   * <p>
   * Getter method for the COM property "BusyLampOff"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(8)
  boolean busyLampOff();


  /**
   * <p>
   * Returns/sets the state of the busy lamp behavior.
   * </p>
   * <p>
   * Setter method for the COM property "BusyLampOff"
   * </p>
   * @param busyLampOff Mandatory boolean parameter.
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(9)
  void busyLampOff(
    boolean busyLampOff);


  /**
   * <p>
   * Returns the current line of execution in the current program.  
   * </p>
   * <p>
   * Getter method for the COM property "CurLine"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(10)
  int curLine();


  /**
   * <p>
   * Returns the current program that is being executed.  
   * </p>
   * <p>
   * Getter method for the COM property "CurProgram"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IProgram
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(11)
  com.github.wshackle.fanuc.robotserver.IProgram curProgram();


  /**
   * <p>
   * Returns the name of the currently executing KAREL routine.
   * </p>
   * <p>
   * Getter method for the COM property "CurRoutine"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(12)
  java.lang.String curRoutine();


  /**
   * <p>
   * Returns the state of the various hold flags.
   * </p>
   * <p>
   * Getter method for the COM property "HoldCondition"
   * </p>
   * @param holdCondition Mandatory com.github.wshackle.fanuc.robotserver.FREHoldConditionConstants parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(205) //= 0xcd. The runtime will prefer the VTID if present
  @VTID(13)
  boolean holdCondition(
    com.github.wshackle.fanuc.robotserver.FREHoldConditionConstants holdCondition);


  /**
   * <p>
   * Returns/sets the ignore abort flags for the task.
   * </p>
   * <p>
   * Getter method for the COM property "IgnoreAbort"
   * </p>
   * @param ignoreConstant Mandatory com.github.wshackle.fanuc.robotserver.FRETaskIgnoreConstants parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(206) //= 0xce. The runtime will prefer the VTID if present
  @VTID(14)
  boolean ignoreAbort(
    com.github.wshackle.fanuc.robotserver.FRETaskIgnoreConstants ignoreConstant);


  /**
   * <p>
   * Returns/sets the ignore abort flags for the task.
   * </p>
   * <p>
   * Setter method for the COM property "IgnoreAbort"
   * </p>
   * @param ignoreConstant Mandatory com.github.wshackle.fanuc.robotserver.FRETaskIgnoreConstants parameter.
   * @param ignore Mandatory boolean parameter.
   */

  @DISPID(206) //= 0xce. The runtime will prefer the VTID if present
  @VTID(15)
  void ignoreAbort(
    com.github.wshackle.fanuc.robotserver.FRETaskIgnoreConstants ignoreConstant,
    boolean ignore);


  /**
   * <p>
   * Returns/sets the ignore pause flags for the task.
   * </p>
   * <p>
   * Getter method for the COM property "IgnorePause"
   * </p>
   * @param ignoreConstant Mandatory com.github.wshackle.fanuc.robotserver.FRETaskIgnoreConstants parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(207) //= 0xcf. The runtime will prefer the VTID if present
  @VTID(16)
  boolean ignorePause(
    com.github.wshackle.fanuc.robotserver.FRETaskIgnoreConstants ignoreConstant);


  /**
   * <p>
   * Returns/sets the ignore pause flags for the task.
   * </p>
   * <p>
   * Setter method for the COM property "IgnorePause"
   * </p>
   * @param ignoreConstant Mandatory com.github.wshackle.fanuc.robotserver.FRETaskIgnoreConstants parameter.
   * @param ignore Mandatory boolean parameter.
   */

  @DISPID(207) //= 0xcf. The runtime will prefer the VTID if present
  @VTID(17)
  void ignorePause(
    com.github.wshackle.fanuc.robotserver.FRETaskIgnoreConstants ignoreConstant,
    boolean ignore);


  /**
   * <p>
   * Returns whether the task shows in the task list.
   * </p>
   * <p>
   * Getter method for the COM property "Invisible"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(208) //= 0xd0. The runtime will prefer the VTID if present
  @VTID(18)
  boolean invisible();


  /**
   * <p>
   * Returns whether the task shows in the task list.
   * </p>
   * <p>
   * Setter method for the COM property "Invisible"
   * </p>
   * @param invisible Mandatory boolean parameter.
   */

  @DISPID(208) //= 0xd0. The runtime will prefer the VTID if present
  @VTID(19)
  void invisible(
    boolean invisible);


  /**
   * <p>
   * Returns/sets whether the task pauses on Shift release.
   * </p>
   * <p>
   * Getter method for the COM property "PauseOnShift"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(209) //= 0xd1. The runtime will prefer the VTID if present
  @VTID(20)
  boolean pauseOnShift();


  /**
   * <p>
   * Returns/sets whether the task pauses on Shift release.
   * </p>
   * <p>
   * Setter method for the COM property "PauseOnShift"
   * </p>
   * @param pauseOnShift Mandatory boolean parameter.
   */

  @DISPID(209) //= 0xd1. The runtime will prefer the VTID if present
  @VTID(21)
  void pauseOnShift(
    boolean pauseOnShift);


  /**
   * <p>
   * Returns whether the task is a system task.
   * </p>
   * <p>
   * Getter method for the COM property "SystemTask"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(210) //= 0xd2. The runtime will prefer the VTID if present
  @VTID(22)
  boolean systemTask();


  /**
   * <p>
   * Returns whether the task is a system task.
   * </p>
   * <p>
   * Setter method for the COM property "SystemTask"
   * </p>
   * @param systemTask Mandatory boolean parameter.
   */

  @DISPID(210) //= 0xd2. The runtime will prefer the VTID if present
  @VTID(23)
  void systemTask(
    boolean systemTask);


  /**
   * <p>
   * Returns/sets whether the task has motion when the TP is enabled.
   * </p>
   * <p>
   * Getter method for the COM property "TPMotion"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(211) //= 0xd3. The runtime will prefer the VTID if present
  @VTID(24)
  boolean tpMotion();


  /**
   * <p>
   * Returns/sets whether the task has motion when the TP is enabled.
   * </p>
   * <p>
   * Setter method for the COM property "TPMotion"
   * </p>
   * @param tpMotion Mandatory boolean parameter.
   */

  @DISPID(211) //= 0xd3. The runtime will prefer the VTID if present
  @VTID(25)
  void tpMotion(
    boolean tpMotion);


  /**
   * <p>
   * Returns/sets whether the task has tracing enabled.
   * </p>
   * <p>
   * Getter method for the COM property "TraceEnable"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(212) //= 0xd4. The runtime will prefer the VTID if present
  @VTID(26)
  boolean traceEnable();


  /**
   * <p>
   * Returns/sets whether the task has tracing enabled.
   * </p>
   * <p>
   * Setter method for the COM property "TraceEnable"
   * </p>
   * @param traceEnable Mandatory boolean parameter.
   */

  @DISPID(212) //= 0xd4. The runtime will prefer the VTID if present
  @VTID(27)
  void traceEnable(
    boolean traceEnable);


  /**
   * <p>
   * Returns/sets whether the operator can write program variables even if the task is running.
   * </p>
   * <p>
   * Getter method for the COM property "VarWriteEnable"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(213) //= 0xd5. The runtime will prefer the VTID if present
  @VTID(28)
  boolean varWriteEnable();


  /**
   * <p>
   * Returns/sets whether the operator can write program variables even if the task is running.
   * </p>
   * <p>
   * Setter method for the COM property "VarWriteEnable"
   * </p>
   * @param varWriteEnable Mandatory boolean parameter.
   */

  @DISPID(213) //= 0xd5. The runtime will prefer the VTID if present
  @VTID(29)
  void varWriteEnable(
    boolean varWriteEnable);


  /**
   * <p>
   * Returns whether the task has any motion groups locked.
   * </p>
   * <p>
   * Getter method for the COM property "LockedArm"
   * </p>
   * @param groupNum Mandatory short parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(214) //= 0xd6. The runtime will prefer the VTID if present
  @VTID(30)
  boolean lockedArm(
    short groupNum);


  /**
   * <p>
   * Returns whether the task has motion control of a group.
   * </p>
   * <p>
   * Getter method for the COM property "MotionControl"
   * </p>
   * @param groupNum Mandatory short parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(215) //= 0xd7. The runtime will prefer the VTID if present
  @VTID(31)
  boolean motionControl(
    short groupNum);


  /**
   * <p>
   * Returns/sets the name associated with the task.
   * </p>
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(216) //= 0xd8. The runtime will prefer the VTID if present
  @VTID(32)
  java.lang.String name();


  /**
   * <p>
   * Returns/sets the name associated with the task.
   * </p>
   * <p>
   * Setter method for the COM property "Name"
   * </p>
   * @param name Mandatory java.lang.String parameter.
   */

  @DISPID(216) //= 0xd8. The runtime will prefer the VTID if present
  @VTID(33)
  void name(
    java.lang.String name);


  /**
   * <p>
   * Returns whether the task performs circular motion.
   * </p>
   * <p>
   * Getter method for the COM property "NotCircularMotion"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(217) //= 0xd9. The runtime will prefer the VTID if present
  @VTID(34)
  boolean notCircularMotion();


  /**
   * <p>
   * Returns whether the task performs circular motion.
   * </p>
   * <p>
   * Setter method for the COM property "NotCircularMotion"
   * </p>
   * @param notCircularMotion Mandatory boolean parameter.
   */

  @DISPID(217) //= 0xd9. The runtime will prefer the VTID if present
  @VTID(35)
  void notCircularMotion(
    boolean notCircularMotion);


  /**
   * <p>
   * Returns the number of child tasks created by this task.
   * </p>
   * <p>
   * Getter method for the COM property "NumChild"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(218) //= 0xda. The runtime will prefer the VTID if present
  @VTID(36)
  short numChild();


  /**
   * <p>
   * Returns the number of MMRs associated with the task.
   * </p>
   * <p>
   * Getter method for the COM property "NumMMR"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(219) //= 0xdb. The runtime will prefer the VTID if present
  @VTID(37)
  short numMMR();


  /**
   * <p>
   * Returns/sets the current priority of the task.
   * </p>
   * <p>
   * Getter method for the COM property "Priority"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(220) //= 0xdc. The runtime will prefer the VTID if present
  @VTID(38)
  short priority();


  /**
   * <p>
   * Returns/sets the current priority of the task.
   * </p>
   * <p>
   * Setter method for the COM property "Priority"
   * </p>
   * @param priority Mandatory short parameter.
   */

  @DISPID(220) //= 0xdc. The runtime will prefer the VTID if present
  @VTID(39)
  void priority(
    short priority);


  /**
   * <p>
   * Returns the type of program the task represents
   * </p>
   * <p>
   * Getter method for the COM property "ProgramType"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FREProgramTypeConstants
   */

  @DISPID(221) //= 0xdd. The runtime will prefer the VTID if present
  @VTID(40)
  com.github.wshackle.fanuc.robotserver.FREProgramTypeConstants programType();


  /**
   * <p>
   * Returns the stack size for the task.
   * </p>
   * <p>
   * Getter method for the COM property "StackSize"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(222) //= 0xde. The runtime will prefer the VTID if present
  @VTID(41)
  int stackSize();


  /**
   * <p>
   * Returns the current status of the task.
   * </p>
   * <p>
   * Getter method for the COM property "Status"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FRETaskStatusConstants
   */

  @DISPID(223) //= 0xdf. The runtime will prefer the VTID if present
  @VTID(42)
  com.github.wshackle.fanuc.robotserver.FRETaskStatusConstants status();


  /**
   * <p>
   * Returns the task statement stepping type.
   * </p>
   * <p>
   * Getter method for the COM property "StepType"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FREStepTypeConstants
   */

  @DISPID(224) //= 0xe0. The runtime will prefer the VTID if present
  @VTID(43)
  com.github.wshackle.fanuc.robotserver.FREStepTypeConstants stepType();


  /**
   * <p>
   * Returns the task statement stepping type.
   * </p>
   * <p>
   * Setter method for the COM property "StepType"
   * </p>
   * @param stepType Mandatory com.github.wshackle.fanuc.robotserver.FREStepTypeConstants parameter.
   */

  @DISPID(224) //= 0xe0. The runtime will prefer the VTID if present
  @VTID(44)
  void stepType(
    com.github.wshackle.fanuc.robotserver.FREStepTypeConstants stepType);


  /**
   * <p>
   * Returns/sets whether the task has supervisory motion control of any group.
   * </p>
   * <p>
   * Getter method for the COM property "SuperMotion"
   * </p>
   * @param groupNum Mandatory short parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(225) //= 0xe1. The runtime will prefer the VTID if present
  @VTID(45)
  boolean superMotion(
    short groupNum);


  /**
   * <p>
   * Returns/sets whether the task has supervisory motion control of any group.
   * </p>
   * <p>
   * Setter method for the COM property "SuperMotion"
   * </p>
   * @param groupNum Mandatory short parameter.
   * @param superMotion Mandatory boolean parameter.
   */

  @DISPID(225) //= 0xe1. The runtime will prefer the VTID if present
  @VTID(46)
  void superMotion(
    short groupNum,
    boolean superMotion);


  /**
   * <p>
   * Returns the number of the task.
   * </p>
   * <p>
   * Getter method for the COM property "TaskNum"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(226) //= 0xe2. The runtime will prefer the VTID if present
  @VTID(47)
  short taskNum();


  /**
   * <p>
   * Returns the current status of the task TCDs.
   * </p>
   * <p>
   * Getter method for the COM property "TCDStatus"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(227) //= 0xe3. The runtime will prefer the VTID if present
  @VTID(48)
  int tcdStatus();


  /**
   * <p>
   * Returns the allotted time slice for the task.
   * </p>
   * <p>
   * Getter method for the COM property "TimeSlice"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(228) //= 0xe4. The runtime will prefer the VTID if present
  @VTID(49)
  int timeSlice();


  /**
   * <p>
   * Returns/sets the length of the trace buffer for this task.
   * </p>
   * <p>
   * Getter method for the COM property "TraceLength"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(229) //= 0xe5. The runtime will prefer the VTID if present
  @VTID(50)
  int traceLength();


  /**
   * <p>
   * Returns/sets the length of the trace buffer for this task.
   * </p>
   * <p>
   * Setter method for the COM property "TraceLength"
   * </p>
   * @param traceLength Mandatory int parameter.
   */

  @DISPID(229) //= 0xe5. The runtime will prefer the VTID if present
  @VTID(51)
  void traceLength(
    int traceLength);


  /**
   * <p>
   * Returns the top level program object.  
   * </p>
   * <p>
   * Getter method for the COM property "TopProgram"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IProgram
   */

  @DISPID(230) //= 0xe6. The runtime will prefer the VTID if present
  @VTID(52)
  com.github.wshackle.fanuc.robotserver.IProgram topProgram();


  /**
   * <p>
   * Aborts the current execution of the task.  
   * </p>
   * @param force Optional parameter. Default value is com4j.Variant.getMissing()
   * @param cancel Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(250) //= 0xfa. The runtime will prefer the VTID if present
  @VTID(53)
  void abort(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object force,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object cancel);


  /**
   * <p>
   * Pauses the task at its current line.  
   * </p>
   * @param force Optional parameter. Default value is com4j.Variant.getMissing()
   * @param cancel Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(251) //= 0xfb. The runtime will prefer the VTID if present
  @VTID(54)
  void pause(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object force,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object cancel);


  /**
   * <p>
   * Executes a task after it has been stopped by the Pause method.
   * </p>
   * @param lineNum Optional parameter. Default value is com4j.Variant.getMissing()
   * @param direction Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(252) //= 0xfc. The runtime will prefer the VTID if present
  @VTID(55)
  void _continue(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object lineNum,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object direction);


  /**
   * <p>
   * Causes one or more statements to be skipped.  The next statement to be executed is determined by the current value of the StepType property.
   * </p>
   * @param number Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(253) //= 0xfd. The runtime will prefer the VTID if present
  @VTID(56)
  void skip(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object number);


  // Properties:
}
