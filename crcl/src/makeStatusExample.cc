#include <string.h>
#include "crcl/CRCLStatusClasses.hh"

int main(int argc, char * argv[])
{
  XmlVersion * versionIn;
  XmlHeaderForCRCLStatus * headerIn;
  CRCLStatusType * CRCLStatusIn;
  CRCLStatusFile * CRCLStatusFileIn;
  CommandStatusType * CommandStatusIn;
  JointStatusesType * JointStatusesIn;
  JointStatusType * JointStatus;
  PoseType * PoseIn;
  ParallelGripperStatusType * GripperStatusIn;
#ifdef STRINGOUT
  char statusMessage[10000];
  int * K;
  int k;
#else
  FILE * outFile;
#endif
  versionIn = new XmlVersion(true);
  headerIn = new XmlHeaderForCRCLStatus;
  CommandStatusIn = new CommandStatusType;
  JointStatusesIn = new JointStatusesType(0, new std::list<JointStatusType *>);
  PoseIn = new PoseType;
  GripperStatusIn =
    new ParallelGripperStatusType(0, new XmlID(strdup("jaws")),
				  new XmlDecimal("0.44"));
  GripperStatusIn->printTypp = true;
  CRCLStatusIn = new CRCLStatusType(0, CommandStatusIn, JointStatusesIn,
				    PoseIn, GripperStatusIn);
  CRCLStatusFileIn = new CRCLStatusFile(versionIn, headerIn, CRCLStatusIn);
  headerIn->location =
    new SchemaLocation(strdup("xsi"),
		       strdup("../xmlSchemas/CRCLStatus.xsd"), false);
  CommandStatusIn->Name = 0;
  CommandStatusIn->CommandID =  new XmlPositiveInteger("1");
  CommandStatusIn->StatusID = new XmlPositiveInteger("1");
  CommandStatusIn->CommandState = new CommandStateEnumType(strdup("Working"));
  JointStatus = new JointStatusType(0, new XmlPositiveInteger("1"),
				    new XmlDecimal("30.0"),
				    new XmlDecimal("3.7"), 0);
  JointStatusesIn->JointStatus->push_back(JointStatus);
  JointStatus = new JointStatusType(0, new XmlPositiveInteger("3"),
				    new XmlDecimal("90.0"), 0,
				    new XmlDecimal("0.87"));
  JointStatusesIn->JointStatus->push_back(JointStatus);
  PoseIn->Name = 0;
  PoseIn->Point = new PointType(0, new XmlDecimal("1.5"),
				new XmlDecimal("1"), new XmlDecimal("1"));
  PoseIn->XAxis = new VectorType(0, new XmlDecimal("1"),
				new XmlDecimal("0"), new XmlDecimal("0"));
  PoseIn->ZAxis = new VectorType(0, new XmlDecimal("0"),
				new XmlDecimal("0"), new XmlDecimal("-1"));
#ifdef STRINGOUT
  k = 0;
  K = &k;
  CRCLStatusFileIn->printSelf(statusMessage, K);
  printf(statusMessage);
#else
  outFile = fopen("CRCLStatusFile", "w");
  CRCLStatusFileIn->printSelf(outFile);
  fclose(outFile);
#endif
  return 0;
}
