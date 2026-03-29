package com.hydra.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.hydra.config.ConfigManager;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Screenshot Utility - Handles screenshot capture
 */
public class ScreenshotUtils {
    private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);
    private static final ConfigManager configManager = ConfigManager.getInstance();

    /**
     * Take screenshot of entire page
     */
    public static String takeScreenshot(WebDriver driver, String fileName) {
        try {
            String screenshotPath = configManager.getScreenshotPath();

            // Create screenshots directory if it doesn't exist
            File screenshotDir = new File(screenshotPath);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
                logger.info("Screenshots directory created: " + screenshotPath);
            }

            // Create filename with timestamp
            String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
            String fullFileName = fileName + "_" + timestamp + ".png";
            String fullPath = screenshotPath + File.separator + fullFileName;

            // Take screenshot
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(fullPath);

            // Copy file to destination
            FileUtils.copyFile(sourceFile, destinationFile);
            logger.info("Screenshot captured successfully: " + fullPath);

            return fullPath;
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Take screenshot with custom path
     */
    public static String takeScreenshot(WebDriver driver, String fileName, String customPath) {
        try {
            // Create screenshots directory if it doesn't exist
            File screenshotDir = new File(customPath);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
                logger.info("Screenshots directory created: " + customPath);
            }

            // Create filename with timestamp
            String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
            String fullFileName = fileName + "_" + timestamp + ".png";
            String fullPath = customPath + File.separator + fullFileName;

            // Take screenshot
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(fullPath);

            // Copy file to destination
            FileUtils.copyFile(sourceFile, destinationFile);
            logger.info("Screenshot captured successfully: " + fullPath);

            return fullPath;
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}
