package com.demoqa.pages.elements;

import com.demoqa.pages.BasePage;
import com.demoqa.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class WebTablesPage extends BasePage {

    private final HomePage homePage;


    @FindBy(id = "item-3")
    private WebElement webTablesSectionLink;
    @FindBy(css = "#addNewRecordButton")
    private WebElement addButton;
    @FindBy(id = "firstName")
    private WebElement registrationFormFirstName;

    @FindBy(id = "lastName")
    private WebElement registrationFormLastName;
    @FindBy(id = "userEmail")
    private  WebElement registrationFormEmail;
    @FindBy(id = "age")
    private WebElement registrationFormAge;

    @FindBy(id = "salary")
    private WebElement registrationFormSalary;
    @FindBy(id = "department")
    private WebElement registrationFormDepartment;
    @FindBy(id = "submit")
    private WebElement registrationFormSubmitBtn;
    @FindBy(css = "#searchBox")
    private WebElement searchBoxInputField;
    @FindBy(className = "rt-tbody")
    private WebElement table;
    @FindBy(xpath = "//div[@class='rt-tr-group']")
    private List<WebElement> rows;
    @FindBy(xpath = "//*[@class='rt-th rt-resizable-header -cursor-pointer']")
    private List<WebElement> columns;
    @FindBy(xpath = "//div[@class='rt-td']")
    private List<WebElement> tableCells;
    @FindBy(xpath = "//select[@aria-label='rows per page']")
    private WebElement rowsPerPageDropDown;

    public WebTablesPage(WebDriver driver, WebDriverWait wait, HomePage homePage) {
        super(driver, wait);
        this.homePage = homePage; // Initialization of the HomePage reference
    }

    public void navigateToWebTablesSubCategory() {
        homePage.clickElementsNavigationBar(); // Using the HomePage to click on navigation bar
        clickOnWebTablesSubCategory();  // Clicking on the "Web Tables" subcategory
        Assert.assertTrue(isOnWebTablesSubCategory(), "Text Box Subcategory is not displayed.");
    }


    public boolean isOnWebTablesSubCategory() {
        return isCurrentPageUrlEqualTo("https://demoqa.com/webtables");
    }

    public void clickOnWebTablesSubCategory() {
        clickElement(webTablesSectionLink);
    }

    public int getTotalRowsInTable() {
        return rows.size();
    }

    public int getTotalColumnsInTable() {
        return columns.size();
    }

    public void selectAndVerifyRowCountForAllRowsPerPageOptions() {
        Select dropdown = new Select(rowsPerPageDropDown);

        List<String> expectedOptions = List.of("5 rows", "10 rows", "20 rows", "25 rows", "50 rows", "100 rows");

        for (String optionText : expectedOptions) {
            // Select the option by visible text
            dropdown.selectByVisibleText(optionText);

            // Wait for the table to load (you might need to customize this condition)
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".rt-tr-group")));

            // Get the number of rows displayed
            int rowsDisplayed = driver.findElements(By.cssSelector(".rt-tr-group")).size();

            // Extract the number from the optionText
            int expectedRows = Integer.parseInt(optionText.split(" ")[0]);

            // Assert that the number of rows matches the selected option
            Assert.assertEquals(rowsDisplayed, expectedRows, "Mismatch in the number of rows displayed");
        }
    }


    public void retrieveAndPrintTableElements() {
        for (WebElement row : rows) {

            // Find all columns in the current row
            List<WebElement> rowColumns = row.findElements(By.xpath(".//div[@class='rt-td']"));

            for (WebElement column : rowColumns) {
                // Get the text of the column and print it
                String columnText = column.getText();
                System.out.print(columnText + "\t");
            }
            System.out.println(); // Move to the next row
        }
    }

    public void clickOnAddButton() {
        clickElement(addButton);
    }

    public void enterFirstNameOnRegistrationForm(String text) {
        sendKeysToElement(registrationFormFirstName, text);
    }

    public void enterLastNameOnRegistrationForm(String text) {
        sendKeysToElement(registrationFormLastName, text);
    }

    public void enterEmailOnRegistrationForm(String text) {
        sendKeysToElement(registrationFormEmail, text);
    }

    public void enterAgeOnRegistrationForm(String text) {
        sendKeysToElement(registrationFormAge, text);
    }

    public void enterSalaryOnRegistrationForm(String text) {
        sendKeysToElement(registrationFormSalary, text);
    }

    public void enterDepartmentOnRegistrationForm(String text) {
        sendKeysToElement(registrationFormDepartment, text);
    }

    public void enterUserDataInRegistrationForm(String firstName, String lastName, String email, String age, String salary, String department) {
        enterFirstNameOnRegistrationForm(firstName);
        enterLastNameOnRegistrationForm(lastName);
        enterEmailOnRegistrationForm(email);
        enterAgeOnRegistrationForm(age);
        enterSalaryOnRegistrationForm(salary);
        enterDepartmentOnRegistrationForm(department);
    }

    public void clickOnRegistrationFormSubmitButton() {
        clickElement(registrationFormSubmitBtn);
    }


    public String verifyNewDataArePresentInTable() {
        for (WebElement row : rows) {

            // Find all cells in All rows
            List<WebElement> rowCells = row.findElements(By.cssSelector(".rt-td"));

            // Iterate through columns to find "Zoran" in the first name column
            for (int i = 0; i < rowCells.size(); i++) {
                if (i == 0 && rowCells.get(i).getText().equals("Zoran")) {
                    // Found "Zoran" in the first name column, now extract all cell text in this row
                    StringBuilder rowText = new StringBuilder();
                    for (WebElement cell : rowCells) {
                        rowText.append(cell.getText()).append(" ");
                    }
                    return rowText.toString().trim(); // Trim extra spaces
                }
            }
        }
        return null; // Return null if no "Zoran" was found in any row
    }


}
