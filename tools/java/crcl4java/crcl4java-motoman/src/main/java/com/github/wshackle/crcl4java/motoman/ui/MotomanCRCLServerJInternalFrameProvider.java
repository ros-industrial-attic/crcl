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
package com.github.wshackle.crcl4java.motoman.ui;

import crcl.utils.server.ServerJInternalFrameProviderInterface;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *@author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class MotomanCRCLServerJInternalFrameProvider implements ServerJInternalFrameProviderInterface {

    private volatile MotomanCRCLServerJInternalFrame motomanCRCLServerJInternalFrame;

    @Override
    public void start(Object... args) {
        try {
            MotomanCRCLServerJInternalFrame newMotomanCRCLServerJInternalFrame =
                    getJInternalFrame();
            newMotomanCRCLServerJInternalFrame.connectCrclMotoplus();
            motomanCRCLServerJInternalFrame = newMotomanCRCLServerJInternalFrame;
            //            return runOnDispatchThread(() -> newFanuCRCLServerJInternalFrame(newMotomanCrclMain))
            //                    .thenComposeToVoid(() -> newMotomanCrclMain.startDisplayInterface())
            //                    .thenComposeToVoid(() -> newMotomanCrclMain.start(motomanPreferRNN, motomanNeighborhoodName, motomanRobotHost, motomanCrclPort))
            //                    .thenRun(() -> {
            //                        this.motoman = newMotomanCrclMain;
            //                    });
        } catch (Exception ex) {
            Logger.getLogger(MotomanCRCLServerJInternalFrameProvider.class.getName()).log(Level.SEVERE, "", ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public synchronized  MotomanCRCLServerJInternalFrame getJInternalFrame() {
        if (null == motomanCRCLServerJInternalFrame) {
            MotomanCRCLServerJInternalFrame newMotomanCRCLServerJInternalFrame = new MotomanCRCLServerJInternalFrame();
            this.motomanCRCLServerJInternalFrame = newMotomanCRCLServerJInternalFrame;
            return newMotomanCRCLServerJInternalFrame;
        }
        return motomanCRCLServerJInternalFrame;
    }

    private String name = "MotomanCRCLServer";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public File getPropertiesFile() {
        return motomanCRCLServerJInternalFrame.getPropertiesFile();
    }

    @Override
    public void setPropertiesFile(File propertiesFile) {
        motomanCRCLServerJInternalFrame.setPropertiesFile(propertiesFile);
    }

    @Override
    public void saveProperties() {
        try {
            motomanCRCLServerJInternalFrame.saveProperties();
        } catch (Exception ex) {
            Logger.getLogger(MotomanCRCLServerJInternalFrameProvider.class.getName()).log(Level.SEVERE, "", ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void loadProperties() {
        try {
            motomanCRCLServerJInternalFrame.loadProperties();
        } catch (Exception ex) {
            Logger.getLogger(MotomanCRCLServerJInternalFrameProvider.class.getName()).log(Level.SEVERE, "", ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void setCrclPort(int crclPort) {
        motomanCRCLServerJInternalFrame.setCrclPort(crclPort);
    }

    @Override
    public int getCrclPort() {
        return motomanCRCLServerJInternalFrame.getCrclPort();
    }

    @Override
    public String getRemoteRobotHost() {
        return motomanCRCLServerJInternalFrame.getMotomanHost();
    }

    @Override
    public void setRemotRobotHost(String remoteRobotHost) {
        motomanCRCLServerJInternalFrame.setMotomanHost(remoteRobotHost);
    }

    @Override
    public String toString() {
        return "MotomanCRCLServerJInternalFrameProvider{" + "motomanCRCLServerJInternalFrame=" + motomanCRCLServerJInternalFrame + ", name=" + name + '}';
    }
}
