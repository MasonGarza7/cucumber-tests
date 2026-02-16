package com.mason.selenium.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;

public class LoggerUtil {

    private static final Logger LOGGER = Logger.getLogger("SeleniumJavaLogger");
    private static boolean initialized = false;

    private static final String RESULTS_DIR = "results";
    private static final String LOGS_DIR = RESULTS_DIR + "/logs";

    static {
        initLogger();
    }

    private static void initLogger() {
        if (initialized) {
            return;
        }
        initialized = true;

        try {
            // Ensure directories exist
            Path resultsPath = Path.of(RESULTS_DIR);
            Path logsPath = Path.of(LOGS_DIR);
            Files.createDirectories(resultsPath);
            Files.createDirectories(logsPath);

            // Build per-run log file name
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String logFileName = "test_run_" + timestamp + ".log";
            Path logFilePath = logsPath.resolve(logFileName);

            LOGGER.setUseParentHandlers(false);
            LOGGER.setLevel(Level.INFO);

            // File handler
            FileHandler fileHandler = new FileHandler(logFilePath.toString(), true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            LOGGER.addHandler(fileHandler);

            // Console handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);
            LOGGER.addHandler(consoleHandler);

            LOGGER.info("Logger initialized. Writing to " + logFilePath);

        } catch (IOException e) {
            // Fallback to console only if file init fails
            LOGGER.setUseParentHandlers(true);
            LOGGER.warning("Failed to initialize file logging: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}

