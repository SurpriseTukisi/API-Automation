package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		StepDefination sd = new StepDefination();
		
		if(StepDefination.place_id==null) {
		sd.add_place_payload_with_and("Apple", "iOS", "Google");
		sd.usercalls_with_http_request("addPlaceAPI", "Post");
		sd.verify_place_id_created_maps_to_using_getplaceapi("place_id", "Apple", "getPlaceAPI");
		}
	}
}
