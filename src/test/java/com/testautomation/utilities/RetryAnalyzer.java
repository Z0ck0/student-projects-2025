package com.testautomation.utilities;

import com.testautomation.utilities.ConfigReader;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Retry analyzer that automatically retries failed tests based on configuration.
 * This helps reduce test flakiness by retrying tests that fail due to transient issues.
 */
public class RetryAnalyzer implements IRetryAnalyzer {
    
    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = getMaxRetryCount();
    
    /**
     * Get maximum retry count from configuration
     * @return maximum number of retries allowed
     */
    private static int getMaxRetryCount() {
        try {
            return ConfigReader.getIntProperty("retry.maxCount", 2);
        } catch (Exception e) {
            LoggerUtil.warning("Failed to get retry count from config, using default: 2");
            return 2;
        }
    }
    
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            LoggerUtil.info("Retrying test '" + result.getName() + "' - Attempt " + 
                (retryCount) + "/" + (MAX_RETRY_COUNT + 1));
            return true;
        }
        
        LoggerUtil.info("Test '" + result.getName() + "' failed after " + 
            (MAX_RETRY_COUNT + 1) + " attempts, marking as failed");
        return false;
    }
    
    /**
     * Get current retry count for the test
     * @return current retry attempt number
     */
    public int getRetryCount() {
        return retryCount;
    }
    
    /**
     * Get maximum retry count
     * @return maximum retry attempts allowed
     */
    public int getMaxRetryCountValue() {
        return MAX_RETRY_COUNT;
    }
    
    /**
     * Reset retry count for a new test
     */
    public void resetRetryCount() {
        retryCount = 0;
    }
}
