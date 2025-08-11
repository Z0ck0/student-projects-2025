package com.testautomation.utilities;

import com.testautomation.exceptions.ConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static final String CONFIG_FILE = "config.properties";
    private static boolean isInitialized = false;

    static {
        initializeProperties();
    }

    /**
     * Initialize properties from classpath
     */
    private static void initializeProperties() {
        try {
            properties = new Properties();
            
            // Try to load from classpath first (recommended approach)
            InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
            
            if (input == null) {
                // Fallback: try to load from current class location
                input = ConfigReader.class.getResourceAsStream("/" + CONFIG_FILE);
            }
            
            if (input == null) {
                // Final fallback: try to load from resources directory
                input = ConfigReader.class.getResourceAsStream(CONFIG_FILE);
            }
            
            if (input != null) {
                properties.load(input);
                input.close();
                isInitialized = true;
                LoggerUtil.info("Configuration loaded successfully from classpath: " + CONFIG_FILE);
            } else {
                throw new ConfigurationException("ConfigReader", 
                    "Configuration file not found: " + CONFIG_FILE + 
                    ". Please ensure the file exists in src/test/resources/");
            }
            
        } catch (IOException e) {
            LoggerUtil.error("Failed to load configuration file: " + e.getMessage(), e);
            throw new ConfigurationException("ConfigReader", 
                "Failed to load configuration file: " + CONFIG_FILE, e);
        } catch (Exception e) {
            LoggerUtil.error("Unexpected error loading configuration: " + e.getMessage(), e);
            throw new ConfigurationException("ConfigReader", 
                "Unexpected error loading configuration", e);
        }
    }

    /**
     * Reload configuration (useful for testing or dynamic config changes)
     */
    public static void reloadConfiguration() {
        try {
            isInitialized = false;
            initializeProperties();
            LoggerUtil.info("Configuration reloaded successfully");
        } catch (Exception e) {
            LoggerUtil.error("Failed to reload configuration: " + e.getMessage(), e);
            throw new ConfigurationException("ConfigReader", 
                "Failed to reload configuration", e);
        }
    }

    /**
     * Check if configuration is properly initialized
     */
    public static boolean isInitialized() {
        return isInitialized && properties != null;
    }

    /**
     * Validate that configuration is initialized before proceeding
     */
    private static void validateInitialization() {
        if (!isInitialized()) {
            throw new ConfigurationException("ConfigReader", 
                "Configuration not initialized. Cannot proceed with property retrieval.");
        }
    }

    public static String getProperty(String key) {
        validateInitialization();
        String value = properties.getProperty(key);
        if (value == null) {
            throw new ConfigurationException("ConfigReader", 
                "Property not found: " + key);
        }
        return value;
    }

    public static String getProperty(String key, String defaultValue) {
        if (!isInitialized()) {
            LoggerUtil.warning("Configuration not initialized, using default value for: " + key);
            return defaultValue;
        }
        return properties.getProperty(key, defaultValue);
    }

    public static int getIntProperty(String key, int defaultValue) {
        String value = getProperty(key, String.valueOf(defaultValue));
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            LoggerUtil.warning("Invalid integer value for property '" + key + "': " + value + 
                ". Using default: " + defaultValue);
            return defaultValue;
        }
    }

    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = getProperty(key, String.valueOf(defaultValue));
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

    /**
     * Validate all required configuration properties
     */
    public static void validateConfiguration() {
        try {
            validateInitialization();
            
            // Validate required properties
            String baseUrl = getProperty("base.url");
            if (baseUrl == null || baseUrl.trim().isEmpty()) {
                throw new ConfigurationException("ConfigReader", 
                    "Base URL is required and cannot be empty");
            }
            
            String defaultBrowser = getProperty("browser.default");
            if (defaultBrowser == null || defaultBrowser.trim().isEmpty()) {
                throw new ConfigurationException("ConfigReader", 
                    "Default browser is required and cannot be empty");
            }
            
            LoggerUtil.info("Configuration validation completed successfully");
        } catch (Exception e) {
            LoggerUtil.error("Configuration validation failed: " + e.getMessage(), e);
            throw new ConfigurationException("ConfigReader", 
                "Configuration validation failed", e);
        }
    }
}
