package com.maven.reflections.lintchecker;

import java.io.File;

public class LintCheckerMojoTest extends BetterAbstractMojoTestCase {

    public void testShouldFindClassAnnotatedWithLintChecked() throws Exception {

        final File pom = getTestFile("src/test/resources/pom.xml");

        final LintCheckerMojo mojo = (LintCheckerMojo) lookupConfiguredMojo( pom, "lint-check");

        mojo.execute();

    }
}
