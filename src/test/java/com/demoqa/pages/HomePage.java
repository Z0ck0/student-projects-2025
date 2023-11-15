package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    @FindBy(xpath = "//*[@class=\"card mt-4 top-card\"][1]")
    WebElement elementsSection;
    @FindBy(xpath = "//*[@class=\"card mt-4 top-card\"][2]")
    WebElement formsSection;
    @FindBy(xpath = "//*[@class=\"card mt-4 top-card\"][3]")
    WebElement alertsFrameWindowsSection;
    @FindBy(xpath = "//*[@class=\"card mt-4 top-card\"][4]")
    WebElement widgetsSection;
    @FindBy(xpath = "//*[@class=\"card mt-4 top-card\"][5]")
    WebElement interactionsSection;
    @FindBy(xpath = "//*[@class=\"card mt-4 top-card\"][6]")
    WebElement bookStoreSection;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    public void clickElementsNavigationBar() {
        clickElement(elementsSection);
    }

    public void clickFormsSection() {
        clickElement(formsSection);
    }

    public void clickAlertsFrameWindowsSection() {
        clickElement(alertsFrameWindowsSection);
    }

    public void clickWidgetsSection() {
        clickElement(widgetsSection);
    }

    public void clickInteractionsSection() {
        clickElement(interactionsSection);
    }

    public void clickBookStoreSection() {
        clickElement(bookStoreSection);
    }
}
