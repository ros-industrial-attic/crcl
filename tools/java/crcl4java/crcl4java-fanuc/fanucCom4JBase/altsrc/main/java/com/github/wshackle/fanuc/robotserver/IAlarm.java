package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * This object contains error and cause data for the item selected from the FRCAlarms collection.
 */
@IID("{7C37F236-A494-11D0-A37F-0020AF39BE5A}")
public interface IAlarm extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * <p>
   * Returns the Error Facility Code for the alarm.
   * </p>
   * <p>
   * Getter method for the COM property "ErrorFacility"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(8)
  int errorFacility();


  /**
   * <p>
   * Returns the severity constant of the alarm
   * </p>
   * <p>
   * Getter method for the COM property "ErrorSeverity"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FREAlarmSeverityConstants
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(9)
  com.github.wshackle.fanuc.robotserver.FREAlarmSeverityConstants errorSeverity();


  /**
   * <p>
   * Returns the field of ErrorSeverity that determines how the alarm effects program execution.
   * </p>
   * <p>
   * Getter method for the COM property "ErrorExecution"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(10)
  int errorExecution();


  /**
   * <p>
   * Returns the field of ErrorSeverity that determins how the alarm effects motion.
   * </p>
   * <p>
   * Getter method for the COM property "ErrorMotion"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(204) //= 0xcc. The runtime will prefer the VTID if present
  @VTID(11)
  int errorMotion();


  /**
   * <p>
   * Returns the Error Number for the alarm.
   * </p>
   * <p>
   * Getter method for the COM property "ErrorNumber"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(205) //= 0xcd. The runtime will prefer the VTID if present
  @VTID(12)
  int errorNumber();


  /**
   * <p>
   * Returns the Error Message for the alarm.
   * </p>
   * <p>
   * Getter method for the COM property "ErrorMessage"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(206) //= 0xce. The runtime will prefer the VTID if present
  @VTID(13)
  java.lang.String errorMessage();


  /**
   * <p>
   * Returns the Facility Code for the cause of the alarm.
   * </p>
   * <p>
   * Getter method for the COM property "CauseFacility"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(207) //= 0xcf. The runtime will prefer the VTID if present
  @VTID(14)
  int causeFacility();


  /**
   * <p>
   * Returns the cause error number for the cause of the alarm.
   * </p>
   * <p>
   * Getter method for the COM property "CauseNumber"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(208) //= 0xd0. The runtime will prefer the VTID if present
  @VTID(15)
  int causeNumber();


  /**
   * <p>
   * Returns the message string describing the cause of the alarm.
   * </p>
   * <p>
   * Getter method for the COM property "CauseMessage"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(209) //= 0xd1. The runtime will prefer the VTID if present
  @VTID(16)
  java.lang.String causeMessage();


  /**
   * <p>
   * Returns the time and date of the error as taken from the controller system clock.
   * </p>
   * <p>
   * Getter method for the COM property "TimeStamp"
   * </p>
   * @return  Returns a value of type java.util.Date
   */

  @DISPID(210) //= 0xd2. The runtime will prefer the VTID if present
  @VTID(17)
  java.util.Date timeStamp();


  /**
   * <p>
   * Returns the description of the severity of the alarm.  
   * </p>
   * <p>
   * Getter method for the COM property "SeverityMessage"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(211) //= 0xd3. The runtime will prefer the VTID if present
  @VTID(18)
  java.lang.String severityMessage();


  /**
   * <p>
   * Returns the integer value that represents the alarm’s current position in the log.
   * </p>
   * <p>
   * Getter method for the COM property "Index"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(212) //= 0xd4. The runtime will prefer the VTID if present
  @VTID(19)
  int index();


  /**
   * <p>
   * Returns the Error Facility Code Mnemonic string for the alarm.
   * </p>
   * <p>
   * Getter method for the COM property "ErrorMnemonic"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(213) //= 0xd5. The runtime will prefer the VTID if present
  @VTID(20)
  java.lang.String errorMnemonic();


  /**
   * <p>
   * Returns the Mnemonic code for the cause of the alarm.
   * </p>
   * <p>
   * Getter method for the COM property "CauseMnemonic"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(214) //= 0xd6. The runtime will prefer the VTID if present
  @VTID(21)
  java.lang.String causeMnemonic();


  /**
   * <p>
   * Returns the type of alarm as FREAlarmSeverityConstants. 
   * </p>
   * <p>
   * Getter method for the COM property "ErrorClass"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(215) //= 0xd7. The runtime will prefer the VTID if present
  @VTID(22)
  int errorClass();


  /**
   * <p>
   * Returns the name of the robot that generated the alarm.
   * </p>
   * <p>
   * Getter method for the COM property "HostName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(216) //= 0xd8. The runtime will prefer the VTID if present
  @VTID(23)
  java.lang.String hostName();


  /**
   * <p>
   * Returns the IP address of the robot that generated the alarm.
   * </p>
   * <p>
   * Getter method for the COM property "IPAddress"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(217) //= 0xd9. The runtime will prefer the VTID if present
  @VTID(24)
  java.lang.String ipAddress();


  // Properties:
}
