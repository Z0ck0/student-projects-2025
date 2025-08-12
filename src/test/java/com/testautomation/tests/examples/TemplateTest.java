package com.testautomation.tests.examples;

import com.testautomation.tests.base.BaseTest;
import com.testautomation.utils.common.LoggerUtil;
import com.testautomation.core.config.ConfigReader;
import com.testautomation.core.listeners.RetryAnalyzer;
import com.testautomation.enums.TestType;
import com.testautomation.enums.BrowserType;

import com.testautomation.fixtures.TestDataProvider;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

/**
 * Enhanced Template Test Class
 * 
 * This class serves as a comprehensive template for creating new test classes.
 * Copy this file or use it as a reference when creating new tests.
 * 
 * Features already provided by BaseTest:
 * - WebDriver management (driver, wait, actions)
 * - Setup and teardown (@BeforeMethod, @AfterMethod)
 * - Random test data generation
 * - Screenshot handling on failure
 * - WebDriver cleanup
 * 
 * Enhanced features in this template:
 * - Allure reporting annotations (@Epic, @Feature, @Story, @Severity)
 * - TestNG listeners and retry mechanisms
 * - Parameterized and data-driven testing
 * - Comprehensive logging and assertions
 * - Framework configuration showcase
 */
@Epic("Template Test Suite")
@Feature("Template Functionality")
@Listeners({com.testautomation.core.listeners.TestListener.class})
public class TemplateTest extends BaseTest {

    /**
     * Basic Template Test Method
     * 
     * This is an example test method showing the basic structure.
     * Copy this method and modify it for your specific test needs.
     */
    @Test(description = "Template Test Method 1 - Basic Structure Example", groups = {"smoke"}, priority = 1)
    @Story("Basic Test Structure")
    @Severity(SeverityLevel.CRITICAL)
    public void templateTestMethod1() {
        try {
            LoggerUtil.info("=== Starting Template Test Method 1 ===");
            LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
            LoggerUtil.info("Current Browser: " + ConfigReader.getDefaultBrowser());
            LoggerUtil.info("Test Start Time: " + System.currentTimeMillis());
            
            // TODO: Add your test steps here
            // Example:
            // 1. Navigate to page
            // 2. Perform actions
            // 3. Verify results
            
            // Example test logic:
            String pageTitle = driver.getTitle();
            LoggerUtil.info("Page title: " + pageTitle);
            
            // Add assertions here
            // Assert.assertEquals(pageTitle, "Expected Title");
            
            LoggerUtil.info("Template Test Method 1 completed successfully");
            LoggerUtil.info("=== Template Test Method 1 Completed ===");
            
        } catch (Exception e) {
            LoggerUtil.error("Template Test Method 1 failed: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Enhanced Template Test Method
     * 
     * Another example showing advanced test structure with framework utilities.
     * Use this as a reference for creating comprehensive test methods.
     */
    @Test(description = "Template Test Method 2 - Enhanced Structure Example")
    @Story("Enhanced Test Structure")
    @Severity(SeverityLevel.NORMAL)
    public void templateTestMethod2() {
        try {
            LoggerUtil.info("=== Starting Template Test Method 2 ===");
            LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
            LoggerUtil.info("Current Browser: " + ConfigReader.getDefaultBrowser());
            LoggerUtil.info("Base URL: " + ConfigReader.getBaseUrl());
            LoggerUtil.info("Implicit Wait: " + ConfigReader.getImplicitWait() + " seconds");
            LoggerUtil.info("Explicit Wait: " + ConfigReader.getExplicitWait() + " seconds");
            
            // TODO: Add your test steps here
            // Example:
            // 1. Setup test data
            // 2. Execute test scenario
            // 3. Validate outcomes
            
            // Example test logic:
            String currentUrl = driver.getCurrentUrl();
            LoggerUtil.info("Current URL: " + currentUrl);
            
            // Add your test logic here
            
            LoggerUtil.info("Template Test Method 2 completed successfully");
            LoggerUtil.info("=== Template Test Method 2 Completed ===");
            
        } catch (Exception e) {
            LoggerUtil.error("Template Test Method 2 failed: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Template Test Method with Parameters
     * 
     * Example of how to create parameterized tests.
     * Uncomment and modify as needed.
     */
    @Test(description = "Template Test Method with Parameters")
    @Story("Parameterized Testing")
    @Severity(SeverityLevel.NORMAL)
    @Parameters({"param1", "param2"})
    public void templateTestMethodWithParams(String param1, String param2) {
        try {
            LoggerUtil.info("=== Starting Parameterized Test ===");
            LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
            LoggerUtil.info("Parameter 1: " + param1);
            LoggerUtil.info("Parameter 2: " + param2);
            
            // TODO: Add your parameterized test logic here
            
            LoggerUtil.info("Parameterized test completed successfully");
            LoggerUtil.info("=== Parameterized Test Completed ===");
            
        } catch (Exception e) {
            LoggerUtil.error("Parameterized test failed: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Template Test Method with Data Provider
     * 
     * Example of how to create data-driven tests.
     * Uncomment and modify as needed.
     */
    @Test(description = "Template Test Method with Data Provider", dataProvider = "testData")
    @Story("Data-Driven Testing")
    @Severity(SeverityLevel.NORMAL)
    public void templateTestMethodWithDataProvider(String data1, String data2) {
        try {
            LoggerUtil.info("=== Starting Data-Driven Test ===");
            LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
            LoggerUtil.info("Data 1: " + data1);
            LoggerUtil.info("Data 2: " + data2);
            
            // TODO: Add your data-driven test logic here
            
            LoggerUtil.info("Data-driven test completed successfully");
            LoggerUtil.info("=== Data-Driven Test Completed ===");
            
        } catch (Exception e) {
            LoggerUtil.error("Data-driven test failed: " + e.getMessage());
            throw e;
        }
    }
    
    @DataProvider(name = "testData")
    public Object[][] getTestData() {
        return new Object[][] {
            {"test1", "value1"},
            {"test2", "value2"},
            {"test3", "value3"}
        };
    }

    /**
     * Framework Configuration Showcase Test
     * 
     * Demonstrates how to access and verify framework configuration.
     * Useful for understanding available framework capabilities.
     */
    @Test(description = "Framework Configuration Showcase")
    @Story("Framework Configuration")
    @Severity(SeverityLevel.MINOR)
    public void testFrameworkConfiguration() {
        try {
            LoggerUtil.info("=== Starting Framework Configuration Test ===");
            LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
            
            // Showcase framework configuration capabilities
            LoggerUtil.info("Framework Configuration:");
            LoggerUtil.info("Base URL: " + ConfigReader.getBaseUrl());
            LoggerUtil.info("Default Browser: " + ConfigReader.getDefaultBrowser());
            LoggerUtil.info("Implicit Wait: " + ConfigReader.getImplicitWait() + " seconds");
            LoggerUtil.info("Explicit Wait: " + ConfigReader.getExplicitWait() + " seconds");
            LoggerUtil.info("Page Load Timeout: " + ConfigReader.getPageLoadTimeout() + " seconds");
            LoggerUtil.info("Screenshot Enabled: " + ConfigReader.isScreenshotEnabled());
            LoggerUtil.info("Parallel Execution: " + ConfigReader.isParallelEnabled());
            
            // Verify configuration is working
            Assert.assertTrue(ConfigReader.isInitialized(), "Configuration should be initialized");
            Assert.assertNotNull(ConfigReader.getBaseUrl(), "Base URL should not be null");
            Assert.assertNotNull(ConfigReader.getDefaultBrowser(), "Default browser should not be null");
            
            LoggerUtil.info("Framework configuration test completed successfully");
            LoggerUtil.info("=== Framework Configuration Test Completed ===");
            
        } catch (Exception e) {
            LoggerUtil.error("Framework configuration test failed: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Random Data Generation Showcase Test
     * 
     * Demonstrates how to use framework's random data generation utilities.
     * Useful for creating dynamic test data.
     */
    @Test(description = "Random Data Generation Showcase")
    @Story("Random Data Generation")
    @Severity(SeverityLevel.MINOR)
    public void testRandomDataGeneration() {
        try {
            LoggerUtil.info("=== Starting Random Data Generation Test ===");
            LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
            
            // Generate random test data using framework utilities
            String randomEmail = getRandomEmail;
            String randomFirstName = getRandomFirstName;
            String randomLastName = getRandomLastName;
            String randomPhone = getRandomPhoneNumber;
            String randomAddress = getRandomAddress;
            
            LoggerUtil.info("Generated Random Data:");
            LoggerUtil.info("Email: " + randomEmail);
            LoggerUtil.info("First Name: " + randomFirstName);
            LoggerUtil.info("Last Name: " + randomLastName);
            LoggerUtil.info("Phone: " + randomPhone);
            LoggerUtil.info("Address: " + randomAddress);
            
            // TODO: Add your test logic using random data here
            
            LoggerUtil.info("Random data generation test completed successfully");
            LoggerUtil.info("=== Random Data Generation Test Completed ===");
            
        } catch (Exception e) {
            LoggerUtil.error("Random data generation test failed: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Browser Type Handling Showcase Test
     * 
     * Demonstrates how to handle different browser types and parameters.
     * Useful for cross-browser testing scenarios.
     */
    @Test(description = "Browser Type Handling Showcase")
    @Story("Browser Handling")
    @Severity(SeverityLevel.MINOR)
    @Parameters({"browser"})
    public void testBrowserTypeHandling(String browser) {
        try {
            LoggerUtil.info("=== Starting Browser Type Handling Test ===");
            LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
            LoggerUtil.info("Parameter Browser: " + browser);
            LoggerUtil.info("Current Driver Browser: " + ConfigReader.getDefaultBrowser());
            
            // Showcase browser type enum usage
            try {
                BrowserType browserType = BrowserType.fromString(browser);
                LoggerUtil.info("Parsed Browser Type: " + browserType.name());
                LoggerUtil.info("Browser Value: " + browserType.getValue());
            } catch (IllegalArgumentException e) {
                LoggerUtil.warning("Invalid browser type provided: " + browser);
                // Use default browser
                BrowserType defaultBrowser = BrowserType.CHROME;
                LoggerUtil.info("Using default browser: " + defaultBrowser.name());
            }
            
            // TODO: Add your browser-specific test logic here
            
            LoggerUtil.info("Browser type handling test completed successfully");
            LoggerUtil.info("=== Browser Type Handling Test Completed ===");
            
        } catch (Exception e) {
            LoggerUtil.error("Browser type handling test failed: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retry Mechanism Showcase Test
     * 
     * Demonstrates how to configure and use retry mechanisms.
     * Useful for handling flaky tests or network issues.
     */
    @Test(description = "Retry Mechanism Showcase")
    @Story("Retry Mechanism")
    @Severity(SeverityLevel.NORMAL)
    public void testRetryMechanism() {
        try {
            LoggerUtil.info("=== Starting Retry Mechanism Test ===");
            LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
            LoggerUtil.info("Current Browser: " + ConfigReader.getDefaultBrowser());
            
            // Showcase retry analyzer configuration
            LoggerUtil.info("Retry Configuration:");
            LoggerUtil.info("Max Retry Count: " + ConfigReader.getProperty("retry.maxCount", "2"));
            
            // TODO: Add your test logic that might benefit from retry here
            
            LoggerUtil.info("Retry mechanism test completed successfully");
            LoggerUtil.info("=== Retry Mechanism Test Completed ===");
            
        } catch (Exception e) {
            LoggerUtil.error("Retry mechanism test failed: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Wait Utilities Showcase Test
     * 
     * Demonstrates how to use framework's wait utilities.
     * Useful for handling dynamic elements and page loading.
     */
    @Test(description = "Wait Utilities Showcase")
    @Story("Wait Utilities")
    @Severity(SeverityLevel.MINOR)
    public void testWaitUtilities() {
        try {
            LoggerUtil.info("=== Starting Wait Utilities Test ===");
            LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
            LoggerUtil.info("Current Browser: " + ConfigReader.getDefaultBrowser());
            
            // Showcase various wait utilities
            LoggerUtil.info("Testing Wait Utilities:");
            
            // Test title wait
            boolean titleWaitSuccess = com.testautomation.utils.browser.WaitUtils.waitForTitleContains(driver, "Expected Title", 10);
            LoggerUtil.info("Title wait success: " + titleWaitSuccess);
            
            // Test URL wait
            boolean urlWaitSuccess = com.testautomation.utils.browser.WaitUtils.waitForUrlContains(driver, "expected-url", 10);
            LoggerUtil.info("URL wait success: " + urlWaitSuccess);
            
            // TODO: Add your wait utility test logic here
            
            LoggerUtil.info("Wait utilities test completed successfully");
            LoggerUtil.info("=== Wait Utilities Test Completed ===");
            
        } catch (Exception e) {
            LoggerUtil.error("Wait utilities test failed: " + e.getMessage());
            throw e;
        }
    }
}
