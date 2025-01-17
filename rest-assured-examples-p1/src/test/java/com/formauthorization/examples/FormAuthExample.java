package com.formauthorization.examples;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.filter.session.SessionFilter;

public class FormAuthExample {
	
	public static SessionFilter filter;

	@BeforeClass
	public static void init() {
		filter = new SessionFilter();
		RestAssured.baseURI = "http://localhost:8085";
		
		RestAssured
		.given()
			.auth().form("user", "user", new FormAuthConfig("/login", "uname", "pwd"))
			.filter(filter)
			.get();
		
		// System.out.println("Session ID: " + filter.getSessionId() + "\n");
	}
	
	@Test
	public void getAllStudents() {
		RestAssured
		.given()
			.log().all()
			.sessionId(filter.getSessionId())
			.get("/student/list")
		.then()
			.log().all();
	}
}
