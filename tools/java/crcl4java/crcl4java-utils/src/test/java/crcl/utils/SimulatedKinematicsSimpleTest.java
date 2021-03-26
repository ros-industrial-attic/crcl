/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * 
 */
package crcl.utils;

import crcl.utils.kinematics.SimulatedKinematicsSimple;
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
public class SimulatedKinematicsSimpleTest {

    public SimulatedKinematicsSimpleTest() {
    }

    static SimulatedKinematicsSimple sk = new SimulatedKinematicsSimple();

    private static final int RANDOM_SEED = 7321;

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
     * Test of jointsToPose method, of class SimulatedKinematicsSimple.
     */
    @Test
    public void testJointsToPose_doubleArr_doubleArr() throws PmException {
        double jtest[] = Arrays.copyOf(SimulatedKinematicsSimple.DEFAULT_JOINTVALS,
                SimulatedKinematicsSimple.DEFAULT_JOINTVALS.length);
        PoseType pose = sk.jointsToPose(jtest);
        double j2[] = sk.poseToJoints(null, pose);
        //System.err.println("j2 = " + Arrays.toString(j2));
        //System.err.println("jtest = " + Arrays.toString(jtest));
        for (int ii = 0; ii < jtest.length; ii++) {
            if (Math.abs(jtest[ii] - j2[ii]) > 1.0) {
                try {
                    System.err.println("pose=" + CRCLPosemath.poseToString(pose));
                } catch (Exception ex) {
                    Logger.getLogger(SimulatedKinematicsSimpleTest.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.err.println("j2=" + Arrays.toString(j2));
                System.err.println("jtest=" + Arrays.toString(jtest));
                break;
            }
        }
        assertArrayEquals(jtest, j2, 0.05);

        Random r = new Random(RANDOM_SEED);
        for (int i = 0; i < 500; i++) {
            try {
                int index = r.nextInt(jtest.length - 2);
                jtest[index] += r.nextDouble() * 10.0 - 5.0;
                if (Math.abs(90 - jtest[1] - jtest[2] - jtest[3]) < 5.0) {
                    jtest = Arrays.copyOf(SimulatedKinematicsSimple.DEFAULT_JOINTVALS,
                            SimulatedKinematicsSimple.DEFAULT_JOINTVALS.length);
                    continue;
                }
                //System.err.println("jtest[index] = " + jtest[index]);
                pose = sk.jointsToPose(jtest);
                j2 = sk.poseToJoints(j2, pose);
                //System.err.println("j2 = " + Arrays.toString(j2));
                //System.err.println("jtest = " + Arrays.toString(jtest));
                for (int ii = 0; ii < jtest.length; ii++) {
                    if (Math.abs(jtest[ii] - j2[ii]) > 1.0) {
                        try {
                            System.err.println("pose=" + CRCLPosemath.poseToString(pose));
                        } catch (Exception ex) {
                            Logger.getLogger(SimulatedKinematicsSimpleTest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.err.println("j2=" + Arrays.toString(j2));
                        System.err.println("jtest=" + Arrays.toString(jtest));
                        break;
                    }
                }
                assertArrayEquals(jtest, j2, 1.0);
            } catch (IllegalArgumentException iae) {
                // ignore
            }
        }
    }

    /**
     * Test of printRot9x9 method, of class SimulatedKinematicsSimple.
     */
    @Test
    public void testPrintRot9x9() {
    }

    /**
     * Test of rot9x9mult method, of class SimulatedKinematicsSimple.
     */
    @Test
    public void testRot9x9mult() {
    }

    /**
     * Test of poseToJoints method, of class SimulatedKinematicsSimple.
     */
    @Test
    public void testPoseToJoints() throws PmException {
        double jtest[] = Arrays.copyOf(SimulatedKinematicsSimple.DEFAULT_JOINTVALS,
                SimulatedKinematicsSimple.DEFAULT_JOINTVALS.length);
        PoseType pose = sk.jointsToPose(jtest);

        Random r = new Random(RANDOM_SEED);
        for (int i = 0; i < 100; i++) {

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
                    rpy.r += r.nextDouble()*Math.toRadians(20.0) - Math.toRadians(10.0);
                    pose = CRCLPosemath.toPoseType(CRCLPosemath.toPmCartesian(pt), rpy);
                }
                break;
                
                case 4: {
                    PmRpy rpy = CRCLPosemath.toPmRpy(pose);
                    rpy.p += r.nextDouble()*Math.toRadians(20.0) - Math.toRadians(10.0);
                    pose = CRCLPosemath.toPoseType(CRCLPosemath.toPmCartesian(pt), rpy);
                }
                break;
                
                case 5: {
                    PmRpy rpy = CRCLPosemath.toPmRpy(pose);
                    rpy.y += r.nextDouble()*Math.toRadians(20.0) - Math.toRadians(10.0);
                    pose = CRCLPosemath.toPoseType(CRCLPosemath.toPmCartesian(pt), rpy);
                }
                break;
                
                default:
                    assert false; // This should never happen.
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
            for (int ii = 0; ii < jtest.length; ii++) {
                if (Math.abs(jtest[ii] - j2[ii]) > 1.0) {
                    try {
                        System.err.println("pose=" + CRCLPosemath.poseToString(pose));
                        System.err.println("pose2=" + CRCLPosemath.poseToString(pose2));
                    } catch (Exception ex) {
                        Logger.getLogger(SimulatedKinematicsSimpleTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.err.println("j2=" + Arrays.toString(j2));
                    System.err.println("jtest=" + Arrays.toString(jtest));
                    break;
                }
            }
            assertArrayEquals(j2, jtest, 1.0);
            assertEquals(pt1.getX(), pt2.getX(), 5.0);
            assertEquals(pt1.getY(), pt2.getY(), 5.0);
            assertEquals(pt1.getZ(), pt2.getZ(), 5.0);
            double diffRot = CRCLPosemath.diffPosesRot(pose, pose2);
            assertTrue(diffRot >= 0);
            assertTrue(Math.abs(diffRot) < Math.toRadians(0.1));
        }
    }
    private static final Logger LOG = Logger.getLogger(SimulatedKinematicsSimpleTest.class.getName());

}
