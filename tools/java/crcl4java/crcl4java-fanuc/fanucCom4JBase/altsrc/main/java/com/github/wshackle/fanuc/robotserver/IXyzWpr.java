package com.github.wshackle.fanuc.robotserver  ;

import com4j.*;

/**
 * Represents the position of a single group of axes consisting of three real components specifying a Cartesian location (x,y,z), three real components specifying an orientation (w,p,r), and a component specifying a configuration.
 */
@IID("{A47A5884-056D-11D0-8901-0020AF68F0A3}")
public interface IXyzWpr extends com.github.wshackle.fanuc.robotserver.ICartesianFormat {
  // Methods:
  /**
   * <p>
   * Returns/sets the X component of the location vector.
   * </p>
   * <p>
   * Getter method for the COM property "X"
   * </p>
   * @return  Returns a value of type double
   */

  @DISPID(301) //= 0x12d. The runtime will prefer the VTID if present
  @VTID(12)
  double x();


  /**
   * <p>
   * Returns/sets the X component of the location vector.
   * </p>
   * <p>
   * Setter method for the COM property "X"
   * </p>
   * @param x Mandatory double parameter.
   */

  @DISPID(301) //= 0x12d. The runtime will prefer the VTID if present
  @VTID(13)
  void x(
    double x);


  /**
   * <p>
   * Returns/sets the Y component of the location vector.
   * </p>
   * <p>
   * Getter method for the COM property "Y"
   * </p>
   * @return  Returns a value of type double
   */

  @DISPID(302) //= 0x12e. The runtime will prefer the VTID if present
  @VTID(14)
  double y();


  /**
   * <p>
   * Returns/sets the Y component of the location vector.
   * </p>
   * <p>
   * Setter method for the COM property "Y"
   * </p>
   * @param y Mandatory double parameter.
   */

  @DISPID(302) //= 0x12e. The runtime will prefer the VTID if present
  @VTID(15)
  void y(
    double y);


  /**
   * <p>
   * Returns/sets the Z component of the location vector.
   * </p>
   * <p>
   * Getter method for the COM property "Z"
   * </p>
   * @return  Returns a value of type double
   */

  @DISPID(303) //= 0x12f. The runtime will prefer the VTID if present
  @VTID(16)
  double z();


  /**
   * <p>
   * Returns/sets the Z component of the location vector.
   * </p>
   * <p>
   * Setter method for the COM property "Z"
   * </p>
   * @param z Mandatory double parameter.
   */

  @DISPID(303) //= 0x12f. The runtime will prefer the VTID if present
  @VTID(17)
  void z(
    double z);


  /**
   * <p>
   * Returns/sets the W component of the orientation vector.
   * </p>
   * <p>
   * Getter method for the COM property "W"
   * </p>
   * @return  Returns a value of type double
   */

  @DISPID(304) //= 0x130. The runtime will prefer the VTID if present
  @VTID(18)
  double w();


  /**
   * <p>
   * Returns/sets the W component of the orientation vector.
   * </p>
   * <p>
   * Setter method for the COM property "W"
   * </p>
   * @param w Mandatory double parameter.
   */

  @DISPID(304) //= 0x130. The runtime will prefer the VTID if present
  @VTID(19)
  void w(
    double w);


  /**
   * <p>
   * Returns/sets the P component of the orientation vector.
   * </p>
   * <p>
   * Getter method for the COM property "P"
   * </p>
   * @return  Returns a value of type double
   */

  @DISPID(305) //= 0x131. The runtime will prefer the VTID if present
  @VTID(20)
  double p();


  /**
   * <p>
   * Returns/sets the P component of the orientation vector.
   * </p>
   * <p>
   * Setter method for the COM property "P"
   * </p>
   * @param p Mandatory double parameter.
   */

  @DISPID(305) //= 0x131. The runtime will prefer the VTID if present
  @VTID(21)
  void p(
    double p);


  /**
   * <p>
   * Returns/sets the R component of the orientation vector.
   * </p>
   * <p>
   * Getter method for the COM property "R"
   * </p>
   * @return  Returns a value of type double
   */

  @DISPID(306) //= 0x132. The runtime will prefer the VTID if present
  @VTID(22)
  double r();


  /**
   * <p>
   * Returns/sets the R component of the orientation vector.
   * </p>
   * <p>
   * Setter method for the COM property "R"
   * </p>
   * @param r Mandatory double parameter.
   */

  @DISPID(306) //= 0x132. The runtime will prefer the VTID if present
  @VTID(23)
  void r(
    double r);


  /**
   * <p>
   * Returns the location and orientation values in one call.
   * </p>
   * @param x Mandatory Holder<Double> parameter.
   * @param y Mandatory Holder<Double> parameter.
   * @param z Mandatory Holder<Double> parameter.
   * @param w Mandatory Holder<Double> parameter.
   * @param p Mandatory Holder<Double> parameter.
   * @param r Mandatory Holder<Double> parameter.
   */

  @DISPID(307) //= 0x133. The runtime will prefer the VTID if present
  @VTID(24)
  void getAll(
    Holder<Double> x,
    Holder<Double> y,
    Holder<Double> z,
    Holder<Double> w,
    Holder<Double> p,
    Holder<Double> r);


  /**
   * <p>
   * Sets the location and orientation values in one call.
   * </p>
   * @param x Mandatory double parameter.
   * @param y Mandatory double parameter.
   * @param z Mandatory double parameter.
   * @param w Mandatory double parameter.
   * @param p Mandatory double parameter.
   * @param r Mandatory double parameter.
   */

  @DISPID(308) //= 0x134. The runtime will prefer the VTID if present
  @VTID(25)
  void setAll(
    double x,
    double y,
    double z,
    double w,
    double p,
    double r);


  // Properties:
}
