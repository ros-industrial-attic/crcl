package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Represents a program on the robot controller. 
 */
@IID("{8C8ACC99-4F57-11D0-BC32-444553540000}")
public interface IProgram extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * <p>
   * Returns the program name for the object.
   * </p>
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(8)
  @DefaultMethod
  java.lang.String name();


  /**
   * <p>
   * Returns a reference to the top level of the variables defined for the program.
   * </p>
   * <p>
   * Getter method for the COM property "Variables"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IVars
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(9)
  com.github.wshackle.fanuc.robotserver.IVars variables();


  /**
   * <p>
   * Returns the current access mode for the program.
   * </p>
   * <p>
   * Getter method for the COM property "AccessMode"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FREAccessModeConstants
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(10)
  com.github.wshackle.fanuc.robotserver.FREAccessModeConstants accessMode();


  /**
   * <p>
   * Returns the current reject mode for the program.
   * </p>
   * <p>
   * Getter method for the COM property "RejectMode"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FRERejectModeConstants
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(11)
  com.github.wshackle.fanuc.robotserver.FRERejectModeConstants rejectMode();


  /**
   * <p>
   * Returns the parent FRCPrograms collection.
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IPrograms
   */

  @DISPID(104) //= 0x68. The runtime will prefer the VTID if present
  @VTID(12)
  com.github.wshackle.fanuc.robotserver.IPrograms parent();


  /**
   * <p>
   * Returns/sets the comment associated with the program.
   * </p>
   * <p>
   * Getter method for the COM property "Comment"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(105) //= 0x69. The runtime will prefer the VTID if present
  @VTID(13)
  java.lang.String comment();


  /**
   * <p>
   * Returns/sets the comment associated with the program.
   * </p>
   * <p>
   * Setter method for the COM property "Comment"
   * </p>
   * @param comment Mandatory java.lang.String parameter.
   */

  @DISPID(105) //= 0x69. The runtime will prefer the VTID if present
  @VTID(14)
  void comment(
    java.lang.String comment);


  /**
   * <p>
   * Returns the date the program was created as a Variant. 
   * </p>
   * <p>
   * Getter method for the COM property "Created"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(106) //= 0x6a. The runtime will prefer the VTID if present
  @VTID(15)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object created();


  /**
   * <p>
   * Returns the date the program was last modified as a Variant. 
   * </p>
   * <p>
   * Getter method for the COM property "LastModified"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(107) //= 0x6b. The runtime will prefer the VTID if present
  @VTID(16)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object lastModified();


  /**
   * <p>
   * Returns the original name of the program.
   * </p>
   * <p>
   * Getter method for the COM property "OriginalName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(108) //= 0x6c. The runtime will prefer the VTID if present
  @VTID(17)
  java.lang.String originalName();


  /**
   * <p>
   * Returns the version of the program.
   * </p>
   * <p>
   * Getter method for the COM property "Version"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(109) //= 0x6d. The runtime will prefer the VTID if present
  @VTID(18)
  short version();


  /**
   * <p>
   * Returns the size of the program in memory.
   * </p>
   * <p>
   * Getter method for the COM property "Size"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(110) //= 0x6e. The runtime will prefer the VTID if present
  @VTID(19)
  int size();


  /**
   * <p>
   * Returns/sets the current level of protection for the program.
   * </p>
   * <p>
   * Getter method for the COM property "Protection"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FREProtectionConstants
   */

  @DISPID(111) //= 0x6f. The runtime will prefer the VTID if present
  @VTID(20)
  com.github.wshackle.fanuc.robotserver.FREProtectionConstants protection();


  /**
   * <p>
   * Returns/sets the current level of protection for the program.
   * </p>
   * <p>
   * Setter method for the COM property "Protection"
   * </p>
   * @param protection Mandatory com.github.wshackle.fanuc.robotserver.FREProtectionConstants parameter.
   */

  @DISPID(111) //= 0x6f. The runtime will prefer the VTID if present
  @VTID(21)
  void protection(
    com.github.wshackle.fanuc.robotserver.FREProtectionConstants protection);


  /**
   * <p>
   * Returns/sets the stack size for the program.
   * </p>
   * <p>
   * Getter method for the COM property "StackSize"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(112) //= 0x70. The runtime will prefer the VTID if present
  @VTID(22)
  int stackSize();


  /**
   * <p>
   * Returns/sets the stack size for the program.
   * </p>
   * <p>
   * Setter method for the COM property "StackSize"
   * </p>
   * @param stackSize Mandatory int parameter.
   */

  @DISPID(112) //= 0x70. The runtime will prefer the VTID if present
  @VTID(23)
  void stackSize(
    int stackSize);


  /**
   * <p>
   * Returns/sets the current priority of the program.
   * </p>
   * <p>
   * Getter method for the COM property "Priority"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(113) //= 0x71. The runtime will prefer the VTID if present
  @VTID(24)
  int priority();


  /**
   * <p>
   * Returns/sets the current priority of the program.
   * </p>
   * <p>
   * Setter method for the COM property "Priority"
   * </p>
   * @param priority Mandatory int parameter.
   */

  @DISPID(113) //= 0x71. The runtime will prefer the VTID if present
  @VTID(25)
  void priority(
    int priority);


  /**
   * <p>
   * Returns the required time slice for the program.
   * </p>
   * <p>
   * Getter method for the COM property "TimeSlice"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(114) //= 0x72. The runtime will prefer the VTID if present
  @VTID(26)
  int timeSlice();


  /**
   * <p>
   * Returns the required time slice for the program.
   * </p>
   * <p>
   * Setter method for the COM property "TimeSlice"
   * </p>
   * @param timeSlice Mandatory int parameter.
   */

  @DISPID(114) //= 0x72. The runtime will prefer the VTID if present
  @VTID(27)
  void timeSlice(
    int timeSlice);


  /**
   * <p>
   * Returns/sets the state of the busy lamp during program execution.  True if the busy lamp is off during execution.
   * </p>
   * <p>
   * Getter method for the COM property "BusyLampOff"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(115) //= 0x73. The runtime will prefer the VTID if present
  @VTID(28)
  boolean busyLampOff();


  /**
   * <p>
   * Returns/sets the state of the busy lamp during program execution.  True if the busy lamp is off during execution.
   * </p>
   * <p>
   * Setter method for the COM property "BusyLampOff"
   * </p>
   * @param busyLampOff Mandatory boolean parameter.
   */

  @DISPID(115) //= 0x73. The runtime will prefer the VTID if present
  @VTID(29)
  void busyLampOff(
    boolean busyLampOff);


  /**
   * <p>
   * Returns/sets the group numbers for the program.  True if the motion group is used.
   * </p>
   * <p>
   * Getter method for the COM property "DefaultGroup"
   * </p>
   * @param groupNumber Mandatory short parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(116) //= 0x74. The runtime will prefer the VTID if present
  @VTID(30)
  boolean defaultGroup(
    short groupNumber);


  /**
   * <p>
   * Returns/sets the group numbers for the program.  True if the motion group is used.
   * </p>
   * <p>
   * Setter method for the COM property "DefaultGroup"
   * </p>
   * @param groupNumber Mandatory short parameter.
   * @param defaultGroup Mandatory boolean parameter.
   */

  @DISPID(116) //= 0x74. The runtime will prefer the VTID if present
  @VTID(31)
  void defaultGroup(
    short groupNumber,
    boolean defaultGroup);


  /**
   * <p>
   * Returns/sets the state of the IgnoreAbort attribute.
   * </p>
   * <p>
   * Getter method for the COM property "IgnoreAbort"
   * </p>
   * @param ignoreConstant Mandatory com.github.wshackle.fanuc.robotserver.FRETaskIgnoreConstants parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(117) //= 0x75. The runtime will prefer the VTID if present
  @VTID(32)
  boolean ignoreAbort(
    com.github.wshackle.fanuc.robotserver.FRETaskIgnoreConstants ignoreConstant);


  /**
   * <p>
   * Returns/sets the state of the IgnoreAbort attribute.
   * </p>
   * <p>
   * Setter method for the COM property "IgnoreAbort"
   * </p>
   * @param ignoreConstant Mandatory com.github.wshackle.fanuc.robotserver.FRETaskIgnoreConstants parameter.
   * @param ignoreAbort Mandatory boolean parameter.
   */

  @DISPID(117) //= 0x75. The runtime will prefer the VTID if present
  @VTID(33)
  void ignoreAbort(
    com.github.wshackle.fanuc.robotserver.FRETaskIgnoreConstants ignoreConstant,
    boolean ignoreAbort);


  /**
   * <p>
   * Returns/sets the state of the IgnorePause attribute. 
   * </p>
   * <p>
   * Getter method for the COM property "IgnorePause"
   * </p>
   * @param ignoreConstant Mandatory com.github.wshackle.fanuc.robotserver.FRETaskIgnoreConstants parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(118) //= 0x76. The runtime will prefer the VTID if present
  @VTID(34)
  boolean ignorePause(
    com.github.wshackle.fanuc.robotserver.FRETaskIgnoreConstants ignoreConstant);


  /**
   * <p>
   * Returns/sets the state of the IgnorePause attribute. 
   * </p>
   * <p>
   * Setter method for the COM property "IgnorePause"
   * </p>
   * @param ignoreConstant Mandatory com.github.wshackle.fanuc.robotserver.FRETaskIgnoreConstants parameter.
   * @param ignorePause Mandatory boolean parameter.
   */

  @DISPID(118) //= 0x76. The runtime will prefer the VTID if present
  @VTID(35)
  void ignorePause(
    com.github.wshackle.fanuc.robotserver.FRETaskIgnoreConstants ignoreConstant,
    boolean ignorePause);


  /**
   * <p>
   * Returns/sets the state of the invisible attribute.  True will make the program invisible.
   * </p>
   * <p>
   * Getter method for the COM property "Invisible"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(119) //= 0x77. The runtime will prefer the VTID if present
  @VTID(36)
  boolean invisible();


  /**
   * <p>
   * Returns/sets the state of the invisible attribute.  True will make the program invisible.
   * </p>
   * <p>
   * Setter method for the COM property "Invisible"
   * </p>
   * @param invisible Mandatory boolean parameter.
   */

  @DISPID(119) //= 0x77. The runtime will prefer the VTID if present
  @VTID(37)
  void invisible(
    boolean invisible);


  /**
   * <p>
   * Returns/sets the owner name of the program.
   * </p>
   * <p>
   * Getter method for the COM property "Owner"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(120) //= 0x78. The runtime will prefer the VTID if present
  @VTID(38)
  java.lang.String owner();


  /**
   * <p>
   * Returns/sets the owner name of the program.
   * </p>
   * <p>
   * Setter method for the COM property "Owner"
   * </p>
   * @param owner Mandatory java.lang.String parameter.
   */

  @DISPID(120) //= 0x78. The runtime will prefer the VTID if present
  @VTID(39)
  void owner(
    java.lang.String owner);


  /**
   * <p>
   * Reopens the program with different access or reject mode attributes.
   * </p>
   * @param accessMode Mandatory com.github.wshackle.fanuc.robotserver.FREAccessModeConstants parameter.
   * @param rejectMode Mandatory com.github.wshackle.fanuc.robotserver.FRERejectModeConstants parameter.
   */

  @DISPID(150) //= 0x96. The runtime will prefer the VTID if present
  @VTID(40)
  void reOpen(
    com.github.wshackle.fanuc.robotserver.FREAccessModeConstants accessMode,
    com.github.wshackle.fanuc.robotserver.FRERejectModeConstants rejectMode);


  /**
   * <p>
   * Saves the TP program and/or the variables associated with the program from memory to the specified file device.  KAREL program executable statements can not be saved using this mechanism.
   * </p>
   * @param fileName Mandatory java.lang.String parameter.
   * @param option Optional parameter. Default value is com4j.Variant.getMissing()
   * @param saveClass Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(151) //= 0x97. The runtime will prefer the VTID if present
  @VTID(41)
  void save(
    java.lang.String fileName,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object option,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object saveClass);


  // Properties:
}
