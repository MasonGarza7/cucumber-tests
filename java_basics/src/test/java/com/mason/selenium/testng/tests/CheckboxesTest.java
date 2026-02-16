package com.mason.selenium.testng.tests;

import com.mason.selenium.pages.CheckboxesPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CheckboxesTest extends BaseTest {

    @Test
    public void testCheckboxesToggle() {
        logger.info("STARTING Test --- Checkboxes Toggle ---");

        CheckboxesPage page = new CheckboxesPage(driver);
        page.open();
        logger.info("Navigated to checkboxes page");

        List<WebElement> checkboxes = page.getCheckboxes();
        logger.info("Found " + checkboxes.size() + " checkboxes");

        int index = 1;
        for (WebElement box : checkboxes) {
            boolean initialState = box.isSelected();
            logger.info("Checkbox " + index + " initial state: " + initialState);

            box.click();

            boolean toggledState = box.isSelected();
            logger.info("Checkbox " + index + " toggled state: " + toggledState);

            Assert.assertNotEquals(
                    toggledState,
                    initialState,
                    "Checkbox " + index + " did not change state"
            );

            index++;
        }

        logger.info("ENDING Test --- Completed successfully: Checkboxes Toggle ---");
    }
}
