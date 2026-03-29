package com.hydra.tests;

import com.hydra.base.BaseTest;
import com.hydra.pages.GoogleHomePage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Parameterized Test Example - Using TestNG Parameters
 * Parameters can be passed via testng.xml
 */
public class ParameterizedTest extends BaseTest {

    /**
     * Test with two parameters
     * Run with: mvn test -Dfirstname=John -Dlastname=Doe
     */
    @Parameters({ "firstname", "lastname" })
    @Test(description = "Test with parameters from testng.xml")
    public void testWithParameters(String firstname, String lastname) {
        logger.info("TEST STARTED: testWithParameters");
        logger.info("First Name: " + firstname);
        logger.info("Last Name: " + lastname);

        try {
            GoogleHomePage googlePage = new GoogleHomePage(driver);
            googlePage.navigateToURL("https://www.google.com");

            String searchQuery = firstname + " " + lastname;
            googlePage.searchQuery(searchQuery);
            logger.info("Searched for: " + searchQuery);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            String currentURL = googlePage.getCurrentURL();
            Assert.assertTrue(currentURL.contains("search"));

            logger.info("TEST PASSED");
        } catch (Exception e) {
            logger.error("TEST FAILED: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Test with multiple parameters for different browsers
     */
    @Parameters({ "searchQuery", "expectedKeyword" })
    @Test(description = "Parameterized search test")
    public void testParameterizedSearch(String searchQuery, String expectedKeyword) {
        logger.info("TEST STARTED: testParameterizedSearch");
        logger.info("Search Query: " + searchQuery);
        logger.info("Expected Keyword: " + expectedKeyword);

        try {
            GoogleHomePage googlePage = new GoogleHomePage(driver);
            googlePage.navigateToURL("https://www.google.com");
            googlePage.acceptCookies();
            googlePage.searchQuery(searchQuery);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            String pageContent = googlePage.getPageSource();
            Assert.assertTrue(pageContent.contains(expectedKeyword),
                    "Page should contain: " + expectedKeyword);

            logger.info("TEST PASSED");
        } catch (Exception e) {
            logger.error("TEST FAILED: " + e.getMessage());
            throw e;
        }
    }
}
