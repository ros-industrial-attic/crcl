package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Provides access to robot controller system information.
 */
@IID("{4553DA61-ACA1-11D3-8783-00C04F81118D}")
public interface ISysInfo extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * <p>
   * Returns/sets the date and time on the robot's system clock.
   * </p>
   * <p>
   * Getter method for the COM property "Clock"
   * </p>
   * @return  Returns a value of type java.util.Date
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(8)
  java.util.Date clock();


  /**
   * <p>
   * Returns/sets the date and time on the robot's system clock.
   * </p>
   * <p>
   * Setter method for the COM property "Clock"
   * </p>
   * @param dateTime Mandatory java.util.Date parameter.
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(9)
  void clock(
    java.util.Date dateTime);


  /**
   * <p>
   * Returns the number of bytes of battery backed CMOS hardware configured on the controller.
   * </p>
   * <p>
   * Getter method for the COM property "CMOS"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(10)
  int cmos();


  /**
   * <p>
   * Returns the number of bytes of DRAM hardware configured on the controller.
   * </p>
   * <p>
   * Getter method for the COM property "DRAM"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(11)
  int dram();


  /**
   * <p>
   * Returns the number of bytes of Flash ROM (FROM) hardware configured on the controller.
   * </p>
   * <p>
   * Getter method for the COM property "From"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(104) //= 0x68. The runtime will prefer the VTID if present
  @VTID(12)
  int from();


  /**
   * <p>
   * Returns the number of bytes allocated to the PERM memory pool.
   * </p>
   * <p>
   * Getter method for the COM property "PermMemTotal"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(105) //= 0x69. The runtime will prefer the VTID if present
  @VTID(13)
  int permMemTotal();


  /**
   * <p>
   * Returns the number of bytes available in the PERM memory pool.
   * </p>
   * <p>
   * Getter method for the COM property "PermMemFree"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(106) //= 0x6a. The runtime will prefer the VTID if present
  @VTID(14)
  int permMemFree();


  /**
   * <p>
   * Returns the size of the largest free block in the PERM memory pool.
   * </p>
   * <p>
   * Getter method for the COM property "PermMemLargestFreeBlock"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(107) //= 0x6b. The runtime will prefer the VTID if present
  @VTID(15)
  int permMemLargestFreeBlock();


  /**
   * <p>
   * Returns the number of bytes used out of the PERM memory pool.
   * </p>
   * <p>
   * Getter method for the COM property "PermMemUsed"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(108) //= 0x6c. The runtime will prefer the VTID if present
  @VTID(16)
  int permMemUsed();


  /**
   * <p>
   * Returns the number of bytes allocated to the TEMP memory pool.
   * </p>
   * <p>
   * Getter method for the COM property "TempMemTotal"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(109) //= 0x6d. The runtime will prefer the VTID if present
  @VTID(17)
  int tempMemTotal();


  /**
   * <p>
   * Returns the number of bytes available in the TEMP memory pool.
   * </p>
   * <p>
   * Getter method for the COM property "TempMemFree"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(110) //= 0x6e. The runtime will prefer the VTID if present
  @VTID(18)
  int tempMemFree();


  /**
   * <p>
   * Returns the size of the largest free block in the TEMP memory pool.
   * </p>
   * <p>
   * Getter method for the COM property "TempMemLargestFreeBlock"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(111) //= 0x6f. The runtime will prefer the VTID if present
  @VTID(19)
  int tempMemLargestFreeBlock();


  /**
   * <p>
   * Returns the number of bytes used out of the TEMP memory pool.
   * </p>
   * <p>
   * Getter method for the COM property "TempMemUsed"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(112) //= 0x70. The runtime will prefer the VTID if present
  @VTID(20)
  int tempMemUsed();


  /**
   * <p>
   * Returns the number of bytes allocated to the TPP memory pool.
   * </p>
   * <p>
   * Getter method for the COM property "TPPMemTotal"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(113) //= 0x71. The runtime will prefer the VTID if present
  @VTID(21)
  int tppMemTotal();


  /**
   * <p>
   * Returns the number of bytes available in the TPP memory pool.
   * </p>
   * <p>
   * Getter method for the COM property "TPPMemFree"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(114) //= 0x72. The runtime will prefer the VTID if present
  @VTID(22)
  int tppMemFree();


  /**
   * <p>
   * Returns the size of the largest free block in the TPP memory pool.
   * </p>
   * <p>
   * Getter method for the COM property "TPPMemLargestFreeBlock"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(115) //= 0x73. The runtime will prefer the VTID if present
  @VTID(23)
  int tppMemLargestFreeBlock();


  /**
   * <p>
   * Returns the number of bytes used out of the TPP memory pool.
   * </p>
   * <p>
   * Getter method for the COM property "TPPMemUsed"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(116) //= 0x74. The runtime will prefer the VTID if present
  @VTID(24)
  int tppMemUsed();


  /**
   * <p>
   * Returns the number of bytes allocated to the SYSTEM memory pool.
   * </p>
   * <p>
   * Getter method for the COM property "SystemMemTotal"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(117) //= 0x75. The runtime will prefer the VTID if present
  @VTID(25)
  int systemMemTotal();


  /**
   * <p>
   * Returns the number of bytes available in the SYSTEM memory pool.
   * </p>
   * <p>
   * Getter method for the COM property "SystemMemFree"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(118) //= 0x76. The runtime will prefer the VTID if present
  @VTID(26)
  int systemMemFree();


  /**
   * <p>
   * Returns the size of the largest free block in the SYSTEM memory pool.
   * </p>
   * <p>
   * Getter method for the COM property "SystemMemLargestFreeBlock"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(119) //= 0x77. The runtime will prefer the VTID if present
  @VTID(27)
  int systemMemLargestFreeBlock();


  /**
   * <p>
   * Returns the number of bytes used out of the SYSTEM memory pool.
   * </p>
   * <p>
   * Getter method for the COM property "SystemMemUsed"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(120) //= 0x78. The runtime will prefer the VTID if present
  @VTID(28)
  int systemMemUsed();


  /**
   * <p>
   * Returns a number representing the manufacturer and type of Flash ROM installed on the controller.
   * </p>
   * <p>
   * Getter method for the COM property "FROMType"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(121) //= 0x79. The runtime will prefer the VTID if present
  @VTID(29)
  int fromType();


  /**
   * <p>
   * Returns a constant indicating in which start mode the controller is operating.
   * </p>
   * <p>
   * Getter method for the COM property "StartMode"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FREStartModeConstants
   */

  @DISPID(122) //= 0x7a. The runtime will prefer the VTID if present
  @VTID(30)
  com.github.wshackle.fanuc.robotserver.FREStartModeConstants startMode();


  // Properties:
}
