package com.hydra.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;

/**
 * Wait Utility - Custom wait implementations
 */
public class WaitUtils {
    private static final Logger logger = LogManager.getLogger(WaitUtils.class);

    /**
     * Fluent wait for element to be visible - with custom timeout
     */
    public static void waitForElementVisible(WebDriver driver, By locator, Duration duration) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, duration);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Element visible within " + duration.getSeconds() + " seconds: " + locator);
        } catch (Exception e) {
            logger.error("Element not visible within " + duration.getSeconds() + " seconds: " + locator);
            throw e;
        }
    }

    /**
     * Fluent wait for element to be clickable - with custom timeout
     */
    public static void waitForElementClickable(WebDriver driver, By locator, Duration duration) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, duration);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            logger.info("Element clickable within " + duration.getSeconds() + " seconds: " + locator);
        } catch (Exception e) {
            logger.error("Element not clickable within " + duration.getSeconds() + " seconds: " + locator);
            throw e;
        }
    }

    /**
     * Wait for element to disappear
     */
    public static void waitForElementToDisappear(WebDriver driver, By locator, Duration duration) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, duration);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            logger.info("Element disappeared within " + duration.getSeconds() + " seconds: " + locator);
        } catch (Exception e) {
            logger.error("Element did not disappear within " + duration.getSeconds() + " seconds: " + locator);
            throw e;
        }
    }

    /**
     * Wait for URL to contain specific string
     */
    public static void waitForURLContains(WebDriver driver, String urlPart, Duration duration) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, duration);
            wait.until(ExpectedConditions.urlContains(urlPart));
            logger.info("URL contains '" + urlPart + "' within " + duration.getSeconds() + " seconds");
        } catch (Exception e) {
            logger.error("URL does not contain '" + urlPart + "' within " + duration.getSeconds() + " seconds");
            throw e;
        }
    }

    /**
     * Wait for page title
     */
    public static void waitForPageTitle(WebDriver driver, String title, Duration duration) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, duration);
            wait.until(ExpectedConditions.titleIs(title));
            logger.info("Page title is '" + title + "' within " + duration.getSeconds() + " seconds");
        } catch (Exception e) {
            logger.error("Page title is not '" + title + "' within " + duration.getSeconds() + " seconds");
            throw e;
        }
    }

    /**
     * Wait for any of multiple elements to be visible
     */
    public static void waitForAnyElementVisible(WebDriver driver, By... locators) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            for (By locator : locators) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            }
            LogManager.getLogger(WaitUtils.class).info("One of the elements became visible");
        } catch (Exception e) {
            LogManager.getLogger(WaitUtils.class).error("None of the elements became visible");
            throw e;
        }
    }
}
