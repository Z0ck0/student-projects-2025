package com.demoqa.tests.elements;

import com.demoqa.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.demoqa.utilities.DateTimeUtils;
import java.util.Date;


public class RadioButtonTest extends BaseTest {
    @Test (priority = 1)
    public void testSuccessMessageDisplayedForYesRadioBtnSelection(){
        // Test the success message for selecting the "Yes" radio button
        homePage.clickElementsNavigationBar();
        radioButtonPage.clickOnRadioButtonSubCategory();
        radioButtonPage.clickOnYesRadioButton();
        Assert.assertTrue(radioButtonPage.isOnCheckBoxSubCategory(), "Text Box Subcategory is not displayed.");
        Assert.assertEquals(radioButtonPage.getSuccessMessageForYesSelection(), "Yes");

        // Call the formatTimestamp method and print the result
        System.out.println("Timestamp of Test Execution: " + DateTimeUtils.formatTimestamp(new Date()));
    }

    @Test (priority = 2)
    public void testSuccessMessageDisplayedForImpressiveBtnSelection(){
        // Test the success message for selecting the "Impressive" radio button
        homePage.clickElementsNavigationBar();
        radioButtonPage.clickOnRadioButtonSubCategory();
        radioButtonPage.clickOnImpressiveRadioButton();
        Assert.assertTrue(radioButtonPage.isOnCheckBoxSubCategory(), "Text Box Subcategory is not displayed.");
        Assert.assertEquals(radioButtonPage.getSuccessMessageForImpressiveSelection(), "Impressive");
    }

    @Test (priority = 3)
    public void testNoRadioButtonIsDisabled(){
        // Test that the "No" radio button is disabled
        homePage.clickElementsNavigationBar();
        radioButtonPage.clickOnRadioButtonSubCategory();
        Assert.assertTrue(radioButtonPage.isOnCheckBoxSubCategory(), "Text Box Subcategory is not displayed.");
        Assert.assertFalse(radioButtonPage.isRadioButtonNoEnabled(), "Radio button is not enabled as expected");
    }
}
