package com.formauthorization.examples;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.filter.session.SessionFilter;

public class FormAuthWithCSRF {
	
	public static SessionFilter filter;

	@BeforeClass
	public static void init() {
		filter = new SessionFilter();
		RestAssured.baseURI = "http://localhost:9090";
		
		RestAssured
		.given()
			// .auth().form("user", "user", new FormAuthConfig("/login", "uname", "pwd").withAutoDetectionOfCsrf())
			.auth().form("user", "user", new FormAuthConfig("/login", "uname", "pwd").withCsrfFieldName("_csrf"))
			.filter(filter)
			.get();
	}
	
	@Test
	public void getAllStudents() {
		RestAssured
		.given()
			.sessionId(filter.getSessionId())
			.get("/student/list")
		.then()
			.log().all();
	}
}
