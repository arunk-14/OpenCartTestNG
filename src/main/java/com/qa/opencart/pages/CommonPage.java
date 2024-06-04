package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class CommonPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By search = By.xpath("//input[@name='search']");
	private By searchIcon = By.cssSelector("div#search button");
		
	public CommonPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	@Step("searching for the product {0}")
	public SearchResultsPage doSearch(String productName) {
		WebElement searchElement = eleUtil.waitForElementVisible(search, Constants.DEFAULT_ELEMENT_WAIT_TIMEOUT);
		searchElement.clear();
		searchElement.sendKeys(productName);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(this.driver);
	}
}
