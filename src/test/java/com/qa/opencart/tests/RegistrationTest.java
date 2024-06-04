package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class RegistrationTest extends BaseTest{

	@BeforeClass
	public void regsSetup(){
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmailId() {
		Random random = new Random();
		String email = "testcart"+random.nextInt(10000)+"@gmail.com";
		return email;
	}
	
	@DataProvider
	public Object[][] getRegisterTestData() {
		Object regsData[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regsData;
	}
	
	@Test(dataProvider="getRegisterTestData")
	@Description("Verify user registartion")
	@Severity(SeverityLevel.BLOCKER)
	public void userRegisterTest(String firstName, String lastName,String telephone, String password, String subscribe) {
		Assert.assertTrue(registerPage.userRegister(firstName,lastName,getRandomEmailId(),telephone,password,subscribe));
	}
}
