package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * <p>
 * Constants for Robot errors
 * </p>
 */
public enum FRERobotErrors implements ComEnum {
  /**
   * <p>
   * Robot object not yet connected.
   * </p>
   * <p>
   * The value of this constant is -2147221504
   * </p>
   */
  frRobotNotConnected(-2147221504),
  /**
   * <p>
   * The PMON Server thread failed to start.
   * </p>
   * <p>
   * The value of this constant is -2147221500
   * </p>
   */
  frCantStartPMON(-2147221500),
  /**
   * <p>
   * Robot object is already connected.
   * </p>
   * <p>
   * The value of this constant is -2147221499
   * </p>
   */
  frRobotAlreadyConnected(-2147221499),
  /**
   * <p>
   * Connection to the controller was refused.
   * </p>
   * <p>
   * The value of this constant is -2147221498
   * </p>
   */
  frConnectionRefused(-2147221498),
  /**
   * <p>
   * Can't connect to controller.  Error received was X.
   * </p>
   * <p>
   * The value of this constant is -2147221497
   * </p>
   */
  frCantConnect(-2147221497),
  /**
   * <p>
   * Can't connect to localhost.  Offline RPC server is already running.  Only one offline connection is allowed at a time.
   * </p>
   * <p>
   * The value of this constant is -2147221496
   * </p>
   */
  frCantConnectOfflineRunning(-2147221496),
  /**
   * <p>
   * Can't connect to localhost.    Unable to start offline RPC server.
   * </p>
   * <p>
   * The value of this constant is -2147221495
   * </p>
   */
  frCantStartOfflineServer(-2147221495),
  /**
   * <p>
   * Can't connect to localhost.  Failed to complete startup of offline RPC server .
   * </p>
   * <p>
   * The value of this constant is -2147221494
   * </p>
   */
  frFailedStartupOfOfflineServer(-2147221494),
  /**
   * <p>
   * Can't connect to localhost.  Timeout waiting for offline RPC server startup to complete.
   * </p>
   * <p>
   * The value of this constant is -2147221493
   * </p>
   */
  frTimeoutStartupOfOfflineServer(-2147221493),
  /**
   * <p>
   * Can't access this property or method when connected as an Event Logger.
   * </p>
   * <p>
   * The value of this constant is -2147221492
   * </p>
   */
  frNotAvailableAsEventLogger(-2147221492),
  /**
   * <p>
   * Invalid language selection
   * </p>
   * <p>
   * The value of this constant is -2147221491
   * </p>
   */
  frInvLanguage(-2147221491),
  /**
   * <p>
   * Connection refused because the PCIF option is not loaded.
   * </p>
   * <p>
   * The value of this constant is -2147221489
   * </p>
   */
  frPCIFNotLoaded(-2147221489),
  /**
   * <p>
   * Object is no longer valid.
   * </p>
   * <p>
   * The value of this constant is -2147221490
   * </p>
   */
  frObjectNotValid(-2147221490),
  /**
   * <p>
   * Object variable not set.
   * </p>
   * <p>
   * The value of this constant is -2147221489
   * </p>
   */
  frObjectNotSet(-2147221489),
  /**
   * <p>
   * Robot controller error.
   * </p>
   * <p>
   * The value of this constant is -2147221484
   * </p>
   */
  frRobotError(-2147221484),
  /**
   * <p>
   * NumRetries parameter of the ConnectEx method is out of range.
   * </p>
   * <p>
   * The value of this constant is -2147221483
   * </p>
   */
  frNumRetriesOOR(-2147221483),
  /**
   * <p>
   * Period parameter of the ConnectEx method is out of range.
   * </p>
   * <p>
   * The value of this constant is -2147221482
   * </p>
   */
  frPeriodOOR(-2147221482),
  /**
   * <p>
   * Robot Object in in the process of connecting. Please wait.
   * </p>
   * <p>
   * The value of this constant is -2147221481
   * </p>
   */
  frRobotConnecting(-2147221481),
  /**
   * <p>
   * All Robot Object connection attempts failed.
   * </p>
   * <p>
   * The value of this constant is -2147221480
   * </p>
   */
  frRobotConnectionFailed(-2147221480),
  /**
   * <p>
   * The object passed in does not belong to the current Robot Server.
   * </p>
   * <p>
   * The value of this constant is -2147221479
   * </p>
   */
  frExternalObject(-2147221479),
  /**
   * <p>
   * The Robot Server run-time license is not valid.
   * </p>
   * <p>
   * The value of this constant is -2147221478
   * </p>
   */
  frLicenseNotValid(-2147221478),
  /**
   * <p>
   * Program is not currently loaded.
   * </p>
   * <p>
   * The value of this constant is -2147221404
   * </p>
   */
  frProgramNotLoaded(-2147221404),
  /**
   * <p>
   * Program PROGNAME is of an unsupported type.
   * </p>
   * <p>
   * The value of this constant is -2147221403
   * </p>
   */
  frProgTypeNotSupported(-2147221403),
  /**
   * <p>
   * Invalid programs collection index.
   * </p>
   * <p>
   * The value of this constant is -2147221402
   * </p>
   */
  frInvProgIndex(-2147221402),
  /**
   * <p>
   * Program must be opened with NewReference = TRUE.
   * </p>
   * <p>
   * The value of this constant is -2147221401
   * </p>
   */
  frProgramReOpenNotAllowed(-2147221401),
  /**
   * <p>
   * Re-open the program with new attributes failed.
   * </p>
   * <p>
   * The value of this constant is -2147221400
   * </p>
   */
  frProgramReOpenFailed(-2147221400),
  /**
   * <p>
   * Invalid collection index.
   * </p>
   * <p>
   * The value of this constant is -2147221399
   * </p>
   */
  frInvIndex(-2147221399),
  /**
   * <p>
   * The object already exists in the collection.
   * </p>
   * <p>
   * The value of this constant is -2147221398
   * </p>
   */
  frObjAlreadyExists(-2147221398),
  /**
   * <p>
   * The supplied key was not found in the collection.
   * </p>
   * <p>
   * The value of this constant is -2147221397
   * </p>
   */
  frKeyNotFound(-2147221397),
  /**
   * <p>
   * AccessMode and RejectMode parameters are not valid when NewReference is false.
   * </p>
   * <p>
   * The value of this constant is -2147221396
   * </p>
   */
  frNotNewRef(-2147221396),
  /**
   * <p>
   * Invalid TP program line number.
   * </p>
   * <p>
   * The value of this constant is -2147221304
   * </p>
   */
  frInvalidLineNum(-2147221304),
  /**
   * <p>
   * Invalid TP program mnemoninic line code.
   * </p>
   * <p>
   * The value of this constant is -2147221303
   * </p>
   */
  frInvalidMnCode(-2147221303),
  /**
   * <p>
   * The TP line object is not part of the TP lines collection.
   * </p>
   * <p>
   * The value of this constant is -2147221302
   * </p>
   */
  frInvalidLineObj(-2147221302),
  /**
   * <p>
   * Line object  doesn't support an XXXXX property.
   * </p>
   * <p>
   * The value of this constant is -2147221204
   * </p>
   */
  frLineNoSupportBinary(-2147221204),
  /**
   * <p>
   * Get of property X of line object Y returned error Z.
   * </p>
   * <p>
   * The value of this constant is -2147221203
   * </p>
   */
  frLineErrFromBinary(-2147221203),
  /**
   * <p>
   * Line object X doesn't support a Y property.
   * </p>
   * <p>
   * The value of this constant is -2147221202
   * </p>
   */
  frLineNoSupportTPLineHelper(-2147221202),
  /**
   * <p>
   * Can't create TP line object X
   * </p>
   * <p>
   * The value of this constant is -2147221201
   * </p>
   */
  frLineCantCreate(-2147221201),
  /**
   * <p>
   * Line has been deleted from the program.
   * </p>
   * <p>
   * The value of this constant is -2147221200
   * </p>
   */
  frLineDeletedFromProg(-2147221200),
  /**
   * <p>
   * The date or time is invalid.
   * </p>
   * <p>
   * The value of this constant is -2147221104
   * </p>
   */
  frInvalidDateTime(-2147221104),
  /**
   * <p>
   * Invalid program object parameter.
   * </p>
   * <p>
   * The value of this constant is -2147221103
   * </p>
   */
  frProgInvalidParam(-2147221103),
  /**
   * <p>
   * The program object parameter is out of range.
   * </p>
   * <p>
   * The value of this constant is -2147221102
   * </p>
   */
  frProgParamRange(-2147221102),
  /**
   * <p>
   * Invalid variable name.
   * </p>
   * <p>
   * The value of this constant is -2147221004
   * </p>
   */
  frInvVarName(-2147221004),
  /**
   * <p>
   * Invalid variable index.
   * </p>
   * <p>
   * The value of this constant is -2147221003
   * </p>
   */
  frInvVarIndex(-2147221003),
  /**
   * <p>
   * Variable is not of String type.
   * </p>
   * <p>
   * The value of this constant is -2147221002
   * </p>
   */
  frNotAStringVar(-2147221002),
  /**
   * <p>
   * String value is too long.
   * </p>
   * <p>
   * The value of this constant is -2147221001
   * </p>
   */
  frStrValueTooLong(-2147221001),
  /**
   * <p>
   * Data is uninitialized.
   * </p>
   * <p>
   * The value of this constant is -2147221000
   * </p>
   */
  frUninit(-2147221000),
  /**
   * <p>
   * No read access to data.
   * </p>
   * <p>
   * The value of this constant is -2147220999
   * </p>
   */
  frNoAccessError(-2147220999),
  /**
   * <p>
   * No write access to data.
   * </p>
   * <p>
   * The value of this constant is -2147220998
   * </p>
   */
  frNoWriteAccessError(-2147220998),
  /**
   * <p>
   * Not under NoUpdate control.
   * </p>
   * <p>
   * The value of this constant is -2147220997
   * </p>
   */
  frNotNoUpdate(-2147220997),
  /**
   * <p>
   * Register value is not of Integer type.
   * </p>
   * <p>
   * The value of this constant is -2147220904
   * </p>
   */
  frRegNotInteger(-2147220904),
  /**
   * <p>
   * Register value is not of Real type.
   * </p>
   * <p>
   * The value of this constant is -2147220903
   * </p>
   */
  frRegNotReal(-2147220903),
  /**
   * <p>
   * Invalid position type.
   * </p>
   * <p>
   * The value of this constant is -2147220804
   * </p>
   */
  frInvPositionType(-2147220804),
  /**
   * <p>
   * Invalid display type for current position.
   * </p>
   * <p>
   * The value of this constant is -2147220803
   * </p>
   */
  frInvCurPosDisplayType(-2147220803),
  /**
   * <p>
   * Not allowed to change the position type.
   * </p>
   * <p>
   * The value of this constant is -2147220802
   * </p>
   */
  frNotChangePosType(-2147220802),
  /**
   * <p>
   * The frame number is out of range.
   * </p>
   * <p>
   * The value of this constant is -2147220801
   * </p>
   */
  frFrameRange(-2147220801),
  /**
   * <p>
   * Invalid position ID.
   * </p>
   * <p>
   * The value of this constant is -2147220800
   * </p>
   */
  frInvPosId(-2147220800),
  /**
   * <p>
   * Invalid turn number.
   * </p>
   * <p>
   * The value of this constant is -2147220799
   * </p>
   */
  frInvTurnNumber(-2147220799),
  /**
   * <p>
   * Invalid turn index.
   * </p>
   * <p>
   * The value of this constant is -2147220798
   * </p>
   */
  frInvTurnIndex(-2147220798),
  /**
   * <p>
   * Invalid configuration string.
   * </p>
   * <p>
   * The value of this constant is -2147220797
   * </p>
   */
  frInvConfigString(-2147220797),
  /**
   * <p>
   * A non-position object was passed to a method that expected a position.
   * </p>
   * <p>
   * The value of this constant is -2147220796
   * </p>
   */
  frNotAPosition(-2147220796),
  /**
   * <p>
   * The motion type is not supported for this operation.
   * </p>
   * <p>
   * The value of this constant is -2147220795
   * </p>
   */
  frMoTypeNotSupported(-2147220795),
  /**
   * <p>
   * Group index is out of range.
   * </p>
   * <p>
   * The value of this constant is -2147220704
   * </p>
   */
  frGroupIndexRange(-2147220704),
  /**
   * <p>
   * Group index is not supported for this position.
   * </p>
   * <p>
   * The value of this constant is -2147220703
   * </p>
   */
  frGroupNotSupported(-2147220703),
  /**
   * <p>
   * Axes index is out of range.
   * </p>
   * <p>
   * The value of this constant is -2147220702
   * </p>
   */
  frAxesRange(-2147220702),
  /**
   * <p>
   * Extended axis index is not supported for this position.
   * </p>
   * <p>
   * The value of this constant is -2147220701
   * </p>
   */
  frExtAxesNotSupported(-2147220701),
  /**
   * <p>
   * Invalid vector index value.  Must be in the range 1 to 3.
   * </p>
   * <p>
   * The value of this constant is -2147220700
   * </p>
   */
  frInvVectIndex(-2147220700),
  /**
   * <p>
   * Extended axis index is not supported for this position.
   * </p>
   * <p>
   * The value of this constant is -2147220699
   * </p>
   */
  frExtAxesRange(-2147220699),
  /**
   * <p>
   * The TP position already exists in the collection.
   * </p>
   * <p>
   * The value of this constant is -2147220698
   * </p>
   */
  frTPPosAlreadyExists(-2147220698),
  /**
   * <p>
   * Invalid position ID.
   * </p>
   * <p>
   * The value of this constant is -2147220697
   * </p>
   */
  frInvTPPosId(-2147220697),
  /**
   * <p>
   * Invalid Input Parameter.
   * </p>
   * <p>
   * The value of this constant is -2147220696
   * </p>
   */
  frInvTPBinayPosData(-2147220696),
  /**
   * <p>
   * The TP label ID does not exist in the program.
   * </p>
   * <p>
   * The value of this constant is -2147220604
   * </p>
   */
  frInvTPLblId(-2147220604),
  /**
   * <p>
   * Can't create robot sync directory.
   * </p>
   * <p>
   * The value of this constant is -2147220503
   * </p>
   */
  frCantCreateSyncDir(-2147220503),
  /**
   * <p>
   * Can't change to robot sync directory.
   * </p>
   * <p>
   * The value of this constant is -2147220502
   * </p>
   */
  frCantChangeToSyncDir(-2147220502),
  /**
   * <p>
   * A required registry value could not be read.
   * </p>
   * <p>
   * The value of this constant is -2147220402
   * </p>
   */
  frRegCantOpenValue(-2147220402),
  /**
   * <p>
   * The variable is of an invalid type.
   * </p>
   * <p>
   * The value of this constant is -2147220204
   * </p>
   */
  frInvVarType(-2147220204),
  /**
   * <p>
   * The variable is of an invalid class.
   * </p>
   * <p>
   * The value of this constant is -2147220203
   * </p>
   */
  frInvVarClass(-2147220203),
  /**
   * <p>
   * The variable is of an unimplemented type.
   * </p>
   * <p>
   * The value of this constant is -2147220202
   * </p>
   */
  frUnimpVarType(-2147220202),
  /**
   * <p>
   * Use Robot.RegPositions to access position registers.
   * </p>
   * <p>
   * The value of this constant is -2147220201
   * </p>
   */
  frUseRobotRegPositions(-2147220201),
  /**
   * <p>
   * Floating point number is out of range.
   * </p>
   * <p>
   * The value of this constant is -2147220200
   * </p>
   */
  frFloatRange(-2147220200),
  /**
   * <p>
   * Cannot be applied globally to all SysVars.
   * </p>
   * <p>
   * The value of this constant is -2147220199
   * </p>
   */
  frInvForSysvars(-2147220199),
  /**
   * <p>
   * Invalid Array Index.
   * </p>
   * <p>
   * The value of this constant is -2147220198
   * </p>
   */
  frInvArrayIndex(-2147220198),
  /**
   * <p>
   * Parameter is not within the range of acceptable values
   * </p>
   * <p>
   * The value of this constant is -2147220197
   * </p>
   */
  frInvParamRange(-2147220197),
  /**
   * <p>
   * The alarm index is out of bounds.
   * </p>
   * <p>
   * The value of this constant is -2147220004
   * </p>
   */
  frAlmIndexBounds(-2147220004),
  /**
   * <p>
   * The maximum alarm log size requested is invalid.
   * </p>
   * <p>
   * The value of this constant is -2147220003
   * </p>
   */
  frAlmMaxSiz(-2147220003),
  /**
   * <p>
   * Unable to allocate memory for alarm synchronization.
   * </p>
   * <p>
   * The value of this constant is -2147220002
   * </p>
   */
  frAlmOutMem(-2147220002),
  /**
   * <p>
   * Local alarm posting not supported.
   * </p>
   * <p>
   * The value of this constant is -2147220001
   * </p>
   */
  frCantPostLocalAlarms(-2147220001),
  /**
   * <p>
   * Invalid error severity number.
   * </p>
   * <p>
   * The value of this constant is -2147220000
   * </p>
   */
  frInvalidErrorSeverity(-2147220000),
  /**
   * <p>
   * Invalid error facility number.
   * </p>
   * <p>
   * The value of this constant is -2147219999
   * </p>
   */
  frInvalidErrorFacility(-2147219999),
  /**
   * <p>
   * Invalid error number.
   * </p>
   * <p>
   * The value of this constant is -2147219998
   * </p>
   */
  frInvalidErrorNumber(-2147219998),
  /**
   * <p>
   * Invalid I/O type collection index.
   * </p>
   * <p>
   * The value of this constant is -2147219904
   * </p>
   */
  frIOStatBadTypeIndex(-2147219904),
  /**
   * <p>
   * Invalid I/O signal collection index.
   * </p>
   * <p>
   * The value of this constant is -2147219903
   * </p>
   */
  frIOStatBadSignalIndex(-2147219903),
  /**
   * <p>
   * Invalid User I/O collection index.
   * </p>
   * <p>
   * The value of this constant is -2147219902
   * </p>
   */
  frIOStatBadUserIndex(-2147219902),
  /**
   * <p>
   * Invalid I/O configuration collection index.
   * </p>
   * <p>
   * The value of this constant is -2147219901
   * </p>
   */
  frIOStatBadConfigIndex(-2147219901),
  /**
   * <p>
   * Duplicate User I/O type.
   * </p>
   * <p>
   * The value of this constant is -2147219900
   * </p>
   */
  frIOStatDupUserType(-2147219900),
  /**
   * <p>
   * Duplicate User I/O signal.
   * </p>
   * <p>
   * The value of this constant is -2147219899
   * </p>
   */
  frIOStatDupUserSignal(-2147219899),
  /**
   * <p>
   * User I/O type is locked.
   * </p>
   * <p>
   * The value of this constant is -2147219898
   * </p>
   */
  frIOStatUserTypeLocked(-2147219898),
  /**
   * <p>
   * Operation value only for User I/O types.
   * </p>
   * <p>
   * The value of this constant is -2147219897
   * </p>
   */
  frIOStatNotUserType(-2147219897),
  /**
   * <p>
   * File extension for I/O save must be .IO.
   * </p>
   * <p>
   * The value of this constant is -2147219896
   * </p>
   */
  frIOSaveBadFileExtension(-2147219896),
  /**
   * <p>
   * TPOUT signals are automatically kept fresh
   * </p>
   * <p>
   * The value of this constant is -2147219895
   * </p>
   */
  frIOTPOutIsAlwaysFresh(-2147219895),
  /**
   * <p>
   * An unknown variant type is being used to send key codes.
   * </p>
   * <p>
   * The value of this constant is -2147219804
   * </p>
   */
  frTPScrnUnknownKeys(-2147219804),
  /**
   * <p>
   * Invalid task parameter.
   * </p>
   * <p>
   * The value of this constant is -2147219704
   * </p>
   */
  frTaskInvalidParam(-2147219704),
  /**
   * <p>
   * The task parameter is out of range.
   * </p>
   * <p>
   * The value of this constant is -2147219703
   * </p>
   */
  frTaskParamRange(-2147219703),
  /**
   * <p>
   * The requested task name does not exist.
   * </p>
   * <p>
   * The value of this constant is -2147219702
   * </p>
   */
  frTaskNoName(-2147219702),
  /**
   * <p>
   * SubSystem Code must be a number in the range 0 to 255.
   * </p>
   * <p>
   * The value of this constant is -2147219604
   * </p>
   */
  frPacketEventInvSSC(-2147219604),
  /**
   * <p>
   * Request Code must be a number in the range of 0 to 255.
   * </p>
   * <p>
   * The value of this constant is -2147219603
   * </p>
   */
  frPacketEventInvReqC(-2147219603),
  /**
   * <p>
   * The requested data item does not exist.
   * </p>
   * <p>
   * The value of this constant is -2147219601
   * </p>
   */
  frPacketEventNoSuchItem(-2147219601),
  /**
   * <p>
   * The data could not be decoded.  The buffer may be corrupted.
   * </p>
   * <p>
   * The value of this constant is -2147219600
   * </p>
   */
  frPacketEventBodyCorrupt(-2147219600),
  /**
   * <p>
   * Invalid feature collection index.
   * </p>
   * <p>
   * The value of this constant is -2147219504
   * </p>
   */
  frFeatureBadIndex(-2147219504),
  /**
   * <p>
   * Invalid feature order number.
   * </p>
   * <p>
   * The value of this constant is -2147219503
   * </p>
   */
  frFeatureBadOrderNum(-2147219503),
  /**
   * <p>
   * Invalid TP instruction LM code or option ID.
   * </p>
   * <p>
   * The value of this constant is -2147219502
   * </p>
   */
  frTPInstructionBadLMCode(-2147219502),
  /**
   * <p>
   * Invalid TP instruction index.
   * </p>
   * <p>
   * The value of this constant is -2147219501
   * </p>
   */
  frTPInstructionBadIndex(-2147219501),
  /**
   * <p>
   * Invalid TP instruction option ID.
   * </p>
   * <p>
   * The value of this constant is -2147219500
   * </p>
   */
  frTPInstructionBadOptCode(-2147219500),
  /**
   * <p>
   * Invalid TP application data index.
   * </p>
   * <p>
   * The value of this constant is -2147219499
   * </p>
   */
  frSynchApplDataItemBadIndex(-2147219499),
  /**
   * <p>
   * Invalid TP application data name or ID.
   * </p>
   * <p>
   * The value of this constant is -2147219498
   * </p>
   */
  frSynchApplDataItemBadID(-2147219498),
  /**
   * <p>
   * The robot does not support this feature.
   * </p>
   * <p>
   * The value of this constant is -2147219497
   * </p>
   */
  frFeatureNotSupported(-2147219497),
  /**
   * <p>
   * Invalid Application Data
   * </p>
   * <p>
   * The value of this constant is -2147219304
   * </p>
   */
  frInvalidApplData(-2147219304),
  /**
   * <p>
   * The TP Application Data already exists in the collection.
   * </p>
   * <p>
   * The value of this constant is -2147219303
   * </p>
   */
  frTPApplAlreadyExists(-2147219303),
  /**
   * <p>
   * Application Data X not in Synch/Features Database.
   * </p>
   * <p>
   * The value of this constant is -2147219302
   * </p>
   */
  frInvalidApplSynch(-2147219302),
  /**
   * <p>
   * The value of this constant is -2147219204
   * </p>
   */
  frCantOpenClipboard(-2147219204),
  /**
   * <p>
   * The value of this constant is -2147219203
   * </p>
   */
  frCantCopyClipboard(-2147219203),
  /**
   * <p>
   * The value of this constant is -2147219202
   * </p>
   */
  frClipboardNoLines(-2147219202),
  /**
   * <p>
   * No objects were identified when creating the ScatteredAccess object.
   * </p>
   * <p>
   * The value of this constant is -2147219104
   * </p>
   */
  frScattAccNoItems(-2147219104),
  /**
   * <p>
   * An object of an invalid class type was supplied in the list to create an FRCScatteredAccess object.
   * </p>
   * <p>
   * The value of this constant is -2147219103
   * </p>
   */
  frScattAccInvType(-2147219103),
  /**
   * <p>
   * Invalid start mode detected.
   * </p>
   * <p>
   * The value of this constant is -2147219054
   * </p>
   */
  frInvStartMode(-2147219054),
  /**
   * <p>
   * Error loading file during offline synchronization.
   * </p>
   * <p>
   * The value of this constant is 1000
   * </p>
   */
  frSynchLoadErr(1000),
  /**
   * <p>
   * Bad Pipe ID number.
   * </p>
   * <p>
   * The value of this constant is -2147219029
   * </p>
   */
  frPipeBadPipeID(-2147219029),
  /**
   * <p>
   * Bad Pipe Index number.
   * </p>
   * <p>
   * The value of this constant is -2147219028
   * </p>
   */
  frPipeBadPipeIndex(-2147219028),
  /**
   * <p>
   * Invalid data type in Pipe structure.
   * </p>
   * <p>
   * The value of this constant is -2147219027
   * </p>
   */
  frPipeInvalidDataType(-2147219027),
  /**
   * <p>
   * Invalid pipe field name.
   * </p>
   * <p>
   * The value of this constant is -2147219026
   * </p>
   */
  frPipeInvalidFieldName(-2147219026),
  /**
   * <p>
   * Invalid pipe field index.
   * </p>
   * <p>
   * The value of this constant is -2147219025
   * </p>
   */
  frPipeInvalidFieldIndex(-2147219025),
  /**
   * <p>
   * The Pipe monitor thread can't be created.
   * </p>
   * <p>
   * The value of this constant is -2147219024
   * </p>
   */
  frPipeCantCreateMonitor(-2147219024),
  /**
   * <p>
   * The Data Acquisition option is not installed on the controller.
   * </p>
   * <p>
   * The value of this constant is -2147219023
   * </p>
   */
  frPipeDAQNotLoaded(-2147219023),
  /**
   * <p>
   * Pipe is not registered for events.
   * </p>
   * <p>
   * The value of this constant is -2147219022
   * </p>
   */
  frPipeNotRegisteredForEvents(-2147219022),
  ;

  private final int value;
  FRERobotErrors(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
