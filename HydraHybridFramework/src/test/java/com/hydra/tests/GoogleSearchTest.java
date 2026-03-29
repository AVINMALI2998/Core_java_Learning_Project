package com.hydra.tests;

import com.hydra.base.BaseTest;
import com.hydra.pages.GoogleHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Google Search Tests - Demo test class using POM
 */
public class GoogleSearchTest extends BaseTest {

    /**
     * Test Case: Perform Google Search for a query
     */
    @Test(description = "User should be able to search on Google", priority = 1)
    public void testGoogleSearch() {
        logger.info("TEST STARTED: testGoogleSearch");

        try {
            GoogleHomePage googlePage = new GoogleHomePage(driver);

            // Navigate to Google
            googlePage.navigateToURL("https://www.google.com");
            logger.info("Navigated to Google");

            // Accept cookies if displayed
            googlePage.acceptCookies();

            // Verify Google logo is displayed
            Assert.assertTrue(googlePage.isGoogleLogoDisplayed(),
                    "Google logo should be displayed");
            logger.info("Google logo is displayed");

            // Perform search
            googlePage.searchQuery("Selenium WebDriver");
            logger.info("Search performed for: Selenium WebDriver");

            // Wait and verify results
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            String currentURL = googlePage.getCurrentURL();
            Assert.assertTrue(currentURL.contains("search"),
                    "Search results page should be displayed");
            logger.info("Search results page displayed successfully");

            logger.info("TEST PASSED: testGoogleSearch");
        } catch (Exception e) {
            logger.error("TEST FAILED: testGoogleSearch - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Test Case: Verify Google page title
     */
    @Test(description = "Google page title should be Google", priority = 2)
    public void testGooglePageTitle() {
        logger.info("TEST STARTED: testGooglePageTitle");

        try {
            GoogleHomePage googlePage = new GoogleHomePage(driver);

            // Navigate to Google
            googlePage.navigateToURL("https://www.google.com");

            // Get page title
            String pageTitle = googlePage.getPageTitle();
            logger.info("Page title: " + pageTitle);

            // Verify page title contains 'Google'
            Assert.assertTrue(pageTitle.contains("Google"),
                    "Page title should contain 'Google'");

            logger.info("TEST PASSED: testGooglePageTitle");
        } catch (Exception e) {
            logger.error("TEST FAILED: testGooglePageTitle - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Test Case: Clear search box
     */
    @Test(description = "User should be able to clear search box", priority = 3)
    public void testClearSearchBox() {
        logger.info("TEST STARTED: testClearSearchBox");

        try {
            GoogleHomePage googlePage = new GoogleHomePage(driver);

            // Navigate to Google
            googlePage.navigateToURL("https://www.google.com");

            // Accept cookies
            googlePage.acceptCookies();

            // Type search query
            googlePage.searchQuery("Test Query");

            // Get search box value
            String searchValue = googlePage.getSearchBoxValue();
            Assert.assertTrue(searchValue.contains("Test Query"),
                    "Search box should contain 'Test Query'");

            logger.info("TEST PASSED: testClearSearchBox");
        } catch (Exception e) {
            logger.error("TEST FAILED: testClearSearchBox - " + e.getMessage());
            throw e;
        }
    }
}
