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

import crcl.base.CRCLStatusType;
import static crcl.utils.CRCLUtils.getLongProperty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.Objects.requireNonNull;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class CRCLSchemaUtils {

    private CRCLSchemaUtils() {
    }

    private static final long RESOURCE_CHANGE_TIME = getLongProperty("crcl.resourceChangeTime", 600000);

    /**
     * Get the current CRCL schema directory.
     *
     * @return directory
     */
    public static File getCrclSchemaDirFile() {
        return crclSchemaDirFile;
    }

    /**
     * Delete extracted CRCL schema files if they exist.
     */
    public static void clearSchemas() {
        if (null != programSchemasFile && programSchemasFile.exists()) {
            boolean deleted = programSchemasFile.delete();
            if (!deleted) {
                Logger.getLogger(CRCLSocket.class.getName()).warning(programSchemasFile + " not deleted");
            }
        }
        if (null != cmdSchemasFile && cmdSchemasFile.exists()) {
            boolean deleted = cmdSchemasFile.delete();
            if (!deleted) {
                Logger.getLogger(CRCLSocket.class.getName()).warning(cmdSchemasFile + " not deleted");
            }
        }
        if (null != statSchemasFile && statSchemasFile.exists()) {
            boolean deleted = statSchemasFile.delete();
            if (!deleted) {
                Logger.getLogger(CRCLSocket.class.getName()).warning(statSchemasFile + " not deleted");
            }
        }
        if (null != crclSchemaDirFile && crclSchemaDirFile.exists()) {
            boolean deleted = crclSchemaDirFile.delete();
            if (!deleted) {
                Logger.getLogger(CRCLSocket.class.getName()).warning(crclSchemaDirFile + " not deleted");
            }
        }
    }

    /**
     * Read a list of CRCL schema files from the default programSchemasFile.
     *
     * @return array of files
     */
    public static File[] readProgramSchemaFiles() {
        return readProgramSchemaFiles(requireNonNull(programSchemasFile, "programSchemasFile"));
    }

    /**
     * Read a list of CRCL schema files from the provided file.
     *
     * @param schemaListFile File to read for list of CRCL schema files.
     * @return array of files
     */
    public static File[] readProgramSchemaFiles(File schemaListFile) {
        if (schemaListFile.exists()
                && SCHEMA_CHANGE_TIME > 0
                && (System.currentTimeMillis() - schemaListFile.lastModified()) > SCHEMA_CHANGE_TIME) {
            boolean deleted = schemaListFile.delete();
            if (!deleted) {
                LOGGER.warning(schemaListFile + " not deleted");
            }
            saveProgramSchemaFiles(schemaListFile, findSchemaFiles());
        } else if (!schemaListFile.exists()) {
            saveProgramSchemaFiles(schemaListFile, findSchemaFiles());
        }
        if (!schemaListFile.exists()) {
            throw new IllegalStateException(schemaListFile + " does not exist.");
        }
        try {
            List<File> fl = readSchemaListFile(schemaListFile);
            fl = reorderProgramSchemaFiles(fl);
            return fl.toArray(new File[fl.size()]);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Can not read " + schemaListFile, ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * Save a list of extracted schema files and a separate file listing them.
     *
     * @param schemasListFile text file to save with list of schema files.
     * @param fa array of schema files to save
     */
    public static void saveCmdSchemaFiles(File schemasListFile, File fa[]) {
        if (null == fa) {
            return;
        }
        fa = reorderAndFilterCommandSchemaFiles(fa);
        saveSchemaListFile(schemasListFile, fa);
    }

    private static List<File> reorderAndFilterStatSchemaFiles(List<File> fl) {
        List<File> newList = new ArrayList<>();
        for (int i = 0; i < fl.size(); i++) {
            File fileI = fl.get(i);
            if (fileI.getName().contains("Status") || fileI.getName().contains("Data") || fileI.getName().contains("Primitive")) {
                newList.add(fileI);
            }
        }
        Collections.sort(newList, new Comparator<File>() {

            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        int statIndex = -1;
        for (int i = 0; i < newList.size(); i++) {
            if (newList.get(i).getName().contains("Status")) {
                statIndex = i;
                break;
            }
        }
        if (statIndex > 0 && statIndex < newList.size()) {
            File f = newList.remove(statIndex);
            newList.add(0, f);
        }
        return newList;
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] listToArray(Class<T> componentType, List<T> list) {
        T[] newArray = (T[]) Array.newInstance(componentType, list.size());
        for (int i = 0; i < newArray.length; i++) {
            T object = list.get(i);
            if (object == null) {
                throw new IllegalArgumentException("list.get(" + i + ")=" + object);
            } else {
                newArray[i] = object;
            }
        }
        return newArray;
    }

    private static File[] reorderAndFilterStatSchemaFiles(File fa[]) {
        if (null == fa || fa.length < 1) {
            throw new IllegalArgumentException("File fa[]=" + fa);
        }
        List<File> fl = new ArrayList<>();
        fl.addAll(Arrays.asList(fa));
        List<File> newList = reorderAndFilterStatSchemaFiles(fl);
        if (null != newList) {
            File files[] = listToArray(File.class, newList);
            if (null != files) {
                File newFiles[] = (/*@NonNull*/File[]) files;
                return newFiles;
            }
        }
        return EMPTY_FILE_ARRAY;
    }

    private static final File[] EMPTY_FILE_ARRAY = new File[0];

    private static Schema filesToSchema(File fa[]) throws CRCLException {
        try {
            Source sources[] = new Source[fa.length];
            for (int i = 0; i < sources.length; i++) {
                if(!fa[i].exists()) {
                    throw new RuntimeException("fa["+i+"] = "+fa[i]+" does not exist.");
                }
                if(!fa[i].canRead()) {
                    throw new RuntimeException("fa["+i+"] = "+fa[i]+" can not be read.");
                }
                sources[i] = new StreamSource(fa[i]);
            }
            SchemaFactory schemaFactory = SchemaFactory
                    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            return schemaFactory.newSchema(sources);
        } catch (SAXException ex) {
            throw new CRCLException(ex);
        }
    }

    /**
     * Read a list of CRCL schema files from the default statSchemasFile.
     *
     * @return array of files
     */
    public static File[] readStatSchemaFiles() {
        return readStatSchemaFiles(requireNonNull(statSchemasFile, "statSchemasFile"));
    }

    /**
     * Read a list of CRCL schema files from the provided file.
     *
     * @param schemaListFile File to read for list of CRCL schema files.
     * @return array of files
     */
    public static File[] readStatSchemaFiles(File schemaListFile) {
        if (schemaListFile.exists()
                && SCHEMA_CHANGE_TIME > 0
                && (System.currentTimeMillis() - schemaListFile.lastModified()) > SCHEMA_CHANGE_TIME) {
            boolean deleted = schemaListFile.delete();
            if (!deleted) {
                LOGGER.warning(schemaListFile + " not deleted");
            }
            saveStatSchemaFiles(schemaListFile, findSchemaFiles());
        } else if (!schemaListFile.exists()) {
            saveStatSchemaFiles(schemaListFile, findSchemaFiles());
        }
        if (!schemaListFile.exists()) {
            throw new IllegalStateException(schemaListFile + " does not exist.");
        }
        try {
            List<File> fl = readSchemaListFile(schemaListFile);
            fl = reorderAndFilterStatSchemaFiles(fl);
            return fl.toArray(new File[fl.size()]);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to read " + schemaListFile, ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
    }

    private static List<File> readSchemaListFile(File schemaListFile) throws IOException {
        List<File> fl = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(schemaListFile))) {
            String line = null;

            while (null != (line = br.readLine())) {
                File file = new File(line.trim());
                if (file.isAbsolute()) {
                    fl.add(file);
                } else {
                    String absFilePath = schemaListFile.getParent() + File.separator + line.trim();
                    fl.add(new File(absFilePath));
                }
            }
        }
        return fl;
    }

    /**
     * Save a list of extracted schema files and a separate file listing them.
     *
     * @param schemaListFile text file to save with list of schema files.
     * @param fa array of schema files to save
     */
    public static void saveProgramSchemaFiles(File schemaListFile, File fa[]) {
        if (null == fa) {
            return;
        }
        fa = reorderProgramSchemaFiles(fa);
        saveSchemaListFile(schemaListFile, fa);
    }

    /**
     * Save a list of extracted schema files and a separate file listing them.
     *
     * @param schemaListFile text file to save with list of schema files.
     * @param fa array of schema files to save
     */
    public static void saveStatSchemaFiles(File schemaListFile, File fa[]) {
        if (null == fa) {
            return;
        }
        fa = reorderAndFilterStatSchemaFiles(fa);
        saveSchemaListFile(schemaListFile, fa);
    }

    private static void saveSchemaListFile(File schemaListFile, File[] fa) {
        PrintStream ps = null;
        try {
            File schemaParent = schemaListFile.getParentFile();
            if (null == schemaParent) {
                throw new NullPointerException("schemaListFile.getParentFile() schemaListFile=" + schemaListFile);
            }
            String schemaParentPath = schemaParent.getCanonicalPath();
            if (null == schemaParentPath) {
                throw new NullPointerException("schemaParent.getCanonicalPath() schemaParent=" + schemaParent);
            }
            ps = new PrintStream(new FileOutputStream(schemaListFile));
            for (int i = 0; i < fa.length; i++) {
                final File fai = fa[i];
                if (null == fai) {
                    throw new NullPointerException("fa=" + Arrays.toString(fa) + ", contains null at i=" + i);
                }
                String elementCanonicalPath = fai.getCanonicalPath();
                if (elementCanonicalPath.startsWith(schemaParentPath)) {
                    elementCanonicalPath = elementCanonicalPath.substring(schemaParentPath.length() + 1);
                }
                ps.println(elementCanonicalPath);
            }
        } catch (Exception ex) {
            String errmsg = "Can not write " + schemaListFile;
            LOGGER.log(Level.SEVERE, errmsg, ex);
            throw new RuntimeException(errmsg, ex);
        } finally {
            if (null != ps) {
                try {
                    ps.close();
                } catch (Exception exx) {
                }
            }
        }
    }

    @SuppressWarnings({"nullness", "unchecked"})
    private static <T> T[] toNonNullArray(List<T> list, Class<T> clzz) {
        final T[] zeroArray = (T[]) Array.newInstance(clzz, 0);
        if (null == list) {
            return zeroArray;
        }
        List<T> listCopy = new ArrayList<>(list);
        for (int i = 0; i < list.size(); i++) {
            if (listCopy.get(i) == null) {
                listCopy.remove(i);
                i--;
            }
        }
        @Nullable
        T nullableArray[] = listCopy.toArray(zeroArray);
        if (nullableArray == null) {
            return zeroArray;
        }
        return (/*@NonNull*/T[]) nullableArray;
    }

    private static File[] reorderAndFilterCommandSchemaFiles(File[] fa) {
        if (null == fa || fa.length < 1) {
            return EMPTY_FILE_ARRAY;
        }
        List<File> fl = new ArrayList<>();
        fl.addAll(Arrays.asList(fa));
        List<File> newList = reorderAndFilterCommandSchemaFiles(fl);
        if (newList != null) {
            return toNonNullArray(newList, File.class);
        }
        return EMPTY_FILE_ARRAY;
    }

    private static List<File> reorderAndFilterCommandSchemaFiles(List<File> fl) {
        List<File> newList = new ArrayList<>();
        for (int i = 0; i < fl.size(); i++) {
            File fileI = fl.get(i);
            if (fileI.getName().contains("Command") || fileI.getName().contains("Data") || fileI.getName().contains("Primitive")) {
                newList.add(fileI);
            }
        }
        Collections.sort(newList, new Comparator<File>() {

            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        int cmdInstanceIndex = -1;
        for (int i = 0; i < newList.size(); i++) {
            final File fileI = newList.get(i);
            if (fileI.getName().contains("CommandInstance")) {
                cmdInstanceIndex = i;
                break;
            }
        }
        if (cmdInstanceIndex > 0 && cmdInstanceIndex < newList.size()) {
            File f = newList.remove(cmdInstanceIndex);
//            CRCLSocket.commandXsdFile = f;
            newList.add(0, f);
        }
        return newList;
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] nonullArrayCopy(T[] oldArray) {
        final Class<?> oldArrayClass = oldArray.getClass();
        if (null == oldArrayClass) {
            throw new IllegalArgumentException("oldArrayClass=" + oldArrayClass + ", oldArray=" + oldArray);
        }
        final Class<T> oldArrayComponentType = (Class<T>) oldArrayClass.getComponentType();
        if (null == oldArrayComponentType) {
            throw new IllegalArgumentException("oldArrayComponentType=" + oldArrayComponentType + ", oldArray=" + oldArray);
        }
        T[] newArray = (T[]) Array.newInstance(oldArrayComponentType, oldArray.length);
        for (int i = 0; i < newArray.length; i++) {
            T oldElement = oldArray[i];
            if (null == oldElement) {
                throw new IllegalArgumentException("oldArray[" + i + "]=" + oldArray[i]);
            } else {
                newArray[i] = oldElement;
            }
        }
        return newArray;
    }

    static File @Nullable [] getDefaultStatSchemaFiles() {
        return defaultStatSchemaFiles;
    }

    private static File defaultStatSchemaFiles @Nullable []  = null;
    private static File defaultCmdSchemaFiles @Nullable []  = null;
    private static File defaultProgramSchemaFiles @Nullable []  = null;

    /**
     * Create a new schema object from an array of schema files.
     * @param fa array of schema files
     * @return new schema
     * @throws CRCLException a schema file is invalid
     */
    public static synchronized Schema filesToCmdSchema(File fa[]) throws CRCLException {
        if (null == fa || fa.length < 1) {
            throw new IllegalArgumentException("File fa[]=" + fa);
        }
        File arrayCopy[] = nonullArrayCopy(fa);
        File[] reorderedFa = reorderAndFilterCommandSchemaFiles(arrayCopy);
        defaultCmdSchemaFiles = reorderedFa;
        return filesToSchema(reorderedFa);
    }

    /**
     * Get a default schema from the default schema files for CRCL command sockets.
     * @return
     * @throws CRCLException schema file was invalid
     */
    public static synchronized @Nullable
    Schema getDefaultCmdSchema() throws CRCLException {
        if (null == defaultCmdSchemaFiles) {
            return null;
        }
        return filesToCmdSchema(defaultCmdSchemaFiles);
    }

    /**
     * Get an array with the default set of schema files for a CRCL command socket.
     * @return array of files
     */
    @SuppressWarnings("nullness")
    public static synchronized File[] getDefaultCmdSchemaFiles() {
        if (null == defaultCmdSchemaFiles || defaultCmdSchemaFiles.length < 1) {
            return new File[0];
        }
        return Arrays.copyOf(defaultCmdSchemaFiles, defaultCmdSchemaFiles.length);
    }

    /**
     * Get an array with the default set of schema files for a CRCLProgram.
     * @return array of files
     */
    @SuppressWarnings("nullness")
    public static synchronized File[] getDefaultProgramSchemaFiles() {
        if (null == defaultProgramSchemaFiles || defaultProgramSchemaFiles.length < 1) {
            return new File[0];
        }
        return Arrays.copyOf(defaultProgramSchemaFiles, defaultProgramSchemaFiles.length);
    }

    /**
     * Create a new schema object from an array of schema files.
     * @param fa array of schema files
     * @return new schema
     * @throws CRCLException a schema file is invalid
     */
    public static synchronized Schema filesToStatSchema(File fa[]) throws CRCLException {
        if (null == fa || fa.length < 1) {
            throw new IllegalArgumentException("File fa[]=" + fa);
        }
        File[] reorderedFa = reorderAndFilterStatSchemaFiles(nonullArrayCopy(fa));
        defaultStatSchemaFiles = reorderedFa;
        return filesToSchema(reorderedFa);
    }

    /**
     * Create a new schema object  with the default set of schema files for a CRCL status socket.
     * @return new schema object
     * @throws crcl.utils.CRCLException schema files could not be found or were invalid
     */
    public static synchronized @Nullable
    Schema getDefaultStatSchema() throws CRCLException {
        if (null == defaultStatSchemaFiles) {
            return null;
        }
        return filesToStatSchema(defaultStatSchemaFiles);
    }

    /**
     * Create a new schema object from an array of schema files.
     * @param fa array of schema files
     * @return new schema
     * @throws CRCLException a schema file is invalid
     */
    public static synchronized Schema filesToProgramSchema(File fa[]) throws CRCLException {
        if (null == fa) {
            throw new IllegalArgumentException("File fa[]=null");
        }
        final File[] faCopy = nonullArrayCopy(fa);
        fa = reorderProgramSchemaFiles(faCopy);
        defaultProgramSchemaFiles = fa;
        return filesToSchema(fa);
    }

    /**
     * Create a new schema object  with the default set of schema files for a CRCLProgram.
     * @return new schema object
     * @throws crcl.utils.CRCLException schema files could not be found or were invalid
     */
    public static synchronized @Nullable
    Schema getDefaultProgramSchema() throws CRCLException {
        if (null == defaultProgramSchemaFiles) {
            return null;
        }
        return filesToProgramSchema(defaultProgramSchemaFiles);
    }

    private static File[] reorderProgramSchemaFiles(File[] fa) {
        if (null == fa) {
            return EMPTY_FILE_ARRAY;
        }
        List<File> fl = new ArrayList<>();
        fl.addAll(Arrays.asList(fa));
        return toNonNullArray(fl, File.class);
    }

    private static List<File> reorderProgramSchemaFiles(List<File> fl) {
        Collections.sort(fl, new Comparator<File>() {

            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        int cmdInstanceIndex = -1;
        for (int i = 0; i < fl.size(); i++) {
            if (fl.get(i).getName().contains("ProgramInstance")) {
                cmdInstanceIndex = i;
                break;
            }
        }
        if (cmdInstanceIndex > 0 && cmdInstanceIndex < fl.size()) {
            File f = fl.remove(cmdInstanceIndex);
//            CRCLSocket.commandXsdFile = f;
            fl.add(0, f);
        }
        return fl;
    }


    private static final Logger LOGGER = Logger.getLogger(CRCLSchemaUtils.class.getName());

//    static private @Nullable
//    File generateSchema(Class<?> clss) throws CRCLException {
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(clss);
//            CRCLSchemaOutputResolver sor = new CRCLSchemaOutputResolver();
//            jaxbContext.generateSchema(sor);
//            return sor.getFile();
//        } catch (JAXBException | IOException ex) {
//            throw new CRCLException(ex);
//        }
//    }

    private final static File statSchemasFile = new File(CRCLUtils.getCrclUserHomeDir(),
            ".crcljava_stat_schemas.txt");
    private final static File cmdSchemasFile = new File(CRCLUtils.getCrclUserHomeDir(),
            ".crcljava_cmd_schemas.txt");
    private final static File programSchemasFile = new File(CRCLUtils.getCrclUserHomeDir(),
            ".crcljava_program_schemas.txt");

    private static final File crclSchemaDirFile;
    private static boolean resourcesCopied = false;

    static {
        File startFile = new File(CRCLUtils.getCrclUserHomeDir());
        crclSchemaDirFile = new File(startFile, "crclXmlSchemas");
    }

    private static File[] findSchemaFiles() {
        copySchemaResources();
        File files[] = crclSchemaDirFile.listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".xsd");
            }
        });
        if (files != null) {
            return files;
        }
        return EMPTY_FILE_ARRAY;
    }

    /**
     * Find schema files ( those that end in ".xsd" ) in a given directory.
     * @param dir directory to search   
     * @return array of files
     */
    public static File[] findSchemaFiles(File dir) {
        copySchemaResources(dir);
        File files[] = dir.listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".xsd");
            }
        });
        if (files != null) {
            return files;
        }
        return EMPTY_FILE_ARRAY;
    }

    private static void copySchemaResources() {
        if (resourcesCopied) {
            return;
        }
        boolean made_directory = crclSchemaDirFile.mkdirs();
        LOGGER.log(Level.FINEST, crclSchemaDirFile + "mkdirs() returned" + made_directory);
        copySchemaResources(crclSchemaDirFile);
        resourcesCopied = true;
    }

    /**
     * Copy schema resource files embedded in a jar to the
     * given directory.
     * @param directory for storing schema files.
     */
    public static void copySchemaResources(File directory) {
        copyResourcesToFiles(directory,
                "CRCLCommandInstance.xsd",
                "CRCLCommands.xsd",
                "CRCLProgramInstance.xsd",
                "DataPrimitives.xsd",
                "CRCLStatus.xsd");
    }

    private static String getVersion(String resourceName) {
        try {
            InputStream is = resourceAsStream(resourceName);
            Document doc
                    = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
            Element el = doc.getDocumentElement();
            String version = el.getAttribute("version");
            return version;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return "";
    }

    private static InputStream resourceAsStream(String resourceName) throws IllegalStateException {
        ClassLoader cl = CRCLStatusType.class.getClassLoader();
        if (null == cl) {
            throw new IllegalStateException("CRCLStatusType.class.getClassLoader() returned null");
        }
        InputStream is
                = cl.getResourceAsStream(resourceName);
        if (null == is) {
            throw new IllegalStateException("CRCLStatusType.class.getClassLoader().getResourceAsStream(" + resourceName + ") returned null");
        }
        return is;
    }

    private static Map<String, String> getSchemaVersions(String... resourcNames) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < resourcNames.length; i++) {
            String resourcName = resourcNames[i];
            map.put(resourcName, getVersion(resourcName));
        }
        return map;
    }

    /**
     * Get a map of embedded resource xsd schema filenames to embedded versions based
     * on the document element's version attribute.
     * 
     * @return map of version 
     */
    public static Map<String, String> getSchemaVersions() {
        return getSchemaVersions(
                "CRCLCommandInstance.xsd",
                "CRCLCommands.xsd",
                "CRCLProgramInstance.xsd",
                "DataPrimitives.xsd",
                "CRCLStatus.xsd");
    }

    static private void copyInputStreamToFile(InputStream is, File f) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(f)) {
            while (true) {
                byte buf[] = new byte[4096];
                int bytes_read = is.read(buf);
                if (bytes_read < 1) {
                    return;
                }
                fos.write(buf, 0, bytes_read);
            }
        }
    }

    /**
     *
     * @param dirFile the value of dirFile
     * @param names the value of names
     */
    private static void copyResourcesToFiles(File dirFile, String... names) {
        boolean made_directory = dirFile.mkdirs();
        LOGGER.log(Level.FINEST, dirFile + "mkdirs() returned" + made_directory);
        ClassLoader classLoader = CRCLStatusType.class.getClassLoader();
        if (null != classLoader) {
            for (String name : names) {
                try {
                    File f = new File(dirFile, name);
                    if (f.exists()
                            && (RESOURCE_CHANGE_TIME < 0
                            || (System.currentTimeMillis() - f.lastModified()) < RESOURCE_CHANGE_TIME)) {
                        continue;
                    }
                    InputStream resourceStream = classLoader.getResourceAsStream(name);
                    if (resourceStream != null) {
                        copyInputStreamToFile(
                                resourceStream,
                                f);

                    }
                } catch (MalformedURLException ex) {
                    LOGGER.log(Level.SEVERE, "Bad resource name " + name, ex);
                } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, "Failed to copy resource " + name, ex);
                }
            }
        }
    }

    /**
     * Read a list of schema files from either a default text file or a directory listing.
     * 
     * @return array of files
     */
    public static File[] readCmdSchemaFiles() {
        return readCmdSchemaFiles(cmdSchemasFile);
    }

    /**
     * Read a list of schema files from either the given text file or a directory listing.
     * 
     * @param schemaListFile a text file with a list of xsd schema files
     * @return array of files
     */
    public static File[] readCmdSchemaFiles(File schemaListFile) {
        if (schemaListFile.exists()
                && SCHEMA_CHANGE_TIME > 0
                && (System.currentTimeMillis() - schemaListFile.lastModified()) > SCHEMA_CHANGE_TIME) {
            boolean deleted = schemaListFile.delete();
            if (!deleted) {
                LOGGER.warning(schemaListFile + " not deleted");
            }
            saveCmdSchemaFiles(schemaListFile, findSchemaFiles());
        } else if (!schemaListFile.exists()) {
            saveCmdSchemaFiles(schemaListFile, findSchemaFiles());
        }
        if (!schemaListFile.exists()) {
            throw new IllegalStateException(schemaListFile + " does not exist.");
        }
        try {
            List<File> fl = readSchemaListFile(schemaListFile);
            fl = reorderAndFilterCommandSchemaFiles(fl);
            return toNonNullArray(fl, File.class);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Can not read " + schemaListFile, ex);
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        }
    }
    private static final long SCHEMA_CHANGE_TIME = getLongProperty("crcl.schemaChangeTime", 600000);

}
