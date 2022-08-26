package stepDefinitions;

import io.cucumber.java.en.Given;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class examplesStepsDefinitions {
	@Given("a test scenario to be executed")
	public void a_test_scenario_to_be_executed() {
		Response response =
		given().
				baseUri("https://api.postman.com")
				.header("X-api-key, ","headerValue")
				.param("param","paramValue").
		when().
				get("/workspaces").
		then().
				log().all().
				assertThat()
				.statusCode(200)
				.extract()
				.response();
	}
	///Another way to use the request specification
	///given().spec(requestSpecification)
}
