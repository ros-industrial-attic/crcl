package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Object used to access extended error information after an error has been thrown (raised) and caught.
 */
@IID("{115069C0-09C5-11D2-871C-00C04F98D092}")
public interface IRobotErrorInfo extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Returns the facility number of the error.  Facilities are in the range of 0-255.
   * </p>
   * <p>
   * Getter method for the COM property "Facility"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  int facility();


  /**
   * <p>
   * Returns the severity number of the error.  The severity must be one of the predefined values given in the type constant FREAlarmSeverityConstants
   * </p>
   * <p>
   * Getter method for the COM property "Severity"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FREAlarmSeverityConstants
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  com.github.wshackle.fanuc.robotserver.FREAlarmSeverityConstants severity();


  /**
   * <p>
   * Returns the number of the error.  Error numbers are in the range of 0-65535.
   * </p>
   * <p>
   * Getter method for the COM property "Number"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(9)
  int number();


  /**
   * <p>
   * Returns the number of the error.  Error numbers are in the range of 0-65535.
   * </p>
   * <p>
   * Getter method for the COM property "Description"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(10)
  java.lang.String description();


  /**
   * <p>
   * Returns the number of the error.  Error numbers are in the range of 0-65535.
   * </p>
   * <p>
   * Getter method for the COM property "GUID"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(11)
  java.lang.String guid();


  /**
   * <p>
   * Returns the number of the error.  Error numbers are in the range of 0-65535.
   * </p>
   * <p>
   * Getter method for the COM property "HelpContext"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(12)
  int helpContext();


  /**
   * <p>
   * Returns the number of the error.  Error numbers are in the range of 0-65535.
   * </p>
   * <p>
   * Getter method for the COM property "HelpFile"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(13)
  java.lang.String helpFile();


  /**
   * <p>
   * Returns the number of the error.  Error numbers are in the range of 0-65535.
   * </p>
   * <p>
   * Getter method for the COM property "Source"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(14)
  java.lang.String source();


  // Properties:
}
