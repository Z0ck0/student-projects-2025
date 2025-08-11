package com.testautomation.utils.browser;

import com.testautomation.utils.common.LoggerUtil;
import com.testautomation.core.exceptions.FrameworkException;
import com.testautomation.core.exceptions.TestSetupException;
import com.testautomation.core.exceptions.WebDriverException;
import com.testautomation.core.exceptions.ConfigurationException;

/**
 * Utility class for centralized exception handling and error reporting.
 * Provides consistent error handling strategies across the framework.
 */
public class ExceptionHandler {
    
    /**
     * Handle framework exceptions with appropriate logging and recovery
     */
    public static void handleFrameworkException(FrameworkException e) {
        LoggerUtil.error("Framework Exception: " + e.getErrorCode() + " in " + e.getComponent(), e);
        
        // Log additional context information
        LoggerUtil.error("Error Code: " + e.getErrorCode());
        LoggerUtil.error("Component: " + e.getComponent());
        LoggerUtil.error("Timestamp: " + e.getTimestamp());
        
        // Re-throw the exception
        throw e;
    }
    
    /**
     * Handle test setup exceptions
     */
    public static void handleTestSetupException(TestSetupException e) {
        LoggerUtil.error("Test Setup Exception in " + e.getComponent() + ": " + e.getMessage(), e);
        
        // Log setup failure details
        LoggerUtil.error("Setup Component: " + e.getComponent());
        LoggerUtil.error("Setup Error: " + e.getMessage());
        
        // Re-throw the exception
        throw e;
    }
    
    /**
     * Handle WebDriver exceptions
     */
    public static void handleWebDriverException(WebDriverException e) {
        LoggerUtil.error("WebDriver Exception in " + e.getComponent() + ": " + e.getMessage(), e);
        
        // Log WebDriver failure details
        LoggerUtil.error("WebDriver Component: " + e.getComponent());
        LoggerUtil.error("WebDriver Error: " + e.getMessage());
        
        // Re-throw the exception
        throw e;
    }
    
    /**
     * Handle configuration exceptions
     */
    public static void handleConfigurationException(ConfigurationException e) {
        LoggerUtil.error("Configuration Exception in " + e.getComponent() + ": " + e.getMessage(), e);
        
        // Log configuration failure details
        LoggerUtil.error("Configuration Component: " + e.getComponent());
        LoggerUtil.error("Configuration Error: " + e.getMessage());
        
        // Re-throw the exception
        throw e;
    }
    
    /**
     * Handle generic exceptions with framework context
     */
    public static void handleGenericException(Exception e, String context) {
        LoggerUtil.error("Generic Exception in " + context + ": " + e.getMessage(), e);
        
        // Log generic failure details
        LoggerUtil.error("Context: " + context);
        LoggerUtil.error("Exception Type: " + e.getClass().getSimpleName());
        LoggerUtil.error("Exception Message: " + e.getMessage());
        
        // Wrap in framework exception if it's not already one
        if (!(e instanceof FrameworkException)) {
            throw new FrameworkException("GENERIC_ERROR", context, 
                "Unexpected error in " + context + ": " + e.getMessage(), e);
        }
        
        // Re-throw framework exceptions as-is
        throw (FrameworkException) e;
    }
    
    /**
     * Check if an exception is recoverable
     */
    public static boolean isRecoverable(Exception e) {
        if (e instanceof ConfigurationException) {
            // Configuration errors might be recoverable with retry
            return true;
        }
        
        if (e instanceof WebDriverException) {
            // WebDriver errors might be recoverable with retry
            return true;
        }
        
        // Test setup errors are generally not recoverable
        if (e instanceof TestSetupException) {
            return false;
        }
        
        // Default to not recoverable for safety
        return false;
    }
    
    /**
     * Get user-friendly error message
     */
    public static String getUserFriendlyMessage(Exception e) {
        if (e instanceof FrameworkException) {
            FrameworkException fe = (FrameworkException) e;
            return String.format("Error in %s: %s", fe.getComponent(), fe.getMessage());
        }
        
        return "An unexpected error occurred: " + e.getMessage();
    }
    
    /**
     * Log exception with stack trace for debugging
     */
    public static void logExceptionWithStackTrace(Exception e, String context) {
        LoggerUtil.error("=== Exception Details for " + context + " ===");
        LoggerUtil.error("Exception Type: " + e.getClass().getName());
        LoggerUtil.error("Exception Message: " + e.getMessage());
        LoggerUtil.error("Stack Trace:");
        
        // Log stack trace line by line for better readability
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (int i = 0; i < Math.min(stackTrace.length, 10); i++) {
            LoggerUtil.error("  at " + stackTrace[i]);
        }
        
        if (stackTrace.length > 10) {
            LoggerUtil.error("  ... and " + (stackTrace.length - 10) + " more lines");
        }
        
        LoggerUtil.error("=== End Exception Details ===");
    }
}
