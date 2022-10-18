package com.qa.Opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.Opencart.error.Apperrors;
import com.qa.Opencart.exception.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	
	

	private static final Logger LOG = Logger.getLogger(DriverFactory.class);

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static String highlight;
	public OptionManager optionManager;

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser").toLowerCase();
		System.out.println("Browser name:::" + browserName);
		//browserName = browserName.toLowerCase();
		LOG.info("browser name is : " + browserName);

		highlight = prop.getProperty("highlight").trim();
		optionManager = new OptionManager(prop);

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver=new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionManager.getChromeOption()));

		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());

		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			// driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
		} else {
			System.out.println("Plz pass the right Browser:::" + browserName);
			
			LOG.error("Please pass the right browser name : " + browserName);
			throw new FrameworkException(Apperrors.BROWSER_NOT_FOUND);
			
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();

		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;

		// String envName=System.getenv("env");

		String envName = System.getProperty("env");

		System.out.println("----->Running Testcases On environment ::" + envName);
		LOG.info("-----> Running test cases on environment: ----->"+ envName) ;
		if (envName == null) {
			System.out.println("----No env running.Run on qa environment------");

			try {
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				switch (envName) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");

					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");

					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");

					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");

					break;

				case "prod":
					ip = new FileInputStream("./src/test/resources/config/pod.config.properties");

					break;

				default:
					System.out.println("plz pass the right browser:::" + envName);
					
					throw new FrameworkException(Apperrors.ENV_NOT_FOUND);
					//break;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		/*
		 * try { FileInputStream ip=new
		 * FileInputStream("./src/test/resources/config/config.properties");
		 * prop.load(ip); } catch (FileNotFoundException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch (IOException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	public static String getScreenshot() {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
