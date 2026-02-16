package com.mason.selenium.testng.tests;

import com.mason.selenium.pages.DropdownPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DropdownTest extends BaseTest {

    @Test
    public void testDropdownSelection() {
        logger.info("STARTING Test --- Dropdown Selection ---");

        DropdownPage page = new DropdownPage(driver);
        page.open();
        logger.info("Navigated to dropdown page");

        // Verify default selection
        String defaultOption = page.getSelectedOptionText();
        logger.info("Default selected option: '" + defaultOption + "'");
        assertEquals(defaultOption, "Please select an option");

        // Select Option 1
        logger.info("Selecting 'Option 1'");
        page.selectByVisibleText("Option 1");
        String selectedText = page.getSelectedOptionText();
        logger.info("Selected option: '" + selectedText + "'");
        assertEquals(selectedText, "Option 1");

        // Select Option 2
        logger.info("Selecting 'Option 2'");
        page.selectByVisibleText("Option 2");
        selectedText = page.getSelectedOptionText();
        logger.info("Selected option: '" + selectedText + "'");
        assertEquals(selectedText, "Option 2");

        logger.info("ENDING Test --- Completed successfully: Dropdown Selection ---");
    }
}
