package com.mernauth.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WelcomePage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By welcomeTitle = By.xpath("//h1[contains(text(), 'Welcome')]");
    private By userNameDisplay = By.xpath("//span[@class='value'][1]");
    private By userEmailDisplay = By.xpath("//span[@class='value'][2]");
    private By logoutButton = By.xpath("//button[contains(text(), 'Logout')]");
    
    public WelcomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public boolean isWelcomePageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeTitle));
            return driver.findElement(welcomeTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getWelcomeMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeTitle));
        return driver.findElement(welcomeTitle).getText();
    }
    
    public String getUserName() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(userNameDisplay));
            return driver.findElement(userNameDisplay).getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    public String getUserEmail() {
        try {
            return driver.findElement(userEmailDisplay).getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        driver.findElement(logoutButton).click();
    }
    
    public boolean isWelcomeMessageContainsName(String name) {
        String message = getWelcomeMessage();
        return message.contains("Welcome") && message.contains(name);
    }
}
