package com.dhruv.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.dhruv.helper.GenericUtils;

public class LoginPage {
	private WebDriver driver;
	
	@FindBy(id = "inputUsername")
	private WebElement usernameField;

	@FindBy(name = "inputPassword")
	private WebElement passwordField;

	@FindBy(className = "signInBtn")
	private WebElement signInButton;

	@FindBy(className = "error")
	private WebElement errorMessage;

	@FindBy(xpath = "//a[text()='Forgot your password?']")
	private WebElement forgotPasswordLink;

	@FindBy(xpath = "//input[@placeholder='Name']")
	private WebElement Name;
	
	@FindBy(xpath = "//input[@placeholder='Email']")
	private WebElement Email;
	
	@FindBy(xpath = "//input[@placeholder='Phone Number']")
	private WebElement phoneNumber;
	
	@FindBy(xpath = "//button[text()='Reset Login']")
	private WebElement resetButton;
	
	@FindBy(xpath = "//button[text()='Go to Login']")
	private WebElement goToLoginPage;
	
	@FindBy(xpath = "//p[@class='infoMsg']")
	private WebElement successfulResetMsg;
	
	@FindBy(xpath = "//p[contains(text(),'successfully')]")
	private WebElement successfulLoginMSG;
	
	@FindBy(xpath = "//button[text()='Log Out']")
	private WebElement logoutBtn;
	
	@FindBy(xpath = "//header/div/descendant::button[text()='Signup']/preceding-sibling::button[2]")
	private WebElement practiceBtn;
	
	@FindBy(xpath = "//header/div/button[text()='Practice']/following-sibling::button[1]")
	private WebElement loginButtonTextBlue;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String loginWithInvalidCred(String un, String pwd) {
		usernameField.sendKeys(un);
		passwordField.sendKeys(pwd);
		signInButton.click();
		return errorMessage.getText();
	}

	public String forgotPWD(String name, String email, String ph) throws InterruptedException {
		GenericUtils.waitForElementToBeClickable(driver, forgotPasswordLink, 10);
		forgotPasswordLink.click();
		Name.sendKeys(name);
		Email.sendKeys(email);
		phoneNumber.sendKeys(ph);
		GenericUtils.waitForElementToBeClickable(driver, resetButton, 10);
		resetButton.click();
		String successMSg = successfulResetMsg.getText();
		Thread.sleep(3000);
		goToLoginPage.click();
		String signInPage = signInButton.getText();
		return signInPage + "||" +successMSg;
	}
	
	public String validtedToCheckForTheSuccessfulLogin(String un, String pwd) {
		usernameField.sendKeys(un);
		passwordField.sendKeys(pwd);
		signInButton.click();
		GenericUtils.waitForElementToBeVisible(driver, successfulLoginMSG, 10);
		String successfullyLoggedInMSGText = successfulLoginMSG.getText();
		return successfullyLoggedInMSGText;
	}
	
	public String validateForLoginAndPracticeString() {
		String practiceBtnText = practiceBtn.getText();
		String loginBtnBlueText = loginButtonTextBlue.getText();
		return practiceBtnText+" "+loginBtnBlueText;
	}
	
	public String logout() {
		logoutBtn.click();
		String signInText = signInButton.getText();
		return signInText;
	}
}
