package com.dharbor.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Mojo(name="test", defaultPhase = LifecyclePhase.COMPILE)
public class CustomMavenPlugin extends AbstractMojo {

    @Parameter(required = true)
    String inputPath;

    @Parameter(required = true)
    String outputPath;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        FileWriter writer = null;
        FileReader reader = null;
        try {
            reader = new FileReader(inputPath);
            writer = new FileWriter(outputPath);

            char[] data = new char[1024];
            while (reader.read(data, 0 , data.length) != -1) {
                writer.write(String.valueOf(data));
            }
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }
}
