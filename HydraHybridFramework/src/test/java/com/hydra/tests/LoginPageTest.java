package com.hydra.tests;

import com.hydra.base.BaseTest;
import com.hydra.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Login Page Tests - Demo test class using POM
 */
public class LoginPageTest extends BaseTest {

    /**
     * Test Case: Verify login page is displayed
     */
    @Test(description = "User should see login page when navigating to login URL", priority = 1)
    public void testLoginPageDisplay() {
        logger.info("TEST STARTED: testLoginPageDisplay");

        try {
            LoginPage loginPage = new LoginPage(driver);

            // Navigate to login page (replace with actual URL)
            loginPage.navigateToURL("https://example.com/login");
            logger.info("Navigated to login page");

            // Verify login page is displayed
            Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                    "Login page should be displayed");
            logger.info("Login page is displayed");

            logger.info("TEST PASSED: testLoginPageDisplay");
        } catch (Exception e) {
            logger.error("TEST FAILED: testLoginPageDisplay - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Test Case: Perform login successfully
     */
    @Test(description = "User should be able to login with valid credentials", priority = 2)
    public void testSuccessfulLogin() {
        logger.info("TEST STARTED: testSuccessfulLogin");

        try {
            LoginPage loginPage = new LoginPage(driver);

            // Navigate to login page
            loginPage.navigateToURL("https://example.com/login");

            // Perform login
            loginPage.login("testuser@example.com", "TestPassword123");
            logger.info("Login performed with valid credentials");

            // Wait for page redirection
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Verify error message is not displayed (assuming successful login)
            Assert.assertFalse(loginPage.isErrorMessageDisplayed(),
                    "Error message should not be displayed for valid credentials");

            logger.info("TEST PASSED: testSuccessfulLogin");
        } catch (Exception e) {
            logger.error("TEST FAILED: testSuccessfulLogin - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Test Case: Login with invalid credentials
     */
    @Test(description = "User should see error message for invalid credentials", priority = 3)
    public void testLoginWithInvalidCredentials() {
        logger.info("TEST STARTED: testLoginWithInvalidCredentials");

        try {
            LoginPage loginPage = new LoginPage(driver);

            // Navigate to login page
            loginPage.navigateToURL("https://example.com/login");

            // Perform login with invalid credentials
            loginPage.login("invaliduser@example.com", "WrongPassword");
            logger.info("Login attempted with invalid credentials");

            // Wait for error message
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Verify error message is displayed
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                    "Error message should be displayed for invalid credentials");

            // Verify error message text
            String errorMsg = loginPage.getErrorMessage();
            Assert.assertTrue(errorMsg.contains("Invalid"),
                    "Error message should contain 'Invalid'");

            logger.info("TEST PASSED: testLoginWithInvalidCredentials");
        } catch (Exception e) {
            logger.error("TEST FAILED: testLoginWithInvalidCredentials - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Test Case: Remember Me checkbox functionality
     */
    @Test(description = "User should be able to select remember me checkbox", priority = 4)
    public void testRememberMeCheckbox() {
        logger.info("TEST STARTED: testRememberMeCheckbox");

        try {
            LoginPage loginPage = new LoginPage(driver);

            // Navigate to login page
            loginPage.navigateToURL("https://example.com/login");

            // Click remember me checkbox
            loginPage.clickRememberMe();
            logger.info("Remember me checkbox clicked");

            // Verify checkbox is selected
            Assert.assertTrue(loginPage.isRememberMeSelected(),
                    "Remember me checkbox should be selected");

            logger.info("TEST PASSED: testRememberMeCheckbox");
        } catch (Exception e) {
            logger.error("TEST FAILED: testRememberMeCheckbox - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Test Case: Forgot password link navigation
     */
    @Test(description = "User should be able to click forgot password link", priority = 5)
    public void testForgotPasswordLink() {
        logger.info("TEST STARTED: testForgotPasswordLink");

        try {
            LoginPage loginPage = new LoginPage(driver);

            // Navigate to login page
            loginPage.navigateToURL("https://example.com/login");

            // Click forgot password link
            loginPage.clickForgotPasswordLink();
            logger.info("Forgot password link clicked");

            // Wait for page navigation
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Verify URL changed
            String currentURL = loginPage.getCurrentURL();
            Assert.assertTrue(currentURL.contains("forgot") || currentURL.contains("reset"),
                    "Should navigate to forgot password page");

            logger.info("TEST PASSED: testForgotPasswordLink");
        } catch (Exception e) {
            logger.error("TEST FAILED: testForgotPasswordLink - " + e.getMessage());
            throw e;
        }
    }
}
