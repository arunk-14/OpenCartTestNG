package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class ForgotPasswordPageTest extends BaseTest{
	
	@BeforeClass
	public void navigateToForgotPasswordPage() {
		forgotPasswordPage = loginPage.clickOnForgotPassword();
	}

	@Test(priority=1)
	@Description("Verify forgot password page title")
	@Severity(SeverityLevel.NORMAL)
	public void forgotPasswordPageTitleTest() {
		String actualTitle = forgotPasswordPage.getForgotPasswordPageTitle();
		Assert.assertEquals(actualTitle, Constants.FORGOT_PASSWORD_PAGE_TILTE);
	}
	
	@Test(priority=2)
	@Description("Verify forgot password page url")
	@Severity(SeverityLevel.CRITICAL)
	public void forgotPasswordPageURLTest() {
		String actualUrl = forgotPasswordPage.getForgotPasswordPageURL();
		Assert.assertTrue(actualUrl.contains(Constants.FORGOT_PASSWORD_PAGE_URL_FRACTION));
	}
	
	@Test(priority=3)
	@Description("Verify back button exist in forgot password page")
	@Severity(SeverityLevel.NORMAL)
	public void backButtonExistTest() {
		Assert.assertTrue(forgotPasswordPage.isBackButtonExist());
	}
	
	@Test(priority=4)
	@Description("Verify continue button exist in forgot password page")
	@Severity(SeverityLevel.NORMAL)
	public void continueButtonExistTest() {
		Assert.assertTrue(forgotPasswordPage.isContinueButtonExist());
	}
	
	@Test(priority=5)
	@Description("Verify message in forgot password page")
	@Severity(SeverityLevel.NORMAL)
	public void forgotPasswordMessageTest() {
		String actualMesg = forgotPasswordPage.getForgotPasswordMessage();
		Assert.assertEquals(actualMesg, Constants.FORGOT_PASSWORD_MESSAGE);
	}
	
	@Test(priority=6)
	@Description("Verify user is able to initiate the password reset")
	@Severity(SeverityLevel.BLOCKER)
	public void forgotPasswordTest() {
		LoginPage loginPage = forgotPasswordPage.doForgotPasswordReset(prop.getProperty("username").trim());
		String accPageTitle = loginPage.getLoginPageTitle();
		String passwordResetMesg = loginPage.getPasswordResetMessage();
		Assert.assertEquals(passwordResetMesg, Constants.PASSWORD_RESET_MESSAGE);
		Assert.assertEquals(accPageTitle, Constants.LOGIN_PAGE_TILTE);
	}
}
