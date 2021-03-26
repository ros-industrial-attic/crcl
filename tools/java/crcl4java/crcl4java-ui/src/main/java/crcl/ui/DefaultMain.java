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
 *  This software is experimental. NIST assumes no responsibility whatsoever for its use by other
 * parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic. We would appreciate
 * acknowledgement if the software is used. This software can be
 * redistributed and/or modified freely provided that any derivative works
 * bear some notice that they are derived from it, and any modified
 * versions bear some notice that they have been modified.
 * 
 *  See http://www.copyright.gov/title17/92chap1.html#105
 * 
 */
package crcl.ui;

import crcl.ui.server.CmdLineSimServer;
import crcl.ui.server.SimServerJFrame;
import crcl.ui.client.CrclSwingClientJFrame;
import crcl.ui.client.CmdLineClient;
import crcl.utils.kinematics.SimRobotEnum;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class DefaultMain {

    
    public static void main(String[] args) {
        String mode = "Launcher";
        for (int i = 0; i < args.length; i++) {
            if (i < args.length - 1) {
                if (args[i].compareTo("--port") == 0 || args[i].compareTo("-p") == 0) {
                    try {
                        int porti = Integer.parseInt(args[i + 1]);
                        if(porti < 1) {
                            System.err.println("port ("+porti+") must be positive.");
                        }
                        System.setProperty("crcl4java.port", Integer.toString(porti));
                    } catch (NumberFormatException numberFormatException) {
                        System.err.println("port\"("+args[i+1]+"\" must be an integer");
                        System.exit(i);
                    }
                    i++;
                    continue;
                } else if (args[i].compareTo("--host") == 0 || args[i].compareTo("-h") == 0) {
                    System.setProperty("crcl4java.host", args[i + 1]);
                    i++;
                    continue;
                } else if (args[i].compareTo("--program") == 0) {
                    System.setProperty("crcl4java.program", args[i + 1]);
                    i++;
                    continue;
                } else if (args[i].compareTo("--mode") == 0 || args[i].compareTo("-m") == 0) {
                    mode = args[i + 1];
                    i++;
                    continue;
                }
            }
            if (args[i].startsWith("-")) {
                if(!"--help".equals(args[i])) {
                    System.err.println("Unrecognized argument : " + args[i]);
                    System.err.println("args="+Arrays.toString(args));
                }
                System.err.println("Options are:");
                System.err.println("\t--help\tPrint this help message.");
                System.err.println("\t--port\tSet main tcp port.");
                System.err.println("\t--host\tSet tcp port used by client to connect.");
                System.err.println("\t--program\tSet xml program file to be sent by client one command at a time.");
                System.err.println("\t--mode\tSet mode to one of:"
                        +"\n\t\tLauncher,CmdLineClient,CmdLineServer,GraphicalServer,GraphicalClient");
                System.err.println("");
                System.err.println("The following properties can be set with -Dpropertyname=value before the -jar arguments.");
                System.err.println("eg:");
                System.err.println("java -Dcrcl4java.simserver.logimages=true -jar crcl4java-ui-1.8-jar-with-dependencies.jar --mode GraphicalServer");
                System.err.println("");
                System.err.println("crcl4java.simserver.imagelogdir\tDirectory for simserver to log images to. Default=/tmp/ ");
                System.err.println("");
                System.err.println("crcl4java.simserver.logimages\tSet to true to have simserver log images. Default=false ");
                System.err.println("");
                System.err.println("crcl4java.simserver.maxTransSpeed\tSet to max translational speed. Default=2.0");
                System.err.println("");
                System.err.println("crcl4java.simserver.maxRotSpeed\tSet to max rotational speed. Default=2.0");
                System.err.println("");
                System.err.println("crcl4java.simserver.delayMillis\tNumber of milliseconds to sleep in simulation thread. Default=100");
                System.err.println("");
                System.err.println("crcl4java.simserver.robottype\tType of robot to simulate. Default="+SimRobotEnum.SIMPLE+" Options include"+Arrays.toString(SimRobotEnum.values()));
                System.err.println("");
                System.err.println("crcl4java.simserver.teleportToGoals\tWhenever the server receives a command to go somehere it will immediately report back that it is DONE and exactly there. Makes programs for testing run fast. Default=false");
                System.err.println("");
                
                
                System.err.println("crcl4java.client.quitOnTestCommandFailure\tHave the client stop running a program if a command test fails. eg. MoveTo results in server reporting DONE but position is not within tolerance of the EndPoint. Default=false. NOTE: A server reporting ERROR always stops the program regardless of this setting. ");
                System.err.println("");
                
                System.exit(1);
            }
        }
        System.out.println("mode = " + mode);
        switch (mode) {
            case "Launcher":
                LauncherJFrame.main(args);
                break;

            case "CmdLineClient":
                CmdLineClient.main(args);
                break;

            case "CmdLineServer":
                CmdLineSimServer.main(args);
                break;

            case "GraphicalServer":
                SimServerJFrame.main(args);
                break;

            case "GraphicalClient":
                CrclSwingClientJFrame.main(args);
                break;
                
            default:
                System.err.println("Unrecognized mode:"+mode);
                System.err.println("Options are:");
                System.err.println("\t--mode\tSet mode to one of:"
                        +"\n\t\tLauncher,CmdLineClient,CmdLineServer,GraphicalServer,GraphicalClient");
                System.exit(1);
        }
    }
    private static final Logger LOG = Logger.getLogger(DefaultMain.class.getName());
}
