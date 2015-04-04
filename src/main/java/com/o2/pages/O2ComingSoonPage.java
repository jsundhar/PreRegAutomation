package com.o2.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class O2ComingSoonPage {
	
	WebDriver driver;
	@FindBy(css="p.product-cta")
	WebElement lnkGetUpdates;
	
	public O2ComingSoonPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickGetUpdates() {		
		lnkGetUpdates.click();		
	}	
}
