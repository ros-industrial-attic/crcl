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
package com.github.wshackle.java2slice.maven.plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java2slice.Java2SliceMain;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 *
 * Echos an object string to the output screen. goal java2slice requiresProject
 * false
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
@Mojo(name = "java2slice")
public class Java2SliceMojo extends AbstractMojo {

    /**
     * Same as command line arguments for java2slice parameter
     * expression="${java2slice.args}"
     */
    @Parameter
    String[] args;

    /**
     * List of ids to be for jars to be looked up and added to command line.
     * Each id should correspond to a dependancy.
     * expression="${java2slice.artifacts}"
     */
    @Parameter
    Set<String> artifactIds;

    @Parameter(property = "project", required = true, readonly = true)
    private MavenProject project;

    @Override
    @SuppressWarnings("unchecked")
    public void execute() throws MojoExecutionException, MojoFailureException {

        Log log = getLog();
        List<String> newArgsList = new ArrayList<>();
        newArgsList.addAll(Arrays.asList(args));
        try {
            Class<?> jaxbClass = Class.forName("javax.xml.bind.JAXBElement");
            log.info("jaxbClass=" + jaxbClass);
            log.info("args=" + Arrays.toString(args));
            log.info("project = " + project);
            final Set<Artifact> artifacts = (Set<Artifact>) project.getDependencyArtifacts();
            log.info("artifacts = " + artifacts);
            log.info("artifactIds = " + artifacts);
            
            for (Artifact a : artifacts) {
                if (artifactIds.contains(a.getArtifactId())) {
                    try {
                        File artifactFile = a.getFile();
                        log.info("artifactFile = " + artifactFile);
                        String artifactFileCanonicalPath = artifactFile.getCanonicalPath();
                        newArgsList.add(artifactFileCanonicalPath);
                    } catch (IOException ex) {
                        log.error(ex);
                    }
                }
            }
            log.info("newArgsList=" + newArgsList);
            String newargs[] = newArgsList.toArray(new String[newArgsList.size()]);
            log.info("newargs=" + Arrays.toString(newargs));
            Java2SliceMain.main(newargs);
        } catch (Exception e) {
            log.error(e);
            throw new MojoExecutionException("Java2Slice failed with newArgsList="+newArgsList, e);
        }
        
    }

}
