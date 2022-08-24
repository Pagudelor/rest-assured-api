package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

	@Before("@")
	public void beforeScenario() throws IOException {
		StepDefinitions stepDefinitions = new StepDefinitions();
		stepDefinitions.add_place_payload("name","english","central park");
		stepDefinitions.user_calls_with_method_http_request("AddPlaceAPI","POST");
	}
}
