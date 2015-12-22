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
#include "crcl_cpp/CRCLProgramInstanceClasses.hh"
#include "crcl_cpp/CRCLProgramInstanceYACC.hh"    // for tokens, yylval, etc.

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

"</"{W}"ActuateJoint"{W}">"   {ECH; return ACTUATEJOINTEND;}
"<"{W}"ActuateJoint"          {ECH; return ACTUATEJOINTSTART;}
"</"{W}"AngularVelocity"{W}">" {ECH; return ANGULARVELOCITYEND;}
"<"{W}"AngularVelocity"       {ECH; return ANGULARVELOCITYSTART;}
"</"{W}"AxialDistanceFree"{W}">" {ECH; return AXIALDISTANCEFREEEND;}
"<"{W}"AxialDistanceFree"     {ECH; return AXIALDISTANCEFREESTART;}
"</"{W}"AxialDistanceScrew"{W}">" {ECH; return AXIALDISTANCESCREWEND;}
"<"{W}"AxialDistanceScrew"    {ECH; return AXIALDISTANCESCREWSTART;}
"</"{W}"AxisPoint"{W}">"      {ECH; return AXISPOINTEND;}
"<"{W}"AxisPoint"             {ECH; return AXISPOINTSTART;}
"</"{W}"CRCLProgram"{W}">"    {ECH; return CRCLPROGRAMEND;}
"<"{W}"CRCLProgram"           {ECH; return CRCLPROGRAMSTART;}
"</"{W}"ChangeRate"{W}">"     {ECH; return CHANGERATEEND;}
"<"{W}"ChangeRate"            {ECH; return CHANGERATESTART;}
"</"{W}"CommandID"{W}">"      {ECH; return COMMANDIDEND;}
"<"{W}"CommandID"             {ECH; return COMMANDIDSTART;}
"</"{W}"ConfigureJointReport"{W}">" {ECH; return CONFIGUREJOINTREPORTEND;}
"<"{W}"ConfigureJointReport"  {ECH; return CONFIGUREJOINTREPORTSTART;}
"</"{W}"Coordinated"{W}">"    {ECH; return COORDINATEDEND;}
"<"{W}"Coordinated"           {ECH; return COORDINATEDSTART;}
"</"{W}"Description"{W}">"    {ECH; return DESCRIPTIONEND;}
"<"{W}"Description"           {ECH; return DESCRIPTIONSTART;}
"</"{W}"DwellTime"{W}">"      {ECH; return DWELLTIMEEND;}
"<"{W}"DwellTime"             {ECH; return DWELLTIMESTART;}
"</"{W}"EndCanon"{W}">"       {ECH; return ENDCANONEND;}
"<"{W}"EndCanon"              {ECH; return ENDCANONSTART;}
"</"{W}"EndPosition"{W}">"    {ECH; return ENDPOSITIONEND;}
"<"{W}"EndPosition"           {ECH; return ENDPOSITIONSTART;}
"</"{W}"Force"{W}">"          {ECH; return FORCEEND;}
"<"{W}"Force"                 {ECH; return FORCESTART;}
"</"{W}"Fraction"{W}">"       {ECH; return FRACTIONEND;}
"<"{W}"Fraction"              {ECH; return FRACTIONSTART;}
"</"{W}"I"{W}">"              {ECH; return IEND;}
"<"{W}"I"                     {ECH; return ISTART;}
"</"{W}"InitCanon"{W}">"      {ECH; return INITCANONEND;}
"<"{W}"InitCanon"             {ECH; return INITCANONSTART;}
"</"{W}"J"{W}">"              {ECH; return JEND;}
"<"{W}"J"                     {ECH; return JSTART;}
"</"{W}"JointAccel"{W}">"     {ECH; return JOINTACCELEND;}
"<"{W}"JointAccel"            {ECH; return JOINTACCELSTART;}
"</"{W}"JointDetails"{W}">"   {ECH; return JOINTDETAILSEND;}
"<"{W}"JointDetails"          {ECH; return JOINTDETAILSSTART;}
"</"{W}"JointNumber"{W}">"    {ECH; return JOINTNUMBEREND;}
"<"{W}"JointNumber"           {ECH; return JOINTNUMBERSTART;}
"</"{W}"JointPosition"{W}">"  {ECH; return JOINTPOSITIONEND;}
"<"{W}"JointPosition"         {ECH; return JOINTPOSITIONSTART;}
"</"{W}"JointSpeed"{W}">"     {ECH; return JOINTSPEEDEND;}
"<"{W}"JointSpeed"            {ECH; return JOINTSPEEDSTART;}
"</"{W}"K"{W}">"              {ECH; return KEND;}
"<"{W}"K"                     {ECH; return KSTART;}
"</"{W}"LinearVelocity"{W}">" {ECH; return LINEARVELOCITYEND;}
"<"{W}"LinearVelocity"        {ECH; return LINEARVELOCITYSTART;}
"</"{W}"LowerRight"{W}">"     {ECH; return LOWERRIGHTEND;}
"<"{W}"LowerRight"            {ECH; return LOWERRIGHTSTART;}
"</"{W}"Message"{W}">"        {ECH; return MESSAGEEND;}
"<"{W}"Message"               {ECH; return MESSAGESTART;}
"</"{W}"MiddleCommand"{W}">"  {ECH; return MIDDLECOMMANDEND;}
"<"{W}"MiddleCommand"         {ECH; return MIDDLECOMMANDSTART;}
"</"{W}"Moment"{W}">"         {ECH; return MOMENTEND;}
"<"{W}"Moment"                {ECH; return MOMENTSTART;}
"</"{W}"MoveStraight"{W}">"   {ECH; return MOVESTRAIGHTEND;}
"<"{W}"MoveStraight"          {ECH; return MOVESTRAIGHTSTART;}
"</"{W}"Name"{W}">"           {ECH; return NAMEEND;}
"<"{W}"Name"                  {ECH; return NAMESTART;}
"</"{W}"NumPositions"{W}">"   {ECH; return NUMPOSITIONSEND;}
"<"{W}"NumPositions"          {ECH; return NUMPOSITIONSSTART;}
"</"{W}"OrientationStandardDeviation"{W}">" {ECH; return ORIENTATIONSTANDARDDEVIATIONEND;}
"<"{W}"OrientationStandardDeviation" {ECH; return ORIENTATIONSTANDARDDEVIATIONSTART;}
"</"{W}"ParameterName"{W}">"  {ECH; return PARAMETERNAMEEND;}
"<"{W}"ParameterName"         {ECH; return PARAMETERNAMESTART;}
"</"{W}"ParameterSetting"{W}">" {ECH; return PARAMETERSETTINGEND;}
"<"{W}"ParameterSetting"      {ECH; return PARAMETERSETTINGSTART;}
"</"{W}"ParameterValue"{W}">" {ECH; return PARAMETERVALUEEND;}
"<"{W}"ParameterValue"        {ECH; return PARAMETERVALUESTART;}
"</"{W}"Point"{W}">"          {ECH; return POINTEND;}
"<"{W}"Point"                 {ECH; return POINTSTART;}
"</"{W}"PositionStandardDeviation"{W}">" {ECH; return POSITIONSTANDARDDEVIATIONEND;}
"<"{W}"PositionStandardDeviation" {ECH; return POSITIONSTANDARDDEVIATIONSTART;}
"</"{W}"ProgramText"{W}">"    {ECH; return PROGRAMTEXTEND;}
"<"{W}"ProgramText"           {ECH; return PROGRAMTEXTSTART;}
"</"{W}"RefObjectName"{W}">"  {ECH; return REFOBJECTNAMEEND;}
"<"{W}"RefObjectName"         {ECH; return REFOBJECTNAMESTART;}
"</"{W}"ReportPosition"{W}">" {ECH; return REPORTPOSITIONEND;}
"<"{W}"ReportPosition"        {ECH; return REPORTPOSITIONSTART;}
"</"{W}"ReportTorqueOrForce"{W}">" {ECH; return REPORTTORQUEORFORCEEND;}
"<"{W}"ReportTorqueOrForce"   {ECH; return REPORTTORQUEORFORCESTART;}
"</"{W}"ReportVelocity"{W}">" {ECH; return REPORTVELOCITYEND;}
"<"{W}"ReportVelocity"        {ECH; return REPORTVELOCITYSTART;}
"</"{W}"ResetAll"{W}">"       {ECH; return RESETALLEND;}
"<"{W}"ResetAll"              {ECH; return RESETALLSTART;}
"</"{W}"RotAccel"{W}">"       {ECH; return ROTACCELEND;}
"<"{W}"RotAccel"              {ECH; return ROTACCELSTART;}
"</"{W}"RotSpeed"{W}">"       {ECH; return ROTSPEEDEND;}
"<"{W}"RotSpeed"              {ECH; return ROTSPEEDSTART;}
"</"{W}"Setting"{W}">"        {ECH; return SETTINGEND;}
"<"{W}"Setting"               {ECH; return SETTINGSTART;}
"</"{W}"StartPosition"{W}">"  {ECH; return STARTPOSITIONEND;}
"<"{W}"StartPosition"         {ECH; return STARTPOSITIONSTART;}
"</"{W}"StopCondition"{W}">"  {ECH; return STOPCONDITIONEND;}
"<"{W}"StopCondition"         {ECH; return STOPCONDITIONSTART;}
"</"{W}"Timestamp"{W}">"      {ECH; return TIMESTAMPEND;}
"<"{W}"Timestamp"             {ECH; return TIMESTAMPSTART;}
"</"{W}"Tolerance"{W}">"      {ECH; return TOLERANCEEND;}
"<"{W}"Tolerance"             {ECH; return TOLERANCESTART;}
"</"{W}"TransAccel"{W}">"     {ECH; return TRANSACCELEND;}
"<"{W}"TransAccel"            {ECH; return TRANSACCELSTART;}
"</"{W}"TransSpeed"{W}">"     {ECH; return TRANSSPEEDEND;}
"<"{W}"TransSpeed"            {ECH; return TRANSSPEEDSTART;}
"</"{W}"Turn"{W}">"           {ECH; return TURNEND;}
"<"{W}"Turn"                  {ECH; return TURNSTART;}
"</"{W}"UnitName"{W}">"       {ECH; return UNITNAMEEND;}
"<"{W}"UnitName"              {ECH; return UNITNAMESTART;}
"</"{W}"UpperLeft"{W}">"      {ECH; return UPPERLEFTEND;}
"<"{W}"UpperLeft"             {ECH; return UPPERLEFTSTART;}
"</"{W}"Waypoint"{W}">"       {ECH; return WAYPOINTEND;}
"<"{W}"Waypoint"              {ECH; return WAYPOINTSTART;}
"</"{W}"XAxisTolerance"{W}">" {ECH; return XAXISTOLERANCEEND;}
"<"{W}"XAxisTolerance"        {ECH; return XAXISTOLERANCESTART;}
"</"{W}"XAxis"{W}">"          {ECH; return XAXISEND;}
"<"{W}"XAxis"                 {ECH; return XAXISSTART;}
"</"{W}"XPointTolerance"{W}">" {ECH; return XPOINTTOLERANCEEND;}
"<"{W}"XPointTolerance"       {ECH; return XPOINTTOLERANCESTART;}
"</"{W}"X"{W}">"              {ECH; return XEND;}
"<"{W}"X"                     {ECH; return XSTART;}
"</"{W}"YPointTolerance"{W}">" {ECH; return YPOINTTOLERANCEEND;}
"<"{W}"YPointTolerance"       {ECH; return YPOINTTOLERANCESTART;}
"</"{W}"Y"{W}">"              {ECH; return YEND;}
"<"{W}"Y"                     {ECH; return YSTART;}
"</"{W}"ZAxisTolerance"{W}">" {ECH; return ZAXISTOLERANCEEND;}
"<"{W}"ZAxisTolerance"        {ECH; return ZAXISTOLERANCESTART;}
"</"{W}"ZAxis"{W}">"          {ECH; return ZAXISEND;}
"<"{W}"ZAxis"                 {ECH; return ZAXISSTART;}
"</"{W}"ZPointTolerance"{W}">" {ECH; return ZPOINTTOLERANCEEND;}
"<"{W}"ZPointTolerance"       {ECH; return ZPOINTTOLERANCESTART;}
"</"{W}"Z"{W}">"              {ECH; return ZEND;}
"<"{W}"Z"                     {ECH; return ZSTART;}

{W}"xsi:type"{W}"="{W}"\"ActuateJointsType\""  { ECH; return ACTUATEJOINTSTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"CloseToolChangerType\""  { ECH; return CLOSETOOLCHANGERTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"ConfigureJointReportsType\""  { ECH; return CONFIGUREJOINTREPORTSTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"DwellType\""  { ECH; return DWELLTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"GetStatusType\""  { ECH; return GETSTATUSTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"JointForceTorqueType\""  { ECH; return JOINTFORCETORQUETYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"JointSpeedAccelType\""  { ECH; return JOINTSPEEDACCELTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"MessageType\""  { ECH; return MESSAGETYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"MoveScrewType\""  { ECH; return MOVESCREWTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"MoveThroughToType\""  { ECH; return MOVETHROUGHTOTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"MoveToType\""  { ECH; return MOVETOTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"OpenToolChangerType\""  { ECH; return OPENTOOLCHANGERTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"PoseAndSetType\""  { ECH; return POSEANDSETTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"RotAccelAbsoluteType\""  { ECH; return ROTACCELABSOLUTETYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"RotAccelRelativeType\""  { ECH; return ROTACCELRELATIVETYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"RotSpeedAbsoluteType\""  { ECH; return ROTSPEEDABSOLUTETYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"RotSpeedRelativeType\""  { ECH; return ROTSPEEDRELATIVETYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"RunProgramType\""  { ECH; return RUNPROGRAMTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"SetAngleUnitsType\""  { ECH; return SETANGLEUNITSTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"SetEndEffectorParametersType\""  { ECH; return SETENDEFFECTORPARAMETERSTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"SetEndEffectorType\""  { ECH; return SETENDEFFECTORTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"SetEndPoseToleranceType\""  { ECH; return SETENDPOSETOLERANCETYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"SetForceUnitsType\""  { ECH; return SETFORCEUNITSTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"SetIntermediatePoseToleranceType\""  { ECH; return SETINTERMEDIATEPOSETOLERANCETYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"SetLengthUnitsType\""  { ECH; return SETLENGTHUNITSTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"SetMotionCoordinationType\""  { ECH; return SETMOTIONCOORDINATIONTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"SetRobotParametersType\""  { ECH; return SETROBOTPARAMETERSTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"SetRotAccelType\""  { ECH; return SETROTACCELTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"SetRotSpeedType\""  { ECH; return SETROTSPEEDTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"SetTorqueUnitsType\""  { ECH; return SETTORQUEUNITSTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"SetTransAccelType\""  { ECH; return SETTRANSACCELTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"SetTransSpeedType\""  { ECH; return SETTRANSSPEEDTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"StopMotionType\""  { ECH; return STOPMOTIONTYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"TransAccelAbsoluteType\""  { ECH; return TRANSACCELABSOLUTETYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"TransAccelRelativeType\""  { ECH; return TRANSACCELRELATIVETYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"TransSpeedAbsoluteType\""  { ECH; return TRANSSPEEDABSOLUTETYPEDECL; }
{W}"xsi:type"{W}"="{W}"\"TransSpeedRelativeType\""  { ECH; return TRANSSPEEDRELATIVETYPEDECL; }

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
