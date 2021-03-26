package com.github.wshackle.fanuc.robotneighborhood  ;

import com4j.*;

/**
 * A collection of FRCRNRobots and FRCRNRobot objects.
 */
@IID("{2EB0E3B0-268B-484F-BEC3-3B9B55E5D170}")
public interface IRNRobots extends Com4jObject,Iterable<Com4jObject> {
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
   * Returns the number of items within this object.
   * </p>
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(8)
  int count();


  /**
   * <p>
   * Creates an FRCRNRobot object for accessing a real controller and adds it to the FRCRNRobots collection.
   * </p>
   * @param name Mandatory java.lang.String parameter.
   * @param hostName Mandatory java.lang.String parameter.
   * @return  Returns a value of type com.github.wshackle.fanuc.robotneighborhood.IRNRealRobot
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(9)
  com.github.wshackle.fanuc.robotneighborhood.IRNRealRobot addRealRobot(
    java.lang.String name,
    java.lang.String hostName);


  /**
   * <p>
   * Creates an FRCRNRobots object and adds it to the FRCRNRobots collection.
   * </p>
   * @param name Mandatory java.lang.String parameter.
   * @return  Returns a value of type com.github.wshackle.fanuc.robotneighborhood.IRNRobots
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(10)
  com.github.wshackle.fanuc.robotneighborhood.IRNRobots addRobots(
    java.lang.String name);


  /**
   * <p>
   * Returns/Sets the name of this robots collection.
   * </p>
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(11)
  java.lang.String name();


  /**
   * <p>
   * Returns/Sets the name of this robots collection.
   * </p>
   * <p>
   * Setter method for the COM property "Name"
   * </p>
   * @param name Mandatory java.lang.String parameter.
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(12)
  void name(
    java.lang.String name);


  /**
   * <p>
   * Returns an FRCRNRobots or FRCRNRobot object from the collection.
   * </p>
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param name Optional parameter. Default value is ""
   * @param index Optional parameter. Default value is -1
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(13)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject item(
    @Optional @DefaultValue("") java.lang.String name,
    @Optional @DefaultValue("-1") int index);


  /**
   * <p>
   * Creates an FRCRNRobot object for accessing a virtual controller and adds it to the FRCRNRobots collection.
   * </p>
   * @param name Mandatory java.lang.String parameter.
   * @param location Mandatory java.lang.String parameter.
   * @return  Returns a value of type com.github.wshackle.fanuc.robotneighborhood.IRNVirtualRobot
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(14)
  com.github.wshackle.fanuc.robotneighborhood.IRNVirtualRobot addVirtualRobot(
    java.lang.String name,
    java.lang.String location);


  /**
   * <p>
   * Returns the parent of this object. FRCRNRobots or FRCRobotNeighborhood object from the collection.
   * </p>
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotneighborhood.IRNRobots
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(15)
  com.github.wshackle.fanuc.robotneighborhood.IRNRobots parent();


  /**
   * <p>
   * Returns the fully decorated name of this robots collection.
   * </p>
   * <p>
   * Getter method for the COM property "PathName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(16)
  java.lang.String pathName();


  /**
   * <p>
   * Copies the description of this object (and all its children) to the Windows clipboard.
   * </p>
   * @param multiSelectList Optional parameter. Default value is ""
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(17)
  void copy(
    @Optional @DefaultValue("") java.lang.String multiSelectList);


  /**
   * <p>
   * Pastes the contents of the Robot Neighborhood Windows clipboard as a child of this object.
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(18)
  java.lang.String paste();


  /**
   * <p>
   * Removes the specified FRCRNRobot or FRCRNRobots object from the FRCRNRobots collection.
   * </p>
   * @param name Optional parameter. Default value is ""
   * @param index Optional parameter. Default value is -1
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(19)
  void remove(
    @Optional @DefaultValue("") java.lang.String name,
    @Optional @DefaultValue("-1") int index);


  /**
   * <p>
   * Cancels any active KeepAlive processes for robots controlled by this object..
   * </p>
   */

  @DISPID(12) //= 0xc. The runtime will prefer the VTID if present
  @VTID(20)
  void cancelKeepAlive();


  /**
   * <p>
   * Removes entries within the scope of this object for which the files no longer exist on the PC.
   * </p>
   */

  @DISPID(13) //= 0xd. The runtime will prefer the VTID if present
  @VTID(21)
  void purge();


    // Properties:
  }
