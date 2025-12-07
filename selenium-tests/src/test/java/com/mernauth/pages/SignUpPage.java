package com.mernauth.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SignUpPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By signUpTitle = By.xpath("//h2[contains(text(), 'Sign Up')]");
    private By nameField = By.xpath("//input[@name='name']");
    private By emailField = By.xpath("//input[@name='email']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By confirmPasswordField = By.xpath("//input[@name='confirmPassword']");
    private By signUpBtn = By.xpath("//button[contains(text(), 'Sign Up')]");
    private By signInLink = By.xpath("//a[contains(text(), 'Sign In')]");
    private By errorMessage = By.xpath("//div[@class='error-message']");
    
    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public boolean isSignUpPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(signUpTitle));
            return driver.findElement(signUpTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }
    
    public void enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }
    
    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }
    
    public void enterConfirmPassword(String confirmPassword) {
        driver.findElement(confirmPasswordField).clear();
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }
    
    public void clickSignUpButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signUpBtn));
        driver.findElement(signUpBtn).click();
    }
    
    public void clickSignInLink() {
        driver.findElement(signInLink).click();
    }
    
    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return driver.findElement(errorMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void signUp(String name, String email, String password, String confirmPassword) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        clickSignUpButton();
    }
}
