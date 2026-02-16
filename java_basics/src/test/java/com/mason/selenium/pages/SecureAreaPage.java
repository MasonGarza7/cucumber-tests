package com.mason.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SecureAreaPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By header = By.cssSelector("div.example h2");

    public SecureAreaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(header)).getText().trim();
    }
}
