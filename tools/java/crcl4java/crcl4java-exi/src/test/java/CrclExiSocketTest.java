/*
 * This software is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain.
 *
 *  This software is experimental. NIST assumes no responsibility whatsoever for its use by other
 * parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic. We would appreciate
 * acknowledgement if the software is used. This software can be
 * redistributed and/or modified freely provided that any derivative works
 * bear some notice that they are derived from it, and any modified
 * versions bear some notice that they have been modified.
 * 
 *  See http://www.copyright.gov/title17/92chap1.html#105
 * 
 */

import crcl.exi.CrclExiSocket;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStateEnumType;
import crcl.base.CommandStatusType;
import crcl.base.GetStatusType;
import crcl.base.InitCanonType;
import crcl.base.MoveThroughToType;
import crcl.base.PointType;
import crcl.base.PoseType;

import crcl.base.VectorType;
import crcl.utils.CRCLPosemath;
import crcl.utils.CRCLException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
@SuppressWarnings("nullness")
public class CrclExiSocketTest {

    private static final Logger LOGGER = Logger.getLogger(CrclExiSocketTest.class.getName());

    static final String programAllXmlTrimmed
            = "<CRCLProgram\n"
            + "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
            + "  xsi:noNamespaceSchemaLocation=\"../xmlSchemas/CRCLProgramInstance.xsd\">\n"
            + "  <InitCanon>\n"
            + "    <Name>start</Name>\n"
            + "    <CommandID>1</CommandID>\n"
            + "  </InitCanon>\n"
            + "  <MiddleCommand xsi:type=\"ActuateJointsType\">\n"
            + "    <CommandID>2</CommandID>\n"
            + "    <ActuateJoint>\n"
            + "      <JointNumber>1</JointNumber>\n"
            + "      <JointPosition>3.8</JointPosition>\n"
            + "      <JointDetails xsi:type=\"JointSpeedAccelType\">\n"
            + "        <JointSpeed>3.7</JointSpeed>\n"
            + "        <JointAccel>11</JointAccel>\n"
            + "      </JointDetails>\n"
            + "    </ActuateJoint>\n"
            + "    <ActuateJoint>\n"
            + "      <JointNumber>3</JointNumber>\n"
            + "       <JointPosition>3.8</JointPosition>\n"
            + "      <JointDetails xsi:type=\"JointForceTorqueType\">\n"
            + "        <Setting>7</Setting>\n"
            + "        <ChangeRate>13.0</ChangeRate>\n"
            + "      </JointDetails>\n"
            + "    </ActuateJoint>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"CloseToolChangerType\">\n"
            + "    <CommandID>3</CommandID>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"ConfigureJointReportsType\">\n"
            + "    <CommandID>4</CommandID>\n"
            + "    <ResetAll>true</ResetAll>\n"
            + "    <ConfigureJointReport>\n"
            + "      <JointNumber>1</JointNumber>\n"
            + "      <ReportPosition>true</ReportPosition>\n"
            + "      <ReportTorqueOrForce>false</ReportTorqueOrForce>\n"
            + "      <ReportVelocity>false</ReportVelocity>\n"
            + "    </ConfigureJointReport>\n"
            + "    <ConfigureJointReport>\n"
            + "      <JointNumber>3</JointNumber>\n"
            + "      <ReportPosition>true</ReportPosition>\n"
            + "      <ReportTorqueOrForce>true</ReportTorqueOrForce>\n"
            + "      <ReportVelocity>false</ReportVelocity>\n"
            + "    </ConfigureJointReport>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"DwellType\">\n"
            + "    <CommandID>5</CommandID>\n"
            + "    <DwellTime>2.5</DwellTime>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"GetStatusType\">\n"
            + "    <CommandID>6</CommandID>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"MessageType\">\n"
            + "    <CommandID>7</CommandID>\n"
            + "    <Message>Hi Mom</Message>\n"
            + "  </MiddleCommand>\n"
            + "\n"
            + "  <MiddleCommand xsi:type=\"MoveScrewType\">\n"
            + "    <CommandID>8</CommandID>\n"
            + "    <AxisPoint>\n"
            + "      <X>8.25</X> <Y>1</Y> <Z>0.5</Z>\n"
            + "    </AxisPoint>\n"
            + "    <AxialDistanceScrew>6.10</AxialDistanceScrew>\n"
            + "    <Turn>-3.14</Turn>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"MoveThroughToType\">\n"
            + "    <CommandID>9</CommandID>\n"
            + "    <MoveStraight>false</MoveStraight>\n"
            + "    <Waypoint>\n"
            + "      <Point>\n"
            + "        <X>8.25</X> <Y>1</Y> <Z>1</Z>\n"
            + "      </Point>\n"
            + "      <XAxis>\n"
            + "        <I>1</I> <J>0</J> <K>0</K>\n"
            + "      </XAxis>\n"
            + "      <ZAxis>\n"
            + "        <I>0</I> <J>0</J> <K>-1</K>\n"
            + "      </ZAxis>\n"
            + "    </Waypoint>\n"
            + "    <Waypoint xsi:type=\"PoseAndSetType\">\n"
            + "      <Point>\n"
            + "        <X>8.25</X> <Y>1</Y> <Z>0.5</Z>\n"
            + "      </Point>\n"
            + "      <XAxis>\n"
            + "        <I>1</I> <J>0</J> <K>0</K>\n"
            + "      </XAxis>\n"
            + "      <ZAxis>\n"
            + "        <I>0</I> <J>0</J> <K>-1</K>\n"
            + "      </ZAxis>\n"
            + "      <Coordinated>true</Coordinated>\n"
            + "      <TransSpeed xsi:type=\"TransSpeedRelativeType\">\n"
            + "        <Fraction>0.9</Fraction>\n"
            + "      </TransSpeed>\n"
            + "      <TransAccel xsi:type=\"TransAccelRelativeType\">\n"
            + "        <Fraction>0.5</Fraction>\n"
            + "      </TransAccel>\n"
            + "      <Tolerance>\n"
            + "        <XPointTolerance>0.005</XPointTolerance>\n"
            + "        <YPointTolerance>0.01</YPointTolerance>\n"
            + "        <ZPointTolerance>0.015</ZPointTolerance>\n"
            + "        <ZAxisTolerance>1.0</ZAxisTolerance>\n"
            + "      </Tolerance>\n"
            + "    </Waypoint>\n"
            + "    <NumPositions>2</NumPositions>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"MoveToType\">\n"
            + "    <CommandID>12</CommandID>\n"
            + "    <MoveStraight>true</MoveStraight>\n"
            + "    <EndPosition>\n"
            + "      <Point>\n"
            + "        <X>8.25</X> <Y>1</Y> <Z>0.5</Z>\n"
            + "      </Point>\n"
            + "      <XAxis>\n"
            + "        <I>1</I> <J>0</J> <K>0</K>\n"
            + "      </XAxis>\n"
            + "      <ZAxis>\n"
            + "        <I>0</I> <J>0</J> <K>-1</K>\n"
            + "      </ZAxis>\n"
            + "    </EndPosition>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"OpenToolChangerType\">\n"
            + "    <CommandID>13</CommandID>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"RunProgramType\">\n"
            + "    <CommandID>14</CommandID>\n"
            + "    <ProgramText>GH$%kkk457 xxx  65</ProgramText>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetAngleUnitsType\">\n"
            + "    <CommandID>15</CommandID>\n"
            + "    <UnitName>degree</UnitName>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetEndEffectorParametersType\">\n"
            + "    <CommandID>16</CommandID>\n"
            + "    <ParameterSetting>\n"
            + "      <ParameterName>rhabdaciousness</ParameterName>\n"
            + "      <ParameterValue>on</ParameterValue>\n"
            + "    </ParameterSetting>\n"
            + "    <ParameterSetting>\n"
            + "      <ParameterName>fluoxity</ParameterName>\n"
            + "      <ParameterValue>33</ParameterValue>\n"
            + "    </ParameterSetting>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetEndEffectorType\">\n"
            + "    <CommandID>17</CommandID>\n"
            + "    <Setting>1.0</Setting>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetEndPoseToleranceType\">\n"
            + "    <CommandID>18</CommandID>\n"
            + "      <Tolerance>\n"
            + "        <XPointTolerance>0.005</XPointTolerance>\n"
            + "        <YPointTolerance>0.01</YPointTolerance>\n"
            + "        <ZPointTolerance>0.015</ZPointTolerance>\n"
            + "        <XAxisTolerance>1.0</XAxisTolerance>\n"
            + "        <ZAxisTolerance>1.0</ZAxisTolerance>\n"
            + "      </Tolerance>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetForceUnitsType\">\n"
            + "    <CommandID>19</CommandID>\n"
            + "    <UnitName>ounce</UnitName>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetIntermediatePoseToleranceType\">\n"
            + "    <CommandID>20</CommandID>\n"
            + "      <Tolerance>\n"
            + "        <XPointTolerance>0.1</XPointTolerance>\n"
            + "        <YPointTolerance>0.05</YPointTolerance>\n"
            + "        <ZPointTolerance>0.06</ZPointTolerance>\n"
            + "        <XAxisTolerance>1.0</XAxisTolerance>\n"
            + "        <ZAxisTolerance>1.0</ZAxisTolerance>\n"
            + "      </Tolerance>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetLengthUnitsType\">\n"
            + "    <CommandID>21</CommandID>\n"
            + "    <UnitName>meter</UnitName>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetMotionCoordinationType\">\n"
            + "    <CommandID>22</CommandID>\n"
            + "    <Coordinated>true</Coordinated>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetRobotParametersType\">\n"
            + "    <CommandID>23</CommandID>\n"
            + "    <ParameterSetting>\n"
            + "      <ParameterName>empathy</ParameterName>\n"
            + "      <ParameterValue>3.2</ParameterValue>\n"
            + "    </ParameterSetting>\n"
            + "    <ParameterSetting>\n"
            + "      <ParameterName>air pressure</ParameterName>\n"
            + "      <ParameterValue>701</ParameterValue>\n"
            + "    </ParameterSetting>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetRotAccelType\">\n"
            + "    <CommandID>24</CommandID>\n"
            + "    <RotAccel xsi:type=\"RotAccelAbsoluteType\">\n"
            + "      <Setting>4.08</Setting>\n"
            + "    </RotAccel>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetRotAccelType\">\n"
            + "    <CommandID>25</CommandID>\n"
            + "    <RotAccel xsi:type=\"RotAccelRelativeType\">\n"
            + "      <Fraction>0.77</Fraction>\n"
            + "    </RotAccel>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetRotSpeedType\">\n"
            + "    <CommandID>26</CommandID>\n"
            + "    <RotSpeed xsi:type=\"RotSpeedAbsoluteType\">\n"
            + "      <Setting>6.28</Setting>\n"
            + "    </RotSpeed>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetRotSpeedType\">\n"
            + "    <CommandID>27</CommandID>\n"
            + "    <RotSpeed xsi:type=\"RotSpeedRelativeType\">\n"
            + "      <Fraction>0.55</Fraction>\n"
            + "    </RotSpeed>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetTorqueUnitsType\">\n"
            + "    <CommandID>28</CommandID>\n"
            + "    <UnitName>newtonMeter</UnitName>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetTransAccelType\">\n"
            + "    <CommandID>29</CommandID>\n"
            + "    <TransAccel xsi:type=\"TransAccelAbsoluteType\">\n"
            + "      <Setting>9.80</Setting>\n"
            + "    </TransAccel>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetTransAccelType\">\n"
            + "    <CommandID>30</CommandID>\n"
            + "    <TransAccel xsi:type=\"TransAccelRelativeType\">\n"
            + "      <Fraction>0.75</Fraction>\n"
            + "    </TransAccel>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetTransSpeedType\">\n"
            + "    <CommandID>31</CommandID>\n"
            + "    <TransSpeed xsi:type=\"TransSpeedAbsoluteType\">\n"
            + "      <Setting>5.0</Setting>\n"
            + "    </TransSpeed>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetTransSpeedType\">\n"
            + "    <CommandID>32</CommandID>\n"
            + "    <TransSpeed xsi:type=\"TransSpeedRelativeType\">\n"
            + "      <Fraction>0.85</Fraction>\n"
            + "    </TransSpeed>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"StopMotionType\">\n"
            + "    <CommandID>33</CommandID>\n"
            + "    <StopCondition>Normal</StopCondition>\n"
            + "  </MiddleCommand>\n"
            + "  <EndCanon>\n"
            + "    <CommandID>34</CommandID>\n"
            + "  </EndCanon>\n"
            + "</CRCLProgram>";
    static final String programAllXml
            = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<!--\n"
            + "\n"
            + "This is a program file with instances of all CRCL commands in alphabetical\n"
            + "order (except that InitCanon is first and EndCanon is last). The file\n"
            + "is syntactically valid (so it is valid in XMLSpy) but violates semantic\n"
            + "rules given in the in-line documentation of CRCLCommands.xsd, so it\n"
            + "should not be executed.\n"
            + "\n"
            + "All instances of complexType may be given a Name. This file has a Name\n"
            + "only in the InitCanon command.\n"
            + "\n"
            + "-->\n"
            + "<CRCLProgram\n"
            + "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
            + "  xsi:noNamespaceSchemaLocation=\"../xmlSchemas/CRCLProgramInstance.xsd\">\n"
            + "  <InitCanon>\n"
            + "    <Name>start</Name>\n"
            + "    <CommandID>1</CommandID>\n"
            + "  </InitCanon>\n"
            + "  <MiddleCommand xsi:type=\"ActuateJointsType\">\n"
            + "    <CommandID>2</CommandID>\n"
            + "    <ActuateJoint>\n"
            + "      <JointNumber>1</JointNumber>\n"
            + "      <JointPosition>3.8</JointPosition>\n"
            + "      <JointDetails xsi:type=\"JointSpeedAccelType\">\n"
            + "        <JointSpeed>3.7</JointSpeed>\n"
            + "        <JointAccel>11</JointAccel>\n"
            + "      </JointDetails>\n"
            + "    </ActuateJoint>\n"
            + "    <ActuateJoint>\n"
            + "      <JointNumber>3</JointNumber>\n"
            + "       <JointPosition>3.8</JointPosition>\n"
            + "      <JointDetails xsi:type=\"JointForceTorqueType\">\n"
            + "        <Setting>7</Setting>\n"
            + "        <ChangeRate>13.0</ChangeRate>\n"
            + "      </JointDetails>\n"
            + "    </ActuateJoint>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"CloseToolChangerType\">\n"
            + "    <CommandID>3</CommandID>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"ConfigureJointReportsType\">\n"
            + "    <CommandID>4</CommandID>\n"
            + "    <ResetAll>true</ResetAll>\n"
            + "    <ConfigureJointReport>\n"
            + "      <JointNumber>1</JointNumber>\n"
            + "      <ReportPosition>true</ReportPosition>\n"
            + "      <ReportTorqueOrForce>false</ReportTorqueOrForce>\n"
            + "      <ReportVelocity>false</ReportVelocity>\n"
            + "    </ConfigureJointReport>\n"
            + "    <ConfigureJointReport>\n"
            + "      <JointNumber>3</JointNumber>\n"
            + "      <ReportPosition>true</ReportPosition>\n"
            + "      <ReportTorqueOrForce>true</ReportTorqueOrForce>\n"
            + "      <ReportVelocity>false</ReportVelocity>\n"
            + "    </ConfigureJointReport>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"DwellType\">\n"
            + "    <CommandID>5</CommandID>\n"
            + "    <DwellTime>2.5</DwellTime>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"GetStatusType\">\n"
            + "    <CommandID>6</CommandID>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"MessageType\">\n"
            + "    <CommandID>7</CommandID>\n"
            + "    <Message>Hi Mom</Message>\n"
            + "  </MiddleCommand>\n"
            + "\n"
            + "  <MiddleCommand xsi:type=\"MoveScrewType\">\n"
            + "    <CommandID>8</CommandID>\n"
            + "    <AxisPoint>\n"
            + "      <X>8.25</X> <Y>1</Y> <Z>0.5</Z>\n"
            + "    </AxisPoint>\n"
            + "    <AxialDistanceScrew>6.10</AxialDistanceScrew>\n"
            + "    <Turn>-3.14</Turn>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"MoveThroughToType\">\n"
            + "    <CommandID>9</CommandID>\n"
            + "    <MoveStraight>false</MoveStraight>\n"
            + "    <Waypoint>\n"
            + "      <Point>\n"
            + "        <X>8.25</X> <Y>1</Y> <Z>1</Z>\n"
            + "      </Point>\n"
            + "      <XAxis>\n"
            + "        <I>1</I> <J>0</J> <K>0</K>\n"
            + "      </XAxis>\n"
            + "      <ZAxis>\n"
            + "        <I>0</I> <J>0</J> <K>-1</K>\n"
            + "      </ZAxis>\n"
            + "    </Waypoint>\n"
            + "    <Waypoint xsi:type=\"PoseAndSetType\">\n"
            + "      <Point>\n"
            + "        <X>8.25</X> <Y>1</Y> <Z>0.5</Z>\n"
            + "      </Point>\n"
            + "      <XAxis>\n"
            + "        <I>1</I> <J>0</J> <K>0</K>\n"
            + "      </XAxis>\n"
            + "      <ZAxis>\n"
            + "        <I>0</I> <J>0</J> <K>-1</K>\n"
            + "      </ZAxis>\n"
            + "      <Coordinated>true</Coordinated>\n"
            + "      <TransSpeed xsi:type=\"TransSpeedRelativeType\">\n"
            + "        <Fraction>0.9</Fraction>\n"
            + "      </TransSpeed>\n"
            + "      <TransAccel xsi:type=\"TransAccelRelativeType\">\n"
            + "        <Fraction>0.5</Fraction>\n"
            + "      </TransAccel>\n"
            + "      <Tolerance>\n"
            + "        <XPointTolerance>0.005</XPointTolerance>\n"
            + "        <YPointTolerance>0.01</YPointTolerance>\n"
            + "        <ZPointTolerance>0.015</ZPointTolerance>\n"
            + "        <ZAxisTolerance>1.0</ZAxisTolerance>\n"
            + "      </Tolerance>\n"
            + "    </Waypoint>\n"
            + "    <NumPositions>2</NumPositions>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"MoveToType\">\n"
            + "    <CommandID>12</CommandID>\n"
            + "    <MoveStraight>true</MoveStraight>\n"
            + "    <EndPosition>\n"
            + "      <Point>\n"
            + "        <X>8.25</X> <Y>1</Y> <Z>0.5</Z>\n"
            + "      </Point>\n"
            + "      <XAxis>\n"
            + "        <I>1</I> <J>0</J> <K>0</K>\n"
            + "      </XAxis>\n"
            + "      <ZAxis>\n"
            + "        <I>0</I> <J>0</J> <K>-1</K>\n"
            + "      </ZAxis>\n"
            + "    </EndPosition>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"OpenToolChangerType\">\n"
            + "    <CommandID>13</CommandID>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"RunProgramType\">\n"
            + "    <CommandID>14</CommandID>\n"
            + "    <ProgramText>GH$%kkk457 xxx  65</ProgramText>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetAngleUnitsType\">\n"
            + "    <CommandID>15</CommandID>\n"
            + "    <UnitName>degree</UnitName>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetEndEffectorParametersType\">\n"
            + "    <CommandID>16</CommandID>\n"
            + "    <ParameterSetting>\n"
            + "      <ParameterName>rhabdaciousness</ParameterName>\n"
            + "      <ParameterValue>on</ParameterValue>\n"
            + "    </ParameterSetting>\n"
            + "    <ParameterSetting>\n"
            + "      <ParameterName>fluoxity</ParameterName>\n"
            + "      <ParameterValue>33</ParameterValue>\n"
            + "    </ParameterSetting>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetEndEffectorType\">\n"
            + "    <CommandID>17</CommandID>\n"
            + "    <Setting>1.0</Setting>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetEndPoseToleranceType\">\n"
            + "    <CommandID>18</CommandID>\n"
            + "      <Tolerance>\n"
            + "        <XPointTolerance>0.005</XPointTolerance>\n"
            + "        <YPointTolerance>0.01</YPointTolerance>\n"
            + "        <ZPointTolerance>0.015</ZPointTolerance>\n"
            + "        <XAxisTolerance>1.0</XAxisTolerance>\n"
            + "        <ZAxisTolerance>1.0</ZAxisTolerance>\n"
            + "      </Tolerance>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetForceUnitsType\">\n"
            + "    <CommandID>19</CommandID>\n"
            + "    <UnitName>ounce</UnitName>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetIntermediatePoseToleranceType\">\n"
            + "    <CommandID>20</CommandID>\n"
            + "      <Tolerance>\n"
            + "        <XPointTolerance>0.1</XPointTolerance>\n"
            + "        <YPointTolerance>0.05</YPointTolerance>\n"
            + "        <ZPointTolerance>0.06</ZPointTolerance>\n"
            + "        <XAxisTolerance>1.0</XAxisTolerance>\n"
            + "        <ZAxisTolerance>1.0</ZAxisTolerance>\n"
            + "      </Tolerance>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetLengthUnitsType\">\n"
            + "    <CommandID>21</CommandID>\n"
            + "    <UnitName>meter</UnitName>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetMotionCoordinationType\">\n"
            + "    <CommandID>22</CommandID>\n"
            + "    <Coordinated>true</Coordinated>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetRobotParametersType\">\n"
            + "    <CommandID>23</CommandID>\n"
            + "    <ParameterSetting>\n"
            + "      <ParameterName>empathy</ParameterName>\n"
            + "      <ParameterValue>3.2</ParameterValue>\n"
            + "    </ParameterSetting>\n"
            + "    <ParameterSetting>\n"
            + "      <ParameterName>air pressure</ParameterName>\n"
            + "      <ParameterValue>701</ParameterValue>\n"
            + "    </ParameterSetting>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetRotAccelType\">\n"
            + "    <CommandID>24</CommandID>\n"
            + "    <RotAccel xsi:type=\"RotAccelAbsoluteType\">\n"
            + "      <Setting>4.08</Setting>\n"
            + "    </RotAccel>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetRotAccelType\">\n"
            + "    <CommandID>25</CommandID>\n"
            + "    <RotAccel xsi:type=\"RotAccelRelativeType\">\n"
            + "      <Fraction>0.77</Fraction>\n"
            + "    </RotAccel>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetRotSpeedType\">\n"
            + "    <CommandID>26</CommandID>\n"
            + "    <RotSpeed xsi:type=\"RotSpeedAbsoluteType\">\n"
            + "      <Setting>6.28</Setting>\n"
            + "    </RotSpeed>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetRotSpeedType\">\n"
            + "    <CommandID>27</CommandID>\n"
            + "    <RotSpeed xsi:type=\"RotSpeedRelativeType\">\n"
            + "      <Fraction>0.55</Fraction>\n"
            + "    </RotSpeed>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetTorqueUnitsType\">\n"
            + "    <CommandID>28</CommandID>\n"
            + "    <UnitName>newtonMeter</UnitName>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetTransAccelType\">\n"
            + "    <CommandID>29</CommandID>\n"
            + "    <TransAccel xsi:type=\"TransAccelAbsoluteType\">\n"
            + "      <Setting>9.80</Setting>\n"
            + "    </TransAccel>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetTransAccelType\">\n"
            + "    <CommandID>30</CommandID>\n"
            + "    <TransAccel xsi:type=\"TransAccelRelativeType\">\n"
            + "      <Fraction>0.75</Fraction>\n"
            + "    </TransAccel>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetTransSpeedType\">\n"
            + "    <CommandID>31</CommandID>\n"
            + "    <TransSpeed xsi:type=\"TransSpeedAbsoluteType\">\n"
            + "      <Setting>5.0</Setting>\n"
            + "    </TransSpeed>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"SetTransSpeedType\">\n"
            + "    <CommandID>32</CommandID>\n"
            + "    <TransSpeed xsi:type=\"TransSpeedRelativeType\">\n"
            + "      <Fraction>0.85</Fraction>\n"
            + "    </TransSpeed>\n"
            + "  </MiddleCommand>\n"
            + "  <MiddleCommand xsi:type=\"StopMotionType\">\n"
            + "    <CommandID>33</CommandID>\n"
            + "    <StopCondition>Normal</StopCondition>\n"
            + "  </MiddleCommand>\n"
            + "  <EndCanon>\n"
            + "    <CommandID>34</CommandID>\n"
            + "  </EndCanon>\n"
            + "</CRCLProgram>\n"
            + "";
    private static final String MOVETHROUGHTO_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<CRCLCommandInstance\n"
            + "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
            + "  xsi:noNamespaceSchemaLocation=\"../xmlSchemas/CRCLCommandInstance.xsd\">\n"
            + "  <CRCLCommand xsi:type=\"MoveThroughToType\">\n"
            + "    <CommandID>2</CommandID>\n"
            + "    <MoveStraight>false</MoveStraight>\n"
            + "    <Waypoint>\n"
            + "      <Point>\n"
            + "        <X>2.5</X> <Y>1</Y> <Z>1</Z>\n"
            + "      </Point>\n"
            + "      <XAxis>\n"
            + "        <I>1</I> <J>0</J> <K>0</K>\n"
            + "      </XAxis>\n"
            + "      <ZAxis>\n"
            + "        <I>0</I> <J>0</J> <K>-1</K>\n"
            + "      </ZAxis>\n"
            + "    </Waypoint>\n"
            + "    <Waypoint>\n"
            + "      <Point>\n"
            + "        <X>0.5</X> <Y>0</Y> <Z>2</Z>\n"
            + "      </Point>\n"
            + "      <XAxis>\n"
            + "        <I>1</I> <J>0</J> <K>0</K>\n"
            + "      </XAxis>\n"
            + "      <ZAxis>\n"
            + "        <I>0</I> <J>0</J> <K>-1</K>\n"
            + "      </ZAxis>\n"
            + "    </Waypoint>\n"
            + "    <NumPositions>2</NumPositions>\n"
            + "  </CRCLCommand>\n"
            + "</CRCLCommandInstance>";
    private static final String STATUS_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<CRCLStatus\n"
            + "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
            + "  xsi:noNamespaceSchemaLocation=\"../xmlSchemas/CRCLStatus.xsd\">\n"
            + "\n"
            + "  <CommandStatus>\n"
            + "    <CommandID>1</CommandID>\n"
            + "    <StatusID>1</StatusID>\n"
            + "    <CommandState>CRCL_Working</CommandState>\n"
            + "  </CommandStatus>\n"
            + "  <JointStatuses>\n"
            + "    <JointStatus>\n"
            + "      <JointNumber>1</JointNumber>\n"
            + "      <JointPosition>30.0</JointPosition>\n"
            + "      <JointTorqueOrForce>3.7</JointTorqueOrForce>\n"
            + "    </JointStatus>\n"
            + "    <JointStatus>\n"
            + "      <JointNumber>3</JointNumber>\n"
            + "      <JointPosition>90.0</JointPosition>\n"
            + "      <JointVelocity>0.87</JointVelocity>\n"
            + "    </JointStatus>\n"
            + "  </JointStatuses>\n"
            + "  <PoseStatus>\n"
            + "    <Pose>\n"
            + "      <Point>\n"
            + "	<X>1.5</X> <Y>1</Y> <Z>1</Z>\n"
            + "      </Point>\n"
            + "      <XAxis>\n"
            + "	<I>1</I> <J>0</J> <K>0</K>\n"
            + "      </XAxis>\n"
            + "      <ZAxis>\n"
            + "	<I>0</I> <J>0</J> <K>-1</K>\n"
            + "      </ZAxis>\n"
            + "    </Pose>\n"
            + "  </PoseStatus>\n"
            + "  <GripperStatus xsi:type=\"ParallelGripperStatusType\">\n"
            + "    <GripperName>jaws</GripperName>\n"
            + "    <Separation>0.44</Separation>\n"
            + "  </GripperStatus>\n"
            + "</CRCLStatus>";

    public CrclExiSocketTest() {
    }

//    /**
//     * Test of statusToEXI method, of class CrclExiSocket.
//     */
//    @Test
//    public void testMessage() throws Exception {
//        crcl.base.MessageType m = new  MessageType();
//        crcl.base.CRCLCommandInstanceType cmd = new CRCLCommandInstanceType();
//        m.setMessage("</CRCLCommandInstanceType>");
//        cmd.setCRCLCommand(m);
//        CrclExiSocket s = new CrclExiSocket();
//        String xmlPrettyS  = s.commandInstanceToPrettyDocString(cmd, true);
//        LOGGER.log(Level.INFO,"xmlPrettyS = " + xmlPrettyS);
//        String xmlS  = s.commandToString(cmd, true);
//        LOGGER.log(Level.INFO,"xmlS = " + xmlS);
//    }
    /**
     * Test of statusToEXI method, of class CrclExiSocket.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testStatusToEXI() throws Exception {
        LOGGER.log(Level.INFO, "statusToEXI");
        CRCLStatusType status = new CRCLStatusType();
        CommandStatusType cst = new CommandStatusType();
        cst.setCommandID(1);
        cst.setStatusID(10);
        cst.setCommandState(CommandStateEnumType.CRCL_DONE);
        status.setCommandStatus(cst);
        PoseType p = new PoseType();
        PointType pt = new PointType();
        pt.setX(1.0);
        pt.setY(1.0);
        pt.setZ(1.0);
        p.setPoint(pt);
        VectorType xAxis = new VectorType();
        xAxis.setI(1.0);
        xAxis.setJ(0.0);
        xAxis.setK(0.0);
        p.setXAxis(xAxis);
        VectorType zAxis = new VectorType();
        zAxis.setI(0.0);
        zAxis.setJ(0.0);
        zAxis.setK(1.0);
        p.setZAxis(zAxis);
        CRCLPosemath.setPose(status, p);
        CrclExiSocket instance = new CrclExiSocket();
        byte[] expResult = null;
        byte[] result = instance.statusToEXI(status);
//        LOGGER.log(Level.INFO,"status = " + instance.statusToString(status, true));
//        LOGGER.log(Level.INFO,"result = " + Arrays.toString(result));
        CRCLStatusType statusCopy = instance.exiToStatus(result);
//        LOGGER.log(Level.INFO,"statusCopy = " + instance.statusToString(statusCopy, false));
//        LOGGER.log(Level.INFO,"status="+CrclExiSocket.statToDebugString(status));
        assertEquals(status.getCommandStatus().getCommandID(),
                statusCopy.getCommandStatus().getCommandID());
        assertEquals(status.getCommandStatus().getStatusID(),
                statusCopy.getCommandStatus().getStatusID());
        assertTrue(
                Math.abs(
                        CRCLPosemath.getPoint(status).getX()
                        - CRCLPosemath.getPoint(statusCopy).getX()
                ) < 1e-6);
    }

    /**
     * Test of commandToEXI method, of class CrclExiSocket.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCommandToEXI() throws Exception {
        LOGGER.log(Level.INFO, "commandToEXI");
        CRCLCommandInstanceType initCmdInstance = new CRCLCommandInstanceType();
        crcl.base.InitCanonType init = new InitCanonType();
        init.setCommandID(100);
        initCmdInstance.setCRCLCommand(init);
        CrclExiSocket instance = new CrclExiSocket();
        byte[] initBytes = instance.commandToEXI(initCmdInstance);
        //        LOGGER.log(Level.INFO,"result = " + Arrays.toString(result));
        CRCLCommandInstanceType returnCmd = instance.exiToCommand(initBytes);
        assertEquals(initCmdInstance.getCRCLCommand().getCommandID(),
                returnCmd.getCRCLCommand().getCommandID());
        CRCLCommandInstanceType getStatusCmdInstance = new CRCLCommandInstanceType();
        GetStatusType getStatus = new GetStatusType();
        getStatus.setCommandID(101);
        getStatusCmdInstance.setCRCLCommand(getStatus);
        byte[] getStatusBytes = instance.commandToEXI(getStatusCmdInstance);
        //        LOGGER.log(Level.INFO,"result = " + Arrays.toString(result));
        returnCmd = instance.exiToCommand(getStatusBytes);
        assertEquals(getStatusCmdInstance.getCRCLCommand().getCommandID(),
                returnCmd.getCRCLCommand().getCommandID());
        byte twocmdsArray[] = new byte[initBytes.length + getStatusBytes.length + 1];
        System.arraycopy(initBytes, 0, twocmdsArray, 0, initBytes.length);
        System.arraycopy(getStatusBytes, 0, twocmdsArray, initBytes.length, getStatusBytes.length);
        LOGGER.log(Level.INFO, "initBytes = {0}", Arrays.toString(initBytes));
        LOGGER.log(Level.INFO, "getStatusBytes = {0}", Arrays.toString(getStatusBytes));
        LOGGER.log(Level.INFO, "twocmdsArray = {0}", Arrays.toString(twocmdsArray));
        InputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(twocmdsArray);
            returnCmd = instance.readCommandFromEXIStream(inputStream);
            LOGGER.log(Level.INFO, "returnCmd = {0}", returnCmd);
            CRCLCommandType c = returnCmd.getCRCLCommand();
            LOGGER.log(Level.INFO, "c = {0}", c);
            assertEquals(init.getCommandID(),
                    returnCmd.getCRCLCommand().getCommandID());
            returnCmd = instance.readCommandFromEXIStream(inputStream);
            LOGGER.log(Level.INFO, "returnCmd = {0}", returnCmd);
            c = returnCmd.getCRCLCommand();
            LOGGER.log(Level.INFO, "c = {0}", c);
            assertEquals(getStatus.getCommandID(),
                    returnCmd.getCRCLCommand().getCommandID());
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (Exception exx) {
                    Logger.getLogger(CrclExiSocketTest.class.getName()).log(Level.FINEST, "exception normally ignored", exx);
                }
            }
        }
        LOGGER.log(Level.INFO, "");
        LOGGER.log(Level.INFO, "Start socket over TCP tests");

        System.setProperty("crcl.prefixEXISizeEnabled", "false");
        final ServerSocket ss = new ServerSocket(0);
        ExecutorService serv = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            LOGGER.log(Level.INFO, "i = {0}", i);
            Future<Socket> f = serv.submit(new Callable<Socket>() {

                @Override
                public Socket call() throws Exception {
                    return ss.accept();
                }
            });
            final CrclExiSocket csClient = new CrclExiSocket("127.0.0.1", ss.getLocalPort());
            csClient.setPrefixEXISizeEnabled(false);
            csClient.setEXIEnabled(true);
            CrclExiSocket csServer = new CrclExiSocket(f.get());
            csServer.setEXIEnabled(true);
            csServer.setPrefixEXISizeEnabled(false);
            final CRCLCommandInstanceType initCmdInstanceF = initCmdInstance;
            final CRCLCommandInstanceType getStatusCmdInstanceF = getStatusCmdInstance;
            final int MAX_J = 2;
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    Random rand = new Random(138);
//                try { Thread.sleep(2000); } catch(Exception e) {};
                    for (int j = 0; j < MAX_J; j++) {
                        try {
                            csClient.writeCommand(initCmdInstanceF, true);
                            csClient.writeCommand(getStatusCmdInstanceF, true);
                            if (rand.nextBoolean()) {
                                try {
                                    Thread.sleep(rand.nextInt(50));
                                } catch (Exception e) {
                                    Logger.getLogger(CrclExiSocketTest.class.getName()).log(Level.FINEST, "exception normally ignored", e);
                                }
                            }
                            LOGGER.log(Level.INFO, "j = {0}", j);
                        } catch (CRCLException ex) {
                            Logger.getLogger(CrclExiSocketTest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            };
            //            new Thread(r).start();
            r.run();
            for (int j = 0; j < MAX_J; j++) {
                LOGGER.log(Level.INFO, "i = {0}", i);
                LOGGER.log(Level.INFO, "j = {0}", j);
                csServer.setEXIEnabled(true);
//                LOGGER.log(Level.INFO, "csServer.available() = {0}", csServer.available());
                returnCmd = csServer.readCommand(true);
                LOGGER.log(Level.INFO, "returnCmd = {0}", returnCmd);
                CRCLCommandType c = returnCmd.getCRCLCommand();
                LOGGER.log(Level.INFO, "c = {0}", c);
                assertEquals(init.getCommandID(),
                        returnCmd.getCRCLCommand().getCommandID());
//                LOGGER.log(Level.INFO, "csServer.available() = {0}", csServer.available());
                returnCmd = csServer.readCommand(true);
                LOGGER.log(Level.INFO, "returnCmd = {0}", returnCmd);
                c = returnCmd.getCRCLCommand();
                LOGGER.log(Level.INFO, "c = {0}", c);
                assertEquals(getStatus.getCommandID(),
                        returnCmd.getCRCLCommand().getCommandID());
            }
            try {
                csServer.close();
            } catch (Exception e) {
                Logger.getLogger(CrclExiSocketTest.class.getName()).log(Level.SEVERE, "exception normally ignored", e);
            }
            try {
                csClient.close();
            } catch (Exception e) {
                Logger.getLogger(CrclExiSocketTest.class.getName()).log(Level.FINEST, "exception normally ignored", e);
            }
        }
        try {
            ss.close();
        } catch (Exception e) {
            Logger.getLogger(CrclExiSocketTest.class.getName()).log(Level.FINEST, "exception normally ignored", e);
        }
        try {
            serv.shutdownNow();
        } catch (Exception e) {
            Logger.getLogger(CrclExiSocketTest.class.getName()).log(Level.FINEST, "exception normally ignored", e);
        }
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            getStatusCmdInstance = new CRCLCommandInstanceType();
            getStatusCmdInstance.setCRCLCommand(init);
            instance.writeEXICommandToStream(outputStream, getStatusCmdInstance);
            getStatusCmdInstance = new CRCLCommandInstanceType();
            getStatusCmdInstance.setCRCLCommand(getStatus);
            instance.writeEXICommandToStream(outputStream, getStatusCmdInstance);
            byte ba[] = outputStream.toByteArray();
            LOGGER.log(Level.INFO, "ba = {0}", Arrays.toString(ba));
            Assert.assertArrayEquals(ba, Arrays.copyOf(twocmdsArray, twocmdsArray.length - 1));
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (Exception exx) {
                    Logger.getLogger(CrclExiSocketTest.class.getName()).log(Level.FINEST, "exception normally ignored", exx);
                }
            }
        }
    }

    /**
     * Test of readCommandFromStream method, of class CrclExiSocket.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testReadCommandFromStream() throws Exception {
        System.out.println("readCommandFromStream");
//        byte ba[] = new byte[MOVETHROUGHTO_XML.length()*2];
//        System.arraycopy(MOVETHROUGHTO_XML.getBytes(), 0, ba, 0, MOVETHROUGHTO_XML.length());
        //System.arraycopy(MOVETHROUGHTO_XML.getBytes(), 0, ba, MOVETHROUGHTO_XML.length(), MOVETHROUGHTO_XML.length());
        InputStream is = new ByteArrayInputStream(MOVETHROUGHTO_XML.getBytes());
        boolean validate = true;
        CrclExiSocket instance = new CrclExiSocket();
        CRCLCommandInstanceType expResult = null;
        CRCLCommandInstanceType result = instance.readCommandFromStream(is, validate);
        final CRCLCommandType c = result.getCRCLCommand();
        assertTrue(c != null && c instanceof MoveThroughToType);
        final MoveThroughToType moveCommand = (MoveThroughToType) c;
        assertEquals(2, c.getCommandID());
        assertEquals(2, moveCommand.getNumPositions());
    }

    /**
     * Test of readStatusFromStream method, of class CrclExiSocket.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testReadStatusFromStream() throws Exception {
        System.out.println("readStatusFromStream");
        InputStream is = new ByteArrayInputStream(STATUS_XML.getBytes());
        boolean validate = false;
        CrclExiSocket instance = new CrclExiSocket();
        CRCLStatusType result = instance.readStatusFromStream(is, validate);
        assertEquals(1, result.getCommandStatus().getCommandID());
        assertEquals(1, result.getCommandStatus().getStatusID());
    }

}
