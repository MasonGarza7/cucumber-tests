package com.mason.selenium.hooks;

import com.mason.selenium.utils.DriverManager;
import com.mason.selenium.utils.LoggerUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class Hooks {

    private static final Logger logger = LoggerUtil.getLogger();

    private static final String RESULTS_DIR = "results";
    private static final String LOGS_DIR = RESULTS_DIR + "/logs";
    private static final String SCREENSHOTS_DIR = RESULTS_DIR + "/screenshots";

    private static final DateTimeFormatter TS_FORMAT =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    @Before
    public void setUp() {
        createResultsDirectories();

        logger.info("Starting WebDriver for scenario");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        if (headless) {
            logger.info("Headless mode ENABLED");
            options.addArguments("--headless=new");
        } else {
            logger.info("Headless mode DISABLED");
        }

        WebDriver driver = new ChromeDriver(options);
        DriverManager.setDriver(driver);
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();

        try {
            if (driver != null && scenario.isFailed() && driver instanceof TakesScreenshot) {

                // 1) Screenshot bytes (good for report attach)
                byte[] pngBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                // 2) Attach to Cucumber report
                scenario.attach(pngBytes, "image/png", "failure-screenshot");

                // 3) Save to results/screenshots
                String timestamp = LocalDateTime.now().format(TS_FORMAT);
                String safeScenarioName = toSafeFilename(scenario.getName());
                String fileName = safeScenarioName + "_" + timestamp + ".png";

                Path dest = Paths.get(SCREENSHOTS_DIR, fileName);
                Files.write(dest, pngBytes);

                logger.warning("Scenario failed. Screenshot saved to: " + dest.toString());
            }
        } catch (Exception e) {
            logger.severe("Failed to capture screenshot for failed scenario: " + e.getMessage());
        } finally {
            if (driver != null) {
                logger.info("Quitting WebDriver for scenario");
            }
            DriverManager.quitDriver();
        }
    }

    private static String toSafeFilename(String name) {
        // Replace anything sketchy for Windows filenames
        return name == null ? "scenario" : name.replaceAll("[^a-zA-Z0-9._-]+", "_");
    }

    private void createResultsDirectories() {
        createDir(RESULTS_DIR);
        createDir(LOGS_DIR);
        createDir(SCREENSHOTS_DIR);
    }

    private void createDir(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
