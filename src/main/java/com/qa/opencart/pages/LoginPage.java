package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step; 

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	private By logoutMessage = By.xpath("//*[text()='You have been logged off your account. It is now safe to leave the computer.']");
	private By passwordResetMessage = By.xpath("//div[text()=' An email with a confirmation link has been sent your email address.']");

	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	@Step("getting login page title")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(Constants.LOGIN_PAGE_TILTE, Constants.DEFAULT_TIMEOUT);
		System.out.println("Login page title is : "+title);
		return title;
	}
	
	@Step("getting login page url")
	public String getLoginPageURL() {
		String url = eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIMEOUT);
		System.out.println("Login page url is : "+url);
		return url;
	}
	
	@Step("checking forgot password link exist or not")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForElementVisible(forgotPwdLink, Constants.DEFAULT_ELEMENT_WAIT_TIMEOUT).isDisplayed();
	}
	
	@Step("clicking on forgot password link")
	public ForgotPasswordPage clickOnForgotPassword() {
		if(isForgotPwdLinkExist()) {
			eleUtil.doClick(forgotPwdLink);
			return new ForgotPasswordPage(driver);
		}
		return null;
	}
	
	@Step("checking register link exist or not")
	public boolean isRegisterLinkExist() {
		return eleUtil.waitForElementVisible(registerLink, Constants.DEFAULT_ELEMENT_WAIT_TIMEOUT).isDisplayed();
	}
	
	@Step("login with username {0} and password {1}")
	public AccountsPage doLogin(String username, String pwd) {
		eleUtil.waitForElementVisible(emailId, Constants.DEFAULT_ELEMENT_WAIT_TIMEOUT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginButton);
		return new AccountsPage(driver);
	}
	
	@Step("getting success message after logout")
	public String getLogoutMessage() {
		String logoutMseg = eleUtil.waitForElementVisible(logoutMessage, Constants.DEFAULT_ELEMENT_WAIT_TIMEOUT).getText();
		return logoutMseg;
	}
	
	@Step("getting success message after password reset")
	public String getPasswordResetMessage() {
		String passwordResetMseg = eleUtil.waitForElementVisible(passwordResetMessage, Constants.DEFAULT_ELEMENT_WAIT_TIMEOUT).getText();
		return passwordResetMseg;
	}
	
	@Step("navigating to register page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
}
