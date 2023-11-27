package weatherAPITest;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GETWeather {

	private static final String API_KEY = "d509c3dd21ce3476c519b083bdd698a6";
	private static final String BASE_URI = "https://api.openweathermap.org/data/2.5";

	@Test
	public void testWeatherInLondon() {
		Response response = getWeatherResponse();

		// Assert the status code
	    Assert.assertEquals("Unexpected status code", 200, response.getStatusCode());
	    
	    // Validate JSON schema
	    response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("success_get_weather.json"));


		JsonPath jsonPath = new JsonPath(response.asString());
		String cityName = jsonPath.getString("name");
		String weatherCondition = jsonPath.getString("weather[0].main");
		
	    boolean isLondonCloudy = cityName.equals("London") && weatherCondition.equals("Clouds");

		 // Check if the assertion passed
	    if (isLondonCloudy) {
	        System.out.println("Today's weather in London is expected to be cloudy.");
	    }

	    // Assert that the condition is true
	    Assert.assertTrue("Today's weather in London is expected to be cloudy.", isLondonCloudy);
	}

	private Response getWeatherResponse() {
		return given().param("appid", API_KEY).param("lat", "51.5073219").param("lon", "-0.1276474").when()
				.get(BASE_URI + "/weather");
	}
}

//	@Test
//	public void get_air_pollution() {
//		
//		baseURI = "https://api.openweathermap.org/data/2.5";
//		
//		given().
//			param("appid", "d509c3dd21ce3476c519b083bdd698a6").
//			param("lat", "51.5073219").
//			param("lon", "-0.1276474").
//		when().
//			get("/air_pollution").
//		then()
//			.statusCode(200);
//		
//	}
//	
//	@Test
//	public void get_forecast() {
//		
//		baseURI = "https://api.openweathermap.org/data/2.5";
//		
//		given().
//			param("appid", "d509c3dd21ce3476c519b083bdd698a6").
//			param("lat", "51.5073219").
//			param("lon", "-0.1276474").
//		when().
//			get("/forecast").
//		then()
//			.statusCode(200);
//		
//	}
//}
