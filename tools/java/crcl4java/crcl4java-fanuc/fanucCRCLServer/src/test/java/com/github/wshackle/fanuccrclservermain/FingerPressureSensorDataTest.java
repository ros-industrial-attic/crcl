/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wshackle.fanuccrclservermain;


import crcl.ui.misc.FingerPressureSensorData;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class FingerPressureSensorDataTest {
    
    public FingerPressureSensorDataTest() {
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
     * Test of getFSR_finger_A_distal method, of class FingerPressureSensorData.
     */
    @Test
    public void testGetFSR_finger_A_distal() {
        System.out.println("getFSR_finger_A_distal");
        FingerPressureSensorData instance = new FingerPressureSensorData();
        int expResult = 0;
        int result = instance.getFSR_finger_A_distal();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getFSR_finger_B_distal method, of class FingerPressureSensorData.
     */
    @Test
    public void testGetFSR_finger_B_distal() {
        System.out.println("getFSR_finger_B_distal");
        FingerPressureSensorData instance = new FingerPressureSensorData();
        int expResult = 0;
        int result = instance.getFSR_finger_B_distal();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    private final String EXAMPLE="{\"sensor_values\": [   {\"FSR_finger_A_distal\": 23}, {\"FSR_finger_B_distal\": 0} ] }";
    private final String EXAMPLE2="{\"sensor_values\": [   {\"FSR_finger_B_distal\": 23}, {\"FSR_finger_A_distal\": 0} ] }";
    private final String EXAMPLE3="{\"sensor_values\": [   {\"FSR_finger_A_distal\": 0}, {\"FSR_finger_A_distal\": 23} ] }";
    private final String EXAMPLE4="{\"sensor_values\": [   {\"FSR_finger_A_distal\": 0}, {\"FSR_finger_B_distal\": 196} ] }";
    
//    /**
//     * Test of fromJSON method, of class FingerPressureSensorData.
//     */
//    @Test
//    public void testFromJSON() {
//        System.out.println("fromJSON");
//        String json = EXAMPLE;
//        FingerPressureSensorData result = FingerPressureSensorData.fromJSON(json);
//        assertEquals(23, result.getFSR_finger_A_distal());
//        assertEquals(0, result.getFSR_finger_B_distal());
//        json = EXAMPLE2;
//        result = FingerPressureSensorData.fromJSON(json);
//        assertEquals(0, result.getFSR_finger_A_distal());
//        assertEquals(23, result.getFSR_finger_B_distal());
//        json = EXAMPLE3;
//        result = FingerPressureSensorData.fromJSON(json);
//        assertEquals(0, result.getFSR_finger_A_distal());
//        assertEquals(0, result.getFSR_finger_B_distal());
//        json = EXAMPLE4;
//        result = FingerPressureSensorData.fromJSON(json);
//        assertEquals(0, result.getFSR_finger_A_distal());
//        assertEquals(196, result.getFSR_finger_B_distal());
//    }
    
}
