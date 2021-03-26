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
 */
package crcl.ui.server;

import crcl.ui.DefaultSchemaFiles;
import crcl.utils.CRCLPosemath;
import crcl.utils.stubs.SimServerOuterStub;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.checkerframework.checker.nullness.qual.Nullable;
import rcs.posemath.Posemath;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class CmdLineSimServer {

    private static @Nullable
    SimServerInner simServerInner = null;

    public static synchronized @Nullable
    SimServerInner getSimServerInner() {
        return simServerInner;
    }

    public static synchronized String getStatusXmlString() throws Exception {
        if (null == simServerInner) {
            return "null";
        }
        return simServerInner.getStatusXmlString();
    }

    public static synchronized void closeServer() {
        if (null != simServerInner) {
            simServerInner.closeServer();
            simServerInner = null;
        }
    }

    public static void main(String[] args) {
        testMain(args, false);
//        try {
//            new BufferedReader(new InputStreamReader(System.in)).readLine();
//        } catch (IOException ex) {
//            Logger.getLogger(CmdLineSimServer.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public static void testMain(String[] args) {
        testMain(args, true);
    }

    public static void testMain(String[] args, boolean asDaemon) {
        try {
            double initPose[][] = null;
            boolean useTempSchemaCopies = false;
            for (int i = 0; i < args.length - 1; i++) {
                switch (args[i]) {
                    case "--mode":
                        i++;
                        break;

                    case "--delayMillis":
                        System.setProperty("SimServer.delayMillis", args[i + 1]);
                        i++;
                        break;

                    case "--jointSpeedMax":
                        System.setProperty("SimServer.jointSpeedMax", args[i + 1]);
                        i++;
                        break;

                    case "--maxTransSpeed":
                        System.setProperty("SimServer.maxTransSpeed", args[i + 1]);
                        i++;
                        break;

                    case "--maxTransAccel":
                        System.setProperty("SimServer.maxTransAccel", args[i + 1]);
                        i++;
                        break;

                    case "--initPose":
                        initPose = Posemath.csvToHomMats(args[i + 1], 0, 1, 2, 3, 4, 5);
                        i++;
                        break;

                    case "--useTempSchemaCopies":
                        useTempSchemaCopies = Boolean.valueOf(args[i + 1]);
                        i++;
                        break;

                    default:
                        System.err.println("Unrecognized argument for CmdLineSimServer: " + args[i]);
                        System.err.println("args = " + Arrays.toString(args));
                        break;
                }
            }
            SimServerOuterStub simServerOuterStub = new SimServerOuterStub();
            simServerInner = new SimServerInner(simServerOuterStub,
                    useTempSchemaCopies ? DefaultSchemaFiles.temp() : DefaultSchemaFiles.instance());
            if (null != initPose) {
                simServerInner.simulatedTeleportToPose(CRCLPosemath.toPose(initPose));
            }
            simServerInner.restartServer(asDaemon);

        } catch (ParserConfigurationException | IOException ex) {
            Logger.getLogger(CmdLineSimServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static final Logger LOG = Logger.getLogger(CmdLineSimServer.class.getName());
}
