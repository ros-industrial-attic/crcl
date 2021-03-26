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
package java2slice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Stack;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java2slice.ClassInspector.ClassInfo;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class Java2SliceMain {

    private static String classToIceType(Class<?> clss) {
        if (String.class.isAssignableFrom(clss)) {
            return "string";
        }
        if (Integer.class.isAssignableFrom(clss)) {
            return "int";
        }
        if (BigInteger.class.isAssignableFrom(clss)) {
            return "long";
        }
        if (Long.class.isAssignableFrom(clss)) {
            return "long";
        }
        if (BigDecimal.class.isAssignableFrom(clss)) {
            return "double";
        }
        if (Double.class.isAssignableFrom(clss)) {
            return "double";
        }
        if (Boolean.class.isAssignableFrom(clss)) {
            return "bool";
        }
        if (boolean.class.isAssignableFrom(clss)) {
            return "bool";
        }

        if (double.class.isAssignableFrom(clss)) {
            return "double";
        }
        if (int.class.isAssignableFrom(clss)) {
            return "int";
        }
        if (long.class.isAssignableFrom(clss)) {
            return "long";
        }
        return clss.getName().replace(".", "::") + "Ice";
    }

    private static String classToIceType(ClassInfo clssInfo) throws ClassNotFoundException {
        Class<?> clss = clssInfo.getPropertyClass();
        if (List.class.isAssignableFrom(clss)) {
            Class genericParamClass = (Class) clssInfo.getGenericParamClasses().get(0);
            return classToIceType(genericParamClass) + "Sequence";
        }
        return classToIceType(clss);
    }

    private static void printHelpAndExit(Options options, String args[]) {
        info("args = " + Arrays.toString(args));
        new HelpFormatter().printHelp("java -jar target/java2slice-1.8-jar-with-dependencies.jar  [OPTION]... [FILE]...", options, false);
        System.exit(1);
    }

    private static Logger LOGGER = Logger.getLogger(Java2SliceMain.class.getName());

    private static void info(String msgString) {
        LOGGER.info(msgString);
    }

    private static boolean verbose = false;

    public static void main(String[] args) {

        ProtectionDomain proDeom = javax.xml.bind.JAXBContext.class.getProtectionDomain();
        LOGGER.log(Level.INFO, "JAXBContext.class.getProtectionDomain() = {0}", proDeom);
        System.setProperty("javax.xml.bind.JAXBContextFactory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
        System.out.println("");
        System.out.println("Running Java2SliceMain");
        System.out.println("args = " + Arrays.toString(args));
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("currentDirectory = " + currentDirectory);
        Options options = new Options();
        try {
            options.addOption(Option.builder("?")
                    .desc("Print this message")
                    .longOpt("help")
                    .build());
            options.addOption(Option.builder("v")
                    .desc("enable verbose output")
                    .longOpt("verbose")
                    .build());
            options.addOption(Option.builder()
                    .desc("output slice file")
                    .required()
                    .hasArg()
                    .longOpt("out-slice")
                    .build());
            options.addOption(Option.builder()
                    .desc("output converters directory")
                    .hasArg()
                    .longOpt("out-converters-dir")
                    .build());

            CommandLine line = new DefaultParser().parse(options, args);
            verbose = line.hasOption("verbose");
            if (verbose) {
                if (!LOGGER.isLoggable(Level.INFO)) {
                    LOGGER.setLevel(Level.INFO);
                }
            }
            if (line.hasOption("help")) {
                printHelpAndExit(options, args);
            }
            String jars[] = line.getArgs();
            String jarpath = "";
            for (int i = 0; i < jars.length; i++) {
                String jar = jars[i];
                jarpath += jar + File.pathSeparator;
            }
            jarpath += File.pathSeparator + Objects.toString(System.getenv("CLASSPATH"), "");
//            String currentDirectory = System.getProperty("user.dir");
            info("currentDirectory = " + currentDirectory);
            info("args = " + Arrays.toString(args));
            String path[] = jarpath.split(File.pathSeparator);
            URL jarUrls[] = new URL[path.length];
            for (int i = 0; i < jarUrls.length; i++) {
                if (path[i].startsWith("file:")) {
                    try {
                        jarUrls[i] = new URI(path[i]).toURL();
                        continue;
                    } catch (Exception e) {
                        // eating exception.
                    }
                }
                jarUrls[i] = new File(path[i]).getCanonicalFile().toURI().toURL();
            }
            String jarUrlsDeepString = Arrays.deepToString(jarUrls);
            System.out.println("jarUrlsDeepString = " + jarUrlsDeepString);
            info("urls = " + jarUrlsDeepString);

            URLClassLoader loader = new URLClassLoader(jarUrls);
            ClassInspector inspector = new ClassInspector(loader);
            File outSliceFile = new File(line.getOptionValue("out-slice")).getCanonicalFile();
            System.out.println("outSliceFile = " + outSliceFile);
            if (verbose) {
                info("outtSliceFile = " + outSliceFile);
                info("jars = " + Arrays.toString(jars));
            }
            List<Class> classes = findClasses(jars, loader);
            outSliceFile.getParentFile().mkdirs();
            writeSliceFile(outSliceFile, classes, inspector);
            File convertersDir = new File(line.getOptionValue("out-converters-dir"));
            if (verbose) {
                info("convertersDir=" + convertersDir);
            }
            File toFile = new File(convertersDir, "converters/toice/ToIceConverters.java").getCanonicalFile();
            toFile.getParentFile().mkdirs();
            if (verbose) {
                info("toFile=" + toFile);
            }
            writeToIceConverters(toFile, classes, inspector);

            File fromFile = new File(convertersDir, "converters/fromice/FromIceConverters.java").getCanonicalFile();
            fromFile.getParentFile().mkdirs();
            if (verbose) {
                info("fromFile=" + fromFile);
            }
            writeFromIceConverters(fromFile, classes, inspector);
        } catch (ParseException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(Java2SliceMain.class.getName()).log(Level.SEVERE, null, ex);
            printHelpAndExit(options, args);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Java2SliceMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Java2SliceMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Java2SliceMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Java2SliceMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(Java2SliceMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Java2SliceMain done.");
        System.out.println("");
    }

    private static List<Class> findClasses(String[] jars, URLClassLoader loader) throws ClassNotFoundException, IOException {
        List<Class> classes = new ArrayList<>();
        for (int jarindex = 0; jarindex < jars.length; jarindex++) {
            String jar = jars[jarindex];
            if (verbose) {
                info("jar = " + jar);
            }
            if (null != jar && jar.length() > 0) {
                Path jarPath = FileSystems.getDefault().getPath(jar).toAbsolutePath();
                if (verbose) {
                    info("jarPath = " + jarPath);
                }
                ZipInputStream zip = new ZipInputStream(Files.newInputStream(jarPath, StandardOpenOption.READ));
                if (verbose) {
                    info("zip = " + zip);
                }
                for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {

                    if (verbose) {
                        final ZipEntry entryToPrint = entry;
                        info("entry = " + entryToPrint);
                    }
                    // This ZipEntry represents a class. Now, what class does it represent?
                    String entryName = entry.getName();
                    if (verbose) {
                        info("entryName = " + entryName);
                    }
                    if (entry.isDirectory()) {
                        continue;
                    }
                    if (entryName.startsWith("META-INF")) {
                        continue;
                    }
                    if (!entryName.endsWith(".class")) {
                        continue;
                    }
                    String fullClassName
                            = entryName.substring(0, entryName.length() - ".class".length())
                                    .replace("/", ".");
                    if (verbose) {
                        info("clssName=" + fullClassName);
                    }
                    Class clss = loader.loadClass(fullClassName);
                    if (verbose) {
                        info("clss=" + clss);
                    }
                    if (clss.isAnnotation() || clss.isInterface() || clss.isLocalClass()
                            || clss.isSynthetic() || Enum.class.equals(clss)) {
                        if (verbose) {
                            info("skipping " + clss);
                        }
                        continue;
                    }
                    classes.add(clss);
                }
            }
        }
        if (verbose) {
            final List<Class> classesToPrint = classes;
            info("classes " + classesToPrint);
        }
        List<Class> newOrderClasses = new ArrayList<>();
        for (Class clss : classes) {
            if (Enum.class.equals(clss)) {
                continue;
            }
            if (newOrderClasses.contains(clss)) {
                continue;
            }
            Class superClass = clss.getSuperclass();
            Stack<Class> stack = new Stack<>();
            while (null != superClass
                    && !newOrderClasses.contains(superClass)
                    && !superClass.equals(java.lang.Object.class)) {
                stack.push(superClass);
                superClass = superClass.getSuperclass();
            }
            while (!stack.empty()) {
                newOrderClasses.add(stack.pop());
            }
            newOrderClasses.add(clss);
        }
        classes = newOrderClasses;
        if (verbose) {
            final List<Class> classesToPrint = classes;
            info("newOrderClasses " + classesToPrint);
        }
        return classes;
    }

    private static void writeSliceFile(File outSliceFile, List<Class> classes, ClassInspector inspector) throws IllegalAccessException, IllegalArgumentException, ClassNotFoundException, SecurityException, InvocationTargetException, NoSuchMethodException, FileNotFoundException {
        try (PrintWriter slicePw = new PrintWriter(outSliceFile)) {
            slicePw.println("module java2slice {");
            for (int i = 0; i < classes.size(); i++) {
                Class clss = classes.get(i);
                Map<String, ClassInfo> propsMap = inspector.getProperties(clss);
                if (verbose) {
                    info("propsMap = " + propsMap);
                }
            }
            for (int i = 0; i < classes.size(); i++) {
                Class clss = classes.get(i);
                if (Enum.class.equals(clss)) {
                    continue;
                }
                Map<String, ClassInfo> propsMap = inspector.getProperties(clss);
                if (verbose) {
                    info("propsMap = " + propsMap);
                }
                String pkgs[] = clss.getName().split("[.]+");
                if (verbose) {
                    info("pkgs = " + Arrays.toString(pkgs));
                }
                String clssName = pkgs[pkgs.length - 1];
                if (verbose) {
                    info("clssName=" + clssName);
                }
                String tabs = "\t";
                for (int j = 0; j < pkgs.length - 1; j++) {
                    String pkg = pkgs[j];
                    slicePw.println(tabs + "module " + pkg + " {");
                    tabs += "\t";
                }
                if (clss.isEnum()) {

                    printSliceEnumValues(slicePw, tabs, clssName, clss);
                } else {
                    slicePw.println(tabs + "class " + clssName + "Ice;");
                    if (inspector.getSeqenceClassesNeeded().contains(clss)) {
                        slicePw.println(tabs + "sequence<" + clssName + "Ice> " + clssName + "IceSequence;");
                    }
                }
//                    tabs += "\t";
//                    for (Entry<String, Class<?>> e : propsMap.entrySet()) {
//                        slicePw.println(tabs + classToIceType(e.getValue()) + " " + e.getKey() + ";");
//                    }
//                    tabs = tabs.substring(1);
//                    slicePw.println(tabs + "};");
//                            pw.println(tabs + "interface " + clssName + "Supplier {");
//                            pw.println(tabs + "\t" + clssName + "Ice get();");
//                            pw.println(tabs + " };");
//                            pw.println(tabs + "interface " + clssName + "Consumer {");
//                            pw.println(tabs + "\tvoid accept(" + clssName + "Ice t);");
//                            pw.println(tabs + " };");

                for (int j = 0; j < pkgs.length - 1; j++) {
                    tabs = tabs.substring(1);
                    slicePw.println(tabs + "};");
                }
            }

            for (int i = 0; i < classes.size(); i++) {
                Class clss = classes.get(i);
                if (Enum.class.equals(clss)) {
                    continue;
                }
                if (clss.isEnum()) {
                    continue;
                }
                Map<String, ClassInfo> propsMap = inspector.getProperties(clss);
                if (verbose) {
                    info("propsMap = " + propsMap);
                }
                String pkgs[] = clss.getName().split("[.]+");
                if (verbose) {
                    info("pkgs = " + Arrays.toString(pkgs));
                }
                String clssName = pkgs[pkgs.length - 1];
                if (verbose) {
                    info("clssName=" + clssName);
                }
                String tabs = "\t";
                for (int j = 0; j < pkgs.length - 1; j++) {
                    String pkg = pkgs[j];
                    slicePw.println(tabs + "module " + pkg + " {");
                    tabs += "\t";
                }
                if (!Objects.equals(Object.class, clss.getSuperclass())) {
                    slicePw.println(tabs + "class " + clssName + "Ice  extends " + clss.getSuperclass().getName().replace(".", "::") + "Ice {");
                } else {
                    slicePw.println(tabs + "class " + clssName + "Ice {");
                }
                tabs += "\t";
                for (Entry<String, ClassInfo> e : propsMap.entrySet()) {
                    Class propClass = e.getValue().getPropertyClass();
                    if (List.class.isAssignableFrom(propClass)) {
                        Method ma[] = clss.getMethods();
                        for (int j = 0; j < ma.length; j++) {
                            Method method = ma[j];
                            String propname = e.getKey().substring(0, 1).toUpperCase() + e.getKey().substring(1);

                            if (method.getName().startsWith("get" + propname)) {
//                                System.out.println("method = " + method);
                                ParameterizedType t = (ParameterizedType) method.getGenericReturnType();
//                                System.out.println("t = " + t);
                            }
                        }
                    }
                    if (isRefToPrimitive(propClass)) {
                        slicePw.println(tabs + "bool" + " " + e.getKey() + "IsNull;");
                    }
                    slicePw.println(tabs + classToIceType(e.getValue()) + " " + e.getKey() + ";");
                }
                tabs = tabs.substring(1);
                slicePw.println(tabs + "};");
                for (int j = 0; j < pkgs.length - 1; j++) {
                    tabs = tabs.substring(1);
                    slicePw.println(tabs + "};");
                }
            }
            slicePw.println("};");
        }
    }

    private static void writeToIceConverters(File toFile, List<Class> classes, ClassInspector inspector) throws ClassNotFoundException, FileNotFoundException {
        try (PrintWriter toFilePw = new PrintWriter(toFile)) {
            toFilePw.println("package converters.toice;");
            toFilePw.println();
            toFilePw.println("import java.math.BigInteger;");
            toFilePw.println("import java.math.BigDecimal;");
            toFilePw.println("import java.util.List;");
            toFilePw.println("import java.util.ArrayList;");

            for (int i = 0; i < classes.size(); i++) {
                Class clss = classes.get(i);
                if (Enum.class == clss) {
                    if (verbose) {
                        info("Skipping enum class :  " + clss);
                    }
                    continue;
                }
                Map<String, ClassInfo> propsMap = inspector.getProperties(clss);
                if (verbose) {
                    info("propsMap = " + propsMap);
                }
                String pkgs[] = clss.getName().split("[.]+");
                if (verbose) {
                    info("pkgs = " + Arrays.toString(pkgs));
                }
                String clssName = pkgs[pkgs.length - 1];
                if (verbose) {
                    info("clssName=" + clssName);
                }

                if (pkgs.length > 1) {
                    toFilePw.println("import " + clss.getCanonicalName() + ";");
                }
                toFilePw.println("import java2slice." + clss.getCanonicalName() + "Ice;");
            }

            toFilePw.println();

            toFilePw.println("public class ToIceConverters {");
            for (Class seqClass : inspector.getSeqenceClassesNeeded()) {
                if (verbose) {
                    info("seqClass = " + seqClass);
                }
                String clssName = splitClassName(seqClass);
                toFilePw.println("\tpublic static " + clssName + "Ice[] listOf" + clssName + "ToIce(List<" + clssName + "> in) {");
                toFilePw.println("\t\t" + clssName + "Ice newArray[]= new " + clssName + "Ice[in.size()];");
                toFilePw.println("\t\tfor(int i = 0; i < in.size(); i++) {");
                toFilePw.println("\t\t\tnewArray[i] = toIce(in.get(i));");
                toFilePw.println("\t\t}");
                toFilePw.println("\t\treturn  newArray;");
                toFilePw.println("\t}");
            }
            for (int i = 0; i < classes.size(); i++) {
                Class clss = classes.get(i);
                if (Enum.class == clss) {
                    if (verbose) {
                        info("Skipping enum class :  " + clss);
                    }
                    continue;
                }
                Map<String, ClassInfo> propsMap = inspector.getProperties(clss);
                if (verbose) {
                    info("propsMap = " + propsMap);
                }
                String clssName = splitClassName(clss);
                String toFileTabs = "\t\t";
                toFilePw.println();

                toFilePw.println("\tpublic static " + clssName + "Ice toIce(" + clssName + " in) {");
                toFilePw.println(toFileTabs + "if(null == in) {");
                toFilePw.println(toFileTabs + "\t return null;");
                toFilePw.println(toFileTabs + "}");
                if (clss.isEnum()) {
                    toFilePw.println(toFileTabs + "return " + clssName + "Ice.valueOf(in.ordinal());");
                } else {
                    List<Class> derivedClasses = getDerivedClasses(clss, classes);
                    if (derivedClasses.size() < 2 && !Modifier.isAbstract(clss.getModifiers())) {
                        toFilePw.println(toFileTabs + "return ((in!=null)?toIce(in, new " + clssName + "Ice()):null);");
                    } else {
                        boolean addelse = false;
                        for (Class dc : derivedClasses) {
                            if (Modifier.isAbstract(dc.getModifiers())) {
                                continue;
                            }
                            String dcClssName = splitClassName(dc);
                            toFilePw.println(toFileTabs + (addelse ? "else " : "") + "if(in instanceof " + dcClssName + ") {");
                            toFilePw.println(toFileTabs + "\treturn toIce((" + dcClssName + ")in, new " + dcClssName + "Ice());");
                            toFilePw.println(toFileTabs + "}");
                            addelse = true;
                        }
                        toFilePw.println(toFileTabs + "throw new IllegalArgumentException(in+\" is not of recognized type\");");
                    }
                    toFilePw.println("\t}");
                    toFilePw.println();
                    toFilePw.println("\tpublic static " + clssName + "Ice toIce(" + clssName + " in," + clssName + "Ice out) {");

                    toFilePw.println(toFileTabs + "if(null == in) {");
                    toFilePw.println(toFileTabs + "\t return null;");
                    toFilePw.println(toFileTabs + "}");

                    toFilePw.println(toFileTabs + "if(null == out) {");
                    toFilePw.println(toFileTabs + "\t return toIce(in);");
                    toFilePw.println(toFileTabs + "}");

                    if (!Objects.equals(Object.class, clss.getSuperclass())) {
                        String superclassName = clss.getSuperclass().getName();
                        int lastp = superclassName.lastIndexOf('.');
                        if (lastp > 0) {
                            superclassName = superclassName.substring(lastp + 1);
                        }
                        toFilePw.println(toFileTabs + "out = (" + clssName + "Ice) toIce((" + superclassName + ")in,(" + superclassName + "Ice)out);");
                    }
                    for (Entry<String, ClassInfo> e : propsMap.entrySet()) {
                        String propname = e.getKey().substring(0, 1).toUpperCase() + e.getKey().substring(1);
                        Class propClass = e.getValue().getPropertyClass();
                        boolean checkNull = isRefToPrimitive(propClass);
                        if (checkNull) {
                            if (Boolean.class.isAssignableFrom(propClass)) {
                                toFilePw.println(toFileTabs + "out." + e.getKey() + "IsNull = (null == in.is" + propname + "());");
                            } else {
                                toFilePw.println(toFileTabs + "out." + e.getKey() + "IsNull = (null == in.get" + propname + "());");
                            }
                            toFilePw.println(toFileTabs + "if(!out." + e.getKey() + "IsNull) {");
                            toFileTabs += "\t";
                        }
                        if (BigInteger.class.isAssignableFrom(propClass)) {
                            toFilePw.println(toFileTabs + "out." + e.getKey() + " =  in.get" + propname + "().longValue();");
                        } else if (Integer.class.isAssignableFrom(propClass)) {
                            toFilePw.println(toFileTabs + "out." + e.getKey() + " =  in.get" + propname + "();");
                        } else if (BigDecimal.class.isAssignableFrom(propClass)) {
                            toFilePw.println(toFileTabs + "out." + e.getKey() + " =  in.get" + propname + "().doubleValue();");
                        } else if (Double.class.isAssignableFrom(propClass)) {
                            toFilePw.println(toFileTabs + "out." + e.getKey() + " =  in.get" + propname + "().doubleValue();");
                        } else if (Boolean.class.isAssignableFrom(propClass)) {
                            toFilePw.println(toFileTabs + "out." + e.getKey() + " =  in.is" + propname + "().booleanValue();");
                        } else if (boolean.class.isAssignableFrom(propClass)) {
                            toFilePw.println(toFileTabs + "out." + e.getKey() + " =  in.is" + propname + "();");
                        } else if (String.class.isAssignableFrom(propClass)) {
                            toFilePw.println(toFileTabs + "out." + e.getKey() + " =  in.get" + propname + "();");
                        } else if (List.class.isAssignableFrom(propClass)) {
                            toFilePw.println(toFileTabs + "out." + e.getKey() + " =  listOf" + splitClassName((Class) e.getValue().getGenericParamClasses().get(0)) + "ToIce(in.get" + propname + "());");
                        } else if (propClass.isEnum()) {
                            toFilePw.println(toFileTabs + "out." + e.getKey() + " =  toIce(in.get" + propname + "());");
                        } else if (propClass.isPrimitive()) {
                            toFilePw.println(toFileTabs + "out." + e.getKey() + " =  in.get" + propname + "();");
                        } else {
                            toFilePw.println(toFileTabs + "out." + e.getKey() + " =  toIce(in.get" + propname + "());");
                        }
                        if (checkNull) {
                            toFileTabs = toFileTabs.substring(1);
                            toFilePw.println(toFileTabs + "}");
                        }
                    }
                    toFilePw.println(toFileTabs + "return out;");
                }
                toFileTabs = toFileTabs.substring(1);
                toFilePw.println(toFileTabs + "}");
            }
            toFilePw.println("}");
        }
    }

    private static boolean isRefToPrimitive(Class c) {
        return BigDecimal.class.isAssignableFrom(c)
                || BigInteger.class.isAssignableFrom(c)
                || Double.class.isAssignableFrom(c)
                || Integer.class.isAssignableFrom(c)
                || Boolean.class.equals(c)
                || String.class.isAssignableFrom(c);
    }

    private static void writeFromIceConverters(File fromFile, List<Class> classes, ClassInspector inspector) throws ClassNotFoundException, FileNotFoundException {
        try (PrintWriter fromFilePw = new PrintWriter(fromFile)) {
            fromFilePw.println("package converters.fromice;");
            fromFilePw.println();
            fromFilePw.println("import java.math.BigInteger;");
            fromFilePw.println("import java.math.BigDecimal;");
            fromFilePw.println("import java.util.List;");
            fromFilePw.println("import java.util.ArrayList;");

            for (int i = 0; i < classes.size(); i++) {
                Class clss = classes.get(i);
                if (Enum.class.equals(clss)) {
                    continue;
                }
                Map<String, ClassInfo> propsMap = inspector.getProperties(clss);
                if (verbose) {
                    info("propsMap = " + propsMap);
                }
                String pkgs[] = clss.getName().split("[.]+");
                if (verbose) {
                    info("pkgs = " + Arrays.toString(pkgs));
                }
                String clssName = pkgs[pkgs.length - 1];
                if (verbose) {
                    info("clssName=" + clssName);
                }

                if (pkgs.length > 1) {
                    fromFilePw.println("import " + clss.getCanonicalName() + ";");
                }
                fromFilePw.println("import java2slice." + clss.getCanonicalName() + "Ice;");
            }

            fromFilePw.println();
            fromFilePw.println("public class FromIceConverters  {");
            for (Class seqClass : inspector.getSeqenceClassesNeeded()) {
                if (verbose) {
                    info("seqClass = " + seqClass);
                }
                String clssName = splitClassName(seqClass);
                fromFilePw.println("\tpublic static List<" + clssName + "> fromIce(" + clssName + "Ice []in) {");
                fromFilePw.println("\t\tList<" + clssName + "> newList= new ArrayList<>();");
                fromFilePw.println("\t\tfor(int i = 0; i < in.length; i++) {");
                fromFilePw.println("\t\t\tnewList.add(fromIce(in[i]));");
                fromFilePw.println("\t\t}");
                fromFilePw.println("\t\treturn  newList;");
                fromFilePw.println("\t}");
            }
            for (int i = 0; i < classes.size(); i++) {
                String fromFileTabs = "\t\t";
                Class clss = classes.get(i);
                if (clss.equals(Enum.class)) {
                    continue;
                }
                Map<String, ClassInfo> propsMap = inspector.getProperties(clss);
                if (verbose) {
                    info("propsMap = " + propsMap);
                }
                String clssName = splitClassName(clss);
                if (clss.isEnum()) {
                    fromFilePw.println("\tpublic static " + clssName + " fromIce(" + clssName + "Ice in) {");
                    fromFilePw.println("\t\treturn " + clssName + ".values()[in.value()];");
                    fromFilePw.println("\t}");
                } else {
                    fromFilePw.println();
                    fromFilePw.println("\tpublic static " + clssName + " fromIce(" + clssName + "Ice in) {");
                    List<Class> derivedClasses = getDerivedClasses(clss, classes);
                    if (derivedClasses.size() < 2 && !Modifier.isAbstract(clss.getModifiers())) {
                        fromFilePw.println(fromFileTabs + "return ((in !=null)?fromIce(in, new " + clssName + "()):null);");
                    } else {
                        boolean addelse = false;
                        for (Class dc : derivedClasses) {
                            if (Modifier.isAbstract(dc.getModifiers())) {
                                continue;
                            }
                            String dcClssName = splitClassName(dc);
                            fromFilePw.println(fromFileTabs + (addelse ? "else " : "") + "if(in instanceof " + dcClssName + "Ice) {");
                            fromFilePw.println(fromFileTabs + "\treturn fromIce((" + dcClssName + "Ice)in,new " + dcClssName + "());");
                            fromFilePw.println(fromFileTabs + "}");
                            addelse = true;
                        }
                        fromFilePw.println(fromFileTabs + "throw new IllegalArgumentException(in+\" is not of recognized type\");");
                    }
                    fromFilePw.println("\t}");
                    fromFilePw.println();
                    fromFilePw.println("\tpublic static " + clssName + " fromIce(" + clssName + "Ice in," + clssName + " out) {");
                    fromFilePw.println(fromFileTabs + "if(in == null) {");
                    fromFilePw.println(fromFileTabs + "\treturn null;");
                    fromFilePw.println(fromFileTabs + "}");
                    fromFilePw.println(fromFileTabs + "if(out == null) {");
                    fromFilePw.println(fromFileTabs + "\treturn fromIce(in);");
                    fromFilePw.println(fromFileTabs + "}");
                    if (!Objects.equals(Object.class, clss.getSuperclass())) {
                        String superclassName = clss.getSuperclass().getName();
                        int lastp = superclassName.lastIndexOf('.');
                        if (lastp > 0) {
                            superclassName = superclassName.substring(lastp + 1);
                        }
                        fromFilePw.println(fromFileTabs + "out = (" + clssName + ") fromIce((" + superclassName + "Ice)in,(" + superclassName + ")out);");
                    }
                    for (Entry<String, ClassInfo> e : propsMap.entrySet()) {
                        String propname = e.getKey().substring(0, 1).toUpperCase() + e.getKey().substring(1);
                        Class propClass = e.getValue().getPropertyClass();
                        boolean checkNull = isRefToPrimitive(propClass);
                        if (checkNull) {
                            fromFilePw.println(fromFileTabs + "if(in." + e.getKey() + "IsNull) {");
                            fromFilePw.println(fromFileTabs + "\tout.set" + propname + "(null);");
                            fromFilePw.println(fromFileTabs + "} else {");
                            fromFileTabs += "\t";
                        }
                        if (BigInteger.class.isAssignableFrom(propClass)) {
                            fromFilePw.println(fromFileTabs + "out.set" + propname + "(BigInteger.valueOf(in." + e.getKey() + "));");
                        } else if (Integer.class.isAssignableFrom(propClass)) {
                            fromFilePw.println(fromFileTabs + "out.set" + propname + "(Integer.valueOf( (int) in." + e.getKey() + "));");
                        } else if (BigDecimal.class.isAssignableFrom(propClass)) {
                            fromFilePw.println(fromFileTabs + "out.set" + propname + "(BigDecimal.valueOf(in." + e.getKey() + "));");
                        } else if (Double.class.isAssignableFrom(propClass)) {
                            fromFilePw.println(fromFileTabs + "out.set" + propname + "(Double.valueOf(in." + e.getKey() + "));");
                        } else if (Boolean.class.isAssignableFrom(propClass)) {
                            fromFilePw.println(fromFileTabs + "out.set" + propname + "(in." + e.getKey() + ");");
                        } else if (List.class.isAssignableFrom(propClass)) {
                            fromFilePw.println(fromFileTabs + "out.get" + propname + "().clear();");
                            fromFilePw.println(fromFileTabs + "out.get" + propname + "().addAll(fromIce(in." + e.getKey() + "));");
                        } else if (String.class.isAssignableFrom(propClass)) {
                            fromFilePw.println(fromFileTabs + "out.set" + propname + "(in." + e.getKey() + ");");
                        } else if (propClass.isEnum()) {
                            fromFilePw.println(fromFileTabs + "out.set" + propname + "(fromIce(in." + e.getKey() + "));");
                        } else if (propClass.isPrimitive()) {
                            fromFilePw.println(fromFileTabs + "out.set" + propname + "(in." + e.getKey() + ");");
                        } else {
                            fromFilePw.println(fromFileTabs + "out.set" + propname + "(fromIce(in." + e.getKey() + ",out.get" + propname + "()));");
                        }
                        if (checkNull) {
                            fromFileTabs = fromFileTabs.substring(1);
                            fromFilePw.println(fromFileTabs + "}");
                        }
                    }
                    fromFilePw.println(fromFileTabs + "return out;");
                    fromFileTabs = fromFileTabs.substring(1);
                    fromFilePw.println("\t}");
                }
            }
            fromFilePw.println("}");
        }
    }

    private static List<Class> getDerivedClasses(Class<?> clss, List<Class> fromClasses) {
        List<Class> derivedClassList = new ArrayList<>();
        for (Class c : fromClasses) {
            if (clss.isAssignableFrom(c)) {
                derivedClassList.add(c);
            }
        }
        List<Class> newOrderClasses = new ArrayList<>();
        for (Class c : derivedClassList) {
            if (newOrderClasses.contains(c)) {
                continue;
            }
            Class superClass = clss.getSuperclass();
            Stack<Class> stack = new Stack<>();
            while (null != superClass
                    && !newOrderClasses.contains(superClass)
                    && !superClass.equals(java.lang.Object.class)) {
                stack.push(superClass);
                superClass = superClass.getSuperclass();
            }
            while (!stack.empty()) {
                Class clssFromStack = stack.pop();
                if (clss.isAssignableFrom(clssFromStack)) {
                    newOrderClasses.add(0, stack.pop());
                }
            }
            if (clss.isAssignableFrom(c)) {
                newOrderClasses.add(0, c);
            }
        }
        if (verbose && !newOrderClasses.isEmpty()) {
            info("getDerivedClasses(" + clss + ",...) returning " + newOrderClasses);
        }
        return newOrderClasses;
    }

    private static String splitClassName(Class clss) {
        String pkgs[] = clss.getName().split("[.]+");
        if (verbose) {
            info("pkgs = " + Arrays.toString(pkgs));
        }
        String clssName = pkgs[pkgs.length - 1];
        if (verbose) {
            info("clssName=" + clssName);
        }
        return clssName;
    }

    @SuppressWarnings("unchecked")
    private static void printSliceEnumValues(final PrintWriter slicePw, String tabs, String clssName, Class clss) throws SecurityException, InvocationTargetException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException {

        slicePw.println(tabs + "enum " + clssName + "Ice {");

        Method valuesMethod = clss.getMethod("values");
        Object values[] = (Object[]) valuesMethod.invoke(null);
        for (int j = 0; j < values.length; j++) {
            Object value = values[j];
            slicePw.println(tabs + "\t" + values[j].toString() + "=" + j + ((j < values.length - 1) ? "," : ""));
        }
        slicePw.println(tabs + "};");
    }
}
