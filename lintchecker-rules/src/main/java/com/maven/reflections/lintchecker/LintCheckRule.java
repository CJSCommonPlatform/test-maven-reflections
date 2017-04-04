package com.maven.reflections.lintchecker;

import java.net.URL;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;

public interface LintCheckRule {

    void execute() throws MojoExecutionException;

}
