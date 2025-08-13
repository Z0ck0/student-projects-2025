package com.testautomation.tests.examples;

import com.testautomation.tests.base.BaseTest;
import com.testautomation.pages.ButtonsPage;
import com.testautomation.utils.common.LoggerUtil;
import com.testautomation.utils.browser.ScreenshotUtils;
import com.testautomation.utils.browser.WaitUtils;
import com.testautomation.core.config.ConfigReader;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


/**
 * Example test class for DemoQA Buttons page.
 * Demonstrates comprehensive button interaction testing including double click,
 * right click, and single click functionality.
 */
@Epic("DemoQA Elements Testing")
@Feature("Buttons Functionality")
@Listeners({com.testautomation.core.listeners.TestListener.class})
public class ExampleButtons extends BaseTest {
    
    private ButtonsPage buttonsPage;
    
    
    @Test(description = "Verify Buttons page loads successfully with framework utilities")
    @Story("Page Navigation")
    @Severity(SeverityLevel.CRITICAL)
    public void testButtonsPageLoads() {
        // LoggerUtil.info("=== Starting Buttons Page Load Test ===");
        // LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
        // LoggerUtil.info("Current Browser: " + ConfigReader.getDefaultBrowser());
        // LoggerUtil.info("Base URL: " + ConfigReader.getBaseUrl());
        // LoggerUtil.info("Test Start Time: " + System.currentTimeMillis());
        
        // Navigate to the Buttons page using PageObjectManager
        pages.navigateToButtonsPage();

        
        // Use WaitUtils for enhanced waiting
        boolean titleContainsExpected = WaitUtils.waitForTitleContains(driver, "DEMOQA", 10);
        Assert.assertTrue(titleContainsExpected, "Page title should contain 'DEMOQA' within 10 seconds");
        
        // Verify page is loaded using PageObjectManager
        Assert.assertTrue(pages.getButtonsPage().isPageLoaded(), "Buttons page should load successfully");
        
        // Verify page title and heading
        String expectedTitle = "DEMOQA";
        String actualTitle = pages.getButtonsPage().getPageTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle), 
            "Page title should contain '" + expectedTitle + "', but was '" + actualTitle + "'");
        
        String expectedHeading = "Buttons";
        String actualHeading = pages.getButtonsPage().getPageHeading();
        Assert.assertEquals(actualHeading, expectedHeading, 
            "Page heading should be '" + expectedHeading + "'");
        
        // Take screenshot for verification
        if (ConfigReader.isScreenshotEnabled()) {
            String screenshotPath = ScreenshotUtils.takeScreenshot(driver, "buttons-page-loaded");
            LoggerUtil.info("Screenshot taken: " + screenshotPath);
        }
        
        LoggerUtil.info("Buttons page loaded successfully with title: " + actualTitle);
        LoggerUtil.info("=== Buttons Page Load Test Completed ===");
    }
    
    @Test(description = "Verify all buttons are present on the page with framework utilities")
    @Story("Button Presence")
    @Severity(SeverityLevel.CRITICAL)
    public void testAllButtonsPresent() {
        LoggerUtil.info("=== Starting Button Presence Test ===");
        LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
        LoggerUtil.info("Current Browser: " + ConfigReader.getDefaultBrowser());
        LoggerUtil.info("Implicit Wait: " + ConfigReader.getImplicitWait() + " seconds");
        LoggerUtil.info("Explicit Wait: " + ConfigReader.getExplicitWait() + " seconds");
        
        // Navigate to the Buttons page
        buttonsPage.navigateToButtonsPage();
        
        // Use WaitUtils for enhanced waiting
        WaitUtils.sleep(1000); // Brief wait for page stabilization
        
        // Verify all buttons are displayed
        Assert.assertTrue(buttonsPage.verifyAllButtonsPresent(), 
            "All buttons should be present on the page");
        
        // Individual button verification with enhanced logging
        boolean doubleClickButtonPresent = buttonsPage.isDoubleClickButtonDisplayed();
        boolean rightClickButtonPresent = buttonsPage.isRightClickButtonDisplayed();
        boolean clickMeButtonPresent = buttonsPage.isClickMeButtonDisplayed();
        
        LoggerUtil.info("Double Click Button Present: " + doubleClickButtonPresent);
        LoggerUtil.info("Right Click Button Present: " + rightClickButtonPresent);
        LoggerUtil.info("Click Me Button Present: " + clickMeButtonPresent);
        
        Assert.assertTrue(doubleClickButtonPresent, "Double Click button should be displayed");
        Assert.assertTrue(rightClickButtonPresent, "Right Click button should be displayed");
        Assert.assertTrue(clickMeButtonPresent, "Click Me button should be displayed");
        
        // Take screenshot for verification
        if (ConfigReader.isScreenshotEnabled()) {
            String screenshotPath = ScreenshotUtils.takeScreenshot(driver, "buttons-present");
            LoggerUtil.info("Screenshot taken: " + screenshotPath);
        }
        
        LoggerUtil.info("All buttons are present on the page");
        LoggerUtil.info("=== Button Presence Test Completed ===");
    }
    
    @Test(description = "Test double click functionality with framework utilities")
    @Story("Double Click Interaction")
    @Severity(SeverityLevel.NORMAL)
    public void testDoubleClickFunctionality() {
        LoggerUtil.info("=== Starting Double Click Test ===");
        LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
        LoggerUtil.info("Current Browser: " + ConfigReader.getDefaultBrowser());
        LoggerUtil.info("Page Load Timeout: " + ConfigReader.getPageLoadTimeout() + " seconds");
        
        // Navigate to the Buttons page
        buttonsPage.navigateToButtonsPage();
        
        // Verify page is loaded
        Assert.assertTrue(buttonsPage.isPageLoaded(), "Page should be loaded");
        
        // Use WaitUtils for enhanced waiting
        WaitUtils.sleep(500); // Brief wait for page stabilization
        
        // Take screenshot before interaction
        if (ConfigReader.isScreenshotEnabled()) {
            String beforeScreenshot = ScreenshotUtils.takeScreenshot(driver, "before-double-click");
            LoggerUtil.info("Before interaction screenshot: " + beforeScreenshot);
        }
        
        // Perform double click
        buttonsPage.performDoubleClick();
        
        // Wait for and verify the result message
        buttonsPage.waitForDoubleClickMessage();
        Assert.assertTrue(buttonsPage.isDoubleClickMessageDisplayed(), 
            "Double click message should be displayed");
        
        String message = buttonsPage.getDoubleClickMessage();
        Assert.assertNotNull(message, "Double click message should not be null");
        Assert.assertFalse(message.isEmpty(), "Double click message should not be empty");
        
        // Take screenshot after interaction
        if (ConfigReader.isScreenshotEnabled()) {
            String afterScreenshot = ScreenshotUtils.takeScreenshot(driver, "after-double-click");
            LoggerUtil.info("After interaction screenshot: " + afterScreenshot);
        }
        
        LoggerUtil.info("Double click functionality working correctly. Message: " + message);
        LoggerUtil.info("=== Double Click Test Completed ===");
    }
    
    @Test(description = "Test right click functionality")
    @Story("Right Click Interaction")
    @Severity(SeverityLevel.NORMAL)
    public void testRightClickFunctionality() {
        LoggerUtil.info("Testing right click functionality");
        
        // Navigate to the Buttons page
        buttonsPage.navigateToButtonsPage();
        
        // Verify page is loaded
        Assert.assertTrue(buttonsPage.isPageLoaded(), "Page should be loaded");
        
        // Perform right click
        buttonsPage.performRightClick();
        
        // Wait for and verify the result message
        buttonsPage.waitForRightClickMessage();
        Assert.assertTrue(buttonsPage.isRightClickMessageDisplayed(), 
            "Right click message should be displayed");
        
        String message = buttonsPage.getRightClickMessage();
        Assert.assertNotNull(message, "Right click message should not be null");
        Assert.assertFalse(message.isEmpty(), "Right click message should not be empty");
        
        LoggerUtil.info("Right click functionality working correctly. Message: " + message);
    }
    
//     @Test(description = "Test single click functionality")
//     @Story("Single Click Interaction")
//     @Severity(SeverityLevel.NORMAL)
//     public void testSingleClickFunctionality() {
//         LoggerUtil.info("Testing single click functionality");
        
//         // Navigate to the Buttons page
//         buttonsPage.navigateToButtonsPage();
        
//         // Verify page is loaded
//         Assert.assertTrue(buttonsPage.isPageLoaded(), "Page should be loaded");
        
//         // Perform single click
//         buttonsPage.performSingleClick();
        
//         // Wait for and verify the result message
//         buttonsPage.waitForDynamicClickMessage();
//         Assert.assertTrue(buttonsPage.isDynamicClickMessageDisplayed(), 
//             "Dynamic click message should be displayed");
        
//         String message = buttonsPage.getDynamicClickMessage();
//         Assert.assertNotNull(message, "Dynamic click message should not be null");
//         Assert.assertFalse(message.isEmpty(), "Dynamic click message should not be empty");
        
//         LoggerUtil.info("Single click functionality working correctly. Message: " + message);
//     }
    
//     @Test(description = "Test all button interactions in sequence with framework utilities")
//     @Story("Complete Button Testing")
//     @Severity(SeverityLevel.CRITICAL)
//     public void testAllButtonInteractions() {
//         LoggerUtil.info("=== Starting Complete Button Interactions Test ===");
//         LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
//         LoggerUtil.info("Current Browser: " + ConfigReader.getDefaultBrowser());
//         LoggerUtil.info("Parallel Execution Enabled: " + ConfigReader.isParallelEnabled());
//         LoggerUtil.info("Parallel Thread Count: " + ConfigReader.getParallelThreadCount());
        
//         // Navigate to the Buttons page
//         buttonsPage.navigateToButtonsPage();
        
//         // Verify page is loaded
//         Assert.assertTrue(buttonsPage.isPageLoaded(), "Page should be loaded");
        
//         // Verify all buttons are present
//         Assert.assertTrue(buttonsPage.verifyAllButtonsPresent(), 
//             "All buttons should be present before testing");
        
//         // Take screenshot before interactions
//         if (ConfigReader.isScreenshotEnabled()) {
//             String beforeScreenshot = ScreenshotUtils.takeScreenshot(driver, "before-all-interactions");
//             LoggerUtil.info("Before interactions screenshot: " + beforeScreenshot);
//         }
        
//         // Perform all button interactions
//         boolean allInteractionsSuccessful = buttonsPage.performAllButtonInteractions();
//         Assert.assertTrue(allInteractionsSuccessful, 
//             "All button interactions should be successful");
        
//         // Verify all result messages are displayed
//         Assert.assertTrue(buttonsPage.verifyAllMessagesDisplayed(), 
//             "All result messages should be displayed after interactions");
        
//         // Verify individual messages
//         String doubleClickMessage = buttonsPage.getDoubleClickMessage();
//         String rightClickMessage = buttonsPage.getRightClickMessage();
//         String dynamicClickMessage = buttonsPage.getDynamicClickMessage();
        
//         Assert.assertNotNull(doubleClickMessage, "Double click message should not be null");
//         Assert.assertNotNull(rightClickMessage, "Right click message should not be null");
//         Assert.assertNotNull(dynamicClickMessage, "Dynamic click message should not be null");
        
//         // Take screenshot after interactions
//         if (ConfigReader.isScreenshotEnabled()) {
//             String afterScreenshot = ScreenshotUtils.takeScreenshot(driver, "after-all-interactions");
//             LoggerUtil.info("After interactions screenshot: " + afterScreenshot);
//         }
        
//         LoggerUtil.info("All button interactions completed successfully");
//         LoggerUtil.info("Double Click Message: " + doubleClickMessage);
//         LoggerUtil.info("Right Click Message: " + rightClickMessage);
//         LoggerUtil.info("Dynamic Click Message: " + dynamicClickMessage);
//         LoggerUtil.info("=== Complete Button Interactions Test Completed ===");
//     }
    
//     @Test(description = "Verify page URL and navigation")
//     @Story("Page Navigation")
//     @Severity(SeverityLevel.MINOR)
//     public void testPageNavigation() {
//         LoggerUtil.info("Testing page navigation and URL verification");
        
//         // Navigate to the Buttons page
//         buttonsPage.navigateToButtonsPage();
        
//         // Verify current URL
//         String currentUrl = buttonsPage.getPageUrl();
//         String expectedUrl = "https://demoqa.com/buttons";
//         Assert.assertEquals(currentUrl, expectedUrl, 
//             "Current URL should match expected URL");
        
//         // Verify page is loaded
//         Assert.assertTrue(buttonsPage.isPageLoaded(), "Page should be loaded after navigation");
        
//         LoggerUtil.info("Page navigation successful. Current URL: " + currentUrl);
//     }
    
//     @Test(description = "Verify page content and structure with framework utilities")
//     @Story("Page Content")
//     @Severity(SeverityLevel.MINOR)
//     public void testPageContent() {
//         LoggerUtil.info("=== Starting Page Content Test ===");
//         LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
//         LoggerUtil.info("Current Browser: " + ConfigReader.getDefaultBrowser());
//         LoggerUtil.info("Screenshot Enabled: " + ConfigReader.isScreenshotEnabled());
//         LoggerUtil.info("Screenshot Directory: " + ConfigReader.getScreenshotDirectory());
        
//         // Navigate to the Buttons page
//         buttonsPage.navigateToButtonsPage();
        
//         // Verify page is loaded
//         Assert.assertTrue(buttonsPage.isPageLoaded(), "Page should be loaded");
        
//         // Get page body text for verification
//         String bodyText = buttonsPage.getPageBodyText();
//         Assert.assertNotNull(bodyText, "Page body text should not be null");
//         Assert.assertFalse(bodyText.isEmpty(), "Page body text should not be empty");
        
//         // Verify body text contains expected content
//         Assert.assertTrue(bodyText.contains("Buttons"), 
//             "Page body should contain 'Buttons' text");
        
//         // Take screenshot for content verification
//         if (ConfigReader.isScreenshotEnabled()) {
//             String screenshotPath = ScreenshotUtils.takeScreenshot(driver, "page-content-verification");
//             LoggerUtil.info("Content verification screenshot: " + screenshotPath);
//         }
        
//         LoggerUtil.info("Page content verification successful. Body text length: " + bodyText.length());
//         LoggerUtil.info("=== Page Content Test Completed ===");
//     }
    
//     @Test(description = "Data-driven button testing with framework utilities")
//     @Story("Data-Driven Testing")
//     @Severity(SeverityLevel.NORMAL)
//     @DataProvider(name = "buttonTestData")
//     public void testButtonsWithDataProvider(String testName, String expectedResult) {
//         LoggerUtil.info("=== Starting Data-Driven Button Test ===");
//         LoggerUtil.info("Test Data: " + testName + " - Expected: " + expectedResult);
//         LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
//         LoggerUtil.info("Current Browser: " + ConfigReader.getDefaultBrowser());
        
//         // Navigate to the Buttons page
//         buttonsPage.navigateToButtonsPage();
        
//         // Verify page is loaded
//         Assert.assertTrue(buttonsPage.isPageLoaded(), "Page should be loaded");
        
//         // Perform different button interactions based on test data
//         switch (testName.toLowerCase()) {
//             case "double click":
//                 buttonsPage.performDoubleClick();
//                 buttonsPage.waitForDoubleClickMessage();
//                 String doubleClickMessage = buttonsPage.getDoubleClickMessage();
//                 Assert.assertNotNull(doubleClickMessage, "Double click message should be displayed");
//                 break;
                
//             case "right click":
//                 buttonsPage.performRightClick();
//                 buttonsPage.waitForRightClickMessage();
//                 String rightClickMessage = buttonsPage.getRightClickMessage();
//                 Assert.assertNotNull(rightClickMessage, "Right click message should be displayed");
//                 break;
                
//             case "single click":
//                 buttonsPage.performSingleClick();
//                 buttonsPage.waitForDynamicClickMessage();
//                 String singleClickMessage = buttonsPage.getDynamicClickMessage();
//                 Assert.assertNotNull(singleClickMessage, "Single click message should be displayed");
//                 break;
                
//             default:
//                 Assert.fail("Unknown test type: " + testName);
//         }
        
//         // Take screenshot for data-driven test
//         if (ConfigReader.isScreenshotEnabled()) {
//             String screenshotPath = ScreenshotUtils.takeScreenshot(driver, "data-driven-" + testName.toLowerCase().replace(" ", "-"));
//             LoggerUtil.info("Data-driven test screenshot: " + screenshotPath);
//         }
        
//         LoggerUtil.info("Data-driven button test completed successfully for: " + testName);
//         LoggerUtil.info("=== Data-Driven Button Test Completed ===");
//     }
    
//     @Test(description = "Random data generation showcase with framework utilities")
//     @Story("Random Data Generation")
//     @Severity(SeverityLevel.MINOR)
//     public void testRandomDataGeneration() {
//         LoggerUtil.info("=== Starting Random Data Generation Test ===");
//         LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
//         LoggerUtil.info("Current Browser: " + ConfigReader.getDefaultBrowser());
        
//         // Generate random test data using framework utilities
//         String randomEmail = RandomDataGenerator.getRandomEmail();
//         String randomFirstName = RandomDataGenerator.getRandomFirstName();
//         String randomLastName = RandomDataGenerator.getRandomLastName();
//         String randomPhone = RandomDataGenerator.getRandomPhoneNumber();
//         String randomAddress = RandomDataGenerator.getRandomAddress();
//         String randomCompany = RandomDataGenerator.getRandomCompany();
//         String randomJobTitle = RandomDataGenerator.getRandomJobTitle();
        
//         LoggerUtil.info("Generated Random Data:");
//         LoggerUtil.info("Email: " + randomEmail);
//         LoggerUtil.info("First Name: " + randomFirstName);
//         LoggerUtil.info("Last Name: " + randomLastName);
//         LoggerUtil.info("Phone: " + randomPhone);
//         LoggerUtil.info("Address: " + randomAddress);
//         LoggerUtil.info("Company: " + randomCompany);
//         LoggerUtil.info("Job Title: " + randomJobTitle);
        
//         // Navigate to the Buttons page
//         buttonsPage.navigateToButtonsPage();
        
//         // Verify page is loaded
//         Assert.assertTrue(buttonsPage.isPageLoaded(), "Page should be loaded");
        
//         // Use random data in test context (demonstration)
//         String testDescription = "Testing with random user: " + randomFirstName + " " + randomLastName;
//         LoggerUtil.info(testDescription);
        
//         // Perform a simple button interaction
//         buttonsPage.performSingleClick();
//         buttonsPage.waitForDynamicClickMessage();
        
//         String message = buttonsPage.getDynamicClickMessage();
//         Assert.assertNotNull(message, "Dynamic click message should be displayed");
        
//         // Take screenshot for random data test
//         if (ConfigReader.isScreenshotEnabled()) {
//             String screenshotPath = ScreenshotUtils.takeScreenshot(driver, "random-data-test");
//             LoggerUtil.info("Random data test screenshot: " + screenshotPath);
//         }
        
//         LoggerUtil.info("Random data generation test completed successfully");
//         LoggerUtil.info("=== Random Data Generation Test Completed ===");
//     }
    
//     @Test(description = "Framework configuration showcase with framework utilities")
//     @Story("Framework Configuration")
//     @Severity(SeverityLevel.MINOR)
//     public void testFrameworkConfiguration() {
//         LoggerUtil.info("=== Starting Framework Configuration Test ===");
//         LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
        
//         // Showcase framework configuration capabilities
//         LoggerUtil.info("Framework Configuration:");
//         LoggerUtil.info("Base URL: " + ConfigReader.getBaseUrl());
//         LoggerUtil.info("Default Browser: " + ConfigReader.getDefaultBrowser());
//         LoggerUtil.info("Implicit Wait: " + ConfigReader.getImplicitWait() + " seconds");
//         LoggerUtil.info("Explicit Wait: " + ConfigReader.getExplicitWait() + " seconds");
//         LoggerUtil.info("Page Load Timeout: " + ConfigReader.getPageLoadTimeout() + " seconds");
//         LoggerUtil.info("Screenshot Enabled: " + ConfigReader.isScreenshotEnabled());
//         LoggerUtil.info("Screenshot Directory: " + ConfigReader.getScreenshotDirectory());
//         LoggerUtil.info("Parallel Execution: " + ConfigReader.isParallelEnabled());
//         LoggerUtil.info("Parallel Thread Count: " + ConfigReader.getParallelThreadCount());
        
//         // Navigate to the Buttons page
//         buttonsPage.navigateToButtonsPage();
        
//         // Verify page is loaded
//         Assert.assertTrue(buttonsPage.isPageLoaded(), "Page should be loaded");
        
//         // Verify configuration is working
//         Assert.assertTrue(ConfigReader.isInitialized(), "Configuration should be initialized");
//         Assert.assertNotNull(ConfigReader.getBaseUrl(), "Base URL should not be null");
//         Assert.assertNotNull(ConfigReader.getDefaultBrowser(), "Default browser should not be null");
        
//         // Take screenshot for configuration test
//         if (ConfigReader.isScreenshotEnabled()) {
//             String screenshotPath = ScreenshotUtils.takeScreenshot(driver, "framework-configuration");
//             LoggerUtil.info("Configuration test screenshot: " + screenshotPath);
//         }
        
//         LoggerUtil.info("Framework configuration test completed successfully");
//         LoggerUtil.info("=== Framework Configuration Test Completed ===");
//     }
    
//     @Test(description = "Browser type handling showcase with framework utilities")
//     @Story("Browser Handling")
//     @Severity(SeverityLevel.MINOR)
//     @Parameters({"browser"})
//     public void testBrowserTypeHandling(String browser) {
//         LoggerUtil.info("=== Starting Browser Type Handling Test ===");
//         LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
//         LoggerUtil.info("Parameter Browser: " + browser);
//         LoggerUtil.info("Current Driver Browser: " + ConfigReader.getDefaultBrowser());
        
//         // Showcase browser type enum usage
//         try {
//             BrowserType browserType = BrowserType.fromString(browser);
//             LoggerUtil.info("Parsed Browser Type: " + browserType.name());
//             LoggerUtil.info("Browser Value: " + browserType.getValue());
//         } catch (IllegalArgumentException e) {
//             LoggerUtil.warning("Invalid browser type provided: " + browser);
//             // Use default browser
//             BrowserType defaultBrowser = BrowserType.CHROME;
//             LoggerUtil.info("Using default browser: " + defaultBrowser.name());
//         }
        
//         // Navigate to the Buttons page
//         buttonsPage.navigateToButtonsPage();
        
//         // Verify page is loaded
//         Assert.assertTrue(buttonsPage.isPageLoaded(), "Page should be loaded");
        
//         // Verify browser-specific behavior
//         String currentUrl = buttonsPage.getPageUrl();
//         Assert.assertTrue(currentUrl.contains("demoqa.com"), "URL should contain demoqa.com");
        
//         // Take screenshot for browser test
//         if (ConfigReader.isScreenshotEnabled()) {
//             String screenshotPath = ScreenshotUtils.takeScreenshot(driver, "browser-handling-" + browser);
//             LoggerUtil.info("Browser handling test screenshot: " + screenshotPath);
//         }
        
//         LoggerUtil.info("Browser type handling test completed successfully");
//         LoggerUtil.info("=== Browser Type Handling Test Completed ===");
//     }
    
//     @Test(description = "Retry mechanism showcase with framework utilities")
//     @Story("Retry Mechanism")
//     @Severity(SeverityLevel.NORMAL)
//     public void testRetryMechanism() {
//         LoggerUtil.info("=== Starting Retry Mechanism Test ===");
//         LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
//         LoggerUtil.info("Current Browser: " + ConfigReader.getDefaultBrowser());
        
//         // Showcase retry analyzer configuration
//         LoggerUtil.info("Retry Configuration:");
//         LoggerUtil.info("Max Retry Count: " + ConfigReader.getProperty("retry.maxCount", "2"));
        
//         // Navigate to the Buttons page
//         buttonsPage.navigateToButtonsPage();
        
//         // Verify page is loaded
//         Assert.assertTrue(buttonsPage.isPageLoaded(), "Page should be loaded");
        
//         // Perform button interactions with potential retry scenarios
//         try {
//             // Use WaitUtils for enhanced waiting
//             WaitUtils.sleep(1000);
            
//             // Perform double click
//             buttonsPage.performDoubleClick();
//             buttonsPage.waitForDoubleClickMessage();
            
//             String message = buttonsPage.getDoubleClickMessage();
//             Assert.assertNotNull(message, "Double click message should be displayed");
            
//             LoggerUtil.info("Button interaction successful on first attempt");
            
//         } catch (Exception e) {
//             LoggerUtil.warning("First attempt failed, this would trigger retry: " + e.getMessage());
//             // In a real scenario, this would trigger the retry mechanism
//         }
        
//         // Take screenshot for retry test
//         if (ConfigReader.isScreenshotEnabled()) {
//             String screenshotPath = ScreenshotUtils.takeScreenshot(driver, "retry-mechanism-test");
//             LoggerUtil.info("Retry mechanism test screenshot: " + screenshotPath);
//         }
        
//         LoggerUtil.info("Retry mechanism test completed successfully");
//         LoggerUtil.info("=== Retry Mechanism Test Completed ===");
//     }
    
//     @Test(description = "Wait utilities showcase with framework utilities")
//     @Story("Wait Utilities")
//     @Severity(SeverityLevel.MINOR)
//     public void testWaitUtilities() {
//         LoggerUtil.info("=== Starting Wait Utilities Test ===");
//         LoggerUtil.info("Test Thread: " + Thread.currentThread().getName());
//         LoggerUtil.info("Current Browser: " + ConfigReader.getDefaultBrowser());
        
//         // Navigate to the Buttons page
//         buttonsPage.navigateToButtonsPage();
        
//         // Showcase various wait utilities
//         LoggerUtil.info("Testing Wait Utilities:");
        
//         // Test title wait
//         boolean titleWaitSuccess = WaitUtils.waitForTitleContains(driver, "DEMOQA", 10);
//         LoggerUtil.info("Title wait success: " + titleWaitSuccess);
//         Assert.assertTrue(titleWaitSuccess, "Title should contain 'DEMOQA' within 10 seconds");
        
//         // Test URL wait
//         boolean urlWaitSuccess = WaitUtils.waitForUrlContains(driver, "buttons", 10);
//         LoggerUtil.info("URL wait success: " + urlWaitSuccess);
//         Assert.assertTrue(urlWaitSuccess, "URL should contain 'buttons' within 10 seconds");
        
//         // Test element wait
//         try {
//             WaitUtils.sleep(1000); // Brief wait for page stabilization
//             LoggerUtil.info("Sleep utility working correctly");
//         } catch (Exception e) {
//             LoggerUtil.error("Sleep utility failed: " + e.getMessage());
//         }
        
//         // Verify page is loaded
//         Assert.assertTrue(buttonsPage.isPageLoaded(), "Page should be loaded");
        
//         // Take screenshot for wait utilities test
//         if (ConfigReader.isScreenshotEnabled()) {
//             String screenshotPath = ScreenshotUtils.takeScreenshot(driver, "wait-utilities-test");
//             LoggerUtil.info("Wait utilities test screenshot: " + screenshotPath);
//         }
        
//         LoggerUtil.info("Wait utilities test completed successfully");
//         LoggerUtil.info("=== Wait Utilities Test Completed ===");
//     }
}
