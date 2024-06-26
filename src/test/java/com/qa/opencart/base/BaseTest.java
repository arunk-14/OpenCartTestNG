package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.CommonPage;
import com.qa.opencart.pages.ForgotPasswordPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {

	DriverFactory df;
	protected Properties prop;
	protected WebDriver driver;
	
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected ForgotPasswordPage forgotPasswordPage;
	protected CommonPage commonPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;
	
	protected SoftAssert softAssert;
	
	@Parameters({"browser","browserversion"})
	@BeforeTest
	public void setup(String browser,String browserVersion) throws InterruptedException {
		df = new DriverFactory();
		prop = df.init_prop();
		
		if(browser!=null) {
			prop.setProperty("browser", browser);
			prop.setProperty("browserversion", browserVersion);
		}
		
		driver = df.init_driver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
