package com.mernauth.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By heading = By.xpath("//h1[contains(text(), 'MERN Auth App')]");
    private By signInButton = By.xpath("//a[contains(text(), 'Sign In')]");
    private By signUpButton = By.xpath("//a[contains(text(), 'Sign Up')]");
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public boolean isHomePageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(heading));
            return driver.findElement(heading).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        driver.findElement(signInButton).click();
    }
    
    public void clickSignUpButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
        driver.findElement(signUpButton).click();
    }
    
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    public String getHeadingText() {
        return driver.findElement(heading).getText();
    }
}
