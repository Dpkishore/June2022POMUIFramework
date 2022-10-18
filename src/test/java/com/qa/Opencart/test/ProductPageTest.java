package com.qa.Opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.Opencart.base.BaseTest;
import com.qa.Opencart.consonants.AppConsonants;

public class ProductPageTest extends BaseTest {
	@BeforeClass
	public void prodInfoSetup(){
	accPage=loginPage.doLogin(prop.getProperty("usernmae"), prop.getProperty("password"));
		
	}
	
	@DataProvider
	public Object[][] getProductKey(){
		return new Object[][]{
			{ "macbook"},
			{ "MacBook Pro"},
			{"MacBook Pro"},
			
			};
			
		
		
	}
	
	
	
	
	@Test(dataProvider="getProductKey")
	public void productHeaderTest(String ProductKey){
		searchResultsPage=accPage.PerformSearch(ProductKey);
		//productInfoPage=searchResultsPage.selectProduct(mainProductName);
	//	String actProductHeader=productInfoPage.getProductHeader(mainProductName);
		//Assert.assertEquals(actProductHeader,mainProductName);
		
		Assert.assertTrue(searchResultsPage.isSearchSuccessful());
		
	}
	
	
	@DataProvider
	public Object[][] getProductInfoData() {
		return new Object[][] {
				{ "Macbook", "MacBook Pro" , AppConsonants.MACBOOK_PRO_IMAGES_COUNT},
				{ "Macbook", "MacBook Air" , AppConsonants.MACBOOK_AIR_IMAGES_COUNT},
				{ "iMac", "iMac" , AppConsonants.IMAC_IMAGES_COUNT},
				};
	}

	
	@Test(dataProvider="getProductInfoData")
	public void productImagesCountTest(String searchKey,String mainProductName, int ImagesCount){
		searchResultsPage=accPage.PerformSearch(searchKey);
		productInfoPage=searchResultsPage.selectProduct(mainProductName);
	int actParoductImages=productInfoPage.getProductImagesCount();
	System.out.println("Account prouduct Images:::"+actParoductImages);
	Assert.assertEquals(actParoductImages,   ImagesCount);
	}
	@Test
	public void productMetaDataTest(){
		searchResultsPage=accPage.PerformSearch("Macbook");
		productInfoPage =searchResultsPage.selectProduct("MacBook Pro");
		Map<String,String>actMetaDataMap =productInfoPage.getProductMetaData();
		Assert.assertEquals(actMetaDataMap.get("Brand"),  "Apple");
		Assert.assertEquals(actMetaDataMap.get("Product Code"),  "Product 18");
		Assert.assertEquals(actMetaDataMap.get("Reward Points"), "800");
		Assert.assertEquals(actMetaDataMap.get("Availability"), "In Stock");
	}
	

}
