package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.customexception.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This method is used to initialize the driver on the basis of given browser name
	 * @param browserName
	 * @return This method will return webDriver
	 * @throws InterruptedException 
	 */
	public WebDriver init_driver(Properties prop) throws InterruptedException {
		
		String browserName = prop.getProperty("browser").trim();
		System.out.println("Browser name is : "+ browserName);
		optionsManager = new OptionsManager(prop);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote execution
				init_remoteDriver("chrome");
			} else {
				// local execution
				WebDriverManager.chromedriver().setup();
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			
		} else if(browserName.equalsIgnoreCase("firefox")) {
			if(Boolean.parseBoolean("remote")) {
				// remote execution
				init_remoteDriver("firefox");
			} else {
				// local execution
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
			}
			
		} else if(browserName.equalsIgnoreCase("safari")) {
			tlDriver.set(new SafariDriver());
		} else{
			System.out.println("Please pass a valid browser name : "+ browserName);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	private void init_remoteDriver(String browserName) {
		System.out.println("Running tests on remote grid server: "+ browserName);
		if(browserName.equalsIgnoreCase("chrome")) {
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getChromeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else if (browserName.equalsIgnoreCase("firefox")) {
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getFireFoxOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/*
	 * This method is used to initialize the properties from the environment config file
	 * @return this returns properties class object with all the config properties
	 */
	
	public Properties init_prop() {
		FileInputStream ip = null;
		prop = new Properties();
		
		// maven command line args:
		// mvn clean install -Denv="qa"
		String envName = System.getProperty("env");
		System.out.println("Running test on environment: "+envName);
		
		if(envName==null) {
			System.out.println("No environment is given, hence running test in qa environment");
			envName = "qa";
			try {
				ip = new FileInputStream("./src/test/resource/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resource/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resource/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resource/config/stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resource/config/uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resource/config/config.properties");
					break;
				default:
					System.out.println("Please pass the right environment...."+envName);
					throw new FrameworkException("No environment found");
				}
			} catch(FileNotFoundException | FrameworkException e) {
				e.printStackTrace();
			}
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = "./screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
