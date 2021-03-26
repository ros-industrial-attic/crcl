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
package crcl.ui.forcetorquesensorsimulator;

import crcl.ui.client.CrclSwingClientJPanel;
import crcl.utils.outer.interfaces.PropertyOwner;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class ForceTorqueSimJInternalFrame extends javax.swing.JInternalFrame implements PropertyOwner {

    /**
     * Creates new form ForceTorqueSimJFrame
     */
    @SuppressWarnings("initialization")
    public ForceTorqueSimJInternalFrame() {
        
        forceTorqueSimJPanel2 = new crcl.ui.forcetorquesensorsimulator.ForceTorqueSimJPanel();
        jMenuBar1 = forceTorqueSimJPanel2.jMenuBar1;
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Force Torque Sensor Simulation");
        setName("forceTorqueSimFrame0"); // NOI18N

        setJMenuBar(jMenuBar1);
        add(forceTorqueSimJPanel2);
        pack();
    }
    
    public int getCRCLSensorOutPort() {
        return forceTorqueSimJPanel2.getCRCLSensorOutPort();
    }
    
    public void setCRCLSensorOutPort(int port) {
        forceTorqueSimJPanel2.setCRCLSensorOutPort(port);
    }
    
    public int getPoseCRCLPort() {
        return forceTorqueSimJPanel2.getPoseCRCLPort();
    }
    
    public void setPoseCRCLPort(int port) {
        forceTorqueSimJPanel2.setPoseCRCLPort(port);
    }
    
    public String getPoseCRCLHost() {
        return forceTorqueSimJPanel2.getPoseCRCLHost();
    }
    
    public void setPoseCRCLHost(String host) {
        forceTorqueSimJPanel2.setPoseCRCLHost(host);
    }
    
    public void startServer() throws IOException, NumberFormatException {
        forceTorqueSimJPanel2.startServer();
    }
    
    public String getObjectsFileName() {
        return forceTorqueSimJPanel2.getObjectsFileName();
    }
    
    public void setObjectsFileName(String name) {
        forceTorqueSimJPanel2.setObjectsFileName(name);
    }

    /**
     * Get the value of crclClientPanel
     *
     * @return the value of crclClientPanel
     */
    public CrclSwingClientJPanel getCrclClientPanel() {
        return forceTorqueSimJPanel2.getCrclClientPanel();
    }

    /**
     * Set the value of crclClientPanel
     *
     * @param crclClientPanel new value of crclClientPanel
     */
    public void setCrclClientPanel(CrclSwingClientJPanel crclClientPanel) {
        System.out.println("crclClientPanel = " + crclClientPanel);
        forceTorqueSimJPanel2.setCrclClientPanel(crclClientPanel);
    }
    private final crcl.ui.forcetorquesensorsimulator.ForceTorqueSimJPanel forceTorqueSimJPanel2;
    private final javax.swing.JMenuBar jMenuBar1;
    
    @Override
    public File getPropertiesFile() {
        return forceTorqueSimJPanel2.getPropertiesFile();
    }
    
    @Override
    public void setPropertiesFile(File propertiesFile) {
        forceTorqueSimJPanel2.setPropertiesFile(propertiesFile);
    }
    
    @Override
    public void loadProperties() {
        forceTorqueSimJPanel2.loadProperties();
    }
    
    @Override
    public void saveProperties() {
        forceTorqueSimJPanel2.saveProperties();
    }
}
