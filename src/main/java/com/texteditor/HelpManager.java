package com.texteditor;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Manages help functionality including about dialog and user guide
 */
public class HelpManager {

    /**
     * Shows the About dialog with application information
     * @param parent the parent component for the dialog
     */
    public static void showAboutDialog(Component parent) {
        String aboutText =
                "<html><center>" +
                        "<h2>Java Text Editor</h2>" +
                        "<p>Version 1.0.0</p>" +
                        "<p>A simple and powerful text editor built with Java Swing</p>" +
                        "<br>" +
                        "<p><b>Features:</b></p>" +
                        "<ul>" +
                        "<li>Create, open, and save text files</li>" +
                        "<li>Find and highlight text</li>" +
                        "<li>Configurable font settings</li>" +
                        "<li>Cross-platform compatibility</li>" +
                        "</ul>" +
                        "<br>" +
                        "<p>Built with Java 11 • MIT License</p>" +
                        "</center></html>";

        JOptionPane.showMessageDialog(parent, aboutText,
                "About Java Text Editor", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows the user guide with keyboard shortcuts and usage tips
     * @param parent the parent component for the dialog
     */
    public static void showUserGuide(Component parent) {
        String guideText =
                "<html><div style='width: 400px;'>" +
                        "<h2>User Guide</h2>" +
                        "<h3>File Operations:</h3>" +
                        "<ul>" +
                        "<li><b>New File:</b> Ctrl+N - Create a new document</li>" +
                        "<li><b>Open File:</b> Ctrl+O - Open an existing text file</li>" +
                        "<li><b>Save File:</b> Ctrl+S - Save the current document</li>" +
                        "<li><b>Save As:</b> Ctrl+Shift+S - Save with a new filename</li>" +
                        "</ul>" +

                        "<h3>Edit Operations:</h3>" +
                        "<ul>" +
                        "<li><b>Cut:</b> Ctrl+X - Cut selected text</li>" +
                        "<li><b>Copy:</b> Ctrl+C - Copy selected text</li>" +
                        "<li><b>Paste:</b> Ctrl+V - Paste from clipboard</li>" +
                        "<li><b>Find:</b> Ctrl+F - Search for text in document</li>" +
                        "</ul>" +

                        "<h3>Tips:</h3>" +
                        "<ul>" +
                        "<li>Use Find feature to quickly locate text</li>" +
                        "<li>All matches will be highlighted in yellow</li>" +
                        "<li>Configuration can be modified in config.yaml</li>" +
                        "<li>Supported file formats: .txt, .java, .xml, etc.</li>" +
                        "</ul>" +
                        "</div></html>";

        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(guideText);
        textPane.setEditable(false);
        textPane.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        JOptionPane.showMessageDialog(parent, scrollPane,
                "User Guide", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows keyboard shortcuts reference
     * @param parent the parent component for the dialog
     */
    public static void showKeyboardShortcuts(Component parent) {
        String shortcutsText =
                "<html><center>" +
                        "<h2>Keyboard Shortcuts</h2>" +
                        "<table border='1' cellpadding='8' style='border-collapse: collapse;'>" +
                        "<tr><th>Function</th><th>Shortcut</th></tr>" +
                        "<tr><td>New File</td><td>Ctrl + N</td></tr>" +
                        "<tr><td>Open File</td><td>Ctrl + O</td></tr>" +
                        "<tr><td>Save File</td><td>Ctrl + S</td></tr>" +
                        "<tr><td>Save As</td><td>Ctrl + Shift + S</td></tr>" +
                        "<tr><td>Cut</td><td>Ctrl + X</td></tr>" +
                        "<tr><td>Copy</td><td>Ctrl + C</td></tr>" +
                        "<tr><td>Paste</td><td>Ctrl + V</td></tr>" +
                        "<tr><td>Find</td><td>Ctrl + F</td></tr>" +
                        "<tr><td>Select All</td><td>Ctrl + A</td></tr>" +
                        "<tr><td>Undo</td><td>Ctrl + Z</td></tr>" +
                        "<tr><td>Redo</td><td>Ctrl + Y</td></tr>" +
                        "</table>" +
                        "</center></html>";

        JOptionPane.showMessageDialog(parent, shortcutsText,
                "Keyboard Shortcuts", JOptionPane.INFORMATION_MESSAGE);
    }
}