package com.qa.Opencart.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.Opencart.consonants.AppConsonants;
import com.qa.Opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class Accountpage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
private	By LogoutLink=By.linkText("Logout");
private	By Search=By.name("search");
private	By searchIcon=By.cssSelector("div#search button");
private	By accSectionHeader=By.cssSelector("div#content h2");
	
	public Accountpage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(driver);
	}
	@Step("getAccPageTitle......")
	public String getAccPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConsonants.DEFAULT_TIME_OUT, AppConsonants.ACC_PAGE_TITLE);
		//String title = driver.getTitle();
		System.out.println("Acc page title &&&&&: " + title);
		return title;
	}
	@Step("getAccPageUrl.....")
	public boolean getAccPageUrl() {
		String url = eleUtil.waitForUrlContains(AppConsonants.DEFAULT_TIME_OUT, AppConsonants.ACC_PAGE_URL_PARAM);
		//String url = driver.getCurrentUrl();
		System.out.println("Acc page url : " + url);
		if(url.contains(AppConsonants.ACC_PAGE_URL_PARAM)){
			return true;
		}
		return false;
		
	}
	@Step("isLogoutLinkExist.....")
	public boolean isLogoutLinkExist() {
		return eleUtil.doEleIsDisplayed(LogoutLink);
	}
	
	@Step("isSearchExist.....")
	public boolean isSearchExist() {
		return eleUtil.doEleIsDisplayed(Search);
	}	
	@Step("performSearch.....{0}")
	public searchResultPage PerformSearch(String productKey){
		System.out.println("ProductKey....."  +productKey);
		if(isSearchExist()){
			eleUtil.doSendKeys(Search, productKey);
			eleUtil.doClick(searchIcon);
			return new searchResultPage(driver);
			
		}
		else {
			System.out.println("search field is not present on the page.....");
			return null;
		}
		
	}
	
	
	
	
	
	
	
	@Step("getAccSecHeadersList.....")
	public ArrayList<String>getActSectionHeaderList(){
		
		
		List<WebElement> secList = eleUtil.waitForElementsToBeVisible(accSectionHeader, AppConsonants.DEFAULT_LARGE_TIME_OUT);
		
		
		
	//	List<WebElement>secList=driver.findElements(accSectionHeader);
		System.out.println("secList is...."+secList.size());
		ArrayList<String>AccSecTextList=new ArrayList<String>();
		
		
		for(WebElement e:secList){
		String text=e.getText();
		AccSecTextList.add(text);
		}
		return AccSecTextList;
		
	}

}
