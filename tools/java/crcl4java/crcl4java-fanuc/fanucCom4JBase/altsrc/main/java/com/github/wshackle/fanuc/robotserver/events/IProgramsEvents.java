package com.github.wshackle.fanuc.robotserver.events;

import com4j.*;

@IID("{BB944831-DB7C-11D0-A083-00A02436CF7E}")
public abstract class IProgramsEvents {
  // Methods:
  /**
   * <p>
   * Occurs after a program is created or loaded on the controller.
   * </p>
   * @param program Mandatory com.github.wshackle.fanuc.robotserver.IProgram parameter.
   */

  @DISPID(1)
  public void create(
    com.github.wshackle.fanuc.robotserver.IProgram program) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after a program is deleted.
   * </p>
   * @param program Mandatory com.github.wshackle.fanuc.robotserver.IProgram parameter.
   */

  @DISPID(2)
  public void delete(
    com.github.wshackle.fanuc.robotserver.IProgram program) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after an attribute is changed.
   * </p>
   * @param program Mandatory com.github.wshackle.fanuc.robotserver.IProgram parameter.
   * @param attr Mandatory com.github.wshackle.fanuc.robotserver.FREAttributeConstants parameter.
   */

  @DISPID(3)
  public void attrChange(
    com.github.wshackle.fanuc.robotserver.IProgram program,
    com.github.wshackle.fanuc.robotserver.FREAttributeConstants attr) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after a program is selected.
   * </p>
   */

  @DISPID(4)
  public void select() {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after a program is renamed.
   * </p>
   * @param program Mandatory com.github.wshackle.fanuc.robotserver.IProgram parameter.
   */

  @DISPID(5)
  public void rename(
    com.github.wshackle.fanuc.robotserver.IProgram program) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after a TP or KAREL program’s subtype is changed.
   * </p>
   * @param program Mandatory com.github.wshackle.fanuc.robotserver.ITPProgram parameter.
   */

  @DISPID(6)
  public void subTypeChange(
    com.github.wshackle.fanuc.robotserver.ITPProgram program) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after a TP program is reloaded.
   * </p>
   * @param program Mandatory com.github.wshackle.fanuc.robotserver.IProgram parameter.
   */

  @DISPID(7)
  public void refresh(
    com.github.wshackle.fanuc.robotserver.IProgram program) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after a program’s variables are reloaded.
   * </p>
   * @param program Mandatory com.github.wshackle.fanuc.robotserver.IProgram parameter.
   */

  @DISPID(8)
  public void refreshVars(
    com.github.wshackle.fanuc.robotserver.IProgram program) {
        throw new UnsupportedOperationException();
  }


  /**
   * <p>
   * Occurs after a TP or KAREL program’s subtype is changed.
   * </p>
   * @param program Mandatory com.github.wshackle.fanuc.robotserver.IProgram parameter.
   */

  @DISPID(9)
  public void subTypeChange2(
    com.github.wshackle.fanuc.robotserver.IProgram program) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
