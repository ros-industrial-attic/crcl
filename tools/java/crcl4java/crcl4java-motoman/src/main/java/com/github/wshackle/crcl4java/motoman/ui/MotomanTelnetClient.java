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
package com.github.wshackle.crcl4java.motoman.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.telnet.TelnetClient;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class MotomanTelnetClient {

    public static final void main(String[] args) throws IOException {

        final InputStream localInputStream = System.in;
        final PrintStream localPrintStream = System.out;
        MotomanTelnetClient telnet;

        telnet = defaultMotoman(localPrintStream, localInputStream);

//        }, "localToRemote");
//        IOUtil.readWrite(open.getInputStream(), open.getOutputStream(),
//                         System.in, System.out);
//        try {
//            telnet.disconnect();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//
//        System.exit(0);
    }

    public static final String DEFAULT_MOTOMAN_USERNAME = "MOTOMANrobot";
    public static final String DEFAULT_MOTOMAN_PASSWD = "MOTOMANrobot";
    public static final String DEFAULT_MOTOMAN_HOST = "10.0.0.2";
    public static final int DEFAULT_PORT = 23;
    TelnetClient telnet;
    Thread remoteToLocalThread;
    Thread localToRemoteThread;

    public void disconnect() {
        if (null != telnet) {
            try {
                telnet.disconnect();
            } catch (IOException ex) {
                Logger.getLogger(MotomanTelnetClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        telnet = null;
        if (null != remoteToLocalThread) {
            if (remoteToLocalThread.isAlive()) {
                remoteToLocalThread.interrupt();
                try {
                    remoteToLocalThread.join(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MotomanTelnetClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            remoteToLocalThread = null;
        }
        if (null != localToRemoteThread) {
            if (localToRemoteThread.isAlive()) {
                localToRemoteThread.interrupt();
                try {
                    localToRemoteThread.join(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MotomanTelnetClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            localToRemoteThread = null;
        }
    }

    public static MotomanTelnetClient defaultMotoman(final PrintStream localPrintStream, final InputStream localInputStream) throws IOException {

        return open(DEFAULT_MOTOMAN_HOST, DEFAULT_PORT, localPrintStream, DEFAULT_MOTOMAN_USERNAME, DEFAULT_MOTOMAN_PASSWD, localInputStream);
    }

    public static MotomanTelnetClient defaultMotomanWithHostPort(String host, int port, final PrintStream localPrintStream, final InputStream localInputStream) throws IOException {
        return open(host, port, localPrintStream, DEFAULT_MOTOMAN_USERNAME, DEFAULT_MOTOMAN_PASSWD, localInputStream);
    }

    public static MotomanTelnetClient open(final String host, final int port, final PrintStream localPrintStream, final String userName, final String passwd, final InputStream localInputStream) throws IOException {
        MotomanTelnetClient motomanTelnetClient = new MotomanTelnetClient();
        motomanTelnetClient.telnet = new TelnetClient();;

//            open.connect("rainmaker.wunderground.com", 3000);
        motomanTelnetClient.telnet.connect(host, port);

        final InputStream remoteInputStream = motomanTelnetClient.telnet.getInputStream();
        final OutputStream remoteOutputStream = motomanTelnetClient.telnet.getOutputStream();
        motomanTelnetClient.remoteToLocalThread = new Thread(() -> {
            try {
                int ib = -1;
                while (0 <= (ib = remoteInputStream.read())) {
                    char c = (char) ib;
                    localPrintStream.print(c);
                }
            } catch (IOException ex) {
                Logger.getLogger(MotomanTelnetClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }, "remoteToLocalTelnet");
        /*
        open 10.0.0.2
        MOTOMANrobot
        MOTOMANrobot
        
        
        
         */
        motomanTelnetClient.remoteToLocalThread.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(MotomanTelnetClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        remoteOutputStream.write((userName + "\r\n").getBytes());
        remoteOutputStream.flush();
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(MotomanTelnetClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        remoteOutputStream.write((passwd + "\r\n").getBytes());
        remoteOutputStream.flush();
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(MotomanTelnetClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        motomanTelnetClient.localToRemoteThread = new Thread(() -> {
            try {
                int ib = -1;
                while (0 <= (ib = localInputStream.read())) {
//                System.out.println("line = " + line);
                    byte ba[] = new byte[1];
                    ba[0] = (byte) ib;
                    if (ib == ((int) '\n')) {
                        remoteOutputStream.write("\r\n".getBytes());
                        remoteOutputStream.flush();
                    } else {

                        remoteOutputStream.write(ba);
                        remoteOutputStream.flush();

                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(MotomanTelnetClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }, "localToRemoteTelnet");
        motomanTelnetClient.localToRemoteThread.start();
        return motomanTelnetClient;
    }
}
