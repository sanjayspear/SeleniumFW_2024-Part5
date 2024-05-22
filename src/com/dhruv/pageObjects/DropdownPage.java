package com.dhruv.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.dhruv.helper.GenericUtils;

public class DropdownPage {
	private WebDriver driver;

	public DropdownPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//select[contains(@name, 'ctl00$mainContent$Drop')]")
	WebElement currencyDropdownObject;

	@FindBy(xpath = "//div[@id='divpaxinfo']")
	WebElement passengersDropdown;

	@FindBy(xpath = "//span[@id='hrefIncAdt']")
	WebElement addPassengerAdult;

	@FindBy(xpath = "//input[@id='btnclosepaxoption']")
	WebElement doneBtn;
	
	@FindBy(xpath = "(//div[@id='marketCityPair_1']//label)[1]/parent::div/following-sibling::input/parent::div[@class='left1']")
	WebElement spiceJetFromDD;
	
	@FindBy(xpath = "(//div[@id='marketCityPair_1']//label)[2]/parent::div/following-sibling::input/parent::div[@class='right1']")
	WebElement spiceJetToDD;
	
	@FindBy(xpath = "//input[@id='autosuggest']")
	WebElement countryInputFieldDropdown;
	
	@FindBy(xpath = "//li[@class='ui-menu-item']//a")
	List<WebElement> countryList;
	
	// Method to get dynamic locator for 'from' city option
    private By getFromOptionByValue(String value) {
        return By.xpath("//div[@class='left1']//ul//a[@value='" + value + "']");
    }

    // Method to get dynamic locator for 'to' city option
    private By getToOptionByText(String text) {
        return By.xpath("//div[@class='right1']//ul//a[text()=' " + text + "']");
    }

    // Method to select 'from' city
    public String selectFromCity(String fromCityCode) {
    	spiceJetFromDD.click();
        driver.findElement(getFromOptionByValue(fromCityCode)).click();
        String fromCity = driver.findElement(getFromOptionByValue(fromCityCode)).getText();
        return fromCity;
    }

    // Method to select 'to' city
    public String selectToCity(String toCityText) {
    	spiceJetToDD.click();
        driver.findElement(getToOptionByText(toCityText)).click();
        String toCity = driver.findElement(getToOptionByText(toCityText)).getText();
        return toCity;
    }

	public String currencyDropdown(String Currency) {
		String actualSelectedDropdown = GenericUtils.getDropdown(currencyDropdownObject, Currency);
		return actualSelectedDropdown;
	}

	public String passengerDropdown(int addCount) {
		passengersDropdown.click();
		for (int count = 0; count < addCount; count++) {
			addPassengerAdult.click();
		}
		doneBtn.click();
    	String optionSelectedFromDropdown = passengersDropdown.getText();
    	return optionSelectedFromDropdown;
	}
	
	public String countryAutosuggestiveDD(String countryName) {
		String option = null;
		countryInputFieldDropdown.sendKeys(countryName);
		GenericUtils.waitForListOfWebElementsToBeVisible(driver, countryList, 10);
		for(WebElement country : countryList) {
			if(country.getText().equalsIgnoreCase("Indonesia")) {
				option = country.getText();
				country.click();
				break;
			}
		}
		return option;
	}
}
