package com.qa.Opencart.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.Opencart.consonants.AppConsonants;
import com.qa.Opencart.factory.DriverFactory;
import com.qa.Opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class Loginpage {

	private WebDriver driver;
	private ElementUtil eleUtil;


private	By emailId = By.id("input-email");
private	By password = By.id("input-password");
private	By loginBtn = By.xpath("//input[@value='Login']");
private	By logoImage = By.cssSelector("img[title='naveenopencart']");
private	By forgotPwdLink = By.linkText("Forgotten Password");
private By registerlink=By.linkText("Register");
private static final Logger LOG = Logger.getLogger(DriverFactory.class);

	public Loginpage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(driver);

	}
	
	@Step("Waiting for login page title and fetching the title")
	public String getLoginPageTitle() {
	String title=	eleUtil.waitForTitleIs(AppConsonants.DEFAULT_TIME_OUT, AppConsonants.LOGIN_PAGE_TITLE);
		//String title = driver.getTitle();
	LOG.info("browser name is : " + title);
		System.out.println(title);
		return title;
	}
	@Step("Waiting for login page url and fetching the url")
	public boolean getLoginPageUrl() {
	String url=	eleUtil.waitForUrlContains(AppConsonants.DEFAULT_LARGE_TIME_OUT, AppConsonants.LOGIN_PAGE_URL_PARAM);
		//String url = driver.getCurrentUrl();
		System.out.println("login page url : " + url);

		if (url.contains(AppConsonants.LOGIN_PAGE_URL_PARAM)) {

			return true;

		}
		return false;

	}

	@Step("checking forgot pwd link is displayed on login page")
	public boolean forgotPasswordLinkExist() {
		return eleUtil.doEleIsDisplayed(forgotPwdLink);

	}
	@Step("login with username : {0} and password: {1}")
	public Accountpage doLogin(String username, String pwd) {
		System.out.println("user creds are : " + username + " : " + pwd);
	eleUtil.doSendKeysWithWait(emailId, AppConsonants.DEFAULT_LARGE_TIME_OUT, username);
	eleUtil.doSendKeys(password, pwd);
	eleUtil.doClick(loginBtn);
		return new Accountpage(driver);

	}
	
	@Step("navigating to register page")
	public Registrationpage navigateToRegisterPage(){
		System.out.println("navigating to register page.....");
		eleUtil.doClick(registerlink);
		return new Registrationpage (driver);
		
	}

}
