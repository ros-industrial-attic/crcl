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
package com.github.wshackle.crcl4java.motoman.sys1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class MP_ALARM_CODE_DATA {

    public short usErrorNo;
    public short usErrorData;
    public short usAlarmNum;

    static public final class MP_ALARM_DATA {

        static public final int MAX_ALARM_COUNT = 4;
        public final short usAlarmNo[] = new short[MAX_ALARM_COUNT];
        public final short usAlarmData[] = new short[MAX_ALARM_COUNT];

        @Override
        public String toString() {
            return "MP_ALARM_DATA{"
                    + "usAlarmNo=" + Arrays.toString(usAlarmNo)
                    + ", comments=" + Arrays.toString(getAlarmNoComments())
                    + ", usAlarmData=" + Arrays.toString(usAlarmData) + '}';
        }

        public String[] getAlarmNoComments() {
            return MP_ALARM_CODE_DATA.getAlarmNoComments(usAlarmNo);
        }

    };
    public final MP_ALARM_DATA AlarmData = new MP_ALARM_DATA();

    @Override
    public String toString() {
        return "MP_ALARM_CODE_DATA{" + "usErrorNo=" + usErrorNo + ", usErrorData=" + usErrorData + ", usAlarmNum=" + usAlarmNum + ", AlarmData=" + AlarmData + '}';
    }

    private final static Map<Short, String> alarmCommentMap = new HashMap<>();

    static {
        alarmCommentMap.put((short) 4980, "DESTINATION PULSE LIMIT");
        alarmCommentMap.put((short) 4312, "ENCODER BATTERY ERROR");
        alarmCommentMap.put((short) 4677, "IMPOSSIBLE LINEAR MOTION");
    }

    public static String getAlarmNoComment(short alarmNo) {
        return alarmCommentMap.get(alarmNo);
    }

    public static String[] getAlarmNoComments(short alarmNos[]) {
        String comments[] = new String[alarmNos.length];
        for (int i = 0; i < alarmNos.length; i++) {
            comments[i] = getAlarmNoComment(alarmNos[i]);
        }
        return comments;
    }

}
