package com.testautomation.tests;

import com.testautomation.utilities.ConfigReader;
import com.testautomation.utilities.LoggerUtil;
import com.testautomation.utilities.RandomDataGenerator;
import com.testautomation.utilities.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
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
    public void setUp(@org.testng.annotations.Optional("chrome") String browserName) {
        try {
            if (browserName == null || browserName.isEmpty()) {
                browserName = ConfigReader.getDefaultBrowser();
            }
            webDriverManager = new WebDriverManager();
            driver = webDriverManager.initiateDriver(browserName);
            wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
            actions = new Actions(driver);

            // Navigate to base URL
            driver.get(ConfigReader.getBaseUrl());
            LoggerUtil.info("Test setup completed successfully for browser: " + browserName);
        } catch (Exception e) {
            LoggerUtil.error("Failed to set up test: " + e.getMessage(), e);
            throw new RuntimeException("Test setup failed", e);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (webDriverManager != null) {
            webDriverManager.quitDriver();
        }
    }
}
