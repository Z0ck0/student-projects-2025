package com.testautomation.tests.base;

import com.testautomation.core.config.ConfigReader;
import com.testautomation.core.driver.WebDriverManager;
import com.testautomation.core.pages.PageObjectManager;
import com.testautomation.utils.browser.ScreenshotUtils;
import com.testautomation.utils.data.RandomDataGenerator;
import com.testautomation.utils.common.LoggerUtil;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;

/**
 * Base test class that provides common setup and teardown functionality for all test classes.
 * This class is framework-agnostic and provides WebDriver management and common test utilities.
 * 
 * Key Features:
 * - Automatically navigates to base URL from config.properties on test setup
 * - No need to manually call driver.get() or ConfigReader.getBaseUrl() in test methods
 * - WebDriver, WebDriverWait, and Actions are automatically initialized
 * - Extend this class to create your own test classes.
 */
public class BaseTest {
    // region WebDriver, WebDriverWait, and Actions Declaration
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    // endregion

    protected WebDriverManager webDriverManager;
    protected PageObjectManager pages;
    
    // Control whether to auto-navigate to base URL
    private boolean autoNavigateToBaseUrl = true;

    // Variables to store generated data for testing.
    public String getRandomEmail;
    public String getRandomPassword;
    public String getRandomFirstName;
    public String getRandomLastName;
    public String getRandomFullName;
    public String getRandomPhoneNumber;
    public String getRandomCellNumber;
    public String getRandomAddress;

    public BaseTest() {
        // Generate random data once for the entire test class.
        getRandomEmail = RandomDataGenerator.getRandomEmail();
        getRandomPassword = RandomDataGenerator.getRandomPassword();
        getRandomFirstName = RandomDataGenerator.getRandomFirstName();
        getRandomLastName = RandomDataGenerator.getRandomLastName();
        getRandomFullName = RandomDataGenerator.getRandomFullName();
        getRandomPhoneNumber = RandomDataGenerator.getRandomPhoneNumber();
        getRandomCellNumber = RandomDataGenerator.getRandomCellNumber();
        getRandomAddress = RandomDataGenerator.getRandomAddress();
    }

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browserName) {
        try {
            // If the TestNG parameter is not provided, use the default browser from configuration
            if (browserName == null || browserName.isEmpty()) {
                browserName = ConfigReader.getDefaultBrowser();
            }

            webDriverManager = new WebDriverManager();

            // region Initiate the WebDriver, WebDriverWait, and Actions Initialization
            driver = webDriverManager.initiateDriver(browserName);
            wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
            actions = new Actions(driver);

            // Auto-navigate to base URL if driver is on blank page or not yet navigated
            // This can be overridden by subclasses using setAutoNavigateToBaseUrl(false)
            if (shouldAutoNavigateToBaseUrl()) {
                String currentUrl = driver.getCurrentUrl();
                if (currentUrl.equals("about:blank") || currentUrl.isEmpty() || currentUrl.equals("data:,")) {
                    LoggerUtil.info("Auto-navigating to base URL: " + ConfigReader.getBaseUrl());
                    driver.get(ConfigReader.getBaseUrl());
                }
            } else {
                LoggerUtil.info("Auto-navigation to base URL is disabled for this test class");
            }
            
            // Initialize PageObjectManager for easy access to all page objects
            pages = new PageObjectManager(driver);
            
            LoggerUtil.info("Test setup completed successfully for browser: " + browserName);
        } catch (Exception e) {
            LoggerUtil.error("Failed to set up test: " + e.getMessage(), e);
            throw new RuntimeException("Test setup failed", e);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        try {
            // Take screenshot on test failure
            if (result.getStatus() == ITestResult.FAILURE) {
                takeScreenshotOnFailure(result.getName());
            }
        } finally {
            // Always quit the WebDriver, regardless of test result
            if (webDriverManager != null && webDriverManager.isDriverInitialized()) {
                try {
                    webDriverManager.quitDriver();
                    LoggerUtil.info("WebDriver closed successfully for test: " + result.getName());
                } catch (Exception e) {
                    LoggerUtil.error("Failed to close WebDriver for test: " + result.getName(), e);
                }
            }
        }
    }

    /**
     * Take screenshot on test failure and attach to Allure report
     * @param testName the name of the failed test
     */
    private void takeScreenshotOnFailure(String testName) {
        try {
            String screenshotPath = ScreenshotUtils.takeScreenshot(driver, testName);
            if (screenshotPath != null) {
                attachScreenshotToReport(screenshotPath);
            }
        } catch (Exception e) {
            LoggerUtil.error("Failed to take screenshot on test failure: " + e.getMessage(), e);
        }
    }

    /**
     * Attach screenshot to Allure report
     * @param screenshotPath the path to the screenshot file
     */
    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] attachScreenshotToReport(String screenshotPath) {
        try {
            return ScreenshotUtils.takeScreenshotAsBytes(driver);
        } catch (Exception e) {
            LoggerUtil.error("Failed to attach screenshot to report: " + e.getMessage(), e);
            return new byte[0];
        }
    }

    /**
     * Cleanup method that runs after all tests in the suite complete
     * This ensures any remaining WebDriver instances are properly closed
     */
    @AfterSuite(alwaysRun = true)
    public void suiteCleanup() {
        try {
            if (webDriverManager != null && webDriverManager.isDriverInitialized()) {
                webDriverManager.quitDriver();
                LoggerUtil.info("Suite cleanup: WebDriver closed successfully");
            }
        } catch (Exception e) {
            LoggerUtil.error("Suite cleanup: Failed to close WebDriver", e);
        }
    }
    
    /**
     * Check if auto-navigation to base URL should be performed
     * @return true if auto-navigation is enabled, false otherwise
     */
    protected boolean shouldAutoNavigateToBaseUrl() {
        return autoNavigateToBaseUrl;
    }
    
    /**
     * Enable or disable auto-navigation to base URL
     * @param enabled true to enable, false to disable
     */
    protected void setAutoNavigateToBaseUrl(boolean enabled) {
        this.autoNavigateToBaseUrl = enabled;
        LoggerUtil.info("Auto-navigation to base URL " + (enabled ? "enabled" : "disabled"));
    }
}
