
#include "crclj.h"
#include <iostream>
#include <stdlib.h>

using namespace crclj;
using namespace crclj::crcl::base;
using namespace crclj::crcl::utils;
using namespace crclj::java::math;
using namespace crclj::java::util;

using namespace std;

int main(int argc, const char **argv) {

    try {
        // Connect to the server
        CRCLSocket s("localhost", 64444);
        
        // Create an instance to wrap all commands.
        CRCLCommandInstanceType instance;

        // Create and send init command.
        InitCanonType initCmd;
        initCmd.setCommandID(7);
        instance.setCRCLCommand(initCmd);
        s.writeCommand(instance);

        GetStatusType getStat;
        long IDback=-1;
        CommandStatusType cmdStat;
        
        do {
            
            getStat.setCommandID(9);
            instance.setCRCLCommand(getStat);
            s.writeCommand(instance);

            CRCLStatusType stat = s.readStatus();
            cmdStat = stat.getCommandStatus();
            IDback = cmdStat.getCommandID();
            cout << "Command ID=" <<  IDback;
            PrintObject("stat=", stat);
            PrintObject("cmdStat.getCommandState()=",cmdStat.getCommandState());
            
        } while (!IDback == initCmd.getCommandID() || cmdStat.getCommandState().equals(CommandStateEnumType::getCRCL_WORKING()));
        
        // Create and send MoveTo command.
        MoveToType moveTo;
        moveTo.setCommandID(8);
        PoseType pose;
        PointType pt;
        pt.setX(248.5);
        pt.setY(2.5);
        pt.setZ(0.1);
        
        pose.setPoint(pt);
        VectorType xAxis;
        xAxis.setI(1.0);
        xAxis.setJ(0.0);
        xAxis.setK(0.0);
        pose.setXAxis(xAxis);
        VectorType zAxis;
        zAxis.setI(0.0);
        zAxis.setJ(0.0);
        zAxis.setK(1.0);
        pose.setZAxis(zAxis);
        moveTo.setEndPosition(pose);
        moveTo.setMoveStraight(false);
        instance.setCRCLCommand(moveTo);
        s.writeCommand(instance, JNI_TRUE);
       
        
        IDback=-1;
        
        do {
            
            getStat.setCommandID(9);
            instance.setCRCLCommand(getStat);
            s.writeCommand(instance);

            CRCLStatusType stat = s.readStatus();
            cmdStat = stat.getCommandStatus();
            IDback = cmdStat.getCommandID();
            cout << "Command ID=" <<  IDback;
            PrintObject("stat=", stat);
            PrintObject("cmdStat.getCommandState()=",cmdStat.getCommandState());
            pose = stat.getPoseStatus().getPose();
            PrintObject("pose=", pose);
            pt = pose.getPoint();
            cout << "X:" << pt.getX() << endl;
            cout << "Y:" << pt.getY() << endl;
            cout << "Z:" << pt.getZ() << endl;
            JointStatusesType jst = stat.getJointStatuses();
            if (jst.jthis != NULL) {
                List l = jst.getJointStatus();
                for (int i = 0; i < l.size(); i++) {
                    JointStatusType elem;
                    elem = JointStatusType::cast(l.get(i));
                    cout << "Joint Number :" << elem.getJointNumber() <<" ";
                    PrintObject("Joint Position :", elem.getJointPosition());
                }
            }
        } while (!IDback == moveTo.getCommandID() || cmdStat.getCommandState().equals(CommandStateEnumType::getCRCL_WORKING()));
        
        
        MessageType message;
        message.setCommandID(11);
        message.setMessage("some message");
        instance.setCRCLCommand(message);
        s.writeCommand(instance, JNI_TRUE);
        
        do {
            
            getStat.setCommandID(12);
            instance.setCRCLCommand(getStat);
            s.writeCommand(instance);

            CRCLStatusType stat = s.readStatus();
            cmdStat = stat.getCommandStatus();
            IDback = cmdStat.getCommandID();
            cout << "Command ID=" <<  IDback;
            PrintObject("stat=", stat);
            PrintObject("cmdStat.getCommandState()=",cmdStat.getCommandState());
            
        } while (!IDback == message.getCommandID() || cmdStat.getCommandState().equals(CommandStateEnumType::getCRCL_WORKING()));
        
        cout << " End of C++ main() reached. " << endl;
    } catch (jthrowable t) {
        PrintJThrowable("Exception Thrown : ", t);
        exit(1);
    }
}
