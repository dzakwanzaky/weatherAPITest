package weatherAPITest;

import static io.restassured.RestAssured.given;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GETForecast {
	private static final String API_KEY = "d509c3dd21ce3476c519b083bdd698a6";
	private static final String BASE_URI = "https://api.openweathermap.org/data/2.5";

	@Test
	public void testWeatherInLondon() {
		Response response = getForecastResponse();

		// Assert the status code
		Assert.assertEquals("Unexpected status code", 200, response.getStatusCode());

		// Validate JSON schema
		response.then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("success_get_forecast.json"));

		JsonPath jsonPath = new JsonPath(response.asString());
		String temp = jsonPath.getString("list[0].main.temp");
		String feels_like = jsonPath.getString("list[0].main.feels_like");
		

		boolean isLondonTemp = temp.equals("279.16") && feels_like.equals("275.67");

		// Check if the assertion passed
		if (isLondonTemp) {
			System.out.println("Today's temperature in London is at 279.13 deg Celcius and it feels like 275.64 deg Celcius.");
		}

		// Assert that the condition is true
		Assert.assertTrue(isLondonTemp);
	}

	private Response getForecastResponse() {
		return given().param("appid", API_KEY).param("lat", "51.5073219").param("lon", "-0.1276474").when()
				.get(BASE_URI + "/forecast");
	}
}
