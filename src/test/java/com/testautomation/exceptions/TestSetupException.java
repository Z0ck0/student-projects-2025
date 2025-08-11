package com.testautomation.exceptions;

/**
 * Exception thrown when test setup fails.
 * This includes WebDriver initialization, configuration loading, and other setup-related failures.
 */
public class TestSetupException extends FrameworkException {
    
    public TestSetupException(String message) {
        super("TEST_SETUP_ERROR", "TestSetup", message);
    }
    
    public TestSetupException(String message, Throwable cause) {
        super("TEST_SETUP_ERROR", "TestSetup", message, cause);
    }
    
    public TestSetupException(String component, String message) {
        super("TEST_SETUP_ERROR", component, message);
    }
    
    public TestSetupException(String component, String message, Throwable cause) {
        super("TEST_SETUP_ERROR", component, message, cause);
    }
}
