package crcl.ui;

import crcl.utils.CRCLSchemaUtils;
import crcl.utils.CRCLUtils;
import java.io.File;
import java.io.IOException;

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
/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class DefaultSchemaFiles {

    private final File cmdSchemasFile;
    private final File statSchemasFile;
    private final File programSchemasFile;

    public DefaultSchemaFiles(File cmdSchemasFile, File statSchemasFile, File programSchemasFile) {
        int cmdSchemaFilesLength;
        File startingCmdSchemaFiles[];
        boolean startingCmdSchemasFileExists = cmdSchemasFile.exists();
        boolean allStartingCmdSchemaFilesExist = true;
        if(startingCmdSchemasFileExists) {
            startingCmdSchemaFiles = CRCLSchemaUtils.readCmdSchemaFiles(cmdSchemasFile);
            for (int i = 0; i < startingCmdSchemaFiles.length; i++) {
                File startingCmdSchemaFile = startingCmdSchemaFiles[i];
                if(allStartingCmdSchemaFilesExist && !startingCmdSchemaFile.exists()) {
                    System.err.println("startingCmdSchemaFile="+startingCmdSchemaFile+" does not exist.");
                    allStartingCmdSchemaFilesExist=false;
                    break;
                }
            }
            cmdSchemaFilesLength= startingCmdSchemaFiles.length;
        } else {
            startingCmdSchemaFiles = null;
            cmdSchemaFilesLength= 0;
        }
        if (!startingCmdSchemasFileExists || !allStartingCmdSchemaFilesExist || cmdSchemaFilesLength < 1) {
            File resourceDir = new File(cmdSchemasFile.getParentFile(), "crclSchemas");
            resourceDir.mkdirs();
            CRCLSchemaUtils.copySchemaResources(resourceDir);
            File[] fa = CRCLSchemaUtils.findSchemaFiles(resourceDir);
            CRCLSchemaUtils.saveCmdSchemaFiles(cmdSchemasFile, fa);
        }

        int statSchemaFilesLength;
        File startingStatSchemaFiles[];
        boolean startingStatSchemasFileExists = statSchemasFile.exists();
        boolean allStartingStatSchemaFilesExist = true;
        if(startingStatSchemasFileExists) {
            startingStatSchemaFiles = CRCLSchemaUtils.readStatSchemaFiles(statSchemasFile);
            for (int i = 0; i < startingStatSchemaFiles.length; i++) {
                File startingStatSchemaFile = startingStatSchemaFiles[i];
                if(allStartingCmdSchemaFilesExist && !startingStatSchemaFile.exists()) {
                    System.err.println("startingStatSchemaFile="+startingStatSchemaFile+" does not exist.");
                    allStartingStatSchemaFilesExist=false;
                    break;
                }
            }
            statSchemaFilesLength= startingStatSchemaFiles.length;
            
        } else {
            startingStatSchemaFiles = null;
            statSchemaFilesLength= 0;
        }
        if (!startingStatSchemasFileExists || !allStartingStatSchemaFilesExist || statSchemaFilesLength < 1) {
            File resourceDir = new File(statSchemasFile.getParentFile(), "crclSchemas");
            resourceDir.mkdirs();
            CRCLSchemaUtils.copySchemaResources(resourceDir);
            File[] fa = CRCLSchemaUtils.findSchemaFiles(resourceDir);
            CRCLSchemaUtils.saveStatSchemaFiles(statSchemasFile, fa);
        }
        
        int programSchemaFilesLength;
        File startingProgramSchemaFiles[];
        boolean startingProgramSchemasFileExists = programSchemasFile.exists();
        if(startingProgramSchemasFileExists) {
            startingProgramSchemaFiles = CRCLSchemaUtils.readProgramSchemaFiles(programSchemasFile);
            programSchemaFilesLength= startingProgramSchemaFiles.length;
        } else {
            startingProgramSchemaFiles = null;
            programSchemaFilesLength= 0;
        }
        if (!startingProgramSchemasFileExists || programSchemaFilesLength < 1) {
            File resourceDir = new File(programSchemasFile.getParentFile(), "crclSchemas");
            resourceDir.mkdirs();
            CRCLSchemaUtils.copySchemaResources(resourceDir);
            File[] fa = CRCLSchemaUtils.findSchemaFiles(resourceDir);
            CRCLSchemaUtils.saveProgramSchemaFiles(programSchemasFile, fa);
        }
        this.cmdSchemasFile = cmdSchemasFile;
        this.statSchemasFile = statSchemasFile;
        this.programSchemasFile = programSchemasFile;
    }
    private static final String HOME = CRCLUtils.getCrclUserHomeDir();
    private static final String SCHEMA_FILES_DIR = System.getProperty("crcl4java.schemaFilesDir", HOME);
    
//    private static final String HOME = 

    private final static File cmdSchemasFileStaticDefault = new File(SCHEMA_FILES_DIR,
            ".crcljava_ui_cmd_schemas.txt");

    private final static File statSchemasFileStaticDefault = new File(SCHEMA_FILES_DIR,
            ".crcljava_ui_stat_schemas.txt");

    private final static File programSchemasFileStaticDefault = new File(SCHEMA_FILES_DIR,
            ".crcljava_ui_program_schemas.txt");

    private static final DefaultSchemaFiles INSTANCE = new DefaultSchemaFiles(
            cmdSchemasFileStaticDefault,
            statSchemasFileStaticDefault,
            programSchemasFileStaticDefault
    );

    public static DefaultSchemaFiles instance() {
        return INSTANCE;
    }

    public static DefaultSchemaFiles temp() throws IOException {
        File tmpDirFile = File.createTempFile("crclResources", ".holder");
        File resourceDir = new File(tmpDirFile.getParentFile(), tmpDirFile.getName() + "_dir");
        resourceDir.mkdirs();
        CRCLSchemaUtils.copySchemaResources(resourceDir);
        File[] fa = CRCLSchemaUtils.findSchemaFiles(resourceDir);
        File cmdFile = File.createTempFile("crcljava_ui_cmd_schemas", ".txt");
        CRCLSchemaUtils.saveCmdSchemaFiles(cmdFile, fa);
        File statFile = File.createTempFile("crcljava_ui_stat_schemas", ".txt");
        CRCLSchemaUtils.saveStatSchemaFiles(statFile, fa);
        File progFile = File.createTempFile("crcljava_ui_program_schemas", ".txt");
        CRCLSchemaUtils.saveProgramSchemaFiles(progFile, fa);
        return new DefaultSchemaFiles(cmdFile,
                statFile,
                progFile);
    }

    public File getCmdSchemasFile() {
        return cmdSchemasFile;
    }

    public File getStatSchemasFile() {
        return statSchemasFile;
    }

    public File getProgramSchemasFile() {
        return programSchemasFile;
    }

}
