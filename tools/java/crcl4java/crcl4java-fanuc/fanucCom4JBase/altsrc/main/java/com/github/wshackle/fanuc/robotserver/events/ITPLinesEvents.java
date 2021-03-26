package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{2D9F8871-DCDA-11D0-A083-00A02436CF7E}")
public abstract class ITPLinesEvents {
  // Methods:
  /**
   */

  @DISPID(1)
  public void curChange() {
        throw new UnsupportedOperationException();
  }


  /**
   * @param lineNum Mandatory int parameter.
   */

  @DISPID(2)
  public void create(
    int lineNum) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param lineNum Mandatory int parameter.
   */

  @DISPID(3)
  public void change(
    int lineNum) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param lineNum Mandatory int parameter.
   */

  @DISPID(4)
  public void delete(
    int lineNum) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param lineNum Mandatory int parameter.
   */

  @DISPID(5)
  public void replace(
    int lineNum) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
