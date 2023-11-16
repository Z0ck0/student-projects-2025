package com.demoqa.tests;

import com.demoqa.pages.*;
import com.demoqa.pages.elementsPage.*;
import com.demoqa.utilities.RandomDataGenerator;
import com.demoqa.utilities.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    // region WebDriver, WebDriverWait, and Actions Declaration
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    // endregion

    WebDriverManager webDriverManager;


    // Variables to store generated data for testing.
    public String getRandomEmail;
    public String getRandomPassword;
    public String getRandomFirstName;
    public String getRandomLastName;
    public String getRandomFullName;
    public String getRandomPhoneNumber;
    public String getRandomCellNumber;
    public String getRandomAddress;

    public BaseTest() {
        // Generate random data once for the entire test class.
        getRandomEmail = RandomDataGenerator.getRandomEmail();
        getRandomPassword = RandomDataGenerator.getRandomPassword();
        getRandomFirstName = RandomDataGenerator.getRandomFirstName();
        getRandomLastName = RandomDataGenerator.getRandomLastName();
        getRandomFullName = RandomDataGenerator.getRandomFullName();
        getRandomPhoneNumber = RandomDataGenerator.getRandomPhoneNumber();
        getRandomCellNumber = RandomDataGenerator.getRandomCellNumber();
        getRandomAddress  = RandomDataGenerator.getRandomAddress();
    }



    // region Page Object Declaration
    public HomePage homePage;
    public BrokenLinks_ImagesPage brokenLinksImagesPage;
    public ButtonsPage buttonsPage;
    public CheckBoxPage checkBoxPage;
    public DynamicPropertiesPage dynamicPropertiesPage;
    public LinksPage linksPage;
     public RadioButtonPage radioButtonPage;
    public TextBoxPage textBoxPage;
    public UploadAndDownloadPage uploadAndDownloadPage;
    public WebTablesPage webTablesPage;

    public RandomDataGenerator randomDataGenerator;



    @BeforeMethod
    public void setUp() {
        webDriverManager = new WebDriverManager();
        // region Initiate the WebDriver, WebDriverWait, and Actions Initialization
        String browserName = "chrome";
        driver = webDriverManager.initiateDriver(browserName);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);

        // Initialize Page Objects
        homePage = new HomePage(driver, wait);
        brokenLinksImagesPage = new BrokenLinks_ImagesPage(driver, wait);
        buttonsPage = new ButtonsPage(driver, wait);
        checkBoxPage = new CheckBoxPage(driver, wait);
        dynamicPropertiesPage =new DynamicPropertiesPage(driver, wait);
        linksPage = new LinksPage(driver, wait);
        radioButtonPage = new RadioButtonPage(driver, wait);
        textBoxPage = new TextBoxPage(driver, wait);
        uploadAndDownloadPage = new UploadAndDownloadPage(driver, wait);
        webTablesPage = new WebTablesPage(driver, wait, homePage);


        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
