package com.texteditor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JTextArea;

/**
 * Unit tests for SearchFunction class
 */
class SearchFunctionTest {
    private JTextArea textArea;
    private SearchFunction searchFunction;

    @BeforeEach
    void setUp() {
        textArea = new JTextArea();
        searchFunction = new SearchFunction(textArea);
    }

    @Test
    void testSearch_Found() {
        // Arrange
        textArea.setText("Hello World! This is a test.");
        String searchTerm = "test";

        // Act
        boolean result = searchFunction.search(searchTerm);

        // Assert
        assertTrue(result);
    }

    @Test
    void testSearch_NotFound() {
        // Arrange
        textArea.setText("Hello World!");
        String searchTerm = "nonexistent";

        // Act
        boolean result = searchFunction.search(searchTerm);

        // Assert
        assertFalse(result);
    }

    @Test
    void testSearch_EmptyText() {
        // Arrange
        textArea.setText("");
        String searchTerm = "test";

        // Act
        boolean result = searchFunction.search(searchTerm);

        // Assert
        assertFalse(result);
    }

    @Test
    void testSearch_CaseInsensitive() {
        // Arrange
        textArea.setText("Hello WORLD!");
        String searchTerm = "world";

        // Act
        boolean result = searchFunction.search(searchTerm);

        // Assert
        assertTrue(result);
    }
}