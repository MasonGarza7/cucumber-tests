package com.mason.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final WebDriver driver;
    private static final String URL = "https://the-internet.herokuapp.com/";
    private static final By HEADER = By.tagName("h1");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
    }

    public String getHeaderText() {
        return driver.findElement(HEADER).getText().trim();
    }
}
