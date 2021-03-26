package crcl.exi;

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
 *  This software is experimental. NIST assumes no responsibility whatsoever for its use by other
 * parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic. We would appreciate
 * acknowledgement if the software is used. This software can be
 * redistributed and/or modified freely provided that any derivative works
 * bear some notice that they are derived from it, and any modified
 * versions bear some notice that they have been modified.
 * 
 *  See http://www.copyright.gov/title17/92chap1.html#105
 * 
 */
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStateEnumType;
import crcl.base.CommandStatusType;
import crcl.base.GetStatusType;
import crcl.base.GripperStatusType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.PointType;
import crcl.base.PoseType;
import crcl.base.VacuumGripperStatusType;
import crcl.base.VectorType;
import crcl.utils.CRCLException;
import crcl.utils.CRCLPosemath;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * 
 * NOTE: Comments beginning with {@literal @} or {@literal >>>} are used by Checker Framework Comments
 * beginning with {@literal @} must have no spaces in the comment or Checker will ignore
 * it.
 *
 * See http://types.cs.washington.edu/checker-framework for null pointer
 * checks. This file can be compiled without the Checker Framework, but using
 * the framework allows potential NullPointerExceptions to be found.
 */
 /*>>>
import org.checkerframework.checker.nullness.qual.*;
 */
public class PerfTest {

    private static CRCLStatusType createStatus() {
        CRCLStatusType stat = new CRCLStatusType();
        CommandStatusType cst = new CommandStatusType();
        cst.setCommandID(1);
        cst.setStatusID(1);
        cst.setCommandState(CommandStateEnumType.CRCL_WORKING);
        stat.setCommandStatus(cst);
        PoseType pose = new PoseType();
        PointType pt = new PointType();
        pt.setX(0.0);
        pt.setY(1.0);
        pt.setZ(10.0);
        pose.setPoint(pt);
        VectorType xAxis = new VectorType();
        xAxis.setI(1.0);
        xAxis.setJ(0.0);
        xAxis.setK(0.0);
        pose.setXAxis(xAxis);
        VectorType zAxis = new VectorType();
        zAxis.setI(0.0);
        zAxis.setJ(0.0);
        zAxis.setK(1.0);
        pose.setZAxis(zAxis);
        CRCLPosemath.setPose(stat, pose);
        GripperStatusType gst = new VacuumGripperStatusType();
        stat.setGripperStatus(gst);
        gst.setGripperName("vacgrip");
        JointStatusesType jst = new JointStatusesType();
        for (int i = 1; i <= 6; i++) {
            JointStatusType js = new JointStatusType();
            js.setJointNumber(i);
            js.setJointPosition(i * 0.1);
            jst.getJointStatus().add(js);
        }
        stat.setJointStatuses(jst);
        return stat;
    }

    public static void main(String[] args) {
        runPerfTest(false, true, 200);
        runPerfTest(false, false, 200);
        MinMaxAvg ft = runPerfTest(false, true, 25000);
        MinMaxAvg ff = runPerfTest(false, false, 25000);
        runPerfTest(true, true, 200);
        MinMaxAvg tt = runPerfTest(true, true, 25000);
        runPerfTest(true, false, 200);
        MinMaxAvg tf = runPerfTest(true, false, 25000);
        System.out.println("useExi,validate,\tmin    ,\tmax    ,\tavg");
        if (null != tt) {
            System.out.println("true,  true,    " + tt.toStringCsv());
        }
        if (null != tf) {
            System.out.println("true,  false,   " + tf.toStringCsv());
        }
        if (null != ft) {
            System.out.println("false, true,    " + ft.toStringCsv());
        }
        if (null != ff) {
            System.out.println("false, false,   " + ff.toStringCsv());
        }
        System.exit(0);
    }

    public static class MinMaxAvg {

        private final double min;
        private final double max;
        private final double avg;

        public MinMaxAvg(double min, double max, double avg) {
            this.min = min;
            this.max = max;
            this.avg = avg;
        }

        public double getMin() {
            return min;
        }

        public double getMax() {
            return max;
        }

        public double getAvg() {
            return avg;
        }

        @Override
        public String toString() {
            return "MinMaxAvgDev{" + "min=" + min + ", max=" + max + ", avg=" + avg + '}';
        }

        public String toStringCsv() {
            return String.format("\t%6.6f,\t%6.6f,\t%6.6f", min, max, avg);
        }
    }

    /*@Nullable*/
    public static MinMaxAvg runPerfTest(final boolean enableEXI, final boolean validate, final int repeats) throws RuntimeException {
        ServerSocket ss = null;
        try {
            System.out.println("Starting runPerfTest(enableEXI=" + enableEXI + ",validate=" + validate + ",repeats=" + repeats + ") ...");
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                Logger.getLogger(PerfTest.class.getName()).log(Level.FINEST, "exception normally ignored", e);
            }
            CrclExiSocket csTst = new CrclExiSocket();
            csTst.setReplaceHeader(true);
            CRCLStatusType stat0 = createStatus();
            String xmlS = csTst.statusToString(stat0, validate);
//            System.out.println("Starting runPerfTest(" + enableEXI + ") ...");
//            System.out.println("enableEXI = " + enableEXI);
//            System.out.println("xmlS = " + xmlS);
//            System.out.println("xmlS.length() = " + xmlS.length());
            byte ba[] = csTst.statusToEXI(stat0);
//            System.out.println("ba.length = " + ba.length);
//            System.out.println("ba = " + Arrays.toString(ba));
            final List<Socket> sockets = new ArrayList<>();
            final ExecutorService exServ = Executors.newCachedThreadPool();
            ss = new ServerSocket(44004);
//            System.out.println("ss.getLocalPort() = " + ss.getLocalPort());
            final ServerSocket ssf = ss;
            exServ.execute(new Runnable() {

                @Override
                public void run() {
                    while (!Thread.currentThread().isInterrupted() && !exServ.isShutdown()) {
                        try {
                            Socket s = ssf.accept();
                            sockets.add(s);
                            final CrclExiSocket cs = new CrclExiSocket(s);
                            cs.setEXIEnabled(enableEXI);
                            final CRCLStatusType status = createStatus();
                            exServ.execute(new Runnable() {

                                @Override
                                public void run() {
                                    try {
                                        while (!Thread.currentThread().isInterrupted()
                                                && cs.isConnected()
                                                && !exServ.isShutdown()) {
                                            CRCLCommandInstanceType cmdInstance = cs.readCommand(validate);
                                            CRCLCommandType cmd = cmdInstance.getCRCLCommand();
                                            status.getCommandStatus().setCommandID(cmd.getCommandID());
                                            status.getCommandStatus().setStatusID(status.getCommandStatus().getStatusID() + 1);
                                            cs.writeStatus(status, validate);
                                        }
                                    } catch (Exception ex) {
                                        Logger.getLogger(PerfTest.class.getName()).log(Level.FINEST, "exception normally ignored", ex);
                                    } finally {
                                        try {
                                            cs.close();
                                        } catch (IOException ex) {
                                            Logger.getLogger(PerfTest.class.getName()).log(Level.SEVERE, "Error closing socket.", ex);
                                        }
                                    }
                                }
                            });
                        } catch (Exception ex) {
                            Logger.getLogger(PerfTest.class.getName()).log(Level.FINEST, "exception normally ignored", ex);
                        }
                    }
                    for (int i = 0; i < sockets.size(); i++) {
                        Socket s = sockets.get(i);
                        if (null != s) {
                            try {
                                s.close();
                            } catch (Exception e) {
                                Logger.getLogger(PerfTest.class.getName()).log(Level.FINEST, "exception normally ignored", e);
                            }
                        }

                    }
                }
            });
            long end = 0;
            long start = 0;
            long diff_max = 0;
            double sum_diff2 = 0;
            long diff_min = Long.MAX_VALUE;
            try (CrclExiSocket cs = new CrclExiSocket("localhost", ss.getLocalPort())) {
                cs.setEXIEnabled(enableEXI);
                CRCLCommandInstanceType cmdInstance = new CRCLCommandInstanceType();
                GetStatusType getStatus = new GetStatusType();
                getStatus.setCommandID(1);
                cmdInstance.setCRCLCommand(getStatus);
                start = System.nanoTime();
                for (int i = 0; i < repeats; i++) {
                    long t1 = System.nanoTime();
                    getStatus.setCommandID(getStatus.getCommandID() + 1);
//                    System.out.println("getStatus.getCommandID() = " + getStatus.getCommandID());
                    cs.writeCommand(cmdInstance, validate);
                    CRCLStatusType stat = cs.readStatus(validate);
//                    System.out.println("stat.getCommandStatus().getCommandID() = " + stat.getCommandStatus().getCommandID());
                    if (stat.getCommandStatus().getCommandID() != getStatus.getCommandID()) {
                        throw new RuntimeException("Command ID doesn't match : "
                                + stat.getCommandStatus().getCommandID() + " != " + getStatus.getCommandID());
                    }
                    long t2 = System.nanoTime();
                    long diff = t2 - t1;
                    if (diff > diff_max) {
                        diff_max = diff;
                    }
                    if (diff < diff_min) {
                        diff_min = diff;
                    }
                    sum_diff2 += diff * diff;
                }
                end = System.nanoTime();
            }
            System.out.println("(end-start) = " + (end - start) + " ns");
            System.out.println("Average time = " + ((double) (end - start)) / repeats + " ns");
            System.out.println("Max time = " + diff_max + " ns");
            exServ.shutdown();
            for (int i = 0; i < sockets.size(); i++) {
                Socket s = sockets.get(i);
                if (null != s) {
                    try {
                        s.close();
                    } catch (Exception e) {
                        Logger.getLogger(PerfTest.class.getName()).log(Level.FINEST, "exception normally ignored", e);
                    }
                }
            }
            exServ.awaitTermination(5, TimeUnit.SECONDS);
            exServ.shutdownNow();
            sockets.clear();
//            double avg = (end - start) * 1e-6 / repeats;
//            double dev = Math.sqrt(sum_diff2 / repeats - avg * avg);
            return new MinMaxAvg(
                    diff_min * 1e-6,
                    diff_max * 1e-6,
                    (end - start) * 1e-6 / repeats
            );
        } catch (CRCLException | IOException | InterruptedException ex) {
            Logger.getLogger(PerfTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (null != ss) {
                try {
                    ss.close();
                } catch (Exception exx) {
                }
            }
            System.out.println("End of runPerfTest(enableEXI=" + enableEXI + ",validate=" + validate + ",repeats=" + repeats + ") ...");
            System.out.println("");
        }
        return null;
    }
    private static final Logger LOG = Logger.getLogger(PerfTest.class.getName());
}
