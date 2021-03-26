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

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    private static void info(String string) {
        java.util.logging.Logger.getLogger(ApplicationConfig.class.getName()).info(string);
    }

    private static void warning(String string) {
        java.util.logging.Logger.getLogger(ApplicationConfig.class.getName()).warning(string);
    }

    private static void logException(java.util.logging.Level level, String msg, Throwable throwable) {
        java.util.logging.Logger.getLogger(ApplicationConfig.class.getName()).log(level,msg,throwable);
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        warning("resources="+resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(crcl.restful.proxy.JsonCommandResource.class);
        resources.add(crcl.restful.proxy.JsonStatusResource.class);
        resources.add(crcl.restful.proxy.XmlCommandResource.class);
        resources.add(crcl.restful.proxy.XmlStatusResource.class);
    }
}
