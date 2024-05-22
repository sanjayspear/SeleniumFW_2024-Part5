package com.dhruv.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.dhruv.helper.BaseClass;
import com.dhruv.pageObjects.LoginPage;

import io.qameta.allure.Step;

public class LoginTest_002 extends BaseClass {
	LoginPage loginPage;

	@Step("Test Case 008")
	@Test(priority = 1)
	public void testInvalidLogin() {
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		loginPage = new LoginPage(driver);
		String errorMessageText = loginPage.loginWithInvalidCred("rahul", "rahul123");
		System.out.println("The Error Message Is: " + errorMessageText);
		Assert.assertEquals(errorMessageText, "* Incorrect username or password", "Error message did not match");
	}

	@Step("Test Case 009")
	@Test(priority = 2)
	public void forgotPasswordTest() throws InterruptedException {
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
	}

}
