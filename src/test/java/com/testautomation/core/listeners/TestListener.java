package com.testautomation.core.listeners;

import com.testautomation.utils.common.LoggerUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TestNG listener that provides comprehensive test execution tracking and reporting.
 * This listener tracks test execution across the entire test suite and provides
 * detailed statistics and debugging information.
 */
public class TestListener implements ITestListener {
    
    // Thread-safe counters for test execution tracking
    private static final ConcurrentHashMap<String, AtomicInteger> testClassCounter = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, AtomicInteger> testMethodCounter = new ConcurrentHashMap<>();
    private static final AtomicInteger totalTestsStarted = new AtomicInteger(0);
    private static final AtomicInteger totalTestsFinished = new AtomicInteger(0);
    private static final AtomicInteger totalTestsPassed = new AtomicInteger(0);
    private static final AtomicInteger totalTestsFailed = new AtomicInteger(0);
    private static final AtomicInteger totalTestsSkipped = new AtomicInteger(0);
    
    @Override
    public void onTestStart(ITestResult result) {
        try {
            String testName = result.getName();
            String testClass = result.getTestClass().getName();
            String browser = getBrowserFromResult(result);
            
            // Update counters
            totalTestsStarted.incrementAndGet();
            testClassCounter.computeIfAbsent(testClass, k -> new AtomicInteger(0)).incrementAndGet();
            testMethodCounter.computeIfAbsent(testName, k -> new AtomicInteger(0)).incrementAndGet();
            
            // Log test start
            LoggerUtil.info("=== Test Started ===");
            LoggerUtil.info("Test: " + testName);
            LoggerUtil.info("Class: " + testClass);
            LoggerUtil.info("Browser: " + browser);
            LoggerUtil.info("Thread: " + Thread.currentThread().getName());
            LoggerUtil.info("Start Time: " + System.currentTimeMillis());
            LoggerUtil.info("==================");
            
        } catch (Exception e) {
            LoggerUtil.error("Error in onTestStart for " + result.getName(), e);
        }
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            String testName = result.getName();
            long duration = result.getEndMillis() - result.getStartMillis();
            
            // Update counters
            totalTestsFinished.incrementAndGet();
            totalTestsPassed.incrementAndGet();
            
            // Log test success
            LoggerUtil.info("=== Test Passed ===");
            LoggerUtil.info("Test: " + testName);
            LoggerUtil.info("Duration: " + duration + "ms");
            LoggerUtil.info("Status: PASSED");
            LoggerUtil.info("==================");
            
        } catch (Exception e) {
            LoggerUtil.error("Error in onTestSuccess for " + result.getName(), e);
        }
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            String testName = result.getName();
            long duration = result.getEndMillis() - result.getStartMillis();
            Throwable throwable = result.getThrowable();
            
            // Update counters
            totalTestsFinished.incrementAndGet();
            totalTestsFailed.incrementAndGet();
            
            // Log test failure
            LoggerUtil.error("=== Test Failed ===");
            LoggerUtil.error("Test: " + testName);
            LoggerUtil.error("Duration: " + duration + "ms");
            LoggerUtil.error("Status: FAILED");
            LoggerUtil.error("Error: " + (throwable != null ? throwable.getMessage() : "Unknown error"));
            if (throwable != null) {
                LoggerUtil.error("Stack Trace:", throwable);
            }
            LoggerUtil.error("==================");
            
        } catch (Exception e) {
            LoggerUtil.error("Error in onTestFailure for " + result.getName(), e);
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        try {
            String testName = result.getName();
            long duration = result.getEndMillis() - result.getStartMillis();
            
            // Update counters
            totalTestsFinished.incrementAndGet();
            totalTestsSkipped.incrementAndGet();
            
            // Log test skip
            LoggerUtil.warning("=== Test Skipped ===");
            LoggerUtil.warning("Test: " + testName);
            LoggerUtil.warning("Duration: " + duration + "ms");
            LoggerUtil.warning("Status: SKIPPED");
            LoggerUtil.warning("===================");
            
        } catch (Exception e) {
            LoggerUtil.error("Error in onTestSkipped for " + result.getName(), e);
        }
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        try {
            String testName = result.getName();
            long duration = result.getEndMillis() - result.getStartMillis();
            
            // Log test failure within success percentage
            LoggerUtil.warning("=== Test Failed Within Success Percentage ===");
            LoggerUtil.warning("Test: " + testName);
            LoggerUtil.warning("Duration: " + duration + "ms");
            LoggerUtil.warning("Status: FAILED_WITHIN_SUCCESS_PERCENTAGE");
            LoggerUtil.warning("==========================================");
            
        } catch (Exception e) {
            LoggerUtil.error("Error in onTestFailedButWithinSuccessPercentage for " + result.getName(), e);
        }
    }
    
    @Override
    public void onStart(ITestContext context) {
        try {
            LoggerUtil.info("=== Test Suite Started ===");
            LoggerUtil.info("Suite: " + context.getName());
            LoggerUtil.info("Start Time: " + System.currentTimeMillis());
            LoggerUtil.info("Parallel: " + context.getCurrentXmlTest().getParallel());
            LoggerUtil.info("Thread Count: " + context.getCurrentXmlTest().getThreadCount());
            LoggerUtil.info("Parameters: " + context.getCurrentXmlTest().getAllParameters());
            LoggerUtil.info("=========================");
            
        } catch (Exception e) {
            LoggerUtil.error("Error in onStart for test suite: " + context.getName(), e);
        }
    }
    
    @Override
    public void onFinish(ITestContext context) {
        try {
            LoggerUtil.info("=== Test Suite Finished ===");
            LoggerUtil.info("Suite: " + context.getName());
            LoggerUtil.info("End Time: " + System.currentTimeMillis());
            LoggerUtil.info("Total Tests: " + context.getAllTestMethods().length);
            LoggerUtil.info("Passed: " + context.getPassedTests().size());
            LoggerUtil.info("Failed: " + context.getFailedTests().size());
            LoggerUtil.info("Skipped: " + context.getSkippedTests().size());
            LoggerUtil.info("==========================");
            
            // Log final statistics
            logFinalStatistics();
            
        } catch (Exception e) {
            LoggerUtil.error("Error in onFinish for test suite: " + context.getName(), e);
        }
    }
    
    /**
     * Extract browser information from test result
     * @param result the test result
     * @return browser name or "Unknown"
     */
    private String getBrowserFromResult(ITestResult result) {
        try {
            // Try to get browser from parameters
            Object[] parameters = result.getParameters();
            if (parameters != null && parameters.length > 0 && parameters[0] != null) {
                return parameters[0].toString();
            }
            
            // Try to get browser from test context
            ITestContext context = result.getTestContext();
            if (context != null) {
                String browser = context.getCurrentXmlTest().getParameter("browser");
                if (browser != null && !browser.isEmpty()) {
                    return browser;
                }
            }
            
            return "Unknown";
        } catch (Exception e) {
            return "Unknown";
        }
    }
    
    /**
     * Log final test execution statistics
     */
    private void logFinalStatistics() {
        LoggerUtil.info("=== Final Test Execution Statistics ===");
        LoggerUtil.info("Total Tests Started: " + totalTestsStarted.get());
        LoggerUtil.info("Total Tests Finished: " + totalTestsFinished.get());
        LoggerUtil.info("Total Tests Passed: " + totalTestsPassed.get());
        LoggerUtil.info("Total Tests Failed: " + totalTestsFailed.get());
        LoggerUtil.info("Total Tests Skipped: " + totalTestsSkipped.get());
        
        // Calculate success rate
        int totalFinished = totalTestsFinished.get();
        if (totalFinished > 0) {
            double successRate = (double) totalTestsPassed.get() / totalFinished * 100;
            LoggerUtil.info("Success Rate: " + String.format("%.2f", successRate) + "%");
        }
        
        // Log test class statistics
        LoggerUtil.info("Test Class Execution Counts:");
        testClassCounter.forEach((className, count) -> 
            LoggerUtil.info("  " + className + ": " + count.get() + " tests")
        );
        
        LoggerUtil.info("=====================================");
    }
    
    /**
     * Get current test execution statistics
     * @return formatted string with current statistics
     */
    public static String getCurrentStatistics() {
        StringBuilder stats = new StringBuilder();
        stats.append("=== Current Test Execution Statistics ===\n");
        stats.append("Total Started: ").append(totalTestsStarted.get()).append("\n");
        stats.append("Total Finished: ").append(totalTestsFinished.get()).append("\n");
        stats.append("Total Passed: ").append(totalTestsPassed.get()).append("\n");
        stats.append("Total Failed: ").append(totalTestsFailed.get()).append("\n");
        stats.append("Total Skipped: ").append(totalTestsSkipped.get()).append("\n");
        stats.append("========================================");
        return stats.toString();
    }
    
    /**
     * Reset all counters (useful for test suite cleanup)
     */
    public static void resetCounters() {
        testClassCounter.clear();
        testMethodCounter.clear();
        totalTestsStarted.set(0);
        totalTestsFinished.set(0);
        totalTestsPassed.set(0);
        totalTestsFailed.set(0);
        totalTestsSkipped.set(0);
        
        LoggerUtil.info("Test execution counters have been reset");
    }
}


