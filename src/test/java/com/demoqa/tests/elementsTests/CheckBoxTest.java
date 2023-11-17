package com.demoqa.tests.elementsTests;

import com.demoqa.tests.BaseTest;
import com.demoqa.utilities.AssertionUtils;
import com.demoqa.utilities.DateTimeUtils;
import com.demoqa.utilities.RandomDataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

public class CheckBoxTest extends BaseTest {
    @Test(priority = 3)
    public void testExpandAndCollapseAllFunctionality() {
        homePage.clickElementsNavigationBar();
        checkBoxPage.clickCheckBoxSubCategory();
        AssertionUtils.assertTrue(checkBoxPage.isOnCheckBoxSubCategory(),
                "Checkbox Subcategory is not displayed.");
        AssertionUtils.assertEquals(checkBoxPage.getCurrentPageTitle(), "DEMOQA");

        // Ensure that ALL Toggles are initially hidden
        checkBoxPage.assertTogglesHidden("Desktop", "Documents", "WorkSpace", "Office", "Downloads");

        checkBoxPage.clickOnExpandAllButton();
        // Verify that ALL Toggles are now displayed
        checkBoxPage.assertTogglesCheckboxesAreDisplayed("Desktop", "Documents", "WorkSpace", "Office", "Downloads");

        checkBoxPage.clickOnCollapsedAllButton();
        // Verify that ALL Toggles are AGAIN hidden
        checkBoxPage.assertTogglesHidden("Desktop", "Documents", "WorkSpace", "Office", "Downloads");
    }


    @Test(priority = 2)
    public void testCheckAndUncheckAllFunctionality() {
        homePage.clickElementsNavigationBar();
        checkBoxPage.clickCheckBoxSubCategory();
        checkBoxPage.clickOnExpandAllButton();

        // Select the "Home" checkbox
        checkBoxPage.selectCheckbox("Home");

        // Verify that the selected checkboxes match the expected list
        Assert.assertEquals(checkBoxPage.getSelectedCheckboxes().replaceAll("\\s+", " ").trim(),
                "You have selected : home desktop notes commands documents workspace react angular veu office public private classified general downloads wordFile excelFile",
                "The selected checkboxes do not align with the expected selection.");

        // Deselect the "Home" checkbox
        checkBoxPage.selectCheckbox("Home");

        // Ensure that the message for checked checkboxes is hidden
        Assert.assertNotEquals(checkBoxPage.isCheckedCheckboxesMessageHidden(),
                "The Selected checkboxes validation message is not displayed");
    }

    @Test(priority = 1)
    public void testToggleExpansionAndCheckboxVisibility() {
        // Step 1: Navigate to the Elements section and the Checkbox Subcategory
        homePage.clickElementsNavigationBar();
        checkBoxPage.clickCheckBoxSubCategory();

        // Step 2: Expand and Assert Toggles and Checkboxes
        verifyToggle("Home", "Desktop", "Documents", "Downloads");
        verifyToggle("Desktop", "Notes", "Commands");
        verifyToggle("Documents", "WorkSpace", "Office");
        verifyToggle("Work Space", "React", "Angular", "Veu");
        verifyToggle("Office", "Public", "Private", "Classified", "General");
        verifyToggle("Downloads", "Word File.doc", "Excel File.doc");
    }

    private void verifyToggle(String toggleName, String... checkboxes) {
        // Expand the toggle
        checkBoxPage.clickToggle(toggleName);

        // Assert that the specified checkboxes are visible
        checkBoxPage.assertTogglesCheckboxesAreDisplayed(checkboxes);
    }

    @Test(priority = 4)
    public void testSelectionOfVariousCheckboxes() {
        homePage.clickElementsNavigationBar();
        checkBoxPage.clickCheckBoxSubCategory();
        checkBoxPage.clickOnExpandAllButton();
        checkBoxPage.selectCheckbox("Commands");
        checkBoxPage.selectCheckbox("Angular");
        checkBoxPage.selectCheckbox("Classified");

        // Verify that the selected checkboxes match the expected list
        Assert.assertEquals(checkBoxPage.getSelectedCheckboxes().replaceAll("\\s+", " ").trim(),
                "You have selected : commands angular classified",
                "The selected checkboxes do not align with the expected selection.");
    }

}
