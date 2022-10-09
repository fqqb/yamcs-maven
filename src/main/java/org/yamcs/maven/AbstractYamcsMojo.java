package org.yamcs.maven;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProjectHelper;
import org.codehaus.plexus.archiver.manager.ArchiverManager;
import org.codehaus.plexus.util.FileUtils;

/**
 * Collects general Maven functionalities for use in specific Mojos
 */
public abstract class AbstractYamcsMojo extends AbstractProgramMojo {

    /**
     * Skip execution
     */
    @Parameter(property = "yamcs.skip", defaultValue = "false")
    protected boolean skip;

    /**
     * The directory that contains Yamcs configuration files. By convention this
     * contains subfolders named <code>etc</code> and <code>mdb</code>.
     * <p>
     * Relative paths in yaml configuration files are resolved from this directory.
     */
    @Parameter(property = "yamcs.configurationDirectory", defaultValue = "${basedir}/src/main/yamcs")
    protected File configurationDirectory;

    @Parameter(defaultValue = "${project.build.directory}", readonly = true)
    protected File target;

    @Parameter(defaultValue = "${project.build.outputDirectory}", readonly = true)
    protected File classesDirectory;

    @Component
    protected ArchiverManager archiverManager;

    @Component
    protected MavenProjectHelper projectHelper;

    protected void initConfiguration(File directory) throws IOException {
        directory.mkdirs();

        File etcDir = new File(directory, "etc");
        etcDir.mkdir();

        if (configurationDirectory.exists()) {
            FileUtils.copyDirectoryStructure(configurationDirectory, directory);
        } else {
            getLog().warn(String.format("Yamcs configuration directory %s does not exist", configurationDirectory));
        }
    }

    protected void copyResource(String resource, File file) throws IOException {
        URL url = getClass().getResource(resource);
        if (url == null) {
            throw new FileNotFoundException(resource);
        }
        FileUtils.copyURLToFile(url, file);
    }

    protected void copyExecutableResource(String resource, File file) throws IOException {
        copyResource(resource, file);
        file.setExecutable(true);
    }
}
