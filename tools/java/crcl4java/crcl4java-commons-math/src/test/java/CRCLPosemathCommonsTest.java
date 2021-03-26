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

import com.github.wshackle.crcl4java.commons.math.CRCLPosemathCommons;
import crcl.base.PointType;
import crcl.base.PoseType;
import crcl.base.VectorType;
import java.math.BigDecimal;
import java.util.logging.Logger;
import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.RotationConvention;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

@SuppressWarnings("nullness")
public class CRCLPosemathCommonsTest {

    public CRCLPosemathCommonsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    private PointType pt123 = null;
    private PointType pt321 = null;
    private VectorType xvec = null;
    private VectorType yvec = null;
    private VectorType zvec = null;
    private PoseType pose123 = null;
    private PoseType pose321 = null;
    private PoseType pose321rot90 = null;

    static final private double ASSERT_TOLERANCE_DELTA = 1e-6;

    private void checkEquals(String msg, double v1, double v2) {
        assertEquals(msg, v1, v2, ASSERT_TOLERANCE_DELTA);
    }

    private void checkEquals(String msg, BigDecimal v1, BigDecimal v2) {
        assertTrue(msg + " both are null or neither is null", (v1 == null) == (v2 == null));
        if (v1 == null) {
            return;
        }
        checkEquals(msg, v1.doubleValue(), v2.doubleValue());
    }

    private void checkEquals(String msg, PointType pt1, PointType pt2) {
        checkEquals(msg + ".getX()", pt1.getX(), pt2.getX());
        checkEquals(msg + ".getY()", pt1.getY(), pt2.getY());
        checkEquals(msg + ".getZ()", pt1.getZ(), pt2.getZ());
    }

    private void checkEquals(String msg, VectorType v1, VectorType v2) {
        checkEquals(msg + ".getI()", v1.getI(), v2.getI());
        checkEquals(msg + ".getJ()", v1.getJ(), v2.getJ());
        checkEquals(msg + ".getK()", v1.getK(), v2.getK());
    }

    private void checkEquals(String msg, PoseType pose1, PoseType pt2) {
        checkEquals(msg + ".getPoint()", pose1.getPoint(), pt2.getPoint());
        checkEquals(msg + ".getXAxis()", pose1.getXAxis(), pt2.getXAxis());
        checkEquals(msg + ".getZAxis()", pose1.getZAxis(), pt2.getZAxis());
    }

    private void checkEquals(String msg, Rotation rot1, Rotation rot2) {
        double m1[][] = rot1.getMatrix();
        double m2[][] = rot2.getMatrix();
        assertArrayEquals(msg + "[0]", m1[0], m2[0], ASSERT_TOLERANCE_DELTA);
        assertArrayEquals(msg + "[1]", m1[1], m2[1], ASSERT_TOLERANCE_DELTA);
        assertArrayEquals(msg + "[2]", m1[2], m2[2], ASSERT_TOLERANCE_DELTA);
    }

    @Before
    public void setUp() {
        pt123 = new PointType();
        pt123.setX(BIG_DECIMAL_1);
        pt123.setY(BIG_DECIMAL_2);
        pt123.setZ(BIG_DECIMAL_3);

        pt321 = new PointType();
        pt321.setX(BIG_DECIMAL_3);
        pt321.setY(BIG_DECIMAL_2);
        pt321.setZ(BIG_DECIMAL_1);

        xvec = new VectorType();
        xvec.setI(1.0);
        xvec.setJ(0.0);
        xvec.setK(0.0);

        yvec = new VectorType();
        yvec.setI(0.0);
        yvec.setJ(1.0);
        yvec.setK(0.0);

        zvec = new VectorType();
        zvec.setI(0.0);
        zvec.setJ(0.0);
        zvec.setK(1.0);

        pose123 = new PoseType();
        pose123.setPoint(pt123);
        pose123.setXAxis(xvec);
        pose123.setZAxis(zvec);

        pose321 = new PoseType();
        pose321.setPoint(pt321);
        pose321.setXAxis(xvec);
        pose321.setZAxis(zvec);

        pose321rot90 = new PoseType();
        pose321rot90.setPoint(pt321);
        pose321rot90.setXAxis(yvec);
        pose321rot90.setZAxis(zvec);
    }

    private static final double BIG_DECIMAL_1 = 1.0;
    private static final double BIG_DECIMAL_2 = 2.0;
    private static final double BIG_DECIMAL_3 = 3.0;
    private static final double BIG_DECIMAL_4 = 4.0;

    @After
    public void tearDown() {
    }

    /**
     * Test of toCommonsVector3D method, of class CRCLPosemathCommons.
     */
    @Test
    public void testToCommonsVector3D_VectorType() {
        System.out.println("toCommonsVector3D");
        VectorType vIn = xvec;
        Vector3D expResult = Vector3D.PLUS_I;
        Vector3D result = CRCLPosemathCommons.toCommonsVector3D(vIn);
        assertEquals(expResult, result);
    }

    /**
     * Test of toCommonsVector3D method, of class CRCLPosemathCommons.
     */
    @Test
    public void testToCommonsVector3D_PointType() {
        System.out.println("toCommonsVector3D");
        PointType ptIn = pt123;
        Vector3D expResult = new Vector3D(1, 2, 3);
        Vector3D result = CRCLPosemathCommons.toCommonsVector3D(ptIn);
        assertEquals(expResult, result);
    }

    /**
     * Test of toCommonsVector3D method, of class CRCLPosemathCommons.
     */
    @Test
    public void testToCommonsVector3D_PoseType() {
        System.out.println("toCommonsVector3D");
        PoseType poseIn = pose321rot90;
        Vector3D expResult = new Vector3D(3, 2, 1);
        Vector3D result = CRCLPosemathCommons.toCommonsVector3D(poseIn);
        assertEquals(expResult, result);
    }

    /**
     * Test of toCRCLUnitVector method, of class CRCLPosemathCommons.
     */
    @Test
    public void testToCRCLUnitVector_Vector3D_VectorType() {
        System.out.println("toCRCLUnitVector");
        Vector3D vIn = Vector3D.PLUS_I;
        VectorType vOut = null;
        VectorType expResult = xvec;
        VectorType result = CRCLPosemathCommons.toCRCLUnitVector(vIn, vOut);
        checkEquals("xvec", result, expResult);
    }

    /**
     * Test of toCRCLUnitVector method, of class CRCLPosemathCommons.
     */
    @Test
    public void testToCRCLUnitVector_Vector3D() {
        System.out.println("toCRCLUnitVector");
        Vector3D vIn = Vector3D.PLUS_I;
        VectorType expResult = xvec;
        VectorType result = CRCLPosemathCommons.toCRCLUnitVector(vIn);
        checkEquals("xvec", result, expResult);
    }

    /**
     * Test of toCommonsRotation method, of class CRCLPosemathCommons.
     */
    @Test
    @SuppressWarnings("deprecation")
    public void testToCommonsRotation() {
        System.out.println("toCommonsRotation");
        PoseType poseIn = pose321rot90;
        // NOTICE the MINUS sign, commons-math uses opposite convention
        // This Rotation constructor is deprecated.
        Rotation expResult =  new Rotation(Vector3D.PLUS_K, -Math.PI / 2, RotationConvention.VECTOR_OPERATOR);
        // wasnew Rotation(Vector3D.PLUS_K, -Math.PI / 2);
        Rotation result = CRCLPosemathCommons.toCommonsRotation(poseIn);
        checkEquals("rot90", expResult, result);
    }

    /**
     * Test of toCRCLPoint method, of class CRCLPosemathCommons.
     */
    @Test
    public void testToCRCLPoint_Vector3D_PointType() {
        System.out.println("toCRCLPoint");
        Vector3D vIn = new Vector3D(1, 2, 3);
        PointType ptOut = new PointType();
        PointType expResult = pt123;
        PointType result = CRCLPosemathCommons.toCRCLPoint(vIn, null);
        checkEquals("pt123", result, expResult);
        result = CRCLPosemathCommons.toCRCLPoint(vIn, ptOut);
        checkEquals("pt123", result, expResult);
        assertTrue(ptOut == result);
    }

    /**
     * Test of toCRCLPoint method, of class CRCLPosemathCommons.
     */
    @Test
    public void testToCRCLPoint_Vector3D() {
        System.out.println("toCRCLPoint");
        Vector3D vIn = new Vector3D(1, 2, 3);
        PointType expResult = pt123;
        PointType result = CRCLPosemathCommons.toCRCLPoint(vIn);
        checkEquals("pt123", result, expResult);
    }

    private static final Logger LOG = Logger.getLogger(CRCLPosemathCommonsTest.class.getName());

}
