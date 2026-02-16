package com.mason.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage {

    private final WebDriver driver;

    private static final String URL = "https://the-internet.herokuapp.com/dropdown";
    private final By dropdown = By.id("dropdown");

    public DropdownPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
    }

    public String getSelectedOptionText() {
        return new Select(driver.findElement(dropdown))
                .getFirstSelectedOption()
                .getText()
                .trim();
    }

    public void selectByVisibleText(String optionText) {
        new Select(driver.findElement(dropdown))
                .selectByVisibleText(optionText);
    }
}
