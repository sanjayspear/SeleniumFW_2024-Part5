package com.dhruv.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.dhruv.helper.BaseClass;
import com.dhruv.pageObjects.FlipkartHomePage;

import io.qameta.allure.Step;

public class FlipkartHomePageTest extends BaseClass {

	@Step("Test Case 001")
    @Test
    public void testActionsOnFlipkart() {
        driver.get("https://www.flipkart.com/");
        
        FlipkartHomePage homePage = new FlipkartHomePage(driver);
        Actions actions = new Actions(driver);

        homePage.closeLoginPopup();

        actions.moveToElement(homePage.fashionThumbnail).perform();
        actions.moveToElement(homePage.menFootwear).perform();
        actions.moveToElement(homePage.mensFormalShoes).perform();
        homePage.clickMensFormalShoes();

        String actualText = driver.findElement(By.xpath("//h1[text()='Men’s Formal Shoes']")).getText();
        Assert.assertEquals(actualText, "Men’s Formal Shoes", "Test Failed: Landing page text mismatch");

        // Composite Actions
        driver.navigate().to("https://www.google.com");
        WebElement searchArea = driver.findElement(By.xpath("//textarea[@title='Search']"));
        actions.moveToElement(searchArea).click().keyDown(Keys.SHIFT).sendKeys("Hello World!").build().perform();
        
        //Context Click or right click
        actions.moveToElement(searchArea).contextClick().build().perform();
    }
}
