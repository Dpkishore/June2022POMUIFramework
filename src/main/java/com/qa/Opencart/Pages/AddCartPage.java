package com.qa.Opencart.Pages;

import org.openqa.selenium.By;

public class AddCartPage {
	String name="pavan";
	By loc=By.id("product");
	
	
	public void click(){
		System.out.println("click on this"+loc);
	}

}
