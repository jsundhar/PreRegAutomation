package com.o2.test;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.o2.pages.*;
import com.o2.data.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class PreRegChecks {
  WebDriver driver;
  AppProperties properties;
  O2HomePage o2HomePage;
  O2ComingSoonPage o2ComingSoonPage;
  PreRegPage preRegPage;
 
  @BeforeTest
  public void beforeTest() {
	 properties = new AppProperties();
	 driver = new FirefoxDriver();
	 driver.manage().timeouts().implicitlyWait(Integer.parseInt(properties.getValue("TIMEOUT")), TimeUnit.SECONDS);	 
	 o2HomePage = new O2HomePage(driver);
	 o2ComingSoonPage = new O2ComingSoonPage(driver);
	 preRegPage = new  PreRegPage(driver);
  }
  
//Provide all mandatory fields and submit the order
  @Test(dataProvider="formData", priority=1)
  public void PreRegistration_positiveflow(String firstName, String lastName, String email, String mobile) {
	  System.out.println("Launching Test");
	  driver.get(properties.getValue("URL"));
	  o2HomePage.moveToShopLink();
	  o2ComingSoonPage.clickGetUpdates();
	  preRegPage.submitPreRegRequest(firstName, lastName, email, mobile);	  
	  if(driver.getCurrentUrl().contains("thankyou"))
		  System.out.println("PreReg order placed successfully");
	  Assert.assertEquals(driver.getCurrentUrl(), "https://www.o2.co.uk/getupdates/thankyou");
  }
  
  @Test(priority=2)
  public void PreRegistration_nagativeflow() {
	  driver.get(properties.getValue("URL"));
	  o2HomePage.moveToShopLink();
	  o2ComingSoonPage.clickGetUpdates();	  
	  
	  //submit without mandatory values
	  preRegPage.submitPreRegForm();
	  Assert.assertNotEquals(preRegPage.validationErrorCount(), 0);
  }
  
  @DataProvider(name="formData")
  public Object[][] formdata () {
	  String excelFileName = System.getProperty("user.dir")
				+ "/src/test/resources/PreRegFormInputs.xlsx";
	  ExcelData forminputs = new ExcelData(excelFileName, 0);
	 //Pass the form inputs from an excel sheet
	 if (forminputs.containsData()) {
		 System.out.println("Feeding inputs from Excel");	
		 return forminputs.getObjectarray();
	 }		 
	 else {
		 //Passing some default values, when excel data can't be fetched
		 System.out.println("Feeding inputs from default data pool");
		 return new Object[][] 
			  {
			  {"firstname","lastname","name@test.com","07702934958"}
			  };
	 }

  }

  @AfterTest
  public void afterTest() {
	  driver.close();
	  driver.quit();
  }

}
