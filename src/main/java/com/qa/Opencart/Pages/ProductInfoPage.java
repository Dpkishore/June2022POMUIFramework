package com.qa.Opencart.Pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.Opencart.consonants.AppConsonants;
import com.qa.Opencart.util.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> productInfoMap;

	private By ProductImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	public String getProductHeader(String mainProductName) {
		String xpath = "//h1[text()='" + mainProductName + "']";
		String productHeader = eleUtil.doGetText(By.xpath(xpath));
		System.out.println("product header is : " + productHeader);
		return productHeader;

	}

	public int getProductImagesCount() {
		return eleUtil.waitForElementsToBeVisible(ProductImages, AppConsonants.DEFAULT_LARGE_TIME_OUT).size();
	}

	public String getProductPageTitle(String ProductTitle) {
		return eleUtil.waitForTitleIs(AppConsonants.DEFAULT_TIME_OUT, ProductTitle);
	}
	public void getProductPageURL(String SearchKey){
		eleUtil.waitForTitleContains(AppConsonants.DEFAULT_TIME_OUT, SearchKey);
	}
	public Map<String,String> getProductMetaData(){
	List<WebElement>metalist=eleUtil.getElements(productMetaData);
	productInfoMap =new LinkedHashMap<String,String>();
	for(WebElement e:metalist){
		String metaText = e.getText();
		String meta[] = metaText.split(":");
		String metaKey = meta[0].trim();
		String metaValue = meta[1].trim();
		productInfoMap.put(metaKey, metaValue);
	}
	productInfoMap.forEach((k,v) -> System.out.println(k+ ":" +v));
	return productInfoMap;
		
	}
}
