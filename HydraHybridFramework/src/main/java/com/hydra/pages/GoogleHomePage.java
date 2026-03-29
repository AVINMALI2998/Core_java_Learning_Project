package com.hydra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.hydra.base.BasePage;

/**
 * Google Home Page - POM
 */
public class GoogleHomePage extends BasePage {

    // Locators
    private By searchBox = By.name("q");
    private By searchButton = By.name("btnK");
    private By googleLogo = By.id("hplogo");
    private By acceptCookiesButton = By.xpath("//button[contains(text(), 'Accept all')]");

    public GoogleHomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Perform search on Google
     */
    public void searchQuery(String query) {
        try {
            typeText(searchBox, query);
            click(searchButton);
            logger.info("Searched for: " + query);
        } catch (Exception e) {
            logger.error("Failed to search query: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Get search box input value
     */
    public String getSearchBoxValue() {
        return getAttribute(searchBox, "value");
    }

    /**
     * Clear search box
     */
    public void clearSearchBox() {
        clearText(searchBox);
    }

    /**
     * Check if Google logo is displayed
     */
    public boolean isGoogleLogoDisplayed() {
        return isElementDisplayed(googleLogo);
    }

    /**
     * Accept cookies if popup appears
     */
    public void acceptCookies() {
        try {
            if (isElementDisplayed(acceptCookiesButton)) {
                click(acceptCookiesButton);
                logger.info("Cookies accepted");
            }
        } catch (Exception e) {
            logger.info("Cookies popup not displayed");
        }
    }

    /**
     * Get page title
     */
    public String getPageTitle() {
        return getPageTitle();
    }
}
