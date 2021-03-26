package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{10CB2FD0-DCDC-11D0-A083-00A02436CF7E}")
public abstract class ITPPositionsEvents {
  // Methods:
  /**
   * <p>
   * Occurs after a TP position is created.
   * </p>
   * @param position Mandatory com.github.wshackle.fanuc.robotserver.ITPPosition parameter.
   */

  @DISPID(1)
  public void create(
    com.github.wshackle.fanuc.robotserver.ITPPosition position) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after a TP position is changed, Position specifies which position was changed.
   * </p>
   * @param position Mandatory com.github.wshackle.fanuc.robotserver.ITPPosition parameter.
   * @param groupNum Mandatory short parameter.
   */

  @DISPID(2)
  public void change(
    com.github.wshackle.fanuc.robotserver.ITPPosition position,
    short groupNum) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs before a TP position is deleted.
   * </p>
   * @param position Mandatory com.github.wshackle.fanuc.robotserver.ITPPosition parameter.
   */

  @DISPID(3)
  public void delete(
    com.github.wshackle.fanuc.robotserver.ITPPosition position) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after a TP position is renumbered.
   * </p>
   * @param position Mandatory com.github.wshackle.fanuc.robotserver.ITPPosition parameter.
   */

  @DISPID(4)
  public void renumber(
    com.github.wshackle.fanuc.robotserver.ITPPosition position) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs before RenumberAll is started.
   * </p>
   */

  @DISPID(5)
  public void beginRenumberAll() {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after RenumberAll is done.
   * </p>
   */

  @DISPID(6)
  public void endRenumberAll() {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
