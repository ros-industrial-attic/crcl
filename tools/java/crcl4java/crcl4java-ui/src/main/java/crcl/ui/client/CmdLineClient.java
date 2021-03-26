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
 */
package crcl.ui.client;

import crcl.base.CRCLProgramType;
import crcl.ui.DefaultSchemaFiles;
import crcl.utils.CRCLException;
import crcl.utils.stubs.PendantClientOuterStub;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;
import rcs.posemath.PmException;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class CmdLineClient {

    private static boolean programSucceeded = false;
    private static int programSucceededGetCount = 0;
    private static int programSucceededSetCount = 0;

    public static synchronized boolean getProgramSucceeded() {
        programSucceededGetCount++;
        return programSucceeded;
    }

    public static synchronized void setProgramSucceeded(boolean val) {
        programSucceededSetCount++;
        programSucceeded = val;
    }

    public static synchronized int getProgramSucceededGetCount() {
        return programSucceededGetCount;
    }

    public static synchronized int getProgramSucceededSetCount() {
        return programSucceededSetCount;
    }

    public static synchronized void main(String[] args) {
        try {
            String poseFileName = null;
            boolean autoRunTest = false;
            boolean useTempSchemaCopies = false;
            for (int i = 0; i < args.length - 1; i++) {
                switch (args[i]) {
                    case "--waitForDoneDelay":
                        System.setProperty("PendantClient.waitForDoneDelay", args[i + 1]);
                        i++;
                        break;

                    case "--poseFileName":
                        poseFileName = args[i + 1];
                        i++;
                        break;

                    case "--autoRunTest":
                        autoRunTest = Boolean.valueOf(args[i + 1]);
                        i++;
                        break;

                    case "--useTempSchemaCopies":
                        useTempSchemaCopies = Boolean.valueOf(args[i + 1]);
                        i++;
                        break;

                    default:
                        System.err.println("Unrecognized argument: " + args[i]);
                        break;
                }
            }
            programSucceeded = false;
            System.setProperty("crcjava.PendandClient.useReadStatusThreadSelected", "false");
            PendantClientOuterStub pendantClientOuterStub = new PendantClientOuterStub();
            CrclSwingClientInner pendantClientInner = new CrclSwingClientInner(pendantClientOuterStub, 
                    useTempSchemaCopies?DefaultSchemaFiles.temp():DefaultSchemaFiles.instance());
            pendantClientInner.setQuitOnTestCommandFailure(true);
            final String programPropertyString = System.getProperty("crcl4java.program");
            pendantClientInner.connect(pendantClientOuterStub.getHost(), pendantClientOuterStub.getPort());
            if (!pendantClientInner.isConnected()) {
                Logger.getLogger(CmdLineClient.class.getName()).log(Level.SEVERE, "Failed to connect to {0}:{1}",
                        new Object[]{pendantClientOuterStub.getHost(), pendantClientOuterStub.getPort()});
                return;
            }
            if (autoRunTest) {
                programSucceeded = pendantClientInner.runTest(pendantClientInner.getDefaultTestPropertiesMap());
            } else if (null != programPropertyString) {
                pendantClientInner.openXmlProgramFile(new File(programPropertyString), false);
                CRCLProgramType program = Objects.requireNonNull(pendantClientInner.getProgram());
                programSucceeded = pendantClientInner.runProgram(program, 0);
                System.out.println("Program " + programPropertyString + " succeeded: " + programSucceeded);
            } else {
                System.err.println("No program specified");
                System.err.println("Use --program option to set a program to run.");
                pendantClientInner.disconnect();
                System.exit(1);
            }
            if (null != poseFileName) {
                try {
                    pendantClientInner.savePoseListToCsvFile(poseFileName);
                } catch (PmException ex) {
                    Logger.getLogger(CmdLineClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(CmdLineClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            pendantClientInner.disconnect();

        } catch (Exception ex) {
            Logger.getLogger(CmdLineClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static final Logger LOG = Logger.getLogger(CmdLineClient.class.getName());
}
