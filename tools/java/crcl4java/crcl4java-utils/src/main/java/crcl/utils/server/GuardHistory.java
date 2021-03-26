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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class GuardHistory {
    
    private static final int DEFAULT_MAX_LENGTH = 500;
    
    private final int maxLength;
    private final List<GuardHistoryElement> list;

    public GuardHistory(int maxLength) {
        this.maxLength = maxLength;
        list = new ArrayList<>(maxLength);
    }

    public GuardHistory() {
        this(DEFAULT_MAX_LENGTH);
    }

    public int getMaxLength() {
        return maxLength;
    }

    public synchronized  List<GuardHistoryElement> getList() {
        return Collections.unmodifiableList(new ArrayList<>(list));
    }
    
    private volatile long startTime = -1;
    
    public synchronized  void addElement(long time, double value, double x, double y, double z) {
        while(list.size() > maxLength) {
            list.remove(0);
        }
        if(startTime <= 0 || list.size() <1) {
            startTime = time;
        }
        double dtime = (double) (1e-3*(time-startTime));
        list.add(new GuardHistoryElement(dtime,value,x,y,z ));
    }

    @Override
    public String toString() {
        GuardHistoryElement lastElement = list.isEmpty() ? null: list.get(list.size()-1);
        return "GuardHistory{" + "maxLength=" + maxLength + ", list.size()="+list.size()+", lastElement=" + lastElement + ", startTime=" + startTime + '}';
    }
    
    
    
    
}
