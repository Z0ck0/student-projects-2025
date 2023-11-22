package com.demoqa.tests.elements;

import com.demoqa.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ButtonsTest extends BaseTest {
    @Test (priority = 1)
    public void verifyDoubleClickButtonFunctionality(){
        homePage.clickElementsNavigationBar();
        buttonsPage.clickOnButtonsSubCategory();

        Assert.assertTrue(buttonsPage.isOnButtonsPage(), "Buttons Subcategory is not displayed.");

        // Test to verify the functionality of the double-click button
        Assert.assertTrue(buttonsPage.isDoubleClickButtonDisplayed());
        buttonsPage.performDoubleClickOnDoubleClickButton();
        Assert.assertEquals(buttonsPage.getDoubleClickSuccessMsg(), "You have done a double click");
    }

    @Test (priority = 2)
    public void verifyRightClickButtonFunctionality(){
        homePage.clickElementsNavigationBar();
        buttonsPage.clickOnButtonsSubCategory();
        Assert.assertTrue(buttonsPage.isOnButtonsPage(), "Buttons Subcategory is not displayed.");

        // Test to verify the functionality of the right-click button
        Assert.assertTrue(buttonsPage.isRightClickButtonDisplayed());
        buttonsPage.performRightClickOnRightClickButton();
        Assert.assertEquals(buttonsPage.getRightClickSuccessMsg(), "You have done a right click");
    }

    @Test (priority = 3)
    public void verifyDynamicClickButtonFunctionality(){
        homePage.clickElementsNavigationBar();
        buttonsPage.clickOnButtonsSubCategory();
        Assert.assertTrue(buttonsPage.isOnButtonsPage(), "Buttons Subcategory is not displayed.");

        // Test to verify the functionality of the dynamic button
        Assert.assertTrue(buttonsPage.isDynamicClickButtonDisplayed());
        buttonsPage.clickOnDynamicClickButton();
        Assert.assertEquals(buttonsPage.getDynamicClickSuccessMsg(), "You have done a dynamic click");
    }
}
