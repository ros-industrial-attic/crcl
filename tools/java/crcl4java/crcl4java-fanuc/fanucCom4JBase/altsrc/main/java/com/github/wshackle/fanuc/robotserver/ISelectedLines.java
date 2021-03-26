package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

@IID("{12585150-9394-11D2-877C-00C04FB9C401}")
public interface ISelectedLines extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(8)
  int count();


  /**
   * <p>
   * Getter method for the COM property "IsSelected"
   * </p>
   * @param lineNum Mandatory int parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(9)
  boolean isSelected(
    int lineNum);


  /**
   * @param startLine Mandatory int parameter.
   * @param endLine Optional parameter. Default value is 0
   */

  @DISPID(251) //= 0xfb. The runtime will prefer the VTID if present
  @VTID(10)
  void add(
    int startLine,
    @Optional int endLine);


  /**
   */

  @DISPID(252) //= 0xfc. The runtime will prefer the VTID if present
  @VTID(11)
  void copy();


  /**
   */

  @DISPID(253) //= 0xfd. The runtime will prefer the VTID if present
  @VTID(12)
  void cut();


  /**
   * @param lineNum Mandatory int parameter.
   * @param pasteType Mandatory int parameter.
   */

  @DISPID(254) //= 0xfe. The runtime will prefer the VTID if present
  @VTID(13)
  void paste(
    int lineNum,
    int pasteType);


  /**
   * @param lineNum Mandatory int parameter.
   */

  @DISPID(255) //= 0xff. The runtime will prefer the VTID if present
  @VTID(14)
  void remove(
    int lineNum);


  /**
   */

  @DISPID(256) //= 0x100. The runtime will prefer the VTID if present
  @VTID(15)
  void removeAll();


  // Properties:
}
