package com.qa.opencart.tests;


import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


public class AccountsPageTest extends BaseTest{

	@BeforeClass
	public void accSetup(){
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test(priority=1)
	@Description("Verify account page title")
	@Severity(SeverityLevel.NORMAL)
	public void accPageTitleTest() {
		String actualTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actualTitle, Constants.ACCOUNT_PAGE_TILTE);
	}
	
	@Test(priority=2)
	@Description("Verify account page header")
	@Severity(SeverityLevel.NORMAL)
	public void accPageHeaderTest() {
		String accPageHeader = accPage.getAccPageHeader();
		Assert.assertEquals(accPageHeader, Constants.ACCOUNTS_PAGE_HEADER);
	}
	
	@Test(priority=3)
	@Description("Verify account page url")
	@Severity(SeverityLevel.CRITICAL)
	public void accPageURLTest() {
		String actualUrl = accPage.getAccPageUrl();
		Assert.assertTrue(actualUrl.contains(Constants.ACCOUNT_PAGE_URL_FRACTION));
	}
	
	@Test(priority=4)
	@Description("Verify account page section header")
	@Severity(SeverityLevel.CRITICAL)
	public void accPageSectionsHeaderTest() {
		List<String> accAccSecList = accPage.getAccSectionList();
		System.out.println("Actual Accounts Page Section Headers : "+ accAccSecList);
		Assert.assertEqualsNoOrder(accAccSecList, Constants.ACCOUNTS_PAGE_SECTION_HEADER_LIST);
	}
	
	@Test(priority=5)
	@Description("Verify user is able to logout")
	@Severity(SeverityLevel.CRITICAL)
	public void isUserLoggedOutTest() {
		accPage.clickOnLogout();
		String logoutMseg = loginPage.getLogoutMessage();
		Assert.assertEquals(logoutMseg, Constants.LOGOUT_MESSAGE);
	}

}
