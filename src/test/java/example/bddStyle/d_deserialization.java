package example.bddStyle;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class d_deserialization {   // Class Lesson 6
	
	@Test (invocationCount=0)
	public void deserialization_jackson_databind() throws StreamReadException, DatabindException, IOException {
		// Deserializing the whole file

		File json_File=new File ("E:\\sample1.json");
		
		ObjectMapper mapper=new ObjectMapper ();
		mapper.readValue(json_File, SamplePOJOClass.class);
	}
	
	@Test
	public void deserialize_JayWayJsonPath () throws IOException {
		// Deserializing the particular Key and Values from the file
		
		File json_File=new File ("E:\\sample1.json");
		
		JacksonMappingProvider mapper=new JacksonMappingProvider () ;
		Configuration config=Configuration
				.builder()
				.mappingProvider(mapper)
				.build();

		SamplePOJOClass samplePojo=JsonPath
				.using(config)
				.parse(json_File)
				.read("$", SamplePOJOClass.class);
	}

	@Test ( enabled= false )
	public void deserialization_Default_RestAssured(){
		Map <String,Object> response = RestAssured.given()
				.baseUri("https://dummy.restapiexample.com")
				.when()
				.get("api/v1/employee/1")
				.then()
				.extract()
				.body()
				.as(new TypeRef<Map<String, Object>>() {});
	}

}
