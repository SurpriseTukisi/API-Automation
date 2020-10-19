package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{
	
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	
	@Given("^Add place payload with (.+), (.+), and (.+)$")
    public void add_place_payload_with_and(String name, String language, String address) throws IOException {
		
		 res=given().spec(requestSpecification())
		.body(data.getPlaceData(name, language, address));

	}

	@When("usercalls {string} with {string} http request")
	public void usercalls_with_http_request(String resource, String method){
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		System.out.println(resource);
		if(method.equalsIgnoreCase("POST"))
		    response =res.when().post(APIResources.valueOf(resource).getResource());
				 //.then().spec(resspec).extract().response();
		else if(method.equalsIgnoreCase("GET"))
			response =res.when().get(APIResources.valueOf(resource).getResource());
	}
	@Then("the API call returns success with status code {int}")
	public void the_api_call_returns_success_with_status_code(Integer int1) {
		
		assertEquals(response.getStatusCode(), 200);
	}
	@And("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
		
		assertEquals(getJsonPath(response, keyValue), Expectedvalue); 
		System.out.println("Actual value is "+getJsonPath(response, keyValue));
	}
	@And("^verify \"([^\"]*)\" created maps to (.+) using \"([^\"]*)\"$")
	public void verify_place_id_created_maps_to_using_getplaceapi(String keyValue, String expectedName, String resource) throws IOException {
		
		place_id=getJsonPath(response, "place_id");
	    res=given().spec(requestSpecification()).queryParam(keyValue, place_id);
	    usercalls_with_http_request(resource, "Get");
	    String actualName = getJsonPath(response, "name");
	    assertEquals(actualName, expectedName);
	}
	@Given("Delete payload with place_id")
	public void delete_payload_with_place_id() throws IOException {
	    res=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}
		
	


}
