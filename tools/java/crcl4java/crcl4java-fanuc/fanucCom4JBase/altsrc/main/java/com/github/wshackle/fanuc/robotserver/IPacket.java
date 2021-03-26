package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Provides access to the information received in a ROS packet.
 */
@IID("{7B125AAE-9330-11D1-B751-00C04FB99C75}")
public interface IPacket extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * <p>
   * Returns the contents of a ROS packet minus the standard ROS header.
   * </p>
   * <p>
   * Getter method for the COM property "Body"
   * </p>
   * @return  Returns a value of type byte[]
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(8)
  byte[] body();


  /**
   * <p>
   * Returns the itr_depth packet parameter used by the Interpreter
   * </p>
   * <p>
   * Getter method for the COM property "ItrDepth"
   * </p>
   * @return  Returns a value of type byte
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(9)
  byte itrDepth();


  /**
   * <p>
   * Returns the ROS Packet ID sent as the argument to osxsndpkt and received in the osrcvpkt argument list.
   * </p>
   * <p>
   * Getter method for the COM property "PacketID"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(10)
  int packetID();


  /**
   * <p>
   * Returns the ROS packet type.
   * </p>
   * <p>
   * Getter method for the COM property "PacketType"
   * </p>
   * @return  Returns a value of type byte
   */

  @DISPID(104) //= 0x68. The runtime will prefer the VTID if present
  @VTID(11)
  byte packetType();


  /**
   * <p>
   * Returns the request code from the header of the ROS packet.
   * </p>
   * <p>
   * Getter method for the COM property "RequestCode"
   * </p>
   * @return  Returns a value of type byte
   */

  @DISPID(105) //= 0x69. The runtime will prefer the VTID if present
  @VTID(12)
  byte requestCode();


  /**
   * <p>
   * Returns the requestor ID from the header of the ROS packet.
   * </p>
   * <p>
   * Getter method for the COM property "RequestorID"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(106) //= 0x6a. The runtime will prefer the VTID if present
  @VTID(13)
  int requestorID();


  /**
   * <p>
   * Returns the status received with each ROS packet.  
   * </p>
   * <p>
   * Getter method for the COM property "Status"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(107) //= 0x6b. The runtime will prefer the VTID if present
  @VTID(14)
  int status();


  /**
   * <p>
   * Returns the subsystem code from the header of the ROS packet.
   * </p>
   * <p>
   * Getter method for the COM property "SubSystemCode"
   * </p>
   * @return  Returns a value of type byte
   */

  @DISPID(108) //= 0x6c. The runtime will prefer the VTID if present
  @VTID(15)
  byte subSystemCode();


  /**
   * <p>
   * Extracts data from the Body of the packet if the data was inserted using the companion KAREL Add_xxxPC built-ins.  
   * </p>
   * @param item Mandatory int parameter.
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(151) //= 0x97. The runtime will prefer the VTID if present
  @VTID(16)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object decodeBody(
    int item);


  // Properties:
}
