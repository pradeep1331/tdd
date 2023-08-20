package pages.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lib.selenium.PreAndPost;

public class SalesForceLoginPage extends PreAndPost {
	
	
	
	public SalesForceLoginPage(EventFiringWebDriver driver, ExtentTest test) {	
		this.driver = driver;
		this.test = test;
		
		PageFactory.initElements(driver,this);
	}
	
	
	
	@FindBy(id="username") 
	private WebElement UserName;	
	
	@FindBy(id="password")
	private WebElement PassWord;	
	
	@FindBy(id="Login")
	private WebElement LoginButton;

	
	
	public SalesForceLoginPage typeUserName(String username) {	
		type(UserName, username);
		return this;
	}	

	
	public SalesForceLoginPage typePassword(String password) {
		type(PassWord, password);
		return this;
	}	
	
	
	public SalesForceLoginPage clickLogIn() {
		click(LoginButton);
		return this;		
	}
	
	
	
	

}
