package lib.rest;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;
import lib.utils.pojo.TokenPojo;
import net.minidev.json.JSONObject;

public class RESTAssuredBase extends PreAndTest{
	
	public static HashMap<String , String> Token_map;

	public static RequestSpecification setLogs() {
		Properties prob = new Properties();
		
		RequestSpecification log = null;
		try {
			prob.load(new FileInputStream("src/test/resources/config.properties"));
			log = RestAssured
					.given()
					.auth()
					.basic(prob.getProperty("username"),prob.getProperty("password"))
					.log().all().contentType(ContentType.JSON);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return log;
	}
	
	
	
	
	public static void setToken() {
		Properties prob = new Properties();
		
		RequestSpecification log = null;
		
		HashMap <String , String> formParam = new HashMap<String , String>();

		
		try {
			prob.load(new FileInputStream("src/test/resources/config.properties"));
			
			
			
			formParam.put("grant_type", prob.getProperty("grant_type"));
			formParam.put("client_id", prob.getProperty("client_id"));
			formParam.put("client_secret", prob.getProperty("client_secret"));
			formParam.put("username", prob.getProperty("Username"));
			formParam.put("password", prob.getProperty("Password"));
			
			
			
			
			RequestSpecBuilder build = 	new RequestSpecBuilder();
			
			RequestSpecification addHeader = build.setBaseUri(prob.getProperty("BaseURL_Token")).addHeader("content-Type"	,prob.getProperty("Token_content_Type")).build();
					
			TokenPojo tokenpojo = RestAssured.given(addHeader).formParams(formParam)
			.when().log().all().post(prob.getProperty("PathParam_Token")).then().extract().response().as(TokenPojo.class);
			 
			System.out.println(tokenpojo.getAccessToken());
			//System.out.println((tokenpojo.getAccessToken()));
			Token_map.put("Token" ,tokenpojo.getAccessToken());
			
			System.out.println(Token_map);
			
			
			
			
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	

	public static Response get(String URL) {
		return setLogs()
				.get(URL);
	}


	public static Response get() {
		return setLogs()
				.get();
	}

	public static Response getWithHeader(Map<String, String> headers, String URL) {

		return setLogs()
				.when()
				.headers(headers)
				.get(URL);
	}
	
	public static Response getWithHeader(Header header, String URL) {

		return setLogs()
				.when()
				.header(header)
				.get(URL);
	}

	public static Response post() {

		return setLogs()
				.post();
	}

	public static Response post(String URL,ContentType type) {

		return setLogs()
				.contentType(type)
				.post(URL);
	}

	public static Response postWithBodyAsFile(File bodyFile) {

		return setLogs()
				.body(bodyFile)
				.post();
	}

	public static Response postWithBodyAsFileAndUrl(File bodyFile, String URL) {

		return setLogs()
				.body(bodyFile)
				.post(URL);
	}
	
	public static Response postWithHeaderAndForm(Map<String, String> headers,
			JSONObject jsonObject, String URL) {

		return setLogs()
				.headers(headers)
				.body(jsonObject)
				.post(URL);
	}

	public static Response postWithJsonAsBody(String jsonObject, String URL) {

		return setLogs()
				.body(jsonObject)
				.post(URL);
	}


	public static Response postWithHeaderAndJsonBody(Map<String, String> headers,
			String jsonObject, String URL) {

		return setLogs()
				.when()
				.headers(headers)
				.body(jsonObject)
				.post(URL);
	}


	public static Response postWithHeaderParam(Map<String, String> headers, String URL) {

		return setLogs()
				.when()
				.headers(headers)
				.post(URL);
	}


	public static Response postWithHeaderAndPathParam(Map<String, String> headers,Map<String, String> pathParms, String URL) {

		return setLogs()
				.when()
				.headers(headers)
				.pathParams(pathParms)
				.post(URL);
	}

	public static Response postWithHeaderAndPathParam(Map<String, String> pathParms, String URL) {

		return setLogs()
				.when()
				.pathParams(pathParms)
				.post(URL);
	}

	public static Response delete(String URL) {
		return setLogs()
				.when()
				.delete(URL);
	}

	public static Response deleteWithHeaderAndPathParam(Map<String, String> headers,
			JSONObject jsonObject, String URL) {

		return setLogs()
				.when()
				.headers(headers)
				.body(jsonObject)
				.delete(URL);
	}

	public static Response deleteWithHeaderAndPathParamWithoutRequestBody(
			Map<String, String> headers, String URL) throws Exception {
		return setLogs()
				.when()
				.headers(headers)
				.delete(URL);
	}

	public static Response putWithHeaderAndBodyParam(Map<String, String> headers,
			JSONObject jsonObject, String URL) {

		return RestAssured.given().headers(headers).contentType(getContentType()).request()
				.body(jsonObject).when().put(URL);
	}
	
	public static Response putWithBodyParam(File file, String URL) {

		return RestAssured.given().contentType(getContentType()).request()
				.body(file).when().put(URL);
	}

	public static ValidatableResponse enableResponseLog(Response response) {
		return response.then().log().all();
	}

	private static ContentType getContentType() {
		return ContentType.JSON;
	}

	public static void verifyContentType(Response response, String type) {
		if(response.getContentType().toLowerCase().contains(type.toLowerCase())) {
			reportRequest("The Content type "+type+" matches the expected content type", "PASS");
		}else {
			reportRequest("The Content type "+type+" does not match the expected content type "+response.getContentType(), "FAIL");	
		}
	}
	
	public static void verifyResponseCode(Response response, int code) {
		if(response.statusCode() == code) {
			reportRequest("The status code "+code+" matches the expected code", "PASS");
		}else {
			reportRequest("The status code "+code+" does not match the expected code"+response.statusCode(), "FAIL");	
		}
	}

	public static void verifyResponseTime(Response response, long time) {
		if(response.time() <= time) {
			reportRequest("The time taken "+response.time() +" with in the expected time", "PASS");
		}else {
			reportRequest("The time taken "+response.time() +" is greater than expected SLA time "+time,"FAIL");		
		}
	}

	public static void verifyContentWithKey(Response response, String key, String expVal) {
		if(response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			String actValue = jsonPath.get(key);
			if(actValue.equalsIgnoreCase(expVal)) {
				reportRequest("The JSON response has value "+expVal+" as expected. ", "PASS");
			}else {
				reportRequest("The JSON response :"+actValue+" does not have the value "+expVal+" as expected. ", "FAIL");		
			}
		}
	}

	public static void verifyContentsWithKey(Response response, String key, String expVal) {
		if(response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			List<String> actValue = jsonPath.getList(key);
			if(actValue.get(0).equalsIgnoreCase(expVal)) {
				reportRequest("The JSON response has value "+expVal+" as expected. ", "PASS");
			}else {
				reportRequest("The JSON response :"+actValue+" does not have the value "+expVal+" as expected. ", "FAIL");		
			}
		}
	}

	public static List<String> getContentsWithKey(Response response, String key) {
		if(response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			return jsonPath.getList(key);			
		}else {
			return null;
		}
	}

	public static String getContentWithKey(Response response, String key) {
		if(response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			return (String) jsonPath.get(key);			
		}else {
			return null;
		}
	}

}
