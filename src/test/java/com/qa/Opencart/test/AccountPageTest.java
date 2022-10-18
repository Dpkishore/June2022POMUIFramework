package com.qa.Opencart.test;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.Opencart.Pages.searchResultPage;
import com.qa.Opencart.base.BaseTest;
import com.qa.Opencart.consonants.AppConsonants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("epic--200: Open cart application Account page design")
@Story("Us--201 Design Account page features.....")
public class AccountPageTest extends BaseTest {
	@BeforeClass
	public void accSetup(){
	accPage=loginPage.doLogin(prop.getProperty("usernmae"), prop.getProperty("password"));
	System.out.println(accPage);
	}
	
	@Description("accpageTitleTest---Dev Name:pavan kishore")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void accpageTitleTest(){
	String AccPageTitle=accPage.getAccPageTitle();
	System.out.println(AccPageTitle);
	Assert.assertEquals(AccPageTitle, AppConsonants.ACC_PAGE_TITLE);
	}
	
	@Description("accpageUrlTest---Dev Name:pavan kishore")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void accpageUrlTest(){
	Assert.assertTrue(accPage.getAccPageUrl());
	}
	
	@Description("accpagesearchExistTest---Dev Name:pavan kishore")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void searchExitTset(){
	Assert.assertTrue(accPage.isSearchExist());
	}
	@Description("accpageLogoutLinkExistTest username and pwd---Dev Name:pavan kishore")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=4)
	public void logoutLinkExistTest(){
	Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	
	@Description("accpageHeaderTest---Dev Name:pavan kishore")
	@Severity(SeverityLevel.TRIVIAL)
	@Test(priority=5)
	public void accPageHeaderTest(){
		ArrayList<String>accHeaderList=accPage.getActSectionHeaderList();
	System.out.println("Account HeaderList::::"+accHeaderList);
	Assert.assertEquals(accHeaderList, AppConsonants.ACC_PAGE_SECTIONS_HEADERS);
	}
	
	@DataProvider
	public Object[][] getProductKey(){
		return new Object[][]{
			{ "Macbook"},
			{ "iMac"},
			{"Samsung"}
			};
			
		
		
	}
	
	@Description("accpageProductSearchTest---Dev Name:pavan kishore")
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider="getProductKey",priority=6)
	public void searchCheckTest(String productKey){
	searchResultsPage=accPage.PerformSearch(productKey);
	Assert.assertTrue(searchResultsPage.isSearchSuccessful());
		
	}
	
	@DataProvider
	public Object[][] getProductData(){
		return new Object[][] {
			{ "Macbook", "MacBook Pro" },
			{ "Macbook", "MacBook Air" },
			{ "iMac", "iMac" },
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Samsung", "Samsung Galaxy Tab 10.1"}
		};
		
	}
	@Description("accpageSearchTest---Dev Name:pavan kishore")
	@Severity(SeverityLevel.BLOCKER)
	@Test(dataProvider="getProductData",priority=7)
	public void searchTest(String searchKey,String mainProductName){
	searchResultsPage=accPage.PerformSearch(searchKey);
	if(searchResultsPage.isSearchSuccessful()){
	productInfoPage=searchResultsPage.selectProduct(mainProductName);
	String actProductHeader=productInfoPage.getProductHeader(mainProductName);
	Assert.assertEquals(actProductHeader,mainProductName );
		
	}
		
	}

}
