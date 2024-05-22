package com.dhruv.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dhruv.helper.BaseClass;
import com.dhruv.pageObjects.LoginPage;

import io.qameta.allure.Step;

public class LoginTest_003 extends BaseClass {
	LoginPage loginPage;
	
	@Step("Test Case 10")
    @Test(priority = 1)
    public void testInvalidLogin() {
    	driver.get("https://rahulshettyacademy.com/locatorspractice/");
    	loginPage = new LoginPage(driver);
        String errorMessageText = loginPage.loginWithInvalidCred("rahul", "rahul123");
        System.out.println("The Error Message Is: " + errorMessageText);
        Assert.assertEquals(errorMessageText, "* Incorrect username or password", "Error message did not match");
    }
    
	@Step("Test Case 11")
    @Test(priority = 2, dependsOnMethods = {"testInvalidLogin"})
    public void forgotPasswordTest() throws InterruptedException{
    	driver.get("https://rahulshettyacademy.com/locatorspractice/");
    	loginPage = new LoginPage(driver);
    	String result = loginPage.forgotPWD("test", "test@test.com", "6783245908");
    	
    	// Split the result into two parts
        String[] resultParts = result.split("\\|\\|");
        String signInPageText = resultParts[0];
        String successMsg = resultParts[1];
        
        System.out.println("Expected text is: " + successMsg);
    	Assert.assertEquals(successMsg, "Please use temporary password 'rahulshettyacademy' to Login.", "Text Did not match");
        
    	System.out.println("Expected text is: " + signInPageText);
    	Assert.assertEquals(signInPageText, "SIGN IN", "Text Did not match");
    }
    
	@Step("Test Case 12")
    @Parameters({"username", "password"})
    @Test(priority = 3)
    	public void successfulLoginTest(String un, String pwd) {
    	  driver.get("https://rahulshettyacademy.com/locatorspractice/");
    	  System.out.println(un+" "+pwd);
    	  loginPage = new LoginPage(driver);
    	  String actualSuccessMSG = loginPage.validtedToCheckForTheSuccessfulLogin(un, pwd);
    	  String expectedMSG = "You are successfully logged in.";
    	  Assert.assertEquals(actualSuccessMSG, expectedMSG, "Success text Did not match");
    	  String loggedOutMSG = loginPage.logout();
    	  Assert.assertEquals(loggedOutMSG, "SIGN IN");
    
	}
	@Step("Test Case 13")
    @Test(priority = 4)
    public void validateForTheLoginBtnTextAndPracticeBtnText() {
    	driver.get("https://rahulshettyacademy.com/AutomationPractice/");
    	loginPage = new LoginPage(driver);
    	String actualText = loginPage.validateForLoginAndPracticeString();
    	String text[] = actualText.split(" ");
    	String actualPracticeText = text[0];
    	String actualLoginText = text[1];
    	System.out.println("Extracted Texts are "+text[0]+" and "+text[1]);
    	Assert.assertEquals(actualPracticeText, "Practice", "Text Did not match");
    	Assert.assertEquals(actualLoginText, "Login", "Text Did not match");
    	
    }
}
