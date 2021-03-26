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
package crcl.restful.proxy;

import crcl.base.CRCLCommandInstanceType;
import crcl.utils.CRCLException;
import java.io.IOException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

/**
 * REST Web Service
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
@Path("jsonCommand")
public class JsonCommandResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of JsonCommandResource
     */
    public JsonCommandResource() {
    }

    private static final QName COMMAND_QNAME = new QName("CRCLCommand");

    /**
     * Retrieves representation of the last command sent through this server.
     *
     * @return A Response built from a JAXBElement of CRCLCommandInstanceType created from the last command.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
        JAXBElement<CRCLCommandInstanceType> content
                = new JAXBElement<>(COMMAND_QNAME,
                        CRCLCommandInstanceType.class,
                        ProxyCommon.getTheCommonProxy().getCmdInstance());
        CacheControl cc = new CacheControl();
        cc.setNoCache(true);
        cc.setNoStore(true);

        GenericEntity<JAXBElement<CRCLCommandInstanceType>> entity = new GenericEntity<JAXBElement<CRCLCommandInstanceType>>(content) {};
        Response.ResponseBuilder builder = Response.ok(entity);
        builder.cacheControl(cc);
        return builder.build();
    }

    /**
     * PUT method sends a command to the Robot to be executed.
     *
     * @param content representation for the resource
     * @throws crcl.utils.CRCLException XML is invalid.
     * @throws java.io.IOException Network connection to robot failed.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(JAXBElement<CRCLCommandInstanceType> content) throws CRCLException, IOException {
        ProxyCommon.getTheCommonProxy().setCmdInstance(content.getValue());
    }
    
    /**
     * POST method sends a command to the Robot to be executed.
     *
     * @param content representation for the resource
     * @throws crcl.utils.CRCLException XML is invalid.
     * @throws java.io.IOException Network connection to robot failed.
     */
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void postXml(JAXBElement<CRCLCommandInstanceType> content) throws CRCLException, IOException {
        ProxyCommon.getTheCommonProxy().setCmdInstance(content.getValue());
    }
}
