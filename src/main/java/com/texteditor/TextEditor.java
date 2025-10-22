package com.texteditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class TextEditor extends JFrame {
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private File currentFile;
    private FileOperations fileOps;
    private SearchFunction searchFunc;
    private static final int CTRL = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();

    public TextEditor() {
        initializeUI();
        fileOps = new FileOperations();
        searchFunc = new SearchFunction(textArea);
        ConfigManager.loadConfig();


    }

    private void initializeUI() {
        setTitle("Java Text Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create components
        textArea = new JTextArea();
        textArea.setFont(new Font(ConfigManager.getFontFamily(),
                Font.PLAIN, ConfigManager.getFontSize()));
        textArea.setForeground(ConfigManager.getFontColor());

        JScrollPane scrollPane = new JScrollPane(textArea);

        // Create menu bar
        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);

        // Create toolbar
        JToolBar toolBar = createToolBar();
        add(toolBar, BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);

        fileChooser = new JFileChooser();
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(createMenuItem("New", e -> newFile()));
        fileMenu.add(createMenuItem("Open", e -> openFile()));
        fileMenu.add(createMenuItem("Save", e -> saveFile()));
        fileMenu.add(createMenuItem("Save As", e -> saveAsFile()));
        fileMenu.addSeparator();
        fileMenu.add(createMenuItem("Exit", e -> exitApp()));

        // Edit menu
        JMenu editMenu = new JMenu("Edit");
        editMenu.add(createMenuItem("Cut", e -> textArea.cut()));
        editMenu.add(createMenuItem("Copy", e -> textArea.copy()));
        editMenu.add(createMenuItem("Paste", e -> textArea.paste()));
        editMenu.addSeparator();
        editMenu.add(createMenuItem("Search", e -> findText()));

        JMenu helpMenu = new JMenu("Help");
        helpMenu.add(createMenuItem("User Guide", e -> showUserGuide()));
        helpMenu.add(createMenuItem("Keyboard Shortcuts", e -> showKeyboardShortcuts()));
        helpMenu.addSeparator();
        helpMenu.add(createMenuItem("About", e -> showAbout()));

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }








        private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        toolBar.add(createToolButton("New", e -> newFile()));
        toolBar.add(createToolButton("Open", e -> openFile()));
        toolBar.add(createToolButton("Save", e -> saveFile()));
        toolBar.addSeparator();
        toolBar.add(createToolButton("Search", e -> findText()));

        return toolBar;
    }

    private JMenuItem createMenuItem(String text, java.awt.event.ActionListener action) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(action);
        return menuItem;
    }

    private JButton createToolButton(String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        return button;
    }

    private void newFile() {
        textArea.setText("");
        currentFile = null;
        setTitle("Java Text Editor - New File");
    }

    private void openFile() {
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                String content = fileOps.openFile(file.getAbsolutePath());
                textArea.setText(content);
                currentFile = file;
                setTitle("Java Text Editor - " + file.getName());
            } catch (Exception ex) {
                showError("Error opening file: " + ex.getMessage());
            }
        }
    }

    private void saveFile() {
        if (currentFile == null) {
            saveAsFile();
        } else {
            try {
                fileOps.saveFile(currentFile.getAbsolutePath(), textArea.getText());
                JOptionPane.showMessageDialog(this, "File saved successfully!");
            } catch (Exception ex) {
                showError("Error saving file: " + ex.getMessage());
            }
        }
    }

    private void saveAsFile() {
        int returnVal = fileChooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                fileOps.saveFile(file.getAbsolutePath(), textArea.getText());
                currentFile = file;
                setTitle("Java Text Editor - " + file.getName());
                JOptionPane.showMessageDialog(this, "File saved successfully!");
            } catch (Exception ex) {
                showError("Error saving file: " + ex.getMessage());
            }
        }
    }

    private void findText() {
        String searchTerm = JOptionPane.showInputDialog(this, "Enter text to find:");
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            boolean found = searchFunc.search(searchTerm);
            if (!found) {
                JOptionPane.showMessageDialog(this,
                        "Text not found: " + searchTerm,
                        "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void exitApp() {
        int result = JOptionPane.showConfirmDialog(this,
                "Save changes before exiting?", "Exit",
                JOptionPane.YES_NO_CANCEL_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            saveFile();
            System.exit(0);
        } else if (result == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    private void showUserGuide() {
        HelpManager.showUserGuide(this);
    }

    private void showKeyboardShortcuts() {
        HelpManager.showKeyboardShortcuts(this);
    }

    private void showAbout() {
        HelpManager.showAboutDialog(this);
    }
}