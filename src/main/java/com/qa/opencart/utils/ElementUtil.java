package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	
	private WebDriver driver;
	
	public ElementUtil(WebDriver driver){
		this.driver = driver;
	}
	
	public By getBy(String locatorType, String loactorValue) {
		By locator = null;
		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(loactorValue);
			break;
		case "name":
			locator = By.name(loactorValue);
			break;
		case "class":
			locator = By.className(loactorValue);
			break;
		case "xpath":
			locator = By.xpath(loactorValue);
			break;
		case "linktext":
			locator = By.linkText(loactorValue);
			break;
		case "partiallinktext":
			locator = By.partialLinkText(loactorValue);
			break;
		case "css":
			locator = By.cssSelector(loactorValue);
			break;
		case "tagname":
			locator = By.tagName(loactorValue);
			break;
		default:
			System.out.println("plese pass right locator type");
			break;
		}
		return locator;
	}
	
	public void doActionsSendKeys(By locator, String text) {
		Actions acts = new Actions(driver);
		acts.sendKeys(getElement(locator),text).perform();
	}
	
	public void doActionsClick(By locator) {
		Actions acts = new Actions(driver);
		acts.click(getElement(locator)).perform();
	}
	
	public void doSendKeys(By locator,String value) {
		getElement(locator).sendKeys(value);;
	}
	
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	
	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public String doGetElementText(By locator) {
		return getElement(locator).getText();
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public  String doGetAttributeValue(By locator, String attributeName) {
		return getElement(locator).getAttribute(attributeName);
	}
	
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public void clickOnListElementFromSection(By locator, String value) {
		List<WebElement> elementList = getElements(locator);
		for(WebElement e : elementList) {
			String text = e.getText();
			if(text.equals(value)) {
				e.click();
				break;
			}
		}	
	}
	
	public int getElementsListCount(By locator) {
		List<WebElement> elementList = getElements(locator);
		return elementList.size();
	}
	
	public ArrayList<String> getElementsTextList(By locator) {
		ArrayList<String> elementTextList = new ArrayList<String>();
		List<WebElement> elementList = getElements(locator);
		for(WebElement e : elementList) {
			String text = e.getText();
			elementTextList.add(text);
		}	
		return elementTextList;
	}
	
	public void printAllElementsText(By locator) {
		List<WebElement> elementList = getElements(locator);
		for(WebElement e : elementList) {
			String text = e.getText();
			System.out.println(text);
		}	
	}
	
	public void doMoveToElement(By locator) {
		Actions acts = new Actions(driver);
		acts.moveToElement(getElement(locator)).build().perform();
	}
	
	public void doDragAndDrop(By draggable_locator, By droppable_locator) {
		Actions acts = new Actions(driver);
		acts.clickAndHold(getElement(draggable_locator)).moveToElement(getElement(droppable_locator)).release().build().perform();
	}
	
	public void rightClickOnElement(By locator) {
		Actions acts = new Actions(driver);
		acts.contextClick(getElement(locator)).perform();
	}
	
	public void handleAlertPopUp(String action) {
		Alert alert = driver.switchTo().alert();
		switch (action.toLowerCase()) {
		case "accept" :
			alert.accept();
			break;
		case "dismiss" : 
			alert.dismiss();
			break;
		default:
			System.out.println("plese pass right action type");
			break;
		}
	}
	
	public void enterTextToPrompt(String text) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(text);
	}
	
// ########## DROP DOWN LIST METHODS ##########
	
		public void doSelectDropDownByIndex(By locator, int index) {
			Select select = new Select(getElement(locator));
			select.selectByIndex(index);
		}
		
		public void doSelectDropDownByValue(By locator, String value) {
			Select select = new Select(getElement(locator));
			select.selectByValue(value);
		}
		
		public void doSelectDropDownByVisibleText(By locator, String text) {
			Select select = new Select(getElement(locator));
			select.selectByVisibleText(text);
		}
		
		public int getSelectDropDownValueCount(By locator) {
			return getElements(locator).size();
		}
		
		public ArrayList<String> getSelectDropDownValuesList(By locator){
			ArrayList<String> optionsValue = new ArrayList<String>();
			Select select = new Select(getElement(locator));
			List<WebElement> options = select.getOptions();
			for(WebElement e : options) {
				String text = e.getText();
				optionsValue.add(text);
			}	
			return optionsValue;
		}
		
		public void selectValueFromDropDown(By locator, String value) {
			Select select = new Select(getElement(locator));
			List<WebElement> options = select.getOptions();
			for(WebElement e : options) {
				String text = e.getText();
				if(text.contains(value)) {
					e.click();
					break;
				}
			}
		}
		
		public void printSelectDropDownValues(By locator){
			Select select = new Select(getElement(locator));
			List<WebElement> options = select.getOptions();
			for(WebElement e : options) {
				String text = e.getText();
				System.out.println(text);
			}	
		}
		
		public void selectValueFromDropDownWithXpath(By locator,String value) {
			List<WebElement> options = getElements(locator);
			for(WebElement e : options) {
				String text = e.getText();
				if(text.contains(value)) {
					e.click();
					break;
				}
			}
		}
	
// ***** WAIT UTILS **** //
		
		/*
		 * An exception for checking that the title contains a case-sensitive substring
		 * 
		 * @param titleFraction
		 * @param timeOut
		 * @return
		 */
		public String waitForTitleContains(String titleFraction, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			if(wait.until(ExpectedConditions.titleContains(titleFraction))) {
				return driver.getTitle();
			}
			return null;
		}
		
		/*
		 * An exception for checking the title of a page
		 * 
		 * @param titleValue
		 * @param timeOut
		 * @return
		 */
		public String waitForTitleIs(String titleValue, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			if(wait.until(ExpectedConditions.titleIs(titleValue))) {
				return driver.getTitle();
			}
			return null;
		}
		
		/*
		 * An exception for checking that the url contains a case-sensitive substring
		 * 
		 * @param urlFraction
		 * @param timeOut
		 * @return
		 */
		public String waitForUrlContains(String urlFraction, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			if(wait.until(ExpectedConditions.urlContains(urlFraction))) {
				return driver.getCurrentUrl();
			}
			return null;
		}
		
		/*
		 * An exception for checking the url of a page
		 * 
		 * @param urlValue
		 * @param timeOut
		 * @return
		 */
		public String waitForUrlIs(String urlValue, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			if(wait.until(ExpectedConditions.urlToBe(urlValue))) {
				return driver.getCurrentUrl();
			}
			return null;
		}
		
		/*
		 * An exception for checking that an element is present on the DOM of a page
		 * This does not mean that the element is visible
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForElementPresent(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		
		/*
		 * An exception for checking that an element is present on the DOM of a page
		 * and visible. Visibility means that the element is not only displayed but also has a height and width that is greater than 0
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForElementVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
}
