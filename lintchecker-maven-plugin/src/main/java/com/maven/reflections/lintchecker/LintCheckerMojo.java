package com.maven.reflections.lintchecker;

import java.util.List;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

@Mojo(name = "lint-check", requiresDependencyResolution = ResolutionScope.RUNTIME)
public class LintCheckerMojo extends AbstractMojo {

    /**
     * The MavenSession
     */
    @Parameter(defaultValue = "${session}", readonly = true, required = true)
    protected MavenSession session;

    /**
     * Array of objects that implement the LintCheckerRule interface to execute.
     */
    @Parameter(required = true)
    protected List<LintCheckRule> rules;

    public void execute() throws MojoExecutionException {

        for (LintCheckRule rule : rules) {
                rule.execute(); //In real life rule.execute() would take a configuration as parameter
        }
    }
}
