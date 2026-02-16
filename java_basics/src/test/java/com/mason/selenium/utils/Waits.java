package com.mason.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class Waits {
    private Waits() {}

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    public static WebDriverWait wait(WebDriver driver) {
        return new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    public static WebElement visible(WebDriver driver, By by) {
        return wait(driver).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static WebElement clickable(WebDriver driver, By by) {
        return wait(driver).until(ExpectedConditions.elementToBeClickable(by));
    }

    public static boolean gone(WebDriver driver, By by) {
        return wait(driver).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}
