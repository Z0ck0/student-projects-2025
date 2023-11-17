package com.demoqa.pages.elements;

import com.demoqa.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CheckBoxPage extends BasePage {

    @FindBy(id = "item-1")
    private WebElement textBoxCategory;
    @FindBy(xpath = "//button[@title='Expand all']")
    private WebElement expandAllButton;
    @FindBy(xpath = "//button[@title='Collapse all']")
    private WebElement collapseAllButton;

    @FindBy(css = "#tree-node > ol > li > span > button > svg")
    private WebElement homeToggle;
    @FindBy(css = "#tree-node > ol > li > ol > li:nth-child(1) > span > button > svg")
    private WebElement desktopToggle;
    @FindBy(css = "#tree-node > ol > li > ol > li:nth-child(2) > span > button > svg")
    private WebElement documentsToggle;
    @FindBy(css = "#tree-node > ol > li > ol > li.rct-node.rct-node-parent.rct-node-expanded > ol > li:nth-child(1) > span > button > svg")
    private WebElement workSpaceToggle;
    @FindBy(css = "#tree-node > ol > li > ol > li.rct-node.rct-node-parent.rct-node-expanded > ol > li:nth-child(2) > span > button > svg")
    private WebElement officeToggle;
    @FindBy(css = "#tree-node > ol > li > ol > li:nth-child(3) > span > button > svg")
    private WebElement downloadsToggle;

    // @formatter:off
    @FindBy(xpath = "//*[contains(text(), \"Home\")]")
    private WebElement homeCheckbox;
        @FindBy(xpath = "//*[contains(text(), 'Desktop')]")
        private WebElement desktopCheckbox;
            @FindBy(xpath = "//*[contains(text(), 'Notes')]")
            private WebElement notesCheckbox;
            @FindBy(xpath = "//*[contains(text(), 'Commands')]")
            private WebElement commandsCheckbox;

        @FindBy(xpath= "//*[@class='rct-title'][contains(text(), 'Documents')]")
        private WebElement documentsCheckbox;
            @FindBy(xpath = "//*[@class='rct-title'][contains(text(), 'WorkSpace')]")
            private WebElement workspaceCheckbox;
                @FindBy(xpath = "//*[@class='rct-title'][contains(text(), 'React')]")
                private WebElement reactCheckbox;
                @FindBy(xpath = "//*[@class='rct-title'][contains(text(), 'Angular')]")
                private WebElement angularCheckbox;
                @FindBy(xpath = "//*[@class='rct-title'][contains(text(), 'Veu')]")
                private WebElement veuCheckbox;

            @FindBy(xpath = "//*[@class='rct-title'][contains(text(), 'Office')]")
            private WebElement officeCheckbox;
                @FindBy(xpath = "//*[@class='rct-title'][contains(text(), 'Public')]")
                private WebElement publicCheckbox;
                @FindBy(xpath = "//*[@class='rct-title'][contains(text(), 'Private')]")
                private WebElement privateCheckbox;
                @FindBy(xpath = "//*[@class='rct-title'][contains(text(), 'Classified')]")
                private WebElement classifiedCheckbox;
                @FindBy(xpath = "//*[@class='rct-title'][contains(text(), 'General')]")
                private WebElement generalCheckbox;

            @FindBy(xpath = "//*[@class='rct-title'][contains(text(), 'Downloads')]")
            private WebElement downloadsCheckbox;
                @FindBy(xpath = "//*[@class='rct-title'][contains(text(), 'Word File.doc')]")
                private WebElement wordFileCheckbox;
                @FindBy(xpath = "//*[@class='rct-title'][contains(text(), 'Excel File.doc')]")
                private WebElement excelFileCheckbox;
    // @formatter:on

    @FindBy(id = "result")
    WebElement displaySelectedCheckboxes;

    public CheckBoxPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickCheckBoxSubCategory() {
        clickElement(textBoxCategory);
    }

    public boolean isOnCheckBoxSubCategory() {
        return isCurrentPageUrlEqualTo("https://demoqa.com/checkbox");
    }

    public void clickOnExpandAllButton() {
        clickElement(expandAllButton);
    }

    public void clickOnCollapsedAllButton() {
        clickElement(collapseAllButton);
    }

    public String getSelectedCheckboxes(){
       return getTextFromElement(displaySelectedCheckboxes);
    }

    public boolean isCheckedCheckboxesMessageHidden(){
        return wait.until(ExpectedConditions.invisibilityOf(displaySelectedCheckboxes));
    }



    public void clickToggle(String elementName) {
        switch (elementName) {
            case "Home":
                clickElement(homeToggle);
                break;
            case "Desktop":
                clickElement(desktopToggle);
                break;
            case "Documents":
                clickElement(documentsToggle);
                break;
            case "Work Space":
                clickElement(workSpaceToggle);
                break;
            case "Office":
                clickElement(officeToggle);
                break;
            case "Downloads":
                clickElement(downloadsToggle);
                break;
            default:
                // Handle unsupported elementName
                break;
        }
    }

    public void selectCheckbox(String elementName) {
        switch (elementName) {
            case "Home":
                clickElement(homeCheckbox);
                scrollToElementIntoView(homeCheckbox);
                break;
            case "Desktop":
                clickElement(desktopCheckbox);
                scrollToElementIntoView(desktopCheckbox);
                break;
            case "Notes":
                clickElement(notesCheckbox);
                scrollToElementIntoView(notesCheckbox);
                break;
            case "Commands":
                clickElement(commandsCheckbox);
                scrollToElementIntoView(commandsCheckbox);
                break;
            case "Documents":
                clickElement(documentsCheckbox);
                scrollToElementIntoView(documentsCheckbox);
                break;
            case "Work Space":
                clickElement(workspaceCheckbox);
                scrollToElementIntoView(workspaceCheckbox);
                break;
            case "React":
                clickElement(reactCheckbox);
                scrollToElementIntoView(reactCheckbox);
                break;
            case "Angular":
                clickElement(angularCheckbox);
                scrollToElementIntoView(angularCheckbox);
                break;
            case "Veu":
                clickElement(veuCheckbox);
                scrollToElementIntoView(veuCheckbox);
                break;
            case "Office":
                clickElement(officeCheckbox);
                scrollToElementIntoView(officeCheckbox);
                break;
            case "Public":
                clickElement(publicCheckbox);
                scrollToElementIntoView(publicCheckbox);
                break;
            case "Private":
                clickElement(privateCheckbox);
                scrollToElementIntoView(privateCheckbox);
                break;
            case "Classified":
                clickElement(classifiedCheckbox);
                scrollToElementIntoView(classifiedCheckbox);
                break;
            case "General":
                clickElement(generalCheckbox);
                scrollToElementIntoView(generalCheckbox);
                break;
            case "Downloads":
                clickElement(downloadsCheckbox);
                scrollToElementIntoView(downloadsCheckbox);
                break;
            case "Word File.doc":
                clickElement(wordFileCheckbox);
                scrollToElementIntoView(wordFileCheckbox);
                break;
            case "Excel File.doc":
                clickElement(excelFileCheckbox);
                scrollToElementIntoView(excelFileCheckbox);
                break;
            default:
                throw new IllegalArgumentException("Invalid element name: " + elementName);
        }
    }

    public boolean isToggleHidden(String elementName) {
        WebElement toggleElement = getToggleCheckboxElement(elementName);
        return wait.until(ExpectedConditions.invisibilityOf(toggleElement));
    }



    public void assertTogglesHidden(String... toggleNames) {
        for (String toggleName : toggleNames) {
            Assert.assertTrue(isToggleHidden(toggleName),
                    toggleName + " toggle is not hidden.");
        }
    }


    public boolean isToggleDisplayed(WebElement toggleElement) {
        return wait.until(ExpectedConditions.visibilityOf(toggleElement))
                .isDisplayed();
    }

    public void assertTogglesCheckboxesAreDisplayed(String... toggleNames) {
        for (String toggleName : toggleNames) {
            WebElement toggleElement = getToggleCheckboxElement(toggleName);
            Assert.assertTrue(isToggleDisplayed(toggleElement),
                    toggleName + " toggle is not displayed.");
        }
    }

    public WebElement getToggleCheckboxElement(String elementName) {
        switch (elementName) {
            case "Home":
                return (homeCheckbox);
            case "Desktop":
                return (desktopCheckbox);
            case "Notes":
                return (notesCheckbox);
            case "Commands":
                return (commandsCheckbox);
            case "Documents":
                return (documentsCheckbox);
            case "WorkSpace":
                return (workspaceCheckbox);
            case "React":
                return (reactCheckbox);
            case "Angular":
                return (angularCheckbox);
            case "Veu":
                return (veuCheckbox);
            case "Office":
                return (officeCheckbox);
            case "Public":
                return (publicCheckbox);
            case "Private":
                return (privateCheckbox);
            case "Classified":
                return (classifiedCheckbox);
            case "General":
                return (generalCheckbox);
            case "Downloads":
                return (downloadsCheckbox);
            case "Word File.doc":
                return (wordFileCheckbox);
            case "Excel File.doc":
                return (excelFileCheckbox);
            default:
                throw new IllegalArgumentException("Invalid element name: " + elementName);
        }

    }

    public String getCurrentPageTitle(){
        return driver.getTitle();
    }
}

