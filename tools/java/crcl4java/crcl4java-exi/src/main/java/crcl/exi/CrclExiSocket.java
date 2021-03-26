/* 
 * This is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * 
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain. This software is experimental.
 * NIST assumes no responsibility whatsoever for its use by other
 * parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic. We would appreciate
 * acknowledgment if the software is used. This software can be
 * redistributed and/or modified freely provided that any derivative works
 * bear some notice that they are derived from it, and any modified
 * versions bear some notice that they have been modified.
 * 
 */
package crcl.exi;

import com.siemens.ct.exi.EXIFactory;
import com.siemens.ct.exi.GrammarFactory;
import com.siemens.ct.exi.api.sax.EXIResult;
import com.siemens.ct.exi.api.sax.EXISource;
import com.siemens.ct.exi.exceptions.EXIException;
import com.siemens.ct.exi.grammars.Grammars;
import com.siemens.ct.exi.helpers.DefaultEXIFactory;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.base.CRCLStatusType;
import crcl.base.GetStatusType;
import crcl.base.ObjectFactory;
import crcl.utils.CRCLException;
import crcl.utils.CRCLSocket;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/*
 * 
 * NOTE: Comments beginning with {@literal @} or {@literal >>>} are used by Checker Framework Comments
 * beginning with {@literal @} must have no spaces in the comment or Checker will ignore
 * it.
 *
 * See http://types.cs.washington.edu/checker-framework for null pointer
 * checks. This file can be compiled without the Checker Framework, but using
 * the framework allows potential NullPointerExceptions to be found.
 */
 /*>>>
import org.checkerframework.checker.nullness.qual.*;
 */
/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov> }
 */
public class CrclExiSocket extends CRCLSocket {

    private boolean EXIEnabled = Boolean.valueOf(System.getProperty("crcl.EXIEnabled", "false"));
    private boolean prefixEXISizeEnabled = Boolean.valueOf(System.getProperty("crcl.prefixEXISizeEnabled", "false"));
    /*@Nullable*/ private EXIFactory exiStatusFactory = null;
    /*@Nullable*/ private EXIFactory exiCommandFactory = null;
    /*@Nullable*/ private BufferedInputStream bufferedInputStream = null;
    private boolean useBufferedInputStream = true;
    /*@Nullable*/ private SAXSource exiCommandInSaxSource = null;
    /*@Nullable*/ private SAXSource exiStatusInSaxSource = null;
    private final ObjectFactory objectFactory
            = new ObjectFactory();
    /*@Nullable*/ private Random random = null;
    /*@Nullable*/ private String last_xml_version_header = null;
    /*@Nullable*/ private String last_orig_first_tag = null;
    private boolean replaceHeader;

    public CrclExiSocket() throws CRCLException {
        this(null);
    }

    public CrclExiSocket(/*@Nullable*/Socket sock) throws CRCLException {
        super(sock);
    }

    public CrclExiSocket(String hostname, int port) throws IOException, CRCLException {
        super(hostname, port);
    }

    private static final Logger LOGGER = Logger.getLogger(CrclExiSocket.class.getName());

    @Override
    public CRCLCommandInstanceType readCommand(boolean validate,int soTimeout) throws CRCLException, IOException {
        final String threadName = Thread.currentThread().getName();
        if (this.isEXIEnabled()) {
            if (!this.isPrefixEXISizeEnabled()) {
                try {
                    final CRCLCommandInstanceType c = this.readCommandFromEXIStream(getBufferedInputStream(soTimeout));
                    final CRCLCommandType cc = c.getCRCLCommand();
                    final Level loglevel = (cc instanceof GetStatusType) ? Level.FINER : Level.FINE;
                    LOGGER.log(loglevel, "readCommand() returning {0} ID={1} called from Thread: {2}", new Object[]{cc, cc.getCommandID(), threadName});
                    return c;
                } catch (EXIException | JAXBException | IOException ex) {
                    throw new CRCLException(ex);
                }
            } else {
                try {
                    byte sizeba[] = new byte[4];
                    DataInputStream dis = new DataInputStream(getBufferedInputStream(soTimeout));
                    dis.readFully(sizeba);
                    ByteBuffer bb = ByteBuffer.wrap(sizeba);
                    int size = bb.getInt();
                    byte ba[] = new byte[size];
                    dis.readFully(ba);
                    final CRCLCommandInstanceType c = exiToCommand(ba);
                    final CRCLCommandType cc = c.getCRCLCommand();
                    final Level loglevel = (cc instanceof GetStatusType) ? Level.FINER : Level.FINE;
                    LOGGER.log(loglevel, "readCommand() returning {0} ID={1} called from Thread: {2}", new Object[]{cc, cc.getCommandID(), threadName});
                    return c;
                } catch (EXIException | JAXBException ex) {
                    throw new CRCLException(ex);
                }
            }
        }
        return super.readCommand(validate);
    }

    /**
     * Get the value of EXIEnabled
     *
     * @return the value of EXIEnabled
     */
    public boolean isEXIEnabled() {
        return EXIEnabled;
    }

    /**
     * Get the value of prefixEXISizeEnabled
     *
     * @return the value of prefixEXISizeEnabled
     */
    public boolean isPrefixEXISizeEnabled() {
        return prefixEXISizeEnabled;
    }

    /**
     * Set the value of prefixEXISizeEnabled
     *
     * @param prefixEXISizeEnabled new value of prefixEXISizeEnabled
     */
    public void setPrefixEXISizeEnabled(boolean prefixEXISizeEnabled) {
        this.prefixEXISizeEnabled = prefixEXISizeEnabled;
    }

    static private <T> /*@NonNull*/ T toNonNull(/*@Nullable*/T t, String msg) {
        if (t != null) {
            return (/*@NonNull*/T) t;
        } else {
            throw new RuntimeException(msg);
        }
    }

    private EXIFactory getExiStatusFactory() throws EXIException, IOException {
        if (null != exiStatusFactory) {
            return exiStatusFactory;
        }
        copySchemaResources();
        final EXIFactory newExiStatusFactory
                = toNonNull(DefaultEXIFactory.newInstance(),
                        "DefaultEXIFactory.newInstance() returned null");
        GrammarFactory grammarFactory
                = toNonNull(GrammarFactory.newInstance(),
                        "GrammarFactory.newInstance() returned null");
        String xsdLocation = getCrclSchemaDirFile().getCanonicalPath() + File.separator + "CRCLStatus.xsd";
        Grammars grammers
                = toNonNull(grammarFactory.createGrammars(xsdLocation),
                        "grammarFactory.createGrammars(\"" + xsdLocation + "\") returned null");
        newExiStatusFactory.setGrammars(grammers);
        exiStatusFactory = newExiStatusFactory;
        return newExiStatusFactory;
    }

    private EXIFactory getExiCommandFactory() throws EXIException, IOException {
        if (null != exiCommandFactory) {
            return exiCommandFactory;
        }
        copySchemaResources();
        final EXIFactory newExiCommandFactory
                = toNonNull(DefaultEXIFactory.newInstance(),
                        "DefaultEXIFactory.newInstance() returned null");
        GrammarFactory grammarFactory
                = toNonNull(GrammarFactory.newInstance(),
                        "GrammarFactory.newInstance() returned null");
        String xsdLocation = getCrclSchemaDirFile().getCanonicalPath() + File.separator + "CRCLCommandInstance.xsd";
        Grammars grammers
                = toNonNull(grammarFactory.createGrammars(xsdLocation),
                        "grammarFactory.createGrammars(\"" + xsdLocation + "\") returned null");
        newExiCommandFactory.setGrammars(grammers);
        exiCommandFactory = newExiCommandFactory;
        return newExiCommandFactory;
    }

    /**
     * Set the value of EXIEnabled
     *
     * @param EXIEnabled new value of EXIEnabled
     * @throws crcl.utils.CRCLException wrapper for potential internal
     * exceptions
     */
    public void setEXIEnabled(boolean EXIEnabled) throws CRCLException {
        this.EXIEnabled = EXIEnabled;
        if (!EXIEnabled) {
            exiStatusFactory = null;
            exiCommandFactory = null;
        }
    }

    public byte[] statusToEXI(CRCLStatusType status) throws CRCLException {
        final String threadName = Thread.currentThread().getName();
        ByteArrayOutputStream exiOS = null;
        try {
            exiOS = new ByteArrayOutputStream();
            writeEXIStatusToStream(exiOS, status);
            return exiOS.toByteArray();
        } catch (Exception ex) {
            final String xmlS = this.statusToString(status, true);
            LOGGER.log(Level.SEVERE,
                    "CRCLStatus.statusToEXIFirst() Exception Thread=" + threadName + ", status=" + statToDebugString(status) + ",xmlS=" + xmlS,
                    ex);
            final Level origLevel = LOGGER.getLevel();
            final Logger xmlInternalLogger = Logger.getLogger("com.sun.xml.internal.bind");
            final Level origXmlInternalLogLevel = xmlInternalLogger.getLevel();
            try {
                exiOS = new ByteArrayOutputStream();
                writeEXIStatusToStream(exiOS, status);
                final byte ba[] = exiOS.toByteArray();
                LOGGER.log(Level.SEVERE, "writeEXIStatusTOStream succeeded on second try. ba={0}", Arrays.toString(ba));
                return ba;
            } catch (IOException | JAXBException | EXIException ex1) {
                throw new CRCLException(ex1);
            } finally {
                xmlInternalLogger.setLevel(origXmlInternalLogLevel);
                LOGGER.setLevel(origLevel);
            }
        } finally {
            if (null != exiOS) {
                try {
                    exiOS.close();
                } catch (Exception exx) {
                    LOGGER.log(Level.FINEST, "exception normally ignored", exx);
                }
            }
        }
    }

    public byte[] commandToEXI(CRCLCommandInstanceType cmd) throws CRCLException {
        ByteArrayOutputStream exiOS = null;
        try {
            exiOS = new ByteArrayOutputStream();
            writeEXICommandToStream(exiOS, cmd);
            return exiOS.toByteArray();
        } finally {
            if (null != exiOS) {
                try {
                    exiOS.close();
                } catch (Exception exx) {
                    LOGGER.log(Level.FINEST, "exception normally ignored", exx);
                }
            }
        }
    }

    /**
     *
     * @param outStream the value of outStream
     * @param status status to write
     * @throws IOException network failure
     * @throws JAXBException JAXB failure
     * @throws EXIException EXI lib failure
     */
    public void writeEXIStatusToStream(
            final java.io.OutputStream outStream,
            CRCLStatusType status)
            throws IOException, JAXBException, EXIException {
        final EXIResult exiResult = new EXIResult(getExiStatusFactory());
        exiResult.setOutputStream(outStream);
        final ContentHandler handler = exiResult.getHandler();
        JAXBElement<CRCLStatusType> jaxb_status
                = objectFactory.createCRCLStatus(status);
        synchronized (m_stat) {
            m_stat.marshal(jaxb_status, handler);
        }
    }

    /**
     * Write an EXI Command to a stream
     *
     * @param outStream the value of outStream
     * @param cmdInstance command to send
     * @throws crcl.utils.CRCLException
     */
    public void writeEXICommandToStream(
            final java.io.OutputStream outStream,
            CRCLCommandInstanceType cmdInstance)
            throws CRCLException {
        try {
            final EXIResult exiResult = new EXIResult(getExiCommandFactory());
            outStream.flush();
            exiResult.setOutputStream(outStream);
            final ContentHandler handler = exiResult.getHandler();
            JAXBElement<CRCLCommandInstanceType> jaxb_cmd_instance
                    = objectFactory.createCRCLCommandInstance(cmdInstance);
            final String threadName = Thread.currentThread().getName();
            synchronized (m_cmd) {
                try {
                    m_cmd.marshal(jaxb_cmd_instance, handler);
                    outStream.flush();
                } catch (NullPointerException nullPointerException) {
                    LOGGER.log(Level.SEVERE,
                            "First NPE Thread=" + threadName, nullPointerException);
                    try {
                        m_cmd.marshal(jaxb_cmd_instance, handler);
                    } catch (NullPointerException nullPointerException2) {
                        LOGGER.log(Level.SEVERE,
                                "Second NPE Thread=" + threadName,
                                nullPointerException);
                    }
                } catch (JAXBException ex) {
                    Logger.getLogger(CrclExiSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (EXIException | IOException | JAXBException ex) {
            throw new CRCLException(ex);
        }
    }

    public CRCLStatusType exiToStatus(byte exi[]) throws EXIException, JAXBException, IOException {
        InputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(exi);
            return readStatusFromEXIStream(inputStream);
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (Exception exx) {
                    LOGGER.log(Level.FINEST, "exception normally ignored", exx);
                }
            }
        }
    }

    public CRCLCommandInstanceType exiToCommand(byte exi[])
            throws EXIException, JAXBException, IOException {
        InputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(exi);
            return readCommandFromEXIStream(inputStream);
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (Exception exx) {
                    LOGGER.log(Level.FINEST, "exception normally ignored", exx);
                }
            }
        }
    }

//    private SAXSource getStatusSAXSource() throws IOException, EXIException {
//        if(null != statusSAXSource) {
//           return statusSAXSource;
//        }
//        EXISource exiSource = new EXISource(getExiStatusFactory());
//        XMLReader xmlReader = exiSource.getXMLReader();
//        InputSource inputSource = new InputSource(getBufferedInputStream());
//        statusSAXSource = new SAXSource(xmlReader, new InputSource(getBufferedInputStream()));
//        return statusSAXSource;
//    }
    public CRCLStatusType readStatusFromEXIStream(final InputStream inputStream)
            throws EXIException, JAXBException, IOException {
//        EXISource exiSource = new EXISource(getExiStatusFactory());
//        XMLReader xmlReader = exiSource.getXMLReader();
        synchronized (inputStream) {
            return readStatusFromSaxSource(getExiStatusInSaxSource(inputStream));
        }
    }

    public CRCLStatusType
            readStatusFromSaxSource(SAXSource saxSource) throws JAXBException {
        synchronized (u_stat) {
            JAXBElement<CRCLStatusType> el
                    = u_stat.unmarshal(saxSource, CRCLStatusType.class
                    );
            return el.getValue();
        }
    }

    private SAXSource getExiCommandInSaxSource(final InputStream is) throws EXIException, IOException {

        if (null != exiCommandInSaxSource) {
            SAXSource src = exiCommandInSaxSource;
            if (src.getInputSource().getByteStream() == is) {
                return src;
            }
        }
        EXISource exiSource = new EXISource(getExiCommandFactory());
        XMLReader xmlReader = exiSource.getXMLReader();
        SAXSource newExiCommandInSaxSource
                = new SAXSource(xmlReader, new InputSource(is));
        exiCommandInSaxSource = newExiCommandInSaxSource;
        return newExiCommandInSaxSource;
    }

    private SAXSource getExiStatusInSaxSource(final InputStream is) throws EXIException, IOException {

        if (null != exiStatusInSaxSource) {
            SAXSource src = exiStatusInSaxSource;
            if (src.getInputSource().getByteStream() == is) {
                return src;
            }
        }
        EXISource exiSource = new EXISource(getExiStatusFactory());
        XMLReader xmlReader = exiSource.getXMLReader();
//        byte notused_ba[] = new byte[1];
//        InputStream proxyInputStream = new ByteArrayInputStream(notused_ba) {
//
//            int count = 0;
//
//            @Override
//            public int read() {
//                int ret = -1;
//                try {
//                    ret = inputStream.read();
//                    count++;
//                    byte b = (byte) ret;
//                    if (b > 127) {
//                        b -= 256;
//                    }
//                    final int retf = ret;
//                    final byte bf = b;
//                    LOGGER.log(Level.FINEST, () -> "read() returning : " + retf + ", count =" + count + ", b = " + bf);
//                    return ret;
//                } catch (IOException ex) {
//                    Logger.getLogger(CRCLSocket.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                return ret;
//            }
//
//            @Override
//            public boolean markSupported() {
//                boolean ret = inputStream.markSupported();
//                LOGGER.log(Level.FINEST, () -> "markSupported() returning " + ret);
//                return ret;
//            }
//
//            @Override
//            public synchronized void reset() {
//                try {
//                    LOGGER.log(Level.FINEST, () -> "reset() called.");
//                    inputStream.reset();
//                } catch (IOException ex) {
//                    Logger.getLogger(CRCLSocket.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//            @Override
//            public synchronized void mark(int readlimit) {
//                LOGGER.log(Level.FINEST, () -> "mart(" + readlimit + ") called");
//                inputStream.mark(readlimit);
//            }
//
//            @Override
//            public void close() throws IOException {
//                LOGGER.log(Level.FINEST, () -> "close() called");
//                inputStream.close();
//            }
//
//            @Override
//            public int available() {
//                int ret = -1;
//                try {
//                    ret = inputStream.available();
//                    final int retf = ret;
//                    LOGGER.log(Level.FINEST, () -> "available() returning " + retf);
//                } catch (IOException ex) {
//                    Logger.getLogger(CRCLSocket.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                return ret;
//            }
//
//            @Override
//            public long skip(long n) {
//                long ret = -1;
//                try {
//                    ret = inputStream.skip(n);
//                    final long retf =ret;
//                    LOGGER.log(Level.FINEST, () -> "skip(" + n + ") returning " + retf);
//                } catch (IOException ex) {
//                    Logger.getLogger(CRCLSocket.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                return ret;
//            }
//
//            @Override
//            public int read(byte[] b, int off, int len) {
//                LOGGER.log(Level.FINEST, () -> "read(...," + off + "," + len + ") called.");
//                int ret = -1;
//                try {
//                    ret = inputStream.read(b, off, len);
//                } catch (IOException ex) {
//                    Logger.getLogger(CRCLSocket.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                final int retf = ret;
//                if (ret > 0) {
//                    final byte bf[] = b;
//                    LOGGER.log(Level.FINEST, () -> "b = " + Arrays.toString(Arrays.copyOf(bf, retf)));
//                }
//                LOGGER.log(Level.FINEST, () -> "read(...," + off + "," + len + ") returning " + retf);
//                return ret;
//            }
//
//            @Override
//            public int read(byte[] b) throws IOException {
//                LOGGER.log(Level.FINEST, () -> "read(...,) called.");
//                int ret = inputStream.read(b);
//                final int retf = ret;
//                if (ret > 0) {
//                    final byte bf[] = b;
//                    LOGGER.log(Level.FINEST, () -> "b = " + Arrays.toString(Arrays.copyOf(bf, retf)));
//                }
//                
//                LOGGER.log(Level.FINEST, () -> "read(...) returning " + retf);
//                return ret;
//            }
//        };
        exiStatusInSaxSource = new SAXSource(xmlReader, new InputSource(is));
        return exiStatusInSaxSource;
    }

    public CRCLCommandInstanceType readCommandFromEXIStream(final InputStream inputStream)
            throws EXIException, JAXBException, IOException {

        synchronized (u_cmd) {
            JAXBElement<CRCLCommandInstanceType> el
                    = u_cmd.unmarshal(getExiCommandInSaxSource(inputStream), CRCLCommandInstanceType.class
                    );
            return el.getValue();
        }
    }

    @Override
    public CRCLStatusType readStatus(boolean validate, int soTimeout)
            throws CRCLException {
        if (this.isEXIEnabled()) {
            if (this.isPrefixEXISizeEnabled()) {
                try {
                    int size = 0;
                    byte sizeba[] = new byte[4];
                    DataInputStream dis = new DataInputStream(getBufferedInputStream(soTimeout));
                    dis.readFully(sizeba);
                    ByteBuffer bb = ByteBuffer.wrap(sizeba);
                    size = bb.getInt();
                    byte ba[] = new byte[size];
                    dis.readFully(ba);
                    return exiToStatus(ba);
                } catch (IOException | EXIException | JAXBException ex) {
                    throw new CRCLException(ex);
                }
            } else {
                try {
                    return this.readStatusFromEXIStream(getBufferedInputStream(soTimeout));
                } catch (EXIException | JAXBException | IOException ex) {
                    throw new CRCLException(ex);
                }
            }
        }
        return super.readStatus(validate);
    }

    @Override
    public synchronized void writeCommand(CRCLCommandInstanceType cmd, boolean validate) throws CRCLException {
        final CRCLCommandType cc = cmd.getCRCLCommand();
        final boolean EXI = this.isEXIEnabled();
        final String threadName = Thread.currentThread().getName();
        final Level loglevel = (cc instanceof GetStatusType) ? Level.FINER : Level.FINE;
        LOGGER.log(loglevel, "writeCommand({0} ID={1}) with EXI = {2} called from Thread: {3}", new Object[]{cc, cc.getCommandID(), EXI, threadName});
        final Socket socket = getSocket();
        if (null == socket) {
            throw new IllegalStateException("Internal socket is null.");
        } else {

            try {
                if (this.isEXIEnabled()) {
                    if (!this.isPrefixEXISizeEnabled()) {
                        this.writeEXICommandToStream(socket.getOutputStream(), cmd);
                    } else {
                        final byte ba[] = this.commandToEXI(cmd);
                        LOGGER.log(loglevel, "writeCommand() : ba = {0}", Arrays.toString(ba));
                        ByteBuffer bb = ByteBuffer.allocate(ba.length + 4);
                        bb.putInt(ba.length);
                        bb.put(ba);
                        byte ba2[] = bb.array();
                        writePackets(ba2);
//                    socket.getOutputStream().write(ba2);
//                    socket.getOutputStream().flush();
                    }
                    return;
                }
            } catch (IOException | InterruptedException ex) {
                throw new CRCLException(ex);
            }
            super.writeCommand(cmd, validate);
        }
    }

    public synchronized void writeStatus(CRCLStatusType status, boolean validate)
            throws CRCLException {
        final Socket socket = getSocket();
        if (null == socket) {
            throw new IllegalStateException("Internal socket is null.");
        } else {
            try {
                if (this.isEXIEnabled()) {
                    if (this.isPrefixEXISizeEnabled()) {
                        byte ba[] = this.statusToEXI(status);
//                LOGGER.log(Level.FINEST,() ->"writeStatus() : ba = " + Arrays.toString(ba));
                        ByteBuffer bb = ByteBuffer.allocate(ba.length + 4);
                        bb.putInt(ba.length);
//                LOGGER.log(Level.FINEST,() ->"writeStatus: ba.length = " + ba.length);
                        bb.put(ba);
                        byte ba2[] = bb.array();
//                LOGGER.log(Level.FINEST,() ->"writeStatus: ba2.length = " + ba2.length);
//                LOGGER.log(Level.FINEST,() ->"writeStatus: ba2 = " + Arrays.toString(ba2));
                        writePackets(ba2);
//                    this.sock.getOutputStream().write(ba2);
//                    this.sock.getOutputStream().flush();
                    } else {
                        this.writeEXIStatusToStream(socket.getOutputStream(), status);
                    }
                    return;
                }
            } catch (IOException | JAXBException | EXIException | InterruptedException ex) {
                throw new CRCLException(ex);
            }
            super.writeStatus(status, validate);
        }
    }

}
