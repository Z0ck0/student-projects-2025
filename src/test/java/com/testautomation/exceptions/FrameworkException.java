package com.testautomation.exceptions;

/**
 * Base exception class for the test automation framework.
 * Provides consistent error handling and logging across the framework.
 */
public class FrameworkException extends RuntimeException {
    
    private final String errorCode;
    private final String component;
    private final long timestamp;
    
    public FrameworkException(String message) {
        super(message);
        this.errorCode = "FRAMEWORK_ERROR";
        this.component = "Unknown";
        this.timestamp = System.currentTimeMillis();
    }
    
    public FrameworkException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "FRAMEWORK_ERROR";
        this.component = "Unknown";
        this.timestamp = System.currentTimeMillis();
    }
    
    public FrameworkException(String errorCode, String component, String message) {
        super(message);
        this.errorCode = errorCode;
        this.component = component;
        this.timestamp = System.currentTimeMillis();
    }
    
    public FrameworkException(String errorCode, String component, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.component = component;
        this.timestamp = System.currentTimeMillis();
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public String getComponent() {
        return component;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    @Override
    public String toString() {
        return String.format("FrameworkException{errorCode='%s', component='%s', message='%s', timestamp=%d}",
                errorCode, component, getMessage(), timestamp);
    }
}
