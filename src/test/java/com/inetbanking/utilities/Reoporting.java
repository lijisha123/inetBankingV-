package com.inetbanking.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reoporting extends TestListenerAdapter{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	    public void onStart(ITestContext testContext) {
	        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	        sparkReporter = new ExtentSparkReporter(".\\reports\\" + "Test-Report-" + timeStamp + ".html");
	        sparkReporter.config().setDocumentTitle("BankingAutomationProject");
	        sparkReporter.config().setReportName("BankingTest");
	        sparkReporter.config().setTheme(Theme.DARK);

	        extent = new ExtentReports();
	        extent.attachReporter(sparkReporter);
	        extent.setSystemInfo("Application", "InetBanking");
	        extent.setSystemInfo("OS Name", System.getProperty("os.name"));
	        extent.setSystemInfo("User Name", System.getProperty("user.name"));
	        extent.setSystemInfo("Environment", "QA");
	        extent.setSystemInfo("user", "Lijisha");
	    }

	    public void onTestSuccess(ITestResult result) {
	        test = extent.createTest(result.getName());
	        test.assignCategory(result.getMethod().getGroups());
	        test.log(Status.PASS, "Test Passed");

	        String throwableMessage = result.getThrowable() != null ? result.getThrowable().getMessage() : "No exception";
	        test.log(Status.PASS, throwableMessage);
	        test.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
	    }

	    public void onTestFailure(ITestResult result){
	        test = extent.createTest(result.getName());
	        test.assignCategory(result.getMethod().getGroups());
	        test.log(Status.FAIL, "Test Failed");
	        test.log(Status.FAIL, result.getThrowable().getMessage());
	        test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));

	        String screenshotPath = System.getProperty("user.dir") + "\\screenshot\\" + result.getName() + ".png"; // Corrected path
	        File screenshotFile = new File(screenshotPath);

	        try {
	            if (screenshotFile.exists()) {
	                test.fail("Screenshot is below:" + test.addScreenCaptureFromPath(screenshotPath));
	            } else {
	                test.fail("Screenshot not found. Path: " + screenshotPath);
	            }
	        } catch (Exception e) {
	            e.printStackTrace(); // Add proper exception handling
	        }
	    }

	    
	        public void onTestSkipped(ITestResult result) {
	        test = extent.createTest(result.getName());
	        test.assignCategory(result.getMethod().getGroups());
	        test.log(Status.SKIP, "Test Skipped");
	        test.log(Status.SKIP, result.getThrowable().getMessage());
	    }

	    public void onFinish(ITestContext Result) {
	        System.out.println("On finish invoked..");
	        extent.flush();
	    }
	   }


