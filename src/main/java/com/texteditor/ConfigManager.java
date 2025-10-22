package com.texteditor;

import org.yaml.snakeyaml.Yaml;
import java.awt.Color;
import java.io.InputStream;
import java.util.Map;

/**
 * Manages configuration settings loaded from YAML file
 */
public class ConfigManager {
    private static Map<String, Object> config;

    static {
        loadConfig();
    }

    /**
     * Loads configuration from YAML file
     */
    public static void loadConfig() {
        try (InputStream input = ConfigManager.class.getClassLoader()
                .getResourceAsStream("config.yaml")) {

            if (input == null) {
                System.err.println("Config file not found, using defaults");
                setDefaultConfig();
                return;
            }

            Yaml yaml = new Yaml();
            config = yaml.load(input);

        } catch (Exception e) {
            System.err.println("Error loading config: " + e.getMessage());
            setDefaultConfig();
        }
    }

    private static void setDefaultConfig() {
        config = Map.of(
                "editor", Map.of(
                        "font", Map.of(
                                "family", "Monospaced",
                                "size", 14,
                                "color", "#000000"
                        ),
                        "window", Map.of(
                                "width", 800,
                                "height", 600
                        )
                )
        );
    }

    public static String getFontFamily() {
        try {
            Map<String, Object> editor = (Map<String, Object>) config.get("editor");
            Map<String, Object> font = (Map<String, Object>) editor.get("font");
            return (String) font.get("family");
        } catch (Exception e) {
            return "Monospaced";
        }
    }

    public static int getFontSize() {
        try {
            Map<String, Object> editor = (Map<String, Object>) config.get("editor");
            Map<String, Object> font = (Map<String, Object>) editor.get("font");
            return (Integer) font.get("size");
        } catch (Exception e) {
            return 14;
        }
    }

    public static Color getFontColor() {
        try {
            Map<String, Object> editor = (Map<String, Object>) config.get("editor");
            Map<String, Object> font = (Map<String, Object>) editor.get("font");
            String colorHex = (String) font.get("color");
            return Color.decode(colorHex);
        } catch (Exception e) {
            return Color.BLACK;
        }
    }
    // 在ConfigManager类末尾添加以下方法：

    public static boolean getShowWelcome() {
        try {
            Map<String, Object> editor = (Map<String, Object>) config.get("editor");
            Map<String, Object> help = (Map<String, Object>) editor.get("help");
            return (Boolean) help.get("show_welcome");
        } catch (Exception e) {
            return true; // 默认显示欢迎信息
        }
    }

    public static boolean getEnableTooltips() {
        try {
            Map<String, Object> editor = (Map<String, Object>) config.get("editor");
            Map<String, Object> help = (Map<String, Object>) editor.get("help");
            return (Boolean) help.get("enable_tooltips");
        } catch (Exception e) {
            return true; // 默认启用工具提示
        }
    }
}