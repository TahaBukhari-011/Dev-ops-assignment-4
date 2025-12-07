package com.mernauth.utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    
    protected WebDriver driver;
    protected String BASE_URL = "http://localhost:3000";
    
    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.createChromeDriver();
        driver.manage().window().maximize();
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    protected void navigateTo(String url) {
        driver.navigate().to(url);
    }
}
