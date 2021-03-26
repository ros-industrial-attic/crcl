package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Represents a KAREL program on the robot controller.  
 */
@IID("{73FF06C4-7178-11D1-B762-00C04FBBE42A}")
public interface IKARELProgram extends com.github.wshackle.fanuc.robotserver.IProgram {
  // Methods:
  /**
   * <p>
   * Runs the KAREL program by creating a task for the program.
   * </p>
   * @param stepType Optional parameter. Default value is com4j.Variant.getMissing()
   * @param lineNum Optional parameter. Default value is com4j.Variant.getMissing()
   * @param direction Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(250) //= 0xfa. The runtime will prefer the VTID if present
  @VTID(42)
  void run(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object stepType,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object lineNum,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object direction);


  /**
   * <p>
   * Returns the sub-type of the program (none or macro).
   * </p>
   * <p>
   * Getter method for the COM property "SubType"
   * </p>
   * @return  Returns a value of type com.github.wshackle.fanuc.robotserver.FRETPSubTypeConstants
   */

  @DISPID(203) //= 0xcb. The runtime will prefer the VTID if present
  @VTID(43)
  com.github.wshackle.fanuc.robotserver.FRETPSubTypeConstants subType();


  // Properties:
}
