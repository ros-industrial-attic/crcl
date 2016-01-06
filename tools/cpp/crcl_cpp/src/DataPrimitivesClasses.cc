/*********************************************************************/

#include <stdio.h>             // for printf, etc.
#include <string.h>            // for strdup
#include <stdlib.h>            // for exit
#include <list>
#include <boost/regex.hpp>
#include <xml_parser_generator/xmlSchemaInstance.hh>
#include "crcl_cpp/DataPrimitivesClasses.hh"

#define INDENT 2

/*********************************************************************/
/*********************************************************************/

/* class AngleUnitEnumType

*/

AngleUnitEnumType::AngleUnitEnumType() :
  XmlNMTOKEN() {}

AngleUnitEnumType::AngleUnitEnumType(
 const char * valIn) :
  XmlNMTOKEN(valIn)
{
  if (!bad)
    bad = (strcmp(val.c_str(), "degree") &&
           strcmp(val.c_str(), "radian"));
}

AngleUnitEnumType::~AngleUnitEnumType() {}

bool AngleUnitEnumType::AngleUnitEnumTypeIsBad()
{
  return (strcmp(val.c_str(), "degree") &&
          strcmp(val.c_str(), "radian"));
}

void AngleUnitEnumType::PRINTSELFDECL
{
  if (AngleUnitEnumTypeIsBad())
    {
      fprintf(stderr, "bad AngleUnitEnumType value, ");
      XmlNMTOKEN::printBad(stderr);
      fprintf(stderr, " exiting\n");
      exit(1);
    }
  XmlNMTOKEN::PRINTSELF;
}

/*********************************************************************/

/* class DataThingType

*/

DataThingType::DataThingType()
{
  Name = 0;
}

DataThingType::DataThingType(
 XmlID * NameIn)
{
  Name = NameIn;
}

DataThingType::~DataThingType()
{
  delete Name;
}

void DataThingType::PRINTSELFDECL
{
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  SPACESMINUS;
}

/*********************************************************************/

/* class ForceUnitEnumType

*/

ForceUnitEnumType::ForceUnitEnumType() :
  XmlNMTOKEN() {}

ForceUnitEnumType::ForceUnitEnumType(
 const char * valIn) :
  XmlNMTOKEN(valIn)
{
  if (!bad)
    bad = (strcmp(val.c_str(), "newton") &&
           strcmp(val.c_str(), "pound") &&
           strcmp(val.c_str(), "ounce"));
}

ForceUnitEnumType::~ForceUnitEnumType() {}

bool ForceUnitEnumType::ForceUnitEnumTypeIsBad()
{
  return (strcmp(val.c_str(), "newton") &&
          strcmp(val.c_str(), "pound") &&
          strcmp(val.c_str(), "ounce"));
}

void ForceUnitEnumType::PRINTSELFDECL
{
  if (ForceUnitEnumTypeIsBad())
    {
      fprintf(stderr, "bad ForceUnitEnumType value, ");
      XmlNMTOKEN::printBad(stderr);
      fprintf(stderr, " exiting\n");
      exit(1);
    }
  XmlNMTOKEN::PRINTSELF;
}

/*********************************************************************/

/* class FractionType

*/

FractionType::FractionType() :
  XmlDecimal() {}

FractionType::FractionType(
 const char * valIn) : XmlDecimal(valIn)
{
  if (!bad)
    bad = ((val < 0.0) ||
          (val > 1.0));
}

FractionType::~FractionType() {}

bool FractionType::FractionTypeIsBad()
{
  if (XmlDecimalIsBad())
    return true;
  return ((val < 0.0) ||
          (val > 1.0));
}

void FractionType::PRINTSELFDECL
{
  if (FractionTypeIsBad())
    {
      fprintf(stderr, "bad FractionType value, ");
      XmlDecimal::printBad(stderr);
      fprintf(stderr, " exiting\n");
      exit(1);
    }
  XmlDecimal::PRINTSELF;
}

/*********************************************************************/

/* class LengthUnitEnumType

*/

LengthUnitEnumType::LengthUnitEnumType() :
  XmlNMTOKEN() {}

LengthUnitEnumType::LengthUnitEnumType(
 const char * valIn) :
  XmlNMTOKEN(valIn)
{
  if (!bad)
    bad = (strcmp(val.c_str(), "meter") &&
           strcmp(val.c_str(), "millimeter") &&
           strcmp(val.c_str(), "inch"));
}

LengthUnitEnumType::~LengthUnitEnumType() {}

bool LengthUnitEnumType::LengthUnitEnumTypeIsBad()
{
  return (strcmp(val.c_str(), "meter") &&
          strcmp(val.c_str(), "millimeter") &&
          strcmp(val.c_str(), "inch"));
}

void LengthUnitEnumType::PRINTSELFDECL
{
  if (LengthUnitEnumTypeIsBad())
    {
      fprintf(stderr, "bad LengthUnitEnumType value, ");
      XmlNMTOKEN::printBad(stderr);
      fprintf(stderr, " exiting\n");
      exit(1);
    }
  XmlNMTOKEN::PRINTSELF;
}

/*********************************************************************/

/* class PhysicalLocationType

*/

PhysicalLocationType::PhysicalLocationType() :
  DataThingType()
{
  RefObjectName = 0;
  Timestamp = 0;
}

PhysicalLocationType::PhysicalLocationType(
 XmlID * NameIn,
 XmlIDREF * RefObjectNameIn,
 XmlDateTime * TimestampIn) :
  DataThingType(
    NameIn)
{
  RefObjectName = RefObjectNameIn;
  Timestamp = TimestampIn;
  printTypp = false;
}

PhysicalLocationType::~PhysicalLocationType()
{
  delete RefObjectName;
  delete Timestamp;
}

void PhysicalLocationType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"PhysicalLocationType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  if (RefObjectName)
    {
      SPACESZERO;
      XFPRINTF "<RefObjectName>");
      RefObjectName->PRINTSELF;
      XFPRINTF "</RefObjectName>\n");
    }
  if (Timestamp)
    {
      SPACESZERO;
      XFPRINTF "<Timestamp>");
      Timestamp->PRINTSELF;
      XFPRINTF "</Timestamp>\n");
    }
  SPACESMINUS;
}

/*********************************************************************/

/* class PointType

*/

PointType::PointType() :
  DataThingType()
{
  X = 0;
  Y = 0;
  Z = 0;
}

PointType::PointType(
 XmlID * NameIn,
 XmlDecimal * XIn,
 XmlDecimal * YIn,
 XmlDecimal * ZIn) :
  DataThingType(
    NameIn)
{
  X = XIn;
  Y = YIn;
  Z = ZIn;
  printTypp = false;
}

PointType::~PointType()
{
  delete X;
  delete Y;
  delete Z;
}

void PointType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"PointType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  SPACESZERO;
  XFPRINTF "<X>");
  X->PRINTSELF;
  XFPRINTF "</X>\n");
  SPACESZERO;
  XFPRINTF "<Y>");
  Y->PRINTSELF;
  XFPRINTF "</Y>\n");
  SPACESZERO;
  XFPRINTF "<Z>");
  Z->PRINTSELF;
  XFPRINTF "</Z>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class PoseLocationInType

*/

PoseLocationInType::PoseLocationInType() :
  PoseLocationType() {}

PoseLocationInType::PoseLocationInType(
 XmlID * NameIn,
 XmlIDREF * RefObjectNameIn,
 XmlDateTime * TimestampIn,
 PointType * PointIn,
 VectorType * XAxisIn,
 VectorType * ZAxisIn,
 PositiveDecimalType * PositionStandardDeviationIn,
 PositiveDecimalType * OrientationStandardDeviationIn) :
  PoseLocationType(
    NameIn,
    RefObjectNameIn,
    TimestampIn,
    PointIn,
    XAxisIn,
    ZAxisIn,
    PositionStandardDeviationIn,
    OrientationStandardDeviationIn)
{
  printTypp = false;
}

PoseLocationInType::~PoseLocationInType() {}

void PoseLocationInType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"PoseLocationInType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  if (RefObjectName)
    {
      SPACESZERO;
      XFPRINTF "<RefObjectName>");
      RefObjectName->PRINTSELF;
      XFPRINTF "</RefObjectName>\n");
    }
  if (Timestamp)
    {
      SPACESZERO;
      XFPRINTF "<Timestamp>");
      Timestamp->PRINTSELF;
      XFPRINTF "</Timestamp>\n");
    }
  SPACESZERO;
  XFPRINTF "<Point");
  Point->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</Point>\n");
  SPACESZERO;
  XFPRINTF "<XAxis");
  XAxis->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</XAxis>\n");
  SPACESZERO;
  XFPRINTF "<ZAxis");
  ZAxis->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</ZAxis>\n");
  if (PositionStandardDeviation)
    {
      SPACESZERO;
      XFPRINTF "<PositionStandardDeviation>");
      PositionStandardDeviation->PRINTSELF;
      XFPRINTF "</PositionStandardDeviation>\n");
    }
  if (OrientationStandardDeviation)
    {
      SPACESZERO;
      XFPRINTF "<OrientationStandardDeviation>");
      OrientationStandardDeviation->PRINTSELF;
      XFPRINTF "</OrientationStandardDeviation>\n");
    }
  SPACESMINUS;
}

/*********************************************************************/

/* class PoseLocationOnType

*/

PoseLocationOnType::PoseLocationOnType() :
  PoseLocationType() {}

PoseLocationOnType::PoseLocationOnType(
 XmlID * NameIn,
 XmlIDREF * RefObjectNameIn,
 XmlDateTime * TimestampIn,
 PointType * PointIn,
 VectorType * XAxisIn,
 VectorType * ZAxisIn,
 PositiveDecimalType * PositionStandardDeviationIn,
 PositiveDecimalType * OrientationStandardDeviationIn) :
  PoseLocationType(
    NameIn,
    RefObjectNameIn,
    TimestampIn,
    PointIn,
    XAxisIn,
    ZAxisIn,
    PositionStandardDeviationIn,
    OrientationStandardDeviationIn)
{
  printTypp = false;
}

PoseLocationOnType::~PoseLocationOnType() {}

void PoseLocationOnType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"PoseLocationOnType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  if (RefObjectName)
    {
      SPACESZERO;
      XFPRINTF "<RefObjectName>");
      RefObjectName->PRINTSELF;
      XFPRINTF "</RefObjectName>\n");
    }
  if (Timestamp)
    {
      SPACESZERO;
      XFPRINTF "<Timestamp>");
      Timestamp->PRINTSELF;
      XFPRINTF "</Timestamp>\n");
    }
  SPACESZERO;
  XFPRINTF "<Point");
  Point->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</Point>\n");
  SPACESZERO;
  XFPRINTF "<XAxis");
  XAxis->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</XAxis>\n");
  SPACESZERO;
  XFPRINTF "<ZAxis");
  ZAxis->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</ZAxis>\n");
  if (PositionStandardDeviation)
    {
      SPACESZERO;
      XFPRINTF "<PositionStandardDeviation>");
      PositionStandardDeviation->PRINTSELF;
      XFPRINTF "</PositionStandardDeviation>\n");
    }
  if (OrientationStandardDeviation)
    {
      SPACESZERO;
      XFPRINTF "<OrientationStandardDeviation>");
      OrientationStandardDeviation->PRINTSELF;
      XFPRINTF "</OrientationStandardDeviation>\n");
    }
  SPACESMINUS;
}

/*********************************************************************/

/* class PoseLocationType

*/

PoseLocationType::PoseLocationType() :
  PhysicalLocationType()
{
  Point = 0;
  XAxis = 0;
  ZAxis = 0;
  PositionStandardDeviation = 0;
  OrientationStandardDeviation = 0;
}

PoseLocationType::PoseLocationType(
 XmlID * NameIn,
 XmlIDREF * RefObjectNameIn,
 XmlDateTime * TimestampIn,
 PointType * PointIn,
 VectorType * XAxisIn,
 VectorType * ZAxisIn,
 PositiveDecimalType * PositionStandardDeviationIn,
 PositiveDecimalType * OrientationStandardDeviationIn) :
  PhysicalLocationType(
    NameIn,
    RefObjectNameIn,
    TimestampIn)
{
  Point = PointIn;
  XAxis = XAxisIn;
  ZAxis = ZAxisIn;
  PositionStandardDeviation = PositionStandardDeviationIn;
  OrientationStandardDeviation = OrientationStandardDeviationIn;
  printTypp = false;
}

PoseLocationType::~PoseLocationType()
{
  delete Point;
  delete XAxis;
  delete ZAxis;
  delete PositionStandardDeviation;
  delete OrientationStandardDeviation;
}

void PoseLocationType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"PoseLocationType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  if (RefObjectName)
    {
      SPACESZERO;
      XFPRINTF "<RefObjectName>");
      RefObjectName->PRINTSELF;
      XFPRINTF "</RefObjectName>\n");
    }
  if (Timestamp)
    {
      SPACESZERO;
      XFPRINTF "<Timestamp>");
      Timestamp->PRINTSELF;
      XFPRINTF "</Timestamp>\n");
    }
  SPACESZERO;
  XFPRINTF "<Point");
  Point->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</Point>\n");
  SPACESZERO;
  XFPRINTF "<XAxis");
  XAxis->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</XAxis>\n");
  SPACESZERO;
  XFPRINTF "<ZAxis");
  ZAxis->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</ZAxis>\n");
  if (PositionStandardDeviation)
    {
      SPACESZERO;
      XFPRINTF "<PositionStandardDeviation>");
      PositionStandardDeviation->PRINTSELF;
      XFPRINTF "</PositionStandardDeviation>\n");
    }
  if (OrientationStandardDeviation)
    {
      SPACESZERO;
      XFPRINTF "<OrientationStandardDeviation>");
      OrientationStandardDeviation->PRINTSELF;
      XFPRINTF "</OrientationStandardDeviation>\n");
    }
  SPACESMINUS;
}

/*********************************************************************/

/* class PoseOnlyLocationType

*/

PoseOnlyLocationType::PoseOnlyLocationType() :
  PoseLocationType() {}

PoseOnlyLocationType::PoseOnlyLocationType(
 XmlID * NameIn,
 XmlIDREF * RefObjectNameIn,
 XmlDateTime * TimestampIn,
 PointType * PointIn,
 VectorType * XAxisIn,
 VectorType * ZAxisIn,
 PositiveDecimalType * PositionStandardDeviationIn,
 PositiveDecimalType * OrientationStandardDeviationIn) :
  PoseLocationType(
    NameIn,
    RefObjectNameIn,
    TimestampIn,
    PointIn,
    XAxisIn,
    ZAxisIn,
    PositionStandardDeviationIn,
    OrientationStandardDeviationIn)
{
  printTypp = false;
}

PoseOnlyLocationType::~PoseOnlyLocationType() {}

void PoseOnlyLocationType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"PoseOnlyLocationType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  if (RefObjectName)
    {
      SPACESZERO;
      XFPRINTF "<RefObjectName>");
      RefObjectName->PRINTSELF;
      XFPRINTF "</RefObjectName>\n");
    }
  if (Timestamp)
    {
      SPACESZERO;
      XFPRINTF "<Timestamp>");
      Timestamp->PRINTSELF;
      XFPRINTF "</Timestamp>\n");
    }
  SPACESZERO;
  XFPRINTF "<Point");
  Point->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</Point>\n");
  SPACESZERO;
  XFPRINTF "<XAxis");
  XAxis->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</XAxis>\n");
  SPACESZERO;
  XFPRINTF "<ZAxis");
  ZAxis->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</ZAxis>\n");
  if (PositionStandardDeviation)
    {
      SPACESZERO;
      XFPRINTF "<PositionStandardDeviation>");
      PositionStandardDeviation->PRINTSELF;
      XFPRINTF "</PositionStandardDeviation>\n");
    }
  if (OrientationStandardDeviation)
    {
      SPACESZERO;
      XFPRINTF "<OrientationStandardDeviation>");
      OrientationStandardDeviation->PRINTSELF;
      XFPRINTF "</OrientationStandardDeviation>\n");
    }
  SPACESMINUS;
}

/*********************************************************************/

/* class PositiveDecimalType

*/

PositiveDecimalType::PositiveDecimalType() :
  XmlDecimal() {}

PositiveDecimalType::PositiveDecimalType(
 const char * valIn) : XmlDecimal(valIn)
{
  if (!bad)
    bad = ((val <= 0));
}

PositiveDecimalType::~PositiveDecimalType() {}

bool PositiveDecimalType::PositiveDecimalTypeIsBad()
{
  if (XmlDecimalIsBad())
    return true;
  return ((val <= 0));
}

void PositiveDecimalType::PRINTSELFDECL
{
  if (PositiveDecimalTypeIsBad())
    {
      fprintf(stderr, "bad PositiveDecimalType value, ");
      XmlDecimal::printBad(stderr);
      fprintf(stderr, " exiting\n");
      exit(1);
    }
  XmlDecimal::PRINTSELF;
}

/*********************************************************************/

/* class RegionOfInterestType

*/

RegionOfInterestType::RegionOfInterestType() :
  DataThingType()
{
  RefObjectName = 0;
  UpperLeft = 0;
  LowerRight = 0;
}

RegionOfInterestType::RegionOfInterestType(
 XmlID * NameIn,
 XmlIDREF * RefObjectNameIn,
 PointType * UpperLeftIn,
 PointType * LowerRightIn) :
  DataThingType(
    NameIn)
{
  RefObjectName = RefObjectNameIn;
  UpperLeft = UpperLeftIn;
  LowerRight = LowerRightIn;
  printTypp = false;
}

RegionOfInterestType::~RegionOfInterestType()
{
  delete RefObjectName;
  delete UpperLeft;
  delete LowerRight;
}

void RegionOfInterestType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"RegionOfInterestType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  SPACESZERO;
  XFPRINTF "<RefObjectName>");
  RefObjectName->PRINTSELF;
  XFPRINTF "</RefObjectName>\n");
  SPACESZERO;
  XFPRINTF "<UpperLeft");
  UpperLeft->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</UpperLeft>\n");
  SPACESZERO;
  XFPRINTF "<LowerRight");
  LowerRight->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</LowerRight>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class RelativeLocationInType

*/

RelativeLocationInType::RelativeLocationInType() :
  RelativeLocationType() {}

RelativeLocationInType::RelativeLocationInType(
 XmlID * NameIn,
 XmlIDREF * RefObjectNameIn,
 XmlDateTime * TimestampIn,
 XmlString * DescriptionIn) :
  RelativeLocationType(
    NameIn,
    RefObjectNameIn,
    TimestampIn,
    DescriptionIn)
{
  printTypp = false;
}

RelativeLocationInType::~RelativeLocationInType() {}

void RelativeLocationInType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"RelativeLocationInType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  if (RefObjectName)
    {
      SPACESZERO;
      XFPRINTF "<RefObjectName>");
      RefObjectName->PRINTSELF;
      XFPRINTF "</RefObjectName>\n");
    }
  if (Timestamp)
    {
      SPACESZERO;
      XFPRINTF "<Timestamp>");
      Timestamp->PRINTSELF;
      XFPRINTF "</Timestamp>\n");
    }
  SPACESZERO;
  XFPRINTF "<Description>");
  Description->PRINTSELF;
  XFPRINTF "</Description>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class RelativeLocationOnType

*/

RelativeLocationOnType::RelativeLocationOnType() :
  RelativeLocationType() {}

RelativeLocationOnType::RelativeLocationOnType(
 XmlID * NameIn,
 XmlIDREF * RefObjectNameIn,
 XmlDateTime * TimestampIn,
 XmlString * DescriptionIn) :
  RelativeLocationType(
    NameIn,
    RefObjectNameIn,
    TimestampIn,
    DescriptionIn)
{
  printTypp = false;
}

RelativeLocationOnType::~RelativeLocationOnType() {}

void RelativeLocationOnType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"RelativeLocationOnType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  if (RefObjectName)
    {
      SPACESZERO;
      XFPRINTF "<RefObjectName>");
      RefObjectName->PRINTSELF;
      XFPRINTF "</RefObjectName>\n");
    }
  if (Timestamp)
    {
      SPACESZERO;
      XFPRINTF "<Timestamp>");
      Timestamp->PRINTSELF;
      XFPRINTF "</Timestamp>\n");
    }
  SPACESZERO;
  XFPRINTF "<Description>");
  Description->PRINTSELF;
  XFPRINTF "</Description>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class RelativeLocationType

*/

RelativeLocationType::RelativeLocationType() :
  PhysicalLocationType()
{
  Description = 0;
}

RelativeLocationType::RelativeLocationType(
 XmlID * NameIn,
 XmlIDREF * RefObjectNameIn,
 XmlDateTime * TimestampIn,
 XmlString * DescriptionIn) :
  PhysicalLocationType(
    NameIn,
    RefObjectNameIn,
    TimestampIn)
{
  Description = DescriptionIn;
  printTypp = false;
}

RelativeLocationType::~RelativeLocationType()
{
  delete Description;
}

void RelativeLocationType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"RelativeLocationType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  if (RefObjectName)
    {
      SPACESZERO;
      XFPRINTF "<RefObjectName>");
      RefObjectName->PRINTSELF;
      XFPRINTF "</RefObjectName>\n");
    }
  if (Timestamp)
    {
      SPACESZERO;
      XFPRINTF "<Timestamp>");
      Timestamp->PRINTSELF;
      XFPRINTF "</Timestamp>\n");
    }
  SPACESZERO;
  XFPRINTF "<Description>");
  Description->PRINTSELF;
  XFPRINTF "</Description>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class TorqueUnitEnumType

*/

TorqueUnitEnumType::TorqueUnitEnumType() :
  XmlNMTOKEN() {}

TorqueUnitEnumType::TorqueUnitEnumType(
 const char * valIn) :
  XmlNMTOKEN(valIn)
{
  if (!bad)
    bad = (strcmp(val.c_str(), "newtonMeter") &&
           strcmp(val.c_str(), "footPound"));
}

TorqueUnitEnumType::~TorqueUnitEnumType() {}

bool TorqueUnitEnumType::TorqueUnitEnumTypeIsBad()
{
  return (strcmp(val.c_str(), "newtonMeter") &&
          strcmp(val.c_str(), "footPound"));
}

void TorqueUnitEnumType::PRINTSELFDECL
{
  if (TorqueUnitEnumTypeIsBad())
    {
      fprintf(stderr, "bad TorqueUnitEnumType value, ");
      XmlNMTOKEN::printBad(stderr);
      fprintf(stderr, " exiting\n");
      exit(1);
    }
  XmlNMTOKEN::PRINTSELF;
}

/*********************************************************************/

/* class TwistType

*/

TwistType::TwistType() :
  DataThingType()
{
  LinearVelocity = 0;
  AngularVelocity = 0;
}

TwistType::TwistType(
 XmlID * NameIn,
 VectorType * LinearVelocityIn,
 VectorType * AngularVelocityIn) :
  DataThingType(
    NameIn)
{
  LinearVelocity = LinearVelocityIn;
  AngularVelocity = AngularVelocityIn;
  printTypp = false;
}

TwistType::~TwistType()
{
  delete LinearVelocity;
  delete AngularVelocity;
}

void TwistType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"TwistType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  SPACESZERO;
  XFPRINTF "<LinearVelocity");
  LinearVelocity->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</LinearVelocity>\n");
  SPACESZERO;
  XFPRINTF "<AngularVelocity");
  AngularVelocity->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</AngularVelocity>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class VectorType

*/

VectorType::VectorType() :
  DataThingType()
{
  I = 0;
  J = 0;
  K = 0;
}

VectorType::VectorType(
 XmlID * NameIn,
 XmlDecimal * IIn,
 XmlDecimal * JIn,
 XmlDecimal * KIn) :
  DataThingType(
    NameIn)
{
  I = IIn;
  J = JIn;
  K = KIn;
  printTypp = false;
}

VectorType::~VectorType()
{
  delete I;
  delete J;
  delete K;
}

void VectorType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"VectorType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  SPACESZERO;
  XFPRINTF "<I>");
  I->PRINTSELF;
  XFPRINTF "</I>\n");
  SPACESZERO;
  XFPRINTF "<J>");
  J->PRINTSELF;
  XFPRINTF "</J>\n");
  SPACESZERO;
  XFPRINTF "<K>");
  K->PRINTSELF;
  XFPRINTF "</K>\n");
  SPACESMINUS;
}

/*********************************************************************/

/* class WrenchType

*/

WrenchType::WrenchType() :
  DataThingType()
{
  Force = 0;
  Moment = 0;
}

WrenchType::WrenchType(
 XmlID * NameIn,
 VectorType * ForceIn,
 VectorType * MomentIn) :
  DataThingType(
    NameIn)
{
  Force = ForceIn;
  Moment = MomentIn;
  printTypp = false;
}

WrenchType::~WrenchType()
{
  delete Force;
  delete Moment;
}

void WrenchType::PRINTSELFDECL
{
  if (printTypp)
    XFPRINTF " xsi:type=\"WrenchType\"");
  XFPRINTF ">\n");
  SPACESPLUS;
  if (Name)
    {
      SPACESZERO;
      XFPRINTF "<Name>");
      Name->PRINTSELF;
      XFPRINTF "</Name>\n");
    }
  SPACESZERO;
  XFPRINTF "<Force");
  Force->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</Force>\n");
  SPACESZERO;
  XFPRINTF "<Moment");
  Moment->PRINTSELF;
  SPACESZERO;
  XFPRINTF "</Moment>\n");
  SPACESMINUS;
}

/*********************************************************************/

