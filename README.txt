This problem relates to a requirement to implement
a lintchecker which is configurable as a maven plugin
using a similar model to the maven-enforcer-plugin and
will allow us to write rules which relate to our bespoke
programme architecture.

However we have struggled to get reflections and maven
to play nicely together and have created this simplified
mock project to highlight the issue.

If you do an mvn clean install on the project you will
see that all modules successfully install except for
maven-lintchecker-test. This is because the org.reflections
api is working fine during tests, however we cannot get it
to work as part of a proper maven build.

According to the maven website we should be able to get a set
of classpath urls from the MavenProject, however this does not
seem to be working. If you provide the reflections object with
the build directory then it will find those files, however
we are still missing the jars which are stored the local .m2
folder:

File buildDirectory = new File( mavenProject.getBuild().getOutputDirectory() );

You can modify the LintCheckRule to take a config object through
which you can provide the list of urls.

You can also look through the maven plexus container and try to locate
where the classworlds ClassReaml is which has all the dependency information.
Note the ClassRealm class is an extension of URLClassLoader.

Good luck!