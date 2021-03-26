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

import crcl.base.CRCLProgramType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStateEnumType;
import crcl.base.CommandStatusType;
import crcl.base.GripperStatusType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.MoveToType;
import crcl.base.ParallelGripperStatusType;
import crcl.base.PointType;
import crcl.base.PoseStatusType;
import crcl.base.PoseType;
import crcl.base.TwistType;
import crcl.base.VectorType;
import crcl.base.WrenchType;
import crcl.utils.CRCLCopier;
import crcl.utils.CRCLUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rcs.posemath.PmCartesian;
import rcs.posemath.PmPose;
import rcs.posemath.PmQuaternion;
import rcs.posemath.PmRotationMatrix;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
@SuppressWarnings({"nullness"})
public class CRCLCopierTest {

    public CRCLCopierTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    private PointType pt123 = null;
    private PointType pt321 = null;
    private PmCartesian cart123 = null;
    private PmCartesian cart321 = null;
    private VectorType xvec = null;
    private VectorType yvec = null;
    private VectorType zvec = null;
    private PoseType pose123 = null;
    private PoseType pose321 = null;
    private PoseType pose321rot90 = null;
    private PmPose pmPose123 = null;

    private static final double DOUBLE_1 = 1.0;
    private static final double DOUBLE_2 = 2.0;
    private static final double DOUBLE_3 = 3.0;
    private static final double DOUBLE_4 = 4.0;

    @Before
    public void setUp() {
        pt123 = new PointType();
        pt123.setX(DOUBLE_1);
        pt123.setY(DOUBLE_2);
        pt123.setZ(DOUBLE_3);

        pt321 = new PointType();
        pt321.setX(DOUBLE_3);
        pt321.setY(DOUBLE_2);
        pt321.setZ(DOUBLE_1);

        xvec = new VectorType();
        xvec.setI(1.0);
        xvec.setJ(0.0);
        xvec.setK(0.0);

        yvec = new VectorType();
        yvec.setI(0.0);
        yvec.setJ(1.0);
        yvec.setK(0.0);

        zvec = new VectorType();
        zvec.setI(0.0);
        zvec.setJ(0.0);
        zvec.setK(1.0);

        pose123 = new PoseType();
        pose123.setPoint(pt123);
        pose123.setXAxis(xvec);
        pose123.setZAxis(zvec);

        pose321 = new PoseType();
        pose321.setPoint(pt321);
        pose321.setXAxis(xvec);
        pose321.setZAxis(zvec);

        pose321rot90 = new PoseType();
        pose321rot90.setPoint(pt321);
        pose321rot90.setXAxis(yvec);
        pose321rot90.setZAxis(zvec);

        cart123 = new PmCartesian(1.0, 2.0, 3.0);
        cart321 = new PmCartesian(3.0, 2.0, 1.0);
    }

    @After
    public void tearDown() {
    }

    static final private double ASSERT_TOLERANCE_DELTA = 1e-6;

    private void checkEquals(String msg, double v1, double v2) {
        assertEquals(msg, v1, v2, ASSERT_TOLERANCE_DELTA);
    }

    private void checkEquals(String msg, BigDecimal v1, double v2) {
        assertEquals(msg, v1.doubleValue(), v2, ASSERT_TOLERANCE_DELTA);
    }

    private void checkEquals(String msg, double v1, BigDecimal v2) {
        assertEquals(msg, v1, v2.doubleValue(), ASSERT_TOLERANCE_DELTA);
    }

    private void checkEquals(String msg, BigDecimal v1, BigDecimal v2) {
        assertTrue(msg + " both are null or neither is null", (v1 == null) == (v2 == null));
        if (v1 == null) {
            return;
        }
        checkEquals(msg, v1.doubleValue(), v2.doubleValue());
    }

    private void checkEquals(String msg, PmCartesian cart1, PmCartesian cart2) {
        checkEquals(msg + ".x", cart1.x, cart2.x);
        checkEquals(msg + ".y", cart1.y, cart2.y);
        checkEquals(msg + ".z", cart1.z, cart2.z);
    }

    private void checkEquals(String msg, PmQuaternion quat1, PmQuaternion quat2) {
        checkEquals(msg + ".s", quat1.s, quat2.s);
        checkEquals(msg + ".x", quat1.x, quat2.x);
        checkEquals(msg + ".y", quat1.y, quat2.y);
        checkEquals(msg + ".z", quat1.z, quat2.z);
    }

    private void checkEquals(String msg, PmPose p1, PmPose p2) {
        checkEquals(msg + ".tran", p1.tran, p2.tran);
        checkEquals(msg + ".rot", p1.rot, p2.rot);
    }

    private void checkEquals(String msg, PmRotationMatrix cart1, PmRotationMatrix cart2) {
        checkEquals(msg + ".x", cart1.x, cart2.x);
        checkEquals(msg + ".y", cart1.y, cart2.y);
        checkEquals(msg + ".z", cart1.z, cart2.z);
    }

    private void checkEquals(String msg, PointType pt1, PointType pt2) {
        checkEquals(msg + ".getX()", pt1.getX(), pt2.getX());
        checkEquals(msg + ".getY()", pt1.getY(), pt2.getY());
        checkEquals(msg + ".getZ()", pt1.getZ(), pt2.getZ());
    }

    private void checkEquals(String msg, VectorType v1, VectorType v2) {
        checkEquals(msg + ".getI()", v1.getI(), v2.getI());
        checkEquals(msg + ".getJ()", v1.getJ(), v2.getJ());
        checkEquals(msg + ".getK()", v1.getK(), v2.getK());
    }

    private void checkEquals(String msg, PoseType pose1, PoseType pt2) {
        checkEquals(msg + ".getPoint()", pose1.getPoint(), pt2.getPoint());
        checkEquals(msg + ".getXAxis()", pose1.getXAxis(), pt2.getXAxis());
        checkEquals(msg + ".getZAxis()", pose1.getZAxis(), pt2.getZAxis());
    }

    static final String RANDOM_STRING
            = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final List<String> staticIncludedPathStrings
            = Arrays.asList(new String[]{
        "target/classes",
        "target\\classes",
        "wshackle",
        "rcslib",
        "crcl",
        "aprs",});
    private static final List<String> staticExcludedPathStrings
            = Arrays.asList(new String[]{
        "vaadin",
        "google",
        "apache",
        "commons-io",
        "commons-math",
        "xerces",
        "exificient",
        "activation",
        "jaxb-impl",
        "checker-qual",
        "checker-compat-qual",
        "javassist",
        "xstream",
        "logback",
        "drools",
        "eclipse-collections",
        "jSerialComm",
        "ATINetFT"
    });

    private static boolean containsStringInCollection(String inputString, Collection<String> collection) {
        for (String collectionElement : collection) {
            if (inputString.contains(collectionElement)) {
                return true;
            }
        }
        return false;
    }

    static public List<Class<?>> getClasses(List<String> customExcludedPathStrings) {
        String name = "";
        File jar = null;
        List<Class<?>> classes = new ArrayList<>();
        try {
            final String[] classpaths = System.getProperty("java.class.path").split(System.getProperty("path.separator"));
            System.out.println("classpaths = " + Arrays.toString(classpaths));
            for (String classpathEntry : classpaths) {
                if (classpathEntry.endsWith(".jar")
                        && containsStringInCollection(classpathEntry, staticIncludedPathStrings)
                        && !containsStringInCollection(classpathEntry, staticExcludedPathStrings)
                        && !containsStringInCollection(classpathEntry, customExcludedPathStrings)) {
                    System.out.println("classpathEntry = " + classpathEntry);
                    JarInputStream is = null;
                    try {
                        jar = new File(classpathEntry);
                        is = new JarInputStream(new FileInputStream(jar));
                        JarEntry entry;
                        while ((entry = is.getNextJarEntry()) != null) {
//                            System.out.println("entry.getName() = " + entry.getName());
                            if (!entry.getName().startsWith("crcl/base/")) {
                                continue;
                            }
                            if (entry.getName().endsWith(".class")) {
                                name = entry.getName();
                                name = name.substring(0, name.length() - 6);
                                name = name.replaceAll("/", ".");
                                if (name.indexOf('$') >= 0) {
                                    continue;
                                }
                                Class<?> clss;
                                try {
                                    clss = Class.forName(name);
                                    if (!Modifier.isAbstract(clss.getModifiers())
                                            && !clss.isSynthetic()
                                            && !clss.isAnonymousClass()
                                            && !clss.isMemberClass()) {
                                        classes.add(clss);

                                    }
                                } catch (Throwable ex) {
                                    System.err.println("entry.getName() = " + entry.getName());
                                    System.err.println("entry.getName().startsWith(\"crcl\") = " + entry.getName().startsWith("crcl"));
                                    System.err.println("entry = " + entry);
                                    System.err.println("name = " + name);
                                    Logger.getLogger(CRCLPosemathTest.class
                                            .getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(CRCLPosemathTest.class
                                .getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            if (null != is) {
                                is.close();
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(CRCLPosemathTest.class
                                    .getName()).log(Level.SEVERE, "classpathEntry=" + classpathEntry, ex);
                        }
                    }
                } else {
                    File dir = new File(classpathEntry);
                    classes = addClasses("", dir, classes);
                }
            }
        } catch (Throwable t) {
            System.err.println("name = " + name);
            System.err.println("jar = " + jar);
            Logger.getLogger(CRCLPosemathTest.class
                    .getName()).log(Level.SEVERE, null, t);
        }
        return classes;
    }

    static private List<Class<?>> addClasses(String prefix, File dir, List<Class<?>> classes) {
        File fa[] = dir.listFiles();
        if (fa == null) {
            return classes;
        }
        for (File f : fa) {
            if (f.isDirectory()) {
                classes = addClasses(prefix + f.getName() + ".", f, classes);
            } else if (f.getName().endsWith(".class")) {
                String clssNameToLookup = "";
                try {
                    String name = f.getName();
                    name = name.substring(0, name.length() - 6);
                    if (name.indexOf('$') >= 0) {
                        continue;
                    }
                    clssNameToLookup = prefix + name;
                    Class<?> clss = Class.forName(clssNameToLookup);
                    if (!Modifier.isAbstract(clss.getModifiers())
                            && !clss.isSynthetic()
                            && !clss.isAnonymousClass()
                            && !clss.isMemberClass()) {
                        classes.add(clss);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CRCLPosemathTest.class
                            .getName()).log(Level.SEVERE, "clssNameToLookup={0}", clssNameToLookup);
                    Logger.getLogger(CRCLPosemathTest.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return classes;
    }
    static private List<Class<?>> classes = null;
    private final List<String> customExcludedPathStrings = new ArrayList<>();

    public static List<Class<?>> getAssignableClasses(Class<?> baseClss, List<Class<?>> classes, Set<String> excludedClassNames) {
        List<Class<?>> assignableClasses = new ArrayList<>();
        for (Class<?> clss : classes) {
            if (clss.isInterface() || Modifier.isAbstract(clss.getModifiers())) {
                continue;
            }
            if (excludedClassNames.contains(clss.getSimpleName()) || excludedClassNames.contains(clss.getName())) {
                continue;
            }
            Constructor constructors[] = clss.getConstructors();
            boolean hasNoArgsConstructor = false;
            for (int i = 0; i < constructors.length; i++) {
                Constructor constructorI = constructors[i];
                if (constructorI.getParameterCount() == 0) {
                    hasNoArgsConstructor = true;
                    break;
                }
            }
            if (!hasNoArgsConstructor) {
                continue;
            }
            if (baseClss.isAssignableFrom(clss)) {
                assignableClasses.add(clss);
            }
        }
        return assignableClasses;
    }

    int stringcount = 0;

    @SuppressWarnings({"unchecked"})
    private <T> T reflectiveRandomGenerate(Class<T> clzz, Random random, Set<String> excludedClasses) {
        if (clzz == int.class || clzz == Integer.class || Integer.class.isAssignableFrom(clzz)) {
            return (T) Integer.valueOf(random.nextInt());
        } else if (clzz == long.class || clzz == Long.class || Long.class.isAssignableFrom(clzz)) {
            return (T) Long.valueOf(random.nextLong());
        } else if (clzz == float.class || clzz == Float.class || Float.class.isAssignableFrom(clzz)) {
            return (T) Float.valueOf(random.nextFloat());
        } else if (clzz == double.class || clzz == Double.class || Double.class.isAssignableFrom(clzz)) {
            return (T) Double.valueOf(random.nextDouble());
        } else if (clzz == boolean.class || clzz == Boolean.class || Boolean.class.isAssignableFrom(clzz)) {
            return (T) Boolean.valueOf(random.nextBoolean());
        } else if (clzz == String.class) {
            byte b[] = new byte[random.nextInt(20) + 1];
            for (int i = 0; i < b.length; i++) {
                b[i] = (byte) RANDOM_STRING.charAt(random.nextInt(RANDOM_STRING.length()));

            }
            stringcount++;
            return (T) (new String(b) + Integer.toHexString(stringcount));
        } else if (clzz.isArray()) {
            final int arrayLength = random.nextInt(3);
            Object a[] = (Object[]) Array.newInstance(clzz.getComponentType(), arrayLength);
            for (int i = 0; i < a.length; i++) {
                final Object arrayVal = reflectiveRandomGenerate(clzz.getComponentType(), random, excludedClasses);
                if (null == arrayVal) {
                    throw new RuntimeException("arrayVal=null clzz.getComponentType()=" + clzz.getComponentType());
                }
                a[i] = arrayVal;
            }
            return (T) a;
        } else if (clzz.isEnum()) {
            final T[] enumConstants = clzz.getEnumConstants();
            return enumConstants[random.nextInt(enumConstants.length)];
        } else {
            T newObj;
            try {
                if (null == classes) {
                    classes = getClasses(customExcludedPathStrings);
                }
                List<Class<?>> availClasses = getAssignableClasses(clzz, classes, excludedClasses);
                final int availClassesSize = availClasses.size();
                if (availClassesSize < 1) {
                    throw new RuntimeException("no available classes for " + clzz + ", excludedClasses=" + excludedClasses);
                }
                Class<?> randClzz = availClasses.get(random.nextInt(availClassesSize));

                try {
                    newObj = (T) randClzz.getConstructor().newInstance();
                } catch (Throwable throwable) {
                    System.out.println("clzz = " + clzz);
                    System.out.println("randClzz = " + randClzz);
                    throwable.printStackTrace();
                    return null;
                }
            } catch (Throwable throwable) {
                System.out.println("clzz = " + clzz);
                throwable.printStackTrace();
                return null;
            }
            Class newObjClass = newObj.getClass();
            Set<String> newExcludedClasses = new TreeSet<>(excludedClasses);
            newExcludedClasses.add(newObjClass.getName());
            newExcludedClasses.add(newObjClass.getSimpleName());
            randomFillObjectFields(clzz.getFields(), newObj, random, newExcludedClasses);
            randomFillObjectFields(clzz.getDeclaredFields(), newObj, random, newExcludedClasses);
            randomFillObjectFields(newObjClass.getFields(), newObj, random, newExcludedClasses);
            randomFillObjectFields(newObjClass.getDeclaredFields(), newObj, random, newExcludedClasses);
            Method method[] = newObjClass.getMethods();
//            if (clzz.getName().contains("DisableGripperType")) {
//                System.out.println("clzz = " + clzz);
//            }

            for (int i = 0; i < method.length; i++) {
                Method method1 = method[i];
                try {
                    if (method1.getReturnType() == Void.class || method1.getReturnType() == void.class) {
                        if (method1.getParameterCount() == 1) {
                            if (method1.getName().startsWith("set")) {
                                final Class<?> parameter0Type = method1.getParameterTypes()[0];
                                if (!newExcludedClasses.contains(parameter0Type.getName())
                                        && !newExcludedClasses.contains(parameter0Type.getSimpleName())) {
                                    final Object setValue = reflectiveRandomGenerate(parameter0Type, random, newExcludedClasses);
                                    if (null == setValue) {
                                        throw new RuntimeException("setValue=null,  method1=" + method1 + ",parameter0Type=" + parameter0Type + ", clzz=" + clzz + ",newExcludedClasses=" + newExcludedClasses);
                                    }
                                    method1.invoke(newObj, setValue);
                                }
                            }
                        }
                    } else if (Collection.class.isAssignableFrom(method1.getReturnType())) {
                        if (method1.getParameterCount() == 0 && method1.getName().startsWith("get")) {
                            Type genRetType = method1.getGenericReturnType();
                            if (null != genRetType) {
                                final String genRetTypeName = genRetType.getTypeName();
                                int i1 = genRetTypeName.indexOf('<');
                                int i2 = genRetTypeName.lastIndexOf('>');
                                if (i1 > 0 && i2 > i1 && i2 < genRetTypeName.length()) {
                                    String containedTypeString = genRetTypeName.substring(i1 + 1, i2);
                                    Class<?> containedClzz = Class.forName(containedTypeString);
                                    Collection collection = (Collection) method1.invoke(newObj);
                                    int collsize = collection.size();
                                    int addsize = random.nextInt(3) - collsize;
                                    List<Class<?>> availClasses = getAssignableClasses(containedClzz, classes, newExcludedClasses);

                                    if (!availClasses.isEmpty()
                                            && !containedClzz.isInterface()
                                            && !newExcludedClasses.contains(containedClzz.getName())
                                            && !newExcludedClasses.contains(containedClzz.getSimpleName())) {
                                        for (int j = 0; j < addsize; j++) {
                                            final Object addValue = reflectiveRandomGenerate(containedClzz, random, newExcludedClasses);
                                            if (null == addValue) {
                                                throw new RuntimeException("addValue=null containedClzz=" + containedClzz + ",metho1=" + method1 + ",clzz=" + clzz + ",newExcludedClasses=" + newExcludedClasses);
                                            }
                                            collection.add(addValue);
                                        }
                                    }
                                    for (int j = 0; j < availClasses.size(); j++) {
                                        final Class<?> availClassJ = availClasses.get(j);
                                        final Object addValue = reflectiveRandomGenerate(availClassJ, random, newExcludedClasses);
                                        if (null == addValue) {
                                            throw new RuntimeException("addValue=null clzz.getComponentType()=" + clzz.getComponentType());
                                        }
                                        collection.add(addValue);
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("clzz = " + clzz);
                    System.out.println("method1 = " + method1);
                    ex.printStackTrace();
                    throw new RuntimeException("clzz=" + clzz + ", method1=" + method1 + ",ex=" + ex, ex);
                }
            }

            if (null == newObj) {
                throw new NullPointerException("newObj = " + newObj);
            }
            checkForNullObjectFields(clzz.getFields(), newObj, newExcludedClasses);
            checkForNullObjectFields(clzz.getDeclaredFields(), newObj, newExcludedClasses);
            checkForNullObjectFields(newObjClass.getFields(), newObj, newExcludedClasses);
            checkForNullObjectFields(newObjClass.getDeclaredFields(), newObj, newExcludedClasses);
            return newObj;
        }
    }

    @SuppressWarnings({"unchecked"})
    private void reflectiveCheckEquals(String msg, Object o1, Object o2, int recursion) {
        if (recursion > 20) {
            throw new RuntimeException("msg=" + msg + ",o1=" + o1 + ",o2=" + o2 + ",recursion=" + recursion);
        }
        assertEquals(msg + " == null", o1 == null, o2 == null);
        if (o1 == null && o2 == null) {
            return;
        }
        assertEquals(msg + ".getClass()", o1.getClass(), o2.getClass());
        Class<?> clzz = o1.getClass();
        if (clzz == int.class || clzz == Integer.class || Integer.class.isAssignableFrom(clzz)) {
            assertEquals(msg, o1, o2);
        } else if (clzz == long.class || clzz == Long.class || Long.class.isAssignableFrom(clzz)) {
            assertEquals(msg, o1, o2);
        } else if (clzz == float.class || clzz == Float.class || Float.class.isAssignableFrom(clzz)) {
            assertEquals(msg, (double) o1, (double) o2, ASSERT_TOLERANCE_DELTA);
        } else if (clzz == double.class || clzz == Double.class || Double.class.isAssignableFrom(clzz)) {
            assertEquals(msg, (double) o1, (double) o2, ASSERT_TOLERANCE_DELTA);
        } else if (clzz == boolean.class || clzz == Boolean.class || Boolean.class.isAssignableFrom(clzz)) {
            assertEquals(msg, o1, o2);
        } else if (clzz == String.class) {
            if (!msg.endsWith("getName()")) {
                assertEquals(msg, o1, o2);
            }
        } else if (clzz.isEnum()) {
            assertEquals(msg, o1, o2);
        } else if (clzz.isArray()) {
            assertEquals(msg + ".length", Array.getLength(o1), Array.getLength(o2));
            for (int i = 0; i < Array.getLength(o1); i++) {
                reflectiveCheckEquals(msg + "[" + i + "]", Array.get(o1, i), Array.get(o2, i), recursion + 1);
            }
        } else if (List.class.isAssignableFrom(clzz)) {
            List l1 = (List) o1;
            List l2 = (List) o2;
            assertEquals(msg + ".size()", l1.size(), l2.size());
            for (int i = 0; i < l1.size(); i++) {
                reflectiveCheckEquals(msg + ".get(" + i + ")", l1.get(i), l2.get(i), recursion + 1);
            }
        } else if (Collection.class.isAssignableFrom(clzz)) {
            Collection c1 = (Collection) o1;
            Collection c2 = (Collection) o2;
            assertEquals(msg + ".size()", c1.size(), c2.size());
            assertTrue(msg + ".c1.containsAll(c2) c1=" + c1 + ", c2=" + c2, c1.containsAll(c2));
            assertTrue(msg + ".c2.containsAll(c1) c1=" + c1 + ", c2=" + c2, c2.containsAll(c1));
        } else {
            reflectiveCheckFields(clzz.getFields(), msg, o1, o2, recursion + 1);
            reflectiveCheckFields(clzz.getDeclaredFields(), msg, o1, o2, recursion + 1);
            Method methods[] = clzz.getMethods();
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                try {
                    if (method.getReturnType() != void.class && method.getReturnType() != Void.class && method.getReturnType() != Class.class) {
                        if (method.getParameterCount() == 0 && method.getName().startsWith("get") && !method.getName().equals("getClass")) {
                            reflectiveCheckEquals(msg + "." + method.getName() + "()", method.invoke(o1), method.invoke(o2), recursion + 1);
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CRCLPosemathTest.class.getName()).log(Level.SEVERE, "method=" + method + ",o1=" + o1 + ",o2=" + o2, ex);
                }
            }
        }
    }

    @SuppressWarnings({"unchecked"})
    private void reflectiveCheckFields(Field[] fa, String msg, Object o1, Object o2, int recursion) {
        for (int i = 0; i < fa.length; i++) {
            Field field = fa[i];
            try {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                reflectiveCheckEquals(msg + "." + field.getName(), field.get(o1), field.get(o2), recursion + 1);
            } catch (Exception ex) {
                Logger.getLogger(CRCLPosemathTest.class.getName()).log(Level.SEVERE, "field=" + field + ",msg=" + msg + ",o1=" + o1 + ",o2=" + o2, ex);
            }
        }
    }

    private <T> void randomFillObjectFields(Field[] fields, T newObj, Random random, Set<String> excludedClasses) {
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            try {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                if (Collection.class.isAssignableFrom(field.getType())) {
                    continue;
                }
                field.setAccessible(true);
                final Class<?> fieldType = field.getType();
                if (!excludedClasses.contains(fieldType.getName()) && !excludedClasses.contains(fieldType.getSimpleName())) {
                    field.set(newObj, reflectiveRandomGenerate(fieldType, random, excludedClasses));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }

    private <T> void checkForNullObjectFields(Field[] fields, T newObj, Set<String> excludedClasses) {
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            try {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                final Class<?> fieldType = field.getType();
                if (fieldType.isInterface()) {
                    continue;
                }
                if (excludedClasses.contains(fieldType.getName())) {
                    continue;
                }
                if (excludedClasses.contains(fieldType.getSimpleName())) {
                    continue;
                }
                if (Collection.class.isAssignableFrom(fieldType)) {
                    continue;
                }
                field.setAccessible(true);
                Object obj = field.get(newObj);
                if (null == obj) {
                    throw new NullPointerException("field=" + field + ", newObj=" + newObj);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_CRCLProgramType() {
        System.out.println("CRCLCopier.copy(CRCLProgramType)");

        // Create a program with one MoveTo command.
        CRCLProgramType programIn = new CRCLProgramType();
        MoveToType moveCmd = new MoveToType();
        moveCmd.setEndPosition(pose123);
        programIn.getMiddleCommand().add(moveCmd);

        CRCLProgramType result = CRCLCopier.copy(programIn);

        assertEquals(result.getMiddleCommand().size(), programIn.getMiddleCommand().size());
        MoveToType transformedMoveCmd = (MoveToType) result.getMiddleCommand().get(0);

        checkEquals("pose", transformedMoveCmd.getEndPosition(), moveCmd.getEndPosition());

        try {
            final File randomProgramFile = File.createTempFile("randomProgram", ".xml");
            CRCLProgramType randProgram = reflectiveRandomGenerate(CRCLProgramType.class, new Random(10), new TreeSet<>(Arrays.asList("CRCLCommandWrapper")));
            try {

                String randProgramString = CRCLSocket.getUtilSocket().programToPrettyString(randProgram, true);
//            System.out.println("randomProgramFile = " + randomProgramFile);
                try (PrintWriter pw = new PrintWriter(randomProgramFile)) {
                    pw.println(randProgramString);
                }
                CRCLProgramType readBackRandProgram = CRCLUtils.readProgramFile(randomProgramFile);
                reflectiveCheckEquals("readBackRandProgram", randProgram, readBackRandProgram, 0);
//            System.out.println("randProgramString = " + randProgramString);
                CRCLProgramType randProgramCopy = CRCLCopier.copy(randProgram);
                reflectiveCheckEquals("randProgramCopy", randProgram, randProgramCopy, 0);
            } catch (Exception exception) {
                System.out.println("randomProgramFile = " + randomProgramFile);
                System.out.println("randProgram = " + randProgram);
                System.out.println("exception.getMessage() = " + exception.getMessage());
                try (BufferedReader br = new BufferedReader(new FileReader(randomProgramFile))) {
                    String line = br.readLine();
                    int count = 1;
                    while (null != line) {
                        System.out.printf("%04d:    %s\n", count, line);
                        count++;
                        line = br.readLine();
                    }
                }
                exception.printStackTrace();
                throw new RuntimeException(exception);
            }
        } catch (IOException iOException) {
            iOException.printStackTrace();
            throw new RuntimeException(iOException);
        }
    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_VectorType() {
        System.out.println("CRCLCopier.copy(VectorType)");
        VectorType vec = xvec;
        VectorType expResult = xvec;
        VectorType result = CRCLCopier.copy(vec);
        checkEquals("vector", result, expResult);
        assertTrue(result != vec);
    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_PoseType() {
        System.out.println("CRCLCopier.copy(PoseType)");
        PoseType pose = pose321rot90;
        PoseType expResult = pose321rot90;
        PoseType result = CRCLCopier.copy(pose);
        checkEquals("pose", result, expResult);
        assertTrue(result != pose);
        assertTrue(result.getPoint() != pose.getPoint());
        assertTrue(result.getXAxis() != pose.getXAxis());
        assertTrue(result.getZAxis() != pose.getZAxis());
    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_CRCLStatusType() {
        System.out.println("CRCLCopier.copy(CRCLStatusType)");
        CRCLStatusType status = new CRCLStatusType();
        CommandStatusType commandStatus = new CommandStatusType();
        commandStatus.setCommandID(233);
        commandStatus.setStatusID(2343);
        status.setCommandStatus(commandStatus);
        PoseStatusType poseStatus = new PoseStatusType();
        poseStatus.setPose(pose123);
        status.setPoseStatus(poseStatus);
        JointStatusesType jointStatuses = new JointStatusesType();
        JointStatusType js1 = new JointStatusType();
        js1.setJointNumber(1);
        js1.setJointPosition(DOUBLE_1);
        jointStatuses.getJointStatus().add(js1);
        status.setJointStatuses(jointStatuses);
        CRCLStatusType expResult = status;
        CRCLStatusType result = CRCLCopier.copy(status);

        checkEquals("pose", result.getPoseStatus().getPose(), expResult.getPoseStatus().getPose());
        assertTrue(result != status);
        assertTrue(result.getPoseStatus() != status.getPoseStatus());
        assertTrue(result.getPoseStatus().getPose() != status.getPoseStatus().getPose());
        assertTrue(result.getCommandStatus() != status.getCommandStatus());
        assertEquals(result.getCommandStatus().getCommandID(), expResult.getCommandStatus().getCommandID());
        assertTrue(result.getCommandStatus() != status.getCommandStatus());

        try {
            final File randomStatusFile = File.createTempFile("randomStatus", ".xml");
            try {
                CRCLStatusType randStatus = reflectiveRandomGenerate(CRCLStatusType.class, new Random(30), new TreeSet<>());
                String randStatusString = CRCLSocket.getUtilSocket().statusToPrettyString(randStatus, true);

//            System.out.println("randomStatusFile = " + randomStatusFile);
                try (PrintWriter pw = new PrintWriter(randomStatusFile)) {
                    pw.println(randStatusString);
                }
                CRCLStatusType readBackRandStatus = CRCLUtils.readStatusFile(randomStatusFile);
                reflectiveCheckEquals("readBackRandStatus", randStatus, readBackRandStatus, 0);
//            System.out.println("randStatusString = \n" + randStatusString);
                CRCLStatusType randStatusCopy = CRCLCopier.copy(randStatus);
                reflectiveCheckEquals("randStatus", randStatus, randStatusCopy, 0);
            } catch (Exception exception) {
                System.err.println("randomStatusFile = " + randomStatusFile);
                exception.printStackTrace();
                throw new RuntimeException(exception);
            }
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_GripperStatusType() {
        System.out.println("CRCLCopier.copy(GripperStatusType)");
        ParallelGripperStatusType status = new ParallelGripperStatusType();
        status.setSeparation(DOUBLE_1);
//        GripperStatusType expResult = status;
        GripperStatusType result = CRCLCopier.copy(status);
        checkEquals("seperation", ((ParallelGripperStatusType) result).getSeparation(),
                status.getSeparation());
        assertTrue(result != status);
    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_PoseStatusType() {
        System.out.println("CRCLCopier.copy(PoseStatusType)");
        PoseStatusType status = new PoseStatusType();
        status.setPose(pose123);
        PoseStatusType expResult = status;
        PoseStatusType result = CRCLCopier.copy(status);
        checkEquals("pose", result.getPose(), expResult.getPose());
        assertTrue(result != status);
        assertTrue(result.getPose() != status.getPose());
    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_TwistType() {
        System.out.println("CRCLCopier.copy(TwistType)");
        TwistType twist = new TwistType();
        twist.setAngularVelocity(xvec);
        twist.setLinearVelocity(zvec);
        TwistType expResult = twist;
        TwistType result = CRCLCopier.copy(twist);
        checkEquals("angularVelocity", result.getAngularVelocity(), expResult.getAngularVelocity());
        checkEquals("linearVelocity", result.getLinearVelocity(), expResult.getLinearVelocity());
        assertTrue(result != twist);
        assertTrue(result.getAngularVelocity() != twist.getAngularVelocity());
        assertTrue(result.getLinearVelocity() != twist.getLinearVelocity());

    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_WrenchType() {
        System.out.println("CRCLCopier.copy(WrenchType)");
        WrenchType wrench = new WrenchType();
        wrench.setForce(yvec);
        wrench.setMoment(xvec);
        WrenchType expResult = wrench;
        WrenchType result = CRCLCopier.copy(wrench);
        checkEquals("force", result.getForce(), expResult.getForce());
        checkEquals("moment", result.getMoment(), expResult.getMoment());
        assertTrue(result != wrench);
        assertTrue(result.getForce() != wrench.getForce());
        assertTrue(result.getMoment() != wrench.getMoment());
    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_JointStatusesType() {
        System.out.println("CRCLCopier.copy(JointStatusesType)");
        JointStatusesType status = new JointStatusesType();
        JointStatusType js1 = new JointStatusType();
        js1.setJointNumber(1);
        js1.setJointPosition(DOUBLE_1);
        status.getJointStatus().add(js1);
        JointStatusesType expResult = status;
        JointStatusesType result = CRCLCopier.copy(status);
        checkEquals("jointPosition", result.getJointStatus().get(0).getJointPosition(),
                expResult.getJointStatus().get(0).getJointPosition());
        assertTrue(result != status);
        assertTrue(result.getJointStatus() != status.getJointStatus());
        assertTrue(result.getJointStatus().size() == status.getJointStatus().size());
        assertTrue(result.getJointStatus().get(0) != status.getJointStatus().get(0));

    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_JointStatusType() {
        System.out.println("CRCLCopier.copy(JointStatusType)");
        JointStatusType status = new JointStatusType();
        status.setJointNumber(1);
        status.setJointPosition(DOUBLE_1);
        status.setJointTorqueOrForce(DOUBLE_2);
        status.setJointVelocity(DOUBLE_3);
        JointStatusType expResult = status;
        JointStatusType result = CRCLCopier.copy(status);
        checkEquals("Position", result.getJointPosition(), expResult.getJointPosition());
        checkEquals("TorqueOrForce", result.getJointTorqueOrForce(), expResult.getJointTorqueOrForce());
        checkEquals("Velocity", result.getJointVelocity(), expResult.getJointVelocity());
        assertTrue(result != status);
    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_CommandStatusType() {
        System.out.println("CRCLCopier.copy(CommandStatusType)");
        CommandStatusType status = new CommandStatusType();
        status.setCommandID(1);
        status.setCommandState(CommandStateEnumType.CRCL_DONE);
        status.setStatusID(1);
        CommandStatusType expResult = status;
        CommandStatusType result = CRCLCopier.copy(status);
        assertEquals(expResult.getCommandID(), result.getCommandID());
        assertEquals(expResult.getStatusID(), result.getStatusID());
        assertEquals(expResult.getCommandState(), result.getCommandState());
        assertTrue(result != status);
    }

}
