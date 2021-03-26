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

import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLProgramType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStatusType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.MiddleCommandType;
import crcl.base.PointType;
import crcl.base.PoseType;
import crcl.base.VectorType;
import static crcl.utils.CRCLSocket.getUtilSocket;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import static java.util.Objects.requireNonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class CRCLUtils {

    private CRCLUtils() {
    }

    /**
     * Convert an array returned from Thread.currentThread().getStackTrace() or
     * exception.getStackTrace() to a readable loggable string.
     *
     * @param trace array of stack trace elements
     * @return new string representation
     */
    public static String traceToString(StackTraceElement trace @Nullable []) {
        if (null == trace) {
            return "";
        }
        try (StringWriter stringWriter = new StringWriter()) {
            try (PrintWriter printWriter = new PrintWriter(stringWriter)) {
                boolean first = true;
                for (StackTraceElement traceElement : trace) {
                    String traceString = traceElement.toString();
                    if (first && traceString.contains("Thread.getStackTrace(")) {
                        first = false;
                        continue;
                    }
                    first = false;
                    printWriter.println("\tat " + traceElement);
                }
            }
            stringWriter.flush();
            return stringWriter.toString();
        } catch (IOException ex) {

            throw new RuntimeException(ex);
        }
    }

    /**
     * Get the user's home directory unless it has been overridden with a system
     * property "crcl.user.home", "windows.crcl.user.home" or
     * "linux.crcl.user.home".
     *
     * @return home directory path string or property override value
     */
    public static String getCrclUserHomeDir() {
        boolean isWindows = System.getProperty("os.name").startsWith("Windows");

        String dir;
        if (isWindows) {
            dir = System.getProperty("windows.crcl.user.home", System.getProperty("crcl.user.home", System.getProperty("user.home")));
        } else {
            dir = System.getProperty("linux.crcl.user.home", System.getProperty("crcl.user.home", System.getProperty("user.home")));
        }
        return dir;
    }

    /**
     * Read a CRCL Status from a File with the given file.
     *
     * @param f File to read
     * @return CRCLStatus read from file.
     * @throws CRCLException file is not valid CRCLStatus
     * @throws IOException unable to read from file
     */
    public static CRCLStatusType readStatusFile(File f) throws CRCLException, IOException {
        return readStatusFile(f.toPath());
    }

    /**
     * Read a CRCL Status from a File with the given path.
     *
     * @param p Path to file to read
     * @return CRCLStatus read from file.
     * @throws CRCLException file is not valid CRCLStatus
     * @throws IOException unable to read from file
     */
    public static CRCLStatusType readStatusFile(Path p) throws CRCLException, IOException {
        CRCLSocket cs = getUtilSocket();
        String str = new String(Files.readAllBytes(p), StandardCharsets.UTF_8);
        synchronized (cs) {
            return cs.stringToStatus(str, true);
        }
    }

    /**
     * Read a CRCL Program from a File with the given file.
     *
     * @param f File to read
     * @return CRCLProgram read from file.
     * @throws CRCLException file is not valid CRCLProgram
     * @throws IOException unable to read from file
     */
    public static CRCLProgramType readProgramFile(File f) throws CRCLException, IOException {
        return readProgramFile(f.toPath());
    }

    /**
     * Read a CRCL Program from a File with the given path.
     *
     * @param p Path to file to read
     * @return CRCLProgram read from file.
     * @throws CRCLException file is not valid CRCLProgram
     * @throws IOException unable to read from file
     */
    public static CRCLProgramType readProgramFile(Path p) throws CRCLException, IOException {
        CRCLSocket cs = getUtilSocket();
        String str = new String(Files.readAllBytes(p), StandardCharsets.UTF_8);
        synchronized (cs) {
            return cs.stringToProgram(str, true);
        }
    }

    /**
     * Read a CRCL Program from a File with the given filename.
     *
     * @param filename name of file to read
     * @return CRCLProgram read from file.
     * @throws CRCLException file is not valid CRCLProgram
     * @throws IOException unable to read from file
     */
    public static CRCLProgramType readProgramFile(String filename) throws CRCLException, IOException {
        return readProgramFile(Paths.get(filename));
    }

    /**
     * Get a property and parse it as a long
     * @param name name of property
     * @param defaultValue value to be returned if property is not found
     * @return value of property
     */
    public static long getLongProperty(String name, long defaultValue) {
        String propVal = System.getProperty(name);
        if (propVal == null) {
            return defaultValue;
        }
        return Long.parseLong(propVal);
    }

//    public static @Nullable
//    JointStatusType getJointStatus(CRCLStatusType _status, BigInteger bi) {
//        return getJointStatus(_status, bi.intValue());
//    }
    /**
     * Get the joint status with the given joint number.
     *
     * @param _status status to get jointstatus field value from
     * @param jointNumber joint number being looked for
     * @return joint status with given number or null if one does not exist
     */
    public static @Nullable
    JointStatusType getJointStatus(CRCLStatusType _status, int jointNumber) {
        if (null == _status) {
            return null;
        }
        JointStatusesType jsst = _status.getJointStatuses();
        if (null == jsst) {
            return null;
        }
        Iterable<JointStatusType> jsl = getNonNullJointStatusIterable(jsst);
        if (null != jsl) {
            for (JointStatusType js : jsl) {
                if (js.getJointNumber() == jointNumber) {
                    return js;
                }
            }
        }
        return null;
    }

    private static <T> Iterator<T> createEmptyIterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                throw new NoSuchElementException("Created from null iterator");
            }
        };
    }

    @SuppressWarnings({"nullness", "initialization"})
    static private <T> Iterator<@NonNull T> createNoNullIterator(@Nullable Iterator<@Nullable T> iteratorIn) {
        return new Iterator<T>() {

            @Nullable
            T cachedNext = getNextNonNull(iteratorIn);

            private @Nullable
            T getNextNonNull(@Nullable Iterator<@Nullable T> _iteratorIn) {
                if (null == _iteratorIn) {
                    return null;
                }
                while (_iteratorIn.hasNext()) {
                    T n = _iteratorIn.next();
                    if (null != n) {
                        return n;
                    }
                }
                return null;
            }

            @Override
            public boolean hasNext() {
                return cachedNext != null;
            }

            @Override
            public T next() {
                T ret = this.cachedNext;
                if (null == ret) {
                    throw new NoSuchElementException();
                }
                cachedNext = getNextNonNull(iteratorIn);
                return ret;
            }
        };
    }

    /**
     * Create an iterable from one that may have null values to one where null values are
     * filtered out.
     *
     * @param <T> the type of elements returned by the iterator
     * @param itIn input iterable that may return null values
     * @return
     */
    @SuppressWarnings({"nullness", "initialization"})
    public static <T> Iterable<@NonNull T> getNonNullIterable(@Nullable Iterable<T> itIn) {
        if (null == itIn) {
            return createEmptyIterable();
        }
        Iterable<T> ret = createNoNullIterableCopy(itIn);
        return ret;
    }

//    @SuppressWarnings({"nullness", "initialization"})
//    public static <T> void clearAndSetList(@Nullable List<@Nullable T> list, @Nullable Collection<? extends T> collectionToAdd) {
//        if (null != list) {
//            list.clear();
//            if (null != collectionToAdd) {
//                list.addAll(collectionToAdd);
//            }
//        }
//    }

    /**
     * Create a new list from the set of elements taken from an iterable excluding null values.
     * @param <T> the type of elements returned by the iterator
     * @param itIn input iterable that may return null values
     * @return new list without null values
     */
    @SuppressWarnings({"nullness", "initialization"})
    public static <T> List<@NonNull T> getNonNullFilteredList(@Nullable Iterable<T> itIn) {
        if (null == itIn) {
            return Collections.emptyList();
        }
        Iterable<T> iterable = createNoNullIterableCopy(itIn);
        List<T> newList = new ArrayList<>();
        for (T el : iterable) {
            newList.add(el);
        }
        return newList;
    }

    /**
     * Get the command field from a command instance object or throw a more informative NullPointerException 
     * @param cmdInstance object to get field from
     * @return value of CRCLCommand field if not null
     */
    static public CRCLCommandType getNonNullCmd(CRCLCommandInstanceType cmdInstance) throws NullPointerException {
        return requireNonNull(
                requireNonNull(cmdInstance, "cmdInstance")
                        .getCRCLCommand(),
                "getCRCLCommand()"
        );
    }

    /**
     * Get the command status field from a status object or throw a more informative NullPointerException 
     * @param stat object to get field from
     * @return command status field if not null
     */
    public static CommandStatusType getNonNullCommandStatus(CRCLStatusType stat) throws NullPointerException {
        return requireNonNull(
                requireNonNull(stat, "stat")
                        .getCommandStatus(),
                "getCommandStatus()"
        );
    }

    /**
     * Get the zaxis field from a pose object or throw a more informative NullPointerException 
     * @param pose object to get field from
     * @return zaxis field if not null
     */
    public static VectorType getNonNullZAxis(PoseType pose) {
        return requireNonNull(
                requireNonNull(pose, "pose")
                        .getZAxis(),
                "getZAxis"
        );
    }

    /**
     * Get the xaxis field from a pose object or throw a more informative NullPointerException 
     * @param pose object to get field from
     * @return xaxis field if not null
     */
    public static VectorType getNonNullXAxis(PoseType pose) {
        return requireNonNull(
                requireNonNull(pose, "pose").
                        getXAxis(),
                "getXAxis()"
        );
    }

    /**
     * Get the point field from a pose object or throw a more informative NullPointerException 
     * @param pose object to get field from
     * @return point field if not null
     */
    public static PointType getNonNullPoint(PoseType pose) {
        return requireNonNull(requireNonNull(pose, "pose").getPoint(), "getPoint()");
    }

    @SuppressWarnings({"nullness", "initialization"})
    private static <T> Iterable<@NonNull T> createNoNullIterableCopy(Iterable<@Nullable T> itIn) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                Iterator<@Nullable T> iteratorIn = itIn.iterator();
                Iterator<T> iteratorOut = createNoNullIterator(iteratorIn);
                return iteratorOut;
            }
        };
    }

    private static <T> Iterable<T> createEmptyIterable() {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return createEmptyIterator();
            }
        };
    }

    /**
     * Get the middleCommands field from a program object or throw a more informative NullPointerException 
     * @param prog object to get field from
     * @return middleCommands field if not null
     */
    @SuppressWarnings("nullness")
    public static List<MiddleCommandType> middleCommands(CRCLProgramType prog) {
        return requireNonNull(
                requireNonNull(prog, "prog")
                        .getMiddleCommand(),
                "getMiddleCommand()"
        );
    }

    /**
     * Get an iterable for JointStatus with null values filtered out.
     * 
     * @param jsst object with joint status list field
     * @return new iterable from list or throw NullPointerException with more informative message.
     */
    public static Iterable<JointStatusType> getNonNullJointStatusIterable(JointStatusesType jsst) {
        return getNonNullIterable(
                requireNonNull(
                        requireNonNull(jsst, "jsst")
                                .getJointStatus(),
                        "getJointStatus()"
                )
        );
    }

}
