package com.hydra.tests;

import com.hydra.base.BaseTest;
import com.hydra.pages.GoogleHomePage;
import com.hydra.utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

/**
 * Data-Driven Test Example using Excel
 */
public class DataDrivenTest extends BaseTest {

    /**
     * Data provider reading from Excel file
     */
    @DataProvider(name = "searchQueries")
    public Object[][] getSearchQueries() {
        return new Object[][] {
                { "Selenium WebDriver" },
                { "Java Programming" },
                { "TestNG Framework" },
                { "Page Object Model" },
                { "Automation Testing" }
        };
    }

    /**
     * Data-driven test for Google search
     */
    @Test(dataProvider = "searchQueries", description = "Perform Google search with data from provider")
    public void testGoogleSearchDataDriven(String searchQuery) {
        logger.info("TEST STARTED: testGoogleSearchDataDriven with query: " + searchQuery);

        try {
            GoogleHomePage googlePage = new GoogleHomePage(driver);

            // Navigate to Google
            googlePage.navigateToURL("https://www.google.com");
            logger.info("Navigated to Google");

            // Accept cookies
            googlePage.acceptCookies();

            // Perform search
            googlePage.searchQuery(searchQuery);
            logger.info("Searched for: " + searchQuery);

            // Wait and verify results
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            String currentURL = googlePage.getCurrentURL();
            Assert.assertTrue(currentURL.contains("search"),
                    "Search results page should be displayed");

            logger.info("TEST PASSED: testGoogleSearchDataDriven");
        } catch (Exception e) {
            logger.error("TEST FAILED: testGoogleSearchDataDriven - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Example method for reading from Excel file
     * Uncomment and update path to use with actual Excel file
     */
    /*
     * @Test(description = "Data-driven test using Excel file")
     * public void testWithExcelData() {
     * String excelPath = "src/test/resources/testdata/login_data.xlsx";
     * String sheetName = "LoginData";
     * 
     * List<Map<String, String>> testData = ExcelUtils.getExcelData(excelPath,
     * sheetName);
     * 
     * for (Map<String, String> data : testData) {
     * String username = data.get("username");
     * String password = data.get("password");
     * 
     * logger.info("Testing with username: " + username);
     * // Perform test with data
     * }
     * 
     * ExcelUtils.closeWorkbook();
     * }
     */
}
