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

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class XFutureVoid extends XFuture<Void> {

    public XFutureVoid(String name) {
        super(name);
    }

    public static XFutureVoid completedFutureWithName(String name) {
        XFutureVoid retXFV = new XFutureVoid(name);
        retXFV.complete(null);
        return retXFV;
    }

    public static XFutureVoid completedFuture() {
        XFutureVoid retXFV = new XFutureVoid("completedFuture( )");
        retXFV.complete(null);
        return retXFV;
    }

    public boolean complete() {
        return super.complete(null);
    }

    private void alsoCancelAddAll(Iterable<? extends CompletableFuture<?>> cfs) {
        for (CompletableFuture<?> cf : cfs) {
            alsoCancelAdd(cf);
        }
    }

    public static XFutureVoid runAsync(String name, Runnable r) {
        return runAsync(name, r, getDefaultThreadPool());
    }

    @SuppressWarnings("unchecked")
    public static XFutureVoid runAsync(String name, Runnable r, ExecutorService es) {
        XFutureVoid myf = new XFutureVoid(name);
        Future<?> f = es.submit(() -> {
            try {
                String tname = Thread.currentThread().getName();
                int cindex = tname.indexOf(':');
                String tname_sub = tname;
                if (cindex > 0) {
                    tname_sub = tname.substring(0, cindex);
                }
                Thread.currentThread().setName(tname_sub + ":" + name);
                r.run();
                Thread.currentThread().setName(tname);
            } catch (Throwable throwable) {
                myf.completeExceptionally(throwable);
                String errMsg = "Exception in XFutureVoid  " + myf.forExceptionString();
//                System.out.println("errMsg = " + errMsg);
//                System.err.println(errMsg);
                LOGGER.log(Level.SEVERE, errMsg, throwable);
                if (throwable instanceof RuntimeException) {
                    throw (RuntimeException) throwable;
                } else {
                    throw new PrintedException(throwable);
                }
            }
            myf.complete(null);
//            myf.alsoCancel.clear();
        });
        myf.setFutureFromExecSubmit((Future<Void>) f);
        return myf;
    }
    private static final Logger LOGGER = Logger.getLogger(XFuture.class.getName());

    public <U> XFuture<U> thenCompose(String name, Supplier< ? extends CompletionStage<U>> supplier) {
        return super.thenCompose(name, c -> supplier.get());
    }

    public <U> XFuture<U> thenCompose(Supplier< ? extends CompletionStage<U>> supplier) {
        return this.thenCompose(defaultName(), supplier);
    }

    public <U> XFuture<U> thenComposeAsync(String name, Supplier< ? extends CompletionStage<U>> supplier) {
        return super.thenComposeAsync(name, c -> supplier.get());
    }

    public <U> XFuture<U> thenComposeAsync(Supplier< ? extends CompletionStage<U>> supplier) {
        return this.thenComposeAsync(defaultName(), supplier);
    }

    public <U> XFuture<U> thenComposeAsync(String name, Supplier< ? extends CompletionStage<U>> supplier, ExecutorService es) {
        return super.thenComposeAsync(name, c -> supplier.get(), es);
    }

    public <U> XFuture<U> thenComposeAsync(Supplier< ? extends CompletionStage<U>> supplier, ExecutorService es) {
        return this.thenComposeAsync(defaultName(), supplier, es);
    }

    public XFutureVoid thenComposeToVoid(String name, Supplier< ? extends XFuture<Void>> supplier) {
        XFuture<Void> f = super.thenCompose(name, c -> supplier.get());
        XFutureVoid retXFV = staticwrapvoid(f.getName(), f);
        retXFV.alsoCancelAdd(f);
        return retXFV;
    }

    public XFutureVoid thenComposeToVoid(Supplier< ? extends XFuture<Void>> supplier) {
        return this.thenComposeToVoid(defaultName(), supplier);
    }

    public XFutureVoid thenComposeAsyncToVoid(String name, Supplier< ? extends XFuture<Void>> supplier) {
        XFuture<Void> future = this.thenComposeAsync(name, x -> supplier.get());
        XFutureVoid retXFV = new XFutureVoid(name);
        setupFutureHandling(future, retXFV);
        retXFV.alsoCancelAdd(future);
        return retXFV;
    }

    private void setupFutureHandling(XFuture<Void> future, XFutureVoid retXFV) {
        XFuture<Void> handledFuture = future.handle(createVoidHandleBiFunction(retXFV));
        retXFV.alsoCancelAdd(handledFuture);
    }

    public XFutureVoid thenComposeAsyncToVoid(Supplier< ? extends XFuture<Void>> supplier) {
        return this.thenComposeAsyncToVoid(defaultName(), supplier);
    }

    public XFutureVoid thenComposeAsyncToVoid(String name, Supplier< ? extends XFuture<Void>> supplier, ExecutorService es) {
        XFuture<Void> future = this.thenComposeAsync(name, x -> supplier.get(), es);
        XFutureVoid retXFV = new XFutureVoid(name);
        setupFutureHandling(future, retXFV);
        retXFV.alsoCancelAdd(future);
        return retXFV;
    }

    public XFutureVoid thenComposeAsyncToVoid(Supplier< ? extends XFuture<Void>> supplier, ExecutorService es) {
        return this.thenComposeAsyncToVoid(defaultName(), supplier, es);
    }

    @Override
    public XFutureVoid alwaysRun(Runnable r) {
        return this.alwaysRun(defaultName(), r);
    }

    public <U> XFuture<U> alwaysComposeToOutput(Supplier<? extends CompletionStage<U>> supplier) {
        return this.alwaysComposeToOutput(defaultName(), supplier);
    }

    public <U> XFuture<U> alwaysComposeToOutput(String name, Supplier<? extends CompletionStage<U>> supplier) {
        return super.alwaysCompose(name, supplier)
                .thenApply((ComposedPair<Void, U> pair) -> pair.getOutput());
    }

    public <U> XFuture<U> alwaysComposeAsyncToOutput(Supplier<? extends CompletionStage<U>> supplier, ExecutorService service) {
        return this.alwaysComposeToOutput(defaultName(), supplier);
    }

    public <U> XFuture<U> alwaysComposeAsyncToOutput(String name, Supplier<? extends CompletionStage<U>> supplier, ExecutorService service) {
        return super.alwaysComposeAsync(name, supplier, service)
                .thenApply((ComposedPair<Void, U> pair) -> pair.getOutput());
    }

    @Override
    public XFutureVoid alwaysRun(String name, Runnable r) {
        return staticwrapvoid(name, super.handle((x, t) -> {
            try {
                r.run();
            } catch (Throwable t2) {
                LOGGER.log(Level.SEVERE, "Exception in XFutureVoid  " + XFutureVoid.this.toString(), t2);
                if (null == t) {
                    if (t2 instanceof RuntimeException) {
                        throw ((RuntimeException) t2);
                    } else {
                        throw new RuntimeException(t2);
                    }
                }
            }
            if (null != t) {
                if (t instanceof RuntimeException) {
                    throw ((RuntimeException) t);
                } else {
                    throw new RuntimeException(t);
                }
            }
            return x;
        }));
    }

    @Override
    public XFutureVoid alwaysComposeToVoid(Supplier<XFutureVoid> supplier) {
        return this.alwaysComposeToVoid(defaultName(), supplier);
    }

    @Override
    public XFutureVoid alwaysComposeToVoid(String name, Supplier<XFutureVoid> supplier) {
        XFutureVoid retXFV = new XFutureVoid(name);
        XFutureVoid orig
                = staticwrapvoid(name, super.handle((x, t) -> {
                    try {
                        supplier.get()
                                .thenRun(() -> {
                                    if (null != t) {
                                        retXFV.completeExceptionally(t);
                                        if (t instanceof RuntimeException) {
                                            throw ((RuntimeException) t);
                                        } else {
                                            throw new RuntimeException(t);
                                        }
                                    }
                                    retXFV.complete(x);
                                });
                    } catch (Throwable t2) {
                        LOGGER.log(Level.SEVERE, "Exception in XFutureVoid  " + XFutureVoid.this.toString(), t2);
                        if (null == t) {
                            if (t2 instanceof RuntimeException) {
                                throw ((RuntimeException) t2);
                            } else {
                                throw new RuntimeException(t2);
                            }
                        }
                    }
                    if (null != t) {
                        if (t instanceof RuntimeException) {
                            throw ((RuntimeException) t);
                        } else {
                            throw new RuntimeException(t);
                        }
                    }
                    return x;
                }));
        retXFV.alsoCancelAdd(orig);
        return retXFV;
    }

    @Override
    public XFutureVoid alwaysRunAsync(Runnable r, ExecutorService service) {
        return this.alwaysRunAsync(defaultName(), r, service);
    }

    @Override
    public XFutureVoid alwaysRunAsync(String name, Runnable r, ExecutorService service) {
        return staticwrapvoid(name, super.handleAsync((x, t) -> {
            try {
                r.run();
            } catch (Throwable t2) {
                LOGGER.log(Level.SEVERE, "Exception in XFutureVoid  " + XFutureVoid.this.toString(), t2);
                if (null == t) {
                    if (t2 instanceof RuntimeException) {
                        throw ((RuntimeException) t2);
                    } else {
                        throw new RuntimeException(t2);
                    }
                }
            }
            if (null != t) {
                if (t instanceof RuntimeException) {
                    throw ((RuntimeException) t);
                } else {
                    throw new RuntimeException(t);
                }
            }
            return x;
        }, service));
    }

    @Override
    public XFutureVoid alwaysComposeAsyncToVoid(Supplier<XFutureVoid> supplier, ExecutorService service) {
        return alwaysComposeAsyncToVoid(defaultName(), supplier, service);
    }

    @Override
    public XFutureVoid alwaysComposeAsyncToVoid(String name, Supplier<XFutureVoid> supplier, ExecutorService service) {
        XFutureVoid retXFV = new XFutureVoid(name);
        XFutureVoid orig
                = staticwrapvoid(retXFV.getName() + ".wrap",
                        super.handleAsync((x, t) -> {
                            try {
                                supplier.get()
                                        .thenRun(() -> {
                                            if (null != t) {
                                                retXFV.completeExceptionally(t);
                                                if (t instanceof RuntimeException) {
                                                    throw ((RuntimeException) t);
                                                } else {
                                                    throw new RuntimeException(t);
                                                }
                                            }
                                            retXFV.complete(x);
                                        });
                            } catch (Throwable t2) {
                                LOGGER.log(Level.SEVERE, "Exception in XFutureVoid  " + XFutureVoid.this.toString(), t2);
                                if (null == t) {
                                    if (t2 instanceof RuntimeException) {
                                        throw ((RuntimeException) t2);
                                    } else {
                                        throw new RuntimeException(t2);
                                    }
                                }
                            }
                            if (null != t) {
                                if (t instanceof RuntimeException) {
                                    throw ((RuntimeException) t);
                                } else {
                                    throw new RuntimeException(t);
                                }
                            }
                            return x;
                        }, service));
        retXFV.alsoCancelAdd(orig);
        return retXFV;
    }

    @Override
    public XFutureVoid thenRun(Runnable r) {
        XFuture<Void> f = super.thenRun(r);
        if (f == this) {
            System.out.println("f = " + f);
            XFuture<Void> f2 = super.thenRun(r);
            System.out.println("f2 = " + f2);
        }
        return staticwrapvoid(f.getName(), f);
    }

    @Override
    public XFutureVoid thenRun(String name, Runnable r) {
        XFuture<Void> f = super.thenRun(name, r);
        return staticwrapvoid(f.getName(), f);
    }

    @Override
    public XFutureVoid thenRunAsync(Runnable r) {
        XFuture<Void> f = super.thenRunAsync(r);
        return staticwrapvoid(f.getName(), f);
    }

    @Override
    public XFutureVoid thenRunAsync(String name, Runnable r) {
        XFuture<Void> f = super.thenRunAsync(name, r);
        return staticwrapvoid(f.getName(), f);
    }

    @Override
    public XFutureVoid thenRunAsync(String name, Runnable r, ExecutorService es) {
        XFuture<Void> f = super.thenRunAsync(name, r, es);
        return staticwrapvoid(f.getName(), f);
    }

    public <U> XFuture<U> thenSupply(Supplier<U> supplier) {
        return super.thenApply(x -> supplier.get());
    }

    public <U> XFuture<U> thenSupply(String name, Supplier<U> supplier) {
        return super.thenApply(name, x -> supplier.get());
    }

    public <U> XFuture<U> thenSupplyAsync(Supplier<U> supplier) {
        return super.thenApplyAsync(x -> supplier.get());
    }

    public <U> XFuture<U> thenSupplyAsync(String name, Supplier<U> supplier) {
        return super.thenApplyAsync(name, x -> supplier.get());
    }

    public <U> XFuture<U> thenSupplyAsync(String name, Supplier<U> supplier, ExecutorService es) {
        return super.thenApplyAsync(name, x -> supplier.get(), es);
    }

    private static XFutureVoid staticwrapvoid(String name, CompletableFuture<Void> future) {
        if (future instanceof XFutureVoid) {
            return (XFutureVoid) future;
        }
        XFutureVoid newFuture = new XFutureVoid(name);
        future.handle((x, t) -> hiddenHandler(x, t, future, newFuture));
        newFuture.alsoCancelAdd(future);
        return newFuture;
    }

    static private <T> T hiddenHandler(T retValue, Throwable thrown, CompletableFuture<T> future, XFutureVoid newFuture) {
        if (null != thrown) {
            if (thrown instanceof PrintedXFutureRuntimeException) {
                newFuture.completeExceptionally(thrown);
                throw ((PrintedXFutureRuntimeException) thrown);
            } else if (thrown instanceof Error) {
                newFuture.completeExceptionally(thrown);
                throw ((Error) thrown);
            } else {
                PrintStream ps = XFuture.logExceptionPrintStream;
                if (null != ps && !XFuture.closingMode) {
                    Throwable cause = thrown.getCause();
                    boolean isCancellationException
                            = XFuture.isPrintedOrCancellationException(thrown);
                    if (!isCancellationException || XFuture.printCancellationExceptions) {
                        ps.println("future=" + future);
                        ps.println("Exception " + thrown + " passed through XFuture : " + newFuture.getName());
                        thrown.printStackTrace(ps);

                        if (null != cause) {
                            ps.println("cause=");
                            cause.printStackTrace(ps);
                        }
                        ps.println("XFuture.forExceptionString() = " + newFuture.forExceptionString());

                    }
                }
                newFuture.completeExceptionally(thrown);
                throw new PrintedXFutureRuntimeException(thrown);
            }
        }
        if (future.isCancelled()) {
            newFuture.cancelAll(false);
        } else {
            newFuture.complete();
        }
        return retValue;
    }

    public static XFutureVoid allOfWithName(String name, CompletableFuture<?>... cfs) {
        for (int i = 0; i < cfs.length; i++) {
            CompletableFuture<?> cf = cfs[i];
            if(null == cf) {
                throw new RuntimeException("name="+name+", cfs["+i+"] is null");
            }
        }
        CompletableFuture<Void> orig = CompletableFuture.allOf(cfs);
        XFutureVoid retXFV = staticwrapvoid(name, orig);
        if (retXFV != orig) {
            retXFV.alsoCancelAddAll(Arrays.asList(cfs));
        }
        return retXFV;
    }

    public static XFutureVoid allOf(CompletableFuture<?>... cfs) {
        for (int i = 0; i < cfs.length; i++) {
            CompletableFuture<?> cf = cfs[i];
            if(null == cf) {
                throw new RuntimeException("cfs["+i+"] is null");
            }
        }
        CompletableFuture<Void> orig = CompletableFuture.allOf(cfs);
        XFutureVoid retXFV = staticwrapvoid("XFutureVoid.allOf.cfs.length=" + cfs.length, orig);
        if (retXFV != orig) {
            retXFV.alsoCancelAddAll(Arrays.asList(cfs));
        }
        return retXFV;
    }

    public XFutureVoid peekException(Consumer<Throwable> consumer) {
        Function<Throwable, Void> func = (Throwable throwable1) -> {
            if (null != throwable1) {
                consumer.accept(throwable1);
                if (throwable1 instanceof RuntimeException) {
                    throw (RuntimeException) throwable1;
                } else {
                    throw new RuntimeException(throwable1);
                }
            }
            return (Void) null;
        };
        XFuture<Void> future = super.exceptionally(func);
        return staticwrapvoid(future.getName(), future);
    }

    @Override
    public XFutureVoid peekNoCancelException(Consumer<Throwable> consumer) {
        Function<Throwable, Void> func = (Throwable throwable1) -> {
            if (null != throwable1) {
                if (!(throwable1 instanceof CancellationException)) {
                    Throwable cause = throwable1.getCause();
                    if (!(cause instanceof CancellationException)) {
                        consumer.accept(throwable1);
                    }
                }
                if (throwable1 instanceof RuntimeException) {
                    throw (RuntimeException) throwable1;
                } else {
                    throw new RuntimeException(throwable1);
                }
            }
            return (Void) null;
        };
        XFuture<Void> future = super.exceptionally(func);
        return staticwrapvoid(future.getName(), future);
    }

    @SuppressWarnings("rawtypes")
    public static XFutureVoid allOf(Collection<? extends CompletableFuture<?>> cfsCollection) {
        return allOf(cfsCollection.toArray(new CompletableFuture[0]));
    }

    @SuppressWarnings("rawtypes")
    public static XFutureVoid allOfWithName(String name, Collection<? extends CompletableFuture<?>> cfsCollection) {
        return allOfWithName(name, cfsCollection.toArray(new CompletableFuture[0]));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static XFutureVoid anyOfWithName(String name, XFutureVoid... cfs) {
        CompletableFuture orig = CompletableFuture.anyOf(cfs);
        XFutureVoid retXFV = staticwrapvoid(name, orig);
        if (retXFV != orig) {
            retXFV.alsoCancelAddAll(Arrays.asList(cfs));
        }
        return retXFV;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static XFutureVoid anyOf(XFutureVoid... cfs) {
        CompletableFuture orig = CompletableFuture.anyOf(cfs);
        XFutureVoid retXFV = staticwrapvoid("anyOfWithName", orig);
        retXFV.alsoCancelAddAll(Arrays.asList(cfs));
        return retXFV;
    }
}
