/* 
 * This is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * 
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain. This software is experimental.
 * NIST assumes no responsibility whatsoever for its use by other
 * parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic. We would appreciate
 * acknowledgment if the software is used. This software can be
 * redistributed and/or modified freely provided that any derivative works
 * bear some notice that they are derived from it, and any modified
 * versions bear some notice that they have been modified.
 * 
 */
package crcl.utils;

import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStateEnumType;
import crcl.base.CommandStatusType;
import crcl.base.GetStatusType;
import crcl.base.InitCanonType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.MoveToType;
import crcl.base.PointType;
import crcl.base.PoseType;
import static crcl.utils.CRCLPosemath.pose;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.vector;

/**
 * Example Client using CRCLSocket and non-blocking polls for the status
 messages on the read.
 *
 * @author Will Shackleford{@literal <william.shackleford@nist.gov> }
 */
@SuppressWarnings("nullness")
public class CRCLSocketPollingClientExample {

    public static void main(String[] args) throws InterruptedException {
        try {
            // Connect to the server
            CRCLSocket s = new CRCLSocket("localhost", CRCLSocket.DEFAULT_PORT);

            // Create an instance to wrap all commands.
            CRCLCommandInstanceType instance = new CRCLCommandInstanceType();

            // Create and send init command.
            InitCanonType init = new InitCanonType();
            init.setCommandID(7);
            instance.setCRCLCommand(init);
            s.writeCommand(instance);

            // Create and send MoveTo command.
            MoveToType moveTo = new MoveToType();
            moveTo.setCommandID(8);
            PoseType pose = pose(point(1.1, 0.0, 0.1), vector(1, 0, 0), vector(0, 0, 1));
            moveTo.setEndPosition(pose);
            moveTo.setMoveStraight(false);
            instance.setCRCLCommand(moveTo);
            s.writeCommand(instance);

            CommandStatusType cmdStat;
            CommandStateEnumType state = CommandStateEnumType.CRCL_WORKING;
            long IDback = 1;

            // Enter a loop requesting status messages until our command 
            // is done or an error occurs.
            do {
                // Create and send getStatus request.
                GetStatusType getStat = new GetStatusType();
                getStat.setCommandID(9);
                instance.setCRCLCommand(getStat);
                s.writeCommand(instance);

                // Yield the processor rather than spin non-blocking
                Thread.sleep(200);
                // Check if we have recieved on or more status messages from server.
                List<CRCLStatusType> statlist = s.checkForStatusMessages(true);
                
                for (CRCLStatusType stat : statlist) {
                    // Print out the status details.
                    cmdStat = stat.getCommandStatus();
                    state = cmdStat.getCommandState();
                    IDback = cmdStat.getCommandID();
                    System.out.println("Status:");
                    System.out.println("CommandID = " + IDback);
                    System.out.println("State = " + cmdStat.getCommandState());
                    PointType pt = CRCLPosemath.getNullablePoint(stat);
                    if (null != pt) {
                        System.out.println("pose = " + pt.getX() + "," + pt.getY() + "," + pt.getZ());
                    }
                    JointStatusesType jst = stat.getJointStatuses();
                    if (null != jst) {
                        List<JointStatusType> l = jst.getJointStatus();
                        System.out.println("Joints:");
                        for (JointStatusType js : l) {
                            System.out.println("Num=" + js.getJointNumber() + " Pos=" + js.getJointPosition());
                        }
                    }
                }
            } while (moveTo.getCommandID() != IDback || CommandStateEnumType.CRCL_WORKING.equals(state));

        } catch (CRCLException | IOException ex) {
            Logger.getLogger(CRCLSocketPollingClientExample.class.getName()).log(Level.SEVERE, "Example Main failed.", ex);
        }
    }
    private static final Logger LOG = Logger.getLogger(CRCLSocketPollingClientExample.class.getName());
}
