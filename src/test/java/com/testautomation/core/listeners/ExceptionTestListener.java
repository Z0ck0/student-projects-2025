package com.testautomation.core.listeners;

import com.testautomation.core.exceptions.FrameworkException;
import com.testautomation.core.exceptions.TestSetupException;
import com.testautomation.core.exceptions.WebDriverException;
import com.testautomation.core.exceptions.ConfigurationException;

import com.testautomation.utils.common.LoggerUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNG listener for centralized exception handling and error reporting.
 * Provides consistent error handling across all test executions.
 */
public class ExceptionTestListener implements ITestListener {
    
    @Override
    public void onTestStart(ITestResult result) {
        try {
            LoggerUtil.info("Starting test: " + result.getName() + " in " + result.getTestClass().getName());
        } catch (Exception e) {
            LoggerUtil.error("Error in onTestStart for " + result.getName(), e);
        }
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            LoggerUtil.info("Test passed: " + result.getName() + " (Duration: " + 
                (result.getEndMillis() - result.getStartMillis()) + "ms)");
        } catch (Exception e) {
            LoggerUtil.error("Error in onTestSuccess for " + result.getName(), e);
        }
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            Throwable throwable = result.getThrowable();
            LoggerUtil.error("Test failed: " + result.getName(), throwable);
            
            // Handle different types of exceptions
            if (throwable instanceof TestSetupException) {
                handleTestSetupFailure(result, (TestSetupException) throwable);
            } else if (throwable instanceof WebDriverException) {
                handleWebDriverFailure(result, (WebDriverException) throwable);
            } else if (throwable instanceof ConfigurationException) {
                handleConfigurationFailure(result, (ConfigurationException) throwable);
            } else if (throwable instanceof FrameworkException) {
                handleFrameworkFailure(result, (FrameworkException) throwable);
            } else {
                handleGenericFailure(result, throwable);
            }
            
        } catch (Exception e) {
            LoggerUtil.error("Error in onTestFailure for " + result.getName(), e);
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        try {
            LoggerUtil.warning("Test skipped: " + result.getName());
            if (result.getThrowable() != null) {
                LoggerUtil.warning("Skip reason: " + result.getThrowable().getMessage());
            }
        } catch (Exception e) {
            LoggerUtil.error("Error in onTestSkipped for " + result.getName(), e);
        }
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        try {
            LoggerUtil.warning("Test failed but within success percentage: " + result.getName());
        } catch (Exception e) {
            LoggerUtil.error("Error in onTestFailedButWithinSuccessPercentage for " + result.getName(), e);
        }
    }
    
    @Override
    public void onStart(ITestContext context) {
        try {
            LoggerUtil.info("Test suite started: " + context.getName());
            LoggerUtil.info("Test suite parameters: " + context.getCurrentXmlTest().getAllParameters());
        } catch (Exception e) {
            LoggerUtil.error("Error in onStart for test suite: " + context.getName(), e);
        }
    }
    
    @Override
    public void onFinish(ITestContext context) {
        try {
            LoggerUtil.info("Test suite finished: " + context.getName());
            LoggerUtil.info("Total tests: " + context.getAllTestMethods().length);
            LoggerUtil.info("Passed: " + context.getPassedTests().size());
            LoggerUtil.info("Failed: " + context.getFailedTests().size());
            LoggerUtil.info("Skipped: " + context.getSkippedTests().size());
        } catch (Exception e) {
            LoggerUtil.error("Error in onFinish for test suite: " + context.getName(), e);
        }
    }
    
    /**
     * Handle test setup failures
     */
    private void handleTestSetupFailure(ITestResult result, TestSetupException e) {
        LoggerUtil.error("=== Test Setup Failure Details ===");
        LoggerUtil.error("Test: " + result.getName());
        LoggerUtil.error("Component: " + e.getComponent());
        LoggerUtil.error("Error: " + e.getMessage());
        LoggerUtil.error("Recoverable: Unknown");
        LoggerUtil.error("=== End Setup Failure Details ===");
    }
    
    /**
     * Handle WebDriver failures
     */
    private void handleWebDriverFailure(ITestResult result, WebDriverException e) {
        LoggerUtil.error("=== WebDriver Failure Details ===");
        LoggerUtil.error("Test: " + result.getName());
        LoggerUtil.error("Component: " + e.getComponent());
        LoggerUtil.error("Error: " + e.getMessage());
        LoggerUtil.error("Recoverable: Unknown");
        LoggerUtil.error("=== End WebDriver Failure Details ===");
    }
    
    /**
     * Handle configuration failures
     */
    private void handleConfigurationFailure(ITestResult result, ConfigurationException e) {
        LoggerUtil.error("=== Configuration Failure Details ===");
        LoggerUtil.error("Test: " + result.getName());
        LoggerUtil.error("Component: " + e.getComponent());
        LoggerUtil.error("Error: " + e.getMessage());
        LoggerUtil.error("Recoverable: Unknown");
        LoggerUtil.error("=== End Configuration Failure Details ===");
    }
    
    /**
     * Handle framework failures
     */
    private void handleFrameworkFailure(ITestResult result, FrameworkException e) {
        LoggerUtil.error("=== Framework Failure Details ===");
        LoggerUtil.error("Test: " + result.getName());
        LoggerUtil.error("Error Code: " + e.getErrorCode());
        LoggerUtil.error("Component: " + e.getComponent());
        LoggerUtil.error("Error: " + e.getMessage());
        LoggerUtil.error("Timestamp: " + e.getTimestamp());
        LoggerUtil.error("Recoverable: Unknown");
        LoggerUtil.error("=== End Framework Failure Details ===");
    }
    
    /**
     * Handle generic failures
     */
    private void handleGenericFailure(ITestResult result, Throwable throwable) {
        LoggerUtil.error("=== Generic Failure Details ===");
        LoggerUtil.error("Test: " + result.getName());
        LoggerUtil.error("Exception Type: " + throwable.getClass().getName());
        LoggerUtil.error("Error: " + throwable.getMessage());
        LoggerUtil.error("=== End Generic Failure Details ===");
        
        // Log full stack trace for debugging
        LoggerUtil.error("Stack trace for test: " + result.getName(), 
            throwable instanceof Exception ? (Exception) throwable : new Exception(throwable));
    }
}


