/*********************************************************************/

#ifndef DATAPRIMITIVES_HH
#define DATAPRIMITIVES_HH
#include <stdio.h>
#include <list>
#include <xml_parser_generator/xmlSchemaInstance.hh>

/*********************************************************************/

class AngleUnitEnumType;
class DataThingType;
class ForceUnitEnumType;
class FractionType;
class LengthUnitEnumType;
class PointType;
class PoseType;
class PositiveDecimalType;
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

class PoseType :
  public DataThingType
{
public:
  PoseType();
  PoseType(
    XmlID * NameIn,
    PointType * PointIn,
    VectorType * XAxisIn,
    VectorType * ZAxisIn);
  ~PoseType();
  void PRINTSELFDECL;

  PointType * Point;
  VectorType * XAxis;
  VectorType * ZAxis;

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

#endif // DATAPRIMITIVES_HH
