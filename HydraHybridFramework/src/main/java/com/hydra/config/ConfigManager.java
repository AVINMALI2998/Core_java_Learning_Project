package com.hydra.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Configuration Manager - Handles reading and managing configuration properties
 */
public class ConfigManager {
    private static final Logger logger = LogManager.getLogger(ConfigManager.class);
    private static Properties properties;
    private static ConfigManager instance;

    private ConfigManager() {
        loadProperties();
    }

    /**
     * Get singleton instance of ConfigManager
     */
    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    /**
     * Load properties from configuration file
     */
    private void loadProperties() {
        properties = new Properties();
        String configPath = "src/test/resources/properties/config.properties";

        try (FileInputStream fileInputStream = new FileInputStream(configPath)) {
            properties.load(fileInputStream);
            logger.info("Configuration file loaded successfully from: " + configPath);
        } catch (IOException e) {
            logger.warn("Configuration file not found at: " + configPath);
            logger.info("Using default configuration values");
        }
    }

    /**
     * Get configuration property value
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get property with default value if not found
     */
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get application URL
     */
    public String getApplicationURL() {
        return getProperty("app.url", "https://www.google.com");
    }

    /**
     * Get browser name
     */
    public String getBrowser() {
        return getProperty("browser", "chrome");
    }

    /**
     * Get implicit wait timeout
     */
    public long getImplicitWait() {
        String implicitWait = getProperty("implicit.wait", "10");
        return Long.parseLong(implicitWait);
    }

    /**
     * Get explicit wait timeout
     */
    public long getExplicitWait() {
        String explicitWait = getProperty("explicit.wait", "20");
        return Long.parseLong(explicitWait);
    }

    /**
     * Get page load timeout
     */
    public long getPageLoadTimeout() {
        String pageLoadTimeout = getProperty("pageload.wait", "30");
        return Long.parseLong(pageLoadTimeout);
    }

    /**
     * Check if headless mode is enabled
     */
    public boolean isHeadlessMode() {
        String headlessMode = getProperty("headless.mode", "false");
        return Boolean.parseBoolean(headlessMode);
    }

    /**
     * Check if screenshot on failure is enabled
     */
    public boolean isScreenshotOnFailure() {
        String screenshot = getProperty("screenshot.on.failure", "true");
        return Boolean.parseBoolean(screenshot);
    }

    /**
     * Get screenshot directory path
     */
    public String getScreenshotPath() {
        return getProperty("screenshot.path", "screenshots/");
    }

    /**
     * Get report directory path
     */
    public String getReportPath() {
        return getProperty("report.path", "reports/");
    }

    /**
     * Clear singleton instance (useful for testing)
     */
    public static void resetInstance() {
        instance = null;
    }
}
