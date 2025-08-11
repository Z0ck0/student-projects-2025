package com.testautomation.exceptions;

/**
 * Exception thrown when WebDriver operations fail.
 * This includes driver initialization, navigation, and element interaction failures.
 */
public class WebDriverException extends FrameworkException {
    
    public WebDriverException(String message) {
        super("WEBDRIVER_ERROR", "WebDriver", message);
    }
    
    public WebDriverException(String message, Throwable cause) {
        super("WEBDRIVER_ERROR", "WebDriver", message, cause);
    }
    
    public WebDriverException(String component, String message) {
        super("WEBDRIVER_ERROR", component, message);
    }
    
    public WebDriverException(String component, String message, Throwable cause) {
        super("WEBDRIVER_ERROR", component, message, cause);
    }
}
