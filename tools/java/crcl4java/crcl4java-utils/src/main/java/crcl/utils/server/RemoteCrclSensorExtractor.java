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
package crcl.utils.server;

import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLStatusType;
import crcl.base.CountSensorStatusType;
import crcl.base.ForceTorqueSensorStatusType;
import crcl.base.GetStatusType;
import crcl.base.OnOffSensorStatusType;
import crcl.base.ParameterSettingType;
import crcl.base.ScalarSensorStatusType;
import crcl.base.SensorStatusType;
import crcl.base.SensorStatusesType;
import crcl.utils.CRCLException;
import crcl.utils.CRCLSocket;
import static crcl.utils.CRCLUtils.getNonNullIterable;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class RemoteCrclSensorExtractor implements SensorServerInterface {

    final int remotePort;
    final String remoteHost;
    final String remoteSensorId;
    final String outSensorId;
    final String inSensorId;
    private final List<ParameterSettingType> parameterList;
    private final CRCLSocket crclSocket;

    private static String findParam(List<ParameterSettingType> sensorParameterSetting, String name, String defaultValue) {
        for (ParameterSettingType param : sensorParameterSetting) {
            final String parameterName = Objects.requireNonNull(param.getParameterName(), "param.getParameterName()");
            final String parameterValue = param.getParameterValue();
            if (Objects.equals(parameterName, name) && null != parameterValue) {
                return parameterValue;
            }
        }
        return defaultValue;
    }

    public RemoteCrclSensorExtractor(String inSensorId, List<ParameterSettingType> parameterList) throws CRCLException, IOException {

        this.inSensorId = inSensorId;
        this.parameterList = parameterList;
        this.remoteHost = findParam(parameterList, "host", "localhost");
        this.remotePort = Integer.parseInt(findParam(parameterList, "port", "8888"));
        this.remoteSensorId = findParam(parameterList, "remoteSensorId", inSensorId);
        this.outSensorId = findParam(parameterList, "outSensorId", inSensorId);
        crclSocket = new CRCLSocket(remoteHost, remotePort);
        getStatusCommandInstance = new CRCLCommandInstanceType();
        GetStatusType getStatusCmd = new GetStatusType();
        getStatusCommandInstance.setCRCLCommand(getStatusCmd);
        this.executorService = Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r, threadNum.incrementAndGet() + "_RemoteCrclSensorExtractorThread:inSensorId=" + inSensorId);
                t.setDaemon(true);
                return t;
            }
        });
        getting_status = true;
        lastFuture = executorService.submit(() -> {
            currentSensorStatus = getCurrentRemoteSensorStatus(crclSocket,
                    getStatusCommandInstance,
                    remoteSensorId,
                    outSensorId);
            getting_status = false;
        });
    }


    private final CRCLCommandInstanceType getStatusCommandInstance;

    private static final AtomicInteger threadNum = new AtomicInteger();
    private final ExecutorService executorService;
    private volatile @Nullable
    SensorStatusType currentSensorStatus = null;
    private volatile boolean getting_status = false;

    private volatile @MonotonicNonNull
    Future<?> lastFuture = null;

    @Override
    public @Nullable
    SensorStatusType getCurrentSensorStatus() {
        if (executorService.isShutdown() || crclSocket.isClosed()) {
            throw new RuntimeException("Service already shutdown/closed.");
        }
        if (!getting_status) {
            getting_status = true;
            lastFuture = executorService.submit(() -> {
                currentSensorStatus = getCurrentRemoteSensorStatus(
                        crclSocket,
                        getStatusCommandInstance,
                        remoteSensorId,
                        outSensorId);
                getting_status = false;
            });
        }
        return currentSensorStatus;
    }

    static private @Nullable
    SensorStatusType getCurrentRemoteSensorStatus(
            final CRCLSocket crclSocket,
            final CRCLCommandInstanceType getStatusCommandInstance,
            final String remoteSensorId,
            final String outSensorId) {
        try {
            long startReadTime = System.currentTimeMillis();
            crclSocket.writeCommand(getStatusCommandInstance);
            CRCLStatusType status = crclSocket.readStatus();
            if (status == null) {
                return null;
            }
            SensorStatusesType sensors = status.getSensorStatuses();
            SensorStatusType firstSensorStat = null;
            if (null != sensors) {
                final Iterable<CountSensorStatusType> countSensorsIterable
                        = getNonNullIterable(sensors.getCountSensorStatus());
                for (SensorStatusType sensorStat : countSensorsIterable) {
                    if (null == firstSensorStat && null != sensorStat) {
                        firstSensorStat = sensorStat;
                    }
                    if (Objects.equals(sensorStat.getSensorID(), remoteSensorId)) {
                        sensorStat.setSensorID(outSensorId);
                        if (sensorStat.getLastReadTime() <= 0 || sensorStat.getLastReadTime() > startReadTime) {
                            sensorStat.setLastReadTime(startReadTime);
                        }
                        return sensorStat;
                    }
                }
                final Iterable<OnOffSensorStatusType> onOffSensorsIterable
                        = getNonNullIterable(sensors.getOnOffSensorStatus());
                for (SensorStatusType sensorStat : onOffSensorsIterable) {
                    if (null == firstSensorStat && null != sensorStat) {
                        firstSensorStat = sensorStat;
                    }
                    if (Objects.equals(sensorStat.getSensorID(), remoteSensorId)) {
                        sensorStat.setSensorID(outSensorId);
                        if (sensorStat.getLastReadTime() <= 0 || sensorStat.getLastReadTime() > startReadTime) {
                            sensorStat.setLastReadTime(startReadTime);
                        }
                        return sensorStat;
                    }
                }
                final Iterable<ScalarSensorStatusType> scalerSensorsIterable
                        = getNonNullIterable(sensors.getScalarSensorStatus());
                for (SensorStatusType sensorStat : scalerSensorsIterable) {
                    if (null == firstSensorStat && null != sensorStat) {
                        firstSensorStat = sensorStat;
                    }
                    if (Objects.equals(sensorStat.getSensorID(), remoteSensorId)) {
                        sensorStat.setSensorID(outSensorId);
                        if (sensorStat.getLastReadTime() <= 0 || sensorStat.getLastReadTime() > startReadTime) {
                            sensorStat.setLastReadTime(startReadTime);
                        }
                        return sensorStat;
                    }
                }
                final Iterable<ForceTorqueSensorStatusType> forceSensorsIterable
                        = getNonNullIterable(sensors.getForceTorqueSensorStatus());
                for (SensorStatusType sensorStat : forceSensorsIterable) {
                    if (null == firstSensorStat && null != sensorStat) {
                        firstSensorStat = sensorStat;
                    }
                    if (Objects.equals(sensorStat.getSensorID(), remoteSensorId)) {
                        sensorStat.setSensorID(outSensorId);
                        if (sensorStat.getLastReadTime() <= 0 || sensorStat.getLastReadTime() > startReadTime) {
                            sensorStat.setLastReadTime(startReadTime);
                        }
                        return sensorStat;
                    }
                }
                if (null != firstSensorStat) {
                    if (firstSensorStat.getLastReadTime() <= 0 || firstSensorStat.getLastReadTime() > startReadTime) {
                        firstSensorStat.setLastReadTime(startReadTime);
                    }
                }
                return firstSensorStat;
            }
            return null;
        } catch (Exception ex) {
            Logger.getLogger(RemoteCrclSensorExtractor.class.getName()).log(Level.SEVERE, null, ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void close() throws Exception {
        crclSocket.close();
        executorService.shutdown();
    }

}
