package com.github.wshackle.fanuc.robotneighborhood  ;

import com4j.*;

/**
 * Provides information about an RDM response recently received from a robot.
 */
@IID("{C3E1CF4A-13B1-4AFF-A40F-06B0509213A0}")
public interface IRNRDMResponse extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Returns the name of the application tool running on this robot 
   * </p>
   * <p>
   * Getter method for the COM property "AppTool"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  java.lang.String appTool();


  /**
   * <p>
   * Returns a number that combines the flag bits of network based options installed on this robot.
   * </p>
   * <p>
   * Getter method for the COM property "Flags"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  int flags();


  /**
   * <p>
   * Returns the F-number of the robot.
   * </p>
   * <p>
   * Getter method for the COM property "FNumber"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(9)
  java.lang.String fNumber();


  /**
   * <p>
   * Returns the network HostName of this robot.
   * </p>
   * <p>
   * Getter method for the COM property "HostName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(10)
  java.lang.String hostName();


  /**
   * <p>
   * Returns the IPAddress of this robot.
   * </p>
   * <p>
   * Getter method for the COM property "IPAddress"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(11)
  java.lang.String ipAddress();


  /**
   * <p>
   * Returns Ture if the flags specified are all set in the RDM Packet.
   * </p>
   * <p>
   * Getter method for the COM property "IsSupported"
   * </p>
   * @param flags Mandatory int parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(12)
  boolean isSupported(
    int flags);


  /**
   * <p>
   * Returns the version number of the controller software running on this robot.
   * </p>
   * <p>
   * Getter method for the COM property "Version"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(13)
  java.lang.String version();


  // Properties:
}
