package com.hydra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.hydra.base.BasePage;

/**
 * Login Page - POM (Example page for typical web application)
 */
public class LoginPage extends BasePage {

    // Locators
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login_button");
    private By errorMessage = By.className("error-message");
    private By rememberMeCheckbox = By.id("remember_me");
    private By forgotPasswordLink = By.linkText("Forgot Password");
    private By pageTitle = By.tagName("h1");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enter username
     */
    public void enterUsername(String username) {
        typeText(usernameField, username);
        logger.info("Username entered: " + username);
    }

    /**
     * Enter password
     */
    public void enterPassword(String password) {
        typeText(passwordField, password);
        logger.info("Password entered");
    }

    /**
     * Click login button
     */
    public void clickLoginButton() {
        click(loginButton);
        logger.info("Login button clicked");
    }

    /**
     * Perform login with username and password
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        logger.info("Login performed with username: " + username);
    }

    /**
     * Check if error message is displayed
     */
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    /**
     * Get error message text
     */
    public String getErrorMessage() {
        return getText(errorMessage);
    }

    /**
     * Click remember me checkbox
     */
    public void clickRememberMe() {
        click(rememberMeCheckbox);
        logger.info("Remember me checkbox clicked");
    }

    /**
     * Check if remember me is selected
     */
    public boolean isRememberMeSelected() {
        return isElementSelected(rememberMeCheckbox);
    }

    /**
     * Click forgot password link
     */
    public void clickForgotPasswordLink() {
        click(forgotPasswordLink);
        logger.info("Forgot password link clicked");
    }

    /**
     * Get page title
     */
    public String getLoginPageTitle() {
        return getText(pageTitle);
    }

    /**
     * Check if username field is displayed
     */
    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(usernameField);
    }
}
