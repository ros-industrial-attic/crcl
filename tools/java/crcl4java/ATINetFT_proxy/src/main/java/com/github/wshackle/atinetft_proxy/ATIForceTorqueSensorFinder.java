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

import crcl.base.ParameterSettingType;
import crcl.utils.server.SensorServerFinderInterface;
import crcl.utils.server.SensorServerInterface;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *@author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class ATIForceTorqueSensorFinder implements  SensorServerFinderInterface{

    @Override
    public SensorServerInterface findSensorServer(String sensorId, List<ParameterSettingType> parameterList) {
        if(sensorId.startsWith("ATINetFT")) {
            try {
                return new ATIForceTorqueSensorServer(sensorId, parameterList);
            } catch (Exception ex) {
                Logger.getLogger(ATIForceTorqueSensorFinder.class.getName()).log(Level.SEVERE, "", ex);
                if(ex instanceof RuntimeException) {
                    throw (RuntimeException) ex;
                } else {
                    throw new RuntimeException(ex);
                }
            }
        }
        return null;
    }
    
}
