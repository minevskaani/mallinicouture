package io.infosec.enigma.config;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Kostadin Almishev
 */
public class Constants {
    // Image
    public static final Path imgPath = Paths.get("./files/img.jpeg");
    public static final Path imgPathEncoded = Paths.get("./files/img_encoded.jpeg");
    public static final Path imgPathDecoded = Paths.get("./files/img_decoded.jpeg");

    // Text file
    public static final Path textPath = Paths.get("./files/text.txt");
    public static final Path textPathEncoded = Paths.get("./files/text_encoded.txt");
    public static final Path textPathDecoded = Paths.get("./files/text_decoded.txt");

    // Video file
    public static final Path videoPath = Paths.get("./files/video.mp4");
    public static final Path videoPathEncoded = Paths.get("./files/video_encoded.mp4");
    public static final Path videoPathDecoded = Paths.get("./files/video_decoded.mp4");
}
