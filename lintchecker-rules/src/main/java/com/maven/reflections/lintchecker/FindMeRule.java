package com.maven.reflections.lintchecker;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.maven.reflection.lintchecker.annotations.LintChecked;
import org.apache.maven.plugin.MojoExecutionException;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

public class FindMeRule implements LintCheckRule {

    @Override
    public void execute() throws MojoExecutionException {

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forClassLoader(Thread.currentThread().getContextClassLoader()))
                .addScanners(new MethodAnnotationsScanner()));
        final Set<Method> lintCheckedMethods = reflections.getMethodsAnnotatedWith(LintChecked.class);
        try {
            assertThat(methodNamesFrom(lintCheckedMethods), hasItem(is("noticeMe")));
        } catch (AssertionError e) {
            throw new MojoExecutionException("Cannot find any LintChecked");
        }

    }

    private List<String> methodNamesFrom(Set<Method> lintCheckedMethods) {
        return lintCheckedMethods.stream()
                .map(method -> method.getName())
                .collect(Collectors.toList());
    }
}
