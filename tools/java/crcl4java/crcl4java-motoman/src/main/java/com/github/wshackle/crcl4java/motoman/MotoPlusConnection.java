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

import com.github.wshackle.crcl4java.motoman.exfile.MP_GET_JOBLIST_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.exfile.MpExFileRamIdEnum;
import com.github.wshackle.crcl4java.motoman.exfile.MpExtensionType;
import com.github.wshackle.crcl4java.motoman.exfile.RemoteExFileFunctionType;
import com.github.wshackle.crcl4java.motoman.file.MpFileFlagsEnum;
import com.github.wshackle.crcl4java.motoman.file.RemoteFileFunctionType;
import com.github.wshackle.crcl4java.motoman.force.FCS_COORD_TYPE;
import com.github.wshackle.crcl4java.motoman.force.FcsReturnCode;
import com.github.wshackle.crcl4java.motoman.force.MP_FCS_ROB_ID;
import com.github.wshackle.crcl4java.motoman.force.MpFcsBaseReturn;
import com.github.wshackle.crcl4java.motoman.force.MpFcsGetForceDataReturn;
import com.github.wshackle.crcl4java.motoman.force.MpFcsGetSensorDataReturn;
import com.github.wshackle.crcl4java.motoman.force.MpFcsStartMeasuringReturn;
import com.github.wshackle.crcl4java.motoman.force.RemoteForceControlFunctionType;
import com.github.wshackle.crcl4java.motoman.kinematics.KinReturnCode;
import com.github.wshackle.crcl4java.motoman.kinematics.MP_COORD;
import com.github.wshackle.crcl4java.motoman.kinematics.MP_KINEMA_TYPE;
import com.github.wshackle.crcl4java.motoman.kinematics.MpKinAngleReturn;
import com.github.wshackle.crcl4java.motoman.kinematics.MpKinCartPosReturn;
import com.github.wshackle.crcl4java.motoman.kinematics.MpKinPulseReturn;
import com.github.wshackle.crcl4java.motoman.kinematics.RemoteKinematicsConversionFunctionType;
import com.github.wshackle.crcl4java.motoman.motctrl.COORD_POS;
import com.github.wshackle.crcl4java.motoman.motctrl.RemoteMotFunctionType;
import com.github.wshackle.crcl4java.motoman.motctrl.CoordTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.JointTarget;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_COORD_TYPE;
import com.github.wshackle.crcl4java.motoman.motctrl.MP_SPEED;
import com.github.wshackle.crcl4java.motoman.motctrl.MotCtrlReturnEnum;
import com.github.wshackle.crcl4java.motoman.sys1.CycleEnum;
import com.github.wshackle.crcl4java.motoman.sys1.MP_ALARM_CODE_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_ALARM_STATUS_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_CART_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_CYCLE_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_DEG_POS_RSP_DATA_EX;
import com.github.wshackle.crcl4java.motoman.sys1.MP_FB_PULSE_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_IO_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_IO_INFO;
import com.github.wshackle.crcl4java.motoman.sys1.MP_MODE_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_PULSE_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_SVAR_RECV_INFO;
import static com.github.wshackle.crcl4java.motoman.sys1.MP_SVAR_RECV_INFO.MAX_SVAR_SIZE;
import com.github.wshackle.crcl4java.motoman.sys1.MP_SVAR_SEND_INFO;
import com.github.wshackle.crcl4java.motoman.sys1.MP_VAR_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_VAR_INFO;
import com.github.wshackle.crcl4java.motoman.sys1.ModeEnum;
import com.github.wshackle.crcl4java.motoman.sys1.RemoteSys1FunctionType;
import com.github.wshackle.crcl4java.motoman.sys1.UnitType;
import crcl.base.CommandStateEnumType;
import static crcl.base.CommandStateEnumType.CRCL_ERROR;
import static crcl.base.CommandStateEnumType.CRCL_WORKING;
import crcl.utils.XFuture;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class MotoPlusConnection implements AutoCloseable {

    private static class DefaultHostHolder {

        static final String DEFAULT_HOST = getDefaultHost();

        private static String getDefaultHost() {
            try {
                String propVal = System.getProperty("MotoPlusHost");
                System.out.println("System.getProperty(\"MotoPlusHost\") = " + propVal);
                if (propVal != null && propVal.length() > 0) {
                    return propVal;
                }
                String envVal = System.getenv("MotoPlusHost");
                System.out.println("System.getenv(\"MotoPlusHost\") = " + envVal);
                if (envVal != null && envVal.length() > 0) {
                    return envVal;
                }
                InetAddress addrToTry = InetAddress.getByName(DEFAULT_ADDRESS_TO_TRY);
                final boolean isAddrToTryReachable = addrToTry.isReachable(2000);
                System.out.println(DEFAULT_ADDRESS_TO_TRY + " is reachable = " + isAddrToTryReachable);
                if (isAddrToTryReachable) {

                    return DEFAULT_ADDRESS_TO_TRY;
                }
                return "localhost";
            } catch (Exception ex) {
                Logger.getLogger(MotoPlusConnection.class.getName()).log(Level.SEVERE, null, ex);
                return "localhost";
            }
        }
        public static final String DEFAULT_ADDRESS_TO_TRY = "192.168.1.33";
    }

    public static String getDefaultHost() {
        return DefaultHostHolder.DEFAULT_HOST;
    }

    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    public static final int NO_WAIT = 0;
    private static final int WAIT_FOREVER = -1; // This is not allowed to avoid hanging server.

    private volatile int maxWait = -99;

    public synchronized int mpGetMaxWait() throws IOException, MotoPlusConnectionException {
        if (maxWait == -99) {
            maxWait = 5000 / mpGetRtc();
        }
        return maxWait;
    }

    public synchronized static MotoPlusConnection connectionFromSocket(Socket socket) throws IOException {
        MotoPlusConnection mpc = new MotoPlusConnection(socket);
        Map<Integer, Collection<MotoPlusConnection>> hostMpcMap
                = mpcConnections.computeIfAbsent(
                        socket.getInetAddress().getHostAddress(),
                        (String addrString) -> new ConcurrentHashMap<>()
                );
        Collection<MotoPlusConnection> mpcHostPortCollection
                = hostMpcMap.computeIfAbsent(
                        socket.getPort(),
                        (Integer port) -> new ConcurrentLinkedDeque<>());
        mpcHostPortCollection.add(mpc);
        return mpc;
    }

    public synchronized static MotoPlusConnection connectionFromHostPort(String host, int port) throws IOException {
        Map<Integer, Collection<MotoPlusConnection>> hostMpcMap;

        if (host != null && host.length() > 1) {
            hostMpcMap
                    = mpcConnections.computeIfAbsent(
                            host,
                            (String addrString) -> new ConcurrentHashMap<>()
                    );
        } else {
            final Collection<Map<Integer, Collection<MotoPlusConnection>>> values = mpcConnections.values();
            if (values.isEmpty()) {
                throw new RuntimeException("host=" + host + " and values.isEmpty()");
            }
            hostMpcMap = values.iterator().next();
        }
        Collection<MotoPlusConnection> mpcHostPortCollection;

        if (port > 0) {
            mpcHostPortCollection = hostMpcMap.computeIfAbsent(
                    port,
                    (Integer portI) -> new ConcurrentLinkedDeque<>());
        } else {
            final Collection<Collection<MotoPlusConnection>> values = hostMpcMap.values();
            if (values.isEmpty()) {
                throw new RuntimeException("port=" + port + " and values.isEmpty()");
            }
            mpcHostPortCollection = values.iterator().next();
        }
        for (MotoPlusConnection mpc : mpcHostPortCollection) {
            if (mpc.isConnected()) {
                return mpc;
            }
        }
        if (port < 0 || null == host || host.length() < 1) {
            throw new RuntimeException("host=" + host + ",port=" + port + " and values.isEmpty()");
        }
        MotoPlusConnection newMpc = connectionFromSocket(new Socket(host, port));
        mpcHostPortCollection.add(newMpc);
        return newMpc;
    }

    private final StackTraceElement createTrace[];

    private MotoPlusConnection(Socket socket) throws IOException {
        createTrace = Thread.currentThread().getStackTrace();
        this.socket = socket;
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
    }

    private static final Map<String, Map<Integer, Collection<MotoPlusConnection>>> mpcConnections
            = new ConcurrentHashMap<>();

    public boolean isConnected() {
        return null != socket && socket.isConnected();
    }

    private String host;
    private int port;

    public void connect(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        if (null != socket) {
            socket.close();
        }
        System.out.println("Connecting to " + host + ", port=" + port + " . . .");
        socket = new Socket(host, port);
        System.out.println("Connection successful.");
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
    }

    public void reconnect() throws IOException {
        if (!isConnected() && null != host && port > 0) {
            connect(host, port);
        }
    }

    @Override
    public void close() throws Exception {
        if (null != dos) {
            try {
                dos.close();

            } catch (IOException iOException) {
            }
            dos = null;
        }
        if (null != dis) {
            try {
                dis.close();

            } catch (IOException iOException) {
            }
            dis = null;
        }
        if (null != socket) {
            try {
                socket.close();

            } catch (IOException iOException) {
            }
            socket = null;
        }
    }

    public String getHost() {
        if (null == socket) {
            throw new RuntimeException("socket==null");
        }
        final InetAddress inetAddress = socket.getInetAddress();
        if (null == inetAddress) {
            throw new RuntimeException("socket.getInetAddress()==null");
        }
        return inetAddress.getCanonicalHostName();
    }

    public int getPort() {
        if (null == socket) {
            throw new RuntimeException("socket==null");
        }
        return socket.getPort();
    }

    private final ConcurrentLinkedDeque<Consumer<String>> logListeners = new ConcurrentLinkedDeque<>();

    public void addLogListener(Consumer<String> consumer) {
        logListeners.add(consumer);
    }

    public void removeLogListener(Consumer<String> consumer) {
        logListeners.remove(consumer);
    }

    private boolean debug = false;

    /**
     * Get the value of debug
     *
     * @return the value of debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * Set the value of debug
     *
     * @param debug new value of debug
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    private void printDebug(String s) {
        if (debug) {
            for (Consumer<String> listener : logListeners) {
                listener.accept(s);
            }
            System.out.print(s);
        }
    }

    private volatile Thread lastThreadUsed = null;
    private volatile StackTraceElement lastThreadSetTrace[] = null;

    private void writeDataOutputStream(ByteBuffer bb) throws IOException {
        final Thread t = Thread.currentThread();
        if (t != lastThreadUsed) {
            if (null != lastThreadUsed) {
                System.err.println("createTrace = " + XFuture.traceToString(createTrace));
                System.err.println("lastThreadSetTrace = " + XFuture.traceToString(lastThreadSetTrace));
                Thread.dumpStack();
                throw new RuntimeException("CurrenThread(" + t + ") != lastThreadUsed : " + lastThreadUsed);
            }
            lastThreadSetTrace = Thread.currentThread().getStackTrace();
            lastThreadUsed = t;
        }
        final byte[] array = bb.array();
        dos.write(array);
        if (debug) {
            printDebug("wrote " + array.length + " bytes.\n");
            debugPrintBytes(array);
        }
    }

    private void readDataInputStream(byte[] inbuf) throws IOException {
        final Thread t = Thread.currentThread();
        if (t != lastThreadUsed) {
            if (null != lastThreadUsed) {
                System.err.println("createTrace = " + XFuture.traceToString(createTrace));
                System.err.println("lastThreadSetTrace = " + XFuture.traceToString(lastThreadSetTrace));
                Thread.dumpStack();
                throw new RuntimeException("CurrenThread(" + t + ") != lastThreadUsed : " + lastThreadUsed);
            }
            lastThreadSetTrace = Thread.currentThread().getStackTrace();
            lastThreadUsed = t;
        }
        dis.readFully(inbuf);
        if (debug) {
            printDebug("read " + inbuf.length + " bytes.\n");
            debugPrintBytes(inbuf);
        }
    }

    private void debugPrintBytes(final byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i += 16) {
            for (int j = i; j < array.length && j < i + 4; j++) {
                sb.append(String.format("%02x", array[j]));
            }
            sb.append(' ');
            for (int j = i + 4; j < array.length && j < i + 8; j++) {
                sb.append(String.format("%02x", array[j]));
            }
            sb.append(' ');
            for (int j = i + 8; j < array.length && j < i + 12; j++) {
                sb.append(String.format("%02x", array[j]));
            }
            sb.append(' ');
            for (int j = i + 12; j < array.length && j < i + 16; j++) {
                sb.append(String.format("%02x", array[j]));
            }
            sb.append('\n');
        }
        printDebug(sb.toString());
    }

    public final class Starter {

        private Starter() {
            // only one needed per MotoPlusConnection instance
        }

        public void startMpLoadFile(int fd, String name, String name2) throws IOException {
            final int inputSize = name.length() + name2.length() + 22;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.EX_FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteExFileFunctionType.EX_FILE_CTRL_LOAD_FILE.getId()); // type of function remote server will call
            bb.putInt(12, fd);
            int offset = 21 + name.length();
            bb.putInt(16, offset);
            bb.position(20);
            bb.put(name.getBytes());
            bb.position(offset);
            bb.put(name2.getBytes());
            writeDataOutputStream(bb);
        }

        public void startMpSaveFile(int fd, String name, String name2) throws IOException {
            final int inputSize = name.length() + name2.length() + 22;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.EX_FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteExFileFunctionType.EX_FILE_CTRL_SAVE_FILE.getId()); // type of function remote server will call
            bb.putInt(12, fd);
            int offset = 21 + name.length();
            bb.putInt(16, offset);
            bb.position(20);
            bb.put(name.getBytes());
            bb.position(offset);
            bb.put(name2.getBytes());
            writeDataOutputStream(bb);
        }

        public void startMpFdReadFile(int fd, String name) throws IOException {
            final int inputSize = name.length() + 17;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.EX_FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteExFileFunctionType.EX_FILE_CTRL_FD_READ_FILE.getId()); // type of function remote server will call
            bb.putInt(12, fd);
            bb.position(16);
            bb.put(name.getBytes());
            writeDataOutputStream(bb);
        }

        public void startMpFdWriteFile(int fd, String name) throws IOException {
            final int inputSize = name.length() + 17;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.EX_FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteExFileFunctionType.EX_FILE_CTRL_FD_WRITE_FILE.getId()); // type of function remote server will call
            bb.putInt(12, fd);
            bb.position(16);
            bb.put(name.getBytes());
            writeDataOutputStream(bb);
        }

        public void startMpFdGetJobList(int fd, MP_GET_JOBLIST_RSP_DATA jlistData) throws IOException {
            final int inputSize = 16;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.EX_FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteExFileFunctionType.EX_FILE_CTRL_FD_GET_JOB_LIST.getId()); // type of function remote server will call
            bb.putInt(12, fd);
            writeDataOutputStream(bb);
        }

        public void startMpOpenFile(String name, MpFileFlagsEnum flags) throws IOException {
            final int inputSize = name.length() + 21;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteFileFunctionType.FILE_CTRL_OPEN.getId()); // type of function remote server will call
            bb.putInt(12, flags.getId());
            // mode  is not used
            bb.putInt(16, 0);
            bb.position(20);
            bb.put(name.getBytes());
            writeDataOutputStream(bb);
        }

        public void startMpCreateFile(String name, int flags) throws IOException {
            final int inputSize = name.length() + 17;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteFileFunctionType.FILE_CTRL_CREATE.getId()); // type of function remote server will call
            bb.putInt(12, flags);
            bb.position(16);
            bb.put(name.getBytes());
            writeDataOutputStream(bb);
        }

        public void startMpCloseFile(int fd) throws IOException {
            final int inputSize = 16;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteFileFunctionType.FILE_CTRL_CLOSE.getId()); // type of function remote server will call
            bb.putInt(12, fd);
            writeDataOutputStream(bb);
        }

        public void startMpReadFile(int fd, int maxBytes) throws IOException {
            final int inputSize = 20;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteFileFunctionType.FILE_CTRL_READ.getId()); // type of function remote server will call
            bb.putInt(12, fd);
            bb.putInt(16, maxBytes);
            writeDataOutputStream(bb);
        }

        public void startMpWriteFile(int fd, byte bytesToWrite[], int offset, int maxBytes) throws IOException {
            if (maxBytes > bytesToWrite.length - offset) {
                maxBytes = bytesToWrite.length - offset;
            }
            final int inputSize = 20 + bytesToWrite.length;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteFileFunctionType.FILE_CTRL_WRITE.getId()); // type of function remote server will call
            bb.putInt(12, fd);
            bb.putInt(16, maxBytes);
            bb.position(20);
            bb.put(bytesToWrite, offset, maxBytes);
            writeDataOutputStream(bb);
        }

        public void startMpGetFileCount(MpExtensionType ext) throws IOException {
            final int inputSize = 14;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.EX_FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteExFileFunctionType.EX_FILE_CTRL_GET_FILE_COUNT.getId()); // type of function remote server will call
            bb.putShort(12, ext.getId());
            writeDataOutputStream(bb);
        }

        public void startMpGetFileName(MpExtensionType ext, int index) throws IOException {
            final int inputSize = 18;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.EX_FILE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteExFileFunctionType.EX_FILE_CTRL_GET_FILE_NAME.getId()); // type of function remote server will call
            bb.putShort(12, ext.getId());
            bb.putInt(14, index);
            writeDataOutputStream(bb);
        }

        public void startMpMotStart(int options) throws IOException {
            final int inputSize = 16;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteMotFunctionType.MOT_START.getId()); // type of function remote server will call
            bb.putInt(12, options);
            writeDataOutputStream(bb);
        }

        public void startMpMotStop(int options) throws IOException {
            final int inputSize = 16;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteMotFunctionType.MOT_STOP.getId()); // type of function remote server will call
            bb.putInt(12, options);
            writeDataOutputStream(bb);
            lastCoordTargetDest = null;
        }

        public void startMpMotTargetClear(int grp, int options) throws IOException {
            final int inputSize = 20;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteMotFunctionType.MOT_TARGET_CLEAR.getId()); // type of function remote server will call
            bb.putInt(12, grp);
            bb.putInt(16, options);
            writeDataOutputStream(bb);
            lastCoordTargetDest = null;
        }

        public void startMpMotTargetJointSend(int grp, JointTarget target, int timeout) throws IOException {
            final int inputSize = 92;
            checkTimeout(timeout);
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteMotFunctionType.MOT_JOINT_TARGET_SEND.getId()); // type of function remote server will call
            bb.putInt(12, grp);
            bb.putInt(16, target.getId());
            bb.putInt(20, target.getIntp().getId());
            for (int i = 0; i < 8 /* MP_GRP_AXES_NUM */; i++) {
                bb.putInt(24 + (i * 4), target.getDst()[i]);
            }
            for (int i = 0; i < 8 /* MP_GRP_AXES_NUM */; i++) {
                bb.putInt(56 + (i * 4), target.getAux()[i]);
            }
            bb.putInt(88, timeout);
            writeDataOutputStream(bb);
            lastCoordTargetDest = null;
        }

        public void startMpMotTargetCoordSend(int grp, CoordTarget target, int timeout) throws IOException {
            final int inputSize = 92;
            checkTimeout(timeout);
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteMotFunctionType.MOT_COORD_TARGET_SEND.getId()); // type of function remote server will call
            bb.putInt(12, grp);
            bb.putInt(16, target.getId());
            bb.putInt(20, target.getIntp().getId());
            bb.putInt(24, target.getDst().x);
            bb.putInt(28, target.getDst().y);
            bb.putInt(32, target.getDst().z);
            bb.putInt(36, target.getDst().rx);
            bb.putInt(40, target.getDst().ry);
            bb.putInt(44, target.getDst().rz);
            bb.putInt(48, target.getDst().ex1);
            bb.putInt(52, target.getDst().ex2);
            bb.putInt(56, target.getAux().x);
            bb.putInt(60, target.getAux().y);
            bb.putInt(64, target.getAux().z);
            bb.putInt(68, target.getAux().rx);
            bb.putInt(72, target.getAux().ry);
            bb.putInt(76, target.getAux().rz);
            bb.putInt(80, target.getAux().ex1);
            bb.putInt(84, target.getAux().ex2);
            bb.putInt(88, timeout);
            writeDataOutputStream(bb);
            lastCoordTargetDest = target.getDst();
            afterMove = true;
        }

        public void startMpMotTargetReceive(int grpNo, int id, int timeout, int options) throws IOException {
            checkTimeout(timeout);
            final int inputSize = 28;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteMotFunctionType.MOT_TARGET_RECEIVE.getId()); // type of function remote server will call
            bb.putInt(12, grpNo);
            bb.putInt(16, id);
            bb.putInt(20, timeout);
            bb.putInt(24, options);
            writeDataOutputStream(bb);
        }

        public void startMpMotSetCoord(int grpNo, MP_COORD_TYPE type, int aux) throws IOException {
            final int inputSize = 24;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteMotFunctionType.MOT_SET_COORD.getId()); // type of function remote server will call
            bb.putInt(12, grpNo);
            bb.putInt(16, type.getId());
            bb.putInt(20, aux);
            writeDataOutputStream(bb);
        }

        public void startMpMotSetTool(int grpNo, int toolNo) throws IOException {
            final int inputSize = 20;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteMotFunctionType.MOT_SET_TOOL.getId()); // type of function remote server will call
            bb.putInt(12, grpNo);
            bb.putInt(16, toolNo);
            writeDataOutputStream(bb);
        }

        public void startMpMotSetSpeed(int grpNo, MP_SPEED spd) throws IOException {
            final int inputSize = 36;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteMotFunctionType.MOT_SET_SPEED.getId()); // type of function remote server will call
            bb.putInt(12, grpNo);
            bb.putInt(16, spd.vj);
            bb.putInt(24, spd.v);
            bb.putInt(32, spd.vr);
            writeDataOutputStream(bb);
        }

        public void startMpMotSetOrigin(int grpNo, int options) throws IOException {
            final int inputSize = 20;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteMotFunctionType.MOT_SET_ORIGIN.getId()); // type of function remote server will call
            bb.putInt(12, grpNo);
            bb.putInt(16, options);
            writeDataOutputStream(bb);
        }

        public void startMpMotSetTask(int grpNo, int taskNo) throws IOException {
            final int inputSize = 20;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteMotFunctionType.MOT_SET_TASK.getId()); // type of function remote server will call
            bb.putInt(12, grpNo);
            bb.putInt(16, taskNo);
            writeDataOutputStream(bb);
        }

        public void startMpMotSetSync(int grpNo, int aux, int options) throws IOException {
            final int inputSize = 24;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteMotFunctionType.MOT_SET_TASK.getId()); // type of function remote server will call
            bb.putInt(12, grpNo);
            bb.putInt(16, aux);
            bb.putInt(20, options);
            writeDataOutputStream(bb);
        }

        public void startMpMotSetSync(int grpNo) throws IOException {
            final int inputSize = 16;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.MOT_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteMotFunctionType.MOT_SET_TASK.getId()); // type of function remote server will call
            bb.putInt(12, grpNo);
            writeDataOutputStream(bb);
        }

        public void startMpPutVarData(MP_VAR_DATA[] sData, int num) throws IOException {
            final int inputSize = 16 + (8 * num);
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_PUT_VAR_DATA.getId()); // type of function remote server will call
            bb.putInt(12, num);
            for (int i = 0; i < num; i++) {
                bb.putShort(16 + (i * 8), sData[i].usType.getId());
                bb.putShort(18 + (i * 8), sData[i].usIndex);
                bb.putInt(20 + (i * 8), sData[i].ulValue);
            }
            writeDataOutputStream(bb);
        }

        public void startMpPutSVarInfo(MP_SVAR_SEND_INFO[] sData, int num) throws IOException {
            final int inputSize = 16 + (8 * num);
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_PUT_VAR_DATA.getId()); // type of function remote server will call
            bb.putInt(12, num);
            for (int i = 0; i < num; i++) {
                bb.putShort(16 + (i * 8), sData[i].usType.getId());
                bb.putShort(18 + (i * 8), sData[i].usIndex);
                bb.put(sData[i].ucValue);
            }
            writeDataOutputStream(bb);
        }

        public void startMpWriteIO(MP_IO_DATA[] sData, int num) throws IOException {
            final int inputSize = 16 + (8 * num);
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_WRITEIO.getId()); // type of function remote server will call
            bb.putInt(12, num);
            for (int i = 0; i < num; i++) {
                bb.putInt(16 + (i * 8), sData[i].ulAddr);
                bb.putInt(20 + (i * 8), sData[i].ulValue);
            }
            writeDataOutputStream(bb);
        }

        public void startMpGetVarData(MP_VAR_INFO[] sData, int num) throws IOException {
            final int inputSize = (int) (16 + (4 * num));
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_VAR_DATA.getId()); // type of function remote server will call
            bb.putInt(12, num);
            for (int i = 0; i < num; i++) {
                bb.putShort(16 + (4 * i), sData[i].usType.getId());
                bb.putShort(18 + (4 * i), sData[i].usIndex);
            }
            writeDataOutputStream(bb);
        }

        public void startMpGetSVarInfo(MP_VAR_INFO[] sData, int num) throws IOException {
            final int inputSize = (int) (16 + (4 * num));
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_SVAR_INFO.getId()); // type of function remote server will call
            bb.putInt(12, num);
            for (int i = 0; i < num; i++) {
                bb.putShort(16 + (4 * i), sData[i].usType.getId());
                bb.putShort(18 + (4 * i), sData[i].usIndex);
            }
            writeDataOutputStream(bb);
        }

        public void startMpReadIO(MP_IO_INFO[] sData, short[] iorData, int num) throws IOException {
            final int inputSize = (int) (16 + (4 * num));
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_READIO.getId()); // type of function remote server will call
            bb.putInt(12, num);
            for (int i = 0; i < num; i++) {
                bb.putInt(16 + (4 * i), sData[i].ulAddr);
            }
            writeDataOutputStream(bb);
        }

        public void startMpGetCartPos(int ctrlGroup) throws IOException {
            final int inputSize = 16;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_CURRENT_CART_POS.getId()); // type of function remote server will call
            bb.putInt(12, ctrlGroup);
            writeDataOutputStream(bb);
        }

        public void startMpGetPulsePos(int ctrlGroup, MP_PULSE_POS_RSP_DATA[] data) throws IOException {
            final int inputSize = 16;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_CURRENT_PULSE_POS.getId()); // type of function remote server will call
            bb.putInt(12, ctrlGroup);
            writeDataOutputStream(bb);
        }

        public void startMpGetFBPulsePos(int ctrlGroup, MP_FB_PULSE_POS_RSP_DATA[] data) throws IOException {
            final int inputSize = 16;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_CURRENT_FEEDBACK_PULSE_POS.getId()); // type of function remote server will call
            bb.putInt(12, ctrlGroup);
            writeDataOutputStream(bb);
        }

        public void startMpGetDegPosEx(int ctrlGroup, MP_DEG_POS_RSP_DATA_EX[] data) throws IOException {
            final int inputSize = 16;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_DEG_POS_EX.getId()); // type of function remote server will call
            bb.putInt(12, ctrlGroup);
            writeDataOutputStream(bb);
        }

        public void startMpGetServoPower() throws IOException {
            final int inputSize = 12;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_SERVO_POWER.getId()); // type of function remote server will call
            writeDataOutputStream(bb);
        }

        public void startMpSetServoPower(boolean on) throws IOException {
            final int inputSize = 14;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_SET_SERVO_POWER.getId()); // type of function remote server will call
            bb.putShort(12, (short) (on ? 1 : 0));
            writeDataOutputStream(bb);
        }

        public void startMpGetMode() throws IOException {
            final int inputSize = 12;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_MODE.getId()); // type of function remote server will call
            writeDataOutputStream(bb);
        }

        public void startMpGetCycle() throws IOException {
            final int inputSize = 12;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_CYCLE.getId()); // type of function remote server will call
            writeDataOutputStream(bb);
        }

        public void startMpGetAlarmStatus() throws IOException {
            final int inputSize = 12;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_ALARM_STATUS.getId()); // type of function remote server will call
            writeDataOutputStream(bb);
        }

        public void startMpGetAlarmCode() throws IOException {
            final int inputSize = 12;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_ALARM_CODE.getId()); // type of function remote server will call
            writeDataOutputStream(bb);
        }

        public void startMpGetRtc() throws IOException {
            final int inputSize = 12;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.SYS1_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteSys1FunctionType.SYS1_GET_RTC.getId()); // type of function remote server will call
            writeDataOutputStream(bb);
        }

        /**
         * Sends a message to the data stream to tell the controller to reset
         * the 6-axis force sensor and start measurement.
         *
         * @param rob_id which robot to target
         * @param reset_time specifies the averaging time to calculate offset
         * value which must be between 10 and 1000 milliseconds.
         * @throws IOException if the write to the socket fails
         */
        public void startMpFcsStartMeasuring(MP_FCS_ROB_ID rob_id, int reset_time) throws IOException {
            final int inputSize = 20;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.FORCE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteForceControlFunctionType.FORCE_CONTROL_START_MEASURING.getId()); // type of function remote server will call
            bb.putInt(12, rob_id.getId()); // robot ID
            if (reset_time < 10 || reset_time > 1000) {
                throw new IllegalArgumentException("reset_time must be between 10 and 1000 not " + reset_time);
            }
            bb.putInt(16, reset_time); // averaging time
            writeDataOutputStream(bb);
        }

        public void startMpFcsGetForceData(MP_FCS_ROB_ID rob_id, FCS_COORD_TYPE coord_type, int uf_no) throws IOException {
            final int inputSize = 24;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.FORCE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteForceControlFunctionType.FORCE_CONTROL_GET_FORCE_DATA.getId()); // type of function remote server will call
            bb.putInt(12, rob_id.getId()); // robot ID
            bb.putInt(16, coord_type.ordinal()); // averaging time
            bb.putInt(20, uf_no); // averaging time
            writeDataOutputStream(bb);
        }

        public void startMpFcsStartImp(MP_FCS_ROB_ID rob_id, int m[], int d[], int k[],
                int coord_type, int uf_no, int cart_axes, int option_ctrl) throws IOException, MotoPlusConnectionException {
            final int inputSize = 4 + 28 + 12 * MP_FCS_AXES_NUM;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.FORCE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteForceControlFunctionType.FORCE_CONTROL_START_IMP.getId()); // type of function remote server will call
            bb.putInt(12, rob_id.getId()); // robot ID
            if (m.length != MP_FCS_AXES_NUM) {
                throw new IllegalArgumentException("m.length must be " + MP_FCS_AXES_NUM + " : m.length=" + m.length);
            }
            if (d.length != MP_FCS_AXES_NUM) {
                throw new IllegalArgumentException("d.length must be " + MP_FCS_AXES_NUM + " : d.length=" + m.length);
            }
            if (k.length != MP_FCS_AXES_NUM) {
                throw new IllegalArgumentException("k.length must be " + MP_FCS_AXES_NUM + " : k.length=" + m.length);
            }
            if (coord_type < 0 || coord_type > 2) {
                throw new IllegalArgumentException("coord_type must be 0,1,or 2 : coord_type=" + coord_type);
            }
            if (uf_no < 0 || uf_no > 15) {
                throw new IllegalArgumentException("uf_no must be in 0 to 15 : uf_no=" + coord_type);
            }
            for (int i = 0; i < m.length; i++) {
                bb.putInt(16 + (4 * i), m[i]);
            }

            for (int i = 0; i < d.length; i++) {
                bb.putInt(16 + 4 * MP_FCS_AXES_NUM + (4 * i), d[i]);
            }

            for (int i = 0; i < k.length; i++) {
                bb.putInt(16 + 8 * MP_FCS_AXES_NUM + (4 * i), k[i]);
            }
            bb.putInt(16 + 12 * MP_FCS_AXES_NUM, coord_type);
            bb.putInt(20 + 12 * MP_FCS_AXES_NUM, uf_no);
            bb.putInt(24 + 12 * MP_FCS_AXES_NUM, cart_axes);
            bb.putInt(28 + 12 * MP_FCS_AXES_NUM, option_ctrl);
            writeDataOutputStream(bb);
        }

        public void startMpFcsSetReferenceForce(MP_FCS_ROB_ID rob_id, int fref_data[]) throws IOException, MotoPlusConnectionException {
            final int inputSize = 16 + 4 * MP_FCS_AXES_NUM;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.FORCE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteForceControlFunctionType.FORCE_CONTROL_SET_REFERENCE_FORCE.getId()); // type of function remote server will call
            bb.putInt(12, rob_id.getId()); // robot ID
            if (fref_data.length != MP_FCS_AXES_NUM) {
                throw new IllegalArgumentException("fref_data.length=" + fref_data.length);
            }
            for (int i = 0; i < fref_data.length; i++) {
                bb.putInt(16 + (4 * i), fref_data[i]);
            }
            writeDataOutputStream(bb);
        }

        public void startMpFcsEndImp(MP_FCS_ROB_ID rob_id) throws IOException, MotoPlusConnectionException {
            final int inputSize = 16;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.FORCE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteForceControlFunctionType.FORCE_CONTROL_END_IMP.getId()); // type of function remote server will call
            bb.putInt(12, rob_id.getId()); // robot ID
            writeDataOutputStream(bb);
        }

        public void startMpFcsConvForceScale(MP_FCS_ROB_ID rob_id, int scale) throws IOException, MotoPlusConnectionException {
            final int inputSize = 20;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.FORCE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteForceControlFunctionType.FORCE_CONTROL_CONV_FORCE_SCALE.getId()); // type of function remote server will call
            bb.putInt(12, rob_id.getId()); // robot ID
            bb.putInt(16, scale); // robot ID
            writeDataOutputStream(bb);
        }

        @Deprecated
        /**
         * *
         * mpServoFcs.h includes a prototype for mpFcsGetSensorData but there is
         * no documentation for it in the MotoPlus PDF files. It is not clear
         * what it does but it may be redundant with mpFcsGetForceData() which
         * is documented.
         */
        public void startMpFcsGetSensorData(MP_FCS_ROB_ID rob_id) throws IOException, MotoPlusConnectionException {
            final int inputSize = 16;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.FORCE_CTRL_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteForceControlFunctionType.FORCE_CONTROL_GET_SENSOR_DATA.getId()); // type of function remote server will call
            bb.putInt(12, rob_id.getId()); // robot ID
            writeDataOutputStream(bb);
        }

        public void startMpConvAxesToCartPos(int grp_no, int angle[], int tool_no) throws IOException, MotoPlusConnectionException {
            if (angle.length != MP_GRP_AXES_NUM) {
                throw new RuntimeException("angle.length=" + angle.length + ", MP_GRP_AXES_NUM=" + MP_GRP_AXES_NUM);
            }
            if (grp_no < 0) {
                throw new IllegalArgumentException("grp_no=" + grp_no);
            }
            if (tool_no < 0) {
                throw new IllegalArgumentException("tool_no=" + tool_no);
            }
            final int inputSize = 20 + 4 * MP_GRP_AXES_NUM;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.KINEMATICS_CONVERSION_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteKinematicsConversionFunctionType.KINEMATICS_CONVERSION_CONVERT_AXES_TO_CART_POS.getId()); // type of function remote server will call
            bb.putInt(12, grp_no); // robot ID
            for (int i = 0; i < MP_GRP_AXES_NUM; i++) {
                bb.putInt(16 + 4 * i, angle[i]);
            }
            bb.putInt(16 + 4 * MP_GRP_AXES_NUM, tool_no);
            writeDataOutputStream(bb);
        }

        public void startMpConvCartPosToAxes(int grp_no, MP_COORD coord, int tool_no, int fig_ctrl, int prev_angle[], MP_KINEMA_TYPE kinema_type) throws IOException, MotoPlusConnectionException {
            if (prev_angle.length != MP_GRP_AXES_NUM) {
                throw new RuntimeException("prev_angle.length=" + prev_angle.length + ", MP_GRP_AXES_NUM=" + MP_GRP_AXES_NUM);
            }
            if (grp_no < 0) {
                throw new IllegalArgumentException("grp_no=" + grp_no);
            }
            if (tool_no < 0) {
                throw new IllegalArgumentException("tool_no=" + tool_no);
            }
            final int inputSize = 60 + 4 * MP_GRP_AXES_NUM;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.KINEMATICS_CONVERSION_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteKinematicsConversionFunctionType.KINEMATICS_CONVERSION_CONVERT_CART_POS_TO_AXES.getId()); // type of function remote server will call
            bb.putInt(12, grp_no);
            bb.putInt(16, coord.x);
            bb.putInt(20, coord.y);
            bb.putInt(24, coord.z);
            bb.putInt(28, coord.rx);
            bb.putInt(32, coord.ry);
            bb.putInt(36, coord.rz);
            bb.putInt(40, coord.ex1);
            bb.putInt(44, coord.ex2);
            bb.putInt(48, tool_no);
            bb.putInt(52, fig_ctrl);
            for (int i = 0; i < MP_GRP_AXES_NUM; i++) {
                bb.putInt(56 + 4 * i, prev_angle[i]);
            }
            bb.putInt(56 + 4 * MP_GRP_AXES_NUM, kinema_type.ordinal());
            writeDataOutputStream(bb);
        }

        public void startMpConvPulseToAngle(int grp_no, int pulse[]) throws IOException, MotoPlusConnectionException {
            if (pulse.length != MP_GRP_AXES_NUM) {
                throw new RuntimeException("prev_angle.length=" + pulse.length + ", MP_GRP_AXES_NUM=" + MP_GRP_AXES_NUM);
            }
            if (grp_no < 0) {
                throw new IllegalArgumentException("grp_no=" + grp_no);
            }
            final int inputSize = 16 + 4 * MP_GRP_AXES_NUM;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.KINEMATICS_CONVERSION_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteKinematicsConversionFunctionType.KINEMATICS_CONVERSION_CONVERT_PULSE_TO_ANGLE.getId()); // type of function remote server will call
            bb.putInt(12, grp_no);
            for (int i = 0; i < MP_GRP_AXES_NUM; i++) {
                bb.putInt(16 + 4 * i, pulse[i]);
            }
            writeDataOutputStream(bb);
        }

        public void startMpConvAngleToPulse(int grp_no, int angle[]) throws IOException, MotoPlusConnectionException {
            if (angle.length != MP_GRP_AXES_NUM) {
                throw new RuntimeException("angle.length=" + angle.length + ", MP_GRP_AXES_NUM=" + MP_GRP_AXES_NUM);
            }
            if (grp_no < 0) {
                throw new IllegalArgumentException("grp_no=" + grp_no);
            }
            final int inputSize = 16 + 4 * MP_GRP_AXES_NUM;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.KINEMATICS_CONVERSION_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteKinematicsConversionFunctionType.KINEMATICS_CONVERSION_CONVERT_ANGLE_TO_PULSE.getId()); // type of function remote server will call
            bb.putInt(12, grp_no);
            for (int i = 0; i < MP_GRP_AXES_NUM; i++) {
                bb.putInt(16 + 4 * i, angle[i]);
            }
            writeDataOutputStream(bb);
        }

        public void startMpConvFBPulseToPulse(int grp_no, int fbpulse[]) throws IOException, MotoPlusConnectionException {
            if (fbpulse.length != MP_GRP_AXES_NUM) {
                throw new RuntimeException("fbpulse.length=" + fbpulse.length + ", MP_GRP_AXES_NUM=" + MP_GRP_AXES_NUM);
            }
            if (grp_no < 0) {
                throw new IllegalArgumentException("grp_no=" + grp_no);
            }
            final int inputSize = 16 + 4 * MP_GRP_AXES_NUM;
            ByteBuffer bb = ByteBuffer.allocate(inputSize);
            bb.putInt(0, inputSize - 4); // bytes to read
            bb.putInt(4, RemoteFunctionGroup.KINEMATICS_CONVERSION_FUNCTION_GROUP.getId()); // type of function remote server will call
            bb.putInt(8, RemoteKinematicsConversionFunctionType.KINEMATICS_CONVERSION_CONVERT_FB_PULSE_TO_PULSE.getId()); // type of function remote server will call
            bb.putInt(12, grp_no);
            for (int i = 0; i < MP_GRP_AXES_NUM; i++) {
                bb.putInt(16 + 4 * i, fbpulse[i]);
            }
            writeDataOutputStream(bb);
        }

    }
    private static final int MP_FCS_AXES_NUM = 6;

    public final class Returner {

        private Returner() {
            // only one needed per MotoPlusConnection instance
        }

        public int getMpFdReadFileReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            return intRet;
        }

        public int getMpFdWriteFileReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            return intRet;
        }

        public int getMpSaveFileReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            return intRet;
        }

        public int getMpLoadFileReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            return intRet;
        }

        public int getMpOpenFileReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet <= 0) {
                throw new MotoPlusConnectionException("mpOpen returned " + intRet);
            }
            return intRet;
        }

        public int getMpFdGetJobListReturn(MP_GET_JOBLIST_RSP_DATA jListData) throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet != 0) {
                throw new MotoPlusConnectionException("mpFdGetJobList returned " + intRet);
            }
            if (size < 10) {
                throw new MotoPlusConnectionException("mpFdGetJobList size = " + size);
            }
            jListData.err_no = bb.getShort(4);
            jListData.uIsEndFlag = bb.getShort(6);
            jListData.uListDataNum = bb.getShort(8);
            return intRet;
        }

        public int getMpCreateFileReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet <= 0) {
                throw new MotoPlusConnectionException("mpCreate returned " + intRet);
            }
            return intRet;
        }

        public int getMpCloseFileReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet != 0) {
                throw new MotoPlusConnectionException("mpClose returned " + intRet);
            }
            return intRet;
        }

        public int getMpReadFileReturn(byte buf[], int offset, int len) throws IOException, MotoPlusConnectionException {
            if (null == buf || buf.length < offset + len) {
                throw new IllegalArgumentException("Buf must not be null and have length of atleast offset = " + offset + " len = " + len);
            }
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet < 0) {
                throw new MotoPlusConnectionException("mpRead returned " + intRet);
            }
            if (size <= 3) {
                throw new MotoPlusConnectionException("mpRead size = " + size);
            }
            bb.position(4);
            bb.get(buf, offset, Math.min(intRet, Math.min(len, size - 4)));
            return intRet;
        }

        public int getMpWriteFileReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            return intRet;
        }

        public int getMpFileCountReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet != 0) {
                throw new MotoPlusConnectionException("mpRefreshFileList returned " + intRet);
            }
            if (size < 8) {
                throw new MotoPlusConnectionException("mpGetFileCount size = " + size + ": 8 bytes needed");
            }
            return bb.getInt(4);
        }

        public String getMpFileNameReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int size = bb.getInt(0);
            inbuf = new byte[size];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet != 0) {
                throw new MotoPlusConnectionException("mpRefreshFileList returned " + intRet);
            }
            if (size < 10) {
                throw new MotoPlusConnectionException("mpGetFileCount size = " + size + ": 10 bytes needed");
            }
            intRet = bb.getInt(4);
            if (intRet != 0) {
                throw new MotoPlusConnectionException("mpGetFileName returned " + intRet);
            }
            return new String(inbuf, 8, size - 9, Charset.forName("US-ASCII"));
        }

        public MotCtrlReturnEnum getMpMotStandardReturn() throws IOException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            if (sz < 4 || sz > 8192) {
                throw new RuntimeException("sz=" + sz);
            }
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            return MotCtrlReturnEnum.fromId(intRet);
        }

        public MotCtrlReturnEnum getMpMotTargetReceiveReturn(int[] recvId) throws IOException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (sz >= 8 && null != recvId && recvId.length > 0) {
                recvId[0] = bb.getInt(4);
            }
            return MotCtrlReturnEnum.fromId(intRet);
        }

        public boolean getSysOkReturn() throws IOException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            return intRet == 0;
        }

        public int getIntReturn() throws IOException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            return intRet;
        }

        public boolean getSysDataReturn(int rData[]) throws IOException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            for (int i = 0; i < rData.length && i < (sz - 4) / 4; i++) {
                rData[i] = bb.getInt(4 + (i * 4));
            }
            return intRet == 0;
        }

        public boolean getSysSVarInfoReturn(MP_SVAR_RECV_INFO rData[]) throws IOException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            for (int i = 0; i < rData.length && i < (sz - 4) / 4; i++) {
//                bb.get(rData[i].ucValue, MAX_SVAR_SIZE);
                bb.get(rData[i].ucValue, 0, MAX_SVAR_SIZE);
            }
            return intRet == 0;
        }

        public boolean getSysReadIOReturn(short iorData[]) throws IOException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            for (int i = 0; i < iorData.length && i < (sz - 4) / 2; i++) {
                iorData[i] = bb.getShort(4 + (i * 2));
            }
            return intRet == 0;
        }

        public boolean getCartPosReturn(MP_CART_POS_RSP_DATA[] data) throws IOException {
            if (data.length != 1) {
                throw new IllegalArgumentException("data.length = " + data.length);
            }
            if (data[0] == null) {
                data[0] = new MP_CART_POS_RSP_DATA();
            }
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            for (int i = 0; i < MP_CART_POS_RSP_DATA.MAX_CART_AXES; i++) {
                data[0].lPos[i] = bb.getInt(4 + (i * 4));
            }
            data[0].sConfig = bb.getShort(52);
            return intRet == 0;
        }

        public boolean getPulsePosReturn(MP_PULSE_POS_RSP_DATA[] data) throws IOException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            for (int i = 0; i < MP_PULSE_POS_RSP_DATA.MAX_PULSE_AXES; i++) {
                data[0].lPos[i] = bb.getInt(4 + (i * 4));
            }
            return intRet == 0;
        }

        public boolean getFBPulsePosReturn(MP_FB_PULSE_POS_RSP_DATA[] data) throws IOException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            for (int i = 0; i < MP_PULSE_POS_RSP_DATA.MAX_PULSE_AXES; i++) {
                data[0].lPos[i] = bb.getInt(4 + (i * 4));
            }
            return intRet == 0;
        }

        public boolean getDegPosExPosReturn(MP_DEG_POS_RSP_DATA_EX[] data) throws IOException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            for (int i = 0; i < MP_DEG_POS_RSP_DATA_EX.MAX_PULSE_AXES; i++) {
                data[0].degPos[i] = bb.getInt(4 + (i * 4));
            }
            for (int i = 0; i < MP_DEG_POS_RSP_DATA_EX.MAX_PULSE_AXES; i++) {
                int unitInt = bb.getInt(68 + (i * 4));
                data[0].degUnit[i] = UnitType.fromId(unitInt);
            }
            return intRet == 0;
        }

        public boolean getServoPowerReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            short shortRet = bb.getShort(4);
            if (intRet != 0) {
                throw new MotoPlusConnectionException("mpGetServoPower returned " + intRet);
            }
            if (shortRet < 0 || shortRet > 1) {
                throw new MotoPlusConnectionException("mpGetServoPower had invalid value for sServoPower =  " + shortRet);
            }
            return shortRet == 1;
        }

        public boolean getSetServoPowerReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            short shortRet = bb.getShort(4);
            if (intRet != 0) {
                throw new MotoPlusConnectionException("mpSetServoPower returned " + intRet);
            }
            return shortRet == 1;
        }

        public MP_MODE_DATA getModeReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet != 0) {
                throw new MotoPlusConnectionException("mpGetMode returned " + intRet);
            }
            short sMode = bb.getShort(4);
            MP_MODE_DATA data = new MP_MODE_DATA();
            data.mode = ModeEnum.fromId(sMode);
            data.sRemote = bb.getShort(6);
            return data;
        }

        public MP_CYCLE_DATA getCycleReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet != 0) {
                throw new MotoPlusConnectionException("mpGetCycle returned " + intRet);
            }
            MP_CYCLE_DATA data = new MP_CYCLE_DATA();
            data.cycle = CycleEnum.fromId(bb.getShort(4));
            return data;
        }

        public MP_ALARM_STATUS_DATA getAlarmStatusReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet != 0) {
                throw new MotoPlusConnectionException("mpGetAlarmStatus returned " + intRet);
            }
            MP_ALARM_STATUS_DATA data = new MP_ALARM_STATUS_DATA();
            data.sIsAlarm = bb.getShort(4);
            if (data.sIsAlarm != 0) {
                alarmCount.incrementAndGet();
            }
            return data;
        }

        public MP_ALARM_CODE_DATA getAlarmCodeReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            int intRet = bb.getInt(0);
            if (intRet != 0) {
                throw new MotoPlusConnectionException("mpGetAlarmCode returned " + intRet);
            }
            MP_ALARM_CODE_DATA data = new MP_ALARM_CODE_DATA();
            data.usErrorNo = bb.getShort(4);
            data.usErrorData = bb.getShort(6);
            data.usAlarmNum = bb.getShort(8);
            for (int i = 0; i < data.usAlarmNum && i < 4; i++) {
                data.AlarmData.usAlarmNo[i] = bb.getShort(10 + i * 4);
                data.AlarmData.usAlarmData[i] = bb.getShort(12 + i * 4);
            }
            return data;
        }

        public MpFcsStartMeasuringReturn getMpFcsStartMeasuringReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            MpFcsStartMeasuringReturn returnVal = new MpFcsStartMeasuringReturn();

            returnVal.returnInt = bb.getInt(0);
            returnVal.returnCode = FcsReturnCode.fromInt(returnVal.returnInt);
            for (int i = 0; i < returnVal.offset_data.length; i++) {
                returnVal.offset_data[i] = bb.getInt(4 + i * 4);
            }
            return returnVal;
        }

        public MpFcsGetForceDataReturn getMpFcsGetForceDataReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            if (sz != 28) {
                throw new RuntimeException("return size of " + sz + " does not match required size of 28.");
            }
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            MpFcsGetForceDataReturn returnVal = new MpFcsGetForceDataReturn();
            returnVal.returnInt = bb.getInt(0);
            returnVal.returnCode = FcsReturnCode.fromInt(returnVal.returnInt);
            returnVal.fx = bb.getInt(4);
            returnVal.fy = bb.getInt(8);
            returnVal.fz = bb.getInt(12);
            returnVal.mx = bb.getInt(16);
            returnVal.my = bb.getInt(20);
            returnVal.mz = bb.getInt(24);
            return returnVal;
        }

        public MpFcsGetSensorDataReturn getMpFcsGetSensorDataReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            MpFcsGetSensorDataReturn returnVal = new MpFcsGetSensorDataReturn();

            returnVal.returnInt = bb.getInt(0);
            returnVal.returnCode = FcsReturnCode.fromInt(returnVal.returnInt);
            returnVal.sens_data[0] = bb.getInt(4);
            returnVal.sens_data[1] = bb.getInt(8);
            returnVal.sens_data[2] = bb.getInt(12);
            returnVal.sens_data[3] = bb.getInt(16);
            returnVal.sens_data[4] = bb.getInt(20);
            returnVal.sens_data[5] = bb.getInt(24);
            return returnVal;
        }

        public MpFcsBaseReturn getMpFcsBaseReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            MpFcsBaseReturn returnVal = new MpFcsBaseReturn();

            returnVal.returnInt = bb.getInt(0);
            returnVal.returnCode = FcsReturnCode.fromInt(returnVal.returnInt);
            return returnVal;
        }

        public MpKinCartPosReturn getMpCartPosReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            MpKinCartPosReturn returnVal = new MpKinCartPosReturn();

            returnVal.returnInt = bb.getInt(0);
            returnVal.returnCode = KinReturnCode.fromInt(returnVal.returnInt);
            returnVal.fig_ctrl = bb.getInt(4);
            returnVal.coord.x = bb.getInt(8);
            returnVal.coord.y = bb.getInt(12);
            returnVal.coord.z = bb.getInt(16);
            returnVal.coord.rx = bb.getInt(20);
            returnVal.coord.ry = bb.getInt(24);
            returnVal.coord.rz = bb.getInt(28);
            returnVal.coord.ex1 = bb.getInt(32);
            returnVal.coord.ex2 = bb.getInt(36);
            return returnVal;
        }

        public MpKinAngleReturn getMpAngleReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            MpKinAngleReturn returnVal = new MpKinAngleReturn();

            returnVal.returnInt = bb.getInt(0);
            returnVal.returnCode = KinReturnCode.fromInt(returnVal.returnInt);
            for (int i = 0; i < returnVal.angle.length; i++) {
                returnVal.angle[i] = bb.getInt(4 + 4 * i);
            }
            return returnVal;
        }

        public MpKinPulseReturn getMpPulseReturn() throws IOException, MotoPlusConnectionException {
            byte inbuf[] = new byte[4];
            readDataInputStream(inbuf);
            ByteBuffer bb = ByteBuffer.wrap(inbuf);
            int sz = bb.getInt(0);
            inbuf = new byte[sz];
            readDataInputStream(inbuf);
            bb = ByteBuffer.wrap(inbuf);
            MpKinPulseReturn returnVal = new MpKinPulseReturn();

            returnVal.returnInt = bb.getInt(0);
            returnVal.returnCode = KinReturnCode.fromInt(returnVal.returnInt);
            for (int i = 0; i < returnVal.pulse.length; i++) {
                returnVal.pulse[i] = bb.getInt(4 + 4 * i);
            }
            return returnVal;
        }

    };

    private final Starter starter = new Starter();
    private final Returner returner = new Returner();

    public Starter getStarter() {
        return starter;
    }

    public Returner getReturner() {
        return returner;
    }

    public MotCtrlReturnEnum mpMotStart(int options) throws IOException {
        starter.startMpMotStart(options);
        return returner.getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotStop(int options) throws IOException {
        starter.startMpMotStop(options);
        return returner.getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotTargetClear(int grp, int options) throws IOException {
        starter.startMpMotTargetClear(grp, options);
        return returner.getMpMotStandardReturn();
    }

    private void checkTimeout(int timeout) {
        if (timeout != NO_WAIT) {
            if (timeout < 0 || timeout > 5000) {
                throw new IllegalArgumentException("timeout must be finite and less than 5000 ms: timeout = " + timeout);
            }
        }
    }

    public MotCtrlReturnEnum mpMotTargetJointSend(int grp, JointTarget target, int timeout) throws IOException {
        checkTimeout(timeout);
        starter.startMpMotTargetJointSend(grp, target, timeout);
        return returner.getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotTargetCoordSend(int grp, CoordTarget target, int timeout) throws IOException {
        checkTimeout(timeout);
        starter.startMpMotTargetCoordSend(grp, target, timeout);
        return returner.getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotTargetReceive(int grpNo, int id, int[] recvId, int timeout, int options) throws IOException {
        checkTimeout(timeout);
        starter.startMpMotTargetReceive(grpNo, id, timeout, options);
        return returner.getMpMotTargetReceiveReturn(recvId);
    }

    private volatile int lastMpMotSetCoordGrpNo = -99;
    private volatile int lastMpMotSetCoordAux = -99;
    private volatile MP_COORD_TYPE lastMpMotSetCoordType;
    private volatile long lastMpMotSetCoordTime;
    private volatile int lastMpMotSetCoordAlarmCount = -1;

    public boolean checkNeedMotSetCoord(int grpNo, MP_COORD_TYPE type, int aux, long maxTimeDiff) {
        long timeDiff = System.currentTimeMillis() - lastMpMotSetCoordTime;
        return grpNo == lastMpMotSetCoordGrpNo
                && type == lastMpMotSetCoordType
                && aux == lastMpMotSetCoordAux
                && lastMpMotSetCoordAlarmCount == alarmCount.get()
                && timeDiff < maxTimeDiff;
    }

    public MotCtrlReturnEnum mpMotSetCoord(int grpNo, MP_COORD_TYPE type, int aux) throws IOException {
        starter.startMpMotSetCoord(grpNo, type, aux);
        lastMpMotSetCoordGrpNo = grpNo;
        lastMpMotSetCoordAux = aux;
        lastMpMotSetCoordType = type;
        lastMpMotSetCoordTime = System.currentTimeMillis();
        lastMpMotSetCoordAlarmCount = alarmCount.get();
        return returner.getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotSetTool(int grpNo, int toolNo) throws IOException {
        starter.startMpMotSetTool(grpNo, toolNo);
        return returner.getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotSetSpeed(int grpNo, MP_SPEED spd) throws IOException {
        starter.startMpMotSetSpeed(grpNo, spd);
        return returner.getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotSetOrigin(int grpNo, int options) throws IOException {
        starter.startMpMotSetOrigin(grpNo, options);
        return returner.getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotSetTask(int grpNo, int taskNo) throws IOException {
        starter.startMpMotSetTask(grpNo, taskNo);
        return returner.getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotSetSync(int grpNo, int aux, int options) throws IOException {
        starter.startMpMotSetSync(grpNo, aux, options);
        return returner.getMpMotStandardReturn();
    }

    public MotCtrlReturnEnum mpMotResetSync(int grpNo) throws IOException {
        starter.startMpMotSetSync(grpNo);
        return returner.getMpMotStandardReturn();
    }

    /**
     * To set multiple variables, define num arrays, set the variable type,
     * variable index, and variable value of each array, then set the starting
     * address of each array to sData
     *
     * @param sData Array of the data structure which specifies variables and
     * values
     * @param num Number of the variable data (up to 24) [ C function allows up
     * to 126 but tcp server limits to 24]
     * @return
     * @throws IOException
     */
    public boolean mpPutVarData(MP_VAR_DATA[] sData, int num) throws IOException {
        if (num < 1) {
            throw new IllegalArgumentException("num < 1 : num=" + num);
        }
        if (num > 24) {
            throw new IllegalArgumentException("num > 24 : num=" + num);
        }
        if (num > sData.length) {
            throw new IllegalArgumentException("num > sData.length: num=" + num + ", sData.length=" + sData.length);
        }
        for (int i = 0; i < num; i++) {
            if (null == sData[i]) {
                throw new NullPointerException("sData[" + i + "]");
            }
        }
        starter.startMpPutVarData(sData, num);
        return returner.getSysOkReturn();
    }

    public boolean mpPutSVarInfo(MP_SVAR_SEND_INFO[] sData, int num) throws IOException {
        starter.startMpPutSVarInfo(sData, num);
        return returner.getSysOkReturn();
    }

    public boolean mpWriteIO(MP_IO_DATA[] sData, int num) throws IOException {
        ioClear = false;
        starter.startMpWriteIO(sData, num);
        return returner.getSysOkReturn();
    }

    /**
     * Retrieves variables B, I, D, R (byte, integer, double precision, real).
     * Specifies multiple variables.
     *
     * @param sData Array of the data structure which specifies variable
     * @param rData variable data
     * @param num Number of variable data (up to 24) [C function allows up to
     * 252 but tcp server limits to 24]
     * @return
     * @throws IOException
     */
    public boolean mpGetVarData(MP_VAR_INFO[] sData, int[] rData, int num) throws IOException {
        if (num < 1) {
            throw new IllegalArgumentException("num < 1 : num=" + num);
        }
        if (num > 24) {
            throw new IllegalArgumentException("num > 24 : num=" + num);
        }
        if (num > sData.length) {
            throw new IllegalArgumentException("num > sData.length: num=" + num + ", sData.length=" + sData.length);
        }
        for (int i = 0; i < num; i++) {
            if (null == sData[i]) {
                throw new NullPointerException("sData[" + i + "]");
            }
        }
        if (num > rData.length) {
            throw new IllegalArgumentException("num > rData.length: num=" + num + ", rData.length=" + rData.length);
        }
        starter.startMpGetVarData(sData, num);
        return returner.getSysDataReturn(rData);
    }

    public boolean mpGetSVarInfo(MP_VAR_INFO[] sData, int[] rData, int num) throws IOException {
        starter.startMpGetSVarInfo(sData, num);
        return returner.getSysDataReturn(rData);
    }

    public boolean mpReadIO(MP_IO_INFO[] sData, short[] iorData, int num) throws IOException {
        starter.startMpReadIO(sData, iorData, num);
        return returner.getSysReadIOReturn(iorData);
    }

    private volatile MP_CART_POS_RSP_DATA cachedGetPosData = null;
    private volatile int lastGetCartPosGrp = -1;
    private volatile long lastGetCartPosTime = -1;

    public MP_CART_POS_RSP_DATA cachedGetCartPos(int grp, int time) throws MotoPlusConnectionException, IOException {
        if (grp != lastGetCartPosGrp || null == cachedGetPosData) {
            return getCartPos(grp);
        }
        long now = System.currentTimeMillis();
        if (now - lastGetCartPosTime > time) {
            return getCartPos(grp);
        }
        return cachedGetPosData;
    }

    public MP_CART_POS_RSP_DATA getCartPos(int grp) throws MotoPlusConnectionException, IOException {
        MP_CART_POS_RSP_DATA cartData[] = new MP_CART_POS_RSP_DATA[1];
        cartData[0] = new MP_CART_POS_RSP_DATA();
        if (!mpGetCartPos(grp, cartData)) {
            throw new MotoPlusConnectionException("mpGetCartPos returned false");
        }
        MP_CART_POS_RSP_DATA pos = cartData[0];
        lastGetCartPosGrp = grp;
        cachedGetPosData = pos;
        lastGetCartPosTime = System.currentTimeMillis();
        return pos;
    }

    public boolean mpGetCartPos(int ctrlGroup, MP_CART_POS_RSP_DATA[] data) throws IOException {
        starter.startMpGetCartPos(ctrlGroup);
        return returner.getCartPosReturn(data);
    }

    private AtomicInteger statusCount = new AtomicInteger();

    public MpcStatus readMpcStatusOnly(
            CommandStateEnumType localOrigCommandState,
            boolean withJoints,
            boolean getAlarmStatus,
            int lastSentId,
            int lastRecvdTargetId) throws MotoPlusConnectionException, IOException {
        if (!this.isConnected()) {
            throw new MotoPlusConnectionException("Not connected");
        }
//        int lastSentId = lastSentTargetId.get();

        MpcStatus mpcStatus = readMpcStatus(localOrigCommandState, lastSentId, statusCount.incrementAndGet(), withJoints, getAlarmStatus, lastRecvdTargetId);
        return mpcStatus;
    }

    private final AtomicInteger alarmCount = new AtomicInteger();

    private volatile long maxReadMpcStatusTime = -1;
    private volatile long maxReadMpcStatusTimeDiffArray[] = null;
    private volatile long maxReadMpcStatusTimeAfterMove = -1;
    private volatile long maxReadMpcStatusTimeDiffArrayAfterMove[] = null;
    private volatile boolean afterMove = false;
    private volatile COORD_POS lastCoordTargetDest;

    public long[] getMaxReadMpcStatusTimeDiffArray() {
        return maxReadMpcStatusTimeDiffArray;
    }

    public long[] getMaxReadMpcStatusTimeDiffArrayAfterMove() {
        return maxReadMpcStatusTimeDiffArrayAfterMove;
    }

    private boolean lastErrorWasWrongMode = false;

    public boolean isLastErrorWasWrongMode() {
        return lastErrorWasWrongMode;
    }

    public MpcStatus readMpcStatus(
            CommandStateEnumType localOrigCommandState,
            int lastSentId,
            int statusCount,
            boolean withJoints,
            boolean withAlarmModeStatus,
            int lastRecvdTargetId)
            throws MotoPlusConnectionException, IOException {
        Starter mpcStarter = this.getStarter();
        int ctrlGroup = 0;
        MP_CART_POS_RSP_DATA cartData[] = new MP_CART_POS_RSP_DATA[1];
        cartData[0] = new MP_CART_POS_RSP_DATA();
        MP_CART_POS_RSP_DATA pos = cartData[0];
        long t[] = new long[11];
        long t0 = System.currentTimeMillis();
        mpcStarter.startMpGetCartPos(ctrlGroup);
        t[0] = System.currentTimeMillis() - t0;
        MP_PULSE_POS_RSP_DATA pulseData[] = new MP_PULSE_POS_RSP_DATA[1];
        pulseData[0] = new MP_PULSE_POS_RSP_DATA();
        if (withJoints) {
            mpcStarter.startMpGetPulsePos(ctrlGroup, pulseData);
            t[1] = System.currentTimeMillis() - t0;
        } else {
            t[1] = -1;
        }

        if (withAlarmModeStatus) {
            starter.startMpGetMode();
            t[2] = System.currentTimeMillis() - t0;
        } else {
            t[2] = -2;
        }
        boolean lastPosCloseToTarget = true;
        final MP_CART_POS_RSP_DATA localCachedGetPosData = cachedGetPosData;
        final COORD_POS localLastCoordTargetDest = this.lastCoordTargetDest;
        double targetPosDiff = 0;
        double targetRotMaxDiff = 0;
        if (null != localLastCoordTargetDest && null != localCachedGetPosData) {
            targetPosDiff = computeTargetPosDiff(localLastCoordTargetDest, localCachedGetPosData);
            targetRotMaxDiff = computeTargetRotMaxDiff(localLastCoordTargetDest, localCachedGetPosData);
            if (targetPosDiff > 250 || targetRotMaxDiff > 1000) {
                lastPosCloseToTarget = false;
            }
        }
        final double initTargetPosDiff = targetPosDiff;
        final double initTargetRotMaxDiff = targetRotMaxDiff;
        final boolean doMpMotTargetRecieve
                = localOrigCommandState == CRCL_WORKING
                && lastSentId != lastRecvdTargetId
                && lastPosCloseToTarget;
        int recvId[] = new int[1];
        recvId[0] = lastRecvdTargetId;
        if (doMpMotTargetRecieve) {
            starter.startMpMotTargetReceive(0, lastSentId, 0, MotoPlusConnection.NO_WAIT);
            t[3] = System.currentTimeMillis() - t0;
        } else {
            t[3] = -3;
        }
        if (withAlarmModeStatus) {
            starter.startMpGetAlarmStatus();
            t[4] = System.currentTimeMillis() - t0;
        } else {
            t[4] = -4;
        }

        Returner mpcReturner = this.getReturner();
        boolean getCartPosRet = mpcReturner.getCartPosReturn(cartData);
        t[5] = System.currentTimeMillis() - t0;

        if (!getCartPosRet) {
            throw new MotoPlusConnectionException("mpGetCartPos returned false");
        }

        final MP_CART_POS_RSP_DATA localNewCachedGetPosData = cartData[0];
        cachedGetPosData = localNewCachedGetPosData;
        if (null != localLastCoordTargetDest && null != localNewCachedGetPosData) {
            targetPosDiff = computeTargetPosDiff(localLastCoordTargetDest, localNewCachedGetPosData);
            targetRotMaxDiff = computeTargetRotMaxDiff(localLastCoordTargetDest, localNewCachedGetPosData);
        }
        if (withJoints) {
            boolean getPulsePosRet = mpcReturner.getPulsePosReturn(pulseData);
            if (!getPulsePosRet) {
                throw new MotoPlusConnectionException("mpGetPulsePos returned false");
            }
            t[6] = System.currentTimeMillis() - t0;
        } else {
            t[6] = -6;
        }

        MP_MODE_DATA modeData = null;
        if (withAlarmModeStatus) {
            modeData = mpcReturner.getModeReturn();
            t[7] = System.currentTimeMillis() - t0;
        } else {
            t[7] = -7;
        }

        MotCtrlReturnEnum motTargetReceiveRet = null;
        if (doMpMotTargetRecieve) {
//                        System.out.println("lastSentTargetId = " + lastSentTargetId);
//                        System.out.println("lastRecvdTargetId = " + lastRecvdTargetId);
            motTargetReceiveRet = returner.getMpMotTargetReceiveReturn(recvId);
            if (motTargetReceiveRet == MotCtrlReturnEnum.SUCCESS) {
                lastRecvdTargetId = recvId[0];
            }
            t[8] = System.currentTimeMillis() - t0;
        } else {
            t[8] = -8;
        }

        if (localOrigCommandState != CRCL_ERROR) {
            lastErrorWasWrongMode = false;
        }
        if (null != modeData) {
            boolean wrongMode = (modeData.sRemote == 0);
            if (wrongMode) {
                lastErrorWasWrongMode = true;
            }
        }
        MP_ALARM_STATUS_DATA alarmStatusData = null;
        MP_ALARM_CODE_DATA alarmCodeData = null;
        if (withAlarmModeStatus) {
            alarmStatusData = returner.getAlarmStatusReturn();
            t[9] = System.currentTimeMillis() - t0;
//            System.out.println("alarmStatusData.sIsAlarm = " + alarmStatusData.sIsAlarm);
            if (alarmStatusData.sIsAlarm != 0) {
                alarmCodeData = this.mpGetAlarmCode();
            }
        } else {
            t[9] = -9;
        }
        t[10] = System.currentTimeMillis() - t0;
        int a[] = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        if (afterMove) {
            if (t[10] > maxReadMpcStatusTimeAfterMove) {
                maxReadMpcStatusTimeDiffArrayAfterMove = t;
            }
            afterMove = false;
        } else {
            if (t[10] > maxReadMpcStatusTime) {
                maxReadMpcStatusTimeDiffArray = t;
            }
        }

//        System.out.println("a = " + Arrays.toString(a));
//        System.out.println("t = " + Arrays.toString(t));
        MpcStatus mpcStatus
                = new MpcStatus(
                        pos,
                        withJoints ? pulseData[0] : null,
                        motTargetReceiveRet,
                        modeData,
                        withAlarmModeStatus ? alarmCodeData : null,
                        withAlarmModeStatus ? alarmStatusData : null,
                        recvId[0],
                        statusCount,
                        targetPosDiff,
                        targetRotMaxDiff);
        return mpcStatus;
    }

    private double computeTargetPosDiff(final COORD_POS localLastCoordTargetDest, final MP_CART_POS_RSP_DATA localCachedGetPosData) {
        double targetPosDiff;
        double distx = (double) (localLastCoordTargetDest.x - localCachedGetPosData.lx());
        double disty = (double) (localLastCoordTargetDest.y - localCachedGetPosData.ly());
        double distz = (double) (localLastCoordTargetDest.z - localCachedGetPosData.lz());
        targetPosDiff = Math.sqrt(distx * distx + disty * disty + distz * distz);
        return targetPosDiff;
    }

    private double computeTargetRotMaxDiff(final COORD_POS localLastCoordTargetDest, final MP_CART_POS_RSP_DATA localCachedGetPosData) {
        double targetRotMaxDiff;
        double distrx = (double) (localLastCoordTargetDest.rx - localCachedGetPosData.lrx());
        double distry = (double) (localLastCoordTargetDest.ry - localCachedGetPosData.lry());
        double distrz = (double) (localLastCoordTargetDest.rz - localCachedGetPosData.lrz());
        targetRotMaxDiff = Math.max(Math.abs(distrz), Math.max(Math.abs(distry), Math.abs(distrz)));
        return targetRotMaxDiff;
    }

    public MP_PULSE_POS_RSP_DATA getPulsePos(int grp) throws MotoPlusConnectionException, IOException {
        MP_PULSE_POS_RSP_DATA pulseData[] = new MP_PULSE_POS_RSP_DATA[1];
        pulseData[0] = new MP_PULSE_POS_RSP_DATA();
        if (!mpGetPulsePos(grp, pulseData)) {
            throw new MotoPlusConnectionException("mpGetPulsePos returned false");
        }
        return pulseData[0];
    }

    private boolean mpGetPulsePos(int ctrlGroup, MP_PULSE_POS_RSP_DATA[] data) throws IOException {
        starter.startMpGetPulsePos(ctrlGroup, data);
        return returner.getPulsePosReturn(data);
    }

    private boolean mpGetFBPulsePos(int ctrlGroup, MP_FB_PULSE_POS_RSP_DATA[] data) throws IOException {
        starter.startMpGetFBPulsePos(ctrlGroup, data);
        return returner.getFBPulsePosReturn(data);
    }

    private boolean mpGetDegPosEx(int ctrlGroup, MP_DEG_POS_RSP_DATA_EX[] data) throws IOException {
        starter.startMpGetDegPosEx(ctrlGroup, data);
        return returner.getDegPosExPosReturn(data);
    }

    public static final class MotoPlusConnectionException extends Exception {

        public MotoPlusConnectionException(String message) {
            super(message);
        }

    }

    private volatile boolean lastGetPowerValue = false;
    private volatile long lastGetPowerValueTime = -1;
    private volatile int lastGetPowerValueAlarmCount = -1;

    public boolean mpGetServoPower() throws IOException, MotoPlusConnectionException {
        starter.startMpGetServoPower();
        boolean ret = returner.getServoPowerReturn();
        lastGetPowerValue = ret;
        lastGetPowerValueTime = System.currentTimeMillis();
        lastGetPowerValueAlarmCount = alarmCount.get();
        return ret;
    }

    private volatile boolean lastSetPowerValue = false;
    private volatile long lastSetPowerValueTime = -1;
    private volatile long lastSetPowerValueAlarmCount = -1;

    public boolean checkNeedSetPower(boolean on, long timeDiffMax) {
        long now = System.currentTimeMillis();
        long timeSetDiff = now - lastSetPowerValueTime;
        long timeGetDiff = now - lastGetPowerValueTime;
        int localAlarmCount = alarmCount.get();
        return on == lastSetPowerValue
                && on == lastGetPowerValue
                && localAlarmCount == lastSetPowerValueAlarmCount
                && localAlarmCount == lastGetPowerValueAlarmCount
                && ((timeSetDiff < timeSetDiff) || (timeGetDiff < timeDiffMax));
    }

    public boolean mpSetServoPower(boolean on) throws IOException, MotoPlusConnectionException {
        starter.startMpSetServoPower(on);
        lastSetPowerValue = on;
        lastSetPowerValueAlarmCount = alarmCount.get();
        lastSetPowerValueTime = System.currentTimeMillis();
        return returner.getSetServoPowerReturn();
    }

    public MP_MODE_DATA mpGetMode() throws IOException, MotoPlusConnectionException {
        starter.startMpGetMode();
        return returner.getModeReturn();
    }

    public MP_CYCLE_DATA mpGetCycle() throws IOException, MotoPlusConnectionException {
        starter.startMpGetCycle();
        return returner.getCycleReturn();
    }

    public MP_ALARM_STATUS_DATA mpGetAlarmStatus() throws IOException, MotoPlusConnectionException {
        starter.startMpGetAlarmStatus();
        return returner.getAlarmStatusReturn();
    }

    public MP_ALARM_CODE_DATA mpGetAlarmCode() throws IOException, MotoPlusConnectionException {
        starter.startMpGetAlarmCode();
        return returner.getAlarmCodeReturn();
    }

    /**
     * Reset the 6-axis force sensor and start measurement.
     *
     * @param rob_id which robot to target
     * @param reset_time specifies the averaging time to calculate offset value
     * which must be between 10 and 1000 milliseconds.
     * @return MpFcsStartMeasuringReturn object with offset_data
     * @throws java.io.IOException network error
     * @throws
     * com.github.wshackle.crcl4java.motoman.MotoPlusConnection.MotoPlusConnectionException
     * motoplus specific error
     *
     */
    public MpFcsStartMeasuringReturn mpFcsStartMeasuring(MP_FCS_ROB_ID rob_id, int reset_time) throws IOException, MotoPlusConnectionException {
        starter.startMpFcsStartMeasuring(rob_id, reset_time);
        return returner.getMpFcsStartMeasuringReturn();
    }

    /**
     * Retrieves the force data in the specified coordinate system.
     *
     * @param rob_id which robot to target
     * @param coord_type specifies the destination coordinate system
     * @param uf_no user file number for user specified coordinate system
     * @return
     * @throws IOException
     * @throws MotoPlusConnectionException
     */
    public MpFcsGetForceDataReturn mpFcsGetForceData(MP_FCS_ROB_ID rob_id, FCS_COORD_TYPE coord_type, int uf_no) throws IOException, MotoPlusConnectionException {
        starter.startMpFcsGetForceData(rob_id, coord_type, uf_no);
        return returner.getMpFcsGetForceDataReturn();
    }

    /**
     * Sets the parameters necessary for the impedance control, and starts the
     * impedance control.
     *
     * @param rob_id target robot ID specification
     * @param m Specifies the inertia coefficients in the 6-element array of the
     * int type. m[0] Inertia coefficient (X direction), unit (Ns^2/m^2) m[1]
     * Inertia coefficient (Y direction), unit (Ns^2/m^2) m[2] Inertia
     * coefficient (Z direction), unit (Ns^2/m^2) m[3] Inertia coefficient (Rx
     * direction), units are undocumented m[4] Inertia coefficient (Ry
     * direction), units are undocumented m[5] Inertia coefficient (Rz
     * direction), units are undocumented
     *
     * @param d Specifies the viscous coefficients in the 6-element array of the
     * int type. d[0] Viscous coefficient (X direction), unit (Ns/m) d[1]
     * Viscous coefficient (Y direction), unit (Ns/m) d[2] Viscous coefficient
     * (Z direction), unit (Ns/m) d[3] Viscous coefficient (Rx direction), units
     * are undocumented d[4] Viscous coefficient (Ry direction), units are
     * undocumented d[5] Viscous coefficient (Rz direction), units are
     * undocumented
     *
     * @param k Specifies the spring coefficients in the 6-element array of the
     * int type. k[0] Spring coefficient (X direction), unit (N/m) k[1] Spring
     * coefficient (Y direction), unit (N/m) k[2] Spring coefficient (Z
     * direction), unit (N/m) d[3] Viscous coefficient (Rx direction), units are
     * undocumented d[4] Viscous coefficient (Ry direction), units are
     * undocumented d[5] Viscous coefficient (Rz direction), units are
     * undocumented
     *
     * @param coord_type Specifies the control coordinate system.
     * (0=robot,1=tool,2=user) (Note this does not match the mpFcsGetForceData
     * coord_type which starts with 0=base, 1=robot ... )
     *
     * @param uf_no Specifies the user coordinate file number (0 to 15) when the
     * user coordinate is specified.
     * @param cart_axes Specifies X,Y,Z,Rx,Ry,Rz (d0 to d5) as the valid control
     * axes.
     * @param option_ctrl Specifies the optional controls (d0 execute the
     * contact stability process, d1 retain previous force command value)
     * @return
     * @throws IOException
     * @throws MotoPlusConnectionException
     */
    public MpFcsBaseReturn mpFcsStartImp(
            MP_FCS_ROB_ID rob_id,
            int m[],
            int d[],
            int k[],
            int coord_type,
            int uf_no,
            int cart_axes,
            int option_ctrl
    ) throws IOException, MotoPlusConnectionException {
        if (null == m || m.length != 6) {
            throw new IllegalArgumentException("m must be non-null and have length = 6 : m =" + Arrays.toString(m));
        }
        if (null == d || d.length != 6) {
            throw new IllegalArgumentException("d must be non-null and have length = 6 : d =" + Arrays.toString(d));
        }
        if (null == k || k.length != 6) {
            throw new IllegalArgumentException("k must be non-null and have length = 6 : k =" + Arrays.toString(k));
        }
        starter.startMpFcsStartImp(rob_id, m, d, k, coord_type, uf_no, cart_axes, option_ctrl);
        return returner.getMpFcsBaseReturn();
    }

    public MpFcsBaseReturn mpFcsSetReferenceForce(MP_FCS_ROB_ID rob_id, int fref_data[]) throws IOException, MotoPlusConnectionException {
        starter.startMpFcsSetReferenceForce(rob_id, fref_data);
        return returner.getMpFcsBaseReturn();
    }

    public MpFcsBaseReturn mpFcsEndImp(MP_FCS_ROB_ID rob_id) throws IOException, MotoPlusConnectionException {
        starter.startMpFcsEndImp(rob_id);
        return returner.getMpFcsBaseReturn();
    }

    public MpFcsBaseReturn mpFcsConvForceScale(MP_FCS_ROB_ID rob_id, int scale) throws IOException, MotoPlusConnectionException {
        starter.startMpFcsConvForceScale(rob_id, scale);
        return returner.getMpFcsBaseReturn();
    }

    @Deprecated
    /**
     * *
     * mpServoFcs.h includes a prototype for mpFcsGetSensorData but there is no
     * documentation for it in the MotoPlus PDF files. It is not clear what it
     * does but it may be redundant with mpFcsGetForceData() which is
     * documented.
     */
    public MpFcsGetSensorDataReturn mpFcsGetSensorData(MP_FCS_ROB_ID rob_id) throws IOException, MotoPlusConnectionException {
        starter.startMpFcsGetSensorData(rob_id);
        return returner.getMpFcsGetSensorDataReturn();
    }

    public static final int MP_GRP_AXES_NUM = 8;

    /**
     * Converts a cartesian coordinate position (robot coordinate systems) of
     * the specified control group to an angle position.
     *
     * @param grp_no Control group number. Acquire the control group number by
     * mpCtrlGrpId2GrpNo()
     * @param angle joint angles in 0.0001 degrees units. length should be 8.
     * Ordered with joints S,L,U,R,B,T,E,W
     * @param tool_no
     * @return
     * @throws IOException
     * @throws MotoPlusConnectionException
     */
    public MpKinCartPosReturn mpConvAxesToCartPos(int grp_no, int angle[], int tool_no) throws IOException, MotoPlusConnectionException {
        starter.startMpConvAxesToCartPos(grp_no, angle, tool_no);
        return returner.getMpCartPosReturn();
    }

    public MpKinAngleReturn mpConvCartPosToAxes(int grp_no, MP_COORD coord, int tool_no, int fig_ctrl, int prev_angle[], MP_KINEMA_TYPE kinema_type) throws IOException, MotoPlusConnectionException {
        starter.startMpConvCartPosToAxes(grp_no, coord, tool_no, fig_ctrl, prev_angle, kinema_type);
        return returner.getMpAngleReturn();
    }

    public MpKinAngleReturn mpConvPulseToAngle(int grp_no, int pulse[]) throws IOException, MotoPlusConnectionException {
        starter.startMpConvPulseToAngle(grp_no, pulse);
        return returner.getMpAngleReturn();
    }

    public MpKinPulseReturn mpConvAngleToPulse(int grp_no, int pulse[]) throws IOException, MotoPlusConnectionException {
        starter.startMpConvAngleToPulse(grp_no, pulse);
        return returner.getMpPulseReturn();
    }

    public MpKinPulseReturn mpConvFBPulseToPulse(int grp_no, int fbpulse[]) throws IOException, MotoPlusConnectionException {
        starter.startMpConvFBPulseToPulse(grp_no, fbpulse);
        return returner.getMpPulseReturn();
    }

//    extern int mpConvPulseToAngle(unsigned int grp_no, long pulse[MP_GRP_AXES_NUM], long angle[MP_GRP_AXES_NUM]);
//    extern int mpConvAngleToPulse(unsigned int grp_no, long angle[MP_GRP_AXES_NUM], long pulse[MP_GRP_AXES_NUM]);
//    extern int mpConvFBPulseToPulse(unsigned int grp_no, long fbpulse[MP_GRP_AXES_NUM], long pulse[MP_GRP_AXES_NUM]);
//    extern int mpMakeFrame(MP_XYZ* org_vector, MP_XYZ* x_vector, MP_XYZ* y_vector, MP_FRAME* frame);
//    extern int mpInvFrame(MP_FRAME*org_frame, MP_FRAME* frame);
//    extern int mpRotFrame(MP_FRAME* org_frame, double angle, MP_XYZ* vector, MP_FRAME* frame);
//    extern int mpMulFrame(MP_FRAME* frame1,MP_FRAME* frame2,MP_FRAME* frame_prod);
//    extern int mpZYXeulerToFrame(MP_COORD* coord, MP_FRAME* frame);
//    extern int mpFrameToZYXeuler(MP_FRAME*frame,MP_COORD* coord);
//    extern int mpCrossProduct(MP_XYZ* vector1, MP_XYZ* vector2, MP_XYZ* xyz_prod);
//    extern int mpInnerProduct(MP_XYZ* vector1, MP_XYZ* vector2, double* double_prod);
    public int mpGetRtc() throws IOException, MotoPlusConnectionException {
        starter.startMpGetRtc();
        return returner.getIntReturn();
    }

    public int mpGetFileCount(MpExtensionType ext) throws IOException, MotoPlusConnectionException {
        starter.startMpGetFileCount(ext);
        return returner.getMpFileCountReturn();
    }

    public String mpGetFileName(MpExtensionType ext, int index) throws IOException, MotoPlusConnectionException {
        starter.startMpGetFileName(ext, index);
        return returner.getMpFileNameReturn();
    }

    public String[] mpGetFileNames(MpExtensionType ext) throws IOException, MotoPlusConnectionException {
        int fileCount = mpGetFileCount(ext);
        if (fileCount < 1) {
            return new String[0];
        }
        String filenames[] = new String[fileCount];
        for (int i = 0; i < fileCount; i++) {
            String filename = mpGetFileName(ext, i);
            filenames[i] = filename;
        }
        return filenames;
    }

    public int mpOpenFile(String filename, MpFileFlagsEnum flags) throws IOException, MotoPlusConnectionException {
        starter.startMpOpenFile(filename, flags);
        return returner.getMpOpenFileReturn();
    }

    public int mpCreateFile(String filename, MpFileFlagsEnum flags) throws IOException, MotoPlusConnectionException {
        starter.startMpCreateFile(filename, flags.getId());
        return returner.getMpCreateFileReturn();
    }

    public int mpCloseFile(int fd) throws IOException, MotoPlusConnectionException {
        starter.startMpCloseFile(fd);
        return returner.getMpCloseFileReturn();
    }

    public int mpReadFileEx(int fd, byte buf[], int offset, int len) throws IOException, MotoPlusConnectionException {
        if (null == buf || len <= 0 || offset < 0 || buf.length < offset + len) {
            throw new IllegalArgumentException("Buf must not be null and have length of atleast offset = " + offset + " + len = " + len);
        }
        if (len > MAX_READ_LEN) {
            throw new IllegalArgumentException("len = " + len + "   must not be greater than MAX_READ_LEN = " + MAX_READ_LEN);
        }
        starter.startMpReadFile(fd, (len - offset));
        return returner.getMpReadFileReturn(buf, offset, len);
    }

    public int mpReadFile(int fd, byte buf[]) throws IOException, MotoPlusConnectionException {
        return mpReadFileEx(fd, buf, 0, buf.length);
    }

    private static class ReadBlock {

        public ReadBlock(int r, byte[] buf) {
            this.r = r;
            this.buf = buf;
        }

        final int r;
        final byte buf[];

        @Override
        public String toString() {
            return "ReadBlock{" + "r=" + r + ", buf=" + new String(buf) + '}';
        }
    };

    public byte[] readFullFile(int fd) throws IOException, MotoPlusConnectionException {
        int r = MAX_READ_LEN;
        int sum = 0;
        ArrayList<ReadBlock> l = new ArrayList<>();
        while (r == MAX_READ_LEN) {
            byte buf[] = new byte[MAX_READ_LEN];
            r = mpReadFile(fd, buf);
            if (r > 0 && r <= buf.length) {
                l.add(new ReadBlock(r, buf));
                sum += r;
            }
        }
        byte ret[] = new byte[sum];
        int s = 0;
        for (ReadBlock rb : l) {
            System.arraycopy(rb.buf, 0, ret, s, rb.r);
            s += rb.r;
        }
        return ret;
    }

    public boolean openGripper() throws Exception {
        boolean a = clearToolChangerGripperIOAndWait();

        boolean b = writeConsecutiveI0(10010, 1, 0, 1);
//        MP_IO_DATA ioData[] = new MP_IO_DATA[3];
//        ioData[0] = new MP_IO_DATA();
//        ioData[0].ulAddr = 10010;
//        ioData[0].ulValue = 1;
//        ioData[1] = new MP_IO_DATA();
//        ioData[1].ulAddr = 10011;
//        ioData[1].ulValue = 0;
//        ioData[2] = new MP_IO_DATA();
//        ioData[2].ulAddr = 10012;
//        ioData[2].ulValue = 1;
//        boolean b = mpWriteIO(ioData, 3);
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(MotoPlusConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean c = clearToolChangerGripperIO();
        return a && b && c;
    }

    public boolean closeGripper() throws Exception {
        boolean a = clearToolChangerGripperIOAndWait();
        boolean b = writeConsecutiveI0(10010, 0, 1, 1);
        return a && b;
    }

    private boolean clearToolChangerGripperIO() throws IOException {
        ioClear = writeConsecutiveI0(10010, 0, 0, 0, 0, 0);
        return ioClear;
    }

    public boolean openToolChanger() throws Exception {
        boolean a = clearToolChangerGripperIOAndWait();

        boolean b = writeConsecutiveI0(10012, 1, 1, 0);
//        MP_IO_DATA ioData[] = new MP_IO_DATA[3];
//        ioData[0] = new MP_IO_DATA();
//        ioData[0].ulAddr = 10013;
//        ioData[0].ulValue = 1;
//        ioData[1] = new MP_IO_DATA();
//        ioData[1].ulAddr = 10014;
//        ioData[1].ulValue = 0;
//        ioData[2] = new MP_IO_DATA();
//        ioData[2].ulAddr = 10012;
//        ioData[2].ulValue = 1;
//        boolean b = mpWriteIO(ioData, 3);

        Thread.sleep(200);
//        ioData = new MP_IO_DATA[5];
//        ioData[0] = new MP_IO_DATA();
//        ioData[0].ulAddr = 10010;
//        ioData[0].ulValue = 0;
//        ioData[1] = new MP_IO_DATA();
//        ioData[1].ulAddr = 10011;
//        ioData[1].ulValue = 0;
//        ioData[2] = new MP_IO_DATA();
//        ioData[2].ulAddr = 10012;
//        ioData[2].ulValue = 0;
//        ioData[3] = new MP_IO_DATA();
//        ioData[3].ulAddr = 10013;
//        ioData[3].ulValue = 1;
//        ioData[4] = new MP_IO_DATA();
//        ioData[4].ulAddr = 10014;
//        ioData[4].ulValue = 0;
//        boolean c = mpWriteIO(ioData, 5);
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(MotoPlusConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
        boolean c = clearToolChangerGripperIO();
        return a && b && c;
    }

    private volatile boolean ioClear = false;

    private boolean clearToolChangerGripperIOAndWait() throws IOException, InterruptedException {
        if (ioClear) {
            return true;
        }
        boolean a = clearToolChangerGripperIO();
        Thread.sleep(50);
        ioClear = a;
        return a;
    }

    private boolean writeConsecutiveI0(int address, int... values) throws IOException {
        MP_IO_DATA ioData[] = new MP_IO_DATA[values.length];
        for (int i = 0; i < values.length; i++) {
            ioData[i] = new MP_IO_DATA();
            ioData[i].ulAddr = address + i;
            ioData[i].ulValue = values[i];
        }
        return mpWriteIO(ioData, values.length);
    }

    public boolean closeToolChanger() throws Exception {
        boolean a = clearToolChangerGripperIOAndWait();
        boolean b = writeConsecutiveI0(10012, 1, 0, 1);
//        MP_IO_DATA ioData[] = new MP_IO_DATA[3];
//        ioData[0] = new MP_IO_DATA();
//        ioData[0].ulAddr = 10013;
//        ioData[0].ulValue = 0;
//        ioData[1] = new MP_IO_DATA();
//        ioData[1].ulAddr = 10014;
//        ioData[1].ulValue = 1;
//        ioData[2] = new MP_IO_DATA();
//        ioData[2].ulAddr = 10012;
//        ioData[2].ulValue = 1;
//        boolean b = mpWriteIO(ioData, 3);
        Thread.sleep(200);
//        ioData = new MP_IO_DATA[5];
//        ioData[0] = new MP_IO_DATA();
//        ioData[0].ulAddr = 10010;
//        ioData[0].ulValue = 0;
//        ioData[1] = new MP_IO_DATA();
//        ioData[1].ulAddr = 10011;
//        ioData[1].ulValue = 0;
//        ioData[2] = new MP_IO_DATA();
//        ioData[2].ulAddr = 10012;
//        ioData[2].ulValue = 0;
//        ioData[3] = new MP_IO_DATA();
//        ioData[3].ulAddr = 10013;
//        ioData[3].ulValue = 0;
//        ioData[4] = new MP_IO_DATA();
//        ioData[4].ulAddr = 10014;
//        ioData[4].ulValue = 1;
//        boolean c = mpWriteIO(ioData, 5);
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(MotoPlusConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
        boolean c = clearToolChangerGripperIO();
        return a && b && c;
    }

    public byte[] readFullFileByName(String fname) throws IOException, MotoPlusConnectionException {
        int fd = mpOpenFile(fname, MpFileFlagsEnum.O_RDONLY);
        if (fd < 0) {
            throw new MotoPlusConnectionException("mpOpenFile(" + fname + ") returned " + fd);
        }
        byte ret[] = readFullFile(fd);
        mpCloseFile(fd);
        return ret;
    }

    public String readFullFileByNameToString(String fname) throws IOException, MotoPlusConnectionException {
        return new String(readFullFileByName(fname), Charset.forName("US-ASCII"));
    }

    public void downloadJobData(String jobName, File localFile) throws IOException, MotoPlusConnectionException {
        if (jobName.endsWith(".JBR") || jobName.endsWith(".JBI")) {
            jobName = jobName.substring(0, jobName.length() - 4);
        }
        String remoteFileName = "MPRAM1:0/" + jobName + ".JBR";
        int fd0 = -1;
        int closeRet = -1;

        System.out.println("Calling  mpc.mpCreateFile(\"" + remoteFileName + "\",MpFileFlagsEnum.O_RDWR); ");

        fd0 = mpCreateFile(remoteFileName, MpFileFlagsEnum.O_RDWR);
        System.out.println("fd0 = " + fd0);

        int fdReadRet = mpFdReadFile(fd0, jobName + ".JBR");
        System.out.println("fdReadRet = " + fdReadRet);

        closeRet = mpCloseFile(fd0);
        System.out.println("closeRet = " + closeRet);
        downloadFile(remoteFileName, localFile);
    }

    public void downloadMPRAM1File(String simpleFileName, File localFile) throws IOException, MotoPlusConnectionException {
        String remoteFileName = "MPRAM1:0/" + simpleFileName;
        int fd0 = -1;
        int closeRet = -1;

        System.out.println("Calling  mpc.mpCreateFile(\"" + remoteFileName + "\",MpFileFlagsEnum.O_RDWR); ");

        fd0 = mpCreateFile(remoteFileName, MpFileFlagsEnum.O_RDWR);
        System.out.println("fd0 = " + fd0);

        int fdReadRet = mpFdReadFile(fd0, simpleFileName);
        System.out.println("fdReadRet = " + fdReadRet);

        closeRet = mpCloseFile(fd0);
        System.out.println("closeRet = " + closeRet);
        downloadFile(remoteFileName, localFile);
    }

    public void downloadFile(String remoteFileName, File localFile) throws IOException, MotoPlusConnectionException {
        try (FileOutputStream fos = new FileOutputStream(localFile)) {
            int fd = mpOpenFile(remoteFileName, MpFileFlagsEnum.O_RDONLY);
            if (fd < 0) {
                throw new MotoPlusConnectionException("mpOpenFile(" + remoteFileName + ") returned " + fd);
            }
            try {
                int r = MAX_READ_LEN;
                int sum = 0;
                while (r == MAX_READ_LEN) {
                    byte buf[] = new byte[MAX_READ_LEN];
                    r = mpReadFile(fd, buf);
//                System.out.println("buf = " + new String(buf));
//                System.out.println("buf = " + Arrays.toString(buf));
                    System.out.println("buf.length = " + buf.length);
                    System.out.println("r = " + r);
                    if (r > 0 && r <= buf.length) {
//                    int lastWrite = 0;
//                    for (int i = 0; i < buf.length; i++) {
//                        char b = (char) buf[i];
//                        if (b == 0) {
//                            System.out.println("zero found at i = " + i);
//                            if (i > lastWrite) {
//                                fos.write(buf, lastWrite, (i - lastWrite));
//                            }
//                            lastWrite = i + 1;
//                        } else if (!Character.isLetterOrDigit(b) && !Character.isWhitespace(b) && (",/:\\-=+_.*#(){}[]<>,.".indexOf(b) < 0)) {
//                            fos.write(buf, lastWrite, (i - lastWrite));
//                            fos.write((" \\" + b + "\\ ").getBytes());
//                            System.out.println("b = " + new String(buf, i, 1) + String.format(" %d (0x%X)", (int) b, (int) b)
//                                    + ", lastWrite = " + lastWrite
//                                    + ", i = " + i);
//                            lastWrite = i;
//                        }
//                    }
                        fos.write(buf, 0, r);
                    }
                }
            } finally {
                mpCloseFile(fd);
            }
        }
    }
    public static final int MAX_WRITE_LEN = 1024 - 10;
    public static final int MAX_READ_LEN = 1024 - 10;

    public int mpWriteFileEx(int fd, byte buf[], int offset, int len) throws IOException, MotoPlusConnectionException {
        if (null == buf || len <= 0 || offset < 0 || buf.length < offset + len) {
            throw new IllegalArgumentException("Buf must not be null and have length of atleast offset = " + offset + " + len = " + len);
        }
        if (len > MAX_WRITE_LEN) {
            throw new IllegalArgumentException("len = " + len + "  must not be greater  than MAX_WRITE_LEN = " + MAX_WRITE_LEN);
        }
        starter.startMpWriteFile(fd, buf, offset, len);
        return returner.getMpWriteFileReturn();
    }

    public int mpWriteFile(int fd, byte buf[]) throws IOException, MotoPlusConnectionException {
        return mpWriteFileEx(fd, buf, 0, buf.length);
    }

    public int mpFdGetJobList(int fd, MP_GET_JOBLIST_RSP_DATA jlistData) throws IOException, MotoPlusConnectionException {
        starter.startMpFdGetJobList(fd, jlistData);
        return returner.getMpFdGetJobListReturn(jlistData);
    }

    public int mpFdReadFile(int fd, String name) throws IOException, MotoPlusConnectionException {
        starter.startMpFdReadFile(fd, name);
        return returner.getMpFdReadFileReturn();
    }

    public int mpFdWriteFile(int fd, String name) throws IOException, MotoPlusConnectionException {
        starter.startMpFdWriteFile(fd, name);
        return returner.getMpFdWriteFileReturn();
    }

    public int mpLoadFile(MpExFileRamIdEnum id, String name, String name2) throws IOException, MotoPlusConnectionException {
        starter.startMpLoadFile(id.getId(), name, name2);
        return returner.getMpLoadFileReturn();
    }

    public int mpSaveFile(MpExFileRamIdEnum id, String name, String name2) throws IOException, MotoPlusConnectionException {
        starter.startMpSaveFile(id.getId(), name, name2);
        return returner.getMpSaveFileReturn();
    }

}
