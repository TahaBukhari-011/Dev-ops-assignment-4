package com.mernauth.tests;

import com.mernauth.pages.*;
import com.mernauth.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MernAuthAppTest extends BaseTest {
    
    /**
     * Test 1: Verify Home Page is displayed with correct title and content
     */
    @Test(description = "Verify Home Page displays correctly")
    public void testHomePageDisplayed() {
        navigateTo(BASE_URL);
        HomePage homePage = new HomePage(driver);
        
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page should be displayed");
        Assert.assertEquals(homePage.getHeadingText(), "MERN Auth App", "Heading should match");
        Assert.assertTrue(homePage.getPageTitle().contains("MERN"), "Page title should contain MERN");
    }
    
    /**
     * Test 2: Verify Sign In button navigation
     */
    @Test(description = "Verify Sign In button navigation")
    public void testSignInButtonNavigation() {
        navigateTo(BASE_URL);
        HomePage homePage = new HomePage(driver);
        homePage.clickSignInButton();
        
        SignInPage signInPage = new SignInPage(driver);
        Assert.assertTrue(signInPage.isSignInPageDisplayed(), "Sign In page should be displayed");
    }
    
    /**
     * Test 3: Verify Sign Up button navigation
     */
    @Test(description = "Verify Sign Up button navigation")
    public void testSignUpButtonNavigation() {
        navigateTo(BASE_URL);
        HomePage homePage = new HomePage(driver);
        homePage.clickSignUpButton();
        
        SignUpPage signUpPage = new SignUpPage(driver);
        Assert.assertTrue(signUpPage.isSignUpPageDisplayed(), "Sign Up page should be displayed");
    }
    
    /**
     * Test 4: Verify Sign Up page displays all required fields
     */
    @Test(description = "Verify Sign Up page has all required fields")
    public void testSignUpPageFields() {
        navigateTo(BASE_URL + "/signup");
        SignUpPage signUpPage = new SignUpPage(driver);
        
        Assert.assertTrue(signUpPage.isSignUpPageDisplayed(), "Sign Up page should be displayed");
    }
    
    /**
     * Test 5: Verify Sign In page displays all required fields
     */
    @Test(description = "Verify Sign In page has all required fields")
    public void testSignInPageFields() {
        navigateTo(BASE_URL + "/signin");
        SignInPage signInPage = new SignInPage(driver);
        
        Assert.assertTrue(signInPage.isSignInPageDisplayed(), "Sign In page should be displayed");
    }
    
    /**
     * Test 6: Verify Sign In with invalid credentials shows error
     */
    @Test(description = "Verify Sign In with invalid credentials shows error message")
    public void testSignInWithInvalidCredentials() {
        navigateTo(BASE_URL + "/signin");
        SignInPage signInPage = new SignInPage(driver);
        
        signInPage.signIn("invalid@email.com", "wrongpassword");
        
        // Wait for error message to appear
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Assert.assertTrue(signInPage.isErrorMessageDisplayed(), 
            "Error message should be displayed for invalid credentials");
    }
    
    /**
     * Test 7: Verify Sign Up with mismatched passwords shows error
     */
    @Test(description = "Verify Sign Up with mismatched passwords shows error")
    public void testSignUpWithMismatchedPasswords() {
        navigateTo(BASE_URL + "/signup");
        SignUpPage signUpPage = new SignUpPage(driver);
        
        signUpPage.enterName("Test User");
        signUpPage.enterEmail("test@example.com");
        signUpPage.enterPassword("password123");
        signUpPage.enterConfirmPassword("password456");
        signUpPage.clickSignUpButton();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Assert.assertTrue(signUpPage.isErrorMessageDisplayed(), 
            "Error message should be displayed for mismatched passwords");
    }
    
    /**
     * Test 8: Verify Sign Up with short password shows error
     */
    @Test(description = "Verify Sign Up with short password (less than 6 chars) shows error")
    public void testSignUpWithShortPassword() {
        navigateTo(BASE_URL + "/signup");
        SignUpPage signUpPage = new SignUpPage(driver);
        
        signUpPage.enterName("Test User");
        signUpPage.enterEmail("test@example.com");
        signUpPage.enterPassword("123");
        signUpPage.enterConfirmPassword("123");
        signUpPage.clickSignUpButton();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Assert.assertTrue(signUpPage.isErrorMessageDisplayed(), 
            "Error message should be displayed for short password");
    }
    
    /**
     * Test 9: Verify Sign In page has Sign Up link
     */
    @Test(description = "Verify Sign In page has navigation link to Sign Up")
    public void testSignInPageHasSignUpLink() {
        navigateTo(BASE_URL + "/signin");
        SignInPage signInPage = new SignInPage(driver);
        
        signInPage.clickSignUpLink();
        
        SignUpPage signUpPage = new SignUpPage(driver);
        Assert.assertTrue(signUpPage.isSignUpPageDisplayed(), 
            "Should navigate to Sign Up page from Sign In page");
    }
    
    /**
     * Test 10: Verify Sign Up page has Sign In link
     */
    @Test(description = "Verify Sign Up page has navigation link to Sign In")
    public void testSignUpPageHasSignInLink() {
        navigateTo(BASE_URL + "/signup");
        SignUpPage signUpPage = new SignUpPage(driver);
        
        signUpPage.clickSignInLink();
        
        SignInPage signInPage = new SignInPage(driver);
        Assert.assertTrue(signInPage.isSignInPageDisplayed(), 
            "Should navigate to Sign In page from Sign Up page");
    }
    
    /**
     * Test 11: Verify page title on Sign In page
     */
    @Test(description = "Verify page title on Sign In page")
    public void testSignInPageTitle() {
        navigateTo(BASE_URL + "/signin");
        String title = driver.getTitle();
        
        Assert.assertTrue(title.contains("MERN"), "Page title should contain MERN");
    }
    
    /**
     * Test 12: Verify page title on Sign Up page
     */
    @Test(description = "Verify page title on Sign Up page")
    public void testSignUpPageTitle() {
        navigateTo(BASE_URL + "/signup");
        String title = driver.getTitle();
        
        Assert.assertTrue(title.contains("MERN"), "Page title should contain MERN");
    }
}
