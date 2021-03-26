package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

@IID("{DEE5EAE0-E283-11D0-8BB6-0020AF39BE5A}")
public interface ITPScreen extends com.github.wshackle.fanuc.robotserver.IRobotObject {
  // Methods:
  /**
   * @param softpartID Mandatory Holder<Integer> parameter.
   * @param screenID Mandatory Holder<Integer> parameter.
   * @param title Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(8)
  void getCurScreen(
    Holder<Integer> softpartID,
    Holder<Integer> screenID,
    Holder<java.lang.String> title);


  /**
   * @param keys Mandatory java.lang.Object parameter.
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(9)
  void simKeys(
    @MarshalAs(NativeType.VARIANT) java.lang.Object keys);


  /**
   * @param softpart Mandatory int parameter.
   * @param screenID Mandatory int parameter.
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(10)
  void forceMenu(
    int softpart,
    int screenID);


  /**
   * @param tpConnID Mandatory int parameter.
   * @param url Mandatory java.lang.String parameter.
   */

  @DISPID(104) //= 0x68. The runtime will prefer the VTID if present
  @VTID(11)
  void tpLinkExecUrl(
    int tpConnID,
    java.lang.String url);


  // Properties:
}
