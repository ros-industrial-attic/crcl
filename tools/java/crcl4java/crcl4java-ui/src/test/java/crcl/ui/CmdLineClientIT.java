/* 
 * This is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * 
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain. This software is experimental.
 * NIST assumes no responsibility whatsoever for its use by other
 * parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic. We would appreciate
 * acknowledgment if the software is used. This software can be
 * redistributed and/or modified freely provided that any derivative works
 * bear some notice that they are derived from it, and any modified
 * versions bear some notice that they have been modified.
 * 
 */
package crcl.ui;

import crcl.ui.server.CmdLineSimServer;
import crcl.ui.server.SimServerInner;
import crcl.ui.client.CmdLineClient;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.util.Objects.requireNonNull;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class CmdLineClientIT {

    private static final Logger LOGGER = Logger.getLogger(CmdLineClientIT.class.getName());
    private static final String DELAY_STRING = "0";

    public CmdLineClientIT() {
        System.setProperty("java.awt.headless", "true");
    }

    /**
     * Test of main method, of class CmdLineClient.
     */
    @Test
    public void testMain() {
        try {
            SimServerInner.setTesting(true);
            System.err.println("");
            System.err.flush();
            LOGGER.log(Level.INFO, "");
            LOGGER.log(Level.INFO, "Begin CmdLineClientIT.testMain");
            LOGGER.log(Level.INFO, "user.dir={0}", System.getProperty("user.dir"));
            CmdLineClient.setProgramSucceeded(false);
            URL programURL = requireNonNull(
                    CmdLineClientIT.class.getResource("/main/programAll.xml"),
                    "CmdLineClientIT.class.getResource(\"/main/programAll.xml\")"
            );
            Path programPath = Paths.get(programURL.toURI());
            System.setProperty("crcl4java.program", programPath.toString());
            System.setProperty("crcl4java.port", "0");
            URL initPoseURL = requireNonNull(
                    CmdLineClientIT.class.getResource("/main/initPose.csv"),
                    "CmdLineClientIT.class.getResource(\"/main/initPose.csv\")"
            );
            Path initPosePath = Paths.get(initPoseURL.toURI());
            System.setProperty("crcl4java.program", programPath.toString());
            System.setProperty("crcl4java.port", "0");

            System.setProperty("crcl4java.simserver.validateXML", "true");// validateXML
            System.setProperty("crcl4java.simserver.sendStatusWithoutRequest", "false");// sendStatusWithoutRequest
            System.setProperty("crcl4java.simserver.appendZero", "false");// appendZero
            System.setProperty("crcl4java.simserver.randomPacket", "false");// randomPacket
            System.setProperty("crcl4java.simserver.replaceState", "false");// replaceState
            System.setProperty("crcl4java.simserver.debugMoveDone", "true");// debugMoveDone
            System.setProperty("crcl4java.simserver.debugReadCommand", "true");// debugReadCommand
            System.setProperty("crcl4java.simserver.replaceXmlHeader", "true");// replaceXmlHeader
            System.setProperty("crcl4java.simserver.debugSendStatus", "true");// debugSendStatus
            System.setProperty("crcl4java.simserver.exiSelected", "false");// exiSelected

            System.setProperty("crcl4java.client.validateXML", "true");// validateXML
            System.setProperty("crcl4java.client.replaceState", "false");// validateXML
            System.setProperty("crcl4java.client.debugWaitForDone", "false");// debugWaitForDone
            System.setProperty("crcl4java.client.debugSendCommand", "true");// debugSendCommand
            System.setProperty("crcl4java.client.recordPose", "true");// recordPose
            System.setProperty("crcl4java.client.exiSelected", "false");// exiSelected
            System.setProperty("crcl4java.client.useReadStatusThreadSelected", "false");
            System.setProperty("crcl.utils.SimServerInner.enableGetStatusIDCheck", "true");
            System.setProperty("crcl4java.maxdwell", "0.01");
//            System.setProperty("crcl.prefixEXISizeEnabled", "true");
            CmdLineSimServer.testMain(new String[]{
                "--delayMillis", "10",
                "--jointSpeedMax", "1000.0",
                "--initPose", initPosePath.toString(),
                "--maxTransSpeed", "200.0",
                "--maxTransAccel", "200.0"
            });
            CmdLineClient.main(new String[]{"--waitForDoneDelay", DELAY_STRING, "--useTempSchemaCopies", "true"});
            if (!CmdLineClient.getProgramSucceeded()) {
                LOGGER.log(Level.SEVERE, "CmdLineSimServer.simServerInner.getStatus = {0}", CmdLineSimServer.getStatusXmlString());
//                Thread.getAllStackTraces().entrySet().forEach((x) -> {
//                    System.err.println("Thread:" + x.getKey().getName());
//                    Arrays.stream(x.getValue()).forEach((xx) -> {
//                        System.err.println("\t" + xx);
//                    });
//                    System.err.println("");
//                });
                fail("Program did NOT succeed.");
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            fail("Exception thrown");
        }
        CmdLineSimServer.closeServer();
        LOGGER.log(Level.INFO, "End CmdLineClientIT.testMain");
        System.err.println("");
        System.out.flush();
        System.err.flush();
        LOGGER.log(Level.INFO, "");
    }

    /**
     * Test of main method, of class CmdLineClient.
     */
    @Test
    public void testAutoRunTest() {
        try {
            SimServerInner.setTesting(true);
            System.err.println("");
            System.err.flush();
            LOGGER.log(Level.INFO, "");
            LOGGER.log(Level.INFO, "Begin CmdLineClientIT.testMain");
            LOGGER.log(Level.INFO, "user.dir={0}", System.getProperty("user.dir"));
            CmdLineClient.setProgramSucceeded(false);
            System.setProperty("crcl4java.port", "0");
            URL initPoseURL = requireNonNull(
                    CmdLineClientIT.class.getResource("/main/initPose.csv"),
                    "CmdLineClientIT.class.getResource(\"/main/initPose.csv\")"
            );
            Path initPosePath = Paths.get(initPoseURL.toURI());
            System.setProperty("crcl4java.port", "0");

            System.setProperty("crcl4java.simserver.validateXML", "true");// validateXML
            System.setProperty("crcl4java.simserver.sendStatusWithoutRequest", "false");// sendStatusWithoutRequest
            System.setProperty("crcl4java.simserver.appendZero", "false");// appendZero
            System.setProperty("crcl4java.simserver.randomPacket", "false");// randomPacket
            System.setProperty("crcl4java.simserver.replaceState", "false");// replaceState
            System.setProperty("crcl4java.simserver.debugMoveDone", "true");// debugMoveDone
            System.setProperty("crcl4java.simserver.debugReadCommand", "true");// debugReadCommand
            System.setProperty("crcl4java.simserver.replaceXmlHeader", "true");// replaceXmlHeader
            System.setProperty("crcl4java.simserver.debugSendStatus", "true");// debugSendStatus
            System.setProperty("crcl4java.simserver.exiSelected", "false");// exiSelected

            System.setProperty("crcl4java.client.validateXML", "true");// validateXML
            System.setProperty("crcl4java.client.replaceState", "false");// validateXML
            System.setProperty("crcl4java.client.debugWaitForDone", "false");// debugWaitForDone
            System.setProperty("crcl4java.client.debugSendCommand", "true");// debugSendCommand
            System.setProperty("crcl4java.client.recordPose", "true");// recordPose
            System.setProperty("crcl4java.client.exiSelected", "false");// exiSelected
            System.setProperty("crcl4java.client.useReadStatusThreadSelected", "false");
            System.setProperty("crcl4java.simserver.teleportToGoals", "true");
            System.setProperty("crcl4java.maxdwell", "0.01");
//          System.setProperty("crcl.utils.SimServerInner.enableGetStatusIDCheck", "true");
//            System.setProperty("crcl.prefixEXISizeEnabled", "true");
            CmdLineSimServer.testMain(new String[]{
                "--delayMillis", "10",
                "--jointSpeedMax", "1000.0",
                "--initPose", initPosePath.toString(),
                "--maxTransSpeed", "200.0",
                "--maxTransAccel", "200.0",});
            CmdLineClient.main(new String[]{
                "--waitForDoneDelay", DELAY_STRING,
                "--autoRunTest", "true",
                "--useTempSchemaCopies", "true"
            });
            if (!CmdLineClient.getProgramSucceeded()) {
                LOGGER.log(Level.SEVERE, 
                        "CmdLineSimServer.simServerInner.getStatus = {0}", 
                        CmdLineSimServer.getStatusXmlString());
//                Thread.getAllStackTraces().entrySet().forEach((x) -> {
//                    System.err.println("Thread:" + x.getKey().getName());
//                    Arrays.stream(x.getValue()).forEach((xx) -> {
//                        System.err.println("\t" + xx);
//                    });
//                    System.err.println("");
//                });
                fail("Program did NOT succeed.");
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            fail("Exception thrown");
        }
        CmdLineSimServer.closeServer();
        LOGGER.log(Level.INFO, "End CmdLineClientIT.testMain");
        System.err.println("");
        System.out.flush();
        System.err.flush();
        LOGGER.log(Level.INFO, "");
    }

    /**
     * Test of main method, of class CmdLineClient.
     */
    @Test
    public void testMainEXI() {
        try {
            CmdLineSimServer.closeServer();
            System.err.println("");
            System.err.flush();
            LOGGER.log(Level.INFO, "");
            LOGGER.log(Level.INFO, "Begin CmdLineClientIT.testMainEXI");
            LOGGER.log(Level.INFO, "user.dir={0}", System.getProperty("user.dir"));
            CmdLineClient.setProgramSucceeded(false);
            URL programURL = requireNonNull(
                    CmdLineClientIT.class.getResource("/main/programAll.xml"),
                    "CmdLineClientIT.class.getResource(\"/main/programAll.xml\")"
            );
            Path programPath = Paths.get(programURL.toURI());
            System.setProperty("crcl4java.program", programPath.toString());
            System.setProperty("crcl4java.port", "0");
            URL initPoseURL = requireNonNull(
                    CmdLineClientIT.class.getResource("/main/initPose.csv"),
                    "CmdLineClientIT.class.getResource(\"/main/initPose.csv\")"
            );
            Path initPosePath = Paths.get(initPoseURL.toURI());
            System.setProperty("crcl4java.program", programPath.toString());
            System.setProperty("crcl4java.port", "0");

            System.setProperty("crcl4java.simserver.validateXML", "false");// validateXML
            System.setProperty("crcl4java.simserver.sendStatusWithoutRequest", "false");// sendStatusWithoutRequest
            System.setProperty("crcl4java.simserver.appendZero", "false");// appendZero
            System.setProperty("crcl4java.simserver.randomPacket", "false");// randomPacket
            System.setProperty("crcl4java.simserver.replaceState", "false");// replaceState
            System.setProperty("crcl4java.simserver.debugMoveDone", "true");// debugMoveDone
            System.setProperty("crcl4java.simserver.debugReadCommand", "true");// debugReadCommand
            System.setProperty("crcl4java.simserver.replaceXmlHeader", "false");// replaceXmlHeader
            System.setProperty("crcl4java.simserver.debugSendStatus", "true");// debugSendStatus
            System.setProperty("crcl4java.simserver.exiSelected", "true");// exiSelected
            System.setProperty("crcl4java.simserver.enableGetStatusIDCheck", "true");

            System.setProperty("crcl4java.client.validateXML", "false");// validateXML
            System.setProperty("crcl4java.client.replaceState", "false");// validateXML
            System.setProperty("crcl4java.client.debugWaitForDone", "false");// debugWaitForDone
            System.setProperty("crcl4java.client.debugSendCommand", "true");// debugSendCommand
            System.setProperty("crcl4java.client.recordPose", "true");// recordPose
            System.setProperty("crcl4java.client.exiSelected", "true");// exiSelected
            System.setProperty("crcl.prefixEXISizeEnabled", "false");
            System.setProperty("crcl4java.simserver.teleportToGoals", "true");
            System.setProperty("crcl4java.client.useReadStatusThreadSelected", "false");
            CmdLineSimServer.testMain(new String[]{
                "--delayMillis", "10",
                "--jointSpeedMax", "1000.0",
                "--initPose", initPosePath.toString(),
                "--maxTransSpeed", "200.0",
                "--maxTransAccel", "200.0"
            });
            CmdLineClient.main(new String[]{
                "--waitForDoneDelay", DELAY_STRING,
                "--useTempSchemaCopies", "true"
            });
            if (!CmdLineClient.getProgramSucceeded()) {
                System.err.println("CmdLineSimServer.simServerInner.getStatus = "
                        + CmdLineSimServer.getStatusXmlString());
//                Thread.getAllStackTraces().entrySet().forEach((x) -> {
//                    System.err.println("Thread:" + x.getKey().getName());
//                    Arrays.stream(x.getValue()).forEach((xx) -> {
//                        System.err.println("\t" + xx);
//                    });
//                    System.err.println("");
//                });
                fail("Program did NOT succeed.");
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            fail("Exception thrown: " + ex);
        }
        CmdLineSimServer.closeServer();
        LOGGER.log(Level.INFO, "End CmdLineClientIT.testMainEXI");
        System.err.println("");
        System.out.flush();
        System.err.flush();
        LOGGER.log(Level.INFO, "");
    }

}
