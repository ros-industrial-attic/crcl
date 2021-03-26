package com.github.wshackle.fanuc.robotneighborhood  ;

import com4j.*;

/**
 */
public enum FRERNEventIDConstants implements ComEnum {
  /**
   * <p>
   * This event notifies the client when a service has changed state on the controller.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frRNServiceOnStateChangeEventID(1),
  /**
   * <p>
   * Occurs when a change in a connections status is detected.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frRNVirtualRobotOnConnectionStatusChangeEventID(1),
  /**
   * <p>
   * Occurs when the start mode of the virtual robot changes.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frRNVirtualRobotOnStartSequenceChangeEventID(2),
  /**
   * <p>
   * Occurs when the sequence number changes during startup.
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frRNVirtualRobotOnStartModeChangeEventID(3),
  /**
   * <p>
   * Occurs when an asynchronous error from the virtual controller is encountered.
   * </p>
   * <p>
   * The value of this constant is 4
   * </p>
   */
  frRNVirtualRobotOnErrorEventID(4),
  /**
   * <p>
   * Occurs when a change in a connections status is detected.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frRNRobotOnConnectionStatusChangeEventID(1),
  /**
   * <p>
   * Occurs when in change is made to the name or structure of this object or its ancestry.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frRNRobotsOnOrganizationChangeEventID(1),
  /**
   * <p>
   * Occurs when the connection status changes on a robot that is a descendant of this object.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frRNRobotsOnRobotConnectionStatusChangeEventID(2),
  /**
   * <p>
   * Occurs when the content of the most recent response from a robot is different than the last.
   * </p>
   * <p>
   * The value of this constant is 1
   * </p>
   */
  frRNRDMResponsesOnChangeEventID(1),
  /**
   * <p>
   * Occurs when a robot that had previously responded, fails to respond now.
   * </p>
   * <p>
   * The value of this constant is 2
   * </p>
   */
  frRNRDMResponsesOnExpireEventID(2),
  /**
   * <p>
   * Occurs when a robot not currently in the responses collection responds to an RDM scan.
   * </p>
   * <p>
   * The value of this constant is 3
   * </p>
   */
  frRNRDMResponsesOnNewEventID(3),
  ;

  private final int value;
  FRERNEventIDConstants(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
