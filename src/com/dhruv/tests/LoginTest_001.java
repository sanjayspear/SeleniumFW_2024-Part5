package com.dhruv.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.dhruv.helper.BaseClass;
import com.dhruv.pageObjects.LoginPage;

import io.qameta.allure.Step;

public class LoginTest_001 extends BaseClass {
	LoginPage loginPage;
	@Step("Test Case 006")
	@Test
	public void testInvalidLogin() {
		logger.info("Starting testLoginWithInvalidCred test method");
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		loginPage = new LoginPage(driver);
		String errorMessageText = loginPage.loginWithInvalidCred("rahul", "rahul123");
		System.out.println("The Error Message Is: " + errorMessageText);
		Assert.assertEquals(errorMessageText, "* Incorrect username or password", "Error message did not match");
		logger.info("Finished testLoginWithInvalidCred test method successfully");
	}

	@Step("Test Case 007")
	@Test
	public void forgotPasswordTest() throws InterruptedException {
		logger.info("Starting testForgotPWD test method");
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		loginPage = new LoginPage(driver);
		String result = loginPage.forgotPWD("test", "test@test.com", "6783245908");

		// Split the result into two parts
		String[] resultParts = result.split("\\|\\|");
		String signInPageText = resultParts[0];
		String successMsg = resultParts[1];

		System.out.println("Expected text is: " + successMsg);
		Assert.assertEquals(successMsg, "Please use temporary password 'rahulshettyacademy' to Login.",
				"Text Did not match");

		System.out.println("Expected text is: " + signInPageText);
		Assert.assertEquals(signInPageText, "SIGN IN", "Text Did not match");
		logger.info("Finished testForgotPWD test method successfully");
	}

}
