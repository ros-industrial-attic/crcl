package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{B23F3D71-F86A-11D0-A093-00A02436CF7E}")
public abstract class IIOTypesEvents {
  // Methods:
  /**
   * <p>
   * Occurs after a User I/O is created.
   * </p>
   * @param userIOType Mandatory com.github.wshackle.fanuc.robotserver.IUserIOType parameter.
   */

  @DISPID(1)
  public void create(
    com.github.wshackle.fanuc.robotserver.IUserIOType userIOType) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs before a User I/O is deleted.
   * </p>
   * @param userIOType Mandatory com.github.wshackle.fanuc.robotserver.IUserIOType parameter.
   */

  @DISPID(2)
  public void delete(
    com.github.wshackle.fanuc.robotserver.IUserIOType userIOType) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after all the I/O types have been updated.
   * </p>
   */

  @DISPID(3)
  public void refresh() {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
