package io.infosec.enigma.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Kostadin Almishev
 */
public class ByteArrayWriter {
    protected Path outputFile;

    private void initOutputFile(Path outputFile) {
        this.outputFile = outputFile;
    }

    private void initOutputDirectory() {
        Path outputDirectory = outputFile.getParent();
        if (!Files.exists(outputDirectory)) {
            try {
                Files.createDirectories(outputDirectory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ByteArrayWriter(Path outputFile) {
        initOutputFile(outputFile);
        initOutputDirectory();
    }

    public void write(byte[] bytesArrayToWrite) {
        try {
            Files.write(outputFile, bytesArrayToWrite);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
