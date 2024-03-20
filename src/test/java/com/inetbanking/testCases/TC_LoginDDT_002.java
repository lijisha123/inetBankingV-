package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.Excel;

public class TC_LoginDDT_002 extends BaseClass
{
	@Test(dataProvider="LoginData")
	public void loginDDT(String user,String pwd) throws InterruptedException {
		LoginPage lp=new LoginPage(driver);
		lp.setUsername(user);
		logger.info("username is provided");
		lp.setPassword(pwd);
		logger.info("password is provided");
		lp.clickSubmit();
		Thread.sleep(3000);
		
		if(isAlertpresent()==true)
		{
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed");
		}
		else
		{
			Assert.assertTrue(true);
			logger.info("login passed");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();//close logout alert
			driver.switchTo().defaultContent();
		}
		
		
	}
	
	public boolean isAlertpresent()//user defined method created to check alert is present or not
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/inetBanking.xlsx";
		
		int rownum=Excel.getRowCount(path, "sheet1");
		int cocount=Excel.getCellCount(path, "sheet1", 1);
		
		String logindata[][]=new String[rownum][cocount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<cocount;j++)
			{
				logindata[i-1][j]=Excel.getCellData(path, "sheet1", i, j);//1 0
			}
		}
		return logindata;
	}

}
