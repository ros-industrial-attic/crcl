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

import com.github.wshackle.crcl4java.motoman.motctrl.MotCtrlReturnEnum;
import com.github.wshackle.crcl4java.motoman.sys1.MP_ALARM_CODE_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_ALARM_STATUS_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_CART_POS_RSP_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_MODE_DATA;
import com.github.wshackle.crcl4java.motoman.sys1.MP_PULSE_POS_RSP_DATA;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class MpcStatus {

    private final MP_CART_POS_RSP_DATA pos;
    private final MP_PULSE_POS_RSP_DATA pulseData;
    private final MotCtrlReturnEnum motTargetReceiveRet;
    private final MP_MODE_DATA modeData;
    private final MP_ALARM_CODE_DATA alarmCodeData;
    private final MP_ALARM_STATUS_DATA alarmStatusData;
    private final int recvId;
    private final int statusCount;
    private final double targetPosDiff;
    private final double targetRotDiffMax;

    public MpcStatus(MP_CART_POS_RSP_DATA pos, MP_PULSE_POS_RSP_DATA pulseData, MotCtrlReturnEnum motTargetReceiveRet, MP_MODE_DATA modeData, MP_ALARM_CODE_DATA alarmCodeData, MP_ALARM_STATUS_DATA alarmStatusData, int recvId, int statusCount, double targetPosDiff, double targetRotDiffMax) {
        this.pos = pos;
        this.pulseData = pulseData;
        this.motTargetReceiveRet = motTargetReceiveRet;
        this.modeData = modeData;
        this.alarmCodeData = alarmCodeData;
        this.alarmStatusData = alarmStatusData;
        this.recvId = recvId;
        this.statusCount = statusCount;
        this.targetPosDiff = targetPosDiff;
        this.targetRotDiffMax = targetRotDiffMax;
    }

    public double getTargetPosDiff() {
        return targetPosDiff;
    }

    public MP_CART_POS_RSP_DATA getPos() {
        return pos;
    }

    public MP_PULSE_POS_RSP_DATA getPulseData() {
        return pulseData;
    }

    public MotCtrlReturnEnum getMotTargetReceiveRet() {
        return motTargetReceiveRet;
    }

    public MP_MODE_DATA getModeData() {
        return modeData;
    }

    public MP_ALARM_CODE_DATA getAlarmCodeData() {
        return alarmCodeData;
    }

    public MP_ALARM_STATUS_DATA getAlarmStatusData() {
        return alarmStatusData;
    }

    public int getRecvId() {
        return recvId;
    }

    public int getStatusCount() {
        return statusCount;
    }

    @Override
    public String toString() {
        return "MpcStatus{" + "pos=" + pos + ",\n pulseData=" + pulseData + ",\n motTargetReceiveRet=" + motTargetReceiveRet + ",\n modeData=" + modeData + ",\n alarmCodeData=" + alarmCodeData + ",\n alarmStatusData=" + alarmStatusData + ",\n recvId=" + recvId + ",\n statusCount=" + statusCount + ",\n targetPosDiff=" + targetPosDiff + ",\n targetRotDiffMax=" + targetRotDiffMax + '}';
    }

    
}
