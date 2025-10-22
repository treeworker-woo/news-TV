package com.texteditor;

import javax.swing.*;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;

/**
 * Handles text search functionality within the text area
 */
public class SearchFunction {
    private JTextArea textArea;
    private Highlighter highlighter;
    private Highlighter.HighlightPainter painter;

    public SearchFunction(JTextArea textArea) {
        this.textArea = textArea;
        this.highlighter = textArea.getHighlighter();
        this.painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
    }

    /**
     * Searches for text in the text area and highlights matches
     * @param searchTerm text to search for
     * @return true if text found, false otherwise
     */
    public boolean search(String searchTerm) {
        // Clear previous highlights
        highlighter.removeAllHighlights();

        String text = textArea.getText();
        String searchText = searchTerm.toLowerCase();
        String content = text.toLowerCase();

        int index = content.indexOf(searchText);
        boolean found = false;

        while (index >= 0) {
            try {
                highlighter.addHighlight(index, index + searchText.length(), painter);
                found = true;

                // Move caret to first occurrence
                if (index == content.indexOf(searchText)) {
                    textArea.setCaretPosition(index + searchText.length());
                    textArea.moveCaretPosition(index);
                }

            } catch (Exception e) {
                // Handle highlighting errors gracefully
                System.err.println("Error highlighting text: " + e.getMessage());
            }

            index = content.indexOf(searchText, index + 1);
        }

        return found;
    }
}