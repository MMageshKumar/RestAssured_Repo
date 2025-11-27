package example.bddStyle;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.commons.io.FileUtils;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

public class b_Schema_validation { // Class Lesson 3

    @Test(enabled = false) // Class 17
    public void default_RestAssuredSchemaValidator () {
        //We can validate only response json with schema using this default method

        File jsonFile=new File ("src/test/resources/A_SamplePost.json");
        File schema_file=new File ("If case File Not in Local Repository (this is common used method)  ");

             RestAssured
                .given()
                .baseUri("https://dummy.restapiexample.com")
                .headers( "Content-Type", "application/json")
                .body(jsonFile)
                .when()
                     .post("api/v1/create")
                .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("file name is enough, if file at resources folder"));
//              .body(JsonSchemaValidator.matchesJsonSchema(schema_file));

    }

    @Test (enabled = false) //class 19
    public void networkNT_SchemaValidator () throws IOException {

        File jsonFile=new File ("src/test/resources/A_SamplePost.json");
        FileInputStream schema_file=new FileInputStream ("");

        ObjectMapper mapper=new ObjectMapper ();
        JsonNode jsonFile_node = mapper.readTree(jsonFile);

        JsonSchemaFactory factory= JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        com.networknt.schema.JsonSchema schema_factory = factory.getSchema(schema_file);

        Set<ValidationMessage> result = schema_factory.validate(jsonFile_node);

        if (result.isEmpty()) {
            System.out.println("No Problem in Schema Side");
        }else {
            for (ValidationMessage x:result) {
                System.out.println(x);
            }
        }

    }

    @Test (enabled = false) //class 20
    public void hamcrest_SchemaValidator () throws IOException {

        File jsonFile=new File ("src/test/resources/A_SamplePost.json");
        FileInputStream schema_file=new FileInputStream ("");

        String json_file_toString = FileUtils.readFileToString(jsonFile,"UFT-8"); //uft-8 why nu theriyathu

        MatcherAssert.assertThat(json_file_toString, JsonSchemaValidator.matchesJsonSchema(schema_file));
    }
}
