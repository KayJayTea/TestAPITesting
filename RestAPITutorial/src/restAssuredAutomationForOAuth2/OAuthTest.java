package restAssuredAutomationForOAuth2;

import static io.restassured.RestAssured.given;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.path.json.JsonPath;

public class OAuthTest {

	public static void main(String[] args) throws Exception {

		System.getProperty("webdriver.chrome.driver", "C:\\Selenium\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get(
				"https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=462165728550-d5udaaq9glt5krq15i6iasrko98niusf.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("kjtodd37@gmail.com");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(ReusableMethods.pwd());
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);

		String url = driver.getCurrentUrl();
		String partial_code = url.split("code=")[1];
		String code = partial_code.split("&scope")[0];

		System.out.println(code);

		driver.quit();

		String access_token_resp = given().urlEncodingEnabled(false).queryParam("code", code)
				.queryParam("client_id", "462165728550-d5udaaq9glt5krq15i6iasrko98niusf.apps.googleusercontent.com")
				.queryParam("client_secret", "jRjWweKySCpI-CuKHulrj9GH")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath js = new JsonPath(access_token_resp);
		String access_token = js.getString("access_token");

		String response = given().queryParam("access_token", access_token).when().log().all()
				.get("https://rahulshettyacademy.com/getCourse.php").asString();

		System.out.println(response);
	}

}
