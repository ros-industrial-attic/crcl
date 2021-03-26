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
 * This software is experimental. NIST assumes no responsibility whatsoever 
 * for its use by other parties, and makes no guarantees, expressed or 
 * implied, about its quality, reliability, or any other characteristic. 
 * We would appreciate acknowledgement if the software is used. 
 * This software can be redistributed and/or modified freely provided 
 * that any derivative works bear some notice that they are derived from it, 
 * and any modified versions bear some notice that they have been modified.
 * 
 *  See http://www.copyright.gov/title17/92chap1.html#105
 * 
 */
package com.github.wshackle.atinetft_proxy;

import com.atiia.automation.sensors.FTVisualizationCube;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JLabel;

/**
 *
 *@author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class ConfigurationReader {

    private double[] m_daftMaxes = {100, 100, 100, 100, 100, 100};
    /*maximum 
     rated force/torque readings*/
    private double[] m_daftCountsPerUnit = {1, 1, 1, 1, 1, 1};
    /*counts per 
        *unit force or torque for each axis*/

    private String m_strForceUnits;
    /*The units of force.*/
    private String m_strTorqueUnits;
    /*The units of torque.*/
    private String m_strCalSN;
    private String m_strCalIndex;
    private String m_strCfgName;
    private String m_strCfgIndex;
    /**
     * The RDT sample rate of the sensor.
     */
    private int m_iRDTSampleRate = 0;
    /**
     * Visualizes the forces and torques.
     */
    private FTVisualizationCube m_ftvc;
    final private String m_strSensorAddress;
    /*The network address of the sensor.*/
    private double countsPerForce;
    private double countsPerTorque;

    public ConfigurationReader(String m_strSensorAddress) {
        this.m_strSensorAddress = m_strSensorAddress;
        m_ftvc = new FTVisualizationCube();

        String mDoc = readNetFTAPI(0);
        int activeConfig = findActiveCFG(mDoc);
        mDoc = readNetFTAPI(activeConfig);
        m_strCfgIndex = "" + activeConfig;
        String[] parseStep1 = mDoc.split("<cfgcalsel>");
        String[] parseStep2 = parseStep1[1].split("</cfgcalsel>");
        String mCal = readNetFTCalAPI(Integer.parseInt(parseStep2[0]));
        m_strCalIndex = parseStep2[0];
        parseStep1 = mCal.split("<calsn>");
        parseStep2 = parseStep1[1].split("</calsn>");
        m_strCalSN = parseStep2[0];
        mDoc = readNetFTAPI(activeConfig);
        parseStep1 = mDoc.split("<cfgnam>");
        parseStep2 = parseStep1[1].split("</cfgnam>");
        m_strCfgName = parseStep2[0];
        parseStep1 = mDoc.split("<cfgcpf>");
        parseStep2 = parseStep1[1].split("</cfgcpf>");
        setCountsPerForce(Double.parseDouble(parseStep2[0]));
        parseStep1 = mDoc.split("<cfgcpt>");
        parseStep2 = parseStep1[1].split("</cfgcpt>");
        setCountsPerTorque(Double.parseDouble(parseStep2[0]));
        parseStep1 = mDoc.split("<comrdtrate>");
        parseStep2 = parseStep1[1].split("</comrdtrate>");
        m_iRDTSampleRate = (Integer.parseInt(parseStep2[0]));
        parseStep1 = mDoc.split("<scfgfu>");
        parseStep2 = parseStep1[1].split("</scfgfu>");
        m_strForceUnits = parseStep2[0];
        parseStep1 = mDoc.split("<scfgtu>");
        parseStep2 = parseStep1[1].split("</scfgtu>");
        m_strTorqueUnits = parseStep2[0];
        parseStep1 = mDoc.split("<cfgmr>");
        parseStep2 = parseStep1[1].split("</cfgmr>");
        String[] asRatings = parseStep2[0].split(";");
        for (int i = 0; i < asRatings.length; i++) {
            m_daftMaxes[i] = Double.parseDouble(asRatings[i]);
            if (0 == m_daftMaxes[i]) {
                m_daftMaxes[i] = 32768;
                /* Default maximum rating. */
            }
        }
        m_ftvc.setMaxForce(m_daftMaxes[2]);
        /* Use Fz rating as maximum. */
        m_ftvc.setMaxTorque(m_daftMaxes[5]);
        /* use Tz rating as maximum. */

    }

    public double getCountsPerForce() {
        return countsPerForce;
    }

    public void setCountsPerForce(double countsPerForce) {
        this.countsPerForce = countsPerForce;
        for (int i = 0; i < 3; i++) {
            m_daftCountsPerUnit[i] = countsPerForce;
        }
    }

    public double getCountsPerTorque() {
        return countsPerTorque;
    }

    public void setCountsPerTorque(double countsPerTorque) {
        this.countsPerTorque = countsPerTorque;
        for (int i = 3; i < 6; i++) {
            m_daftCountsPerUnit[i] = countsPerForce;
        }
    }

    /**
     * Reads a page from the integrated web server.
     *
     * @param strUrlSuffix The page on the web server to read.
     * @return The text of the web page.
     * @throws MalformedURLException If strUrlSuffix doesn't point to a valid
     * web page address.
     * @throws IOException If there is an error reading the web page text.
     */
    private String readWebPageText(String strUrlSuffix) throws
            MalformedURLException, IOException {
        /*Reads the HTML from the web server.*/
        BufferedReader cBufferedReader;
        /*The url of the configuration page.*/
        String strURL = "http://" + m_strSensorAddress + "/"
                + strUrlSuffix;
        cBufferedReader = new BufferedReader(new InputStreamReader(new URL(strURL).openConnection().getInputStream()));
        /*The text of the page.*/
        String strPageText = "";
        /*The last line read from the web stream.*/
        String strCurLine;
        /*Precondition: cBufferedReader is at the beginning of the page.
         *Postcondition: cBufferedReader is finished, strPageText =
         *the text of the page, strCurLine = last line read from the 
         *page.
         */
        while (null != (strCurLine = cBufferedReader.readLine())) {
            strPageText += strCurLine;
        }
        return strPageText;
    }

    private String readNetFTAPI(int index) {
        try {
            String strXML = readWebPageText("netftapi2.xml?index=" + index);
            return strXML;
        } catch (Exception e) {
            return "";
        }
    }

    private String readNetFTCalAPI(int index) {
        try {
            String strXML = readWebPageText("netftcalapi.xml?index=" + index);
            return strXML;
        } catch (Exception e) {
            return "";
        }
    }

    private int findActiveCFG(String xmlText) {
        String[] strret = xmlText.split("<setcfgsel>");
        String[] strret2 = strret[1].split("</setcfgsel>");
        int activeConfig = Integer.parseInt(strret2[0]);
        return activeConfig;
    }

    /**
     * Reads information about the sensor's configuration from the integrated
     * web server.
     *
     * @return True if configuration was successfully read, false otherwise.
     */
    private boolean readConfigurationInfo() {
        try {
            String mDoc = readNetFTAPI(0);
            int activeConfig = findActiveCFG(mDoc);
            mDoc = readNetFTAPI(activeConfig);
            m_strCfgIndex = "" + activeConfig;
            String[] parseStep1 = mDoc.split("<cfgcalsel>");
            String[] parseStep2 = parseStep1[1].split("</cfgcalsel>");
            String mCal = readNetFTCalAPI(Integer.parseInt(parseStep2[0]));
            m_strCalIndex = parseStep2[0];
            parseStep1 = mCal.split("<calsn>");
            parseStep2 = parseStep1[1].split("</calsn>");
            m_strCalSN = parseStep2[0];
            mDoc = readNetFTAPI(activeConfig);
            parseStep1 = mDoc.split("<cfgnam>");
            parseStep2 = parseStep1[1].split("</cfgnam>");
            m_strCfgName = parseStep2[0];
            parseStep1 = mDoc.split("<cfgcpf>");
            parseStep2 = parseStep1[1].split("</cfgcpf>");
            setCountsPerForce(Double.parseDouble(parseStep2[0]));
            parseStep1 = mDoc.split("<cfgcpt>");
            parseStep2 = parseStep1[1].split("</cfgcpt>");
            setCountsPerTorque(Double.parseDouble(parseStep2[0]));
            parseStep1 = mDoc.split("<comrdtrate>");
            parseStep2 = parseStep1[1].split("</comrdtrate>");
            m_iRDTSampleRate = (Integer.parseInt(parseStep2[0]));
            parseStep1 = mDoc.split("<scfgfu>");
            parseStep2 = parseStep1[1].split("</scfgfu>");
            m_strForceUnits = parseStep2[0];
            parseStep1 = mDoc.split("<scfgtu>");
            parseStep2 = parseStep1[1].split("</scfgtu>");
            m_strTorqueUnits = parseStep2[0];
            parseStep1 = mDoc.split("<cfgmr>");
            parseStep2 = parseStep1[1].split("</cfgmr>");
            String[] asRatings = parseStep2[0].split(";");
            for (int i = 0; i < asRatings.length; i++) {
                m_daftMaxes[i] = Double.parseDouble(asRatings[i]);
                if (0 == m_daftMaxes[i]) {
                    m_daftMaxes[i] = 32768;
                    /* Default maximum rating. */
                }
            }
            m_ftvc.setMaxForce(m_daftMaxes[2]);
            /* Use Fz rating as maximum. */
            m_ftvc.setMaxTorque(m_daftMaxes[5]);
            /* use Tz rating as maximum. */
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConfigurationReader{" + "m_daftMaxes=" + m_daftMaxes + ", m_daftCountsPerUnit=" + m_daftCountsPerUnit + ", m_strForceUnits=" + m_strForceUnits + ", m_strTorqueUnits=" + m_strTorqueUnits + ", m_strCalSN=" + m_strCalSN + ", m_strCalIndex=" + m_strCalIndex + ", m_strCfgName=" + m_strCfgName + ", m_strCfgIndex=" + m_strCfgIndex + ", m_iRDTSampleRate=" + m_iRDTSampleRate + ", m_ftvc=" + m_ftvc + ", m_strSensorAddress=" + m_strSensorAddress + ", countsPerForce=" + countsPerForce + ", countsPerTorque=" + countsPerTorque + '}';
    }

}
