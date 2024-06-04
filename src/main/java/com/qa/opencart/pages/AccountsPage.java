package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By header = By.cssSelector("div#logo h1 a");
	private By accountSectionsHeader = By.cssSelector("div#content h2");
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	@Step("getting account page title")
	public String getAccPageTitle(){
		return eleUtil.waitForTitleIs(Constants.ACCOUNT_PAGE_TILTE, Constants.DEFAULT_TIMEOUT);
	}
	
	@Step("getting account page url")
	public String getAccPageUrl(){
		return eleUtil.waitForUrlContains(Constants.ACCOUNT_PAGE_URL_FRACTION, Constants.DEFAULT_TIMEOUT);
	}
	
	@Step("getting account page header")
	public String getAccPageHeader(){
		return eleUtil.waitForElementVisible(header, Constants.DEFAULT_ELEMENT_WAIT_TIMEOUT).getText();
	}
	
	@Step("getting account page section list")
	public List<String> getAccSectionList(){
		List<WebElement> accSecList = eleUtil.waitForElementsVisible(accountSectionsHeader, Constants.DEFAULT_ELEMENT_WAIT_TIMEOUT);
		List<String> accSecValueList = new ArrayList<String>();
		for(WebElement e : accSecList) {
			String text = e.getText();
			accSecValueList.add(text);
		}
		return accSecValueList;
	}
	
	@Step("checking logout link exist or not")
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, Constants.DEFAULT_ELEMENT_WAIT_TIMEOUT).isDisplayed();
	}
	
	@Step("clicking on logout link")
	public LoginPage clickOnLogout() {
		if(isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);
			return new LoginPage(driver);
		}
		return null;
	}
	
	@Step("checking search field exist or not")
	public boolean isSearchkExist() {
		return eleUtil.doIsDisplayed(search);
	}
}
