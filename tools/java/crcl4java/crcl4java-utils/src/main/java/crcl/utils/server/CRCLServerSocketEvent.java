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

import crcl.base.CRCLCommandInstanceType;
import crcl.base.GuardType;
import crcl.utils.CRCLSocket;
import org.checkerframework.checker.nullness.qual.Nullable;

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
 * @author Will Shackleford {@literal <william.shackleford@nist.gov> }
 */
public class CRCLServerSocketEvent<STATE_TYPE extends CRCLServerClientState> {

    private final @Nullable
    GuardType guard;

    public @Nullable
    GuardType getGuard() {
        return guard;
    }

    private final CRCLServerSocketEventType eventType;

    public CRCLServerSocketEventType getEventType() {
        return eventType;
    }

    private final @Nullable
    STATE_TYPE state;

    public @Nullable
    STATE_TYPE getState() {
        return state;
    }

    private final @Nullable
    CRCLSocket source;

    /**
     * Get the value of source
     *
     * @return the value of source
     */
    public @Nullable
    CRCLSocket getSource() {
        return source;
    }

    private final @Nullable
    CRCLCommandInstanceType instance;

    /**
     * Get the value of instance
     *
     * @return the value of instance
     */
    public @Nullable
    CRCLCommandInstanceType getInstance() {
        return instance;
    }

    final private @Nullable
    Exception exception;

    /**
     * Get the value of exception
     *
     * @return the value of exception
     */
    public @Nullable
    Exception getException() {
        return exception;
    }

    private CRCLServerSocketEvent(CRCLServerSocketEventType crclServerSocketEventType, @Nullable STATE_TYPE state, @Nullable CRCLCommandInstanceType instance, @Nullable Exception exception, @Nullable GuardType guard) {
        this.eventType = crclServerSocketEventType;
        this.state = state;
        this.source = (state != null) ? state.getCs() : null;
        this.instance = instance;
        this.exception = exception;
        this.guard = guard;
    }

    private CRCLServerSocketEvent(CRCLServerSocketEventType crclServerSocketEventType, @Nullable Class<STATE_TYPE> state_clzz, @Nullable CRCLCommandInstanceType instance, @Nullable Exception exception, @Nullable GuardType guard) {
        this.eventType = crclServerSocketEventType;
        this.state = null;
        this.source = null;
        this.instance = instance;
        this.exception = exception;
        this.guard = guard;
    }

    @Override
    public String toString() {
        String instanceString;
        if (null != instance && null != source) {
            try {
                instanceString = ",instance=" + source.cmdToString(instance);
            } catch (Exception ex) {
                instanceString = "";
            }
        } else {
            instanceString = "";
        }
        return "CRCLServerSocketEvent{" + "eventType=" + eventType + ", state=" + state + ", source=" + source + instanceString + ", exception=" + exception + '}';
    }

    public static <STATE_TYPE extends CRCLServerClientState> CRCLServerSocketEvent<STATE_TYPE> newClient(STATE_TYPE state) {
        return new CRCLServerSocketEvent<>(CRCLServerSocketEventType.NEW_CRCL_CLIENT, state, null, null, null);
    }

    public static <STATE_TYPE extends CRCLServerClientState> CRCLServerSocketEvent<STATE_TYPE> commandRecieved(STATE_TYPE state, CRCLCommandInstanceType command) {
        return new CRCLServerSocketEvent<>(CRCLServerSocketEventType.CRCL_COMMAND_RECIEVED, state, command, null, null);
    }

    public static <STATE_TYPE extends CRCLServerClientState> CRCLServerSocketEvent<STATE_TYPE> exceptionOccured(STATE_TYPE state, Exception ex) {
        return new CRCLServerSocketEvent<>(CRCLServerSocketEventType.EXCEPTION_OCCURRED, state, null, ex, null);
    }

    public static <STATE_TYPE extends CRCLServerClientState> CRCLServerSocketEvent<STATE_TYPE> badGuardSensorId(STATE_TYPE state, CRCLCommandInstanceType commandInstance, GuardType guard) {
        return new CRCLServerSocketEvent<>(CRCLServerSocketEventType.BAD_GUARD_SENSOR_ID, state, commandInstance, null, guard);
    }

    public static <STATE_TYPE extends CRCLServerClientState> CRCLServerSocketEvent<STATE_TYPE> guardLimitReached(STATE_TYPE state, CRCLCommandInstanceType commandInstance, GuardType guard) {
        return new CRCLServerSocketEvent<>(CRCLServerSocketEventType.GUARD_LIMIT_REACHED, state, commandInstance, null, guard);
    }

    public static <STATE_TYPE extends CRCLServerClientState> CRCLServerSocketEvent<STATE_TYPE> serverClosed(Class<STATE_TYPE> clzz) {
        return new CRCLServerSocketEvent<STATE_TYPE>(CRCLServerSocketEventType.SERVER_CLOSED, clzz, null, null, null);
    }
}
