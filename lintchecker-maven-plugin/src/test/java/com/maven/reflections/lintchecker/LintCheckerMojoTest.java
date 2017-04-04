package com.maven.reflections.lintchecker;

import java.io.File;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;

public class LintCheckerMojoTest extends AbstractMojoTestCase {


    public void testShouldFindClassAnnotatedWithLintChecked() throws Exception {

        final File pom = getTestFile("src/test/resources/pom.xml");

        final LintCheckerMojo mojo = (LintCheckerMojo) lookupMojo("lint-check", pom);

        mojo.execute();

    }
}
