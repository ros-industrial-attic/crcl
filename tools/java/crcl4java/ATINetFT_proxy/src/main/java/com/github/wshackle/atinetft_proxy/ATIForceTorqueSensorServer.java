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

import com.atiia.automation.sensors.NetFTRDTCommand;
import com.atiia.automation.sensors.NetFTRDTPacket;
import crcl.base.ForceTorqueSensorStatusType;
import crcl.base.ParameterSettingType;
import crcl.utils.server.SensorServerInterface;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class ATIForceTorqueSensorServer implements SensorServerInterface {

    private final NetFTSensorProxy netFtSensor;
    private final ConfigurationReader configurationReader;
//    private final DatagramSocket datagramSocket;
    private final String sensorId;
    private final List<ParameterSettingType> sensorParameterSetting;

    private final @Nullable
    String logFileName;

    public ATIForceTorqueSensorServer(String sensorId, List<ParameterSettingType> sensorParameterSetting, NetFTSensorProxy netFtSensor, ConfigurationReader configurationReader) throws IOException {
        this.sensorId = sensorId;
        this.sensorParameterSetting = sensorParameterSetting;
        this.netFtSensor = netFtSensor;
        this.configurationReader = configurationReader;
        this.logFileName = findParam(sensorParameterSetting, "logFileName");
    }

    public ATIForceTorqueSensorServer(String sensorId, List<ParameterSettingType> sensorParameterSetting, String host) throws UnknownHostException, IOException, IOException, IOException {
        this(sensorId, sensorParameterSetting, new NetFTSensorProxy(InetAddress.getByName(host)), new ConfigurationReader(host));
    }

    private static String findParam(List<ParameterSettingType> sensorParameterSetting, String name) {
        for (ParameterSettingType param : sensorParameterSetting) {
            if (param.getParameterName().equals(name)) {
                return param.getParameterValue();
            }
        }
        return null;
    }

    public ATIForceTorqueSensorServer(String sensorId, List<ParameterSettingType> sensorParameterSetting) throws UnknownHostException, IOException {
        this(sensorId, sensorParameterSetting, findParam(sensorParameterSetting, "host"));
    }

    private final int NETFT_RDT_COMMAND_LENGTH = 8;
    private final int NETFT_RDT_DATA_LENGTH = 36;

    private DatagramPacket getDatagramPacketFromNetFTRDTCommand(
            NetFTRDTCommand netFTCommand) {
        final int NUM_RDT_COMMAND_FIELDS = 3;
        /*the number of fields in
                                                   *an RDT command*/
        byte[] dataBuf = new byte[NETFT_RDT_COMMAND_LENGTH];
        /*the data
                                                   *buffer of the datagram*/

        dataBuf[0] = (byte) ((netFTCommand.getHeader() >> 8) & 0xff);
        dataBuf[1] = (byte) (netFTCommand.getHeader() & 0xff);
        dataBuf[2] = (byte) ((netFTCommand.getCommand() >> 8) & 0xff);
        dataBuf[3] = (byte) (netFTCommand.getCommand() & 0xff);
        dataBuf[4] = (byte) ((netFTCommand.getCount() >> 24) & 0xff);
        dataBuf[5] = (byte) ((netFTCommand.getCount() >> 16) & 0xff);
        dataBuf[6] = (byte) ((netFTCommand.getCount() >> 8) & 0xff);
        dataBuf[7] = (byte) (netFTCommand.getCount() & 0xff);

        return new DatagramPacket(dataBuf, NETFT_RDT_COMMAND_LENGTH);
    }

    private final AtomicInteger requestNetFTDataCount = new AtomicInteger();

    private void requestNetFTData(DatagramSocket cNetFTSocket) throws
            IOException {
        NetFTRDTCommand netFTCommand = new NetFTRDTCommand();
        netFTCommand.setCount(1);
        DatagramPacket commandPacket
                = getDatagramPacketFromNetFTRDTCommand(netFTCommand);
        cNetFTSocket.send(commandPacket);
        requestNetFTDataCount.incrementAndGet();
    }

    private NetFTRDTPacket getNetFTRDTPacketFromDatagramPacket(
            DatagramPacket NetFTRDTDataPacket) {

        final int NUM_RDT_FIELDS = 9;
        int[] rdtFields = new int[NUM_RDT_FIELDS];
        /*the 9 fields of an RDT
                                           *data packet*/
        byte[] dataBuf = NetFTRDTDataPacket.getData();
        /*the received data*/

        int i, j;
        /*loop/array indices*/
 /*precondition: dataBuf has the data received from the network
             *postcondition: rdtFields has the fields of an rdt data packet,
             *in the order RDTSequence, FTSequence, Status, Fx, Fy, Fz, Tx, Ty,
             *and Tz. i = -1, j = 4.
         */
        for (i = (NUM_RDT_FIELDS - 1); i >= 0; i--) {
            rdtFields[i] = (int) dataBuf[(i * 4)] & 0xff;
            for (j = ((i * 4) + 1); j < ((i * 4) + 4); j++) {
                rdtFields[i] = (rdtFields[i] << 8) | ((int) dataBuf[j]
                        & 0xff);
            }
        }

        return new NetFTRDTPacket(rdtFields);
    }

    private final AtomicInteger receiveRequestedNetFTDataCount = new AtomicInteger();

    private NetFTRDTPacket receiveRequestedNetFTData(DatagramSocket cNetFTSocket) throws
            IOException {
        DatagramPacket NetFTRDTDataPacket = new DatagramPacket(
                new byte[NETFT_RDT_DATA_LENGTH], NETFT_RDT_DATA_LENGTH);
        cNetFTSocket.receive(NetFTRDTDataPacket);

        receiveRequestedNetFTDataCount.incrementAndGet();
        return getNetFTRDTPacketFromDatagramPacket(NetFTRDTDataPacket);
    }

    private volatile double z = 0;

    private volatile long setZTime = 0;
    private volatile long lastSetZTime = 0;
    private final AtomicInteger setZCount = new AtomicInteger();

    public void setZ(double z) {
        this.z = z;
        lastSetZTime = setZTime;
        setZTime = System.currentTimeMillis();
        setZCount.incrementAndGet();
    }

    private volatile boolean firstLog = true;
    private volatile long firstStart = 0;
    private volatile long lastStart = 0;

    @Override
    public synchronized ForceTorqueSensorStatusType getCurrentSensorStatus() {
        try {
            long start = System.currentTimeMillis();
            @Nullable
            File fzLogFile;
            boolean fzLogFileExisted;
            if (null != logFileName && logFileName.length() > 1) {
                fzLogFile = new File(System.getProperty("user.home"), logFileName);
                if (firstLog) {
                    System.out.println("fzLogFile = " + fzLogFile);
                }
                fzLogFileExisted = fzLogFile.exists();
            } else {
                fzLogFile = null;
                fzLogFileExisted = false;
            }
            double countsPerForce = configurationReader.getCountsPerForce();
            ForceTorqueSensorStatusType sensorStatus = new ForceTorqueSensorStatusType();
            try (DatagramSocket datagramSocket = netFtSensor.initLowSpeedData()) {
//                NetFTRDTPacketProxy[] packets = netFtSensor.readHighSpeedData(datagramSocket, 10);
////            if(receiveRequestedNetFTDataCount.get() >=  requestNetFTDataCount.get()) {
////                requestNetFTData(datagramSocket);
////            }
////            try (DatagramSocket lowSpeedSocket = netFtSensor.initLowSpeedData()) {
////            NetFTRDTPacketProxy packet = new NetFTRDTPacketProxy(receiveRequestedNetFTData(datagramSocket));
//
//                double minFz = Double.POSITIVE_INFINITY;
//                if (Double.isFinite(z)) {
//                    System.out.println("ATIForceTorqueSensorServer z = " + z);
//                    try (PrintWriter pw = new PrintWriter(new FileWriter(fzLogFile, true))) {
//                        if (!fzLogFileExisted) {
//                            pw.println("time,seq,i,fz,z");
//                        }
//                        for (int i = 0; i < packets.length; i++) {
//                            NetFTRDTPacketProxy packetI = packets[i];
//                            long seq = packetI.getRDTSequence();
//                            double fz = packetI.getFz() / countsPerForce;
//                            if(fz < minFz) {
//                                minFz = fz;
//                            }
//                            pw.println(start + "," + i + "," + fz + "," + z);
//                        }
//                    }
//                }

                NetFTRDTPacketProxy packet0 = netFtSensor.readLowSpeedData(datagramSocket);
                sensorStatus.setFx(packet0.getFx() / countsPerForce);
                sensorStatus.setFy(packet0.getFy() / countsPerForce);
                final double fz = packet0.getFz() / countsPerForce;
                sensorStatus.setFz(fz);
                if (null != fzLogFile) {
                    try (PrintWriter pw = new PrintWriter(new FileWriter(fzLogFile, !firstLog))) {
                        if (!fzLogFileExisted || firstLog) {
                            pw.println("timeSinceStart,timeSinceLast,setZCount,timeSinceSetZ,setZTimeDiff,seq,fz,z");
                        }
                        long seq = packet0.getRDTSequence();
                        pw.println((start - firstStart) + "," + (start - lastStart) + "," + setZCount.get() + "," + (start - setZTime) + "," + (setZTime - lastSetZTime) + "," + seq + "," + fz + "," + z);
                        if (firstLog) {
                            firstStart = start;
                        }
                        lastStart = start;
                        firstLog = false;
                    }
                }
                double countsPerTorque = configurationReader.getCountsPerTorque();
                sensorStatus.setTx(packet0.getTx() / countsPerTorque);
                sensorStatus.setTy(packet0.getTy() / countsPerTorque);
                sensorStatus.setTz(packet0.getTz() / countsPerTorque);
                sensorStatus.setSensorID(sensorId);
                sensorStatus.getSensorParameterSetting().addAll(sensorParameterSetting);
                sensorStatus.setLastReadTime(start);
                sensorStatus.setReadCount((int) packet0.getRDTSequence());
            }
//            requestNetFTData(lowSpeedSocket);
//            }
//            long end = System.currentTimeMillis();
//            long timediff = end-start;
//            System.out.println("ForceTorqueSensorStatusType.getCurrentSensorStatus timediff = " + timediff+", fz="+sensorStatus.getFz());
            return sensorStatus;
        } catch (Exception ex) {
            Logger.getLogger(ATIForceTorqueSensorServer.class.getName()).log(Level.SEVERE, "", ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void close() throws Exception {
//        datagramSocket.close();
        requestNetFTDataCount.set(0);
        receiveRequestedNetFTDataCount.set(0);
    }

}
