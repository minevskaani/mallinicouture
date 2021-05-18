package io.infosec.enigma.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * @author Kostadin Almishev
 */
public class ByteArrayReader {
    protected Path inputFile;

    public ByteArrayReader(Path inputFile) {
        this.inputFile = inputFile;
    }

    public byte[] read() {
        try {
            return Files.readAllBytes(inputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
