package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By resultsPageHeader = By.cssSelector("div#content h1");
			
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	@Step("getting search results page header")
	public String getResultsPageHeader() {
		return eleUtil.doGetElementText(resultsPageHeader);
	}
	
	@Step("selecting the product {0}")
	public ProductInfoPage selectProductName(String mainProductName) {
		WebElement mainProductElement = eleUtil.waitForElementVisible(By.linkText(mainProductName), Constants.DEFAULT_ELEMENT_WAIT_TIMEOUT);
		mainProductElement.click();
		return new ProductInfoPage(driver);
	}

}
