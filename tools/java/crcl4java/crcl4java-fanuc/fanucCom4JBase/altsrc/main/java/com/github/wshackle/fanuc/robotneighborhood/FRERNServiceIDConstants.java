package com.github.wshackle.fanuc.robotneighborhood  ;

import com4j.*;

/**
 * <p>
 * An enumeration of constants used identify a TCP/IP service offered by the robot.
 * </p>
 */
public enum FRERNServiceIDConstants implements ComEnum {
  /**
   * <p>
   * Indicates the Web service over TCP/IP.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frRNWebServiceID(1),
  /**
   * <p>
   * Indicates the legacy TP service for virtual over TCP/IP.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frRNTeachPendantServiceID(2),
  /**
   * <p>
   * Indicates the SMON service over TCP/IP using telnet protocol.
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frRNSMONServiceID(4),
  /**
   * <p>
   * Indicates the KCL service over TCP/IP using telnet protocol.
   * </p>
   * <p>
   * The value of this constant is 5
   * </p>
   */
  frRNKCLServiceID(5),
  /**
   * <p>
   * Indicates the RPC main service over TCP/IP.
   * </p>
   * <p>
   * The value of this constant is 7
   * </p>
   */
  frRNRPCMainServiceID(7),
  /**
   * <p>
   * Indicates the Telnet service over TCP/IP.
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frRNTelnetServiceID(8),
  /**
   * <p>
   * Indicates the FTP service over TCP/IP.
   * </p>
   * <p>
   * The value of this constant is 9
   * </p>
   */
  frRNFTPServiceID(9),
  /**
   * <p>
   * Indicates the iPendant service over TCP/IP.
   * </p>
   * <p>
   * The value of this constant is 15
   * </p>
   */
  frRNCGTPServiceID(15),
  /**
   * <p>
   * Indicates the iPendant input service over TCP/IP.
   * </p>
   * <p>
   * The value of this constant is 16
   * </p>
   */
  frRNTP_INPUTServiceID(16),
  /**
   * <p>
   * Indicates the RDM service over TCP/IP.
   * </p>
   * <p>
   * The value of this constant is 17
   * </p>
   */
  frRNRDMServiceID(17),
  /**
   * <p>
   * Indicates the Comm Web service over TCP/IP.
   * </p>
   * <p>
   * The value of this constant is 18
   * </p>
   */
  frRNHTTCServiceID(18),
  /**
   * <p>
   * Indicates the Cimplicity service over TCP/IP.
   * </p>
   * <p>
   * The value of this constant is 19
   * </p>
   */
  frRNSNPXServiceID(19),
  /**
   * <p>
   * Indicates the Robot IF service over TCP/IP.
   * </p>
   * <p>
   * The value of this constant is 20
   * </p>
   */
  frRNRobotIFServiceID(20),
  /**
   * <p>
   * Indicates that the ID is not supplied.
   * </p>
   * <p>
   * The value of this constant is 21
   * </p>
   */
  frRNPMCProgrammerServiceID(21),
  /**
   * <p>
   * Indicates the PMC Programmer service over TCP/IP.
   * </p>
   * <p>
   * The value of this constant is 255
   * </p>
   */
  frRNServiceIDNotSupplied(255),
  ;

  private final int value;
  FRERNServiceIDConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
