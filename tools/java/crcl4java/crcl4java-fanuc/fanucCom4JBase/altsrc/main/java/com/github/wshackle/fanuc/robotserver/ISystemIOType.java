package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * This object is used to access both I/O signal and I/O configuration collections.
 */
@IID("{59DC16F2-AF91-11D0-A072-00A02436CF7E}")
public interface ISystemIOType extends com.github.wshackle.fanuc.robotserver.IIOType {
  // Methods:
  /**
   * <p>
   * Returns the name of the I/O type.
   * </p>
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
   * Returns the collection of I/O signals
   * </p>
   * <p>
   * Getter method for the COM property "Signals"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IIOSignals
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(11)
  com.github.wshackle.fanuc.robotserver.IIOSignals signals();


  /**
   * <p>
   * Returns True if the I/O type is input, returns False otherwise.
   * </p>
   * <p>
   * Getter method for the COM property "IsInput"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(12)
  boolean isInput();


  /**
   * <p>
   * Returns True if the values of the I/O type are Boolean, returns False otherwise.
   * </p>
   * <p>
   * Getter method for the COM property "IsBoolean"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(13)
  boolean isBoolean();


  /**
   * <p>
   * Returns True if the I/O type can be configured, returns False otherwise.
   * </p>
   * <p>
   * Getter method for the COM property "CanConfigure"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(205) //= 0xcd. The runtime will prefer the VTID if present
  @VTID(14)
  boolean canConfigure();


  /**
   * <p>
   * Returns True if the I/O type can be simulated, returns False otherwise.
   * </p>
   * <p>
   * Getter method for the COM property "CanSimulate"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(206) //= 0xce. The runtime will prefer the VTID if present
  @VTID(15)
  boolean canSimulate();


  /**
   * <p>
   * Returns True if reverse polarity is available, returns False otherwise.
   * </p>
   * <p>
   * Getter method for the COM property "CanInvert"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(207) //= 0xcf. The runtime will prefer the VTID if present
  @VTID(16)
  boolean canInvert();


  /**
   * <p>
   * Returns True if complementary pairs are available, returns False otherwise.
   * </p>
   * <p>
   * Getter method for the COM property "CanComplement"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(208) //= 0xd0. The runtime will prefer the VTID if present
  @VTID(17)
  boolean canComplement();


  // Properties:
}
