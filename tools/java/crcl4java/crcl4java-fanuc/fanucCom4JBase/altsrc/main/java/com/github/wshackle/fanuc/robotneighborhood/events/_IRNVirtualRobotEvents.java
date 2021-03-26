package com.github.wshackle.fanuc.robotneighborhood.events;

import com4j.*;

/**
 * _IRNVirtualRobotEvents Interface
 */
@IID("{9FB97FB3-4F01-49B6-B23A-69E87E0FF2FC}")
public abstract class _IRNVirtualRobotEvents {
  // Methods:
  /**
   * <p>
   * Occurs when a change in a connections status is detected.
   * </p>
   * @param newStatus Mandatory com.github.wshackle.fanuc.robotneighborhood.FRERNConnectionStatusConstants parameter.
   */

  @DISPID(1)
  public void onConnectionStatusChange(
    com.github.wshackle.fanuc.robotneighborhood.FRERNConnectionStatusConstants newStatus) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs when the start mode of the virtual robot changes.
   * </p>
   * @param sequenceNumber Mandatory int parameter.
   */

  @DISPID(2)
  public void onStartSequenceChange(
    int sequenceNumber) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs when the sequence number changes during startup.
   * </p>
   * @param startMode Mandatory com.github.wshackle.fanuc.robotneighborhood.FRERNStartModeConstants parameter.
   */

  @DISPID(3)
  public void onStartModeChange(
    com.github.wshackle.fanuc.robotneighborhood.FRERNStartModeConstants startMode) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs when an asynchronous error from the virtual controller is encountered.
   * </p>
   * @param errorCode Mandatory int parameter.
   * @param description Mandatory java.lang.String parameter.
   */

  @DISPID(4)
  public void onError(
    int errorCode,
    java.lang.String description) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
