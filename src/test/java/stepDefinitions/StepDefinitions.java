package stepDefinitions;

import Resources.APIResources;
import Resources.Generics;
import Resources.TestDataBuild;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinitions extends Generics {
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	private static Response response;
	TestDataBuild dataBuild = new TestDataBuild();

	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload( String name, String languaje, String address) throws IOException {
		requestSpecification =
				given()
				.spec(requestSpecification()) /* requestSpecificationBase heredado de generics */
				.body(dataBuild.addPlacePayLoad(name,languaje,address));
	}

	@Given("Delete place payload")
	public void deletePlacePayload() throws IOException {
		add_place_payload("name","english","central park");
		user_calls_with_method_http_request("AddPlaceAPI","POST");

		requestSpecification = given()
						.spec(requestSpecification())
						.body(dataBuild.deletePlacePayload(getJsonPath(response,"place_id")));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_method_http_request(String resource,String httpMethod) {
		APIResources aPIResources = APIResources.valueOf(resource);

		responseSpecification = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();

		if(httpMethod.equalsIgnoreCase("POST")){
			response =requestSpecification.when().post(aPIResources.getResource());
		   }
		else if(httpMethod.equalsIgnoreCase("GET"))
			response =requestSpecification.when().get(aPIResources.getResource());
	}

	@Then("{string} in response body should be {string}")
	public void in_response_body_should_be(String keyValue, String expectedValue) {
		assertEquals(getJsonPath(response,keyValue),expectedValue);
	}

	@Then("verify {string} created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String value, String expectedValue, String resource) throws IOException {
		String place_id= getJsonPath(response,value);
		requestSpecification = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_method_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(expectedValue, actualName);
	}

}
