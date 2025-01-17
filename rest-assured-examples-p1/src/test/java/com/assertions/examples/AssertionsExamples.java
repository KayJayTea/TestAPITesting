package com.assertions.examples;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class AssertionsExamples {

	static final String API_KEY = "8ZPHAxbFRvyYPATX569FK1JAYTKWxhYE";

	@BeforeClass
	public static void init() {

		RestAssured.baseURI = "https://api.nytimes.com";
		RestAssured.basePath = "/svc/books/v3";
	}

	// 1) Verify if the number of items is equal to 59
	@Test
	public void test_001() {

		given().queryParam("api-key", API_KEY).when().get("/lists/names.json").then().body("num_results", equalTo(59));
	}

	// 2) Verify Copyright
	@Test
	public void test_002() {
		given().queryParam("api-key", API_KEY).when().get("/lists/names.json").then().body("copyright",
				equalTo("Copyright (c) 2019 The New York Times Company.  All Rights Reserved."));
	}

	// 3) Check single name in ArrayList
	@Test
	public void test_003() {
		given().queryParam("api-key", API_KEY).when().get("/lists/names.json").then().body("results.list_name",
				hasItem("Combined Print and E-Book Fiction"));
	}

	// 4) Checks multiple items in ArrayList
	@Test
	public void test_004() {
		given().queryParam("api-key", API_KEY).when().get("/lists/names.json").then().body("results.list_name",
				hasItems("Combined Print and E-Book Fiction", "Hardcover Graphic Books"));
	}

	// 5) Checks values inside map using hasKey()
	@Test
	public void test_005() {
		given().queryParam("api-key", API_KEY).when().get("/lists/names.json").then().body("results[1]",
				hasKey("oldest_published_date"));
	}

	// 6) Check hashmap values inside a list
	/**
	 * This test is NOT working TODO: Troubleshoot
	 */
//	@Test
//	public void test_006() {
//		given()
//			.queryParam("api-key", API_KEY)
//			.when()
//			.get("/lists/names.json")
//			.then()
//			.body("results.findAll{results.updated=='MONTHLY'}", hasItems(hasEntry("updated", "MONTHLY")));
//	}

	// 7) Checks multiple values in the same statement
	@Test
	public void test_007() {
		// This is a test
		given().queryParam("api-key", API_KEY).when().get("/lists/names.json").then()
				.body("results[1]", hasKey("oldest_published_date"))
//			.body("results.findAll{results.updated=='MONTHLY'}", hasItems(hasEntry("updated", "MONTHLY")))
				.body("results.list_name", hasItems("Combined Print and E-Book Fiction", "Hardcover Graphic Books"))
				.statusCode(200);
	}

	// 7) Checks multiple values in the same statement
	@Test
	public void test_008() {
		given().queryParam("api-key", API_KEY).when().get("/lists/names.json").then()
				.body("results.size()", equalTo(59)).body("results.size()", greaterThan(10))
				.body("results.size()", lessThan(60)).body("results.size()", greaterThanOrEqualTo(59))
				.body("results.size()", lessThanOrEqualTo(59)).statusCode(200);
	}
}
