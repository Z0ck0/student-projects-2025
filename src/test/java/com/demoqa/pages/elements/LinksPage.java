package com.demoqa.pages.elements;

import com.demoqa.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinksPage extends BasePage {

    @FindBy(id = "item-5")
    private WebElement linksCategory;

    @FindBy(xpath = "//strong[normalize-space()='Following links will open new tab']")
    private WebElement openNewTabTitle;
        //region New Tab Links Locators
        @FindBy(id="simpleLink")
        private WebElement homeSimpleLink;
        @FindBy(id="dynamicLink")
        private WebElement homeDynamicLink;
    //endregion

    @FindBy(xpath = "//strong[normalize-space()='Following links will send an api call']")
    private WebElement sendApiCallTitle;

        //region Sen API Calls Links Locators
        @FindBy(id="created")
        private WebElement createdLink;
        @FindBy(id="no-content")
        private WebElement noContentLink;
        @FindBy(id="moved")
        private WebElement movedLink;
        @FindBy(id="bad-request")
        private WebElement badRequestLink;
        @FindBy(id="unauthorized")
        private WebElement unauthorizedLink;
        @FindBy(id="forbidden")
        private WebElement forbiddenLink;
        @FindBy(id="invalid-url")
        private WebElement notFoundLink;
        //endregion



    public LinksPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickOnLinksSubCategory(){
        scrollToElementIntoView(linksCategory);
        clickElement(linksCategory);

    }
    public boolean isOnLinksPage(){
        return isCurrentPageUrlEqualTo("https://demoqa.com/links");
    }

    public void clickOnHomeLink(){
        clickElement(homeSimpleLink);
    }


}