package com.qa.Opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.Opencart.Pages.Accountpage;
import com.qa.Opencart.Pages.Loginpage;
import com.qa.Opencart.Pages.ProductInfoPage;
import com.qa.Opencart.Pages.Registrationpage;
import com.qa.Opencart.Pages.searchResultPage;
import com.qa.Opencart.factory.DriverFactory;

public class BaseTest {

	DriverFactory df;
	public WebDriver driver;
	public Properties prop;
	public Loginpage loginPage;
	public Accountpage accPage;
	public searchResultPage searchResultsPage;
	public ProductInfoPage productInfoPage;
	public Registrationpage registrationpage;

	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new Loginpage(driver);
	}

	@AfterTest
	public void teatDown() {
		driver.quit();
	}

}
