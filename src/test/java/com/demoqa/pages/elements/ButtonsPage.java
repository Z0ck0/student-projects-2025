package com.demoqa.pages.elements;

import com.demoqa.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ButtonsPage extends BasePage {

    @FindBy(xpath ="//button[@id='doubleClickBtn']")
    private WebElement doubleClickMeBtn;
    @FindBy(xpath ="//button[@id='rightClickBtn']")
    private WebElement rightClickMeBtn;

    //button[normalize-space()='Click Me']
    @FindBy(xpath = "//button[text()='Click Me']")
    private WebElement dynamicClickMeBtn;
    @FindBy(xpath="//li[contains(.,'Buttons')]")
    private WebElement buttonsPageSectionLink;

    @FindBy(xpath= "//*[@id='doubleClickMessage']")
    private WebElement doubleClickMessage;
    @FindBy(xpath="//*[@id='rightClickMessage']")
    private WebElement rightClickMessage;
    @FindBy(id = "dynamicClickMessage")
    private WebElement regularClickMessage;

    public ButtonsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickOnButtonsSubCategory(){
        scrollToElementIntoView(buttonsPageSectionLink);
        clickElement(buttonsPageSectionLink);
    }
    public  boolean isOnButtonsPage(){
        return isCurrentPageUrlEqualTo("https://demoqa.com/buttons");
    }

    public boolean isDoubleClickButtonDisplayed() {
        return isElementDisplayed(doubleClickMeBtn);
    }

    public boolean isRightClickButtonDisplayed() {
        return isElementDisplayed(rightClickMeBtn);
    }

    public boolean isDynamicClickButtonDisplayed() {
        return isElementDisplayed(dynamicClickMeBtn);
    }

    public void performDoubleClickOnDoubleClickButton(){
        scrollToElementIntoView(doubleClickMeBtn);
        doubleClick(doubleClickMeBtn);
    }

    public void performRightClickOnRightClickButton(){
        scrollToElementIntoView(rightClickMeBtn);
        rightClick(rightClickMeBtn);
    }
    public void clickOnDynamicClickButton(){
        scrollToElementIntoView(dynamicClickMeBtn);
        clickElement(dynamicClickMeBtn);
    }

    public String getDoubleClickSuccessMsg(){
        scrollToElementIntoView(doubleClickMessage);
        return getTextFromElement(doubleClickMessage);
    }

    public String getRightClickSuccessMsg(){
        scrollToElementIntoView(rightClickMessage);
        return getTextFromElement(rightClickMessage);
    }

    public String getDynamicClickSuccessMsg(){
        return getTextFromElement(regularClickMessage);
    }
}

