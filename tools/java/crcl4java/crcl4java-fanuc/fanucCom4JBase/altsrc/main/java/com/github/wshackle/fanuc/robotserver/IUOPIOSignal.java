package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Represents a User Operator Panel I/O signal.  
 */
@IID("{714CC924-B4E5-11D0-A073-00A02436CF7E}")
public interface IUOPIOSignal extends com.github.wshackle.fanuc.robotserver.IIOSignal {
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
   * Returns the owning Signals collection
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.IIOSignals
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(18)
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
  @VTID(19)
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
  @VTID(20)
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
  @VTID(21)
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
  @VTID(22)
  void noUpdate(
    boolean noUpdate);


  /**
   * <p>
   * Reads new information of the signal from the robot.
   * </p>
   */

  @DISPID(252) //= 0xfc. The runtime will prefer the VTID if present
  @VTID(23)
  void refresh();


  /**
   * <p>
   * Sends the local copy of this signal's information to the robot.
   * </p>
   */

  @DISPID(253) //= 0xfd. The runtime will prefer the VTID if present
  @VTID(24)
  void update();


  // Properties:
}
