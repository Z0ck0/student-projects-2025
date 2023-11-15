package com.demoqa.pages.elementsPage;

import com.demoqa.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TextBoxPage extends BasePage {
    @FindBy(id = "item-0")
    private WebElement textBoxSectionLink;
    @FindBy(id = "userName")
    private WebElement fullNameInputField;
    @FindBy(id = "userEmail")
    private WebElement emailInputField;
    @FindBy(id = "currentAddress")
    private WebElement currentAddressInputField;
    @FindBy(id = "permanentAddress")
    private WebElement permanentAddressInputField;
    @FindBy(id = "submit")
    private WebElement submitButton;
    @FindBy(id = "output")
    private WebElement outputBlock;
    @FindBy(id = "name")
    private WebElement nameOutput;
    @FindBy(id = "email")
    private WebElement emailOutput;
    @FindBy(xpath = "//*[contains(text(), \"Current Address :\")]")
    private WebElement currentAddressOutput;
    @FindBy(xpath = "//*[contains(text(), 'Permananet Address :')]")
    private WebElement permanentAddressOutput;


    public TextBoxPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickOnTextBoxSubCategory() {
        clickElement(textBoxSectionLink);
    }

    public boolean isOnTextBoxSubCategory(){
        return isOnExpectedPage("https://demoqa.com/text-box");
    }

    public void enterFullName(String text) {
        sendKeysToElement(fullNameInputField, text);
    }

    public void enterEmail(String text) {
        sendKeysToElement(emailInputField, text);
    }

    public void enterCurrentAddress(String text) {
        sendKeysToElement(currentAddressInputField, text);
    }

    public void enterPermanentAddress(String text) {
        sendKeysToElement(permanentAddressInputField, text);
    }

    public void clickSubmitButton() {
        scrollToElementIntoView(submitButton);
        clickElement(submitButton);
    }

    public boolean isOutputMessageDisplayed() {
        return isElementDisplayed(outputBlock);
    }

    public boolean isEmailNotDisplayedInOutputBlock() {
        return wait.until(ExpectedConditions.invisibilityOf(emailOutput));
    }

    public void scrollToOutputBlockDisplayed() {
        scrollToElementIntoView(outputBlock);
    }

    public String getNameTextFromOutputField() {
        return getTextFromElement(nameOutput);
    }

    public String getEmailTextFromOutputField() {
        return getTextFromElement(emailOutput);
    }

    public String getCurrentAddressOutputField() {
        return getTextFromElement(currentAddressOutput);
    }

    public String getPermanentAddressOutputField() {
        return getTextFromElement(permanentAddressOutput);
    }


    public void clearTextFromFullNameField() {
        clearElement(fullNameInputField);
    }

    public void clearTextFromEmailField() {
        clearElement(emailInputField);
    }

    public void clearTextFromCurrentAddressField() {
        clearElement(currentAddressInputField);
    }

    public String getCurrentPageTitle() {
       return driver.getTitle();
    }

}