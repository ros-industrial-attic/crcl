package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * This object represents an input or output configuration.
 */
@IID("{59DC1702-AF91-11D0-A072-00A02436CF7E}")
public interface IIOConfig extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * <p>
   * Returns the first logical signal number of a configuration.
   * </p>
   * <p>
   * Getter method for the COM property "FirstLogicalNum"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(8)
  int firstLogicalNum();


  /**
   * <p>
   * Returns/sets the number of logical signals configured for the I/O type; returns/sets the number of physical ports assigned for a group type.
   * </p>
   * <p>
   * Getter method for the COM property "NumSignals"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(9)
  int numSignals();


  /**
   * <p>
   * Returns/sets the number of logical signals configured for the I/O type; returns/sets the number of physical ports assigned for a group type.
   * </p>
   * <p>
   * Setter method for the COM property "NumSignals"
   * </p>
   * @param numSignals Mandatory int parameter.
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(10)
  void numSignals(
    int numSignals);


  /**
   * <p>
   * Returns/sets the rack containing the physical port module.
   * </p>
   * <p>
   * Getter method for the COM property "Rack"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(11)
  short rack();


  /**
   * <p>
   * Returns/sets the rack containing the physical port module.
   * </p>
   * <p>
   * Setter method for the COM property "Rack"
   * </p>
   * @param rack Mandatory short parameter.
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(12)
  void rack(
    short rack);


  /**
   * <p>
   * Returns/sets the slot in the rack containing the physical port module.
   * </p>
   * <p>
   * Getter method for the COM property "Slot"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(104) //= 0x68. The runtime will prefer the VTID if present
  @VTID(13)
  short slot();


  /**
   * <p>
   * Returns/sets the slot in the rack containing the physical port module.
   * </p>
   * <p>
   * Setter method for the COM property "Slot"
   * </p>
   * @param slot Mandatory short parameter.
   */

  @DISPID(104) //= 0x68. The runtime will prefer the VTID if present
  @VTID(14)
  void slot(
    short slot);


  /**
   * <p>
   * Returns/sets the physical port type to be assigned to.
   * </p>
   * <p>
   * Getter method for the COM property "PhysicalType"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(105) //= 0x69. The runtime will prefer the VTID if present
  @VTID(15)
  int physicalType();


  /**
   * <p>
   * Returns/sets the physical port type to be assigned to.
   * </p>
   * <p>
   * Setter method for the COM property "PhysicalType"
   * </p>
   * @param physicalType Mandatory int parameter.
   */

  @DISPID(105) //= 0x69. The runtime will prefer the VTID if present
  @VTID(16)
  void physicalType(
    int physicalType);


  /**
   * <p>
   * Returns/sets the number of the physical port to be assigned to; returns/sets the least-significant bit for group I/O type.
   * </p>
   * <p>
   * Getter method for the COM property "FirstPhysicalNum"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(106) //= 0x6a. The runtime will prefer the VTID if present
  @VTID(17)
  int firstPhysicalNum();


  /**
   * <p>
   * Returns/sets the number of the physical port to be assigned to; returns/sets the least-significant bit for group I/O type.
   * </p>
   * <p>
   * Setter method for the COM property "FirstPhysicalNum"
   * </p>
   * @param firstPhysicalNum Mandatory int parameter.
   */

  @DISPID(106) //= 0x6a. The runtime will prefer the VTID if present
  @VTID(18)
  void firstPhysicalNum(
    int firstPhysicalNum);


  /**
   * <p>
   * Returns True if the configuration is valid for the connected robot controller, returns False otherwise.
   * </p>
   * <p>
   * Getter method for the COM property "IsValid"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(107) //= 0x6b. The runtime will prefer the VTID if present
  @VTID(19)
  boolean isValid();


  /**
   * <p>
   * Returns the owning configurations collection
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IIOConfigs
   */

  @DISPID(108) //= 0x6c. The runtime will prefer the VTID if present
  @VTID(20)
  com.github.wshackle.fanuc.robotserver.IIOConfigs parent();


  // Properties:
}
