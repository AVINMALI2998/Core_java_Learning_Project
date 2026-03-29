package com.hydra.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * WebDriver Factory - Creates and manages WebDriver instances
 */
public class WebDriverFactory {
    private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);

    /**
     * Get WebDriver instance based on browser type
     */
    public static WebDriver getDriver(String browser) {
        WebDriver driver = null;

        try {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = createChromeDriver();
                    logger.info("Chrome driver initialized successfully");
                    break;
                case "firefox":
                    driver = createFirefoxDriver();
                    logger.info("Firefox driver initialized successfully");
                    break;
                case "edge":
                    driver = createEdgeDriver();
                    logger.info("Edge driver initialized successfully");
                    break;
                default:
                    logger.error("Browser: " + browser + " is not supported");
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        } catch (Exception e) {
            logger.error("Failed to initialize WebDriver: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return driver;
    }

    /**
     * Create Chrome WebDriver
     */
    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        
        // Optional: Configure Chrome options
        // options.addArguments("--headless");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        return new ChromeDriver(options);
    }

    /**
     * Create Firefox WebDriver
     */
    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        
        // Optional: Configure Firefox options
        // options.addArguments("--headless");
        options.addArguments("--start-maximized");

        return new FirefoxDriver(options);
    }

    /**
     * Create Edge WebDriver
     */
    private static WebDriver createEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        
        // Optional: Configure Edge options
        // options.addArguments("--headless");
        options.addArguments("--start-maximized");

        return new EdgeDriver(options);
    }

    /**
     * Close WebDriver
     */
    public static void closeDriver(WebDriver driver) {
        if (driver != null) {
            try {
                driver.quit();
                logger.info("WebDriver closed successfully");
            } catch (Exception e) {
                logger.error("Error closing WebDriver: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
