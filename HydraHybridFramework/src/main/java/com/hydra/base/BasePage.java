package com.hydra.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.hydra.config.ConfigManager;
import java.time.Duration;

/**
 * Base Page Object Model - Contains common methods for all page objects
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final Logger logger = LogManager.getLogger(BasePage.class);
    protected ConfigManager configManager = ConfigManager.getInstance();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(configManager.getExplicitWait()));
        PageFactory.initElements(driver, this);
    }

    // ============ Wait Methods ============

    /**
     * Wait for element to be visible
     */
    public WebElement waitForElementToBeVisible(By locator) {
        try {
            logger.info("Waiting for element to be visible: " + locator);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element not visible: " + locator);
            throw e;
        }
    }

    /**
     * Wait for element to be clickable
     */
    public WebElement waitForElementToBeClickable(By locator) {
        try {
            logger.info("Waiting for element to be clickable: " + locator);
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            logger.error("Element not clickable: " + locator);
            throw e;
        }
    }

    /**
     * Wait for element to be present in DOM
     */
    public WebElement waitForElementPresence(By locator) {
        try {
            logger.info("Waiting for element presence: " + locator);
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element not present: " + locator);
            throw e;
        }
    }

    /**
     * Wait for element invisibility
     */
    public boolean waitForElementInvisibility(By locator) {
        try {
            logger.info("Waiting for element to be invisible: " + locator);
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element is still visible: " + locator);
            return false;
        }
    }

    // ============ Click Methods ============

    /**
     * Click on element
     */
    public void click(By locator) {
        try {
            WebElement element = waitForElementToBeClickable(locator);
            element.click();
            logger.info("Clicked on element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to click on element: " + locator);
            throw e;
        }
    }

    /**
     * Click using JavaScript executor
     */
    public void jsClick(By locator) {
        try {
            WebElement element = waitForElementPresence(locator);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            logger.info("JavaScript click on element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to JS click on element: " + locator);
            throw e;
        }
    }

    /**
     * Double click on element
     */
    public void doubleClick(By locator) {
        try {
            WebElement element = waitForElementPresence(locator);
            Actions actions = new Actions(driver);
            actions.doubleClick(element).perform();
            logger.info("Double clicked on element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to double click on element: " + locator);
            throw e;
        }
    }

    /**
     * Right click on element
     */
    public void rightClick(By locator) {
        try {
            WebElement element = waitForElementPresence(locator);
            Actions actions = new Actions(driver);
            actions.contextClick(element).perform();
            logger.info("Right clicked on element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to right click on element: " + locator);
            throw e;
        }
    }

    // ============ Text Input Methods ============

    /**
     * Type text into element
     */
    public void typeText(By locator, String text) {
        try {
            WebElement element = waitForElementToBeVisible(locator);
            element.clear();
            element.sendKeys(text);
            logger.info("Typed text '" + text + "' into element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to type text into element: " + locator);
            throw e;
        }
    }

    /**
     * Type text with clear using JavaScript
     */
    public void typeTextJS(By locator, String text) {
        try {
            WebElement element = waitForElementPresence(locator);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].value = '';", element);
            element.sendKeys(text);
            logger.info("JS typed text '" + text + "' into element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to JS type text into element: " + locator);
            throw e;
        }
    }

    /**
     * Clear element text
     */
    public void clearText(By locator) {
        try {
            WebElement element = waitForElementToBeVisible(locator);
            element.clear();
            logger.info("Cleared text from element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to clear text from element: " + locator);
            throw e;
        }
    }

    // ============ Get Text Methods ============

    /**
     * Get text from element
     */
    public String getText(By locator) {
        try {
            WebElement element = waitForElementToBeVisible(locator);
            String text = element.getText();
            logger.info("Retrieved text from element: " + locator + " - Text: " + text);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from element: " + locator);
            throw e;
        }
    }

    /**
     * Get attribute value
     */
    public String getAttribute(By locator, String attribute) {
        try {
            WebElement element = waitForElementPresence(locator);
            String value = element.getAttribute(attribute);
            logger.info("Retrieved attribute '" + attribute + "' from element: " + locator + " - Value: " + value);
            return value;
        } catch (Exception e) {
            logger.error("Failed to get attribute from element: " + locator);
            throw e;
        }
    }

    // ============ Element Checks ============

    /**
     * Check if element is displayed
     */
    public boolean isElementDisplayed(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            boolean isDisplayed = element.isDisplayed();
            logger.info("Element displayed check: " + locator + " - " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.warn("Element not displayed: " + locator);
            return false;
        }
    }

    /**
     * Check if element is enabled
     */
    public boolean isElementEnabled(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            boolean isEnabled = element.isEnabled();
            logger.info("Element enabled check: " + locator + " - " + isEnabled);
            return isEnabled;
        } catch (Exception e) {
            logger.warn("Element not enabled: " + locator);
            return false;
        }
    }

    /**
     * Check if element is selected
     */
    public boolean isElementSelected(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            boolean isSelected = element.isSelected();
            logger.info("Element selected check: " + locator + " - " + isSelected);
            return isSelected;
        } catch (Exception e) {
            logger.warn("Element not selected: " + locator);
            return false;
        }
    }

    // ============ Navigation Methods ============

    /**
     * Navigate to URL
     */
    public void navigateToURL(String url) {
        try {
            driver.navigate().to(url);
            logger.info("Navigated to URL: " + url);
        } catch (Exception e) {
            logger.error("Failed to navigate to URL: " + url);
            throw e;
        }
    }

    /**
     * Navigate to application base URL
     */
    public void navigateToBaseURL() {
        try {
            String baseURL = configManager.getApplicationURL();
            driver.navigate().to(baseURL);
            logger.info("Navigated to base URL: " + baseURL);
        } catch (Exception e) {
            logger.error("Failed to navigate to base URL");
            throw e;
        }
    }

    /**
     * Refresh page
     */
    public void refreshPage() {
        try {
            driver.navigate().refresh();
            logger.info("Page refreshed");
        } catch (Exception e) {
            logger.error("Failed to refresh page");
            throw e;
        }
    }

    // ============ JavaScript Methods ============

    /**
     * Scroll to element
     */
    public void scrollToElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("Scrolled to element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to scroll to element: " + locator);
            throw e;
        }
    }

    /**
     * Scroll page by pixels
     */
    public void scrollByPixels(int x, int y) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("window.scrollBy(" + x + "," + y + ");");
            logger.info("Scrolled by pixels: X=" + x + ", Y=" + y);
        } catch (Exception e) {
            logger.error("Failed to scroll by pixels");
            throw e;
        }
    }

    /**
     * Execute JavaScript
     */
    public Object executeJavaScript(String script) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            Object result = executor.executeScript(script);
            logger.info("Executed JavaScript: " + script);
            return result;
        } catch (Exception e) {
            logger.error("Failed to execute JavaScript");
            throw e;
        }
    }

    // ============ Wait Methods ============

    /**
     * Explicit wait with custom duration
     */
    public void waitForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
            logger.info("Waited for " + seconds + " seconds");
        } catch (InterruptedException e) {
            logger.error("Wait interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Wait for page title
     */
    public boolean waitForPageTitle(String expectedTitle) {
        try {
            boolean result = wait.until(ExpectedConditions.titleIs(expectedTitle));
            logger.info("Page title found: " + expectedTitle);
            return result;
        } catch (Exception e) {
            logger.error("Page title not found: " + expectedTitle);
            return false;
        }
    }

    /**
     * Get current page title
     */
    public String getPageTitle() {
        String title = driver.getTitle();
        logger.info("Current page title: " + title);
        return title;
    }

    /**
     * Get current page URL
     */
    public String getCurrentURL() {
        String url = driver.getCurrentUrl();
        logger.info("Current URL: " + url);
        return url;
    }

    /**
     * Get page source
     */
    public String getPageSource() {
        return driver.getPageSource();
    }
}
