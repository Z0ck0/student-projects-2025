package com.testautomation.pages;

import com.testautomation.core.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExamplePage extends BasePage {
    
    @FindBy(tagName = "body")
    private WebElement bodyElement;
    
    @FindBy(tagName = "h1")
    private WebElement headingElement;
    
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
