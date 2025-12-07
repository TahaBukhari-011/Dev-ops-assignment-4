package com.mernauth.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SignInPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By signInTitle = By.xpath("//h2[contains(text(), 'Sign In')]");
    private By emailField = By.xpath("//input[@name='email']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By signInBtn = By.xpath("//button[contains(text(), 'Sign In')]");
    private By signUpLink = By.xpath("//a[contains(text(), 'Sign Up')]");
    private By errorMessage = By.xpath("//div[@class='error-message']");
    
    public SignInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public boolean isSignInPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(signInTitle));
            return driver.findElement(signInTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }
    
    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }
    
    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signInBtn));
        driver.findElement(signInBtn).click();
    }
    
    public void clickSignUpLink() {
        driver.findElement(signUpLink).click();
    }
    
    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return driver.findElement(errorMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    public void signIn(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignInButton();
    }
    
    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
