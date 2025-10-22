package com.texteditor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Handles file operations including opening and saving files
 * with proper exception handling
 */
public class FileOperations {

    /**
     * Opens a file and returns its content as String
     * @param filePath path to the file to open
     * @return file content as String
     * @throws IOException if file cannot be read
     */
    public String openFile(String filePath) throws IOException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }

        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File does not exist: " + filePath);
        }

        if (!Files.isReadable(path)) {
            throw new IOException("File is not readable: " + filePath);
        }

        return new String(Files.readAllBytes(path));
    }

    /**
     * Saves content to specified file path
     * @param filePath path where to save the file
     * @param content content to save
     * @throws IOException if file cannot be written
     */
    public void saveFile(String filePath, String content) throws IOException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }

        if (content == null) {
            content = "";
        }

        Path path = Paths.get(filePath);
        Path parentDir = path.getParent();

        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }

        Files.write(path, content.getBytes());
    }
}