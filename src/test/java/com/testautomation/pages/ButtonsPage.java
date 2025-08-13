package com.testautomation.pages;

import com.testautomation.core.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;

/**
 * Page Object Model for DemoQA Buttons page.
 * Contains all interactive elements and methods for button interactions.
 */
public class ButtonsPage extends BasePage {

        public ButtonsPage(WebDriver driver) {
        super(driver);
        this.actions = new Actions(driver);
    }
    
    // Actions for complex interactions
    private Actions actions;
    
    // Page title and heading locators
    @FindBy(tagName = "h1")
    private WebElement pageHeading;
    
    @FindBy(tagName = "body")
    private WebElement bodyElement;
    
    // Button locators using By for better control
    private final By doubleClickButton = By.id("doubleClickBtn");
    private final By rightClickButton = By.id("rightClickBtn");
    private final By clickMeButton = By.xpath("//button[text()='Click Me']");
    
    // Result message locators
    private final By doubleClickMessage = By.id("doubleClickMessage");
    private final By rightClickMessage = By.id("rightClickMessage");
    private final By dynamicClickMessage = By.id("dynamicClickMessage");
    
    // Page navigation
    private final By elementsMenu = By.xpath("//div[contains(@class,'header-text') and text()='Elements']");
    private final By buttonsSubmenu = By.xpath("//span[text()='Buttons']");
    

    
    /**
     * Navigate to the Buttons page
     */
    public void navigateToButtonsPage() {
        driver.get("https://demoqa.com/buttons");
        waitForPageToLoad();
    }
    
    /**
     * Get the page heading text
     * @return page heading text
     */
    public String getPageHeading() {
        return getElementText(pageHeading);
    }
    
    /**
     * Get the page title
     * @return page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Get the current page URL
     * @return current page URL
     */
    public String getPageUrl() {
        return driver.getCurrentUrl();
    }
    
    /**
     * Check if the page is loaded
     * @return true if page is loaded, false otherwise
     */
    public boolean isPageLoaded() {
        try {
            waitForPageToLoad();
            return isElementDisplayed(pageHeading);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Perform double click on the double click button
     */
    public void performDoubleClick() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(doubleClickButton));
        actions.doubleClick(element).perform();
    }
    
    /**
     * Perform right click on the right click button
     */
    public void performRightClick() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(rightClickButton));
        actions.contextClick(element).perform();
    }
    
    /**
     * Perform single click on the Click Me button
     */
    public void performSingleClick() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(clickMeButton));
        element.click();
    }
    
    /**
     * Get the double click result message
     * @return double click message text
     */
    public String getDoubleClickMessage() {
        return getElementText(doubleClickMessage);
    }
    
    /**
     * Get the right click result message
     * @return right click message text
     */
    public String getRightClickMessage() {
        return getElementText(rightClickMessage);
    }
    
    /**
     * Get the dynamic click result message
     * @return dynamic click message text
     */
    public String getDynamicClickMessage() {
        return getElementText(dynamicClickMessage);
    }
    
    /**
     * Check if double click button is displayed
     * @return true if button is displayed, false otherwise
     */
    public boolean isDoubleClickButtonDisplayed() {
        return isElementDisplayed(doubleClickButton);
    }
    
    /**
     * Check if right click button is displayed
     * @return true if button is displayed, false otherwise
     */
    public boolean isRightClickButtonDisplayed() {
        return isElementDisplayed(rightClickButton);
    }
    
    /**
     * Check if Click Me button is displayed
     * @return true if button is displayed, false otherwise
     */
    public boolean isClickMeButtonDisplayed() {
        return isElementDisplayed(clickMeButton);
    }
    
    /**
     * Check if double click message is displayed
     * @return true if message is displayed, false otherwise
     */
    public boolean isDoubleClickMessageDisplayed() {
        return isElementDisplayed(doubleClickMessage);
    }
    
    /**
     * Check if right click message is displayed
     * @return true if message is displayed, false otherwise
     */
    public boolean isRightClickMessageDisplayed() {
        return isElementDisplayed(rightClickMessage);
    }
    
    /**
     * Check if dynamic click message is displayed
     * @return true if message is displayed, false otherwise
     */
    public boolean isDynamicClickMessageDisplayed() {
        return isElementDisplayed(dynamicClickMessage);
    }
    
    /**
     * Wait for double click message to be visible
     */
    public void waitForDoubleClickMessage() {
        waitForElementToBeVisible(doubleClickMessage);
    }
    
    /**
     * Wait for right click message to be visible
     */
    public void waitForRightClickMessage() {
        waitForElementToBeVisible(rightClickMessage);
    }
    
    /**
     * Wait for dynamic click message to be visible
     */
    public void waitForDynamicClickMessage() {
        waitForElementToBeVisible(dynamicClickMessage);
    }
    
    /**
     * Perform all button interactions in sequence
     * @return true if all interactions are successful, false otherwise
     */
    public boolean performAllButtonInteractions() {
        try {
            // Perform double click
            performDoubleClick();
            waitForDoubleClickMessage();
            
            // Perform right click
            performRightClick();
            waitForRightClickMessage();
            
            // Perform single click
            performSingleClick();
            waitForDynamicClickMessage();
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Verify all buttons are present on the page
     * @return true if all buttons are displayed, false otherwise
     */
    public boolean verifyAllButtonsPresent() {
        return isDoubleClickButtonDisplayed() && 
               isRightClickButtonDisplayed() && 
               isClickMeButtonDisplayed();
    }
    
    /**
     * Verify all result messages are displayed after interactions
     * @return true if all messages are displayed, false otherwise
     */
    public boolean verifyAllMessagesDisplayed() {
        return isDoubleClickMessageDisplayed() && 
               isRightClickMessageDisplayed() && 
               isDynamicClickMessageDisplayed();
    }
    
    /**
     * Get the page body text for verification
     * @return page body text
     */
    public String getPageBodyText() {
        return getElementText(bodyElement);
    }
}
