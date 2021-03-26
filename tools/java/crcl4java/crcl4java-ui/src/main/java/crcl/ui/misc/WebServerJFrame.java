/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crcl.ui.misc;

import static crcl.ui.IconImages.SERVER_IMAGE;
import crcl.utils.CRCLUtils;
import crcl.utils.PropertiesUtils;
import java.awt.Desktop;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JFileChooser;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class WebServerJFrame extends javax.swing.JFrame {

    static final private List<WebServerJFrame> allWebServers = new ArrayList<>();

    private static boolean shutdownHookRegistered = false;

    private static void shutdownAll() {
        System.out.println("Starting WebServerJFrame.shutdownAll()");
        for (int i = 0; i < allWebServers.size(); i++) {
            try {
                WebServerJFrame ws = allWebServers.get(i);
                if (null != ws) {
                    ws.stop();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("i = " + i + " / " + allWebServers.size());
        }
        allWebServers.clear();
        System.out.println("Finished WebServerJFrame.shutdownAll()");
    }

    static private void registerShutdownHook() {
        if (!shutdownHookRegistered) {
            synchronized (WebServerJFrame.class) {
                Runtime.getRuntime().addShutdownHook(new Thread(WebServerJFrame::shutdownAll, "webServerShutdownThread"));
                shutdownHookRegistered = true;
            }
        }
    }

    static private Optional<File> findJarInDir(@Nullable String dir) {
        if (dir == null) {
            return Optional.empty();
        }
        File fa[] = new File(dir).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("crcl4java-vaadin-webapp") && name.endsWith("war-exec.jar");
            }
        });
        if (null != fa && fa.length > 0) {
            return Optional.of(fa[0]);
        }
        return Optional.empty();
    }

    static private Optional<File> findFileInDir(@Nullable String dir, @Nullable String filename) {
        if (dir == null) {
            return Optional.empty();
        }
        if (filename == null) {
            return Optional.empty();
        }
        File fa[] = new File(dir).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.equals(filename);
            }
        });
        if (null != fa && fa.length > 0) {
            return Optional.of(fa[0]);
        }
        return Optional.empty();
    }

    private static @Nullable
    File findJar() {
        String userDir = System.getProperty("user.dir");
        String parentDir = new File(userDir).getParent();
        return Stream.of(userDir,
                userDir + File.separator + "target",
                userDir + File.separator + "crcl4java-vaadin-webapp" + File.separator + "target",
                parentDir,
                parentDir + File.separator + "target",
                parentDir + File.separator + "crcl4java-vaadin-webapp" + File.separator + "target"
        )
                .map(WebServerJFrame::findJarInDir)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst().orElse(null);
    }

    private static @Nullable
    File findFile(String name) {
        String userDir = System.getProperty("user.dir");
        String parentDir = new File(userDir).getParent();
        Function<@Nullable String, Optional<File>> finder
                = (@Nullable String dir) -> findFileInDir(dir, name);
        return Stream.of(userDir,
                userDir + File.separator + "target",
                userDir + File.separator + "crcl4java-vaadin-webapp" + File.separator + "target",
                parentDir,
                parentDir + File.separator + "target",
                parentDir + File.separator + "crcl4java-vaadin-webapp" + File.separator + "target"
        )
                .map(finder)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst().orElse(null);
    }

    private String getDefaultCommandString() {
        if (System.getProperty("os.name").startsWith("Windows")) {
            File jarFile = findJar();
            if (null != jarFile) {
                try {
                    return "java -jar " + jarFile.getCanonicalPath();
                } catch (IOException ex) {
                    Logger.getLogger(WebServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            File batFile = findFile("runWebApp.bat");
            if (null != batFile) {
                try {
                    return batFile.getCanonicalPath();
                } catch (IOException ex) {
                    Logger.getLogger(WebServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return "";
        } else {
            return (System.getProperty("user.dir") + File.separator + "runWebApp.sh");
        }
    }

    /**
     * Creates new form WebServerJFrame
     */
    @SuppressWarnings("initialization")
    public WebServerJFrame() {
        initComponents();
        registerShutdownHook();
        try {
            setURL("http://" + InetAddress.getLocalHost().getHostName() + ":8080/crcl4java-vaadin-webapp");
        } catch (UnknownHostException ex) {
            Logger.getLogger(WebServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        setDirectoryString(System.getProperty("user.dir"));
        readProperties();
        if (!commandStringSet) {
            setCommandString(getDefaultCommandString());
        }
        setIconImage(SERVER_IMAGE);
        allWebServers.add(this);
    }

    @Override
    final public void setIconImage(Image image) {
        super.setIconImage(image);
    }

    public void setCommandString(String s) {
        try {
            if (javax.swing.SwingUtilities.isEventDispatchThread()) {
                jTextFieldCommand.setText(s);
            } else {
                javax.swing.SwingUtilities.invokeAndWait(() -> jTextFieldCommand.setText(s));
            }
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(WebServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setDirectoryString(String s) {
        try {
            if (javax.swing.SwingUtilities.isEventDispatchThread()) {
                jTextFieldDirectory.setText(s);
            } else {
                javax.swing.SwingUtilities.invokeAndWait(() -> jTextFieldDirectory.setText(s));
            }
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(WebServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getCommandString() {
        return jTextFieldCommand.getText();
    }

    public void setURL(String s) {
        try {
            if (javax.swing.SwingUtilities.isEventDispatchThread()) {
                jTextFieldURL.setText(s);
            } else {
                javax.swing.SwingUtilities.invokeAndWait(() -> jTextFieldURL.setText(s));
            }
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(WebServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getURL() {
        return jTextFieldURL.getText();
    }

    public String getDirectoryString() {
        return jTextFieldDirectory.getText();
    }

    volatile private @Nullable
    Runnable onStopRunnable = null;

    public void setOnStopRunnable(Runnable r) {
        this.onStopRunnable = r;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxRun = new javax.swing.JCheckBox();
        jTextFieldCommand = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldDirectory = new javax.swing.JTextField();
        jButtonDirBrowse = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaConsoleOutput = new javax.swing.JTextArea();
        jButtonClearOutput = new javax.swing.JButton();
        jButtonFullLog = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldURL = new javax.swing.JTextField();
        jButtonView = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CRCL Web Server Control");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jCheckBoxRun.setText("Run");
        jCheckBoxRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxRunActionPerformed(evt);
            }
        });

        jTextFieldCommand.setText("C:\\Users\\Public\\Documents\\runWebApp.bat");
        jTextFieldCommand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCommandActionPerformed(evt);
            }
        });

        jLabel1.setText("Directory:");

        jTextFieldDirectory.setText("C:\\Users\\Public\\Documents\\");
            jTextFieldDirectory.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextFieldDirectoryActionPerformed(evt);
                }
            });

            jButtonDirBrowse.setText("Browse");
            jButtonDirBrowse.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonDirBrowseActionPerformed(evt);
                }
            });

            jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Console Output"));

            jTextAreaConsoleOutput.setColumns(20);
            jTextAreaConsoleOutput.setRows(5);
            jScrollPane1.setViewportView(jTextAreaConsoleOutput);

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1)
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addContainerGap())
            );

            jButtonClearOutput.setText("Clear Output");
            jButtonClearOutput.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonClearOutputActionPerformed(evt);
                }
            });

            jButtonFullLog.setText("Full Log");
            jButtonFullLog.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonFullLogActionPerformed(evt);
                }
            });

            jLabel2.setText("URL:");

            jTextFieldURL.setText("http://localhost:8080/crcl4java-vaadin-webapp/");

            jButtonView.setText("View");
            jButtonView.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonViewActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jCheckBoxRun)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldCommand))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jButtonFullLog)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonClearOutput))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldURL))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldDirectory, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButtonDirBrowse)
                                .addComponent(jButtonView, javax.swing.GroupLayout.Alignment.TRAILING))))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBoxRun)
                        .addComponent(jTextFieldCommand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextFieldDirectory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonDirBrowse))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTextFieldURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonView))
                    .addGap(2, 2, 2)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonClearOutput)
                        .addComponent(jButtonFullLog))
                    .addGap(28, 28, 28))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldDirectoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDirectoryActionPerformed
        saveProperties();
    }//GEN-LAST:event_jTextFieldDirectoryActionPerformed

    private volatile @Nullable
    Process internalProcess;
    
    private volatile @Nullable
    Thread monitorOutputThread;
    
    private volatile @Nullable
    Thread monitorErrorThread;

    private List<String> consoleStrings = new LinkedList<String>();

    private void consoleAppend(String s) {
        if (consoleStrings.size() > maxLoggedStrings) {
            consoleStrings.remove(0);
        }
        consoleStrings.add(s);
        String txt = consoleStrings.stream().collect(Collectors.joining());
        jTextAreaConsoleOutput.setText(txt);
        jTextAreaConsoleOutput.setCaretPosition(txt.length());
    }
    private int maxLoggedStrings = 250;

    public int getMaxLoggedStrings() {
        return maxLoggedStrings;
    }

    public void setMaxLoggedStrings(int maxLoggedStrings) {
        this.maxLoggedStrings = maxLoggedStrings;
    }

    private volatile @Nullable
    PrintWriter logger = null;

    private @Nullable
    File logFile = null;

    private synchronized PrintWriter getLogger() throws IOException {
        if (logger == null) {
            File newLogFile = File.createTempFile("webserverlog", ".txt");
            System.out.println("logFile = " + newLogFile);
            logger = new PrintWriter(new FileWriter(newLogFile));
            this.logFile = newLogFile;
        }
        return logger;
    }

    private void monitorInternalProcessOutput() {
        Process internalProcess1 = internalProcess;
        if (null == internalProcess1) {
            throw new IllegalStateException("null == internalProcess");
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(internalProcess1.getInputStream()))) {
            String line = null;
            while (null != (line = br.readLine()) && !Thread.currentThread().isInterrupted()) {
                System.out.println(line);
                getLogger().println(line);
                final String s = line;
                javax.swing.SwingUtilities.invokeLater(() -> consoleAppend(s + "\n"));
            }
        } catch (IOException ex) {
            Logger.getLogger(WebServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.SwingUtilities.invokeLater(() -> consoleAppend(ex.toString()));
        }
    }

    private void monitorInternalProcessError() {
        Process internalProcess1 = internalProcess;
        if (null == internalProcess1) {
            throw new IllegalStateException("null == internalProcess");
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(internalProcess1.getErrorStream()))) {
            String line = null;
            while (null != (line = br.readLine()) && !Thread.currentThread().isInterrupted()) {
                System.err.println(line);
                getLogger().println("#ERROR " + line);
                final String s = line;
                javax.swing.SwingUtilities.invokeLater(() -> consoleAppend("\nERROR:" + s + "\n"));
            }
        } catch (IOException ex) {
            Logger.getLogger(WebServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.SwingUtilities.invokeLater(() -> consoleAppend(ex.toString() + "\n"));
        }
    }

    private void jCheckBoxRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxRunActionPerformed
        try {
            stop();
            jTextAreaConsoleOutput.setText("");
            if (this.jCheckBoxRun.isSelected()) {
                start();
            }
        } catch (Exception ex) {
            Logger.getLogger(WebServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.SwingUtilities.invokeLater(() -> consoleAppend(ex.toString()));
        }
    }//GEN-LAST:event_jCheckBoxRunActionPerformed

    public void start() throws Exception {
        try {
            internalProcess = new ProcessBuilder(jTextFieldCommand.getText().split("[ \t]+"))
                    .directory(new File(jTextFieldDirectory.getText()))
                    .redirectOutput(ProcessBuilder.Redirect.PIPE)
                    .redirectError(ProcessBuilder.Redirect.PIPE)
                    .start();
            monitorOutputThread = new Thread(this::monitorInternalProcessOutput, "monitorFingerSensorConsole");
            monitorOutputThread.start();
            monitorErrorThread = new Thread(this::monitorInternalProcessError, "monitorFingerSensorError");
            monitorErrorThread.start();
            if (!jCheckBoxRun.isSelected()) {
                jCheckBoxRun.setSelected(true);
            }
        } catch (Exception exception) {
            javax.swing.SwingUtilities.invokeLater(() -> consoleAppend(exception.toString()));
            throw new Exception(exception);
        }
    }

    private synchronized void closeLogger() {
        if (null != logger) {
            logger.close();
            logger = null;
        }
    }

    public void stop() {
        Runnable onStopRunnable1 = onStopRunnable;
        if (null != onStopRunnable1) {
            System.out.println("Starting WebServerJFrame : onStopRunnable.run()");
            onStopRunnable1.run();
            System.out.println("Finished WebServerJFrame : onStopRunnable.run()");
        }
        Process internalProcess1 = internalProcess;
        if (null != internalProcess1) {
            try {
                System.out.println("Starting WebServerJFrame : internalProcess.destroy()");
                internalProcess1.destroy();
                internalProcess1.waitFor(10, TimeUnit.SECONDS);
                int exit_code = internalProcess1.exitValue();
                System.out.println("exit_code = " + exit_code);
                System.out.println("Finished WebServerJFrame : internalProcess.destroy()");
            } catch (InterruptedException ex) {
                Logger.getLogger(WebServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                System.out.println("Starting WebServerJFrame : internalProcess.destroyForcibly()");
                internalProcess1.destroyForcibly().waitFor(10, TimeUnit.SECONDS);
                System.out.println("Finished WebServerJFrame : internalProcess.destroyForcibly()");
            } catch (InterruptedException ex) {
                Logger.getLogger(WebServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            internalProcess1 = null;
            this.internalProcess = null;
        }
        Thread monitorOutputThread1 = monitorOutputThread;
        if (null != monitorOutputThread1) {
            System.out.println("Starting WebServerJFrame : monitorOutputThread.join(...)");
            monitorOutputThread1.interrupt();
            try {
                monitorOutputThread1.join(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(WebServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Finished WebServerJFrame : monitorOutputThread.join(...)");
            monitorOutputThread1 = null;
            this.monitorOutputThread = null;
        }
        Thread monitorErrorThread1 = monitorErrorThread;
        if (null != monitorErrorThread1) {
            System.out.println("Starting WebServerJFrame : monitorErrorThread.join(...)");
            monitorErrorThread1.interrupt();
            try {
                monitorErrorThread1.join(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(WebServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Finished WebServerJFrame : monitorErrorThread.join(...)");
            monitorErrorThread1 = null;
            this.monitorErrorThread = null;
        }
        closeLogger();
        System.out.println("Starting WebServerJFrame : saveProperties()");
        saveProperties();
        System.out.println("Finished WebServerJFrame : saveProperties()");

    }

    private static final File PROPERTIES_FILE = new File(CRCLUtils.getCrclUserHomeDir(), ".crcl4java.webserver.properties.txt");

    private boolean commandStringSet = false;

    private void readProperties() {
        if (PROPERTIES_FILE.exists()) {
            Properties props = new Properties();
            try (FileReader fileReader = new FileReader(PROPERTIES_FILE)) {
                props.load(fileReader);
                String cmd = props.getProperty("webServerCmd");
                if (null != cmd && !cmd.isEmpty()) {
                    jTextFieldCommand.setText(cmd);
                    commandStringSet = !cmd.endsWith("runWebApp.bat");
                }
                String dir = props.getProperty("webServerDirectory");
                if (null != dir && !dir.isEmpty()) {
                    jTextFieldDirectory.setText(dir);
                }
                String url = props.getProperty("url");
                if (null != url && !url.isEmpty()) {
                    jTextFieldURL.setText(url);
                }
            } catch (IOException ex) {
                Logger.getLogger(WebServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void saveProperties() {
        Properties props = new Properties();
        props.put("webServerCmd", jTextFieldCommand.getText());
        props.put("webServerDirectory", jTextFieldDirectory.getText());
        props.put("url", jTextFieldURL.getText());
        try {
            //        try (FileWriter fileWriter = new FileWriter(PROPERTIES_FILE)) {
//
//            props.store(fileWriter, "Saved automatically from " + Arrays.toString(Thread.currentThread().getStackTrace()));
//        } catch (IOException ex) {
//            Logger.getLogger(WebServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
            PropertiesUtils.saveProperties(PROPERTIES_FILE, props);
        } catch (IOException ex) {
            Logger.getLogger(WebServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @Override
//    protected void finalize() throws Throwable {
//        stop();
//        super.finalize();
//    }

    private void jButtonDirBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDirBrowseActionPerformed
        JFileChooser chooser = new JFileChooser(new File(jTextFieldDirectory.getText()));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int ret = chooser.showOpenDialog(this);
        if (ret == JFileChooser.APPROVE_OPTION) {
            try {
                jTextFieldDirectory.setText(chooser.getSelectedFile().getCanonicalPath());
            } catch (IOException ex) {
                Logger.getLogger(WebServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
                javax.swing.SwingUtilities.invokeLater(() -> consoleAppend(ex.toString()));
            }
        }
        saveProperties();
    }//GEN-LAST:event_jButtonDirBrowseActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        stop();
        onStopRunnable = null;
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        stop();
        onStopRunnable = null;
    }//GEN-LAST:event_formWindowClosing

    private void jButtonClearOutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearOutputActionPerformed
        this.jTextAreaConsoleOutput.setText("");
    }//GEN-LAST:event_jButtonClearOutputActionPerformed

    private void jButtonFullLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFullLogActionPerformed
        showLogFile();
    }//GEN-LAST:event_jButtonFullLogActionPerformed

    private synchronized void showLogFile() {
        closeLogger();
        File logFile1 = logFile;
        if (null != logFile1 && logFile1.exists()) {
            try {
                Desktop.getDesktop().open(logFile1);
            } catch (IOException ex) {
                Logger.getLogger(WebServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void jTextFieldCommandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCommandActionPerformed
        saveProperties();
    }//GEN-LAST:event_jTextFieldCommandActionPerformed

    private void jButtonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewActionPerformed
        try {
            Desktop.getDesktop().browse(new URL(jTextFieldURL.getText()).toURI());
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(WebServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonViewActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WebServerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WebServerJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClearOutput;
    private javax.swing.JButton jButtonDirBrowse;
    private javax.swing.JButton jButtonFullLog;
    private javax.swing.JButton jButtonView;
    private javax.swing.JCheckBox jCheckBoxRun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaConsoleOutput;
    private javax.swing.JTextField jTextFieldCommand;
    private javax.swing.JTextField jTextFieldDirectory;
    private javax.swing.JTextField jTextFieldURL;
    // End of variables declaration//GEN-END:variables
}
