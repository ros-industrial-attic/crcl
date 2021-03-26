package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

@IID("{D0EDFE01-C6AC-11D2-8727-00C04F81118D}")
public interface IVarObject2 extends com.github.wshackle.fanuc.robotserver.IVarObject {
  // Methods:
  /**
   * <p>
   * Returns/sets the level of controller restriction override. Use FREVarOverrideConstants to set this.
   * </p>
   * <p>
   * Getter method for the COM property "Override"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(400) //= 0x190. The runtime will prefer the VTID if present
  @VTID(17)
  int override();


  /**
   * <p>
   * Returns/sets the level of controller restriction override. Use FREVarOverrideConstants to set this.
   * </p>
   * <p>
   * Setter method for the COM property "Override"
   * </p>
   * @param override Mandatory int parameter.
   */

  @DISPID(400) //= 0x190. The runtime will prefer the VTID if present
  @VTID(18)
  void override(
    int override);


  /**
   * <p>
   * Returns/sets whether or not the data is immediately sent to the robot when an assignment is made to the Value property. 
   * </p>
   * <p>
   * Getter method for the COM property "NoUpdate"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(401) //= 0x191. The runtime will prefer the VTID if present
  @VTID(19)
  boolean noUpdate();


  /**
   * <p>
   * Returns/sets whether or not the data is immediately sent to the robot when an assignment is made to the Value property. 
   * </p>
   * <p>
   * Setter method for the COM property "NoUpdate"
   * </p>
   * @param noUpdate Mandatory boolean parameter.
   */

  @DISPID(401) //= 0x191. The runtime will prefer the VTID if present
  @VTID(20)
  void noUpdate(
    boolean noUpdate);


  /**
   * <p>
   * Sends the local copy of this variable's value to the robot.
   * </p>
   */

  @DISPID(402) //= 0x192. The runtime will prefer the VTID if present
  @VTID(21)
  void update();


  // Properties:
}
