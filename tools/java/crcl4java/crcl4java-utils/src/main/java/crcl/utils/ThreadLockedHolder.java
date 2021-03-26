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

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class ThreadLockedHolder<T> implements Supplier<T> {

    private final T object;
    private final String name;

    private final StackTraceElement createTrace[];
    private volatile StackTraceElement lockTrace  @Nullable [];
    private volatile StackTraceElement unlockTrace @Nullable [];
    private volatile @Nullable Thread lockThread;
    private final AtomicInteger getCountAI = new AtomicInteger();
    private final AtomicInteger releaseCountAI = new AtomicInteger();

    public ThreadLockedHolder(String name, T object) {
        this.name = name;
        this.object = object;
        this.createTrace = Thread.currentThread().getStackTrace();
        this.lockTrace = createTrace;
        this.lockThread = Thread.currentThread();
        this.unlockTrace = null;
        this.disabled = false;
    }

    public ThreadLockedHolder(String name, T object, boolean locked) {
        this.name = name;
        this.object = object;
        this.createTrace = Thread.currentThread().getStackTrace();
        if (locked) {
            this.lockTrace = createTrace;
            this.lockThread = Thread.currentThread();
            this.unlockTrace = null;
        } else {
            this.lockThread = null;
        }
        this.disabled = false;
    }

    private final boolean disabled;

    public ThreadLockedHolder(String name, T object, boolean locked, boolean disabled) {
        this.name = name;
        this.object = object;
        this.createTrace = Thread.currentThread().getStackTrace();
        if (locked) {
            this.lockTrace = createTrace;
            this.lockThread = Thread.currentThread();
            this.unlockTrace = null;
        } else {
            this.lockThread = null;
        }
        this.disabled = disabled;

    }

    @Override
    public T get() {
        getCountAI.incrementAndGet();
        if (null == lockThread) {
            lockThread = Thread.currentThread();
            this.lockTrace = Thread.currentThread().getStackTrace();
        } else if (lockThread != Thread.currentThread() && !disabled) {
            System.out.println("");
            System.out.flush();
            System.err.println("");
            System.err.flush();
            Thread.dumpStack();
            System.err.println("");
            System.err.flush();
            System.out.println("");
            System.out.flush();
            final String errMessage = "object named " + name + " accessed from " + Thread.currentThread() + " when locked to " + lockThread;
            System.out.println("");
            System.out.println("ThreadLocked: " + errMessage);
            System.out.println("ThreadLocked: object = " + object);
            System.out.println("ThreadLocked: getCountAI = " + getCountAI.get());
            System.out.println("ThreadLocked: releaseCountAI = " + releaseCountAI.get());
            System.out.println("ThreadLocked: createTrace = " + XFuture.traceToString(createTrace));
            System.out.println("ThreadLocked: lockTrace = " + XFuture.traceToString(lockTrace));
            System.out.println("ThreadLocked: unlockTrace = " + XFuture.traceToString(unlockTrace));
            System.out.println("ThreadLocked: Thread.currentThread() = " + Thread.currentThread());
            System.out.println("ThreadLocked: lockThread = " + lockThread);
            System.out.println("");
            System.out.flush();
            System.err.println("");
            System.err.flush();
            Thread.dumpStack();
            System.out.println("");
            System.out.flush();
            System.err.println("");
            System.err.flush();
            throw new RuntimeException(errMessage);
        }
        return object;
    }

    public void releaseLockThread() {
        if(null == object) {
            throw new NullPointerException("object");
        }
        synchronized (object) {
            releaseCountAI.incrementAndGet();
            lockThread = null;
            unlockTrace = Thread.currentThread().getStackTrace();
        }
    }
}
