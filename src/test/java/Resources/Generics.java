package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class Generics {

	public static RequestSpecification req;

	public RequestSpecification requestSpecification() throws IOException {
		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON)
					.build();
			System.out.println(log);
			return req;
		}
		return req;
	}

	public String getGlobalValue(String keyParameter) throws IOException {
		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream("src/test/java/Resources/global.properties");
		properties.load(fileInputStream);
		return properties.getProperty(keyParameter);
	}

	public String getJsonPath(Response res, String key) {
		String response = res.asString();
		JsonPath jsonPath = new JsonPath(response);
		return jsonPath.get(key).toString();
	}
}
