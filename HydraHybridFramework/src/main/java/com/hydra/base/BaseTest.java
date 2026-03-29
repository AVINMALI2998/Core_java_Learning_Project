package com.hydra.base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.hydra.config.ConfigManager;
import com.hydra.utils.ScreenshotUtils;

/**
 * Base Test Class - Contains setup and teardown methods for all tests
 */
public class BaseTest {
    protected WebDriver driver;
    protected ConfigManager configManager;
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    /**
     * Setup method - runs before each test
     * Can accept browser parameter from testng.xml
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({ "browser" })
    public void setUp(String browser) {
        try {
            // If browser parameter is not provided, use default from config
            if (browser == null || browser.isEmpty()) {
                configManager = ConfigManager.getInstance();
                browser = configManager.getBrowser();
            } else {
                configManager = ConfigManager.getInstance();
            }

            logger.info("====================================");
            logger.info("Starting test setup with browser: " + browser);
            logger.info("====================================");

            // Initialize WebDriver
            driver = WebDriverFactory.getDriver(browser);

            // Set implicit wait
            driver.manage().timeouts()
                    .implicitlyWait(java.time.Duration.ofSeconds(configManager.getImplicitWait()));

            // Set page load timeout
            driver.manage().timeouts()
                    .pageLoadTimeout(java.time.Duration.ofSeconds(configManager.getPageLoadTimeout()));

            logger.info("WebDriver initialized and configured successfully");
            logger.info("Implicit wait: " + configManager.getImplicitWait() + " seconds");
            logger.info("Page load timeout: " + configManager.getPageLoadTimeout() + " seconds");

        } catch (Exception e) {
            logger.error("Failed to set up WebDriver: " + e.getMessage());
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }
    }

    /**
     * Setup method - runs before each test (alternative signature)
     */
    @BeforeMethod(alwaysRun = true)
    public void setUpWithoutParameter() {
        try {
            configManager = ConfigManager.getInstance();
            String browser = configManager.getBrowser();

            logger.info("====================================");
            logger.info("Starting test setup with browser: " + browser);
            logger.info("====================================");

            // Initialize WebDriver
            driver = WebDriverFactory.getDriver(browser);

            // Set implicit wait
            driver.manage().timeouts()
                    .implicitlyWait(java.time.Duration.ofSeconds(configManager.getImplicitWait()));

            // Set page load timeout
            driver.manage().timeouts()
                    .pageLoadTimeout(java.time.Duration.ofSeconds(configManager.getPageLoadTimeout()));

            logger.info("WebDriver initialized and configured successfully");

        } catch (Exception e) {
            logger.error("Failed to set up WebDriver: " + e.getMessage());
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }
    }

    /**
     * Teardown method - runs after each test
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        try {
            logger.info("====================================");
            logger.info("Test Execution Finished");
            logger.info("Test Status: " + result.getStatus());

            // Take screenshot on failure if enabled
            if (result.getStatus() == ITestResult.FAILURE) {
                logger.error("Test FAILED: " + result.getName());
                logger.error("Failure Message: " + result.getThrowable().getMessage());

                if (configManager.isScreenshotOnFailure() && driver != null) {
                    String screenshotPath = ScreenshotUtils.takeScreenshot(
                            driver,
                            result.getName() + "_" + System.currentTimeMillis());
                    logger.info("Screenshot taken: " + screenshotPath);
                }
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                logger.info("Test PASSED: " + result.getName());
            } else if (result.getStatus() == ITestResult.SKIP) {
                logger.warn("Test SKIPPED: " + result.getName());
            }

            logger.info("====================================");

        } catch (Exception e) {
            logger.error("Error during test teardown: " + e.getMessage());
        } finally {
            // Close WebDriver
            if (driver != null) {
                WebDriverFactory.closeDriver(driver);
                logger.info("WebDriver closed successfully");
            }
        }
    }

    /**
     * Get current WebDriver instance
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Get ConfigManager instance
     */
    public ConfigManager getConfigManager() {
        return configManager;
    }
}
