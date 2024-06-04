package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic(value = "EPIC -01: Design login page for open cart application")
@Story(value = "US - 01: Design login page features")
public class LoginPageTest extends BaseTest{
	
	@Test(priority=1)
	@Description("Verify login page title")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, Constants.LOGIN_PAGE_TILTE);
	}
	
	@Test(priority=2)
	@Description("Verify login page url")
	@Severity(SeverityLevel.CRITICAL)
	public void loginPageURLTest() {
		String actualUrl = loginPage.getLoginPageURL();
		Assert.assertTrue(actualUrl.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}

	@Test(priority=3)
	@Description("Verify forgot password link exist")
	@Severity(SeverityLevel.CRITICAL)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test(priority=4)
	@Description("Verify register link exist")
	@Severity(SeverityLevel.CRITICAL)
	public void registerLinkExistTest() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
	
	@Test(priority=5)
	@Description("Verify user is able to login")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		AccountsPage accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		String accPageTitle = accPage.getAccPageTitle();
		Assert.assertTrue(accPage.isLogoutLinkExist());
		Assert.assertEquals(accPageTitle, Constants.ACCOUNT_PAGE_TILTE);
	}
}
