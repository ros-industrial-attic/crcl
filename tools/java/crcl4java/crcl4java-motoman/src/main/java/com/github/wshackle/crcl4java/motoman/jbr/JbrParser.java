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
package com.github.wshackle.crcl4java.motoman.jbr;

import crcl.base.CRCLProgramType;
import crcl.base.EndCanonType;
import crcl.base.InitCanonType;
import crcl.base.MoveToType;
import crcl.base.PoseType;
import crcl.base.SetEndEffectorType;
import crcl.utils.CRCLPosemath;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rcs.posemath.PmCartesian;
import rcs.posemath.PmException;
import rcs.posemath.PmRpy;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class JbrParser {

    private final List<JbrPose> jbrPoses = new ArrayList<>();
    private final Map<Integer, JbrPose> jbrIndexMap = new HashMap<>();
    private final Map<String, JbrPose> jbrNameMap = new HashMap<>();
    private final Map<String, List<String>> progMap = new HashMap<>();

    public void parse(File f) throws IOException, PmException {
        boolean readingAliases = false;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line = null;
            String curJobName = null;
            boolean jobStarted = false;
            while (null != (line = br.readLine())) {
                if (line.matches("P\\d\\d\\d\\d\\d=.*")) {
                    System.out.println("line = " + line);
                    int index = Integer.parseInt(line.substring(1, 6), 10);
                    System.out.println("index = " + index);
                    String fa[] = line.substring(7).split("[ ,]+");
                    System.out.println("fa = " + Arrays.toString(fa));

                    PmCartesian cart = new PmCartesian(Double.parseDouble(fa[0]), Double.parseDouble(fa[1]), Double.parseDouble(fa[2]));
                    PmRpy rpy = new PmRpy(Math.toRadians(Double.parseDouble(fa[5])), Math.toRadians(Double.parseDouble(fa[4])), Math.toRadians(Double.parseDouble(fa[3])+Math.PI));
                    PoseType pose = CRCLPosemath.toPoseType(cart, rcs.posemath.Posemath.toRot(rpy));
                    JbrPose jbrPose = new JbrPose("", index, pose);
                    System.out.println("jbrPose = " + jbrPose);
                    jbrPoses.add(jbrPose);
                    jbrIndexMap.put(index, jbrPose);
                } else if (line.equals("//ALIAS")) {
                    readingAliases = true;
                    System.out.println("readingAliases = " + readingAliases);
                } else if (line.equals("//INST")) {
                    readingAliases = false;
                    System.out.println("readingAliases = " + readingAliases);
                } else if (line.startsWith("//NAME ")) {
                    curJobName = line.substring("//NAME ".length()).trim();
                    System.out.println("curJobName = " + curJobName);
                    progMap.put(curJobName, new ArrayList<String>());
                } else if (line.equals("NOP")) {
                    jobStarted = true;
                    System.out.println("jobStarted = " + jobStarted);
                } else if (line.equals("END")) {
                    jobStarted = false;
                    System.out.println("jobStarted = " + jobStarted);
                }
                if (jobStarted) {
                    List<String> l = progMap.get(curJobName);
                    if (null != l) {
                        l.add(line);
                    }
                }
                if (readingAliases) {
                    if (line.matches("P\\d\\d\\d.* [^ ]*")) {
                        String fa[] = line.substring(1).split("[ ]+");
                        int index = Integer.parseInt(fa[0]);
                        System.out.println("index = " + index);
                        String name = fa[1];
                        System.out.println("name = " + name);
                        JbrPose jbrPose = jbrIndexMap.get(index);
                        if (null != jbrPose) {
                            jbrPose.setName(name);
                            jbrNameMap.put(name, jbrPose);
                            System.out.println("jbrNameMap = " + jbrNameMap);
                        }
                    }
                }
            }
        }
    }

    JbrPose getPose(String poseNameOrId) {
        if (poseNameOrId.matches("P\\d\\d\\d.* [^ ]*")) {
            int index = Integer.parseInt(poseNameOrId.substring(1));
            return jbrIndexMap.get(index);
        } else {
            return jbrNameMap.get(poseNameOrId);
        }
    }

    public CRCLProgramType getProgram(String progName) {
        CRCLProgramType program = new CRCLProgramType();
        InitCanonType initCanon = new InitCanonType();
        initCanon.setCommandID(1);
        program.setInitCanon(initCanon);
        EndCanonType endCanon = new EndCanonType();
        List<String> l = progMap.get(progName);
        for (String line : l) {
            if (line.startsWith("MOVJ")) {
                String fa[] = line.substring(1).split("[ ]+");
                JbrPose jbPose = getPose(fa[1]);
                System.out.println("jbPose = " + jbPose);
                MoveToType moveTo = new MoveToType();
                moveTo.setCommandID(program.getMiddleCommand().size() + 2);
                moveTo.setEndPosition(jbPose.getPose());
                program.getMiddleCommand().add(moveTo);

            } else if (line.startsWith("MOVL")) {
                String fa[] = line.substring(1).split("[ ]+");
                JbrPose jbPose = getPose(fa[1]);
                    System.out.println("jbPose = " + jbPose);
                    MoveToType moveTo = new MoveToType();
                    moveTo.setMoveStraight(true);
                    moveTo.setCommandID(program.getMiddleCommand().size() + 2);
                    moveTo.setEndPosition(jbPose.getPose());
                    program.getMiddleCommand().add(moveTo);
            } else if (line.startsWith("SET")) {
                String fa[] = line.substring(1).split("[ ]+");
                JbrPose jbPose1 = getPose(fa[1]);
                JbrPose jbPose2 = getPose(fa[2]);
                jbPose1.setPose(jbPose2.getPose());
            }  else if (line.startsWith("ADD")) {
                String fa[] = line.substring(1).split("[ ]+");
                JbrPose jbPose1 = getPose(fa[1]);
                JbrPose jbPose2 = getPose(fa[2]);
                jbPose1.setPose(CRCLPosemath.multiply(jbPose1.getPose(),jbPose2.getPose()));
            } else if (line.equals("CALL JOB:GRIPCLOSE")) {
                SetEndEffectorType seeCmd = new SetEndEffectorType();
                seeCmd.setCommandID(program.getMiddleCommand().size() + 2);
                seeCmd.setSetting(0.0);
                program.getMiddleCommand().add(seeCmd);
            } else if (line.equals("CALL JOB:GRIPPEROPEN")) {
                SetEndEffectorType seeCmd = new SetEndEffectorType();
                seeCmd.setCommandID(program.getMiddleCommand().size() + 2);
                seeCmd.setSetting(1.0);
                program.getMiddleCommand().add(seeCmd);
            } else if (line.equals("CALL JOB:HOME")) {
                JbrPose jbPose = jbrNameMap.get("HOMEPOS");
                System.out.println("jbPose = " + jbPose);
                MoveToType moveTo = new MoveToType();
                moveTo.setCommandID(program.getMiddleCommand().size() + 2);
                moveTo.setEndPosition(jbPose.getPose());
                program.getMiddleCommand().add(moveTo);
            }
        }
        endCanon.setCommandID(program.getMiddleCommand().size() + 2);
        program.setEndCanon(endCanon);
        return program;
    }

    public JbrPose getByName(String name) {
        return jbrNameMap.get(name);
    }

//    public static void main(String[] args) throws IOException, PmException, CRCLException, JAXBException {
//
//        JbrParser jp = new JbrParser();
//        jp.parse(new File(Utils.getCrclUserHomeDir(), "MOVELGEAR.JBR"));
//
//        System.out.println("Generating program");
//        CRCLProgramType program = jp.getProgram("MOVELGEAR");
//        String programString = CRCLSocket.getUtilSocket().programToPrettyDocString(program, true);
//        System.out.println("programString = " + programString);
//        Files.write(Paths.get(Utils.getCrclUserHomeDir(), "MOVELGEAR_CRLC.xml"),
//                programString.getBytes(), StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
//        if (true) {
//            return;
//        }
////        CRCLProgramType program = new CRCLProgramType();
//        InitCanonType initCanon = new InitCanonType();
//        initCanon.setCommandID(1);
//        program.setInitCanon(initCanon);
//        EndCanonType endCanon = new EndCanonType();
//        MoveToType moveTo = new MoveToType();
//        moveTo.setCommandID(2);
//        PoseType pose = jp.getByName("HOMEPOS").getPose();
//        moveTo.setEndPosition(pose);
//        endCanon.setCommandID(3);
//        program.setEndCanon(endCanon);
//
//        CRCLSocket socket = new CRCLSocket("localhost", CRCLSocket.DEFAULT_PORT);
//        CRCLCommandInstanceType inst = new CRCLCommandInstanceType();
//        inst.setCRCLCommand(initCanon);
//        socket.writeCommand(inst);
//        CRCLStatusType status = null;
//        while (status == null || status.getCommandStatus().getCommandState() == CRCL_WORKING) {
//            CRCLCommandInstanceType getStatusInst = new CRCLCommandInstanceType();
//            GetStatusType getStatus = new GetStatusType();
//            getStatus.setCommandID(4);
//            getStatusInst.setCRCLCommand(getStatus);
//            socket.writeCommand(getStatusInst);
//            status = socket.readStatus();
////            System.out.println("status = " + socket.statusToPrettyString(status, true));
//        }
//        moveTo.setCommandID(5);
//        inst.setCRCLCommand(moveTo);
//        socket.writeCommand(inst);
//        status = null;
//        while (status == null || status.getCommandStatus().getCommandState() == CRCL_WORKING) {
//            CRCLCommandInstanceType getStatusInst = new CRCLCommandInstanceType();
//            GetStatusType getStatus = new GetStatusType();
//            getStatus.setCommandID(6);
//            getStatusInst.setCRCLCommand(getStatus);
//            socket.writeCommand(getStatusInst);
//            status = socket.readStatus();
////            System.out.println("status = " + socket.statusToPrettyString(status, true));
//        }
//    }
}
