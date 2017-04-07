package com.maven.reflections.lintchecker;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.DependencyResolutionException;
import org.apache.maven.project.MavenProject;

@Mojo(name = "lint-check", requiresDependencyResolution = ResolutionScope.RUNTIME)
public class LintCheckerMojo extends AbstractMojo {

    /**
     * The MavenSession
     */
    @Parameter(defaultValue = "${session}", readonly = true, required = true)
    protected MavenSession session;

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    /**
     * Array of objects that implement the LintCheckerRule interface to execute.
     */
    @Parameter(required = true)
    protected List<LintCheckRule> rules;

    public void execute() throws MojoExecutionException {

        final ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            final Set<URL> urls = new HashSet<>();
            for (String element : project.getRuntimeClasspathElements()) {
                urls.add(new File(element).toURI().toURL());
            }

            final ClassLoader contextClassLoader = URLClassLoader.newInstance(
                    urls.toArray(new URL[0]), originalClassLoader);

            Thread.currentThread().setContextClassLoader(contextClassLoader);

        } catch (DependencyResolutionRequiredException | MalformedURLException e) {
            throw new MojoExecutionException("Could not set up class loader", e);
        }

        for (LintCheckRule rule : rules) {
            rule.execute(); //In real life rule.execute() would take a configuration as parameter
        }

        Thread.currentThread().setContextClassLoader(originalClassLoader);
    }
}
