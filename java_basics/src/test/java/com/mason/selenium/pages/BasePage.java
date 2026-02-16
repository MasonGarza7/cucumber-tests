package com.mason.selenium.pages;

import com.mason.selenium.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {
    protected final WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement visible(By by) {
        return Waits.visible(driver, by);
    }

    protected WebElement clickable(By by) {
        return Waits.clickable(driver, by);
    }

    public String currentUrl() {
        return driver.getCurrentUrl();
    }
}
