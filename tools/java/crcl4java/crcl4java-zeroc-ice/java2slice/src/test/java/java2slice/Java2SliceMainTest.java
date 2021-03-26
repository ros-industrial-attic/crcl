/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2slice;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class Java2SliceMainTest {

    public Java2SliceMainTest() {
    }

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
     * Test of main method, of class Java2SliceMain.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = new String[]{
            "--verbose",
            //            "--jar", "../ice-testinlib/target/ice-testinlib-1.0-SNAPSHOT.jar",
            //            "--jar", "../crcl4java-base/target/crcl4java-base-1.5-SNAPSHOT.jar",
            "--out-slice", "../ice-testoutlib/src/main/resources/crcl4java.ice",
            "--out-converters-dir", "../ice-testoutlib/src/main/java",
            "../ice-testinlib/target/ice-testinlib-1.0-SNAPSHOT.jar",
            "../../crcl4java-base/target/crcl4java-base-1.5-SNAPSHOT.jar"
        };
//            "src/test/resources/converters/toice/TestClassToIceConverter.java",
//            "converters.toice",
//            "src/test/resources/converters/fromice/TestClassFromIceConverter.java",
//            "converters.fromice"};
        Java2SliceMain.main(args);
    }

}
