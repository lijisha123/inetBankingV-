package com.inetbanking.testCases;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddNewCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass{
	
	@Test
	public void addNewCustomer() throws InterruptedException {
		
		LoginPage lp=new LoginPage(driver);
		lp.setUsername(username);
		logger.info("username is provided");
		lp.setPassword(password);
		logger.info("password is provided");
		lp.clickSubmit();
		AddNewCustomerPage addcust=new AddNewCustomerPage(driver);
		addcust.clickNewcusBtn();
		//driver.switchTo().alert().accept();
		logger.info("providing customer details");
		addcust.custNamefield("Lijisha");
		addcust.custgender("female");
		addcust.custdob("23", "02", "1990");
		Thread.sleep(3000);
		addcust.custAddress("India");
		addcust.custCity("Palakkad");
		addcust.custstate("kerala");
		addcust.custpinno(5655555);
		addcust.custmobno("864566456");
		Thread.sleep(3000);
		String email=getRandomEmail();
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.clicksubmit();
		Thread.sleep(3000);
		logger.info("validation started...");
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("Testcase passed");
		}
		else
		{
			Assert.assertTrue(false);
			logger.info("Test case failed");
		}
	}
	
	public static String getRandomEmail() {
	    String alphabet = "abcdefghijklmnopqrstuvwxyz";
	    StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    int length = 5;
	    for (int i = 0; i < length; i++) {
	        int index = random.nextInt(alphabet.length());
	        char randomChar = alphabet.charAt(index);
	        sb.append(randomChar);
	    }
	    String randomStringEmail = sb.toString() + "@gmail.com";
	    return randomStringEmail;
	}

}
