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
package crcl.ui;

import crcl.ui.client.CrclSwingClientJPanel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class AutomaticPropertyFileUtils {

    private static Optional<Object> safeInvokeMethod(Method m, Object o) {
        try {
            return Optional.ofNullable(m.invoke(o));

        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Throwable cause = ex.getCause();
            if (null != cause) {
                Logger.getLogger(CrclSwingClientJPanel.class
                        .getName()).log(Level.SEVERE, "m=" + m + ",o=" + o, cause);
            }
            Logger.getLogger(CrclSwingClientJPanel.class
                    .getName()).log(Level.SEVERE, "m=" + m + ",o=" + o, ex);
        }
        return Optional.empty();
    }

    public static void saveObjectProperties(File f, Object o) {
        try {
            try (PrintStream ps = new PrintStream(new FileOutputStream(f))) {
                ps.println("classname=" + o.getClass().getCanonicalName());
                Method ma[] = o.getClass().getMethods();
                printProperiesToStream(ma, o, "", ps);
            }
        } catch (IOException iOException) {
            iOException.printStackTrace();
            throw new RuntimeException(iOException);
        }
    }

    private static boolean isDeclaringClassExcluded(Method m) {
        Class<?> mDecClss = m.getDeclaringClass();
        return mDecClss != Object.class
                && mDecClss != java.awt.Component.class
                && mDecClss != java.awt.Container.class
                && mDecClss != java.awt.Window.class
                && mDecClss != javax.swing.JComponent.class
                && mDecClss != javax.swing.JMenuBar.class;
    }

    private static void printProperiesToStream(Method[] ma, Object o, String prefix, final PrintStream ps) {
        Stream
                .of(ma)
                .filter(m -> Modifier.isPublic(m.getModifiers()))
                .filter(m -> !Modifier.isStatic(m.getModifiers()))
                .filter(m -> !Modifier.isNative(m.getModifiers()))
                .filter(AutomaticPropertyFileUtils::isDeclaringClassExcluded)
                .filter(m -> m.getExceptionTypes() == null || m.getExceptionTypes().length == 0)
                .filter(m -> m.getName().startsWith("is"))
                .filter(m -> haveValueOf(m.getReturnType()))
                .filter(m -> haveSetMethod(ma, m.getName().substring(2), m.getReturnType()))
                .filter(m -> m.getParameterTypes().length == 0)
                .filter(m -> m.getReturnType().isAssignableFrom(boolean.class))
                .map(m -> {
                    return safeInvokeMethod(m, o)
                            .map(result -> m.getReturnType().getCanonicalName() + " " + prefix + m.getName().substring(2, 3).toLowerCase() + m.getName().substring(3) + "=" + result.toString())
                            .orElse("# " + prefix + m.getName() + " returned null or could not be invoked");
                })
                .forEachOrdered(ps::println);
        Stream.of(ma)
                .filter(m -> Modifier.isPublic(m.getModifiers()))
                .filter(m -> !Modifier.isStatic(m.getModifiers()))
                .filter(m -> !Modifier.isNative(m.getModifiers()))
                .filter(AutomaticPropertyFileUtils::isDeclaringClassExcluded)
                .filter(m -> m.getReturnType() != void.class && m.getReturnType() != Void.class)
                .filter(m -> haveValueOf(m.getReturnType()))
                .filter(m -> m.getExceptionTypes() == null || m.getExceptionTypes().length == 0)
                .filter(m -> m.getName().startsWith("get"))
                .filter(m -> m.getParameterTypes().length == 0)
                .filter(m -> haveSetMethod(ma, m.getName().substring(3), m.getReturnType()))
                .map(m -> {
                    return safeInvokeMethod(m, o)
                            .map(result -> m.getReturnType().getCanonicalName() + " " + prefix + m.getName().substring(3, 4).toLowerCase() + m.getName().substring(4) + "=" + result.toString())
                            .orElse("# " + prefix + m.getName() + " returned null or could not be invoked");
                })
                .forEachOrdered(ps::println);
    }

    public static void loadPropertyFile(File f, Map<String, Object> targetMap, Object defaultTarget) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(f.toPath())) {
            br.lines()
                    .filter(l -> !l.startsWith("#"))
                    .map(l -> splitLine(l))
                    .forEachOrdered(args -> AutomaticPropertyFileUtils.setParam(targetMap, defaultTarget, args));
        }
    }

    private static String[] splitLine(String l) {
        int eqindex = l.indexOf('=');
        String afterEq;
        String beforeEq;
        if(eqindex > 0) {
            beforeEq = l.substring(0, eqindex);
            afterEq = l.substring(eqindex+1).trim();
        } else {
            beforeEq = l.trim();
            afterEq = "";
        }
        String beforeEqA[] = beforeEq.split("[ \t\r\n]+");
        if(beforeEqA.length <= 1) {
            return new String[]{beforeEqA[0],afterEq};
        } else {
            return new String[]{beforeEqA[0],beforeEqA[1],afterEq};
        }
    }

    public static void appendObjectProperties(File f, String prefix, Object o) {
        try {
            try (PrintStream ps = new PrintStream(new FileOutputStream(f, true))) {
                Method ma[] = o.getClass().getMethods();
                printProperiesToStream(ma, o, prefix, ps);
            }
        } catch (IOException iOException) {
            iOException.printStackTrace();
            throw new RuntimeException(iOException);
        }
    }

    private static boolean haveSetMethod(Method ma[], String name, Class paramType) {

        String setName = "set" + name;
        for (Method m : ma) {
            int modifiers = m.getModifiers();
            if (Modifier.isPublic(modifiers)
                    && !Modifier.isStatic(modifiers)
                    && !Modifier.isNative(modifiers)
                    && m.getName().equals(setName)
                    && (m.getReturnType() == Void.class || m.getReturnType() == void.class)
                    && m.getParameterCount() == 1
                    && m.getParameterTypes().length == 1
                    && m.getParameterTypes()[0] == paramType
                    && (m.getExceptionTypes() == null || m.getExceptionTypes().length == 0)) {
                return true;
            }
        }
        return false;
    }

    static private boolean haveValueOf(Class<?> clss) {
        Method vmethod
                = Stream.of(clss.getMethods())
                        .filter(m -> m.getName().equals("valueOf"))
                        .filter(m -> m.getParameterTypes().length == 1)
                        .filter(m -> Modifier.isStatic(m.getModifiers()))
                        .filter(m -> m.getParameterTypes()[0].isAssignableFrom(String.class))
                        .findAny()
                        .orElse(null);
        if (null != vmethod) {
            return true;
        }
        if (clss.isAssignableFrom(String.class)) {
            return true;
        } else if (clss.isAssignableFrom(double.class)) {
            return true;
        } else if (clss.isAssignableFrom(float.class)) {
            return true;
        } else if (clss.isAssignableFrom(long.class)) {
            return true;
        } else if (clss.isAssignableFrom(int.class)) {
            return true;
        } else if (clss.isAssignableFrom(short.class)) {
            return true;
        } else if (clss.isAssignableFrom(byte.class)) {
            return true;
        } else if (clss.isAssignableFrom(boolean.class)) {
            return true;
        } else if (clss.isAssignableFrom(Double.class)) {
            return true;
        } else if (clss.isAssignableFrom(Float.class)) {
            return true;
        } else if (clss.isAssignableFrom(Long.class)) {
            return true;
        } else if (clss.isAssignableFrom(Integer.class)) {
            return true;
        } else if (clss.isAssignableFrom(Short.class)) {
            return true;
        } else if (clss.isAssignableFrom(Byte.class)) {
            return true;
        } else if (clss.isAssignableFrom(Boolean.class)) {
            return true;
        }
        return false;
    }

    @SuppressWarnings({"unchecked", "nullness"})
    static private <T> @Nullable
         T valueOf(Class<T> clss, String s) {
        try {
            Method vmethod = Stream.of(clss.getMethods())
                    .filter(m -> m.getName().equals("valueOf"))
                    .filter(m -> m.getParameterTypes().length == 1)
                    .filter(m -> Modifier.isStatic(m.getModifiers()))
                    .filter(m -> m.getParameterTypes()[0].isAssignableFrom(String.class
            ))
                    .findAny()
                    .orElse(null);
            if (null != vmethod) {
                return (T) vmethod.invoke(null, s);
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(CrclSwingClientJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
        if (clss.isAssignableFrom(String.class)) {
            return (T) s;
        } else if (clss.isAssignableFrom(double.class)) {
            return (T) Double.valueOf(s);
        } else if (clss.isAssignableFrom(float.class)) {
            return (T) Float.valueOf(s);
        } else if (clss.isAssignableFrom(long.class)) {
            return (T) Long.valueOf(s);
        } else if (clss.isAssignableFrom(int.class)) {
            return (T) Integer.valueOf(s);
        } else if (clss.isAssignableFrom(short.class)) {
            return (T) Short.valueOf(s);
        } else if (clss.isAssignableFrom(byte.class)) {
            return (T) Byte.valueOf(s);
        } else if (clss.isAssignableFrom(boolean.class)) {
            return (T) Boolean.valueOf(s);
        } else if (clss.isAssignableFrom(Double.class)) {
            return (T) Double.valueOf(s);
        } else if (clss.isAssignableFrom(Float.class)) {
            return (T) Float.valueOf(s);
        } else if (clss.isAssignableFrom(Long.class)) {
            return (T) Long.valueOf(s);
        } else if (clss.isAssignableFrom(Integer.class)) {
            return (T) Integer.valueOf(s);
        } else if (clss.isAssignableFrom(Short.class)) {
            return (T) Short.valueOf(s);
        } else if (clss.isAssignableFrom(Byte.class)) {
            return (T) Byte.valueOf(s);
        } else if (clss.isAssignableFrom(Boolean.class)) {
            return (T) Boolean.valueOf(s);
        }
        return null;
    }

    @SuppressWarnings({"unchecked","nullness"})
    private static void setParam(Map<String, Object> targetMap, Object defaultTarget, String args[]) {
        try {
            if (args.length < 2) {
                return;
            }
            final Class<? extends Object> defaultTargetClass = defaultTarget.getClass();
            if (args[0].equals("classname") || args[1].equals("classname")) {
                if (!Objects.equals(defaultTargetClass.getCanonicalName(),args[args.length-1])) {
                    throw new RuntimeException("wrong class: defaultTarget.getClass()=" + defaultTargetClass + ", args=" + Arrays.toString(args));
                }
                return;
            }
            
            if (args.length < 3) {
                return;
            }
            

            
            Class<?> clss = null;

            switch (args[0]) {
                case "boolean":
                    clss = boolean.class;
                    break;

                case "int":
                    clss = int.class;
                    break;

                case "long":
                    clss = long.class;
                    break;

                case "float":
                    clss = float.class;
                    break;

                case "double":
                    clss = double.class;
                    break;

                case "String":
                    clss = String.class;
                    break;

                default:
                    try {
                        if (args[0].indexOf('.') < 0) {
                            clss = Class.forName("java.lang." + args[0]);
                        } else {
                            clss = Class.forName(args[0]);
                        }
                    } catch (ClassNotFoundException classNotFoundException) {
                        System.err.println("Can't set param type: args= " + Arrays.toString(args));
                    }
                    break;
            }

            if (null == clss) {
                System.err.println("clss== null, args=" + Arrays.toString(args));
                return;
            }
            final String valueString = args.length == 3? args[2]: String.join(" ", Arrays.copyOfRange(args, 2, args.length-1));
            Object o = valueOf(clss, valueString);
            if (null == o) {
                System.err.println("o== null, clss=" + clss + ", args=" + Arrays.toString(args));
                return;
            }
            Method m = null;
            Object target;
            for (Map.Entry<String, Object> entry : targetMap.entrySet()) {
                if (args[1].startsWith(entry.getKey())) {
                    String name = args[1].substring(entry.getKey().length());
                    target = entry.getValue();
                    String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
                    final Class<?> targetClass = target.getClass();
                    try {
                        m = targetClass
                                .getMethod(methodName, clss);
                    } catch (NoSuchMethodException ex) {
                        System.err.println("NoSuchMethodException: targetClass=" + targetClass + ", methodName=" + methodName + ", o=" + o + ", clss=" + clss + ", args=" + Arrays.toString(args));
                    }
                    if (null == m) {
                        return;
                    }
                    m.invoke(target, o);
                    return;
                }
            }

            String methodName = "set" + args[1].substring(0, 1).toUpperCase() + args[1].substring(1);
            try {
                m = defaultTargetClass.getMethod(methodName, clss);
            } catch (NoSuchMethodException ex) {
                System.err.println("NoSuchMethodException: defaultTargetClass=" + defaultTargetClass + ", methodName=" + methodName + ", o=" + o + ", clss=" + clss + ", args=" + Arrays.toString(args));
            }
            if (null == m) {
                return;
            }
            m.invoke(defaultTarget, o);
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(CrclSwingClientJPanel.class
                    .getName()).log(
                            Level.SEVERE,
                            "Can not setParam with args = " + Arrays.toString(args),
                            ex);
        }
    }
}
