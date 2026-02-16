package com.mason.selenium.listeners;

import com.mason.selenium.utils.DriverManager;
import com.mason.selenium.utils.LoggerUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class ScreenshotListener implements ITestListener {

    private static final Logger logger = LoggerUtil.getLogger();
    private static final String SCREENSHOTS_DIR = "results/screenshots";

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverManager.getDriver();

        if (driver == null) {
            logger.warning("WebDriver was null, screenshot not captured");
            return;
        }

        try {
            Files.createDirectories(Path.of(SCREENSHOTS_DIR));

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String testName = result.getMethod().getMethodName();
            String filename = testName + "_" + timestamp + ".png";

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(SCREENSHOTS_DIR, filename);

            Files.copy(srcFile.toPath(), destFile.toPath());

            logger.warning("Test failed. Screenshot saved to: " + destFile.getPath());
        } catch (Exception e) {
            logger.severe("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
