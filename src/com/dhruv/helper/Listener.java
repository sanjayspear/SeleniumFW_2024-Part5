package com.dhruv.helper;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    private String browser;

    @Override
    public void onStart(ITestContext context) {
        browser = context.getCurrentXmlTest().getParameter("browser");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseClass) currentClass).initializeDriver(browser);
        // Screenshot Code
        String testName = result.getMethod().getMethodName();
        GenericUtils.captureScreenshot(driver, testName);

        // Print the name of the failed test case
        System.out.println("Test failed and screenshot of the failed test is captured. Test case name is: " + testName);
    }

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

    // Other overridden methods like onTestStart, onTestSuccess, etc.
}
