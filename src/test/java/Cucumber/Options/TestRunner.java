package Cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class) /* to keep in mind io.cucumber.junit tiene version 4 no junit 5 por eso se usa este RunWith si no sería ExtendWith*/
@CucumberOptions(features = "src/test/java/features",
		plugin="json:target/jsonReports/cucumber-report.json",
		glue="stepDefinitions")
public class TestRunner {
}
