package com.dhruv.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.dhruv.helper.BaseClass;
import com.dhruv.pageObjects.DropdownPage;

import io.qameta.allure.Step;

public class DropdownTest_004 extends BaseClass {
	DropdownPage dropdownPage;
	@Step("Test Case 002")
	@Test
	public void currencyDrodownTest() {
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		dropdownPage = new DropdownPage(driver);
		String actualOption = dropdownPage.currencyDropdown("USD");
		System.out.println("Selected Option Is " + actualOption);
		Assert.assertEquals(actualOption, "USD");
	}
	@Step("Test Case 003")
	@Test
	public void passengerDrodownTest() {
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		dropdownPage = new DropdownPage(driver);
		int count = 2;
		String actualSelectedPassengerOption = dropdownPage.passengerDropdown(count);
		System.out.println("Selected Option Is " + actualSelectedPassengerOption);
		Assert.assertEquals(actualSelectedPassengerOption, (count + 1) + " Adult");
	}

	@Step("Test Case 004")
	@Test
	public void testDropdownSelection() {
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		dropdownPage = new DropdownPage(driver);
		String selectedFromCity = dropdownPage.selectFromCity("AMD"); // Change 'AMD' to any other city code as needed
		System.out.println("From City: " + selectedFromCity);
		Assert.assertEquals(selectedFromCity.trim(), "Ahmedabad (AMD)");
		String selectedToCity = dropdownPage.selectToCity("Bengaluru (BLR)"); // Change 'Bengaluru (BLR)' to any other
																				// city name as needed
		System.out.println("To City: " + selectedToCity);
		Assert.assertEquals(selectedToCity.trim(), "Bengaluru (BLR)");
	}

	@Step("Test Case 005")
	@Test()
	public void testAutoSuggestiveCountryDD() throws InterruptedException {
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/#");
		dropdownPage = new DropdownPage(driver);
		String actualCountryName = dropdownPage.countryAutosuggestiveDD("Ind");
		Thread.sleep(1000);
		Assert.assertEquals(actualCountryName, "Indonesia", "There is a mismatch between actual and expected");
	}

}
