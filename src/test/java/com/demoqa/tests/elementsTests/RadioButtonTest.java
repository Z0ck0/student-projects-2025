package com.demoqa.tests.elementsTests;

import com.demoqa.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.demoqa.utilities.DateTimeManagement;
import java.util.Date;


public class RadioButtonTest extends BaseTest {
    @Test (priority = 1)
    public void testSuccessMessageForYesSelection(){
        homePage.clickElementsNavigationBar();
        radioButtonPage.clickOnTextRadioButtonCategory();
        radioButtonPage.clickOnYesRadioButton();
        Assert.assertTrue(radioButtonPage.isOnCheckBoxSubCategory(), "Text Box Subcategory is not displayed.");

        // Call the formatTimestamp method and print the result
        System.out.println("Timestamp of Test Execution: " + DateTimeManagement.formatTimestamp(new Date()));
    }

    @Test (priority = 2)
    public void testSuccessMessageForImpressiveSelection(){
        homePage.clickElementsNavigationBar();
        radioButtonPage.clickOnTextRadioButtonCategory();
        radioButtonPage.clickOnImpressiveRadioButton();
        Assert.assertTrue(radioButtonPage.isOnCheckBoxSubCategory(), "Text Box Subcategory is not displayed.");
        Assert.assertEquals(radioButtonPage.getSuccessMessageForImpressiveSelection(), "Impressive");

    }

    @Test (priority = 3)
    public void testNoRadioButtonIsDisabled(){
        homePage.clickElementsNavigationBar();
        radioButtonPage.clickOnTextRadioButtonCategory();
        Assert.assertTrue(radioButtonPage.isOnCheckBoxSubCategory(), "Text Box Subcategory is not displayed.");
        Assert.assertFalse(radioButtonPage.isRadioButtonNoEnabled(), "Radio button is not enabled as expected");
    }

}
