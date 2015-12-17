/*********************************************************************/

#ifndef DATAPRIMITIVES_HH
#define DATAPRIMITIVES_HH
#include <stdio.h>
#include <list>
#include <xml_common/xmlSchemaInstance.hh>

/*********************************************************************/

class AngleUnitEnumType;
class DataThingType;
class ForceUnitEnumType;
class FractionType;
class LengthUnitEnumType;
class PhysicalLocationType;
class PointType;
class PoseLocationInType;
class PoseLocationOnType;
class PoseLocationType;
class PoseOnlyLocationType;
class PositiveDecimalType;
class RegionOfInterestType;
class RelativeLocationInType;
class RelativeLocationOnType;
class RelativeLocationType;
class TorqueUnitEnumType;
class TwistType;
class VectorType;
class WrenchType;

/*********************************************************************/
/*********************************************************************/

class AngleUnitEnumType :
  public XmlNMTOKEN
{
public:
  AngleUnitEnumType();
  AngleUnitEnumType(
    const char * valIn);
  ~AngleUnitEnumType();
  bool AngleUnitEnumTypeIsBad();
  void PRINTSELFDECL;
};

/*********************************************************************/

class DataThingType :
  public XmlSchemaInstanceBase
{
public:
  DataThingType();
  DataThingType(
    XmlID * NameIn);
  ~DataThingType();
  void PRINTSELFDECL;

  XmlID * Name;
};

/*********************************************************************/

class ForceUnitEnumType :
  public XmlNMTOKEN
{
public:
  ForceUnitEnumType();
  ForceUnitEnumType(
    const char * valIn);
  ~ForceUnitEnumType();
  bool ForceUnitEnumTypeIsBad();
  void PRINTSELFDECL;
};

/*********************************************************************/

class FractionType :
  public XmlDecimal
{
public:
  FractionType();
  FractionType(
    const char * valIn);
  ~FractionType();
  bool FractionTypeIsBad();
  void PRINTSELFDECL;
};

/*********************************************************************/

class LengthUnitEnumType :
  public XmlNMTOKEN
{
public:
  LengthUnitEnumType();
  LengthUnitEnumType(
    const char * valIn);
  ~LengthUnitEnumType();
  bool LengthUnitEnumTypeIsBad();
  void PRINTSELFDECL;
};

/*********************************************************************/

class PhysicalLocationType :
  public DataThingType
{
public:
  PhysicalLocationType();
  PhysicalLocationType(
    XmlID * NameIn,
    XmlIDREF * RefObjectNameIn,
    XmlDateTime * TimestampIn);
  ~PhysicalLocationType();
  void PRINTSELFDECL;

  XmlIDREF * RefObjectName;
  XmlDateTime * Timestamp;

  bool printTypp;
};

/*********************************************************************/

class PointType :
  public DataThingType
{
public:
  PointType();
  PointType(
    XmlID * NameIn,
    XmlDecimal * XIn,
    XmlDecimal * YIn,
    XmlDecimal * ZIn);
  ~PointType();
  void PRINTSELFDECL;

  XmlDecimal * X;
  XmlDecimal * Y;
  XmlDecimal * Z;

  bool printTypp;
};

/*********************************************************************/

class PoseLocationType :
  public PhysicalLocationType
{
public:
  PoseLocationType();
  PoseLocationType(
    XmlID * NameIn,
    XmlIDREF * RefObjectNameIn,
    XmlDateTime * TimestampIn,
    PointType * PointIn,
    VectorType * XAxisIn,
    VectorType * ZAxisIn,
    PositiveDecimalType * PositionStandardDeviationIn,
    PositiveDecimalType * OrientationStandardDeviationIn);
  ~PoseLocationType();
  void PRINTSELFDECL;

  PointType * Point;
  VectorType * XAxis;
  VectorType * ZAxis;
  PositiveDecimalType * PositionStandardDeviation;
  PositiveDecimalType * OrientationStandardDeviation;

  bool printTypp;
};

/*********************************************************************/

class PoseOnlyLocationType :
  public PoseLocationType
{
public:
  PoseOnlyLocationType();
  PoseOnlyLocationType(
    XmlID * NameIn,
    XmlIDREF * RefObjectNameIn,
    XmlDateTime * TimestampIn,
    PointType * PointIn,
    VectorType * XAxisIn,
    VectorType * ZAxisIn,
    PositiveDecimalType * PositionStandardDeviationIn,
    PositiveDecimalType * OrientationStandardDeviationIn);
  ~PoseOnlyLocationType();
  void PRINTSELFDECL;


  bool printTypp;
};

/*********************************************************************/

class PositiveDecimalType :
  public XmlDecimal
{
public:
  PositiveDecimalType();
  PositiveDecimalType(
    const char * valIn);
  ~PositiveDecimalType();
  bool PositiveDecimalTypeIsBad();
  void PRINTSELFDECL;
};

/*********************************************************************/

class RegionOfInterestType :
  public DataThingType
{
public:
  RegionOfInterestType();
  RegionOfInterestType(
    XmlID * NameIn,
    XmlIDREF * RefObjectNameIn,
    PointType * UpperLeftIn,
    PointType * LowerRightIn);
  ~RegionOfInterestType();
  void PRINTSELFDECL;

  XmlIDREF * RefObjectName;
  PointType * UpperLeft;
  PointType * LowerRight;

  bool printTypp;
};

/*********************************************************************/

class RelativeLocationType :
  public PhysicalLocationType
{
public:
  RelativeLocationType();
  RelativeLocationType(
    XmlID * NameIn,
    XmlIDREF * RefObjectNameIn,
    XmlDateTime * TimestampIn,
    XmlString * DescriptionIn);
  ~RelativeLocationType();
  void PRINTSELFDECL;

  XmlString * Description;

  bool printTypp;
};

/*********************************************************************/

class TorqueUnitEnumType :
  public XmlNMTOKEN
{
public:
  TorqueUnitEnumType();
  TorqueUnitEnumType(
    const char * valIn);
  ~TorqueUnitEnumType();
  bool TorqueUnitEnumTypeIsBad();
  void PRINTSELFDECL;
};

/*********************************************************************/

class TwistType :
  public DataThingType
{
public:
  TwistType();
  TwistType(
    XmlID * NameIn,
    VectorType * LinearVelocityIn,
    VectorType * AngularVelocityIn);
  ~TwistType();
  void PRINTSELFDECL;

  VectorType * LinearVelocity;
  VectorType * AngularVelocity;

  bool printTypp;
};

/*********************************************************************/

class VectorType :
  public DataThingType
{
public:
  VectorType();
  VectorType(
    XmlID * NameIn,
    XmlDecimal * IIn,
    XmlDecimal * JIn,
    XmlDecimal * KIn);
  ~VectorType();
  void PRINTSELFDECL;

  XmlDecimal * I;
  XmlDecimal * J;
  XmlDecimal * K;

  bool printTypp;
};

/*********************************************************************/

class WrenchType :
  public DataThingType
{
public:
  WrenchType();
  WrenchType(
    XmlID * NameIn,
    VectorType * ForceIn,
    VectorType * MomentIn);
  ~WrenchType();
  void PRINTSELFDECL;

  VectorType * Force;
  VectorType * Moment;

  bool printTypp;
};

/*********************************************************************/

class PoseLocationInType :
  public PoseLocationType
{
public:
  PoseLocationInType();
  PoseLocationInType(
    XmlID * NameIn,
    XmlIDREF * RefObjectNameIn,
    XmlDateTime * TimestampIn,
    PointType * PointIn,
    VectorType * XAxisIn,
    VectorType * ZAxisIn,
    PositiveDecimalType * PositionStandardDeviationIn,
    PositiveDecimalType * OrientationStandardDeviationIn);
  ~PoseLocationInType();
  void PRINTSELFDECL;


  bool printTypp;
};

/*********************************************************************/

class PoseLocationOnType :
  public PoseLocationType
{
public:
  PoseLocationOnType();
  PoseLocationOnType(
    XmlID * NameIn,
    XmlIDREF * RefObjectNameIn,
    XmlDateTime * TimestampIn,
    PointType * PointIn,
    VectorType * XAxisIn,
    VectorType * ZAxisIn,
    PositiveDecimalType * PositionStandardDeviationIn,
    PositiveDecimalType * OrientationStandardDeviationIn);
  ~PoseLocationOnType();
  void PRINTSELFDECL;


  bool printTypp;
};

/*********************************************************************/

class RelativeLocationInType :
  public RelativeLocationType
{
public:
  RelativeLocationInType();
  RelativeLocationInType(
    XmlID * NameIn,
    XmlIDREF * RefObjectNameIn,
    XmlDateTime * TimestampIn,
    XmlString * DescriptionIn);
  ~RelativeLocationInType();
  void PRINTSELFDECL;


  bool printTypp;
};

/*********************************************************************/

class RelativeLocationOnType :
  public RelativeLocationType
{
public:
  RelativeLocationOnType();
  RelativeLocationOnType(
    XmlID * NameIn,
    XmlIDREF * RefObjectNameIn,
    XmlDateTime * TimestampIn,
    XmlString * DescriptionIn);
  ~RelativeLocationOnType();
  void PRINTSELFDECL;


  bool printTypp;
};

/*********************************************************************/

#endif // DATAPRIMITIVES_HH
