package com.demoqa.tests.elementsTests;

import com.demoqa.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class RadioButtonTest extends BaseTest {
    @Test (priority = 1)
    public void testSuccessMessageForYesSelection(){
        homePage.clickElementsNavigationBar();
        radioButtonPage.clickOnTextRadioButtonCategory();
        radioButtonPage.clickOnYesRadioButton();
        Assert.assertTrue(radioButtonPage.isOnCheckBoxSubCategory(), "Text Box Subcategory is not displayed.");
        Assert.assertEquals(radioButtonPage.getSuccessMessageForYesSelection(), "Yes");
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
