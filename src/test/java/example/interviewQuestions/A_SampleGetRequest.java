package example.interviewQuestions;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class A_SampleGetRequest {

	@Test
	public void getRequestMethod() {

		RequestSpecification rs = RestAssured.given();
		rs.baseUri("https://dummy.restapiexample.com");

		Response response = rs.request(Method.GET, "api/v1/employee/1");
		String str = response.asPrettyString();

		System.out.println(str);

		response.statusCode();
		response.statusLine();
	}
}
