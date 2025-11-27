package example.bddStyle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.util.*;

public class a_SampleBddRequests_and_Serialization { // Class Lesson 1 and 2

	@Test ( enabled = false )
	public void get_Request() { // Retrieving the Data with GET request

	String str = RestAssured
		.given()
		.baseUri("https://dummy.restapiexample.com")
		.when()
		.get("api/v1/employee/1")
		.asPrettyString();

	System.out.println(str);

	}	

	@Test ( enabled = false )
	public void post_Request() { // Creating the Data with POST request (with Path)

		Response response = RestAssured
		.given()
				.baseUri("https://dummy.restapiexample.com")
		.headers( "Content-Type", "application/json")
		.body(" {\n" +
				"        \"id\": 1,\n" +
				"        \"employee_name\": \"Magesh\",\n" +
				"        \"employee_salary\": \"324021\",\n" +
				"        \"employee_age\": \"61\",\n" +
				"        \"profile_image\": \"\"\n" +
				"    },")
		.when()
		.post("api/v1/create");

		System.out.println(response.statusCode());

	}

	@Test (enabled = false)
	public void post_request_withJsonFile () {

		File jsonFile=new File ("src/test/resources/A_SamplePost.json");

		Response response = RestAssured
				.given()
				.baseUri("https://dummy.restapiexample.com")
				.headers( "Content-Type", "application/json")
				.body(jsonFile)
				.when()
				.post("api/v1/create");
		System.out.println(response.statusCode());

	}

	@Test (enabled = false) //Class 12
	public void map_to_json_serialisation() throws JsonProcessingException {  // Jockson (DataBind) library

		List <String> list=new ArrayList <>();
		list.add("Java");
		list.add("Selenium");

		Map <String,Object> map=new LinkedHashMap <> ();
		map.put("Course fees", 20000);
		map.put("Location", "Chennai");
		map.put("Course Name", list);
		System.out.println(map); // Server will not consider this Map data as a Proper Json data. We have to serialize.
		Object map_obj = map; // Converting map to Object

		//Serialization with Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		String str_map = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map_obj);

		Response response = RestAssured
				.given()
				.baseUri("https://dummy.restapiexample.com")
				.headers( "Content-Type", "application/json")
				.body(str_map)
				.when()
				.post("api/v1/create");
		System.out.println(response.statusCode());
	}

	@Test (enabled = false)
	public void pojo_to_json_serialization () throws JsonProcessingException {

		Object objClass = null;  // Instead of POJO class object - giving null

		//Serialization with Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		String str_map = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objClass);

		Response response = RestAssured
				.given()
				.baseUri("https://dummy.restapiexample.com")
				.headers( "Content-Type", "application/json")
				.body(str_map)
				.when()
				.post("api/v1/create");
		System.out.println(response.statusCode());

	}

}
