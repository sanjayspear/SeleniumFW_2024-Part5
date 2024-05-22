package com.dhruv.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlipkartHomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public FlipkartHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(text(),'✕')]")
    private WebElement closeLoginPopup;

    @FindBy(xpath = "//img[@alt='Fashion']")
	public WebElement fashionThumbnail;

    @FindBy(xpath = "//a[text()='Men Footwear']")
	public WebElement menFootwear;

    @FindBy(xpath = "//a[text()=\"Men's Formal Shoes\"]")
	public WebElement mensFormalShoes;

    public void closeLoginPopup() {
        try {
            wait.until(ExpectedConditions.visibilityOf(closeLoginPopup)).click();
        } catch (Exception e) {
            // Popup not present, do nothing
        }
    }

    public void hoverOnFashion() {
        wait.until(ExpectedConditions.visibilityOf(fashionThumbnail));
    }

    public void hoverOnMenFootwear() {
        wait.until(ExpectedConditions.visibilityOf(menFootwear));
    }

    public void hoverOnMensFormalShoes() {
        wait.until(ExpectedConditions.visibilityOf(mensFormalShoes));
    }

    public void clickMensFormalShoes() {
        wait.until(ExpectedConditions.elementToBeClickable(mensFormalShoes)).click();
    }
}
