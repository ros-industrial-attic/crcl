package com.github.wshackle.fanuc.robotneighborhood  ;

import com4j.*;

/**
 */
public enum FRERNErrorConstants implements ComEnum {
  /**
   * <p>
   * The Windows clipboard does not contain a Neighborhood entry.
   * </p>
   * <p>
   * The value of this constant is -2147209135
   * </p>
   */
  frRNTheClipboardIsEmpty(-2147209135),
  /**
   * <p>
   * The specified item is not a child of this object.
   * </p>
   * <p>
   * The value of this constant is -2147209134
   * </p>
   */
  frRNItemIsNotAChildOfThisObject(-2147209134),
  /**
   * <p>
   * The parameter supplied is not within the specified range of valid values.
   * </p>
   * <p>
   * The value of this constant is -2147209133
   * </p>
   */
  frRNParameterIsOutOfRange(-2147209133),
  /**
   * <p>
   * The requested item was not found.
   * </p>
   * <p>
   * The value of this constant is -2147209132
   * </p>
   */
  frRNItemNotFound(-2147209132),
  /**
   * <p>
   * The index supplied is invalid.
   * </p>
   * <p>
   * The value of this constant is -2147209131
   * </p>
   */
  frRNIndexIsInvalid(-2147209131),
  /**
   * <p>
   * This object has no parent.
   * </p>
   * <p>
   * The value of this constant is -2147209130
   * </p>
   */
  frRNHasNoParent(-2147209130),
  /**
   * <p>
   * The name already exists.
   * </p>
   * <p>
   * The value of this constant is -2147209129
   * </p>
   */
  frRNNameAlreadyExists(-2147209129),
  /**
   * <p>
   * The Name supplied is Invalid..
   * </p>
   * <p>
   * The value of this constant is -2147209128
   * </p>
   */
  frRNNameIsInvalid(-2147209128),
  /**
   * <p>
   * The Virtual location supplied is invalid.
   * </p>
   * <p>
   * The value of this constant is -2147209127
   * </p>
   */
  frRNVirtualLocationIsInvalid(-2147209127),
  /**
   * <p>
   * The HostName supplied is invalid..
   * </p>
   * <p>
   * The value of this constant is -2147209126
   * </p>
   */
  frRNHostNameIsInvalid(-2147209126),
  /**
   * <p>
   * The specified item is in use.
   * </p>
   * <p>
   * The value of this constant is -2147209125
   * </p>
   */
  frRNItemIsInUse(-2147209125),
  /**
   * <p>
   * The run-time license is invalid.
   * </p>
   * <p>
   * The value of this constant is -2147209122
   * </p>
   */
  frRNLicenseIsInvalid(-2147209122),
  /**
   * <p>
   * Two or more requests for the Robot Server or Start requests for the Virtual Robot were made simultaneously. This one lost. Try again.
   * </p>
   * <p>
   * The value of this constant is -2147209121
   * </p>
   */
  frRNConcurrentRequests(-2147209121),
  /**
   * <p>
   * Unexpected system error.
   * </p>
   * <p>
   * The value of this constant is -2147209120
   * </p>
   */
  frRNSystemError(-2147209120),
  /**
   * <p>
   * The XML data can not be decoded by RobotNeighborhood.
   * </p>
   * <p>
   * The value of this constant is -2147209119
   * </p>
   */
  frRNUnknownXmlStructure(-2147209119),
  /**
   * <p>
   * This version of the virtual controller is not supported.
   * </p>
   * <p>
   * The value of this constant is -2147209118
   * </p>
   */
  frRNVirtualVersionCorrupt(-2147209118),
  /**
   * <p>
   * The virtual controller has not been started.
   * </p>
   * <p>
   * The value of this constant is -2147209117
   * </p>
   */
  frRNVirtualControllerNotStarted(-2147209117),
  /**
   * <p>
   * The virtual controller cannot be requested to start in this mode.
   * </p>
   * <p>
   * The value of this constant is -2147209116
   * </p>
   */
  frRNRequestedStartModeNotSupported(-2147209116),
  /**
   * <p>
   * The requested start mode is not compatible with the current start mode of the virtual controller.
   * </p>
   * <p>
   * The value of this constant is -2147209115
   * </p>
   */
  frRNIncompatibleStartModeRequested(-2147209115),
  /**
   * <p>
   * The virtual controller could not be started.
   * </p>
   * <p>
   * The value of this constant is -2147209114
   * </p>
   */
  frRNVirtualStartRequestFailed(-2147209114),
  /**
   * <p>
   * Access to the Windows Registry was denied.
   * </p>
   * <p>
   * The value of this constant is -2147209113
   * </p>
   */
  frRNRegistryAccessDenied(-2147209113),
  /**
   * <p>
   * The BackupPath is invalid and cannot be used.
   * </p>
   * <p>
   * The value of this constant is -2147209112
   * </p>
   */
  frRNVirtualBackupPathIsInvalid(-2147209112),
  /**
   * <p>
   * The virtual controller has been started.
   * </p>
   * <p>
   * The value of this constant is -2147209111
   * </p>
   */
  frRNVirtualControllerIsStarted(-2147209111),
  /**
   * <p>
   * The backup for this virtual controller cannot be found.
   * </p>
   * <p>
   * The value of this constant is -2147209110
   * </p>
   */
  frRNVirtualBackupCannotBeFound(-2147209110),
  /**
   * <p>
   * The virtual controller was not able to execute an RN request.
   * </p>
   * <p>
   * The value of this constant is -2147209109
   * </p>
   */
  frRNUnexpectedVirtualControllerError(-2147209109),
  /**
   * <p>
   * The Virtual location folder is currently in use.
   * </p>
   * <p>
   * The value of this constant is -2147209108
   * </p>
   */
  frRNVirtualLocationIsInUse(-2147209108),
  /**
   * <p>
   * The Virtual Controller encountered a fatal error during recovery.
   * </p>
   * <p>
   * The value of this constant is -2147209107
   * </p>
   */
  frRNVirtualFatalErrorDuringRecovery(-2147209107),
  /**
   * <p>
   * An enumeration of RN robot types.
   * </p>
   * <p>
   * The value of this constant is -2147209106
   * </p>
   */
  frRNVirtualVersionNotSupported(-2147209106),
  /**
   * <p>
   * The revision of this virtual robot does not match the installed virtual controller.
   * </p>
   * <p>
   * The value of this constant is -2147209105
   * </p>
   */
  frRNVirtualRevisionMismatch(-2147209105),
  /**
   * <p>
   * An IP address for this host name cannot be found.
   * </p>
   * <p>
   * The value of this constant is -2147209104
   * </p>
   */
  frRNNoIPAddress(-2147209104),
  /**
   * <p>
   * The network was not able complete the RDM operation.
   * </p>
   * <p>
   * The value of this constant is -2147209103
   * </p>
   */
  frRNRDMNetworkError(-2147209103),
  /**
   * <p>
   * The RDM response packet provided by this robot is corrupted.
   * </p>
   * <p>
   * The value of this constant is -2147209103
   * </p>
   */
  frRNRDMResponsePacketIsCorrupt(-2147209103),
  ;

  private final int value;
  FRERNErrorConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
