package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Represents a digital I/O signal object.  
 */
@IID("{59DC16FC-AF91-11D0-A072-00A02436CF7E}")
public interface IDigitalIOSignal extends com.github.wshackle.fanuc.robotserver.IIOSignal {
  // Methods:
  /**
   * <p>
   * Returns/sets the value of a signal.
   * </p>
   * <p>
   * Getter method for the COM property "Value"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(16)
  boolean value();


  /**
   * <p>
   * Returns/sets the value of a signal.
   * </p>
   * <p>
   * Setter method for the COM property "Value"
   * </p>
   * @param value Mandatory boolean parameter.
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(17)
  void value(
    boolean value);


  /**
   * <p>
   * Returns/sets the simulation status of a signal; True - Simulated, False - unsimulated.
   * </p>
   * <p>
   * Getter method for the COM property "Simulate"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(18)
  boolean simulate();


  /**
   * <p>
   * Returns/sets the simulation status of a signal; True - Simulated, False - unsimulated.
   * </p>
   * <p>
   * Setter method for the COM property "Simulate"
   * </p>
   * @param simulate Mandatory boolean parameter.
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(19)
  void simulate(
    boolean simulate);


  /**
   * <p>
   * Returns/sets the polarity of a signal; True – normal polarity, False – inverse polarity.
   * </p>
   * <p>
   * Getter method for the COM property "Polarity"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(20)
  boolean polarity();


  /**
   * <p>
   * Returns/sets the polarity of a signal; True – normal polarity, False – inverse polarity.
   * </p>
   * <p>
   * Setter method for the COM property "Polarity"
   * </p>
   * @param polarity Mandatory boolean parameter.
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(21)
  void polarity(
    boolean polarity);


  /**
   * <p>
   * Returns/sets the complementary status of an output signal.
   * </p>
   * <p>
   * Getter method for the COM property "Complementary"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(22)
  boolean complementary();


  /**
   * <p>
   * Returns/sets the complementary status of an output signal.
   * </p>
   * <p>
   * Setter method for the COM property "Complementary"
   * </p>
   * @param complementary Mandatory boolean parameter.
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(23)
  void complementary(
    boolean complementary);


  /**
   * <p>
   * Returns the owning Signals collection
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IIOSignals
   */

  @DISPID(205) //= 0xcd. The runtime will prefer the VTID if present
  @VTID(24)
  com.github.wshackle.fanuc.robotserver.IIOSignals parent();


  /**
   * <p>
   * Controls if the data is read directly from the robot or not when an read access is made to the Value property.
   * </p>
   * <p>
   * Getter method for the COM property "NoRefresh"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(250) //= 0xfa. The runtime will prefer the VTID if present
  @VTID(25)
  boolean noRefresh();


  /**
   * <p>
   * Controls if the data is read directly from the robot or not when an read access is made to the Value property.
   * </p>
   * <p>
   * Setter method for the COM property "NoRefresh"
   * </p>
   * @param noRefresh Mandatory boolean parameter.
   */

  @DISPID(250) //= 0xfa. The runtime will prefer the VTID if present
  @VTID(26)
  void noRefresh(
    boolean noRefresh);


  /**
   * <p>
   * Controls if the data is immediately sent to the robot or not when an assignment is made to the Value property.
   * </p>
   * <p>
   * Getter method for the COM property "NoUpdate"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(251) //= 0xfb. The runtime will prefer the VTID if present
  @VTID(27)
  boolean noUpdate();


  /**
   * <p>
   * Controls if the data is immediately sent to the robot or not when an assignment is made to the Value property.
   * </p>
   * <p>
   * Setter method for the COM property "NoUpdate"
   * </p>
   * @param noUpdate Mandatory boolean parameter.
   */

  @DISPID(251) //= 0xfb. The runtime will prefer the VTID if present
  @VTID(28)
  void noUpdate(
    boolean noUpdate);


  /**
   * <p>
   * Reads new information of the signal from the robot.
   * </p>
   */

  @DISPID(252) //= 0xfc. The runtime will prefer the VTID if present
  @VTID(29)
  void refresh();


  /**
   * <p>
   * Sends the local copy of this signal's information to the robot.
   * </p>
   */

  @DISPID(253) //= 0xfd. The runtime will prefer the VTID if present
  @VTID(30)
  void update();


  // Properties:
}
