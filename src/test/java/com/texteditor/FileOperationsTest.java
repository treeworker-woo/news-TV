package com.texteditor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Path;
import java.io.IOException;

/**
 * Unit tests for FileOperations class
 */
class FileOperationsTest {

    @TempDir
    Path tempDir;

    @Test
    void testOpenFile_Success() throws IOException {
        // Arrange
        FileOperations fileOps = new FileOperations();
        File testFile = tempDir.resolve("test.txt").toFile();
        String expectedContent = "Hello, World!";
        java.nio.file.Files.write(testFile.toPath(), expectedContent.getBytes());

        // Act
        String actualContent = fileOps.openFile(testFile.getAbsolutePath());

        // Assert
        assertEquals(expectedContent, actualContent);
    }

    @Test
    void testOpenFile_FileNotFound() {
        // Arrange
        FileOperations fileOps = new FileOperations();
        String nonExistentPath = tempDir.resolve("nonexistent.txt").toString();

        // Act & Assert
        assertThrows(IOException.class, () -> fileOps.openFile(nonExistentPath));
    }

    @Test
    void testOpenFile_NullPath() {
        // Arrange
        FileOperations fileOps = new FileOperations();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> fileOps.openFile(null));
    }

    @Test
    void testSaveFile_Success() throws IOException {
        // Arrange
        FileOperations fileOps = new FileOperations();
        File testFile = tempDir.resolve("save_test.txt").toFile();
        String content = "Test content for saving";

        // Act
        fileOps.saveFile(testFile.getAbsolutePath(), content);

        // Assert
        assertTrue(testFile.exists());
        String fileContent = new String(java.nio.file.Files.readAllBytes(testFile.toPath()));
        assertEquals(content, fileContent);
    }

    @Test
    void testSaveFile_NullContent() throws IOException {
        // Arrange
        FileOperations fileOps = new FileOperations();
        File testFile = tempDir.resolve("null_content_test.txt").toFile();

        // Act
        fileOps.saveFile(testFile.getAbsolutePath(), null);

        // Assert
        assertTrue(testFile.exists());
        String fileContent = new String(java.nio.file.Files.readAllBytes(testFile.toPath()));
        assertEquals("", fileContent);
    }
}