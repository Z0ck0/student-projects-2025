package com.demoqa.tests.elementsTests;

import com.demoqa.tests.BaseTest;
import com.demoqa.utilities.LoggerUtil;
import com.demoqa.utilities.DateTimeUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;


public class WebTablesTest extends BaseTest {

    @Test(groups = "regression")
    public void verifyTotalRowsInWebTable() {
        LoggerUtil.info("Executing verifyTotalRowsInWebTable test...");
        try {
            webTablesPage.navigateToWebTablesSubCategory();
            System.out.println("The total number of rows in the table is: " + webTablesPage.getTotalRowsInTable());
            Assert.assertEquals(webTablesPage.getTotalRowsInTable(), 10);

            // Call the formatTimestamp method and print the result
            System.out.println("Timestamp of Test Execution: " + DateTimeUtils.formatTimestamp(new Date()));
        } catch (Exception e) {
            LoggerUtil.error("An error occurred during the test.", e);
            throw e; // Rethrow the exception to fail the test
        }
    }


    @Test(priority = 1)
    public void verifyTotalColumnsInWebTable() {
        webTablesPage.navigateToWebTablesSubCategory();
        System.out.println("The total number of columns in the table is: " + webTablesPage.getTotalColumnsInTable());
        Assert.assertEquals(webTablesPage.getTotalColumnsInTable(), 7);
    }


    @Test(priority = 3)
    public void testRowsPerPageFunctionality() {
        webTablesPage.navigateToWebTablesSubCategory();
        webTablesPage.selectAndVerifyRowCountForAllRowsPerPageOptions();
    }


    @Test(priority = 5)
    public void testPrintAllDataFromWebTable() {
        webTablesPage.navigateToWebTablesSubCategory();
        webTablesPage.retrieveAndPrintTableElements();
    }


    @Test(priority = 4)
    public void testAddNewUserViaRegistrationForm() {
        try {
            webTablesPage.navigateToWebTablesSubCategory();
            webTablesPage.clickOnAddButton();
            webTablesPage.enterUserDataInRegistrationForm(
                    "Zoran",
                    "Dimitrievski",
                    "zzdimitrievski@gmail.com",
                    "41",
                    "25000",
                    "IT");
            webTablesPage.clickOnRegistrationFormSubmitButton();
        } catch (Exception e) {
            LoggerUtil.error("An error occurred during the test.", e);
            // Handle the exception or rethrow it
        }

        //Verify the new record is added to the table
        Assert.assertEquals(webTablesPage.verifyNewDataArePresentInTable(),
                "Zoran Dimitrievski 41 zzdimitrievski@gmail.com 25000 IT",
                "The new data in the table is not as expected");
    }

}
