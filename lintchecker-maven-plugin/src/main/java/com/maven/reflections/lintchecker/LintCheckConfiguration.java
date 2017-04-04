package com.maven.reflections.lintchecker;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

public class LintCheckConfiguration {

    private final MavenProject mavenProject;
    private final Log log;

    public LintCheckConfiguration(final MavenProject mavenProject, final Log log) {
        this.mavenProject = mavenProject;
        this.log = log;
    }

    public MavenProject getMavenProject() {
        return mavenProject;
    }

    public Log getLog() {
        return log;
    }

}
