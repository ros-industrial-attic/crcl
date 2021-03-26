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
package crcl.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
public class XpathUtils {

    final TransformerFactory transformerFactory;
    final DocumentBuilderFactory documentBuilderFactory;
    final DocumentBuilder documentBuilder;

    public XpathUtils() throws ParserConfigurationException {
        transformerFactory = TransformerFactory.newInstance();
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
    }

    public String getDocumentation(@Nullable File schemaFiles[], String name) throws SAXException, IOException, XPathExpressionException, ParserConfigurationException {
        return queryXml(schemaFiles, "/schema/complexType[@name=\"" + name + "\"]/annotation/documentation/text()");
    }

    public String nodeToString(Node node) {
        StringWriter sw = new StringWriter();
        try {
            Transformer t = transformerFactory.newTransformer();
            t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(node), new StreamResult(sw));
        } catch (TransformerException te) {
            te.printStackTrace();
        }
        return sw.toString();
    }

    public String queryXml(File xsdFile, String query) throws SAXException, IOException, XPathExpressionException, ParserConfigurationException {
        Document doc = documentBuilder.parse(xsdFile);
        final XPathFactory xPathfactory = XPathFactory.newInstance();
        final XPath xpath = xPathfactory.newXPath();
        NodeList nl = (NodeList) xpath.evaluate(query, doc, XPathConstants.NODESET);
        return nodeListToString(nl);
    }

    public String queryXml(@Nullable File fa[], String query) throws SAXException, IOException, XPathExpressionException, ParserConfigurationException {
        StringBuilder sb = new StringBuilder();
        if (null != fa) {
            for (File f : fa) {
                if (null != f) {
                    sb.append(queryXml(f, query));
                }
            }
        }
        
        return sb.toString();
    }

    public String nodeListToString(NodeList nl) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            if (n != null) {
                sb.append(nodeToString(n));
            }
        }
        return sb.toString();
    }

    public String queryXmlString(String string, String query) throws SAXException, IOException, XPathExpressionException, ParserConfigurationException {
        Document doc = documentBuilder.parse(new ByteArrayInputStream(string.getBytes()));
        final XPathFactory xPathfactory = XPathFactory.newInstance();
        final XPath xpath = xPathfactory.newXPath();
        NodeList nl = (NodeList) xpath.evaluate(query, doc, XPathConstants.NODESET);
        return nodeListToString(nl);
    }
    private static final Logger LOG = Logger.getLogger(XpathUtils.class.getName());

}
