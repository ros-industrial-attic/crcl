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
package crcl.utils.outer.interfaces;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public abstract class CommandStatusLogElement {

    public CommandStatusLogElement(long id, long time,/* @Nullable */ String progName, int progIndex, /* @Nullable */  String svrSocket) {
        this.id = id;
        this.time = time;
        this.progName = (null != progName)?progName:"";
        this.progIndex = progIndex;
        this.svrSocket = (null != svrSocket)?svrSocket:"";
    }

    private final String progName;
    private final int progIndex;
    private final String svrSocket;

    public String getSvrSocket() {
        return svrSocket;
    }
    
    public String getProgName() {
        return progName;
    }

    public int getProgIndex() {
        return progIndex;
    }
    
    
    private final long id;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public long getId() {
        return id;
    }
    private final long time;

    /**
     * Get the value of time
     *
     * @return the value of time
     */
    public long getTime() {
        return time;
    }
    
    private static final DateFormat timeFormat = new SimpleDateFormat("HHmmss.SSS");
    
    public  String getTimeString() {
        Date date = new Date(time);
        return timeFormat.format(date);
    }

    
    @Override
    public String toString() {
        return "CommandStatusLogElement{" + "id=" + id + ", time=" + getTimeString() + '}';
    }

}
