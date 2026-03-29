package com.hydra.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Alert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

/**
 * Web Element Utility - Common interactions with web elements
 */
public class WebElementUtils {
    private static final Logger logger = LogManager.getLogger(WebElementUtils.class);

    /**
     * Select option from dropdown by visible text
     */
    public static void selectDropdownByText(WebDriver driver, By locator, String text) {
        try {
            WebElement dropdownElement = driver.findElement(locator);
            Select select = new Select(dropdownElement);
            select.selectByVisibleText(text);
            logger.info("Selected dropdown option by text: " + text);
        } catch (Exception e) {
            logger.error("Failed to select dropdown option by text: " + text);
            throw e;
        }
    }

    /**
     * Select option from dropdown by value
     */
    public static void selectDropdownByValue(WebDriver driver, By locator, String value) {
        try {
            WebElement dropdownElement = driver.findElement(locator);
            Select select = new Select(dropdownElement);
            select.selectByValue(value);
            logger.info("Selected dropdown option by value: " + value);
        } catch (Exception e) {
            logger.error("Failed to select dropdown option by value: " + value);
            throw e;
        }
    }

    /**
     * Select option from dropdown by index
     */
    public static void selectDropdownByIndex(WebDriver driver, By locator, int index) {
        try {
            WebElement dropdownElement = driver.findElement(locator);
            Select select = new Select(dropdownElement);
            select.selectByIndex(index);
            logger.info("Selected dropdown option by index: " + index);
        } catch (Exception e) {
            logger.error("Failed to select dropdown option by index: " + index);
            throw e;
        }
    }

    /**
     * Get all options from dropdown
     */
    public static List<WebElement> getDropdownOptions(WebDriver driver, By locator) {
        try {
            WebElement dropdownElement = driver.findElement(locator);
            Select select = new Select(dropdownElement);
            List<WebElement> options = select.getOptions();
            logger.info("Retrieved dropdown options. Total options: " + options.size());
            return options;
        } catch (Exception e) {
            logger.error("Failed to get dropdown options");
            throw e;
        }
    }

    /**
     * Get selected option text
     */
    public static String getSelectedDropdownOption(WebDriver driver, By locator) {
        try {
            WebElement dropdownElement = driver.findElement(locator);
            Select select = new Select(dropdownElement);
            String selectedText = select.getFirstSelectedOption().getText();
            logger.info("Selected dropdown option: " + selectedText);
            return selectedText;
        } catch (Exception e) {
            logger.error("Failed to get selected dropdown option");
            throw e;
        }
    }

    /**
     * Handle JavaScript alert - Accept
     */
    public static void acceptAlert(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            logger.info("Alert accepted");
        } catch (Exception e) {
            logger.error("Failed to accept alert");
            throw e;
        }
    }

    /**
     * Handle JavaScript alert - Dismiss
     */
    public static void dismissAlert(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            logger.info("Alert dismissed");
        } catch (Exception e) {
            logger.error("Failed to dismiss alert");
            throw e;
        }
    }

    /**
     * Get alert text
     */
    public static String getAlertText(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            logger.info("Alert text: " + alertText);
            return alertText;
        } catch (Exception e) {
            logger.error("Failed to get alert text");
            throw e;
        }
    }

    /**
     * Type text in alert input field
     */
    public static void typeInAlert(WebDriver driver, String text) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.sendKeys(text);
            logger.info("Typed text in alert: " + text);
        } catch (Exception e) {
            logger.error("Failed to type in alert");
            throw e;
        }
    }

    /**
     * Check if element is checked (checkbox/radio)
     */
    public static boolean isCheckboxChecked(WebDriver driver, By locator) {
        try {
            WebElement checkbox = driver.findElement(locator);
            boolean isChecked = checkbox.isSelected();
            logger.info("Checkbox checked status: " + isChecked);
            return isChecked;
        } catch (Exception e) {
            logger.error("Failed to check checkbox status");
            throw e;
        }
    }

    /**
     * Check if dropdown is multi-select
     */
    public static boolean isMultiSelectDropdown(WebDriver driver, By locator) {
        try {
            WebElement dropdownElement = driver.findElement(locator);
            Select select = new Select(dropdownElement);
            boolean isMultiple = select.isMultiple();
            logger.info("Dropdown is multi-select: " + isMultiple);
            return isMultiple;
        } catch (Exception e) {
            logger.error("Failed to check if dropdown is multi-select");
            throw e;
        }
    }

    /**
     * Switch to iframe by locator
     */
    public static void switchToIframe(WebDriver driver, By iframeLocator) {
        try {
            WebElement iframeElement = driver.findElement(iframeLocator);
            driver.switchTo().frame(iframeElement);
            logger.info("Switched to iframe");
        } catch (Exception e) {
            logger.error("Failed to switch to iframe");
            throw e;
        }
    }

    /**
     * Switch to iframe by index
     */
    public static void switchToIframeByIndex(WebDriver driver, int index) {
        try {
            driver.switchTo().frame(index);
            logger.info("Switched to iframe by index: " + index);
        } catch (Exception e) {
            logger.error("Failed to switch to iframe by index: " + index);
            throw e;
        }
    }

    /**
     * Switch back to main content from iframe
     */
    public static void switchToMainContent(WebDriver driver) {
        try {
            driver.switchTo().defaultContent();
            logger.info("Switched back to main content");
        } catch (Exception e) {
            logger.error("Failed to switch back to main content");
            throw e;
        }
    }

    /**
     * Get element count
     */
    public static int getElementCount(WebDriver driver, By locator) {
        try {
            List<WebElement> elements = driver.findElements(locator);
            int count = elements.size();
            logger.info("Element count for " + locator + ": " + count);
            return count;
        } catch (Exception e) {
            logger.error("Failed to get element count");
            throw e;
        }
    }
}
