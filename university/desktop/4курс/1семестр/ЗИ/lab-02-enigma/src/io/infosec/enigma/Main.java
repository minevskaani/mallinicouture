package io.infosec.enigma;

import io.infosec.enigma.io.ByteArrayReader;
import io.infosec.enigma.io.ByteArrayWriter;

import java.nio.file.Path;

import static io.infosec.enigma.config.Constants.*;

public class Main {
    // write your code here
    private static Enigma enigma = new Enigma();

    // saving state of enigma
    private static Enigma enigma2 = new Enigma(enigma);

    private static void fullReset() {
        enigma = new Enigma();
        enigma2 = new Enigma(enigma);
    }

    private static void reset() {
        enigma = new Enigma(enigma2);
    }


    private static void encodeAndDecode(Path file, Path encodedFile, Path decodedFile) {
        fullReset();
        byte[] imgOriginal = new ByteArrayReader(file).read();

        byte[] encoded = enigma.value(imgOriginal);
        reset();
        byte[] decoded = enigma.value(encoded);

        new ByteArrayWriter(encodedFile).write(encoded);
        new ByteArrayWriter(decodedFile).write(decoded);
    }

    public static void main(String[] args) {
        System.out.println("Image ...");
        encodeAndDecode(imgPath, imgPathEncoded, imgPathDecoded);
        System.out.println("Text ...");
        encodeAndDecode(textPath, textPathEncoded, textPathDecoded);
        //System.out.println("Video ...");
       // encodeAndDecode(videoPath, videoPathEncoded, videoPathDecoded);

        System.out.println("\nDone.");
    }
}
