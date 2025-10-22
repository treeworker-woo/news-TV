package com.texteditor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;

/**
 * Unit tests for HelpManager class
 */
class HelpManagerTest {

    @Test
    void testHelpManagerCreation() {
        // This test verifies that HelpManager can be used without instantiation
        // since it contains only static methods
        assertDoesNotThrow(() -> {
            // Just accessing the class should not throw exceptions
            Class<?> clazz = Class.forName("com.texteditor.HelpManager");
            assertNotNull(clazz);
        });
    }

    @Test
    void testAboutDialogContent() {
        // Test that about dialog methods don't throw exceptions
        JFrame testFrame = new JFrame();
        assertDoesNotThrow(() -> HelpManager.showAboutDialog(testFrame));
    }

    @Test
    void testUserGuideContent() {
        // Test that user guide methods don't throw exceptions
        JFrame testFrame = new JFrame();
        assertDoesNotThrow(() -> HelpManager.showUserGuide(testFrame));
    }

    @Test
    void testKeyboardShortcutsContent() {
        // Test that keyboard shortcuts methods don't throw exceptions
        JFrame testFrame = new JFrame();
        assertDoesNotThrow(() -> HelpManager.showKeyboardShortcuts(testFrame));
    }
}