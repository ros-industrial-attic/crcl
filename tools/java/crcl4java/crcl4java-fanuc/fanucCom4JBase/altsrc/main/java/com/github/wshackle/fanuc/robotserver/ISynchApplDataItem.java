package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

@IID("{8FAFC8E9-B2B8-11D1-B705-00C04FA3BD85}")
public interface ISynchApplDataItem extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(8)
  java.lang.String name();


  /**
   * <p>
   * This is an integer that represents the application ID, if any, of this application data item. 
   * </p>
   * <p>
   * Getter method for the COM property "ApplicationID"
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(9)
  short applicationID();


  /**
   * <p>
   * This is a string that represents the application data prog ID of the object that handles this application data item.
   * </p>
   * <p>
   * Getter method for the COM property "ApplProgID"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(10)
  java.lang.String applProgID();


  /**
   * <p>
   * This is a string that represents the application data prog ID of the object that handles this application data item.
   * </p>
   * <p>
   * Setter method for the COM property "ApplProgID"
   * </p>
   * @param applProgID Mandatory java.lang.String parameter.
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(11)
  void applProgID(
    java.lang.String applProgID);


  /**
   * <p>
   * This is a string that represents the edit view prog ID of the editing object for this application data item
   * </p>
   * <p>
   * Getter method for the COM property "EditProgID"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(104) //= 0x68. The runtime will prefer the VTID if present
  @VTID(12)
  java.lang.String editProgID();


  /**
   * <p>
   * This is a string that represents the edit view prog ID of the editing object for this application data item
   * </p>
   * <p>
   * Setter method for the COM property "EditProgID"
   * </p>
   * @param editProgID Mandatory java.lang.String parameter.
   */

  @DISPID(104) //= 0x68. The runtime will prefer the VTID if present
  @VTID(13)
  void editProgID(
    java.lang.String editProgID);


  // Properties:
}
