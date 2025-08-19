package com.testautomation.pages;

import com.testautomation.core.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExamplePage extends BasePage {
    
    // Page element locators using By for better control
    private final By bodyElement = By.tagName("body");
    private final By headingElement = By.tagName("h1");
    
    public ExamplePage(WebDriver driver) {
        super(driver);
    }
    
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    public String getPageUrl() {
        return driver.getCurrentUrl();
    }
    
    public String getBodyText() {
        return getElementText(bodyElement);
    }
    
    public String getHeadingText() {
        try {
            return getElementText(headingElement);
        } catch (Exception e) {
            return "";
        }
    }
    
    public boolean isPageLoaded() {
        try {
            waitForPageToLoad();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
