package test_framework;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import googleAPI.Resources;
import test_framework.ReusableMethods;

public class e_Basics_Get_All_Reponses {
	
	private static Logger log = LogManager.getLogger(e_Basics_Get_All_Reponses.class.getName());
	
	// GLOBAL VARIABLES
	Properties prop = new Properties();
	
	@BeforeTest
	public void setUp() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\google_apis.properties");
		prop.load(fis);
	}
	
	@Test
	public void Test() {
				
		// BaseURI or host
		RestAssured.baseURI = prop.getProperty("HOST");
		log.info("HOST (e_Basics_Get_All_Reponses): ");
		
		Response resp = 
				given()
					.param("location", prop.getProperty("LOCATION"))
					.param("radius", prop.getProperty("RADIUS"))
					.param("key", prop.getProperty("KEY"))
				.when()
					.get(Resources.searchNearby())
				.then()
					.assertThat()
					.statusCode(200).and()
					.contentType(ContentType.JSON).and()
					.body("results[0].name", equalTo("Hampton")).and()
					// .body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and()
					.header("Server", "scaffolding on HTTPServer2")
					.extract().response();
		
		JsonPath jsp = ReusableMethods.rawToJSON(resp);
		int count = jsp.get("results.size()");
		
		log.info("Count: " + count);
		
		for(int i = 0; i < count; i++) {
			log.info(jsp.getString("results["+ i +"].name"));
			System.out.println(jsp.getString("results["+ i +"].name"));
		}
	}

}
