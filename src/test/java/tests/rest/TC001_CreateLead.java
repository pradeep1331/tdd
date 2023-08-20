package tests.rest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import lib.rest.RESTAssuredBase;
import lib.utils.pojo.*;




import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class TC001_CreateLead  extends RESTAssuredBase{
	
	
	String token;
	String CreateLeadID;
	
	
	
	@BeforeTest//Reporting
	public void setValues() {
		testCaseName = "Create a Auth Token";
		testDescription = "Create a new Auth Token";
		nodes = "Lead";
		authors = "SDET 4 Batch 5 ";
		category = "REST";
		dataFileName = "TC0001";
		dataFileType = "JSON";
	}
	
	
	@Test
	public void getToken() {
		
	/*	
		RequestSpecBuilder build = 	new RequestSpecBuilder();
		
		RequestSpecification addHeader = build.setBaseUri("https://login.salesforce.com").addHeader("content-Type"	, "application/x-www-form-urlencoded").build();
				
		TokenPojo tokenpojo = RestAssured.given(addHeader).formParam("grant_type", "password").formParam("client_id", "3MVG9pRzvMkjMb6lZlt3YjDQwe0hk0f5ZPyWD38tfPYQ75KXUzZBGzM_c7I0o3yc6ua1IUk6lEQIy4U2sByrg").formParam("client_secret", "79FFF874D54193B377A60354C71CBBE83120AD28A5D6BC536D2F68C94645DE98").formParam("username", "ranjini.r@testleaf.com").formParam("password", "Testleaf$321")
		.when().post("/services/oauth2/token").then().extract().response().as(TokenPojo.class);
		 
		*/
		
		setToken();
		//System.out.println((tokenpojo.getAccessToken()));
		//token = tokenpojo.getAccessToken();
		
	}
	
	
	@Test(dependsOnMethods="getToken")
	public void CreateLead() {
		
			CreateLeadReq build = CreateLeadReq.builder().firstName("Mohammed").lastName("Asif").company("Company").build();
			
			RestAssured.baseURI = "https://testleaf30-dev-ed.develop.my.salesforce.com";
			
			CreateLeadResp createLeadresp = RestAssured
			.given()
			.auth()
			.oauth2(token)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.when()
			.body(build)
			.post("/services/data/v58.0/sobjects/Lead/").then().extract().as(CreateLeadResp.class);
			
			
			CreateLeadID = createLeadresp.getId();
			
			System.out.println(CreateLeadID);
			
	}
	
	

}
