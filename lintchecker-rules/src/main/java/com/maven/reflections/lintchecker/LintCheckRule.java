package com.maven.reflections.lintchecker;

import org.apache.maven.plugin.MojoExecutionException;

public interface LintCheckRule {

    void execute() throws MojoExecutionException;

}
