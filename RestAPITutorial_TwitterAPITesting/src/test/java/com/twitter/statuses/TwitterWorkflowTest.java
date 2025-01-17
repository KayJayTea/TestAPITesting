package com.twitter.statuses;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.twitter.common.RestUtilitites;
import com.twitter.constants.Endpoints;
import com.twitter.constants.Path;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TwitterWorkflowTest {
	
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	String tweetId = "";
	
	@BeforeClass
	public void setup() {
		reqSpec = RestUtilitites.getRequestSpecification();
		reqSpec.basePath(Path.STATUSES);
		
		resSpec = RestUtilitites.getResponseSpecification();
	}
	
	@Test
	public void postTweet() {
		Response response = given()
			.spec(RestUtilitites.createQueryParam(reqSpec, "status", "My First Tweet"))
		.when()
			.post(Endpoints.STATUSES_TWEET_POST)
		.then()
			.spec(resSpec)
			.extract()
			.response();
		
		JsonPath jsPath = RestUtilitites.getJsonPath(response);
		tweetId = jsPath.get("id_str");
		System.out.println("The response.path: " + tweetId);
	}
	
	@Test(dependsOnMethods = {"postTweet"})
	public void readTweet() {
		RestUtilitites.setEndPont(Endpoints.STATUSES_TWEET_READ_SINGLE);
		Response res = RestUtilitites.getResponse(
				RestUtilitites.createQueryParam(reqSpec, "id", tweetId), "get");
		
		String text = res.path("text");
		System.out.println("The tweet text is: " + text + "\n");
	}
	
	@Test(dependsOnMethods = {"readTweet"})
	public void deleteTweet() {
		given()
			.spec(RestUtilitites.createPathParam(reqSpec, "id", tweetId))
		.when()
			.post(Endpoints.STATUSES_TWEET_DESTROY)
		.then()
			.spec(resSpec);
	}
}
