package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.CommonPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class ProductInfoTest extends BaseTest{
	
	@BeforeClass
	public void prodductInfoSetup(){
		commonPage = new CommonPage(driver);
	}
	
	@DataProvider
	public Object[][] getProductName() {
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Samsung"},
			{"Apple"}
		};
	}

	@Test(dataProvider = "getProductName")
	@Description("Verify user is able to search the product")
	@Severity(SeverityLevel.NORMAL)
	public void searchTest(String productName) {
		searchResultsPage = commonPage.doSearch(productName);
		String resultsPageHeader = searchResultsPage.getResultsPageHeader();
		Assert.assertTrue(resultsPageHeader.contains(productName));
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"Macbook","MacBook Pro"},
			{"Macbook","MacBook Air"},
			{"Samsung","Samsung SyncMaster 941BW"},
		};
	}
	
	@Test(dataProvider = "getProductData")
	@Description("Verify product information page")
	@Severity(SeverityLevel.NORMAL)
	public void productInfoTest(String productName, String mainProductName) {
		searchResultsPage = commonPage.doSearch(productName);
		String resultsPageHeader = searchResultsPage.getResultsPageHeader();
		productInfoPage = searchResultsPage.selectProductName(mainProductName);
		String mainProductNameVale = productInfoPage.getMainProductName();
		Assert.assertEquals(mainProductNameVale, mainProductName);
	}
	
	@DataProvider
	public Object[][] getProductImagesCount() {
		return new Object[][] {
			{"Macbook","MacBook Pro",4},
			{"Macbook","MacBook Air",4},
			{"Samsung","Samsung SyncMaster 941BW",1},
		};
	}
	
	@Test(dataProvider = "getProductImagesCount")
	@Description("Verify number of images in product information page")
	@Severity(SeverityLevel.NORMAL)
	public void productImagesCountTest(String productName, String mainProductName, int imagesCount) {
		searchResultsPage = commonPage.doSearch(productName);
		productInfoPage = searchResultsPage.selectProductName(mainProductName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), imagesCount);
	}
	
	@Test(dataProvider = "getProductData")
	@Description("Verify product description in product information page")
	@Severity(SeverityLevel.NORMAL)
	public void productDescriptionTest(String productName, String mainProductName) {
		searchResultsPage = commonPage.doSearch(productName);
		productInfoPage = searchResultsPage.selectProductName(mainProductName);
		String prodDesc = productInfoPage.getProductDescription();
		softAssert.assertTrue(prodDesc!=null);
		softAssert.assertFalse(prodDesc.isEmpty());
		softAssert.assertAll();
		
	}
}
