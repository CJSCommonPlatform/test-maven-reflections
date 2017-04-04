package com.maven.reflections.lintchecker;

public class LintCheckerException extends Exception {

    public LintCheckerException() {
        super();
    }

    public LintCheckerException(String message) {
        super(message);
    }

    public LintCheckerException(String message, Throwable cause) {
        super(message, cause);
    }
}
