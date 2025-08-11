package com.testautomation.tests;

import com.testautomation.utilities.ConfigReader;
import com.testautomation.utilities.LoggerUtil;
import com.testautomation.utilities.RandomDataGenerator;
import com.testautomation.utilities.ScreenshotUtils;
import com.testautomation.utilities.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

/**
 * Base test class that provides common setup and teardown functionality for all test classes.
 * This class is framework-agnostic and provides WebDriver management and common test utilities.
 * Extend this class to create your own test classes.
 */
public class BaseTest {
    // region WebDriver, WebDriverWait, and Actions Declaration
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    // endregion

    protected WebDriverManager webDriverManager;

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

            // Navigate to the base URL with retry logic
            int maxRetries = 3;
            for (int i = 0; i < maxRetries; i++) {
                try {
                    driver.get(ConfigReader.getBaseUrl());
                    break;
                } catch (Exception e) {
                    if (i == maxRetries - 1) {
                        throw e;
                    }
                    LoggerUtil.warning("Failed to navigate to base URL, retrying... Attempt " + (i + 1));
                    Thread.sleep(2000);
                }
            }
            
            LoggerUtil.info("Test setup completed successfully for browser: " + browserName);
        } catch (Exception e) {
            LoggerUtil.error("Failed to set up test: " + e.getMessage(), e);
            throw new RuntimeException("Test setup failed", e);
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Take screenshot on test failure
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshotOnFailure(result.getName());
        }
        
        // Quit the WebDriver
        if (webDriverManager != null) {
            webDriverManager.quitDriver();
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
}
