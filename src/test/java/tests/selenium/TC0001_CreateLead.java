package tests.selenium;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import lib.selenium.PreAndPost;
import pages.selenium.LoginPage;
import pages.selenium.SalesForceLoginPage;

public class TC0001_CreateLead extends PreAndPost {
	
	
	@BeforeTest
	public void setValues() {

		testCaseName = "Create Incident (Using Selenium)";
		testDescription = "Create a new Incident";
		nodes = "Incident Management";
		authors = "Sarath";
		category = "UI";
		dataSheetName = "TC002";

	}
	
	@Test(dataProvider = "fetchData")
	public void CreateLead(String filter, String user, String short_desc) {
		
		new SalesForceLoginPage(driver, test)
		.typeUserName(user)
		.typePassword(short_desc)
		.clickLogIn();
		
		
	}
	
	
}
