package com.testautomation.core.pages;

import com.testautomation.pages.ButtonsPage;
import com.testautomation.pages.ExamplePage;
import com.testautomation.core.config.ConfigReader;
import com.testautomation.utils.common.LoggerUtil;
import org.openqa.selenium.WebDriver;

/**
 * Page Object Manager - Centralized management of all page objects.
 * 
 * This class provides lazy initialization of page objects, ensuring they are only
 * created when needed and reused across test methods. This pattern improves
 * performance and maintains clean separation of concerns.
 * 
 * Usage:
 * PageObjectManager pages = new PageObjectManager(driver);
 * pages.getButtonsPage().clickButton();
 * pages.getExamplePage().fillForm();
 */
public class PageObjectManager {
    
    private final WebDriver driver;
    
    // 1. ========== CREATE THE PAGE CLASS ==========
    // Page objects as private fields for lazy initialization
    private ButtonsPage buttonsPage;
    private ExamplePage examplePage;
    
    // Add more page objects here as your framework grows
    // private PracticeFormPage practiceFormPage;
    // private BooksPage booksPage;
    // private AlertsPage alertsPage;
    
    /**
     * Constructor - requires WebDriver instance
     * @param driver The WebDriver instance to be used by all page objects
     */
    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
        LoggerUtil.info("PageObjectManager initialized successfully");
    }
    
    // 2. ========== ADD TO PAGE OBJECT MANAGER ==========
    /**
     * Get ButtonsPage instance with lazy initialization
     * @return ButtonsPage instance
     */
    public ButtonsPage getButtonsPage() {
        if (buttonsPage == null) {
            buttonsPage = new ButtonsPage(driver);
            LoggerUtil.info("ButtonsPage initialized");
        }
        return buttonsPage;
    }
    
    /**
     * Get ExamplePage instance with lazy initialization
     * @return ExamplePage instance
     */
    public ExamplePage getExamplePage() {
        if (examplePage == null) {
            examplePage = new ExamplePage(driver);
            LoggerUtil.info("ExamplePage initialized");
        }
        return examplePage;
    }
    
    // 3. ========== ADD NAVIGATION METHOD ==========
    
    /**
     * Navigate to the Buttons page
     */
    public void navigateToButtonsPage() {
        driver.get(ConfigReader.getBaseUrl() + "buttons");

        LoggerUtil.info("Navigated to Buttons page");
    }
    
    /**
     * Navigate to the Practice Form page
     */
    public void navigateToPracticeFormPage() {
        driver.get(ConfigReader.getBaseUrl() + "automation-practice-form");
        LoggerUtil.info("Navigated to Practice Form page");
    }
    
    /**
     * Navigate to the Text Box page
     */
    public void navigateToTextBoxPage() {
        driver.get(ConfigReader.getBaseUrl() + "text-box");
        LoggerUtil.info("Navigated to Text Box page");
    }
    
    /**
     * Navigate to the Check Box page
     */
    public void navigateToCheckBoxPage() {
        driver.get(ConfigReader.getBaseUrl() + "checkbox");
        LoggerUtil.info("Navigated to Check Box page");
    }
    
    /**
     * Navigate to the Radio Button page
     */
    public void navigateToRadioButtonPage() {
        driver.get(ConfigReader.getBaseUrl() + "radio-button");
        LoggerUtil.info("Navigated to Radio Button page");
    }
    
    /**
     * Navigate to the Web Tables page
     */
    public void navigateToWebTablesPage() {
        driver.get(ConfigReader.getBaseUrl() + "webtables");
        LoggerUtil.info("Navigated to Web Tables page");
    }
    
    /**
     * Navigate to the Links page
     */
    public void navigateToLinksPage() {
        driver.get(ConfigReader.getBaseUrl() + "links");
        LoggerUtil.info("Navigated to Links page");
    }
    
    /**
     * Navigate to the Broken Links page
     */
    public void navigateToBrokenLinksPage() {
        driver.get(ConfigReader.getBaseUrl() + "broken");
        LoggerUtil.info("Navigated to Broken Links page");
    }
    
    /**
     * Navigate to the Upload and Download page
     */
    public void navigateToUploadDownloadPage() {
        driver.get(ConfigReader.getBaseUrl() + "upload-download");
        LoggerUtil.info("Navigated to Upload and Download page");
    }
    
    /**
     * Navigate to the Dynamic Properties page
     */
    public void navigateToDynamicPropertiesPage() {
        driver.get(ConfigReader.getBaseUrl() + "dynamic-properties");
        LoggerUtil.info("Navigated to Dynamic Properties page");
    }
    
    /**
     * Navigate to a custom path
     * @param path The path to append to base URL
     */
    public void navigateToCustomPath(String path) {
        driver.get(ConfigReader.getBaseUrl() + path);
        LoggerUtil.info("Navigated to custom path: " + path);
    }
    
    /**
     * Navigate to a full URL
     * @param url The complete URL to navigate to
     */
    public void navigateToUrl(String url) {
        driver.get(url);
        LoggerUtil.info("Navigated to URL: " + url);
    }
    
    /**
     * Get the underlying WebDriver instance
     * @return WebDriver instance
     */
    public WebDriver getDriver() {
        return driver;
    }
    
    /**
     * Reset all page objects (useful for test isolation)
     */
    public void resetPages() {
        buttonsPage = null;
        examplePage = null;
        LoggerUtil.info("All page objects reset");
    }
    
    /**
     * Get page object count for debugging
     * @return Number of initialized page objects
     */
    public int getInitializedPageCount() {
        int count = 0;
        if (buttonsPage != null) count++;
        if (examplePage != null) count++;
        return count;
    }
}
