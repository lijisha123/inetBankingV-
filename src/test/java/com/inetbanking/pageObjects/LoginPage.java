package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver lDriver;
	public LoginPage(WebDriver rDriver) {
		lDriver=rDriver;
		PageFactory.initElements(rDriver, this);
		}
	
	@FindBy(name="uid")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(xpath="//a[text()='Log out']")
	@CacheLookup
	WebElement Logout;
	public void setUsername(String uname) {
		txtUserName.sendKeys(uname);
		}
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
		}
	public void clickSubmit (){
		btnLogin.click();
		}
	public void clickLogout() {
		Logout.click();
		
	}
}
