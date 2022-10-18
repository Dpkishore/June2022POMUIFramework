package com.qa.Opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.Opencart.base.BaseTest;
import com.qa.Opencart.consonants.AppConsonants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("epic--100: Open cart application login page design")
@Story("Us--101 Design login page features.....")
public class PageTest extends BaseTest{
	
	
	
	@Description("login page title Test....")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginpagetitleTest(){
		
		String actualTitle=loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle,AppConsonants.LOGIN_PAGE_TITLE);
		
	}
	
	@Description("login page url Test....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void LoginPageUrlTest(){
		Assert.assertTrue(loginPage.getLoginPageUrl());
	}
	@Description("login page displaying forgot pwd Test....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void isForgotpwdExitTest(){
	Assert.assertEquals(loginPage.forgotPasswordLinkExist(),true);
	}
	@Description("login test correct usernamr and pwd....")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("usernmae"),prop.getProperty("password"));
	}
}


