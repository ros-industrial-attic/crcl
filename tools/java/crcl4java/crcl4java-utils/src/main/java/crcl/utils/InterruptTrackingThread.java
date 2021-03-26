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
package crcl.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author shackle
 */
public class InterruptTrackingThread extends java.lang.Thread {

    public InterruptTrackingThread() {
        super();
    }

    public InterruptTrackingThread(Runnable r) {
        super(r);
    }

    public InterruptTrackingThread(ThreadGroup tg, Runnable r) {
        super(tg, r);
    }

    public InterruptTrackingThread(String string) {
        super(string);
    }

    public InterruptTrackingThread(ThreadGroup tg, String string) {
        super(tg, string);
    }

    public InterruptTrackingThread(Runnable r, String string) {
        super(r, string);
    }

    public InterruptTrackingThread(ThreadGroup tg, Runnable r, String string) {
        super(tg, r, string);
    }

    public InterruptTrackingThread(ThreadGroup tg, Runnable r, String string, long l) {
        super(tg, r, string, l);
    }
    
    private final List<StackTraceElement []>
            interruptTraces = Collections.synchronizedList(new ArrayList<>());
    
    private final List<String>
            interruptThreadNames = Collections.synchronizedList(new ArrayList<>());
    
    @Override
    public void interrupt() {
        interruptTraces.add(Thread.currentThread().getStackTrace());
        interruptThreadNames.add(Thread.currentThread().getName());
        super.interrupt(); 
    }

    public String getInterruptTracesString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < interruptTraces.size(); i++) {
            StackTraceElement trace[] = interruptTraces.get(i);
            if(null == trace) {
                break;
            }
            sb.append("\n").append(XFuture.traceToString(trace)).append("\n");
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return "InterruptTrackingThread{\n   super="+super.toString() + ",\n    interruptTraces=" + getInterruptTracesString() + ", interruptThreadNames=" + interruptThreadNames + '}';
    }
    
    
    
}
