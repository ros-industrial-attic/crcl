package com.github.wshackle.fanuc.robotneighborhood  ;

import com4j.*;

/**
 * <p>
 * An enumeration of RDM flags.
 * </p>
 */
public enum FRERNRDMFlagConstants implements ComEnum {
  /**
   * <p>
   * Indicates that the PC Interface communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frRNRDMPCIFFlag(1),
  /**
   * <p>
   * Indicates that the File Transfer Protocol communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frRNRDMFTPFlag(2),
  /**
   * <p>
   * Indicates that the Telnet communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frRNRDMTelnetFlag(4),
  /**
   * <p>
   * Indicates that the web server communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 8
   * </p>
   */
  frRNRDMHTTPFlag(8),
  /**
   * <p>
   * Indicates that the domain name server communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 16
   * </p>
   */
  frRNRDMDNSFlag(16),
  /**
   * <p>
   * Indicates that the domain host communitation protocol communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 32
   * </p>
   */
  frRNRDMDHCPFlag(32),
  /**
   * <p>
   * Indicates that the proxy server communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 64
   * </p>
   */
  frRNRDMPRXYFlag(64),
  /**
   * <p>
   * Indicates that the socket messaging communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 128
   * </p>
   */
  frRNRDMSKMGFlag(128),
  /**
   * <p>
   * Indicates that the SNPX protocol communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 256
   * </p>
   */
  frRNRDMSNPXFlag(256),
  /**
   * <p>
   * Indicates that the Ethernet controller backup/restore communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 512
   * </p>
   */
  frRNRDMECBRFlag(512),
  /**
   * <p>
   * Indicates that the web server enhancements feature is supported.
   * </p>
   * <p>
   * The value of this constant is 1024
   * </p>
   */
  frRNRDMWebPlusFlag(1024),
  /**
   * <p>
   * Indicates that the iPendant connectivity communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 2048
   * </p>
   */
  frRNRDMIPCCFlag(2048),
  /**
   * <p>
   * Indicates that the color graphic teach pendant communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 4096
   * </p>
   */
  frRNRDMCGTPFlag(4096),
  /**
   * <p>
   * Indicates that the Genius/ABRIO communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 8192
   * </p>
   */
  frRNRDMPLCIOFlag(8192),
  /**
   * <p>
   * Indicates that the ProfiBus master/slave communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 16384
   * </p>
   */
  frRNRDMProfiMSFlag(16384),
  /**
   * <p>
   * Indicates that the ProfiBus master only communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 65536
   * </p>
   */
  frRNRDMProfiSFlag(65536),
  /**
   * <p>
   * Indicates that the ProfiBus slave only communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 32768
   * </p>
   */
  frRNRDMProfiMFlag(32768),
  /**
   * <p>
   * Indicates that the Controlnet communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 131072
   * </p>
   */
  frRNRDMCNetFlag(131072),
  /**
   * <p>
   * Indicates that the Device Net communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 262144
   * </p>
   */
  frRNRDMDNetFlag(262144),
  /**
   * <p>
   * Indicates that the Ethernet global data communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 524288
   * </p>
   */
  frRNRDMEGDFlag(524288),
  /**
   * <p>
   * Indicates that the Robot Link simultaneous communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 1048576
   * </p>
   */
  frRNRDMRlinkSFlag(1048576),
  /**
   * <p>
   * Indicates that the Robot Link coordinated communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 2097152
   * </p>
   */
  frRNRDMRlinkCFlag(2097152),
  /**
   * <p>
   * Indicates that the Robot Link APDT communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 4194304
   * </p>
   */
  frRNRDMRlinkAFlag(4194304),
  /**
   * <p>
   * Indicates that the Ethernet IP communication feature is supported.
   * </p>
   * <p>
   * The value of this constant is 8388608
   * </p>
   */
  frRNRDMEIPFlag(8388608),
  /**
   * <p>
   * The value of this constant is 134217728
   * </p>
   */
  frRNRDMPMCFlag(134217728),
  ;

  private final int value;
  FRERNRDMFlagConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
