package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Raises an event when the desired ROS Packet is received from the controller. 
 */
@IID("{FCCE9E60-9420-11D1-B751-00C04FB99C75}")
public interface IPacketEvent extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * <p>
   * Returns the Packet ID used to decide if a Receive event should be triggered on an incoming ROS packet.
   * </p>
   * <p>
   * Getter method for the COM property "PacketID"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(8)
  int packetID();


  /**
   * <p>
   * Returns the request code trigger value which is used to decide if a Receive event should be raised for an incoming ROS packet.
   * </p>
   * <p>
   * Getter method for the COM property "RequestCode"
   * </p>
   * @return  Returns a value of type byte
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(9)
  byte requestCode();


  /**
   * <p>
   * Returns the subsystem code trigger value which is used to decide if a Receive event should be raised for an incoming ROS packet.
   * </p>
   * <p>
   * Getter method for the COM property "SubSystemCode"
   * </p>
   * @return  Returns a value of type byte
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(10)
  byte subSystemCode();


  // Properties:
}
