package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

@IID("{59DC16F5-AF91-11D0-A072-00A02436CF7E}")
public interface IUserIOType extends com.github.wshackle.fanuc.robotserver.IIOType {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(10)
  java.lang.String name();


  /**
   * <p>
   * Setter method for the COM property "Name"
   * </p>
   * @param name Mandatory java.lang.String parameter.
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(11)
  void name(
    java.lang.String name);


  /**
   * <p>
   * Getter method for the COM property "Signals"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IUserIOSignals
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(12)
  com.github.wshackle.fanuc.robotserver.IUserIOSignals signals();


  /**
   * <p>
   * Getter method for the COM property "Lock"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(13)
  boolean lock();


  /**
   * <p>
   * Setter method for the COM property "Lock"
   * </p>
   * @param lock Mandatory boolean parameter.
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(14)
  void lock(
    boolean lock);


  // Properties:
}
