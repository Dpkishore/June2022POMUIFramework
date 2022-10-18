package com.qa.Opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;



public class OptionManager {
	private Properties prop;
	private ChromeOptions co;
	public OptionManager(Properties prop){
		this.prop=prop;
		
	}
	public ChromeOptions getChromeOption(){
		co=new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))){
			co.setHeadless(true);
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito"))){
			co.addArguments("--incognito");
		}
		return co;
	}

}
