package com.github.wshackle.fanuc.robotneighborhood.events;

import com4j.*;

/**
 * _IRNRobotsEvents Interface
 */
@IID("{06E0C937-6AC1-4C34-9254-9F9882F9A5BB}")
public abstract class _IRNRobotsEvents {
  // Methods:
  /**
   * <p>
   * Occurs when in change is made to the name or structure of this object or its ancestry.
   * </p>
   */

  @DISPID(1)
  public void onOrganizationChange() {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs when the connection status changes on a robot that is a descendant of this object.
   * </p>
   * @param pathName Mandatory java.lang.String parameter.
   * @param newStatus Mandatory com.github.wshackle.fanuc.robotneighborhood.FRERNConnectionStatusConstants parameter.
   */

  @DISPID(2)
  public void onRobotConnectionStatusChange(
    java.lang.String pathName,
    com.github.wshackle.fanuc.robotneighborhood.FRERNConnectionStatusConstants newStatus) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
