package tests.rest;

import java.io.File;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;


public class TC003_CreateChangeRequest extends RESTAssuredBase{
	
	@BeforeTest//Reporting
	public void setValues() {
		testCaseName = "Create a new Change Request (REST)";
		testDescription = "Create a new Change Request and Verify";
		nodes = "Change Request";
		authors = "Sarath";
		category = "REST";
		//dataProvider
		dataFileName = "TC003";
		dataFileType = "JSON";
	}

	@Test(dataProvider = "fetchData")
	public void createCR(File file) {		
				
		Response response = postWithBodyAsFileAndUrl(file, "change_request");
		
		verifyContentType(response, "application/json");
		
		verifyResponseCode(response, 201);
		
		verifyContentWithKey(response, "result.short_description", "This is Rest Assured Automation framework - Makaia");
		sysID = getContentWithKey(response, "result.sys_id");
	}


}




















