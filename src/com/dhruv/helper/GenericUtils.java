package com.dhruv.helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericUtils {

    @SuppressWarnings("deprecation")
	public static void waitForElementToBeClickable(WebDriver driver, WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @SuppressWarnings("deprecation")
	public static void waitForElementToBeVisible(WebDriver driver, WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    @SuppressWarnings("deprecation")
	public static void waitForListOfWebElementsToBeVisible(WebDriver driver, List<WebElement> element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }
    
    public static String getDropdown(WebElement staticDropdown, String dropDownOption) {
    	Select dropdown = new Select(staticDropdown);
    	dropdown.selectByVisibleText(dropDownOption);
    	String optionSelectedFromDropdown = dropdown.getFirstSelectedOption().getText();
    	return optionSelectedFromDropdown;
    }
    
 // Method to capture screenshot
    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        // Create a TakesScreenshot object
        TakesScreenshot ts = (TakesScreenshot) driver;
        
        // Capture the screenshot and store it as a file format
        File source = ts.getScreenshotAs(OutputType.FILE);
        
        // Add timestamp to the screenshot name
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        
        // Create the destination file path
        String dest = System.getProperty("user.dir") + "/screenshots/" + screenshotName + "_" + timestamp + ".png";
        File destination = new File(dest);
        
     // Check if the directory exists, if not, create it
        File screenshotDir = new File(System.getProperty("user.dir") + "/screenshots/");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdir();
        }
        
        try {
            // Copy the screenshot to the desired location
            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot captured: " + dest);
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    // You can add more utility methods for other wait conditions as needed
}

