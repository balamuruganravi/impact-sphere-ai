package com.citi.impactsphere.ai.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public final class FileUtils {

    private FileUtils() {
    }

    public static Path writeBase64ToFile(String base64, String directory, String fileName) throws IOException {
        byte[] data = base64 != null ? Base64.getDecoder().decode(base64) : new byte[0];
        Path dir = Paths.get(directory == null ? "./tmp" : directory);
        Files.createDirectories(dir);
        Path target = dir.resolve(fileName);
        return Files.write(target, data);
    }
}
