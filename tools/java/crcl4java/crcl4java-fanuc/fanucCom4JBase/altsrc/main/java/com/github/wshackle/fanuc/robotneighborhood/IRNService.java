package com.github.wshackle.fanuc.robotneighborhood  ;

import com4j.*;

/**
 * Provides information about a TCP/IP service running on the robot.
 */
@IID("{92AFD155-F90A-47DB-86F5-51C929753BBC}")
public interface IRNService extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Returns the ID of this service.
   * </p>
   * <p>
   * Getter method for the COM property "ID"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotneighborhood.FRERNServiceIDConstants
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  com.github.wshackle.fanuc.robotneighborhood.FRERNServiceIDConstants id();


  /**
   * <p>
   * Returns the name or associated comment for the service.
   * </p>
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  java.lang.String name();


  /**
   * <p>
   * Returns the parent of this object.
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotneighborhood.IRNServices
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(9)
  com.github.wshackle.fanuc.robotneighborhood.IRNServices parent();


  /**
   * <p>
   * This property returns the TCP/IP Port Number to which the service is connected.
   * </p>
   * <p>
   * Getter method for the COM property "PortNum"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(10)
  int portNum();


  /**
   * <p>
   * This property will return the current state of the Service.
   * </p>
   * <p>
   * Getter method for the COM property "State"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotneighborhood.FRERNServiceStateConstants
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(11)
  com.github.wshackle.fanuc.robotneighborhood.FRERNServiceStateConstants state();


  // Properties:
}
