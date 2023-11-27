package weatherAPITest;

import static io.restassured.RestAssured.given;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GETPollution {
	private static final String API_KEY = "d509c3dd21ce3476c519b083bdd698a6";
	private static final String BASE_URI = "https://api.openweathermap.org/data/2.5";

	@Test
	public void testWeatherInLondon() {
		Response response = getPollutionResponse();

		// Assert the status code
		Assert.assertEquals("Unexpected status code", 200, response.getStatusCode());

		// Validate JSON schema
		response.then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("success_get_pollution.json"));

		JsonPath jsonPath = new JsonPath(response.asString());
		String aqi = jsonPath.getString("list[0].main.aqi");

		boolean isLondonAirGood = aqi.equals("1");

		// Check if the assertion passed
		if (isLondonAirGood) {
			System.out.println("Today's Air Quality Index in London is good.");
		}

		// Assert that the condition is true
		Assert.assertTrue(isLondonAirGood);
	}

	private Response getPollutionResponse() {
		return given().param("appid", API_KEY).param("lat", "51.5073219").param("lon", "-0.1276474").when()
				.get(BASE_URI + "/air_pollution");
	}
}
