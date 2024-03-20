package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewCustomerPage {
	public static WebDriver lDriver;
	public AddNewCustomerPage(WebDriver rDriver) {
		lDriver=rDriver;
		PageFactory.initElements(rDriver, this);
		}

	@FindBy(xpath="//a[contains(text(),'New Customer')]")
	@CacheLookup
	WebElement btnNewCstmr;
	
	@FindBy(xpath="//tbody/tr[4]/td[2]/input[1]")
	@CacheLookup
	WebElement txtCustName;
	
	@FindBy(xpath="//input[@name='rad1'][2]")
	@CacheLookup
	WebElement rdbtnGender;
	
	@FindBy(xpath="//input[@id='dob']")
	@CacheLookup
	WebElement txtDOB;
	

	@FindBy(xpath="//textarea[@name='addr']")
	@CacheLookup
	WebElement txtaddr;
	

	@FindBy(xpath="//input[@name='city']")
	@CacheLookup
	WebElement txtcity;
	
	@FindBy(xpath="//input[@name='state']")
	@CacheLookup
	WebElement txtstate;
	
	@FindBy(xpath="//input[@name='pinno']")
	@CacheLookup
	WebElement txtpin;
	
	@FindBy(xpath="//input[@name='telephoneno']")
	@CacheLookup
	WebElement txtmobno;
	
	@FindBy(xpath="//input[@name='emailid']")
	@CacheLookup
	WebElement txtemailid;
	
	@FindBy(xpath="//input[@name='password']")
	@CacheLookup
	WebElement txtpwd;
	
	@FindBy(xpath="//input[@name='sub']")
	@CacheLookup
	WebElement btnsubmit;
	
	public void clickNewcusBtn() {
		btnNewCstmr.click();
	}
	
	public void custNamefield(String cname) {
		txtCustName.sendKeys(cname);
	}
	public void custgender(String cgender) {
		rdbtnGender.click();
	}
	public void custdob(String mm,String dd,String yy) {
		txtDOB.sendKeys(mm);
		txtDOB.sendKeys(dd);
		txtDOB.sendKeys(yy);
	}
	public void custAddress(String caddress) {
		txtaddr.sendKeys(caddress);
	}
	public void custCity(String ccity) {
		txtcity.sendKeys(ccity);
	}
	public void custstate(String cstate) {
		txtstate.sendKeys(cstate);
	}
	public void custpinno(int cpinno) {
		txtpin.sendKeys(String.valueOf(cpinno));
	}
	public void custmobno(String cmobno) {
		txtmobno.sendKeys(cmobno);
	}
	public void custemailid(String cemailid) {
		txtemailid.sendKeys(cemailid);
	}
	public void custpassword(String cpwd) {
		txtpwd.sendKeys(cpwd);
	}
	public void clicksubmit() {
		btnsubmit.click();
	}
}
