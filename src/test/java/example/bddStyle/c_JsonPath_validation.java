package example.bddStyle;

import com.jayway.jsonpath.*;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class c_JsonPath_validation { // Class Lesson 5 with "Jey way json path"

	@Test (invocationCount=0)
	public void multipleTime_Parse_JsonPath () throws IOException { // giving jsonFile as a parameter

		File jsonFile=new File ("src/test/resources/A_SamplePost.json");

		List <Object> Parsed_value_FirstName=JsonPath.read(jsonFile, "$..firstName");

		for (Object x:Parsed_value_FirstName) {
			System.out.println(x);
		}
	}


	@Test (invocationCount=0)
	public void OneTime_Parse_jsonPath () throws IOException { // giving ConfiguredJsonFile as a parameter

		File jsonFile_InputStream=new File ("src/test/resources/A_SamplePost.json");

		Object configured_Obj=Configuration.defaultConfiguration()
				.jsonProvider()
				.parse(jsonFile_InputStream.toString());    // this problem takes 1 hour

		List <Object> Parsed_value_Object=JsonPath.read(configured_Obj,"$..firstName");

		for (Object x : Parsed_value_Object) {
			System.out.println(x);

		}
	}


	@Test (invocationCount=0)
	public void fluentAPI_jsonpath () throws IOException {   //Commonly Used Types

		File jsonFile=new File ("src/test/resources/A_SamplePost.json");

		Configuration configuration=Configuration.defaultConfiguration();

		List <Object>   json_value_ofLast=JsonPath.using(configuration)
				.parse(jsonFile)
				.read("$..lastName");

		for (Object x : json_value_ofLast) {
			System.out.println(x);
		}
	}


	@Test (invocationCount=0)
	public void inline_predicates () throws IOException {

		File jsonFile=new File ("src/test/resources/A_SamplePost.json");

		List <Object>  id_s=JsonPath.parse(jsonFile).read("$.[0].apply.[?(@.price>8)]");

		for (Object x:id_s) {
			System.out.println(x);
		}
	}

	@Test (invocationCount=0)
	public void filter_predicates () throws IOException {

		File jsonFile=new File ("src/test/resources/A_SamplePost.json");

		Filter varOf_filtered= Filter.filter(
				Criteria.where("price").lt(11).and("price").gt(6));

		List <Object> id_s=	JsonPath.parse(jsonFile).read("$.[0].apply.[?]",varOf_filtered);

		for (Object x:id_s) {
			System.out.println(x);
		}

	}

	@Test(invocationCount=0)
	public void configuration_full () throws IOException {

		File json_File=new File ("E:\\sample1.json");

		Configuration config1=Configuration.defaultConfiguration().addOptions(Option.ALWAYS_RETURN_LIST);
		Configuration config2=Configuration.defaultConfiguration().addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);
		Configuration config3=Configuration.defaultConfiguration().addOptions(Option.REQUIRE_PROPERTIES);

		List <Object>  id_s=JsonPath.using(config1).parse(json_File)
				.read("$.[0].apply.[?(@.price>8)].price");

		for (Object x:id_s) {
			System.out.println(x);
		}
	}

}


