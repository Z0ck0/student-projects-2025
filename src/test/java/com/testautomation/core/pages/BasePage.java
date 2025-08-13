package com.testautomation.core.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage - Common functionality for all page objects.
 * 
 * This class provides reusable methods for common web element interactions
 * and should be extended by all specific page objects in the application.
 * 
 * Features:
 * - Common element interaction methods (click, sendKeys, getText)
 * - Wait utilities for element visibility and clickability
 * - Page load verification
 * - Consistent timeout handling
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Constructor - initializes the page with WebDriver and PageFactory
     * @param driver The WebDriver instance for this page
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Click an element using By locator
     * @param locator The By locator for the element
     */
    protected void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    /**
     * Click an element using WebElement
     * @param element The WebElement to click
     */
    protected void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    /**
     * Send keys to an element using By locator
     * @param locator The By locator for the element
     * @param text The text to send
     */
    protected void sendKeysToElement(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Send keys to an element using WebElement
     * @param element The WebElement to send keys to
     * @param text The text to send
     */
    protected void sendKeysToElement(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Get text from an element using By locator
     * @param locator The By locator for the element
     * @return The text content of the element
     */
    protected String getElementText(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }

    /**
     * Get text from an element using WebElement
     * @param element The WebElement to get text from
     * @return The text content of the element
     */
    protected String getElementText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    /**
     * Check if an element is displayed using By locator
     * @param locator The By locator for the element
     * @return true if element is displayed, false otherwise
     */
    protected boolean isElementDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if an element is displayed using WebElement
     * @param element The WebElement to check
     * @return true if element is displayed, false otherwise
     */
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait for an element to be clickable using By locator
     * @param locator The By locator for the element
     */
    protected void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for an element to be visible using By locator
     * @param locator The By locator for the element
     */
    protected void waitForElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for page to load completely
     */
    protected void waitForPageToLoad() {
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
    }

    /**
     * Get the current page title
     * @return The page title
     */
    protected String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get the current page URL
     * @return The page URL
     */
    protected String getPageUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Check if current page URL contains specific text
     * @param text The text to check for in the URL
     * @return true if URL contains the text, false otherwise
     */
    protected boolean isUrlContains(String text) {
        return driver.getCurrentUrl().contains(text);
    }

    /**
     * Check if current page title contains specific text
     * @param text The text to check for in the title
     * @return true if title contains the text, false otherwise
     */
    protected boolean isTitleContains(String text) {
        return driver.getTitle().contains(text);
    }
}
