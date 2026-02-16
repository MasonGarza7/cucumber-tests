package com.mason.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String URL = "https://the-internet.herokuapp.com/login";

    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By submitBtn = By.cssSelector("button[type='submit']");
    private final By flash = By.id("flash");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public LoginPage open() {
        driver.get(URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        wait.until(ExpectedConditions.visibilityOfElementLocated(password));
        return this;
    }

    public LoginPage login(String user, String pass) {
        WebElement userEl = wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        userEl.clear();
        userEl.sendKeys(user);

        WebElement passEl = wait.until(ExpectedConditions.visibilityOfElementLocated(password));
        passEl.clear();
        passEl.sendKeys(pass);

        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
        return this;
    }

    public String getFlashText() {
        WebElement flashEl = wait.until(ExpectedConditions.visibilityOfElementLocated(flash));
        // Remove the close "×" and trim
        return flashEl.getText().replace("×", "").trim();
    }
}
