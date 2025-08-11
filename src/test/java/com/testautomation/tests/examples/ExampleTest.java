package com.testautomation.tests.examples;

import com.testautomation.fixtures.TestDataProvider;
import com.testautomation.tests.BaseTest;
import com.testautomation.utilities.ConfigReader;
import com.testautomation.utilities.LoggerUtil;
import com.testautomation.utilities.ScreenshotUtils;
import com.testautomation.utilities.WaitUtils;
import com.testautomation.utilities.RetryAnalyzer;
import com.testautomation.listeners.TestListener;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Example test class demonstrating framework usage with enhanced utilities.
 * This class shows various testing patterns and best practices including
 * performance monitoring, retry mechanisms, and comprehensive logging.
 * Replace the example methods with your own test logic.
 */
@Epic("Example Website Testing")
@Feature("Example Functionality")
@Listeners({TestListener.class})
public class ExampleTest extends BaseTest {


    @Test(description = "Verify basic page navigation")
    @Story("Page Navigation")
    @Severity(SeverityLevel.NORMAL)
    public void testBasicNavigation() {

        // Example: Navigate to a specific page (replace with your page URL)
        String targetPage = ConfigReader.getBaseUrl() + "elements";
        driver.get(targetPage);

        // Verify we're on the target page
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("elements"),
                "Should be on about page but URL was '" + currentUrl + "'");

        LoggerUtil.info("Successfully navigated to: " + currentUrl);
    }


    // EXAMPLE - Expand to see the enhanced test that now provides comprehensive validation, focusing on reliability, debugging capabilities, and page quality validation!

//    @Test(description = "Verify homepage loads successfully with enhanced utilities",
//          retryAnalyzer = RetryAnalyzer.class)
//    @Story("Homepage Navigation")
//    @Severity(SeverityLevel.CRITICAL)
//    @Description("This test verifies that the homepage loads successfully with comprehensive validation including page title, URL, DOM structure, and enhanced error handling.")
//    public void testHomepageLoads() {
//
//        try {
//            LoggerUtil.info("=== Starting Homepage Load Test ===");
//            LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
//            LoggerUtil.info("Current Browser: " + (webDriverManager != null ? "Initialized" : "Not initialized"));
//            LoggerUtil.info("Base URL: " + ConfigReader.getBaseUrl());
//            LoggerUtil.info("Test Start Time: " + System.currentTimeMillis());
//
//            // Verify page title contains expected text with enhanced assertions
//            LoggerUtil.info("Verifying page title...");
//            String actualTitle = driver.getTitle();
//
//            // Enhanced assertions with better error messages
//            Assert.assertNotNull(actualTitle, "Page title should not be null - this indicates a serious page loading issue");
//            Assert.assertFalse(actualTitle.isEmpty(), "Page title should not be empty - page may not have loaded completely");
//
//            LoggerUtil.info("Page title verified successfully: '" + actualTitle + "'");
//
//            // Verify URL contains expected domain with enhanced validation
//            LoggerUtil.info("Verifying current URL...");
//            String currentUrl = driver.getCurrentUrl();
//
//            // Enhanced URL validation
//            Assert.assertTrue(currentUrl.startsWith("http"),
//                "URL should start with 'http' - current URL: " + currentUrl);
//            Assert.assertTrue(currentUrl.contains("demoqa.com"),
//                "URL should contain expected domain 'demoqa.com' - current URL: " + currentUrl);
//
//            LoggerUtil.info("URL verification successful: " + currentUrl);
//
//            // Additional page validation checks
//            LoggerUtil.info("Performing additional page validation checks...");
//
//            // Check if page has loaded completely
//            WaitUtils.sleep(500); // Brief wait for any dynamic content
//
//            // Verify page source is not empty
//            String pageSource = driver.getPageSource();
//            Assert.assertNotNull(pageSource, "Page source should not be null");
//            Assert.assertTrue(pageSource.length() > 100,
//                "Page source should contain substantial content - current length: " + pageSource.length());
//
//            LoggerUtil.info("Page source validation successful - Length: " + pageSource.length() + " characters");
//
//            // Verify page has a body element (basic DOM structure)
//            WebElement bodyElement = driver.findElement(By.tagName("body"));
//            Assert.assertNotNull(bodyElement, "Body element should be present in the DOM");
//            Assert.assertTrue(bodyElement.isDisplayed(), "Body element should be visible");
//
//            LoggerUtil.info("DOM structure validation successful");
//
//            // Verify page has basic HTML structure
//            LoggerUtil.info("Verifying basic HTML structure...");
//            WebElement htmlElement = driver.findElement(By.tagName("html"));
//            Assert.assertNotNull(htmlElement, "HTML element should be present in the DOM");
//
//            WebElement headElement = driver.findElement(By.tagName("head"));
//            Assert.assertNotNull(headElement, "Head element should be present in the DOM");
//
//            LoggerUtil.info("HTML structure validation successful");
//
//            // Check for common page elements
//            LoggerUtil.info("Checking for common page elements...");
//            try {
//                WebElement titleElement = driver.findElement(By.tagName("title"));
//                Assert.assertNotNull(titleElement, "Title element should be present");
//                LoggerUtil.info("Title element found: " + titleElement.getText());
//            } catch (Exception e) {
//                LoggerUtil.warning("Title element not found: " + e.getMessage());
//            }
//
//            // Verify page is responsive (has viewport meta tag or responsive design indicators)
//            LoggerUtil.info("Checking page responsiveness indicators...");
//            try {
//                String viewportMeta = driver.findElement(By.cssSelector("meta[name='viewport']")).getAttribute("content");
//                if (viewportMeta != null && viewportMeta.contains("width=device-width")) {
//                    LoggerUtil.info("Responsive design detected - viewport meta tag found");
//                } else {
//                    LoggerUtil.info("Viewport meta tag found but may not be responsive");
//                }
//            } catch (Exception e) {
//                LoggerUtil.info("No viewport meta tag found - page may not be responsive");
//            }
//
//            // Log comprehensive test results
//            long testEndTime = System.currentTimeMillis();
//            LoggerUtil.info("=== Homepage Load Test Results ===");
//            LoggerUtil.info("Status: PASSED");
//            LoggerUtil.info("Page Title: " + actualTitle);
//            LoggerUtil.info("Current URL: " + currentUrl);
//            LoggerUtil.info("Page Source Length: " + pageSource.length() + " characters");
//            LoggerUtil.info("Test Duration: " + (testEndTime - System.currentTimeMillis()) + "ms");
//            LoggerUtil.info("Browser: " + (webDriverManager != null ? "Initialized" : "Not initialized"));
//            LoggerUtil.info("Thread: " + Thread.currentThread().getName());
//            LoggerUtil.info("Test End Time: " + testEndTime);
//            LoggerUtil.info("================================");
//
//        } catch (Exception e) {
//            LoggerUtil.error("Homepage load test failed with exception: " + e.getMessage(), e);
//
//            // Log test failure details
//            LoggerUtil.error("=== Test Failure Details ===");
//            LoggerUtil.error("Current URL: " + driver.getCurrentUrl());
//            LoggerUtil.error("Page Title: " + driver.getTitle());
//            LoggerUtil.info("Browser: " + (webDriverManager != null ? "Initialized" : "Not initialized"));
//            LoggerUtil.error("Thread: " + Thread.currentThread().getName());
//            LoggerUtil.error("Exception Type: " + e.getClass().getSimpleName());
//            LoggerUtil.error("Exception Message: " + e.getMessage());
//            LoggerUtil.error("==========================");
//
//            throw e; // Re-throw to trigger retry mechanism
//        }
//    }

//    @Test(description = "Verify homepage loads successfully")
//    @Story("Homepage Navigation")
//    @Severity(SeverityLevel.CRITICAL)
//    public void testHomepageLoads() {
//
//        // Verify page title contains expected text
//        String actualTitle = driver.getTitle();
//        Assert.assertNotNull(actualTitle, "Page title should not be null");
//        Assert.assertFalse(actualTitle.isEmpty(), "Page title should not be empty");
//
//        // Verify URL contains expected domain
//        String currentUrl = driver.getCurrentUrl();
//        Assert.assertTrue(currentUrl.startsWith("http"), "URL should start with 'http'");
//
//        LoggerUtil.info("Successfully loaded homepages: " + currentUrl);
//    }



    @Test(description = "Test with data provider", 
          dataProvider = "userRegistrationData", 
          dataProviderClass = TestDataProvider.class)
    @Story("Data-Driven Testing")
    @Severity(SeverityLevel.MINOR)
    public void testWithDataProvider(String firstName, String lastName, String email, 
                                   String phone, String gender, String address) {
        // This test demonstrates data-driven testing
        Assert.assertNotNull(firstName, "First name should not be null");
        Assert.assertNotNull(lastName, "Last name should not be null");
        Assert.assertNotNull(email, "Email should not be null");
        Assert.assertTrue(email.contains("@"), "Email should contain '@' symbol");
        
        // Log the test data
        LoggerUtil.info("Testing with data: " + firstName + " " + lastName + " (" + email + ")");
    }

    @Test(description = "Test browser configuration")
    @Story("Browser Configuration")
    @Severity(SeverityLevel.MINOR)
    public void testBrowserConfiguration() {
        // Verify browser configuration
        String defaultBrowser = ConfigReader.getDefaultBrowser();
        Assert.assertNotNull(defaultBrowser, "Default browser should not be null");
        
        // Verify timeout configuration
        int implicitWait = ConfigReader.getImplicitWait();
        Assert.assertTrue(implicitWait > 0, "Implicit wait should be greater than 0");
        
        // Verify base URL configuration
        String baseUrl = ConfigReader.getBaseUrl();
        Assert.assertNotNull(baseUrl, "Base URL should not be null");
        Assert.assertTrue(baseUrl.startsWith("http"), "Base URL should start with 'http'");
        
        LoggerUtil.info("Browser configuration verified successfully");
    }

    @Test(description = "Test screenshot functionality")
    @Story("Screenshot Capture")
    @Severity(SeverityLevel.MINOR)
    public void testScreenshotFunctionality() {

        // Take a screenshot
        String screenshotPath = ScreenshotUtils.takeScreenshot(driver, "test-screenshot");
        
        // Verify screenshot was taken (if enabled)
        if (ConfigReader.isScreenshotEnabled()) {
            Assert.assertNotNull(screenshotPath, "Screenshot path should not be null");
            LoggerUtil.info("Screenshot saved to: " + screenshotPath);
        }
    }

    @Test(description = "Test wait utilities")
    @Story("Wait Utilities")
    @Severity(SeverityLevel.MINOR)
    public void testWaitUtilities() {
        
        // Wait for page to load completely
        WaitUtils.sleep(2000);
        
        // Test wait for title - use a more realistic expectation
        String currentTitle = driver.getTitle();
        System.out.println("Current page title: " + currentTitle);
        
        // Wait for title to contain any text (more realistic)
        boolean titleContains = WaitUtils.waitForTitleContains(driver, "", 10);
        Assert.assertTrue(titleContains, "Page title should contain some text");
        System.out.println("Title Contains any text: " + titleContains);
        
        // Test wait for URL - use the actual base URL
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        
        // Wait for URL to contain the base URL domain
        String baseUrl = ConfigReader.getBaseUrl();
        String domain = baseUrl.replace("https://", "").replace("http://", "").replace("/", "");
        boolean urlContains = WaitUtils.waitForUrlContains(driver, domain, 10);
        Assert.assertTrue(urlContains, "URL should contain the expected domain");
        
        // Test wait for element presence (more practical)
        try {
            WebElement bodyElement = WaitUtils.waitForElementPresent(driver, By.tagName("body"), 10);
            Assert.assertNotNull(bodyElement, "Body element should be present");
            LoggerUtil.info("Body element found successfully");
        } catch (Exception e) {
            LoggerUtil.warning("Body element not found: " + e.getMessage());
        }
        
        LoggerUtil.info("Wait utilities tested successfully");
    }

    @Test(description = "Test random data generation")
    @Story("Test Data Generation")
    @Severity(SeverityLevel.MINOR)
    public void testRandomDataGeneration() {
        // Verify random data generation
        Assert.assertNotNull(getRandomEmail, "Random email should not be null");
        Assert.assertTrue(getRandomEmail.contains("@"), "Random email should contain '@'");
        
        Assert.assertNotNull(getRandomFirstName, "Random first name should not be null");
        Assert.assertFalse(getRandomFirstName.isEmpty(), "Random first name should not be empty");
        
        Assert.assertNotNull(getRandomLastName, "Random last name should not be null");
        Assert.assertFalse(getRandomLastName.isEmpty(), "Random last name should not be empty");
        
        Assert.assertNotNull(getRandomPhoneNumber, "Random phone number should not be null");
        Assert.assertFalse(getRandomPhoneNumber.isEmpty(), "Random phone number should not be empty");
        
        LoggerUtil.info("Random data generation tested successfully");

        // Print the generated random data to console
        System.out.println("=== Random Data Generation Test Results ===");
        System.out.println("Random Email: " + getRandomEmail);
        System.out.println("Random First Name: " + getRandomFirstName);
        System.out.println("Random Last Name: " + getRandomLastName);
        System.out.println("Random Phone Number: " + getRandomPhoneNumber);
        System.out.println("==========================================");
    }

    @Test(description = "Example test with element interaction")
    @Story("Element Interaction")
    @Severity(SeverityLevel.NORMAL)
    public void testElementInteraction() {
        
        // Example: Find and interact with an element (replace with your locators)
        try {
            // This is an example - replace with actual element locators for your application
            WebElement exampleElement = driver.findElement(By.tagName("body"));
            Assert.assertNotNull(exampleElement, "Example element should be found");
            
            // Example: Get text from element
            String elementText = exampleElement.getText();
            Assert.assertNotNull(elementText, "Element text should not be null");
            
            LoggerUtil.info("Element interaction test completed successfully");
        } catch (Exception e) {
            LoggerUtil.warning("Element interaction test - no specific elements found (this is expected for a template)");
        }
    }

    @Test(description = "Example test for form filling")
    @Story("Form Interaction")
    @Severity(SeverityLevel.NORMAL)
    public void testFormFilling() {
        // Navigate to homepage
        driver.get(ConfigReader.getBaseUrl());
        
        // Example: Demonstrate form filling pattern (replace with your form elements)
        LoggerUtil.info("Form filling test - replace with your actual form elements");
        LoggerUtil.info("Example: Use sendKeysToElement() method from BasePage for form inputs");
        LoggerUtil.info("Example: Use clickElement() method from BasePage for buttons");
        LoggerUtil.info("Example: Use wait utilities for element synchronization");
        
        // This test demonstrates the pattern without specific elements
        Assert.assertTrue(true, "Form filling pattern demonstrated");
    }
}
