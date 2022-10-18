package com.qa.Opencart.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.Opencart.consonants.AppConsonants;
import com.qa.Opencart.util.ElementUtil;

public class searchResultPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By ProductSearchLayout=By.cssSelector("div.product-layout");

	public searchResultPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	
	}
	public boolean isSearchSuccessful(){
		List<WebElement>searchList=eleUtil.waitForElementsToBeVisible(ProductSearchLayout, AppConsonants.DEFAULT_LARGE_TIME_OUT);
		if(searchList.size()>0){
			System.out.println("Search is successfully done.....");
			return true;
		}
		
		return false;
	}
	public ProductInfoPage selectProduct(String mainProductName){
		By mainPrName = By.linkText(mainProductName);
		eleUtil.doClick(mainPrName);
		return new ProductInfoPage(driver);
		
	}

}
