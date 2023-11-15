package com.demoqa.pages;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/* The BasePage class is made abstract to emphasize its role as a template for deriving other page classes, promoting inheritance.
This approach enhances code reusability and encourages the creation of specialized page classes through extension,
eliminating the necessity of declaring and initializing a BasePage within the BaseTest class.

By defining these methods at a higher level, we promote code reusability, as testers using your BasePage don't need to worry about the inner workings of these actions.
They can focus on what needs to be done rather than how to do it, which is a key aspect of abstraction in software development.

This abstraction simplifies the complexity of modeling different entities while fostering code reusability and ensuring consistent behavior.

'public' Access Modifier: The public keyword indicates that this class is accessible from anywhere, including other packages.
It can be used and extended by classes in different packages.*/
public abstract class BasePage {

    // region 0.    Declared Class member variables for interacting with the web page (initialized in the constructor):
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    //endregion

    // region 0.    Constructor/s
    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }
    // endregion

    // Create a helper method for waiting until an element is visible
    protected void waitUntilElementIsVisible(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
//
//    protected void select(WebElement dropdown){
//        Select select = new Select(dropdown);
//    }
//
//
//    public void selectOptionByValue1(WebElement dropdown, String value) {
//          selectOptionByValue(dropdown, value);
//    }


    // region 1.    Basic Browser Operations (get/navigateURL, getTitle/Url)

    // Opens a URL in the browser. This is a blocking call, meaning that the script waits for the page to load
    // completely before proceeding to the next step. It replaces the current page with the provided URL.
    public void getUrl(String url) {
        driver.get(url);
    }
    // -----------------------------------------------------------------------------------------------------------------

    // Navigates to a page with the provided URL. This is not a blocking call, so it does not wait for the page to load
    // completely. It can be used for various navigation actions like going forward, backward, or refreshing the page.
    public void navigateToPage(String url) {
        driver.navigate().to(url);
    }

    // @formatter:off
            // Refreshes the current page. This method navigates back to the same page, effectively reloading it.
            public void refreshPage() {
                driver.navigate().refresh();
            }

            // Navigates back to the previous page in the browser's history.

            public void navigateBack() {
                driver.navigate().back();
            }

            // Navigates forward to the next page in the browser's history.
            public void navigateForward() {
                driver.navigate().forward();
            }
            // @formatter:on
    // -----------------------------------------------------------------------------------------------------------------

    // Retrieves and returns the title of the current web page.
    public void getTitle() {
        driver.getTitle();
    }
    // -----------------------------------------------------------------------------------------------------------------

    // Retrieves and returns the current URL of the web page.
    public void getCurrentUrl() {
        driver.getCurrentUrl();
    }
    // endregion


    // region 2.    Basic Elements Operations (click, submit, clear, sendKeys, getText)

    // This method simulates a mouse click on a web element, typically buttons, links, checkboxes, radio buttons, and some custom clickable elements.
    public void clickElement(WebElement webElement) {
        waitUntilElementIsVisible(webElement);
        webElement.click();
    }
    // -----------------------------------------------------------------------------------------------------------------

    // This method simulates the submission of a form to the server. It is typically used with HTML <form> or an <input> element within a form.
    public void submitForm(WebElement webElement) {
        waitUntilElementIsVisible(webElement);
        webElement.submit();
    }
    // -----------------------------------------------------------------------------------------------------------------

    // Clear an input element.
    public void clearElement(WebElement webElement) {
        waitUntilElementIsVisible(webElement);
        webElement.clear();
    }
    // -----------------------------------------------------------------------------------------------------------------

    // Send keys to an input element.
    public void sendKeysToElement(WebElement webElement, String text) {
        waitUntilElementIsVisible(webElement);
        webElement.sendKeys(text);
    }
    // -----------------------------------------------------------------------------------------------------------------

    // Get text from a web element.
    public String getTextFromElement(WebElement webElement) {
        waitUntilElementIsVisible(webElement);
        return webElement.getText();
    }

    // endregion


    // region 3.    Actions (doubleClick, rightClick, mouseHover, clickAndHold,dragAndDrop)

    // Simulates a double-click action on a WebElement.
    public void doubleClick(WebElement webElement) {
        waitUntilElementIsVisible(webElement);
        actions.doubleClick(webElement).perform(); // Double-click the element
    }
    // -----------------------------------------------------------------------------------------------------------------

    // Simulates a right-click (context click) action on a WebElement.
    public void rightClick(WebElement webElement) {
        waitUntilElementIsVisible(webElement);
        actions.contextClick(webElement).perform();  // Right-clicks the element (context menu)
    }
    // -----------------------------------------------------------------------------------------------------------------

    // Simulates a mouse hover action over a WebElement.
    public void mouseHover(WebElement webElement) {
        waitUntilElementIsVisible(webElement);
        actions.moveToElement(webElement).perform(); // Hovers the mouse over the element
    }
    // -----------------------------------------------------------------------------------------------------------------

    // Simulates a click-and-hold action on a WebElement (used for dragging).
    public void clickAndHold(WebElement webElement) {
        waitUntilElementIsVisible(webElement);
        actions.clickAndHold(webElement).perform();
    }
    // -----------------------------------------------------------------------------------------------------------------

    // Simulates a drag-and-drop action from a source element to a target element.
    public void dragAndDrop(WebElement sourceElement, WebElement targetElement) {
        WebElement source = wait.until(ExpectedConditions.visibilityOf(sourceElement));
        WebElement target = wait.until(ExpectedConditions.visibilityOf(targetElement));
        actions.dragAndDrop(source, target).perform();
    }
    // -----------------------------------------------------------------------------------------------------------------

    // Simulates keypress actions on a WebElement.
    public void keyPress(WebElement webElement, Keys key, String text) {
        waitUntilElementIsVisible(webElement);
        actions.keyDown(key).sendKeys(text).keyUp(key).perform();
        // Holds down the Control key, types 'a', and releases the Control key (select all)
    }
    // endregion


    // region 4.    Boolean Handling: (isEnabled, isDisplayed, isSelected, isClickable, isChecked, isTextPresentInElement)

    // Checks if the provided WebElement is enabled. It examines HTML attributes (e.g., "disabled" and "readonly") and CSS properties to determine element enablement.
    public boolean isElementEnabled(@NotNull WebElement webElement) {
        waitUntilElementIsVisible(webElement);
        return webElement.isEnabled();
    }
    // -----------------------------------------------------------------------------------------------------------------

    // Checks if the provided WebElement is displayed or visible on the web page.
    public boolean isElementDisplayed(@NotNull WebElement webElement) {
        waitUntilElementIsVisible(webElement);
        return webElement.isDisplayed();
    }
    // -----------------------------------------------------------------------------------------------------------------

    // Check if the provided WebElement, typically a checkbox or radio button, is selected.
    public boolean isElementChecked(@NotNull WebElement webElement) {
        waitUntilElementIsVisible(webElement);
        return webElement.isSelected();
    }
    // -----------------------------------------------------------------------------------------------------------------

    // Checks if the provided WebElement becomes clickable within the specified timeout.
    public boolean isElementClickable(@NotNull WebElement webElement) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            return true; // The element is clickable
        } catch (org.openqa.selenium.TimeoutException e) {
            return false; // The element is not clickable
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    // Checks if the current web page's URL matches the expected URL.
    public boolean isOnExpectedPage(String expectedUrl) {
        try {
            // Get the actual URL
            wait.until(ExpectedConditions.urlToBe(expectedUrl));
            String actualUrl = driver.getCurrentUrl();

            // Compare the actual URL with the expected URL
            boolean isOnExpectedPage = actualUrl.equals(expectedUrl);

            if (!isOnExpectedPage) {
                // Log the expected and actual URLs if the test fails
                System.out.println("Test failed. Expected URL: " + expectedUrl);
                System.out.println("Actual URL: " + actualUrl);
            }

            return isOnExpectedPage;
        } catch (TimeoutException e) {
            // Handle the exception if the URL doesn't match the expected URL within the specified timeout
            System.out.println("Test failed. URL did not match the expected URL: " + expectedUrl);
            return false;
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    // Checks if the provided text is present within the given WebElement's text content.
    public boolean isTextPresentInElement(WebElement webElement, String text) {
        waitUntilElementIsVisible(webElement);
        String elementText = webElement.getText();
        return elementText.contains(text);
    }
    // -----------------------------------------------------------------------------------------------------------------

    // Checks if a specific option is present within a dropdown element.
    public boolean isOptionPresentInDropdown(WebElement dropdownElement, String optionText) {
        // Wait for the dropdown element to be visible
        WebElement selectFromDropDown = wait.until(ExpectedConditions.visibilityOf(dropdownElement));

        // Create a Select object
        Select select = new Select(selectFromDropDown);

        // Initialize a flag to track if the option is present
        boolean isOptionPresent = false;

        // Loop through the options and check if "North Macedonia" is listed
        for (WebElement option : select.getOptions()) {
            if (option.getText().equals(optionText)) {
                isOptionPresent = true;
                break;
            }
        }
        // Return the result
        return isOptionPresent;
    }
    // -----------------------------------------------------------------------------------------------------------------

    // - areElementsPresent(By selector): Check if a list of elements matching a selector is present.
    // - areElementsVisible(List<WebElement> elements): Check if a list of elements is visible.
    // - isAlertPresent(): Check if an alert dialog is present.

    // endregion


    // region 5.    Dropdown Handling (selectByIndex/ByVisibleText/ByValue, deselectAllI/ByIndex/ByVisibleText/ByValue)
    public void selectOptionByVisibleText(WebElement dropdown, String text) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
    }

    // -----------------------------------------------------------------------------------------------------------------
    public void selectOptionByValue(WebElement dropdown, String value) {
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    // -----------------------------------------------------------------------------------------------------------------
    public void selectOptionByIndex(WebElement dropdown, int index) {
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }
    // -----------------------------------------------------------------------------------------------------------------

    // - deselectAllOptionsInDropdown(Select select): Deselect all selected options in the dropdown.
    // - deselectOptionInDropdownByIndex(Select select, int index): Deselect an option by index (1).
    // - deselectOptionInDropdownByVisibleText(Select select, String visibleText): Deselect an option by visible text ("Ford").
    // - deselectOptionInDropdownByValue(Select select, String value): Deselect an option by value ("ford").

    // endregion


    // region 6.    Mouse Hover Actions:


    //endregion


    // region 7.    Scroll Handling:
    public void scrollToElementIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }


    //endregion


    // region 8.    Frame Handling:


    //endregion


    // region 9.    Alert Handling:


    //endregion


    // region 10.    Browser Tab and Window Management:


    //endregion

}

