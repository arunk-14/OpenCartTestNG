package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class ForgotPasswordPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By forgotPasswordMseg = By.xpath("//*[text()='Enter the e-mail address associated with your account. Click submit to have a password reset link e-mailed to you.']");
	private By emailId =  By.id("input-email");
	private By continueButton = By.xpath("//input[@value='Continue']");
	private By backButton = By.linkText("Back");
	
	public ForgotPasswordPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(this.driver);
	}

	@Step("getting forgot password page title")
	public String getForgotPasswordPageTitle() {
		String title = eleUtil.waitForTitleIs(Constants.FORGOT_PASSWORD_PAGE_TILTE, Constants.DEFAULT_TIMEOUT);
		System.out.println("Forgot password page title is : "+title);
		return title;
	}
	
	@Step("getting forgot password page url")
	public String getForgotPasswordPageURL() {
		String url = eleUtil.waitForUrlContains(Constants.FORGOT_PASSWORD_PAGE_URL_FRACTION, Constants.DEFAULT_TIMEOUT);
		System.out.println("Forgot password page url is : "+url);
		return url;
	}
		
	@Step("checking forgot password message exist or not")
	public boolean isForgotPassswordMessageExist() {
		return eleUtil.waitForElementVisible(forgotPasswordMseg, Constants.DEFAULT_ELEMENT_WAIT_TIMEOUT).isDisplayed();
	}
		
	@Step("getting forgot password message")
	public String getForgotPasswordMessage() {
		if(isForgotPassswordMessageExist()) {
			return eleUtil.doGetElementText(forgotPasswordMseg);
		}
		return null;
	}
		
	@Step("checking continue button exist or not")
	public boolean isContinueButtonExist() {
		return eleUtil.waitForElementVisible(continueButton, Constants.DEFAULT_ELEMENT_WAIT_TIMEOUT).isDisplayed();
	}
		
	@Step("checking back button exist or not")
	public boolean isBackButtonExist() {
		return eleUtil.waitForElementVisible(backButton, Constants.DEFAULT_ELEMENT_WAIT_TIMEOUT).isDisplayed();
	}
		
	@Step("clicking on back button")
	public LoginPage clickOnBackButton() {
		if(isBackButtonExist()) {
			eleUtil.doClick(backButton);
			return new LoginPage(driver);
		}
		return null;
	}
		
	@Step("initiating the password reset request")
	public LoginPage doForgotPasswordReset(String username) {
		eleUtil.waitForElementVisible(emailId, Constants.DEFAULT_ELEMENT_WAIT_TIMEOUT).sendKeys(username);
		eleUtil.doClick(continueButton);
		return new LoginPage(driver);
	}
}
