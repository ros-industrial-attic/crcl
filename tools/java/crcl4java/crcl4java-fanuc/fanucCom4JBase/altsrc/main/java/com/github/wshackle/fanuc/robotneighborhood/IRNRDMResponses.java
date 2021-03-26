package com.github.wshackle.fanuc.robotneighborhood  ;

import com4j.*;

/**
 * A collection of FRCRNRDMResponse objects.
 */
@IID("{FCAA0182-4A1C-4FAA-8053-AB61B90D8755}")
public interface IRNRDMResponses extends Com4jObject,Iterable<Com4jObject> {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "_NewEnum"
   * </p>
   */

  @DISPID(-4) //= 0xfffffffc. The runtime will prefer the VTID if present
  @VTID(7)
  java.util.Iterator<Com4jObject> iterator();

  /**
   * <p>
   * Returns/sets the enable status of the RDM auto scan feature.
   * </p>
   * <p>
   * Getter method for the COM property "AutoScanEnable"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(8)
  boolean autoScanEnable();


  /**
   * <p>
   * Returns/sets the enable status of the RDM auto scan feature.
   * </p>
   * <p>
   * Setter method for the COM property "AutoScanEnable"
   * </p>
   * @param autoScanEnable Mandatory boolean parameter.
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(9)
  void autoScanEnable(
    boolean autoScanEnable);


  /**
   * <p>
   * Returns/sets the time between RDM auto scans.
   * </p>
   * <p>
   * Getter method for the COM property "AutoScanPeriod"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(10)
  int autoScanPeriod();


  /**
   * <p>
   * Returns/sets the time between RDM auto scans.
   * </p>
   * <p>
   * Setter method for the COM property "AutoScanPeriod"
   * </p>
   * @param autoScanPeriod Mandatory int parameter.
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(11)
  void autoScanPeriod(
    int autoScanPeriod);


  /**
   * <p>
   * Returns the number of responses that are currently known.
   * </p>
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(12)
  int count();


  /**
   * <p>
   * Returns an FRCRNResponse object as specified.
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param ipAddress Optional parameter. Default value is ""
   * @param index Optional parameter. Default value is -1
   * @return  Returns a value of type com.github.wshackle.fanuc.robotneighborhood.IRNRDMResponse
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(13)
  com.github.wshackle.fanuc.robotneighborhood.IRNRDMResponse item(
    @Optional @DefaultValue("") java.lang.String ipAddress,
    @Optional @DefaultValue("-1") int index);


  /**
   * <p>
   * Directs the Neighborhood to perform a scan of the nextwork immediately and update its collection of responses.
   * </p>
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(14)
  void doScan();


  // Properties:
}
