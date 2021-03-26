/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * 
 */
package crcl.utils;

import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLProgramType;
import crcl.base.EndCanonType;
import crcl.base.InitCanonType;
import crcl.base.MiddleCommandType;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * 
 * NOTE: Comments beginning with {@literal @} or {@literal >>>} are used by Checker Framework Comments
 * beginning with {@literal @} must have no spaces in the comment or Checker will ignore
 * it.
 *
 * See http://types.cs.washington.edu/checker-framework for null pointer
 * checks. This file can be compiled without the Checker Framework, but using
 * the framework allows potential NullPointerExceptions to be found.
 */

 /*>>>
import org.checkerframework.checker.nullness.qual.*;
 */
public class CRCLSocketIT {

    public CRCLSocketIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        CRCLSchemaUtils.clearSchemas();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of stringToCommand method, of class CRCLSocket.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testStringToCommand() throws Exception {
        System.out.println("stringToCommand");
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<CRCLCommandInstance\n"
                + "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
                + "  xsi:noNamespaceSchemaLocation=\"../xmlSchemas/CRCLCommandInstance.xsd\">\n"
                + "  <CRCLCommand xsi:type=\"InitCanonType\">\n"
                + "    <CommandID>1</CommandID>\n"
                + "  </CRCLCommand>\n"
                + "</CRCLCommandInstance>";
        boolean validate = false;
        CRCLSocket instance = new CRCLSocket();
        CRCLCommandInstanceType result = instance.stringToCommand(str, validate);
        final CRCLCommandType crclCommand = result.getCRCLCommand();
        assertTrue(crclCommand instanceof InitCanonType);
        if (null != crclCommand) {
            assertTrue(crclCommand.getCommandID() == 1);
        }
//        fail("forced failure");
    }

    private @Nullable
    Exception serverThreadEx = null;

    private boolean interruptingServer = false;
    private boolean timeoutOccured = false;

    private enum ExampleType {
        BLOCKING, POLLING, CALLBACK
    };

    private void testClientServer(final String testName,
            final ExampleType clientType,
            final ExampleType serverType,
            final boolean serverMultiThreaded) throws InterruptedException {
        interruptingServer = false;
        serverThreadEx = null;
        timeoutOccured = false;
        System.out.println(testName);
        System.setProperty("CRCLServerSocket.multithreaded", Boolean.toString(serverMultiThreaded));
        final Thread serverThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    switch (serverType) {
                        case BLOCKING:
                            CRCLServerSocketBlockingExample.main(new String[]{});
                            break;

                        case POLLING:
                            CRCLServerSocketPollingExample.main(new String[]{});
                            break;

                        case CALLBACK:
                            CRCLServerSocketCallbackExample.main(new String[]{});
                            break;
                    }

                } catch (InterruptedException ex) {
                    if (!interruptingServer) {
                        serverThreadEx = ex;
                        Logger.getLogger(CRCLSocketIT.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (Exception ex) {
                    serverThreadEx = ex;
                    Logger.getLogger(CRCLSocketIT.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    Logger.getLogger(CRCLSocketIT.class.getName()).log(Level.FINE, testName + " Server finished.");
                }
            }
        }, testName + "ServerMain");

        serverThread.start();
        Thread.sleep(200);
        assertTrue("this.serverThreadEx(" + this.serverThreadEx + ") == null", this.serverThreadEx == null);
        final PrintStream out = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final PrintStream newout = new PrintStream(baos);
        final Thread clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.setOut(newout);
                    switch (clientType) {
                        case BLOCKING:
                            CRCLSocketBlockingClientExample.main(new String[]{});
                            break;

                        case POLLING:
                            CRCLSocketPollingClientExample.main(new String[]{});
                            break;

                        default:
                            System.setOut(out);
                            fail("Bad clientType" + clientType);
                    }
                    System.setOut(out);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CRCLSocketIT.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    Logger.getLogger(CRCLSocketIT.class.getName()).log(Level.FINE, testName + " client finished.");
                }
            }
        }, testName + "client");

        Thread timeoutThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    timeoutOccured = true;
                    Map<Thread, String> allThreads = new HashMap<>();
                    Map<Thread, StackTraceElement[]> traces = Thread.getAllStackTraces();
                    for (Entry<Thread, StackTraceElement[]> entry : traces.entrySet()) {
                        allThreads.put(entry.getKey(), Arrays.deepToString(entry.getValue()) + "\n");
                    }
                    Logger.getLogger(CRCLSocketIT.class.getName()).log(Level.SEVERE, "Timedout with Thread.getAllStackTraces()=" + allThreads);
                    serverThread.interrupt();
                    clientThread.interrupt();
                } catch (InterruptedException ex) {
                    //Logger.getLogger(CRCLSocketIT.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, testName + "timeout");
        timeoutThread.start();
        clientThread.start();
        clientThread.join();
        assertFalse("timeoutOccured", timeoutOccured);
        newout.flush();
        String clientoutput = baos.toString();
        System.setOut(out);
        assertTrue("\"" + clientoutput + "\".contains(\"CommandID = 8\")", clientoutput.contains("CommandID = 8"));
        assertTrue("\"" + clientoutput + "\".contains(\"State = CRCL_DONE\")", clientoutput.contains("State = CRCL_DONE"));
        assertTrue("\"" + clientoutput + "\".contains(\"pose = 1.1,0.0,0.1\")", clientoutput.contains("pose = 1.1,0.0,0.1"));
//        System.out.println("clientoutput = " + clientoutput);
        assertTrue(this.serverThreadEx == null);
        interruptingServer = true;
        timeoutThread.interrupt();
        timeoutThread.join();
        serverThread.interrupt();
        serverThread.join();
        System.out.println("end " + testName);
        this.serverThreadEx = null;
        interruptingServer = false;
    }

    /**
     * Test that Blocking Client works with Blocking Single-Threaded Server
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testClientBlockingServerBlockingSingleThreaded() throws Exception {
        testClientServer("testClientBlockingServerBlockingSingleThreaded",
                ExampleType.BLOCKING,
                ExampleType.BLOCKING,
                false);
    }

    /**
     * Test that Blocking Client works with Blocking Single-Threaded Server
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testClientBlockingServerBlockingMultiThreaded() throws Exception {
        testClientServer("testClientBlockingServerBlockingMultiThreaded",
                ExampleType.BLOCKING,
                ExampleType.BLOCKING,
                true);
    }

    /**
     * Test that Blocking Client works with Blocking Single-Threaded Server
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testClientPollingServerBlockingSingleThreaded() throws Exception {
        testClientServer("testClientPollingServerBlockingSingleThreaded",
                ExampleType.POLLING,
                ExampleType.BLOCKING,
                false);
    }

    /**
     * Test that Blocking Client works with Blocking Single-Threaded Server
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testClientPollingServerBlockingMultiThreaded() throws Exception {
        testClientServer("testClientBlockingServerBlockingMultiThreaded",
                ExampleType.POLLING,
                ExampleType.BLOCKING,
                true);
    }

    /**
     * Test that Blocking Client works with Blocking Single-Threaded Server
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testClientPollingServerPollingSingleThreaded() throws Exception {
        testClientServer("testClientPollingServerPollingSingleThreaded",
                ExampleType.POLLING,
                ExampleType.POLLING,
                false);
    }

    /**
     * Test that Blocking Client works with Blocking Single-Threaded Server
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testClientPollingServerPollingMultiThreaded() throws Exception {
        testClientServer("testClientBlockingServerBlockingMultiThreaded",
                ExampleType.POLLING,
                ExampleType.POLLING,
                true);
    }

    /**
     * Test that Blocking Client works with Blocking Single-Threaded Server
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testClientBlockingServerPollingSingleThreaded() throws Exception {
        testClientServer("testClientBlockingServerPollingSingleThreaded",
                ExampleType.BLOCKING,
                ExampleType.POLLING,
                false);
    }

    /**
     * Test that Blocking Client works with Blocking Single-Threaded Server
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testClientBlockingServerPollingMultiThreaded() throws Exception {
        testClientServer("testClientBlockingServerPollingMultiThreaded",
                ExampleType.BLOCKING,
                ExampleType.POLLING,
                true);
    }

    /**
     * Test that Blocking Client works with Blocking Single-Threaded Server
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testClientPollingServerCallbackSingleThreaded() throws Exception {
        testClientServer("testClientPollingServerCallbackSingleThreaded",
                ExampleType.POLLING,
                ExampleType.CALLBACK,
                false);
    }

    /**
     * Test that Blocking Client works with Blocking Single-Threaded Server
     *
     * @throws java.lang.Exception
     */
    private void testClientPollingServerCallbackMultiThreaded() throws Exception {
        testClientServer("testClientPollingServerCallbackMultiThreaded",
                ExampleType.POLLING,
                ExampleType.CALLBACK,
                true);
    }

    /**
     * Test that Blocking Client works with Blocking Single-Threaded Server
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testClientBlockingServerCallbackSingleThreaded() throws Exception {
        testClientServer("testClientBlockingServerCallbackSingleThreaded",
                ExampleType.BLOCKING,
                ExampleType.CALLBACK,
                false);
    }

    /**
     * Test that Blocking Client works with Blocking Single-Threaded Server
     *
     * @throws java.lang.Exception
     */
    private void testClientBlockingServerCallbackMultiThreaded() throws Exception {
        testClientServer("testClientBlockingServerCallbackMultiThreaded",
                ExampleType.BLOCKING,
                ExampleType.CALLBACK,
                true);
    }

    /**
     * Test of stringToProgram method, of class CRCLSocket.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testStringToProgram() throws Exception {
        System.out.println("stringToProgram");
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<CRCLProgram\n"
                + "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
                + "  xsi:noNamespaceSchemaLocation=\"../xmlSchemas/CRCLProgramInstance.xsd\">\n"
                + "  <InitCanon>\n"
                + "    <CommandID>1</CommandID>\n"
                + "  </InitCanon>\n"
                + "  <MiddleCommand xsi:type=\"SetLengthUnitsType\">\n"
                + "    <CommandID>2</CommandID>\n"
                + "    <UnitName>meter</UnitName>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"SetTransSpeedType\">\n"
                + "    <CommandID>3</CommandID>\n"
                + "    <TransSpeed xsi:type=\"TransSpeedAbsoluteType\">\n"
                + "      <Setting>1.0</Setting>\n"
                + "    </TransSpeed>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"SetTransAccelType\">\n"
                + "    <CommandID>4</CommandID>\n"
                + "    <TransAccel xsi:type=\"TransAccelRelativeType\">\n"
                + "      <Fraction>0.80</Fraction>\n"
                + "    </TransAccel>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"SetEndPoseToleranceType\">\n"
                + "    <CommandID>5</CommandID>\n"
                + "    <Tolerance>\n"
                + "      <XPointTolerance>0.002</XPointTolerance>\n"
                + "      <YPointTolerance>0.002</YPointTolerance>\n"
                + "      <ZPointTolerance>0.002</ZPointTolerance>\n"
                + "    </Tolerance>\n"
                + "   </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"SetIntermediatePoseToleranceType\">\n"
                + "    <CommandID>6</CommandID>\n"
                + "    <Tolerance>\n"
                + "      <XPointTolerance>0.01</XPointTolerance>\n"
                + "      <YPointTolerance>0.01</YPointTolerance>\n"
                + "      <ZPointTolerance>0.01</ZPointTolerance>\n"
                + "    </Tolerance>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"SetEndEffectorType\">\n"
                + "    <CommandID>7</CommandID>\n"
                + "    <Setting>0</Setting>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"MoveThroughToType\">\n"
                + "    <CommandID>8</CommandID>\n"
                + "    <MoveStraight>false</MoveStraight>\n"
                + "    <Waypoint>\n"
                + "      <Point>\n"
                + "        <X>1.5</X> <Y>1</Y> <Z>1</Z>\n"
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
                + "        <X>1.5</X> <Y>1</Y> <Z>0.0001</Z>\n"
                + "      </Point>\n"
                + "      <XAxis>\n"
                + "        <I>1</I> <J>0</J> <K>0</K>\n"
                + "      </XAxis>\n"
                + "      <ZAxis>\n"
                + "        <I>0</I> <J>0</J> <K>-1</K>\n"
                + "      </ZAxis>\n"
                + "    </Waypoint>\n"
                + "    <NumPositions>2</NumPositions>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"SetEndEffectorType\">\n"
                + "    <CommandID>9</CommandID>\n"
                + "    <Setting>1</Setting>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"MoveThroughToType\">\n"
                + "    <CommandID>10</CommandID>\n"
                + "    <MoveStraight>false</MoveStraight>\n"
                + "    <Waypoint>\n"
                + "      <Point>\n"
                + "        <X>1.5</X> <Y>1</Y> <Z>1</Z>\n"
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
                + "        <X>4</X> <Y>1</Y> <Z>1</Z>\n"
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
                + "        <X>4</X> <Y>1</Y> <Z>0.5001</Z>\n"
                + "      </Point>\n"
                + "      <XAxis>\n"
                + "        <I>1</I> <J>0</J> <K>0</K>\n"
                + "      </XAxis>\n"
                + "      <ZAxis>\n"
                + "        <I>0</I> <J>0</J> <K>-1</K>\n"
                + "      </ZAxis>\n"
                + "    </Waypoint>\n"
                + "    <NumPositions>3</NumPositions>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"SetEndEffectorType\">\n"
                + "    <CommandID>11</CommandID>\n"
                + "    <Setting>0</Setting>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"MoveThroughToType\">\n"
                + "    <CommandID>12</CommandID>\n"
                + "    <MoveStraight>false</MoveStraight>\n"
                + "    <Waypoint>\n"
                + "      <Point>\n"
                + "        <X>4</X> <Y>1</Y> <Z>1</Z>\n"
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
                + "        <X>8.25</X> <Y>1</Y> <Z>1</Z>\n"
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
                + "        <X>8.25</X> <Y>1</Y> <Z>0.4</Z>\n"
                + "      </Point>\n"
                + "      <XAxis>\n"
                + "        <I>1</I> <J>0</J> <K>0</K>\n"
                + "      </XAxis>\n"
                + "      <ZAxis>\n"
                + "        <I>0</I> <J>0</J> <K>-1</K>\n"
                + "      </ZAxis>\n"
                + "    </Waypoint>\n"
                + "    <NumPositions>3</NumPositions>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"OpenToolChangerType\">\n"
                + "    <CommandID>13</CommandID>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"MoveThroughToType\">\n"
                + "    <CommandID>14</CommandID>\n"
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
                + "    <Waypoint>\n"
                + "      <Point>\n"
                + "        <X>8.75</X> <Y>1</Y> <Z>1</Z>\n"
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
                + "        <X>8.75</X> <Y>1</Y> <Z>0.5</Z>\n"
                + "      </Point>\n"
                + "      <XAxis>\n"
                + "        <I>1</I> <J>0</J> <K>0</K>\n"
                + "      </XAxis>\n"
                + "      <ZAxis>\n"
                + "        <I>0</I> <J>0</J> <K>-1</K>\n"
                + "      </ZAxis>\n"
                + "    </Waypoint>\n"
                + "    <NumPositions>3</NumPositions>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"CloseToolChangerType\">\n"
                + "    <CommandID>15</CommandID>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"MoveThroughToType\">\n"
                + "    <CommandID>16</CommandID>\n"
                + "    <MoveStraight>false</MoveStraight>\n"
                + "    <Waypoint>\n"
                + "      <Point>\n"
                + "        <X>8.75</X> <Y>1</Y> <Z>1</Z>\n"
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
                + "        <X>5.659</X> <Y>1.1</Y> <Z>1.8</Z>\n"
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
                + "        <X>5.659</X> <Y>1.1</Y> <Z>0.1501</Z>\n"
                + "      </Point>\n"
                + "      <XAxis>\n"
                + "        <I>1</I> <J>0</J> <K>0</K>\n"
                + "      </XAxis>\n"
                + "      <ZAxis>\n"
                + "        <I>0</I> <J>0</J> <K>-1</K>\n"
                + "      </ZAxis>\n"
                + "    </Waypoint>\n"
                + "    <NumPositions>3</NumPositions>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"SetEndEffectorType\">\n"
                + "    <CommandID>17</CommandID>\n"
                + "    <Setting>1</Setting>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"MoveThroughToType\">\n"
                + "    <CommandID>18</CommandID>\n"
                + "    <MoveStraight>false</MoveStraight>\n"
                + "    <Waypoint>\n"
                + "      <Point>\n"
                + "        <X>5.659</X> <Y>1.1</Y> <Z>0.5</Z>\n"
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
                + "        <X>3.86</X> <Y>1.07</Y> <Z>1</Z>\n"
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
                + "        <X>3.86</X> <Y>1.07</Y> <Z>0.6501</Z>\n"
                + "      </Point>\n"
                + "      <XAxis>\n"
                + "        <I>1</I> <J>0</J> <K>0</K>\n"
                + "      </XAxis>\n"
                + "      <ZAxis>\n"
                + "        <I>0</I> <J>0</J> <K>-1</K>\n"
                + "      </ZAxis>\n"
                + "    </Waypoint>\n"
                + "    <NumPositions>3</NumPositions>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"SetEndEffectorType\">\n"
                + "    <CommandID>19</CommandID>\n"
                + "    <Setting>0</Setting>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"MoveThroughToType\">\n"
                + "    <CommandID>20</CommandID>\n"
                + "    <MoveStraight>false</MoveStraight>\n"
                + "    <Waypoint>\n"
                + "      <Point>\n"
                + "        <X>3.86</X> <Y>1.07</Y> <Z>1</Z>\n"
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
                + "        <X>5.659</X> <Y>0.9</Y> <Z>0.5</Z>\n"
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
                + "        <X>5.659</X> <Y>0.9</Y> <Z>0.1501</Z>\n"
                + "      </Point>\n"
                + "      <XAxis>\n"
                + "        <I>1</I> <J>0</J> <K>0</K>\n"
                + "      </XAxis>\n"
                + "      <ZAxis>\n"
                + "        <I>0</I> <J>0</J> <K>-1</K>\n"
                + "      </ZAxis>\n"
                + "    </Waypoint>\n"
                + "    <NumPositions>3</NumPositions>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"SetEndEffectorType\">\n"
                + "    <CommandID>21</CommandID>\n"
                + "    <Setting>1</Setting>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"MoveThroughToType\">\n"
                + "    <CommandID>22</CommandID>\n"
                + "    <MoveStraight>false</MoveStraight>\n"
                + "    <Waypoint>\n"
                + "      <Point>\n"
                + "        <X>5.659</X> <Y>0.9</Y> <Z>0.5</Z>\n"
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
                + "        <X>3.86</X> <Y>0.93</Y> <Z>1</Z>\n"
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
                + "        <X>3.86</X> <Y>0.93</Y> <Z>0.6501</Z>\n"
                + "      </Point>\n"
                + "      <XAxis>\n"
                + "        <I>1</I> <J>0</J> <K>0</K>\n"
                + "      </XAxis>\n"
                + "      <ZAxis>\n"
                + "        <I>0</I> <J>0</J> <K>-1</K>\n"
                + "      </ZAxis>\n"
                + "    </Waypoint>\n"
                + "    <NumPositions>3</NumPositions>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"SetEndEffectorType\">\n"
                + "    <CommandID>23</CommandID>\n"
                + "    <Setting>0</Setting>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"MoveThroughToType\">\n"
                + "    <CommandID>24</CommandID>\n"
                + "    <MoveStraight>false</MoveStraight>\n"
                + "    <Waypoint>\n"
                + "      <Point>\n"
                + "        <X>3.86</X> <Y>0.93</Y> <Z>1</Z>\n"
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
                + "        <X>6.42</X> <Y>1</Y> <Z>0.5</Z>\n"
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
                + "        <X>6.42</X> <Y>1</Y> <Z>0.1501</Z>\n"
                + "      </Point>\n"
                + "      <XAxis>\n"
                + "        <I>1</I> <J>0</J> <K>0</K>\n"
                + "      </XAxis>\n"
                + "      <ZAxis>\n"
                + "        <I>0</I> <J>0</J> <K>-1</K>\n"
                + "      </ZAxis>\n"
                + "    </Waypoint>\n"
                + "    <NumPositions>3</NumPositions>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"SetEndEffectorType\">\n"
                + "    <CommandID>25</CommandID>\n"
                + "    <Setting>1</Setting>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"MoveThroughToType\">\n"
                + "    <CommandID>26</CommandID>\n"
                + "    <MoveStraight>false</MoveStraight>\n"
                + "    <Waypoint>\n"
                + "      <Point>\n"
                + "        <X>6.42</X> <Y>1</Y> <Z>0.5</Z>\n"
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
                + "        <X>4.14</X> <Y>0.93</Y> <Z>1</Z>\n"
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
                + "        <X>4.14</X> <Y>0.93</Y> <Z>0.6501</Z>\n"
                + "      </Point>\n"
                + "      <XAxis>\n"
                + "        <I>1</I> <J>0</J> <K>0</K>\n"
                + "      </XAxis>\n"
                + "      <ZAxis>\n"
                + "        <I>0</I> <J>0</J> <K>-1</K>\n"
                + "      </ZAxis>\n"
                + "    </Waypoint>\n"
                + "    <NumPositions>3</NumPositions>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"SetEndEffectorType\">\n"
                + "    <CommandID>27</CommandID>\n"
                + "    <Setting>0</Setting>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"MoveThroughToType\">\n"
                + "    <CommandID>28</CommandID>\n"
                + "    <MoveStraight>false</MoveStraight>\n"
                + "    <Waypoint>\n"
                + "      <Point>\n"
                + "        <X>4.14</X> <Y>0.93</Y> <Z>1</Z>\n"
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
                + "        <X>7.61</X> <Y>1.02</Y> <Z>0.5</Z>\n"
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
                + "        <X>7.61</X> <Y>1.02</Y> <Z>0.1501</Z>\n"
                + "      </Point>\n"
                + "      <XAxis>\n"
                + "        <I>1</I> <J>0</J> <K>0</K>\n"
                + "      </XAxis>\n"
                + "      <ZAxis>\n"
                + "        <I>0</I> <J>0</J> <K>-1</K>\n"
                + "      </ZAxis>\n"
                + "    </Waypoint>\n"
                + "    <NumPositions>3</NumPositions>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"SetEndEffectorType\">\n"
                + "    <CommandID>29</CommandID>\n"
                + "    <Setting>1</Setting>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"MoveThroughToType\">\n"
                + "    <CommandID>30</CommandID>\n"
                + "    <MoveStraight>false</MoveStraight>\n"
                + "    <Waypoint>\n"
                + "      <Point>\n"
                + "        <X>7.61</X> <Y>1.02</Y> <Z>0.5</Z>\n"
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
                + "        <X>4.14</X> <Y>1.07</Y> <Z>1</Z>\n"
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
                + "        <X>4.14</X> <Y>1.07</Y> <Z>0.6501</Z>\n"
                + "      </Point>\n"
                + "      <XAxis>\n"
                + "        <I>1</I> <J>0</J> <K>0</K>\n"
                + "      </XAxis>\n"
                + "      <ZAxis>\n"
                + "        <I>0</I> <J>0</J> <K>-1</K>\n"
                + "      </ZAxis>\n"
                + "    </Waypoint>\n"
                + "    <NumPositions>3</NumPositions>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"SetEndEffectorType\">\n"
                + "    <CommandID>31</CommandID>\n"
                + "    <Setting>0</Setting>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"MoveThroughToType\">\n"
                + "    <CommandID>32</CommandID>\n"
                + "    <MoveStraight>false</MoveStraight>\n"
                + "    <Waypoint>\n"
                + "      <Point>\n"
                + "        <X>4.14</X> <Y>1.07</Y> <Z>1</Z>\n"
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
                + "        <X>8.75</X> <Y>1</Y> <Z>1</Z>\n"
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
                + "        <X>8.75</X> <Y>1</Y> <Z>0.475</Z>\n"
                + "      </Point>\n"
                + "      <XAxis>\n"
                + "        <I>1</I> <J>0</J> <K>0</K>\n"
                + "      </XAxis>\n"
                + "      <ZAxis>\n"
                + "        <I>0</I> <J>0</J> <K>-1</K>\n"
                + "      </ZAxis>\n"
                + "    </Waypoint>\n"
                + "    <NumPositions>3</NumPositions>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"OpenToolChangerType\">\n"
                + "    <CommandID>33</CommandID>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"MoveThroughToType\">\n"
                + "    <CommandID>34</CommandID>\n"
                + "    <MoveStraight>false</MoveStraight>\n"
                + "    <Waypoint>\n"
                + "      <Point>\n"
                + "        <X>8.75</X> <Y>1</Y> <Z>1</Z>\n"
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
                + "        <X>8.25</X> <Y>1</Y> <Z>1</Z>\n"
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
                + "        <X>8.25</X> <Y>1</Y> <Z>0.5</Z>\n"
                + "      </Point>\n"
                + "      <XAxis>\n"
                + "        <I>1</I> <J>0</J> <K>0</K>\n"
                + "      </XAxis>\n"
                + "      <ZAxis>\n"
                + "        <I>0</I> <J>0</J> <K>-1</K>\n"
                + "      </ZAxis>\n"
                + "    </Waypoint>\n"
                + "    <NumPositions>3</NumPositions>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"CloseToolChangerType\">\n"
                + "    <CommandID>35</CommandID>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"MoveThroughToType\">\n"
                + "    <CommandID>36</CommandID>\n"
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
                + "    <Waypoint>\n"
                + "      <Point>\n"
                + "        <X>4</X> <Y>1</Y> <Z>1</Z>\n"
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
                + "        <X>4</X> <Y>1</Y> <Z>0.5001</Z>\n"
                + "      </Point>\n"
                + "      <XAxis>\n"
                + "        <I>1</I> <J>0</J> <K>0</K>\n"
                + "      </XAxis>\n"
                + "      <ZAxis>\n"
                + "        <I>0</I> <J>0</J> <K>-1</K>\n"
                + "      </ZAxis>\n"
                + "    </Waypoint>\n"
                + "    <NumPositions>3</NumPositions>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"SetEndEffectorType\">\n"
                + "    <CommandID>37</CommandID>\n"
                + "    <Setting>1</Setting>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"MoveThroughToType\">\n"
                + "    <CommandID>38</CommandID>\n"
                + "    <MoveStraight>false</MoveStraight>\n"
                + "    <Waypoint>\n"
                + "      <Point>\n"
                + "        <X>4</X> <Y>1</Y> <Z>1</Z>\n"
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
                + "        <X>2.5</X> <Y>1</Y> <Z>0.0001</Z>\n"
                + "      </Point>\n"
                + "      <XAxis>\n"
                + "        <I>1</I> <J>0</J> <K>0</K>\n"
                + "      </XAxis>\n"
                + "      <ZAxis>\n"
                + "        <I>0</I> <J>0</J> <K>-1</K>\n"
                + "      </ZAxis>\n"
                + "    </Waypoint>\n"
                + "    <NumPositions>3</NumPositions>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"SetEndEffectorType\">\n"
                + "    <CommandID>39</CommandID>\n"
                + "    <Setting>0</Setting>\n"
                + "  </MiddleCommand>\n"
                + "  <MiddleCommand xsi:type=\"MoveThroughToType\">\n"
                + "    <CommandID>40</CommandID>\n"
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
                + "  </MiddleCommand>\n"
                + "  <EndCanon>\n"
                + "    <CommandID>41</CommandID>\n"
                + "  </EndCanon>\n"
                + "</CRCLProgram>";
        boolean validate = false;
        CRCLSocket instance = new CRCLSocket();
        CRCLProgramType result = instance.stringToProgram(str, validate);
        final InitCanonType initCanon = result.getInitCanon();
        assertTrue(initCanon != null);
        if (null != initCanon) {
            assertTrue(initCanon.getCommandID() == 1);
        }
        final List<MiddleCommandType> middleCommandList = CRCLUtils.getNonNullFilteredList(result.getMiddleCommand());
        assertTrue(middleCommandList != null);
        if (null != middleCommandList) {
            assertEquals(middleCommandList.size(), 39);
        }
        final EndCanonType endCanon = result.getEndCanon();
        assertTrue(endCanon != null);
        if (null != endCanon) {
            assertTrue(endCanon.getCommandID() == 41);
        }

    }

    /**
     * Test of statusToString method, of class CRCLSocket.
     */
//    @Test
//    public void testStatusToString() throws Exception {
//        System.out.println("statusToPrettyString");
//        boolean validate = false;
//        CRCLSocket instance = new CRCLSocket();
//        CRCLStatusType status = instance.stringToStatus("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
//                + "<CRCLStatus\n"
//                + "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
//                + "  xsi:noNamespaceSchemaLocation=\"../xmlSchemas/CRCLStatus.xsd\">\n"
//                + "  <CommandStatus>\n"
//                + "    <CommandID>5</CommandID>\n"
//                + "    <StatusID>1</StatusID>\n"
//                + "    <CommandState>Working</CommandState>\n"
//                + "  </CommandStatus>\n"
//                + "  <JointStatuses>\n"
//                + "    <JointStatus>\n"
//                + "      <JointNumber>1</JointNumber>\n"
//                + "      <JointPosition>8.000000</JointPosition>\n"
//                + "    </JointStatus>\n"
//                + "    <JointStatus>\n"
//                + "      <JointNumber>2</JointNumber>\n"
//                + "      <JointPosition>53.000000</JointPosition>\n"
//                + "    </JointStatus>\n"
//                + "    <JointStatus>\n"
//                + "      <JointNumber>3</JointNumber>\n"
//                + "      <JointPosition>-98.000000</JointPosition>\n"
//                + "    </JointStatus>\n"
//                + "    <JointStatus>\n"
//                + "      <JointNumber>4</JointNumber>\n"
//                + "      <JointPosition>82.000000</JointPosition>\n"
//                + "    </JointStatus>\n"
//                + "    <JointStatus>\n"
//                + "      <JointNumber>5</JointNumber>\n"
//                + "      <JointPosition>-8.000000</JointPosition>\n"
//                + "    </JointStatus>\n"
//                + "    <JointStatus>\n"
//                + "      <JointNumber>6</JointNumber>\n"
//                + "      <JointPosition>0.000000</JointPosition>\n"
//                + "    </JointStatus>\n"
//                + "  </JointStatuses>\n"
//                + "  <Pose>\n"
//                + "    <Timestamp>2015-04-02T09:55:44.455-04:00</Timestamp>\n"
//                + "    <Point>\n"
//                + "      <X>102.519869</X>\n"
//                + "      <Y>12.724614</Y>\n"
//                + "      <Z>36.635392</Z>\n"
//                + "    </Point>\n"
//                + "    <XAxis>\n"
//                + "      <I>0.798636</I>\n"
//                + "      <J>0.000000</J>\n"
//                + "      <K>0.601815</K>\n"
//                + "    </XAxis>\n"
//                + "    <ZAxis>\n"
//                + "      <I>-0.601815</I>\n"
//                + "      <J>0.000000</J>\n"
//                + "      <K>0.798636</K>\n"
//                + "    </ZAxis>\n"
//                + "  </Pose>\n"
//                + "</CRCLStatus> ", validate);
//        String expResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
//                + "<CRCLStatus\n"
//                + "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
//                + "  xsi:noNamespaceSchemaLocation=\"../xmlSchemas/CRCLStatus.xsd\"><CommandStatus><CommandID>5</CommandID><StatusID>1</StatusID></CommandStatus><JointStatuses><JointStatus><JointNumber>1</JointNumber><JointPosition>8.000000</JointPosition></JointStatus><JointStatus><JointNumber>2</JointNumber><JointPosition>53.000000</JointPosition></JointStatus><JointStatus><JointNumber>3</JointNumber><JointPosition>-98.000000</JointPosition></JointStatus><JointStatus><JointNumber>4</JointNumber><JointPosition>82.000000</JointPosition></JointStatus><JointStatus><JointNumber>5</JointNumber><JointPosition>-8.000000</JointPosition></JointStatus><JointStatus><JointNumber>6</JointNumber><JointPosition>0.000000</JointPosition></JointStatus></JointStatuses><Pose><Timestamp>2015-04-02T09:55:44.455-04:00</Timestamp><Point><X>102.519869</X><Y>12.724614</Y><Z>36.635392</Z></Point><XAxis><I>0.798636</I><J>0.000000</J><K>0.601815</K></XAxis><ZAxis><I>-0.601815</I><J>0.000000</J><K>0.798636</K></ZAxis></Pose></CRCLStatus>";
//        String result = instance.statusToString(status, validate);
//        System.out.println("result = " + result);
//
//        for (int i = 0; i < Math.min(result.length(), expResult.length()); i++) {
//            if (expResult.charAt(i) != result.charAt(i)) {
//                System.out.println("expResult.substring(" + i + ")=" + expResult.substring(i));
//                System.out.println("result.substring(" + i + ")=" + result.substring(i));
//                break;
//            }
//        }
////        System.out.println("resultPart = " + resultPart);
////        System.out.println("expResultPart = " + expResultPart);
//        assertEquals(expResult, result);
//    }
    private static final Logger LOG = Logger.getLogger(CRCLSocketIT.class.getName());

}
