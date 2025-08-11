package com.testautomation.exceptions;

/**
 * Exception thrown when configuration operations fail.
 * This includes property loading, validation, and configuration-related failures.
 */
public class ConfigurationException extends FrameworkException {
    
    public ConfigurationException(String message) {
        super("CONFIGURATION_ERROR", "Configuration", message);
    }
    
    public ConfigurationException(String message, Throwable cause) {
        super("CONFIGURATION_ERROR", "Configuration", message, cause);
    }
    
    public ConfigurationException(String component, String message) {
        super("CONFIGURATION_ERROR", component, message);
    }
    
    public ConfigurationException(String component, String message, Throwable cause) {
        super("CONFIGURATION_ERROR", component, message, cause);
    }
}
