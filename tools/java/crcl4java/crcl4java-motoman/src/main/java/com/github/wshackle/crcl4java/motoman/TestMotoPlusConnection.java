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
package com.github.wshackle.crcl4java.motoman;

import com.github.wshackle.crcl4java.motoman.exfile.MpExtensionType;
import com.github.wshackle.crcl4java.motoman.kinematics.MP_COORD;
import com.github.wshackle.crcl4java.motoman.kinematics.MP_KINEMA_TYPE;
import com.github.wshackle.crcl4java.motoman.kinematics.MpKinAngleReturn;
import com.github.wshackle.crcl4java.motoman.kinematics.MpKinCartPosReturn;
import com.github.wshackle.crcl4java.motoman.kinematics.MpKinPulseReturn;
import com.github.wshackle.crcl4java.motoman.motctrl.COORD_POS;
import com.github.wshackle.crcl4java.motoman.motctrl.CoordTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.JointTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_COORD_TYPE;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_INTP_TYPE;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_SPEED;
import com.github.wshackle.crcl4java.motoman.motctrl.MotCtrlReturnEnum;
import com.github.wshackle.crcl4java.motoman.sys1.MP_CART_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_IO_INFO;
import com.github.wshackle.crcl4java.motoman.sys1.MP_PULSE_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_VAR_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_VAR_INFO;
import com.github.wshackle.crcl4java.motoman.sys1.VarType;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class TestMotoPlusConnection {

    private static String host = MotoPlusConnection.getDefaultHost();

    // 192.168.1.33");//"10.0.0.2";
    //    private static String host = "localhost";
    public static void main(String[] args) throws Exception {

//        Enumeration<NetworkInterface> netIFEnum = NetworkInterface.getNetworkInterfaces();
//        while (netIFEnum.hasMoreElements()) {
//            NetworkInterface netIfi = netIFEnum.nextElement();
//            System.out.println("netIfi = " + netIfi);
//            List<InterfaceAddress> ifAddrs = netIfi.getInterfaceAddresses();
//            System.out.println("ifAddrs = " + ifAddrs);
//        }
////         System.out.println("netIFEnum = " + netIFEnum);
//        String net = "192.168.1";
//        NetworkInterface netIf = NetworkInterface.getByName("eth5");
//        System.out.println("netIf = " + netIf);
//        for (int i = 0; i < 255; i++) {
//            String addrString = net + "." + i;
//            InetAddress inetAddr = InetAddress.getByName(addrString);
//            long t1 = System.currentTimeMillis();
//            Thread thread2 = new Thread(() -> {
//                try {
//                    if (inetAddr.isReachable(netIf, 1, 10)) {
//                        System.out.println("inetAddr = " + inetAddr);
//                    }
//                } catch (IOException ex) {
////                    Logger.getLogger(TestMotoPlusConnection.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            });
//            thread2.start();
//            Thread.sleep(20);
//            thread2.interrupt();
//            long t2 = System.currentTimeMillis();
//            long d = t2 - t1;
////            System.out.println("d = " + d);
//        }
        if (args.length > 0) {
            host = args[0];
        }
        System.out.println("host = " + host);
        try (MotoPlusConnection mpc = MotoPlusConnection.connectionFromSocket(new Socket(host, 12222))) {
            testFileFunctions(mpc);
//            testMoveZ(mpc);
        }
    }

    private static void testFileFunctions(MotoPlusConnection mpc) throws IOException, MotoPlusConnection.MotoPlusConnectionException {
        final String[] jbi_filenames = mpc.mpGetFileNames(MpExtensionType.MP_EXT_ID_JBI);
        System.out.println("jbi_filenames = " + Arrays.toString(jbi_filenames));
        final String[] jbr_filenames = mpc.mpGetFileNames(MpExtensionType.MP_EXT_ID_JBR);
        System.out.println("jbr_filenames = " + Arrays.toString(jbr_filenames));
        final File jbrFile = File.createTempFile(jbr_filenames[0].substring(0, jbr_filenames[0].length()-3), ".jbr");
        System.out.println("jbrFile = " + jbrFile);
        mpc.downloadMPRAM1File(jbr_filenames[0], jbrFile);
        final File jbiFile = File.createTempFile(jbi_filenames[0].substring(0, jbi_filenames[0].length()-3), ".jbi");
        System.out.println("jbiFile = " + jbiFile);
        mpc.downloadMPRAM1File(jbi_filenames[0], jbiFile);
//        int fd0 = mpc.mpOpenFile(jbi_filenames[0], MpFileFlagsEnum.O_RDONLY);
//        byte buf[] = new byte[0x400];
//        int readRet = mpc.mpReadFile(fd0, buf);
//        System.out.println("readRet = " + readRet);
//        System.out.println("buf = " + new String(buf));
//        mpc.mpCloseFile(fd0);
    }
    
    private static void testSys1Functions(MotoPlusConnection mpc) throws IOException {
        final int num = 3;
        final MP_VAR_INFO[] varInfo = new MP_VAR_INFO[num];
        final int[] rdata = new int[num];
        varInfo[0] = new MP_VAR_INFO();
        varInfo[0].usType = VarType.MP_RESTYPE_VAR_I;
        varInfo[0].usIndex = 0;
        varInfo[1] = new MP_VAR_INFO();
        varInfo[1].usType = VarType.MP_RESTYPE_VAR_I;
        varInfo[1].usIndex = 1;
        varInfo[2] = new MP_VAR_INFO();
        varInfo[2].usType = VarType.MP_RESTYPE_VAR_I;
        varInfo[2].usIndex = 2;
        System.out.println(
                "Calling mpc.mpGetVarData(\n"
                + "                " + Arrays.toString(varInfo) + ", // MP_VAR_INFO[] sData\n"
                + "                " + Arrays.toString(rdata) + ", // long[] rData, int num\n"
                + "                " + num + " // int num\n"
                + "        );");
        final boolean mpGetVarDataRet
                = mpc.mpGetVarData(
                        varInfo, // MP_VAR_INFO[] sData
                        rdata, // long[] rData
                        num // int num
                );
        System.out.println("mpGetVarDataRet = " + mpGetVarDataRet);
        System.out.println("rdata = " + Arrays.toString(rdata));
        final MP_VAR_DATA[] varData = new MP_VAR_DATA[num];
        for (int i = 0; i < varData.length && i < num && i < varInfo.length; i++) {
            MP_VAR_DATA mp_var_data = new MP_VAR_DATA();
            mp_var_data.usType = varInfo[i].usType;
            mp_var_data.usIndex = varInfo[i].usIndex;
            mp_var_data.ulValue = rdata[i];
            varData[i] = mp_var_data;
        }
        System.out.println("Calling mpc.mpPutVarData(\n"
                + "                " + Arrays.toString(varData) + ", // MP_VAR_DATA[] sData, \n"
                + "                " + num + " // int num\n"
                + "        ); ");
        final boolean mpPutVarDataRet = mpc.mpPutVarData(
                varData, // MP_VAR_DATA[] sData, 
                num // int num
        );
        System.out.println("mpPutVarDataRet = " + mpPutVarDataRet);
        int ctrlGroup = 0;
        final MP_CART_POS_RSP_DATA[] cartPosData = new MP_CART_POS_RSP_DATA[1];
        System.out.println("Calling mpc.mpGetCartPos(\n"
                + "                " + ctrlGroup + ", // int ctrlGroup\n"
                + "                " + Arrays.toString(cartPosData) + " //  MP_CART_POS_RSP_DATA[] data\n"
                + "        );");
        final boolean mpGetCartPosRet = mpc.mpGetCartPos(
                ctrlGroup, // int ctrlGroup
                cartPosData //  MP_CART_POS_RSP_DATA[] data
        );
        System.out.println("mpGetCartPosRet = " + mpGetCartPosRet);
        System.out.println("cartPosData = " + Arrays.toString(cartPosData));
        short iorData[] = new short[10];
        final MP_IO_INFO[] ioInfo = new MP_IO_INFO[10];
        for (int i = 0; i < ioInfo.length; i++) {
            MP_IO_INFO mp_io_info = new MP_IO_INFO();
            mp_io_info.ulAddr = 20050 + i;
            ioInfo[i] = mp_io_info;
        }
        System.out.println(
                "Calling mpc.mpReadIO(\n"
                + "                " + Arrays.toString(ioInfo) + ", // MP_IO_INFO[] sData\n"
                + "                " + Arrays.toString(iorData) + ", // short[] iorData\n"
                + "                10 // int num\n"
                + "        );");
        mpc.mpReadIO(
                ioInfo, // MP_IO_INFO[] sData
                iorData, // short[] iorData
                10 // int num
        );
        System.out.println("iorData = " + Arrays.toString(iorData));for (int i = 0; i < ioInfo.length; i++) {
            MP_IO_INFO mp_io_info = new MP_IO_INFO();
            mp_io_info.ulAddr = 20060 + i;
            ioInfo[i] = mp_io_info;
        }
        System.out.println(
                "Calling mpc.mpReadIO(\n"
                + "                " + Arrays.toString(ioInfo) + ", // MP_IO_INFO[] sData\n"
                + "                " + Arrays.toString(iorData) + ", // short[] iorData\n"
                + "                10 // int num\n"
                + "        );");
        mpc.mpReadIO(
                ioInfo, // MP_IO_INFO[] sData
                iorData, // short[] iorData
                10 // int num
        );
        System.out.println("iorData = " + Arrays.toString(iorData));
    }

    private static void testConvCartPosToAxesAndBack(MP_CART_POS_RSP_DATA currentCartPos, final MotoPlusConnection mpc, int[] prev_angle, MP_KINEMA_TYPE kinType, MpKinAngleReturn currentAngle) throws IOException, MotoPlusConnection.MotoPlusConnectionException {

        System.out.println("kinType = " + kinType);
        System.out.println("prev_angle = " + Arrays.toString(prev_angle));
        System.out.println("currentCartPos = " + currentCartPos);
        MP_COORD coord = currentCartPos.toMpCoord();
        System.out.println("coord = " + coord);
//        for (int j = 0; j < 64; j++) {
        MpKinAngleReturn mpConvCartPosToAxesRet
                = mpc.mpConvCartPosToAxes(0, coord, 0, currentCartPos.sConfig, prev_angle, kinType);

        MpKinCartPosReturn mpConvAxesToCartPosRet = mpc.mpConvAxesToCartPos(0, mpConvCartPosToAxesRet.angle, 0);
        System.out.println("mpConvAxesToCartPosRet.fig_ctrl=" + mpConvAxesToCartPosRet.fig_ctrl);
//            if(mpConvAxesToCartPosRet.fig_ctrl != j) {
//                continue;
//            }
        System.out.println("mpConvCartPosToAxesRet = " + mpConvCartPosToAxesRet);
        for (int i = 0; i < currentAngle.angle.length; i++) {
            long diff = currentAngle.angle[i] - mpConvCartPosToAxesRet.angle[i];
            long diffmod = (diff < -1800000 ? diff + 3600000 : (diff > 1800000 ? diff - 3600000 : diff)) % 3600000;
            System.out.println("i=" + i + ", diff = " + diff + ", diffmod=" + diffmod);
        }
        System.out.println("mpConvAxesToCartPosRet = " + mpConvAxesToCartPosRet);
        MP_COORD coorddiff = coord.diff(mpConvAxesToCartPosRet.coord);
        System.out.println("coorddiff = " + coorddiff);
        MpKinPulseReturn mpConvAngleToPulseRet1 = mpc.mpConvAngleToPulse(0, mpConvCartPosToAxesRet.angle);
        System.out.println("mpConvAngleToPulseRet1 (0,mpConvCartPosToAxesRet.angle) = " + mpConvAngleToPulseRet1);
        MpKinPulseReturn mpConvAngleToPulseRet2 = mpc.mpConvAngleToPulse(0, prev_angle);
        System.out.println("mpConvAngleToPulseRet2 (0,prev_angle) = " + mpConvAngleToPulseRet2);
    }

    private static void testMoveZ(final MotoPlusConnection mpc) throws InterruptedException, IOException, MotoPlusConnection.MotoPlusConnectionException {
        System.out.println("Calling mpMotStop(0)");
        MotCtrlReturnEnum motStopRet = mpc.mpMotStop(0);
        System.out.println("motStopRet = " + motStopRet);

        System.out.println("Calling mpMotTargetClear(1,0)");
        MotCtrlReturnEnum motTargetClearRet = mpc.mpMotTargetClear(1, 0);
        System.out.println("motTargetClearRet = " + motTargetClearRet);

        System.out.println("Calling mpSetServoPower(true)");
        mpc.mpSetServoPower(true);
        System.out.println("Calling mpGetServoPower()");
        boolean on = mpc.mpGetServoPower();
        System.out.println("on = " + on);

        System.out.println("Calling mpMotSetCoord(1, MP_COORD_TYPE.MP_ROBOT_TYPE, 0)");
        MotCtrlReturnEnum motSetCoordRet = mpc.mpMotSetCoord(0, MP_COORD_TYPE.MP_ROBOT_TYPE, 0);
        System.out.println("motSetCoordRet = " + motSetCoordRet);
        MP_SPEED spd = new MP_SPEED();
        spd.v = 200;
        System.out.println("Calling mpMotSetSpeed(0," + spd + ")");
        mpc.mpMotSetSpeed(0, spd);

        System.out.println("Calling mpGetCartPos(0,...)");
        MP_CART_POS_RSP_DATA cartResData = mpc.getCartPos(0);
        System.out.println("cartResData = " + cartResData);
        CoordTarget coordTarget = new CoordTarget();
        coordTarget.setId(16);
        coordTarget.setIntp(MP_INTP_TYPE.MP_MOVL_TYPE);
        COORD_POS cp = coordTarget.getDst();
        cp.x = (int) cartResData.lx();
        cp.y = (int) cartResData.ly();
        cp.z = (int) cartResData.lz() + Z_MOVE_MICROS;
        cp.rx = (int) cartResData.lrx();
        cp.ry = (int) cartResData.lry();
        cp.rz = (int) cartResData.lrz();
        cp = coordTarget.getAux();
        cp.x = (int) cartResData.lx();
        cp.y = (int) cartResData.ly();
        cp.z = (int) cartResData.lz() + Z_MOVE_MICROS;
        cp.rx = (int) cartResData.lrx();
        cp.ry = (int) cartResData.lry();
        cp.rz = (int) cartResData.lrz();

        System.out.println("coordTarget = " + coordTarget);
        System.out.println("Calling mpMotTargetCoordSend(0,(...),0)\n");
        MotCtrlReturnEnum motTargetCoordRet = mpc.mpMotTargetCoordSend(1, coordTarget, 0);
        System.out.println("motTargetCoordRet = " + motTargetCoordRet);
        Thread.sleep(2000);

        System.out.println("Calling mpMotStart(0)");
        final MotCtrlReturnEnum mpMotStartRet = mpc.mpMotStart(0);
        System.out.println("mpMotStartRet = " + mpMotStartRet);
        Thread.sleep(2000);

        int[] recvId = new int[1];
        final int MAX_WAIT = mpc.mpGetMaxWait();
        System.out.println("MAX_WAIT = " + MAX_WAIT);
        System.out.println("Calling mpMotTargetReceive(0," + coordTarget.getId() + ",...," + MAX_WAIT + ",0)");
        final MotCtrlReturnEnum mpMotTargetReceiveRet
                = mpc.mpMotTargetReceive(0, coordTarget.getId(), recvId, mpc.mpGetMaxWait(), 0);
        System.out.println("mpMotTargetReceiveRet = " + mpMotTargetReceiveRet);
        System.out.println("recvId = " + Arrays.toString(recvId));
        Thread.sleep(2000);

        System.out.println("Calling mpMotStop(0)");
        final MotCtrlReturnEnum mpMotStopRet = mpc.mpMotStop(0);
        System.out.println("mpMotStopRet = " + mpMotStopRet);

        CoordTarget coordTarget2 = new CoordTarget();
        coordTarget2.setId(17);
        coordTarget2.setIntp(MP_INTP_TYPE.MP_MOVL_TYPE);
        COORD_POS cp2 = coordTarget2.getDst();
        cp2 = coordTarget2.getDst();
        cp2.x = (int) cartResData.lx();
        cp2.y = (int) cartResData.ly();
        cp2.z = (int) cartResData.lz();
        cp2.rx = (int) cartResData.lrx();
        cp2.ry = (int) cartResData.lry();
        cp2.rz = (int) cartResData.lrz();
        cp2 = coordTarget2.getAux();
        cp2.x = (int) cartResData.lx();
        cp2.y = (int) cartResData.ly();
        cp2.z = (int) cartResData.lz();
        cp2.rx = (int) cartResData.lrx();
        cp2.ry = (int) cartResData.lry();
        cp2.rz = (int) cartResData.lrz();
        coordTarget2.setId(17);

        System.out.println("coordTarget = " + coordTarget2);
        System.out.println("Calling mpMotTargetCoordSend(0,(...),0)\n");
        motTargetCoordRet = mpc.mpMotTargetCoordSend(1, coordTarget2, 0);
        System.out.println("motTargetCoordRet = " + motTargetCoordRet);
        Thread.sleep(200);

        System.out.println("Calling mpMotStart(0)");
        final MotCtrlReturnEnum mpMotStartRet2 = mpc.mpMotStart(0);
        System.out.println("mpMotStartRet2 = " + mpMotStartRet2);
        Thread.sleep(200);

        recvId = new int[1];
        System.out.println("Calling mpMotTargetReceive(0," + coordTarget2.getId() + ",...," + MAX_WAIT + ",0)");
        final MotCtrlReturnEnum mpMotTargetReceiveRet2
                = mpc.mpMotTargetReceive(0, coordTarget2.getId(), recvId, mpc.mpGetMaxWait(), 0);
        System.out.println("mpMotTargetReceiveRet2 = " + mpMotTargetReceiveRet2);
        System.out.println("recvId = " + Arrays.toString(recvId));
//

        System.out.println("Calling mpMotStop(0)");
        final MotCtrlReturnEnum mpMotStopRet2 = mpc.mpMotStop(0);
        System.out.println("mpMotStopRet2 = " + mpMotStopRet2);
        Thread.sleep(200);
        System.out.println("Calling mpGetCartPos(0,...)");
        MP_CART_POS_RSP_DATA cartResData2 = mpc.getCartPos(0);
        System.out.println("cartResData2 = " + cartResData2);
        System.out.println("Calling mpSetServoPower(false)");
        mpc.mpSetServoPower(false);
        System.out.println("Calling mpGetServoPower()");
        on = mpc.mpGetServoPower();
        System.out.println("on = " + on);
    }
    private static final int Z_MOVE_MICROS = 200000;

    private static void testMoveJointS(final MotoPlusConnection mpc) throws InterruptedException, IOException, MotoPlusConnection.MotoPlusConnectionException {
        System.out.println("Calling mpMotStop(0)");
        MotCtrlReturnEnum motStopRet = mpc.mpMotStop(0);
        System.out.println("motStopRet = " + motStopRet);

        System.out.println("Calling mpMotTargetClear(1,0)");
        MotCtrlReturnEnum motTargetClearRet = mpc.mpMotTargetClear(1, 0);
        System.out.println("motTargetClearRet = " + motTargetClearRet);

        System.out.println("Calling mpSetServoPower(true)");
        mpc.mpSetServoPower(true);
        System.out.println("Calling mpGetServoPower()");
        boolean on = mpc.mpGetServoPower();
        System.out.println("on = " + on);

        System.out.println("Calling mpMotSetCoord(1, MP_COORD_TYPE.MP_PULSE_TYPE, 0)");
        MotCtrlReturnEnum motSetCoordRet = mpc.mpMotSetCoord(0, MP_COORD_TYPE.MP_PULSE_TYPE, 0);
        System.out.println("motSetCoordRet = " + motSetCoordRet);
        MP_SPEED spd = new MP_SPEED();
        spd.v = 1;
        spd.vj = 1000;
        spd.vr = 1;
        int grp = 0;
        int dist = 20000;

        System.out.println("Calling getPulsePos(0)");
        MP_PULSE_POS_RSP_DATA pulseData = mpc.getPulsePos(0);
        System.out.println("pulseData = " + pulseData);
        JointTarget jointTarget = new JointTarget();
        jointTarget.setId(26);
        jointTarget.setIntp(MP_INTP_TYPE.MP_MOVJ_TYPE);
        int dst[] = jointTarget.getDst();
        System.arraycopy(pulseData.lPos, 0, dst, 0, dst.length);
        int aux[] = jointTarget.getDst();
        System.arraycopy(pulseData.lPos, 0, aux, 0, aux.length);
        dst[0] += dist;
        aux[0] += dist;

        System.out.println("jointTarget = " + jointTarget);
        System.out.println("Calling mpMotSetSpeed(" + grp + "," + spd + ")");
        mpc.mpMotSetSpeed(grp, spd);
        System.out.println("Calling mpMotTargetJointSend(0,(...),0)\n");
        MotCtrlReturnEnum motTargeJointRet = mpc.mpMotTargetJointSend(1, jointTarget, 0);
        System.out.println("motTargeJointRet = " + motTargeJointRet);
        Thread.sleep(2000);

        System.out.println("Calling mpMotStart(0)");
        motTargetClearRet = mpc.mpMotStart(0);
        System.out.println("motStartRet = " + motTargetClearRet);
        Thread.sleep(2000);

        int[] recvId = new int[1];
        final int MAX_WAIT = mpc.mpGetMaxWait();
        System.out.println("MAX_WAIT = " + MAX_WAIT);
        System.out.println("Calling mpMotTargetReceive(0," + jointTarget.getId() + ",...,MAX_WAIT,0)");
        mpc.mpMotTargetReceive(0, jointTarget.getId(), recvId, MAX_WAIT + 1000, 0);
        System.out.println("recvId = " + Arrays.toString(recvId));
        Thread.sleep(2000);

        System.out.println("Calling mpMotStop(0)");
        motTargetClearRet = mpc.mpMotStop(0);

        dst = jointTarget.getDst();
        aux = jointTarget.getAux();
        System.arraycopy(pulseData.lPos, 0, dst, 0, dst.length);
        System.arraycopy(pulseData.lPos, 0, aux, 0, aux.length);
        jointTarget.setId(27);

        System.out.println("Calling mpMotSetSpeed(" + grp + "," + spd + ")");
        mpc.mpMotSetSpeed(grp, spd);
        System.out.println("jointTarget = " + jointTarget);
        System.out.println("Calling mpMotTargetCoordSend(0,(...),0)\n");
        motTargeJointRet = mpc.mpMotTargetJointSend(1, jointTarget, 0);
        System.out.println("motTargeJointRet = " + motTargeJointRet);
        Thread.sleep(200);

        System.out.println("Calling mpMotStart(0)");
        motTargetClearRet = mpc.mpMotStart(0);
        System.out.println("motStartRet = " + motTargetClearRet);
        Thread.sleep(2000);

        recvId = new int[1];
        System.out.println("Calling mpMotTargetReceive(0," + jointTarget.getId() + ",...,WAIT_FOREVER,0)");
        mpc.mpMotTargetReceive(0, jointTarget.getId(), recvId, MAX_WAIT, 0);
        System.out.println("recvId = " + Arrays.toString(recvId));
//

        System.out.println("Calling mpMotStop(0)");
        motTargetClearRet = mpc.mpMotStop(0);

        System.out.println("motStartRet = " + motTargetClearRet);
        Thread.sleep(200);
        System.out.println("Calling mpSetServoPower(false)");
        mpc.mpSetServoPower(false);
        System.out.println("Calling mpGetServoPower()");
        on = mpc.mpGetServoPower();
        System.out.println("on = " + on);
    }
}
