%{

/*

This ignores white space outside of meaningful strings of characters.

*/

#ifdef WIN32
#include <io.h>
#define strdup _strdup
#define fileno _fileno
#define isatty _isatty
#define YY_NO_UNISTD_H
#endif
#include <string.h>          // for strdup
#include "crcl_cpp/CRCLStatusClasses.hh"
#include "crcl_cpp/CRCLStatusYACC.hh"    // for tokens, yylval, etc.

#ifndef NO_ECHO
#define ECHO_IT 1
#else
#define ECHO_IT 0
#endif
#define ECH if (ECHO_IT) ECHO

extern int yyReadData;
extern int yyReadDataList;

%}

W [ \t\n\r]*
%x COMMENT
%x DATA
%x DATALIST

%%

  if (yyReadData)
    {
      BEGIN(DATA);
      yyReadData = 0;
    }
  else if (yyReadDataList)
    {
      BEGIN(DATALIST);
    }

{W}"<!--"               { ECH; BEGIN(COMMENT); /* delete comment start */}
<COMMENT>.              { ECH;  /* delete comment middle */ }
<COMMENT>\n             { ECH;  /* delete comment middle */ }
<COMMENT>"-->"          { ECH; BEGIN(INITIAL); /* delete comment end */ }

<DATA>[^<]*             { ECH; BEGIN(INITIAL);
                          yylval.sVal = strdup(yytext);
                          return DATASTRING;
                        }

<DATALIST>[^< \t]*      { ECH;
                          yylval.sVal = strdup(yytext);
                          return DATASTRING;
                        }
<DATALIST>{W}           { ECH;}
<DATALIST>"<"           { yyReadDataList = 0;
                          unput('<');
			  BEGIN(INITIAL);}

"encoding"{W}"="              {ECH; return ENCODING;}
"?>"                          {ECH; return ENDVERSION;}
"<?"                          {ECH; return STARTVERSION;}
"xsi:noNamespaceSchemaLocation"{W}"=" {ECH; return SCHEMALOCATION;}
"xml"[ \t]+"version"{W}"="    {ECH; return XMLVERSION;}

"</"{W}"AngularVelocity"{W}">" {ECH; return ANGULARVELOCITYEND;}
"<"{W}"AngularVelocity"       {ECH; return ANGULARVELOCITYSTART;}
"</"{W}"CRCLStatus"{W}">"     {ECH; return CRCLSTATUSEND;}
"<"{W}"CRCLStatus"            {ECH; return CRCLSTATUSSTART;}
"</"{W}"CommandID"{W}">"      {ECH; return COMMANDIDEND;}
"<"{W}"CommandID"             {ECH; return COMMANDIDSTART;}
"</"{W}"CommandState"{W}">"   {ECH; return COMMANDSTATEEND;}
"<"{W}"CommandState"          {ECH; return COMMANDSTATESTART;}
"</"{W}"CommandStatus"{W}">"  {ECH; return COMMANDSTATUSEND;}
"<"{W}"CommandStatus"         {ECH; return COMMANDSTATUSSTART;}
"</"{W}"Description"{W}">"    {ECH; return DESCRIPTIONEND;}
"<"{W}"Description"           {ECH; return DESCRIPTIONSTART;}
"</"{W}"Finger1Force"{W}">"   {ECH; return FINGER1FORCEEND;}
"<"{W}"Finger1Force"          {ECH; return FINGER1FORCESTART;}
"</"{W}"Finger1Position"{W}">" {ECH; return FINGER1POSITIONEND;}
"<"{W}"Finger1Position"       {ECH; return FINGER1POSITIONSTART;}
"</"{W}"Finger2Force"{W}">"   {ECH; return FINGER2FORCEEND;}
"<"{W}"Finger2Force"          {ECH; return FINGER2FORCESTART;}
"</"{W}"Finger2Position"{W}">" {ECH; return FINGER2POSITIONEND;}
"<"{W}"Finger2Position"       {ECH; return FINGER2POSITIONSTART;}
"</"{W}"Finger3Force"{W}">"   {ECH; return FINGER3FORCEEND;}
"<"{W}"Finger3Force"          {ECH; return FINGER3FORCESTART;}
"</"{W}"Finger3Position"{W}">" {ECH; return FINGER3POSITIONEND;}
"<"{W}"Finger3Position"       {ECH; return FINGER3POSITIONSTART;}
"</"{W}"Force"{W}">"          {ECH; return FORCEEND;}
"<"{W}"Force"                 {ECH; return FORCESTART;}
"</"{W}"GripperName"{W}">"    {ECH; return GRIPPERNAMEEND;}
"<"{W}"GripperName"           {ECH; return GRIPPERNAMESTART;}
"</"{W}"GripperStatus"{W}">"  {ECH; return GRIPPERSTATUSEND;}
"<"{W}"GripperStatus"         {ECH; return GRIPPERSTATUSSTART;}
"</"{W}"I"{W}">"              {ECH; return IEND;}
"<"{W}"I"                     {ECH; return ISTART;}
"</"{W}"IsPowered"{W}">"      {ECH; return ISPOWEREDEND;}
"<"{W}"IsPowered"             {ECH; return ISPOWEREDSTART;}
"</"{W}"J"{W}">"              {ECH; return JEND;}
"<"{W}"J"                     {ECH; return JSTART;}
"</"{W}"JointNumber"{W}">"    {ECH; return JOINTNUMBEREND;}
"<"{W}"JointNumber"           {ECH; return JOINTNUMBERSTART;}
"</"{W}"JointPosition"{W}">"  {ECH; return JOINTPOSITIONEND;}
"<"{W}"JointPosition"         {ECH; return JOINTPOSITIONSTART;}
"</"{W}"JointStatus"{W}">"    {ECH; return JOINTSTATUSEND;}
"<"{W}"JointStatus"           {ECH; return JOINTSTATUSSTART;}
"</"{W}"JointStatuses"{W}">"  {ECH; return JOINTSTATUSESEND;}
"<"{W}"JointStatuses"         {ECH; return JOINTSTATUSESSTART;}
"</"{W}"JointTorqueOrForce"{W}">" {ECH; return JOINTTORQUEORFORCEEND;}
"<"{W}"JointTorqueOrForce"    {ECH; return JOINTTORQUEORFORCESTART;}
"</"{W}"JointVelocity"{W}">"  {ECH; return JOINTVELOCITYEND;}
"<"{W}"JointVelocity"         {ECH; return JOINTVELOCITYSTART;}
"</"{W}"K"{W}">"              {ECH; return KEND;}
"<"{W}"K"                     {ECH; return KSTART;}
"</"{W}"LinearVelocity"{W}">" {ECH; return LINEARVELOCITYEND;}
"<"{W}"LinearVelocity"        {ECH; return LINEARVELOCITYSTART;}
"</"{W}"LowerRight"{W}">"     {ECH; return LOWERRIGHTEND;}
"<"{W}"LowerRight"            {ECH; return LOWERRIGHTSTART;}
"</"{W}"Moment"{W}">"         {ECH; return MOMENTEND;}
"<"{W}"Moment"                {ECH; return MOMENTSTART;}
"</"{W}"Name"{W}">"           {ECH; return NAMEEND;}
"<"{W}"Name"                  {ECH; return NAMESTART;}
"</"{W}"OrientationStandardDeviation"{W}">" {ECH; return ORIENTATIONSTANDARDDEVIATIONEND;}
"<"{W}"OrientationStandardDeviation" {ECH; return ORIENTATIONSTANDARDDEVIATIONSTART;}
"</"{W}"Point"{W}">"          {ECH; return POINTEND;}
"<"{W}"Point"                 {ECH; return POINTSTART;}
"</"{W}"PoseStatus"{W}">"     {ECH; return POSESTATUSEND;}
"<"{W}"PoseStatus"            {ECH; return POSESTATUSSTART;}
"</"{W}"Pose"{W}">"           {ECH; return POSEEND;}
"<"{W}"Pose"                  {ECH; return POSESTART;}
"</"{W}"PositionStandardDeviation"{W}">" {ECH; return POSITIONSTANDARDDEVIATIONEND;}
"<"{W}"PositionStandardDeviation" {ECH; return POSITIONSTANDARDDEVIATIONSTART;}
"</"{W}"RefObjectName"{W}">"  {ECH; return REFOBJECTNAMEEND;}
"<"{W}"RefObjectName"         {ECH; return REFOBJECTNAMESTART;}
"</"{W}"Separation"{W}">"     {ECH; return SEPARATIONEND;}
"<"{W}"Separation"            {ECH; return SEPARATIONSTART;}
"</"{W}"StatusID"{W}">"       {ECH; return STATUSIDEND;}
"<"{W}"StatusID"              {ECH; return STATUSIDSTART;}
"</"{W}"Timestamp"{W}">"      {ECH; return TIMESTAMPEND;}
"<"{W}"Timestamp"             {ECH; return TIMESTAMPSTART;}
"</"{W}"Twist"{W}">"          {ECH; return TWISTEND;}
"<"{W}"Twist"                 {ECH; return TWISTSTART;}
"</"{W}"UpperLeft"{W}">"      {ECH; return UPPERLEFTEND;}
"<"{W}"UpperLeft"             {ECH; return UPPERLEFTSTART;}
"</"{W}"Wrench"{W}">"         {ECH; return WRENCHEND;}
"<"{W}"Wrench"                {ECH; return WRENCHSTART;}
"</"{W}"XAxis"{W}">"          {ECH; return XAXISEND;}
"<"{W}"XAxis"                 {ECH; return XAXISSTART;}
"</"{W}"X"{W}">"              {ECH; return XEND;}
"<"{W}"X"                     {ECH; return XSTART;}
"</"{W}"Y"{W}">"              {ECH; return YEND;}
"<"{W}"Y"                     {ECH; return YSTART;}
"</"{W}"ZAxis"{W}">"          {ECH; return ZAXISEND;}
"<"{W}"ZAxis"                 {ECH; return ZAXISSTART;}
"</"{W}"Z"{W}">"              {ECH; return ZEND;}
"<"{W}"Z"                     {ECH; return ZSTART;}

{W}"xsi:type"{W}"="{W}"\"ParallelGripperStatusType\""  { ECH; return PARALLELGRIPPERSTATUSTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"ThreeFingerGripperStatusType\""  { ECH; return THREEFINGERGRIPPERSTATUSTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"VacuumGripperStatusType\""  { ECH; return VACUUMGRIPPERSTATUSTYPEDECL; }

"xmlns:xsi"{W}"="{W}"\"http://www.w3.org/2001/XMLSchema-instance\"" {ECH;
                                           return XMLNSPREFIX;}

">"                           {ECH; return ENDITEM;}

\"[^\"]+\"                 {ECH;
                            int n;
                            for (n = 1; yytext[n] != '"'; n++);
                            yytext[n] = 0;
                            yylval.sVal = strdup(yytext + 1);
                            return TERMINALSTRING;
                           }

{W}                        {ECH;}

.                          {ECH; return BAD;}

%%

int yywrap()
{
  return 1;
}
