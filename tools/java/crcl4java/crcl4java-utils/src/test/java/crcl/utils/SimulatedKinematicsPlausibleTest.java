/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * 
 */
package crcl.utils;

import crcl.utils.kinematics.SimulatedKinematicsPlausible;
import crcl.base.PointType;
import crcl.base.PoseType;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rcs.posemath.PmException;
import rcs.posemath.PmRpy;

@SuppressWarnings("nullness")
public class SimulatedKinematicsPlausibleTest {

    public SimulatedKinematicsPlausibleTest() {
    }

    static SimulatedKinematicsPlausible sk = new SimulatedKinematicsPlausible();

    private static final int RANDOM_SEED = 8321;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of printRot9x9 method, of class SimulatedKinematicsPlausible.
     */
    @Test
    public void testPrintRot9x9() {
    }

    /**
     * Test of rot9x9mult method, of class SimulatedKinematicsPlausible.
     */
    @Test
    public void testRot9x9mult() {
    }

    /**
     * Test of poseToJoints method, of class SimulatedKinematicsPlausible.
     */
    @Test
    public void testPoseToJoints() throws PmException {
        double jtest[] = Arrays.copyOf(SimulatedKinematicsPlausible.DEFAULT_JOINTVALS,
                SimulatedKinematicsPlausible.DEFAULT_JOINTVALS.length);
        jtest[5] = 10;
        PoseType pose = sk.jointsToPose(jtest);
        PmRpy rpyOut1 = CRCLPosemath.toPmRpy(pose);
        assertEquals(-Math.toDegrees(rpyOut1.r), jtest[5],0.1);
        double jout1[] = sk.poseToJoints(null, pose);
        assertArrayEquals(jout1, jtest, 0.1);
        pose.getPoint().setZ(pose.getPoint().getZ() + 1.0);
        double jout2[] = sk.poseToJoints(null, pose);
        assertEquals(jout2[5], jtest[5], 0.1);
        PoseType poseOut1 = sk.jointsToPose(jout2);
        assertEquals(poseOut1.getPoint().getX(), pose.getPoint().getX(), 0.1);
        assertEquals(poseOut1.getPoint().getY(), pose.getPoint().getY(), 0.1);
        assertEquals(poseOut1.getPoint().getZ(), pose.getPoint().getZ(), 0.1);
                
        Random r = new Random(RANDOM_SEED);
        for (int i = 0; i < 100; i++) {
            try {
                //System.err.println("");
                //System.err.println("i = " + i);
                int index = r.nextInt(6);
                //System.err.println("index = " + index);
                PointType pt = pose.getPoint();
                switch (index) {
                    case 0:
                        //System.err.println("pt.getX().doubleValue() = " + pt.getX().doubleValue());
                        pt.setX(pt.getX() + r.nextDouble() * 20.0 - 10.0);
                        //System.err.println("pt.getX().doubleValue() = " + pt.getX().doubleValue());
                        pose.setPoint(pt);
                        break;

                    case 1:
                        //System.err.println("pt.getY().doubleValue() = " + pt.getY().doubleValue());
                        pt.setY(pt.getY()+ r.nextDouble() * 20.0 - 10.0);
                        //System.err.println("pt.getY().doubleValue() = " + pt.getY().doubleValue());
                        pose.setPoint(pt);
                        break;

                    case 2:
                        //System.err.println("pt.getZ().doubleValue() = " + pt.getZ().doubleValue());
                        pt.setZ(pt.getZ() + r.nextDouble() * 20.0 - 10.0);
                        //System.err.println("pt.getZ().doubleValue() = " + pt.getZ().doubleValue());
                        pose.setPoint(pt);
                        break;

                    case 3: {
                        PmRpy rpy = CRCLPosemath.toPmRpy(pose);
                        rpy.r += r.nextDouble() * Math.toRadians(20.0) - Math.toRadians(10.0);
                        pose = CRCLPosemath.toPoseType(CRCLPosemath.toPmCartesian(pt), rpy);
                    }
                    break;

                    case 4: {
                        PmRpy rpy = CRCLPosemath.toPmRpy(pose);
                        rpy.p += r.nextDouble() * Math.toRadians(20.0) - Math.toRadians(10.0);
                        pose = CRCLPosemath.toPoseType(CRCLPosemath.toPmCartesian(pt), rpy);
                    }
                    break;

                    case 5: {
                        PmRpy rpy = CRCLPosemath.toPmRpy(pose);
                        rpy.y += r.nextDouble() * Math.toRadians(20.0) - Math.toRadians(10.0);
                        pose = CRCLPosemath.toPoseType(CRCLPosemath.toPmCartesian(pt), rpy);
                    }
                    break;
                    
                    default:
                        assert false;
                }

                jtest = sk.poseToJoints(jtest, pose);
                //System.err.println("jtest = " + Arrays.toString(jtest));
                PoseType pose2 = sk.jointsToPose(jtest);
                //System.err.println("jtest = " + Arrays.toString(jtest));
                double j2[] = sk.poseToJoints(null, pose2);
                //System.err.println("jtest = " + Arrays.toString(jtest));
                //System.err.println("j2 = " + Arrays.toString(j2));

                PointType pt1 = pose.getPoint();
                //System.err.printf("pt1 = (%+.3g,%+.3g,%+.3g)\n",
//                        pt1.getX().doubleValue(),
//                        pt1.getY().doubleValue(),
//                        pt1.getZ().doubleValue()
//                );
                PointType pt2 = pose2.getPoint();
                //System.err.printf("pt2 = (%+.3g,%+.3g,%+.3g)\n",
//                        pt2.getX().doubleValue(),
//                        pt2.getY().doubleValue(),
//                        pt2.getZ().doubleValue()
//                );
                assertEquals(pt1.getX(), pt2.getX(), 5.0);
                assertEquals(pt1.getY(), pt2.getY(), 5.0);
                assertEquals(pt1.getZ(), pt2.getZ(), 5.0);
                double diffRot = CRCLPosemath.diffPosesRot(pose, pose2);
                assertTrue(diffRot >= 0);
                assertTrue(Math.abs(diffRot) < Math.toRadians(0.1));
                assertArrayEquals(j2, jtest, 1.0);
            } catch (IllegalArgumentException iae) {
                // If we are at an impossible pose just reset and ignore.
                jtest = Arrays.copyOf(SimulatedKinematicsPlausible.DEFAULT_JOINTVALS,
                        SimulatedKinematicsPlausible.DEFAULT_JOINTVALS.length);
                pose = sk.jointsToPose(jtest);
            }
        }
    }

    /**
     * Test of jointsToPose method, of class SimulatedKinematicsPlausible.
     */
    @Test
    public void testJointsToPose_3args() {
    }
    private static final Logger LOG = Logger.getLogger(SimulatedKinematicsPlausibleTest.class.getName());

}
