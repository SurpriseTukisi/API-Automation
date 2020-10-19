package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	AddPlace p =new AddPlace();
	Location l =new Location();
	
	public AddPlace getPlaceData(String name, String language, String address) {
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName(name);
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		p.setTypes(myList);
		
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		return p;
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n"
				+ "    \"place_id\": \""+placeId+"\"\r\n"
				+ "}";
		/*return "{\r\n"
				+ "    \"place_id\": \""+placeId+"\"\r\n"
				+ "}";*/
	}

}
