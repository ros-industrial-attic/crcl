package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * This object represents a single feature loaded on the controller.  
 */
@IID("{2AF44185-9273-11D1-B6F9-00C04FA3BD85}")
public interface IFeature extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * <p>
   * This is a four character string that represents the order number of this feature (i.e. “H510”). 
   * </p>
   * <p>
   * Getter method for the COM property "OrderNumber"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(8)
  java.lang.String orderNumber();


  /**
   * <p>
   * This is a string that represents the descriptive name of this feature.  
   * </p>
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(9)
  java.lang.String name();


  /**
   * <p>
   * This is a string that represents the version of this feature. 
   * </p>
   * <p>
   * Getter method for the COM property "Version"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(10)
  java.lang.String version();


  // Properties:
}
