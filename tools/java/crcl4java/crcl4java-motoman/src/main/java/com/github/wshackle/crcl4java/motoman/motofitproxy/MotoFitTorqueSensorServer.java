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
package com.github.wshackle.crcl4java.motoman.motofitproxy;

import com.github.wshackle.crcl4java.motoman.MotoPlusConnection;
import com.github.wshackle.crcl4java.motoman.force.FCS_COORD_TYPE;
import com.github.wshackle.crcl4java.motoman.force.MP_FCS_ROB_ID;
import com.github.wshackle.crcl4java.motoman.force.MpFcsGetForceDataReturn;
import crcl.base.ForceTorqueSensorStatusType;
import crcl.base.ParameterSettingType;
import crcl.utils.server.SensorServerInterface;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class MotoFitTorqueSensorServer implements SensorServerInterface {

    private final String sensorId;
    private final List<ParameterSettingType> sensorParameterSetting;
    private final ThreadLocal<MotoPlusConnection> mpc = new ThreadLocal<>();
    private final ConcurrentLinkedDeque<MotoPlusConnection> allMpcs = new ConcurrentLinkedDeque<>();
    
    private final String host;
    private final int port;
    
    public MotoFitTorqueSensorServer(String sensorId,
            List<ParameterSettingType> sensorParameterSetting,
            @Nullable String host, int port) throws IOException, MotoPlusConnection.MotoPlusConnectionException {
        this.sensorId = sensorId;
        this.sensorParameterSetting = sensorParameterSetting;
        this.host = host;
        this.port = port;
        MotoPlusConnection mpcInit = MotoPlusConnection.connectionFromSocket(new Socket(host, port));
        mpcInit.mpFcsStartMeasuring(MP_FCS_ROB_ID.MP_FCS_R1ID, 100);
        mpc.set(mpcInit);
        allMpcs.add(mpcInit);
    }

    private final AtomicInteger readCountAtomicInt = new AtomicInteger();
    
    @Override
    public synchronized ForceTorqueSensorStatusType getCurrentSensorStatus() {
        try {
            long start = System.currentTimeMillis();
            ForceTorqueSensorStatusType sensorStatus = new ForceTorqueSensorStatusType();
            MotoPlusConnection mpcCurrent = mpc.get();
            if(null == mpcCurrent || !mpcCurrent.isConnected()) {
                mpcCurrent = MotoPlusConnection.connectionFromSocket(new Socket(host, port));
                mpc.set(mpcCurrent);
                allMpcs.add(mpcCurrent);
            }
            MpFcsGetForceDataReturn forceData = 
                    mpcCurrent.mpFcsGetForceData(MP_FCS_ROB_ID.MP_FCS_R1ID, FCS_COORD_TYPE.FCS_ROBO_TYPE, 0);
            sensorStatus.setSensorID(sensorId);
            sensorStatus.setReadCount(readCountAtomicInt.incrementAndGet());
            sensorStatus.setFx(forceData.fx);
            sensorStatus.setFy(forceData.fy);
            sensorStatus.setFz(forceData.fz);
            sensorStatus.setTx(forceData.mx);
            sensorStatus.setTy(forceData.my);
            sensorStatus.setTz(forceData.mz);
            long end = System.currentTimeMillis();
            long timediff = (end-start);
            System.out.println("timediff = " + timediff);
            return sensorStatus;
        } catch (Exception ex) {
            Logger.getLogger(MotoFitTorqueSensorServer.class.getName()).log(Level.SEVERE, "", ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void close() throws Exception {
        MotoPlusConnection mpcI;
        while(null != (mpcI = allMpcs.poll())) {
            mpcI.close();
        }
        mpc.remove();
    }

}
