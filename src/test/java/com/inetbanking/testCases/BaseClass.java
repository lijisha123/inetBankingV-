package com.inetbanking.testCases;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.inetbanking.utilities.ReadConfig;
import com.inetbanking.utilities.Screenshot;




public class BaseClass {
	ReadConfig readconfig=new ReadConfig();
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getusername();
	public String password=readconfig.getpassword();
	public static WebDriver driver;
	public static Logger logger;
	public static Screenshot ss;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {
		
		logger=Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver",readconfig.getchromePath());
		driver=new ChromeDriver();
		}
	else if(br.equals("firefox")){
		System.setProperty("webdriver.gecko.driver",readconfig.getfirefoxPath());
		driver=new FirefoxDriver();
	}
	else if(br.equals("msedge")){
		System.setProperty("webdriver.msedge.driver",readconfig.getEdgePath());
		driver=new EdgeDriver();
	}	
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(baseURL);
		driver.manage().window().maximize();
	}
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult itestResult) throws IOException {

		if (itestResult.getStatus() == ITestResult.FAILURE) {
			ss =new Screenshot();
			ss.captureFailureScreenShot(driver, username);
		}
	}
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	}
