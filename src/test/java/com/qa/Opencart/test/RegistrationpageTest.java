package com.qa.Opencart.test;

import java.util.Random;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.Opencart.Pages.Registrationpage;
import com.qa.Opencart.base.BaseTest;
import com.qa.Opencart.consonants.AppConsonants;
import com.qa.Opencart.util.ExcelUtil;



public class RegistrationpageTest extends BaseTest{
	@BeforeClass
	public void regSetup() {
		registrationpage = loginPage.navigateToRegisterPage();
	}
	
	
	@DataProvider
	public Object[][] getRegTestData()   {
		Object regData[][];
		
			regData = ExcelUtil.getTestData(AppConsonants.REGISTER_SHEET_NAME);
	
		return regData;
	}
	public String getRandomEmail() {
		Random random = new Random();
		String email = "automationtest"+random.nextInt(10000)+"@gmail.com";
		return email;
	}
	
	@Test(dataProvider = "getRegTestData")
	public void registerUserTest(String firstName, String lastName,String email, String telephone, String password, String subscribe) {
				
		String actSuccMessg = registrationpage.userRegister(firstName, lastName,getRandomEmail(), telephone, password, subscribe);
		Assert.assertEquals(actSuccMessg, AppConsonants.ACC_CREATE_SUCC_MESSG);
	}

}
