package com.o2.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class O2HomePage {
	
	WebDriver driver;
	@FindBy(id="pn1")
	WebElement shopLink;
	@FindBy(linkText="Coming soon")
	WebElement lnkComingSoon;
	
	public O2HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void moveToShopLink() {
		Actions action = new Actions(driver);
		action.moveToElement(shopLink).build().perform();
		lnkComingSoon.click();		
	}
	

}
