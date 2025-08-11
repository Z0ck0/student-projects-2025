package com.testautomation.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static final String CONFIG_FILE = "src/test/resources/config.properties";

    static {
        try {
            properties = new Properties();
            FileInputStream input = new FileInputStream(CONFIG_FILE);
            properties.load(input);
            input.close();
            LoggerUtil.info("Configuration loaded successfully from " + CONFIG_FILE);
        } catch (IOException e) {
            LoggerUtil.error("Failed to load configuration file: " + e.getMessage(), e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static int getIntProperty(String key, int defaultValue) {
        String value = properties.getProperty(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = properties.getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }

    public static String getBaseUrl() {
        return getProperty("base.url", "https://demoqa.com/");
    }

    public static String getDefaultBrowser() {
        return getProperty("browser.default", "chrome");
    }

    public static boolean isHeadless() {
        return getBooleanProperty("browser.headless", false);
    }

    public static int getImplicitWait() {
        return getIntProperty("timeout.implicit", 20);
    }

    public static int getExplicitWait() {
        return getIntProperty("timeout.explicit", 20);
    }

    public static int getPageLoadTimeout() {
        return getIntProperty("timeout.pageLoad", 60);
    }

    public static boolean isScreenshotEnabled() {
        return getBooleanProperty("screenshot.enabled", true);
    }

    public static String getScreenshotDirectory() {
        return getProperty("screenshot.dir", "screenshots");
    }

    public static boolean isParallelEnabled() {
        return getBooleanProperty("parallel.enabled", true);
    }

    public static int getParallelThreadCount() {
        return getIntProperty("parallel.threadCount", 4);
    }
}
