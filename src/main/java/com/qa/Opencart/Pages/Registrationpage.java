package com.qa.Opencart.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.Opencart.consonants.AppConsonants;
import com.qa.Opencart.util.ElementUtil;

public class Registrationpage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By firstName=By.id("input-firstname");
	private By lastName=By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");
	
	private By agreeCheckBox = By.name("agree");
	private By continueButton=By.xpath("//input[@type='submit' and @value='Continue']");
	private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@value='1' and @type='radio']");
	private By subscribeNo=By.xpath("//label[@class='radio-inline']/input[@value='0' and @type='radio']");
	private By regSuccMsg=By.cssSelector("div#content h1");
	private By logoutlink=By.linkText("Logout");
	private By registerlink=By.linkText("Register");
	
	public Registrationpage(WebDriver driver){
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
		
	}
	public  String userRegister(String firstName,String lastName,String email, String telephone, String password, String subscribe){
		eleUtil.doSendKeysWithVisibleElement(this.firstName, AppConsonants.DEFAULT_LARGE_TIME_OUT, firstName);
		
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmpassword, password);
		if(subscribe.endsWith("Yes")){
			eleUtil.doClick(this.subscribeYes);
		}else{
			eleUtil.doClick(this.subscribeNo);
		}
		eleUtil.doClick(this.agreeCheckBox);
		eleUtil.doClick(this.continueButton);
		String succMsg=eleUtil.waitForElementVisible(regSuccMsg, AppConsonants.DEFAULT_LARGE_TIME_OUT).getText();
		System.out.println("Success Messsggggg=====>" + succMsg);
		
		eleUtil.doClick(logoutlink);
		eleUtil.doClick(registerlink);
		
		
		return succMsg;
		
	}
}
