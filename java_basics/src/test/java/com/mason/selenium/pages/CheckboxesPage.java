package com.mason.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckboxesPage {

    private final WebDriver driver;

    private static final String URL = "https://the-internet.herokuapp.com/checkboxes";
    private final By checkboxes = By.cssSelector("#checkboxes input[type='checkbox']");

    public CheckboxesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
    }

    public List<WebElement> getCheckboxes() {
        return driver.findElements(checkboxes);
    }

    public WebElement getCheckbox(int oneBasedIndex) {
        List<WebElement> all = getCheckboxes();
        int idx = oneBasedIndex - 1;

        if (idx < 0 || idx >= all.size()) {
            throw new IllegalArgumentException(
                    "Checkbox index out of range. Requested " + oneBasedIndex + " but found " + all.size()
            );
        }
        return all.get(idx);
    }

    public boolean isSelected(int oneBasedIndex) {
        return getCheckbox(oneBasedIndex).isSelected();
    }

    public void toggle(int oneBasedIndex) {
        getCheckbox(oneBasedIndex).click();
    }
}
