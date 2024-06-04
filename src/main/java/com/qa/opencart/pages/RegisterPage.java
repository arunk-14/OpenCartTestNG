package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@type='radio' and @value='1']");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@type='radio' and @value='0']");
	private By agreeCheckBox = By.xpath("//input[@type='checkbox' and @name='agree']");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By registerSuccessMesg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	@Step("registering the user {2}")
	public boolean userRegister(String firstName, String lastName, String email, String telephone,String password,String subscribe) {
		eleUtil.doSendKeys(this.firstName, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		} else eleUtil.doClick(subscribeNo);
		
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		String successMesg = eleUtil.waitForElementVisible(registerSuccessMesg, Constants.DEFAULT_ELEMENT_WAIT_TIMEOUT).getText();
		System.out.println("Register message "+successMesg);
		if(successMesg.contains(Constants.REGISTRATION_SUCCESS_MESSAGE)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		} 
		else {
			eleUtil.doClick(registerLink);
			return false;
		} 
	}
}
